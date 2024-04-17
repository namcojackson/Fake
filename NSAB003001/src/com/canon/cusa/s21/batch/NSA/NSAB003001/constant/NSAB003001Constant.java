/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB003001.constant;

/**
 * <pre>
 * Service Contract Auto Renew Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/20   SRA             Y.Chen          Create          N/A
 * 2016/01/20   Hitachi         T.Iwamoto       Update          N/A
 * 2016/02/05   Hitachi         K.Kasai         Create          QC#3996
 * 2016/05/26   Hitachi         T.Mizuki        Update          QC#447
 * 2017/02/08   Hitachi         K.Kitachi       Update          QC#17410
 * 2017/08/22   CITS            M.Naito         Update          QC#8661
 * 2023/07/06   Hitachi         K.Watanabe      Update          QC#61145
 * 2023/07/11   Hitachi         K.Watanabe      Update          QC#61145
 * 2023/07/12   Hitachi         K.Watanabe      Update          QC#61145
 *</pre>
 */
public final class NSAB003001Constant {

    /** Batch ID */
    public static final String BATCH_ID = "NSAB003001";

    /** BIZ_APP_ID */
    public static final String BIZ_APP_ID = "NSAB0030";

    /** NASM0010E: Global Company Code is mandatory. */
    public static final String NASM0010E = "NASM0010E";

    /** NASM0011E: Sales date cannot be obtained. */
    public static final String NASM0011E = "NASM0011E";

    /** No valid price effectivity found. */
    public static final String NSAM0370E = "NSAM0370E";

    /** @ is not found.[@] */
    public static final String NSZM0396E = "NSZM0396E";

    /** ZZZM9004E: This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** Constant name */
    public static final String DEF_MDL_RULE_BASE = "DEF_MDL_RULE_BASE";

    /** Constant name */
    public static final String DEF_MDL_RULE_USAGE = "DEF_MDL_RULE_USAGE";

    /** System Person Cd */
    public static final String SYSTEM_PSN_CD = "SYSTEM";

    /** OTPT_OP_CD */
    public static final String OTPT_OP_CD = "Batch";

    /** FETCH_SIZE_MAX */
    public static final int FETCH_SIZE_MAX = 1000;

    /** DS_CONTR_BASE_USG_NM:BASE */
    public static final String DS_CONTR_BASE_USG_NM_B = "BASE";

    /** DS_CONTR_BASE_USG_NM:USAGE */
    public static final String DS_CONTR_BASE_USG_NM_U = "OVERAGE";

    /** DS_CONTR_MACH_LVL_NUM:1 */
    public static final String DS_CONTR_MACH_LVL_NUM_1 = "1";

    /** DS_CONTR_MACH_LVL_NUM:2 */
    public static final String DS_CONTR_MACH_LVL_NUM_2 = "2";

    /** DS_CONTR_MACH_LVL_NUM:3 */
    public static final String DS_CONTR_MACH_LVL_NUM_3 = "3";

    /** MAX_DATE:29991231 */
    public static final String MAX_DATE = "29991231";

    /** index_6 */
    public static final int IDX_6 = 6;

    // mod start 2016/05/25 CSA QC#447
    /** The EOL Date has passed. The Contract for the Machine could not be renewed. */
    public static final String SVC_CMNT_TXT = "The EOL Date has passed. The Contract for the Machine could not be renewed.";

    /** Error Key Name: DS Contract PK */
    public static final String KEY_DS_CONTR_PK = "DS Contract PK";

    /** Error Key Name: DS Contract Detail PK */
    public static final String KEY_DS_CONTR_DTL_PK = "DS Contract Detail PK";

    /** Error Key Name: Service Machine Master PK */
    public static final String KEY_SVC_MACH_MSTR_PK = "Service Machine Master PK";

    /** Error Key Name: Renewal Type Code */
    public static final String KEY_CONTR_AUTO_RNW_TP_CD = "Renewal Type Code";

    /** Colon */
    public static final String STR_COLON = ":";

    /** Error Massage : Space 4 */
    public static final String ERR_MSG_SPACE_4 = "    ";

    /** IB ID */
    public static final String IB_ID = "IB ID";

    /** Contract No */
    public static final String CONTRACT_NO = "Contract No";

    // mod end 2016/05/25 CSA QC#447

    // START 2017/02/08 K.Kitachi [QC#17410, ADD]
    /** Constant name */
    public static final String CFS_DLR_CD = "CFS_DLR_CD";

    /** Constant name */
    public static final String SPCL_FLT_MDSE_CD = "SPCL_FLT_MDSE_CD";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** CFS Contract Price Upliftment */
    public static final String MSG_INFO_CFS_CONTR_PRC_UPLFT = "CFS Contract Price Upliftment";

    /** Format : DS Contract Modification Text */
    public static final String FORMAT_DS_CONTR_MOD_TXT = "yyyyMMddHHmmss";

    /** Suffix : Fleet Serial Number */
    public static final String SFX_FLT_SER_NUM = "FLT_";
    // END 2017/02/08 K.Kitachi [QC#17410, ADD]

    // START 2017/08/23 M.Naito [QC#8661, ADD]
    /** Report ID : NSAF0030 */
    public static final String RPT_ID_NSAF0030 = "NSAF0030";

    /** Report ID : NSAF0040 */
    public static final String RPT_ID_NSAF0040 = "NSAF0040";

    /** Report ID : NSAF0050 */
    public static final String RPT_ID_NSAF0050 = "NSAF0050";

    /** Report ID : NSAF0060 */
    public static final String RPT_ID_NSAF0060 = "NSAF0060";
    // END 2017/08/23 M.Naito [QC#8661, ADD]

    // START 2023/07/06 K.Watanab [QC#61145, ADD]
    /** MODEL */
    public static final String MODEL = "Model";

    /** Mail Group From(FROM0005) */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    // START 2023/07/12 K.Watanabe [QC#61145, DEL]
    // /** mail key for To */
    // public static final String MAIL_KEY_TO = "To";
    // END 2023/07/12 K.Watanabe [QC#61145, DEL]

    /** Mail Group To(NSBB0460) */
    // START 2023/07/11 K.Watanabe [QC#61145, MOD]
    // public static final String MAIL_GROUP_ID_TO = "NSAB0030";
    public static final String MAIL_GROUP_ID_TO = "NSAB0030M002";
    // END 2023/07/11 K.Watanabe [QC#61145, MOD]

    /** Mail Template ID(NSAB0030M002) */
    public static final String MAIL_TEMPLATE_ID_02 = "NSAB0030M002";

    /** line feed code */
    public static final String LINE_FEED_CODE = "\r\n";

    /** template parameter key : message */
    public static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    /** Error Message : [@] is not registered.(@) */
    public static final String NSAM0069E = "NSAM0069E";
    // END 2023/07/06 K.Watanabe [QC#61145, ADD]
}
