/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB501001.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Daily AR Customer Aging By COA Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/02/2016   CITS            M.Okigami       Create          N/A
 * 06/05/2018   Hitachi         T.Tsuchida      Update          QC#26481
 *</pre>
 */
public class NFCB501001Constant {

    /** fetchSize */
    public static final int FETCH_SIZE = 1000;

    /** commitCount */
    public static final int DEFAULT_COMMIT_COUNT = 1000;

    /** [@] has no value. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Data insert failure.(ReturnCode = [@]) */
    public static final String ZZZM9012E = "ZZZM9012E";

    /** Data delete failure.(ReturnCode = [@]) */
    public static final String ZZZM9014E = "ZZZM9014E";

    /** message Item: GlobalCompanyCode. */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: Batch Process Date. */
    public static final String MSG_ITEM_BATCH_DATE = "Batch Process Date";

    /** SQL PARAM NAME */
    /** . */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** . */
    public static final String PARAM_SALES_DT = "slsDt";
    /** . */
    public static final String PARAM_DWH_TRGT_DT = "dwhtrgtDt";
    /** . */
    public static final String PARAM_AR_CASH_APPLY_STS_CD_UNAPPLIED = "arCachApplyStsCdUnapplied";
    /** . */
    public static final String PARAM_AR_CASH_APPLY_STS_CD_PARTIAL = "arCachApplyStsCdPartial";
    /** . */
    public static final String PARAM_AR_CASH_APPLY_STS_CD_UNIDENTIFIED = "arCachApplyStsCdUnidentified";
    /** . */
    public static final String PARAM_AR_TRX_TP_CD_INVOICE = "arTrxTpCdInvoice";
    /** . */
    public static final String PARAM_AR_TRX_TP_CD_DEDUCTION = "arTrxTpCdDeduction";
    /** . */
    public static final String PARAM_AR_TRX_TP_CD_DEBIT_MEMO = "arTrxTpCdDebitMemo";
    /** . */
    public static final String PARAM_AR_TRX_TP_CD_CREDIT_MEMO = "arTrxTpCdCreditMemo";
    /** . */
    public static final String PARAM_AR_TRX_TP_CD_RECEIPT = "arTrxTpCdReceipt";
    /** . */
    public static final String PARAM_AR_TRX_TP_CD_ON_ACCOUNT = "arTrxTpCdOnAccount";
    /** . */
    public static final String PARAM_UNKNOWN_CD = "unknownCd";
    /** . */
    public static final String PARAM_LINE_BIZ_TP_CD_ALL = "lineBizTpCdAll";
    /** . */
    public static final String PARAM_AGE_0 = "age0";
    /** . */
    public static final String PARAM_AGE_30 = "age30";
    /** . */
    public static final String PARAM_AGE_60 = "age60";
    /** . */
    public static final String PARAM_AGE_90 = "age90";
    /** . */
    public static final String PARAM_AGE_120 = "age120";
    /** . */
    public static final String PARAM_AGE_150 = "age150";
    /** . */
    public static final String PARAM_AGE_180 = "age180";
    /** . */
    public static final String PARAM_AGE_210 = "age210";
    /** . */
    public static final String PARAM_AGE_240 = "age240";
    /** . */
    public static final String PARAM_AGE_270 = "age270";
    /** . */
    public static final String PARAM_AGE_300 = "age300";
    /** . */
    public static final String PARAM_AGE_330 = "age330";
    /** . */
    public static final String PARAM_AGE_365 = "age365";
    /** . */
    public static final String PARAM_RPT_COND_CONST_VAL_RS_UNID = "rptCondConstValRsUnid";
    /** . */
    public static final String PARAM_RPT_COND_CONST_VAL_RS_UNAPL = "rptCondConstValRsUnapl";
    /** . */
    public static final String PARAM_RPT_COND_CONST_VAL_RS_CASH = "rptCondConstValRsCash";
    /** . */
    public static final String PARAM_RPT_COND_CONST_VAL_RS_OACC = "rptCondConstValRsOacc";
    /** . */
    public static final String PARAM_COA_ACCT_CD_DEF = "coaAcctCdDef";
    /** . */
    public static final String PARAM_COA_AFFL_CD_DEF = "coaAfflCdDef";
    /** . */
    public static final String PARAM_COA_CC_CD_DEF = "coaCcCdDef";
    /** . */
    public static final String PARAM_RPT_COND_CONST_GRP_ID = "rptCondConstGrpId";
    /** . */
    public static final String PARAM_RPT_COND_CONST_CD_RCPT_IDENTIFIED = "rptCondConstCdRcptIdentified";
    /** . */
    public static final String PARAM_RPT_COND_CONST_CD_RECEIPT = "rptCondConstCdReceipt";
    /** . */
    public static final String PARAM_RPT_COND_CONST_CD_ON_ACCOUNT = "rptCondConstCdOnAccount";
    /** . */
    public static final String PARAM_AMT_0 = "amt0";

