/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2040.constant;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Invoice Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/07   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2016/07/11   Hitachi         Y.Tsuchimoto    Update          QC#11502
 * 2016/08/01   Hitachi         T.Tsuchida      Update          QC#12712
 * 2016/08/05   Hitachi         T.Tsuchida      Update          QC#12935
 * 2016/08/09   Hitachi         Y.Tsuchimoto    Update          QC#12195
 * 2016/08/09   Hitachi         Y.Tsuchimoto    Update          QC#12198
 * 2016/08/10   Hitachi         Y.Tsuchimoto    Update          QC#12948
 * 2016/08/23   Hitachi         Y.Tsuchimoto    Update          QC#12010
 * 2016/08/25   Hitachi         Y.Tsuchimoto    Update          QC#12979
 * 2016/08/26   Hitachi         Y.Tsuchimoto    Update          QC#12043
 * 2016/09/16   Hitachi         Y.Tsuchimoto    Update          QC#13245
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#13550
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#12038
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#13157
 * 2016/09/26   Fujitsu         W.Honda         Update          Unit Test#201
 * 2016/09/28   Hitachi         T.Tsuchida      Update          QC#13608
 * 2016/09/28   Hitachi         T.Tsuchida      Update          QC#13960
 * 2016/09/28   Hitachi         Y.Tsuchimoto    Update          QC#14797
 * 2016/09/30   Hitachi         T.Tsuchida      Update          QC#14498
 * 2016/10/04   Hitachi         T.Tsuchida      Update          QC#13414
 * 2016/10/05   Hitachi         T.Tsuchida      Update          QC#14164
 * 2016/10/25   Hitachi         Y.Tsuchimoto    Update          QC#14501
 * 2016/11/11   Hitachi         K.Kasai         Update          QC#15455
 * 2016/11/15   Hitachi         K.Kasai         Update          QC#15904
 * 2016/12/07   Fujitsu         H.Ikeda         Update          QC#15823
 * 2017/01/12   Hitachi         E.Kameishi      Update          QC#16950
 * 2017/01/13   Hitachi         E.Kameishi      Update          QC#16948
 * 2017/01/25   Hitachi         E.Kameishi      Update          QC#17090
 * 2017/03/17   Hitachi         E.Kameishi      Update          QC#14205
 * 2017/06/15   CITS            K.Ogino         Update          QC#19219
 * 2017/10/20   CITS            T.Tokutomi      Update          QC#21653
 * 2017/11/15   CITS            T.Wada          Update          QC#21666
 * 2017/12/15   CITS            T.Hakodate      Update          QC#23038
 * 2017/12/22   CITS            T.Tokutomi      Update          QC#14858-Sol#060
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#23022
 * 2017/12/25   Hitachi         J.Kim           Update          QC#23059
 * 2018/01/25   CITS            S.Katsuma       Update          QC#19579
 * 2018/02/23   Hitachi         Y.Takeno        Update          QC#23505
 * 2018/03/06   Hitachi         Y.Takeno        Update          QC#23505
 * 2018/03/08   Hitachi         Y.Takeno        Update          QC#24275
 * 2018/03/13   Hitachi         Y.Takeno        Update          QC#24089
 * 2018/03/22   Hitachi         Y.Takeno        Update          QC#20316
 * 2018/03/28   Hitachi         Y.Takeno        Update          QC#24277
 * 2018/05/07   Hitachi         Y.Takeno        Update          QC#25828
 * 2018/05/25   CITS            K.Ogino         Update          QC#25902,QC#25190,QC#25141
 * 2018/07/17   CITS            S.Katsuma       Update          QC#27078
 * 2018/08/02   Hitachi         Y.Takeno        Update          QC#27025-1
 * 2018/08/15   Hitachi         Y.Takeno        Update          QC#27029-1
 * 2018/08/16   Hitachi         Y.Takeno        Update          QC#27247
 * 2018/09/25   Fujitsu         S.Ohki          Update          QC#28441
 * 2018/11/20   Hitachi         Y.Takeno        Update          QC#28660
 * 2020/03/16   Fujitsu         H.Mizukami      Update          QC#55993
 * 2020/03/23   Fujitsu         H.Ikeda         Update          QC#53413
 * 2022/03/28   CITS            D.Mamaril       Update          QC#59862
 * 2022/05/27   CITS            D.Mamaril       Update          QC#60084
 * </pre>
 */
public interface NFBL2040Constant {

    /** Empty Value */
    static final String EMPTY_STRING = "";
    
    // QC#23038 MOD START
    /** VND_INV_IMPT_TP_CD_10 */
    static final String VND_INV_IMPT_TP_CD_10 = "10";
    // QC#23038 MOD END
    
    /** NONE */
    static final String NONE = "NONE";

    /** yyyyMMdd */
    static final String YYYYMMDD = "yyyyMMdd";

    public static final String DOT = ".";

    /** PO_ORD_DTL_LINE_NUM format **/
    public static final String PO_ORD_DTL_LINE_NUM_FORMAT = "%03d";

    /** XXX_LINE_NUM format **/
    public static final String LINE_NUM_FORMAT = "%03d";

    /** COA_EXTN_CD 10 */
    static final String VAR_COA_EXTN_CD_010 = "010";

    /** AP_VND_INV_SQ_NUM 00 */
    static final String AP_VND_INV_SQ_NUM_00 = "00";

    /** AP_INV_TP_CD 00 */
    static final String AP_INV_TP_CD_00 = "00";

    /** AP_INV_TP_CD 00 */
    static final String AP_INV_TP_CD_02 = "02";

    /** AP_LINE_TP_CD 01 */
    static final String AP_LINE_TP_CD_01 = "01";

    // Distribution Radio Button
    /** Distribution radio button (All Lines) */
    public static final String All_LINES = "A";

    /** Distribution radio button (Selected Lines) */
    public static final String SELECTED_LINES = "S";

    // PMT_HLD_CD
    /** PMT_HLD_CD 000 (Success) */
    public static final String PMT_HLD_CD_000 = "000";

