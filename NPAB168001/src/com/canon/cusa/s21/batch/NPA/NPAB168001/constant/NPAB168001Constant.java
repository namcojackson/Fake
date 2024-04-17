/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB168001.constant;

/**
 * <pre>
 * Send Parts Return to Click Software
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 01/04/2016   Fujitsu         S.Nakai         Create          N/A
 * 03/30/2015   Hitachi         T.Iwamoto       Update          QC#4287
 * 2016/11/24   Hitachi         K.Kojima        Update          QC#16033
 * 2016/11/29   Hitachi         K.Kojima        Update          QC#16033
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 01/26/2017   Hitachi         K.Ochiai        Update          QC#17180
 *</pre>
 */
public interface NPAB168001Constant {

    /**
     * BATCH_PROGRAM_ID(NPAB1680)
     */
     String BATCH_PROGRAM_ID = "NPAB1680";

    /** Mail Item (${batchId}) */
     String MAIL_ITEM_BATCH_ID = "batchId";

     /** Mail Item (${errDate}) */
     String MAIL_ITEM_ERR_DATE = "errDate";

     /** Mail Item (${message}) */
     String MAIL_ITEM_ERR_MSG = "message";

    /** Date Format(yyyyMMddHHmmssSSS) */
     String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    /** Mail Date Format : MM/dd/yyyy HH:mm:ss.SSS */
     String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

    /** business_id : NPAB1680 */
     String BUSINESS_ID = "NPAB1680";

    /** Mail Group From(FROM0003) */
     String MAIL_GROUP_ID_FROM = "FROM0003";

    // START 2017/01/26 K.Ochiai [QC#17180, MOD]
    /** Mail Group From */
    String MAIL_GROUP_KEY_FROM = "NPA";
    // END 2017/01/26 K.Ochiai [QC#17180, MOD]

    /** Mail Group To(NPAB1680) */
     String MAIL_GROUP_ID_TO = "NPAB1680";

    /** Mail Template ID(NPAB1680M001) */
     String MAIL_TEMPLATE_ID_01 = "NPAB1680M001";

    /** Error happen during checking parameter. The parameter is @. */
     String NPAM1001E = "NPAM1001E";

    /** [@] is not registered.(@) */
     String NPAM1478E = "NPAM1478E";

     /** The e-mail template <template ID: [@]> cannot be obtained. */
     String NPAM1331E = "NPAM1331E";

     /** Failed to update @ table. @ is @. */
     String NPAM1003E = "NPAM1003E";

     /** The Constant [@] was not found on Constant table. */
     String NPAM1010E = "NPAM1010E";

     /** The corresponding data does not exist. Table Name : [@], Key Field Name:  [@], Key Value:  [@] */
     String NPAM1166E = "NPAM1166E";

     /*****************************************************************
      * DS_COND_CONST
      ****************************************************************/
     /** DS_COND_CONST GroupId : NPAB1680 */
     String CONST_GRP_ID_NPAB1680 = "NPAB1680";

     /** DS_COND_CONST KEY : PRT_RTRN_REQ_STS_KEY */
     String CONST_PRT_RTRN_REQ_STS_KEY = "PRT_RTRN_REQ_STS";

     /** DS_COND_CONST GroupId : NPAB1680_LINE_STS */
     String NPAB1680_LINE_STS = "NPAB1680_LINE_STS";
     // START 2016/11/24 K.Kojima [QC#16033,ADD]
     /** DS_COND_CONST GroupId : NPZC1190_RTRN_TP */
     String NPZC1190_RTRN_TP = "NPZC1190_RTRN_TP";
     // END 2016/11/24 K.Kojima [QC#16033,ADD]

     /** New */
     String CONST_STS_NEW = "STS_NEW";
     /** Awaiting Shipment */
     String CONST_STS_AWAITINGSHIPMENT = "STS_AWAITINGSHIPMENT";
     /** Shipped */
     String CONST_STS_SHIPPED = "STS_SHIPPED";
     /** Shipment Short */
     String CONST_STS_SHIPMENTSHORT = "STS_SHIPMENTSHORT";
     /** Close */
     String CONST_STS_CLOSE = "STS_CLOSE";
     /** Success */
     String CONST_STS_SUCCESS = "STS_SUCCESS";
     /** Error */
     String CONST_STS_ERROR = "STS_ERROR";

