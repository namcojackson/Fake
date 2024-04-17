/**
 * <Pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </Pre>
 */
/**
 * <pre>
 * Daily Option Information Entry Batch Class.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/25/2009   Canon           H.Tokunaga        Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB271002;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_DLY_OP_INFOTMsg;
import business.db.AR_TRX_BALTMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCDbConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Daily Option Information Entry Batch
 */
public class NFCB271002 extends S21BatchMain {

    /** GlobalCompanyCode. */
    private String glblCmpyCode = NFCConst.CST_DB_INIT_VAL_STR;

    /** Batch Process Date. */
    private String batProcDate = NFCConst.CST_DB_INIT_VAL_STR;

    /** Processing Count. */
    private int procCount = 0;

    /** Error Count. */
    private int errCnt = 0;

    /** normal Count. */
    private int normalCnt = 0;

    /** commit Count. */
    private int commitCount = 0;

    /** total commit Count. */
    private int totalCommitCount = 0;

    /** Message ID NFCM0501E. */
    private static final String NFCM0501E = "NFCM0501E";

    /** Message ID NFCM0532E. */
    private static final String NFCM0531E = "NFCM0531E";

    /** Message ID NFCM0532E. */
    private static final String NFCM0532E = "NFCM0532E";

    /** Message ID NFCM0533E. */
    private static final String NFCM0533E = "NFCM0533E";

    /** Message ID NFCM0581E. */
    private static final String NFCM0581E = "NFCM0581E";

    /** Message ID NFCM0584I. */
    private static final String NFCM0584I = "NFCM0584I";

    /** Message ID NFCM0593I. */
    private static final String NFCM0593I = "NFCM0593I";

    /** Message String batProcDate. */
    private static final String[] MSG_BAT_PROC_DATE = {"batProcDate" };

    /** Message String AR_TRX_BAL. */
    private static final String[] MSG_AR_TRX_BAL = {"AR_TRX_BAL" };

    /** Message String AR_DLY_OP_INFO. */
    private static final String[] MSG_AR_DLY_OP_INFO = {"AR_DLY_OP_INFO" };

    /** GLBL_CMPY_CD. */
    private static final String[] GLBL_CMPY_CD = {"GLBL_CMPY_CD" };

    /** PROGLAM ID. */
    private static final String[] PROGLAM_ID = {"NFCB271002" };

    /**
     * initRoutine.
     */
    @Override
    protected final void initRoutine() {
        debugLog("initRoutine start");
        S21InfoLogOutput.println(NFCM0584I, PROGLAM_ID);
        checkInputParam();

        debugLog("initRoutine end");
    }

    /**
     * getGlblCmpyCd.
     * 
     * <pre>
     * </pre>
     */
    private boolean getGlblCmpyCd() {
        debugLog("getEnvParameter start");

        this.glblCmpyCode = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (S21StringUtil.isEmpty(this.glblCmpyCode)) {
            return false;
        }
        debugLog("GlobalCompanyCode=<" + this.glblCmpyCode + ">");
        debugLog("getEnvParameter end");
        return true;
    }

