/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB140001.constant;


/**
 *<pre>
 * NPAB140001:Insourcing Report Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/17/2016   CITS            Y.Fujii         Create          E477
 *</pre>
 */
public class NPAB140001Constant {

    // =================================================
    // Const
    // =================================================
    /** BUSHINESS_ID. */
    public static final String BUSINESS_ID = "NPAB140001";

    /** ONE_BLANK . */
    public static final String ONE_BLANK = " ";

    /** COMMA. */
    public static final String COMMA = ",";

    /** DB_CONST_SPLIT. */
    public static final String DB_CONST_SPLIT = ",";

    /** PERIOD. */
    public static final String PERIOD = ".";

    /** HYPHEN. */
    public static final String HYPHEN = "-";

    /** DEF_TS_ZERO . */
    public static final String DEF_TS_ZERO = "00000000000000";

    /** Date Format */
    public static final String DATE_TIME_PRINT_FORMAT = "MM/dd/yyyy HH:mm";

    /** Date Format */
    public static final String DATE_TIME_FORMAT_14 = "yyyyMMddHHmmss";

    /** Date Format */
    public static final String DATE_TIME_FORMAT_17 = "yyyyMMddHHmmssSSS";

    /** INSOURCING_TO_CSA_WH */
    public static final String INSOURCING_TO_CSA_WH = "1";

    /** INSOURCING_TO_3PL_WH_DB */
    public static final String INSOURCING_TO_3PL_WH_DB = "2";

    /** INSOURCING_TO_3PL_WH_OTHER */
    public static final String INSOURCING_TO_3PL_WH_OTHER = "3";

    /** INSOURCING_PR */
    public static final String INSOURCING_PR = "4";

    // =================================================
    // EIP Parameter
    // =================================================
    /** RPT_ID_NPAF0040. */
    public static final String RPT_ID_NPAF0040 = "NPAF0040";

    /** REPORT_TITLE */
    public static final String REPORT_TITLE = "Insourcing Report";

    /** INTL_LANG_VAL_COL_NM */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** INTL_LANG_VAL_COL_NM */
    public static final String INSRC_RPT_PRINT_RQST_SQ = "INSRC_RPT_PRINT_RQST_SQ";

    /** INTL_LANG_VAL_COL_NM */
    public static final String INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    /** HAIFUN */
    public static final String HAIFUN = "-";

    /** SPACE */
    public static final String SPACE = " ";

    // =================================================
    // Message Const
    // =================================================
    /** [@] has no value. */
    public static final String NPAM0078E = "NPAM0078E";

    /** Failed to get @. */
    public static final String NPAM1328E = "NPAM1328E";

    /** Failed to update @ table. @ is @. */
    public static final String NPAM1003E = "NPAM1003E";

    /** It failed to insert the [@]. PK [@]. */
    public static final String NPAM1499E = "NPAM1499E";

    /** Failed to remove. [@] */
    public static final String NPAM1342E = "NPAM1342E";

    /** EIP Print Error Message. */
    public static final String PRINT_ERROR_MSG = "It failed to generate a report instance.";

    // =================================================
    // DB_PARAM Const
    // =================================================
    /** DB_PARAM_GLBL_CMPY_CD : glblCmpyCd */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** DB_PARAM_RPT_ID : rptId */
    public static final String DB_PARAM_RPT_ID = "rptId";

    /** DB_PARAM_PROC_PGM_ID : procPgmId */
    public static final String DB_PARAM_PROC_PGM_ID = "procPgmId";

    /** DB_PARAM_PROC_DT : procDt */
    public static final String DB_PARAM_PROC_DT = "procDt";

    /** DB_PARAM_RTL_WH_CD : rtlWhCd */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /** DB_PARAM_PRCH_REQ_REC_TP_TECH_REQUEST : prchReqRecTpTechRequest */
    public static final String DB_PARAM_PRCH_REQ_REC_TP_TECH_REQUEST = "prchReqRecTpTechRequest";

    /** DB_PARAM_PRCH_REQ_REC_TP_PO_REQUISITION : prchReqRecTpPoRequisition */
    public static final String DB_PARAM_PRCH_REQ_REC_TP_PO_REQUISITION = "prchReqRecTpPoRequisition";

    /** DB_PARAM_PRCH_REQ_SRC_TP_INSOURCING : prchReqSrcTpInsourcing */
    public static final String DB_PARAM_PRCH_REQ_SRC_TP_INSOURCING = "prchReqSrcTpInsourcing";

    /** DB_PARAM_PRCH_REQ_LINE_TP_CD_LIST : prchReqLineTpCdList */
    public static final String DB_PARAM_PRCH_REQ_LINE_TP_CD_LIST = "prchReqLineTpCdList";

