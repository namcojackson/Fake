/*
 * <Pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB261001.constant;

/**
 * <pre>
 * Constant Class for Receiving PO ACK WRK from EDI Vendor<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 09/07/2018   CITS            M.Naito         Create          QC#26964,26965
 * 11/12/2018   CITS            M.Naito         Update          QC#29050
 * 02/25/2019   Fujitsu         S.Takami        Update          QC#30453
 * 03/08/2023   Hitachi         E.Watabe        Update          QC#61128
 * 09/25/2023   Hitachi         G.Quan          Update          QC#61608
 * </pre>
 */
public class NPAB261001Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NPAB261001";

    /** Attachment File Name : ReceivingPoAckFromEDIVendor */
    public static final String ATT_FILE_NAME = "NPAB2610_ReceivingPoAckWrkFromEDIVendor";

    /** File Extension : .csv */
    public static final String EXT_CSV = ".csv";

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: Interface Id */
    public static final String MSG_ITEM_INTERFACE_ID = "Interface Id";

    /** message Item: CommitUnit */
    public static final String MSG_ITEM_COMMIT_UNIT = "Number of Commit Unit";

    /** bind param */
    /** SSM set Key Name: GLBL_CMPY_CD */
    public static final String KEY_GLBL_CMPY_CD = "glblCmpyCd";

    /** SSM set Key Name: INTERFACE_ID */
    public static final String KEY_INTERFACE_ID = "interfaceId";

    /** SSM set Key Name: TRANSACTION_ID */
    public static final String KEY_TRANSACTION_ID = "transactionId";

    /** SSM set Key Name: EDI_PO_ORD_NUM */
    public static final String KEY_EDI_PO_ORD_NUM = "ediPoOrdNum";

    /** SSM set Key Name: PO_ORD_NUM */
    public static final String KEY_PO_ORD_NUM = "poOrdNum";

    /** SSM set Key Name: PO_ORD_DTL_LINE_NUM */
    public static final String KEY_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    /** SSM set Key Name: ASL_MDSE_CD */
    public static final String KEY_ASL_MDSE_CD = "aslMdseCd";

    /** SSM set Key Name: KEY_VND_SYS_TP_CD */
    public static final String KEY_VND_SYS_TP_CD = "vndSysTpCd";

    /** KEY_VND_CD_AZERTY */
    public static final String KEY_VND_CD = "vndCd";

    /** KEY_ST_CD */
    public static final String KEY_ST_CD = "stCd";

    /** KEY_VND_SHIP_METH_CD */
    public static final String KEY_VND_SHIP_METH_CD = "vndShipMethCd";

    /** KEY_AST */
    public static final String KEY_AST = "ast";

    /** KEY_GRP_ID_VND_UOM_XREF */
    public static final String KEY_GRP_ID_VND_UOM_XREF = "grpIdVndUomXref";

    /** KEY_PO_ACK_DTL_UOM_CD */
    public static final String KEY_PO_ACK_DTL_UOM_CD = "poAckDtlUomCd";

    /** KEY_MDSE_CD */
    public static final String KEY_MDSE_CD = "mdseCd";

    /** KEY_BAT_DT */
    public static final String KEY_BAT_DT = "batDt";

    // START 2018/11/12 M.Naito [QC#29050,ADD]
    /** KEY_BASE_PKG_UOM_CD */
    public static final String KEY_BASE_PKG_UOM_CD = "basePkgUomCd";

    /** KEY_PKG_UOM_CD */
    public static final String KEY_PKG_UOM_CD = "pkgUomCd";
    // END 2018/11/12 M.Naito [QC#29050,ADD]

    /** message id : [@] does not exist. Table:[@], Search Key:[@] */
    public static final String NPAM1537E = "NPAM1537E";

    /** message id : NPAM1538E */
    public static final String NPAM1538E = "NPAM1538E";

    /** message id : NPAM1539E */
    public static final String NPAM1539E = "NPAM1539E";

    /** message id : It failed to register [@]. */
    public static final String NPAM1557E = "NPAM1557E";

    /** message id : Failed to get @ */
    public static final String NPAM1328E = "NPAM1328E";

    // START 2019/02/25 S.Takami [QC#30453,ADD]
    /** Purchase order detail from interface does not found. [Interface ID : @] */
    public static final String NPAM1642E = "NPAM1642E";
    // End 2019/02/25 S.Takami [QC#30453,ADD]

    /** MAX_COMMIT_NUMBER */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** INDEX : 1 */
    public static final int IDX_1 = 1;

    /** INDEX : 3 */
    public static final int IDX_3 = 3;

    /** Value : "0" */
    public static final String VAL_ZERO = "0";

    /** Value : "" */
    public static final String VAL_EMPTY = "";

    /** Default Value(DTL_SUB_LINE_NUM) : "001" */
    public static final String VAL_DEF_DTL_SUB_LINE_NUM = "001";

    /** Default Value(CCY_CD) : "USD" */
    public static final String VAL_DEF_CCY_CD = "USD";

    /** VAL_AST */
    public static final String VAL_AST = "*";

    /** DS_COND_CONST Group Id : VND_UOM_XREF */
    public static final String DS_COND_CONST_KEY_VND_UOM_XREF = "VND_UOM_XREF";

    /** Mail */
    /** Template ID : NPAB2610M001 */
    public static final String MAIL_TEMPLATE_ID_NPAB2610M001 = "NPAB2610M001";

    /** Template ID : NPAB2610M002 */
    public static final String MAIL_TEMPLATE_ID_NPAB2610M002 = "NPAB2610M002";

    /** Mail Group ID(From) */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Mail Group ID(To) - NPAB261001  */
    public static final String MAIL_GROUP_ID_TO_NPAB261001 = "NPAB261001";

    /** Mail Group ID(To) - NPAB261002  */
    public static final String MAIL_GROUP_ID_TO_NPAB261002 = "NPAB261002";

    /** Mail Key (Fatal Error) : S21Operator */
    public static final String MAIL_TO_KEY_1 = "S21Operator";

    /** Mail Key (Application Error) : Supplier Group */
    public static final String MAIL_TO_KEY_2 = "Supplier Group";

    /** Mail Item : batchId */
    public static final String MAIL_ITEM_BATCHID = "batchId";

    /** Mail Item (for Fatal Error) : errorDate */
    public static final String MAIL_ITEM_ERR_DATE = "errDate";

    /** Mail Item (for Fatal Error) : message */
    public static final String MAIL_ITEM_MESSAGE = "message";

    /** Mail Date Format : MM/dd/yyyy HH:mm:ss.SSS */
    public static final String MAIL_DATE_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

    /** Comma */
    public static final String COMMA = ",";

    /** CRLF */
    public static final String CRLF = "\r\n";

    /** CSV HEADER */
    /** PO# */
    public static final String CSV_HEADER_EDI_ACK_PO_NUM = "PO#";

    /** PO Line# */
    public static final String CSV_HEADER_EDI_ACK_PO_LINE_NUM = "PO Line#";

    /** Item# */
    public static final String CSV_HEADER_EDI_ACK_VND_ITEM_CD = "Item#";

    /** Error Messages */
    public static final String CSV_HEADER_ERR_MSG = "Error Messages";

    /** SQL Statement Id */
    /** getPoAckIntfcLst */
    public static final String SQL_STMT_ID_GET_PO_ACK_INTFC_LST = "getPoAckIntfcLst";

    /** getMaxPoAckNumFromPoAckHdrWrk */
    public static final String SQL_STMT_ID_GET_MAX_PO_ACK_NUM_FROM_PO_HDR_WRK = "getMaxPoAckNumFromPoAckHdrWrk";

    /** getMaxPoAckNumFromPoAckHdr */
    public static final String SQL_STMT_ID_GET_MAX_PO_ACK_NUM_FROM_PO_HDR = "getMaxPoAckNumFromPoAckHdr";

    /** getCsaMdseInfo */
    public static final String SQL_STMT_ID_GET_CSA_MDSE_INFO = "getCsaMdseInfo";

    /** getVndUom */
    public static final String SQL_STMT_ID_GET_VND_UOM = "getVndUom";

    /** getPoInfo */
    public static final String SQL_STMT_ID_GET_PO_INFO = "getPoInfo";

    /** getDelyLeadAot */
    public static final String SQL_STMT_ID_GET_DELY_LEAD_AOT = "getDelyLeadAot";

    /** getVndCd */
    public static final String SQL_STMT_ID_GET_VND_CD = "getVndCd";

    /** getMdseInfo */
    public static final String SQL_STMT_ID_GET_MDSE_NM = "getMdseNm";

    /** getSplyItemNumFromAsl */
    public static final String SQL_STMT_ID_GET_SPLY_ITEM_NUM_FROM_ASL = "getSplyItemNumFromAsl";

    // START 2018/11/12 M.Naito [QC#29050,ADD]
    /** getMdseStorePkgInfo */
    public static final String SQL_STMT_ID_GET_MDSE_STORE_PKG = "getMdseStorePkg";
    // END 2018/11/12 M.Naito [QC#29050,ADD]

    /** table name */
    /** NPAI0410_01 */
    public static final String TBL_NPAI0410_01 = "NPAI0410_01";

    /** NPAI0410_04 */
    public static final String TBL_NPAI0410_04 = "NPAI0410_04";

    /** InterfaceID : TST/Impreso */
    public static final String INTFC_ID_TST_IMPRESO = "NPAI0410";

    /** InterfaceID : Dietzgen */
    public static final String INTFC_ID_DIETZGEN = "NPAI0510";
    
    // START 2023/03/08 E.Watabe [QC#61128,ADD]
    /** InterfaceID : Hytec */
    public static final String INTFC_ID_HYTEC = "NPAI0610";
    // END 2023/03/08 E.Watabe [QC#61128,ADD]

    // START 2023/09/25 G.Quan [QC#61608, ADD]
    /** InterfaceID : Atrix */
    public static final String INTFC_ID_ATRIX = "NPAI0810";
    // END 2023/09/25 G.Quan [QC#61608, ADD]

    /** PO_ACK_HDR_WRK */
    public static final String TBL_PO_ACK_HDR_WRK = "PO_ACK_HDR_WRK";

    /** PO_ACK_DTL_WRK */
    public static final String TBL_PO_ACK_DTL_WRK = "PO_ACK_DTL_WRK";

    /** RCV_ASN_VND */
    public static final String TBL_RCV_ASN_VND = "RCV_ASN_VND";

    /** column name */
    /** PO_ACK_ORD_NUM */
    public static final String COLUMN_PO_ACK_ORD_NUM = "PO_ACK_ORD_NUM";

    /** PO_ACK_ORD_DTL_LINE_NUM */
    public static final String COLUMN_PO_ACK_ORD_DTL_LINE_NUM = "PO_ACK_ORD_DTL_LINE_NUM";

    /** PO_ACK_LINE_STS_CD */
    public static final String COLUMN_PO_ACK_LINE_STS_CD = "PO_ACK_LINE_STS_CD";

    /** PO_ACK_LINE_STS_NM */
    public static final String COLUMN_PO_ACK_LINE_STS_NM = "PO_ACK_LINE_STS_NM";

    /** PO_ACK_MDSE_CD */
    public static final String COLUMN_PO_ACK_MDSE_CD = "PO_ACK_MDSE_CD";

    /** PO_ACK_DTL_PO_ACK_CD */
    public static final String COLUMN_PO_ACK_DTL_PO_ACK_CD = "PO_ACK_DTL_PO_ACK_CD";

    /** DTL_PO_ACK_ORD_NUM(NPAI0410_04.PO_ACK_ORD_NUM) */
    public static final String COLUMN_DTL_PO_ACK_ORD_NUM = "DTL_PO_ACK_ORD_NUM";

    /** PO_ACK_CONF_QTY */
    public static final String COLUMN_PO_ACK_CONF_QTY = "PO_ACK_CONF_QTY";

    /** PO_ACK_DTL_RDD_DT */
    public static final String COLUMN_PO_ACK_DTL_RDD_DT = "PO_ACK_DTL_RDD_DT";

    /** PO_ACK_DTL_UOM_CD */
    public static final String COLUMN_PO_ACK_DTL_UOM_CD = "PO_ACK_DTL_UOM_CD";

    /** MDSE_NM */
    public static final String COLUMN_MDSE_NM = "MDSE_NM";

    /** PO_ORD_DTL_LINE_NUM */
    public static final String COLUMN_PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** SHPG_SVC_LVL_CD */
    public static final String COLUMN_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** SHIP_TO_ST_CD */
    public static final String COLUMN_SHIP_TO_ST_CD = "SHIP_TO_ST_CD";

    /** VND_CD */
    public static final String COLUMN_VND_CD = "VND_CD";

    /** PO_ACK_HDR_CRAT_DT */
    public static final String COLUMN_PO_ACK_HDR_CRAT_DT = "PO_ACK_HDR_CRAT_DT";

    /** PO_ACK_HDR_CRAT_TM */
    public static final String COLUMN_PO_ACK_HDR_CRAT_TM = "PO_ACK_HDR_CRAT_TM";

    /** PO_ACK_HDR_VND_ORD_NUM */
    public static final String COLUMN_PO_ACK_HDR_VND_ORD_NUM = "PO_ACK_HDR_VND_ORD_NUM";

    /** PO_ACK_ORD_PRC_NUM */
    public static final String COLUMN_PO_ACK_ORD_PRC_NUM = "PO_ACK_ORD_PRC_NUM";

    /** VND_PO_ACK_STS_CD */
    public static final String COLUMN_VND_PO_ACK_STS_CD = "VND_PO_ACK_STS_CD";

}
