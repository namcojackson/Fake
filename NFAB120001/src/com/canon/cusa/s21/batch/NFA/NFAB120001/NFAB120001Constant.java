package com.canon.cusa.s21.batch.NFA.NFAB120001;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDTMsg;
import business.db.AJE_INV_ACCT_DISTTMsg;
import business.db.AJE_INV_ACCT_DIST_ERRTMsg;
import business.parts.NFACommonJrnlEntry;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * Invoice Distribution Create Batch
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 03/12/2018   Hitachi         E.Kameishi      Create          N/A
 *</pre>
 */
public interface NFAB120001Constant {

    // ** Fixed Value ** //
    /** business_id : NFAB1200 */
    String BUSINESS_ID = "NFAB120001";

    /** Message */
    String NFAM0035E = "NFAM0035E";

    /** Message */
    String NFAM0032E = "NFAM0032E";

    /** Message */
    String ZZZM9025E = "ZZZM9025E";

    /** Message */
    String UNIQUE_ERROR = "ZZBM0074E";

    /** Message */
    public static final String ZZBM0074E = "ZZBM0074E";

    /** Message */
    public static final String NWAM0794E = "NWAM0794E";

    /** Message */
    public static final String NFZM0027E = "NFZM0027E";

    /** Message */
    public static final String COA_SEG_ERR = "NFCM0531E";

    /** Message */
    public static final String NO_AJE_PTRN_ERR = "NFAM0037E";

    /** Message */
    public static final String NO_SLS_CR_ERR = "ZZZM9006E";

    /** Insert Count*/
    int BULK_INSERT_CNT = 10000;

    /** */
    public static final String COA_CMPY_CD = "COA_CMPY_CD";

    /** */
    public static final String COA_AFFL_CD = "COA_AFFL_CD";

    /** */
    public static final String COA_BR_CD = "COA_BR_CD";

    /** */
    public static final String COA_CH_CD = "COA_CH_CD";

    /** */
    public static final String COA_CC_CD = "COA_CC_CD";

    /** */
    public static final String COA_ACCT_CD = "COA_ACCT_CD";

    /** */
    public static final String COA_PROD_CD = "COA_PROD_CD";

    /** */
    public static final String COA_PROJ_CD = "COA_PROJ_CD";

    /** */
    public static final String COA_EXTN_CD = "COA_EXTN_CD";

    /** */
    public static final String TRX_CD = "TRX_CD";

    /** */
    public static final String TRX_RSN_CD = "TRX_RSN_CD";

    /** */
    public static final String DEAL_SLS_CR_AMT = "DEAL_SLS_CR_AMT";

    /** */
    public static final String FUNC_SLS_CR_AMT = "FUNC_SLS_CR_AMT";

    /** */
    public static final String INV_LINE_SPL_PCT = "INV_LINE_SPL_PCT";

    /** */
    public static final String SLS_REP_CR_PCT = "SLS_REP_CR_PCT";

    /** */
    public static final String DS_INV_SLS_CR_PK = "DS_INV_SLS_CR_PK";

    /** */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** */
    public static final String MDSE_CD = "MDSE_CD";

    /** */
    public static final String INV_NUM = "INV_NUM";

    /** */
    public static final String INV_TP_CD = "INV_TP_CD";

    /** */
    public static final String ACCT_DT = "ACCT_DT";

    /** */
    public static final String INV_DT = "INV_DT";

    /** */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** */
    public static final String DEAL_CCY_CD = "DEAL_CCY_CD";

    /** */
    public static final String INV_LINE_NUM = "INV_LINE_NUM";

    /** */
    public static final String INV_LINE_DEAL_TAX_AMT = "INV_LINE_DEAL_TAX_AMT"; // ADD 2016/12/07 [QC#16174]

    /** */
    public static final String INV_LINE_FUNC_TAX_AMT = "INV_LINE_FUNC_TAX_AMT"; // ADD 2016/12/07 [QC#16174]
    /** */

    public static final String FRT_DEAL_TAX_AMT = "FRT_DEAL_TAX_AMT"; // ADD 2016/12/07 [QC#16174]

    /** */
    public static final String FRT_FUNC_TAX_AMT = "FRT_FUNC_TAX_AMT"; // ADD 2016/12/07 [QC#16174]

    /** */
//  public static final String TOT_BOL_DEAL_TAX_AMT = "TOT_BOL_DEAL_TAX_AMT"; // DEL 2016/12/07 [QC#16174]

  /** */
//  public static final String TOT_BOL_FUNC_TAX_AMT = "TOT_BOL_FUNC_TAX_AMT"; // DEL 2016/12/07 [QC#16174]

    /** */
    public static final String INV_BOL_LINE_NUM = "INV_BOL_LINE_NUM";

    /** */
    public static final String INV_LINE_SUB_NUM = "INV_LINE_SUB_NUM";

    /** */
    public static final String INV_TRX_LINE_SUB_NUM = "INV_TRX_LINE_SUB_NUM";

