/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB235001;

/**
 * <pre>
 * Customer Installable Machine Master Update Batch
 * NWAB235001 Constant class
 * program of BusinessID NWAB235001. 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/23   Fujitsu         M.Yamada        Create          N/A
 * 2019/09/13   Fujitsu         T.Noguchi       Update          QC#52471
 * 2019/11/20   Fujitsu         A.Kazuki        Update          QC#54204
 *</pre>
 */
public class NWAB235001Constant {
    /** Program ID : NWAB235001 */
    public static final String PROGRAM_ID = "NWAB235001";

    /** Program Name : NWAB235001 */
    public static final String PROGRAM_NM = "Customer Installable Machine Master Update Batch";

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
         * Machine Master corresponding to the Shipping Plan does not exist. [@]
         */
        , NWAM0649E
        /** Could not retrieve MailGroupAddress. MAIL_GROUP_ID_TO : [@] MAIL_KEY1 : [@] */
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
        //2019/09/13 QC#52471 Mod Start
        //, ISTL_DT;
        , ISTL_DT
        /** SVC_MACH_MSTR_LOC_STS_CD */
        , SVC_MACH_MSTR_LOC_STS_CD
        /** STK_STS_CD */
        , STK_STS_CD;
        //2019/09/13 QC#52471 Mod End
    }

    /** DATE_TYPE : YYYYMMDD */
    public static final String DATE_TYPE_YYYYMMDD = "YYYYMMDD";

    /** MAIL_GROUP_ID_FROM : FROM0001 */
    public static final String MAIL_GROUP_ID_FROM = "FROM0001";

    /** MAIL_KEY_FROM : Z */
    public static final String MAIL_KEY_FROM = "Z";

    /** MAIL_GROUP_ID_TO : NWAB2350 */
    public static final String MAIL_GROUP_ID_TO = "NWAB2350";

    /** MAIL_KEY_TO : To */
    public static final String MAIL_KEY_TO = "To";

    /** MAIL_TEMPLATE_ID_M001 : NWAB2350M001 */
    public static final String MAIL_TEMPLATE_ID_M001 = "NWAB2350M001";

    /** MAIL_TEMPLATE_KEY_BATCHID : batchId */
    public static final String MAIL_TEMPLATE_KEY_BATCH_ID = "batchId";

    /** MAIL_TEMPLATE_KEY_BATCHID : batchNm */
    public static final String MAIL_TEMPLATE_KEY_BATCH_NM = "batchNm";

    /** MAIL_TEMPLATE_KEY_MAIL_TEXT : mailText */
    public static final String MAIL_TEMPLATE_KEY_ERR_DATA = "errData";

}
