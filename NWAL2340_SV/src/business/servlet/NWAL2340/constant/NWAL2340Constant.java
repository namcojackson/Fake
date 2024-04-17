/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */

package business.servlet.NWAL2340.constant;

/**
 *<pre>
 * SOM Address Mass Apply
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/22   CITS            S.Tanikawa      Create          N/A
 * 2017/09/07   Fujitsu         R.Nakamura      Update          QC#20974
 *</pre>
 */
public class NWAL2340Constant {

    /** Common button 8 */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** Common button 10 */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NWAL2340";

    /** Screen ID */
    public static final String SCRN_ID = "NWAL2340Scrn00";

    /** Function Code : Search */
    public static final String FUNCTION_CD_SEARCH = "20";

    /** Function Code : Update */
    public static final String FUNCTION_CD_UPDATE = "40";

    /** NMAL6760 Calling Mode: Related Accounts */
    public static final String NMAL6760_DISPLAY_RELATED_ACCOUNTS = "04";

    /** NMAL6760 Calling Mode: Status Active */
    public static final String NMAL6760_STATUS_CD_ACTIVE = "01";

    /** NMAL6760 Calling Mode: Search Mode */
    public static final String NMAL6760_SEARCH_MODE = "02";

    /** NMAL6760 Calling Mode: Ship To */
    public static final String NMAL6760_DISP_HRCH_ACCT_CD_SHIP = "03";

    /** EVENT :EVENT_CMN_CLOSE */
    public static final String EVENT_CMN_CLOSE = "EVENT_CMN_CLOSE";

    /** EVENT :OPEN_WIN_ITEM_OLD */
    public static final String EVENT_OPEN_WIN_SHIP_TO_H = "OpenWin_ShipTo_H";

    /** EVENT :OPEN_WIN_ITEM_NEW */
    public static final String EVENT_OPEN_WIN_SHIP_TO_D = "OpenWin_ShipTo_D";

    // Add Start 2017/09/07 QC#20974
    /** EVENT :OPEN_WIN_ITEM_NEW */
    public static final String SPACE = " ";

    /** EVENT :OPEN_WIN_ITEM_NEW */
    public static final String COMMA = ",";
    // Add End 2017/09/07 QC#20974

}
