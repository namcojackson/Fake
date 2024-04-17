/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB723001.constant;

/**
 * <pre>
 * Mass Upload Default WH Mapping Template
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   Hitachi         K.Kojima        Create          N/A
 * 2016/05/29   SRAA            K.Aratani       Update          QC#8596
 * </pre>
 */
public class NMAB723001Constant {

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

    /** MessageID : NWAM0841E */
    public static final String NWAM0841E = "NWAM0841E";
    
    /** MessageID : NMAM8075E */
    public static final String NMAM8075E = "NMAM8075E";
    
    /** MessageID : NYXM0001I */
    public static final String NYXM0001I = "NYXM0001I";

    /** MessageID : NYXM0002E */
    public static final String NYXM0002E = "NYXM0002E";

    /** Column Name : UPLD_CSV_RQST_ROW_NUM */
    public static final String UPLD_CSV_RQST_ROW_NUM = "UPLD_CSV_RQST_ROW_NUM";

    /** Column Name : DS_ACCT_NUM */
    public static final String DS_ACCT_NUM = "DS_ACCT_NUM";

    /** Column Name : DS_ACCT_NM */
    public static final String DS_ACCT_NM = "DS_ACCT_NM";

    /** Column Name : CSMP_NUM */
    public static final String CSMP_NUM = "CSMP_NUM";

    /** Column Name : DLR_REF_NUM */
    public static final String DLR_REF_NUM = "DLR_REF_NUM";

    /** Column Name : PRC_CATG_NM */
    public static final String PRC_CATG_NM = "PRC_CATG_NM";

    /** Column Name : PRC_CATG_CD */
    public static final String PRC_CATG_CD = "PRC_CATG_CD";
    
    /** Column Name : PRC_CONTR_NUM */
    public static final String PRC_CONTR_NUM = "PRC_CONTR_NUM";

    /** Column Name : ORIG_COA_BR_CD */
    public static final String ORIG_COA_BR_CD = "ORIG_COA_BR_CD";

    /** Column Name : RTL_DLR_CD */
    public static final String RTL_DLR_CD = "RTL_DLR_CD";

    /** Column Name : EFF_FROM_DT */
    public static final String EFF_FROM_DT = "EFF_FROM_DT";

    /** Column Name : EFF_THRU_DT */
    public static final String EFF_THRU_DT = "EFF_THRU_DT";

    /** Column Name : CUSA_REJ_DT */
    public static final String CUSA_REJ_DT = "CUSA_REJ_DT";

    /** Column Name : ERL_TRMN_DT */
    public static final String ERL_TRMN_DT = "ERL_TRMN_DT";

    /** Column Name : RNW_CSMP_NUM */
    public static final String RNW_CSMP_NUM = "RNW_CSMP_NUM";

    /** Column Name : UPLFT_EQUIP_RATIO */
    public static final String UPLFT_EQUIP_RATIO = "UPLFT_EQUIP_RATIO";

    /** Column Name : UPLFT_ASRY_RATIO */
    public static final String UPLFT_ASRY_RATIO = "UPLFT_ASRY_RATIO";

    /** Column Name : CSMP_NUM_CMNT_TXT */
    public static final String CSMP_NUM_CMNT_TXT = "CSMP_NUM_CMNT_TXT";

    /** Column Name : CSMP_CONTR_ACTV_FLG */
    public static final String CSMP_CONTR_ACTV_FLG = "CSMP_CONTR_ACTV_FLG";

    /** CSV Column Name : DS_ACCT_NUM */
    public static final String CSV_DS_ACCT_NUM = "Account Number";

    /** CSV Column Name : DS_ACCT_NM */
    public static final String CSV_DS_ACCT_NM = "Account Name";

    /** CSV Column Name : CSMP_NUM */
    public static final String CSV_CSMP_NUM = "CSMP Number";

    /** CSV Column Name : DLR_REF_NUM */
    public static final String CSV_DLR_REF_NUM = "CSA Number";

    /** CSV Column Name : PRC_CATG_NM */
    public static final String CSV_PRC_CATG_NM = "CSMP Level";

    /** CSV Column Name : PRC_CONTR_NUM */
    public static final String CSV_PRC_CONTR_NUM = "Contract Number";

    /** CSV Column Name : ORIG_COA_BR_CD */
    public static final String CSV_ORIG_COA_BR_CD = "Originating GL Branch Code";

    /** CSV Column Name : RTL_DLR_CD */
    public static final String CSV_RTL_DLR_CD = "Origin Dealer Code";

    /** CSV Column Name : EFF_FROM_DT */
    public static final String CSV_EFF_FROM_DT = "Effective Date";

    /** CSV Column Name : EFF_THRU_DT */
    public static final String CSV_EFF_THRU_DT = "Expiration Date";

    /** CSV Column Name : CUSA_REJ_DT */
    public static final String CSV_CUSA_REJ_DT = "Rejection Date";

    /** CSV Column Name : ERL_TRMN_DT */
    public static final String CSV_ERL_TRMN_DT = "Early Term Date";

    /** CSV Column Name : RNW_CSMP_NUM */
    public static final String CSV_RNW_CSMP_NUM = "Renewed CSMP#";

    /** CSV Column Name : UPLFT_EQUIP_RATIO */
    public static final String CSV_UPLFT_EQUIP_RATIO = "Markup Equipment";

    /** CSV Column Name : UPLFT_ASRY_RATIO */
    public static final String CSV_UPLFT_ASRY_RATIO = "Markup Accessory";

    /** CSV Column Name : CSMP_NUM_CMNT_TXT */
    public static final String CSV_CSMP_NUM_CMNT_TXT = "Notes";

    /** CSV Column Name : CSMP_CONTR_ACTV_FLG */
    public static final String CSV_CSMP_CONTR_ACTV_FLG = "Active";

}
