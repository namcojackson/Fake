/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB581001.constant;

/**
 * <pre>
 * Receive Supplier Info from CUSA ROSS
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   CITS            T.Wada          Create
 * 09/06/2016   CITS            T.Gotoda        Update          QC#14133
 *</pre>
 */
public class NMAB581001Constant {

    /** Business ID */
    public static final String BIZ_APP_ID = "NMAB5810";

    /** Batch Name */
    public static final String BAT_NM = "Receive Supplier Info from CUSA ROSS";

    /** Output Log Program ID */
    public static final String PROGRAM_ID = "NMAB581001";

    /**
     * Message ID : NPAM1323E Error occurred in [@] API.[@]
     */
    public static final String MSG_ID_NPAM1323E = "NPAM1323E";

    /**
     * Message ID : ZZM9000E [@] is mandatory.
     */
    public static final String MSG_ID_ZZM9000E = "ZZM9000E";

    /**
     * Message ID : ZZZM9012E Data insert failure.(ReturnCode = [@])
     */
    public static final String MSG_ID_ZZZM9012E = "ZZZM9012E";

    /**
     * Message ID : NMAM8132E @ doesn't exist in @.
     */
    public static final String MSG_ID_NMAM8132E = "NMAM8132E";

    /**
     * Message ID : ZZZM9013E Data update failure.(ReturnCode = [@])
     */
    public static final String MSG_ID_ZZZM9013E = "ZZZM9013E";

    /**
     * Message ID : NMAM8131E An error has occurred in the called API
     * [@].
     */
    public static final String MSG_ID_NMAM8131E = "NMAM8131E";

    /**
     * Message ID : ZZZM9014E Data delete failure.(ReturnCode = [@])
     */
    public static final String MSG_ID_ZZZM9014E = "ZZZM9014E";

    /**
     * Message ID : NMAM8186E The data @ does not exist in the master.
     */
    public static final String MSG_ID_NMAM8186E = "NMAM8186E";

    /**
     * Message ID : NMAM8105E The target data for the update does not
     * exist.
     */
    public static final String MSG_ID_NMAM8105E = "NMAM8105E";

    /** Message ID : ZZBM0009I */
    public static final String MSG_ID_ZZBM0009I = "ZZBM0009I";

    /** Message ID : NMAM8031E */
    public static final String MSG_ID_NMAM8031E = "NMAM8031E";

    /** The name of the API to run on this Batch **/
    public static final String API_NMZC001001 = "NMZC0010 Customer Update API";

    /** The name of the API to run on this Batch **/
    public static final String API_NMZC201001 = "NMZC2010 Update Supplier API";

    /** The name of the API to run on this Batch **/
    public static final String API_NMZC002001 = "NMZC0020 Contact Update API";

    /** Message string : Global Company Code */
    public static final String MSG_STR_COMP_CODE = "Global Company Code";

    /** Message string : Contact Type */
    public static final String MSG_STR_CNTCT_TP = "Contact Type";

    /**
     * Classification Code
     */
    public static final String MSG_STR_CC = "Classification Code";

    /**
     * GL Classification code
     */
    public static final String MSG_STR_GCC = "GL Classification code";

    /**
     * GL Intercompany code
     */
    public static final String MSG_STR_GIC = "GL Intercompany code";

    /**
     * Parent Vendor Type
     */
    public static final String MSG_STR_PRNT_VND_TP = "Parent Vendor Type";

    /** Message string : Global Company Code */
    public static final String MSG_STR_INTERFACE_ID = "Interface ID";

    /** Message string : MAIL USER GROUP */
    public static final String MSG_STR_PARAM_01 = "MAIL USER GROUP(VAR_USER1)";

    /** Message string : Contact Type for Supplier */
    public static final String MSG_STR_CTAC_TP = "Contact Type for Supplier";

    /** Message string : Supplier Type Conversion */
    public static final String MSG_STR_SPLT_TP = "Supplier Type Conversion";

    /**
     * DB COLUMN : PRNT_VND_CD
     */
    public static final String COL_PRNT_VND_CD = "PRNT_VND_CD";

    /**
     * DB COLUMN : TAX_PAYER_ID
     */
    public static final String COL_TAX_PAYER_ID = "ARCS_TAX_PAYER_ID";

    /**
     * DB COLUMN : VND_PK
     */
    public static final String COL_VND_PK = "VND_PK";

    /**
     * DB COLUMN : VND_CD
     */
    public static final String COL_VND_CD = "VND_CD";

    /**
     * DB COLUMN : LOC_NM
     */
    public static final String COL_LOC_NM = "LOC_NM";

    /**
     * DB COLUMN : EFF_FROM_DT
     */
    public static final String COL_EFF_FROM_DT = "EFF_FROM_DT";

