package business.blap.NFCL3000.constant;

import java.math.BigDecimal;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/12/21   Fujitsu         T.Tanaka        Create          N/A
 * 2016/05/17   Fujitsu         S.Fujita        Update          QC#8373
 * 2016/05/18   Fujitsu         S.Fujita        Update          QC#7780
 * 2016/05/24   Fujitsu         S.Fujita        Update          QC#8550
 * 2016/05/24   Fujitsu         S.Fujita        Update          QC#8522
 * 2016/06/03   Fujitsu         S.Fujita        Update          QC#9157
 * 2016/06/20   Fujitsu         S.Fujita        Update          QC#9454
 * 2016/07/20   Fujitsu         S.Fujita        Update          QC#10148
 * 2016/09/05   Fujitsu         S.Fujita        Update          QC#13648
 * 2016/09/15   Fujitsu         S.Yoshida       Update          QC#13956
 * 2016/10/26   Fujitsu         T.Murai         Update          QC#13639
 * 2016/11/22   Fujitsu         S.Fujita        Update          QC#16154
 * 2016/11/29   Fujitsu         S.Fujita        Update          QC#16075
 * 2018/05/22   Fujitsu         Y.Matsui        Update          QC#21841
 * 2018/05/28   Fujitsu         Y.Matsui        Update          QC#26342
 * 2018/08/13   Fujitsu         Y.Matsui        Update          QC#26968 Modify CSV Header
 * 2019/04/17   Fujitsu         S.Takami        Update          QC#31204
 * 2019/04/25   Fujitsu         S.Takami        Update          QC#50078
 * 2019/05/16   Fujitsu         S.Takami        Update          QC#50374
 * 2019/05/29   Fujitsu         S.Takami        Update          QC#50542
 * 2019/06/05   Fujitsu         S.Takami        Update          QC#50685
 * 2019/06/05   Fujitsu         S.Takami        Update          QC#50683
 * 2019/06/06   Fujitsu         S.Takami        Update          QC#50691
 * 2020/04/22   Fujitsu         H.Mizukami      Update          QC#56117
 * 2021/01/04   CITS            R.Kurahashi     Update          QC#56282
 *</pre>
 */
public interface NFCL3000Constant {

    /** */
    public static final String INIT_INV_BOL_LINE_NUM = "001";

    /** */
    public static final String DUMMY_INV_BOL_LINE_NUM = "000";

    /** */
    public static final String INIT_INV_LINE_NUM = "00001";

    /** */
    public static final String INIT_INV_LINE_SUB_NUM = "001";

    /** */
    public static final String INIT_INV_LINE_SUB_TRX_NUM = "001";

    /** */
    public static final String TAB_Line = "TAB_Line";

    /** */
    public static final String TAB_SalesCredit = "TAB_SalesCredit";

    /** */
    public static final String TAB_Accounting = "TAB_Accounting";

    /** */
    public static final String TAB_BOL = "TAB_BOL";

    /** */
    public static final String TAB_MoreInfo = "TAB_MoreInfo";

    /** */
    public static final String DR_CR_TP_DEBIT = "D";

    /** */
    public static final String DR_CR_TP_CREDIT = "C";

    /** */
    public static final String CUST_ACCT = "@CUST-ACCT";

    public static final String INV_PRINT_STS_NOT_PRINT = "1";

    public static final String INV_PRINT_STS_PRINTED = "2";

    public static final String INV_PRINT_STS_NOT_PRINT_DESC = "UNPROCESSED";

    public static final String INV_PRINT_STS_PRINTED_DESC = "PROCESSED";

    // START 2018/05/28 Y.Matsui [QC#26342,ADD]
    public static final String INV_PRINT_STS_DO_NOT_PRINT = "0";

    public static final String INV_PRINT_STS_DO_NOT_PRINT_DESC = "DO NOT PRINT";
    // START 2018/05/28 Y.Matsui [QC#26342,ADD]

    // START 2017/12/25 E.Kameishi [QC#20312,ADD]
    public static final String INV_PRINT_CRAT_STS_NOT_PRINT = "0";

    public static final String INV_PRINT_CRAT_STS_PRINTED = "1";

    public static final String INV_PRINT_CRAT_STS_RE_PRINTED = "2";

    public static final String INV_PRINT_CRAT_STS_PRINTED_DESC = "PRINTED";

    public static final String INV_PRINT_CRAT_STS_RE_PRINTED_DESC = "REPRINTED";

    public static final String TRX_TAX_ADJUSTMENT = "080";

    public static final String TRX_RSN_TAX_ADJUSTMENT = "03";

    public static final String UOM_CD_EA = "EA";
    // END 2017/12/25 E.Kameishi [QC#20312,ADD]

    public static final String CSV_DOWNLOAD_FILE = "ManualInvoiceEntry";

    public static final String CSV_DOWNLOAD_INV_LINE = "Invoice Line";

    public static final String CSV_DOWNLOAD_INV_SALES_CREDIT = "Invoice Sales Credit";

    public static final String CSV_DOWNLOAD_INV_ACCOUNTING = "Invoice Accounting";

    public static final String CSV_DOWNLOAD_INV_BOL = "Invoice BOL";

