package com.canon.cusa.s21.batch.NPA.NPAB100001.constant;

/**
 * <pre>
 * PO ACK<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------

 * 01/17/2016   CITS            T.Hakodate      Created         WDS NA V4.0
 * 09/27/2016   CITS            K.Ogino         Update          QC#8195
 * 12/21/2016   CITS            R.Shimamoto     Update          QC#16100
 * 07/04/2017   CITS            S.Endo          Update          QC#19487
 * 10/22/2018   CITS            T.Hakodate      Update          QC#28834
 * 11/14/2018   CITS            M.Naito         Update          QC#29047
 * 12/05/2018   Fujitsu         S.Takami        Update          QC#29494
 * 02/21/2019   CITS            M.Naito         Update          QC#50340
 * 08/20/2019   CITS            R.Shimamotmo    Update          QC#52184
 * 11/15/2019   CITS            M.Naito         Update          QC#53502
 * 10/06/2020   CITS            H.Dimay         Update          QC#57795
 * 07/20/2023   Hitachi         S.Dong          Update          QC#61638
 * </pre>
 */

public class NPAB100001Constant {

    /** Failed to get Interface ID. */
    public static final String NPAM1168E = "NPAM1168E";

    /** No PO data found. [@] */
    public static final String NPAM1169E = "NPAM1169E";

    /** Cannot lock PO Data. PO Ord Num:[@]. */
    public static final String NPAM1170E = "NPAM1170E";

    /** Failed to update. [@]. */
    public static final String NPAM1171E = "NPAM1171E";

    /** Failed to insert. [@]. */
    public static final String NPAM1172E = "NPAM1172E";

    /** Error of API [@] */
    public static final String NPAM1178E = "NPAM1178E";

    /** Failed to update @ table. @ is @. */
    public static final String NPAM1003E = "NPAM1003E";

    /** [@] does not exist in Master. */
    public static final String NPAM0076E = "NPAM0076E";

    public static final String NPZM0147E = "NPZM0147E";

    /**
     * The MDSE Code of PO Detail does not match the ACK of MDSE Code.
     */
    public static final String NPAM1281W = "NPAM1281W";

    /** The Qty of PO Detail does not match to the ACK of Order Qty. */
    public static final String NPAM1282W = "NPAM1282W";

    /**
     * Could not get MailGroupAddress. MAIL_GROUP_ID_TO : [@]
     * MAIL_KEY1 : [@]
     */
    public static final String NPAM0063E = "NPAM0063E";

    /** Could not get Mail Template. MAIL_TEMPLATE_ID : [@] */
    public static final String NPAM0064E = "NPAM0064E";

    /** No PO Ord Dtl data found. [@] */
    public static final String NPAM1236E = "NPAM1236E";

    /** The field of [@] is not input. */
    public static final String NPAM1173E = "NPAM1173E";

    /** Cannot retrieve mail address. */
    public static final String NPAM1265E = "NPAM1265E";

    /** Record of PO_ACK_HDR on the PO is more than one record exists. */
    public static final String NPAM1307E = "NPAM1307E";

    /** [@] assigned to [@] doesn't exist.[@] */
    public static final String NPAM1530E = "NPAM1530E";

    /**
     * Mandatory parameter is not set. Item Name: [@], Table Name:
     * [@], Key Item: [@].
     */
    public static final String NPAM1321E = "NPAM1321E";

    /** Error occurred in [@] API. */
    public static final String NPAM1323E = "NPAM1323E";

    /** '@'s @ doesn't exist. */
    public static final String NSBM0141E = "NSBM0141E";

    // START 2019/05/23 M.Naito [QC#50340,ADD]
    /** PO Price has been changed. */
    public static final String NPAM1644W = "NPAM1644W";
    // END 2019/05/23 M.Naito [QC#50340,ADD]

    // QC#52184 Start
    /** Cannot process since the PO status is Saved. */
    public static final String NPZM0080E = "NPZM0080E";

    /** Cannot process since the PO status is Waiting Approval. */
    public static final String NPZM0081E = "NPZM0081E";

    public static final String NSBM0027E = "NSBM0027E";
    // QC#52184 End

    /** TimeStamp format : ediRcvDateTs TimeStamp */
    public static final String TS_FORMAT_EDI_RCV_DATE_TS = "yyyyMMddHHmmssSSS";

    public static final String TS_FORMAT_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    /** Date Time Pattern For Processing Date */
    public static final String DATE_TIME_PATTERN_FOR_PROC_DATE = "yyyyMMddHHmmssSSS";

    /** Cusa Global Company Code . */
    public static final String CUSA_GLBL_CMPY_CD = "CUSA_GLBL_CMPY_CD";

    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** map key : interfaceId. */
    public static final String MAP_KEY_INTERFACE_ID = "interfaceId";

    /** Transaction Id */
    public static final String CONFIG_ID = "Transaction Config Id";

