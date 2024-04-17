/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2560.constant;

/**
 *<pre>
 * NMAL2560Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/13   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2560Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL2560";

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
     * Too many search results. Please narrow your search criteria and
     * retry.
     */
    public static final String ZZZM9002W = "ZZZM9002W";

    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    /** No search result were found. */
    public static final String NMAM0007I = "NMAM0007I";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** The effective period for same Biz Area should be contiguous. */
    public static final String NMAM8514E = "NMAM8514E";

    /** The Structure Name[@] is registered in same Biz Area[@]. */
    public static final String NMAM8515E = "NMAM8515E";

    /** The effective period is incorrect. */
    public static final String NMAM8516E = "NMAM8516E";

    /** The structure definition is duplicated. */
    public static final String NMAM8517E = "NMAM8517E";

    /** The process ended abnormally. */
    public static final String NMAM8020E = "NMAM8020E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** No change has been made. */
    public static final String NMAM8333I = "NMAM8333I";

    // --------------------------------
    // Other
    // --------------------------------
    /** Insert Flg */
    public static final String INSERT_FLG = "I";

    /** max download count */
    public static final int MAX_DOWNLOAD_CNT = 65000;

    /** Tier count */
    public static final int TIER_CNT = 10;
}
