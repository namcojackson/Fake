/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB050001.constant;

/**
 *<pre>
 * NSAB0500: Populate CUSA Retail Contract Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/31/2016   CITS            M.Okigami         Create          N/A
 * 01/11/2017   CITS            T.Kikuhara        Update          QC#15484
 *</pre>
 */
public class NSAB050001Constant {

    /** business_id */
    public static final String BUSINESS_ID = "NSAB0500";

    /** program_id */
    public static final String PROGRAM_ID = "NSAB050001";

    /** fetchSize */
    public static final int FETCH_SIZE = 1000;

    // =================================================
    // Message Code
    // =================================================
    /** Global Company Code is mandatory. */
    public static final String NASM0010E = "NASM0010E";

    /** Sales date cannot be obtained. */
    public static final String NASM0011E = "NASM0011E";

    /** System Error : [@] was not found on a constant table. */
    public static final String NSZM0543E = "NSZM0543E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** Failed to delete "@". */
    public static final String NSAM0033E = "NSAM0033E";

    // =================================================
    // DB Param
    // =================================================
    /** GLBL_CMPY_CD */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** CONTR_END_DT */
    public static final String DB_PARAM_CONTR_END_DT = "contrEndDt";

    /** ROSS_INTFC_CONTR_PK */
    public static final String DB_PARAM_ROSS_INTFC_CONTR_PK = "rossIntfcContrPk";

    /** Last Proc Date */
    public static final String DB_PARAM_LAST_PROC_DATE = "lastProcDate";

    /** MACH_MDL_TP_CD */
    public static final String DB_PARAM_MACH_MDL_TP_CD = "machMdlTpCd";

    /** BLLG_BIZ_TP_CD */
    public static final String DB_PARAM_BLLG_BIZ_TP_CD = "bllgBizTpCd";

    /** Sales Date */
    public static final String DB_PARAM_SALES_DATE = "slsDt";

    /** RTL_DIV_CD */
    public static final String DB_PARAM_RTL_DIV_CD = "rtlDivCd";

    /** MSTR_BIZ_TP_CD */
    public static final String DB_PARAM_MSTR_BIZ_TP_CD = "mstrBizTpCd";

    /** PRNT_SER_NUM */
    public static final String DB_PARAM_PRNT_SER_NUM = "prntSerNum";

    /** MACH_MSTR_STS_CD */
    public static final String DB_PARAM_MACH_MSTR_STS_CD = "machMstrStsCd";

    /** BLLG_MSTR_PK */
    public static final String DB_PARAM_BLLG_MSTR_PK = "bllgMstrPk";

    /** RTL_DLR_CD */
    public static final String DB_PARAM_RTL_DLR_CD = "rtlDlrCd";

    /** GLBL_CMPY_CD(ABR) */
    public static final String DB_PARAM_GLBL_CMPY_CD_ABR = "glblCmpyCdAbr";

    /** GLBL_CMPY_CD 01 */
    public static final String DB_PARAM_GLBL_CMPY_CD_01 = "glblCmpyCd01";

    /** PROC_PGM_ID 01 */
    public static final String DB_PARAM_PROC_PGM_ID_01 = "procPgmId01";

    /** Last Proc Timestamp */
    public static final String DB_PARAM_LAST_PROC_TS = "lastProcTs";

    /** Current Timestamp */
    public static final String DB_PARAM_CUR_PROC_TS = "curProcTs";

    // =================================================
    // DB Value
    // =================================================
    /** MACH_MDL_TP_CD */
    public static final String MACH_MDL_TP_CD_MACHINE = "1";

    /** MACH_MDL_TP_CD */
    public static final String MACH_MDL_TP_CD_ACCESSORY = "2";

    /** BLLG_BIZ_TP_CD */
    public static final String BLLG_BIZ_TP_CD_SM = "SM";

    /** BLLG_BIZ_TP_CD */
    public static final String BLLG_BIZ_TP_CD_CM = "CM";

    /** BLLG_BIZ_TP_CD */
    public static final String BLLG_BIZ_TP_CD_P = "P";

    /** BLLG_BIZ_TP_CD */
    public static final String BLLG_BIZ_TP_CD_TP = "TP";

    /** BLLG_BIZ_TP_CD */
    public static final String BLLG_BIZ_TP_CD_LT = "LT";

    /** BLLG_BIZ_TP_CD */
    public static final String BLLG_BIZ_TP_CD_DP = "DP";

    /** MACH_MSTR_STS_CD */
    public static final String MACH_MSTR_STS_CD_65 = "65";

