/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB061001.constant;

/**
 * <pre>
 * Batch Program Constant Class for Ship Order Confirmation(PostGoodsIssue) from Choice
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2016   CITS            Y.IWASAKI       Create          
 * 06/26/2017   CITS            Y.IWASAKI       Update          QC#19371
 * 06/26/2017   CITS            Y.IWASAKI       Update          QC#19434
 * 09/11/2017   CITS            T.Tokutomi      Update          QC#20379
 * 07/31/2018   CITS            Y.Iwasaki       Update          QC#27459
 * 07/29/2019   CITS            T.Wada          Update          QC#52077
 * 08/27/2019   CITS            T.Wada          Update          QC#52509
 * 10/02/2019   CITS            K.Ogino         Update          QC#52894
 * 11/26/2019   Fujitsu         R.Nakamura      Update          QC#52804
 * 12/01/2020   CITS            M.Furugoori     Update          QC#57659
 *</pre>
 */
public class NLBB061001Constant {

    // *********************************************************
    // Constants: Basic
    // *********************************************************

    /** Business ID */
    public static final String BUSINESS_ID = "NLBB0610";

    /** Fetch size */
    public static final int FETCH_SIZE = 1000;

    /** Debug level for debug */
    public static final int CST_DEBUG_MSG_LVL = 1;

    /** Split size for address */
    public static final int ADDR_LENGTH = 60;

    // *********************************************************
    // Constants: Message ID
    // *********************************************************

    /** Message ID : ZZBM0009I */
    public static final String ZZBM0009I = "ZZBM0009I";

    /** Message ID : ZZM9000E The field of [@] is not input. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message ID : ZZM9004E The value which is not numerical was input to the field of [@]. */
    public static final String ZZM9004E = "ZZM9004E";

    /**
     * Message ID: NLGM0041E [@] does not exist. Table:[@], Search
     * Key:[@]
     */
    public static final String NLGM0041E = "NLGM0041E";

    /**
     * Message ID: NLGM0043E A code which does not exist in the Code
     * Table is set. Table:[@], Search Key:[@], Column:[@], Code: [@]
     */
    public static final String NLGM0043E = "NLGM0043E";

    /**
     * Message ID: NLGM0045E The record cannot be registered.
     * Registration Table Name: [@], Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    public static final String NLGM0045E = "NLGM0045E";

    /**
     * Message ID: NLGM0047E Warehouse code to be processed is not
     * set. Please check the WMS warehouse table. MW_REPL_TRGT_GRP_CD:
     * [@]
     */
    public static final String NLGM0047E = "NLGM0047E";

    /**
     * Message ID: NLGM0049E Parameter has not been set. [@] Parameter
     * has not been set.
     */
    public static final String NLGM0049E = "NLGM0049E";

    /** It failed to register an email. */
    public static final String NLEM0004E = "NLEM0004E";

    // Add Start 2019/11/26 QC#52804
    /** [@] does not exist. Please check error data : Order#:[@]. 
     * Interface Name:[@]. Transaction Id:[@]. Segment Id:[@]. 
     * */
    public static final String NLGM0089E = "NLGM0089E";
    // Add End 2019/11/26 QC#52804

    // *********************************************************
    // Constants: Message Parameter
    // *********************************************************

    /** Parameter Name: GLBL_CMPY_CD */
    public static final String PARAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Parameter Name: INTERFACE_ID */
    public static final String PARAM_NM_INTERFACE_ID = "Interface ID";

    /** Parameter Name : VAR_USER1 */
    public static final String PARAM_NM_VAR_USER1 = "XXX(VAR_USER1)";

    /** Parameter Name : VAR_USER2 */
    public static final String PARAM_NM_VAR_USER2 = "XXX(VAR_USER2)";

    /** Parameter Name : VAR_USER3 */
    public static final String PARAM_NM_VAR_USER3 = "XXX(VAR_USER3)";

    // *********************************************************
    // Constants: Format pattern
    // *********************************************************

    /** Datetime format : yyyyMMdd */
    public static final String FMT_YYYYMMDD = "yyyyMMdd";

