/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB001001;

/**
 * <pre>
 * Machine Master Creation Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 05/05/2013   Fujitsu         Y.Kamide        Create          N/A
 * 10/16/2017   CITS            M.Naito         Update          CSA QC#20246
 * 2018/03/02   Hitachi         T.Tomita        Update          CSA QC#23425
 *</pre>
 */
public interface NSAB001001Constant {

    /** business_id : NSAB0010 */
    String BUSINESS_ID = "NSAB0010";

    /** Fetch size for SSM */
    int FETCH_SIZE_MAX = 1000;

    /**
     * Error Msg : Global Company Code is mandatory.
     */
    String NASM0010E = "NASM0010E";

    /**
     * Error Msg : Sales date cannot be obtained.
     */
    String NASM0011E = "NASM0011E";

    /**
     * Error Msg : Failed to update @ [@].
     */
    String NSAM0001E = "NSAM0001E";

    /**
     * Error Msg : Failed to get Serial Number. [@]
     */
    String NSAM0002E = "NSAM0002E";

    /**
     * Error Msg : An error occurred in API. <APIID: [@], Error Code:
     * [@], Data Key: [@]>
     */
    String NSAM0003E = "NSAM0003E";

    /**
     * Error Msg : The corresponding data does not exist.
     * <TABLE: [@], Data Key: [@]>
     */
    String NSAM0004E = "NSAM0004E";

    /**
     * Dummy Serial#([@]) has been generated because Serial# could not
     * be obtained from [@] Table. ([@])
     */
    String NSAM0021W = "NSAM0021W";

    /**
     * Dummy Serial#([@]) has been generated because Serial# already
     * exists in SVC_MACH_MSTR. ([@])
     */
    String NSAM0022W = "NSAM0022W";

    /** [@] is not registered.(@) */
    public static final String NSAM0069E = "NSAM0069E";

    /** Main Machine data does not exist. [@] */
    public static final String NSAM0108W = "NSAM0108W";

    /** Serial Number could not be obtained.[@] */
    public static final String NSAM0109W = "NSAM0109W";

    /**
     * Dummy Serial#([@]) has been generated because Serial# already
     * exists in DS_ASSET_MSTR. ([@]).
     */
    String NSAM0303W = "NSAM0303W";

    // Add Start 2018/03/02 QC#23425
    /** Main Machine data does not exist. [@] */
    public static final String NSAM0713E = "NSAM0713E";
    // Add End 2018/03/02 QC#23425

    /** mail group id for From */
    String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    String MAIL_GROUP_ID_TO = BUSINESS_ID;

    /** template ID */
    String MAIL_TEMPLATE_ID = BUSINESS_ID + "M001";

    /** mail message header */
    String MAIL_MSG_HEADER = "ErrorMessage";

    /** Date Time Pattern For Mail */
    String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    /** DEFAULT_BOM_LVL */
    String DEFAULT_BOM_LVL = "000000";

    /** DEFAULT_BOM_LVL */
    String GET_SEQ_KEY_SER_NUM = "DS_SER_NUM";

    /** DS_DROP_SHIP_WH_CD */
    String DS_DROP_SHIP_WH_CD = "DS_DROP_SHIP_WH_CD";

    /** Service Machine Transaction Type Code Length */
    int SVC_MACH_TRX_TP_CD_LEN = 20;

    // START 2017/10/16 M.Naito [QC#20246, ADD]
    /** Failed to insert @ table. [@] */
    public static final String NSAM0012E = "NSAM0012E";

    /** default EFF_THRU_DT*/
    public static final String DEF_EFF_THRU_DT = "99991231";
    // END 2017/10/16 M.Naito [QC#20246, ADD]

    // START 2018/03/06 M.Naito [QC#23412, ADD]
    /** DROP_SHIP_WH_CD */
    String DROP_SHIP_WH_CD = "DROP_SHIP_WH_CD";
    // END 2018/03/06 M.Naito [QC#23412, ADD]

    // START 2018/07/12 K.Kitachi [QC#26526, ADD]
    /** Middleware Replace Target Group Code : Tecsys(1) */
    String MW_GRP_CD_TECSYS = "1";
    // END 2018/07/12 K.Kitachi [QC#26526, ADD]
}
