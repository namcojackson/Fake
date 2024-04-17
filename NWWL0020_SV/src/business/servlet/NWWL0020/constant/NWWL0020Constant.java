/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0020.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NWWL0020Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/28   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0020Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWWL0020";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWWL0020Scrn00";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit" };

    /** F3 : Apply */
    public static final String[] BTN_CMN_APL = {"btn3", "CMN_Apply", "Apply" };

    /** F4 : Apply */
    public static final String[] BTN_CMN_APR = {"btn4", "CMN_Approve", "Approve" };

    /** F5 : Reject */
    public static final String[] BTN_CMN_RJT = {"btn5", "CMN_Reject", "Reject" };

    /** F6 : Download */
    public static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /** F7 : Delete */
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** The maximum number of data has been exceeded. */
    public static final String NWWM0001E = "NWWM0001E";

    /** [@] is not selected. */
    public static final String NWWM0002E = "NWWM0002E";

    /** [@] it has not been entered. */
    public static final String NWWM0004E = "NWWM0004E";

    /** For [@], please enter [@] or a later date. */
    public static final String NWWM0005E = "NWWM0005E";

    /** Please enter a date later than today. */
    public static final String NWWM0006E = "NWWM0006E";

    /** For [@], please enter [@] or a later time. */
    public static final String NWWM0007E = "NWWM0007E";

    /** In the case of Weekly, please be sure to select the Run Days. */
    public static final String NWWM0008E = "NWWM0008E";

    /** Input ban to SQL character [@] has been entered. */
    public static final String NWWM0010E = "NWWM0010E";

    /** Email address is not valid. */
    public static final String NWWM0011E = "NWWM0011E";

    /** There are items that are not entered in the column list. */
    public static final String NWWM0012E = "NWWM0012E";

    /**
     * Additional Action, please be performed for each Submit 1
     * review.
     */
    public static final String NWWM0017E = "NWWM0017E";

    /** The specified format is incorrect. It must be @ */
    public static final String NWWM0026E = "NWWM0026E";

    /** Entered time is incorrect. */
    public static final String NWWM0027E = "NWWM0027E";

    /** [@] field has too many digits entered. */
    public static final String NWWM0028E = "NWWM0028E";

    /** Set number exceeds the maximum number. Maximum number: [@] */
    public static final String NWWM0029E = "NWWM0029E";

    /** Colon */
    public static final String COLON = ":";

    /** TAB Name (Header) */
    public static final String TAB_NAME_HEADER = "Header";

    /** TAB Name (Sql) */
    public static final String TAB_NAME_SQL = "Sql";

    /** TAB Name (Action Detail) */
    public static final String TAB_NAME_ACTION_DETAIL = "ActionDetail";

    /** Time check Pattern */
    public static final String CHK_TIME_PATTERN = "(([0-1][0-9])|(2[0-3])):[0-5][0-9]";

    /** Time Format */
    public static final String TIME_FORMAT = "9999";

    /** Popup Window Title */
    public static final String POPUP_WIN_TITLE = "Distribution Search Popup";

    /** Popup Select Table Name */
    public static final String POPUP_SELECT_TABLE_NAME = "NTFY_DIST_LIST";

    /** Popup Column Display Name(Distribute ID) */
    public static final String POPUP_COLUMN_DISP_NAME_DIST_ID = "Distribute ID";

    /** Popup Column Name(Distribute ID) */
    public static final String POPUP_COLUMN_NAME_DIST_ID = "NTFY_DIST_LIST_ID";

    /** Popup Column Display Name(Distribute Name) */
    public static final String POPUP_COLUMN_DISP_NAME_DIST_NM = "Distribute Name";

    /** Popup Column Name(Distribute Name) */
    public static final String POPUP_COLUMN_NAME_DIST_NM = "NTFY_DIST_LIST_NM";

    /** Popup Sort Key */
    public static final String POPUP_SORT_KEY_ASC = "ASC";

    /** Select Interval Radio (Interval) */
    public static final BigDecimal SEL_INTVL_RADIO_INTVL = BigDecimal.valueOf(1);

    /** Select Interval Radio (Minutes after each hour) */
    public static final BigDecimal SEL_INTVL_RADIO_MIN_AFTR_HOUR = BigDecimal.valueOf(2);

}
