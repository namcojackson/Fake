/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3050.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NFCL3050Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/17   Fujitsu         S.Fujita        Create          CSA
 * 2016/03/23   Hitachi         T.Tsuchida      Update          QC#5859
 * 2016/04/25   Fujitsu         S.Fujita        Update          QC#5047
 * 2016/04/26   Fujitsu         S.Fujita        Update          QC#7586
 * 2016/05/19   Fujitsu         S.Fujita        Update          QC#7780
 * 2016/06/27   Hitachi         K.Kojima        Update          QC#8026
 * 2016/07/12   Hitachi         K.Kojima        Update          QC#11049
 * 2016/08/23   Fujitsu         S.Fujita        Update          QC#13478
 * 2017/09/27   Hitachi         T.Tsuchida      Update          QC#21373
 * 2018/05/28   Fujitsu         Y.Matsui        Update          QC#26342
 * 2022/11/29   Hitachi         M.Nakajima      Update          QC#60742
 *</pre>
 */
public class NFCL3050Constant {

    /** Business ID */
    public static final String BIZ_ID = "NFCL3050";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NFCL3050Scrn00";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** @ couldn't be completed by unexpected reason. The detailed error message: "@". */
    public static final String NFAM0035E = "NFAM0035E";

    /** Failed to update [@]. */
    public static final String NFCM0615E = "NFCM0615E";

    /** @ has been updated by another user. */
    public static final String NFCM0594E = "NFCM0594E";

    /** No record found. */
    public static final String NFCM0048I = "NFCM0048I";

    // --------------------------------
    // CSV File Download
    // --------------------------------
    /** CSV File Name Download */
    public static final String CSV_FILE_NAME_DOWNLOAD = "NFCL3050_CustomerAccountSearch";

    /** CSV Header For Download */
    // START 2016/04/26 S.Fujita [QC#7586,MOD]
    // START 2016/06/24 K.Kojima [QC#8026,MOD]
    // START 2016/07/12 K.Kojima [QC#11049,MOD]
    // START 2018/05/28 Y.Matsui [QC#26342,MOD]
    // START 2022/11/28 M.Nakajima [QC#60742,MOD]
    public static final String[] CSV_DOWNLOAD_HEADER = new String[] {"ChkBox", "RadioBtn", "Customer Name", "Cust Num", "Invoice Num", "Invoice Dt", "Due Date", "Invoice Amount", "Balance", "Sales Order /Contract#", "Sales Rep", "Collector Name", "Invoice Type", "Source", "Invoice Class", "Batch Source", "Status", "Dispute Amt", "Dispute Date", "GL Date", "Cmp", "Customer PO#", "Invoice Error Message Text", "Invalid Value Text" };
    // END 2022/11/28 M.Nakajima [QC#60742,MOD]
    // END 2018/05/28 Y.Matsui [QC#26342,MOD]
    // END 2016/07/12 K.Kojima [QC#11049,MOD]
    // END 2016/06/24 K.Kojima [QC#8026,MOD]
    // END 2016/04/26 S.Fujita [QC#7586,MOD]

    // START 2016/07/12 K.Kojima [QC#11049,ADD]
    // START 2018/05/28 Y.Matsui [QC#26342,MOD]
    // START 2022/11/28 M.Nakajima [QC#60742,MOD]
    /** CSV Header For Download(General) */
    public static final String[] CSV_DOWNLOAD_HEADER_GENERAL = new String[] {"RadioBtn", "Customer Name", "Cust Num", "Invoice Num", "Invoice Dt", "Due Date", "Invoice Amount", "Balance", "Sales Order /Contract#", "Sales Rep", "Collector Name",
            "Invoice Type", "Source", "Invoice Class", "Batch Source", "Status", "Dispute Amt", "Dispute Date", "GL Date", "Cmp", "Customer PO#" };
    // END 2022/11/28 M.Nakajima [QC#60742,MOD]
    // END 2018/05/28 Y.Matsui [QC#26342,MOD]
    // END 2016/07/12 K.Kojima [QC#11049,ADD]

    // --------------------------------
    // Check Box Value
    // --------------------------------
    // START 2016/03/23 T.Tsuchida [QC#5859,MOD]
//    /** Unapplied */
//    public static final String UNAPPLIED = "U";
//
//    /** Partial */
//    public static final String PARTIAL = "P";
    // END 2016/03/23 T.Tsuchida [QC#5859,MOD]

    // START 2016/04/25 S.Fujita [QC#5047,MOD]
    // --------------------------------
    // Fuzzy Search Value
    // --------------------------------
    /** percent. */
    public static final String PERCENT = "%";
    // END 2016/04/25 S.Fujita [QC#5047,MOD]

    // START 2016/05/19 S.Fujita [QC#7780,ADD]
    /**
     */
    public static final int YEAR_INDEX = 0;

    /**
     */
    public static final int MONTH_INDEX = 1;

    /** Accounting Date is not in open GL periods. */
    public static final String NFCM0844E = "NFCM0844E";
    // END 2016/05/19 S.Fujita [QC#7780,ADD]

    // START 2016/07/12 K.Kojima [QC#11049,ADD]
    /** DisplayPattern : Invisibility Error */
    public static final String DIS_PAT_INVISIBILITY_ERROR = "InvisibilityError";

    /** DisplayPattern : Visibility Error */
    public static final String DIS_PAT_VISIBILITY_ERROR = "VisibilityError";
    // END 2016/07/12 K.Kojima [QC#11049,ADD]

    // START 2016/08/23 S.Fujita [QC#13478,ADD]
    /** Completed Type : Complete */
    public static final BigDecimal COMP_TP_COMP = BigDecimal.ONE;
    // END   2016/08/23 S.Fujita [QC#13478,ADD]

    // START 2017/09/27 T.Tsuchida [QC#21373,ADD]
    /** Include Closed Invoices : Include Closed Invoices */
    public static final String INCLUDE_CLOSED_INVOICES = "INCLUDE_CLOSED_INVOICES";
    // END 2017/09/27 T.Tsuchida [QC#21373,ADD]
}
