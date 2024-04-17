package com.canon.cusa.s21.batch.NFA.NFAB001501;

import java.math.BigDecimal;


/**
 * Class name: NFAB001501Constant
 * <dd>The class explanation: Constant variable for NFAB001501.
 * <dd>Remarks:
 * @version 1.0
 * @author K.Uramori
 */
public interface NFAB001501Constant {

    // ** Fixed Value ** //    
    /** Fixed Value : Error Message Parameter. */
    static final String MSG_PARAM = "OM Journal Entry Creation";

    /** Fixed Value : Debit Type Code */
    static final String DEBIT_TP_CD_VAL = "D";

    /** Fixed Value : Credit Type Code */
    static final String CREDIT_TP_CD_VAL = "C";

    /** Fixed Value : Referrence Text To Oracle */
    static final String ORCL_REF_10_TXT_PREFIX = "AJE ID : ";

    /** Fixed Value : Tax Line */
    static final String AJE_LINE_IND_TP_CD_TAX = "03";

    /** Fixed Value : FRT Line */
    static final String AJE_LINE_IND_TP_CD_FRT = "02";

    /** Fixed Value : Expense Line */
    static final String AJE_LINE_IND_TP_CD_EXPSE = "04";
    
    /** Fixed Value : Insurance Line */
    static final String AJE_LINE_IND_TP_CD_INS = "13";

    /** Fixed Value : Each Bulk Insert Count */
    static final int BULK_INSERT_COUNT = 1000;

    /** Fixed Value : Big Decimal 0 value */
    static final BigDecimal BIG_DEC_VAL_0 = new BigDecimal(0);  
    
    /** Fixed Value : Affiliated Company */
    static final String AFFL_CD_OTHER = "***";
    
    /** Fixed Value : First Prod Code Z */
    static final String COA_PROD_CD_VAL_Z = "Z";
   
    /** Fixed Value : AJE_ID AE3-010-01 */
    static final String AJE_ID_VAL_CPNet = "DE3-010-01";
    
    /** Fixed Value : TRX_RSN_CD 02 */
    static final String  TRX_RSN_CD_02 = "02";

    /** Fixed Value : AJE_INTFC_CTRL update condition */
    String[] ajeIntfcCtrl_cond = new String[]{"glblCmpyCd","ajeIntfcTpCd","ajeIntfcPk"};

    /** Fixed Value : AJE_INTFC_CTRL update values */
    String[] ajeIntfcCtrl_upd = new String[]{"jrnlCpltFlg"};

    // START 2014/04/16 T.Tozuka [CSA AJE#84 Add]
    /** Fixed Value : Default CoA Tax Account Code (It is used in case failed to get CoA account code) */
    static final String DEFAULT_COA_TAX_ACCT_CD = "99999999";
    // END   2014/04/16 T.Tozuka [CSA AJE#84 Add]

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String JRNL_ENTRY_SQ = "JRNL_ENTRY_SQ";

    /** DB Item Column Name */
    static final String JRNL_ENTRY_PK = "JRNL_ENTRY_PK";
    
    /** DB Item Column Name */
    static final String DS_INV_SLS_CR_PK = "DS_INV_SLS_CR_PK";

    /** DB Item Column Name */
    static final String GL_DT = "GL_DT";

    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD";

    /** DB Item Column Name */
    static final String MDSE_NM = "MDSE_NM";

    /** DB Item Column Name */
    static final String AJE_LINE_IDX_IND_TP_NM = "AJE_LINE_IDX_IND_TP_NM";

    /** DB Item Column Name */
    static final String COA_BR_CD_ORG = "COA_BR_CD_ORG";

    /** DB Item Column Name */
    static final String COA_BR_CD_ORG_INV = "COA_BR_CD_ORG_INV";

    /** DB Item Column Name */
    static final String COA_BR_CD_LINK = "COA_BR_CD_LINK";

    /** DB Item Column Name */
    static final String COA_CC_CD_ORG = "COA_CC_CD_ORG";

    /** DB Item Column Name */
    static final String COA_CC_CD_LINK = "COA_CC_CD_LINK";

    /** DB Item Column Name */
    static final String COA_CC_CD_ORG_INV = "COA_CC_CD_ORG_INV";

    /** DB Item Column Name */
    static final String PRMO_COA_ACCT_CD = "PRMO_COA_ACCT_CD";

    /** DB Item Column Name */
    static final String INV_LINE_COA_ACCT_CD = "INV_LINE_COA_ACCT_CD";

