/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB068001;

import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.ACTV_CONTR_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.ACTV_CONTR_MACH_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.AGE_TO_ACTV_SHELL_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.AVG_AGE_SHELL_PEND_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.AVG_DAYS_SHELL_ACTV_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MACH_DUE_BY_EOM_AOT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTD_CONTR_LINE_CRAT_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTD_CONTR_MOD_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTD_CONTR_TRMN_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTD_CR_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTD_CR_DOL_PROC_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTD_CR_DOL_PROC_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTD_INCDT_CLO_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTD_INV_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTD_MACH_PAST_DUE_AOT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTD_MACH_READ_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTD_NET_MAN_DOL_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTD_NEW_ACTV_SHELL_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTD_NEW_CONTR_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTD_RNW_ACTV_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTD_RNW_PROC_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTD_SHELL_CLO_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTD_SHELL_CRAT_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTR_DUE_NEXT_IN_DAYS_AOT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTR_READ_CPTR_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTR_READ_PAST_DUE_AOT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.MTR_READ_PLN_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_AGG;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_CPO;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_CREDIT_MEMO;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_FLEET;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_INVOICE;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_MACB;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_MACBU;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_MACU;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_PROCESSED;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_REVENUE;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_RPT_SHELL_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_STS_ACTV;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_STS_CACL_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_STS_CANC;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_STS_DRFT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_STS_DRFT_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_STS_EFCT_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_STS_ENTE_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_STS_EXPR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_STS_IACT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_STS_NONE;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_STS_RNPO;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_STS_RNWH;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_STS_SAVE;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_STS_SBMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_STS_SIGD;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_STS_TERM_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_STS_TRMD;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PARAM_UNEARND_REVENUE;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PEND_OVER_DAYS_AMT_60;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PEND_UP_TO_DAYS_AMT_05;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PEND_UP_TO_DAYS_AMT_10;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PEND_UP_TO_DAYS_AMT_30;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PEND_UP_TO_DAYS_AMT_60;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PSN_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PSN_FIRST_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PSN_LAST_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.PSN_MID_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.RNW_HLD_CONTR_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.RNW_PEND_CONTR_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.RNW_PLAN_CONTR_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.RPT_SHELL_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.SHELL;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.SHELL_PEND_TOT_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.STS_NONE;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.SVC_CONTR_BR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.SVC_LINE_BIZ_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.SVC_RG_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.SVC_RG_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NSA.NSAB068001.constant.NSAB068001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.FCT_DLY_SHELL_PRVTYTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INV_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRMO_EFF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Contract Shell Productivity Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/14/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NSAB068001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Target Date */
    private String trgtDt = null;

    /** Commit Count */
    private int commitCount;

    /** Insert Count */
    private int normalRecCnt;

    /** Fetch Count */
    private int totalRecCnt;

    /** Error Count */
    private int errRecCnt;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /**
     * @param args parameter
     */
    public static void main(String[] args) {
        new NSAB068001().executeBatch(NSAB068001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Initialize
        this.normalRecCnt = 0;
        this.errRecCnt = 0;
        this.totalRecCnt = 0;
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Commit Count
        this.commitCount = getCommitCount();
        if (this.commitCount <= 0 || this.commitCount >= DEFAULT_COMMIT_COUNT) {
            this.commitCount = DEFAULT_COMMIT_COUNT;
        }

        // Get Target date
        this.trgtDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.trgtDt)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_BATCH_PROC_DATE });
        }

    }

    @Override
    protected void mainRoutine() {

        // Check Target Date Recored
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_TRGT_DT, this.trgtDt);
        BigDecimal recCnt = (BigDecimal) ssmBatchClient.queryObject("getTrgtDtRec", queryParam);
        if (ZYPCommonFunc.hasValue(recCnt) && (BigDecimal.ZERO.compareTo(recCnt) < 0)) {
            // Delete Target Date Recored
            FCT_DLY_SHELL_PRVTYTMsg tMsg = new FCT_DLY_SHELL_PRVTYTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg });
            }
        }

        // Get Main Data For FCT_DLY_SHELL_PRVTY Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(PARAM_TRGT_DT, this.trgtDt);
        param.put(PARAM_STS_NONE, STS_NONE);
        param.put(PARAM_STS_SAVE, DS_CONTR_DTL_STS.SAVED);
        param.put(PARAM_STS_SBMT, DS_CONTR_DTL_STS.SUBMITED);
        param.put(PARAM_STS_ACTV, DS_CONTR_DTL_STS.ACTIVE);
        param.put(PARAM_STS_IACT, DS_CONTR_DTL_STS.INACTIVE);
        param.put(PARAM_STS_SIGD, DS_CONTR_DTL_STS.SIGNED);
        param.put(PARAM_STS_TRMD, DS_CONTR_DTL_STS.TERMINATED);
        param.put(PARAM_STS_CANC, DS_CONTR_DTL_STS.CANCELLED);
        param.put(PARAM_STS_RNWH, DS_CONTR_DTL_STS.RENEWAL_HOLD);
        param.put(PARAM_STS_RNPO, DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO);
        param.put(PARAM_STS_DRFT, PRMO_EFF_STS.DRAFT);
        param.put(PARAM_STS_ENTE_CD, DS_CONTR_STS.ENTERED);
        param.put(PARAM_STS_EFCT_CD, DS_CONTR_STS.EFFECTIVE);
        param.put(PARAM_STS_EXPR_CD, DS_CONTR_STS.EXPIRED);
        param.put(PARAM_STS_CACL_CD, DS_CONTR_STS.CANCELLED);
        param.put(PARAM_STS_DRFT_CD, DS_CONTR_STS.DRAFT);
        param.put(PARAM_STS_TERM_CD, DS_CONTR_STS.TERMINATED);
        param.put(PARAM_MACB, DS_CONTR_DTL_TP.BASE_ONLY);
        param.put(PARAM_MACU, DS_CONTR_DTL_TP.USAGE_ONLY);
        param.put(PARAM_MACBU, DS_CONTR_DTL_TP.BASE_AND_USAGE);
        param.put(PARAM_FLEET, DS_CONTR_DTL_TP.FLEET);
        param.put(PARAM_AGG, DS_CONTR_DTL_TP.AGGREGATE);
        param.put(PARAM_INVOICE, INV_TP.INVOICE);
        param.put(PARAM_CREDIT_MEMO, INV_TP.CREDIT_MEMO);
        param.put(PARAM_PROCESSED, SVC_CR_REBIL_STS.PROCESSED);
        param.put(PARAM_REVENUE, AJE_INV_ACCT_CLS.REVENUE);
        param.put(PARAM_UNEARND_REVENUE, AJE_INV_ACCT_CLS.UNEARNED_REVENUE);
        param.put(PARAM_CPO, CONTR_INTFC_SRC_TP.ORDER);
        param.put(PARAM_RPT_SHELL_TP_CD, SHELL);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getMainData", param, execParam);
            rs = stmt.executeQuery();

            // Loop for Main data
            int wkInsertCount = 0;
            while (rs.next()) {
                this.totalRecCnt++;

                // Map Result Set Data To FCT_DLY_SHELL_PRVTY
                FCT_DLY_SHELL_PRVTYTMsg tMsg = mapData(rs);

                // Insert FCT_DLY_SHELL_PRVTY
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:"
                                                                                                   , SVC_RG_PK, "=", rs.getString(SVC_RG_PK)
                                                                                                   , SVC_CONTR_BR_CD, "=", rs.getString(SVC_CONTR_BR_CD)
                                                                                                   , PSN_CD, "=", rs.getString(PSN_CD));
                    S21InfoLogOutput.println(ZZZM9012E, new String[] {errMsg });
                    this.errRecCnt++;
                }

                // Commit By Commit Point
                wkInsertCount++;
                if (this.errRecCnt == 0 && wkInsertCount == this.commitCount) {
                    commit();
                    this.normalRecCnt = this.normalRecCnt + wkInsertCount;
                    wkInsertCount = 0;
                }

            } // End Loop

            // Commit By Last Data
            if (this.errRecCnt == 0 && wkInsertCount > 0) {
                this.normalRecCnt = this.normalRecCnt + wkInsertCount;
                commit();
            } else {
                rollback();
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * Map Result Set Data To FCT_DLY_SHELL_PRVTY
     */
    private FCT_DLY_SHELL_PRVTYTMsg mapData(ResultSet rs) throws SQLException {
        FCT_DLY_SHELL_PRVTYTMsg tMsg = new FCT_DLY_SHELL_PRVTYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
        BigDecimal fctDlyShellPrvtySq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FCT_DLY_SHELL_PRVTY_SQ);
        ZYPEZDItemValueSetter.setValue(tMsg.fctDlyShellPrvtyPk, fctDlyShellPrvtySq);
        ZYPEZDItemValueSetter.setValue(tMsg.svcLineBizCd, rs.getString(SVC_LINE_BIZ_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcRgPk, rs.getBigDecimal(SVC_RG_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.svcRgDescTxt, rs.getString(SVC_RG_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcContrBrCd, rs.getString(SVC_CONTR_BR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.psnCd, rs.getString(PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.psnFirstNm, rs.getString(PSN_FIRST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.psnMidNm, rs.getString(PSN_MID_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.psnLastNm, rs.getString(PSN_LAST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.mtdShellCratAmt, rs.getBigDecimal(MTD_SHELL_CRAT_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtdShellCloAmt, rs.getBigDecimal(MTD_SHELL_CLO_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.ageToActvShellAmt, rs.getBigDecimal(AGE_TO_ACTV_SHELL_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.avgAgeShellPendAmt, rs.getBigDecimal(AVG_AGE_SHELL_PEND_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.shellPendTotAmt, rs.getBigDecimal(SHELL_PEND_TOT_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.pendUpToDaysAmt_05, rs.getBigDecimal(PEND_UP_TO_DAYS_AMT_05));
        ZYPEZDItemValueSetter.setValue(tMsg.pendUpToDaysAmt_10, rs.getBigDecimal(PEND_UP_TO_DAYS_AMT_10));
        ZYPEZDItemValueSetter.setValue(tMsg.pendUpToDaysAmt_30, rs.getBigDecimal(PEND_UP_TO_DAYS_AMT_30));
        ZYPEZDItemValueSetter.setValue(tMsg.pendUpToDaysAmt_60, rs.getBigDecimal(PEND_UP_TO_DAYS_AMT_60));
        ZYPEZDItemValueSetter.setValue(tMsg.pendOverDaysAmt_60, rs.getBigDecimal(PEND_OVER_DAYS_AMT_60));
        ZYPEZDItemValueSetter.setValue(tMsg.actvContrAmt, rs.getBigDecimal(ACTV_CONTR_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.actvContrMachAmt, rs.getBigDecimal(ACTV_CONTR_MACH_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtdNewContrAmt, rs.getBigDecimal(MTD_NEW_CONTR_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtdNewActvShellAmt, rs.getBigDecimal(MTD_NEW_ACTV_SHELL_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.avgDaysShellActvAmt, rs.getBigDecimal(AVG_DAYS_SHELL_ACTV_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.rnwHldContrAmt, rs.getBigDecimal(RNW_HLD_CONTR_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.rnwPlnContrAmt, rs.getBigDecimal(RNW_PLAN_CONTR_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtdRnwProcAmt, rs.getBigDecimal(MTD_RNW_PROC_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtdRnwActvAmt, rs.getBigDecimal(MTD_RNW_ACTV_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.rnwPendContrAmt, rs.getBigDecimal(RNW_PEND_CONTR_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtrReadPlnAmt, rs.getBigDecimal(MTR_READ_PLN_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtrReadCptrAmt, rs.getBigDecimal(MTR_READ_CPTR_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtrReadPastDueAot, rs.getBigDecimal(MTR_READ_PAST_DUE_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtrDueNextInDaysAot, rs.getBigDecimal(MTR_DUE_NEXT_IN_DAYS_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.machDueByEomAot, rs.getBigDecimal(MACH_DUE_BY_EOM_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtdMachReadCnt, rs.getBigDecimal(MTD_MACH_READ_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtdMachPastDueAot, rs.getBigDecimal(MTD_MACH_PAST_DUE_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtdContrModAmt, rs.getBigDecimal(MTD_CONTR_MOD_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtdContrTrmnAmt, rs.getBigDecimal(MTD_CONTR_TRMN_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtdContrLineCratAmt, rs.getBigDecimal(MTD_CONTR_LINE_CRAT_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtdCrDolProcAmt, rs.getBigDecimal(MTD_CR_DOL_PROC_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtdCrDolProcCnt, rs.getBigDecimal(MTD_CR_DOL_PROC_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtdNetManDolAmt, rs.getBigDecimal(MTD_NET_MAN_DOL_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtdIncdtCloAmt, rs.getBigDecimal(MTD_INCDT_CLO_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtdCrCnt, rs.getBigDecimal(MTD_CR_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtdInvCnt, rs.getBigDecimal(MTD_INV_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.rptShellTpCd, rs.getString(RPT_SHELL_TP_CD));
        return tMsg;
    }

    @Override
    protected void termRoutine() {
        if (this.errRecCnt > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);
    }

}
