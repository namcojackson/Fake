package business.blap.NFDL0030.constant;

import java.math.BigDecimal;

public interface NFDL0030Constant {

    String MODE_INVOICE = "01";

    String MODE_ON_ACCOUNT = "02";

    /**
     * Receipt Return Code : Normal
     */
    public static final String RECEIPT_RTNCD_NORMAL = "0";

    /**
     * Receipt Return Code : Error
     */
    public static final String RECEIPT_RTNCD_ERROR = "1";

    /**
     * Apply Return Code : UnProcess
     */
    public static final String APPLY_RTNCD_UN_PROCCES = "0";

    /**
     * Apply Return Code : Normal
     */
    public static final String APPLY_RTNCD_NORMAL = "1";

    /**
     * Apply Return Code : Cash Application Error
     */
    public static final String APPLY_RTNCD_CASHAPP_ERROR = "2";

    /**
     * Apply Return Code : Exclusion Error
     */
    public static final String APPLY_RTNCD_EXCLUSION_ERROR = "3";

    /**
     * Apply Return Code : Other Error
     */
    public static final String APPLY_RTNCD_OTHERS_ERROR = "4";

    /**
     * Currency Code : USD
     */
    public static final String CCY_USD = "USD";

    /**
     * Receipt Detail Number : 0001
     */
    public static final String RCPT_DTL_NUM = "0001";

    /**
     * Deal Sequence Number : 
     */
    public static final String DEAL_SQ_NUM = "00000001";

    /**
     * Deal Sequence Detail Number
     */
    public static final String DEAL_SQ_DTL_NUM = "0001";

    /**
     * Receipt Batch Number
     */
    public static final String RCPT_BAT_NUM = "999";

    /**
     * Receipt Batch Sequence Number
     */
    public static final String RCPT_BAT_SQ_NUM = "001";

    /**
     * AR Receipt Type Dummy Code
     */
    public static final String AR_RCPT_TP_DUMMY = "99";

    /**
     * AR Cash Application Sequence Number
     */
    public static final String AR_CASH_APP_SQ_NUM = "00001";

    /**
     * AR Receipt Auto Sequence Key
     */
    public static final String AR_RCPT_AUTO_SEQ_KEY = "RC#";

    // START 2023/03/10 S.Nakatani [QC#55645,ADD]
    /**
     * AR Receipt Batch Count
     */
    public static final String AR_BAT_RCPT_CNT = "1";
    // END 2023/03/10 S.Nakatani [QC#55645,ADD]

    /**
     * VarChar Constant Key : AR_RCPT_TOC_CD 
     */
    public static final String VAR_CHAR_CONST_AR_RCPT_TOC_CD_KEY = "AR_RCPT_TOC_CD";

    /**
     * VarChar Constant Key : AR_RCPT_PROD_CD
     */
    public static final String VAR_CHAR_CONST_AR_RCPT_PROD_CD_KEY = "AR_RCPT_PROD_CD";

    /**
     * VarChar Constant Key : AR_RCPT_PROD_CD
     */
    public static final String VAR_CHAR_CONST_AR_SUB_SYS_ID_KEY = "AR_SUB_SYS_ID";

    /**
     * Create Method Type Code : Manual
     */
    public static final String CREATE_METH_TP_CD_MANUAL = "M";

    // Add Start 2018/18/17 S21_NA#20563
    /**
     * This transaction has pending cash application and this application will make balance minus.
     */
    public static final String NFCM0880E = "NFCM0880E";
    // Add End 2018/18/17 S21_NA#20563

    // START 2018/04/18 [QC#20940, ADD]
    /**
     * VarChar Constant Key : NFDL0030_CLT_DTL_NOTE_TXT
     */
    public static final String VAR_CHAR_CONST_NFDL0030_CLT_DTL_NOTE_TXT = "NFDL0030_CLT_DTL_NOTE_TXT";

    /**
     * MAX_LENGTH_CLT_DTL_NOTE_TXT
     */
    public static final int MAX_LENGTH_CLT_DTL_NOTE_TXT = 4000;
    // END   2018/04/18 [QC#20940, ADD]
    // START 2018/05/15 E.Kameishi [QC#24833, ADD]
    /**
     * ASTERISK
     */
    public static final String ASTERISK = "****";

    /**
     * ReceiptCheckNumberingKey("AR_RCPT_CHK_NUM")
     */
    public static final String AUTO_SQ_EXTN_KEY_AR_RCPT_CHK_NUM = "AR_RCPT_CHK_NUM";

