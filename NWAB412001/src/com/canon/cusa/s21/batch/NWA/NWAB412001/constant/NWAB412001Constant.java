/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001.constant;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * NWAB412001Constant
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 12/01/2016   SRAA            K.Aratani       Update          QC#15539
 * 04/03/2017   FUJITSU         S.Iidaka        Update          QC#18179
 * 03/31/2017   SRAA            K.Aratani       Update          QC#18210
 * 04/14/2017   SRAA            K.Aratani       Update          QC#18306
 * 08/04/2017   FUJITSU         R.Nakamura      Update          QC#19839
 * 02/16/2018   SRAA            K.Aratani       Update          QC#24078, QC#24093
 *</pre>
 */
public class NWAB412001Constant {

    /** Program ID */
    public static final String PROGRAM_ID = "NWAB412001";

    public static final String QUOTE_LINE_TP_TRADEIN = "TRADEIN";

    public static final String QUOTE_LINE_TP_UPGRADEBUYOUTREBATE = "UPGRADEBUYOUTREBATE";

    public static final String TRX_TP_TXT_LEASE = "LEASE";

    public static final String TRX_TP_TXT_RENTAL = "RENTAL";

    public static final String YYYYMMDDHHMNSSFFF = "yyyyMMddHHmmssSSS";

    public static final String LEASE_PMT_FREQ_MONTHLY = "1";

    public static final String LEASE_PRCH_OPT_FMV = "01";

    public static final String INVALID_DATE = "19000101";

    public static final String HYPHEN = "-";

    public static final String FORMAT_CTAC_PSN_CMNT_TXT = "%s\r\nOperating Hrs: %s";

    public static final String FORMAT_CTAC_PSN_CMNT_TXT_2 = "Operating Hrs: %s";
    
    public static enum CONFIG_TYPE {
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

    public static enum DTL_TYPE {
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
    }

    public static enum PRC_CALC_BASE_TYPE {
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
        FREIGHT_AMOUNT,
    }

    public static enum RTRN_DTL_TYPE {
        /** Process (7) (OCE-PROMO) */
        OCE_PROMO,
        /** Process (9) (TRADE-IN (RMA)) */
        TRADE_IN_RMA,
        /** Process (12) (BUYOUT (RMA)) */
        BUYOUT_RMA,
        /** Process (13) (UPGRADE RETURN (RMA)) */
        UPGRADE_RETURN_RMA,
    }

    public static enum SLS_CR_TYPE {
        /** Process (1) (HEADER) */
        HEADER,
        /** Process (16) (Ship Config) */
        SHIP_CONFIG,
        /** Process (16) (RMA Config) */
        RMA_CONFIG,
    }

    public static enum CTAC_PSN_TYPE {
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
        OCE_PROMOTION_RMA,
    }

    public static enum CTAC_PSN_SUB_TYPE {
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

    public static enum ISTL_INFO_TYPE {
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

    public static enum DELY_INFO_TYPE {
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

    public static enum SITE_SRVY_TYPE {
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

    public static enum SHELL_TYPE {
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

    public static enum SVC_USG_PRC_TYPE {
        /** Process (22) Billable (Copier) */
        BILLABLE,
        /** Process (22) Physical (Copier) */
        PHYSICAL,
    }

    /**
     * Message IDs
     */
    public static enum MSG_ID {
        /** Data delete failure. (table name is [@]) */
        NWAM0730E,
        /** Access: [@] to Table: [@] failed. */
        NMAM8010E,
        /** No data found. [Table Name : @, PKey : @] */
        NWAM0525E,
        /** It does not exist in @} table. */
        NWAM0809E,
        /** [@] is mandatory. */
        ZZZM9025E,
        // Add Start 2017/08/04 QC#19839
        /** The "Model ID" set in "Line Config" and "Service Price" is different. */
        NWAM0937E,
        // Add End 2017/08/04 QC#19839
    }

