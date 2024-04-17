/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2030.constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;

/**
 *<pre>
 * NLAL2030Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         Y.Taoka         Create          N/A
 * 06/09/2016   CSAI            Y.Imazu         Update          QC#7977
 * 06/09/2016   CSAI            Y.Imazu         Update          QC#7981
 * 02/20/2018   CITS            T.Tokutomi      Update          QC#18461-Sol#085
 * 02/25/2019   CITS            K.Ogino         Update          QC#30456
 * 03/06/2023   Hitachi         TZ.Win          Update          QC#61160
 *</pre>
 */
public class NLAL2030Constant {

    /** Business ID */
    public static final String BIZ_ID = "NLAL2030";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NLAL2030Scrn00";

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
    // Business button
    // --------------------------------
    /** Select All */
    public static final String[] BTN_SELECT_ALL = {"Select_All", "Select_All", "Select All" };

    /** UnSelect All */
    public static final String[] BTN_UNSELECT_ALL = {"UnSelect_All", "UnSelect_All", "UnSelect All" };

    /** Create RWS */
    public static final String[] BTN_CREATE = {"RWS_Create", "RWS_Create", "Create RWS" };

    /** Print */
    public static final String[] BTN_PRINT = {"Print", "Print", "Print" };

    /** Cancel */
    public static final String[] BTN_CANCEL = {"RWS_Cancel", "RWS_Cancel", "Cancel" };

    /** Serial Entry */
    public static final String[] BTN_SER_NUM = {"OpenWin_SerEntry", "OpenWin_SerEntry", "S" };

    // QC#18461-Sol#085 Add.
    /** Open_Win_WhFromRwsList */
    public static final String BTN_RWS_WH_POPUP = "Open_Win_WhFromRwsList";

    /** getWhNameFromRwsList */
    public static final String BTN_RWS_WH_NM = "getWhNameFromRwsList";

    /** getWhNameFromApply */
    public static final String BTN_APPLY_WH_NM = "getWhNameFromApply";

    /** Apply */
    public static final String BTN_APPLY = "Apply";
    
    // START 2023/03/06 TZ.Win [QC#61160, ADD]
    /** getSwhNameFromApply */
    public static final String BTN_APPLY_SWH_NM = "getSwhNameFromApply";
    // END 2023/03/06 TZ.Win [QC#61160, ADD]

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * NLAM1343W : Please confirm that you can execute Submit
     * processing. If there is ok, please click the Submit button
     * again.
     */
    public static final String NLAM1343W = "NLAM1343W";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // Tab ID
    // --------------------------------
    /** Tab ID: Order */
    public static final String TAB_ID_ORD = "Tab_Ord";

    /** Tab ID: RWS List */
    public static final String TAB_ID_RWS = "Tab_Rws";

    // --------------------------------
    // Function ID
    // --------------------------------
    /** Function ID: Inquiry User */
    public static final String FUNC_ID_INQ = "NLAL2030T010";

    /** Function ID: Update User */
    public static final String FUNC_ID_UPD = "NLAL2030T020";

    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    // --------------------------------
    // Event
    // --------------------------------
    /** Event Name: OpenWin_Wh */
    public static final String EVENT_NM_OPENWIN_WH = "OpenWin_Wh";

    /** Event Name: OpenWin_Swh */
    public static final String EVENT_NM_OPENWIN_SWH = "OpenWin_Swh";

    /** Event Name: OpenWin_RcvWh */
    public static final String EVENT_NM_OPENWIN_RCV_WH = "OpenWin_RcvWh";

    /** Event Name: OpenWin_Mdse */
    public static final String EVENT_NM_OPENWIN_MDSE = "OpenWin_Mdse";

    /** Event Name: OpenWin_FlipMdse */
    public static final String EVENT_NM_OPENWIN_FLIP_MDSE = "OpenWin_FlipMdse";

    /** Event Name: Open_Win_WhFromApply */
    public static final String EVENT_NM_OPENWIN_WH_APPLY = "Open_Win_WhFromApply";

    /** Event Name: Open_Win_WhFromApply */
    public static final String EVENT_NM_OPENWIN_WH_RWSLIST = "Open_Win_WhFromRwsList";
    // START 2023/03/06 TZ.Win [QC#61160, ADD]
    /** Event Name: Open_Win_WhFromApply */
    public static final String EVENT_NM_OPENWIN_SWH_APPLY = "Open_Win_SwhFromApply";
    // END 2023/03/06 TZ.Win [QC#61160, ADD]

    // QC#18461-Sol#085 Add
    /** Location Popup_Param: LOC_ROLE_TP_FOR_WH */
    public static final String LOC_ROLE_TP_FOR_WH = LOC_ROLE_TP.REGULAR_STOCK_WAREHOUSE + "," + LOC_ROLE_TP.RETURNED_ASSET_WAREHOUSE;
    // QC#30456
    // --------------------------------
    // RWS_CREATE_MODE
    // --------------------------------
    /** RWS_CREATE_MODE: PO */
    public static final String RWS_CREATE_MODE_PO = "1";

    /** RWS_CREATE_MODE: Return */
    public static final String RWS_CREATE_MODE_RTRN = "2";
}
