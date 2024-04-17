/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB136001.constant;

/**
 * <pre>
 * Business ID : NPAB1360 Insourcing Batch
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/28/2015   CITS       Yasushi Nomura        Create          N/A
 * 09/26/2016   CITS       K.Ogino               Update          QC#8195
 * 08/29/2017   CITS       S.Endo                Update          Sol#406(QC#19243)
 * 05/09/2018   CITS       T.Tokutomi            Update          QC#2366
 * 08/23/2018   CITS       T.Tokutomi            Update          QC#27660
 * 02/16/2023   CITS       R.Kurahashi           Update          QC#61128
 * 09/08/2023   CITS       F.Komaki              Update          QC#61704
 * 09/12/2023   Hitachi    G.Quan                Update          QC#61589
 * 09/19/2023   Hitachi    G.Quan                Update          QC#59207
 *</pre>
 */
public class NPAB136001Constant {

    /** Batch ID */
    public static final String BATCH_ID = "NPAB136001";

    /** . */
    public static final String SELECT_LIKE_CHAR = "%";

    /** . */
    public static final String DATE_FORMAT = "MM/dd/yyyy";

    /** . */
    public static final String TIME_FORMAT = "HH:mm";

    /** . */
    public static final String TIMESTAMP_FORMAT = "MM/dd/yyyy HH:mm:ss";

    /** . */
    public static final int TIME_LEN_HHMM = 4;

    /** . */
    public static final int DATE_LEN_YYYYMMDD = 8;

    /** . */
    public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmssSSS";

    /** . */
    public static final String PLN_ITEM_INSRC_CD_SKIP = "2";

    /** . */
    public static final String PLN_ITEM_INSRC_CD_MIN = "3";

    /** . */
    public static final String PLN_ITEM_INSRC_CD_MAX = "4";

    /** . */
    public static final String PRCH_REQ_CMNT_TXT_MSG = "TECH REQUEST:  #";

    /** . */
    public static final String PRCH_REQ_REL_STS_8 = "8";

    /** Request# to Line# Space. 4 spaces */
    public static final String REQ_TO_LINE_SPACE = "    ";

    /** Line# to Item Space. 5 spaces */
    public static final String LINE_TO_ITEM_SPACE = "     ";

    /** Item to Qty Space. 13 spaces */
    public static final String ITEM_TO_QTY_BASE_SPACE = "             ";

    /** Item Adjustment Space. 2 spaces */
    public static final String ITEM_ADJUSTMENT_SPACE = "  ";

    /** Qty to errMsg Space. 3 spaces */
    public static final String QTY_TO_ERRMSG_SPACE = "   ";

    /** MDSE CODE 8 digit length  */
    public static final int MDSE_LENGTH_8 = 8;

    // QC#2366 Add.
    /** RTL_WH_CD length  */
    public static final int RTL_WH_CD_LENGTH = 3;

    // =================================================
    // e-mail
    // =================================================
    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_ID = "NPAB1360M001";

    /** Error Mail Template ID */
    public static final String ERR_MAIL_TEMPLATE_ID = "NPAB1360M002";

    /** Mail Group ID(To) */
    public static final String MAIL_TO_GROUP_ID = "NPAB1360";

    /** Error Mail Group ID(To) */
    public static final String ERR_MAIL_TO_GROUP_ID = "NPAB136002";

    /** Mail Group ID(From) */
    public static final String MAIL_FROM_GROUP_ID = "FROM0005";

    /** Mail Language */
    public static final String ML_LANG = "en";

    /** Mail Language Code */
    public static final String ML_LANG_CD = "ISO-8859-1";

    /** . */
    public static final String EMAIL_PARAM_TECH_TOC_CD = "tecTocCd";

    /** . */
    public static final String EMAIL_PARAM_PRCH_REQ_NUM_SUBJECT = "prchReqNum";

    /** . */
    public static final String EMAIL_PARAM_PRCH_REQ_NUM = "PrchReqNum";

    /** . */
    public static final String EMAIL_PARAM_PRCH_REQ_LINE_NUM = "PrchReqLineNum";

    /** . */
    public static final String EMAIL_PARAM_MDSE_CD = "MdseCd";

    /** . */
    public static final String EMAIL_PARAM_PRCH_REQ_QTY = "PrchReqQty";

    /** . */
    public static final String EMAIL_PARAM_PRCH_REQ_BAL_QTY = "PrchReqBalQty";

