package com.canon.cusa.s21.batch.NMA.NMAB266001.Constant;

/**
 *<pre>
 * NMAB266001Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/02   Fujitsu         C.Tanaka        Create          N/A
 * 2016/09/16   Fujitsu         C.Yokoi         Update          CSA-QC#8156
 *</pre>
 */
public class NMAB266001Constant {

    /** Max FETCH_SIZE */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Account Name Length for Duplicate Check */
    public static final int SUBS_LEN_ACCT_NM = 360;

    /** Address Length for Duplicate Check */
    public static final int SUBS_LEN_ADDR = 60;

    /** Application Function ID */
    public static final String APP_FUNC_ID = "NMAL6700";

    /** Return Code: Normal */
    public static final String RTNCD_NORMAL = "0000";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** NMAL2800_DEF_DS_ACCT_ITRL_FLG */
    public static final String DEF_DS_ACCT_ITRL_FLG = "NMAL2800_DEF_DS_ACCT_ITRL_FLG";

    /** NMAL2800_DEF_DS_ACCT_CLS_CD */
    public static final String DEF_DS_ACCT_CLS_CD = "NMAL2800_DEF_DS_ACCT_CLS_CD";

    // --------------------------------
    // Field
    // --------------------------------
    /** UPLD_CSV_ID */
    public static final String UPLD_CSV_ID = "UPLD_CSV_ID";

    /** UPLD_CSV_RQST_PK */
    public static final String UPLD_CSV_RQST_PK = "UPLD_CSV_RQST_PK";

    /** UPLD_CSV_RQST_CMNT_TXT */
    public static final String UPLD_CSV_RQST_CMNT_TXT = "UPLD_CSV_RQST_CMNT_TXT";

    /** UPLD_CSV_RQST_ROW_NUMl */
    public static final String UPLD_CSV_RQST_ROW_NUM = "UPLD_CSV_RQST_ROW_NUM";

    /** ORIG_CTRY_CD */
    public static final String ORIG_CTRY_CD = "ORIG_CTRY_CD";

    /** ORIG_LINE_BIZ_TP_CD */
    public static final String ORIG_LINE_BIZ_TP_CD = "ORIG_LINE_BIZ_TP_CD";

    /** ORIG_ACCT_NM */
    public static final String ORIG_ACCT_NM = "ORIG_ACCT_NM";

    /** ORIG_CTAC_TP_CD */
    public static final String ORIG_CTAC_TP_CD = "ORIG_CTAC_TP_CD";

    /** ORIG_CTAC_PSN_SALT_CD */
    public static final String ORIG_CTAC_PSN_SALT_CD = "ORIG_CTAC_PSN_SALT_CD";

    /** ORIG_CTAC_PSN_TTL_CD */
    public static final String ORIG_CTAC_PSN_TTL_CD = "ORIG_CTAC_PSN_TTL_CD";

    /** ORIG_CTAC_PNT_TP_CD */
    public static final String ORIG_CTAC_PNT_TP_CD = "ORIG_CTAC_PNT_TP_CD";

    /** DS_ACCT_NM */
    public static final String DS_ACCT_NM = "DS_ACCT_NM";

    /** ORIG_ST_CD */
    public static final String ORIG_ST_CD = "ORIG_ST_CD";

    /** upldCsvRqstPk */
    public static final String PARAM_UPLD_CSV_RQST_PK = "upldCsvRqstPk";

    /** glblCmpyCd */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** rowNum */
    public static final String PARAM_ROW_NUM = "rowNum";

    /** locPMsg */
    public static final String PARAM_LOC_PMSG = "locPMsg";

    /** firstLineAddr */
    public static final String API_FIRST_ADDR = "firstLineAddr";

    /** scdLineAddr */
    public static final String API_SCD_ADDR = "scdLineAddr";

    /** ctyAddr */
    public static final String API_CTY_ADDR = "ctyAddr";

    /** stCd */
    public static final String API_ST_CD = "stCd";

    /** postCd */
    public static final String API_POST_CD = "postCd";

    /** ctryCd */
    public static final String API_CTRY_CD = "ctryCd";

    /** cntyNm */
    public static final String API_CNTY_NM = "cntyNm";

    // --------------------------------
    // Message
    // --------------------------------
    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** Data insert failure.(ReturnCode = [@]) */
    public static final String ZZZM9012E = "ZZZM9012E";

    /** Data update failure.(ReturnCode = [@]) */
    public static final String ZZZM9013E = "ZZZM9013E";

    /** Data delete failure.(ReturnCode = [@]) */
    public static final String ZZZM9014E = "ZZZM9014E";

    /** [@] is invalid value. */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** Error Msg */
    public static final String ZYPM0181E = "ZYPM0181E";

    /** Please confirm the error details. */
    public static final String NMAM8463E = "NMAM8463E";

    /** @ is not registered Customer Master data. */
    public static final String NMAM8414E = "NMAM8414E";

    /** [@] is already registered. */
    public static final String NMAM0010E = "NMAM0010E";

    /** @ is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** There is a duplicate possible location in the S21[@]. */
    public static final String NMAM8563E = "NMAM8563E";

    /**
     * The data for account information is not equal in an uploaded
     * file.
     */
    public static final String NMAM8564E = "NMAM8564E";

    /**
     * File contains duplicated person name lines under same account
     * name.
     */
    public static final String NMAM8565E = "NMAM8565E";

    /** The account cannot be registered for some issue. */
    public static final String NMAM8566E = "NMAM8566E";

    /** Territory[@] is not Active Territory on S21 . */
    public static final String NMAM8572E = "NMAM8572E";

    /** Sales Rep is not assigned to Territory[@]. */
    public static final String NMAM8597I = "NMAM8597I";

