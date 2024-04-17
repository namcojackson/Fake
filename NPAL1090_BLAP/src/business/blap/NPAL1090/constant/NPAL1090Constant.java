package business.blap.NPAL1090.constant;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request List
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/09/2015   CITS       Yasushi Nomura        Create          N/A
 *</pre>
 */
public class NPAL1090Constant {
    /**
     * Business Application ID
     */
    public static final String BUSINESS_APPLICATION_ID = "NPAL1090";

    /**
     * space string
     */
    public static final String SP_CHAR = " ";

    // CSV D/L
    /** max download count */
    public static final int MAX_DOWNLOAD_CNT = 65000;

    /** fetch size */
    public static final int FETCH_SIZE = 3000;

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
     * AM
     */
    public static final String TIME_AM = "AM";

    /**
     * PM
     */
    public static final String TIME_PM = "PM";

    /** CSV File Name  */
    public static final String CSV_FILE_NAME = "NPAL1090_TechRequestList";

    /** */
    public static final String EXTN_CSV = ".csv";

    /** CSV file header Information */
    public static final String[] CSV_HEADER_INFO = new String[] {//
    //
            "Tech Request #" //
            , "Tech Request Type" //
            , "Header Status" //
            , "Approval Status" //
            , "Date Created" //
            , "Date & Time Needed" //
            , "Document Source Type" //
            , "Source Doc#" //
            , "Create By" //
            , "Create By Name" //
            , "Technician Division" //
            , "Req Service Level" //
            , "Requested Carrier" //
            , "Technician" //
            , "Technician Name" //
            , "Tech WH" //
            , "Tech WH Name" //
            , "Tech SWH" //
            , "Tech SWH Name" //
            , "Ship To Customer" //
            , "Ship To Customer Name" //
            , "Service Request#" //
            , "Service Task#" //
            , "Service Request Serial#" //
    };

    /**
     * Date time format string : yyyyMMddHHmmss
     */
    public static final String TIME_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * Date time format string : yyyyMMddHHmmssSSS
     */
    public static final String TIME_FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /**
     * Date time format string : MM/dd/yyyy
     */
    public static final String TIME_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param Name: Search Option Application Id
     */
    public static final String DB_PARAM_SRCH_OPT_APL_ID = "srchOptAplId";

    /**
     * DB Param Name: Search Option User Id
     */
    public static final String DB_PARAM_SRCH_OPT_USR_ID = "srchOptUsrId";

    /**
     * DB Param Name: RequisitionType PRCH_REQ_REC_TP_CD
     */
    public static final String DB_PARAM_PRCH_REQ_REC_TP_CD = "prchReqRecTpCd";

    /**
     * DB Param Name: RequisitionType PRCH_REQ_REC_TP_CD Tech Request
     */
    public static final String DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_REQUEST = "prchReqRecTpCdRequest";

    /**
     * DB Param Name: RequisitionType PRCH_REQ_REC_TP_CD Tech Return
     */
    public static final String DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_RETURN = "prchReqRecTpCdReturn";

    /**
     * DB Param Name: RequisitionType TECH_NM
     */
    public static final String DB_PARAM_LIKE_TECH_NM = "likeTechNm";

    /**
     * DB Param Name: RequisitionType RTL_WH_CD
     */
    public static final String DB_PARAM_LIKE_RTL_WH_CD = "likeRtlWhCd";

    /**
     * DB Param Name: RequisitionType RTL_SWH_CD
     */
    public static final String DB_PARAM_LIKE_RTL_SWH_CD = "likeRtlSwhCd";

    /**
     * DB Param Name: RequisitionType LOC_NM
     */
    public static final String DB_PARAM_LIKE_LOC_NM = "likeLocNm";

    /**
     * DB Param Name: RequisitionType CARR_NM
     */
    public static final String DB_PARAM_LIKE_CARR_NM = "likeCarrNm";

