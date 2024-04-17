/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB114001.constant;

/**
 *<pre>
 * MRP Run Post-process Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/31/2016   CITS            K.Ogino         Create          N/A
 * 11/17/2016   CITS            K.Ogino         Update          QC#16002
 * 09/27/2017   CITS            K.Fukumura      Update          QC#21230
 *</pre>
 */

public class NPAB114001Constant {

    /** Date Format */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    /** Date Format */
    public static final String DATE_TIME_PRINT_FORMAT = "dd-MMM-yyyy HH:mm";

    // =================================================
    // Message Code
    // =================================================
    /** NPAM1173E The field of [@] is not input. */
    public static final String NPAM1173E = "NPAM1173E";

    /** An error occurred in @. */
    public static final String NLCM0047E = "NLCM0047E";

    /** Failed to insert. [@] */
    public static final String NPAM1172E = "NPAM1172E";

    /** EIP Print Error Message */
    public static final String PRINT_ERROR_MSG = "It failed to generate a report instance.";

    // =================================================
    // Sequence Name
    // =================================================
    /** MRP_RPT_PRINT_RQST_SQ */
    public static final String MRP_RPT_PRINT_RQST_SQ = "MRP_RPT_PRINT_RQST_SQ";

    /** MRP_RPT_WRK_SQ */
    public static final String MRP_RPT_WRK_SQ = "MRP_RPT_WRK_SQ";

    // =================================================
    // SSM Parameter Name
    // =================================================
    /** GLBL_CMPY_CD */
    public static final String P_GLBL_CMPY_CD = "glblCmpyCd";

    /** MRP_RPT_PRINT_PROC_STS_CD */
    public static final String MRP_RPT_PRINT_PROC_STS_CD_LIST = "mrpRptPrintProcStsCdList";

    /** P_MRP_RUN_SCHD_ID */
    public static final String P_MRP_RUN_SCHD_ID = "mrpRunSchdId";

    /** P_MRP_RPT_PRINT_RQST_SQ */
    public static final String P_MRP_RPT_PRINT_RQST_SQ = "mrpRptPrintRqstSq";

    /** P_MRP_RUN_PRM_PK */
    public static final String P_MRP_RUN_PRM_PK = "mrpRunPrmPk";

    /** RTL_WH_CD */
    public static final String P_RTL_WH_CD = "rtlWhCd";

    /** RTL_SWH_CD */
    public static final String P_RTL_SWH_CD = "rtlSwhCd";

    /** MRP_ENBL_FLG */
    public static final String P_MRP_ENBL_FLG = "mrpEnblFlg";

    /** MRP_INFO_RGTN_STS_CD */
    public static final String P_MRP_INFO_RGTN_STS_CD = "mrpInfoRgtnStsCd";

    /** fetchSize */
    public static final int FETCH_SIZE = 1000;

    // =================================================
    // DB Result Column Name
    // =================================================
    /** MRP_RUN_PRM_PK */
    public static final String MRP_RUN_PRM_PK = "MRP_RUN_PRM_PK";

    /** MRP_RUN_SCHD_ID */
    public static final String MRP_RUN_SCHD_ID = "MRP_RUN_SCHD_ID";

    /** LINE_BIZ_TP_CD */
    public static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";

    /** MRP_PLN_NM */
    public static final String MRP_PLN_NM = "MRP_PLN_NM";

    /** RTL_WH_CD */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /** RTL_WH_NM */
    public static final String RTL_WH_NM = "RTL_WH_NM";

    /** RTL_SWH_CD */
    public static final String RTL_SWH_CD = "RTL_SWH_CD";

    /** RTL_SWH_NM */
    public static final String RTL_SWH_NM = "RTL_SWH_NM";

    /** LOC_TP_CD */
    public static final String LOC_TP_CD = "LOC_TP_CD";

    /** LOC_TP_DESC_TXT */
    public static final String LOC_TP_DESC_TXT = "LOC_TP_DESC_TXT";