    /**
     * DB COLUMN : CTRY_CD
     */
    public static final String COL_CTRY_CD = "CTRY_CD";

    /**
     * DB COLUMN : FIRST_LINE_ADDR
     */
    public static final String COL_FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /**
     * DB COLUMN : SCD_LINE_ADDR
     */
    public static final String COL_SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /**
     * DB COLUMN : THIRD_LINE_ADDR
     */
    public static final String COL_THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /**
     * DB COLUMN : FRTH_LINE_ADDR
     */
    public static final String COL_FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /**
     * DB COLUMN : PAY_GRP_DESC_TXT
     */
    public static final String COL_PAY_GRP_DESC_TXT = "PAY_GRP_DESC_TXT";

    /** DB COLUMN :TRANSACTION_ID **/
    public static final String COL_TRANSACTION_ID = "TRANSACTION_ID";

    /** DB COLUMN : **/
    public static final String COL_SEGMENT_ID = "SEGMENT_ID";

    /** DB COLUMN : **/
    public static final String COL_UNIT_ID = "UNIT_ID";

    /** DB COLUMN : **/
    public static final String COL_SEQ_NUMBER = "SEQ_NUMBER";

    /** DB COLUMN : **/
    public static final String COL_ARCS_SPLY_ID = "ARCS_SPLY_ID";

    /** DB COLUMN : **/
    public static final String COL_ARCS_SPLY_NUM = "ARCS_SPLY_NUM";

    /** DB COLUMN : **/
    public static final String COL_PRNT_VND_NM = "PRNT_VND_NM";

    /** DB COLUMN : **/
    public static final String COL_ARCS_VND_TP_LKUP_TXT = "ARCS_VND_TP_LKUP_TXT";

    /** DB COLUMN : **/
    public static final String COL_PRNT_VND_PMT_TERM_DESC_TXT = "PRNT_VND_PMT_TERM_DESC_TXT";

    /** DB COLUMN : **/
    public static final String COL_ARCS_TAX_PAYER_ID = "ARCS_TAX_PAYER_ID";

    /** DB COLUMN : **/
    public static final String COL_PAY_ALONE_FLG = "PAY_ALONE_FLG";

    /** DB COLUMN : **/
    public static final String COL_PRNT_VND_EFF_FROM_DT = "PRNT_VND_EFF_FROM_DT";

    /** DB COLUMN : **/
    public static final String COL_PRNT_VND_INAC_DT = "PRNT_VND_INAC_DT";

    /** DB COLUMN : **/
    public static final String COL_PRNT_VND_LAST_UPD_TS = "PRNT_VND_LAST_UPD_TS";

    /** DB COLUMN : **/
    public static final String COL_ARCS_SPLY_SITE_ID = "ARCS_SPLY_SITE_ID";

    /** DB COLUMN : **/
    public static final String COL_ARCS_SPLY_SITE_CD = "ARCS_SPLY_SITE_CD";

    /** DB COLUMN : **/
    public static final String COL_ARCS_SPLY_SITE_ATTRB_TXT_05 = "ARCS_SPLY_SITE_ATTRB_TXT_05";

    /** DB COLUMN : **/
    public static final String COL_ARCS_CTRY_TXT = "ARCS_CTRY_TXT";

    /** DB COLUMN : **/
    public static final String COL_VND_ADDR_ALL_TXT_01 = "VND_ADDR_ALL_TXT_01";

    /** DB COLUMN : **/
    public static final String COL_VND_ADDR_ALL_TXT_02 = "VND_ADDR_ALL_TXT_02";

    /** DB COLUMN : **/
    public static final String COL_VND_ADDR_ALL_TXT_03 = "VND_ADDR_ALL_TXT_03";

    /** DB COLUMN : **/
    public static final String COL_CTY_ADDR = "CTY_ADDR";

    /** DB COLUMN : **/
    public static final String COL_ST_CD = "ST_CD";

    /** DB COLUMN : **/
    public static final String COL_POST_CD = "POST_CD";

    /** DB COLUMN : **/
    public static final String COL_ARCS_CNTY_NM = "ARCS_CNTY_NM";

    /** DB COLUMN : **/
    public static final String COL_ARCS_PROV_NM = "ARCS_PROV_NM";

    /** DB COLUMN : **/
    public static final String COL_ALT_VND_ADDR_ALL_TXT = "ALT_VND_ADDR_ALL_TXT";

    /** DB COLUMN : **/
    public static final String COL_ARCS_SPLY_COA_SEG_TXT_01 = "ARCS_SPLY_COA_SEG_TXT_01";

