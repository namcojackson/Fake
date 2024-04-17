/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB138001.constant;

/**
 * <pre>
 * NPAB138001:PR Import
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   CITS            T.Kikuhara      Create          N/A
 * 2016/03/24   CSAI            K.Lee           Update          QC#5959
 * 2016/04/12   CSAI            K.Lee           Update          QC#6181
 * 2016/05/03   CSAI            K.Lee           Update          QC#7802
 * 01/27/2017   CITS            K.Ogino         Update          QC#11314
 * 03/07/2017   CITS            T.Kikuhara      Update          QC#15983
 * 07/04/2017   CITS            Y.Iwasaki       Update          QC#19742
 * 08/23/2017   CITS            H.Naoi          Update          Sol#097(QC#18398)
 * 04/04/2018   CITS            T.Wada          Update          QC#21170
 * 04/13/2018   CITS            K.Ogino         Update          QC#25411
 * 10/09/2018   CITS            T.Tokutomi      Update          QC#28268
 * 01/19/2023   CITS            A.Cullano       Update          QC#61081
 * 10/11/2023   CITS            F.Komaki        Update          QC#61870
 *</pre>
 */
public class NPAB138001Constant {

    /**
     * BATCH_PROGRAM_ID(NPAB1380)
     */
    public static final String BATCH_PROGRAM_ID = "NPAB1380";

    /** . */
    public static final int LENGTH_EIGHT = 8;

    /** . */
    public static final int SIZE_400 = 400;

    /** . */
    public static final String NPZM0200E = "NPZM0200E";

    /** . */
    public static final String NPAM1173E = "NPAM1173E";

    /** . */
    public static final String NSBM0069E = "NSBM0069E";

    /** . */
    public static final String NPZM0268E = "NPZM0268E";

    /** Failed to update. [@] */
    public static final String NPAM1171E = "NPAM1171E";

    // 
    /** E-mail address is not registered in S21. */
    public static final String NPAM1608E = "NPAM1608E";

    /** . */
    public static final String GETPRINTERFACE = "getPrInterface";

    // START 2023/01/19 A.Cullano [QC#61081, ADD]
    /** SQL Function name to check if PR exist for a CPO#*/
    public static final String CHECKEXISTPR = "checkExistPR";
    // END 2023/01/19 A.Cullano [QC#61081, ADD]

    /** . */
    public static final String MDSE_CD = "MDSE_CD";

    /** . */
    public static final String PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** . */
    public static final String PROCR_TP_CD = "PROCR_TP_CD";

    /** . */
    public static final String SRC_RTL_WH_CD = "SRC_RTL_WH_CD";

    /** . */
    public static final String SRC_RTL_SWH_CD = "SRC_RTL_SWH_CD";

    /** . */
    public static final String VND_CD = "VND_CD";

    /** . */
    public static final String DEAL_CCY_CD = "DEAL_CCY_CD";

    /** . */
    public static final String FUNC_AMT = "FUNC_AMT";

    /** . */
    public static final String ORD_TAKE_MDSE_CD = "ORD_TAKE_MDSE_CD";

    /** . */
    public static final String PRCH_REQ_REC_TP_CD = "PRCH_REQ_REC_TP_CD";

    /** . */
    public static final String PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";

    /** . */
    public static final String SRC_LOC_CD = "SRC_LOC_CD";

    /** . */
    public static final String PRCH_REQ_INTFC_PK = "PRCH_REQ_INTFC_PK";

    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** . */
    public static final String SALES_DATE = "SALES_DATE";

    /** . */
    public static final String PROC_STS_CD = "PROC_STS_CD";

    /** . */
    public static final String PROC_STS_CD_LIST = "PROC_STS_CD_LIST";

    /** . */
    public static final String PROC_STS_CD_IN_COMPLETED = "PROC_STS_CD_IN_COMPLETED";

    /** . */
    public static final String PROC_STS_CD_ERROR = "PROC_STS_CD_ERROR";

    /** . */
    public static final String PROC_ERR_MSG_CD = "PROC_ERR_MSG_CD";

