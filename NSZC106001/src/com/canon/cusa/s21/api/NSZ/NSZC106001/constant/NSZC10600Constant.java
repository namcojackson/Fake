/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC106001.constant;

/**
 *<pre>
 * NSZC1060: CUSA Retail Contract Interface API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/17/2016   CITS            M.Okigami       Create          N/A
 * 01/11/2017   CITS            T.Kikuhara      Update          QC#15484
 * 02/20/2017   Hitachi         K.Kitachi       Update          QC#17604
 * 03/06/2017   Hitachi         K.Ochiai        Update          QC#17747
 * 12/19/2017   Hitachi         T.Tomita        Update          QC#18362
 * 2017/12/28   Hitachi         U.Kim           Update          QC#22735
 * 2018/01/15   Hitachi         U.Kim           Update          QC#22920
 *</pre>
 */
public class NSZC10600Constant {

    // =================================================
    // Message Code
    // =================================================
    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "Sales Date" is a mandatory field.
     */
    public static final String NSZM0002E = "NSZM0002E";

    /**
     * Failed to update the ROSS_INTFC_CONTR.
     */
    public static final String NSZM0971E = "NSZM0971E";

    /**
     * Failed to insert the DS_CONTR_INTFC.
     */
    public static final String NSZM0972E = "NSZM0972E";

    /**
     * Failed to insert the DS_ACTL_CNT_INTFC.
     */
    public static final String NSZM0974E = "NSZM0974E";

    /**
     * Failed to insert the DS_XS_COPY_INTFC.
     */
    public static final String NSZM0975E = "NSZM0975E";

    /**
     * Failed to insert the DS_SUB_CONTR_INTFC.
     */
    public static final String NSZM0977E = "NSZM0977E";

    /**
     * Failed to insert the ROSS_INTFC_CONTR_COMP.
     */
    public static final String NSZM0978E = "NSZM0978E";

    /**
     * Failed to update the ROSS_INTFC_CONTR_COMP.
     */
    public static final String NSZM0979E = "NSZM0979E";

    /**
     * Input parameter "PRNT_BLLG_MSTR_PK" is a mandatory field.
     */
    public static final String NSZM1025E = "NSZM1025E";

    // =================================================
    // DB Value
    // =================================================
    /**
     * Process Text
     */
    public static final String ROSS_INTFC_CONTR_PROC_TXT_DEALER_NOT_EXIST = "Dealer does not exist in TPC01 Dealer Info and ROSS Contract Dealer View.";

    /**
     * Process Text
     */
    public static final String ROSS_INTFC_CONTR_PROC_TXT_END_OF_LIFE = "Machine/serial number with End Of Life status cannot be placed on any contract line.";

    /**
     * Process Text
     */
    public static final String ROSS_INTFC_CONTR_PROC_TXT_24_HOUR_WINDOW = "The Same Serial Number cannot be processed twice in a 24 Hour Window.";

    /**
     * Process Text
     */
    public static final String ROSS_INTFC_CONTR_PROC_TXT_SERIAL_NOT_EXIST = "Serial# does not exist in SVC_MACH_MSTR.";

    /**
     * DS_CONTR_CTRL_STS_CD
     */
    public static final String DS_CONTR_CTRL_STS_CD_TRMD = "TRMD";

    /**
     * DS_CONTR_CTRL_STS_CD
     */
    public static final String DS_CONTR_CTRL_STS_CD_EXPD = "EXPD";

    /**
     * DS_CONTR_CTRL_STS_CD
     */
    public static final String DS_CONTR_CTRL_STS_CD_CANC = "CANC";

    /**
     * SVC_LINE_BIZ_CD
     */
    public static final String SVC_LINE_BIZ_CD_ESS = "ESS";

    /**
     * ROSS_INTFC_CONTR_PROC_NORMAL
     */
    public static final String ROSS_INTFC_CONTR_PROC_NORMAL = "N";

    /**
     * ROSS_INTFC_CONTR_PROC_ERROR
     */
    public static final String ROSS_INTFC_CONTR_PROC_ERROR = "E";

    /**
     * ROSS_INTFC_CONTR_PROC_INTERFACED
     */
    public static final String ROSS_INTFC_CONTR_PROC_INTERFACED = "I";

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

    /**
     * SUB_CONTR_INTFC_STS_NORMAL
     */
    public static final String SUB_CONTR_INTFC_STS_NORMAL = "N";