    /** DB COLUMN : **/
    public static final String COL_ARCS_SPLY_COA_SEG_TXT_02 = "ARCS_SPLY_COA_SEG_TXT_02";

    /** DB COLUMN : **/
    public static final String COL_ARCS_SPLY_COA_SEG_TXT_03 = "ARCS_SPLY_COA_SEG_TXT_03";

    /** DB COLUMN : **/
    public static final String COL_ARCS_SPLY_COA_SEG_TXT_04 = "ARCS_SPLY_COA_SEG_TXT_04";

    /** DB COLUMN : **/
    public static final String COL_ARCS_SPLY_COA_SEG_TXT_05 = "ARCS_SPLY_COA_SEG_TXT_05";

    /** DB COLUMN : **/
    public static final String COL_ARCS_SPLY_COA_SEG_TXT_06 = "ARCS_SPLY_COA_SEG_TXT_06";

    /** DB COLUMN : **/
    public static final String COL_ARCS_SPLY_COA_SEG_TXT_07 = "ARCS_SPLY_COA_SEG_TXT_07";

    /** DB COLUMN : **/
    public static final String COL_ARCS_SPLY_COA_SEG_TXT_08 = "ARCS_SPLY_COA_SEG_TXT_08";

    /** DB COLUMN : **/
    public static final String COL_ARCS_SPLY_COA_SEG_TXT_09 = "ARCS_SPLY_COA_SEG_TXT_09";

    /** DB COLUMN : **/
    public static final String COL_VND_PMT_TERM_DESC_TXT = "VND_PMT_TERM_DESC_TXT";

    /** DB COLUMN : **/
    public static final String COL_VND_INAC_DT = "VND_INAC_DT";

    /** DB COLUMN : **/
    public static final String COL_VND_LAST_UPD_TS = "VND_LAST_UPD_TS";

    /** DB COLUMN : **/
    public static final String COL_ARCS_CTAC_PSN_FIRST_NM = "ARCS_CTAC_PSN_FIRST_NM";

    /** DB COLUMN : **/
    public static final String COL_ARCS_CTAC_PSN_LAST_NM = "ARCS_CTAC_PSN_LAST_NM";

    /** DB COLUMN : **/
    public static final String COL_ARCS_CTAC_AREA_TEL_NUM = "ARCS_CTAC_AREA_TEL_NUM";

    /** DB COLUMN : **/
    public static final String COL_ARCS_CTAC_TEL_NUM = "ARCS_CTAC_TEL_NUM";

    /** DB COLUMN : **/
    public static final String COL_ARCS_CTAC_AREA_FAX_NUM = "ARCS_CTAC_AREA_FAX_NUM";

    /** DB COLUMN : **/
    public static final String COL_ARCS_CTAC_FAX_NUM = "ARCS_CTAC_FAX_NUM";

    /** DB COLUMN : **/
    public static final String COL_ARCS_CTAC_EML_ADDR = "ARCS_CTAC_EML_ADDR";

    /** DB COLUMN : **/
    public static final String COL_VND_CTAC_INAC_DT = "VND_CTAC_INAC_DT";

    /** DB COLUMN : **/
    public static final String COL_VND_CTAC_LAST_UPD_TS = "VND_CTAC_LAST_UPD_TS";

    /** DB COLUMN : **/
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB COLUMN : **/
    public static final String COL_DS_CTAC_PSN_RELN_PK = "DS_CTAC_PSN_RELN_PK";

    /** DB COLUMN : **/
    public static final String COL_DS_ACCT_NUM = "DS_ACCT_NUM";

    /** DB COLUMN : **/
    public static final String COL_LOC_NUM = "LOC_NUM";

    /** DB COLUMN : **/
    public static final String COL_EFF_THRU_DT = "EFF_THRU_DT";

    /** DB COLUMN : **/
    public static final String COL_DS_PRIM_LOC_FLG = "DS_PRIM_LOC_FLG";

    /** DB COLUMN : **/
    public static final String COL_PTY_LOC_PK = "PTY_LOC_PK";

    /** DB COLUMN : **/
    public static final String COL_CTAC_PSN_FIRST_NM = "CTAC_PSN_FIRST_NM";

    /** DB COLUMN : **/
    public static final String COL_CTAC_PSN_MID_NM = "CTAC_PSN_MID_NM";

    /** DB COLUMN : **/
    public static final String COL_CTAC_PSN_LAST_NM = "CTAC_PSN_LAST_NM";

    /** DB COLUMN : **/
    public static final String COL_CTAC_PSN_EML_ADDR = "CTAC_PSN_EML_ADDR";

    /** DB COLUMN : **/
    public static final String COL_CTAC_PSN_EXTN_NUM = "CTAC_PSN_EXTN_NUM";

