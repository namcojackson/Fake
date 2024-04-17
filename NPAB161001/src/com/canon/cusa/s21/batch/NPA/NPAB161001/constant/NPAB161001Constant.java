/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB161001.constant;


/**
 * <pre>
 * Constant Class for Receiving PO ACK from Azerty<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 07/01/2016   CITS            N.Akaishi       Create          V1.0
 * </pre>
 */
public class NPAB161001Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NPAB161001";

    /** Application Name : Receiving POAckFromAzerty  */
    public static final String APP_NAME = "Receiving POAckFromAzerty";

    /** Attachment File Name : Receiving POAckFromAzerty */
    public static final String ATT_FILE_NAME = "Receiving POAckFromAzerty";

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

    /** SSM set Key Name: EDI_ACK_PO_NUM */
    public static final String KEY_EDI_ACK_PO_NUM = "ediAckPoNum";

    /** SSM set Key Name: EDI_ACK_VND_ITEM_CD */
    public static final String KEY_EDI_ACK_VND_ITEM_CD = "ediAckVndItemCd";

    /** SSM set Key Name: GRP_ID_VND_UOM_XREF */
    public static final String KEY_GRP_ID_VND_UOM_XREF = "grpIdVndUomXref";

    /** SSM set Key Name: EDI_ACK_UOM_TXT_02 */
    public static final String KEY_EDI_ACK_UOM_TXT_02 = "ediAckUomTxt02";

    /** SSM set Key Name: EDI_PO_ORD_NUM */
    public static final String KEY_EDI_PO_ORD_NUM = "ediPoOrdNum";

    /** SSM set Key Name: PO_ORD_NUM */
    public static final String KEY_PO_ORD_NUM = "poOrdNum";

    /** SSM set Key Name: PO_ORD_DTL_LINE_NUM */
    public static final String KEY_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    /** KEY_VND_CD_AZERTY */
    public static final String KEY_VND_CD_AZERTY = "vndCdAzerty";

    /** KEY_ST_CD */
    public static final String KEY_ST_CD = "stCd";

    /** KEY_VND_SHIP_METH_CD */
    public static final String KEY_VND_SHIP_METH_CD = "vndShipMethCd";

    /** KEY_VND_SYS_TP_CD_A */
    public static final String KEY_VND_SYS_TP_CD_A = "vndSysTpCdA";

    /** KEY_AST */
    public static final String KEY_AST = "ast";

    /** message id : NPAM1537E */
    public static final String NPAM1537E = "NPAM1537E";

    /** message id : NPAM1538E */
    public static final String NPAM1538E = "NPAM1538E";

    /** message id : NPAM1539E */
    public static final String NPAM1539E = "NPAM1539E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

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

    /** length : PO_DTL.PO_ORD_DTL_LINE_NUM  */
    public static final int LEN_PO_ORD_DTL_LINE_NUM = 3;

    /** DS_COND_CONST Group Id : VND_UOM_XREF */
    public static final String DS_COND_CONST_KEY_VND_UOM_XREF = "VND_UOM_XREF";

    /** Mail */
    /** Template ID : NPAB1610M001 */
    public static final String MAIL_TEMPLATE_ID_NPAB1610M001 = "NPAB1610M001";

    /** Template ID : NPAB1610M002 */
    public static final String MAIL_TEMPLATE_ID_NPAB1610M002 = "NPAB1610M002";

    /** Mail Group ID(From) */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Mail Group ID(To) - NPAB161001  */
    public static final String MAIL_GROUP_ID_TO_NPAB161001 = "NPAB161001";

    /** Mail Group ID(To) - NPAB161002  */
    public static final String MAIL_GROUP_ID_TO_NPAB161002 = "NPAB161002";

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

    /** Date Format DATE */
    public static final String TIME_FORMAT = "yyyyMMddHHmmssSSS";

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

    /** getAzertyLt */
    public static final String SQL_STMT_ID_GET_AZERTY_LT = "getAzertyLt";

    /** getAzertyVndCd */
    public static final String SQL_STMT_ID_GET_AZERTY_VND_CD = "getAzertyVndCd";

    /** table name */
    /** NPAI2100_01 */
    public static final String TBL_NPAI2100_01 = "NPAI2100_01";

    /** NPAI2100_02 */
    public static final String TBL_NPAI2100_02 = "NPAI2100_02";

    /** PO_ACK_HDR_WRK */
    public static final String TBL_PO_ACK_HDR_WRK = "PO_ACK_HDR_WRK";

    /** PO_ACK_DTL_WRK */
    public static final String TBL_PO_ACK_DTL_WRK = "PO_ACK_DTL_WRK";

    /** PO_DTL */
    public static final String TBL_PO_DTL = "PO_DTL";

    /** RCV_ASN_VND */
    public static final String TBL_RCV_ASN_VND = "RCV_ASN_VND";

    /** column name */
    /** EDI_ACK_PO_NUM */
    public static final String COLUMN_EDI_ACK_PO_NUM = "EDI_ACK_PO_NUM";

    /** EDI_ACK_PO_LINE_NUM */
    public static final String COLUMN_EDI_ACK_PO_LINE_NUM = "EDI_ACK_PO_LINE_NUM";

    /** PO_ACK_LINE_STS_CD */
    public static final String COLUMN_PO_ACK_LINE_STS_CD = "PO_ACK_LINE_STS_CD";

    /** PO_ACK_LINE_STS_NM */
    public static final String COLUMN_PO_ACK_LINE_STS_NM = "PO_ACK_LINE_STS_NM";

    /** EDI_ACK_VND_ITEM_CD */
    public static final String COLUMN_EDI_ACK_VND_ITEM_CD = "EDI_ACK_VND_ITEM_CD";

    /** EDI_ACK_ACK_CD */
    public static final String COLUMN_EDI_ACK_ACK_CD = "EDI_ACK_ACK_CD";

    /** DTL_EDI_ACK_PO_NUM(NPAI2100_02.EDI_ACK_PO_NUM) */
    public static final String COLUMN_DTL_EDI_ACK_PO_NUM = "DTL_EDI_ACK_PO_NUM";

    /** EDI_ACK_QTY_EDI_TXT */
    public static final String COLUMN_EDI_ACK_QTY_EDI_TXT = "EDI_ACK_QTY_EDI_TXT";

    /** EDI_ACK_ACK_QTY_EDI_TXT */
    public static final String COLUMN_EDI_ACK_ACK_QTY_EDI_TXT = "EDI_ACK_ACK_QTY_EDI_TXT";

    /** EDI_ACK_TRSMT_DT */
    public static final String COLUMN_EDI_ACK_TRSMT_DT = "EDI_ACK_TRSMT_DT";

    /** EDI_ACK_FOB_TXT */
    public static final String COLUMN_EDI_ACK_FOB_TXT = "EDI_ACK_FOB_TXT";

    /** EDI_ACK_UOM_TXT_02 */
    public static final String COLUMN_EDI_ACK_UOM_TXT_02 = "EDI_ACK_UOM_TXT_02";

    /** MDSE_CD */
    public static final String COLUMN_MDSE_CD = "MDSE_CD";

    /** MDSE_NM */
    public static final String COLUMN_MDSE_NM = "MDSE_NM";

    /** DELY_LEAD_AOT */
    public static final String COLUMN_DELY_LEAD_AOT = "DELY_LEAD_AOT";

    /** EDI_ACK_CD : "AC" */
    public static final String EDI_ACK_CD_AC = "AC";

    /** PO_ORD_DTL_LINE_NUM */
    public static final String COLUMN_PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** SHPG_SVC_LVL_CD */
    public static final String COLUMN_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** SHIP_TO_ST_CD */
    public static final String COLUMN_SHIP_TO_ST_CD = "SHIP_TO_ST_CD";

    /** VND_CD */
    public static final String COLUMN_VND_CD = "VND_CD";

}
