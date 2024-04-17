/**
 *<pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 *</pre>
 */
package business.blap.NLCL1060.constant;


/**
 * <pre>
 * 3rd Party Onhand Inventory Inquiry Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/15   Hitachi         S.Dong          Create          QC#61398
 *</pre>
 */
public class NLCL1060Constant {
    /** Search Condition (%)*/
    public static final String STR_SEARCH_PARAM_LIKE = "%";

    /** Global Company Code CUSA */
    public static final String GLBL_CMPY_CD_CUSA = "ABR";
    // ---------------------------------------
    // Messages
    // ---------------------------------------

    /** No data found with this search criteria. */
    public static final String NLCM0002I = "NLCM0002I";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Too many search results.  Please narrow your search criteria and retry */
    public static final String ZZZM9002W = "ZZZM9002W";

    // ---------------------------------------
    // EVENT Name
    // ---------------------------------------
    /** Event (INIT) */
    public static final String EVENT_NM_NLCL1060_INIT = "NLCL1060_INIT";

    /** Event (Search) */
    public static final String EVENT_NM_NLCL1060_SEARCH = "NLCL1060Scrn00_Search";

    /** Event (PageNext) */
    public static final String EVENT_NM_NLCL1060_PAGE_NEXT = "NLCL1060Scrn00_PageNext";

    /** Event (PagePrev) */
    public static final String EVENT_NM_NLCL1060_PAGE_PREV = "NLCL1060Scrn00_PagePrev";

    /** Event (Clear) */
    public static final String EVENT_NM_NLCL1060_CMN_CLEAR = "NLCL1060Scrn00_CMN_Clear";

    /** Event (Close) */
    public static final String EVENT_NM_NLCL1060_CMN_CLOSE = "NLCL1060Scrn00_CMN_Close";

    /** Event (TBLColumnSort) */
    public static final String EVENT_NM_NLCL1060_TBL_COLUMN_SORT = "NLCL1060Scrn00_TBLColumnSort";

    // ---------------------------------------
    // DB item
    // ---------------------------------------
    /** glblCmpyCd */
    public static final String GLBL_CMPY_CD = "glblCmpyCd";

    /** cusaCmpyCd */
    public static final String CUSA_CMPY_CD = "cusaCmpyCd";

    /** mdseCd */
    public static final String MDSE_CD = "mdseCd";

    /** mdseDescShortTxt */
    public static final String MDSE_DESC_SHORT_TXT = "mdseDescShortTxt";

    /** vdnCd */
    public static final String VND_CD = "vndCd";

    /** cusaPrtVndCd */
    public static final String CUSA_PRT_VND_CD = "cusaPrtVndCd";

    /** stkStsCd */
    public static final String STK_STS_CD = "stkStsCd";

    /** dplyVndNm */
    public static final String DPLY_VND_NM = "dplyVndNm";

    /** rtlWhNm */
    public static final String RTL_WH_NM = "rtlWhNm";

    /** rtlWhCd */
    public static final String RTL_WH_CD = "rtlWhCd";

    /** invtyQty */
    public static final String INVTY_QTY = "invtyQty";

    /** locStsDescTxt */
    public static final String LOC_STS_DESC_TXT = "locStsDescTxt";

    /** stkStsDescTxt */
    public static final String STK_STS_DESC_TXT = "stkStsDescTxt";

    /** locTpDescTxt */
    public static final String LOC_TP_DESC_TXT = "locTpDescTxt";

    /** Binding Name : Available Location Code Array */
    public static final String BIND_AVAL_LOC_CD_ARRAY = "avalLocCdArray";

    /** rowNum */
    public static final String ROW_NUM = "rowNum";

    /** xxChkBox */
    public static final String XX_CHK_BOX = "xxChkBox";
}
