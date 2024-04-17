/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB118001;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Create AP Distribution
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/26/2016   CUSA            Y.Aikawa        Create          N/A
 * 09/08/2016   Hitachi         K.Kasai         Update          QC#14161
 * 09/29/2016   Hitachi         K.Kasai         Update          QC#14799
 * 09/30/2016   Hitachi         K.Kasai         Update          QC#14797
 * 10/19/2016   Hitachi         Y.Tsuchimoto    Update          QC#14810
 * 11/10/2016   Hitachi         E.Kameishi      Update          QC#14800
 * 11/15/2016   Hitachi         T.Tsuchida      Update          QC#15982
 * 01/24/2017   Hitachi         E.Kameishi      Update          QC#17090
 * 07/26/2017   CITS            S.Katsuma       Update          QC#19335
 * 09/12/2017   CITS            K.Ogino         Update          QC#18093
 * 12/13/2017   CITS            T.Hakodate      Update          QC#23038
 * 04/02/2018   Hitachi         Y.Takeno        Update          QC#20316
 * 04/02/2018   Hitachi         Y.Takeno        Update          QC#24277
 * 05/16/2018   Hitachi         Y.Takeno        Update          QC#26080
 * 07/30/2018   Hitachi         Y.Takeno        Update          QC#27025-1
 * 10/18/2018   Hitachi         Y.Takeno        Update          QC#28734
 * 11/08/2018   Hitachi         Y.Takeno        Update          QC#28982
 * 11/15/2018   Hitachi         Y.Takeno        Update          QC#28982
 * 12/20/2018   Hitachi         Y.Takeno        Update          QC#29679
 * 08/29/2019   Hitachi         Y.Takeno        Update          QC#52280
 * 10/25/2019   Hitachi         Y.Takeno        Update          QC#54275
 * 11/11/2019   Hitachi         Y.Takeno        Update          QC#54275-1
 * 06/09/2021   CITS            H.Dimay         Update          QC#58883
 * 2023/01/05   Hitachi         S.Nakatani      Update          QC#60248
 * </pre>
 */
public interface NFBB118001Constant {
    // ** Fixed Value ** //
    // ** Common ** //
    /** Empty String */
    static final String EMPTY_STRING = "";
    /** Bulk Commit Limit */
    static final int INT_BULK_COM_LIM = 10000;
    /** NONE */
    static final String NONE = "NONE";
    /** FRT */
    static final String FREIGHT = "FREIGHT";
    /** TAX */
    static final String TAX = "TAX";
    // START 2023/01/05 S.Nakatani [QC#60248,ADD]
    static final String NONE0001 = "NONE0001";
    // END 2023/01/05 S.Nakatani [QC#60248,ADD]
    /** VAR */
    static final String VAR = "VAR";
    /** AP_INV_TP_CD 00 */
    static final String AP_INV_TP_CD_00 = "00";
    /** PSN_CD NFBB1180 */
    static final String PSN_CD_NFBB1180 = "NFBB1180";
    /** yyyyMMdd */
    static final String YYYYMMDD = "yyyyMMdd";
    /** yyyyMMddHHmmSSsss */
    static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    // START 2017/01/23 E.Kameishi [QC#17090,ADD]
    /** COA_EXTN_CD 10 */
    static final String VAR_COA_EXTN_CD_010 = "010";
    // END 2017/01/23 E.Kameishi [QC#17090,ADD]

