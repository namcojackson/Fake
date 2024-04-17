/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPB.NPBB001001.constant;

/**
 * <pre>
 * NPBB001001:Inventory Request Release Batch
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/22   CITS            T.Kikuhara      Create          N/A
 * 2016/04/19   CITS            K.Ogino         Update          QC#7124
 * 2016/04/26   CSAI            K.Lee           Update          QC#7347
 * 2016/06/20   CSAI            D.Fukaya        Update          QC#8134
 * 2016/06/30   CSAI            D.Fukaya        Update          QC#7735
 * 2016/12/01   CITS            Y.IWASAKI       Update          QC#15584
 * 2017/03/03   CITS            R.Shimamoto     Update          QC#17395
 * 2017/09/01   CITS            T.Kikuhara      Create          QC#18365(L3)
 * 2018/02/02   Hitachi         Y.Takeno        Update          QC#21703
 * 2018/02/26   CITS            K.Ogino         Update          QC#22378
 * 2018/03/27   CITS            K.Fukumura      Update          QC#22517
 * 2019/02/26   Fujitsu         S.Takami        Update          QC#30451
 * 2019/09/11   CITS            K.Ogino         Update          QC#52809
 * 2019/10/03   CITS            M.Naito         Update          QC#52809
 * 2022/03/14   Hitachi         A.Kohinata      Update          QC#59780
 *</pre>
 */
public class NPBB001001Constant {

    /** . */
    public static final String NPZM0200E = "NPZM0200E";

    /** . */
    public static final String NPAM1173E = "NPAM1173E";

    /** . */
    public static final String NPAM1323E = "NPAM1323E";

    /** . */
    public static final String NPAM1370M = "NPAM1370M";

    /** [@] does not exist in Master. */
    public static final String NPAM0076E = "NPAM0076E";

    /** The Base Component does not exists in config. */
    public static final String NWZM1482E = "NWZM1482E";

    /** Failed to insert. [@] */
    public static final String NPAM1172E = "NPAM1172E";

    // add start 2022/03/14 QC#59780
    /** Failed to update. [@] */
    public static final String NPAM1171E = "NPAM1171E";
    // add end 2022/03/14 QC#59780

    /** . */
    public static final String NMZC6100 = "NMZC6100 Customer Information Get API";

    /** . */
    public static final String NLZC0030 = "NLZC0030 Inventory Order API";

    /** . */
    public static final String NWZC1070 = "NWZC1070 Allocation for non CPO API";

    /** . */
    public static final String NWZC0030 = "NWZC0030 Shipping Plan Update API";

    /** . */
    public static final String NLZC2050 = "NLZC2050 SO API";

    /** . */
    public static final String NLZC3030 = "NLZC3030 DS SO API";

    /** . */
    public static final String NSZC0010 = "NSZC0010 Machine Master Update API";

    /** . */
    public static final String NLZC2100 = "NLZC2100 S21 DC SO Confirmation API";

    /** . */
    public static final int MAX_CMNT_LENGTH = 60;

    /** . */
    public static final String START_LINE_NUM = "001";

    /** . */
    public static final String SO_COMMENT = "20";

    /** . */
    public static final String LINE_COMMENT = "60";

    /** . */
    public static final String IR = "IR";

    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** . */
    public static final String SALES_DATE = "SALES_DATE";

    /** . */
    public static final String INVTY_REQ_TP_CD = "INVTY_REQ_TP_CD";

    /** . */
    public static final String NPBB0010_REL_PRCH_REQ_STS_CD = "NPBB0010_REL_PRCH_REQ_STS_CD";

    /** . */
    public static final String INVTY_REQ_DEF_SHPG_SVC_LVL_CD = "INVTY_REQ_DEF_SHPG_SVC_LVL_CD";

    /** . */
    public static final String PRCH_REQ_REC_TP_CD = "PRCH_REQ_REC_TP_CD";

    /** . */
    public static final String PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** . */
    public static final String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** . */
    public static final String PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /** . */
    public static final String IN_COMPLETED = "IN_COMPLETED";

