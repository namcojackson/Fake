/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL1110.constant;

/**
 * <pre>
 * AP Invoice Maintenance Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   CUSA            Y.Aikawa        Create          N/A
 * 2016/08/04   Hitachi         J.Kim           Update          QC#12712
 * 2016/08/05   Fujitsu         T.Murai         Update          QC#12692,12866
 * 2016/08/23   Fujitsu         T.Murai         Update          QC#12830,12729
 * 2017/03/16   Hitachi         E.Kameishi      Update          QC#14205
 * 2017/11/13   CITS            K.Ogino         Update          QC#21686
 * </pre>
 */
public interface NFBL1110Constant {

    /** Empty Value */
    static final String EMPTY_STRING = "";
    /** yyyyMMdd */
    static final String YYYYMMDD = "yyyyMMdd";
    /** yyyyMMddHHmmSSsss */
    static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmSSss";
    /** yyyyMMddHHmmSSsss */
    // Mod 2016/08/23 QC#12729
    // static final String STR_20_DIGIT_ZERO = "0000000000000";
    static final String STR_13_DIGIT_ZERO = "0000000000000";

    /** Record Count for one serial number */
    // Del 2016/08/23 QC#12729
//    static final int INT_6 = 6;

    // Oracle Sequence Name
    /** CM_MAINT_INV_BAT_SQ */
    static final String CM_MAINT_INV_BAT_SQ = "CM_MAINT_INV_BAT_SQ";

    // AP_DS_WF_STS_CD
    /** AP_DS_WF_STS_CD 10 (Pending Approval) */
    static final String AP_DS_WF_STS_CD_00 = "00";
    /** AP_DS_WF_STS_CD 11 (Rejected) */
    static final String AP_DS_WF_STS_CD_09 = "09";

