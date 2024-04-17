/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2840.constant;

/**
 *<pre>
 * NMAL2840Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/25   Fujitsu         R.Nakamura      Create          N/A
 * 2016/06/16   Fujitsu         R.Nakamura      Update          QC#10224
 * 2016/06/22   Fujitsu         R.Nakamura      Update          QC#10340
 * 2016/06/27   Fujitsu         R.Nakamura      Update          QC#10905
 * 2016/07/01   Fujitsu         R.Nakamura      Update          QC#11316
 * 2016/10/06   Fujitsu         R.Nakamura      Update          QC#14861
 * 2016/11/08   Fujitsu         N.Sugiura       Update          QC#14832
 * 2017/12/15   Fujitsu         Hd.Sugawara     Update          QC#20905
 *</pre>
 */
public class NMAL2840Constant {

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL2840Scrn00";

    // --------------------------------
    // Message ID
    // --------------------------------
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

    /** Data insert failure.@ */
    public static final String NMAM0282E = "NMAM0282E";

    /** Please set up Extruct Filters correctly. */
    public static final String NMAM8549E = "NMAM8549E";

    /**
     * The "Include Customers" flag is no effect on anything extract
     * filters.
     */
    public static final String NMAM8550I = "NMAM8550I";

    // Add Start 2016/11/08 QC#14832
    /** The Extract Filters has been already registered. Press button again to continue. */
    public static final String NMAM8657W = "NMAM8657W";
    // Add End 2016/11/08 QC#14832

    /** CSV File Name Upload */
    public static final String CSV_FILE_NAME_UPLOAD = "UploadedMeterReadDownload";

    /** CSV Upload header upload */
    public static final String[] CSV_UPLOAD_HEADER = new String[] {//
    //
            "Serial#" //
            , "Model Name" //
            , "MDSE Code" //
            , "Meter ID" //
            , "Meter Label" //
    };

    /** CSV Upload header upload with error */
    public static final String[] CSV_UPLOAD_HEADER_WITH_ERROR = new String[] {//
    //
            "Error Count" //
            , "Error Description" //
            , "Serial#" //
            , "Model Name" //
            , "MDSE Code" //
            , "Meter ID" //
            , "Meter Label" //
    };

    // Del Start 2016/10/06 QC#14861
//    /** MAX Num(Imported DNB Date List) */
//    public static final int MAX_NUM_IDDL = 21;
//
//    /** MAX Num(Audit Information Data) */
//    public static final int MAX_NUM_AID = 401;
    // Del End 2016/10/06 QC#14861

    // Del Start 2016/06/22 QC#10340
    // /** MAX Dounload Num */
    // public static final int MAX_DOWNLOAD_CNT = 65000;
    // Del End 2016/06/22 QC#10340

    // Add Start 2016/06/27 QC#10905
    /** FETCH_SIZE : 1000 */
    public static final int FETCH_SIZE = 1000;

    // Add End 2016/06/27 QC#10905

    /** Check Information(99) */
    public static final int CHECK_INFORMATION = 1;

    /** Check Error(99) */
    public static final int CHECK_ERROR = 99;

    // Del Start 2016/10/06 QC#14861
//    /** Duns Proc Type Code(10) */
//    public static final String DUNS_PROC_STS_CD_01 = "01";
//
//    /** Duns Proc Type Code(10) */
//    public static final String DUNS_PROC_STS_CD_10 = "10";
//
//    /** Duns Proc Type Code(10) */
//    public static final String DUNS_PROC_STS_CD_30 = "30";
//
//    /** Duns Proc Type Code(10) */
//    public static final String DUNS_PROC_STS_CD_90 = "90";
//
//    /** Duns Proc Type Code(10) */
//    public static final String DUNS_PROC_TP_CD_10 = "10";
//
//    /** Duns Proc Type Code(20) */
//    public static final String DUNS_PROC_TP_CD_20 = "20";
//
//    /** Duns Proc Type Code(30) */
//    public static final String DUNS_PROC_TP_CD_30 = "30";
//
//    /** Duns Proc Type Code(40) */
//    public static final String DUNS_PROC_TP_CD_40 = "40";
    // Del End 2016/10/06 QC#14861

    /** Duns Criteria Default Value Flag(11) */
    public static final String DUNS_CRIT_DEF_VAL_FLG_11 = "11";

    /** Duns Criteria Default Value Flag(12) */
    public static final String DUNS_CRIT_DEF_VAL_FLG_12 = "12";

    /** Duns Criteria Default Value Flag(21) */
    public static final String DUNS_CRIT_DEF_VAL_FLG_21 = "21";

    /** Duns Criteria Default Value Flag(31) */
    public static final String DUNS_CRIT_DEF_VAL_FLG_31 = "31";

