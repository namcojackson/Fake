package business.blap.NPAL1080.constant;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS            Yasushi Nomura  Create          N/A
 * 05/06/2016   CSAI            D.Fukaya        Update          QC#7628
 * 09/21/2016   CITS            K.Ogino         Update          QC#8195
 * 11/22/2016   CITS            K.Ogino         Update          QC#8295
 * 12/19/2016   CITS            K.Ogino         Update          QC#15324
 * 08/22/2017   CITS            R.Shimamoto     Update          QC#19966
 * 08/31/2017   CITS            K.Kameoka       Update          Sol#369(QC#19243)
 * 10/25/2017   CITS            S.Katsuma       Update          QC#21896
 * 12/26/2017   CITS            K.Ogino         Update          QC#21908
 * 01/17/2018   CITS            K.Ogino         Update          QC#21909
 * 02/26/2018   CITS            S.Katsuma       Update          QC#24312
 * 03/13/2018   CITS            S.Katsuma       Update          SOL#118(QC#12110)
 * 05/25/2018   CITS            Y.Iwasaki       Update          SOL#135(QC#2366)
 * 07/05/2018   CITS            K.Ogino         Update          QC#24918
 * 07/31/2018   CITS            Y.Iwasaki       Update          QC#27459
 * 08/20/2018   CITS            T.Tokutomi      Update          QC#26581
 * 08/20/2018   CITS            T.Tokutomi      Update          QC#26662
 * 12/05/2018   CITS            M.Naito         Update          QC#25900
 * 02/14/2019   Fujitsu         S.Takami        Update          QC#30358
 * 02/13/2020   CITS            Y.Iwasaki       Update          QC#55702,55709
 * 03/05/2020   Fujitsu         R.Nakamura      Update          QC#56103
 * 03/10/2020   Fujitsu         R.Nakamura      Update          QC#56178
 * 03/12/2020   Fujitsu         R.Nakamura      Update          QC#56104
 * 06/18/2021   CITS            J.Evangelista   Update          QC#58876
 * 03/17/2023   Hitachi         T.Kuroda        Update          QC#61204
 * 08/03/2023   Hitachi         T.Kuroda        Update          QC#61648
 * 10/20/2023   Hitachi         G.Quan          Update          QC#61494
 *</pre>
 */
public class NPAL1080Constant {
    /**
     * Business Application ID
     */
    public static final String BUSINESS_APPLICATION_ID = "NPAL1080";

    /**
     * Business Application ID
     */
    public static final String NMXC100001_BIZ_APP_KEY_ID = "H";

    /**
     * Date time format string : yyyyMMddHHmmss
     */
    public static final String TIME_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * AM
     */
    public static final String TIME_AM = "AM";

    /**
     * PM
     */
    public static final String TIME_PM = "PM";

    /**
     * Date string length : YYYYMMDD
     */
    public static final int DATE_STR_LENGTH = 8;

    /**
     * Date string length : HHMMSS
     */
    public static final int TIME_STR_LENGTH = 6;

    /**
     * Hour minute string length : HHMM
     */
    public static final int HOUR_MINUTE_STR_LENGTH = 4;

    /**
     * Hour string length : HH
     */
    public static final int HOUR_STR_LENGTH = 2;

    /**
     * Minute string length : MM
     */
    public static final int MINUTE_STR_LENGTH = 2;

    /**
     * half day hours
     */
    public static final int HALF_DAY_HOURS = 12;

    /**
     * One day hours
     */
    public static final int ONE_DAY_HOURS = 24;

    /**
     * One hour munutes
     */
    public static final int ONE_HOUR_MINUTES = 60;

    /**
     * Tab Header
     */
    public static final String TAB_HEADER = "Header";

    /**
     * Tab Detail
     */
    public static final String TAB_DETAIL = "Detail";

    /** . */
    public static final int COMNT_LENGTH = 240;

    /** MAX_DATE:99991231 */
    public static final String MAX_DATE = "99991231";

    // QC#19966 Add.
    /** SWH_R: R */
    public static final String SWH_R = "R";

    /** SWH_NEW: NEW */
    public static final String SWH_NEW = "NEW";

    // Add Start 2020/03/05 QC#56103
    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Message Kind Warning */
    public static final String MESSAGE_KIND_WARN = "W";
    // Add End 2020/03/05 QC#56103

    /**
     * Group ID for Constant Value
     */
    public static final String DS_COND_CONST_GRP_ID = "NPZC1170";
    
    // QC#26662 Update 2018/08/20. Add Function of special Update mode.
    /**
     * FUNC_ID_SPEC_UPDATE
     */
    public static final String FUNC_ID_SPEC_UPDATE = "NPAL1080T030";

    // START 2023/03/17 T.Kuroda [QC#61204, ADD]
    /**
     * status code : 1000
     */
    public static final int CSV_READ_STATUS_CODE_1000 = 1000;

    /**
     * status code : 2000 
     */
    public static final int CSV_READ_STATUS_CODE_2000 = 2000;

    /**
     * Upload Template File Name
     */
    public static final String CSV_FILE_NAME = "TechRequestEntry_UploadTemplate";

    /**
     * Upload Template File Extension
     */
    public static final String TMPL_FILE_EXTENSION = ".csv";

    /**
     * Upload Template File Header
     */
    public static final String[] CSV_HEADER = new String[]{
        "Item Number", "Req Qty", "Source Type", "WH / Supplier", "SWH / Site"};

