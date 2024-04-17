/*
 * <pre>Copyright (c) 2024 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2020.constant;

/**
 *<pre>
 * NWAL2020Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/04/10   CITS            M.Kobayashi     Create          QC#63757
 *</pre>
 */
public class NWAL2020Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL2020";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL2020Scrn00";

    /** Authority (Update) */
    public static final String AUTH_UPDATE = "NWAL2020T010";

    // --------------------------------
    // Common button
    // --------------------------------

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Close */
    public static final String[] BTN_CMN_CLO = {"btn10", "CMN_Close", "Close" };

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
