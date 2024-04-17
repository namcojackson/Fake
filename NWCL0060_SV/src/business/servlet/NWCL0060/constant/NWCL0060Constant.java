/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0060.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/25   Fujitsu         M.Nakamura      Create          N/A
 * 2022/03/31   CITS            A.Cullano       Update          QC#59828
 * 2023/04/07   Hitachi         S.Nakatani      Update          QC#61270
 *</pre>
 */
public class NWCL0060Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWCL0060";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWCL0060Scrn00";

    //--------------------------------
    // Common button
    //--------------------------------
    ///** Common button - Clear Name */
    public static final String BTN_08_CLE_NAME  = "btn8";
    ///** Common button - Clear Guard */
    public static final String BTN_08_CLE_GUARD = "CMN_Clear";
    ///** Common button - Clear Label */
    public static final String BTN_08_CLE_LABEL = "";

    /** Common button - Close Name */
    public static final String BTN_10_CLS_NAME  = "btn10";
    /** Common button - Close Guard */
    public static final String BTN_10_CLS_GUARD = "CMN_Close";
    /** Common button - Close Label */
    public static final String BTN_10_CLS_LABEL = "Close";

    /** Common button - Cancel */
    public static final String BTN_CANCEL_LABEL = "Cancel";

    /** Common button - Send Mail Label */
    public static final String BTN_SEND_MAIL_LABEL = "SendMail";

    // START 2023/04/07 S.Nakatani [QC#61270,ADD]
    /** NWCL0060 Params Length */
    public static final int NWCL0060_PARAMS_LENGTH = 6;

    /** NFDL0100 Params Length */
    public static final int NFDL0100_PARAMS_LENGTH = 7;
    // END 2023/04/07 S.Nakatani [QC#61270,ADD]

    //--------------------------------
    // Message ID
    //--------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    // START 2022/03/31 A.Cullano [QC#59828, ADD]
    /** From Email Address: Default */
    public static final int FROM_EML_ADDR_DEF = 1;
    // END 2022/03/31 A.Cullano [QC#59828, ADD]
}