    /**
     * Display Name : Upload Data Format
     */
    public static final String UPLOAD_DATA_FORMAT = "CSV";
    // END   2023/03/17 T.Kuroda [QC#61204, ADD]

    // =================================================
    // Message Code
    // =================================================
    /**
     * ZZM9003I: The process [@] has been successfully completed.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    /**
     * Search results exceeded [@]. Only showing first @ records.
     */
    public static final String NMAM8181W = "NMAM8181W";

    /**
     * The data specified does not exist.
     */
    public static final String NPAM0089E = "NPAM0089E";

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
     * NMAM0014E: [@] is not selected.
     */
    public static final String NMAM0014E = "NMAM0014E";

    /** MSG */
    public static final String NPAM0006E = "NPAM0006E";

    /** MSG */
    public static final String NPAM0008E = "NPAM0008E";

    /** MSG */
    public static final String NPAM0005I = "NPAM0005I";

    /** MSG */
    public static final String NPAM0027E = "NPAM0027E";

    /** MSG */
    public static final String NPAM0088E = "NPAM0088E";

    /** MSG */
    public static final String NPAM1360E = "NPAM1360E";

    /** MSG */
    public static final String NPAM0046E = "NPAM0046E";

    /** MSG */
    public static final String NPAM0079E = "NPAM0079E";

    /** MSG */
    public static final String NPAM1363E = "NPAM1363E";

    /** MSG */
    public static final String NPAM1368E = "NPAM1368E";

    /** MSG */
    public static final String ZZM8100I = "ZZM8100I";

    /** MSG */
    public static final String NPAM0049E = "NPAM0049E";

    /** MSG */
    public static final String NPAM1365E = "NPAM1365E";

    /** MSG */
    public static final String NMAM0836E = "NMAM0836E";

    /** MSG */
    public static final String NLAM0173E = "NLAM0173E";

    /** MSG */
    public static final String ZZM9001E = "ZZM9001E";

    /**
     * The entered @ does not exist in Master.
     */
    public static final String NPAM1361E = "NPAM1361E";

    /**
     * Either [@] or [@] must be specified.
     */
    public static final String NPAM1517E = "NPAM1517E";

    /**
     * There are multiple Technician Code associated with entered
     * Technician Name. Please specify Technician Code.
     */
    public static final String NPAM1518E = "NPAM1518E";

    /**
     * [@] does not exist in Master.
     */
    public static final String NPAM0076E = "NPAM0076E";

    /**
     * [@] is invalid code.
     */
    public static final String NPAM0080E = "NPAM0080E";

    /**
     * Please enter the details of the customized parts.
     */
    public static final String NPAM1579E = "NPAM1579E";

    /**
     * There are multiple [@]. Please select only one by Pop up.
     */
    public static final String NLBM1341E = "NLBM1341E";

    // START 2018/03/13 S.Katsuma [SOL#118(QC#12110),ADD]
    /**
     * You cannot save this line because parts item type of entered item is tool.
     */
    public static final String NPAM1616E = "NPAM1616E";
    // END 2018/03/13 S.Katsuma [SOL#118(QC#12110),ADD]

    /**
     * Hazmat item(s) are specified with Non-Hazmat item(s). Hazmat/Non-Hazmat must be ordered separately each other.
     */
    public static final String NPAM1619E = "NPAM1619E";

    // START 2018/12/05 M.Naito [QC#25900,ADD]
    /**
     * Tool item(s) are specified with Non-Tool item(s). Tool/Non-Tool must be ordered separately each other.
     */
    public static final String NPAM1638E = "NPAM1638E";
    // END 2018/12/05 M.Naito [QC#25900,ADD]

    /**
     * This function cannot be used for a cancelled record.
     */
    public static final String NPAM1649E = "NPAM1649E";

    /**
     * This item is no longer available. Please submit again to proceed.
     */
    public static final String NPAM1650W = "NPAM1650W";

    // START 2023/03/17 T.Kuroda [QC#61204, ADD]
    /**
     * NPAM1239E: Select only one detail to process.
     */
    public static final String NPAM1239E = "NPAM1239E";

    /**
     * ZYEM0004E:The Upload File is empty or only has a header line,
     * therefore it could not be processed. Please confirm the content
     * of the Upload file.
     */
    public static final String ZYEM0004E = "ZYEM0004E";

    /**
     * NMAM0052E:The format of [@] is incorrect.
     */
    public static final String NMAM0052E = "NMAM0052E";

    /**
     * NMAM8191E: Error has occurred while uploading. Please verify
     * the upload file format. (@)
     */
    public static final String NMAM8191E = "NMAM8191E";

    /**
     * NPAM1199E: The number for this process exceeds the maximum numbers for display and cannot proceed.
     */
    public static final String NPAM1199E = "NPAM1199E";
    // END   2023/03/17 T.Kuroda [QC#61204, ADD]

    // START 2023/08/03 T.Kuroda [QC#61648, ADD]
    /**
     * Ordering Parts doesn't exist in inventory. Please select the other Req Service Level if Premium Rush Order needs.
     */
    public static final String NPAM1660E = "NPAM1660E";
    // END   2023/08/03 T.Kuroda [QC#61648, ADD]

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param Name: Rownum
     */
    public static final String DB_PARAM_ROW_NUM = "rowNum";

