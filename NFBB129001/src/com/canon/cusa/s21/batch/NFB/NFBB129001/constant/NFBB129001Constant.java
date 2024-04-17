/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB129001.constant;

/**
 * <pre>
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/04/2016   Hitachi         J.Kim           Create          N/A
 * 05/25/2017   CITS            T.Tokutomi      Update          RS#8439
 * </pre>
 */
public class NFBB129001Constant {

    /**
     * BATCH_PROGRAM_ID(NFBB1290)
     */
    public static final String BUSINESS_ID = "NFBB1290";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Max Commit Number */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /**
     * Error Msg : Global Company Code is required.
     */
    public static final String NFBM0199E = "NFBM0199E";

    /** Error Msg : There is no parameter in [@] */
    public static final String NFBM0207E = "NFBM0207E";

    /** Error Msg : @ doesn't exist. */
    public static final String NFBM0044E = "NFBM0044E";

    /** NDSE_CD(REFUND) */
    public static final String MDSE_CD_REFUND = "REFUND";

    /** NONE */
    public static final String NONE = "NONE";

    /** yyyyMMdd */
    public static final String YYYYMMDD = "yyyyMMdd";

    // VAR_CHAR_CONST
    /** VAR_CHAR_CONST : AP_LINE_TP_ITEM */
    public static final String VARCHAR_CONST_AP_LINE_TP_ITEM = "AP_LINE_TP_ITEM";

    /** 00 */
    public static final String STR_00 = "00";

    /** REFUND */
    public static final String REFUND = "REFUND";
}
