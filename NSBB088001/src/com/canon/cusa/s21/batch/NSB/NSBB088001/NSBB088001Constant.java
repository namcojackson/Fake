/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB088001;

/**
 * <pre>
 * Send Task Info to Click Software
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 12/28/2015   Fujitsu         S.Nakai         Create          N/A
 * 11/17/2016   Hitachi         K.Ochiai        Update          QC#16024
 * 12/12/2016   Hitachi         Y.Takeno        Update          QC#16316
 *</pre>
 */
public interface NSBB088001Constant {

    /**
     * BATCH_PROGRAM_ID(NSBB0880)
     */
     String BATCH_PROGRAM_ID = "NSBB0880";

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

    /** business_id : NSBB0880 */
     String BUSINESS_ID = "NSBB0880";

    /** Mail Group From(FROM0003) */
     String MAIL_GROUP_ID_FROM = "FROM0003";

    /** Mail Group From */
     String MAIL_GROUP_KEY_FROM = "NS";

    /** Mail Group To(NSBB0880) */
     String MAIL_GROUP_ID_TO = "NSBB0880";

    /** Mail Template ID(NSBB0880M001) */
     String MAIL_TEMPLATE_ID_01 = "NSBB0880M001";

    /** There is no parameter in [@]. */
     String NSBM0032E = "NSBM0032E";

    /** [@] is not registered.(@) */
     String NSBM0135E = "NSBM0135E";
     
     /** Failed to update @ table.[@] */
     String NSBM0120E = "NSBM0120E";

     /** Colon */
     String COLON = ":";
     /** Comma */
     String COMMA = ",";
     /** Space */
     String SPACE = " ";

    /**
     * ERROR_MESSAGE.
     */
     String ERROR_MESSAGE = "ERROR_MESSAGE";

    // Send Field Request Item
    /** SER_NUM */
     String SER_NUM = "SER_NUM";
    /** SVC_CMNT_TXT */
     String SVC_CMNT_TXT = "SVC_CMNT_TXT";
    /** LOC_NM */
     String LOC_NM = "LOC_NM";
     /** FIRST_LINE_ADDR */
     String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";
     /** SCD_LINE_ADDR */
     String SCD_LINE_ADDR = "SCD_LINE_ADDR";
     /** THIRD_LINE_ADDR */
     String THIRD_LINE_ADDR = "THIRD_LINE_ADDR";
     /** FRTH_LINE_ADDR */
     String FRTH_LINE_ADDR = "FRTH_LINE_ADDR";
    /** CTY_ADDR */
     String CTY_ADDR = "CTY_ADDR";
    /** ST_CD */
     String ST_CD = "ST_CD";
     /** POST_CD */
     String POST_CD = "POST_CD";
     /** CTRY_CD */
     String CTRY_CD = "CTRY_CD";
     /** SVC_TASK_NUM */
     String SVC_TASK_NUM = "SVC_TASK_NUM";
     /** CLICK_FLD_RQST_STS_NM */
     String CLICK_FLD_RQST_STS_NM = "CLICK_FLD_RQST_STS_NM";
     /** CLICK_FLD_RQST_PK */
     String CLICK_FLD_RQST_PK = "CLICK_FLD_RQST_PK";
     /** CLICK_KEY_TXT */
     String CLICK_KEY_TXT = "CLICK_KEY_TXT";
     // START 2016/11/17 K.Ochiai [QC#16024, ADD]
     /** FSR_VISIT.TECH_CD */
     String TECH_CD = "TECH_CD";
     // END 2016/11/17 K.Ochiai [QC#16024, ADD]


     /** CLICK_FLD_RQST */
     String CLICK_FLD_RQST = "CLICK_FLD_RQST";

     // START 2016/12/12 [QC#16316, ADD]
     /** INTERFACE_ID */
     String INTERFACE_ID = "NSBI1290";
     // START 2016/12/12 [QC#16316, ADD]

}
