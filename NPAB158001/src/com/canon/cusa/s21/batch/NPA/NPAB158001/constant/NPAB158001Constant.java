/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB158001.constant;

/**
 * <pre>
 * NPAB158001:Create PR IF from CPO
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   CITS            T.Kikuhara      Create          N/A
 * 2016/03/25   CSAI            K.Lee           Update          QC#6016
 * 2016/11/09   CITS            K.Ogino         Update          QC#15749,QC#15768
 * 01/25/2017   CITS            K.Ogino         Update          QC#11314
 * 08/08/2017   CITS            S.Endo          Update          Sol#035(QC#18108)
 * 10/17/2017   CITS            S.Katsuma       Update          Sol#454
 * 07/31/2017   CITS            K.Ogino         Update          QC#27535
 * 11/19/2018   CITS            K.Ogino         Update          QC#29255
 *</pre>
 */
public class NPAB158001Constant {

    /** . */
    public static final String SET_LINE_SUB_NUM = "000";

    /** . */
    public static final String SET_REF_LINE_SUB_NUM = "001";

    /** . */
    public static final String SETMDSE = "1";

    /** . */
    public static final String CHILD = "2";

    /** . */
    public static final String REGULAR = "3";

    /** . */
    public static final String ORDER_ENTRY = "22";

    /** . */
    public static final String DROP_SHIP = "DS";

    /** . */
    public static final String WARE_HOUSE = "WH";

    /** . */
    public static final int MAX_LEN_CTAC_PSN_NM = 90;

    /** . */
    public static final String NPAM0002E = "NPAM0002E";

    /** . */
    public static final String NPAM0078E = "NPAM0078E";

    /** . */
    public static final String NPAM1172E = "NPAM1172E";

    /** . */
    public static final String RTE_STS_CD = "RTE_STS_CD";

    /** . */
    public static final String SHPG_STS_CD = "SHPG_STS_CD";

    /** . */
    public static final String ORD_LINE_SRC_CATG_CD = "ORD_LINE_SRC_CATG_CD";

    /** . */
    public static final String FLGONY = "flgony";

    /** . */
    public static final String FLGOFFN = "flgoffn";

    /** . */
    public static final String ORDER_TYPE = "ORDER_TYPE";

    /** . */
    public static final String ORDER_TYPE_DS = "ORDER_TYPE_DS";

    /** . */
    public static final String ORDER_TYPE_WH = "ORDER_TYPE_WH";

    /** . */
    public static final String TRX_HDR_NUM = "TRX_HDR_NUM";

    /** . */
    public static final String TRX_LINE_NUM = "TRX_LINE_NUM";

    /** . */
    public static final String TRX_LINE_SUB_NUM = "TRX_LINE_SUB_NUM";

    /** . */
    public static final String TRX_REF_LINE_SUB_NUM = "TRX_REF_LINE_SUB_NUM";

    /** . */
    public static final String GET_ORDER_INFO = "getOrderInfo";

    /** . */
    public static final String GET_SET_MDSE_ORDER_INFO = "getSetMdseOrderInfo";

    /** . */
    public static final String SUBMIT_FOR_APPROVAL = "SUBMIT_FOR_APPROVAL";

    /** . */
    public static final String ENTERED = "entered";

    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** . */
    public static final String SALES_DATE = "SALES_DATE";

    /** . */
    public static final String PR_CRAT_SYSTEM_USER = "PR_CRAT_SYSTEM_USER";

    /** . */
    public static final String DS_PO_SCHD_REL_DAYS = "DS_PO_SCHD_REL_DAYS";

    /** . */
    public static final String PRCH_REQ_INTFC = "PRCH_REQ_INTFC";

    /** . */
    public static final String PRNT_CMPY_SET_MDSE_FLG = "PRNT_CMPY_SET_MDSE_FLG";

    /** . */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** . */
    public static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** . */
    public static final String CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";

    /** . */
    public static final String VND_DROP_SHIP_FLG = "VND_DROP_SHIP_FLG";

