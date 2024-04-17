/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB008003;

public interface NFBB008003Constant {

    // ** Fixed Value ** //
    /** Commit timing */
    static final int INT_COM_LIM = 10000;

    /** yyyyMMdd */
    static final String YYYYMMDD = "yyyyMMdd";

    /** IF_PROC_STS_CD C */
    static final String IF_PROC_STS_CD_C = "C";

    // ** Interface ID ** //
    /** INTERFACE ID */
    static final String INTERFACE_ID_NFBI0145 = "NFBI0145";

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
    static final String SELECT_RECORD_FROM_CM_IF_OPEN_AP_DTL = "SELECT_RECORD_FROM_CM_IF_OPEN_AP_DTL";

    // ** DB Table Name's Fixed Value ** //
    /** DB Table Name */
    static final String NFBI0145_01 = "NFBI0145_01";

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
    static final String VND_CD = "VND_CD";

    /** DB Item Column Name */
    static final String VND_INV_NUM = "VND_INV_NUM";

    /** DB Item Column Name */
    static final String VND_INV_SQ_NUM = "VND_INV_SQ_NUM";

    /** DB Item Column Name */
    static final String AP_INV_LINE_NUM = "AP_INV_LINE_NUM";

    /** DB Item Column Name */
    static final String INVOICE_NUMBER_L15_IF = "INVOICE_NUMBER_L15_IF";

    /** DB Item Column Name */
    static final String LINE_NUMBER_L5_IF = "LINE_NUMBER_L5_IF";

    /** DB Item Column Name */
    static final String DESCRIPTION_L32_IF = "DESCRIPTION_L32_IF";

    /** DB Item Column Name */
    static final String INVOICE_AMOUNT_N15_IF = "INVOICE_AMOUNT_N15_IF";

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

}
