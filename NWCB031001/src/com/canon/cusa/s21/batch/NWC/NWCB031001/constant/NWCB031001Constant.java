/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB031001.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NWCB031001Constant.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/12   Fujitsu         M.Nakamura      Create
 *</pre>
 */
public class NWCB031001Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NWCB0310";

    /** Batch Name */
    public static final String BATCH_NM = "Invoice EDI Translation for Outsourcer";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Invoice Type : OM */
    public static final String TYPE_OM = "OM";

    /** Invoice Type : Service */
    public static final String TYPE_SERVICE = "SERVICE";

    /** Limit Date : 9999/12/31 */
    public static final String LIMIT_DATE = "99991231";

    /** Separate String '/' */
    public static final String SEP_STR = "|";

    /** NEAI0970_01 : RECORD_TYPE (H01) */
    public static final String NEAI0970_01_REC_TP = "H01";

    /** NEAI0970_01 : DEBIT_CREDIT_CODE (DEBIT = DR) */
    public static final String DEBIT_CD = "DR";

    /** NEAI0970_01 : DEBIT_CREDIT_CODE (CREDIT = CR) */
    public static final String CREDIT_CD = "CR";

    /** NEAI0970_01 : GROSS_WEIGHT (0000000000000000000100) */
    public static final String STR_WT_GRS_22_100 = "0000000000000000000100";

    /** NEAI0970_02 : RECORD_TYPE (D01) */
    public static final String NEAI0970_02_REC_TP = "D01";

    /** NEAI0970_02 : DISBURSEMENT_CODE (BASE CHARGE) */
    public static final String DISBURSEMENT_CODE = "BASE CHARGE";

    /** NEAI0970_02 : INV_PRINT_SVC_PGM_SRC_NM : RENTAL */
    public static final String SRC_NM_RENTAL = "RENTAL";

    /** NEAI0970_02 : merchandise : (026ZZ765) SHIPPING */
    public static final String MDSE_SHIPPING = "026ZZ765";

    /** NEAI0970_02 : ITEM_TYPE : S-TONER */
    public static final String ITEM_TYPE_S_TONER = "S-TONER";

    /** NEAI0970_02 : ITEM_TYPE : M-SHIPPING */
    public static final String ITEM_TYPE_M_SHIPPING = "M-SHIPPING";

    /** NEAI0970_02 : ITEM_TYPE : U- */
    public static final String ITEM_TYPE_U = "U-";

    /** NEAI0970_02 : ITEM_TYPE : BW */
    public static final String ITEM_TYPE_BW = "BW";

    /** NEAI0970_02 : ITEM_TYPE : Color */
    public static final String ITEM_TYPE_COLOR = "Color";

    /** NEAI0970_02 : ITEM_TYPE : M-LABOR */
    public static final String ITEM_TYPE_M_LABOR = "M-LABOR";

    /** NEAI0970_02 : ITEM_TYPE : C-PERI */
    public static final String ITEM_TYPE_C_PERI = "C-PERI";

    /** NEAI0970_02 : ITEM_TYPE : C-RENTAL */
    public static final String ITEM_TYPE_C_RENTAL = "C-RENTAL";

    /** NEAI0970_02 : ITEM_TYPE : E-BASEUNIT */
    public static final String ITEM_TYPE_E_BASEUNIT = "E-BASEUNIT";

    /** NEAI0970_02 : USAGE PER : BASE CHARGE */
    public static final String INV_PER = "BASE CHARGE";

    /** NEAI0970_02 : USAGE PER : USAGE CHARGE */
    public static final String USG_PER = "USAGE CHARGE";

    /** NEAI0970_02 : SERVICE_REFERENCE_TYPE : 00000000000000000000000000000000001 */
    public static final String STR_SVC_REF_TP_TXT = "00000000000000000000000000000000001";

    /** NEAI0970_01/02 : Integer Format 5 */
    public static final String STR_INT_FORMAT_5 = "00000";

    /** NEAI0970_01/02 : Integer Format 20 */
    public static final String STR_INT_FORMAT_20 = "00000000000000000000";

    /** NEAI0970_01/02 : Integer Format 20 */
    public static final String INT_FORMAT_20 = "FM" + STR_INT_FORMAT_20;

    /** NEAI0970_01/02 : Integer Format 22 */
    public static final String STR_INT_FORMAT_22 = "0000000000000000000000";

    /** NEAI0970_01/02 : Integer Format 22 */
    public static final String INT_FORMAT_22 = "FM" + STR_INT_FORMAT_22;

    /** NEAI0970_01/02 : Integer Format 25 */
    public static final String STR_INT_FORMAT_25 = "0000000000000000000000000";

    /** NEAI0970_01/02 : Integer Format 34 */
    public static final String INT_FORMAT_34 = "FM0000000000000000000000000000000000";

    /** NEAI0970_01/02 : Integer Format 35 */
    public static final String STR_INT_FORMAT_35 = "00000000000000000000000000000000000";

    /** NEAI0970_01/02 : Integer Format 35 */
    public static final String INT_FORMAT_35 = "FM" + STR_INT_FORMAT_35;

    /** NEAI0970_01/02 : Integer Format 22 */
    public static final String STR_DEC_FORMAT_22 = "0000000000000000000.00";

    /** NEAI0970_01/02 : Decimal Format 22(19.2) */
    public static final String DEC_FORMAT_22 = "FM" + STR_DEC_FORMAT_22;

    /** NEAI0970_01/02 : Decimal Format 34(31.2) */
    public static final String DEC_FORMAT_34 = "FM0000000000000000000000000000000.00";

    /** NEAI0970_01/02 : Decimal Format 35(32.2) */
    public static final String DEC_FORMAT_35 = "FM00000000000000000000000000000000.00";

    /** NEAI0970_01/02 : Percent Format 10(6.3) */
    public static final String PCT_FORMAT_10 = "FM000000.000";

    /** Length : 20 */
    public static final BigDecimal LEN_20 = BigDecimal.valueOf(20);

    /** Length : 22 */
    public static final BigDecimal LEN_22 = BigDecimal.valueOf(22);

    /** Length : 35 */
    public static final BigDecimal LEN_35 = BigDecimal.valueOf(35);

    /** Mail Group From */
    public static final String GROUP_FROM = "FROM0005";

    /** Mail template ID */
    public static final String MAIL_TEMPLATE_ID = "NWCB0310M001";

    /** Mail Info : ID */
    public static final String TITLE = "";

    /** Mail Info : SEPARATOR */
    public static final String SEPARATOR = ", ";

    /** Mail Info : ID */
    public static final String KEYID = "ID";

    /** Mail Info : Message : Invoice No. */
    public static final String NUMBER = "NUMBER";

    /** Mail Info : Message */
    public static final String MESSAGE = "MESSAGE";

    /** Message : 'Invoice Number =' */
    public static final String MSG_INV_NUM = "Invoice Number =";

    /** Line Feed Code */
    public static final String LINE_FEED_CODE = "\r\n";
}
