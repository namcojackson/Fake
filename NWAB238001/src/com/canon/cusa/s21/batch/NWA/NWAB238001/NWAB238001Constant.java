/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB238001;

/**
 * <pre>
 * NWAB238001 Customer Back Order Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ----------------------------------------------------------------------
 * 2013/10/07   Fujitsu         A.Suda          Create          MEX-IF013
 * 2013/11/22   Fujitsu         A.Suda          Update          Defect#3161
 * 2016/06/07   CITS            N.Akaishi       Update          V1.0
 * 09/04/2018   CITS            T.Tokutomi      Update          QC#26966
 * 04/02/2019   CITS            T.Tokutomi      Update          QC#30964
 * 04/11/2019   CITS            T.Tokutomi      Update          QC#31175
 * 2020/02/04   Fujitsu         K.Kato          Update          QC#55572
 *</pre>
 */
public class NWAB238001Constant {

    // -- Error Message Code --------------------
    /** [@] is mandatory. */
    public static final String MSG_ID_ZZZM9025E = "ZZZM9025E";

    /** Data failed @. [ Table Name = @, Return Code = @ ]. */
    public static final String MSG_ID_NWAM0221E = "NWAM0221E";

    /** Failed to register data. Table: [@]. */
    public static final String MSG_ID_NWAM0546E = "NWAM0546E";

    /** Error returned at API call. APIID[@] MSGID[@] MSGTXT[@]. */
    public static final String MSG_ID_NWAM0528E = "NWAM0528E";

    /** No data found. [Table Name : @, PKey : @]. */
    public static final String MSG_ID_NWAM0525E = "NWAM0525E";

    /** Data is duplicated. [@]. */
    public static final String MSG_ID_NWAM0551W = "NWAM0551W";

    // -- Variable Character Const Key --------------------
    /** Variable Character Const Key (Back Order Status) */
    public static final String VAR_CHAR_CONST_KEY_BO_STATUS = "NWAI2420_BO_STATUS";

    /** Variable Character Const Key (Reserved Back Order Status) */
    public static final String VAR_CHAR_CONST_KEY_RSVD_BO_STATUS = "NWAI2420_RSVD_BO_STATUS";

    /** Variable Character Const Key (Cinc Global WH Code) */
    public static final String VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD = "SCUBE_IF_CINC_GLBL_WH_CD";

    /** Variable Character Const Key (Cinc Vendor Code) */
    public static final String VAR_CHAR_CONST_KEY_CINC_VND_CD = "SCUBE_IF_CINC_VND_CD";

    /** Variable Character Const Key (Carr Code) */
    public static final String VAR_CHAR_CONST_KEY_CARR_CD = "SCUBE_IF_CARR_CD";

    /** Variable Character Const Key (Category Code REGULAR_ORDER) */
    public static final String VAR_CHAR_CONST_KEY_CATG_CD_A1 = "SCUBE_IF_CINC_GLBL_ORD_CATG_N";

    /**
     * Variable Character Const Key (Parts Charge indicator-Compensation)
     */
    public static final String VAR_CHAR_CONST_KEY_PRT_CHRG_IND_COMP = "SCUBE_IF_PRT_CHRG_IND_C";

    /**
     * Variable Character Const Key (Parts Charge indicator- NO
     * Compensation)
     */
    public static final String VAR_CHAR_CONST_KEY_PRT_CHRG_IND_NO_COMP = "SCUBE_IF_PRT_CHRG_IND_N";

    /** Variable Character Const Key (Parts Emergency Order - NO) */
    public static final String VAR_CHAR_CONST_KEY_PRT_EMER_ORD_IND_NO = "SCUBE_IF_PRT_EMER_ORD_IND_N";

    // QC#26966 Add.
    /** Variable Character Const Key (CINC_GLBL_WH_CD_CLMBUS) */
    public static final String VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD_CLMBUS = "CINC_GLBL_WH_CD_CLMBUS";

