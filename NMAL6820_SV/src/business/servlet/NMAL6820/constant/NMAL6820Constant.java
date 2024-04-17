/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6820.constant;

/**
 * <pre>
 * Business ID : NMAL6820 Warehouse Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC# 2380
 * 02/11/2016   CSAI            D.Fukaya        Update          QC# 1598
 * 02/18/2016   CSAI            D.Fukaya        Update          QC# 3436
 * 02/22/2016   CSAI            D.Fukaya        Update          QC# 2369
 * 10/19/2016   Fujitsu         C.Yokoi         Update          QC# 4096
 * 10/25/2016   CITS            Y.IWASAKI       Update          QC#15120/2371
 * 08/17/2017   CITS            S.Endo          Update          Sol#013(QC#18717)
 * 04/09/2019   Fujitsu         T.Ogura         Update          QC#28667
 * 09/17/2020   CITS            J.Evangelista   Update          QC#57659
 *</pre>
 */
public class NMAL6820Constant {
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
    public static final String BTN_SEARCH = "OnClick_Search";

    /**
     * Business button Manager Name
     */
    public static final String BTN_MGRNM = "OnClick_MgrNm";

    /**
     * Business button Manager Name
     */
    public static final String BTN_ALTOWNRNM = "OnClick_AltOwnrNm";

    /**
     * Business button Branch Name
     */
    public static final String BTN_BRNM = "OnClick_Branch";

    /**
     * Business button Supplier Name
     */
    public static final String BTN_SUPPLIER = "Get_SupplierName";             // 2019/04/09 T.Ogura [QC#28667,ADD]

    /**
     * Business button Supplier Site Name
     */
    public static final String BTN_SUPPLIER_SITE = "Get_SupplierSiteName";    // 2019/04/09 T.Ogura [QC#28667,ADD]

    /**
     * Business button Set Ship To
     */
    public static final String BTN_SET_SHIP_TO = "OnClick_SetShipTo";

    /**
     * Business button Clear Ship To
     */
    public static final String BTN_CLEAR_SHIP_TO = "OnClick_ClearShipTo";

    /**
     * Business button Get Ship To
     */
    public static final String BTN_GET_SHIP_TO = "GetAddressShipTo";          // 2019/04/09 T.Ogura [QC#28667,ADD]

    /**
     * Business button Set Return To
     */
    public static final String BTN_SET_RETURN_TO = "OnClick_SetReturnTo";

    /**
     * Business button Clear Return To
     */
    public static final String BTN_CLEAR_RETURN_TO = "OnClick_ClearReturnTo";

    /**
     * Business button Get Return To
     */
    public static final String BTN_GET_RETURN_TO = "GetAddressReturnTo";      // 2019/04/09 T.Ogura [QC#28667,ADD]

    /**
     * Business button SOURCE_LOC
     */
    public static final String BTN_SOURCE_LOC = "OpenWin_SourceLocation_SW";

    /**
     * Business button RETURN_TO_LOC
     */
    public static final String BTN_RETURN_TO_LOC = "OpenWin_ReturnToLocation_SW";

    /**
     * Under tab [Address]
     */
    public static final String TAB_ADDR = "Address";

    /**
     * Under tab [Sourcing]
     */
    public static final String TAB_SRC = "Sourcing";

    /**
     * Under tab [SWH]
     */
    public static final String TAB_SWH = "SWH";

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NMAL6820";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NMAL6820Scrn00";

    /** Function Inquiry */
    public static final String FUNC_INQUIRY = "NMAL6820T010";

    /** Function Edit */
    public static final String FUNC_EDIT = "NMAL6820T020";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    /** Mode : Create */
    public static final String MODE_CREATE = "1";

    /** Mode : Update */
    public static final String MODE_UPDATE = "2";

    /** Maximum length for WH/SWH code */
    public static final int MAX_LENGTH_WH_SWH_CD = 20;

    // =================================================
    // Event Name
    // =================================================
    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /**
     * Event name : OpenWin_Wh.
     */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_WH = "NMAL6820Scrn00_OpenWin_Wh";

