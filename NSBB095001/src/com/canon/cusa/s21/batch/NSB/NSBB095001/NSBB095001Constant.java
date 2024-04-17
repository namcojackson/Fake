/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB095001;

/**
 *<pre>
 * FSR Completion Letter Creation Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/04   Hitachi         T.Tomita        Create          QC#18713(Solution#138)
 * 2019/03/01   Hitachi         A.Kohinata      Update          QC#30592
 * 2019/10/04   Hitachi         K.Fujimoto      Update          QC#53389
 *</pre>
 **/
public class NSBB095001Constant {

    /**
     * BATCH_BIZ_ID : NSBB0950
     */
    public static final String BATCH_BIZ_ID = "NSBB0950";

    /**
     * BATCH_PROGRAM_ID : NSBB0950
     */
    public static final String BATCH_PROGRAM_ID = BATCH_BIZ_ID + "01";

    /** RPT_ID : NSBF0010 */
    public static final String RPT_ID = "NSBF0010";

    /**
     * Date Format : MM/dd HH:mm
     */
    public static final String DATE_FORMAT = "MM/dd/yyyy";

    /**
     * Timestamp Format : yyyyMMddHHmmssSSS
     */
    public static final String TS_FORMAT = "yyyyMMddHHmmssSSS";

    /**
     * Date Format : MM/dd HH:mm
     */
    public static final String DATE_TIME_FORMAT = "MM/dd HH:mm";

    /** Date Time length */
    public static final int LEN_DT_TM = 14;

    /** Date length */
    public static final int LEN_DT = 8;

    /** Format PRE_SVC_MIN_CHRG_HRS_AMT */
    public static final String PRE_SVC_MIN_CHRG_HRS_AMT = "(";

    /** Format POST_SVC_MIN_CHRG_HRS_AMT */
    public static final String POST_SVC_MIN_CHRG_HRS_AMT = " HR MIN)";

    /** Default CCY Code */
    public static final String DEF_CCY_CD = "USD";

    /** RPT_NM_SUFIX */
    public static final String RPT_NM_SUFIX = "Canon_Maintenance_S_";

    /** FILE_EXTN */
    public static final String FILE_EXTN = ".pdf";

    /** No Parts Comment */
    public static final String NO_PRT_CMT = "No Parts Installed.";

    /**
     * ML_TMPL_ID : NSBB0950M001
     */
    public static final String NSBB0950M001 = "NSBB0950M001";

    /**
     * Mail Group ID : FROM0013
     */
    public static final String FROM0013 = "FROM0013";

    /**
     * Message : There is no parameter in [@].
     */
    public static final String NSBM0032E = "NSBM0032E";

    /**
     * @ doesn't exist in the VAR_CHAR_CONST.
     */
    public static final String NSBM0069E = "NSBM0069E";

    /**
     * Message : The e-mail template <template ID: [@]> cannot be
     * obtained.
     */
    public static final String NSBM0077E = "NSBM0077E";

    /**
     * Failed to update @ table. [@]
     */
    public static final String NSBM0120E = "NSBM0120E";

    /**
     * Failed to insert @ table. [@]
     */
    public static final String NSBM0121E = "NSBM0121E";

    /** 
     * Template parameter key : ctacPsnNm
     */
    public static final String MAIL_PARAM_CTAC_PSN_NM = "ctacPsnNm";

    // add start 2019/03/01 QC#30592
    /** Constant Key : NSBB0950_SVC_MEMO_TP */
    public static final String CONST_KEY_NSBB0950_SVC_MEMO_TP = "NSBB0950_SVC_MEMO_TP";

    /** Delimiter : , */
    public static final String DELIMITER = ",";
    // add end 2019/03/01 QC#30592
    // add start 2019/10/04 K.Fujimoto QC#53389
    /** Constant Key : NSBB0950_EXCLD_SVC_TASK_STS */
    public static final String CONST_KEY_NSBB0950_DBRF_ERR_SENDBL_FLG = "NSBB0950_DBRF_ERR_SENDBL_FLG";
    // add end   2019/10/04 K.Fujimoto QC#53389
}

