/*
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC119001.constant;


/**
 * <pre>
 * NPZC119001Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/22/2015   Hitachi         T.Harada        Create
 * 03/30/2016   Hitachi         T.Iwamoto       Update          QC#4287
 * 09/23/2016   CSAI            K.Lee           Update          QC#13310
 * 2016/11/10   Hitachi         K.Kojima        Update          QC#15490
 * 2016/11/25   Hitachi         T.Tomita        Update          QC#16165
 * 2016/12/05   Hitachi         T.Tomita        Update          QC#16079
 * 2018/03/09   CITS            T.Tokutomi      Update          QC#21913
 * </pre>
 */
public final class NPZC119001Constant {
    /*****************************************************************
     * Message ID
     ****************************************************************/
    /** Input parameter "Global Company Code" is a mandatory field. */
    public static final String NPZM0179E = "NPZM0179E";

    /** Input parameter "Sales Date" is a mandatory field. */
    public static final String NPZM0180E = "NPZM0180E";

    /** Input parameter "Purchase Requisition Status Name" is a mandatory field. */
    public static final String NPZM0191E = "NPZM0191E";

    /** Input parameter "Purchase Requisition Number" is a mandatory field. */
    public static final String NPZM0060E = "NPZM0060E";

    /** Input parameter "Pro Number" is a mandatory field. */
    public static final String NPZM0192E = "NPZM0192E";

    /** Input parameter "Inventory Location Code" is a mandatory field. */
    public static final String NPZM0193E = "NPZM0193E";

    /** Input parameter "Purchase Requisition Source Type Name" is a mandatory field. */
    public static final String NPZM0194E = "NPZM0194E";

    /** Input parameter "Merchandise Code" is a mandatory field. */
    public static final String NPZM0044E = "NPZM0044E";

    /** Purchase Requisition Quantity" is a mandatory field. */
    public static final String NPZM0064E = "NPZM0064E";

    /** Input Parameter "Technician TOC Code" is a mandatory field. */
    public static final String NPZM0181E = "NPZM0181E";

    /** Input Parameter "Click Key Text" is a mandatory field. */
    public static final String NPZM0260E = "NPZM0260E";

    /** Input Parameter "Retail Warehouse Code" is a mandatory field.    */
    public static final String NPZM0261E = "NPZM0261E";

    /** Input Parameter "Retail Sub Warehouse Code" is a mandatory field. */
    public static final String NPZM0262E = "NPZM0262E";

    /** Failed to insert the CLICK_TECH_RCV_PRT. */
    public static final String NPZM0207E = "NPZM0207E";

    /** Unexpected value is set for parameter [Purchase Request Source Type Name]. */
    public static final String NPZM0292E = "NPZM0292E";

    /** Input Parameter "Click Key Detail Text" is a mandatory field. */
    public static final String NPZM0295E = "NPZM0295E";

    /*****************************************************************
     * DS_COND_CONST
     ****************************************************************/
    /** DS_COND_CONST GroupId : NPZC1190 */
    public static final String CONST_GRP_ID_NPZC1190 = "NPZC1190";

    /** DS_COND_CONST GroupId : NPZC1190 */
    public static final String CONST_GRP_ID_NPZC1190_RTRN_TP = "NPZC1190_RTRN_TP";

    /** DS_COND_CONST KEY : PRT_RTRN_REQ_STS_KEY */
    public static final String CONST_PRT_RTRN_REQ_STS_KEY = "PRT_RTRN_REQ_STS";

    /** DS_COND_CONST KEY : PRT_RTRN_TP_KEY */
    public static final String CONST_PRT_RTRN_TP_KEY = "PRT_RTRN_TP";

//    /** --- Parts Return Request Status --- */
//    /** New */
//    public static final String CONST_STS_NEW = "STS_NEW";

    /** Awaiting Shipment */
    public static final String CONST_STS_AWAITINGSHIPMENT = "STS_AWAITINGSHIPMENT";

    /** Shipped */
    public static final String CONST_STS_SHIPPED = "STS_SHIPPED";

    /** Shipment Short */
    public static final String CONST_STS_SHIPMENTSHORT = "STS_SHIPMENTSHORT";

    /** Close */
    public static final String CONST_STS_CLOSE = "STS_CLOSE";

    /** Success */
    public static final String CONST_STS_SUCCESS = "STS_SUCCESS";

    /** Error */
    public static final String CONST_STS_ERROR = "STS_ERROR";

    /*****************************************************************
     * NLZC2100_CONST(SO Confirmation API)
     ****************************************************************/
    /** TIME FORMAT*/
    public static final String NLZC2100_TIME_FORMAT = "HHmmss";

    // START 2016/11/10 K.Kojima [QC#15490,ADD]
    /*****************************************************************
     * PRCH_REQ_STS_TXT
     ****************************************************************/
    /** Awaiting Shipment */
    public static final String PRCH_REQ_STS_AWAITING_SHIPMENT = "Awaiting Shipment";

    // add start 2016/12/05 CSA Defect#16079
    /** Shipped */
    public static final String PRCH_REQ_STS_SHIPPED = "Shipped";
    // add end 2016/12/05 CSA Defect#16079

    /*****************************************************************
     * COLUMN_NAME
     ****************************************************************/
    /** RQST_TECH_TOC_CD */
    public static final String RQST_TECH_TOC_CD = "RQST_TECH_TOC_CD";

    /** SRC_RTL_WH_CD */
    public static final String SRC_RTL_WH_CD = "SRC_RTL_WH_CD";

    /** SRC_RTL_SWH_CD */
    public static final String SRC_RTL_SWH_CD = "SRC_RTL_SWH_CD";

    /** INVTY_LOC_CD */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** PRCH_REQ_INVTY_TP_TXT */
    public static final String PRCH_REQ_INVTY_TP_TXT = "PRCH_REQ_INVTY_TP_TXT";

    /** MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** PRCH_REQ_QTY */
    public static final String PRCH_REQ_QTY = "PRCH_REQ_QTY";

    /** PRCH_REQ_SHORT_QTY */
    public static final String PRCH_REQ_SHORT_QTY = "PRCH_REQ_SHORT_QTY";

    /** PRCH_REQ_LINE_CMNT_TXT */
    public static final String PRCH_REQ_LINE_CMNT_TXT = "PRCH_REQ_LINE_CMNT_TXT";

    /** CLICK_KEY_DTL_TXT */
    public static final String CLICK_KEY_DTL_TXT = "CLICK_KEY_DTL_TXT";

    /** PRCH_REQ_LINE_NUM */
    public static final String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** PRCH_REQ_LINE_SUB_NUM */
    public static final String PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /** yyyyMMddHHmmSSsss */
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmSSss";

    // END 2016/11/10 K.Kojima [QC#15490,ADD]

    // START 2016/11/25 T.Tomita [QC#16165, ADD]
    /** HHmmss */
    public static final String HHMMSS = "HHmmss";

    /** Failed to insert the RTRN_PRO_NUM_LIST. */
    public static final String NPZM0297E = "NPZM0297E";
    // END 2016/11/25 T.Tomita [QC#16165, ADD]

    // QC#21913 Add.
    /** Failed to insert the SHPG_ORD_PRO_NUM_LIST. */
    public static final String NPZM0302E = "NPZM0302E";
}
