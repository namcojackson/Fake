/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/06   CUSA            Fujitsu         Create          N/A
 * 2015/10/15   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/26   Fujitsu         C.Tanaka        Update          QC#4349
 * 2016/02/29   Fujitsu         C.Tanaka        Update          QC#2824
 * 2016/04/06   Fujitsu         C.Yokoi         Update          QC#6633
 * 2016/04/29   SRAA            Y.Chen          Update          QC#6559
 * 2016/05/18   Fujitsu         N.Sugiura       Update          Unit Test#193
 * 2016/08/22   Fujitsu         N.Sugiura       Update          QC#11729
 * 2016/09/21   SRAA            Y.Chen          Update          QC#12060
 * 2016/10/12   Fujitsu         C.Yokoi         Update          QC#4096
 * 2016/10/20   Fujitsu         C.Yokoi         Update          QC#12060
 * 2016/10/21   Fujitsu         C.Yokoi         Update          QC#14982
 * 2016/11/17   Fujitsu         R.Nakamura      Update          QC#16037
 * 2017/02/21   Fujitsu         R.Nakamura      Update          QC#17631
 * 2018/01/22   Fujitsu         Hd.Sugawara     Update          QC#20291(Sol#348)
 * 2018/02/19   Fujitsu         M.Ohno          Update          QC#20297(Sol#379)
 * 2018/07/11   Fujitsu         H.Kumagai       Update          QC#26422
 *</pre>
 */
public class NMAL6720Constant {

    // --------------------------------
    // Common button
    // --------------------------------
    /** Save - Button Name */
    public static final String BTN_01_SAV_NAME = "btn1";

    /** Save - Guard Condition */
    public static final String BTN_01_SAV_GUARD = "CMN_Save";

    /** Save - Label Name */
    public static final String BTN_01_SAV_LABEL = "Save";

    /** Submit - Button Name */
    public static final String BTN_02_SUB_NAME = "btn2";

    /** Submit - Guard Condition */
    public static final String BTN_02_SUB_GUARD = "CMN_Submit";

    /** Submit - Label Name */
    public static final String BTN_02_SUB_LABEL = "Submit";

    /** Apply - Button Name */
    public static final String BTN_03_APL_NAME = "btn3";

    /** Apply - Guard Condition */
    public static final String BTN_03_APL_GUARD = "CMN_Apply";

    /** Apply - Label Name */
    public static final String BTN_03_APL_LABEL = "Apply";

    /** Approve - Button Name */
    public static final String BTN_04_APR_NAME = "btn4";

    /** Approve - Guard Condition */
    public static final String BTN_04_APR_GUARD = "CMN_Approve";

    /** Approve - Label Name */
    public static final String BTN_04_APR_LABEL = "Approve";

    /** Reject - Button Name */
    public static final String BTN_05_REJ_NAME = "btn5";

    /** Reject - Guard Condition */
    public static final String BTN_05_REJ_GUARD = "CMN_Reject";

    /** Reject - Label Name */
    public static final String BTN_05_REJ_LABEL = "Reject";

    /** Download - Button Name */
    public static final String BTN_06_DWL_NAME = "btn6";

    /** Download - Guard Condition */
    public static final String BTN_06_DWL_GUARD = "CMN_Download";

    /** Download - Label Name */
    public static final String BTN_06_DWL_LABEL = "Download";

    /** Delete - Button Name */
    public static final String BTN_07_DEL_NAME = "btn7";

    /** Delete - Guard Condition */
    public static final String BTN_07_DEL_GUARD = "CMN_Delete";

    /** Delete - Label Name */
    public static final String BTN_07_DEL_LABEL = "Delete";

    /** Clear - Button Name */
    public static final String BTN_08_CLE_NAME = "btn8";

    /** Clear - Guard Condition */
    public static final String BTN_08_CLE_GUARD = "CMN_Clear";

    /** Clear - Label Name */
    public static final String BTN_08_CLE_LABEL = "Clear";

    /** Reset - Button Name */
    public static final String BTN_09_RST_NAME = "btn9";

    /** Reset - Guard Condition */
    public static final String BTN_09_RST_GUARD = "CMN_Reset";

    /** Reset - Label Name */
    public static final String BTN_09_RST_LABEL = "Reset";

    /** Return - Button Name */
    public static final String BTN_10_RTR_NAME = "btn10";

    /** Return - Guard Condition */
    public static final String BTN_10_RTR_GUARD = "CMN_Return";

    /** Return - Label Name */
    public static final String BTN_10_RTR_LABEL = "Return";