    /** DB Item Column Name */
    static final String COA_PROD_CD_MDSE = "COA_PROD_CD_MDSE";

    /** DB Item Column Name */
    static final String COA_PROD_CD_LINK = "COA_PROD_CD_LINK";

    /** DB Item Column Name */
    static final String COA_PROD_CD_ORG_INV = "COA_PROD_CD_ORG_INV";

    /** DB Item Column Name */
    static final String COA_CH_CD_ORG = "COA_CH_CD_ORG";

    /** DB Item Column Name */
    static final String COA_CH_CD_ORG_INV = "COA_CH_CD_ORG_INV";

    /** DB Item Column Name */
    static final String COA_CH_CD_LINK = "COA_CH_CD_LINK";
    
    /** DB Item Column Name */
    static final String COA_CH_CD_CUST = "COA_CH_CD_CUST";

    /** DB Item Column Name */
    static final String COA_AFFL_CD_LINK = "COA_AFFL_CD_LINK";

    /** DB Item Column Name */
    static final String INV_INVTY_IND_TP_CD = "INV_INVTY_IND_TP_CD";

    /** DB Item Column Name */
    static final String FUNC_CCY_CD = "FUNC_CCY_CD";

    /** DB Item Column Name */
    static final String DEAL_CCY_CD = "DEAL_CCY_CD";

    /** DB Item Column Name */
    static final String EXCH_RATE = "EXCH_RATE";

    /** DB Item Column Name */
    static final String ACCT_ARTH_TP_CD = "ACCT_ARTH_TP_CD";

    /** DB Item Column Name */
    static final String ORCL_CCY_CD = "ORCL_CCY_CD";

    /** DB Item Column Name */
    static final String SHIP_QTY = "SHIP_QTY";

    /** DB Item Column Name */
    static final String INV_LINE_DEAL_NET_AMT = "INV_LINE_DEAL_NET_AMT";

    /** DB Item Column Name */
    static final String INV_LINE_FUNC_NET_AMT = "INV_LINE_FUNC_NET_AMT";

    /** DB Item Column Name */
    static final String  AJE_INTFC_CMNT_TXT = "AJE_INTFC_CMNT_TXT";

    /** DB Item Column Name */
    static final String  FRT_DEAL_TAX_AMT = "FRT_DEAL_TAX_AMT";

    /** DB Item Column Name */
    static final String  FRT_FUNC_TAX_AMT = "FRT_FUNC_TAX_AMT";

    /** DB Item Column Name */
    static final String  SHIP_DEAL_FRT_AMT = "SHIP_DEAL_FRT_AMT";

    /** DB Item Column Name */
    static final String  SHIP_FUNC_FRT_AMT = "SHIP_FUNC_FRT_AMT";

    /** DB Item Column Name */
    static final String  INV_LINE_DEAL_TAX_AMT = "INV_LINE_DEAL_TAX_AMT";

    /** DB Item Column Name */
    static final String  INV_LINE_FUNC_TAX_AMT = "INV_LINE_FUNC_TAX_AMT";

    /** DB Item Column Name */
    static final String  DEAL_GRS_TOT_PRC_AMT = "DEAL_GRS_TOT_PRC_AMT";

    /** DB Item Column Name */
    static final String  FUNC_GRS_TOT_PRC_AMT = "FUNC_GRS_TOT_PRC_AMT";

    /** DB Item Column Name */
    static final String  DEAL_DISC_AMT = "DEAL_DISC_AMT";

    /** DB Item Column Name */
    static final String  FUNC_DISC_AMT = "FUNC_DISC_AMT";

    /** DB Item Column Name */
    static final String  COA_BR_CD_DPC_ITEM = "COA_BR_CD_DPC_ITEM";

    /** DB Item Column Name */
    static final String  COA_CC_CD_DPC_ITEM = "COA_CC_CD_DPC_ITEM";

    /** DB Item Column Name */
    static final String  COA_CH_CD_DPC_ITEM = "COA_CH_CD_DPC_ITEM";

    /** DB Item Column Name */
    static final String  COA_BR_CD_DPC_LINK = "COA_BR_CD_DPC_LINK";

    /** DB Item Column Name */
    static final String  COA_CC_CD_DPC_LINK = "COA_CC_CD_DPC_LINK";

    /** DB Item Column Name */
    static final String  COA_CH_CD_DPC_LINK = "COA_CH_CD_DPC_LINK";

