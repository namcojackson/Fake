/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3140.constant;

/**
 *<pre>
 * Invoice Type Setup screen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/25   Hitachi         K.Kojima        Create          N/A
 * 2016/05/23   Hitachi         T.Tsuchida      Update          QC#7404
 * 2016/11/25   Hitachi         J.Kim           Update          QC#16240
 *</pre>
 */
public class NFCL3140Constant {

    /** Pulldown size */
    // START 2016/05/25 T.Tsuchida [QC#7404,MOD]
    //public static final int PULLDOWN_SIZE = 3;
    public static final int PULLDOWN_SIZE = 99;
    // END 2016/05/25 T.Tsuchida [QC#7404,MOD]

    /** Message ID : NFCM0580E */
    public static final String NFCM0580E = "NFCM0580E";

    /** Message ID : NFCM0836E */
    public static final String NFCM0836E = "NFCM0836E";

    /** Message ID : NFCM0840E */
    public static final String NFCM0840E = "NFCM0840E";

    /** Message ID : NFCM0841E */
    public static final String NFCM0841E = "NFCM0841E";

    /** Message ID : NZZM0010E */
    public static final String NZZM0010E = "NZZM0010E";

    /** Message ID : NFCM0573E */
    public static final String NFCM0573E = "NFCM0573E";

    /** Message ID : ZZM9000E */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message ID : ZZZM9004E */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** ELIG_COA_SEG_PTRN_CD : COA_ACCT_CD */
    public static final String ELIG_COA_SEG_PTRN_COA_ACCT_CD = "COA_ACCT_CD";

    /** ELIG_COA_SEG_PTRN_CD : COA_AFFL_CD */
    public static final String ELIG_COA_SEG_PTRN_COA_AFFL_CD = "COA_AFFL_CD";

    /** ELIG_COA_SEG_PTRN_CD : COA_BR_CD */
    public static final String ELIG_COA_SEG_PTRN_COA_BR_CD = "COA_BR_CD";

    /** ELIG_COA_SEG_PTRN_CD : COA_CC_CD */
    public static final String ELIG_COA_SEG_PTRN_COA_CC_CD = "COA_CC_CD";

    /** ELIG_COA_SEG_PTRN_CD : COA_CH_CD */
    public static final String ELIG_COA_SEG_PTRN_COA_CH_CD = "COA_CH_CD";

    /** ELIG_COA_SEG_PTRN_CD : COA_CMPY_CD */
    public static final String ELIG_COA_SEG_PTRN_COA_CMPY_CD = "COA_CMPY_CD";

    /** ELIG_COA_SEG_PTRN_CD : COA_EXTN_CD */
    public static final String ELIG_COA_SEG_PTRN_COA_EXTN_CD = "COA_EXTN_CD";

    /** ELIG_COA_SEG_PTRN_CD : COA_PROD_CD */
    public static final String ELIG_COA_SEG_PTRN_COA_PROD_CD = "COA_PROD_CD";

    /** ELIG_COA_SEG_PTRN_CD : COA_PROJ_CD */
    public static final String ELIG_COA_SEG_PTRN_COA_PROJ_CD = "COA_PROJ_CD";

    /** Screen Name : Receivable */
    private static final String SCREEN_RECEIVABLE = "Receivable";

    /** Screen Name : Revenue */
    private static final String SCREEN_REVENUE = "Revenue";

    /** Screen Name : Tax */
    private static final String SCREEN_TAX = "Tax";

    /** Screen Name : Unearned Revenue */
    private static final String SCREEN_UNEARNED_REVENUE = "Unearned Revenue";

    /** Screen Name : - Company */
    private static final String SCREEN_COMPANY = " - Company";

    /** Screen Name : - Branch */
    private static final String SCREEN_BRANCH = " - Branch";

    /** Screen Name : - Cost Center */
    private static final String SCREEN_COST_CENTER = " - Cost Center";

    /** Screen Name : - Account */
    private static final String SCREEN_ACCOUNT = " - Account";

    /** Screen Name : - Product */
    private static final String SCREEN_PRODUCT = " - Product";

    /** Screen Name : - Channel */
    private static final String SCREEN_CHANNEL = " - Channel";

    // START 2016/11/25 J.Kim [QC#16240,MOD]
    // /** Screen Name : - Affiliate */
    // private static final String SCREEN_AFFILIATE = " - Affiliate";
    /** Screen Name : - Intercompany */
    private static final String SCREEN_INTERCOMPANY = " - Intercompany";
    // END 2016/11/25 J.Kim [QC#16240,MOD]

