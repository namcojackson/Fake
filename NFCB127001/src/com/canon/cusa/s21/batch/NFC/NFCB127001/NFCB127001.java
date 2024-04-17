/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */

package com.canon.cusa.s21.batch.NFC.NFCB127001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_ACCT_DTTMsg;
import business.db.AR_ADJTMsg;
import business.db.AR_TRX_BAL_MLYTMsg;
import business.db.AR_TRX_BAL_UPD_WRKTMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCDbConst;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Dunning Current Month Updated Balance Extraction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/29/2009   Canon           U.Usui          Create          N/A
 * 11/09/2009   Canon           M.Moriyama      Update          DefID 1599
 * 12/17/2009   Canon           A.Yoshimoto     Update          DefID-2167,DefID-2792
 * 01/21/2010   Canon           M.Moriyama      Update          For TOC/PROD Code
 * 04/19/2010   Canon           H.Tokunaga      Update          DefID 5518  
 * 05/18/2010   Canon           T.Tanaka        Update          6524,6543
 * 05/31/2010   Canon           K.Kimura        Update          DefectID:6658 No:062
 * 07/22/2010   Canon           I.Kondo         Update          DefectID:7901 No:278
 * 08/04/2010   Canon           I.Kondo         Update          Merge.
 * 10/13/2010   Canon           I.Kondo         Update          R2 -> R3 Merge.
 * 11/25/2010   Canon           K.Kimura        Update          R2 -> R3 Merge.
 * 03/25/2016   Hitachi         T.Tsuchida      Update          S21 NA Unit Test #164 Change Update Data
 * 04/11/2016   Fujitsu         S.Fujita        Create          QC#6722
 * 04/14/2016   Fujitsu         S.Fujita        Create          QC#6908
 * 04/14/2016   Fujitsu         S.Fujita        Create          QC#6909
 * </pre>
 */
public class NFCB127001 extends S21BatchMain {

    // -- CONSTANTS --//
    /** Message ID */
    private static final String NFCM0584I = "NFCM0584I";

    /** Program Id */
    private static final String[] PROGRAM_ID = {"NFCB127001" };

    /** Message ID */
    private static final String NFCM0593I = "NFCM0593I";

    /** Message ID */
    private static final String NFCM0522E = "NFCM0522E";

    /** Message ID */
    private static final String NFCM0531E = "NFCM0531E";

// START 2016/04/11 S.Fujita [S21 NA Unit Test QC#6722,MOD]
// START 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
    /** Tbl Name */
    private static final String[] TBL_NAME_VAR_CHAR_CONST = {"VAR_CHAR_CONST" };

    /** Tbl Name */
    private static final String[] TBL_NAME_AR_ACCT_DT = {"AR_ACCT_DT" };

//    /** Tbl Name : TBL_NAME_ACCT_MTH_CTRL */
//    private static final String[] TBL_NAME_ACCT_MTH_CTRL = {"ACCT_MTH_CTRL" };

    /** Tbl Name : TBL_NAME_AR_TRX_BAL_UPD_WRK */
    private static final String[] TBL_NAME_AR_TRX_BAL_UPD_WRK = {"AR_TRX_BAL_UPD_WRK" };

    /** Tbl Name : TBL_NAME_AR_TRX_BAL_MLY */
    private static final String[] TBL_NAME_AR_TRX_BAL_MLY = {"AR_TRX_BAL_MLY" };

//    /** Format Year Month */
//    private static final String FORMAT_YR_MTH = "yyyyMM";
// END 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
// END 2016/04/11 S.Fujita [S21 NA Unit Test QC#6722,MOD]

    /** Message ID */
    /** @ cannot be added. */
    private static final String NFCM0532E = "NFCM0532E";
    /** @ cannot be deleted. */
    private static final String NFCM0534E = "NFCM0534E";

    /** YEAR START INSEX */
    private static final int Y_START_INDEX = 0;

    /** YEAR END INSEX */
    private static final int Y_END_INDEX = 4;

    /** MONTH START INSEX */
    private static final int M_START_INDEX = 4;

    /** MONTH END INSEX */
    private static final int M_END_INDEX = 6;

    /** GL_DT START DATE */
    private static final String CST_GL_DT_FROM = "GL_DT_FROM";

    /** GL_DT END DATE */
    private static final String CST_GL_DT_THRU = "GL_DT_THRU";

    /** COUNT_NUM */
    private static final String CST_COUNT_NUM = "COUNT_NUM";

    /** AR_CASH_APPLY_STS_CD_U */
    private static final String AR_CASH_APPLY_STS_CD_U = "AR_CASH_APPLY_STS_CD_U";

    /** AR_CASH_APPLY_STS_CD_P */
    private static final String AR_CASH_APPLY_STS_CD_P = "AR_CASH_APPLY_STS_CD_P";

    /** AR_APPLY_TP_CD_CSH */
    private static final String AR_APPLY_TP_CD_CSH = "AR_APPLY_TP_CD_CSH";

    /** AR_APPLY_TP_CD_RFD */
    private static final String AR_APPLY_TP_CD_RFD = "AR_APPLY_TP_CD_RFD";

    /** AR_APPLY_TP_CD_ADJ */
    private static final String AR_APPLY_TP_CD_ADJ = "AR_APPLY_TP_CD_ADJ";

    /** AR_APPLY_TP_CD_CRM */
    private static final String AR_APPLY_TP_CD_CRM = "AR_APPLY_TP_CD_CRM";

    /** _EZCancelFlag */
    private static final String CST_EZ_CANCEL_FLAG = "0";

    /** _EZInCompanyCode */
    private static final String CST_EZ_IN_COMPANY_CODE = "000";

    /** _EZUpdateApplicationID */
    private static final String CST_EZ_UPDATE_APPLICATION_ID = "NFCB1270";

    /** NUM_OF_SEPARATE */
    private static final int NUM_OF_SEPARATE = 100000;

    // -- FIELDS --//
    /** GLOBAL_COMPANY_CODE */
    private String glblCmpyCd;

// START 2016/04/11 S.Fujita [S21 NA Unit Test QC#6722,MOD]
// START 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
    /** SUB_SYS_CD */
    private String subSysCd;

//    /** NEXT_YR_MTH */
//    private String nextYrMth;
//
//    /** AFTER_NEXT_YR_MTH */
//    private String afterNextYrMth;
// END 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
// END 2016/04/11 S.Fujita [S21 NA Unit Test QC#6722,MOD]
    /** ACCT_YR_MTH */
    private String acctYrMth;

    /** ACCT_YR_MTH_START_DATE */
    private String acctStartDate;

    /** ACCT_YR_MTH_END_DATE */
    private String acctEndDate;

    /** ACCT_YR_MTH_END_DATE */
    private String recalculationFlg = NFCConst.CST_FLAG_OFF;

    /** Processing Count */
    private int procCount = 0;

    /** normal Count */
    private int normalCnt = 0;

    /** err Count */
    private int errCnt = 0;

    /** commit Count */
    private int commitCount = 0;

