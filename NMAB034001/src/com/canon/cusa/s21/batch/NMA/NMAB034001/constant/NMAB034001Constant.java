/**
 * <pre>
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB034001.constant;

/**
 *<pre>
 * Contact Mass Upload Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/07/13   CITS            M.Furugoori     Create          QC#55448
 *</pre>
 */
public class NMAB034001Constant {

    /** Parameter @ is required. */
    public static final String NMAM8228E = "NMAM8228E";

    /** Item Number [@] is not exist in Master. */
    public static final String NMAM8709E = "NMAM8709E";

    /** Tax Code [@] is not exist in Master. */
    public static final String NMAM8710E = "NMAM8710E";

    /** [MDSE] for update target is not found. */
    public static final String NMZM0374E = "NMZM0374E";

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

    /** NUM_3 */
    public static final int NUM_3 = 3;

    /** NUM_4 */
    public static final int NUM_4 = 4;

    /** GLOBAL_COMPANY_CODE */
    public static final String GLOBAL_COMPANY_CODE = "Global Company Code";

    /** PARAM_GLBL_CMPY_CD */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** PARAM_UPLD_CSV_RQST_PK */
    public static final String PARAM_UPLD_CSV_RQST_PK = "upldCsvRqstPk";

    /** MSG_UPLD_CSV_ID */
    public static final String MSG_UPLD_CSV_ID = "Upload Csv ID";

    /** PARAM_MDSE_CD */
    public static final String PARAM_MDSE_CD = "mdseCd";

    /** PARAM_TAX_EXEM_TP_DESC_TXT */
    public static final String PARAM_TAX_EXEM_TP_DESC_TXT = "taxExemTpDescTxt";

    /** MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** TAX_EXEM_TP_DESC_TXT */
    public static final String TAX_EXEM_TP_DESC_TXT = "TAX_EXEM_TP_DESC_TXT";

    /** MSG_DESC_MDSE_CD */
    public static final String MSG_DESC_MDSE_CD = "Item Number";

    /** UPLD_CSV_RQST_CMNT_TXT */
    public static final String UPLD_CSV_RQST_CMNT_TXT = "UPLD_CSV_RQST_CMNT_TXT";

    /** UPLD_CSV_RQST_PK */
    public static final String UPLD_CSV_RQST_PK = "UPLD_CSV_RQST_PK";

    /** UPLD_CSV_RQST_ROW_NUM */
    public static final String UPLD_CSV_RQST_ROW_NUM = "UPLD_CSV_RQST_ROW_NUM";

    /** COUNT */
    public static final String COUNT = "COUNT";

    /** MSG_UPLD_CSV_RQST_PK */
    public static final String MSG_UPLD_CSV_RQST_PK = "Upload CSV Request PK";

    /** GET_WRK */
    public static final String GET_WRK = "getTaxUpldWrk";

    /** CHK_PRMRY */
    public static final String CNT_MDSE = "countMdse";

    /** GET_TAX_EXEM_TP */
    public static final String GET_TAX_EXEM_TP = "getTaxExemTp";

}
