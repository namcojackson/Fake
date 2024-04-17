/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/08/2016   CSAI            Y.Miyauchi      Create          New
 * 09/28/2016   Hitachi         K.Kasai         Update          QC#14130
 * 2018/03/29   CITS            T.Kikuhara      Update          QC#20316
 * </pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB116001;

public interface NFBB116001Constant {

    /** DB Item Column Name */
	static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
	/** DB Item Column Name */
    static final String VND_CD = "VND_CD";
    /** DB Item Column Name */
    static final String AP_VND_CD = "AP_VND_CD";
    /** DB Item Column Name */
    static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";
    // START 2018/03/29 [QC#20316, ADD]
    /** DB Item Column Name */
    static final String AP_VND_INV_LINE_NUM = "AP_VND_INV_LINE_NUM";
    // END 2018/03/29 [QC#20316, ADD]
    /** DB Item Column Name */
    static final String AP_VND_INV_SQ_NUM = "AP_VND_INV_SQ_NUM";
    /** DB Item Column Name */
    static final String PO_NUM = "PO_NUM";
    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD";
    /** DB Item Column Name */
    static final String DELY_ORD_NUM = "DELY_ORD_NUM";
    /** DB Item Column Name */
    static final String PO_QTY = "PO_QTY";
    /** DB Item Column Name */
    static final String RCV_QTY = "RCV_QTY";
    /** DB Item Column Name */
    static final String THIS_MTH_FOB_COST_AMT = "THIS_MTH_FOB_COST_AMT";
    /** DB Item Column Name */
    static final String INV_RCV_DT = "INV_RCV_DT";
    /** DB Item Column Name */
    static final String ACRL_COA_ACCT_CD = "ACRL_COA_ACCT_CD";
    /** DB Item Column Name */
    static final String CM_COA_CMPY_CD = "CM_COA_CMPY_CD";
    /** DB Item Column Name */
    static final String CM_COA_BR_CD = "CM_COA_BR_CD";
    /** DB Item Column Name */
    static final String CM_COA_CC_CD = "CM_COA_CC_CD";
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
    static final String LOC_NUM = "LOC_NUM";
    /** DB Item Column Name */
    static final String INV_DT = "INV_DT";
    /** DB Item Column Name */
    static final String INV_QTY = "INV_QTY";
    /** DB Item Column Name */
    static final String INV_RCV_QTY = "INV_RCV_QTY";
    /** DB Item Column Name */
    static final String AC_INV_JRNL_COST_AMT = "AC_INV_JRNL_COST_AMT";
    /** DB Item Column Name */
    static final String COA_CMPY_CD = "COA_CMPY_CD";
    /** DB Item Column Name */
    static final String COA_BR_CD = "COA_BR_CD";
    /** DB Item Column Name */
    static final String COA_CC_CD = "COA_CC_CD";
    /** DB Item Column Name */
    static final String COA_ACCT_CD = "COA_ACCT_CD";
    /** DB Item Column Name */
    static final String COA_PROD_CD = "COA_PROD_CD";
    /** DB Item Column Name */
    static final String COA_CH_CD = "COA_CH_CD";
    /** DB Item Column Name */
    static final String COA_AFFL_CD = "COA_AFFL_CD";
    /** DB Item Column Name */
    static final String COA_PROJ_CD = "COA_PROJ_CD";
    /** DB Item Column Name */
    static final String COA_EXTN_CD = "COA_EXTN_CD";
    /** DB Item Column Name */
    static final String STK_IN_DT = "STK_IN_DT";
    /** DB Item Column Name */
    static final String ACCT_INV_STS_CD = "ACCT_INV_STS_CD";
    // START 2016/09/28 [QC#14130, ADD]
    /** DB Item Column Name */
    static final String ENT_DEAL_NET_UNIT_PRC_AMT = "ENT_DEAL_NET_UNIT_PRC_AMT";
    // END 2016/09/28 [QC#14130, ADD]
    // START 2018/11/13 J.Kim [QC#23037, ADD]
    static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";
    // END 2018/11/13 J.Kim [QC#23037, ADD]

    /** Map Argument Name */
    static final String GLBL_CMPY_CD_ARG = "glblCmpyCd";
    /** Map Argument Name */
    static final String MDSE_CD_ARG = "mdseCd";
    /** Map Argument Name */
    static final String ACCT_INV_STS_CD_ARG = "acctInvStsCd";
    /** Map Argument Name */
    static final String AP_INV_CATG_CD_ARG = "apInvCatgCd";
    /** Map Argument Name */
    static final String LIST_AP_INV_CATG_CD_ARG = "listApInvCatgCd";
    /** Map Argument Name */
    static final String LIST_EXCLUDE_MDSE_CD_ARG = "listExcludeMdseCd";

    /** Var Char Constant Key for Default Account Code of Accrual */
    static final String DEFAULT_ACCOUNT_CODE_ACRL_KEY = "DEFAULT_ACRL_ACCOUNT_CODE_KEY";
    /** Var Char Constant Key for AP_INV_CATG_CD */
    static final String NFBB1160_AP_INV_CATG_CD = "NFBB1160_AP_INV_CATG_CD";
    /** Var Char Constant Key for exclude merchandise code */
    static final String NFBB1160_EXCLUDE_MDSE_CD = "NFBB1160_EXCLUDE_MDSE_CD";

    /** Constant Credit Key for DR_CR_TP_CD */
    static final String DR_CR_TP_CD_CREDIT = "C";
    /** Constant Debit Key for DR_CR_TP_CD */
    static final String DR_CR_TP_CD_DEBIT = "D";
    /** Account Invoice Status Code for Authorize */
    static final String ACCT_INV_STS_CD_AUTH = "20";
    /** Map Argument Name */
    static final String AP_INV_CATG_CD_MDSE = "01";
    
    /** Constant Key */
    static final String FLG_KEY_YES = "Y";
    static final String FLG_KEY_NO = "N";

    /** Error Message : Unexpected Error Occurred */
    static final String NFBM0028E = "NFBM0028E";
    /** Error Message */
    static final String ZZBM0074E = "ZZBM0074E";

    /** MDSE */
    static final String ARG_MDSE = "MDSE";

    // Oracle Sequence Name
    /** CM_ACRL_WRITE_OFF_SQ */
    static final String CM_ACRL_WRITE_OFF_SQ = "CM_ACRL_WRITE_OFF_SQ";
}