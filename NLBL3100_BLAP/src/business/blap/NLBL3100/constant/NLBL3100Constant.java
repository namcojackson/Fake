/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */

package business.blap.NLBL3100.constant;

/**
 *<pre>
 * Reman Labor/Expense Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/13/2015   CITS            M.Ito           Create          N/A
 * 03/05/2019   Hitachi         Y.Takeno        Update          QC#30642
 *</pre>
 */
public interface NLBL3100Constant {

    /**
     * BusinessID
     */
    public static final String BUSINESS_ID = "NLBL3100";

    // =================================================
    // Event Name
    // =================================================
    /** Event Name : Init */
    public static final String EVENT_NM_NLBL3100_INIT = "NLBL3100_INIT";

    /** Event Name : Search */
    public static final String EVENT_NM_NLBL3100SCRN00_SEARCH = "NLBL3100Scrn00_Search";

    /** Event Name : PagePrev */
    public static final String EVENT_NM_NLBL3100SCRN00_PAGE_PREV = "NLBL3100Scrn00_PagePrev";

    /** Event Name : PageNext */
    public static final String EVENT_NM_NLBL3100SCRN00_PAGE_NEXT = "NLBL3100Scrn00_PageNext";

    /** Event Name : Clear */
    public static final String EVENT_NM_NLBL3100SCRN00_CLEAR = "NLBL3100Scrn00_Clear";

    // =================================================
    // Message ID
    // =================================================

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    // =================================================
    // key value of ssm parameter
    // =================================================
    /** cMsg */
    public static final String DB_PARAM_CMSG = "cMsg";

    /** DB Param Name:Coordinator Code */
    public static final String DB_PARAM_MRP_SCHD_COORD_PSN_CD_LIKE = "schdCoordPsnCd_Like";

    /** DB Param Name:Warehouse Code */
    public static final String DB_PARAM_MRP_RTL_WH_CD_LIKE = "rtlWhCd_Like";

    /** DB Param Name:Manager Code */
    public static final String DB_PARAM_MRP_MGR_PSN_CD_LIKE = "mgrPsnCd_Like";

    /** DB Param Name:Supervisor Code */
    public static final String DB_PARAM_MRP_SUPV_PSN_CD_LIKE = "supvPsnCd_Like";

    /** DB Param Name:Carrier Code */
    public static final String DB_PARAM_MRP_CARR_CD_LIKE = "carrCd_Like";

    /** DB Param Name:Carrier Name */
    public static final String DB_PARAM_MRP_CARR_NM_LIKE = "xxPsnNm_H4_Like";

    /** DB Param Name:Coordinator Name */
    public static final String DB_PARAM_MRP_SCHD_COORD_PSN_NM_LIKE = "xxPsnNm_H1_Like";

    /** DB Param Name:Manager Name */
    public static final String DB_PARAM_MRP_MGR_PSN_NM_LIKE = "xxPsnNm_H2_Like";

    /** DB Param Name:Supervisor Name */
    public static final String DB_PARAM_MRP_SUPV_PSN_NM_LIKE = "xxPsnNm_H3_Like";

    /** DB Param Name:Supervisor Name */
    public static final String DB_PARAM_ST_CD_LIKE = "stCd_H1_Like";
    
    /** DB Param Data:Like(%) */
    public static final String DB_PARAM_DATA_LIKE = "%";

    // START 2019/03/05 [QC#30642, ADD]
    /** DB Param Name:Warehouse Name */
    public static final String DB_PARAM_MRP_RTL_WH_NM_LIKE = "rtlWhNm_Like";
    // END   2019/03/05 [QC#30642, ADD]
}