    /**
     * DB Param Name: Purchase Request Number
     */
    public static final String DB_PARAM_PRCH_REQ_NUM = "prchReqNum";

    /**
     * DB Param Name: Purchase Request Record Type Code
     * PRCH_REQ_REC_TP_CD Tech Request
     */
    public static final String DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_REQUEST = "prchReqRecTpCdRequest";

    /**
     * DB Param Name: Purchase Request Record Type Code
     * PRCH_REQ_REC_TP_CD Tech Return
     */
    public static final String DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_RETURN = "prchReqRecTpCdReturn";

    /**
     * DB Param Name: Purchase Request Record Type Code
     * PRCH_REQ_REC_TP_CD Tech Return
     */
    public static final String DB_PARAM_PRCH_REQ_SRC_TP_CD_INSOURCING = "prchReqSrcTpCdInsourcing";

    /**
     * DB Param Name: Procurement Type Code PROCR_TP_CD Supplier
     */
    public static final String DB_PARAM_PROCR_TP_CD_SUPPLIER = "procrTpCdSupplier";

    /**
     * DB Param Name: Procurement Type Code Warehouse
     */
    public static final String DB_PARAM_PROCR_TP_CD_WAREHOUSE = "procrTpCdWarehouse";

    /**
     * DB Param Name: Technician TOC Code TECH_TOC_CD
     */
    public static final String DB_PARAM_TECH_TOC_CD = "techTocCd";

    /**
     * DB Param Name: SCE Entry Available Flag SCR_ENT_AVAL_FLG
     */
    public static final String DB_PARAM_SCR_ENT_AVAL_FLG = "scrEntAvalFlg";

    /**
     * DB Param Name: Location Type Code LOC_TP_CD
     */
    public static final String DB_PARAM_LOC_TP_CD = "locTpCd";

    /**
     * DB Param Name: Priority Location Flag
     */
    public static final String DB_PARAM_PRTY_LOC_FLG_Y = "prtyLocFlgY";

    /**
     * DB Param Name: Destination Warehouse Code
     */
    public static final String DB_PARAM_DEST_WH_CD = "destWhCd";

    /**
     * DB Param Name: Sales Date
     */
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    /**
     * DB Param Name: EFF_THRU_DT Defalut
     */
    public static final String DB_PARAM_EFF_THRU_DT_DEFALUT = "effThruDtDefalut";

    /**
     * DB Param Name: technician
     */
    public static final String DB_PARAM_TECHNICIAN = "technician";

    /**
     * DB Param Name: DS Condition Constant Group Id
     */
    public static final String DB_PARAM_DS_COND_CONST_GRP_ID = "dsCondConstGrpId";

    /**
     * DB Param Name: SCE_ORD_TP_CD
     */
    public static final String DB_PARAM_SCE_ORD_TP_CD = "sceOrdTpCd";

    /**
     * DB Param Name: FLAG_Y
     */
    public static final String DB_PARAM_FLAG_Y = "flagY";

    /**
     * DB Param Name: FLAG_N
     */
    public static final String DB_PARAM_FLAG_N = "flagN";

    /**
     * DB Param Name: PRCH_REQ_REC_TP_CD
     */
    public static final String DB_PARAM_PRCH_REQ_REC_TP_CD = "prchReqRecTpCd";

    // Del Start 2020/03/10 QC#56178
//    /**
//     * DB Param Name: PO_HDR_STS_CD
//     */
//    public static final String DB_PARAM_PO_HDR_STS_CD = "poHdrStsCd";
    // Del End 2020/03/10 QC#56178

    // Add Start 2020/03/10 QC#56178
    /**
     * DB Param Name: PO_STS_CD_CLOSED
     */
    public static final String DB_PARAM_PO_STS_CD_CLOSED = "poStsCdClosed";

    /**
     * DB Param Name: PO_STS_CD_CANCELED
     */
    public static final String DB_PARAM_PO_STS_CD_CANCELED = "poStsCdCanceled";
    // Add End 2020/03/10 QC#56178

    /**
     * DB Param Name: PRCH_REEQ_SRC_TP_Cd
     */
    public static final String DB_PARAM_PRCH_REEQ_SRC_TP_Cd = "prchReqSrcTpCd";
    

    /**
     * DB Param Name: VND_CD
     */
    public static final String DB_PARAM_VND_CD = "vndCd";

    /**
     * DB Param Name: MDSE_CD
     */
    public static final String DB_PARAM_MDSE_CD = "mdseCd";

    // =================================================
    // DB Columns
    // =================================================
    /**
     * DB Column Name: Tech Request Type
     */
    public static final String DB_COLUMN_PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";

    /**
     * DB Column Name: RequisitionType Name
     */
    public static final String DB_COLUMN_PRCH_REQ_TP_DESC_TXT = "PRCH_REQ_TP_DESC_TXT";

    /**
     * DB Column Name: Header Status
     */
    public static final String DB_COLUMN_PRCH_REQ_STS_DESC_TXT = "PRCH_REQ_STS_DESC_TXT";

    /**
     * DB Column Name: Approval Status
     */
    public static final String DB_COLUMN_PRCH_REQ_APVL_STS_DESC_TXT = "PRCH_REQ_APVL_STS_DESC_TXT";

