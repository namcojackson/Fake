/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0170.constant;

/** 
 *<pre>
 * Customer Account Search Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/07   Hitachi         T.Tsuchida      Create          QC#19574
 *</pre>
 */
public class NFDL0170Constant {

    /** Business ID */
    public static final String BIZ_ID = "NFDL0170";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NFDL0170Scrn00";

    /** Function ID : T010 */
    public static final String NFDL0170T010 = "NFDL0170T010";

    /** Function Code Search:20 */
    public static final String FUNC_CD_SRCH = "20";

    // --------------------------------
    // Common button
    // --------------------------------
    /** Clear - Button Name */
    public static final String BTN_08_CLR_NAME = "btn8";

    /** Clear - Guard Condition */
    public static final String BTN_08_CLR_GUARD = "CMN_Clear";

    /** Clear - Label Name */
    public static final String BTN_08_CLR_LABEL = "Clear";

    /** Close - Button Name */
    public static final String BTN_10_CLS_NAME = "btn10";

    /** Close - Guard Condition */
    public static final String BTN_10_CLS_GUARD = "CMN_Close";

    /** Close - Label Name */
    public static final String BTN_10_CLS_LABEL = "Close";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** ZZSM4300E : User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";

    // --------------------------------
    // Parameter Length
    // --------------------------------
    /** NMAL6760_PRM_LENGTH : 24 */
    public static final int NMAL6760_PRM_LENGTH = 24;

    /** RELN_TP_BILL_TO : 02 */
    public static final String RELN_TP_BILL_TO = "02";

    /** RELN_TP_SHIP_TO : 03 */
    public static final String RELN_TP_SHIP_TO = "03";
}