    /**
     * Event name : OpenWin_Mgr.
     */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_MGR = "NMAL6820Scrn00_OpenWin_Mgr";

    /**
     * Event name : OpenWin_AltOwnr.
     */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_ALT_OWNR = "NMAL6820Scrn00_OpenWin_AltOwnr";

    /**
     * Event name : OpenWin_Crr.
     */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_CRR = "NMAL6820Scrn00_OpenWin_Crr";

    /**
     * Event name : OpenWin_ShipToState.
     */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_SHIP_TO_STATE = "NMAL6820Scrn00_OpenWin_ShipToState";

    /**
     * Event name : OpenWin_ReturnToState.
     */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_STATE = "NMAL6820Scrn00_OpenWin_ReturnToState";

    /**
     * Event name : OpenWin_Branch.
     */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_BRANCH = "NMAL6820Scrn00_OpenWin_Branch";

    /**
     * Event name : OpenWin Source Location.
     */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_SOURCE_LOCATION = "NMAL6820Scrn00_OpenWin_SourceLocation";

    /**
     * Event name : OpenWin Emergency Location.
     */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_EMERGENCY_LOCATION = "NMAL6820Scrn00_OpenWin_EmergencyLocation";

    /**
     * Event name : OpenWin ReturnTo Location.
     */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_LOCATION = "NMAL6820Scrn00_OpenWin_ReturnToLocation";

    /**
     * Event name : OpenWin Source Location(SW).
     */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_SOURCE_LOCATION_SW = "NMAL6820Scrn00_OpenWin_SourceLocation_SW";

    /**
     * Event name : OpenWin Return To Location(SW).
     */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_LOCATION_SW = "NMAL6820Scrn00_OpenWin_ReturnToLocation_SW";

    // /**
    // * Event name : OpenWin Source Supplier.
    // */
    // public static final String
    // EVENT_NM_NMAL6820SCRN00_OPENWIN_SOURCESUPPLIER =
    // "NMAL6820Scrn00_OpenWin_SourceSupplier";
    //
    // /**
    // * Event name : OpenWin Emergency Supplier.
    // */
    // public static final String
    // EVENT_NM_NMAL6820SCRN00_OPENWIN_EMERGENCYSUPPLIER =
    // "NMAL6820Scrn00_OpenWin_EmergencySupplier";
    //
    // /**
    // * Event name : OpenWin ReturnTo Supplier.
    // */
    // public static final String
    // EVENT_NM_NMAL6820SCRN00_OPENWIN_RETURNTOSUPPLIER =
    // "NMAL6820Scrn00_OpenWin_ReturnToSupplier";
    //
    // /**
    // * Event name : OpenWin Source Supplier(SW).
    // */
    // public static final String
    // EVENT_NM_NMAL6820SCRN00_OPENWIN_SOURCESUPPLIER_SW =
    // "NMAL6820Scrn00_OpenWin_SourceSupplier_SW";
    //
    // /**
    // * Event name : OpenWin ReturnTo Supplier(SW).
    // */
    // public static final String
    // EVENT_NM_NMAL6820SCRN00_OPENWIN_RETURNTOSUPPLIER_SW =
    // "NMAL6820Scrn00_OpenWin_ReturnToSupplier_SW";

    /**
     * Event name : NMAL6820Scrn00_OpenWin_ShipToCode.
     */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_SHIP_TO_CODE = "NMAL6820Scrn00_OpenWin_ShipToCode";

    /**
     * Event name : NMAL6820Scrn00_OpenWin_ReturnToCode.
     */
    public static final String EVENT_NM_AL6820SCRN00_OPEN_WIN_RTRN_TO_CODE = "NMAL6820Scrn00_OpenWin_ReturnToCode";

    // =================================================
    // Display Name on Message
    // =================================================
    /** Warehouse Code */
    public static final String RTL_WH_CD = "Warehouse Code";

