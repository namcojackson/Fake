/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB252001.constant;

/**
 * <pre>
 * Mass Upload Default WH Mapping Template
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   Hitachi         K.Kojima        Create          N/A
 * 2016/05/29   SRAA            K.Aratani       Update          QC#8596
 * 2016/06/16   Hitachi         Y.Takeno        Update          QC#8156
 * 2017/09/12   Fujitsu         T.Ogura         Update          QC#19805
 * </pre>
 */
public class NWAB252001Constant {

    /** BUSINESS_ID (NFBB1260) */
    public static final String BUSINESS_ID = "NWAB2520";

    /** PROGRAM_ID(NFBB1260) */
    public static final String PROGRAM_ID = BUSINESS_ID + "01";

    /** UploadCsvId : NWA2040001(Append) */
    public static final String UPLOAD_CSV_ID_APPEND = "NWA2040001";

    /** UploadCsvId : NWA2040002(Replace) */
    public static final String UPLOAD_CSV_ID_REPLACE = "NWA2040002";

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

    /** MessageID : NWAM0804E */
    public static final String NWAM0804E = "NWAM0804E";

    /** MessageID : NWAM0805E */
    public static final String NWAM0805E = "NWAM0805E";

    /** MessageID : NWAM0806E */
    public static final String NWAM0806E = "NWAM0806E";

    /** MessageID : NWAM0807E */
    public static final String NWAM0807E = "NWAM0807E";

    /** MessageID : NWAM0808E */
    public static final String NWAM0808E = "NWAM0808E";

    /** MessageID : ZZM9000E */
    public static final String ZZBM0009I = "ZZBM0009I";

    /** MessageID : ZZM9000E */
    public static final String ZZM9000E = "ZZM9000E";
    //QC#8596
    /** MessageID : NWAM0841E */
    public static final String NWAM0841E = "NWAM0841E";

    // START 2016/06/16 [QC#8156,ADD]
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

    // END 2016/06/16 [QC#8156,ADD]

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

    /** Column Name : OTBD_PRIM_ON_HND_CHK_WH_CD */
    public static final String OTBD_PRIM_ON_HND_CHK_WH_CD = "OTBD_PRIM_ON_HND_CHK_WH_CD";

    /** Column Name : OTBD_PRIM_ON_HND_CHK_SWH_NM */
    public static final String OTBD_PRIM_ON_HND_CHK_SWH_NM = "OTBD_PRIM_ON_HND_CHK_SWH_NM";

    /** Column Name : OTBD_PRIM_ON_HND_LIN_SRC_NM */
    public static final String OTBD_PRIM_ON_HND_LIN_SRC_NM = "OTBD_PRIM_ON_HND_LIN_SRC_NM";

    /** Column Name : OTBD_SCD_ON_HND_CHK_WH_CD */
    public static final String OTBD_SCD_ON_HND_CHK_WH_CD = "OTBD_SCD_ON_HND_CHK_WH_CD";

    /** Column Name : OTBD_SCD_ON_HND_CHK_SWH_NM */
    public static final String OTBD_SCD_ON_HND_CHK_SWH_NM = "OTBD_SCD_ON_HND_CHK_SWH_NM";

    /** Column Name : OTBD_SCD_ON_HND_LINE_SRC_NM */
    public static final String OTBD_SCD_ON_HND_LINE_SRC_NM = "OTBD_SCD_ON_HND_LINE_SRC_NM";

    /** Column Name : OTBD_DEF_WH_CD */
    public static final String OTBD_DEF_WH_CD = "OTBD_DEF_WH_CD";

    /** Column Name : OTBD_DEF_SWH_NM */
    public static final String OTBD_DEF_SWH_NM = "OTBD_DEF_SWH_NM";

    /** Column Name : OTBD_DEF_LINE_SRC_NM */
    public static final String OTBD_DEF_LINE_SRC_NM = "OTBD_DEF_LINE_SRC_NM";

    // 2017/09/12 QC#19805 Del Start
//    /** Column Name : RMA_DEF_WH_CD */
//    public static final String RMA_DEF_WH_CD = "RMA_DEF_WH_CD";
//
//    /** Column Name : RMA_DEF_SWH_NM */
//    public static final String RMA_DEF_SWH_NM = "RMA_DEF_SWH_NM";
//
//    /** Column Name : RMA_DEF_LINE_SRC_NM */
//    public static final String RMA_DEF_LINE_SRC_NM = "RMA_DEF_LINE_SRC_NM";
    // 2017/09/12 QC#19805 Del End

