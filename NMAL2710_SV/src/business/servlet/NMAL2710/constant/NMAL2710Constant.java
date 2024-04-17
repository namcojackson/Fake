/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2710.constant;

/**
 *<pre>
 * NMAL2710Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Fujitsu         W.Honda         Create          N/A
 * 2017/11/16   Fujitsu         N.Sugiura       Update          CSA-QC#20597
 *</pre>
 */
public class NMAL2710Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL2710";

    /** Screen ID 00 */
    public static final String SCRN_ID = "NMAL2710Scrn00";

    /** Upload CSV Id. */
    public static final String UPLOAD_CSV_ID = "NMA2630001";

    /** Event Name: CMN_Close */
    public static final String CMN_CLOSE = "CMN_Close";

    /** Pop up parameter number : 0 */
    public static final int POPUP_PRM_NUM_0 = 0;

    /** Pop up parameter number : 1 */
    public static final int POPUP_PRM_NUM_1 = 1;

    /** Pop up parameter number : 2 */
    public static final int POPUP_PRM_NUM_2 = 2;

    /** Pop up parameter number : 3 */
    public static final int POPUP_PRM_NUM_3 = 3;

    /** Pop up parameter number : 4 */
    public static final int POPUP_PRM_NUM_4 = 4;

    /** Pop up parameter number : 5 */
    public static final int POPUP_PRM_NUM_5 = 5;

    /** Pop up parameter number : 6 */
    public static final int POPUP_PRM_NUM_6 = 6;

    /** Pop up parameter number : 7 */
    public static final int NWAL1130_POPUP_PRM_NUM = 7;

    /** Pop up parameter number : 9 */
    public static final int NMAL2630_POPUP_PRM_NUM = 9;

    /** Event Name : OpenWin_TrtyNmForMoveTo */
    public static final String OPENWIN_TRTYNMFORMOVETO = "OpenWin_TrtyNmForMoveTo";

    /** Event Name : OpenWin_TrtyNmForSearchItem */
    public static final String OPENWIN_TRTYNMFORSEARCHITEM = "OpenWin_TrtyNmForSearchItem";

    /** Event Name : OpenWin_TrtyNmForSearchItem */
    public static final String OPENWIN_RQSTHIST = "OpenWin_RqstHist";

    /** Pop up Name: Request History Search */
    public static final String POPUP_NM_RQST_HIST_SEARCH_POPUP = "Request History Search";

    /** Pop up Display Column Name: Request ID(*) */
    public static final String POPUP_LABEL_NM_RQST_ID = "Request ID";

    /** Pop up Table Column Name: POST_UPD_RQST_HDR_PK */
    public static final String POPUP_COL_NM_RQST_ID = "POST_UPD_RQST_HDR_PK";

    /** Pop up Display Column Name: Employee ID(*) */
    public static final String POPUP_LABEL_NM_EMP_ID = "Employee ID";

    /** Pop up Table Column Name: RQST_USR_ID */
    public static final String POPUP_COL_NM_EMP_ID = "RQST_USR_ID";

    /** Pop up Display Column Name: Employee Name(*) */
    public static final String POPUP_LABEL_NM_EMP_NM = "Employee Name";

    /** Pop up Table Column Name: RQST_USR_NM */
    public static final String POPUP_COL_NM_EMP_NM = "RQST_USR_NM";

    /** Pop up Display Column Name: Request Date(*) */
    public static final String POPUP_LABEL_NM_RQST_DATE = "Request Date";

    /** Pop up Table Column Name: RQST_CRAT_TS */
    public static final String POPUP_COL_NM_RQST_DATE = "RQST_CRAT_TS";

    /** Pop up Display Column Name: Request Status(*) */
    public static final String POPUP_LABEL_NM_RQST_STS = "Request Status";

    /** Pop up Table Column Name: RQST_RSLT_TP_DESC_TXT */
    public static final String POPUP_COL_NM_RQST_STS = "RQST_RSLT_TP_DESC_TXT";

    /** Pop up Display Column Name: Request Type(*) */
    public static final String POPUP_LABEL_NM_RQST_TP = "Request Type";

    /** Pop up Table Column Name: RQST_CRAT_SYS_TP_DESC_TXT */
    public static final String POPUP_COL_NM_RQST_TP = "RQST_CRAT_SYS_TP_DESC_TXT";

    /** Pop up Display Column Name: Mass Update Reason(*) */
    public static final String POPUP_LABEL_NM_MASS_UPD_RSN = "Mass Update Reason";

    /** Pop up Table Column Name: RQST_RSLT_CMNT_TXT */
    public static final String POPUP_COL_NM_MASS_UPD_RSN = "RQST_RSLT_CMNT_TXT";

    /** Pop up Sort Name: DESC */
    public static final String POPUP_SORT_KEY_DESC = "DESC";

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

    /** Search */
    public static final String[] BTN_SEARCH = {"Search", "Search", "Search" };

    /** Upload */
    public static final String[] BTN_UPLOAD = {"MoveWin_CsvUpload", "MoveWin_CsvUpload", "Upload" };

    /** Upload */
    public static final String[] BTN_GET_ORG_CD = {"GetOrgCd", "GetOrgCd", ">>" };

    /** Request_History */
    public static final String[] BTN_RQST_HIST = {"OpenWin_RqstHist", "OpenWin_RqstHist", "Request History" };

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /**  The value for [@] must be smaller than [@].  */
    public static final String NMAM0043E = "NMAM0043E";

    /**  The value for [@] must be over [@].  */
    public static final String NMAM0042E = "NMAM0042E";

    /** Please set at least one search criteria */
    public static final String NMAM0192E = "NMAM0192E";

    /** Please select at least 1 check box. */
    public static final String NMAM0835E = "NMAM0835E";

    /** Please input [@] */
    public static final String NMAM8368E = "NMAM8368E";

    /** Old Territory and New Territory can't be set up same value. */
    public static final String NMAM8578E = "NMAM8578E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";
    // 2017/11/16 CSA-QC#20597 Add Start
    /** Message Kind Warning */
    public static final String MESSAGE_KIND_WARN = "W";
    // 2017/11/16 CSA-QC#20597 Add End

}
