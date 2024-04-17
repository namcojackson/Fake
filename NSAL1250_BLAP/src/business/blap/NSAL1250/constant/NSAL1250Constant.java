/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1250.constant;

/**
 *<pre>
 * Ds Contract Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/07   Hitachi         Y.Tsuchimoto    Create          N/A
 * 03/30/2016   Hitachi         T.Aoyagi        Update          QC#1467
 * 03/23/2018   Hitachi         U.Kim           Update          QC#18884(Sol337)
 *</pre>
 */
public class NSAL1250Constant {

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * Successfully approved the selected claim(s).
     */
    // START 03/30/2016 T.Aoyagi [QC#1467, MOD]
    public static final String NSAM0435I = "NSAM0435I";
    // END 03/30/2016 T.Aoyagi [QC#1467, MOD]

    /**
     * Failed to approve the following claim(s). Process Definition
     * Name:@ Document Id:@
     */
    public static final String NSAM0436E = "NSAM0436E";

    /**
     * You do not have the required authorization to perform this
     * action.
     */
    public static final String NSAM0437E = "NSAM0437E";

    /**
     * Failed to search the following claim(s). Process Definition
     * Name:@ Document Id:@
     */
    public static final String NSAM0439E = "NSAM0439E";

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * Param1 String Part1
     */
    public static final String PARAM1_STR_PART1 = "High Overage Billing for Contract Number : ";

    /**
     * Param1 String Part2
     */
    public static final String PARAM1_STR_PART2 = ", Machine : ";

    /**
     * Param2 String Part1
     */
    public static final String PARAM2_STR_PART1 = "Serial [";

    /**
     * Param2 String Part2
     */
    public static final String PARAM2_STR_PART2 = "] has been flagged because it either goes above $";

    /**
     * Param2 String Part3
     */
    public static final String PARAM2_STR_PART3 = " tolerance or current overage is above/below the average billing variance.";
    
    // START 2018/03/23 U.Kim [QC#18884(Sol337), ADD]
    /**
     * ParamComp String Part1
     */
    public static final String PARAMCOMP_STR_PART1 = "Negative Read for Contract Number : ";

    /**
     * ParamComp String Part1
     */
    public static final String PARAMCOMP_STR_PART2 = "] has been flagged because Start Meter Read is bigger than End Meter Read.";

    // END 2018/03/23 U.Kim [QC#18884(Sol337), ADD]

    /**
     * Detail1 Column name : Counter Type
     */
    public static final String DETAIL1_COLUMN_NAME_COUNTER = "MTR_LB_DESC_TXT";

    /**
     * Detail1 Column name : Billing Period Start Date
     */
    public static final String DETAIL1_COLUMN_NAME_START_DATE = "SVC_CONTR_BLLG_FROM_DT";

    /**
     * Detail1 Column name : Billing Period End Date
     */
    public static final String DETAIL1_COLUMN_NAME_END_DATE = "SVC_CONTR_BLLG_THRU_DT";

    /**
     * Detail1 Column name : Average Billing(12 months)
     */
    public static final String DETAIL1_COLUMN_NAME_AV = "MTR_CHRG_DEAL_AMT_AV";

    /**
     * Detail1 Column name : Current Overage Billing
     */
    public static final String DETAIL1_COLUMN_NAME_AA = "MTR_CHRG_DEAL_AMT_AA";

    /**
     * Detail2 Column name : Action Date
     */
    public static final String DETAIL2_COLUMN_NAME_ACTION_DATE = "actionDate";

    /**
     * Detail2 Column name : Action
     */
    public static final String DETAIL2_COLUMN_NAME_ACTION = "action";

    /**
     * Detail2 Column name : From
     */
    public static final String DETAIL2_COLUMN_NAME_FROM = "from";

    /**
     * Detail2 Column name : To
     */
    public static final String DETAIL2_COLUMN_NAME_TO = "to";

    /**
     * Detail2 Column name : Detail
     */
    public static final String DETAIL2_COLUMN_NAME_DETAIL = "detail";

    /**
     * DATE_FORMAT : YYYYMMDD
     */
    public static final String DATE_FORMAT = "YYYYMMDD";

    /**
     * Table name : Table A
     */
    public static final String TABLE_DETAIL1 = "A";

    /**
     * Table name : TableB
     */
    public static final String TABLE_DETAIL2 = "B";

    /** Screen name NSAL0150 */
    public static final String SCREEN_NAME_NSAL0150 = "Meter Entry";

    /** Process def name : NSWP0002 */
    public static final String  PROCESS_DEF_NSWP0002 = "NSWP0002";

    // START 2018/03/23 U.Kim [QC#18884(Sol337)]
    /** Process def name : NSWP0005 */
    public static final String  PROCESS_DEF_NSWP0005 = "NSWP0005";
    // END 2018/03/23 U.Kim [QC#18884(Sol337)]

    /**
     * Document ID : Non Fleet
     */
    public static final String DOCUMENT_ID_NON_FLEET = "M";

    /**
     * Document ID : Fleet
     */
    public static final String DOCUMENT_ID_FLEET = "C";
    // add start 2016/10/05 CSA Defect#10729
    /**
     * Document ID : Aggregate
     */
    public static final String DOCUMENT_ID_AGG = "A";
    // add end 2016/10/05 CSA Defect#10729

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Time Stamp Length */
    public static final int TS_LENGTH = 19;

    /** Divide Round up point digits */
    public static final int DIVIDE_ROUND_UP_POINT_DIGITS = 5;
}
