/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7090.constant;

/**
 * <pre>
 * Business ID : NMAL7090 Item Supersessions Mass Add
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NMAL7090Constant {
    /** Business Application ID */
    public static final String BIZ_ID = "NMAL7090";

    /** Screen ID */
    public static final String SCRN_ID = "NMAL7090Scrn00";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Maximum Number of Rows */
    public static final int MAX_ROWS = 5000;

    /** Maximum Number of Rows */
    public static final int DOWNLOAD_MAX_ROWS = 65000;

    /** TABLE_A **/
    public static final String TABLE_A = "A";

    /** TABLE_A **/
    public static final String TABLE_B = "B";

    /** TABLE_C **/
    public static final String TABLE_C = "C";

    /** Display Name : Upload Data Format */
    public static final String UPLOAD_DATA_FORMAT = "CSV";

    // =================================================
    // Message
    // =================================================

    /** ZZZM9003I */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** No search results found. */
    public static final String NMAM0007I = "NMAM0007I";

    /** Too many search results. Please narrow your search criteria and retry. */
    public static final String NZZM0007E = "NZZM0007E";

    /** ZZM9037E */
    public static final String ZZM9037E = "ZZM9037E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Data insert failure. [ TableName = @ , key = @, value = @ ] */
    public static final String ZZMM0001E = "ZZMM0001E";

    /** This data has been updated by another user. [ TableName = @ , key = @, value= @ ] */
    public static final String NMAM8175E = "NMAM8175E";

    /** Please check Submit or Discard. */
    public static final String NMAM8429E = "NMAM8429E";

    /** Please check Discard. */
    public static final String NMAM8431E = "NMAM8431E";

    /** If [@] is entered, please enter [@], too. */
    public static final String NMAM0049E = "NMAM0049E";

    /** Please select same price list type's price list ID. */
    public static final String NMAM8426E = "NMAM8426E";

    /** Please don't input New Price if you checked Retain Original Price. */
    public static final String NMAM8427E = "NMAM8427E";

    /** Please input New Price if you don't check Retain Original Price. */
    public static final String NMAM8428E = "NMAM8428E";

    /** Please entry 8 digits merchandise code. */
    public static final String NMAM8216E = "NMAM8216E";

    /** [@] is not exists in master. */
    public static final String NMAM8121E = "NMAM8121E";

    /** @ cannot be added because it is exceeding the maximum number of row [@] */
    public static final String NMAM8187E = "NMAM8187E";

    /** The format of [@] is incorrect. */
    public static final String NMAM0052E = "NMAM0052E";

    /** The same [@] as @ cannot be registered. */
    public static final String NMAM0833E = "NMAM0833E";

    /** The Upload File is empty or only has a header line, therefore it could not be processed. Please confirm the content of the Upload file. */
    public static final String ZYEM0004E = "ZYEM0004E";

    /** 1st row should include the column headers and be discarded when processing the data. */
    public static final String NMAM8434E = "NMAM8434E";

    /** Upload file must contain at least 1 row */
    public static final String NMAM8435E = "NMAM8435E";

    /** Upload file has incorrect data. Please verify it. ( [1] row, [2], [3]) */
    public static final String NMAM8436E = "NMAM8436E";

    // =================================================
    // Event Name
    // =================================================
    /** Event Name : Init */
    public static final String EVENT_NM_NMAL7090_INIT = "NMAL7090_INIT";

    /** Event Name : CMN_Clear */
    public static final String EVENT_NM_NMAL7090_CMN_CLEAR = "NMAL7090Scrn00_CMN_Clear";

    /** Event Name : CMN_Delete */
    public static final String EVENT_NM_NMAL7090_CMN_DELETE = "NMAL7090Scrn00_CMN_Delete";

    /** Event Name : CMN_Submit */
    public static final String EVENT_NM_NMAL7090_CMN_SUBMIT = "NMAL7090Scrn00_CMN_Submit";

    /** Event Name : Search */
    public static final String EVENT_NM_NMAL7090_SEARCH = "NMAL7090Scrn00_Search";

    /** Event Name : PageNext */
    public static final String EVENT_NM_NMAL7090_PAGE_NEXT = "NMAL7090Scrn00_PageNext";

    /** Event Name : PagePrev */
    public static final String EVENT_NM_NMAL7090_PAGE_PREV = "NMAL7090Scrn00_PagePrev";

    /** Event Name : Refresh */
    public static final String EVENT_NM_NMAL7090_UPDATE = "NMAL7090Scrn00_UpdatePriceList";

    /** Event Name : Refresh */
    public static final String EVENT_NM_NMAL7090_COPY = "NMAL7090Scrn00_Copy";

    /** Event Name : Refresh */
    public static final String EVENT_NM_NMAL7090_INSERT_ROW = "NMAL7090Scrn00_InsertRow";

    /** Event Name : Refresh */
    public static final String EVENT_NM_NMAL7090_REFRESH = "NMAL7090Scrn00_Refresh";

    /** Event Name : Apply A */
    public static final String EVENT_NM_NMAL7090_APPLY_A = "NMAL7090Scrn00_Apply_A";

    /** Event Name : Apply B */
    public static final String EVENT_NM_NMAL7090_APPLY_B = "NMAL7090Scrn00_Apply_B";

    /** Event Name : Apply C */
    public static final String EVENT_NM_NMAL7090_APPLY_C = "NMAL7090Scrn00_Apply_C";

    /** Event Name : Upload */
    public static final String EVENT_NM_NMAL7090_UPLOAD = "NMAL7090Scrn00_Upload";

    /** Event Name : Download Template */
    public static final String EVENT_NM_NMAL7090_TEMPLATE = "NMAL7090Scrn00_Template";

    /** Event Name : Download Historical Request Result */
    public static final String EVENT_NM_NMAL7090_DOWNLOAD_HIS_RQST_RSLT = "NMAL7090Scrn00_DownloadHistoricalRequestResult";

    /** Event Name : CMN Download */
    public static final String EVENT_NM_NMAL7090_DOWNLOAD = "NMAL7090Scrn00_CMN_Download";

    /** Event Name : On Change Discard */
    public static final String EVENT_NM_NMAL7090_ON_CHANGE_DISCARD_ALL = "NMAL7090Scrn00_OnChange_DiscardAll";

    /** Event Name : On Change Submit */
    public static final String EVENT_NM_NMAL7090_ON_CHANGE_SUBMIT_ALL = "NMAL7090Scrn00_OnChange_SubmitAll";

    // =================================================
    // DB Param
    // =================================================
    /** DB Param Name: Global Company Code */
    public static final String DB_PRM_GLBL_CMPY_CD = "glblCmpyCd";

    /** DB Param Name: rqstStsTpCd */
    public static final String DB_PRM_RQST_STS_TP_CD = "rqstStsTpCd";

    /** DB Param Name: rqstStsTpCd */
    public static final String DB_PRM_PRC_QLFY_VAL_TXT = "prcQlfyValTxt";

    /** DB Param Name: rqstStsTpCd */
    public static final String DB_PRM_PRC_QLFY_TP_CD = "prcQlfyTpCd";

    /** DB Param Name: rqstStsTpCd */
    public static final String DB_PRM_SUPD_FROM_MDSE_CD = "supdFromMdseCd";

    /** DB Param Name: rqstStsTpCd */
    public static final String DB_PRM_SUPD_TO_MDSE_CD = "supdToMdseCd";

    /** DB Param Name: rqstStsTpCd */
    public static final String DB_PRM_RQST_STS_TP_CD_NEW = "rqstStsTpCdNew";

    /** DB Param Name: rqstStsTpCd */
    public static final String DB_PRM_RQST_STS_TP_CD_REQUESTED = "rqstStsTpCdRequested";

    /** DB Param Name: PRC_LIST_TP_CD */
    public static final String DB_PRM_PRC_LIST_TP_CD = "prcListTpCd";

    /** DB Param Name: PRC_LIST_TP_CD */
    public static final String DB_PRM_PRC_LIST_ACT_TP_CD = "prcListActTpCd";

    /** DB Param Name: PRC_LIST_TP_CD */
    public static final String DB_PRM_PRC_LIST_TP_DESC_TXT = "prcListTpDescTxt";

    /** DB Param Name: PRC_LIST_TP_CD */
    public static final String DB_PRM_PRC_LIST_ACT_TP_DESC_TXT = "prcListActTpDescTxt";

    /** DB Param Name: PRC_CATG_CD */
    public static final String DB_PRM_PRC_CATG_CD = "prcListIds";

    /** DB Param Name: MDSE_CD */
    public static final String DB_PRM_MDSE_CD = "mdseCd";

    /** DB Param Name: PRC_LIST_EQUIP_RQST_PK */
    public static final String DB_PRM_PRC_LIST_EQUIP_RQST_PK = "prcListEquipRqstPk";

    // =================================================
    // DB Columns
    // =================================================
    /** DB Column Name: PRC_LIST_TP_CD */
    public static final String DB_CLM_PRC_LIST_TP_CD = "PRC_LIST_TP_CD";

    /** DB Column Name: PRC_LIST_TP_DESC_TXT */
    public static final String DB_CLM_PRC_LIST_TP_DESC_TXT = "PRC_LIST_TP_DESC_TXT";

    /** DB Column Name: PRC_LIST_ACT_TP_CD */
    public static final String DB_CLM_PRC_LIST_ACT_TP_CD = "PRC_LIST_ACT_TP_CD";

    /** DB Column Name: PRC_LIST_ACT_TP_DESC_TXT */
    public static final String DB_CLM_PRC_LIST_ACT_TP_DESC_TXT = "PRC_LIST_ACT_TP_DESC_TXT";

    /** DB Column Name: RQST_STS_TP_CD */
    public static final String DB_CLM_RQST_STS_TP_CD = "RQST_STS_TP_CD";

    /** DB Column Name: RQST_STS_TP_DESC_TXT */
    public static final String DB_CLM_RQST_STS_TP_DESC_TXT = "RQST_STS_TP_DESC_TXT";
}