    // ** Varchar Const ** //
    /** VAR_CHAR_CONST : ACCT_INV_STS_CD_ENTERED */
    public static final String VARCHAR_CONST_ACCT_INV_STS_CD_ENTERED = "ACCT_INV_STS_CD_ENTERED";
    /** VAR_CHAR_CONST : ACCT_INV_STS_CD_AUTHORIZED */
    public static final String VARCHAR_CONST_ACCT_INV_STS_CD_AUTHORIZED = "ACCT_INV_STS_CD_AUTHORIZED";
    /** VAR_CHAR_CONST : AP_LINE_TP_ITEM */
    public static final String VARCHAR_CONST_AP_LINE_TP_ITEM = "AP_LINE_TP_ITEM";
    /** VAR_CHAR_CONST : AP_LINE_TP_TAX */
    public static final String VARCHAR_CONST_AP_LINE_TP_TAX = "AP_LINE_TP_TAX";
    /** VAR_CHAR_CONST : AP_LINE_TP_FREIGHT */
    public static final String VARCHAR_CONST_AP_LINE_TP_FREIGHT = "AP_LINE_TP_FREIGHT";
    /** VAR_CHAR_CONST : AP_LINE_TP_VARIANCE */
    public static final String VARCHAR_CONST_AP_LINE_TP_VARIANCE = "AP_LINE_TP_VARIANCE";
    // START 2016/10/19 Y.Tsuchimoto [QC#14810,ADD]
    /** VAR_CHAR_CONST : AUTO_PMT_HLD_REL_RSN_CD */
    public static final String VARCHAR_CONST_AUTO_PMT_HLD_REL_RSN_CD = "AUTO_PMT_HLD_REL_RSN_CD";
    // END   2016/10/19 Y.Tsuchimoto [QC#14810,ADD]
    // START 2016/11/10 E.Kameishi [QC#14800, ADD]
    /** VAR_CHAR_CONST : AP_VND_CD */
    public static final String VARCHAR_CONST_COA_FROM_WH_VND_CD = "COA_FROM_WH_VND_CD";
    // END 2016/11/10 E.Kameishi [QC#14800, ADD]
    
    // START 2017 12/13 T.Hakodate [QC#23038, ADD]
    static final String VARCHAR_CONST_AUTO_HLD_RELEASE_CATG_CD = "AUTO_HLD_RELEASE_CATG_CD";
    // END  2017 12/13 T.Hakodate [QC#23038, ADD]
    
    // ** Message ID ** //
    /** Error Message */
    static final String NFBM0028E = "NFBM0028E";
    /** Error Message (Specified PO Information is invalid or does not exist.) */
    static final String NPZM0021E = "NPZM0021E";
    /** Error Message (Unique Constraint Violation) */
    static final String ZZBM0074E = "ZZBM0074E";
    // START 2016/10/19 Y.Tsuchimoto [QC#14810,ADD]
    /** Error Message ("@" doesn't exist in the VAR_CHAR_CONST.) */
    public static final String NFBM0268E = "NFBM0268E";
    // END   2016/10/19 Y.Tsuchimoto [QC#14810,ADD]

    // ** SSM Statement ID's Fixed Value ** //
    /** SSM Statement ID */
    static final String SELECT_RECORD = "SELECT_RECORD";
    /** SSM Statement ID */
    static final String SELECT_CM_INV_PMT_HLD_BY_PARTIAL_KEY = "SELECT_CM_INV_PMT_HLD_BY_PARTIAL_KEY";
    /** SSM Statement ID */
    static final String SELECT_CM_AP_INV_DTL_BY_PARTIAL_KEY = "SELECT_CM_AP_INV_DTL_BY_PARTIAL_KEY";

    // START 2016/10/19 Y.Tsuchimoto [QC#14810,ADD]
    /** SSM Statement ID */
    public static final String SELECT_HOLD_RECORD = "SELECT_HOLD_RECORD";
    // END   2016/10/19 Y.Tsuchimoto [QC#14810,ADD]
    // START 2016/11/10 E.Kameishi [QC#14800, ADD]
    /** SSM Statement ID */
    static final String SELECT_COA_CONST_BY_SHIP_TO_CUST = "SELECT_COA_CONST_BY_SHIP_TO_CUST";
    // END 2016/11/10 E.Kameishi [QC#14800, ADD]
    // START 2016/11/15 T.Tsuchida [QC#15982,ADD]
    static final String SELECT_POUPDATE_RECORD = "SELECT_POUPDATE_RECORD";
    // END 2016/11/15 T.Tsuchida [QC#15982,ADD]

    // START 2017/01/23 E.Kameishi [QC#17090,ADD]
    static final String SELECT_COA_CD_FOR_VAR = "SELECT_COA_CD_FOR_VAR";
    // END 2017/01/23 E.Kameishi [QC#17090,ADD]

    // START 2018/11/08 [QC#28982, ADD]
    static final String SELECT_PO_VARIANCE_UPDATE_RECORD = "SELECT_PO_VARIANCE_UPDATE_RECORD";
    // END   2018/11/08 [QC#28982, ADD]

