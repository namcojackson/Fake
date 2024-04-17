/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7000.constant;

/**
 *<pre>
 * NMAL7000Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/16   Fujitsu         M.Nakamura      Create          N/A
 * 2018/07/11   Fujitsu         M.Ishii         Update          QC#25557
 *</pre>
 */
public class NMAL7000Constant {

    /** Business Application ID */
    public static final String BIZ_ID = "NMAL7000";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NMAL7000Scrn00";

    /** High Value Date : 99991231 */
    public static final String HIGH_VAL_DT = "99991231";

    /** Status : Active */
    public static final String STS_ACTIVE = "Active";

    /** Status : Inactive */
    public static final String STS_INACTIVE = "Inactive";

    /** Status : Delete */
    public static final String STS_DELETE = "Delete";

    /** Status : None */
    public static final String STS_NONE = "None";
    
    // 2018/07/11 QC#25557 Add Start
    /** convert format : 0.regex, 1.replacement */
    public static final String[] DT_CONV_FORMAT = {"^(....)(..)(..)$", "$2/$3/$1" };
    // 2018/07/11 QC#25557 Add End
    
    //--------------------------------
    // Message ID
    //--------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** [@] is not selected. */
    public static final String NMAM0014E = "NMAM0014E";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NMAM0182E = "NMAM0182E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** ZZZM9003I The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** CSV file name */
    public static final String CSV_FILE_NM = "NMAL7000PriceList";

    /** CSV file extension */
    public static final String CSV_FILE_EXTENSION = ".csv";

    /** CSV header upload */
    public static final String[] CSV_HEADER = new String[] {
        "Price List ID"
        , "Price List Name"
        , "Price List Type"
        , "Price List Structure"
        , "Status"
        , "Contract#"
        , "Contract Name"
        , "Price List Group Name"
        , "Effective Date From"
        , "Effective Date To"
    };
}
