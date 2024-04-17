/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB046001;

/**
 * <pre>
 * Send Task Info to Click Software
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 2015/08/06   Fujitsu         N.Sugiura       Create          N/A
 * 10/28/2015   Fujitsu         T.Nakamura      Update          BugFix, Vikas & Sigi's request
 * 02/29/2016   Hitachi         T.Iwamoto       Update          QC#4101,4102
 * 04/08/2016   Hitachi         T.Iwamoto       Update          QC#2567,6042,4101,4467,6718
 * 06/17/2016   Hitachi         T.Iwamoto       Update          CSA QC#9677
 * 10/22/2018   Hitachi         T.Tomita        Update          CSA QC#28565
 * 2019/03/01   Hitachi         K.Fujimoto      Update          CSA QC#29742
 * 2019/08/22   Hitachi         K.Fujimoto      Update          QC#51206
 *</pre>
 */
public interface NSBB046001Constant {

    /**
     * BATCH_PROGRAM_ID(NSBB0460)
     */
    String BATCH_PROGRAM_ID = "NSBB0460";

    /** Mail Item (${batchId}) */
    String MAIL_ITEM_BATCH_ID = "batchId";

    /** Mail Item (${errDate}) */
    String MAIL_ITEM_ERR_DATE = "errDate";

    // Add Start 2018/10/22 QC#28565
    /** Mail Item (${message}) */
    String MAIL_ITEM_MSG = "message";
    // Add End 2018/10/22 QC#28565

    /** Date Format(yyyyMMddHHmmssSSS) */
    String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    /** Mail Date Format : MM/dd/yyyy HH:mm:ss.SSS */
    String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

    /** business_id : NSBB0460 */
    String BUSINESS_ID = "NSBB0460";

    /** Mail Group From(FROM0003) */
    String MAIL_GROUP_ID_FROM = "FROM0003";

    /** Mail Group From */
    String MAIL_GROUP_KEY_FROM = "NS";

    /** Mail Group To(NSBB0460) */
    String MAIL_GROUP_ID_TO = "NSBB0460";

    /** Mail Template ID(NSBB0460M001) */
    String MAIL_TEMPLATE_ID_01 = "NSBB0460M001";

    /**
     * Error Msg : There is no parameter in [@].
     */
    String NSBM0032E = "NSBM0032E";

    /**
     * Message ID : NSAM0069E
     */
    String NSAM0069E = "NSAM0069E";

    // Add Start 2019/03/01 K.Fujimoto QC#29742
    /**
     * Message : @ cannot be obtained.
     */
    public static final String NSZM0392E = "NSZM0392E";
    // Add End   2019/03/01 K.Fujimoto QC#29742

    // Add Start 2019/08/22 K.Fujimoto QC#51206
    /**
     * Message : @ cannot be obtained.
     */
    public static final String NSZM1362E = "NSZM1362E";
    /**
     * Message : @ cannot be obtained.
     */
    public static final String NSZM1363E = "NSZM1363E";
    /**
     * Message : @ cannot be obtained.
     */
    public static final String NSZM1364E = "NSZM1364E";
    /**
     * Message : @ cannot be obtained.
     */
    public static final String NSZM1365E = "NSZM1365E";
    
    // Add End   2019/08/22 K.Fujimoto QC#51206
    /**
     * ERROR_MESSAGE.
     */
    String ERROR_MESSAGE = "ERROR_MESSAGE";

    // Send task Info Header Item
    /**
     * SVC_TASK_NUM.
     */
    String SVC_TASK_NUM = "SVC_TASK_NUM";

    /**
     * FSR_NUM.
     */
    String FSR_NUM = "FSR_NUM";

    // Add Start 2018/10/22 QC#28565
    /**
     * VAR_CHAR_CONST Key : AUTO_RTRY_SEND_PROC_STS
     */
    String AUTO_RTRY_SEND_PROC_STS = "AUTO_RTRY_SEND_PROC_STS";
    // Add End 2018/10/22 QC#28565
    
    // Add Start 2019/03/01 K.Fujimoto QC#29742
    /** NUM_CONST Key : MULTI_CRS_SVC_RQST_CNT */
    public static final String NUM_CONST_MULTI_SEND_TASK_TO_CLICK_CNT = "MULTI_SEND_TASK_TO_CLICK_CNT";
    // Add End 2019/03/01 K.Fujimoto QC#29742
}
