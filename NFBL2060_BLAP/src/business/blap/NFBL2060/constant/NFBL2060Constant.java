/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2060.constant;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Invoice Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/26   Hitachi         T.Tsuchida      Update          QC#12239
 * 2016/07/29   Hitachi         J.Kim           Update          QC#12554
 * 2016/08/30   Fujitsu         C.Tanaka        Update          QC#5521
 * 2016/09/30   Hitachi         T.Tsuchida      Update          QC#14498
 * 2016/10/05   Hitachi         T.Tsuchida      Update          QC#13960
 * 2017/07/04   CITS            M.Naito         Update          QC#19678
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 * 2017/12/25   Hitachi         Y.Takeno        Update          QC#22831
 * 2018/03/26   Hitachi         Y.Takeno        Update          QC#22350
 * 2018/05/28   CITS            K.Ogino         Update          QC#25902
 * 2020/03/03   Fujitsu         H.Ikeda         Update          QC#53413
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 * </pre>
 */
public interface NFBL2060Constant {

    /** Business ID */
    public static final String BIZ_ID = "NFBL2060";

    /** Empty Value */
    static final String EMPTY_STRING = "";
    /** NONE */
    static final String NONE = "NONE";
    /** yyyyMMdd */
    static final String YYYYMMDD = "yyyyMMdd";
    /** yyyyMMddHHmmSSsss */
    static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmSSss";
    
    /** AP_VND_INV_SQ_NUM 00 */
    static final String AP_VND_INV_SQ_NUM_00 = "00";

    // START 2020/03/03 [QC#53413, ADD]
    // mod start 2022/02/15 QC#57090
    //static final String MULTI_PO_NUM_ITEM = "*";
    static final String MULTI_ITEM = "*";
    // mod end 2022/02/15 QC#57090
    // END   2020/03/03 [QC#53413, ADD]

    // Tab
    /** Tab anchor Detailed */
    public static final String TAB_DETAILED = "Detailed";
    /** Tab anchor Summary */
    public static final String TAB_SUMMARY = "Summary";

     // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String AP_INV_CATG_CD = "AP_INV_CATG_CD";
    /** DB Item Column Name */
    static final String AP_INV_CATG_DESC_TXT = "AP_INV_CATG_DESC_TXT";
    /** DB Item Column Name */
    static final String AP_VND_CD = "AP_VND_CD";
    /** DB Item Column Name */
    static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";
    /** DB Item Column Name */
    static final String AP_VND_INV_SQ_NUM = "AP_VND_INV_SQ_NUM";
    /** DB Item Column Name */
    static final String VND_REF_NUM = "VND_REF_NUM";
    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD";
    /** DB Item Column Name */
    static final String CM_LIC_NUM = "CM_LIC_NUM";
    /** DB Item Column Name */
    static final String REC_CNT = "REC_CNT";
    
    // ** SSM Statement ID's Fixed Value ** //
    /** SSM Statement ID */
    static final String SELECT_CM_AP_INV_IMPT_DTL_BY_PARTIAL_KEY = "SELECT_CM_AP_INV_IMPT_DTL_BY_PARTIAL_KEY";
    
    // ** Message ID ** //
    /** Unexpected Error Occurred */
    static final String NFBM0028E = "NFBM0028E";
    /** @ is invalid. */
    static final String NFBM0041E = "NFBM0041E";
    /** Record is not found. */
    static final String NFBM0069E = "NFBM0069E";
    /**
     * Another user has already updated target record. Please search
     * again.
     */
    static final String NFBM0155E = "NFBM0155E";
    /**
     * There are too many search results, there is data that cannot be displayed.
     */
    static final String NZZM0001W = "NZZM0001W";
    /**
     * The search ended normally.
     */
    static final String ZZM8002I = "ZZM8002I";
    /**
     * The process has been successfully completed.
     */
    static final String AZZM0002I = "AZZM0002I";
    /** You can not [@] this record Because of [@] already exists. */
    public static final String NFCM0865E = "NFCM0865E";

    // ** CSV Download ** //
    /** File name for excel download (Detailed tab)*/
    static final String FILE_NAME_DETAILED = "Invoice Inquiry(Detailed)";
    /** File name for excel download (Summary tab)*/
    static final String FILE_NAME_SUMMARY = "Invoice Inquiry(Summary)";
    /** File extension for CSV download */
    static final String CSV_EXT = ".csv";
    /** Column names for excel download */
    static final String[] CSV_HEADER_DETAILED = {
            "Source",
            "Invoice Number",
            "Invoice Date",
            // START 2018/10/18 J.Kim [QC#28816,ADD]
            "Linked To ARCS Date",
            // END 2018/10/18 J.Kim [QC#28816,ADD]
            // START 2017/12/22 [QC#22831, MOD]
            "Supplier Number",
            "Supplier Name",
            "Supplier Site Code",
            // END   2017/12/22 [QC#22831, MOD]
            "Terms",
            "Invoice Amount",
            "Header Description",
            "Status",
            "PO Number",
            "PO Date",
            // QC#25902
            "Vendor Return Number",
            "Vendor Return Date",
            "Receipt Number",
            // START 2018/03/26 [QC#22350, MOD]
            "Order Line #",
            // QC#25902 End
            "Invoice Line #",
            // END   2018/03/26 [QC#22350, MOD]
            "Line Type",
            "Line Description",
            "Line Amount",
            "Line Tax",
            "Account Descripton",
            "Charge Account",
            "Order Quantity",
            "Received Quantity",
            "Billed Quantity",
            "Payment Number",
            "Payment Date",
            "Contract",
            "Dealer Code",
            "Serial Number",
            "CSMP Invoice",
            "Install Loc"
    };
    /** Column names for excel download */
    static final String[] CSV_HEADER_SUMMARY = {
        "Source",
        "Invoice Number",
        "Invoice Date",
        // START 2018/10/18 J.Kim [QC#28816,ADD]
        "Linked To ARCS Date",
        // END 2018/10/18 J.Kim [QC#28816,ADD]
        // START 2017/12/25 [QC#22831, MOD]
        "Supplier Number",
        "Supplier Name",
        "Supplier Site Code",
        // END   2017/12/25 [QC#22831, MOD]
        "Invoice Amount",
        "Header Description",
        "Status",
        "PO Number",
        "PO Date",
        // QC#25902
        "Vendor Return Number",
        "Vendor Return Date",
        "Order Amount",
        // QC#25902 End
        "Payment Number",
        "Payment Date",
        "Terms"
    };
    
    // ** Header RowNum Count **//
    static final int ROW_NUM_MAX = 1000;
}