    /** Warehouse Category Code */
    public static final String RTL_WH_CATG_CD = "Warehouse Category Code";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_WH = "RTL_WH";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_WH = "RTL_WH_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_WH = "RTL_WH_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_WH = "RTL_WH_CD";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_WH = "Warehouse Search Popup";

    /** Header Code of Label Name for NMAL6050 : Warehouse Code */
    public static final String HDR_CD_LB_NM_FOR_WH = "Warehouse Code";

    /** Header Name of Label Name for NMAL6050 : Warehouse Name */
    public static final String HDR_NM_LB_NM_FOR_WH = "Warehouse Name";

    /** Detail Code of Label Name for NMAL6050 : Warehouse Code */
    public static final String DTL_CD_LB_NM_FOR_WH = "Warehouse Code";

    /** Detail Name of Label Name for NMAL6050 : Warehouse Name */
    public static final String DTL_NM_LB_NM_FOR_WH = "Warehouse Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_TECH = "TECH_MSTR";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_TECH = "TECH_TOC_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_TECH = "TECH_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_TECH = "TECH_TOC_CD";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_TECH = "Technician Search Popup";

    /** Header Code of Label Name for NMAL6050 : Technician Code */
    public static final String HDR_CD_LB_NM_FOR_TECH = "Technician Code";

    /** Header Name of Label Name for NMAL6050 : Technician Name */
    public static final String HDR_NM_LB_NM_FOR_TECH = "Technician Name";

    /** Detail Code of Label Name for NMAL6050 : Technician Code */
    public static final String DTL_CD_LB_NM_FOR_TECH = "Technician Code";

    /** Detail Name of Label Name for NMAL6050 : Technician Name */
    public static final String DTL_NM_LB_NM_FOR_TECH = "Technician Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_ALT_OWNER = "S21_PSN_V";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_ALT_OWNER = "PSN_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_ALT_OWNER = "FULL_PSN_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_ALT_OWNER = "PSN_CD";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_ALT_OWNER = "Alternate Owner Search Popup";

    /** Header Code of Label Name for NMAL6050 : Alternate Owner Code */
    public static final String HDR_CD_LB_NM_FOR_ALT_OWNER = "Alternate Owner Code";

    /** Header Name of Label Name for NMAL6050 : Alternate Owner Name */
    public static final String HDR_NM_LB_NM_FOR_ALT_OWNER = "Alternate Owner Name";

    /** Detail Code of Label Name for NMAL6050 : Alternate Owner Code */
    public static final String DTL_CD_LB_NM_FOR_ALT_OWNER = "Alternate Owner Code";

    /** Detail Name of Label Name for NMAL6050 : Alternate Owner Name */
    public static final String DTL_NM_LB_NM_FOR_ALT_OWNER = "Alternate Owner Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_BR = "COA_BR";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_BR = "COA_BR_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_BR = "COA_BR_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_BR = "COA_BR_SORT_NUM";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_BR = "Branch Search Popup";

    /** Header Code of Label Name for NMAL6050 : Branch Code */
    public static final String HDR_CD_LB_NM_FOR_BR = "Branch Code";

    /** Header Name of Label Name for NMAL6050 : Branch Name */
    public static final String HDR_NM_LB_NM_FOR_BR = "Branch Name";

    /** Detail Code of Label Name for NMAL6050 : Branch Code */
    public static final String DTL_CD_LB_NM_FOR_BR = "Branch Code";

    /** Detail Name of Label Name for NMAL6050 : Branch Name */
    public static final String DTL_NM_LB_NM_FOR_BR = "Branch Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_OWNER = "S21_PSN_V";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_OWNER = "PSN_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_OWNER = "FULL_PSN_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_OWNER = "PSN_CD";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_OWNER = "Owner Search Popup";

    /** Header Code of Label Name for NMAL6050 : Manager Code */
    public static final String HDR_CD_LB_NM_FOR_OWNER = "Owner Code";

    /** Header Name of Label Name for NMAL6050 : Manager Name */
    public static final String HDR_NM_LB_NM_FOR_OWNER = "Owner Name";

