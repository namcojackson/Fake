/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB008002;

public interface NFBB008002Constant {

    // ** Fixed Value ** //
    /** Commit timing */
    static final int INT_COM_LIM = 10000;

    /** yyyyMMdd */
    static final String YYYYMMDD = "yyyyMMdd";

    /** IF_PROC_STS_CD C */
    static final String IF_PROC_STS_CD_C = "C";

    // ** Interface ID ** //
    /** INTERFACE ID */
    static final String INTERFACE_ID_NFBI0140 = "NFBI0140";

    /** SEGMENT ID */
    static final int SEGMENT_ID = 1;

    /** SEQ NUMBER */
    static final int SEQ_NUMBER = 1;

    // ** Message ID ** //
    /** Information Message */
    static final String NFBM0028E = "NFBM0028E";

    /** Information Message */
    static final String ZZBM0009I = "ZZBM0009I";

    /** Information Message */
    static final String ZZIM0009I = "ZZIM0009I";

    /** Error Message */
    static final String ZZBM0074E = "ZZBM0074E";

    /** Error Message */
    static final String ZZIM1201E = "ZZIM1201E";

    // ** SSM Statement ID's Fixed Value ** //
    /** SSM Statement ID */
    static final String SELECT_CM_PROC_DT = "SELECT_CM_PROC_DT";

    /** SSM Statement ID */
    static final String SELECT_RECORD_FROM_CM_IF_OPEN_AP_HDR = "SELECT_RECORD_FROM_CM_IF_OPEN_AP_HDR";

    // ** DB Table Name's Fixed Value ** //
    /** DB Table Name */
    static final String NFBI0140_01 = "NFBI0140_01";

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String CM_PROC_DT = "CM_PROC_DT";

    /** DB Item Column Name */
    static final String AP_INV_ID = "AP_INV_ID";

    /** DB Item Column Name */
    static final String AP_VND_CD = "AP_VND_CD";

    /** DB Item Column Name */
    static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";

    /** DB Item Column Name */
    static final String AP_VND_INV_SQ_NUM = "AP_VND_INV_SQ_NUM";

    /** DB Item Column Name */
    static final String REM_SQ_NUM = "REM_SQ_NUM";

    /** DB Item Column Name */
    static final String INVOICE_NUM_L50_IF = "INVOICE_NUM_L50_IF";

    /** DB Item Column Name */
    static final String INVOICE_DATE_L8_IF = "INVOICE_DATE_L8_IF";

    /** DB Item Column Name */
    static final String CREATION_DATE_L8_IF = "CREATION_DATE_L8_IF";

    /** DB Item Column Name */
    static final String UPDATE_DATE_L8_IF = "UPDATE_DATE_L8_IF";

    /** DB Item Column Name */
    static final String INVOICE_AMOUNT_N15_IF = "INVOICE_AMOUNT_N15_IF";

    /** DB Item Column Name */
    static final String SOURCE_L20_IF = "SOURCE_L20_IF";

    /** DB Item Column Name */
    static final String DESCRIPTION_L240_IF = "DESCRIPTION_L240_IF";

    /** DB Item Column Name */
    static final String COA_1_L3_IF = "COA_1_L3_IF";

    /** DB Item Column Name */
    static final String COA_2_L3_IF = "COA_2_L3_IF";

    /** DB Item Column Name */
    static final String COA_3_L6_IF = "COA_3_L6_IF";

    /** DB Item Column Name */
    static final String COA_4_L8_IF = "COA_4_L8_IF";

    /** DB Item Column Name */
    static final String COA_5_L2_IF = "COA_5_L2_IF";

    /** DB Item Column Name */
    static final String COA_6_L3_IF = "COA_6_L3_IF";

    /** DB Item Column Name */
    static final String COA_7_L3_IF = "COA_7_L3_IF";

    /** DB Item Column Name */
    static final String COA_8_L4_IF = "COA_8_L4_IF";

    /** DB Item Column Name */
    static final String COA_9_L3_IF = "COA_9_L3_IF";

    /** DB Item Column Name */
    static final String VENDOR_SITE_ID_L15_IF = "VENDOR_SITE_ID_L15_IF";

    /** DB Item Column Name */
    static final String TERMS_NAME_L50_IF = "TERMS_NAME_L50_IF";

    /** DB Item Column Name */
    static final String EXCL_PAY_FLAG_L1_IF = "EXCL_PAY_FLAG_L1_IF";
    
    /** DB Item Column Name */
    static final String CODE_CURRENCY_L3_IF = "CODE_CURRENCY_L3_IF";

    /** DB Item Column Name */
    static final String EXCHANGE_RATE_N15_IF = "EXCHANGE_RATE_N15_IF";

    /** DB Item Column Name */
    static final String EXCHANGE_DATE_L8_IF = "EXCHANGE_DATE_L8_IF";

    /** DB Item Column Name */
    static final String DOC_ATT_L240_IF = "DOC_ATT_L240_IF";

    /** DB Item Column Name */
    static final String ACCOUNTING_DATE_L8_IF = "ACCOUNTING_DATE_L8_IF";

    //START 2016/09/23 K.Kasai [QC#14354,ADD]
    /** Var Char Const Name */
    static final String CONST_NFBB008002_AP_INV_SRC_NM = "NFBB008002_AP_INV_SRC_NM";

    /** Default AP_INV_SRC_NM */
    static final String DFT_AP_INV_SRC_NM = "S21 Account Payable";
    //END 2016/09/23 K.Kasai [QC#14354,ADD]

}
