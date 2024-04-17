/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL1120.constant;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Invoice Maintenance Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/13   Hitachi         K.Kojima        Update          QC#12725
 * 2017/03/16   Hitachi         E.Kameishi      Update          QC#14205
 * </pre>
 */
public interface NFBL1120Constant {

    /** Business ID */
    static final String BIZ_ID = "NFBL1120";

    /** You can not [@] this record Because of [@] already exists. */
    static final String NFBM0226E = "NFBM0226E";

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
    static final String AP_BAT_NUM = "AP_BAT_NUM";
    /** DB Item Column Name */
    static final String AP_BAT_DT = "AP_BAT_DT";
    /** DB Item Column Name */
    static final String AP_VND_CD = "AP_VND_CD";
    /** DB Item Column Name */
    static final String PRNT_VND_CD = "PRNT_VND_CD";
    /** DB Item Column Name */
    static final String PRNT_VND_NM = "PRNT_VND_NM";
    /** DB Item Column Name */
    static final String VND_SITE_NM = "VND_SITE_NM";
    /** DB Item Column Name */
    static final String AP_MAINT_INV_STS_CD = "AP_MAINT_INV_STS_CD";
    /** DB Item Column Name */
    static final String AP_MAINT_INV_STS_DESC_TXT = "AP_MAINT_INV_STS_DESC_TXT";
    /** DB Item Column Name */
    static final String APVR_USR_ID = "APVR_USR_ID";
    /** DB Item Column Name */
    static final String APVR_USR_NM = "APVR_USR_NM";
    /** DB Item Column Name */
    static final String AP_INV_NUM = "AP_INV_NUM";
    /** DB Item Column Name */
    static final String INV_DT = "INV_DT";
    /** DB Item Column Name */
    static final String AP_INV_AMT = "AP_INV_AMT";
    //START 2017/03/16 E.Kameishi [QC#14205, ADD]
    /** DB Item Column Name */
    static final String XX_REC_HIST_CRAT_TS = "XX_REC_HIST_CRAT_TS";
    /** DB Item Column Name */
    static final String XX_REC_HIST_CRAT_BY_NM = "XX_REC_HIST_CRAT_BY_NM";
    /** DB Item Column Name */
    static final String XX_REC_HIST_UPD_TS = "XX_REC_HIST_UPD_TS";
    /** DB Item Column Name */
    static final String XX_REC_HIST_UPD_BY_NM = "XX_REC_HIST_UPD_BY_NM";
    /** DB Item Column Name */
    static final String XX_REC_HIST_TBL_NM = "XX_REC_HIST_TBL_NM";
    //END 2017/03/16 E.Kameishi [QC#14205, ADD]

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
        "Supplier#", 
        "Supplier Name", 
        "Line", 
        "UoM", 
        "Receipt Qty", 
        "Unit Price", 
        "Receipt Amount", 
        "Account Accrual", 
        "Item", 
        "Description", 
        "Payable Invoice", 
        "Invoice Date", 
        "Invoice Qty", 
        "Invoice Price", 
        "Invoice Amt", 
        "Write off", 
        "Write off Date", 
        "Write off Reason", 
        "Remarks"
    };

    // ** Header RowNum Count **//
    static final int ROW_NUM_MAX = 1000;

    // START 2016/09/13 K.Kojima [QC#12725,ADD]
    /** Var Char Const Name */
    static final String VAR_CHAR_NFBL1120_LIST_CD = "NFBL1120_LIST_CD";
    // END 2016/09/13 K.Kojima [QC#12725,ADD]
}
