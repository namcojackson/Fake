/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2720.constant;

/**
 *<pre>
 * NMAL2720Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/22   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL2720Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL2720";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL2720Scrn00";

    /** Upload CSV Id. */
    public static final String UPLOAD_CSV_ID = "NMA2640001";

    /** Parameters length Common Code Pop Up: 7 */
    public static final int PRM_LENGTH_NWAL1130 = 7;

    /** Parameters length Organization Pop up: 7 */
    public static final int PRM_LENGTH_NMAL2530 = 7;

    /** Parameters length Resource Look up Pop up: 4 */
    public static final int PRM_LENGTH_NMAL2570 = 4;

    /** Event Name : ResrcLookUpForName */
    public static final String EVENT_NM_RESRC_LOOK_UP_FOR_CD = "OpenWin_ResrcLookUpForCd";

    /** Event Name : ResrcLookUpForName */
    public static final String EVENT_NM_RESRC_LOOK_UP_FOR_NAME = "OpenWin_ResrcLookUpForName";

    /** Event Name : ResrcLookUpForName */
    public static final String EVENT_NM_RESRC_LOOK_UP_FOR_NUM = "OpenWin_ResrcLookUpForNum";

    /** Event Name : ResrcLookUpForName */
    public static final String EVENT_NM_RESRC_LOOK_UP_DTL = "OpenWin_ResrcLookUpDtl";

    /** Pop up Display Column Name: Request ID(*) */
    public static final String POPUP_LABEL_NM_RQST_ID = "Request ID";

    /** Pop up Table Column Name: MOVE_ORG_RQST_HDR_PK */
    public static final String POPUP_COL_NM_RQST_ID = "MOVE_ORG_RQST_HDR_PK";

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

    /** Button name : CMN_Close */
    public static final String BTN_CMN_CLOSE = "CMN_Close";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /**  The value for [@] must be over [@].  */
    public static final String NMAM0042E = "NMAM0042E";

    /**  The value for [@] must be smaller than [@].  */
    public static final String NMAM0043E = "NMAM0043E";

    /** Please set at least one search criteria */
    public static final String NMAM0192E = "NMAM0192E";

    /** Please select at least 1 check box. */
    public static final String NMAM0835E = "NMAM0835E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

}
