/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB049001.constant;

/**
 * <pre>
 * NSBB049001Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 23/19/2015   Hitachi         J.Kim           Create          N/A
 * 11/18/2016   Hitachi         Y.Takeno        Update          QC#16005
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * </pre>
 */
public class NSBB049001Constant {

    /** System Error : @ */
    public static final String NSAM0219E = "NSAM0219E";

    /** It failed to get the [@]. */
    public static final String NLAM1285E = "NLAM1285E";

    /** Message ID : NSAM0069E */
    public static final String NSAM0069E = "NSAM0069E";

    /** Message ID : NLAM1315E */
    public static final String NLAM1315E = "NLAM1315E";

    /** Error Message Text: Global Company Code */
    public static final String MSG_TXT_GLBL_CMPY_CD = "Global Company Code cannot be obtained.";

    /** Column name : TECH_INVTY_AGING_WRK_PK */
    public static final String TECH_INVTY_AGING_WRK_PK = "TECH_INVTY_AGING_WRK_PK";

    /** MAX COUNT NUMBER */
    public static final int MAX_COUNT_NUMBER = 1000;

    /**
     * BATCH_PROGRAM_ID(NSBB0490)
     */
    public static final String BATCH_PROGRAM_ID = "NSBB0490";

    /**
     * PROGRAM_ID(For DS_BIZ_PROC_LOG)
     */
    public static final String PROGRAM_ID = "NSBB049001";

    /** Mail Template ID(NSBB0490M001) */
    public static final String MAIL_TEMPLATE_ID_01 = "NSBB0490M001";

    /** Mail Group From(DS) */
    public static final String MAIL_GROUP_KEY_FROM = "NS";

    /** Mail Group From(FROM0005) */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

// START 2016/11/18 [QC#16005,MOD]
    /** Mail Group To(NSBB049001) */
    public static final String MAIL_GROUP_ID_TO = "NSBB0490";
// END   2016/11/18 [QC#16005,MOD]

    /** Mail Character Set */
    public static final String MAIL_CHARSET = "ISO-8859-1";

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    /** Mail Date Format : MM/dd/yyyy HH:mm:ss.SSS */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

    /** Mail Item (${batchId}) */
    public static final String MAIL_ITEM_BATCH_ID = "batchId";

    /** Mail Item (${errDate}) */
    public static final String MAIL_ITEM_ERR_DATE = "errDate";

    /** Mail Item (${message}) */
    public static final String MAIL_ITEM_ERR_MSG = "message";

    /** Column name : DS_BIZ_LAST_PROC_TS */
    public static final String DS_BIZ_LAST_PROC_TS = "DS_BIZ_LAST_PROC_TS";

    /** Column name : DS_BIZ_PROC_LOG_PK */
    public static final String DS_BIZ_PROC_LOG_PK = "DS_BIZ_PROC_LOG_PK";

    /** Column name : SVC_MBL_DICT_TP_TXT */
    public static final String SVC_MBL_DICT_TP_TXT = "SVC_MBL_DICT_TP_TXT";

    /** Date Length */
    public static final int DT_LEN = 8;

    /** COMMA */
    public static final String STR_CNM = ", ";

    /**
     * SVC_MBL_DICT Table Name - Bill Code
     */
    public static final String SVC_BILL_TP = "SVC_BILL_TP";

    /**
     * SVC_MBL_DICT Table Name - Correction Code 
     */
    public static final String SVC_PBLM_CRCT_TP = "SVC_PBLM_CRCT_TP";

    /**
     * SVC_MBL_DICT Table Name - Location Code
     */
    public static final String SVC_PBLM_LOC_TP = "SVC_PBLM_LOC_TP";
 
    /**
     * SVC_MBL_DICT Table Name - Problem Code
     */
    public static final String SVC_PBLM_TP = "SVC_PBLM_TP";

    /**
     * SVC_MBL_DICT Table Name - Reason Code
     */
    public static final String SVC_PBLM_RSN_TP = "SVC_PBLM_RSN_TP";

    /**
     * SVC_MBL_DICT Table Name - Skills
     */
    public static final String SVC_SKILL = "SVC_SKILL";

    /**
     * SVC_MBL_DICT Table Name - Task Types
     */
    public static final String DS_SVC_CALL_TP = "DS_SVC_CALL_TP";

    // START 2017/01/04 K.Kitachi [QC#16316, ADD]
    public static final String INTERFACE_ID = "NSBI1130";
    // END 2017/01/04 K.Kitachi [QC#16316, ADD]
}
