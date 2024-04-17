/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7420.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/07   Fujitsu         K.Ishizuka         Create          N/A
 * 2018/04/19   Fujitsu         M.Ohno             Update          QC#22569
 * 2018/07/20   Fujitsu         W.Honda            Update          QC#20267
 * 2018/12/04   Fujitsu         Hd.Sugawara     Update          QC#29321
 *</pre>
 */
public interface NMAL7420Constant {

    /** Business ID */
    String BIZ_ID = "NMAL7420";

    /** Common Visible Attributes */
    public static final String CMN_VISIBLE_ATTRB = ":BH1";

    /** Function Code (Search) */
    public static final String FUNC_CD_SRCH = "20";

    /** NMAL6760 : DISP_HRCH_ACCT_CD_BILL */
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** @ is not number. */
    public static final String NMAM8535E = "NMAM8535E";

    /** A past date cannot be entered into [@]. */
    public static final String NMAM8200E = "NMAM8200E";

    /** The value for [@] must be over [@]. */
    public static final String NMAM0042E = "NMAM0042E";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F8 : Clear */
    String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /* ------ Parameter Number ------ */

    int FILTER_CSMP_NUM = 4;

    int FILTER_CSMP_COMMENT = 104;

    int FILTER_GRP_PK = 5;

    int FILTER_GRP_NM = 105;

    int FILTER_PROD_CTRL_1 = 6;

    int FILTER_PROD_CTRL_1_TXT = 106;

    int FILTER_PROD_CTRL_2 = 7;

    int FILTER_PROD_CTRL_2_TXT = 107;

    int FILTER_COA_MDSE_TP = 8;

    int FILTER_COA_MDSE_TP_DESC_TXT = 108;

    int FILTER_COA_PROD = 9;

    int FILTER_COA_PROD_DESC_TXT = 109;

    int FILTER_MDSE_CD = 10;

    int FILTER_MDSE_SHORT_TXT = 110;

    int FILTER_MNF_ITEM_CD = 62; // QC#20267 2018/07/20 Add

    int FILTER_DS_ORD_CATG = 11;

    int FILTER_DS_ORD_CATG_DESC_TXT = 111;

    int FILTER_DS_ORD_TP = 12;

    int FILTER_DS_ORD_TP_DESC_TXT = 112;

    int FILTER_LINE_BIZ_TP = 13;

    int FILTER_LINE_BIZ_TP_DESC_TXT = 113;

    int FILTER_PRC_GRP_TRT = 14;

    int FILTER_PRC_GRP_TRT_TXT = 114;

    int FILTER_TOTAL_TRX_WEIGHT_FROM_FROM = 15;

    int FILTER_TOTAL_TRX_WEIGHT_FROM_TO = 115;

    int FILTER_TOTAL_TRX_WEIGHT_TO_FROM = 171;

    int FILTER_TOTAL_TRX_WEIGHT_TO_TO = 172;

    int FILTER_BILL_TO_ACCT = 16;

    int FILTER_BILL_TO_ACCT_NM = 116;

    int FILTER_COA_CH = 17;

    int FILTER_COA_CH_DESC_TXT = 117;

    int FILTER_DS_ACCT_CLS = 18;

    int FILTER_DS_ACCT_CLS_DESC_TXT = 118;

    int FILTER_COA_BR = 19;

    int FILTER_COA_BR_DESC_TXT = 119;

    int FILTER_SVC_CALL_TP = 20;

    int FILTER_SVC_CALL_TP_DESC_TXT = 120;

    int FILTER_CALL_DATE_FROM_FROM = 21;

    int FILTER_CALL_DATET_FROM_TO = 121;

    int FILTER_CALL_DATE_TO_FROM = 181;

    int FILTER_CALL_DATE_TO_TO = 182;

    int FILTER_CUST_PO_FROM_FROM = 22;

    int FILTER_CUST_PO_FROM_TO = 122;

    int FILTER_CUST_PO_TO_FROM = 173;

