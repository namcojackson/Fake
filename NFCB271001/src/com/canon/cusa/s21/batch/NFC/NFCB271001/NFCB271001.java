/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Account Period Update Batch.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/23/2009   Canon           H.Tokunaga        Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB271001;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_ACCT_DTTMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Account Period Update Batch
 */
public class NFCB271001 extends S21BatchMain {

    /** GlobalCompanyCode. */
    private String glblCmpyCode = "";

    /** Envparameter subSystemCode. */
    private String subSystemCode = "";

    /** Envparameter onBatchTypeCode. */
    private String onBatchTypeCode = "";

    /** Envparameter procTypeCode. */
    private String procTypeCode = "";

    /** Batch Process Date. */
    private String batProcDate = "";

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

    /** subString to index. */
    private static final int SUB_STRING_TO_INDEX = 6;

    /** Message ID NFCM0001E. */
    private static final String NFCM0001E = "NFCM0001E";

    /** Message ID NFCM0501E. */
    private static final String NFCM0501E = "NFCM0501E";

    /** Message ID NFCM0532E. */
    private static final String NFCM0532E = "NFCM0532E";

    /** Message ID NFCM0581E. */
    private static final String NFCM0581E = "NFCM0581E";

    /** Message ID NFCM0584I. */
    private static final String NLBM0006I = "NFCM0584I";

    /** Message ID NFCM0593I. */
    private static final String NLBM0008I = "NFCM0593I";

    /** Message String batProcDate. */
    private static final String[] MSG_BAT_PROC_DATE = {"batProcDate" };

    /** Message String AR_ACCT_DT. */
    private static final String[] MSG_AR_ACCT_DT = {"AR_ACCT_DT" };

    /** GLBL_CMPY_CD. */
    private static final String[] GLBL_CMPY_CD = {"GLBL_CMPY_CD" };

    /** SUB_SYS_CD. */
    private static final String[] SUB_SYS_CD = {"SUB_SYS_CD" };

    /** ONL_BAT_TP_CD. */
    private static final String[] ONL_BAT_TP_CD = {"ONL_BAT_TP_CD" };

    /** PROC_TP_CD. */
    private static final String[] PROC_TP_CD = {"PROC_TP_CD" };

    /** PROGLAM ID. */
    private static final String[] PROGLAM_ID = {"NFCB271001" };

    /** Table Column Name(AR_ACCT_DT) */
    /** GLBL_CMPY_CD. */
    private static final String AIDCOW_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** SUB_SYS_CD. */
    private static final String AIDCOW_SUB_SYS_CD = "SUB_SYS_CD";

    /** ONL_BAT_TP_CD. */
    private static final String AIDCOW_ONL_BAT_TP_CD = "ONL_BAT_TP_CD";

