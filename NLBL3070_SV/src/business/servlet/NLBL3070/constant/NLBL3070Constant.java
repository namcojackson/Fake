/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3070.constant;

/**
 *<pre>
 * Delivery Scheduling / Manage Deliveries
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   Fujitsu         Y.Taoka         Create          N/A
 * 01/25/2016   Hitachi         T.Tomita        Update          CSA QC#1029
 * 02/26/2016   CSAI            Y.Imazu         Update          QC#2046, 2201
 *</pre>
 */
public class NLBL3070Constant {

    /***************************************************
     * Function Button
     * [0]:Button Name [1]:Event Name [2]:Button Lavel
     ***************************************************/

    /** Function Button 1 */
    public static final String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Function Button 2 */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    public static final String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Function Button 4 */
    public static final String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /** Function Button 5 */
    public static final String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /** Function Button 6 */
    public static final String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_DETELE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Button Search */
    public static final String[] BTN_SEARCH = {"Search", "Search", "Search" };

    /** Button Select All */
    public static final String[] BTN_SELECT_ALL = {"Select_All", "Select_All", "Select All" };

    /** Button Un-select All */
    public static final String[] BTN_UNSELECT_ALL = {"UnSelect_All", "UnSelect_All", "UnSelect All" };

    /** Button Apply */
    public static final String[] BTN_APPLY = {"Apply", "Apply", "Apply" };

    /** Button Release_SO */
    public static final String[] BTN_REL_SO = {"Release_SO", "Release_SO", "Drop Shipping Order" };

    /** Button Send_Rqst */
    public static final String[] BTN_SEND_RQST = {"Send_Rqst", "Send_Rqst", "Send Carrier Request" };

    /** Button SO_Cancel */
    public static final String[] BTN_SO_CANCEL = {"SO_Cancel", "SO_Cancel", "Cancel" };

    /** Button SO Ship */
    public static final String[] BTN_SO_SHIP = {"Ship", "Ship", "Ship Confirm" };

    /** Button SO_Close */
    public static final String[] BTN_SO_CLOSE = {"SO_Close", "SO_Close", "SO Close" };

    /** Button Delivery_Conf */
    public static final String[] BTN_DELY_CONF = {"Delivery_Conf", "Delivery_Conf", "Delivery Confirm" };

    /** Button CarrInfo */
    public static final String[] BTN_CARR_INFO = {"OpenWin_CarrInfo", "OpenWin_CarrInfo", "Carr" };

    /** Button Serial */
    public static final String[] BTN_SER_NUM = {"OpenWin_SerEntry", "OpenWin_SerEntry", "S" };

    /** Button Serial */
    public static final String[] BTN_DELY_INSTN = {"Open_DelyInstn", "Open_DelyInstn", "Edit" };

    /** Screen ID */
    public static final String SCREEN_ID = "NLBL3070Scrn00";

    /** Business ID */
    public static final String BUSINESS_ID = "NLBL3070";

    /** Read User*/
    public static final String FUNC_ID_READ = BUSINESS_ID + "T010";

    /** Update User */
    public static final String FUNC_ID_UPDATE = BUSINESS_ID + "T020";

    /** CLOSE */
    public static final String CLOSE = "CLOSE";

    /** TAB ID:Scheduling */
    public static final String TAB_ID_SCHD = "Scheduling";

    /** TAB ID:Deliveries */
    public static final String TAB_ID_DELV = "Deliveries";

    /***************************************************
     * Event Name
     ***************************************************/

    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /** Event Name: OpenWin_LocInfoRtlWh */
    public static final String EVENT_NM_OPENWIN_LOCINFO_RTLWH = "OpenWin_LocInfoRtlWh";

    /** Event Name: OpenWin_LocInfoRtlSWh */
    public static final String EVENT_NM_OPENWIN_LOCINFO_RTLSWH = "OpenWin_LocInfoRtlSWh";

    /** Event Name: OpenWin_ModelName */
    public static final String EVENT_NM_OPENWIN_MODEL = "OpenWin_ModelName";

    /** Event Name: OpenWin_CarrCode */
    public static final String EVENT_NM_OPENWIN_CARR = "OpenWin_CarrCode";

    /** Event Name: OpenWin_CarrInfo */
    public static final String EVENT_NM_OPENWIN_CARR_LINE = "OpenWin_CarrInfo";

    /** Event Name: OpenWin_AplyCarrCode */
    public static final String EVENT_NM_OPENWIN_CARR_APLY = "OpenWin_AplyCarrCode";

    /** Display Hierarchy Accounts Code: 03 */
    public static final String DISP_HRCH_ACCT_CD_SHIP = "03";

    /***************************************************
     * Message
     ***************************************************/

    /** Please execute again after checking the warning field. */
    public static final String NATM0001W = "NATM0001W";

    /** The value for @ must be greater than or equal to @. */
    public static final String NFCM0780E = "NFCM0780E";

    /** Enter at least one item. */
    public static final String NMAM8119E = "NMAM8119E";

    /** Please set at least one search criteria. */
    public static final String NLZM2276E = "NLZM2276E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";
}
