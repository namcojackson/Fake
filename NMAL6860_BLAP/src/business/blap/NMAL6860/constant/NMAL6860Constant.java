/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6860.constant;

/**
 * <pre>
 * NMAL6860 Supplier Entry
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/04   CITS            M.Ouchi         Create          N/A
 * 08/09/2016   CITS            S.Endo          Update          QC#10839
 * 2016/08/22   CITS            T.Gotoda        Update          QC#12215
 * 2016/10/03   CITS            R.Shimamoto     Update          QC#12768
 * 2016/11/25   Fujitsu         R.Nakamura      Update          QC#15438
 * 2016/12/15   CITS            R.Shimamoto     Update          QC#15816
 * 2018/02/15   CITS            T.Gotoda        Update          QC#22054
 * 06/11/2018   CITS            K.Ogino         Update          QC#26498
 * 12/23/2019   Fujitsu         R.Nakamura      Update          QC#54971-1
 * 2020/02/28   Fujitsu         C.Hara          Update          QC#55971
 * 2020/10/28   CITS            R.Kurahashi     Update          QC#57732
 * 2021/03/01   CITS            G.Delgado       Update          QC#56057
 * 2022/11/14   CITS            D.Mamaril       Update          QC#60617
 * </pre>
 */
public class NMAL6860Constant {

    // ------------------- Tab Name -------------------

    /**
     * The tab name. (General)
     */
    public static final String TAB_NM_GENERAL = "General";

    /**
     * The tab name. (Detail)
     */
    public static final String TAB_NM_DETAIL = "Detail";

    // ------------------- Event Name -------------------

    /**
     * The event name of "INIT".
     */
    public static final String EVENT_NM_INIT = "NMAL6860_INIT";

    public static final String BUSINESS_ID = "NMAL6860";

    /**
     * The event name of "OnClick_AddSupplierSite".
     */
    public static final String EVENT_NM_ON_CLICK_ADD_SUPPLIER_SITE = "NMAL6860Scrn00_OnClick_AddSupplierSite";

    /**
     * The event name of "OnClick_AddContactInfo".
     */
    public static final String EVENT_NM_ON_CLICK_ADD_CONTACT_INFO = "NMAL6860Scrn00_OnClick_AddContactInfo";

    /**
     * The event name of "TAB_General".
     */
    public static final String EVENT_NM_TAB_GENERAL = "NMAL6860Scrn00_TAB_General";

    /**
     * The event name of "TAB_Detail".
     */
    public static final String EVENT_NM_TAB_DETAIL = "NMAL6860Scrn00_TAB_Detail";

    /**
     * The event name of "CMN_Submit".
     */
    public static final String EVENT_NM_CMN_SUBMIT = "NMAL6860Scrn00_CMN_Submit";

    /**
     * The event name of "CMN_Clear".
     */
    public static final String EVENT_NM_CMN_CLEAR = "NMAL6860Scrn00_CMN_Clear";

    /**
     * The event name of "CMN_Reset".
     */
    public static final String EVENT_NM_CMN_RESET = "NMAL6860Scrn00_CMN_Reset";
    /**
     * The event name of "NMAL6860Scrn00_GetAddress".
     */
    public static final String EVENT_NM_GET_ADDRESS = "NMAL6860Scrn00_GetAddress";

    /**
     * The event name of "OnClick_LiabilityAccount".
     */
    public static final String EVENT_NM_ON_CLICK_LIABILITYACCOUNT = "NMAL6860Scrn00_OnClick_LiabilityAccount";

    /**
     * The event name of "OpenWin_PrePayAccount".
     */
    public static final String EVENT_NM_OPEN_WIN_PREPAYACCOUNT = "NMAL6860Scrn00_OpenWin_PrePayAccount";

    /**
     * The event name of "OpenWin_VendorReturnAccount".
     */
    public static final String EVENT_NM_OPEN_WIN_VENDORRETURNACCOUNT = "NMAL6860Scrn00_OpenWin_VendorReturnAccount";

    // 2020/02/28 QC#55971 Add Start
    /**
     * The event name of "NMAL6860_NWAL1130".
     */
    public static final String EVENT_NM_SELECT_NWAL1130 = "NMAL6860_NWAL1130";
    // 2020/02/28 QC#55971 Add End

    /** Address each line max length */
    public static final int ADDRESS_LENGTH = 60;

    /** Default thru date */
    public static final String DEF_THRU_DT = "99991231";

    /** VarcharConst Key : Address Check Flag */
    public static final String NMAL6860_ADDR_CHK_FLG = "NMAL6860_ADDR_CHK_FLG";

    /** VarcharConst Key : Default International Postal Code */
    public static final String DEF_INTL_POST_CD = "DEF_INTL_POST_CD";

    // START 2021/03/01 G.Delgado [QC#56057,ADD]
    /** VarcharConst Key : Open AP Invoice Status Codes */
    public static final String NMAL6860_ACCT_INV_STS_CD_OPEN = "NMAL6860_ACCT_INV_STS_CD_OPEN";