    /** . */
    public static final String EMAIL_PARAM_PRCH_REQ_CRAT_DT = "PrchReqCratDt";

    /** . */
    public static final String EMAIL_PARAM_FSR_NUM = "FsrNum";

    /** . */
    public static final String EMAIL_PARAM_SVC_TASK_NUM = "SvcTaskNum";

    /** . */
    public static final String EMAIL_PARAM_SVC_MACH_SER_NUM = "SvcMachSerNum";

    /** . */
    public static final String EMAIL_PARAM_PO_REQ_NUM = "PoReqNum";

    /** . */
    public static final String EMAIL_PARAM_PO_REQ_LINE_NUM = "PoReqLineNum";

    /** . */
    public static final String EMAIL_PARAM_PO_REQ_APVL_STS_CD = "PoReqApvlStsCd";

    /** . */
    public static final String EMAIL_PARAM_TODAY = "TODAY";

    /** . */
    public static final String EMAIL_PARAM_TIME = "TIME";

    /** . */
    public static final String EMAIL_PARAM_BATCH_ID = "batchId";

    /** . */
    public static final String EMAIL_PARAM_MESSAGE = "message";

    /** . */
    public static final String EMAIL_PARAM_ERR_DATE = "errDate";

    // =================================================
    // Message Code
    // =================================================
    /** Global Company Code is mandatory. */
    public static final String NPAM1479E = "NPAM1479E";

    /** Sales date cannot be obtained. */
    public static final String NPAM1480E = "NPAM1480E";

    /** It failed to register an email. */
    public static final String NPZM0161E = "NPZM0161E";

    /** There is no ASL setup for the item number. */
    public static final String NPZM0272E = "NPZM0272E";
    
    // QC#61128 Add Start
    /** Inventory exists for this material at third party vendor location(Vendor# @), but missing ASL.  Please create ASL for vendor site. */
    public static final String NPAM1659E = "NPAM1659E";
    // QC#61128 Add End

    // =================================================
    // Var Char Const Name
    // =================================================
    /** Var Char Const Name */
    public static final String PR_INSC_FRZ_CMNT = "PR_INSC_FRZ_CMNT";

    /** Var Char Const Name */
    public static final String INSRC_PLN_ITEM_CLS_TP_CD = "INSRC_PLN_ITEM_CLS_TP_CD";

    /** . */
    public static final String PRCH_REQ_STS_CD_OPEN = "PRCH_REQ_STS_CD_OPEN";

    /** . */
    public static final String PRCH_REQ_INSRC_ERR_STS = "PRCH_REQ_INSRC_ERR_STS";

    /** . */
    public static final String PRCH_REQ_LINE_INSRC_ERR_STS = "PRCH_REQ_LINE_INSRC_ERR_STS";

    /** . */
    public static final String PR_CRAT_SYSTEM_USER = "PR_CRAT_SYSTEM_USER";

    /** . */
    public static final String INSC_PRCH_REQ_SRC_TP_CD = "INSC_PRCH_REQ_SRC_TP_CD";

    /** . */
    public static final String TECH_INSRC_PO_QLFY_CD = "TECH_INSRC_PO_QLFY_CD";

    // =================================================
    // DB Param
    // =================================================
    /** . */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** . */
    public static final String DB_PARAM_VND_CD = "vndCd";

    /** . */
    public static final String DB_PARAM_MDSE_CD = "mdseCd";

    /** . */
    public static final String DB_PARAM_MRP_ENBL_FLG = "mrpEnblFlg";

    /** . */
    public static final String DB_PARAM_MDSE_ITEM_CLS_TP_CD = "mdseItemClsTpCd";

    /** . */
    public static final String DB_PARAM_INSRC_ENBL_FLG = "insrcEnblFlg";

    /** . */
    public static final String DB_PARAM_FROM_RTL_WH_CD = "fromRtlWhCd";

    /** . */
    public static final String DB_PARAM_TO_RTL_WH_CD = "toRtlWhCd";

    /** . */
    public static final String DB_PARAM_VAR_CHAR_CONST_VAL = "varCharConstVal";

    /** . */
    public static final String DB_PARAM_VAR_CHAR_CONST_NM = "varCharConstNm";

    /** . */
    public static final String DB_PARAM_WH_OWNR_CD = "whOwnrCd";

    /** . */
    public static final String DB_PARAM_LOC_TP_CD = "locTpCd";