    /** CSV */
    public static final String[] LINE_HEADER = {
        // START 2016/10/26 T.Murai [QC#13639, MOD]
        "#",
        "ShipLn#",
        // END   2016/10/26 T.Murai [QC#13639, MOD]
        "Line#",
        // START 2018/05/22 Y.Matsui [QC#21841,ADD]
        "Category",
        // END   2018/05/22 Y.Matsui [QC#21841,ADD]
        "Item Code",
        "Description",
        "UOM",
        "Qty",
        "Unit Price",
        "Sales Amount",
        "Tax Amount",
        "Total Amount",
        "Order#",
        "Order Line#",
        "Order Date",
        "Ship Date",
        "W/H",
        //"Std Cost",// 2016/10/26 T.Murai [QC#13639, DEL]
        "Tax Percent",
        "Contract#",
        "Contract Line Revision",
        "Serial#",
        "Model#",
        "Billing Type",
        "Bill From Date",
        "Bill To Date",
        "No of Copies",
        "Quantity Ordered",
        "Reason",
        "Bill Instance#",
        "CSMP Number",
        "CSA Number",
        "CSMP Amount",
        "Control1",
        "Control2",
        "Control3",
        "Control4",
        "Control5",
        "Control6",
        "Comments",
        "Error Message",
        "Error Val"
    };

    public static final String[] SALES_CREDIT_HEADER = {
        "#",
        "S/C#",
        // START 2016/10/26 T.Murai [QC#13639, MOD]
        "ShipLn#",
        // END   2016/10/26 T.Murai [QC#13639, MOD]
        "Line#",
        "Salesrep Number",
        "Salesrep Name",
        "Percentage Split%",
        "Amount",
        "Salesrep Branch",
        "Comments",
        "Revenue Split Type",
        "Item Code",
        // START 2016/09/05 S.Fujita [QC#13648,DEL]
//        "Line Split%",
        // END   2016/09/05 S.Fujita [QC#13648,DEL]
        "Accounting Rule",
        "Deferred Revenue Balance Amount",
        "First Revenue Recognition Date",
        "Next Revenue Recognition Date",
        "Schedule Revenue Amount",
        "Revenue Recognition Count",
        "Accounting Rule Duration",
        "Rule Start Date",
        "Trx",
        "Trx Reason",
        "Error Message",
        "Error Val"
    };

    public static final String[] ACCOUNTING_HEADER = {
        "#",
        "S/C#",
        // START 2016/10/26 T.Murai [QC#13639, MOD]
        "ShipLn#",
        // END   2016/10/26 T.Murai [QC#13639, MOD]
        "Line#",
        "D/C",
        "Accounting Class",
        "Account Information",
        "Debit Amount",
        "Credit Amount",
        "Percentage(%)",
        "GL Date",
        "GL Posting Date",
        "Error Message",
        "Error Val"
    };

    public static final String[] BOL_HEADER = {
        // START 2016/10/26 T.Murai [QC#13639, MOD]
        "#",
        "ShipLn#",
        // END   2016/10/26 T.Murai [QC#13639, MOD]
        // START 2018/05/22 Y.Matsui [QC#21841,DEL]
        // "Freight Amount",
        // "Freight Tax Amoujnt",
        // "Freight Tax Percent",
        // END   2018/05/22 Y.Matsui [QC#21841,DEL]
        "W/H",
        "Ship To Account#",
        "Ship To Customer Name",
        "Ship To Location",
        "Address1",
        "Address2",
        "City",
        "State",
        "Postal Code",
        "Ship To Contact",
        "Ship Date",
        "S/O Number",
        "BOL Number",
        "Error Message",
        "Error Val"
    };

    // START 2016/08/01 S.Fujita [QC#10148,ADD]
    /** CSV GENERAL*/
    public static final String[] LINE_HEADER_GENERAL = {
        // START 2016/10/26 T.Murai [QC#13639, MOD]
        "#",
        "ShipLn#",
        // END   2016/10/26 T.Murai [QC#13639, MOD]
        "Line#",
        // START 2018/05/22 Y.Matsui [QC#21841,ADD]
        "Category",
        // END   2018/05/22 Y.Matsui [QC#21841,ADD]
        "Item Code",
        "Description",
        "UOM",
        "Qty",
        "Unit Price",
        "Sales Amount",
        "Tax Amount",
        "Total Amount",
        "Order#",
        "Order Line#",
        "Order Date",
        "Ship Date",
        "W/H",
        //"Std Cost", // 2016/10/26 T.Murai [QC#13639, DEL]
        "Tax Percent",
        "Contract#",
        "Contract Line Revision",
        "Serial#",
        "Model#",
        "Billing Type",
        "Bill From Date",
        "Bill To Date",
        "No of Copies",
        "Quantity Ordered",
        "Reason",
        "Bill Instance#",
        "CSMP Number",
        "CSA Number",
        "CSMP Amount",
        "Control1",
        "Control2",
        "Control3",
        "Control4",
        "Control5",
        "Control6",
        "Comments",
    };

