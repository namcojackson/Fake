/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840.constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   Fujitsu         T.Murai         Create          N/A
 * 2016/05/19   Fujitsu         T.Murai         Update          S21_NA#7659
 * 2016/05/30   Fujitsu         T.Murai         Update          S21_NA#8393
 * 2016/10/06   Fujitsu         Mz.Takahashi    Update          QC#14431
 * 2016/11/25   Fujitsu         M.Ohno          Update          S21_NA#15733
 * 2018/07/26   Fujitsu         H.Kumagai       Update          QC#14307
 * 2018/08/08   Fujitsu         M.Ishii         Update          QC#26551
 * 2018/12/14   Fujitsu         Hd.Sugawara     Update          QC#24022
 * 2019/12/10   Fujitsu         S.Kosaka        Update          QC#54233
 * 2023/10/10   Hitachi         T.Fukuta        Update          CSA-QC#61730
 * </pre>
 */
public class NWAL1840Constant {
    /** Business ID */
    public static final String BIZ_ID = "NWAL1840";

    /** ScreenID */
    public static final String SCREEN_ID = "NWAL1840Scrn00";

    /** Function Code (Search) */
    public static final String FUNC_CD_SRCH = "20";

    /** Function Code (Submit) */
    public static final String FUNC_CD_UPDT = "40";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // Common button
    // --------------------------------
    /** Common button 1 */
    public static final String BTN_CMN_SAV_BTN_NM = "btn1";

    /** Function Button 1 Event Name */
    public static final String BTN_CMN_SAV_EVENT_NM = "CMN_Save";

    /** Function Button 1 Label */
    public static final String BTN_CMN_SAV_LABEL = "Save";

    /** Function Button 2 Button Name */
    public static final String BTN_CMN_SUB_BTN_NM = "btn2";

    /** Function Button 2 Event Name */
    public static final String BTN_CMN_SUB_EVENT_NM = "CMN_Submit";

    /** Function Button 2 Label */
    public static final String BTN_CMN_SUB_LABEL = "Submit";

    /** Function Button 3 Button Name */
    public static final String BTN_CMN_APL_BTN_NM = "btn3";

    /** Function Button 3 Event Name */
    public static final String BTN_CMN_APL_EVENT_NM = "CMN_Apply";

    /** Function Button 3 Label */
    public static final String BTN_CMN_APL_LABEL = "Apply";

    /** Function Button 4 Button Name */
    public static final String BTN_CMN_ARV_BTN_NM = "btn4";

    /** Function Button 4 Event Name */
    public static final String BTN_CMN_ARV_EVENT_NM = "CMN_Approve";

    /** Function Button 4 Label */
    public static final String BTN_CMN_ARV_LABEL = "Approve";

    /** Function Button 5 Button Name */
    public static final String BTN_CMN_RJT_BTN_NM = "btn5";

    /** Function Button 5 Event Name */
    public static final String BTN_CMN_RJT_EVENT_NM = "CMN_Reject";

    /** Function Button 5 Label */
    public static final String BTN_CMN_RJT_LABEL = "Reject";

    /** Function Button 6 Button Name */
    public static final String BTN_CMN_DWL_BTN_NM = "btn6";

    /** Function Button 6 Event Name */
    public static final String BTN_CMN_DWL_EVENT_NM = "CMN_Download";

    /** Function Button 6 Label */
    public static final String BTN_CMN_DWL_LABEL = "Download";

    /** Function Button 7 Button Name */
    public static final String BTN_CMN_DEL_BTN_NM = "btn7";

    /** Function Button 7 Event Name */
    public static final String BTN_CMN_DEL_EVENT_NM = "CMN_Delete";

    /** Function Button 7 Label */
    public static final String BTN_CMN_DEL_LABEL = "Delete";

    /** Function Button 8 Button Name */
    public static final String BTN_CMN_CLR_BTN_NM = "btn8";

    /** Function Button 8 Event Name */
    public static final String BTN_CMN_CLR_EVENT_NM = "CMN_Clear";

    /** Function Button 8 Label */
    public static final String BTN_CMN_CLR_LABEL = "Clear";

    /** Function Button 9 Button Name */
    public static final String BTN_CMN_RST_BTN_NM = "btn9";

