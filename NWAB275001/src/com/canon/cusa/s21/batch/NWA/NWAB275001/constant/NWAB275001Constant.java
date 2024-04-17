/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB275001.constant;

/**
 * <pre>
 * Machine Master Auto Install Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/04/08   CITS            N.Sang          Create          QC#63689
 *</pre>
 */
public class NWAB275001Constant {
    /** Program ID : NWAB275001 */
    public static final String PROGRAM_ID = "NWAB275001";

    /** Program Name : NWAB275001 */
    public static final String PROGRAM_NM = "Machine Master Auto Install Batch";

    /** API id : NSZC0010 */
    public static final String API_ID_NSZC0010 = "NSZC0010";

    /** message id */
    public static enum MSGID {
        /** [@] is mandatory. */
        ZZZM9025E
        /** [@] is invalid value */
        , ZZZM9026E
        /** An error has occurred in the called API [@]. */
        , NWAM0189E
        /** It failed to register Mail. */
        , NWAM0268E
        /**
         * Machine Master corresponding to the Shipping Plan does not
         * exist. [@]
         */
        , NWAM0649E
        /**
         * Could not retrieve MailGroupAddress. MAIL_GROUP_ID_TO : [@]
         * MAIL_KEY1 : [@]
         */
        , NWAM0516E
        /** The mail template cannot be acquired. <Template ID: [@]> */
        , NWAM0319E;
    }

    /** DB Item */
    public static enum DB_ITEM {
        /** TRX_HDR_NUM */
        TRX_HDR_NUM
        /** TRX_LINE_NUM */
        , TRX_LINE_NUM
        /** TRX_LINE_SUB_NUM */
        , TRX_LINE_SUB_NUM
        /** SVC_MACH_MSTR_PK */
        , SVC_MACH_MSTR_PK
        /** SVC_CONFIG_MSTR_PK */
        , SVC_CONFIG_MSTR_PK
        /** SVC_MACH_MSTR_STS_CD */
        , SVC_MACH_MSTR_STS_CD
        /** SHPG_PLN_NUM */
        , SHPG_PLN_NUM
        /** SMM_SHPG_PLN_NUM */
        , SMM_SHPG_PLN_NUM
        /** ISTL_DT */
        , ISTL_DT
        /** SVC_MACH_MSTR_LOC_STS_CD */
        , SVC_MACH_MSTR_LOC_STS_CD
        /** STK_STS_CD */
        , STK_STS_CD;
    }

    /** DATE_TYPE : YYYYMMDD */
    public static final String DATE_TYPE_YYYYMMDD = "YYYYMMDD";

    /** MAIL_GROUP_ID_FROM : FROM0001 */
    public static final String MAIL_GROUP_ID_FROM = "FROM0001";

    /** MAIL_KEY_FROM : Z */
    public static final String MAIL_KEY_FROM = "Z";

    /** MAIL_GROUP_ID_TO : NWAB2750 */
    public static final String MAIL_GROUP_ID_TO = "NWAB2750";

    /** MAIL_KEY_TO : To */
    public static final String MAIL_KEY_TO = "To";

    /** MAIL_TEMPLATE_ID_M001 : NWAB2750M001 */
    public static final String MAIL_TEMPLATE_ID_M001 = "NWAB2750M001";

    /** MAIL_TEMPLATE_KEY_BATCHID : batchId */
    public static final String MAIL_TEMPLATE_KEY_BATCH_ID = "batchId";

    /** MAIL_TEMPLATE_KEY_BATCHID : batchNm */
    public static final String MAIL_TEMPLATE_KEY_BATCH_NM = "batchNm";

    /** MAIL_TEMPLATE_KEY_MAIL_TEXT : mailText */
    public static final String MAIL_TEMPLATE_KEY_ERR_DATA = "errData";

    /** AUTO_INSTALLED : AUTO_INSTALLED_ITEM */
    public static final String AUTO_INSTALLED = "AUTO_INSTALLED_ITEM";

}