    // --------------------------------
    // Fields
    // --------------------------------

    /** Button Bill To */
    public static final String BTN_BILL_TO = "OpenWin_BillToSearch";

    /** Button Ship To */
    public static final String BTN_SHIP_TO = "OpenWin_ShipToSearch";

    /** Button Add Account */
    public static final String BTN_ADD_ACCT = "AddAccount";

    /** Button Delete Account */
    public static final String BTN_DEL_ACCT = "DeleteAccount";

    /** Button Add Reference */
    public static final String BTN_ADD_REF = "AddRef";

    /** Button Delete Reference */
    public static final String BTN_DEL_REF = "DeleteRef";

    /** Button Add Contact */
    public static final String BTN_ADD_CONTACT = "CreateContact";

    /** Button Contact Search */
    public static final String BTN_OPEN_WIN_CTAC_SEARCH = "OpenWin_CtacSearch";

    // QC#6559
    /** Button Open Window Upload Contact */
    public static final String BTN_OPEN_WIN_UPLD_CTAC = "OpenWin_UploadContact";

    // Add Start 2017/02/21 QC#17631
    /** Button Open Window Account Search */
    public static final String BTN_OPEN_WIN_ACCT_SRCH = "OpenWin_AcctSrch";

    /** Button Get Account Name */
    public static final String BTN_GET_ACCT_NM = "GetAcctNm";
    // Add End 2017/02/21 QC#17631

    // 2016/04/06 CSA-QC#6633 Add Start
    /** Button Show Contact Details */
    public static final String BTN_OPEN_WIN_SHOW_CTACDETAILS = "ShowContactDetails";

    // 2016/04/06 CSA-QC#6633 Add Start

    /** Button Add Transaction */
    public static final String BTN_ADD_TRAN = "AddTransaction";

    /** Button DeleteTransaction */
    public static final String BTN_DEL_TRAN = "DeleteTransaction";

    /** Button Add Instruction */
    public static final String BTN_ADD_MSG = "AddMsgNote";

    /** Button Delete Instruction */
    public static final String BTN_DEL_MSG = "DeleteMsgNote";

    /** Button Add Certification Required */
    public static final String BTN_ADD_CERT = "AddCertReq";

    /** Button Delete Certification Required */
    public static final String BTN_DEL_CERT = "DeleteCertReq";

    /** Button Add Do Not Sent Technician(s) */
    public static final String BTN_ADD_TECH = "AddTech";

    /** Button Delete Do Not Sent Technician(s) */
    public static final String BTN_DEL_TECH = "DeleteTech";

    /** Button Add Instruction */
    public static final String BTN_ADD_SHIPPING = "AddShipping";

    /** Button Delete Instruction */
    public static final String BTN_DEL_SHIPPING = "DeleteShipping";

    /** Button Show Dtl Acct */
    public static final String BTN_SHOW_DTL_ACCT = "ShowDtlAcct";

    /** Button Bill To for Transsaction */
    public static final String BTN_BILL_TO_TRX = "OpenWin_BillToSearchTrx";

    /** Button Search Technician Name */
    public static final String BTN_SEARCH_PRIM_TECH_NM = "Search_PrimTechNm";

    /** Button Search Technician Name */
    public static final String BTN_SEARCH_REQ_TECH_NM = "Search_ReqTechNm";

    /** Button Attatchment */
    public static final String BTN_ATTACH = "OpenWin_Attachments";

    /** Button Validate Address */
    public static final String BTN_VLD_ADDR = "ValidateAddress";

    // 2018/07/11 Update Start QC#26422
    // 2016/10/21 CSA-QC#14982 Add Start
    /** Button Clear Related Bill To */
    //public static final String BTN_CLEAR_REL_BILL_TO = "Clear_RelatedBillTo";
    public static final String BTN_CLEAR_REL_BILL_TO = "ShowRelatedBillToDetails";
    // 2016/10/21 CSA-QC#14982 Add End
    // 2018/07/11 Update End QC#26422

    // Add Start 2018/01/22 QC#20291(Sol#348)
    /** Button Primary Technician */
    public static final String BTN_OPEN_WIN_PRIM_TECH = "OpenWin_PrimTech";

    /** Button Requested Technician */
    public static final String BTN_OPEN_WIN_REQ_TECH = "OpenWin_ReqTech";
    // Add End 2018/01/22 QC#20291(Sol#348)

    /** Button Get Address */
    public static final String BTN_GET_ADDR = "GetAddress";

