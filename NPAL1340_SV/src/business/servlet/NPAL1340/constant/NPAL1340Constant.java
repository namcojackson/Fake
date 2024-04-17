/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1340.constant;

/**
 *<pre>
 * NPAL1340 Drop Ship Release
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/11/11   CSAI            K.Lee           Create          N/A
 * </pre>
 */
public interface NPAL1340Constant {

    /**
     * Business Application ID
     */
    String BUSINESS_APPLICATION_ID = "NPAL1340";

    /**
     * Screen ID
     */
    String SCREEN_ID = "NPAL1340Scrn00";

    /**
     * Message No search results found.
     */
    String NZZM0000E = "NZZM0000E";

    /**
     * Message Either [@] or [@] needs to be set.
     */
    String NPAM1235E = "NPAM1235E";

    /**
     * Message [@] field is mandatory.
     */
    String ZZM9000E = "ZZM9000E";

    /**
     * Common button 1
     */
    String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    String[] BTN_CMN_BTN_2 = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    String[] BTN_CMN_BTN_3 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    String[] BTN_CMN_BTN_9 = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    String[] BTN_CMN_BTN_10 = {"btn10", "CMN_Return", "Return" };

    /**
     * Message Split Item [NPAM1235E]
     */
    String SPLIT_NPAM1235E = "],[";

    // =================================================
    // Event Name
    // =================================================
    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /** Event Name: OpenWin_CarrCode */
    public static final String EVENT_NM_OPENWIN_CARRIER = "OpenWin_Carrier";

    /** Label: Release Qty*/
    public static final String MSG_PARAM_RELEASE_QTY = "Release Qty";

    /**
     * Update authority
     */
    String ROLE_UPDATE = "NPAL1340T020";

    /**
     * Message Please set at least one search criteria.
     */
    public static final String NMAM0288E = "NMAM0288E";
    
    /**
     * Message : Please set either @ or @.
     */
    public static final String NMZM0159E = "NMZM0159E";
}