    /**
     * DB Column Name: Document Source Type
     */
    public static final String DB_COLUMN_PRCH_REQ_SRC_TP_DESC_TXT = "PRCH_REQ_SRC_TP_DESC_TXT";

    /**
     * DB Column Name: Requester
     */
    public static final String DB_COLUMN_FULL_PSN_NM = "FULL_PSN_NM";

    /**
     * DB Column Name: Service Request#
     */
    public static final String DB_COLUMN_FSR_NUM = "FSR_NUM";

    /**
     * DB Column Name: Service Request Task#
     */
    public static final String DB_COLUMN_SVC_TASK_NUM = "SVC_TASK_NUM";

    /**
     * DB Column Name: Service Request Serial#
     */
    public static final String DB_COLUMN_SVC_MACH_SER_NUM = "SVC_MACH_SER_NUM";

    /**
     * DB Column Name: Attention To
     */
    public static final String DB_COLUMN_CTAC_PSN_NM = "CTAC_PSN_NM";

    /**
     * DB Column Name: Technician Code
     */
    public static final String DB_COLUMN_RQST_TECH_TOC_CD = "RQST_TECH_TOC_CD";

    /**
     * DB Column Name: Technician Name
     */
    public static final String DB_COLUMN_TECH_NM = "TECH_NM";

    /**
     * DB Column Name: Ship To Customer (Code)
     */
    public static final String DB_COLUMN_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /**
     * DB Column Name: ReqService Level
     */
    public static final String DB_COLUMN_SHPG_SVC_LVL_DESC_TXT = "SHPG_SVC_LVL_DESC_TXT";

    /**
     * DB Column Name: Requested Carrier
     */
    public static final String DB_COLUMN_CARR_NM = "CARR_NM";

    /**
     * DB Column Name: Line#
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /**
     * DB Column Name: Line#
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /**
     * DB Column Name: Item Number
     */
    public static final String DB_COLUMN_MDSE_CD = "MDSE_CD";

    /**
     * DB Column Name: Item Description
     */
    public static final String DB_COLUMN_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /**
     * DB Column Name: Req Qty
     */
    public static final String DB_COLUMN_PRCH_REQ_QTY = "PRCH_REQ_QTY";

    /**
     * DB Column Name: Source Type
     */
    public static final String DB_COLUMN_PROCR_TP_CD = "PROCR_TP_CD";

    /**
     * DB Column Name: Source Type Name
     */
    public static final String DB_COLUMN_PROCR_TP_DESC_TXT = "PROCR_TP_DESC_TXT";

    /**
     * DB Column Name: PR Type
     */
    public static final String DB_COLUMN_PRCH_REQ_REC_TP_CD = "PRCH_REQ_REC_TP_CD";

    /**
     * DB Column Name: SRC_RTL_WH_CD
     */
    public static final String DB_COLUMN_SRC_RTL_WH_CD = "SRC_RTL_WH_CD";

    /**
     * DB Column Name: SRC_RTL_SWH_CD
     */
    public static final String DB_COLUMN_SRC_RTL_SWH_CD = "SRC_RTL_SWH_CD";

    /**
     * DB Column Name: PRNT_VND_CD
     */
    public static final String DB_COLUMN_PRNT_VND_CD = "PRNT_VND_CD";

    /**
     * DB Column Name: PRNT_VND_NM
     */
    public static final String DB_COLUMN_PRNT_VND_NM = "PRNT_VND_NM";

    /**
     * DB Column Name: DEST_RTL_WH_CD
     */
    public static final String DB_COLUMN_DEST_RTL_WH_CD = "DEST_RTL_WH_CD";

    /**
     * DB Column Name: DEST_RTL_SWH_CD
     */
    public static final String DB_COLUMN_DEST_RTL_SWH_CD = "DEST_RTL_SWH_CD";

    /**
     * DB Column Name: RTL_WH_NM
     */
    public static final String DB_COLUMN_RTL_WH_NM = "RTL_WH_NM";

    /**
     * DB Column Name: RTL_SWH_CD
     */
    public static final String DB_COLUMN_RTL_SWH_CD = "RTL_SWH_CD";

    /**
     * DB Column Name: RTL_WH_NM
     */
    public static final String DB_COLUMN_RTL_WH_NM_1 = "RTL_WH_NM_1";

    /**
     * DB Column Name: RTL_WH_NM
     */
    public static final String DB_COLUMN_RTL_WH_NM_2 = "RTL_WH_NM_2";

    /**
     * DB Column Name: Line Status
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_STS_DESC_TXT = "PRCH_REQ_LINE_STS_DESC_TXT";

    /**
     * DB Column Name: Order Qty
     */
    public static final String DB_COLUMN_ORD_QTY = "ORD_QTY";

    /**
     * DB Column Name: SO Status
     */
    public static final String DB_COLUMN_DS_SO_LINE_STS_DESC_TXT = "DS_SO_LINE_STS_DESC_TXT";

    /**
     * DB Column Name: RWS Status
     */
    public static final String DB_COLUMN_RWS_STS_DESC_TXT = "RWS_STS_DESC_TXT";

    /**
     * DB Column Name: Fulfilled Qty
     */
    public static final String DB_COLUMN_SHIP_QTY = "SHIP_QTY";

    /**
     * DB Column Name: Received Qty
     */
    public static final String DB_COLUMN_RWS_PUT_AWAY_QTY = "RWS_PUT_AWAY_QTY";