    /** xxChkBox_A1 **/
    public static final String CHKBOX_A = "xxChkBox_A1";

    /** xxChkBox_B1 **/
    public static final String CHKBOX_B = "xxChkBox_B1";

    /** xxChkBox_D1 **/
    public static final String CHKBOX_D = "xxChkBox_D1";

    /** xxChkBox_E1 **/
    public static final String CHKBOX_E = "xxChkBox_E1";

    /** xxChkBox_F1 **/
    public static final String CHKBOX_F = "xxChkBox_F1";

    /** xxChkBox_G1 **/
    public static final String CHKBOX_G = "xxChkBox_G1";

    /** xxChkBox_G1 **/
    public static final String CHKBOX_I = "xxChkBox_I1";

    /** Close Button Name */
    public static final String POPUP_CLOSE = "CMN_Close";

    // --------------------------------
    // Screen
    // --------------------------------

    /** Screen ID */
    public static final String SCREEN_ID = "NMAL6720Scrn00";

    /** Business ID */
    public static final String BUSINESS_ID = "NMAL6720";

    // 2016/10/20 CSA-QC#12060 Add Start
    /** Screen ID : NMAL6700 */
    public static final String SCREEN_ID_NMAL6700 = "NMAL6700Scrn00";

    // 2016/10/20 CSA-QC#12060 Add End

    /** Function Code 20 */
    public static final String FUNC_CD_SRCH = "20";

    /** Function Code 40 */
    public static final String FUNC_CD_UPD = "40";

    /** Mode Edit:01 */
    public static final String SCRN_EVENT_EDIT = "01";

    /** Mode New:02 */
    public static final String SCRN_EVENT_NEW = "02";

    /** TAB_ACCOUNT */
    public static final String TAB_ACCOUNT = "Account";

    /** TAB_CLASSIFICATION */
    public static final String TAB_CLASSIFICATIONS = "Classifications";

    /** TAB_CONTACTS */
    public static final String TAB_CONTACTS = "Contacts";

    /** TAB_TRANSACTIONS */
    public static final String TAB_TRANSACTIONS = "Transactions";

    /** TAB_INSTRUCTION */
    public static final String TAB_INSTRUCTIONS = "Instructions";

    /** TAB_SRVATTRB */
    public static final String TAB_SRVATTRB = "SrvAttrb";

    /** TAB_SRVATTRB */
    public static final String TAB_SHIPPING = "Shipping";

    /** EVENT_BILL_TO_SEARCH */
    public static final String EVENT_BILL_TO_SEARCH = "OpenWin_BillToSearch";

    /** EVENT_BILL_TO_SEARCH_TRX */
    public static final String EVENT_BILL_TO_SEARCH_TRX = "OpenWin_BillToSearchTrx";

    /** EVENT_SHIP_TO_SEARCH */
    public static final String EVENT_SHIP_TO_SEARCH = "OpenWin_ShipToSearch";

    /** EVENT_TECH */
    public static final String EVENT_TECH = "OpenWin_Tech";

    /** EVENT_REQ_TECH */
    public static final String EVENT_REQ_TECH = "OpenWin_ReqTech";

    /** EVENT_PRIM_TECH */
    public static final String EVENT_PRIM_TECH = "OpenWin_PrimTech";

    /** EVENT_AP */
    public static final String EVENT_AP = "OpenWin_AccsPmit";

    // QC#4505
    /** EVENT_OPEN_WIN_CITY */
    public static final String EVENT_OPEN_WIN_CITY = "OpenWin_City";

    /** EVENT_OPEN_WIN_ST */
    public static final String EVENT_OPEN_WIN_ST = "OpenWin_St";

    /** EVENT_OPEN_WIN_POST */
    public static final String EVENT_OPEN_WIN_POST = "OpenWin_Post";

    /** EVENT_OPEN_WIN_CNTY */
    public static final String EVENT_OPEN_WIN_CNTY = "OpenWin_Cnty";

    /** Active **/
    public static final String ACTIVE = "Active";

    // --------------------------------
    // Message
    // --------------------------------
    /**
     * Multiple check boxes cannot be selected. Check either one of
     * the boxes only.
     */
    public static final String NMAM8195E = "NMAM8195E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Please check at least 1 checkbox. */
    public static final String NZZM0011E = "NZZM0011E";

    /** Details cannot be added because the number will exceed [@]. */
    public static final String NMAM0050E = "NMAM0050E";

    /** [@] is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** Registered data cannot be deleted. */
    public static final String NMAM8230E = "NMAM8230E";

