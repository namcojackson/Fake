/**
 * <pre>
 * Copyright (c) 2015 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB022001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Create Contract From Interface
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/03/15   Hitachi         Y.Tsuchimoto    Update          QC#869,QC#87
 * 2016/04/08   Hitachi         Y.Tsuchimoto    Update          QC#6764
 * 2016/08/01   Hitachi         T.Iwamoto       Update          QC#12625
 * 2017/10/10   Hitachi         A.Kohinata      Update          QC#21617
 * 2018/06/11   CITS            M.Naito         Update          QC#22971
 * 2018/06/12   Hitachi         T.Tomita        Update          QC#26441
 * 2019/01/18   Fujitsu         R.Nakamura      Update          QC#29776
 * 2023/02/16   Hitachi         K.Watanabe      Update          QC#60913
 * </pre>
 */
public class NSAB022001Constant {

    // Add Start 2018/06/12 QC#26441
    /** PRG_ID:NSAB022001 */
    public static final String PRG_ID = "NSAB022001";
    // Add End 2018/06/12 QC#26441
    /** Global Company Code is mandatory. */
    public static final String NSAM0177E = "NSAM0177E";

    /** Sales date cannot be obtained. */
    public static final String NSAM0178E = "NSAM0178E";

    /** [@] field is mandatory. */
    public static final String NMAM0836E = "NMAM0836E";

    /** [@] doesn't exist in the table [@]. */
    public static final String NFCM0721E = "NFCM0721E";

    /** The chronological sequence between the dates is wrong. */
    public static final String NSBM0024E = "NSBM0024E";

    /** Specified Sub Contract cannot be deleted. Please terminate the Sub Contract by Terminate Mode. */
    public static final String NSAM0389E = "NSAM0389E";

    /** [@] should be match each other in the same [@]. */
    public static final String NSAM0390E = "NSAM0390E";

    /** Cannot specify Service Pricing Detail information from Contract Interface information. */
    public static final String NSAM0601E = "NSAM0601E";

    // Add Start 2019/01/18 QC#29776
    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** Failed to update "DS_ADDL_CHRG_INTFC". */
    public static final String NSXM0097E = "NSXM0097E";

    /** Failed to update "DS_CONTR_INTFC". */
    public static final String NSXM0074E = "NSXM0074E";
    // Add End 2019/01/18 QC#29776

    /** Error Message Text: Global Company Code */
    public static final String MSG_TXT_GLBL_CMPY_CD = "Global Company Code";

    /** Error Message Text: Sales Date */
    public static final String MSG_TXT_SALES_DATE = "Sales Date";

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** DS_CONTR_MACH_LVL_NUM LEVEL1 */
    public static final String DS_CONTR_MACH_LVL_NUM_LEVEL1 = "1";

    /** DS_CONTR_MACH_LVL_NUM LEVEL2 */
    public static final String DS_CONTR_MACH_LVL_NUM_LEVEL2 = "2";

    /** DS_CONTR_MACH_LVL_NUM LEVEL3 */
    public static final String DS_CONTR_MACH_LVL_NUM_LEVEL3 = "3";

    /** DS_CONTR_INTFC_ACT_CD CREATE */
    public static final String DS_CONTR_INTFC_ACT_CD_CREATE = "01";

    /** DS_CONTR_INTFC_ACT_CD UPDATE */
    public static final String DS_CONTR_INTFC_ACT_CD_UPDATE = "02";

    /** DS_CONTR_INTFC_ACT_CD ADD_TO_CONTR */
    public static final String DS_CONTR_INTFC_ACT_CD_ADD_TO_CONTR = "03";

    /** DS_CONTR_INTFC_ACT_CD DELETE */
    public static final String DS_CONTR_INTFC_ACT_CD_DELETE = "04";

    /** DS_CONTR_INTFC_ACT_CD TERMINATE */
    public static final String DS_CONTR_INTFC_ACT_CD_TERMINATE = "05";

    /** Xx CRUD Mode : Create */
    public static final String CRUD_MODE_CREATE = "C";

    /** Xx CRUD Mode : Update */
    public static final String CRUD_MODE_UPDATE = "U";

