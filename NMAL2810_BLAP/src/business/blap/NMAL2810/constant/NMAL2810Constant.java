/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2810.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NMAL2810Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/27   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2810Constant {

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

    // --------------------------------
    // Other
    // --------------------------------
    /** Mode : Reference(For Merge to Location ID) */
    public static final String MODE_REF_LOC = "01";

    /** Mode : Reference(For Merge to Prospect#(For SFDC# / OASIS)) */
    public static final String MODE_REF_PROS = "02";

    /** Mode : Set(For Merge to Location ID) */
    public static final String MODE_SET_LOC = "03";

    /** Mode : Set(For Merge to Prospect#(For SFDC# / OASIS)) */
    public static final String MODE_SET_PROS = "04";

    /** Radio : Prospect */
    public static final BigDecimal RADIO_PROS = BigDecimal.valueOf(0);

    /** Radio : Merge to */
    public static final BigDecimal RADIO_MRG_TO = BigDecimal.valueOf(1);

    /** NMZC003001 : ctryCd */
    public static final String NMZC003001_CTRY_CD = "US";

    /** NMZC003001 : Return Cd(Error) */
    public static final String NMZC003001_RTRN_E = "E";

    /** NMZC003001 : Return Cd(Suggested) */
    public static final String NMZC003001_RTRN_S = "S";

    /** Max Date(9999/12/31) */
    public static final String MAX_DATE = "99991231";
}
