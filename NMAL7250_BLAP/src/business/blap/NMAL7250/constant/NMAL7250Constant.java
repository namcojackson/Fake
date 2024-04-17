/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7250.constant;

/**
 *<pre>
 * NMAL7250Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7250Constant {

    /** Business Application ID */
    public static final String BIZ_ID = "NMAL7250";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NMAL7250Scrn00";

    /** High Value Date : 99991231 */
    public static final String HIGH_VAL_DT = "99991231";

    /** Status : Active */
    public static final String STS_ACTIVE = "ACTIVE";

    /** Status : Inactive */
    public static final String STS_INACTIVE = "INACTIVE";

    /** Status : Delete */
    public static final String STS_DELETE = "DELETE";

    /** Status : Delete */
    public static final String STS_NONE = "NONE";

    /** Status Code : Active */
    public static final String STS_ACTIVE_ONLY_CODE = "01";

    /** Status Code : Inactive */
    public static final String STS_INACTIVE_ONLY_CODE = "02";

    /** Status Code : Delete */
    public static final String STS_DELETE_ONLY_CODE = "03";

    /** Status Code : Active & Inactive */
    public static final String STS_ACTIVE_INACTIVE_CODE = "04";

    /** Status Code : All */
    public static final String STS_ALL_CODE = "05";

    /** Flag Display String : YES */
    public static final String FLG_YES = "YES";

    /** Flag Display String : NO */
    public static final String FLG_NO = "NO";

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

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Max Fetch Size */
    public static final String CSV = ".csv";

    /** Table Item Name for prcRuleCondFromTxt */
    public static final String PRC_RULE_COND_FROM_TXT = "PRC_RULE_COND_FROM_TXT_";

    /** Table Item Name for prcRuleCondThruTxt */
    public static final String PRC_RULE_COND_THRU_TXT = "PRC_RULE_COND_THRU_TXT_";

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NMAL7250 Adjustment Search";

    /** CSV_HDR_SCHD */
    public static final String[] CSV_HDR = new String[] {
        "CheckBox"
        , "ID"
        , "Rule Or Table Name"
        , "Price Adjustment Type"
        , "Line of Business"
        , "Price Adjustment Category"
        , "Apply Automatically"
        , "Override"
        , "Status"
        , "Default Rule Precedence"
        , "Effective Date From"
        , "Effective Date To"};

}