    /** WS Transaction Id */
    public static final String WS_CONFIG_ID = "ITRL_CONFIG_CUSA_WS_CSA";

    /** PARTS Transaction Id */
    public static final String PRT_CONFIG_ID = "ITRL_CONFIG_CUSA_PRT_CSA";

    /** map key : glblCmpyCd. */
    public static final String MAP_KEY_GLBL_CMPY_CD = "glblCmpyCd";

    /** map key : LastUpdTs. */
    public static final String MAP_KEY_LAST_UPD_TS_CAMEL = "lastUpdTs";

    public static final/* map key : poStsCd. */
    String MAP_KEY_PO_STS_CD = "poStsCd";

    /** map key : vndIssOrdNum. */
    public static final String MAP_KEY_VND_ISS_ORD_NUM = "vndIssOrdNum";

    /** map key : ezCancelFlag. */
    public static final String MAP_KEY_EZ_CANCEL_FLAG = "ezCancelFlag";

    /** map key : procPgmId. */
    public static final String MAP_KEY_PROC_PGM_ID_CAMEL = "procPgmId";

    /** map key : dsBizLastProcTs. */
    public static final String MAP_KEY_DS_BIZ_LAST_PROC_TS = "dsBizLastProcTs";

    /** map key : dsBizLastUpdTs. */
    public static final String MAP_KEY_DS_BIZ_LAST_UPD_TS_CAMEL = "dsBizLastUpdTs";

    /** map key : DS_BIZ_LAST_UPD_TS. */
    public static final String MAP_KEY_DS_BIZ_LAST_UPD_TS = "DS_BIZ_LAST_UPD_TS";

    /** map key : LAST_PROC_TS. */
    public static final String MAP_KEY_LAST_PROC_TS = "LAST_PROC_TS";

    /** DB item Name : EDI_PO_ORD_NUM. */
    public static final String DB_EDI_PO_ORD_NUM = "ediPoOrdNum";

    /** DB item Name : PO_ORD_NUM. */
    public static final String DB_PO_ORD_NUM = "poOrdNum";

    /** DB item Name : PO_ORD_DTL_LINE_NUM. */
    public static final String DB_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    /** DB item Name : PO_ORD_DTL_SUB_LINE_NUM. */
    public static final String DB_PO_ORD_DTL_SUB_LINE_NUM = "poOrdDtlSubLineNum";

    /** map key : PROC_PGM_ID. */
    public static final String MAP_KEY_PROC_PGM_ID = "PROC_PGM_ID";

    /** map key : ordDtlLastUpdTs */
    public static final String MAP_KEY_ORD_DTL_LAST_UPD_TS = "ordDtlLastUpdTs";

    /** map key : poOrdNum */
    public static final String MAP_KEY_PO_ORD_NUM = "poOrdNum";

    /** map key : poOrdDtlLineNum */
    public static final String MAP_KEY_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    /** map key : mdseCd */
    public static final String MAP_KEY_MDSE_CD = "mdseCd";

    /** map key : VND_CD */
    public static final String MAP_KEY_VND_CD = "vndCd";

    /** map key : VND_CD */
    public static final String MAP_KEY_PO_ACK_HDR_PK = "poAckHdrPk";

    /** map key : hasNullEdiPoOrdDtlLineNum */
    public static final String MAP_KEY_CONVERT_PO_ORD_NUM_FROM_EDI_PO_DTL_XREF = "convertPoOrdNumFromEdiPoDtlXref";

    /** map key : hasNullEdiPoOrdDtlLineNum */
    public static final String MAP_KEY_HAS_NULL_EDI_PO_ORD_DTL_LINE_NUM = "hasNullEdiPoOrdDtlLineNum";

    /** PRNT_CMPY_VND.VND_SYS_TP_CD value:CUSA Parts. */
    public static final String VND_SYS_TP_CD_P = "P";

    /** PRNT_CMPY_VND.VND_SYS_TP_CD value:CUSA WS. */
    public static final String VND_SYS_TP_CD_W = "W";

    /** map key : PO_ACK_UPD_PROC_FLG. */
    public static final String MAP_KEY_PO_ACK_UPD_PROC_FLG = "poAckUpdProcFlg";

    /** map key : ACK_EDI_PROC_STS_CD. */
    public static final String MAP_KEY_ACK_EDI_PROC_STS_CD = "ackEdiProcStsCd";

    /** Mail Group ID From : FROM0002 */
    public static final String MAIL_GROUP_ID_FROM = "FROM0002";

    /** Mail Group ID To : */
    public static final String MAIL_GROUP_ID_TO = "NPAB1000";

    /** Mail Key From : DP */
    public static final String MAIL_KEY_FROM = "NP";

    /** Mail Key To : */
    public static final String MAIL_KEY_TO = "To";

    /** Mail Template ID 01 : */
    public static final String MAIL_TEMPLATE_ID_01 = "NPAB1000M001";

