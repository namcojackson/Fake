/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB119001.constant;

/**
 *<pre>
 * NWAB2470:CUSA Retail Order import report Creation Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/06/2016   CITS            K.Ogino         Create          N/A
 * 01/25/2017   CITS            K.Ogino         Update          QC#11314
 *</pre>
 */
public class NPAB119001Constant {

    /** BIZ_APP_ID */
    public static final String BIZ_APP_ID = "NPAB1190";

    /** ROSS_SHIP_HDR */
    public static final String ROSS_SHIP_HDR = "ROSS_SHIP_HDR";

    /** ROSS_SHIP_ITEM */
    public static final String ROSS_SHIP_ITEM = "ROSS_SHIP_ITEM";

    /** ROSS_SHIP_SER */
    public static final String ROSS_SHIP_SER = "ROSS_SHIP_SER";

    /** VND_SHIP_TO_XREF */
    public static final String VND_SHIP_TO_XREF = "VND_SHIP_TO_XREF";

    /** VND_SHIP_TO_CUST_CD */
    public static final String VND_SHIP_TO_CUST_CD = "VND_SHIP_TO_CUST_CD";

    /** MSG_ROSS_SHIP_HDR_PK */
    public static final String MSG_ROSS_SHIP_HDR_PK = "[Processed PK] ROSS_SHIP_HDR_PK= %s";

    /** MSG_GLOBAL_COMPANY_CODE */
    public static final String MSG_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** MSG_SALES_DATE */
    public static final String MSG_SALES_DATE = "Sales Date";

    /** VAR_CHAR_CONST_PR_CRAT_SYSTEM_USER */
    public static final String VAR_CHAR_CONST_PR_CRAT_SYSTEM_USER = "PR_CRAT_SYSTEM_USER";

    /** CREATE */
    public static final String CREATE = "1";

    /** SUBMIT */
    public static final String SUBMIT = "1";

    // =================================================
    // Message Code
    // =================================================
    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /**
     * Failed to update @ table. @ is @.
     */
    public static final String NPAM1003E = "NPAM1003E";

    /**
     * Data insert failure.@
     */
    public static final String NPAM1341E = "NPAM1341E";

    /**
     * No data found on @. @ is @.
     */
    public static final String NPAM1007E = "NPAM1007E";

    /**
     * The Constant [@] was not found on Constant table.
     */
    public static final String NPAM1010E = "NPAM1010E";

    /**
     * Supplier Item Number cannot be obtained from ASL Table.
     */
    public static final String NPZM0268E = "NPZM0268E";

    /**
     * Vendor Code does not exist in Master.
     */
    public static final String NPZM0043E = "NPZM0043E";

    /**
     * A valid Merchandise Code does not exist in Master.
     */
    public static final String NPZM0045E = "NPZM0045E";

    // =================================================
    // Mail Param
    // =================================================
    /** MAIL_TEMPLATE_BATCH_ID_KEY */
    public static final String MAIL_TEMPLATE_BATCH_ID_KEY = "batchId";

    /** MAIL_TEMPLATE_ERR_MESSAGE_KEY */
    public static final String MAIL_TEMPLATE_ERR_MESSAGE_KEY = "message";

    /** MAIL_TEMPLATE_ERR_DATE */
    public static final String MAIL_TEMPLATE_ERR_DATE = "errDate";

    /** MAIL_FROM_ADDR_GRP */
    public static final String MAIL_FROM_ADDR_GRP = "System common";

    /** MAIL_FROM_ADDR_GRP_ID */
    public static final String MAIL_FROM_ADDR_GRP_ID = "FROM0005";

    /** MAIL_ADDR_TO_GRP */
    public static final String MAIL_ADDR_TO_GRP = BIZ_APP_ID;

    /** MAIL_TEMP_ID */
    public static final String MAIL_TEMP_ID = "NPAB1190M001";

    /** Mail Date Format : MM/dd/yyyy HH:mm:ss.SSS */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

    // =================================================
    // DB Param
    // =================================================

    /** FETCH_SIZE */
    public static final int FETCH_SIZE = 1000;

    /** DB_PARAM_CSA_GLBL_CMPY_CD */
    public static final String DB_PARAM_CSA_GLBL_CMPY_CD = "csaGlblCmpyCd";

