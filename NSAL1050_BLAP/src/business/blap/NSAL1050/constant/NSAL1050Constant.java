/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1050.constant;

/**
 *<pre>
 * T&C Attributes Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public final class NSAL1050Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL1050";

    /** LIMIT_DOWNLOAD:65000 */
    public static final int LIMIT_DOWNLOAD = 65000;

    /** There is no data to be processed. */
    public static final String NSAM0456E = "NSAM0456E";

    /** Contract is not found. */
    public static final String NSAM0093E = "NSAM0093E";

    /** [@] is duplicated. */
    public static final String NSAM0035E = "NSAM0035E";

    /** If you selected @, it is required to enter @. */
    public static final String NSAM0081E = "NSAM0081E";

    /** For [@], please enter  [@] or a later date. */
    public static final String NSAM0457E = "NSAM0457E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /** "@" does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** Data update failure.  [ TableName = @ , key = @, value = @ ] */
    public static final String ZZMM0015E = "ZZMM0015E";

    /** Data insert failure.  [ TableName = @ , key = @, value = @ ] */
    public static final String ZZMM0001E = "ZZMM0001E";

    /**
     * RETURN_CD_NORMAL -- 0000
     */
    public static final String RETURN_CD_NORMAL = "0000";

    /** COL_NUM */
    public static final int[] COL_NUM = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
}
