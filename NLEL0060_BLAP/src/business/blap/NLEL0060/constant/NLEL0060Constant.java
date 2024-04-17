/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLEL0060.constant;

/**
 *<pre>
 * NLEL0060Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/04/15   Hitachi         J.Kim           Update          QC#7043
 * 2016/04/19   Hitachi         J.Kim           Update          QC#6774
 * 2016/07/21   Hitachi         Y.Tsuchimoto    Update          QC#11019
 * 2016/08/23   Fujitsu         C.Tanaka        Update          QC#11026
 * 2016/08/30   Fujitsu         C.Tanaka        Update          QC#13360
 * 2016/09/14   Fujitsu         C.Tanaka        Update          QC#12697
 * 2016/11/29   Fujitsu         T.Murai         Update          QC#15823
 * 2017/02/21   Hitachi         J.Kim           Update          QC#17589
 * 2017/05/15   CITS            M.Naito         Update          Merge DS Lv2
 * 2017/10/26   CITS            M.Naito         Update          QC#22052
 * 2017/11/07   Hitachi         J.Kim           Update          QC#16345
 * 2018/02/07   Hitachi         J.Kim           Update          QC#23890
 *</pre>
 */
public class NLEL0060Constant {

    /** Business ID */
    public static final String BIZ_ID = "NLEL0060";

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

    /** Fetch size */
    public static final int FETCH_SIZE = 1000;

    /** Download size */
    public static final int CSV_LIMIT_COUNT = 65536;

    /** CSV file name */
    public static final String CSV_NAME = "Depreciation Simulation";

    /** CSV extension */
    public static final String CSV = ".csv";

    // START 2016/07/21 Y.Tsuchimoto [QC#11019,ADD]
    /** YYYY length */
    public static final int YYYY_LENGTH = 4;

    // END 2016/07/21 Y.Tsuchimoto [QC#11019,ADD]

    /** xxCheckBox_A1 */
    public static final String CHECK_BOX_A = "xxChkBox_A1";

    /** xxCheckBox_B1 */
    public static final String CHECK_BOX_B = "xxChkBox_B1";

    /** xxCheckBox_C1 */
    public static final String CHECK_BOX_C = "xxChkBox_C1";

    /** xxPgFlg_A1 */
    public static final String PG_FLG_A = "xxPgFlg_A1";

    /** Comma */
    public static final String STR_COMMA = ".";

    /** COA length */
    public static final int COA_LEN = 9;

    /** CONST: NLEL0060_MDSE_TP_CTX_TP_CD */
    public static final String MDSE_TP_CTX_TP_CD = "NLEL0060_MDSE_TP_CTX_TP_CD";

    // START 2016/09/26 J.Kim [QC#13372,ADD]
    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    /** Date Format : MM/dd/yyyy HH:mm:ss */
    public static final String DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";

    // END 2016/09/26 J.Kim [QC#13372,ADD]

    // --------------------------------
    // Message
    // --------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    // START 2016/04/26 J.Kim [QC#7065,ADD]
    /** [@] field requires numeric input only. */
    public static final String ZZM9004E = "ZZM9004E";

    // END 2016/04/26 J.Kim [QC#7065,ADD]

    // START 2016/06/20 J.Kim [QC#10294,ADD]
    /** Numeric [@] field has an input error. */
    public static final String ZZM9033E = "ZZM9033E";

    // END 2016/06/20 J.Kim [QC#10294,ADD]

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NLZM2273E = "NLZM2273E";

    /** Data insert failure.(ReturnCode = [@]) */
    public static final String ZZZM9012E = "ZZZM9012E";

    /** Data update failure.(ReturnCode = [@]) */
    public static final String ZZZM9013E = "ZZZM9013E";

    /** Data delete failure.(ReturnCode = [@]) */
    public static final String ZZZM9014E = "ZZZM9014E";

    /** @ cannot be next or previous month. */
    public static final String NLEM0031E = "NLEM0031E";

    /** No change has been made. */
    public static final String NLEM0032E = "NLEM0032E";

