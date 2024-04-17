/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2020.constant;

/**
 *<pre>
 * NLAL2020Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         Y.Taoka         Create          N/A
 * 06/03/2016   CSAI            Y.Imazu         Update          QC#7981
 *</pre>
 */
public class NLAL2020Constant {

    /** Business ID */
    public static final String BIZ_ID = "NLAL2020";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NLAL2020Scrn00";

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

    /** F6 : Down load */
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
    /** Meter Read */
    public static final String[] BTN_MTR_READ = {"Open_MtrEnt", "Open_MtrEnt", "Meter Read" };

    /** Receive */
    public static final String[] BTN_RCV = {"Receive", "Receive", "Receive" };

    /** RWS Close */
    public static final String[] BTN_RWS_CLS = {"RWS_Close", "RWS_Close", "RWS Close" };

    /** Select All */
    public static final String[] BTN_SELECT_ALL = {"Select_All", "Select_All", "Select All" };

    /** UnSelect All */
    public static final String[] BTN_UNSELECT_ALL = {"UnSelect_All", "UnSelect_All", "UnSelect All" };

    /** Receive By */
    public static final String[] BTN_RCV_BY = {"OpenWin_RcvRtlWhInfo", "OpenWin_RcvRtlWhInfo", "Rcv" };

    /** Receive By (Name) */
    public static final String[] BTN_RCV_BY_NAME = {"Search_RcvRtlWh", "Search_RcvRtlWh", ">>" };

    /** Serial */
    public static final String[] BTN_SER_NUM = {"OpenWin_SerEntry", "OpenWin_SerEntry", "S" };


    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // Function ID
    // --------------------------------
    /** Function ID: Inquiry. */
    public static final String FUNC_ID_INQUIRY = "NLAL2020T010";

    /** Function ID: Update . */
    public static final String FUNC_ID_UPDATE = "NLAL2020T020";

    // --------------------------------
    // Event Name
    // --------------------------------
    /** Event Name: OpenWin_LocInfo */
    public static final String EVENT_NM_OPEN_WIN_LOC_INFO = "OpenWin_LocInfo";

    /** Event Name: OpenWin_RcvLocInfo */
    public static final String EVENT_NM_OPEN_WIN_RCV_LOC_INFO = "OpenWin_RcvLocInfo";

    /** Event Name: OpenWin_RcvRtlWhInfo */
    public static final String EVENT_NM_OPEN_WIN_RCV_RTL_WH_INFO = "OpenWin_RcvRtlWhInfo";

    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";
}
