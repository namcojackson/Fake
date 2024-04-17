/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * EOPS Interface Batch NWAB415001Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 * 
 * </pre>
 */
public class NWAB415001Constant {

    public enum SITE_SRVY_TYPE {
        /** Header delivery info */
        HEADER_DELY_INFO,
        /** Process (5) (REGULAR SHIP CONFIG) */
        REGULAR_SHIP_CONFIG,
        /** Process (8) (TRADE-IN (RMA)) */
        TRADE_IN_RMA,
        /** Process (12) (BUYOUT (RMA)) */
        BUYOUT_RMA,
        /** Process (13) (UPGRADE RETURN (RMA)) */
        UPGRADE_RETURN_RMA,
        /** Process (21) (OCE PROMOTION (RMA)) */
        OCE_PROMOTION_RMA,
    }

    public enum SLS_CR_TYPE {
        /** Process (1) (HEADER) */
        HEADER,
        /** Process (16) (Ship Config) */
        SHIP_CONFIG,
        /** Process (16) (RMA Config) */
        RMA_CONFIG,
    }

    public enum ISTL_INFO_TYPE {
        /** Header delivery info */
        HEADER_DELY_INFO,
        /** Process (5) (REGULAR SHIP CONFIG) */
        REGULAR_SHIP_CONFIG,
        /** Process (8) (TRADE-IN (RMA)) */
        TRADE_IN_RMA,
        /** Process (12) (BUYOUT (RMA)) */
        BUYOUT_RMA,
        /** Process (13) (UPGRADE RETURN (RMA)) */
        UPGRADE_RETURN_RMA,
        /** Process (21) (OCE PROMOTION (RMA)) */
        OCE_PROMOTION_RMA
        /** Process (29) (EOPS(RMA)) */
        , EOPS_RMA,
    }

    public enum DELY_INFO_TYPE {
        /** Header delivery info */
        HEADER_DELY_INFO,
        /** Process (5) (REGULAR SHIP CONFIG) */
        REGULAR_SHIP_CONFIG,
        /** Process (8) (TRADE-IN (RMA)) */
        TRADE_IN_RMA,
        /** Process (12) (BUYOUT (RMA)) */
        BUYOUT_RMA,
        /** Process (13) (UPGRADE RETURN (RMA)) */
        UPGRADE_RETURN_RMA,
        /** Process (21) (OCE PROMOTION (RMA)) */
        OCE_PROMOTION_RMA
        /** Process (29) (EOPS(RMA)) */
        , EOPS_RMA,
    }

    /**     * EOPS Contract Indicator     */
    public static enum EOPS_CONTR_IND {
        /** NEW */
        N,
        /** Add to Fleet Contract */
        F,
        /** New Aggregate */
        A,
        /** Add to Aggregate */
        P,
    }

    /**     * Flag     */
    public static enum FLG {
        /** YES */
        YES,
        /** NO */
        NO,
    }

    public enum RENTAL_TYPE {
        /** */
        EQUIPMENT,
        /** */
        SERVICE,
    }

    public enum SVC_USG_PRC_TYPE {
        /** Process (22) Billable (Copier) */
        BILLABLE,
        /** Process (22) Physical (Copier) */
        PHYSICAL,
    }

    public enum CONTRACT_TYPE {
        /** Fleet */
        FLEET,
        /** Aggregate */
        AGGREGATE,
        /** Regular */
        REGULAR,
    }

    public enum SHELL_TYPE {
        /**
         * Process (22) (COPIER) (Regular) Process (22) Copier (per
         * Model) Process (22) (COPIER) Process (22) Billable (Copier)
         * Process (23) Base Only- Copier Process (24) Additional
         * Charge - Copier
         */
        COPIER,
        /**
         * Process (22) (NON-COPIER) (Regular) Process (25) Non-Cpoier
         * (per Model) Process (22) (NON-COPIER) Process (22) Physical
         * (Copier) Process (26) Base Only - Non Copier Process (27)
         * Additional Charge - Non Copier
         */
        NON_COPIER,
    }

    public enum SHELL_TYPE_KEY {
        /** */
        COPIER("Copier")
        /** */
        , NON_COPIER("Non-Copier")
        /* */
        ;

        private final String val;

        private SHELL_TYPE_KEY(final String val) {
            this.val = val;
        }

        public String getValue() {
            return this.val;
        }
    }

    public enum CTAC_PSN_SUB_TYPE {
        /** DELIVERY(Header) */
        H_DELIVERY,
        /** BILLING(Header) */
        H_BILLING,
        /** IT(Header) */
        H_IT,
        /** SUBSERVICES(Header) */
        H_SUBSERVICES,
        /** H_EMANAGE_USER(Header) */
        H_EMANAGE_USER,
        /** H_EMANAGE_USER(Header) */
        H_EMANAGE_ADMIN,
        /** PRIMARY(Config) */
        C_PRIMARY,
        /** SECONDARY(Config) */
        C_SECONDARY,
        /** C_EMANAGE_USER(Config) */
        C_EMANAGE_USER,
        /** C_EMANAGE_ADMIN(Config) */
        C_EMANAGE_ADMIN,
        /** PICKUP(Config) */
        C_PICKUP,
    }