    /** Function Button 9 Event Name */
    public static final String BTN_CMN_RST_EVENT_NM = "CMN_Reset";

    /** Function Button 9 Label */
    public static final String BTN_CMN_RST_LABEL = "Reset";

    /** Function Button 10 Button Name */
    public static final String BTN_CMN_RTN_BTN_NM = "btn10";

    /** Function Button 10 Event Name */
    public static final String BTN_CMN_RTN_EVENT_NM = "CMN_Return";

    /** Function Button 10 Label */
    public static final String BTN_CMN_RTN_LABEL = "Return";

    /** Tab anchor Customer / Contact */
    public static final String TAB_CUSTOMER = "Customer";

    /** Tab anchor Header */
    public static final String TAB_HEADER = "Header";

    /** Tab anchor Lines */
    public static final String TAB_LINES = "Lines";

    /** Tab anchor Comments */
    public static final String TAB_COMMENTS = "Comments";

    /** Tab anchor Additional Data */
    public static final String TAB_ADDITIONAL = "Additional";

    /** No Selected Detail Line */
    public static final int NO_SLCT_DTL = -1;

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

    /** Index Number 11 */
    public static final int IDX_11 = 11;

    /** Index Number 12 */
    public static final int IDX_12 = 12; 

    /** Index Number 13 */
    public static final int IDX_13 = 13;

    /** Index Number 20 */
    public static final int IDX_20 = 20;

    /** Index Number 20 */
    public static final int IDX_22 = 22;

    /** Index Number 20 */
    public static final int IDX_26 = 26;

    /** Index Number 20 */
    public static final int IDX_27 = 27;

    /** Index Number 30 */
    public static final int IDX_30 = 30;

    /** Index Number 30 */
    public static final int IDX_31 = 31;

    /** Index Number 30 */
    public static final int IDX_33 = 33;

    /** Index Number 50 */
    public static final int IDX_50 = 50;

    /** Index Number 30 */
    public static final int IDX_80 = 80;

    /** Index Number 50 */
    public static final int IDX_100 = 100;

    /** Varchar Const Key (NWAL1840_ATT_LIMIT) */
    public static final String KEY_NWAL1840_ATT_LIMIT = "NWAL1840_ATT_LIMIT";

    /** Varchar Const Key (NWAL1840_AUTHORIZE_FILE_EXT) */
    public static final String KEY_NWAL1840_AUTHORIZE_FILE_EXT = "NWAL1840_AUTHORIZE_FILE_EXT";

    /** Varchar Const Key (NWAL1840_AUTHORIZE_FILE_VOL) */
    public static final String KEY_NWAL1840_AUTHORIZE_FILE_VOL = "NWAL1840_AUTHORIZE_FILE_VOL";

    /** Code Table Name : SCHD_ATT_DOC_TP */
    public static final String CODE_TABLE_SCHD_ATT_DOC_TP = "SCHD_ATT_DOC_TP"; // S21_NA#7659 Add

    // ----------- Event Button -----------
    /** Search Button Event Name */
    public static final String BTN_SRCH_EVENT_NM = "Search";

    /** Sales Credit Button Event Name */
    public static final String BTN_SLS_CREDIT_EVENT_NM = "OpenWin_SalesCredit";

    /** Add Line Button Event Name (Contact) */
    public static final String BTN_ADD_CTAC_EVENT_NM = "Add_ContactLine";

    /** Delete Line Button Event Name */
    public static final String BTN_DEL_CTAC_EVENT_NM = "Delete_ContactLine";

    /** Line All Expand Button Event Name (Item) */
    public static final String BTN_LINE_EXPAND_EVENT_NM = "Line_All_Expand";

    /** Line All Collapsed Button Event Name (Item) */
    public static final String BTN_LINE_COLLAPSE_EVENT_NM = "Line_All_Collapsed";

    /** Add Line Button Event Name (Item) */
    public static final String BTN_ADD_ITEM_EVENT_NM = "Add_ItemLine";

    /** Add Line Button Event Name (Item) */
    public static final String BTN_ADD_SCHD_EVENT_NM = "Add_SchdLine";

    /** Cancel Button Event Name */
    public static final String BTN_CANC_EVENT_NM = "Cancel_Line";

