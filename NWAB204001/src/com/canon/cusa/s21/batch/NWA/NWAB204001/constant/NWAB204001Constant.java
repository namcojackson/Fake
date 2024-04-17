/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB204001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Credit Card Capture Batch.
 * This class defines the constant used in the batch application
 * program of BusinessID NWAB204001. 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/26/2016   Fujitsu         S.Ohki          Create          N/A
 * 06/09/2016   Fujitsu         H.Nagashima     Update          QC#9426
 * 04/01/2021   CITS            K.Ogino         Update          QC#58621
 * 
 *</pre>
 */
public class NWAB204001Constant {

    /**
     * Message ID
     */
    public static enum MSG_ID {
        /** [@] is not found. */
        ZZZM9006E,
        /** [@] is mandatory. */
        ZZZM9025E,
        /** Failed to insert the @. */
        NWAM0790E,
        /** Failed to update the @. */
        NWAM0791E,
        /** It failed to update the [@]. PK [@] */
        NWZM1024E,
        /** Failed billing process with the credit card. Pkey [@] */
        NWAM0518E,
        /** @ ended abnormally. @ */
        NWAM0447E,
        /** The mail template cannot be acquired. <Template ID: [@]> */
        NWAM0319E,
        /** It failed to register Mail. */
        NWAM0268E,
        /**
         * Could not retrieve MailGroupAddress. MAIL_GROUP_ID_TO : [@]
         * MAIL_KEY1 : [@]
         */
        NWAM0516E
    }

    /** Program ID */
    public static final String PROGRAM_ID = "NWAB204001";

    /** Program Name */
    public static final String PROGRAM_NM = "Credit Card Capture Batch";

    /** STR_CRLF */
    public static final String CRLF = "\r\n";

    /** Invoice number max length */
    public static final int INV_NUM_LEN = 13;

    /** Customer reference# max length */
    public static final int CUST_REF_NUM_LEN = 40;

    /** Transaction reference# max length */
    public static final int TRX_REF_NUM_LEN = 50;

    /** INV_DT_LEN */
    public static final int INV_DT_LEN = 10;

    /** INV_TOT_FUNC_NET_AMT_LEN */
    public static final int INV_TOT_FUNC_NET_AMT_LEN = 20;

    /** INV_TOT_FUNC_TAX_AMT_LEN */
    public static final int INV_TOT_FUNC_TAX_AMT_LEN = 20;

    /** INV_TOT_DEAL_NET_AMT_LEN */
    public static final int INV_TOT_DEAL_NET_AMT_LEN = 20;

    /** LGCY_CONTR_NUM_LEN */
    public static final int LGCY_CONTR_NUM_LEN = 7;

    /** Get Credit Card Transaction Row Number */
    public static final int GET_CR_CARD_TRX_ROW_NUM = 1;

    /** email content header label */
    public static final String CONTENT_HDR_LBL = "Invoice#         Invoice Date  Customer Reference#                         Transaction Reference#                                     Deal Net Amount     Function Tax Amount    Functionl Net Amount";

    /** email content header div line */
    public static final String CONTENT_HDR_DIV_LINE = "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";

    /** one white space */
    public static final String ONE_SPACE = " ";

    /** 4 white space */
    public static final String SEPT_SPACE = "    ";

    /** Mail Group Id FROM0001 */
    public static final String MAIL_GROUP_ID_FROM = "FROM0001";

    /** Mail Key Id Z */
    public static final String MAIL_KEY_FROM = "Z";

    /** Mail Key Id To */
    public static final String MAIL_KEY_TO = "To";

    /** Mail Group Id Key: To */
    public static final String MAIL_GROUP_ID_TO = "NWAB2040";

    /** Mail Template ID M001 */
    public static final String MAIL_TEMPLATE_ID_M001 = "NWAB2040M001";

    /** Mail Template ID M002 */
    public static final String MAIL_TEMPLATE_ID_M002 = "NWAB2040M002";

    /** template KEY Sales Date */
    public static final String MAIL_TEMPLATE_KEY_SLS_DT = "slsDt";

    /** template KEY CONTENT */
    public static final String MAIL_TEMPLATE_KEY_CONTENT = "content";

    /** Mail Date Format MM/dd/yyyy */
    public static final String MAIL_OUT_TYPE_MM_DD_YYYY = "MM/dd/yyyy";

    /** Pmtc Process Status (Success) */
    public static final String PMTC_PROC_STS_SUCCESS = "0";

    /** Pmtc Applove Status (Apploved) */
    public static final String PMTC_APVL_STS_APPLOVED = "1";

    /** Process Result Code (Not Target) */
    public static final String PROC_RES_CD_NOT_TARGET = "0";

    /** Process Result Code (Success) */
    public static final String PROC_RES_CD_SUCCESS = "1";

    /** Process Result Code (Error) */
    public static final String PROC_RES_CD_ERROR = "9";

    /** Error Message Key Name (CPO_ORD_NUM) */
    public static final String ERR_MSG_KEY_NM_CPO_ORD_NUM = "CPO_ORD_NUM";

    /** Error Message Key Name (FSR_NUM) */
    public static final String ERR_MSG_KEY_NM_FSR_NUM = "FSR_NUM";

    /** Error Message Key Name (INV_NUM) */
    public static final String ERR_MSG_KEY_NM_INV_NUM = "INV_NUM";

    /** Pmtc Prfl Order Ovrd Code (NO) */
    public static final String PMTC_PRFL_ORD_OVRD_CD_NO = "NO";

    /** Pmtc Tax Index Number (ON) */
    public static final String PMTC_TAX_INDEX_NUM_ON = "1";

    /** Pmtc Tax Index Number (OFF) */
    public static final String PMTC_TAX_INDEX_NUM_OFF = "2";

    /** Authorization Status Message Comment */
    public static final String AUTH_STS_MSG_CMNT_TXT = "It failed to get the credit card token# or authorized# from CR_CARD_TRX.";
    // Add QC#58621 Start
    /** Discount Indicator */
    public static final String DISCOUNT_INDICATOR = "0";

    /** Tax Type */
    public static final String TAX_TYPE = "2";

    /** Gross Net Indicator */
    public static final String GROSS_NET_INDICATOR = "0";

    /** Discount Amount */
    public static final BigDecimal DISCOUNT_AMOUNT = BigDecimal.ZERO;

    /** Unit Of Measure */
    public static final String UNIT_OF_MEASURE = "EA";
    // Add QC#58621 End
}
