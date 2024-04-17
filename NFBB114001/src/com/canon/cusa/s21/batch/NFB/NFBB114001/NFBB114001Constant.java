/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB114001;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Invoice Data Creation for AP Invoice Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/11/2016   CUSA            Y.Aikawa        Create          N/A
 * 11/16/2016   Hitachi         K.Kasai         Update          QC#15927
 * 11/17/2016   Hitachi         Y.Tsuchimoto    Update          QC#15966
 * 12/15/2016   Hitachi         T.Tsuchida      Update          QC#16673
 * 07/06/2017   CITS            K.Ogino         Update          QC#19629
 * 2017/12/26   Hitachi         J.Kim           Update          QC#22458
 * 2018/03/28   CITS            T.Kikuhara      Update          QC#20316
 * </pre>
 */

public interface NFBB114001Constant {
    // ** Fixed Value ** //
    // ** Common ** //
    /** Bulk Commit Limit */
    static final int INT_BULK_COM_LIM = 10000;

    /** yyyyMMdd */
    static final String YYYYMMDD = "yyyyMMdd";

    /** Empty String */
    static final String EMPTY_STRING = "";

    // ** Message ID ** //
    /** Error Message */
    static final String NFBM0028E = "NFBM0028E";

    /** Error Message (Unique Constraint Violation) */
    static final String ZZBM0074E = "ZZBM0074E";

    // START 2017/12/26 J.Kim [QC#22458,DEL]
    // // ** AP_MAINT_INV_STS_CD ** //
    // /** AP_MAINT_INV_STS_CD (Approved) */
    // static final String AP_MAINT_INV_STS_CD_APPROVED = "20";

    // /** AP_MAINT_INV_STS_CD (Linked To ARCS) */
    // static final String AP_MAINT_INV_STS_CD_LINKED_TO_ARCS = "25";
    // END 2017/12/26 J.Kim [QC#22458,DEL]

    // ** CM_DEF_ACCT_CD ** //
    /** CM_DEF_ACCT_CD (MAINT) */
    static final String CM_DEF_ACCT_CD_MAINT = "MAINT";

    // START 2017/12/19 [QC#23022, ADD]
    /** INV_HDR_DESC_TXT_HDR_TXT */
    public static final String INV_HDR_DESC_TXT_HDR_TXT = "Maintenance Invoice Batch#";
    // END   2017/12/19 [QC#23022, ADD]

    // ** Varchar Const ** //
    /** Varchar Const(NFBB114001_ACCT_INV_STS_CD) */
    public static final String VARCHAR_CONST_NFBB114001_ACCT_INV_STS_CD = "NFBB114001_ACCT_INV_STS_CD";

    /** Varchar Const(NFBB114001_AP_INV_CATG_CD) */
    public static final String VARCHAR_CONST_NFBB114001_AP_INV_CATG_CD = "NFBB114001_AP_INV_CATG_CD";

    // ** SSM Statement ID's Fixed Value ** //
    /** SSM Statement ID */
    static final String SELECT_RECORD = "SELECT_RECORD";

    /** SSM Statement ID */
    static final String SELECT_FIN_BR_CD = "SELECT_FIN_BR_CD";

    /** SSM Statemend ID */
    static final String SELECT_CONTR_INFO = "SELECT_CONTR_INFO";

    /** SSM Statement ID */
    static final String SELECT_RECORD_CM_AP_INV = "SELECT_RECORD_CM_AP_INV";

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String AP_VND_CD = "AP_VND_CD";

    /** DB Item Column Name */
    static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";

    /** DB Item Column Name */
    static final String AP_VND_INV_SQ_NUM = "AP_VND_INV_SQ_NUM";

    /** DB Item Column Name */
    static final String LOC_NUM = "LOC_NUM";

    /** DB Item Column Name */
    static final String CCY_CD = "CCY_CD";

    /** DB Item Column Name */
    static final String INV_DT = "INV_DT";

    /** DB Item Column Name */
    static final String PO_NUM = "PO_NUM";

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
    static final String PMT_HLD_FLG = "PMT_HLD_FLG";

    /** DB Item Column Name */
    static final String PMT_HLD_CD = "PMT_HLD_CD";

    /** DB Item Column Name */
    static final String PMT_HLD_DT = "PMT_HLD_DT";

    /** DB Item Column Name */
    static final String PMT_HLD_RSN_CD = "PMT_HLD_RSN_CD";