    /** commit Tran Cnt */
    private int commitTranCnt = 0;

    /** DEAL_APPLY_ADJ_RSVD_AMT */
    private BigDecimal wDealApplyAdjRsvdAmt = BigDecimal.ZERO;

    /** FUNC_APPLY_ADJ_RSVD_AMT */
    private BigDecimal wFuncApplyAdjRsvdAmt = BigDecimal.ZERO;

    /** DEAL_APPLY_GRS_AMT */
    private BigDecimal wDealApplyGrsAmt = BigDecimal.ZERO;

    /** FUNC_APPLY_GRS_AMT */
    private BigDecimal wFuncApplyGrsAmt = BigDecimal.ZERO;

    /** DEAL_APPLY_ADJ_AMT */
    private BigDecimal wDealApplyAdjAmt = BigDecimal.ZERO;

    /** FUNC_APPLY_ADJ_AMT */
    private BigDecimal wFuncApplyAdjAmt = BigDecimal.ZERO;

    /** DEAL_APPLY_CR_AMT */
    private BigDecimal wDealApplyCrAmt = BigDecimal.ZERO;

    /** FUNC_APPLY_CR_AMT */
    private BigDecimal wFuncApplyCrAmt = BigDecimal.ZERO;

    /** DEAL_APPLY_CASH_DISC_AMT */
    private BigDecimal wDealApplyCashDiscAmt = BigDecimal.ZERO;

    /** FUNC_APPLY_CASH_DISC_AMT */
    private BigDecimal wFuncApplyCashDiscAmt = BigDecimal.ZERO;

    /** DEAL_RMNG_BAL_GRS_AMT */
    private BigDecimal wDealRmngBalGrsAmt = BigDecimal.ZERO;

    /** FUNC_RMNG_BAL_GRS_AMT */
    private BigDecimal wFuncRmngBalGrsAmt = BigDecimal.ZERO;

    /** SQL Access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFCB127001().executeBatch(NFCB127001.class.getSimpleName());
    }

    /**
     */
    @Override
    protected void initRoutine() {

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        S21InfoLogOutput.println(NFCM0584I, PROGRAM_ID);

        if (!getGlblCmpyCd()) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0522E);
        }

        deletearAgingCalcPer();

// START 2016/04/11 S.Fujita [S21 NA Unit Test QC#6722,MOD]
// START 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
        if (!getSubSysCd()) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0531E, TBL_NAME_VAR_CHAR_CONST);
        }

        if (!getAcctDt()) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0531E, TBL_NAME_AR_ACCT_DT);
        }

        this.acctStartDate = this.acctYrMth + NFCConst.CST_START_DATE_OF_MONTH;
        int y = Integer.parseInt(this.acctStartDate.substring(Y_START_INDEX, Y_END_INDEX));
        int m = Integer.parseInt(this.acctStartDate.substring(M_START_INDEX, M_END_INDEX)) - 1;
        Calendar cal = Calendar.getInstance();
        cal.set(y, m, 1);
        this.acctEndDate = this.acctYrMth + Integer.toString(cal.getActualMaximum(Calendar.DATE));
        cal.add(Calendar.MONTH, 1);

// START 2016/04/14 S.Fujita [S21 NA Unit Test QC#6908,MOD]
        deleteArTrxBalMlyData(TBL_NAME_AR_TRX_BAL_MLY);
// END 2016/04/14 S.Fujita [S21 NA Unit Test QC#6908,MOD]

//        if (!getAcctMthCtrl()) {
//            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
//            throw new S21AbendException(NFCM0531E, TBL_NAME_ACCT_MTH_CTRL);
//        }
//
//        this.acctStartDate = this.acctYrMth + NFCConst.CST_START_DATE_OF_MONTH;
//        int y = Integer.parseInt(this.acctStartDate.substring(Y_START_INDEX, Y_END_INDEX));
//        int m = Integer.parseInt(this.acctStartDate.substring(M_START_INDEX, M_END_INDEX)) - 1;
//        Calendar cal = Calendar.getInstance();
//        cal.set(y, m, 1);
//        this.acctYrMth = new SimpleDateFormat(FORMAT_YR_MTH).format(cal.getTime());
//        this.acctEndDate = this.acctYrMth + Integer.toString(cal.getActualMaximum(Calendar.DATE));
//        cal.add(Calendar.MONTH, 1);
//        this.nextYrMth = new SimpleDateFormat(FORMAT_YR_MTH).format(cal.getTime());
//        cal.add(Calendar.MONTH, 1);
//        this.afterNextYrMth = new SimpleDateFormat(FORMAT_YR_MTH).format(cal.getTime());
// END 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
// END 2016/04/11 S.Fujita [S21 NA Unit Test QC#6722,MOD]

        /* */
        this.commitTranCnt = getCommitCount();

    }

    /**
     */
    @Override
    protected void mainRoutine() {

        execute();

    }

    /**
     */
    @Override
    protected void termRoutine() {

        setTermState(TERM_CD.NORMAL_END, this.normalCnt, this.errCnt, this.procCount);
        S21InfoLogOutput.println(NFCM0593I, PROGRAM_ID);

    }

    /**
     * @return false:NG true:OK
     */
    private boolean getGlblCmpyCd() {

        this.glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (S21StringUtil.isEmpty(this.glblCmpyCd)) {
            return false;
        }

        return true;
    }

    /**
     */
    private void deletearAgingCalcPer() {

        AR_TRX_BAL_UPD_WRKTMsg arTrxBalUpdWrkTMsg = new AR_TRX_BAL_UPD_WRKTMsg();
        arTrxBalUpdWrkTMsg.glblCmpyCd.setValue(this.glblCmpyCd);

        S21FastTBLAccessor.removeByPartialValue(arTrxBalUpdWrkTMsg, new String[] {NFCDbConst.GLBL_CMPY_CD_J });
        commit();
    }

// START 2016/04/11 S.Fujita [S21 NA Unit Test QC#6722,MOD]
// START 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
    /**
     * @return false:NG true:OK
     */
    private boolean getSubSysCd() {

        this.subSysCd = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.CST_VAR_CHAR_CONST_NAME_SUB_SYS_ID, this.glblCmpyCd);
        if (S21StringUtil.isEmpty(this.subSysCd)) {
            return false;
        }

        return true;

    }

    /**
     * @return false:NG true:OK
     */
    private boolean getAcctDt() {

        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put(NFCDbConst.GLBL_CMPY_CD, this.glblCmpyCd);
        sqlParam.put(NFCDbConst.SUB_SYS_CD, this.subSysCd);
        sqlParam.put(NFCDbConst.ONL_BAT_TP_CD, NFCConst.CST_ONL_BAT_TP_CD_BAT);

        AR_ACCT_DTTMsg arAcctDtTMsg = new AR_ACCT_DTTMsg();
        S21SsmEZDResult result = NFCB127001Query.getInstance().getArAcctDt(sqlParam, arAcctDtTMsg);

        if (result.getQueryResultCount() == 0) {
            return false;
        }

        this.acctYrMth = arAcctDtTMsg.acctYrMth.getValue();

        return true;

    }
