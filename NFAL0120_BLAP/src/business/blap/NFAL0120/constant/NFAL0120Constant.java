/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0120.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/26   Hitachi         Y.Tsuchimoto    Update          QC#11379
 * 2016/08/25   Fujitsu         S.Fujita        Update          QC#4105
 * 2017/10/03   CITS            Y.Fujii         Update          QC#19408
 * 2018/06/22   CITS            S.Katsuma       Update          QC#24025
 * 2018/07/11   CITS            S.Katsuma       Update          QC#25710
 *</pre>
 */
public interface NFAL0120Constant {

    /** Constant Val */
    static final String BLANK = "";

    /** Constant Val */
    static final String DEBIT_STR = "D";

    /** Constant Val */
    static final String CREDIT_STR = "C";

    /** Constant Val */
    static final String DEBIT_NUM_STR = "1";

    /** Constant Val */
    static final String CREDIT_NUM_STR = "2";

    /** Constant Val */
    static final int DEBIT_INT = 1;

    /** Constant Val */
    static final int CREDIT_INT = 2;

    /** Y/N */
    static final String YES = ZYPConstant.FLG_ON_Y;

    /** Y/N */
    static final String NO = ZYPConstant.FLG_OFF_N;

    /** File name for excel download */
    static final String FILE_NAME_NORMAL = "JRNL_ENTRY_INQUIRY";

    /** File name for excel download */
    static final String FILE_NAME_AR_RECLASS = "AR_RECLASS";

    /** File extension for CSV download */
    static final String CSV_EXT = ".csv";

    /** Date Format yyyyMMdd */
    static final String YYYYMMDD = "yyyyMMdd";

    /** Date Format yyyyMMdd */
    static final String MM_DD_YYYY = "MM/dd/yyyy";

    /** Fixed Value : Each Bulk Insert Count */
    static final int BULK_INSERT_COUNT = 10000;

    /** Fixed Value : Max value of record count */
    static final long RECORD_MAX_LIMIT = 100000;

    /** Transaction Code for reclass */
    static final String TRX_CD_RECLASS = "190";

    /** Transaction Reason Code for reclass */
    static final String[] TRX_RSN_CD_RECLASS = {"08", "09", "10", "11", "12", "13" };

    /** Transaction Code for A/R reclass */
    static final String TRX_CD_AR_RECLASS = "170";

    /** Transaction Code for A/R reclass */
    static final String TRX_RSN_CD_AR_RECLASS = "01";

    /** Max row count */
    static final int MAX_ROW_CNT = 30000;
    
    /** Error Message (Unique Constraint Violation) */
    public static final String ZZBM0074E = "ZZBM0074E";

    // START 2016/07/26 Y.Tsuchimoto [QC#11379,MOD]
    /** Column names for excel download */
    static final String[] EXCEL_HEADER_NORMAL = 
            {"COA Account String",
             "GL Date", // GL_DT
            "GL Send", // GL Send
            "Journal Source", // JRNL_SRC_NM
            "Journal Category", // JRNL_CATG_NM
            "Debit Amt", // JRNL_FUNC_DR_AMT
            "Credit Amt", // JRNL_FUNC_CR_AMT
            "AJE ID", // AJE_ID
            // START 2018/07/11 S.Katsuma [QC#25710,DEL]
//            "Qty",    // Qty
            // END 2018/07/11 S.Katsuma [QC#25710,DEL]
            // START 2016/08/25 S.Fujita [QC#4105,MOD]
//            "TOC Code", // TOC_CD
            "Sales Rep", // PSN_NUM
            // END   2016/08/25 S.Fujita [QC#4105,MOD]
            "Cust Acct", //
            "Vendor", // VND_CD
            "Invoice", // AJE_INV_NUM
            "Item ID", // AJE_ITEM_CD
            // START 2018/06/22 S.Katsuma [QC#24025,ADD]
            "Item Name", // AJE_ITEM_DESC_TXT
            // END 2018/06/22 S.Katsuma [QC#24025,ADD]
            "Ser Num",
            "Order Num",
            "PO Num",
            "SO Num", // SO_NUM
            "Rcpt Num",
            "Asset Num",
            "Inventory Trx",
            "Install Base ID",
            // START 2019/08/20 S.Takami [QC#51897,ADD]
            "Configuration#",
            // END 2019/08/20 S.Takami [QC#51897,ADD]
            // START 2018/07/11 S.Katsuma [QC#25710,DEL]
//            "Man Jrnl Entry PK",
            // END 2018/07/11 S.Katsuma [QC#25710,DEL]
            "Ref# 1",
            "Ref# 2",
            "Ref# 3",
            "Ref# 4",
            "Ref# 5"
    };
    // END   2016/07/26 Y.Tsuchimoto [QC#11379,MOD]

