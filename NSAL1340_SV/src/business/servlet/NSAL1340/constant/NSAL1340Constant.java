/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1340.constant;

/**
 *<pre>
 * NSAL1340Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/09   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public class NSAL1340Constant {

    /** Business ID */
    public static final String BIZ_ID = "NSAL1340";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NSAL1340Scrn00";

    /** count of parameters */
    public static final int NSAL1340_PRM_CNT = 18;

    /** first index of output parameter */
    public static final int NSAL1340_OUT_PRM_START_IX = 11;

    // --------------------------------
    // Common button
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Could not get the initial parameter. */
    public static final String NSAM0644E = "NSAM0644E";

    /** An input parameter, [@],  has not been set. */
    public static final String NZZM0012E = "NZZM0012E";

    /** Please select only one Check Box. */
    public static final String NZZM0009E = "NZZM0009E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // Variable
    // --------------------------------
    /** Model ID. */
    public static final String MDL_ID = "Model ID";

    /** Model Name. */
    public static final String MDL_NM = "Model Name";

    /** Price category code. */
    public static final String PRC_CATG_CD = "Price category code";

    /** Price meter package PK. */
    public static final String PRC_MTR_PKG_PK = "Price meter package PK";

    /** Price service plan type code. */
    public static final String PRC_SVC_PLN_TP_CD = "Price service plan type code";

    /** Price service control type code. */
    public static final String PRC_SVC_CONTR_TP_CD = "Price service control type code";

    /** Billing meter label code. */
    public static final String BLLG_MTR_LB_CD = "Billing meter label code";

    /** Price base date. */
    public static final String PRC_BASE_DT = "Price base date";
}