    public enum CTAC_PSN_TYPE {
        /** Process (1) (HEADER) */
        HEADER,
        /** Process (5) (REGULAR SHIP CONFIG) */
        REGULAR_SHIP_CONFIG,
        /** Process (8) (TRADE-IN (RMA)) */
        TRADE_IN_RMA,
        /** Process (12) (BUYOUT (RMA)) */
        BUYOUT_RMA,
        /** Process (13) (UPGRADE RETURN (RMA)) */
        UPGRADE_RETURN_RMA,
        /** Process (21) (OCE PROMOTION (RMA)) */
        OCE_PROMOTION_RMA
        /** Process (29) (EOPS (RMA)) */
        , EOPS_RMA,
    }

    public enum RTRN_DTL_TYPE {
        /** Process (7) (OCE-PROMO) */
        OCE_PROMO,
        /** Process (9) (TRADE-IN (RMA)) */
        TRADE_IN_RMA,
        /** Process (12) (BUYOUT (RMA)) */
        BUYOUT_RMA,
        /** Process (13) (UPGRADE RETURN (RMA)) */
        UPGRADE_RETURN_RMA
        /** Process (30) (EOPS RETURN) */
        , EOPS_RETURN,
    }

    public enum PRC_CALC_BASE_TYPE {
        /** Process (6) (REGULAR SHIP LINE) */
        REGULAR_SHIP_LINE,
        /** Process (7) (OCE-PROMO) */
        OCE_PROMO,
        /** Process (9) (TRADE-IN) */
        TRADE_IN,
        /** Process (10) (UPGRADEBUYOUTREBATE) */
        UPGRADEBUYOUTREBATE,
        /** Process (11) (NOT UPGRADEBUYOUTREBATE AND TRADE-IN) */
        NOT_UPGRADEBUYOUTREBATE_AND_TRADE_IN,
        /** Process (12) (BUYOUT) */
        BUYOUT,
        /** Process (14) (HEADER REBATE) */
        HEADER_REBATE,
        /** Process (15) (SPIFF ITEM) */
        SPIFF_ITEM,
        /** Process (17) (Freight Amount) */
        FREIGHT_AMOUNT
        /** Process (30) (EOPS Return) */
        , EOPS_RETURN,
    }

    public enum DTL_TYPE {
        /** Process (6) (REGULAR SHIP LINE) */
        REGULAR_SHIP_LINE,
        /** Process (7) (OCE-PROMO) */
        OCE_PROMO,
        /** Process (9) (TRADE-IN) */
        TRADE_IN,
        /** Process (10) (UPGRADEBUYOUTREBATE) */
        UPGRADEBUYOUTREBATE,
        /** Process (11) (NOT UPGRADEBUYOUTREBATE AND TRADE-IN) */
        NOT_UPGRADEBUYOUTREBATE_AND_TRADE_IN,
        /** Process (12) (BUYOUT) */
        BUYOUT,
        /** Process (14) (HEADER REBATE) */
        HEADER_REBATE,
        /** Process (15) (SPIFF ITEM) */
        SPIFF_ITEM
    }

    public enum CONFIG_TYPE {
        /** Process (5) (REGULAR SHIP CONFIG) */
        REGULAR_SHIP_CONFIG,
        /** Process (8) (TRADE-IN (RMA)) */
        TRADE_IN_RMA,
        /** Process (12) (BUYOUT (RMA)) */
        BUYOUT_RMA,
        /** Process (13) (UPGRADE RETURN (RMA)) */
        UPGRADE_RETURN_RMA,
        /** Process (21) (OCE PROMOTION (RMA)) */
        OCE_PROMOTION_RMA
        /** Process (29) (EOPS (RMA)) */
        , EOPS_RMA
    }

    /** */
    public enum SOM_BYOT_NM {
        /** */
        CANON_FINANCIAL_SERVICES("Canon Financial Services")
        /** */
        , CIT("CIT")
        /** */
        , YOU("You")
        /* */
        ;

        private final String val;

        private SOM_BYOT_NM(final String val) {
            this.val = val;
        }

        public String getValue() {
            return this.val;
        }
    }

    /** */
    public enum SOM_SRV_MTR_TP {
        /** */
        BLACK("Black")
        /** */
        , COLOR("Color")
        /* */
        ;

        private final String val;

        private SOM_SRV_MTR_TP(final String val) {
            this.val = val;
        }

        public String getValue() {
            return this.val;
        }
    }

