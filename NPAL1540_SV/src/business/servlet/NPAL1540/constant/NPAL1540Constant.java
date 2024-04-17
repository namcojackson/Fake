/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1540.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Business ID :  NPAL1540 Manual ASN Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/19/2016   CITS         Makoto Okigami     Create          N/A
 * 03/06/2018   CITS         T.Wada             Update          QC#20445-2
 *</pre>
 */
public class NPAL1540Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1540";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1540Scrn00";

    /** Function Inquiry */
    public static final String FUNC_INQUIRY = "NPAL1540T010";

    /** Function Edit */
    public static final String FUNC_EDIT = "NPAL1540T020";

    // =================================================
    // Business Button Name
    // =================================================
    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    public static final String[] BTN_CMN_BTN_2 = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_BTN_9 = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_BTN_10 = {"btn10", "CMN_Return", "Return" };

    /** Function Button 11 */
    public static final String[] BTN_CMN_BTN_11 = {"btn11", "AddLine", "Add Line" };

    /**
     * Business button Search
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * Business button Error Correction
     */
    public static final String BTN_ERROR_CORRECTION = "MoveTo_AsnErrorCorrection";

    /**
     * Business button Apply
     */
    public static final String BTN_APPLY = "ApplyPO";

    /**
     * Business button PO Entry
     */
    public static final String BTN_PO_ENTRY = "MoveTo_PoEntry";

    /**
     * Business button Add All Line
     */
    public static final String BTN_ADD_ALL_LINE = "AddAllLine";

    /**
     * Business button Add Line
     */
    public static final String BTN_ADD_LINE = "AddLine";

    /**
     * Business button Check All
     */
    public static final String BTN_CHECK_ALL = "CheckAll";

    /**
     * Business button Un Check All
     */
    public static final String BTN_UN_CHECK_ALL = "UnCheckAll";

    /**
     * Business button Delete Row
     */
    public static final String BTN_DELETE_ROW = "DeleteRow";

    /**
     * Business button Delete Row
     */
    public static final String BTN_ATTACHMENTS = "Attachments";

    /**
     * Business button Serial Entry Pop Up
     */
    public static final String BTN_OPENWIN_SER_ENTRY = "OpenWin_SerEntry";
    
    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : ASN SO#
     */
    public static final String DISPLAY_NM_ASN_SO_NUM = "ASN SO#";

    /**
     * Display Name : PO#
     */
    public static final String DISPLAY_NM_PO_ORD_NUM = "PO#";

    /**
     * Display Name : Delivery Order#
     */
    public static final String DISPLAY_NM_SHIP_FROM_SO_NUM = "Delivery Order#";

    /**
     * Display Name : Carrier
     */
    public static final String DISPLAY_NM_CARR_CD = "Carrier";

    /**
     * Display Name : BOL#
     */
    public static final String DISPLAY_NM_ASN_BOL_NUM = "BOL#";

    /**
     * Display Name : PRO#
     */
    public static final String DISPLAY_NM_ASN_PRO_NUM = "PRO#";

    /**
     * Display Name : Ship Date
     */
    public static final String DISPLAY_NM_SHIP_DT = "Ship Date";

    /**
     * Display Name : PDD Date
     */
    public static final String DISPLAY_NM_ASN_PLN_DELY_DT = "PDD Date";

    /**
     * Display Name : Total Weight
     */
    public static final String DISPLAY_NM_ASN_TOT_SHIP_WT = "Total Weight";

    /**
     * Display Name : Supplier WH
     */
    public static final String DISPLAY_NM_VND_INVTY_LOC_CD = "Supplier WH";

    /**
     * Display Name : PO Line#
     */
    public static final String DISPLAY_NM_DISP_PO_DTL_LINE_NUM = "PO Line#";

    /**
     * Display Name : Supplier Item Code
     */
    public static final String DISPLAY_NM_ASN_MDSE_CD = "Supplier Item Code";

    /**
     * Display Name : Ship Qty
     */
    public static final String DISPLAY_NM_ASN_QTY = "Ship Qty";

    /**
     * Display Name : Weight
     */
    public static final String DISPLAY_NM_ASN_TTL_WT = "Weight";

    /**
     * Display Name : Freight Amt
     */
    public static final String DISPLAY_NM_ASN_TOT_FRT_AMT = "Freight Amt";

    // =================================================
    // Popup Param
    // =================================================
    /**
     * Poupu Param: NLBL3000
     */
    public static final String SERIAL_POPUP_PARAM_ENTRY = "1";

    /**
     * Poupu Param: NLBL3000
     */
    public static final String SERIAL_POPUP_PARAM_READ = "2";

    /**
     * Param Value for NWAL1130(Carrier Search)
     */
    public static final String WINDOW_TITLE_CARRIER_SEARCH = "Carrier Search Popup";

    /**
     * Param Value for NWAL1130(Carrier Search)
     */
    public static final String SELECT_TABLE_NAME_CARRIER_SEARCH = "OTBD_CARR_V";

    /**
     * Param Value for NWAL1130(Carrier Search)
     */
    public static final String WHERE_DISP_NM_FOR_CARRIER_CODE = "Carrier Code";

    /**
     * Param Value for NWAL1130(Carrier Search)
     */
    public static final String WHERE_SQL_NM_FOR_CARRIER_CODE = "CARR_CD";

    /**
     * Param Value for NWAL1130(Carrier Search)
     */
    public static final String WHERE_DISP_NM_FOR_CARRIER_NAME = "Carrier Name";

    /**
     * Param Value for NWAL1130(Carrier Search)
     */
    public static final String WHERE_SQL_NM_FOR_CARRIER_NAME = "CARR_NM";

    /**
     * Param Value for NWAL1130(Carrier Search)
     */
    public static final String COLUMN_DISP_NM_FOR_CARRIER_CODE = "Carrier Code";

    /**
     * Param Value for NWAL1130(Carrier Search)
     */
    public static final String COLUMN_SQL_NM_FOR_CARRIER_CODE = "CARR_CD";

    /**
     * Param Value for NWAL1130(Carrier Search)
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_CARRIER_CODE = new BigDecimal(20);

    /**
     * Param Value for NWAL1130(Carrier Search)
     */
    public static final String COLUMN_DISP_NM_FOR_CARRIER_NAME = "Carrier Name";

    /**
     * Param Value for NWAL1130(Carrier Search)
     */
    public static final String COLUMN_SQL_NM_FOR_CARRIER_NAME = "CARR_NM";

    /**
     * Param Value for NWAL1130(Carrier Search)
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_CARRIER_NAME = new BigDecimal(60);

    /**
     * Param Value for NWAL1130(Carrier Search)
     */
    public static final String SORT_COLUMN_NAME_FOR_CARRIER_CODE = "CARR_CD";

    /**
     * Param Value for NWAL1130(Carrier Search)
     */
    public static final String SORT_CONDITION_FOR_CARRIER_CODE = "ASC";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    // =================================================
    // Window Item Name
    // =================================================
    /**
     * XX_CHK_BOX_A1
     */
    public static final String XX_CHK_BOX_A1 = "xxChkBox_A1";

    // =================================================
    // After Decimal Point Digit Number
    // =================================================
    /**
     * After Decimal Point Digit Number(Weight)
     */
    public static final int AFT_DECL_PNT_DIGIT_NUM_WT = 2;

    // =================================================
    // Message Code
    // =================================================
    /**
     * ZZM9000E: [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * NPAM1566E: Required field(s). Please enter.
     */
    public static final String NPAM1566E = "NPAM1566E";

}
