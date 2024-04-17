package com.canon.cusa.s21.batch.NFB.NFBB101301;

/**
 * <pre>
 * Copyright (c) 2013 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Invoice Import For WDS for 3way matching
 *  This program is written based on NFBB101001Constant.
 *  
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/14/2016   CSAI            Y.Aikawa        Create          N/A
 * 09/12/2017   CITS            K.Ogino         Update          QC#18093
 * 09/26/2018   Hitachi         Y.Takeno        Update          QC#28099
 * 11/08/2018   Hitachi         Y.Takeno        Update          QC#28982
 * 05/16/2019   Hitachi         Y.Takeno        Update          QC#50204
 * 08/20/2019   Hitachi         Y.Takeno        Update          QC#52280
 * 2019/12/13   Fujitsu         H.Ikeda         Update          QC#55051
 * 2023/01/05   Hitachi         S.Nakatani      Update          QC#60248
 * </pre>
 */
public interface NFBB101301Constant {

    // ** Fixed Value ** //
    /** Commit timing */
    static final int INT_COM_LIM = 10000;

    /** EMPTY_STRING */
    static final String EMPTY_STRING = "";

    /** Max Size of Amount : 15 */
    static final int MAX_SIZE_AMT = 15;

    /** Message IDs */
    static enum MSGID {
        /** Error Message : Unexpected Error Occurred */
        NFBM0028E
        /** Error Message : Calculated amount is too big. */
        , NFBM0164E
        /** Error Message : unique constraint violated. */
        , ZZBM0074E;
    }

    /** Error Message */
    static final String ZZBM0074E = "ZZBM0074E";

    /** Error Message */
    static final String NFBM0028E = "NFBM0028E";

    /** AP_VND_INV_SQ_NUM_00 */
    static final String AP_VND_INV_SQ_NUM_00 = "00";

    /** AP_INV_TP_CD_00 */
    static final String AP_INV_TP_CD_00 = "00";

    /** VND_INV_IMPT_TP_CD_01 */
    static final String VND_INV_IMPT_TP_CD_01 = "01";

    // ** SSM Statement ID's Fixed Value ** //
    /** SSM Statement ID */
    static final String SELECT_RECORD = "SELECT_RECORD";

    /** SSM Statement ID */
    static final String SELECT_DELETE_RECORDS = "SELECT_DELETE_RECORDS";

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String AP_VND_CD = "AP_VND_CD";

    /** DB Item Column Name */
    static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";

    /** DB Item Column Name */
    static final String AP_VND_INV_SQ_NUM = "AP_VND_INV_SQ_NUM";

    /** DB Item Column Name */
    static final String AP_INV_TP_CD = "AP_INV_TP_CD";

    /** DB Item Column Name */
    static final String INV_DT = "INV_DT";

    /** DB Item Column Name */
    static final String CCY_CD = "CCY_CD";

    /** DB Item Column Name */
    static final String PMT_DUE_DT = "PMT_DUE_DT";

    /** DB Item Column Name */
    static final String VND_PMT_TERM_CD = "VND_PMT_TERM_CD";

    /** DB Item Column Name */
    static final String INV_TP_CD = "INV_TP_CD";

    /** DB Item Column Name */
    static final String INV_QTY = "INV_QTY";

    /** DB Item Column Name */
    static final String AC_OC_TOT_FOB_COST_AMT = "AC_OC_TOT_FOB_COST_AMT";

    /** DB Item Column Name */
    static final String AC_OC_TOT_FRT_COST_AMT = "AC_OC_TOT_FRT_COST_AMT";

    /** DB Item Column Name */
    static final String AC_OC_TOT_INS_COST_AMT = "AC_OC_TOT_INS_COST_AMT";

    /** DB Item Column Name */
    static final String AC_OC_TOT_TAX_AMT = "AC_OC_TOT_TAX_AMT";

    /** DB Item Column Name */
    static final String AC_OC_TOT_OTH_COST_AMT = "AC_OC_TOT_OTH_COST_AMT";

    /** DB Item Column Name */
    static final String AC_OC_TOT_GND_COST_AMT = "AC_OC_TOT_GND_COST_AMT";

    // START 2023/01/05 S.Nakatani [QC#60248,ADD]
    /** DB Item Column Name */
    static final String AC_OC_TOT_HDLG_COST_AMT = "AC_OC_TOT_HDLG_COST_AMT";
    // END 2023/01/05 S.Nakatani [QC#60248,ADD]

    /** DB Item Column Name */
    static final String VND_REF_NUM = "VND_REF_NUM";

    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD";

