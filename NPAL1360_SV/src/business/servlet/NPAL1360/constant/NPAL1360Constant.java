/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1360.constant;

/**
 *<pre>
 * Business ID : NPAL1360 Kitting Work Order Entry
 * Function Name : Constant Value Definition
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2016   CITS            Keiichi Masaki  Create          N/A
 * 03/07/2017   CITS            Y.IWASAKI       Update          QC#17363
 * 11/07/2017   CITS            S.Katsuma       Update          SOL#014(QC#18401)
 *</pre>
 */

public class NPAL1360Constant {

    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_01 = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    public static final String[] BTN_CMN_BTN_02 = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_BTN_03 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_BTN_04 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_BTN_05 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_BTN_06 = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_BTN_07 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_08 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_BTN_09 = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_BTN_10 = {"btn10", "CMN_Return", "Return" };

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1360";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1360Scrn00";

    /** 
     * Function Inquiry
     */
    public static final String FUNC_INQUIRY = "NPAL1360T010";

    /**
     * Function Edit
     */
    public static final String FUNC_EDIT = "NPAL1360T020";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_PRINT = "70";

    /**
     * Display Name : Work Order#
     */
    public static final String DISPLAY_WRK_ORD_NUM = "Work Order#";

    /**
     * Display Name : Work Order Type
     */
    public static final String DISPLAY_WRK_ORD_TP = "Work Order Type";

    /**
     * Display Name : Warehouse 
     */
    public static final String DISPLAY_WH = "Warehouse";

    /**
     * Display Name : Completion SWH
     */
    public static final String DISPLAY_COMP_SWH = "Completion Sub Warehouse";

    /**
     * Display Name : Request Completion Date
     */
    public static final String DISPLAY_REQ_COMP_DT = "Request Completion Date";

    /**
     * Display Name : Kit Item
     */
    public static final String DISPLAY_KIT_ITEM = "Kit Item";

    /**
     * Display Name : Order Quantity
     */
    public static final String DISPLAY_ORD_QTY = "Order Qty";

    /**
     * Display Name : Supply SWH
     */
    public static final String DISPLAY_SPPLY_SWH = "Supply SWH";

    /**
     * Display Name : Serial#
     */
    public static final String DISPLAY_SERIAL_NUM = "Serial#";

    /**
     * Display Name : Massage
     */
    public static final String DISPLAY_MESSAGE = "Massage";

    /**
     * Business button Search
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * Business button Set WH name
     */
    public static final String BTN_SET_WHNAME = "SetWhName";

    /**
     * Business button Set SWH name
     */
    public static final String BTN_SET_SWHNAME = "SetSwhName";

    /**
     * Business button Set Kit Item name
     */
    public static final String BTN_SET_KITITEMNAME = "SetKitItemName";

    /**
     * Business button Component
     */
    public static final String BTN_COMPONENT = "Component";

    /**
     * Business button Print
     */
    public static final String BTN_SERIAL = "OpenWin_KitSerial";

    /**
     * Business button Print
     */
    public static final String BTN_LINE_SERIAL = "OpenWin_SupplySerial";

    /**
     * Business button Print
     */
    public static final String BTN_LINE_SWH = "OpenWin_SupplySwh";

    /**
     * Business button Cancel
     */
    public static final String BTN_CANCEL = "Cancel";

    /**
     * Business button Print
     */
    public static final String BTN_PRINT = "Print";

    /**
     * Business button Print
     */
    public static final String BTN_ATTACHMENTS = "Attachments";

    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /**
     * OpenWin_Wh
     */
    public static final String EVENT_OPEN_WIN_WH = "OpenWin_Wh";

    /**
     * OpenWin_CompletionSwh
     */
    public static final String EVENT_OPEN_WIN_COMPLETION_SWH = "OpenWin_CompletionSwh";

    /**
     * OpenWin_SupplySwh
     */
    public static final String EVENT_OPEN_WIN_SUPPLY_SWH = "OpenWin_SupplySwh";

    /**
     * OpenWin_SupplySwh
     */
    public static final String EVENT_OPEN_WIN_KIT_SERIAL = "OpenWin_KitSerial";