    // ** CM_DEF_ACCT_CD ** //
    /** CM_DEF_ACCT_CD FREIGHT */
    static final String CM_DEF_ACCT_CD_FREIGHT = "FREIGHT";
    // START 2023/01/05 S.Nakatani [QC#60248,ADD]
    /** CM_DEF_ACCT_CD HDLG */
    static final String CM_DEF_ACCT_CD_HDLG = "HDLG";
    // END 2023/01/05 S.Nakatani [QC#60248,ADD];
    /** CM_DEF_ACCT_CD TAX */
    static final String CM_DEF_ACCT_CD_TAX = "TAX";
    /** CM_DEF_ACCT_CD VAR */
    static final String CM_DEF_ACCT_CD_VAR = "VAR";

    // PMT_HLD_CD
    /** PMT_HLD_CD 000 (Success) */
    public static final String PMT_HLD_CD_000 = "000";
    /** PMT_HLD_CD 010 (Quantity) */
    public static final String PMT_HLD_CD_010 = "010";
    /** PMT_HLD_CD 020 (Amount) */
    public static final String PMT_HLD_CD_020 = "020";

    // PMT_HLD_RSN_CD
    /** PMT_HLD_RSN_CD 000 (Success) */
    public static final String PMT_HLD_RSN_CD_000 = "000";
    /** PMT_HLD_RSN_CD 011 (Quantity Not Match (PO : Received)) */
    public static final String PMT_HLD_RSN_CD_011 = "011";
    /** PMT_HLD_RSN_CD 012 (Quantity Not Match (PO : Invoice)) */
    public static final String PMT_HLD_RSN_CD_012 = "012";
    /** PMT_HLD_RSN_CD 013 (Quantity Not Match (Invoice : Received)) */
    public static final String PMT_HLD_RSN_CD_013 = "013";
    /** PMT_HLD_RSN_CD 021 (Amount (PO : Invoice)) */
    public static final String PMT_HLD_RSN_CD_021 = "021";
    
    // PMT_HLD_REL_RSN_CD
    /** PMT_HLD_REL_RSN_CD 000 (Success) */
    public static final String PMT_HLD_REL_RSN_CD_000 = "000";
    /** PMT_HLD_REL_RSN_CD 010 (Invoice Hold Release) */
    public static final String PMT_HLD_REL_RSN_CD_010 = "010";