    // AP_MAINT_INV_STS_CD
    /** AP_MAINT_INV_STS_CD 00 (Invoice Entered) */
    static final String AP_MAINT_INV_STS_CD_00 = "00";
    /** AP_MAINT_INV_STS_CD 10 (Invoice Batch Entry Completed) */
    static final String AP_MAINT_INV_STS_CD_10 = "10";
    /** AP_MAINT_INV_STS_CD 12 (Pending Workflow Approval) */
    static final String AP_MAINT_INV_STS_CD_12 = "12";
    /** AP_MAINT_INV_STS_CD 20 (Approved) */
    static final String AP_MAINT_INV_STS_CD_20 = "20";
    /** AP_MAINT_INV_STS_CD 30 (ARCS AP Invoice Created) */
    static final String AP_MAINT_INV_STS_CD_30 = "30";
    /** AP_MAINT_INV_STS_CD 50 (Paid) */
    static final String AP_MAINT_INV_STS_CD_50 = "50";
    /** AP_MAINT_INV_STS_CD 90 (Cancelled) */
    static final String AP_MAINT_INV_STS_CD_90 = "90";
    /** AP_MAINT_INV_STS_CD 95 (Voided) */
    static final String AP_MAINT_INV_STS_CD_95 = "95";

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String TOT_REC_CNT = "TOT_REC_CNT";
    /** DB Item Column Name */
    static final String XX_LIST_NUM = "XX_LIST_NUM";
    /** DB Item Column Name */
    static final String CMIB_EZUPTIME = "CMIB_EZUPTIME";
    /** DB Item Column Name */
    static final String CMIB_EZUPTIMEZONE = "CMIB_EZUPTIMEZONE";
    /** DB Item Column Name */
    static final String CMI_EZUPTIME = "CMI_EZUPTIME";
    /** DB Item Column Name */
    static final String CMI_EZUPTIMEZONE = "CMI_EZUPTIMEZONE";
    /** DB Item Column Name */
    static final String CMIS_EZUPTIME = "CMIS_EZUPTIME";
    /** DB Item Column Name */
    static final String CMIS_EZUPTIMEZONE = "CMIS_EZUPTIMEZONE";
    /** DB Item Column Name */
    static final String CMIM_EZUPTIME = "CMIM_EZUPTIME";
    /** DB Item Column Name */
    static final String CMIM_EZUPTIMEZONE = "CMIM_EZUPTIMEZONE";
    /** DB Item Column Name */
    static final String CM_MAINT_INV_BAT_PK = "CM_MAINT_INV_BAT_PK";
    /** DB Item Column Name */
    static final String AP_BAT_NUM = "AP_BAT_NUM";
    /** DB Item Column Name */
    static final String AP_BAT_DT = "AP_BAT_DT";
    /** DB Item Column Name */
    static final String AP_CTRL_AMT = "AP_CTRL_AMT";
    /** DB Item Column Name */
    static final String AP_CTRL_CNT = "AP_CTRL_CNT";
    /** DB Item Column Name */
    static final String AP_RUN_TOT_AMT = "AP_RUN_TOT_AMT";
    /** DB Item Column Name */
    static final String AP_RUN_TOT_CNT = "AP_RUN_TOT_CNT";
    /** DB Item Column Name */
    static final String AP_DS_WF_STS_CD = "AP_DS_WF_STS_CD";
    /** DB Item Column Name */
    static final String AP_DS_WF_STS_NM = "AP_DS_WF_STS_NM";
    /** DB Item Column Name */
    static final String AP_MAINT_INV_STS_CD = "AP_MAINT_INV_STS_CD";
    /** DB Item Column Name */
    static final String AP_MAINT_INV_STS_NM = "AP_MAINT_INV_STS_NM";
    /** DB Item Column Name */
    static final String AP_INV_NUM = "AP_INV_NUM";
    /** DB Item Column Name */
    static final String INV_DT = "INV_DT";
    /** DB Item Column Name */
    static final String APVR_USR_NM = "APVR_USR_NM";
    /** DB Item Column Name */
    static final String AP_VND_CD = "AP_VND_CD";
    /** DB Item Column Name */
    static final String PRNT_VND_CD = "PRNT_VND_CD";
    /** DB Item Column Name */
    static final String PRNT_VND_NM = "PRNT_VND_NM";
    /** DB Item Column Name */
    static final String VND_SITE_NM = "VND_SITE_NM";
    /** DB Item Column Name */
    static final String VND_CD = "VND_CD"; // TODO now 
    /** DB Item Column Name */
    static final String AP_INV_AMT = "AP_INV_AMT";
    /** DB Item Column Name */
    static final String AP_MISC_AMT = "AP_MISC_AMT";
    /** DB Item Column Name */
    static final String AP_TAX_AMT = "AP_TAX_AMT";
    /** DB Item Column Name */
    static final String LATE_FEE_AMT = "LATE_FEE_AMT";
    /** DB Item Column Name */
    static final String SER_NUM = "SER_NUM";
    /** DB Item Column Name */
    static final String OVRD_SER_NUM = "OVRD_SER_NUM";
    /** DB Item Column Name */
    static final String START_DT = "START_DT";
    /** DB Item Column Name */
    static final String END_DT = "END_DT";
    /** DB Item Column Name */
    static final String BASE_AMT = "BASE_AMT";
    /** DB Item Column Name */
    static final String CNTR_TP_CD = "CNTR_TP_CD";
    /** DB Item Column Name */
    static final String CNTR_TP_NM = "CNTR_TP_NM";
    /** DB Item Column Name */
    static final String START_READ_MTR_CNT = "START_READ_MTR_CNT";
    /** DB Item Column Name */
    static final String END_READ_MTR_CNT = "END_READ_MTR_CNT";
    /** DB Item Column Name */
    static final String READ_MTR_CNT = "READ_MTR_CNT";
    /** DB Item Column Name */
    static final String AP_TOL_AMT = "AP_TOL_AMT";
    /** DB Item Column Name */
    static final String INV_CMNT_TXT = "INV_CMNT_TXT";
    /** DB Item Column Name */
    static final String AP_ADJ_AMT = "AP_ADJ_AMT";
    /** DB Item Column Name */
    static final String AP_ADJ_RSN_CD = "AP_ADJ_RSN_CD";
    /** DB Item Column Name */
    static final String AP_ADJ_RSN_NM = "AP_ADJ_RSN_NM";
    /** DB Item Column Name */
    static final String STD_CCY_CD = "STD_CCY_CD";
    // START 2016/09/29 W.Honda [Unit Test#201,ADD]
    /** DB Item Column Name */
    static final String DOC_MGT_DOC_ID = "DOC_MGT_DOC_ID";
    /** DB Item Column Name */
    static final String DOC_MGT_CATG_CD = "DOC_MGT_CATG_CD";
    // END   2016/09/29 W.Honda [Unit Test#201,ADD]

