/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0270.constant;

/**
 *<pre>
 * Service Pricing Shift Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/18   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public final class NSBL0270Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSBL0270";

    /**
     * The Upload File is empty or only has a header line, therefore
     * it could not be processed. Please confirm the content of the
     * Upload file.
     */
    public static final String ZYEM0004E = "ZYEM0004E";

    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * This data has been updated by another user.
     */
    public static final String ZZZM9004E = "ZZZM9004E";

    /**
     * Failed to update "@".
     */
    public static final String NSAM0031E = "NSAM0031E";

    /**
     * Failed to insert "@".
     */
    public static final String NSAM0032E = "NSAM0032E";

    /**
     * Time setting at [@] is not set.
     */
    public static final String NSAM0461E = "NSAM0461E";

    /**
     * Start Time is greater than End Time at [@].
     */
    public static final String NSAM0462E = "NSAM0462E";

    /**
     * Time setting is overlaped in the Line Of Business[@].
     */
    public static final String NSAM0463E = "NSAM0463E";

    /**
     * Time setting is spaced in the Line Of Business [@].
     */
    public static final String NSAM0464E = "NSAM0464E";

    /**
     * Shift# [@] is duplicated.
     */
    public static final String NSAM0465E = "NSAM0465E";

    /**
     * Please set only one After Hours Shift Flag to off for each Line of Business.
     */
    public static final String NSAM0468E = "NSAM0468E";

    /**
     * No search results found.
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * cannot be added because it is exceeding the maximum number of row [@].
     */
    public static final String NSBM0058E = "NSBM0058E";

    /**
     * Table name:SVC_PRC_SHIFT
     */
    public static final String TBL_NM_SVC_PRC_SHIFT = "SVC_PRC_SHIFT";

    /**
     * VAR_CHAR_CONST:INIT_SVC_PRC_SHIFT_NUM
     */
    public static final String INIT_SVC_PRC_SHIFT_NUM = "INIT_SVC_PRC_SHIFT_NUM";

    /**
     * String : MaxDate
     */
    public static final String MAX_DATE = "29991231";

    /**
     * String : TsStart
     */
    public static final String TS_START = "000000";

    /**
     * String : TsEnd
     */
    public static final String TS_END = "235959";

    /** HHmmss:length */
    public static final int LENGTH_HHMMSS = 6;

    /** HHmm:Start Point */
    public static final int POINT_HHMM_S = 4;

    /** HHmmss:Start Point */
    public static final int POINT_HHMMSS_S = 6;

    /** HHmmss:End Point */
    public static final int POINT_HHMMSS_E = 8;

    /** HH:mm:End Point */
    public static final int POINT_HH_MM_E = 5;

    /** SS:value */
    public static final String VALUE_SS = "00";

    /** COLON */
    public static final String COLON = ":";

    /** COLUMN:LINE_BIZ_TP_CD */
    public static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";

    /** COLUMN:LINE_BIZ_TP_DESC_TXT */
    public static final String LINE_BIZ_TP_DESC_TXT = "LINE_BIZ_TP_DESC_TXT";

    /** FORMAT_HHMMSS */
    public static final String FORMAT_HHMMSS = "HHmmss";

}