    /** DB_PARAM_CUSA_GLBL_CMPY_CD */
    public static final String DB_PARAM_CUSA_GLBL_CMPY_CD = "cusaGlblCmpyCd";

    /** DB_PARAM_SALES_DATE */
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    /** DB_PARAM_CRAT_RWS_PROC_CD */
    public static final String DB_PARAM_CRAT_RWS_PROC_CD = "cratRwsProcCd";

    /** DB_PARAM_VND_XREF_TP_CD */
    public static final String DB_PARAM_VND_XREF_TP_CD = "vndXrefTpCd";

    /** DB_PARAM_VND_SHIP_TO_CUST_CD */
    public static final String DB_PARAM_VND_SHIP_TO_CUST_CD = "vndShipToCustCd";

    /** DB_PARAM_SO_NUM */
    public static final String DB_PARAM_SO_NUM = "soNum";

    /** DB_PARAM_PRIM_SPLY_FLG */
    public static final String DB_PARAM_PRIM_SPLY_FLG = "primSplyFlg";

    /** DB_PARAM_PRIM_PKG_UOM_FLG */
    public static final String DB_PARAM_PRIM_PKG_UOM_FLG = "primPkgUomFlg";

    /** DB_PARAM_SHIP_TO_ADDR_TP_CD_LIST */
    public static final String DB_PARAM_SHIP_TO_ADDR_TP_CD_LIST = "shipToAddrTpCdList";

    /** DB_PARAM_IMPT_PROC_CD */
    public static final String DB_PARAM_IMPT_PROC_CD = "imptProcCd";

    /** DB_PARAM_RGTN_STS_CD */
    public static final String DB_PARAM_RGTN_STS_CD = "rgtnStsCd";

    // =================================================
    // DB Column
    // =================================================
    /** SHPG_PLN_NUM */
    public static final String SHPG_PLN_NUM = "SHPG_PLN_NUM";

    /** SHPG_PLN_NUM_RSI */
    public static final String SHPG_PLN_NUM_RSI = "SHPG_PLN_NUM_RSI";

    /** SHPG_PLN_NUM_RSS */
    public static final String SHPG_PLN_NUM_RSS = "SHPG_PLN_NUM_RSS";

    /** SO_NUM */
    public static final String SO_NUM = "SO_NUM";

    /** SO_NUM_RSI */
    public static final String SO_NUM_RSI = "SO_NUM_RSI";

    /** SO_NUM_RSS */
    public static final String SO_NUM_RSS = "SO_NUM_RSS";

    /** SO_SLP_NUM */
    public static final String SO_SLP_NUM = "SO_SLP_NUM";

    /** SO_SLP_NUM_RSI */
    public static final String SO_SLP_NUM_RSI = "SO_SLP_NUM_RSI";

    /** SO_SLP_NUM_RSS */
    public static final String SO_SLP_NUM_RSS = "SO_SLP_NUM_RSS";

    /** MDSE_CD_RSI */
    public static final String MDSE_CD_RSI = "MDSE_CD_RSI";

    /** MDSE_CD_RSS */
    public static final String MDSE_CD_RSS = "MDSE_CD_RSS";

    /** MDL_NM */
    public static final String MDL_NM = "MDL_NM";

    /** SHIP_QTY */
    public static final String SHIP_QTY = "SHIP_QTY";

    /** SHIP_TO_ADDR_TP_CD */
    public static final String SHIP_TO_ADDR_TP_CD = "SHIP_TO_ADDR_TP_CD";

    /** BOL_NUM */
    public static final String BOL_NUM = "BOL_NUM";

    /** PRO_NUM */
    public static final String PRO_NUM = "PRO_NUM";

    /** ACTL_SHIP_DT */
    public static final String ACTL_SHIP_DT = "ACTL_SHIP_DT";

    /** RCPO_POSN_NUM */
    public static final String RCPO_POSN_NUM = "RCPO_POSN_NUM";

    /** MACH_MDL_TP_CD */
    public static final String MACH_MDL_TP_CD = "MACH_MDL_TP_CD";

    /** SVC_DLR_CD */
    public static final String SVC_DLR_CD = "SVC_DLR_CD";

    /** INSTL_NM */
    public static final String INSTL_NM = "INSTL_NM";

    /** RTL_DIV_CD */
    public static final String RTL_DIV_CD = "RTL_DIV_CD";

