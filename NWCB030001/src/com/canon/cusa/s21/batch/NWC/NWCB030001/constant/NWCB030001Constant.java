/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB030001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Invoice EDI Extract for SAP Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/04   Hitachi         K.Kojima        Create          N/A
 * 2018/01/26   Fujitsu         A.Kosai         Update          QC#22718
 * 2018/04/23   Fujitsu         S.Ohki          Update          QC#25772
 * 2018/04/26   Fujitsu         S.Ohki          Update          QC#25816
 * 2018/05/08   Fujitsu         S.Ohki          Update          QC#25986
 * 2018/08/27   Fujitsu         K.Ishizuka      Update          QC#25001
 * 2018/08/27   Fujitsu         K.Ishizuka      Update          QC#28548
 * 2018/09/18   Fujitsu         S.Takami        Update          QC#28159
 * 2018/10/03   Fujitsu         S.Takami        Update          QC#27032
 * 2019/01/10   Fujitsu         S.Ohki          Update          QC#29534
 * 2019/01/16   Fujitsu         T.Noguchi       Update          QC#29535
 * 2019/01/17   Fujitsu         T.Murai         Update          QC#29533
 * 2019/02/05   Fujitsu         T.Murai         Update          QC#30175
 * 2019/05/10   Fujitsu         T.Murai         Update          QC#50326
 * </pre>
 */
public class NWCB030001Constant {

    /** BUSINESS_ID */
    public static final String BUSINESS_ID = "NWCB0300";

    /** Batch Name */
    public static final String BATCH_NAME = "Invoice EDI Extract for SAP Batch";

    /** PROGRAM_ID */
    public static final String PROGRAM_ID = BUSINESS_ID + "01";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Limit Date : 9999/12/31 */
    public static final String LIMIT_DATE = "99991231";

    /** XTRNL_INTFC_XREF : INTFC_ID */
    public static final String XIX_INTFC_ID = "NEAI0940";

    /** Message ID : NWCM0059E */
    public static final String NWCM0059E = "NWCM0059E";

    /** Message ID : NWCM0131E */
    public static final String NWCM0131E = "NWCM0131E";

    /** Message ID : NWCM0132E */
    public static final String NWCM0132E = "NWCM0132E";

    /** Message ID : NWCM0133E */
    public static final String NWCM0133E = "NWCM0133E";

    /** Table Name : NEAI0940_01 */
    public static final String NEAI0940_01 = "NEAI0940_01";

    /** Table Name : NEAI0940_02 */
    public static final String NEAI0940_02 = "NEAI0940_02";

    /** Table Name : NEAI0940_04 */
    public static final String NEAI0940_04 = "NEAI0940_04";

    /** Table Name : NEAI0940_05 */
    public static final String NEAI0940_05 = "NEAI0940_05";

    /** Table Name : NEAI0940_06 */
    public static final String NEAI0940_06 = "NEAI0940_06";

    /** Table Name : NEAI0940_10 */
    public static final String NEAI0940_10 = "NEAI0940_10";

    /** Table Name : NEAI0940_12 */
    public static final String NEAI0940_12 = "NEAI0940_12";

    /** Table Name : NEAI0940_13 */
    public static final String NEAI0940_13 = "NEAI0940_13";

    /** Table Name : NEAI0940_14 */
    public static final String NEAI0940_14 = "NEAI0940_14";

    /** Table Name : NEAI0940_15 */
    public static final String NEAI0940_15 = "NEAI0940_15";

    /** Table Name : NEAI0940_17 */
    public static final String NEAI0940_17 = "NEAI0940_17";

    /** Table Name : NEAI0940_19 */
    public static final String NEAI0940_19 = "NEAI0940_19";

    /** Table Name : NEAI0940_20 */
    public static final String NEAI0940_20 = "NEAI0940_20";

    /** Table Name : NEAI0940_21 */
    public static final String NEAI0940_21 = "NEAI0940_21";

    /** Table Name : NEAI0940_23 */
    public static final String NEAI0940_23 = "NEAI0940_23";

    /** Table Name : NEAI0940_24 */
    public static final String NEAI0940_24 = "NEAI0940_24";

    /** Table Name : INV_PRT_CTRL */
    public static final String INV_PRT_CTRL = "INV_PRT_CTRL";