    //START 2017/03/16 E.Kameishi [QC#14205, ADD]
    /** DB Item Column Name */
    static final String XX_REC_HIST_CRAT_TS = "XX_REC_HIST_CRAT_TS";
    /** DB Item Column Name */
    static final String XX_REC_HIST_CRAT_BY_NM = "XX_REC_HIST_CRAT_BY_NM";
    /** DB Item Column Name */
    static final String XX_REC_HIST_UPD_TS = "XX_REC_HIST_UPD_TS";
    /** DB Item Column Name */
    static final String XX_REC_HIST_UPD_BY_NM = "XX_REC_HIST_UPD_BY_NM";
    /** DB Item Column Name */
    static final String XX_REC_HIST_TBL_NM = "XX_REC_HIST_TBL_NM";
    /** DB Item Column Name */
    static final String XX_REC_HIST_CRAT_TS_BA = "XX_REC_HIST_CRAT_TS_BA";
    /** DB Item Column Name */
    static final String XX_REC_HIST_CRAT_BY_NM_BA = "XX_REC_HIST_CRAT_BY_NM_BA";
    /** DB Item Column Name */
    static final String XX_REC_HIST_UPD_TS_BA = "XX_REC_HIST_UPD_TS_BA";
    /** DB Item Column Name */
    static final String XX_REC_HIST_UPD_BY_NM_BA = "XX_REC_HIST_UPD_BY_NM_BA";
    /** DB Item Column Name */
    static final String XX_REC_HIST_TBL_NM_BA = "XX_REC_HIST_TBL_NM_BA";
    /** DB Item Column Name */
    static final String XX_REC_HIST_CRAT_TS_A1 = "XX_REC_HIST_CRAT_TS_A1";
    /** DB Item Column Name */
    static final String XX_REC_HIST_CRAT_BY_NM_A1 = "XX_REC_HIST_CRAT_BY_NM_A1";
    /** DB Item Column Name */
    static final String XX_REC_HIST_UPD_TS_A1 = "XX_REC_HIST_UPD_TS_A1";
    /** DB Item Column Name */
    static final String XX_REC_HIST_UPD_BY_NM_A1 = "XX_REC_HIST_UPD_BY_NM_A1";
    /** DB Item Column Name */
    static final String XX_REC_HIST_TBL_NM_A1 = "XX_REC_HIST_TBL_NM_A1";
    //END 2017/03/16 E.Kameishi [QC#14205, ADD]

    // ** SSM Statement ID's Fixed Value ** //
    /** SSM Statement ID */
    static final String SELECT_CM_AP_INV_IMPT_DTL_BY_PARTIAL_KEY = "SELECT_CM_AP_INV_IMPT_DTL_BY_PARTIAL_KEY";
    
    // ** Message ID ** //
    /** Please check at least 1 checkbox. */
    static final String NFAM0075E = "NFAM0075E";
    /** Please check at least 1 @ checkbox. */
    static final String NFBM0017E = "NFBM0017E";
    /** Unexpected Error Occurred */
    static final String NFBM0028E = "NFBM0028E";
    /** @ is invalid. */
    static final String NFBM0041E = "NFBM0041E";
    /** Record is not found. */
    static final String NFBM0069E = "NFBM0069E";
    /** @ of @ is different from that of @. */
    static final String NFBM0134E = "NFBM0134E";
    /**
     * Another user has already updated target record. Please search again.
     */
    static final String NFBM0155E = "NFBM0155E";
    /**
     * Showing only first @ of total @ records. Please review your search criteria.
     */
    static final String NFBM0001W = "NFBM0001W";
    /** Please enter the value. */
    static final String NFCM0038E = "NFCM0038E";
    /** The invoice data has been updated by another user.  Please search again. */
    static final String NFCM0079E = "NFCM0079E";
    /** [@] is not saved. */
    static final String NWAM0222E = "NWAM0222E";
    /** @ could not acquired From @. */
    static final String NWAM0299E = "NWAM0299E";
    /** The process has been successfully completed. */
    static final String NZZM0002I = "NZZM0002I";
    /**
     * The search ended normally.
     */
    static final String ZZM8002I = "ZZM8002I";
    /** Process ended normally. */
    static final String ZZM8100I = "ZZM8100I";
    /**
     * The process has been successfully completed.
     */
    static final String AZZM0002I = "AZZM0002I";

