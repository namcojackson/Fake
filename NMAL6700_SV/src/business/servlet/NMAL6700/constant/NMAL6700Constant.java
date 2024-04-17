/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/20   Fujitsu         R.Nakamura      Update          QC#17631
 * 2017/07/06   Hitachi         J.Kim           Update          QC#16966
 * 2017/09/11   Hitachi         J.Kim           Update          QC#20359
 * 2018/01/22   Fujitsu         Hd.Sugawara     Update          QC#20291(Sol#348)
 * 2018/02/14   Fujitsu         M.Ohno          Update          QC#20297(Sol#379)
 * 2018/07/30   Fujitsu         S.Ohki          Update          QC#27222
 * 2018/08/21   Fujitsu         S.Ohki          Update          QC#27222-2
 *</pre>
 */
public class NMAL6700Constant {

    // [0]:Button Name [1]:Event Name [2]:Button Lavel
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
    public static final String[] DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_BLANK7 = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Button Add Location */
    public static final String[] BTN_ADD_LOCATION = {"AddLocation", "AddLocation" };

    /** Button Filter Location */
    public static final String[] BTN_OPENWIN_ADDR_SRCH = {"OpenWin_AddressSearch", "OpenWin_AddressSearch" };

    /** Button Open Window Upload Location */
    public static final String[] BTN_OPENWIN_UPLD_LOC = {"OpenWin_UploadLocation", "OpenWin_UploadLocation" };

    /** Button Download Location Template */
    public static final String[] BTN_DWND_LOC_TMPL = {"DownloadLocationTemplate", "DownloadLocationTemplate" };

    /** Button Add Location */
    public static final String[] BTN_SHOW_ACCT_FROM_HRCH = {"ShowAcctFromHierarchy", "ShowAcctFromHierarchy" };

    /** Button Add Relationship */
    public static final String[] BTN_ADD_RELNSHIP = {"AddRelnship", "AddRelnship" };

    /** Button Delete Relationship */
    public static final String[] BTN_DEL_RELNSHIP = {"DeleteRelnship", "DeleteRelnship" };

    // START 2017/07/06 J.Kim [QC#16966,ADD]
    /** Button Filter Relationship */
    public static final String[] BTN_FILTER_RELNSHIP = {"OpenWin_RelationshipFilter", "OpenWin_RelationshipFilter" };
    // END 2017/07/06 J.Kim [QC#16966,ADD]

    /** Button Show Account */
    public static final String[] BTN_SHOW_ACCT = {"ShowAcct", "ShowAcct" };

    /** Button OpenWin_AcctSrch */
    public static final String[] BTN_OPENWIN_ACCT_SRCH = {"OpenWin_AcctSrch", "OpenWin_AcctSrch" };

    /** Button Get Account Name */
    public static final String[] BTN_GET_DS_ACCT_NM = {"GetDsAcctNm", "GetDsAcctNm" };
    /** Button Add Contact */
    public static final String[] BTN_ADD_CONTACT = {"AddContact", "AddContact" };

    /** Button Add Account Group */
    public static final String[] BTN_ADD_GROUP = {"AddGrpAsg", "AddGrpAsg" };

    /** Button Delete Account Group */
    public static final String[] BTN_DEL_GROUP = {"DeleteGrpAsg", "DeleteGrpAsg" };

    /** Button Add Certificatio Request */
    public static final String[] BTN_ADD_SER_REQ = {"AddCertificationReq", "AddCertificationReq" };

    /** Button Delete Certificatio Request */
    public static final String[] BTN_DEL_SER_REQ = {"DeleteCertificationReq", "DeleteCertificationReq" };

    /** Button Access Permit */
    public static final String BTN_OPEN_WIN_ACCS_PMIT = "OpenWin_AccsPmit";

    /** Button Add Transaction */
    public static final String[] BTN_ADD_TRANSACTION = {"AddTransactionRule", "AddTransactionRule" };

    /** Button Delete Transaction */
    public static final String[] BTN_DEL_TRANSACTION = {"DeleteTransactionRule", "DeleteTransactionRule"};

    /** Button Add Special Handling */
    public static final String[] BTN_ADD_SPECIAL_HANDLONG = {"AddSpecialHandling", "AddSpecialHandling"};

    /** Button Delete Special Handling */
    public static final String[] BTN_DEL_SPECIAL_HANDLONG = {"DeleteSpecialHandling", "DeleteSpecialHandling"};

    /** Button COA */
    public static final String BTN_OPEN_COA = "OpenWin_Coa";

