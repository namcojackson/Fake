/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB164001.constant;

/**
 * <pre>
 * Business ID : NPAB1640 Tech Request Notification Batch
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/29/2016   CITS       Yasushi Nomura        Create          N/A
 *</pre>
 */
public class NPAB164001Constant {

    /** Batch ID */
    public static final String BATCH_ID = "NPAB136001";

    /**
     * Date time format string : yyyyMMddHHmmss
     */
    public static final String TIME_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * Date time format string : yyyyMMddHHmmssSSS
     */
    public static final String TIME_FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /**
     * Date time format string : MM/dd/yyyy HH:mm
     */
//    public static final String TIME_FORMAT_MM_DD_YYYY_HH_MM = "MM/dd/yyyy hh:mm";
    public static final String TIME_FORMAT_MM_DD_YYYY_HH_MM = "MM/dd/yyyy HH:mm";

    /**
     * Date time format string : MM/dd/yyyy HH:mm
     */
    public static final String TIME_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";

    /**
     * Mail Message Format (Request)
     */
    public static final String ML_FMT_RQST = "%3s.%-2s       %-50s  %10s  %-50s  %10s  %10s  %10s    %10s  %10s";

    /**
     * Mail Message Format (Return)
     */
    public static final String ML_FMT_RET = "%3s.%-2s       %-50s  %10s  %-50s  %10s  %10s  %-10s    %-10s  %s";

    /**
     * Line Separator Form
     */
    public static final String LINE_SEPARATOR = "line.separator";

    /** . */
    public static final String TECH_RQST_NTC_ML_PROC_CD_0 = "0";

    /** . */
    public static final String TECH_RQST_NTC_ML_PROC_CD_1 = "1";

    /** . */
    public static final String TECH_RQST_NTC_ML_PROC_CD_9 = "9";

    // =================================================
    // Message Code
    // =================================================
    /** Global Company Code is mandatory. */
    public static final String NPAM1479E = "NPAM1479E";

    /** Sales date cannot be obtained. */
    public static final String NPAM1480E = "NPAM1480E";

    /** . */
    public static final String NPAM0064E = "NPAM0064E";

    /** . */
    public static final String NPAM1172E = "NPAM1172E";

    /** . */
    public static final String NPAM1265E = "NPAM1265E";

    // =================================================
    // e-mail
    // =================================================
    /** Mail Template ID (Request) */
    public static final String MAIL_TEMPLATE_ID_RQST = "NPAB1640M002";

    /** Mail Template ID (Return) */
    public static final String MAIL_TEMPLATE_ID_RET = "NPAB1640M001";

    /** Mail Group ID(From) */
    public static final String MAIL_FROM_GROUP_ID = "FROM0005";

    /** Mail Language */
    public static final String ML_LANG = "en";

    /** Mail Language Code */
    public static final String ML_LANG_CD = "ISO-8859-1";

    /** . */
    public static final String EMAIL_PARAM_PRCH_REQ_NUM = "prNum";

    /** . */
    public static final String EMAIL_PARAM_PRCH_REQ_TP_DESC_TXT = "prTp";

    /** . */
    public static final String EMAIL_PARAM_TECH_NM = "techNM";

    /** . */
    public static final String EMAIL_PARAM_RTL_WH_NM = "techWH";

    /** . */
    public static final String EMAIL_PARAM_RTL_SWH_NM = "techSWH";

    /** . */
    public static final String EMAIL_PARAM_DATE_REQ = "requestDt";

    /** . */
    public static final String EMAIL_PARAM_DATE_RET = "cratDt";

    /** . */
    public static final String EMAIL_PARAM_MESSAGE = "message";

    // =================================================
    // DB Param
    // =================================================
    /** . */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** . */
    public static final String DB_PARAM_PRCH_REQ_REC_TP_CD = "prchReqRecTpCd";

    /** . */
    public static final String DB_PARAM_TECH_RQST_NTC_ML_PROC_CD_0 = "techRqstNtcMlProcCd_0";

    /** . */
    public static final String DB_PARAM_TECH_RQST_NTC_ML_PROC_CD_9 = "techRqstNtcMlProcCd_9";

    /** . */
    public static final String DB_PARAM_EFF_FROM_DT = "effFromDt";

    /** . */
    public static final String DB_PARAM_EFF_THRU_DT = "effThruDt";

    /** . */
    public static final String DB_PARAM_DEL_FLG = "delFlg";

    /** . */
    public static final String DB_PARAM_LOC_STS_CD_1 = "locStsCd_1";

    /** . */
    public static final String DB_PARAM_LOC_STS_CD_2 = "locStsCd_2";

    /** . */
    public static final String DB_PARAM_MRP_ENBL_FLG = "mrpEnblFlg";

    /** . */
    public static final String DB_PARAM_MRP_INFO_RGTN_STS_CD = "mrpInfoRgtnStsCd";

    /** . */
    public static final String DB_PARAM_PRCH_REQ_LINE_STS_CD = "prchReqLineStsCd";

    // =================================================
    // DB Column
    // =================================================
    /** . */
    public static final String PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** . */
    public static final String CTRY_CD = "CTRY_CD";

    /** . */
    public static final String POST_CD = "POST_CD";

    /** . */
    public static final String RQST_RCV_DT = "RQST_RCV_DT";

    /** . */
    public static final String RQST_RCV_TM = "RQST_RCV_TM";

    /** . */
    public static final String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** . */
    public static final String PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /** . */
    public static final String MDSE_CD = "MDSE_CD";

    /** . */
    public static final String PRCH_REQ_QTY = "PRCH_REQ_QTY";

    /** . */
    public static final String PRCH_REQ_LINE_TP_DESC_TXT = "PRCH_REQ_LINE_TP_DESC_TXT";

    /** . */
    public static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /** . */
    public static final String INVTY_QTY_1 = "INVTY_QTY_1";

    /** . */
    public static final String INVTY_QTY_2 = "INVTY_QTY_2";

    /** . */
    public static final String MIN_ORD_QTY = "MIN_ORD_QTY";

    /** . */
    public static final String MAX_INVTY_QTY = "MAX_INVTY_QTY";

    /** . */
    public static final String EML_ADDR_1 = "EML_ADDR_1";

    /** . */
    public static final String EML_ADDR_2 = "EML_ADDR_2";

    /** . */
    public static final String RWS_PUT_AWAY_QTY = "RWS_PUT_AWAY_QTY";

    /** . */
    public static final String DEST_RTL_WH_CD = "DEST_RTL_WH_CD";

    /** . */
    public static final String DEST_RTL_SWH_CD = "DEST_RTL_SWH_CD";

    /** . */
    public static final String SRC_RTL_WH_CD = "SRC_RTL_WH_CD";

    /** . */
    public static final String SRC_RTL_SWH_CD = "SRC_RTL_SWH_CD";

    /** . */
    public static final String RWS_CLO_DT_TM_TS = "RWS_CLO_DT_TM_TS";

    /** . */
    public static final String PRCH_REQ_TP_DESC_TXT = "PRCH_REQ_TP_DESC_TXT";

    /** . */
    public static final String TECH_NM = "TECH_NM";

    /** . */
    public static final String PRCH_REQ_CRAT_DT = "PRCH_REQ_CRAT_DT";
}