    /** Mail line feed code */
    public static final String LINE_FEED_CODE = "\r\n";

    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_ID = "NWCB0300M001";

    /** Invoice Type : OM */
    public static final String TYPE_OM = "OM";

    /** Invoice Type : Service */
    public static final String TYPE_SERVICE = "SERVICE";

    /** Segment ID : 1 */
    public static final BigDecimal SEGMENT_ID_LEVEL_1 = BigDecimal.ONE;

    /** DS_EDI_TRD_PTNR_CD : MG */
    public static final String DS_EDI_TRD_PTNR_CD_MG = "MG";

    /** DS_EDI_TRD_PTNR_CD : NCR */
    public static final String DS_EDI_TRD_PTNR_CD_NCR = "NCR";

    /** DS_EDI_TRD_PTNR_CD : JPMC */
    public static final String DS_EDI_TRD_PTNR_CD_JPMC = "JPMC";

    /** DS_EDI_TRD_PTNR_CD : FEDEX */
    public static final String DS_EDI_TRD_PTNR_CD_FEDEX = "FEDEX";

    /** DS_EDI_TRD_PTNR_CD : NOV */
    public static final String DS_EDI_TRD_PTNR_CD_NOV = "NOV";

    /** IDOC Segment Name : NEAI0940_01 */
    public static final String IDOC_SEG_NM_01 = "E2EDK01005";

    /** IDOC Segment Name : NEAI0940_02 */
    public static final String IDOC_SEG_NM_02 = "ZOMSIH000";

    /** IDOC Segment Name : NEAI0940_04 */
    public static final String IDOC_SEG_NM_04 = "E2EDKA1003";

    /** IDOC Segment Name : NEAI0940_05 */
    public static final String IDOC_SEG_NM_05 = "E2EDK02";

    /** IDOC Segment Name : NEAI0940_06 */
    public static final String IDOC_SEG_NM_06 = "E2EDK03";

    /** IDOC Segment Name : NEAI0940_10 */
    public static final String IDOC_SEG_NM_10 = "E2EDK18";

    /** IDOC Segment Name : NEAI0940_12 */
    public static final String IDOC_SEG_NM_12 = "E2EDK14";

    /** IDOC Segment Name : NEAI0940_13 */
    public static final String IDOC_SEG_NM_13 = "E2EDP01006";

    /** IDOC Segment Name : NEAI0940_14 */
    public static final String IDOC_SEG_NM_14 = "ZOMSID000";

    /** IDOC Segment Name : NEAI0940_15 */
    public static final String IDOC_SEG_NM_15 = "ZOMSIM000";

    /** IDOC Segment Name : NEAI0940_17 */
    public static final String IDOC_SEG_NM_17 = "E2EDP02001";

    /** IDOC Segment Name : NEAI0940_19 */
    public static final String IDOC_SEG_NM_19 = "E2EDP19001";

    /** IDOC Segment Name : NEAI0940_20 */
    public static final String IDOC_SEG_NM_20 = "E2EDP26";

    /** IDOC Segment Name : NEAI0940_21 */
    public static final String IDOC_SEG_NM_21 = "E2EDPA1003";

    /** IDOC Segment Name : NEAI0940_23 */
    public static final String IDOC_SEG_NM_23 = "E2EDS01";

    /** IDOC Segment Name : NEAI0940_24 */
    public static final String IDOC_SEG_NM_24 = "E2EDP05002";

    /** NEAI0940_01 : IDOC_DOC_TP_CD(INVO) */
    public static final String IDOC_DOC_TP_CD_INVO = "INVO";

    /** NEAI0940_01 : IDOC_DOC_TP_CD(CRME) */
    public static final String IDOC_DOC_TP_CD_CRME = "CRME";

    /** NEAI0940_02 : IDOC_PRINT_LINE_DTL_NUM(Component) */
    public static final String IDOC_PRINT_LINE_DTL_NUM_COMPONENT = "4";

    /** NEAI0940_02 : IDOC_PRINT_DOL_DTL_NUM(Component) */
    public static final String IDOC_PRINT_DOL_DTL_NUM_COMPONENT = "4";

