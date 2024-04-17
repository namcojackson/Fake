/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2330.constant;

/**
 *<pre>
 * NWAL2330Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NWAL2330Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL2330";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL2330Scrn00";

    // --------------------------------
    // Common button for PopUp
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear"};

    /** F10 : Return */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close"};

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Please set at least one search criteria. */
    public static final String NWAM0001E = "NWAM0001E";

    /** There are not enough parameters. */
    public static final String NWAM0755E = "NWAM0755E";

    /** Invoice Date term should be less than {@} dates. */
    public static final String NWAM0756E = "NWAM0756E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";


    /** NUM_CONST_NWAL2330_INV_TERM */
    public static final String NUM_CONST_INV_TERM = "NWAL2330_INV_TERM";


    // --------------------------------
    // PopUp Param
    // --------------------------------
    /** Display Related Accounts(03:Bill To's Only) */
    public static final String NMAL6760_DISPLAY_RELATED_ACCOUNTS_03 = "03";

    /** Display Related Accounts(04:Ship To's Only) */
    public static final String NMAL6760_DISPLAY_RELATED_ACCOUNTS_04 = "04";

    /** Search Mode(02：Quick Lookup) */
    public static final String NMAL6760_SEARCH_MODE_02 = "02";

    /** Status(01:Active Only) */
    public static final String NMAL6760_STATUS_01 = "01";

    /** Display Hirarchey Account(02:Bill To's Only) */
    public static final String NMAL6760_DISPLAY_HIRARCHEY_ACCOUNTS_02 = "02";

    /** Display Hirarchey Account(03:Ship To's Only) */
    public static final String NMAL6760_DISPLAY_HIRARCHEY_ACCOUNTS_03 = "03";

}
