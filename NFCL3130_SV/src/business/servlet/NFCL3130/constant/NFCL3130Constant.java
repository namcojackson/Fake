package business.servlet.NFCL3130.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/11   Hitachi         K.Kojima        Update          QC#11576
 * 2016/12/26   Fujitsu         H.Ikeda         Update          QC#12865
 *</pre>
 */
public interface NFCL3130Constant {

    /** Screen Event Name */
    public static enum SCREEN_EVENT_NAME {
        NFCL3130_INIT,
        NFCL3130Scrn00_Click_rcptSrc,
        NFCL3130Scrn00_CMN_Return,
        NFCL3130Scrn00_openCashCOA,
        NFCL3130Scrn00_CMN_Submit,
        NFCL3130Scrn00_CMN_Clear,
        NFCL3130_NWAL1130,
        CMN_Close
    }

    /** Screen Event Name */
    public static enum COA_EVENT {
        NFCL3130_CASH,
        NFCL3130_OACC,
        NFCL3130_RCPT,
        NFCL3130_RMTC,
        NFCL3130_UNAP,
        NFCL3130_UNDF
    }

    // START 2016/07/11 K.Kojima [QC#11576,ADD]
    /** Message ID : ZZM9000E */
    public static final String ZZM9000E = "ZZM9000E";
    // END 2016/07/11 K.Kojima [QC#11576,ADD]

    // Start 2016/12/26 H.Ikeda [QC#12865,ADD]
    /** Business ID */
    public static final String BIZ_ID = "NFCL3130";

    /** Function ID NFCL3130T020 (Update) */
    public static final String FUNC_ID_UPDATE = "NFCL3130T020";

    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";
    // End   2016/12/26 H.Ikeda [QC#12865,ADD]
}
