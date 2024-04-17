/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2010.constant;

/**
 *<pre>
 * NWAL2010Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Fujitsu         C.Yokoi         Create          N/A
 * 2016/09/23   Fujitsu         R.Nakamura      Update          QC#13958
 * 2018/03/20   Fujitsu         H.Nagashima     Update          QC#24675
 *</pre>
 */
public class NWAL2010Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL2010";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL2010Scrn00";

    /** Authority (Reference) */
    public static final String AUTH_REFERENCE = "NWAL2010T010";

    /** Authority (Update) */
    public static final String AUTH_UPDATE = "NWAL2010T020";

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

    /** Common Return */
    public static final String BTN_RETURN = "CMN_Return";

    /** Search */
    public static final String BTN_SEARCH = "Search";

    /** Insert New Cards */
    public static final String BTN_INSERT_NEW_CARDS = "InsertNewCard";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Value is missing in the parameter's required field. */
    public static final String NWAM0699E = "NWAM0699E";

    /** The credit card information is not registered. */
    public static final String NWZM0971E = "NWZM0971E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /**
     * radio#0
     */
    public static final String RADIO_ID_IN_FIRST_ROW = "radio#0";

    public static final String PAYMENTCH_FAILED = "Paymentech Gateway Authorization failed.";

    public static final String TCEPPS_VI = "VI";

//    public static final String TCEPPS_DS = "DI";
    public static final String TCEPPS_DI = "DI";    //QC#24675 mod

    public static final String TCEPPS_MC = "MC";

    public static final String TCEPPS_AX = "AX";

    public static final String TCEPPS_DS = "DS";    //QC#24675 add

/*QC#24675 del
    public static final String S21_VISA = "VISA";

    public static final String S21_DIN = "DIN";

    public static final String S21_MSTR = "MSTR";

    public static final String S21_AMEX = "AMEX";
*/
    /**
     * Amount
     */
    public static final String API_PARAM_AMOUNT = "0";

    /** Link String Account Number */
    public static final String LINK_STRING_ACCT_NUM = "LINK";

    // Add Start 2016/09/23 QC#13958
    /** Mode Type : Read Only. */
    public static final String MODE_READ_ONLY = "0";
    // Add End 2016/09/23 QC#13958
}