    /** VarcharConst Key : Unchangeable Supplier Types */
    public static final String NMAL6860_PRNT_VND_TP_CD_FIXED = "NMAL6860_PRNT_VND_TP_CD_FIXED";
    // END 2021/03/01 G.Delgado [QC#56057,ADD]

    /** delimiter string */
    public static final String VAR_CHAR_KEY_DELIMITER = "CONT_DELIMITER";

    /** segment token list size. */
    public static final int SEGMENT_TOKEN_LIST_SIZE = 9;

    /** The format of [@] is incorrect. */
    public static final String NLCM0210E = "NLCM0210E";

    /** Number of digits over error (item name [@]). */
    public static final String NLCM0211E = "NLCM0211E";

    /** Entered @ is invalid. */
    public static final String NLCM0065E = "NLCM0065E";

    /** The corresponding [@] does not exist. */
    public static final String NMAM0039E = "NMAM0039E";

    /** Please correct it because segment is different from default value.[@] */
    public static final String NMAM8662E = "NMAM8662E";

    /**
     * Anchor event name. (LiabilityAccount)
     */
    public static final String LIABILITY_ACCOUNT = "LiabilityAccount";

    /**
     * Anchor event name. (PrePayAccount)
     */
    public static final String PREPAY_ACCOUNT = "PrePayAccount";

    /**
     * Anchor event name. (VendorReturnAccount)
     */
    public static final String VENDOR_RETURN_ACCOUNT = "VendorReturnAccount";

    /** message parameter : Segment */
    public static final String MSG_PARAM_SEGMENT = "Segment";

    /** message parameter : Company */
    public static final String MSG_PARAM_CMPY = "Company";

    // Mod Start 2016/11/25 QC#15438
    /** message parameter : Extension */
//    public static final String MSG_PARAM_EXTN = "Extension";
    public static final String MSG_PARAM_EXTN = "Business Unit";
    // Mod Start 2016/11/25 QC#15438

    /** message parameter : Cost Center */
    public static final String MSG_PARAM_CC = "Cost Center";

    /** message parameter : Account */
    public static final String MSG_PARAM_ACCT = "Account";

    // Mod Start 2016/11/25 QC#15438
    /** message parameter : Company */
//    public static final String MSG_PARAM_PROJ = "Project";
    public static final String MSG_PARAM_PROJ = "Merchandise";
    // Mod End 2016/11/25 QC#15438

    /** message parameter : Product */
    public static final String MSG_PARAM_PROD = "Product";

    // Mod Start 2016/11/25 QC#15438
    /** message parameter : Affiliate */
//    public static final String MSG_PARAM_AFFL = "Affiliate";
    public static final String MSG_PARAM_AFFL = "Intercompany";
    // Mod End 2016/11/25 QC#15438

    /** message parameter : Channel */
    public static final String MSG_PARAM_CH = "Channel";

    /** message parameter : Branch */
    public static final String MSG_PARAM_BR = "Branch";

    /** segment token list index : COA_CMPY_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD = 0;

    /** segment token list index : COA_BR_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD = 1;

    /** segment token list index : COA_CC_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD = 2;

    /** segment token list index : COA_ACCT_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD = 3;

    /** segment token list index : COA_PROD_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD = 4;

    /** segment token list index : COA_PROD_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD = 5;

    /** segment token list index : COA_PROD_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD = 6;

    /** segment token list index : COA_PROJ_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD = 7;

    /** segment token list index : COA_EXTN_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD = 8;

    /** segment element length : COA_CMPY_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CMPY = 3;

    /** segment element length : COA_EXTN_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_EXTN = 3;

    /** segment element length : COA_CC_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CC = 6;

    /** segment element length : COA_ACCT_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_ACCT = 8;

    /** segment element length : COA_PROJ_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_PROJ = 4;

    /** segment element length : COA_PROD_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_PROD = 8;

    /** segment element length : COA_AFFL_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_AFFL = 3;

    /** segment element length : COA_CH_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CH = 3;

    /** segment element length : COA_BR_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_BR = 3;

    /** DB Column Name  : COA_CMPY_CD */
    public static final String DB_COLUMN_COA_CMPY_CD = "COA_CMPY_CD";

    /** DB Column Name  : COA_BR_CD */
    public static final String DB_COLUMN_COA_BR_CD = "COA_BR_CD";

    /** DB Column Name  : COA_CC_CD */
    public static final String DB_COLUMN_COA_CC_CD = "COA_CC_CD";

    /** DB Column Name  : COA_ACCT_CD */
    public static final String DB_COLUMN_COA_ACCT_CD = "COA_ACCT_CD";

    /** DB Column Name  : COA_PROD_CD */
    public static final String DB_COLUMN_COA_PROD_CD = "COA_PROD_CD";

    /** DB Column Name  : COA_CH_CD */
    public static final String DB_COLUMN_COA_CH_CD = "COA_CH_CD";