    /**
     * Check parameter.
     */
    private void checkInputParam() {
        debugLog("checkInputParam start");

        if (getGlblCmpyCd() == false) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0501E, GLBL_CMPY_CD);
        }

        debugLog("check EnvParameter");
        debugLog("GLBL_CMPY_CD < " + this.glblCmpyCode + " >");

        debugLog("checkInputParam end");
    }

    /**
     * mainRoutine.
     */
    @Override
    protected void mainRoutine() {
        debugLog("mainRoutine start");
        try {
            doProcess();
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        debugLog("mainRoutine end");
    }

    /**
     * termRoutine.
     */
    @Override
    protected final void termRoutine() {
        debugLog("termRoutine start");

        setTermState(TERM_CD.NORMAL_END, this.normalCnt, this.errCnt, this.procCount);
        S21InfoLogOutput.println(NFCM0593I, PROGLAM_ID);

        debugLog("termRoutine end");
    }

    /**
     * call main method.
     * @param args String[]
     */
    public static void main(final String[] args) {
        new NFCB271002().executeBatch(NFCB271002.class.getSimpleName());
    }

    /**
     * doProcess.
     * @return boolean true:Data exist false:Data not exist
     * @throws SQLException
     */
    private void doProcess() throws SQLException {

        debugLog("doProcess start");

        BigDecimal fromArTrxBalPk;
        BigDecimal thruArTrxBalPk;

        AR_DLY_OP_INFOTMsg arDlyOpInfoTMsg = new AR_DLY_OP_INFOTMsg();
        Map<String, Object> paramDlyOpInfo = new HashMap<String, Object>();
        paramDlyOpInfo.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCode);
        S21SsmEZDResult resultDlyOpInfo = NFCB271002Query.getInstance().getThruArTrxBalPkTMsgList(paramDlyOpInfo, arDlyOpInfoTMsg);

        if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(resultDlyOpInfo.getResultCode())) {
            this.errCnt++;
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0531E, MSG_AR_DLY_OP_INFO);
        }

        if (ZYPCommonFunc.hasValue(arDlyOpInfoTMsg.thruArTrxBalPk.getValue())) {
            fromArTrxBalPk = arDlyOpInfoTMsg.thruArTrxBalPk.getValue();
        } else {
            fromArTrxBalPk = BigDecimal.ZERO;
        }
        
        debugLog("FROM_AR_TRX_BAL_PK < " + fromArTrxBalPk + " >");

        AR_TRX_BALTMsg arTrxBalTMsg = new AR_TRX_BALTMsg();
        Map<String, Object> paramTrxBal = new HashMap<String, Object>();
        paramTrxBal.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCode);
        S21SsmEZDResult resultArTrxBal = NFCB271002Query.getInstance().getArTrxBalPkTMsgList(paramTrxBal, arTrxBalTMsg);

        if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(resultArTrxBal.getResultCode())) {
            this.errCnt++;
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0531E, MSG_AR_TRX_BAL);
        }
        
        if (ZYPCommonFunc.hasValue(arTrxBalTMsg.arTrxBalPk.getValue())) {
            thruArTrxBalPk = arTrxBalTMsg.arTrxBalPk.getValue();
        } else {
            thruArTrxBalPk = BigDecimal.ZERO;
        }

        debugLog("THRU_AR_TRX_BAL_PK < " + thruArTrxBalPk + " >");

        getBatProcDate();
        if (S21StringUtil.isEmpty(this.batProcDate)) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0581E, MSG_BAT_PROC_DATE);
        }

        AR_DLY_OP_INFOTMsg entryArDlyOpInfoTMsg = new AR_DLY_OP_INFOTMsg();
        entryArDlyOpInfoTMsg.fromArTrxBalPk.setValue(fromArTrxBalPk);
        entryArDlyOpInfoTMsg.thruArTrxBalPk.setValue(thruArTrxBalPk);

        String entryReturnCode = entryArDlyOpInfo(entryArDlyOpInfoTMsg);
        if (NFCConst.CST_EZ_RETURN_CODE_UNIEQU_CONSTRAINT_VIOLATE.equals(entryReturnCode)) {
            updArDlyOpInfo(entryArDlyOpInfoTMsg);
        }

        if (commitCount > 0) {
            commit();
            this.totalCommitCount += this.commitCount;
            this.commitCount = 0;
            debugLog("totalCommitCount <" + this.totalCommitCount + "> ");
        }

        debugLog("doProcess end");
    }

    /**
     * get batProcDate.
     */
    private void getBatProcDate() {
        this.batProcDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCode);
    }

    /**
     * entryArDlyOpInfo.
     * @return String rtnCode
     * @param tMsg AR_DLY_OP_INFOTMsg
     * @throws SQLException
     */
    private String entryArDlyOpInfo(final AR_DLY_OP_INFOTMsg tMsg) throws SQLException {
        debugLog("entryArDlyOpInfo start");

        final AR_DLY_OP_INFOTMsg outRecord1 = new AR_DLY_OP_INFOTMsg();
        String rtnCode = NFCConst.CST_EZ_RETURN_CODE_CPLT;

        this.procCount++;

        outRecord1.glblCmpyCd.setValue(this.glblCmpyCode);
        outRecord1.arOpsDt.setValue(this.batProcDate);
        outRecord1.fromArTrxBalPk.setValue(tMsg.fromArTrxBalPk.getValue());
        outRecord1.thruArTrxBalPk.setValue(tMsg.thruArTrxBalPk.getValue());

        debugLog("GLBL_CMPY_CD < " + this.glblCmpyCode + " >");
        debugLog("AR_OPS_DT < " + this.batProcDate + " >");
        debugLog("FROM_AR_TRX_BAL_PK < " + tMsg.fromArTrxBalPk.getValue() + " >");
        debugLog("THRU_AR_TRX_BAL_PK < " + tMsg.thruArTrxBalPk.getValue() + " >");

        EZDTBLAccessor.insert(outRecord1);

        rtnCode = outRecord1.getReturnCode();
        debugLog("rtnCode < " + rtnCode + " >");

        if (NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(rtnCode)) {
            commitCount++;
        } else if (!NFCConst.CST_EZ_RETURN_CODE_UNIEQU_CONSTRAINT_VIOLATE.equals(rtnCode)) {
            this.errCnt++;
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0532E, MSG_AR_DLY_OP_INFO);
        }

        this.normalCnt++;

        debugLog("entryArDlyOpInfo end");
        return rtnCode;
    }

    /**
     * updArDlyOpInfo.
     * @param tMsg AR_DLY_OP_INFOTMsg
     * @throws SQLException
     */
    private void updArDlyOpInfo(final AR_DLY_OP_INFOTMsg tMsg) throws SQLException {
        debugLog("updArDlyOpInfo start");

        final AR_DLY_OP_INFOTMsg outRecord1 = new AR_DLY_OP_INFOTMsg();
        String rtnCode = NFCConst.CST_EZ_RETURN_CODE_CPLT;

        outRecord1.glblCmpyCd.setValue(this.glblCmpyCode);
        outRecord1.arOpsDt.setValue(this.batProcDate);
        debugLog("GLBL_CMPY_CD < " + this.glblCmpyCode + " >");
        debugLog("AR_OPS_DT < " + this.batProcDate + " >");

        AR_DLY_OP_INFOTMsg result = (AR_DLY_OP_INFOTMsg) EZDTBLAccessor.findByKeyForUpdate(outRecord1);
        if (result != null) {
            this.procCount++;

            result.thruArTrxBalPk.setValue(tMsg.thruArTrxBalPk.getValue());
            EZDTBLAccessor.update(result);
            rtnCode = result.getReturnCode();
            debugLog("rtnCode < " + rtnCode + " >");

            if (NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(rtnCode)) {
                commitCount++;
            } else {
                this.errCnt++;
                setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
                throw new S21AbendException(NFCM0533E, MSG_AR_DLY_OP_INFO);
            }

            this.normalCnt++;
        }

        debugLog("updArDlyOpInfo end");
    }

    /**
     * Output debug log.
     * @param logmsg String
     */
    protected final void debugLog(final String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
