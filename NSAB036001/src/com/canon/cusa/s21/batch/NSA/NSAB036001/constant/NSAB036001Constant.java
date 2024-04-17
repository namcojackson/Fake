/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB036001.constant;


/**
 * <pre>
 * Service Contact Annual Escalation
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/21   Hitachi         K.Kasai         Create          N/A
 * 2016/05/27   Hitachi         K.Kasai         Update          QC#447
 * 2017/02/10   Hitachi         K.Kitachi       Update          QC#17410
 * 2017/08/24   Hitachi         E.Kameishi      Update          QC#8661
 * 2018/11/19   Hitachi         T.Tomita        Update          QC#28638
 * 2022/12/05   CITS            E.Sanchez       Update          QC#60807
 * 2023/01/06   CITS            E.Sanchez       Update          QC#60807
 *</pre>
 */
public final class NSAB036001Constant {

    /** Batch Id */
    public static final String BATCH_ID = "NSAB036001";

    // add start 2016/05/27 CSA Defect#447
    /** business Application ID */
    public static final String BIZ_APP_ID = "NSAB0360";
    // add end 2016/05/27 CSA Defect#447

    /** FETCH_SIZE_MAX */
    public static final int FETCH_SIZE_MAX = 1000;

    /** colon */
    public static final String COLON = ":";

    // ---------------------------------------
    // Column Name
    // ---------------------------------------

    /** Column Name */
    public static final String DS_CONTR_PK = "DS_CONTR_PK";

    /** Column Name */
    public static final String DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";

    /** Column Name */
    public static final String DS_CONTR_DTL_TP_CD = "DS_CONTR_DTL_TP_CD";

    /** Column Name */
    public static final String DS_CONTR_CATG_CD = "DS_CONTR_CATG_CD";

    /** Column Name */
    public static final String EFF_FROM_DT = "EFF_FROM_DT";

    /** Column Name */
    public static final String CCY_CD = "CCY_CD";

    /** Column Name */
    public static final String CONTR_EFF_THRU_DT = "CONTR_EFF_THRU_DT";

    /** Column Name */
    public static final String CONTR_CLO_DAY = "CONTR_CLO_DAY";

    /** Column Name */
    public static final String BLLG_CYCLE_CD = "BLLG_CYCLE_CD";

    /** Column Name */
    public static final String XS_MTR_COPY_QTY = "XS_MTR_COPY_QTY";

    /** Column Name */
    public static final String XS_MTR_AMT_RATE = "XS_MTR_AMT_RATE";

    /** Column Name */
    public static final String CONTR_UPLFT_TP_CD = "CONTR_UPLFT_TP_CD";

    /** Column Name */
    public static final String BASE_CHRG_FLG = "BASE_CHRG_FLG";

    /** Column Name */
    public static final String USG_CHRG_FLG = "USG_CHRG_FLG";

    /** Column Name */
    public static final String DS_CONTR_BLLG_MTR_PK = "DS_CONTR_BLLG_MTR_PK";

    /** Column Name */
    public static final String CONTR_XS_COPY_PK = "CONTR_XS_COPY_PK";

    /** Column Name */
    public static final String XS_MTR_FIRST_FLG = "XS_MTR_FIRST_FLG";

    /** Column Name */
    public static final String AFT_DECL_PNT_DIGIT_NUM = "AFT_DECL_PNT_DIGIT_NUM";

    /** Column Name */
    public static final String BASE_FLG = "BASE_FLG";

    /** Column Name */
    public static final String USAGE_FLG = "USAGE_FLG";

    // Add Start 2018/11/19 QC#28638
    /** Column Name */
    public static final String MAX_PRC_UP_RATIO = "MAX_PRC_UP_RATIO";
    // Add End 2018/11/19 QC#28638

    // add start 2016/05/27 CSA Defect#447
    /** Column Name */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";
    // add end 2016/05/27 CSA Defect#447

    /** default AFT_DECL_PNT_DIGIT_NUM Value */
    public static final int DEF_AFT_DECL_PNT_DIGIT_NUM = 2;

    // ---------------------------------------
    // Messages
    // ---------------------------------------

    /** Sales Date is required. */
    public static final String NWZM0346E = "NWZM0346E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** This data has been updated by another user. */
    public static final String NSBM0006E = "NSBM0006E";

    /** No valid price effectivity found. */
    public static final String NSAM0370E = "NSAM0370E";

    /** @ is not found.[@] */
    public static final String NSZM0396E = "NSZM0396E";

    // add start 2016/05/27 CSA Defect#447
    /** This IB ID {@} is the end of life for No-Contract. */
    public static final String NSAM0481E = "NSAM0481E";
    // add end 2016/05/27 CSA Defect#447

    /** Constant name */
    public static final String DEF_MDL_RULE_BASE = "DEF_MDL_RULE_BASE";

    /** Constant name */
    public static final String DEF_MDL_RULE_USAGE = "DEF_MDL_RULE_USAGE";

    // ---------------------------------------
    // Parameter
    // ---------------------------------------