    /** DB Item Column Name */
    static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /** DB Item Column Name */
    static final String DELY_ORD_NUM = "DELY_ORD_NUM";

    /** DB Item Column Name */
    static final String AC_OC_FOB_COST_AMT = "AC_OC_FOB_COST_AMT";

    /** DB Item Column Name */
    static final String AC_OC_OTH_COST_AMT = "AC_OC_OTH_COST_AMT";

    /** DB Item Column Name */
    static final String VND_FOC_TP_CD = "VND_FOC_TP_CD";

    /** DB Item Column Name */
    static final String PO_ORD_NUM = "PO_ORD_NUM";

    /** DB Item Column Name */
    static final String AP_VND_INV_LINE_NUM = "AP_VND_INV_LINE_NUM";

    /** DB Item Column Name */
    static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** DB Item Column Name */
    static final String VND_INV_PROC_STS_CD = "VND_INV_PROC_STS_CD";

    /** DB Item Column Name */
    static final String GLBL_CMPY_CD_J = "glblCmpyCd";

    /** DB Item Column Name */
    static final String AP_VND_CD_J = "apVndCd";

    /** DB Item Column Name */
    static final String AP_VND_INV_NUM_J = "apVndInvNum";

    /** DB Item Column Name */
    static final String AP_VND_INV_SQ_NUM_J = "apVndInvSqNum";

    /** DB Item Column Name */
    static final String AP_INV_TP_CD_J = "apInvTpCd";

    /** Varchar Const(NFBB101301_CR_DR_RSN_CD) */
    static final String VARCHAR_CONST_NFBB101301_CR_DR_RSN_CD = "NFBB101301_CR_DR_RSN_CD";

    /** PO_QTY */
    static final String PO_QTY = "PO_QTY";

    /** THIS_MTH_FOB_COST_AMT */
    static final String THIS_MTH_FOB_COST_AMT = "THIS_MTH_FOB_COST_AMT";

    /** ENT_DEAL_NET_UNIT_PRC_AMT */
    static final String ENT_DEAL_NET_UNIT_PRC_AMT = "ENT_DEAL_NET_UNIT_PRC_AMT";

    /** ENT_PO_DTL_DEAL_NET_AMT */
    static final String ENT_PO_DTL_DEAL_NET_AMT = "ENT_PO_DTL_DEAL_NET_AMT";

    /** ENT_FUNC_NET_UNIT_PRC_AMT */
    static final String ENT_FUNC_NET_UNIT_PRC_AMT = "ENT_FUNC_NET_UNIT_PRC_AMT";

    /** ENT_PO_DTL_FUNC_NET_AMT */
    static final String ENT_PO_DTL_FUNC_NET_AMT = "ENT_PO_DTL_FUNC_NET_AMT";

    /** EXCH_RATE */
    static final String EXCH_RATE = "EXCH_RATE";

    // START 2018/09/26 [QC#28099, ADD]
    /** SPLY_PMT_FLG */
    static final String SPLY_PMT_FLG = "SPLY_PMT_FLG";

    /** ALT_AP_VND_CD */
    static final String ALT_VND_CD = "ALT_VND_CD";
    // END   2018/09/26 [QC#28099, ADD]

    // START 2018/11/08 [QC#28758, ADD]
    /** DEAL_NET_UNIT_PRC_AMT */
    static final String DEAL_NET_UNIT_PRC_AMT = "DEAL_NET_UNIT_PRC_AMT";
    // END   2018/11/08 [QC#28758, ADD]

    // START 2019/05/16 [QC#50204, ADD]
    /** Interface ID: USDA0300 */
    public static final String INTERFACE_ID_CUSA_PARTS = "USDA0300";
    // END   2019/05/16 [QC#50204, ADD]

    // START 2019/08/20 [QC#52280, ADD]
    /** ITRL_INTFC_ID */
    public static final String ITRL_INTFC_ID = "ITRL_INTFC_ID";

    /** Interface ID: AWCA0010 */
    public static final String INTERFACE_ID_CUSA_WS = "AWCA0010";
    // END   2019/08/20 [QC#52280, ADD]

    // START 2019/12/13 [QC#55051, ADD]
    /** SSM Statement ID */
    static final String SELECT_ERR_RECORD = "SELECT_ERR_RECORD";

    /** VND_INV_NUM */
    static final String VND_INV_NUM = "VND_INV_NUM";

    /** NFBM0175E Actual invoice record already exists. [ @ ] */
    static final String MSG_ID_NFBM0175E = "NFBM0175E";

    // END   2018/12/13 [QC#55051, ADD]
}
