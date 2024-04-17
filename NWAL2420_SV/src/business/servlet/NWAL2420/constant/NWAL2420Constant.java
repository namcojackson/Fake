/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2420.constant;

/**
 *<pre>
 * NWAL2420Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/14   CUSA            H.Tomimatsu     Create          S21_NA#22139
 *</pre>
 */
public class NWAL2420Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL2420";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL2420Scrn00";

    // --------------------------------
    // Common
    // --------------------------------
    /** Max Detail */
    public static final int MAX_DETAIL = 20;

    /** Parameter Length */
    public static final int IDX_6 = 6;

    /** Max Authorization Comment */
    public static final int MAX_COMMENT_LENGTH = 250;

    /** Detail Table Id */
    public static final String DETAIL_TABLE_ID = "A";

    /** Radio button - Customer */
    public static final String RADIO_BTN_CUSTOMER = "1";

    /** Radio button - CSA */
    public static final String RADIO_BTN_CSA = "2";

    /** Parameter Index 0 */
    public static final int IDX_EMAIL_ADDRESS = 0;

    /** Parameter Index 1 */
    public static final int IDX_SUFFIX = 1;

    /** Parameter Index 2 */
    public static final int IDX_MAIL_SEND_FLG = 2;

    /** Parameter Index 3 */
    public static final int IDX_PRINT_FLG = 3;

    /** Parameter Index 4 */
    public static final int IDX_RETURN_SUPPLIES = 4;

    /** Parameter Index 5 */
    public static final int IDX_AUTHORIZATION_COMMENT = 5;

    // --------------------------------
    // Common button
    // --------------------------------

    /** Common button - Clear Name */
    public static final String BTN_08_CLE_NAME = "btn8";

    /** Common button - Clear Guard */
    public static final String BTN_08_CLE_GUARD = "CMN_Clear";

    /** Common button - Clear Label */
    public static final String BTN_08_CLE_LABEL = "Clear";

    /** Common button - Close Name */
    public static final String BTN_10_CLS_NAME = "btn10";

    /** Common button - Close Guard */
    public static final String BTN_10_CLS_GUARD = "CMN_Close";

    /** Common button - Close Label */
    public static final String BTN_10_CLS_LABEL = "Close";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** ZZZM9025E */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** NWAM0664E */
    public static final String NWAM0664E = "NWAM0664E";

    /** NWAM0186E */
    public static final String NWAM0186E = "NWAM0186E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Could not get the initial parameter. */
    public static final String NWAM0270E = "NWAM0270E";

    /** Input length must be less than @ */
    public static final String NWAM0954E = "NWAM0954E";

    /** EMail Format */
    public static final String EMAIL_FORMAT = "aaa@bbb";

    /** EMail Address */
    public static final String EMAIL_ADDRESS = "EMail Address";

    /** Authorization Comment */
    public static final String AUTHORIZATION_COMMENT = "250";

}