    /** Detail Code of Label Name for NMAL6050 : Manager Code */
    public static final String DTL_CD_LB_NM_FOR_OWNER = "Owner Code";

    /** Detail Name of Label Name for NMAL6050 : Manager Name */
    public static final String DTL_NM_LB_NM_FOR_OWNER = "Owner Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_CRR = "OTBD_CARR_V";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_CRR = "CARR_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_CRR = "CARR_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_CRR = "CARR_SORT_NUM";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_CRR = "Carrier Search Popup";

    /** Header Code of Label Name for NMAL6050 : Carrier Code */
    public static final String HDR_CD_LB_NM_FOR_CRR = "Carrier Code";

    /** Header Name of Label Name for NMAL6050 : Carrier Name */
    public static final String HDR_NM_LB_NM_FOR_CRR = "Carrier Name";

    /** Detail Code of Label Name for NMAL6050 : Carrier Code */
    public static final String DTL_CD_LB_NM_FOR_CRR = "Carrier Code";

    /** Detail Name of Label Name for NMAL6050 : Carrier Name */
    public static final String DTL_NM_LB_NM_FOR_CRR = "Carrier Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_SHIP_TO_ST = "ST";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_SHIP_TO_ST = "ST_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_SHIP_TO_ST = "ST_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_SHIP_TO_ST = "ST_SORT_NUM";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_SHIP_TO_ST = "Ship to State Pop Up";

    /** Header Code of Label Name for NMAL6050 : State Code */
    public static final String HDR_CD_LB_NM_FOR_SHIP_TO_ST = "ST Code";

    /** Header Name of Label Name for NMAL6050 : State Name */
    public static final String HDR_NM_LB_NM_FOR_SHIP_TO_ST = "ST Name";

    /** Detail Code of Label Name for NMAL6050 : State Code */
    public static final String DTL_CD_LB_NM_FOR_SHIP_TO_ST = "ST Code";

    /** Detail Name of Label Name for NMAL6050 : State Name */
    public static final String DTL_NM_LB_NM_FOR_SHIP_TO_ST = "ST Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_RETURN_TO_ST = "ST";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_RETURN_TO_ST = "ST_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_RETURN_TO_ST = "ST_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_RETURN_TO_ST = "ST_SORT_NUM";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_RETURN_TO_ST = "Return to State Pop Up";

    /** Header Code of Label Name for NMAL6050 : State Code */
    public static final String HDR_CD_LB_NM_FOR_RETURN_TO_ST = "ST Code";

    /** Header Name of Label Name for NMAL6050 : State Name */
    public static final String HDR_NM_LB_NM_FOR_RETURN_TO_ST = "ST Name";

    /** Detail Code of Label Name for NMAL6050 : State Code */
    public static final String DTL_CD_LB_NM_FOR_RETURN_TO_ST = "ST Code";

    /** Detail Name of Label Name for NMAL6050 : State Name */
    public static final String DTL_NM_LB_NM_FOR_RETURN_TO_ST = "ST Name";

    /** EZ Column Name for RTL_WH_CD */
    public static final String EZ_FIELD_NAME_FOR_RTL_WH_CD_SD = "rtlWhCd_SD";

    /** EZ Column Name for RTL_WH_CD */
    public static final String EZ_FIELD_NAME_FOR_RTL_SWH_CD_SD = "rtlSwhCd_SD";

    // =================================================
    // Message ID
    // =================================================
    /** The effective date of the entered data is incorrect. */
    public static final String NMAM0803E = "NMAM0803E";

    /** The specified format is incorrect. It must be @. */
    public static final String NMAM8075E = "NMAM8075E";

    /** [@] is not selected. */
    public static final String NMAM0014E = "NMAM0014E";

    /** Multiple items are selected for [@]. */
    public static final String NMAM0051E = "NMAM0051E";

    /** @ cannot be selected, because @. */
    public static final String NMAM0248E = "NMAM0248E";

