/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1300.constant;


/**
 *<pre>
 * Usage Meter Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/01   Hitachi         N.Arai          Create          N/A
 * 2020/03/03   Hitachi         A.Kohinata      Update          QC#56123
 *</pre>
 */
public final class NSAL1300Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL1300";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * There are too many search results, there is data that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** There are no machines/Reads to be processed. */
    public static final String NSAM0607E = "NSAM0607E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    // add start 2020/03/03 QC#56123
    /**
     * Current meter reading can not be higher than the next actual
     * read. Please enter lower reads than the next actual read.
     */
    public static final String NSZM1312E = "NSZM1312E";
    // add end 2020/03/03 QC#56123

    /** Scale of Divide */
    public static final int SCALE_DIVIDE = 2;

    /** Past 12Months */
    public static final int PAST_MONTHS = -12;

}