    // =================================================
    // DB Param
    // =================================================
    /** GLBL_CMPY_CD */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** Sales Date */
    public static final String DB_PARAM_SALES_DATE = "slsDt";

    /** ROSS_INTFC_CONTR_PROC_CD */
    public static final String DB_PARAM_ROSS_INTFC_CONTR_PROC_CD = "rossIntfcContrProcCd";

    /** ROSS_INTFC_PROC_STS_CD */
    public static final String DB_PARAM_ROSS_INTFC_PROC_STS_CD = "rossIntfcProcStsCd";

    /** PRNT_BLLG_MSTR_ID */
    public static final String DB_PARAM_PRNT_BLLG_MSTR_ID = "prntBllgMstrId";

    /** DS_CONTR_SRC_TP_CD */
    public static final String DB_PARAM_DS_CONTR_SRC_TP_CD = "dsContrSrcTpCd";

    /** DS_CONTR_CTRL_STS_CD */
    public static final String DB_PARAM_DS_CONTR_CTRL_STS_CD = "dsContrCtrlStsCd";

    /** CONTR_INTFC_SRC_TP_CD */
    public static final String DB_PARAM_CONTR_INTFC_SRC_TP_CD = "contrIntfcSrcTpCd";

    /** ENBL_FLG */
    public static final String DB_PARAM_ENBL_FLG = "enblFlg";

    /** BLLG_MSTR_PK */
    public static final String DB_PARAM_BLLG_MSTR_PK = "bllgMstrPk";

    /** EOL_CONTR_FLG */
    public static final String DB_PARAM_EOL_CONTR_FLG = "eolContrFlg";

    /** BLLG_CYCLE_CD */
    public static final String DB_PARAM_BLLG_CYCLE_CD = "bllgCycleCd";

    /** MTR_LB_CD */
    public static final String DB_PARAM_MTR_LB_CD = "mtrLbCd";

    /** ROSS_INTFC_CONTR_PK */
    public static final String DB_PARAM_ROSS_INTFC_CONTR_PK = "rossIntfcContrPk";

    /** CONTR_TRMN_FLG */
    public static final String DB_PARAM_CONTR_TRMN_FLG = "contrTrmnFlg";

    /** CTAC_PSN_ACTV_FLG */
    public static final String DB_PARAM_CTAC_PSN_ACTV_FLG = "ctacPsnActvFlg";

    /** CTAC_TP_CD */
    public static final String DB_PARAM_CTAC_TP_CD = "ctacTpCd";

    /** DEL_FLG */
    public static final String DB_PARAM_DEL_FLG = "delFlg";

    // START 2018/01/15 U.Kim [QC#22920, ADD]
    /** SPLY_IND_FLG */
    public static final String DB_PARAM_SPLY_IND_FLG = "splyIndFlg";

    /** ACTV_FLG */
    public static final String DB_PARAM_ACTV_FLG = "actvFlg";
    // START 2018/01/15 U.Kim [QC#22920, ADD]

    /** GLBL_CMPY_CD(ABR) */
    public static final String DB_PARAM_GLBL_CMPY_CD_ABR = "glblCmpyCdAbr";

    /** DEL_FLG */
    public static final String DB_PARAM_ROSS_INTFC_PROC_MODE_CD = "rossIntcfProcModeCd";

    /** RTL_DLR_CD */
    public static final String DB_PARAM_RTL_DLR_CD = "rtlDlrCd";

    /** SER_NUM */
    public static final String DB_PARAM_SER_NUM = "serNum";

    // START 2017/03/06 K.Ochiai [QC#17747, ADD]
    /** DS_COND_CONST_GRP_ID */
    public static final String DB_PARAM_DS_COND_CONST_GRP_ID = "NSZC1060_BLLG_TMG_TP";
    // ENDT 2017/03/06 K.Ochiai [QC#17747, ADD]
    // Add Start 2017/12/19 QC#18362
    /** SVC_LINE_BIZ_CD */
    public static final String DB_PARAM_SVC_LINE_BIZ_CD = "svcLineBizCd";

    /** DS_ACCT_NUM */
    public static final String DB_PARAM_DS_ACCT_NUM = "dsAcctNum";

    /** EFF_FROM_DT */
    public static final String DB_PARAM_EFF_FROM_DT = "effFromDt";
    // Add End 2017/12/19 QC#18362