    /** NEAI0940_04 : IDOC_PTNR_FUNC_NUM(RE) */
    public static final String IDOC_PTNR_FUNC_NUM_RE = "RE";

    /** NEAI0940_05 : IDOC_QLFY_REF_DOC_CD(Invoice Number) */
    public static final String IDOC_QLFY_REF_DOC_CD_INV = "009";

    /** NEAI0940_05 : IDOC_QLFY_REF_DOC_CD(Customer PO Number) */
    public static final String IDOC_QLFY_REF_DOC_CD_CPO = "001";

    /** NEAI0940_06 : IDOC_QLFY_DT_SEG_CD(Billing Date) */
    public static final String IDOC_QLFY_DT_SEG_CD_BILL = "026";

    /** NEAI0940_06 : IDOC_QLFY_DT_SEG_CD(Document Date) */
    public static final String IDOC_QLFY_DT_SEG_CD_DOC = "012";

    /** NEAI0940_06 : IDOC_QLFY_DT_SEG_CD(PO Order Date(Invoice Date)) */
    public static final String IDOC_QLFY_DT_SEG_CD_ORD = "022";

    /** NEAI0940_10 : IDOC_QLFY_PMT_TERM_CD */
    public static final String IDOC_QLFY_PMT_TERM_CD = "001";

    /** NEAI0940_12 : IDOC_QLFY_ORG_CD */
    public static final String IDOC_QLFY_ORG_CD = "015";

    /** NEAI0940_12 : IDOC_LINE_REC_TP_CD(Pooling) */
    public static final String IDOC_LINE_REC_TP_CD_POOLING = "P";

    /** NEAI0940_12 : IDOC_LINE_REC_TP_CD(Rental) */
    public static final String IDOC_LINE_REC_TP_CD_RENTAL = "R";

    /** NEAI0940_12 : IDOC_LINE_REC_TP_CD(Lease) */
    public static final String IDOC_LINE_REC_TP_CD_LEASE = "L";

    // Add Start QC#50326 2019/05/10
    /** NEAI0940_12 : IDOC_LINE_REC_TP_CD(Lease) */
    public static final String IDOC_LINE_REC_TP_CD_SERVICE = "S";
    // Add End QC#50326 2019/05/10

    /** NEAI0940_12 : IDOC_LINE_REC_TP_CD(Excess Usage) */
    public static final String IDOC_LINE_REC_TP_CD_EXCESS_USAGE = "U";

    /** NEAI0940_17 : IDOC_QLFY_REF_DOC_CD(Ship to party7s PO Order) */
    // 2018/04/23 QC#25772 Mod Start
//    public static final String IDOC_QLFY_REF_DOC_CD = "044";
    public static final String IDOC_QLFY_REF_DOC_CD_SHIP_TO_PO = "044";
    // 2018/04/23 QC#25772 Mod End

    // 2018/04/23 QC#25772 Add Start
    /** NEAI0940_17 : IDOC_QLFY_REF_DOC_CD(Customer PO) */
    public static final String IDOC_QLFY_REF_DOC_CD_CUST_PO = "001";

    /** NEAI0940_17 : IDOC_QLFY_REF_DOC_CD(Vendor Order) */
    public static final String IDOC_QLFY_REF_DOC_CD_VND_ORD = "002";
    // 2018/04/23 QC#25772 Add End

    /** NEAI0940_19 : IDOC_OBJ_EDI_ID */
    public static final String IDOC_OBJ_EDI_ID = "002";

    /** NEAI0940_20 : IDOC_QLFY_AMT_EDI_CD(Net Price) */
    public static final String IDOC_QLFY_AMT_EDI_CD_NET_PRICE = "001"; // 2018/10/03 QC#27032 Change Name

    /** NEAI0940_20 : IDOC_QLFY_AMT_EDI_CD(Gross Value) */
    public static final String IDOC_QLFY_AMT_EDI_CD_NET_LINE_VALUE = "002"; // 2018/10/03 QC#27032 Change Name

    /** NEAI0940_20 : IDOC_QLFY_AMT_EDI_CD(Net Value) */
    public static final String IDOC_QLFY_AMT_EDI_CD_NET_VALUE = "003";

    /** NEAI0940_20 : IDOC_QLFY_AMT_EDI_CD(Handling Charge) */
    public static final String IDOC_QLFY_AMT_EDI_CD_HANDLING_CHARGE = "ZSF";

