/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB069001.constant;

/**
 *<pre>
 * Machine Master Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/16/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NSAB069001Constant {

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

    /** message Item: DWH Target Year Month. */
    public static final String MSG_ITEM_DWH_TRGT_YR_MTH = "DWH Target Year Month";

    /** SSM set Key Name: KEY_SVC_MACH_TP_CD*/
    public static final String KEY_SVC_MACH_TP_CD = "svcMachTpCd";

    /** SSM set Key Name: KEY_SVC_MACH_MSTR_STS_1*/
    public static final String KEY_SVC_MACH_MSTR_STS_1 = "svcMachMstrSts1";

    /** SSM set Key Name: KEY_SVC_MACH_MSTR_STS_2*/
    public static final String KEY_SVC_MACH_MSTR_STS_2 = "svcMachMstrSts2";

    /** SSM set Key Name: KEY_SVC_MACH_MSTR_STS_3*/
    public static final String KEY_SVC_MACH_MSTR_STS_3 = "svcMachMstrSts3";

    /** SSM set Key Name: KEY_SVC_MACH_MSTR_STS_4*/
    public static final String KEY_SVC_MACH_MSTR_STS_4 = "svcMachMstrSts4";

    /** SSM set Key Name: KEY_SVC_MACH_MSTR_STS_TRMN*/
    public static final String KEY_SVC_MACH_MSTR_STS_TRMN = "svcMachMstrStsTrmn";

    /** SSM set Key Name: KEY_DS_CONTR_DTL_STS_CD*/
    public static final String KEY_DS_CONTR_DTL_STS_CD = "dsContrDtlStsCd";

    /** SSM set Key Name: KEY_SVC_TERM_COND_ATTRB*/
    public static final String KEY_SVC_TERM_COND_ATTRB = "svcTermCondAttrb";

    /** SSM set Key Name: KEY_ORG_STRU_CD*/
    public static final String KEY_ORG_STRU_CD = "orgStruCd";

    /** SSM set Key Name: KEY_FST_ORG_CD*/
    public static final String KEY_FST_ORG_CD = "fstOrgCd";

    /** SSM set Key Name: KEY_PHONE_WORK*/
    public static final String KEY_PHONE_WORK = "phoneWork";

    /** SSM set Key Name: KEY_EMAIL_WORK*/
    public static final String KEY_EMAIL_WORK = "emailWork";

    /** SSM set Key Name: KEY_PHONE_MOBILE*/
    public static final String KEY_PHONE_MOBILE = "phoneMobile";

    /** SSM set Key Name: KEY_FAX_WORK*/
    public static final String KEY_FAX_WORK = "faxWork";

    /** SSM set Key Name: KEY_SVC_KEY_OPERATOR*/
    public static final String KEY_SVC_KEY_OPERATOR = "svckeyOpe";

    /**  Value : 2 */
    public static final String VAL_2 = "2";

    /**  Value : 7 */
    public static final String VAL_7 = "7";

    /** SQL PARAM NAME */
    /** . */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** . */
    public static final String PARAM_TRGT_DT = "trgtDt";
    /** . */
    public static final String PARAM_TRGT_YR_MTH = "trgtYrMth";

    /** DB COLUMN NAME */
    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** . */
    public static final String FCT_MLY_MACH_MSTR_PK = "FCT_MLY_MACH_MSTR_PK";
    /** . */
    public static final String DWH_TRGT_YR_MTH = "DWH_TRGT_YR_MTH";
    /** . */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";
    /** . */
    public static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";
    /** . */
    public static final String MDL_ID = "MDL_ID";
    /** . */
    public static final String T_MDL_NM = "T_MDL_NM";
    /** . */
    public static final String SER_NUM = "SER_NUM";
    /** . */
    public static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";
    /** . */
    public static final String MDSE_CD = "MDSE_CD";
    /** . */
    public static final String MTR_GRP_PK = "MTR_GRP_PK";
    /** . */
    public static final String MTR_GRP_DESC_TXT = "MTR_GRP_DESC_TXT";
    /** . */
    public static final String COA_PROD_CD = "COA_PROD_CD";
    /** . */
    public static final String COA_PROD_DESC_TXT = "COA_PROD_DESC_TXT";
    /** . */
    public static final String EFF_FROM_DT = "EFF_FROM_DT";
    /** . */
    public static final String EFF_THRU_DT = "EFF_THRU_DT";
    /** . */
    public static final String SVC_MACH_MSTR_STS_CD = "SVC_MACH_MSTR_STS_CD";
    /** . */
    public static final String SVC_MACH_MSTR_STS_DESC_TXT = "SVC_MACH_MSTR_STS_DESC_TXT";
    /** . */
    public static final String SVC_CMNT_TXT = "SVC_CMNT_TXT";
    /** . */
    public static final String SVC_TRTY_CD = "SVC_TRTY_CD";
    /** . */
    public static final String SVC_TRTY_DESC_TXT = "SVC_TRTY_DESC_TXT";
    /** . */
    public static final String OWNR_ACCT_NUM = "OWNR_ACCT_NUM";
    /** . */
    public static final String OWNR_ACCT_NM = "OWNR_ACCT_NM";
    /** . */
    public static final String OWNR_LOC_NUM = "OWNR_LOC_NUM";
    /** . */
    public static final String BILL_TO_ACCT_NUM = "BILL_TO_ACCT_NUM";
    /** . */
    public static final String BILL_TO_ACCT_NM = "BILL_TO_ACCT_NM";
    /** . */
    public static final String BILL_TO_LOC_NUM = "BILL_TO_LOC_NUM";
    /** . */
    public static final String IND_BILL_TO_LOC_NUM = "IND_BILL_TO_LOC_NUM";
    /** . */
    public static final String CUR_LOC_ACCT_NUM = "CUR_LOC_ACCT_NUM";
    /** . */
    public static final String CUR_LOC_ACCT_NM = "CUR_LOC_ACCT_NM";
    /** . */
    public static final String CUR_LOC_NUM = "CUR_LOC_NUM";
    /** . */
    public static final String IND_CUR_LOC_NUM = "IND_CUR_LOC_NUM";
    /** . */
    public static final String DS_KEY_ACCT_FLG = "DS_KEY_ACCT_FLG";
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
    public static final String ISTL_DT = "ISTL_DT";
    /** . */
    public static final String ISTL_AUTH_NUM = "ISTL_AUTH_NUM";
    /** . */
    public static final String FLD_SVC_BR_CD = "FLD_SVC_BR_CD";
    /** . */
    public static final String FIN_BR_CD = "FIN_BR_CD";
    /** . */
    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";
    /** . */
    public static final String CUST_ISS_PO_DT = "CUST_ISS_PO_DT";
    /** . */
    public static final String DS_PO_EXPR_DT = "DS_PO_EXPR_DT";
    /** . */
    public static final String DS_CONTR_NUM = "DS_CONTR_NUM";
    /** . */
    public static final String DS_CONTR_CATG_CD = "DS_CONTR_CATG_CD";
    /** . */
    public static final String DS_CONTR_CATG_DESC_TXT = "DS_CONTR_CATG_DESC_TXT";
    /** . */
    public static final String CONTR_VRSN_EFF_FROM_DT = "CONTR_VRSN_EFF_FROM_DT";
    /** . */
    public static final String CONTR_VRSN_EFF_THRU_DT = "CONTR_VRSN_EFF_THRU_DT";
    /** . */
    public static final String TRGT_SVC_RSP_VAL_CD = "TRGT_SVC_RSP_VAL_CD";
    /** . */
    public static final String TRGT_SVC_RSTR_VAL_CD = "TRGT_SVC_RSTR_VAL_CD";
    /** . */
    public static final String BIZ_HRS_SUN_FROM_TM = "BIZ_HRS_SUN_FROM_TM";
    /** . */
    public static final String BIZ_HRS_SUN_TO_TM = "BIZ_HRS_SUN_TO_TM";
    /** . */
    public static final String BIZ_HRS_MON_FRI_FROM_TM = "BIZ_HRS_MON_FRI_FROM_TM";
    /** . */
    public static final String BIZ_HRS_MON_FRI_TO_TM = "BIZ_HRS_MON_FRI_TO_TM";
    /** . */
    public static final String BIZ_HRS_SAT_FROM_TM = "BIZ_HRS_SAT_FROM_TM";
    /** . */
    public static final String BIZ_HRS_SAT_TO_TM = "BIZ_HRS_SAT_TO_TM";
    /** . */
    public static final String LEASE_END_DT = "LEASE_END_DT";
    /** . */
    public static final String INTFC_TO_FA_FLG = "INTFC_TO_FA_FLG";
    /** . */
    public static final String LTST_FSR_NUM = "LTST_FSR_NUM";
    /** . */
    public static final String LTST_FSR_CRAT_DT = "LTST_FSR_CRAT_DT";
    /** . */
    public static final String LTST_FSR_VISIT_ARV_DT = "LTST_FSR_VISIT_ARV_DT";
    /** . */
    public static final String LTST_VISIT_TECH_CD = "LTST_VISIT_TECH_CD";
    /** . */
    public static final String CUST_MACH_CTRL_NUM = "CUST_MACH_CTRL_NUM";
    /** . */
    public static final String SVC_MACH_TP_CD = "SVC_MACH_TP_CD";
    /** . */
    public static final String SHIP_FROM_WH_CD = "SHIP_FROM_WH_CD";
    /** . */
    public static final String SVC_MACH_RMV_DT = "SVC_MACH_RMV_DT";
    /** . */
    public static final String RTRN_TO_WH_CD = "RTRN_TO_WH_CD";
    /** . */
    public static final String DRUM_EXCH_DT = "DRUM_EXCH_DT";
    /** . */
    public static final String EXCH_DRUM_MTR_CNT = "EXCH_DRUM_MTR_CNT";
    /** . */
    public static final String LAST_SVC_CALL_DT = "LAST_SVC_CALL_DT";
    /** . */
    public static final String TOT_SVC_VISIT_CNT = "TOT_SVC_VISIT_CNT";
    /** . */
    public static final String LAST_TECH_VISIT_DT = "LAST_TECH_VISIT_DT";
    /** . */
    public static final String LAST_TECH_VISIT_MTR_CNT = "LAST_TECH_VISIT_MTR_CNT";
    /** . */
    public static final String LAST_PRVNT_MAINT_DT = "LAST_PRVNT_MAINT_DT";
    /** . */
    public static final String LAST_PRVNT_MAINT_MTR_CNT = "LAST_PRVNT_MAINT_MTR_CNT";
    /** . */
    public static final String LAST_SVC_CALL_VISIT_DT = "LAST_SVC_CALL_VISIT_DT";
    /** . */
    public static final String LAST_SVC_CALL_MTR_CNT = "LAST_SVC_CALL_MTR_CNT";
    /** . */
    public static final String PRF_TECH_CD = "PRF_TECH_CD";
    /** . */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";
    /** . */
    public static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";
    /** . */
    public static final String CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";
    /** . */
    public static final String SHPG_PLN_NUM = "SHPG_PLN_NUM";
    /** . */
    public static final String SO_NUM = "SO_NUM";
    /** . */
    public static final String SO_SLP_NUM = "SO_SLP_NUM";
    /** . */
    public static final String RMA_NUM = "RMA_NUM";
    /** . */
    public static final String RMA_LINE_NUM = "RMA_LINE_NUM";
    /** . */
    public static final String RMA_LINE_SUB_NUM = "RMA_LINE_SUB_NUM";
    /** . */
    public static final String RMA_LINE_SUB_TRX_NUM = "RMA_LINE_SUB_TRX_NUM";
    /** . */
    public static final String AUTO_CRAT_FLG = "AUTO_CRAT_FLG";
    /** . */
    public static final String OLD_SER_NUM = "OLD_SER_NUM";
    /** . */
    public static final String ISTL_STS_UPD_CPLT_FLG = "ISTL_STS_UPD_CPLT_FLG";
    /** . */
    public static final String SVC_MACH_USG_STS_CD = "SVC_MACH_USG_STS_CD";
    /** . */
    public static final String SVC_MACH_USG_STS_DESC_TXT = "SVC_MACH_USG_STS_DESC_TXT";
    /** . */
    public static final String SVC_BY_LINE_BIZ_TP_CD = "SVC_BY_LINE_BIZ_TP_CD";
    /** . */
    public static final String REQ_TECH_CD = "REQ_TECH_CD";
    /** . */
    public static final String STK_STS_CD = "STK_STS_CD";
    /** . */
    public static final String PRNT_SER_NUM = "PRNT_SER_NUM";
    /** . */
    public static final String SVC_MACH_TRX_TP_CD = "SVC_MACH_TRX_TP_CD";
    /** . */
    public static final String SVC_MACH_TRX_TP_DESC_TXT = "SVC_MACH_TRX_TP_DESC_TXT";
    /** . */
    public static final String SVC_MACH_QTY = "SVC_MACH_QTY";
    /** . */
    public static final String CTRL_FLD_TXT_01 = "CTRL_FLD_TXT_01";
    /** . */
    public static final String CTRL_FLD_TXT_02 = "CTRL_FLD_TXT_02";
    /** . */
    public static final String CTRL_FLD_TXT_03 = "CTRL_FLD_TXT_03";
    /** . */
    public static final String CTRL_FLD_TXT_04 = "CTRL_FLD_TXT_04";
    /** . */
    public static final String CTRL_FLD_TXT_05 = "CTRL_FLD_TXT_05";
    /** . */
    public static final String CTRL_FLD_TXT_06 = "CTRL_FLD_TXT_06";
    /** . */
    public static final String PRC_CONTR_NUM = "PRC_CONTR_NUM";
    /** . */
    public static final String CORP_ADVTG_STS_CD = "CORP_ADVTG_STS_CD";
    /** . */
    public static final String SVC_CORP_ADVTG_STS_DESC_TXT = "SVC_CORP_ADVTG_STS_DESC_TXT";
    /** . */
    public static final String HARD_DRIVE_RMV_INCL_FLG = "HARD_DRIVE_RMV_INCL_FLG";
    /** . */
    public static final String HARD_DRIVE_ERASE_INCL_FLG = "HARD_DRIVE_ERASE_INCL_FLG";
    /** . */
    public static final String LEASE_RTRN_FEE_INCL_FLG = "LEASE_RTRN_FEE_INCL_FLG";
    /** . */
    public static final String SVC_NTWK_CONN_STS_CD = "SVC_NTWK_CONN_STS_CD";
    /** . */
    public static final String SVC_NTWK_CONN_STS_DESC_TXT = "SVC_NTWK_CONN_STS_DESC_TXT";
    /** . */
    public static final String SLD_BY_LINE_BIZ_TP_CD = "SLD_BY_LINE_BIZ_TP_CD";
    /** . */
    public static final String SVC_LIC_CNT = "SVC_LIC_CNT";
    /** . */
    public static final String SVC_MACH_MAINT_AVAL_FLG = "SVC_MACH_MAINT_AVAL_FLG";
    /** . */
    public static final String TRX_HDR_NUM = "TRX_HDR_NUM";
    /** . */
    public static final String TRX_LINE_NUM = "TRX_LINE_NUM";
    /** . */
    public static final String TRX_LINE_SUB_NUM = "TRX_LINE_SUB_NUM";
    /** . */
    public static final String SVC_MACH_MSTR_LOC_STS_CD = "SVC_MACH_MSTR_LOC_STS_CD";
    /** . */
    public static final String SHR_DLR_FLG = "SHR_DLR_FLG";
    /** . */
    public static final String IWR_RGTN_STS_CD = "IWR_RGTN_STS_CD";
    /** . */
    public static final String ENET_PLOT_FLG = "ENET_PLOT_FLG";
    /** . */
    public static final String ENET_CMNT_TXT_01 = "ENET_CMNT_TXT_01";
    /** . */
    public static final String ENET_CMNT_TXT_02 = "ENET_CMNT_TXT_02";
    /** . */
    public static final String BR_ORG_CD = "BR_ORG_CD";
    /** . */
    public static final String CTAC_PSN_PK = "CTAC_PSN_PK";
    /** . */
    public static final String CTAC_PSN_FIRST_NM = "CTAC_PSN_FIRST_NM";
    /** . */
    public static final String CTAC_PSN_MID_NM = "CTAC_PSN_MID_NM";
    /** . */
    public static final String CTAC_PSN_LAST_NM = "CTAC_PSN_LAST_NM";
    /** . */
    public static final String TEL_DS_CTAC_PNT_VAL_TXT = "TEL_DS_CTAC_PNT_VAL_TXT";
    /** . */
    public static final String EXTN_DS_CTAC_PNT_VAL_TXT = "EXTN_DS_CTAC_PNT_VAL_TXT";
    /** . */
    public static final String EML_DS_CTAC_PNT_VAL_TXT = "EML_DS_CTAC_PNT_VAL_TXT";
    /** . */
    public static final String CELL_DS_CTAC_PNT_VAL_TXT = "CELL_DS_CTAC_PNT_VAL_TXT";
    /** . */
    public static final String FAX_DS_CTAC_PNT_VAL_TXT = "FAX_DS_CTAC_PNT_VAL_TXT";

}