    /** CONST KEY Field **/
    public static final String AR_RCPT_CHK_NUM_HDR = "IEX_";

    // START 2023/03/10 S.Nakatani [QC#55645,ADD]
    /** VarChar Constant Key : AR_RCPT_E_CHK_NUM_HDR */
    public static final String AR_RCPT_E_CHK_NUM_HDR = "EC_";

    /** VarChar Constant Key : AR_BAT_RCPT_E_CHK_NM_HDR */
    public static final String AR_BAT_RCPT_E_CHK_NM_HDR = "ECHECK_";

    /** VarChar Constant Key : AR_RCPT_E_CHK_CMNT_TXT_HDR */
    public static final String AR_RCPT_E_CHK_CMNT_TXT_HDR = "ECHECK PAYMENT";
    // END 2023/03/10 S.Nakatani [QC#55645,ADD]
    // END 2018/05/15 E.Kameishi [QC#24833, ADD]

    // START 2018/05/23 [QC#20940, ADD]
    /** VarChar Constant Key : AR_CLT_DEF_EML_ADDR */
    public static final String AR_CLT_DEF_EML_ADDR = "AR_CLT_DEF_EML_ADDR";
    
    /** VarChar Constant Key : AR_CONF_LTR_OFC_FIRST_ADDR */
    public static final String VAR_CHAR_CONST_AR_CONF_LTR_OFC_FIRST_ADDR = "AR_CONF_LTR_OFC_FIRST_ADDR";

    /** VarChar Constant Key : AR_CONF_LTR_OFC_SCD_ADDR */
    public static final String VAR_CHAR_CONST_AR_CONF_LTR_OFC_SCD_ADDR = "AR_CONF_LTR_OFC_SCD_ADDR";

    /** VarChar Constant Key : AR_CONF_LTR_OFC_THIRD_ADDR */
    public static final String VAR_CHAR_CONST_AR_CONF_LTR_OFC_THIRD_ADDR = "AR_CONF_LTR_OFC_THIRD_ADDR";

    /** VarChar Constant Key : AR_CONF_LTR_OFC_FRTH_ADDR */
    public static final String VAR_CHAR_CONST_AR_CONF_LTR_OFC_FRTH_ADDR = "AR_CONF_LTR_OFC_FRTH_ADDR";

    /** VarChar Constant Key : AR_CONF_LTR_OFC_CITY_ADDR */
    public static final String VAR_CHAR_CONST_AR_CONF_LTR_OFC_CITY_ADDR = "AR_CONF_LTR_OFC_CITY_ADDR";

    /** VarChar Constant Key : AR_CONF_LTR_OFC_PROV_ADDR */
    public static final String VAR_CHAR_CONST_AR_CONF_LTR_OFC_PROV_ADDR = "AR_CONF_LTR_OFC_PROV_ADDR";

    /** VarChar Constant Key : AR_CONF_LTR_OFC_ST_CD */
    public static final String VAR_CHAR_CONST_AR_CONF_LTR_OFC_ST_CD = "AR_CONF_LTR_OFC_ST_CD";

    /** VarChar Constant Key : AR_CONF_LTR_OFC_POST_CD */
    public static final String VAR_CHAR_CONST_AR_CONF_LTR_OFC_POST_CD = "AR_CONF_LTR_OFC_POST_CD";

    /** VarChar Constant Key : AR_CONF_LTR_OFC_TEL_NUM */
    public static final String VAR_CHAR_CONST_AR_CONF_LTR_OFC_TEL_NUM = "AR_CONF_LTR_OFC_TEL_NUM";

    /** VarChar Constant Key : AR_CONF_LTR_CMPY_URL */
    public static final String VAR_CHAR_CONST_AR_CONF_LTR_CMPY_URL = "AR_CONF_LTR_CMPY_URL";

    /** VarChar Constant Key : AR_HDR_PRINT_CMPY_NM */
    public static final String VAR_CHAR_CONST_AR_HDR_PRINT_CMPY_NM = "AR_HDR_PRINT_CMPY_NM";

    /** mail template paramter : billToCustCd */
    public static final String ML_TMPL_PRM_BILL_TO_CUST_CD = "billToCustCd";

    /** mail template paramter : billToCustCd */
    public static final String ML_TMPL_PRM_CARD_TYPE_NAME = "cardTypeName";

    /** mail template paramter : signature */
    public static final String ML_TMPL_PRM_SIGNATURE = "signature";

    /** IN_DATE_FORMAT */
    public static final String IN_DATE_FORMAT = "yyyyMMdd";