    public static final String[] SALES_CREDIT_HEADER_GENERAL = {
        "#",
        "S/C#",
        // START 2016/10/26 T.Murai [QC#13639, MOD]
        "ShipLn#",
        // END   2016/10/26 T.Murai [QC#13639, MOD]
        "Line#",
        "Salesrep Number",
        "Salesrep Name",
        "Percentage Split%",
        "Amount",
        "Salesrep Branch",
        "Comments",
        "Revenue Split Type",
        "Item Code",
        // START 2016/09/05 S.Fujita [QC#13648,DEL]
//        "Line Split%",
        // END   2016/09/05 S.Fujita [QC#13648,DEL]
        "Accounting Rule",
        "Deferred Revenue Balance Amount",
        "First Revenue Recognition Date",
        "Next Revenue Recognition Date",
        "Schedule Revenue Amount",
        "Revenue Recognition Count",
        "Accounting Rule Duration",
        "Rule Start Date",
        "Trx",
        "Trx Reason"
    };

    public static final String[] ACCOUNTING_HEADER_GENERAL = {
        "#",
        "S/C#",
        // START 2016/10/26 T.Murai [QC#13639, MOD]
        "ShipLn#",
        // END   2016/10/26 T.Murai [QC#13639, MOD]
        "Line#",
        "D/C",
        "Accounting Class",
        "Account Information",
        "Debit Amount",
        "Credit Amount",
        "Percentage(%)",
        "GL Date",
        "GL Posting Date",
    };

    public static final String[] BOL_HEADER_GENERAL = {
        // START 2016/10/26 T.Murai [QC#13639, MOD]
        "#",
        "ShipLn#",
        // END   2016/10/26 T.Murai [QC#13639, MOD]
        // START 2018/05/22 Y.Matsui [QC#21841,DEL]
        // "Freight Amount",
        // "Freight Tax Amoujnt",
        // "Freight Tax Percent",
        // END   2018/05/22 Y.Matsui [QC#21841,DEL]
        "W/H",
        "Ship To Account#",
        "Ship To Customer Name",
        "Ship To Location",
        "Address1",
        "Address2",
        "City",
        "State",
        "Postal Code",
        "Ship To Contact",
        "Ship Date",
        "S/O Number",
        "BOL Number",
    };
    // END   2016/08/01 S.Fujita [QC#10148,ADD]
    /**
     * 
     */
    public static final String TRX_MANUAL_INVOICE = "010";
    public static final String TRX_MANUAL_INVOICE_NM = "MANUAL INVOICE";

    /**
     * 
     */
    public static final String TRX_RSN_MANUAL_INVOICE = "M1";
    public static final String TRX_RSN_MANUAL_INVOICE_NM = "MANUAL INVOCIE";

    /** */
    public static final BigDecimal ACCT_DIST_SMRY = BigDecimal.ZERO;

    /** */
    public static final BigDecimal ACCT_DIST_EDIT = BigDecimal.ONE;
    
    /** */
    public static int[] COA_LENGTH = {
        3,3,6,8,2,3,3,2,3
    };

    static final String AJE_INV_ACCT_DIST_SQ = "AJE_INV_ACCT_DIST_SQ";

    // START 2016/05/17 S.Fujita [QC#8373,MOD]
//    static final String AJE_PTRN_IND_TP_CD_01 = "001";
//    static final String AJE_PTRN_IND_TP_NM_01 = "MANUAL INVOCIE";
//    static final String AJE_PTRN_ACTL_CD_01 = "I";
//    static final String AJE_PTRN_ACTL_NM_01 = "MANUAL INVOCIE";
//    static final String AJE_PTRN_IND_TP_CD_02 = "011";
//    static final String AJE_PTRN_IND_TP_NM_02 = "MANUAL INVOCIE";
//    static final String AJE_PTRN_ACTL_CD_02 = "IM";
//    static final String AJE_PTRN_ACTL_NM_02 = "IM";
//    static final String AJE_PTRN_IND_TP_CD_03 = "ZZZ";
//    static final String AJE_PTRN_IND_TP_NM_03 = "DEFAULT";
//    static final String AJE_PTRN_ACTL_CD_03 = "ZZ";
//    static final String AJE_PTRN_ACTL_NM_03 = "DEFAULT VAL";
//    static final String JRNL_CATG_CD = "A01";
//    static final String JRNL_CATG_NM = "CASH LEASE - MDSE";
    /** -------- for AJE Pattern ------- **/
    /** DB Item Column Name */
    static final String AJE_ID = "AJE_ID";

    /** DB Item Column Name */
    static final String AJE_LINE_IND_TP_CD = "AJE_LINE_IND_TP_CD";
    
    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_CD_01 = "AJE_PTRN_IND_TP_CD_01";

    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_NM_01 = "AJE_PTRN_IND_TP_NM_01";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_CD_01 = "AJE_PTRN_ACTL_CD_01";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_NM_01 = "AJE_PTRN_ACTL_NM_01";

    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_CD_02 = "AJE_PTRN_IND_TP_CD_02";

    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_NM_02 = "AJE_PTRN_IND_TP_NM_02";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_CD_02 = "AJE_PTRN_ACTL_CD_02";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_NM_02 = "AJE_PTRN_ACTL_NM_02";

    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_CD_03 = "AJE_PTRN_IND_TP_CD_03";

    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_NM_03 = "AJE_PTRN_IND_TP_NM_03";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_CD_03 = "AJE_PTRN_ACTL_CD_03";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_NM_03 = "AJE_PTRN_ACTL_NM_03";

    /** DB Item Column Name */
    static final String AJE_INTFC_COL_TXT_01 = "AJE_INTFC_COL_TXT_01";

