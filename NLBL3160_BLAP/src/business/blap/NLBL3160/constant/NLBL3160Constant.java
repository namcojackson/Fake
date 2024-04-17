/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3160.constant;

/**
 *<pre>
 * Distribution Coordinator Work Bench
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/13   CITS            S.Katsuma       Create          N/A
 * 2017/10/23   CITS            S.Katsuma       Update          QC#21947
 * 2018/05/29   CITS            S.Katsuma       Update          QC#25232
 *</pre>
 */

public class NLBL3160Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NLBL3160";

    /** Screen ID */
    public static final String SCREEN_ID = "NLBL3160Scrn00";

    /** Coordinator */
    public static final String FUNC_ID_COORD = BUSINESS_ID + "T010";

    /** Supervisor / Manager */
    public static final String FUNC_ID_SUPERVISOR_MANAGER = BUSINESS_ID + "T020";

    /** Logistics / IT */
    public static final String FUNC_ID_LOGI_IT = BUSINESS_ID + "T030";

    /** XX_SPL_CHAR_TXT */
    public static final String XX_SPL_CHAR_TXT = ";";

    /**
     * CPO_ORD_NUM
     */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /**
     * CONFIG_ID
     */
    public static final String CONFIG_ID = "CONFIG_ID";

    /**
     * TRX_REF_NUM
     */
    public static final String TRX_REF_NUM = "TRX_REF_NUM";

    /**
     * MDL_DESC_TXT
     */
    public static final String MDL_DESC_TXT = "MDL_DESC_TXT";

    /**
     * BACK_ORDER_CRITICAL
     */
    public static final String BACK_ORDER_CRITICAL = "BACK_ORDER_CRITICAL";

    /**
     * BACK_ORDER_ESSENTIAL
     */
    public static final String BACK_ORDER_ESSENTIAL = "BACK_ORDER_ESSENTIAL";

    /**
     * RTL_SWH_CD
     */
    public static final String RTL_SWH_CD = "RTL_SWH_CD";

    /**
     * ORD_BOOK_TS
     */
    public static final String ORD_BOOK_TS = "ORD_BOOK_TS";

    /**
     * AGE
     */
    public static final String AGE = "AGE";

    /**
     * RDD_DT
     */
    public static final String RDD_DT = "RDD_DT";

    /**
     * NEXT_ACT_DT
     */
    public static final String NEXT_ACT_DT = "NEXT_ACT_DT";

    /**
     * SCHD_COORD_STS_CD
     */
    public static final String SCHD_COORD_STS_CD = "SCHD_COORD_STS_CD";

    /**
     * SCHD_COORD_STS_DESC_TXT
     */
    public static final String SCHD_COORD_STS_DESC_TXT = "SCHD_COORD_STS_DESC_TXT";

    /**
     * DELIVERY_SCHEDULED_DATE
     */
    public static final String DELIVERY_SCHEDULED_DATE = "DELIVERY_SCHEDULED_DATE";

    /**
     * SELL_TO_CUST_CD
     */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /**
     * DS_ACCT_NM
     */
    public static final String DS_ACCT_NM = "DS_ACCT_NM";

    /**
     * SHIP_TO_CUST_LOC_CD
     */
    public static final String SHIP_TO_CUST_LOC_CD = "SHIP_TO_CUST_LOC_CD";

    /**
     * SHIP_TO_CITY_ADDR
     */
    public static final String SHIP_TO_CITY_ADDR = "SHIP_TO_CITY_ADDR";

    /**
     * SHIP_TO_ST_CD
     */
    public static final String SHIP_TO_ST_CD = "SHIP_TO_ST_CD";

    /**
     * WMS_STATUS
     */
    public static final String WMS_STATUS = "WMS_STATUS";

    /**
     * WMS_STATUS_DESC_TXT
     */
    public static final String WMS_STATUS_DESC_TXT = "WMS_STATUS_DESC_TXT";

    /**
     * DEINSTALL_DATE
     */
    public static final String DEINSTALL_DATE = "DEINSTALL_DATE";

    /**
     * EARLIEST_INSTALL_DATE
     */
    public static final String EARLIEST_INSTALL_DATE = "EARLIEST_INSTALL_DATE";

    /**
     * SVC_BR_CD_DESC_TXT
     */
    public static final String SVC_BR_CD_DESC_TXT = "SVC_BR_CD_DESC_TXT";

    /**
     * FSR_NUM
     */
    public static final String FSR_NUM = "FSR_NUM";

    /**
     * LOC_NM
     */
    public static final String LOC_NM = "LOC_NM";

    /**
     * RTL_WH_CD
     */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /**
     * RTL_WH_NM
     */
    public static final String RTL_WH_NM = "RTL_WH_NM";

    /**
     * CPO_TOT_DEAL_NET_AMT
     */
    public static final String CPO_TOT_DEAL_NET_AMT = "CPO_TOT_DEAL_NET_AMT";

    /**
     * CONFIG_CATG_CD
     */
    public static final String CONFIG_CATG_CD = "CONFIG_CATG_CD";

    // START 2017/10/23 S.Katsuma QC#21947 ADD
    /**
     * DATA_STATUS
     */
    public static final String DATA_STATUS = "DATA_STATUS";

    /**
     * DATA_STATUS
     */
    public static final String DS_CPO_CONFIG_PK = "DS_CPO_CONFIG_PK";

    /**
     * DATA_STATUS SCHEDULED
     */
    public static final String DS_SCHEDULED = "SCHEDULED";

    /**
     * DATA_STATUS NOT AVAILABLE
     */
    public static final String DS_NOT_AVAILABLE = "NOT AVAILABLE";

    /**
     * DATA_STATUS ORDERS TO SCHEDULE
     */
    public static final String DS_ORDERS_TO_SCHEDULE = "ORDERS TO SCHEDULE";
    // END 2017/10/23 S.Katsuma QC#21947 ADD

    /**
     * CPO_ANCHOR_ACTIVE_FLG
     */
    public static final String CPO_ANCHOR_ACTIVE_FLG = "CPO_ANCHOR_ACTIVE_FLG";

    /**
     * OUTPUT_MODE
     */
    public static final String OUTPUT_MODE = "OUTPUT_MODE";

    /**
     * OUTPUT_MODE_CPO
     */
    public static final String OUTPUT_MODE_CPO = "OUTPUT_MODE_CPO";

    /**
     * OUTPUT_MODE_CONFIG
     */
    public static final String OUTPUT_MODE_CONFIG = "OUTPUT_MODE_CONFIG";

    /**
     * TOT_MACH_ON_SALES_ORDER
     */
    public static final String TOT_MACH_ON_SALES_ORDER = "TOT_MACH_ON_SALES_ORDER";

    /**
     * TOT_RET_ON_SALES_ORDER
     */
    public static final String TOT_RET_ON_SALES_ORDER = "TOT_RET_ON_SALES_ORDER";

    // START 2018/05/29 S.Katsuma [QC#25232,ADD]
    /**
     * ORD_CATG_CTX_TP_CD_SE
     */
    public static final String ORD_CATG_CTX_TP_CD_SE = "SERVICE_EXCHANGE";

    /**
     * DS_ORD_CATG_CD
     */
    public static final String DS_ORD_CATG_CD = "DS_ORD_CATG_CD";

    /**
     * RMA_CONFIG_ID_PARAM_FLG
     */
    public static final String RMA_CONFIG_ID_PARAM_FLG = "RMA_CONFIG_ID_PARAM_FLG";
    // END 2018/05/29 S.Katsuma [QC#25232,ADD]

    /**
     * NLBL3080_NOT_ALLC_WH
     */
    public static final String NLBL3080_NOT_ALLC_WH = "NLBL3080_NOT_ALLC_WH";

}