    /** Mail Template ID 02 : */
    public static final String MAIL_TEMPLATE_ID_02 = "NPAB1000M002";

    /** Mail Template ID 03 : */
    public static final String MAIL_TEMPLATE_ID_03 = "NPAB1000M003";

    public static final/* Mail Template ID 04 : */
    String MAIL_TEMPLATE_ID_04 = "NPAB1000M004";

    /** Mail Character Set */
    public static final String MAIL_CHARSET = "ISO-8859-1";

    /** debug message code */
    public static final int DEBUG_MESSAGE_CODE = 600;

    /** SQL_ID:001 */
    public static final String SQL_ID_001 = "001";

    /** SQL_ID:002 */
    public static final String SQL_ID_002 = "002";

    /** SQL_ID:003 */
    public static final String SQL_ID_003 = "003";

    /** SQL_ID:004 */
    public static final String SQL_ID_004 = "004";

    /** SQL_ID:005 */
    public static final String SQL_ID_005 = "005";

    /** SQL_ID:008 */
    public static final String SQL_ID_008 = "008";

    /** SQL_ID:999 */
    public static final String SQL_ID_999 = "999";

    /** param:glblCmpyCd01 */
    public static final String PARAM_GLBL_CMPY_CD_01 = "glblCmpyCd01";

    /** param:custIssPoNum01 */
    public static final String PARAM_CUST_ISS_PO_NUM_01 = "custIssPoNum01";

    /** param:ediPoLineNum01 */
    public static final String PARAM_EDI_PO_LINE_NUM_01 = "ediPoLineNum01";

    /** param:ediPoSubLineNum01 */
    public static final String PARAM_EDI_PO_SUB_LINE_NUM_01 = "ediPoSubLineNum01";

    /** param:shpgPlnDplyLineNum01 */
    public static final String PARAM_SHPG_PLN_DPLY_LINE_NUM_01 = "shpgPlnDplyLineNum01";

    /** param:atpInfoLastUpdTs01 */
    public static final String PARAM_ATP_INFO_LAST_UPD_TS_01 = "atpInfoLastUpdTs01";

    /** param:ordDtlLastUpdTs01 */
    public static final String PARAM_ORD_DTL_LAST_UPD_TS_01 = "ordDtlLastUpdTs01";

    /** param:ordDtlLastUpdTs01 */
    public static final String PARAM_ITRL_INT_FC_ID_01 = "itrlIntfcId01";

    public static final/* param:ackEdiProcStsCd01A */
    String PARAM_ACK_EDI_PROC_STS_CD_01A = "ackEdiProcStsCd01A";

    /** param:ackEdiProcStsCd01B */
    public static final String PARAM_ACK_EDI_PROC_STS_CD_01B = "ackEdiProcStsCd01B";

    /** param:poAckAtpInfoPk01 */
    public static final String PARAM_PO_ACK_ATP_INFO_PK_01 = "poAckAtpInfoPk01";

    /** param:poOrdNum01 */
    public static final String PARAM_PO_ORD_NUM_01 = "poOrdNum01";

    /** param:poOrdDtlLineNum01 */
    public static final String PARAM_PO_ORD_DTL_LINE_NUM_01 = "poOrdDtlLineNum01";

    /** param:poOrdDtlSubLineNum01 */
    public static final String PARAM_PO_ORD_DTL_SUB_LINE_NUM_01 = "poOrdDtlSubLineNum01";

    /** param:vndCpoOrdNum01 */
    public static final String PARAM_VND_CPO_ORD_NUM_01 = "vndCpoOrdNum01";

    /** param:vndCpoDtlLineNum01 */
    public static final String PARAM_VND_CPO_DTL_LINE_NUM_01 = "vndCpoDtlLineNum01";

    /** param:vndCpoDtlLineSubNum01 */
    public static final String PARAM_VND_CPO_DTL_LINE_SUB_NUM_01 = "vndCpoDtlLineSubNum01";

    /** param:poAckUpdProcFlg01 */
    public static final String PARAM_PO_ACK_UPD_PROC_FLG_01 = "poAckUpdProcFlg01";

    /** param:poAckHdrWrkPk01 */
    public static final String PARAM_PO_ACK_HDR_WRK_PK_01 = "poAckHdrWrkPk01";

    /** param:mdseCd01 */
    public static final String PARAM_MDSE_CD_01 = "mdseCd01";

    /** param:ediPoOrdNum01 */
    public static final String PARAM_EDI_PO_ORD_NUM_01 = "ediPoOrdNum01";

    /** param:vndCd01 */
    public static final String PARAM_VND_CD_01 = "vndCd01";

    /** param:vndPoSkuCd01 */
    public static final String PARAM_VND_PO_SKU_CD_01 = "vndPoSkuCd01";

    /** param:rgtnStsCd01 */
    public static final String PARAM_RGTN_STS_CD_01 = "rgtnStsCd01";

    /** param:rgtnStsCd02 */
    public static final String PARAM_RGTN_STS_CD_02 = "rgtnStsCd02";

