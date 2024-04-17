/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB113001.constant;

/**
 * <pre>
 * NPAB113001:MRP Run Pre-Process Batch
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   CITS            K.Ogino         Create          N/A
 * 2016/04/11   CITS            K.Ogino         Update          QC#6837
 * 2016/04/14   CITS            K.Ogino         Update          QC#7024
 * 07/12/2017   CITS            Y.Imazu         Update          QC#19865
 * 10/04/2017   CITS            K.Fukumura      Update          QC#21230
 *</pre>
 */
public class NPAB113001Constant {

    /* 07/12/2017 CSAI Y.Imazu Add QC#19865 START */
    /** business_id : NLEB0040 */
    public static final String BUSINESS_ID = "NPAB1130";
    /* 07/12/2017 CSAI Y.Imazu Add QC#19865 END */

    /** MAX FETCH_SIZE */
    public static final int FETCH_SIZE = 1000;

    /** HHmm */
    public static final String TIME_PATTERN_HH24MI = "HHmm";

    /** yyyyMMdd */
    public static final String TIME_PATTERN_YYYYMMDD = "yyyyMMdd";

    /** yyyyMMddHHmmssSSS */
    public static final String TIME_PATTERN_TS = "yyyyMMddHHmmssSSS";

    /** NPAB1120_PR_CRAT_BY_NM */
    public static final String VAR_CHAR_CONST_NM = "NPAB1120_PR_CRAT_BY_NM";

    /** . */
    public static final String STAR = "*";

    /** [@] has no value. */
    public static final String NPAM0078E = "NPAM0078E";

    /* 07/12/2017 CSAI Y.Imazu Del QC#19865 START */
    // /** An error occurred in @. */
    // public static final String NLCM0047E = "NLCM0047E";
    /* 07/12/2017 CSAI Y.Imazu Del QC#19865 END */

    /* 07/12/2017 CSAI Y.Imazu Add QC#19865 START */
    /**  Error Msg : Data could not be registered. Table [@] Pkey [@] */
    public static final String NASM0006E = "NASM0006E";

    /** Error Msg : It failed to update the [@] table. Pkey [@] */
    public static final String NASM0007E = "NASM0007E";
    /* 07/12/2017 CSAI Y.Imazu Add QC#19865 END */

    /** DB_PARAM */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** DB_PARAM */
    public static final String DB_PARAM_LOC_TP_CD = "locTpCd";

    /** DB_PARAM */
    public static final String DB_PARAM_RUN_TM = "runTm";

    /** DB_PARAM */
    public static final String DB_PARAM_MRP_RUN_LAST_RQST_TS = "mrpRunLastRqstTs";

    /** DB_PARAM */
    public static final String DB_PARAM_MRP_RUN_GRP_ID_LIST = "mrpRunGrpIdList";

    /** DB_PARAM */
    public static final String DB_PARAM_LINE_BIZ_TP_CD = "lineBizTpCd";

    /** DB_PARAM */
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    /** DB_PARAM */
    public static final String DB_PARAM_MRP_PLN_NM = "mrpPlnNm";

    /** DB_PARAM */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /** DB_PARAM */
    public static final String DB_PARAM_RTL_SWH_CD = "rtlSwhCd";

    /** DB_PARAM */
    public static final String DB_PARAM_DEL_FLG = "delFlg";

    /** DB_PARAM */
    public static final String DB_PARAM_MRP_ENBL_FLG = "mrpEnblFlg";

    /** DB_PARAM */
    public static final String DB_PARAM_MRP_INFO_RGTN_STS_CD = "mrpInfoRgtnStsCd";

    /** DB_PARAM */
    public static final String DB_PARAM_RGTN_STS_CD = "rgtnStsCd";

    /** DB_PARAM */
    public static final String DB_PARAM_MRP_RUN_STS_CD_LIST = "mrpRunStsCdList";

    /** DB_PARAM */
    public static final String DB_PARAM_RPLSH_WEEK = "rplshWeek";

    /** DB_PARAM */
    public static final String DB_PARAM_FLG_VAL_Y = "flgValueY";

    /** DB_PARAM */
    public static final String DB_PARAM_PRCH_PLN_AVAL_FLG = "prchPlnAvalFlg";

    /** Result Set */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Result Set */
    public static final String SALES_DATE = "SALES_DATE";

    /** Result Set */
    public static final String MRP_INFO_PK = "MRP_INFO_PK";

