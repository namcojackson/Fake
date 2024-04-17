/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB052001.constant;

/**
 * <pre>
 * Contract Agreement Letter Creation Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/03/2016   Hitachi         M.Gotou         Create          N/A
 * 08/15/2017   CITS            M.Naito         Update          QC#8661
 * 12/11/2017   Hitachi         K.Ochiai        Update          QC#23006
 * 2019/02/27   Hitachi         K.Kitachi       Update          QC#30517
 * 2019/03/01   Hitachi         K.Kitachi       Update          QC#30517
 * 2019/03/07   Hitachi         K.Fujimoto      Update          QC#30663
 * 2019/03/07   Hitachi         T.Tomita        Update          QC#30517
 * 2019/06/13   Fujitsu         W.Honda         Update          QC#50798
 * 2020/03/17   Hitachi         A.Kohinata      Update          QC#56228
 * 2024/03/19   Hitachi         T.Nagae         Update          QC#63552
 * </pre>
 */
public final class NSAB052001Constant {

    /** Batch ID */
    public static final String BATCH_ID = "NSAB052001";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** NASM0010E: Global Company Code is mandatory. */
    public static final String NASM0010E = "NASM0010E";

    /** NASM0011E: Sales date cannot be obtained. */
    public static final String NASM0011E = "NASM0011E";

    /** ZZZM9004E: This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    // START 2017/08/15 M.Naito [QC#8661, ADD]
    /** NSAM0691E: Output of the Renewal Escalation Letter failed. */
    public static final String NSAM0691E = "NSAM0691E";

    /** PRINTER_AVOID_FLAG */
    public static final String NSAB0520_PRINTER_AVOID_FLAG = "NSAB0520_PRINTER_AVOID_FLAG";
    // START 2017/08/15 M.Naito [QC#8661, ADD]

    // START 2019/06/13 W.Honda [QC#50798, ADD]
    /** Cups Page Count NUM_CONST_NM */
    public static final String CUPS_PAGE_CNT_NUM_CONST_NM = "NWCB011001_CUPS_PAGE_COUNT";
    // END 2019/06/13 W.Honda [QC#50798, ADD]

    /** TYPE_TIME_STAMP */
    public static final String TYPE_TIME_STAMP = "yyyyMMddHHmmss";

    // START 2017/08/15 M.Naito [QC#8661, DEL]
//    /** REPORT_ID */
//    public static final String REPORT_ID = "NSAF0020";
    // END 2017/08/15 M.Naito [QC#8661, DEL]

    // START 2017/08/15 M.Naito [QC#8661, MOD]
    /** Print Title Name */
    public static final String RPT_TTL_NAME = "Canon Maintenance S ";

    /** Print Job Name */
    public static final String PRINT_JOB_NAME = "Canon Maintenance S ";

    /** BIZ_APP_ID */
    public static final String BIZ_APP_ID = "BIZ_APP_ID";

    /** RPT_ID */
    public static final String RPT_ID = "RPT_ID";

    // END 2017/08/15 M.Naito [QC#8661, MOD]

    /** GLBL_CMPY_CD */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DS_CONTR_PK */
    public static final String DS_CONTR_PK = "DS_CONTR_PK";

    // START 2019/03/07 K.Fujimoto [QC#30663, ADD]

    /** SVC_CONTR_BR_CD */
    public static final String SVC_CONTR_BR_CD = "SVC_CONTR_BR_CD";

    /** DS_CONTR_NUM */
    public static final String DS_CONTR_NUM = "DS_CONTR_NUM";

    /** DS_ACCT_NUM */
    public static final String DS_ACCT_NUM = "DS_ACCT_NUM";

    /** LINE_BIZ_TP_DESC_TXT */
    public static final String LINE_BIZ_TP_DESC_TXT = "LINE_BIZ_TP_DESC_TXT";