    /** DLR_WH_LOC_CD */
    public static final String DLR_WH_LOC_CD = "DLR_WH_LOC_CD";

    /** SHIP_TO_LOC_NM */
    public static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    /** SHIP_TO_FIRST_LINE_ADDR */
    public static final String SHIP_TO_FIRST_LINE_ADDR = "SHIP_TO_FIRST_LINE_ADDR";

    /** SHIP_TO_CTY_ADDR */
    public static final String SHIP_TO_CTY_ADDR = "SHIP_TO_CTY_ADDR";

    /** SHIP_TO_ST_CD */
    public static final String SHIP_TO_ST_CD = "SHIP_TO_ST_CD";

    /** SHIP_TO_POST_CD */
    public static final String SHIP_TO_POST_CD = "SHIP_TO_POST_CD";

    /** CUST_ISS_PO_NUM */
    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** PDD_DT */
    public static final String PDD_DT = "PDD_DT";

    /** VND_CD */
    public static final String VND_CD = "VND_CD";

    /** SHIP_UNIT_PK */
    public static final String SHIP_UNIT_PK = "SHIP_UNIT_PK";

    /** SER_NUM */
    public static final String SER_NUM = "SER_NUM";

    /** IMPT_PROC_CD */
    public static final String IMPT_PROC_CD = "IMPT_PROC_CD";

    /** GLBL_CMPY_CD_RSI */
    public static final String GLBL_CMPY_CD_RSI = "GLBL_CMPY_CD_RSI";

    /** GLBL_CMPY_CD_RSS */
    public static final String GLBL_CMPY_CD_RSS = "GLBL_CMPY_CD_RSS";

    /** ROSS_SHIP_HDR_PK */
    public static final String ROSS_SHIP_HDR_PK = "ROSS_SHIP_HDR_PK";

    /** SO_CRAT_DT */
    public static final String SO_CRAT_DT = "SO_CRAT_DT";

    /** CPO_ORD_NUM */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** SVC_DLR_CUST_CD */
    public static final String SVC_DLR_CUST_CD = "SVC_DLR_CUST_CD";

    /** ETA_DT */
    public static final String ETA_DT = "ETA_DT";

    /** CARR_CD */
    public static final String CARR_CD = "CARR_CD";

    /** CRAT_RWS_PROC_CD */
    public static final String CRAT_RWS_PROC_CD = "CRAT_RWS_PROC_CD";

    /** INVTY_LOC_CD */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** RTL_WH_CD */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /** ROSS_SHIP_ITEM_PK */
    public static final String ROSS_SHIP_ITEM_PK = "ROSS_SHIP_ITEM_PK";

    /** PROD_MDL_TP_CD */
    public static final String PROD_MDL_TP_CD = "PROD_MDL_TP_CD";

    /** RWS_NUM */
    public static final String RWS_NUM = "RWS_NUM";

    /** PO_RCV_LINE_NUM */
    public static final String PO_RCV_LINE_NUM = "PO_RCV_LINE_NUM";

    /** PRNT_VND_CD */
    public static final String PRNT_VND_CD = "PRNT_VND_CD";

    /** DEAL_CCY_CD */
    public static final String DEAL_CCY_CD = "DEAL_CCY_CD";

    /** MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** PRCH_GRP_CD */
    public static final String PRCH_GRP_CD = "PRCH_GRP_CD";

    /** LINE_BIZ_TP_CD */
    public static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";

    /** BASE_PKG_UOM_CD */
    public static final String BASE_PKG_UOM_CD = "BASE_PKG_UOM_CD";

    /** ASL_DTL_PK */
    public static final String ASL_DTL_PK = "ASL_DTL_PK";

    /** V_VND_CD */
    public static final String V_VND_CD = "V_VND_CD";

    /** VAR_CHAR_CONST_ROSS_VND_CD */
    public static final String VAR_CHAR_CONST_ROSS_VND_CD = "ROSS_VND_CD";

    /** DB_PARAM_ROSS_VND_CD */
    public static final String DB_PARAM_ROSS_VND_CD = "rossVndCd";

    // QC#22275
    /** DB_PARAM BOL_NUM */
    public static final String DB_PARAM_BOL_NUM = "bolNum";

    /** DB_PARAM PRO_NUM */
    public static final String DB_PARAM_PRO_NUM = "proNum";
}
