/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NMAL4500.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/08   Fujitsu         F.Saito         Update          WDS#R-MS001
 * 2013/09/19   Fujitsu         N.Sugiura       Update          MEX-LC004
 * 2013/10/21   Fujitsu         D.Yanagisawa    Update          MEX-LC001
 * 2016/07/29   CITS            S.Endo          Update          QC#10840
 */
public interface NMAL4500Constant {

    String[] BTN_CMN_SAVE       = {"btn1",     "CMN_Save",         "Save"      };
    String[] BTN_CMN_SUBMIT     = {"btn2",     "CMN_Submit",       "Submit"    };
    String[] BTN_CMN_APPLY      = {"btn3",     "CMN_Apply",        "Apply"     };
    String[] BTN_CMN_APPROVE    = {"btn4",     "CMN_Approve",      "Approve"   };
    String[] BTN_CMN_REJECT     = {"btn5",     "CMN_Reject",       "Reject"    };
    String[] BTN_CMN_DOWNLOAD   = {"btn6",     "CMN_Download",     "Download"  };
    String[] BTN_CMN_DELETE     = {"btn7",     "CMN_Delete",       "Delete"    };
    String[] BTN_CMN_CLEAR      = {"btn8",     "CMN_Clear",        "Clear"     };
    String[] BTN_CMN_RESET      = {"btn9",     "CMN_Reset",        "Reset"     };
    String[] BTN_CMN_RETURN     = {"btn10",    "CMN_Return",       "Return"    };

    String TAB_SUBSTITUTION   = "Substitution";
    String TAB_REPLACEMENT    = "Replacement";

    String SCREEN_ID = "NMAL4500Scrn00";

    int RADIO_VALUE_BILL_TO = 1;
    int RADIO_VALUE_SELL_TO = 2;


    // BusinessID 
    String BUSINESS_ID = "NMAL4500";

    // Screen SBST VIEW
    String FUNCTION_READ = "NMAL4500T010";

    // Screen SBST INSTER/UPDATE
    String FUNCTION_UPDATE = "NMAL4500T020";


    //Popup window NMAL6050 action name
    String POPUP_ACTION_VENDOR = "OpenWin_Vendor_CD";
    String POPUP_ACTION_CITY = "OpenWin_City";
    String POPUP_ACTION_STATE = "OpenWin_State";
    String POPUP_ACTION_AFFILIATION = "OpenWin_Affiliation";
    // ADD START 2013/09/19 MEX-LC004
    String POPUP_ACTION_CURRENCY_CODE = "OpenWin_Currency_Code";
    // ADD END 2013/09/19 MEX-LC004
    // ADD START 2013/10/21 MEX-LC001
    String POPUP_ACTION_INVOICE_VENDOR = "OpenWin_Invoice_Vendor_CD";
    // ADD E N D 2013/10/21 MEX-LC001
    //VND ADDRESS check screen name
    String VND_CHECK = "VND_CHECK";

    String ZIP_CODE_FORMAT =  "'NNNNN' or '99999-9999'. (N: Character, 9: Number)";

    // ADD START 2013/05/08 WDS#R-MS001
    /** EMail Format : @ */
    public static final String AT_MARK = "@";
    /** EMail Format : Separator */
    public static final String COMMA = ",";
    /** EMail Format : Dot */
    public static final String DOT = ".";
    /** EMail Format : Error Message */
    public static final String MAIL_FORMAT = "Email address";
    // ADD END   2013/05/08 WDS#R-MS001

    /** Button Close Button Event Name */
    public static final String BTN_CMN_CLOSE_EVENT_NM = "CMN_Close";
    /** Business ID */
    public static final String BIZ_ID = "NMAL4500";

    /** Function Code : Search */
    public static final String FUNCTION_CD_SEARCH = "20";
    /** Link OpenWin_ST Button Event Name */
    public static final String LINK_OPENWIN_ST_EVENT_NM = "OpenWin_ST";

    /** Link OpenWin_PostCd Link Event Name */
    public static final String LINK_OPENWIN_POSTCD_EVENT_NM = "OpenWin_PostCd";

    /** Link OpenWin_City Link Event Name */
    public static final String LINK_OPENWIN_CTY_EVENT_NM = "OpenWin_City";

    /** Link OpenWin_Cnty Link Event Name */
    public static final String LINK_OPENWIN_CNTY_EVENT_NM = "OpenWin_Cnty";
    /** Index Number 0 */
    public static final int IDX_0 = 0;

    /** Index Number 1 */
    public static final int IDX_1 = 1;

    /** Index Number 2 */
    public static final int IDX_2 = 2;

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    /** Index Number 4 */
    public static final int IDX_4 = 4;

    /** Index Number 5 */
    public static final int IDX_5 = 5;

    /** Index Number 6 */
    public static final int IDX_6 = 6;

    /** Index Number 7 */
    public static final int IDX_7 = 7;
}