    /** . */
    public static final String DB_PARAM_STK_STS_CD = "stkStsCd";

    /** . */
    public static final String DB_PARAM_LOC_STS_CD = "locStsCd";

    /** . */
    public static final String DB_PARAM_INVTY_LOC_CD = "invtyLocCd";

    /** . */
    public static final String DB_PARAM_MDSE_ITEM_RELN_TP_CD = "srchMdseItemRelnTpCd";

    /** . */
    public static final String DB_PARAM_RELN_MDSE_CD = "relnMdseCd";

    /** . */
    public static final String DB_PARAM_PRCH_REQ_STS_CD = "prchReqStsCd";

    /** . */
    public static final String DB_PARAM_PRCH_REQ_APVL_FLG = "prchReqApvlFlgYes";

    /** . */
    public static final String DB_PARAM_PRCH_REQ_REL_STS_CD_NC = "prchReqRelStsNc";

    /** . */
    public static final String DB_PARAM_PRCH_REQ_REL_STS_CD_ERR = "prchReqRelStsErr";

    /** . */
    public static final String DB_PARAM_INSRC_RULE_DTL_NUM = "insrcRuleDtlNum";

    /** . */
    public static final String DB_PARAM_SRC_RTL_SWH_CD = "srcRtlSwhCd";

    /** . */
    public static final String DB_PARAM_PRCH_REQ_NUM = "prNum";

    /** . */
    public static final String DB_PARAM_PRCH_REQ_LINE_NUM = "prchReqLineNum";

    /** . */
    public static final String DB_PARAM_PRTY_LOC_FLG = "prtyLocFlg";
    
    // QC#61128 Add Start
    /** . */
    public static final String DB_PARAM_PRCH_REQ_TP_CD = "prchReqTpCd";
    // QC#61128 Add End

    // 2023/09/08 QC#61704 START
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    public static final String DB_PARAM_PRNT_VND_CD = "prntVndCd";
    // 2023/09/08 QC#61704 END

    // =================================================
    // DB Column
    // =================================================
    /** . */
    public static final String PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** . */
    public static final String PRCH_REQ_REC_TP_CD = "PRCH_REQ_REC_TP_CD";

    /** . */
    public static final String PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";

    /** . */
    public static final String PRCH_REQ_STS_CD = "PRCH_REQ_STS_CD";

    /** . */
    public static final String PRCH_REQ_CRAT_DT = "PRCH_REQ_CRAT_DT";

    /** . */
    public static final String PRCH_REQ_CRAT_TM = "PRCH_REQ_CRAT_TM";

    /** . */
    public static final String PRCH_REQ_CRAT_TZ = "PRCH_REQ_CRAT_TZ";

    /** . */
    public static final String PRCH_REQ_CRAT_DISP_DT_TM_TS = "PRCH_REQ_CRAT_DISP_DT_TM_TS";

    /** . */
    public static final String PRCH_REQ_CRAT_BY_PSN_CD = "PRCH_REQ_CRAT_BY_PSN_CD";

    /** . */
    public static final String PRCH_REQ_CRAT_BY_NM = "PRCH_REQ_CRAT_BY_NM";

    /** . */
    public static final String PRCH_REQ_RQST_BY_PSN_CD = "PRCH_REQ_RQST_BY_PSN_CD";

    /** . */
    public static final String PRCH_REQ_SRC_TP_CD = "PRCH_REQ_SRC_TP_CD";

    /** . */
    public static final String FSR_NUM = "FSR_NUM";

    /** . */
    public static final String SVC_TASK_NUM = "SVC_TASK_NUM";

    /** . */
    public static final String SVC_MACH_SER_NUM = "SVC_MACH_SER_NUM";

    /** . */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** . */
    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** . */
    public static final String CUST_ISS_PO_DT = "CUST_ISS_PO_DT";

    /** . */
    public static final String CPO_ORD_TP_CD = "CPO_ORD_TP_CD";

    /** . */
    public static final String CTAC_PSN_NM = "CTAC_PSN_NM";

    /** . */
    public static final String ADMIN_PSN_CD = "ADMIN_PSN_CD";

    /** . */
    public static final String PRCH_REQ_APVL_STS_CD = "PRCH_REQ_APVL_STS_CD";

    /** . */
    public static final String PRCH_REQ_APVL_DT = "PRCH_REQ_APVL_DT";

