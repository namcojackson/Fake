/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLA.NLAB311001.constant;

/**
 * <pre>
 * NLAB311001Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 23/19/2015   Hitachi         J.Kim           Create          N/A
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 2017/01/13   Hitachi         K.Kojima        Update          QC#16905
 * 2020/04/25   CITS            T.Wada          Update          QC#55791-1
 * </pre>
 */
public class NLAB311001Constant {

    /** A value is not set in the required field. Field Name: [@] */
    public static final String NLAM1118E = "NLAM1118E";

    /** It failed to get the [@]. */
    public static final String NLAM1285E = "NLAM1285E";

    /** Message ID : NSAM0069E */
    public static final String NSAM0069E = "NSAM0069E";

    /** Message ID : NLAM1315E */
    public static final String NLAM1315E = "NLAM1315E";

    /** Error Message Text: Global Company Code */
    public static final String MSG_TXT_GLBL_CMPY_CD = "Global Company Code";

    /** Error Message Text: Sales Date */
    public static final String MSG_TXT_SALES_DATE = "Sales Date";

    /** Column name : TECH_INVTY_AGING_WRK_PK */
    public static final String TECH_INVTY_AGING_WRK_PK = "TECH_INVTY_AGING_WRK_PK";

    /** MAX COUNT NUMBER */
    public static final int MAX_COUNT_NUMBER = 1000;

    /**
     * BATCH_PROGRAM_ID(NLAB3110)
     */
    public static final String BATCH_PROGRAM_ID = "NLAB3110";

    /** Mail Template ID(NLAB3110M001) */
    public static final String MAIL_TEMPLATE_ID_01 = "NLAB3110M001";

    /** Mail Group From(FROM0005) */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Mail Group To(NLAB311001) */
    public static final String MAIL_GROUP_ID_TO = "NLAB311001";

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

    // START 2017/01/04 K.Kitachi [QC#16316, ADD]
    public static final String INTERFACE_ID = "NSBI1080";
    // END 2017/01/04 K.Kitachi [QC#16316, ADD]

    // START 2017/01/13 K.Kojima [QC#16905,ADD]
    /** Mode : Daily */
    public static final String MODE_DAILY = "01";

    /** Mode : Nightly */
    public static final String MODE_NIGHTLY = "02";

    /** Process Program ID */
    public static final String PROC_PGM_ID = "NLAB311001";

    /** DS_BIZ_LAST_UPD_TS DEFAULT. */
    public static final String DS_BIZ_LAST_UPD_TS_DEFAULT = "00000101000000";

    /** TimeStamp Format(yyyyMMddHHmmssSSS). */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";
    // END 2017/01/13 K.Kojima [QC#16905,ADD]

    // QC#55791-1 Add Start
    /** DS_BIZ_LAST_UPD_TS DEFAULT. */
    public static final String DEF_TMSTMP = "000000000";
    // QC#55791-1 Add End

}
