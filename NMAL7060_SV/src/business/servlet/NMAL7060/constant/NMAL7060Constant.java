/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7060.constant;

/**
 *<pre>
 * NMAL7060Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         W.Honda         Create          N/A
 * 2017/02/17   Fujitsu         R.Nakamura      Update          QC#17529
 *</pre>
 */
public class NMAL7060Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7060";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7060Scrn00";

    /** Read User*/
    public static final String FUNC_ID_READ = BIZ_ID + "T010";

    /** Update User */
    public static final String FUNC_ID_UPDATE = BIZ_ID + "T020";

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

    /** Button : Insert Row */
    public static final String[] BTN_INSERT_ROW = {"InsertRow", "InsertRow", "Insert Row" };

    /** Button : Delete Row */
    public static final String[] BTN_DELETE_ROW = {"DeleteRow", "DeleteRow", "Delete Row" };

    /** Button : Insert Row Billing */
    public static final String[] BTN_INSERT_ROW_BLLG = {"InsertRow_Bllg", "InsertRow_Bllg", "Insert Row" };

    /** Button : Delete Row Billing */
    public static final String[] BTN_DELETE_ROW_BLLG = {"DeleteRow_Bllg", "DeleteRow_Bllg", "Delete Row" };

    /** Button : OpenWin_PrcMtrPkgBllgMtrNm */
    public static final String[] BTN_OPENWIN_BLLGNM = {"OpenWin_PrcMtrPkgBllgMtrNm", "OpenWin_PrcMtrPkgBllgMtrNm", "B" };

    /** Button : OpenWin_Model */
    public static final String[] BTN_OPENWIN_MDL = {"OpenWin_Model", "OpenWin_Model", "Model" };

    // Add Start 2017/02/17 QC#17529
    /** Button : Insert Row */
    public static final String[] BTN_FILTER_SEARCH = {"FilterSearch", "FilterSearch", "Filter" };
    // Add End 2017/02/17 QC#17529

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /**  Please set at least one search criteria */
    public static final String NMAM0192E = "NMAM0192E";

    /**  A past date cannot be entered into the "Date Effective From */
    public static final String NMAM0185E = "NMAM0185E";

    /**  A past date cannot be entered into the "Date Effective From */
    public static final String NMAM0050E = "NMAM0050E";

    /**  @ cannot be added because it is exceeding the maximum number of row [@]  */
    public static final String NMAM8187E = "NMAM8187E";

    /**  A past date cannot be entered into the "Date Effective From */
    public static final String NMAM8200E = "NMAM8200E";

    /**  @ should be @. */
    public static final String NMAM8211E = "NMAM8211E";

    /**  [@] field is mandatory. */
    public static final String NMAM0836E = "NMAM0836E";

    /**  The value for [@] must be smaller than [@].  */
    public static final String NMAM0043E = "NMAM0043E";

    /**  The value for [@] must be over [@].  */
    public static final String NMAM0042E = "NMAM0042E";

    /**  Have not been selected.  Please select.  */
    public static final String NLAM0023E = "NLAM0023E";

    /**  @ must have at least one row.  Please enter data.  */
    public static final String NMAM8214E = "NMAM8214E";

    /**  Remove the selected records. is it OK?  */
    public static final String NMAM8234I = "NMAM8234I";

    /**  Common Message */
    public static final String ZZM9037E = "ZZM9037E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Event Name: OpenWin_ModelName */
    public static final String EVENT_NM_OPENWIN_MODEL = "OpenWin_Model";

    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /** Popup Name: Service Model Search */
    public static final String POPUP_NM_SERVICE_MODEL_SEARCH = "Service Model Search";

    /** Popup Display Column Name: Model Name */
    public static final String POPUP_NM_MODEL_NAME_DISPLAY = "Model Name";

    /** Popup Display Column Name: Model ID */
    public static final String POPUP_NM_MODEL_ID_DISPLAY = "Model ID";

    /** Popup Column Name: Model Name */
    public static final String POPUP_NM_MODEL_NAME = "T_MDL_NM";

    /** Popup Column Name: Model Name */
    public static final String POPUP_NM_MODEL_ID = "T_MDL_ID";

    /** Popup Name: Service Model Search */
    public static final String POPUP_NM_SERVICE_METER_PACK_SEARCH = "Service Meter Package Search";

    /** Popup Select Table Name: Price Meter Package */
    public static final String POPUP_TABLE_NM_PRC_MTR_PKG = "PRC_MTR_PKG";

    /** Popup Display Column Name: Model Name */
    public static final String POPUP_NM_SVC_MTR_PKG_NM_DISPLAY = "Service Mtr Pkg Name";

    /** Popup Column Name: Model Name */
    public static final String POPUP_NM_SVC_MTR_PKG_NM = "PRC_MTR_PKG_NM";

    /** Popup Sort Name: ASC */
    public static final String POPUP_SORT_KEY_ASC = "ASC";

    /** Event Name: OpenWin_ModelName */
    public static final String EVENT_NM_OPENWIN_PRCMTRPKGBLLGMTR = "OpenWin_PrcMtrPkgBllgMtr";

    /** Popup Name: Service Model Search */
    public static final String POPUP_NM_BILLING_METER_SEARCH = "Billing Meter Search";

    /** Popup Display Column Name: Level */
    public static final String POPUP_NM_LEVEL_DISPLAY = "Level";

    /** Popup Display Column Name: Billing Meter Name */
    public static final String POPUP_NM_BILLING_METER_NAME_DISPLAY = "Billing Meter Name";

    /** Popup Display Column Name: Meter Display */
    public static final String POPUP_NM_METER_DISPLAY = "Meter Display";

    /** Popup Display Column Name: Usage Item Code */
    public static final String POPUP_NM_ITEM_CODE_DISPLAY = "Usage Item Code";

    /** Popup Display Column Name: Billing Meter Name */
    public static final String POPUP_NM_BILLING_METER_CODE_DISPLAY = "Billing Meter Code";

    /** Popup Column Name: Level */
    public static final String POPUP_NM_LEVEL = "BLLG_MTR_MAP_LVL_NUM";

    /** Popup Column Name: Billing Meter Name */
    public static final String POPUP_NM_BILLING_METER_NAME = "MTR_LB_DESC_TXT";

    /** Popup Column Name: Meter Display */
    public static final String POPUP_NM_METER = "MTR_LB_NM";

    /** Popup Column Name: Usage Item Code */
    public static final String POPUP_NM_ITEM_CODE = "INTG_MDSE_CD";

    /** Popup Column Name: Billing Meter Code */
    public static final String POPUP_NM_BILLING_METER_CODE = "BLLG_MTR_LB_CD";
}
