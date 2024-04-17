package com.canon.cusa.s21.batch.NWC.NWCB722001;

/**
 * <pre>
 * CSMP Auto Claim creation Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/28   CSAI            Miyauchi        Create          N/A
 * 2018/01/05   Fujitsu         Hd.Sugawara     Update          QC#22371
 * 
 * </pre>
 */
public interface NWCB722001Constant {

	public static enum VAR_CHAR_ARG {
        CSMP_CLAIM_BILL_TO_ACCT,
        CSMP_CLAIM_BILL_TO_CUST,
        CSMP_CLAIM_PMT_TERM_CASH_DISC,
        CSMP_CLAIM_TRX_CD,
        CSMP_CLAIM_TRX_RSN_CD,
        CSMP_CLAIM_DS_INV_TP_CD,
        CSMP_PRC_LIST_05
    }

    public static enum MSG_ID {
        NWCM0059E,
        NMAM8432E,
        ZZMM0007E
    }
    
    public static enum FLG {
        Y,
        N
    }

    public static enum DEF_VAL {
        INV_LINE_SUB_NUM_000("000"),
        INV_LINE_SUB_NUM_001("001"),
        TRX_LINE_SUB_NUM_001("001"),
        CONF_CATG_CD_LINE_CONF("01"),
        TRX_LINE_NUM_1("1"),
        TRX_LINE_SUB_NUM_1("1"),
        CONF_CATG_CD_O("O"),
        PRC_DTL_GRP_CD_BASE("BASE"),
        PRC_COND_UNIT_CD_AMT("A"),
        EXCLUDE_CD_0("0"),
        INV_LINE_SUB_TRX_NUM("001"),
        INV_TRX_LINE_SUB_NUM("001"),
        INV_PROC_STS_CD_ERROR("9"),
        INV_PROC_STS_CD_NONE("0"),
        PROGRAM_ID("NWCB7220"),
        CSMP_INV_PROC_STS_CD_COMP("2"),
        CSMP_SPEC_NAME_START("CSMP SLG");

        /** value */
        private String value;

        private DEF_VAL (String value) {
            this.value = value;
        }

        /**
         * @return String
         */
        public String getValue() {
            return value;
        }
    }
    
    public static enum PRC_CD {
        SUCCESS("00"),
        ERROR("99");

        /** value */
        private String value;

        private PRC_CD (String value) {
            this.value = value;
        }

        /**
         * @return String
         */
        public String getValue() {
            return value;
        }
    }

    public static enum MAIL {
        ML_TMPL_ID("NWCB7220M001"),
        ML_GRP_ID_FROM("FROM0005"),
        ML_GRP_ID_TO("NWCB7220"),
        REPLACE_SUB_BATCH_ID("batchId"),
        REPLACE_ERR_DT("errDate"),
        REPLACE_MESSAGE("message");

        /** value */
        private String value;

        private MAIL (String value) {
            this.value = value;
        }

        /**
         * @return String
         */
        public String getValue() {
            return value;
        }
    }

    public static enum ERR_MSG {
        CONTRACT_ERROR("Not Contract Error : Merchandise Code : "),
        INSTLATION_ERRO("Not Install Date Error : Merchandise Code : "),
        IMPORT_INVOICE_ERROR("Invoice Import API Error : Merchandise Code : ");

        /** value */
        private String value;

        private ERR_MSG (String value) {
            this.value = value;
        }

        /**
         * @return String
         */
        public String getValue() {
            return value;
        }
    }

