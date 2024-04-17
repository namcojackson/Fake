package com.canon.cusa.s21.batch.NFC.NFCB055001;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ECHK_NTFY_TP;

public interface NFCB055001Constant {

    /** Target Interface ID */
    public static final String TARGET_INFTERFACE_ID = "NFCI1110";

    public static String[] ECHK_NTFY_INFO_LIST = {"ECHK_NTFY_INFO"};

    public static final String INVOICE_SEPARATOR = ",";

    /** FLAG */
    public static enum FLG {
        Y,
        N
    }

    public static enum AR_CUST_REF_TP_CD {
        AR_CUST_REF_TP_CD_INV("INV");

        private String key;

        private AR_CUST_REF_TP_CD(String key) {	
            this.key = key;
        }

        public String getKey() {
            return key;	
        }
    }

    /** Table Name */
    public static enum TBL_NAME {
        ECHK_NTFY_INFO /** ECHK_NTFY_INFO */
    }

    /** DB Item Name Setting */
    public static enum DB_ITEM_NAME {
    	ECHK_NTFY_INFO_REF_TXT,
        GLBL_CMPY_CD, /** Global Company Code */
        RCPT_NUM,      /** Receipt Number */
        RCPT_CHK_NUM,
        AR_CASH_APPLY_STS_CD,
        ECHK_NTFY_TP_CD,
        ECHK_NTFY_DT,
        CLT_PSN_CD,
        FUNC_RCPT_AMT,
        AR_TRX_NUM,
        FUNC_ORIG_GRS_AMT,
        CLT_PTFO_NM,
        PAYER_CUST_CD,
        LOC_NM,
        ERR_STS_CD,
        AR_RCPT_RCV_INTFC_PK
    }

    /** DB Item Name Setting for J */
    public static enum DB_ITEM_NAME_J {
        ECHK_NTFY_INFO_REF_TXT("echkNtfyInfoRefTxt"),
        GLBL_CMPY_CD("glblCmpyCd"),                 /** Global Company Code */
        AR_RCPT_RCV_ERR_FLG("arRcptRcvErrFlg"),     /** AR Receipt Receive Error Flag */ 
        RCV_FUNC_ID("rcvFuncId"),                   /** Receive Function ID */
        AR_CASH_APPLY_STS_CD_U("arCashApplyStsCd_U"),
        AR_CASH_APPLY_STS_CD_P("arCashApplyStsCd_P"),
        AR_CASH_APPLY_STS_CD_A("arCashApplyStsCd_A"),
        RCPT_NUM("rcptNum"),
        AR_TRX_TP_CD_INV("arTrxTpCd_Inv"),
        AR_TRX_TP_CD_CRM("arTrxTpCd_Crm"),
        DS_ACCT_NUM("dsAcctNum");

        private String key;

        private DB_ITEM_NAME_J(String key) {	
            this.key = key;
        }

        public String getKey() {
            return key;	
        }
    }

    public static enum ECHK_NTFY_TP_CD {
        NO_NOTIFICATION(ECHK_NTFY_TP.NO_NOTIFICATION, null, null),
        UNIDENTIFIED_RECEIPT(ECHK_NTFY_TP.UNIDENTIFED, "NFCM0887E", "NFCM0891E"),
        RECEIPT_UNAPPLY(ECHK_NTFY_TP.PARTIAL_OR_UNAPPLIED, "NFCM0888E", "NFCM0892E"),
        INVOICE_UNAPPLY(ECHK_NTFY_TP.OPEN_BALANCE, "NFCM0889E", "NFCM0893E"),
        INTERFACE_ERROR(ECHK_NTFY_TP.ERROR, "NFCM0890E", "NFCM0894E");

        private String key;
        private String ntfyMsgId;
        private String errMsgId;

        private ECHK_NTFY_TP_CD(String key, String ntfyMsgId, String errMsgId) {	
            this.key = key;
            this.ntfyMsgId = ntfyMsgId;
            this.errMsgId = errMsgId;
        }

        public String getKey() {
            return key;	
        }

        public String getNtfyMsgId() {
            return ntfyMsgId;
        }

        public String getErrMsgId() {
            return errMsgId;
        }

        public static ECHK_NTFY_TP_CD fromKey(String key) {
            for (ECHK_NTFY_TP_CD echkNtfyTpCd : values()) {
                if (echkNtfyTpCd.getKey().equals(key)) {
                    return echkNtfyTpCd;
                }
            }
            return null;
        }
    }

    /**
     * 
     * @author q06799
     *
     */
    public static enum MSG_ERROR_CD {
        NFCM0532E, /** Message ID : NFCM0532E */
        NFCM0033E, /** Message ID : NFCM0033E */
        ZZMM0007E  /** Message ID : ZZMM0007E */
    }

    /***
     * 
     * @author q06799
     *
     */
    public static enum MAIL_CONST {
         ML_TMPL_ID("NFCB0550M001"),
         ML_DEFAULT_GRP_ID("NFCB0550"),
         ML_DEFAULT_GRP_ID_KEY_FROM("FROM"),
         ML_DEFAULT_GRP_ID_KEY_TO("TO"),
         ML_DEFAULT_GRP_ID_KEY_CC("CC");

        private String key;

        private MAIL_CONST(String key) {	
            this.key = key;
        }

        public String getKey() {
            return key;	
        }
    }

    public static enum MAIL_TEMPLATE_KEY {
        NOTIFYTP_MESSAGE_TXT("NotifyTpMessageTxt"),
        BATCH_DT("BatchDate"),
        INV_NUM("InvNum"),
        INV_AMT("InvAmt"),
        RCPT_NUM("ReceiptNum"),
        RCPT_AMT("ReceiptAmt"),
        PAYER_CUST_CD("PayerCustCd"),
        BILL_TO_LOC_NUM("BillToLocNm"),
        PORT_FOLIO_NM("PortfolioNm"),
        CLT_ANALYST("CltAnalyst"),
        CLT_SPR_VISOR("CltSuperVisor"),
        CLT_MANAGER("CltManager"),
        ERR_MSG("ErrMsg");

        private String key;

        private MAIL_TEMPLATE_KEY(String key) {    
            this.key = key;
        }

        public String getKey() {
            return key; 
        }
    }
}