    /** DB COLUMN : **/
    public static final String COL_CTAC_PSN_PVT_FLG = "CTAC_PSN_PVT_FLG";

    /** DB COLUMN : **/
    public static final String COL_CTAC_PSN_PVT_LOGIN_USR_NM = "CTAC_PSN_PVT_LOGIN_USR_NM";

    /** DB COLUMN : **/
    public static final String COL_CTAC_PSN_SCR_TRGT_FLG = "CTAC_PSN_SCR_TRGT_FLG";

    /** DB COLUMN : **/
    public static final String COL_CTAC_PSN_RCV_ML_FLG = "CTAC_PSN_RCV_ML_FLG";

    /** DB COLUMN : **/
    public static final String COL_CTAC_TP_CD = "CTAC_TP_CD";

    /** DB COLUMN : **/
    public static final String COL_CTAC_PSN_TEL_NUM = "CTAC_PSN_TEL_NUM";

    /** DB COLUMN : **/
    public static final String COL_CTAC_PSN_CELL_PHO_NUM = "CTAC_PSN_CELL_PHO_NUM";

    /** DB COLUMN : **/
    public static final String COL_CTAC_PSN_FAX_NUM = "CTAC_PSN_FAX_NUM";

    /** DB COLUMN : **/
    public static final String COL_CTAC_PSN_CMNT_TXT = "CTAC_PSN_CMNT_TXT";

    /** DB COLUMN : **/
    public static final String COL_CTAC_PSN_ACTV_FLG = "CTAC_PSN_ACTV_FLG";

    /** DB COLUMN : **/
    public static final String COL_DS_CTAC_PSN_SALT_CD = "DS_CTAC_PSN_SALT_CD";

    /** DB COLUMN : **/
    public static final String COL_DS_CTAC_PSN_DEPT_CD = "DS_CTAC_PSN_DEPT_CD";

    /** DB COLUMN : **/
    public static final String COL_DS_CTAC_PSN_TTL_CD = "DS_CTAC_PSN_TTL_CD";

    /** DB COLUMN : **/
    public static final String COL_DS_PRIM_CTAC_TP_CD = "DS_PRIM_CTAC_TP_CD";

    /** DB COLUMN : **/
    public static final String COL_CTAC_PSN_NUM = "CTAC_PSN_NUM";

    /** DB COLUMN : **/
    public static final String COL_CTAC_PSN_ADMIN_TEL_NUM = "CTAC_PSN_ADMIN_TEL_NUM";

    /** DB COLUMN : **/
    public static final String COL_CTAC_PSN_PK = "CTAC_PSN_PK";

    /** DB COLUMN : **/
    public static final String COL_DS_CTAC_PNT_TP_CD = "DS_CTAC_PNT_TP_CD";

    /** DB COLUMN : **/
    public static final String COL_DS_CTAC_PNT_VAL_TXT = "DS_CTAC_PNT_VAL_TXT";

    /** DB COLUMN : **/
    public static final String COL_DS_CTAC_PSN_EXTN_NUM = "DS_CTAC_PSN_EXTN_NUM";

    /** DB COLUMN : **/
    public static final String COL_DS_OPS_OUT_FLG = "DS_OPS_OUT_FLG";

    /** DB COLUMN : **/
    public static final String COL_DS_CTAC_PNT_ACTV_FLG = "DS_CTAC_PNT_ACTV_FLG";

    /** DB COLUMN : **/
    public static final String COL_CNTY_PK = "CNTY_PK";

    /** DB COLUMN : **/
    public static final String COL_PROV_NM = "PROV_NM";

    /** DB COLUMN : **/
    public static final String COL_ADDL_LOC_NM = "ADDL_LOC_NM";

    /** DB COLUMN : **/
    public static final String COL_GLN_NUM = "GLN_NUM";

    /** DB COLUMN : **/
    public static final String COL_FIRST_REF_CMNT_TXT = "FIRST_REF_CMNT_TXT";

    /** DB COLUMN : **/
    public static final String COL_SCD_REF_CMNT_TXT = "SCD_REF_CMNT_TXT";

    /** DB COLUMN : **/
    public static final String COL_DBA_NM = "DBA_NM";

    /** DB COLUMN : **/
    public static final String COL_DUNS_NUM = "DUNS_NUM";

    /** DB COLUMN : **/
    public static final String COL_RGTN_STS_CD = "RGTN_STS_CD";

    /** DB COLUMN : **/
    public static final String COL_LOC_ROLE_TP_CD = "LOC_ROLE_TP_CD";

    /** DB COLUMN : **/
    public static final String COL_LOC_GRP_CD = "LOC_GRP_CD";

    /** DB COLUMN : **/
    public static final String COL_COA_AFFL_CD = "COA_AFFL_CD";

