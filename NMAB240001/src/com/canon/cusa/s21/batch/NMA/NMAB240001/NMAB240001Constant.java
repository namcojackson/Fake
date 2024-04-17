/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB240001;


/**
 * <pre>
 * NMAB2400 Org Effective Data Control Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/06/20   Hitach          A.Kohinata      Update          CSA-QC#10424
 * 2016/07/05   Fujitsu         R.Nakamura      Update          QC#11081
 * 2017/03/07   Fujitsu         M.Ohno          Update          S21_NA#17760
 * 2019/02/15   Fujitsu         Hd.Sugawara     Update          QC#29668
 *</pre>
 */
public class NMAB240001Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAB2400";

    /** DEFAULT_COMMIT_UNIT */
    public static final int DEFAULT_COMMIT_UNIT = 1000;

    /** MAX_END_DATE */
    public static final String MAX_END_DATE = "99991231";

    /** ZERO */
    public static final Integer ZERO = Integer.valueOf(0);

    /** Global Company Code **/
    public static final String NAME_FOR_MESSAGE_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** Mode **/
    public static final String NAME_FOR_MESSAGE_MODE = "Mode";

    /** NMAB2400 Commit Count**/
    public static final String NMAB2400_COMMIT_COUNT = "NMAB2400_COMMIT_COUNT";

    /** Index for substring **/
    public static final int INDEX_4 = 4;

    /** Index for substring **/
    public static final int INDEX_6 = 6;

    /** Process Mode: Daily **/
    public static final String MODE_DAILY = "0";

    /** Process Mode: Night **/
    public static final String MODE_NIGHT = "1";

    /** YYYYMM **/
    public static final int YYYYMM = 6;

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** Data insert failure. (table name is [@]) */
    public static final String MMAM0003E = "MMAM0003E";

    /** Data update failure. (table name is [@]) */
    public static final String MMAM0004E = "MMAM0004E";

    /** Data delete failure. (table name is [@]) */
    public static final String MMAM0005E = "MMAM0005E";


    /** Table Name: DS_ORG_UNIT **/
    public static final String TBL_NM_DS_ORG_UNIT = "DS_ORG_UNIT";

    /** Table Name: DS_ORG_RELN **/
    public static final String TBL_NM_DS_ORG_RELN = "DS_ORG_RELN";

    /** Table Name: DS_ORG_RESRC_RELN **/
    public static final String TBL_NM_DS_ORG_RESRC_RELN = "DS_ORG_RESRC_RELN";

    /** Table Name: ORG_FUNC_ASG **/
    public static final String TBL_NM_ORG_FUNC_ASG = "ORG_FUNC_ASG";

    /** Table Name: ORG_TOC_CHNG_RQST **/
    public static final String TBL_NM_ORG_TOC_CHNG_RQST = "ORG_TOC_CHNG_RQST";

    /** Table Name: TOC **/
    public static final String TBL_NM_TOC = "TOC";

    /** Table Name: ORG_STRU **/
    public static final String TBL_NM_ORG_STRU = "ORG_STRU";

    /** Table Name: S21_ORG **/
    public static final String TBL_NM_S21_ORG = "S21_ORG";

    /** Table Name: TECH_MSTR **/
    public static final String TBL_NM_TECH_MSTR = "TECH_MSTR";

    /** Table Name: TOC_ORG_MGR_INFO **/
    public static final String TBL_NM_TOC_ORG_MGR_INFO = "TOC_ORG_MGR_INFO";

    // Add Start 2019/02/15 QC#29668
    /** Table Name: TOC_ORG_MGR_INFO **/
    public static final String TBL_NM_TOC_ORG_STRU_RELN = "TOC_ORG_STRU_RELN";

    /** Table Name: DS_ORG_RESRC_REV **/
    public static final String TBL_NM_DS_ORG_RESRC_REV = "DS_ORG_RESRC_REV";
    // Add End 2019/02/15 QC#29668

    /** Parameter : B (Batch) **/
    public static final String PARAM_BATCH_B = "B";

    /** Prefix: 1 **/
    public static final String PREFIX_1 = "FIRST";

    /** Prefix: 2 **/
    public static final String PREFIX_2 = "SCD";

    /** Prefix: 3 **/
    public static final String PREFIX_3 = "THIRD";

    /** Prefix: 4 **/
    public static final String PREFIX_4 = "FRTH";

    /** Prefix: 5 **/
    public static final String PREFIX_5 = "FIFTH";

    /** Prefix: 6 **/
    public static final String PREFIX_6 = "SIXTH";

    /** Prefix: 7 **/
    public static final String PREFIX_7 = "SVNTH";

    /** Prefix: 8 **/
    public static final String PREFIX_8 = "EIGHTH";

    /** Prefix: 9 **/
    public static final String PREFIX_9 = "NINTH";

    /** Prefix: 10 **/
    public static final String PREFIX_10 = "TENTH";

    /** Prefix: 11 **/
    public static final String PREFIX_11 = "ELVTH";

    /** FIRST_LAYER **/
    public static final int FIRST_LAYER = 1;

    /** SECOND_LAYER **/
    public static final int SECOND_LAYER = 2;

    /** THIRD_LAYER **/
    public static final int THIRD_LAYER = 3;

    /** FOURTH_LAYER **/
    public static final int FOURTH_LAYER = 4;

    /** FIFTH_LAYER **/
    public static final int FIFTH_LAYER = 5;

    /** SIXTH_LAYER **/
    public static final int SIXTH_LAYER = 6;

    /** SEVENTH_LAYER **/
    public static final int SEVENTH_LAYER = 7;

    /** EIGHTH_LAYER **/
    public static final int EIGHTH_LAYER = 8;

    /** NINTH_LAYER **/
    public static final int NINTH_LAYER = 9;

    /** TENTH_LAYER **/
    public static final int TENTH_LAYER = 10;

    /** ELEVENTH_LAYER **/
    public static final int ELEVENTH_LAYER = 11;

    /** Column Name: ORG_CD **/
    public static final String COLUMN_NM_ORG_CD = "ORG_CD";

    /** Column Name: ORG_NM **/
    public static final String COLUMN_NM_ORG_NM = "ORG_NM";

    /** Column Name: PSN_CD **/
    public static final String COLUMN_NM_PSN_CD = "PSN_CD";

    /** Column Name: TOC_CD **/
    public static final String COLUMN_NM_TOC_CD = "TOC_CD";

    /** Column Name: TOC_NM **/
    public static final String COLUMN_NM_TOC_NM = "TOC_NM";

    /** Column Name: PRNT_ORG_CD **/
    public static final String COLUMN_NM_PRNT_ORG_CD = "PRNT_ORG_CD";

    /** Column Name: START_DT **/
    public static final String COLUMN_NM_START_DT = "START_DT";

    /** Column Name: END_DT **/
    public static final String COLUMN_NM_END_DT = "END_DT";

    /** Column Name: EFF_FROM_DT **/
    public static final String COLUMN_NM_EFF_FROM_DT = "EFF_FROM_DT";

    /** Column Name: EFF_THRU_DT **/
    public static final String COLUMN_NM_EFF_THRU_DT = "EFF_THRU_DT";

    /** Column Name: ORG_FUNC_ROLE_TP_CD **/
    public static final String COLUMN_NM_ORG_FUNC_ROLE_TP_CD = "ORG_FUNC_ROLE_TP_CD";

    /** Column Name: COA_CMPY_CD **/
    public static final String COLUMN_NM_COA_CMPY_CD = "COA_CMPY_CD";

    /** Column Name: COA_BR_CD **/
    public static final String COLUMN_NM_COA_BR_CD = "COA_BR_CD";

    /** Column Name: COA_EXTN_CD **/
    public static final String COLUMN_NM_COA_EXTN_CD = "COA_EXTN_CD";

    /** Column Name: COA_CC_CD **/
    public static final String COLUMN_NM_COA_CC_CD = "COA_CC_CD";

    /** Column Name: COA_PROD_CD **/
    public static final String COLUMN_NM_COA_PROD_CD = "COA_PROD_CD";

    /** Column Name: COA_CH_CD **/
    public static final String COLUMN_NM_COA_CH_CD = "COA_CH_CD";

    /** Column Name: ORG_CHNG_RQST_PK **/
    public static final String COLUMN_NM_ORG_CHNG_RQST_PK = "ORG_CHNG_RQST_PK";

    /** Column Name: PSN_FIRST_NM **/
    public static final String COLUMN_NM_PSN_FIRST_NM = "PSN_FIRST_NM";

    /** Column Name: PSN_LAST_NM **/
    public static final String COLUMN_NM_PSN_LAST_NM = "PSN_LAST_NM";

    /** Column Name: ORG_LAYER_NUM **/
    public static final String COLUMN_NM_ORG_LAYER_NUM = "ORG_LAYER_NUM";

    /** Column Name: ORG_TIER_CD **/
    public static final String COLUMN_NM_ORG_TIER_CD = "ORG_TIER_CD";

    /** Column Name: BIZ_AREA_ORG_CD **/
    public static final String COLUMN_NM_BIZ_AREA_ORG_CD = "BIZ_AREA_ORG_CD";

    /** Column Name: JOB_TTL_CD **/
    public static final String COLUMN_NM_JOB_TTL_CD = "JOB_TTL_CD";

    /** Column Name: GNRN_TP_CD **/
    public static final String COLUMN_NM_GNRN_TP_CD = "GNRN_TP_CD";

    /** Column Name: FIRST_ORG_CD **/
    public static final String COLUMN_NM_FIRST_ORG_CD = "FIRST_ORG_CD";

    /** Column Name: FIRST_ORG_NM **/
    public static final String COLUMN_NM_FIRST_ORG_NM = "FIRST_ORG_NM";

    /** Column Name: SCD_ORG_CD **/
    public static final String COLUMN_NM_SCD_ORG_CD = "SCD_ORG_CD";

    /** Column Name: SCD_ORG_NM **/
    public static final String COLUMN_NM_SCD_ORG_NM = "SCD_ORG_NM";

    /** Column Name: THIRD_ORG_CD **/
    public static final String COLUMN_NM_THIRD_ORG_CD = "THIRD_ORG_CD";

    /** Column Name: THIRD_ORG_NM **/
    public static final String COLUMN_NM_THIRD_ORG_NM = "THIRD_ORG_NM";

    /** Column Name: FRTH_ORG_CD **/
    public static final String COLUMN_NM_FRTH_ORG_CD = "FRTH_ORG_CD";

    /** Column Name: FRTH_ORG_NM **/
    public static final String COLUMN_NM_FRTH_ORG_NM = "FRTH_ORG_NM";

    /** Column Name: FIFTH_ORG_CD **/
    public static final String COLUMN_NM_FIFTH_ORG_CD = "FIFTH_ORG_CD";

    /** Column Name: FIFTH_ORG_NM **/
    public static final String COLUMN_NM_FIFTH_ORG_NM = "FIFTH_ORG_NM";

    /** Column Name: SIXTH_ORG_CD **/
    public static final String COLUMN_NM_SIXTH_ORG_CD = "SIXTH_ORG_CD";

    /** Column Name: SIXTH_ORG_NM **/
    public static final String COLUMN_NM_SIXTH_ORG_NM = "SIXTH_ORG_NM";

    /** Column Name: SVNTH_ORG_CD **/
    public static final String COLUMN_NM_SVNTH_ORG_CD = "SVNTH_ORG_CD";

    /** Column Name: SVNTH_ORG_NM **/
    public static final String COLUMN_NM_SVNTH_ORG_NM = "SVNTH_ORG_NM";

    /** Column Name: EIGHTH_ORG_CD **/
    public static final String COLUMN_NM_EIGHTH_ORG_CD = "EIGHTH_ORG_CD";

    /** Column Name: EIGHTH_ORG_NM **/
    public static final String COLUMN_NM_EIGHTH_ORG_NM = "EIGHTH_ORG_NM";

    /** Column Name: NINTH_ORG_CD **/
    public static final String COLUMN_NM_NINTH_ORG_CD = "NINTH_ORG_CD";

    /** Column Name: NINTH_ORG_NM **/
    public static final String COLUMN_NM_NINTH_ORG_NM = "NINTH_ORG_NM";

    /** Column Name: TENTH_ORG_CD **/
    public static final String COLUMN_NM_TENTH_ORG_CD = "TENTH_ORG_CD";

    /** Column Name: TENTH_ORG_NM **/
    public static final String COLUMN_NM_TENTH_ORG_NM = "TENTH_ORG_NM";

    /** Column Name: ELVTH_ORG_CD **/
    public static final String COLUMN_NM_ELVTH_ORG_CD = "ELVTH_ORG_CD";

    /** Column Name: ELVTH_ORG_NM **/
    public static final String COLUMN_NM_ELVTH_ORG_NM = "ELVTH_ORG_NM";

    /** Column Name: FIRST_ORG_TIER_CD **/
    public static final String COLUMN_NM_FIRST_ORG_TIER_CD = "FIRST_ORG_TIER_CD";

    /** Column Name: SCD_ORG_TIER_CD **/
    public static final String COLUMN_NM_SCD_ORG_TIER_CD  = "SCD_ORG_TIER_CD";

    /** Column Name: THIRD_ORG_TIER_CD **/
    public static final String COLUMN_NM_THIRD_ORG_TIER_CD = "THIRD_ORG_TIER_CD";

    /** Column Name: FRTH_ORG_TIER_CD **/
    public static final String COLUMN_NM_FRTH_ORG_TIER_CD  = "FRTH_ORG_TIER_CD";

    /** Column Name: FIFTH_ORG_TIER_CD **/
    public static final String COLUMN_NM_FIFTH_ORG_TIER_CD = "FIFTH_ORG_TIER_CD";

    /** Column Name: SIXTH_ORG_TIER_CD **/
    public static final String COLUMN_NM_SIXTH_ORG_TIER_CD = "SIXTH_ORG_TIER_CD";

    /** Column Name: SVNTH_ORG_TIER_CD **/
    public static final String COLUMN_NM_SVNTH_ORG_TIER_CD = "SVNTH_ORG_TIER_CD";

    /** Column Name: EIGHTH_ORG_TIER_CD **/
    public static final String COLUMN_NM_EIGHTH_ORG_TIER_CD = "EIGHTH_ORG_TIER_CD";

    /** Column Name: NINTH_ORG_TIER_CD **/
    public static final String COLUMN_NM_NINTH_ORG_TIER_CD = "NINTH_ORG_TIER_CD";

    /** Column Name: EIGHTH_ORG_TIER_CD **/
    public static final String COLUMN_NM_TENTH_ORG_TIER_CD = "TENTH_ORG_TIER_CD";

    /** Column Name: ELVTH_ORG_TIER_CD **/
    public static final String COLUMN_NM_ELVTH_ORG_TIER_CD = "ELVTH_ORG_TIER_CD";

    // 2016/06/20 CSA-QC#10424 Add Start
    /** Column Name: TOC_REQ_FLG **/
    public static final String COLUMN_TOC_REQ_FLG = "TOC_REQ_FLG";
    // 2016/06/20 CSA-QC#10424 Add End

    // Add Start 2016/07/05 QC#11081
    /** Column Name: RGTN_STS_CD **/
    public static final String COLUMN_RGTN_STS_CD = "RGTN_STS_CD";
    // Add End 2016/07/05 QC#11081

    // 2017/03/07 S21_NA#17760 Add Start
    /** Column Name: DS_ORG_RELN_PK **/
    public static final String COLUMN_NM_DS_ORG_RELN_PK = "DS_ORG_RELN_PK";
    // 2017/03/07 S21_NA#17760 Add End

    // Add Start 2019/02/15 QC#29668
    /** Column Name: ORG_FUNC_ASG.EFF_FROM_DT **/
    public static final String COLUMN_NM_EFF_FROM_DT_ASG = "EFF_FROM_DT_ASG";

    /** Column Name: ORG_FUNC_ASG.EFF_THRU_DT **/
    public static final String COLUMN_NM_EFF_THRU_DT_ASG = "EFF_THRU_DT_ASG";

    /** Column Name: TOC_ORG_STRU_RELN.EFF_FROM_DT **/
    public static final String COLUMN_NM_EFF_FROM_DT_RELN = "EFF_FROM_DT_RELN";

    /** Column Name: TOC_ORG_STRU_RELN.EFF_THRU_DT **/
    public static final String COLUMN_NM_EFF_THRU_DT_RELN = "EFF_THRU_DT_RELN";

    /** Column Name: ORG_STRU_TP_CD **/
    public static final String COLUMN_NM_ORG_STRU_TP_CD = "ORG_STRU_TP_CD";

    /** Column Name: REV_ACCT_TP_CD **/
    public static final String COLUMN_NM_REV_ACCT_TP_CD = "REV_ACCT_TP_CD";

    /** COLUMN_LEN_TOC_NM */
    public static final int COLUMN_LEN_TOC_NM = 50;
    // Add End 2019/02/15 QC#29668

    // 2017/03/17 S21_NA#18072 Add Start
    /** TECH_NM length **/
    public static final int TECH_NM_LENGTH = 45;
    // 2017/03/17 S21_NA#18072 Add End

    // Add Start 2019/02/15 QC#29668
    /** TOC_CD **/
    public static final String BIZAPL_TOCCDKEY = "TOC_CD";
    // Add End 2019/02/15 QC#29668
}
