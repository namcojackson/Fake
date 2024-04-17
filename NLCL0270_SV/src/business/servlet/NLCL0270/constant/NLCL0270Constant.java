/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */

package business.servlet.NLCL0270.constant;

/**
 *<pre>
 * Supersession Inventory Inquiry Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/20/2015   CITS            M.Ito           Create          N/A
 *</pre>
 */
public interface NLCL0270Constant {

    /**
     * SCREEN_ID
     */
    String SCREEN_ID = "NLCL0270Scrn00";

    /**
     * BUSINESS_ID
     */
    String BUSINESS_ID = "NLCL0270";

    /**
     * FUNCID_REFERENCE
     */
    String FUNCID_REFERENCE = "NLCL0270T010";

    /**
     * FUNCTION_CD_SEARCH
     */
    String FUNCTION_CD_SEARCH = "20";

    /**
     * Common button 8
     */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 10
     */
    String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /**
     * Business button Search
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * SuperSession Item ID
     */
    public static final String SUPERSESSION_ITEM_ID = "id_row_";

    /** MDSE_DIGIT degit:8 */
    public static final int MDSE_8_DIGIT = 8;

    /**
     * A
     */
    public static final String TABLE_A = "A";
    /** Event Name : OpenWin_LocInfoForWh */
    public static final String EVENT_NM_OPENWIN_LOC_INFO_FOR_WH = "OpenWin_LocInfoForWh";

    /** Event Name : OpenWin_LocInfoForSwh */
    public static final String EVENT_NM_OPENWIN_LOC_INFO_FOR_SWH = "OpenWin_LocInfoForSwh";

    // =================================================
    // Display character string for message
    // =================================================
    /** Original Item Code */
    public static final String NAME_FOR_MESSAGE_MDSE_CD = "Original Item Code";

    /** Original Item Description */
    public static final String NAME_FOR_MESSAGE_MDSE_NM = "Original Item Description";

    /** Stock Status */
    public static final String NAME_FOR_MESSAGE_STK_STS_CD = "Stock Status";

    /** Warehouse Name */
    public static final String NAME_FOR_MESSAGE_RTL_WH_NM = "Warehouse Name";

    /** Sub Warehouse Name */
    public static final String NAME_FOR_MESSAGE_RTL_SWH_NM = "Sub Warehouse Name";

    // =================================================
    // Message Code
    // =================================================
    /**
     * ZZZM9007E:The field of [@] is not input.
     */
    public static final String ZZZM9007E = "ZZZM9007E";

}