    /** Xx CRUD Mode : Delete */
    public static final String CRUD_MODE_DELETE = "D";

    /** Column name: DS_CONTR_VLD_RSLT_MSG_TXT */
    public static final String COL_DS_CONTR_VLD_RSLT_MSG_TXT = "DS_CONTR_VLD_RSLT_MSG_TXT";

    /** Column name: VND_CD */
    public static final String COL_VND_CD = "VND_CD";

    /** Column name: TECH_CD */
    public static final String COL_TECH_CD = "TECH_CD";

    /** Column name: EFF_FROM_DT */
    public static final String COL_EFF_FROM_DT = "EFF_FROM_DT";

    /** Column name: EFF_THRU_DT */
    public static final String COL_EFF_THRU_DT = "EFF_THRU_DT";

    /** Column name: CONTR_TRMN_FLG */
    public static final String COL_CONTR_TRMN_FLG = "CONTR_TRMN_FLG";

    /** Column name: ADMIN_PRC_DEAL_AMT */
    public static final String COL_ADMIN_PRC_DEAL_AMT = "ADMIN_PRC_DEAL_AMT";

    /** Column name: PREPD_MAINT_FLG */
    public static final String COL_PREPD_MAINT_FLG = "PREPD_MAINT_FLG";

    /** Column name: BW_MTR_ALWNC_CNT */
    public static final String COL_BW_MTR_ALWNC_CNT = "BW_MTR_ALWNC_CNT";

    /** Column name: COLOR_MTR_ALWNC_CNT */
    public static final String COL_COLOR_MTR_ALWNC_CNT = "COLOR_MTR_ALWNC_CNT";

    /** Column name: BW_MTR_PRC_AMT_RATE */
    public static final String COL_BW_MTR_PRC_AMT_RATE = "BW_MTR_PRC_AMT_RATE";

    /** Column name: COLOR_MTR_PRC_AMT_RATE */
    public static final String COL_COLOR_MTR_PRC_AMT_RATE = "COLOR_MTR_PRC_AMT_RATE";

    /** Column name: SPLY_INCL_FLG */
    public static final String COL_SPLY_INCL_FLG = "SPLY_INCL_FLG";

    /** Column name: DLR_FLEET_FLG */
    public static final String COL_DLR_FLEET_FLG = "DLR_FLEET_FLG";

    /** Column name: DLR_FLEET_NUM */
    public static final String COL_DLR_FLEET_NUM = "DLR_FLEET_NUM";

    /** Column name: CAP_CLR_ORIG_QTY */
    public static final String COL_CAP_CLR_ORIG_QTY = "CAP_CLR_ORIG_QTY";

    /** Column name: DLR_FLEET_NUM */
    public static final String COL_CAP_CLR_RUN_QTY = "CAP_CLR_RUN_QTY";

    /** DS_CONTR_INTFC */
    /** Column name: GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column name: DS_CONTR_INTFC_PK */
    public static final String COL_DS_CONTR_INTFC_PK = "DS_CONTR_INTFC_PK";

    /** Column name: DS_CONTR_INTFC_BAT_NUM */
    public static final String COL_DS_CONTR_INTFC_BAT_NUM = "DS_CONTR_INTFC_BAT_NUM";

    /** Column name: DS_CONTR_SRC_REF_NUM */
    public static final String COL_DS_CONTR_SRC_REF_NUM = "DS_CONTR_SRC_REF_NUM";

    /** Column name: CONTR_INTFC_SRC_TP_CD */
    public static final String COL_CONTR_INTFC_SRC_TP_CD = "CONTR_INTFC_SRC_TP_CD";

    /** Column name: DS_CONTR_INTFC_ACT_CD */
    public static final String COL_DS_CONTR_INTFC_ACT_CD = "DS_CONTR_INTFC_ACT_CD";

    /** Column name: DS_CONTR_PROC_STS_CD */
    public static final String COL_DS_CONTR_PROC_STS_CD = "DS_CONTR_PROC_STS_CD";

