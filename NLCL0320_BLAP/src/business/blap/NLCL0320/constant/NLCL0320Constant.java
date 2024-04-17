/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.blap.NLCL0320.constant;


/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public interface NLCL0320Constant {

    enum MESSAGE_ID {
        NZZM0002I, NZZM0003E, NMAM0007I, NMAM0098I, NMAM0009E, NMAM0010E, NMAM0050E, NMAM0072E, NMAM0177E, 
        NMAM8054E, NLAM8140E, NWDM0099E, NWDM0134E, NWDM0223E, NLBM1295E, NFCM0063E, NFCM0504E, NFCM0764E, 
        NFAM0075E;
    }

    /** The format of [@] is incorrect. */
    public static final String NPAM1193E = "NPAM1193E";

    /** Maximum number of digits exceeded.[@] */
    public static final String NPAM1320E = "NPAM1320E";

    String LAST_DATE = "99991231";

    // =================================================
    // 9seg token
    // =================================================
    /** segment token list index : COA_CMPY_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD = 0;

    /** segment token list index : COA_BR_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD = 1;

    /** segment token list index : COA_CC_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD = 2;

    /** segment token list index : COA_ACCT_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD = 3;

    /** segment token list index : COA_PROD_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD = 4;

    /** segment token list index : COA_PROD_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD = 5;

    /** segment token list index : COA_PROD_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD = 6;

    /** segment token list index : COA_PROJ_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD = 7;

    /** segment token list index : COA_EXTN_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD = 8;

    /** segment element length : COA_CMPY_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CMPY = 3;

    /** segment element length : COA_EXTN_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_EXTN = 3;

    /** segment element length : COA_CC_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CC = 6;

    /** segment element length : COA_ACCT_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_ACCT = 8;

    /** segment element length : COA_PROJ_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_PROJ = 4;

    /** segment element length : COA_PROD_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_PROD = 8;

    /** segment element length : COA_AFFL_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_AFFL = 3;

    /** segment element length : COA_CH_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CH = 3;

    /** segment element length : COA_BR_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_BR = 3;

    /** delimiter string */
    public static final String VAR_CHAR_KEY_DELIMITER = "CONT_DELIMITER";

    /** segment token list size. */
    public static final int SEGMENT_TOKEN_LIST_SIZE = 9;

    // =================================================
    // 9seg
    // =================================================
    /** message parameter : Segment */
    public static final String MSG_PARAM_SEGMENT = "Segment";

    /** message parameter : Company */
    public static final String MSG_PARAM_CMPY = "Company";

    /** message parameter : Extension */
    public static final String MSG_PARAM_EXTN = "Extension";

    /** message parameter : Cost Center */
    public static final String MSG_PARAM_CC = "Cost Center";

    /** message parameter : Account */
    public static final String MSG_PARAM_ACCT = "Account";

    /** message parameter : Company */
    public static final String MSG_PARAM_PROJ = "Project";

    /** message parameter : Product */
    public static final String MSG_PARAM_PROD = "Product";

    /** message parameter : Affiliate */
    public static final String MSG_PARAM_AFFL = "Affiliate";

    /** message parameter : Channel */
    public static final String MSG_PARAM_CH = "Channel";

    /** message parameter : Branch */
    public static final String MSG_PARAM_BR = "Branch";

    // =================================================
    // DB Column
    // =================================================
    /**
     * DB Column Name: COA Company Code
     */
    public static final String DB_COLUMN_COA_CMPY_CD = "COA_CMPY_CD";

    /**
     * DB Column Name: COA Affiliate Code
     */
    public static final String DB_COLUMN_COA_AFFL_CD = "COA_AFFL_CD";

    /**
     * DB Column Name: COA Branch Code
     */
    public static final String DB_COLUMN_COA_BR_CD = "COA_BR_CD";

    /**
     * DB Column Name: COA CostCentor Code
     */
    public static final String DB_COLUMN_COA_CC_CD = "COA_CC_CD";

    /**
     * DB Column Name: COA Account Code
     */
    public static final String DB_COLUMN_COA_ACCT_CD = "COA_ACCT_CD";

    /**
     * DB Column Name: COA Product Code
     */
    public static final String DB_COLUMN_COA_PROD_CD = "COA_PROD_CD";

    /**
     * DB Column Name: COA Channel Code
     */
    public static final String DB_COLUMN_COA_CH_CD = "COA_CH_CD";

    /**
     * DB Column Name: COA Project Code
     */
    public static final String DB_COLUMN_COA_PROJ_CD = "COA_PROJ_CD";

    /**
     * DB Column Name: COA Extension Code
     */
    public static final String DB_COLUMN_COA_EXTN_CD = "COA_EXTN_CD";

}
