/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1530.constant;
/**
 *<pre>
 * Business ID : NPAL1530 Min-Max Planning Report Run Screen
 * Function Name : Constant (BLAP)
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/23/2016   CITS            Keiichi Masaki  Create          N/A
 * 01/16/2018   CITS            T.Tokutomi      Update          QC#21879
 *</pre>
 */
public class NPAL1530Constant {
    /**
     * Planning Level Code : PLAN NAME
     */
    public static final String PLAN_NAME = "01";

    /**
     * Planning Level Code : SUB WAREHOUSE
     */
    public static final String SUB_WAREHOUSE = "02";

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name:cMsg
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * DB Param Name:MRP Enable Flag
     */
    public static final String DB_PARAM_MRP_ENBL_FLG = "mrpEnblFlg";

    /**
     * DB Param Name:Registration Status Code
     */
    public static final String DB_PARAM_RGTN_STS_CD = "rgtnStsCd";

    /**
     * Constant Param Name:NPAL1530_DMND_CTOFF_DAYS_AOT
     */
    public static final String NPAL1530_DMND_CTOFF_DAYS_AOT = "NPAL1530_DMND_CTOFF_DAYS_AOT";

    /**
     * Constant Param Name:NPAL1530_DMND_CTOFF_DAYS_AOT
     */
    public static final String NPAL1530_SPLY_CTOFF_DAYS_AOT = "NPAL1530_SPLY_CTOFF_DAYS_AOT";

    /**
     * Sequence Object Name:MRP_RUN_PRM_SQ
     */
    public static final String MRP_RUN_PRM_SQ = "MRP_RUN_PRM_SQ";

    /**
     * Date format:DATEFORMAT
     */
    public static final String DATEFORMAT = "yyyyMMddHHmmssSSS";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NPAL1530_INIT = "NPAL1530_INIT";

    /**
     * Event Name : Clear
     */
    public static final String EVENT_NM_NPAL1530_CMN_CLEAR = "NPAL1530Scrn00_CMN_Clear";

    /**
     * Event Name : CMN_Submit
     */
    public static final String EVENT_NM_NPAL1530_CMN_SUBMIT = "NPAL1530Scrn00_CMN_Submit";

    /**
     * Event Name : SetWarehouseName
     */
    public static final String EVENT_NM_NPAL1530_SET_WAREHOUSENAME = "NPAL1530Scrn00_SetWarehouseName";

    /**
     * Event Name : SetSubWarehouseName
     */
    public static final String EVENT_NM_NPAL1530_SET_SUB_WAREHOUSENAME = "NPAL1530Scrn00_SetSubWarehouseName";

    /**
     * QC#21879 Add.
     * Event Name : NPAL1530_NWAL1130
     */
    public static final String EVENT_NM_NPAL1530_NWAL1130 = "NPAL1530_NWAL1130";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NPAM0224E: Valid [@] does not exist in master.
     */
    public static final String NPAM0224E = "NPAM0224E";

    /**
     * NLAM1091E: Entered data is already registered.  
     */
    public static final String NLAM1091E = "NLAM1091E";

    /**
     * NLAM0014E: It is being updated by another user.  Please start over from the search.
     */
    public static final String NLAM0014E = "NLAM0014E";

    /**
     * NPAM1494E: A value of @ must not be less than '0' .
     */
    public static final String NPAM1494E = "NPAM1494E";

    /**
     * NPAM1495E: Will not process if MRP Run status is "Processing"
     */
    public static final String NPAM1495E = "NPAM1495E";
    
    /**
     * NPAM1573E: The entered @ does not exist in Master.
     */
    public static final String NPAM1573E = "NPAM1573E";
    
    /**
     * ZZZM9003I: The process [@] has been successfully completed.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

}
