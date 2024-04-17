/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB253001.constant;

/**
 * <pre>
 * Mass Upload RMA Default WH Mapping Template
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/12   Fujitsu         T.Ogura         Create          QC#19805
 * </pre>
 */
public class NWAB253001Constant {

    /** UploadCsvId : NWA2040003(Append) */
    public static final String UPLOAD_CSV_ID_APPEND = "NWA2040003";

    /** UploadCsvId : NWA2040004(Replace) */
    public static final String UPLOAD_CSV_ID_REPLACE = "NWA2040004";

    /** Max Commit Number */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Duplicated Check Result Key */
    public static final String CHECK_RESULT_KEY = "CHECK_RESULT_KEY";

    /** Duplicated Check Result : OK */
    public static final String CHECK_RESULT_OK = "OK";

    /** Duplicated Check Result : NG */
    public static final String CHECK_RESULT_NG = "NG";

    /** MessageID : NWAM0803E */
    public static final String NWAM0803E = "NWAM0803E";

    /** MessageID : NWAM0805E */
    public static final String NWAM0805E = "NWAM0805E";

    /** MessageID : NWAM0806E */
    public static final String NWAM0806E = "NWAM0806E";

    /** MessageID : NWAM0807E */
    public static final String NWAM0807E = "NWAM0807E";

    /** MessageID : NWAM0940E */
    public static final String NWAM0940E = "NWAM0940E";

    /** MessageID : ZZBM0009I */
    public static final String ZZBM0009I = "ZZBM0009I";

    /** MessageID : ZZM9000E */
    public static final String ZZM9000E = "ZZM9000E";

    /** MessageID : NWAM0841E */
    public static final String NWAM0841E = "NWAM0841E";

    /** MessageID : NYXM0001I */
    public static final String NYXM0001I = "NYXM0001I";

    /** MessageID : NYXM0002E */
    public static final String NYXM0002E = "NYXM0002E";

    /** Message : RESULT_MSG_INS */
    public static final String RESULT_MSG_INS = "%d record(s) successfully created.";

    /** Message : RESULT_MSG_UPD */
    public static final String RESULT_MSG_UPD = "%d record(s) successfully updated.";

    /** Message : RESULT_MSG_ERR */
    public static final String RESULT_MSG_ERR = "%d record(s) skipped/errored.";

    /** Column Name : UPLD_CSV_RQST_ROW_NUM */
    public static final String UPLD_CSV_RQST_ROW_NUM = "UPLD_CSV_RQST_ROW_NUM";

    /** Column Name : DEF_WH_MAP_TMPL_CD */
    public static final String DEF_WH_MAP_TMPL_CD = "DEF_WH_MAP_TMPL_CD";

    /** Column Name : MDSE_ITEM_CLS_TP_DESC_TXT */
    public static final String MDSE_ITEM_CLS_TP_DESC_TXT = "MDSE_ITEM_CLS_TP_DESC_TXT";

    /** Column Name : THIRD_PTY_ITEM_FLG */
    public static final String THIRD_PTY_ITEM_FLG = "THIRD_PTY_ITEM_FLG";

    /** Column Name : FROM_POST_CD */
    public static final String FROM_POST_CD = "FROM_POST_CD";

    /** Column Name : TO_POST_CD */
    public static final String TO_POST_CD = "TO_POST_CD";

    /** Column Name : ON_HND_CHK_FLG */
    public static final String ON_HND_CHK_FLG = "ON_HND_CHK_FLG";

    /** Column Name : THIRD_PTY_DSP_TP_NM */
    public static final String THIRD_PTY_DSP_TP_NM = "THIRD_PTY_DSP_TP_NM";

    /** Column Name : DROP_RTRN_VND_NM */
    public static final String DROP_RTRN_VND_NM = "DROP_RTRN_VND_NM";

    /** Column Name : RMA_DEF_WH_CD */
    public static final String RMA_DEF_WH_CD = "RMA_DEF_WH_CD";

    /** Column Name : MDSE_ITEM_CLS_TP_CD */
    public static final String MDSE_ITEM_CLS_TP_CD = "MDSE_ITEM_CLS_TP_CD";

    /** Column Name : THIRD_PTY_DSP_TP_CD */
    public static final String THIRD_PTY_DSP_TP_CD = "THIRD_PTY_DSP_TP_CD";

    /** Column Name : DROP_RTRN_VND_CD */
    public static final String DROP_RTRN_VND_CD = "DROP_RTRN_VND_CD";

    /** CSV Column Name : DEF_WH_MAP_TMPL_CD */
    public static final String CSV_DEF_WH_MAP_TMPL_CD = "Mapping Template Name";

    /** CSV Column Name : MDSE_ITEM_CLS_TP_DESC_TXT */
    public static final String CSV_MDSE_ITEM_CLS_TP_DESC_TXT = "Item Sourcing Classification: Qualifier";

    /** CSV Column Name : THIRD_PTY_ITEM_FLG */
    public static final String CSV_THIRD_PTY_ITEM_FLG = "Third Party Flag";

    /** CSV Column Name : FROM_POST_CD */
    public static final String CSV_FROM_POST_CD = "Postal Code From";

    /** CSV Column Name : TO_POST_CD */
    public static final String CSV_TO_POST_CD = "Postal Code To";

    /** CSV Column Name : ON_HND_CHK_FLG */
    public static final String CSV_ON_HND_CHK_FLG = "On Hand Checking";

    /** CSV Column Name : THIRD_PTY_DSP_TP_NM */
    public static final String CSV_THIRD_PTY_DSP_TP_NM = "[RWA:Default WH] RMA Disposition";

    /** CSV Column Name : DROP_RTRN_VND_NM */
    public static final String CSV_DROP_RTRN_VND_NM = "[RWA:Default WH] Supplier";

    /** CSV Column Name : RMA_DEF_WH_CD */
    public static final String CSV_RMA_DEF_WH_CD = "[RWA:Default WH] WH";

}