    // =================================================
    // DB Columns
    // =================================================
    /** ROSS_INTFC_PROC_MODE_CD */
    public static final String DB_COLUMN_ROSS_INTFC_PROC_MODE_CD = "ROSS_INTFC_PROC_MODE_CD";

    /** ROSS_INTFC_CONTR_PK */
    public static final String DB_COLUMN_ROSS_INTFC_CONTR_PK = "ROSS_INTFC_CONTR_PK";

    /** BLLG_MSTR_PK */
    public static final String DB_COLUMN_BLLG_MSTR_PK = "BLLG_MSTR_PK";

    /** DS_CONTR_NUM */
    public static final String DB_COLUMN_DS_CONTR_NUM = "DS_CONTR_NUM";

    /** SER_NUM */
    public static final String DB_COLUMN_SER_NUM = "SER_NUM";

    /** SVC_MACH_MSTR_PK */
    public static final String DB_COLUMN_SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** SELL_TO_CUST_CD */
    public static final String DB_COLUMN_SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** BILL_TO_CUST_CD */
    public static final String DB_COLUMN_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** SVC_CONTR_BR_CD */
    public static final String DB_COLUMN_SVC_CONTR_BR_CD = "SVC_CONTR_BR_CD";

    /** TOC_CD */
    public static final String DB_COLUMN_TOC_CD = "TOC_CD";

    /** MTR_EST_TP_CD */
    public static final String DB_COLUMN_MTR_EST_TP_CD = "MTR_EST_TP_CD";

    /** SVC_PGM_MDSE_CD */
    public static final String DB_COLUMN_SVC_PGM_MDSE_CD = "SVC_PGM_MDSE_CD";

    /** MDSE_CD */
    public static final String DB_COLUMN_MDSE_CD = "MDSE_CD";

    /** MDL_ID */
    public static final String DB_COLUMN_MDL_ID = "MDL_ID";

    /** T_MDL_NM */
    public static final String DB_COLUMN_T_MDL_NM = "T_MDL_NM";

    /** CONTR_START_DT */
    public static final String DB_COLUMN_CONTR_START_DT = "CONTR_START_DT";

    /** CONTR_END_DT */
    public static final String DB_COLUMN_CONTR_END_DT = "CONTR_END_DT";

    /** BEF_END_RNW_DAYS_AOT */
    public static final String DB_COLUMN_BEF_END_RNW_DAYS_AOT = "BEF_END_RNW_DAYS_AOT";

    /** CONTR_UPLFT_TP_CD */
    public static final String DB_COLUMN_CONTR_UPLFT_TP_CD = "CONTR_UPLFT_TP_CD";

    /** UPLFT_PRC_METH_CD */
    public static final String DB_COLUMN_UPLFT_PRC_METH_CD = "UPLFT_PRC_METH_CD";

    /** UPLFT_BASE_PRC_UP_RATIO */
    public static final String DB_COLUMN_UPLFT_BASE_PRC_UP_RATIO = "UPLFT_BASE_PRC_UP_RATIO";

    /** MTR_READ_METH_CD */
    public static final String DB_COLUMN_MTR_READ_METH_CD = "MTR_READ_METH_CD";

    /** BASE_PRC_DEAL_AMT */
    public static final String DB_COLUMN_BASE_PRC_DEAL_AMT = "BASE_PRC_DEAL_AMT";

    /** CONTR_CLO_DAY */
    public static final String DB_COLUMN_CONTR_CLO_DAY = "CONTR_CLO_DAY";

    /** CONTR_BLLG_DAY */
    public static final String DB_COLUMN_CONTR_BLLG_DAY = "CONTR_BLLG_DAY";

    /** BLLG_THRU_DT */
    public static final String DB_COLUMN_BLLG_THRU_DT = "BLLG_THRU_DT";

    /** MLY_BASE_COMP_AMT */
    public static final String DB_COLUMN_MLY_BASE_COMP_AMT = "MLY_BASE_COMP_AMT";

    /** PMT_TERM_AOT */
    public static final String DB_COLUMN_PMT_TERM_AOT = "PMT_TERM_AOT";

    /** BLLG_CYCLE_MTH_AOT */
    public static final String DB_COLUMN_BLLG_CYCLE_MTH_AOT = "BLLG_CYCLE_MTH_AOT";

    /** RTL_DIV_CD */
    public static final String DB_COLUMN_RTL_DIV_CD = "RTL_DIV_CD";

    /** CTAC_PSN_PK */
    public static final String DB_COLUMN_CTAC_PSN_PK = "CTAC_PSN_PK";