    /** Business Process Log: Subsystem ID */
    public static final String BIZ_PROC_LOG_SUB_SYS_ID = "NPA";

    public static final/* Business Process Log: Process ID */
    String BIZ_PROC_LOG_PROC_ID = "PROCUREMENT";

    /** Business Process Log: document Type */
    public static final String BIZ_PROC_LOG_DOC_TP_CD = "PROCR";

    /** Business Process Log: Event ID */
    public static final String BIZ_PROC_LOG_EVENT_ID = "ACK Received";

    /** System Last Date */
    public static final String SYSTEM_LAST_DATE = "99991231";

    /** Interval days */
    public static final String KEY_INTERVAL_DAYS = "INTERVAL_DAYS";

    /** Asterisk:* */
    public static final String ASTERISK = "*";

    /** HYPHEN */
    public static final String HYPHEN = "-";

    // 2018/12/05 QC#29494 Mod Start
//    /** space * 4 */
//    public static final String INDENT = "    ";

    /** space * 19 */
    public static final String INDENT = "                   ";
    // 2018/12/05 QC#29494 Mod End

    /** Mandatory ACK Header Items */
    public static final String[] MANDATORY_HDR_ITEMS = {"ACK_EDI_PROC_STS_CD", "EDI_RCV_DATE_TS", "EDI_PO_ORD_NUM" };

    /** Mandatory ACK Detail Items */
    public static final String[] MANDATORY_DTL_ITEMS = {"PO_ORD_DTL_SUB_LINE_NUM", "EDI_PO_ORD_NUM", "SHPG_PLN_DPLY_LINE_NUM", "VND_PO_ACK_LINE_STS_TXT", "ORD_QTY" };

    /** Mandatory ATP INFO Items */
    public static final String[] MANDATORY_ATP_INFO_ITEMS = {"poAckAtpInfoPk", "ediPoOrdNum", "mdseCd", "ordQty", "allocQty", "psdDt", "pddDt", "atpInfoLastUpdTs" };

    /** SQL Bind Name for SSM */
    public static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** . */
    public static final String BIND_VND_CD = "vndCd";

    /** . */
    public static final String BIND_SPLY_ITEM_NUM = "splyItemNum";

    /** . */
    public static final String PO_ACK_HDR_SQ = "PO_ACK_HDR_SQ";

    /** . */
    public static final String VND_CPO_ORD_NUM = "VND_CPO_ORD_NUM";

    /** . */
    public static final String ORD_HDR_STS_CD = "ORD_HDR_STS_CD";

    /** . */
    public static final String EDI_RCV_DATE_TS = "EDI_RCV_DATE_TS";

    /** . */
    public static final String BIND_EFF_THRU_DT = "effThruDt";

    /** . */
    public static final String BIND_EFF_FROM_DT = "effFromDt";

    /** . */
    public static final String BIND_ASL_START_DT = "aslStartDt";

    /** . */
    public static final String BIND_ASL_END_DT = "aslEndDt";

    /** . */
    public static final String BIND_SLS_DATE = "slsDate";

    /** . */
    public static final String BIND_MDSE_CD = "mdseCd";

    /** . */
    public static final String BIND_PO_ORD_NUM = "poOrdNum";

    /** . */
    public static final String BIND_DEFAULT = "default";

    /** . */
    public static final String BIND_ROWNUM = "rowNum";

    /** . */
    public static final String BIND_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    /** . */
    public static final String BIND_PO_ACK_HDR_LTST_FLG = "poAckHdrLtstFlg";

    /** . */
    public static final String BIND_PO_ACK_HDR_PK_01 = "poAckHdrPk01";

    /** . */
    public static final String BIND_PO_ACK_UPD_PROC_FLG = "poACkUpdProcFlg";

    /** . */
    public static final String BIND_ITRL_INTFC_ID = "itrlIntfcId";

    /** . */
    public static final String BIND_ACK_EDI_PROC_STS_CD = "ackEdiProcStsCd";

    /** . */
    public static final String BIND_ACK_EDI_PROC_STS_CD_LIST = "ackEdiProcStsCdList";

    /** . */
    public static final String BIND_RGTN_STS_LIST = "rgtnStsList";

    /** . */
    public static final String BIND_PO_ACK_HDR_WRK_PK = "poAckHdrWrkPk";

    /** . */
    public static final String BIND_PO_ACK_HDR_PK = "poAckHdrPk";

    /** . */
    public static final String BIND_PO_ACK_DTL_PK = "poAckDtlPk";

    /** . */
    public static final String BIND_VND_MDSE_CD = "vndMdseCd";

    /** . */
    public static final String BIND_SUPD_FROM_MDSE_CD = "supdFromMdseCd";

    /** . */
    public static final String BIND_SUPD_TO_MDSE_CD = "supdToMdseCd";

    /** . */
    public static final String BIND_PO_REC_FLG = "poRecFlg";

