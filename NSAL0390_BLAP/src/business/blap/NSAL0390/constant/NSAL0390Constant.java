/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0390.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/18   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/18   Hitachi         A.Kohinata      Update          QC#4212
 * 2016/06/10   Hitachi         T.Kanasaka      Update          QC#9708
 * 2016/08/29   Hitachi         A.Kohinata      Update          QC#11243
 *</pre>
 */
public class NSAL0390Constant {

    /** Please select at least 1 check box. */
    public static final String NSAM0015E = "NSAM0015E";

    /** System Error : @ */
    public static final String NSAM0219E = "NSAM0219E";

    /** The process has been successfully completed. */
    public static final String NSAM0200I = "NSAM0200I";

    // START 2016/06/10 T.Kanasaka [QC#9708, ADD]
    /** Please input @ field. */
    public static final String NSAM0315E = "NSAM0315E";
    // END 2016/06/10 T.Kanasaka [QC#9708, ADD]

    /**
     * The process has been error in some data. Please check the data.
     */
    public static final String NSAM0394W = "NSAM0394W";

    // add start 2016/08/29 CSA Defect#11243
    /** "@" does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /** @ is not valid. */
    public static final String NSAM0354E = "NSAM0354E";
    // add end 2016/08/29 CSA Defect#11243

    /** Process Name (Submit) */
    public static final String SUBMIT = "Submit";

    /** Message Parameter (No Input Parameter) */
    public static final String NO_INPUT = "The DS Contract pk is not found";

    /** Message Parameter (Invalid Parameter) */
    public static final String INVALID_PARAM = "The DS Contract pk is not valid";

    /** Return Message : Note Added */
    public static final String RTRN_MSG_SUCCESS = "Success.";

    /** Return Message : Failed to register Note */
    public static final String RTRN_MSG_FAILED = "Status Not Eligible.";

    /** DS Contract Machine Level Number (1) */
    public static final String MACH_LVL_NUM_1 = "1";

    /** DS Contract Machine Level Number (2) */
    public static final String MACH_LVL_NUM_2 = "2";

    /** DS Contract Machine Level Number (3) */
    public static final String MACH_LVL_NUM_3 = "3";

    /** Third Name (Base) */
    public static final String TRD_NM_BASE = "BASE";

    /** Third Type (Contract Detail) */
    public static final String TRD_TP_CONTR_DTL = "CD";

    /** Third Type (Billing Meter) */
    public static final String TRD_TP_BLLG_MTR = "BM";

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    /** DS Contract Credit Card PO PK */
    public static final String DS_CONTR_CR_CARD_PO_PK = "DS_CONTR_CR_CARD_PO_PK";
}
