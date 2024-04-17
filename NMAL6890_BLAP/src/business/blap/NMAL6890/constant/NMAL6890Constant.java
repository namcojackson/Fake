/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL6890.constant;

/**
 * <pre>
 * Business ID : NMAL6890 Warehouse Search
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/11/2016   CITS            Y.Kuroda        Create          N/A
 * 21/10/2022   HITACHI         B.Amarsanaa     Update          (QC#60609)
 * 03/07/2023   Hitachi         S.Dong          Update          QC#61205
 *</pre>
 */

public class NMAL6890Constant {

    /** BusinessID */
    public static final String BUSINESS_ID = "NMAL6890";

    /** Screen ID */
    public static final String SCREEN_ID = "NMAL6890Scrn00";

    /** CSV file name */
    public static final String CSV_FILE_NAME = "WarehouseSearch";

    // =================================================
    // Common
    // =================================================
    /** Comma */
    public static final String COMMA = ",";

    /** Percent */
    public static final String PERCENT = "%";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Limit Search RowNumber */
    public static final int LIMIT_DL_ROWNUM_SEARCH = 999;

    /** Limit Download RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65000;

    // =================================================
    // Event Name
    // =================================================
    /** Event Name : Init */
    public static final String EVENT_NM_NMAL6890_INIT = "NMAL6890_INIT";

    /** Event Name : Search */
    public static final String EVENT_NM_NMAL6890SCRN00_SEARCH = "NMAL6890Scrn00_Search";

    /** Event Name : get Owner Name */
    public static final String EVENT_NM_NMAL6890SCRN00_OPENWIN_OWNER = "NMAL6890Scrn00_OpenWin_Owner";

    /** Event Name : get Alternate Owner Name */
    public static final String EVENT_NM_NMAL6890SCRN00_OPENWIN_ALTOWNER = "NMAL6890Scrn00_OpenWin_AltOwner";

    /** Event Name : CMN_Clear */
    public static final String EVENT_NM_NMAL6890SCRN00_CMN_CLEAR = "NMAL6890Scrn00_CMN_Clear";

    /** Event Name : CMN_Download */
    public static final String EVENT_NM_NMAL6890SCRN00_CMN_DOWNLOAD = "NMAL6890Scrn00_CMN_Download";

    /** Event Name : CMN_ColClear */
    public static final String EVENT_NM_NMAL6890SCRN00_CMN_COL_CLEAR = "NMAL6890Scrn00_CMN_ColClear";

    /** Event Name : CMN_ColSave */
    public static final String EVENT_NM_NMAL6890SCRN00_CMN_COL_SAVE = "NMAL6890Scrn00_CMN_ColSave";

    /** Event Name : NMAL6890_NMAL6820 */
    public static final String EVENT_NM_NMAL6890_NMAL6820 = "NMAL6890_NMAL6820";

    /** Event Name : NMAL6890_NMAL6050 */
    public static final String EVENT_NM_NMAL6890_NMAL6050 = "NMAL6890_NMAL6050";

    /** Event Name : SaveSearhOption */
    public static final String EVENT_NM_NMAL6890SCRN00_SAVESEARCHOPTION = "NMAL6890Scrn00_SaveSearchOption";

    /** Event Name : DeleteSearhOption */
    public static final String EVENT_NM_NMAL6890SCRN00_DELETESEARCHOPTION = "NMAL6890Scrn00_DeleteSearchOption";

    /** Event Name : OnChange_SearchOption */
    public static final String EVENT_NM_NMAL6890SCRN00_ONCHANGE_SEARCHOPTION = "NMAL6890Scrn00_OnChange_SearchOption";

    /** Event Name : PageNext */
    public static final String EVENT_NM_NMAL6890SCRN00_PAGE_NEXT = "NMAL6890Scrn00_PageNext";

    /** Event Name : PagePrev */
    public static final String EVENT_NM_NMAL6890SCRN00_PAGE_PREV = "NMAL6890Scrn00_PagePrev";

