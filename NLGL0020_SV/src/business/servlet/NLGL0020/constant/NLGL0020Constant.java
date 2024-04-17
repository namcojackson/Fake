/**
 * <pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0020.constant;

/**
 * <pre>
 * PO Maintenance
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/30/2013     CSAI            N.Sekine          Create             MW Replace Initial
 * 01/17/2017     CITS            T.Kikuhara        Update            QC#16256
 * 05/23/2017     CITS            S.Endo            Update             RS#3172
 *</pre>
 */
public interface NLGL0020Constant {

    // **************** Buisiness constant value *****************

    /** Business ID */
    String BIZ_ID = "NLGL0020";

    /** Function Update */
    public static final String FUNCTION_UPDATE = "NLGL0020T020";

    /** Screen ID */
    String SCRN_ID = "NLGL0020Scrn00";

    /** Function Code Search */
    String FUNCTION_CODE_SEARCH = "20";

    /** Function Code Update */
    String FUNCTION_CODE_UPDATE = "40";

    /** Button Close Button Event Name */
    String BTN_CMN_CLOSE_EVENT_NM = "CMN_Close";
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
     * button Insert
     */
    String[] BTN_INSERT = {"OnClick_DNLD_InsertRow", "Insert Row" };

    /**
     * button Delete
     */
    String[] BTN_DELETE = {"OnClick_DNLD_DeleteRow", "Delete Row" };

    /**
     * button Check All
     */
    String[] BTN_CHECKALL = {"OnClick_CheckAll", "Check All" };

    /**
     * button UNCheck All
     */
    String[] BTN_UNCHECKALL = {"OnClick_UncheckAll", "UnCheck All" };

    /**
     * Field Value : *Blank
     */
    String FLD_VALUE_BLANK = "";

    /**
     * Field Value : *Zero
     */
    int FLD_VALUE_INT_ZERO = 0;

    /**
     * Field Value : 1
     */
    int FLD_VALUE_INT_1 = 1;

    /**
     * Field Value : "1"
     */
    String FLD_VALUE_COPY = "1";

    /**
     * Field Value : "2"
     */
    String FLD_VALUE_2 = "2";

    /**
     * minimum input value for date item on search condition
     */
    String MIN_DATE_FOR_SEARCH = "00010101";

    /**
     * maximum input value for date item on search condition
     */
    String MAX_DATE_FOR_SEARCH = "99991231";

    /**
     * yyyyMMddHHmmss for DateTime check
     */
    String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * VAR_CHAR_CONST Key Name: Valid copy flag 
     */
    public static final String NLGL_VALID_COPY_FLG = "NLGL_VALID_COPY_FLG";
    // ******************* TAB NAME *******************
    /**
     * Field Value : "POList"
     */
    String TAB_ID_LIST = "POList";

    /**
     * Field Value : "POStatus"
     */
    String TAB_ID_STATUS = "POStatus";

    /**
     * Field Value : "DnldEdt"
     */
    String TAB_ID_DNLD = "DnldEdt";

    /**
     * Field Value : "UpldEdt"
     */
    String TAB_ID_UPD = "UpldEdt";

    // ******************* Button NAME *******************
    /** Prev */
    String BTN_PREV = "Prev";

    /** Next */
    String BTN_NEXT = "Next";

    // **************** Display character string ********
    /**
     * Display character string for message(Submit Type)
     */
    String NAME_FOR_MESSAGE_SUBMIT_TYPE = "Submit Type";

    /**
     * Display character string for message(WH Code)
     */
    String NAME_FOR_MESSAGE_WH = "WH";

    /**
     * Display character string for message(PO#)
     */
    String NAME_FOR_MESSAGE_PO_ID = "PO#";

    /**
     * Display character string for message(DATE for Search)
     */
    String NAME_FOR_MESSAGE_DT_TYP = "DATE Type";

    /**
     * Display character string for message(from date)
     */
    String NAME_FOR_MESSAGE_FROM_DT = "From Date";

    /**
     * Display character string for message(To Date)
     */
    String NAME_FOR_MESSAGE_TO_DT = "To Date";

    /**
     * Display character string for message(Item code)
     */
    String NAME_FOR_MESSAGE_ITEM_CD = "Mdse code";

    /**
     * Display character string for message(Transaction Code)
     */
    String NAME_FOR_MESSAGE_TRX_CD = "Transaction Code";

    /**
     * Display character string for message(Line)
     */
    String NAME_FOR_MESSAGE_LINE = "Line";

    /**
     * Display character string for message(Item)
     */
    String NAME_FOR_MESSAGE_ITEM = "Mdse Code";

