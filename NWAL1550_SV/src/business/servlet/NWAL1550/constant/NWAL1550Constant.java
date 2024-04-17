/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1550.constant;

/**
 *<pre>
 * NWAL1550Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         S.Ohki          Create          N/A
 * 2017/02/27   Fujitsu         T.Yoshida       Update          S21_NA#16035
 *</pre>
 */
public class NWAL1550Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1550";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1550Scrn00";

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

    /** Order Number has not been entered. */
    public static final String NWAM0014E = "NWAM0014E";

    /**
     * No Order information linked to the entered 'Order Number'
     * exists.
     */
    public static final String NWAM0142E = "NWAM0142E";

    /** No search result was found. */
    public static final String NWAM0006I = "NWAM0006I";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** An error has occurred in the called API [@]. */
    public static final String NWAM0189E = "NWAM0189E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** Select at least one of [@]. */
    public static final String NWAM0634E = "NWAM0634E";

    /** Please correct the error fields, and then try again. */
    public static final String NWAM0010E = "NWAM0010E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** Only latest version of data could be updated. */
    public static final String NWZM1342E = "NWZM1342E";

    /** Order information exists. */
    public static final int COLUMN_NUM_INCORRECT_VALUE = 5;

    /** Work Flow Name. */
    public static final String CONTEXT_PROCESS_WORK_FLOW_NM = "XXXXXXX";

    /** Document ID. */
    public static final String CONTEXT_PROCESS_DOCUMENT_ID = "XXXXXXX";

    /** Decimal digits Number 2. */
    public static final int DECIMAL_DIGITS_NUM_TWO = 2;

    /** Decimal digits Number 0. */
    public static final int DECIMAL_DIGITS_NUM_ZERO = 0;

    /** Back Ground Color Red. */
    public static final String BACK_GROUND_COLOR_RED = "#FF6060";

    /** Back Ground Color Yellow. */
    public static final String BACK_GROUND_COLOR_YELLOW = "#FFFF00";

    /** Sharp. */
    public static final String SHARP = "#";

    // S21_NA#16035 Add Start
    /** Reference Authority */
    public static final String REF_AUTH = "NWAL1550T020";

    /** Execute DI Check Button Event Name */
    public static final String BTN_EXEC_DI_CHK_EVENT_NM = "ExecDIChk";
    // S21_NA#16035 Add End
}