    /** SQL PARAM VALUE */
    /** . */
    public static final String PARAM_VAL_UNKNOWN_CD = "ZZZZZZZ";
    /** . */
    public static final BigDecimal PARAM_VAL_AGE_0 = BigDecimal.ZERO;
    /** . */
    public static final BigDecimal PARAM_VAL_AGE_30 = BigDecimal.valueOf(30);
    /** . */
    public static final BigDecimal PARAM_VAL_AGE_60 = BigDecimal.valueOf(60);
    /** . */
    public static final BigDecimal PARAM_VAL_AGE_90 = BigDecimal.valueOf(90);
    /** . */
    public static final BigDecimal PARAM_VAL_AGE_120 = BigDecimal.valueOf(120);
    /** . */
    public static final BigDecimal PARAM_VAL_AGE_150 = BigDecimal.valueOf(150);
    /** . */
    public static final BigDecimal PARAM_VAL_AGE_180 = BigDecimal.valueOf(180);
    /** . */
    public static final BigDecimal PARAM_VAL_AGE_210 = BigDecimal.valueOf(210);
    /** . */
    public static final BigDecimal PARAM_VAL_AGE_240 = BigDecimal.valueOf(240);
    /** . */
    public static final BigDecimal PARAM_VAL_AGE_270 = BigDecimal.valueOf(270);
    /** . */
    public static final BigDecimal PARAM_VAL_AGE_300 = BigDecimal.valueOf(300);
    /** . */
    public static final BigDecimal PARAM_VAL_AGE_330 = BigDecimal.valueOf(330);
    /** . */
    public static final BigDecimal PARAM_VAL_AGE_365 = BigDecimal.valueOf(365);
    /** . */
    public static final String PARAM_VAL_RPT_COND_CONST_VAL_RS_UNID = "@RS-UNID";
    /** . */
    public static final String PARAM_VAL_RPT_COND_CONST_VAL_RS_UNAPL = "@RS-UNAPL";
    /** . */
    public static final String PARAM_VAL_RPT_COND_CONST_VAL_RS_CASH = "@RS-CASH";
    /** . */
    public static final String PARAM_VAL_RPT_COND_CONST_VAL_RS_OACC = "@RS-OACC";
    /** . */
    public static final String PARAM_VAL_COA_ACCT_CD_DEF = "00000000";
    /** . */
    public static final String PARAM_VAL_COA_AFFL_CD_DEF = "000";
    /** . */
    public static final String PARAM_VAL_COA_CC_CD_DEF = "000000";
    /** . */
    public static final String PARAM_VAL_RPT_COND_CONST_GRP_ID = "NFCB5010_RCPT_COA";
    /** . */
    public static final String PARAM_VAL_RPT_COND_CONST_CD_RCPT_IDENTIFIED = "RCPT IDENTIFIED";
    /** . */
    public static final String PARAM_VAL_RPT_COND_CONST_CD_RECEIPT = "RECEIPT";
    /** . */
    public static final String PARAM_VAL_RPT_COND_CONST_CD_ON_ACCOUNT = "ON ACCOUNT";
    /** . */
    public static final BigDecimal PARAM_VAL_AMT_0 = BigDecimal.ZERO;

