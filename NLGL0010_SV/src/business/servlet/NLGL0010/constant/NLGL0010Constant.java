/**
 * <pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0010.constant;

/**
 * <pre>
 * SO Maintenance
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 09/20/2013     CSAI            Y.Miyauchi        Create            MW Replace Initial
 * 01/17/2017     CITS            T.Kikuhara        Update            QC#16256
 * 05/29/2017     CITS            S.Endo            Update            RS#3168
 *</pre>
 */
public interface NLGL0010Constant {

    // **************** Business values *****************
    /** Business ID */
    String BIZ_ID = "NLGL0010";

    /** Function Update */
    public static final String FUNCTION_UPDATE = "NLGL0010T020";

    /** Screen ID */
    String SCRN_ID = "NLGL0010Scrn00";

    // **************** Evenet Function values *****************
    /** Function Code Search */
    String FUNC_CD_SRCH = "20";

    /** Function Code Update */
    String FUNC_CD_UPD = "40";

    // **************** TAB setting values *****************
    /** Tab SO List */
    String TAB_SO_LIST = "SOList";

    /** Tab SO Status */
    String TAB_SO_STS = "SOStatus";

    /** Tab SO Download Edit */
    String TAB_SO_DNLD_EDT = "DnldEdt";

    /** Tab SO Upload Edit */
    String TAB_SO_UPLD_EDT = "UpldEdt";

    // **************** common buttonName definition *****************
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
     * Search button
     */
    String[] BTN_SEARCH = {"OnClick_Search", "Search" };

    /**
     * check all of DNLD
     */
    String[] BTN_CHECK_ALL_OF_DNLD = {"OnClick_CheckAll", "Check All" };

    /**
     * uncheck all of DNLD
     */
    String[] BTN_UNCHECK_ALL_OF_DNLD = {"OnClick_UncheckAll", "UnCheck All" };

    /**
     * delete row of DNLD
     */
    String[] BTN_DELETE_ROW_OF_DNLD = {"OnClick_DNLD_DeleteRow", "Delete Row" };

    /**
     * check all of DNLD
     */
    String[] BTN_INSERT_ROW_OF_DNLD = {"OnClick_DNLD_InsertRow", "Insert Row" };

    /**
     * copy row of UPD
     */
    String[] BTN_COPY_ROW_OF_UPD = {"OnClick_UPD_CopyRow", "Copy Row" };

    /**
     * copy row of UPD
     */
    String[] BTN_DELETE_ROW_OF_UPD = {"OnClick_UPD_DeleteRow", "Delete Row" };

    /**
     * copy row of UPD
     */
    String[] BTN_INSERT_ROW_OF_UPD = {"OnClick_UPD_InsertRow", "Insert Row" };

    /** Prev */
    String BTN_PREV = "Prev";

    /** Next */
    String BTN_NEXT = "Next";

    // **************** Message ID *****************

    /**
     * [@] field is mandatory.
     */
    String ZZM9000E = "ZZM9000E";
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
     * for has been exceeded.
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
     * deplicate
     */
    String NLGM0060E = "NLGM0060E";

    /**
     * Please add detail information.
     */
    String NLGM0071E = "NLGM0071E";

    /**
     * There is a line that not entered both of Inbound Order Number and Outbound Order Number.
     */
    String NLGM0076W = "NLGM0076W";

    /**
     * Please input MDSE that has been downloaded.
     */
    String NLGM0077E = "NLGM0077E";

    /**
     * Both Line of Inbound Order Number and Outbound Order Number is not inputting.
     * When it doesn't have problem, Please Click Submit Botton again.
     */
    String NLGM0075W = "NLGM0075W";

    /**
     * The field of [@] is not input.
     */
    String NLGM0029E = "NLGM0029E";

    /**
     * Please execute again after correcting the error field.
     */
    String ZZM9037E = "ZZM9037E";
    // **************** Display character string *****************
    /**
     * field name check box(SO List)
     */
    String FIELD_NAME_XXCHKBOX_A1 = "xxChkBox_A1";

    /**
     * field name check box(Download)
     */
    String FIELD_NAME_XXCHKBOX_K1 = "xxChkBox_K1";

    /**
     * field name check box(Upload)
     */
    String FIELD_NAME_XXCHKBOX_O1 = "xxChkBox_O1";

    /**
     * Field Value : 1
     */
    int FLD_VALUE_INT_1 = 1;

    /**
     * Field Value : 0
     */
    int FLD_VALUE_INT_ZERO = 0;

    /**
     * Upload List
     */
    String NAME_FOR_MESSAGE_LIST = "Upload List";

    // **************** Check Item *****************
    /**
     * minimum input value for date item on search condition
     */
    String MIN_DATE_FOR_SEARCH = "00010101";

    /**
     * maximum input value for date item on search condition
     */
    String MAX_DATE_FOR_SEARCH = "99991231";

    // =================================================
    // Others
    // =================================================

    /** TpCd */
    String TP_CD_COPY = "1";

    /** Type Resend */
    String TP_CD_RESEND = "2";

    // **************** Display character string ********
    /**
     * Display character string for message(SO)
     */
    String NAME_FOR_MESSAGE_SO = "SO#";

    /**
     * Display character string for message(WH Code)
     */
    String NAME_FOR_MESSAGE_WH = "WH";

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
     * Display character string for message(Submit Type)
     */
    String NAME_FOR_MESSAGE_SUBMIT_TP = "Submit Type";

    /**
     * Display character string for message(RSD)
     */
    String NAME_FOR_MESSAGE_RSD = "RSD";

