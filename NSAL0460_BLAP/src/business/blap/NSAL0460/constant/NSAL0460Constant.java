/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0460.constant;

/**
 *<pre>
 * Start Read Capture
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Hitachi         T.Iwamoto       Create          N/A
 * 2015/12/03   Hitachi         T.Iwamoto       Update          QC#1261
 * 2016/02/12   Hitachi         A.Kohinata      Update          QC#2886
 * 2016/12/16   Hitachi         K.Ochiai        Update          QC#16584
 * 2017/07/06   Hitachi         K.Kitachi       Update          QC#18292
 * 2018/05/11   Hitachi         K.Kojima        Update          QC#24817
 * 2022/06/22   CITS            E.Sanchez       Update          QC#59804
 * 2022/08/05   CITS            E.Sanchez       Update          QC#60357
 * 2024/02/15   Hitachi         K.Watanabe      Update          QC#63529
 *</pre>
 */
public class NSAL0460Constant {
    /** Search Limit Number */
    public static final int LIMIT_NUM = 2001;

    /** DATE_FORMAT */
    public static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";

    /** System Error : @ */
    public static final String NSAM0219E = "NSAM0219E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** Please select at least 1 checkbox. */
    public static final String NSAM0015E = "NSAM0015E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** The process has been successfully completed. */
    public static final String NSAM0200I = "NSAM0200I";

    /**
     * The process has been error in some data. Please check the data.
     */
    public static final String NSAM0394W = "NSAM0394W";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** Error Message */
    public static final String ZZM9000E = "ZZM9000E";

    /** Process Name (Submit) */
    public static final String SUBMIT = "Submit";

    /** Message Parameter (No Input Parameter) */
    public static final String NO_INPUT = "The DS Contract pk is not found";

    /** Message Parameter (Invalid Parameter) */
    public static final String INVALID_PARAM = "The DS Contract pk is not valid";

    /** Error Parameter */
    public static final String ERR_PRAM_RSN_CD = "Reason Code";

    /** Error Parameter */
    public static final String ERR_PRAM_NOTES = "Notes";

    /** Error Parameter */
    public static final String ERR_PRAM_READ_DATE = "Read Date";

    /** Error Parameter */
    public static final String ERR_PRAM_READ = "Read";

    /** DS Contract Machine Level Number (1) */
    public static final String MACH_LVL_NUM_1 = "1";

    /** DS Contract Machine Level Number (2) */
    public static final String MACH_LVL_NUM_2 = "2";

    /** DS Contract Machine Level Number (3) */
    public static final String MACH_LVL_NUM_3 = "3";

    /** Third Type (Contract Detail) */
    public static final String TRD_TP_CONTR_DTL = "CD";

    /** Third Type (Billing Meter) */
    public static final String TRD_TP_BLLG_MTR = "BM";

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0460";

    /** Reference authority */
    public static final String AUTH_REFERENCE = "NSAL0460T010";

    /** Update authority */
    public static final String AUTH_UPDATE = "NSAL0460T020";

    // [QC#1261,ADD]START
    /** EXST_ELIG_READ_BEF_DAYS */
    public static final String EXST_ELIG_READ_BEF_DAYS = "EXST_ELIG_READ_BEF_DAYS";
    // [QC#1261,ADD]END

    // [QC#2886,ADD]START
    /** SVC_MEMO_TRX_NM: SVC_PHYS_MTR_PK */
    public static final String SVC_MEMO_TRX_NM_SVC_PHYS_MTR_PK = "SVC_PHYS_MTR_PK";
    // [QC#2886,ADD]END

    // START 2016/12/16 K.Ochiai [QC#16584, ADD]
    /** "@" is invalid. */
    public static final String NSAM0040E = "NSAM0040E";

    /** MTR_READ */
    public static final String MTR_READ = "Meter Read";

    /**
     * Machine is missing start reads. Please enter a read with in set
     * days window.
     */
    public static final String NSZM0750E = "NSZM0750E";
    // END 2016/12/16 K.Ochiai [QC#16584, ADD]

    // START 2017/07/06 K.Kitachi [QC#18292, ADD]
    /**
     * Please execute again after checking the warning field.
     */
    public static final String NSAM0686W = "NSAM0686W";
    // END 2017/07/06 K.Kitachi [QC#18292, ADD]

    // START 2018/05/11 K.Kojima [QC#24817,ADD]
    public static final String NO_BLLG_CNTR_MSG = "Billing Couneters are not registered.";
    // END 2018/05/11 K.Kojima [QC#24817,ADD]

    // START 2022/06/22 E.Sanchez [QC#59804,ADD]
    /**
     * CARRY_OVER_TP_CD_OFF : 1
     */
    public static final String CARRY_OVER_TP_CD_OFF = "0";

    /**
     * CARRY_OVER_TP_CD_ON : 1
     */
    public static final String CARRY_OVER_TP_CD_ON = "1";
    // END 2022/06/22 E.Sanchez [QC#59804,ADD]
 
    // START 2022/08/05 E.Sanchez [QC#60357,ADD]
    /** This Serial #[@] is on Contract #[@] with an open schedule date of [@]. Please register the date after the open schedule date. */
    public static final String NSAM0763E = "NSAM0763E";

    /**
     * date separator
     */
    public static final Character DATE_SEPARATOR = '/';
    // END 2022/08/05 E.Sanchez [QC#60357,ADD]

    // START 2024/02/15 K.Watanabe [QC#63529,ADD]
    /**
     * Current meter reading can not be lower than the previous read. Please select the meter reading type as Adjustment to enter lower reads.
     */
    public static final String NSZM0541E = "NSZM0541E";

    /**
     * The entered reads are lower than the previous billed read. If OK, please submit again. The meter will be entered as an Adjustment.
     */
    public static final String NSAM0784W = "NSAM0784W";
    // END 2024/02/15 K.Watanabe [QC#63529,ADD]
}