    /** DB COLUMN : **/
    public static final String COL_INTL_VND_FLG = "INTL_VND_FLG";

    /** DB COLUMN : **/
    public static final String COL_ASN_REQ_FLG = "ASN_REQ_FLG";

    /** DB COLUMN : **/
    public static final String COL_CMPY_PK = "CMPY_PK";

    /** DB COLUMN : **/
    public static final String COL_FAX_NUM = "FAX_NUM";

    /** DB COLUMN : **/
    public static final String COL_TEL_NUM = "TEL_NUM";

    /** DB COLUMN : **/
    public static final String COL_PAYEE_FLG = "PAYEE_FLG";

    /** DB COLUMN : **/
    public static final String COL_THIRD_PTY_VND_FLG = "THIRD_PTY_VND_FLG";

    /** DB COLUMN : **/
    public static final String COL_TRSMT_METH_TP_CD = "TRSMT_METH_TP_CD";

    /** DB COLUMN : **/
    public static final String COL_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** DB COLUMN : **/
    public static final String COL_FRT_CHRG_TO_CD = "FRT_CHRG_TO_CD";

    /** DB COLUMN : **/
    public static final String COL_FRT_CHRG_METH_CD = "FRT_CHRG_METH_CD";

    /** DB COLUMN : **/
    public static final String COL_INV_RCV_METH_TP_CD = "INV_RCV_METH_TP_CD";

    /** DB COLUMN : **/
    public static final String COL_CHRG_RATE_VND_GRP_CD = "CHRG_RATE_VND_GRP_CD";

    /** DB COLUMN : **/
    public static final String COL_AFFL_GLBL_CMPY_CD = "AFFL_GLBL_CMPY_CD";

    /** DB COLUMN : **/
    public static final String COL_ATTN_NM = "ATTN_NM";

    /** DB COLUMN : **/
    public static final String COL_WH_CD = "WH_CD";

    /** DB COLUMN : **/
    public static final String COL_SEND_PO_EML_ADDR = "SEND_PO_EML_ADDR";

    /** DB COLUMN : **/
    public static final String COL_DEAL_CCY_CD = "DEAL_CCY_CD";

    /** DB COLUMN : **/
    public static final String COL_INV_VND_CD = "INV_VND_CD";

    /** DB COLUMN : **/
    public static final String COL_PRNT_VND_PK = "PRNT_VND_PK";

    /** DB COLUMN : **/
    public static final String COL_SPLY_PMT_FLG = "SPLY_PMT_FLG";

    /** DB COLUMN : **/
    public static final String COL_SPLY_PO_FLG = "SPLY_PO_FLG";

    /** DB COLUMN : **/
    public static final String COL_INV_MACH_TP_CD = "INV_MACH_TP_CD";

    /** DB COLUMN : **/
    public static final String COL_INV_TOL_RATE = "INV_TOL_RATE";

    /** DB COLUMN : **/
    public static final String COL_RCV_TOL_RATE = "RCV_TOL_RATE";

    /** DB COLUMN : **/
    public static final String COL_VND_PMT_TERM_CD = "VND_PMT_TERM_CD";

    /** DB COLUMN : **/
    public static final String COL_VND_PMT_METH_CD = "VND_PMT_METH_CD";

    /** DB COLUMN : **/
    public static final String COL_PAY_GRP_CD = "PAY_GRP_CD";

    /** DB COLUMN : **/
    public static final String COL_SPLY_EDI_LOC_CD = "SPLY_EDI_LOC_CD";

    /** DB COLUMN : **/
    public static final String COL_SPLY_EDI_NUM = "SPLY_EDI_NUM";

    /** DB COLUMN : **/
    public static final String COL_INAC_DT = "INAC_DT";

    /** DB COLUMN : **/
    public static final String COL_SPLY_SITE_DEAL_CD = "SPLY_SITE_DEAL_CD";

    /** DB COLUMN : **/
    public static final String COL_XTRNL_REF_NUM = "XTRNL_REF_NUM";

    /** DB COLUMN : **/
    public static final String COL_END_CUST_NUM = "END_CUST_NUM";

    /** DB COLUMN : **/
    public static final String COL_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** DB COLUMN : **/
    public static final String COL_SPLY_COA_CMPY_CD = "SPLY_COA_CMPY_CD";

    /** DB COLUMN : **/
    public static final String COL_SPLY_COA_BR_CD = "SPLY_COA_BR_CD";

    /** DB COLUMN : **/
    public static final String COL_SPLY_COA_CC_CD = "SPLY_COA_CC_CD";

    /** DB COLUMN : **/
    public static final String COL_SPLY_COA_ACCT_CD = "SPLY_COA_ACCT_CD";

