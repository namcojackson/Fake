/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6700.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/14   Fujitsu         C.Yokoi         Update          QC#15488
 * 2017/12/11   Fujitsu         Hd.Sugawara     Update          QC#20357
 * 2018/01/22   Fujitsu         Hd.Sugawara     Update          QC#20291(Sol#348)
 * 2018/01/25   Fujitsu         H.Ikeda         Update          QC#22095
 * 2018/05/07   Fujitsu         H.Nagashima     Update          QC#23604
 * 2018/05/16   Fujitsu         Hd.Sugawara     Update          QC#26041
 * 2018/08/30   Fujitsu         W.Honda         Update          QC#27869
 * 2018/09/28   Fujitsu         Mz.Takahashi    Update          QC#28165
 * 2018/10/09   Fujitsu         Hd.Sugawara     Update          QC#27598
 * 2018/12/10   Fujitsu         M.Ishii         Update          QC#29315
 * 2019/01/07   Fujitsu         Hd.Sugawara     Update          QC#29749
 * 2019/05/23   Fujitsu         Hd.Sugawara     Update          QC#50101
 * 2020/07/22   CITS            K.Ogino         Update          QC#57316
 * 2023/01/20   Hitachi         S.Fujita        Update          QC#61011
 * 2024/03/12   Hitachi         T.Nagae         Update          QC#63552
 *</pre>
 */
public class NMAL6700Constant {

    /** @ is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** If [@], [@] cannot be set. */
    public static final String NMAM0076E = "NMAM0076E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** @ cannot be selected. */
    public static final String NMAM0183E = "NMAM0183E";

    /** It failed to delete [@]. */
    public static final String NMAM0208E = "NMAM0208E";

    /** The same [@] as @ cannot be registered. */
    public static final String NMAM0833E = "NMAM0833E";

    /** [@] does not exit. */
    public static final String NMAM8111E = "NMAM8111E";

    /** When @ contains @, @ is required. */
    public static final String NMAM8177E = "NMAM8177E";

    /** When @ is entered, @ must be entered. */
    public static final String NMAM8178E = "NMAM8178E";

    /** Please select @ when @ is entered. */
    public static final String NMAM8179E = "NMAM8179E";

    /** When @ is @, @ is required. */
    public static final String NMAM8180E = "NMAM8180E";

    /** Search results exceeded [@]. Only showing first @ records. */
    public static final String NMAM8181W = "NMAM8181W";

    /** If [@] is [@], [@] is mandatory. */
    public static final String NMAM8139W = "NMAM8139W";

    /** If @ has been changed to @, @ must be entered. */
    public static final String NMAM8183E = "NMAM8183E";

    /** A past date cannot be entered into [@]. */
    public static final String NMAM8200E = "NMAM8200E";

    /** Primary can not be multiple check. Check either one of the boxes only. */
    public static final String NMAM8287E = "NMAM8287E";

    /** Please select at least 1 checkbox. */
    public static final String NMAM0835E = "NMAM0835E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** [@] field is mandatory. */
    public static final String NMAM0836E = "NMAM0836E";

    /** . */
    public static final String NMZM0059E = "NMZM0059E";

    /**
     * This customer has active MIF or contracts associated. Are you
     * sure you want to inactivate this customer? If OK, please click
     * the "@" button again.
     */
    public static final String NMAM8184W = "NMAM8184W";

    /**
     * [@] already exists in [@].If OK, please click the "@" button
     * again.
     */
    public static final String NMAM8185W = "NMAM8185W";

    /** The data @ does not exist in the master. */
    public static final String NMAM8186E = "NMAM8186E";

    /**
     * @ cannot be added because it is exceeding the maximum number of
     * row [@]
     */
    public static final String NMAM8187E = "NMAM8187E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** A past date cannot be entered into the "Date Effective From". */
    public static final String NMAM0185E = "NMAM0185E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** [@] is invalid value */
    public static final String NMAM8121E = "NMAM8121E";

    /** You can have only one Primary Contact . */
    public static final String NMAM8343E = "NMAM8343E";

    /** Please select the same value for @ in the same "Invoice Group#". */
    public static final String NMAM0293E = "NMAM0293E";

