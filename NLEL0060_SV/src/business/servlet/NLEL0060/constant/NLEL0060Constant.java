/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060.constant;

/**
 *<pre>
 * NLEL0060Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/04/18   Hitachi         J.Kim           Update          QC#6581
 * 2016/06/16   Hitachi         T.Tsuchida      Update          QC#10145
 * 2016/08/23   Fujitsu         C.Tanaka        Update          QC#11026
 * 2016/08/30   Fujitsu         C.Tanaka        Update          QC#13360
 * 2016/09/14   Fujitsu         C.Tanaka        Update          QC#12697
 * 2017/05/15   CITS            M.Naito         Update          Merge DS Lv2
 * 2018/05/17   Hitachi         J.Kim           Update          QC#25879
 *</pre>
 */
public class NLEL0060Constant {

    /** Business ID */
    public static final String BIZ_ID = "NLEL0060";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NLEL0060Scrn00";

    /** Function Code: Search */
    public static final String FUNC_CD_SRCH = "20";

    /** Function Code: Update */
    public static final String FUNC_CD_UPD = "40";

    /** Privilege: Update */
    public static final String PRIV_UPD = "NLEL0060T020";

    /** TAB: Detail */
    public static final String TAB_DTL = "Detail";

    /** TAB: Assignment */
    public static final String TAB_ASG = "Assignment";

    /** TAB: Financial */
    public static final String TAB_FIN = "Financial";

    /** TAB: Trx */
    public static final String TAB_TRX = "Trx";

    /** TAB: DepSim */
    public static final String TAB_DEP_SIM = "DepSim";

    // START 2016/09/27 J.Kim [QC#13372,ADD]
    /** TAB: AssetHist */
    public static final String TAB_ASSET_HIST = "AssetHist";

    // END 2016/09/27 J.Kim [QC#13372,ADD]

    /** Comma */
    public static final String STR_COMMA = ".";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit" };

    /** F3 : Apply */
    public static final String[] BTN_CMN_APL = {"btn3", "CMN_Apply", "Apply" };

    /** F4 : Apply */
    public static final String[] BTN_CMN_APR = {"btn4", "CMN_Approve", "Approve" };

    /** F5 : Reject */
    public static final String[] BTN_CMN_RJT = {"btn5", "CMN_Reject", "Reject" };

    /** F6 : Download */
    public static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /** F7 : Delete */
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

    // --------------------------------
    // Message
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    // START 2016/06/16 T.Tsuchida [QC#10145,DEL]
    // /** Please set at least one criteria. */
    // public static final String NLZM2276E = "NLZM2276E";
    // END 2016/06/16 T.Tsuchida [QC#10145,DEL]

    /** The value for [@] must be bigger than [@]. */
    public static final String NLZM2277E = "NLZM2277E";

    // START 2016/04/18 J.Kim [QC#7650,MOD]
    // /** Have not been selected. Please select. */
    // public static final String NLAM0023E = "NLAM0023E";
    /** Please check at least 1 checkbox. */
    public static final String NZZM0011E = "NZZM0011E";

    // END 2016/04/18 J.Kim [QC#7650,MOD]

    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";

    // START 2016/10/17 J.Kim [QC#13453,ADD]
    /** . */
    public static final String NLEM0007E = "NLEM0007E";

    // END 2016/10/17 J.Kim [QC#13453,ADD]

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Saved Search Option */
    public static final String SRCH_OPT_PK = "Saved Search Options";

    /** Search Option Name */
    public static final String SRCH_OPT_NM = "Search Option Name";

    /** Customer */
    // START 2016/04/18 J.Kim [QC#6581,MOD]
    // public static final String SELL_TO_CUST_CD = "Customer";
    public static final String SHIP_TO_CUST_ACCT_CD = "Customer";

    // END 2016/04/18 J.Kim [QC#6581,MOD]

    /** Item */
    public static final String MDSE_CD = "Item";

    /** Vendor Header */
    public static final String VND_CD_HDR = "Vendor Header";

    /** Vendor */
    public static final String VND_CD = "Vendor";

    /** Warehouse */
    public static final String RTN_WH_CD = "Warehouse";

    /** Book Type */
    public static final String ASSET_TP_CD = "Book Type";

    /** Retire Date */
    public static final String ACCT_YR_MTH = "Retire Month";

    /** Retire Reason */
    public static final String ASSET_RETIRE_RSN_CMNT_TXT = "Retire Reason";

    /** Service In From */
    public static final String DEPC_START_DT_FROM = "From Date in Service";

    /** Service In To */
    public static final String DEPC_START_DT_TO = "To Date in Service";

    /** Description */
    public static final String DS_ASSET_DESC_TXT = "Description";

    /** Tag Number */
    public static final String ASSET_TAG_NUM = "Tag Number";

    /** Check Box */
    public static final String CHECK_BOX = "Check Box";

    // --------------------------------
    // Fields
    // --------------------------------
    /** xxCheckBox_A1 */
    public static final String CHECK_BOX_A = "xxChkBox_A1";

