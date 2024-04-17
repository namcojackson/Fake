/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2590.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/11   Fujitsu         C.Yokoi          Create          CSA-QC#4096
 *</pre>
 */
public class NMAL2590Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL2590";

    /** ScreenID */
    public static final String SCREEN_ID = "NMAL2590Scrn00";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    // --------------------------------
    // Message
    // --------------------------------
    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** Please set at least one search criteria. */
    public static final String NMAM0288E = "NMAM0288E";

    /** Input Parameter City, State, County or post is mandatory field. */
    public static final String NMZM0338E = "NMZM0338E";

    // --------------------------------
    // Other
    // --------------------------------
    /** Table ID : A */
    public static final String TBL_ID_A = "A_Top";
}
