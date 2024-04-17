/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0630.constant;

import java.math.BigDecimal;

/** 
 * <pre>
 * Business ID : NLCL0630 Tech PI Inquiry
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/07/2018   CITS            Y.Iwasaki       Update          QC#27010
 * 12/11/2018   Fujitsu         T.Ogura         Update          QC#28755
 *</pre>
 */
public class NLCL0630Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NLCL0630";

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
    public static final String SCRN_ID = "NLCL0630Scrn00";

    /** Function Inquiry */
    public static final String FUNC_ID_USER_MANAGER = "NLCL0630T010";

    /** Function Edit */
    public static final String FUNC_ID_ALL_USERS = "NLCL0630T020";

    /**
     * USD Decimal Point (e.g. 50.25)
     */
    public static final int DECIMAL_POINT_USD = 2;

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : TECH_TOC_CD
     */
    public static final String DISPLAY_NM_TECH_TOC_CD = "Technician";

    /**
     * Display Name : TECH_NM
     */
    public static final String DISPLAY_NM_TECH_NM = "Technician";

    /**
     * Display Name : COA_BR_CD
     */
    public static final String DISPLAY_NM_COA_BR_CD = "Branch";

    /**
     * Display Name : COA_BR_NM
     */
    public static final String DISPLAY_NM_COA_BR_NM = "Branch";

    /**
     * Display Name : WH_OWNR_CD
     */
    public static final String DISPLAY_NM_WH_OWNR_CD = "Owner";

    /**
     * Display Name : WH_OWNR_NM
     */
    public static final String DISPLAY_NM_WH_OWNR_NM = "Owner";

    /**
     * Display Name : PHYS_INVTY_DT(From)
     */
    public static final String DISPLAY_NM_PHYS_INVTY_DT_DF = "Scheduled Date From";

    /**
     * Display Name : PHYS_INVTY_DT(To)
     */
    public static final String DISPLAY_NM_PHYS_INVTY_DT_DT = "Scheduled Date To";

    /**
     * Display Name : PHYS_INVTY_CTRL_NM
     */
    public static final String DISPLAY_NM_PHYS_INVTY_CTRL_NM = "Tech Physical";

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * Display Name : RTL_WH_CD
     */
    public static final String DISPLAY_NM_RTL_WH_CD = "Location";

    /**
     * Display Name : RTL_WH_NM
     */
    public static final String DISPLAY_NM_RTL_WH_NM = "Location";
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

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
     * Business button Search
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * Business button Cancel
     */
    public static final String BTN_CANCEL = "Cancel";

    /**
     * Business button Begin
     */
    public static final String BTN_BEGIN = "Begin";

    // =================================================
    // HTML TAG ID
    // =================================================
    /**
     * HTML TAG ID Radio Button
     */
    public static final String HTML_ID_RADIO_BUTTON = "radio_button";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    // =================================================
    // Popup Param
    // =================================================
    /**
     * Param Value for NWAL1130(Technician Search)
     */
    public static final String WINDOW_TITLE_TECHNICIAN_SEARCH = "Technician Search";

    /**
     * Param Value for NWAL1130(Technician Search)
     */
    public static final String WHERE_DISP_NM_FOR_TECHNICIAN_CODE = "Technician Code";

    /**
     * Param Value for NWAL1130(Technician Search)
     */
    public static final String WHERE_SQL_NM_FOR_TECHNICIAN_CODE = "TECH_TOC_CD";

    /**
     * Param Value for NWAL1130(Technician Search)
     */
    public static final String WHERE_DISP_NM_FOR_TECHNICIAN_NAME = "Technician Name";

    /**
     * Param Value for NWAL1130(Technician Search)
     */
    public static final String WHERE_SQL_NM_FOR_TECHNICIAN_NAME = "TECH_NM";

    /**
     * Param Value for NWAL1130(Technician Search)
     */
    public static final String COLUMN_DISP_NM_FOR_TECHNICIAN_CODE = "Technician Code";

    /**
     * Param Value for NWAL1130(Technician Search)
     */
    public static final String COLUMN_SQL_NM_FOR_TECHNICIAN_CODE = "TECH_TOC_CD";

    /**
     * Param Value for NWAL1130(Technician Search)
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_TECHNICIAN_CODE = new BigDecimal(20);

    /**
     * Param Value for NWAL1130(Technician Search)
     */
    public static final String COLUMN_DISP_NM_FOR_TECHNICIAN_NAME = "Technician Name";

    /**
     * Param Value for NWAL1130(Technician Search)
     */
    public static final String COLUMN_SQL_NM_FOR_TECHNICIAN_NAME = "TECH_NM";

    /**
     * Param Value for NWAL1130(Technician Search)
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_TECHNICIAN_NAME = new BigDecimal(60);

    /**
     * Param Value for NWAL1130(Technician Search)
     */
    public static final String SORT_COLUMN_NAME_FOR_TECHNICIAN_CODE = "TECH_TOC_CD";

    /**
     * Param Value for NWAL1130(Technician Search)
     */
    public static final String SORT_CONDITION_FOR_TECHNICIAN_CODE = "ASC";

    /**
     * Param Value for NWAL1130(Tech Physical Search)
     */
    public static final String WINDOW_TITLE_TECH_PHYSICAL_SEARCH = "Tech Physical Search";

    /**
     * Param Value for NWAL1130(Tech Physical Search)
     */
    public static final String WHERE_DISP_NM_FOR_TECH_PHYSICAL_NAME = "Tech Physical";

    /**
     * Param Value for NWAL1130(Tech Physical Search)
     */
    public static final String WHERE_SQL_NM_FOR_TECH_PHYSICAL_NAME = "PHYS_INVTY_CTRL_NM";

    /**
     * Param Value for NWAL1130(Tech Physical Search)
     */
    public static final String COLUMN_DISP_NM_FOR_TECH_PHYSICAL_NAME = "Tech Physical";

    /**
     * Param Value for NWAL1130(Tech Physical Search)
     */
    public static final String COLUMN_SQL_NM_FOR_TECH_PHYSICAL_NAME = "PHYS_INVTY_CTRL_NM";

    /**
     * Param Value for NWAL1130(Tech Physical Search)
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_TECH_PHYSICAL_NAME = new BigDecimal(100);

    /**
     * Param Value for NWAL1130(Tech Physical Search)
     */
    public static final String SORT_COLUMN_NAME_FOR_TECH_PHYSICAL_NAME = "PHYS_INVTY_CTRL_NM";

    /**
     * Param Value for NWAL1130(Tech Physical Search)
     */
    public static final String SORT_CONDITION_FOR_TECH_PHYSICAL_NAME = "ASC";

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * Param Value for NWAL1130(Location Search)
     */
    public static final String WINDOW_TITLE_LOCATION_SEARCH = "Location Search";

    /**
     * Param Value for NWAL1130(Location Search)
     */
    public static final String WHERE_DISP_NM_FOR_LOCATION_CODE = "Location Code";

    /**
     * Param Value for NWAL1130(Location Search)
     */
    public static final String WHERE_SQL_NM_FOR_LOCATION_CODE = "RTL_WH_CD";

    /**
     * Param Value for NWAL1130(Location Search)
     */
    public static final String WHERE_DISP_NM_FOR_LOCATION_NAME = "Location Name";

    /**
     * Param Value for NWAL1130(Location Search)
     */
    public static final String WHERE_SQL_NM_FOR_LOCATION_NAME = "RTL_WH_NM";

    /**
     * Param Value for NWAL1130(Location Search)
     */
    public static final String COLUMN_DISP_NM_FOR_LOCATION_CODE = "Location Code";

    /**
     * Param Value for NWAL1130(Location Search)
     */
    public static final String COLUMN_SQL_NM_FOR_LOCATION_CODE = "RTL_WH_CD";

    /**
     * Param Value for NWAL1130(Location Search)
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_LOCATION_CODE = new BigDecimal(20);

    /**
     * Param Value for NWAL1130(Location Search)
     */
    public static final String COLUMN_DISP_NM_FOR_LOCATION_NAME = "Location Name";

    /**
     * Param Value for NWAL1130(Location Search)
     */
    public static final String COLUMN_SQL_NM_FOR_LOCATION_NAME = "RTL_WH_NM";

    /**
     * Param Value for NWAL1130(Location Search)
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_LOCATION_NAME = new BigDecimal(60);

    /**
     * Param Value for NWAL1130(Location Search)
     */
    public static final String SORT_COLUMN_NAME_FOR_LOCATION_CODE = "RTL_WH_CD";

    /**
     * Param Value for NWAL1130(Location Search)
     */
    public static final String SORT_CONDITION_FOR_LOCATION_CODE = "ASC";
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

    // =================================================
    // Message Code
    // =================================================
    /**
     * NLCM0142E: Please choose a Physical Inventory.
     */
    public static final String NLCM0142E = "NLCM0142E";

    /**
     * ZZM9000E: [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * ZZM8100I: Process ended normally.
     */
    public static final String ZZM8100I = "ZZM8100I";
}