    /** . */
    public static final String PRCH_REQ_APVL_BY_PSN_CD = "PRCH_REQ_APVL_BY_PSN_CD";

    /** . */
    public static final String PRCH_REQ_APVL_BY_NM = "PRCH_REQ_APVL_BY_NM";

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
    public static final String ASL_SHIP_TO_CUST_CD = "ASL_SHIP_TO_CUST_CD";

    /** . */
    public static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";

    /** . */
    public static final String PO_QLFY_CD = "PO_QLFY_CD";

    /** . */
    public static final String VND_DROP_SHIP_FLG = "VND_DROP_SHIP_FLG";

    /** . */
    public static final String PRCH_REQ_CMNT_TXT = "PRCH_REQ_CMNT_TXT";

    /** . */
    public static final String SPCL_INSTN_CMNT_TXT = "SPCL_INSTN_CMNT_TXT";

    /** . */
    public static final String DELY_ADDL_CMNT_TXT = "DELY_ADDL_CMNT_TXT";

    /** . */
    public static final String RCV_ADDL_CMNT_TXT = "RCV_ADDL_CMNT_TXT";

    /** . */
    public static final String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** . */
    public static final String PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /** . */
    public static final String PRCH_REQ_INTFC_PK = "PRCH_REQ_INTFC_PK";

    /** . */
    public static final String ORIG_PRCH_REQ_LINE_NUM = "ORIG_PRCH_REQ_LINE_NUM";

    /** . */
    public static final String ORIG_PRCH_REQ_LINE_SUB_NUM = "ORIG_PRCH_REQ_LINE_SUB_NUM";

    /** . */
    public static final String PRCH_REQ_LINE_TP_CD = "PRCH_REQ_LINE_TP_CD";

    /** . */
    public static final String PRCH_REQ_LINE_STS_CD = "PRCH_REQ_LINE_STS_CD";

    /** . */
    public static final String RQST_RCV_DT = "RQST_RCV_DT";

    /** . */
    public static final String RQST_RCV_TM = "RQST_RCV_TM";

    /** . */
    public static final String RQST_RCV_TZ = "RQST_RCV_TZ";

    /** . */
    public static final String RQST_RCV_DISP_DT_TM_TS = "RQST_RCV_DISP_DT_TM_TS";

    /** . */
    public static final String PO_SCHD_REL_DT = "PO_SCHD_REL_DT";

    /** . */
    public static final String PRCH_REQ_REL_STS_CD = "PRCH_REQ_REL_STS_CD";

    /** . */
    public static final String PRCH_REQ_REL_DT_TM_TS = "PRCH_REQ_REL_DT_TM_TS";

    /** . */
    public static final String PRCH_REQ_REL_ERR_MSG_TXT = "PRCH_REQ_REL_ERR_MSG_TXT";

    /** . */
    public static final String PRCH_REQ_FRZ_FLG = "PRCH_REQ_FRZ_FLG";

    /** . */
    public static final String SHPG_PLN_NUM = "SHPG_PLN_NUM";

    /** . */
    public static final String SHIP_FROM_SO_NUM = "SHIP_FROM_SO_NUM";

    /** . */
    public static final String PROCR_TP_CD = "PROCR_TP_CD";

    /** . */
    public static final String DEST_INVTY_LOC_CD = "DEST_INVTY_LOC_CD";

    /** . */
    public static final String DEST_RTL_WH_CD = "DEST_RTL_WH_CD";

    /** . */
    public static final String DEST_RTL_SWH_CD = "DEST_RTL_SWH_CD";

    /** . */
    public static final String SRC_INVTY_LOC_CD = "SRC_INVTY_LOC_CD";

    /** . */
    public static final String SRC_RTL_WH_CD = "SRC_RTL_WH_CD";

    /** . */
    public static final String SRC_RTL_SWH_CD = "SRC_RTL_SWH_CD";

    /** . */
    public static final String PRNT_VND_CD = "PRNT_VND_CD";

    /** . */
    public static final String VND_CD = "VND_CD";

    /** . */
    public static final String VND_INVTY_LOC_CD = "VND_INVTY_LOC_CD";

    /** . */
    public static final String SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** . */
    public static final String CARR_CD = "CARR_CD";

    /** . */
    public static final String CARR_ACCT_NUM = "CARR_ACCT_NUM";

    /** . */
    public static final String CONFIG_TP_CD = "CONFIG_TP_CD";

    /** . */
    public static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** . */
    public static final String MDSE_CD = "MDSE_CD";