    /** Column names for excel download */
    static final String[] EXCEL_HEADER_AR_RECLASS = {"Company", // DR_COA_CMPY_CD
            "Branch", // DR_COA_BR_CD
            "Cost Center", // DR_COA_CC_CD
            "Account", // DR_COA_ACCT_CD
            "Product", // DR_COA_PROD_CD
            "Channel", // DR_COA_CH_CD
            "Affiliate", // DR_COA_AFFL_CD
            "Project", // DR_COA_PROJ_CD
            "Other", // DR_COA_EXTN_CD
            "Debit Amount", // JRNL_FUNC_DR_AMT
            "Credit Amount" // JRNL_FUNC_CR_AMT
    };

    /** DB Item Column Name */
    static final String JRNL_ENTRY_PK = "JRNL_ENTRY_PK";

    /** DB Item Column Name */
    static final String GL_DT = "GL_DT";

    /** DB Item Column Name */
    static final String PROC_DT = "PROC_DT";

    /** DB Item Column Name */
    static final String AJE_ID = "AJE_ID";

    /** DB Item Column Name */
    static final String JRNL_SRC_NM = "JRNL_SRC_NM";

    /** DB Item Column Name */
    static final String JRNL_CATG_NM = "JRNL_CATG_NM";

    /** DB Item Column Name */
    static final String DR_OR_CR = "DR_OR_CR";

    /** DB Item Column Name */
    static final String COA_ACCT_CD = "COA_ACCT_CD";

    /** DB Item Column Name */
    static final String COA_CMPY_CD = "COA_CMPY_CD";

    /** DB Item Column Name */
    static final String COA_BR_CD = "COA_BR_CD";

    /** DB Item Column Name */
    static final String COA_CC_CD = "COA_CC_CD";

    /** DB Item Column Name */
    static final String COA_PROD_CD_DC = "COA_PROD_CD_DC";

    /** DB Item Column Name */
    static final String COA_CH_CD = "COA_CH_CD";

    /** DB Item Column Name */
    static final String COA_AFFL_CD = "COA_AFFL_CD";

    /** DB Item Column Name */
    static final String COA_PROJ_CD = "COA_PROJ_CD";

    /** DB Item Column Name */
    static final String COA_EXTN_CD = "COA_EXTN_CD";

    /** DB Item Column Name */
    static final String JRNL_FUNC_DR_AMT = "JRNL_FUNC_DR_AMT";

    /** DB Item Column Name */
    static final String JRNL_FUNC_CR_AMT = "JRNL_FUNC_CR_AMT";

    /** DB Item Column Name */
    static final String COA_PROD_CD = "COA_PROD_CD";

    /** DB Item Column Name */
    static final String TOC_CD = "TOC_CD";

    /** DB Item Column Name */
    static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** DB Item Column Name */
    static final String VND_CD = "VND_CD";

    /** DB Item Column Name */
    static final String AJE_INV_NUM = "AJE_INV_NUM";

    /** DB Item Column Name */
    static final String PRMO_PK = "PRMO_PK";

    /** DB Item Column Name */
    static final String AJE_ITEM_CD = "AJE_ITEM_CD";

