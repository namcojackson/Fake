/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2580.constant;

/**
 *<pre>
 * NMAL2580Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/08   Fujitsu         R.Nakamura      Create          N/A
 * 2016/07/28   Fujitsu         R.Nakamura      Update          QC#11871
 * 2016/11/28   Fujitsu         M.Ohno          Update          QC#15623
 *</pre>
 */
public class NMAL2580Constant {

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL2580Scrn00";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /**
     * The chronological sequence between the dates in the "@" is
     * wrong.
     */
    public static final String NMAM0806E = "NMAM0806E";

    /** Fetch Size */
    public static final int FETCH_SIZE = 1000;

    // Del Start 2016/07/28 QC#11871
//    /** Max Row Num */
//    public static final int MAX_ROW_NUM = 2001;
    // Del End 2016/07/28 QC#11871

    /** CSV File Name Download */
    public static final String CSV_FILE_NAME_DOWNLOAD = "xxxDownload";

// Mod Start 2016/11/28 M.Ohno S21_NA#15623
//    /** CSV Header For Download : NMAL2460 */
//    public static final String[] CSV_DOWNLOAD_HEADER_NMAL2460 = new String[] {//
//    //
//            "Global Company Code" //
//            , "Account Territory Resource Request Header Primary Key" //
//            , "Direct Sales Account Number" //
//            , "Direct Sales Account Name" //
//            , "Location Number" //
//            , "Direct Sales Account Type Code" //
//            , "First Line Address" //
//            , "Second Line Address" //
//            , "Third Line Address" //
//            , "Fourth Line Address" //
//            , "City address" //
//            , "State Code" //
//            , "Postal Code" //
//            , "Before Account Territory Organization Name 01" //
//            , "Before Account Territory Organization Name 02" //
//            , "Before Account Territory Organization Name 03" //
//            , "Before Account Territory Organization Name 04" //
//            , "Before Account Territory Organization Name 05" //
//            , "Before Account Territory Organization Name 06" //
//            , "Before Account Territory Organization Name 07" //
//            , "Before Account Territory Organization Name 08" //
//            , "Before Account Territory Organization Name 09" //
//            , "Before Account Territory Organization Name 10" //
//            , "Before Account Territory Organization Name 11" //
//            , "Before Account Territory Organization Name 12" //
//            , "Before Account Territory Organization Name 13" //
//            , "Before Account Territory Organization Name 14" //
//            , "Before Account Territory Organization Name 15" //
//            , "Before Account Territory Organization Name 16" //
//            , "Before Account Territory Organization Name 17" //
//            , "Before Account Territory Organization Name 18" //
//            , "Before Account Territory Organization Name 19" //
//            , "Before Account Territory Organization Name 20" //
//            , "After Account Territory Organization Name 01" //
//            , "After Account Territory Organization Name 02" //
//            , "After Account Territory Organization Name 03" //
//            , "After Account Territory Organization Name 04" //
//            , "After Account Territory Organization Name 05" //
//            , "After Account Territory Organization Name 06" //
//            , "After Account Territory Organization Name 07" //
//            , "After Account Territory Organization Name 08" //
//            , "After Account Territory Organization Name 09" //
//            , "After Account Territory Organization Name 10" //
//            , "After Account Territory Organization Name 11" //
//            , "After Account Territory Organization Name 12" //
//            , "After Account Territory Organization Name 13" //
//            , "After Account Territory Organization Name 14" //
//            , "After Account Territory Organization Name 15" //
//            , "After Account Territory Organization Name 16" //
//            , "After Account Territory Organization Name 17" //
//            , "After Account Territory Organization Name 18" //
//            , "After Account Territory Organization Name 19" //
//            , "After Account Territory Organization Name 20" //
//            , "Manual Entry Flag" //
//            , "Request Detail Result Comment Text" //
//    };