    /** . */
    public static final String ERROR = "ERROR";

    /** . */
    public static final String PRCH_REQ_STS_CD = "PRCH_REQ_STS_CD";

    /** . */
    public static final String PRCH_REQ_SRC_TP_CD = "PRCH_REQ_SRC_TP_CD";

    /** . */
    public static final String PRCH_REQ_APVL_FLG = "PRCH_REQ_APVL_FLG";

    /** . */
    public static final String TECH_RTRN_TP_CD = "TECH_RTRN_TP_CD";

    /** . */
    public static final String MISS_PRT_LINE_TP_CD = "MISS_PRT_LINE_TP_CD";

    /** . */
    public static final String MISS_PRT_ORIG_LINE_STS_CD = "MISS_PRT_ORIG_LINE_STS_CD";

    /** . */
    public static final String PRCH_REQ_LINE_TP_CD = "PRCH_REQ_LINE_TP_CD";

    /** . */
    public static final String PRCH_REQ_LINE_STS_CD = "PRCH_REQ_LINE_STS_CD";

    /** . */
    public static final String PRCH_REQ_LINE_CMNT_TXT = "PRCH_REQ_LINE_CMNT_TXT";

    /** . */
    public static final String INVTY_ORD_TP_CD = "INVTY_ORD_TP_CD";

    /** . */
    public static final String SER_NUM = "SER_NUM";

    /** . */
    public static final String SO_NUM = "SO_NUM";

    /** . */
    public static final String SO_SLP_NUM = "SO_SLP_NUM";

    /** . */
    public static final String SRC_RTL_WH_CD = "SRC_RTL_WH_CD";

    /** . */
    public static final String SRC_RTL_SWH_CD = "SRC_RTL_SWH_CD";

    /** . */
    public static final String SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /** . */
    public static final String TRX_HDR_NUM = "TRX_HDR_NUM";

    /** . */
    public static final String TRX_LINE_NUM = "TRX_LINE_NUM";

    /** . */
    public static final String TRX_LINE_SUB_NUM = "TRX_LINE_SUB_NUM";

    /** . */
    public static final String TRX_CD = "TRX_CD";

    /** . */
    public static final String TRX_RSN_CD = "TRX_RSN_CD";

    /** . */
    public static final String INBD_OTBD_CD = "INBD_OTBD_CD";

    /** . */
    public static final String SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** . */
    public static final String RQST_RCV_DT = "RQST_RCV_DT";

    /** . */
    public static final String DELY_ADDL_CMNT_TXT = "DELY_ADDL_CMNT_TXT";

    /** . */
    public static final String PRCH_REQ_CMNT_TXT = "PRCH_REQ_CMNT_TXT";

    /** . */
    public static final String TRX_SRC_TP_CD = "TRX_SRC_TP_CD";

    /** . */
    public static final String MDSE_CD = "MDSE_CD";

    /** . */
    public static final String MDSE_NM = "MDSE_NM";

    /** . */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** . */
    public static final String SRC_INVTY_LOC_CD = "SRC_INVTY_LOC_CD";

    /** . */
    public static final String FROM_STK_STS_CD = "FROM_STK_STS_CD";

    /** . */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** . */
    public static final String SHIP_QTY = "SHIP_QTY";

    /** . */
    public static final String SHPG_QTY = "SHPG_QTY";

    /** . */
    public static final String SHPG_BAL_QTY = "SHPG_BAL_QTY";

    /** . */
    public static final String PRCH_REQ_QTY = "PRCH_REQ_QTY";

    /** . */
    public static final String PO_ORD_NUM = "PO_ORD_NUM";

    /** . */
    public static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** . */
    public static final String TRX_REF_NUM = "TRX_REF_NUM";

    /** . */
    public static final String TRX_REF_LINE_NUM = "TRX_REF_LINE_NUM";

    /** . */
    public static final String TRX_REF_LINE_SUB_NUM = "TRX_REF_LINE_SUB_NUM";

    /** . */
    public static final String COA_CMPY_CD = "COA_CMPY_CD";