    /** MACH_MSTR_STS_CD */
    public static final String MACH_MSTR_STS_CD_70 = "70";

    /** MACH_MSTR_STS_CD */
    public static final String MACH_MSTR_STS_CD_8X = "8";

    /** Process Text */
    public static final String ROSS_INTFC_CONTR_PROC_TXT_INVALID = "Process Mode is invalid.";

    /** ROSS_INTFC_CONTR_PROC_ERROR */
    public static final String ROSS_INTFC_CONTR_PROC_ERROR = "E";

    /** ROSS_INTFC_CONTR_PROC_NORMAL */
    public static final String ROSS_INTFC_CONTR_PROC_NORMAL = "N";

    /**
     * ROSS_INTFC_PROC_MODE_ERROR
     */
    public static final String ROSS_INTFC_PROC_MODE_ERROR = "E";

    /**
     * ROSS_INTFC_PROC_MODE_CREATE
     */
    public static final String ROSS_INTFC_PROC_MODE_CREATE = "C";

    /**
     * ROSS_INTFC_PROC_MODE_UPDATE
     */
    public static final String ROSS_INTFC_PROC_MODE_UPDATE = "U";

    /**
     * ROSS_INTFC_PROC_MODE_SKIP
     */
    public static final String ROSS_INTFC_PROC_MODE_SKIP = "S";

    /**
     * ROSS_INTFC_PROC_MODE_TERMINATE
     */
    public static final String ROSS_INTFC_PROC_MODE_TERMINATE = "T";

    /**
     * ROSS_INTFC_PROC_MODE_TERMINATE_CREATE
     */
    public static final String ROSS_INTFC_PROC_MODE_TERMINATE_CREATE = "TC";

    /**
     * ROSS_INTFC_PROC_STS_PROCESSED
     */
    public static final String ROSS_INTFC_PROC_STS_PROCESSED = "10";

    /**
     * ROSS_INTFC_PROC_STS_UNPROCESSED
     */
    public static final String ROSS_INTFC_PROC_STS_UNPROCESSED = "00";


    // =================================================
    // DB Columns
    // =================================================
    /** PRNT_SER_NUM */
    public static final String DB_COLUMN_PRNT_SER_NUM = "PRNT_SER_NUM";

    /** PRNT_BLLG_MSTR_PK */
    public static final String DB_COLUMN_PRNT_BLLG_MSTR_PK = "PRNT_BLLG_MSTR_PK";

    /** MACH_MSTR_STS_CD */
    public static final String DB_COLUMN_MACH_MSTR_STS_CD = "MACH_MSTR_STS_CD";

    /** TRMN_DT */
    public static final String DB_COLUMN_TRMN_DT = "TRMN_DT";

    /** ROSS_INTFC_PROC_MODE_CD */
    public static final String DB_COLUMN_ROSS_INTFC_PROC_MODE_CD = "ROSS_INTFC_PROC_MODE_CD";

    /** MLY_BASE_COMP_AMT */
    public static final String DB_COLUMN_MLY_BASE_COMP_AMT = "MLY_BASE_COMP_AMT";

    /** CONTR_START_DT */
    public static final String DB_COLUMN_CONTR_START_DT = "CONTR_START_DT";

    /** BLLG_CYCLE_CD */
    public static final String DB_COLUMN_BLLG_CYCLE_CD = "BLLG_CYCLE_CD";

    /** BLLG_TMG_TP_CD */
    public static final String DB_COLUMN_BLLG_TMG_TP_CD = "BLLG_TMG_TP_CD";

    /** SER_NUM */
    public static final String DB_COLUMN_SER_NUM = "SER_NUM";

    /** SVC_DLR_CD */
    public static final String DB_COLUMN_SVC_DLR_CD = "SVC_DLR_CD";

    /** FNDG_DLR_CD */
    public static final String DB_COLUMN_FNDG_DLR_CD = "FNDG_DLR_CD";

    /** ORIG_DLR_CD */
    public static final String DB_COLUMN_ORIG_DLR_CD = "ORIG_DLR_CD";

    /** ADMIN_DLR_CD */
    public static final String DB_COLUMN_ADMIN_DLR_CD = "ADMIN_DLR_CD";

    /** ISTL_COMP_AMT */
    public static final String DB_COLUMN_ISTL_COMP_AMT = "ISTL_COMP_AMT";

    /** SVC_DLR_COMP_AMT */
    public static final String DB_COLUMN_SVC_DLR_COMP_AMT = "SVC_DLR_COMP_AMT";

