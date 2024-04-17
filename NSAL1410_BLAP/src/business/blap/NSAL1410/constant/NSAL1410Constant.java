/*
 * <Pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1410.constant;

/**
 *<pre>
 * Contract Branch Rep Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/06/07   Hitachi         A.Kohinata      Create          QC#60080
 * 2022/07/25   Hitachi         H.Watanabe      Update          QC#60080
 *</pre>
 */
public final class NSAL1410Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL1410";

    /** MAX_DATA */
    public static final int MAX_DATA = 99999999;

    /**
     * The Upload File is empty or only has a header line, therefore
     * it could not be processed. Please confirm the content of the
     * Upload file.
     */
    public static final String ZYEM0004E = "ZYEM0004E";

    /**
     * The number for this process exceeds the maximum numbers for
     * display and can not process.
     */
    public static final String NSAM0214E = "NSAM0214E";

    /** The number of items Unjust error. */
    public static final String NSAM0208E = "NSAM0208E";

    /** Number of digits over error (item name [@]). */
    public static final String NSAM0209E = "NSAM0209E";

    /** Item classification error (item name [@]). */
    public static final String NSAM0210E = "NSAM0210E";

    /** @ does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /** New Branch Rep. is missing. */
    public static final String NSAM0453E = "NSAM0453E";

    /**
     * Relationships are incorrect among LOB, Branch and Rep.
     */
    public static final String NSAM0762E = "NSAM0762E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /**
     * There are too many data, it will be processed in batch. Please
     * wait for the completion notification.
     */
    public static final String NSAM0761I = "NSAM0761I";

    /**
     * The process has been error in some data. Please check the data.
     */
    public static final String NSAM0394W = "NSAM0394W";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

}