    /**
     * Display character string for message(RDD)
     */
    String NAME_FOR_MESSAGE_RDD = "RDD";

    /**
     * Display character string for message(CustPO)
     */
    String NAME_FOR_MESSAGE_CUST_PO = "Cust Order#";

    /**
     * Display character string for message(Ship Via)
     */
    String NAME_FOR_MESSAGE_SHIP_VIA = "Ship Via";

    /**
     * Display character string for message(Freight-Terms)
     */
    String NAME_FOR_MESSAGE_FRT = "Freight Code";

    /**
     * Display character string for message(Item)
     */
    String NAME_FOR_MESSAGE_ITEM = "Mdse Code";

    /**
     * Display character string for message(Qty/Units)
     */
    String NAME_FOR_MESSAGE_QTY_UNITS = "Quantity/Units";

    /**
     * Display character string for message(UOM)
     */
    String NAME_FOR_MESSAGE_UOM = "UOM";

    /**
     * Display character string for message(StockStatus)
     */
    String NAME_FOR_MESSAGE_STK_STS = "Stock Status";

    /**
     * Display character string for message(StockStatusTo)
     */
    String NAME_FOR_MESSAGE_STK_STS_TO = "Stock Status To";

    /**
     * Display character string for message(RECORDID)
     */
    String NAME_FOR_MESSAGE_RECORDID = "RECORDID";

    /**
     * Display character string for message(LINE#(OTBD))
     */
    String NAME_FOR_MESSAGE_LINE_OTBD = "LINE#(OTBD)";

    /**
     * Display character string for message(LINE#(INBD))
     */
    String NAME_FOR_MESSAGE_LINE_INBD = "LINE#(INBD)";

    /**
     * Display character string for message(TASK)
     */
    String NAME_FOR_MESSAGE_TASK = "TASK";

    /**
     * Display character string for message(ORD_STS)
     */
    String NAME_FOR_MESSAGE_ORD_STS = "ORDER STATUS";

    /**
     * Display character string for message(ITEM)
     */
    String NAME_FOR_MESSAGE_ITEM_UP = "ITEM";

    /**
     * Display character string for message(HOLD CODE)
     */
    String NAME_FOR_MESSAGE_HOLD_CODE = "HOLD CODE";

    /**
     * Display character string for message(QUANTITY)
     */
    String NAME_FOR_MESSAGE_QUANTITY = "QUANTITY";

    /**
     * Display character string for message(ORDER TYPE(OTBD))
     */
    String NAME_FOR_MESSAGE_ORD_TP_OTBD = "ORDER TYPE(OTBD)";

    /**
     * Display character string for message(ORDER TYPE(INBD))
     */
    String NAME_FOR_MESSAGE_ORD_TP_INBD = "ORDER TYPE(INBD)";

    /**
     * Display character string for message(TRANSACTION DATE)
     */
    String NAME_FOR_MESSAGE_TRX_DT = "TRANSACTION DATE";

    /**
     * Display character string for message(WEIGHT)
     */
    String NAME_FOR_MESSAGE_WT = "WEIGHT";

    /**
     * Display character string for message(CARRIER)
     */
    String NAME_FOR_MESSAGE_CARR = "CARRIER";

    /**
     * Display character string for message(TRAILER_ID)
     */
    String NAME_FOR_MESSAGE_TRAILER_ID = "TRAILER_ID";

    /**
     * Display character string for message(PRO_BILL)
     */
    String NAME_FOR_MESSAGE_PRO_BILL = "PRO_BILL";

    /**
     * Display character string for message(BOL)
     */
    String NAME_FOR_MESSAGE_BOL = "BOL";

    /**
     * Display character string for message(SHIPMENT)
     */
    String NAME_FOR_MESSAGE_SHIPMENT = "SHIPMENT";

    /**
     * Display character string for message(CONTAINER)
     */
    String NAME_FOR_MESSAGE_CONTAINER = "CONTAINER";

    /**
     * Display character string for message(OUTERMOST CONTAINER)
     */
    String NAME_FOR_MESSAGE_OUTERMOST_CONTAINER = "OUTERMOST CONTAINER";

    /**
     * Display character string for message(FREIGHT CHARGE)
     */
    String NAME_FOR_MESSAGE_FREIGHT_CHARGE = "FREIGHT CHARGE";

    /**
     * Display character string for message(TMS FREIGHT CHARGE)
     */
    String NAME_FOR_MESSAGE_TMS_FREIGHT_CHARGE = "TMS FREIGHT CHARGE";

    /**
     * Display character string for message(SERIAL NUMBER)
     */
    String NAME_FOR_MESSAGE_SERIAL_NUMBER = "SERIAL NUMBER";

    /**
     * Display character string for message(ESTIMATED DOCK_DATE)
     */
    String NAME_FOR_MESSAGE_ESTIMATED_DOCK_DATE = "ESTIMATED DOCK_DATE";

    /**
     * Display character string for message(CreatedOn)
     */
    String NAME_FOR_MESSAGE_CREATED_ON = "CreatedOn";

    /**
     * VAR_CHAR_CONST Key Name: Valid copy flag 
     */
    String NLGL_VALID_COPY_FLG = "NLGL_VALID_COPY_FLG";

    /**
     *  Value of WH_CD All
     */
    String VAL_WH_CD_NM_ALL = "ALL";

    /**
     * code value of "00:All" in pulldown list of wh"
     */
    String WH_ALL_SELECTION_VALUE = "00";
}
