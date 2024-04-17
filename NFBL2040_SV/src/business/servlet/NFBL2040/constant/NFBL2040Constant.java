/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFBL2040.constant;

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
 * 2016/05/23   Hitachi         T.Tsuchida      Update          QC#4492
 * 2016/07/07   Hitachi         Y.Tsuchimoto    Update          QC#10995
 * 2016/07/22   Hitachi         Y.Tsuchimoto    Update          QC#12008
 * 2016/07/25   Hitachi         Y.Tsuchimoto    Update          QC#11999
 * 2016/07/27   Hitachi         T.Tsuchida      Update          QC#12002
 * 2016/08/01   Hitachi         T.Tsuchida      Update          QC#12009
 * 2016/08/02   Hitachi         T.Tsuchida      Update          QC#12040
 * 2016/08/05   Hitachi         T.Tsuchida      Update          QC#12714
 * 2016/08/05   Hitachi         T.Tsuchida      Update          QC#12968
 * 2016/08/05   Hitachi         T.Tsuchida      Update          QC#12988
 * 2016/08/24   Hitachi         Y.Tsuchimoto    Update          QC#13693
 * 2016/08/26   Hitachi         Y.Tsuchimoto    Update          QC#12043
 * 2016/09/14   Hitachi         Y.Tsuchimoto    Update          QC#13333
 * 2016/09/14   Hitachi         Y.Tsuchimoto    Update          QC#13156
 * 2016/09/26   Fujitsu         W.Honda         Update          Unit Test#201
 * 2016/10/04   Hitachi         T.Tsuchida      Update          QC#13414
 * 2016/10/18   Hitachi         Y.Tsuchimoto    Update          QC#12872
 * 2016/11/16   Hitachi         Y.Tsuchimoto    Update          QC#15940
 * 2017/01/05   Fujitsu         H.Ikeda         Update          QC#12865
 * 2017/10/11   CITS            T.Tokutomi      Update          QC#21640
 * 2017/12/26   Hitachi         Y.Takeno        Update          QC#23022
 * 2017/12/27   Hitachi         Y.Takeno        Update          QC#22143
 * 2018/01/23   CITS            T.Gotoda        Update          QC#21694, 21696
 * 2018/02/02   Hitachi         Y.Takeno        Update          QC#21703
 * 2018/02/26   Hitachi         Y.Takeno        Update          QC#23505
 * 2018/05/25   CITS            K.Ogino         Update          QC#25902,QC#25190,QC#25141
 * 2018/08/03   CITS            T.Hakodate      Update          QC#27494
 * 2018/11/29   Hitachi         Y.Takeno        Update          QC#28904
 * 2020/03/16   Fujitsu         H.Mizukami      Update          QC#55993
 * 2020/03/23   Fujitsu         H.Ikeda         Update          QC#53413
 * 2022/02/08   Hitachi         R.Onozuka       Update          QC#59613
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 * 2023/01/17   Hitachi         S.Nakatani      Update          QC#60812
 * </pre>
 */
public interface NFBL2040Constant {

    /** Empty Value */
    static final String EMPTY_STRING = "";

    /** NONE */
    static final String NONE = "NONE";

    /** Divide Display Vendor Name */
    static final String DIV_DPLY_VND_NM = " - ";

    /** Business ID */
    static final String BIZ_ID = "NFBL2040";

    // Function Code
    /** Fuction Code 20 (Search) */
    static final String FUNC_CD_20 = "20";
    /** Fuction Code 40 (Update) */
    static final String FUNC_CD_40 = "40";

    /** Screen Name */
    static final String SCRN_NM_00 = "NFBL2040Scrn00";

    /** Message Kind */
    static final String MESSAGE_KIND_E = "E";
    // START 2020/03/16 [QC#55993, ADD]
    static final String MESSAGE_KIND_W = "W";
    // END  2020/03/16 [QC#55993, ADD]

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
    /** Index Number 240 */
    public static final int IDX_240 = 240;

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
    /** Screen ID NFBL2040Scrn00 */
    static final String SCRN_ID_00 = "NFBL2040Scrn00";