    /** Column name: DS_CONTR_INTFC_STS_CD */
    public static final String COL_DS_CONTR_INTFC_STS_CD = "DS_CONTR_INTFC_STS_CD";

    /** Column name: INTFC_ERR_MSG_TXT */
    public static final String COL_INTFC_ERR_MSG_TXT = "INTFC_ERR_MSG_TXT";

    /** Column name: DS_CONTR_NUM */
    public static final String COL_DS_CONTR_NUM = "DS_CONTR_NUM";

    /** Column name: SER_NUM */
    public static final String COL_SER_NUM = "SER_NUM";

    /** Column name: SVC_MACH_MSTR_PK */
    public static final String COL_SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** Column name: CONTR_INTFC_DTL_TP_CD */
    public static final String COL_CONTR_INTFC_DTL_TP_CD = "CONTR_INTFC_DTL_TP_CD";

    /** Column name: DS_ACCT_NUM */
    public static final String COL_DS_ACCT_NUM = "DS_ACCT_NUM";

    /** Column name: BILL_TO_CUST_CD */
    public static final String COL_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** Column name: BILL_TO_LOC_NUM */
    public static final String COL_BILL_TO_LOC_NUM = "BILL_TO_LOC_NUM";

    /** Column name: LEASE_CMPY_CD */
    public static final String COL_LEASE_CMPY_CD = "LEASE_CMPY_CD";

    /** Column name: SVC_CONTR_BR_CD */
    public static final String COL_SVC_CONTR_BR_CD = "SVC_CONTR_BR_CD";

    /** Column name: TOC_CD */
    public static final String COL_TOC_CD = "TOC_CD";

    /** Column name: CUST_PO_NUM */
    public static final String COL_CUST_PO_NUM = "CUST_PO_NUM";

    /** Column name: PO_DT */
    public static final String COL_PO_DT = "PO_DT";

    /** Column name: CR_CARD_CUST_REF_NUM */
    public static final String COL_CR_CARD_CUST_REF_NUM = "CR_CARD_CUST_REF_NUM";

    /** Column name: CR_CARD_EXPR_YR_MTH */
    public static final String COL_CR_CARD_EXPR_YR_MTH = "CR_CARD_EXPR_YR_MTH";

    /** Column name: MTR_EST_TP_CD */
    public static final String COL_MTR_EST_TP_CD = "MTR_EST_TP_CD";

    /** Column name: SVC_PGM_MDSE_CD */
    public static final String COL_SVC_PGM_MDSE_CD = "SVC_PGM_MDSE_CD";

    /** Column name: MDSE_CD */
    public static final String COL_MDSE_CD = "MDSE_CD";

    /** Column name: MDL_ID */
    public static final String COL_MDL_ID = "MDL_ID";

    /** Column name: MDL_NM */
    public static final String COL_MDL_NM = "MDL_NM";

    /** Column name: CONTR_FROM_DT */
    public static final String COL_CONTR_FROM_DT = "CONTR_FROM_DT";

    /** Column name: CONTR_THRU_DT */
    public static final String COL_CONTR_THRU_DT = "CONTR_THRU_DT";

    /** Column name: BLLG_CYCLE_CD */
    public static final String COL_BLLG_CYCLE_CD = "BLLG_CYCLE_CD";

    /** Column name: PRC_ALLOC_BY_MACH_QTY_FLG */
    public static final String COL_PRC_ALLOC_BY_MACH_QTY_FLG = "PRC_ALLOC_BY_MACH_QTY_FLG";

    /** Column name: CONTR_AUTO_RNW_TP_CD */
    public static final String COL_CONTR_AUTO_RNW_TP_CD = "CONTR_AUTO_RNW_TP_CD";

    /** Column name: RNW_PRC_METH_CD */
    public static final String COL_RNW_PRC_METH_CD = "RNW_PRC_METH_CD";

    /** Column name: BEF_END_RNW_DAYS_AOT */
    public static final String COL_BEF_END_RNW_DAYS_AOT = "BEF_END_RNW_DAYS_AOT";

    /** Column name: RNW_PRC_UP_RATIO */
    public static final String COL_RNW_PRC_UP_RATIO = "RNW_PRC_UP_RATIO";

