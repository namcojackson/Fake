/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1210.constant;

/**
 *<pre>
 * PO/Inventory Approval History
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/20/2016   CITS            R Shimamoto     Create          N/A
 * 03/08/2016   CITS            K.Ogino         Update          QC#5148
 * 03/24/2016   CITS            T.Tokutomi      Update          QC#5763
 *</pre>
 */
public class NPAL1210Constant {

    /**
     * DB Like search char
     */
    public static final String LIKE_SEARCH_CHAR = "%";

    /**
     * USD Decimal Point (e.g. 50.25)
     */
    public static final int DECIMAL_POINT_USD = 2;

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_BTN_10 = {"btn10", "CMN_Close", "Close" };

    /**
     * Business button SaveSearchOption
     */
    public static final String BTN_SAVE_SEARCH = "SaveSearchOption";

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1210";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1210Scrn00";

    /** Function Inquiry */
    public static final String FUNC_INQUIRY = "NPAL1210T010";

    /** Function Edit */
    public static final String FUNC_EDIT = "NPAL1210T020";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    /**
     * HTML ID :note
     */
    public static final String HTML_ID_NOTE = "note";

    /**
     * Style Attribute :background-color
     */
    public static final String STYLE_BG_COLOR = "background-color";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : Document Source Type
     */
    public static final String DISPLAY_NM_DOCUMENT_SOURCE_TYPE = "Document Source Type";

    /**
     * Display Name : Order Number
     */
    public static final String DISPLAY_NM_ORDER_NUMBER = "Order Number";

    /**
     * Display Name : Seq
     */
    public static final String DISPLAY_NM_SEQ = "Seq";

    /**
     * Display Name : Who
     */
    public static final String DISPLAY_NM_WHO = "Who";

    /**
     * Display Name : Action
     */
    public static final String DISPLAY_NM_ACTION = "Action";

    /**
     * Display Name : Date
     */
    public static final String DISPLAY_NM_DATE = "Date";

    /**
     * Display Name : Note
     */
    public static final String DISPLAY_NM_NOTE = "Note";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NMAM0288E:Please set at least one search criteria.
     */
    public static final String NMAM0288E = "NMAM0288E";

    /**
     * ZZZM9007E:The field of [@] is not input.
     */
    public static final String ZZZM9007E = "ZZZM9007E";

    /**
     * NPAM0009E:Please fill out/select the required field.
     */
    public static final String NPAM0009E = "NPAM0009E";

}