// END 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
// END 2016/04/11 S.Fujita [S21 NA Unit Test QC#6722,MOD]

// START 2016/04/11 S.Fujita [S21 NA Unit Test QC#6722,MOD]
// START 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
//    /**
//     * Get Account Month Control
//     * @return false:NG true:OK
//     */
//    private boolean getAcctMthCtrl() {
//
//        ACCT_MTH_CTRLTMsg inTMsg = new ACCT_MTH_CTRLTMsg();
//        inTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
//        inTMsg.acctMthCtrlCd.setValue(ACCT_MTH_CTRL.AR_MONTHLY_JOB);
//        ACCT_MTH_CTRLTMsg outTMsg = (ACCT_MTH_CTRLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inTMsg);
//        if (outTMsg == null) {
//            return false;
//        }
//        this.acctYrMth = outTMsg.acctYrMth.getValue();
//        return true;
//
//    }
// END 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
// END 2016/04/11 S.Fujita [S21 NA Unit Test QC#6722,MOD]

    /**
     */
    private void execute() {
        int targetCnt = this.getTargetCnt();

        int startNum = 0;
        int endNum = 0;

        while (endNum != targetCnt) {
            startNum = endNum + 1;
            if ((endNum + NUM_OF_SEPARATE) > targetCnt) {
                endNum = targetCnt;
            } else {
                endNum = endNum + NUM_OF_SEPARATE;
            }
            this.executeSeparate(startNum, endNum);
        }
    }

    private int getTargetCnt() {
        PreparedStatement stmtSelect = null;
        ResultSet rs = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put(NFCDbConst.GLBL_CMPY_CD, this.glblCmpyCd);
            queryParam.put(NFCDbConst.ACCT_YR_MTH, this.acctYrMth);
            queryParam.put(CST_GL_DT_FROM, this.acctStartDate);
            queryParam.put(CST_GL_DT_THRU, this.acctEndDate);
            queryParam.put(AR_CASH_APPLY_STS_CD_U, NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY);
            queryParam.put(AR_CASH_APPLY_STS_CD_P, NFCConst.CST_AR_CASH_APPLY_STS_CD_PARTIAL);

            stmtSelect = this.ssmLLClient.createPreparedStatement("getArTrxBalTargetCnt", queryParam, execParam);
            rs = stmtSelect.executeQuery();

            if (rs.next()) {
                int cnt = rs.getBigDecimal("COUNT").intValue();
                return cnt;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
        return 0;
    }

    private void executeSeparate(int startNum, int endNum) {
        PreparedStatement stmtSelect = null;
        ResultSet rs = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put(NFCDbConst.GLBL_CMPY_CD, this.glblCmpyCd);
            queryParam.put(NFCDbConst.ACCT_YR_MTH, this.acctYrMth);
            queryParam.put(CST_GL_DT_FROM, this.acctStartDate);
            queryParam.put(CST_GL_DT_THRU, this.acctEndDate);
            queryParam.put(AR_CASH_APPLY_STS_CD_U, NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY);
            queryParam.put(AR_CASH_APPLY_STS_CD_P, NFCConst.CST_AR_CASH_APPLY_STS_CD_PARTIAL);
            queryParam.put("START_NUM", String.valueOf(startNum));
            queryParam.put("END_NUM", String.valueOf(endNum));

            stmtSelect = this.ssmLLClient.createPreparedStatement("getArTrxBalSeparate", queryParam, execParam);
            rs = stmtSelect.executeQuery();

            List<AR_TRX_BAL_UPD_WRKTMsg> arTrxBalUpdWrkList = new ArrayList<AR_TRX_BAL_UPD_WRKTMsg>();
            // START 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
            List<AR_TRX_BAL_MLYTMsg> arTrxBalMlyList = new ArrayList<AR_TRX_BAL_MLYTMsg>();
            // END 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]

            while (rs.next()) {

                this.procCount++;

                AR_TRX_BAL_UPD_WRKTMsg arTrxBalUpdWrkTMsg = new AR_TRX_BAL_UPD_WRKTMsg();

                setArTrxBalUpdWrkTMsg(rs, arTrxBalUpdWrkTMsg);

                BigDecimal bigZero = BigDecimal.ZERO;
                if (bigZero.compareTo(rs.getBigDecimal(NFCDbConst.DEAL_APPLY_ADJ_RSVD_AMT)) != 0) {
                    getArAdj(arTrxBalUpdWrkTMsg);
                }
                if (rs.getString(NFCDbConst.CASH_APP_DT).compareTo(this.acctStartDate) >= 0) {
                    if (NFCConst.CST_AR_TRX_TP_CD_RECEIPT.equals(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_TRX_TP_CD)))) {
                        getArCashAppByPayment(rs, arTrxBalUpdWrkTMsg);
                    } else {
                        if (NFCConst.CST_AR_TRX_TP_CD_DEDUCTION.equals(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_TRX_TP_CD)))
                                || NFCConst.CST_AR_TRX_TP_CD_ACCOUNT.equals(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_TRX_TP_CD)))) {
                            getArCashAppByDeduction(rs, arTrxBalUpdWrkTMsg);
                        }
                        getArCashAppByOthers(rs, arTrxBalUpdWrkTMsg);
                    }
                    if (NFCConst.CST_AR_TRX_TP_CD_CREDITMEMO.equals(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_TRX_TP_CD)))) {
                        getArCashAppByCreditMemo(rs, arTrxBalUpdWrkTMsg);
                    }
                }
                setRecalculationOfBalance(arTrxBalUpdWrkTMsg);

                // START 2016/04/14 S.Fujita [S21 NA Unit Test QC#6909,MOD]
                // if (!NFCConst.CST_AR_TRX_TP_CD_RECEIPT.equals(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_TRX_TP_CD)))) {
                setStatus(arTrxBalUpdWrkTMsg);
                // }
                // END 2016/04/14 S.Fujita [S21 NA Unit Test QC#6909,MOD]

                arTrxBalUpdWrkList.add(arTrxBalUpdWrkTMsg);
                // START 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
                AR_TRX_BAL_MLYTMsg arTrxBalMlyTMsg = new AR_TRX_BAL_MLYTMsg();
                EZDMsg.copy(arTrxBalUpdWrkTMsg, null, arTrxBalMlyTMsg, null);
                setValue(arTrxBalMlyTMsg.acctYrMth, this.acctYrMth);
                arTrxBalMlyList.add(arTrxBalMlyTMsg);
                // END 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]

                if (this.commitCount++ >= this.commitTranCnt) {
                    // START 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
                    //this.insertAll(arTrxBalUpdWrkList, TBL_NAME_AR_ACCT_DT);
                    this.insertAll(arTrxBalUpdWrkList, TBL_NAME_AR_TRX_BAL_UPD_WRK);
                    this.insertAll(arTrxBalMlyList, TBL_NAME_AR_TRX_BAL_MLY);
                    // END 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
                    commit();
                    this.commitCount = 0;
                }
            }
            if (this.commitCount > 0) {
                // START 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
                //this.insertAll(arTrxBalUpdWrkList, TBL_NAME_AR_ACCT_DT);
                this.insertAll(arTrxBalUpdWrkList, TBL_NAME_AR_TRX_BAL_UPD_WRK);
                this.insertAll(arTrxBalMlyList, TBL_NAME_AR_TRX_BAL_MLY);
                // END 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
                commit();
                this.commitCount = 0;
            }
            // START 2016/04/11 S.Fujita [S21 NA Unit Test QC#6722,MOD]
            // START 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
