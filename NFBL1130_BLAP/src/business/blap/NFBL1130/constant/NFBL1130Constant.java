/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL1130.constant;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Accrual write of Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/28   Hitachi         K.Kasai         Update          QC#14130
 * 2016/09/29   Hitachi         K.Kojima        Update          QC#14178
 * 2016/10/06   Hitachi         K.Kojima        Update          QC#11613
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 * 2019/02/26   Fujitsu         S.Ohki          Update          QC#30505
 * 2021/03/12   CITS            H.Dimay         Update          QC#57040
 * 2022/03/28   Hitachi         A.Kohinata      Update          QC#57935(56588)
 * 2022/04/05   Hitachi         R.Onozuka       Update          QC#57935
 * 2022/08/05   Hitachi         A.Kohinata      Update          QC#59245
 * </pre>
 */
public interface NFBL1130Constant {

    /** Business ID */
    static final String BIZ_ID = "NFBL1130";

    /** Empty Value */
    static final String EMPTY_STRING = "";
    /** yyyyMMdd */
    static final String YYYYMMDD = "yyyyMMdd";
    /** yyyyMMddHHmmSSsss */
    static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmSSss";
    
    /** AP_VND_INV_SQ_NUM 00 */
    static final String AP_VND_INV_SQ_NUM_00 = "00";
    /** AP_INV_TP_CD 00 */
    static final String AP_INV_TP_CD_00 = "00";

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
    /** Table : A (LINES tab) */
    public static final String TABLE_A = "A";

     // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String ACRL_COA_ACCT_CD = "ACRL_COA_ACCT_CD";
    /** DB Item Column Name */
    static final String XX_DPLY_BY_ITEM_NM = "XX_DPLY_BY_ITEM_NM";
    /** DB Item Column Name */
    static final String CM_ACRL_WRITE_OFF_PK = "CM_ACRL_WRITE_OFF_PK"; // CM_ACRL_WRITE_OFF_PK
    /** DB Item Column Name */
    static final String PO_NUM = "PO_NUM"; // PO #
    /** DB Item Column Name */
    static final String EZUPTIME = "EZUPTIME";
    /** DB Item Column Name */
    static final String EZUPTIMEZONE = "EZUPTIMEZONE";
    /** DB Item Column Name */
    static final String DELY_ORD_NUM = "DELY_ORD_NUM"; // Receipt #
    /** DB Item Column Name */
    static final String STK_IN_DT = "STK_IN_DT"; // Receipt Date
    // START 2016/09/29 K.Kojima [QC#14178,ADD]
    /** DB Item Column Name */
    static final String RWS_NUM = "RWS_NUM";
    // END 2016/09/29 K.Kojima [QC#14178,ADD]
    /** DB Item Column Name */
    static final String AP_VND_CD = "AP_VND_CD";
    /** DB Item Column Name */
    static final String PRNT_VND_NM = "PRNT_VND_NM";
    // START 2016/09/30 K.Kojima [QC#14179,ADD]
    /** DB Item Column Name */
    static final String PRNT_VND_CD = "PRNT_VND_CD";
    /** DB Item Column Name */
    static final String DPLY_VND_NM = "DPLY_VND_NM";
    // END 2016/09/30 K.Kojima [QC#14179,ADD]
    /** DB Item Column Name */
    static final String AP_VND_INV_SQ_NUM = "AP_VND_INV_SQ_NUM";
    /** DB Item Column Name */
    static final String INV_RCV_QTY = "INV_RCV_QTY";
    /** DB Item Column Name */
    static final String THIS_MTH_FOB_COST_AMT = "THIS_MTH_FOB_COST_AMT";
    /** DB Item Column Name */
    // START 2018/10/17 J.Kim [QC#23037,MOD]
    // static final String AC_INV_JRNL_COST_AMT = "AC_INV_JRNL_COST_AMT";
    static final String AC_INV_JRNL_COST_DR_AMT = "AC_INV_JRNL_COST_DR_AMT";
    /** DB Item Column Name */
    static final String AC_INV_JRNL_COST_CR_AMT = "AC_INV_JRNL_COST_CR_AMT";
    // END 2018/10/17 J.Kim [QC#23037,MOD]