    /** In case Bill Vehicle is @, please select Consolidated for the Bill Type. */
    public static final String NMAM0292E = "NMAM0292E";

    /** Related Account # [@] is not a Lease company. */
    public static final String NMAM8254E = "NMAM8254E";

    /** [@] is not selected. */
    public static final String NMAM0014E = "NMAM0014E";

    /** This account number is nested in the account relationship. */
    public static final String NMAM0295E = "NMAM0295E";

    /** The same Account Number cannot be registered. */
    public static final String NMAM0294E = "NMAM0294E";

    /** Selected Account status is inactive. Please select active account.  */
    public static final String NMAM8362E = "NMAM8362E";

    /** Primary Location should be active please review Primary Location.  */
    public static final String NMAM8363E = "NMAM8363E";

    /** The chronological sequence is wrong. */
    public static final String NMAM8115E = "NMAM8115E";

    /** You cannot set "Y" on "Reciprocal Flag" if "PARENT ACCOUNT" is selected. */
    public static final String NMAM8252E = "NMAM8252E";

    /** [Start Date] or [End Date] is duplicated. */
    public static final String NMAM8253E = "NMAM8253E";

    /** The selected record has already been registered and cannot be deleted. */
    public static final String NMAM8255E = "NMAM8255E";

    /** Enter the day before or today or future date on [End Date]. */
    public static final String NMAM8251E = "NMAM8251E";

    /** Enter a today or future date on [Start Date]. */
    public static final String NMAM8250E = "NMAM8250E";

    /**
     * Multiple check boxes cannot be selected. Check either one of
     * the boxes only.
     */
    public static final String NMAM8195E = "NMAM8195E";

    /** Please specify Default Carrier. */
    public static final String NMAM8372E = "NMAM8372E";

    /** '[@]' cannot be entered as the GL Intercompany Code, when Internal/External is Internal. */
    public static final String NMAM8371E = "NMAM8371E";

    /** Please enter [@] to set up [@]. */
    public static final String NAMM0016E = "NAMM0016E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    // Del Start 2018/10/09 QC#27598
    ///** There is a Transaction that is Open. */
    //public static final String NMAM8374E = "NMAM8374E";
    // Del End 2018/10/09 QC#27598

    /** No change has been made. */
    public static final String NMAM8333I = "NMAM8333I";

    /** Please select the Contact that is associated with the relation account. */
    public static final String NMAM0304E = "NMAM0304E";

    /** Please select the Resource Email has been registered. */
    public static final String NMAM0305E = "NMAM0305E";

    /** Please select the Contact person who has Email address. */
    public static final String NMAM8491E = "NMAM8491E";

    /** When @ is entered, @ cannot entered. */
    public static final String NMAM8409E = "NMAM8409E";

    /** The combination between [@] and [@] is not correct. */
    public static final String NMAM0306E = "NMAM0306E";

    /** The maximum number of data has been exceeded. */
    public static final String NMAM0289E = "NMAM0289E";

    /** The specified format is incorrect. It must be @. */
    public static final String NMAM8075E = "NMAM8075E";

    /** [@] field has too many digits entered. */
    public static final String ZZM9001E = "ZZM9001E";

    /** [@] already exists in [@] */
    public static final String NMAM0834E = "NMAM0834E";

    /** If Hard Hold Flag is Y, please specify Credit Hold as 'Hold'. */
    public static final String NMAM8638E = "NMAM8638E";

    // Add Start 2017/12/11 QC#20357
    /** This record can not be deleted because it is being used in the [@]. */
    public static final String NMAM8622E = "NMAM8622E";
    // Add End 2017/12/11 QC#20357

    /** If change category (Prospect --> Customer), please create Bill To or Ship To. */
    public static final String NMAM8648I = "NMAM8648I";

    /** There were location relation(s) that can not be activated. Please activate manually. */
    public static final String NMAM8658I = "NMAM8658I";

    //QC#23604 add Start
    /** @ cannot be entered if @ is @. */
    public static final String NMAM8682E = "NMAM8682E";
    //QC#23604 add End

