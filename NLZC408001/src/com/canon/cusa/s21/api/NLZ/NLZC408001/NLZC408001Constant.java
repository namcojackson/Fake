/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC408001;

/**
 *<pre>
 * NLZC4080:Create RMA Report Work API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/21/2016   CITS            M.Naito         Create          N/A
 * 12/28/2016   CITS            Y.Fujii         Update          QC#16738
 * 10/20/2017   CITS            T.Kikuhara      Update          QC#21195
 * 04/16/2018   CITS            K.Kameoka       Update          QC#24216
 *</pre>
 */
public class NLZC408001Constant {

    /** SPACE */
    public static final String SPACE = " ";

    /** COMMA */
    public static final String COMMA = ",";

    /** PERIOD */
    public static final String ESC_PERIOD = "\\.";

    /** PERIOD */
    public static final String PERIOD = ".";

    /** DECIMAL_FORMAT */
    public static final String DECIMAL_FORMAT = ",##0";

    /** DATE_TIME_FORMAT */
    public static final String DATE_TIME_FORMAT = "MM/dd/yyyy";

    /** DATE_TIME_FORMAT2 */
    public static final String DATE_TIME_FORMAT2 = "yyyyMMddHHmmssSSS";

    /** DATE_TIME_FORMAT3 */
    public static final String DATE_TIME_FORMAT_14 = "yyyyMMddHHmmss";

    /** DEBUG_LOG_HDR */
    public static final String DEBUG_LOG_HDR = "setMsgId";

    /** CST_DEBUG_MSG_LVL. */
    public static final int CST_DEBUG_MSG_LVL = 10;

    /** RMA_COMMENT_LENGTH. */
    public static final int RMA_COMMENT_LENGTH = 1000;

    /** VAR_CHAR_CONST Key : NLAF0020_DIRECT_PRINT */
    public static final String NLAF0020_DIRECT_PRINT = "NLAF0020_DIRECT_PRINT";

    /** NUM_CONST Key : NLAF0020_PURGE_DT */
    public static final String NLAF0020_PURGE_DT = "NLAF0020_PURGE_DT";

    /** NUM_CONST Key : NLAF0020_LINE_NUM_OF_1ST_PAGE */
    public static final String NLAF0020_LINE_NUM_OF_1ST_PAGE = "NLAF0020_LINE_NUM_OF_1ST_PAGE";

    /** NUM_CONST Key : NLAF0020_LINE_NUM_OF_2ND_PAGE */
    public static final String NLAF0020_LINE_NUM_OF_2ND_PAGE = "NLAF0020_LINE_NUM_OF_2ND_PAGE";

    /** NUM_CONST Key : NLAF0020_NUM_OF_DESC_LTR */
    public static final String NLAF0020_NUM_OF_DESC_LTR = "NLAF0020_NUM_OF_DESC_LTR";

    // =================================================
    // Message Code
    // =================================================
    /**
     * Global Company Code is not set.
     */
    public static final String NLZM2259E = "NLZM2259E";
    /**
     * Sales Date is not set.
     */
    public static final String NLZM2079E = "NLZM2079E";
    /**
     * RMA# is not set.
     */
    public static final String NLZM2480E = "NLZM2480E";
    /**
     * Process Program ID is not set.
     */
    public static final String NLZM2501E = "NLZM2501E";
    /**
     * "NLAF0020_PURGE_DT" doesn't exist in the NUM_CONST.
     */
    public static final String NLZM2481E = "NLZM2481E";
    /**
     * "NLAF0020_LINE_NUM_OF_1ST_PAGE" doesn't exist in the NUM_CONST.
     */
    public static final String NLZM2495E = "NLZM2495E";
    /**
     * "NLAF0020_LINE_NUM_OF_2ND_PAGE" doesn't exist in the NUM_CONST.
     */
    public static final String NLZM2496E = "NLZM2496E";
    /**
     * "NLAF0020_NUM_OF_DESC_LTR" doesn't exist in the NUM_CONST.
     */
    public static final String NLZM2497E = "NLZM2497E";
    /**
     * Failed to delete the RMA_RPT_WRK.
     */
    public static final String NLZM2499E = "NLZM2499E";
    /**
     * Failed to insert the RMA_RPT_WRK.
     */
    public static final String NLZM2482E = "NLZM2482E";
    /**
     * Failed to insert the RPT_RMA_PRINT_LOG.
     */
    public static final String NLZM2498E = "NLZM2498E";
    /**
     * Failed to delete the RPT_RMA_PRINT_LOG.
     */
    public static final String NLZM2483E = "NLZM2483E";
    /**
     * The target data does not exist. 
     */
    public static final String NLZM2494W = "NLZM2494W";
    //QC#21195 ADD START
    /**
     * RWS Number is not set. 
     */
    public static final String NLZM2133E = "NLZM2133E";
    //QC#21195 ADD END
    // =================================================
    // DB Param
    // =================================================
    /** . */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** . */
    public static final String DB_PARAM_PURGE_DATE = "purgeTs";

    /** . */
    public static final String DB_PARAM_RWS_REF_NUM = "rwsRefNum";

    /** . */
    public static final String DB_PARAM_COMMA = "comma";

    /** . */
    public static final String DB_PARAM_ORD_HDR_STS_CLOSED = "closed";

