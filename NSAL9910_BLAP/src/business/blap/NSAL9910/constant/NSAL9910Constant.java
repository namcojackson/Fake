/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL9910.constant;

/**
 *<pre>
 * General Business Master Maintenance List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Hitachi         A.Kohinata      Create          N/A
 * 2018/06/15   CITS            M.Naito         Update          QC#24320
 *</pre>
 */
public final class NSAL9910Constant {

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    // START 2018/06/15 M.Naito [QC#24320, ADD]
    /** FUNC_ID_T020 */
    public static final String FUNC_ID_T020 = "T020";
    // END 2018/06/15 M.Naito [QC#24320, ADD]
}