    /** Please select [@]. */
    public static final String NMAM8461E = "NMAM8461E";

    /** Territory Rule did not register because other rule exists. */
    public static final String NMAM8559I = "NMAM8559I";

    /**
     * Territory Rule cannot be registered because Territory Rule
     * Logic Type in target rule is not "OR".
     */
    public static final String NMAM8573I = "NMAM8573I";

    /**
     * We found a different address from the one you entered.
     */
    public static final String NMZM0179W = "NMZM0179W";

    // 2016/09/16 CSA-QC#8156 Add Start
    /** @ */
    public static final String NYXM0001I = "NYXM0001I";

    /** @ */
    public static final String NYXM0002E = "NYXM0002E";
    // 2016/09/16 CSA-QC#8156 Add End

    /** Global Company Code */
    public static final String MSG_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** Upload Csv ID */
    public static final String MSG_UPLD_CSV_ID = "Upload Csv ID";

    /** Upload CSV Request PK */
    public static final String MSG_UPLD_CSV_RQST_PK = "Upload CSV Request PK";

    /** territory which is assigned Sales Rep */
    public static final String MSG_TRTY_ASG_SLS_REP = "territory which is assigned Sales Rep";

    /** territory which is assigned Resource(s) */
    public static final String MSG_TRTY_ASG_RESRC = "territory which is assigned Resource(s)";

    /** Account */
    public static final String MSG_ACCT = "Account";

    /** Territory Name */
    public static final String MSG_ORG_NM = "Territory Name";

    /** Account Name */
    public static final String MSG_DS_ACCT_NM = "Account Name";

    /** Shipping Street 1 */
    public static final String MSG_FIRST_LINE_ADDR = "Shipping Street 1";

    /** Shipping Street 2 */
    public static final String MSG_SCD_LINE_ADDR = "Shipping Street 2";

    /** Shipping Street 3 */
    public static final String MSG_THIRD_LINE_ADDR = "Shipping Street 3";

    /** Shipping Street 4 */
    public static final String MSG_FRTH_LINE_ADDR = "Shipping Street 4";

    /** Shipping City */
    public static final String MSG_CTY_ADDR = "Shipping City";

    /** Shipping State */
    public static final String MSG_ST_CD = "Shipping State";

    /** Shipping Postal Code */
    public static final String MSG_POST_CD = "Shipping Postal Code";

    /** Shipping County */
    public static final String MSG_CNTY_NM = "Shipping County";

    /** Shipping Country */
    public static final String MSG_CTRY_CD = "Shipping Country";

    /** Phone */
    public static final String MSG_TEL_NUM = "Phone";

    /** Fax */
    public static final String MSG_FAX_NUM = "Fax";

    /** Website */
    public static final String MSG_DS_ACCT_URL = "Website";

    /** Line of Business */
    public static final String MSG_DS_CUST_SIC_DESC_TXT = "Line of Business";

    /** Annual US Sales */
    public static final String MSG_DS_LOC_REV_AMT = "Annual US Sales";

    /** Employees Total */
    public static final String MSG_DS_LOC_EMP_NUM = "Employees Total";

    /** SIC Code */
    public static final String MSG_DS_CUST_SIC_CD = "SIC Code";

    /** Company SIC Code */
    public static final String MSG_DS_CUST_CMPY_SIC_CD = "Compay SIC Code";

    /** DUNS Number */
    public static final String MSG_DUNS_NUM = "DUNS Number";

    /** Ultimate DUNS Number */
    public static final String MSG_DS_ULT_DUNS_NUM = "Ultimate DUNS Number";

    /** Parent DUNS Number */
    public static final String MSG_DS_PRNT_DUNS_NUM = "Parent DUNS Number";

    /** D&B Name */
    public static final String MSG_DS_ACCT_DUNS_NM = "D&B Name";

    /** GLN (Global Location Number) */
    public static final String MSG_GLN_NUM = "GLN (Global Location Number)";

    /** Account Source */
    public static final String MSG_ACCT_SRC_TXT = "Account Source";

    /** Source Business Unit */
    public static final String MSG_LINE_BIZ_TP_CD = "Source Business Unit";

    /** Salutation */
    public static final String MSG_DS_CTAC_PSN_SALT_DESC_TXT = "Salutation";

    /** First Name */
    public static final String MSG_CTAC_PSN_FIRST_NM = "First Name";

    /** Last Name */
    public static final String MSG_CTAC_PSN_LAST_NM = "Last Name";

    /** Title */
    public static final String MSG_DS_CTAC_PSN_TTL_DESC_TXT = "Title";

    /** Relationship to CSA */
    public static final String MSG_CTAC_TP_DESC_TXT = "Relationship to CSA";

    /** Preferred Contact Type */
    public static final String MSG_DS_CTAC_PNT_TP_DESC_TXT = "Prefered Contact Type";

    /** Phone - Work */
    public static final String MSG_PROS_CTAC_PHO_NUM = "Phone - Work";

    /** FAX - Work */
    public static final String MSG_PROS_CTAC_FAX_NUM = "FAX - Work";

    /** Email Address - Work */
    public static final String MSG_PROS_CTAC_EML_ADDR = "Email Address - Work";

    /** Cell Phone - Work */
    public static final String MSG_PROS_CTAC_CELL_PHO_NUM = "Cell Phone - Work";

    // 2016/09/16 CSA-QC#8156 Add Start
    /** Info Message(@ record(s) successfully created.) */
    public static final String MSG_INFO = " record(s) successfully created.";

    /** Error Message(@ record(s) skipped/errored.) */
    public static final String MSG_ERR = " record(s) skipped/errored.";
    // 2016/09/16 CSA-QC#8156 Add End
}
