/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB112001.constant;

/**
 *<pre>
 *  Part KPI Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/02/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NLCB112001Constant {

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

    /** message Item: DWH Target Year Month. */
    public static final String MSG_ITEM_DWH_TRGT_YR_MTH = "DWH Target Year Month";

    /** message Item: Processing Times. */
    public static final String MSG_ITEM_PROCESSING_TIMES = "Processing Times";

    /** message Item: Over Target Flag. */
    public static final String MSG_ITEM_OVER_TARGET_FLAG = "Over Target Flag";

    /** SSM set Key Name: KEY_RPT_COND_CONST_GRP_ID */
    public static final String KEY_RPT_COND_CONST_GRP_ID = "rptCondConstGrpId";

    /** SSM set Key Name: KEY_APVL_HIST_ACT_TP_CD */
    public static final String KEY_APVL_HIST_ACT_TP_CD = "apvlHistActTpCd";

    /** SSM set Key Name: KEY_PRCH_REQ_LINE_SUB_NUM_1 */
    public static final String KEY_PRCH_REQ_LINE_SUB_NUM_1 = "prchRqLnSbNm1";

    /** SSM set Key Name: KEY_PRCH_REQ_LINE_SUB_NUM_2 */
    public static final String KEY_PRCH_REQ_LINE_SUB_NUM_2 = "prchRqLnSbNm2";

    /** SSM set Key Name: KEY_PRCH_REQ_LINE_SUB_NUM_3 */
    public static final String KEY_PRCH_REQ_LINE_SUB_NUM_3 = "prchRqLnSbNm3";

    /** SSM set Key Name: KEY_PRCH_REQ_LINE_SUB_NUM_4 */
    public static final String KEY_PRCH_REQ_LINE_SUB_NUM_4 = "prchRqLnSbNm4";

    /** SSM set Key Name: KEY_PRCH_REQ_LINE_SUB_NUM_5 */
    public static final String KEY_PRCH_REQ_LINE_SUB_NUM_5 = "prchRqLnSbNm5";

    /** SSM set Key Name: KEY_SUBSTR_DT_LNGTH */
    public static final String KEY_SUBSTR_DT_LNGTH = "substrDtLngth";

    /** SSM set Key Name: KEY_SUBSTR_TM_FROM */
    public static final String KEY_SUBSTR_TM_FROM = "substrTmFrom";

    /** SSM set Key Name: KEY_SUBSTR_TM_LNGTH */
    public static final String KEY_SUBSTR_TM_LNGTH = "substrTmLngth";

    /** SSM set Key Name: KEY_SUBSTR_TRGT_YR_MTH_LNGTH */
    public static final String KEY_SUBSTR_TRGT_YR_MTH_LNGTH = "substrTrgtYrMthLngth";

    /** Value : VAL_POD_STS */
    public static final String VAL_POD_STS = "POD_STS";

    /** Value : VAL_SSL_CD */
    public static final String VAL_SSL_CD = "SSL_CD";

    /**  Value : 1 */
    public static final String VAL_1 = "1";

    /**  Value : 3 */
    public static final String VAL_3 = "3";

    /**  Value : 6 */
    public static final String VAL_6 = "6";

    /**  Value : 8 */
    public static final String VAL_8 = "8";

    /**  Value : 9 */
    public static final String VAL_9 = "9";

    /**  Value : 1001 */
    public static final String VAL_1001 = "1001";

    /**  Value : 1002 */
    public static final String VAL_1002 = "1002";

    /**  Value : 1003 */
    public static final String VAL_1003 = "1003";

    /**  Value : 1004 */
    public static final String VAL_1004 = "1004";

    /**  Value : 1011 */
    public static final String VAL_1011 = "1011";

    /**  Date Format : HHmmSS */
    public static final String DATE_FORMAT = "yyyyMMdd HHmmss";

    /**  Miri Second To Second */
    public static final int MIRI_SEC_TO_SEC = 1000;

    /**  Second To Minute */
    public static final int SEC_TO_MIN = 60;

    /**  Minute To Hour */
    public static final int MIN_TO_HOUR = 60;

    /**  Hour To Day */
    public static final int HOUR_TO_DAY = 24;

    /** SQL PARAM NAME */
    /** . */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** . */
    public static final String PARAM_TRGT_DT = "trgtDt";
    /** . */
    public static final String PARAM_TRGT_YR_MTH = "trgtYrMth";
    /** . */
    public static final String PARAM_CAL_TP_CD = "calTpCd";
    /** . */
    public static final String PARAM_CAL_DT_FROM = "calDtFrom";
    /** . */
    public static final String PARAM_CAL_DT_TO = "calDtTo";
    /** . */
    public static final String PARAM_RPT_COND_CONST_GRP_ID = "rptCndCnstGrpId";

    /** DB COLUMN NAME */
    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** . */
    public static final String BATCH_PROCESS_DATE = "BATCH_PROCESS_DATE";
    /** . */
    public static final String CAL_TP_CD = "CAL_TP_CD";
    /** . */
    public static final String SVC_LVL = "SVC_LVL";
    /** . */
    public static final String TRGT_DT = "TRGT_DT";
    /** . */
    public static final String DWH_TRGT_YR_MTH = "DWH_TRGT_YR_MTH";
    /** . */
    public static final String PRCH_REQ_NUM = "PRCH_REQ_NUM";
    /** . */
    public static final String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";
    /** . */
    public static final String PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";
    /** . */
    public static final String SO_NUM = "SO_NUM";
    /** . */
    public static final String MDSE_CD = "MDSE_CD";
    /** . */
    public static final String PRCH_REQ_LINE_TP_CD = "PRCH_REQ_LINE_TP_CD";
    /** . */
    public static final String PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";
    /** . */
    public static final String RTL_WH_CATG_CD = "RTL_WH_CATG_CD";
    /** . */
    public static final String TECH_TOC_CD = "TECH_TOC_CD";
    /** . */
    public static final String SHIP_TO_RTL_WH_CD = "SHIP_TO_RTL_WH_CD";
    /** . */
    public static final String SHIP_FROM_RTL_WH_CD = "SHIP_FROM_RTL_WH_CD";
    /** . */
    public static final String SRC_RTL_WH_CD = "SRC_RTL_WH_CD";
    /** . */
    public static final String DEST_RTL_WH_CD = "DEST_RTL_WH_CD";
    /** . */
    public static final String CARR_CD = "CARR_CD";
    /** . */
    public static final String SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";
    /** . */
    public static final String SHIP_FRT_AMT = "SHIP_FRT_AMT";
    /** . */
    public static final String CRAT_DT = "CRAT_DT";
    /** . */
    public static final String CRAT_TM = "CRAT_TM";
    /** . */
    public static final String PRCH_REQ_APVL_DT = "PRCH_REQ_APVL_DT";
    /** . */
    public static final String PRCH_REQ_APVL_TM = "PRCH_REQ_APVL_TM";
    /** . */
    public static final String WMS_INTFC_DT = "WMS_INTFC_DT";
    /** . */
    public static final String WMS_INTFC_TM = "WMS_INTFC_TM";
    /** . */
    public static final String WH_PICK_DT = "WH_PICK_DT";
    /** . */
    public static final String WH_PICK_TM = "WH_PICK_TM";
    /** . */
    public static final String SHIP_DT = "SHIP_DT";
    /** . */
    public static final String SHIP_TM = "SHIP_TM";
    /** . */
    public static final String CARR_PICK_UP_DT = "CARR_PICK_UP_DT";
    /** . */
    public static final String CARR_PICK_UP_TM = "CARR_PICK_UP_TM";
    /** . */
    public static final String CARR_AT_DEST_DT = "CARR_AT_DEST_DT";
    /** . */
    public static final String CARR_AT_DEST_TM = "CARR_AT_DEST_TM";
    /** . */
    public static final String DELY_DT = "DELY_DT";
    /** . */
    public static final String DELY_TM = "DELY_TM";
    /** . */
    public static final String RCV_DT = "RCV_DT";
    /** . */
    public static final String RCV_TM = "RCV_TM";
    /** . */
    public static final String CRAT_TO_APVL_TERM_AOT = "CRAT_TO_APVL_TERM_AOT";
    /** . */
    public static final String APVL_TO_INTFC_TERM_AOT = "APVL_TO_INTFC_TERM_AOT";
    /** . */
    public static final String INTFC_TO_PICK_TERM_AOT = "INTFC_TO_PICK_TERM_AOT";
    /** . */
    public static final String PICK_TO_SHIP_TERM_AOT = "PICK_TO_SHIP_TERM_AOT";
    /** . */
    public static final String SHIP_TO_PICK_UP_TERM_AOT = "SHIP_TO_PICK_UP_TERM_AOT";
    /** . */
    public static final String PICK_UP_TO_AT_DEST_TERM_AOT = "PICK_UP_TO_AT_DEST_TERM_AOT";
    /** . */
    public static final String AT_DEST_TO_DELY_TERM_AOT = "AT_DEST_TO_DELY_TERM_AOT";
    /** . */
    public static final String DELY_TO_RCV_TERM_AOT = "DELY_TO_RCV_TERM_AOT";
    /** . */
    public static final String FSR_NUM = "FSR_NUM";
    /** . */
    public static final String SVC_TASK_NUM = "SVC_TASK_NUM";
    /** . */
    public static final String OVER_TRGT_FLG = "OVER_TRGT_FLG";

}
