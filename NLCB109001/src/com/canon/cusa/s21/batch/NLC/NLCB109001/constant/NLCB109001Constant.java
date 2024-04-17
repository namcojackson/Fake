/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB109001.constant;

/**
 *<pre>
 * NLCB1090:Inventory Discrepancy Report Batch For Serial# / Config ID Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/30/2016   CITS            Y.Nomura         Create          N/A
 *</pre>
 */
public class NLCB109001Constant {

    /** Batch ID */
    public static final String BATCH_ID = "NLCB109001";

    /** CSV comma. */
    public static final String COMMA = ",";

    /** CSV double quotes. */
    public static final String DOUBLE_QUOTES = "\"";

    /** line feed code */
    public static final String LINE_FEED_CODE = "\r\n";

    // QC#21298 Start
    /** Time pattern : HHmmss */
    public static final String TM_PTRN_HHMMSS = "HHmmss";
    // QC#21298 End
    /**
     * Date time format string : MM/dd/yyyy HH:mm
     */
    public static final String TIME_FORMAT_MM_DD_YYYY_HH_MM = "MM/dd/yyyy hh:mm";

    /**
     * Mail Message Format
     */
    public static final String ML_FMT = "  %-10s  %-10s  %-12s  %-30s  %-2s  %-28s  %-30s  %10d  %10d  %10d";

    /**
     * Mail Message Format
     */
    public static final String ML_HEAD = "W/H         PR          Item          Description                     SS  Config ID                     Serial#                            S21 Qty     WMS Qty    Diff Qty";

    /**
     * Mail Message Format
     */
    public static final String ML_NO_DATA = "                                             WMS W/H:%s  No Discrepancy";

    // =================================================
    // Var Char Const Name
    // =================================================
    /** Var Char Const Name */
    public static final String NLCB1090_SVC_MACH_MSTR_STS_CD = "NLCB1090_SVC_MACH_MSTR_STS_CD";

    /** Var Char Const Name */
    public static final String NLCB1090_SVC_LOC_STS_CD = "NLCB1090_SVC_LOC_STS_CD";

    /** Var Char Const Name */
    public static final String NLCB1090_PROC_ADJ_DT = "NLCB1090_PROC_ADJ_DT";

    // =================================================
    // Message Code
    // =================================================
    /** [@] has no value. */
    public static final String NPAM0078E = "NPAM0078E";

    /** Sales date cannot be obtained. */
    public static final String NPAM1480E = "NPAM1480E";

    /** Sales date cannot be obtained. */
    public static final String ZZM9000E = "ZZM9000E";

    /** It failed to register an email. */
    public static final String NLBM1065E = "NLBM1065E";
    // QC#21298 Start
    /** Message ID : [@] field requires numeric input only. */
    public static final String ZZM9004E = "ZZM9004E";

    /** Message ID : Key Duplication Error: Table @, Key @ */
    public static final String NLCM0034E = "NLCM0034E";

    /** Message ID : DB Access Error. Table[@], Command[@], Key[@] */
    public static final String NLCM0050E = "NLCM0050E";

    /** Message string : Discrepancy Report Preservation period */
    public static final String MSG_STR_DISCR_RPT_PRESER_PERIOD = "Preservation period of Discrepancy Report";
    // QC#21298 End
    // =================================================
    // e-mail
    // =================================================
    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_ID = "NLCB1090M001";

    /** Mail Group ID(From) */
    public static final String MAIL_FROM_GROUP_ID = "FROM0003";

    /** Mail Group ID(To) */
    public static final String MAIL_TO_GROUP_ID = "NLCB1090";

    /** Mail Language */
    public static final String ML_LANG = "en";

    /** Mail Language Code */
    public static final String ML_LANG_CD = "ISO-8859-1";

    /** . */
    public static final String EMAIL_PARAM_WMS_WH_CD = "WMS_WH_CD";

    /** . */
    public static final String EMAIL_PARAM_REPORT_ID = "REPORT_ID";

    /** . */
    public static final String EMAIL_PARAM_DATE = "DATE";

    /** . */
    public static final String EMAIL_PARAM_MSG = "MSG";

    /** . */
    public static final String CSV_FILE_NAME = "NLCB1090_InventoryDiscrepancy_%s.csv";

    // =================================================
    // DB Param
    // =================================================
    // QC#21298 Start
    /** Sequence Name : INVTY_DISCR_RPT_WRK_SQ */
    public static final String SEQ_NM_INVTY_DISCR_RPT_SER_WRK_SQ = "INVTY_DISCR_RPT_SER_WRK_SQ";
    // QC#21298 End
    /** . */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** . */
    public static final String DB_PARAM_SVC_MACH_MSTR_STS_CD = "svcMachMstrStsCd";

    /** . */
    public static final String DB_PARAM_SVC_MACH_MSTR_LOC_STS_CD = "svcMachMstrLocStsCd";

    /** . */
    public static final String DB_PARAM_WMS_WH_CD = "wmsWhCd";

    /** . */
    public static final String DB_PARAM_WMS_INVTY_DT = "wmsInvtyDt";

    // =================================================
    // DB Column
    // =================================================
    /** . */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** . */
    public static final String MDSE_CD = "MDSE_CD";

    /** . */
    public static final String FIRST_PROD_CTRL_CD = "FIRST_PROD_CTRL_CD";

    /** . */
    public static final String MDSE_NM = "MDSE_NM";

    /** . */
    public static final String STK_STS_CD = "STK_STS_CD";

    /** . */
    public static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** . */
    public static final String SER_NUM = "SER_NUM";

    /** . */
    public static final String S21_QTY = "S21_QTY";

    /** . */
    public static final String WMS_QTY = "WMS_QTY";

    /** . */
    public static final String DIFF_QTY = "DIFF_QTY";

    /** . */
    public static final String WMS_WH_CD = "WMS_WH_CD";

}
