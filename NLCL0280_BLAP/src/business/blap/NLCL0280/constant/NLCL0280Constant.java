/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0280.constant;

/**
 *<pre>
 * Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/25   CITS            T.Tokutomi      Create          N/A
 * 05/25/2016   CSAI            Y.Imazu         Update          QC#6864
 * 04/21/2023   CITS            DuyLe           Update          QC#61403
 *</pre>
 */
public class NLCL0280Constant {

    /** BUSINESS_ID */
    public static final String BUSINESS_ID = "NLCL0280";

    /** MDSE_DIGIT_8 */
    public static final int MDSE_DIGIT_8 = 8;

    /** DS_COND_CONST_GRP_ID : NLCL0280_INVTY_TRX */
    public static final String NLCL0280_INVTY_TRX = "NLCL0280_INVTY_TRX";

    /** DS Condition Constant Group ID */
    public static final String LOC_STS_COND_CONST_GRP_ID = "NLCL0250_LOC_STS";

    /***************************************************
     * Message
     ***************************************************/

    /** The entered [@] does not exist in master. */
    public static final String NLZM2278E = "NLZM2278E";

    /** The combination of [@] and [@] does not exist in master. */
    public static final String NLZM2279E = "NLZM2279E";

    /**  Data does not exist in @ */
    public static final String NLCM0170E = "NLCM0170E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NLZM2273E = "NLZM2273E";

    /** The process [@] has been successfully completed. exists. */
    public static final String ZZZM9003I = "ZZZM9003I";

    // 04/21/2023 - QC#61403 - DuyLe - START
    /** The download information could not be found.  Go back to Search and try again. */
    public static final String NMAM8298E = "NMAM8298E";
    
    /** Limit Search RowNumber */
    public static final int LIMIT_SEARCH_ROWNUM = 1001;

    /** Limit Download RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;
    // 04/21/2023 - QC#61403 - DuyLe - END
}
