/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB163001.constant;

/**
 * <pre>
 * Send Task Info to Click Software
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 01/04/2016   Fujitsu         S.Nakai         Create          N/A
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 01/26/2017   Hitachi         K.Ochiai        Update          QC#17180
 *</pre>
 */
public interface NPAB163001Constant {

    /**
     * BATCH_PROGRAM_ID(NPAB1630)
     */
     String BATCH_PROGRAM_ID = "NPAB1630";

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

    /** business_id : NPAB1630 */
     String BUSINESS_ID = "NPAB1630";

    /** Mail Group From(FROM0003) */
     String MAIL_GROUP_ID_FROM = "FROM0003";

    // START 2017/01/26 K.Ochiai [QC#17180, MOD]
    /** Mail Group From */
    String MAIL_GROUP_KEY_FROM = "NPA";
    // END 2017/01/26 K.Ochiai [QC#17180, MOD]

    /** Mail Group To(NPAB1630) */
     String MAIL_GROUP_ID_TO = "NPAB1630";

    /** Mail Template ID(NPAB1630M001) */
     String MAIL_TEMPLATE_ID_01 = "NPAB1630M001";

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

     /** Colon */
     String COLON = ":";
     /** Comma */
     String COMMA = ",";
     /** Space */
     String SPACE = " ";

     /** varchar const : NPAB01630_TIME_FORMAT */
     String NPAB1630_TIME_FORMAT = "NPAB1630_TIME_FORMAT";


     /** TIME_FORMAT */
     String TIME_FORMAT_HHmmss = "HHmmss";

     /** PM Hours */
     String PM_HOURS = "12";

     /**
     * ERROR_MESSAGE.
     */
     String ERROR_MESSAGE = "ERROR_MESSAGE";

     /** CLICK_TECH_ORD_PRT Table */
     String CLICK_TECH_ORD_PRT = "CLICK_TECH_ORD_PRT";

    // Send Field Request Item
    /** CLICK_TECH_ORD_PRT_PK */
     String CLICK_TECH_ORD_PRT_PK = "CLICK_TECH_ORD_PRT_PK";
    /** TECH_ORD_PRT_SQ */
     String TECH_ORD_PRT_SQ = "TECH_ORD_PRT_SQ";
    /** VLD_ACT_CD */
     String VLD_ACT_CD = "VLD_ACT_CD";
     /** MDSE_CD_OR_SHORT_TXT */
     String MDSE_CD_OR_SHORT_TXT = "MDSE_CD_OR_SHORT_TXT";
     /** PRCH_REQ_QTY */
     String PRCH_REQ_QTY = "PRCH_REQ_QTY";
     /** PRCH_REQ_TP_NM */
     String PRCH_REQ_TP_NM = "PRCH_REQ_TP_NM";
     /** RQST_RCV_DT_TXT */
     String RQST_RCV_DT_TXT = "RQST_RCV_DT_TXT";
     /** SHIP_TO_CUST_NM */
     String SHIP_TO_CUST_NM = "SHIP_TO_CUST_NM";
    /** FIRST_INVTY_ORD_CMNT_TXT */
     String FIRST_INVTY_ORD_CMNT_TXT = "FIRST_INVTY_ORD_CMNT_TXT";
    /** SVC_TASK_NUM */
     String SVC_TASK_NUM = "SVC_TASK_NUM";
     /** RQST_TECH_TOC_CD */
     String RQST_TECH_TOC_CD = "RQST_TECH_TOC_CD";
     /** PRCH_REQ_STS_NM */
     String PRCH_REQ_STS_NM = "PRCH_REQ_STS_NM";
     /** PRCH_REQ_NUM */
     String PRCH_REQ_NUM = "PRCH_REQ_NUM";
     /** DEST_RTL_WH_CD */
     String DEST_RTL_WH_CD = "DEST_RTL_WH_CD";
     /** MDSE_CD */
     String MDSE_CD = "MDSE_CD";
     /** MDSE_CD */
     String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";
     /** CLICK_KEY_TXT */
     String CLICK_KEY_TXT = "CLICK_KEY_TXT";
     // add start 2016/10/05 CSA Defect#14803
     /** LGSC_ORD_RMK_TXT */
     String LGSC_ORD_RMK_TXT = "LGSC_ORD_RMK_TXT";
     // add end 2016/10/05 CSA Defect#14803

    // START 2017/01/04 K.Kitachi [QC#16316, ADD]
    public static final String INTERFACE_ID = "NSBI1250";
    // END 2017/01/04 K.Kitachi [QC#16316, ADD]
}