//            updateAcctMthCtrl();
//            commit();
            // END 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
            // END 2016/04/11 S.Fujita [S21 NA Unit Test QC#6722,MOD]
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * @param rs ResultSet
     * @param tMsg AR_TRX_BAL_UPD_WRKTMsg
     * @throws SQLException
     */
    private void setArTrxBalUpdWrkTMsg(final ResultSet rs, AR_TRX_BAL_UPD_WRKTMsg tMsg) throws SQLException {

        /* EZCANCELFLAG */
        tMsg.ezCancelFlag.setValue(CST_EZ_CANCEL_FLAG);
        /* EZINCOMPANYCODE */
        tMsg.ezInCompanyCode.setValue(CST_EZ_IN_COMPANY_CODE);
        /* EZUPAPLID */
        tMsg.ezUpAplID.setValue(CST_EZ_UPDATE_APPLICATION_ID);
        /* GLBL_CMPY_CD */
        tMsg.glblCmpyCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.GLBL_CMPY_CD)));
        /* AR_TRX_BAL_PK */
        tMsg.arTrxBalPk.setValue(rs.getBigDecimal(NFCDbConst.AR_TRX_BAL_PK));
        /* AR_TRX_NUM */
        tMsg.arTrxNum.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_TRX_NUM)));
        /* AR_TRX_TP_CD */
        tMsg.arTrxTpCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_TRX_TP_CD)));
        /* ATT_ADJ_NUM */
        tMsg.attAdjNum.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.ATT_ADJ_NUM)));
        /* DEAL_CCY_CD */
        tMsg.dealCcyCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.DEAL_CCY_CD)));
        /* DEAL_ORIG_GRS_AMT */
        tMsg.dealOrigGrsAmt.setValue(rs.getBigDecimal(NFCDbConst.DEAL_ORIG_GRS_AMT));

        /* DEAL_APPLY_GRS_AMT */
        tMsg.dealApplyGrsAmt.setValue(rs.getBigDecimal(NFCDbConst.DEAL_APPLY_GRS_AMT));
        /* DEAL_APPLY_CASH_DISK_AMT */
        tMsg.dealApplyCashDiscAmt.setValue(rs.getBigDecimal(NFCDbConst.DEAL_APPLY_CASH_DISC_AMT));
        /* DEAL_APPLY_CR_AMT */
        tMsg.dealApplyCrAmt.setValue(rs.getBigDecimal(NFCDbConst.DEAL_APPLY_CR_AMT));
        /* DEAL_APPLY_ADJ_AMT */
        tMsg.dealApplyAdjAmt.setValue(rs.getBigDecimal(NFCDbConst.DEAL_APPLY_ADJ_AMT));
        /* DEAL_APPLY_ADJ_RSVD_AMT */
        tMsg.dealApplyAdjRsvdAmt.setValue(rs.getBigDecimal(NFCDbConst.DEAL_APPLY_ADJ_RSVD_AMT));
        /* DEAL_APPLY_ADJ_RSVD_AMT */
        tMsg.dealRmngBalGrsAmt.setValue(rs.getBigDecimal(NFCDbConst.DEAL_RMNG_BAL_GRS_AMT));

        /* DEAL_NET_SLS_AMT */
        tMsg.dealNetSlsAmt.setValue(rs.getBigDecimal(NFCDbConst.DEAL_NET_SLS_AMT));
        /* DEAL_FRT_AMT */
        tMsg.dealFrtAmt.setValue(rs.getBigDecimal(NFCDbConst.DEAL_FRT_AMT));
        /* DEAL_TAX_AMT */
        tMsg.dealTaxAmt.setValue(rs.getBigDecimal(NFCDbConst.DEAL_TAX_AMT));
        /* DEAL_INV_DISC_AMT */
        tMsg.dealInvDiscAmt.setValue(rs.getBigDecimal(NFCDbConst.DEAL_INV_DISC_AMT));
        /* DEAL_PRMO_DISC_AMT */
        tMsg.dealPrmoDiscAmt.setValue(rs.getBigDecimal(NFCDbConst.DEAL_PRMO_DISC_AMT));
        /* DEAL_RCPT_VOID_AMT */
        tMsg.dealRcptVoidAmt.setValue(rs.getBigDecimal(NFCDbConst.DEAL_RCPT_VOID_AMT));
        /* EXCH_RATE */
        tMsg.exchRate.setValue(rs.getBigDecimal(NFCDbConst.EXCH_RATE));
        /* FUNC_CCY_CD */
        tMsg.funcCcyCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.FUNC_CCY_CD)));
        /* FUNC_ORIG_GRS_AMT */
        tMsg.funcOrigGrsAmt.setValue(rs.getBigDecimal(NFCDbConst.FUNC_ORIG_GRS_AMT));

        /* FUNC_APPLY_GRS_AMT */
        tMsg.funcApplyGrsAmt.setValue(rs.getBigDecimal(NFCDbConst.FUNC_APPLY_GRS_AMT));
        /* FUNC_APPLY_CASH_DISK_AMT */
        tMsg.funcApplyCashDiscAmt.setValue(rs.getBigDecimal(NFCDbConst.FUNC_APPLY_CASH_DISC_AMT));
        /* FUNC_APPLY_CR_AMT */
        tMsg.funcApplyCrAmt.setValue(rs.getBigDecimal(NFCDbConst.FUNC_APPLY_CR_AMT));
        /* FUNC_APPLY_ADJ_AMT */
        tMsg.funcApplyAdjAmt.setValue(rs.getBigDecimal(NFCDbConst.FUNC_APPLY_ADJ_AMT));
        /* FUNC_APPLY_ADJ_RSVD_AMT */
        tMsg.funcApplyAdjRsvdAmt.setValue(rs.getBigDecimal(NFCDbConst.FUNC_APPLY_ADJ_RSVD_AMT));
        /* FUNC_APPLY_ADJ_RSVD_AMT */
        tMsg.funcRmngBalGrsAmt.setValue(rs.getBigDecimal(NFCDbConst.FUNC_RMNG_BAL_GRS_AMT));

        /* FUNC_RVAL_VAR_AMT */
        tMsg.funcRvalVarAmt.setValue(rs.getBigDecimal(NFCDbConst.FUNC_RVAL_VAR_AMT));
        /* FUNC_NET_SLS_AMT */
        tMsg.funcNetSlsAmt.setValue(rs.getBigDecimal(NFCDbConst.FUNC_NET_SLS_AMT));
        /* FUNC_FRT_AMT */
        tMsg.funcFrtAmt.setValue(rs.getBigDecimal(NFCDbConst.FUNC_FRT_AMT));
        /* FUNC_TAX_AMT */
        tMsg.funcTaxAmt.setValue(rs.getBigDecimal(NFCDbConst.FUNC_TAX_AMT));
        /* FUNC_INV_DISC_AMT */
        tMsg.funcInvDiscAmt.setValue(rs.getBigDecimal(NFCDbConst.FUNC_INV_DISC_AMT));
        /* FUNC_PRMO_DISC_AMT */
        tMsg.funcPrmoDiscAmt.setValue(rs.getBigDecimal(NFCDbConst.FUNC_PRMO_DISC_AMT));
        /* FUNC_RCPT_VOID_AMT */
        tMsg.funcRcptVoidAmt.setValue(rs.getBigDecimal(NFCDbConst.FUNC_RCPT_VOID_AMT));
        /* PMT_TERM_CASH_DISC_CD */
        tMsg.pmtTermCashDiscCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.PMT_TERM_CASH_DISC_CD)));
        /* PMT_TERM_CD */
        tMsg.pmtTermCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.PMT_TERM_CD)));
        /* CASH_DISC_TERM_CD */
        tMsg.cashDiscTermCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.CASH_DISC_TERM_CD)));
        /* CASH_DISC_PCT */
        tMsg.cashDiscPct.setValue(rs.getBigDecimal(NFCDbConst.CASH_DISC_PCT));
        /* INV_PMT_METH_CD */
        tMsg.invPmtMethCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.INV_PMT_METH_CD)));
        /* INV_PMT_COND_CD */
        tMsg.invPmtCondCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.INV_PMT_COND_CD)));
        /* AR_CASH_APPLY_STS_CD */
        tMsg.arCashApplyStsCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_CASH_APPLY_STS_CD)));
        /* AR_TRX_DT */
        tMsg.arTrxDt.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_TRX_DT)));
        /* INV_DUE_DT */
        tMsg.invDueDt.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.INV_DUE_DT)));
        /* GL_DT */
        tMsg.glDt.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.GL_DT)));
        /* CASH_APP_DT */
        tMsg.cashAppDt.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.CASH_APP_DT)));
        /* BILL_TO_CUST_CD */
        tMsg.billToCustCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.BILL_TO_CUST_CD)));
        /* SELL_TO_CUST_CD */
        tMsg.sellToCustCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.SELL_TO_CUST_CD)));
        /* PAYER_CUST_CD */
        tMsg.payerCustCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.PAYER_CUST_CD)));
        /* TOC_CD */
        tMsg.tocCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.TOC_CD)));
        /* COA_PROD_CD */
        tMsg.coaProdCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.COA_PROD_CD)));
        /* GRP_INV_NUM */
        tMsg.grpInvNum.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.GRP_INV_NUM)));
        /* CPO_ORD_NUM */
        tMsg.cpoOrdNum.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.CPO_ORD_NUM)));
        /* CUST_ISS_PO_NUM */
        tMsg.custIssPoNum.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.CUST_ISS_PO_NUM)));
        /* AR_CUST_REF_NUM */
        tMsg.arCustRefNum.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_CUST_REF_NUM)));
        /* EXPT_FLG */
        tMsg.exptFlg.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.EXPT_FLG)));

        // START 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
        /* UPPER_CUST_ISS_PO_NUM */
        tMsg.upperCustIssPoNum.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.UPPER_CUST_ISS_PO_NUM)));
        /* BILL_TO_CUST_ACCT_CD */
        tMsg.billToCustAcctCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.BILL_TO_CUST_ACCT_CD)));
        /* SRC_SYS_DOC_NUM */
        tMsg.srcSysDocNum.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.SRC_SYS_DOC_NUM)));
        /* SYS_SRC_CD */
        tMsg.sysSrcCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.SYS_SRC_CD)));
        /* ORIG_INV_NUM */
        tMsg.origInvNum.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.ORIG_INV_NUM)));
        /* SVC_INV_PK */
        tMsg.svcInvPk.setValue(rs.getBigDecimal(NFCDbConst.SVC_INV_PK));
        /* DS_INV_TP_CD */
        tMsg.dsInvTpCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.DS_INV_TP_CD)));
        /* SLS_REP_TOC_CD */
        tMsg.slsRepTocCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.SLS_REP_TOC_CD)));
        /* CUST_CARE_TKT_NUM */
        tMsg.custCareTktNum.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.CUST_CARE_TKT_NUM)));
        /* AR_AUTO_PURGE_OFS_FLG */
        tMsg.arAutoPurgeOfsFlg.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_AUTO_PURGE_OFS_FLG)));
        /* CR_REBIL_RSN_CATG_CD */
        tMsg.crRebilRsnCatgCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.CR_REBIL_RSN_CATG_CD)));
        /* AR_TRX_BILL_FROM_DT */
        tMsg.arTrxBillFromDt.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_TRX_BILL_FROM_DT)));
        /* AR_TRX_BILL_THRU_DT */
        tMsg.arTrxBillThruDt.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_TRX_BILL_THRU_DT)));
        // END 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]

    }

    /**
     * @param tMsg AR_TRX_BAL_UPD_WRKTMsg
     */
    private void getArAdj(AR_TRX_BAL_UPD_WRKTMsg tMsg) {

        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put(NFCDbConst.GLBL_CMPY_CD, NFCCmnMethod.convertDbString(tMsg.glblCmpyCd.getValue()));
        sqlParam.put(NFCDbConst.ORIG_AR_TRX_BAL_PK, tMsg.arTrxBalPk.getValue());
        sqlParam.put(CST_GL_DT_THRU, this.acctEndDate);
        sqlParam.put(NFCDbConst.AR_ADJ_STS_CD, NFCConst.CST_ADJ_STS_CD_SUBMITTED);

        AR_ADJTMsg arAdjTMsg = new AR_ADJTMsg();

        S21SsmEZDResult result = NFCB127001Query.getInstance().getArAdj(sqlParam, arAdjTMsg);

        if ((NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(result.getResultCode())) && (arAdjTMsg.dealArAdjAmt.getValue() != null) && (arAdjTMsg.funcArAdjAmt.getValue() != null)) {
            this.wDealApplyAdjRsvdAmt = tMsg.dealApplyAdjRsvdAmt.getValue().subtract(arAdjTMsg.dealArAdjAmt.getValue());
            tMsg.dealApplyAdjRsvdAmt.setValue(this.wDealApplyAdjRsvdAmt);
            this.wFuncApplyAdjRsvdAmt = tMsg.funcApplyAdjRsvdAmt.getValue().subtract(arAdjTMsg.funcArAdjAmt.getValue());
            tMsg.funcApplyAdjRsvdAmt.setValue(this.wFuncApplyAdjRsvdAmt);
            this.recalculationFlg = NFCConst.CST_FLAG_ON;
        }

    }

    /**
     * @param paramRs ResultSet
     * @param tMsg AR_TRX_BAL_UPD_WRKTMsg
     * @throws SQLException
     */
    private void getArCashAppByPayment(final ResultSet paramRs, AR_TRX_BAL_UPD_WRKTMsg tMsg) throws SQLException {

        BigDecimal bigZero = BigDecimal.ZERO;
        PreparedStatement stmtSelect = null;
        ResultSet rs = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            Map<String, String> queryParam = new HashMap<String, String>();
            if (bigZero.compareTo(paramRs.getBigDecimal(NFCDbConst.DEAL_RCPT_VOID_AMT)) != 0) {
                queryParam.put(NFCDbConst.GLBL_CMPY_CD, NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.GLBL_CMPY_CD)));
                queryParam.put(CST_GL_DT_THRU, this.acctEndDate);
                queryParam.put(NFCDbConst.AR_TRX_NUM, NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.AR_TRX_NUM)));

                stmtSelect = this.ssmLLClient.createPreparedStatement("getArRcpt", queryParam, execParam);
                rs = stmtSelect.executeQuery();

                if (rs.first()) {
                    if (rs.getInt(CST_COUNT_NUM) != 0) {
                        // Next mounth
                        if (paramRs.getString(NFCDbConst.CASH_APP_DT).compareTo(this.acctEndDate) > 0) {
                            tMsg.dealRcptVoidAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                            tMsg.funcRcptVoidAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                            tMsg.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY);
                            tMsg.dealRmngBalGrsAmt.setValue(paramRs.getBigDecimal(NFCDbConst.DEAL_ORIG_GRS_AMT));
                            tMsg.funcRmngBalGrsAmt.setValue(paramRs.getBigDecimal(NFCDbConst.FUNC_ORIG_GRS_AMT).subtract(paramRs.getBigDecimal(NFCDbConst.FUNC_RVAL_VAR_AMT)));
                        } else {
                            tMsg.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_VOID);
                        }
                    }
                }
                S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            }

            queryParam.put(NFCDbConst.GLBL_CMPY_CD, NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.GLBL_CMPY_CD)));
            queryParam.put(NFCDbConst.AR_TRX_NUM, NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.AR_TRX_NUM)));
            queryParam.put(NFCDbConst.ACCT_YR_MTH, this.acctYrMth);
            queryParam.put(CST_GL_DT_THRU, this.acctEndDate);
            queryParam.put(AR_APPLY_TP_CD_CSH, NFCConst.CST_AR_APPLY_TP_CD_CASH);
            queryParam.put(AR_APPLY_TP_CD_RFD, NFCConst.CST_AR_APPLY_TP_CD_REFUND);
            queryParam.put(AR_APPLY_TP_CD_ADJ, NFCConst.CST_AR_APPLY_TP_CD_ADJUSTMENT);

            stmtSelect = this.ssmLLClient.createPreparedStatement("getArCashAppByPayment", queryParam, execParam);
            rs = stmtSelect.executeQuery();

            while (rs.next()) {
                if (NFCConst.CST_AR_APPLY_TP_CD_CASH.equals(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_APPLY_TP_CD)))) {
                    this.wDealApplyGrsAmt = tMsg.dealApplyGrsAmt.getValue().subtract(rs.getBigDecimal(NFCDbConst.DEAL_APPLY_AMT));
                    tMsg.dealApplyGrsAmt.setValue(this.wDealApplyGrsAmt);
                    this.wFuncApplyGrsAmt = tMsg.funcApplyGrsAmt.getValue().subtract(rs.getBigDecimal(NFCDbConst.FUNC_APPLY_AMT));
                    tMsg.funcApplyGrsAmt.setValue(this.wFuncApplyGrsAmt);
                    this.recalculationFlg = NFCConst.CST_FLAG_ON;
                } else if ((NFCConst.CST_AR_APPLY_TP_CD_ADJUSTMENT.equals(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_APPLY_TP_CD))))) {
                    if (NFCConst.CST_AR_ADJ_TP_CD_BANK_COMMISSION_FEE.equals(rs.getString(NFCDbConst.AR_ADJ_TP_CD))) {
                        if (!NFCConst.CST_AR_ADJ_TRX_TP_CD_DEDUCTION.equals(rs.getString(NFCDbConst.AR_ADJ_TRX_TP_CD)) && !NFCConst.CST_AR_ADJ_TRX_TP_CD_ACCOUNT.equals(rs.getString(NFCDbConst.AR_ADJ_TRX_TP_CD))) {
                            this.wDealApplyAdjAmt = tMsg.dealApplyAdjAmt.getValue().subtract(rs.getBigDecimal(NFCDbConst.DEAL_APPLY_AMT));
                            tMsg.dealApplyAdjAmt.setValue(this.wDealApplyAdjAmt);
                            this.wFuncApplyAdjAmt = tMsg.funcApplyAdjAmt.getValue().subtract(rs.getBigDecimal(NFCDbConst.FUNC_APPLY_AMT));
                            tMsg.funcApplyAdjAmt.setValue(this.wFuncApplyAdjAmt);
                            this.recalculationFlg = NFCConst.CST_FLAG_ON;
                        }
                    } else {
                        this.wDealApplyAdjAmt = tMsg.dealApplyAdjAmt.getValue().subtract(rs.getBigDecimal(NFCDbConst.DEAL_APPLY_AMT));
                        tMsg.dealApplyAdjAmt.setValue(this.wDealApplyAdjAmt);
                        this.wFuncApplyAdjAmt = tMsg.funcApplyAdjAmt.getValue().subtract(rs.getBigDecimal(NFCDbConst.FUNC_APPLY_AMT));
                        tMsg.funcApplyAdjAmt.setValue(this.wFuncApplyAdjAmt);
                        this.recalculationFlg = NFCConst.CST_FLAG_ON;
                    }
                } else if (NFCConst.CST_AR_APPLY_TP_CD_REFUND.equals(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_APPLY_TP_CD)))) {
                    this.wDealApplyAdjAmt = tMsg.dealApplyAdjAmt.getValue().subtract(rs.getBigDecimal(NFCDbConst.DEAL_APPLY_AMT));
                    tMsg.dealApplyAdjAmt.setValue(this.wDealApplyAdjAmt);
                    this.wFuncApplyAdjAmt = tMsg.funcApplyAdjAmt.getValue().subtract(rs.getBigDecimal(NFCDbConst.FUNC_APPLY_AMT));
                    tMsg.funcApplyAdjAmt.setValue(this.wFuncApplyAdjAmt);
                    this.recalculationFlg = NFCConst.CST_FLAG_ON;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

    }

    /**
     * @param paramRs ResultSet
     * @param tMsg AR_TRX_BAL_UPD_WRKTMsg
     * @throws SQLException
     */
    private void getArCashAppByCreditMemo(final ResultSet paramRs, AR_TRX_BAL_UPD_WRKTMsg tMsg) throws SQLException {

        PreparedStatement stmtSelect = null;
        ResultSet rs = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put(NFCDbConst.GLBL_CMPY_CD, NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.GLBL_CMPY_CD)));
            queryParam.put(NFCDbConst.AR_CR_TRX_NUM, NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.AR_TRX_NUM)));
            queryParam.put(NFCDbConst.ACCT_YR_MTH, this.acctYrMth);
            queryParam.put(CST_GL_DT_THRU, this.acctEndDate);
            queryParam.put(AR_APPLY_TP_CD_CRM, NFCConst.CST_AR_APPLY_TP_CD_CREDITMEMO);
            stmtSelect = this.ssmLLClient.createPreparedStatement("getArCashAppByCreditMemo", queryParam, execParam);
            rs = stmtSelect.executeQuery();

            while (rs.next()) {
                if (NFCConst.CST_AR_APPLY_TP_CD_CREDITMEMO.equals(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_APPLY_TP_CD)))) {
                    this.wDealApplyCrAmt = tMsg.dealApplyCrAmt.getValue().subtract(rs.getBigDecimal(NFCDbConst.DEAL_APPLY_AMT));
                    tMsg.dealApplyCrAmt.setValue(this.wDealApplyCrAmt);
                    this.wFuncApplyCrAmt = tMsg.funcApplyCrAmt.getValue().subtract(rs.getBigDecimal(NFCDbConst.FUNC_APPLY_AMT));
                    tMsg.funcApplyCrAmt.setValue(this.wFuncApplyCrAmt);
                    this.recalculationFlg = NFCConst.CST_FLAG_ON;
                }

            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * @param paramRs ResultSet
     * @param tMsg AR_TRX_BAL_UPD_WRKTMsg
     * @throws SQLException
     */
    private void getArCashAppByDeduction(final ResultSet paramRs, AR_TRX_BAL_UPD_WRKTMsg tMsg) throws SQLException {

        PreparedStatement stmtSelect = null;
        ResultSet rs = null;
        try {

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(NFCDbConst.GLBL_CMPY_CD, NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.GLBL_CMPY_CD)));
            queryParam.put(NFCDbConst.ACCT_YR_MTH, this.acctYrMth);
            queryParam.put(CST_GL_DT_THRU, this.acctEndDate);
            queryParam.put(NFCDbConst.SET_AR_ADJ_NUM, NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.ATT_ADJ_NUM)));
            queryParam.put(AR_APPLY_TP_CD_ADJ, NFCConst.CST_AR_APPLY_TP_CD_ADJUSTMENT);

            stmtSelect = this.ssmLLClient.createPreparedStatement("getArCashAppByDeduction", queryParam, execParam);
            rs = stmtSelect.executeQuery();
            while (rs.next()) {
                if (NFCConst.CST_AR_APPLY_TP_CD_ADJUSTMENT.equals(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_APPLY_TP_CD)))) {
                    this.wDealApplyAdjAmt = tMsg.dealApplyAdjAmt.getValue().add(rs.getBigDecimal(NFCDbConst.DEAL_APPLY_AMT));
                    tMsg.dealApplyAdjAmt.setValue(this.wDealApplyAdjAmt);
                    this.wFuncApplyAdjAmt = tMsg.funcApplyAdjAmt.getValue().add(rs.getBigDecimal(NFCDbConst.FUNC_APPLY_AMT));
                    tMsg.funcApplyAdjAmt.setValue(this.wFuncApplyAdjAmt);
                    this.recalculationFlg = NFCConst.CST_FLAG_ON;
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

    }

    /**
     * @param paramRs ResultSet
     * @param tMsg AR_TRX_BAL_UPD_WRKTMsg
     * @throws SQLException
     */
    private void getArCashAppByOthers(final ResultSet paramRs, AR_TRX_BAL_UPD_WRKTMsg tMsg) throws SQLException {

        PreparedStatement stmtSelect = null;
        ResultSet rs = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put(NFCDbConst.GLBL_CMPY_CD, NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.GLBL_CMPY_CD)));
            queryParam.put(NFCDbConst.AR_TRX_NUM, NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.AR_TRX_NUM)));
            queryParam.put(NFCDbConst.ACCT_YR_MTH, this.acctYrMth);
            queryParam.put(CST_GL_DT_THRU, this.acctEndDate);
            queryParam.put(AR_APPLY_TP_CD_CRM, NFCConst.CST_AR_APPLY_TP_CD_CREDITMEMO);
            queryParam.put(AR_APPLY_TP_CD_CSH, NFCConst.CST_AR_APPLY_TP_CD_CASH);
            queryParam.put(AR_APPLY_TP_CD_ADJ, NFCConst.CST_AR_APPLY_TP_CD_ADJUSTMENT);

            stmtSelect = this.ssmLLClient.createPreparedStatement("getArCashAppByOthers", queryParam, execParam);
            rs = stmtSelect.executeQuery();

            while (rs.next()) {
                if (NFCConst.CST_AR_APPLY_TP_CD_CASH.equals(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_APPLY_TP_CD)))) {
                    this.wDealApplyGrsAmt = tMsg.dealApplyGrsAmt.getValue().subtract(rs.getBigDecimal(NFCDbConst.DEAL_APPLY_AMT));
                    tMsg.dealApplyGrsAmt.setValue(this.wDealApplyGrsAmt);
                    this.wFuncApplyGrsAmt = tMsg.funcApplyGrsAmt.getValue().subtract(rs.getBigDecimal(NFCDbConst.FUNC_APPLY_AMT));
                    tMsg.funcApplyGrsAmt.setValue(this.wFuncApplyGrsAmt);
                    this.wDealApplyCashDiscAmt = tMsg.dealApplyCashDiscAmt.getValue().subtract(rs.getBigDecimal(NFCDbConst.DEAL_APPLY_CASH_DISC_AMT));
                    tMsg.dealApplyCashDiscAmt.setValue(this.wDealApplyCashDiscAmt);
                    this.wFuncApplyCashDiscAmt = tMsg.funcApplyCashDiscAmt.getValue().subtract(rs.getBigDecimal(NFCDbConst.FUNC_APPLY_CASH_DISC_AMT));
                    tMsg.funcApplyCashDiscAmt.setValue(this.wFuncApplyCashDiscAmt);
                    this.recalculationFlg = NFCConst.CST_FLAG_ON;
                } else if (NFCConst.CST_AR_APPLY_TP_CD_ADJUSTMENT.equals(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_APPLY_TP_CD)))) {
                    this.wDealApplyAdjAmt = tMsg.dealApplyAdjAmt.getValue().subtract(rs.getBigDecimal(NFCDbConst.DEAL_APPLY_AMT));
                    tMsg.dealApplyAdjAmt.setValue(this.wDealApplyAdjAmt);
                    this.wFuncApplyAdjAmt = tMsg.funcApplyAdjAmt.getValue().subtract(rs.getBigDecimal(NFCDbConst.FUNC_APPLY_AMT));
                    tMsg.funcApplyAdjAmt.setValue(this.wFuncApplyAdjAmt);
                    this.recalculationFlg = NFCConst.CST_FLAG_ON;
                } else if (NFCConst.CST_AR_APPLY_TP_CD_CREDITMEMO.equals(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_APPLY_TP_CD)))) {
                    this.wDealApplyCrAmt = tMsg.dealApplyCrAmt.getValue().add(rs.getBigDecimal(NFCDbConst.DEAL_APPLY_AMT));
                    tMsg.dealApplyCrAmt.setValue(this.wDealApplyCrAmt);
                    this.wFuncApplyCrAmt = tMsg.funcApplyCrAmt.getValue().add(rs.getBigDecimal(NFCDbConst.FUNC_APPLY_AMT));
                    tMsg.funcApplyCrAmt.setValue(this.wFuncApplyCrAmt);
                    this.recalculationFlg = NFCConst.CST_FLAG_ON;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

    }

    /**
     * @param tMsg AR_TRX_BAL_UPD_WRKTMsg
     */
    private void setRecalculationOfBalance(AR_TRX_BAL_UPD_WRKTMsg tMsg) {

        if (NFCConst.CST_FLAG_ON.equals(this.recalculationFlg)) {
            this.wDealRmngBalGrsAmt = tMsg.dealOrigGrsAmt.getValue();
            this.wDealRmngBalGrsAmt = this.wDealRmngBalGrsAmt.subtract(tMsg.dealApplyGrsAmt.getValue());
            this.wDealRmngBalGrsAmt = this.wDealRmngBalGrsAmt.subtract(tMsg.dealApplyCashDiscAmt.getValue());
            this.wDealRmngBalGrsAmt = this.wDealRmngBalGrsAmt.subtract(tMsg.dealApplyCrAmt.getValue());
            this.wDealRmngBalGrsAmt = this.wDealRmngBalGrsAmt.subtract(tMsg.dealApplyAdjAmt.getValue());
            this.wDealRmngBalGrsAmt = this.wDealRmngBalGrsAmt.subtract(tMsg.dealRcptVoidAmt.getValue());

            this.wFuncRmngBalGrsAmt = tMsg.funcOrigGrsAmt.getValue();
            this.wFuncRmngBalGrsAmt = this.wFuncRmngBalGrsAmt.subtract(tMsg.funcApplyGrsAmt.getValue());
            this.wFuncRmngBalGrsAmt = this.wFuncRmngBalGrsAmt.subtract(tMsg.funcApplyCashDiscAmt.getValue());
            this.wFuncRmngBalGrsAmt = this.wFuncRmngBalGrsAmt.subtract(tMsg.funcApplyCrAmt.getValue());
            this.wFuncRmngBalGrsAmt = this.wFuncRmngBalGrsAmt.subtract(tMsg.funcApplyAdjAmt.getValue());
            this.wFuncRmngBalGrsAmt = this.wFuncRmngBalGrsAmt.subtract(tMsg.funcRvalVarAmt.getValue());
            this.wFuncRmngBalGrsAmt = this.wFuncRmngBalGrsAmt.subtract(tMsg.funcRcptVoidAmt.getValue());

            tMsg.dealRmngBalGrsAmt.setValue(this.wDealRmngBalGrsAmt);
            tMsg.funcRmngBalGrsAmt.setValue(this.wFuncRmngBalGrsAmt);
            this.recalculationFlg = NFCConst.CST_FLAG_OFF;
        }

    }

    /**
     * @param tMsg AR_TRX_BAL_UPD_WRKTMsg
     * @throws SQLException
     */
    private void setStatus(AR_TRX_BAL_UPD_WRKTMsg tMsg) throws SQLException {

        BigDecimal zeroDecimal = BigDecimal.ZERO;

        if (tMsg.dealOrigGrsAmt.getValue().compareTo(tMsg.dealRmngBalGrsAmt.getValue()) == 0) {
            tMsg.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY);
        } else {
            if (tMsg.dealRmngBalGrsAmt.getValue().compareTo(zeroDecimal) == 0) {
                tMsg.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_APPLY);
            } else {
                tMsg.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_PARTIAL);
            }
        }

        if (tMsg.dealRcptVoidAmt.getValue().compareTo(zeroDecimal) != 0) {
            tMsg.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_VOID);
        }

    }

    /**
     * Batch Insert using S21FastTBLAccessor with direct load insert.
     * @param <T> EZDTMsg
     * @param insertList List contains TMsg
     * @param tableName Table name
     * @return Number of inserted records
     */
    private <T extends EZDTMsg> int insertAll(final List<T> insertList, final String[] tableName) {

        if (insertList.isEmpty()) {
            return 0;
        }

        int recCnt = S21FastTBLAccessor.insert(insertList.toArray(new EZDTMsg[insertList.size()]), true);
        if (recCnt != insertList.size()) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0532E, tableName);
        }

        this.normalCnt += recCnt;
        insertList.clear();

        return recCnt;
    }