    /** PMT_HLD_CD 010 (Quantity) */
    public static final String PMT_HLD_CD_010 = "010";

    /** PMT_HLD_CD 020 (Amount) */
    public static final String PMT_HLD_CD_020 = "020";

    /** PMT_HLD_CD 030 (Therefore) */
    public static final String PMT_HLD_CD_030 = "030";

    // PMT_HLD_RSN_CD
    /** PMT_HLD_RSN_CD 000 (Success) */
    public static final String PMT_HLD_RSN_CD_000 = "000";

    /** PMT_HLD_RSN_CD 012 (Quantity Not Match (PO : Invoice)) */
    public static final String PMT_HLD_RSN_CD_012 = "012";

    // QC#21653 Add
    /** PMT_HLD_RSN_CD 011 (Quantity Not Match (PO : Received)) */
    public static final String PMT_HLD_RSN_CD_011 = "011";

    // QC#21653 Add
    /** PMT_HLD_RSN_CD 013 (Quantity Not Match (Invoice : Received)) */
    public static final String PMT_HLD_RSN_CD_013 = "013";

    /** PMT_HLD_RSN_CD 021 (Amount (PO : Invoice)) */
    public static final String PMT_HLD_RSN_CD_021 = "021";

    /** PMT_HLD_RSN_CD 031 (Therefore) */
    public static final String PMT_HLD_RSN_CD_031 = "031";

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String PO_STS_CD = "PO_STS_CD";

    /** DB Item Column Name */
    static final String CM_AP_INV_TP_CD = "CM_AP_INV_TP_CD";

    /** DB Item Column Name */
    static final String CM_AP_INV_TP_DESC_TXT = "CM_AP_INV_TP_DESC_TXT";

    /** DB Item Column Name */
    static final String AP_INV_CATG_DESC_TXT = "AP_INV_CATG_DESC_TXT";

    /** DB Item Column Name */
    static final String DPLY_VND_NM = "DPLY_VND_NM"; // Vendor Name

    /** DB Item Column Name */
    static final String AP_VND_CD = "AP_VND_CD"; // Location

    /** DB Item Column Name */
    static final String AP_VND_INV_NUM = "AP_VND_INV_NUM"; // Invoice

    // Number

    /** DB Item Column Name */
    static final String INV_DT = "INV_DT"; // Invoice Date

    /** DB Item Column Name */
    static final String PAY_ALONE_FLG = "PAY_ALONE_FLG"; // Pay Alone

    // Flag

    /** DB Item Column Name */
    static final String AP_PMT_CHK_NUM = "AP_PMT_CHK_NUM"; // Payment

    // Number

    /** DB Item Column Name */
    static final String VND_PMT_METH_CD = "VND_PMT_METH_CD"; // Payment

    // Method
    // Code

    /** DB Item Column Name */
    static final String VND_PMT_METH_DESC_TXT = "VND_PMT_METH_DESC_TXT"; // Payment

    // Method
    // Name

    /** DB Item Column Name */
    static final String ACCT_INV_STS_CD = "ACCT_INV_STS_CD"; // Accounting

    // Invoice
    // Status
    // Code

    /** DB Item Column Name */
    static final String ACCT_INV_STS_DESC_TXT = "ACCT_INV_STS_DESC_TXT"; // Status

    /** DB Item Column Name */
    static final String PRNT_VND_CD = "PRNT_VND_CD"; // Vendor Number

    /** DB Item Column Name */
    static final String CM_INV_MATCH_CD = "CM_INV_MATCH_CD"; // Invoice

    // Matching

    /** DB Item Column Name */
    static final String CM_INV_MATCH_DESC_TXT = "CM_INV_MATCH_DESC_TXT"; // Invoice

    // Matching

    /** DB Item Column Name */
    static final String PO_NUM = "PO_NUM"; // PO Number

    /** DB Item Column Name */
    static final String AC_INV_TOT_COST_AMT = "AC_INV_TOT_COST_AMT"; // Invoice

    // Amount

    /** DB Item Column Name */
    static final String VND_PMT_TERM_CD = "VND_PMT_TERM_CD"; // Payment

    // Term
    // Code

    /** DB Item Column Name */
    static final String VND_PMT_TERM_DESC_TXT = "VND_PMT_TERM_DESC_TXT"; // Terms

    /** DB Item Column Name */
    static final String HDR_PMT_AMT = "HDR_PMT_AMT"; // Amount Paid

    /** DB Item Column Name */
    static final String PMT_DT = "PMT_DT"; // Payment Date

    /** DB Item Column Name */
    public static final String INV_AUTH_DT = "INV_AUTH_DT";

    /** DB Item Column Name */
    static final String TERM_NET_DUE_DT = "TERM_NET_DUE_DT"; // Terms

    // Date

    /** DB Item Column Name */
    static final String PO_APVL_DT = "PO_APVL_DT"; // PO Date

    /** DB Item Column Name */
    static final String DELY_ORD_NUM = "DELY_ORD_NUM"; // Receipt

    // Number

    /** DB Item Column Name */
    static final String RWS_NUM = "RWS_NUM"; // RWS Number

    /** DB Item Column Name */
    static final String INV_HDR_DESC_TXT = "INV_HDR_DESC_TXT"; // Description

    /** DB Item Column Name */
    static final String AC_OC_TOT_DISC_AMT = "AC_OC_TOT_DISC_AMT"; // Discount

    /** DB Item Column Name */
    static final String INV_ASG_FLG = "INV_ASG_FLG"; // Invoice Assign

    // Flag

    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD"; // Item

    /** DB Item Column Name */
    static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT"; // Line

    // Description

    /** DB Item Column Name */
    static final String AC_INV_JRNL_COST_AMT = "AC_INV_JRNL_COST_AMT"; // AC_INV_JRNL_COST_AMT

    /** DB Item Column Name */
    static final String UOM_CD = "UOM_CD"; // Unit Of Measure

    /** DB Item Column Name */
    static final String THIS_MTH_FOB_COST_AMT = "THIS_MTH_FOB_COST_AMT";

