/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6760.constant;

/**
 *<pre>
 * NMAL6760Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/21   Fujitsu         K.Ishizuka      Update          QC#17610
 * 2018/07/05   Fujitsu         T.Aoi           Update          QC#26939
 *</pre>
 */
public class NMAL6760Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL6760";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL6760Scrn00";

    /** Function Code 20 */
    public static final String FUNC_CD_20 = "20";

    // --------------------------------
    // Common button for Popup
    // --------------------------------
    /** You can enter wild card only with keyword. */
    public static final String NMAM8667E = "NMAM8667E";

    /** wild card */
    public static final String WILDCARD = "%";

    /** Clear - Button Name */
    public static final String BTN_08_CLR_NAME = "btn8";

    /** Clear - Guard Condition */
    public static final String BTN_08_CLR_GUARD = "CMN_Clear";

    /** Clear - Label Name */
    public static final String BTN_08_CLR_LABEL = "Clear";

    /** Close - Button Name */
    public static final String BTN_10_CLS_NAME = "btn10";

    /** Close - Guard Condition */
    public static final String BTN_10_CLS_GUARD = "CMN_Close";

    /** Close - Label Name */
    public static final String BTN_10_CLS_LABEL = "Close";

    // 2018/07/05 QC#26939 Add Start
    /** Status (ACTIVE ONLY/ ACTIVE & INACTIVE) */
    public static final String STATUS_CD_ACTIVE = "01";
    public static final String STATUS_CD_ALL = "02";
    public static final String STATUS_NM_ACTIVE = "Active Only";
    public static final String STATUS_NM_ALL = "Active & Inactive";
    // 2018/07/05 QC#26939 Add End

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Display Related Accounts Code: 01 */
    public static final String DISP_RELN_ACCT_CD_ACCT = "01";

    /** Please set at least one search criteria. */
    public static final String NMAM0288E = "NMAM0288E";
}
