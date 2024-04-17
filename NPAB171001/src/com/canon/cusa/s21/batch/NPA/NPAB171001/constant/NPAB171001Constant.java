/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB171001.constant;

/**
 * <pre>
 * Constant Class for Receiving PO ASN from Azerty<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 07/06/2016   CITS            M.Naito         Create          V1.0
 * 02/08/2018   CITS            T.Tokutomi      Update          QC#23339
 * </pre>
 */
public class NPAB171001Constant {

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: Interface Id */
    public static final String MSG_ITEM_INTERFACE_ID = "Interface Id";

    /** SSM set Key Name: GLBL_CMPY_CD */
    public static final String KEY_GLBL_CMPY_CD = "glblCmpyCd";

    /** SSM set Key Name: INTERFACE_ID */
    public static final String KEY_INTERFACE_ID = "interfaceId";

    /** SSM set Key Name: TRANSACTION_ID */
    public static final String KEY_TRANSACTION_ID = "transactionId";

    /** SSM set Key Name: KEY_VND_SYS_TP_CD */
    public static final String KEY_VND_SYS_TP_CD = "vndSysTpCd";

    /** SSM set Key Name: KEY_DEST_ST_CD */
    public static final String KEY_DEST_ST_CD = "destStCd";

    /** SSM set Key Name: KEY_VND_CD_AZERTY */
    public static final String KEY_VND_CD_AZERTY = "vndCdAzerty";

    /** SSM set Key Name: EDI_ASN_HDR_BOL_NUM */
    public static final String KEY_EDI_ASN_HDR_BOL_NUM = "ediAsnHdrBolNum";

    /** SSM set Key Name: EDI_ASN_HDR_BOL_NUM_HYPHN */
    public static final String KEY_EDI_ASN_HDR_BOL_NUM_HYPHN = "ediAsnHdrBolNumHyphn";

    /** SSM set Key Name: EDI_ASN_SHIP_METH_TXT */
    public static final String KEY_EDI_ASN_SHIP_METH_TXT = "ediAsnShipMethTxt";

    /** SSM set Key Name: SEGMENT_ID */
    public static final String KEY_SEGMENT_ID = "segmentId";

    /** SSM set Key Name: UNIT_ID */
    public static final String KEY_UNIT_ID = "unitId";

    /** SSM set Key Name: KEY_EDI_ASN_DTL_BOL_NUM */
    public static final String KEY_EDI_ASN_DTL_BOL_NUM = "ediAsnDtlBolNum";

    /** SSM set Key Name: EDI_PO_ORD_NUM */
    public static final String KEY_EDI_PO_ORD_NUM = "poOldNum";

    /** SSM set Key Name: PO_ORD_NUM */
    public static final String KEY_PO_ORD_NUM = "poOrdNum";

    /** SSM set Key Name: PO_ORD_DTL_LINE_NUM */
    public static final String KEY_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    /** SSM set Key Name: KEY_ST_CD */
    public static final String KEY_ST_CD = "stCd";

    /** SSM set Key Name: KEY_VND_SHIP_METH_CD */
    public static final String KEY_VND_SHIP_METH_CD = "vndShipMethCd";

    /** SSM set Key Name: KEY_VND_SYS_TP_CD_A */
    public static final String KEY_VND_SYS_TP_CD_A = "vndSysTpCdA";

    /** QC#23339 Add SSM set Key Name: KEY_ASN_SO_NUM */
    public static final String KEY_ASN_SO_NUM = "asnSoNum";

    /** QC#23339 Add SSM set Key Name: KEY_ASN_MDSE_CD */
    public static final String KEY_ASN_MDSE_CD = "asnMdseCd";

    /** QC#23339 Add SSM set Key Name: KEY_EDI_PO_ORD_NUM_FOR_SER */
    public static final String KEY_EDI_PO_ORD_NUM_FOR_SER = "ediPoOrdNum";

    /** QC#23339 Add SSM set Key Name: KEY_EDI_ASN_PO_DTL_LINE_NUM */
    public static final String KEY_EDI_ASN_PO_DTL_LINE_NUM = "ediAsnPoDtlLineNum";

    /** KEY_AST */
    public static final String KEY_AST = "ast";

    /** ASN_SO_NUM_BR_NUM */
    public static final String ASN_SO_NUM_BR_NUM = "-[0-9]{2}$";

    /** Max Value(EDI_ASN_SHIP_METH_TXT) */
    public static final int MAX_EDI_ASN_SHIP_METH_TXT = 20;

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** message id : NPAM1537E */
    public static final String NPAM1537E = "NPAM1537E";

    /** MAX_COMMIT_NUMBER */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /**  Default Value(ASN_EDI_PROC_STS_CD) : "S" */
    public static final String ASN_EDI_PROC_STS_CD = "S";

    /** [@] Parameter has not been set.  */
    public static final String NPGM0009E = "NPGM0009E";

    /** Value : "0" */
    public static final String VAL_ZERO = "0";

    /** Value 01 */
    public static final String VAL_01 = "01";

    /** Value 001 */
    public static final String VAL_001 = "001";

    /** Date Format : yyyyMMddHHmmSSsss */
    public static final String DATE_FORMAT = "yyyyMMddHHmmSS";

    /** Hyphen */
    public static final String HYPHEN = "-";

    /** Asterisk */
    public static final String ASTERISK = "*";

    /** CARET */
    public static final String CARET = "^";

    /** Comma */
    public static final String COMMA = ",";

    /** length : PO_DTL.PO_ORD_DTL_LINE_NUM  */
    public static final int LEN_PO_ORD_DTL_LINE_NUM = 3;


    /** SQL Statement Id */
    /** getPoAsnIntfcHdr */
    public static final String SQL_STMT_ID_GET_PO_ASN_INTFC_HDR = "getPoAsnIntfcHdr";

    /** getPoAsnIntfcDtl */
    public static final String SQL_STMT_ID_GET_PO_ASN_INTFC_DTL = "getPoAsnIntfcDtl";

    /** getPoAsnBolNum */
    public static final String SQL_STMT_ID_GET_PO_ASN_SO_NUM = "getPoAsnSoNum";

    /** getPoAsnLeadTime */
    public static final String SQL_STMT_ID_GET_PO_ASN_LEAD_TIME = "getPoAsnLeadTime";

    /** getAzertyVndCd */
    public static final String SQL_STMT_ID_GET_AZERTY_VND_CD = "getAzertyVndCd";

    /** getPoInfo */
    public static final String SQL_STMT_ID_GET_PO_INFO = "getPoInfo";
}