    /** . */
    public static final String PRCH_REQ_APVL_STS_CD = "PRCH_REQ_APVL_STS_CD";

    /** . */
    public static final String PRCH_REQ_CRAT_BY_PSN_CD = "PRCH_REQ_CRAT_BY_PSN_CD";

    /** . */
    public static final String PRCH_REQ_CRAT_BY_NM = "PRCH_REQ_CRAT_BY_NM";

    /** . */
    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** . */
    public static final String CUST_ISS_PO_DT = "CUST_ISS_PO_DT";

    /** . */
    public static final String CPO_ORD_TP_CD = "CPO_ORD_TP_CD";

    /** . */
    public static final String CTAC_PSN_NM = "CTAC_PSN_NM";

    // START 2017/10/16 S.Katsuma [Sol#454,ADD]
    /** . */
//    public static final String CTAC_PSN_FIRST_NM = "CTAC_PSN_FIRST_NM";

    /** . */
//    public static final String CTAC_PSN_MID_NM = "CTAC_PSN_MID_NM";

    /** . */
//    public static final String CTAC_PSN_LAST_NM = "CTAC_PSN_LAST_NM";

    /** . */
    public static final String SELL_TO_FIRST_REF_CMNT_TXT = "SELL_TO_FIRST_REF_CMNT_TXT";
    // END 2017/10/16 S.Katsuma [Sol#454,ADD]

    /** . */
    public static final String CTAC_PSN_TEL_NUM = "CTAC_PSN_TEL_NUM";

    /** . */
    public static final String ADMIN_PSN_CD = "ADMIN_PSN_CD";

    /** . */
    public static final String PO_SCHD_REL_DT = "PO_SCHD_REL_DT";

    /** . */
    public static final String VND_INVTY_LOC_CD = "VND_INVTY_LOC_CD";

    /** . */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** . */
    public static final String DEST_INVTY_LOC_CD = "DEST_INVTY_LOC_CD";

    /** . */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /** . */
    public static final String DEST_RTL_WH_CD = "DEST_RTL_WH_CD";

    /** . */
    public static final String RTL_SWH_CD = "RTL_SWH_CD";

    /** . */
    public static final String DEST_RTL_SWH_CD = "DEST_RTL_SWH_CD";

    /** . */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** . */
    public static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    /** . */
    public static final String SHIP_TO_ADDL_LOC_NM = "SHIP_TO_ADDL_LOC_NM";

    /** . */
    public static final String SHIP_TO_FIRST_LINE_ADDR = "SHIP_TO_FIRST_LINE_ADDR";

    /** . */
    public static final String SHIP_TO_SCD_LINE_ADDR = "SHIP_TO_SCD_LINE_ADDR";

    /** . */
    public static final String SHIP_TO_THIRD_LINE_ADDR = "SHIP_TO_THIRD_LINE_ADDR";

    /** . */
    public static final String SHIP_TO_FRTH_LINE_ADDR = "SHIP_TO_FRTH_LINE_ADDR";

    /** . */
    public static final String SHIP_TO_FIRST_REF_CMNT_TXT = "SHIP_TO_FIRST_REF_CMNT_TXT";

    /** . */
    public static final String SHIP_TO_SCD_REF_CMNT_TXT = "SHIP_TO_SCD_REF_CMNT_TXT";

    /** . */
    public static final String SHIP_TO_CTY_ADDR = "SHIP_TO_CTY_ADDR";

    /** . */
    public static final String SHIP_TO_ST_CD = "SHIP_TO_ST_CD";

    /** . */
    public static final String SHIP_TO_PROV_ADDR = "SHIP_TO_PROV_ADDR";

    /** . */
    public static final String SHIP_TO_CNTY_NM = "SHIP_TO_CNTY_NM";

    /** . */
    public static final String SHIP_TO_POST_CD = "SHIP_TO_POST_CD";

