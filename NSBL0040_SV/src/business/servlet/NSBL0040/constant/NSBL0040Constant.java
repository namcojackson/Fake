package business.servlet.NSBL0040.constant;

/**
 * Credit Approval
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/01   SRA             Otsuji          Create          N/A
 * 2019/10/02   Hitachi         K.Kitachi       Update          QC#53692
 *</pre>
 */
public interface NSBL0040Constant {

    /**
     * Business ID
     */
    String BUSINESS_ID = "NSBL0040";

    /**
     * Screen ID
     */
    String SCREEN_ID = "NSBL0040Scrn00";

    /**
     * Search function
     */
    String SEARCH_FUNCTION = "20";

    /**
     * Update function
     */
    String UPDATE_FUNCTION = "40";

    /**
     * Common button 1
     */
    String[] COMMON_BUTTON_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    String[] COMMON_BUTTON_2 = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    String[] COMMON_BUTTON_3 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    String[] COMMON_BUTTON_4 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    String[] COMMON_BUTTON_5 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    String[] COMMON_BUTTON_6 = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    String[] COMMON_BUTTON_7 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    String[] COMMON_BUTTON_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    String[] COMMON_BUTTON_9 = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    String[] COMMON_BUTTON_10 = {"btn10", "CMN_Return", "Return" };

    /**
     * Search_BillTo button
     */
    String SEARCH_BILL_TO_BUTTON = "Search_BillTo";

    /**
     * Search_SellTo button
     */
    String SEARCH_SELL_TO_BUTTON = "Search_SellTo";

    /**
     * Search_ShipTo button
     */
    String SEARCH_SHIP_TO_BUTTON = "Search_ShipTo";

    /**
     * Search button
     */
    String SEARCH_BUTTON = "Search";

    /**
     * SelectAll button
     */
    String SELECT_ALL_BUTTON = "SelectAll";

    /**
     * UnSelectAll button
     */
    String UN_SELECT_ALL_BUTTON = "UnSelectAll";

    /**
     * [@] should be filled.
     */
    String ZZZM9024E = "ZZZM9024E";

    /**
     * Please select item(s) to approve.
     */
    String NSBM0015E = "NSBM0015E";

    // START 2019/10/02 K.Kitachi [QC#53692, ADD]
    /**
     * OpenWin_BillTo
     */
    String OPENWIN_BILLTO = "OpenWin_BillTo";

    /**
     * OpenWin_SellTo
     */
    String OPENWIN_SELLTO = "OpenWin_SellTo";

    /**
     * OpenWin_ShipTo
     */
    String OPENWIN_SHIPTO = "OpenWin_ShipTo";

    /**
     * Button Close Button Event Name
     */
    String BTN_CMN_CLOSE_EVENT_NM = "CMN_Close";
    // END 2019/10/02 K.Kitachi [QC#53692, ADD]
}