    /** DB Item Column Name */
    static final String AC_INV_TAX_AMT = "AC_INV_TAX_AMT";

    /** DB Item Column Name */
    static final String INV_ASG_DT = "INV_ASG_DT";

    /** DB Item Column Name */
    static final String STK_IN_DT = "STK_IN_DT";

    /** DB Item Column Name */
    static final String AP_INV_DESC_TXT = "AP_INV_DESC_TXT";

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
    static final String DR_COA_CMPY_CD = "DR_COA_CMPY_CD";

    /** DB Item Column Name */
    static final String DR_COA_BR_CD = "DR_COA_BR_CD";

    /** DB Item Column Name */
    static final String DR_COA_CC_CD = "DR_COA_CC_CD";

    /** DB Item Column Name */
    static final String DR_COA_ACCT_CD = "DR_COA_ACCT_CD";

    /** DB Item Column Name */
    static final String DR_COA_PROD_CD = "DR_COA_PROD_CD";

    /** DB Item Column Name */
    static final String DR_COA_CH_CD = "DR_COA_CH_CD";

    /** DB Item Column Name */
    static final String DR_COA_AFFL_CD = "DR_COA_AFFL_CD";

    /** DB Item Column Name */
    static final String DR_COA_PROJ_CD = "DR_COA_PROJ_CD";

    /** DB Item Column Name */
    static final String DR_COA_EXTN_CD = "DR_COA_EXTN_CD";

    /** DB Item Column Name */
    static final String XX_CMNT_TXT = "XX_CMNT_TXT"; // Charge Account

    /** DB Item Column Name */
    static final String AP_ACCT_DESC_TXT = "AP_ACCT_DESC_TXT"; // Charge

    // Account

    /** DB Item Column Name */
    static final String IF_PROC_STS_CD = "IF_PROC_STS_CD";

    /** DB Item Column Name */
    static final String AP_INV_AUTH_FLG = "AP_INV_AUTH_FLG";

    /** DB Item Column Name */
    static final String AP_JRNL_CPLT_FLG = "AP_JRNL_CPLT_FLG";

    /** DB Item Column Name */
    static final String ENT_PO_DTL_DEAL_NET_AMT = "ENT_PO_DTL_DEAL_NET_AMT";

    // ADD QC#19219 START
    /** DB Item Column Name */
    static final String ENT_PO_DTL_DEAL_NET_AMT_LINE = "ENT_PO_DTL_DEAL_NET_AMT_LINE";
    // ADD QC#19219 END

    /** DB Item Column Name */
    static final String ENT_FUNC_NET_UNIT_PRC_AMT = "ENT_FUNC_NET_UNIT_PRC_AMT";

    /** DB Item Column Name */
    static final String ENT_PO_DTL_FUNC_NET_AMT = "ENT_PO_DTL_FUNC_NET_AMT";

    /** DB Item Column Name */
    static final String ENT_DEAL_NET_UNIT_PRC_AMT = "ENT_DEAL_NET_UNIT_PRC_AMT"; // Unit

    // QC#14858-Sol#060 Add.

    /** DB Item Column Name */
    static final String CSI_ENT_PO_DTL_DEAL_NET_AMT = "CSI_ENT_PO_DTL_DEAL_NET_AMT";

    /** DB Item Column Name */
    static final String CSI_ENT_FUNC_NET_UNIT_PRC_AMT = "CSI_ENT_FUNC_NET_UNIT_PRC_AMT";

    /** DB Item Column Name */
    static final String CSI_ENT_PO_DTL_FUNC_NET_AMT = "CSI_ENT_PO_DTL_FUNC_NET_AMT";

    /** DB Item Column Name */
    static final String CSI_ENT_DEAL_NET_UNIT_PRC_AMT = "CSI_ENT_DEAL_NET_UNIT_PRC_AMT";

    /** DB Item Column Name */
    static final String CM_STK_IN_PRC_FLG = "CM_STK_IN_PRC_FLG";

    // Price

    /** DB Item Column Name */
    static final String EXCH_RATE = "EXCH_RATE";

    /** DB Item Column Name */
    static final String PO_QTY = "PO_QTY"; // Ordered Quantity

    /** DB Item Column Name */
    static final String INV_RCV_QTY = "INV_RCV_QTY"; // Received

    // Quantity

    /** DB Item Column Name */
    static final String AP_REJ_QTY = "AP_REJ_QTY"; // Rejected

    // Quantity

    /** DB Item Column Name */
    static final String AP_BILL_QTY = "AP_BILL_QTY"; // Billed

    // Quantity

    /** DB Item Column Name */
    static final String CONTR_NUM = "CONTR_NUM"; // Contract

    /** DB Item Column Name */
    static final String CUST_DLR_CD = "CUST_DLR_CD"; // Dealer Code

    /** DB Item Column Name */
    static final String SER_NUM = "SER_NUM"; // Serial Number

    /** DB Item Column Name */
    static final String CSMP_INV_NUM = "CSMP_INV_NUM"; // CSMP Invoice

    /** DB Item Column Name */
    static final String ISTL_LOC = "ISTL_LOC"; // Install Location

    /** DB Item Column Name */
    static final String LINE_AMT = "LINE_AMT"; // Line Amount

    /** DB Item Column Name */
    static final String INV_TP_CD = "INV_TP_CD";

    /** DB Item Column Name */
    static final String INV_QTY = "INV_QTY"; // Ordered Quantity

    /** DB Item Column Name */
    static final String CM_STK_IN_PK = "CM_STK_IN_PK";

    /** DB Item Column Name */
    static final String ORIG_VND_INV_NUM = "ORIG_VND_INV_NUM";

    /** DB Item Column Name */
    static final String ORIG_VND_INV_SQ_NUM = "ORIG_VND_INV_SQ_NUM";

    /** DB Item Column Name */
    static final String ORIG_DELY_ORD_NUM = "ORIG_DELY_ORD_NUM";

