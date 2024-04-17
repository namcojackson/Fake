package business.blap.NSAL1140.constant;

/**
 *<pre>
 * Supply Watch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Hitachi         T.Nishimura     Create          N/A
 * 2016/03/28   Hitachi         A.Kohinata      Update          QC#6053
 * 2016/10/14   Hitachi         T.Tomita        Update          QC#15196
 * 2017/05/24   Hitachi         K.Kitachi       Update          QC#18315
 * 2017/07/26   Hitachi         U.Kim           Update          QC#18316
 *</pre>
 */
public class NSAL1140Constant {
    /** Business ID */
    public static final String BUSINESS_ID = "NSAL1140";
    /** SCREEN ID */
    public static final String SCREEN_ID = BUSINESS_ID + "src00";
    /** Table A */
    public static final String TABLE_A = "A";

    /** SRCH_OPT_PK. */
    public static final String COL_NM_SRCH_OPT_PK = "SRCH_OPT_PK";
    /** SRCH_OPT_NM. */
    public static final String COL_NM_SRCH_OPT_NM = "SRCH_OPT_NM";
    /** SVC_SPLY_ABUSE_ACT */
    public static final String SVC_SPLY_ABUSE_ACT = "SVC_SPLY_ABUSE_ACT";
    /** SVC_SPLY_ABUSE_STAGE */
    public static final String SVC_SPLY_ABUSE_STAGE = "SVC_SPLY_ABUSE_STAGE";

    // START 2017/05/24 K.Kitachi [QC#18315, MOD]
    /** SVC_SPLY_ENFC_APRV */
//    public static final String SVC_SPLY_ENFC_APRV = "007";
    public static final String SVC_SPLY_ENFC_APRV = "SVC_SPLY_ENFC_APRV";
    // END 2017/05/24 K.Kitachi [QC#18315, MOD]
    /** COND_VAL_GLBL_CMPY_CD */
    public static final String COND_VAL_GLBL_CMPY_CD = "glblCmpyCd01";
    /** COND_VAL_DS_CONTR_PK */
    public static final String COND_VAL_DS_CONTR_PK = "dsContrPk01";
    /** COND_VAL_SVC_MDSE_CD */
    public static final String COND_VAL_SVC_MDSE_CD = "svcPgmMdseCd01";
    /** COND_VAL_SRCH_OPT_APL_ID */
    public static final String COND_VAL_SRCH_OPT_APL_ID = "srchOptAplId01";
    /** COND_VAL_SRCH_OPT_USR_ID */
    public static final String COND_VAL_SRCH_OPT_USR_ID = "srchOptUsrId01";

    /** THRU_DT_LIMIT */
    public static final String THRU_DT_LIMIT = "29991231";

    /** ROWNUM_B */
    public static final int ROWNUM_B = 20;

    /** FETCH_SIZE_MAX = 1000     */
    public static final int FETCH_SIZE_MAX = 1000;
    /** PAGE_SIZE */
    public static final int PAGE_SIZE = 10;
    /** DOWNLOAD_LIMIT_COUNT */
    public static final int DOWNLOAD_LIMIT_COUNT = 65000;
    /** CSV */
    public static final String CSV = ".csv";
    /** CSV file name */
    public static final String CSV_FILE_NAME = "_SUPPLY_WATCH";
    /** FMSG_COL_LENGTH */
    public static final int FMSG_COL_LENGTH = 27;

    /** DATE_FORMAT_YYYYMMDD : yyyyMMdd */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * MSG_KIND_ERROR.
     */
    public static final String MSG_KIND_ERROR = "E";

    /**
     * More than @ records have been retrieved. Please change search conditions and try again.
     */
    public static final String NSAM0006I = "NSAM0006I";
    /** 
     * Please select at least 1 checkbox. 
     * */
    public static final String NSAM0015E = "NSAM0015E";
    /**
     * Please set at least one search criteria.
     */
    public static final String NSAM0017E = "NSAM0017E";
    /**
     * Search results exceeded [@]. Only showing first @ records.
     */
    public static final String NSAM0024W = "NSAM0024W";
    /**
     * Failed to update "@".
     */
    public static final String NSAM0031E = "NSAM0031E";
    /**
     * Failed to insert "@".
     */
    public static final String NSAM0032E = "NSAM0032E";
    /** 
     * Specified "@" already exists. 
     */
    public static final String NSAM0059E = "NSAM0059E";
    /**
     * If you selected @, it is required to enter @. 
     * */
    public static final String NSAM0081E = "NSAM0081E";
    /**
     * No search results found.
     */
    public static final String NSAM0194I = "NSAM0194I";
    /**
     * [@] is not selected.
     */
    public static final String NSAM0199E = "NSAM0199E";
    /**
     * The process has been successfully completed.
     */
    public static final String NSAM0200I = "NSAM0200I";
    /** The process completed successfully . */
    public static final String NZZM0002I = "NZZM0002I";
    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
    /**
     * The process [@] has been successfully completed.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** STR_COMMA : , */
    public static final String STR_COMMA = ",";

    // START 2016/10/14 T.Tomita [QC#15196, ADD]
    /**
     * No search results found.
     */
    public static final String NSAM0013E = "NSAM0013E";
    // END 2016/10/14 T.Tomita [QC#15196, ADD]
    
    // START 2017/07/26 U.Kim [QC#18316,ADD]
    /** If the "@" field is entered, please enter the "@" field as well. */
    public static final String NSAM0066E = "NSAM0066E";
    // END 2017/07/26 U.Kim [QC#18316,ADD]
}
