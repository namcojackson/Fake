package com.canon.cusa.s21.batch.NFC.NFCB303001;

public interface NFCB3030Constant {
    /** Message Id */
    String NFCM0501E = "NFCM0501E";
    
    /** Fixed Value : Each Bulk Insert Count */
    int BULK_INSERT_COUNT = 10000;
    
    /** DB Field **/
    String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** DB Field **/
    String AR_WRT_OFF_RQST_PK = "AR_WRT_OFF_RQST_PK";
    /** DB Field **/
    String WRT_OFF_RQST_GRP_CD = "WRT_OFF_RQST_GRP_CD";
    /** DB Field **/
    String WRT_OFF_RQST_USR_ID = "WRT_OFF_RQST_USR_ID";
    /** DB Field **/
    String AR_ADJ_RSN_CD = "AR_ADJ_RSN_CD";
    /** DB Field **/
    String AR_ADJ_RSN_NM = "AR_ADJ_RSN_NM";
    /** DB Field **/
    String AR_ADJ_TP_CD = "AR_ADJ_TP_CD";
    /** DB Field **/
    String AR_ADJ_TP_NM = "AR_ADJ_TP_NM";
    /** DB Field **/
    String AR_ADJ_CATG_CD = "AR_ADJ_CATG_CD";
    /** DB Field **/
    String LOW_RMNG_BAL_AMT = "LOW_RMNG_BAL_AMT";
    /** DB Field **/
    String HIGH_RMNG_BAL_AMT = "HIGH_RMNG_BAL_AMT";
    /** DB Field **/
    String LOW_INV_NUM = "LOW_INV_NUM";
    /** DB Field **/
    String HIGH_INV_NUM = "HIGH_INV_NUM";
    /** DB Field **/
    String LOW_INV_DUE_DT = "LOW_INV_DUE_DT";
    /** DB Field **/
    String HIGH_INV_DUE_DT = "HIGH_INV_DUE_DT";
    /** DB Field **/
    String LOW_DS_ACCT_NUM = "LOW_DS_ACCT_NUM";
    /** DB Field **/
    String HIGH_DS_ACCT_NUM = "HIGH_DS_ACCT_NUM";
    /** DB Field **/
    String LOW_BILL_TO_CUST_CD = "LOW_BILL_TO_CUST_CD";
    /** DB Field **/
    String HIGH_BILL_TO_CUST_CD = "HIGH_BILL_TO_CUST_CD";
    /** DB Field **/
    String INV_TP_CD = "INV_TP_CD";
    /** DB Field **/
    String INCL_CONSL_INV_FLG = "INCL_CONSL_INV_FLG"; 
    /** DB Field **/
    String WRT_OFF_OPT_TP_CD = "WRT_OFF_OPT_TP_CD";
    /** DB Field **/
    String WRT_OFF_APPLY_AMT = "WRT_OFF_APPLY_AMT";
    /** DB Field **/
    String AR_WRT_OFF_NOTE_CD = "AR_WRT_OFF_NOTE_CD";
    /** DB Field **/
    String AR_WRT_OFF_NOTE_TXT = "AR_WRT_OFF_NOTE_TXT";
    /** DB Field **/
    String AR_WRT_OFF_RQST_TP_CD = "AR_WRT_OFF_RQST_TP_CD";
    /** DB Field **/
    String AR_DS_WF_STS_CD = "AR_DS_WF_STS_CD";
    /** DB Field **/
    String PROC_STS_CD = "PROC_STS_CD";
    /** DB Field **/
    String WRT_OFF_PROC_DT = "WRT_OFF_PROC_DT";
    /** DB Field **/
    String APVL_LIMIT_FROM_AMT = "APVL_LIMIT_FROM_AMT";
    /** DB Field **/
    String APVL_LIMIT_TO_AMT = "APVL_LIMIT_TO_AMT";
    /** DB Field **/
    String EML_ADDR = "EML_ADDR";
    /** DB Field **/
    String EZINTIME = "EZINTIME";

    // add start 2022/11/28 QC#57252
    /** DB field */
    String ADJ_COA_CMPY_CD = "ADJ_COA_CMPY_CD";
    /** DB field */
    String ADJ_COA_BR_CD = "ADJ_COA_BR_CD";
    /** DB field */
    String ADJ_COA_CC_CD = "ADJ_COA_CC_CD";
    /** DB field */
    String ADJ_COA_ACCT_CD = "ADJ_COA_ACCT_CD";
    /** DB field */
    String ADJ_COA_PROD_CD = "ADJ_COA_PROD_CD";
    /** DB field */
    String ADJ_COA_CH_CD = "ADJ_COA_CH_CD";
    /** DB field */
    String ADJ_COA_AFFL_CD = "ADJ_COA_AFFL_CD";
    /** DB field */
    String ADJ_COA_PROJ_CD = "ADJ_COA_PROJ_CD";
    /** DB field */
    String ADJ_COA_EXTN_CD = "ADJ_COA_EXTN_CD";

