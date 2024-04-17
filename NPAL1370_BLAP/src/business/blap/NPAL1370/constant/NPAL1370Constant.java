/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1370.constant;

/**
 * <pre>
 * Business ID : NPAL1370 Min Max Planning Copy Popup
 * Function Name : Constant
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/03/2015   CITS       Takeshi Tokutomi     Create          N/A
 *</pre>
 */
public class NPAL1370Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1370";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1370Scrn00";

    // =================================================
    // Event Name
    // =================================================

    /**
     * Event NPAL1370Scrn00_SetCopyfromWHName
     */
    public static final String EVENT_SET_CPY_FRM_WH_NM = "NPAL1370Scrn00_SetCopyfromWHName";

    /**
     * Event NPAL1370Scrn00_SetCopyfromSWHName
     */
    public static final String EVENT_SET_CPY_FRM_SWH_NM = "NPAL1370Scrn00_SetCopyfromSWHName";

    /**
     * Event NPAL1370Scrn00_SetCopytoSWHName
     */
    public static final String EVENT_SET_CPY_TO_WH_NM = "NPAL1370Scrn00_SetCopytoWHName";

    /**
     * Event NPAL1370Scrn00_SetCopytoSWHName
     */
    public static final String EVENT_SET_CPY_TO_SWH_NM = "NPAL1370Scrn00_SetCopytoSWHName";

    /**
     * Event NPAL1370Scrn00_CMN_Close
     */
    public static final String EVENT_CMN_CLOSE = "NPAL1370Scrn00_CMN_Close";
    // =================================================
    // SQL Param
    // =================================================

    /**
     * SQL Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * SQL Param Name: Retail Warehouse Code
     */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /**
     * SQL Param Name: Retail Sub Warehouse Code
     */
    public static final String DB_PARAM_RTL_SWH_CD = "rtlSwhCd";

    // =================================================
    // Message Code
    // =================================================

    /**
     * NZZM0010E: The data [@] does not exist in the master.
     */
    public static final String NZZM0010E = "NZZM0010E";

    /**
     * NPAM0076E: [@] does not exist in Master.
     */
    public static final String NPAM0076E = "NPAM0076E";

    // ======================================
    // Screen Item Name
    // ======================================

    /**
     * Item Copy from WH
     */
    public static final String DISPLAY_NM_CPY_FRM_WH = "Copy from WH";

    /**
     * Item Copy from Sub WH
     */
    public static final String DISPLAY_NM_CPY_FRM_SWH = "Copy from Sub WH";

    /**
     * Item Copy to WH
     */
    public static final String DISPLAY_NM_CPY_TO_WH = "Copy to WH";

    /**
     * Item Copy to Sub WH
     */
    public static final String DISPLAY_NM_CPY_TO_SWH = "Copy to Sub WH";

}
