/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3070.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NFCL3070Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/19   Fujitsu         S.Fujita        Update          N/A
 * 2016/06/28   Fujitsu         S.Fujita        Update          QC#10942
 * 2016/07/13   Fujitsu         S.Fujita        Update          QC#11445
 * 2018/05/30   Hitachi         E.Kameishi      Update          QC#26229
 * 2018/06/27   Hitachi         E.Kameishi      Update          QC#26906
 * 2019/07/24   Hitachi         Y.Takeno        Update          QC#51934
 * 2019/09/04   Fujitsu         T.Murai         Update          QC#52945
 * 2019/09/17   Fujitsu         S.Takami        Update          QC#53456
 *</pre>
 */
public class NFCL3070Constant {

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Message Kind warning */
    public static final String MESSAGE_KIND_WARN = "W";

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

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Customer invoice number does not exists. */
    public static final String NFCM0786E = "NFCM0786E";

    /**
     * More than one credits are already applied on the invoice. Do
     * you want to continue?
     */
    public static final String NFCM0787W = "NFCM0787W";

    /** Data insert failure. (table name is [@]) */
    public static final String NFCM0782E = "NFCM0782E";

    /**
     * It could not obtain invoice line. Please input other additional
     * line information.
     */
    public static final String NFCM0791W = "NFCM0791W";

    /** Please enter credit amount lower than @ amount. */
    public static final String NFCM0792E = "NFCM0792E";

    /** Please correct the warned field. */
    public static final String NFCM0793W = "NFCM0793W";

    /**
     * The total amount of Rebill invoice does not equals to amount of
     * CI Tickets.
     */
    public static final String NFCM0804E = "NFCM0804E";

    /** System error has occurred.  Please contact IT Support. */
    public static final String NFCM0041E = "NFCM0041E";

    /** The percentage entered is too low to calculate the amount. */
    public static final String NFCM0820E = "NFCM0820E";

    // START 2016/06/28 S.Fujita [QC#10942,ADD]
    /** "@" must be a negative number. */
    public static final String NFCM0846E = "NFCM0846E";
    // END   2016/06/28 S.Fujita [QC#10942,ADD]

    // START 2016/07/13 S.Fujita [QC#11445,ADD]
    /** Target Invoice data cannot be processed due to incompleteness. */
    public static final String NFCM0851E = "NFCM0851E";
    // END   2016/07/13 S.Fujita [QC#11445,ADD]

    // START 2016/10/24 T.Murai [QC#14760, ADD]
    /** This Customer invoice cannot be processed for the Invoice type of Credit Memo. */
    public static final String NFCM0870E = "NFCM0870E";
    // END   2016/10/24 T.Murai [QC#14760, ADD]
    // START 2018/05/30 E.Kameishi [QC#26229,ADD]
    /** Invalid CI Number. */
    public static final String NFCM0818E = "NFCM0818E";

    /** Credit Amount exceeds the original invoice amount. */
    public static final String NFCM0886E = "NFCM0886E";
    // END 2018/05/30 E.Kameishi [QC#26229,ADD]
    // START 2018/06/04 E.Kameishi [QC#25191,ADD]
    /** @ couldn't be completed by unexpected reason. The detailed error message: "@". */
    public static final String NFAM0035E = "NFAM0035E";
    // END 2018/06/04 E.Kameishi [QC#25191,ADD]

    // START 2018/06/27 E.Kameishi [QC#26906,ADD]
    /** Type of Credit does not match the type of Line specified. */
    public static final String NFCM0895E = "NFCM0895E";
    // END 2018/06/27 E.Kameishi [QC#26906,ADD]
    // START 2019/09/17 S.Takami [QC#53456,ADD]
    /** Error Message: NFCM0902E "Please set @, because you have chosen @ as @." */
    public static final String NFCM0902E = "NFCM0902E";
    // END 2019/09/17 S.Takami [QC#53456,ADD]
    // --------------------------------
    // Request Type
    // --------------------------------
    /** Request Type : Both */
    public static final BigDecimal RQST_TP_BOTH = BigDecimal.ONE;

    /** Request Type : Both */
    public static final BigDecimal RQST_TP_CREDIT_ONLY = BigDecimal.valueOf(2);

    /** Request Type : Both */
    public static final BigDecimal RQST_TP_REBILL_ONLY = BigDecimal.valueOf(3);

    // --------------------------------
    // Valuable
    // --------------------------------

    /** ONL_BAT_TP */
    public static final String ONL_BAT_TP = "1";

    /** Tickets Status (Customer Care Invoice) */
    public static final String CI_TICKET_STS_CLOSE = "CLOSE";

    /** Object Type (Customer Care Invoice) */
    public static final String CI_OBJECT_TP_INV = "INV_NUMBER";

    /** Dummy freight tax merchandise code varchar-const key */
    public static final String CONST_KEY_FRT_TAX_DUMMY_MDSE_CD = "FRT_TAX_DUMMY_MDSE_CD";

    /** default transaction type for tax varchar const key */
    public static final String CONST_KEY_DEFAULT_TAX_TRX_TP = "DEFAULT_TAX_TRX_TP";

    /** frt tax detail line number */
    public static final String CONST_FRT_TAX_LINE_NUM = "*";

    /** pulldownIdx */
    public static final int PIDX = 100;

    /** hundred */
    public static final int HIDX = 100;

    /** ten */
    public static final int CIDX = 10;

    /** scale */
    public static final int SIDX = 8;

    // START 2018/05/30 E.Kameishi [QC#26229,ADD]
    /** scale */
    public static final int SCL_4 =4;
    // END 2018/05/30 E.Kameishi [QC#26229,ADD]

    // START 2019/07/24 [QC#51934, ADD]
    /** COMP_PROC_STS : incomplete */
    public static final String COMP_PROC_STS_INCOMPLETE = "0";
    // END   2019/07/24 [QC#51934, ADD]
    // START 2019/09/04 [QC#55945, ADD]
    public static final String INIT_INV_BOL_LINE_NUM = "001";
    // END   2019/09/04 [QC#55945, ADD]

    // START 2019/09/17 S.Takami [QC#53456,ADD]
    /** Set Parent INV_LINE_SUB_NUM */
    public static final String SET_PRNT_INV_LINE_SUB_NUM = "000";
    // END 2019/09/17 S.Takami [QC#53456,ADD]
}
