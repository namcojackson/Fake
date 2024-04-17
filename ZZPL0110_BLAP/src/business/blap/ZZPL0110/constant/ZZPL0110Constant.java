/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.ZZPL0110.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/13   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0110Constant {

    /*****************************************************************
     * Event Name
     ****************************************************************/

    /** INIT */
    public static final String EVENT_INIT = "ZZPL0110_INIT";

    /** ZZSL1110 */
    public static final String EVENT_ZZSL1110_SCRN00 = "ZZPL0110_ZZSL1110";

    /** Common Clear */
    public static final String EVENT_CMN_CLEAR_SCRN00 = "ZZPL0110Scrn00_CMN_Clear";

    /** Common Submit */
    public static final String EVENT_CMN_SUBMIT_SCRN00 = "ZZPL0110Scrn00_CMN_Submit";

    /** Company Lookup */
    public static final String EVENT_COMPANY_LOOKUP_SCRN00 = "ZZPL0110Scrn00_CompanyLookup";

    /** Page Next */
    public static final String EVENT_PAGE_NEXT_SCRN00 = "ZZPL0110Scrn00_PageNext";

    /** Page Prev */
    public static final String EVENT_PAGE_PREV_SCRN00 = "ZZPL0110Scrn00_PagePrev";

    /** Search */
    public static final String EVENT_SEARCH_SCRN00 = "ZZPL0110Scrn00_Search";

    /** Table Column Sort */
    public static final String EVENT_TBL_COLUMN_SORT_SCRN00 = "ZZPL0110Scrn00_TBLColumnSort";

    /*****************************************************************
     * report job status
     ****************************************************************/

    /** CANCELED */
    public static final String RPT_JOB_STS_CANCELED = "CANCELED";

    /** FAIL */
    public static final String RPT_JOB_STS_FAIL = "FAIL";

    /** BLANK */
    public static final String RPT_JOB_STS_BLANK = "";

    /*****************************************************************
     * Date Format
     ****************************************************************/

    /** System Date Format */
    public static final String SYSTEM_DATE_FORMAT = "yyyyMMddHHmmssSSS";

    /** Creation Date Format */
    public static final String DATE_FORMAT = "yyyyMMdd";

}