    /** DB Column Name  : COA_AFFL_CD */
    public static final String DB_COLUMN_COA_AFFL_CD = "COA_AFFL_CD";

    /** DB Column Name  : COA_PROJ_CD */
    public static final String DB_COLUMN_COA_PROJ_CD = "COA_PROJ_CD";

    /** DB Column Name  : COA_EXTN_CD */
    public static final String DB_COLUMN_COA_EXTN_CD = "COA_EXTN_CD";

    /**
     * DB Column Name: COA Company Dply Flag
     */
    public static final String DB_COLUMN_COA_CMPY_DPLY_FLG = "COA_CMPY_DPLY_FLG";

    /**
     * DB Column Name: COA Affiliate Dply Flag
     */
    public static final String DB_COLUMN_COA_AFFL_DPLY_FLG = "COA_AFFL_DPLY_FLG";

    /**
     * DB Column Name: COA Branch Dply Flag
     */
    public static final String DB_COLUMN_COA_BR_DPLY_FLG = "COA_BR_DPLY_FLG";

    /**
     * DB Column Name: COA CostCentor Dply Flag
     */
    public static final String DB_COLUMN_COA_CC_DPLY_FLG = "COA_CC_DPLY_FLG";

    /**
     * DB Column Name: COA Account Dply Flag
     */
    public static final String DB_COLUMN_COA_ACCT_DPLY_FLG = "COA_ACCT_DPLY_FLG";

    /**
     * DB Column Name: COA Product Dply Flag
     */
    public static final String DB_COLUMN_COA_PROD_DPLY_FLG = "COA_PROD_DPLY_FLG";

    /**
     * DB Column Name: COA Channel Dply Flag
     */
    public static final String DB_COLUMN_COA_CH_DPLY_FLG = "COA_CH_DPLY_FLG";

    /**
     * DB Column Name: COA Project Dply Flag
     */
    public static final String DB_COLUMN_COA_PROJ_DPLY_FLG = "COA_PROJ_DPLY_FLG";

    /**
     * DB Column Name: COA Extension Dply Flag
     */
    public static final String DB_COLUMN_COA_EXTN_DPLY_FLG = "COA_EXTN_DPLY_FLG";

    /** Account Type Liability  : 01 */
    public static final String ACC_TYPE_LIABILITY = "01";

    /** Account Type PrePay     : 02 */
    public static final String ACC_TYPE_PREPAY = "02";

    /** Account Type Vnd Return : 03 */
    public static final String ACC_TYPE_VNDRETURN = "03";

    // ------------------- The message code -------------------
    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";
    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";
    /** The number for this process exceeds the maximum numbers for
     *  display and cannot proceed. */
    public static final String NMAM8114E = "NMAM8114E";
    /** No search result is found. */
    public static final String NMAM0038I = "NMAM0038I";
    /** The selected [@] does not exist. */
    public static final String NMAM0013E = "NMAM0013E";
    /** You have entered an invalid address. Please enter a valid
     *  address. */
    public static final String NMAM8359E = "NMAM8359E";
    /** We found a different address from the one you entered your
     *  address has been modified. Please confirm and click submit. */
    public static final String NMAM8360W = "NMAM8360W";
    /** The entered [@] does not exist in [@]. */
    public static final String NMAM0163E = "NMAM0163E";
    /** Error Message: [@] is not exist in Master. */
    public static final String NMAM8454E = "NMAM8454E";
    /** Error Message: [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";
    /** Address Verification is limited US only. */
    public static final String NMAM8675I = "NMAM8675I";
    // Add Start 2019/12/23 QC#54971-1
    /** The effective date of the entered data is incorrect. */
    public static final String NMAM0803E = "NMAM0803E";
    // Add End 2019/12/23 QC#54971-1
    // START 2021/03/01 G.Delgado [QC#56057,ADD]
    /** Please process remaining Open PO / Open AP for this Supplier in order to change Supplier Type. */
    public static final String NMAM0308E = "NMAM0308E";
    // END 2021/03/01 G.Delgado [QC#56057,ADD]
    /**
     * Add QC#26498 Maximum loc_Nm length.
     */
    public static final int MAX_LOC_NM_LENGTH = 15;
    
    // START 2020/10/28 R.Kurahashi [QC#57732,ADD]
    public static final String ENTRY_SUPPLIER = "SupplierEntry";
    
    public static final String NEW_SUPPLIER = "NewSupplier";
    // END 2020/10/28 R.Kurahashi [QC#57732,ADD]
    // START 2022/11/14 D.Mamaril [QC#60617,ADD]
    /** The entered [State Code] does not exist in [ST]. */
    public static final String NMZM0292E = "NMZM0292E";
    /** The entered [Country Code] does not exist in [CTRY]. */
    public static final String NMZM0293E = "NMZM0293E";
    // END 2022/11/14 D.Mamaril [QC#60617,ADD]
}
