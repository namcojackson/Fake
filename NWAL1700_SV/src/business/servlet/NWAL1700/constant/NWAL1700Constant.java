/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1700.constant;

/**
 *<pre>
 * NWAL1700Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1700Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1700";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1700Scrn00";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit" };

    /** F3 : Apply */
    public static final String[] BTN_CMN_APL = {"btn3", "CMN_Apply", "Apply" };

    /** F4 : Apply */
    public static final String[] BTN_CMN_APR = {"btn4", "CMN_Approve", "Approve" };

    /** F5 : Reject */
    public static final String[] BTN_CMN_RJT = {"btn5", "CMN_Reject", "Reject" };

    /** F6 : Download */
    public static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /** F7 : Delete */
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };


    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** No more lines can be added. */
    public static final String NWAM8441E = "NWAM8441E";

    /** At least one line has to be selected for deletion. */
    public static final String NWAM8442E = "NWAM8442E";

    /** You don’t have permission to delete existing line categories. */
    public static final String NWAM8443E = "NWAM8443E";

    /** End Date must be greater than Start Date */
    public static final String NWAM8445E = "NWAM8445E";

    /**
     * Node Profile @ is required when the node is enabled for the
     * order category/reason.
     */
    public static final String NWAM8446E = "NWAM8446E";

    /**
     * Only one primary category can be specified for each transaction
     * direction.
     */
    public static final String NWAM8447E = "NWAM8447E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Mode Create */
    public static final String MODE_CREATE = "0";

    /** Mode Edit */
    public static final String MODE_EDIT = "1";

    /** Function Read */
    public static final String FUNCTION_READ = "NWAL1700T010";

    /** Function INSERT */
    public static final String FUNCTION_INSERT = "NWAL1700T020";

    /** Function FULL */
    public static final String FUNCTION_FULL = "NWAL1700T030";

    /** BTN Add Line */
    public static final String BTN_ADD_LINE = "Add_Line";

    /** BTN Remove Line */
    public static final String BTN_REMOVE_LINE = "Remove_Line";

    /** BTN OpenWin Lov */
    public static final String BTN_OPENWIN_LOV = "OpenWin_Lov";

    /** BTN Search AJE */
    public static final String BTN_SEARCH_AJE = "Search_AJE";

    /** OpenWin_AJE */
    public static final String BTN_OPENWIN_AJE = "OpenWin_AJE";

    /** BTN Search ARTran */
    public static final String BTN_SEARCH_ARTRAN  = "Search_ARTran";

    /** BTN Search BillToAccount */
    public static final String BTN_SEARCH_BILLTOACCOUNT = "Search_BillToAccount";

    /** BTN Search BillToLocation */
    public static final String BTN_SEARCH_BILLTO_LOCATION = "Search_BillToLocation";

    /** BTN Search PriceList */
    public static final String BTN_SEARCH_PRICELIST = "Search_PriceList";

    /** BTN Service PriceList */
    public static final String BTN_SEARCH_SERVICE_PRICE_LIST = "Search_ServicePriceList";

    /** BTN Serach Carrier */
    public static final String BTN_SEARCH_CARRIER = "Search_Carrier";

    /** Out bound */
    public static final String OUT_BOUND = "Outbound";

    /** In bound */
    public static final String IN_BOUND = "Inbound";

    /** DB Flag Insert */
    public static final String DB_FLAG_INSERT = "I";

    /** chkBox */
    public static final String CHK_A = "xxChkBox_A";

}
