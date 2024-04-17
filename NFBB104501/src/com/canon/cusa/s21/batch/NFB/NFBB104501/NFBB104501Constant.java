/** 
* <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Stock-In Assignment
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/09/2013   Fujitsu         K.Kimura        Create          CMEX
 * </pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB104501;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/09/2013   Fujitsu         K.Kimura        Create          CMEX
 * 11/04/2016   Hitachi         Y.Tsuchimoto    Update          QC#14562
 * 2017/12/21   CITS            T.Hakodate      Update          QC#23038
 * 2018/03/30   CITS            T.Kikuhara      Update          QC#20316
 * </pre>
 */
public class NFBB104501Constant {

    // ** Fixed Value ** //
    /** Commit timing */
    static final int INT_COM_LIM = 1000;

    // ** VND_INV_IMPT_TP_CD ** //
    /** VND_INV_IMPT_TP_CD_01 */
    static final String VND_INV_IMPT_TP_CD_01 = "01";
    
    // QC#23038 T.Hakodate ADD START
    /** VND_INV_IMPT_TP_CD_10 */
    static final String VND_INV_IMPT_TP_CD_10 = "10";
    // QC#23038 T.Hakodate ADD END

    // ** AP_INV_TP_CD ** //
    /** AP_INV_TP_CD_01 */
    static final String AP_INV_TP_CD_01 = "01";
    /** AP_INV_TP_CD_02 */
    static final String AP_INV_TP_CD_02 = "02";

    // ** Message ID ** //
    /** Error Message */
    static final String NFBM0028E = "NFBM0028E";

    // ** SSM Statement ID's Fixed Value ** //
    /** SSM Statement ID */
    static final String SELECT_AP_INVOICE = "SELECT_AP_INVOICE";

    /** SSM Statement ID */
    static final String SELECT_CM_STK_IN = "SELECT_CM_STK_IN";

    /** SSM Statement ID */
    static final String SELECT_CM_AP_INV_DTL = "SELECT_CM_AP_INV_DTL";

    // START 2016/11/04 Y.Tsuchimoto [QC#14562,ADD]
    /** SSM Statement ID */
    static final String SELECT_TARGET_CM_STK_IN = "SELECT_TARGET_CM_STK_IN";
    /** SSM Statement ID */
    static final String SELECT_TARGET_CM_MATCH_TRK = "SELECT_TARGET_CM_MATCH_TRK";
    // END   2016/11/04 Y.Tsuchimoto [QC#14562,ADD]

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String AP_VND_CD = "AP_VND_CD";
    /** DB Item Column Name */
    static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";
    // START 2018/03/30 T.Kikuhara [QC#20316,ADD]
    /** DB Item Column Name */
    static final String AP_VND_INV_LINE_NUM = "AP_VND_INV_LINE_NUM";
    // END 2018/03/30 T.Kikuhara [QC#20316,ADD]
    /** DB Item Column Name */
    static final String AP_VND_INV_SQ_NUM = "AP_VND_INV_SQ_NUM";
    /** DB Item Column Name */
    static final String VND_CD = "VND_CD";
    /** DB Item Column Name */
    static final String VND_INV_NUM = "VND_INV_NUM";
    /** DB Item Column Name */
    static final String VND_INV_SQ_NUM = "VND_INV_SQ_NUM";
    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD";
    /** DB Item Column Name */
    static final String DELY_ORD_NUM = "DELY_ORD_NUM";
    /** DB Item Column Name */
    static final String AP_INV_TP_CD = "AP_INV_TP_CD";
    /** DB Item Column Name */
    static final String PO_NUM = "PO_NUM";
    /** DB Item Column Name */
    static final String RCV_QTY = "RCV_QTY";
    /** DB Item Column Name */
    static final String THIS_MTH_TOT_STD_COST_AMT = "THIS_MTH_TOT_STD_COST_AMT";
    /** DB Item Column Name */
    static final String INV_LOC_RCV_DT = "INV_LOC_RCV_DT";
    /** DB Item Column Name */
    static final String PO_VND_CD = "PO_VND_CD";
    /** DB Item Column Name */
    static final String STK_IN_SC_FOB_COST_AMT = "STK_IN_SC_FOB_COST_AMT";
    /** DB Item Column Name */
    static final String  H_IMPT_INV_CNSGN_CD = "H_IMPT_INV_CNSGN_CD";
    /** DB Item Column Name */
    static final String  D_IMPT_INV_CNSGN_CD = "D_IMPT_INV_CNSGN_CD";
    /** DB Item Column Name */
    static final String INV_DT = "INV_DT";
    /** DB Item Column Name */
    static final String CCY_CD = "CCY_CD";
    /** DB Item Column Name */
    static final String PMT_DUE_DT = "PMT_DUE_DT";
    /** DB Item Column Name */
    static final String VND_PMT_TERM_CD = "VND_PMT_TERM_CD";
    /** DB Item Column Name */
    static final String ACCT_BANK_CD = "ACCT_BANK_CD";
    /** DB Item Column Name */
    static final String ACCT_BANK_ACCT_PAY_TP_CD = "ACCT_BANK_ACCT_PAY_TP_CD";
    /** DB Item Column Name */
    static final String INV_TP_CD = "INV_TP_CD";
    /** DB Item Column Name */
    static final String AP_DISC_RATE = "AP_DISC_RATE";
    /** DB Item Column Name */
    static final String INTFC_ID = "INTFC_ID";
    /** DB Item Column Name */
    static final String COA_ACCT_CD = "COA_ACCT_CD";
    /** DB Item Column Name */
    static final String COA_PROJ_CD = "COA_PROJ_CD";
    /** DB Item Column Name */
    static final String VND_REF_NUM = "VND_REF_NUM";
    /** DB Item Column Name */
    static final String CM_LIC_NUM = "CM_LIC_NUM";
    /** DB Item Column Name */
    static final String INV_QTY = "INV_QTY";
    /** DB Item Column Name */
    static final String APPLY_EXCH_RATE = "APPLY_EXCH_RATE";
    /** DB Item Column Name */
    static final String VND_FOC_TP_CD = "VND_FOC_TP_CD";
    /** DB Item Column Name */
    static final String OC_UNIT_EXT_COST_AMT = "OC_UNIT_EXT_COST_AMT";
    /** DB Item Column Name */
    static final String SC_UNIT_EXT_COST_AMT = "SC_UNIT_EXT_COST_AMT";
    /** DB Item Column Name */
    static final String PO_QTY = "PO_QTY";
    /** DB Item Column Name */
    static final String THIS_MTH_FOB_COST_AMT = "THIS_MTH_FOB_COST_AMT";
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
    // START 2016/11/04 Y.Tsuchimoto [QC#14562,ADD]
    /** DB Item Column Name */
    static final String INV_RCV_QTY = "INV_RCV_QTY";
    // END   2016/11/04 Y.Tsuchimoto [QC#14562,ADD]
}