    /** Column name: CONTR_UPLFT_TP_CD */
    public static final String COL_CONTR_UPLFT_TP_CD = "CONTR_UPLFT_TP_CD";

    /** Column name: UPLFT_PRC_METH_CD */
    public static final String COL_UPLFT_PRC_METH_CD = "UPLFT_PRC_METH_CD";

    /** Column name: UPLFT_PRC_UP_RATIO */
    public static final String COL_UPLFT_PRC_UP_RATIO = "UPLFT_PRC_UP_RATIO";

    /** Column name: MTR_READ_METH_CD */
    public static final String COL_MTR_READ_METH_CD = "MTR_READ_METH_CD";

    /** Column name: BASE_PRC_DEAL_AMT */
    public static final String COL_BASE_PRC_DEAL_AMT = "BASE_PRC_DEAL_AMT";

    /** Column name: CONTR_CLO_DAY */
    public static final String COL_CONTR_CLO_DAY = "CONTR_CLO_DAY";

    /** Column name: CONTR_BLLG_DAY */
    public static final String COL_CONTR_BLLG_DAY = "CONTR_BLLG_DAY";

    /** Column name: BLLG_THRU_DT */
    public static final String COL_BLLG_THRU_DT = "BLLG_THRU_DT";

    /** Column name: BLLG_MTR_LB_CD */
    public static final String COL_BLLG_MTR_LB_CD = "BLLG_MTR_LB_CD";

    /** Column name: BLLG_MTR_LB_NM */
    public static final String COL_BLLG_MTR_LB_NM = "BLLG_MTR_LB_NM";

    /** Column name: START_MTR_CNT */
    public static final String COL_START_MTR_CNT = "START_MTR_CNT";

    /** Column name: BLLG_ROLL_OVER_RATIO */
    public static final String COL_BLLG_ROLL_OVER_RATIO = "BLLG_ROLL_OVER_RATIO";

    /** Column name: DS_CONTR_CATG_CD */
    public static final String COL_DS_CONTR_CATG_CD = "DS_CONTR_CATG_CD";

    /** Column name: DS_CONTR_STS_CD */
    public static final String COL_DS_CONTR_STS_CD = "DS_CONTR_STS_CD";

    /** Column name: XS_CHRG_TP_CD */
    public static final String COL_XS_CHRG_TP_CD = "XS_CHRG_TP_CD";

    /** Column name: XS_MTR_COPY_QTY */
    public static final String COL_XS_MTR_COPY_QTY = "XS_MTR_COPY_QTY";

    /** Column name: XS_MTR_AMT_RATE */
    public static final String COL_XS_MTR_AMT_RATE = "XS_MTR_AMT_RATE";

    /** Column name: ADDL_CHRG_TP_CD */
    public static final String COL_ADDL_CHRG_TP_CD = "ADDL_CHRG_TP_CD";

    /** Column name: ADDL_CHRG_FLAT_DEAL_PRC_AMT */
    public static final String COL_ADDL_CHRG_FLAT_DEAL_PRC_AMT = "ADDL_CHRG_FLAT_DEAL_PRC_AMT";

    /** Column name: ADDL_CHRG_APLC_PCT */
    public static final String COL_ADDL_CHRG_APLC_PCT = "ADDL_CHRG_APLC_PCT";

    /** Column name: CHRG_LVL_TP_CD */
    public static final String COL_CHRG_LVL_TP_CD = "CHRG_LVL_TP_CD";

    /** Column name: ADDL_CHRG_INV_TP_CD */
    public static final String COL_ADDL_CHRG_INV_TP_CD = "ADDL_CHRG_INV_TP_CD";

    /** Column name: PRINT_DTL_FLG */
    public static final String COL_PRINT_DTL_FLG = "PRINT_DTL_FLG";

    /** Column name: BASE_PRC_TERM_DEAL_AMT_RATE */
    public static final String COL_BASE_PRC_TERM_DEAL_AMT_RATE = "BASE_PRC_TERM_DEAL_AMT_RATE";