    /** . */
    public static final String COA_BR_CD = "COA_BR_CD";

    /** . */
    public static final String COA_ACCT_CD = "COA_ACCT_CD";

    /** . */
    public static final String COA_PROD_CD = "COA_PROD_CD";

    /** . */
    public static final String COA_CH_CD = "COA_CH_CD";

    /** . */
    public static final String COA_CC_CD = "COA_CC_CD";

    /** . */
    public static final String COA_AFFL_CD = "COA_AFFL_CD";

    /** . */
    public static final String COA_EXTN_CD = "COA_EXTN_CD";

    /** . */
    public static final String COA_PROJ_CD = "COA_PROJ_CD";

    /** . */
    public static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** . */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** . */
    public static final String INSTL_BASE_CTRL_FLG = "INSTL_BASE_CTRL_FLG";

    /** . */
    public static final String CARR_CD = "CARR_CD";

    /** . */
    public static final String CARR_ACCT_NUM = "CARR_ACCT_NUM";

    /** . */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /** . */
    public static final String DEST_RTL_WH_CD = "DEST_RTL_WH_CD";

    /** . */
    public static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    /** . */
    public static final String SHIP_TO_ADDL_LOC_NM = "SHIP_TO_ADDL_LOC_NM";

    /** . */
    public static final String SHIP_TO_FIRST_LINE_ADDR = "SHIP_TO_FIRST_LINE_ADDR";

    /** . */
    public static final String SHIP_TO_SCD_LINE_ADDR = "SHIP_TO_SCD_LINE_ADDR";

    /** . */
    public static final String SHIP_TO_THIRD_LINE_ADDR = "SHIP_TO_THIRD_LINE_ADDR";

    /** . */
    public static final String SHIP_TO_FRTH_LINE_ADDR = "SHIP_TO_FRTH_LINE_ADDR";

    /** . */
    public static final String SHIP_TO_CTY_ADDR = "SHIP_TO_CTY_ADDR";

    /** . */
    public static final String SHIP_TO_CNTY_NM = "SHIP_TO_CNTY_NM";

    /** . */
    public static final String SHIP_TO_PROV_NM = "SHIP_TO_PROV_NM";

    /** . */
    public static final String SHIP_TO_ST_CD = "SHIP_TO_ST_CD";

    /** . */
    public static final String SHIP_TO_POST_CD = "SHIP_TO_POST_CD";

    /** . */
    public static final String SHIP_TO_CTRY_CD = "SHIP_TO_CTRY_CD";

    /** . */
    public static final String SHIP_TO_FIRST_REF_CMNT_TXT = "SHIP_TO_FIRST_REF_CMNT_TXT";

    /** . */
    public static final String SHIP_TO_SCD_REF_CMNT_TXT = "SHIP_TO_SCD_REF_CMNT_TXT";

    /** . */
    public static final String SCE_SRC_TP_CD = "SCE_SRC_TP_CD";

    /** . */
    public static final String SRC_INVTY_CD = "SRC_INVTY_CD";

    /** . */
    public static final String SHPG_PLN_NUM = "SHPG_PLN_NUM";

    /** . */
    public static final String VND_CD = "VND_CD";

    /** . */
    public static final String ENT_DEAL_NET_UNIT_PRC_AMT = "ENT_DEAL_NET_UNIT_PRC_AMT";

    // START 2019/02/26 S.Takami [QC#30451,ADD]
    /** . */
    public static final String ENT_PO_DTL_DEAL_NET_AMT = "ENT_PO_DTL_DEAL_NET_AMT";
    // END 2019/02/26 S.Takami [QC#30451,ADD]

    /** . */
    public static final String ITRL_ORD_PROC_FLG = "ITRL_ORD_PROC_FLG";

    /** . */
    public static final String DS_ORD_TP_CD = "DS_ORD_TP_CD";

    /** . */
    public static final String DS_ORD_CATG_CD = "DS_ORD_CATG_CD";