    /** OUT_DATE_FORMAT */
    // START 2018/05/29 [QC#20940, ADD]
    public static final String OUT_DATE_FORMAT = "dd MMM,yyyy";
    // END   2018/05/29 [QC#20940, ADD]

    /** SPACE */
    public static final String SPACE = " ";

    /** COMMA */
    public static final String COMMA = ",";

    /** TELEPHONE */
    public static final String TELEPHONE = "T";

    /** FAX */
    public static final String FAX = "F";

    /** MAIL_TMPL_ID **/
    public static final String MAIL_TMPL_ID = "NFDL0030M001";

    /** RPT_ID **/
    public static final String RPT_ID = "NFDF0050";

    /** RPT_TTL_NM **/
    // START 2019/01/09 [QC#28800, MOD]
    //public static final String RPT_TTL_NM = "Confirmation Letter";
    public static final String RPT_TTL_NM = "Credit Card Receipt";
    // END   2019/01/09 [QC#28800, MOD]
    // START 2023/03/10 S.Nakatani [QC#55645,ADD]
    /** E_CHECK_RPT_TTL_NM **/
    public static final String E_CHECK_RPT_TTL_NM = "eCheck Receipt";
    // END 2023/03/10 S.Nakatani [QC#55645,ADD]

    /** RPT_BR_NUM_EML **/
    public static final String RPT_BR_NUM_EML = "02";

    /** RPT_DEST_ID **/
    public static final String RPT_DEST_ID = "EML01";

    /** ATTACH_FILE_NM **/
    // START 2019/01/09 [QC#28800, MOD]
    //public static final String ATTACH_FILE_NM = "Confirmation Letter";
    public static final String ATTACH_FILE_NM = "Credit Card Receipt";
    // END   2019/01/09 [QC#28800, MOD]

    /** CTAC_TO_NM */
    public static final String CTAC_TO_NM = "CTAC_TO_NM";

    /** CTAC_TO_ML_ADDR */
    public static final String CTAC_TO_ML_ADDR = "CTAC_TO_ML_ADDR";

    /**
     * Cannot send @. because @'s email address is not registered.
     */
    public static final String NFDM0048E = "NFDM0048E";

    /** MSG_PRM_CONF_LTR */
    // START 2019/01/09 [QC#28800, MOD]
    //public static final String MSG_PRM_CONF_LTR = "confirmation letter";
    public static final String MSG_PRM_CONF_LTR = "Credit Card Receipt";
    // END   2019/01/09 [QC#28800, MOD]

    /** MSG_PRM_CLTR */
    public static final String MSG_PRM_CLTR = "collector";

    /** MSG_PRM_CTAC_PSN */
    public static final String MSG_PRM_CTAC_PSN = "contact person";

    /** NFBM0184E */
    public static final String NFBM0184E = "NFBM0184E";
    // END   2018/05/23 [QC#20940, ADD]

    // START 2018/07/11 [QC#27002, ADD]
    /** EXTENSION_PDF */
    public static final String EXTENSION_PDF = ".pdf";
    // END   2018/07/11 [QC#27002, ADD]

    // START 2020/05/22 [QC#56866, ADD]
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

    /** Alt Tax Amount */
    public static final BigDecimal ALT_TAX_AMOUNT = BigDecimal.ZERO;

    /** Duty Amount */
    public static final BigDecimal DUTY_AMOUNT = BigDecimal.ZERO;

    /** VarChar Constant Key : L_TICKET_TRN_COMMONDITY_CD(20549) */
    public static final String VAR_CHAR_CONST_L_TICKET_TRN_COMMONDITY_CD = "L_TICKET_TRN_COMMONDITY_CD";
    // END   2020/05/22 [QC#56866, ADD]

    // START 2023/03/10 S.Nakatani [QC#55645,ADD]
    /** Inv Num On Account */
    public static final String INV_NUM_ON_ACCOUNT = "OnAccount";

    /** Inv Num On Account Only */
    public static final String INV_NUM_ON_ACCOUNT_ONLY = "1";

    /** NFCM0533E */
    public static final String NFCM0533E = "NFCM0533E";

    /** NFCM0594E */
    public static final String NFCM0594E = "NFCM0594E";

    /** NFDM0060E */
    public static final String NFDM0060E = "NFDM0060E";

    /** NWZM2316E */
    public static final String NWZM2316E = "NWZM2316E";
    // END 2023/03/10 S.Nakatani [QC#55645,ADD]
}
