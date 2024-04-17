package com.canon.cusa.s21.batch.NFA.NFAB002001;

import java.math.BigDecimal;

/**
 * Class name: NFAB002001Constant
 * <dd>The class explanation: Constant variable for NFAB002001.
 * <dd>Remarks:
 * @version 1.0
 * @author N. Sasaki
 */
public interface NFAB002001Constant {

    // ** Fixed Value ** //
    /** Fixed Value : Error Message Parameter. */
    static final String MSG_PARAM = "Inventory Journal Creation";

    /** Fixed Value : AJE Interface Type Code for Loan Depreciation. */
    static final String AJE_INTFC_TP_CD_VAL = "02";

    /** Fixed Value : CoA Segment Lookup Type Value for TOC */
    static final String COA_SEG_LKUP_TP_TOC_VAL = "@TOC";

    /** Fixed Value : CoA Segment Lookup Type Value for ITEM */
    static final String COA_SEG_LKUP_TP_ITEM_VAL = "@ITEM";

    /** Fixed Value : CoA Segment Lookup Type Value for CUST */
    static final String COA_SEG_LKUP_TP_CUST_VAL = "@CUST";
    
    /** Fixed Value : PO Account Tp : Acctual*/
    static final String PO_ACCT_TP_ACCRUAL = "AC";
    
    /**
     * Fixed Value : Initial Exchange Rate Value. Loan Depreciation
     * Amount must be in dollars
     */
    static final BigDecimal INITIAL_EXCH_RATE_VAL = new BigDecimal("1.00");

    /** Fixed Value : Debit Type Code */
    static final String DEBIT_TP_CD_VAL = "D";

    /** Fixed Value : Credit Type Code */
    static final String CREDIT_TP_CD_VAL = "C";

    /** Fixed Value : Referrence Text To Oracle */
    static final String ORCL_REF_10_TXT_PREFIX = "AJE ID : ";
    
    /** Fixed Value : Variance */
    static final String LINE_IND_TP_VAR = "11";
    
    /** Fixed Value : Variance */
    static final String LINE_IND_TP_ACRAL = "22";
    
    /** Fixed Value : Variance */
    static final String LINE_IND_TP_RMNF_WIP = "23";
    
    /** Fixed Value : Variance */
    static final String LINE_IND_TP_SHIP_FROM_COST = "24";
    
    /** Fixed Value : Each Bulk Insert Count */
    static final int BULK_INSERT_COUNT = 10000;
       
    /** Fixed Value : MDSE or PARTS Code M */
    static final String MDSE_OR_PRT_CD_M_VAL = "M";
    
    /** DB Item Column Name */
    static final String JRNL_ENTRY_SQ = "JRNL_ENTRY_SQ";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_ID = "AJE_ID";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_PTRN_IND_TP_CD_01 = "AJE_PTRN_IND_TP_CD_01";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_PTRN_IND_TP_NM_01 = "AJE_PTRN_IND_TP_NM_01";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_PTRN_ACTL_CD_01 = "AJE_PTRN_ACTL_CD_01";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_PTRN_ACTL_NM_01 = "AJE_PTRN_ACTL_NM_01";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_PTRN_IND_TP_CD_02 = "AJE_PTRN_IND_TP_CD_02";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_PTRN_IND_TP_NM_02 = "AJE_PTRN_IND_TP_NM_02";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_PTRN_ACTL_CD_02 = "AJE_PTRN_ACTL_CD_02";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_PTRN_ACTL_NM_02 = "AJE_PTRN_ACTL_NM_02";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_PTRN_IND_TP_CD_03 = "AJE_PTRN_IND_TP_CD_03";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_PTRN_IND_TP_NM_03 = "AJE_PTRN_IND_TP_NM_03";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_PTRN_ACTL_CD_03 = "AJE_PTRN_ACTL_CD_03";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_PTRN_ACTL_NM_03 = "AJE_PTRN_ACTL_NM_03";

    /** DB Item Column Name for AJE_PTRN */
    static final String JRNL_SRC_CD = "JRNL_SRC_CD";

    /** DB Item Column Name for AJE_PTRN */
    static final String JRNL_SRC_NM = "JRNL_SRC_NM";

    /** DB Item Column Name for AJE_PTRN */
    static final String JRNL_CATG_CD = "JRNL_CATG_CD";

    /** DB Item Column Name for AJE_PTRN */
    static final String JRNL_CATG_NM = "JRNL_CATG_NM";

