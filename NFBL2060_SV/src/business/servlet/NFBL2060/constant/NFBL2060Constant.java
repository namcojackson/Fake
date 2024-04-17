/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFBL2060.constant;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Invoice Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/22   Hitachi         Y.Tsuchimoto    Update          QC#12008
 * 2016/07/27   Hitachi         T.Tsuchida      Update          QC#12002
 * 2016/08/05   Fujitsu         C.Tanaka        Update          QC#12872, 12951
 * 2016/08/08   Fujitsu         C.Tanaka        Update          QC#12040
 * 2016/08/30   Fujitsu         C.Tanaka        Update          QC#5521
 * 2016/10/03   Hitachi         T.Tsuchida      Update          QC#13414
 * 2017/01/05   Fujitsu         H.Ikeda         Update          QC#12865
 * 2020/03/03   Fujitsu         H.Ikeda         Update          QC#53413
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 * </pre>
 */
public interface NFBL2060Constant {

    /** Empty Value */
    static final String EMPTY_STRING = "";
    /** Divide Display Vendor Name */
    static final String DIV_DPLY_VND_NM = " - ";

    /** Business ID */
    static final String BIZ_ID = "NFBL2060";
    /** Fuction Code 20 (Search) */
    static final String FUNC_CD_20 = "20";
    /** Fuction Code 40 (Update) */
    static final String FUNC_CD_40 = "40";

    // START 2020/03/03 [QC#53413, ADD]
    // mod start 2022/02/15 QC#57090
    //static final String MULTI_PO_NUM_ITEM = "*";
    static final String MULTI_ITEM = "*";
    // mod end 2022/02/15 QC#57090
    // END   2020/03/03 [QC#53413, ADD]

    /** Index Number */
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
    /** Index Number 8 */
    public static final int IDX_8 = 8;
    /** Index Number 9 */
    public static final int IDX_9 = 9;
    /** Index Number 10 */
    public static final int IDX_10 = 10;
    /** Index Number 13 */
    public static final int IDX_13 = 13;
    /** Index Number 20 */
    public static final int IDX_20 = 20;
    /** Index Number 30 */
    public static final int IDX_30 = 30;
    /** Index Number 50 */
    public static final int IDX_50 = 50;
    /** Index Number 60 */
    public static final int IDX_60 = 60;
    /** Index Number 100 */
    public static final int IDX_100 = 100;

    // ** CM_AP_INV_ASG_CD ** //
    /** CM_AP_INV_ASG_CD Automatically assigned */
    static final String CM_AP_INV_ASG_CD_A = "A";
    /** CM_AP_INV_ASG_CD Deleted */
	static final String CM_AP_INV_ASG_CD_D = "D";
    /** CM_AP_INV_ASG_CD Error */
	static final String CM_AP_INV_ASG_CD_E = "E";
    /** CM_AP_INV_ASG_CD Hold */
	static final String CM_AP_INV_ASG_CD_H = "H";
	/** CM_AP_INV_ASG_CD Maintenance */
    static final String CM_AP_INV_ASG_CD_M = "M";
    /** CM_AP_INV_ASG_CD Not Assigned */
	static final String CM_AP_INV_ASG_CD_N = "N";
    /** CM_AP_INV_ASG_CD Manually released */
    static final String CM_AP_INV_ASG_CD_R = "R";

    // Screen ID
    /** Screen ID NFBL2060Scrn00 */
    static final String SCRN_ID_00 = "NFBL2060Scrn00";

    // Start 2017/01/05 H.Ikeda [QC#12865,MOD]
    // Function ID
    /** Function ID NFBL2060T010 (Reference) */
    static final String FUNC_ID_NFBL2060T010 = "NFBL2060T010";
    /** Function ID NFBL2060T020 (Edit) */
    static final String FUNC_ID_NFBL2060T020 = "NFBL2060T020";
    // End   2017/01/05 H.Ikeda [QC#12865,MOD]

    // Tab
    /** Tab anchor Detailed */
    public static final String TAB_DETAILED = "Detailed";
    /** Tab anchor Summary */
    public static final String TAB_SUMMARY = "Summary";

    // Normal Button
    /** Function Button 1 */
    static final String BTN_NORMAL_SEARCH = "OnClick_Search";
    static final String BTN_NORMAL_NEW = "OnClick_New";
    static final String BTN_NORMAL_DETAILED_TAB_DONWLOAD_BUTTON = "OnClick_DetailedTabDownloadButton";
    static final String BTN_NORMAL_SUMMARY_TAB_DONWLOAD_BUTTON = "OnClick_SummaryTabDownloadButton";
	