    /** . */
    public static final String BIND_PENDING = "pending";

    // START 2018/11/14 M.Naito [QC#29047,ADD]
    /** . */
    public static final String BIND_TST_IMPRESO_FLG = "tstImpresoFlg";
    // END 2018/11/14 M.Naito [QC#29047,ADD]

    // START 2019/11/15 M.Naito [QC#53502,ADD]
    /** . */
    public static final String BIND_VND_PO_ACK_STS_CD = "vndPoAckStsCd";
    // END 2019/11/15 M.Naito [QC#53502,ADD]

    /** . */
    public static final String PO_ACK_ERR_WRK_PK = "PO_ACK_ERR_WRK_PK";

    /** . */
    public static final String PO_ACK_HDR_PK = "PO_ACK_HDR_PK";

    /** . */
    public static final String PO_ACK_DTL_SQ = "PO_ACK_DTL_SQ";

    /** . */
    public static final String SUPD_RELN_STAGE_SQ = "SUPD_RELN_STAGE_SQ";

    /** . */
    public static final String THIS_MTH_FOB_COST_AMT = "THIS_MTH_FOB_COST_AMT";

    /** . */
    public static final String PO_ORD_DTL_SUB_LINE_NUM = "PO_ORD_DTL_SUB_LINE_NUM";

    /** . */
    public static final String PRNT_VND_CD = "PRNT_VND_CD";

    /** . */
    public static final String SPLY_ITEM_NUM = "SPLY_ITEM_NUM";

    /** . */
    public static final String VND_CD = "VND_CD";

    /** . */
    public static final String SHPG_PLN_DPLY_LINE_NUM = "SHPG_PLN_DPLY_LINE_NUM";

    /** . */
    public static final String PO_ACK_LINE_STS_CD = "PO_ACK_LINE_STS_CD";

    /** . */
    public static final String VND_PO_ACK_LINE_STS_TXT = "VND_PO_ACK_LINE_STS_TXT";

    /** . */
    public static final String PO_ACK_HDR_WRK_PK = "PO_ACK_HDR_WRK_PK";

    /** . */
    public static final String PO_ACK_DTL_WRK_PK = "PO_ACK_DTL_WRK_PK";

    /** . */
    public static final String PO_ACK_HDR_WRK = "PO_ACK_HDR_WRK";

    /** . */
    public static final String PO_ACK_DTL_WRK = "PO_ACK_DTL_WRK";

    /** . */
    public static final String VND_ISS_ORD_NUM = "VND_ISS_ORD_NUM";

    /** . */
    public static final String EDI_PO_ORD_NUM = "EDI_PO_ORD_NUM";

    /** . */
    public static final String ACK_EDI_PROC_STS_CD = "ACK_EDI_PROC_STS_CD";

    /** . */
    public static final String ORD_LAST_UPD_TS = "ORD_LAST_UPD_TS";

    /** . */
    public static final String BAT_ERR_MSG_TXT = "BAT_ERR_MSG_TXT";

    /** . */
    public static final String PO_ORD_NUM = "PO_ORD_NUM";

    /** . */
    public static final String EFF_THRU_DATE = "99991231";

    /** . */
    public static final String EFF_FROM_DATE = "19000101";

    /** . */
    public static final String PO_QTY = "PO_QTY";

    /** . */
    public static final String MDSE_CD = "MDSE_CD";

    /** . */
    public static final String MDSE_NM = "MDSE_NM";

    /** . */
    public static final String PO_ACK_DTL_PK = "PO_ACK_DTL_PK";

    /** . */
    public static final String VND_MDSE_CD = "VND_MDSE_CD";

    /** . */
    public static final String TRD_PTNR_SKU_CD = "TRD_PTNR_SKU_CD";

    /** . */
    public static final String THIS_MTH_FOB_CONST_AMT = "THIS_MTH_FOB_CONST_AMT";

    /** . */
    public static final String CCY_CD = "CCY_CD";

    /** . */
    public static final String UOM_CD = "UOM_CD";

    /** . */
    public static final String ETD_DT = "ETD_DT";

    /** . */
    public static final String ETA_DT = "ETA_DT";

    /** . */
    public static final String VND_CPO_DTL_LINE_NUM = "VND_CPO_DTL_LINE_NUM";

    /** . */
    public static final String VND_CPO_DTL_LINE_SUB_NUM = "VND_CPO_DTL_LINE_SUB_NUM";

    /** . */
    public static final String VND_CPO_LINE_STS_CD = "VND_CPO_LINE_STS_CD";

    /** . */
    public static final String SHPG_STS_CD = "SHPG_STS_CD";

    /** . */
    public static final String ORIG_TRD_PTNR_SKU_CD = "ORIG_TRD_PTNR_SKU_CD";

    /** . */
    public static final String ORIG_ORD_QTY = "ORIG_ORD_QTY";

    /** . */
    public static final String SHPG_PLN_NUM = "SHPG_PLN_NUM";