    /** . */
    public static final String SHIP_TO_CTRY_CD = "SHIP_TO_CTRY_CD";

    /** . */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** . */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** . */
    public static final String RQST_RCV_DT = "RQST_RCV_DT";

    /** . */
    public static final String RQST_RCV_TM = "RQST_RCV_TM";

    /** . */
    public static final String PO_QLFY_CD = "PO_QLFY_CD";

    /** . */
    public static final String PRCH_GRP_CD = "PRCH_GRP_CD";

    /** . */
    public static final String MDSE_CD = "MDSE_CD";

    /** . */
    public static final String SET_MDSE_CD = "SET_MDSE_CD";

    /** . */
    public static final String PRCH_REQ_QTY = "PRCH_REQ_QTY";

    /** . */
    public static final String ORD_QTY = "ORD_QTY";

    /** . */
    public static final String CUST_UOM_CD = "CUST_UOM_CD";

    /** . */
    public static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";

    /** . */
    public static final String REQ_FRT_CHRG_TO_CD = "REQ_FRT_CHRG_TO_CD";

    /** . */
    public static final String REQ_FRT_CHRG_METH_CD = "REQ_FRT_CHRG_METH_CD";

    /** . */
    public static final String FRT_COND_CD = "FRT_COND_CD";

    /** . */
    public static final String REQ_SHPG_SVC_LVL_CD = "REQ_SHPG_SVC_LVL_CD";

    /** . */
    public static final String RQST_CARR_CD = "RQST_CARR_CD";

    /** . */
    public static final String CARR_ACCT_NUM = "CARR_ACCT_NUM";

    /** . */
    public static final String DELY_ADDL_CMNT_TXT = "DELY_ADDL_CMNT_TXT";

    //08/08/2017 CITS S.Endo Mod Sol#035(QC#18108) START
    /** . */
    public static final String SPCL_HDLG_TP_DESC_TXT = "SPCL_HDLG_TP_DESC_TXT";

    /** . */
    public static final int MAX_LENGTH_DELY_ADDL_CMNT_TXT = 240;
    //08/08/2017 CITS S.Endo Mod Sol#035(QC#18108) END
    /** . */
    public static final String SHPG_PLN_NUM = "SHPG_PLN_NUM";

    /** . */
    public static final String PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** . */
    public static final String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** . */
    public static final String PRCH_REQ_REC_TP_CD = "PRCH_REQ_REC_TP_CD";

    /** . */
    public static final String PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";

    /** . */
    public static final String PRCH_REQ_LINE_TP_CD = "PRCH_REQ_LINE_TP_CD";

    /** . */
    public static final String PRCH_REQ_CRAT_DT = "PRCH_REQ_CRAT_DT";

    /** . */
    public static final String PRCH_REQ_CRAT_TM = "PRCH_REQ_CRAT_TM";

    /** . */
    public static final String RQST_TECH_TOC_CD = "RQST_TECH_TOC_CD";

    /** . */
    public static final String PRCH_REQ_SRC_TP_CD = "PRCH_REQ_SRC_TP_CD";

    /** . */
    public static final String TRX_REF_NUM = "TRX_REF_NUM";

    /** . */
    public static final String TRX_REF_LINE_NUM = "TRX_REF_LINE_NUM";

    /** . */
    public static final String FSR_NUM = "FSR_NUM";

    /** . */
    public static final String SVC_TASK_NUM = "SVC_TASK_NUM";

    /** . */
    public static final String SVC_MACH_SER_NUM = "SVC_MACH_SER_NUM";

    /** . */
    public static final String SER_NUM = "SER_NUM";

    /** . */
    public static final String SHIP_FROM_SO_NUM = "SHIP_FROM_SO_NUM";

    /** . */
    public static final String PROCR_TP_CD = "PROCR_TP_CD";

    /** . */
    public static final String SRC_INVTY_LOC_CD = "SRC_INVTY_LOC_CD";

    /** . */
    public static final String SRC_RTL_WH_CD = "SRC_RTL_WH_CD";