    /** . */
    public static final String INTFC_ERR_MSG_TXT = "INTFC_ERR_MSG_TXT";

    /** . */
    public static final String PRCH_REQ_CRAT_BY_PSN_CD = "PRCH_REQ_CRAT_BY_PSN_CD";

    /** . */
    public static final String PRCH_REQ_SRC_TP_CD = "PRCH_REQ_SRC_TP_CD";

    /** . */
    public static final String PRCH_GRP_CD = "PRCH_GRP_CD";

    /** . */
    public static final String PRCH_REQ_APVL_STS_CD = "PRCH_REQ_APVL_STS_CD";

    /** . */
    public static final String FSR_NUM = "FSR_NUM";

    /** . */
    public static final String SVC_TASK_NUM = "SVC_TASK_NUM";

    /** . */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** . */
    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** . */
    public static final String CUST_ISS_PO_DT = "CUST_ISS_PO_DT";

    /** . */
    public static final String CPO_ORD_TP_CD = "CPO_ORD_TP_CD";

    /** . */
    public static final String PO_SCHD_REL_DT = "PO_SCHD_REL_DT";

    /** . */
    public static final String RQST_RCV_DT = "RQST_RCV_DT";

    /** . */
    public static final String RQST_RCV_TM = "RQST_RCV_TM";

    /** . */
    public static final String RQST_TECH_TOC_CD = "RQST_TECH_TOC_CD";

    /** . */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

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
    public static final String SHIP_TO_FIRST_REF_CMNT_TXT = "SHIP_TO_FIRST_REF_CMNT_TXT";

    /** . */
    public static final String SHIP_TO_SCD_REF_CMNT_TXT = "SHIP_TO_SCD_REF_CMNT_TXT";

    /** . */
    public static final String SHIP_TO_CTY_ADDR = "SHIP_TO_CTY_ADDR";

    /** . */
    public static final String SHIP_TO_ST_CD = "SHIP_TO_ST_CD";

    /** . */
    public static final String SHIP_TO_PROV_NM = "SHIP_TO_PROV_NM";

    /** . */
    public static final String SHIP_TO_CNTY_NM = "SHIP_TO_CNTY_NM";

    /** . */
    public static final String SHIP_TO_POST_CD = "SHIP_TO_POST_CD";

    /** . */
    public static final String SHIP_TO_CTRY_CD = "SHIP_TO_CTRY_CD";

    /** . */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** . */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** . */
    public static final String PRCH_REQ_CMNT_TXT = "PRCH_REQ_CMNT_TXT";

    /** . */
    public static final String DELY_ADDL_CMNT_TXT = "DELY_ADDL_CMNT_TXT";

    /** . */
    public static final String RCV_ADDL_CMNT_TXT = "RCV_ADDL_CMNT_TXT";

    /** . */
    public static final String PO_QLFY_CD = "PO_QLFY_CD";

    //08/21/2017 CITS H.Naoi Add Sol#097(QC#18398) START
    /** . */
    public static final String MRP_PLN_NM = "MRP_PLN_NM";
    //08/21/2017 CITS H.Naoi Add Sol#097(QC#18398) END

    /** . */
    public static final String PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /** . */
    public static final String PRCH_REQ_LINE_TP_CD = "PRCH_REQ_LINE_TP_CD";

    /** . */
    public static final String SHPG_PLN_NUM = "SHPG_PLN_NUM";

    /** . */
    public static final String CTAC_PSN_NM = "CTAC_PSN_NM";

    /** . */
    public static final String ADMIN_PSN_CD = "ADMIN_PSN_CD";

    /** . */
    public static final String SHIP_FROM_SO_NUM = "SHIP_FROM_SO_NUM";

    /** . */
    public static final String SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** . */
    public static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** . */
    public static final String CARR_CD = "CARR_CD";

    /** . */
    public static final String CARR_ACCT_NUM = "CARR_ACCT_NUM";

    /** . */
    public static final String DEST_INVTY_LOC_CD = "DEST_INVTY_LOC_CD";

    /** . */
    public static final String SRC_INVTY_LOC_CD = "SRC_INVTY_LOC_CD";

