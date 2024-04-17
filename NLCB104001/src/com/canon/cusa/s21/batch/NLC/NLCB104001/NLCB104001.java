/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB104001;

import static com.canon.cusa.s21.batch.NLC.NLCB104001.constant.NLCB104001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.NLCI1030_01TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Stock Out Data of the Day IF Data Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/22/2013   Hitachi         T.Tomita        Create          MEX-IF017
 *</pre>
 */
public class NLCB104001 extends S21BatchMain {

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
        mainProcess();
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
        new NLCB104001().executeBatch(NLCB104001.class.getSimpleName());
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

    private void mainProcess() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("intfcCratDt", this.procDate);
            queryParam.put("cincShipToGlblCmpyCd", CANON_GRP_OTHER);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(this.commitUnit);
            execParam.setMaxRows(0);

            stmt = this.ssmLLClient.createPreparedStatement("getTargetData", queryParam, execParam);
            rs = stmt.executeQuery();

            List<NLCI1030_01TMsg> inMsgLst = new ArrayList<NLCI1030_01TMsg>(this.totalCommitCount);
            int inputCount = 0;
            int commitCount = 0;
            while (rs.next()) {
                inputCount++;
                inMsgLst.add(createTargetData(rs, inputCount));
                if (this.commitUnit <= inMsgLst.size()) {
                    commitCount = insertTargetData(inMsgLst);
                    inMsgLst = new ArrayList<NLCI1030_01TMsg>(this.totalCommitCount);
                    this.totalCommitCount += commitCount;
                    commitCount = 0;
                }
            }
            if (inputCount != this.totalCommitCount) {
                commitCount = insertTargetData(inMsgLst);
                this.totalCommitCount += commitCount;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private NLCI1030_01TMsg createTargetData(ResultSet rs, int seqNum) throws SQLException {
        NLCI1030_01TMsg inMsg = new NLCI1030_01TMsg();

        ZYPEZDItemValueSetter.setValue(inMsg.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(inMsg.transactionId, this.trxId);
        ZYPEZDItemValueSetter.setValue(inMsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inMsg.unitId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inMsg.seqNumber, new BigDecimal(seqNum));
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpy3Cd, rs.getString("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.cincGlblWhCd, rs.getString("CINC_GLBL_WH_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcStkOutDt, rs.getString("INTFC_STK_OUT_DT"));
        ZYPEZDItemValueSetter.setValue(inMsg.cincShipToGlblCmpyCd, rs.getString("CINC_SHIP_TO_GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.cincRcvGlblWhCd, rs.getString("CINC_RCV_GLBL_WH_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.cincBillToGlblCmpyCd, rs.getString("CINC_BILL_TO_GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.cincGlblCmpyCatgCd, rs.getString("CINC_GLBL_CMPY_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcPrtMdseCd, rs.getString("INTFC_PRT_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtSizeTxt, rs.getString("PRT_SIZE_TXT"));

        BigDecimal intfcStkOutQty = rs.getBigDecimal("INTFC_STK_OUT_QTY");
        ZYPEZDItemValueSetter.setValue(inMsg.intfcStkOutQtySgnTxt, getQtySgnTxt(intfcStkOutQty));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcStkOutQty, intfcStkOutQty.abs());

        ZYPEZDItemValueSetter.setValue(inMsg.cincGlblShpgMethCd, rs.getString("CINC_GLBL_SHPG_METH_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.cincGlblShpgCatgCd, rs.getString("CINC_GLBL_SHPG_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.cincGlblOrdCatgCd, rs.getString("CINC_GLBL_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.prtChrgInd, rs.getString("PRT_CHRG_IND"));
        ZYPEZDItemValueSetter.setValue(inMsg.cincDsplRsnCd, rs.getString("CINC_DSPL_RSN_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcCratDt, rs.getString("INTFC_CRAT_DT"));

        return inMsg;
    }

    private String getQtySgnTxt(BigDecimal qty) {
        if (BigDecimal.ZERO.compareTo(qty) < 0) {
            return QTY_SIGN_MINUS;
        }
        return QTY_SIGN_PLUS;
    }

    private int insertTargetData(List<NLCI1030_01TMsg> inMsgLst) {
        NLCI1030_01TMsg[] inMsgArray;
        inMsgArray = new NLCI1030_01TMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(MSG_ID_NLCM0125E, new String[] {MSG_ITEM_INTFC_TBL_NM });
        }

        commit();
        return insertCount;
    }
}
