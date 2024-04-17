/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3080.contant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   CITS            H.Sugawara      Create          N/A
 * 02/02/2017   CITS            M.Naito         Update          QC#12673
 * 03/30/2018   CITS            Keiichi Masaki  Update          QC#24997
 * 2019/03/26   CITS            K.Ogino         Update          QC#30903
 * 2019/04/12   CTIS            K.Ogino         Update          QC#31163
 *</pre>
 */
public class NLBL3080Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NLBL3080";

    /** Screen ID */
    public static final String SCREEN_ID = "NLBL3080Scrn00";

    /**
     * TAB ID: Order
     */
    public static final String TAB_ID_ORD = "Order";

    /**
     * TAB ID: Order Line
     */
    public static final String TAB_ID_ORD_LINE = "OrderLine";

    /**
     * Search Type: 1(by Search Condition)
     */
    public static final String SEARCH_TP_COND = "1";

    /**
     * Search Type: 2(Re-Search by So#)
     */
    public static final String SEARCH_TP_RESEARCH = "2";

    // DEL QC#24997 START
    /**
     * Search Type: 3(by Change Tab)
     */
//    public static final String SEARCH_TP_TAB = "3";
    // DEL QC#24997 END

    /**
     * Back Order Qty
     */
    public static final String BACK_ORDER_QTY = "Back Order Qty";

    /**
     * Current Available Qty
     */
    public static final String CURR_AVIL_QTY = "Current Available Qty";

    /**
     * Partical Allocation Flag No
     */
    public static final String PRTL_ACPT_FLG_NO = "0";

    /**
     * Item Flip Acceptable Flag No
     */
    public static final String ITEM_FLIPT_FLG_ON = "1";

    /**
     * Item Flip Acceptable Flag No
     */
    public static final String ITEM_FLIPT_FLG_NO = "0";

    /**
     * Warehouse Flip Acceptable Flag No
     */
    public static final String WH_FLIP_FLG_NO = "0";

    /**
     * DS_COND_CONST Not Allocation Warehouse
     */
    public static final String NLBL3080_NOT_ALLC_WH = "NLBL3080_NOT_ALLC_WH";

    /** Duplicate Check : Serial Only */
    public static final String DUP_CHK_SERIAL = "1";

    /** Duplicate Check : Serial and Model */
    public static final String DUP_CHK_MDL = "2";

    /** SHPG_PLN_NUM degit:8 */
    public static final int SHPG_PLN_NUM_DIGIT = 8;

    /** MDSE_DIGIT degit:8 */
    public static final int MDSE_8_DIGIT = 8;

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Limit Down load RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /** CSV file name */
    public static final String CSV_FILE_NAME_ORD_LINE = "NLBL3080_ManageBackorder";

    /** CSV_HDR_ORD */
    public static final String[] CSV_HDR_ORD_LINE = new String[] {
          "xxSmryLineFlg"
        , "xxChkBox_B1"
        , "xxChkBox_B2"
        , "Order Number"
        , "Line"
        , "Item"
        , "Item Description"
        , "MT"
        , "Sup"
        , "Set Item"
        , "Essential Critical"
        , "SS"
        , "Back Order Qty"
        , "Allocated Qty"
        , "Current Available"
        , "Serial"
        , "Model"
        , "Ship Config"
        , "Pick Config"
        , "Request Date"
        , "Warehouse"
        , "SWH"
        , "Line Category"
        , "Line Status"};

    /** Details require more than 1 row.  Please enter. */
    public static final String NLBM0002E = "NLBM0002E";

    /** It is being updated by another user.  Please start again from a search. */
    public static final String NLBM0009E = "NLBM0009E";

    /** Cancel process ended abnormally. */
    public static final String NLBM0049E = "NLBM0049E";

    /** Effective Asset does not exist. */
    public static final String NLBM1290E = "NLBM1290E";

    /** The Serial # specified does not exist in this WH. */
    public static final String NLBM1299W = "NLBM1299W";

    /** Please select all components of the set item. */
    public static final String NLBM1320E = "NLBM1320E";

    /** Total Back Order Qty for the selected lines exceeds Current Available Qty at the Warehouse. */
    public static final String NLBM1321E = "NLBM1321E";

    /** Your request cannot be processed under this status. */
    public static final String NLZM2283E = "NLZM2283E";

    /** Select all details with the same Config Number. [@] */
    public static final String NLZM2284E = "NLZM2284E";

    /** The value for [@] must be [@] or less. */
    public static final String NLZM2316E = "NLZM2316E";

    /** The entered Serial Number is already allocated by other order. */
    public static final String NLZM2317E = "NLZM2317E";

    /** The entered Serial Number is located at customer site. */
    public static final String NLZM2318E = "NLZM2318E";

    /** The entered Serial Number does not include in the specified configuration. */
    public static final String NLZM2319E = "NLZM2319E";

    /** The specified serial number is the component of the other configuration. */
    public static final String NLZM2324E = "NLZM2324E";

    /** Install Base cannot be uniquely specified. */
    public static final String NLZM2436E = "NLZM2436E";

    /** The specified Serial# already exists as different item.　Please check the Serial#. */
    public static final String NLZM2450E = "NLZM2450E";

    /** Please execute again after checking the warning field. */
    public static final String NATM0001W = "NATM0001W";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** Hard allocation could not been executed. */
    public static final String NLCM0037E = "NLCM0037E";

    /** This serial is not existed in Machine Master. */
    public static final String NMAM8421E = "NMAM8421E";

    /** No corresponding Machine Master information. */
    public static final String NACM0008E = "NACM0008E";

    // QC#20008 Add
    /** Stock status of the specified Serial number is different from IB. */
    public static final String NLZM2414E = "NLZM2414E";

    // =================================================
    // Event Name
    // =================================================
    /** Event Name: Alloc_Cancel */
    public static final String EVENT_NM_ALLOC_CANCEL = "NLBL3080Scrn00_Alloc_Cancel";

    /** Event Name: CMN_Submit */
    public static final String EVENT_NM_CMN_SUBMIT = "NLBL3080Scrn00_CMN_Submit";

    /** Total Back Order Qty for the selected lines exceeds Current Available Qty at the Warehouse. Please search again. */
    public static final String NLBM1371E = "NLBM1371E";

    /** The serials specified in the order do not exist in the warehouse.Please confirm the Order Information */
    public static final String NLBM1372E = "NLBM1372E";
}