    /** DB COLUMN : **/
    public static final String COL_SPLY_COA_PROD_CD = "SPLY_COA_PROD_CD";

    /** DB COLUMN : **/
    public static final String COL_SPLY_COA_CH_CD = "SPLY_COA_CH_CD";

    /** DB COLUMN : **/
    public static final String COL_SPLY_COA_AFFL_CD = "SPLY_COA_AFFL_CD";

    /** DB COLUMN : **/
    public static final String COL_SPLY_COA_PROJ_CD = "SPLY_COA_PROJ_CD";

    /** DB COLUMN : **/
    public static final String COL_SPLY_COA_EXTN_CD = "SPLY_COA_EXTN_CD";

    /** DB COLUMN : **/
    public static final String COL_PRE_PMT_COA_CMPY_CD = "PRE_PMT_COA_CMPY_CD";

    /** DB COLUMN : **/
    public static final String COL_PRE_PMT_COA_BR_CD = "PRE_PMT_COA_BR_CD";

    /** DB COLUMN : **/
    public static final String COL_PRE_PMT_COA_CC_CD = "PRE_PMT_COA_CC_CD";

    /** DB COLUMN : **/
    public static final String COL_PRE_PMT_COA_ACCT_CD = "PRE_PMT_COA_ACCT_CD";

    /** DB COLUMN : **/
    public static final String COL_PRE_PMT_COA_PROD_CD = "PRE_PMT_COA_PROD_CD";

    /** DB COLUMN : **/
    public static final String COL_PRE_PMT_COA_CH_CD = "PRE_PMT_COA_CH_CD";

    /** DB COLUMN : **/
    public static final String COL_PRE_PMT_COA_AFFL_CD = "PRE_PMT_COA_AFFL_CD";

    /** DB COLUMN : **/
    public static final String COL_PRE_PMT_COA_PROJ_CD = "PRE_PMT_COA_PROJ_CD";

    /** DB COLUMN : **/
    public static final String COL_PRE_PMT_COA_EXTN_CD = "PRE_PMT_COA_EXTN_CD";

    /** DB COLUMN : **/
    public static final String COL_VND_RTRN_COA_CMPY_CD = "VND_RTRN_COA_CMPY_CD";

    /** DB COLUMN : **/
    public static final String COL_VND_RTRN_COA_BR_CD = "VND_RTRN_COA_BR_CD";

    /** DB COLUMN : **/
    public static final String COL_VND_RTRN_COA_CC_CD = "VND_RTRN_COA_CC_CD";

    /** DB COLUMN : **/
    public static final String COL_VND_RTRN_COA_ACCT_CD = "VND_RTRN_COA_ACCT_CD";

    /** DB COLUMN : **/
    public static final String COL_VND_RTRN_COA_PROD_CD = "VND_RTRN_COA_PROD_CD";

    /** DB COLUMN : **/
    public static final String COL_VND_RTRN_COA_CH_CD = "VND_RTRN_COA_CH_CD";

    /** DB COLUMN : **/
    public static final String COL_VND_RTRN_COA_AFFL_CD = "VND_RTRN_COA_AFFL_CD";

    /** DB COLUMN : **/
    public static final String COL_VND_RTRN_COA_PROJ_CD = "VND_RTRN_COA_PROJ_CD";

    /** DB COLUMN : **/
    public static final String COL_VND_RTRN_COA_EXTN_CD = "VND_RTRN_COA_EXTN_CD";

    /** DB COLUMN : **/
    public static final String COL_SEND_ARCS_FLG = "SEND_ARCS_FLG";

    /** DB COLUMN : **/
    public static final String COL_CARR_TP_CD = "CARR_TP_CD";

    /** DB COLUMN : **/
    public static final String COL_INV_MATCH_TP_CD = "INV_MATCH_TP_CD";

    /** DB COLUMN : **/
    public static final String COL_PRNT_VND_TP_CD = "PRNT_VND_TP_CD";

    /** DB COLUMN : **/
    public static final String COL_DS_CTAC_PNT_PK = "DS_CTAC_PNT_PK";

    /** DB COLUMN : **/
    public static final String COL_CTAC_TEL_NUM_TXT = "CTAC_TEL_NUM_TXT";

    /** DB COLUMN : **/
    public static final String COL_CTAC_FAX_AREA_CD_TXT = "CTAC_FAX_AREA_CD_TXT";

    /** DB COLUMN : **/
    public static final String COL_CTAC_EML_ADDR_TXT = "CTAC_EML_ADDR_TXT";

    /** DB COLUMN : **/
    public static final String COL_CTAC_TEL_AREA_CD_TXT = "CTAC_TEL_AREA_CD_TXT";