    // QC#26966 Add.
    /** Canon inc global warehouse code of CLUMBUS */
    public static final String CINC_GLBL_WH_CD_CLMBUS_DEFAULT = "CLMBS";

    //QC# 26966 Add.
    /** CINC_PO_GLBL_CATG_CD_AZ ="BZ" */
    public static final String CINC_PO_GLBL_CATG_CD_BZ = "BZ";

    // QC#30964 Add.
    /** Variable Character Const Key (SCUBE_EXCL_MDSE_CD_LIST) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST = "SCUBE_EXCL_MDSE_CD_LIST";

    // 2020/02/04 QC#55572 Add Start
    /** Variable Character Const Key (SCUBE_EXCL_SWH_CD_LIST) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_EXCL_SWH_CD_LIST = "SCUBE_EXCL_SWH_CD_LIST";
    // 2020/02/04 QC#55572 Add End

    /** Quantity Signature Plus(+) */
    public static final String QTY_SGN_TXT_PLUS = "+";

    /** Quantity Signature Negative(-) */
    public static final String QTY_SGN_TXT_NEGATIVE = "-";

    /** Sell To Cust Code Length 12 */
    public static final int SELL_TO_CUST_CD_LENGTH_12 = 12;

    /**
     * Variable Character Const Key (Parts Include Tech Inventory Cinc Flg)
     */
    public static final String VAR_CHAR_CONST_KEY_PRT_INCL_TECH_INVTY_CINC_FLG = "PRT_INCL_TECH_INVTY_CINC_FLG";

    /**
     * Variable Character Const Key (Parts Exclude Inventory Location Code)
     */
    public static final String VAR_CHAR_CONST_KEY_PRT_EXCL_INVTY_LOC_CD_CINC = "PRT_EXCL_INVTY_LOC_CD_CINC";

    /** Add Date 1 */
    public static final int ADD_DATE_1 = 1;

    /** Variable Character Const Key (Category Code WH TRANSFER) */
    public static final String VAR_CHAR_CONST_KEY_CATG_CD_ZZ = "SCUBE_IF_SHIP_TP_O";

    /** Transaction Type Stock Out */
    public static final String TRX_TP_STOCK_OUT = "StockOut";

    /** Transaction Type Return */
    public static final String TRX_TP_RETUEN = "Return";

    /** Transaction Type WHTransfer */
    public static final String TRX_TP_WHTRANSFER = "WHTransfer";

    /** Transaction Type Tech Transfer */
    public static final String TRX_TP_TECHTRANSFER = "Tech Transfer";

    /** Transaction Type Disposal */
    public static final String TRX_TP_DISPOSAL = "Disposal";

    /** Transaction Type Disposal */
    public static final String TRX_TP_ITEMCHANGE = "Item Change";

    /** Transaction Type Disposal */
    public static final String TRX_TP_WORKORDER_STOCK_OUT = "Work Order Stock Out";

    /** Transaction Type Disposal */
    public static final String TRX_TP_WORKORDER_STOCK_IN = "Work Order Stock In";

    /**
     * In Table [@], there is no data corresponding to [@]. Data error
     * [@].
     */
    public static final String MSG_ID_NWAM0312W = "NWAM0312W";

    /** @ is incorrect value. */
    public static final String MSG_ID_NWAM0300E = "NWAM0300E";

    /** Variable Character Const Key : SCUBE_IF_SHPG_METH_OTHER */
    public static final String VAR_CHAR_CONST_KEY_SHPG_METH_OTHER = "SCUBE_IF_SHPG_METH_OTHER";

    /** INDEX : 0 */
    public static final int IDX_0 = 0;

    /** INDEX : 1 */
    public static final int IDX_1 = 1;

    /** INDEX : 2 */
    public static final int IDX_2 = 2;

    /** INDEX : 3 */
    public static final int IDX_3 = 3;

    /** INDEX : 4 */
    public static final int IDX_4 = 4;

    /** INDEX : 5 */
    public static final int IDX_5 = 5;