    /** Datetime format : yyyyMMddHHmmss */
    public static final String FMT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /** Datetime format : yyyyMMddHHmmssSSS */
    public static final String FMT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    // *********************************************************
    // Constants: Table Name
    // *********************************************************

    /** DB Table NLBI1020_01 : */
    public static final String TBL_NLBI1020_01 = "NLBI1020_01";

    /** DB Table NLBI1020_02 : */
    public static final String TBL_NLBI1020_02 = "NLBI1020_02";

    /** DB Table WMS_INBD_TRX : */
    public static final String TBL_WMS_INBD_TRX = "WMS_INBD_TRX";

    /** Table name of WMS_INBD_SO_HDR */
    public static final String TBL_WMS_INBD_SO_HDR = "WMS_INBD_SO_HDR";

    /** Table name of WMS_INBD_SO_DTL */
    public static final String TBL_WMS_INBD_SO_DTL = "WMS_INBD_SO_DTL";

    /** Table name of WMS_INBD_SO_TEXT */
    public static final String TBL_WMS_INBD_SO_TEXT = "WMS_INBD_SO_TEXT";

    /** Table name of WMS_INBD_SO_SHIP_TO */
    public static final String TBL_WMS_INBD_SO_SHIP_TO = "WMS_INBD_SO_SHIP_TO";

    /** Table name of WMS_INBD_SO_CHRG_TO */
    public static final String TBL_WMS_INBD_SO_CHRG_TO = "WMS_INBD_SO_CHRG_TO";

    /** Table name of WMS_INBD_SO_BILL_TO */
    public static final String TBL_WMS_INBD_SO_BILL_TO = "WMS_INBD_SO_BILL_TO";

    /** DB Table TPL_LOC */
    public static final String TBL_TPL_LOC = "TPL_LOC";

    /** DB Table TECH_LOC */
    public static final String TBL_TECH_LOC = "TECH_LOC";

    /** DB Table RTL_SWH */
    public static final String TBL_RTL_SWH = "RTL_SWH";

    /** DB Table SCE_ORD_TP */
    public static final String TBL_SCE_ORD_TP = "SCE_ORD_TP";

    /** DB Table SHPG_ORD */
    public static final String TBL_SHPG_ORD = "SHPG_ORD";

    /** DB Table SHPG_ORD_DTL */
    public static final String TBL_SHPG_ORD_DTL = "SHPG_ORD_DTL";

    // *********************************************************
    // Constants: VAR_CHAR_CONST Name
    // *********************************************************

    /** Value for Look Up From VarcharConst */
    public static final String VAR_CHAR_CONST_NM_TARGET_ORDER = "NLBB0600_TARGET_ORDER"; /* CHOICE */

    /** Value for Look Up From VarcharConst */
    public static final String VAR_CHAR_CONST_NM_RTL_WH_CD_COLUMBUS = "NLBB0610_RTL_WH_CD_COLUMBUS"; /* 2P1 : COLUMBUS */

    /** */
    public static final String VAR_CHAR_CONST_NM_WMS_PACK_CD_SET_OWNER_CD_FLG = "WMS_PACK_CD_SET_OWNER_CD_FLG"; /* Acquiring mode for WMS_PACK_CD. Normally "Y" */

    /** QC#52894 */
    public static final String VAR_CHAR_CONST_NM_NLBB0610_SO_CONF_Z = "NLBB0610_SO_CONF_Z";

    /** QC#52894 */
    public static final String VAR_CHAR_CONST_NM_NLBB0610_SO_CONF_G = "NLBB0610_SO_CONF_G";

    // *********************************************************
    // Constants: Column Name
    // *********************************************************

    /** */
    public static final String COL_EZINTIME = "EZINTIME";
    
    /** Column name of INTERFACE_ID */
    public static final String COL_INTERFACE_ID = "INTERFACE_ID";

    /** Column name of TRANSACTION_ID */
    public static final String COL_TRANSACTION_ID = "TRANSACTION_ID";

    /** Column name of SEGMENT_ID */
    public static final String COL_SEGMENT_ID = "SEGMENT_ID";

    /** Column name of UNIT_ID */
    public static final String COL_UNIT_ID = "UNIT_ID";

    /** Column name of SEQ_NUMBER */
    public static final String COL_SEQ_NUMBER = "SEQ_NUMBER";