    /**
     * The Asset has not been created and is still in the Pending
     * status.
     */
    public static final String NLEM0040E = "NLEM0040E";

    // START 2016/04/15 J.Kim [QC#7043,ADD]
    /** Please Enter range between '0' and 'NBV'. */
    public static final String NLEM0038E = "NLEM0038E";

    // END 2016/04/15 J.Kim [QC#7043,ADD]

    /**
     * It is being updated by another user. Please start again from a
     * search.
     */
    public static final String NLBM0009E = "NLBM0009E";

    /**
     * Parent Asset Number you entered doesn't exist in Asset list you
     * chose.
     */
    public static final String NLEM0035E = "NLEM0035E";

    /**
     * The merge list under same parent has to include pending status
     * Asset.
     */
    public static final String NLEM0041E = "NLEM0041E";

    /**
     * Parent Asset must be under activate status when the merge list
     * include pending and activate status Assets.
     */
    public static final String NLEM0042E = "NLEM0042E";

    /**
     * You cannot merge this Asset because it is not manually entered.
     */
    public static final String NLEM0043E = "NLEM0043E";

    /** @ must be more than @. */
    public static final String NLEM0044E = "NLEM0044E";

    /** Please check at least 1 checkbox. */
    public static final String NZZM0011E = "NZZM0011E";

    /** Your request cannot be processed under this status. */
    public static final String NLZM2283E = "NLZM2283E";

    /** Parent Asset must be under same Book Type. */
    public static final String NLEM0046E = "NLEM0046E";

    // START 2016/10/17 J.Kim [QC#13453,ADD]
    /** The format of [@] is incorrect. */
    public static final String NLEM0007E = "NLEM0007E";

    // END 2016/10/17 J.Kim [QC#13453,ADD]

    // START 2016/10/27 J.Kim [QC#11026,ADD]
    /** You cannot import this Asset because it is manually entered. */
    public static final String NLEM0047E = "NLEM0047E";

    // END 2016/10/27 J.Kim [QC#11026,ADD]

    // START 2017/05/15 M.Naito
    /** No change has been made. */
    public static final String NLAM0023E = "NLAM0023E";

    /**
     * The asset exists in Warehouse . Please process it from
     * "Inventory Request Entry" screen.
     */
    public static final String NLEM0049E = "NLEM0049E";

    /** Target data does not exist. */
    public static final String NLBM1063E = "NLBM1063E";

    /** The value you entered is incorrect. */
    public static final String NLXM1010E = "NLXM1010E";
    // END 2017/05/15 M.Naito

    // START 2017/10/26 M.Naito [QC#22052,ADD]
    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";
    // END 2017/10/26 M.Naito [QC#22052,ADD]

    /** Save */
    public static final String MSG_SAVE = "Save";

    /** Customer */
    public static final String MSG_CUSTOMER = "Customer";

    /** Item */
    public static final String MSG_ITEM = "Item";

    /** Vendor */
    public static final String MSG_VND = "Vendor";

    /** Warehouse */
    public static final String MSG_WH = "Warehouse";

    /** Location */
    public static final String MSG_LOC = "Location";

    /** Asset For Depreciation */
    public static final String MSG_ASSET_FOR_DEPC = "Asset For Depreciation";

    /** Search Option Name */
    public static final String SRCH_OPT_NM = "Search Option Name";

    /** Retire Date */
    public static final String ACCT_YR_MTH = "Retire Date";

    /** Parent Asset */
    public static final String MSG_PRNT_DS_ASSET_MSTR_PK = "Parent Asset";

    /** Life in Months */
    public static final String MSG_DEPC_MTH_NUM = "Life in Months";

    /** Depreciation Count */
    public static final String MSG_DEPC_CNT = "Depreciation Count";

    /** Vendor Code */
    public static final String MSG_VND_CD = "Vendor Code";

    /** MDSE Type */
    public static final String MSG_MDSE_TP_CD = "Item Type";

    /** Customer Name */
    public static final String MSG_DS_ACCT_NM = "Customer Name";

    /** Order Number */
    public static final String MSG_CPO_ORD_NUM = "Order Number";

