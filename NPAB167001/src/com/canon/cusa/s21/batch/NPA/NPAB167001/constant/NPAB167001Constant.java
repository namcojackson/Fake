/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB167001.constant;

/**
 * <pre>
 * Send Tech Receive Parts to Click Software
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 01/04/2016   Fujitsu         S.Nakai         Create          N/A
 * 08/03/2016   Hitachi         A.Kohinata      Update          QC#10805
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 01/26/2017   Hitachi         K.Ochiai        Update          QC#17180
 *</pre>
 */
public interface NPAB167001Constant {

    /**
     * BATCH_PROGRAM_ID(NPAB1670)
     */
     String BATCH_PROGRAM_ID = "NPAB1670";

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

    /** business_id : NPAB1670 */
     String BUSINESS_ID = "NPAB1670";

    /** Mail Group From(FROM0003) */
     String MAIL_GROUP_ID_FROM = "FROM0003";

    // START 2017/01/26 K.Ochiai [QC#17180, MOD]
    /** Mail Group From */
    String MAIL_GROUP_KEY_FROM = "NPA";
    // END 2017/01/26 K.Ochiai [QC#17180, MOD]

    /** Mail Group To(NPAB1670) */
     String MAIL_GROUP_ID_TO = "NPAB1670";

    /** Mail Template ID(NPAB1670M001) */
     String MAIL_TEMPLATE_ID_01 = "NPAB1670M001";

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

     /** varchar const : NPAB1670_TIME_FORMAT */
     String NPAB1670_TIME_FORMAT = "NPAB1670_TIME_FORMAT";

     /** TIME_FORMAT */
     String TIME_FORMAT_HHmmss = "HHmmss";

     /**
     * ERROR_MESSAGE.
     */
     String ERROR_MESSAGE = "ERROR_MESSAGE";

     /** CLICK_TECH_RCV_PRT Table */
     String CLICK_TECH_RCV_PRT = "CLICK_TECH_RCV_PRT";

    // Send Field Request Item
    /** CLICK_TECH_RCV_PRT_PK */
     String CLICK_TECH_RCV_PRT_PK = "CLICK_TECH_RCV_PRT_PK";
    /** CLICK_TRK_NUM */
     String CLICK_TRK_NUM = "CLICK_TRK_NUM";
    /** RWS_STS_DESC_TXT */
     String RWS_STS_DESC_TXT = "RWS_STS_DESC_TXT";
     /** TECH_CD */
     String TECH_CD = "TECH_CD";
     /** WH_CD */
     String WH_CD = "WH_CD";
     /** SVC_ASG_TECH_CD */
     String SVC_ASG_TECH_CD = "SVC_ASG_TECH_CD";
     /** LAST_DT_TM_UPD_TXT */
     String LAST_DT_TM_UPD_TXT = "LAST_DT_TM_UPD_TXT";
     /** CLICK_KEY_TXT */
     String CLICK_KEY_TXT = "CLICK_KEY_TXT";
// START 02/12/2016 Y.Takeno [QC#3727, MOD]
     /** CTRY_CD */
     String CTRY_CD = "CTRY_CD";
     /** POST_CD */
     String POST_CD = "POST_CD";
// END   02/12/2016 Y.Takeno [QC#3727, MOD]
     // add start 2016/08/03 CSA Defect#10805
     /** RTL_WH_NM */
     String RTL_WH_NM = "RTL_WH_NM";
     /** RWS_DT_TM_CLO_TXT */
     String RWS_DT_TM_CLO_TXT = "RWS_DT_TM_CLO_TXT";
     // add end 2016/08/03 CSA Defect#10805

    // START 2017/01/04 K.Kitachi [QC#16316, ADD]
    public static final String INTERFACE_ID = "NSBI1270";
    // END 2017/01/04 K.Kitachi [QC#16316, ADD]
}