    /** Please enter both '@' and '@'. */
    public static final String NMAM8289E = "NMAM8289E";

    /** The format of [@] is incorrect. */
    public static final String NMAM0052E = "NMAM0052E";

    /** [@] field is mandatory. */
    public static final String NMAM0836E = "NMAM0836E";

    /** In case where [@] is [@], [@] is an invalid entry. */
    public static final String NMAM8148W = "NMAM8148W";

    /** Please input [@] with [@] characters or less. */
    public static final String NMAM8350E = "NMAM8350E";

    /** [@] field is mandatory.. */
    public static final String ZZM9000E = "ZZM9000E";

    // 2016/10/14 CSA-QC#4096 Add Start
    /** @ is required when @ are selected. */
    public static final String NMAM8385E = "NMAM8385E";

    // 2016/10/14 CSA-QC#4096 Add End

    // =================================================
    // Message Parameter
    // =================================================
    /** The message parameter : not 'Warehouse' */
    public static final String MESSAGE_PARAM_NOT_WH = "not 'Warehouse'";

    /** The message parameter : Source WH/SWH */
    public static final String MESSAGE_PARAM_SRC_WH_SWH = "Source WH/SWH";

    /** The message parameter : Supplier Comment */
    public static final String MESSAGE_PARAM_SUPPLIER_COMMENT = "Supplier Comment";

    // 2016/10/12 CSA-QC#4096 Add Start
    /** The message parameter : Ship To Address */
    public static final String MESSAGE_PARAM_SHIP_TO_LOC = "Ship To Address";

    /** The message parameter : GeoCode search */
    public static final String MESSAGE_PARAM_GEOCODE_SRCH = "GeoCode search";

    // 2016/10/12 CSA-QC#4096 Add End

    // =================================================
    // Display character string for message
    // =================================================
    /** WH Code */
    public static final String NAME_FOR_MESSAGE_RTL_WH_CD = "WH Code";

    /** WH Name */
    public static final String NAME_FOR_MESSAGE_RTL_WH_NM = "WH Name";

    /** WH Category */
    public static final String NAME_FOR_MESSAGE_RTL_WH_CATG = "WH Category";

    /** Tech Toc */
    public static final String NAME_FOR_MESSAGE_TECH_TOC = "Tech Toc";

    /** Inventory Ownership */
    public static final String NAME_FOR_MESSAGE_INVTY_OWNR = "Inventory Ownership";

    /** Effective Thru Date */
    public static final String NAME_FOR_MESSAGE_EFF_THRU = "Effective Thru Date";

    /** Time Zone */
    public static final String NAME_FOR_MESSAGE_TM_ZONE = "Time Zone";

    /** WH System Type */
    public static final String NAME_FOR_MESSAGE_WH_SYS_TP = "WH System Type";

    /** WMS WH Code */
    public static final String NAME_FOR_MESSAGE_WMS_WH_CD = "WMS WH Code";

    /** WH Group Name */
    public static final String NAME_FOR_MESSAGE_WH_GROUP_NAME = "WH Group Name";

    /** PO Receipt Routin */
    public static final String NAME_FOR_MESSAGE_PO_RCPT_RTE_TP = "PO Receipt Routing";

    /** RMA Receipt Routing */
    public static final String NAME_FOR_MESSAGE_RMA_RCPT_RTE_TP = "RMA Receipt Routing";

    /** Address Line1 */
    public static final String NAME_FOR_MESSAGE_FIRST_LINE_ADDR = "Address Line1";

    /** City */
    public static final String NAME_FOR_MESSAGE_CTY_ADDR = "City";

    /** State */
    public static final String NAME_FOR_MESSAGE_ST = "State";

    /** Postal Code */
    public static final String NAME_FOR_MESSAGE_POST = "Postal Code";

    /** Country */
    public static final String NAME_FOR_MESSAGE_CTRY = "Country";

    /** Telephone */
    public static final String NAME_FOR_MESSAGE_TEL = "Telephone";