    /** Order Line Number */
    public static final String MSG_CPO_ORD_LINE_NUM = "Order Line Number";

    // --------------------------------
    // Table
    // --------------------------------
    /** Table: DS_ASSET_MSTR */
    public static final String TBL_DS_ASSET_MSTR = "DS_ASSET_MSTR";

    /** Column: PRNT_DS_ASSET_MSTR_PK */
    public static final String COL_PRNT_DS_ASSET_MSTR_PK = "PRNT_DS_ASSET_MSTR_PK";

    /** Column: DS_ASSET_MSTR_PK */
    public static final String COL_DS_ASSET_MSTR_PK = "DS_ASSET_MSTR_PK";

    /** Column: CUR_VAL_AMT */
    public static final String COL_CUR_VAL_AMT = "CUR_VAL_AMT";

    /** Column: DEPC_CNT */
    public static final String COL_DEPC_CNT = "DEPC_CNT";

    /** Column: LAST_DEPC_YR_MTH */
    public static final String COL_LAST_DEPC_YR_MTH = "LAST_DEPC_YR_MTH";

    /** Column: RSDL_VAL_AMT */
    // START 2017/02/21 J.Kim [QC#17589,MOD]
    // public static final String COL_RSDL_VAL_AMT = "RSDL_VAL_AM";
    public static final String COL_RSDL_VAL_AMT = "RSDL_VAL_AMT";

    // END 2017/02/21 J.Kim [QC#17589,MOD]

    /** Column: DEPC_MTH_NUM */
    public static final String COL_DEPC_MTH_NUM = "DEPC_MTH_NUM";

    // START 2017/05/15 M.Naito
    /** Column: CPO_DTL_LINE_NUM */
    public static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** Column: CPO_DTL_LINE_SUB_NUM */
    public static final String CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";

    // END 2017/05/15 M.Naito

    // START 2017/11/01 J.Kim [QC#16345,ADD]
    /** Column _CMPY_CD */
    public static final String CMPY_CD = "_CMPY_CD";

    /** Column _BR_CD */
    public static final String BR_CD = "_BR_CD";

    /** Column _CC_CD */
    public static final String CC_CD = "_CC_CD";

    /** Column _ACCT_CD */
    public static final String ACCT_CD = "_ACCT_CD";

    /** Column _PROD_CD */
    public static final String PROD_CD = "_PROD_CD";

    /** Column _CH_CD */
    public static final String CH_CD = "_CH_CD";

    /** Column _AFFL_CD */
    public static final String AFFL_CD = "_AFFL_CD";

    /** Column PROJ_CD */
    public static final String PROJ_CD = "_PROJ_CD";

    /** Column _EXTN_CD */
    public static final String EXTN_CD = "_EXTN_CD";

    // END 2017/11/01 J.Kim [QC#16345,ADD]

    // --------------------------------
    // Code
    // --------------------------------
    /** DEPC_SMLTN_RQST_STS_CD: NE */
    public static final String DEPC_SMLTN_RQST_STS_CD_NE = "NE";

    /** Location : On-Site */
    public static final String ON_SITE = "On-Site";

    // START 2017/02/21 J.Kim [QC#17589,ADD]
    /** Depreciation Mode */
    public static final String MODE_DEPC = "10";
    // END 2017/02/21 J.Kim [QC#17589,ADD]

    // START 2017/11/01 J.Kim [QC#16345,ADD]
    /** Default Expense COA */
    public static final String DEF_EXP_COA = "DEF_EXP_COA";
    // END 2017/11/01 J.Kim [QC#16345,ADD]

    // START 2018/02/06 J.Kim [QC#23890,ADD]
    /** Process Mode : 51 */
    public static final String PROC_MODE_51 = "51";
    // END 2018/02/06 J.Kim [QC#23890,ADD]

    // START 2018/06/20 J.Kim [QC#24936,ADD]
    /** Process Mode : 11 */
    public static final String PROC_MODE_11 = "11";

    /** Location : Sold */
    public static final String SOLD = "Sold";
    // END 2018/06/20 J.Kim [QC#24936,ADD]
}
