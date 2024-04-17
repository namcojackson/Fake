/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7240.constant;

/**
 *<pre>
 * NMAL7240Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   Fujitsu         W.Honda         Create          N/A
 * 2016/05/17   Fujitsu         W.Honda         Update          QC#7040
 *</pre>
 */
public class NMAL7240Constant {

    /** Business Application ID */
    public static final String BIZ_ID = "NMAL7240";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NMAL7240Scrn00";

    /** varCharConstData(NMAL7240_QTY_UNIT_TP_CD) */
    public static final String VAR_CHAR_CONST_QTY_UNIT_TP_CD = "NMAL7240_QTY_UNIT_TP_CD";

    /** varCharConstData(NMAL7240_QTY_UNIT_TP_CD) */
    public static final String VAR_CHAR_CONST_PER_UNIT_TP_CD = "NMAL7240_PER_UNIT_TP_CD";

    /**  Date format length */
    public static final int YYYYMMDD_LENGTH = 8;

    /**  12319999 */
    public static final String HIGH_DATE = "99991231";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Message Kind warning */
    public static final String MESSAGE_KIND_WARNING = "W";

    /**  Result number : Normal End */
    public static final int RESULT_NUM_NORMAL = 1;

    /**  Result number : Abnormal End */
    public static final int RESULT_NUM_ABNORMAL = -1;

    /**  Result number : No Update */
    public static final int RESULT_NUM_NOUPDATE = 0;

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** File extension */
    public static final String CSV_EXTENSION = ".csv";

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NMAL7240F00";

    /**  Upload error code : reading error */
    public static final int UPLOAD_READ_ERROR = 1000;

    /**  Upload error code : format error */
    public static final int UPLOAD_FORMAT_ERROR = 2000;

    //--------------------------------
    // Message ID
    //--------------------------------
    // QC#7040 2016/05/17 Add start
    /** The Upload File is empty or only has a header line,
     *  therefore it could not be processed.
     *  Please confirm the content of the  Upload file. */
    public static final String ZYEM0004E = "ZYEM0004E";
    // QC#7040 2016/05/17 Add end

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** @  is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /**  The entered [@] does not exist in [@].  */
    public static final String NMAM0163E = "NMAM0163E";

    /**  It failed to register [@].  */
    public static final String NMAM0176E = "NMAM0176E";

    /**  It failed to update [@].  */
    public static final String NMAM0177E = "NMAM0177E";

    /**  @ cannot be added because it is exceeding the maximum number of row [@]  */
    public static final String NMAM8187E = "NMAM8187E";

    /** Error has occurred while uploading. Please verify the upload file format. (@) */
    public static final String NMAM8191E = "NMAM8191E";

    /**  Error records exist in the upload file.  */
    public static final String NMAM8425E = "NMAM8425E";

    /** "@" doesn't exist in the VAR_CHAR_CONST. */
    public static final String NMAM8432E = "NMAM8432E";

    /** Could not get the "@" */
    public static final String NMAM8433E = "NMAM8433E";

    /**  No change has been made.  */
    public static final String NMAM8446W = "NMAM8446W";

    // QC#7040 2016/05/17 Add start
    /**  Specified "@" already exists.  */
    public static final String NMAM8513E = "NMAM8513E";
    // QC#7040 2016/05/17 Add end

    /** Freight Rate */
    public static final String FREIGHT_RATE = "Freight Rate";

    /** Freight Rate */
    public static final String THIS_FREIGHT_RATE = "This Freight Rate";

    /** Line of Business */
    public static final String LINE_OF_BUSINESS = "Line of Business";

    /** Zone */
    public static final String ZONE = "Zone";

    /** Shipping Service Level */
    public static final String SHIPPING_SERVICE_LEVEL = "Shipping Service Level";

    /** From Scale Quantity */
    public static final String FROM_SCALE_QUANTITY = "From Scale Quantity";

    /** Effective Date From */
    public static final String EFFECTIVE_DATE_FROM = "Effective Date From";

    /** Standard Cost */
    public static final String STANDARD_CURRENCY = "Standard Currency";

    // QC#7040 2016/05/17 Add start
    /** Saved Search Options */
    public static final String SAVED_SEARCH_OPTIONS = "Saved Search Options";

    /** Search Option Name */
    public static final String SEARCH_OPTION_NAME = "Search Option Name";

    /** Delete Search */
    public static final String DELETE_SEARCH = "Delete Search";

    /** Save Search */
    public static final String SAVE_SEARCH = "Save Search";
    // QC#7040 2016/05/17 Add end

    /** CSV File Name Upload */
    public static final String CSV_FILE_NAME_UPLOAD = "UploadedMeterReadDownload";

    /**  Combination of start date and End date */
    public static final String COMBI_START_END = "Combination of start date and End date";

    // 2019/03/13 QC#30715 add start
    /** Date Format Padding Zero */
    public static final String DATE_FORMAT_PADDING_ZERO = "0";
    // 2019/03/13 QC#30715 add End

    /** CSV_HDR_SCHD */
    public static final String[] CSV_HDR = new String[] {
        "Line of Business"
        , "Zone"
        , "Shipping Service Level"
        , "From Scale Quantity"
        , "UOM"
        , "Rate"
        , "Unit"
        , "Per"
        , "UOM"
        , "Effective Date From"
        , "Effective Date To"};

    /** effFromDt_A1  */
    public static final String EFF_FROM_DT_A1 = "effFromDt_A1";

    /** effThruDt_A1  */
    public static final String EFF_THRU_DT_A1 = "effThruDt_A1";

    /** Check Duplicate Item Name List */
    public static final String[] CHK_DUPLICATE_ITEM_NM_LIST = new String[] {
                "lineBizTpDescTxt_A1",
                "frtZoneNum_A1",
                "shpgSvcLvlDescTxt_A1",
                "fromSclQty_A1",
                "qtyUnitTpCd_A1",
                "effFromDt_A1",
                "effThruDt_A1"
    };

    /** Check Term Duplicate Item Name List */
    public static final String[] CHK_TERM_DUPLICATE_ITEM_NM_LIST = new String[] {
                "lineBizTpDescTxt_A1",
                "frtZoneNum_A1",
                "shpgSvcLvlDescTxt_A1",
                "fromSclQty_A1",
                "qtyUnitTpCd_A1"
    };

}
