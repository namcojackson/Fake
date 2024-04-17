/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB047001;

/**
 * <pre>
 * Send Technician to Click Software
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 2015/08/06   Fujitsu         N.Sugiura       Create          N/A
 * 2015/09/16   Hitachi         K.Yamada        Update          BugFix
 * 01/12/2016   Fujitsu         S.Nakai         Update          QC#2475
 * 11/16/2016   Hitachi         Y.Takeno        Update          QC#16005
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 06/20/2018   Hitachi         K.Yamada        Update          QC#24961
 * 2022/09/13   Hitachi         K.Kim           Update          QC#60566
 * 2023/06/12   CITS            M.Okamura       Update          QC#61544
 *</pre>
 */
public class NSBB047001Constant {

    /**
     * BATCH_PROGRAM_ID(for mail)
     */
    public static final String BATCH_PROGRAM_ID = "NSBB0470";
    /**
     * PGM_ID(for DS_BIZ_PROC_LOG)
     */
    public static final String PGM_ID = "NSBB047001";

    /** Mail Item (${batchId}) */
    public static final String MAIL_ITEM_BATCH_ID = "batchId";

    /** Mail Item (${errDate}) */
    public static final String MAIL_ITEM_ERR_DATE = "errDate";

    /** Mail Item (${message}) */
    public static final String MAIL_ITEM_ERR_MSG = "message";

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    /** Mail Date Format : MM/dd/yyyy HH:mm:ss.SSS */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

    /** business_id : NSBB0470 */
    public static final String BUSINESS_ID = "NSBB0470";

    /** Mail Group From(FROM0003) */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** Mail Group From */
    public static final String MAIL_GROUP_KEY_FROM = "NS";

    /** Mail Group To(NSBB0470) */
    public static final String MAIL_GROUP_ID_TO = "NSBB0470";

    /** Mail Template ID(NSBB0470M001) */
    public static final String MAIL_TEMPLATE_ID_01 = "NSBB0470M001";

    /** There is no parameter in [@]. */
    public static final String NSBM0032E = "NSBM0032E";

    /** @ doesn't exist in the VAR_CHAR_CONST. */
    public static final String NSBM0069E = "NSBM0069E";

    /** Data update failure.(ReturnCode = [@]) */
    public static final String ZZZM9013E = "ZZZM9013E";

    /** Data update failure.(ReturnCode = [@]) */
    public static final String ZZXM0015E = "ZZXM0015E";

    /** [@] is not registered.(@) */
    public static final String NSAM0069E = "NSAM0069E";

    /** Error occurred while sending technician information to Clicksoftware. Tech code: [@] */
    public static final String NSBM0129E = "NSBM0129E";

    /** Date Length */
    public static final int DT_LEN = 8;

    /** Mail Line Separator */
    public static final String MAIL_LINE_SEPARATOR = "\r\n";

    // Send Technician Info Header Item
    /**
     * TECH_TOC_CD.
     */
    public static final String TECH_TOC_CD = "TECH_TOC_CD";

    /**
     * TECH_PSN_NM.
     */
    public static final String TECH_PSN_NM = "TECH_PSN_NM";

    // START 2022/09/13 [QC#60566, ADD]
    /**
     * PSN_FIRST_NM.
     */
    public static final String PSN_FIRST_NM = "PSN_FIRST_NM";

    /**
     * PSN_LAST_NM.
     */
    public static final String PSN_LAST_NM = "PSN_LAST_NM";
    // END 2022/09/13 [QC#60566, ADD]

    /**
     * SVC_SKILL_NUM.
     */
    public static final String SVC_SKILL_NUM = "SVC_SKILL_NUM";

    /**
     * SVC_ACCS_PMIT_NUM.
     */
    public static final String SVC_ACCS_PMIT_NUM = "SVC_ACCS_PMIT_NUM";

    /**
     * SVC_ACCS_PMIT_DESC_TXT.
     */
    public static final String SVC_ACCS_PMIT_DESC_TXT = "SVC_ACCS_PMIT_DESC_TXT";
    /**
     * LINE_BIZ_TP_CD.
     */
    public static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";

    /**
     * CELL_TEL_NUM.
     */
    public static final String CELL_TEL_NUM = "CELL_TEL_NUM";

    /**
     * ACTV_FLG.
     */
    public static final String ACTV_FLG = "ACTV_FLG";

    /**
     * COA_BR_CD.
     */
    public static final String COA_BR_CD = "COA_BR_CD";

    /**
     * COA_BR_NM.
     */
    public static final String COA_BR_NM = "COA_BR_NM";

    /**
     * PHYS_INVTY_FLG.
     */
    public static final String PHYS_INVTY_FLG = "PHYS_INVTY_FLG";

    //START 2018/06/20 [QC#24961, ADD]
    /**
     * DS_BIZ_LAST_PROC_TS.
     */
    public static final String DS_BIZ_LAST_PROC_TS = "DS_BIZ_LAST_PROC_TS";
    //END 2018/06/20 [QC#24961, ADD]

    /**
     * DISTRICT.
     */
    public static final String DISTRICT = "DISTRICT";

    // START 2017/01/04 K.Kitachi [QC#16316, ADD]
    public static final String INTERFACE_ID = "NSBI1000";
    // END 2017/01/04 K.Kitachi [QC#16316, ADD]

    // START 2022/09/13 [QC#60566, ADD]
    /** NSBB0470_OFS_SEND */
    public static final String NSBB0470_OFS_SEND = "NSBB0470_OFS_SEND";
    // END 2022/09/13 [QC#60566, ADD]

    // START 2023/06/12 [QC#61544, ADD]
    /** NSBB0470_OFS_SEND */
    public static final String NSBB047001_OFS_NON_ALGN = "NSBB047001_OFS_NON_ALGN";
    // END 2023/06/12 [QC#61544, ADD]
}
