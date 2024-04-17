/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB047001.constant;

/**
 *<pre>
 * NSAB047001:Create CFS Contract Batch Constant Value
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/28/2016   CITS            Keiichi Masaki  Create          N/A
 * 09/30/2016   Hitachi         N.Arai          Update          QC#12669
 *</pre>
 */

public class NSAB047001Constant {
    /** Business ID */
    public static final String BUSINESS_ID = "NSAB0470";
    /** Program ID */
    public static final String PROGRAM_ID = "NSAB047001";
    /** fetchSize */
    public static final int FETCH_SIZE = 1000;
    // =================================================
    // Variable Character Constant Name
    // =================================================
    /** CFS_UPLFT_CONTR_DTL_STS_CD */
    public static final String CFS_UPLFT_CONTR_DTL_STS_CD = "CFS_UPLFT_CONTR_DTL_STS_CD";
// START 2016/09/30 N.Arai [QC#12669, MOD]
    /** SPCL_FLT_MDSE_CD */
    public static final String SPCL_FLT_MDSE_CD = "SPCL_FLT_MDSE_CD";
    /** SPCL_AGG_MDSE_CD */
    public static final String SPCL_AGG_MDSE_CD = "SPCL_AGG_MDSE_CD";
    // =================================================
    // Number Constant Name
    // =================================================
    /** DEF_MDL_RULE_BASE */
    public static final String DEF_MDL_RULE_BASE = "DEF_MDL_RULE_BASE";
    /** DEF_MDL_RULE_USAGE */
    public static final String DEF_MDL_RULE_USAGE = "DEF_MDL_RULE_USAGE";
// END 2016/09/30 N.Arai [QC#12669, MOD]
    // =================================================
    // Message Code
    // =================================================
    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";
    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";
    /** Failed to delete "@". */
    public static final String NSAM0033E = "NSAM0033E";
    /** NSAM0045E @ does not exist. */
    public static final String NSAM0045E = "NSAM0045E";
// START 2016/09/30 N.Arai [QC#12669, MOD]
    /** Error Msg : Sales date cannot be obtained. */
    public static final String NSAM0178E = "NSAM0178E";
// END 2016/09/30 N.Arai [QC#12669, MOD]

    // =================================================
    // DB Column Name
    // =================================================
    /** GLBL_CMPY_CD */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** PROC_PGM_ID */
    public static final String PROC_PGM_ID = "PROC_PGM_ID";
    /** DS_BIZ_LAST_PROC_TS */
    public static final String DS_BIZ_LAST_PROC_TS = "DS_BIZ_LAST_PROC_TS";
    /** DS_ACCT_DLR_CD */
    public static final String DS_ACCT_DLR_CD = "DS_ACCT_DLR_CD";
    /** DS_CONTR_PK */
    public static final String DS_CONTR_PK = "DS_CONTR_PK";
    /** DS_CONTR_DTL_PK */
    public static final String DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";
    /** DS_CONTR_NUM */
    public static final String DS_CONTR_NUM = "DS_CONTR_NUM";
    /** DS_CONTR_CATG_CD */
    public static final String DS_CONTR_CATG_CD = "DS_CONTR_CATG_CD";
// START 2016/09/30 N.Arai [QC#12669, MOD]
    /** UPLFT_PRC_METH_CD */
    public static final String UPLFT_PRC_METH_CD = "UPLFT_PRC_METH_CD";
// END 2016/09/30 N.Arai [QC#12669, MOD]
    /** SVC_MACH_MSTR_PK */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";
    /** DS_CONTR_DTL_TP_CD */
    public static final String DS_CONTR_DTL_TP_CD = "DS_CONTR_DTL_TP_CD";
    /** UPLFT_EFF_FROM_DT */
    public static final String UPLFT_EFF_FROM_DT = "UPLFT_EFF_FROM_DT";
    /** BASE_CHRG_FLG */
    public static final String BASE_CHRG_FLG = "BASE_CHRG_FLG";
    /** USG_CHRG_FLG */
    public static final String USG_CHRG_FLG = "USG_CHRG_FLG";
    /** FLEET_LINE_FLG */
    public static final String FLEET_LINE_FLG = "FLEET_LINE_FLG";
    /** SER_NUM */
    public static final String SER_NUM = "SER_NUM";
    /** MDL_ID */
    public static final String MDL_ID = "MDL_ID";
    /** T_MDL_NM */
    public static final String T_MDL_NM = "T_MDL_NM";
    /** DS_CONTR_BLLG_MTR_PK */
    public static final String DS_CONTR_BLLG_MTR_PK = "DS_CONTR_BLLG_MTR_PK";
    /** BLLG_MTR_LB_CD */
    public static final String BLLG_MTR_LB_CD = "BLLG_MTR_LB_CD";
    /** UPLFT_BASE_PRC_UP_RATIO */
    public static final String UPLFT_BASE_PRC_UP_RATIO = "UPLFT_BASE_PRC_UP_RATIO";
    /** UPLFT_MTR_PRC_UP_RATIO */
    public static final String UPLFT_MTR_PRC_UP_RATIO = "UPLFT_MTR_PRC_UP_RATIO";
    /** UPLFT_BASE_FLG */
    public static final String UPLFT_BASE_FLG = "UPLFT_BASE_FLG";
    /** UPLFT_USG_FLG */
    public static final String UPLFT_USG_FLG = "UPLFT_USG_FLG";
    /** DS_CONTR_BASE_USG_NM */
    public static final String DS_CONTR_BASE_USG_NM = "DS_CONTR_BASE_USG_NM";
    /** ROW_NUM */
    public static final String ROW_NUM = "ROW_NUM";
    /** UPTIME */
    public static final String UPTIME = "UPTIME";
    /** DS_CONTR_DTL_STS_CD */
    public static final String DS_CONTR_DTL_STS_CD = "uplftContrDtlStsCdList";
    /** DS_CONTR_DTL_TP */
    public static final String DS_CONTR_DTL_TP_SHELL = "dsContrDtlTpCd";
    /** GLBL_CMPY_CD 01 */
    public static final String GLBL_CMPY_CD_01 = "glblCmpyCd01";
    /** PROC_PGM_ID 01 */
    public static final String PROC_PGM_ID_01 = "procPgmId01";
    // =================================================
    // Sequence Name
    // =================================================
    /** DS_BIZ_PROC_LOG_SQ */
    public static final String DS_BIZ_PROC_LOG_SQ = "DS_BIZ_PROC_LOG_SQ";
    /** CFS_CONTR_PRC_UPLFT_SQ */
    public static final String CFS_CONTR_PRC_UPLFT_SQ = "CFS_CONTR_PRC_UPLFT_SQ";
    // =================================================
    // SSM Parameter Constant Value
    // =================================================
    /** TARGET_DLR_CD */
    public static final String TARGET_DLR_CD = "1000";
    /** BASE */
    public static final String BASE = "BASE";
    /** OVERAGE */
    public static final String OVERAGE = "OVERAGE";
    /** ROW_NUM1 */
    public static final String ROW_NUM1 = "1";
    // =================================================
    // Mail Setting
    // =================================================
    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";
    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = BUSINESS_ID;
    /** template ID */
    public static final String MAIL_TEMPLATE_ID = BUSINESS_ID + "M001";
    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

}