    /** Column name: DS_CONTR_CLS_CD */
    public static final String COL_DS_CONTR_CLS_CD = "DS_CONTR_CLS_CD";

    /** Column name: CTAC_PSN_PK */
    public static final String COL_CTAC_PSN_PK = "CTAC_PSN_PK";

    /** Column name: CTAC_PSN_NM */
    public static final String COL_CTAC_PSN_NM = "CTAC_PSN_NM";

    /** Column name: INV_SEPT_BASE_USG_FLG */
    public static final String COL_INV_SEPT_BASE_USG_FLG = "INV_SEPT_BASE_USG_FLG";

    /** Column name: CONTR_CLO_DT */
    public static final String COL_CONTR_CLO_DT = "CONTR_CLO_DT";

    /** Column name: CONTR_DURN_AOT */
    public static final String COL_CONTR_DURN_AOT = "CONTR_DURN_AOT";

    /** Column name: PMT_TERM_CASH_DISC_CD */
    public static final String COL_PMT_TERM_CASH_DISC_CD = "PMT_TERM_CASH_DISC_CD";

    /** Column name: PMT_TERM_CASH_DISC_CD */
    public static final String COL_SVC_LINE_BIZ_CD = "PMT_TERM_CASH_DISC_CD";

    /** Column name: BLLG_TMG_TP_CD */
    public static final String COL_BLLG_TMG_TP_CD = "BLLG_TMG_TP_CD";

    /** Column name: DS_CONTR_EDI_CD */
    public static final String COL_DS_CONTR_EDI_CD = "DS_CONTR_EDI_CD";

    /** Column name: DS_CONTR_INTFC_DT */
    public static final String COL_DS_CONTR_INTFC_DT = "DS_CONTR_INTFC_DT";

    /** Column name: DS_CONTR_DESC_TXT */
    public static final String COL_DS_CONTR_DESC_TXT = "DS_CONTR_DESC_TXT";

    /** Column name: BASE_CHRG_TO_LEASE_CMPY_FLG */
    public static final String COL_BASE_CHRG_TO_LEASE_CMPY_FLG = "BASE_CHRG_TO_LEASE_CMPY_FLG";

    /** Column name: USG_CHRG_TO_LEASE_CMPY_FLG */
    public static final String COL_USG_CHRG_TO_LEASE_CMPY_FLG = "USG_CHRG_TO_LEASE_CMPY_FLG";

    /** Column name: INTG_MDSE_CD */
    public static final String COL_INTG_MDSE_CD = "INTG_MDSE_CD";

    /** Column name: CAP_BW_ORIG_QTY */
    public static final String COL_CAP_BW_ORIG_QTY = "CAP_BW_ORIG_QTY";

    /** Column name: CAP_COLOR_ORIG_QTY */
    public static final String COL_CAP_COLOR_ORIG_QTY = "CAP_COLOR_ORIG_QTY";

    /** Column name: CAP_TOT_ORIG_QTY */
    public static final String COL_CAP_TOT_ORIG_QTY = "CAP_TOT_ORIG_QTY";

    /** Column name: CAP_BW_RUN_QTY */
    public static final String COL_CAP_BW_RUN_QTY = "CAP_BW_RUN_QTY";

    /** Column name: CAP_COLOR_RUN_QTY */
    public static final String COL_CAP_COLOR_RUN_QTY = "CAP_COLOR_RUN_QTY";

    /** Column name: CAP_TOT_RUN_QTY */
    public static final String COL_CAP_TOT_RUN_QTY = "CAP_TOT_RUN_QTY";

    /** Column name: DS_CONTR_INTFC_BAT_SQ */
    public static final String COL_DS_CONTR_INTFC_BAT_SQ = "DS_CONTR_INTFC_BAT_SQ";

    /** Column name: PRC_SVC_PLN_TP_CD */
    public static final String COL_PRC_SVC_PLN_TP_CD = "PRC_SVC_PLN_TP_CD";

    /** Varchar Const : NINE_SEGMENT_DELIMITER_CHAR */
    public static final String  VARCHAR_NINE_SEGMENT_DELIMITER_CHAR = "NINE_SEGMENT_DELIMITER_CHAR";