    /** CSV Header For Download : NMAL2460 */
    public static final String[] CSV_DOWNLOAD_HEADER_NMAL2460 = new String[] {//
    //
            "<*Account Number>(<Alphabet Numeric Character>(20))" //
            , "<Account Name>(<Alphabet Numeric Character>(360))" //
            , "<Account Category>(<Alphabet Numeric Character>(50))" //
            , "<*Location ID>(<Alphabet Numeric Character>(30))" //
            , "<Address>(<Alphabet Numeric Character>(240))" //
            , "<City>(<Alphabet Numeric Character>(25))" //
            , "<State>(<Alphabet Numeric Character>(2))" //
            , "<Postal Code>(<Alphabet Numeric Character>(15))" //
            , "<Before ATTR1>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR1>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR2>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR2>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR3>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR3>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR4>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR4>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR5>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR5>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR6>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR6>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR7>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR7>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR8>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR8>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR9>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR9>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR10>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR10>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR11>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR11>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR12>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR12>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR13>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR13>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR14>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR14>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR15>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR15>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR16>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR16>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR17>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR17>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR18>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR18>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR19>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR19>(<Alphabet Numeric Character>(50))" //
            , "<Before ATTR20>(<Alphabet Numeric Character>(50))" //
            , "<After ATTR20>(<Alphabet Numeric Character>(50))" //
            , "<Manual Entry(Y or N)>(<Alphabet Numeric Character>(1))" //
            , "<Mass Update Reason>(<Alphabet Numeric Character>(4000))" //
            , "<UPLD_CSV_RQST_CMNT_TXT>(<Alphabet Numeric Character>(4000))" // 

    };

//    /** CSV Header For Download : NMAL2620 */
//    public static final String[] CSV_DOWNLOAD_HEADER_NMAL2620 = new String[] {//
//    //
//            "Global Company Code" //
//            , "Territory Update Request Detail Primary Key" //
//            , "Territory Update Request Header Primary Key" //
//            , "Territory Organization Name" //
//            , "Current Person Number" //
//            , "Move Person Number" //
//            , "Move Effective From Date" //
//            , "Move Effective Through Date" //
//            , "Territory End Date" //
//            , "Territory Update Mode Type Code" //
//            , "Request Detail Result Comment Text" //
//    };

    /** CSV Header For Download : NMAL2620 */
    public static final String[] CSV_DOWNLOAD_HEADER_NMAL2620 = new String[] {//
    //
            "<*Current Organization Name>(<Alphabet Numeric Character>(50))" //
            , "<*Current Resource #>(<Alphabet Numeric Character>(50))" //
            , "<*Move Resource #>(<Alphabet Numeric Character>(50))" //
            , "<*Move Effective From(MM/DD/YYYY)>(<Alphabet Numeric Character>(10))" //
            , "<Move Effective Thru(MM/DD/YYYY)>(<Alphabet Numeric Character>(10))" //
            , "<Mass Update Reason>(<Alphabet Numeric Character>(4000))" //
            , "<UPLD_CSV_RQST_CMNT_TXT>(<Alphabet Numeric Character>(4000))" // 
    };

//    /** CSV Header For Download : NMAL2710 */
//    public static final String[] CSV_DOWNLOAD_HEADER_NMAL2710 = new String[] {//
//    //
//            "Global Company Code" //
//            , "Postal Update Request Detail Primary Key" //
//            , "Postal Update Request Header Primary Key" //
//            , "Territory Rule Operand Type Description Text" //
//            , "Territory Rule From Value Text" //
//            , "Territory Rule Through Value Text" //
//            , "Old Organization Name" //
//            , "New Organization Name" //
//            , "Move Effective From Date Text" //
//            , "Move Effective Through Date Text" //
//            , "Mass Update Reason Comment Text" //
//            , "Request Detail Result Comment Text" //
//    };

