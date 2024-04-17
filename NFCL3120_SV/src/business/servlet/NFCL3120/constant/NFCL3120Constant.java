/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3120.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         T.Tanaka        Create          N/A
 * 2016/08/02   Hitachi         K.Kojima        Update          QC#5521
 * 2016/12/27   Fujitsu         H.Ikeda         Update          QC#12865
 *</pre>
 */
public interface NFCL3120Constant {

    // START 2016/08/02 K.Kojima [QC#5521,ADD]
    /** BUSINESS_ID */
    public static final String BUSINESS_ID = "NFCL3120";

    /** SCREEN_ID */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";
    // END 2016/08/02 K.Kojima [QC#5521,ADD]

    // Start 2016/12/27 H.Ikeda [QC#12865,ADD]
    /** Function ID NFCL3120T020 (Update) */
    public static final String FUNC_ID_UPDATE = "NFCL3120T020";

    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";
    // End   2016/12/27 H.Ikeda [QC#12865,ADD]
}
