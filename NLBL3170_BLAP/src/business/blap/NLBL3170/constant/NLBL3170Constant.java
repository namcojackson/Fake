/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3170.constant;

/**
 *<pre>
 * Tracking Number Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/28   CITS            T.Hakodate      Create          QC#21913
 * 2020/06/18   CITS            M.Furugoori     Update          QC#56914
 *</pre>
 */

public class NLBL3170Constant {

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    // START 2020/06/18 [QC#56914,ADD]
    /**
     * Replace Character at CARR_URL.CARR_TRK_URL.
     */
    public static final String REPLACE_CHAR = "\\$\\$";
    // END 2020/06/18 [QC#56914,ADD]
}
