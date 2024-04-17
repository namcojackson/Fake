/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1440.constant;

/**
 * <pre>
 * Business ID : NPAL1440 PR History Popup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/27/2016   CITS       Yasushi Nomura        Create          N/A
 * 02/14/2024   CITS            H.Iwasaki        Update          QC#63476
 *</pre>
 */
public class NPAL1440Constant {
    /**
     * Business Application ID
     */
    public static final String BUSINESS_APPLICATION_ID = "NPAL1440";

    /**
     * Date time format string : MM/dd/yyyy HH:mm
     */
    public static final String TIME_FORMAT_MMDDYYYYHHMM = "MM/dd/yyyy HH:mm";

    /**
     * Date time format string : yyyyMMddHHmmssSSS
     */
    public static final String TIME_FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    // =================================================
    // Message
    // =================================================
    /** . */
    public static final String NZZM0001W = "NZZM0001W";

    /** . */
    public static final String NPAM0002E = "NPAM0002E";

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** . */
    public static final String DB_PARAM_PRCH_REQ_NUM = "prchReqNum";

    /** . */
    public static final String DB_PARAM_PRCH_REQ_LINE_NUM = "prchReqLineNum";

    /** . */
    public static final String DB_PARAM_PRCH_REQ_LINE_SUB_NUM = "prchReqLineSubNum";

    /** . */
    public static final String DB_PARAM_PROCR_TP_CD = "procrTpCd";

    // =================================================
    // DB Columns
    // =================================================
    /** . */
    public static final String PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** . */
    public static final String PRCH_REQ_TP_DESC_TXT = "PRCH_REQ_TP_DESC_TXT";

    /** . */
    public static final String PRCH_REQ_CRAT_DT = "PRCH_REQ_CRAT_DT";

    /** . */
    public static final String PRCH_REQ_STS_DESC_TXT = "PRCH_REQ_STS_DESC_TXT";

    /** . */
    public static final String FULL_PSN_NM_H = "FULL_PSN_NM_H";

    /** . */
    public static final String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** . */
    public static final String PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /** . */
    public static final String EZINTIME = "EZINTIME";

    /** . */
    public static final String FULL_PSN_NM_D = "FULL_PSN_NM_D";

    /** . */
    public static final String EZINAPLID = "EZINAPLID";

    /** . */
    public static final String PRCH_REQ_LOG_MODE_TXT = "PRCH_REQ_LOG_MODE_TXT";

    /** . */
    public static final String PRCH_REQ_LOG_EVENT_TXT = "PRCH_REQ_LOG_EVENT_TXT";

    /** . */
    public static final String PRCH_REQ_APVL_STS_DESC_TXT = "PRCH_REQ_APVL_STS_DESC_TXT";

    /** . */
    public static final String PRCH_REQ_LINE_STS_DESC_TXT = "PRCH_REQ_LINE_STS_DESC_TXT";

    /** . */
    public static final String MDSE_CD = "MDSE_CD";

    /** . */
    public static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /** . */
    public static final String PRCH_REQ_QTY = "PRCH_REQ_QTY";

    /** . */
    public static final String PROCR_TP_DESC_TXT = "PROCR_TP_DESC_TXT";

    /** . */
    public static final String PRNT_VND_NM = "PRNT_VND_NM";

    /** . */
    public static final String LOC_NM = "LOC_NM";

    /** . */
    public static final String PRCH_REQ_FRZ_FLG = "PRCH_REQ_FRZ_FLG";

    /** . */
    public static final String SHIP_QTY = "SHIP_QTY";

    /** . */
    public static final String RWS_PUT_AWAY_QTY = "RWS_PUT_AWAY_QTY";

    // QC#63476
    /** . */
    public static final String TECH_CD = "TECH_CD";
}
