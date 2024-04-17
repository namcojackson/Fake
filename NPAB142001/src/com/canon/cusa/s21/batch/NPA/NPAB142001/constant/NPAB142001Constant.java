/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB142001.constant;

/**
 * <pre>
 * NPAB142001 : CVI Sales Order Update
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/26/2015   CITS            Y.Kuroda        Create          N/A
 *</pre>
 */
public class NPAB142001Constant {

    /** program id */
    public static final String PROGRAM_ID = "NPAB142001";

    /** message id : The parameter is null. [@] */
    public static final String ZZXM0020E = "ZZXM0020E";

    /** message id : [@] does not exist in Master. */
    public static final String NPAM0076E = "NPAM0076E";

    /** message id : [@] has no value. */
    public static final String NPAM0078E = "NPAM0078E";

    /** message id : Cannot retrieve mail address. */
    public static final String NPAM1265E = "NPAM1265E";

    /** message id : Cannot retrieve mail template. */
    public static final String NPAM1266E = "NPAM1266E";

    /** message id : Failed to delete the data. */
    public static final String NPAM1298E = "NPAM1298E";

    /** message id : Failed to register the data. */
    public static final String NPAM1300E = "NPAM1300E";

    /** message id : @ could not be obtained from the @ Table.[@] */
    public static final String NPAM1337W = "NPAM1337W";

    /** message id : NPAM1537E */
    public static final String NPAM1537E = "NPAM1537E";

    /** Oracle Sequence Name */
    public static final String DB_SEQ_CVI_ORD_STS_INFO_SQ = "CVI_ORD_STS_INFO_SQ";

    /** Search Key for NUM_CONST */
    public static final String NPAB1420_CVI_VND_CD = "NPAB1420_CVI_VND_CD";

    /** Error Message Text: Global Company Code */
    public static final String MSG_TXT_GLBL_CMPY_CD = "Global Company Code";

    /** Error Message Text: Interface Id */
    public static final String MSG_TXT_INTERFACE_ID = "Interface Id";

    /** Error Message Text: CVI Vendor Code */
    public static final String MSG_TXT_CVI_VND_CD = "CVI Vendor Code";

    /** Error Message Text: Delivery Lead Time */
    public static final String MSG_TXT_LEAD_TIME = "Delivery Lead Time";

    /** Error Table Name : VAR_CHAR_CONST */
    public static final String ERR_TBL_VAR_CHAR_CONST = "VAR_CHAR_CONST";

    /** System Error Mail group id for To. */
    public static final String MAIL_GROUP_ID = "NPAB1420";

    /** Mail Group Id Key: From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_NPAB1420M001 = "NPAB1420M001";

    /** Mail String Field : batchId */
    public static final String MAIL_FIELD_BATCH_ID = "batchId";

    /** Mail String Field : errDate */
    public static final String MAIL_FIELD_ERR_DATE = "errDate";

    /** Mail Date Format : MM/dd/yyyy HH:mm:ss.SSS */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

    /** Mail String Field : message */
    public static final String MAIL_FIELD_MESSAGE = "message";

    /** Status CD : In Completed */
    public static final String PROC_STS_CD_IN_COMPLETED = "0";

    /** NPAI222001 : CVI_VND_CPO_REF_NUM */
    public static final String NPAI222001_CVI_VND_CPO_REF_NUM = "CVI_VND_CPO_REF_NUM";

    /** NPAI222001 : VND_ISS_ORD_NUM */
    public static final String NPAI222001_VND_ISS_ORD_NUM = "VND_ISS_ORD_NUM";

    /** NPAI222001 : VND_ISS_PO_ORD_NUMR */
    public static final String NPAI222001_VND_ISS_PO_ORD_NUM = "VND_ISS_PO_ORD_NUM";

    /** NPAI222001 : CVI_ORD_NUM */
    public static final String NPAI222001_CVI_ORD_NUM = "CVI_ORD_NUM";

    /** NPAI222001 : CVI_ORD_LINE_NUM */
    public static final String NPAI222001_CVI_ORD_LINE_NUM = "CVI_ORD_LINE_NUM";

    /** NPAI222001 : CVI_ORD_SHIP_NUM */
    public static final String NPAI222001_CVI_ORD_SHIP_NUM = "CVI_ORD_SHIP_NUM";

    /** NPAI222001 : PO_ORD_NUM */
    public static final String NPAI222001_PO_ORD_NUM = "PO_ORD_NUM";

    /** NPAI222001 : PO_DTL_LINE_NUM */
    public static final String NPAI222001_PO_DTL_LINE_NUM = "PO_DTL_LINE_NUM";

    /** NPAI222001 : MDSE_CD */
    public static final String NPAI222001_MDSE_CD = "MDSE_CD";

    /** NPAI222001 : SPLY_ITEM_NUM */
    public static final String NPAI222001_SPLY_ITEM_NUM = "SPLY_ITEM_NUM";

    /** NPAI222001 : CVI_ORD_QTY */
    public static final String NPAI222001_CVI_ORD_QTY = "CVI_ORD_QTY";

    /** NPAI222001 : CVI_SHIP_QTY */
    public static final String NPAI222001_CVI_SHIP_QTY = "CVI_SHIP_QTY";

    /** NPAI222001 : CVI_RSD_TS */
    public static final String NPAI222001_CVI_RSD_TS = "CVI_RSD_TS";

    /** NPAI222001 : CVI_ETD_TS */
    public static final String NPAI222001_CVI_ETD_TS = "CVI_ETD_TS";

    /** NPAI222001 : CVI_SHIP_TS */
    public static final String NPAI222001_CVI_SHIP_TS = "CVI_SHIP_TS";

    /** NPAI222001 : CVI_SHPG_STS_DESC_TXT */
    public static final String NPAI222001_CVI_SHPG_STS_DESC_TXT = "CVI_SHPG_STS_DESC_TXT";

    /** NPAI222001 : CVI_BL_NUM */
    public static final String NPAI222001_CVI_BL_NUM = "CVI_BL_NUM";

    /** NPAI222001 : CVI_DELY_NUM */
    public static final String NPAI222001_CVI_DELY_NUM = "CVI_DELY_NUM";

    /** NPAI222001 : CVI_DELY_LINE_NUM */
    public static final String NPAI222001_CVI_DELY_LINE_NUM = "CVI_DELY_LINE_NUM";

    /** NPAI222001 : CVI_SHIP_METH_DESC_TXT */
    public static final String NPAI222001_CVI_SHIP_METH_DESC_TXT = "CVI_SHIP_METH_DESC_TXT";

    /** NPAI222001 : FORMAT_YYYYMMDD */
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";

    /** NPAI222001 : FORMAT_YYYYMMDD */
    public static final String FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /** Asterisk */
    public static final String ASTERISK = "*";

    /** Comma */
    public static final String COMMA = ",";

    /** length : PO_DTL.PO_ORD_DTL_LINE_NUM  */
    public static final int LEN_PO_ORD_DTL_LINE_NUM = 3;

    /** Value : "0" */
    public static final String VAL_ZERO = "0";
}