    /** INDEX : 6 */
    public static final int IDX_6 = 6;

    /** INDEX : 7 */
    public static final int IDX_7 = 7;

    /** INDEX : 8 */
    public static final int IDX_8 = 8;

    /** INDEX : 9 */
    public static final int IDX_9 = 9;

    /** INDEX : 10 */
    public static final int IDX_10 = 10;

    /** EFF_FROM_DT_DEFALUT */
    public static final String EFF_FROM_DT_DEFALUT = "99991231";

    /** NUM:1 */
    public static final int NUM_ONE = 1;

    /** VAL(SHPG_PLN.TRX_LINE_SUB_NUM) : "001" */
    public static final String VAL_001 = "001";

    // -- db table --------------------
    /** db table : INTFC_BO_WRK. */
    public static final String INTFC_BO_WRK = "INTFC_BO_WRK";

    /** db table : VAR_CHAR_CONST. */
    public static final String VAR_CHAR_CONST = "VAR_CHAR_CONST";

    /** db table : TRD_PTNR_SHPG_X_REF. */
    public static final String TRD_PTNR_SHPG_X_REF = "TRD_PTNR_SHPG_X_REF";

    // -- db column --------------------
    /** db column : GLBL_CMPY_CD. */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** db column : CINC_GLBL_CMPY_CATG_CD. */
    public static final String CINC_GLBL_CMPY_CATG_CD = "CINC_GLBL_CMPY_CATG_CD";

    /** db column : SELL_TO_CUST_CD. */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** db column : MDSE_CD. */
    public static final String MDSE_CD = "MDSE_CD";

    /** db column : RTL_WH_CD. */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /** db column : WH_OWNR_CD. */
    public static final String WH_OWNR_CD = "WH_OWNR_CD";

    /** db column : INVTY_LOC_TP_CD. */
    public static final String INVTY_LOC_TP_CD = "INVTY_LOC_TP_CD";

    /** db column : CPO_ORD_TS. */
    public static final String CPO_ORD_TS = "CPO_ORD_TS";

    /** db column : INTFC_CRAT_DT. */
    public static final String INTFC_CRAT_DT = "INTFC_CRAT_DT";

    /** db column : EXPD_SHIP_DT. */
    public static final String EXPD_SHIP_DT = "EXPD_SHIP_DT";

    /** db column : ORD_NUM. */
    public static final String ORD_NUM = "ORD_NUM";

    /** db column : SHPG_SVC_LVL_CD. */
    public static final String SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** db column : CARR_CD. */
    public static final String CARR_CD = "CARR_CD";

    /** db column : TRD_PTNR_SHPG_METH_CD. */
    public static final String TRD_PTNR_SHPG_METH_CD = "TRD_PTNR_SHPG_METH_CD";

    /** db column : CUSA_VND_CD. */
    public static final String CUSA_VND_CD = "CUSA_VND_CD";

    /** db column : ORIG_GOODS_MDSE_CD. */
    public static final String ORIG_GOODS_MDSE_CD = "ORIG_GOODS_MDSE_CD";

    /** db column : FNSH_GOODS_MDSE_CD. */
    public static final String FNSH_GOODS_MDSE_CD = "FNSH_GOODS_MDSE_CD";

    /** db column : WRK_ORD_DT. */
    public static final String WRK_ORD_DT = "WRK_ORD_DT";

    /** db column : FROM_RTL_WH_CD. */
    public static final String FROM_RTL_WH_CD = "FROM_RTL_WH_CD";

    /** db column : FROM_WH_OWNR_CD. */
    public static final String FROM_WH_OWNR_CD = "FROM_WH_OWNR_CD";

    /** db column : FROM_INVTY_LOC_TP_CD. */
    public static final String FROM_INVTY_LOC_TP_CD = "FROM_INVTY_LOC_TP_CD";

    /** db column : TO_RTL_WH_CD. */
    public static final String TO_RTL_WH_CD = "TO_RTL_WH_CD";