    /** CSV Header For Download : NMAL2710 */
    public static final String[] CSV_DOWNLOAD_HEADER_NMAL2710 = new String[] {//
    //
            "<*Operand>(<Alphabet Numeric Character>(50))" //
            , "<*Value From>(<Alphabet Numeric Character>(360))" //
            , "<Value To>(<Alphabet Numeric Character>(360))" //
            , "<*Old Territory Name>(<Alphabet Numeric Character>(50))" //
            , "<*New Territory Name>(<Alphabet Numeric Character>(50))" //
            , "<*Move Effective From(MM/DD/YYYY)>(<Alphabet Numeric Character>(10))" //
            , "<*Move Effective Thru(MM/DD/YYYY)>(<Alphabet Numeric Character>(10))" //
            , "<Mass update reason>(<Alphabet Numeric Character>(4000))" //
            , "<UPLD_CSV_RQST_CMNT_TXT>(<Alphabet Numeric Character>(4000))" // 
    };

//    /** CSV Header For Download : NMAL2720 */
//    public static final String[] CSV_DOWNLOAD_HEADER_NMAL2720 = new String[] {//
//    //
//            "Global Company Code" //
//            , "Move Organization Request Detail Primary Key" //
//            , "Move Organization Request Header Primary Key" //
//            , "Current Organization Name" //
//            , "Current Person Number" //
//            , "Move Person Number" //
//            , "Move Effective From Date Text" //
//            , "Move Effective Through Date Text" //
//            , "Mass Update Reason Comment Text" //
//            , "Request Detail Result Comment Text" //
//    };

    /** CSV Header For Download : NMAL2720 */
    public static final String[] CSV_DOWNLOAD_HEADER_NMAL2720 = new String[] {//
    //
            "<*Current Territory Name>(<Alphabet Numeric Character>(50))" //
            , "<*Current Resource #>(<Alphabet Numeric Character>(50))" //
            , "<*Move Resource #>(<Alphabet Numeric Character>(50))" //
            , "<*Move Effective From(MM/DD/YYYY)>(<Alphabet Numeric Character>(10))" //
            , "<Move Effective Thru(MM/DD/YYYY)>(<Alphabet Numeric Character>(10))" //
            , "<Mass Update Reason>(<Alphabet Numeric Character>(4000))" //
            , "<UPLD_CSV_RQST_CMNT_TXT>(<Alphabet Numeric Character>(4000))" // 

    };
// Mod End   2016/11/28 M.Ohno S21_NA#15623

    /** DEFALT_FROM_DATE */
    public static final String DEFALT_FROM_DATE = "19000101";

    /** DEFALT_TO_DATE */
    public static final String DEFALT_TO_DATE = "99991231";

    /** DEFALT_TO_DATE */
    public static final String TIMESTAMP_FROM = "000000000";

    /** DEFALT_TO_DATE */
    public static final String TIMESTAMP_TO = "235959999";

    /** Set Value Request Date */
    public static final String VALUE_NAME_REQUEST_DATE = "Request Date";

    /** Function ID : NMAL2460 */
    public static final String FUNCTION_ID_NMAL2460 = "NMAL2460";

    /** Function ID : NMAL2620 */
    public static final String FUNCTION_ID_NMAL2620 = "NMAL2620";

    /** Function ID : NMAL2710 */
    public static final String FUNCTION_ID_NMAL2710 = "NMAL2710";

    /** Function ID : NMAL2720 */
    public static final String FUNCTION_ID_NMAL2720 = "NMAL2720";

    /** File Name : NMAL2460 */
    public static final String FILE_NAME_NMAL2460 = "Account Territory Resource Request Detail List";

    /** File Name : NMAL2620 */
    public static final String FILE_NAME_NMAL2620 = "Territory Update Request Detail List";

    /** File Name : NMAL2710 */
    public static final String FILE_NAME_NMAL2710 = "Postal Update Request Detail List";

    /** File Name : NMAL2710 */
    public static final String FILE_NAME_NMAL2720 = "Move Organization Request Detail List";

    /** File Suffix */
    public static final String FILE_SFX = ".csv";
}
