/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL2620.constant;

/**
 *<pre>
 * AR Refund Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   Hitachi         J.Kim           Create          N/A
 * 2016/04/25   Hitachi         K.Kojima        Update          QC#7532
 * 2016/08/08   Fujitsu         C.Tanaka        Update          QC#5521
 * 2022/07/20   Hitachi         A.Kohinata      Update          QC#60113
 * 2022/08/01   Hitachi         A.Kohinata      Update          QC#60113-1
 * 2023/06/13   Hitachi         S.Fujita        Update          QC#61486
 *</pre>
 */
public final class NFCL2620Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NFCL2620";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    // mod start 2022/08/01 QC#60113-1
    /**
     * [@] and [@] and [@] and [@] and [@] of the input parameter is a
     * mandatory field either.
     */
    // mod end 2022/08/01 QC#60113-1
    public static final String NFBM0213E = "NFBM0213E";

    /**
     * No search results found.
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * Another user has already updated target record. Please search
     * again.
     */
    public static final String NFAM0004E = "NFAM0004E";

    /**
     * TO date must be greater than FROM date.
     */
    public static final String ZZSM4100E = "ZZSM4100E";

    /**
     * The data @ does not exist in the master.
     */
    public static final String NFDM0012E = "NFDM0012E";

    // QC#5521 ADD Start
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NFCM0865E = "NFCM0865E";

    /** yyyyMMdd */
    public static final String YYYYMMDD_PATTERN = "yyyyMMdd";
    // QC#5521 ADD End

    /**
     * Error Message
     */
    // mod start 2022/07/20 QC#60113
    //public static final String[] ERROR_MSG = new String[] {"Refund Number", "Refund Type", "Customer", "Refund Status" };
    // mod start 2022/08/01 QC#60113-1
    //public static final String[] ERROR_MSG = new String[] {"Refund Number", "Refund Type", "Customer", "Refund Status", "Check#" };
    public static final String[] ERROR_MSG = new String[] {"Refund Number", "Refund Type", "Customer", "Refund Status", "Check#] and [Refund Amount" };
    // mod end 2022/08/01 QC#60113-1
    // mod end 2022/07/20 QC#60113

    /**
     * Defaul Bill/Ship to
     */
    public static final String PROCESS_MODE = "04";

    /**
     * Scale length : 2
     */
    public static final int SCALE_2 = 2;

    // START 2023/06/13 S.Fujita [QC#61486,ADD]
    /** Function ID NFCL2620T020 (Reference for Collection Reps) */
    public static final String FUNC_ID_CLT_REPS = "NFCL2620T020";
    // END 2023/06/13 S.Fujita [QC#61486,ADD]
}