    /** db column : TO_WH_OWNR_CD. */
    public static final String TO_WH_OWNR_CD = "TO_WH_OWNR_CD";

    /** db column : TO_INVTY_LOC_TP_CD. */
    public static final String TO_INVTY_LOC_TP_CD = "TO_INVTY_LOC_TP_CD";

    /** db column : ORD_QTY. */
    public static final String ORD_QTY = "ORD_QTY";

    /** db column : ORIG_ORD_QTY. */
    public static final String ORIG_ORD_QTY = "ORIG_ORD_QTY";

    /** db column : ORIG_BAL_QTY. */
    public static final String ORIG_BAL_QTY = "ORIG_BAL_QTY";

    /** db column : TOT_ORD_QTY. */
    public static final String TOT_ORD_QTY = "TOT_ORD_QTY";

    /** db column : TOT_BAL_QTY. */
    public static final String TOT_BAL_QTY = "TOT_BAL_QTY";

    /** db column : RQST_RCV_DT. */
    public static final String RQST_RCV_DT = "RQST_RCV_DT";

    /** db column : RQST_PICK_UP_DT. */
    public static final String RQST_PICK_UP_DT = "RQST_PICK_UP_DT";

    /** db column : ORIG_RTRN_QTY. */
    public static final String ORIG_RTRN_QTY = "ORIG_RTRN_QTY";

    /** db column : TOT_RTRN_QTY. */
    public static final String TOT_RTRN_QTY = "TOT_RTRN_QTY";

    /** db column : RSD_DT. */
    public static final String RSD_DT = "RSD_DT";

    /** db column : CPO_ORD_DT. */
    public static final String CPO_ORD_DT = "CPO_ORD_DT";

    /** db column : BO_QTY. */
    public static final String BO_QTY = "BO_QTY";

    /** db column : RSVD_QTY. */
    public static final String RSVD_QTY = "RSVD_QTY";

    /** db column : ORIG_BO_ORD_QTY. */
    public static final String ORIG_BO_ORD_QTY = "ORIG_BO_ORD_QTY";

    /** db column : TOT_BO_ORD_QTY. */
    public static final String TOT_BO_ORD_QTY = "TOT_BO_ORD_QTY";

    /** db column : ORIG_RSVD_ORD_QTY. */
    public static final String ORIG_RSVD_ORD_QTY = "ORIG_RSVD_ORD_QTY";

    /** db column : TOT_RSVD_ORD_QTY. */
    public static final String TOT_RSVD_ORD_QTY = "TOT_RSVD_ORD_QTY";

    /** db column : CINC_GLBL_ORD_CATG_CD. */
    public static final String CINC_GLBL_ORD_CATG_CD = "CINC_GLBL_ORD_CATG_CD";

    /** db column : VND_CD. */
    public static final String VND_CD = "VND_CD";

    /** db column : BO_ORD_QTY. */
    public static final String BO_ORD_QTY = "BO_ORD_QTY";

    /** db column : RSVD_BO_ORD_QTY. */
    public static final String RSVD_BO_ORD_QTY = "RSVD_BO_ORD_QTY";

    // QC#31175 Add.
    /** db column : DS_WRK_ORD_TP_CD. */
    public static final String DS_WRK_ORD_TP_CD = "DS_WRK_ORD_TP_CD";

    /** Asterisk:* */
    public static final String ASTERISK = "*";

    /** DS_COND_CONST CLASS ID : SCUBE_IF_CUSA_VND_CD  */
    public static final String DS_COND_CONST_KEY_SCUBE_IF_CUSA_VND_CD = "SCUBE_IF_CUSA_VND_CD";

    /** DS_COND_CONST CLASS ID : SCUBE_WHT_DS_ORD_TP  */
    public static final String DS_COND_CONST_KEY_SCUBE_WHT_DS_ORD_TP = "SCUBE_WHT_DS_ORD_TP";