    /** Fax */
    public static final String NAME_FOR_MESSAGE_FAX = "Fax";

    /** Priority */
    public static final String NAME_FOR_MESSAGE_PRTY_LOC_FLG = "Priority";

    /** Disable */
    public static final String NAME_FOR_MESSAGE_DSBL_LOC_FLG = "Disable";

    /** Priority is selected */
    public static final String NAME_FOR_MESSAGE_PRTY_SELECTED = "Priority is selected";

    /** Inventory Account */
    public static final String NAME_FOR_MESSAGE_INVTY_ACCT = "Inventory Account";

    /** WH ownership */
    public static final String NAME_FOR_MESSAGE_WH_OWNR = "WH ownership";

    /** WH Start Date */
    public static final String NAME_FOR_MESSAGE_EFF_FROM = "WH Start Date";

    /** Branch */
    public static final String NAME_FOR_MESSAGE_BR = "Branch";

    /** Ship To Code */
    public static final String NAME_FOR_MESSAGE_SHIP_TO_CD = "Ship To Code";

    /** Ship To Name */
    public static final String NAME_FOR_MESSAGE_SHIP_TO_LOC_NM = "Ship To Name";

    /** PO receipt routing */
    public static final String NAME_FOR_MESSAGE_PO_RCPT_RTE_TP_CD = "PO receipt routing";

    /** RMA receipt routing */
    public static final String NAME_FOR_MESSAGE_RMA_RCPT_RTE_TP_CD = "RMA receipt routing";

    /** Return To Code */
    public static final String NAME_FOR_MESSAGE_RTRN_TO_CD = "Return To Code";

    /** Return To Name */
    public static final String NAME_FOR_MESSAGE_RTRN_TO_LOC_NM = "Return To Name";

    /** Branch */
    public static final String NAME_FOR_MESSAGE_BRANCH_IN_ACCT = "Branch";

    /** Account */
    public static final String NAME_FOR_MESSAGE_ACCOUNT_IN_ACCT = "Account";

    /** SWH Name */
    public static final String NAME_FOR_MESSAGE_SWH_NM = "SWH Name";

    /** SWH Description */
    public static final String NAME_FOR_MESSAGE_SWH_DESCRIPTION = "SWH Description";

    /** Default Source */
    public static final String NAME_FOR_MESSAGE_PROCR_TP_CD_OF_SRC = "Default Source To";

    /** Source (WH) */
    public static final String NAME_FOR_MESSAGE_RTL_WH_NM_OF_SRC = "Source";

    /** Sub Source (SWH) */
    public static final String NAME_FOR_MESSAGE_RTL_SWH_NM_OF_SRC = "Sub Source";

    /** Default Return To */
    public static final String NAME_FOR_MESSAGE_PROCR_TP_CD_OF_RTRN = "Default Return To";

    /** Return To (WH) */
    public static final String NAME_FOR_MESSAGE_RTL_WH_NM_OF_RTRN = "Return To";

    /** Return Sub Source (SWH) */
    public static final String NAME_FOR_MESSAGE_RTL_SWH_NM_OF_RTRN = "Return Sub Source";

    /** Return Sub Source (SWH) */
    public static final String NAME_FOR_MESSAGE_TECH_MBL_MSG_ADDR = "Tech Mobile/Messaging";

    //08/17/2017 CITS S.Endo Mod Sol#013(QC#18717) START
//    /** Emergency Source (WH) */
//    public static final String NAME_FOR_MESSAGE_RTL_WH_NM_OF_EMERGENCY = "Emergency Source";
//
//    /** Emergency Sub Source (SWH) */
//    public static final String NAME_FOR_MESSAGE_RTL_SWH_NM_OF_EMERGENCY = "Emergency Sub Source";
    /** Satellite Warehouse (WH) */
    public static final String NAME_FOR_MESSAGE_RTL_WH_NM_OF_EMERGENCY = "Satellite Warehouse";

