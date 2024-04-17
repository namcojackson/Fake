/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB113001;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Contract Department approval notification
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/23/2016   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/09   Hitachi         K.Kojima        Update          QC#14038
 * 2016/12/15   Fujitsu         H.Ikeda         Update          QC#15823
 * </pre>
 */
public interface NFBB113001Constant {
    // ** Fixed Value ** //
    // ** Common ** //
    /** Empty String */
    static final String EMPTY_STRING = "";
    // START 2016/09/12 K.Kojima [QC#14038,DEL]
    // /** Bulk Commit Limit */
    // static final int INT_BULK_COM_LIM = 10000;
    // END 2016/09/12 K.Kojima [QC#14038,DEL]
    /** IF_PROC_STS_CD C */
    static final String IF_PROC_STS_CD_C = "C";
    /** AP_MAINT_TOL_CD TOLERANCE */
    static final String AP_MAINT_TOL_CD_TOLERANCE = "Tolerance";

    // START 2017/12/26 J.Kim [QC#22458,DEL]
    // // ** AP_DS_WF_STS_CD ** //
    // /** AP_DS_WF_STS_CD Pending */
    // static final String AP_DS_WF_STS_CD_00 = "00";
    // /** AP_DS_WF_STS_CD Approved */
    // static final String AP_DS_WF_STS_CD_01 = "01";
    // /** AP_DS_WF_STS_CD Rejected */
    // static final String AP_DS_WF_STS_CD_09 = "09";

    //// ** AP_MAINT_INV_STS_CD ** //
    // /** AP_MAINT_INV_STS_CD SAVED */
    // static final String AP_MAINT_INV_STS_CD_00 = "00";
    // /** AP_MAINT_INV_STS_CD VALIDATED */
    // static final String AP_MAINT_INV_STS_CD_10 = "10";
    // /** AP_MAINT_INV_STS_CD PENDING CONTRACT APPROVAL */
    // static final String AP_MAINT_INV_STS_CD_11 = "11";
    // /** AP_MAINT_INV_STS_CD PENDING WF APPROVAL */
    // static final String AP_MAINT_INV_STS_CD_12 = "12";
    // /** AP_MAINT_INV_STS_CD WORKFLOW REQUESTED */
    // static final String AP_MAINT_INV_STS_CD_15 = "15";
    // /** AP_MAINT_INV_STS_CD APPROVED */
    // static final String AP_MAINT_INV_STS_CD_20 = "20";
    // /** AP_MAINT_INV_STS_CD PAID */
    // static final String AP_MAINT_INV_STS_CD_30 = "30";
    // /** AP_MAINT_INV_STS_CD CANCELLED */
    // static final String AP_MAINT_INV_STS_CD_90 = "90";
    // END 2017/12/26 J.Kim [QC#22458,DEL]

    // ** CNT_TP_CD ** //
    /** CNT_TP_CD Black and White */
    static final String CNT_TP_CD_BW = "BW";
    /** CNT_TP_CD Color */
    static final String CNT_TP_CD_CLR = "CLR";

    // ** Message ID ** //
    /** Error Message */
    static final String NFBM0028E = "NFBM0028E";
    /** Error Message */
    static final String NFBM0184E = "NFBM0184E";
    // START 2016/12/15 H.Ikeda [QC#15823,ADD]
    /** Error Message */
    static final String NFZM0027E = "NFZM0027E";
    // END   2016/12/15 H.Ikeda [QC#15823,ADD]