// START 2016/04/11 S.Fujita [S21 NA Unit Test QC#6722,MOD]
// START 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
//    /**
//     * Get Account Month Control
//     * @return ACCT_MTH_CTRLTMsg
//     */
//    private void updateAcctMthCtrl() {
//
//        ACCT_MTH_CTRLTMsg tMsg = new ACCT_MTH_CTRLTMsg();
//        tMsg.glblCmpyCd.setValue(this.glblCmpyCd);
//        tMsg.acctMthCtrlCd.setValue(ACCT_MTH_CTRL.AR_MONTHLY_JOB);
//        ACCT_MTH_CTRLTMsg updTMsg = (ACCT_MTH_CTRLTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
//        if (tMsg == null) {
//            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
//            throw new S21AbendException(NFCM0531E, TBL_NAME_ACCT_MTH_CTRL);
//        }
//        setValue(updTMsg.acctYrMth, this.nextYrMth);
//        setValue(updTMsg.lastRunDt, updTMsg.nextRunDt);
//        setValue(updTMsg.nextRunDt, this.afterNextYrMth + NFCConst.CST_START_DATE_OF_MONTH);
//        S21FastTBLAccessor.update(tMsg);
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
//            throw new S21AbendException(NFCM0531E, TBL_NAME_ACCT_MTH_CTRL);
//        }
//    }
// END 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
// END 2016/04/11 S.Fujita [S21 NA Unit Test QC#6722,MOD]

// START 2016/04/14 S.Fujita [S21 NA Unit Test QC#6908,MOD]
    /**
     * deleteArTrxBalMlyData
     * @param tableName Table name
     */
    private void deleteArTrxBalMlyData(final String[] tableName) {

        AR_TRX_BAL_MLYTMsg arTrxBalMlyTMsg = new AR_TRX_BAL_MLYTMsg();
        arTrxBalMlyTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        arTrxBalMlyTMsg.acctYrMth.setValue(this.acctYrMth);

        EZDTBLAccessor.removeByPartialKey(arTrxBalMlyTMsg);

        String returnCode = arTrxBalMlyTMsg.getReturnCode();
        if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(returnCode) && !NFCConst.CST_EZ_RETURN_CODE_NODATA.equals(returnCode)) {
            throw new S21AbendException(NFCM0534E, tableName);
        }
    }
// END 2016/04/14 S.Fujita [S21 NA Unit Test QC#6908,MOD]
}