    /** VAR_CHAR_CONST KEY : SCUBE_VND_SYS_TP_CD  */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_VND_SYS_TP_CD = "SCUBE_VND_SYS_TP_CD";

    /** DateFormat : yyyyMMddHHmmSSsss */
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmSSss";

    // -- bind parameter --------------------
    /**  */
    public static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /**  */
    public static final String BIND_BATCH_PROC_DT = "batchProcDt";

    /**  */
    public static final String BIND_INTFC_CRAT_DT = "intfcCratDt";

    /**  */
    public static final String BIND_PRT_EXCL_INVTY_LOC_CD_CINC_LIST = "prtExclInvtyLocCdCincList";

    /**  */
    public static final String BIND_PRT_INCL_TECH_INVTY_CINC_FLG = "prtInclTechInvtyCincFlg";

    /**  */
    public static final String BIND_EFF_FROM_DT_DEFAULT = "effFromDtDefalut";

    /**  */
    public static final String BIND_IDX_1 = "IDX_1";

    /**  */
    public static final String BIND_CPO_DTL_CANC_FLG_N = "cpoDtlCancFlgN";

    /**  */
    public static final String BIND_VND_SYS_TP_CD_LIST = "vndSysTpCdList";

    /**  */
    public static final String BIND_TECHNICIAN = "technician";

    /**  */
    public static final String BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD = "dsCondConstGrpIdCusaVndCd";

    /**  */
    public static final String BIND_CMPY_INVTY_FLG = "cmpyInvtyFlg";

    /**  */
    public static final String BIND_INVTY_CTRL_FLG = "invtyCtrlFlg";

    /**  */
    public static final String BIND_TRD_PTNR_SHPG_METH_CD_OTHER = "trdPtnrShpgMethCdOther";

    /**  */
    public static final String BIND_DS_COND_CONST_GRP_ID_WHT_DS_ORD_TP = "dsCondConstGrpIdWhtDsOrdTp";

    /**  */
    public static final String BIND_BO_STS_CD_LIST = "boStsCdList";

    /**  */
    public static final String BIND_BO_RSVD_STS_CD_LIST = "boRsvdStsCdList";

    /**  */
    public static final String BIND_ALL_BO_STS_CD_LIST = "allboStsCdList";

    /**  */
    public static final String BIND_CINC_GLBL_WH_CD = "cincGlblWhCd";

    /**  */
    public static final String BIND_INTFC_CPO_ORD_DT = "intfcCpoOrdDt";

    /**  */
    public static final String BIND_INTFC_CPO_ORD_NUM = "intfcCpoOrdNum";

    /**  */
    public static final String BIND_INTFC_PRT_MDSE_CD = "intfcPrtMdseCd";

    /**  */
    public static final String BIND_PRT_SIZE_TXT = "prtSizeTxt";

    /**  */
    public static final String BIND_GLBL_SHPG_METH_CD = "glblShpgMethCd";

    /**  */
    public static final String BIND_CPO_ORD_NUM = "cpoOrdNum";

    /**  */
    public static final String BIND_MDSE_CD = "mdseCd";

    /**  */
    public static final String BIND_SHPG_SVC_LVL_CD = "shpgSvcLvlCd";

    /**  */
    public static final String BIND_ORD_LINE_STS_LIST = "ordLineStsList";

    /**  */
    public static final String BIND_SUBMIT_FLG  = "submitFlg";

    /**  */
    public static final String BIND_RTL_WH_CD  = "rtlWhCd";

    /**  */
    public static final String BIND_WH_OWNR_CD  = "whOwnrCd";

    /**  */
    public static final String BIND_LOC_TP_CD  = "locTpCd";

    /**  */
    public static final String BIND_FROM_RTL_WH_CD  = "fromRtlWhCd";

    /**  */
    public static final String BIND_FROM_WH_OWNR_CD  = "fromWhOwnrCd";

    /**  */
    public static final String BIND_FROM_LOC_TP_CD  = "fromLocTpCd";

    /**  */
    public static final String BIND_TO_RTL_WH_CD  = "toRtlWhCd";