    /** Button Get Coa Cc Name */
    public static final String[] BTN_GET_COA_CC_NM = {"GetCoaCcNm", "GetCoaCcNm" };

    /** Button Template */
    public static final String BTN_OPEN_WIN_TEMP = "OpenWin_Template";

    /** Button Clt Cust Type */
    public static final String BTN_OPEN_WIN_CLT_CUST = "OpenWin_CltCustTp";

    /** Get Clt Cust Type */
    public static final String[] BTN_GET_CLT_CUST = {"GetCltCustTpNm", "GetCltCustTpNm"};

    /** Button Clt Ptfo */
    public static final String BTN_OPEN_WIN_CLT_PTFO = "OpenWin_CltPtfo";

    /** Get Clt Ptfo */
    public static final String[] BTN_GET_CLT_PTFO = {"GetCltPtfoNm", "GetCltPtfoNm"};

    /** Button Resource */
    public static final String[] BTN_OPEN_WIN_RESRC = {"OpenWin_Resrc", "OpenWin_Resrc"};

    /** Button Contact */
    public static final String[] BTN_OPEN_WIN_CTAC_PSN = {"OpenWin_CtacPsn", "OpenWin_CtacPsn"};

    /** Button Bill To */
    public static final String[] BTN_OPEN_WIN_BILL_TO = {"OpenWin_BillTo", "OpenWin_BillTo"};

    /** Button Ship To */
    public static final String[] BTN_OPEN_WIN_SHIP_TO = {"OpenWin_ShipTo", "OpenWin_ShipTo"};

    /** Button Account Group */
    public static final String[] BTN_OPEN_WIN_ACCT_GP = {"OpenWin_AcctGrp", "OpenWin_AcctGrp"};

    /** Button Add Template */
    public static final String[] BTN_ADD_TEMPLATE = {"ApplyTemplate", "ApplyTemplate" };

    /** Button Add Invoice Rule */
    public static final String[] BTN_ADD_INV_RULE = {"AddInvRule", "AddInvRule" };

    /** Button Delete Invoice Rule */
    public static final String[] BTN_DEL_INV_RULE = {"DeleteInvRule", "DeleteInvRule" };

    /** Button Add Invoice Rule */
    public static final String[] BTN_ADD_ATTRIBUTE = {"AddAttribute", "AddAttribute" };

    /** Button Delete Invoice Rule */
    public static final String[] BTN_DEL_ATTRIBUTE = {"DeleteAttribute", "DeleteAttribute" };

    /** Button Delete Invoice Rule */
    public static final String[] BTN_DEL_MSG_NOTE = {"DeleteMsgNote", "DeleteMsgNote" };

    /** Button Add Invoice Rule */
    public static final String[] BTN_ADD_MSG_NOTE = {"AddMsgNote", "AddMsgNote" };
    
    /** Button Delete Invoice Rule */
    public static final String[] BTN_DEL_SHIPPING = {"DeleteShipping", "DeleteShipping" };

    /** Button Add Invoice Rule */
    public static final String[] BTN_ADD_SHIPPING = {"AddShipping", "AddShipping" };

    /** Button GetCoaChNm */
    public static final String[] BTN_GET_COA_CH_NM = {"GetCoaChNm", "GetCoaChNm" };

    /** Button GetInterCompanyNm */
    public static final String[] BTN_GET_INTER_COMPANY_NM = {"GetInterCompanyNm", "GetInterCompanyNm" };

    /** Button Contact Search */
    public static final String BTN_OPEN_WIN_CTAC_SEARCH = "OpenWin_CtacSearch";

    /** Button Open Window Upload Contact */
    public static final String BTN_OPEN_WIN_UPLD_CTAC = "OpenWin_UploadContact";

    /** Screen ID */
    public static final String SCREEN_ID = "NMAL6700Scrn00";

    /** Business ID */
    public static final String BUSINESS_ID = "NMAL6700";

    /** TAB_ADDRESSES */
    public static final String TAB_ADDRESSES = "Addresses";

    /** TAB_HIERARCHY */
    public static final String TAB_HIERARCHY = "Hierarchy";

    /** TAB_RELNSHIPS */
    public static final String TAB_RELNSHIPS = "Relnships";

    /** TAB_CONTACTS */
    public static final String TAB_CONTACTS = "Contacts";

    /** TAB_MARKETING */
    public static final String TAB_MARKETING = "Marketing";

    /** TAB_TRANSACTIONS */
    public static final String TAB_TRANSACTIONS = "Transactions";

    /** TAB_CR_CLT */
    public static final String TAB_CR_CLT = "CrClt";

