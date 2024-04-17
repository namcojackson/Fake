/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB101001;

import static com.canon.cusa.s21.batch.NLC.NLCB101001.constant.NLCB101001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.NLCI1010_01TMsg;
import business.db.NLCI1020_01TMsg;

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
 * S-CUBE Inv. Master Info to CINC (WWABF304/312) IF Data Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/18/2013   Hitachi         K.Kishimoto     Create          N/A
 * 03/22/2018   CITS            K.Masaki        Update          QC#25038
 *</pre>
 */
public class NLCB101001 extends S21BatchMain {

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

        checkParam();

        if (this.commitUnit <= 0 || this.commitUnit >= MAX_COMMIT_NUMBER) {
            this.commitUnit = MAX_COMMIT_NUMBER;
        }

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
        new NLCB101001().executeBatch(NLCB101001.class.getSimpleName());
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

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(this.commitUnit);
            execParam.setMaxRows(0);

            stmt = this.ssmLLClient.createPreparedStatement("getDailyData", queryParam, execParam);
            rs = stmt.executeQuery();

            List<NLCI1010_01TMsg> inMsgLst = new ArrayList<NLCI1010_01TMsg>();
            int inputCount = 0;
            int commitCount = 0;
            while (rs.next()) {
                inputCount++;
                inMsgLst.add(createDailyData(rs, inputCount));
                if (this.commitUnit <= inMsgLst.size()) {
                    commitCount = insertDailyData(inMsgLst);
                    inMsgLst = new ArrayList<NLCI1010_01TMsg>();
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

    private NLCI1010_01TMsg createDailyData(ResultSet rs, int seqNum) throws SQLException {
        NLCI1010_01TMsg inMsg = new NLCI1010_01TMsg();

        ZYPEZDItemValueSetter.setValue(inMsg.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(inMsg.transactionId, this.trxId);
        ZYPEZDItemValueSetter.setValue(inMsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inMsg.unitId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inMsg.seqNumber, new BigDecimal(seqNum));
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpy3Cd, rs.getString("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.cincGlblWhCd, rs.getString("CINC_GLBL_WH_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcInvtyDt, rs.getString("INTFC_INVTY_DT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcPrtMdseCd, rs.getString("INTFC_PRT_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtSizeTxt, rs.getString("PRT_SIZE_TXT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcInTrnstFlg, rs.getString("INTFC_IN_TRNST_FLG"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtInvtyCatgCd, rs.getString("PRT_INVTY_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcQtySgnTxt, rs.getString("INTFC_QTY_SGN_TXT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcInvtyStkQty, rs.getBigDecimal("INTFC_INVTY_STK_QTY"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtChrgInd, rs.getString("PRT_CHRG_IND"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcCratDt, rs.getString("INTFC_CRAT_DT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcUpdInd, "0");
        ZYPEZDItemValueSetter.setValue(inMsg.fill8Txt, "");
        return inMsg;
    }

    private int insertDailyData(List<NLCI1010_01TMsg> inMsgLst) {
        NLCI1010_01TMsg[] inMsgArray;
        inMsgArray = new NLCI1010_01TMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(MSG_ID_NLAM1295E, new String[] {"NLCI1010_01" });
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

            List<NLCI1020_01TMsg> inMsgLst = new ArrayList<NLCI1020_01TMsg>();
            int inputCount = 0;
            int commitCount = 0;
            while (rs.next()) {
                inputCount++;
                inMsgLst.add(createMonthlyData(rs, inputCount));
                if (this.commitUnit <= inMsgLst.size()) {
                    commitCount = insertMonthlyData(inMsgLst);
                    inMsgLst = new ArrayList<NLCI1020_01TMsg>();
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

    private NLCI1020_01TMsg createMonthlyData(ResultSet rs, int seqNum) throws SQLException {
        NLCI1020_01TMsg inMsg = new NLCI1020_01TMsg();

        ZYPEZDItemValueSetter.setValue(inMsg.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(inMsg.transactionId, this.trxId);
        ZYPEZDItemValueSetter.setValue(inMsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inMsg.unitId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inMsg.seqNumber, new BigDecimal(seqNum));
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpy3Cd, rs.getString("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.cincGlblWhCd, rs.getString("CINC_GLBL_WH_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcInvtyDt, rs.getString("INTFC_INVTY_DT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcPrtMdseCd, rs.getString("INTFC_PRT_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtSizeTxt, rs.getString("PRT_SIZE_TXT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcInTrnstFlg, rs.getString("INTFC_IN_TRNST_FLG"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtInvtyCatgCd, rs.getString("PRT_INVTY_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcQtySgnTxt, rs.getString("INTFC_QTY_SGN_TXT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcInvtyStkQty, rs.getBigDecimal("INTFC_INVTY_STK_QTY"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtChrgInd, rs.getString("PRT_CHRG_IND"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcCratDt, rs.getString("INTFC_CRAT_DT"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcUpdInd, "");
        ZYPEZDItemValueSetter.setValue(inMsg.fill8Txt, "");
        return inMsg;
    }

    private int insertMonthlyData(List<NLCI1020_01TMsg> inMsgLst) {
        NLCI1020_01TMsg[] inMsgArray;
        inMsgArray = new NLCI1020_01TMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(MSG_ID_NLAM1295E, new String[] {"NLCI1020_01" });
        }

        commit();
        return insertCount;
    }
}