    /** DB Item Column Name */
    static final String PMT_HLD_PSN_CD = "PMT_HLD_PSN_CD";

    /** DB Item Column Name */
    static final String PMT_HLD_REL_PSN_CD = "PMT_HLD_REL_PSN_CD";

    /** DB Item Column Name */
    static final String PMT_HLD_REL_DT = "PMT_HLD_REL_DT";

    /** DB Item Column Name */
    static final String PMT_HLD_REL_RSN_CD = "PMT_HLD_REL_RSN_CD";

    /** DB Item Column Name */
    static final String PMT_HLD_REL_CMNT_TXT = "PMT_HLD_REL_CMNT_TXT";

    /** DB Item Column Name */
    static final String AP_INV_AMT = "AP_INV_AMT";

    /** DB Item Column Name */
    static final String AP_ADJ_AMT = "AP_ADJ_AMT";

    /** DB Item Column Name */
    static final String AP_INV_SRC_NM = "AP_INV_SRC_NM";

    /** DB Item Column Name */
    static final String AP_INV_DESC_TXT = "AP_INV_DESC_TXT";

    /** DB Item Column Name */
    static final String PAY_ALONE_FLG = "PAY_ALONE_FLG";

    /** DB Item Column Name */
    static final String INV_MATCH_TP_CD = "INV_MATCH_TP_CD";

    /** DB Item Column Name */
    static final String INV_PMT_NUM = "INV_PMT_NUM";

    /** DB Item Column Name */
    static final String INV_HDR_DESC_TXT = "INV_HDR_DESC_TXT";

    /** DB Item Column Name */
    static final String ACCT_INV_STS_CD = "ACCT_INV_STS_CD";

    /** DB Item Column Name */
    static final String SPLY_COA_CMPY_CD = "SPLY_COA_CMPY_CD";

    /** DB Item Column Name */
    static final String SPLY_COA_BR_CD = "SPLY_COA_BR_CD";

    /** DB Item Column Name */
    static final String SPLY_COA_CC_CD = "SPLY_COA_CC_CD";

    /** DB Item Column Name */
    static final String SPLY_COA_ACCT_CD = "SPLY_COA_ACCT_CD";

    /** DB Item Column Name */
    static final String SPLY_COA_PROD_CD = "SPLY_COA_PROD_CD";

    /** DB Item Column Name */
    static final String SPLY_COA_CH_CD = "SPLY_COA_CH_CD";

    /** DB Item Column Name */
    static final String SPLY_COA_AFFL_CD = "SPLY_COA_AFFL_CD";

    /** DB Item Column Name */
    static final String SPLY_COA_PROJ_CD = "SPLY_COA_PROJ_CD";

    /** DB Item Column Name */
    static final String SPLY_COA_EXTN_CD = "SPLY_COA_EXTN_CD";

    /** DB Item Column Name */
    static final String AP_PMT_CHK_NUM = "AP_PMT_CHK_NUM";

    /** DB Item Column Name */
    static final String IF_PROC_STS_CD = "IF_PROC_STS_CD";

    /** DB Item Column Name */
    static final String AP_INV_CATG_CD = "AP_INV_CATG_CD";

    /** DB Item Column Name */
    static final String INV_AUTH_DT = "INV_AUTH_DT";

    /** DB Item Column Name */
    static final String PRNT_VND_CD = "PRNT_VND_CD";

    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD";

    /** DB Item Column Name */
    static final String AP_INV_TP_CD = "AP_INV_TP_CD";

    /** DB Item Column Name */
    static final String DELY_ORD_NUM = "DELY_ORD_NUM";

    /** DB Item Column Name */
    static final String INV_QTY = "INV_QTY";

    /** DB Item Column Name */
    static final String PO_QTY = "PO_QTY";

    /** DB Item Column Name */
    static final String INV_RCV_QTY = "INV_RCV_QTY";

    /** DB Item Column Name */
    static final String AP_BILL_QTY = "AP_BILL_QTY";

    /** DB Item Column Name */
    static final String AP_REJ_QTY = "AP_REJ_QTY";

    /** DB Item Column Name */
    static final String UOM_CD = "UOM_CD";

    /** DB Item Column Name */
    static final String THIS_MTH_FOB_COST_AMT = "THIS_MTH_FOB_COST_AMT";

    /** DB Item Column Name */
    static final String AC_INV_TAX_AMT = "AC_INV_TAX_AMT";

    /** DB Item Column Name */
    static final String AC_INV_JRNL_COST_AMT = "AC_INV_JRNL_COST_AMT";