    /** DB COLUMN NAME */
    /** . */
    public static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";
    /** . */
    public static final String BILL_TO_CUST_ACCT_CD = "BILL_TO_CUST_ACCT_CD";
    /** . */
    public static final String BILL_TO_CUST_ACCT_NM = "BILL_TO_CUST_ACCT_NM";
    /** . */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";
    /** . */
    public static final String BILL_TO_LOC_NUM = "BILL_TO_LOC_NUM";
    /** . */
    public static final String CLT_PSN_CD = "CLT_PSN_CD";
    /** . */
    public static final String CLT_PSN_NM = "CLT_PSN_NM";
    /** . */
    public static final String TOC_CD = "TOC_CD";
    /** . */
    public static final String PSN_CD = "PSN_CD";
    /** . */
    public static final String PSN_FIRST_NM = "PSN_FIRST_NM";
    /** . */
    public static final String PSN_LAST_NM = "PSN_LAST_NM";
    /** . */
    public static final String AR_TRX_TP_CD = "AR_TRX_TP_CD";
    /** . */
    public static final String DS_INV_TP_CD = "DS_INV_TP_CD";
    /** . */
    public static final String AR_RCPT_SRC_CD = "AR_RCPT_SRC_CD";
    /** . */
    public static final String COA_CC_CD = "COA_CC_CD";
    /** . */
    public static final String COA_ACCT_CD = "COA_ACCT_CD";
    /** . */
    public static final String COA_AFFL_CD = "COA_AFFL_CD";
    /** . */
    public static final String DEAL_CCY_CD = "DEAL_CCY_CD";
    /** . */
    public static final String DEAL_AGING_AMT_00 = "DEAL_AGING_AMT_00";
    /** . */
    public static final String DEAL_AGING_AMT_01 = "DEAL_AGING_AMT_01";
    /** . */
    public static final String DEAL_AGING_AMT_02 = "DEAL_AGING_AMT_02";
    /** . */
    public static final String DEAL_AGING_AMT_03 = "DEAL_AGING_AMT_03";
    /** . */
    public static final String DEAL_AGING_AMT_04 = "DEAL_AGING_AMT_04";
    /** . */
    public static final String DEAL_AGING_AMT_05 = "DEAL_AGING_AMT_05";
    /** . */
    public static final String DEAL_AGING_AMT_06 = "DEAL_AGING_AMT_06";
    /** . */
    public static final String DEAL_AGING_AMT_07 = "DEAL_AGING_AMT_07";
    /** . */
    public static final String DEAL_AGING_AMT_08 = "DEAL_AGING_AMT_08";
    /** . */
    public static final String DEAL_AGING_AMT_09 = "DEAL_AGING_AMT_09";
    /** . */
    public static final String DEAL_AGING_AMT_10 = "DEAL_AGING_AMT_10";
    /** . */
    public static final String DEAL_AGING_AMT_11 = "DEAL_AGING_AMT_11";
    /** . */
    public static final String DEAL_AGING_AMT_12 = "DEAL_AGING_AMT_12";
    /** . */
    public static final String DEAL_AGING_AMT_13 = "DEAL_AGING_AMT_13";
    /** . */
    public static final String FUNC_CCY_CD = "FUNC_CCY_CD";
    /** . */
    public static final String FUNC_AGING_AMT_00 = "FUNC_AGING_AMT_00";
    /** . */
    public static final String FUNC_AGING_AMT_01 = "FUNC_AGING_AMT_01";
    /** . */
    public static final String FUNC_AGING_AMT_02 = "FUNC_AGING_AMT_02";
    /** . */
    public static final String FUNC_AGING_AMT_03 = "FUNC_AGING_AMT_03";
    /** . */
    public static final String FUNC_AGING_AMT_04 = "FUNC_AGING_AMT_04";
    /** . */
    public static final String FUNC_AGING_AMT_05 = "FUNC_AGING_AMT_05";
    /** . */
    public static final String FUNC_AGING_AMT_06 = "FUNC_AGING_AMT_06";
    /** . */
    public static final String FUNC_AGING_AMT_07 = "FUNC_AGING_AMT_07";
    /** . */
    public static final String FUNC_AGING_AMT_08 = "FUNC_AGING_AMT_08";
    /** . */
    public static final String FUNC_AGING_AMT_09 = "FUNC_AGING_AMT_09";
    /** . */
    public static final String FUNC_AGING_AMT_10 = "FUNC_AGING_AMT_10";
    /** . */
    public static final String FUNC_AGING_AMT_11 = "FUNC_AGING_AMT_11";
    /** . */
    public static final String FUNC_AGING_AMT_12 = "FUNC_AGING_AMT_12";
    /** . */
    public static final String FUNC_AGING_AMT_13 = "FUNC_AGING_AMT_13";
    /** . */
    public static final String CLT_PTFO_PK = "CLT_PTFO_PK";
    /** . */
    public static final String CLT_PTFO_CD = "CLT_PTFO_CD";
    /** . */
    public static final String CLT_PTFO_NM = "CLT_PTFO_NM";
    /** . */
    public static final String ALL_BAL_DEAL_AMT = "ALL_BAL_DEAL_AMT";
    /** . */
    public static final String ALL_BAL_FUNC_AMT = "ALL_BAL_FUNC_AMT";

}
