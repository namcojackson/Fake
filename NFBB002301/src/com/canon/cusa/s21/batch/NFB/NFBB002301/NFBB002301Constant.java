/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB002301;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Stock-in records from INVTY_TRX table and insert records into CM_IF_MDSE_STK_IN table
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/14   Fujitsu         H.Ikeda         Update          QC#15823
 * 2017/05/16   CITS            S.Endo          Update          RS#11
 * 2019/09/17   Hitachi         Y.Takeno        Update          QC#51820
 * </pre>
 */
public class NFBB002301Constant {

    // ** Fixed Value ** //

    /** Bulk Commit Limit */
    static final int INT_BULK_COM_LIM = 10000;

    // ** SSM Name ** //
    /** SELECT_PROC_DT */
    static final String SELECT_PROC_DT = "SELECT_PROC_DT";

    /** SSM Statement ID */
    static final String SELECT_CM_STK_IN = "SELECT_CM_STK_IN";

    // START 2019/09/17 [QC#51820, ADD]
    /** SSM Statement ID */
    static final String UPDATE_CM_STK_IN = "UPDATE_CM_STK_IN";

    /** SSM Statement ID */
    static final String FIND_AUTHORIZED_AP_INVOICES = "FIND_AUTHORIZED_AP_INVOICES";

    /** PSN_CD_NFBB0023 */
    static final String PSN_CD_NFBB0023 = "NFBB0023";
    // END   2019/09/17 [QC#51820, ADD]

    // ** Message ID ** //
    /** Error Message (Unexpected Error) */
    static final String NFBM0028E = "NFBM0028E";
    // START 2016/12/14 [QC#15823, ADD]
    /** Error Message (Insert Error) */
    static final String NFZM0028E = "NFZM0028E";
    // END   2016/12/14 [QC#15823, ADD]

    // START 2019/09/17 [QC#51820, ADD]
    /** Error Message (Specified PO Information is invalid or does not exist.) */
    static final String NPZM0021E = "NPZM0021E";
    // END   2019/09/17 [QC#51820, ADD]

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** DB Item Column Name */
    static final String VND_CD = "VND_CD";

    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD";

    /** DB Item Column Name */
    static final String TRX_CD = "TRX_CD";

    /** DB Item Column Name */
    static final String PROD_LINE_PROD_CTRL_CD = "PROD_LINE_PROD_CTRL_CD";

    /** DB Item Column Name */
    static final String INV_STK_IN_DT = "INV_STK_IN_DT";

    /** DB Item Column Name */
    static final String VND_INV_NUM = "VND_INV_NUM";

    /** DB Item Column Name */
    static final String DELY_ORD_NUM = "DELY_ORD_NUM";

    /** DB Item Column Name */
    static final String COA_PROD_CD = "COA_PROD_CD";

    /** DB Item Column Name */
    static final String IMPT_DO_SO_NUM = "IMPT_DO_SO_NUM";

    /** DB Item Column Name */
    static final String CM_PROC_DT = "CM_PROC_DT";

    /** DB Item Column Name */
    static final String CM_HIST_TS = "CM_HIST_TS";

    /** DB Item Column Name */
    static final String PO_ORD_NUM = "PO_ORD_NUM";

    /** DB Item Column Name */
    static final String PO_QTY = "PO_QTY";

    /** DB Item Column Name */
    static final String THIS_MTH_FOB_COST_AMT = "THIS_MTH_FOB_COST_AMT";

    /** DB Item Column Name */
    static final String TRX_RSN_CD = "TRX_RSN_CD";

    /** DB Item Column Name */
    static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** DB Item Column Name */
    static final String VND_RTRN_NUM = "VND_RTRN_NUM";

    /** DB Item Column Name */
    static final String TRX_LINE_NUM = "TRX_LINE_NUM";

    /** DB Item Column Name */
    static final String TRX_LINE_SUB_NUM = "TRX_LINE_SUB_NUM";

    /** DB Item Column Name */
    static final String ENT_DEAL_NET_UNIT_PRC_AMT = "ENT_DEAL_NET_UNIT_PRC_AMT";

    /** DB Item Column Name */
    static final String ENT_PO_DTL_DEAL_NET_AMT = "ENT_PO_DTL_DEAL_NET_AMT";

    /** DB Item Column Name */
    static final String ENT_FUNC_NET_UNIT_PRC_AMT = "ENT_FUNC_NET_UNIT_PRC_AMT";

    /** DB Item Column Name */
    static final String ENT_PO_DTL_FUNC_NET_AMT = "ENT_PO_DTL_FUNC_NET_AMT";

    /** DB Item Column Name */
    static final String EXCH_RATE = "EXCH_RATE";

    /** DB Item Column Name */
    static final String CM_STK_IN_PK = "CM_STK_IN_PK";

    /** DB Item Column Name */
    static final String MDSE_OR_PRT_CD = "MDSE_OR_PRT_CD";

    /** DB Item Column Name */
    static final String PO_NUM = "PO_NUM";

    /** DB Item Column Name */
    static final String ACCT_REC_CNT = "ACCT_REC_CNT";

    /** DB Item Column Name */
    static final String STK_IN_ERR_FLG = "STK_IN_ERR_FLG";

    /** DB Item Column Name */
    static final String VND_INV_SQ_NUM = "VND_INV_SQ_NUM";

    /** DB Item Column Name */
    static final String ACCT_YR_MTH = "ACCT_YR_MTH";

    /** DB Item Column Name */
    static final String INV_LOC_RCV_DT = "INV_LOC_RCV_DT";

    /** DB Item Column Name */
    static final String INV_SEND_CD = "INV_SEND_CD";

    /** DB Item Column Name */
    static final String LC_DA_NUM = "LC_DA_NUM";

