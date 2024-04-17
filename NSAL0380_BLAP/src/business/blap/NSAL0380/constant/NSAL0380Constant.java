/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0380.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/24   Fujitsu         T.Yoshida       Create          N/A
 * 2015/12/15   Hitachi         T.Tsuchida      Update          QC#1964
 * 2016/02/15   Hitachi         O.Okuma         Update          QC3029
 * 2016/07/26   Hitachi         A.Kohinata      Update          QC#12199
 * 2017/12/21   Hitachi         M.Kidokoro      Update          QC#22660
 * 2019/06/26   Hitachi         A.Kohinata      Update          QC#50931
 *</pre>
 */
public class NSAL0380Constant {

    /** System Error : @ */
    public static final String NSAM0219E = "NSAM0219E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** Please select at least 1 check box. */
    public static final String NSAM0015E = "NSAM0015E";

    /** The process has been error in some data. Please check the data. */
    public static final String NSAM0394W = "NSAM0394W";

    // add start 2016/07/26 CSA Defect#12199
    /** If you selected @, it is required to enter @. */
    public static final String NSAM0081E = "NSAM0081E";

    /** [@] cannot be set if you don't select [@]. */
    public static final String NSZM0684E = "NSZM0684E";
    // add end 2016/07/26 CSA Defect#12199

    // START 2017/12/21 M.Kidokoro [QC#22660, ADD]
    /** @ cannot be entered because @ is not equivalent. */
    public static final String NSAM0710E = "NSAM0710E";
    // END 2017/12/21 M.Kidokoro [QC#22660, ADD]

    // add start 2019/06/26 QC#50931
    /** #Days before renewal is a required field for selected renewal type. */
    public static final String NSAM0330E = "NSAM0330E";
    // add end 2019/06/26 QC#50931

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    /** Return Message : "Success. */
    public static final String RTRN_MSG_SUCCESS = "Success.";

    /** Return Message :Status Not Eligible. */
    public static final String RTRN_MSG_FAILED = "Status Not Eligible.";

    /** Process Name (Submit) */
    public static final String SUBMIT = "Submit";

    /** Message Parameter (No Input Parameter DS Contract PK) */
    public static final String NO_INPUT_CONTR_PK = "The DS Contract pk is not found";

    /** Message Parameter (No Input Parameter Contract Category Code) */
    public static final String NO_INPUT_CATG_CD = "The Contract Category Code is not found";

    /** Message Parameter (Invalid Parameter) */
    public static final String INVALID_PARAM = "The DS Contract pk is not valid";

    /** Level Number (1) */
    public static final String MACH_LVL_NUM_1 = "1";

    /** Level Number (2) */
    public static final String MACH_LVL_NUM_2 = "2";

    /** Level Number (3) */
    public static final String MACH_LVL_NUM_3 = "3";

    /** Level Number (4) */
    public static final String MACH_LVL_NUM_4 = "4";

    /** Level Number (5) */
    public static final String MACH_LVL_NUM_5 = "5";

    /** Level Number (6) */
    public static final String MACH_LVL_NUM_6 = "6";

    /** Level Number (7) */
    public static final String MACH_LVL_NUM_7 = "7";

    /** DS Contract Base Usage Name (BASE) */
    public static final String BASE_NM = "BASE";

    /** DS Contract Base Usage Name (OVERAGE) */
    public static final String USG_NM = "OVERAGE";

    /** Table Name (CONTR_AUTO_RNW_TP) */
    public static final String TBL_CONTR_AUTO_RNW_TP = "CONTR_AUTO_RNW_TP";

    /** Table Name (RNW_PRC_METH) */
    public static final String TBL_RNW_PRC_METH = "RNW_PRC_METH";

    /** Table Name (CONTR_UPLFT_TP) */
    public static final String TBL_CONTR_UPLFT_TP = "CONTR_UPLFT_TP";

    /** Table Name (UPLFT_PRC_METH) */
    public static final String TBL_UPLFT_PRC_METH = "UPLFT_PRC_METH";

    /** NumConst : DEF_CONTR_UPLFT_TERM_AOT */
    public static final String NUM_CONST_DEF_CONTR_UPLFT_TERM_AOT = "DEF_CONTR_UPLFT_TERM_AOT";

    /** NumConst Default Value: DEF_CONTR_UPLFT_TERM_AOT */
    public static final BigDecimal DEF_CONTR_UPLFT_TERM_AOT = new BigDecimal(5);

    /** DS Contract Renewal Escalation PK) */
    public static final String DS_CONTR_RNW_ESCL_PK = "DS_CONTR_RNW_ESCL_PK";
}