    /** */
    public static final String SHIP_DEAL_FRT_AMT = "SHIP_DEAL_FRT_AMT";

    /** */
    public static final String SHIP_FUNC_FRT_AMT = "SHIP_FUNC_FRT_AMT";

    /** */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** */
    public static final String DS_ORD_TP_CD = "DS_ORD_TP_CD";

    /** */
    public static final String DS_ORD_RSN_CD = "DS_ORD_RSN_CD";

    /** */
    public static final String SYS_SRC_CD = "SYS_SRC_CD";

    /** */
    public static final String SYS_SRC_NM = "SYS_SRC_NM";

    /** */
    public static final String COA_ATTRB_NM_01 = "COA_ATTRB_NM_01";

    /** */
    public static final String COA_ATTRB_NM_02 = "COA_ATTRB_NM_02";

    /** */
    public static final String COA_ATTRB_NM_03 = "COA_ATTRB_NM_03";

    /** */
    public static final String COA_ATTRB_NM_04 = "COA_ATTRB_NM_04";

    /** */
    public static final String COA_ATTRB_NM_05 = "COA_ATTRB_NM_05";

    /** */
    public static final String COA_ATTRB_NM_06 = "COA_ATTRB_NM_06";

    /** */
    public static final String COA_ATTRB_NM_07 = "COA_ATTRB_NM_07";

    /** */
    public static final String COA_ATTRB_NM_08 = "COA_ATTRB_NM_08";

    /** */
    public static final String COA_ATTRB_NM_09 = "COA_ATTRB_NM_09";

    /** */
    public static final String DR_CR_TP_CD = "DR_CR_TP_CD";

    /** */
    public static final String DR_CR_TP_CD_CREDIT = "C";

    /** */
    public static final String DR_CR_TP_CD_DEBIT = "D";

    /** */
    public static final int DEBUG_MSG_LVL = 8;

    /** */
    public static final int ERR_MSG_TXT_LEN = 1000;

    /** */
    public static final int INVLD_VAL_TXT_LEN = 50;

    /** */
    static final String AJE_INTFC_TP_CD_VAL = "01";

    /** */
    static final String AJE_INV_ACCT_DIST_SQ = "AJE_INV_ACCT_DIST_SQ";
    
    /** */
    // static final String AJE_INV_ACCT_DIST_ERR_SQ =
    // "AJE_INV_ACCT_DIST_ERR_SQ";
    static final String AJE_INV_ACCT_DIST_ERR_SQ = "AJE_INV_ACCT_DIST_ERR_SQ";

    /** */
    static final String AJE_INV_ACCT_DIST_PK = "AJE_INV_ACCT_DIST_PK";

    /** */
    static final String AJE_INV_ACCT_CLS_CD = "AJE_INV_ACCT_CLS_CD";

    /** */
    static final String DR_CD = "D";

    /** */
    static final String CR_CD = "C";

    /** */
    public static final String COAID_COA_CMPY = "1";

    /** */
    public static final String COAID_COA_BR = "2";

    /** */
    public static final String COAID_COA_CC = "3";

    /** */
    public static final String COAID_COA_ACCT = "4";

    /** */
    public static final String COAID_COA_PROD = "5";

    /** */
    public static final String COAID_COA_CH = "6";

    /** */
    public static final String COAID_COA_AFFL = "7";

    /** */
    public static final String COAID_COA_PROJ = "8";

    /** */
    public static final String COAID_COA_EXTN = "9";

    /** */
    public static final String COAMSG_COA_CMPY = "CMPY";

    /** */
    public static final String COAMSG_COA_BR = "BR";

    /** */
    public static final String COAMSG_COA_CC = "CC";

    /** */
    public static final String COAMSG_COA_ACCT = "ACCT";

    /** */
    public static final String COAMSG_COA_PROD = "PROD";

    /** */
    public static final String COAMSG_COA_CH = "CH";

    /** */
    public static final String COAMSG_COA_AFFL = "AFFL";

    /** */
    public static final String COAMSG_COA_PROJ = "PROJ";

    /** */
    public static final String COAMSG_COA_EXTN = "EXTN";

    /** Const Name */
    public static final String AJE_COA_DEF_TAX_ACCT = "AJE_COA_DEF_TAX_ACCT";

    /** */
    public static final String COA_ACCT_CD_ITEM_REV = "@ITEM-REV";

    /** */
    public static final String BS_PL_TP_CD_BS = "B";

    /** */
    public static final String DOT = ".";

    /** */
    public static final String ERR_MSG_LIST = "NFAM0188E,NFAM0189E,NFAM0190E,NFAM0191E,NFAM0192E,NFAM0193E,NFAM0194E,NFAM0195E,NFAM0196E";
    // START 2018/05/21 E.Kameishi [QC#21841,MOD]
    public static final String COA_MDSE_TP_CD = "COA_MDSE_TP_CD";
    // END 2018/05/21 E.Kameishi [QC#21841,MOD]
}
