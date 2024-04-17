/*
 * <Pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB262001.constant;

/**
 * <pre>
 * Constant Class for Receiving ASN WRK from EDI Vendor<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 09/04/2018   CITS            M.Naito         Create          QC#26964,26965
 * 11/12/2018   CITS            M.Naito         Update          QC#29050
 * 09/02/2020   CITS            JR.Mercado      Update          QC#56991
 * 03/13/2023   Hitachi         E.Watabe        Update          QC#61128
 * 09/25/2023   Hitachi         TZ.Win          Update          QC#61608
 * </pre>
 */
public class NPAB262001Constant {

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

    /** SSM set Key Name: SEGMENT_ID */
    public static final String KEY_SEGMENT_ID = "segmentId";

    /** SSM set Key Name: UNIT_ID */
    public static final String KEY_UNIT_ID = "unitId";

    /** SSM set Key Name: KEY_ASN_SO_NUM */
    public static final String KEY_ASN_SO_NUM = "asnSoNum";

    /** SSM set Key Name: KEY_ASN_SO_NUM_HYPHN */
    public static final String KEY_ASN_SO_NUM_HYPHN = "asnSoNumHyphn";

    /** SSM set Key Name: PO_ORD_NUM */
    public static final String KEY_PO_ORD_NUM = "poOrdNum";

    /** SSM set Key Name: PO_ORD_DTL_LINE_NUM */
    public static final String KEY_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    /** SSM set Key Name: KEY_VND_SYS_TP_CD */
    public static final String KEY_VND_SYS_TP_CD = "vndSysTpCd";

    /** SSM set Key Name: KEY_DEST_ST_CD */
    public static final String KEY_DEST_ST_CD = "destStCd";

    /** SSM set Key Name: KEY_VND_CD */
    public static final String KEY_VND_CD = "vndCd";

    /** SSM set Key Name: KEY_ST_CD */
    public static final String KEY_ST_CD = "stCd";

    /** SSM set Key Name: KEY_VND_SHIP_METH_CD */
    public static final String KEY_VND_SHIP_METH_CD = "vndShipMethCd";

    /** SSM set Key Name: KEY_VND_SHIP_METH_CD_FROM_PO */
    public static final String KEY_VND_SHIP_METH_CD_FROM_PO = "vndShipMethCdFromPo";

    /** SSM set Key Name: KEY_AST */
    public static final String KEY_AST = "ast";

    /** SSM set Key Name: KEY_TRD_PTNR_CARR_CD */
    public static final String KEY_TRD_PTNR_CARR_CD = "trdPtnrCarrCd";

    // START 2018/11/12 M.Naito [QC#29050,ADD]
    /** SSM set Key Name: KEY_MDSE_CD */
    public static final String KEY_MDSE_CD = "mdseCd";

    /** SSM set Key Name: KEY_BASE_PKG_UOM_CD */
    public static final String KEY_BASE_PKG_UOM_CD = "basePkgUomCd";

    /** SSM set Key Name: KEY_PKG_UOM_CD */
    public static final String KEY_PKG_UOM_CD = "pkgUomCd";

    /** SSM set Key Name: KEY_VND_UOM_CD */
    public static final String KEY_VND_UOM_CD = "vndUomCd";
    // END 2018/11/12 M.Naito [QC#29050,ADD]

    /** message id : It failed to register [@]. */
    public static final String NPAM1557E = "NPAM1557E";

    /** message id : [@] does not exist. Table:[@], Search Key:[@] */
    public static final String NPAM1537E = "NPAM1537E";

    // START 2020/09/02 [QC#56991,ADD]
    /** message id :  PO#[@] is invalid. Please recovery data at Manual ASN screen.*/
    public static final String NPAM1651E = "NPAM1651E";
    // END 2020/09/02 [QC#56991,ADD]

    /** message id : Failed to get @ */
    public static final String NPAM1328E = "NPAM1328E";