    // Common Button
    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    // Scrn00
    // QC#12872 MOD Start
    /** Function Button 1 */
    String[] BTN_CMN_BLANK1 = {"btn1", "CMN_Save", "Save" };
    /** Function Button 2 */
    String[] BTN_CMN_BLANK2 = {"btn2", "CMN_Submit", "Submit" };
    /** Function Button 3 */
    String[] BTN_CMN_BLANK3 = {"btn3", "CMN_Apply", "Apply" };
    /** Function Button 4 */
    String[] BTN_CMN_BLANK4 = {"btn4", "CMN_Approve", "Approve" };
    /** Function Button 5 */
    String[] BTN_CMN_BLANK5 = {"btn5", "CMN_Reject", "Reject" };
    /** Function Button 6 */
    String[] BTN_CMN_BLANK6 = {"btn6", "CMN_Download", "Download"};
    /** Function Button 7 */
    String[] BTN_CMN_BLANK7 = {"btn7", "CMN_Delete", "Delete" };
    /** Function Button 8 */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };
    /** Function Button 9 */
    String[] BTN_CMN_BLANK9 = {"btn9", "CMN_Reset", "Reset" };
    /** Function Button 10 */
    String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };
    // QC#12872 MOD End

    // Message ID
    /** Please check at least 1 @ checkbox. */
    static final String NFBM0017E = "NFBM0017E";
    /** Please check only 1 checkbox. */
    static final String NFBM0064E = "NFBM0064E";
    /** You can not @ the selected invoice. */
    static final String NFBM0192E = "NFBM0192E";
    /** Please set at least one search criteria. */
    static final String NFBM0212E = "NFBM0212E";
    /**
     * Showing only first @ of total @ records. Please review your
     * search criteria.
     */
    static final String NFBM0001W = "NFBM0001W";
    /** The search ended normally. */
    static final String ZZM8002I = "ZZM8002I";
    /** Do you want to execute [@]? Please respond to message box. */
    static final String ZZM8101I = "ZZM8101I";
    /** [@] field is mandatory. */
    static final String ZZM9000E = "ZZM9000E";
    static final String ZZM9010E = "ZZM9010E";
    /** Invalid parameters found. */
    static final String ZZSM2014E = "ZZSM2014E";
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Error sign for String */
    static final String ERROR_STR = "E";

    // Frac Digit
    /** Frac Digit 2 */
    static final int FRAC_DIGIT_2 = 2;

    // START 2016/07/22 Y.Tsuchimoto [QC#12008,ADD]
    /** Event Name : OpenWin_Supplier */
    public static final String OPENWIN_SUPPLIER = "OpenWin_Supplier";
    // END   2016/07/22 Y.Tsuchimoto [QC#12008,ADD]

    // START 2016/07/25 Y.Tsuchimoto [QC#11999,ADD]
     /** Event Name : OpenWin_PurchaseOrder */
    public static final String OPENWIN_PURCHASE_ORDER = "OpenWin_PurchaseOrder";
    // END   2016/07/25 Y.Tsuchimoto [QC#11999,ADD]

    // START 2016/07/27 T.Tsuchida [QC#12002,ADD]
    /** Event Name : OpenWin_Receipt */
    public static final String OPENWIN_RECEIPT = "OpenWin_Receipt";
    // END   2016/07/27 T.Tsuchida [QC#12002,ADD]

    // START 2016/07/27 T.Tsuchida [QC#12239,ADD]
    /** Event Name : OnClick_TermLink */
    public static final String ONCLICK_TERM_LINK = "OnClick_TermLink";

    /** Event Name : OnClick_PaymentMethodLink */
    public static final String ONCLICK_PAYMENT_METHOD_LINK = "OnClick_PaymentMethodLink";
// END   2016/07/27 T.Tsuchida [QC#12239,ADD]

    // QC#12040 ADD Start    
    /** parameter index : 9 */
    public static final int PARAM_INDEX_9 = 9;

    /** parameter index : 10 */
    public static final int PARAM_INDEX_10 = 10;

    /** Comma */
    public static final String STR_COMMA = ".";

    /** @ is invalid. */
    static final String NFBM0041E = "NFBM0041E";

    /** Event Name : OpenWin_GlChrgAcctFrom */
    public static final String OPENWIN_GL_CHRG_ACCT_FROM = "OpenWin_GlChrgAcctFrom";

    /** Event Name : OpenWin_GlChrgAcctTo */
    public static final String OPENWIN_GL_CHRG_ACCT_TO = "OpenWin_GlChrgAcctTo";
    // QC#12040 ADD End

    // QC#12951 ADD Start
    /** Tale ID : D */
    public static final String TBL_ID_D = "D";

    /** Tale ID : S */
    public static final String TBL_ID_S = "S";
    // QC#12951 ADD End
}
