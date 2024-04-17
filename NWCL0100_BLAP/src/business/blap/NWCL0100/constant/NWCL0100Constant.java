/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0100.constant;

/**
 *<pre>
 * NWCL0100Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Fujitsu         S.Ohki          Create          N/A
 * 2016/09/06   Hitachi         Y.Takeno        Update          QC#13315
 * 2016/09/07   Hitachi         Y.Takeno        Update          QC#13337
 * 2016/09/08   Hitachi         Y.Takeno        Update          QC#13339
 * 2016/09/15   Hitachi         Y.Takeno        Update          QC#13339-1
 * 2016/09/15   Hitachi         Y.Takeno        Update          QC#13341-1
 * 2017/11/14   Fujitsu         Mz.Takahashi    Update          Sol#265(QC#18788) 
 * 2019/09/12   Fujitsu         R.Matsuki       Update          QC#52432-1
 *</pre>
 */
public class NWCL0100Constant {

    /** Business Application ID */
    public static final String BIZ_ID = "NWCL0100";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NWCL0100Scrn00";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NWCM0003I = "NWCM0003I";

// 2016/09/15 QC#13339-1 Add Start
    /** Negative number is not allowed. */
    public static final String NWCM0151E = "NWCM0151E";
// 2016/09/15 QC#13339-1 Add End

// 2016/09/15 QC#13341-1 Add Start
    /** The entered Bill To Account does not have an active Easy Pac I contract. */
    public static final String NWCM0152E = "NWCM0152E";
// 2016/09/15 QC#13341-1 Add End

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] field Requires numeric input only. */
    public static final String ZZM9004E = "ZZM9004E";

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Data delete failure.(ReturnCode = [@]) */
    public static final String ZZZM9014E = "ZZZM9014E";

    /** Data insert failure.(ReturnCode = [@]) */
    public static final String ZZZM9012E = "ZZZM9012E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** Data update failure.(ReturnCode = [@]) */
    public static final String ZZZM9013E = "ZZZM9013E";

    /** {@} Please enter a future date than @}. */
    public static final String NWCM0124E = "NWCM0124E";

    /** The selected line is during the effective period. */
    public static final String NWCM0125E = "NWCM0125E";

    /**
     * By the input period will not overlap with the period of the
     * other records.
     */
    public static final String NWCM0126E = "NWCM0126E";

    /** No Details are selected. */
    public static final String NWCM0127E = "NWCM0127E";

    /** There are already registered data in this name. */
    public static final String NWCM0128E = "NWCM0128E";

    /**
     * Update the Contract information. Is this OK? If this is OK,
     * click Button again.
     */
    public static final String NWCM0130W = "NWCM0130W";

    /** Cannot add anymore details. */
    public static final String NWCM0134E = "NWCM0134E";

    // 2017/11/14 Sol#265(QC#18788) Add Start
    /** [@] is invalid value.*/
    public static final String ZZBM0085E = "ZZBM0085E";
    // 2017/11/14 Sol#265(QC#18788) Add End

    /** Display Control Code (Past). */
    public static final String DISP_CTRL_CD_PAST = "1";

    /** Display Control Code (Current). */
    public static final String DISP_CTRL_CD_CURRENT = "2";

    /** Display Control Code (future). */
    public static final String DISP_CTRL_CD_FUTURE = "3";

    /** Display Control Code (New). */
    public static final String DISP_CTRL_CD_NEW = "4";

    /** Display Control Code (From is Sales date). */
    public static final String DISP_CTRL_CD_SALESDATE = "5";

    /** CSV Fetch Size */
    public static final int FETCH_SIZE = 1000;

    /** CSV Extension */
    public static final String CSV_EXT = ".csv";

    /** Link Name List Bill To Account Code. */
    public static final String LINK_NAME_LIST_BILL_TO_ACCT_CD = "A";

    /** Link Name Bill To Account. */
    public static final String LINK_NAME_BILL_TO_ACCT = "Bill To Account";

    /** Link Name Account Group. */
    public static final String LINK_NAME_LIST_ACCT_GRP = "C";

    /** Error Message Text Valid From. */
    public static final String TEXT_VALID_FROM = "Valid From";

    /** Error Message Text Valid To. */
    public static final String TEXT_VALID_TO = "Valid To";

    /** Error Message Text Sales Date. */
    public static final String TEXT_SALES_DATE = "Sales Date";

 // 2019/09/12 QC#52432-1 Add START
    /** Error Message Text Sales Date. */
    public static final String TEXT_LAST_MONTH_END = "Last Month End";
 // 2019/09/12 QC#52432-1 Add End

    /** Error Message Bill To Acct Nm. */
    public static final String TEXT_BILL_TO = "Bill To Acct Cd";

    /** Error Message Text Rate. */
    public static final String TEXT_RATE = "Rate";

    /** Error Message Text Monthly Quota. */
    public static final String TEXT_MONTH_QUOTA = "Monthly Quota";

    /** Date Format (yyyyMMdd) */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /** Date Format (MM/dd/yyyy) */
    public static final String DATE_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";

// 2016/09/07 QC#13337 Add Start
    /** Max Date Value : 99991231 */
    public static final String MAX_DT_VAL = "99991231";
// 2016/09/07 QC#13337 Add End
}