    /** message id [@] Parameter has not been set. */
    public static final String NPGM0009E = "NPGM0009E";

    /** ASN_SO_NUM_BR_NUM */
    public static final String ASN_SO_NUM_BR_NUM = "-[0-9]{2}$";

    /** MAX_COMMIT_NUMBER */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** Default Value(ASN_EDI_PROC_STS_CD) : "S" */
    public static final String ASN_EDI_PROC_STS_CD = "S";

    /** Date Format : yyyyMMddHHmmSS */
    public static final String DATE_FORMAT = "yyyyMMddHHmmSS";

    /** Value : "0" */
    public static final String VAL_ZERO = "0";

    /** Value 01 */
    public static final String VAL_01 = "01";

    /** Value 001 */
    public static final String VAL_001 = "001";

    /** Hyphen */
    public static final String HYPHEN = "-";

    /** Asterisk */
    public static final String ASTERISK = "*";

    /** CARET */
    public static final String CARET = "^";

    /** Comma */
    public static final String COMMA = ",";

    /** length : PO_DTL.PO_ORD_DTL_LINE_NUM */
    public static final int LEN_PO_ORD_DTL_LINE_NUM = 3;

    /** InterfaceID : TST/Impreso */
    public static final String INTFC_ID_TST_IMPRESO = "NPAI0420";

    /** InterfaceID : Dietzgen */
    public static final String INTFC_ID_DIETZGEN = "NPAI0520";
    
    // START 2023/03/13 E.Watabe [QC#61128,ADD]
    /** InterfaceID : Hytec */
    public static final String INTFC_ID_HYTEC = "NPAI0620";
    // END 2023/03/13 E.Watabe [QC#61128,ADD]

    // START 2023/09/25 TZ.Win [QC#61608,ADD]
    /** InterfaceID : ATRIX */
    public static final String INTFC_ID_ATRIX = "NPAI0820";
    // END 2023/09/25 TZ.Win [QC#61608,ADD]

    /** SQL Statement Id */
    /** getPoAsnIntfcHdr */
    public static final String SQL_STMT_ID_GET_PO_ASN_INTFC_HDR = "getPoAsnIntfcHdr";

    /** getPoAsnIntfcDtl */
    public static final String SQL_STMT_ID_GET_PO_ASN_INTFC_DTL = "getPoAsnIntfcDtl";

    /** getPoAsnBolNum */
    public static final String SQL_STMT_ID_GET_PO_ASN_SO_NUM = "getPoAsnSoNum";

    /** getPoAsnLeadTime */
    public static final String SQL_STMT_ID_GET_PO_ASN_LEAD_TIME = "getPoAsnLeadTime";

    /** getAsnVndCd */
    public static final String SQL_STMT_ID_GET_ASN_VND_CD = "getAsnVndCd";

    /** getPoInfo */
    public static final String SQL_STMT_ID_GET_PO_INFO = "getPoInfo";

    /** getCarrCdFromXref */
    public static final String SQL_STMT_ID_GET_CARR_CD_FROM_XREF = "getCarrCdFromXref";

    /** getSoNumFromCmbnXref */
    public static final String SQL_STMT_ID_GET_SO_NUM_FROM_CMBN_XREF = "getSoNumFromCmbnXref";

    // START 2018/11/12 M.Naito [QC#29050,ADD]
    /** getVndUom */
    public static final String SQL_STMT_ID_GET_VND_UOM = "getVndUom";

    /** getMdseStorePkgInfo */
    public static final String SQL_STMT_ID_GET_MDSE_STORE_PKG = "getMdseStorePkg";
    // END 2018/11/12 M.Naito [QC#29050,ADD]

    // START 2020/09/02 [QC#56991,ADD]
    /** getPoOrdNum */
    public static final String SQL_STMT_ID_GET_PO_ORD_NUM = "getPoOrdNum";
    // END 2020/09/02 [QC#56991,ADD]
}
