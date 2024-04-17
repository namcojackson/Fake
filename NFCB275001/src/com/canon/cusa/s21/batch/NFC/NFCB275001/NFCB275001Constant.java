package com.canon.cusa.s21.batch.NFC.NFCB275001;

/**
 * Class name: NFCB275001Constant
 * <dd>The class explanation: Constant variable for NFCB275001.
 * <dd>Remarks:
 * @version 1.0
 * @author K.Uramori
 */
public interface NFCB275001Constant {

    // ** Fixed Value ** //
    /** Fixed Value : Error Message Parameter */
    static final String MSG_PARAM = "AR Reclass Entry Process";

    /** Fixed Value : AJE Interface Type Code for Merchandise Reclass */
//    static final String AJE_INTFC_TP_CD_AR_RCLS = "14";

    /** Fixed Value : Journal Completion Flag Value for Y */
    //static final String JRNL_CPLT_FLG_Y_VAL = "Y";

    /** Fixed Value : Merchandise or Parts Code for Merchandise */
//    static final String MDSE_OR_PRT_CD_M_VAL = "M";

    /** Fixed Value : Journal Completion Flag Value for Y */
    static final String TRX_RSN_CD_WRT_DOWN_Y_VAL = "Y";

    /** Fixed Value : Transaction Code Value for Cost Calculation */
//    static final String TRX_CD_VAL = "170";

    /** Fixed Value : Transaction Reason Code Value for Reclass */
//    static final String TRX_RSN_CD_RCLS = "05";

    /** Fixed Value : Transaction Reason Code Value for Reclass Reversal */
//    static final String TRX_RSN_CD_RVSL = "06";

    /** Fixed Value : Location Status value '01' (In Transit) */
//    static final String LOC_STS_01_VAL = "01";
    
    /** Fixed Value : Asset Code Value */
//    static final String ASSET_CD_VAL = "I";

    /** Fixed Value : Each Bulk Insert Count */
    static final int BULK_INSERT_COUNT = 10000;
    
    /** Fixed Value : Blank for null value */
    static final String BLANK = "";
    
    /** Fixed Value : Inventory Location Type Code: Warehouse */
//    static final String INVTY_LOC_TP_CD_WAREHOUSE = "01";
    
    /** Fixed Value : Inventory Location Type Code: Consignee */
//    static final String INVTY_LOC_TP_CD_CONSIGNEE = "05";
    
    /** Fixed Value : Inventory Location Type Code: Vendor */
//    static final String INVTY_LOC_TP_CD_VENDOR = "03";
    
    /** Fixed Value : VendorType Code: Kitting */
//    static final String VND_TP_CD_KITTING = "09";
    
    /** Fixed Value : Vendor Type Code: Refurbish */
//    static final String VND_TP_CD_REFURBISH = "10";

    /** Fixed Value : Default COA_AFFL_CD */
//    static final String COA_AFFL_CD_001 = "001";

    // ** DB Item Column Name's Fixed Value ** //    
    /** DB Item Column Name */
    static final String AJE_AR_RECLS_INTFC_SQ = "AJE_AR_RECLS_INTFC_SQ";

    /** DB Item Column Name */
    static final String AJE_AR_RECLS_INTFC_PK = "AJE_AR_RECLS_INTFC_PK";

    /** DB Item Column Name */
    static final String PROC_DT = "PROC_DT";

    /** DB Item Column Name */
    static final String GL_DT = "GL_DT";

    /** DB Item Column Name */
//    static final String INVTY_SNAP_SHOT_PK = "INVTY_SNAP_SHOT_PK";

    /** DB Item Column Name */
//    static final String INVTY_SNAP_SHOT_DT = "INVTY_SNAP_SHOT_DT";

    /** DB Item Column Name */
//    static final String MDSE_CD = "MDSE_CD";

    /** DB Item Column Name */
//    static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** DB Item Column Name */
//    static final String LOC_STS_CD = "LOC_STS_CD";

    /** DB Item Column Name */
//    static final String STK_STS_CD = "STK_STS_CD";

    /** DB Item Column Name */
//    static final String INVTY_QTY = "INVTY_QTY";

    /** DB Item Column Name */
//    static final String ASSET_CD = "ASSET_CD";

    /** DB Item Column Name */
//    static final String COA_PROD_CD = "COA_PROD_CD";