    /**
     * variable character constant
     */
    public static enum VAR_CHAR_CONST_NM {
        /** SOM MDSE code for TRADE IN */
        SOM_MDSE_TRADE_IN,
        /** SOM MDSE code for UPGRADE RETURN RMA */
        SOM_MDSE_UPGRADE_RETURN_RMA,
        /** SOM default price list */
        SOM_DEFAULT_PRICE_LIST,
//        /** SOM copier base price category code */
//        SOM_COPIER_BASE_PRC_CATG_CD,
//        /** SOM non copier base price category code */
//        SOM_NONCOPIER_BASE_PRC_CATG_CD,
//        /** SOM copier charge price category code */
//        SOM_COPIER_CHRG_PRC_CATG_CD,
//        /** SOM non copier charge price category code */
//        SOM_NONCOPIER_CHRG_PRC_CATG_CD,
//        /** SOM non copier service price category code */
//        SOM_NONCOPIER_SVC_PRC_CATG_CD,
//        /** SOM service revenue allocation merchandise code */
//        SOM_SVC_REV_ALLOC_MDSE_CD,
        /** SOM writing rep split percent */
        SOM_WRITING_REP_SPLIT_PERCENT,
        /** SOM default payment term cash discount code */
        SOM_DEF_PMT_TERM_CASH_DISC_CD,
        /** supply vendor code for CFS */
        CFS_VND_CD_FOR_SOM,
        /** supply vendor code for CIT */
        CIT_VND_CD_FOR_SOM,
        /** CFS bill to location */
        CANON_E22_CFS_INFO_BILL_TO,
        /** CFS bill to account */
        CANON_E22_CFS_INFO_ACCT,
        DELY_CMNT_FIRST_NAME_FLG,
        DELY_CMNT_LAST_NAME_FLG,
        DELY_CMNT_PHONE_FLG,
        DELY_CMNT_PHONE_EXTN_FLG,
        DELY_CMNT_FAX_FLG,
        DELY_CMNT_EMAIL_FLG,
        DELY_CMNT_NOTE_FLG,
        DELY_CMNT_HRS_FROM_FLG,
        DELY_CMNT_HRS_TO_FLG,
        DELY_CMNT_STEPS_FLG,
        DELY_CMNT_ELEV_FLG,
        DELY_CMNT_LDOCK_FLG,
        DELY_CMNT_HRS_FROM_NM,
        DELY_CMNT_HRS_TO_NM,
        DELY_CMNT_STEPS_NM,
        DELY_CMNT_ELEV_NM,
        DELY_CMNT_LDOCK_NM,
        DROP_SHIP_RTL_WH_CD,
        SOM_TRADE_IN_NON_CANON,
        LEASE_BYOT_MDSE_CD,
        ARCS_SPLY_SITE_CD_FOR_SOM,
        SOM_NEED_RENTAL_AVAIL_ITEM_CHK,
        SOM_RMA_DEF_PRC_LIST,  //QC#24078, QC#24093
    }

    /**
     * interface id
     */
    public static enum INTERFACE_ID {
        /** SOM interface id */
        NWAI4120,
    }

    /**
     * Flag
     */
    public static enum FLG {
        /** YES */
        YES,
        /** NO */
        NO,
    }

    /**
     * SOM Contract Indicator
     */
    public static enum SOM_CONTR_IND {
        /** NEW */
        N,
        /** Add to Fleet Contract */
        F,
        /** New Aggregate */
        A,
        /** Add to Aggregate */
        P,
    }

    /**
     * SOM service meter type
     */
    public static enum SOM_SRV_MTR_TP {
        /** Black */
        Black,
        /** color */
        Color,
    }

    /**
     * SOM buy out name : CFS
     */
    public static final String SOM_BUYOUT_NM_CFS = "Canon Financial Services";

    /**
     * SOM buy out name : CIT
     */
    public static final String SOM_BUYOUT_NM_CIT = "CIT";

    /**
     * SOM buy out name : You
     */
    public static final String SOM_BUYOUT_NM_YOU = "You";

    /**
     * SOM fleet bill by type : CFS
     */
    public static final String SOM_FLEET_BILL_BY_TYPE_CFS = "CFS";
    
    //QC#15539-14
    public static enum RENTAL_TYPE {
        EQUIPMENT,
        SERVICE,
    }
    //QC#18210
    public static enum CONTRACT_TYPE {
        /** Fleet */
        FLEET,
        /** Aggregate */
        AGGREGATE,
        /** Regular */
        REGULAR,
    }
    
}