    /** . */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    // START 2019/10/03 M.Naito [QC#52809,ADD]
    /** . */
    public static final String DS_ACCT_NUM = "DS_ACCT_NUM";
    // END 2019/10/03 M.Naito [QC#52809,ADD]

    /** . */
    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** . */
    public static final String CUST_ISS_PO_DT = "CUST_ISS_PO_DT";

    /** . */
    public static final String LOC_NM = "LOC_NM";

    /** . */
    public static final String ADDL_LOC_NM = "ADDL_LOC_NM";

    /** . */
    public static final String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /** . */
    public static final String SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /** . */
    public static final String THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /** . */
    public static final String FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /** . */
    public static final String CTY_ADDR = "CTY_ADDR";

    /** . */
    public static final String ST_CD = "ST_CD";

    /** . */
    public static final String PROV_NM = "PROV_NM";

    /** . */
    public static final String CNTY_NM = "CNTY_NM";

    /** . */
    public static final String POST_CD = "POST_CD";

    /** . */
    public static final String CTRY_CD = "CTRY_CD";

    /** . */
    public static final String FIRST_REF_CMNT_TXT = "FIRST_REF_CMNT_TXT";

    /** . */
    public static final String SCD_REF_CMNT_TXT = "SCD_REF_CMNT_TXT";

    /** . */
    public static final String ADMIN_PSN_CD = "ADMIN_PSN_CD";

    /** . */
    public static final String FRT_COND_CD = "FRT_COND_CD";

    /** . */
    public static final String FRT_CHRG_TO_CD = "FRT_CHRG_TO_CD";

    /** . */
    public static final String FRT_CHRG_METH_CD = "FRT_CHRG_METH_CD";

    /** . */
    public static final String ITRL_TRNSF_DEF_SLS_REP_TOC_CD = "ITRL_TRNSF_DEF_SLS_REP_TOC_CD";

    /** . */
    public static final String CONFIG_TP_CD = "CONFIG_TP_CD";

    /** . */
    public static final String CONFIG_FLG = "CONFIG_FLG";

    /** . */
    public static final String FIRST_PROD_CTRL_CD = "FIRST_PROD_CTRL_CD";

    /** . */
    public static final String DEF_PRC_CATG_CD = "DEF_PRC_CATG_CD";

    /** . */
    public static final String PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";

    /** . */
    public static final String VND_INVTY_LOC_CD = "VND_INVTY_LOC_CD";

    /** . */
    public static final String DEST_INVTY_LOC_CD = "DEST_INVTY_LOC_CD";

    /** . */
    public static final String CUST_UOM_EA = "EA";

    /** . */
    public static final String PRO_NUM = "PRO_NUM";

    /** INVTY_ORD_NUM */
    public static final String INVTY_ORD_NUM = "INVTY_ORD_NUM";

    /** INVTY_ORD_LINE_NUM */
    public static final String INVTY_ORD_LINE_NUM = "INVTY_ORD_LINE_NUM";

    /** DS_COND_CONST_GRP_ID */
    public static final String DS_COND_CONST_GRP_ID = "DS_COND_CONST_GRP_ID";

    /** DS_COND_CONST_VAL_TXT_01 */
    public static final String DS_COND_CONST_VAL_TXT_01 = "DS_COND_CONST_VAL_TXT_01";

    /** DS_COND_CONST_VAL_TXT_02 */
    public static final String DS_COND_CONST_VAL_TXT_02 = "DS_COND_CONST_VAL_TXT_02";

    /** MDSE_TP_CTX_TP_CD */
    public static final String MDSE_TP_CTX_TP_CD = "MDSE_TP_CTX_TP_CD";

    /** DS_IMPT_ORD_PK */
    public static final String DS_IMPT_ORD_PK = "DS_IMPT_ORD_PK";

    /** DS_IMPT_ORD_CONFIG_PK */
    public static final String DS_IMPT_ORD_CONFIG_PK = "DS_IMPT_ORD_CONFIG_PK";

