/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7230.constant;

/**
 *<pre>
 * NMAL7230Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7230Constant {


    /** Business Application ID */
    public static final String BIZ_ID = "NMAL7230";

    /**  Date format length */
    public static final int YYYYMMDD_LENGTH = 8;

    /**  yyyyMMdd */
    public static final String YYYYMMDD_PATTERN = "yyyyMMdd";

    /**  12319999 */
    public static final String HIGH_DATE = "99991231";

    /**  Upload error code : reading error */
    public static final int UPLOAD_READ_ERROR = 1000;

    /**  Upload error code : format error */
    public static final int UPLOAD_FORMAT_ERROR = 2000;

    /**  Result number : Normal End */
    public static final int RESULT_NUM_NORMAL = 1;

    /**  Result number : Abnormal End */
    public static final int RESULT_NUM_ABNORMAL = -1;

    /**  Result number : No Update */
    public static final int RESULT_NUM_NOUPDATE = 0;

    //--------------------------------
    // Message ID
    //--------------------------------

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** [@] is not selected.*/
    public static final String NMAM0014E = "NMAM0014E";

    /** @  is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /**  The entered [@] does not exist in [@].  */
    public static final String NMAM0163E = "NMAM0163E";

    /**  It failed to register [@].  */
    public static final String NMAM0176E = "NMAM0176E";

    /**  It failed to update [@].  */
    public static final String NMAM0177E = "NMAM0177E";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NMAM0182E = "NMAM0182E";

    /**  The specified format is incorrect. It must be @. */
    public static final String NMAM8075E = "NMAM8075E";

    /**  When @ is entered, @ must be entered. */
    public static final String NMAM8178E = "NMAM8178E";

    /**  @ cannot be added because it is exceeding the maximum number of row [@]  */
    public static final String NMAM8187E = "NMAM8187E";

    /** Error has occurred while uploading. Please verify the upload file format. (@) */
    public static final String NMAM8191E = "NMAM8191E";

    /**  When @ is entered, @ cannot entered. */
    public static final String NMAM8409E = "NMAM8409E";

    /**  Please enter at least one of '@'. */
    public static final String NMAM8410E = "NMAM8410E";

    /**  Error records exist in the upload file.  */
    public static final String NMAM8425E = "NMAM8425E";

    /** Could not get the "@" */
    public static final String NMAM8433E = "NMAM8433E";

    /**  No change has been made.  */
    public static final String NMAM8446W = "NMAM8446W";

    /** Freight Zone */
    public static final String THIS_FREIGHT_ZONE = "This Freight Zone";

    /** Screen Item Name : Country */
    public static final String COUNTRY = "Country";

    /** Screen Item Name : State */
    public static final String STATE = "State";

    /** Screen Item Name : Postal Code From */
    public static final String POSTAL_FROM = "Portal Code From";

    /** Screen Item Name : Postal Code To */
    public static final String POSTAL_TO = "Portal Code To";

    /** Screen Item Name : LOB */
    public static final String LINE_OF_BUSINESS = "LOB";

    /** Screen Item Name : Zone */
    public static final String ZONE = "Zone";

    /** Check Target List For Customer */
    public static final String CUSTOMER = "Customer";

    /** Check Target List For Customer Group*/
    public static final String CUSTOMER_GROUP = "Customer Group";

    /** Check Target List For Country & State*/
    public static final String COUNTRY_STATE = "Country & State";

    /** Check Target List For Postal Code From/To */
    public static final String POSTALCODE = "Postal Code From/To";

    // 2019/03/13 QC#30715 add start
    /** Effective Date From */
    public static final String EFFECTIVE_DATE_FROM = "Effective Date From";
    // 2019/03/13 QC#30715 add End

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Message Kind warning */
    public static final String MESSAGE_KIND_WARNING = "W";

    /** PostalCode Pattern  */
    public static final String POSTALCODE_PATTERN = "99999 or 99999-9999";

    /** Five PortalCode Pattern  */
    public static final String FIVE_POSTALCODE_PATTERN = "^[0-9]{5}$";

    /** Ten PortalCode Pattern  */
    public static final String TEN_POSTALCODE_PATTERN = "^[0-9]{5}[-][0-9]{4}$";

    /** PortalCode Zero Pack  */
    public static final String PORTALCODE_ZERO_PACK = "0000";

    /** Freight Zone */
    public static final String FREIGHT_ZONE = "Freight Zone";

    /** Search Option Name */
    public static final String SEARCH_OPTION_NAME = "Search Option Name";

    /** Save Search */
    public static final String SAVE_SEARCH = "Save Search";

    /** Delete Search */
    public static final String DELETE_SEARCH = "Delete Search";

    /** Save */
    public static final String SAVE = "Save";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** File extension */
    public static final String CSV_EXTENSION = ".csv";

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NMAL7230F00";

    // 2019/03/13 QC#30715 add start
    /** Date Format Padding Zero */
    public static final String DATE_FORMAT_PADDING_ZERO = "0";
    // 2019/03/13 QC#30715 add End

    /** CSV_HDR_SCHD */
    public static final String[] CSV_HDR = new String[] {
        "Line Of Business"
        , "Customer"
        , "Customer Group"
        , "Zone"
        , "State"
        , "Country"
        , "Postal From"
        , "Postal To"
        , "Effective Date From"
        , "Effective Date To"};
}