    /** DB_PARAM_PRNT_VND_CD_LIST : prntVndCdList */
    public static final String DB_PARAM_PRNT_VND_CD_LIST = "prntVndCdList";

    /** DB_PARAM_PRNT_VND_CD_LIST : prntVndCdList */
    public static final String DB_PARAM_BIZ_LAST_UPD_TS = "bizLastUpdTs";

    /** DB_PARAM_INSRC_RPT_WRK_PK : insrcRptWrkPk */
    public static final String DB_PARAM_INSRC_RPT_WRK_PK = "insrcRptWrkPk";

    /** DB_PARAM_PRNT_PURGE_TS : purgeTs */
    public static final String DB_PARAM_PRNT_PURGE_TS = "purgeTs";

    // =================================================
    // DB_COLUMN Const
    // =================================================
    /** DB Column Name: RPT_ID. */
    public static final String DB_COLUMN_RPT_ID = "RPT_ID";

    /** DB Column Name: PROC_PGM_ID. */
    public static final String DB_COLUMN_PROC_PGM_ID = "PROC_PGM_ID";

    /** DB Column Name: PROC_DT. */
    public static final String DB_COLUMN_PROC_DT = "PROC_DT";

    /** DB Column Name: RTL_WH_CD. */
    public static final String DB_COLUMN_RTL_WH_CD = "RTL_WH_CD";

    /** DB Column Name: PRCH_REQ_CRAT_DT_TM. */
    public static final String DB_COLUMN_PRCH_REQ_CRAT_DT_TM = "PRCH_REQ_CRAT_DT_TM";

    /** DB Column Name: PRD1_PRCH_REQ_INSRC_QTY. */
    public static final String DB_COLUMN_PRD1_PRCH_REQ_INSRC_QTY = "PRD1_PRCH_REQ_INSRC_QTY";

    /** DB Column Name: PRD1_PRCH_REQ_QTY. */
    public static final String DB_COLUMN_PRD1_PRCH_REQ_QTY = "PRD1_PRCH_REQ_QTY";

    /** DB Column Name: PRD1_SRC_RTL_SWH_CD. */
    public static final String DB_COLUMN_PRD1_SRC_RTL_SWH_CD = "PRD1_SRC_RTL_SWH_CD";

    /** DB Column Name: PRD1_SRC_RTL_WH_CD. */
    public static final String DB_COLUMN_PRD1_SRC_RTL_WH_CD = "PRD1_SRC_RTL_WH_CD";

    /** DB Column Name: PRD2_PRCH_REQ_NUM. */
    public static final String DB_COLUMN_PRD2_PRCH_REQ_NUM = "PRD2_PRCH_REQ_NUM";

    /** DB Column Name: PRD2_DEST_INVTY_LOC_CD. */
    public static final String DB_COLUMN_PRD2_DEST_INVTY_LOC_CD = "PRD2_DEST_INVTY_LOC_CD";

    /** DB Column Name: PRD2_MDSE_CD. */
    public static final String DB_COLUMN_PRD2_MDSE_CD = "PRD2_MDSE_CD";

    /** DB Column Name: PRD2_ORIG_RQST_MDSE_CD. */
    public static final String DB_COLUMN_PRD2_ORIG_RQST_MDSE_CD = "PRD2_ORIG_RQST_MDSE_CD";

    /** DB Column Name: PRD2_PRCH_REQ_CRAT_DT. */
    public static final String DB_COLUMN_PRD2_PRCH_REQ_CRAT_DT = "PRD2_PRCH_REQ_CRAT_DT";

    /** DB Column Name: PRD2_PRCH_REQ_CRAT_TM. */
    public static final String DB_COLUMN_PRD2_PRCH_REQ_CRAT_TM = "PRD2_PRCH_REQ_CRAT_TM";

    /** DB Column Name: PRD2_PRCH_REQ_LINE_NUM. */
    public static final String DB_COLUMN_PRD2_PRCH_REQ_LINE_NUM = "PRD2_PRCH_REQ_LINE_NUM";

    /** DB Column Name: PRD2_PRCH_REQ_LINE_SUB_NUM. */
    public static final String DB_COLUMN_PRD2_PRCH_REQ_LINE_SUB_NUM = "PRD2_PRCH_REQ_LINE_SUB_NUM";

    /** DB Column Name: PRD2_PRCH_REQ_LINE_TP_CD. */
    public static final String DB_COLUMN_PRD2_PRCH_REQ_LINE_TP_CD = "PRD2_PRCH_REQ_LINE_TP_CD";