    /** DB Item Column Name */
    static final String PMT_HLD_FLG = "PMT_HLD_FLG"; // Payment Hold

    // Flag

    /** DB Item Column Name */
    static final String PMT_HLD_CD = "PMT_HLD_CD"; // Hold Code

    /** DB Item Column Name */
    static final String PMT_HLD_DESC_TXT = "PMT_HLD_DESC_TXT"; // Hold

    // Name

    /** DB Item Column Name */
    static final String PMT_HLD_DT = "PMT_HLD_DT"; // Hold Date

    /** DB Item Column Name */
    static final String PMT_HLD_RSN_CD = "PMT_HLD_RSN_CD"; // Hold

    // Reason
    // Code

    /** DB Item Column Name */
    static final String PMT_HLD_RSN_DESC_TXT = "PMT_HLD_RSN_DESC_TXT"; // Hold

    // Reason
    // Name

    /** DB Item Column Name */
    static final String PMT_HLD_PSN_CD = "PMT_HLD_PSN_CD"; // Hold By

    /** DB Item Column Name */
    static final String PMT_HLD_REL_PSN_CD = "PMT_HLD_REL_PSN_CD"; // Released

    // By

    /** DB Item Column Name */
    static final String PMT_HLD_REL_DT = "PMT_HLD_REL_DT"; // Released

    // Date

    /** DB Item Column Name */
    static final String PMT_HLD_REL_RSN_CD = "PMT_HLD_REL_RSN_CD"; // Released

    // Reason
    // Code

    /** DB Item Column Name */
    static final String PMT_HLD_REL_RSN_DESC_TXT = "PMT_HLD_REL_RSN_DESC_TXT"; // Released

    // Reason
    // Name

    /** DB Item Column Name */
    static final String PMT_HLD_REL_CMNT_TXT = "PMT_HLD_REL_CMNT_TXT"; // Release

    // Note

    /** DB Item Column Name */
    static final String AP_LINE_TP_CD = "AP_LINE_TP_CD"; // Line Type

    // Code

    /** DB Item Column Name */
    static final String AP_LINE_TP_DESC_TXT = "AP_LINE_TP_DESC_TXT"; // Line

    // Type
    // Name

    /** DB Item Column Name */
    static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM"; // PO

    // Order
    // Detail
    // Line
    // Number

    /** DB Item Column Name */
    static final String PO_MATCH_TP_CD = "PO_MATCH_TP_CD"; // PO Match

    // Type
    // Code

    /** DB Item Column Name */
    static final String IMPT_INV_CNSGN_CD = "IMPT_INV_CNSGN_CD";

    /** DB Item Column Name */
    static final String CM_LIC_NUM = "CM_LIC_NUM";

    /** DB Item Column Name */
    static final String AC_OC_FOB_COST_AMT = "AC_OC_FOB_COST_AMT";

    /** DB Item Column Name */
    static final String AC_OC_FRT_COST_AMT = "AC_OC_FRT_COST_AMT";

    /** DB Item Column Name */
    static final String AC_OC_INS_COST_AMT = "AC_OC_INS_COST_AMT";

    /** DB Item Column Name */
    static final String AC_OC_DOM_EXP_COST_AMT = "AC_OC_DOM_EXP_COST_AMT";

    /** DB Item Column Name */
    static final String AC_OC_OTH_COST_AMT = "AC_OC_OTH_COST_AMT";

    /** DB Item Column Name */
    static final String AC_OC_TAX_AMT = "AC_OC_TAX_AMT";

    /** DB Item Column Name */
    static final String AC_OC_TOT_COST_AMT = "AC_OC_TOT_COST_AMT";

    /** DB Item Column Name */
    static final String AC_OC_TOT_INV_AMT = "AC_OC_TOT_INV_AMT";

    /** DB Item Column Name */
    static final String AC_SC_FOB_COST_AMT = "AC_SC_FOB_COST_AMT";

    /** DB Item Column Name */
    static final String AC_SC_FRT_COST_AMT = "AC_SC_FRT_COST_AMT";

    /** DB Item Column Name */
    static final String AC_SC_INS_COST_AMT = "AC_SC_INS_COST_AMT";

    /** DB Item Column Name */
    static final String AC_SC_DOM_EXP_COST_AMT = "AC_SC_DOM_EXP_COST_AMT";

    /** DB Item Column Name */
    static final String AC_SC_OTH_COST_AMT = "AC_SC_OTH_COST_AMT";

    /** DB Item Column Name */
    static final String AC_SC_TAX_AMT = "AC_SC_TAX_AMT";

    /** DB Item Column Name */
    static final String AC_SC_TOT_COST_AMT = "AC_SC_TOT_COST_AMT";

    /** DB Item Column Name */
    static final String AC_SC_TOT_INV_AMT = "AC_SC_TOT_INV_AMT";

    /** DB Item Column Name */
    static final String AC_SC_TRNST_JRNL_AMT = "AC_SC_TRNST_JRNL_AMT";

    /** DB Item Column Name */
    static final String AC_SC_STK_IN_COST_AMT = "AC_SC_STK_IN_COST_AMT";

    /** DB Item Column Name */
    static final String CM_AP_INV_ASG_CD = "CM_AP_INV_ASG_CD";

    /** DB Item Column Name */
    static final String CSTM_ARV_DT = "CSTM_ARV_DT";

    /** DB Item Column Name */
    static final String APPLY_EXCH_RATE = "APPLY_EXCH_RATE";

    /** DB Item Column Name */
    static final String IMPT_REF_NUM = "IMPT_REF_NUM";

    /** DB Item Column Name */
    static final String TRNST_JRNL_CPLT_FLG = "TRNST_JRNL_CPLT_FLG";

    /** DB Item Column Name */
    static final String STK_IN_JRNL_CPLT_FLG = "STK_IN_JRNL_CPLT_FLG";

    /** DB Item Column Name */
    static final String VND_REF_NUM = "VND_REF_NUM";

    /** DB Item Column Name */
    static final String VND_FOC_TP_CD = "VND_FOC_TP_CD";

