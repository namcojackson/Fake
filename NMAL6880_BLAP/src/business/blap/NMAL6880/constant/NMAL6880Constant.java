/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6880.constant;

/**
 * <pre>
 * Business ID : NMAL6880 TPC09 WH Mapping Maintenance
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/09/2016   CITS            K.Ogino         Create          N/A
 * 07/12/2017   CITS            T.Kikuhara      Update          QC#19864
 *</pre>
 */
public class NMAL6880Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NMAL6880";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NMAL6880_INIT = "NMAL6880_INIT";

    /**
     * Event Name : CancelDetailLine
     */
    public static final String EVENT_NM_NMAL6880_CANCEL_DETAIL_LINE = "NMAL6880Scrn00_CancelDetailLine";

    /**
     * Event Name : CMN_ColClear
     */
    public static final String EVENT_NM_NMAL6880_CMN_COL_CLEAR = "NMAL6880Scrn00_CMN_ColClear";

    /**
     * Event Name : CMN_ColSave
     */
    public static final String EVENT_NM_NMAL6880_CMN_COL_SAVE = "NMAL6880Scrn00_CMN_ColSave";

    /**
     * Event Name : CMN_Download
     */
    public static final String EVENT_NM_NMAL6880_CMN_DOWNLOAD = "NMAL6880Scrn00_CMN_Download";

    /**
     * Event Name : CMN_Reset
     */
    public static final String EVENT_NM_NMAL6880_CMN_CLEAR = "NMAL6880Scrn00_CMN_Clear";

    /**
     * Event Name : CMN_Return
     */
    public static final String EVENT_NM_NMAL6880_CMN_RETURN = "NMAL6880Scrn00_CMN_Return";

    /**
     * Event Name : CMN_Submit
     */
    public static final String EVENT_NM_NMAL6880_CMN_SUBMIT = "NMAL6880Scrn00_CMN_Submit";

    /**
     * Event Name : EditDetailLine
     */
    public static final String EVENT_NM_NMAL6880_ADD_DETAIL_LINE = "NMAL6880Scrn00_AddDetailLine";

    /**
     * Event Name : PageJump
     */
    public static final String EVENT_NM_NMAL6880_PAGE_JUMP = "NMAL6880Scrn00_PageJump";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NMAL6880_PAGE_NEXT = "NMAL6880Scrn00_PageNext";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NMAL6880_PAGE_PREV = "NMAL6880Scrn00_PagePrev";

    /**
     * Event Name : Search
     */
    public static final String EVENT_NM_NMAL6880_SEARCH = "NMAL6880Scrn00_Search";

    /**
     * Event Name : SearchWH1
     */
    public static final String EVENT_NM_NMAL6880_SEARCH_WH1 = "NMAL6880Scrn00_SearchWH1";

    /**
     * Event Name : SearchWH2
     */
    public static final String EVENT_NM_NMAL6880_SEARCH_WH2 = "NMAL6880Scrn00_SearchWH2";

    /**
     * Event Name : SearchWH2
     */
    public static final String EVENT_NM_NMAL6880_NPAL1010 = "NMAL6880_NPAL1010";

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
     * NPAM0077E: You can add Details up to [@].
     */
    public static final String NPAM0077E = "NPAM0077E";

    /**
     * NMAM0050E:Details cannot be added because the number will
     * exceed [@].
     */
    public static final String NMAM0050E = "NMAM0050E";

    /**
     * NMAM8175E:This data has been updated by another user. [
     * TableName = @ , key = @, value = @ ]
     */
    public static final String NMAM8175E = "NMAM8175E";

    /**
     * NMAM8000E:Data to update by Pkey not found.
     * < Table [@] Pkey [@] >
     */
    public static final String NMAM8000E = "NMAM8000E";

    /**
     * NMAM0282E:Data insert failure.@
     */
    public static final String NMAM0282E = "NMAM0282E";

    /**
     * NMAM0803E:Please enter today or future date on [@].
     */
    public static final String NMAM0803E = "NMAM0803E";

    /**
     * NMAM8506E:For [@], please enter [@] or a later date.
     */
    public static final String NMAM8506E = "NMAM8506E";

    /**
     * NMAM0836E:[@] field is mandatory.
     */
    public static final String NMAM0836E = "NMAM0836E";

    /**
     * NMAM8509E:The data you entered has already been registered.
     */
    public static final String NMAM8509E = "NMAM8509E";

    /**
     * NMAM8121E:[@] is not exists in master.
     */
    public static final String NMAM8121E = "NMAM8121E";

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
     * DB_PARAM_VND_XREF_TP_CD
     */
    public static final String DB_PARAM_VND_XREF_TP_CD = "vndXrefTpCd";

    /**
     * DB_PARAM_VND_SHIP_TO_CUST_CD
     */
    public static final String DB_PARAM_VND_SHIP_TO_CUST_CD = "vndShipToCustCd";

    /**
     * DB_PARAM_VND_SHIP_TO_XREF_PK_LIST
     */
    public static final String DB_PARAM_VND_SHIP_TO_XREF_PK_LIST = "vndShipToXrefPkList";

    /**
     * DB_PARAM_RTL_WH_CD
     */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /**
     * DB_PARAM_RTL_SWH_CD
     */
    public static final String DB_PARAM_RTL_SWH_CD = "rtlSwhCd";

    /**
     * DB_PARAM_SALES_DATE
     */
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    /**
     * DB_PRAM_EFF_FROM_DT
     */
    public static final String DB_PARAM_EFF_FROM_DT = "effFromDt";

    /**
     * DB_PRAM_EFF_THRU_DT
     */
    public static final String DB_PARAM_EFF_THRU_DT = "effThruDt";

    /**
     * DB_PRAM_CMSG
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * DB_PRAM_ROWNUM
     */
    public static final String DB_PARAM_ROWNUM = "rowNum";

    // =================================================
    // DB Result Columns
    // =================================================
    /**
     * VND_SHIP_TO_CUST_CD
     */
    public static final String VND_SHIP_TO_CUST_CD = "VND_SHIP_TO_CUST_CD";

    /**
     * RTL_WH_CD
     */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /**
     * RTL_WH_NM
     */
    public static final String RTL_WH_NM = "RTL_WH_NM";

    /**
     * XX_ALL_LINE_ADDR
     */
    public static final String XX_ALL_LINE_ADDR = "XX_ALL_LINE_ADDR";

    /**
     * RTL_SWH_CD
     */
    public static final String RTL_SWH_CD = "RTL_SWH_CD";

    /**
     * EFF_FROM_DT
     */
    public static final String EFF_FROM_DT = "EFF_FROM_DT";

    /**
     * EFF_THRU_DT
     */
    public static final String EFF_THRU_DT = "EFF_THRU_DT";

    /**
     * VND_SHIP_TO_XREF_PK
     */
    public static final String VND_SHIP_TO_XREF_PK = "VND_SHIP_TO_XREF_PK";

    /**
     * EZUPTIME
     */
    public static final String EZUPTIME = "EZUPTIME";

    /**
     * EZUPTIMEZONE
     */
    public static final String EZUPTIMEZONE = "EZUPTIMEZONE";

    /**
     * EZCANCELFLAG
     */
    public static final String EZCANCELFLAG = "EZCANCELFLAG";

    // =================================================
    // Other
    // =================================================

    /**
     * SUBMIT
     */
    public static final String SUBMIT = "Submit";

    /**
     * Delete Line
     */
    public static final String DELETE = "Delete Line";

    /**
     * XX_CHK_BOX_A1
     */
    public static final String XX_CHK_BOX_A1 = "xxChkBox_A1";

    /**
     * VND_SHIP_TO_XREF
     */
    public static final String VND_SHIP_TO_XREF = "VND_SHIP_TO_XREF";

    /**
     * MSG_START_DATE
     */
    public static final String MSG_START_DATE = "Start Date";

    /**
     * MSG_END_DATE
     */
    public static final String MSG_END_DATE = "End Date";

    /**
     * MSG_VND_SHIP_TO_CUST_CD
     */
    public static final String MSG_VND_SHIP_TO_CUST_CD = "ROSS Location";

    /**
     * MSG_RTL_WH_NM
     */
    public static final String MSG_RTL_WH_NM = "Warehouse Location Name";

    /**
     * MSG_XX_ALL_LINE_ADDR
     */
    public static final String MSG_XX_ALL_LINE_ADDR = "Warehouse Location Address";

    /**
     * MSG_PK
     */
    public static final String MSG_PK = "Primary Key";

    /**
     * MSG_RTL_WH_CD
     */
    public static final String MSG_RTL_WH_CD = "CSA Warehouse";

    /**
     * MSG_RTL_SWH_CD
     */
    public static final String MSG_RTL_SWH_CD = "Sub Warehouse";

    /**
     * XX_CHK_BOX
     */
    public static final String XX_CHK_BOX = "xxChkBox";

    /**
     * MAX_EFF_THRU_DATE
     */
    public static final int MAX_EFF_THRU_DATE = 99991231;

    // =================================================
    // CSV Download
    // =================================================
    /** max download count */
    public static final int MAX_DOWNLOAD_CNT = 65000;

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** CSV_FILE_NAME */
    public static final String CSV_FILE_NAME = "ROSS_Location_CSA_Warehouse_Mapping";

    /** EXTN_CSV */
    public static final String EXTN_CSV = ".csv";

    /** CSV_HDR */
    public static final String[] CSV_HDR = new String[] {XX_CHK_BOX_A1, MSG_VND_SHIP_TO_CUST_CD, MSG_RTL_WH_CD, MSG_RTL_WH_NM, MSG_XX_ALL_LINE_ADDR, MSG_RTL_SWH_CD, MSG_START_DATE, MSG_END_DATE };

}