    /** NEAI0940_20 : IDOC_QLFY_AMT_EDI_CD(Total Tax Amount) */
    public static final String IDOC_QLFY_AMT_EDI_CD_LINE_TOTAL_AMOUNT = "ZUT"; // 2018/10/03 QC#27032 Change Name

    // 2018/04/26 QC#25816 Add Start
    /** NEAI0940_20 : IDOC_QLFY_AMT_EDI_CD(Net Unit Price Amount) */
    public static final String IDOC_QLFY_AMT_EDI_CD_NET_LINE_AMT = "ZST"; // 2018/10/03 QC#27032 Change Name
    // 2018/04/26 QC#25816 Add End

    /** NEAI0940_21 : IDOC_PTNR_FUNC_NUM */
    public static final String IDOC_PTNR_FUNC_NUM = "WE";

    /** NEAI0940_23 : IDOC_QLFY_TOT_SEG_CD(Total Sales Tax) */
    public static final String IDOC_QLFY_TOT_SEG_CD_TOTAL_SALES_TAX = "005";

    /** NEAI0940_23 : IDOC_QLFY_TOT_SEG_CD(Handling Charge) */
    public static final String IDOC_QLFY_TOT_SEG_CD_HANDLING_CHARGE = "ZSF";

    /** NEAI0940_23 : IDOC_QLFY_TOT_SEG_CD(Freight) */
    public static final String IDOC_QLFY_TOT_SEG_CD_FREIGHT = "ZFE";

    /** NEAI0940_23 : IDOC_QLFY_TOT_SEG_CD(Invoice Sub Total) */
    public static final String IDOC_QLFY_TOT_SEG_CD_INVOICE_SUB_TOTAL = "ZPN";

    /** NEAI0940_23 : IDOC_QLFY_TOT_SEG_CD(Total Tax Amount) */
    public static final String IDOC_QLFY_TOT_SEG_CD_TOTAL_TAX_AMONT = "ZUT";

    /** NEAI0940_23 : IDOC_QLFY_TOT_SEG_CD(Invoice Total) */
    public static final String IDOC_QLFY_TOT_SEG_CD_INVOICE_TOTAL = "011";

    /** NEAI0940_24 : IDOC_SRCRG_DISC_CD(+) */
    public static final String IDOC_SRCRG_DISC_CD_POSITIVE = "+";

    /** NEAI0940_24 : IDOC_COND_TP_CD(CSTP) */
    public static final String IDOC_COND_TP_CD_CSTP = "CSTP";

    /** NEAI0940_24 : IDOC_COND_TP_NM(Cost Based) */
    public static final String IDOC_COND_TP_NM_COST_BASED = "Cost Based";

    /** Column name : ADDRESS */
    public static final String ADDRESS = "ADDRESS";

    /** Column name : BILL_TO_CUST_ACCT_CD */
    public static final String BILL_TO_CUST_ACCT_CD = "BILL_TO_CUST_ACCT_CD";

    /** Column name : BILL_TO_CUST_ACCT_NM */
    public static final String BILL_TO_CUST_ACCT_NM = "BILL_TO_CUST_ACCT_NM";

    /** Column name : CNTY_NM */
    public static final String CNTY_NM = "CNTY_NM";

    // 2019/02/05 QC#30175 Mod Start
    /** Column name : BLLG_PER_FROM_DT */
    public static final String BLLG_PER_FROM_DT = "BLLG_PER_FROM_DT";

    /** Column name : BLLG_PER_THRU_DT */
    public static final String BLLG_PER_THRU_DT = "BLLG_PER_THRU_DT";
    // 2019/02/05 QC#30175 Mod End

    /** Column name : CTRY_CD */
    public static final String CTRY_CD = "CTRY_CD";

    /** Column name : CTY_ADDR */
    public static final String CTY_ADDR = "CTY_ADDR";

    /** Column name : CUST_ISS_PO_DT */
    public static final String CUST_ISS_PO_DT = "CUST_ISS_PO_DT";

    /** Column name : CUST_ISS_PO_NUM */
    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    // 2019/01/17 QC#29553 Add Start
    /** Column name : CUST_ISS_PO_NUM */
    public static final String CUST_ISS_PO_NUM_L = "CUST_ISS_PO_NUM_L";
    // 2019/01/17 QC#29553 Add End