    // START 2016/08/04 J.Kim [QC#12712,ADD]
    /** Invoice date must be before today. */
    static final String NFBM0106E = "NFBM0106E";
    // END 2016/08/04 J.Kim [QC#12712,ADD]

    // ADD START 2016/08/05 T.Murai [QC#12692]
    /** The specified Serial Number does not exist. */
    static final String NFBM0228E = "NFBM0228E";
    // ADD END 2016/08/05 T.Murai [QC#12692]

    // ADD START 2016/08/05 T.Murai [QC#12866]
    /** Control Count must be same as Running Count. */
    static final String NFBM0232E = "NFBM0232E";
    // ADD END 2016/08/05 T.Murai [QC#12866]

    // ADD START 2016/08/05 T.Murai [QC#12947]
    /** The combination of data entered is incorrect. */
    static final String NFBM0237E = "NFBM0237E";

    // ADD END 2016/08/05 T.Murai [QC#12947]

    // START 2016/09/26 W.Honda [Unit Test#201,ADD]
    /**
     * It failed to register [@]. Please contact IT Support.
     */
    public static final String NFBM0262E = "NFBM0262E";

    /** Message Kind */
    static final String MESSAGE_KIND_E = "E";
    // END   2016/09/26 W.Honda [Unit Test#201,ADD]

    // ** Header RowNum Count **//
    static final int ROW_NUM_MAX = 1000;

    // START 2016/09/21 W.Honda [Unit Test#201,ADD]
    /** Business ID */
    static final String BIZ_ID = "NFBL1110";

    /** parameter index : 9 */
    public static final int PARAM_INDEX_9 = 9;

    /** Attachments Screen Display Mode : Read-Only */
    public static final String PARAMS_DISPLAY_MODE_READ_ONLY = "Read-Only";

    /** Attachments Screen Display Mode : Modification */
    public static final String PARAMS_DISPLAY_MODE_MODIFICATION = "Modification";

    /** Attachments Screen Grouping Text : Therefore */
    public static final String PARAMS_THEREFORE_KEY = "TH";

    /** Attachments Screen Grouping Text : AP Vendor Code */
    public static final String PARAMS_AP_VND_CD_KEY = "AP_VND_CD";

    /** Attachments Screen Grouping Text : AP Vendor Invoice Number */
    public static final String PARAMS_AP_VND_INV_NUM_KEY = "AP_VND_INV_NUM";

    /** Attachments Screen Function Name */
    public static final String PARAMS_FUNCTION_NAME = "Maintenance Invoice Entry Attachments";

    /** Therefore Attach API Business ID : NFZC505001 */
    public static final String THEREFORE_ATT_BIZ_ID = "NFZC505001";

    /** EZDsystemporperty key : S21.therfore.attachment.url */
    public static final String EZDSYS_KEY_THEREFORE_URL = "S21.therfore.attachment.url";

    // VAR_CHAR_CONST_NM
    /** VAR_CHAR_CONST_NM :  */
    static final String CONST_NM_NFBL1110_THEREFORE_CATG_LIST = "NFBL1110_THEREFORE_CATG_LIST";
    // END 2016/09/21 W.Honda [Unit Test#201,ADD]

    /** Duplicate records exist. [ @ ] */
    static final String NFBM0181E = "NFBM0181E";
}