    /** . */
    public static final String SRC_RTL_SWH_CD = "SRC_RTL_SWH_CD";

    /** . */
    public static final String PRNT_VND_CD = "PRNT_VND_CD";

    /** . */
    public static final String VND_CD = "VND_CD";

    /** . */
    public static final String CPO_RDD_DT = "CPO_RDD_DT";

    /** . */
    public static final String RDD_DT = "RDD_DT";

    /** . */
    public static final String PRCH_REQ_CMNT_TXT = "PRCH_REQ_CMNT_TXT";

    /** . */
    public static final String SPCL_INSTN_CMNT_TXT = "SPCL_INSTN_CMNT_TXT";

    /** . */
    public static final String RCV_ADDL_CMNT_TXT = "RCV_ADDL_CMNT_TXT";

    // START 2017/10/16 S.Katsuma [Sol#454,ADD]
    /** . */
//    public static final String SHIPTO = "SHIPTO";

    /** . */
//    public static final String CTAC_PSN_TP_CD = "CTAC_PSN_TP_CD";

    /** . */
    public static final String CTAC_PSN_TP_DELIV_INS = "CTAC_PSN_TP_DELIV_INS";

    /** . */
    public static final String CTAC_PSN_TP_ORD_CTAC = "CTAC_PSN_TP_ORD_CTAC";
    // END 2017/10/16 S.Katsuma [Sol#454,ADD]

    /** . */
    public static final String MDSE_TP_CD = "MDSE_TP_CD";

    /** . */
    public static final String ORD_LINE_CTX_TP_CD = "ORD_LINE_CTX_TP_CD";

    /** . */
    public static final String NPAB1580_ROSS_ORD_CTX_TP = "NPAB1580_ROSS_ORD_CTX_TP";

    /** . */
    public static final String DS_ORD_LINE_CATG_CD = "DS_ORD_LINE_CATG_CD";

    /** */
    public static final String ITT_DROP_SHIP_FLG = "ITT_DROP_SHIP_FLG";

    /** . */
    public static final String GET_DS_ORDER_LINE_CATEGORY_TYPE = "getDsOrderLineCategoryType";

    /** . */
    public static final String NPAB1580_PR_TP_ROSS = "NPAB1580_PR_TP_ROSS";

    /** . */
    public static final String NPAB1580_PR_TP_STANDARD = "NPAB1580_PR_TP_STANDARD";

    /** . */
    public static final String PO_LINE_STS_CANCEL = "poLineStsCdCancel";

    /** . */
    public static final String PO_LINE_STS_CLOSE = "poLineStsCdClose";

    /** VAR_CHAR_CONST_ROSS_VND_CD */
    public static final String VAR_CHAR_CONST_ROSS_VND_CD = "ROSS_VND_CD";

    /** DB_PARAM_ROSS_VND_Cd */
    public static final String DB_PARAM_ROSS_VND_CD = "rossVndCd";

    /** DB_PARAM_ROSS_VND_Cd */
    public static final String DB_PARAM_SLS_DT = "salesDate";

    /** DB_PARAM_ROSS_VND_Cd */
    public static final String DB_PARAM_RGTN_STS_CD = "rgtnStsCd";

    /**
     * The Constant [@] was not found on Constant table.
     */
    public static final String NPAM1010E = "NPAM1010E";

    /** . */
    public static final String GET_PRNT_VND_CD = "getPrntVndCd";

    /** Add QC#27535. VAR_CHAR_CONST : MANUAL_DROPSHIP_WAREHOUSE_CD */
    public static final String MANUAL_DROPSHIP_WAREHOUSE_CD = "MANUAL_DROPSHIP_WAREHOUSE_CD";

    /** Add QC#29255. NPAB1580_ORD_LINE_DPLY_STS_CD */
    public static final String NPAB1580_ORD_LINE_DPLY_STS_CD = "NPAB1580_ORD_LINE_DPLY_STS_CD";
}