    // 2018/04/23 QC#25772 Add Start
    /** Column name : CPO_ORD_NUM */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";
    // 2018/04/23 QC#25772 Add End

    /** Column name : CUST_MDSE_CD */
    public static final String CUST_MDSE_CD = "CUST_MDSE_CD";

    /** Column name : CUST_MDSE_NM */
    public static final String CUST_MDSE_NM = "CUST_MDSE_NM";

    /** Column name : DEAL_GRS_TOT_PRC_AMT */
    public static final String DEAL_GRS_TOT_PRC_AMT = "DEAL_GRS_TOT_PRC_AMT";

    /** Column name : DEAL_GRS_UNIT_PRC_AMT */
    public static final String DEAL_GRS_UNIT_PRC_AMT = "DEAL_GRS_UNIT_PRC_AMT";

    /** Column name : DEAL_NET_UNIT_PRC_AMT */
    public static final String DEAL_NET_UNIT_PRC_AMT = "DEAL_NET_UNIT_PRC_AMT";

    // 2018/04/26 QC#25816 Add Start
    /** Column name : FUNC_NET_UNIT_PRC_AMT */
    public static final String FUNC_NET_UNIT_PRC_AMT = "FUNC_NET_UNIT_PRC_AMT";
    // 2018/04/26 QC#25816 Add End

    /** Column name : DS_ACCT_NM */
    public static final String DS_ACCT_NM = "DS_ACCT_NM";

    /** Column name : DS_CONTR_CATG_CD */
    public static final String DS_CONTR_CATG_CD = "DS_CONTR_CATG_CD";

    /** Column name : DS_CTAC_PNT_VAL_TXT_1 */
    public static final String DS_CTAC_PNT_VAL_TXT_1 = "DS_CTAC_PNT_VAL_TXT_1";

    /** Column name : DS_CTAC_PNT_VAL_TXT_2 */
    public static final String DS_CTAC_PNT_VAL_TXT_2 = "DS_CTAC_PNT_VAL_TXT_2";

    /** Column name : DS_EDI_TRD_PTNR_CD */
    public static final String DS_EDI_TRD_PTNR_CD = "DS_EDI_TRD_PTNR_CD";

    /** Column name : DS_IMPT_ATTRB_TXT_02 */
    public static final String DS_IMPT_ATTRB_TXT_02 = "DS_IMPT_ATTRB_TXT_02";

    /** Column name : DS_INV_TP_CD */
    public static final String DS_INV_TP_CD = "DS_INV_TP_CD";

    /** Column name : DS_ORD_CATG_CD */
    public static final String DS_ORD_CATG_CD = "DS_ORD_CATG_CD";

    /** Column name : DS_ORD_RSN_CD */
    public static final String DS_ORD_RSN_CD = "DS_ORD_RSN_CD";

    /** Column name : DS_ORD_TP_CD */
    public static final String DS_ORD_TP_CD = "DS_ORD_TP_CD";

    /** Column name : FIRST_LINE_ADDR */
    public static final String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /** Column name : FRT_DEAL_TAX_AMT */
    public static final String FRT_DEAL_TAX_AMT = "FRT_DEAL_TAX_AMT";

    /** Column name : INV_BOL_LINE_NUM */
    public static final String INV_BOL_LINE_NUM = "INV_BOL_LINE_NUM";

    /** Column name : INV_DT */
    public static final String INV_DT = "INV_DT";

    /** Column name : INV_INTFC_ID */
    public static final String INV_INTFC_ID = "INV_INTFC_ID";

    /** Column name : INV_LINE_DEAL_NET_AMT */
    public static final String INV_LINE_DEAL_NET_AMT = "INV_LINE_DEAL_NET_AMT";

    /** Column name : INV_LINE_DEAL_TAX_AMT */
    public static final String INV_LINE_DEAL_TAX_AMT = "INV_LINE_DEAL_TAX_AMT";

    /** Column name : INV_LINE_MDSE_CD */
    public static final String INV_LINE_MDSE_CD = "INV_LINE_MDSE_CD";