    /** . */
    public static final String PO_MDSE_CMPSN_TP_CD = "PO_MDSE_CMPSN_TP_CD";

    /** . */
    public static final String ORIG_RQST_MDSE_CD = "ORIG_RQST_MDSE_CD";

    /** . */
    public static final String ASL_MDSE_CD = "ASL_MDSE_CD";

    /** . */
    public static final String PRCH_REQ_QTY = "PRCH_REQ_QTY";

    /** . */
    public static final String ORD_QTY = "ORD_QTY";

    /** . */
    public static final String CUST_UOM_CD = "CUST_UOM_CD";

    /** . */
    public static final String PRCH_REQ_DISP_QTY = "PRCH_REQ_DISP_QTY";

    /** . */
    public static final String PRCH_REQ_DSPL_UOM_CD = "PRCH_REQ_DSPL_UOM_CD";

    /** . */
    public static final String PRCH_REQ_REL_QTY = "PRCH_REQ_REL_QTY";

    /** . */
    public static final String PRCH_REQ_BAL_QTY = "PRCH_REQ_BAL_QTY";

    /** . */
    public static final String PRCH_REQ_INSRC_QTY = "PRCH_REQ_INSRC_QTY";

    /** . */
    public static final String PRCH_REQ_CANC_QTY = "PRCH_REQ_CANC_QTY";

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
    public static final String INSRC_FLG = "INSRC_FLG";

    /** . */
    public static final String PO_CRAT_FLG = "PO_CRAT_FLG";

    /** . */
    public static final String REL_RQST_TO_PO_ORD_NUM = "REL_RQST_TO_PO_ORD_NUM";

    /** . */
    public static final String PO_ORD_NUM = "PO_ORD_NUM";

    /** . */
    public static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** . */
    public static final String INVTY_ORD_NUM = "INVTY_ORD_NUM";

    /** . */
    public static final String INVTY_ORD_LINE_NUM = "INVTY_ORD_LINE_NUM";

    /** . */
    public static final String WRK_ORD_NUM = "WRK_ORD_NUM";

    /** . */
    public static final String WRK_ORD_DTL_LINE_NUM = "WRK_ORD_DTL_LINE_NUM";

    /** . */
    public static final String TRX_REF_NUM = "TRX_REF_NUM";

    /** . */
    public static final String TRX_REF_LINE_NUM = "TRX_REF_LINE_NUM";

    /** . */
    public static final String TRX_REF_LINE_SUB_NUM = "TRX_REF_LINE_SUB_NUM";

    /** . */
    public static final String PRCH_REQ_LINE_CMNT_TXT = "PRCH_REQ_LINE_CMNT_TXT";

    /** . */
    public static final String BO_EML_PROC_STS_CD = "BO_EML_PROC_STS_CD";

    /** . */
    public static final String SO_NUM = "SO_NUM";

    /** . */
    public static final String PRO_NUM = "PRO_NUM";

    /** . */
    public static final String SHIP_DT_TM_TS = "SHIP_DT_TM_TS";

    /** . */
    public static final String SHIP_QTY = "SHIP_QTY";

    /** . */
    public static final String RWS_NUM = "RWS_NUM";

    /** . */
    public static final String RWS_CLO_DT_TM_TS = "RWS_CLO_DT_TM_TS";

    /** . */
    public static final String RWS_PUT_AWAY_QTY = "RWS_PUT_AWAY_QTY";

    /** . */
    public static final String BACK_TO_TECH_QTY = "BACK_TO_TECH_QTY";

    /** . */
    public static final String FROM_STK_STS_CD = "FROM_STK_STS_CD";

    /** . */
    public static final String TO_STK_STS_CD = "TO_STK_STS_CD";

    /** . */
    public static final String THIS_MTH_FOB_COST_AMT = "THIS_MTH_FOB_COST_AMT";

    /** . */
    public static final String ASL_DTL_PK = "ASL_DTL_PK";

    /** . */
    public static final String ASL_UNIT_PRC_AMT = "ASL_UNIT_PRC_AMT";

    /** . */
    public static final String ENT_DEAL_NET_UNIT_PRC_AMT = "ENT_DEAL_NET_UNIT_PRC_AMT";

    /** . */
    public static final String ENT_PO_DTL_DEAL_NET_AMT = "ENT_PO_DTL_DEAL_NET_AMT";