    /** Column Name : MDSE_ITEM_CLS_TP_CD */
    public static final String MDSE_ITEM_CLS_TP_CD = "MDSE_ITEM_CLS_TP_CD";

    /** Column Name : RTL_WH_CD */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /** Column Name : RTL_SWH_CD */
    public static final String RTL_SWH_CD = "RTL_SWH_CD";

    /** Column Name : ORD_LINE_SRC_CD */
    public static final String ORD_LINE_SRC_CD = "ORD_LINE_SRC_CD";

    /** Column Name : ORD_LINE_SRC_CATG_CD */
    public static final String ORD_LINE_SRC_CATG_CD = "ORD_LINE_SRC_CATG_CD";

    /** CSV Column Name : DEF_WH_MAP_TMPL_CD */
    public static final String CSV_DEF_WH_MAP_TMPL_CD = "Mapping Template Name";

    /** CSV Column Name : MDSE_ITEM_CLS_TP_DESC_TXT */
    // 2017/09/12 QC#19805 Mod Start
//    public static final String CSV_MDSE_ITEM_CLS_TP_DESC_TXT = "Item Sourcing Classfication: Qualifer";
    public static final String CSV_MDSE_ITEM_CLS_TP_DESC_TXT = "Item Sourcing Classification: Qualifier";
    // 2017/09/12 QC#19805 Mod End

    /** CSV Column Name : THIRD_PTY_ITEM_FLG */
    public static final String CSV_THIRD_PTY_ITEM_FLG = "Third Party Flag";

    /** CSV Column Name : FROM_POST_CD */
    public static final String CSV_FROM_POST_CD = "Postal Code From";

    /** CSV Column Name : TO_POST_CD */
    public static final String CSV_TO_POST_CD = "Postal Code To";

    /** CSV Column Name : ON_HND_CHK_FLG */
    public static final String CSV_ON_HND_CHK_FLG = "On Hand Checking";

    /** CSV Column Name : OTBD_PRIM_ON_HND_CHK_WH_CD */
    public static final String CSV_OTBD_PRIM_ON_HND_CHK_WH_CD = "[Primary On Hand Check] WH";

    /** CSV Column Name : OTBD_PRIM_ON_HND_CHK_SWH_NM */
    public static final String CSV_OTBD_PRIM_ON_HND_CHK_SWH_NM = "[Primary On Hand Check] SWH";

    /** CSV Column Name : OTBD_PRIM_ON_HND_LIN_SRC_NM */
    public static final String CSV_OTBD_PRIM_ON_HND_LIN_SRC_NM = "[Primary On Hand Check] Line Source Type";

    /** CSV Column Name : OTBD_SCD_ON_HND_CHK_WH_CD */
    public static final String CSV_OTBD_SCD_ON_HND_CHK_WH_CD = "[Secondary On Hand Check] WH";

    /** CSV Column Name : OTBD_SCD_ON_HND_CHK_SWH_NM */
    public static final String CSV_OTBD_SCD_ON_HND_CHK_SWH_NM = "[Secondary On Hand Check] SWH";

    /** CSV Column Name : OTBD_SCD_ON_HND_LINE_SRC_NM */
    public static final String CSV_OTBD_SCD_ON_HND_LINE_SRC_NM = "[Secondary On Hand Check] Line Source Type";

    /** CSV Column Name : OTBD_DEF_WH_CD */
    public static final String CSV_OTBD_DEF_WH_CD = "[OUTBOUND:Default WH(Not On Hand)] WH";

    /** CSV Column Name : OTBD_DEF_SWH_NM */
    public static final String CSV_OTBD_DEF_SWH_NM = "[OUTBOUND:Default WH(Not On Hand)] SWH";

    /** CSV Column Name : OTBD_DEF_LINE_SRC_NM */
    public static final String CSV_OTBD_DEF_LINE_SRC_NM = "[OUTBOUND:Default WH(Not On Hand)] Line Source Type";

    // 2017/09/12 QC#19805 Del Start
//    /** CSV Column Name : RMA_DEF_WH_CD */
//    public static final String CSV_RMA_DEF_WH_CD = "[RWA:Default WH] WH";
//
//    /** CSV Column Name : RMA_DEF_SWH_NM */
//    public static final String CSV_RMA_DEF_SWH_NM = "[RWA:Default WH] SWH";
//
//    /** CSV Column Name : RMA_DEF_LINE_SRC_NM */
//    public static final String CSV_RMA_DEF_LINE_SRC_NM = "[RWA:Default WH] Line Source Type";
    // 2017/09/12 QC#19805 Del End

}
