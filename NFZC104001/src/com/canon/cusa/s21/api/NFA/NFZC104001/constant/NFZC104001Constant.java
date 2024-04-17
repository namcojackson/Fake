/**<pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.api.NFA.NFZC104001.constant;

/**
 * <pre>
 * Journal Entry Creation API
 * NFZC104001Constant.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/08   Fujitsu         S.Fujita        Create          N/A.
 * 2016/06/28   Hitachi         T.Mizuki        Update          N/A.
 * 2016/08/05   Fujitsu         C.Tanaka        Update          QC#13077
 * </pre>
 */
public class NFZC104001Constant {

    /** AJE_INTFC_TP_CD */
    public static final String AJE_INTFC_TP_CD_MAN = "99";

    /** AJE_ID */
    public static final String AJE_ID_MAN = "NFA-999-99";

    /** SYS_SRC_CD -NFA- */
    public static final String NFA = "NFA";

    /** TRX_CD */
    public static final String TRX_CD_MAN = "999";

    /** TRX_NM */
    public static final String TRX_NM_MAN = "Manual";

    /** TRX_RSN_CD */
    public static final String TRX_RSN_CD_MAN = "99";

    /** TRX_RSN_NM */
    public static final String TRX_RSN_NM_MAN = "Manual";

    /** AJE_LINE_IDX_NUM */
    public static final String IDX_NUM = "01";

    /** DR_CR_TP_DEBIT */
    public static final String DR_CR_TP_DEBIT = "D";

    // QC#13077 ADD Start
    /** JRNL_ENTRY_SCD_ATTRB_NM */
    public static final String JRNL_ENTRY_SCD_ATTRB_NM = "Journal Name";
    // QC#13077 ADD End

    // START 2016/09/01 J.Kim [QC#12851,ADD]
    public static final int INSERT_MAX_COUNT = 1000;
    // END 2016/09/01 J.Kim [QC#12851,ADD]

    //--------------
    // Message ID
    //--------------
    /** Input Parameter "Global Company Code"  is a mandatory field. */
    public static final String NFZM0021E = "NFZM0021E";

    /** Input Parameter "Process Date"  is a mandatory field. */
    public static final String NFZM0022E = "NFZM0022E";

    /** Input Parameter "Manual Journal Entry Header PK"  is a mandatory field. */
    public static final String NFZM0023E = "NFZM0023E";

    /** It failed to register. */
    public static final String NFZM0013E = "NFZM0013E";

    /** "System Source Name" could not be obtained. */
    public static final String NFZM0024E = "NFZM0024E";

    /** "Standard Currency Code" could not be obtained. */
    public static final String NFZM0025E = "NFZM0025E";

    // mod start 2016/06/28 CSA Defect#
    /** The record of JRNL_ENTRY already exists in the database. */
    public static final String NFZM0026E = "NFZM0026E";
    // mod end 2016/06/28 CSA Defect#
}