    int FILTER_CUST_PO_TO_TO = 174;

    int FILTER_LINE_AMT_FROM_FROM = 24;

    int FILTER_LINE_AMT_FROM_TO = 124;

    int FILTER_LINE_AMT_TO_FROM = 175;

    int FILTER_LINE_AMT_TO_TO = 176;

    int FILTER_DS_ORD_LINE_CATG = 25;

    int FILTER_DS_ORD_LINE_CATG_DESC_TXT = 125;

    int FILTER_LINE_QTY_FROM_FROM = 26;

    int FILTER_LINE_QTY_FROM_TO = 126;

    int FILTER_LINE_QTY_TO_FROM = 177;

    int FILTER_LINE_QTY_TO_TO = 178;

    int FILTER_MKT_MDL = 27;

    int FILTER_MKT_MDL_DESC_TXT = 127;

    int FILTER_MKT_MDL_SEG = 28;

    int FILTER_MKT_MDL_SEG_DESC_TXT = 128;

    int FILTER_CPO_SRC_TP = 29;

    int FILTER_CPO_SRC_TP_DESC_TXT = 129;

    int FILTER_ORD_SUB_TOT_FROM_FROM = 30;

    int FILTER_ORD_SUB_TOT_FROM_TO = 130;

    int FILTER_ORD_SUB_TOT_TO_FROM = 179;

    int FILTER_ORD_SUB_TOT_TO_TO = 180;

    int FILTER_DS_PMT_METH = 31;

    int FILTER_DS_PMT_METH_DESC_TXT = 131;

    int FILTER_PRC_LIST = 32;

    int FILTER_PRC_LIST_TXT = 132;

    int FILTER_PRC_DATE_FROM_FROM = 33;

    int FILTER_PRC_DATE_FROM_TO = 133;

    int FILTER_PRC_DATE_TO_FROM = 183;

    int FILTER_PRC_DATE_TO_TO = 184;

    int FILTER_PROD_CTRL_3 = 34;

    int FILTER_PROD_CTRL_3_TXT = 134;

    int FILTER_PROD_CTRL_4 = 35;

    int FILTER_PROD_CTRL_4_TXT = 135;

    int FILTER_PROD_CTRL_5 = 36;

    int FILTER_PROD_CTRL_5_TXT = 136;

    int FILTER_REQ_DATE_FROM_FROM = 37;

    int FILTER_REQ_DATE_TOT_FROM_TO = 137;

    int FILTER_REQ_DATE_TO_FROM = 185;

    int FILTER_REQ_DATE_TO_TO = 186;

    int FILTER_RTRN_RSN = 38;

    int FILTER_RTRN_RSN_DESC_TXT = 138;

    int FILTER_SHPG_SVC_LVL = 39;

    int FILTER_SHPG_SVC_LVL_DESC_TXT = 139;

    int FILTER_MDL = 40;

    int FILTER_MDL_NM = 140;

    int FILTER_PRC_SVC_ZONE = 41;

    int FILTER_PRC_SVC_ZONE_DESC_TXT = 141;

    int FILTER_DS_ACCT_CLS_BILL = 42;

    int FILTER_DS_ACCT_CLS_BILL_DESC_TXT = 142;

    int FILTER_SPCL_HDLG_TP = 44;

    int FILTER_SPCL_HDLG_TP_DESC_TXT = 144;

    int FILTER_TOT_QTY_FROM_FROM = 45;

    int FILTER_TOT_QTY_FROM_TO = 145;

    int FILTER_TOT_QTY_TO_FROM = 187;

    int FILTER_TOT_QTY_TO_TO = 188;

    int FILTER_COA_EXTN = 46;

    int FILTER_COA_EXTN_DESC_TXT = 146;

    int FILTER_FRT_COND = 48;

    int FILTER_FRT_COND_DESC_TXT = 148;

    int FILTER_FREIGHT_ZONE = 49;