    /** DB Item Column Name */
    static final String AJE_INTFC_COL_TXT_02 = "AJE_INTFC_COL_TXT_02";

    /** DB Item Column Name */
    static final String AJE_INTFC_COL_TXT_03 = "AJE_INTFC_COL_TXT_03";

    /** DB Item Column Name */
    static final String SYS_SRC_CD = "SYS_SRC_CD";

    /** DB Item Column Name */
    static final String SYS_SRC_NM = "SYS_SRC_NM";

    /** DB Item Column Name */
    static final String TRX_CD = "TRX_CD";

    /** DB Item Column Name */
    static final String TRX_NM = "TRX_NM";

    /** DB Item Column Name */
    static final String TRX_RSN_CD = "TRX_RSN_CD";

    /** DB Item Column Name */
    static final String TRX_RSN_NM = "TRX_RSN_NM";

    /** DB Item Column Name */
    static final String JRNL_SRC_CD = "JRNL_SRC_CD";

    /** DB Item Column Name */
    static final String JRNL_SRC_NM = "JRNL_SRC_NM";

    /** DB Item Column Name */
    static final String JRNL_CATG_CD = "JRNL_CATG_CD";

    /** DB Item Column Name */
    static final String JRNL_CATG_NM = "JRNL_CATG_NM";

    /** DB Item Column Name */
    static final String DR_CR_TP_CD = "DR_CR_TP_CD";

    /** DB Item Column Name */
    static final String AJE_LINK_FLG = "AJE_LINK_FLG";

    /** DB Item Column Name */
    static final String AJE_LINE_IDX_NUM = "AJE_LINE_IDX_NUM";

    /** DB Item Column Name */
    static final String DR_AJE_LINE_IDX_DESC_TXT = "DR_AJE_LINE_IDX_DESC_TXT";

    /** DB Item Column Name */
    static final String CR_AJE_LINE_IDX_DESC_TXT = "CR_AJE_LINE_IDX_DESC_TXT";
    // END 2016/05/17 S.Fujita [QC#8373,MOD]
    //---- start add 2016/05/06
    /** message */
    static final String NOT_ASSIGNED_ERROR = "NFCM0838E";

    /** message */
    static final String NOT_ASSIGNED_WARNING = "NFCM0839W";

    /** message param*/
    static final String PRM_SLS_CR_S = "Sales Credit(s)";

    /** message param*/
    static final String PRM_SLS_CR = "Sales Credit";

    /** message param*/
    static final String PRM_ACCT_DIST = "Account Distribution";

    /** message param*/
    static final String PRM_LINE_S = "Line(s)";

    /** message param*/
    static final String PRM_LINE = "Line";

    /** message param*/
    static final String PRM_BOL_S = "BOL(s)";

    /** message param*/
    static final String PRM_LINE_ONE = "one Line";

    /** message param*/
    static final String PRM_SLS_CR_ONE = "one Sales Credit";
    //---- end 2016/05/06

    // START 2016/05/18 S.Fujita [QC#7780,ADD]
    /**
     */
    int YEAR_INDEX = 0;

    /**
     */
    int MONTH_INDEX = 1;
    // END 2016/05/18 S.Fujita [QC#7780,ADD]

    // START 2016/05/24 S.Fujita [QC#8522,ADD]
    /**
     */
    static final String DFRD_REV_GL_STRG_FLG = "N";
    // END   2016/05/24 S.Fujita [QC#8522,ADD]

    // START 2016/06/03 S.Fujita [QC#9157,ADD]
    /** Data does not exist. */
    static final String NOT_EXISTS_ERROR = "NFCM0508E";

    /** Invalid @ format. */
    static final String INVALID_ERROR = "NFCM0833E";

    /** Sum of Percentage must be @. */
    static final String PERCENTAGE_ERROR = "NFCM0830E";

    /** '@ is mismatched with @. */
    static final String MISMATCH_ERROR = "NFCM0800E";

    // START 2017/12/25 E.Kameishi [QC#20312,ADD]
    /** Item code for Tax Adjustment and other item code is mixed. */
    static final String NFCM0877E = "NFCM0877E";

    /** Please select item code for tax adjustment with "I" or ">>" button. */
    static final String NFCM0878E = "NFCM0878E";
    // END 2017/12/25 E.Kameishi [QC#20312,ADD]

    // START 2019/04/17 S.Takami [QC#31204,ADD]
    /** Please choose @, because you have chosen @ on other line(s). */
    static final String NFCM0899E = "NFCM0899E";
    /** Please click "S" button and choose a line which has contract# from the popup window. */
    static final String NFCM0900E = "NFCM0900E";
    /** Please set @ using "S" button and choose a line which has contract#  from the popup window, because you have chosen @ as billing type. */
    static final String NFCM0901E = "NFCM0901E";
    /** Please set @, because you have chosen @ as billing type. */
    static final String NFCM0902E = "NFCM0902E";
    // END 2019/04/17 S.Takami [QC#31204,ADD]
    /** message param*/
    static final String PRM_SLS_CR_LINE_NUMBERE = "Sales Credit Line Number";

    /** message param*/
    static final String PRM_100_PERCENT = "100%";

    /** message param*/
    static final String PRM_SLS_CR_AMOUNT = "Sales Credit Amount";

    /** message param*/
    static final String PRM_LINE_AMOUNT = "Line Amount";

