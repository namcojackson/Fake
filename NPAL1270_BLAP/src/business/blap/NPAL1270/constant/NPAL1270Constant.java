/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1270.constant;

/**
 * <pre>
 * Business ID : NPAL1270 PO Requisition List
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2016   CITS            R Shimamoto     Create          N/A
 * 03/01/2016   CITS            K.Ogino         Update          QC#4610
 * 03/16/2016   CITS            K.Ogino         Update          QC#5634
 * 08/23/2017   CITS            H.Naoi          Update          Sol#097(QC#18398)
 * 01/29/2018   CITS            T.Gotoda        Update          QC#22521
 * 09/23/2019   CITS            R.Shimamoto     Update          QC#53271
 * 02/01/2023   Hitachi         S.Dong          Update          QC#60966
 *</pre>
 */
public class NPAL1270Constant {
    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1270";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1270Scrn00";

    // CSV D/L
    /** max download count */
    public static final int MAX_DOWNLOAD_CNT = 65000;

    /** fetch size */
    public static final int FETCH_SIZE = 3000;

    /**  */
    public static final String CSV_FILE_NAME = "NPAL1270_PORequisitionList";

    /** */
    public static final String EXTN_CSV = ".csv";

    /** CSV file header Information Detail */
    public static final String[] CSV_HEADER = new String[] {

    "Requisition #" //
            , "Requisition Type" //
            , "Header Status" //
            , "Approval Status" //
            , "Date Created" //
            , "Need By Date" //
            , "Need By Time" //
            // START 2023/02/02 S.Dong [QC#60966, ADD]
            , "Vendor Ship Date" //
            // END 2023/02/02 S.Dong [QC#60966, ADD]
            , "Document Source Type" //
            , "Source Doc#" //
            //08/22/2017 CITS H.Naoi Add Sol#097(QC#18398) START
            , "Plan Name" //
            //08/22/2017 CITS H.Naoi Add Sol#097(QC#18398) END
            , "Buyer" //
            , "Buyer Name" //
            , "Business Unit" //
            , "Req Service Level" //
            , "Carrier" //
            , "Carrier Name" //
            , "Supplier" //
            , "Suppler Name" //
            , "Supplier Site" //
            , "Supplier Site Name" //
            , "Destination WH" //
            , "Destination WH Name" //
            , "Destination SWH" //
            , "Destination SWH Name" //
            , "Ship To Customer" //
            , "Ship To Customer Name" //
            , "PO Number" //
            , "Line#" //
            , "Line Status" //
            , "Item Number" //
            , "Item Description" //
            , "MT" //
            , "PC" //
            , "Order Qty" //
            , "Planned PO Release Date" //
            , "PO Release Date" //
    };

    /** CSV file header Information Detail */
    public static final String[] CSV_EXCLUSION_ITEM = new String[] {"Line Status" //
            , "Item Number" //
            , "Item Description" //
            , "Order Qty" //
            , "Planned PO Release Date" //
            , "PO Release Date" //
    };

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NPAL1270_INIT = "NPAL1270_INIT";

    /**
     * Event Name : CMN_Reset
     */
    public static final String EVENT_NM_NPAL1270_CMN_RESET = "NPAL1270Scrn00_CMN_Reset";

    /**
     * Event Name : SetItemDescription
     */
    public static final String EVENT_NM_NPAL1270_SET_ITEM_DESCRIPTION = "NPAL1270Scrn00_SetItemDescription";

    /**
     * Event Name : SetSupplierName
     */
    public static final String EVENT_NM_NPAL1270_SET_SUPPLIER_NAME = "NPAL1270Scrn00_SetSupplierName";

    /**
     * Event Name : Search
     */
    public static final String EVENT_NM_NPAL1270_SEARCH = "NPAL1270Scrn00_Search";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NPAL1270_PAGE_NEXT = "NPAL1270Scrn00_PageNext";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NPAL1270_PAGE_PREV = "NPAL1270Scrn00_PagePrev";

