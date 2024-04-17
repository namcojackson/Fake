/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1260.constant;

/**
 *<pre>
 * Contract Machine Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/22   Hitachi         A.Kohinata      Create          N/A
 * 2016/05/30   Hitachi         Y.Takeno        Update          QC#447
 *</pre>
 */
public final class NSAL1260Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL1260";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /** MAX_DATE */
    public static final String MAX_DATE = "99991231";

    /** @ does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /** Your request cannot be processed under this status. */
    public static final String NSAM0065E = "NSAM0065E";

    /** @ has been already registered. */
    public static final String NSAM0079E = "NSAM0079E";

    /** Parameter "@" is not set */
    public static final String NSAM0131E = "NSAM0131E";

    /** The number of items Unjust error. */
    public static final String NSAM0208E = "NSAM0208E";

    /** Number of digits over error (item name [@]). */
    public static final String NSAM0209E = "NSAM0209E";

    /** Item classification error (item name [@]). */
    public static final String NSAM0210E = "NSAM0210E";

    /**
     * The number for this process exceeds the maximum numbers for
     * display and can not process.
     */
    public static final String NSAM0214E = "NSAM0214E";

    /** Either [@] or [@] needs to be set. */
    public static final String NSAM0216E = "NSAM0216E";

    /** @ cannot be specified. */
    public static final String NSAM0432E = "NSAM0432E";

    /** @ is mismatched with @. */
    public static final String NSAM0433E = "NSAM0433E";

    /**
     * The Upload File is empty or only has a header line, therefore
     * it could not be processed. Please confirm the content of the
     * Upload file.
     */
    public static final String ZYEM0004E = "ZYEM0004E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** The process completed successfully . */
    public static final String NZZM0002I = "NZZM0002I";

    // START 2016/05/30 [QC#447, ADD]
    /** The EOL has passed. Can not add the Serial# {@}. */
    public static final String NSAM0478E = "NSAM0478E";
    // END 2016/05/30 [QC#447, ADD]
}