    /** DB Item Column Name */
    static final String ORIG_SC_FOB_COST_AMT = "ORIG_SC_FOB_COST_AMT";

    /** DB Item Column Name */
    static final String ORIG_SC_FRT_COST_AMT = "ORIG_SC_FRT_COST_AMT";

    /** DB Item Column Name */
    static final String ORIG_SC_INS_COST_AMT = "ORIG_SC_INS_COST_AMT";

    /** DB Item Column Name */
    static final String ORIG_SC_OTH_COST_AMT = "ORIG_SC_OTH_COST_AMT";

    /** DB Item Column Name */
    static final String OC_FRT_COST_AMT = "OC_FRT_COST_AMT";

    /** DB Item Column Name */
    static final String OC_INS_OTH_COST_AMT = "OC_INS_OTH_COST_AMT";

    /** DB Item Column Name */
    static final String OC_UNIT_EXT_COST_AMT = "OC_UNIT_EXT_COST_AMT";

    /** DB Item Column Name */
    static final String OC_EXT_COST_AMT = "OC_EXT_COST_AMT";

    /** DB Item Column Name */
    static final String SC_UNIT_EXT_COST_AMT = "SC_UNIT_EXT_COST_AMT";

    /** DB Item Column Name */
    static final String SC_EXT_COST_AMT = "SC_EXT_COST_AMT";

    /** DB Item Column Name */
    static final String ATT_DATA_PK = "ATT_DATA_PK";

    /** DB Item Column Name */
    static final String DOC_MGT_DOC_ID = "DOC_MGT_DOC_ID";

    /** DB Item Column Name */
    static final String ATT_DOC_TP_CD = "ATT_DOC_TP_CD";

    /** DB Item Column Name */
    static final String DOC_MGT_CATG_CD = "DOC_MGT_CATG_CD";

    /** DB Item Column Name */
    static final String PO_INV_BAL_QTY = "PO_INV_BAL_QTY";

    /** DB Item Column Name */
    public static final String AP_VND_INV_LINE_NUM = "AP_VND_INV_LINE_NUM";

    /** DB Item Column Name */
    public static final String CM_INV_ACCT_DIST_LINE_NUM = "CM_INV_ACCT_DIST_LINE_NUM";

    /** DB Item Column Name */
    public static final String DEAL_GRS_UNIT_PRC_AMT = "DEAL_GRS_UNIT_PRC_AMT";

    //START 2017/03/16 E.Kameishi [QC#14205, ADD]
    /** DB Item Column Name */
    static final String XX_REC_HIST_CRAT_TS = "XX_REC_HIST_CRAT_TS";
    /** DB Item Column Name */
    static final String XX_REC_HIST_CRAT_BY_NM = "XX_REC_HIST_CRAT_BY_NM";
    /** DB Item Column Name */
    static final String XX_REC_HIST_UPD_TS = "XX_REC_HIST_UPD_TS";
    /** DB Item Column Name */
    static final String XX_REC_HIST_UPD_BY_NM = "XX_REC_HIST_UPD_BY_NM";
    /** DB Item Column Name */
    static final String XX_REC_HIST_TBL_NM = "XX_REC_HIST_TBL_NM";
    /** DB Item Column Name */
    static final String XX_REC_HIST_CRAT_TS_A1 = "XX_REC_HIST_CRAT_TS_A1";
    /** DB Item Column Name */
    static final String XX_REC_HIST_CRAT_BY_NM_A1 = "XX_REC_HIST_CRAT_BY_NM_A1";
    /** DB Item Column Name */
    static final String XX_REC_HIST_UPD_TS_A1 = "XX_REC_HIST_UPD_TS_A1";
    /** DB Item Column Name */
    static final String XX_REC_HIST_UPD_BY_NM_A1 = "XX_REC_HIST_UPD_BY_NM_A1";
    /** DB Item Column Name */
    static final String XX_REC_HIST_TBL_NM_A1 = "XX_REC_HIST_TBL_NM_A1";
    //END 2017/03/16 E.Kameishi [QC#14205, ADD]

    // ** SSM Statement ID's Fixed Value ** //
    /** SSM Statement ID */
    static final String SELECT_CM_AP_INV_IMPT_DTL_BY_PARTIAL_KEY = "SELECT_CM_AP_INV_IMPT_DTL_BY_PARTIAL_KEY";
    
    // ** Message ID ** //
    /** Please check at least 1 @ checkbox. */
    static final String NFBM0017E = "NFBM0017E";

    /** @ is invalid. */
    static final String NFBM0041E = "NFBM0041E";

    /** Record is not found. */
    static final String NFBM0069E = "NFBM0069E";

    /**
     * Showing only first @ of total @ records. Please review your
     * search criteria.
     */
    static final String NFBM0001W = "NFBM0001W";

    /** Process ended normally. */
    static final String ZZM8100I = "ZZM8100I";

    /** Specified PO Information is invalid or does not exist. */
    static final String NPZM0021E = "NPZM0021E";

    /**
     * Invoice date must be before today.
     */
    public static final String NFBM0106E = "NFBM0106E";