    int FILTER_FREIGHT_ZONE_TXT = 149;

    int FILTER_PRC_GRP_CUST_SOLD = 53;

    int FILTER_PRC_GRP_CUST_SOLD_TXT = 153;

    int FILTER_DS_ACCT = 54;

    int FILTER_DS_ACCT_NM = 154;

    int FILTER_PRC_RULE_COND = 55;

    int FILTER_PRC_RULE_COND_FROM_TXT = 155;

    int FILTER_ACCT_CLS = 56;

    int FILTER_ACCT_CLS_DESC_TXT = 156;

    int FILTER_GRP_QTYBRK_PK = 57;

    int FILTER_GRP_QTYBRK_NM = 157;

    int FILTER_LINEQTY_QTYBRK_FROM_FROM = 58;

    int FILTER_LINEQTY_QTYBRK_FROM_TO = 158;

    int FILTER_LINEQTY_QTYBRK_TO_FROM = 199;

    int FILTER_LINEQTY_QTYBRK_TO_TO = 200;

    int FILTER_FORMULA = 50;

    int FILTER_PERCENT = 51;

    int FILTER_VALUE = 52;

    int FILTER_FORMULA_TXT = 150;

    int FILTER_EFFECT_DATE_FROM_FROM = 189;

    int FILTER_EFFECT_DATE_FROM_TO = 190;

    int FILTER_EFFECT_DATE_TO_FROM = 191;

    int FILTER_EFFECT_DATE_TO_TO = 192;

    int FILTER_CREATE_BY = 193;

    int FILTER_CREATE_DATE_FROM = 194;

    int FILTER_CREATE_DATE_TO = 195;

    int FILTER_UPDATE_BY = 196;

    int FILTER_UPDATE_DATE_FROM = 197;

    int FILTER_UPDATE_DATE_TO = 198;

    // 2018/04/19 QC#22569 add start
    int FILTER_MAT_GRP_1 = 59;

    int FILTER_MAT_GRP_2 = 60;

    int FILTER_MAT_GRP_3 = 61;

    int FILTER_MAT_GRP_1_TXT = 159;

    int FILTER_MAT_GRP_2_TXT = 160;

    int FILTER_MAT_GRP_3_TXT = 161;
    // 2018/04/19 QC#22569 add end
    // --------------------------------
    // NWAL1130_EventNm
    // --------------------------------
    /** OpenWin_PrcGrpCust(02) */
    public static final String OPENWIN_PRCGRPCUST = "OpenWin_PrcGrpCust";

    /** OpenWin_CsmpNum(04) */
    public static final String OPENWIN_CSMPNUM = "OpenWin_CsmpNum";

    /** OpenWin_PrcGrpMat(05) */
    public static final String OPENWIN_PRCGRPMAT = "OpenWin_PrcGrpMat";

    /** OpenWin_ProdCtrl(06) */
    public static final String OPENWIN_PRODCTRL = "OpenWin_ProdCtrl";

    /** OpenWin_ProdCtrl2(07) */
    public static final String OPENWIN_PRODCTRL2 = "OpenWin_ProdCtrl2";

    /** OpenWin_CoaProd(09) */
    public static final String OPENWIN_COAPROD = "OpenWin_CoaProd";

    /** OpenWin_PrcGrpTrx(09) */
    public static final String OPENWIN_PRCGRPTRX = "OpenWin_PrcGrpTrx";

    /** OpenWin_Formula */
    public static final String OPENWIN_FORMULA = "OpenWin_Formula";

    /** OpenWin_ProdCtrl3 */
    public static final String OPENWIN_PRODCTRL3 = "OpenWin_ProdCtrl3";

    /** OpenWin_ProdCtrl4 */
    public static final String OPENWIN_PRODCTRL4 = "OpenWin_ProdCtrl4";

    /** OpenWin_ProdCtrl5 */
    public static final String OPENWIN_PRODCTRL5 = "OpenWin_ProdCtrl5";