    /** DB Item Column Name */
//    static final String THIS_MTH_TOT_STD_COST_AMT = "THIS_MTH_TOT_STD_COST_AMT";

    /** DB Item Column Name */
//    static final String COST_CCY_CD = "COST_CCY_CD";

    /** DB Item Column Name */
    static final String DEAL_CCY_CD = "DEAL_CCY_CD";

    /** DB Item Column Name */
    static final String FUNC_CCY_CD = "FUNC_CCY_CD";

    /** DB Item Column Name */
//    static final String VND_CD = "VND_CD";

    /** DB Item Column Name */
    static final String AR_TRX_BAL_PK = "AR_TRX_BAL_PK";
    /** DB Item Column Name */
    static final String AR_TRX_NUM = "AR_TRX_NUM";
    /** DB Item Column Name */
    static final String AR_TRX_TP_CD = "AR_TRX_TP_CD";
    /** DB Item Column Name */
    static final String AR_TRX_DT = "AR_TRX_DT";

    /** DB Item Column Name */
//    static final String RECLS_AMT = "RECLS_AMT";
    /** DB Item Column Name */
    static final String DEAL_RMNG_BAL_GRS_AMT = "DEAL_RMNG_BAL_GRS_AMT";
    /** DB Item Column Name */
    static final String FUNC_RMNG_BAL_GRS_AMT = "FUNC_RMNG_BAL_GRS_AMT";

    /** DB Item Column Name */
    static final String BILL_TO_CUST_ACCT_CD = "BILL_TO_CUST_ACCT_CD";

    /** DB Item Column Name */
    static final String COA_AFFL_CD = "COA_AFFL_CD";
    
    /** DB Item Column Name */
    static final String COA_ACCT_CD = "COA_ACCT_CD";
   
    /** DB Item Column Name */
//    static final String MDSE_CATG_CD = "MDSE_CATG_CD";
    
    /** DB Item Column Name */
    static final String COA_PROJ_CD = "COA_PROJ_CD";
    
    /** DB Item Column Name */
    static final String COA_CC_CD = "COA_CC_CD";
  
    /** DB Item Column Name */
    static final String COA_CH_CD = "COA_CH_CD";
    
    /** DB Item Column Name */
    static final String COA_BR_CD = "COA_BR_CD";
    
    /** DB Item Column Name */
    static final String COA_EXTN_CD = "COA_EXTN_CD";
    
    /** DB Item Column Name */
//    static final String RTL_WH_CD = "RTL_WH_CD";
    
    /** DB Item Column Name */
//    static final String RTL_SWH_CD = "RTL_SWH_CD";
    
    /** DB Item Column Name */
//    static final String MDSE_COST_TP_CD = "MDSE_COST_TP_CD";
    
    /** DB Item Column Name */
//    static final String MDSE_INVTY_COST_PCT = "MDSE_INVTY_COST_PCT";

    /** Fixed Value : Report Condition - Unidentified */
    public static final String PARAM_VAL_RPT_COND_CONST_VAL_RS_UNID = "@RS-UNID";
    /** Fixed Value : Report Condition - Unapplied */
    public static final String PARAM_VAL_RPT_COND_CONST_VAL_RS_UNAPL = "@RS-UNAPL";
    /** Fixed Value : Report Condition - Cash */
    public static final String PARAM_VAL_RPT_COND_CONST_VAL_RS_CASH = "@RS-CASH";
    /** Fixed Value : Report Condition - OnAccount */
    public static final String PARAM_VAL_RPT_COND_CONST_VAL_RS_OACC = "@RS-OACC";
    /** Fixed Value : Report Condition Group ID */
    public static final String PARAM_VAL_RPT_COND_CONST_GRP_ID = "NFCB5010_RCPT_COA";
    /** Fixed Value : Report Condition Code */
    public static final String PARAM_VAL_RPT_COND_CONST_CD_RCPT_IDENTIFIED = "RCPT IDENTIFIED";

    /** Fixed Value : Default COA Account */
    public static final String PARAM_VAL_COA_ACCT_CD_DEF = "00000000";
    /** Fixed Value : Default COA Affiliate */
    public static final String PARAM_VAL_COA_AFFL_CD_DEF = "000";
    /** Fixed Value : Default COA Cost Center */
    public static final String PARAM_VAL_COA_CC_CD_DEF = "000000";

}