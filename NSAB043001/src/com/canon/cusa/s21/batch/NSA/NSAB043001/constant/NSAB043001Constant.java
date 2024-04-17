/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB043001.constant;

/**
 * <pre>
 * CFS Contract IB Interface
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/19   Hitachi         O.Okuma         Create          N/A
 * 2016/09/28   Hitachi         N.Arai          Update          QC#12670
 * 2017/05/11   Hitachi         K.Kitachi       Update          QC#18446
 * </pre>
 */
public class NSAB043001Constant {

    /** BUSINESS_ID (NSAB0430) */
    public static final String BUSINESS_ID = "NSAB0430";

    /** PROGRAM_ID(NSAB0430) */
    public static final String PROGRAM_ID = BUSINESS_ID + "01";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    /** @ cannot be obtained. */
    public static final String NSZM0392E = "NSZM0392E";

    /** It failed to register @ Table.[@] */
    public static final String NSAM0469E = "NSAM0469E";

    /** It failed to update @ Table.[@] */
    public static final String NSAM0470E = "NSAM0470E";

    /** It failed to delete @ Table.[@] */
    public static final String NSAM0475E = "NSAM0475E";

    /** Table Name : CFS_CONTR_DTL_PK */
    public static final String CFS_CONTR_DTL_PK = "CFS_CONTR_DTL_PK";

    /** Table Name : CFS_CONTR_DTL */
    public static final String TBL_NM_CFS_CONTR_DTL = "CFS_CONTR_DTL";

    /** Table Name : DS_BIZ_PROC_LOG */
    public static final String TBL_NM_DS_BIZ_PROC_LOG = "DS_BIZ_PROC_LOG";

    /** Column Name : CFS_LEASE_NUM */
    public static final String CFS_LEASE_NUM = "CFS_LEASE_NUM";

    /** Column Name : DS_CONTR_PK */
    public static final String DS_CONTR_PK = "DS_CONTR_PK";

    /** Column Name : DS_CONTR_NUM */
    public static final String DS_CONTR_NUM = "DS_CONTR_NUM";

    /** Column Name : DS_CONTR_DTL_PK */
    public static final String DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";

    /** Column Name : SVC_MACH_MSTR_PK */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** Column Name : SER_NUM */
    public static final String SER_NUM = "SER_NUM";

    /** Column Name : DS_CONTR_CATG_CD */
    public static final String DS_CONTR_CATG_CD = "DS_CONTR_CATG_CD";

    /** Column Name : LOC_NM */
    public static final String LOC_NM = "LOC_NM";

    /** Column Name : DS_ACCT_DLR_CD */
    public static final String DS_ACCT_DLR_CD = "DS_ACCT_DLR_CD";

    /** Column Name : BASE_CHRG_FLG */
    public static final String BASE_CHRG_FLG = "BASE_CHRG_FLG";

    /** Column Name : USG_CHRG_FLG */
    public static final String USG_CHRG_FLG = "USG_CHRG_FLG";

    /** Column Name : DS_CONTR_BLLG_MTR_PK */
    public static final String DS_CONTR_BLLG_MTR_PK = "DS_CONTR_BLLG_MTR_PK";

    /** Column Name : BLLG_MTR_LB_CD */
    public static final String BLLG_MTR_LB_CD = "BLLG_MTR_LB_CD";

    /** Column Name : BASE_BLLG_CYCLE_CD */
    public static final String BASE_BLLG_CYCLE_CD = "BASE_BLLG_CYCLE_CD";

    /** Column Name : BLLG_MTR_BLLG_CYCLE_CD */
    public static final String BLLG_MTR_BLLG_CYCLE_CD = "BLLG_MTR_BLLG_CYCLE_CD";

    /** Column Name : BASE_PRC_DEAL_AMT */
    public static final String BASE_PRC_DEAL_AMT = "BASE_PRC_DEAL_AMT";

    /** Column Name : CONTR_EFF_FROM_DT */
    public static final String CONTR_EFF_FROM_DT = "CONTR_EFF_FROM_DT";

    /** Column Name : CONTR_EFF_THRU_DT */
    public static final String CONTR_EFF_THRU_DT = "CONTR_EFF_THRU_DT";

    /** Column Name : CONTR_VRSN_EFF_FROM_DT */
    public static final String CONTR_VRSN_EFF_FROM_DT = "CONTR_VRSN_EFF_FROM_DT";

    /** Column Name : CONTR_VRSN_EFF_THRU_DT */
    public static final String CONTR_VRSN_EFF_THRU_DT = "CONTR_VRSN_EFF_THRU_DT";

