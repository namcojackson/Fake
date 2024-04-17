/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLCL1040.constant;

/**
 * <pre>
 * Business ID : NLCL1040 Inventory ABC Analysis Setup
 * Function Name : NLCL1040Constant
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            T.Hakodate      Create          N/A
 *</pre>
 */
public class NLCL1040Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NLCL1040";

    /** Screen ID */
    public static final String SCREEN_ID = "NLCL1040Scrn00";

    /** Table Header : A */
    public static final String TABLE_A_HEAD = "AHEAD";

    /** Code Search Size */
    public static final int CODE_SEARCH_SIZE = 1001;

    /** Limit Down load RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /** Limit Date */
    public static final String LIMIT_DATE = "99991231";

    /** String */
    public static final String ABC_ANLS_RQST_SRC_TP_ANLZ = "ABC_ANLS_RQST_SRC_TP_ANLZ";

    /**
     * ABC Analysis Setup
     */
    public static final String CSV_FILE_NAME = "NLCL1040_InventoryABCAnalysisSetup";

    /**
     * ABC Analysis Setup
     */
    public static final String XX_CHECK_BOX = "xxChkBox_A";

    /**
     * TABLE_A
     */
    public static final String TABLE_A = "A";

    /** CSV HEADER DOWNLOAD */
    public static final String[] CSV_HDR_DOWNLOAD = new String[] {
        "ABC Name"
        , "Item Number"
        , "Item Description"
        , "Warehouse Type"
        , "Warehouse Code"
        , "Sub Warehouse"
        , "Stock Status Code"
        , "Stock Status Name"
        , "Current On Hand Quantity"
        , "Current On Hand Value"
        , "Historical Usage Qty"
        , "Historical Usage Value"
        , "Historical Usage Transactions"
        , "ABC Class Name"
        , "ABC Class Tag"};

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : NLCL1040_INIT
     */
    public static final String EVENT_NM_NLCL1040_INIT = "NLCL1040_INIT";

    /**
     * Event Name : NLCL1040Scrn00_SearchHeader
     */
    public static final String EVENT_NM_NLCL1040_SEARCH_HEADER = "NLCL1040Scrn00_SearchHeader";

    /**
     * Event Name : NLCL1040Scrn00_Search_Item
     */
    public static final String EVENT_NM_NLCL1040_SEARCH_ITEM = "NLCL1040Scrn00_Search_Item";

    /**
     * Event Name : NLCL1040Scrn00_Search_Warehouse
     */
    public static final String EVENT_NM_NLCL1040_SEARCH_WAREHOUSE = "NLCL1040Scrn00_Search_Warehouse";

    /**
     * Event Name : NLCL1040Scrn00_Save
     */
    public static final String EVENT_NM_NLCL1040_SAVE = "NLCL1040Scrn00_Save";

    /**
     * Event Name : NLCL1040Scrn00_CMN_Submit
     */
    public static final String EVENT_NM_NLCL1040_CMN_SUBMIT = "NLCL1040Scrn00_CMN_Submit";

    /**
     * Event Name : NLCL1040Scrn00_DeleteTag
     */
    public static final String EVENT_NM_NLCL1040_DELETE_TAG = "NLCL1040Scrn00_DeleteTag";

    /**
     * Event Name : NLCL1040Scrn00_Analyze
     */
    public static final String EVENT_NM_NLCL1040_ANALYZE = "NLCL1040Scrn00_Analyze";

    /**
     * Event Name : NLCL1040Scrn00_Delete
     */
    public static final String EVENT_NM_NLCL1040_DELETE = "NLCL1040Scrn00_Delete";

    /**
     * Event Name : NLCL1040Scrn00_EditTag
     */
    public static final String EVENT_NM_NLCL1040_EDITTAG = "NLCL1040Scrn00_EditTag";

    /**
     * Event Name : NLCL1040Scrn00_PageJump
     */
    public static final String EVENT_NM_NLCL1040_PAGEJUMP = "NLCL1040Scrn00_PageJump";

    /**
     * Event Name : NLCL1040Scrn00_PageNext
     */
    public static final String EVENT_NM_NLCL1040_PAGENEXT = "NLCL1040Scrn00_PageNext";

    /**
     * Event Name : NLCL1040Scrn00_PagePrev
     */
    public static final String EVENT_NM_NLCL1040_PAGPREV = "NLCL1040Scrn00_PagePrev";

    /**
     * Event Name : NLCL1040Scrn00_TBLColumnSort
     */
    public static final String EVENT_NM_NLCL1040_TBLCOLUMNSORT = "NLCL1040Scrn00_TBLColumnSort";

    /**
     * Event Name : NLCL1040Scrn00_AddTag
     */
    public static final String EVENT_NM_NLCL1040_ADDTAG = "NLCL1040Scrn00_AddTag";

    /**
     * Event Name : NLCL1040Scrn00_CMN_Clear
     */
    public static final String EVENT_NM_NLCL1040_CMN_CLEAR = "NLCL1040Scrn00_CMN_Clear";

    /**
     * Event Name : NLCL1040Scrn00_CMN_Reset
     */
    public static final String EVENT_NM_NLCL1040_CMN_RESET = "NLCL1040Scrn00_CMN_Reset";

    /**
     * Event Name : NLCL1040Scrn00_Refresh
     */
    public static final String EVENT_NM_NLCL1040_REFRESH = "NLCL1040Scrn00_Refresh";

    /**
     * Event Name : NLCL1040Scrn00_OpenWin_NewClass
     */
    public static final String EVENT_NM_OPEN_WIN_EDIT_CLASS = "NLCL1040Scrn00_OpenWin_EditClass";

    /**
     * Event Name :
     * NLCL1040Scrn00_OpenWin_NLCL1040Scrn00_NLCL1040_NLCL1050NewClass
     */
    public static final String EVENT_NM_NLCL1040_NLCL1050 = "NLCL1040_NLCL1050";

    /**
     * Event Name : NLCL1040Scrn00_CMN_Download
     */
    public static final String EVENT_NM_NLCL1040_CMN_DOWNLOAD = "NLCL1040Scrn00_CMN_Download";

    /**
     * Event Name : NLCL1040Scrn00_CMN_ColClear
     */
    public static final String EVENT_NM_NLCL1040_CMN_COLCLEAR = "NLCL1040Scrn00_CMN_ColClear";

    /**
     * Event Name : NLCL1040Scrn00_ItemSearch
     */
    public static final String EVENT_NM_NLCL1040_ITEMSEARCH = "NLCL1040Scrn00_ItemSearch";

    /**
     * Event Name : NLCL1040Scrn00_CMN_ColSave
     */
    public static final String EVENT_NM_NLCL1040_CMN_COLSAVE = "NLCL1040Scrn00_CMN_ColSave";

    /***************************************************
     * Message
     ***************************************************/

    /** Data does not exist in Stock Status Code Master. */
    public static final String NLZM0024E = "NLZM0024E";

    /** Data does not exist in @ */
    public static final String NLCM0170E = "NLCM0170E";

    /** [@] is not selected. */
    public static final String NLZM2274E = "NLZM2274E";

    /** The entered [@] does not exist in master. */
    public static final String NLZM2278E = "NLZM2278E";

    /** The combination of [@] and [@] does not exist in master. */
    public static final String NLZM2279E = "NLZM2279E";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NMAM0182E = "NMAM0182E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] field requires numeric input only. */
    public static final String ZZM9004E = "ZZM9004E";

    /** Please enter "@". */
    public static final String NLAM0173E = "NLAM0173E";

    /** In "@", please enter the date after "@ date". */
    public static final String NLGM0059E = "NLGM0059E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /**
     * 
     */
    public static final String NMAM0038I = "NMAM0038I";

    /** [@] does not exist in Master.. */
    public static final String NPAM0076E = "NPAM0076E";

    /** The entered @ does not exist in Master. */
    public static final String NPAM1361E = "NPAM1361E";

    /** Entered @ is already registered. Is it OK?. */
    public static final String NLAM1310W = "NLAM1310W";

    /** The ABC Name will be deleted. OK?. */
    public static final String NLCM0196I = "NLCM0196I";

    /**
     * Selected Warehouse Type is different from the Warehouse Type of
     * entered warehouse..
     */
    public static final String NLCM0185E = "NLCM0185E";

    /** Please select [@]. */
    public static final String NLGM0070E = "NLGM0070E";

    /** Entered @ is already registered. */
    public static final String NLAM1294E = "NLAM1294E";

    /** The input data has been updated. Click 'Save'. */
    public static final String NLCM0193E = "NLCM0193E";

    /** This data has been updated by another user. */
    public static final String NPAM1297E = "NPAM1297E";

    /** Data insert failure.@. */
    public static final String NLCM0125E = "NLCM0125E";

    /** Data delete failure. (table name is [@]). */
    public static final String NLCM0131E = "NLCM0131E";

    /** The record cannot be updated. Table Name[@]. */
    public static final String NLCM0206E = "NLCM0206E";

    /**
     * NWAM0221E:Data failed @. [ Table Name = @, Return Code = @ ]
     */
    public static final String NWAM0221E = "NWAM0221E";

    /**
     * You can't request analsys during Open request status.(Requested
     * or In Process)
     */
    public static final String NLCM0194E = "NLCM0194E";

    /**
     * The delete target, entered "ABC NAME" does not exist.
     */
    public static final String NLCM0195E = "NLCM0195E";

    /**
     * [@] does not exist in Master.
     */
    public static final String NLCM0197E = "NLCM0197E";

    /**
     * NMAM8509E:The data you entered has already been registered.
     */
    public static final String NMAM8509E = "NMAM8509E";

    /**
     * ABC tag does not match.
     */
    public static final String NLCM0205E = "NLCM0205E";

    /**
     * Intangible Item can't be entered.
     */
    public static final String NLCM0198E = "NLCM0198E";

    /**
     * The details of the process target have not been selected.
     */
    public static final String NPAM0049E = "NPAM0049E";

    /**
     * The number for this process exceeds the maximum numbers for
     * display and cannot proceed.
     */
    public static final String NMAM8114E = "NMAM8114E";

    /** For "@", please enter a date earlier than "@" */
    public static final String NLAM0028E = "NLAM0028E";

    /**
     * NPAM0077E : You can add Details up to [@].
     */
    public static final String NPAM0077E = "NPAM0077E";

    /**
     * NLCM0208E : It exceeds the maximum display number. Please edit tag after item search.
     */
    public static final String NLCM0208E = "NLCM0208E";

    /**
     * NLCM0212I : Delete line check completed. Please execute "Submit".
     */
    public static final String NLCM0212I = "NLCM0212I";

    /***************************************************
     * DB COLUMN
     ***************************************************/
    public static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** . */
    public static final String BIND_SMSG = "sMsg";

    /** . */
    public static final String BIND_CMSG = "cMsg";

    /** . */
    public static final String BIND_ROWNUM = "rowNum";

    /** . */
    public static final String BIND_ABC_ANLS_CLS_NM = "abcAnlsClsNm";

    /** . */
    public static final String BIND_ABC_ASG_PK = "abcAsgPk";

    /** . */
    public static final String BIND_ABC_ANLS_RQST_STS_CD = "abcAnlsRqstStsCd";

    /** . */
    public static final String BIND_MDSE_CD = "mdseCd";

    /** . */
    public static final String BIND_STK_STS_CD = "stkStsCd";

    /** . */
    public static final String BIND_RTL_SWH_CD = "rtlSwhCd";

    /** . */
    public static final String BIND_RTL_WH_CD = "rtlWhCd";

    /** . */
    public static final String BIND_WH_CD_LIST = "whCdList";

    /** . */
    public static final String BIND_SWH_CD_LIST = "subWhCdList";

    /** . */
    public static final String BIND_RTL_WH_CATG_CD = "rtlWhCatgCd";

    /** . */
    public static final String BIND_ABC_ANLS_CLS_NUM = "abcAnlsClsNum";

    /** . */
    public static final String BIND_SALES_DATE = "salesDate";

    /** . */
    public static final String BIND_LIMIT_EFF_THRU_DT = "limitEffThruDt";

}
