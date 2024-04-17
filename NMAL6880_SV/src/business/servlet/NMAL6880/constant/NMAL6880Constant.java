/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6880.constant;

/**
 * <pre>
 * Business ID : NMAL6880 TPC09 WH Mapping Maintenance
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/09/2016   CITS            K.Ogino         Create          N/A
 * 07/12/2017   CITS            T.Kikuhara      Update          QC#19862
 *</pre>
 */
public class NMAL6880Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NMAL6880";

    /**
     * Reference function code
     */
    public static final String FUNCTION_UPDATE = "NMAL6880T020";

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
    public static final String SCRN_ID = "NMAL6880Scrn00";

    // =================================================
    // Business Button Name
    // =================================================
    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "" };

    /**
     * Common button 2
     */
    public static final String[] BTN_CMN_BTN_2 = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "Add_NewLine", "" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_BTN_9 = {"btn9", "CMN_Reset", "" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_BTN_10 = {"btn10", "CMN_Return", "Return" };

    // =================================================
    // Message Code
    // =================================================
    /**
     * NMAM0835E:Please select at least 1 checkbox.
     */
    public static final String NMAM0835E = "NMAM0835E";

    /**
     * NMAM8105E:The target data for the update does not exist.
     */
    public static final String NMAM8105E = "NMAM8105E";

    /**
     * NMAM0836E:[@] field is mandatory.
     */
    public static final String NMAM0836E = "NMAM0836E";

    // =================================================
    // setNameForMessage
    // ================================================
    /**
     * CSA_WH
     */
    public static final String CSA_WH = "CSA Warehouse";

    /**
     * CSA_WH_NM
     */
    public static final String CSA_WH_NM = "CSA Warehouse Name";

    /**
     * ROSS_LOC
     */
    public static final String ROSS_LOC = "ROSS Location";

    /**
     * SUB_WH
     */
    public static final String SUB_WH = "Sub Warehouse";

    /**
     * EFF_FROM
     */
    public static final String EFF_FROM = "Start Date";

    /**
     * EFF_THRU
     */
    public static final String EFF_THRU = "End Date";

    // =================================================
    // Other
    // =================================================

    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /**
     * Header Area WH Popup Button Event
     */
    public static final String OPEN_WIN_FROM_WH_H = "OpenWinFromWarehouseH";

    /**
     * Detail Area WH Popup Button Event
     */
    public static final String OPEN_WIN_FROM_WH_D = "OpenWinFromWarehouseD";

    /**
     * Detail Area SWH Popup Button Event
     */
    public static final String OPEN_WIN_FROM_SUB_WH_D = "OpenWinFromSubWarehouseD";

    /**
     * XX_CHK_BOX_A1
     */
    public static final String XX_CHK_BOX_A1 = "xxChkBox_A1";

    /**
     * GMD (CUSA WH)
     */
    public static final String GMD = "GMD";

    /**
     * INDEX 18
     */
    public static final int IDX_18 = 18;

    /**
     * INDEX_OUT_RANGE
     */
    public static final String INDEX_OUT_RANGE = "-1";

}