    /** Line Assist Button Event Name */
    public static final String BTN_SCHD_ASSIST_EVENT_NM = "OpenWin_Schd_line_Assist";

    /** Price Change Button Event Name */
    public static final String BTN_PRICE_CHANGE_EVENT_NM = "OpenWin_PriceChanges";

    /** Shipping Detail Button Event Name */
    public static final String BTN_SHPG_DTL_EVENT_NM = "OpenWin_ShippingDetail";

    /** View Change Log Button Event Name */
    public static final String BTN_VIEW_CHNG_LOG_EVENT_NM = "OpenWin_ViewChangeLog";

    /** Attachment Button Event Name */
    public static final String BTN_ATT_EVENT_NM = "OpenWin_Attachments";

    /** Bill Event List */
    public static final List<String> BILL_EVENT_LIST;
    static {
        List<String> billEventList = new ArrayList<String>();
        billEventList.add("OpenWin_BillTo");
        billEventList.add("OnBlur_DeriveFromBillToName");
        billEventList.add("OnBlur_DeriveFromBillToAccount");
        billEventList.add("OnBlur_DeriveFromBillToLocation");
        BILL_EVENT_LIST = Collections.unmodifiableList(billEventList);
    }

    /** Ship Event List */
    public static final List<String> SHIP_EVENT_LIST;
    static {
        List<String> shipEventList = new ArrayList<String>();
        shipEventList.add("OpenWin_ShipTo");
        shipEventList.add("OnBlur_DeriveFromShipToName");
        shipEventList.add("OnBlur_DeriveFromShipToAccount");
        shipEventList.add("OnBlur_DeriveFromShipToLocation");
        SHIP_EVENT_LIST = Collections.unmodifiableList(shipEventList);
    }

    /** Sold Event List */
    public static final List<String> SOLD_EVENT_LIST;
    static {
        List<String> soldEventList = new ArrayList<String>();
        soldEventList.add("OpenWin_SoldTo");
        soldEventList.add("OnBlur_DeriveFromSoldToName");
        soldEventList.add("OnBlur_DeriveFromSoldToAccount");
        soldEventList.add("OnBlur_DeriveFromSoldToLocation");
        SOLD_EVENT_LIST = Collections.unmodifiableList(soldEventList);
    }

    // ----------- Common -----------
    /** Comma */
    public static final String COMMA = ",";

    /** Blank */
    public static final String BLANK = "";

    /** Space */
    public static final String SPACE = " ";

    /** Percent */
    public static final String PERCENT = "%"; // S21_NA#8393 Add

    /** New Line */
    public static final String NEW_LINE = "\r\n";

    // Add Start 2016/11/25 M.Ohno S21_NA#15733
    /** Slash */
    public static final String SLASH = "/";

    /** Period */
    public static final String PERIOD = "\\.";

    /** Hyphen */
    public static final String HYPHEN = "-";
    // Add End   2016/11/25 M.Ohno S21_NA#15733

    /** 8 Digit Mode */
    public static final String EIGHT_DIGIT_MODE = "08";

    /** Phone Check Pattern */
    public static final String CHK_PHONE_PATTERN = "(\\d{10}|\\d{3}-\\d{3}-\\d{4})";

    /** EXT Check Pattern */
    public static final String CHK_EXT_PATTERN = "\\d{1,4}";

    /** EMail Check Pattern */
    // 2016/10/06 QC#1443 Del --------------
    //public static final String CHK_EMAIL_PATTERN = "^[a-zA-Z0-9]+([\\w\\.\\-]*[\\w\\-])*@([\\w\\-]+\\.)+[\\w\\-]+$";

    // ----------- Message ID -----------
    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Please check at least 1 checkbox. */
    public static final String NWAM0186E = "NWAM0186E";

    /** The specified format is incorrect. It must be @ */
    public static final String NWAM0664E = "NWAM0664E";

    /** @ has not been entered. */
    public static final String NWAM0671E = "NWAM0671E";

    /** Only 1 @ can be selected. */
    public static final String NWAM0681E = "NWAM0681E";

    /** Multiple Details cannot be processed at the same time. */
    public static final String NWAM0683E = "NWAM0683E";

    /** @ must be selected. */
    public static final String NWAM0667E = "NWAM0667E";