    /** . */
    public static final String ENT_FUNC_NET_UNIT_PRC_AMT = "ENT_FUNC_NET_UNIT_PRC_AMT";

    /** . */
    public static final String ENT_PO_DTL_FUNC_NET_AMT = "ENT_PO_DTL_FUNC_NET_AMT";

    /** . */
    public static final String CCY_CD = "CCY_CD";

    /** . */
    public static final String EXCH_RATE = "EXCH_RATE";

    /** . */
    public static final String FRT_CHRG_TO_CD = "FRT_CHRG_TO_CD";

    /** . */
    public static final String FRT_CHRG_METH_CD = "FRT_CHRG_METH_CD";

    /** . */
    public static final String FRT_COND_CD = "FRT_COND_CD";

    /** . */
    public static final String VND_PMT_TERM_CD = "VND_PMT_TERM_CD";

    /** . */
    public static final String VND_PMT_TERM_DESC_TXT = "VND_PMT_TERM_DESC_TXT";

    /** . */
    public static final String COA_CMPY_CD = "COA_CMPY_CD";

    /** . */
    public static final String COA_AFFL_CD = "COA_AFFL_CD";

    /** . */
    public static final String COA_BR_CD = "COA_BR_CD";

    /** . */
    public static final String COA_CH_CD = "COA_CH_CD";

    /** . */
    public static final String COA_CC_CD = "COA_CC_CD";

    /** . */
    public static final String COA_ACCT_CD = "COA_ACCT_CD";

    /** . */
    public static final String COA_PROJ_CD = "COA_PROJ_CD";

    /** . */
    public static final String COA_EXTN_CD = "COA_EXTN_CD";

    /** . */
    public static final String COA_PROD_CD = "COA_PROD_CD";

    /** . */
    public static final String TECH_RQST_NTC_ML_PROC_CD = "TECH_RQST_NTC_ML_PROC_CD";

    /** . */
    public static final String TECH_RTN_NTC_ML_PROC_CD = "TECH_RTN_NTC_ML_PROC_CD";

    /** . */
    public static final String VND_RTRN_RSN_CD = "VND_RTRN_RSN_CD";

    /** . */
    public static final String MDSE_ITEM_CLS_TP_CD = "MDSE_ITEM_CLS_TP_CD";

    /** . */
    public static final String PRCH_GRP_CD = "PRCH_GRP_CD";

    /** . */
    public static final String PRCH_AVAL_FLG = "PRCH_AVAL_FLG";

    /** . */
    public static final String INSRC_RULE_DTL_NUM = "INSRC_RULE_DTL_NUM";

    /** . */
    public static final String WH_CUT_OFF_TM_APPLY_FLG = "WH_CUT_OFF_TM_APPLY_FLG";

    /** . */
    public static final String INSRC_RULE_DTL_NM = "INSRC_RULE_DTL_NM";

    /** . */
    public static final String INSRC_DTL_PRTY_NUM = "INSRC_DTL_PRTY_NUM";

    /** . */
    public static final String SRCH_SRC_WH_FLG = "SRCH_SRC_WH_FLG";

    /** . */
    public static final String SRCH_INSRC_PLN_FLG = "SRCH_INSRC_PLN_FLG";

    /** . */
    public static final String SRCH_CUSA_INVTY_FLG = "SRCH_CUSA_INVTY_FLG";

    /** . */
    public static final String SRCH_CVI_INVTY_FLG = "SRCH_CVI_INVTY_FLG";

    /** . */
    public static final String SRCH_WH_OWNR_INVTY_FLG = "SRCH_WH_OWNR_INVTY_FLG";

    /** . */
    public static final String SRCH_WH_OWNR_CD = "SRCH_WH_OWNR_CD";

    /** . */
    public static final String WH_OWNR_DUMMY_INVTY_LOC_CD = "WH_OWNR_DUMMY_INVTY_LOC_CD";

    /** . */
    public static final String SRCH_SPEC_LOC_INVTY_FLG = "SRCH_SPEC_LOC_INVTY_FLG";

    /** . */
    public static final String SRCH_SPEC_INVTY_LOC_CD = "SRCH_SPEC_INVTY_LOC_CD";

    /** . */
    public static final String CRAT_PO_FLG = "CRAT_PO_FLG";

    /** . */
    public static final String SRCH_ORIG_ITEM_FLG = "SRCH_ORIG_ITEM_FLG";

