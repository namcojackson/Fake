/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */

package business.blap.NLBL3130.constant;

/**
 *<pre>
 * Delivery Instruction
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/18/2015   CITS            M.Ito           Create          N/A
 * 02/07/2017   CITS            T.Kikuhara      Update          QC#15586
 * 01/12/2018   CITS            T.Tokutomi      Update          QC#18460_Sol#087
 * 06/05/2018   CITS            K.Ogino         Update          QC#25233
 * 03/14/2019   Fujitsu         T.Ogura         Update          QC#30707
 *</pre>
 */
public interface NLBL3130Constant {

    /** BusinessID */
    public static final String BUSINESS_ID = "NLBL3130";

    // =================================================
    // Event Name
    // =================================================
    /** Event Name : Init */
    public static final String EVENT_NM_NLBL3130_INIT = "NLBL3130_INIT";

    /** Event Name : CMN_Reset */
    public static final String EVENT_NM_NLBL3130SCRN00_CMN_RESET = "NLBL3130Scrn00_CMN_Reset";

    /** Event Name : CMN_Clear */
    public static final String EVENT_NM_NLBL3130SCRN00_CMN_CLEAR = "NLBL3130Scrn00_CMN_Clear";

    /** Event Name : CMN_Submit */
    public static final String EVENT_NM_NLBL3130SCRN00_CMN_SUBMIT = "NLBL3130Scrn00_CMN_Submit";

    // =================================================
    // Constant Value for Time Check 
    // QC#18460_Sol#087 add
    // =================================================
    /** Hour minute string length : HHMM */
    public static final int HOUR_MINUTE_STR_LENGTH = 4;

    /** One day hours */
    public static final int ONE_DAY_HOURS = 24;

    /** One hour minutes */
    public static final int ONE_HOUR_MINUTES = 60;

    /** Half day hours */
    public static final int HALF_DAY_HOURS = 12;

    /** Half day hours */
    public static final String TIME_AM = "AM";
    
    /** Half day hours */
    public static final String TIME_PM = "PM";

    // =================================================
    // Message ID
    // =================================================

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** The record is being locked. */
    public static final String NLBM0050E = "NLBM0050E";

    /** Target data does not exist. */
    public static final String NLBM1063E = "NLBM1063E";

    /** Data insert failure.(ReturnCode = [@]) */
    public static final String ZZZM9012E = "ZZZM9012E";

    /** Data update failure.(ReturnCode = [@]) */
    public static final String ZZZM9013E = "ZZZM9013E";

    /** Site Survey Information cannot be uniquely specified. */
    public static final String NLBM2442W = "NLBM2442W";

    /** [@] field has too many digits entered. */
    public static final String NLBM1370E = "NLBM1370E";    // 2019/03/14 T.Ogura [QC#30707,ADD]

    // =================================================
    // key value of ssm parameter
    // =================================================

    /** timestamp format (time part) */
    public static final String FORMAT_TIMESTAMP_TIME = "hhmmss";

    // =================================================
    // Fix Value
    // =================================================

    /** SHPG_ORD_MSG.SO_MSG_DESC_TXT SIZE */
    public static final int SO_MSG_MAX_LEN = 65;

    /** Add QC#25233 RWS_MSG.RWS_MSG_TXT SIZE */
    public static final int RWS_MSG_MAX_LEN = 65;

}
