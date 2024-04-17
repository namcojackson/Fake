/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB109001;

import static com.canon.cusa.s21.batch.NMA.NMAB109001.constant.NMAB109001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.NMAI1040_01TMsg;
import business.db.NMAI1050_01TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * S-Cube Parts Master Info to CINC  (WWABF301/311) (IF Data Creation)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/18/2013   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NMAB109001 extends S21BatchMain {

    /** SSM Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Transaction table Access */
    private S21TransactionTableAccessor trxAccess;

    /** Transaction ID */
    private BigDecimal trxId;

    /** Grobal Company Code */
    private String glblCmpyCd = null;

    /** Interface ID */
    private String interfaceId = null;

    /** Process Code */
    private String processCd = null;

    /** Commit Unit */
    private int commitUnit;

    /** Process Date */
    private String procDate = null;

    /** total commit count */
    private int totalCommitCount;

    @Override
    protected void initRoutine() {

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        this.totalCommitCount = 0;

        this.glblCmpyCd = getGlobalCompanyCode();

        this.interfaceId = getInterfaceID();

        this.processCd = getUserVariable1();

        this.commitUnit = getCommitCount();
        if (this.commitUnit <= 0 || this.commitUnit >= MAX_COMMIT_NUMBER) {
            this.commitUnit = MAX_COMMIT_NUMBER;
        }

        checkParam();


        this.procDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);

        this.trxAccess = new S21TransactionTableAccessor();
        this.trxId = trxAccess.getNextTransactionId();

    }

    @Override
    protected void mainRoutine() {

        if (this.processCd.equals(MONTHLY)) {
            monthlyProcess();
        } else {
            dailyProcess();
        }
        trxAccess.createIntegrationRecordForBatch(this.interfaceId, this.trxId);
    }

    @Override
    protected void termRoutine() {
        setTermState(TERM_CD.NORMAL_END, this.totalCommitCount, 0);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NMAB109001().executeBatch(NMAB109001.class.getSimpleName());
    }

    private void checkParam() {
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }
        if (!ZYPCommonFunc.hasValue(this.interfaceId)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {MSG_ITEM_INTERFACE_ID });
        }
        if (!ZYPCommonFunc.hasValue(this.processCd)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {MSG_ITEM_PROCESS_CODE });
        }
    }

    private void dailyProcess() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("intfcCratDt", this.procDate);
            queryParam.put("preIntfcCratDt", getPreIntfcCratDt());
            queryParam.put("nullCompare", VALUE_NULL_COMPARE);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(this.commitUnit);
            execParam.setMaxRows(0);

            stmt = this.ssmLLClient.createPreparedStatement("getDailyData", queryParam, execParam);
            rs = stmt.executeQuery();

            List<NMAI1040_01TMsg> inMsgLst = new ArrayList<NMAI1040_01TMsg>();
            int inputCount = 0;
            int commitCount = 0;
            while (rs.next()) {
                inputCount++;
                inMsgLst.add(createDailyData(rs, inputCount));
                if (this.commitUnit <= inMsgLst.size()) {
                    commitCount = insertDailyData(inMsgLst);
                    inMsgLst = new ArrayList<NMAI1040_01TMsg>();
                    this.totalCommitCount += commitCount;
                    commitCount = 0;
                }
            }
            if (inputCount != this.totalCommitCount) {
                commitCount = insertDailyData(inMsgLst);
                this.totalCommitCount += commitCount;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private String getPreIntfcCratDt() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("intfcCratDt", this.procDate);
        return (String) ssmBatchClient.queryObject("getPreIntfcCratDt", ssmParam);
    }

    private NMAI1040_01TMsg createDailyData(ResultSet rs, int seqNum) throws SQLException {
        NMAI1040_01TMsg inMsg = new NMAI1040_01TMsg();

        ZYPEZDItemValueSetter.setValue(inMsg.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(inMsg.transactionId, this.trxId);
        ZYPEZDItemValueSetter.setValue(inMsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inMsg.unitId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inMsg.seqNumber, new BigDecimal(seqNum));
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpy3Cd, rs.getString("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcPrtMdseCd, rs.getString("INTFC_PRT_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtSizeTxt, rs.getString("PRT_SIZE_TXT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcPrtMdseCd, rs.getString("INTFC_PRT_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtSizeTxt, rs.getString("PRT_SIZE_TXT"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtMainProdCd, rs.getString("PRT_MAIN_PROD_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.cincProdCd, rs.getString("CINC_PROD_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtDescTxt, rs.getString("PRT_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtItemCd, rs.getString("PRT_ITEM_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtItemNm, rs.getString("PRT_ITEM_NM"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtRusCd, rs.getString("PRT_RUS_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtVndDropFlg, rs.getString("PRT_VND_DROP_FLG"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtAssetCd, rs.getString("PRT_ASSET_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.dsctnFlg, rs.getString("DSCTN_FLG"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtYfiFlg, rs.getString("PRT_YFI_FLG"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtDelFlg, rs.getString("PRT_DEL_FLG"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtGtyVndEndDt, rs.getString("PRT_GTY_VND_END_DT"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtGtyEndDt, rs.getString("PRT_GTY_END_DT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcStdCostTxt, rs.getString("INTFC_STD_COST_TXT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcWrtDownCostTxt, rs.getString("INTFC_WRT_DOWN_COST_TXT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcCcyCd, rs.getString("INTFC_CCY_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcPrtSpecWt, rs.getBigDecimal("INTFC_PRT_SPEC_WT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcGlblVndCd, rs.getString("INTFC_GLBL_VND_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcLocalVndCd, rs.getString("INTFC_LOCAL_VND_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcPackSnpQty, rs.getBigDecimal("INTFC_PACK_SNP_QTY"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcSubDivQty, rs.getBigDecimal("INTFC_SUB_DIV_QTY"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcPrtSaleDt, rs.getString("INTFC_PRT_SALE_DT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcPrtEntryDt, rs.getString("INTFC_PRT_ENTRY_DT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcCmntTxt_01, rs.getString("INTFC_CMNT_TXT_01"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcCmntTxt_02, rs.getString("INTFC_CMNT_TXT_02"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcAttrbTxt_01, rs.getString("INTFC_ATTRB_TXT_01"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcAttrbTxt_02, rs.getString("INTFC_ATTRB_TXT_02"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcAttrbTxt_03, rs.getString("INTFC_ATTRB_TXT_03"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcAttrbTxt_04, rs.getString("INTFC_ATTRB_TXT_04"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcUpdInd, VALUE_INTFC_UPD_IND_DAYLY);
        ZYPEZDItemValueSetter.setValue(inMsg.intfcCratDt, rs.getString("INTFC_CRAT_DT"));

        return inMsg;
    }

    private int insertDailyData(List<NMAI1040_01TMsg> inMsgLst) {
        NMAI1040_01TMsg[] inMsgArray;
        inMsgArray = new NMAI1040_01TMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(NMAM0176E, new String[] {"NMAI1040_01" });
        }

        commit();
        return insertCount;
    }

    private void monthlyProcess() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("intfcCratDt", this.procDate);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(this.commitUnit);
            execParam.setMaxRows(0);

            stmt = this.ssmLLClient.createPreparedStatement("getMonthlyData", queryParam, execParam);
            rs = stmt.executeQuery();

            List<NMAI1050_01TMsg> inMsgLst = new ArrayList<NMAI1050_01TMsg>();
            int inputCount = 0;
            int commitCount = 0;
            while (rs.next()) {
                inputCount++;
                inMsgLst.add(createMonthlyData(rs, inputCount));
                if (this.commitUnit <= inMsgLst.size()) {
                    commitCount = insertMonthlyData(inMsgLst);
                    inMsgLst = new ArrayList<NMAI1050_01TMsg>();
                    this.totalCommitCount += commitCount;
                    commitCount = 0;
                }
            }
            if (inputCount != this.totalCommitCount) {
                commitCount = insertMonthlyData(inMsgLst);
                this.totalCommitCount += commitCount;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private NMAI1050_01TMsg createMonthlyData(ResultSet rs, int seqNum) throws SQLException {
        NMAI1050_01TMsg inMsg = new NMAI1050_01TMsg();

        ZYPEZDItemValueSetter.setValue(inMsg.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(inMsg.transactionId, this.trxId);
        ZYPEZDItemValueSetter.setValue(inMsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inMsg.unitId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inMsg.seqNumber, new BigDecimal(seqNum));
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpy3Cd, rs.getString("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcPrtMdseCd, rs.getString("INTFC_PRT_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtSizeTxt, rs.getString("PRT_SIZE_TXT"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtMainProdCd, rs.getString("PRT_MAIN_PROD_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.cincProdCd, rs.getString("CINC_PROD_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtDescTxt, rs.getString("PRT_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtItemCd, rs.getString("PRT_ITEM_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtItemNm, rs.getString("PRT_ITEM_NM"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtRusCd, rs.getString("PRT_RUS_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtVndDropFlg, rs.getString("PRT_VND_DROP_FLG"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtAssetCd, rs.getString("PRT_ASSET_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.dsctnFlg, rs.getString("DSCTN_FLG"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtYfiFlg, rs.getString("PRT_YFI_FLG"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtDelFlg, rs.getString("PRT_DEL_FLG"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtGtyVndEndDt, rs.getString("PRT_GTY_VND_END_DT"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtGtyEndDt, rs.getString("PRT_GTY_END_DT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcStdCostTxt, rs.getString("INTFC_STD_COST_TXT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcWrtDownCostTxt, rs.getString("INTFC_WRT_DOWN_COST_TXT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcCcyCd, rs.getString("INTFC_CCY_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcPrtSpecWt, rs.getBigDecimal("INTFC_PRT_SPEC_WT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcGlblVndCd, rs.getString("INTFC_GLBL_VND_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcLocalVndCd, rs.getString("INTFC_LOCAL_VND_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcPackSnpQty, rs.getBigDecimal("INTFC_PACK_SNP_QTY"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcSubDivQty, rs.getBigDecimal("INTFC_SUB_DIV_QTY"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcPrtSaleDt, rs.getString("INTFC_PRT_SALE_DT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcPrtEntryDt, rs.getString("INTFC_PRT_ENTRY_DT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcCmntTxt_01, rs.getString("INTFC_CMNT_TXT_01"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcCmntTxt_02, rs.getString("INTFC_CMNT_TXT_02"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcAttrbTxt_01, rs.getString("INTFC_ATTRB_TXT_01"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcAttrbTxt_02, rs.getString("INTFC_ATTRB_TXT_02"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcAttrbTxt_03, rs.getString("INTFC_ATTRB_TXT_03"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcAttrbTxt_04, rs.getString("INTFC_ATTRB_TXT_04"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcAttrbTxt_04, rs.getString("INTFC_ATTRB_TXT_04"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcUpdInd, rs.getString("INTFC_UPD_IND"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcCratDt, rs.getString("INTFC_CRAT_DT"));

        return inMsg;
    }

    private int insertMonthlyData(List<NMAI1050_01TMsg> inMsgLst) {
        NMAI1050_01TMsg[] inMsgArray;
        inMsgArray = new NMAI1050_01TMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(NMAM0176E, new String[] {"NMAI1050_01" });
        }

        commit();
        return insertCount;
    }
}