    /** Unable to add @ entered because it already exists in the list. */
    public static final String NMAM8247E = "NMAM8247E";

    /** [@] is not registered. */
    public static final String NMAM0011E = "NMAM0011E";

    /** The effective periods are overlapped. Please correct it. */
    public static final String NMAM8113E = "NMAM8113E";

    /** The chronological sequence is wrong. */
    public static final String NMAM8115E = "NMAM8115E";

    /** A past date cannot be entered into the "Date Effective From". */
    public static final String NMAM0185E = "NMAM0185E";

    /** The effective date of the entered data is incorrect. */
    public static final String NMAM0803E = "NMAM0803E";

    /** If [@] is set, [@] must be entered. */
    public static final String NMAM0849E = "NMAM0849E";

    /** [@] should be filled. */
    public static final String ZZZM9024E = "ZZZM9024E";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is not selected. */
    public static final String NMAM0014E = "NMAM0014E";

    /** "Start Date" cannot be later than "Sales Date". */
    public static final String NMAM0303E = "NMAM0303E";

    /** You can have only one Primary Contact. */
    public static final String NMAM8343E = "NMAM8343E";

    /** You can not delete all the lines. */
    public static final String NMAM8346E = "NMAM8346E";

    /**
     * Phone number format is incorrect. Minimum length is 7digit,
     * Maximum length is 20 digit.
     */
    public static final String NMAM8348E = "NMAM8348E";

    // 2016/08/22 QC#11729 Add Start
    /**
     * If you change status to Inactive immediately, Please set end
     * date as past date.
     */
    public static final String NMAM8642E = "NMAM8642E";

    // 2016/08/22 QC#11729 Add End

    // 2016/10/12 CSA-QC#4096 Add Start
    /** @ is required when @ are selected. */
    public static final String NMAM8385E = "NMAM8385E";

    // 2018/07/11 Update Start QC#26422
    /** The entered [@] does not exist in [@]. */
    public static final String NMAM0163E = "NMAM0163E";
    // 2018/07/11 Update End QC#26422

    /** The message parameter : Address */
    public static final String MSG_PARAM_ADDRESS = "Address";

    /** The message parameter : GeoCode search popup */
    public static final String MSG_PARAM_GEOCODE_SRCH = "GeoCode search";

    // 2016/10/12 CSA-QC#4096 Add End

    /** Transaction Type */
    public static final String MSG_TRX_TP = "Transaction Type";

    /** Account Number */
    public static final String MSG_ACCT_NUM = "Account Number";

    /** Related Account Number */
    public static final String MSG_RELN_ACCT = "Related Acct#";

    /** Ship To */
    public static final String MSG_SHIP_TO = "Ship To";

    /** Bill To */
    public static final String MSG_BILL_TO = "Bill To";

    /** Location Number */
    public static final String MSG_LOC_NUM = "Location Number";

    /** Bill To CheckBox */
    public static final String MSG_BILL_TO_CHECK = "Bill To CheckBox";

    /** Ship To CheckBox */
    public static final String MSG_SHIP_TO_CHECK = "Ship To CheckBox";

    /** Start Date */
    public static final String MSG_START_DT = "Start Date";

    /** End Date */
    public static final String MSG_END_DT = "End Date";

    // 2018/07/11 Update Start QC#26422
    /** Bill To Code */
    public static final String MSG_BILL_TO_CD = "Bill To Code";

    /** Master */
    public static final String MSG_MST = "master";
    // 2018/07/11 Update End QC#26422

    /** MAX_ROW_NUM */
    public static final int MAX_ROW_NUM = 2000;

    /** Warning Kind */
    public static final String MESSAGE_KIND_WARN = "W";

    /** Error Kind */
    public static final String MESSAGE_KIND_ERR = "E";

    /** Max Date */
    public static final String MAX_DT = "99991231";

    // --------------------------------
    // Privilege
    // --------------------------------

    /** Location General Inquiry */
    // public static final String FUNC_ID_LOCATION_GENERAL_INQUIRY =
    // "NMAL6720T010";
    // Add Start 2016/11/17 QC#16037
    /** Location General Update */
     public static final String FUNC_ID_LOCATION_GENERAL_UPDATE = "NMAL6720T020";
    // Add End 2016/11/17 QC#16037
    /** Location General Inquiry */
     public static final String FUNC_ID_ACCOUNTS_INQUIRY = "NMAL6720T030";
    /** Location General Update */
    public static final String FUNC_ID_ACCOUNTS_UPDATE = "NMAL6720T040";

