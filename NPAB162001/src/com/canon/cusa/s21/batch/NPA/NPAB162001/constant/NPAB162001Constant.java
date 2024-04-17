/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB162001.constant;


/**
 * <pre>
 * Constant Class for Receiving Price from Azerty<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 07/29/2016   CITS            N.Akaishi       Create          V1.0
 * </pre>
 */
public class NPAB162001Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NPAB162001";

    /** Attach File Name  */
    public static final String ATT_FILE_NAME = "NPAB1620_Receiving PriceFromAzerty";

    /** extension .csv */
    public static final String EXT_CSV = ".csv";

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: Interface Id */
    public static final String MSG_ITEM_INTERFACE_ID = "Interface Id";

    /** message Item: Sales Date */
    public static final String MSG_ITEM_SALES_DATE = "Sales Date";

    /** message Item: Vendor Code */
    public static final String MSG_ITEM_VENDOR_CODE = "Vendor Code";

    /** message Item: Parent Vendor Code */
    public static final String MSG_ITEM_PARENT_VENDOR_CODE = "Parent Vendor Code";

    /** message Item: CommitUnit */
    public static final String MSG_ITEM_COMMIT_UNIT = "Number of Commit Unit";

    /** bind param */
    /** SSM set Key Name: GLBL_CMPY_CD */
    public static final String KEY_GLBL_CMPY_CD = "glblCmpyCd";

    /** SSM set Key Name: INTERFACE_ID */
    public static final String KEY_INTERFACE_ID = "interfaceId";

    /** SSM set Key Name: TRANSACTION_ID */
    public static final String KEY_TRANSACTION_ID = "transactionId";

    /** SSM set Key Name: GRP_ID_VND_UOM_XREF */
    public static final String KEY_GRP_ID_VND_UOM_XREF = "grpIdVndUomXref";

    /** SSM set Key Name: EDI_PRC_CAT_UOM_TXT */
    public static final String KEY_EDI_PRC_CAT_UOM_TXT = "ediPrcCatUomTxt";

    /** SSM set Key Name: SALES_DATE */
    public static final String KEY_SALES_DATE = "slsDt";

    /** SSM set Key Name: ASL_QLFY_TP_CD_02 */
    public static final String KEY_ASL_QLFY_TP_CD = "aslQlfyTpCd02";

    /** SSM set Key Name: VND_CD */
    public static final String KEY_VND_CD = "vndCd";

    /** SSM set Key Name: PRNT_VND_CD */
    public static final String KEY_PRNT_VND_CD = "prntVndCd";

    /** SSM set Key Name: EFF_THRU_DT_DEFAULT */
    public static final String KEY_EFF_THRU_DT_DEFAULT = "effThruDtDefault";

    /** EFF_THRU_DT_DEFAULT : 99991231 */
    public static final String VAL_EFF_THRU_DT_DEFAULT = "99991231";

    /** message code */
    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value. */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** [@] does not exist. Table:[@], Search Key:[@] */
    public static final String NPAM1537E = "NPAM1537E";

    /** [@] Parameter has not been set. */
    public static final String NPAM1539E = "NPAM1539E";

    /** Error of API [@] */
    public static final String NPAM1178E = "NPAM1178E";

    /** MAX_ASL_DTL_CNT(for ASL Update API) */
    public static final int MAX_ASL_DTL_CNT = 1000;

    /** INDEX : 1 */
    public static final int IDX_1 = 1;

    /** INDEX : 3 */
    public static final int IDX_3 = 3;

    /** Value : "0" */
    public static final String VAL_ZERO = "0";

    /** Value : "" */
    public static final String VAL_EMPTY = "";

    /** DS_COND_CONST Group Id : VND_UOM_XREF */
    public static final String DS_COND_CONST_KEY_VND_UOM_XREF = "VND_UOM_XREF";

    /** KEY_VND_SYS_TP_CD_A */
    public static final String KEY_VND_SYS_TP_CD_A = "vndSysTpCdA";

    /** VAL_VND_SYS_TP_CD_A */
    public static final String VAL_VND_SYS_TP_CD_A = "A";

    /** Mail */
    /** Template ID : NPAB1620M001 */
    public static final String MAIL_TEMPLATE_ID_NPAB1620M001 = "NPAB1620M001";

    /** Template ID : NPAB1620M002 */
    public static final String MAIL_TEMPLATE_ID_NPAB1620M002 = "NPAB1620M002";

    /** Mail Group ID(To) - NPAB162001  */
    public static final String MAIL_GROUP_ID_TO_NPAB162001 = "NPAB162001";

    /** Mail Group ID(To) - NPAB162002  */
    public static final String MAIL_GROUP_ID_TO_NPAB162002 = "NPAB162002";

    /** Mail Group ID(From) */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

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
    /** CSV_HEADER_EDI_PRC_CAT_VND_PROD_NUM */
    public static final String CSV_HEADER_EDI_PRC_CAT_VND_PROD_NUM = "Vendor Product";

    /** CSV_HEADER_EDI_PRC_CAT_EFF_FROM_DT */
    public static final String CSV_HEADER_EDI_PRC_CAT_EFF_FROM_DT = "Effective From";

    /** CSV_HEADER_EDI_PRC_CAT_EFF_THRU_DT */
    public static final String CSV_HEADER_EDI_PRC_CAT_EFF_THRU_DT = "Effective To";

    /** CSV_HEADER_EDI_PRC_CAT_UOM_TXT */
    public static final String CSV_HEADER_EDI_PRC_CAT_UOM_TXT = "UOM";

    /** CSV_HEADER_EDI_PRC_CAT_UNIT_PRC_TXT */
    public static final String CSV_HEADER_EDI_PRC_CAT_UNIT_PRC_TXT = "Unit Price";

    /** CSV_HEADER_ERR_MSG */
    public static final String CSV_HEADER_ERR_MSG = "Error Messages";

    /** SQL Statement Id */
    /** getPoAckIntfcLst */
    public static final String STMT_GET_EDI_PRC_LIST = "getEdiPrcList";

    /** getAzertyVndCd */
    public static final String STMT_GET_AZERTY_VND_CD = "getAzertyVndCd";

    /** getAzertyPrntVndCd */
    public static final String STMT_GET_AZERTY_PRNT_VND_CD = "getAzertyPrntVndCd";

    /** table name */
    /** NPAI2110_01 */
    public static final String TBL_NPAI2110_01 = "NPAI2110_01";

    /** NPAI2110_02 */
    public static final String TBL_NPAI2110_02 = "NPAI2110_02";

    /** RCV_ASN_VND */
    public static final String TBL_RCV_ASN_VND = "RCV_ASN_VND";

    /** PRNT_VND */
    public static final String TBL_PRNT_VND = "PRNT_VND";

    /** db column name */
    /** GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** PRNT_VND_CD */
    public static final String COL_PRNT_VND_CD = "PRNT_VND_CD";

    /** VND_SYS_TP_CD */
    public static final String COL_VND_SYS_TP_CD = "VND_SYS_TP_CD";

    /** EDI_PRC_CAT_BIG_DEAL_NUM */
    public static final String COL_EDI_PRC_CAT_BIG_DEAL_NUM = "EDI_PRC_CAT_BIG_DEAL_NUM";

    /** EDI_PRC_CAT_VND_PROD_NUM */
    public static final String COL_EDI_PRC_CAT_VND_PROD_NUM = "EDI_PRC_CAT_VND_PROD_NUM";

    /** EDI_PRC_CAT_UOM_TXT */
    public static final String COL_EDI_PRC_CAT_UOM_TXT = "EDI_PRC_CAT_UOM_TXT";

    /** EDI_PRC_CAT_UNIT_PRC_TXT */
    public static final String COL_EDI_PRC_CAT_UNIT_PRC_TXT = "EDI_PRC_CAT_UNIT_PRC_TXT";

    /** EDI_PRC_CAT_EFF_FROM_DT */
    public static final String COL_EDI_PRC_CAT_EFF_FROM_DT = "EDI_PRC_CAT_EFF_FROM_DT";

    /** EDI_PRC_CAT_EFF_THRU_DT */
    public static final String COL_EDI_PRC_CAT_EFF_THRU_DT = "EDI_PRC_CAT_EFF_THRU_DT";

    /** EDI_PRC_CAT_CCY_CD */
    public static final String COL_EDI_PRC_CAT_CCY_CD = "EDI_PRC_CAT_CCY_CD";

    /** EDI_PRC_CAT_ACT_TP_TXT */
    public static final String COL_EDI_PRC_CAT_ACT_TP_TXT = "EDI_PRC_CAT_ACT_TP_TXT";

    /** EDI_SHIP_FROM_CUST_NM */
    public static final String COL_EDI_SHIP_FROM_CUST_NM = "EDI_SHIP_FROM_CUST_NM";

    /** MDSE_CD */
    public static final String COL_MDSE_CD = "MDSE_CD";

    /** VND_UOM_CD */
    public static final String COL_VND_UOM_CD = "VND_UOM_CD";

    /** SPLY_ITEM_NUM */
    public static final String COL_SPLY_ITEM_NUM = "SPLY_ITEM_NUM";

    /** VND_CD */
    public static final String COL_VND_CD = "VND_CD";

    /** UNIT_PRC_AMT */
    public static final String COL_UNIT_PRC_AMT = "UNIT_PRC_AMT";

    /** BASE_ORD_QTY */
    public static final String COL_BASE_ORD_QTY = "BASE_ORD_QTY";

    /** VND_UOM_QTY */
    public static final String COL_VND_UOM_QTY = "VND_UOM_QTY";

    /** EFF_FROM_DT */
    public static final String COL_EFF_FROM_DT = "EFF_FROM_DT";

    /** EFF_THRU_DT */
    public static final String COL_EFF_THRU_DT = "EFF_THRU_DT";

    /** ASL_QLFY_REF_CD */
    public static final String COL_ASL_QLFY_REF_CD = "ASL_QLFY_REF_CD";

    /** DCC_VND_UOM_CD */
    public static final String COL_DCC_VND_UOM_CD = "DCC_VND_UOM_CD";

    // EDI_PRC_CAT_UNIT_PRC_TXT
    /** EDI_PRC_CAT_UNIT_PRC_TXT(digit)  : 19 */
    public static final int NUM_EDI_PRC_CAT_UNIT_PRC_TXT_DIGITS = 19;

    /** EDI_PRC_CAT_UNIT_PRC_TXT(frac digit)  : 4 */
    public static final int NUM_EDI_PRC_CAT_UNIT_PRC_TXT_FRAC_DIGITS = 2;

    /** ZYPCommonFunc.isDigits Real numeric: 2 */
    public static final int CHECK_DIGITS_TP_REAL_NUMERIC = 2;

    /** API Error Code Prefix : E */
    public static final String ERROR_CODE_E = "E";
}