    /** . */
    public static final String PRNT_VND_CD = "PRNT_VND_CD";

    /** . */
    public static final String ASL_MDSE_CD = "ASL_MDSE_CD";

    /** . */
    public static final String PRCH_REQ_QTY = "PRCH_REQ_QTY";

    /** . */
    public static final String ORD_QTY = "ORD_QTY";

    /** . */
    public static final String CUST_UOM_CD = "CUST_UOM_CD";

    /** . */
    public static final String PRCH_REQ_DPLY_QTY = "PRCH_REQ_DPLY_QTY";

    /** . */
    public static final String PRCH_REQ_DSPL_UOM = "PRCH_REQ_DSPL_UOM";

    /** . */
    public static final String PO_MDSE_CMPSN_TP_CD = "PO_MDSE_CMPSN_TP_CD";

    /** . */
    public static final String ROP_QTY = "ROP_QTY";

    /** . */
    public static final String MIN_ORD_QTY = "MIN_ORD_QTY";

    /** . */
    public static final String INCR_ORD_QTY = "INCR_ORD_QTY";

    /** . */
    public static final String MAX_INVTY_QTY = "MAX_INVTY_QTY";

    /** . */
    public static final String CUR_INVTY_QTY = "CUR_INVTY_QTY";

    /** . */
    public static final String CUR_INVTY_AVAL_QTY = "CUR_INVTY_AVAL_QTY";

    /** . */
    public static final String SCHD_INBD_QTY = "SCHD_INBD_QTY";

    /** . */
    public static final String SCHD_OTBD_QTY = "SCHD_OTBD_QTY";

    /** . */
    public static final String FROM_STK_STS_CD = "FROM_STK_STS_CD";

    /** . */
    public static final String TO_STK_STS_CD = "TO_STK_STS_CD";

    /** . */
    public static final String ASL_DTL_PK = "ASL_DTL_PK";

    /** . */
    public static final String UNIT_PRC_AMT = "UNIT_PRC_AMT";

    /** . */
    public static final String ASL_UNIT_PRC_AMT = "ASL_UNIT_PRC_AMT";

    // QC#21170
    /** . */
    public static final String ASL_VND_LT_DAYS_NUM = "ASL_VND_LT_DAYS_NUM";

    // 2023/10/11 QC#61870 START
    /** . */
    public static final String ASL_VND_SHIP_LT_DAYS_NUM = "ASL_VND_SHIP_LT_DAYS_NUM";

    /** . */
    public static final String RQST_SHIP_DT = "RQST_SHIP_DT";
    // 2023/10/11 QC#61870 END

    /** . */
    public static final String ENT_DEAL_NET_UNIT_PRC_AMT = "ENT_DEAL_NET_UNIT_PRC_AMT";

    /** . */
    public static final String ENT_FUNC_NET_UNIT_PRC_AMT = "ENT_FUNC_NET_UNIT_PRC_AMT";

    /** . */
    public static final String CCY_CD = "CCY_CD";

    /** . */
    public static final String EXCH_RATE = "EXCH_RATE";

    /** . */
    public static final String TRX_REF_NUM = "TRX_REF_NUM";

    /** . */
    public static final String TRX_REF_LINE_NUM = "TRX_REF_LINE_NUM";

    /** . */
    public static final String TRX_REF_LINE_SUB_NUM = "TRX_REF_LINE_SUB_NUM";

    /** . */
    public static final String PRCH_REQ_LINE_CMNT_TXT = "PRCH_REQ_LINE_CMNT_TXT";

    /** . */
    public static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";

    /** . */
    public static final String SPCL_INSTN_CMNT_TXT = "SPCL_INSTN_CMNT_TXT";

    /** . */
    public static final String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** . */
    public static final String SVC_MACH_SER_NUM = "SVC_MACH_SER_NUM";

    /** . */
    public static final String SER_NUM = "SER_NUM";

    /** . */
    public static final String VND_INVTY_LOC_CD = "VND_INVTY_LOC_CD";

    /** . */
    public static final String BASE_PKG_UOM_CD = "BASE_PKG_UOM_CD";

