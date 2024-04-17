/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0020.constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *<pre>
 * NWWL0020Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   Fujitsu         S.Ohki          Create          N/A
 * 2016/10/06   Fujitsu         Mz.Takahashi    Update          QC#14431
 *</pre>
 */
public class NWWL0020Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWWL0020";

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

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Target data does not exist. */
    public static final String NWWM0003E = "NWWM0003E";

    /** [@] it has not been entered. */
    public static final String NWWM0004E = "NWWM0004E";

    /** For [@], please enter [@] or a later date. */
    public static final String NWWM0005E = "NWWM0005E";

    /** Please enter a date later than today. */
    public static final String NWWM0006E = "NWWM0006E";

    /** For [@], please enter [@] or a later time. */
    public static final String NWWM0007E = "NWWM0007E";

    /** In the case of Weekly, please be sure to select the Run Days. */
    public static final String NWWM0008E = "NWWM0008E";

    /** SQL syntax error. [@] */
    public static final String NWWM0009E = "NWWM0009E";

    /** Input ban to SQL character [@] has been entered. */
    public static final String NWWM0010E = "NWWM0010E";

    /** Email address is not valid. */
    public static final String NWWM0011E = "NWWM0011E";

    /** There are items that are not entered in the column list. */
    public static final String NWWM0012E = "NWWM0012E";

    /** No search results found. */
    public static final String NWWM0014E = "NWWM0014E";

    /** '[@] doesn't exist in [@]. */
    public static final String NWWM0015E = "NWWM0015E";

    /**
     * [@] has been changed. Is this OK? If this is OK, click Button
     * again.
     */
    public static final String NWWM0016W = "NWWM0016W";

    /**
     * Additional Action, please be performed for each Submit 1
     * review.
     */
    public static final String NWWM0017E = "NWWM0017E";

    /**
     * In the case of the Monthly, enter the [Run Calendar], please
     * select the [End of Month].
     */
    public static final String NWWM0019E = "NWWM0019E";

    /** Failed to update the record.[@] */
    public static final String NWWM0020E = "NWWM0020E";

    /** Failed to create the record.[@] */
    public static final String NWWM0021E = "NWWM0021E";

    /** Failed to delete the record.[@] */
    public static final String NWWM0022E = "NWWM0022E";

    /** Header information is not registered. */
    public static final String NWWM0023E = "NWWM0023E";

    /** SQL Statement ignored. */
    public static final String NWWM0024E = "NWWM0024E";

    /** It is a normal SQL. */
    public static final String NWWM0025I = "NWWM0025I";

    /** [@] Could not be obtained. */
    public static final String NWWM0032E = "NWWM0032E";

    /** [@] has not been changed. */
    public static final String NWWM0033E = "NWWM0033E";

    /** Please enter a value of [@] separated by commas. */
    public static final String NWWM0034E = "NWWM0034E";

    /** Please enter in the format of [@]. */
    public static final String NWWM0035E = "NWWM0035E";

    /** [@] has been changed. Is this OK? If this is OK, click Button again. */
    public static final String NWWM0036I = "NWWM0036I";

    /** Table Name NTFY_BIZ_AREA_TP */
    public static final String TABLE_NM_NTFY_BIZ_AREA_TP = "NTFY_BIZ_AREA_TP";

    /** Table Name NTFY_SUB_AREA_TP */
    public static final String TABLE_NM_NTFY_SUB_AREA_TP = "NTFY_SUB_AREA_TP";

    /** Table Name NTFY_FREQ_TP */
    public static final String TABLE_NM_NTFY_FREQ_TP = "NTFY_FREQ_TP";

    /** Table Name NTFY_INTVL_UOM_TP */
    public static final String TABLE_NM_NTFY_INTVL_UOM_TP = "NTFY_INTVL_UOM_TP";

    /** Table Name NTFY_ACT_TP */
    public static final String TABLE_NM_NTFY_ACT_TP = "NTFY_ACT_TP";

    /** Table Name NTFY_OTPT_TP */
    public static final String TABLE_NM_NTFY_OTPT_TP = "NTFY_OTPT_TP";

    /** Table Name NTFY_ATT_TP */
    public static final String TABLE_NM_NTFY_ATT_TP = "NTFY_ATT_TP";

    /** EMail check Pattern */
    // 2016/10/06 QC#1443 Del --------------
    //public static final String CHK_EMAIL_PATTERN = "^[a-zA-Z0-9]+([\\w\\.\\-]*[\\w\\-])*@([\\w\\-]+\\.)+[\\w\\-]+$";

    /** Days check Pattern */
    public static final String CHK_DAY_PATTERNM = "^[1-9]|[0][1-9]||[1-2][0-9]|3[0-1]$";

    /** Minute check Pattern */
    public static final String CHK_MIN_PATTERNM = "^[0-9]|[0-5][0-9]$";

    /** Place Holder check Pattern */
    public static final String CHK_PLACE_HOLDER_PATTERNM = "^\\$\\{\\w+\\}$";

    /** Ban String Pattern Start */
    public static final String CHK_BAN_STRING_PATTERNM = "\\b";

    /** TAB Name (Header) */
    public static final String TAB_NAME_HEADER = "Header";

    /** TAB Name (Sql) */
    public static final String TAB_NAME_SQL = "Sql";

    /** TAB Name (Action Detail) */
    public static final String TAB_NAME_ACTION_DETAIL = "ActionDetail";

    /** Search Mode (ALL) */
    public static final String SEARCH_MODE_ALL = "ALL";

    /** Search Mode (HEADER) */
    public static final String SEARCH_MODE_HEADER = "HEADER";

    /** Search Mode (SQL) */
    public static final String SEARCH_MODE_SQL = "SQL";

    /** Blank */
    public static final String BLANK = "";

    /** Comma */
    public static final String COMMA = ",";

    /** Fetch Size */
    public static final int FETCH_SIZE = 1000;

    /** CSV Download Count */
    public static final int CSV_LIMIT_COUNT = 1000;

    /** CSV Extension */
    public static final String CSV_EXT = ".csv";

    /** CSV Notification ID */
    public static final String NTFY_IF = "Notification ID";

    /** CSV Notification Name */
    public static final String NTFY_NAME = "Notification Name";

    /** CSV Notification Description */
    public static final String NTFY_DESCRIPTION = "Notification Description";

    /** CSV Now Time Pattern */
    public static final String NOW_TIME_PATTERN = "yyyyMMddhhmmss";

    /** Next Date Time Check Pattern */
    public static final String DATE_PATTERN = "yyyyMMddHHmmssSSS";

    /** Distribution List Link Name */
    public static final String NTFY_DIST_LIST_NM_LINK_NM = "Distribution List";

    /** SQL Check Word List */
    public static final List<String> SQL_CHK_WORD_LIST;
    static {
        List<String> sqlChkWordList = new ArrayList<String>();
        sqlChkWordList.add("CREATE");
        sqlChkWordList.add("ALTER");
        sqlChkWordList.add("DROP");
        sqlChkWordList.add("GRANT");
        sqlChkWordList.add("REVOKE");
        sqlChkWordList.add("TRUNCATE");
        sqlChkWordList.add("DELETE");
        sqlChkWordList.add("INSERT");
        sqlChkWordList.add("UPDATE");
        sqlChkWordList.add("NEXTVAL");
        sqlChkWordList.add("EXPLAIN");
        SQL_CHK_WORD_LIST = Collections.unmodifiableList(sqlChkWordList);
    }

}