    /** Event Name : PageJump */
    public static final String EVENT_NM_NMAL6890SCRN00_PAGE_JUMP = "NMAL6890Scrn00_PageJump";

    /** Event Name : TBLColumnSort */
    public static final String EVENT_NM_NMAL6890SCRN00_TBLCOLUMNSORT = "NMAL6890Scrn00_TBLColumnSort";

    // =================================================
    // key value of ssm parameter
    // =================================================
    /** cMsg */
    public static final String DB_PARAM_CMSG = "cMsg";

    /** FLG_ON */
    public static final String FLG_ON = "flgOn";

    /** VND_XREF_TP_CD : CSA to CUSA */
    public static final String CSA_TO_CUSA = "1";

    // =================================================
    // DB Param
    // =================================================
    /** DB Param: glblCmpyCd */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** DB Param: srchOptAplId */
    public static final String DB_PARAM_SRCH_OPT_APL_ID = "srchOptAplId";

    /** DB Param: srchOptUsrId */
    public static final String DB_PARAM_SRCH_OPT_USR_ID = "srchOptUsrId";

    /** DB Param: rtlWhNm_H1 */
    public static final String DB_PARAM_RTL_WH_NM_H1 = "rtlWhNm_H1";

    /** DB Param: rtlWhDescTxt */
    public static final String DB_PARAM_RTLWHDESCTXT = "rtlWhDescTxt";

    /** DB Param: rtlWhCd */
    public static final String DB_PARAM_RTLWHCD = "rtlWhCd";

    /** DB Param: rtlWhCatgCd_RS */
    public static final String DB_PARAM_RTLWHCATGCD_RS = "rtlWhCatgCd_RS";

    /** DB Param: invtyAcctCd_IS */
    public static final String DB_PARAM_INVTYACCTCD_IS = "invtyAcctCd_IS";

    /** DB Param: telNum */
    public static final String DB_PARAM_TELNUM = "telNum";

    /** DB Param: procrTpCd_SS */
    public static final String DB_PARAM_PROCRTPCD_SS = "procrTpCd_SS";

    /** DB Param: procrTpCd_RS */
    public static final String DB_PARAM_PROCRTPCD_RS = "procrTpCd_RS";

    /** DB Param: procrTpCd_ES */
    public static final String DB_PARAM_PROCRTPCD_ES = "procrTpCd_ES";

    /** DB Param: plnItemInsrcCd_MS */
    public static final String DB_PARAM_PLNITEMINSRCCD_MS = "plnItemInsrcCd_MS";

    /** DB Param: vndLocCd */
    public static final String DB_PARAM_VNDLOCCD = "vndLocCd";

    /** DB Param: invtyOwnrCd_OS */
    public static final String DB_PARAM_INVTYOWNRCD_OS = "invtyOwnrCd_OS";

    /** DB Param: firstRefCmntTxt */
    public static final String DB_PARAM_FIRSTREFCMNTTXT = "firstRefCmntTxt";

    /** DB Param: effFromDt */
    public static final String DB_PARAM_EFFFROMDT = "effFromDt";

    /** DB Param: effThruDt */
    public static final String DB_PARAM_EFFTHRUDT = "effThruDt";

    // START 2023/03/07 S.Dong [QC#61205, ADD]
    /** DB Param: whOwnrCd_H1 */
    public static final String DB_PARAM_WHOWNRCD_H1 = "whOwnrCd_H1";
    // END 2023/03/07 S.Dong [QC#61205, ADD]

    /** DB Param: fullPsnNm_O1 */
    public static final String DB_PARAM_FULLPSNNM_O1 = "fullPsnNm_O1";

    /** DB Param: fullPsnNm_O2 */
    public static final String DB_PARAM_FULLPSNNM_O2 = "fullPsnNm_O2";

    /** DB Param: shipToLocNm */
    public static final String DB_PARAM_SHIPTOLOCNM = "shipToLocNm";