    /**  */
    public static final String BIND_TO_WH_OWNR_CD  = "toWhOwnrCd";

    /**  */
    public static final String BIND_TO_LOC_TP_CD  = "toLocTpCd";

    /**  */
    public static final String BIND_SHPG_SVC_LCL_CD  = "shpgSvcLclCd";

    /**  */
    public static final String BIND_VND_CD  = "vndCd";

    /**  */
    public static final String BIND_CARR_CD  = "carrCd";

    /**  */
    public static final String BIND_FRT_COND_CD  = "frtCondCd";

    /**  */
    public static final String BIND_INVTY_ORD_DTL_STS_LIST  = "invtyOrdDtlStsList";

    /**  */
    public static final String BIND_INVTY_ORD_DTL_STS_BO_LIST  = "invtyOrdDtlStsBoList";

    /**  */
    public static final String BIND_INVTY_ORD_DTL_STS_RSVD_BO_LIST  = "invtyOrdDtlStsRsvdBoList";

    /**  */
    public static final String BIND_INVTY_ORD_TP_LIST  = "invtyOrdTpList";

    /**  */
    public static final String BIND_INVTY_ORD_TP_ITM_CHG  = "invtyOrdTpItmChg";

    /**  */
    public static final String BIND_INVTY_ORD_TP_DC_TRANSFER  = "invtyOrdTpDcTranfer";

    /**  */
    public static final String BIND_INVTY_ORD_STS_APPROVED  = "invtyOrdStsApproved";

    /**  */
    public static final String BIND_SHPG_PLN_N  = "shpgFlgN";

    /**  */
    public static final String BIND_TRX_LINE_SUB_NUM  = "trxLineSubNum";

    /**  */
    public static final String BIND_WORK_ORDER_STS_LIST  = "workOrderStsList";

    /** Constant value RTRN_LINE_STS_CD */
    public static final String BIND_RTRN_LINE_STS_CD = "rtrnLineStsCd";

    /** Constant value RTRN_LINE_STS_CD */
    public static final String BIND_SCUBE_EXCL_SWH_CD_LIST = "scubeExclSwhCdList";

    // -- statement id --------------------
    /**  */
    public static final String STMT_GET_BO_INFO = "getBoInfo";

    /**  */
    public static final String STMT_GET_REMOVE_INTFC_BO_INFO = "getRemoveIntfcBoInfo";

    /**  */
    public static final String STMT_GET_RETURN_INFO = "getReturnInfo";

    /**  */
    public static final String STMT_GET_DISPOSAL_INFO = "getDisposalInfo";

    /**  */
    public static final String STMT_GET_ITEM_CHANGE_INFO = "getItemChangeInfo";

    /**  */
    public static final String STMT_GET_WORK_ORDER_STOCK_OUT_INFO = "getWorkOrderStockOutInfo";

    /**  */
    public static final String STMT_GET_WORK_ORDER_STOCK_IN_INFO = "getWorkOrderStockInInfo";

    /**  */
    public static final String STMT_GET_WH_TRANSFER_INFO = "getWHTransferInfo";

    /**  */
    public static final String STMT_GET_TECH_TRANSFER_INFO = "getTechTransferInfo";

    /**  */
    public static final String STMT_GET_INTFC_BO_WRK = "getIntfcBoWrk";

    /**  */
    public static final String STMT_EXPD_SHIP_DT_AND_PRT_CHRG_IND = "getExpdShipDtAndPrtChrgInd";

    /**  */
    public static final String STMT_GET_DUPLICATION_DATA = "getDuplicationData";

    /**  */
    public static final String STMT_CINC_GLBL_WH_CD = "getCincGlblWhCd";

    /**  */
    public static final String STMT_CINC_GLBL_CATG_CD = "getCincGlblCatgCd";

    /**  */
    public static final String STMT_TRD_PTNR_SHPG_METH_CD = "getTrdPtnrShpgMethCd";
}
