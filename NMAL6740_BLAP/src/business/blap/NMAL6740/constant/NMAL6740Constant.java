/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6740.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/20   CUSA            Fujitsu         Create          N/A
 * 2015/10/13   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/18   Fujitsu         C.Tanaka        Update          QC#2440
 * 2016/05/05   SRAA            Y.Chen          Update          QC#6382
 * 2018/04/16   Fujitsu         M.Ohno          Update          QC#24635
 *</pre>
 */
public class NMAL6740Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL6740";

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /** Data update failure.(ReturnCode = [@]) */
    public static final String ZZZM9013E = "ZZZM9013E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** The target data for the update does not exist. */
    public static final String NMAM8105E = "NMAM8105E";

    /** The entered [@] does not exist in master. */
    public static final String NMAM8121E = "NMAM8121E";

    /** To change has been made. */
    public static final String NMAM8333I = "NMAM8333I";
    
    // QC#6382
    /** The data @ does not exist in the master. */
    public static final String NMAM8186E = "NMAM8186E";

    /** Message Kind warning */
    public static final String MESSAGE_KIND_WAR = "W";

    /** COA_CH */
    public static final String COA_CH = "Sales Channel";

    /** COA_AFFL */
    public static final String COA_AFFL = "Intercompany";

    // 2018/04/16 QC#24635 add start
    /** Number of digits over error (item name [@]). */
    public static final String NMAM8681E = "NMAM8681E";

    /** The format of [@] is incorrect. */
    public static final String NMAM0052E = "NMAM0052E";

    /** segment token list size. */
    public static final int SEGMENT_TOKEN_LIST_SIZE = 9;

    /** segment element length : COA_CMPY_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CMPY = 3;

    /** segment element length : COA_EXTN_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_EXTN = 3;

    /** segment element length : COA_CC_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CC = 6;

    /** segment element length : COA_ACCT_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_ACCT = 8;

    /** segment element length : COA_PROJ_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_PROJ = 2;

    /** segment element length : COA_PROD_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_PROD = 2;

    /** segment element length : COA_AFFL_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_AFFL = 3;

    /** segment element length : COA_CH_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CH = 3;

    /** segment element length : COA_BR_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_BR = 3;
    // 2018/04/16 QC#24635 add end

}
