/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL2650.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         M.Ohno          Create          N/A
 * 2016/10/20   Hitachi         K.Kojima        Update          QC#13259
 * 2018/05/15   Fujitsu         Y.Matsui        Update          QC#24329
 * 2020/01/16   Fujitsu         M.Ishii            Update          QC#55216
 *</pre>
 */
public class NFCL2650Constant {

    /** Business ID */
    public static final String BIZ_ID = "NFCL2650";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NFCL2650Scrn00";

    /** From */
    public static final String FROM = "From";

    /** To */
    public static final String TO = "To";

    /** 02 */
    public static final String PROC_MODE_CD = "02";

    /** 1 */
    public static final String ONL_BAT_TP = "1";

    /** NFCL2650_STMT_CRAT_MAX_CNT1 */
    public static final String DRAFT_CRAT_MAX_CNT_CD = "NFCL2650_STMT_CRAT_MAX_CNT";

    // --------------------------------
    // Message
    // --------------------------------
    /** Search results exceeded @. Please modify search criteria. */
    public static final String NFCM0798E = "NFCM0798E";

    /** No search results found. Please modify the search criteria. */
    public static final String NFCM0803E = "NFCM0803E";

    /** Parameter [@] = [@] does not exists in [@]. */
    public static final String NFCM0807E = "NFCM0807E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    // START 2018/05/15 [QC#24329,ADD]
    /** Target Data for Statement does not exist. */
    public static final String NFCM0831E = "NFCM0831E";
    // END   2018/05/15 [QC#24329,ADD]

    // START   2020/01/16 [QC#55216,ADD]
    /** The collector assigned to Customer#([@]) has been terminated. Please assign new collector. */
    public static final String NFCM0910E = "NFCM0910E";

    /** String COMMA */
    public static final String COMMA = ",";
    // END   2020/01/16 [QC#55216,ADD]
    // --------------------------------
    // Message Param
    // --------------------------------
    // START 2016/10/20 K.Kojima [QC#13259,MOD]
    // /** Bill to Customer Account From */
    // public static final String BILL_TO_CUST_ACCT_FR =
    // "Bill to Customer Account From";
    //
    // /** Bill to Customer Account From */
    // public static final String BILL_TO_CUST_ACCT_TO =
    // "Bill to Customer Account To";
    //
    // /** Bill to Customer Account Name From */
    // public static final String BILL_TO_CUST_ACCT_NM_FR =
    // "Bill to Customer Account Name From";
    //
    // /** Bill to Customer Account Name To */
    // public static final String BILL_TO_CUST_ACCT_NM_TO =
    // "Bill to Customer Account Name To";
    //
    // /** Bill to Customer Code */
    // public static final String BILL_TO_CUST_CD =
    // "Bill to Customer Code";
    /** Bill to Customer Account From */
    public static final String BILL_TO_CUST_ACCT_FR = "Customer Number From";

    /** Bill to Customer Account From */
    public static final String BILL_TO_CUST_ACCT_TO = "Customer Number To";

    /** Bill to Customer Account Name From */
    public static final String BILL_TO_CUST_ACCT_NM_FR = "Customer Name From";

    /** Bill to Customer Account Name To */
    public static final String BILL_TO_CUST_ACCT_NM_TO = "Customer Name To";

    /** Bill to Customer Code */
    public static final String BILL_TO_CUST_CD = "Location";

    // END 2016/10/20 K.Kojima [QC#13259,MOD]

    /** SELL_TO_CUST */
    public static final String SELL_TO_CUST = "SELL_TO_CUST";

    /** BILL_TO_CUST */
    public static final String BILL_TO_CUST = "BILL_TO_CUST";
}