    /** TAB_INV_BLLG */
    public static final String TAB_INV_BLLG = "InvBllg";

    /** TAB_BANK_ACCT */
    public static final String TAB_BANK_ACCT = "BankAcct";

    /** TAB_MSG_NOTE */
    public static final String TAB_MSG_NOTE = "MsgNote";

    // 2018/02/14 QC#20297(Sol#379) add start
    /** TAB_SHIPPING */
    public static final String TAB_SHIPPING = "Shipping";
    // 2018/02/14 QC#20297(Sol#379) add end

    // Add Start 2018/07/30 QC#27222
    /** TAB_TAXING */
    public static final String TAB_TAXING = "Taxing";
    // Add End 2018/07/30 QC#27222

    /** [@] does not exit. */
    public static final String NMAM8111E = "NMAM8111E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    /** The chronological sequence is wrong. */
    public static final String NMAM8115E = "NMAM8115E";

    /** Registered data cannot be deleted. */
    public static final String NMAM8230E = "NMAM8230E";

    /** Enter a today or future date on [Start Date]. */
    public static final String NMAM8250E = "NMAM8250E";

    /** Enter the day before or today or future date on [End Date]. */
    public static final String NMAM8251E = "NMAM8251E";

    /** You cannot set "Y" on "Reciprocal Flag" if "PARENT ACCOUNT" is selected. */
    public static final String NMAM8252E = "NMAM8252E";

    /** [Start Date] or [End Date] is duplicated. */
    public static final String NMAM8253E = "NMAM8253E";

    /** Related Account # [@] is not a Lease company. */
    public static final String NMAM8254E = "NMAM8254E";

    /** The selected record has already been registered and cannot be deleted. */
    public static final String NMAM8255E = "NMAM8255E";

    /** The selected record has already been registered and cannot be deleted. */
    public static final String NMZM0059E = "NMZM0059E";

    /** The same Account Number cannot be registered.. */
    public static final String NMAM0294E = "NMAM0294E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Please check at least 1 checkbox. */
    public static final String NZZM0011E = "NZZM0011E";

    /** @ is duplicated */
    public static final String NMAM0072E = "NMAM0072E";

    /** If [@] is set, [@] must be entered. */
    public static final String NMAM0849E = "NMAM0849E";

    /** [@] must be greater than or equal to [@]. */
    public static final String NMAM8441E = "NMAM8441E";

    /** The specified format is incorrect. It must be @. */
    public static final String NMAM8075E = "NMAM8075E";

    /** [@] field has too many digits entered. */
    public static final String ZZM9001E = "ZZM9001E";

    /** @ is duplicated */
    public static final String MSG_DUP = "Relationship Type and Effective Date";

    /**
     * Multiple check boxes cannot be selected. Check either one of
     * the boxes only.
     */
    public static final String NMAM8195E = "NMAM8195E";

    /** ADD_CONTACT_EVENT */
    public static final String ADD_CONTACT_EVENT = "AddContact";

    /** SHOW_CONTACT_EVENT */
    public static final String SHOW_CONTACT_EVENT = "ShowContact";

    /** Account General Inquiry */
    public static final String FUNC_ID_ACCT_INQUIRY = "NMAL6700T010";

    /** Account General Update */
    public static final String FUNC_ID_ACCT_UPDATE = "NMAL6700T020";

    /** Address Inquiry */
    public static final String FUNC_ID_ADDR_INQUIRY = "NMAL6700T030";

    /** Address Update */
    public static final String FUNC_ID_ADDR_UPDATE = "NMAL6700T040";

    /** Hierarchy Inquiry */
    public static final String FUNC_ID_HIERARCHY_INQUIRY = "NMAL6700T050";

    /** Relationship Inquiry */
    public static final String FUNC_ID_RELATIONSHIP_INQUIRY = "NMAL6700T060";

    /** Relationship Update */
    public static final String FUNC_ID_RELATIONSHIP_UPDATE = "NMAL6700T070";

    /** Relationship Inquiry */
    public static final String FUNC_ID_CONTACT_INQUIRY = "NMAL6700T080";

    /** Relationship Update */
    public static final String FUNC_ID_CONTACT_UPDATE = "NMAL6700T090";

    /** Relationship Inquiry */
    public static final String FUNC_ID_MARKETING_INQUIRY = "NMAL6700T100";

    /** Relationship Update */
    public static final String FUNC_ID_MARKETING_UPDATE = "NMAL6700T110";

    /** Relationship Inquiry */
    public static final String FUNC_ID_TRANSACTION_INQUIRY = "NMAL6700T120";