    /** Max Percent */
    public static final int MAX_PERCENT = 100;

    /** DS_CONTR_BASE_USG_NM : BASE*/
    public static final String DS_CONTR_BASE_USG_NM_BASE = "BASE";

    /** DS_CONTR_BASE_USG_NM : OVERAGE */
    public static final String DS_CONTR_BASE_USG_NM_OVERAGE = "OVERAGE";

    /** Message parameter: CONTR_INTFC_SRC_TP_CD */
    public static final String PARAM_CONTR_INTFC_SRC_TP_CD = "Source Type";

    /** Message parameter: DS_CONTR_SRC_REF_NUM */
    public static final String PARAM_DS_CONTR_SRC_REF_NUM = "Source Ref#";

    /** Message parameter: DS_CONTR_INTFC_ACT_CD */
    public static final String PARAM_DS_CONTR_INTFC_ACT_CD = "Action Code";

    /** Message parameter: SER_NUM */
    public static final String PARAM_SER_NUM = "Serial#";

    /** Message parameter: Machine Master */
    public static final String PARAM_SER_NUM2 = "Machine Master";

    /** Message parameter: DS_CONTR_NUM */
    public static final String PARAM_DS_CONTR_NUM = "Contract#";

    /** Message parameter: DS Contract */
    public static final String PARAM_DS_CONTR_NUM2 = "DS Contract";

    /** Message parameter: VND_CD */
    public static final String PARAM_VND_CD = "Vendor Code";

    /** Message parameter: Vendor Master */
    public static final String PARAM_VND_CD2 = "Vendor Master";

    /** Message parameter: TECH_CD */
    public static final String PARAM_TECH_CD = "Technician Code";

    /** Message parameter: Technician Master */
    public static final String PARAM_TECH_CD2 = "Technician Master";

    /** Message parameter: EFF_FROM_DT */
    public static final String PARAM_EFF_FROM_DT = "Effective From Date";

    /** Message parameter: EFF_THRU_DT */
    public static final String PARAM_EFF_THRU_DT = "Effective Through Date";

    /** Message parameter: DS_CONTR_INTFC_BAT_NUM */
    public static final String PARAM_DS_CONTR_INTFC_BAT_NUM = "Interface Bat#";

    /** MODE: DS_CONTR_INTFC_BAT_NUM */
    public static final String MODE_DS_CONTR_INTFC_BAT_NUM = "1";

    /** MODE: DS_CONTR_NUM */
    public static final String MODE_DS_CONTR_NUM = "2";

    /** MODE: DS_CONTR_INTFC_ACT_CD */
    public static final String MODE_DS_CONTR_INTFC_ACT_CD = "3";

    /** MAX LENGTH SVC_CMN_TXT */
    public static final int MAX_LENGTH_SVC_CMN_TXT = 4000;

    /** VARCHAR CONST KEY: TERM_COND_CAP_BW_ORIG_ATTRB_NM */
    public static final String TERM_COND_CAP_BW_ORIG_ATTRB_NM = "TERM_COND_CAP_BW_ORIG_ATTRB_NM";

    /** VARCHAR CONST KEY: TERM_COND_CAP_CL_ORIG_ATTRB_NM */
    public static final String TERM_COND_CAP_CL_ORIG_ATTRB_NM = "TERM_COND_CAP_CL_ORIG_ATTRB_NM";

    /** VARCHAR CONST KEY: TERM_COND_CAP_TO_ORIG_ATTRB_NM */
    public static final String TERM_COND_CAP_TO_ORIG_ATTRB_NM = "TERM_COND_CAP_TO_ORIG_ATTRB_NM";

    /** VARCHAR CONST KEY: TERM_COND_CAP_BW_RUN_ATTRB_NM */
    public static final String TERM_COND_CAP_BW_RUN_ATTRB_NM = "TERM_COND_CAP_BW_RUN_ATTRB_NM";

    /** VARCHAR CONST KEY: TERM_COND_CAP_CLR_RUN_ATTRB_NM */
    public static final String TERM_COND_CAP_CLR_RUN_ATTRB_NM = "TERM_COND_CAP_CLR_RUN_ATTRB_NM";