    /** DB Column Name: PRD2_PRCH_REQ_QTY. */
    public static final String DB_COLUMN_PRD2_PRCH_REQ_QTY = "PRD2_PRCH_REQ_QTY";

    /** DB Column Name: PRD2_PRNT_VND_CD. */
    public static final String DB_COLUMN_PRD2_PRNT_VND_CD = "PRD2_PRNT_VND_CD";

    /** DB Column Name: PRD2_PROCR_TP_CD. */
    public static final String DB_COLUMN_PRD2_PROCR_TP_CD = "PRD2_PROCR_TP_CD";

    /** DB Column Name: PRD2_SRC_RTL_SWH_CD. */
    public static final String DB_COLUMN_PRD2_SRC_RTL_SWH_CD = "PRD2_SRC_RTL_SWH_CD";

    /** DB Column Name: PRD2_SRC_RTL_WH_CD. */
    public static final String DB_COLUMN_PRD2_SRC_RTL_WH_CD = "PRD2_SRC_RTL_WH_CD";

    /** DB Column Name: PRD2_VND_CD. */
    public static final String DB_COLUMN_PRD2_VND_CD = "PRD2_VND_CD";

    /** DB Column Name: PRD3_PO_ORD_NUM. */
    public static final String DB_COLUMN_PRD3_PO_ORD_NUM = "PRD3_PO_ORD_NUM";

    /** DB Column Name: PRD3_PRCH_REQ_NUM. */
    public static final String DB_COLUMN_PRD3_PRCH_REQ_NUM = "PRD3_PRCH_REQ_NUM";

    /** DB Column Name: PRH2_PRCH_REQ_NUM. */
    public static final String DB_COLUMN_PRH2_PRCH_REQ_NUM = "PRH2_PRCH_REQ_NUM";

    /** DB Column Name: PRLT2_PRCH_REQ_LINE_TP_NM. */
    public static final String DB_COLUMN_PRLT2_PRCH_REQ_LINE_TP_NM = "PRLT2_PRCH_REQ_LINE_TP_NM";

    /** DB Column Name: PV1_PRNT_VND_NM. */
    public static final String DB_COLUMN_PV1_PRNT_VND_NM = "PV1_PRNT_VND_NM";

    /** DB Column Name: RWH1_RTL_WH_NM. */
    public static final String DB_COLUMN_RWH1_RTL_WH_NM = "RWH1_RTL_WH_NM";

    /** DB Column Name: RWH2_RTL_WH_NM. */
    public static final String DB_COLUMN_RWH2_RTL_WH_NM = "RWH2_RTL_WH_NM";

    /** DB Column Name: RWH3_RTL_WH_NM. */
    public static final String DB_COLUMN_RWH3_RTL_WH_NM = "RWH3_RTL_WH_NM";

    /** DB Column Name: VD1_LOC_NM. */
    public static final String DB_COLUMN_VD1_LOC_NM = "VD1_LOC_NM";

    /** DB Column Name: DS_BIZ_PROC_LOG_PK. */
    public static final String DB_COLUMN_DS_BIZ_PROC_LOG_PK = "DS_BIZ_PROC_LOG_PK";

    /** DB Column Name: DS_BIZ_LAST_UPD_TS. */
    public static final String DB_COLUMN_DS_BIZ_LAST_UPD_TS = "DS_BIZ_LAST_UPD_TS";

    /** DB Column Name: RTL_WH_NM. */
    public static final String DB_COLUMN_RTL_WH_NM = "RTL_WH_NM";

    /** DB Column Name: RTL_WH_DESC_TXT. */
    public static final String DB_COLUMN_RTL_WH_DESC_TXT = "RTL_WH_DESC_TXT";

    /** DB Column Name: DS_ACCT_NM. */
    public static final String DB_COLUMN_DS_ACCT_NM = "DS_ACCT_NM";

    /** DB Column Name: FIRST_LINE_ADDR. */
    public static final String DB_COLUMN_FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /** DB Column Name: SCD_LINE_ADDR. */
    public static final String DB_COLUMN_SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /** DB Column Name: THIRD_LINE_ADDR. */
    public static final String DB_COLUMN_THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /** DB Column Name: FRTH_LINE_ADDR. */
    public static final String DB_COLUMN_FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /** DB Column Name: CTY_ADDR. */
    public static final String DB_COLUMN_CTY_ADDR = "CTY_ADDR";

    /** DB Column Name: ST_CD. */
    public static final String DB_COLUMN_ST_CD = "ST_CD";

    /** DB Column Name: POST_CD. */
    public static final String DB_COLUMN_POST_CD = "POST_CD";

