/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB074001.constant;

/**
 *<pre>
 * Monthly Contract Supply Project Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/18/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NSAB074001Constant {

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
    public static final String PARAM_INV_CHRG_TP_MC = "invChrgTpMC";
    /** . */
    public static final String PARAM_CRAT_DAYS = "cratDays";
    /** . */
    public static final String PARAM_AVE_MTH = "aveMth";
    /** . */
    public static final String PARAM_CNT = "cnt";

    /**  Value : FLEET */
    public static final String VAL_FLEET = "Fleet";
    /**  Value : NO_MODEL */
    public static final String VAL_NO_MODEL = "NO_MODEL";
    /**  Value : NO_SEGMENT */
    public static final String VAL_NO_SEGMENT = "NO_SEGMENT";
    /**  Value : NSAB0740_CRAT_DAYS */
    public static final String VAL_NSAB0740_CRAT_DAYS = "NSAB0740_CRAT_DAYS";
    /**  Value : AVE_MTH */
    public static final String VAL_AVE_MTH = "30.41";

    /** DB COLUMN NAME (FCT_MLY_CONTR_REV_COST)*/
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
    public static final String COA_PROD_CD = "COA_PROD_CD";
    /** . */
    public static final String MDSE_CD = "MDSE_CD";
    /** . */
    public static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";
    /** . */
    public static final String UNIT_COST_AMT = "UNIT_COST_AMT";
    /** . */
    public static final String INV_NUM = "INV_NUM";
    /** . */
    public static final String SVC_INV_LINE_PK = "SVC_INV_LINE_PK";
    /** . */
    public static final String INV_DT = "INV_DT";
    /** . */
    public static final String BW_COPY_QTY = "BW_COPY_QTY";
    /** . */
    public static final String BW_PROJ_SPLY_QTY = "BW_PROJ_SPLY_QTY";
    /** . */
    public static final String BW_COST_RATE = "BW_COST_RATE";
    /** . */
    public static final String CLR_COPY_QTY = "CLR_COPY_QTY";
    /** . */
    public static final String CLR_PROJ_SPLY_QTY = "CLR_PROJ_SPLY_QTY";
    /** . */
    public static final String CLR_COST_RATE = "CLR_COST_RATE";
    /** . */
    public static final String TOT_MTR_COPY_QTY = "TOT_MTR_COPY_QTY";
    /** . */
    public static final String BLLG_PER_FROM_DT = "BLLG_PER_FROM_DT";
    /** . */
    public static final String BLLG_PER_THRU_DT = "BLLG_PER_THRU_DT";
    /** . */
    public static final String IMG_SPLY_YIELD_CNT = "IMG_SPLY_YIELD_CNT";
    /** . */
    public static final String SPLY_INCL_FLG = "SPLY_INCL_FLG";
    /** . */
    public static final String IMG_SPLY_TP_CD = "IMG_SPLY_TP_CD";
    /** . */
    public static final String IMG_SPLY_TP_DESC_TXT = "IMG_SPLY_TP_DESC_TXT";
    /** . */
    public static final String IMG_SPLY_COLOR_TP_CD = "IMG_SPLY_COLOR_TP_CD";
    /** . */
    public static final String IMG_SPLY_COLOR_TP_DESC_TXT = "IMG_SPLY_COLOR_TP_DESC_TXT";
    /** . */
    public static final String BLLG_CYCLE_MTH_AOT = "BLLG_CYCLE_MTH_AOT";
    /** . */
    public static final String SVC_PGM_MDSE_CD = "SVC_PGM_MDSE_CD";
    /** . */
    public static final String COA_CMPY_DESC_TXT = "COA_CMPY_DESC_TXT";

}