     /**
     * ERROR_MESSAGE.
     */
     String ERROR_MESSAGE = "ERROR_MESSAGE";

     /** CLICK_PRT_RTRN_RQST Table */
     String CLICK_PRT_RTRN_RQST = "CLICK_PRT_RTRN_RQST";

    // Send Field Request Item
    /** CLICK_PRT_RTRN_RQST_PK */
     String CLICK_PRT_RTRN_RQST_PK = "CLICK_PRT_RTRN_RQST_PK";
    /** PRT_RTRN_RQST_SQ */
     String PRT_RTRN_RQST_SQ = "PRT_RTRN_RQST_SQ";
    /** PRCH_REQ_NUM */
     String PRCH_REQ_NUM = "PRCH_REQ_NUM";
     /** PRCH_REQ_RQST_STS_TXT */
     String PRCH_REQ_RQST_STS_TXT = "PRCH_REQ_RQST_STS_TXT";
     /** PRCH_REQ_STS_TXT */
     String PRCH_REQ_STS_TXT = "PRCH_REQ_STS_TXT";
     /** INVTY_LOC_CD */
     String INVTY_LOC_CD = "INVTY_LOC_CD";
     /** PRCH_REQ_INVTY_TP_TXT */
     String PRCH_REQ_INVTY_TP_TXT = "PRCH_REQ_INVTY_TP_TXT";
     /** MDSE_CD */
     String MDSE_CD = "MDSE_CD";
    /** PRCH_REQ_QTY */
     String PRCH_REQ_QTY = "PRCH_REQ_QTY";
    /** PRCH_REQ_LINE_CMNT_TXT */
     String PRCH_REQ_LINE_CMNT_TXT = "PRCH_REQ_LINE_CMNT_TXT";
     /** CLICK_TRK_NUM */
     String CLICK_TRK_NUM = "CLICK_TRK_NUM";
     /** CLICK_KEY_TXT */
     String CLICK_KEY_TXT = "CLICK_KEY_TXT";
     /** CLICK_KEY_DTL_TXT */
     String CLICK_KEY_DTL_TXT = "CLICK_KEY_DTL_TXT";
     /** PRCH_REQ_LINE_NUM */
     String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";
     /** PRCH_REQ_LINE_SUB_NUM */
     String PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";
     // START 2016/11/24 K.Kojima [QC#16033,ADD]
     /** SRC_RTL_WH_CD */
     String SRC_RTL_WH_CD = "SRC_RTL_WH_CD";
     // START 2016/11/29 K.Kojima [QC#16033,DEL]
     // /** TECH_PSN_NM */
     // String TECH_PSN_NM = "TECH_PSN_NM";
     // END 2016/11/29 K.Kojima [QC#16033,DEL]
     /** RQST_TECH_TOC_CD */
     String RQST_TECH_TOC_CD = "RQST_TECH_TOC_CD";
     /** MDSE_DESC_SHORT_TXT */
     String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";
     // END 2016/11/24 K.Kojima [QC#16033,ADD]
     // START 2016/11/29 K.Kojima [QC#16033,ADD]
     /** RTL_WH_NM */
     String RTL_WH_NM = "RTL_WH_NM";
     // END 2016/11/29 K.Kojima [QC#16033,ADD]
     // START 2016/11/29 K.Kojima [QC#14204,ADD]
     /** PRCH_REQ_LINE_TP_CD */
     String PRCH_REQ_LINE_TP_CD = "PRCH_REQ_LINE_TP_CD";
     /** PRCH_REQ_DELY_QTY */
     String PRCH_REQ_DELY_QTY = "PRCH_REQ_DELY_QTY";
     /** PRCH_REQ_SHIP_QTY */
     String PRCH_REQ_SHIP_QTY = "PRCH_REQ_SHIP_QTY";
     /** PRCH_REQ_SHORT_QTY */
     String PRCH_REQ_SHORT_QTY = "PRCH_REQ_SHORT_QTY";
     // END 2016/11/29 K.Kojima [QC#14204,ADD]

    // START 2017/01/04 K.Kitachi [QC#16316, ADD]
    public static final String INTERFACE_ID = "NSBI1280";
    // END 2017/01/04 K.Kitachi [QC#16316, ADD]
}