    /** DB Item Column Name */
    static final String  COA_PROJ_CD_LINK = "COA_PROJ_CD_LINK";
    
    /** DB Item Column Name */
    static final String  COA_BR_CD_DPC_PTRN = "COA_BR_CD_DPC_PTRN";

    /** DB Item Column Name */
    static final String  COA_CC_CD_DPC_PTRN = "COA_CC_CD_DPC_PTRN";

    /** DB Item Column Name */
    static final String  COA_CH_CD_DPC_PTRN = "COA_CH_CD_DPC_PTRN";

    /** DB Item Column Name */
    static final String  COA_CH_CD_LINK_LINK = "COA_CH_CD_LINK_LINK";

    /** DB Item Column Name */
    static final String  COA_CH_CD_LINK_PTRN = "COA_CH_CD_LINK_PTRN";

    /** DB Item Column Name */
    static final String  BILL_TO_CUST_CD = "BILL_TO_CUST_CD";
    
    /** DB Item Column Name */
    static final String  SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";
    
    /** DB Item Column Name */
    static final String  SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** DB Item Column Name */
    static final String  INV_NUM = "INV_NUM";

    /** DB Item Column Name */
    static final String  PRMO_PK = "PRMO_PK";

    /** DB Item Column Name */
    static final String  SLS_REP_TOC_CD = "SLS_REP_TOC_CD";

    /** DB Item Column Name */
    static final String  PRT_NM = "PRT_NM";

    /** DB Item Column Name */
    static final String  COA_PROD_CD_PRT = "COA_PROD_CD_PRT";
    
    /** DB Item Column Name */
    static final String  COA_PROD_CD_ORG = "COA_PROD_CD_ORG";
    
    /** DB Item Column Name */
    static final String  COA_BR_CD_DPC_TOC = "COA_BR_CD_DPC_TOC";
    
    /** DB Item Column Name */
    static final String  COA_CC_CD_DPC_TOC = "COA_CC_CD_DPC_TOC";
    
    /** DB Item Column Name */
    static final String  COA_CH_CD_DPC_TOC = "COA_CH_CD_DPC_TOC";
    
    /** DB Item Column Name */
    static final String  COA_CH_CD_TOC_CUST = "COA_CH_CD_TOC_CUST";
    
    /** DB Item Column Name */
    static final String  COA_CH_CD_TOC_PTRN = "COA_CH_CD_TOC_PTRN";
    
    /** DB Item Column Name */
    static final String  PRT_MDSE_COA_PROD_CD = "PRT_MDSE_COA_PROD_CD";
    
    /** DB Item Column Name */
    static final String INV_TOT_FUNC_INS_AMT = "INV_TOT_FUNC_INS_AMT";
    
    /** DB Item Column Name */
    static final String INV_TOT_DEAL_INS_AMT = "INV_TOT_DEAL_INS_AMT";
    
    /** DB Item Column Name */
    static final String COA_ACCT_CD_AT_AFFL = "COA_ACCT_CD_AT_AFFL";
    
    /** DB Item Column Name */
    static final String DISC_AMT = "DISC_AMT";
    
    /** DB Item Column Name */
    static final String DS_ORD_TP_CD = "DS_ORD_TP_CD";
    
    /** DB Item Column Name */
    static final String DS_ORD_RSN_CD = "DS_ORD_RSN_CD";
    
    /** DB Item Column Name */
    static final String MACH_CONFIG_NUM = "MACH_CONFIG_NUM";
    
    /** DB Item Column Name */
    static final String AJE_ITEM_CD = "AJE_ITEM_CD";
    
    /** DB Item Column Name */
    static final String AJE_ITEM_DESC_TXT = "AJE_ITEM_DESC_TXT";
    
    /** DB Item Column Name */
    static final String DR_AJE_LINE_IDX_DESC_TXT = "DR_AJE_LINE_IDX_DESC_TXT";
    
    /** DB Item Column Name */
    static final String DR_COA_CMPY_CD = "DR_COA_CMPY_CD";
    
    /** DB Item Column Name */
    static final String DR_COA_BR_CD = "DR_COA_BR_CD";
    
    /** DB Item Column Name */
    static final String DR_COA_CC_CD = "DR_COA_CC_CD";
    
    /** DB Item Column Name */
    static final String DR_COA_ACCT_CD = "DR_COA_ACCT_CD";
    
    /** DB Item Column Name */
    static final String DR_COA_PROD_CD = "DR_COA_PROD_CD";
    