    /** DB Column Name: RPT_PRINT_CTRL_ID. */
    public static final String DB_COLUMN_RPT_PRINT_CTRL_ID = "RPT_PRINT_CTRL_ID";

    /** DB Column Name: RPT_BR_ID. */
    public static final String DB_COLUMN_RPT_BR_ID = "RPT_BR_ID";

    /** DB Column Name: RPT_PRINT_CTRL_VAL_TXT_01. */
    public static final String DB_COLUMN_RPT_PRINT_CTRL_VAL_TXT_01 = "RPT_PRINT_CTRL_VAL_TXT_01";

    /** DB Column Name: RPT_PRINT_CTRL_NM. */
    public static final String DB_COLUMN_RPT_PRINT_CTRL_NM = "RPT_PRINT_CTRL_NM";

    /** DB Column Name: PRD2_PRCH_REQ_INSRC_QTY. */
    public static final String DB_COLUMN_PRD2_PRCH_REQ_INSRC_QTY = "PRD2_PRCH_REQ_INSRC_QTY";

    /** DB Column Name: PRD2_PRCH_REQ_BAL_QTY. */
    public static final String DB_COLUMN_PRD2_PRCH_REQ_BAL_QTY = "PRD2_PRCH_REQ_BAL_QTY";

    /** DB Column Name: PRD2_PRCH_REQ_REL_ERR_MSG_TXT. */
    public static final String DB_COLUMN_PRD2_PRCH_REQ_REL_ERR_MSG_TXT = "PRD2_PRCH_REQ_REL_ERR_MSG_TXT";

    /** DB Column Name: INSRC_RPT_WRK_PK. */
    public static final String DB_COLUMN_INSRC_RPT_WRK_PK = "INSRC_RPT_WRK_PK";

    // =================================================
    // Var Char / Number Const Name
    // =================================================
    /** Number Const Name : NPAF0040_PURGE_DT */
    public static final String CONST_NPAF0040_PURGE_DT = "NPAF0040_PURGE_DT";

    /** Var Char Const Name : NPAF0040_INSRC_TO_CSA_WH */
    public static final String CONST_NPAF0040_INSRC_TO_CSA_WH = "NPAF0040_INSRC_TO_CSA_WH";

    /** Var Char Const Name : NPAF0040_INSRC_TO_3PL_WH_DB */
    public static final String CONST_NPAF0040_INSRC_TO_3PL_WH_DB = "NPAF0040_INSRC_TO_3PL_WH_DB";

    /** Var Char Const Name : NPAF0040_INSRC_TO_3PL_WH_OT */
    public static final String CONST_NPAF0040_INSRC_TO_3PL_WH_OT = "NPAF0040_INSRC_TO_3PL_WH_OT";

    /** Var Char Const Name : NPAF0040_INSRC_PR */
    public static final String CONST_NPAF0040_INSRC_PR = "NPAF0040_INSRC_PR";

    /** Var Char Const Name : NPAF0040_INSRC_PR_EX_SPLY */
    public static final String CONST_NPAF0040_INSRC_PR_EX_SPLY = "NPAF0040_INSRC_PR_EX_SPLY";

    /** Var Char Const Name : NPAF0040_SMRY_INSRC_TO_CSA_WH */
    public static final String CONST_NPAF0040_SMRY_INSRC_TO_CSA_WH = "NPAF0040_SMRY_INSRC_TO_CSA_WH";

    /** Var Char Const Name : NPAF0040_SMRY_INSRC_TO_CLB_WH */
    public static final String CONST_NPAF0040_SMRY_INSRC_TO_CLB_WH = "NPAF0040_SMRY_INSRC_TO_CLB_WH";

    /** Var Char Const Name : NPAF0040_SMRY_INSRC_TO_CHO_WH */
    public static final String CONST_NPAF0040_SMRY_INSRC_TO_CHO_WH = "NPAF0040_SMRY_INSRC_TO_CHO_WH";

    /** Var Char Const Name : NPAF0040_SMRY_INSRC_PR */
    public static final String CONST_NPAF0040_SMRY_INSRC_PR = "NPAF0040_SMRY_INSRC_PR";

    /** Var Char Const Name : NPAF0040_SMRY_INSRC_PR_SPLY */
    public static final String CONST_NPAF0040_SMRY_INSRC_PR_SPLY = "NPAF0040_SMRY_INSRC_PR_SPLY";

    /** Var Char Const Name : NPAF0040_DIRECT_PRINT */
    public static final String CONST_NPAF0040_DIRECT_PRINT = "NPAF0040_DIRECT_PRINT";
}
