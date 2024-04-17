/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6720.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/01/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/15   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/24   SRAA            Y.Chen          Update          QC#3580
 * 2016/02/25   Fujitsu         C.Tanaka        Update          QC#2756
 * 2016/02/26   Fujitsu         C.Tanaka        Update          QC#4349
 * 2016/02/29   Fujitsu         C.Tanaka        Update          QC#2824
 * 2016/04/26   SRAA            Y.Chen          Update          QC#6186
 * 2016/04/26   SRAA            Y.Chen          Update          QC#2674
 * 2016/04/28   SRAA            Y.Chen          Update          QC#2674
 * 2016/05/18   Fujitsu         N.Sugiura       Update          Unit Test#193
 * 2016/07/19   Hitachi         Y.Tsuchimoto    Update          QC#10745
 * 2016/09/22   SRAA            Y.Chen          Update          QC#12060
 * 2016/12/27   Fujitsu         C.Yokoi         Update          QC#14924
 * 2017/02/20   Fujitsu         T.Aoi           Update          QC#16846
 * 2017/06/29   Hitachi         J.Kim           Update          QC#17670
 * 2017/07/13   R.Nakamura      R.Nakamura      Update          QC#19059
 * 2017/11/27   Fujitsu         A.Kosai         Update          QC#20828
 * 2018/02/19   Fujitsu         M.Ohno          Update          QC#20297(Sol#379)
 * 2018/03/26   Fujitsu         K.Ishizuka      Update          QC#23935
 * 2018/05/16   Fujitsu         Hd.Sugawara     Update          QC#26041
 * 2018/07/11   Fujitsu         H.Kumagai       Update          QC#26422
 * 2018/07/31   Fujitsu         W.Honda         Update          QC#27488
 * 2018/10/09   Fujitsu         Hd.Sugawara     Update          QC#27598
 * 2018/10/26   Fujitsu         S.Kosaka        Update          QC#28841
 * 2018/12/13   Fujitsu         M.Ishii         Update          QC#29315
 * 2020/07/22   CITS            K.Ogino         Update          QC#57316
 * 2022/01/12   CITS            R.Azucena       Update          QC#59596
 * 2023/01/13   Hitachi         H.Watanabe      Update          QC#60860
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NMAL6720Constant {

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** Please check at least 1 checkbox. */
    public static final String NZZM0011E = "NZZM0011E";

    /** Search results exceeded [@]. Only showing first @ records. */
    public static final String NMAM8171W = "NMAM8171W";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** It failed to delete [@]. */
    public static final String NMAM0208E = "NMAM0208E";

    /** [@] does not exit. */
    public static final String NMAM8111E = "NMAM8111E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /**
     * Other data is set to Primary. To continue the process to change
     * primary, click submit.
     */
    public static final String NMAM8261W = "NMAM8261W";

    /** Unable to set Primary because it already exists. */
    public static final String NMAM8262E = "NMAM8262E";

    /**
     * No Primary Usage data found on the Account. Please check
     * Primary usage.
     */
    public static final String NMAM8345E = "NMAM8345E";

    /**
     * System can't validate to the address nor find suggested one, If
     * you want to validate, please enter proper address, otherwise
     * skip address validation.
     */
    public static final String NMAM8666E = "NMAM8666E";

    /**
     * We found a different address from the one you entered your
     * address has been modified. Please confirm and click submit.
     */
    public static final String NMAM8360W = "NMAM8360W";

    /**
     * No change has been made.
     */
    public static final String NMAM8333I = "NMAM8333I";

    /**
     * Please activate Bill To or Ship To.
     */
    public static final String NMAM8370E = "NMAM8370E";

    /** [@] is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** Can not get the default Remit ID. */
    public static final String NMAM8490E = "NMAM8490E";

    /**
     * Address exceeds 30 characters which may cause transaction
     * issues with Canon USA.
     */
    public static final String NMAM8492W = "NMAM8492W";

    /** The corresponding [@] does not exist. */
    public static final String NMAM0039E = "NMAM0039E";

    /** The check ended normally. */
    public static final String ZZM8000I = "ZZM8000I";

    /** Enter Code directly, if it is non-USA. */
    public static final String NMAM0147I = "NMAM0147I";

    /** "Start Date" cannot be later than "Sales Date". */
    public static final String NMAM0303E = "NMAM0303E";

    /** The effective periods are overlapped. Please correct it. */
    public static final String NMAM8113E = "NMAM8113E";

    /** The chronological sequence is wrong. */
    public static final String NMAM8115E = "NMAM8115E";

    /** Duplicate Reference# and Cross Reference Type exists. */
    public static final String NMAM8631E = "NMAM8631E";

    /**
     * Reference# and Cross Reference Type is already exists on other
     * location.
     */
    public static final String NMAM8632E = "NMAM8632E";

    /**
     * If you change status to Inactive immediately, Please set end
     * date as past date.
     */
    public static final String NMAM8642E = "NMAM8642E";

    /**
     * To change status to active, Please set end date as future date.
     */
    public static final String NMAM8651E = "NMAM8651E";

    /** Account has been converted from Prospect to Customer. */
    public static final String NMAM8649I = "NMAM8649I";

    /**
     * This location is WH/Tech WH address but @ table data doesn't
     * exist.
     */
    public static final String NMAM8650E = "NMAM8650E";

    /**
     * Either Bill to or Ship to must be active. Please activate Bill
     * to or Ship to.
     */
    public static final String NMAM8652E = "NMAM8652E";

    // Add Start 2017/07/13 QC#19059
    /**
     * More than 2 search results for "Postal Code" exist. 
     * Please Imput Address Information or select "Postal Code" from Popup Screen.
     */
    public static final String NMAM8670E = "NMAM8670E";
    // Add End 2017/07/13 QC#19059

    /** Please enter [@] to set up [@]. */
    public static final String NAMM0016E = "NAMM0016E";

    // Add Start 2018/10/09 QC#27598
    /** There is a Transaction that is Open. If OK, please click the same button again. */
    public static final String NMAM8692W = "NMAM8692W";
    // Add End 2018/10/09 QC#27598

    // 2018/10/26 QC#28841 Add Start
    /** @ doesn't exist. */
    public static final String NMAM8693E = "NMAM8693E";

    /** @ is not active. */
    public static final String NMAM8694E = "NMAM8694E";
    // 2018/10/26 QC#28841 Add End

    // 2018/12/13 QC#29135 Add Start
    /** When @ is entered, @ must be entered. */
    public static final String NMAM8178E = "NMAM8178E";

    /** Please specify Default Carrier. */
    public static final String NMAM8372E = "NMAM8372E";

    /** [@] is mandatory. */
    public static final String ZZIM0094E = "ZZIM0094E";

    /** A past date cannot be entered into [@]. */
    public static final String NMAM8200E = "NMAM8200E";

    // 2018/12/13 QC#29135 Add End

    // 2023/01/13 QC#60860 Add Start
    /** The entered [@] dose not in master */
    public static final String NMAM8121E = "NMAM8121E";
    // 2023/01/13 QC#60860 Add End

    // 2023/11/06 QC#61924 Add Start
    /** Cannot uncheck. Because parent account is deactivated for new transactions. */
    public static final String NMAM8725E = "NMAM8725E";
    // 2023/11/06 QC#61924 Add End

    /** TAB_LINKAGE */
    public static final String TAB_ACCOUNT = "Account";

    /** TAB_RELNSHIP */
    public static final String TAB_CLASSIFICATIONS = "Classifications";

    /** TAB_CONTACTS */
    public static final String TAB_CONTACTS = "Contacts";

    /** TAB_TRANSACTIONS */
    public static final String TAB_TRANSACTIONS = "Transactions";

    /** TAB_MSG_NOTE */
    public static final String TAB_MSG_NOTE = "Instructions";

    /** TAB_SVC_ATTRB */
    public static final String TAB_SVC_ATTRB = "SrvAttrb";

    /** TAB_SHIPPING */
    public static final String TAB_SHIPPING = "Shipping";

    /** PTY_LOC_WRK **/
    public static final String MSG_PTY_LOC_WRK = "PTY_LOC_WRK";

    /** PROS_PTY_LOC_WRK **/
    public static final String MSG_PROS_PTY_LOC_WRK = "PTY_LOC_WRK";

    /** PRIN_CUST **/
    public static final String MSG_PRIN_CUST = "PRIN_CUST";

    /** DS_ACCT_PROS **/
    public static final String MSG_DS_ACCT_PROS = "DS_ACCT_PROS";

    /** SELL_TO_CUST **/
    public static final String MSG_SELL_TO_CUST = "SELL_TO_CUST";

    /** BILL_TO_CUST **/
    public static final String MSG_BILL_TO_CUST = "BILL_TO_CUST";

    /** SHIP_TO_CUST **/
    public static final String MSG_SHIP_TO_CUST = "SHIP_TO_CUST";

    /** DS_CTAC_PSN_RELN **/
    public static final String MSG_DS_CTAC_PSN_RELN = "DS_CTAC_PSN_RELN";

    /** DS_XREF_ACCT **/
    public static final String MSG_DS_XREF_ACCT = "DS_XREF_ACCT";

    /** DS_CUST_TRX_RULE **/
    public static final String MSG_DS_CUST_TRX_RULE = "DS_CUST_TRX_RULE";

    /** DS_CUST_NON_PRF_TECH **/
    public static final String MSG_DS_CUST_NON_PRF_TECH = "DS_CUST_NON_PRF_TECH";

    /** SVC_ACCS_PMIT **/
    public static final String MSG_SVC_ACCS_PMIT = "SVC_ACCS_PMIT";

    /** CUSTOMER **/
    public static final String MSG_CUSTOMER = "CUSTOMER";

    /** PROSPECT **/
    public static final String MSG_PROSPECT = "PROSPECT";

    /** TECH_MSTR **/
    public static final String MSG_TECH_MSTR = "TECH_MSTR";

    /** DS_CUST_SPCL_MSG **/
    public static final String MSG_DS_CUST_SPCL_MSG = "DS_CUST_SPCL_MSG";

    /** ACCT_LOC **/
    public static final String MSG_ACCT_LOC = "ACCT_LOC";

    /** LOC_USG **/
    public static final String MSG_LOC_USG = "LOC_USG";

    /** INV_RCPNT **/
    public static final String MSG_INV_RCPNT = "INV_RCPNT";

    /** ALT_PAYER **/
    public static final String MSG_ALT_PAYER = "ALT_PAYER";

    /** Access Permission Number */
    public static final String MSG_SVC_ACCS_PMIT_NUM = "Access Permission No";

    /** Related Account Number */
    public static final String MSG_RELN_ACCT = "Related Acct#";

    /** Start Date */
    public static final String MSG_START_DT = "Start date";

    /** RTL_WH */
    public static final String MSG_RTL_WH = "RTL_WH";

    /** WH */
    public static final String MSG_WH = "WH";

    /** DS_CUST_SHPG_DEF */
    public static final String MSG_DS_CUST_SHPG_DEF = "DS_CUST_SHPG_DEF";

    // 2018/12/13 QC#29315 Add Start
    /** DS_ACCT_CARR */
    public static final String MSG_DS_ACCT_CARR = "DS_ACCT_CARR";
    // 2018/12/13 QC#29315 Add End

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

    /** xxChkBox_F1 **/
    public static final String CHKBOX_G = "xxChkBox_G1";

    /** CHKBOX_I **/
    public static final String CHKBOX_I = "xxChkBox_I1";

    /** VAR_CHAR_CONST_NM_COA_ACCT_CD */
    public static final String VAR_CHAR_CONST_NM_COA_ACCT_CD = "SVC_COA_ACCT_CD";

    // 2017/11/27 QC#20828 Add Start
    /** VAR_CHAR_CONST_NM_ACCT_INAC_RSN_CD */
    public static final String VAR_CHAR_CONST_NM_ACCT_INAC_RSN_CD = "NMAL6720_DS_ACCT_INAC_RSN_CD";
    // 2017/11/27 QC#20828 Add End

    // 2018/10/26 QC#28841 Add Start
    /** VAR_CHAR_CONST_NM_END_TECH_HAZMAT_CD */
    public static final String VAR_CHAR_CONST_NM_END_TECH_HAZMAT_CD = "END_OF_TECH_HAZMAT_CODE";

    /** Error message:Related Tech WH */
    public static final String ERR_MSG_RELATED_TECH_WH = "Related Tech WH";
    // 2018/10/26 QC#28841 Add End

    /** Mode Edit:01 */
    public static final String SCRN_EVENT_EDIT = "01";

    /** Mode New:02 */
    public static final String SCRN_EVENT_NEW = "02";

    /** Max Date */
    public static final String MAX_DT = "99991231";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** CSV File Name */
    public static final String CSV_FILE_NAME = "Contacts";

    /** Limit Download RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /** Active */
    public static final String ACTIVE = "Active";

    /** Inactive */
    public static final String IN_ACTIVE = "Inactive";

    /** In Process */
    public static final String IN_PROCESS = "In Process";

    /** Parent Account */
    public static final String PRNT_ACCT = "Parent Account";

    /** [@] is not selected. */
    public static final String NMAM0014E = "NMAM0014E";

    /** You can have only one Primary Contact. */
    public static final String NMAM8343E = "NMAM8343E";

    /**
     * Selected @ and @ doesn't have active relationship. Please
     * Confirm @.
     */
    public static final String NMAM8389E = "NMAM8389E";

    /** Current date can not be set to @. */
    public static final String NMAM8663E = "NMAM8663E";

    // START 2017/06/29 J.Kim [QC#17670,ADD]
    // Del Start 2018/10/09 QC#27598
    ///** There is a Transaction that is Open. */
    //public static final String NMAM8374E = "NMAM8374E";
    // Del End 2018/10/09 QC#27598
    // END 2017/06/29 J.Kim [QC#17670,ADD]

    // 2017/11/27 QC#20828 Add Start
    /** When you merge Prospect to Customer, please entered Bill to or Ship to. */
    public static final String NMAM8674I = "NMAM8674I";
    // 2017/11/27 QC#20828 Add End

    /**
     * "DPL Screening Process Error" occurred and the process has been
     * aborted.
     */
    public static final String NMAM8052E = "NMAM8052E";

    /** Special Message Max Byte Length */
    public static final int MAX_SPCL_MSG_LEN = 4000;

    /** Max Location Address Length */
    public static final int MAX_LOC_ADDR_LEN = 30;

    /** DPL Rason Max Byte Length */
    public static final int MAX_LEN_DPL_RSN_TXT = 2000;

    /** Prospect to Customer Change Status: Pending */
    public static final String PROS_TO_CUST_CHNG_STS_PENDING = "PE";
    
    /** Default Disply COA Information Application Function Id */ // 2018/03/26 S21_NA#23935 Add
    public static final String DEF_DPLY_COA_INFO_APP_FUNC_ID = "NMAL6730001";

    // Add Start 2018/05/16 QC#26041
    /** Business ID */
    public static final String BUSINESS_ID = "NMAL6720";

    /** Location General Update */
    public static final String FUNC_ID_LOCATION_GENERAL_UPDATE = "NMAL6720T020";
    // Add End 2018/05/16 QC#26041

    // 2018/07/11 Update Start QC#26422
    /** The entered [@] does not exist in [@]. */
    public static final String NMAM0163E = "NMAM0163E";

    /** Bill To Code */
    public static final String MSG_BILL_TO_CD = "Bill To Code";

    /** Master */
    public static final String MSG_MST = "master";
    // 2018/07/11 Update End QC#26422

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

    // Add QC#57316
    /** Download and check open transactions. */
    public static final String NWAM0984E = "NWAM0984E";
    /** CSV file name */
    public static final String CSV_FILE_NAME_OPEN_TRX = "OpenTransactionSearch";
    // Add QC#57316 End

    // START 2022/01/12 R.Azucena[QC#59596, ADD]
    /** All account dates are older than Sales Date[@]. */
    public static final String NMAM8711E = "NMAM8711E";
    // END 2022/01/12 R.Azucena[QC#59596, ADD]
}