    /** Satellite Sub Warehouse (SWH) */
    public static final String NAME_FOR_MESSAGE_RTL_SWH_NM_OF_EMERGENCY = "Satellite Sub Warehouse";
    //08/17/2017 CITS S.Endo Mod Sol#013(QC#18717) END

    /** Supplier */
    public static final String NAME_FOR_MESSAGE_SUPPLIER = "Supplier";              // 2019/04/09 T.Ogura [QC#28667,ADD]

    /** Supplier Site */
    public static final String NAME_FOR_MESSAGE_SUPPLIER_SITE = "Supplier Site";    // 2019/04/09 T.Ogura [QC#28667,ADD]

    // START 2020/09/17 J.Evangelista [QC#57659,ADD]
    /** WH Start Time */
    public static final String NAME_FOR_MESSAGE_WH_START_TM = "WH Start Time";

    /** WH End Time */
    public static final String NAME_FOR_MESSAGE_WH_END_TM = "WH End Time";

    /** WH Cut Off Time */
    public static final String NAME_FOR_MESSAGE_WH_CUT_OFF_TM = "WH Cut Off Time";

    /** FedEx Cut Off Time */
    public static final String NAME_FOR_MESSAGE_FEDEX_CUT_OFF_TM = "FedEx Cut Off Time";

    /** UPS Cut Off Time */
    public static final String NAME_FOR_MESSAGE_UPS_CUT_OFF_TM = "UPS Cut Off Time";
    // END   2020/09/17 J.Evangelista [QC#57659,ADD]

    // =================================================
    // html id value
    // =================================================
    /** Return to Location Check Box */
    public static final String RTRN_TO_LOC_CHK_BOX_NAME_VALUE = "xxChkBox_R1";

    /** Priority Check Box */
    public static final String SWH_PRTY_CHK_BOX_NAME_VALUE = "xxChkBox_P1";

    /** Disabled Check Box */
    public static final String SWH_DSBL_CHK_BOX_NAME_VALUE = "xxChkBox_D1";

    /** SWH Name */
    public static final String SWH_RTL_SWH_NM_VALUE = "rtlSwhNm_A1";

    /** SWH Description */
    public static final String RTL_SWH_DESC_TXT_VALUE = "rtlSwhDescTxt_A1";

    /** SWH Table Name */
    public static final String SWH_LEFT_TABLE_NAME_VALUE = "A_Left";

    /** SWH Table Name */
    public static final String SWH_RIGHT_TABLE_NAME_VALUE = "A_Right";

    // =================================================
    // format value
    // =================================================
    /** Zip */
    public static final String ZIP_CODE_FORMAT = "'99999' or '99999-9999'";

    /** Tel/Fax */
    public static final String TEL_FAX_NUM_FORMAT = "'9999999999' or '999-999-9999'";

    /** Default Effective Thru Date */
    public static final String DEF_EFF_DATE = "99991231";

    /** HYPHEN */
    public static final String HYPHEN = "-";

    // =================================================
    // value for message kind error
    // =================================================
    /** error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** warning */
    public static final String MESSAGE_KIND_WARNING = "W";

    // =================================================
    // code value
    // =================================================
    /** WH System Type : WMS */
    public static final String WH_SYS_TP_WMS = "WMS";

    /** Account Popup Parameter : 08 : Display Related Accounts */
    public static final String ACCT_POPUP_PARAM_08_SHIP_TO_ONLY = "04";

    /** Account Popup Parameter : 11 : Status */
    public static final String ACCT_POPUP_PARAM_11_ACTIVE_ONLY = "01";

    /** Link OpenWin_ShipTo_ST Link Event Name */
    public static final String LINK_OPENWIN_SHIPTO_ST_EVENT_NM = "OpenWin_ShipTo_ST";

    /** Link OpenWin_ShipTo_PostCd Link Event Name */
    public static final String LINK_OPENWIN_SHIPTO_POSTCD_EVENT_NM = "OpenWin_ShipTo_PostCd";

