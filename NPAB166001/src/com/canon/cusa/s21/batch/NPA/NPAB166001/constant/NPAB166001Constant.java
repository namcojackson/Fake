/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB166001.constant;

/**
 * <pre>
 * Create Open Tech Request Work Data Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/07/2016   Hitachi         T.Iwamoto       Create          NA
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 12/08/2017   CITS            M.Naito         Update          QC#18572
 * 04/09/2018   Hitachi         T.Tomita        Update          QC#23804
 * </pre>
 */
public class NPAB166001Constant {

    /** Global Company Code is required. */
    public static final String NPZM0001E = "NPZM0001E";

    /** No search results found of [@]. */
    public static final String NPAM0020E = "NPAM0020E";

    /** Error occurred while calling WebService. */
    public static final String NPAM1481E = "NPAM1481E";

    /** Could not retrieve an email address. */
    public static final String NPZM0125E = "NPZM0125E";

    /** Could not retrieve mail template. */
    public static final String NPZM0128E = "NPZM0128E";

    /** Error Message Text: Sales Date */
    public static final String MSG_TXT_SALES_DATE = "Sales Date";

    /** Error Message Text: DS_COND_CONST */
    public static final String DS_COND_CONST = "DS_COND_CONST";

    /** MAX COUNT NUMBER */
    public static final int MAX_COUNT_NUMBER = 1000;

    /** LAST_DATE_SHIPPED_FORMAT */
    public static final String LAST_DATE_SHIPPED_FORMAT = "LAST_DATE_SHIPP_FMT";

    /** ETA_DATE_FORMT */
    public static final String ETA_DATE_FORMT = "ETA_DATE_FORMT";

    /** BATCH_PROGRAM_ID(NPAB1660) */
    public static final String BATCH_PROGRAM_ID = "NPAB1660";

    /** Mail Template ID(NPAB1660M001) */
    public static final String MAIL_TEMPLATE_ID_01 = "NPAB1660M001";

    /** Mail Group From(FROM0005) */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Mail Group To(NPAB166001) */
    public static final String MAIL_GROUP_ID_TO = "NPAB1660";

    /** Mail Character Set */
    public static final String MAIL_CHARSET = "ISO-8859-1";

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmss";

    // START 2017/12/08 M.Naito [QC#18572,ADD]
    /** Date Format(MM/dd/yyyy) */
    public static final String DT_FORMAT = "MM/dd/yyyy";
    // END 2017/12/08 M.Naito [QC#18572,ADD]

    /** Mail Date Format : MM/dd/yyyy HH:mm:ss.SSS */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

    /** Mail Item (${batchId}) */
    public static final String MAIL_ITEM_BATCH_ID = "batchId";

    /** Mail Item (${errDate}) */
    public static final String MAIL_ITEM_ERR_DATE = "errDate";

    /** Mail Item (${message}) */
    public static final String MAIL_ITEM_ERR_MSG = "message";

    // START 2017/01/04 K.Kitachi [QC#16316, ADD]
    public static final String INTERFACE_ID = "NSBI1140";
    // END 2017/01/04 K.Kitachi [QC#16316, ADD]

    // Add Start 2018/04/09 QC#23804
    /**
     * Replace Character at CARR_URL.CARR_TRK_URL.
     */
    public static final String REPLACE_CHAR = "\\$\\$";
    // Add End 2018/04/09 QC#23804

    public static final String MNX = "MNX";
}
