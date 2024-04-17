/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/13/2009   Fujitsu         I.Kondo         Create          N/A
 * 2011/10/27   CSAI            N.Sasaki        Update          362045
 *</pre>
 */
package business.servlet.NPAL0100.constant;

public interface NPAL0100Constant {

    /** BUSINESS_ID */
    String BUSINESS_ID = "NPAL0100";

    String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /** FUNCTION_CODE */
    String FUNCTION_CODE = "20";

    /** ID of table A */
    String TABLE_ID_A = "A";

    // Message Code
    /**
     * message code.
     */
    enum MESSAGE_CD {
        /** NPAM0014E */
        NPAM0014E
        /** NPAM0038W */
        , NPAM0038I
        /** NZZM0004W */
        , NZZM0004W
        /** ZZM9000E */
        , ZZM9000E
    }

    /** MAX NUM of INV_QTY */
    int MAX_NUM_INV_QTY = 200;

    // Request Parameter
    /** VND_CD */
    String REQUEST_PARAM_VND_CD = "VND_CD";

    /** VND_NM */
    String REQUEST_PARAM_VND_NM = "VND_NM";

    /** CUST_ISS_PO_NUM */
    String REQUEST_PARAM_CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** CUST_ISS_PO_DT */
    String REQUEST_PARAM_CUST_ISS_PO_DT = "CUST_ISS_PO_DT";

    /** CPO_ORD_NUM */
    String REQUEST_PARAM_CPO_ORD_NUM = "CPO_ORD_NUM";

    /** BILL_TO_CUST_CD */
    String REQUEST_PARAM_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** MDSE_CD */
    String REQUEST_PARAM_MDSE_CD = "MDSE_CD";

    /** MDSE_DESC_SHORT_TXT */
    String REQUEST_PARAM_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /** PO_QTY */
    String REQUEST_PARAM_PO_QTY = "PO_QTY";

    /** INV_QTY */
    String REQUEST_PARAM_INV_QTY = "INV_QTY";

    /** CPO_DTL_LINE_NUM */
    String REQUEST_PARAM_CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** CPO_DTL_LINE_SUB_NUM */
    String REQUEST_PARAM_CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";

    /** CPO_DTL_LINE_SUB_NUM */
    String REQUEST_PARAM_SHPG_PLN_NUM = "SHPG_PLN_NUM";

    /** PARAM_PO_ORD_NUM */
    String REQUEST_PARAM_PO_ORD_NUM = "PO_ORD_NUM";

    /** PO_ORD_DTL_LINE_NUM */
    String REQUEST_PARAM_PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** PO_RCV_NUM */
    String REQUEST_PARAM_PO_RCV_NUM = "PO_RCV_NUM";

    /** PO_RCV_LINE_NUM */
    String REQUEST_PARAM_PO_RCV_LINE_NUM = "PO_RCV_LINE_NUM";

    /** QTY_NUM */
    String REQUEST_PARAM_QTY_NUM = "Qty Num";

    /** SERIAL_NUM_ARRAY */
    String REQUEST_PARAM_SERIAL_NUM_ARRAY = "SERIAL_NUM_ARRAY";

    /** PROC_FLG */
    String REQUEST_PARAM_PROC_FLG_ARRAY = "PROC_FLG_ARRAY";

    /** MESSAGE_ID */
    String REQUEST_PARAM_MESSAGE_ID = "MESSAGE_ID";

    // SCRN_ITEM_NAME
    /** VND_CD */
    String SCRN_ITEM_NM_VND_CD = "Vendor Code";

    /** VND_NM */
    String SCRN_ITEM_NM_VND_NM = "Vendor Name";

    /** ISS_PO_NUM */
    String SCRN_ITEM_NM_CUST_ISS_PO_NUM = "Customer Issue PO Number";

    /** CUST_ISS_PO_DT */
    String SCRN_ITEM_NM_CUST_ISS_PO_DT = "Customer Issue PO Date";

    /** CPO_ORD_NUM */
    String SCRN_ITEM_NM_CPO_ORD_NUM = "CPO Order Number";