    /**
     * Event Name : SaveSearchOption
     */
    public static final String EVENT_NM_NPAL1270_SAVE_SEARCH_OPTION = "NPAL1270Scrn00_SaveSearchOption";

    /**
     * Event Name : DeleteSearchOption
     */
    public static final String EVENT_NM_NPAL1270_DELETE_SEARCH_OPTION = "NPAL1270Scrn00_DeleteSearchOption";

    /**
     * Event Name : OnChange_SearchOption
     */
    public static final String EVENT_NM_NPAL1270_ON_CHANGE_SEARCH_OPTION = "NPAL1270Scrn00_OnChange_SearchOption";

    /**
     * Event Name : CMN_ColClear
     */
    public static final String EVENT_NM_NPAL1270_CMN_COL_CLEAR = "NPAL1270Scrn00_CMN_ColClear";

    /**
     * Event Name : CMN_ColSave
     */
    public static final String EVENT_NM_NPAL1270_CMN_COL_SAVE = "NPAL1270Scrn00_CMN_ColSave";

    /**
     * Event Name : CMN_ColSave
     */
    public static final String EVENT_NM_NPAL1270_CMN_DOWNLOAD = "NPAL1270Scrn00_CMN_Download";

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param Name: PRCH_REQ_REC_TP_CD
     */
    public static final String DB_PARAM_PRCH_REQ_REC_TP_CD = "prchReqRecTpCd";

    /**
     * DB Param Name: PO_REQUISITION
     */
    public static final String DB_PARAM_PO_REQUISITION = "poRequisition";

    /**
     * DB Param Name: Search Option Application Id
     */
    public static final String DB_PARAM_SRCH_OPT_APL_ID = "srchOptAplId";

    /**
     * DB Param Name: Search Option User Id
     */
    public static final String DB_PARAM_SRCH_OPT_USR_ID = "srchOptUsrId";

    /**
     * DB Param Name: MDSE code
     */
    public static final String DB_PARAM_MDSE_CD = "mdseCd";

    /**
     * DB Param Name:Supplier Number
     */
    public static final String DB_PARAM_PRNT_VND_CD = "prntVndCd";

    /**
     * DB Param Name:Supplier Site Number
     */
    public static final String DB_PARAM_VND_CD = "vndCd";

    /**
     * DB Param Name:Destination WH Code
     */
    public static final String DB_PARAM_DEST_RTL_WH_CD = "destRtlWhCd";

    /**
     * DB Param Name:Destination SWH Code
     */
    public static final String DB_PARAM_DEST_RTL_SWH_CD = "destRtlSwhCd";

    /**
     * DB Param Name:cMsg
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * DB Param Name:Sales Date
     */
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    /**
     * DB Param Name:Row Num
     */
    public static final String DB_PARAM_ROW_NUM = "rowNum";

    /**
     * DB Param Name:prchReqNum
     */
    public static final String DB_PARAM_PRCH_REQ_NUM = "prchReqNum";

    /**
     * DB Param prchReqTpCd
     */
    public static final String DB_PARAM_PRCH_REQ_TP_CD = "prchReqTpCd";

    /**
     * DB Param prchReqStsCd
     */
    public static final String DB_PARAM_PRCH_REQ_STS_CD = "prchReqStsCd";

    /**
     * DB Param prchReqApvlStsCd
     */
    public static final String DB_PARAM_PRCH_REQ_APVL_STS_CD = "prchReqApvlStsCd";

    /**
     * DB Param prchReqCratDt_FR
     */
    public static final String DB_PARAM_PRCH_REQ_CRAT_DT_FR = "prchReqCratDt_FR";

    /**
     * DB Param prchReqCratDt_TO
     */
    public static final String DB_PARAM_PRCH_REQ_CRAT_DT_TO = "prchReqCratDt_TO";

    /**
     * DB Param prchReqSrcTpCd
     */
    public static final String DB_PARAM_PRCH_REQ_SRC_TP_CD = "prchReqSrcTpCd";

    /**
     * DB Param trxRefNum
     */
    public static final String DB_PARAM_TRX_REF_NUM = "trxRefNum";