    /**
     * DB Column Name: Cancel Qty
     */
    public static final String DB_COLUMN_PRCH_REQ_CANC_QTY = "PRCH_REQ_CANC_QTY";

    /**
     * DB Column Name: PO#
     */
    public static final String DB_COLUMN_PO_ORD_NUM = "PO_ORD_NUM";

    /**
     * DB Column Name: PR#
     */
    public static final String DB_COLUMN_PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /**
     * DB Column Name: SO#
     */
    public static final String DB_COLUMN_SO_NUM = "SO_NUM";

    // Add Start 2020/03/12 QC#56104
    /**
     * DB Column Name: Alt SO#
     */
    public static final String DB_COLUMN_ALT_SO_NUM = "ALT_SO_NUM";
    // Add End 2020/03/12 QC#56104

    /**
     * DB Column Name: RWS#
     */
    public static final String DB_COLUMN_RWS_NUM = "RWS_NUM";

    // START 2021/06/18 J.Evangelista [QC#58876,ADD]
    /**
     * DB Column Name: Transaction Reference Number
     */
    public static final String DB_COLUMN_TRX_REF_NUM = "TRX_REF_NUM";
    // END 2021/06/18 J.Evangelista [QC#58876,ADD]

    /**
     * DB Column Name: Insourced Qty
     */
    public static final String DB_COLUMN_PRCH_REQ_INSRC_QTY = "PRCH_REQ_INSRC_QTY";

    /**
     * DB Column Name: Insourced Flag
     */
    public static final String DB_COLUMN_INSRC_FLG = "INSRC_FLG";

    /**
     * DB Column Name: PO Flag
     */
    public static final String DB_COLUMN_PO_CRAT_FLG = "PO_CRAT_FLG";

    /**
     * DB Column Name: Freeze Flag
     */
    public static final String DB_COLUMN_PRCH_REQ_FRZ_FLG = "PRCH_REQ_FRZ_FLG";

    /**
     * DB Column Name: Line Comment
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_CMNT_TXT = "PRCH_REQ_LINE_CMNT_TXT";

    /**
     * DB Column Name: Actual Service Level
     */
    public static final String DB_COLUMN_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /**
     * DB Column Name: Tracking Number
     */
    public static final String DB_COLUMN_PRO_NUM = "PRO_NUM";

    /**
     * DB Column Name: PRCH_REQ_LINE_TP_CD
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_TP_CD = "PRCH_REQ_LINE_TP_CD";

    /**
     * DB Column Name: PRCH_REQ_LINE_TP_DESC_TXT
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_TP_DESC_TXT = "PRCH_REQ_LINE_TP_DESC_TXT";

    /**
     * DB Column Name: PRCH_REQ_CRAT_DT
     */
    public static final String DB_COLUMN_PRCH_REQ_CRAT_DT = "PRCH_REQ_CRAT_DT";

    /**
     * DB Column Name: PRCH_REQ_CRAT_TM
     */
    public static final String DB_COLUMN_PRCH_REQ_CRAT_TM = "PRCH_REQ_CRAT_TM";

    /**
     * DB Column Name: RQST_RCV_DT
     */
    public static final String DB_COLUMN_RQST_RCV_DT = "RQST_RCV_DT";

    /**
     * DB Column Name: RQST_RCV_TM
     */
    public static final String DB_COLUMN_RQST_RCV_TM = "RQST_RCV_TM";

    /**
     * DB Column Name: DS_ACCT_NM
     */
    public static final String DB_COLUMN_DS_ACCT_NM = "DS_ACCT_NM";

    /**
     * DB Column Name: DEST_INVTY_LOC_CD
     */
    public static final String DB_COLUMN_DEST_INVTY_LOC_CD = "DEST_INVTY_LOC_CD";

    /**
     * DB Column Name: SHIP_TO_LOC_NM
     */
    public static final String DB_COLUMN_SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    /**
     * DB Column Name: SHIP_TO_ADDL_LOC_NM
     */
    public static final String DB_COLUMN_SHIP_TO_ADDL_LOC_NM = "SHIP_TO_ADDL_LOC_NM";

    /**
     * DB Column Name: LOC_NM
     */
    public static final String DB_COLUMN_LOC_NM = "LOC_NM";

    /**
     * DB Column Name: SHIP_TO_FIRST_LINE_ADDR
     */
    public static final String DB_COLUMN_SHIP_TO_FIRST_LINE_ADDR = "SHIP_TO_FIRST_LINE_ADDR";

    /**
     * DB Column Name: SHIP_TO_SCD_LINE_ADDR
     */
    public static final String DB_COLUMN_SHIP_TO_SCD_LINE_ADDR = "SHIP_TO_SCD_LINE_ADDR";

    /**
     * DB Column Name: SHIP_TO_THIRD_LINE_ADDR
     */
    public static final String DB_COLUMN_SHIP_TO_THIRD_LINE_ADDR = "SHIP_TO_THIRD_LINE_ADDR";

    /**
     * DB Column Name: SHIP_TO_FRTH_LINE_ADDR
     */
    public static final String DB_COLUMN_SHIP_TO_FRTH_LINE_ADDR = "SHIP_TO_FRTH_LINE_ADDR";

    /**
     * DB Column Name: SHIP_TO_POST_CD
     */
    public static final String DB_COLUMN_SHIP_TO_POST_CD = "SHIP_TO_POST_CD";