    static final BigDecimal DIV100 = new BigDecimal(100);
    // END   2016/06/03 S.Fujita [QC#9157,ADD]

    // START 2016/06/20 S.Fujita [QC#9454,ADD]
    /** DB Item Column Name */
    static final String LOC_NM ="LOC_NM";

    /** DB Item Column Name */
    static final String ADDL_LOC_NM ="ADDL_LOC_NM";

    /** DB Item Column Name */
    static final String FIRST_LINE_ADDR ="FIRST_LINE_ADDR";

    /** DB Item Column Name */
    static final String SCD_LINE_ADDR ="SCD_LINE_ADDR";

    /** DB Item Column Name */
    static final String THIRD_LINE_ADDR ="THIRD_LINE_ADDR";

    /** DB Item Column Name */
    static final String FRTH_LINE_ADDR ="FRTH_LINE_ADDR";

    /** DB Item Column Name */
    static final String CTY_ADDR ="CTY_ADDR";

    /** DB Item Column Name */
    static final String PROV_NM ="PROV_NM";

    /** DB Item Column Name */
    static final String ST_CD ="ST_CD";

    /** DB Item Column Name */
    static final String POST_CD ="POST_CD";

    /** DB Item Column Name */
    static final String CTRY_CD ="CTRY_CD";

    /** DB Item Column Name */
    static final String FIRST_REF_CMNT_TXT ="FIRST_REF_CMNT_TXT";

    /** DB Item Column Name */
    static final String SCD_REF_CMNT_TXT ="SCD_REF_CMNT_TXT";

    /** DB Item Column Name */
    static final String CNTY_NM ="CNTY_NM";

    /** DB Item Column Name */
    static final String INV_RCPNT_CUST_CD = "INV_RCPNT_CUST_CD";

    /** DB Item Column Name */
    static final String FL_PLN_CMPY_FLG = "FL_PLN_CMPY_FLG";

    /** DB Item Column Name */
    static final String DS_ACCT_NM = "DS_ACCT_NM";

    /** DB Item Column Name */
    static final String CTAC_PSN_PK = "CTAC_PSN_PK";

    /** DB Item Column Name */
    static final String MDSE_NM = "MDSE_NM";

    /** DB Item Column Name */
    static final String MDSE_TP_CD = "MDSE_TP_CD";

    /** DB Item Column Name */
    static final String ZEROTH_PROD_CTRL_CD = "ZEROTH_PROD_CTRL_CD";

    /** DB Item Column Name */
    static final String ZEROTH_PROD_CTRL_NM = "ZEROTH_PROD_CTRL_NM";

    /** DB Item Column Name */
    static final String FIRST_PROD_CTRL_CD = "FIRST_PROD_CTRL_CD";

    /** DB Item Column Name */
    static final String FIRST_PROD_CTRL_NM = "FIRST_PROD_CTRL_NM";

    /** DB Item Column Name */
    static final String SCD_PROD_CTRL_CD = "SCD_PROD_CTRL_CD";

    /** DB Item Column Name */
    static final String SCD_PROD_CTRL_NM = "SCD_PROD_CTRL_NM";

    /** DB Item Column Name */
    static final String THIRD_PROD_CTRL_CD = "THIRD_PROD_CTRL_CD";

    /** DB Item Column Name */
    static final String THIRD_PROD_CTRL_NM = "THIRD_PROD_CTRL_NM";

    /** DB Item Column Name */
    static final String FRTH_PROD_CTRL_CD = "FRTH_PROD_CTRL_CD";

    /** DB Item Column Name */
    static final String FRTH_PROD_CTRL_NM = "FRTH_PROD_CTRL_NM";

    /** DB Item Column Name */
    static final String FIFTH_PROD_CTRL_CD = "FIFTH_PROD_CTRL_CD";

    /** DB Item Column Name */
    static final String FIFTH_PROD_CTRL_NM = "FIFTH_PROD_CTRL_NM";

    /** DB Item Column Name */
    static final String SIXTH_PROD_CTRL_CD = "SIXTH_PROD_CTRL_CD";

    /** DB Item Column Name */
    static final String SIXTH_PROD_CTRL_NM = "SIXTH_PROD_CTRL_NM";

    /** DB Item Column Name */
    static final String SVNTH_PROD_CTRL_CD = "SVNTH_PROD_CTRL_CD";

    /** DB Item Column Name */
    static final String SVNTH_PROD_CTRL_NM = "SVNTH_PROD_CTRL_NM";

    /** DB Item Column Name */
    static final String COA_PROD_CD = "COA_PROD_CD";

    /** DB Item Column Name */
    static final String COA_CMPY_CD = "COA_CMPY_CD";

    /** */
    static final String COA_AFFL_CD = "COA_AFFL_CD";

    /** */
    static final String COA_BR_CD = "COA_BR_CD";

    /** */
    static final String COA_CH_CD = "COA_CH_CD";

    /** */
    static final String COA_CC_CD = "COA_CC_CD";

    /** */
    static final String COA_ACCT_CD = "COA_ACCT_CD";

    /** */
    static final String COA_PROJ_CD = "COA_PROJ_CD";

    /** DB Item Column Name */
    static final String COA_EXTN_CD = "COA_EXTN_CD";