    /** Column name of WMS_REC_ID */
    public static final String COL_WMS_REC_ID = "WMS_REC_ID";

    /** Column name of TPL_FROM_PTNR_ID */
    public static final String COL_TPL_FROM_PTNR_ID = "TPL_FROM_PTNR_ID";

    /** Column name of TPL_TO_PTNR_ID */
    public static final String COL_TPL_TO_PTNR_ID = "TPL_TO_PTNR_ID";

    /** Column name of REQ_DT_TM_TS_TXT */
    public static final String COL_REQ_DT_TM_TS_TXT = "REQ_DT_TM_TS_TXT";

    /** Column name of OTBD_ORD_NUM */
    public static final String COL_OTBD_ORD_NUM = "OTBD_ORD_NUM";

    /** Column name of SO_NUM */
    public static final String COL_SO_NUM = "SO_NUM";

    /** Column name of SCE_ORD_TP_CD */
    public static final String COL_SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /** Column name of WMS_ITEM_DESC_TXT */
    public static final String COL_WMS_ITEM_DESC_TXT = "WMS_ITEM_DESC_TXT";

    /** Column name of OTBD_ORD_TP_CD */
    public static final String COL_OTBD_ORD_TP_CD = "OTBD_ORD_TP_CD";

    /** Column name of WMS_JOB_ID */
    public static final String COL_WMS_JOB_ID = "WMS_JOB_ID";

    /** Column name of TPL_EMP_ID */
    public static final String COL_TPL_EMP_ID = "TPL_EMP_ID";

    /** Column name of TECH_TOC_CD */
    public static final String COL_TECH_TOC_CD = "TECH_TOC_CD";

    /** Column name of TECH_LOC_CD */
    public static final String COL_TECH_LOC_CD = "TECH_LOC_CD";

    /** Column name of TPL_ACCT_TXT */
    public static final String COL_TPL_ACCT_TXT = "TPL_ACCT_TXT";

    /** Column name of TPL_LOC_TXT */
    public static final String COL_TPL_LOC_TXT = "TPL_LOC_TXT";

    /** Column name of TPL_LOC_TXT */
    public static final String COL_TPL_LOC_CD = "TPL_LOC_CD";

    /** */
    public static final String COL_WMS_ORG_HOST_ID = "WMS_ORG_HOST_ID";

    /** Column name of RTL_WH_CD */
    public static final String COL_RTL_WH_CD = "RTL_WH_CD";

    /** Column name of RTL_SWH_CD */
    public static final String COL_RTL_SWH_CD = "RTL_SWH_CD";

    /** Column name of INVTY_LOC_CD */
    public static final String COL_INVTY_LOC_CD = "INVTY_LOC_CD";

    /** Column name of SHIP_NM_ALL_TXT */
    public static final String COL_SHIP_NM_ALL_TXT = "SHIP_NM_ALL_TXT";

    /** Column name of SHIP_ADDR_ALL_TXT */
    public static final String COL_SHIP_ADDR_ALL_TXT = "SHIP_ADDR_ALL_TXT";

    /** Column name of SHIP_CTY_TXT */
    public static final String COL_SHIP_CTY_TXT = "SHIP_CTY_TXT";

    /** Column name of SHIP_ST_OR_PROV_TXT */
    public static final String COL_SHIP_ST_OR_PROV_TXT = "SHIP_ST_OR_PROV_TXT";

    /** Column name of SHIP_ZIP_OR_POST_CD_TXT */
    public static final String COL_SHIP_ZIP_OR_POST_CD_TXT = "SHIP_ZIP_OR_POST_CD_TXT";

    /** Column name of SHIP_CTRY_TXT */
    public static final String COL_SHIP_CTRY_TXT = "SHIP_CTRY_TXT";

    /** Column name of SHIP_CTAC_NM_TXT */
    public static final String COL_SHIP_CTAC_NM_TXT = "SHIP_CTAC_NM_TXT";

    /** Column name of SHIP_PHO_NUM_TXT */
    public static final String COL_SHIP_PHO_NUM_TXT = "SHIP_PHO_NUM_TXT";

    /** Column name of CARR_CD */
    public static final String COL_CARR_CD = "CARR_CD";