    /** This [@] has not been sent to Order yet. */
    public static final String NWAM0802E = "NWAM0802E";

    /** At least one contract point has to be entered. */
    public static final String NWAM0832E = "NWAM0832E";
    
    // Add Start 2016/11/25 M.Ohno S21_NA#15733
    /**
     * Phone number format is incorrect. Minimum length is 7digit,
     * Maximum length is 20 digit.
     */
    public static final String NWAM0763E = "NWAM0763E";

    // Del Start 2018/12/14 QC#24022
    ///** Extention length has been exceeded. Maximum length is 4digit. */
    //public static final String NWAM0764E = "NWAM0764E";
    // Del End 2018/12/14 QC#24022
    // Add End   2016/11/25 M.Ohno S21_NA#15733

    // 2018/08/08 QC#26551 Add Start
    /** "Price Change" is not available, because there are some Items that can't get "Sell Price". */
    public static final String NWAM0962E = "NWAM0962E";
    // 2018/08/08 QC#26551 Add End

    // Add Start 2018/12/14 QC#24022
    /** @ length has been exceeded. Maximum length is @ digit. */
    public static final String NWAM0964E = "NWAM0964E";
    // Add End 2018/12/14 QC#24022

    // ----------- Message PARAM -----------
    /** Supply Quote Entry */
    public static final String MSG_PARAM_BIZ_NM = "Scheduling Agreement Entry";

    /** Quote Number */
    public static final String MSG_PARAM_SCHD_NUM = "Schedule Number";

    /** Reason */
    public static final String MSG_PARAM_RSN = "Reason";

    /** Category */
    public static final String MSG_PARAM_CATG = "Category";

    /** item Code */
    public static final String MSG_PARAM_ITEM_NUM = "Item#";

    /** Line */
    public static final String MSG_PARAM_LINE = "Line";

    /** Schedule Line */
    public static final String MSG_PARAM_SCHD_LINE = "Schd Line";

    /** Tel Format */
    public static final String TEL_FORMAT = "9999999999 or 999-999-9999";

    /** EXT FORMAT */
    public static final String EXT_FORMAT = "9999";

    /** E-Mail Format */
    public static final String EMAIL_FORMAT = "aaa@bbb";

    // ----------- Authority -----------
    /** Reference Authority */
    public static final String REF_AUTH = "NWAL1840T010";

    /** Registration Authority (No Price Authority) */
    public static final String NO_PRC_RGTN_AUTH = "NWAL1840T020";

    /** Registration Authority */
    public static final String ALL_RGTN_AUTH = "NWAL1840T030";

    // 2018/06/21 S21_NA#14307 Add Start
    /** Result Parameter(toSpecialInstruction) */
    public static final String RESULT_PARAM_SPECIAL_INSTRUCTION = "toSpecialInstruction";
    // 2018/06/21 S21_NA#14307 Add End

    // Add Start 2018/12/14 QC#24022
    /** Max length of extension */
    public static final int EXT_MAX_LENGTH = 10;
    // Add End 2018/12/14 QC#24022

    // 2019/12/10 QC#54233 Add Start
    /** Qty Allowed */
    public static final String MSG_PARAM_QTY_ALLW = "Qty Allowed";
    // 2019/12/10 QC#54233 Add End

    // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
    /** Tab anchor SA History */
    public static final String TAB_SA_HISTORY = "SaHistory";

    /** Notes Button Event Name */
    public static final String BTN_NOTES_EVENT_NM = "MoveWin_MemoEntry";

    /** Memo Entry: Item Title */
    public static final String MEMO_ENTRY_ITEM_TITLE = "SA Number";

    /** Memo Entry: key SVC_MEMO_TRX_NUM */
    public static final String MEMO_ENTRY_KEY_TRX_NUM = "SVC_MEMO_TRX_NUM";

    /** Memo Entry: key SVC_MEMO_TRX_NM */
    public static final String MEMO_ENTRY_KEY_TRX_NM = "SVC_MEMO_TRX_NM";

    /** Memo Entry: value SVC_MEMO_TRX_NM */
    public static final String MEMO_ENTRY_VAL_TRX_NM = "SCHD_AGMT_NUM";
    // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
}
