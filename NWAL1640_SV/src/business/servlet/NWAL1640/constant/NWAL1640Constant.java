/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1640.constant;

/**
 *<pre>
 * NWAL1640Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         C.Yokoi         Create          N/A
 * 2016/05/20   SRAA            Y.Chen          Update          QC#4505
 *</pre>
 */
public class NWAL1640Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1640";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1640Scrn00";

    // --------------------------------
    // Common button for Popup
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] is mandantory value. */
    public static final String NWAM0298E = "NWAM0298E";

    /** Could not get the initial parameter. */
    public static final String NWAM0270E = "NWAM0270E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // Valuable
    // --------------------------------
    /** NMAL6780 Call Param "DsAcctRelnTpCd" : 99:Bill To or Ship To */
    public static final String BILL_TO_SHIP_TO = "99";
    
    // 2016/02/22 S21_NA#2140 Add Start ------------
    /** MODE_REFERENCE = "10" */
    public static final String MODE_REFERENCE = "10";
    // 2016/02/22 S21_NA#2140 Add End ------------
    
    // QC#4505
    /** Get Address Button */
    public static final String GET_ADDR_BTN = "GetAddress";
    
    /** EVENT_OPEN_WIN_CITY */
    public static final String EVENT_OPEN_WIN_CITY = "OpenWin_City";

    /** EVENT_OPEN_WIN_POST */
    public static final String EVENT_OPEN_WIN_POST = "OpenWin_Post";

    /** EVENT_OPEN_WIN_STATE */
    public static final String EVENT_OPEN_WIN_STATE = "OpenWin_State";

}
