/**
 * <pre>
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB035001.constant;

/**
 *<pre>
 * Header Mass Upload Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/11/09   Hitachi         S.Nakatani      Create          QC#60399
 *</pre>
 */
public class NMAB035001Constant {

    /** Parameter @ is required. */
    public static final String NMAM8228E = "NMAM8228E";

    /** Given item type and mdse type combination is not accepted.Please review code table (INTG_PROD_CATG_CONV). */
    public static final String NMAM8358E = "NMAM8358E";

    /** Merchandise Type cannot be changed since COA Account of Inventory will be changed. */
    public static final String NMAM8707E = "NMAM8707E";

    /** Data select failure.  TableName = MDSE , key = MDSE_CD, value = [@] */
    public static final String NMAM8713E = "NMAM8713E";

    /** The entered Product Code does not exist in Coa Product Master. */
    public static final String NMAM8714E = "NMAM8714E";

    /** Product Code:ZZ(ADMINISTRATION) cannot be entered because entered Sales Account in Revenue. */
    public static final String NMAM8715E = "NMAM8715E";

    /** The entered Merchandise Type does not exist in Coa Project Master. */
    public static final String NMAM8716E = "NMAM8716E";

    /** The entered Marketing Model does not exist in Mkt Model Master. */
    public static final String NMAM8717E = "NMAM8717E";

    /** The entered [@] does not exist in Product Level 5 Master. */
    public static final String NMAM8718E = "NMAM8718E";

    /** The entered [@] does not exist in Material Group Level 1. */
    public static final String NMAM8719E = "NMAM8719E";

    /** The entered [@] does not exist in Material Group Level 2. */
    public static final String NMAM8720E = "NMAM8720E";

    /** The entered [@] does not exist in Material Group Level 3. */
    public static final String NMAM8721E = "NMAM8721E";

    /** [@] field is mandatory. */
    public static final String NMAM8722E = "NMAM8722E";

    /** [@] is invalid. */
    public static final String NWBM0097E = "NWBM0097E";

    /** The record cannot be updated. Table Name: [MDSE] */
    public static final String NMZM0375E = "NMZM0375E";

    /** Please confirm the error details. */
    public static final String NMAM8457E = "NMAM8457E";

    /** Please confirm the warning details. */
    public static final String NMAM8463E = "NMAM8463E";

    /** Error Msg */
    public static final String ZYPM0181E = "ZYPM0181E";

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

    /** Max FETCH_SIZE */
    public static final int MAX_FETCH_SIZE = 1000;

    /** GLOBAL_COMPANY_CODE */
    public static final String GLOBAL_COMPANY_CODE = "Global Company Code";

    /** PARAM_GLBL_CMPY_CD */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** PARAM_UPLD_CSV_RQST_PK */
    public static final String PARAM_UPLD_CSV_RQST_PK = "upldCsvRqstPk";

    /** MSG_UPLD_CSV_ID */
    public static final String MSG_UPLD_CSV_ID = "Upload Csv ID";

    /** MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** COA_PROD_CD */
    public static final String COA_PROD_CD = "COA_PROD_CD";

    /** COA_MDSE_TP_CD */
    public static final String COA_MDSE_TP_CD = "COA_MDSE_TP_CD";

    /** MKT_MDL_CD */
    public static final String MKT_MDL_CD = "MKT_MDL_CD";

    /** FRTH_PROD_CTRL_CD */
    public static final String FRTH_PROD_CTRL_CD = "FRTH_PROD_CTRL_CD";

    /** SLS_MAT_GRP_CD_01 */
    public static final String SLS_MAT_GRP_CD_01 = "SLS_MAT_GRP_CD_01";

    /** SLS_MAT_GRP_CD_02 */
    public static final String SLS_MAT_GRP_CD_02 = "SLS_MAT_GRP_CD_02";

    /** SLS_MAT_GRP_CD_03 */
    public static final String SLS_MAT_GRP_CD_03 = "SLS_MAT_GRP_CD_03";

    /** MSG_DESC_MDSE_CD */
    public static final String MSG_DESC_MDSE_CD = "Item Number";

    /** UPLD_CSV_RQST_CMNT_TXT */
    public static final String UPLD_CSV_RQST_CMNT_TXT = "UPLD_CSV_RQST_CMNT_TXT";

    /** UPLD_CSV_RQST_PK */
    public static final String UPLD_CSV_RQST_PK = "UPLD_CSV_RQST_PK";

    /** UPLD_CSV_RQST_ROW_NUM */
    public static final String UPLD_CSV_RQST_ROW_NUM = "UPLD_CSV_RQST_ROW_NUM";

    /** MSG_UPLD_CSV_RQST_PK */
    public static final String MSG_UPLD_CSV_RQST_PK = "Upload CSV Request PK";

    /** GET_WRK */
    public static final String GET_WRK = "getHeaderUpldWrk";
    
    /** REV_ACCT_START_WITH_CD */
    public static final String REV_ACCT_START_WITH_CD= "4";
    
    /** BLK */
    public static final String BLK = "BLK";

}
