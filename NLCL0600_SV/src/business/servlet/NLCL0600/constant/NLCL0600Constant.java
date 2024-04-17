/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0600.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Business ID :  NLCL0600 NLCL0600 PI Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/24/2016   CITS         Makoto Okigami     Create          N/A
 * 10/05/2016   CITS         Y.Fujii            Update          QC#14417
 *</pre>
 */
public class NLCL0600Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NLCL0600";

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
    public static final String SCRN_ID = "NLCL0600Scrn00";

    /** Function Inquiry */
    public static final String FUNC_INQUIRY = "NLCL0600T010";

    /** Function Edit */
    public static final String FUNC_EDIT = "NLCL0600T020";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : RTL_WH_CD
     */
    public static final String DISPLAY_NM_RTL_WH_CD = "Warehouse Code";

    /**
     * Display Name : RTL_WH_NM
     */
    public static final String DISPLAY_NM_RTL_WH_NM = "Warehouse Name";

    /**
     * Display Name : PHYS_INVTY_DT
     */
    public static final String DISPLAY_NM_PHYS_INVTY_DT = "Schedule Date";

    /**
     * Display Name : PHYS_INVTY＿CTRL_NM
     */
    public static final String DISPLAY_NM_PHYS_INVTY_CTRL_NM = "Physical Inventory";

    /**
     * Display Name : PHYS_INVTY_CTRL_DESC_NM
     */
    public static final String DISPLAY_NM_PHYS_INVTY_CTRL_DESC_NM = "Description";

    /**
     * Display Name : RTL_SWH_CD
     */
    public static final String DISPLAY_NM_RTL_SWH_CD = "SWH Code";

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

    /**
     * Business button SearchWHInfo
     */
    public static final String BTN_SEARCH_WH_INFO = "SearchWHInfo";

    /**
     * Business button SearchRetailSubWHInfo
     */
    public static final String BTN_SEARCH_RETAIL_SUB_WH_INFO = "SearchRetailSubWHInfo";

    /**
     * Business button Add_SpecificSubWH
     */
    public static final String BTN_ADD_SPECIFIC_SUB_WH = "Add_SpecificSubWH";

    /**
     * Business button Add_AllSubWH
     */
    public static final String BTN_ADD_ALL_SUB_WH = "Add_AllSubWH";

    /**
     * Business button Delete_SubWHs
     */
    public static final String BTN_DELETE_SUB_WH = "Delete_SubWHs";

    // =================================================
    // Popup Param
    // =================================================
    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String WINDOW_TITLE_WH_SEARCH = "Warehouse Search";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String WHERE_DISP_NM_FOR_WH_CODE = "Warehouse Code";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String WHERE_SQL_NM_FOR_WH_CODE = "RTL_WH_CD";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String WHERE_DISP_NM_FOR_WH_NAME = "Warehouse Name";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String WHERE_SQL_NM_FOR_WH_NAME = "RTL_WH_NM";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String COLUMN_DISP_NM_FOR_WH_CODE = "Warehosue Code";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String COLUMN_SQL_NM_FOR_WH_CODE = "RTL_WH_CD";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_WH_CODE = new BigDecimal(20);

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String COLUMN_DISP_NM_FOR_WH_NAME = "Warehouse Name";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String COLUMN_SQL_NM_FOR_WH_NAME = "RTL_WH_NM";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_WH_NAME = new BigDecimal(30);

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String SORT_COLUMN_NAME_FOR_WH_CODE = "RTL_WH_CD";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String SORT_CONDITION_FOR_WH_CODE = "ASC";

    /**
     * Param Value for NWAL1130(Sub WH Search)
     */
    public static final String WINDOW_TITLE_SUB_WH_SEARCH = "Sub WH Search";

    /**
     * Param Value for NWAL1130(Sub WH Search)
     */
    public static final String WHERE_DISP_NM_FOR_SUB_WH_CODE = "Sub WH Code";

    /**
     * Param Value for NWAL1130(Sub WH Search)
     */
    public static final String WHERE_SQL_NM_FOR_SUB_WH_CODE = "RTL_SWH_CD";

    /**
     * Param Value for NWAL1130(Sub WH Search)
     */
    public static final String WHERE_DISP_NM_FOR_SUB_WH_NAME = "Sub WH Name";

    /**
     * Param Value for NWAL1130(Sub WH Search)
     */
    public static final String WHERE_SQL_NM_FOR_SUB_WH_NAME = "RTL_SWH_NM";

    /**
     * Param Value for NWAL1130(Sub WH Search)
     */
   public static final String WHERE_DISP_NM_FOR_INVTY_LOC_CODE = "Location Code";

   /**
    * Param Value for NWAL1130(Sub WH Search)
    */
   public static final String WHERE_SQL_NM_FOR_INVTY_LOC_CODE = "INVTY_LOC_CD";

    /**
     * Param Value for NWAL1130(Sub WH Search)
     */
    public static final String COLUMN_DISP_NM_FOR_SUB_WH_CODE = "Sub WH Code";

    /**
     * Param Value for NWAL1130(Sub WH Search)
     */
    public static final String COLUMN_SQL_NM_FOR_SUB_WH_CODE = "RTL_SWH_CD";

    /**
     * Param Value for NWAL1130(Sub WH Search)
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_SUB_WH_CODE = new BigDecimal(20);

    /**
     * Param Value for NWAL1130(Sub WH Search)
     */
    public static final String COLUMN_DISP_NM_FOR_SUB_WH_NAME = "Sub WH Name";

    /**
     * Param Value for NWAL1130(Sub WH Search)
     */
    public static final String COLUMN_SQL_NM_FOR_SUB_WH_NAME = "RTL_SWH_NM";

    /**
     * Param Value for NWAL1130(Sub WH Search)
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_SUB_WH_NAME = new BigDecimal(30);

    /**
     * Param Value for NWAL1130(Sub WH Search)
     */
    public static final String COLUMN_DISP_NM_FOR_INVTY_LOC_CODE = "Location Code";

    /**
     * Param Value for NWAL1130(Sub WH Search)
     */
    public static final String COLUMN_SQL_NM_FOR_INVTY_LOC_CODE = "INVTY_LOC_CD";

    /**
     * Param Value for NWAL1130(Sub WH Search)
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_INVTY_LOC_CODE = new BigDecimal(20);

    /**
     * Param Value for NWAL1130(Sub WH Search)
     */
    public static final String SORT_COLUMN_NAME_FOR_SUB_WH_CODE = "RTL_SWH_CD";

    /**
     * Param Value for NWAL1130(Sub WH Search)
     */
    public static final String SORT_CONDITION_FOR_SUB_WH_CODE = "ASC";

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
     * NLZM2273E: You can not [@] this record Because of [@] already exists.
     */
    public static final String NLZM2273E = "NLZM2273E";

    /**
     * NLCM0025E: The number of Detail rows reached to the maximum.  No more Details can be registered.
     */
    public static final String NLCM0025E = "NLCM0025E";

    /**
     * ZZM9000E: 
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * NLCM0064E: Please enter today's date or a later date in @.
     */
    public static final String NLCM0064E = "NLCM0064E";

    /**
     * NLCM0175E: Please add Sub Warehouse.
     */
    public static final String NLCM0175E = "NLCM0175E";

    /**
     * NLCM0178E: Please choose a SWH.
     */
    public static final String NLCM0178E = "NLCM0178E";

    // =================================================
    // Message Param
    // =================================================
    /**
     * NLZM2273E Param 1
     */
    public static final String NLZM2273E_P01 = "Add";

    /**
     * NPAM1230E: Enter a future date in [@].
     */
    public static final String NPAM1230E = "NPAM1230E";
}
