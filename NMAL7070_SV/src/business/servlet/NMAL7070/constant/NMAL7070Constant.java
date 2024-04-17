/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7070.constant;

/**
 *<pre>
 * Supply Agreement Plan Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7070Constant {
    /** Business ID */
    public static final String BIZ_ID = "NMAL7070";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7070Scrn00";

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

    /** Search Button */
    public static final String[] BTN_SEARCH = {"Search", "Search", "Search" };

    /** New Registration Button */
    public static final String[] BTN_NEW_REGISTRATION = {"newRegistration", "MoveWin_NewRegistration", "NewRegistration" };

    /** View Details Button */
    public static final String[] BTN_VIEW_DETAIL = {"viewDetails", "MoveWin_ViewDetails", "View Details" };

    // --------------------------------
    // Message
    // --------------------------------

    /** A past date cannot be entered into the "Date Effective From". */
    public static final String NMAM0185E = "NMAM0185E";

    /** ZZZM9003I The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    // --------------------------------
    // Common
    // --------------------------------

    /** accountNum */
    public static final String EVNT_ACCT_NUM = "accountNum";

    /** accountNm */
    public static final String EVNT_ACCT_NM = "accountNm";

    /** CSMP */
    public static final String EVNT_CSMP = "CSMP";

    /** Branch */
    public static final String EVNT_BRANCH = "Branch";

    /** Common Pop-up Parameter/Where */
    public static final int CMN_PRM_WHERE_NUM = 4;

    /** Common Pop-up Parameter/Colum */
    public static final int CMN_PRM_COLUMN_NUM = 4;

    /** Common Pop-up Parameter/sort */
    public static final int CMN_PRM_SORT_NUM = 2;

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Mode Update */
    public static final String MODE_UPDATE = "02";

    /** Mode Update */
    public static final String MODE_NEW = "01";

    /** Search */
    public static final String EVENT_SEARCH = "Search";

    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";
}