    //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
    /**
     * DB Param mrpPlnNm
     */
    public static final String DB_PARAM_MRP_PLN_NM = "mrpPlnNm";
    //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END

    /**
     * DB Param prchGrpCd
     */
    public static final String DB_PARAM_PRCH_GRP_CD = "prchGrpCd";

    /**
     * DB Param shipToCustCd
     */
    public static final String DB_PARAM_SHIP_TO_CUST_CD = "shipToCustCd";

    /**
     * DB Param prchReqLineStsCd
     */
    public static final String DB_PARAM_PRCH_REQ_LINE_STS_CD = "prchReqLineStsCd";

    /**
     * DB Param rqstRcvDt_FR
     */
    public static final String DB_PARAM_RQST_RCV_DT_FR = "rqstRcvDt_FR";

    /**
     * DB Param rqstRcvDt_TO
     */
    public static final String DB_PARAM_RQST_RCV_DT_TO = "rqstRcvDt_TO";
    // START 2023/02/01 S.Dong [QC#60966, ADD]
    /**
     * DB Param rqstShipDt_FR
     */
    public static final String DB_PARAM_RQST_SHIP_DT_FR = "rqstShipDt_FR";

    /**
     * DB Param rqstShipDt_TO
     */
    public static final String DB_PARAM_RQST_SHIP_DT_TO = "rqstShipDt_TO";
    // END 2023/02/01 S.Dong [QC#60966, ADD]
    /**
     * DB Param shpgSvcLvlCd
     */
    public static final String DB_PARAM_SHPG_SVC_LVL_CD = "shpgSvcLvlCd";

    /**
     * DB Param poOrdNum
     */
    public static final String DB_PARAM_PO_ORD_NUM = "poOrdNum";

    /**
     * DB Param fullPsnNm
     */
    public static final String DB_PARAM_FULL_PSN_NM = "fullPsnNm";

    /**
     * DB Param carrNm
     */
    public static final String DB_PARAM_CARR_LOC_NM = "carrNm";

    /**
     * DB Param salesDprntVndNmate
     */
    public static final String DB_PARAM_PRNT_VND_NM = "salesDprntVndNmate";

    /**
     * DB Param vndNm
     */
    public static final String DB_PARAM_SUPPLIERSITE_LOC_NM = "vndNm";

    /**
     * DB Param rtlWhNm
     */
    public static final String DB_PARAM_RTL_WH_NM = "rtlWhNm";

    /**
     * DB Param rtlSwhNm
     */
    public static final String DB_PARAM_RTL_SWH_NM = "rtlSwhNm";

    /**
     * DB Param shipToLocNm
     */
    public static final String DB_PARAM_DS_ACCT_NM = "shipToLocNm";

    // 2019/09/23 QC#53271 Add Start
    /** */
    public static final String DB_PARAM_PRCH_REQ_LINE_TP_CD_EXPENSE = "prchReqLineTpCdExpense";

    /** */
    public static final String DB_PARAM_PRCH_REQ_LINE_TP_CD_EXPENSE_W_RECEIPT = "prchReqLineTpCdExpenseWReceipt";
    // 2019/09/23 QC#53271 Add End

    // =================================================
    // DB Columns
    // =================================================
    /**
     * DB Column Name: Search Option PK
     */
    public static final String DB_COLUMN_SRCH_OPT_PK = "SRCH_OPT_PK";

    /**
     * DB Column Name: Search Option Name
     */
    public static final String DB_COLUMN_SRCH_OPT_NM = "SRCH_OPT_NM";

    /**
     * DB Column Name: PO Requisition Type Code
     */
    public static final String DB_COLUMN_PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";

    /**
     * DB Column Name: PO Requisition Type Code
     */
    public static final String DB_COLUMN_PRCH_REQ_TP_DESC_TXT = "PRCH_REQ_TP_DESC_TXT";

    /**
     * DB Column Name: PRCH_REQ_STS_CD
     */
    public static final String DB_COLUMN_PRCH_REQ_STS_CD = "PRCH_REQ_STS_CD";