    /** xxCheckBox_B1 */
    public static final String CHECK_BOX_B = "xxChkBox_B1";

    /** xxCheckBox_C1 */
    public static final String CHECK_BOX_C = "xxChkBox_C1";

    /** xxPgFlg_A1 */
    public static final String PG_FLG_A = "xxPgFlg_A1";

    // --------------------------------
    // Popup
    // --------------------------------
    /** Event Name : OpenWin_AssetAcctGL */
    public static final String OPENWIN_ASSET_ACCT_GL = "OpenWin_AssetAcctGL";

    /** Event Name : OpenWin_ExpAcctGL */
    public static final String OPENWIN_EXP_ACCT_GL = "OpenWin_ExpAcctGL";

    /** Event Name : OpenWin_WHCode */
    public static final String OPENWIN_WH_CODE = "OpenWin_WHCode";

    /** Event Name : OpenWin_RtrnWhCd */
    public static final String OPENWIN_RTRN_WH_CD = "OpenWin_RtrnWhCd";

    /** Event Name : OpenWin_Vender */
    public static final String OPENWIN_VENDER = "OpenWin_Vender";

    /** Event Name : OpenWin_VndCode */
    public static final String OPENWIN_VND_CODE = "OpenWin_VndCode";

    /** Event Name : OpenWin_CoaMdseTpCd */
    public static final String OPENWIN_COA_MDSE_TP_CD = "OpenWin_CoaMdseTpCd";

    /** Event Name : OpenWin_CustCode */
    public static final String OPENWIN_CUST_CODE = "OpenWin_CustCode";

    /** Event Name : OpenWin_DsAcctNm */
    public static final String OPENWIN_DS_ACCT_NM = "OpenWin_DsAcctNm";

    // START 2018/04/12 J.Kim [QC#22087,MOD]
    /** Event Name : OpenWin_ExpenseAcctFrom */
    public static final String OPENWIN_EXP_ACCT_FROM = "OpenWin_ExpenseAcctFrom";

    /** Event Name : OpenWin_ExpenseAcctFrom */
    public static final String OPENWIN_EXP_ACCT_TO = "OpenWin_ExpenseAcctTo";

    /** Event Name : OpenWin_ExpenseAcctFrom */
    public static final String OPENWIN_EXP_BR_FROM = "OpenWin_ExpenseBrFrom";

    /** Event Name : OpenWin_ExpenseAcctFrom */
    public static final String OPENWIN_EXP_BR_TO = "OpenWin_ExpenseBrTo";

    /** Event Name : OpenWin_ExpenseAcctFrom */
    public static final String OPENWIN_EXP_CC_FROM = "OpenWin_ExpenseCcFrom";

    /** Event Name : OpenWin_ExpenseAcctFrom */
    public static final String OPENWIN_EXP_CC_TO = "OpenWin_ExpenseCcTo";

    /** Event Name : OpenWin_ExpenseAcctFrom */
    public static final String OPENWIN_EXP_EXTN_FROM = "OpenWin_ExpenseExtnFrom";

    /** Event Name : OpenWin_ExpenseAcctFrom */
    public static final String OPENWIN_EXP_EXTN_TO = "OpenWin_ExpenseExtnTo";

    // END 2018/04/12 J.Kim [QC#22087,MOD]

    // START 2018/05/17 J.Kim [QC#25879,ADD]
    /** Event Name : OpenWin_SR */
    public static final String OPENWIN_SR = "OpenWin_SR";
    // END 2018/05/17 J.Kim [QC#25879,ADD]

    // START 2016/10/17 J.Kim [QC#13453,ADD]
    /** Array Number : COA_CMPY_CD */
    public static final int ARRAY_NUM_CMPY_CD = 0;

    /** Array Number : COA_BR_CD */
    public static final int ARRAY_NUM_BR_CD = 1;

    /** Array Number : COA_CC_CD */
    public static final int ARRAY_NUM_CC_CD = 2;

    /** Array Number : COA_ACCT_CD */
    public static final int ARRAY_NUM_ACCT_CD = 3;

    /** Array Number : COA_PROD_CD */
    public static final int ARRAY_NUM_PROD_CD = 4;

    /** Array Number : COA_CH_CD */
    public static final int ARRAY_NUM_CH_CD = 5;

    /** Array Number : COA_AFFL_CD */
    public static final int ARRAY_NUM_AFFL_CD = 6;

    /** Array Number : COA_PROJ_CD */
    public static final int ARRAY_NUM_PROJ_CD = 7;

    /** Array Number : COA_EXTN_CD */
    public static final int ARRAY_NUM_EXTN_CD = 8;

    /** Parram Number : COA_CMPY_CD */
    public static final int PARAM_NUM_CMPY_CD = 1;

    /** Parram Number : COA_BR_CD */
    public static final int PARAM_NUM_BR_CD = 3;

    /** Parram Number : COA_CC_CD */
    public static final int PARAM_NUM_CC_CD = 4;

    /** Parram Number : COA_ACCT_CD */
    public static final int PARAM_NUM_ACCT_CD = 5;