    /** DB COLUMN : **/
    public static final String COL_CTAC_FAX_NUM_TXT = "CTAC_FAX_NUM_TXT";

    /** DB COLUMN : **/
    public static final String COL_DS_BIZ_LAST_UPD_DT = "DS_BIZ_LAST_UPD_DT";

    /** DB COLUMN : **/
    public static final String COL_DS_BIZ_PROC_LOG_PK = "DS_BIZ_PROC_LOG_PK";

    /** DB COLUMN : **/
    public static final String COL_ARCS_LIAB_SEG_TXT_01 = "ARCS_SPLY_COA_SEG_TXT_01";

    /** DB COLUMN : **/
    public static final String COL_ARCS_LIAB_SEG_TXT_02 = "ARCS_SPLY_COA_SEG_TXT_02";

    /** DB COLUMN : **/
    public static final String COL_ARCS_LIAB_SEG_TXT_03 = "ARCS_SPLY_COA_SEG_TXT_03";

    /** DB COLUMN : **/
    public static final String COL_ARCS_LIAB_SEG_TXT_04 = "ARCS_SPLY_COA_SEG_TXT_04";

    /** DB COLUMN : **/
    public static final String COL_ARCS_LIAB_SEG_TXT_05 = "ARCS_SPLY_COA_SEG_TXT_05";

    /** DB COLUMN : **/
    public static final String COL_ARCS_LIAB_SEG_TXT_06 = "ARCS_SPLY_COA_SEG_TXT_06";

    /** DB COLUMN : **/
    public static final String COL_ARCS_LIAB_SEG_TXT_07 = "ARCS_SPLY_COA_SEG_TXT_07";

    /** DB COLUMN : **/
    public static final String COL_ARCS_LIAB_SEG_TXT_08 = "ARCS_SPLY_COA_SEG_TXT_08";

    /** DB COLUMN : **/
    public static final String COL_ARCS_LIAB_SEG_TXT_09 = "ARCS_SPLY_COA_SEG_TXT_09";

    /** DB COLUMN : **/
    public static final String COL_TAX_PAYER_RG_NUM = "TAX_PAYER_RG_NUM";

    /** DB COLUMN : **/
    public static final String COL_INDY_TP_CD = "INDY_TP_CD";

    /** DB COLUMN : **/
    public static final String COL_MNRTY_OWND_TP_CD = "MNRTY_OWND_TP_CD";

    /** DB COLUMN : **/
    public static final String COL_SPLY_ORG_TP_CD = "SPLY_ORG_TP_CD";

    /** DB COLUMN : **/
    public static final String COL_SPLY_ONE_TM_FLG = "SPLY_ONE_TM_FLG";

    /** DB COLUMN : **/
    public static final String COL_SM_BIZ_FLG = "SM_BIZ_FLG ";

    /** DB COLUMN : **/
    public static final String COL_WOMEN_OWND_FLG = "WOMEN_OWND_FLG";

    /** DB COLUMN : **/
    public static final String COL_DISC_TAKE_FLG = "DISC_TAKE_FLG";

    /** DB COLUMN : **/
    public static final String COL_PO_PMT_HLD_FLG = "PO_PMT_HLD_FLG";

    /** DB COLUMN : **/
    public static final String COL_FD_RPT_FLG = "FD_RPT_FLG";

    /** DB COLUMN : **/
    public static final String COL_INC_TAX_TP_CD = "INC_TAX_TP_CD";

    /** DB COLUMN : **/
    public static final String COL_ST_TAX_FLG = "ST_TAX_FLG";

    /** DB COLUMN : **/
    public static final String COL_SPLY_RPT_NM = "SPLY_RPT_NM";

    /** DB COLUMN : **/
    public static final String COL_SPLY_HUB_ZN_CD = "SPLY_HUB_ZN_CD";

    /** DB COLUMN : **/
    public static final String COL_PRNT_VND_LAST_UPD_DT = "PRNT_VND_LAST_UPD_DT";

    /** DB COLUMN : **/
    public static final String COL_VND_LAST_UPD_DT = "VND_LAST_UPD_DT";

    /** DB COLUMN : **/
    public static final String COL_VND_CTAC_LAST_UPD_DT = "VND_CTAC_LAST_UPD_DT";

    /** DB COLUMN : **/
    public static final String COL_MAX_PRNT_VND_LAST_UPD_DT = "MAX_PRNT_VND_LAST_UPD_DT";

    /** DB COLUMN : **/
    public static final String COL_MAX_VND_LAST_UPD_DT = "MAX_VND_LAST_UPD_DT";

    /** DB COLUMN : **/
    public static final String COL_MAX_VND_CTAC_LAST_UPD_DT = "MAX_VND_CTAC_LAST_UPD_DT";

