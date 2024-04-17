/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB169001.constant;

/**
 * <pre>
 * Send Parts Transfer to Click Software
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 01/04/2016   Fujitsu         S.Nakai         Create          N/A
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 01/26/2017   Hitachi         K.Ochiai        Update          QC#17180
 *</pre>
 */
public interface NPAB169001Constant {

    /**
     * BATCH_PROGRAM_ID(NPAB1690)
     */
     String BATCH_PROGRAM_ID = "NPAB1690";

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

    /** business_id : NPAB1690 */
     String BUSINESS_ID = "NPAB1690";

    /** Mail Group From(FROM0003) */
     String MAIL_GROUP_ID_FROM = "FROM0003";

    // START 2017/01/26 K.Ochiai [QC#17180, MOD]
    /** Mail Group From */
    String MAIL_GROUP_KEY_FROM = "NPA";
    // EMD 2017/01/26 K.Ochiai [QC#17180, MOD]

    /** Mail Group To(NPAB1690) */
     String MAIL_GROUP_ID_TO = "NPAB1690";

    /** Mail Template ID(NPAB1690M001) */
     String MAIL_TEMPLATE_ID_01 = "NPAB1690M001";

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
     String NPAB1690_TIME_FORMAT = "NPAB1690_TIME_FORMAT";


     /** TIME_FORMAT */
     String TIME_FORMAT_HHmmss = "HHmmss";

     /** PM Hours */
     String PM_HOURS = "12";

     /**
     * ERROR_MESSAGE.
     */
     String ERROR_MESSAGE = "ERROR_MESSAGE";

     /** CLICK_TECH_TRNSF_TO Table */
     String CLICK_TECH_TRNSF_TO = "CLICK_TECH_TRNSF_TO";

    // Send Field Request Item
    /** CLICK_TECH_ORD_PRT_PK */
     String CLICK_TECH_TRNSF_TO_PK = "CLICK_TECH_TRNSF_TO_PK";
    /** TO_INVTY_LOC_CD */
     String TO_INVTY_LOC_CD = "TO_INVTY_LOC_CD";
    /** TECH_TRNSF_RQST_TS */
     String TECH_TRNSF_RQST_TS = "TECH_TRNSF_RQST_TS";
     /** MDSE_CD */
     String MDSE_CD = "MDSE_CD";
     /** MDSE_DESC_SHORT_TXT */
     String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";
     /** SHIP_QTY */
     String SHIP_QTY = "SHIP_QTY";
     /** FROM_INVTY_LOC_CD */
     String FROM_INVTY_LOC_CD = "FROM_INVTY_LOC_CD";
     /** TECH_PROC_STS_DESC_TXT */
     String TECH_PROC_STS_DESC_TXT = "TECH_PROC_STS_DESC_TXT";
    /** TECH_TRNSF_RTRN_STS_TXT */
     String TECH_TRNSF_RTRN_STS_TXT = "TECH_TRNSF_RTRN_STS_TXT";
    /** SER_NUM */
     String SER_NUM = "SER_NUM";
     /** CLICK_KEY_TXT */
     String CLICK_KEY_TXT = "CLICK_KEY_TXT";
// START 02/12/2016 Y.Takeno [QC#3727, MOD]
     /** CTRY_CD */
     String CTRY_CD = "CTRY_CD";
     /** POST_CD */
     String POST_CD = "POST_CD";
// END   02/12/2016 Y.Takeno [QC#3727, MOD]

    // START 2017/01/04 K.Kitachi [QC#16316, ADD]
    public static final String INTERFACE_ID = "NSBI1260";
    // END 2017/01/04 K.Kitachi [QC#16316, ADD]
}
