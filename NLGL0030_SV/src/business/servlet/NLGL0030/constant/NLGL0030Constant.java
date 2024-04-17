/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0030.constant;

/**
 * <pre>
 * ForcedItem Master download
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/16   CSAI            M.Shimamura     Create          MW Replace Initial
 * 2017/01/17   CITS            T.Kikuhara      Update          QC#16256
 *</pre>
 */
public interface NLGL0030Constant {

    // **************** Buisiness constant value *****************

    /** Business ID */
    String BIZ_ID = "NLGL0030";

    /** Function Update */
    public static final String FUNCTION_UPDATE = "NLGL0030T020";

    /** Screen ID */
    String SCRN_ID = "NLGL0030Scrn00";

    /** Function Code Search */
    String FUNCTION_CODE_SEARCH = "20";

    /** Function Code Update */
    String FUNCTION_CODE_UPDATE = "40";

    /**
     * Common button 1
     */
    String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /**
     * button Search
     */
    String[] BTN_SEARCH = {"OnClick_Search", "Search" };

    /**
     * Field Value : *Blank
     */
    String FLD_VALUE_BLANK = "";

    /**
     * Field Value : *Zero
     */
    int FLD_VALUE_ZERO = 0;

    // **************** Display character string *****************
    /**
     * Display character string for message(WH)
     */
    String NAME_FOR_MESSAGE_WH = "WH";

    /**
     * Display character string for message(MDSE_CD)
     */
    String NAME_FOR_MESSAGE_ITEM_NUMBER = "Item Number";
}
