/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */

package business.blap.NLCL0270.constant;

/**
 *<pre>
 * Supersession Inventory Inquiry Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/20/2015   CITS            M.Ito           Create          N/A
 *</pre>
 */
public interface NLCL0270Constant {

    /**
     * BusinessID
     */
    public static final String BUSINESS_ID = "NLCL0270";

    // =================================================
    // Event Name
    // =================================================
    /** Event Name : Init */
    public static final String EVENT_NM_NLCL0270_INIT = "NLCL0270_INIT";

    /** Event Name : SetItemDescription */
    public static final String EVENT_NM_NLCL0270SCRN00_SET_ITEM_DESCRIPTION = "NLCL0270Scrn00_SetItemDescription";

    /** Event Name : Search */
    public static final String EVENT_NM_NLCL0270SCRN00_ONCLICK_SEARCH = "NLCL0270Scrn00_OnClick_Search";

    /** Event Name : PagePrev */
    public static final String EVENT_NM_NLCL0270SCRN00_PAGE_PREV = "NLCL0270Scrn00_PagePrev";

    /** Event Name : PageNext */
    public static final String EVENT_NM_NLCL0270SCRN00_PAGE_NEXT = "NLCL0270Scrn00_PageNext";

    /** Event Name : Clear */
    public static final String EVENT_NM_NLCL0270SCRN00_CLEAR = "NLCL0270Scrn00_Clear";
    
    /** Event Name : TBLColumnSort */
    public static final String EVENT_NM_NLCL0270SCRN00_TBLCOLUMNSORT = "NLCL0270Scrn00_TBLColumnSort";

    /**
     * A
     */
    public static final String TABLE_A = "A";
    // =================================================
    // Message ID
    // =================================================
    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** The data [@] does not exist in the master. */
    public static final String NZZM0010E = "NZZM0010E";

    // =================================================
    // key value of ssm parameter
    // =================================================
    /** cMsg */
    public static final String DB_PARAM_CMSG = "cMsg";

    /** Global Company Code */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** Merchandise Code */
    public static final String DB_PARAM_MDSE_ID = "mdseCd";

    /** mdseList */
    public static final String DB_PARAM_MDSE_LIST = "mdseList";

    /** DB Param Data:Like(%) */
    public static final String DB_PARAM_DATA_LIKE = "%";

    // =================================================
    // for Supersede API(NWZC2060)
    // =================================================

    /** Mode */
    public static final String MODE = "1";

    /** MDSE_DIGIT degit:8 */
    public static final int MDSE_8_DIGIT = 8;

}