    /** DB Item Column Name */
    static final String DR_COA_CH_CD = "DR_COA_CH_CD";
    
    /** DB Item Column Name */
    static final String DR_COA_AFFL_CD = "DR_COA_AFFL_CD";
    
    /** DB Item Column Name */
    static final String DR_COA_PROJ_CD = "DR_COA_PROJ_CD";
    
    /** DB Item Column Name */
    static final String DR_COA_EXTN_CD = "DR_COA_EXTN_CD";
    
    /** DB Item Column Name */
    static final String CR_COA_CMPY_CD = "CR_COA_CMPY_CD";
    
    /** DB Item Column Name */
    static final String CR_COA_BR_CD = "CR_COA_BR_CD";
    
    /** DB Item Column Name */
    static final String CR_COA_CC_CD = "CR_COA_CC_CD";
    
    /** DB Item Column Name */
    static final String CR_COA_ACCT_CD = "CR_COA_ACCT_CD";
    
    /** DB Item Column Name */
    static final String CR_COA_PROD_CD = "CR_COA_PROD_CD";
    
    /** DB Item Column Name */
    static final String CR_COA_CH_CD = "CR_COA_CH_CD";
    
    /** DB Item Column Name */
    static final String CR_COA_AFFL_CD = "CR_COA_AFFL_CD";
    
    /** DB Item Column Name */
    static final String CR_COA_PROJ_CD = "CR_COA_PROJ_CD";
    
    /** DB Item Column Name */
    static final String CR_COA_EXTN_CD = "CR_COA_EXTN_CD";
    
    /** DB Item Column Name */
    static final String JRNL_QTY = "JRNL_QTY";
    
    /** DB Item Column Name */
    static final String JRNL_DEAL_DR_AMT = "JRNL_DEAL_DR_AMT";
    
    /** DB Item Column Name */
    static final String JRNL_DEAL_CR_AMT = "JRNL_DEAL_CR_AMT";
    
    /** DB Item Column Name */
    static final String JRNL_FUNC_DR_AMT = "JRNL_FUNC_DR_AMT";
    
    /** DB Item Column Name */
    static final String JRNL_FUNC_CR_AMT = "JRNL_FUNC_CR_AMT";
    
    /** DB Item Column Name */
    static final String COA_RPOD_CD = "COA_PROD_CD";
    
    /** DB Item Column Name */
    static final String TOC_CD = "TOC_CD";
    
    /** DB Item Column Name */
    static final String AJE_INV_NUM = "AJE_INV_NUM";
    
    /** DB Item Column Name */
    static final String DS_ACCT_NUM = "DS_ACCT_NUM";
    
    /** DB Item Column Name */
    static final String SER_NUM = "SER_NUM";
    
    /** DB Item Column Name */
    static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";
    
    /** DB Item Column Name */
    static final String DS_CONTR_PK = "DS_CONTR_PK";
    
    /** DB Item Column Name */
    static final String DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";
    
    /** DB Item Column Name */
    static final String DS_CONTR_NUM = "DS_CONTR_NUM";
    
    /** DB Item Column Name */
    static final String AJE_INV_ACCT_DIST_PCT = "AJE_INV_ACCT_DIST_PCT";
    
    /** DB Item Column Name */
    static final String AJE_INV_ACCT_DIST_PK = "AJE_INV_ACCT_DIST_PK";
    
    // START 2014/04/16 T.Tozuka [CSA AJE#84 Add]
    /** DB Item Column Name */
    static final String COA_TAX_ACCT_CD = "COA_TAX_ACCT_CD";
    // END   2014/04/16 T.Tozuka [CSA AJE#84 Add]
    
    /** Display Item Column Name */
    static final String DISP_DS_CONTR_NUM = "Contract Number";
    
    /** Display Item Column Name */
    static final String DISP_AJE_INV_ACCT_DIST_PCT = "Service AllocationRatio";

    /** DB Item Column Name */
    static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** Display Item Column Name */
    static final String DISP_CUST_ISS_PO_NUM = "Customer PO#";

    /** DB Item Column Name */
    static final String DS_ORD_POSN_NUM = "DS_ORD_POSN_NUM";

    /** DB Item Column Name */
    static final String DS_CPO_LINE_NUM = "DS_CPO_LINE_NUM";

    /** DB Item Column Name */
    static final String DS_CPO_LINE_SUB_NUM = "DS_CPO_LINE_SUB_NUM";

    /** Display Item Column Name */
    static final String DISP_ORD_LINE_NUM = "Order Line#";
}