    // END   2019/03/07 K.Fujimoto [QC#30663, ADD]
    // START 2017/08/15 M.Naito [QC#8661, DEL]
//    /** SLS_DT */
//    public static final String SLS_DT = "SLS_DT";
//
//    /** BIZ_APP_ID */
//    public static final String BIZ_APP_ID = "BIZ_APP_ID";
//
//    /** OTPT_OP_CD */
//    public static final String OTPT_OP_CD = "OTPT_OP_CD";
    // END 2017/08/15 M.Naito [QC#8661, DEL]

    /** INTL_LANG_VAL_COL_NM */
    public static final String INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    // Add Start 2019/03/07 QC#30671
    /** EIP_RPT_RQST_PK */
    public static final String EIP_RPT_RQST_PK = "EIP_RPT_RQST_PK";
    // Add End 2019/03/07 QC#30671

    // START 2017/12/11 K.Ochiai [QC#23006, ADD]
    /** NSAF0020 */
    public static final String NSAF0020 = "NSAF0020";
    // END 2017/12/11 K.Ochiai [QC#23006, ADD]

    // START 2024/03/19 T.Nagae [QC#63552, ADD]
    /** NSAF0030 */
    public static final String NSAF0030 = "NSAF0030";

    /** NSAF0040 */
    public static final String NSAF0040 = "NSAF0040";

    /** NSAF0050 */
    public static final String NSAF0050 = "NSAF0050";

    /** NSAF0060 */
    public static final String NSAF0060 = "NSAF0060";

    // END 2024/03/19 T.Nagae [QC#63552, ADD]

    // START 2019/02/27 K.Kitachi [QC#30517, ADD]
    /** RENEWAL_TICKMARK_FLG */
    public static final String RENEWAL_TICKMARK_FLG = "RENEWAL_TICKMARK_FLG";
    // END 2019/02/27 K.Kitachi [QC#30517, ADD]

    // START 2019/03/01 K.Kitachi [QC#30517, ADD]
    /** STMT_RPT_BR_NUM_FOR_PRT */
    public static final String STMT_RPT_BR_NUM_FOR_PRT = "01";
    // END 2019/03/01 K.Kitachi [QC#30517, ADD]

    // add start 2020/03/17 QC#56228
    /** BIZ_APP_ID_NSAB0030 */
    public static final String BIZ_APP_ID_NSAB0030 = "NSAB0030";

    /** BIZ_APP_ID_NSAB0360 */
    public static final String BIZ_APP_ID_NSAB0360 = "NSAB0360";
    // add end 2020/03/17 QC#56228
    

    // START 2024/03/19 T.Nagae [QC#63552, ADD]
    /** STMT_RPT_BR_NUM_FOR_PRT : 02 */
    public static final String STMT_RPT_BR_NUM_FOR_PRT_02 = "02";

    /** Mail From Group Id */
    public static final String MAIL_FROM_GROUP_ID = "FROM0003";

    /** Mail Template Id */
    public static final String MAIL_TEMPLATE_ID = "NSAB0520M001";

    /** Mail PDF File Name */
    public static final String MAIL_PDF_FILE_NAME = "Canon Maintenance";

    /** PDF File Extension */
    public static final String MAIL_DPF_FILE_EXT = ".pdf";

    /** VAR_CHAR_CONST Key : NSAB052001_LFS_PPS_REP_ADDR */
    public static final String NSAB052001_LFS_PPS_REP_ADDR = "NSAB052001_LFS_PPS_REP_ADDR";

    /** VAR_CHAR_CONST Key : NSAB052001_MAIL_DPF_FILE_NAME */
    public static final String NSAB052001_MAIL_DPF_FILE_NAME = "NSAB052001_MAIL_DPF_FILE_NAME";

    /** SVC_LINE_BIZ_CD */
    public static final String SVC_LINE_BIZ_CD = "SVC_LINE_BIZ_CD";

    /** The field of [@] is illegal. */
    public static final String ZZMM0007E = "ZZMM0007E";
    // END 2024/03/19 T.Nagae [QC#63552, ADD]
    
}
