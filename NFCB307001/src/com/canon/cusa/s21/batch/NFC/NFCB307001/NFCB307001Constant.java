/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB307001;

import java.math.BigDecimal;

/**
 * <pre>
 * CSMP Credit Memo Creation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/26   CSAI            Miyauchi        Create          N/A
 * 2016/11/16   Hitachi         T.Tsuchida      Update          QC#15881
 * 2018/02/06   Hitachi         E.Kameishi      Update          QC#23018
 * </pre>
 */
public interface NFCB307001Constant {

    public static String AR_PROCESS_FLG_P = "P";
    public static String AR_PROCESS_FLG_E = "E";
    public static String UNIQUE_INPUT_KEY_SUBMIT_CREDIT = "CD#";
    /** ONL_BAT_TP */
    public static final String ONL_BAT_TP = "1";
    /** scale */
    public static final int SIDX = 8;
    /** ten */
    public static final int CIDX = 10;
    /** hundred */
    public static final int HIDX = 100;
    /** pulldownIdx */
    public static final int PIDX = 100;
    public String AR_CR_TP_DEFAULT = "01";

    enum SPLIT_SYMBOL {

        COMMAS(","),
        HYPHEN("-");

        /** value */
        private String value;

        private SPLIT_SYMBOL(String value) {
            this.value = value;
        }

        /**
         * @return String
         */
        public String getValue() {
            return value;
        }
    }

    enum VAR_CHAR_CONST {
        NFCB307001_CR_DR_RSN_CD,
        NFCL307001_AR_CR_TP_CD,
        NFCL307001_DS_INV_TP_CRM_CSMP,
        NFCB307001_TRX_CD,
        NFCB307001_TRX_RSN_CD
    }

    enum SQL_ARG_J {

        GLBL_CMPY_CD("glblCmpyCd"),
//Mod Start QC#12760
        //AR_PROCESS_FLG_P("processFlgP"),
        INV_AR_PROC_STS_CD("invArProcStsCd"),
//Mod End QC#12760
        CR_DR_RSN_CD("rsnCd_10"),
        // START 2016/11/16 T.Tsuchida [QC#15881,ADD]
        INV_TP_CD_IS_CM("invTpCdIsCM"),
        // END 2016/11/16 T.Tsuchida [QC#15881,ADD]
        INV_NUM("invNum"),
        INV_TP_CD_1("invTpCd_1");

        /** value */
        private String value;

        private SQL_ARG_J(String value) {
            this.value = value;
        }

        /**
         * @return String
         */
        public String getValue() {
            return value;
        }
    }

    enum DTO_COL_NAME {
         VND_INV_NUM,
         ORIG_VND_INV_NUM,
         INV_DT,
         INV_TP_CD,
         VND_CD,
         INV_TOT_DEAL_NET_AMT,
         INV_TOT_DEAL_SLS_AMT,
         INV_TOT_DEAL_FRT_AMT,
         INV_TOT_DEAL_TAX_AMT,
         INV_TOT_DEAL_DISC_AMT,
         INV_TOT_DEAL_INS_AMT,
         CUST_ISS_PO_NUM,
         PAYER_CUST_CD,
         INV_TOT_PKG_QTY,
         INV_TOT_GRS_WT,
         VND_INV_BOL_LINE_NUM,
         VND_INV_LINE_NUM,
         VND_INV_LINE_SUB_NUM,
         VND_INV_LINE_SUB_TRX_NUM,
         VND_CPO_ORD_NUM,
         VND_MDSE_CD,
         MDSE_CD,
         MDSE_NM,
         ORD_QTY,
         SHIP_QTY,
         BO_QTY,
         COUNT_TOTAL,
         // START 2018/02/06 E.Kameishi [QC#23018, ADD]
         INV_NUM,
         CPO_ORD_NUM;
         // END 2018/02/06 E.Kameishi [QC#23018, ADD]
    }

    enum ERROR_MSG {
        ZZBM0074E,
        NFCM0857E;
    }
    
    enum AR_CR_PCT {

        DEFAULT(BigDecimal.valueOf(100));

        /** value */
        private BigDecimal value;

        private AR_CR_PCT(BigDecimal value) {
            this.value = value;
        }

        /**
         * @return String
         */
        public BigDecimal getValue() {
            return value;
        }
    }
}