    /**
     * DB Param Name: RequisitionType TECH_TOC_CD
     */
    public static final String DB_PARAM_TECH_TOC_CD = "techTocCd";

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
     * DB Column Name: RequisitionType Code
     */
    public static final String DB_COLUMN_PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";

    /**
     * DB Column Name: RequisitionType Name
     */
    public static final String DB_COLUMN_PRCH_REQ_TP_DESC_TXT = "PRCH_REQ_TP_DESC_TXT";

    /**
     * DB Column Name: Header Status Code
     */
    public static final String DB_COLUMN_PRCH_REQ_STS_CD = "PRCH_REQ_STS_CD";

    /**
     * DB Column Name: Header Status Name
     */
    public static final String DB_COLUMN_PRCH_REQ_STS_DESC_TXT = "PRCH_REQ_STS_DESC_TXT";

    /**
     * DB Column Name: Approval Status Code
     */
    public static final String DB_COLUMN_PRCH_REQ_APVL_STS_CD = "PRCH_REQ_APVL_STS_CD";

    /**
     * DB Column Name: Approval Status Name
     */
    public static final String DB_COLUMN_PRCH_REQ_APVL_STS_DESC_TXT = "PRCH_REQ_APVL_STS_DESC_TXT";

    /**
     * DB Column Name: Document Source Type Code
     */
    public static final String DB_COLUMN_PRCH_REQ_SRC_TP_CD = "PRCH_REQ_SRC_TP_CD";

    /**
     * DB Column Name: Document Source Type Name
     */
    public static final String DB_COLUMN_PRCH_REQ_SRC_TP_DESC_TXT = "PRCH_REQ_SRC_TP_DESC_TXT";

    /**
     * DB Column Name: Req Service Level Code
     */
    public static final String DB_COLUMN_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /**
     * DB Column Name: Req Service Level Name
     */
    public static final String DB_COLUMN_SHPG_SVC_LVL_DESC_TXT = "SHPG_SVC_LVL_DESC_TXT";

    /**
     * DB Column Name: Technician Master Code
     */
    public static final String DB_COLUMN_TECH_TOC_CD = "TECH_TOC_CD";

    /**
     * DB Column Name: Technician Master Name
     */
    public static final String DB_COLUMN_TECH_NM = "TECH_NM";

    /**
     * DB Column Name: Warehouse Master Code
     */
    public static final String DB_COLUMN_RTL_WH_CD = "RTL_WH_CD";

    /**
     * DB Column Name: Warehouse Master Name
     */
    public static final String DB_COLUMN_RTL_WH_NM = "RTL_WH_NM";

    /**
     * DB Column Name: Sub Warehouse Master Code
     */
    public static final String DB_COLUMN_RTL_SWH_CD = "RTL_SWH_CD";

    /**
     * DB Column Name: Sub Warehouse Master Name
     */
    public static final String DB_COLUMN_RTL_SWH_NM = "RTL_SWH_NM";

    /**
     * DB Column Name: ShipToCustomer Master Code
     */
    public static final String DB_COLUMN_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /**
     * DB Column Name: ShipToCustomer Master Name
     */
    public static final String DB_COLUMN_LOC_NM = "LOC_NM";

    /**
     * DB Column Name: Carrier Master Code
     */
    public static final String DB_COLUMN_CARR_CD = "CARR_CD";

    /**
     * DB Column Name: Carrier Master Name
     */
    public static final String DB_COLUMN_CARR_NM = "CARR_NM";

    /**
     * DB Column Name: POST_CD
     */
    public static final String DB_COLUMN_POST_CD = "POST_CD";

    /**
     * DB Column Name: CTRY_CD
     */
    public static final String DB_COLUMN_CTRY_CD = "CTRY_CD";

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

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /** <pre>Too many search results.  Please narrow your search criteria and retry.</pre> */
    public static final String NZZM0007E = "NZZM0007E";
}
