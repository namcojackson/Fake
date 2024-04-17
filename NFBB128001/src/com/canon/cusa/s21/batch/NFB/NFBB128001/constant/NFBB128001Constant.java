/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB128001.constant;

/**
 * <pre>
 * AP Invoice Data Creation for LeaseBuyout
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   Hitachi         K.Kojima        Create          N/A
 * 2016/03/16   Hitachi         K.Kojima        Update          CSA QC#5297
 * 2016/11/14   Hitachi         K.Kasai         Update          CSA QC#15785
 * 2016/12/12   Fujitsu         H.Ikeda         Update          CSA QC#15823
 * 2017/01/17   Fujitsu         T.Murai         Update          CSA QC#17091
 * 2018/03/22   CITS            T.Kikuhara      Update          CSA QC#20316
 * </pre>
 */
public class NFBB128001Constant {

    /** BUSINESS_ID (NFBB1280) */
    public static final String BUSINESS_ID = "NFBB1280";

    /** PROGRAM_ID(NFBB1280) */
    public static final String PROGRAM_ID = BUSINESS_ID + "01";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Max Commit Number */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** Message Id : NFBM0199E */
    public static final String NFBM0199E = "NFBM0199E";

    /** Message Id : MFBM0207E */
    public static final String NFBM0207E = "NFBM0207E";
    // START 2016/12/12 [QC#15823, ADD]
    /** Message Id : NFZM0027E */
    public static final String NFZM0027E = "NFZM0027E";

    /** Message Id : NFZM0028E */
    public static final String NFZM0028E = "NFZM0028E";
    // END   2016/12/12 [QC#15823, ADD]

    /** Error Msg :  @ doesn't exist. */
    public static final String NFBM0044E = "NFBM0044E";

    /** 00 */
    public static final String STR_00 = "00";

    /** EMPTY */
    public static final String STR_EMPTY = "";

    /** NONE */
    public static final String STR_NONE = "NONE";

    /** yyyyMMdd */
    public static final String YYYYMMDD = "yyyyMMdd";

    /** CM_DEF_ACCT_CD : LEASE */
    public static final String CM_DEF_ACCT_CD_LEASE = "LEASE";

    /** Column name : CPO_ORD_NUM */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    // START 2016/03/16 K.Kojima [QC#5297,DEL]
    // /** Column name : CPO_ORD_TP_CD */
    // public static final String CPO_ORD_TP_CD = "CPO_ORD_TP_CD";
    // END 2016/03/16 K.Kojima [QC#5297,DEL]

    /** Column name : CPO_DTL_LINE_NUM */
    public static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    // START 2016/03/16 K.Kojima [QC#5297,DEL]
    // /** Column name : CPO_ORD_TS */
    // public static final String CPO_ORD_TS = "CPO_ORD_TS";
    //
    // /** Column name : DS_ORD_LINE_CATG_CD */
    // public static final String DS_ORD_LINE_CATG_CD =
    // "DS_ORD_LINE_CATG_CD";
    //
    // /** Column name : APVL_TP_TXT */
    // public static final String APVL_TP_TXT = "APVL_TP_TXT";
    // END 2016/03/16 K.Kojima [QC#5297,DEL]

    // START 2016/03/16 K.Kojima [QC#5297,ADD]
    /** Column name : CPO_DTL_LINE_NUM */
    public static final String INV_NUM = "INV_NUM";

    /** Column name : CPO_DTL_LINE_NUM */
    public static final String INV_BOL_NUM = "INV_BOL_NUM";

    /** Column name : CPO_DTL_LINE_NUM */
    public static final String INV_LINE_NUM = "INV_LINE_NUM";

    /** Column name : CPO_DTL_LINE_NUM */
    public static final String INV_LINE_SUB_NUM = "INV_LINE_SUB_NUM";

    /** Column name : CPO_DTL_LINE_NUM */
    public static final String INV_LINE_SUB_TRX_NUM = "INV_LINE_SUB_TRX_NUM";

    /** Column name : AP_INV_AMT */
    public static final String AP_INV_AMT = "AP_INV_AMT";

    // END 2016/03/16 K.Kojima [QC#5297,ADD]

    // START 2018/03/22 [QC#20316, ADD]
    /** Column name : AP_VND_INV_LINE_NUM */
    public static final String AP_VND_INV_LINE_NUM = "AP_VND_INV_LINE_NUM";
    // END 2018/03/22 [QC#20316, ADD]

    // START 2016/11/14 [QC#15785, ADD]
    /** Column name : CM_INV_ACCT_DIST_LINE_NUM */
    public static final String CM_INV_ACCT_DIST_LINE_NUM = "CM_INV_ACCT_DIST_LINE_NUM";
    // END 2016/11/14 [QC#15785, ADD]

    /** Column name : SPLY_COA_CMPY_CD */
    public static final String SPLY_COA_CMPY_CD = "SPLY_COA_CMPY_CD";

    /** Column name : SPLY_COA_BR_CD */
    public static final String SPLY_COA_BR_CD = "SPLY_COA_BR_CD";

    /** Column name : SPLY_COA_CC_CD */
    public static final String SPLY_COA_CC_CD = "SPLY_COA_CC_CD";

    /** Column name : SPLY_COA_ACCT_CD */
    public static final String SPLY_COA_ACCT_CD = "SPLY_COA_ACCT_CD";

    /** Column name : SPLY_COA_PROD_CD */
    public static final String SPLY_COA_PROD_CD = "SPLY_COA_PROD_CD";

    /** Column name : SPLY_COA_CH_CD */
    public static final String SPLY_COA_CH_CD = "SPLY_COA_CH_CD";

    /** Column name : SPLY_COA_AFFL_CD */
    public static final String SPLY_COA_AFFL_CD = "SPLY_COA_AFFL_CD";

    /** Column name : SPLY_COA_PROJ_CD */
    public static final String SPLY_COA_PROJ_CD = "SPLY_COA_PROJ_CD";

    /** Column name : SPLY_COA_EXTN_CD */
    public static final String SPLY_COA_EXTN_CD = "SPLY_COA_EXTN_CD";

    /** Column name : PRNT_VND_CD */
    public static final String PRNT_VND_CD = "PRNT_VND_CD";

    // VAR_CHAR_CONST
    /** VAR_CHAR_CONST : AP_LINE_TP_ITEM */
    public static final String VARCHAR_CONST_AP_LINE_TP_ITEM = "AP_LINE_TP_ITEM";

    // START 2017/01/16 [QC#17091]
    // VAR_CHAR_CONST
    /** VAR_CHAR_CONST : AJE_COA_DEF_VALUES */
    public static final String VARCHAR_CONST_AJE_COA_DEF_VALUES = "AJE_COA_DEF_VALUES";
    // END   2017/01/16 [QC#17091]

}