    /** Column Name : CONTR_MTR_MULT_RATE */
    public static final String CONTR_MTR_MULT_RATE = "CONTR_MTR_MULT_RATE";

    /** Column Name : COLOR_MTR_FLG */
    public static final String COLOR_MTR_FLG = "COLOR_MTR_FLG";

    /** Column Name : CONTR_CLO_DT */
    public static final String CONTR_CLO_DT = "CONTR_CLO_DT";

    /** Column Name : DS_CONTR_STS_CD */
    public static final String DS_CONTR_STS_CD = "DS_CONTR_STS_CD";

    /** Column Name : DS_CONTR_CATG_ABBR_NM */
    public static final String DS_CONTR_CATG_ABBR_NM = "DS_CONTR_CATG_ABBR_NM";

    /** Column Name : EZUPTIME 01 */
    public static final String EZUPTIME_01 = "EZUPTIME_01";

    /** Column Name : EZUPTIME 02 */
    public static final String EZUPTIME_02 = "EZUPTIME_02";

    /** Column Name : EZUPTIME 03 */
    public static final String EZUPTIME_03 = "EZUPTIME_03";

    /** Column Name : EZUPTIME 04 */
    public static final String EZUPTIME_04 = "EZUPTIME_04";

    /** Column Name : EZUPTIME 05 */
    public static final String EZUPTIME_05 = "EZUPTIME_05";

    /** Column Name : EZUPTIME 06 */
    public static final String EZUPTIME_06 = "EZUPTIME_06";

    /** Column Name : EZUPTIME 07 */
    public static final String EZUPTIME_07 = "EZUPTIME_07";

    /** Column Name : EZUPTIME 08 */
    public static final String EZUPTIME_08 = "EZUPTIME_08";

    /** Column Name : DS_BIZ_PROC_LOG_PK */
    public static final String DS_BIZ_PROC_LOG_PK = "DS_BIZ_PROC_LOG_PK";

    /** Column Name : PROC_PGM_ID */
    public static final String PROC_PGM_ID = "PROC_PGM_ID";

    /** VAR_CHAR_CONST : EXCL_CFS_CONTR_STS */
    public static final String EXCL_CFS_CONTR_STS = "EXCL_CFS_CONTR_STS";

    /** VAR_CHAR_CONST : EXCL_CFS_CONTR_DTL_STS */
    public static final String EXCL_CFS_CONTR_DTL_STS = "EXCL_CFS_CONTR_DTL_STS";

    /** MTR_TP_IND_TXT : BLACK AND WHITE */
    public static final String MTR_TP_IND_TXT_B = "B";

    /** Minimum Value of UPTIME */
    public static final String TS_ZERO = "00000000000000000";

    /** LENGTH : 8 */
    public static final int LENGTH_8 = 8;

    /** MTR_TP_IND_TXT : COLOR */
    public static final String MTR_TP_IND_TXT_C = "C";

    /** Mail Group ID (From) */
    public static final String MAIL_GRP_ID_FROM  = "FROM0003";

    /** Mail Group KEY (From) */
    public static final String MAIL_GROUP_KEY_FROM  = "NS";

    /** Set ID: Mail Template ID */
    public static final String SET_MAIL_TEMPLATE_ID = "NSAB0430M001";

    /** Error Massage : CRLF */
    public static final String ERR_MSG_CRLF = "\r\n";
    /** Mail Item (${batchId}) */
    public static final String MAIL_ITEM_BATCH_ID = "batchId";

    /** Mail Item (${errDate}) */
    public static final String MAIL_ITEM_ERR_DATE = "errDate";

    /** Mail Item (${message}) */
    public static final String MAIL_ITEM_ERR_MSG = "message";

// START 2016/09/28 N.Arai [QC#12670, ADD]
    /** CFS_PROC_CPLT_STS_CD(COMPLETED) : CFS_BAT_PROC_STS_CD */
    public static final String CFS_PROC_CPLT_STS_CD = "CFS_PROC_CPLT_STS_CD";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";
// END 2016/09/28 N.Arai [QC#12670, ADD]

    // START 2017/05/11 K.Kitachi [QC#18446, ADD]
    /** VAR_CHAR_CONST : CSA_DEALER_CODE */
    public static final String CSA_DEALER_CODE = "CSA_DEALER_CODE";

    /** COMMA : , */
    public static final String COMMA = ",";
    // END 2017/05/11 K.Kitachi [QC#18446, ADD]
}
