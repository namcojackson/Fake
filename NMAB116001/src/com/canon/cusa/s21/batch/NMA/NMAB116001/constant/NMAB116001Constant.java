/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB116001.constant;

/**
 *<pre>
 * Warehouse Dimension Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NMAB116001Constant {

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

    /** message Item: Batch Process Date. */
    public static final String MSG_ITEM_BATCH_PROC_DATE = "Batch Process Date";

    /** SSM set Key Name: RTL_SWH_DSBL_FLG */
    public static final String KEY_RTL_SWH_DSBL_FLG = "rtlSwhDsblFlg";

    /** SSM set Key Name: RTL_WH_RPT_RG */
    public static final String KEY_RTL_WH_RPT_RG = "rtlWhRptRg";

    /** SSM set Key Name: KEY_RTL_WH_RPT_OP_ID */
    public static final String KEY_RTL_WH_RPT_OP_ID = "rtlWhRptOpId";

    /** SSM set Key Name: DFLT_RTL_WH_RPT_OP_ID */
    public static final String KEY_DFLT_RTL_WH_RPT_OP_ID = "dfltRtlWhRptOpId";

    /** SSM set Key Name: DFLT_RTL_WH_RPT_OP_ID */
    public static final String KEY_DFLT_RTL_WH_RPT_RG_TXT = "dfltRtlWhRptRgTxt";

    /**  Value : 999 */
    public static final String VAL_999 = "999";

    /**  Value : UNDEFINED */
    public static final String VAL_UNDEFINED = "UNDEFINED";

    /**  Value : "RTL_WH_RPT_RG" */
    public static final String VAL_RTL_WH_RPT_RG = "NMAB1160_RPT_RG";

    /**  Value : "RTL_WH_RPT_OP_ID" */
    public static final String VAL_RTL_WH_RPT_OP_ID = "NMAB1160_RPT_OP_ID";

    /** SQL PARAM NAME */
    /** . */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** . */
    public static final String PARAM_TRGT_DT = "trgtDt";

    /** DB COLUMN NAME */
    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** . */
    public static final String DWH_TRGT_DT = "DWH_TRGT_DT";
    /** . */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";
    /** . */
    public static final String RTL_WH_CD = "RTL_WH_CD";
    /** . */
    public static final String RTL_SWH_CD = "RTL_SWH_CD";
    /** . */
    public static final String RTL_WH_NM = "RTL_WH_NM";
    /** . */
    public static final String RTL_SWH_NM = "RTL_SWH_NM";
    /** . */
    public static final String LOC_TP_CD = "LOC_TP_CD";
    /** . */
    public static final String TECH_TOC_CD = "TECH_TOC_CD";
    /** . */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";
    /** . */
    public static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";
    /** . */
    public static final String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";
    /** . */
    public static final String SCD_LINE_ADDR = "SCD_LINE_ADDR";
    /** . */
    public static final String THIRD_LINE_ADDR = "THIRD_LINE_ADDR";
    /** . */
    public static final String FRTH_LINE_ADDR = "FRTH_LINE_ADDR";
    /** . */
    public static final String CTY_ADDR = "CTY_ADDR";
    /** . */
    public static final String CNTY_PK = "CNTY_PK";
    /** . */
    public static final String PROV_NM = "PROV_NM";
    /** . */
    public static final String ST_CD = "ST_CD";
    /** . */
    public static final String POST_CD = "POST_CD";
    /** . */
    public static final String CTRY_CD = "CTRY_CD";
    /** . */
    public static final String ADDL_LOC_NM = "ADDL_LOC_NM";
    /** . */
    public static final String FAX_NUM = "FAX_NUM";
    /** . */
    public static final String TEL_NUM = "TEL_NUM";
    /** . */
    public static final String ATTN_NM = "ATTN_NM";
    /** . */
    public static final String RTL_WH_EFF_FROM_DT = "RTL_WH_EFF_FROM_DT";
    /** . */
    public static final String RTL_WH_EFF_THRU_DT = "RTL_WH_EFF_THRU_DT";
    /** . */
    public static final String COA_BR_CD = "COA_BR_CD";
    /** . */
    public static final String COA_BR_DESC_TXT = "COA_BR_DESC_TXT";
    /** . */
    public static final String COA_BR_RG_CD = "COA_BR_RG_CD";
    /** . */
    public static final String COA_BR_RG_DESC_TXT = "COA_BR_RG_DESC_TXT";
    /** . */
    public static final String COA_BR_ZN_CD = "COA_BR_ZN_CD";
    /** . */
    public static final String COA_BR_ZN_DESC_TXT = "COA_BR_ZN_DESC_TXT";
    /** . */
    public static final String RTL_WH_RPT_OP_ID = "RTL_WH_RPT_OP_ID";
    /** . */
    public static final String RTL_WH_RPT_RG_TXT = "RTL_WH_RPT_RG_TXT";
    /** . */
    public static final String RTL_SWH_DSBL_FLG = "RTL_SWH_DSBL_FLG";
    /** . */
    public static final String RTL_SWH_EFF_FROM_DT = "RTL_SWH_EFF_FROM_DT";
    /** . */
    public static final String RTL_SWH_EFF_THRU_DT = "RTL_SWH_EFF_THRU_DT";
    /** . */
    public static final String RTL_WH_CATG_CD = "RTL_WH_CATG_CD";
    /** . */
    public static final String RTL_WH_CATG_DESC_TXT = "RTL_WH_CATG_DESC_TXT";
    /** . */
    public static final String PHYS_WH_CD = "PHYS_WH_CD";
    /** . */
    public static final String WMS_WH_CD = "WMS_WH_CD";
    /** . */
    public static final String WMS_DESC_NM = "WMS_DESC_NM";
    /** . */
    public static final String WH_MGR_PSN_CD = "WH_MGR_PSN_CD";
    /** . */
    public static final String WH_MGR_PSN_FULL_NM = "WH_MGR_PSN_FULL_NM";
    /** . */
    public static final String ALT_PSN_CD = "ALT_PSN_CD";
    /** . */
    public static final String ALT_PSN_FULL_NM = "ALT_PSN_FULL_NM";
    /** . */
    public static final String INVTY_ACCT_CD = "INVTY_ACCT_CD";
    /** . */
    public static final String INVTY_ACCT_DESC_TXT = "INVTY_ACCT_DESC_TXT";
    /** . */
    public static final String INVTY_OWNR_CD = "INVTY_OWNR_CD";
    /** . */
    public static final String INVTY_OWNR_DESC_TXT = "INVTY_OWNR_DESC_TXT";
    /** . */
    public static final String CMPY_INVTY_FLG = "CMPY_INVTY_FLG";
    /** . */
    public static final String WH_OWNR_CD = "WH_OWNR_CD";
    /** . */
    public static final String WH_OWNR_DESC_TXT = "WH_OWNR_DESC_TXT";
    /** . */
    public static final String FIRST_REF_CMNT_TXT = "FIRST_REF_CMNT_TXT";
    /** . */
    public static final String SCD_REF_CMNT_TXT = "SCD_REF_CMNT_TXT";
    /** . */
    public static final String AUTO_SO_DROP_FLG = "AUTO_SO_DROP_FLG";
    /** . */
    public static final String SO_PRIN_REQ_FLG = "SO_PRIN_REQ_FLG";
    /** . */
    public static final String RWS_PRIN_REQ_FLG = "RWS_PRIN_REQ_FLG";
    /** . */
    public static final String WH_SYS_TP_CD = "WH_SYS_TP_CD";
    /** . */
    public static final String WH_SYS_TP_DESC_TXT = "WH_SYS_TP_DESC_TXT";
    /** . */
    public static final String WH_START_TM = "WH_START_TM";
    /** . */
    public static final String WH_END_TM = "WH_END_TM";
    /** . */
    public static final String WH_CUT_OFF_TM = "WH_CUT_OFF_TM";
    /** . */
    public static final String TM_ZONE_CD = "TM_ZONE_CD";
    /** . */
    public static final String GEO_CD = "GEO_CD";
    /** . */
    public static final String SRC_ZN_CD = "SRC_ZN_CD";
    /** . */
    public static final String DEF_SRC_PROCR_TP_CD = "DEF_SRC_PROCR_TP_CD";
    /** . */
    public static final String DEF_SRC_PROCR_TP_DESC_TXT = "DEF_SRC_PROCR_TP_DESC_TXT";
    /** . */
    public static final String DEF_SRC_LOC_CD = "DEF_SRC_LOC_CD";
    /** . */
    public static final String DEF_SRC_RTL_WH_CD = "DEF_SRC_RTL_WH_CD";
    /** . */
    public static final String DEF_SRC_RTL_SWH_CD = "DEF_SRC_RTL_SWH_CD";
    /** . */
    public static final String EMER_SRC_PROCR_TP_CD = "EMER_SRC_PROCR_TP_CD";
    /** . */
    public static final String EMER_SRC_PROCR_TP_DESC_TXT = "EMER_SRC_PROCR_TP_DESC_TXT";
    /** . */
    public static final String EMER_SRC_LOC_CD = "EMER_SRC_LOC_CD";
    /** . */
    public static final String EMER_SRC_RTL_WH_CD = "EMER_SRC_RTL_WH_CD";
    /** . */
    public static final String EMER_SRC_RTL_SWH_CD = "EMER_SRC_RTL_SWH_CD";
    /** . */
    public static final String DEF_RTRN_TO_PROCR_TP_CD = "DEF_RTRN_TO_PROCR_TP_CD";
    /** . */
    public static final String DEF_RTRN_PROCR_TP_DESC_TXT = "DEF_RTRN_PROCR_TP_DESC_TXT";
    /** . */
    public static final String DEF_RTRN_TO_LOC_CD = "DEF_RTRN_TO_LOC_CD";
    /** . */
    public static final String DEF_RTRN_TO_RTL_WH_CD = "DEF_RTRN_TO_RTL_WH_CD";
    /** . */
    public static final String DEF_RTRN_TO_RTL_SWH_CD = "DEF_RTRN_TO_RTL_SWH_CD";
    /** . */
    public static final String PRF_CARR_CD = "PRF_CARR_CD";
    /** . */
    public static final String PLN_ITEM_INSRC_CD = "PLN_ITEM_INSRC_CD";
    /** . */
    public static final String PLN_ITEM_INSRC_DESC_TXT = "PLN_ITEM_INSRC_DESC_TXT";
    /** . */
    public static final String GND_SVC_LVL_CD = "GND_SVC_LVL_CD";
    /** . */
    public static final String FIRST_OVNGT_SVC_LVL_CD = "FIRST_OVNGT_SVC_LVL_CD";
    /** . */
    public static final String SCD_OVNGT_SVC_LVL_CD = "SCD_OVNGT_SVC_LVL_CD";
    /** . */
    public static final String RTRN_TO_CUST_CD = "RTRN_TO_CUST_CD";
    /** . */
    public static final String RTRN_TO_LOC_NM = "RTRN_TO_LOC_NM";
    /** . */
    public static final String RTRN_TO_FIRST_LINE_ADDR = "RTRN_TO_FIRST_LINE_ADDR";
    /** . */
    public static final String RTRN_TO_SCD_LINE_ADDR = "RTRN_TO_SCD_LINE_ADDR";
    /** . */
    public static final String RTRN_TO_THIRD_LINE_ADDR = "RTRN_TO_THIRD_LINE_ADDR";
    /** . */
    public static final String RTRN_TO_FRTH_LINE_ADDR = "RTRN_TO_FRTH_LINE_ADDR";
    /** . */
    public static final String RTRN_TO_CTY_ADDR = "RTRN_TO_CTY_ADDR";
    /** . */
    public static final String RTRN_TO_CNTY_PK = "RTRN_TO_CNTY_PK";
    /** . */
    public static final String RTRN_TO_PROV_NM = "RTRN_TO_PROV_NM";
    /** . */
    public static final String RTRN_TO_ST_CD = "RTRN_TO_ST_CD";
    /** . */
    public static final String RTRN_TO_POST_CD = "RTRN_TO_POST_CD";
    /** . */
    public static final String RTRN_TO_CTRY_CD = "RTRN_TO_CTRY_CD";
    /** . */
    public static final String RTRN_TO_ADDL_LOC_NM = "RTRN_TO_ADDL_LOC_NM";
    /** . */
    public static final String RTRN_TO_FAX_NUM = "RTRN_TO_FAX_NUM";
    /** . */
    public static final String RTRN_TO_TEL_NUM = "RTRN_TO_TEL_NUM";
    /** . */
    public static final String RTRN_TO_ATTN_NM = "RTRN_TO_ATTN_NM";
    /** . */
    public static final String PO_RCPT_RTE_TP_CD = "PO_RCPT_RTE_TP_CD";
    /** . */
    public static final String PO_RCPT_RTE_TP_DESC_TXT = "PO_RCPT_RTE_TP_DESC_TXT";
    /** . */
    public static final String RMA_RCPT_RTE_TP_CD = "RMA_RCPT_RTE_TP_CD";
    /** . */
    public static final String RMA_RCPT_RTE_TP_DESC_TXT = "RMA_RCPT_RTE_TP_DESC_TXT";

}