    /** Column name of SHPG_SVC_LVL_CD */
    public static final String COL_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** Column name of TPL_CARR_CD */
    public static final String COL_TPL_CARR_CD = "TPL_CARR_CD";

    /** Column name of TPL_SVC_LVL_CD */
    public static final String COL_TPL_SVC_LVL_CD = "TPL_SVC_LVL_CD";

    /** Column name of WMS_ORD_STS_CD */
    public static final String COL_WMS_ORD_STS_CD = "WMS_ORD_STS_CD";

    /** Column name of ORIG_ORD_QTY_TXT */
    public static final String COL_ORIG_ORD_QTY_TXT = "ORIG_ORD_QTY_TXT";

    /** Column name of QTY_ORD_TXT */
    public static final String COL_QTY_ORD_TXT = "QTY_ORD_TXT";

    /** Column name of ITEM_CD_TXT */
    public static final String COL_ITEM_CD_TXT = "ITEM_CD_TXT";

    /** Column name of GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column name of S80_ORD_TP_CD */
    public static final String COL_S80_ORD_TP_CD = "S80_ORD_TP_CD";

    /** Column name of MDSE_CD */
    public static final String COL_MDSE_CD = "MDSE_CD";

    /** Column name of SO_SLP_NUM */
    public static final String COL_SO_SLP_NUM = "SO_SLP_NUM";

    /** Column name of WH_OWNR_CD */
    public static final String COL_WH_OWNR_CD = "WH_OWNR_CD";

    /** Column name of RTL_SWH_DSBL_FLG */
    public static final String COL_RTL_SWH_DSBL_FLG = "RTL_SWH_DSBL_FLG";

    /** Column name of PRTY_LOC_FLG */
    public static final String COL_PRTY_LOC_FLG = "PRTY_LOC_FLG";

    /** Column name of INBD_OTBD_CD */
    public static final String COL_INBD_OTBD_CD = "INBD_OTBD_CD";

    /** */
    public static final String COL_INVTY_OWNR_CD = "INVTY_OWNR_CD";

    /** */
    public static final String COL_UNSHIP_QTY = "UNSHIP_QTY";

    /** */
    public static final String COL_FROM_STK_STS_CD = "FROM_STK_STS_CD";

    /** */
    public static final String COL_INTFC_PROC_STS_CD = "INTFC_PROC_STS_CD";

    /** */
    public static final String COL_INTFC_ERR_MSG_CD = "INTFC_ERR_MSG_CD";

    /** */
    public static final String COL_TPL_CARR_SVC_LVL_CD = "TPL_CARR_SVC_LVL_CD";

    /** Column name of WH_CD */
    public static final String COL_WH_CD = "WH_CD";

    /** Column name of SO_MSG_TP_CD */
    public static final String COL_SO_MSG_TP_CD = "SO_MSG_TP_CD";

    /** Column name of WH_SYS_TP_CD */
    public static final String COL_WH_SYS_TP_CD = "WH_SYS_TP_CD";

    /** Column name of RCH_REQ_TP_CD */
    public static final String COL_PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";

    /** Column name of PRCH_REQ_TP_NM */
    public static final String COL_PRCH_REQ_TP_NM = "PRCH_REQ_TP_NM";

    /** Column name of DELY_DAYS */
    public static final String COL_DELY_DAYS = "DELY_DAYS";

    /** Column name of DELY_TM */
    public static final String COL_DELY_TM = "DELY_TM";

    /** Column name of DS_COND_CONST_GRP_ID */
    public static final String COL_DS_COND_CONST_GRP_ID = "DS_COND_CONST_GRP_ID";

    /** Column name of DELY_HOURS */
    public static final String COL_DELY_HOURS = "DELY_HOURS";

    /** Column name of DELY_DATE */
    public static final String COL_DELY_DATE = "DELY_DATE";

    /** Column name of DELY_TIME */
    public static final String COL_DELY_TIME = "DELY_TIME";

    /** Bind name of SALES_DATE */
    public static final String BIND_SALES_DATE = "SALES_DATE";

    /** */
    public static final String BIND_SO_CUST_DATA_TP_CD_SHIP = "SO_CUST_DATA_TP_CD_SHIP";

