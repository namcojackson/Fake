/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFD.NFDB006001;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 04/29/2015   Fujitsu         Y.Kamide        Create          N/A
 * 2021/07/09   CITS            G.Delgado       Update          QC#58939
 *</pre>
 */
public class NFDB006001Constant {

    /** business_id : NFDB0030 */
    public static final String BUSINESS_ID = "NFDB006001";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Failed to update [@]. */
    public static final String NFDM0004E = "NFDM0004E";

    /** Failed to insert [@]. */
    public static final String NFDM0013E = "NFDM0013E";

    /** Unexpected Error Occurred [@]. */
    public static final String NFDM0003E = "NFDM0003E";

    /**
     * The corresponding data does not exist.
     * <TABLE: [@], Data Key: [@]>
     */
    public static final String NFDM0005E = "NFDM0005E";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** System error has occurred. */
    public static final String NFDM0008E = "NFDM0008E";

    /** COMPLETE_STRATEGY_ITEM_CD */
    public static final String COMPLETE_STRATEGY_ITEM_CD = "12";

    /** UPDATE_CNT */
    public static final int UPDATE_CNT = 100;

    /** VAR_CHAR_CONST_NM_AR_SYS_BAT_USR_ID */
    public static final String VAR_CHAR_CONST_NM_AR_SYS_BAT_USR_ID = "AR_SYS_BAT_USR_ID";

    /** DEFAULT_WRT_OFF_RQST_USR_ID */
    public static final String DEFAULT_WRT_OFF_RQST_USR_ID = "FINBATCH";

    /** WRT_OFF_OPT_TP_CD_CRAT_ADJ */
    public static final String WRT_OFF_OPT_TP_CD_CRAT_ADJ = "2";

    /** VAR_CAR_CONST Key : Write Off Workflow ID */
    public static final String CONST_KEY_VLD_APVL_WF_ID = "CLT_STRGY_WRITE_OFF_WF_ID";

    // START 2021/07/09 G.Delgado [QC#58939, ADD]
    /** VAR_CHAR_CONST Key : Create Write Off Request Flag */
    public static final String NFDB0060_WRT_OFF_RQST_FLG = "NFDB0060_WRT_OFF_RQST_FLG";

    /** Default Create Write Off Request Flag : N */
    public static final String DEFAULT_WRT_OFF_RQST_FLG = "N";
    // END 2021/07/09 G.Delgado [QC#58939, ADD]

    // START 2016/11/18 [QC#15747, ADD]
    /** The mail template cannot be acquired. <Template ID: [@]> */
    public static final String NFBM0184E = "NFBM0184E";

    /** E-mail address <Type: [@], Group: [@], Key: [@]> cannot be obtained. */
    public static final String NFBM0217E = "NFBM0217E";

    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = "NFDB0020";

    /** template ID */
    public static final String MAIL_TMPL_ID = "NFDB0060M001";

    /** template parameter key : acctNum */
    public static final String MAIL_TMPL_KEY_ACCT_NUM = "acctNum";

    /** template parameter key : billTo */
    public static final String MAIL_TMPL_KEY_BILL_TO = "billTo";
   // END   2016/11/18 [QC#15747, ADD]
}