    /** BILL_TO_CUST_CD */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    // START 2019/10/03 M.Naito [QC#52809,ADD]
    /** BILL_TO_CUST_ACCT_CD */
    public static final String BILL_TO_CUST_ACCT_CD = "BILL_TO_CUST_ACCT_CD";
    // END 2019/10/03 M.Naito [QC#52809,ADD]

    /** DS_ORD_POSN_NUM */
    public static final String DS_ORD_POSN_NUM = "DS_ORD_POSN_NUM";

    /** MDL_ID */
    public static final String MDL_ID = "MDL_ID";

    /** MDL_DESC_TXT */
    public static final String MDL_DESC_TXT = "MDL_DESC_TXT";

    /** DS_COND_CONST : DS_COND_CONST_GRP_ID : NPBB0010_DS_ORD_TP */
    public static final String NPBB0010_DS_ORD_TP = "NPBB0010_DS_ORD_TP";

    /** DS_COND_CONST : DS_COND_CONST_VAL_TXT_02 : EO */
    public static final String EXPENSE_ORDER = "EO";

    /** WRK_ORD_NUM */
    public static final String WRK_ORD_NUM = "WRK_ORD_NUM";

    /** WRK_ORD_DTL_LINE_NUM */
    public static final String WRK_ORD_DTL_LINE_NUM = "WRK_ORD_DTL_LINE_NUM";

    /** HAZ_MAT_FLG_N */
    public static final String HAZ_MAT_FLG_N = "HAZ_MAT_FLG_N";

    /** HAZ_MAT_FLG_Y */
    public static final String HAZ_MAT_FLG_Y = "HAZ_MAT_FLG_Y";

    /** DEF_HAZ_MAT_FLG */
    public static final String DEF_HAZ_MAT_FLG = "DEF_HAZ_MAT_FLG";

    /** TRX_SRC_TP_CD_WHOLE_SALES */
    public static final String TRX_SRC_TP_CD_WHOLE_SALES = "TRX_SRC_TP_CD_WHOLE_SALES";

    /** WH_OWNR_CD_LIST */
    public static final String WH_OWNR_CD_LIST = "whOwnrCdList";

    // QC#18365(L3) ADD START
    /** DROP_RTRN_VND_CD */
    public static final String DROP_RTRN_VND_CD = "DROP_RTRN_VND_CD";
    // QC#18365(L3) ADD END

    // START 2018/02/02  [QC#21703,ADD]
    /** THIS_MTH_TOT_STD_COST_AMT */
    public static final String THIS_MTH_TOT_STD_COST_AMT = "THIS_MTH_TOT_STD_COST_AMT";

    /** PKG_UOM_CD */
    public static final String PKG_UOM_CD = "PKG_UOM_CD";

    /** PRIM_PKG_UOM_FLG */
    public static final String PRIM_PKG_UOM_FLG = "PRIM_PKG_UOM_FLG";
    // END   2018/02/02  [QC#21703,ADD]

    /** QC#22378 SVC_ISTL_RULE_NUM */
    public static final String SVC_ISTL_RULE_NUM = "SVC_ISTL_RULE_NUM";

    // START 2019/10/03 M.Naito [QC#52809,ADD]
    /** RGTN_STS_CD */
    public static final String RGTN_STS_CD = "RGTN_STS_CD";
    // END 2019/10/03 M.Naito [QC#52809,ADD]

    // QC#22517
    /** MULTIPLE */
    public static final String SHIP_TO_LOC_MULTIPLE = "MULTIPLE";

    // QC#52809 Add
    /**
     * Display Name : SHIP_TO_CUST_CD
     */
    public static final String DISPLAY_NM_SHIP_TO_CUST_CD = "Ship To Customer Code";

    /**
     * Display Name : BILL_TO_CUST_CD
     */
    public static final String DISPLAY_NM_BILL_TO_CUST_CD = "Bill To Customer Code";

    /**
     * NPBM0004E: The relationship between '@' and '@' is incorrect.
     */
    public static final String NPBM0004E = "NPBM0004E";

    /** Blank */
    public static final String BLANK = "";
    // QC#52809 End
}