    /** . */
    public static final String VND_CPO_CRAT_TS = "VND_CPO_CRAT_TS";

    /** . */
    public static final String ORD_DTL_LAST_UPD_TS = "ORD_DTL_LAST_UPD_TS";

    /** . */
    public static final String VND_INVTY_LOC_CD = "VND_INVTY_LOC_CD";

    /** . */
    public static final String VND_ISS_PO_ORD_NUM = "VND_ISS_PO_ORD_NUM";

    /** . */
    public static final String PO_ACK_CMNT_TXT = "PO_ACK_CMNT_TXT";

    /** . */
    public static final String VND_OTBD_CARR_CD = "VND_OTBD_CARR_CD";

    /** . */
    public static final String VND_OTBD_CARR_NM = "VND_OTBD_CARR_NM";

    /** . */
    public static final String PRO_NUM = "PRO_NUM";

    /** . */
    public static final String VND_SO_NUM = "VND_SO_NUM";

    /** . */
    public static final String VND_SO_SLP_NUM = "VND_SO_SLP_NUM";

    /** . */
    public static final String VND_SHIP_TO_CUST_CD = "VND_SHIP_TO_CUST_CD";

    /** . */
    public static final String VND_SHIP_TO_CUST_LOC_NM = "VND_SHIP_TO_CUST_LOC_NM";

    /** . */
    public static final String VND_SELL_TO_CUST_CD = "VND_SELL_TO_CUST_CD";

    /** . */
    public static final String VND_SELL_TO_CUST_LOC_NM = "VND_SELL_TO_CUST_LOC_NM";

    /** . */
    public static final String VND_CHILD_BOM_PRC_AMT = "VND_CHILD_BOM_PRC_AMT";

    /** . */
    public static final String PO_ACK_DTL_LTST_FLG = "PO_ACK_DTL_LTST_FLG";

    /** . */
    public static final String ORIG_VND_MDSE_CD = "ORIG_VND_MDSE_CD";

    /** . */
    public static final String PO_ACK_NUM = "PO_ACK_NUM";

    /** . */
    public static final String PO_SEND_TS = "PO_SEND_TS";

    /** . */
    public static final String VND_PO_ACK_STS_CD = "VND_PO_ACK_STS_CD";

    /** . */
    public static final String DEST_RTL_WH_CD = "DEST_RTL_WH_CD";

    /** . */
    public static final String SRC_RTL_WH_CD = "SRC_RTL_WH_CD";

    /** . */
    public static final String PO_ORD_SRC_CD = "PO_ORD_SRC_CD";

    /** . */
    public static final String PRNT_VND_NM = "PRNT_VND_NM";

    /** . */
    public static final String VND_DROP_SHIP_FLG = "VND_DROP_SHIP_FLG";

    /** . */
    public static final String PRCH_GRP_CD = "PRCH_GRP_CD";

    /** . */
    public static final String VND_PMT_TERM_CD = "VND_PMT_TERM_CD";

    /** . */
    public static final String VND_PMT_TERM_DESC_TXT = "VND_PMT_TERM_DESC_TXT";

    /** . */
    public static final String RQST_TECH_TOC_CD = "RQST_TECH_TOC_CD";

    /** . */
    public static final String RQST_RCV_DT = "RQST_RCV_DT";

    /** . */
    public static final String RQST_RCV_TM = "RQST_RCV_TM";
    // START 2023/07/20 S.Dong [QC#61638, ADD]
    /** . */
    public static final String RQST_SHIP_DT = "RQST_SHIP_DT";

    /** . */
    public static final String DTL_RQST_SHIP_DT = "DTL_RQST_SHIP_DT";
    // END 2023/07/20 S.Dong [QC#61638, ADD]
    /** . */
    public static final String CTAC_PSN_NM = "CTAC_PSN_NM";

    /** . */
    public static final String CARR_CD = "CARR_CD";

    /** . */
    public static final String CARR_ACCT_NUM = "CARR_ACCT_NUM";

    /** . */
    public static final String SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** . */
    public static final String RTRN_SHIP_TO_RTL_WH_CD = "RTRN_SHIP_TO_RTL_WH_CD";

    /** . */
    public static final String SHIP_FROM_SO_NUM_LIST_TXT = "SHIP_FROM_SO_NUM_LIST_TXT";

    /** . */
    public static final String DP_SHIP_FROM_SO_NUM_LIST_TXT = "DP_SHIP_FROM_SO_NUM_LIST_TXT";

    /** . */
    public static final String DPD_SHIP_FROM_SO_NUM_LIST_TXT = "DPD_SHIP_FROM_SO_NUM_LIST_TXT";

    /** . */
    public static final String FRT_CHRG_TO_CD = "FRT_CHRG_TO_CD";

    /** . */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** . */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** . */
    public static final String PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** . */
    public static final String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** . */
    public static final String PO_ORD_DTL_CMNT_TXT = "PO_ORD_DTL_CMNT_TXT";

