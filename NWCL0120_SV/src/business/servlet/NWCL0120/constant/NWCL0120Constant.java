/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0120.constant;

/**
 *<pre>
 * NWCL0120Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWCL0120Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWCL0120";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWCL0120Scrn00";

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

    /** Please enter the date of the previous month earlier. */
    public static final String NWCM0116E = "NWCM0116E";

    /**
     * The invoices will be updated to 'Reviewed'. Is this OK? If this
     * is OK, click Button again.
     */
    public static final String NWCM0118W = "NWCM0118W";

    /** The chronological sequence on the dates for [@] is wrong. */
    public static final String NWCM0002E = "NWCM0002E";

    /** Mandatory field: Please enter the value. */
    public static final String NFCM0039E = "NFCM0039E";

    /** There is no "Invoice" data to be processed. */
    public static final String NWZM0634E = "NWZM0634E";

    /** Contract Manager */
    public static final String AUTH_CONTR_MGR = "NWCL0120T020";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Month Get Day Max Day */
    public static final String MONTH_GET_DAY_MAX_DAY = "MAX_DAY";

    /** Month Get Day Min Day */
    public static final String MONTH_GET_DAY_MIN_DAY = "MIN_DAY";

    /** Column Number Invoice Date. */
    public static final int COLUMN_NUM_INVOICE_DATE = 4;

    /** Column Number Invoice Number. */
    public static final int COLUMN_NUM_INVOICE_NUM = 5;

    /** Decimal digits Number 2. */
    public static final int DECIMAL_DIGITS_NUM_TWO = 2;

    /** Back Ground Color Red. */
    public static final String BACK_GROUND_COLOR_ERR = "pErr";

    /** Sharp. */
    public static final String SHARP = "#";
}