    /** Error Massage: CRLF */
    public static final String ERR_MSG_CRLF = "\r\n";

    /** */
    public static final String NONE = "NONE";

    /**
     * The maximum length of the POST_CD
     */
    public static final int LEN_MAX_POST_CD = 10;

    /**
     * The maximum length of the FIRST_LINE_ADDR
     */
    public static final int LEN_FIRST_LINE_ADDR = 60;

    /**
     * The start point to be cut out of the ARCS_LIAB_SEG_TXT_08
     */
    public static final int SP_ARCS_LIAB_SEG_TXT_08 = 2;

    /**
     * The end point to be cut out of the ARCS_LIAB_SEG_TXT_08
     */
    public static final int EP_ARCS_LIAB_SEG_TXT_08 = 4;

    /** The initial value of the date **/
    public static final String MIN_DT = "00000000";

    /** DATE_TIME_PATTERN **/
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    /***
     * VAL_IF_DEF
     */
    public static final String VAL_IF_DEF = "IF_DEF";

    /**
     * Default thru date
     */
    public static final String DEF_THRU_DT = "99991231";

    /**
     * Header Two Letter Of COA_PROJ_CD
     */
    public static final String HL_COA_PROJ_CD = "00";

    /**
     * TBL NAME : VND
     */
    public static final String TBL_DS_BIZ_PROC_LOG = "DS_BIZ_PROC_LOG";

    /** Mail Timestamp Format */
    public static final String MAIL_TS_FMT = "MM/dd/yyyy HH:mm:ss.SSS";

    /** Mail Date Format Substr Length */
    public static final int MAIL_LEN_FMT_SUBSTR = 10;

    /** Mail String Field : TIMESTAMP */
    public static final String MAIL_FIELD_TIMESTAMP = "TIMESTAMP";

    /** Mail String Field : MESSAGE */
    public static final String MAIL_FIELD_MESSAGE = "SupplierInfo";

    /**
     * KEY_LGCY_CUST_CLS_CD
     */
    public static final String KEY_LGCY_CUST_CLS_CD = "NMAB5810_DEF_LGCY_CUST_CLS_CD";

    /**
     * KEY_COA_CH_CD
     */
    public static final String KEY_COA_CH_CD = "NMAB5810_DEF_COA_CH_CD";

    /**
     * KEY_COA_AFFL_CD
     */
    public static final String KEY_COA_AFFL_CD = "NMAB5810_DEF_COA_AFFL_CD";

    /**
     * C_XTRNL_SYS_VND_TP_LKUP_TXT
     */
    public static final String C_XTRNL_SYS_VND_TP_LKUP_TXT = "ROSS_DLR";

    /** DB COLUMN :RTL_DLR_CD **/
    public static final String COL_RTL_DLR_CD = "RTL_DLR_CD";

    /** DB COLUMN :RTL_SELL_TO_ATTN_TXT **/
    public static final String COL_RTL_SELL_TO_ATTN_TXT = "RTL_SELL_TO_ATTN_TXT";

    /** DB COLUMN :EML_ADDR **/
    public static final String COL_EML_ADDR = "EML_ADDR";

    /** VAL_EXTERNAL */
    public static final String VAL_EXTERNAL = "N";

    /** VAL_CTAC_PSN_FIRST_NM */
    public static final String VAL_CTAC_PSN_FIRST_NM = "3RD PARTY SVC";

    /** MAX_LEN_LOC_NM */
    public static final int MAX_LEN_LOC_NM = 10;

    /** Fetch Size */
    public static final int FETCH_SIZE = 1000;

    // =================================================
    // Mail Param
    // =================================================
    /** MAIL_TEMPLATE_BATCH_ID_KEY */
    public static final String MAIL_TEMPLATE_BATCH_ID_KEY = "batchId";

    /** MAIL_TEMPLATE_ERR_MESSAGE_KEY */
    public static final String MAIL_TEMPLATE_ERROR_INFO_KEY = "ErrorInfo";

    /** MAIL_TEMPLATE_BATCH_NM */
    public static final String MAIL_TEMPLATE_BATCH_NM = "batchNm";

    /** MAIL_FROM_ADDR_GRP */
    public static final String MAIL_FROM_ADDR_GRP = "System common";

    /** MAIL_FROM_ADDR_GRP_ID */
    public static final String MAIL_FROM_ADDR_GRP_ID = "FROM0005";

    /** MAIL_ADDR_TO_GRP */
    public static final String MAIL_ADDR_TO_GRP = BIZ_APP_ID;

    /** MAIL_TEMP_ID */
    public static final String MAIL_TEMP_ID = BIZ_APP_ID + "M001";

}