    // Add Start 2018/09/28 QC#28165
    /** Data update failure. Reciprocal relationship(s) with different effective term exists in the other side account. Please maintain each. */
    public static final String NMAM8689E = "NMAM8689E";

    /** You cannot set "Y" on "Reciprocal Flag" if "LEASE ACCOUNT" is selected. */
    public static final String NMAM8690E = "NMAM8690E";

    /** You cannot set "Y" on "Reciprocal Flag" if "MYCSA ACCOUNT" is selected. */
    public static final String NMAM8691E = "NMAM8691E";
    // Add End 2018/09/28 QC#28165

    // Add Start 2018/10/09 QC#27598
    /** There is a Transaction that is Open. If OK, please click the same button again. */
    public static final String NMAM8692W = "NMAM8692W";
    // Add End 2018/10/09 QC#27598

    // Add Start 2019/05/23 QC#50101
    /** You cannot check this location primary, because this location is not yet changed from other account. */
    public static final String NMAM8701E = "NMAM8701E";
    // Add End 2019/05/23 QC#50101

    // Add Start 2018/12/10 QC#29315
    /** [@] is mandatory. */
    public static final String ZZIM0094E = "ZZIM0094E";
    // Add End 2018/10/09 QC#29315

    /** @ is duplicated */
    public static final String MSG_DUP = "Relationship Type and Effective Date";

    /** xxChkBox_WD **/
    public static final String CHKBOX_WD = "xxChkBox_WD";

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

    /** TAB_TRANSACTIONS */
    public static final String TAB_SHIPPING = "Shipping";

    // Add Start 2018/07/30 QC#27222
    /** TAB_TAXING */
    public static final String TAB_TAXING = "Taxing";
    // Add End 2018/07/30 QC#27222

    /** ACTIVE_STS */
    public static final String ACTIVE_STS = "ACTIVE";

    /** INACTIVE_STS */
    public static final String INACTIVE_STS = "INACTIVE";

    /** CONTROL_STR */
    public static final String CONTROL_STR = "Control ";

    /** VAR_CHAR_CONST splitter */
    public static final String CONST_SPLITTER = ",";

    /** status (P01) */
    public static final String RGTN_STS_INPROCESS = "In Process";
    /** status (P20) */
    public static final String RGTN_STS_ACTIVE = "Active";
    /** status (P99) */
    public static final String RGTN_STS_INACTIVE = "Inactive";

    /** Max Effective Thru Date */
    public static final String MAX_EFF_THRU_DT = "99991231";

    /** LOC_NM max digit length */
    public static final int COL_LOC_NM_MAX_DIGIT_LENGTH = 60;

    /** Business ID */
    public static final String BUSINESS_ID = "NMAL6700";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** CSV file name */
    public static final String CSV_FILE_NAME = "LocationSearch";

    /** CSV file name */
    public static final String CSV_FILE_NAME_CTAC = "ContactSearch";
    
    // Add Start 2023/01/20 QC#61011
    public static final String CSV_FILE_NAME_RELNSHIP = "RelationshipSearch";
    // Add End 2023/01/20 QC#61011

    /** Limit Download RowNumber */
    public static final int LIMIT_DL_ROWNUM = 20000;

    /** SCRN_ID : NMAL6700Scrn00 */
    public static final String SCRN_ID = "NMAL6700Scrn00";

    /** MOST PARENT MAX LEVEL */
    public static final int MOST_PARENT_MAX_LEVEL = 6;

    /** CHILDREN MAX LEVEL */
    public static final int CHILDREN_MAX_LEVEL = 11;

    /** RGTN_STS_CD : ACTIVE */
    public static final String RGTN_STS_CD_ACTIVE = "ACTIVE";

    /** RGTN_STS_CD : INACTIVE */
    public static final String RGTN_STS_CD_INACTIVE = "INACTIVE";

    /** VAR_CHAR_CONST : DS_CUST_MSG_TP_NOTE */
    public static final String CONST_DS_CUST_MSG_TP_NOTE = "DS_CUST_MSG_TP_NOTE";

    /** VAR_CHAR_CONST : EXTERNAL_DEF_COA_AFFL_CD */
    public static final String CONST_EXTERNAL_DEF_COA_AFFL_CD = "EXTERNAL_DEF_COA_AFFL_CD";

