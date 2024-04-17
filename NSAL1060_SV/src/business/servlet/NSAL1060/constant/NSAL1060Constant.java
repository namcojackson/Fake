package business.servlet.NSAL1060.constant;

/** <pre>
 * Meter Reading Popup.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   Fujitsu         M.Hara          Create          N/A
 * 2019/11/05   Hitachi         K.Kitachi       Update          QC#54164
 * </pre>
 */
public class NSAL1060Constant {

    /** BUSSINESS_ID (NSAL1060) */
    public static final String BUSINESS_APPLICATION_ID = "NSAL1060";

    /** Screen ID (NSAL1060Scrn00) */
    public static final String SCREEN_ID = "NSAL1060Scrn00";

    /**
     * BUTTON_NAME : btn8
     */
    public static final String BUTTON_NAME_CLEAR = "btn8";

    /**
     * BUTTON_NAME : btn10
     */
    public static final String BUTTON_NAME_RETURN = "btn10";

    /**
     * BUTTON_GUARD : btn8
     */
    public static final String BUTTON_GUARD_CLEAR = "CMN_Clear";

    /**
     * BUTTON_GUARD : btn10
     */
    public static final String BUTTON_GUARD_CLOSE = "CMN_Close";

    /**
     * BUTTON_LABEL : btn8
     */
    public static final String BUTTON_LABEL_CLEAR = "Clear";

    /**
     * BUTTON_LABEL : btn10
     */
    public static final String BUTTON_LABEL_CLOSE = "Close";

    /**
     * Screen Interface Arguments Num.
     */
    public static final int SCREEN_IF_ARGS_NUM = 3;

    // START 2019/11/05 K.Kitachi [QC#54164, ADD]
    /**
     * The selected meter read is used as Start Read for Contract#@.
     */
    public static final String NSAM0752E = "NSAM0752E";
    // END 2019/11/05 K.Kitachi [QC#54164, ADD]
}
