/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0540.constant;

/**
 *<pre>
 * Solution Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/11   Hitachi         T.Aoyagi        Create          N/A
 * 2016/02/19   Hitachi         K.Kasai         Update          QC#3689
 * 2016/09/20   Hitachi         N.Arai          Update          QC#11616
 *</pre>
 */
public class NSAL0540Constant {

    /**
     * Business ID
     */
    public static final String BUSINESS_ID = "NSAL0540";

    /**
     * Timestamp store pattern
     */
    public static final String TS_STORE_PATTERN = "yyyyMMddHHmmssSSS";

    /**
     * Message Kind Error
     */
    public static final String ERROR = "E";

    /**
     * Table Name : SVC_MEMO
     */
    public static final String SVC_MEMO = "SVC_MEMO";

    /**
     * DB Column : SVC_SLN_SQ
     */
    public static final String SVC_SLN_SQ = "SVC_SLN_SQ";

    /**
     * DB Column : SVC_SLN_NM
     */
    public static final String SVC_SLN_NM = "SVC_SLN_NM";

    /**
     * DB Column : SVC_CONFIG_MSTR_PK
     */
    public static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /**
     * DB Column : SER_NUM
     */
    public static final String SER_NUM = "SER_NUM";

    /**
     * DB Column : MDSE_CD
     */
    public static final String MDSE_CD = "MDSE_CD";

// START 2016/09/20 N.Arai [QC#11616, MOD]
    /**
     * DB Column : MDSE_NM
     */
    // public static final String MDSE_NM = "MDSE_NM";

    /**
     * DB Column : MDSE_DESC_SHORT_TXT
     */
    public static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";
// END 2016/09/20 N.Arai [QC#11616, MOD]

    /**
     * DB Column : T_MDL_NM
     */
    public static final String T_MDL_NM = "T_MDL_NM";

    /**
     * DB Column : SVC_MACH_MSTR_PK
     */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /**
     * DB Column : PRNT_SVC_MACH_MSTR_PK
     */
    public static final String PRNT_SVC_MACH_MSTR_PK = "PRNT_SVC_MACH_MSTR_PK";

    /**
     * DB Column : ISTL_DT
     */
    public static final String ISTL_DT = "ISTL_DT";

    /**
     * DB Column : BILL_TO_LOC_NM
     */
    public static final String BILL_TO_LOC_NM = "BILL_TO_LOC_NM";

    /**
     * DB Column : SHIP_TO_LOC_NM
     */
    public static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    /**
     * DB Column : SELL_TO_LOC_NM
     */
    public static final String SELL_TO_LOC_NM = "SELL_TO_LOC_NM";

    /**
     * DB Column : EZUPTIME
     */
    public static final String EZUPTIME = "EZUPTIME";

    /**
     * DB Column : EZUPTIMEZONE
     */
    public static final String EZUPTIMEZONE = "EZUPTIMEZONE";

    /**
     * DB Column : SVC_MEMO_PK
     */
    public static final String SVC_MEMO_PK = "SVC_MEMO_PK";

    /**
     * DB Column : SVC_CMNT_TXT
     */
    public static final String SVC_CMNT_TXT = "SVC_CMNT_TXT";

    /**
     * Please select at least 1 checkbox.
     */
    public static final String NSAM0015E = "NSAM0015E";

    /**
     * Search results exceeded [@]. Only showing first @ records.
     */
    public static final String NSAM0024W = "NSAM0024W";

    /**
     * No search results found.
     */
    public static final String NSAM0194I = "NSAM0194I";

    /**
     * The process has been successfully completed.
     */
    public static final String NSAM0200I = "NSAM0200I";

    /**
     * You are about to remove a configuration from the solution. 
     */
    public static final String NSAM0363W = "NSAM0363W";

    /**
     * The configurations exceeded [@].
     */
    public static final String NSAM0364E = "NSAM0364E";

    /**
     * No target data exists.
     */
    public static final String NSAM0020E = "NSAM0020E";

    /**
     * Failed to update "@".
     */
    public static final String NSAM0031E = "NSAM0031E";

    /**
     * Failed to insert "@".
     */
    public static final String NSAM0032E = "NSAM0032E";

    /**
     * This data has been updated by another user.
     */
    public static final String NZZM0003E = "NZZM0003E";

    // add start 2016/02/22 CSA Defect#3689
    /**
     * VAR_CHAR_CONST:SVC_MEMO_RSN_FOR_SLN
     */
    public static final String SVC_MEMO_RSN_FOR_SLN = "SVC_MEMO_RSN_FOR_SLN";
    // add end 2016/02/22 CSA Defect#3689
}
