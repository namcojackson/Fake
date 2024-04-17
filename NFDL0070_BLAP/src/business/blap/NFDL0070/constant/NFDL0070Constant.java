/**
 * <Pre>
 * 
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.blap.NFDL0070.constant;

/**
 * NFDL0070Constant.
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/16   Fujitsu         M.Yamada        Create          N/A
 * 2022/04/22   CITS            K.Suzuki        Update          QC#59333
 *</pre>
 */
public class NFDL0070Constant {

    /** MAX_SEARCH_CNT:999 */
    public static final int MAX_SEARCH_CNT = 999;

    /** max download count */
    public static final int MAX_DOWNLOAD_CNT = 65000;

    /** MSG_ID */
    public static enum MSG_ID {

        /** No search results found. */
        NZZM0000E
        /**
         * There are too many search results, there is data that
         * cannot be displayed.
         */
        , NZZM0001W
        //
        , /* */;

    }

    // START 2016/11/11 J.Kim [QC#15756,ADD]
    /** Receipt for refund has been applied already. */
    public static final String NFDM0041E = "NFDM0041E";
    // END 2016/11/11 J.Kim [QC#15756,ADD]

    // START 2022/04/22 K.Suzuki [QC#59333,ADD]
    /** The target transaction is already in Refund process. Please cancel Refund process if you would like to process further. */
    public static final String NFDM0053E = "NFDM0053E";
    // END   2022/04/22 K.Suzuki [QC#59333,ADD]
}