    /** CTAC_PSN_FIRST_NM */
    public static final String DB_COLUMN_CTAC_PSN_FIRST_NM = "CTAC_PSN_FIRST_NM";

    /** CTAC_PSN_LAST_NM */
    public static final String DB_COLUMN_CTAC_PSN_LAST_NM = "CTAC_PSN_LAST_NM";

    /** DS_BILL_TGTR_FLG */
    public static final String DB_COLUMN_DS_BILL_TGTR_FLG = "DS_BILL_TGTR_FLG";

    /** PMT_TERM_CASH_DISC_CD */
    public static final String DB_COLUMN_PMT_TERM_CASH_DISC_CD = "PMT_TERM_CASH_DISC_CD";

    /** MTR_LB_CD */
    public static final String DB_COLUMN_MTR_LB_CD = "MTR_LB_CD";

    /** MTR_LB_NM */
    public static final String DB_COLUMN_MTR_LB_NM = "MTR_LB_NM";

    /** DEF_START_MTR_CNT */
    public static final String DB_COLUMN_DEF_START_MTR_CNT = "DEF_START_MTR_CNT";

    /** BLLG_ROLL_OVER_RATIO */
    public static final String DB_COLUMN_BLLG_ROLL_OVER_RATIO = "BLLG_ROLL_OVER_RATIO";

    /** MLY_COPY_INCL_COMP_QTY */
    public static final String DB_COLUMN_MLY_COPY_INCL_COMP_QTY = "MLY_COPY_INCL_COMP_QTY";

    /** XS_MTR_COMP_AMT_RATE */
    public static final String DB_COLUMN_XS_MTR_COMP_AMT_RATE = "XS_MTR_COMP_AMT_RATE";

    /** INTG_MDSE_CD */
    public static final String DB_COLUMN_INTG_MDSE_CD = "INTG_MDSE_CD";

    /** UPLFT_MTR_PRC_UP_RATIO */
    public static final String DB_COLUMN_UPLFT_MTR_PRC_UP_RATIO = "UPLFT_MTR_PRC_UP_RATIO";

    /** MDL_MTR_LB_CD */
    public static final String DB_COLUMN_MDL_MTR_LB_CD = "MDL_MTR_LB_CD";

    /** BW_MTR_FLG */
    public static final String DB_COLUMN_BW_MTR_FLG = "BW_MTR_FLG";

    /** BLLG_MSTR_BLLG_MTR_PK */
    public static final String DB_COLUMN_BLLG_MSTR_BLLG_MTR_PK = "BLLG_MSTR_BLLG_MTR_PK";

    /** XS_MTR_COMP_COPY_QTY */
    public static final String DB_COLUMN_XS_MTR_COMP_COPY_QTY = "XS_MTR_COMP_COPY_QTY";

    /** BLLG_MTR_LVL_NUM */
    public static final String DB_COLUMN_BLLG_MTR_LVL_NUM = "BLLG_MTR_LVL_NUM";

    /** VND_CD */
    public static final String DB_COLUMN_VND_CD = "VND_CD";

    /** TECH_TOC_CD */
    public static final String DB_COLUMN_TECH_TOC_CD = "TECH_TOC_CD";

    /** EFF_FROM_DT */
    public static final String DB_COLUMN_EFF_FROM_DT = "EFF_FROM_DT";

    /** EFF_THRU_DT */
    public static final String DB_COLUMN_EFF_THRU_DT = "EFF_THRU_DT";

    /** ADMIN_PRC_DEAL_AMT */
    public static final String DB_COLUMN_ADMIN_PRC_DEAL_AMT = "ADMIN_PRC_DEAL_AMT";

    /** PREPD_MAINT_FLG */
    public static final String DB_COLUMN_PREPD_MAINT_FLG = "PREPD_MAINT_FLG";

    /** BW_MTR_ALWNC_CNT */
    public static final String DB_COLUMN_BW_MTR_ALWNC_CNT = "BW_MTR_ALWNC_CNT";

    /** COLOR_MTR_ALWNC_CNT */
    public static final String DB_COLUMN_COLOR_MTR_ALWNC_CNT = "COLOR_MTR_ALWNC_CNT";

    /** BW_MTR_PRC_AMT_RATE */
    public static final String DB_COLUMN_BW_MTR_PRC_AMT_RATE = "BW_MTR_PRC_AMT_RATE";

