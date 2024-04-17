/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB110001.constant;
/**
 * <pre>    
 * Batch Program Class for ABC Analysis Result Import
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 07/05/2016   CITS            S.Endo      Create          
 * 
 *</pre>
 */
public class NLCB110001Constant {
    /** NLCM0187E/@ is required fields.*/
    public static final String MSG_NLCM0187E = "NLCM0187E";
    /** NLCM0188E/Different ABC Names exist in the import file. */
    public static final String MSG_NLCM0188E = "NLCM0188E";
    /** NLCM0189E/Different ABC Class Names exist in the import file. */
    public static final String MSG_NLCM0189E = "NLCM0189E";
    /** NLCM0190E/The combination of Item Number,Warehouse Code,Sub Warehouse Code,Stock Status Code is already exists. */
    public static final String MSG_NLCM0190E = "NLCM0190E";
    /** NLCM0191E/Different ABC Class Tag exists in the combination of Item Number,Warehouse Code. */
    public static final String MSG_NLCM0191E = "NLCM0191E";
    /** NLCM0192E/Upload CSV Request PK was not found at Upload Work Table. */
    public static final String MSG_NLCM0192E = "NLCM0192E";
    /** NLCM0199E/@ */
    public static final String MSG_NLCM0199E = "NLCM0199E";
    /** NLCM0200I/@ */
    public static final String MSG_NLCM0200I = "NLCM0200I";
    /** Message ID :NLCM0100E/ The record cannot be registered. Table Name[@] */
    public static final String MSG_ID_NLCM0100E = "NLCM0100E";
    /** Message ID :NLCM0206E/ The record cannot be updated. Table Name[@] */
    public static final String MSG_ID_NLCM0206E = "NLCM0206E";
    /** Message : RESULT_MSG_INS */
    public static final String RESULT_MSG_INS = "%d record(s) successfully created.";
    /** Message : RESULT_MSG_UPD */
    public static final String RESULT_MSG_UPD = "%d record(s) successfully updated.";
    /** Message : RESULT_MSG_ERR */
    public static final String RESULT_MSG_ERR = "%d record(s) skipped/errored.";
    /** ZZM9000E/[@] field is mandatory. */
    public static final String MSG_ZZM9000E = "ZZM9000E";
    /** NLCM0148E/The item @ does not exist in Master. */
    public static final String MSG_NLCM0148E = "NLCM0148E";
    /** DB TABLE : ABC_ANLS_RQST */
    public static final String TBL_ABC_ANLS_RQST = "ABC_ANLS_RQST";
    /** DB TABLE : ABC_ANLS_RSLT */
    public static final String TBL_ABC_ANLS_RSLT = "ABC_ANLS_RSLT";
    /** DB TABLE : ABC_ANLS_RSLT_DTL */
    public static final String TBL_ABC_ANLS_RSLT_DTL = "ABC_ANLS_RSLT_DTL";
    /** DB COLUMN : GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** DB COLUMN : UPLD_CSV_RQST_PK */
    public static final String COL_UPLD_CSV_RQST_PK = "UPLD_CSV_RQST_PK";
    /** DB COLUMN : UPLD_CSV_RQST_ROW_NUM */
    public static final String COL_UPLD_CSV_RQST_ROW_NUM = "UPLD_CSV_RQST_ROW_NUM";
    /** DB COLUMN : ABC_ASG_NM */
    public static final String COL_ABC_ASG_NM = "ABC_ASG_NM";
    /** DB COLUMN : MDSE_CD */
    public static final String COL_MDSE_CD = "MDSE_CD";
    /** DB COLUMN : RTL_WH_CD */
    public static final String COL_RTL_WH_CD = "RTL_WH_CD";
    /** DB COLUMN : RTL_SWH_CD */
    public static final String COL_RTL_SWH_CD = "RTL_SWH_CD";
    /** DB COLUMN : ABC_ANLS_CLS_NM */
    public static final String COL_ABC_ANLS_CLS_NM = "ABC_ANLS_CLS_NM";
    /** DB COLUMN : ABC_ANLS_CLS_TAG_CD */
    public static final String COL_ABC_ANLS_CLS_TAG_CD = "ABC_ANLS_CLS_TAG_CD";
    /** DB COLUMN : STK_STS_CD */
    public static final String COL_STK_STS_CD = "STK_STS_CD";
    /** DB COLUMN : ABC_ASG_PK */
    public static final String COL_ABC_ASG_PK = "ABC_ASG_PK";
    /** DB COLUMN : RQST_LTST_FLG */
    public static final String COL_RQST_LTST_FLG = "RQST_LTST_FLG";
    /** DB COLUMN : ABC_ANLS_LTST_FLG */
    public static final String COL_ABC_ANLS_LTST_FLG = "ABC_ANLS_LTST_FLG";
    /** DB COLUMN : CUR_INVTY_QTY */
    public static final String COL_CUR_INVTY_QTY = "CUR_INVTY_QTY";
    /** DB COLUMN : CUR_INVTY_COST_AMT */
    public static final String COL_CUR_INVTY_COST_AMT = "CUR_INVTY_COST_AMT";
    /** DB COLUMN : HIST_INVTY_TRX_QTY */
    public static final String COL_HIST_INVTY_TRX_QTY = "HIST_INVTY_TRX_QTY";
    /** DB COLUMN : HIST_INVTY_TRX_COST_AMT */
    public static final String COL_HIST_INVTY_TRX_COST_AMT = "HIST_INVTY_TRX_COST_AMT";
    /** DB COLUMN : HIST_INVTY_TRX_REC_CNT */
    public static final String COL_HIST_INVTY_TRX_REC_CNT = "HIST_INVTY_TRX_REC_CNT";
    /** CSV COLUMN : ABC Name */
    public static final String CSV_ABC_NAME = "ABC Name";
    /** CSV COLUMN : Item Number */
    public static final String CSV_ITEM_NUMBER = "Item Number";
    /** CSV COLUMN : Warehouse Code */
    public static final String CSV_WAREHOUSE_CODE = "Warehouse Code";
    /** CSV COLUMN : ABC Class Name */
    public static final String CSV_ABC_CLASS_NAME = "ABC Class Name";
    /** CSV COLUMN : ABC Class Tag */
    public static final String CSV_ABC_CLASS_TAG = "ABC Class Tag";
    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;
    /** Empty String */
    public static final String EMPTY = "";
    /** Complete Code for ABC Analysis Request */
    public static final String COMPLETE_CD = "2";
    /** Implement Code for ABC Analysis Request */
    public static final String IMPLEMENT = "IMPL";
}