    // Start 2017/01/05 H.Ikeda [QC#12865,MOD]
    // Function ID
    /** Function ID NFBL2040T010 (Reference) */
    static final String FUNC_ID_NFBL2040T010 = "NFBL2040T010";
    /** Function ID NFBL2040T020 (Edit) */
    static final String FUNC_ID_NFBL2040T020 = "NFBL2040T020";
    // End   2017/01/05 H.Ikeda [QC#12865,MOD]
    // START 2016/09/26 W.Honda [Unit Test#201,ADD]
    /** Function ID NFBL2040T030 (ThereforeEdit) */
    static final String FUNC_ID_NFBL2040T030 = "NFBL2040T030";
    // END   2016/09/26 W.Honda [Unit Test#201,ADD]
    // START 2017/12/27 [QC#22143, ADD]
    /** Function ID NFBL2040T040 (Approve) */
    static final String FUNC_ID_NFBL2040T040 = "NFBL2040T040";
    // END   2017/12/27 [QC#22143, ADD]

    // Normal Button
    /** Search */
    static final String BTN_NORMAL_SEARCH = "Search";
    /** Refresh */
    static final String BTN_NORMAL_REFRESH = "Refresh";
    /** InsertRow */
    static final String BTN_NORMAL_INSERT_ROW = "InsertRow";
    /** DeleteRow */
    static final String BTN_NORMAL_DELETE_ROW = "DeleteRow";
    /** LinesTabDownloadButton */
    static final String BTN_NORMAL_LINES_TAB_DOWNLOAD_BUTTON = "LinesTabDownloadButton";
    /** MDSE */
    static final String BTN_NORMAL_MDSE = "MDSE";
    /** ItemDescription */
    static final String BTN_NORMAL_ITEM_DESCRIPTION = "ItemDescription";
    /** HoldTabDownloadButton */
    static final String BTN_NORMAL_HOLD_TAB_DOWNLOAD_BUTTON = "HoldTabDownloadButton";
    /** DistributionTabDownloadButton */
    static final String BTN_NORMAL_DISTRIBUTION_TAB_DOWNLOAD_BUTTON = "DistributionTabDownloadButton";
    // START 2016/05/23 T.Tsuchida [QC#4492,MOD]
    /** OpenWin_AttachFile */
    static final String BTN_NORMAL_ATTACH = "OpenWin_AttachFile";
    // END 2016/05/23 T.Tsuchida [QC#4492,MOD]

    // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
    /** InvoiceCancel */
    public static final String BTN_NORMAL_INVOICE_CANCEL = "InvoiceCancel";

    /** Release */
    public static final String BTN_NORMAL_RELEASE = "Release";

    /** OpenWin_ChargeAccount */
    public static final String BTN_NORMAL_CHARGE_ACCOUNT = "OpenWin_ChargeAccount";
    // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]

    // START 2016/08/26 Y.Tsuchimoto [QC#12043,ADD]
    /** InsertRow(Hold) */
    static final String BTN_NORMAL_INSERT_ROW_HOLD = "InsertRowHold";

    /** DeleteRow(Hold) */
    static final String BTN_NORMAL_DELETE_ROW_HOLD = "DeleteRowHold";

    /** Hold */
    static final String BTN_NORMAL_HOLD = "Hold";
    // END   2016/08/26 Y.Tsuchimoto [QC#12043,ADD]

    // START 2016/09/26 W.Honda [Unit Test#201,ADD]
    /** OpenWin_AttachFile */
    static final String BTN_NORMAL_WORKFOLDER = "OpenWin_WorkingFolder";
    // START 2016/09/26 W.Honda [Unit Test#201,ADD]

    // START 2018/02/26 [QC#23505, ADD]
    static final String BTN_NORMAL_CORRECTION = "Correction";
    // END   2018/02/26 [QC#23505, ADD]

    // START 2020/03/23 [QC#53413, ADD]
    /** Line_Search */
    static final String BTN_NORMAL_LINE_SEARCH = "Line_Search";
    // END   2020/03/23 [QC#53413, ADD]