    /** COLOR_MTR_PRC_AMT_RATE */
    public static final String DB_COLUMN_COLOR_MTR_PRC_AMT_RATE = "COLOR_MTR_PRC_AMT_RATE";

    /** SPLY_INCL_FLG */
    public static final String DB_COLUMN_SPLY_INCL_FLG = "SPLY_INCL_FLG";

    /** BLLG_CYCLE_CD */
    public static final String DB_COLUMN_BLLG_CYCLE_CD = "BLLG_CYCLE_CD";

    /** DLR_FLEET_FLG */
    public static final String DB_COLUMN_DLR_FLEET_FLG = "DLR_FLEET_FLG";

    /** DLR_FLEET_NUM */
    public static final String DB_COLUMN_DLR_FLEET_NUM = "DLR_FLEET_NUM";

    /** PRF_TECH_CD */
    public static final String DB_COLUMN_PRF_TECH_CD = "PRF_TECH_CD";

    /** PSN_CD */
    public static final String DB_COLUMN_PSN_CD = "PSN_CD";

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

    /** ROSS_INTFC_CONTR_COMP_PK */
    public static final String DB_COLUMN_ROSS_INTFC_CONTR_COMP_PK = "ROSS_INTFC_CONTR_COMP_PK";

    /** PRINT_DTL_FLG */
    public static final String DB_COLUMN_PRINT_DTL_FLG = "PRINT_DTL_FLG";

    /** CONTR_ADMIN_PSN_CD */
    public static final String DB_COLUMN_CONTR_ADMIN_PSN_CD = "CONTR_ADMIN_PSN_CD";

    // START 2017/02/20 K.Kitachi [QC#17604, ADD]
    /** MRCF_TP_CD */
    public static final String DB_COLUMN_MRCF_TP_CD = "MRCF_TP_CD";
    // END 2017/02/20 K.Kitachi [QC#17604, ADD]

    // START 2017/03/06 K.Ochiai [QC#17747, ADD]
    /** BLLG_TMG_TP_CD */
    public static final String DB_COLUMN_BLLG_TMG_TP_CD = "BLLG_TMG_TP_CD";

    /** DEF_BASE_CYCLE_CD */
    public static final String DB_COLUMN_DEF_BASE_CYCLE_CD = "DEF_BASE_CYCLE_CD";

    /** DEF_USG_CYCLE_CD */
    public static final String DB_COLUMN_DEF_USG_CYCLE_CD = "DEF_USG_CYCLE_CD";
    // END 2017/03/06 K.Ochiai [QC#17747, ADD]

    // START 2017/11/11 [QC#22175, ADD]
    /** TRMN_DT */
    public static final String DB_COLUMN_TRMN_DT = "TRMN_DT";
    // END 2017/11/11 [QC#22175, ADD]

    // START 2017/12/28 U.Kim [QC#22735,ADD]
    /** BLLG_DAY */
    public static final String DB_COLUMN_BLLG_DAY = "BLLG_DAY";
    // END 2017/12/28 U.Kim [QC#22735,ADD]

    // =================================================
    // VAR_CHAR_CONST_NAME
    // =================================================
    /** NSZC1060_BLLG_MTR_LB_CD_SM */
    public static final String NSZC1060_BLLG_MTR_LB_CD_SM = "NSZC1060_BLLG_MTR_LB_CD_SM";

    /** NSZC1060_BLLG_MTR_LB_CD_BW */
    public static final String NSZC1060_BLLG_MTR_LB_CD_BW = "NSZC1060_BLLG_MTR_LB_CD_BW";

    /** NSZC1060_BLLG_MTR_LB_CD_CLR */
    public static final String NSZC1060_BLLG_MTR_LB_CD_CLR = "NSZC1060_BLLG_MTR_LB_CD_CLR";

    /** NSZC1060_MTR_EST_TP_CD */
    public static final String NSZC1060_MTR_EST_TP_CD = "NSZC1060_MTR_EST_TP_CD";

    /** NSZC1060_CONTR_UPLFT_TP_CD */
    public static final String NSZC1060_CONTR_UPLFT_TP_CD = "NSZC1060_CONTR_UPLFT_TP_CD";

    /** NSZC1060_PRINT_DTL_FLG */
    public static final String NSZC1060_PRINT_DTL_FLG = "NSZC1060_PRINT_DTL_FLG";

    /** CUSA_GLBL_CMPY_CD */
    public static final String CUSA_GLBL_CMPY_CD = "CUSA_GLBL_CMPY_CD";

}
