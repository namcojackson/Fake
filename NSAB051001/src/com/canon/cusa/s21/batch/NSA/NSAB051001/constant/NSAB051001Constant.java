/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB051001.constant;

/**
 *<pre>
 * NSAB0510: CUSA Retail Contract Interface Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2016   CITS            M.Okigami         Create          N/A
 * 01/11/2017   CITS            T.Kikuhara        Update          QC#15484
 *</pre>
 */
public class NSAB051001Constant {

    /** business_id */
    public static final String BUSINESS_ID = "NSAB0510";

    // =================================================
    // Message Code
    // =================================================
    /** Global Company Code is mandatory. */
    public static final String NASM0010E = "NASM0010E";

    /** Sales date cannot be obtained. */
    public static final String NASM0011E = "NASM0011E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    // =================================================
    // DB Param
    // =================================================
    /** GLBL_CMPY_CD */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** ROSS_INTFC_CONTR_PROC_CD */
    public static final String DB_PARAM_ROSS_INTFC_CONTR_PROC_CD = "rossIntfcContrProcCd";

    /** ROSS_INTFC_PROC_STS_CD */
    public static final String DB_PARAM_ROSS_INTFC_PROC_STS_CD = "rossIntfcProcStsCd";

    /** PRNT_BLLG_MSTR_ID */
    public static final String DB_PARAM_PRNT_BLLG_MSTR_ID = "prntBllgMstrId";

    // =================================================
    // DB Columns
    // =================================================
    /** PRNT_BLLG_MSTR_ID */
    public static final String DB_COLUMN_PRNT_BLLG_MSTR_ID = "PRNT_BLLG_MSTR_ID";

    /** ROSS_INTFC_CONTR_PK */
    public static final String DB_COLUMN_ROSS_INTFC_CONTR_PK = "ROSS_INTFC_CONTR_PK";

    // =================================================
    // Mail Setting
    // =================================================
    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = BUSINESS_ID;

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = BUSINESS_ID + "M001";

    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    // =================================================
    // Const Value
    // =================================================
    /** fetchSize */
    public static final int FETCH_SIZE = 1000;

    /** API Call Max Count */
    public static final int API_CALL_MAX_COUNT = 2000;

    /** ROSS_INTFC_CONTR_PROC_ERROR */
    public static final String ROSS_INTFC_CONTR_PROC_ERROR = "E";

    /** ROSS_INTFC_CONTR_PROC_NORMAL */
    public static final String ROSS_INTFC_CONTR_PROC_NORMAL = "N";

    /**
     * ROSS_INTFC_PROC_STS_PROCESSED
     */
    public static final String ROSS_INTFC_PROC_STS_PROCESSED = "10";

    /**
     * ROSS_INTFC_PROC_STS_UNPROCESSED
     */
    public static final String ROSS_INTFC_PROC_STS_UNPROCESSED = "00";

}