    /** AR_ADJ_TP: OTHER **/
    String AR_ADJ_TP_OTHER = "OTHER";
    // add end 2022/11/28 QC#57252

    /**
     * AR_RCPT_TOC_CD Key for VAR_CHAR_CONST("AR_RCPT_TOC_CD")
     */
    String AR_RCPT_CONST_KEY_AR_RCPT_TOC_CD = "AR_RCPT_TOC_CD";

    /**
     * AR_RCPT_PROD_CD Key for VAR_CHAR_CONST("AR_RCPT_PROD_CD")
     */
    String AR_RCPT_CONST_KEY_AR_RCPT_PROD_CD = "AR_RCPT_PROD_CD";
    
    /** CONST KEY Field **/
    String CONST_KEY_AR_WRT_OFF_INV = "AR_WRT_OFF_INV";
    
    /** CONST KEY Field **/
    String CONST_KEY_AR_WRT_OFF_CRM = "AR_WRT_OFF_CRM";
    
    /** CONST KEY Field **/
    String CONST_KEY_AR_CLT_DEF_EML_ADDR = "AR_CLT_DEF_EML_ADDR";
    
    /**
     * RCPT_BAT_SQ_NUM Key for
     * VAR_CHAR_CONST("AR_PUR_RCPT_BAT_SQ_NUM")
     */
    String AR_RCPT_CONST_KEY_RCPT_BAT_SQ_NUM = "AR_PUR_RCPT_BAT_SQ_NUM";
    
    /**
     * ReceiptNumberingKey("RC#")
     */
    String BIZAPL_RCPTNUMKEY = "RC#";
    
    /**
     * ArTrxNum StartNumber(0)
     */
    int AR_TRX_BAL_SQ_START_NUM = 0;
    
    /**
     * ApplyBatchAPI RtnCd-UnProcces("0")
     */
    String APPLY_RTNCD_UN_PROCCES = "0";

    /**
     * ApplyBatchAPI RtnCd-Normal("1")
     */
    String APPLY_RTNCD_NORMAL = "1";

    /**
     * ApplyBatchAPI RtnCd-CashappError("2")
     */
    String APPLY_RTNCD_CASHAPP_ERROR = "2";

    /**
     * ApplyBatchAPI RtnCd-ExclusionError("3")
     */
    String APPLY_RTNCD_EXCLUSION_ERROR = "3";

    /**
     * ApplyBatchAPI RtnCd-OthersError("4")
     */
    String APPLY_RTNCD_OTHERS_ERROR = "4";
    
    /** mail group id for From */
    String MAIL_GROUP_ID_FROM = "FROM0003";

    /** template ID */
    String MAIL_TMPL_ID = "NFCB3030M001";
    
    /** template parameter key : applyGrpKey */
    String MAIL_TMPL_KEY_APPLY_GRP_KEY = "applyGrpKey";
    /** template parameter key : requestDate */
    String MAIL_TMPL_KEY_RQST_DT = "requestDate";
    
    /** message **/
    String NFCM0768E = "NFCM0768E";  // Request Parameter is not valid
    
    
    /** message **/
    String NFCM0624E = "NFCM0624E";  // Unexpected error has occurred.
    
    /** message **/
    String NFCM0769E = "NFCM0769E";  // Approval Limit is not found for the user.
    
    /** message **/
    String NFCM0084E = "NFCM0084E";  // The adjustment amount exceeds the limit.
    
    /** message **/
    String NFDM0003E = "NFDM0003E";  // Unexpected Error Occurred [@].
    
    /** message **/
    String NFCM0770E = "NFCM0770E";  // Write Off request could not be updated.
    
    /** message **/
    String NFCM0771E = "NFCM0771E";  // This record is not processed due to error of other record of the same customer.
    
    /** message **/
    String NFCM0772E = "NFCM0772E";  // This customer is not allowed for Auto Write-Off.
    
    /** message **/
    String NFCM0773E = "NFCM0773E";  // This record didn't exist when request was issued. If you need to write-off, please tye again.
    
    /** message **/
    String NFCM0594E = "NFCM0594E";  // @ has been updated by another user.
    
    /** message **/
    String NFCM0532E = "NFCM0532E";  // @ cannot be added.
    
    /** message **/
    String NFCM0002E = "NFCM0002E";  // Data could not be added due to [@]. Please try again.
    
    /** message **/
    String NFCM0533E = "NFCM0533E";  // @ cannot be updated.
    
    /** message **/
    String NFCM0774E = "NFCM0774E";  // "Email could not be sent.
    
}