    /**
     * initRoutine.
     */
    @Override
    protected final void initRoutine() {
        debugLog("initRoutine start");
        S21InfoLogOutput.println(NLBM0006I, PROGLAM_ID);
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

        if (S21StringUtil.isEmpty(getUserVariable1())) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0501E, SUB_SYS_CD);
        }

        if (S21StringUtil.isEmpty(getUserVariable2())) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0501E, ONL_BAT_TP_CD);
        }
        if (S21StringUtil.isEmpty(getUserVariable3())) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0501E, PROC_TP_CD);
        }

        this.subSystemCode = getUserVariable1();
        this.onBatchTypeCode = getUserVariable2();
        this.procTypeCode = getUserVariable3();

        debugLog("check EnvParameter");
        debugLog("GLBL_CMPY_CD < " + this.glblCmpyCode + " >");
        debugLog("SUB_SYS_CD < " + this.subSystemCode + " >");
        debugLog("ONL_BAT_TP_CD < " + this.onBatchTypeCode + " >");
        debugLog("PROC_TP_CD < " + this.procTypeCode + " >");

        debugLog("checkInputParam end");
    }

    /**
     * mainRoutine.
     */
    @Override
    protected void mainRoutine() {
        debugLog("mainRoutine start");
        try {
            execute();
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
        S21InfoLogOutput.println(NLBM0008I, PROGLAM_ID);

        debugLog("termRoutine end");
    }

    /**
     * call main method.
     * @param args String[]
     */
    public static void main(final String[] args) {
        new NFCB271001().executeBatch(NFCB271001.class.getSimpleName());
    }

    /**
     * execute.
     * 
     * <pre>
     * The date of accounting is updated. 
     * </pre>
     * 
     * @throws SQLException
     */
    private void execute() throws SQLException {
        debugLog("execute start");
        boolean returnCode = doProcess();
        if (!returnCode) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0001E);
        }
        debugLog("execute end");
    }

    /**
     * doProcess.
     * @return boolean true:Data exist false:Data not exist
     * @throws SQLException
     */
    private boolean doProcess() throws SQLException {
        debugLog("doProcess start");

        AR_ACCT_DTTMsg arAcctDtTMsg = new AR_ACCT_DTTMsg();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(AIDCOW_GLBL_CMPY_CD, this.glblCmpyCode);
        param.put(AIDCOW_SUB_SYS_CD, this.subSystemCode);
        param.put(AIDCOW_ONL_BAT_TP_CD, this.onBatchTypeCode);
        S21SsmEZDResult result = NFCB271001Query.getInstance().getArAcctDtTMsgList(param, arAcctDtTMsg);

        if (result.getQueryResultCount() <= 0) {
            return false;
        }

        debugLog("ACCT_YR_MTH < " + arAcctDtTMsg.acctYrMth.getValue() + " >");
        debugLog("ACCT_DT < " + arAcctDtTMsg.acctDt.getValue() + " >");

        getBatProcDate();
        if (S21StringUtil.isEmpty(this.batProcDate)) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0581E, MSG_BAT_PROC_DATE);
        }

        if (this.batProcDate.length() < SUB_STRING_TO_INDEX) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0581E, MSG_BAT_PROC_DATE);
        }

        String returnCode = updArAcctDt(arAcctDtTMsg);
        if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(returnCode)) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0532E, MSG_AR_ACCT_DT);
        }

        if (commitCount > 0) {
            commit();
            this.totalCommitCount += this.commitCount;
            this.commitCount = 0;
        }

        debugLog("doProcess end");
        return true;
    }

    /**
     * get batProcDate.
     */
    private void getBatProcDate() {
        this.batProcDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCode);
    }

    /**
     * updArAcctDt.
     * @return String Update
     * @param tMsg AR_ACCT_DTTMsg
     * @throws SQLException
     */
    private String updArAcctDt(final AR_ACCT_DTTMsg tMsg) throws SQLException {
        debugLog("updArAcctDt start");

        final AR_ACCT_DTTMsg outRecord1 = new AR_ACCT_DTTMsg();
        boolean updateFlg = false;
        String rtnCode = NFCConst.CST_EZ_RETURN_CODE_CPLT;

        outRecord1.glblCmpyCd.setValue(this.glblCmpyCode);
        outRecord1.subSysCd.setValue(this.subSystemCode);
        outRecord1.onlBatTpCd.setValue(this.onBatchTypeCode);

        /* daily */
        if (NFCConst.CST_PRC_TP_CD_DAY.equals(this.procTypeCode)) {
            if (this.batProcDate.substring(0, SUB_STRING_TO_INDEX).equals(tMsg.acctYrMth.getValue())) {
                updateFlg = true;
            }
        } else {
            if (NFCConst.CST_PRC_TP_CD_MNTH.equals(this.procTypeCode)) {
                if (Integer.valueOf(tMsg.acctYrMth.getValue()).intValue() < Integer.valueOf(this.batProcDate.substring(0, SUB_STRING_TO_INDEX)).intValue()) {
                    updateFlg = true;
                }
            }
        }

        if (updateFlg == true) {
            debugLog("ACCT_DT < " + this.batProcDate + " >");
            debugLog("ACCT_YR_MTH < " + this.batProcDate.substring(0, SUB_STRING_TO_INDEX) + " >");
            AR_ACCT_DTTMsg result = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKeyForUpdate(outRecord1);
            if (result != null) {
                if (NFCConst.CST_PRC_TP_CD_DAY.equals(this.procTypeCode)) {
                    result.acctYrMth.setValue(tMsg.acctYrMth.getValue());
                    result.acctDt.setValue(this.batProcDate);
                } else {
                    if (NFCConst.CST_PRC_TP_CD_MNTH.equals(this.procTypeCode)) {
                        result.acctYrMth.setValue(this.batProcDate.substring(0, SUB_STRING_TO_INDEX));
                        result.acctDt.setValue(this.batProcDate);
                    }
                }
                EZDTBLAccessor.update(result);
                rtnCode = result.getReturnCode();
                debugLog("rtnCode < " + rtnCode + " >");
            }

            if (NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(rtnCode)) {
                commitCount++;
            } else {
                this.errCnt++;
                setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
                throw new S21AbendException(NFCM0532E, MSG_AR_ACCT_DT);
            }
            this.procCount++;
            this.normalCnt++;

        }

        debugLog("updArAcctDt end");
        return rtnCode;
    }

    /**
     * Output debug log.
     * @param logmsg String
     */
    protected final void debugLog(final String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