    /** . */
    public static final String DB_PARAM_ORD_HDR_STS_CANCELED = "canceled";

    /** . */
    public static final String DB_PARAM_TRX_HDR_NUM = "trxHdrNum";

    /** . */
    public static final String DB_PARAM_MSG_CTRL_TP_CD = "msgCtrlTpCd";

    /** . */
    public static final String DB_PARAM_RPT_ID = "rptId";

    /** . */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /** . */
    public static final String DB_PARAM_RWS_NUM = "rwsNum";

    /** . */
    public static final String DB_PARAM_PROC_PGM_ID = "procPgmId";


    // =================================================
    // DB Column
    // =================================================
    /** . */
    public static final String CSA_RMA_RPT_WRK_PK = "CSA_RMA_RPT_WRK_PK";
    /** . */
    public static final String WH_CD = "WH_CD";
    //QC#22885 MOD Start
    /** . */
    public static final String RTL_WH_NM = "RTL_WH_NM";
    //QC#22885 MOD END
    /** . */
    public static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";
    /** . */
    public static final String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";
    /** . */
    public static final String SCD_LINE_ADDR = "SCD_LINE_ADDR";
    /** . */
    public static final String THIRD_LINE_ADDR = "THIRD_LINE_ADDR";
    /** . */
    public static final String FRTH_LINE_ADDR = "FRTH_LINE_ADDR";
    /** . */
    public static final String CTY_ADDR = "CTY_ADDR";
    /** . */
    public static final String ST_CD = "ST_CD";
    /** . */
    public static final String POST_CD = "POST_CD";
    /** . */
    public static final String RWS_REF_NUM = "RWS_REF_NUM";
    /** . */
    public static final String SCHD_ETA_DT = "SCHD_ETA_DT";
    /** . */
    public static final String FROM_LOC_NM_01 = "FROM_LOC_NM_01";
    /** . */
    public static final String FROM_LOC_ADDR_01 = "FROM_LOC_ADDR_01";
    /** . */
    public static final String FROM_LOC_ADDR_02 = "FROM_LOC_ADDR_02";
    /** . */
    public static final String FROM_LOC_ADDR_03 = "FROM_LOC_ADDR_03";
    /** . */
    public static final String FROM_LOC_ADDR_04 = "FROM_LOC_ADDR_04";
    /** . */
    public static final String FROM_LOC_CTY_ADDR = "FROM_LOC_CTY_ADDR";
    /** . */
    public static final String FROM_LOC_ST_CD = "FROM_LOC_ST_CD";
    /** . */
    public static final String FROM_LOC_POST_CD = "FROM_LOC_POST_CD";
    /** . */
    public static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";
    /** . */
    public static final String RTRN_TO_LOC_NM = "RTRN_TO_LOC_NM";
    /** . */
    public static final String RTRN_TO_FIRST_LINE_ADDR = "RTRN_TO_FIRST_LINE_ADDR";
    /** . */
    public static final String RTRN_TO_SCD_LINE_ADDR = "RTRN_TO_SCD_LINE_ADDR";
    /** . */
    public static final String RTRN_TO_THIRD_LINE_ADDR = "RTRN_TO_THIRD_LINE_ADDR";
    /** . */
    public static final String RTRN_TO_FRTH_LINE_ADDR = "RTRN_TO_FRTH_LINE_ADDR";
    /** . */
    public static final String RTRN_TO_CTY_ADDR = "RTRN_TO_CTY_ADDR";
    /** . */
    public static final String RTRN_TO_ST_CD = "RTRN_TO_ST_CD";
    /** . */
    public static final String RTRN_TO_POST_CD = "RTRN_TO_POST_CD";
    /** . */
    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";
    /** . */
    public static final String CPO_DPLY_LINE_NUM = "CPO_DPLY_LINE_NUM";
    /** . */
    public static final String MDSE_CD = "MDSE_CD";
    /** . */
    public static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";
    /** . */
    public static final String SER_NUM = "SER_NUM";
    /** . */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";
    /** . */
    public static final String PKG_CD = "PKG_CD";
    /** . */
    public static final String IN_POUND_WT = "IN_POUND_WT";
    /** . */
    public static final String RWS_QTY = "RWS_QTY";
    /** . */
    public static final String TOT_RTRN_QTY_DPLY_TXT = "TOT_RTRN_QTY_DPLY_TXT";
    /** . */
    public static final String TOT_SHIP_WT_DPLY_TXT_01 = "TOT_SHIP_WT_DPLY_TXT_01";
    /** . */
    public static final String TOT_SHIP_WT_DPLY_TXT_02 = "TOT_SHIP_WT_DPLY_TXT_02";
    /** . */
    public static final String RWS_NUM = "RWS_NUM";
    /** . */
    public static final String PROC_PGM_ID = "PROC_PGM_ID";
    /** . */
    public static final String TRX_ORD_NUM = "TRX_ORD_NUM";
    /** . */
    public static final String RTRN_TRX_CMNT_TXT = "RTRN_TRX_CMNT_TXT";
    /** . */
    public static final String CARR_CD = "CARR_CD";
    //QC#24216 MOD Start
    /** . */
    public static final String FROM_LOC_PSN_NM = "FROM_LOC_PSN_NM";
    /** . */
    public static final String FROM_LOC_TEL_NUM = "FROM_LOC_TEL_NUM";
    //QC#24216 MOD End
}
