/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB271001.constant;

/**
 *<pre>
 * Prospect Candinate Territory and Locations
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/06   Fujitsu         M.Ohno          Create
 *</pre>
 */
public class NMAB271001Constant {
    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** @ doesn't exist in @. */
    public static final String NMAM8132E = "NMAM8132E";

    /** DUP_MODE_01 */
    public static final String DUP_MODE_01 = "01";

    /** DUP_MODE_02 */
    public static final String DUP_MODE_02 = "02";

    /** DUP_MODE_03 */
    public static final String DUP_MODE_03 = "03";

    /** TIME_MODE_01 */
    public static final String TIME_MODE_01 = "01";

    /** TIME_MODE_02 */
    public static final String TIME_MODE_02 = "02";

    /** Table Name "DS_ACCT_PROS" */
    public static final String TABLE_NM_DAP = "DAP";

    /** Table Name "DS_ACCT_RVW_PROS" */
    public static final String TABLE_NM_DARP = "DARP";

    /** varCharConst "MATCH_CRIT_TXT_EXACT" */
    public static final String MATCH_CRIT_TXT_EXACT = "MATCH_CRIT_TXT_EXACT";

    /** varCharConst "MATCH_CRIT_TXT_EXACT" */
    public static final String MATCH_CRIT_TXT_PARTIAL = "MATCH_CRIT_TXT_PARTIAL";

    /** varCharConst "MATCH_CRIT_TXT_EXACT" */
    public static final String MATCH_CRIT_TXT_DUNS = "MATCH_CRIT_TXT_DUNS";

    /** varCharConst "MATCH_CRIT_TXT_EXACT" */
    public static final String MATCH_CRIT_TXT_GLN = "MATCH_CRIT_TXT_GLN";

    /** varCharConst "MATCH_CRIT_TXT_EXACT" */
    public static final String DS_ACCT_NM_SUBSTR_LG = "DS_ACCT_NM_SUBSTR_LG";

    /** varCharConst "MATCH_CRIT_TXT_EXACT" */
    public static final String FIRST_LINE_ADDR_SUBSTR_LG = "FIRST_LINE_ADDR_SUBSTR_LG";

    /** VAR_CHAR_CONST */
    public static final String VAR_CHAR_CONST = "VAR_CHAR_CONST";

    /** DUP_CANDI_MATCH_WRK */
    public static final String DUP_CANDI_MATCH_WRK = "DUP_CANDI_MATCH_WRK";

    // Add Start 2018/07/11 QC#25871
    /** DUP_PROS_CANDI_MATCH_WRK */
    public static final String DUP_PROS_CANDI_MATCH_WRK = "DUP_PROS_CANDI_MATCH_WRK";

    /** DS SCHEMA NAME*/
    public static final String CONST_DB_SCHEMA = "NMAB2650_DB_SCHEMA";
    // Add End 2018/07/11 QC#25871

    /** MATCH_ACCT_LOC_NUM_MAX_LENGTH */
    public static final int MATCH_ACCT_LOC_NUM_MAX_LENGTH = 4000;

    // 2016/10/24 CSA-QC#15214 Del Start
    // /** PROS_TO_TRTY_MODE */
    // public static final String PROS_TO_TRTY_MODE = "2";
    // 2016/10/24 CSA-QC#15214 Del End

    // 2016/10/24 CSA-QC#15214 Add Start
    /** PROS_TO_TRTY_BATCH_MODE */
    public static final String PROS_TO_TRTY_BATCH_MODE = "3";

    /** Territory Match Bulk Size */
    public static final int TRTY_MATCH_BULK_SIZE = 100;
    // 2016/10/24 CSA-QC#15214 Add End

}
