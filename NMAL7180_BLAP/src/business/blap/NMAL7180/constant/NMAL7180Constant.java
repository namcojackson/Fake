/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7180.constant;

/**
 *<pre>
 * NMAL7180Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Fujitsu         W.Honda         Create          N/A
 * 2016/09/02   Fujitsu         R.Nakamura      Update          QC#8282
 * 2017/08/21   Fujitsu         M.Yamada        Update          QC#18785(L3)
 * 2020/02/19   Fujitsu         C.Hara          Update          QC#5520
 *</pre>
 */
public class NMAL7180Constant {


    /** High Value Date : 99991231 */
    public static final String HIGH_VAL_DT = "99991231";

    /**  Date format length */
    public static final int YYYYMMDD_LENGTH = 8;

    //--------------------------------
    // Message ID
    //--------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** File */
    public static final String CSV = ".csv";

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NMAL7180 Price Group Search";

    // Mod Start 2016/09/02 QC#8282
    /** CSV_HDR_SCHD */
    public static final String[] CSV_HDR = new String[] {
        "RadioButton"
        , "Price Group Name"
        , "Group Description"
        , "Pricing Group Type"
        , "Active"
        // 2020/02/19 QC#55203 Del Start
        //, "Target Context"
        //, "Attribute Name"
        //, "Operator"
        //, "Target From"
        //, "Target To"
        // 2020/02/19 QC#55203 Del End
        , "Date From"
        , "Date To"
        , "Transaction Type" // QC#18785
        //, "Precedence#" // QC#18785 // 2020/02/19 QC#55203 Del
        };
    // Mod End 2016/09/02 QC#8282

    /** Fetch size */
    public static final int FETCH_SIZE = 1000;

    /** File size */
    public static final int CSV_LIMIT_COUNT = 65000;
}