    /** MRP_RPT_WRK_PK */
    public static final String MRP_RPT_WRK_PK = "MRP_RPT_WRK_PK";

    /** DMND_CTOFF_DT */
    public static final String DMND_CTOFF_DT = "DMND_CTOFF_DT";

    /** SPLY_CTOFF_DT */
    public static final String SPLY_CTOFF_DT = "SPLY_CTOFF_DT";

    /** GLBL_CMPY_CD */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DMND_CTOFF_DT_TXT */
    public static final String DMND_CTOFF_DT_TXT = "DMND_CTOFF_DT_TXT";

    /** DMND_CTOFF_DAYS_AOT */
    public static final String DMND_CTOFF_DAYS_AOT = "DMND_CTOFF_DAYS_AOT";

    /** SPLY_CTOFF_DT_TXT */
    public static final String SPLY_CTOFF_DT_TXT = "SPLY_CTOFF_DT_TXT";

    /** SPLY_CTOFF_DAYS_AOT */
    public static final String SPLY_CTOFF_DAYS_AOT = "SPLY_CTOFF_DAYS_AOT";

    /** CRAT_PRCH_REQ_FLG */
    public static final String CRAT_PRCH_REQ_FLG = "CRAT_PRCH_REQ_FLG";

    /** PRINT_ITEM_STS_FLG */
    public static final String PRINT_ITEM_STS_FLG = "PRINT_ITEM_STS_FLG";

    /** PRINT_ITEM_DESC_FLG */
    public static final String PRINT_ITEM_DESC_FLG = "PRINT_ITEM_DESC_FLG";

    /** ONL_BAT_RQST_FLG */
    public static final String ONL_BAT_RQST_FLG = "ONL_BAT_RQST_FLG";

    /** PLN_LVL_DESC_TXT */
    public static final String PLN_LVL_DESC_TXT = "PLN_LVL_DESC_TXT";

    /** MRP_PLN_NM_SRCH_TXT */
    public static final String MRP_PLN_NM_SRCH_TXT = "MRP_PLN_NM_SRCH_TXT";

    /** RTL_WH_CD_SRCH_TXT */
    public static final String RTL_WH_CD_SRCH_TXT = "RTL_WH_CD_SRCH_TXT";

    /** RTL_WH_NM_SRCH_TXT */
    public static final String RTL_WH_NM_SRCH_TXT = "RTL_WH_NM_SRCH_TXT";

    /** RTL_SWH_CD_SRCH_TXT */
    public static final String RTL_SWH_CD_SRCH_TXT = "RTL_SWH_CD_SRCH_TXT";

    /** RTL_SWH_NM_SRCH_TXT */
    public static final String RTL_SWH_NM_SRCH_TXT = "RTL_SWH_NM_SRCH_TXT";
    // =================================================
    // Print Status Code
    // =================================================
    /** Unprocessed */
    public static final String PRINT_PROC_STS_UNPROCESSED = "0";

    /** Error */
    public static final String PRINT_PROC_STS_ERROR = "9";

    /** Success */
    public static final String PRINT_PROC_STS_SUCCESS = "1";

    // =================================================
    // EIP Parameter
    // =================================================
    /** REPORT_ID */
    public static final String REPORT_ID = "NPAF0030";

    /** REPORT_TITLE */
    public static final String REPORT_TITLE = "Planning Report";

    /** HAIFUN */
    public static final String HAIFUN = "-";

    /** SPACE */
    public static final String SPACE = " ";

    /** INTL_LANG_VAL_COL_NM */
    public static final String INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";
    // =================================================
    // VAR_CHAR_CONST value
    // =================================================
    /** NPAB1140_PRNT_NOFOUND_MSG */
    public static final String NPAB1140_PRNT_NOFOUND_MSG = "NPAB1140_PRNT_NOFOUND_MSG";

}