    /** VARCHAR CONST KEY: TERM_COND_CAP_TOT_RUN_ATTRB_NM */
    public static final String TERM_COND_CAP_TOT_RUN_ATTRB_NM = "TERM_COND_CAP_TOT_RUN_ATTRB_NM";

    /** VARCHAR CONST KEY: TERM_COND_PRC_SVC_PLN_TP_CD */
    public static final String TERM_COND_PRC_SVC_PLN_TP_CD = "TERM_COND_PRC_SVC_PLN_TP_CD";

    // START 2016/05/24 [QC#447, ADD]
    /** VARCHAR CONST KEY: TERM_COND_RSP_TM_COMIT */
    public static final String TERM_COND_RSP_TM_COMIT = "TERM_COND_RSP_TM_COMIT";

    /** VARCHAR CONST KEY: TERM_COND_RSP_TM_MEAS_PER */
    public static final String TERM_COND_RSP_TM_MEAS_PER = "TERM_COND_RSP_TM_MEAS_PER";

    /** VARCHAR CONST KEY: TERM_COND_RSP_TM_RMD_1 */
    public static final String TERM_COND_RSP_TM_RMD_1 = "TERM_COND_RSP_TM_RMD_1";

    /** VARCHAR CONST KEY: TERM_COND_RSP_TM_RMD_2 */
    public static final String TERM_COND_RSP_TM_RMD_2 = "TERM_COND_RSP_TM_RMD_2";

    /** VARCHAR CONST KEY: TERM_COND_RSP_TM_RMD_3 */
    public static final String TERM_COND_RSP_TM_RMD_3 = "TERM_COND_RSP_TM_RMD_3";

    /** VARCHAR CONST KEY: TERM_COND_UP_TM_PCT */
    public static final String TERM_COND_UP_TM_PCT = "TERM_COND_UP_TM_PCT";

    /** VARCHAR CONST KEY: TERM_COND_UP_TM_MEAS_PER */
    public static final String TERM_COND_UP_TM_MEAS_PER = "TERM_COND_UP_TM_MEAS_PER";

    /** VARCHAR CONST KEY: TERM_COND_UP_TM_RMD_1 */
    public static final String TERM_COND_UP_TM_RMD_1 = "TERM_COND_UP_TM_RMD_1";

    /** VARCHAR CONST KEY: TERM_COND_UP_TM_RMD_2 */
    public static final String TERM_COND_UP_TM_RMD_2 = "TERM_COND_UP_TM_RMD_2";

    /** VARCHAR CONST KEY: TERM_COND_UP_TM_RMD_3 */
    public static final String TERM_COND_UP_TM_RMD_3 = "TERM_COND_UP_TM_RMD_3";

    /** VARCHAR CONST KEY: TERM_COND_MAX_CMBN_SLA_RMD */
    public static final String TERM_COND_MAX_CMBN_SLA_RMD = "TERM_COND_MAX_CMBN_SLA_RMD";

    /** SLA target VARCHAR CONST KEY */
    public static final String[] SLA_TERM_COND_VARCHAR_CONST_KEY = {TERM_COND_RSP_TM_MEAS_PER, TERM_COND_RSP_TM_RMD_1, TERM_COND_RSP_TM_RMD_2, TERM_COND_RSP_TM_RMD_3, TERM_COND_UP_TM_PCT, TERM_COND_UP_TM_MEAS_PER, TERM_COND_UP_TM_RMD_1,
            TERM_COND_UP_TM_RMD_2, TERM_COND_UP_TM_RMD_3, TERM_COND_MAX_CMBN_SLA_RMD };

    /** SLA value set target VARCHAR CONST KEY */
    public static final String[] SLA_TERM_COND_VARCHAR_CONST_KEY_SET_VALUE = {TERM_COND_RSP_TM_COMIT, TERM_COND_RSP_TM_MEAS_PER, TERM_COND_RSP_TM_RMD_1, TERM_COND_RSP_TM_RMD_2, TERM_COND_MAX_CMBN_SLA_RMD };
    // END   2016/05/24 [QC#447, ADD]