    /** DB Item Column Name */
    static final String INV_ASG_DT = "INV_ASG_DT";

    /** DB Item Column Name */
    static final String STK_IN_DT = "STK_IN_DT";

    /** DB Item Column Name */
    static final String CSMP_INV_NUM = "CSMP_INV_NUM";

    /** DB Item Column Name */
    static final String SER_NUM = "SER_NUM";

    /** DB Item Column Name */
    static final String CONTR_NUM = "CONTR_NUM";

    /** DB Item Column Name */
    static final String CR_TERM_CD = "CR_TERM_CD";

    /** DB Item Column Name */
    static final String CUST_DLR_CD = "CUST_DLR_CD";

    /** DB Item Column Name */
    static final String ISTL_LOC_FIRST_LINE_ADDR = "ISTL_LOC_FIRST_LINE_ADDR";

    /** DB Item Column Name */
    static final String ISTL_LOC_FL_NM = "ISTL_LOC_FL_NM";

    /** DB Item Column Name */
    static final String ISTL_LOC_CTY_ADDR = "ISTL_LOC_CTY_ADDR";

    /** DB Item Column Name */
    static final String ISTL_LOC_ST_CD = "ISTL_LOC_ST_CD";

    /** DB Item Column Name */
    static final String ISTL_LOC_POST_CD = "ISTL_LOC_POST_CD";

    /** DB Item Column Name */
    static final String INV_LINE_DESC_TXT = "INV_LINE_DESC_TXT";

    /** DB Item Column Name */
    static final String CM_COA_CMPY_CD = "CM_COA_CMPY_CD";

    /** DB Item Column Name */
    static final String CM_COA_BR_CD = "CM_COA_BR_CD";

    /** DB Item Column Name */
    static final String CM_COA_CC_CD = "CM_COA_CC_CD";

    /** DB Item Column Name */
    static final String CM_COA_ACCT_CD = "CM_COA_ACCT_CD";

    /** DB Item Column Name */
    static final String CM_COA_PROD_CD = "CM_COA_PROD_CD";

    /** DB Item Column Name */
    static final String CM_COA_CH_CD = "CM_COA_CH_CD";

    /** DB Item Column Name */
    static final String CM_COA_AFFL_CD = "CM_COA_AFFL_CD";

    /** DB Item Column Name */
    static final String CM_COA_PROJ_CD = "CM_COA_PROJ_CD";

    /** DB Item Column Name */
    static final String CM_COA_EXTN_CD = "CM_COA_EXTN_CD";

    /** DB Item Column Name */
    static final String AP_ACCT_DESC_TXT = "AP_ACCT_DESC_TXT";

    /** DB Item Column Name */
    static final String AP_INV_AUTH_FLG = "AP_INV_AUTH_FLG";

    /** DB Item Column Name */
    static final String AP_JRNL_CPLT_FLG = "AP_JRNL_CPLT_FLG";

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
    static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** DB Item Column Name */
    static final String FIN_BR_CD = "FIN_BR_CD";

    /** DB Item Column Name */
    static final String CM_INV_ACCT_DIST_LINE_NUM = "CM_INV_ACCT_DIST_LINE_NUM";

    // START 2018/03/28 [QC#20316, ADD]
    /** DB Item Column Name */
    static final String AP_VND_INV_LINE_NUM = "AP_VND_INV_LINE_NUM";
    // END 2018/03/28 [QC#20316, ADD]

    // VAR_CHAR_CONST
    /** VAR_CHAR_CONST : AP_LINE_TP_ITEM */
    static final String VARCHAR_CONST_AP_LINE_TP_ITEM = "AP_LINE_TP_ITEM";

    /** DUMMY_MDSE : NONE */
    static final String DUMMY_MDSE = "NONE";

    /** NONE MDSE_CD format **/
    static final String NONE_MDSE_CD_FORMAT = "%04d";

    /** DB Item Column Name */
    static final String START_DT = "START_DT";

    /** DB Item Column Name */
    static final String END_DT = "END_DT";

    /** DB Item Column Name */
    static final String DS_CONTR_NUM = "DS_CONTR_NUM";

    /** DB Item Column Name */
    static final String VND_CD = "VND_CD";

    // START 2017/12/19 [QC#23022, ADD]
    /** DB Item Column Name */
    public static final String MDSE_NM = "MDSE_NM";
    // END   2017/12/19 [QC#23022, ADD]

}