    /** Screen Name : - M'dise Type */
    private static final String SCREEN_MDISE_TYPE = " - M'dise Type";

    /** Screen Name : - Business Unit */
    private static final String SCREEN_BUSINESS_UNIT = " - Business Unit";

    /** Screen Name : Receivable - Company */
    public static final String SCREEN_RECEIVABLE_COMPANY = SCREEN_RECEIVABLE + SCREEN_COMPANY;

    /** Screen Name : Receivable - Branch */
    public static final String SCREEN_RECEIVABLE_BRANCH = SCREEN_RECEIVABLE + SCREEN_BRANCH;

    /** Screen Name : Receivable - Cost Center */
    public static final String SCREEN_RECEIVABLE_COST_CENTER = SCREEN_RECEIVABLE + SCREEN_COST_CENTER;

    /** Screen Name : Receivable - Account */
    public static final String SCREEN_RECEIVABLE_ACCOUNT = SCREEN_RECEIVABLE + SCREEN_ACCOUNT;

    /** Screen Name : Receivable - Product */
    public static final String SCREEN_RECEIVABLE_PRODUCT = SCREEN_RECEIVABLE + SCREEN_PRODUCT;

    /** Screen Name : Receivable - Channel */
    public static final String SCREEN_RECEIVABLE_CHANNEL = SCREEN_RECEIVABLE + SCREEN_CHANNEL;

    // START 2016/11/25 J.Kim [QC#16240,MOD]
    // /** Screen Name : Receivable - Affiliate */
    // public static final String SCREEN_RECEIVABLE_AFFILIATE = SCREEN_RECEIVABLE + SCREEN_AFFILIATE;
    /** Screen Name : Receivable - Intercompany */
    public static final String SCREEN_RECEIVABLE_INTERCOMPANY = SCREEN_RECEIVABLE + SCREEN_INTERCOMPANY;
    // END 2016/11/25 J.Kim [QC#16240,MOD]

    /** Screen Name : Receivable - M'Dise Type */
    public static final String SCREEN_RECEIVABLE_MDISE_TYPE = SCREEN_RECEIVABLE + SCREEN_MDISE_TYPE;

    /** Screen Name : Receivable - Business Unit */
    public static final String SCREEN_RECEIVABLE_BUSINESS_UNIT = SCREEN_RECEIVABLE + SCREEN_BUSINESS_UNIT;

    /** Screen Name : Revenue - Company */
    public static final String SCREEN_REVENUE_COMPANY = SCREEN_REVENUE + SCREEN_COMPANY;

    /** Screen Name : Revenue - Branch */
    public static final String SCREEN_REVENUE_BRANCH = SCREEN_REVENUE + SCREEN_BRANCH;

    /** Screen Name : Revenue - Cost Center */
    public static final String SCREEN_REVENUE_COST_CENTER = SCREEN_REVENUE + SCREEN_COST_CENTER;

    /** Screen Name : Revenue - Account */
    public static final String SCREEN_REVENUE_ACCOUNT = SCREEN_REVENUE + SCREEN_ACCOUNT;

    /** Screen Name : Revenue - Product */
    public static final String SCREEN_REVENUE_PRODUCT = SCREEN_REVENUE + SCREEN_PRODUCT;

    /** Screen Name : Revenue - Channel */
    public static final String SCREEN_REVENUE_CHANNEL = SCREEN_REVENUE + SCREEN_CHANNEL;

    // START 2016/11/25 J.Kim [QC#16240,MOD]
    // /** Screen Name : Revenue - Affiliate */
    // public static final String SCREEN_REVENUE_AFFILIATE = SCREEN_REVENUE + SCREEN_AFFILIATE;
    /** Screen Name : Revenue - Intercompany */
    public static final String SCREEN_REVENUE_INTERCOMPANY = SCREEN_REVENUE + SCREEN_INTERCOMPANY;
    // END 2016/11/25 J.Kim [QC#16240,MOD]

    /** Screen Name : Revenue - M'Dise Type */
    public static final String SCREEN_REVENUE_MDISE_TYPE = SCREEN_REVENUE + SCREEN_MDISE_TYPE;