    /** Column name : INV_LINE_NUM */
    public static final String INV_LINE_NUM = "INV_LINE_NUM";

    /** Column name : INV_LINE_SUB_NUM */
    public static final String INV_LINE_SUB_NUM = "INV_LINE_SUB_NUM";

    /** Column name : INV_LINE_SUB_TRX_NUM */
    public static final String INV_LINE_SUB_TRX_NUM = "INV_LINE_SUB_TRX_NUM";

    /** Column name : INV_NUM */
    public static final String INV_NUM = "INV_NUM";

    /** Column name : INV_PRINT_SVC_PGM_SRC_NM */
    public static final String INV_PRINT_SVC_PGM_SRC_NM = "INV_PRINT_SVC_PGM_SRC_NM";

    /** Column name : INV_PRT_CTRL_PK */
    public static final String INV_PRT_CTRL_PK = "INV_PRT_CTRL_PK";

    /** Column name : INV_TOT_DEAL_DISC_AMT */
    public static final String INV_TOT_DEAL_DISC_AMT = "INV_TOT_DEAL_DISC_AMT";

    /** Column name : INV_TOT_DEAL_NET_AMT */
    public static final String INV_TOT_DEAL_NET_AMT = "INV_TOT_DEAL_NET_AMT";

    /** Column name : INV_TOT_DEAL_SLS_AMT */
    public static final String INV_TOT_DEAL_SLS_AMT = "INV_TOT_DEAL_SLS_AMT";

    /** Column name : INV_TOT_DEAL_TAX_AMT */
    public static final String INV_TOT_DEAL_TAX_AMT = "INV_TOT_DEAL_TAX_AMT";

    /** Column name : INV_TP_CD */
    public static final String INV_TP_CD = "INV_TP_CD";

    /** Column name : INV_TYPE */
    public static final String INV_TYPE = "INV_TYPE";

    /** Column name : MDL_DESC_TXT */
    public static final String MDL_DESC_TXT = "MDL_DESC_TXT";

    /** Column name : MDL_GRP_NM */
    public static final String MDL_GRP_NM = "MDL_GRP_NM";

    /** Column name : MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** Column name : MDSE_NM */
    public static final String MDSE_NM = "MDSE_NM";

    /** Column name : NET_DUE_DT */
    public static final String NET_DUE_DT = "NET_DUE_DT";

    /** Column name : ORD_DT */
    public static final String ORD_DT = "ORD_DT";

    /** Column name : PMT_TERM_AOT */
    public static final String PMT_TERM_AOT = "PMT_TERM_AOT";

    /** Column name : PMT_TERM_CD */
    public static final String PMT_TERM_CD = "PMT_TERM_CD";

    /** Column name : PMT_TERM_CD_TXT */
    public static final String PMT_TERM_CD_TXT = "PMT_TERM_CD_TXT";

    /** Column name : PMT_TERM_TXT */
    public static final String PMT_TERM_TXT = "PMT_TERM_TXT";

    /** Column name : POST_CD */
    public static final String POST_CD = "POST_CD";

    /** Column name : PREV_TOT_COPY_QTY */
    public static final String PREV_TOT_COPY_QTY = "PREV_TOT_COPY_QTY";

    /** Column name : RCPNT_CTRY_CD */
    public static final String RCPNT_CTRY_CD = "RCPNT_CTRY_CD";

    /** Column name : RCPNT_CTY_ADDR */
    public static final String RCPNT_CTY_ADDR = "RCPNT_CTY_ADDR";

    /** Column name : RCPNT_POST_CD */
    public static final String RCPNT_POST_CD = "RCPNT_POST_CD";

    /** Column name : RCPNT_ST_CD */
    public static final String RCPNT_ST_CD = "RCPNT_ST_CD";

    /** Column name : SELL_TO_CUST_CD */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** Column name : SER_NUM */
    public static final String SER_NUM = "SER_NUM";

    /** Column name : SVC_INV_CHRG_TP_CD */
    public static final String SVC_INV_CHRG_TP_CD = "SVC_INV_CHRG_TP_CD";

    /** Column name : SHIP_DEAL_FRT_AMT */
    public static final String SHIP_DEAL_FRT_AMT = "SHIP_DEAL_FRT_AMT";