    /** DB Item Column Name for AJE_PTRN */
    static final String DR_CR_TP_CD = "DR_CR_TP_CD";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_LINK_FLG = "AJE_LINK_FLG";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_LINE_IDX_NUM = "AJE_LINE_IDX_NUM";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_LINE_IDX_DESC_TXT = "AJE_LINE_IDX_DESC_TXT";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_LINE_IDX_IND_TP_CD = "AJE_LINE_IDX_IND_TP_CD";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_COA_CMPY_CD = "AJE_COA_CMPY_CD";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_COA_BR_CD = "AJE_COA_BR_CD";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_COA_CC_CD = "AJE_COA_CC_CD";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_COA_ACCT_CD = "AJE_COA_ACCT_CD";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_COA_PROD_CD = "AJE_COA_PROD_CD";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_COA_CH_CD = "AJE_COA_CH_CD";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_COA_AFFL_CD = "AJE_COA_AFFL_CD";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_COA_PROJ_CD = "AJE_COA_PROJ_CD";

    /** DB Item Column Name for AJE_PTRN */
    static final String AJE_COA_EXTN_CD = "AJE_COA_EXTN_CD";

    /** COA_PROD_CD Name in map list */
    static final String AJE_ID_COUNT = "AJE_ID_COUNT";

    /** DB Item Column Name for Interface */
    static final String AJE_INVTY_INTFC_PK = "AJE_INVTY_INTFC_PK";

    /** DB Item Column Name for Interface */
    static final String GL_DT = "GL_DT";

    /** DB Item Column Name for Interface */
    static final String INVTY_TRX_PK = "INVTY_TRX_PK";

    /** DB Item Column Name for Interface */
    static final String INV_INVTY_IND_TP_CD = "INV_INVTY_IND_TP_CD";

    /** DB Item Column Name for Interface */
    static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** DB Item Column Name for Interface */
    static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** DB Item Column Name for Interface */
    static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** DB Item Column Name for Interface */
    static final String MDSE_CD = "MDSE_CD";

    /** DB Item Column Name for Interface */
    static final String MDSE_NM = "MDSE_NM";

    /** DB Item Column Name for Interface */
    static final String COA_PROD_CD_MDSE = "COA_PROD_CD_MDSE";

    /** DB Item Column Name for Interface */
    static final String MDSE_OR_PRT_CD = "MDSE_OR_PRT_CD";

    /** DB Item Column Name for Interface */
    static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** DB Item Column Name for Interface */
    static final String COA_BR_CD_CSW = "COA_BR_CD_CSW";

    /** DB Item Column Name for Interface */
    static final String COA_CC_CD_CSW = "COA_CC_CD_CSW";

    /** DB Item Column Name for Interface */
    static final String COA_CH_CD_CSW = "COA_CH_CD_CSW";

    /** DB Item Column Name for Interface */
    static final String COA_CC_CD_PR = "COA_CC_CD_PR";

    /** DB Item Column Name for Interface */
    static final String COA_CH_CD_PR = "COA_CH_CD_PR";

    /** DB Item Column Name for Interface */
    static final String TOC_CD = "TOC_CD";

    /** DB Item Column Name for Interface */
    static final String COA_BR_CD_ORG = "COA_BR_CD_ORG";

    /** DB Item Column Name for Interface */
    static final String COA_CC_CD_ORG = "COA_CC_CD_ORG";

    /** DB Item Column Name for Interface */
    static final String COA_CH_CD_ORG = "COA_CH_CD_ORG";
    
    /** DB Item Column Name for Interface */
    static final String COA_BR_CD_LINK = "COA_BR_CD_LINK";

    /** DB Item Column Name for Interface */
    static final String COA_CC_CD_LINK = "COA_CC_CD_LINK";

    /** DB Item Column Name for Interface */
    static final String COA_CH_CD_LINK = "COA_CH_CD_LINK";

    /** DB Item Column Name for Interface */
    static final String VND_CD = "VND_CD";

    /** DB Item Column Name for Interface */
    static final String INTL_VND_FLG = "INTL_VND_FLG";

    /** DB Item Column Name for Interface */
    static final String AJE_DOM_EXP_IMP_TP_CD = "AJE_DOM_EXP_IMP_TP_CD";

    /** DB Item Column Name for Interface */
    static final String INVTY_TRX_QTY = "INVTY_TRX_QTY";

    /** DB Item Column Name for Interface */
    static final String UNIT_COST_AMT = "UNIT_COST_AMT";

    /** DB Item Column Name for Interface */
    static final String SHIP_COST_AMT = "SHIP_COST_AMT";

    /** DB Item Column Name for Interface */
    static final String DEPC_AMT = "DEPC_AMT";

//    /** DB Item Column Name */
//    static final String THIS_MTH_TOT_STD_COST_AMT = "THIS_MTH_TOT_STD_COST_AMT";

    /** DB Item Column Name for Interface */
    static final String COA_ACCT_CD = "COA_ACCT_CD";

    /** DB Item Column Name for Interface */
    static final String COA_ACCT_CD_MC = "COA_ACCT_CD_MC";
    
    /** DB Item Column Name for Interface */
    static final String COA_PROD_CD_LINK = "COA_PROD_CD_LINK";

    /** DB Item Column Name for Interface */
    static final String EXP_PROJ_CD = "EXP_PROJ_CD";