    /** Duns Criteria Default Value Flag(32) */
    public static final String DUNS_CRIT_DEF_VAL_FLG_32 = "32";

    /** Duns Criteria Default Value Flag(33) */
    public static final String DUNS_CRIT_DEF_VAL_FLG_33 = "33";

    /** Duns Criteria Default Value Flag(34) */
    public static final String DUNS_CRIT_DEF_VAL_FLG_34 = "34";

    // Add Start 2016/06/16 QC#10224
    /** Duns Criteria Default Value Flag(51) */
    public static final String DUNS_CRIT_DEF_VAL_FLG_50 = "50";
    // Add Start 2016/06/16 QC#10224

    /** Duns Criteria Default Value Flag(51) */
    public static final String DUNS_CRIT_DEF_VAL_FLG_51 = "51";

    /** Duns Criteria Default Value Flag(52) */
    public static final String DUNS_CRIT_DEF_VAL_FLG_52 = "52";

    /** Duns Criteria Default Value Flag(53) */
    public static final String DUNS_CRIT_DEF_VAL_FLG_53 = "53";

    /** Duns Criteria Default Value Flag(54) */
    public static final String DUNS_CRIT_DEF_VAL_FLG_54 = "54";

    /** Duns Criteria Default Value Flag(55) */
    public static final String DUNS_CRIT_DEF_VAL_FLG_55 = "55";

    /** Duns Criteria Default Value Flag(56) */
    public static final String DUNS_CRIT_DEF_VAL_FLG_56 = "56";

    /** Duns Criteria Default Value Flag(57) */
    public static final String DUNS_CRIT_DEF_VAL_FLG_57 = "57";

    /** DS_ACCT_NM_01 */
    public static final String DS_ACCT_NM_01 = "%CPC";

    /** DS_ACCT_NM_02 */
    public static final String DS_ACCT_NM_02 = "CANON%";

    /** DS_ACCT_NM_03 */
    public static final String DS_ACCT_NM_03 = "% FM";

    /** DS_ACCT_NM_04 */
    public static final String DS_ACCT_NM_04 = "%DUPLICATE%";

    /** DS_ACCT_NM_05 */
    public static final String DS_ACCT_NM_05 = "% OM ONLY";

    /** DS_ACCT_NM_06 */
    public static final String DS_ACCT_NM_06 = "% SERVICE ONLY";

    /** RGTN_STS_CD */
    public static final String DS_ACCT_NM_07 = "CFS %";

    /** FIRST_LINE_ADDR_01 */
    public static final String FIRST_LINE_ADDR_01 = "158 GAITH%";

    /** FIRST_LINE_ADDR_02 */
    public static final String FIRST_LINE_ADDR_02 = "%DUPLICATE%";

    /** FIRST_LINE_ADDR_03 */
    public static final String FIRST_LINE_ADDR_03 = "%DzUPLICATE%";

    /** DS_ACCT_DLR_CD_1000 */
    public static final String DS_ACCT_DLR_CD_1000 = "1000";

    /** DS_ACCT_DLR_CD_1010 */
    public static final String DS_ACCT_DLR_CD_1010 = "1010";

    /** DS_ACCT_DLR_CD_1020 */
    public static final String DS_ACCT_DLR_CD_1020 = "1020";

    /** DS_ACCT_DLR_CD_2000 */
    public static final String DS_ACCT_DLR_CD_2000 = "2000";

    /** DS_ACCT_DLR_CD_3000 */
    public static final String DS_ACCT_DLR_CD_3000 = "3000";

    /** Update Count */
    public static final String UPDATE_COUNT_CONFIGRATION = "NMAL2840_UPLOAD_MAX_NUM_ROWS";

    /** Date Pattan */
    public static final String DATE_PATTAN = "yyyyMMddHHmmssSSS";

    /** Date Pattan */
    public static final int DATE_LENGTH = 8;

    // Mod Start 2016/07/01 QC#11316
    /** Date Pattan */
    public static final int DATE_TIME_LENGTH = 14;

    /** Max CRIT Code */
    public static final String MAX_CRIT_CD = "99";

    /** Check CRIT Code */
    public static final String CHECK_CRIT_CD = "50";

    /** Is Null */
    public static final String VALUE_IS_NULL = "Is Null";
    // Mod End 2016/07/01 QC#11316
    // Add Start 2016/11/08 QC#14832
    /** Comma */
    public static final String COMMA = ",";
    // Add End 2016/11/08 QC#14832

    // Add Start 2017/12/15 QC#20905
    /**
     * XX_DUNS_PROC_CMNT_TXT Max Length
     */
    public static final int XX_DUNS_PROC_CMNT_TXT_MAX_LEN = 1200;
    // Add End 2017/12/15 QC#20905
}