    /** . */
    public static final String PRIM_PKG_UOM_FLG = "PRIM_PKG_UOM_FLG";

    /** . */
    public static final String FRT_CHRG_TO_CD = "FRT_CHRG_TO_CD";

    /** . */
    public static final String FRT_CHRG_METH_CD = "FRT_CHRG_METH_CD";

    /** . */
    public static final String FRT_COND_CD = "FRT_COND_CD";

    /** SPLY_ITEM_NUM */
    public static final String SPLY_ITEM_NUM = "SPLY_ITEM_NUM";

    /** SLS_DT. */
    public static final String SLS_DT = "SLS_DT";

    /** MIN_START_DATE. */
    public static final String MIN_START_DATE = "MIN_START_DATE";

    /** MAX_START_DATE. */
    public static final String MAX_START_DATE = "MAX_START_DATE";

    /** MIN_START_DATE_VAL. */
    public static final String MIN_START_DATE_VAL = "10000101";

    /** MAX_START_DATE_VAL. */
    public static final String MAX_START_DATE_VAL = "99991231";

    /** GET_ASL_INFO_BY_VND. */
    public static final String GET_ASL_INFO_BY_VND = "getAslInfoByVnd";

    /** VAR_CHAR_CONST_KEY_NPAL1380_DIVIDE_GRP_COLUMNS */
    public static final String VAR_CHAR_CONST_KEY_NPAB1380_DIVIDE_GRP_COLUMNS = "NPAB1380_DIVIDE_GRP_COLUMNS";

    /** VAR_CHAR_CONST_KEY_NPAB1380_MRP_TPS */
    public static final String VAR_CHAR_CONST_KEY_NPAB1380_MRP_TPS = "NPAB1380_MRP_TPS";

    /** Mail Item (${batchId}) */
    public static final String MAIL_ITEM_BATCH_ID = "batchId";

    /** Mail Item (${errDate}) */
    public static final String MAIL_ITEM_ERR_DATE = "errDate";

    /** Mail Item (${message}) */
    public static final String MAIL_ITEM_ERR_MSG = "message";

    // QC#18689-Sol#303
    /** Mail Item (${trxRefNum}) */
    public static final String MAIL_ITEM_TRX_REF_NUM = "trxRefNum";

   /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

   /** Mail Date Format : MM/dd/yyyy HH:mm:ss.SSS */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

   /** Mail Group From(FROM0005) */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

   /** Mail Group From */
    public static final String MAIL_GROUP_KEY_FROM = "NPA";

   /** Mail Group To(NPAB1670) */
    public static final String MAIL_GROUP_ID_TO = "NPAB1380";

   /** Mail Template ID(NPAB1380M001) */
    public static final String MAIL_TEMPLATE_ID_01 = "NPAB1380M001";

    // QC#18689-Sol#303
    /** Mail Template ID(NPAB1380M002) */
    public static final String MAIL_TEMPLATE_ID_02 = "NPAB1380M002";

   /** [@] is not registered.(@) */
    public static final String NPAM1478E = "NPAM1478E";

    /** The e-mail template <template ID: [@]> cannot be obtained. */
    public static final String NPAM1331E = "NPAM1331E";

    /** PR import failed. The maximum number of statements in the same group is [@]. Please correct the source [@] of the request.*/
    public static final String NPAM1661E = "NPAM1661E";

    /** NPZC103001PMsg prchReqInfo Length */
    public static final String PRCH_REQ_LENGTH = "999";

    /** Space */
    public static final String SPACE_VALUE = " ";

    /** Mail Detail Length */
    public static final int[] MAIL_DETAIL_LENGTH = new int[]{25, 15, 10, 10, 20, 15, 400};

    /** Mail Header */
    public static final String[] MAIL_DETAIL_HEADER = new String[]{"Transaction Source", "Order#", "Line#", "Sub#", "Order Item", "Order Qty", "Error Message"};

    /** Mail Detail Align Left */
    public static final int ALIGN_LEFT = 0;

    /** Mail Detail Align Right */
    public static final int ALIGN_RIGHT = 1;