    /** DB Param: rtrnToLocNm */
    public static final String DB_PARAM_RTRNTOLOCNM = "rtrnToLocNm";

    /** DB Param: rtlWhNm_H2 */
    public static final String DB_PARAM_RTLWHNM_H2 = "rtlWhNm_H2";

    /** DB Param: rtlWhNm_H3 */
    public static final String DB_PARAM_RTLWHNM_H3 = "rtlWhNm_H3";

    /** DB Param: rtlWhNm_H3 */
    public static final String DB_PARAM_RTLWHNM_H4 = "rtlWhNm_H4";
    
    //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD START
    /** DB Param: ctyAddr */
    public static final String DB_PARAM_CTYADDR = "ctyAddr";
    //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD END
    
    /** DB Param: rtlWhNm_H4 */
    public static final String DB_PARAM_RTLSWHCD = "rtlSwhCd";

    /** DB Param: rtlSwhCd */
    public static final String DB_PARAM_VND_XREF_TP_CD = "vndXrefTpCd";

    /** DB Param: limitRownum */
    public static final String DB_PARAM_LIMIT_ROWNUM = "limitRownum";

    // =================================================
    // Define Table Column to create Pulldown
    // =================================================
    /** SRCH_OPT_PK */
    public static final String SRCH_OPT_PK_DBCOLUMN = "SRCH_OPT_PK";

    /** SRCH_OPT_NM */
    public static final String SRCH_OPT_NM_DBCOLUMN = "SRCH_OPT_NM";

    /** RTL_WH_CATG_CD */
    public static final String RTL_WH_CATG_CD_DBCOLUMN = "RTL_WH_CATG_CD";

    /** RTL_WH_CATG_DESC_TXT */
    public static final String RTL_WH_CATG_DESC_TXT_DBCOLUMN = "RTL_WH_CATG_DESC_TXT";

    /** INVTY_ACCT_CD */
    public static final String INVTY_ACCT_CD_DBCOLUMN = "INVTY_ACCT_CD";

    /** INVTY_ACCT_DESC_TXT */
    public static final String INVTY_ACCT_DESC_TXT_DBCOLUMN = "INVTY_ACCT_DESC_TXT";

    /** PROCR_TP_CD */
    public static final String PROCR_TP_CD_DBCOLUMN = "PROCR_TP_CD";

    /** PROCR_TP_DESC_TXT */
    public static final String PROCR_TP_DESC_TXT_DBCOLUMN = "PROCR_TP_DESC_TXT";

    /** PLN_ITEM_INSRC_CD */
    public static final String PLN_ITEM_INSRC_CD_DBCOLUMN = "PLN_ITEM_INSRC_CD";

    /** PLN_ITEM_INSRC_DESC_TXT */
    public static final String PLN_ITEM_INSRC_DESC_TXT_DBCOLUMN = "PLN_ITEM_INSRC_DESC_TXT";

    /** INVTY_OWNR_CD */
    public static final String INVTY_OWNR_CD_DBCOLUMN = "INVTY_OWNR_CD";

    /** INVTY_OWNR_DESC_TXT */
    public static final String INVTY_OWNR_DESC_TXT_DBCOLUMN = "INVTY_OWNR_DESC_TXT";

    // =================================================
    // The Message Code
    // =================================================
    /** [@] is not selected. */
    public static final String MSG_CD_NMAM0014E = "NMAM0014E";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String MSG_CD_NMAM0182E = "NMAM0182E";

    /** No search results found. */
    public static final String MSG_CD_NMAM0038I = "NMAM0038I";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String MSG_CD_NZZM0001W = "NZZM0001W";

    /** Search results exceeded [@]. Only showing first @ records. */
    public static final String MSG_CD_NMAM8181W = "NMAM8181W";

    /** The process has been successfully completed. */
    public static final String MSG_CD_NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String MSG_CD_ZZM9000E = "ZZM9000E";

    /** The process [@] has been successfully completed. */
    public static final String MSG_CD_ZZZM9003I = "ZZZM9003I";
}
