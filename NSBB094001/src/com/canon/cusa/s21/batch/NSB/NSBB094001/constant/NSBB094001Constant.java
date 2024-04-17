/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB094001.constant;

/**
 * <pre>
 * Send Warehouse to Click
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/09/2016   Hitachi         Y.Zhang         Create          NA
 * 11/18/2016   Hitachi         Y.Takeno        Update          QC#16005
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 09/06/2017   Hitachi         U.Kim           Update          QC#20868
 * 2021/11/17   CITS            R.Cabral        Update          QC#59387
 * 2023/06/21   Hitachi         K.Chiba         Update          QC#61262
 * </pre>
 */
public class NSBB094001Constant {

    /** Global Company Code is required. */
    public static final String NSBM0179E = "NSBM0179E";

    /** No search results found of [@]. */
    public static final String NSBM0180E = "NSBM0180E";

    /** Error occurred while calling WebService. */
    public static final String NSBM0181E = "NSBM0181E";

    /** Could not retrieve an email address. */
    public static final String NSBM0182E = "NSBM0182E";

    /** Could not retrieve mail template. */
    public static final String NSBM0183E = "NSBM0183E";

    /** Error Message Text: Sales Date */
    public static final String MSG_TXT_SALES_DATE = "Sales Date";

    /** MAX COUNT NUMBER */
    public static final int MAX_COUNT_NUMBER = 1000;

    /**
     * PROGRAM_ID(For DS_BIZ_PROC_LOG)
     */
    public static final String PROGRAM_ID = "NSBB094001";

    /** Date Length */
    public static final int DT_LEN = 8;

    /** Column name : DS_BIZ_PROC_LOG_PK */
    public static final String DS_BIZ_PROC_LOG_PK = "DS_BIZ_PROC_LOG_PK";

    /** Column name : DS_BIZ_LAST_PROC_TS */
    public static final String DS_BIZ_LAST_PROC_TS = "DS_BIZ_LAST_PROC_TS";

    /** Mail Template ID(NPAB0940M001) */
    public static final String MAIL_TEMPLATE_ID_01 = "NSBB0940M001";

    /** Mail Group From(FROM0005) */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

// START 2016/11/18 [QC#16005,MOD]
    /** Mail Group To(NSBB094001) */
    public static final String MAIL_GROUP_ID_TO = "NSBB0940";
// END   2016/11/18 [QC#16005,MOD]

    /** Mail Character Set */
    public static final String MAIL_CHARSET = "ISO-8859-1";

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmss";

    /** Mail Date Format : MM/dd/yyyy HH:mm:ss.SSS */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

    /** Mail Item (${batchId}) */
    public static final String MAIL_ITEM_BATCH_ID = "batchId";

    /** Mail Item (${errDate}) */
    public static final String MAIL_ITEM_ERR_DATE = "errDate";

    /** Mail Item (${message}) */
    public static final String MAIL_ITEM_ERR_MSG = "message";

    // START 2017/01/04 K.Kitachi [QC#16316, ADD]
    public static final String INTERFACE_ID = "NSBI1240";
    // END 2017/01/04 K.Kitachi [QC#16316, ADD]
    
    // START 2017/09/06 U.Kim [QC#20868, ADD]
    public static final String RTL_SWH_CD_G = "G";
    // END 2017/09/06 U.Kim [QC#20868, ADD]

    // START 2021/11/17 R.Cabral [QC#59387, ADD]
    /** Effective Thru Date Expired Const */
    public static final String EFF_THRU_DT_EXPIRED_CONST = "235959999";

    /** RTL_WH_CD Column */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /** RTL_SWH_CD Column */
    public static final String RTL_SWH_CD = "RTL_SWH_CD";

    /** TECH_TOC_CD Column */
    public static final String TECH_TOC_CD = "TECH_TOC_CD";

    /** Default EFF_THRU_DT */
    public static final String DEFAULT_EFF_THRU_DT = "99900101";
    // END   2021/11/17 R.Cabral [QC#59387, ADD]

    // START 2023/06/19 K.Chiba [QC#61262, ADD]
    /** WH_OWNR_PSN_TP_CD Column */
    public static final String WH_OWNR_PSN_TP_CD = "WH_OWNR_PSN_TP_CD";
    // END 2023/06/19 K.Chiba [QC#61262, ADD]

}
