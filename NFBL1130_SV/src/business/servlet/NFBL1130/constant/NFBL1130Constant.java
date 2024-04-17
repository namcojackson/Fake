/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFBL1130.constant;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Accrual Write-off Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/12/21   Fujitsu         H.Ikeda         Update          QC#12865
 * </pre>
 */
public interface NFBL1130Constant {

    /** Empty Value */
    static final String EMPTY_STRING = "";

    /** Business ID */
    static final String BIZ_ID = "NFBL1130";

    // Function Code
    /** Fuction Code 20 (Search) */
    static final String FUNC_CD_20 = "20";
    /** Fuction Code 40 (Update) */
    static final String FUNC_CD_40 = "40";

    /** Screen Name */
    static final String SCRN_NM_00 = "NFBL1130Scrn00";

    /** Message Kind */
    static final String MESSAGE_KIND_E = "E";

    // Tab
    /** Tab anchor Header */
    public static final String TAB_HEADER = "Header";
    /** Tab anchor Holds */
    public static final String TAB_HOLDS = "Holds";
    /** Tab anchor Lines */
    public static final String TAB_LINES = "Lines";
    /** Tab anchor Distributions */
    public static final String TAB_DISTRIBUTIONS = "Distributions";
    
    // Table
    /** Table : H (HOLDS tab)*/
    public static final String TABLE_H = "H";
    /** Table : A (LINES tab) */
    public static final String TABLE_A = "A";
    /** Table : A1 (LINES tab left table) */
    public static final String TABLE_A1 = "A1";
    /** Table : A2 (LINES tab right table) */
    public static final String TABLE_A2 = "A2";
    /** Table : D (DISTRIBUTIONS tab) */
    public static final String TABLE_D = "D";

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
    /** Index Number 16 */
    public static final int IDX_16 = 16;
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
    /** Screen ID NFBL1130Scrn00 */
    static final String SCRN_ID_00 = "NFBL1130Scrn00";

    // Start 2016/12/21 H.Ikeda [QC#12865,MOD]
    // Function ID
    /** Function ID NFBL1130T010 (Reference) */
    static final String FUNC_ID_NFBL1130T010 = "NFBL1130T010";
    /** Function ID NFBL1130T020 (Edit) */
    static final String FUNC_ID_NFBL1130T020 = "NFBL1130T020";
    // End   2016/12/21 H.Ikeda [QC#12865,MOD]

    // Normal Button
    /** Function Button 1 */
    static final String BTN_NORMAL_SEARCH = "Search";
    static final String BTN_NORMAL_REFRESH = "Refresh";
    static final String BTN_NORMAL_INSERT_ROW = "InsertRow";
    static final String BTN_NORMAL_DELETE_ROW = "DeleteRow";
    static final String BTN_NORMAL_LINES_TAB_DOWNLOAD_BUTTON = "LinesTabDownloadButton";
    static final String BTN_NORMAL_MDSE = "MDSE";
    static final String BTN_NORMAL_ITEM_DESCRIPTION = "ItemDescription";
    static final String BTN_NORMAL_HOLD_TAB_DOWNLOAD_BUTTON = "HoldTabDownloadButton";
    static final String BTN_NORMAL_DISTRIBUTION_TAB_DOWNLOAD_BUTTON = "DistributionTabDownloadButton";
	
    // Common Button
    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    // Scrn00
    /** Function Button 1 */
    String[] BTN_CMN_BLANK1 = {"btn1", "CMN_Save", "Save" };
    /** Function Button 2 */
    String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };
    /** Function Button 3 */
    String[] BTN_CMN_BLANK3 = {"btn3", "CMN_Apply", "Apply" };
    /** Function Button 4 */
    String[] BTN_CMN_BLANK4 = {"btn4", "CMN_Approve", "Approve" };
    /** Function Button 5 */
    String[] BTN_CMN_BLANK5 = {"btn5", "CMN_Reject", "Reject" };
    /** Function Button 6 */
    String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };
    /** Function Button 7 */
    String[] BTN_CMN_BLANK7 = {"btn7", "CMN_Delete", "Delete" };
    /** Function Button 8 */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };
    /** Function Button 9 */
    String[] BTN_CMN_BLANK9 = {"btn9", "CMN_Reset", "Reset" };
    /** Function Button 10 */
    String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    // Message ID
    /** Please check at least 1 @ checkbox. */
    static final String NFBM0017E = "NFBM0017E";
    /** You cannot change @ before submit. */
    static final String NFBM0052E = "NFBM0052E";
    /** Please check only 1 checkbox. */
    static final String NFBM0064E = "NFBM0064E";
    /** You can not @ the selected invoice. */
    static final String NFBM0192E = "NFBM0192E";
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

    /** Error sign for String */
    static final String ERROR_STR = "E";

    // Frac Digit
    /** Frac Digit 2 */
    static final int FRAC_DIGIT_2 = 2;
}
