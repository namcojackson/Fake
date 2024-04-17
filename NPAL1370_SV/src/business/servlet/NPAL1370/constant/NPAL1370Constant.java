/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1370.constant;

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

    /**
     * Flag on N
     */
    public static final String FLAG_ON_N = "N";

    // ======================================
    // Function Code
    // ======================================
    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    // =================================================
    // Event Name
    // =================================================

    /**
     * Event Name : OpenWin_CopyfromWH
     */
    public static final String EVENT_OPEN_WIN_CPY_FRM_WH = "OpenWin_CopyfromWH";

    /**
     * Event Name : OpenWin_CopyfromSWH
     */
    public static final String EVENT_OPEN_WIN_CPY_FRM_SWH = "OpenWin_CopyfromSWH";

    /**
     * Event Name : OpenWin_CopytoWH
     */
    public static final String EVENT_OPEN_WIN_CPY_TO_WH = "OpenWin_CopytoWH";

    /**
     * Event Name : OpenWin_CopytoSWH
     */
    public static final String EVENT_OPEN_WIN_CPY_TO_SWH = "OpenWin_CopytoSWH";

    /**
     * Event Name : OpenWin_CopytoSWH
     */
    public static final String EVENT_CMN_CLOSE = "CMN_Close";

    // ======================================
    // Screen Button Name
    // ======================================
    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_BTN_10 = {"btn10", "CMN_Close", "Close" };

    /**
     * Button SetCopyfromWHName
     */
    public static final String BTN_SET_CPY_FRM_WH_NM = "SetCopyfromWHName";

    /**
     * Button SetCopyfromSWHName
     */
    public static final String BTN_SET_CPY_FRM_SWH_NM = "SetCopyfromSWHName";

    /**
     * Button SetCopytoWHName
     */
    public static final String BTN_SET_CPY_TO_WH_NM = "SetCopytoWHName";

    /**
     * Button SetCopytoSWHName
     */
    public static final String BTN_SET_CPY_TO_SWH_NM = "SetCopytoSWHName";

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

    /**
     * Item Copy to Enabled Item Only
     */
    public static final String DISPLAY_NM_ENBL_ITM_CHKBOX = "Copy to Enabled Item Only Check Box";

}