    /** EXT_WTY_COMP_AMT */
    public static final String DB_COLUMN_EXT_WTY_COMP_AMT = "EXT_WTY_COMP_AMT";

    /** FNDG_DLR_COMP_AMT */
    public static final String DB_COLUMN_FNDG_DLR_COMP_AMT = "FNDG_DLR_COMP_AMT";

    /** ORIG_DLR_COMP_AMT */
    public static final String DB_COLUMN_ORIG_DLR_COMP_AMT = "ORIG_DLR_COMP_AMT";

    /** MLY_ADMIN_COMP_AMT */
    public static final String DB_COLUMN_MLY_ADMIN_COMP_AMT = "MLY_ADMIN_COMP_AMT";

    /** RNW_COMP_AMT */
    public static final String DB_COLUMN_RNW_COMP_AMT = "RNW_COMP_AMT";

    /** RTL_DIV_CD */
    public static final String DB_COLUMN_RTL_DIV_CD = "RTL_DIV_CD";

    /** SPLY_INCL_FLG */
    public static final String DB_COLUMN_SPLY_INCL_FLG = "SPLY_INCL_FLG";

    /** MRCF_TP_CD */
    public static final String DB_COLUMN_MRCF_TP_CD = "MRCF_TP_CD";

    /** BLLG_MSTR_PK */
    public static final String DB_COLUMN_BLLG_MSTR_PK = "BLLG_MSTR_PK";

    /** LAST_BLLG_DT */
    public static final String DB_COLUMN_LAST_BLLG_DT = "LAST_BLLG_DT";

    /** MSTR_BIZ_TP_CD */
    public static final String DB_COLUMN_MSTR_BIZ_TP_CD = "MSTR_BIZ_TP_CD";

    /** MDSE_CD */
    public static final String DB_COLUMN_MDSE_CD = "MDSE_CD";

    /** PRNT_MDSE_CD */
    public static final String DB_COLUMN_PRNT_MDSE_CD = "PRNT_MDSE_CD";

    /** BILL_TO_CUST_CD */
    public static final String DB_COLUMN_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** SELL_TO_CUST_CD */
    public static final String DB_COLUMN_SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** INSTL_CD */
    public static final String DB_COLUMN_INSTL_CD = "INSTL_CD";

    /** ISTL_SUB_LOC_CD */
    public static final String DB_COLUMN_ISTL_SUB_LOC_CD = "ISTL_SUB_LOC_CD";

    /** BLLG_BIZ_TP_CD */
    public static final String DB_COLUMN_BLLG_BIZ_TP_CD = "BLLG_BIZ_TP_CD";

    /** CONTR_END_DT */
    public static final String DB_COLUMN_CONTR_END_DT = "CONTR_END_DT";

    /** RTRN_DT */
    public static final String DB_COLUMN_RTRN_DT = "RTRN_DT";

    /** BLLG_DAY */
    public static final String DB_COLUMN_BLLG_DAY = "BLLG_DAY";

    /** PMT_TERM_AOT */
    public static final String DB_COLUMN_PMT_TERM_AOT = "PMT_TERM_AOT";

    /** MACH_MDL_TP_CD */
    public static final String DB_COLUMN_MACH_MDL_TP_CD = "MACH_MDL_TP_CD";

    /** BLLG_MSTR_CRAT_DT */
    public static final String DB_COLUMN_BLLG_MSTR_CRAT_DT = "BLLG_MSTR_CRAT_DT";

    /** BLLG_MSTR_MOD_DT */
    public static final String DB_COLUMN_BLLG_MSTR_MOD_DT = "BLLG_MSTR_MOD_DT";

    /** SVC_BLLG_MSTR_CRAT_DT */
    public static final String DB_COLUMN_SVC_BLLG_MSTR_CRAT_DT = "SVC_BLLG_MSTR_CRAT_DT";

    /** SVC_BLLG_MSTR_MOD_DT */
    public static final String DB_COLUMN_SVC_BLLG_MSTR_MOD_DT = "SVC_BLLG_MSTR_MOD_DT";

    /** SELL_TO_CUST_CRAT_DT */
    public static final String DB_COLUMN_SELL_TO_CUST_CRAT_DT = "SELL_TO_CUST_CRAT_DT";

    /** SELL_TO_CUST_MOD_DT */
    public static final String DB_COLUMN_SELL_TO_CUST_MOD_DT = "SELL_TO_CUST_MOD_DT";

    /** RTL_MDL_CRAT_DT */
    public static final String DB_COLUMN_RTL_MDL_CRAT_DT = "RTL_MDL_CRAT_DT";