    /** . */
    public static final String SRCH_SUPD_ITEM_FLG = "SRCH_SUPD_ITEM_FLG";

    /** . */
    public static final String SRCH_MDSE_ITEM_RELN_TP_CD = "SRCH_MDSE_ITEM_RELN_TP_CD";

    /** . */
    public static final String ORIG_LINE_FRZ_FLG = "ORIG_LINE_FRZ_FLG";

    /** . */
    public static final String BO_NTFY_REQ_FLG = "BO_NTFY_REQ_FLG";

    /** . */
    public static final String INVTY_AVAL_QTY = "INVTY_AVAL_QTY";

    /** . */
    public static final String TO_RTL_WH_CD = "TO_RTL_WH_CD";

    /** . */
    public static final String WH_CUT_OFF_TM = "WH_CUT_OFF_TM";

    /** . */
    public static final String PLN_ITEM_INSRC_CD = "PLN_ITEM_INSRC_CD";

    /** . */
    public static final String POST_CD = "POST_CD";

    /** . */
    public static final String CTRY_CD = "CTRY_CD";

    /** . */
    public static final String INSRC_PRCH_REQ_LINE_TP_CD = "INSRC_PRCH_REQ_LINE_TP_CD";

    /** . */
    public static final String DEAL_CCY_CD = "DEAL_CCY_CD";

    /** . */
    public static final String INSRC_RULE_NM = "INSRC_RULE_NM";

    /** . */
    public static final String DEST_LOC_TP_CD = "DEST_LOC_TP_CD";

    /** . */
    public static final String DEST_RTL_WH_CATG_CD = "DEST_RTL_WH_CATG_CD";

    /** . */
    public static final String TECH_LINE_BIZ_TP_CD = "TECH_LINE_BIZ_TP_CD";

    /** . */
    public static final String INSRC_PRTY_NUM = "INSRC_PRTY_NUM";

    /** . */
    public static final String LOC_TP_CD = "LOC_TP_CD";

    /** . */
    public static final String RTL_WH_CATG_CD = "RTL_WH_CATG_CD";

    /** . */
    public static final String RELN_MDSE_CD = "RELN_MDSE_CD";

    /** . */
    public static final String VAR_CHAR_CONST_VAL = "VAR_CHAR_CONST_VAL";

    /** . */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";
    //08/29/2017 CITS S.Endo Add Sol#406(QC#19243) START
    /** . */
    public static final String WH_TRNSF_BO_REQ_FLG = "WH_TRNSF_BO_REQ_FLG";

    /** . */
    public static final String BO_SRC_RTL_WH_CD = "BO_SRC_RTL_WH_CD";

    /** . */
    public static final String BO_SRC_RTL_SWH_CD = "BO_SRC_RTL_SWH_CD";

    /** . */
    public static final String BO_ERR_MSG = "This request is BackOrdered.";

    /** . */
    public static final String DB_PARAM_OO = "oo";

    /** . */
    public static final String DB_VALUE_OO = "OO";
    //08/29/2017 CITS S.Endo Add Sol#406(QC#19243) END

    // QC#27660 Add FRZ_REF_CMNT_TXT.
    /** . */
    public static final String FRZ_REF_CMNT_TXT = "FRZ_REF_CMNT_TXT";

    /**
     * VAR_CONST: CREATE_MATERIAL_PARTS
     */
    public static final String VAR_CONST_CREATE_MATERIAL_PARTS = "CREATE_MATERIAL_PARTS";

    // QC#61128 Add Start
    /**
     * VAR_CONST: ORCL_VND_CD
     */
    public static final String VAR_CONST_ORCL_VND_CD = "ORCL_VND_CD";
    // QC#61128 Add End
    /**
     * SPLY_ITEM_NUM
     */
    public static final String SPLY_ITEM_NUM = "SPLY_ITEM_NUM";

    // START 2023/09/12 G.Quan [QC#61589, ADD] 
    /**
     * DS_COND_CONST_GRP_ID
     */
    public static final String NPAB1360_ITEM_CLS = "NPAB1360_ITEM_CLS";
    // END 2023/09/12 G.Quan [QC#61589, ADD] 

    // START 2023/09/19 G.Quan [QC#59207, ADD]
    /**
     * Kick out from mail address
     */
    public static final String NPAB1360_FROM_ADDR_KICK_OUT = "PartsSupportCenter@csa.canon.com";
    // END 2023/09/19 G.Quan [QC#59207, ADD]
}
