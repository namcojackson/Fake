package business.blap.NPAL1260.constant;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request Inquiry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/02/2015   CITS       Yasushi Nomura        Create          N/A
 * 12/26/2017   CITS       K.Ogino               Update          QC#21908,QC#21887
 * 2023/10/25   Hitachi    G.Quan                Update          QC#61494
 *</pre>
 */
public class NPAL1260Constant {
    /**
     * Business Application ID
     */
    public static final String BUSINESS_APPLICATION_ID = "NPAL1260";

    /**
     * DB Like search char
     */
    public static final String LIKE_SEARCH_CHAR = "%";

    // CSV D/L
    /** max download count */
    public static final int MAX_DOWNLOAD_CNT = 65000;

    /** fetch size */
    public static final int FETCH_SIZE = 3000;

    /** CSV File Name  */
    public static final String CSV_FILE_NAME = "NPAL1260_TechPartsRequestInquiry";

    /** */
    public static final String EXTN_CSV = ".csv";

    /** CSV file header Information Mod QC#21908,QC#21887*/
    public static final String[] CSV_HEADER_INFO = new String[] {//
    //
            "Tech Request #" //
            , "Line #" //
            , "Tech Request Type" //
            , "Line Type" //
            , "Item Number" //
            , "Item Description" //
            , "Req Qty" //
            , "Source Type" //
            , "SourceWH" //
            , "SourceWH Name" //
            , "SourceSWH" //
            , "SourceSWH Name" //
            , "DestinationWH" //
            , "DestinationWH Name" //
            , "DestinationSWH" //
            , "DestinationSWH Name" //
            , "Line Status" //
            , "Order Qty" //
            , "Fulfilled Qty" //
            , "Received Qty" //
            , "PO B/O Qty" //
            , "FreezeFlag" //
            , "InsourcedFlag" //
            , "PurchasedFlag" //
            , "CancelledFlag" //
            , "Ref#" //
            , "Date Created" //
            , "Date & Time Needed" //
            , "Ship Date" //
            , "Date Received" //
            , "Ack Date B/O" //
            , "Tech Territory" //
            , "Document Source Type" //
            , "Service Request#" //
            , "Service Request Task#" //
            , "Service Request Serial#" //
            , "Purchase Order#" //
            , "Shipping Order#" //
            // START 2023/10/25 G.Quan [QC#61494, ADD]
            , "Alternate Shipping Order#" //
            // END 2023/10/25 G.Quan [QC#61494, ADD]
            , "Requisition#" //
            , "ETA" //
            // START 2023/10/25 G.Quan [QC#61494, ADD]
            , "Delivery Date" //
            // END 2023/10/25 G.Quan [QC#61494, ADD]
            , "Supplier" //
            , "Supplier Name" //
            , "Site" //
            , "Site Name" //
            , "Ship To Customer" //
            , "Ship To Customer Name" //
            , "Technician" //
            , "Technician Name" //
            , "Requested Carrier" //
            , "Actual Service Level" //
            , "Tracking Number" //
            , "Line Type Code" //
    };

    // =================================================
    // Message Code
    // =================================================
    /**
     * Search results exceeded [@]. Only showing first @ records.
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
     * NMAM0014E: [@] is not selected.
     */
    public static final String NMAM0014E = "NMAM0014E";

    /**
     * The data specified does not exist.
     */
    public static final String NPAM0089E = "NPAM0089E";

    /**
     * ZZM9003I: The process [@] has been successfully completed.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** <pre>Too many search results.  Please narrow your search criteria and retry.</pre> */
    public static final String NZZM0007E = "NZZM0007E";

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * Date time format string : yyyyMMddHHmmss
     */
    public static final String TIME_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * Date time format string : yyyyMMddHHmmssSSS
     */
    public static final String TIME_FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /**
     * Date time format string : MM/dd/yyyy HH:mm
     */
//    public static final String TIME_FORMAT_MM_DD_YYYY_HH_MM = "MM/dd/yyyy HH:mm";

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
     * serch option VND_PO_ACK_STS_CD
     */
    public static final String VND_PO_ACK_STS_CD_IB = "IB";

    /**
     * Parent PRCH_REQ_LINE_SUB_NUM
     */
    public static final int PARENT_LINE_SUB_NUM = 0;

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

    /**
     * VAR_CHAR_CONST:POD_UPD_STS_CD
     */
    public static final String VAR_CHAR_POD_UPD_STS_CD = "POD_UPD_STS_CD";

    /**
     * VAR_CHAR_CONST:POD_ARV_STS_CD
     */
    public static final String VAR_CHAR_POD_ARV_STS_CD = "POD_ARV_STS_CD";

    // START 2023/10/25 G.Quan [QC#61494, ADD]
    /**
     * VAR_CHAR_CONST:NPAB1650_NPAL1260_TARGET_POD
     */
    public static final String NPAB1650_NPAL1260_TARGET_POD = "NPAB1650_NPAL1260_TARGET_POD";

    /** PREMIUM_RUSH_CONDITION_TPL_CARR_NM */
    public static final String PREMIUM_RUSH_CONDITION_TPL_CARR_NM = "MNX%";

    /** RUSH_CONDITION_TPL_CARR_NM_FEDEX */
    public static final String RUSH_CONDITION_TPL_CARR_NM_FEDEX = "FEDEX";

    /** RUSH_CONDITION_TPL_CARR_NM_UPS */
    public static final String RUSH_CONDITION_TPL_CARR_NM_UPS = "UPS";
    // END 2023/10/25 G.Quan [QC#61494, ADD]
}
