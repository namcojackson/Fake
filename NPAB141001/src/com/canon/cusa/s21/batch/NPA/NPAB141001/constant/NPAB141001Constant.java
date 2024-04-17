/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB141001.constant;

/**
 *<pre>
 * NPAB1410:ELAN Printing Batch

 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/08/2016   CITS            M.Naito         Create          N/A
 * 10/22/2018   CITS            T.Wada          Update          QC#28852
 *</pre>
 */
public class NPAB141001Constant {

    /** Batch ID */
    public static final String BATCH_ID = "NPAB1410";

    /** . */
    public static final String GLOBAL_CMPY_CD = "Global Company Code";

    /** SPACE */
    public static final String SPACE = " ";

    /** COMMA */
    public static final String COMMA = ",";

    /** Date Format */
    public static final String DATE_TIME_FORMAT = "MM/dd/yyyy";

    /** Date Format2 */
    public static final String DATE_TIME_FORMAT_14 = "yyyyMMddHHmmss";

    /** EDI_ASN_HDR_WRK */
    public static final String EDI_ASN_HDR_WRK = "EDI_ASN_HDR_WRK";

    /** RPT_ITEM_CTRL */
    public static final String RPT_ITEM_CTRL = "RPT_ITEM_CTRL";

    /** EDI_ASN_HDR_WRK */
    public static final String RPT_PRINT_CTRL = "RPT_PRINT_CTRL";

    /** EDI_ASN_SER_NUM_WRK */
    public static final String EDI_ASN_SER_NUM_WRK = "EDI_ASN_SER_NUM_WRK";

    // =================================================
    // Var Char Const Name
    // =================================================
    /** Var Char Const Name */
    public static final String NPAF0060_DIRECT_PRINT = "NPAF0060_DIRECT_PRINT";

    /** Var Char Const Name */
    public static final String NPAF0060_PURGE_DT = "NPAF0060_PURGE_DT";

    // QC#28852 Add
    /** Var Char Const Name */
    public static final String NPAF0060_SENDMAIL_LIMIT_DAYS = "NPAF0060_SENDMAIL_LIMIT_DAYS";

    // =================================================
    // Message Code
    // =================================================
    /** Global Company Code is mandatory. */
    public static final String NPAM1479E = "NPAM1479E";

    /** [@] has no value. */
    public static final String NPAM0078E = "NPAM0078E";

    /** [@] is invalid code. */
    public static final String NPAM0080E = "NPAM0080E";

    /** Sales date cannot be obtained. */
    public static final String NPAM1480E = "NPAM1480E";

    /** Failed to update. [@] */
    public static final String NPAM1171E = "NPAM1171E";

    /** Failed to insert. [@] */
    public static final String NPAM1172E = "NPAM1172E";

    /** Failed to remove. [@] */
    public static final String NPAM1342E = "NPAM1342E";

    /** MDSE_CD[@] is not defined in @. */
    public static final String NPAM1591E = "NPAM1591E";

    /** RPT_BR_ID(RPT_PRINT_CTRL_ID：[@] , RPT_ID：[@]) is not defined in @. */
    public static final String NPAM1592E = "NPAM1592E";

    /** SER_NUM(ASN_SO_NUM：[@], ASN_SO_SLP_NUM：[@])is not defined in @. */
    public static final String NPAM1593E = "NPAM1593E";

    // =================================================
    // DB Param
    // =================================================
    /** . */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** . */
    public static final String DB_PARAM_PURGE_DATE = "purgeTs";

    /** . */
    public static final String DB_PARAM_EDI_PROC_STS_CD = "ediProcStsCd";

    /** . */
    public static final String DB_PARAM_INITIAL = "initial";

    /** . */
    public static final String DB_PARAM_PRINT_ERROR = "printError";

    /** . */
    public static final String DB_PARAM_MDSE_CD = "mdseCd";

    /** . */
    public static final String DB_PARAM_RPT_ID = "rptId";

    /** . */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    // =================================================
    // EIP Parameter
    // =================================================
    /** REPORT_ID */
    public static final String REPORT_ID = "NPAF0060";

    /** ELAN_RPT_PRINT_RQST_SQ */
    public static final String ELAN_RPT_PRINT_RQST_SQ = "ELAN_RPT_PRINT_RQST_SQ";

    /** INTL_LANG_VAL_COL_NM */
    public static final String INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    /** REPORT_TITLE */
    public static final String REPORT_TITLE = "ELAN Printing Report";

    // =================================================
    // Mail Setting
    // =================================================
    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = BATCH_ID;

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = BATCH_ID + "M001";

    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    /** Mail Header Space */
    public static final String MAIL_SPACE = "           ";

    /** Mail Header ErrorMessage*/
    public static final String ERROR_MESSAGE = "ErrorMessage";


    // =================================================
    // DB Column
    // =================================================
    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** . */
    public static final String ELAN_RPT_WRK_PK = "ELAN_RPT_WRK_PK";
    /** . */
    public static final String PO_ORD_NUM = "PO_ORD_NUM";
    /** . */
    public static final String MDSE_CD = "MDSE_CD";
    /** . */
    public static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";
    /** . */
    public static final String SER_NUM = "SER_NUM";
    /** . */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";
    /** . */
    public static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";
    /** . */
    public static final String SHIP_TO_FIRST_LINE_ADDR = "SHIP_TO_FIRST_LINE_ADDR";
    /** . */
    public static final String SHIP_TO_SCD_LINE_ADDR = "SHIP_TO_SCD_LINE_ADDR";
    /** . */
    public static final String SHIP_TO_THIRD_LINE_ADDR = "SHIP_TO_THIRD_LINE_ADDR";
    /** . */
    public static final String SHIP_TO_FRTH_LINE_ADDR = "SHIP_TO_FRTH_LINE_ADDR";
    /** . */
    public static final String SHIP_TO_CTY_ADDR = "SHIP_TO_CTY_ADDR";
    /** . */
    public static final String SHIP_TO_ST_CD = "SHIP_TO_ST_CD";
    /** . */
    public static final String DEST_RTL_WH_CD = "DEST_RTL_WH_CD";
    /** . */
    public static final String SHIP_TO_POST_CD = "SHIP_TO_POST_CD";
    /** . */
    public static final String ASN_SO_NUM = "ASN_SO_NUM";
    /** . */
    public static final String ASN_SO_SLP_NUM = "ASN_SO_SLP_NUM";
    /** . */
    public static final String RPT_ID = "RPT_ID";
    /** . */
    public static final String RPT_BR_ID = "RPT_BR_ID";
    /** . */
    public static final String BR_FLG = "BR_FLG";
    /** . */
    public static final String SW_LIC_CTRL_TP_CD = "SW_LIC_CTRL_TP_CD";
    /** . */
    public static final String INTNT_CONN_SW_CTRL_FLG = "INTNT_CONN_SW_CTRL_FLG";
    // QC#28852 Add
    /** . */
    public static final String EZINTIME = "EZINTIME";
}
