/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB247001.constant;

/**
 *<pre>
 * NWAB2470:CUSA Retail Order import report Creation Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/17/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NWAB247001Constant {

    /** BIZ_APP_ID */
    public static final String BIZ_APP_ID = "NWAB2470";

    /** VAR_CHAR_CONST_NM */
    public static final String VAR_CHAR_CONST_NM = "NWAB2470_PRINT_BR_NUM";

    /** RPT_RQST_HDR */
    public static final String RPT_RQST_HDR = "RPT_RQST_HDR";

    /** RPT_RQST_COND */
    public static final String RPT_RQST_COND = "RPT_RQST_COND";

    /** DS_IMPT_ORD */
    public static final String DS_IMPT_ORD = "DS_IMPT_ORD";

    /** CPO */
    public static final String CPO = "CPO";

    /** SVC_DLR_CD_C525 */
    public static final String SVC_DLR_CD_C525 = "C525";

    /** ORD_IMPT_RPT_WRK */
    public static final String ORD_IMPT_RPT_WRK = "ORD_IMPT_RPT_WRK";

    /** MSG_RPT_RQST_COND */
    public static final String MSG_RPT_RQST_COND = "Search Criteria. Please check RPT_RQST_COND table (RPT_RQST_HDR_PK = [%s]";

    /** CSA_ORD_HDR_VIEW_SNAP */
    public static final String CSA_ORD_HDR_VIEW_SNAP = "CSA_ORD_HDR_VIEW_SNAP";

    /** CSA_ORD_ITEM_VIEW_SNAP */
    public static final String CSA_ORD_ITEM_VIEW_SNAP = "CSA_ORD_ITEM_VIEW_SNAP";

    /** MSG_ORD_SRC_REF_NUM */
    public static final String MSG_ORD_SRC_REF_NUM = "CPO_ORD_NUM = %s,INSTL_CD = %s,ISTL_SUB_LOC_CD = %s";

    /** MSG_RPT_RQST_HDR_PK */
    public static final String MSG_RPT_RQST_HDR_PK = "RPT_RQST_HDR, RPT_RQST_HDR_PK = [%s]";

    /** MSG_RPT_RQST_HDR_PK_RPT_ID */
    public static final String MSG_RPT_RQST_HDR_PK_RPT_ID = "RPT_RQST_HDR, RPT_RQST_HDR_PK = [%s],RPT_ID = [%s]";

    /** MSG_RPT_RQST_HDR_PK_RPT_ID */
    public static final String MSG_RPT_RQST_HDR_PK_EIP_RPT_RQST_ID = "RPT_RQST_HDR, RPT_RQST_HDR_PK = [%s],RPT_ID = [%s]";

    /** MSG_RPT_RQST_HDR_REP_ID_NULL */
    public static final String MSG_RPT_RQST_HDR_REP_ID_NULL = "RPT_ID is NULL, RPT_RQST_HDR_PK = [%s] ";

    /** FMT_TIME_STAMP */
    public static final String FMT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /** FMT_YYYYMMDD */
    public static final String FMT_YYYYMMDD = "yyyyMMdd";

    /** MSG_ITEM_GLOBAL_COMPANY_CODE */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** COND_SQL_FORMAT */
    public static final String COND_SQL_FORMAT = " AND DIO.%s %s '%s'";

    /** OTPT_OP_CD_VAL */
    public static final String OTPT_OP_CD_VAL = "Batch";

    /** LINE_ONE_AREA */
    public static final String LINE_ONE_AREA = "Line1Area";

    /** LINE_TWOE_AREA */
    public static final String LINE_TWO_AREA = "Line2Area";

    /** LINE_THREE_AREA */
    public static final String LINE_THREE_AREA = "Line3Area";

    /** LINE_MSG_AREA */
    public static final String LINE_MSG_AREA = "LineMsgArea";

    /** LINE_NOTE_AREA */
    public static final String LINE_NOTE_AREA = "LineNoteArea";

    /** CONTR_BIZ_TP_CDS */
    public static final String[] CONTR_BIZ_TP_CDS = {"P", "TP", "R", "FL", "LT", "TR", "CP", "DP" };

    // =================================================
    // Message Code
    // =================================================
    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** In the table [@], there is no data corresponding to [@]. */
    public static final String NWAM0311E = "NWAM0311E";

    /** Data update failure. (table name is [@]) */
    public static final String NWAM0729E = "NWAM0729E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** The specified Constant [@] has not been set. */
    public static final String NWAM0261E = "NWAM0261E";

    /** Date format error. [Table Name : @, Item Name : @] */
    public static final String NWAM0526E = "NWAM0526E";

    /** Failed to register data. Table: [@] */
    public static final String NWAM0546E = "NWAM0546E";

    // =================================================
    // Note Area
    // =================================================
    /** NOTE_AREA_C525_MSG01 */
    public static final String NOTE_AREA_C525_MSG01 = "  IF YOUR PUCHASE DID NOT INCLUDE AN EXTENDED WARRANTY AND YOU WOULD LIKE TO PURCHASE";

    /** NOTE_AREA_C525_MSG02 */
    public static final String NOTE_AREA_C525_MSG02 = "  A SERVICE AGREEMENT UPON THE EXPIRATION OF OUR MANUFACTURER'S WARRANTY. PLEASE CONTACT";

    /** NOTE_AREA_C525_MSG03 */
    public static final String NOTE_AREA_C525_MSG03 = "  CANON'S GOVERNMENT MARKETING DIVISION AT THE ABOVE ADDRESS FOR ADDITIONAL INFORMATION AND PRICING.";

    /** NOTE_AREA_DEAL_APPR_FLAG_Y_MSG01 */
    public static final String NOTE_AREA_DEAL_APPR_FLAG_Y_MSG01 = "  1. EQUIPMENT IS TO BE INSTALLED OUT OF DEALER'S INVENTORY.";

    /** NOTE_AREA_DEAL_APPR_FLAG_Y_MSG02 */
    public static final String NOTE_AREA_DEAL_APPR_FLAG_Y_MSG02 = "  2. PLEASE CONTACT THE CUSTOMER TO MAKE ARRANGEMENTS FOR INSTALLATION.";

    /** NOTE_AREA_DEAL_APPR_FLAG_Y_MSG303 */
    public static final String NOTE_AREA_DEAL_APPR_FLAG_Y_MSG03 = "  3. PLEASE PROVIDE THE SERIAL NUMBER(S) AND RECORD AN INITIAL METER READING.";

    /** NOTE_AREA_DEAL_APPR_FLAG_N_MSG01 */
    public static final String NOTE_AREA_DEAL_APPR_FLAG_N_MSG01 = " PLEASE FAX INSTALL INFORMATION TO %s";

    /** NOTE_ARE_FAX_NAD */
    public static final String NOTE_ARE_FAX_NAD = "(800)-525-5578";

    /** NOTE_ARE_FAX_GMD */
    public static final String NOTE_ARE_FAX_GMD = "(703)-807-3729";

    /** NOTE_AREA_DEAL_APPR_FLAG_N_MSG02_PRE */
    public static final String NOTE_AREA_DEAL_APPR_FLAG_N_MSG02 = "  1.THIS EQUIPMENT HAS BEEN SHIPPED FROM %s TO ABOVE SHIP TO ADDRESS FOR INSTALLATION PREPARATION.";

    /** NOTE_AREA_DEAL_APPR_FLAG_N_MSG303 */
    public static final String NOTE_AREA_DEAL_APPR_FLAG_N_MSG03 = "  2.PLEASE CONTACT THE CUSTOMER TO MAKE ARRANGEMENTS FOR INSTALLATION.";

    /** NOTE_AREA_DEAL_APPR_FLAG_N_MSG04 */
    public static final String NOTE_AREA_DEAL_APPR_FLAG_N_MSG04 = "  3.PLEASE VERIFY THE SERIAL NUMBER(S) AND RECORD AN INITIAL METER READING.";

    /** NOTE_AREA_SUPPLY_INCL_FLAG_Y_NOT_ZERO_MSG05 */
    public static final String NOTE_AREA_SUPPLY_INCL_FLAG_Y_MSG05 = "  4.THIS ORDER IS TONER - INCLUSIVE. TONER WILL BE PROVIDED BY  CANON.";

    /** NOTE_AREA_DEAL_APPR_FLAG_N_MSG04 */
    public static final String NOTE_AREA_SUPPLY_INCL_FLAG_Y_NOT_ZERO_MSG06 = "  5.THE ABOVE CUSTOMER HAS PURCHASED AN EXTENDED WARRANTY SERVICE AGREEMENT.";

    /** NOTE_AREA_SUPPLY_INCL_FLAG_N_NOT_ZERO_MSG05 */
    public static final String NOTE_AREA_SUPPLY_INCL_FLAG_N_NOT_ZERO_MSG05 = "  4.THE ABOVE CUSTOMER HAS PURCHASED AN EXTENDED WARRANTY SERVICE AGREEMENT.";

    // =================================================
    // EIP Report Param
    // =================================================
    /** EIP_RPT_ID */
    public static final String EIP_RPT_ID = "NWAF0090";

    /** EIP_RPT_TITLE */
    public static final String EIP_RPT_TITLE = "Print Order Import Success Failure Report Request# [%s] Time %s";

    /** EIP_PRINT_JOB_NAME */
    public static final String EIP_PRINT_JOB_NAME = "Request# [%s] Time %s";

    /** EIP_RPT_PARAM_GLBL_CMPY_CD */
    public static final String EIP_RPT_PARAM_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** EIP_RPT_PARAM_BIZ_APP_ID */
    public static final String EIP_RPT_PARAM_BIZ_APP_ID = "BIZ_APP_ID";

    /** EIP_RPT_PARAM_OTPT_OP_CD */
    public static final String EIP_RPT_PARAM_OTPT_OP_CD = "OTPT_OP_CD";

    /** EIP_RPT_PARAM_RPT_RQST_HDR_PK */
    public static final String EIP_RPT_PARAM_RPT_RQST_HDR_PK = "RPT_RQST_HDR_PK";

    /** EIP_RPT_PARAM_INTL_LANG_VAL_COL_NM */
    public static final String EIP_RPT_PARAM_INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    // =================================================
    // Mail Param
    // =================================================
    /** MAIL_TEMPLATE_BATCH_ID_KEY */
    public static final String MAIL_TEMPLATE_BATCH_ID_KEY = "batchId";

    /** MAIL_TEMPLATE_BATCH_NAME_KEY */
    public static final String MAIL_TEMPLATE_BATCH_NAME_KEY = "batchNm";

    /** MAIL_TEMPLATE_ERR_INFO_KEY */
    public static final String MAIL_TEMPLATE_ERR_INFO_KEY = "ErrorInfo";

    /** MAIL_FROM_ADDR_GRP */
    public static final String MAIL_FROM_ADDR_GRP = "System common";

    /** MAIL_FROM_ADDR_GRP_ID */
    public static final String MAIL_FROM_ADDR_GRP_ID = "FROM0005";

    /** MAIL_ADDR_TO_GRP */
    public static final String MAIL_ADDR_TO_GRP = BIZ_APP_ID;

    /** MAIL_TEMP_ID */
    public static final String MAIL_TEMP_ID = "NWAB2470M001";

    /** BATCH_NM */
    public static final String BATCH_NM = "CUSA Retail Order import report Creation Batch";

    /** ERR_MSG_HDR */
    public static final String ERR_MSG_HDR = "Report Request Header PK Error Message";

    /** FRM_ERR MSG_TXT */
    public static final String FRM_ERR_MSG_TXT = "    %-25s%s";

    // =================================================
    // DB Param
    // =================================================

    /** FETCH_SIZE */
    public static final int FETCH_SIZE = 1000;

    /** DB_PARAM_GLBL_CMPY_CD */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** DB_PARAM_RPT_RQST_PROC_STS_CD */
    public static final String DB_PARAM_RPT_RQST_PROC_STS_CD = "rptRqstProcStsCd";

    /** DB_PARAM_RPT_RQST_HDR_PK */
    public static final String DB_PARAM_RPT_RQST_HDR_PK = "rptRqstHdrPk";

    /** DB_PARAM_RPT_ID */
    public static final String DB_PARAM_RPT_ID = "rptId";

    /** DB_PARAM_COND_VAL_LIST */
    public static final String DB_PARAM_COND_VAL_LIST = "condValList";

    /** DB_PARAM_CPO_ORD_NUM */
    public static final String DB_PARAM_CPO_ORD_NUM = "cpoOrdNum";

    /** DB_PARAM_INSTL_CD */
    public static final String DB_PARAM_INSTL_CD = "instlCd";

    /** DB_PARAM_ISTL_SUB_LOC_CD */
    public static final String DB_PARAM_ISTL_SUB_LOC_CD = "istlSubLocCd";

    /** DB_PARAM_TRX_HDR_NUM */
    public static final String DB_PARAM_TRX_HDR_NUM = "trxHdrNum";

    /** DB_PARAM_ROW_NUM */
    public static final String DB_PARAM_ROW_NUM = "rowNum";

    /** DB_PARAM_CONTR_BIZ_TP_CD_LIST */
    public static final String DB_PARAM_CONTR_BIZ_TP_CD_LIST = "contrBizTpCdList";

    //QC#9971
    /** DB_PARAM_CPO_SRC_TP_CD */
    public static final String DB_PARAM_CPO_SRC_TP_CD = "cpoSrcTpCd";
    
    // =================================================
    // DB Column
    // =================================================
    /** RPT_RQST_HDR_PK */
    public static final String RPT_RQST_HDR_PK = "RPT_RQST_HDR_PK";

    /** DS_BIZ_LAST_UPD_TS */
    public static final String RPT_ID = "RPT_ID";

    /** RPT_RQST_COND_PK */
    public static final String RPT_RQST_COND_PK = "RPT_RQST_COND_PK";

    /** RPT_RQST_COL_TP_CD */
    public static final String RPT_RQST_COL_TP_CD = "RPT_RQST_COL_TP_CD";

    /** RPT_RQST_OP_TP_CD */
    public static final String RPT_RQST_OP_TP_CD = "RPT_RQST_OP_TP_CD";

    /** DS_BIZ_LAST_UPD_TS */
    public static final String RPT_RQST_COL_VAL_TXT = "RPT_RQST_COL_VAL_TXT";

    /** RPT_RQST_PHYS_NM */
    public static final String RPT_RQST_PHYS_NM = "RPT_RQST_PHYS_NM";

    /** RPT_RQST_OP_TP_NM */
    public static final String RPT_RQST_OP_TP_NM = "RPT_RQST_OP_TP_NM";

    /** DS_IMPT_ORD_PK */
    public static final String DS_IMPT_ORD_PK = "DS_IMPT_ORD_PK";

    /** ORD_SRC_REF_NUM */
    public static final String ORD_SRC_REF_NUM = "ORD_SRC_REF_NUM";

    /** IMPT_STS_CD */
    public static final String IMPT_STS_CD = "IMPT_STS_CD";

    /** TOC_CD */
    public static final String TOC_CD = "TOC_CD";

    /** TOC_NM */
    public static final String TOC_NM = "TOC_NM";

    /** OIG_ORD_NUM AS CPO_ORD_NUM */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** CPO_ORD_TS */
    public static final String CPO_ORD_TS = "CPO_ORD_TS";

    /** COA_EXTN_CD */
    public static final String COA_EXTN_CD = "COA_EXTN_CD";

    /** CCOA_EXTN_DESC_TXT */
    public static final String CCOA_EXTN_DESC_TXT = "CCOA_EXTN_DESC_TXT";

    /** OLD_TO_CUST_LOC_CD */
    public static final String OLD_TO_CUST_LOC_CD = "OLD_TO_CUST_LOC_CD";

    /** DS_ACCT_NM_SELL */
    public static final String DS_ACCT_NM_SELL = "DS_ACCT_NM_SELL";

    /** sELL_TO_CUST_CD */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** BILL_TO_CUST_ACCT_CD */
    public static final String BILL_TO_CUST_ACCT_CD = "BILL_TO_CUST_ACCT_CD";

    /** DS_ACCT_NM_BILL */
    public static final String DS_ACCT_NM_BILL = "DS_ACCT_NM_BILL";

    /** BILL_TO_CUST_CD */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** HIP_TO_CUST_ACCT_CD */
    public static final String SHIP_TO_CUST_ACCT_CD = "SHIP_TO_CUST_ACCT_CD";

    /** HIP_TO_LOC_NM AS DS_ACCT_NM_SHIP */
    public static final String DS_ACCT_NM_SHIP = "DS_ACCT_NM_SHIP";

    /** HIP_TO_CUST_CD */
    public static final String HSIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** ORD_SRC_IMPT_TS */
    public static final String ORD_SRC_IMPT_TS = "ORD_SRC_IMPT_TS";

    /** COA_BR_CD */
    public static final String COA_BR_CD = "COA_BR_CD";

    /** COA_BR_DESC_TXT */
    public static final String COA_BR_DESC_TXT = "COA_BR_DESC_TXT";

    /** RTL_DIV_CD */
    public static final String RTL_DIV_CD = "RTL_DIV_CD";

    /** CUST_ISS_PO_NUM */
    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** INSTL_SELL_TO_CUST_CD */
    public static final String INSTL_SELL_TO_CUST_CD = "INSTL_SELL_TO_CUST_CD";

    /** ORD_COORD_NM */
    public static final String ORD_COORD_NM = "ORD_COORD_NM";

    /** RTL_CUST_PO_DT */
    public static final String RTL_CUST_PO_DT = "RTL_CUST_PO_DT";

    /** ROSS_ORD_TP_MSG_TXT */
    public static final String ROSS_ORD_TP_MSG_TXT = "ROSS_ORD_TP_MSG_TXT";

    /** ISTL_LOC_FIRST_CUST_NM */
    public static final String ISTL_LOC_FIRST_CUST_NM = "ISTL_LOC_FIRST_CUST_NM";

    /** ISTL_LOC_SCD_CUST_NM */
    public static final String ISTL_LOC_SCD_CUST_NM = "ISTL_LOC_SCD_CUST_NM";

    /** ISTL_LOC_FIRST_LINE_ADDR */
    public static final String ISTL_LOC_FIRST_LINE_ADDR = "ISTL_LOC_FIRST_LINE_ADDR";

    /** ISTL_LOC_CTY_ADDR */
    public static final String ISTL_LOC_CTY_ADDR = "ISTL_LOC_CTY_ADDR";

    /** ISTL_LOC_ST_CD */
    public static final String ISTL_LOC_ST_CD = "ISTL_LOC_ST_CD";

    /** ISTL_LOC_POST_CD */
    public static final String ISTL_LOC_POST_CD = "ISTL_LOC_POST_CD";

    /** ISTL_LOC_TEL_NUM */
    public static final String ISTL_LOC_TEL_NUM = "ISTL_LOC_TEL_NUM";

    /** ISTL_LOC_FAX_NUM */
    public static final String ISTL_LOC_FAX_NUM = "ISTL_LOC_FAX_NUM";

    /** ISTL_LOC_ATTN_TO_NM */
    public static final String ISTL_LOC_ATTN_TO_NM = "ISTL_LOC_ATTN_TO_NM";

    /** INSTL_CD */
    public static final String INSTL_CD = "INSTL_CD";

    /** ISTL_SUB_LOC_CD */
    public static final String ISTL_SUB_LOC_CD = "ISTL_SUB_LOC_CD";

    /** SVC_DLR_CD */
    public static final String SVC_DLR_CD = "SVC_DLR_CD";

    /** SVC_DLR_FIRST_CUST_NM */
    public static final String SVC_DLR_FIRST_CUST_NM = "SVC_DLR_FIRST_CUST_NM";

    /** SVC_DLR_SCD_CUST_NM */
    public static final String SVC_DLR_SCD_CUST_NM = "SVC_DLR_SCD_CUST_NM";

    /** SVC_DLR_FIRST_LINE_ADDR */
    public static final String SVC_DLR_FIRST_LINE_ADDR = "SVC_DLR_FIRST_LINE_ADDR";

    /** SVC_DLR_CTY_ADDR */
    public static final String SVC_DLR_CTY_ADDR = "SVC_DLR_CTY_ADDR";

    /** SVC_DLR_ST_CD */
    public static final String SVC_DLR_ST_CD = "SVC_DLR_ST_CD";

    /** SVC_DLR_POST_CD */
    public static final String SVC_DLR_POST_CD = "SVC_DLR_POST_CD";

    /** SVC_DLR_TEL_NUM */
    public static final String SVC_DLR_TEL_NUM = "SVC_DLR_TEL_NUM";

    /** SVC_DLR_FAX_NUM */
    public static final String SVC_DLR_FAX_NUM = "SVC_DLR_FAX_NUM";

    /** DLR_APP_FLG */
    public static final String DLR_APP_FLG = "DLR_APP_FLG";

    /** SPLY_INCL_FLG */
    public static final String SPLY_INCL_FLG = "SPLY_INCL_FLG";

    /** WTY_MTH */
    public static final String WTY_MTH = "WTY_MTH";

    /** FNDG_DLR_CD */
    public static final String FNDG_DLR_CD = "FNDG_DLR_CD";

    /** FNDG_DLR_FIRST_CUST_NM */
    public static final String FNDG_DLR_FIRST_CUST_NM = "FNDG_DLR_FIRST_CUST_NM";

    /** FNDG_DLR_SCD_CUST_NM */
    public static final String FNDG_DLR_SCD_CUST_NM = "FNDG_DLR_SCD_CUST_NM";

    /** FNDG_DLR_FIRST_LINE_ADDR */
    public static final String FNDG_DLR_FIRST_LINE_ADDR = "FNDG_DLR_FIRST_LINE_ADDR";

    /** FNDG_DLR_CTY_ADDR */
    public static final String FNDG_DLR_CTY_ADDR = "FNDG_DLR_CTY_ADDR";

    /** FNDG_DLR_ST_CD */
    public static final String FNDG_DLR_ST_CD = "FNDG_DLR_ST_CD";

    /** FNDG_DLR_POST_CD */
    public static final String FNDG_DLR_POST_CD = "FNDG_DLR_POST_CD";

    /** FNDG_DLR_TEL_NUM */
    public static final String FNDG_DLR_TEL_NUM = "FNDG_DLR_TEL_NUM";

    /** FNDG_DLR_FAX_NUM */
    public static final String FNDG_DLR_FAX_NUM = "FNDG_DLR_FAX_NUM";

    /** ORIG_DLR_CD */
    public static final String ORIG_DLR_CD = "ORIG_DLR_CD";

    /** ORIG_DLR_FIRST_CUST_NM */
    public static final String ORIG_DLR_FIRST_CUST_NM = "ORIG_DLR_FIRST_CUST_NM";

    /** ORIG_DLR_SCD_CUST_NM */
    public static final String ORIG_DLR_SCD_CUST_NM = "ORIG_DLR_SCD_CUST_NM";

    /** ORIG_DLR_FIRST_LINE_ADDR */
    public static final String ORIG_DLR_FIRST_LINE_ADDR = "ORIG_DLR_FIRST_LINE_ADDR";

    /** ORIG_DLR_CTY_ADDR */
    public static final String ORIG_DLR_CTY_ADDR = "ORIG_DLR_CTY_ADDR";

    /** ORIG_DLR_ST_CD */
    public static final String ORIG_DLR_ST_CD = "ORIG_DLR_ST_CD";

    /** ORIG_DLR_POST_CD */
    public static final String ORIG_DLR_POST_CD = "ORIG_DLR_POST_CD";

    /** ORIG_DLR_TEL_NUM */
    public static final String ORIG_DLR_TEL_NUM = "ORIG_DLR_TEL_NUM";

    /** ORIG_DLR_FAX_NUM */
    public static final String ORIG_DLR_FAX_NUM = "ORIG_DLR_FAX_NUM";

    /** SHIP_TO_LOC_FIRST_CUST_NM */
    public static final String SHIP_TO_LOC_FIRST_CUST_NM = "SHIP_TO_LOC_FIRST_CUST_NM";

    /** SHIP_TO_LOC_SCD_CUST_NM */
    public static final String SHIP_TO_LOC_SCD_CUST_NM = "SHIP_TO_LOC_SCD_CUST_NM";

    /** SHIP_TO_LOC_FIRST_LINE_ADDR */
    public static final String SHIP_TO_LOC_FIRST_LINE_ADDR = "SHIP_TO_LOC_FIRST_LINE_ADDR";

    /** SHIP_TO_LOC_CTY_ADDR */
    public static final String SHIP_TO_LOC_CTY_ADDR = "SHIP_TO_LOC_CTY_ADDR";

    /** SHIP_TO_LOC_ST_CD */
    public static final String SHIP_TO_LOC_ST_CD = "SHIP_TO_LOC_ST_CD";

    /** SHIP_TO_LOC_POST_CD */
    public static final String SHIP_TO_LOC_POST_CD = "SHIP_TO_LOC_POST_CD";

    /** SHIP_TO_LOC_TEL_NUM */
    public static final String SHIP_TO_LOC_TEL_NUM = "SHIP_TO_LOC_TEL_NUM";

    /** SHIP_TO_LOC_FAX_NUM */
    public static final String SHIP_TO_LOC_FAX_NUM = "SHIP_TO_LOC_FAX_NUM";

    /** SHIP_TO_LOC_ATTN_TO_NM */
    public static final String SHIP_TO_LOC_ATTN_TO_NM = "SHIP_TO_LOC_ATTN_TO_NM";

    /** CPO_ORD_NUM || RCPO_POSN_NUM */
    public static final String LOC_NUM = "LOC_NUM";

    /** ORD_QTY */
    public static final String ORD_QTY = "ORD_QTY";

    /** SHIP_QTY */
    public static final String SHIP_QTY = "SHIP_QTY";

    /** BO_QTY */
    public static final String BO_QTY = "BO_QTY";

    /** SER_NUM */
    public static final String SER_NUM = "SER_NUM";

    /** PRNT_SER_NUM */
    public static final String PRNT_SER_NUM = "PRNT_SER_NUM";

    /** SO_NUM */
    public static final String SO_NUM = "SO_NUM";

    /** WH_FIRST_CUST_NM */
    public static final String WH_FIRST_CUST_NM = "WH_FIRST_CUST_NM";

    /** ACTL_SHIP_DT */
    public static final String ACTL_SHIP_DT = "ACTL_SHIP_DT";

    /** LOC_NM */
    public static final String LOC_NM = "LOC_NM";

    /** MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** MDL_NM */
    public static final String MDL_NM = "MDL_NM";

    /** ISTL_COMP_AMT */
    public static final String ISTL_COMP_AMT = "ISTL_COMP_AMT";

    /** SVC_DLR_COMP_AMT */
    public static final String SVC_DLR_COMP_AMT = "SVC_DLR_COMP_AMT";

    /** EXT_WTY_COMP_AMT */
    public static final String EXT_WTY_COMP_AMT = "EXT_WTY_COMP_AMT";

    /** FNDG_DLR_COMP_AMT */
    public static final String FNDG_DLR_COMP_AMT = "FNDG_DLR_COMP_AMT";

    /** ORIG_DLR_COMP_AMT */
    public static final String ORIG_DLR_COMP_AMT = "ORIG_DLR_COMP_AMT";

    /** ORD_TAKE_DLR_COMP_AMT */
    public static final String ORD_TAKE_DLR_COMP_AMT = "ORD_TAKE_DLR_COMP_AMT";

    /** RMV_COMP_AMT */
    public static final String RMV_COMP_AMT = "RMV_COMP_AMT";

    /** MLY_BASE_COMP_AMT */
    public static final String MLY_BASE_COMP_AMT = "MLY_BASE_COMP_AMT";

    /** MLY_ADMIN_COMP_AMT */
    public static final String MLY_ADMIN_COMP_AMT = "MLY_ADMIN_COMP_AMT";

    /** XS_MTR_COPY_QTY */
    public static final String XS_MTR_COPY_QTY = "XS_MTR_COPY_QTY";

    /** XS_MTR_COMP_COPY_QTY */
    public static final String XS_MTR_COMP_COPY_QTY = "XS_MTR_COMP_COPY_QTY";

    /** XS_MTR_COMP_AMT_RATE */
    public static final String XS_MTR_COMP_AMT_RATE = "XS_MTR_COMP_AMT_RATE";

    /** RTL_MSG_TXT */
    public static final String RTL_MSG_TXT = "RTL_MSG_TXT";

}
