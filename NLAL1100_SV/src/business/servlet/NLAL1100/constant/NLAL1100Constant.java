/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */

package business.servlet.NLAL1100.constant;

/**
 *<pre>
 * Manage RMA Orders
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/21/2015   CITS            M.Ito           Create          N/A
 * 05/16/2016   CSAI            Y.Imazu         Update          QC#7972
 * 11/22/2016   CITS            Y.Fujii          Update          R362
 *</pre>
 */
public class NLAL1100Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NLAL1100";

    /** Screen ID */
    public static final String SCRN_ID = "NLAL1100Scrn00";

    /** Function Inquiry */
    public static final String FUNC_INQUIRY = "NLAL1100T010";

    /** Function Edit */
    public static final String FUNC_EDIT = "NLAL1100T020";

    /** Function Code : Update */
    public static final String FUNCTION_CD_UPDATE = "40";

    /** Function Code : Print */
    public static final String FUNCTION_CD_PRINT = "70";

    /***************************************************
     * Function Button
     * [0]:Button Name [1]:Event Name [2]:Button Level
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

    /** Button Send_Rqst */
    public static final String[] BTN_SEND_RQST = {"Send_Rqst", "Send_Rqst", "Send Carrier Request" };

    /** Button Comment Add */
    public static final String[] BTN_CMNT_ADD = {"Add_Comment", "Add_Comment", "Add" };

    /** Button Comment Save */
    public static final String[] BTN_CMNT_SAVE = {"Save_Comment", "Save_Comment", "Save" };

    /** Business button OpenWin_NtfyGrp */
    public static final String[]  BTN_NTFY_GRP = {"OpenWin_NtfyGrp", "OpenWin_NtfyGrp", "Grp" };

    /** Business button Search_NtfyGrp */
    public static final String[] BTN_SRCH_NTFY_GRP = {"Search_NtfyGrp", "Search_NtfyGrp", ">>" };

    /** Business button OpenWin_CarrInfo */
    public static final String[] BTN_CARR_INFO = {"OpenWin_CarrInfo", "OpenWin_CarrInfo", "Carr" };

    /** Business button Search_CarrInfo */
    public static final String[] BTN_SRCH_CARR = {"Search_CarrLineInfo", "Search_CarrLineInfo", ">>" };

    /** button Print */
    public static final String[] BTN_PRINT = {"Print", "Print", "Print" };

    /** button Print */
    public static final String[] BTN_APPLY = {"Apply", "Apply", "Apply" };

    /***************************************************
     * Message
     ***************************************************/

    /** Please execute again after checking the warning field. */
    public static final String NATM0001W = "NATM0001W";

    /** Please set at least one search criteria. */
    public static final String NLZM2276E = "NLZM2276E";

    /** The value for [@] must be bigger than [@]. */
    public static final String NLZM2277E = "NLZM2277E";

    /** Details require more than 1 row.  Please enter. */
    public static final String NLBM0002E = "NLBM0002E";

    /** Your request cannot be processed under this status. */
    public static final String NLZM2283E = "NLZM2283E";

    /** Not allowed to proceed when data is for "Send Request". */
    public static final String NLBM1311E = "NLBM1311E";

    /** The target data for the update does not exist. */
    public static final String NLCM0123E = "NLCM0123E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Enter at least one item. */
    public static final String NMAM8119E = "NMAM8119E";

    /***************************************************
     * Event Name
     ***************************************************/

    /** Event Name : CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /** Event Name : OpenWin_LocInfoForWh */
    public static final String EVENT_NM_OPENWIN_LOCINFO_FOR_WH = "OpenWin_LocInfoForWh";

    /** Event Name : OpenWin_LocInfoForSwh */
    public static final String EVENT_NM_OPENWIN_LOCINFO_FOR_SWH = "OpenWin_LocInfoForSwh";

    /** Event Name : OpenWin_CarrCode */
    public static final String EVENT_NM_OPENWIN_CARR = "OpenWin_CarrCode";

    /** Event Name : OpenWin_SlsRepCode */
    public static final String EVENT_NM_OPENWIN_SLS_REP = "OpenWin_SlsRepCode";

    /** Event Name : OpenWin_CarrInfo */
    public static final String EVENT_NM_OPENWIN_CARR_LINE = "OpenWin_CarrInfo";

    /** Event Name : OpenWin_NtfyGrp */
    public static final String EVENT_NM_OPENWIN_NTFY_GRP = "OpenWin_NtfyGrp";

    /** Event Name : OpenWin_CarrInfo_Apply */
    public static final String EVENT_NM_OPENWIN_CARR_APPLY = "OpenWin_CarrInfo_Apply";

    /** Event Name : OpenWin_NtfyGrp_Apply */
    public static final String EVENT_NM_OPENWIN_NTFY_GRP_APPLY = "OpenWin_NtfyGrp_Apply";

    /***************************************************
     * Popup param
     ***************************************************/

    /** Display Related Accounts : 04 (Ship To's Only) */
    public static final String DISP_RELATED_ACCT_SHIP = "04";

    /** Search Mode : 02 (Quick Lookup) */
    public static final String SEARCH_MODE_QUICK = "02";

    /** Display Address : Y (not only Primary) */
    public static final String DISP_ADDR_NOT_ONLY_PRM = "Y";

    /** Status : 02:Active & Inactive */
    public static final String STS_ACTIVE_INACTIVE = "02";

    /** Display Hirarchey Account : 03:Ship To's Only */
    public static final String DISP_HIRARCHEY_ACCT_SHIP = "03";
}
