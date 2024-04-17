/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1530.constant;
/**
 *<pre>
 * Business ID : NPAL1530 Min-Max Planning Report Run Screen
 * Function Name : Constant (SV)
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/11/2016   CITS            Keiichi Masaki  Create          N/A
 * 01/18/2017   CITS            T.Kikuhara      Update          QC#16256
 *</pre>
 */
public class NPAL1530Constant {
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

    /**
     * Business button SetWarehouseName
     */
    public static final String BTN_SET_WAREHOUSENAME = "SetWarehouseName";

    /**
     * Business button SetSubWarehouseName
     */
    public static final String BTN_SET_SUB_WAREHOUSENAME = "SetSubWarehouseName";

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1530";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1530Scrn00";

    /**
     * Function Edit
     */
    public static final String FUNC_EDIT = "NPAL1530T020";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    /**
     * Planning Level Code : PLAN NAME
     */
    public static final String PLAN_NAME = "01";

    /**
     * Planning Level Code : SUB WAREHOUSE
     */
    public static final String SUB_WAREHOUSE = "02";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : MRP_PLN_NM
     */
     public static final String DISPLAY_NM_MRP_PLN_NM = "Plan Name";

    /**
     * Display Name : RTL_WH_CD
     */
    public static final String DISPLAY_NM_RTL_WH_CD = "Warehouse Code";

    /**
     * Display Name : RTL_SWH_CD
     */
    public static final String DISPLAY_NM_RTL_SWH_CD = "Sub Warehouse Code";

    /**
     * Display Name : DMND_CTOFF_DT
     */
    public static final String DISPLAY_DMND_CTOFF_DT = "Demand Cut Off Date";

    /**
     * Display Name : DMND_CTOFF_DAYS_AOT
     */
    public static final String DISPLAY_DMND_CTOFF_DAYS_AOT = "Demand Cut Off Date OFFSET (Days)";

    /**
     * Display Name : SPLY_CTOFF_DT
     */
    public static final String DISPLAY_SPLY_CTOFF_DT = "Supply Cut Off Date";

    /**
     * Display Name : SPLY_CTOFF_DAYS_AOT
     */
    public static final String DISPLAY_SPLY_CTOFF_DAYS_AOT = "Supply Cut Off Date OFFSET (Days)";

    // =================================================
    // Message Code
    // =================================================
    /**
     * ZZZM9007E:The field of [@] is not input.
     */
    public static final String ZZZM9007E = "ZZZM9007E";

    /**
     * ZZM9000E:[@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * NFCM0006E:Invalid value!  Please enter a number equal to or greater than 0.
     */
    public static final String NFCM0006E = "NFCM0006E";

    /**
     * NPAM0079E:Please enter today's or later date.
     */
    public static final String NPAM0079E = "NPAM0079E";

    /**
     * NPAM1494E A value of @ must not be less than '0' .
     */
    public static final String NPAM1494E = "NPAM1494E";

}
