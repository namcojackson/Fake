/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLA.NLAB317001.constant;

/**
 *<pre>
 * NLAB317001:Standalone RMA Printing Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/08/2016   CITS            M.Naito         Create          N/A
 * 10/20/2017   CITS            T.Kikuhara      Update          QC#21195
 *</pre>
 */
public class NLAB317001Constant {

    /** Batch ID */
    public static final String BATCH_ID = "NLAB3170";

    /** RPT_PRINT_CTRL */
    public static final String RPT_PRINT_CTRL = "RPT_PRINT_CTRL";

    /** NLAF0020_DIRECT_PRINT */
    public static final String NLAF0020_DIRECT_PRINT = "NLAF0020_DIRECT_PRINT";

    // =================================================
    // Message Code
    // =================================================

    /** Global Company Code is not set. */
    public static final String NLZM2259E = "NLZM2259E";

    /** Sales Date is not set. */
    public static final String NLZM2079E = "NLZM2079E";

    /** An error was returned against the calling API. @ */
    public static final String NLEM0003E = "NLEM0003E";

    /** RPT_BR_ID(RPT_PRINT_CTRL_ID：[@] , RPT_ID：[@]) is not defined in @. */
    public static final String NLZM2492E = "NLZM2492E";

    /** [@] doesn't exist in the VAR_CHAR_CONST. */
    public static final String NLZM2493E = "NLZM2493E";

    // =================================================
    // DB Param
    // =================================================
    /** . */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** . */
    public static final String DB_PARAM_ORD_HDR_STS_CD = "ordHdrStsCd";

    /** . */
    public static final String DB_PARAM_ORD_LINE_STS_CD = "ordLineStsCd";

    /** . */
    public static final String DB_PARAM_ORD_BOOK_REQ_TS = "ordBookReqTs";

    /** . */
    public static final String DB_PARAM_CARR_CD = "carrCd";

    /** . */
    public static final String DB_PARAM_RPT_ID = "rptId";

    /** . */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    // =================================================
    // API Status
    // =================================================
    /** SUCCESS */
    public static final String SUCCESS = "SUCCESS";

    /** ERROR */
    public static final String ERROR = "ERROR";

    /** WARNING */
    public static final String WARNING = "WARNING";

    // =================================================
    // ERRORMESSAGE Status
    // =================================================
    /** ERROR */
    public static final String MSG_ERROR = "E";

    /** WARNING */
    public static final String MSG_WARNING = "W";
    // =================================================
    // EIP Parameter
    // =================================================
    /** REPORT_ID */
    public static final String REPORT_ID = "NLAF0020";

    /** ELAN_RPT_PRINT_RQST_SQ */
    public static final String ELAN_RPT_PRINT_RQST_SQ = "ELAN_RPT_PRINT_RQST_SQ";

    /** INTL_LANG_VAL_COL_NM */
    public static final String INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    // =================================================
    // Mail Setting
    // =================================================
    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = BATCH_ID + "01";

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = BATCH_ID + "M001";

    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    // =================================================
    // DB Column
    // =================================================
    /** . */
    public static final String RWS_REF_NUM = "RWS_REF_NUM";
    // QC#21195 ADD START
    /** . */
    public static final String RWS_NUM = "RWS_NUM";
    // QC#21195 ADD END
}