    /** . */
    public static final String VND_NM = "VND_NM";

    /** . */
    public static final String PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /** . */
    public static final String TRX_REF_NUM = "TRX_REF_NUM";

    /** . */
    public static final String TRX_REF_LINE_NUM = "TRX_REF_LINE_NUM";

    /** . */
    public static final String TRX_REF_LINE_SUB_NUM = "TRX_REF_LINE_SUB_NUM";

    /** . */
    public static final String ASL_DTL_PK = "ASL_DTL_PK";

    /** . */
    public static final String ORIG_PO_ORD_NUM = "ORIG_PO_ORD_NUM";

    /** . */
    public static final String ORIG_PO_ORD_DTL_LINE_NUM = "ORIG_PO_ORD_DTL_LINE_NUM";

    /** . */
    public static final String ASL_MDSE_CD = "ASL_MDSE_CD";

    /** . */
    public static final String ASL_UNIT_PRC_AMT = "ASL_UNIT_PRC_AMT";

    /** . */
    public static final String ORIG_DISP_PO_DTL_LINE_NUM = "ORIG_DISP_PO_DTL_LINE_NUM";

    /** . */
    public static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** . */
    public static final String FRT_CHRG_METH_CD = "FRT_CHRG_METH_CD";

    /** . */
    public static final String PO_ORD_CMNT_TXT = "PO_ORD_CMNT_TXT";

    /** . */
    public static final String TRSMT_METH_TP_CD = "TRSMT_METH_TP_CD";

    /** . */
    public static final String SEND_PO_FAX_NUM = "SEND_PO_FAX_NUM";

    /** . */
    public static final String SEND_PO_EML_ADDR = "SEND_PO_EML_ADDR";

    /** . */
    public static final String CUST_UOM_CD = "CUST_UOM_CD";

    /** . */
    public static final String DEST_RTL_SWH_CD = "DEST_RTL_SWH_CD";

    /** . */
    public static final String SRC_RTL_SWH_CD = "SRC_RTL_SWH_CD";

    /** . */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** . */
    public static final String FRT_COND_CD = "FRT_COND_CD";

    /** . */
    public static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** . */
    public static final String CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";

    /** . */
    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** . */
    public static final String CUST_ISS_PO_DT = "CUST_ISS_PO_DT";

    /** . */
    public static final String CPO_ORD_TP_CD = "CPO_ORD_TP_CD";

    /** . */
    public static final String ORIG_MDSE_CD = "ORIG_MDSE_CD";

    /** . */
    public static final String FROM_STK_STS_CD = "FROM_STK_STS_CD";

    /** . */
    public static final String TO_STK_STS_CD = "TO_STK_STS_CD";

    /** . */
    public static final String ADMIN_PSN_CD = "ADMIN_PSN_CD";

    /** . */
    public static final String PO_MATCH_TP_CD = "PO_MATCH_TP_CD";

    /** . */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** . */
    public static final String PO_SEND_FLG = "PO_SEND_FLG";

    /** . */
    public static final String PO_PRINT_FLG = "PO_PRINT_FLG";

    /** . */
    public static final String EIP_RPT_RQST_PK = "EIP_RPT_RQST_PK";

    /** . */
    public static final String DISP_PO_DTL_LINE_NUM = "DISP_PO_DTL_LINE_NUM";

    /** . */
    public static final String PO_LINE_TP_CD = "PO_LINE_TP_CD";

    /** . */
    public static final String PO_MDSE_CMPSN_TP_CD = "PO_MDSE_CMPSN_TP_CD";

    /** . */
    public static final String SET_PO_ORD_DTL_LINE_NUM = "SET_PO_ORD_DTL_LINE_NUM";

    /** . */
    public static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /** . */
    public static final String PO_INV_QTY = "PO_INV_QTY";

    /** . */
    public static final String PO_DISP_QTY = "PO_DISP_QTY";

    /** . */
    public static final String EXCH_RATE = "EXCH_RATE";

    /** . */
    public static final String PO_DISP_UOM_CD = "PO_DISP_UOM_CD";

    /** . */
    public static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";

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
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** . */
    public static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    /** . */
    public static final String SHIP_TO_ACCT_NM = "SHIP_TO_ACCT_NM";

    /** . */
    public static final String DS_PO_TP_CD = "DS_PO_TP_CD";

    /** . */
    public static final String DS_PO_TP_NM = "DS_PO_TP_NM";

    /** . */
    public static final String PO_QLFY_CD = "PO_QLFY_CD";

    /** . */
    public static final String PO_SUBMT_PSN_CD = "PO_SUBMT_PSN_CD";

    /** . */
    public static final String PO_SUBMT_TS = "PO_SUBMT_TS";

    /** . */
    public static final String PO_APVL_PSN_CD = "PO_APVL_PSN_CD";

    /** . */
    public static final String PO_APVL_DT = "PO_APVL_DT";