    /** RTL_MDL_MOD_DT */
    public static final String DB_COLUMN_RTL_MDL_MOD_DT = "RTL_MDL_MOD_DT";

    /** BLLG_COND_CRAT_DT */
    public static final String DB_COLUMN_BLLG_COND_CRAT_DT = "BLLG_COND_CRAT_DT";

    /** BLLG_COND_MOD_DT */
    public static final String DB_COLUMN_BLLG_COND_MOD_DT = "BLLG_COND_MOD_DT";

    /** PMT_TERM_CASH_DISC_CRAT_DT */
    public static final String DB_COLUMN_PMT_TERM_CASH_DISC_CRAT_DT = "PMT_TERM_CASH_DISC_CRAT_DT";

    /** PMT_TERM_CASH_DISC_MOD_DT */
    public static final String DB_COLUMN_PMT_TERM_CASH_DISC_MOD_DT = "PMT_TERM_CASH_DISC_MOD_DT";

    /** RTL_CUST_PO_PK */
    public static final String DB_COLUMN_RTL_CUST_PO_PK = "RTL_CUST_PO_PK";

    /** RTL_CUST_PO_CRAT_DT */
    public static final String DB_COLUMN_RTL_CUST_PO_CRAT_DT = "RTL_CUST_PO_CRAT_DT";

    /** RTL_CUST_PO_MOD_DT */
    public static final String DB_COLUMN_RTL_CUST_PO_MOD_DT = "RTL_CUST_PO_MOD_DT";

    /** MLY_COPY_INCL_COMP_QTY */
    public static final String DB_COLUMN_MLY_COPY_INCL_COMP_QTY = "MLY_COPY_INCL_COMP_QTY";

    /** MTR_CNT */
    public static final String DB_COLUMN_MTR_CNT = "MTR_CNT";

    /** BLLG_MSTR_BLLG_MTR_PK */
    public static final String DB_COLUMN_BLLG_MSTR_BLLG_MTR_PK = "BLLG_MSTR_BLLG_MTR_PK";

    /** XS_MTR_COMP_COPY_QTY */
    public static final String DB_COLUMN_XS_MTR_COMP_COPY_QTY = "XS_MTR_COMP_COPY_QTY";

    /** XS_MTR_COMP_AMT_RATE */
    public static final String DB_COLUMN_XS_MTR_COMP_AMT_RATE = "XS_MTR_COMP_AMT_RATE";

    /** BLLG_MSTR_BLLG_MTR_CRAT_DT */
    public static final String DB_COLUMN_BLLG_MSTR_BLLG_MTR_CRAT_DT = "BLLG_MSTR_BLLG_MTR_CRAT_DT";

    /** BLLG_MSTR_BLLG_MTR_MOD_DT */
    public static final String DB_COLUMN_BLLG_MSTR_BLLG_MTR_MOD_DT = "BLLG_MSTR_BLLG_MTR_MOD_DT";

    /** PMT_TERM_CRAT_DT */
    public static final String DB_COLUMN_PMT_TERM_CRAT_DT = "PMT_TERM_CRAT_DT";

    /** PMT_TERM_MOD_DT */
    public static final String DB_COLUMN_PMT_TERM_MOD_DT = "PMT_TERM_MOD_DT";

    /** BLLG_XS_COPY_PRC_CRAT_DT */
    public static final String DB_COLUMN_BLLG_XS_COPY_PRC_CRAT_DT = "BLLG_XS_COPY_PRC_CRAT_DT";

    /** BLLG_XS_COPY_PRC_MOD_DT */
    public static final String DB_COLUMN_BLLG_XS_COPY_PRC_MOD_DT = "BLLG_XS_COPY_PRC_MOD_DT";

    // =================================================
    // NUM_CONST_NAME
    // =================================================
    /** ROSS_INTFC_CONTR_DEL_VAL */
    public static final String ROSS_INTFC_CONTR_DEL_VAL = "ROSS_INTFC_CONTR_DEL_VAL";

    // =================================================
    // VAR_CHAR_CONST_NAME
    // =================================================
    /** CUSA_GLBL_CMPY_CD */
    public static final String CUSA_GLBL_CMPY_CD = "CUSA_GLBL_CMPY_CD";

    // =================================================
    // API Param
    // =================================================
    /**
     * API Param: Timestamp Format
     */
    public static final String API_PARAM_TIMESTAMP_FORMAT = "yyyyMMddHHmmssSSS";

}