    /** Column name : SHIP_DEAL_HDLG_CHRG_AMT */
    public static final String SHIP_DEAL_HDLG_CHRG_AMT = "SHIP_DEAL_HDLG_CHRG_AMT";

    /** Column name : SHIP_QTY */
    public static final String SHIP_QTY = "SHIP_QTY";

    /** Column name : SHIP_TO_CUST_CD */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** Column name : ST_CD */
    public static final String ST_CD = "ST_CD";

    /** Column name : SVC_INV_SRC_TP_CD */
    public static final String SVC_INV_SRC_TP_CD = "SVC_INV_SRC_TP_CD";

    /** Column name : TOTAL_IN_POUND_WEIGHT */
    public static final String TOTAL_IN_POUND_WEIGHT = "TOTAL_IN_POUND_WEIGHT";

    /** Column name : TOT_COPY_QTY */
    public static final String TOT_COPY_QTY = "TOT_COPY_QTY";

    /** Column name : UOM_CD */
    public static final String UOM_CD = "UOM_CD";

    // 2018/01/26 QC#22718 Add Start
    /** Column name : SAP_EDI_TRD_PTNR_CD */
    public static final String SAP_EDI_TRD_PTNR_CD = "SAP_EDI_TRD_PTNR_CD";

    /** Column name : DROP_SHIP_FLG */
    public static final String DROP_SHIP_FLG = "DROP_SHIP_FLG";

    /** Column name : SHIP_TO_LOC_NM */
    public static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";
    // 2018/01/26 QC#22718 Add End

    // 2018/05/08 QC#25986 Add Start
    /** Master Graphics Idoc Reference Name (Your reference [Partner]) */
    public static final String IDOC_REF_NM_DEF_MG = "OCEB";
    // 2018/05/08 QC#25986 Add End

    /** Column name : IN_EACH_QTY */ // 2018/08/27 S21_NA#25001 Add 
    public static final String IN_EACH_QTY = "IN_EACH_QTY";
    
    /** variable character const : IDOC_PLANT_CD_FOR_JPMC */ // 2018/10/05 S21_NA#28548 Add 
    public static final String IDOC_PLANT_CD_FOR_JPMC = "IDOC_PLANT_CD_FOR_JPMC";

    // 2018/09/18 QC#28159 Add Start
    /** Column name : SHIP_TO_CUST_ACCT_CD */
    public static final String SHIP_TO_CUST_ACCT_CD = "SHIP_TO_CUST_ACCT_CD";
    // 2018/09/18 QC#28159 Add End

    // 2018/10/03 QC#27032 Add Start
    /** Column name : INV_LINE_CATG_CD */ 
    public static final String INV_LINE_CATG_CD = "INV_LINE_CATG_CD";

    /** Column name : CPO_DTL_LINE_NUM */ 
    public static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** Column name : CPO_DTL_LINE_SUB_NUM */ 
    public static final String CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";

    /** PNTP Price Rule Header PK VAR_CHAR_CONST Key */
    public static final String NWCB0300_PNTP_PRC_RULE_HDR_PK = "NWCB0300_PNTP_PRC_RULE_HDR_PK";

    /** Special Handling Price Rule Header PK VAR_CHAR_CONST Key */
    public static final String NWCB0300_SPCL_HDLG_RULE_HDR_PK = "NWCB0300_SPCL_HDLG_RULE_HDR_PK";
    // 2018/10/03 QC#27032 Add End

    // 2019/01/10 QC#29534 Mod Start
    /** Column name : Prev Read Meter Count */
    public static final String READ_MTR_CNT_PR = "READ_MTR_CNT_PR";

    /** Column name : Original Read Meter Count */
    public static final String READ_MTR_CNT_OR = "READ_MTR_CNT_OR";
    // 2019/01/10 QC#29534 Mod End

    // 2019/01/16 QC#29535 Add Start
    /** Column name : ADD_DROP_SHIP_FLG */ 
    public static final String ADD_DROP_SHIP_FLG   = "ADD_DROP_SHIP_FLG";

    /** Column name : ADD_SHIP_TO_CUST_CD */ 
    public static final String ADD_SHIP_TO_CUST_CD = "ADD_SHIP_TO_CUST_CD";
    // 2019/01/16 QC#29535 Add End
}