    /** Link OpenWin_ShipTo_City Link Event Name */
    public static final String LINK_OPENWIN_SHIPTO_CTY_EVENT_NM = "OpenWin_ShipTo_City";

    /** Link OpenWin_ShipTo_Cnty Link Event Name */
    public static final String LINK_OPENWIN_SHIPTO_CNTY_EVENT_NM = "OpenWin_ShipTo_Cnty";

    /** Link OpenWin_ReturnTo_ST Link Event Name */
    public static final String LINK_OPENWIN_RETURNTO_ST_EVENT_NM = "OpenWin_ReturnTo_ST";

    /** Link OpenWin_ReturnTo_PostCd Link Event Name */
    public static final String LINK_OPENWIN_RETURNTO_POSTCD_EVENT_NM = "OpenWin_ReturnTo_PostCd";

    /** Link OpenWin_ReturnTo_City Link Event Name */
    public static final String LINK_OPENWIN_RETURNTO_CTY_EVENT_NM = "OpenWin_ReturnTo_City";

    /** Link OpenWin_ReturnTo_Cnty Link Event Name */
    public static final String LINK_OPENWIN_RETURNTO_CNTY_EVENT_NM = "OpenWin_ReturnTo_Cnty";

    /** Link OpenWin_Supplier Link Event Name */
    public static final String LINK_OPENWIN_SUPPLIER_EVENT_NM = "OpenWin_Supplier";

    /** Index Number 0 */
    public static final int IDX_0 = 0;

    /** Index Number 1 */
    public static final int IDX_1 = 1;

    /** Index Number 2 */
    public static final int IDX_2 = 2;

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    /** Index Number 4 */
    public static final int IDX_4 = 4;

    /** Index Number 5 */
    public static final int IDX_5 = 5;

    /** Index Number 6 */
    public static final int IDX_6 = 6;

    /** Index Number 7 */
    public static final int IDX_7 = 7;
    
    /**
     * IDX_8
     */
    public static final int IDX_8 = 8;

    /**
     * IDX_9
     */
    public static final int IDX_9 = 9;

    /**
     * IDX_10
     */
    public static final int IDX_10 = 10;

    /**
     * IDX_11
     */
    public static final int IDX_11 = 11;

    /**
     * IDX_12
     */
    public static final int IDX_12 = 12;

    /**
     * IDX_13
     */
    public static final int IDX_13 = 13;

    /**
     * IDX_14
     */
    public static final int IDX_14 = 14;

    /**
     * IDX_15
     */
    public static final int IDX_15 = 15;

    /**
     * IDX_16
     */
    public static final int IDX_16 = 16;

    /**
     * IDX_17
     */
    public static final int IDX_17 = 17;

    /**
     * IDX_18
     */
    public static final int IDX_18 = 18;

    /**
     * IDX_19
     */
    public static final int IDX_19 = 19;

    /**
     * IDX_20
     */
    public static final int IDX_20 = 20;

    /**
     * IDX_21
     */
    public static final int IDX_21 = 21;

    /**
     * IDX_22
     */
    public static final int IDX_22 = 22;

    /**
     * IDX_23
     */
    public static final int IDX_23 = 23;

    /**
     * IDX_24
     */
    public static final int IDX_24 = 24;

    /**
     * IDX_25
     */
    public static final int IDX_25 = 25;

    /**
     * IDX_26
     */
    public static final int IDX_26 = 26;

    /**
     * IDX_27
     */
    public static final int IDX_27 = 17;

    /**
     * IDX_28
     */
    public static final int IDX_28 = 28;

    /**
     * IDX_29
     */
    public static final int IDX_29 = 29;

    /**
     * IDX_30
     */
    public static final int IDX_30 = 30;

    /**
     * IDX_31
     */
    public static final int IDX_31 = 31;

    /**
     * IDX_32
     */
    public static final int IDX_32 = 32;

    /**
     * IDX_60
     */
    public static final int IDX_60 = 60;

    /**
     * IDX_200
     */
    public static final int IDX_200 = 200;
}