    // START 2016/09/28 [QC#14130, ADD]
    /** DB Item Column Name */
    static final String ENT_DEAL_NET_UNIT_PRC_AMT = "ENT_DEAL_NET_UNIT_PRC_AMT";
    // END 2016/09/28 [QC#14130, ADD]

    // START 2019/02/26 [QC#30505, ADD]
    /** DB Item Column Name */
    static final String ENT_DEAL_NET_UNIT_PRC_AMT_DR = "ENT_DEAL_NET_UNIT_PRC_AMT_DR";
    /** DB Item Column Name */
    static final String RECEIPT_AMT = "RECEIPT_AMT";
    // END 2019/02/26 [QC#30505, ADD]

    /** DB Item Column Name */
    static final String COA_ACCT_CD = "COA_ACCT_CD";
    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD";
    // START 2016/10/06 K.Kojima [QC#11613,MOD]
    /** DB Item Column Name */
    // static final String MDSE_NM = "MDSE_NM";
    static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";
    // END 2016/10/06 K.Kojima [QC#11613,MOD]
    // add start 2022/03/28 QC#57935(56588)
    /** DB Item Column Name */
    static final String INV_MDSE_CD = "INV_MDSE_CD";
    /** DB Item Column Name */
    static final String INV_MDSE_DESC_SHORT_TXT = "INV_MDSE_DESC_SHORT_TXT";
    // add end 2022/03/28 QC#57935(56588)
    /** DB Item Column Name */
    static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";
    /** DB Item Column Name */
    static final String INV_DT = "INV_DT";
    /** DB Item Column Name */
    static final String INV_QTY = "INV_QTY";
    /** DB Item Column Name */
    static final String WRT_OFF_FLG = "WRT_OFF_FLG";
    /** DB Item Column Name */
    static final String WRT_OFF_DT = "WRT_OFF_DT";
    /** DB Item Column Name */
    static final String ACRL_WRT_OFF_RSN_CD = "ACRL_WRT_OFF_RSN_CD";
    /** DB Item Column Name */
    static final String ACRL_WRT_OFF_RSN_NM = "ACRL_WRT_OFF_RSN_NM";
    /** DB Item Column Name */
    static final String ACRL_WRT_OFF_CMNT_TXT = "ACRL_WRT_OFF_CMNT_TXT";
    // START 2018/11/13 J.Kim [QC#23037,ADD]
    /** DB Item Column Name */
    static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";
    // END 2018/11/13 J.Kim [QC#23037,ADD]
    // START 2022/04/05 R.Onozuka [QC357935, ADD]
    /** DB Item Column Name */
    static final String XX_CMNT_TXT = "XX_CMNT_TXT";
    /** DB Item Column Name */
    static final String AJE_CM_INTFC_TP_CD = "AJE_CM_INTFC_TP_CD";
    /** DB Item Column Name */
    static final String INV_UNIT_PRC_AMT = "INV_UNIT_PRC_AMT";
    /** DB Item Column Name */
    static final String AC_OC_TOT_INV_AMT = "AC_OC_TOT_INV_AMT";
    // END 2022/04/05 R.Onozuka [QC357935, ADD]
    // add start 2022/08/05 QC#59245
    /** DB Item Column Name */
    static final String RCV_TOT_AMT = "RCV_TOT_AMT";
    // add end 2022/08/05 QC#59245

    // ** SSM Statement ID's Fixed Value ** //
    /** SSM Statement ID */
    static final String SELECT_CM_AP_INV_IMPT_DTL_BY_PARTIAL_KEY = "SELECT_CM_AP_INV_IMPT_DTL_BY_PARTIAL_KEY";
    