    /**
     * DB Column Name: SHIP_TO_CTY_ADDR
     */
    public static final String DB_COLUMN_SHIP_TO_CTY_ADDR = "SHIP_TO_CTY_ADDR";

    /**
     * DB Column Name: SHIP_TO_CNTY_NM
     */
    public static final String DB_COLUMN_SHIP_TO_CNTY_NM = "SHIP_TO_CNTY_NM";

    /**
     * DB Column Name: SHIP_TO_ST_CD
     */
    public static final String DB_COLUMN_SHIP_TO_ST_CD = "SHIP_TO_ST_CD";

    /**
     * DB Column Name: SHIP_TO_PROV_NM
     */
    public static final String DB_COLUMN_SHIP_TO_PROV_NM = "SHIP_TO_PROV_NM";

    /**
     * DB Column Name: SHIP_TO_CTRY_CD
     */
    public static final String DB_COLUMN_SHIP_TO_CTRY_CD = "SHIP_TO_CTRY_CD";

    /**
     * DB Column Name: PRCH_REQ_SAVED_FLG
     */
    public static final String DB_COLUMN_PRCH_REQ_SAVED_FLG = "PRCH_REQ_SAVED_FLG";

    /**
     * DB Column Name: PRCH_REQ_STS_CD
     */
    public static final String DB_COLUMN_PRCH_REQ_STS_CD = "PRCH_REQ_STS_CD";

    /**
     * DB Column Name: PRCH_REQ_LINE_STS_CD
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_STS_CD = "PRCH_REQ_LINE_STS_CD";

    /**
     * DB Column Name: PRCH_REQ_APVL_DT
     */
    public static final String DB_COLUMN_PRCH_REQ_APVL_DT = "PRCH_REQ_APVL_DT";

    /**
     * DB Column Name: PRCH_REQ_CRAT_BY_PSN_CD
     */
    public static final String DB_COLUMN_PRCH_REQ_CRAT_BY_PSN_CD = "PRCH_REQ_CRAT_BY_PSN_CD";

    /**
     * DB Column Name: POST_CD
     */
    public static final String DB_COLUMN_POST_CD = "POST_CD";

    /**
     * DB Column Name: CTRY_CD
     */
    public static final String DB_COLUMN_CTRY_CD = "CTRY_CD";

    /**
     * DB Param Name: Parent Prch Req Line Sub Num
     */
    public static final String DB_PARAM_PARENT_LINE_SUB_NUM = "lineSubNumParent";

    /**
     * Parent PRCH_REQ_LINE_SUB_NUM
     */
    public static final int PARENT_LINE_SUB_NUM = 0;

    /**
     * DB Column Name: Sum Fulfilled Qty
     */
    public static final String DB_COLUMN_SUM_SHIP_QTY = "SUM_SHIP_QTY";

    /**
     * DB Column Name: Sum Received Qty
     */
    public static final String DB_COLUMN_SUM_RWS_PUT_AWAY_QTY = "SUM_RWS_PUT_AWAY_QTY";

    /**
     * DB Column Name: DS_COND_CONST_CD
     */
    public static final String DB_COLUMN_DS_COND_CONST_CD = "DS_COND_CONST_CD";

    /**
     * DB Column Name: DS_COND_CONST_VAL_TXT_02
     */
    public static final String DB_COLUMN_DS_COND_CONST_VAL_TXT_02 = "DS_COND_CONST_VAL_TXT_02";

    /**
     * DB Column Name: DS_COND_CONST_VAL_TXT_03
     */
    public static final String DB_COLUMN_DS_COND_CONST_VAL_TXT_03 = "DS_COND_CONST_VAL_TXT_03";

    /**
     * DB Column Name: DS_COND_CONST_VAL_TXT_06
     */
    public static final String DB_COLUMN_DS_COND_CONST_VAL_TXT_06 = "DS_COND_CONST_VAL_TXT_06";

    /**
     * DB Column Name: DS_COND_CONST_VAL_TXT_09
     */
    public static final String DB_COLUMN_DS_COND_CONST_VAL_TXT_09 = "DS_COND_CONST_VAL_TXT_09";

    // START 2018/02/26 S.Katsuma [QC#24312,ADD]
    /**
     * DB Column Name: PRCH_REQ_BAL_QTY
     */
    public static final String DB_COLUMN_PRCH_REQ_BAL_QTY = "PRCH_REQ_BAL_QTY";

    /**
     * DB Column Name: XX_REC_HIST_CRAT_TS_H1
     */
    public static final String DB_COLUMN_XX_REC_HIST_CRAT_TS_H1 = "XX_REC_HIST_CRAT_TS_H1";

    /**
     * DB Column Name: XX_REC_HIST_CRAT_BY_NM_H1
     */
    public static final String DB_COLUMN_XX_REC_HIST_CRAT_BY_NM_H1 = "XX_REC_HIST_CRAT_BY_NM_H1";

    /**
     * DB Column Name: XX_REC_HIST_UPD_TS_H1
     */
    public static final String DB_COLUMN_XX_REC_HIST_UPD_TS_H1 = "XX_REC_HIST_UPD_TS_H1";

    /**
     * DB Column Name: XX_REC_HIST_UPD_BY_NM_H1
     */
    public static final String DB_COLUMN_XX_REC_HIST_UPD_BY_NM_H1 = "XX_REC_HIST_UPD_BY_NM_H1";

