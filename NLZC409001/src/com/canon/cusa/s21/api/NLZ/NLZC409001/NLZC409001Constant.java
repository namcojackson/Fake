/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC409001;

/**
 *<pre>
 * NLAB409001:RMA Report Send To EIP API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/08/2016   CITS            M.Naito         Create          N/A
 * 12/27/2016   CITS            Y.Fujii         Update          QC#16547
 *</pre>
 */
public class NLZC409001Constant {

    /** 
     * Batch ID
     */
    public static final String BATCH_ID = "NLZC4090";

    /** 
     * DEBUG_LOG_HDR.
     */
    public static final String DEBUG_LOG_HDR = "setMsgId:";

    /** 
     * CST_DEBUG_MSG_LVL.
    */
    public static final int CST_DEBUG_MSG_LVL = 10;

    /**
     * FILE_NM_PREF.
    */
    public static final String RPT_TITLE = " RMA Return Order Report";

    /** 
     * CHAR_HYPHEN.
     */
    public static final char CHAR_HYPHEN = '-';

    /** 
     * CHAR_BLANK.
     */
    public static final char CHAR_BLANK = ' ';

    /**
     * FILE_NM_PREF.
    */
    public static final String FILE_NM_PREF = "RMA";

    /**
     * EXT: .pdf
     */
    public static final String EXT = ".pdf";

    /**
     * HALF_SPACE.
     */
    public static final String HALF_SPACE = " ";

    /**
     * REPORT_ID. */
    public static final String REPORT_ID = "NLAF0020";

    /**
     * Report Title Name. */
    public static final String REPORT_TITLE = " RMA Return Order Report";

    /**
     * GLBL_CMPY_CD. */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /**
     * CSA_RMA_RPT_PRINT_RQST_SQ. */
    public static final String CSA_RMA_RPT_PRINT_RQST_SQ = "CSA_RMA_RPT_PRINT_RQST_SQ";

    /**
     * INTL_LANG_VAL_COL_NM. */
    public static final String INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    /**
     * RWS_REF_NUM. */
    public static final String RWS_REF_NUM = "RWS_REF_NUM";

    // =================================================
    // Message Code
    // =================================================
    /**
     * TRSMT_METH_TP is PRINTER or EMAIL_PDF or PDF_DOWNLOAD.
     */
    public static final String NLZM2487E = "NLZM2487E";

    /**
     * Email Address is not entered.
     */
    public static final String NLZM2488E = "NLZM2488E";

    /**
     * Global Company Code is not set.
     */
    public static final String NLZM2259E = "NLZM2259E";

    /**
     *  CSA_RMA_RPT_PRINT_RQST_SQ is not set.
     */
    public static final String NLZM2485E = "NLZM2485E";

    /**
     *  RWS_REF_NUM is not set.
     */
    public static final String NLZM2502E = "NLZM2502E";

    /**
     * Transmission Method Type Code is not set.
     */
    public static final String NLZM2486E = "NLZM2486E";

    /**
     * RPT_BR_ID input is not set.
     */
    public static final String NLZM2489E = "NLZM2489E";

    /**
     *  It failed to generate a report instance.
     */
    public static final String NLZM1041E = "NLZM1041E";

    /**
     * Cannot retrieve From Address.
     */
    public static final String NLZM2490E = "NLZM2490E";

    /**
     * Cannot retrieve mail template.
     */
    public static final String NLZM2491E = "NLZM2491E";

    // =================================================
    // Mail Setting
    // =================================================
    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = BATCH_ID + "M001";

    /** meil Key */
    public static final String MAIL_KEY = "NLZ";
}
