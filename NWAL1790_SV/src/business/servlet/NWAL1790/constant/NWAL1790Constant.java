/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1790.constant;

/**
 *<pre>
 * NWAL1790Constant
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   CUSA             T.Murai        Create          N/A
 * 2016/10/06   Fujitsu          Mz.Takahashi   Update          QC#14431
 *</pre>
 */
public class NWAL1790Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL17900";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1790Scrn00";

    // --------------------------------
    // Common
    // --------------------------------
    /** Max Detail */
    public static final int MAX_DETAIL = 20;

    /** EMail check Pattern */
    // 2016/10/06 QC#1443 Del --------------
    //public static final String CHK_EMAIL_PATTERN = "^[a-zA-Z0-9]+([\\w\\.\\-]*[\\w\\-])*@([\\w\\-]+\\.)+[\\w\\-]+$";

    /** Detail Table Id */
    public static final String DETAIL_TABLE_ID = "A";

    /** 0 */
    public static final int INDEX_0 = 0;

    /** 1 */
    public static final int INDEX_1 = 1;

    /** 2 */
    public static final int INDEX_2 = 2;

    /** 3 */
    public static final int INDEX_3 = 3;

    /** 4 */
    public static final int INDEX_4 = 4;

    /** Confirmation Flag */
    public static final String CONFIRMATION_FLAG = "1";

    /** Tracking Flag */
    public static final String TRACKING_FLAG = "2";

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
    // Message
    // --------------------------------
    /** ZZZM9025E */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** NWAM0664E */
    public static final String NWAM0664E = "NWAM0664E";

    /** NWAM0186E */
    public static final String NWAM0186E = "NWAM0186E";

    /** Process Mode */
    public static final String PROCESS_MODE = "Process Mode";

    /** EMail Format */
    public static final String EMAIL_FORMAT = "aaa@bbb";

    /** EMail Address */
    public static final String EMAIL_ADDRESS = "EMail Address";
}
