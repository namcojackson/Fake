/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2830.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 *<pre>
 * NMAL2830Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2830Constant {

    /** Business Application ID */
    public static final String BIZ_ID = "NMAL2830";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NMAL2830Scrn00";

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

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NMAM0182E = "NMAM0182E";

    /** [@] is not selected. */
    public static final String NMAM0014E = "NMAM0014E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** No search result were found. */
    public static final String NMAM0007I = "NMAM0007I";

    /**
     * Too many search results. Please narrow your search criteria and
     * retry.
     */
    public static final String NZZM0007E = "NZZM0007E";

    /** Please select [@]. */
    public static final String NMAM8461E = "NMAM8461E";

    /** The entered [@] does not exist in master. */
    public static final String NMAM8121E = "NMAM8121E";

    /** No change has been made. */
    public static final String NMAM8333I = "NMAM8333I";

    /** [@] is not Active. */
    public static final String NMAM8497E = "NMAM8497E";

    /** [@] cannot be merged. */
    public static final String NMAM8592E = "NMAM8592E";

    // --------------------------------
    // Other
    // --------------------------------
    /** Pulldown Code(Display Territory Visibility) */
    public static final String[] DTV_PULLDOWN_CD = {ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_ON_Y };

    /** Pulldown Name(Display Territory Visibility) */
    public static final String[] DTV_PULLDOWN_NM = {ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_ON_Y };

    /** Max Row(Prospect) */
    public static final int MAX_ROW_PROSPECT = 800;

    /** Max Row(Duplicate_realTime) */
    public static final int MAX_ROW_DUPLICATE_REAL_TIME = 30;

    /** Max Row(Duplicate_nonRealTime) */
    public static final int MAX_ROW_DUPLICATE_NON_REAL_TIME = 10;

    /** Limit Row(Duplicate for Disp) */
    public static final int LIMIT_ROW_DUPLICATE_FOR_DISP = 10;

    /** Limit Row(Disp) */
    public static final int LIMIT_ROW_DISP = 2388;

    /** Arrow(Left) */
    public static final String ARROW_LEFT = " <- ";

    /** Parenthesis(Start) */
    public static final String PARENTHESIS_START = "(";

    /** Parenthesis(End) */
    public static final String PARENTHESIS_END = ")";

    /** Spece(Half size) */
    public static final String SPECE_HALF_SIZE = " ";

    /** Comma */
    public static final String COMMA = ", ";

    /** Row Type(Prospect) */
    public static final String ROW_TP_PROSPECT = "Prospect";

    /** Row Type(Requested Merge To) */
    public static final String ROW_TP_REQUESTED_MERGE_TO = "Requested Merge To";

    /** Row Type(Duplicate) */
    public static final String ROW_TP_DUPLICATE = "Duplicate";

    /** Row Name(Duplicate) */
    public static final String ROW_NM_DUPLICATE = "Option ";

    /** Non Under Review */
    public static final String NON_UNDER_REVIEW = "RVW";

    /** Condition(Exact Match) */
    public static final String COND_EXACT_MATCH = "1";

    /** Condition(Partial Match) */
    public static final String COND_PARTIAL_MATCH = "2";

    /** Condition(Duns Match) */
    public static final String COND_DUNS_MATCH = "3";

    /** Prefix(Merge To) */
    public static final String PREFIX_MERGE_TO = "Merge To:";

    /** Prefix(Location #) */
    public static final String PREFIX_LOC_NUM = "Location #:";

    /** Message Args(NMAM8461E) */
    public static final String MSG_ARGS_NMAM8461E = "only Option's Merge to or All Prospect Merge To or input location# in Merge to field";

    /** Message Args(NMAM8461E - Not Fund) */
    public static final String MSG_ARGS_NMAM8461E_NOT_FUND = "Merge to location that can be candidated for Merge Prospect";

    /** Message Args(NMAM8592E) */
    public static final String MSG_ARGS_NMAM8592E = "Selected Prospect/Customer";

    /** API Call Type(Merge To Txt) */
    public static final String API_CALL_TP_MERGE_TO_TXT = "Merge To Txt";

    /** API Call Type(Merge To Check Box) */
    public static final String API_CALL_TP_MERGE_TO_CHECK = "Merge To Check Box";

    /** API Call Type(All Merge To Check Box) */
    public static final String API_CALL_TP_ALL_MERGE_TO_CHECK = "All Merge To Check Box";

    /** PROS_ACCT_INAC_RSN_CD(VAR_CHAR_CONST) */
    public static final String PROS_ACCT_INAC_RSN_CD = "PROS_ACCT_INAC_RSN_CD";

    /** Regular Expression(Alphanumeric only) */
    public static final String REG_EXP_ALPH_NUM_ONLY = "[^A-Za-z0-9]+";

    /** Max Date(9999/12/31) */
    public static final String MAX_DATE = "99991231";
}