    // START 2017/12/27 [QC#22143, MOD]
    // START 2016/10/18 Y.Tsuchimoto [QC#12872,MOD]
    // Common Button
    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    // Scrn00
    /** Function Button 1 */
    String[] BTN_CMN_BLANK1 = {"btn1", EMPTY_STRING, "Save" };
    /** Function Button 2 */
    String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };
    /** Function Button 3 */
    String[] BTN_CMN_BLANK3 = {"btn3", EMPTY_STRING, "Apply" };
    /** Function Button 4 */
    String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };
    /** Function Button 5 */
    String[] BTN_CMN_BLANK5 = {"btn5", EMPTY_STRING, "Reject" };
    /** Function Button 6 */
    String[] BTN_CMN_BLANK6 = {"btn6", EMPTY_STRING, "Download"};
    /** Function Button 7 */
    String[] BTN_CMN_BLANK7 = {"btn7", EMPTY_STRING, "Delete" };
    /** Function Button 8 */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };
    /** Function Button 9 */
    String[] BTN_CMN_BLANK9 = {"btn9", EMPTY_STRING, "Reset" };
    /** Function Button 10 */
    String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };
    // END   2016/10/18 Y.Tsuchimoto [QC#12872,MOD]
    // END   2017/12/27 [QC#22143, MOD]

    // Message ID
    /** Please search for invoices first. */
    static final String NACM0090E = "NACM0090E";
    /** @ cannot be added because it is exceeding the maximum number of row [@] */
    static final String NFAM0072E = "NFAM0072E";
    /** Please check at least 1 checkbox. */
    static final String NFAM0075E = "NFAM0075E";
    /** Please check at least 1 @ checkbox. */
    static final String NFBM0017E = "NFBM0017E";
    /** Please check only 1 checkbox. */
    static final String NFBM0064E = "NFBM0064E";
    /** You can not @ the selected invoice. */
    static final String NFBM0192E = "NFBM0192E";
    /**
     * Showing only first @ of total @ records. Please review your
     * search criteria.
     */
    static final String NFBM0001W = "NFBM0001W";
    /** Unexpected Error Occurred */
    static final String NFBM0028E = "NFBM0028E";
    // START 2016/08/02 T.Tsuchida [QC#12040,ADD]
    /** @ is invalid. */
    static final String NFBM0041E = "NFBM0041E";
    // END 2016/08/02 T.Tsuchida [QC#12040,ADD]
    // START 2016/08/05 [QC#12968,ADD]
    /** @ doesn't exist. */
    static final String NFBM0044E = "NFBM0044E";
    // END 2016/08/05 [QC#12968,ADD]
    /** You cannot change @ before submit. */
    static final String NFBM0052E = "NFBM0052E";
    /** Total Amount doesn't match. */
    static final String NFBM0067E = "NFBM0067E";
    // START 2016/07/07 [QC#11331,ADD]
    /** Line Amount or Invoiced Quantity is not valid. */
    static final String NFBM0225E = "NFBM0225E";
    // END 2016/07/07 [QC#11331,ADD]
    // START 2016/08/05 [QC#12714,ADD]
    /** If invoice type is Credit Memo, invoice amount cannot be greater than 0. */
    static final String NFBM0229E = "NFBM0229E";
    // END 2016/08/05 [QC#12714,ADD]
    // START 2016/08/05 [QC#12988,ADD]
    /** Invoice amount has to be greater than 0. */
    static final String NFBM0230E = "NFBM0230E";
    /** Invoiced Quantity has to be less than or equal to 0. */
    static final String NFBM0231E = "NFBM0231E";
    // END 2016/08/05 [QC#12988,ADD]

    /** Please select "@" from the list. */
    static final String NLAM0047E = "NLAM0047E";
    /** The search ended normally. */
    static final String ZZM8002I = "ZZM8002I";
    // START 2016/07/07 [ADD]
    /** Process ended normally. **/
    public static final String ZZM8100I = "ZZM8100I";
    // END   2016/07/07 [ADD]
    /** Do you want to execute [@]? Please respond to message box. */
    static final String ZZM8101I = "ZZM8101I";
    /** [@] field is mandatory. */
    static final String ZZM9000E = "ZZM9000E";
    /** Date verification error occurred in [@] field (mm/dd/yyyy). */
    static final String ZZM9010E = "ZZM9010E";
    /** Invalid parameters found. */
    static final String ZZSM2014E = "ZZSM2014E";

    // START 2016/08/26 T.Tsuchida [QC#12043,ADD]
    /**
     * There is no data to be processed.
     */
    public static final String NFBM0238E = "NFBM0238E";
    // END   2016/08/26 T.Tsuchida [QC#12043,ADD]

    // START 2016/09/26 W.Honda [Unit Test#201,ADD]
    /**
     * Therefore Hold can not be allowed manually.
     */
    public static final String NFBM0253E = "NFBM0253E";

    /**
     * Privilege needed to release a Therefore Hold.
     */
    public static final String NFBM0254E = "NFBM0254E";
    // END   2016/09/26 W.Honda [Unit Test#201,ADD]

    // START 2016/09/14 Y.Tsuchimoto [QC#13333,ADD]
    /**
     * If [@] is @, [@] is required.
     */
    public static final String NFBM0255E = "NFBM0255E";
    // END   2016/09/14 Y.Tsuchimoto [QC#13333,ADD]

    // START 2017/01/27 E.Kameishi [QC#12988,ADD]
    /** Invoiced Quantity has to be greater than or equal to 0. */
    static final String NFBM0273E = "NFBM0273E";
    // END 2017/02/27 E.Kameishi [QC#12988,ADD]
    // START 2017/02/23 E.Kameishi [QC#12988,ADD]
    /** Line Amount has to be greater than 0 when Line Type is Tax or Freight. */
    static final String NFBM0274E = "NFBM0274E";
    // START 2017/02/23 E.Kameishi [QC#12988,ADD]
    /** Line Amount has to be less than 0 when Line Type is Tax or Freight. */
    static final String NFBM0278E = "NFBM0278E";
    /** Unit Price has to be greater than 0. */
    static final String NFBM0279E = "NFBM0279E";
    /** Error sign for String */
    static final String ERROR_STR = "E";

    // START 2020/03/23 [QC#53413, ADD]
    /** You cannot select PO Number that is already selected. please change. */
    public static final String NFBM0293E = "NFBM0293E";
    // END   2020/03/23 [QC#53413, ADD]

    // add start 2022/02/15 QC#57090
    /** You cannot select Vendor Return Number that is already selected. please change. */
    public static final String NFBM0295E = "NFBM0295E";
    // add end 2022/02/15 QC#57090

    /** Event Name CMN_Close */
    static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    // START 2016/08/02 T.Tsuchida [QC#12040,ADD]
    /**
     * Comma
     */
    public static final String STR_COMMA = ".";

    /**
     * parameter index : 10
     */
    public static final int PARAM_INDEX_10 = 10;
    // START 2016/08/02 T.Tsuchida [QC#12040,ADD]

    // START 2016/05/23 T.Tsuchida [QC#4492,MOD]
    /**
     * parameter index : 9
     */
    public static final int PARAM_INDEX_9 = 9;

    /**
     * Attachments Screen Display Mode : Read-Only
     */
    public static final String PARAMS_DISPLAY_MODE_READ_ONLY = "Read-Only";

    // START 2016/07/07 [QC#10995,MOD]
    /**
     * Attachments Screen Display Mode : Modification
     */
    public static final String PARAMS_DISPLAY_MODE_MODIFICATION = "Modification";
    // END   2016/07/07 [QC#10995,MOD]

    /**
     * Attachments Screen Grouping Text : AP Vendor Code
     */
    public static final String PARAMS_AP_VND_CD_KEY = "AP_VND_CD";

    /**
     * Attachments Screen Grouping Text : AP Vendor Invoice Number
     */
    public static final String PARAMS_AP_VND_INV_NUM_KEY = "AP_VND_INV_NUM";

    /**
     * Attachments Screen Function Name
     */
    public static final String PARAMS_FUNCTION_NAME = "Invoice Entry Attachments";

    /**
     * Attachments Screen Primary Key
     */
    public static final String PARAMS_PRIMARY_KEY = "Location And Inovice#";

    // START 2016/11/16 Y.Tsuchimoto [QC#15940,ADD]
    /**
     * Attachments Screen Document type table name
     */
    public static final String PARAMS_DOC_TYPE_TABLE_NAME = "AP_INV_ATT_DOC_TP";
    // END   2016/11/16 Y.Tsuchimoto [QC#15940,ADD]

    /**
     * Attachments Screen Attachments Limit
     */
    public static final String PARAMS_UPPER_KEY = "NFBL2040_ATT_LIMIT";

    /**
     * Attachments Screen Authorize File Extensions
     */
    public static final String PARAMS_EXTENSION_KEY = "NFBL2040_AUTHORIZE_FILE_EXT";

    /**
     * Attachments Screen Authorize File Volume
     */
    public static final String PARAMS_SIZE_KEY = "NFBL2040_AUTHORIZE_FILE_VOL";
    // END 2016/05/23 T.Tsuchida [QC#4492,MOD]

    // Event Name
    /** Event Name : OnClick_LocationLink */
    static final String ONCLICK_LOCATION_LINK = "OnClick_LocationLink";
    /** Event Name : OnClick_VendorNumberLink */
    static final String ONCLICK_VND_NUM_LINK = "OnClick_VendorNumberLink";
    /** Event Name : OnClick_VendorNameLink */
    static final String ONCLICK_VND_NM_LINK = "OnClick_VendorNameLink";
    // START 2016/07/22 Y.Tsuchimoto [QC#12008,ADD]
    /** Event Name : OpenWin_Supplier */
    public static final String OPENWIN_SUPPLIER = "OpenWin_Supplier";
    // END   2016/07/22 Y.Tsuchimoto [QC#12008,ADD]
    // START 2016/07/25 Y.Tsuchimoto [QC#11999,ADD]
    /** Event Name : OpenWin_PurchaseOrder1 */
    public static final String OPENWIN_PURCHASE_ORDER_1 = "OpenWin_PurchaseOrder1";
    /** Event Name : OpenWin_PurchaseOrder2 */
    public static final String OPENWIN_PURCHASE_ORDER_2 = "OpenWin_PurchaseOrder2";
    // END   2016/07/25 Y.Tsuchimoto [QC#11999,ADD]
    // START 2020/03/23 H.Ikeda [QC#53413, ADD]
    /** Event Name : OpenWin_PurchaseOrder3 */
    public static final String OPENWIN_PURCHASE_ORDER_3 = "OpenWin_PurchaseOrder3";
    /** Event Name : OpenWin_Receipt3 */
    public static final String OPENWIN_RECEIPT_3 = "OpenWin_Receipt3";
    // END   2020/03/23 H.Ikeda [QC#53413, ADD]
    // START 2016/07/27 T.Tsuchida [QC#12002,ADD]
    /** Event Name : OpenWin_Receipt1 */
    public static final String OPENWIN_RECEIPT_1 = "OpenWin_Receipt1";
    /** Event Name : OpenWin_Receipt2 */
    public static final String OPENWIN_RECEIPT_2 = "OpenWin_Receipt2";
    // END 2016/07/27 T.Tsuchida [QC#12002,ADD]
    // START 2016/08/01 T.Tsuchida [QC#12009,ADD]
    /** Event Name : OnClick_TermLink */
    public static final String ONCLICK_TERM_LINK = "OnClick_TermLink";
    // END 2016/08/01 T.Tsuchida [QC#12009,ADD]
    /** Event Name : CMN_Close */
    static final String CMN_CLOSE = "CMN_Close";

    // VAR_CHAR_CONST
    /** VAR_CHAR_CONST : NFBL2040_PO_MATCH */
    static final String VARCHAR_CONST_NFBL2040_PO_MATCH = "NFBL2040_PO_MATCH";

    // Start 2023/1/17 S.Nakatani [QC#60812, ADD]
    /** VAR_CHAR_CONST : NFBL2040_EXCL_AP_INV_CATG */
    static final String VARCHAR_CONST_EXCL_AP_INV_CATG = "NFBL2040_EXCL_AP_INV_CATG";
    // End 2023/1/17 S.Nakatani [QC#60812, ADD]
    
    // Frac Digit
    /** Frac Digit 2 */
    static final int FRAC_DIGIT_2 = 2;

    // START 2016/09/15 Y.Tsuchimoto [QC#13156,ADD]
    /** DISPLAY_MODE : Not other screen ref */
    public static final String DISPLAY_MODE_NOT_OTH_SCR_REF = "NOT_OTH_SCR_REF";

    /** DISPLAY_MODE : Not other screen edit */
    public static final String DISPLAY_MODE_NOT_OTH_SCR_EDIT = "NOT_OTH_SCR_EDIT";

    /** DISPLAY_MODE : Other screen ref */
    public static final String DISPLAY_MODE_OTH_SCR_REF = "OTH_SCR_REF";

    /** DISPLAY_MODE : Other screen edit */
    public static final String DISPLAY_MODE_OTH_SCR_EDIT = "OTH_SCR_EDIT";
    // END   2016/09/15 Y.Tsuchimoto [QC#13156,ADD]

    // START 2016/09/15 Y.Tsuchimoto [QC#13333,ADD]
    /** NONE MDSE_CD format **/
    public static final String NONE_MDSE_CD_FORMAT = "%04d";
    // END   2016/08/15 Y.Tsuchimoto [QC#13333,ADD]

    // START 2016/09/26 W.Honda [Unit Test#201,ADD]
    // VAR_CHAR_CONST_NM
    /** VAR_CHAR_CONST_NM :  */
    static final String CONST_NM_NFBL2040_THEREFORE_CATG_LIST = "NFBL2040_THEREFORE_CATG_LIST";
    // END 2016/09/26 W.Honda [Unit Test#201,ADD]
    
    // QC#21640 
    // HTML ID : Terms Link
    static final String ID_LINK_TERMS = "vndPmtTermDescTxt_A";

    // START 2017/12/26 [QC#23022, ADD]
    /** MDSE Code : FREIGHT */
    public static final String FREIGHT = "FREIGHT";
    // END   2017/12/26 [QC#23022, ADD]

    // START 2018/02/02 [QC#23022, ADD]
    /** CM_DEF_ACCT_CD : RETURN */
    public static String CM_DEF_ACCT_CD_RETURN = "RETURN";

    // END   2018/02/02 [QC#21703, ADD]
    /** VND_RTRN_STS_OPEN : Open */
    public static String VND_RTRN_STS_OPEN = "Open";

    /** VND_RTRN_STS_CREDITED : Open */
    public static String VND_RTRN_STS_CREDITED = "Credited";
    // END   2018/02/02 [QC#21703, ADD]

    // START 2018/02/27 [QC#23505, ADD]
    /** XX_LINE_TP_CD_NEW */
    public static String XX_LINE_TP_CD_VALID = "V";

    /** XX_LINE_TP_CD_OLD */
    public static String XX_LINE_TP_CD_INVALID = "I";

    /** XX_LINE_TP_CD_CANCEL */
    public static String XX_LINE_TP_CD_CREDIT = "C";
    // END   2018/02/27 [QC#23505, ADD]

    // START 2018/03/07 J.Kim [QC#24636,ADD]
    /** MDSE Code : TAX */
    public static final String TAX = "TAX";
    // END 2018/03/07 J.Kim [QC#24636,ADD]

    // START QC#25902,QC#25190,QC#25141
    /** XX_LINE_TP_CD_NO_CHANGE */
    public static String XX_LINE_TP_CD_NO_CHANGE = "N";
    static final String BTN_NORMAL_PO_LINE_CORRECTION = "PO_Line_Correction";
    public static final String OPENWIN_CORRECTION = "OpenWin_Correction";
    /** Event Name : OpenWin_VndRtrn1 */
    public static final String OPENWIN_VND_RTRN_1 = "OpenWin_VndRtrn1";
    /** Event Name : OpenWin_VndRtrn2 */
    public static final String OPENWIN_VND_RTRN_2 = "OpenWin_VndRtrn2";
    // END QC#25902,QC#25190,QC#25141
    // add start 2022/02/15 QC#57090
    /** Event Name : OpenWin_VndRtrn3 */
    public static final String OPENWIN_VND_RTRN_3 = "OpenWin_VndRtrn3";
    // add end 2022/02/15 QC#57090

    // QC#27494 MOD Start
    /** Function ID NFBL2040T050 (canont release therefor hold) */
    static final String FUNC_ID_NFBL2040T050 = "NFBL2040T050";
    
    /**
     * Privilege needed to release a Quantity Hold / Amount Hold.
     */
    public static final String NFBM0285E = "NFBM0285E";
    // QC#27494 MOD End

    // START 2018/11/29 [QC#28904, ADD]
    /** Event Name : Regenerate_Invoice */
    public static final String BTN_NORMAL_REGENERATE_INVOICE = "Regenerate_Invoice";
    // END   2018/11/29 [QC#28904, ADD]
    // START 2022/02/08 [QC#59613, ADD]
    /**
     * The status of this Purchase Order Line does not allow.
     */
    public static final String NFBM0294E = "NFBM0294E";
    // END 2022/02/08 [QC#59613, ADD]
}