    /** MAX LENGTH TERM_COND_CA */
    public static final int TERM_COND_CA_MAX_LENGTH = 7;

    /** VARCHAR CONST ARGS: TERM_COND_CAP_BW_ORIG_ATTRB_NM */
    public static final int ARGS_TERM_COND_CAP_BW_ORIG_ATTRB_NM = 0;

    /** VARCHAR CONST ARGS: TERM_COND_CAP_CL_ORIG_ATTRB_NM */
    public static final int ARGS_TERM_COND_CAP_CL_ORIG_ATTRB_NM = 1;

    /** VARCHAR CONST ARGS: TERM_COND_CAP_TO_ORIG_ATTRB_NM */
    public static final int ARGS_TERM_COND_CAP_TO_ORIG_ATTRB_NM = 2;

    /** VARCHAR CONST ARGS: TERM_COND_CAP_BW_RUN_ATTRB_NM */
    public static final int ARGS_TERM_COND_CAP_BW_RUN_ATTRB_NM = 3;

    /** VARCHAR CONST ARGS: TERM_COND_CAP_CLR_RUN_ATTRB_NM */
    public static final int ARGS_TERM_COND_CAP_CLR_RUN_ATTRB_NM = 4;

    /** VARCHAR CONST ARGS: TERM_COND_CAP_TOT_RUN_ATTRB_NM */
    public static final int ARGS_TERM_COND_CAP_TOT_RUN_ATTRB_NM = 5;

    /** VARCHAR CONST ARGS: TERM_COND_PRC_SVC_PLN_TP_CD */
    public static final int ARGS_TERM_COND_PRC_SVC_PLN_TP_CD = 6;

    /** MAX DAY: 2999/12/31 */
    public static final String MAX_DATE = "29991231";

    /** DATE FORMAT: yyyymmdd */
    public static final String FORMAT = "yyyymmdd";

    /** PSN_CD LENGTH */
    public static final int PSN_CD_LENGTH = 8;

    /** Ex data pattern Top only */
    public static final String EX_PATTERN_TOP_ONLY = "01";

    /** Ex data pattern Top and Second */
    public static final String EX_PATTERN_TOP_SECOND = "02";

    /** Ex data pattern Second only */
    public static final String EX_PATTERN_SECOND_ONLY = "03";

    /** Additional data pattern Top only */
    public static final String ADD_PATTERN_TOP_ONLY = "01";

    /** Additional data pattern Top and Second */
    public static final String ADD_PATTERN_TOP_SECOND = "02";

    /** Additional data pattern Second only */
    public static final String ADD_PATTERN_SECOND_ONLY = "03";

    /** DELIMITA */
    public static final String DELIMITA = ",";

    /** RESRC_OBJ_NM Value : NSAB0220 */
    public static final String RESRC_OBJ_NM = "NSAB0220";

    /** SPCL_FLT_MDSE_CD */
    public static final String SPCL_FLT_MDSE_CD = "SPCL_FLT_MDSE_CD";

    // add start 2017/10/10 QC#21617
    /** DEF_FSR_NUM */
    public static final String DEF_FSR_NUM = "-1";
    // add end 2017/10/10 QC#21617

    // START 2018/06/11 M.Naito [QC#22971, ADD]
    /** NumConst : DEF_CONTR_UPLFT_TERM_AOT */
    public static final String NUM_CONST_DEF_CONTR_UPLFT_TERM_AOT = "DEF_CONTR_UPLFT_TERM_AOT";

    /** NumConst Default Value: DEF_CONTR_UPLFT_TERM_AOT */
    public static final BigDecimal DEF_CONTR_UPLFT_TERM_AOT = new BigDecimal(5);
    // END 2018/06/11 M.Naito [QC#22971, ADD]

    // START 2023/02/16 K.Watanabe [QC#60913, ADD]
    /** End Date : 99991231 */
    public static final String END_DT = "99991231";
    // END 2023/02/16 K.Watanabe [QC#60913, ADD]
}