    /** Relationship Update */
    public static final String FUNC_ID_TRANSACTION_UPDATE = "NMAL6700T130";

    /** Relationship Inquiry */
    public static final String FUNC_ID_COLLECTION_INQUIRY = "NMAL6700T140";

    /** Relationship Update */
    public static final String FUNC_ID_COLLECTION_UPDATE = "NMAL6700T150";

    /** Relationship Inquiry */
    public static final String FUNC_ID_INV_BLLG_INQUIRY = "NMAL6700T160";

    /** Relationship Update */
    public static final String FUNC_ID_INV_BLLG_UPDATE = "NMAL6700T170";

    /** Relationship Inquiry */
    public static final String FUNC_ID_BANK_INQUIRY = "NMAL6700T180";

    /** Relationship Update */
    public static final String FUNC_ID_BANK_UPDATE = "NMAL6700T190";

    /** Relationship Inquiry */
    public static final String FUNC_ID_INSTRUCTION_INQUIRY = "NMAL6700T200";

    /** Relationship Update */
    public static final String FUNC_ID_INSTRUCTION_UPDATE = "NMAL6700T210";

    // Add Start 2018/01/22 QC#20291(Sol#348)
    /** Account General Prospect Update */
    public static final String FUNC_ID_ACCT_PROS_UPDATE = "NMAL6700T220";

    /** Address Prospect Update */
    public static final String FUNC_ID_ADDR_PROS_UPDATE = "NMAL6700T230";

    /** Relationship Prospect Update */
    public static final String FUNC_ID_RELATIONSHIP_PROS_UPDATE = "NMAL6700T240";

    /** Contacts Prospect Update */
    public static final String FUNC_ID_CONTACT_PROS_UPDATE = "NMAL6700T250";

    /** Marketing Prospect Update */
    public static final String FUNC_ID_MARKETING_PROS_UPDATE = "NMAL6700T260";

    /** Transactions Prospect Update */
    public static final String FUNC_ID_TRANSACTION_PROS_UPDATE = "NMAL6700T270";

    /** Collections Prospect Update */
    public static final String FUNC_ID_COLLECTION_PROS_UPDATE = "NMAL6700T280";

    /** Invoice/Billing Prospect Update */
    public static final String FUNC_ID_INV_BLLG_PROS_UPDATE = "NMAL6700T290";

    /** Bank Accounts Prospect Update */
    public static final String FUNC_ID_BANK_PROS_UPDATE = "NMAL6700T300";

    /** Instructions Prospect Update */
    public static final String FUNC_ID_INSTRUCTION_PROS_UPDATE = "NMAL6700T310";
    // Add End 2018/01/22 QC#20291(Sol#348)

    // 2018/02/14 QC#20297(Sol#379) add start
    /** Shipping Inquiry */
    public static final String FUNC_ID_SHIPPING_INQUIRY = "NMAL6700T320";

    /** Shipping Update */
    public static final String FUNC_ID_SHIPPING_UPDATE = "NMAL6700T330";

    /** Shipping Prospect Update */
    public static final String FUNC_ID_SHIPPING_PROS_UPDATE = "NMAL6700T340";
    // 2018/02/14 QC#20297(Sol#379) add end

    // Add Start 2018/07/30 QC#27222
    /** Taxing Inquiry */
    public static final String FUNC_ID_TAXING_INQUIRY = "NMAL6700T350";

    /** Taxing Update */
    public static final String FUNC_ID_TAXING_UPDATE = "NMAL6700T360";

    /** Taxing Prospect Update */
    public static final String FUNC_ID_TAXING_PROS_UPDATE = "NMAL6700T370";
    // Add End 2018/07/30 QC#27222

    // Add Start 2018/08/21 QC#27222-2
    /** DI Check Item Inquiry */
    public static final String FUNC_ID_DI_CHECK_ITEM_INQUIRY = "NMAL6700T380";

    /** DI Check Item Update */
    public static final String FUNC_ID_DI_CHECK_ITEM_UPDATE = "NMAL6700T390";

    /** DI Check Item Prospect Update */
    public static final String FUNC_ID_DI_CHECK_ITEM_PROS_UPDATE = "NMAL6700T400";
    // Add End 2018/08/21 QC#27222-2

    /** Max Effective Thru Date */
    public static final String MAX_EFF_THRU_DT = "99991231";

    /*** BUTTON_NAME : AddGrpAsg */
    public static final String BUTTON_NAME_ADD_GRP_ASG = "AddGrpAsg";