    // PO_MATCH_TP_CD
    /** PO_MATCH_TP_CD Receipt */
    public static final String PO_MATCH_TP_CD_R = "R"; 

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String AP_VND_CD = "AP_VND_CD";
    /** DB Item Column Name */
    static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";
    /** DB Item Column Name */
    static final String VND_INV_NUM = "VND_INV_NUM";
    /** DB Item Column Name */
    static final String AP_VND_INV_SQ_NUM = "AP_VND_INV_SQ_NUM";
    /** DB Item Column Name */
    static final String LOC_NUM = "LOC_NUM";
    /** DB Item Column Name */
    static final String INV_DT = "INV_DT";
    /** DB Item Column Name */
    static final String CAIH_PO_NUM = "CAIH_PO_NUM";
    /** DB Item Column Name */
    static final String CAID_PO_NUM = "CAID_PO_NUM";
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
    static final String AC_SC_TOT_GND_INV_AMT = "AC_SC_TOT_GND_INV_AMT";
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
    static final String PRNT_VND_CD = "PRNT_VND_CD";
    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD";
    /** DB Item Column Name */
    static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";
    /** DB Item Column Name */
    static final String AP_INV_TP_CD = "AP_INV_TP_CD";
    /** DB Item Column Name */
    static final String DELY_ORD_NUM = "DELY_ORD_NUM";
    /** DB Item Column Name */
    static final String INV_QTY = "INV_QTY";
    // START 2017/07/26 S.Katsuma [QC#19335,ADD]
    /** DB Item Column Name */
    static final String SUM_INV_QTY = "SUM_INV_QTY";
    // END 2017/07/26 S.Katsuma [QC#19335,ADD]
    /** DB Item Column Name */
    static final String PO_QTY = "PO_QTY";
    /** DB Item Column Name */
    static final String INV_RCV_QTY = "INV_RCV_QTY";
    // START 2017/07/26 S.Katsuma [QC#19335,ADD]
    /** DB Item Column Name */
    static final String SUM_INV_RCV_QTY = "SUM_INV_RCV_QTY";
    // END 2017/07/26 S.Katsuma [QC#19335,ADD]
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
    static final String AC_SC_TOT_FOB_COST_AMT = "AC_SC_TOT_FOB_COST_AMT";
    /** DB Item Column Name */
    static final String AC_SC_TOT_FRT_COST_AMT = "AC_SC_TOT_FRT_COST_AMT";
    /** DB Item Column Name */
    static final String AC_SC_TOT_TAX_AMT = "AC_SC_TOT_TAX_AMT";
    // START 2023/01/05 S.Nakatani [QC#60248,ADD]
    static final String AC_SC_TOT_HDLG_COST_AMT = "AC_SC_TOT_HDLG_COST_AMT";
    // END 2023/01/05 S.Nakatani [QC#60248,ADD]
    /** DB Item Column Name */
    static final String AC_INV_JRNL_COST_AMT = "AC_INV_JRNL_COST_AMT";
    /** DB Item Column Name */
    static final String PMT_HLD_FLG = "PMT_HLD_FLG";
    /** DB Item Column Name */
    static final String FRT_COA_CMPY_CD = "FRT_COA_CMPY_CD";
    /** DB Item Column Name */
    static final String FRT_COA_BR_CD = "FRT_COA_BR_CD";
    /** DB Item Column Name */
    static final String FRT_COA_CC_CD = "FRT_COA_CC_CD";
    /** DB Item Column Name */
    static final String FRT_COA_ACCT_CD = "FRT_COA_ACCT_CD";
    /** DB Item Column Name */
    static final String FRT_COA_PROD_CD = "FRT_COA_PROD_CD";
    /** DB Item Column Name */
    static final String FRT_COA_CH_CD = "FRT_COA_CH_CD";
    /** DB Item Column Name */
    static final String FRT_COA_AFFL_CD = "FRT_COA_AFFL_CD";
    /** DB Item Column Name */
    static final String FRT_COA_PROJ_CD = "FRT_COA_PROJ_CD";
    /** DB Item Column Name */
    static final String FRT_COA_EXTN_CD = "FRT_COA_EXTN_CD";
    /** DB Item Column Name */
    static final String TAX_COA_CMPY_CD = "TAX_COA_CMPY_CD";
    /** DB Item Column Name */
    static final String TAX_COA_BR_CD = "TAX_COA_BR_CD";
    /** DB Item Column Name */
    static final String TAX_COA_CC_CD = "TAX_COA_CC_CD";
    /** DB Item Column Name */
    static final String TAX_COA_ACCT_CD = "TAX_COA_ACCT_CD";
    /** DB Item Column Name */
    static final String TAX_COA_PROD_CD = "TAX_COA_PROD_CD";
    /** DB Item Column Name */
    static final String TAX_COA_CH_CD = "TAX_COA_CH_CD";
    /** DB Item Column Name */
    static final String TAX_COA_AFFL_CD = "TAX_COA_AFFL_CD";
    /** DB Item Column Name */
    static final String TAX_COA_PROJ_CD = "TAX_COA_PROJ_CD";
    /** DB Item Column Name */
    static final String TAX_COA_EXTN_CD = "TAX_COA_EXTN_CD";
    // START 2023/01/05 S.Nakatani [QC#60248,ADD]
    /** DB Item Column Name */
    static final String HDLG_COA_CMPY_CD = "HDLG_COA_CMPY_CD";
    /** DB Item Column Name */
    static final String HDLG_COA_BR_CD = "HDLG_COA_BR_CD";
    /** DB Item Column Name */
    static final String HDLG_COA_CC_CD = "HDLG_COA_CC_CD";
    /** DB Item Column Name */
    static final String HDLG_COA_ACCT_CD = "HDLG_COA_ACCT_CD";
    /** DB Item Column Name */
    static final String HDLG_COA_PROD_CD = "HDLG_COA_PROD_CD";
    /** DB Item Column Name */
    static final String HDLG_COA_CH_CD = "HDLG_COA_CH_CD";
    /** DB Item Column Name */
    static final String HDLG_COA_AFFL_CD = "HDLG_COA_AFFL_CD";
    /** DB Item Column Name */
    static final String HDLG_COA_PROJ_CD = "HDLG_COA_PROJ_CD";
    /** DB Item Column Name */
    static final String HDLG_COA_EXTN_CD = "HDLG_COA_EXTN_CD";
    // END 2023/01/05 S.Nakatani [QC#60248,ADD]
    // START 2016/09/08 [QC#14161, ADD]
    /** DB Item Column Name */
    static final String MDSE_COA_CMPY_CD = "MDSE_COA_CMPY_CD";
    /** DB Item Column Name */
    static final String MDSE_COA_BR_CD = "MDSE_COA_BR_CD";
    /** DB Item Column Name */
    static final String MDSE_COA_CC_CD = "MDSE_COA_CC_CD";
    /** DB Item Column Name */
    static final String MDSE_COA_ACCT_CD = "MDSE_COA_ACCT_CD";
    /** DB Item Column Name */
    static final String MDSE_COA_PROD_CD = "MDSE_COA_PROD_CD";
    /** DB Item Column Name */
    static final String MDSE_COA_CH_CD = "MDSE_COA_CH_CD";
    /** DB Item Column Name */
    static final String MDSE_COA_AFFL_CD = "MDSE_COA_AFFL_CD";
    /** DB Item Column Name */
    static final String MDSE_COA_PROJ_CD = "MDSE_COA_PROJ_CD";
    /** DB Item Column Name */
    static final String MDSE_COA_EXTN_CD = "MDSE_COA_EXTN_CD";
    // END 2016/09/08 [QC#14161, ADD]
    //START 2016/09/29 [QC#14799, ADD]
    /** DB Item Column Name */
    static final String FROM_TOL_NET_PRC_AMT = "FROM_TOL_NET_PRC_AMT";
    /** DB Item Column Name */
    static final String THRU_TOL_NET_PRC_AMT = "THRU_TOL_NET_PRC_AMT";
    /** DB Item Column Name */
    static final String AC_OC_FOB_COST_AMT = "AC_OC_FOB_COST_AMT";
    // END 2016/09/29 [QC#14799, ADD]
    //START 2016/09/30 [QC#14797, ADD]
    /** DB Item Column Name */
    static final String CM_INV_ACCT_DIST_LINE_NUM = "CM_INV_ACCT_DIST_LINE_NUM";
    // END 2016/09/30 [QC#14797, ADD]
    /** DB Item Column Name */
    static final String VAR_COA_CMPY_CD = "VAR_COA_CMPY_CD";
    /** DB Item Column Name */
    static final String VAR_COA_BR_CD = "VAR_COA_BR_CD";
    /** DB Item Column Name */
    static final String VAR_COA_CC_CD = "VAR_COA_CC_CD";
    /** DB Item Column Name */
    static final String VAR_COA_ACCT_CD = "VAR_COA_ACCT_CD";
    /** DB Item Column Name */
    static final String VAR_COA_PROD_CD = "VAR_COA_PROD_CD";
    /** DB Item Column Name */
    static final String VAR_COA_CH_CD = "VAR_COA_CH_CD";
    /** DB Item Column Name */
    static final String VAR_COA_AFFL_CD = "VAR_COA_AFFL_CD";
    /** DB Item Column Name */
    static final String VAR_COA_PROJ_CD = "VAR_COA_PROJ_CD";
    /** DB Item Column Name */
    static final String VAR_COA_EXTN_CD = "VAR_COA_EXTN_CD";
    /** DB Item Column Name */
    static final String AC_INV_VAR_COST_AMT = "AC_INV_VAR_COST_AMT";
    /** DB Item Column Name */
    static final String PO_NUM = "PO_NUM";
    /** DB Item Column Name */
    static final String PO_MATCH_TP_CD = "PO_MATCH_TP_CD";
    /** DB Item Column Name */
    static final String PMT_HLD_CD = "PMT_HLD_CD";
    /** DB Item Column Name */
    static final String PMT_HLD_RSN_CD = "PMT_HLD_RSN_CD";
    /** DB Item Column Name */
    static final String PMT_HLD_REL_RSN_CD = "PMT_HLD_REL_RSN_CD";
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
    static final String INVTY_CTRL_FLG = "INVTY_CTRL_FLG";
    /** DB Item Column Name */
    static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";
    // START 2016/11/15 T.Tsuchida [QC#15982,ADD]
    /** DB Item Column Name */
    static final String PO_INV_BAL_QTY = "PO_INV_BAL_QTY";
    // END 2016/11/15 T.Tsuchida [QC#15982,ADD]