    /** DB Item Column Name for Interface */
    static final String AJE_INTFC_CMNT_TXT = "AJE_INTFC_CMNT_TXT";

    /** DB Item Column Name for Interface */
    static final String COA_CH_CD_BTC = "COA_CH_CD_BTC";
    
    /** DB Item Column Name for Interface */
    static final String COA_CH_CD_CUST = "COA_CH_CD_CUST";

    /** DB Item Column Name for Interface */
    static final String COA_AFFL_CD_BTC = "COA_AFFL_CD_BTC";

    /** DB Item Column Name for Interface */
    static final String COA_AFFL_CD_VND = "COA_AFFL_CD_VND";
    
    /** DB Item Column Name for Interface */
    static final String CCY_CD = "CCY_CD";

    /** DB Item Column Name for Interface */
    static final String ACCT_ARTH_TP_CD = "ACCT_ARTH_TP_CD";

    /** DB Item Column Name for Interface */
    static final String ORCL_CCY_CD = "ORCL_CCY_CD";
    
    /** DB Item Column Name for AJE_PTRN */
    static final String COA_BR_CD_DPC = "COA_BR_CD_DPC";
    
    /** DB Item Column Name for AJE_PTRN */
    static final String COA_CC_CD_DPC = "COA_CC_CD_DPC";
    
    /** DB Item Column Name for AJE_PTRN */
    static final String COA_CH_CD_DPC = "COA_CH_CD_DPC";
    
    /** DB Item Column Name for AJE_PTRN */
    static final String COA_CH_CD_ITEM_CUST = "COA_CH_CD_ITEM_CUST";
    
    /** DB Item Column Name for AJE_PTRN */
    static final String COA_CH_CD_ITEM_PTRN = "COA_CH_CD_ITEM_PTRN";

    /** DB Item Column Name for AJE_PTRN */
    static final String INV_NUM = "INV_NUM";
    
    /** DB Item Column Name */
    static final String PRCH_PRC_AMT = "PRCH_PRC_AMT";
    
    /** DB Item Column Name */
    static final String SHIP_FROM_AMT = "SHIP_FROM_AMT";
    
    /** DB Item Column Name */
    static final String RMNF_WIP_AMT = "RMNF_WIP_AMT";
    
    /** DB Item Column Name */
    static final String DS_ACCT_NUM = "DS_ACCT_NUM";
    
    /** DB Item Column Name */
    static final String RTL_WH_CD = "RTL_WH_CD";
    
    /** DB Item Column Name */
    static final String ORIG_RTL_WH_CD = "ORIG_RTL_WH_CD";
    
    /** DB Item Column Name */
    static final String SER_NUM = "SER_NUM";
    
    /** DB Item Column Name */
    static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";
    
    /** DB Item Column Name */
    static final String ITEM_PROD_CD = "ITEM_PROD_CD";
    
    /** DB Item Column Name */
    static final String SLS_REP_CR_PCT = "SLS_REP_CR_PCT";
    
    /** DB Item Column Name */
    static final String PO_ORD_NUM = "PO_ORD_NUM";
    
    /** DB Item Column Name */
    static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";
    
    /** DB Item Column Name */
    static final String INVTY_TRX_SLP_NUM = "INVTY_TRX_SLP_NUM";
    
    /** Const Value */
    static final String CONST_PPS_FIRST_PROD_CTRL = "AJE_VAL_PPS_FIRST_PROD_CD";
    
    // 2017/09/28 S21_NA#19408 Add Start
    /** DB Item Column Attrb Name */
    static final String RTL_WH_CD_ATTRB_NM = "Warehouse Code";
    
    /** DB Item Column Attrb Name */
    static final String SLS_REP_CR_PCT_ATTRB_NM = "Sales Rep Allocation Ratio";
    
    /** DB Item Column Attrb Name */
    static final String PO_ORD_NUM_ATTRB_NM = "PO Number";
    
    /** DB Item Column Attrb Name */
    static final String PO_ORD_DTL_LINE_NUM_ATTRB_NM = "PO Line#";
    
    /** DB Item Column Attrb Name */
    static final String INVTY_TRX_SLP_NUM_ATTRB_NM = "Inventory Trx Ref Num";
    // 2017/09/28 S21_NA#19408 Add End
    // START 2018/02/08 E.Kameishi [QC#23191,ADD]
    /** DB Item Column Name */
    static final String SLS_REP_TOC_CD = "SLS_REP_TOC_CD";

    /** DB Item Column Attrb Name */
    static final String EMSD_REP_ATTRB_NM = "EMSD Rep";
    // END 2018/02/08 E.Kameishi [QC#23191,ADD]

    // START 2019/08/10 S.Takami [QC#51897,ADD]
    /** DB Item Column Service Config Master PK */
    static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";
    // END 2019/08/10 S.Takami [QC#51897,ADD]
}