    /** . */
    public static final String PO_APVL_TS = "PO_APVL_TS";

    /** . */
    public static final String PO_APVL_STS_CD = "PO_APVL_STS_CD";

    /** . */
    public static final String BIND_DS_PO_TP_CD = "dsPoTpCd";

    /** . */
    public static final String BIND_PO_ORD_SRC_CD = "poOrdSrcCd";

    /** . */
    public static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** . */
    public static final String ORD_QTY = "ORD_QTY";

    /** . */
    public static final String RQST_TOT_STD_COST_AMT = "RQST_TOT_STD_COST_AMT";

    /** . */
    public static final String PKG_UOM_CD = "PKG_UOM_CD";

    /** . */
    public static final String MDSE_CST_UPD_EFF_FROM_DT = "MDSE_CST_UPD_EFF_FROM_DT";

    /** . */
    public static final String MDSE_CST_UPD_STS_CD = "MDSE_CST_UPD_STS_CD";

    /** . */
    public static final String ENT_DEAL_NET_UNIT_PRC_AMT = "ENT_DEAL_NET_UNIT_PRC_AMT";

    /** . */
    public static final String ENT_FUNC_NET_UNIT_PRC_AMT = "ENT_FUNC_NET_UNIT_PRC_AMT";

    /** . */
    public static final String ENT_PO_DTL_DEAL_NET_AMT = "ENT_PO_DTL_DEAL_NET_AMT";

    /** . */
    public static final String ENT_PO_DTL_FUNC_NET_AMT = "ENT_PO_DTL_FUNC_NET_AMT";

    /** . */
    public static final String ERROR_ORIG_VND_MDSE_CD = "Origin Vendor Mdse Code";

    /** . */
    public static final String ERROR_ASL_MASTER = "ASL Master";

    /** . */
    public static final String ERROR_MDSE_MASTER = "Mdse Master";

    /** . */
    public static final String ERROR_SUPPLY_ITEM_NUMBER = "Supply Item Number";

    /**
     * The target data does not exist. Table Name: [@], Key Item: [@],
     * Key Value: [@].
     */
    public static final String NPAM1288E = "NPAM1288E";

    /** . */
    public static final String MAIL_FIELD_EDI_PO_ORD_NUM = "ediPoOrdNum";

    /** . */
    public static final String MAIL_FIELD_PO_ORD_NUM = "poOrdNum";

    /** . */
    public static final String MAIL_FIELD_EDI_STS = "ediSts";

    /** . */
    public static final String MAIL_FIELD_RCV_DATE = "rcvDate";

    /** . */
    public static final String MAIL_FIELD_INTERFACE_ID = "rcvDate";

    /** . */
    public static final String MAIL_FIELD_ERR_MSG = "errMsg";

    /** . */
    public static final String MAIL_FIELD_SUBMIT_DATE = "submitDate";

    /** . */
    public static final String MAIL_FIELD_USER_ID = "userId";

    /** . */
    public static final String MAIL_FIELD_USER_NM = "userNm";

    /** . */
    public static final String MAIL_FIELD_VND_NM = "vndNm";

    /** . */
    public static final String EDI_PO_ORD_DTL_LINE_NUM = "EDI_PO_ORD_DTL_LINE_NUM";

    /**
     * VAR_CONST: CREATE_MATERIAL_PARTS
     */
    public static final String VAR_CONST_CREATE_MATERIAL_PARTS = "CREATE_MATERIAL_PARTS";

    // START 2020/10/06 [QC#57795, ADD]
    /**
     * VAR_CONST: NPAB_1000_UPD_ASL_EXCL_VND
     */
    public static final String VAR_CONST_UPD_ASL_EXCL_VND = "NPAB1000_UPD_ASL_EXCL_VND";
    // START 2020/10/06 [QC#57795, ADD]

    // QC#28834 add start
    public static final String IF_ID_NPAA0031 = "NPAA0031";
    // QC#28834 add end
    // START 2023/07/20 S.Dong [QC#61638, ADD]
    public static final String IF_ID_NPAA0032 = "NPAA0032";
    // END 2023/07/20 S.Dong [QC#61638, ADD]

    // QC#28679 add start
    public static final int LINE_SPASE_9 = 9;

    public static final int LINE_SPASE_12 = 12;

    public static final int LINE_SPASE_18 = 18;

    public static final int LINE_SPASE_80 = 80;

    /** Mail Template ID 03 : */
    public static final String MAIL_TEMPLATE_ID_06 = "NPAB1000M006";

    public static final String BATCH_ID = "NPAB100001";
    // QC#28679 add end

    // 2018/12/05 QC#29494 Add Start
    /** Mail Field: errorDetail */
    public static final String MAIL_FIELD_ERR_DETAIL = "errorDetail";

    /** . */
    public static final String MAIL_FIELD_VND_CD = "vndCd";
    // 2018/12/05 QC#29494 Add End
}
