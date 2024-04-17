/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0100.constant;

/**
 *<pre>
 * NWCL0100Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Fujitsu         S.Ohki          Create          N/A
 * 2016/09/08   Hitachi         Y.Takeno        Update          QC#13343
 *</pre>
 */
public class NWCL0100Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWCL0100";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWCL0100Scrn00";

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

    // TODO [Template] if popup screen then use below.
    // // --------------------------------
    // // Common button for Popup
    // // --------------------------------
    // /** F8 : Clear */
    // public static final String[] BTN_CMN_CLR = {"btn8",
    // "CMN_Clear", "Clear" };
    //
    // /** F10 : Return */
    // public static final String[] BTN_CMN_CLS = {"btn10",
    // "CMN_Close", "Close" };

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] field has too many digits entered. */
    public static final String NWCM0129W = "NWCM0129W";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** PopUp Parameter Suffix: */
    public static final String POPUP_PRM_SUFFIX = "P";

    /** PopUp Parameter Window Title */
    public static final String POPUP_PRM_WIN_TITLE = "Bill To Account Multi Select";

    /** PopUp Parameter Table Name: */
    public static final String POPUP_PRM_TABLE_NAME =  
          "( "
        + "    SELECT DISTINCT "
        + "        ACCT.GLBL_CMPY_CD  AS GLBL_CMPY_CD "
        + "      , ACCT.EZCANCELFLAG  AS EZCANCELFLAG "
        + "      , ACCT.SELL_TO_CUST_CD  AS DS_ACCT_NUM "
        + "      , ACCT.DS_ACCT_NM   AS DS_ACCT_NM "
        + "      FROM SELL_TO_CUST ACCT "
        + "         , ACCT_LOC LOC "
        + "         , PTY_LOC_WRK PTY "
        + "         , BILL_TO_CUST BILL "
        + "      WHERE "
        + "          ACCT.EZCANCELFLAG = '0' "
        + "      AND ACCT.GLBL_CMPY_CD = LOC.GLBL_CMPY_CD "
        + "      AND ACCT.SELL_TO_CUST_CD = LOC.DS_ACCT_NUM "
        + "      AND LOC.EZCANCELFLAG = '0' "
        + "      AND LOC.GLBL_CMPY_CD = PTY.GLBL_CMPY_CD "
        + "      AND LOC.PTY_LOC_PK = PTY.PTY_LOC_PK "
        + "      AND PTY.EZCANCELFLAG = '0' "
        + "      AND PTY.GLBL_CMPY_CD = BILL.GLBL_CMPY_CD "
        + "      AND PTY.LOC_NUM = BILL.LOC_NUM "
        + "      AND BILL.EZCANCELFLAG = '0' "
        + "      AND ACCT.DS_ACCT_ACTV_CUST_FLG = 'Y' "
        + "      AND BILL.RGTN_STS_CD = 'P20' "
        + ") ";
// 2016/09/08 QC#13343 Mod End

    /** PopUp Parameter Display Name (Name) */
    public static final String POPUP_PRM_DISP_NAME_NM = "Bill To Acct Nm";

    /** PopUp Parameter Display Name (Code) */
    public static final String POPUP_PRM_DISP_NAME_CD = "Bill To Acct Cd";

    /** PopUp Parameter SQL Name (Name) */
    public static final String POPUP_PRM_SQL_NAME_NM = "DS_ACCT_NM";

    /** PopUp Parameter SQL Name (Number) */
    public static final String POPUP_PRM_SQL_NAME_NUM = "DS_ACCT_NUM";

    /** PopUp Parameter Column Width (Name) */
    public static final String POPUP_PRM_COLUMN_WIDTH_NM = "60";

    /** PopUp Parameter Column Width (Code) */
    public static final String POPUP_PRM_COLUMN_WIDTH_CD = "20";

    /** PopUp Parameter Sort Key ASC */
    public static final String POPUP_PRM_SORT_KEY_ASC = "ASC";

    /** PopUp Parameter Display Related Account (Bill To Only) */
    public static final String POPUP_PRM_DISP_RLTD_ACCT_BILL_TO_ONLY = "03";

    /** PopUp Parameter Search Mode (Quick LookUp) */
    public static final String POPUP_PRM_SRCH_MODE_QUICK_LOOKUP = "02";

    /** PopUp Parameter Status (Active Only) */
    public static final String POPUP_PRM_STATUS_ACTV_ONLY = "01";

    /** PopUp Parameter Display Hirarchey Account (Bill To Only) */
    public static final String POPUP_PRM_DISP_HIR_ACCT_BILL_TO_ONLY = "02";

    /** Display Control Code (Past). */
    public static final String DISP_CTRL_CD_PAST = "1";

    /** Display Control Code (Current). */
    public static final String DISP_CTRL_CD_CURRENT = "2";

    /** Display Control Code (future). */
    public static final String DISP_CTRL_CD_FUTURE = "3";

    /** Display Control Code (New). */
    public static final String DISP_CTRL_CD_NEW = "4";

    /** Display Control Code (From is Sales date). */
    public static final String DISP_CTRL_CD_SALESDATE = "5";

    /** Link Name Account Group. */
    public static final String LINK_NAME_LIST_ACCT_GRP = "C";

}
