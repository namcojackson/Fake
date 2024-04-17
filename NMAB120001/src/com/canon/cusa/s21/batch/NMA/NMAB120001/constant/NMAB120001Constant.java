/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB120001.constant;

/**
 *<pre>
 * Service Organization Dimension Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/25/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NMAB120001Constant {

    /** fetchSize */
    public static final int FETCH_SIZE = 1000;

    /** commitCount */
    public static final int DEFAULT_COMMIT_COUNT = 1000;

    /** [@] has no value. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Failed to insert "@". */
    public static final String ZZZM9012E = "ZZZM9012E";

    /** Failed to delete "@". */
    public static final String ZZZM9014E = "ZZZM9014E";

    /** message Item: GlobalCompanyCode. */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: Batch Process Date. */
    public static final String MSG_ITEM_BATCH_PROC_DATE = "Batch Process Date";

    /** SQL PARAM NAME */
    /** . */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** . */
    public static final String PARAM_TRGT_DT = "trgtDt";
    /** . */
    public static final String PARAM_BOS = "bos";
    /** . */
    public static final String PARAM_PAST = "past";
    /** . */
    public static final String PARAM_CURRENT = "current";
    /** . */
    public static final String PARAM_FUTURE = "future";
    /** . */
    public static final String PARAM_SERVICE = "service";

    /** DB COLUMN NAME */
    /** . */
    public static final String TECH_TOC_CD = "TECH_TOC_CD";
    /** . */
    public static final String TOC_CD = "TOC_CD";
    /** . */
    public static final String TECH_NM = "TECH_NM";
    /** . */
    public static final String PSN_NUM = "PSN_NUM";
    /** . */
    public static final String RESRC_EFF_FROM_DT = "RESRC_EFF_FROM_DT";
    /** . */
    public static final String RESRC_EFF_THRU_DT = "RESRC_EFF_THRU_DT";
    /** . */
    public static final String FLD_SVC_MGR_CD = "FLD_SVC_MGR_CD";
    /** . */
    public static final String FLD_SVC_MGR_NM = "FLD_SVC_MGR_NM";
    /** . */
    public static final String REV_ACCT_TP_CD = "REV_ACCT_TP_CD";
    /** . */
    public static final String REV_ACCT_TP_DESC_TXT = "REV_ACCT_TP_DESC_TXT";
    /** . */
    public static final String COA_CMPY_CD = "COA_CMPY_CD";
    /** . */
    public static final String COA_CMPY_DESC_TXT = "COA_CMPY_DESC_TXT";
    /** . */
    public static final String COA_EXTN_CD = "COA_EXTN_CD";
    /** . */
    public static final String COA_EXTN_DESC_TXT = "COA_EXTN_DESC_TXT";
    /** . */
    public static final String COA_BR_CD = "COA_BR_CD";
    /** . */
    public static final String COA_BR_DESC_TXT = "COA_BR_DESC_TXT";
    /** . */
    public static final String COA_CC_CD = "COA_CC_CD";
    /** . */
    public static final String COA_CC_DESC_TXT = "COA_CC_DESC_TXT";
    /** . */
    public static final String RESRC_REV_EFF_FROM_DT = "RESRC_REV_EFF_FROM_DT";
    /** . */
    public static final String RESRC_REV_EFF_THRU_DT = "RESRC_REV_EFF_THRU_DT";
    /** . */
    public static final String FIRST_ORG_MGR_PSN_CD = "FIRST_ORG_MGR_PSN_CD";
    /** . */
    public static final String FIRST_ORG_MGR_TOC_CD = "FIRST_ORG_MGR_TOC_CD";
    /** . */
    public static final String FIRST_ORG_MGR_FIRST_NM = "FIRST_ORG_MGR_FIRST_NM";
    /** . */
    public static final String FIRST_ORG_MGR_LAST_NM = "FIRST_ORG_MGR_LAST_NM";
    /** . */
    public static final String SCD_ORG_MGR_PSN_CD = "SCD_ORG_MGR_PSN_CD";
    /** . */
    public static final String SCD_ORG_MGR_TOC_CD = "SCD_ORG_MGR_TOC_CD";
    /** . */
    public static final String SCD_ORG_MGR_FIRST_NM = "SCD_ORG_MGR_FIRST_NM";
    /** . */
    public static final String SCD_ORG_MGR_LAST_NM = "SCD_ORG_MGR_LAST_NM";
    /** . */
    public static final String THIRD_ORG_MGR_PSN_CD = "THIRD_ORG_MGR_PSN_CD";
    /** . */
    public static final String THIRD_ORG_MGR_TOC_CD = "THIRD_ORG_MGR_TOC_CD";
    /** . */
    public static final String THIRD_ORG_MGR_FIRST_NM = "THIRD_ORG_MGR_FIRST_NM";
    /** . */
    public static final String THIRD_ORG_MGR_LAST_NM = "THIRD_ORG_MGR_LAST_NM";
    /** . */
    public static final String FRTH_ORG_MGR_PSN_CD = "FRTH_ORG_MGR_PSN_CD";
    /** . */
    public static final String FRTH_ORG_MGR_TOC_CD = "FRTH_ORG_MGR_TOC_CD";
    /** . */
    public static final String FRTH_ORG_MGR_FIRST_NM = "FRTH_ORG_MGR_FIRST_NM";
    /** . */
    public static final String FRTH_ORG_MGR_LAST_NM = "FRTH_ORG_MGR_LAST_NM";
    /** . */
    public static final String FIFTH_ORG_MGR_PSN_CD = "FIFTH_ORG_MGR_PSN_CD";
    /** . */
    public static final String FIFTH_ORG_MGR_TOC_CD = "FIFTH_ORG_MGR_TOC_CD";
    /** . */
    public static final String FIFTH_ORG_MGR_FIRST_NM = "FIFTH_ORG_MGR_FIRST_NM";
    /** . */
    public static final String FIFTH_ORG_MGR_LAST_NM = "FIFTH_ORG_MGR_LAST_NM";
    /** . */
    public static final String SIXTH_ORG_MGR_PSN_CD = "SIXTH_ORG_MGR_PSN_CD";
    /** . */
    public static final String SIXTH_ORG_MGR_TOC_CD = "SIXTH_ORG_MGR_TOC_CD";
    /** . */
    public static final String SIXTH_ORG_MGR_FIRST_NM = "SIXTH_ORG_MGR_FIRST_NM";
    /** . */
    public static final String SIXTH_ORG_MGR_LAST_NM = "SIXTH_ORG_MGR_LAST_NM";
    /** . */
    public static final String SVNTH_ORG_MGR_PSN_CD = "SVNTH_ORG_MGR_PSN_CD";
    /** . */
    public static final String SVNTH_ORG_MGR_TOC_CD = "SVNTH_ORG_MGR_TOC_CD";
    /** . */
    public static final String SVNTH_ORG_MGR_FIRST_NM = "SVNTH_ORG_MGR_FIRST_NM";
    /** . */
    public static final String SVNTH_ORG_MGR_LAST_NM = "SVNTH_ORG_MGR_LAST_NM";
    /** . */
    public static final String EIGHTH_ORG_MGR_PSN_CD = "EIGHTH_ORG_MGR_PSN_CD";
    /** . */
    public static final String EIGHTH_ORG_MGR_TOC_CD = "EIGHTH_ORG_MGR_TOC_CD";
    /** . */
    public static final String EIGHTH_ORG_MGR_FIRST_NM = "EIGHTH_ORG_MGR_FIRST_NM";
    /** . */
    public static final String EIGHTH_ORG_MGR_LAST_NM = "EIGHTH_ORG_MGR_LAST_NM";
    /** . */
    public static final String NINTH_ORG_MGR_PSN_CD = "NINTH_ORG_MGR_PSN_CD";
    /** . */
    public static final String NINTH_ORG_MGR_TOC_CD = "NINTH_ORG_MGR_TOC_CD";
    /** . */
    public static final String NINTH_ORG_MGR_FIRST_NM = "NINTH_ORG_MGR_FIRST_NM";
    /** . */
    public static final String NINTH_ORG_MGR_LAST_NM = "NINTH_ORG_MGR_LAST_NM";
    /** . */
    public static final String TENTH_ORG_MGR_PSN_CD = "TENTH_ORG_MGR_PSN_CD";
    /** . */
    public static final String TENTH_ORG_MGR_TOC_CD = "TENTH_ORG_MGR_TOC_CD";
    /** . */
    public static final String TENTH_ORG_MGR_FIRST_NM = "TENTH_ORG_MGR_FIRST_NM";
    /** . */
    public static final String TENTH_ORG_MGR_LAST_NM = "TENTH_ORG_MGR_LAST_NM";
    /** . */
    public static final String ELVTH_ORG_MGR_PSN_CD = "ELVTH_ORG_MGR_PSN_CD";
    /** . */
    public static final String ELVTH_ORG_MGR_TOC_CD = "ELVTH_ORG_MGR_TOC_CD";
    /** . */
    public static final String ELVTH_ORG_MGR_FIRST_NM = "ELVTH_ORG_MGR_FIRST_NM";
    /** . */
    public static final String ELVTH_ORG_MGR_LAST_NM = "ELVTH_ORG_MGR_LAST_NM";
    /** . */
    public static final String RESRC_ASG_EFF_FROM_DT = "RESRC_ASG_EFF_FROM_DT";
    /** . */
    public static final String RESRC_ASG_EFF_THRU_DT = "RESRC_ASG_EFF_THRU_DT";
    /** . */
    public static final String FIRST_ORG_CD = "FIRST_ORG_CD";
    /** . */
    public static final String FIRST_ORG_NM = "FIRST_ORG_NM";
    /** . */
    public static final String SCD_ORG_CD = "SCD_ORG_CD";
    /** . */
    public static final String SCD_ORG_NM = "SCD_ORG_NM";
    /** . */
    public static final String THIRD_ORG_CD = "THIRD_ORG_CD";
    /** . */
    public static final String THIRD_ORG_NM = "THIRD_ORG_NM";
    /** . */
    public static final String FRTH_ORG_CD = "FRTH_ORG_CD";
    /** . */
    public static final String FRTH_ORG_NM = "FRTH_ORG_NM";
    /** . */
    public static final String FIFTH_ORG_CD = "FIFTH_ORG_CD";
    /** . */
    public static final String FIFTH_ORG_NM = "FIFTH_ORG_NM";
    /** . */
    public static final String SIXTH_ORG_CD = "SIXTH_ORG_CD";
    /** . */
    public static final String SIXTH_ORG_NM = "SIXTH_ORG_NM";
    /** . */
    public static final String SVNTH_ORG_CD = "SVNTH_ORG_CD";
    /** . */
    public static final String SVNTH_ORG_NM = "SVNTH_ORG_NM";
    /** . */
    public static final String EIGHTH_ORG_CD = "EIGHTH_ORG_CD";
    /** . */
    public static final String EIGHTH_ORG_NM = "EIGHTH_ORG_NM";
    /** . */
    public static final String NINTH_ORG_CD = "NINTH_ORG_CD";
    /** . */
    public static final String NINTH_ORG_NM = "NINTH_ORG_NM";
    /** . */
    public static final String TENTH_ORG_CD = "TENTH_ORG_CD";
    /** . */
    public static final String TENTH_ORG_NM = "TENTH_ORG_NM";
    /** . */
    public static final String ELVTH_ORG_CD = "ELVTH_ORG_CD";
    /** . */
    public static final String ELVTH_ORG_NM = "ELVTH_ORG_NM";
    /** . */
    public static final String FIRST_ORG_TIER_CD = "FIRST_ORG_TIER_CD";
    /** . */
    public static final String SCD_ORG_TIER_CD = "SCD_ORG_TIER_CD";
    /** . */
    public static final String THIRD_ORG_TIER_CD = "THIRD_ORG_TIER_CD";
    /** . */
    public static final String FRTH_ORG_TIER_CD = "FRTH_ORG_TIER_CD";
    /** . */
    public static final String FIFTH_ORG_TIER_CD = "FIFTH_ORG_TIER_CD";
    /** . */
    public static final String SIXTH_ORG_TIER_CD = "SIXTH_ORG_TIER_CD";
    /** . */
    public static final String SVNTH_ORG_TIER_CD = "SVNTH_ORG_TIER_CD";
    /** . */
    public static final String EIGHTH_ORG_TIER_CD = "EIGHTH_ORG_TIER_CD";
    /** . */
    public static final String NINTH_ORG_TIER_CD = "NINTH_ORG_TIER_CD";
    /** . */
    public static final String TENTH_ORG_TIER_CD = "TENTH_ORG_TIER_CD";
    /** . */
    public static final String ELVTH_ORG_TIER_CD = "ELVTH_ORG_TIER_CD";
    /** . */
    public static final String ORG_FUNC_ROLE_TP_CD = "ORG_FUNC_ROLE_TP_CD";
    /** . */
    public static final String ORG_FUNC_ROLE_TP_DESC_TXT = "ORG_FUNC_ROLE_TP_DESC_TXT";
}