    /** DB Item Column Name */
    static final String THIS_MTH_TOT_STD_COST_AMT = "THIS_MTH_TOT_STD_COST_AMT";

    /** DB Item Column Name */
    static final String EXP_ITEM_FLG = "EXP_ITEM_FLG";

    /** DB Item Column Name */
    static final String COA_PROD_NM = "COA_PROD_NM";

    /** DB Item Column Name */
    static final String DS_TAX_GRP_EXEM_CD = "DS_TAX_GRP_EXEM_CD";

    /** DB Item Column Name */
    static final String GEO_CD = "GEO_CD";

    /** DB Item Column Name */
    static final String DS_INSD_CTY_LIMIT_FLG = "DS_INSD_CTY_LIMIT_FLG";

    /** DB Item Column Name */
    static final String CNTY_PK = "CNTY_PK";

    static final String FIXED_INV_PRMO_INFO_SEQ_NUM  = "001";

    static final String FREIGHT_TAX_LINE_NUM = "*";

    /** DB Item Column Name */
    static final String DS_INV_SLS_CR_PK = "DS_INV_SLS_CR_PK";

    /** DB Item Column Name */
    static final String INV_PRMO_INFO_PK = "INV_PRMO_INFO_PK";

    /** DB Item Column Name */
    static final String AJE_INV_ACCT_DIST_PK = "AJE_INV_ACCT_DIST_PK";

    /** DB Item Column Name */
    static final String AJE_INV_ACCT_DIST_ERR_PK = "AJE_INV_ACCT_DIST_ERR_PK";

    /** DB Item Column Name */
    static final String DS_INV_LINE_TAX_DTL_PK = "DS_INV_LINE_TAX_DTL_PK";

    /** DB Item Column Name */
    static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";

    /** DB Item Column Name */
    static final String DS_ACCT_NUM = "DS_ACCT_NUM";

    /** DB Item Column Name */
    static final String AJE_ACCTG_RULE_CD = "AJE_ACCTG_RULE_CD";

    /** DB Item Column Name */
    static final String INV_NUM = "INV_NUM";

    /** DB Item Column Name */
    static final String INV_TP_CD = "INV_TP_CD";

    /** DB Item Column Name */
    static final String ACCT_DT = "ACCT_DT";

    /** DB Item Column Name */
    static final String INV_DT = "INV_DT";

    /** DB Item Column Name */
    static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** DB Item Column Name */
    static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** DB Item Column Name */
    static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** DB Item Column Name */
    static final String DEAL_CCY_CD = "DEAL_CCY_CD";

    /** DB Item Column Name */
    static final String INV_LINE_NUM = "INV_LINE_NUM";

    /** DB Item Column Name */
    static final String TOT_BOL_DEAL_TAX_AMT = "TOT_BOL_DEAL_TAX_AMT";

    /** DB Item Column Name */
    static final String TOT_BOL_FUNC_TAX_AMT = "TOT_BOL_FUNC_TAX_AMT";

    /** DB Item Column Name */
    static final String INV_BOL_LINE_NUM = "INV_BOL_LINE_NUM";

    /** DB Item Column Name */
    static final String INV_LINE_SUB_NUM = "INV_LINE_SUB_NUM";

    /** DB Item Column Name */
    static final String INV_TRX_LINE_SUB_NUM = "INV_TRX_LINE_SUB_NUM";

    /** DB Item Column Name */
    static final String SHIP_DEAL_FRT_AMT = "SHIP_DEAL_FRT_AMT";

    /** DB Item Column Name */
    static final String SHIP_FUNC_FRT_AMT = "SHIP_FUNC_FRT_AMT";

    /** DB Item Column Name */
    static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** DB Item Column Name */
    static final String DS_ORD_TP_CD = "DS_ORD_TP_CD";

    /** DB Item Column Name */
    static final String DS_ORD_RSN_CD = "DS_ORD_RSN_CD";

    /** DB Item Column Name */
    static final String INV_LINE_SPL_PCT = "INV_LINE_SPL_PCT";

    /** DB Item Column Name */
    static final String SLS_REP_CR_PCT = "SLS_REP_CR_PCT";

    /** DB Item Column Name */
    static final String DEAL_SLS_CR_AMT = "DEAL_SLS_CR_AMT";

    /** DB Item Column Name */
    static final String FUNC_SLS_CR_AMT = "FUNC_SLS_CR_AMT";

    /** DB Item Column Name */
    static final String DR_AJE_COA_CMPY_CD = "DR_AJE_COA_CMPY_CD";

    /** DB Item Column Name */
    static final String DR_AJE_COA_AFFL_CD = "DR_AJE_COA_AFFL_CD";

    /** DB Item Column Name */
    static final String DR_AJE_COA_BR_CD = "DR_AJE_COA_BR_CD";

    /** DB Item Column Name */
    static final String DR_AJE_COA_CH_CD = "DR_AJE_COA_CH_CD";

    /** DB Item Column Name */
    static final String DR_AJE_COA_CC_CD = "DR_AJE_COA_CC_CD";

    /** DB Item Column Name */
    static final String DR_AJE_COA_ACCT_CD = "DR_AJE_COA_ACCT_CD";

    /** DB Item Column Name */
    static final String DR_AJE_COA_PROD_CD = "DR_AJE_COA_PROD_CD";

    /** DB Item Column Name */
    static final String DR_AJE_COA_PROJ_CD = "DR_AJE_COA_PROJ_CD";