    /** Result Set */
    public static final String MRP_RUN_PRM_PK = "MRP_RUN_PRM_PK";

    /** Result Set */
    public static final String MRP_PLN_NM = "MRP_PLN_NM";

    /** Result Set */
    public static final String MRP_RUN_SCHD_ID = "MRP_RUN_SCHD_ID";

    /** Result Set */
    public static final String MRP_RUN_GRP_ID = "MRP_RUN_GRP_ID";

    // QC#21230 Stat
    /** Result Set */
    public static final String MRP_RUN_GRP_ITEM_CNT = "MRP_RUN_GRP_ITEM_CNT";
    // QC#21230 END 

    /** Result Set */
    public static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";

    /** Result Set */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /** Result Set */
    public static final String RTL_SWH_CD = "RTL_SWH_CD";

    /** Result Set */
    public static final String MRP_ENBL_FLG = "MRP_ENBL_FLG";

    /** Result Set */
    public static final String RPLSH_FLG = "RPLSH_FLG";

    /** Result Set */
    public static final String RGTN_STS_CD = "RGTN_STS_CD";

    /** Result Set */
    public static final String PRCH_PLN_AVAL_FLG = "PRCH_PLN_AVAL_FLG";

    /** Result Set */
    public static final String MRP_RUN_RQST_TS = "MRP_RUN_RQST_TS";

    /** Result Set */
    public static final String MRP_RUN_RQST_BY_CD = "MRP_RUN_RQST_BY_CD";

    /** Result Set */
    public static final String RS_MRP_RUN_STS_CD = "MRP_RUN_STS_CD";

    /** Result Set */
    public static final String MRP_RUN_MODE_TP_CD = "MRP_RUN_MODE_TP_CD";

    /** Result Set */
    public static final String DMND_CTOFF_DT = "DMND_CTOFF_DT";

    /** Result Set */
    public static final String DMND_CTOFF_DAYS_AOT = "DMND_CTOFF_DAYS_AOT";

    /** Result Set */
    public static final String SPLY_CTOFF_DT = "SPLY_CTOFF_DT";

    /** Result Set */
    public static final String SPLY_CTOFF_DAYS_AOT = "SPLY_CTOFF_DAYS_AOT";

    /** Result Set */
    public static final String CRAT_PRCH_REQ_FLG = "CRAT_PRCH_REQ_FLG";

    /** Result Set */
    public static final String PRINT_ITEM_DESC_FLG = "PRINT_ITEM_DESC_FLG";

    /** Result Set */
    public static final String LOC_TP_CD = "LOC_TP_CD";

    /** Result Set */
    public static final String MRP_RPT_PRINT_PROC_STS_CD = "MRP_RPT_PRINT_PROC_STS_CD";

    /** Result Set */
    public static final String ITM_CNT = "ITM_CNT";

    /** Sequence Name */
    public static final String MRP_RUN_PRM_SQ = "MRP_RUN_PRM_SQ";

    /* 07/12/2017 CSAI Y.Imazu Add QC#19865 START */
    /** MRP_INFO */
    public static final String MRP_INFO = "MRP_INFO";

    /** MRP_RUN_SCHD */
    public static final String MRP_RUN_SCHD = "MRP_RUN_SCHD";

    /** MRP_RUN_PRM */
    public static final String MRP_RUN_PRM = "MRP_RUN_PRM";

    /*****************************************************************
     * Constants for Register Mail
     ****************************************************************/

    /** It failed to send mail. */
    public static final String NWBM0092E = "NWBM0092E";

    /** Mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Mail group id for To */
    public static final String MAIL_GROUP_ID_TO = "NPAB1130";

    /** Mail template ID */
    public static final String MAIL_TEMPLATE_ID = "NPAB1130M001";

    /** Mail message header */
    public static final String MAIL_MSG_HEADER = "ErrorMessage";

    /** Mail line feed code */
    public static final String LINE_FEED_CODE = "\r\n";

    /** Mail template parameter key : Batch ID */
    public static final String MAIL_TEMPLATE_KEY_BATCH_ID = "batchId";

    /** Mail template parameter key : Error Date */
    public static final String MAIL_TEMPLATE_KEY_DATE = "errDate";

    /** Mail template parameter key : Message */
    public static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";
    /* 07/12/2017 CSAI Y.Imazu Add QC#19865 END */
}