    /** DB Item Column Name */
    static final String SO_NUM = "SO_NUM";

    /** ************ Debit search criteria ******************* */
    /** DB Item Column Name for DR */
    static final String DR_COA_CMPY_CD = "DR_COA_CMPY_CD";

    /** DB Item Column Name for DR */
    static final String DR_COA_BR_CD = "DR_COA_BR_CD";

    /** DB Item Column Name for DR */
    static final String DR_COA_CC_CD = "DR_COA_CC_CD";

    /** DB Item Column Name for DR */
    static final String DR_COA_ACCT_CD = "DR_COA_ACCT_CD";

    /** DB Item Column Name for DR */
    static final String DR_COA_PROD_CD = "DR_COA_PROD_CD";

    /** DB Item Column Name for DR */
    static final String DR_COA_CH_CD = "DR_COA_CH_CD";

    /** DB Item Column Name for DR */
    static final String DR_COA_AFFL_CD = "DR_COA_AFFL_CD";

    /** DB Item Column Name for DR */
    static final String DR_COA_PROJ_CD = "DR_COA_PROJ_CD";

    /** DB Item Column Name for DR */
    static final String DR_COA_EXTN_CD = "DR_COA_EXTN_CD";

    /** ************ Credit search criteria ******************* */
    /** DB Item Column Name for DR */
    static final String CR_COA_CMPY_CD = "CR_COA_CMPY_CD";

    /** DB Item Column Name for CR */
    static final String CR_COA_BR_CD = "CR_COA_BR_CD";

    /** DB Item Column Name for CR */
    static final String CR_COA_CC_CD = "CR_COA_CC_CD";

    /** DB Item Column Name for CR */
    static final String CR_COA_ACCT_CD = "CR_COA_ACCT_CD";

    /** DB Item Column Name for CR */
    static final String CR_COA_PROD_CD = "CR_COA_PROD_CD";

    /** DB Item Column Name for CR */
    static final String CR_COA_CH_CD = "CR_COA_CH_CD";

    /** DB Item Column Name for CR */
    static final String CR_COA_AFFL_CD = "CR_COA_AFFL_CD";

    /** DB Item Column Name for CR */
    static final String CR_COA_PROJ_CD = "CR_COA_PROJ_CD";

    /** DB Item Column Name for CR */
    static final String CR_COA_EXTN_CD = "CR_COA_EXTN_CD";

    /** ************ Special fields ******************* */
    /** DB Item Column Name for DR */
    static final String DR_TOT_AMT = "DR_TOT_AMT";

    /** DB Item Column Name for CR */
    static final String CR_TOT_AMT = "CR_TOT_AMT";

    /** DB Item Column Name for DR */
    static final String DR_TOT_COUNT = "DR_TOT_COUNT";

    /** DB Item Column Name for CR */
    static final String CR_TOT_COUNT = "CR_TOT_COUNT";

    /** Map Name */
    static final String COA_ACCT = "coaAcctCd";

    /** Map Name */
    static final String COA_PROD = "coaProdCd";

    /** Map Name */
    static final String COA_BR = "coaBrCd";

    /** Map Name */
    static final String COA_CC = "coaCcCd";

    /** Map Name */
    static final String COA_CH = "coaChCd";

    /** Map Name */
    static final String COA_AFFL = "coaAfflCd";

    /** Map Name */
    static final String COA_PROJ = "coaProjCd";
    
    /** GL String Divider*/
    static final String DIVIDER = ".";
    
    /** Screen ID */
    static final String SCRN_ID_00 = "NFAL0120Scrn00";
    
    /** Biz ID*/
    static final String BIZ_ID = "NFAL0120";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    // START 2018/10/09 J.Kim [QC#28677,DEL]
    // /** File size */
    // public static final int CSV_LIMIT_COUNT = 65536;
    // END 2018/10/09 J.Kim [QC#28677,DEL]

    /** Message (There are too many search results, there is data that cannot be displayed.) */
    public static final String NZZM0001W = "NZZM0001W";
}