    /** DS_CONTR_BASE_USG_NM:BASE */
    public static final String DS_CONTR_BASE_USG_NM_B = "BASE";

    /** DS_CONTR_BASE_USG_NM:OVERAGE */
    public static final String DS_CONTR_BASE_USG_NM_U = "OVERAGE";

    /** DS_CONTR_MACH_LVL_NUM:1 */
    public static final String DS_CONTR_MACH_LVL_NUM_1 = "1";

    /** DS_CONTR_MACH_LVL_NUM:2 */
    public static final String DS_CONTR_MACH_LVL_NUM_2 = "2";

    /** DS_CONTR_MACH_LVL_NUM:3 */
    public static final String DS_CONTR_MACH_LVL_NUM_3 = "3";

    /** ANNUAL_ESCL */
    public static final String MODE_ANN_ESCL = "02";

    /** MODE05_Escalation */
    public static final String PARAM_ESCL = "05";

    /** MAX_DATE:29991231 */
    public static final String MAX_DATE = "29991231";

    /** index_6 */
    public static final int IDX_6 = 6;

    // add start 2016/05/27 CSA Defect#447
    /** VAR_CHAR_CONST:SVC_CONTR_ANN_ESCL_CHK_EOL_FLG */
    public static final String SVC_CONTR_ANN_ESCL_CHK_EOL_FLG = "SVC_CONTR_ANN_ESCL_CHK_EOL_FLG";

    /** Column Name:Ds Contract PK */
    public static final String LOGICAL_NAME_DS_CONTR_PK = "Ds Contract PK";

    /** Column Name:Ds Contract Detail PK */
    public static final String LOGICAL_NAME_DS_CONTR_DTL_PK = "Ds Contract Detail PK";

    /** Column Name:Service Machine Master PK */
    public static final String LOGICAL_NAME_SVC_MACH_MSTR_PK = "Service Machine Master PK";
    // add end 2016/05/27 CSA Defect#447

    // START 2017/02/10 K.Kitachi [QC#17410, ADD]
    /** Constant name */
    public static final String CFS_DLR_CD = "CFS_DLR_CD";

    /** Constant name */
    public static final String SPCL_FLT_MDSE_CD = "SPCL_FLT_MDSE_CD";

    /** CFS Contract Price Upliftment */
    public static final String MSG_INFO_CFS_CONTR_PRC_UPLFT = "CFS Contract Price Upliftment";

    /** Format : DS Contract Modification Text */
    public static final String FORMAT_DS_CONTR_MOD_TXT = "yyyyMMddHHmmss";

    /** Suffix : Fleet Serial Number */
    public static final String SFX_FLT_SER_NUM = "FLT_";
    // END 2017/02/10 K.Kitachi [QC#17410, ADD]

    // START 2017/08/24 E.Kameishi [QC#8661, ADD]
    /** OTPT_OP_CD */
    public static final String OTPT_OP_CD = "Batch";

    /** Report ID : NSAF0030 */
    public static final String RPT_ID_NSAF0030 = "NSAF0030";

    /** Report ID : NSAF0040 */
    public static final String RPT_ID_NSAF0040 = "NSAF0040";
    // END 2017/08/24 E.Kameishi [QC#8661, ADD]

    // START 2022/12/05 E.Sanchez [QC#60807, ADD]
    /** Empty Value */
    public static final String EMPTY_STRING = "";

    /** Line Feed Code */
    public static final String LINE_FEED_CODE = "\r\n";
    
    /** Mail Date Format : MM/dd/yyyy HH:mm:ss.SSS */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

    /** Mail Group From(FROM0005) */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Mail Group To(NSAB0360) */
    public static final String MAIL_GROUP_ID_TO = "NSAB0360";

    /** Mail Template ID(NSAB0360M001) */
    public static final String MAIL_TEMPLATE_ID = "NSAB0360M001";

    /** Mail Item (${batchId}) */
    public static final String MAIL_ITEM_BATCH_ID = "batchId";

    /** Mail Item (${errDate}) */
    public static final String MAIL_ITEM_ERR_DATE = "errDate";

    /** Mail Item (${message}) */
    public static final String MAIL_ITEM_ERR_MSG = "message";

    /** Mail Character Set */
    public static final String MAIL_CHARSET = "ISO-8859-1";

    /** Mail Contract # Info */
    public static final String MAIL_CONTRACT_NUM = "Contract#: ";

    /** Mail Serial # Info */
    public static final String MAIL_SERIAL_NUM = "Serial#: ";

    /** Mail Base/Overage # Info */
    public static final String MAIL_BASE_OVERAGE = "Base/Overage: ";

    /** Mail Policy Date Info */
    public static final String MAIL_POLICY_DATE = "Policy Date: ";
    // END 2022/12/05 E.Sanchez [QC#60807, ADD]

    // START 2023/01/06 E.Sanchez [QC#60807, ADD]
    /** Mail IB ID Info */
    public static final String MAIL_IB_ID = "IB ID: ";
    // END 2023/01/06 E.Sanchez [QC#60807, ADD]
}
