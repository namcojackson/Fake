/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB003001;

/**
 * <pre>
 * MDSE to WMS
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/14/2013   CSAI            K.Hayashida     Create          MW Replace Initial
 *</pre>
 */
public interface NLGB003001Constant {

    /** Business ID */
    String BUSINESS_ID = "NLGB0030";

    /** Output Log Program ID */
    String PROGRAM_ID = "NLGB003001:";

    /**
     * The corresponding data does not exist. Table Name : [@], Key
     * Field Name: [@], Key Value: [@]
     */
    String NLGM0044E = "NLGM0044E";

    /**
     * The record cannot be updated. Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    String NLGM0046E = "NLGM0046E";

    /**
     * Warehouse code to be processed is not set. Please check the WMS
     * warehouse table. MW_REPL_TRGT_GRP_CD: [@]
     */
    String NLGM0047E = "NLGM0047E";

    /**
     * [@] Parameter has not been set.
     */
    String NLGM0049E = "NLGM0049E";

    /**
     * The record cannot be registered. Table Name: [@], Field Name:
     * [@], Key Value: [@]
     */
    String NLGM0007E = "NLGM0007E";

    /** Prameter Name: GLBL_CMPY_CD */
    String PRAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Prameter Name: WH_GRP_CD */
    String PRAM_NM_WH_GRP_CD = "Warehouse Group Code";

    /** DB Column: P_WH_LIST */
    String COL_P_WH_LIST = "P_WH_LIST";

    /** DB Column: P_PARTS_NUM */
    String COL_P_PARTS_NUM = "P_PARTS_NUM";

    /** DB Column: P_CODE_PROD_MAIN */
    String COL_P_CODE_PROD_MAIN = "P_CODE_PROD_MAIN";

    /** DB Column: P_DESC_PART */
    String COL_P_DESC_PART = "P_DESC_PART";

    /** DB Column: P_CODE_VENDR */
    String COL_P_CODE_VENDR = "P_CODE_VENDR";

    /** DB Column: P_COST_BASE_STD */
    String COL_P_COST_BASE_STD = "P_COST_BASE_STD";

    /** DB Column: P_COST_BASE_STD_NEW */
    String COL_P_COST_BASE_STD_NEW = "P_COST_BASE_STD_NEW";

    /** DB Column: P_INVTY_CNT_CATG */
    String COL_P_INVTY_CNT_CATG = "P_INVTY_CNT_CATG";

    /** DB Column: P_NUM_TSUS_T88 */
    String COL_P_NUM_TSUS_T88 = "P_NUM_TSUS_T88";

    /** DB Column: P_FLG_HAZMAT */
    String COL_P_FLG_HAZMAT = "P_FLG_HAZMAT";

    /** DB Column: EZUPTIME */
    String COL_EZUPTIME = "EZUPTIME";

    /** DB Column: WH_CD */
    String COL_WH_CD = "WH_CD";

    /** DB Column: ABC_ANLS_CLS_TAG_CD */
    String COL_ABC_ANLS_CLS_TAG_CD = "ABC_ANLS_CLS_TAG_CD";

    /** DB Column: CYCLE_CNT_FREQ_DAYS_AOT */
    String COL_CYCLE_CNT_FREQ_DAYS_AOT = "CYCLE_CNT_FREQ_DAYS_AOT";
    
    /** Value : WMS_ORG_HOST_ID */
    String VAL_WMS_ORG_HOST_ID_PARTS = "S21Parts";

    /** Value : WMS_MDSE_CD */
    String VAL_WMS_MDSE_CD_PARTS = "PARTS_COMMON_CD";

    /** Value : P_FILE_ID */
    String VAL_P_FILE_ID = "PARTS_TO_WMS";

    /** Value : INTFC_TP_ID */
    String VAL_INTFC_TP_ID_03 = "03";

    /** Value : INTFC_REC_TP_ID */
    String VAL_INTFC_REC_TP_ID_1 = "1";

    /** Value : WMS_CMPY_CD */
    String VAL_WMS_CMPY_CD_01 = "01";

    /** Value : INVTY_CTRL_CD */
    String VAL_INVTY_CTRL_CD_1 = "1";

    /** Value : , */
    String VAL_COMMA = ",";

    /** Value : : */
    String VAL_COLON = ":";
}