    // ** Message ID ** //
    /** Please check at least 1 checkbox. */
    static final String NFAM0075E = "NFAM0075E";
    /** Please check at least 1 @ checkbox. */
    static final String NFBM0017E = "NFBM0017E";
    /** Unexpected Error Occurred */
    static final String NFBM0028E = "NFBM0028E";
    /** @ is invalid. */
    static final String NFBM0041E = "NFBM0041E";
    /** Record is not found. */
    static final String NFBM0069E = "NFBM0069E";
    /**
     * Showing only first @ of total @ records. Please review your
     * search criteria.
     */
    static final String NFBM0001W = "NFBM0001W";
    /** Please enter the value. */
    static final String NFCM0038E = "NFCM0038E";
    /** The invoice data has been updated by another user.  Please search again. */
    static final String NFCM0079E = "NFCM0079E";
    /** The process has been successfully completed. */
    static final String NZZM0002I = "NZZM0002I";
    /**
     * The search ended normally.
     */
    static final String ZZM8002I = "ZZM8002I";
    /** Process ended normally. */
    static final String ZZM8100I = "ZZM8100I";
    /**
     * The process has been successfully completed.
     */
    static final String AZZM0002I = "AZZM0002I";

    // ** CSV Download ** //
    /** File name for csv download */
    static final String CSV_FILE_NAME = "AP Accrual write of Serach";
    /** File extension for CSV download */
    static final String CSV_EXT = ".csv";
    /** Column names for csv download */
    static final String[] CSV_HEADER = {
        "PO#",
        "Receipt#", 
        "Receipt Date", 
        // START 2016/09/29 K.Kojima [QC#14178,ADD]
        "RWS Number",
        // DEL 2016/09/29 K.Kojima [QC#14178,ADD]
        // START 2017/12/22 [QC#22831, ADD]
        "Supplier Number", 
        // END   2017/12/22 [QC#22831, ADD]
        "Supplier Name", 
        "Line", 
        // START 2018/11/13 J.Kim [QC#23037, DEL]
        //"UoM",
        // END 2018/11/13 J.Kim [QC#23037, DEL]
        "Receipt Qty", 
        "Unit Price", 
        "Receipt Amount", 
        "Account Accrual", 
        // mod start 2022/03/28 QC#57935(56588)
        //"Item", 
        //"Description", 
        "PO Item", 
        "PO Description", 
        "Invoice Item", 
        "Invoice Description", 
        // mod end 2022/03/28 QC#57935(56588)
        "Payable Invoice", 
        "Invoice Date", 
        "Invoice Qty", 
        "Invoice Price", 
        "Invoice Amt", 
        "Write off", 
        "Write off Date", 
        "Write off Reason", 
        "Remarks"
        // START 2022/04/05 R.Onozuka [QC357935, ADD]
        ,"Account Code"
        // END 2022/04/05 R.Onozuka [QC357935, ADD]
    };

    // ** Header RowNum Count **//
    static final int ROW_NUM_MAX = 1000;

    // START 2018/11/08 J.Kim [QC#23037,ADD]
    /** DR_CR_TP_CREDIT */
    public static final String DR_CR_TP_CREDIT = "C";

    /** DR_CR_TP_DEBIT */
    public static final String DR_CR_TP_DEBIT = "D";
    // END 2018/11/08 J.Kim [QC#23037,ADD]

    // START 2021/03/12 H.Dimay [QC#57040,ADD]
    /** VAR_CHAR_CONST Name NFBL1130_COA_ACCT_CD */
    public static final String VAR_CHAR_CONST_NFBL1130_COA_ACCT_CD_LIST = "NFBL1130_COA_ACCT_CD";

    /** Default COA_ACCT_CD value */
    public static final String DEFAULT_COA_ACCT_CD = "21431001";

    /** Array Delimiter */
    public static final String ARRAY_DELIMITER = ",";

    /** Constant: For value initialization */
    public static final String CONST_INIT_VAL = "";
    // END 2021/03/12 H.Dimay [QC#57040,ADD]
    // START 2022/04/05 R.Onozuka [QC#57935, ADD]
    /** CM_ACRL_WRITE_OFF_SQ */
    public static final String CM_ACRL_WRITE_OFF_SQ = "CM_ACRL_WRITE_OFF_SQ";
    
    /** DEF_ACRL_WRT_OFF_COA */
    public static final String DEF_ACRL_WRT_OFF_COA = "DEF_ACRL_WRT_OFF_COA";
    
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
    
    /** Comma */
    public static final String STR_COMMA = ".";
    
    /** DR */
    public static final String DR = "D";
    
    /** CR */
    public static final String CR = "C";
    
    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";
    // END 2022/04/05 R.Onozuka [QC#57935, ADD]
}
