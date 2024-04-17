/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB501001;

import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.ALL_BAL_DEAL_AMT;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.ALL_BAL_FUNC_AMT;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.AR_RCPT_SRC_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.AR_TRX_TP_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.BILL_TO_CUST_ACCT_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.BILL_TO_CUST_ACCT_NM;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.BILL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.BILL_TO_LOC_NUM;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.CLT_PSN_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.CLT_PSN_NM;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.CLT_PTFO_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.CLT_PTFO_NM;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.CLT_PTFO_PK;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.COA_ACCT_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.COA_AFFL_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.COA_CC_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.DEAL_AGING_AMT_00;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.DEAL_AGING_AMT_01;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.DEAL_AGING_AMT_02;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.DEAL_AGING_AMT_03;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.DEAL_AGING_AMT_04;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.DEAL_AGING_AMT_05;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.DEAL_AGING_AMT_06;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.DEAL_AGING_AMT_07;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.DEAL_AGING_AMT_08;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.DEAL_AGING_AMT_09;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.DEAL_AGING_AMT_10;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.DEAL_AGING_AMT_11;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.DEAL_AGING_AMT_12;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.DEAL_AGING_AMT_13;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.DEAL_CCY_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.DS_INV_TP_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.FUNC_AGING_AMT_00;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.FUNC_AGING_AMT_01;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.FUNC_AGING_AMT_02;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.FUNC_AGING_AMT_03;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.FUNC_AGING_AMT_04;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.FUNC_AGING_AMT_05;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.FUNC_AGING_AMT_06;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.FUNC_AGING_AMT_07;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.FUNC_AGING_AMT_08;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.FUNC_AGING_AMT_09;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.FUNC_AGING_AMT_10;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.FUNC_AGING_AMT_11;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.FUNC_AGING_AMT_12;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.FUNC_AGING_AMT_13;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.FUNC_CCY_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.MSG_ITEM_BATCH_DATE;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AGE_0;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AGE_120;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AGE_150;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AGE_180;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AGE_210;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AGE_240;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AGE_270;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AGE_30;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AGE_300;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AGE_330;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AGE_365;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AGE_60;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AGE_90;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AMT_0;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AR_CASH_APPLY_STS_CD_PARTIAL;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AR_CASH_APPLY_STS_CD_UNAPPLIED;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AR_CASH_APPLY_STS_CD_UNIDENTIFIED;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AR_TRX_TP_CD_CREDIT_MEMO;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AR_TRX_TP_CD_DEBIT_MEMO;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AR_TRX_TP_CD_DEDUCTION;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AR_TRX_TP_CD_INVOICE;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AR_TRX_TP_CD_ON_ACCOUNT;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_AR_TRX_TP_CD_RECEIPT;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_COA_ACCT_CD_DEF;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_COA_AFFL_CD_DEF;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_COA_CC_CD_DEF;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_DWH_TRGT_DT;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_LINE_BIZ_TP_CD_ALL;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_RPT_COND_CONST_CD_ON_ACCOUNT;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_RPT_COND_CONST_CD_RCPT_IDENTIFIED;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_RPT_COND_CONST_CD_RECEIPT;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_RPT_COND_CONST_GRP_ID;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_RPT_COND_CONST_VAL_RS_CASH;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_RPT_COND_CONST_VAL_RS_OACC;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_RPT_COND_CONST_VAL_RS_UNAPL;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_RPT_COND_CONST_VAL_RS_UNID;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_SALES_DT;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_UNKNOWN_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_AGE_0;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_AGE_120;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_AGE_150;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_AGE_180;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_AGE_210;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_AGE_240;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_AGE_270;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_AGE_30;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_AGE_300;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_AGE_330;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_AGE_365;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_AGE_60;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_AGE_90;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_AMT_0;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_COA_ACCT_CD_DEF;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_COA_AFFL_CD_DEF;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_COA_CC_CD_DEF;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_RPT_COND_CONST_CD_ON_ACCOUNT;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_RPT_COND_CONST_CD_RCPT_IDENTIFIED;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_RPT_COND_CONST_CD_RECEIPT;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_RPT_COND_CONST_GRP_ID;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_RPT_COND_CONST_VAL_RS_CASH;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_RPT_COND_CONST_VAL_RS_OACC;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_RPT_COND_CONST_VAL_RS_UNAPL;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_RPT_COND_CONST_VAL_RS_UNID;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PARAM_VAL_UNKNOWN_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PSN_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PSN_FIRST_NM;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.PSN_LAST_NM;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.TOC_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NFC.NFCB501001.constant.NFCB501001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_AGING_BY_COATMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
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
 * Daily AR Customer Aging By COA Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/02/2016   CITS            M.Okigami       Create          N/A
 * 02/28/2017   Fujitsu         S.Fujita        Update          QC#17600
 * 06/05/2018   Hitachi         T.Tsuchida      Update          QC#26481
 *</pre>
 */
