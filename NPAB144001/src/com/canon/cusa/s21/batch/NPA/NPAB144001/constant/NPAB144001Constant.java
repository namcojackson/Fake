/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB144001.constant;

/**
 * <pre>
 * NPAB144001:Update PO Ack From CVI Sales Order Batch
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/31   CITS            Y.Nomura        Create          N/A
 *</pre>
 */
public class NPAB144001Constant {

    /** Batch ID */
    public static final String BATCH_ID = "NPAB144001";

    /** CCY Code */
    public static final String CCY_CD = "USD";

    /** default PO Sub Line Number */
    public static final String DEF_PO_SUB_NUM = "001";

    // =================================================
    // e-mail
    // =================================================
    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_ID = "NPAB1440M001";

    /** Mail Group ID(To) */
    public static final String MAIL_TO_GROUP_ID = "NPAB1440";

    /** Mail Group ID(From) */
    public static final String MAIL_FROM_GROUP_ID = "FROM0005";

    /** Mail Language */
    public static final String ML_LANG = "en";

    /** Mail Language Code */
    public static final String ML_LANG_CD = "ISO-8859-1";

    /** . */
    public static final String DATE_FORMAT = "MM/dd/yyyy HH:mm";

    /** . */
    public static final String DATE_FORMAT_SYSDATE = "yyyyMMddHHmmssSSS";

    /** Date Format : yyyyMMdd */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /** . */
    public static final String EMAIL_PARAM_BATCH_ID = "batchId";

    /** . */
    public static final String EMAIL_PARAM_ERR_DATE = "errDate";

    /** . */
    public static final String EMAIL_PARAM_MESSAGE = "message";

    // =================================================
    // Message Code
    // =================================================
    /** Global Company Code is mandatory. */
    public static final String NPAM1479E = "NPAM1479E";

    /** message id : The parameter is null. [@] */
    public static final String ZZXM0020E = "ZZXM0020E";

    /** It failed to register an email. */
    public static final String NPZM0161E = "NPZM0161E";

    /** Failed to update. [@] */
    public static final String NPAM1171E = "NPAM1171E";

    /** Failed to insert. [@] */
    public static final String NPAM1172E = "NPAM1172E";

    /** Error Message Text: Interface Id */
    public static final String MSG_TXT_INTERFACE_ID = "Interface Id";

    // =================================================
    // DB Param
    // =================================================
    /** . */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** . */
    public static final String DB_PARAM_PROC_STS_CD = "procStsCd";

    // =================================================
    // DB Column
    // =================================================
    /** . */
    public static final String CVI_ORD_STS_INFO_PK = "CVI_ORD_STS_INFO_PK";

    /** . */
    public static final String PO_DTL_LINE_NUM = "PO_DTL_LINE_NUM";

    /** . */
    public static final String VND_PO_ACK_STS_NM = "VND_PO_ACK_STS_NM";

    /** . */
    public static final String PO_ACK_LINE_STS_CD = "PO_ACK_LINE_STS_CD";

    /** . */
    public static final String CVI_SHPG_STS_DESC_TXT = "CVI_SHPG_STS_DESC_TXT";

    /** . */
    public static final String MDSE_CD = "MDSE_CD";

    /** . */
    public static final String MDSE_NM = "MDSE_NM";

    /** . */
    public static final String SPLY_ITEM_NUM = "SPLY_ITEM_NUM";

    /** . */
    public static final String CVI_SHIP_QTY = "CVI_SHIP_QTY";

    /** . */
    public static final String THIS_MTH_FOB_AMT = "THIS_MTH_FOB_AMT";

    /** . */
    public static final String ETA_DT = "ETA_DT";

    /** . */
    public static final String CVI_ETD_TS = "CVI_ETD_TS";

    /** . */
    public static final String VND_ISS_ORD_NUM = "VND_ISS_ORD_NUM";

    /** . */
    public static final String CVI_ORD_QTY = "CVI_ORD_QTY";

    /** . */
    public static final String PO_ORD_NUM = "PO_ORD_NUM";

    /** . */
    public static final String CVI_SHIP_METH_DESC_TXT = "CVI_SHIP_METH_DESC_TXT";

    /** . */
    public static final String CVI_BL_NUM = "CVI_BL_NUM";

    /** . */
    public static final String CVI_DELY_NUM = "CVI_DELY_NUM";

    /** . */
    public static final String CVI_DELY_LINE_NUM = "CVI_DELY_LINE_NUM";

}