    /** Screen Name : Revenue - Business Unit */
    public static final String SCREEN_REVENUE_BUSINESS_UNIT = SCREEN_REVENUE + SCREEN_BUSINESS_UNIT;

    /** Screen Name : Tax - Company */
    public static final String SCREEN_TAX_COMPANY = SCREEN_TAX + SCREEN_COMPANY;

    /** Screen Name : Tax - Branch */
    public static final String SCREEN_TAX_BRANCH = SCREEN_TAX + SCREEN_BRANCH;

    /** Screen Name : Tax - Cost Center */
    public static final String SCREEN_TAX_COST_CENTER = SCREEN_TAX + SCREEN_COST_CENTER;

    /** Screen Name : Tax - Account */
    public static final String SCREEN_TAX_ACCOUNT = SCREEN_TAX + SCREEN_ACCOUNT;

    /** Screen Name : Tax - Product */
    public static final String SCREEN_TAX_PRODUCT = SCREEN_TAX + SCREEN_PRODUCT;

    /** Screen Name : Tax - Channel */
    public static final String SCREEN_TAX_CHANNEL = SCREEN_TAX + SCREEN_CHANNEL;

    // START 2016/11/25 J.Kim [QC#16240,MOD]
    // /** Screen Name : Tax - Affiliate */
    // public static final String SCREEN_TAX_AFFILIATE = SCREEN_TAX + SCREEN_AFFILIATE;
    /** Screen Name : Tax - Intercompany */
    public static final String SCREEN_TAX_INTERCOMPANY = SCREEN_TAX + SCREEN_INTERCOMPANY;
    // END 2016/11/25 J.Kim [QC#16240,MOD]

    /** Screen Name : Tax - M'Dise Type */
    public static final String SCREEN_TAX_MDISE_TYPE = SCREEN_TAX + SCREEN_MDISE_TYPE;

    /** Screen Name : Tax - Business Unit */
    public static final String SCREEN_TAX_BUSINESS_UNIT = SCREEN_TAX + SCREEN_BUSINESS_UNIT;

    /** Screen Name : Unearned_revenue - Company */
    public static final String SCREEN_UNEARNED_REVENUE_COMPANY = SCREEN_UNEARNED_REVENUE + SCREEN_COMPANY;

    /** Screen Name : Unearned_revenue - Branch */
    public static final String SCREEN_UNEARNED_REVENUE_BRANCH = SCREEN_UNEARNED_REVENUE + SCREEN_BRANCH;

    /** Screen Name : Unearned_revenue - Cost Center */
    public static final String SCREEN_UNEARNED_REVENUE_COST_CENTER = SCREEN_UNEARNED_REVENUE + SCREEN_COST_CENTER;

    /** Screen Name : Unearned_revenue - Account */
    public static final String SCREEN_UNEARNED_REVENUE_ACCOUNT = SCREEN_UNEARNED_REVENUE + SCREEN_ACCOUNT;

    /** Screen Name : Unearned_revenue - Product */
    public static final String SCREEN_UNEARNED_REVENUE_PRODUCT = SCREEN_UNEARNED_REVENUE + SCREEN_PRODUCT;

    /** Screen Name : Unearned_revenue - Channel */
    public static final String SCREEN_UNEARNED_REVENUE_CHANNEL = SCREEN_UNEARNED_REVENUE + SCREEN_CHANNEL;

    // START 2016/11/25 J.Kim [QC#16240,MOD]
    // /** Screen Name : Unearned_revenue - Affiliate */
    // public static final String SCREEN_UNEARNED_REVENUE_AFFILIATE = SCREEN_UNEARNED_REVENUE + SCREEN_AFFILIATE;
    /** Screen Name : Unearned_revenue - Intercompany */
    public static final String SCREEN_UNEARNED_REVENUE_INTERCOMPANY = SCREEN_UNEARNED_REVENUE + SCREEN_INTERCOMPANY;
    // END 2016/11/25 J.Kim [QC#16240,MOD]

    /** Screen Name : Unearned_revenue - M'Dise Type */
    public static final String SCREEN_UNEARNED_REVENUE_MDISE_TYPE = SCREEN_UNEARNED_REVENUE + SCREEN_MDISE_TYPE;

    /** Screen Name : Unearned_revenue - Business Unit */
    public static final String SCREEN_UNEARNED_REVENUE_BUSINESS_UNIT = SCREEN_UNEARNED_REVENUE + SCREEN_BUSINESS_UNIT;

}