    /** DB Item Column Name */
    static final String DR_AJE_COA_EXTN_CD = "DR_AJE_COA_EXTN_CD";

    /** DB Item Column Name */
    static final String CR_AJE_COA_CMPY_CD = "CR_AJE_COA_CMPY_CD";

    /** DB Item Column Name */
    static final String CR_AJE_COA_AFFL_CD = "CR_AJE_COA_AFFL_CD";

    /** DB Item Column Name */
    static final String CR_AJE_COA_BR_CD = "CR_AJE_COA_BR_CD";

    /** DB Item Column Name */
    static final String CR_AJE_COA_CH_CD = "CR_AJE_COA_CH_CD";

    /** DB Item Column Name */
    static final String CR_AJE_COA_CC_CD = "CR_AJE_COA_CC_CD";

    /** DB Item Column Name */
    static final String CR_AJE_COA_ACCT_CD = "CR_AJE_COA_ACCT_CD";

    /** DB Item Column Name */
    static final String CR_AJE_COA_PROD_CD = "CR_AJE_COA_PROD_CD";

    /** DB Item Column Name */
    static final String CR_AJE_COA_PROJ_CD = "CR_AJE_COA_PROJ_CD";

    /** DB Item Column Name */
    static final String CR_AJE_COA_EXTN_CD = "CR_AJE_COA_EXTN_CD";

    /** DB Item Column Name */
    static final String COA_ATTRB_NM_01 = "COA_ATTRB_NM_01";

    /** DB Item Column Name */
    static final String COA_ATTRB_NM_02 = "COA_ATTRB_NM_02";

    /** DB Item Column Name */
    static final String COA_ATTRB_NM_03 = "COA_ATTRB_NM_03";

    /** DB Item Column Name */
    static final String COA_ATTRB_NM_04 = "COA_ATTRB_NM_04";

    /** DB Item Column Name */
    static final String COA_ATTRB_NM_05 = "COA_ATTRB_NM_05";

    /** DB Item Column Name */
    static final String COA_ATTRB_NM_06 = "COA_ATTRB_NM_06";

    /** DB Item Column Name */
    static final String COA_ATTRB_NM_07 = "COA_ATTRB_NM_07";

    /** DB Item Column Name */
    static final String COA_ATTRB_NM_08 = "COA_ATTRB_NM_08";

    /** DB Item Column Name */
    static final String COA_ATTRB_NM_09 = "COA_ATTRB_NM_09";

    /** DB Item Column Name */
    static final String COA_SEG_ERR = "NFCM0531E";

    /** Fetch Size */
    static final int FETCH_SIZE = 1000;

    /** Amount Size */
    static final int AMT_SIZE = 15;

    /** Tax Calculate Header Num */
    static final String TAX_HEADER_NUM = "1";
    // END   2016/06/20 S.Fujita [QC#9454,ADD]

    // START 2016/07/20 S.Fujita [QC#10148,ADD]
    /** DB Item Column Name */
    static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Default COA Value*/
    static final String DEF_COA_VAL = "#DEF";

    /** Default COA Value*/
    static final String AJE_COA_DEF_VALUES = "AJE_COA_DEF_VALUES";

    /** Fixed Value : Blank */
    static final String BLANK = "";

    /** Fixed Value : Zero */
    static final String ZERO = "0";

    /** DisplayPattern : Invisibility Error */
    static final String DIS_PAT_INVISIBILITY_ERROR = "InvisibilityError";

    /** DisplayPattern : Visibility Error */
    static final String DIS_PAT_VISIBILITY_ERROR = "VisibilityError";
    // END   2016/07/20 S.Fujita [QC#10148,ADD]

    // START 2016/09/15 S.Yoshida [QC#13956,ADD]
    public static final String CR_CARD_SCRN_TXT = "****";
    // END   2016/09/15 S.Yoshida [QC#13956,ADD]

    // START 2016/10/26 T.Murai [QC#15639,ADD]
    public static final int CPO_LINE_NUM_LENG = 3;
    // END   2016/10/26 T.Murai [QC#15639,ADD]

    // START 2016/10/26 S.Fujitai [QC#16154,ADD]
    public static final int INIT_AJE_LINE_IDX_NUM = 1;
    // END   2016/10/26 S.Fujita [QC#16154,ADD]

    // START 2016/11/29 S.Fujita [QC#16075,ADD]
    /** Out Of Terrytory Value*/
    public static final String OUT_OF_TERRITORY_BR = "OUT_TRTY_BR_CD";
    // END   2016/11/29 S.Fujita [QC#16075,ADD]

    // START 2018/05/22 Y.Matsui [QC#21841,ADD]
    /** VarCharConstNm: COA Merchandise Type Code (Freight) */
    public static final String AJE_COA_MDSE_TP_FRT = "AJE_COA_MDSE_TP_FRT";

    /** The combination of item code and category is incorrect. */
    public static final String NFCM0885E = "NFCM0885E";
    // END   2018/05/22 Y.Matsui [QC#21841,ADD]

    // START 2019/04/17 S.Takami [QC#31204,ADD]
    /** Varchar Const Name: SVC_INV_CHRG_TP */
    public static final String NFCL3000_MND_SEL_SVC_INV_CHRG = "NFCL3000_MND_SEL_SVC_INV_CHRG";