    /**
     * OpenWin_SupplySwh
     */
    public static final String EVENT_OPEN_WIN_SUPPLY_SERIAL = "OpenWin_SupplySerial";

    /**
     * Show Dialog
     */
    public static final String SHOW_DIALOG = "ShowDialog";

    /**
     * Not Show Dialog
     */
    public static final String NOT_SHOW_DIALOG = "NotShowDialog";

    /**
     * Kit Item Line# 000
     */
    public static final String KIT_ITEM_LINE_NUM = "000";

    /**
     * Serial# PopUp Mode Edit : 1
     */
    public static final String SERIAL_EDIT = "1";

    /**
     * Serial# PopUp Mode Read : 2
     */
    public static final String SERIAL_READ = "2";

    /**
     * Poup Parameter Index 0
     */
    public static final int INDEX_0 = 0;

    /**
     * Poup Parameter Index 1
     */
    public static final int INDEX_1 = 1;

    /**
     * Poup Parameter Index 2
     */
    public static final int INDEX_2 = 2;

    /**
     * Poup Parameter Index 3
     */
    public static final int INDEX_3 = 3;

    /**
     * Poup Parameter Index 4
     */
    public static final int INDEX_4 = 4;

    /**
     * Poup Parameter Index 5
     */
    public static final int INDEX_5 = 5;

    /**
     * Poup Parameter Index 6
     */
    public static final int INDEX_6 = 6;

    /**
     * Poup Parameter Index 7
     */
    public static final int INDEX_7 = 7;

    /**
     * Poup Parameter Index 8
     */
    public static final int INDEX_8 = 8;

    /**
     * Poup Parameter Index 9
     */
    public static final int INDEX_9 = 9;

    /**
     * Poup Parameter Index 10
     */
    public static final int INDEX_10 = 10;

    /**
     * Poup Parameter Index 11
     */
    public static final int INDEX_11 = 11;

    /**
     * Poup Parameter Index 12
     */
    public static final int INDEX_12 = 12;

    /**
     * Poup Parameter Index 13
     */
    public static final int INDEX_13 = 13;

    /**
     * Poup Parameter Index 14
     */
    public static final int INDEX_14 = 14;

    /**
     * Poup Parameter Index 15
     */
    public static final int INDEX_15 = 15;

    /**
     * Max rows
     */
    public static final int MAX_ROWS = 200;

    /**
     * Suffix
     */
    public static final String SUFFIX = "L1";

    // =================================================
    // Message Code
    // =================================================
    /**
     * Message ZZZM9007E : The field of [@] is not input.
     */
    public static final String ZZZM9007E = "ZZZM9007E";

    /**
     * Message NPZM0002E : Work Order Number is required.
     */
    public static final String NPZM0002E = "NPZM0002E";

    /**
     * Message NPAM1185E : The value for [@] must be bigger than [@].
     */
    public static final String NPAM1185E = "NPAM1185E";

    /**
     * Message NPAM0046E : For [@], '0' or less than '0' cannot be specified.
     */
    public static final String NPAM0046E = "NPAM0046E";

    /**
     * Message NPAM0079E : For [@], '0' or less than '0' cannot be specified.
     */
    public static final String NPAM0079E = "NPAM0079E";

    /**
     * Message NPAM1521E : The number of serialized items have exceeded 200 in the work order.
     */
    public static final String NPAM1521E = "NPAM1521E";

    // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
    /**
     * The details of the process target have not been selected.
     */
    public static final String NPAM0049E = "NPAM0049E";

    /**
     * Multiple details to process are selected.
     */
    public static final String NPAM1215E = "NPAM1215E";

    /**
     * [@] is mandatory value.@
     */
    public static final String NPAM1329E = "NPAM1329E";

    /**
     * Business button Print
     */
    public static final String BTN_SUPPLYDEMAND = "OpenWin_SupplyDemand";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
    // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]

    /**
     * Message Parameter : Minimum Quantity 1
     */
    public static final String MIN_QTY = "1";


}