    /**
     * Table : @ Update Error. Return Code = @
     */
    public static final String NFBM0224E = "NFBM0224E";

    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * You can not [Submit] this invoice record, because this invoice
     * record already exists. [Vendor# = '@', Invoice# = '@']
     */
    public static final String NFBM0233E = "NFBM0233E";

    /**
     * @ does not exist in @.
     */
    public static final String NFBM0235E = "NFBM0235E";

    /**
     * There is no data to be processed.
     */
    public static final String NFBM0238E = "NFBM0238E";

    /**
     * Duplicate records exist. [ @ ]
     */
    public static final String NFBM0181E = "NFBM0181E";

    /**
     * Please set the same value as the PO's Vendor.
     */
    public static final String NFBM0259E = "NFBM0259E";

    /**
     * Please set the same value as the Vendor Return's Vendor.
     */
    public static final String NFBM0267E = "NFBM0267E";

    /**
     * The status of this Purchase Order does not allow.
     */
    public static final String NFBM0260E = "NFBM0260E";

    /**
     * It failed to register [@]. Please contact IT Support.
     */
    public static final String NFBM0263E = "NFBM0263E";

    /**
     * It failed to remove [@]. Please contact IT Support.
     */
    public static final String NFBM0266E = "NFBM0266E";

    /** Message Kind */
    static final String MESSAGE_KIND_E = "E";

    /**
     * The Item has been put on Hold.
     */
    public static final String NFBM0265E = "NFBM0265E";

    /**
     * You cannot delete this line, because line type is Item.
     */
    public static final String NFBM0269E = "NFBM0269E";

    /**
     * Table : @ Insert Error. Return Code = @
     */
    public static final String NFBM0073E = "NFBM0073E";

    /**
     * @ must be after @
     */
    public static final String NFBM0142E = "NFBM0142E";

    /**
     * Please enter the "Release" button if you would like to release
     * hold.
     */
    public static final String NFBM0272E = "NFBM0272E";

    // START 2018/02/27 [QC#23505, ADD]
    /**
     * You cannot delete this line, because line is already corrected.
     */
    public static final String NFBM0280E = "NFBM0280E";

    /**
     * @ is mismatched with @.
     */
    public static final String NFBM0281E = "NFBM0281E";
    // END   2018/02/27 [QC#23505, ADD]

    // START 2018/03/06 [QC#23505, ADD]
    /**
     * This data has been updated by another user.
     */
    public static final String NZZM0003E = "NZZM0003E";
    // END   2018/03/06 [QC#23505, ADD]

    
    // START 2018/03/06 [QC#24275, ADD]
    /**
     * You cannot release hold, because @ exceeds @.
     */
    public static final String NFBM0282E = "NFBM0282E";
    // END   2018/03/06 [QC#24275, ADD]

    // START 2020/03/16 [QC#55993, MOD]
    /**
     * @ exceeds @, Are you sure to release hold?
     */
    public static final String NFBM0291W = "NFBM0291W";
    /**
     * You cannot release hold, because @ exceeds @.
     */
    public static final String NFBM0292W = "NFBM0292W";
    // END   2020/03/16 [QC#55993, MOD]

    // ** CSV Download ** //
    /** File name for excel download (Holds tab) */
    static final String FILE_NAME_HOLDS = "Invoice Entry(Holds)";

    /** File name for excel download (Lines tab) */
    static final String FILE_NAME_LINES = "Invoice Entry(Lines)";

    /** File name for excel download (Distributions tab) */
    static final String FILE_NAME_DISTRIBUTIONS = "Invoice Entry(Distributions)";

    /** File extension for CSV download */
    static final String CSV_EXT = ".csv";

    /** Column names for excel download */
    // START 2018/01/25 S.Katsuma [QC#19579,MOD]
//    static final String[] CSV_HEADER_HOLDS = {"Hold Name", "Hold Date", "Hold Reason", "Hold By", "Released By", "Released Date", "Release Reason", "Release Note" };
    // START 2018/03/28 [QC#24277, MOD]
//    static final String[] CSV_HEADER_HOLDS = {"Hold Name", "Hold Date", "Hold Reason", "Hold By", "Hold Release" ,"Released By", "Released Date", "Release Reason", "Release Note" };
    static final String[] CSV_HEADER_HOLDS = {"Line Number", "Hold Name", "Hold Date", "Hold Reason", "Hold By", "Hold Release" ,"Released By", "Released Date", "Release Reason", "Release Note" };
    // END   2018/03/28 [QC#24277, MOD]
    // END 2018/01/25 S.Katsuma [QC#19579,MOD]

    // QC#21666
    /** Column names for excel download */
    // START 2018/03/22 [QC#20316, MOD]
//    static final String[] CSV_HEADER_LINES = {"Line Number", "Line Type", "Item", "Line Description", "Line Amount", "UOM", "Charge Account", "Account Description", "Unit Price", "Order Qty", "Received Qty",
//        "Cancel Qty", "Invoiced Qty", "Already Invoiced Qty","Contract", "Dealer Code", "Serial Number", "CSMP Invoice", "Install Location" };
//    static final String[] CSV_HEADER_LINES = {"Line Number", "Line Type", "Item", "Line Description", "Line Amount", "UOM", "Charge Account", "Account Description", "Unit Price", "Order Qty", "Received Qty",
//            "Cancel Qty", "Invoiced Qty", "Already Invoiced Qty","Contract", "Dealer Code", "Serial Number", "CSMP Invoice", "Install Location", "Orig PO Price", "PO#", "Order Line #", "Vendor Return#", "Correction Date", "Received WH" };
    // START 2018/08/16 [QC#27247, MOD]
    // START 2018/09/25 [QC#28441, MOD]
//    static final String[] CSV_HEADER_LINES = {"Line Number", "Line Type", "Item", "Line Description", "Line Amount", "Unit Price", "Invoiced Qty", "Already Invoiced Qty", "Order Qty", "Received Qty", "Cancel Qty", 
//        "UOM", "Charge Account", "Account Description", "Contract", "Dealer Code", "Serial Number", "CSMP Invoice", "Install Location", "Orig PO Price", "PO#", "Order Line #", "Vendor Return#", "Correction Date", "Received WH" };
    // START 2020/03/23 [QC#53413, MOD]
//    static final String[] CSV_HEADER_LINES = {"#", "Line Number", "Line Type", "Item", "Line Description", "Line Amount", "Unit Price", "Invoiced Qty", "Already Invoiced Qty", "Order Qty", "Received Qty", "Cancel Qty", 
//        "Charge Account", "Account Description", "Contract", "Dealer Code", "Serial Number", "CSMP Invoice", "Install Location", "Orig PO Price", "PO#", "Order Line #", "Vendor Return#", "Correction Date", "Received WH" };    
    // START 2022/03/28 D.Mamaril [QC#59862, MOD]
    //static final String[] CSV_HEADER_LINES = {"#", "Line Number", "Line Type", "Item", "Line Description", "Line Amount", "Unit Price", "Invoiced Qty", "Already Invoiced Qty", "Order Qty", "Received Qty", "Cancel Qty", 
    //    "Charge Account", "Account Description", "Contract", "Dealer Code", "Serial Number", "CSMP Invoice", "Install Location", "Orig PO Price", "PO#", "PO Date", "PO Amount", "Receipt#", "RWS#", "Order Line #", "Vendor Return#", "Correction Date", "Received WH" };
    static final String[] CSV_HEADER_LINES = {"#", "Line Number", "Line Type", "Item", "Line Description", "Line Amount", "Unit Price", "Invoiced Qty", "Already Invoiced Qty", "Invoice Qty on HOLD", "Order Qty", "Received Qty", "Cancel Qty",
        "Charge Account", "Account Description", "Contract", "Dealer Code", "Serial Number", "CSMP Invoice", "Install Location", "Orig PO Price", "PO#", "PO Date", "PO Amount", "Receipt#", "RWS#", "Order Line #", "Vendor Return#", "Correction Date", "Received WH" };
    // END 2022/03/28 D.Mamaril [QC#59862, MOD]
    // END 2020/03/23 [QC#53413, MOD]
    // END 2018/09/25 [QC#28441, MOD]
    // END   2018/08/16 [QC#27247, MOD]
    // END   2018/03/22 [QC#20316, MOD]

    /** Column names for excel download */
    static final String[] CSV_HEADER_DISTRIBUTIONS = {"Line Number", "Distribution Line Number", "Date", "Debit", "Credit", "Account Description", "Account Code" };

    // VAR_CHAR_CONST
    /** VAR_CHAR_CONST : NFBL2040_AP_INV_CATG_CD */
    static final String VARCHAR_CONST_NFBL2040_AP_INV_CATG_CD = "NFBL2040_AP_INV_CATG_CD";

    /** VAR_CHAR_CONST_NM : */
    static final String CONST_NM_NFBL2040_THEREFORE_CATG_LIST = "NFBL2040_THEREFORE_CATG_LIST";

    /** VAR_CHAR_CONST_NM : NFBL2040_NOT_DPLY_AP_LINE_TP */
    static final String CONST_NM_NFBL2040_NOT_DPLY_AP_LINE_TP = "NFBL2040_NOT_DPLY_AP_LINE_TP";

    /** DB Item Column Name */
    public static final String SPLY_COA_CMPY_CD = "SPLY_COA_CMPY_CD";

    /** DB Item Column Name */
    public static final String SPLY_COA_BR_CD = "SPLY_COA_BR_CD";

    /** DB Item Column Name */
    public static final String SPLY_COA_CC_CD = "SPLY_COA_CC_CD";

    /** DB Item Column Name */
    public static final String SPLY_COA_ACCT_CD = "SPLY_COA_ACCT_CD";

    /** DB Item Column Name */
    public static final String SPLY_COA_PROD_CD = "SPLY_COA_PROD_CD";

    /** DB Item Column Name */
    public static final String SPLY_COA_CH_CD = "SPLY_COA_CH_CD";

    /** DB Item Column Name */
    public static final String SPLY_COA_AFFL_CD = "SPLY_COA_AFFL_CD";

    /** DB Item Column Name */
    public static final String SPLY_COA_PROJ_CD = "SPLY_COA_PROJ_CD";

    /** DB Item Column Name */
    public static final String SPLY_COA_EXTN_CD = "SPLY_COA_EXTN_CD";

    /** Business ID */
    static final String BIZ_ID = "NFBL2040";

    /** Attachments Screen Grouping Text : AP Vendor Code */
    public static final String PARAMS_AP_VND_CD_KEY = "AP_VND_CD";

    /** Attachments Screen Grouping Text : AP Vendor Invoice Number */
    public static final String PARAMS_AP_VND_INV_NUM_KEY = "AP_VND_INV_NUM";

    /** Attachments Screen Function Name */
    public static final String PARAMS_FUNCTION_NAME = "Invoice Entry Attachments";

    /** Therefore Attach API Business ID : NFZC505001 */
    public static final String THEREFORE_ATT_BIZ_ID = "NFZC505001";

    /** detachTherefore return code error : 2000 */
    public static final String API_RETURN_CD_ERROR = "2000";
    
    /** DB Item Column Name */
    static final String PO_ORD_NUM = "PO_ORD_NUM"; // PO Number

    // START 2017/12/25 J.Kim [QC#23059,ADD]
    /** Screen ID */
    static final String SCREEN_ID = "NFBL2040Scrn00_Refresh";
    // END 2017/12/25 J.Kim [QC#23059,ADD]

    // START 2017/12/19 [QC#23022, ADD]
    /** DB Item Column Name : AP_INV_CATG_CD*/
    static final String AP_INV_CATG_CD = "AP_INV_CATG_CD";

    /** MDSE Code : FREIGHT */
    public static final String FREIGHT = "FREIGHT";
    // START 2018/03/07 J.Kim [QC#24636,ADD]
    /** MDSE Code : TAX */
    public static final String TAX = "TAX";
    // END 2018/03/07 J.Kim [QC#24636,ADD]

    /** VAR_CHAR_CONST_NM : EMPTY_MDSE_AP_INV_CATG_CD */
    public static String CONST_NM_EMPTY_MDSE_AP_INV_CATG_CD = "EMPTY_MDSE_AP_INV_CATG_CD";
    // START 2017/12/19 [QC#23022, ADD]

    // START 2018/02/02 [QC#21703, ADD]
    /** VAR_CHAR_CONST_NM : NFBL2040_CM_VND_PMT_TERM_CD */
    public static String CONST_NM_NFBL2040_CM_VND_PMT_TERM_CD = "NFBL2040_CM_VND_PMT_TERM_CD";
    // END   2018/02/02 [QC#21703, ADD]

    // START 2018/02/23 [QC#23505, ADD]
    /** AP_VND_INV_SQ_NUM format **/
    public static final String AP_VND_INV_SQ_NUMFORMAT = "%02d";
    
    /** DB Item Column Name : AP_VND_INV_SQ_NUM */
    public static String AP_VND_INV_SQ_NUM = "AP_VND_INV_SQ_NUM";

    /** DB Item Column Name : LINE_AP_VND_INV_SQ_NUM */
    public static String LINE_AP_VND_INV_SQ_NUM = "LINE_AP_VND_INV_SQ_NUM";

    /** DB Item Column Name : LINE_PO_NUM */
    public static String LINE_PO_NUM = "LINE_PO_NUM";

    /** DB Item Column Name : INV_CRCT_DT */
    public static String INV_CRCT_DT = "INV_CRCT_DT";

    /** Map Key Name : XX_LINE_TP_CD */
    public static String XX_LINE_TP_CD = "XX_LINE_TP_CD";

    /** XX_LINE_TP_CD_VALID */
    public static String XX_LINE_TP_CD_VALID = "V";

    /** XX_LINE_TP_CD_INVALID */
    public static String XX_LINE_TP_CD_INVALID = "I";

    /** XX_LINE_TP_CD_CREDIT */
    public static String XX_LINE_TP_CD_CREDIT = "C";
    // END   2018/02/23 [QC#23505, ADD]

    // START 2018/05/07 [QC#25828, ADD]
    /** DB Item Column Name : PD_ENT_DEAL_NET_UNIT_PRC_AMT */
    public static String PD_ENT_DEAL_NET_UNIT_PRC_AMT = "PD_ENT_DEAL_NET_UNIT_PRC_AMT";
    // END   2018/05/07 [QC#25828, ADD]

    // START 2018/02/23 [QC#24089, ADD]
    /** DB Item Column Name : PO_LINE_STS_CD */
    public static String PO_LINE_STS_CD = "PO_LINE_STS_CD";
    // END   2018/02/23 [QC#24089, ADD]

    // START 2018/03/22 [QC#20316, ADD]
    /** DB Item Column Name */
    static final String CR_COA_CMPY_CD = "CR_COA_CMPY_CD";

    /** DB Item Column Name */
    static final String CR_COA_BR_CD = "CR_COA_BR_CD";

    /** DB Item Column Name */
    static final String CR_COA_CC_CD = "CR_COA_CC_CD";

    /** DB Item Column Name */
    static final String CR_COA_ACCT_CD = "CR_COA_ACCT_CD";

    /** DB Item Column Name */
    static final String CR_COA_PROD_CD = "CR_COA_PROD_CD";

    /** DB Item Column Name */
    static final String CR_COA_CH_CD = "CR_COA_CH_CD";

    /** DB Item Column Name */
    static final String CR_COA_AFFL_CD = "CR_COA_AFFL_CD";

    /** DB Item Column Name */
    static final String CR_COA_PROJ_CD = "CR_COA_PROJ_CD";

    /** DB Item Column Name */
    static final String CR_COA_EXTN_CD = "CR_COA_EXTN_CD";

    /** DB Item Column Name */
    static final String AP_INV_TP_CD = "AP_INV_TP_CD";

    /** DB Item Column Name */
    static final String DISP_PO_DTL_LINE_NUM = "DISP_PO_DTL_LINE_NUM";

    /** AP_VND_INV_LINE_NUM_FORMAT format **/
    public static final String AP_VND_INV_LINE_NUM_FORMAT = "%04d";
    // END   2018/03/22 [QC#20316, ADD]

    // START 2018/03/22 [QC#24277, ADD]
    /** PO_ORD_DTL_LINE_NUM_000 */
    public static final String PO_ORD_DTL_LINE_NUM_000 = "000";

    /** EMPTY_AP_VND_INV_LINE_NUM */
    public static final String EMPTY_AP_VND_INV_LINE_NUM = "";

    /** AP_VND_INV_LINE_NUM_0000 */
    public static final String AP_VND_INV_LINE_NUM_0000 = "0000";
    // END   2018/03/22 [QC#24277, ADD]

    // START QC#25902,QC#25190,QC#25141
    /** DB Item Column Name */
    static final String VND_RTRN_NUM = "VND_RTRN_NUM"; // Vendor Return Number
    /** DB Item Column Name : LINE_VND_RTRN_NUM */
    public static String LINE_VND_RTRN_NUM = "LINE_VND_RTRN_NUM";
    /** XX_LINE_TP_CD_NO_CHANGE */
    public static String XX_LINE_TP_CD_NO_CHANGE = "N";
    /** MDSE Code : MISC */
    public static final String MISC = "MISC";
    // END QC#25902,QC#25190,QC#25141

    // START 2018/08/02 [QC#27025-1, ADD]
    public static final String EZUPTIME = "EZUPTIME";
    public static final String EZUPTIMEZONE = "EZUPTIMEZONE";
    // END   2018/08/02 [QC#27025-1, ADD]

    // START 2018/08/15 [QC#27029-1, ADD]
    public static final String VRD_ENT_DEAL_NET_UNIT_PRC_AMT = "VRD_ENT_DEAL_NET_UNIT_PRC_AMT";
    // START 2018/08/15 [QC#27029-1, ADD]

    // START 2018/11/20 [QC#28660, ADD]
    /**
     * Please cancel credit memo associated with this invoice, if you would like to submit this invoice.
     */
    public static final String NFBM0286E = "NFBM0286E";
    
    /** VARCHAR_CONST_AUTO_PMT_HLD_REL_RSN_CD */ 
    public static final String VARCHAR_CONST_AUTO_PMT_HLD_REL_RSN_CD = "AUTO_PMT_HLD_REL_RSN_CD";
    // END   2018/11/20 [QC#28660, ADD]

   // START 2022/05/27 D.Mamaril [QC#60084, ADD]
   /**
    * Do not enter POs with different Supplier Numbers.
    */
   public static final String NFBM0296E = "NFBM0296E";
   // END 2022/05/27 D.Mamaril [QC#60084, ADD]
}