    // Add Start 2019/01/07 QC#29749
    /** VAR_CHAR_CONST : ITRL_NON_AFFL_ACCT_NUM */
    public static final String CONST_ITRL_NON_AFFL_ACCT_NUM = "ITRL_NON_AFFL_ACCT_NUM";
    // Add End 2019/01/07 QC#29749

    /** Dupulicate message (Group Name and Biz Area combination ) */
    public static final String DUP_MSG_BIZ_AREA_AND_GRP_NM = "Group Name and Biz Area combination";

    /** error message item: Credit Profile Information */
    public static final String MSG_CR_PRFL_INFO = "Credit Profile Information";

    /** display name : Currency */
    public static final String DPLY_NAME_CCY = "Currency";

    /** display name : Credit Limit */
    public static final String DPLY_NAME_CR_LIMIT = "Credit Limit";

    /** display name : Credit Hold */
    public static final String DPLY_NAME_CR_HOLD = "Credit Hold";

    /** display name : Grace Period (Days) */
    public static final String DPLY_GRACE_PERIOD = "Grace Period (Days)";
    // START 2018/01/25 [QC#22095, ADD]
    /** display name : Contract Grace Period*/
    public static final String DPLY_CONTRACT_GRACE_PERIOD = "Contract Grace Period";
    // END   2018/01/25 [QC#22095, ADD]
    /** display name : Payment Term */
    public static final String DPLY_NAME_PMT_TERM = "Payment Term";

    /** display name : Default Collector */
    public static final String DPLY_NAME_DEF_COLLECTOR = "Default Collector";

    /** PARENT MAX LEVEL */
    public static final int PARENT_MAX_LEVEL = 9;

    /** PARENT MAX Count */
    public static final int PARENT_DATA_MAX_CNT = 10;

    /** Special Message Max Byte Length */
    public static final int MAX_SPCL_MSG_LEN = 4000;

    /** Application Function ID - Header */
    public static final String APP_FUNC_ID_HEADER = "NMAL6700001";

    /** CSV Upload ID - Location */
    public static final String CSV_UPD_ID_LOC = "NMA2670001";

    /** File extension */
    public static final String CSV_EXTENSION = ".csv";

    /** Char: Comma */
    public static final String CHAR_COMMA = ",";

    /** Max Customer Invoice Rule Recipient Count */
    public static final int MAX_CUST_INV_RULE_RCPNT_CNT = 20;

    /** Button Contact Search */
    public static final String BTN_OPEN_WIN_CTAC_SEARCH = "OpenWin_CtacSearch";

    /** Char: Slash */
    public static final String CHAR_SLASH = "/";

    /** Char: Semicolon */
    public static final String CHAR_SEMICOLON = ";";

    /** Prospect to Customer Change Status: Pending */
    public static final String PROS_TO_CUST_CHNG_STS_PENDING = "PE";

    // Add Start 2018/01/22 QC#20291(Sol#348)
    /** Account General Customer Update */
    public static final String FUNC_ID_ACCT_CUST_UPDATE = "NMAL6700T020";

    /** Account General Prospect Update */
    public static final String FUNC_ID_ACCT_PROS_UPDATE = "NMAL6700T220";
    // Add End 2018/01/22 QC#20291(Sol#348)

    // Add Start 2018/08/30 QC#27869
    /** Max Date */
    public static final String MAX_DT = "99991231";
    // Add End 2018/08/30 QC#27869

    // Add QC#57316
    /** Download and check open transactions. */
    public static final String NWAM0984E = "NWAM0984E";
    /** CSV file name */
    public static final String CSV_FILE_NAME_OPEN_TRX = "OpenTransactionSearch";
    // Add QC#57316 End

    // START 2024/03/12 T.Nagae [QC#63552,ADD]
    /** VAR_CHAR_CONST : Customer Billing Vehicle Code Renewal/Upliftment Target Key */
    public static final String NMAL6700_RNW_UPLFT_TARGET = "NMAL6700_RNW_UPLFT_TARGET";
    // END   2024/03/12 T.Nagae [QC#63552,ADD]
}