public class NFCB501001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String slsDt;

    /** Commit Count */
    private int commitCount;

    /** Insert Count */
    private int normalRecCnt;

    /** Fetch Count */
    private int totalRecCnt;

    /** Error Count */
    private int errRecCnt;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFCB501001().executeBatch(NFCB501001.class.getSimpleName());
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
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE});
        }

        // Get Commit Count
        this.commitCount = getCommitCount();
        if (this.commitCount <= 0 || this.commitCount >= DEFAULT_COMMIT_COUNT) {
            this.commitCount = DEFAULT_COMMIT_COUNT;
        }

        // Get Sales date (Batch Date)
        this.slsDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_BATCH_DATE});
        }
    }

    @Override
    protected void mainRoutine() {

        // Check Target Date Recored
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_DWH_TRGT_DT, this.slsDt);
        BigDecimal recCnt = (BigDecimal) ssmBatchClient.queryObject("getTrgtDtRec", queryParam);
        if (ZYPCommonFunc.hasValue(recCnt) && (BigDecimal.ZERO.compareTo(recCnt) < 0)) {
            // Delete Target Date Recored
            // START 2017/02/28 S.Fujita [QC#17600,MOD]
//            FCT_DLY_AR_AGING_BY_COATMsg tMsg = new FCT_DLY_AR_AGING_BY_COATMsg();
            AR_AGING_BY_COATMsg tMsg = new AR_AGING_BY_COATMsg();
            // END   2017/02/28 S.Fujita [QC#17600,MOD]
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.slsDt);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName());
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg});
            }
        }

        // Get Main Data For AR_AGING_BY_COA Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;

        queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_SALES_DT, this.slsDt);
        queryParam.put(PARAM_AR_CASH_APPLY_STS_CD_UNAPPLIED, AR_CASH_APPLY_STS.UNAPPLIED);
        queryParam.put(PARAM_AR_CASH_APPLY_STS_CD_PARTIAL, AR_CASH_APPLY_STS.PARTIAL);
        queryParam.put(PARAM_AR_CASH_APPLY_STS_CD_UNIDENTIFIED, AR_CASH_APPLY_STS.UNIDENTIFIED);
        queryParam.put(PARAM_AR_TRX_TP_CD_INVOICE, AR_TRX_TP.INVOICE);
        queryParam.put(PARAM_AR_TRX_TP_CD_DEDUCTION, AR_TRX_TP.DEDUCTION);
        queryParam.put(PARAM_AR_TRX_TP_CD_DEBIT_MEMO, AR_TRX_TP.DEBIT_MEMO);
        queryParam.put(PARAM_AR_TRX_TP_CD_CREDIT_MEMO, AR_TRX_TP.CREDIT_MEMO);
        queryParam.put(PARAM_AR_TRX_TP_CD_RECEIPT, AR_TRX_TP.RECEIPT);
        queryParam.put(PARAM_AR_TRX_TP_CD_ON_ACCOUNT, AR_TRX_TP.ON_ACCOUNT);
        queryParam.put(PARAM_UNKNOWN_CD, PARAM_VAL_UNKNOWN_CD);
        queryParam.put(PARAM_LINE_BIZ_TP_CD_ALL, LINE_BIZ_TP.ALL);
        queryParam.put(PARAM_AGE_0, PARAM_VAL_AGE_0);
        queryParam.put(PARAM_AGE_30, PARAM_VAL_AGE_30);
        queryParam.put(PARAM_AGE_60, PARAM_VAL_AGE_60);
        queryParam.put(PARAM_AGE_90, PARAM_VAL_AGE_90);
        queryParam.put(PARAM_AGE_120, PARAM_VAL_AGE_120);
        queryParam.put(PARAM_AGE_150, PARAM_VAL_AGE_150);
        queryParam.put(PARAM_AGE_180, PARAM_VAL_AGE_180);
        queryParam.put(PARAM_AGE_210, PARAM_VAL_AGE_210);
        queryParam.put(PARAM_AGE_240, PARAM_VAL_AGE_240);
        queryParam.put(PARAM_AGE_270, PARAM_VAL_AGE_270);
        queryParam.put(PARAM_AGE_300, PARAM_VAL_AGE_300);
        queryParam.put(PARAM_AGE_330, PARAM_VAL_AGE_330);
        queryParam.put(PARAM_AGE_365, PARAM_VAL_AGE_365);
        queryParam.put(PARAM_RPT_COND_CONST_VAL_RS_UNID, PARAM_VAL_RPT_COND_CONST_VAL_RS_UNID);
        queryParam.put(PARAM_RPT_COND_CONST_VAL_RS_UNAPL, PARAM_VAL_RPT_COND_CONST_VAL_RS_UNAPL);
        queryParam.put(PARAM_RPT_COND_CONST_VAL_RS_CASH, PARAM_VAL_RPT_COND_CONST_VAL_RS_CASH);
        queryParam.put(PARAM_RPT_COND_CONST_VAL_RS_OACC, PARAM_VAL_RPT_COND_CONST_VAL_RS_OACC);
        queryParam.put(PARAM_COA_ACCT_CD_DEF, PARAM_VAL_COA_ACCT_CD_DEF);
        queryParam.put(PARAM_COA_AFFL_CD_DEF, PARAM_VAL_COA_AFFL_CD_DEF);
        queryParam.put(PARAM_COA_CC_CD_DEF, PARAM_VAL_COA_CC_CD_DEF);
        queryParam.put(PARAM_RPT_COND_CONST_GRP_ID, PARAM_VAL_RPT_COND_CONST_GRP_ID);
        queryParam.put(PARAM_RPT_COND_CONST_CD_RCPT_IDENTIFIED, PARAM_VAL_RPT_COND_CONST_CD_RCPT_IDENTIFIED);
        queryParam.put(PARAM_RPT_COND_CONST_CD_RECEIPT, PARAM_VAL_RPT_COND_CONST_CD_RECEIPT);
        queryParam.put(PARAM_RPT_COND_CONST_CD_ON_ACCOUNT, PARAM_VAL_RPT_COND_CONST_CD_ON_ACCOUNT);
        queryParam.put(PARAM_AMT_0, PARAM_VAL_AMT_0);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getMainData", queryParam, execParam);
            rs = stmt.executeQuery();

            // Loop for Main data
            int wkInsertCount = 0;
            while (rs.next()) {
                this.totalRecCnt++;

                // Map Result Set Data To AR_AGING_BY_COA
                AR_AGING_BY_COATMsg tMsg = mapData(rs);

                // Insert AR_AGING_BY_COA
                EZDTBLAccessor.insert(tMsg);
                if (tMsg.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName());
                    S21InfoLogOutput.println(ZZZM9012E, new String[] {errMsg});
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

    @Override
    protected void termRoutine() {
        if (this.errRecCnt > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);
    }

    /**
     * Map Result Set Data To AR_AGING_BY_COA
     */
    // START 2017/02/28 S.Fujita [QC#17600,MOD]
//    private FCT_DLY_AR_AGING_BY_COATMsg mapData(ResultSet rs) throws SQLException {
//        FCT_DLY_AR_AGING_BY_COATMsg tMsg = new FCT_DLY_AR_AGING_BY_COATMsg();
    private AR_AGING_BY_COATMsg mapData(ResultSet rs) throws SQLException {
        AR_AGING_BY_COATMsg tMsg = new AR_AGING_BY_COATMsg();
    // END   2017/02/28 S.Fujita [QC#17600,MOD]

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        // START 2017/02/28 S.Fujita [QC#17600,MOD]
//        ZYPEZDItemValueSetter.setValue(tMsg.fctDlyArAgingByCoaPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FCT_DLY_AR_AGING_BY_COA_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.arAgingByCoaPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_AGING_BY_COA_SQ));
        // END   2017/02/28 S.Fujita [QC#17600,MOD]
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(tMsg.lineBizTpCd, rs.getString(LINE_BIZ_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustAcctCd, rs.getString(BILL_TO_CUST_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustAcctNm, rs.getString(BILL_TO_CUST_ACCT_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, rs.getString(BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billToLocNum, rs.getString(BILL_TO_LOC_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.cltPsnCd, rs.getString(CLT_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.cltPsnNm, rs.getString(CLT_PSN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.tocCd, rs.getString(TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.psnCd, rs.getString(PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.psnFirstNm, rs.getString(PSN_FIRST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.psnLastNm, rs.getString(PSN_LAST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.arTrxTpCd, rs.getString(AR_TRX_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsInvTpCd, rs.getString(DS_INV_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.arRcptSrcCd, rs.getString(AR_RCPT_SRC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaCcCd, rs.getString(COA_CC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaAcctCd, rs.getString(COA_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaAfflCd, rs.getString(COA_AFFL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dealCcyCd, rs.getString(DEAL_CCY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dealAgingAmt_00, rs.getBigDecimal(DEAL_AGING_AMT_00));
        ZYPEZDItemValueSetter.setValue(tMsg.dealAgingAmt_01, rs.getBigDecimal(DEAL_AGING_AMT_01));
        ZYPEZDItemValueSetter.setValue(tMsg.dealAgingAmt_02, rs.getBigDecimal(DEAL_AGING_AMT_02));
        ZYPEZDItemValueSetter.setValue(tMsg.dealAgingAmt_03, rs.getBigDecimal(DEAL_AGING_AMT_03));
        ZYPEZDItemValueSetter.setValue(tMsg.dealAgingAmt_04, rs.getBigDecimal(DEAL_AGING_AMT_04));
        ZYPEZDItemValueSetter.setValue(tMsg.dealAgingAmt_05, rs.getBigDecimal(DEAL_AGING_AMT_05));
        ZYPEZDItemValueSetter.setValue(tMsg.dealAgingAmt_06, rs.getBigDecimal(DEAL_AGING_AMT_06));
        ZYPEZDItemValueSetter.setValue(tMsg.dealAgingAmt_07, rs.getBigDecimal(DEAL_AGING_AMT_07));
        ZYPEZDItemValueSetter.setValue(tMsg.dealAgingAmt_08, rs.getBigDecimal(DEAL_AGING_AMT_08));
        ZYPEZDItemValueSetter.setValue(tMsg.dealAgingAmt_09, rs.getBigDecimal(DEAL_AGING_AMT_09));
        ZYPEZDItemValueSetter.setValue(tMsg.dealAgingAmt_10, rs.getBigDecimal(DEAL_AGING_AMT_10));
        ZYPEZDItemValueSetter.setValue(tMsg.dealAgingAmt_11, rs.getBigDecimal(DEAL_AGING_AMT_11));
        ZYPEZDItemValueSetter.setValue(tMsg.dealAgingAmt_12, rs.getBigDecimal(DEAL_AGING_AMT_12));
        ZYPEZDItemValueSetter.setValue(tMsg.dealAgingAmt_13, rs.getBigDecimal(DEAL_AGING_AMT_13));
        ZYPEZDItemValueSetter.setValue(tMsg.funcCcyCd, rs.getString(FUNC_CCY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.funcAgingAmt_00, rs.getBigDecimal(FUNC_AGING_AMT_00));
        ZYPEZDItemValueSetter.setValue(tMsg.funcAgingAmt_01, rs.getBigDecimal(FUNC_AGING_AMT_01));
        ZYPEZDItemValueSetter.setValue(tMsg.funcAgingAmt_02, rs.getBigDecimal(FUNC_AGING_AMT_02));
        ZYPEZDItemValueSetter.setValue(tMsg.funcAgingAmt_03, rs.getBigDecimal(FUNC_AGING_AMT_03));
        ZYPEZDItemValueSetter.setValue(tMsg.funcAgingAmt_04, rs.getBigDecimal(FUNC_AGING_AMT_04));
        ZYPEZDItemValueSetter.setValue(tMsg.funcAgingAmt_05, rs.getBigDecimal(FUNC_AGING_AMT_05));
        ZYPEZDItemValueSetter.setValue(tMsg.funcAgingAmt_06, rs.getBigDecimal(FUNC_AGING_AMT_06));
        ZYPEZDItemValueSetter.setValue(tMsg.funcAgingAmt_07, rs.getBigDecimal(FUNC_AGING_AMT_07));
        ZYPEZDItemValueSetter.setValue(tMsg.funcAgingAmt_08, rs.getBigDecimal(FUNC_AGING_AMT_08));
        ZYPEZDItemValueSetter.setValue(tMsg.funcAgingAmt_09, rs.getBigDecimal(FUNC_AGING_AMT_09));
        ZYPEZDItemValueSetter.setValue(tMsg.funcAgingAmt_10, rs.getBigDecimal(FUNC_AGING_AMT_10));
        ZYPEZDItemValueSetter.setValue(tMsg.funcAgingAmt_11, rs.getBigDecimal(FUNC_AGING_AMT_11));
        ZYPEZDItemValueSetter.setValue(tMsg.funcAgingAmt_12, rs.getBigDecimal(FUNC_AGING_AMT_12));
        ZYPEZDItemValueSetter.setValue(tMsg.funcAgingAmt_13, rs.getBigDecimal(FUNC_AGING_AMT_13));
        ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoPk, rs.getBigDecimal(CLT_PTFO_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoCd, rs.getString(CLT_PTFO_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoNm, rs.getString(CLT_PTFO_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.dealAgingBalAmt, rs.getBigDecimal(ALL_BAL_DEAL_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.funcAgingBalAmt, rs.getBigDecimal(ALL_BAL_FUNC_AMT));

        return tMsg;
    }

}