    /** Parram Number : COA_PROD_CD */
    public static final int PARAM_NUM_PROD_CD = 6;

    /** Parram Number : COA_CH_CD */
    public static final int PARAM_NUM_CH_CD = 7;

    /** Parram Number : COA_AFFL_CD */
    public static final int PARAM_NUM_AFFL_CD = 2;

    /** Parram Number : COA_PROJ_CD */
    public static final int PARAM_NUM_PROJ_CD = 8;

    /** Parram Number : COA_EXTN_CD */
    public static final int PARAM_NUM_EXTN_CD = 9;

    // END 2016/10/17 J.Kim [QC#13453,ADD]

    // START 2018/04/12 J.Kim [QC#22087,MOD]
    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_ACCT = "COA_ACCT";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_ACCT = "COA_ACCT_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_ACCT = "COA_ACCT_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_ACCT = "COA_ACCT_SORT_NUM";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_ACCT = "Natural Account Search Popup";

    /** Header Code of Label Name for NMAL6050 : Natural Account Code */
    public static final String HDR_CD_LB_NM_FOR_ACCT = "Natural Account Code";

    /** Header Name of Label Name for NMAL6050 : Natural Account Name */
    public static final String HDR_NM_LB_NM_FOR_ACCT = "Natural Account Name";

    /** Detail Code of Label Name for NMAL6050 : Natural Account Code */
    public static final String DTL_CD_LB_NM_FOR_ACCT = "Natural Account Code";

    /** Detail Name of Label Name for NMAL6050 : Natural Account Name */
    public static final String DTL_NM_LB_NM_FOR_ACCT = "Natural Account Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_BR = "COA_BR";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_BR = "COA_BR_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_BR = "COA_BR_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_BR = "COA_BR_SORT_NUM";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_BR = "Branch Search Popup";

    /** Header Code of Label Name for NMAL6050 : Branch Code */
    public static final String HDR_CD_LB_NM_FOR_BR = "Branch Code";

    /** Header Name of Label Name for NMAL6050 : Branch Name */
    public static final String HDR_NM_LB_NM_FOR_BR = "Branch Name";

    /** Detail Code of Label Name for NMAL6050 : Branch Code */
    public static final String DTL_CD_LB_NM_FOR_BR = "Branch Code";

    /** Detail Name of Label Name for NMAL6050 : Branch Name */
    public static final String DTL_NM_LB_NM_FOR_BR = "Branch Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_CC = "COA_CC";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_CC = "COA_CC_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_CC = "COA_CC_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_CC = "COA_CC_SORT_NUM";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_CC = "Cost Center Search Popup";

    /** Header Code of Label Name for NMAL6050 : Cost Center Code */
    public static final String HDR_CD_LB_NM_FOR_CC = "Cost Center Code";

    /** Header Name of Label Name for NMAL6050 : Cost Center Name */
    public static final String HDR_NM_LB_NM_FOR_CC = "Cost Center Name";

    /** Detail Code of Label Name for NMAL6050 : Cost Center Code */
    public static final String DTL_CD_LB_NM_FOR_CC = "Cost Center Code";

    /** Detail Name of Label Name for NMAL6050 : Cost Center Name */
    public static final String DTL_NM_LB_NM_FOR_CC = "Cost Center Name";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_EXTN = "COA_EXTN";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_EXTN = "COA_EXTN_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_EXTN = "COA_EXTN_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_EXTN = "COA_EXTN_SORT_NUM";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_EXTN = "Business Unit Search Popup";

    /** Header Code of Label Name for NMAL6050 : Business Unit Code */
    public static final String HDR_CD_LB_NM_FOR_EXTN = "Business Unit Code";

    /** Header Name of Label Name for NMAL6050 : Business Unit Name */
    public static final String HDR_NM_LB_NM_FOR_EXTN = "Business Unit Name";

    /** Detail Code of Label Name for NMAL6050 : Business Unit Code */
    public static final String DTL_CD_LB_NM_FOR_EXTN = "Business Unit Code";

    /** Detail Name of Label Name for NMAL6050 : Business Unit Name */
    public static final String DTL_NM_LB_NM_FOR_EXTN = "Business Unit Name";
    // END 2018/04/12 J.Kim [QC#22087,MOD]

    // START 2017/05/15 M.Naito
    /** Location : On-Site */
    public static final String ON_SITE = "On-Site";

    /** Value : 9 */
    public static final int VAL_9 = 9;
    // END 2017/05/15 M.Naito

    // START 2018/05/17 J.Kim [QC#25879,ADD]
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

    /** Index Number 10 */
    public static final int IDX_10 = 10;

    /** Index Number 12 */
    public static final int IDX_12 = 12;

    /** Index Number 20 */
    public static final int IDX_20 = 20;

    /** Index Number 30 */
    public static final int IDX_30 = 30;

    /** Percent */
    public static final String PERCENT = "%";

    /** Process Mode : 51 */
    public static final String PROC_MODE_51 = "51";
    // END 2018/05/17 J.Kim [QC#25879,ADD]
}