    /** BILL_TO_CUST_CD */
    String SCRN_ITEM_NM_BILL_TO_CUST_CD = "Bill To Customer Code";

    /** BILL_TO_CUST_NM */
    String SCRN_ITEM_NM_BILL_TO_CUST_NM = "Bill To Customer Name";

    /** MDSE_CD */
    String SCRN_ITEM_NM_MDSE_CD = "Merchandise Code";

    /** MDSE_NM */
    String SCRN_ITEM_NM_MDSE_NM = "Merchandise Name";

    /** PO_QTY */
    String SCRN_ITEM_NM_PO_QTY = "PO Quantity";

    /** MDSE_TP_CD */
    String SCRN_ITEM_NM_MDSE_TP_CD = "Merchandise Type Code";

    /** INV_QTY */
    String SCRN_ITEM_NM_INV_QTY = "Quantity Invoiced";

    /** SER_NUM */
    String SCRN_ITEM_NM_SER_NUM = "Serial Number";

    /** SCRN_ITEM_NM_SER_NUM_LIST_SIZE */
    String SCRN_ITEM_NM_SER_NUM_LIST_SIZE = "Serial Number List Size";

    /**
     * MDSE_TP_CD_TYPE
     */
    enum MDSE_TP_CD_TYPE {
        /** Yes */
        Yes,
        /** No */
        No
    }

    // Proc FLG
    /** New */
    String PROC_FLG_NEW = "1";

    /** Update */
    String PROC_FLG_UPDATE = "2";

    /** Other */
    String PROC_FLG_OTHER = "3";

    // Button Name
    /**
     * Button Name
     */
    enum BTN_NM {
        /** Edit */
        Edit,
        /** Cancel */
        Cancel
    }

    /** ERROR FLG ON */
    String ERROR_FLG_ON = "y";

    /** ERROR FLG OFF */
    String ERROR_FLG_OFF = "n";

    // ItemStatus
    /** ITEM ENABLED */
    String ITEM_STATUS_ENABLED = "";

    /** ITEM DISABLED */
    String ITEM_STATUS_DISABLED = " disabled ";

    /** BTN_PAGE_PREV */
    String[] BTN_PAGE_PREV = {"PagePrev", "PagePrev", "Prev" };

    /** BTN_PAGE_NEXT */
    String[] BTN_PAGE_NEXT = {"PageNext", "PageNext", "Next" };

    /** BTN_APPLY */
    String[] BTN_APPLY = {"Apply", "Apply", "Apply" };

    /** BTN_CMN_BTN1 */
    String[] BTN_CMN_BTN1 = {"btn1", "CMN_Save", "Save" };

    /** BTN_CMN_BTN2 */
    String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** BTN_CMN_BTN3 */
    String[] BTN_CMN_BTN3 = {"btn3", "CMN_Apply", "Apply" };

    /** BTN_CMN_BTN4 */
    String[] BTN_CMN_BTN4 = {"btn4", "CMN_Approve", "Approve" };

    /** BTN_CMN_BTN5 */
    String[] BTN_CMN_BTN5 = {"btn5", "CMN_Reject", "Reject" };

    /** BTN_CMN_BTN6 */
    String[] BTN_CMN_BTN6 = {"btn6", "CMN_Download", "Download" };

    /** BTN_CMN_BTN7 */
    String[] BTN_CMN_BTN7 = {"btn7", "CMN_Delete", "Delete" };

    /** BTN_CMN_CLEAR */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** BTN_CMN_BTN9 */
    String[] BTN_CMN_BTN9 = {"btn9", "CMN_Reset", "Reset" };

    /** BTN_CMN_CLOSE */
    String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /** BTN_CANCEL */
    String[] BTN_CANCEL = {"Cancel", null, "Cancel" };

    /** BTN_EDIT */
    String[] BTN_EDIT = {"Edit", null, "Edit" };
    
    /** SUBMIT */
    String SUBMIT = "SUBMIT";
    
    /** INIT */
    String INIT = "INIT";
    
    /** APPLY */
    String APPLY = "APPLY";
    
    /** CLEAR */
    String CLEAR = "CLEAR";
}
