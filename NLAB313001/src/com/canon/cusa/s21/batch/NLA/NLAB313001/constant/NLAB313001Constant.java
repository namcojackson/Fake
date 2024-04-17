/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLA.NLAB313001.constant;

/**
 * <pre>
 * NLAB313001Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 23/19/2015   Hitachi         J.Kim           Create          N/A
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 05/23/2017   Hitachi         K.Kitachi       Update          QC#18374
 * </pre>
 */
public class NLAB313001Constant {

    /** A value is not set in the required field. Field Name: [@] */
    public static final String NLAM1118E = "NLAM1118E";

    // START 2017/05/23 K.Kitachi [QC#18374, DEL]
//    /** It failed to get the [@]. */
//    public static final String NLAM1285E = "NLAM1285E";
    // END 2017/05/23 K.Kitachi [QC#18374, DEL]

    /** Message ID : NSAM0069E */
    public static final String NSAM0069E = "NSAM0069E";

    /** Message ID : NLAM1315E */
    public static final String NLAM1315E = "NLAM1315E";

    /** Error Message Text: Global Company Code */
    public static final String MSG_TXT_GLBL_CMPY_CD = "Global Company Code";

    // START 2017/05/23 K.Kitachi [QC#18374, DEL]
//    /** Error Message Text: Sales Date */
//    public static final String MSG_TXT_SALES_DATE = "Sales Date";
    // END 2017/05/23 K.Kitachi [QC#18374, DEL]

    /** Column name : TECH_MDSE_LIST_WRK_PK */
    public static final String TECH_MDSE_LIST_WRK_PK = "TECH_MDSE_LIST_WRK_PK";

    /** MAX COUNT NUMBER : */
    public static final int MAX_COUNT_NUMBER = 1000;

    /**
     * BATCH_PROGRAM_ID(NLAB3130)
     */
    public static final String BATCH_PROGRAM_ID = "NLAB3130";

    /** Mail Template ID(NLAB3130M001) */
    public static final String MAIL_TEMPLATE_ID_01 = "NLAB3130M001";

    /** Mail Group From(FROM0005) */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Mail Group To(NLAB313001) */
    public static final String MAIL_GROUP_ID_TO = "NLAB313001";

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
    public static final String INTERFACE_ID = "NSBI1070";
    // END 2017/01/04 K.Kitachi [QC#16316, ADD]
}