    /**
     * DB Column Name: XX_REC_HIST_TBL_NM_H1
     */
    public static final String DB_COLUMN_XX_REC_HIST_TBL_NM_H1 = "XX_REC_HIST_TBL_NM_H1";

    /**
     * DB Column Name: XX_REC_HIST_CRAT_TS_D1
     */
    public static final String DB_COLUMN_XX_REC_HIST_CRAT_TS_D1 = "XX_REC_HIST_CRAT_TS_D1";

    /**
     * DB Column Name: XX_REC_HIST_CRAT_BY_NM_D1
     */
    public static final String DB_COLUMN_XX_REC_HIST_CRAT_BY_NM_D1 = "XX_REC_HIST_CRAT_BY_NM_D1";

    /**
     * DB Column Name: XX_REC_HIST_UPD_TS_D1
     */
    public static final String DB_COLUMN_XX_REC_HIST_UPD_TS_D1 = "XX_REC_HIST_UPD_TS_D1";

    /**
     * DB Column Name: XX_REC_HIST_UPD_BY_NM_D1
     */
    public static final String DB_COLUMN_XX_REC_HIST_UPD_BY_NM_D1 = "XX_REC_HIST_UPD_BY_NM_D1";

    /**
     * DB Column Name: XX_REC_HIST_TBL_NM_D1
     */
    public static final String DB_COLUMN_XX_REC_HIST_TBL_NM_D1 = "XX_REC_HIST_TBL_NM_D1";

    /**
     * Selected line cannon be freezed because balance quantity is zero.
     */
    public static final String NPAM1615E = "NPAM1615E";
    // START 2018/02/26 S.Katsuma [QC#24312,ADD]

    /**
     * VAR_CONST: CREATE_MATERIAL_PARTS
     */
    public static final String VAR_CONST_CREATE_MATERIAL_PARTS = "CREATE_MATERIAL_PARTS";

    /**
     * You can't close this request since open Shipping Order exists.
     */
    public static final String NPAM1594E = "NPAM1594E";

    /**
     * You can't close this request since open Receiving Order exists.
     */
    public static final String NPAM1595E = "NPAM1595E";

    /**
     * You can't close this request since open PO Requisition exists.
     */
    public static final String NPAM1596E = "NPAM1596E";

    /**
     * You can't close this request since open PO exists.
     */
    public static final String NPAM1597E = "NPAM1597E";

    /** QC#19966 Add.
     * Tech Return from R to NEW can not be done.
     */
    public static final String NPZM0301E = "NPZM0301E";

    // START 2017/08/31 K.Kameoka [Sol#369(QC#19243),ADD]
    /** . */
    public static final String ASTERISK = "*";
    // END 2017/08/31 K.Kameoka [Sol#369(QC#19243),ADD]

    // START 2017/10/25 S.Katsuma QC#21986 ADD
    /** . */
    public static final String TECH_SWH_CD_G = "G";

    /** . */
    public static final String TECH_SWH_CD_R = "R";

    /**
     * DB Param Name: Tech SWH Cd
     */
    public static final String DB_PARAM_TECH_SWH_CD = "techSwhCd";

    /**
     * DB Param Name: Purchase Request Line Type Code(Local Return)
     * PRCH_REQ_LINE_TP_CD Tech Request
     */
    public static final String DB_PARAM_PRCH_REQ_LINE_TP_CD_LOCAL_RETURN = "localReturnCd";

    /**
     * DB Param Name: Purchase Request Line Type Code(Defective Return)
     * PRCH_REQ_LINE_TP_CD Tech Request
     */
    public static final String DB_PARAM_PRCH_REQ_LINE_TP_CD_DEFECTIVE_RETURN = "defectiveReturnCd";

    /**
     * DB Param Name: Purchase Request Line Type Code(Used Local Return)
     * PRCH_REQ_LINE_TP_CD Tech Request
     */
    public static final String DB_PARAM_PRCH_REQ_LINE_TP_CD_USED_LOCAL_RETURN = "usedLocalReturnCd";

    /**
     * DB Param Name: Tech SWH CD
     * DB_PARAM_DEST_SWH_CD
     */
    public static final String DB_PARAM_DEST_SWH_CD = "destSwhCd";

    /**
     * DB Param Name: Request Tech Toc CD
     */
    public static final String DB_PARAM_RQST_TECH_TOC_CD = "rqstTechTocCd";

    /**
     * DB Column Name: RTRN_REQ_PRT_FLG
     */
    public static final String DB_COLUMN_RTRN_REQ_PRT_FLG = "RTRN_REQ_PRT_FLG";

    /**
     * DB Column Name: RTRN_DSPL_TP_CD
     */
    public static final String DB_COLUMN_RTRN_DSPL_TP_CD = "RTRN_DSPL_TP_CD";

    /**
     * DB Column Name: RSW_DEF_SRC_RTL_WH_CD
     */
    public static final String DB_COLUMN_RSW_DEF_SRC_RTL_WH_CD = "RSW_DEF_SRC_RTL_WH_CD";

    /**
     * DB Column Name: RSW_DEF_SRC_PRNT_VND_NM
     */
    public static final String DB_COLUMN_RSW_DEF_SRC_PRNT_VND_NM = "RSW_DEF_SRC_PRNT_VND_NM";

