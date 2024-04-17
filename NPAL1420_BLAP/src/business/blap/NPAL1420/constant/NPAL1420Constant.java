/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1420.constant;

/**
 * <pre>
 * Business ID : NPAL1420 Reman Task Entry
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 5/17/2016   CITS       Yasushi Nomura       Create          N/A
 * 1/29/2018   CITS       T.Wada               Update          QC#23072
 *</pre>
 */
public class NPAL1420Constant {
    /**
     * Business Application ID
     */
    public static final String BUSINESS_APPLICATION_ID = "NPAL1420";

    /** . */
    public static final String SPACE = " ";

    // =================================================
    // API Mode
    // =================================================
    /**
     * Mode 1:CREATE
     */
    public static final String MODE_CREATE = "1";

    /**
     * Mode 2:UPDATE
     */
    public static final String MODE_UPDATE = "2";

    /**
     * Mode 3:DELETE
     */
    public static final String MODE_DELETE = "3";

    /**
     * Mode 3:MODE_SUBMIT
     */
    public static final String MODE_SUBMIT = "5";
    // =================================================
    // Message Code
    // =================================================
    /**
     * ZZM9003I: The process [@] has been successfully completed.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** . */
    public static final String NPAM0002E = "NPAM0002E";

    /** . */
    public static final String NPAM0076E = "NPAM0076E";

    /** . */
    public static final String NPAM1351E = "NPAM1351E";

    /** . */
    public static final String NPAM1577W = "NPAM1577W";

    /** . */
    public static final String NPZM0270E = "NPZM0270E";

    /** . */
    public static final String NPAM1522W = "NPAM1522W";
    
    /** . */
    public static final String NLZM2316E = "NLZM2316E";


    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** . */
    public static final String DB_PARAM_RMNF_ORD_NUM = "rmnfOrdNum";

    /** . */
    public static final String DB_PARAM_RMNF_TASK_NUM = "rmnfTaskNum";

    /** . */
    public static final String DB_PARAM_RMNF_TASK_STS_CD = "rmnfTaskStsCd";

    /** . */
    public static final String DB_PARAM_LOC_STS_CD = "locStsCd";

    /** . */
    public static final String DB_PARAM_STK_STS_CD = "stkStsCd";

    /** . */
    public static final String DB_PARAM_TECH_TOC_CD = "techTocCd";

    /** . */
    public static final String DB_PARAM_EFF_FROM_DT = "effFromDt";

    /** . */
    public static final String DB_PARAM_EFF_THRU_DT = "effThruDt";

    /** . */
    public static final String DB_PARAM_DEL_FLG = "delFlg";

    /** . */
    public static final String DB_PARAM_MDSE_CD = "mdseCd";

    /** . */
    public static final String DB_PARAM_PRCH_AVAL_FLG = "prchAvalFlg";

    /** . */
    public static final String DB_PARAM_INVTY_LOC_CD = "invtyLocCd";

    /** . */
    public static final String DB_PARAM_ROWNUM = "rowNum";

    // =================================================
    // DB Columns
    // =================================================
    /** . */
    public static final String RTL_WH_NM = "RTL_WH_NM";

    /** . */
    public static final String RMNF_MAIN_UNIT_SER_NUM = "RMNF_MAIN_UNIT_SER_NUM";

    /** . */
    public static final String RMNF_ORD_NUM = "RMNF_ORD_NUM";

    /** . */
    public static final String RMNF_ORD_STS_DESC_TXT = "RMNF_ORD_STS_DESC_TXT";

    /** . */
    public static final String TECH_NM = "TECH_NM";

    /** . */
    public static final String TECH_NM1 = "TECH_NM1";

    /** . */
    public static final String TECH_NM2 = "TECH_NM2";

    /** . */
    public static final String RMNF_USG_RTL_WH_CD = "RMNF_USG_RTL_WH_CD";

    /** . */
    public static final String RMNF_USG_RTL_SWH_CD = "RMNF_USG_RTL_SWH_CD";

    /** . */
    public static final String RMNF_TASK_NUM = "RMNF_TASK_NUM";

    /** . */
    public static final String RMNF_TASK_DESC_TXT = "RMNF_TASK_DESC_TXT";

    /** . */
    public static final String RMNF_TASK_START_DT = "RMNF_TASK_START_DT";

    /** . */
    public static final String RMNF_TASK_END_DT = "RMNF_TASK_END_DT";

    /** . */
    public static final String SPCL_INSTN_CMNT_TXT = "SPCL_INSTN_CMNT_TXT";

    /** . */
    public static final String OWNR_TECH_TOC_CD = "OWNR_TECH_TOC_CD";

    /** . */
    public static final String RMNF_LBOR_AOT = "RMNF_LBOR_AOT";

    /** . */
    public static final String RMNF_LBOR_CMNT_TXT = "RMNF_LBOR_CMNT_TXT";

    /** . */
    public static final String TECH_TOC_CD = "TECH_TOC_CD";

    /** . */
    public static final String RMNF_COST_PER_HOUR_AMT = "RMNF_COST_PER_HOUR_AMT";

    /** . */
    public static final String STD_CCY_CD = "STD_CCY_CD";

    /** . */
    public static final String RMNF_LBOR_COST_AMT = "RMNF_LBOR_COST_AMT";

    /** . */
    public static final String MDSE_CD = "MDSE_CD";

    /** . */
    public static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /** . */
    public static final String RTRN_REQ_PRT_FLG = "RTRN_REQ_PRT_FLG";

    /** . */
    public static final String PRT_UNIT_COST_AMT = "PRT_UNIT_COST_AMT";

    /** . */
    public static final String INVTY_AVAL_QTY = "INVTY_AVAL_QTY";

    /** . */
    public static final String RMNF_PRT_QTY = "RMNF_PRT_QTY";

    /** . */
    public static final String EZUPTIME = "EZUPTIME";

    /** . */
    public static final String EZUPTIMEZONE = "EZUPTIMEZONE";

    /** . */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** . */
    public static final String PSN_FIRST_NM = "PSN_FIRST_NM";

    /** . */
    public static final String PSN_LAST_NM = "PSN_LAST_NM";

    /** . */
    public static final String THIS_MTH_TOT_STD_COST_AMT = "THIS_MTH_TOT_STD_COST_AMT";

    /** . */
    public static final String RMNF_TASK_PK = "RMNF_TASK_PK";

    /** . */
    public static final String OPEN_STS_FLG = "OPEN_STS_FLG";
    /** . */
    public static final String RMNF_TASK_STS_CD = "RMNF_TASK_STS_CD";

    /** Failed to update. [@] */
    public static final String NPAM1171E = "NPAM1171E";

    /** . */
    public static final String REQUEST_TYPE_NEW_ALLOC = "1";

    /** . */
    public static final String RWS_REF_NUM_RR = "-RR-";
    /** . */
    public static final String TIME_FORMAT = "HHmmss";
    /** . */
    public static final String TRX_LINE_SUB_NUM = "001";


}
