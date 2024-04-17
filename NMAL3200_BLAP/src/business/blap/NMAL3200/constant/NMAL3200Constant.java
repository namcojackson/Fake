/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL3200.constant;

/**
 * <pre>
 * Business ID : NMAL3200 Marketing Data Analysis
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/23/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NMAL3200Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NMAL3200";

    /**
     * TAB_UPLOAD
     */
    public static final String TAB_UPLOAD = "Upload";

    /**
     * TAB_DOWNLOAD
     */
    public static final String TAB_DOWNLOAD = "Download";

    /**
     * FETCH_SIZE
     */
    public static final int FETCH_SIZE = 1000;

    /**
     * MKTG_FLD_MAP
     */
    public static final String MKTG_FLD_MAP = "MKTG_FLD_MAP";

    /**
     * MKTG_MAP_WRK
     */
    public static final String MKTG_MAP_WRK = "MKTG_MAP_WRK";

    /**
     * MKTG_MAP_RQST
     */
    public static final String MKTG_MAP_RQST = "MKTG_MAP_RQST";

    /**
     * Date Format TIMESTAMP
     */
    public static final String FORMAT_TIMESTAMP = "yyyyMMddHHmmssSSS";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : INIT
     */
    public static final String EVENT_NM_NMAL3200_INIT = "NMAL3200_INIT";

    /**
     * Event Name : Download_NewProsFile
     */
    public static final String EVENT_NM_NMAL3200_CMN_DOWNLOAD_NEW_PROS_FILE = "NMAL3200Scrn00_Download_NewProsFile";

    /**
     * Event Name : Download_ReviewFile
     */
    public static final String EVENT_NM_NMAL3200_CMN_DOWNLOAD_REVIEW_FILE = "NMAL3200Scrn00_Download_ReviewFile";

    /**
     * Event Name : Download_SuccessFile
     */
    public static final String EVENT_NM_NMAL3200_CMN_DOWNLOAD_SUCCESS_FILE = "NMAL3200Scrn00_Download_SuccessFile";

    /**
     * Event Name : Download_UpldData
     */
    public static final String EVENT_NM_NMAL3200_CMN_DOWNLOAD_UPLD_DATA = "NMAL3200Scrn00_Download_UpldData";

    /**
     * Event Name : CMN_Return
     */
    public static final String EVENT_NM_NMAL3200_CMN_RETURN = "NMAL3200Scrn00_CMN_Return";

    /**
     * Event Name : CMN_Submit
     */
    public static final String EVENT_NM_NMAL3200_CMN_SUBMIT = "NMAL3200Scrn00_CMN_Submit";

    /**
     * Event Name : CMN_Clear
     */
    public static final String EVENT_NM_NMAL3200_CMN_CLEAR = "NMAL3200Scrn00_CMN_Clear";

    /**
     * Event Name : PageJump
     */
    public static final String EVENT_NM_NMAL3200_PAGE_JUMP = "NMAL3200Scrn00_PageJump";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NMAL3200_PAGE_NEXT = "NMAL3200Scrn00_PageNext";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NMAL3200_PAGE_PREV = "NMAL3200Scrn00_PagePrev";

    /**
     * Event Name : Search_MktFldMap
     */
    public static final String EVENT_NM_NMAL3200_SEARCH_MKT_FLD_MAP = "NMAL3200Scrn00_Search_MktFldMap";

    /**
     * Event Name : Search_MktMapReq
     */
    public static final String EVENT_NM_NMAL3200_SEARCH_MKT_MAP_REQ = "NMAL3200Scrn00_Search_MktMapReq";

    /**
     * Event Name : TBLColumnSort
     */
    public static final String EVENT_NM_NMAL3200_TBL_COL_SORT = "NMAL3200Scrn00_TBLColumnSort";

    /**
     * Event Name : Upload
     */
    public static final String EVENT_NM_NMAL3200_UPLOAD = "NMAL3200Scrn00_Upload";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NMAM0038I: No search result is found.
     */
    public static final String NMAM0038I = "NMAM0038I";

    /**
     * NMAM8181W: Search results exceeded [@]. Only showing first @
     * records.
     */
    public static final String NMAM8181W = "NMAM8181W";

    /**
     * NMAM8175E:This data has been updated by another user. [
     * TableName = @ , key = @, value = @ ]
     */
    public static final String NMAM8175E = "NMAM8175E";

    /**
     * NMAM0282E:Data insert failure.@
     */
    public static final String NMAM0282E = "NMAM0282E";

    /**
     * NMAM0836E:[@] field is mandatory.
     */
    public static final String NMAM0836E = "NMAM0836E";

    /**
     * NMAM8509E:The data you entered has already been registered.
     */
    public static final String NMAM8509E = "NMAM8509E";

    /**
     * NMAM8121E:The entered [@] does not exists in master.
     */
    public static final String NMAM8121E = "NMAM8121E";

    /**
     * NMAM8615E:You can't update the record because this Mapping Name
     * is already used by Upload.
     */
    public static final String NMAM8615E = "NMAM8615E";

    /** The format of [@] is incorrect. */
    public static final String NMAM0052E = "NMAM0052E";

    /** [@] not included in Upload file header. */
    public static final String NMAM8618E = "NMAM8618E";

    /**
     * It failed to read the file. File name: @
     */
    public static final String NMAM8329E = "NMAM8329E";

    /**
     * This record exceeded the upper limit [@].This and bellow
     * records were not able to be uploaded.
     */
    public static final String ZYEM0013E = "ZYEM0013E";

    /**
     * The Upload File is empty or only has a header line, therefore
     * it could not be processed. Please confirm the content of the
     * Upload file.
     */
    public static final String ZYEM0004E = "ZYEM0004E";

    /**
     * ZZZM9003I:The process [@] has been successfully completed.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    // =================================================
    // DB Query Parameters
    // =================================================
    /**
     * DB_PARAM_GLBL_CMPY_CD
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB_PARAM_ACTV_FLG
     */
    public static final String DB_PARAM_ACTV_FLG = "actvFlg";

    /**
     * DB_PARAM_MKTG_FLD_MAP_NM
     */
    public static final String DB_PARAM_MKTG_FLD_MAP_NM = "mktgFldMapNm";

    /**
     * DB_PARAM_MKTG_FLD_MAP_PK
     */
    public static final String DB_PARAM_MKTG_FLD_MAP_PK = "mktgFldMapPk";

    /**
     * DB_PARAM_MKTG_MAP_RQST_PK
     */
    public static final String DB_PARAM_MKTG_MAP_RQST_PK = "mktgMapRqstPk";

    /**
     * DB_PARAM_UPLD_ERR_FLG
     */
    public static final String DB_PARAM_UPLD_ERR_FLG = "upldErrFlg";

    /**
     * DB_PARAM_MKTG_MAP_RQST_PROC_FLG
     */
    public static final String DB_PARAM_MKTG_MAP_RQST_PROC_FLG = "mktgMapRqstProcFlg";

    /**
     * DB_PARAM_CONTR_ASSN_TRGT_TP_CD
     */
    public static final String DB_PARAM_CONTR_ASSN_TRGT_TP_CD = "contrAssnTrgtTpCd";

    /**
     * DB_PARAM_EXACT_MATCH_FLG
     */
    public static final String DB_PARAM_EXACT_MATCH_FLG = "exactMatchFlg";

    /**
     * DB_PARAM_DUNS_MATCH_FLG
     */
    public static final String DB_PARAM_DUNS_MATCH_FLG = "dunsMatchFlg";

    /**
     * DB_PARAM_GLN_MATCH_FLG
     */
    public static final String DB_PARAM_GLN_MATCH_FLG = "glnMatchFlg";

    /**
     * DB_PARAM_PRTL_MATCH_FLG
     */
    public static final String DB_PARAM_PRTL_MATCH_FLG = "prtlMatchFlg";

    /**
     * DB_PARAM_SALES_DATE
     */
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    /**
     * DB_PARAM_CNTY_NM
     */
    public static final String DB_PARAM_CNTY_NM = "cntyNm";

    /**
     * DB_PARAM_ST_CD
     */
    public static final String DB_PARAM_ST_CD = "stCd";

    /**
     * DB_PARAM_CTRY_CD
     */
    public static final String DB_PARAM_CTRY_CD = "ctryCd";

    /**
     * DB_PRAM_ROWNUM
     */
    public static final String DB_PARAM_ROWNUM = "rowNum";

    // =================================================
    // DB Result Columns
    // =================================================

    /**
     * CONTR_ASSN_TRGT_TP_CD
     */
    public static final String CONTR_ASSN_TRGT_TP_CD = "CONTR_ASSN_TRGT_TP_CD";

    /**
     * CONTR_ASSN_TRGT_TP_DESC_TXT
     */
    public static final String CONTR_ASSN_TRGT_TP_DESC_TXT = "CONTR_ASSN_TRGT_TP_DESC_TXT";

    /**
     * XX_FULL_NM
     */
    public static final String XX_FULL_NM = "XX_FULL_NM";

    /**
     * MKTG_FLD_MAP_NM
     */
    public static final String MKTG_FLD_MAP_NM = "MKTG_FLD_MAP_NM";

    /**
     * UPLD_ERR_FLG
     */
    public static final String UPLD_ERR_FLG = "UPLD_ERR_FLG";

    /**
     * UPLD_FILE_NM
     */
    public static final String UPLD_FILE_NM = "UPLD_FILE_NM";

    /**
     * MATCH_CRIT_TP_TXT
     */
    public static final String MATCH_CRIT_TP_TXT = "MATCH_CRIT_TP_TXT";

    /**
     * EXACT_MATCH_LOC_NUM
     */
    public static final String EXACT_MATCH_LOC_NUM = "EXACT_MATCH_LOC_NUM";

    /**
     * EXACT_MATCH_SF_ACCT_ID
     */
    public static final String EXACT_MATCH_SF_ACCT_ID = "EXACT_MATCH_SF_ACCT_ID";

    /**
     * PRTL_MATCH_LOC_NUM
     */
    public static final String PRTL_MATCH_LOC_NUM = "PRTL_MATCH_LOC_NUM";

    /**
     * PRTL_MATCH_SF_ACCT_ID
     */
    public static final String PRTL_MATCH_SF_ACCT_ID = "PRTL_MATCH_SF_ACCT_ID";

    /**
     * DUNS_MATCH_LOC_NUM
     */
    public static final String DUNS_MATCH_LOC_NUM = "DUNS_MATCH_LOC_NUM";

    /**
     * DUNS_MATCH_SF_ACCT_ID
     */
    public static final String DUNS_MATCH_SF_ACCT_ID = "DUNS_MATCH_SF_ACCT_ID";

    /**
     * GLN_MATCH_LOC_NUM
     */
    public static final String GLN_MATCH_LOC_NUM = "GLN_MATCH_LOC_NUM";

    /**
     * GLN_MATCH_SF_ACCT_ID
     */
    public static final String GLN_MATCH_SF_ACCT_ID = "GLN_MATCH_SF_ACCT_ID";

    /**
     * CANDI_TRTY_NM
     */
    public static final String CANDI_TRTY_NM = "CANDI_TRTY_NM";

    /**
     * MKTG_FLD_MAP_PK
     */
    public static final String MKTG_FLD_MAP_PK = "MKTG_FLD_MAP_PK";

    /**
     * DS_ACCT_NM_COL_DFN_NM
     */
    public static final String DS_ACCT_NM_COL_DFN_NM = "DS_ACCT_NM_COL_DFN_NM";

    /**
     * FIRST_LINE_ADDR_COL_DFN_NM
     */
    public static final String FIRST_LINE_ADDR_COL_DFN_NM = "FIRST_LINE_ADDR_COL_DFN_NM";

    /**
     * SCD_LINE_ADDR_COL_DFN_NM
     */
    public static final String SCD_LINE_ADDR_COL_DFN_NM = "SCD_LINE_ADDR_COL_DFN_NM";

    /**
     * THIRD_LINE_ADDR_COL_DFN_NM
     */
    public static final String THIRD_LINE_ADDR_COL_DFN_NM = "THIRD_LINE_ADDR_COL_DFN_NM";

    /**
     * FRTH_LINE_ADDR_COL_DFN_NM
     */
    public static final String FRTH_LINE_ADDR_COL_DFN_NM = "FRTH_LINE_ADDR_COL_DFN_NM";

    /**
     * CTY_ADDR_COL_DFN_NM
     */
    public static final String CTY_ADDR_COL_DFN_NM = "CTY_ADDR_COL_DFN_NM";

    /**
     * CNTY_PK_COL_DFN_NM
     */
    public static final String CNTY_PK_COL_DFN_NM = "CNTY_PK_COL_DFN_NM";

    /**
     * ST_CD_COL_DFN_NM
     */
    public static final String ST_CD_COL_DFN_NM = "ST_CD_COL_DFN_NM";

    /**
     * POST_CD_COL_DFN_NM
     */
    public static final String POST_CD_COL_DFN_NM = "POST_CD_COL_DFN_NM";

    /**
     * CTRY_CD_COL_DFN_NM
     */
    public static final String CTRY_CD_COL_DFN_NM = "CTRY_CD_COL_DFN_NM";

    /**
     * DUNS_NUM_COL_DFN_NM
     */
    public static final String DUNS_NUM_COL_DFN_NM = "DUNS_NUM_COL_DFN_NM";

    /**
     * GLN_COL_DFN_NM
     */
    public static final String GLN_COL_DFN_NM = "GLN_COL_DFN_NM";

    /**
     * HIN_NUM_COL_DFN_NM
     */
    public static final String HIN_COL_DFN_NM = "HIN_COL_DFN_NM";

    /**
     * ATTRB_TXT_COL_DFN_NM_01
     */
    public static final String ATTRB_TXT_COL_DFN_NM_01 = "ATTRB_TXT_COL_DFN_NM_01";

    /**
     * ATTRB_TXT_COL_DFN_NM_02
     */
    public static final String ATTRB_TXT_COL_DFN_NM_02 = "ATTRB_TXT_COL_DFN_NM_02";

    /**
     * ATTRB_TXT_COL_DFN_NM_03
     */
    public static final String ATTRB_TXT_COL_DFN_NM_03 = "ATTRB_TXT_COL_DFN_NM_03";

    /**
     * ATTRB_TXT_COL_DFN_NM_04
     */
    public static final String ATTRB_TXT_COL_DFN_NM_04 = "ATTRB_TXT_COL_DFN_NM_04";

    /**
     * ATTRB_TXT_COL_DFN_NM_05
     */
    public static final String ATTRB_TXT_COL_DFN_NM_05 = "ATTRB_TXT_COL_DFN_NM_05";

    /**
     * ATTRB_TXT_COL_DFN_NM_06
     */
    public static final String ATTRB_TXT_COL_DFN_NM_06 = "ATTRB_TXT_COL_DFN_NM_06";

    /**
     * ATTRB_TXT_COL_DFN_NM_07
     */
    public static final String ATTRB_TXT_COL_DFN_NM_07 = "ATTRB_TXT_COL_DFN_NM_07";

    /**
     * ATTRB_TXT_COL_DFN_NM_08
     */
    public static final String ATTRB_TXT_COL_DFN_NM_08 = "ATTRB_TXT_COL_DFN_NM_08";

    /**
     * ATTRB_TXT_COL_DFN_NM_09
     */
    public static final String ATTRB_TXT_COL_DFN_NM_09 = "ATTRB_TXT_COL_DFN_NM_09";

    /**
     * ATTRB_TXT_COL_DFN_NM_10
     */
    public static final String ATTRB_TXT_COL_DFN_NM_10 = "ATTRB_TXT_COL_DFN_NM_10";

    /**
     * ATTRB_TXT_COL_DFN_NM_11
     */
    public static final String ATTRB_TXT_COL_DFN_NM_11 = "ATTRB_TXT_COL_DFN_NM_11";

    /**
     * ATTRB_TXT_COL_DFN_NM_12
     */
    public static final String ATTRB_TXT_COL_DFN_NM_12 = "ATTRB_TXT_COL_DFN_NM_12";

    /**
     * ATTRB_TXT_COL_DFN_NM_13
     */
    public static final String ATTRB_TXT_COL_DFN_NM_13 = "ATTRB_TXT_COL_DFN_NM_13";

    /**
     * ATTRB_TXT_COL_DFN_NM_14
     */
    public static final String ATTRB_TXT_COL_DFN_NM_14 = "ATTRB_TXT_COL_DFN_NM_14";

    /**
     * ATTRB_TXT_COL_DFN_NM_15
     */
    public static final String ATTRB_TXT_COL_DFN_NM_15 = "ATTRB_TXT_COL_DFN_NM_15";

    /**
     * ATTRB_TXT_COL_DFN_NM_16
     */
    public static final String ATTRB_TXT_COL_DFN_NM_16 = "ATTRB_TXT_COL_DFN_NM_16";

    /**
     * ATTRB_TXT_COL_DFN_NM_17
     */
    public static final String ATTRB_TXT_COL_DFN_NM_17 = "ATTRB_TXT_COL_DFN_NM_17";

    /**
     * ATTRB_TXT_COL_DFN_NM_18
     */
    public static final String ATTRB_TXT_COL_DFN_NM_18 = "ATTRB_TXT_COL_DFN_NM_18";

    /**
     * ATTRB_TXT_COL_DFN_NM_19
     */
    public static final String ATTRB_TXT_COL_DFN_NM_19 = "ATTRB_TXT_COL_DFN_NM_19";

    /**
     * ATTRB_TXT_COL_DFN_NM_20
     */
    public static final String ATTRB_TXT_COL_DFN_NM_20 = "ATTRB_TXT_COL_DFN_NM_20";

    /**
     * MKTG_MAP_RQST_PK
     */
    public static final String MKTG_MAP_RQST_PK = "MKTG_MAP_RQST_PK";

    /**
     * MKTG_MAP_RQST_PROC_FLG
     */
    public static final String MKTG_MAP_RQST_PROC_FLG = "MKTG_MAP_RQST_PROC_FLG";

    /**
     * CRAT_USR_ID
     */
    public static final String CRAT_USR_ID = "CRAT_USR_ID";

    /**
     * CRAT_TS
     */
    public static final String CRAT_TS = "CRAT_TS";

    /**
     * EXACT_MATCH_FLG
     */
    public static final String EXACT_MATCH_FLG = "EXACT_MATCH_FLG";

    /**
     * PRTL_MATCH_FLG
     */
    public static final String PRTL_MATCH_FLG = "PRTL_MATCH_FLG";

    /**
     * DUNS_MATCH_FLG
     */
    public static final String DUNS_MATCH_FLG = "DUNS_MATCH_FLG";

    /**
     * GLN_MATCH_FLG
     */
    public static final String GLN_MATCH_FLG = "GLN_MATCH_FLG";

    /** MKTG_MAP_WRK_PK */
    public static final String MKTG_MAP_WRK_PK = "MKTG_MAP_WRK_PK";

    /** GLBL_CMPY_CD */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DS_ACCT_NM */
    public static final String DS_ACCT_NM = "DS_ACCT_NM";

    /** FIRST_LINE_ADDR */
    public static final String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /** SCD_LINE_ADDR */
    public static final String SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /** THIRD_LINE_ADDR */
    public static final String THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /** FRTH_LINE_ADDR */
    public static final String FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /** CTY_ADDR */
    public static final String CTY_ADDR = "CTY_ADDR";

    /** CNTY_PK */
    public static final String CNTY_PK = "CNTY_PK";

    /** ST_CD */
    public static final String ST_CD = "ST_CD";

    /** POST_CD */
    public static final String POST_CD = "POST_CD";

    /** CTRY_CD */
    public static final String CTRY_CD = "CTRY_CD";

    /** DUNS_NUM */
    public static final String DUNS_NUM = "DUNS_NUM";

    /** GLN_NUM */
    public static final String GLN_NUM = "GLN_NUM";

    /** HIN_NUM */
    public static final String HIN_NUM = "HIN_NUM";

    /** MKTG_MAP_ATTRB_TXT_01 */
    public static final String MKTG_MAP_ATTRB_TXT_01 = "MKTG_MAP_ATTRB_TXT_01";

    /** MKTG_MAP_ATTRB_TXT_02 */
    public static final String MKTG_MAP_ATTRB_TXT_02 = "MKTG_MAP_ATTRB_TXT_02";

    /** MKTG_MAP_ATTRB_TXT_03 */
    public static final String MKTG_MAP_ATTRB_TXT_03 = "MKTG_MAP_ATTRB_TXT_03";

    /** MKTG_MAP_ATTRB_TXT_04 */
    public static final String MKTG_MAP_ATTRB_TXT_04 = "MKTG_MAP_ATTRB_TXT_04";

    /** MKTG_MAP_ATTRB_TXT_05 */
    public static final String MKTG_MAP_ATTRB_TXT_05 = "MKTG_MAP_ATTRB_TXT_05";

    /** MKTG_MAP_ATTRB_TXT_06 */
    public static final String MKTG_MAP_ATTRB_TXT_06 = "MKTG_MAP_ATTRB_TXT_06";

    /** MKTG_MAP_ATTRB_TXT_07 */
    public static final String MKTG_MAP_ATTRB_TXT_07 = "MKTG_MAP_ATTRB_TXT_07";

    /** MKTG_MAP_ATTRB_TXT_08 */
    public static final String MKTG_MAP_ATTRB_TXT_08 = "MKTG_MAP_ATTRB_TXT_08";

    /** MKTG_MAP_ATTRB_TXT_09 */
    public static final String MKTG_MAP_ATTRB_TXT_09 = "MKTG_MAP_ATTRB_TXT_09";

    /** MKTG_MAP_ATTRB_TXT_10 */
    public static final String MKTG_MAP_ATTRB_TXT_10 = "MKTG_MAP_ATTRB_TXT_10";

    /** MKTG_MAP_ATTRB_TXT_11 */
    public static final String MKTG_MAP_ATTRB_TXT_11 = "MKTG_MAP_ATTRB_TXT_11";

    /** MKTG_MAP_ATTRB_TXT_12 */
    public static final String MKTG_MAP_ATTRB_TXT_12 = "MKTG_MAP_ATTRB_TXT_12";

    /** MKTG_MAP_ATTRB_TXT_13 */
    public static final String MKTG_MAP_ATTRB_TXT_13 = "MKTG_MAP_ATTRB_TXT_13";

    /** MKTG_MAP_ATTRB_TXT_14 */
    public static final String MKTG_MAP_ATTRB_TXT_14 = "MKTG_MAP_ATTRB_TXT_14";

    /** MKTG_MAP_ATTRB_TXT_15 */
    public static final String MKTG_MAP_ATTRB_TXT_15 = "MKTG_MAP_ATTRB_TXT_15";

    /** MKTG_MAP_ATTRB_TXT_16 */
    public static final String MKTG_MAP_ATTRB_TXT_16 = "MKTG_MAP_ATTRB_TXT_16";

    /** MKTG_MAP_ATTRB_TXT_17 */
    public static final String MKTG_MAP_ATTRB_TXT_17 = "MKTG_MAP_ATTRB_TXT_17";

    /** MKTG_MAP_ATTRB_TXT_18 */
    public static final String MKTG_MAP_ATTRB_TXT_18 = "MKTG_MAP_ATTRB_TXT_18";

    /** MKTG_MAP_ATTRB_TXT_19 */
    public static final String MKTG_MAP_ATTRB_TXT_19 = "MKTG_MAP_ATTRB_TXT_19";

    /** MKTG_MAP_ATTRB_TXT_20 */
    public static final String MKTG_MAP_ATTRB_TXT_20 = "MKTG_MAP_ATTRB_TXT_20";

    /** UPLD_CSV_RQST_CMNT_TXT */
    public static final String UPLD_CSV_RQST_CMNT_TXT = "UPLD_CSV_RQST_CMNT_TXT";

    /**
     * EZUPTIME
     */
    public static final String EZUPTIME = "EZUPTIME";

    /**
     * EZUPTIMEZONE
     */
    public static final String EZUPTIMEZONE = "EZUPTIMEZONE";

    // =================================================
    // Other
    // =================================================

    /**
     * SUBMIT
     */
    public static final String SUBMIT = "Submit";

    /**
     * UPLOAD
     */
    public static final String UPLOAD = "Upload";

    // =================================================
    // File Download Upload
    // =================================================
    /** UPLOAD_FILE */
    public static final String UPLOAD_FILE = "Upload File";

    /** MAX_UPLOAD_SIZE_NUM_CONST_KEY */
    public static final String MAX_UPLOAD_SIZE_NUM_CONST_KEY = "NMAL3200_UPLOAD_LIMIT";

    /** MAX_UPLOAD_CNT_NUM_CONST_KEY */
    public static final String MAX_UPLOAD_CNT_NUM_CONST_KEY = "NMAL3200_UPLOAD_MAX_NUM_ROWS";

    /** max download count */
    public static final int MAX_DOWNLOAD_CNT = 100000;

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** CSV_FILE_NAME_UPLOAD */
    public static final String CSV_FILE_NAME_UPLOAD = "Marketing_Data_Analysis_Upload";

    /** CSV_FILE_NAME_SUCCESS */
    public static final String CSV_FILE_NAME_SUCCESS = "Marketing_Data_Analysis_Success";

    /** CSV_FILE_NAME_REVIEW */
    public static final String CSV_FILE_NAME_REVIEW = "Marketing_Data_Analysis_Review";

    /** CSV_FILE_NAME_NEW_PROS */
    public static final String CSV_FILE_NAME_NEW_PROS = "Marketing_Data_Analysis_New_Pros";

    /** EXTN_CSV */
    public static final String EXTN_CSV = ".csv";

    /** CSV_DELIMITER_CHAR */
    public static final char CSV_DELIMITER_CHAR = ',';

    /** ERROR_LENGTH */
    public static final int ERROR_LENGTH = 1000;

    /** ERROR_ITEM */
    public static final int ERROR_ITEM = 2000;

    /** ERROR_NULL */
    public static final int ERROR_NULL = 9000;

    /** ERROR_LENGTH_MSG */
    public static final String ERROR_LENGTH_MSG = " Length error Item the number of/character string length/bytes";

    /** ERROR_ITEM */
    public static final String ERROR_ITEM_MSG = " Item error Numeric conversion/character string attribute";

    /** ERROR_NULL */
    public static final String ERROR_NULL_MSG = " null Error nullValue setting";

    /** CSV_HDR_KEY */
    public static final String[] CSV_HDR_KEY = {DS_ACCT_NM_COL_DFN_NM, FIRST_LINE_ADDR_COL_DFN_NM, SCD_LINE_ADDR_COL_DFN_NM, THIRD_LINE_ADDR_COL_DFN_NM, FRTH_LINE_ADDR_COL_DFN_NM, CTY_ADDR_COL_DFN_NM, CNTY_PK_COL_DFN_NM, ST_CD_COL_DFN_NM,
            POST_CD_COL_DFN_NM, CTRY_CD_COL_DFN_NM, DUNS_NUM_COL_DFN_NM, GLN_COL_DFN_NM, HIN_COL_DFN_NM, ATTRB_TXT_COL_DFN_NM_01, ATTRB_TXT_COL_DFN_NM_02, ATTRB_TXT_COL_DFN_NM_03, ATTRB_TXT_COL_DFN_NM_04, ATTRB_TXT_COL_DFN_NM_05,
            ATTRB_TXT_COL_DFN_NM_06, ATTRB_TXT_COL_DFN_NM_07, ATTRB_TXT_COL_DFN_NM_08, ATTRB_TXT_COL_DFN_NM_09, ATTRB_TXT_COL_DFN_NM_10, ATTRB_TXT_COL_DFN_NM_11, ATTRB_TXT_COL_DFN_NM_12, ATTRB_TXT_COL_DFN_NM_13, ATTRB_TXT_COL_DFN_NM_14,
            ATTRB_TXT_COL_DFN_NM_15, ATTRB_TXT_COL_DFN_NM_16, ATTRB_TXT_COL_DFN_NM_17, ATTRB_TXT_COL_DFN_NM_18, ATTRB_TXT_COL_DFN_NM_19, ATTRB_TXT_COL_DFN_NM_20 };

    /** ERR_MSG_HDR_TXT */
    public static final String ERR_MSG_HDR_TXT = "Error Message";

    /** CSV_HDR_KEY_PREFIX */
    public static final String[] CSV_HDR_KEY_PREFIX = {"Request ID", "Mapping Name" };

    /** CSV_HDR_KEY_SUFIX */
    public static final String[] CSV_HDR_KEY_SUFIX = {"Request By", "Request On", "Upload File Name", "Matching Criteria", "EXACT Matching Location ID", "EXACT Matching Account Code", "Partial Matching Location ID",
            "Partial Matching Account Code", "DUNS Matching Location ID", "DUNS Matching Account Code", "GLN Matching Location ID", "GLN Matching Account Code", "Candidate Territory" };

}