    /** VAR_CHAR_CONST_ROSS_VND_CD */
    public static final String VAR_CHAR_CONST_ROSS_VND_CD = "ROSS_VND_CD";

    /** . */
    public static final String NPBL0020_ID = "NPBL0020";

    /** . */
    public static final String DEF_COA_AFFL_CD = "000";

    /** . */
    public static final String DEF_COA_BR_CD = "000";

    /** . */
    public static final String DEF_COA_CH_CD = "000";

    /** . */
    public static final String DEF_COA_CC_CD = "000000";

    /** . */
    public static final String DEF_COA_ACCT_CD = "00000000";

    /** . */
    public static final String DEF_COA_PROJ_CD = "0000";

    /** . */
    public static final String DEF_COA_EXTN_CD = "000";

    /** . */
    public static final String DEF_COA_PROD_CD = "00000000";

    /** . */
    public static final String DEST_RTL_WH_CD = "DEST_RTL_WH_CD";

    /** . */
    public static final String COA_BR_CD = "COA_BR_CD";

    /** . */
    public static final String COA_PROJ_CD = "COA_PROJ_CD";

    /** . */
    public static final String COA_ACCT_CD = "COA_ACCT_CD";

    /** . */
    public static final String GET_ACCOUNT = "getAccount";

    // QC#18689-Sol#303. Technician mail data.
    /** . */
    public static final String TECH_PSN_CD = "TECH_PSN_CD";

    /** . */
    public static final String TECH_MAIL_ADDR = "TECH_MAIL_ADDR";

    /** . */
    public static final String TECH_MAIL_ERR_MSG = "TECH_MAIL_ERR_MSG";

    /** . */
    public static final String TECH_MAIL_TRX_REF_NUM = "TECH_MAIL_TRX_REF_NUM";

    /** . */
    public static final String PRCH_REQ_STS_CD = "PRCH_REQ_STS_CD";

    /** . */
    public static final String PRCH_REQ_LINE_STS_CD = "PRCH_REQ_LINE_STS_CD";

    /** . */
    public static final String GET_ENTERD_DS_PR = "getEnteredDropshipPr";

    /** NPAB1380_PR_CANCEL_APVL_STATUS */
    public static final String NPAB1380_PR_CANCEL_APVL_STATUS = "NPAB1380_PR_CANCEL_APVL_STATUS";

    /** prCancelApvlCds */
    public static final String PR_CANCEL_APVL_STATUS_CD = "prCancelApvlStsCd";

    // QC#28268 ADD.
    /** VAR_CHAR_CONST_NPAB1380_CPO_MRG_PLN_GRP_CD */
    public static final String VAR_CHAR_CONST_NPAB1380_CPO_MRG_PLN_GRP_CD = "NPAB1380_CPO_MRG_PLN_GRP_CD";

    // QC#28268 ADD.
    /** VAR_CHAR_CONST_NPAB1380_ROSS_MRG_PLN_GRP_CD */
    public static final String VAR_CHAR_CONST_NPAB1380_ROSS_MRG_PLN_GRP_CD = "NPAB1380_ROSS_MRG_PLN_GRP_CD";

    // QC#28268 ADD.
    /** VAR_CHAR_CONST_NPAB1380_MRP_MRG_PLN_GRP_CD */
    public static final String VAR_CHAR_CONST_NPAB1380_MRP_MRG_PLN_GRP_CD = "NPAB1380_MRP_MRG_PLN_GRP_CD";

    // QC#28268 ADD.
    /** VAR_CHAR_CONST_DROP_SHIP_RTL_WH_CD */
    public static final String VAR_CHAR_CONST_DROP_SHIP_RTL_WH_CD = "DROP_SHIP_RTL_WH_CD";

    // QC#28268 ADD.
    /** NPAM1537E:[@] does not exist. Table:[@], Search Key:[@] */
    public static final String NPAM1537E = "NPAM1537E";

    /** . */
    public static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** . */
    public static final String CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";

    /** . */
    public static final String GET_ENTERD_EX_PR = "getEnteredExternalPr";
}