    /** BUTTON_NAME : DeleteGrpAsg */
    public static final String BUTTON_NAME_DELETE_GRP_ASG = "DeleteGrpAsg";

    /** BUTTON_NAME : AddCertificationReq */
    public static final String BUTTON_NAME_ADD_CERTIFICATION_REQ = "AddCertificationReq";

    /** BUTTON_NAME : DeleteCertificationReq */
    public static final String BUTTON_NAME_DELETE_CERTIFICATION_REQ = "DeleteCertificationReq";

    /** FORMAT */
    public static final String FORMAT_TM = "^([0-1][0-9]|2[0-3]):?([0-5][0-9])$";

    /*** BUTTON_NAME : AddGrpAsg */
    public static final String BUTTON_NAME_ADD_MSG_NOTE = "AddMsgNote";

    /** BUTTON_NAME : DeleteGrpAsg */
    public static final String BUTTON_NAME_DELETE_MSG_NOTE = "DeleteMsgNote";

    /*** BUTTON_NAME : InsertRowCarrierAcct */
    public static final String BUTTON_NAME_INSERT_BANK_ACCT = "InsertRowCarrierAcct";

    /** BUTTON_NAME : DeleteRowCarrierAcct */
    public static final String BUTTON_NAME_DELETE_BANK_ACCT = "DeleteRowCarrierAcct";

    /*** BUTTON_NAME : AddShipping */
    public static final String BUTTON_NAME_ADD_SHIPPING = "AddShipping";

    /** BUTTON_NAME : DeleteShipping */
    public static final String BUTTON_NAME_DELETE_SHIPPING = "DeleteShipping";


    /** xxChkBox_WD **/
    public static final String CHKBOX_WD = "xxChkBox_WD";

    /** APP_FUNC_ID_HEADER */
    public static final String APP_FUNC_ID_HEADER = "NMAL6700001";

    /** APP_FUNC_ID_TRANSACTION */
    public static final String APP_FUNC_ID_TRANSACTION = "NMAL6700002";

    /** Button Attatchment */
    public static final String BTN_ATTACH = "OpenWin_Attachments";

    /** NMAL6760 Status Code: 01 */
    public static final String STATUS_CD_ACTIVE = "01";

    /** CSV Upload ID - Location */
    public static final String CSV_UPD_ID_LOC = "NMA2670001";

    /** CSV Upload ID - Contact */
    public static final String CSV_UPD_ID_CTAC = "NMA2680001";

    /** Default Invoice Group Number */
    public static final String DEF_INV_GRP_NUM = "1";

    /** Char: Comma */
    public static final String CHAR_COMMA = ",";

   // START 2017/07/06 J.Kim [QC#16966,ADD]
    /* ------ Parameter Number ------ */
    /** DS_ACCT_RELN_TP_CD */
    public static final int INPUT_DS_ACCT_RELN_TP_CD = 0;

    /** DS_ACCT_NUM  */
    public static final int INPUT_DS_ACCT_NUM = 1;

    /** DS_ACCT_NM */
    public static final int INPUT_DS_ACCT_NM = 2;

    /** XX_CHK_BOX_CB */
    public static final int INPUT_XX_CHK_BOX_CB = 3;

    /** XX_CHK_BOX_CS */
    public static final int INPUT_XX_CHK_BOX_CS = 4;

    /** XX_CHK_BOX_CR */
    public static final int INPUT_XX_CHK_BOX_CR = 5;

    /** EFF_FROM_DT From */
    public static final int INPUT_EFF_FROM_DT_F = 6;

    /** EFF_FROM_DT To */
    public static final int INPUT_EFF_FROM_DT_T = 7;

    /** EFF_THRU_DT From */
    public static final int INPUT_EFF_THRU_DT_F = 8;

    /** EFF_THRU_DT To */
    public static final int INPUT_EFF_THRU_DT_T = 9;

    /** LENGTH */
    public static final int LENGTH = 10;
    // END 2017/07/06 J.Kim [QC#16966,ADD]

    // START 2017/09/11 J.Kim [QC#20359,ADD]
    /** Display Hierarchy Accounts Code: 02 */
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";

    /** Display Hierarchy Accounts Code: 03 */
    public static final String DISP_HRCH_ACCT_CD_SHIP = "03";

    /** Display Related Accounts Code: 03 (Bill To's Only) */
    public static final String DISP_RELN_ACCT_CD_BILL = "03";

    /** Display Related Accounts Code: 04 (Ship To's Only) */
    public static final String DISP_RELN_ACCT_CD_SHIP = "04";
    // END 2017/09/11 J.Kim [QC#20359,ADD]
}