    /**
     * DB Column Name: PRCH_REQ_STS_DESC_TXT
     */
    public static final String DB_COLUMN_PRCH_REQ_STS_DESC_TXT = "PRCH_REQ_STS_DESC_TXT";

    /**
     * DB Column Name: PRCH_REQ_APVL_STS_CD
     */
    public static final String DB_COLUMN_PRCH_REQ_APVL_STS_CD = "PRCH_REQ_APVL_STS_CD";

    /**
     * DB Column Name: PRCH_REQ_APVL_STS_DESC_TXT
     */
    public static final String DB_COLUMN_PRCH_REQ_APVL_STS_DESC_TXT = "PRCH_REQ_APVL_STS_DESC_TXT";

    /**
     * DB Column Name: PRCH_REQ_LINE_STS_CD
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_STS_CD = "PRCH_REQ_LINE_STS_CD";

    /**
     * DB Column Name: PRCH_REQ_LINE_STS_DESC_TXT
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_STS_DESC_TXT = "PRCH_REQ_LINE_STS_DESC_TXT";

    /**
     * DB Column Name: PRCH_REQ_SRC_TP_CD
     */
    public static final String DB_COLUMN_PRCH_REQ_SRC_TP_CD = "PRCH_REQ_SRC_TP_CD";

    /**
     * DB Column Name: PRCH_REQ_SRC_TP_DESC_TXT
     */
    public static final String DB_COLUMN_PRCH_REQ_SRC_TP_DESC_TXT = "PRCH_REQ_SRC_TP_DESC_TXT";

    /**
     * DB Column Name: PRCH_GRP_CD
     */
    public static final String DB_COLUMN_PRCH_GRP_CD = "PRCH_GRP_CD";

    /**
     * DB Column Name: PRCH_GRP_DESC_TXT
     */
    public static final String DB_COLUMN_PRCH_GRP_DESC_TXT = "PRCH_GRP_DESC_TXT";

    /**
     * DB Column Name: SHPG_SVC_LVL_CD
     */
    public static final String DB_COLUMN_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /**
     * DB Column Name: SHPG_SVC_LVL_DESC_TXT
     */
    public static final String DB_COLUMN_SHPG_SVC_LVL_DESC_TXT = "SHPG_SVC_LVL_DESC_TXT";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NZZM0010E: The data [@] does not exist in the master.
     */
    public static final String NZZM0010E = "NZZM0010E";

    /**
     * NMAM0038I: No search result is found.
     */
    public static final String NMAM0038I = "NMAM0038I";

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * NZZM0001W: There are too many search results, there is data
     * that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * NMAM8181W:
     */
    public static final String NMAM8181W = "NMAM8181W";

    /**
     * ZZM9000E:
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * NMAM0182E: You can not [@] this record Because of [@] already
     * exists.
     */
    public static final String NMAM0182E = "NMAM0182E";

    /**
     * ZZM9003I: The process [@] has been successfully completed.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    /**
     * NMAM0014E: [@] is not selected.
     */
    public static final String NMAM0014E = "NMAM0014E";

    /**
     * NZZM0007E:
     */
    public static final String NZZM0007E = "NZZM0007E";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : MDSE_CD
     */
    public static final String DISPLAY_NM_MDSE_CD = "Item Number";

    /**
     * Display Name : PRNT_VND_CD
     */
    public static final String DISPLAY_NM_PRNT_VND_CD = "Supplier Number";

    /**
     * Display Name : SRCH_OPT_NM
     */
    public static final String DISPLAY_NM_SRCH_OPT_NM = "Saved Option Name";

    /**
     * Display Name : SRCH_OPT_PK
     */
    public static final String DISPLAY_NM_SRCH_OPT_PK = "Saved Search Options";

    /**
     * Display Name : DISPLAY_LEVEL_HEADER
     */
    public static final String DISPLAY_LEVEL_HEADER = "Header";

    /**
     * Display Name : DISPLAY_LEVEL_DETAIL
     */
    public static final String DISPLAY_LEVEL_DETAIL = "Detail";

}
