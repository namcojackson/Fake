/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0450.constant;

/**
 *<pre>
 * Terms & Conditions
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Hitachi         T.Iwamoto         Create          N/A
 * 2017/03/01   Hitachi         A.Kohinata        Update          QC#17608
 *</pre>
 */
public class NSBL0450Constant {

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** The process completed successfully . */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * This data has been updated by another user.Table Name: [@], Key
     * Field Name: [@], Key Value: [@]
     */
    public static final String NSBM0075E = "NSBM0075E";

    /** Table Name. */
    public static final String TBL_SUPPL = "SVC_SUPPL_TASK";

    /** Field Name */
    public static final String FIELD_SUPPL = "Supplemental Task#";

    /** Please change at least 1 line. */
    public static final String NSBM0133E = "NSBM0133E";

    /** An error occurred in Supplemental Task#: @. Message: @ */
    public static final String NSBM0132E = "NSBM0132E";

    /** CATEGORY_Y:Interrupt */
    public static final String CATEGORY_Y = "Interrupt";

    /** CATEGORY_N:Supplemental */
    public static final String CATEGORY_N = "Supplemental";

    /** 6000 */
    public static final int MAX_MINUTE = 6000;

    /** 60 */
    public static final int MINUTE = 60;

    /** 59:59 */
    public static final String MAX_TIME = "99:59";

    /** HH24MISS */
    public static final String TIME_FORMAT_PRE = "HH24MISS";

    /** HH24:MI */
    public static final String TIME_FORMAT = "HH24:MI:SS";

    /** HH24:MI */
    public static final String DL_FORMAT = "mm/dd/yyyy";

    /** padding 00 */
    public static final String PAD00 = "FM00";

    /** 60 */
    public static final int LIMIT_DOWNLOAD = 65000;

    /** Time format */
    public static final String CHECK_TIME_FORMAT = "^([0-9][0-9]):([0-5][0-9])$";

    /** Business ID */
    public static final String BUSINESS_ID = "NSBL0450";

    /** SCRN_ID : NSBL0450Scrn00 */
    public static final String SCRN_ID = "NSBL0450Scrn00";

    /** substring 0 */
    public static final int SUB_0 = 0;

    /** substring 2 */
    public static final int SUB_2 = 2;

    /** substring 3 */
    public static final int SUB_3 = 3;

    /** substring 4 */
    public static final int SUB_4 = 4;

    /** substring 5 */
    public static final int SUB_5 = 5;

    /** substring 6 */
    public static final int SUB_6 = 6;

    /** substring 8 */
    public static final int SUB_8 = 8;

    /** length 4 */
    public static final int LENGTH_4 = 4;

    /** length 5 */
    public static final int LENGTH_5 = 5;

    /** length 6 */
    public static final int LENGTH_6 = 6;

   /** length 8 */
    public static final int LENGTH_8 = 8;

    /** COLON */
    public static final String COLON = ":";

    /** SECOND 00 */
    public static final String SECOND_00 = "00";
}