    /** OpenWin_FreightZone */
    public static final String OPENWIN_FREIGHTZONE = "OpenWin_FreightZone";

    /** OpenWin_PrcGrpCust(Sold To) */
    public static final String OPENWIN_PRCGRPCUST_SOLD = "OpenWin_PrcGrpCustSold";

    /** OpenWin_BillTo */
    public static final String OPENWIN_BILLTO = "OpenWin_BillTo";

    /** OpenWin_BillToAcctChnl */
    public static final String OPENWIN_BILLTOACCTCHNL = "OpenWin_BillToAcctChnl";

    /** OpenWin_BillToAcctClss */
    public static final String OPENWIN_BILLTOACCTCLSS = "OpenWin_BillToAcctClss";

    /** OpenWin_Branch */
    public static final String OPENWIN_BRANCH = "OpenWin_Branch";

    /** OpenWin_CallType */
    public static final String OPENWIN_CALLTYPE = "OpenWin_CallType";

    /** OpenWin_LineCatg */
    public static final String OPENWIN_LINECATG = "OpenWin_LineCatg";

    /** OpenWin_MarketMdlNm */
    public static final String OPENWIN_MARKETMDLNM = "OpenWin_MarketMdlNm";

    /** OpenWin_ModelSeg */
    public static final String OPENWIN_MODELSEG = "OpenWin_ModelSeg";

    /** OpenWin_OrderSrc */
    public static final String OPENWIN_ORDERSRC = "OpenWin_OrderSrc";

    /** OpenWin_PaymentTp */
    public static final String OPENWIN_PAYMENTTP = "OpenWin_PaymentTp";

    /** OpenWin_PrcList */
    public static final String OPENWIN_PRCLIST = "OpenWin_PrcList";

    /** OpenWin_RtnRsnCd */
    public static final String OPENWIN_RTNRSNCD = "OpenWin_RtnRsnCd";

    /** OpenWin_ServiceLv */
    public static final String OPENWIN_SERVICELV = "OpenWin_ServiceLv";

    /** OpenWin_ServiceMdl */
    public static final String OPENWIN_SERVICEMDL = "OpenWin_ServiceMdl";

    /** OpenWin_ServiceZone */
    public static final String OPENWIN_SERVICEZONE = "OpenWin_ServiceZone";

    /** OpenWin_ShipToAcct */
    public static final String OPENWIN_SHIPTOACCT = "OpenWin_ShipToAcct";

    /** OpenWin_SpecialHandTp */
    public static final String OPENWIN_SPECIALHANDTP = "OpenWin_SpecialHandTp";

    /** OpenWin_PrcGrpMatQtyBrk */
    public static final String OPENWIN_PRCGRPMATBRK = "OpenWin_PrcGrpMatQtyBrk";

    // 2018/04/19 QC#22569 add start
    /** OpenWin_SlsMatGrpDescTxt1 */
    public static final String OPENWIN_MATGRP1 = "OpenWin_SlsMatGrpDescTxt1";

    /** OpenWin_SlsMatGrpDescTxt2 */
    public static final String OPENWIN_MATGRP2 = "OpenWin_SlsMatGrpDescTxt2";

    /** OpenWin_SlsMatGrpDescTxt3 */
    public static final String OPENWIN_MATGRP3 = "OpenWin_SlsMatGrpDescTxt3";
    // 2018/04/19 QC#22569 add end

    // Add Start 2018/12/04 QC#29321
    /** @ exceed the maximum length. */
    String NMAM8579E = "NMAM8579E";

    /** The number of data for @ exceeded the maximum[@]. */
    String NMAM8696E = "NMAM8696E";

    /** String COMMA */
    String COMMA = ",";

    /** Max Input Data Count */
    int MAX_INPUT_DATA_CNT = 99;

    /** Max length of parameter */
    int MAX_LENGTH_PARAMETER = 50;
    // Add End 2018/12/04 QC#29321
}
