package com.canon.cusa.s21.batch.NWC.NWCB032001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Populate Regeneration Invoice Staging Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         S.Ohki          Create          N/A
 * 2017/03/01   Fujitsu         H.Nagashima     Update          QC#16531
 * 2018/08/09   Fujitsu         R.Nakamura      Update          QC#27442
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 *</pre>
 */
public class NWCB032001Constant {

    /**
     * Message ID
     */
    public static enum MSG_ID {
        /** [@] is mandatory. */
        ZZZM9025E,
        /** @ ended abnormally. */
        NLBM0024E,
        /** Failed to get the Pricing processing result.(@) */
        NWCM0113E,
        /** Invoice Import operation failed.(@) */
        NWCM0114E,
        /** The corresponding data does not exist in "GLBL_CMPY". */
        NWZM0257E,
        /** The corresponding data does not exist in  "CCY ". */
        NWZM0368E,
        /** Could not get the (@) */
        NWCM0115E,
        // Add Start 2018/08/10 QC#27442
        /** Sales Rep is not assigned to the specified Ship-To customer. */
        NWAM0757W,
        /** Ship To Customer Code" is not set. */
        NWZM0507E,
        /** Bill To Customer Code" is not set. */
        NWZM0510E,
        // Add End 2018/08/10 QC#27442
        // 2020/04/27 QC#56638 Add
        /** Sales Rep is not assigned to the specified Sold-To customer. */
        NWAM0981W
    }

    /** Business ID NWCB0320 */
    public static final String BUSINESS_ID = "NWCB0320";

    /** Program Name */
    public static final String PROGRAM_NM = "EasyPAC1 Shorfall Debit Creation Batch";

    /** Invoice Key Number 001 */
    public static final String INVOICE_KEY_NUM_001 = "001";

    /** Invoice Key Line Number 00001 */
    public static final String INVOICE_KEY_LINE_NUM_001 = "00001";

    /** Invoice Key Line Number 00001 */
    public static final String INVOICE_KEY_LINE_SUB_NUM_001 = "001";

    /** Transaction Line Key Number */
    public static final String TRX_LINE_KEY_NUM = "1";

    /** Percent */
    public static final String PERCENT = "%";

    /** Hyphen */
    public static final String HYPHEN = "-";

    /** Space */
    public static final String SPACE = " ";

    /** Customer Iss Po Number Header EZP */
    public static final String CUST_ISS_PO_NUM_HEADER = "EZP";

    /** System Source Code(NW) */
    public static final String SYS_SRC_CD_NW = "NW";

    /** VAR_CHAR_CONST Key CTAC_PSN_TP_CD_INV_PRINT_SHORT_FALL_ITEM */
    public static final String VAR_CHAR_CONST_NM_INV_PRINT_SHORT_FALL_ITEM = "INV_PRINT_SHORT_FALL_ITEM";

    /** Invoice Print Status Code(Untreated) */
    public static final String INV_PRINT_STS_CD_UNTREATED = "1";

    /** Order Category Context Type Code(EASY_PACK1) */
    public static final String ORD_CATG_CTX_TP_CD_EASY_PACK1 = "EASY_PACK1";

    /** Order Category Context Type Code(EASY_PACK1_RETURN) */
    public static final String ORD_CATG_CTX_TP_CD_EASY_PACK1_RETURN = "EASY_PACK1_RETURN";

    /** Invoice Line Sub Number(Set Parent) */
    public static final String INV_LINE_SUB_NUM_SET_PARENT = "000";

    /** Error Message Bill To Customer Account Code */
    public static final String ERR_MSG_BILL_TO_CUST_ACCT_CD = "Bill To Customer Account Code:";

    /** Mail Error Message(Pricing Process) */
    public static final String MAIL_ERR_MSG_PRICING_PROCESS = "Failed to get the Pricing processing result.";

    /** Mail Error Message(Invoice Import) */
    public static final String MAIL_ERR_MSG_INVOICE_IMPORT = "Invoice Import operation failed.";

    /** Mail Error Message(Tax Calculation Process) */
    public static final String MAIL_ERR_MSG_TAX_CALCULATION_PROCESS = "Failed to calculation tax processing result.";

    /** Date Format (yyyyMMdd) */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /** Date Format (MM/dd/yyyy) */
    public static final String DATE_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";

    /** String Format (%05s) */
    public static final String STRING_FORMAT_ZERO_PADDING = "%05d";

    /** Easy PAC1 Shortfall Debit Bill Only (A6) */
    public static final String EASYPAC1_SHORTFALL_DEBIT_BILL_ONRY = "A6";

    /** BigDecimal Percent 100 */
    public static final BigDecimal BIGDECIMAL_PERCENT_100 = new BigDecimal(100);

    /** default transaction type for tax varchar const key */
    public static final String VAR_CHAR_CONST_KEY_DEFAULT_TAX_TRX_TP = "DEFAULT_TAX_TRX_TP";    //QC#16531 add

    // Add Start 2018/08/09 QC#27442
    /** Invoice Print Status Code(Untreated) */
    public static final int SORT_NUM_1 = 1;

    /** Invoice Print Status Code(Untreated) */
    public static final int SORT_NUM_2 = 2;

    // Add End 2018/08/09 QC#27442

}