    // ** SSM Statement ID's Fixed Value ** //
    /** SSM Statement ID */
    static final String SELECT_CM_MAINT_INV = "SELECT_CM_MAINT_INV";

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String AP_MAINT_TOL_CD = "AP_MAINT_TOL_CD";
    /** DB Item Column Name */
    static final String AP_MAINT_INV_TOL_FROM_AMT = "AP_MAINT_INV_TOL_FROM_AMT";
    /** DB Item Column Name */
    static final String AP_MAINT_INV_TOL_TO_AMT = "AP_MAINT_INV_TOL_TO_AMT";
    /** DB Item Column Name */
    static final String AP_MAINT_BASE_FROM_RATE = "AP_MAINT_BASE_FROM_RATE";
    /** DB Item Column Name */
    static final String AP_MAINT_BASE_TO_RATE = "AP_MAINT_BASE_TO_RATE";
    /** DB Item Column Name */
    static final String AP_MAINT_OVER_FROM_RATE = "AP_MAINT_OVER_FROM_RATE";
    /** DB Item Column Name */
    static final String AP_MAINT_OVER_TO_RATE = "AP_MAINT_OVER_TO_RATE";
    /** DB Item Column Name */
    static final String AP_MAINT_FROM_CNT = "AP_MAINT_FROM_CNT";
    /** DB Item Column Name */
    static final String AP_MAINT_TO_CNT = "AP_MAINT_TO_CNT";
    /** DB Item Column Name */
    static final String AP_MAINT_MTR_FROM_RATE = "AP_MAINT_MTR_FROM_RATE";
    /** DB Item Column Name */
    static final String AP_MAINT_MTR_TO_RATE = "AP_MAINT_MTR_TO_RATE";
    /** DB Item Column Name */
    static final String AP_MAINT_MTR_MTH = "AP_MAINT_MTR_MTH";
    /** DB Item Column Name */
    static final String AP_INV_NUM = "AP_INV_NUM";
    /** DB Item Column Name */
    static final String AP_VND_CD = "AP_VND_CD";
    /** DB Item Column Name */
    static final String SER_NUM = "SER_NUM";
    /** DB Item Column Name */
    static final String START_DT = "START_DT";
    /** DB Item Column Name */
    static final String CNT_TP_CD = "CNT_TP_CD";
    /** DB Item Column Name */
    static final String START_READ_MTR_CNT = "START_READ_MTR_CNT";
    /** DB Item Column Name */
    static final String END_READ_MTR_CNT = "END_READ_MTR_CNT";
    /** DB Item Column Name */
    static final String READ_MTR_CNT = "READ_MTR_CNT";
    /** DB Item Column Name */
    static final String INV_DT = "INV_DT";
    /** DB Item Column Name */
    static final String PRNT_VND_NM = "PRNT_VND_NM";
    /** DB Item Column Name */
    static final String OVRD_SER_NUM = "OVRD_SER_NUM";
    /** DB Item Column Name */
    static final String END_DT = "END_DT";
    /** DB Item Column Name */
    static final String CNTR_TP_NM = "CNTR_TP_NM";
    /** DB Item Column Name */
    static final String AP_TOL_AMT = "AP_TOL_AMT";
    /** DB Item Column Name */
    static final String AP_INV_AMT = "AP_INV_AMT";
    /** DB Item Column Name */
    static final String AP_MISC_AMT = "AP_MISC_AMT";
    /** DB Item Column Name */
    static final String AP_TAX_AMT = "AP_TAX_AMT";
    /** DB Item Column Name */
    static final String LATE_FEE_AMT = "LATE_FEE_AMT";
    /** DB Item Column Name */
    static final String AP_ADJ_AMT = "AP_ADJ_AMT";
    /** DB Item Column Name */
    static final String AP_MAINT_INV_STS_CD = "AP_MAINT_INV_STS_CD";
    /** DB Item Column Name */
    static final String BASE_AMT = "BASE_AMT";
    /** DB Item Column Name */
    static final String BASE_PRC_DEAL_AMT = "BASE_PRC_DEAL_AMT";
    /** DB Item Column Name */
    static final String BW_MTR_ALWNC_CNT = "BW_MTR_ALWNC_CNT";
    /** DB Item Column Name */
    static final String BW_MTR_PRC_AMT_RATE = "BW_MTR_PRC_AMT_RATE";
    /** DB Item Column Name */
    static final String COLOR_MTR_ALWNC_CNT = "COLOR_MTR_ALWNC_CNT";
    /** DB Item Column Name */
    static final String COLOR_MTR_PRC_AMT_RATE = "COLOR_MTR_PRC_AMT_RATE";
    // START 2016/09/09 K.Kojima [QC#14038,ADD]
    /** DB Item Column Name */
    static final String EZINUSERID = "EZINUSERID";
    // END 2016/09/09 K.Kojima [QC#14038,ADD]

    /****************************** 
     * Mail Information
     ******************************/
    /** Subject : License Invoice */
    static final String MAIL_SUB_NM_APPROVAL = "Automated Approval Notification";
    /** Subject : Unmatched License Invoice List */
    static final String MAIL_SUB_NM_ERROR = "AP Maintenance Invoice Approval Notification";
    /** Mail Group ID (From) */
    static final String CST_MAIL_GRP_ID_FROM = "FROM0003";
    /** Mail Key (From) */
    static final String CST_MAIL_KEY_1_FROM = "NFB";
    /** Mail Group ID (To) */
    static final String CST_MAIL_GRP_ID_TO = "NFBB1110";
    /** Mail Key (To) */
    static final String CST_MAIL_KEY_TO_1 = "01";
    /** Mail Key (To) */
    static final String CST_MAIL_KEY_TO_2 = "TO";
    /** Mail Key (To) */
    static final String CST_MAIL_KEY_TO_3_APPROVAL = "APVL";
    /** Mail Key (To) */
    static final String CST_MAIL_KEY_TO_3_ERROR = "ERR";
    /** Mail Group ID (CC) */
    static final String CST_MAIL_GRP_ID_CC = "NFBB1110";
    /** Mail Key (CC) */
    static final String CST_MAIL_KEY_CC_1 = "01";
    /** Mail Key (CC) */
    static final String CST_MAIL_KEY_CC_2 = "CC";
    /** Mail Key (CC) */
    static final String CST_MAIL_KEY_CC_3_APPROVAL = "APVL";
    /** Mail Key (CC) */
    static final String CST_MAIL_KEY_CC_3_ERROR = "ERR";
    /** Mail Template ID */
    static final String ML_TMPL_ID = "NFBB1110M001";
    /** template parameter key : subject */
    static final String MAIL_TEMPLATE_KEY_SUBJECT = "subject";
    /** template parameter key : message */
    static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";
    /** Mail message fixed Program ID  */
    static final String MAIL_MESSAGE_FIXED_LINE_PROGRAM_ID = "NFBB111001";
    /** line feed code */
    static final String LINE_FEED_CODE = "\r\n";
    /** License email line format */
    static final String FORMAT_APPROVAL_EMAIL_LINE = "%-9s%-17s%-15s%-11s%-16s%-17s%-21s%-11s%-11s%11s%-1s%21s%-1s%-8s";
    /** Unmatched email line format */
    static final String FORMAT_ERROR_EMAIL_LINE = "%-27s%-9s%-17s%-15s%-11s%-16s%-18s%-18s%11s%21s";

    // ** Workflow ** //
    /** VAR_CAR_CONST Key : Maintenance Invoice Workflow ID */
    public static final String CONST_KEY_MAINT_INV_WF_ID = "MAINT_INV_WF_ID";
    /** System error has occurred. */
    public static final String NFDM0008E = "NFDM0008E";    
    
}