    /**
     * Display character string for message(Qty/Units)
     */
    String NAME_FOR_MESSAGE_QTY_UNIT = "Quantity/Units";

    /**
     * Display character string for message(Unit Of Measure)
     */
    String NAME_FOR_MESSAGE_UOM = "UOM";

    /**
     * Display character string for message(StockStatus)
     */
    String NAME_FOR_MESSAGE_STOCK_STS = "Stock Status";

    /**
     * Display character string for message(PROCESS)
     */
    String NAME_FOR_MESSAGE_PROC = "PROCESS";

    /**
     * Display character string for message(RECORDID)
     */
    String NAME_FOR_MESSAGE_REC_ID = "RECORDID";

    /**
     * Display character string for message(TASK)
     */
    String NAME_FOR_MESSAGE_TASK = "Task";

    /**
     * Display character string for message(HOLD_CODE)
     */
    String NAME_FOR_MESSAGE_HLD_CD = "Stock Status";

    /**
     * Display character string for message(QUANTITY)
     */
    String NAME_FOR_MESSAGE_QNTY = "Order Quantity";

    /**
     * Display character string for message(PURCHASE_TYPE)
     */
    String NAME_FOR_MESSAGE_PUR_TYP = "Purchase Type";

    /**
     * Display character string for message(TRANSACTION_DATE)
     */
    String NAME_FOR_MESSAGE_TRN_DT = "Transaction Date";

    /**
     * Display character string for message(INBOUND_ORDER_ID)
     */
    String NAME_FOR_MESSAGE_INBD_ID = "Inbound Order ID";

    /**
     * Display character string for message(Inbound Order TYPE)
     */
    String NAME_FOR_MESSAGE_INBD_TYP = "Inbound Order TYPE";

    /**
     * Display character string for message(Inbound Order Line)
     */
    String NAME_FOR_MESSAGE_INBD_NUM = "Line#";

    /**
     * Upload List
     */
    String NAME_FOR_MESSAGE_LIST = "Upload List";

    /**
     * Display character string for message(WH Code)
     */
    String NAME_FOR_MESSAGE_RTL_WH = "Retail Warehouse";

    /**
     * Display character string for message(WH Code)
     */
    String NAME_FOR_MESSAGE_INVTY_OWNER = "Owner";

    /**
     * Display character string for message(WH Code)
     */
    String NAME_FOR_MESSAGE_CONFIG_ID = "Config ID";

    /**
     * Display character string for message(WH Code)
     */
    String NAME_FOR_MESSAGE_PACK_CD = "Package Code";

    /**
     * Display character string for message(WH Code)
     */
    String NAME_FOR_MESSAGE_SER_APVL_REQ_FLG = "Serial Approval Request";

    /**
     * Display character string for message(WH Code)
     */
    String NAME_FOR_MESSAGE_DPS_CD = "Disposition";

    /**
     *  Value of WH_CD All
     */
    String VAL_WH_CD_NM_ALL = "ALL";

    /**
     * code value of "00:All" in Pull down list of warehouse"
     */
    String WH_ALL_SELECTION_VALUE = "00";

    /**
     * field name check box(Upload)
     */
    String FIELD_NAME_XXCHKBOX_F1 = "xxChkBox_F1";

    /**
     * field name check box(Upload)
     */
    String FIELD_NAME_XXCHKBOX_I1 = "xxChkBox_I1";

    /**
     * field name check box(PO List)
     */
    String FIELD_NAME_XXCHKBOX_A1 = "xxChkBox_A1";

    // **************** Message ID *****************
    /**
     * Please add detail information.
     */
    String NLGM0071E = "NLGM0071E";

    /**
     * Multiple Details cannot be processed at the same time.
     */
    String NLGM0035E = "NLGM0035E";

    /**
     * It has not been selected. Please select.
     */
    String NLGM0036E = "NLGM0036E";

    /**
     * [@] cannot be added because the maximum number of line breaks
     * for [@] has been exceeded.
     */
    String NLGM0054E = "NLGM0054E";

    /**
     * Please enter a date between 01/01/0001 and 12/31/9999.
     */
    String NLGM0058E = "NLGM0058E";

    /**
     * In "@", please enter the date after "@ date".
     */
    String NLGM0059E = "NLGM0059E";

    /**
     * [@] is an invalid parameter. Parameter Name: [@]
     */
    String NLGM0060E = "NLGM0060E";

    /**
     * The field of [@] is not input.
     */
    String NLGM0029E = "NLGM0029E";
    /**
     * [@] field is mandatory.
     */
    String ZZM9000E ="ZZM9000E";
}