    /**  BC/MC/AC if varchar const name "NFCL3000_SEL_MND_ SVC_INV_CHRG_TP" is not set up on VAR_CHAR_CONST*/
    public static final String NFCL3000_MND_SEL_SVC_INV_CHRG_VAL = "BC,MC,AC";
    // END 2019/04/17 S.Takami [QC#31204,ADD]

    // START 2019/04/25 S.Takami [QC#50078,ADD]
    /** Varchar Const Name: NFCL3000_FSR_INV_TP */
    public static final String NFCL3000_FSR_INV_TP = "NFCL3000_FSR_INV_TP";

    /** Varchar Const Name: NFCL3000_FSR_BLLG_TP */
    public static final String NFCL3000_FSR_BLLG_TP = "NFCL3000_FSR_BLLG_TP";

    /** Varchar Const Name: NFCL3000_DECL_UOM_CD */
    public static final String NFCL3000_DECL_UOM_CD = "NFCL3000_DECL_UOM_CD";

    /** Varchar Const Name: NFCL3000_CONTR_DS_INV_TP */
    public static final String NFCL3000_CONTR_DS_INV_TP = "NFCL3000_CONTR_DS_INV_TP";

    /** Varchar Const Name: NFCL3000_FSR_INV_TP Default Value */
    public static final String NFCL3000_FSR_INV_TP_VAL = "0055,0032";

    /** Varchar Const Name: NFCL3000_FSR_BLLG_TP Default Value */
    public static final String NFCL3000_FSR_BLLG_TP_VAL = "LC,TC,PC,XC";

    /** Varchar Const Name: NFCL3000_DECL_UOM_CD Default Value*/
    public static final String NFCL3000_DECL_UOM_CD_VAL = "HR";

    /** Varchar Const Name: NFCL3000_DECL_UOM_CD Default Value*/
    public static final String NFCL3000_CONTR_DS_INV_TP_VAL = "0029,0051";

    /** Please choose Billing Type, because you have chosen @ as Invoice Type. */
    public static final String NFCM0903E = "NFCM0903E";

    /** Please choose @ as Billing Type, because you have chosen @ as Invoice Type. */
    public static final String NFCM0904E = "NFCM0904E";
    // END 2019/04/25 S.Takami [QC#50078,ADD]

    // START 2019/05/16 S.Takami [QC#50374,ADD]
    /** Please make this length less than @, because it will be userd as Order Number. */
    public static final String NFCM0905E = "NFCM0905E";
    // END 2019/05/16 S.Takami [QC#50374,ADD]

    // START 2019/05/29 S.Takami [QC#50542,ADD]
    /** NFZC102001 Resource Object Name: Debit */
    public static final String RESRC_OBJ_NM_DEBIT = "NFCL3000D";

    /** NFZC102001 Resource Object Name: Credit */
    public static final String RESRC_OBJ_NM_CREDIT = "NFCL3000C";
    // END 2019/05/29 S.Takami [QC#50542,ADD]

    // START 2019/06/05 S.Takami [QC#50685,ADD]
    /** Available Tax Adjustment Item DS_INV_TP_CD */
    public static final String NFCL3000_TAX_ADJ_DS_INV_TP = "NFCL3000_TAX_ADJ_DS_INV_TP";

    /** Please select @ as Invoice type, because you selected Tax Adjustment Item. */
    public static final String NFCM0906E = "NFCM0906E";
    // END 2019/06/05 S.Takami [QC#50685,ADD]

    // START 2019/06/05 S.Takami [QC#50683,ADD]
    /** Please click "S" button and choose a configuration from the popup window. */
    public static final String NFCM0907E = "NFCM0907E";

    // END 2019/06/05 S.Takami [QC#50683,ADD]
    // START 2019/06/06 S.Takami [QC#50691,ADD]
    /** Manual Invoice Supply Invoice Type */
    public static final String NFCL3000_SPLY_INV_TP = "NFCL3000_SPLY_INV_TP";

    /** Manual Invoice Supply Invoice Type Value */
    public static final String NFCL3000_SPLY_INV_TP_VAL = "0034,0057";

    /** Manual Invoice Supply DS Order Category */
    public static final String NFCL3000_SPLY_DS_ORD_CATG = "NFCL3000_SPLY_DS_ORD_CATG";

    /** Manual Invoice Supply DS Order Category Value */
    public static final String NFCL3000_SPLY_DS_ORD_CATG_VAL = "0009";
    // END 2019/06/06 S.Takami [QC#50691,ADD]
    
    // START 2020/04/22 [QC#56117,ADD]
    /** Since sales amount doesn't match meter charge amount, you cannot submit. */
    public static final String NFCM0913E = "NFCM0913E";
    // END   2020/04/22 [QC#56117,ADD]
    // START 2021/01/04 R.Kurahashi [QC#56282, ADD]
    /** Cannot mix Service/non-Service type Invoice data. Will not generate expected invoice/credit as we do not have all data necessary to display properly. */
    public static final String NFCM0916E = "NFCM0916E";

    /** Var Char Const : DEFAULT_FIN_BR */
    public static final String DEFAULT_FIN_BR = "NFAB1220_DEFAULT_FIN_BR";
    // END 2021/01/04 R.Kurahashi [QC#56282, ADD]
}