    /**
     * DB Column Name: RSW_DEF_SRC_RTL_SWH_CD
     */
    public static final String DB_COLUMN_RSW_DEF_SRC_RTL_SWH_CD = "RSW_DEF_SRC_RTL_SWH_CD";

    /**
     * DB Column Name: RSW_DEF_RTRN_TO_RTL_WH_CD
     */
    public static final String DB_COLUMN_RSW_DEF_RTRN_TO_RTL_WH_CD = "RSW_DEF_RTRN_TO_RTL_WH_CD";

    /**
     * DB Column Name: RSW_DEF_RTRN_PRNT_VND_NM
     */
    public static final String DB_COLUMN_RSW_DEF_RTRN_PRNT_VND_NM = "RSW_DEF_RTRN_PRNT_VND_NM";

    /**
     * DB Column Name: RSW_DEF_RTRN_TO_RTL_SWH_CD
     */
    public static final String DB_COLUMN_RSW_DEF_RTRN_TO_RTL_SWH_CD = "RSW_DEF_RTRN_TO_RTL_SWH_CD";

    /**
     * DB Column Name: RSW_DEF_SRC_PROCR_TP_CD
     */
    public static final String DB_COLUMN_RSW_DEF_SRC_PROCR_TP_CD = "RSW_DEF_SRC_PROCR_TP_CD";

    /**
     * DB Column Name: RSW_DEF_RTRN_TO_PROCR_TP_CD
     */
    public static final String DB_COLUMN_RSW_DEF_RTRN_TO_PROCR_TP_CD = "RSW_DEF_RTRN_TO_PROCR_TP_CD";

    /**
     * DB Column Name: RTRN_WH_CD
     */
    public static final String DB_COLUMN_RTRN_WH_CD = "RTRN_WH_CD";

    /**
     * DB Column Name: PRCH_AVAL_FLG
     */
    public static final String DB_COLUMN_PRCH_AVAL_FLG = "PRCH_AVAL_FLG";

    /**
     * Item Number [@] is not Parts Return Control Item.
     */
    public static final String NPAM1605E = "NPAM1605E";

    /**
     * Tech SWH and Line Type combination does not match.
     */
    public static final String NPAM1606E = "NPAM1606E";
    // END 2017/10/25 S.Katsuma QC#21986 ADD

    /**
     * DB Column Name: PR# Add QC#21908.
     */
    public static final String DB_COLUMN_SRC_PRCH_REQ_NUM = "SRC_PRCH_REQ_NUM";

    /**
     * QC#21909
     * This Service Level is no allow for ESS Tech
     */
    public static final String NPAM1614E = "NPAM1614E";

    /**
     * QC#24918 
     * There is no ASL setup for the item number. 
     * */
    public static final String NPZM0272E = "NPZM0272E";

    /**
     * QC#24918 
     * [@] is mandatory value.@
     * */
    public static final String NPAM1329E = "NPAM1329E";
    
    /**
     * QC#26581
     * You can't cancel this request since open Shipping Order exists.
     **/
    public static final String NPAM1622E = "NPAM1622E";

    /**
     * QC#26581
     * You can't cancel this request since open Receiving Order exists.
     **/
    public static final String NPAM1623E = "NPAM1623E";

    /**
     * QC#26581
     * You can't cancel this request since open PO Requisition exists.
     **/
    public static final String NPAM1624E = "NPAM1624E";

    /**
     * QC#26581
     * You can't cancel this request since open PO exists.
     **/
    public static final String NPAM1625E = "NPAM1625E";

    /**
     * QC#26581
     * [@] is closed. Cannot [@] process.
     **/
    public static final String NPAM1628E = "NPAM1628E";

    // 2019/02/14 QC#30358 Add Start
    public static final String RUN_MODE_NEW = "1";

    /** . */
    public static final String RUN_MODE_COPIED = "2";

    /** . */
    public static final String RUN_MODE_SAVED = "3";

    /** . */
    public static final String RUN_MODE_SUBMITTED = "4";

    /** . */
    public static final String RUN_MODE_CLOSED = "5";
    // 2019/02/14 QC#30358 Add End

    // START 2021/06/18 J.Evangelista [QC#58876,ADD]
    /** VAR_CHAR_CONST: SHIP_DTL_WH_OWNER_CD */
    public static final String VAR_CHAR_CONST_SHIP_DTL_WH_OWNER_CD = "NPAL1080_SHIP_DTL_WH_OWNER_CD";

    /** Default value for VAR_CHAR_CONST: SHIP_DTL_WH_OWNER_CD */
    public static final String SHIP_DTL_WH_OWNER_CD_DEFAULT = "MNX";

    /** Screen Item: Ship Detail */
    public static final String SCR_ITEM_SHIP_DETAIL = "Ship Detail";
    // END 2021/06/18 J.Evangelista [QC#58876,ADD]

    // START 2023/10/20 G.Quan [QC#61494, ADD]
    /** Shipping Cut off Start time */
    public static final String SHIPPING_CUT_OFF_START_TIME = "SHIPPING_CUT_OFF_START_TIME";

    /** Shipping Cut off End time */
    public static final String SHIPPING_CUT_OFF_END_TIME = "SHIPPING_CUT_OFF_END_TIME";
    // END 2023/10/20 G.Quan [QC#61494, ADD]
}
