/* <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSAL0920.constant;

/**
 *<pre>
 * Contract Billing Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         K.Kasai         Create          N/A
 * 2016/01/06   Hitachi         T.Tomita        Update          QC#1029
 * 2016/03/30   Hitachi         T.Aoyagi        Update          QC#1467
 * 2016/10/18   Hitachi         N.Arai          Update          QC#15216
 * 2017/01/23   Hitachi         K.Kitachi       Update          QC#17219
 *</pre>
 */
public class NSAL0920Constant {

 // [0]:Button Name [1]:Event Name [2]:Button Label
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
    public static final String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** ScreenID : NSAL0920Scrn00 */
    public static final String SCREEN_ID = "NSAL0920Scrn00";

    /** BizID : NSAL0920 */
    public static final String BIZ_ID = "NSAL0920";

    /** FUNCTION */
    public static final String FUNC_SEARCH = "20";

    // START 2016/01/06 T.Tomita [QC#1029, ADD]
    /** Display Hierarchy Accounts Code: 01 */
    public static final String DISP_HRCH_ACCT_CD_ALL = "01";

    /** Display Hierarchy Accounts Code: 02 */
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";

    /** OpenWin_AcctCust */
    public static final String OPENWIN_ACCTCUST = "OpenWin_AcctCust";

    /** OpenWin_AcctCustNm */
    public static final String OPENWIN_ACCTCUSTNM = "OpenWin_AcctCustNm";

    /** OpenWin_BillToLoc */
    public static final String OPENWIN_BILLTOLOC = "OpenWin_BillToLoc";

    /** OpenWin_BillToLocNm */
    public static final String OPENWIN_BILLTOLOCNM = "OpenWin_BillToLocNm";
    // END 2016/01/06 T.Tomita [QC#1029, ADD]
    // START 03/30/2016 T.Aoyagi [QC#1467, ADD]
    /** Notification : NO */
    public static final String NTFY_YES = "YES";
    // END 03/30/2016 T.Aoyagi [QC#1467, ADD]

// START 2016/10/18 N.Arai [QC#15216, MOD]
    /** OpenWin_Branch */
    public static final String OPENWIN_BRANCH = "Service Branch Popup";

    /** String : end date */
    public static final String END_DT = "99991231";
// END 2016/10/18 N.Arai [QC#15216, MOD]

    // START 2017/01/23 K.Kitachi [QC#17219, ADD]
    /** OpenWin_Supv */
    public static final String OPENWIN_SUPV = "Resource Search Popup";
    // END 2017/01/23 K.Kitachi [QC#17219, ADD]
}