    /** DB Item Column Name */
    static final String VND_SHIP_VIA_PRCH_CD = "VND_SHIP_VIA_PRCH_CD";

    /** DB Item Column Name */
    static final String ACTL_FOB_CD = "ACTL_FOB_CD";

    /** DB Item Column Name */
    static final String ACCT_SHPG_TERM_CD = "ACCT_SHPG_TERM_CD";

    /** DB Item Column Name */
    static final String STK_IN_RSN_DESC_TXT = "STK_IN_RSN_DESC_TXT";

    /** DB Item Column Name */
    static final String INV_SC_FOB_COST_AMT = "INV_SC_FOB_COST_AMT";

    /** DB Item Column Name */
    static final String INV_SC_FRT_COST_AMT = "INV_SC_FRT_COST_AMT";

    /** DB Item Column Name */
    static final String INV_SC_INS_COST_AMT = "INV_SC_INS_COST_AMT";

    /** DB Item Column Name */
    static final String INV_OC_FOB_COST_AMT = "INV_OC_FOB_COST_AMT";

    /** DB Item Column Name */
    static final String INV_OC_FRT_COST_AMT = "INV_OC_FRT_COST_AMT";

    /** DB Item Column Name */
    static final String INV_OC_INS_COST_AMT = "INV_OC_INS_COST_AMT";

    /** DB Item Column Name */
    static final String INV_QTY = "INV_QTY";

    /** DB Item Column Name */
    static final String FOB_CCY_CD = "FOB_CCY_CD";

    /** DB Item Column Name */
    static final String FRT_CCY_CD = "FRT_CCY_CD";

    /** DB Item Column Name */
    static final String INS_CCY_CD = "INS_CCY_CD";

    /** DB Item Column Name */
    static final String RCV_QTY = "RCV_QTY";

    /** DB Item Column Name */
    static final String SLS_PRMO_QTY = "SLS_PRMO_QTY";

    /** DB Item Column Name */
    static final String AC_MDSE_FOB_COST_AMT = "AC_MDSE_FOB_COST_AMT";

    /** DB Item Column Name */
    static final String PLN_SC_FOB_COST_AMT = "PLN_SC_FOB_COST_AMT";

    /** DB Item Column Name */
    static final String PLN_SC_FRT_COST_AMT = "PLN_SC_FRT_COST_AMT";

    /** DB Item Column Name */
    static final String PLN_SC_INS_COST_AMT = "PLN_SC_INS_COST_AMT";

    /** DB Item Column Name */
    static final String PLN_SC_DTY_COST_AMT = "PLN_SC_DTY_COST_AMT";

    /** DB Item Column Name */
    static final String PLN_SC_OTH_COST_AMT = "PLN_SC_OTH_COST_AMT";

    /** DB Item Column Name */
    static final String STK_IN_SC_FOB_COST_AMT = "STK_IN_SC_FOB_COST_AMT";

    /** DB Item Column Name */
    static final String STK_IN_SC_FRT_COST_AMT = "STK_IN_SC_FRT_COST_AMT";

    /** DB Item Column Name */
    static final String STK_IN_SC_INS_COST_AMT = "STK_IN_SC_INS_COST_AMT";

    /** DB Item Column Name */
    static final String STK_IN_SC_DTY_COST_AMT = "STK_IN_SC_DTY_COST_AMT";

    /** DB Item Column Name */
    static final String STK_IN_SC_OTH_COST_AMT = "STK_IN_SC_OTH_COST_AMT";

    /** DB Item Column Name */
    static final String SLS_PRMO_SC_FOB_COST_AMT = "SLS_PRMO_SC_FOB_COST_AMT";

    /** DB Item Column Name */
    static final String SLS_PRMO_SC_FRT_COST_AMT = "SLS_PRMO_SC_FRT_COST_AMT";

    /** DB Item Column Name */
    static final String SLS_PRMO_SC_INS_COST_AMT = "SLS_PRMO_SC_INS_COST_AMT";

    /** DB Item Column Name */
    static final String SLS_PRMO_SC_DTY_COST_AMT = "SLS_PRMO_SC_DTY_COST_AMT";

    /** DB Item Column Name */
    static final String SLS_PRMO_SC_OTH_COST_AMT = "SLS_PRMO_SC_OTH_COST_AMT";

    /** DB Item Column Name */
    static final String PSET_VND_FLG = "PSET_VND_FLG";

    /** DB Item Column Name */
    static final String INV_ASG_FLG = "INV_ASG_FLG";

    /** DB Item Column Name */
    static final String STK_IN_ITEM_DEL_FLG = "STK_IN_ITEM_DEL_FLG";

    /** DB Item Column Name */
    static final String PSET_ASG_FLG = "PSET_ASG_FLG";

    /** DB Item Column Name */
    static final String CM_STK_IN_VAR_CALC_FLG = "CM_STK_IN_VAR_CALC_FLG";

    /** DB Item Column Name */
    static final String CM_STK_IN_VAR_FRCE_CALC_FLG = "CM_STK_IN_VAR_FRCE_CALC_FLG";

    /** DB Item Column Name */
    static final String STK_IN_ASG_ERR_RSN_CD = "STK_IN_ASG_ERR_RSN_CD";

    /** DB Item Column Name */
    static final String EZINTIME = "EZINTIME";

    /** DB Item Column Name */
    static final String INVTY_TRX_PK = "INVTY_TRX_PK";

    // START 2019/09/17 [QC#51820, ADD]
    /** DB Item Column Name */
    static final String AP_VND_CD = "AP_VND_CD";

    /** DB Item Column Name */
    static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";

    /** DB Item Column Name */
    static final String AP_VND_INV_SQ_NUM = "AP_VND_INV_SQ_NUM";
    // END   2019/09/17 [QC#51820, ADD]
}