    // START 2019/10/25 [QC#54275, ADD]
    /** DB Item Column Name */
    static final String PO_VND_CD = "PO_VND_CD";
    // END   2019/10/25 [QC#54275, ADD]
    
    // VAR_CHAR_CONST
    /** VAR_CHAR_CONST : NFBB1180_AP_INV_CATG_CD */
    static final String VARCHAR_CONST_NFBB1180_AP_INV_CATG_CD = "NFBB1180_AP_INV_CATG_CD";
    // START 2016/11/10 E.Kameishi [QC#14800, ADD]
    // COA_CONST
    /** COA_CONST : I05_CHARGES_ACCT */
    static final String I05_CHARGES_ACCT = "I05_CHARGES_ACCT";
    // START 2021/06/09 H.Dimay [QC#58883, ADD]
    /** COA_CONST : ALL_FREIGHT */
    static final String ALL_FREIGHT = "ALL_FREIGHT";
    // END 2021/06/09 H.Dimay [QC#58883, ADD]
    /** COA_CONST : ALL */
    static final String ALL = "ALL";
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
    static final String DEST_RTL_WH_CD = "DEST_RTL_WH_CD";
    // END 2016/11/10 E.Kameishi [QC#14800, ADD]

    // START 2018/04/02 [QC#20316, ADD]
    /** DB Item Column Name */
    static final String AP_VND_INV_LINE_NUM = "AP_VND_INV_LINE_NUM";

