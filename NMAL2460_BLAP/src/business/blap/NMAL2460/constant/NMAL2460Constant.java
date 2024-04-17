/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL2460.constant;

/**
 *<pre>
 * Account Owner Lookup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/27   Hitachi         O.Okuma         Create          N/A
 * 2016/09/02   SRAA            Y.Chen          Update          QC#12193
 * 2016/09/13   Fujitsu         C.Yokoi         Update          QC#10091
 * 2016/09/21   Fujitsu         Mz.Takahashi    Update          QC#11068
 *</pre>
 */
public final class NMAL2460Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NMAL2460";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /** TIME_STAMP_FORMAT: yyyyMMddHHmmssSSS */
    public static final java.lang.String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /** You can not [@] this record Because of [@] already exists.*/
    public static final String NMAM0182E = "NMAM0182E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * Process ended normally.
     */
    public static final String ZZM8100I = "ZZM8100I";

    /**
     * Please set at least one search criteria
     */
    public static final String NMAM0192E = "NMAM0192E";

    /**
     * A past date cannot be entered into the "Date Effective From".
     */
    public static final String NMAM0185E = "NMAM0185E";

    /**
     * The entered [@] does not exist in master.
     */
    public static final String NMAM8121E = "NMAM8121E";

    /**
     * Could not get the "@".
     */
    public static final String NMAM8433E = "NMAM8433E";

    /**
     * Territory is not found. Please enter active territory.
     */
    public static final String NMAM8542E = "NMAM8542E";

    /** Selected territory can not be set. Please select active territory which active resource is assigned. */
    public static final String NMAM8551E = "NMAM8551E";

    /**
     * Please select territory which is assigned Sales Rep.
     */
    public static final String NMAM8545E = "NMAM8545E";

    /**
     * Please select territory which is assigned Resource(s).
     */
    public static final String NMAM8546E = "NMAM8546E";

    /** No change has been made. */
    public static final String NMAM8333I = "NMAM8333I";

    /**
     * The entered [@] is not active
     */
    public static final String NMAM8460E = "NMAM8460E";

    /**
     * Please select [@].
     */
    public static final String NMAM8461E = "NMAM8461E";

    /**
     * This data has been updated by another user.
     */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    // QC#12193
    /** Please specify an organization which tier is greater than or equal to [@]. */
    public static final String NMAM8646E = "NMAM8646E";

    /** Message Kind : Error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // 2016/09/13 CSA-QC#10091 Add Start
    /** Message Kind : Warning */
    public static final String MESSAGE_KIND_WARNING = "W";

    /** Error Code : Error(1) */
    public static final int ERROR_CODE_ERROR = 1;

    /** Error Code : Warning(2) */
    public static final int ERROR_CODE_WARNING = 2;
    // 2016/09/13 CSA-QC#10091 Add End

    // --------------------------------
    // column
    // --------------------------------
    /** Column : acctTrtyOrgCd_ */
    public static final String CLMN_ACCT_TRTY_ORG_CD = "acctTrtyOrgCd_";

    /** Column : "Suffix" */
    public static final String CLMN_TRTY_GRP_TP_DESC_TXT = "trtyGrpTpDescTxt_";

    /** Column : "orgNm_" */
    public static final String CLMN_ORG_NM = "orgNm_";

    /** Column : "psnNum_" */
    public static final String CLMN_PSN_NUM = "psnNum_";

    /** Column : "xxPsnFirstMidLastNm_" */
    public static final String CLMN_XX_PSN_FIRST_MID_LAST_NM = "xxPsnFirstMidLastNm_";

    /** Column : "acctTrtyPsnCd_" */
    public static final String CLMN_ACCT_TRTY_PSN_CD = "acctTrtyPsnCd_";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 5000;

    /** CSV File Name */
    public static final String CSV_FILE_NAME = "Account Teritory";

    /** Limit Download RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    // START 2017/10/11 J.Kim [QC#21299,MOD]
    // /** CSV Header Number : 50 */
    // public static final int CSV_HEADER_NUM = 50;
    /** CSV Header Number : 140 */
    public static final int CSV_HEADER_NUM = 140;
    // END 2017/10/11 J.Kim [QC#21299,MOD]

    /** Delete */
    public static final String DELETE = "DEL";
    
    // QC#12193
    /** Minimum Search Organization Tier  */
    public static final int MIN_SRCH_ORG_TIER = 4;

}
