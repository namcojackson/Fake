/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC047001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Contract Billing Schedule API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/14/2015   Hitachi         T.Aoyagi        Create          N/A
 * 03/28/2016   Hitachi         K.Kishimoto     Update          QC#1003
 * 08/09/2016   Hitachi         K.Kishimoto     Update          QC#12310
 * 10/11/2016   Hitachi         K.Kishimoto     Update          QC#14400
 * 2017/08/07   Hitachi         E.Kameishi      Update          QC#18586
 * 2017/08/15   Hitachi         A.Kohinata      Update          QC#18799
 * 09/27/2017   Hitachi         K.Kim           Update          QC#20383
 * 10/06/2017   Hitachi         M.Kidokoro      Update          QC#21546
 * 2018/06/18   Hitachi         U.Kim           Update          QC#24903
 * 2018/07/03   Hitachi         K.Kim           Update          QC#26726
 * 2019/03/05   Hitachi         K.Kitachi       Update          QC#30619
 * 2020/03/18   Hitachi         K.Kitachi       Update          QC#55693
 * 2022/09/20   Hitachi         D.Yoshida       Update          QC#60070
 *</pre>
 */
public class NSZC047001Constant {

    /** SVC_BLLG_SKIP_MTH_TP */
    public static final String SVC_BLLG_SKIP_MTH_TP = "SVC_BLLG_SKIP_MTH_TP";

    /** MONTH_END_0 */
    public static final String MONTH_END_0 = "0";

    /** MONTH_END_99 */
    public static final String MONTH_END_99 = "99";

    /** DAYS_OF_YEAR */
    public static final int DAYS_OF_YEAR = 365;

    /** Rate 100 */
    public static final BigDecimal RATE_100 = new BigDecimal(100);

    /** ASC */
    public static final int ASC = 0;

    /** Scale 4 */
    public static final int SCALE_4 = 4;

    /** Process Mode Credit */
    public static final String PROC_MODE_CR = "D";

    /** MULTIPLICATION */
    public static final String MULTIPLICATION = "*";

    /** SVC_INV_REBILL_TP_CD */
    public static final String SVC_INV_REBILL_TP_CD = "SVC_INV_REBILL_TP_CD";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** The field of [@] is not input. */
    public static final String ZZZM9007E = "ZZZM9007E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** Failed to delete "@". */
    public static final String NSAM0033E = "NSAM0033E";

    // Add Start 08/09/2016 <QC#12310>
    /** Can not be calculated billing schedule because the contract period is too long. */
    public static final String NSZM1054E = "NSZM1054E";

    // START 2017/08/07 E.Kameishi [QC#18586,MOD]
    /** Over Per Sequence */
    public static final BigDecimal OVER_PER_SEQ = BigDecimal.valueOf(10000);
    // Add End   08/09/2016 <QC#12310>
    // END 2017/08/07 E.Kameishi [QC#18586,MOD]

    // START 2018/06/18 U.Kim [QC#24903,ADD]
    /** MessageID : NSZM0845E */
    public static final String NSZM0845E = "NSZM0845E";
    // END 2018/06/18 U.Kim [QC#24903,ADD]

    // START 2017/10/06 M.Kidokoro [QC#21546,ADD]
    /**
     * Excess Rate exceeds the upper limit to appreciation rate after
     * renewal. Contract cannot be renewed.
     */
    public static final String NSZM0846E = "NSZM0846E";

    /**
     * Process ended abnormally.
     */
    public static final String NSZM0391E = "NSZM0391E";

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";
    // END 2017/10/06 M.Kidokoro [QC#21546,ADD]

    // Add Start 03/28/2016 <QC#1003>
    // del start 2017/08/15 QC#18799
//    /**
//     * Start Meter Read Window Before Days
//     */
//    public static final String BLLG_MTR_READ_WINDOW_BEF_DAYS = "BLLG_MTR_READ_WINDOW_BEF_DAYS";
    // del end 2017/08/15 QC#18799

    /**
     * Start Meter Read Window After Days
     */
    public static final String BLLG_MTR_READ_WINDOW_AFT_DAYS = "BLLG_MTR_READ_WINDOW_AFT_DAYS";
    // Add End   03/28/2016 <QC#1003>

    // Add Start 10/11/2016 <QC#14400>
    /** Workflow process name : Preview Billng */
    public static final String WF_PROCESS_NM = "NSWP0002";

    // START 2018/07/03 K.Kim [QC#26726, ADD]
    /** Workflow process name : Negative Billing */
    public static final String WF_PROCESS_NM_NEGA_BLLG = "NSWP0005";
    // END 2018/07/03 K.Kim [QC#26726, ADD]

    /** Prefix Document Id : Regular */
    public static final String PREFIX_DOC_ID_REG = "M";

    /** Prefix Document Id : Fleet */
    public static final String PREFIX_DOC_ID_FLT = "C";

    /** Prefix Document Id : Aggregate */
    public static final String PREFIX_DOC_ID_AGG = "A";

    /** It failed to cancel workflow. */
    public static final String NSZM0866E = "NSZM0866E";
    // Add End   10/11/2016 <QC#14400>

    // add start 2017/08/15 QC#18799
    /** DATE FORMAT: yyyymmdd */
    public static final String DATE_FORMAT = "yyyymmdd";

    /** DEF_WINDOW_BEF_DAYS */
    // START 2022/09/20 [QC#60070,MOD]
//    public static final String DEF_WINDOW_BEF_DAYS = "0";
    public static final int DEF_WINDOW_BEF_DAYS = 0;
    // END   2022/09/20 [QC#60070,MOD]
    // add end 2017/08/15 QC#18799

    // START 2017/09/27 K.Kim [QC#20383, ADD]
    /** Mode : Quotation */
    public static final String PROC_MODE_QUOTATION = "Q";
    // END 2017/09/27 K.Kim [QC#20383, ADD]

    // START 2019/03/05 K.Kitachi [QC#30619, ADD]
    /** Failed to update the DS_CONTR_BLLG_SCHD. */
    public static final String NSZM0828E = "NSZM0828E";
    // END 2019/03/05 K.Kitachi [QC#30619, ADD]

    // START 2020/03/18 K.Kitachi [QC#55693, ADD]
    /** Failed to update the DS_CONTR_BLLG_SCHD_MTR. */
    public static final String NSZM1375E = "NSZM1375E";
    // END 2020/03/18 K.Kitachi [QC#55693, ADD]
}