    /** FLEET_BILL_BY_TXT */
    public enum FLEET_BILL_BY_TXT {
        /** */
        CSA
        /** */
        , CFS
        /* */;
    }

    /** Message Id */
    public enum MSG_ID {
        /** Data delete failure. (table name is [@]) */
        NWAM0730E
        /** Access: [@] to Table: [@] failed. */
        , NMAM8010E
        /** No data found. [Table Name : @, PKey : @] */
        , NWAM0525E
        /** It does not exist in @} table. */
        , NWAM0809E
        /** [@] is mandatory. */
        , ZZZM9025E
        /** The "Model ID" set in "Line Config" and "Service Price" is different. */
        , NWAM0937E
    }

    public enum VAR_CHAR_CONST_NM {
        /** */
        ARCS_SPLY_SITE_CD_FOR_EOPS
        /** */
        , CFS_VND_CD_FOR_EOPS
        /** */
        , EOPS_DEFAULT_PRICE_LIST
        /** */
        , EOPS_DEF_PMT_TERM_CASH_DISC_CD
        /** */
        , EOPS_MDSE_TRADE_IN
        /** */
        , EOPS_MDSE_UPGRADE_RETURN_RMA
        /** */
        , EOPS_MDSE_EOPS_RETURN
        /** */
        , EOPS_TRADE_IN_NON_CANON
        /** */
        , EOPS_WRITING_REP_SPLIT_PERCENT
        /** */
        , EOPS_ACCT_MGR_SPLIT_PERCENT
        /** */
        , LEASE_BYOT_MDSE_CD
        /** */
        , DROP_SHIP_RTL_WH_CD
    }

    /**     * interface id     */
    public static enum INTERFACE_ID {
        /** EOPS interface id */
        NWAI4150 //
        /* */,
    }

    public static enum CUTOFF_LEN {
        /**  */
        CUST_ISS_PO_NUM(35) //
        /**  */
        , SLS_REP_ID(8)
        /**  */
        , LEASE_PRCH_ORDER_TXT(35)
        /**  */
        , LEASE_TERM_MTH_AOT(3)
        /**  */
        , MDL_DESC_TXT(90)
        /**  */
        , YYYYMMDD(8)
        /** Length:30 */
        , CTAC_PSN_FIRST_NM(30)
        /** Length : 30 */
        , CTAC_PSN_LAST_NM(30)
        /** Length : 20 */
        , CTAC_PSN_TEL_NUM(20)
        /** Length : 20 */
        , CTAC_PSN_FAX_NUM(20)
        /** Length : 1000 */
        , CTAC_PSN_CMNT_TXT(1000)
        /** Length : 10 */
        , CTAC_PSN_EXTN_NUM(10)
        /** Length : 16 */
        , MDSE_CD(16)
        /** Length : 30 */
        , MDSE_NM(30)
        /** Length : 5 */
        , ZIP_CD(5)
        /** Length : 60 */
        , LINE_ADDR(60)
        /** Length : 25 */
        , CTY_ADDR(25)
        /** Length : 2 */
        , ST(2)
        /** Length : 30 */
        , CNTY_NM(30)
        /** Length : 15 */
        , POST_CD(15)
        /** Length : 35 */
        , SOM_MAINT_PO_NUM(35)
        /** Length : 9 */
        , ORD_SRC_REF_NUM(9)
        /** Length : 240 */
        , DELY_ADDL_CMNT_TXT(240)
        /* */;
        private final int len;

        private CUTOFF_LEN(final int len) {
            this.len = len;
        }

        public int getLen() {
            return this.len;
        }

    }

    /**  */
    public static final String TRX_TP_TXT_RENTAL = "RENTAL";

    /**  */
    public static final String FORMAT_CTAC_PSN_CMNT_TXT = "%s\r\nOperating Hrs: %s";

    /**  */
    public static final String FORMAT_CTAC_PSN_CMNT_TXT_2 = "Operating Hrs: %s";

    /**  */
    public static final String TRX_TP_TXT_MONTHLY_PAYMENT = "MONTHLY PAYMENT";

    /**  */
    public static final String LEASE_PMT_FREQ_MONTHLY = "1";

    /**  */
    public static final String INVALID_DATE = "19000101";

    /**  */
    public static final String EOPS_PREFIX_ORD_SRC_NUM = "E";

    /**  */
    public static final String QUOTE_LINE_TP_TRADEIN = "TRADEIN";

    /**  */
    public static final String QUOTE_LINE_TP_UPGRADEBUYOUTREBATE = "UPGRADEBUYOUTREBATE";

    /** split type */
    public static final String INTER_REGIONAL = "Inter-Regional";

    /**  */
    public static final BigDecimal HUNDRED_PCT = BigDecimal.ONE.movePointRight(2);

    /** Time Start Position */
    public static final int TIME_START_POS = 8;

    /** Time End Position */
    public static final int TIME_END_POS = 4;

}
