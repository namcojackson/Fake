package com.canon.cusa.s21.batch.NWC.NWCB037001.constant;

import java.math.BigDecimal;

import business.db.INV_LINETMsg;

/**
 * <pre>
 * Invoice Interface from EMSD Tool (Create Invoice)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/03   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
public class NWCB037001Constant {

    /**
     * Message ID
     */
    public static enum MSG_ID {
        /** Could not get the (@) */
        NWCM0115E
        /** It failed to update @ Table.[@] */
        , NWCM0110E
        /** The data does not exist in [@].[@] */
        , NWCM0142E
        /** @ is not set. */
        , NWCM0155E
        /** It failed to get [@].(@) */
        , NWCM0112E
        /** Failed to update the @. */
        , NWCM0133E
        /** @ is duplicated. */
        , NWCM0140E
    }

    /** Business ID NWCB0370 */
    public static final String BUSINESS_ID = "NWCB0370";

    /** Invoice BOL Line Number */
    public static final String FIXED_INV_BOL_LINE_NUM = "001";

    /** Invoice Line Sub Number */
    public static final String FIXED_INV_LINE_SUB_NUM = "001";

    /** Invoice Line Sub Trx Number */
    public static final String FIXED_INV_LINE_SUB_TRX_NUM = "001";

    /** status code */
    public static final String STS_CD_0 = "0";

    /** percent 100 */
    public static final BigDecimal PCT_100 = BigDecimal.valueOf(100);

    /** invoice Line Number Digit */
    public static final int DIGIT_INV_LINE_NUM = new INV_LINETMsg().getAttr("invLineNum").getDigit();

    /** positive amount DS_INV_TP varchar const key */
    public static final String VAR_CHAR_CONST_KEY_PST_AMT_DS_INV_TP = "NWCB0370_PST_AMT_DS_INV_TP";

    /** negative amount DS_INV_TP varchar const key */
    public static final String VAR_CHAR_CONST_KEY_NEG_AMT_DS_INV_TP = "NWCB0370_NEG_AMT_DS_INV_TP";

    /** EMSD Billing DS_ORD_TP varchar const key */
    public static final String VAR_CHAR_CONST_KEY_EMSD_BILLING_DS_ORD_TP = "NWCB0370_BLLG_EMSD_DS_ORD_TP";

    /** default transaction type for tax varchar const key */
    public static final String VAR_CHAR_CONST_KEY_DEFAULT_TAX_TRX_TP = "DEFAULT_TAX_TRX_TP";

    /** mandatory check error message text */
    public static final String MND_CHK_ERR_MSG_TXT = "Mandatory Check Error";

    /** tax calc error message text */
    public static final String TAX_CALC_ERR_MSG_TXT = "Tax Calculation Error";

    /** invoice import error message text */
    public static final String INV_IMPT_ERR_MSG_TXT = "Invoice Import Error";

    /** line separator */
    public static final String NEW_LINE = String.format("%n");

    /** <pre>length of invoice key (FM_BAT_ID,BILL_TO_CUST_CD,PMT_TERM_CD,SHIP_TO_CUST_CD,FM_LINE_AMT_SIGN)</pre> */
    public static final int LEN_INVOICE_KEY = 76;

    /** DEF_ERROR_MESSAGE_LEN */
    public static final int DEF_ERROR_MESSAGE_LEN = 140;

    /** mail group id(from) : FROM0005 */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id(to) for Error : NWCB0370 */
    public static final String MAIL_GROUP_ID_TO = "NWCB0370";

    /** mail template id : NWCB0370M001 */
    public static final String MAIL_TEMPLATE_ID_FOR_BIZ_ERROR = "NWCB0370M001";

    /** <pre>batchNm : NWCB0370 Invoice Interface from EMSD Tool (Create Invoice)</pre> */
    public static final String BATCH_NM = "NWCB0370 Invoice Interface from EMSD Tool (Create Invoice)";

    /** mail template key(batchNm) : batchNm */
    public static final String MAIL_TEMPLATE_KEY_BATCH_NM = "batchNm";

    /** mail template key(batchProcLogId) : batchProcLogId */
    public static final String MAIL_TEMPLATE_KEY_BATCH_PROC_LOG_ID = "batchProcLogId";

    /** MAIL_TEMPLATE_KEY_MSG_INFO */
    public static final String MAIL_TEMPLATE_KEY_MSG_INFO = "ErrorInfo";
}