    /** Location General Inquiry */
     public static final String FUNC_ID_CLASSIFIFATIONS_INQUIRY = "NMAL6720T050";
    /** Location General Update */
    public static final String FUNC_ID_CLASSIFICATIONS_UPDATE = "NMAL6720T060";

    /** Location General Inquiry */
     public static final String FUNC_ID_CONTACTS_INQUIRY = "NMAL6720T070";
    /** Location General Update */
    public static final String FUNC_ID_CONTACTS_UPDATE = "NMAL6720T080";

    /** Location General Inquiry */
     public static final String FUNC_ID_TRANSACTIONS_INQUIRY = "NMAL6720T090";
    /** Location General Update */
    public static final String FUNC_ID_TRANSACTIONS_UPDATE = "NMAL6720T100";

    /** Location General Inquiry */
     public static final String FUNC_ID_INSTRUCTIONS_INQUIRY = "NMAL6720T110";
    /** Location General Update */
    public static final String FUNC_ID_INSTRUCTIONS_UPDATE = "NMAL6720T120";

    /** Service Attribute Update */
    public static final String FUNC_ID_SRVATTRB_UPDATE = "NMAL6720T140";

    // 2016/05/18 Unit Test#193 Add Start
    /** DPL Update */
    public static final String FUNC_ID_DPL_UPDATE = "NMAL6720T130";

    // 2016/05/18 Unit Test#193 Add End
    // Add Start 2018/01/22 QC#20291(Sol#348)
    /** Location General Prospect Update */
    public static final String FUNC_ID_LOCATION_GENERAL_PROS_UPDATE = "NMAL6720T150";

    /** Account Prospect Update */
    public static final String FUNC_ID_ACCOUNTS_PROS_UPDATE = "NMAL6720T160";

    /** Classifications Prospect Update */
    public static final String FUNC_ID_CLASSIFICATIONS_PROS_UPDATE = "NMAL6720T170";

    /** Contacts Prospect Update */
    public static final String FUNC_ID_CONTACTS_PROS_UPDATE = "NMAL6720T180";

    /** Transactions Prospect Update */
    public static final String FUNC_ID_TRANSACTIONS_PROS_UPDATE = "NMAL6720T190";

    /** Instructions Prospect Update */
    public static final String FUNC_ID_INSTRUCTIONS_PROS_UPDATE = "NMAL6720T200";

    /** DPL Prospect Update */
    public static final String FUNC_ID_DPL_PROS_UPDATE = "NMAL6720T210";

    /** Service Attribute Prospect Update */
    public static final String FUNC_ID_SRVATTRB_PROS_UPDATE = "NMAL6720T220";
    // Add End 2018/01/22 QC#20291(Sol#348)

    // 2018/02/19 QC#20297(Sol#379) add start
    /** Shipping Inquiry */
    public static final String FUNC_ID_SHIPPING_INQUIRY = "NMAL6720T230";

    /** Shipping Update */
    public static final String FUNC_ID_SHIPPING_UPDATE = "NMAL6720T240";

    /** Shipping Prospect Update */
    public static final String FUNC_ID_SHIPPING_PROS_UPDATE = "NMAL6720T250";
    // 2018/02/19 QC#20297(Sol#379) add end

    /** Slash */
    public static final String SLASH = "/";

    /** Period */
    public static final String PERIOD = "\\.";

    /** Hyphen */
    public static final String HYPHEN = "-";

    // QC#6559
    /** CSV Upload ID - Contact */
    public static final String CSV_UPD_ID_CTAC = "NMA2680001";

    // QC#12060
    /** Prospect to Customer Change Status: Pending */
    public static final String PROS_TO_CUST_CHNG_STS_PENDING = "PE";

    // 2018/07/31 Update Start QC#27488
    /** Address Get Result Code: Success */
    public static final String SUCCESS = "0";

    /** Address Get Result Code: Original Postal Code With County Error */
    public static final String ORIG_POST_WITH_CNTY_ERR = "1";

    /** Address Get Result Code: Original Postal Code Without County Error */
    public static final String ORIG_POST_WITHOUT_CNTY_ERR = "2";

    /** Address Get Result Code: Five Digit Postal Code With County Error */
    public static final String FIVE_DIGIT_POST_WITH_CNTY_ERR = "3";

    /** Address Get Result Code: Five Digit Postal Code Without County Error */
    public static final String FIVE_DIGIT_POST_WITHOUT_CNTY_ERR = "4";
    // 2018/07/31 Update End QC#27488
}
