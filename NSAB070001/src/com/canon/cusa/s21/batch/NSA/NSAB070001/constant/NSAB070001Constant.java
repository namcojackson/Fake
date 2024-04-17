/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB070001.constant;

/**
 *<pre>
 * Monthly Contract Revenue Cost Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/18/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NSAB070001Constant {

    /** fetchSize */
    public static final int FETCH_SIZE = 1000;

    /** commitCount */
    public static final int DEFAULT_COMMIT_COUNT = 1000;

    /** [@] has no value. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Failed to insert "@". */
    public static final String ZZZM9012E = "ZZZM9012E";

    /** Failed to delete "@". */
    public static final String ZZZM9014E = "ZZZM9014E";

    /** message Item: GlobalCompanyCode. */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: Commit Transaction Count. */
    public static final String MSG_ITEM_COMMIT_TRANCNT = "Commit Transaction Count";

    /** message Item: Batch Process Date. */
    public static final String MSG_ITEM_BATCH_PROC_DATE = "Batch Process Date";

    /** SQL PARAM NAME */
    /** . */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** . */
    public static final String PARAM_TRGT_DT = "trgtDt";
    /** . */
    public static final String PARAM_REVSVC = "revsvc";
    /** . */
    public static final String PARAM_REVSPLY = "revsply";
    /** . */
    public static final String PARAM_COSTSVC = "costsvc";
    /** . */
    public static final String PARAM_COSTSPLY = "costsply";
    /** . */
    public static final String PARAM_CNTR_CTG_CD = "cntrCtgCd";
    /** . */
    public static final String PARAM_SER_NUM = "serNum";
    /** . */
    public static final String PARAM_MDL_NM = "mdlNm";
    /** . */
    public static final String PARAM_SEG_DESC_TXT = "segDescTxt";
    /** . */
    public static final String PARAM_INV_CHRG_TP_BC = "invChrgTpBC";
    /** . */
    public static final String PARAM_INV_CHRG_TP_MC = "invChrgTpMC";
    /** . */
    public static final String PARAM_INV_CHRG_TP_LC = "invChrgTpLC";
    /** . */
    public static final String PARAM_INV_CHRG_TP_PC = "invChrgTpPC";
    /** . */
    public static final String PARAM_INV_CHRG_TP_XC = "invChrgTpXC";
    /** . */
    public static final String PARAM_INV_CHRG_TP_TC = "invChrgTpTC";
    /** . */
    public static final String PARAM_INV_TP_DESC_BC = "invTpDescBC";
    /** . */
    public static final String PARAM_INV_TP_DESC_MC = "invTpDescMC";
    /** . */
    public static final String PARAM_HYPHEN = "hyphen";
    /** . */
    public static final String PARAM_DR_CR_TP_C = "drCrTpC";
    /** . */
    public static final String PARAM_MACB = "macb";
    /** . */
    public static final String PARAM_MACU = "macu";
    /** . */
    public static final String PARAM_MACBU = "macbu";
    /** . */
    public static final String PARAM_FLEET = "fleet";
    /** . */
    public static final String PARAM_SVC_MACH_TP = "svcMachTp";
    /** . */
    public static final String PARAM_HR = "hr";
    /** . */
    public static final String PARAM_EA = "ea";
    /** . */
    public static final String PARAM_MI = "mi";
    /** . */
    public static final String PARAM_BASE = "Base";
    /** . */
    public static final String PARAM_USAGE = "Usage";
    /** . */
    public static final String PARAM_CRAT_DAYS = "cratDays";
    /** . */
    public static final String PARAM_USG_TRX_TP = "usgTrxTp";
    /** . */
    public static final String PARAM_SVC_ACCT = "svcAcct";
    /** . */
    public static final String PARAM_USG_CHRG_TP = "usgChrgTp";
    /** . */
    public static final String PARAM_EXP_CHRG_TP = "expChrgTp";
    /** . */
    public static final String PARAM_LBOR_UNIT = "lborUnit";
    /** . */
    public static final String PARAM_RPT_COND_CONST_CD_01 = "rptCondCostCd01";

    /**  Value : REVSVC */
    public static final String VAL_REVSVC = "REVSVC";
    /**  Value : REVSPLY */
    public static final String VAL_REVSPLY = "REVSPLY";
    /**  Value : COSTSVC */
    public static final String VAL_COSTSVC = "COSTSVC";
    /**  Value : COSTSPLY */
    public static final String VAL_COSTSPLY = "COSTSPLY";
    /**  Value : HYPHEN */
    public static final String VAL_HYPHEN = "-";
    /**  Value : FLEET */
    public static final String VAL_FLEET = "Fleet";
    /**  Value : HR */
    public static final String VAL_HR = "HR";
    /**  Value : EA */
    public static final String VAL_EA = "EA";
    /**  Value : MI */
    public static final String VAL_MI = "MI";
    /**  Value : BASE */
    public static final String VAL_BASE = "Base";
    /**  Value : USAGE */
    public static final String VAL_USAGE = "Usage";
    /**  Value : NO_MODEL */
    public static final String VAL_NO_MODEL = "NO_MODEL";
    /**  Value : NO_SEGMENT */
    public static final String VAL_NO_SEGMENT = "NO_SEGMENT";
    /**  Value : NSAB0700_CRAT_DAYS */
    public static final String VAL_NSAB0700_CRAT_DAYS = "NSAB0700_CRAT_DAYS";
    /**  Value : USG_TRX_TP */
    public static final String VAL_USG_TRX_TP = "NSAB0700_USG_TRX_TP";
    /**  Value : SVC_ACCT */
    public static final String VAL_SVC_ACCT = "NSAB0700_SVC_ACCT";
    /**  Value : USG_CHRG_TP */
    public static final String VAL_USG_CHRG_TP = "NSAB0700_USG_CHRG_TP";
    /**  Value : EXP_CHRG_TP */
    public static final String VAL_EXP_CHRG_TP = "NSAB0700_EXP_CHRG_TP";
    /**  Value : LBOR_UNIT */
    public static final String VAL_LBOR_UNIT = "NSAB0700_LBOR_UNIT";
    /**  Value : 01 */
    public static final String VAL_01 = "01";
    /**  Value : DR_CR_TP_C */
    public static final String VAL_DR_CR_TP_C = "C";

    /** DB COLUMN NAME (FCT_DLY_CONTR_REV_COST)*/
    /** . */
    public static final String FACT_CRAT_DT = "FACT_CRAT_DT";
    /** . */
    public static final String DWH_TRGT_DT = "DWH_TRGT_DT";
    /** . */
    public static final String DWH_TRGT_YR_MTH = "DWH_TRGT_YR_MTH";
    /** . */
    public static final String DWH_TRGT_YR = "DWH_TRGT_YR";
    /** . */
    public static final String DWH_TRGT_QTR_NUM = "DWH_TRGT_QTR_NUM";
    /** . */
    public static final String FCT_CATG_CD = "FCT_CATG_CD";
    /** . */
    public static final String DS_CONTR_PK = "DS_CONTR_PK";
    /** . */
    public static final String DS_CONTR_NUM = "DS_CONTR_NUM";
    /** . */
    public static final String DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";
    /** . */
    public static final String DS_CONTR_DTL_TP_CD = "DS_CONTR_DTL_TP_CD";
    /** . */
    public static final String DS_CONTR_DTL_TP_DESC_TXT = "DS_CONTR_DTL_TP_DESC_TXT";
    /** . */
    public static final String SVC_CONTR_BR_CD = "SVC_CONTR_BR_CD";
    /** . */
    public static final String DS_CONTR_CATG_CD = "DS_CONTR_CATG_CD";
    /** . */
    public static final String DS_CONTR_CATG_DESC_TXT = "DS_CONTR_CATG_DESC_TXT";
    /** . */
    public static final String SVC_LINE_BIZ_CD = "SVC_LINE_BIZ_CD";
    /** . */
    public static final String DS_ACCT_NUM = "DS_ACCT_NUM";
    /** . */
    public static final String DS_ACCT_NM = "DS_ACCT_NM";
    /** . */
    public static final String ALT_PAYER_CUST_CD = "ALT_PAYER_CUST_CD";
    /** . */
    public static final String CUR_LOC_ACCT_NUM = "CUR_LOC_ACCT_NUM";
    /** . */
    public static final String CUR_LOC_ACCT_NM = "CUR_LOC_ACCT_NM";
    /** . */
    public static final String CUR_LOC_NUM = "CUR_LOC_NUM";
    /** . */
    public static final String IND_CUR_LOC_NUM = "IND_CUR_LOC_NUM";
    /** . */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";
    /** . */
    public static final String SER_NUM = "SER_NUM";
    /** . */
    public static final String MDL_ID = "MDL_ID";
    /** . */
    public static final String T_MDL_NM = "T_MDL_NM";
    /** . */
    public static final String SVC_SEG_CD = "SVC_SEG_CD";
    /** . */
    public static final String SVC_SEG_DESC_TXT = "SVC_SEG_DESC_TXT";
    /** . */
    public static final String COA_CMPY_CD = "COA_CMPY_CD";
    /** . */
    public static final String COA_AFFL_CD = "COA_AFFL_CD";
    /** . */
    public static final String COA_BR_CD = "COA_BR_CD";
    /** . */
    public static final String COA_CH_CD = "COA_CH_CD";
    /** . */
    public static final String COA_CC_CD = "COA_CC_CD";
    /** . */
    public static final String COA_ACCT_CD = "COA_ACCT_CD";
    /** . */
    public static final String COA_PROD_CD = "COA_PROD_CD";
    /** . */
    public static final String COA_PROJ_CD = "COA_PROJ_CD";
    /** . */
    public static final String COA_EXTN_CD = "COA_EXTN_CD";
    /** . */
    public static final String MDSE_CD = "MDSE_CD";
    /** . */
    public static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";
    /** . */
    public static final String SHIP_QTY = "SHIP_QTY";
    /** . */
    public static final String UNIT_COST_AMT = "UNIT_COST_AMT";
    /** . */
    public static final String SHIP_CPLT_COST_AMT = "SHIP_CPLT_COST_AMT";
    /** . */
    public static final String SVC_INV_CHRG_TP_CD = "SVC_INV_CHRG_TP_CD";
    /** . */
    public static final String SVC_INV_CHRG_TP_DESC_TXT = "SVC_INV_CHRG_TP_DESC_TXT";
    /** . */
    public static final String DS_INV_TP_CD = "DS_INV_TP_CD";
    /** . */
    public static final String INV_TP_DESC_TXT = "INV_TP_DESC_TXT";
    /** . */
    public static final String SVC_INV_UNIT_HRS_AOT = "SVC_INV_UNIT_HRS_AOT";
    /** . */
    public static final String SVC_LBOR_UNIT_AMT = "SVC_LBOR_UNIT_AMT";
    /** . */
    public static final String INV_NUM = "INV_NUM";
    /** . */
    public static final String INV_BOL_LINE_NUM = "INV_BOL_LINE_NUM";
    /** . */
    public static final String INV_LINE_NUM = "INV_LINE_NUM";
    /** . */
    public static final String INV_LINE_SUB_NUM = "INV_LINE_SUB_NUM";
    /** . */
    public static final String INV_LINE_SUB_TRX_NUM = "INV_LINE_SUB_TRX_NUM";
    /** . */
    public static final String SVC_INV_LINE_PK = "SVC_INV_LINE_PK";
    /** . */
    public static final String INV_DT = "INV_DT";
    /** . */
    public static final String ACCT_DT = "ACCT_DT";
    /** . */
    public static final String BW_MTR_CHRG_DEAL_AMT = "BW_MTR_CHRG_DEAL_AMT";
    /** . */
    public static final String BW_COST_AMT = "BW_COST_AMT";
    /** . */
    public static final String CLR_MTR_CHRG_DEAL_AMT = "CLR_MTR_CHRG_DEAL_AMT";
    /** . */
    public static final String CLR_COST_AMT = "CLR_COST_AMT";
    /** . */
    public static final String OTH_INV_LINE_DEAL_NET_AMT = "OTH_INV_LINE_DEAL_NET_AMT";
    /** . */
    public static final String OTH_COST_AMT = "OTH_COST_AMT";
    /** . */
    public static final String BLLG_PER_FROM_DT = "BLLG_PER_FROM_DT";
    /** . */
    public static final String BLLG_PER_THRU_DT = "BLLG_PER_THRU_DT";
    /** . */
    public static final String ACTL_MACH_QTY = "ACTL_MACH_QTY";
    /** . */
    public static final String ORD_QTY = "ORD_QTY";
    /** . */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";
    /** . */
    public static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";
    /** . */
    public static final String CPO_ORD_DT = "CPO_ORD_DT";
    /** . */
    public static final String IMG_SPLY_YIELD_CNT = "IMG_SPLY_YIELD_CNT";
    /** . */
    public static final String SPLY_INCL_FLG = "SPLY_INCL_FLG";
    /** . */
    public static final String SVC_PGM_MDSE_CD = "SVC_PGM_MDSE_CD";
    /** . */
    public static final String FSR_NUM = "FSR_NUM";
    /** . */
    public static final String FSR_CRAT_DT = "FSR_CRAT_DT";
    /** . */
    public static final String INV_LINE_SPL_TP_CD = "INV_LINE_SPL_TP_CD";
    /** . */
    public static final String INV_LINE_SPL_TP_DESC_TXT = "INV_LINE_SPL_TP_DESC_TXT";
    /** . */
    public static final String FSR_VISIT_NUM = "FSR_VISIT_NUM";
    /** . */
    public static final String SVC_TASK_CLO_DT = "SVC_TASK_CLO_DT";
    /** . */
    public static final String PRT_USED_BY_TECH_LOC_CD = "PRT_USED_BY_TECH_LOC_CD";
    /** . */
    public static final String UOM_CD = "UOM_CD";
    /** . */
    public static final String SVC_TASK_NUM = "SVC_TASK_NUM";
    /** . */
    public static final String SVC_PBLM_TP_CD = "SVC_PBLM_TP_CD";
    /** . */
    public static final String SVC_PBLM_TP_DESC_TXT = "SVC_PBLM_TP_DESC_TXT";
    /** . */
    public static final String IMG_SPLY_TP_CD = "IMG_SPLY_TP_CD";
    /** . */
    public static final String IMG_SPLY_TP_DESC_TXT = "IMG_SPLY_TP_DESC_TXT";
    /** . */
    public static final String IMG_SPLY_COLOR_TP_CD = "IMG_SPLY_COLOR_TP_CD";
    /** . */
    public static final String IMG_SPLY_COLOR_TP_DESC_TXT = "IMG_SPLY_COLOR_TP_DESC_TXT";
    /** . */
    public static final String USED_MULT_CNT = "USED_MULT_CNT";
    /** . */
    public static final String USED_EQUA_CNT = "USED_EQUA_CNT";
    /** . */
    public static final String COA_CMPY_DESC_TXT = "COA_CMPY_DESC_TXT";

}