    /** DB Item Column Name */
    static final String INV_LINE_CNT = "INV_LINE_CNT";
    // END   2018/04/02 [QC#20316, ADD]

    // START 2018/04/02 [QC#24277, ADD]
    /** AP_VND_INV_LINE_NUM_0000 */
    public static final String AP_VND_INV_LINE_NUM_0000 = "0000";
    // END   2018/04/02 [QC#24277, ADD]

    // START 2018/05/16 [QC#26080, ADD]
    /** DB Item Column Name */
    static final String TOT_INV_QTY = "TOT_INV_QTY";
    // END   2018/05/16 [QC#26080, ADD]

    // START 2018/07/30 [QC#27025-1, ADD]
    /** DB Item Column Name */
    static final String PD_ENT_DEAL_NET_UNIT_PRC_AMT = "PD_ENT_DEAL_NET_UNIT_PRC_AMT";
    // END   2018/07/30 [QC#27025-1, ADD]

    // START 2018/10/18 [QC#28734, MOD]
    static final String SUM_CSI_RCV_QTY = "SUM_CSI_RCV_QTY";
    // END   2018/10/18 [QC#28734, MOD]

    // START 2018/11/15 [QC#28982, ADD]
    /** DB Item Column Name */
    static final String PD_ENT_FUNC_NET_UNIT_PRC_AMT = "PD_ENT_FUNC_NET_UNIT_PRC_AMT";
    // END   2018/11/15 [QC#28982, ADD]

    // START 2018/12/20 [QC#29679, ADD]
    /** DS_COND_CONST_KEY : NFBB1180_PRCH_GRP */
    static final String NFBB1180_PRCH_GRP = "NFBB1180_PRCH_GRP";
    // END   2018/12/20 [QC#29679, ADD]

    // START 2019/08/29 [QC#52280, ADD]
    static final String SELECT_POUPDATE_RECORD_FOR_CREDIT_MEMO = "SELECT_POUPDATE_RECORD_FOR_CREDIT_MEMO";
    // END   2019/08/29 [QC#52280, ADD]

    // START 2019/11/11 [QC#29679, ADD]
    /** RPT_COND_CONST_KEY : PRT_ORD_LOB */
    static final String PRT_ORD_LOB = "PRT_ORD_LOB";
    // END   2019/11/11 [QC#29679, ADD]

    // START 2021/06/09 [QC#58883, ADD]
    /** SHIP_TO_CUST_CD_DIETZGEN */
    static final String SHIP_TO_CUST_CD_DIETZGEN = "DIETZGEN";
    // END 2021/06/09 [QC#58883, ADD]
}
