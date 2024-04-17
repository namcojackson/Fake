/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.ZZPL0030.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/23   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0030Constant {

    /*****************************************************************
     * Event Name
     ****************************************************************/

    /** INIT */
    public static final String EVENT_INIT = "ZZPL0030_INIT";

    /** Common Clear */
    public static final String EVENT_CMN_CLEAR_SCRN00 = "ZZPL0030Scrn00_CMN_Clear";

    /** Open ReportName Row */
    public static final String EVENT_OPEN_RPT_NM_ROW = "ZZPL0030Scrn00_OpenRptNm_Row";

    /** Page Next */
    public static final String EVENT_PAGE_NEXT_SCRN00 = "ZZPL0030Scrn00_PageNext";

    /** Page Prev */
    public static final String EVENT_PAGE_PREV_SCRN00 = "ZZPL0030Scrn00_PagePrev";

    /** Search */
    public static final String EVENT_SEARCH_SCRN00 = "ZZPL0030Scrn00_Search";

    /** Table Column Sort */
    public static final String EVENT_TBL_COLUMN_SORT_SCRN00 = "ZZPL0030Scrn00_TBLColumnSort";

    /** Common Clear */
    public static final String EVENT_CMN_CLEAR_SCRN01 = "ZZPL0030Scrn01_CMN_Clear";

    /** Open ReportTitle Row */
    public static final String EVENT_OPEN_RPT_TTL_ROW = "ZZPL0030Scrn01_OpenRptTtl_Row";

    /** Page Next */
    public static final String EVENT_PAGE_NEXT_SCRN01 = "ZZPL0030Scrn01_PageNext";

    /** Page Prev */
    public static final String EVENT_PAGE_PREV_SCRN01 = "ZZPL0030Scrn01_PagePrev";

    /** Search */
    public static final String EVENT_SEARCH_SCRN01 = "ZZPL0030Scrn01_Search";

    /** Table Column Sort */
    public static final String EVENT_TBL_COLUMN_SORT_SCRN01 = "ZZPL0030Scrn01_TBLColumnSort";

    /*****************************************************************
     * Sequence name for Archive Viewer access log
     ****************************************************************/

    /** Sequence Name */
    public static final String SEQUENCE_FOR_ACCESS_LOG = "ARC_VIEW_ACCS_LOG_SQ";

    /*****************************************************************
     * Date Format
     ****************************************************************/

    /** System Date Format */
    public static final String SYSTEM_DATE_FORMAT = "yyyyMMddHHmmssSSS";

    /** Creation Date Format */
    public static final String DATE_FORMAT = "yyyyMMdd";

    /** Creation Date Format */
    public static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm";

    /*****************************************************************
     * File Extension
     ****************************************************************/

    /** PDF */
    public static final String EXTENSION_PDF = ".pdf";
}
