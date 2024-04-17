/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2240.constant;

/**
 *<pre>
 * NWAL2240Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         S.Ohki          Create          N/A
 * 2016/04/26   Hitachi         K.Kojima        Update          QC#5503
 * 2018/01/09   Fujitsu         N.Sugiura       Update          QC#18460
 * 2023/11/22   Hitachi         K.Ishizuka      Update          QC#53408
 *</pre>
 */
public class NWAL2240Constant {

    /** Business ID -- NWAL2240 */
    public static final String BUSINESS_ID = "NWAL2240";

    /** Screen ID. */
    public static final String SCREEN_ID = "NWAL2240Scrn00";

    /** Message kind Error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** message name order number */
    public static final String MESSAGE_NAME_ORDER_NUM = "Order Number";

    /** Pull down Delimiter */
    public static final String PULLDOWN_DELIMITER = ":";

    /** AM/PM Pulldown Code */
    public static final String[] AM_PM_PULLDOWN_CD = {"0", "1" };

    /** AM/PM Pulldown Name */
    public static final String[] AM_PM_PULLDOWN_NM = {"AM", "PM" };

    /** NEW */
    public static final String MODE_NEW = "01";

    /** MODIFY */
    public static final String MODE_MODIFY = "02";

    /** DELETE */
    public static final String MODE_DELETE = "03";

    /** Delivery Type Code */
    public static final String DELY_TP_CD_INSTALL = "01";

    /** Contact Point Type Code (Phone) */
    public static final String CTAC_PT_TP_CD_PHONE = "02";

    /** Contact Point Type Code (EMail) */
    public static final String CTAC_PT_TP_CD_MAIL = "04";

    /** Contact Point Type Code (Fax) */
    public static final String CTAC_PT_TP_CD_FAX = "05";

    /** Tab EnableFlg Off */
    public static final String TAB_ENABLE_FLG_OFF = "N";

    /** Tab EnableFlg On */
    public static final String TAB_ENABLE_FLG_ON = "Y";

    /** AM */
    public static final String AM_CD = "0";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** This data has been updated by another user.. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** The error was returned by business API. */
    public static final String NWAM0269E = "NWAM0269E";

    /** The entered @ does not exist in Master. */
    public static final String NWAM0181E = "NWAM0181E";

    /**
     * No Order information linked to the entered 'Order Number'
     * exists.
     */
    public static final String NWAM0142E = "NWAM0142E";

    /** The search returned no results. */
    public static final String NWZM0819E = "NWZM0819E";

    /**
     * There are too many search results. Please review search
     * criteria.
     */
    public static final String NWZM0775E = "NWZM0775E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] Search results for does not exist. */
    public static final String NWAM0722E = "NWAM0722E";

    /** Button Display Flag */
    public static final String BUTTON_DISP_FLG_ON = "Y";

    /** Site Survey Material Weights and Dimensions Information */
    public static final String WEIGHTS_AND_DIMENSIONS_INFO_NAME = "Material Weights and Dimensions Information";

    /** Quantity Number Zero */
    public static final int QTY_ZERO = 0;

    /** Valid Count Zero */
    public static final int VALID_COUNT_ZERO = 0;

    /** Row Number Zero */
    public static final int ROW_NUM_ONE = 1;

    /** Time of 24 hours change 12 */
    public static final int TIME_OF_24_HOURS_CHANGE = 12;

    /** Rtl Wh Category Code Dummy */
    public static final String RTL_WH_CATG_CD_DUMMY = "99";

    // START 2016/04/26 K.Kojima [QC#5503,ADD]
    /** XX_EDT_MODE_CD : No Edit */
    public static final String XX_EDT_MODE_CD_NO_EDIT = "0";
    // END 2016/04/26 K.Kojima [QC#5503,ADD]

    /** Default Time **/
    public static final String DEFAULT_TIME = "0000000";

    /** Time Start Position */
    public static final int TIME_START_POS = 8;

    /** Time End Position */
    public static final int TIME_END_POS = 4;

    // START 2023/11/22 K.Ishizuka [QC#53408, ADD]
    /** Service Install Rule Number(No Install:03) */
    public static final String SVC_ISTL_RULE_NUM_NO_INSTALL = "03";
    // END 2023/11/22 K.Ishizuka [QC#53408, ADD]

}
