/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB320001.constant;

/**
 *<pre>
 * Marketing Data Mass Update Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/20   Fujitsu         M.Ohno          Create          N/A
 * 2016/09/07   SRAA            Y.Chen          Update          QC#12258
 *</pre>
 */
public class NMAB320001Constant {
    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** @ doesn't exist in @. */
    public static final String NMAM8132E = "NMAM8132E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

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

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** MAX_MATCH_LG */
    public static final int MAX_MATCH_LG = 4000;

    /** PROS_TO_TRTY_MODE */
    public static final String PROS_TO_TRTY_MODE = "2";

    // QC#12258
    /** PROS_TO_TRTY_BATCH_MODE */
    public static final String PROS_TO_TRTY_BATCH_MODE = "3";

    /** Territory Match Bulk Size */
    public static final int TRTY_MATCH_BULK_SIZE = 100;

    /** VAR_CHAR_CONST */
    public static final String PROCEDURE_NM = "CANON_E404_EXTN_INBOUND_PKG.UPDATE_E404_ACCT_ASSOC_GRP1";

    /** PROS_TO_TRTY_MODE */
    public static final char QUOTE = '\'';

}