    public static enum COL_NM_SL {
        INV_NUM,
        INV_BOL_LINE_NUM,
        INV_LINE_NUM,
        INV_LINE_SUB_NUM,
        INV_LINE_SUB_TRX_NUM,
        CPO_ORD_NUM,
        DS_ORD_POSN_NUM,
        CPO_DTL_LINE_NUM,
        CPO_DTL_LINE_SUB_NUM,
        DS_CPO_LINE_NUM,
        DS_CPO_LINE_SUB_NUM,
        LINE_BIZ_TP_CD,
        DS_BIZ_AREA_CD,
        DS_ORD_CATG_CD,
        DS_ORD_TP_CD,
        DS_ORD_RSN_CD,
        CUST_ISS_PO_NUM,
        CUST_ISS_PO_DT,
        INV_DT,
        SHIP_DT,
        DEAL_CCY_CD,
        SELL_TO_CUST_CD,
        SOLD_TO_CUST_LOC_CD,
        SELL_TO_LOC_NM,
        SELL_TO_ADDL_LOC_NM,
        SELL_TO_FIRST_LINE_ADDR,
        SELL_TO_SCD_LINE_ADDR,
        SELL_TO_THIRD_LINE_ADDR,
        SELL_TO_FRTH_LINE_ADDR,
        SELL_TO_CTY_ADDR,
        SELL_TO_PROV_NM,
        SELL_TO_CNTY_NM,
        SELL_TO_ST_CD,
        SELL_TO_POST_CD,
        SELL_TO_CTRY_CD,
        SELL_TO_FIRST_REF_CMNT_TXT,
        SELL_TO_SCD_REF_CMNT_TXT,
        SHIP_TO_CUST_ACCT_CD,
        SHIP_TO_CUST_CD,
        DROP_SHIP_FLG,
        SHIP_TO_LOC_NM,
        SHIP_TO_ADDL_LOC_NM,
        SHIP_TO_FIRST_LINE_ADDR,
        SHIP_TO_SCD_LINE_ADDR,
        SHIP_TO_THIRD_LINE_ADDR,
        SHIP_TO_FRTH_LINE_ADDR,
        SHIP_TO_CTY_ADDR,
        SHIP_TO_CNTY_NM,
        SHIP_TO_PROV_NM,
        SHIP_TO_ST_CD,
        SHIP_TO_POST_CD,
        SHIP_TO_CTRY_CD,
        SHIP_TO_FIRST_REF_CMNT_TXT,
        SHIP_TO_SCD_REF_CMNT_TXT,
        CSMP_CONTR_NUM,
        DLR_REF_NUM,
        UOM_CD,
        ORD_CUST_UOM_QTY,
        MDSE_CD,
        ORD_TAKE_MDSE_CD,
        MDSE_NM,
        COA_PROD_CD,
        SET_MDSE_CD,
        ORD_QTY,
        SHIP_QTY,
        DEAL_NET_UNIT_PRC_AMT,
        INV_LINE_DEAL_NET_AMT,
        CSMP_PRC_LIST_CD,
        SVC_CONFIG_MSTR_PK,
        FIRST_BLLG_ATTRB_VAL_TXT,
        SCD_BLLG_ATTRB_VAL_TXT,
        THIRD_BLLG_ATTRB_VAL_TXT,
        FRTH_BLLG_ATTRB_VAL_TXT,
        FIFTH_BLLG_ATTRB_VAL_TXT,
        SIXTH_BLLG_ATTRB_VAL_TXT,
        SLS_ADMIN_PSN_CD,
        SLS_REP_TOC_CD,
        INV_DPLY_QTY,
        DFRD_ACCTG_RULE_CD,
        DS_ORD_LINE_CATG_CD,
        BASE_CMPT_FLG,
        PRC_CATG_NM,
        SPCL_CSMP_EXCL_AR_NM,
        ISTL_DT,
        CSMP_AFT_MKT_MDSE_CD,
        PRC_CATG_CD,
        SER_NUM,
        ORD_DT,
        ERR_MSG,
        CSMP_PRC_LIST_CD_SAVE,
        ISTL_DT_SAVE,
        INV_LINE_SPL_PCT,
        SLS_REP_CR_PCT,
        SET_FLG,
        DEAL_GRS_TOT_PRC_AMT,
        FUNC_GRS_TOT_PRC_AMT,
        FUNC_SLS_CR_AMT,
        INV_LINE_FUNC_NET_AMT,
        DEAL_GRS_UNIT_PRC_AMT,
        // Mod Start 2018/01/05 QC#22371
        //CSMP_NUM
        CSMP_NUM,
        SHIP_FROM_INVTY_LOC_CD,
        CSMP_EXCL_FLG
        // Mod End 2018/01/05 QC#22371
    }

}