    /** */
    public static final String BIND_SO_CUST_DATA_TP_CD_SELL = "SO_CUST_DATA_TP_CD_SELL";

    /** */
    public static final String BIND_SO_CUST_DATA_TP_CD_BILL = "SO_CUST_DATA_TP_CD_BILL";

    // START 2020/12/01 [QC#57659,ADD]
    /** Bind name of WH_OWNR_CD */
    public static final String BIND_WH_OWNR_CD = "WH_OWNR_CD";
    // END 2020/12/01 [QC#57659,ADD]

    // *********************************************************
    // Constants: Value
    // *********************************************************

    /** Value Blank */
    public static final String VAL_BLANK = " ";

    /** Value 0 */
    public static final String VAL_ZERO = "0";

    /** */
    public static final String VAL_WRK_TRX_ID = "000000000000000000000000000001";

    /** Value NPZC103001 */
    public static final String VAL_NPZC103001 = "NPZC103001";

    /** */
    public static final String VAL_PROC_STS_CD="0";

    /** Value WMS_ORD_STS_CD */
    public static final String VAL_WMS_ORD_STS_CD_A = "A";

    /** Value INBD_OTBD_CD(Outbound) */
    public static final String VAL_INBD_OTBD_CD_OTBD = "2";

    /** Value PRCH_REQ_INFO */
    public static final String VAL_PRCH_REQ_INFO = "prchReqInfo";

    /** */
    public static final String VAL_INTFC_PROC_STS_CD_SUCCESS = "1";

    /** */
    public static final String VAL_INTFC_PROC_STS_CD_ERROR = "9";

    /** */
    public static final String VAL_INTFC_TP_ID = "02";

    /** */
    public static final String VAL_INTFC_REC_TP_ID_HDR = "1";

    /** */
    public static final String VAL_INTFC_REC_TP_ID_DTL = "2";

    /** */
    public static final String VAL_INTFC_REC_TP_ID_SHIPTO = "3";

    /** */
    public static final String VAL_INTFC_REC_TP_ID_TEXT = "4";

    /** */
    public static final String VAL_INTFC_REC_TP_ID_CHRGTO = "5";

    /** */
    public static final String VAL_INTFC_REC_TP_ID_BILLTO = "6";

    /** */
    public static final String VAL_WMS_PRTY_CD = "5";

    /** */
    public static final String VAL_WMS_SO_STS_CD = "R";

    /** */
    public static final String VAL_STAGE_REC_STS_CD = "2";

    /** */
    public static final String VAL_MIN_DATE = "00000000";

    /** */
    public static final String VAL_MAX_DATE = "99999999";

    /** */
    public static final String VAL_WMS_PRINT_TP_CD = "B";

    /** */
    public static final String VAL_DS_COND_CONST_GRP_ID = "NPZC1170";

    /** */
    public static final String NLBM1373E = "NLBM1373E";

    /** */
    public static final String NLBM1374E = "NLBM1374E";

    // QC#52894 Add
    /** Bind name of Ship conf Status G-Tickets */
    public static final String BIND_SHIP_CONF_STS_G = "shipConfStsG";

    /** Bind name of Ship conf Status Z-Tickets */
    public static final String BIND_SHIP_CONF_STS_Z = "shipConfStsZ";

    /** WMS_JOB_ID Prefix G-Tickets */
    public static final String G_TICKETS = "G";

    /** WMS_JOB_ID Prefix Z-Tickets */
    public static final String Z_TICKETS = "Z";

    /** Bind Name WMS_JOB_ID Prefix G-Tickets */
    public static final String BIND_G_TICKETS = "gTickets";

    /** Bind Name WMS_JOB_ID Prefix Z-Tickets */
    public static final String BIND_Z_TICKETS = "zTickets";
    // QC#52894 End

    // Add Start 2019/11/26 QC#52804
    /** ]. Item#:[@ */
    public static final String VAL_CONNECT_MDSE_CD = "]. Item#:[";

    /** ]. Segment Id:[ */
    public static final String VAL_CONNECT_SEGMENT_ID = "]. Segment Id:[";
    // Add End 2019/11/26 QC#52804
}
