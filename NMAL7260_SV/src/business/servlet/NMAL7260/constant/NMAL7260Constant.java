/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260.constant;

/**
 *<pre>
 * NMAL7260Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2016/04/06   Fujitsu         Y.Kanefusa      Update          QC#6397
 * 2016/09/29   Fujitsu         R.nakamura      Update          QC#6924
 * 2016/11/11   Fujitsu         R.Nakamura      Update          QC#5940
 * 2017/02/27   Fujitsu         R.Nakamura      Update          QC#16011
 * 2017/08/15   Fujitsu         K.Ishizuka      Update          QC#18237(L3#161)
 * 2017/08/24   Fujitsu         S.Ohki          Update          QC#20729
 * 2018/04/05   Fujitsu         H.Nagashima     Update          QC#22595,QC#22593
 * 2018/04/19   Fujitsu         M.Ohno          Update          QC#22569
 * 2018/06/15   Fujitsu         M.Ishii         Update          QC#22594
 *</pre>
 */
public class NMAL7260Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7260";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7260Scrn00";

    /** Update Authority Screen ID T020 */
    public static final String UPDATE_AUTHORITY = "NMAL7260T020";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit" };

    /** F3 : Apply */
    public static final String[] BTN_CMN_APL = {"btn3", "CMN_Apply", "Apply" };

    /** F4 : Apply */
    public static final String[] BTN_CMN_APR = {"btn4", "CMN_Approve", "Approve" };

    /** F5 : Reject */
    public static final String[] BTN_CMN_RJT = {"btn5", "CMN_Reject", "Reject" };

    /** F6 : Download */
    public static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /** F7 : Delete */
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

    // --------------------------------
    // Message ID
    // --------------------------------

    /** The magnitude correlation of [@] is wrong. */
    public static final String NMAM8332E = "NMAM8332E";

    /** The contents of Table Definition and Table Data is different. */
    public static final String NMAM8324E = "NMAM8324E";

    /** You can not register by selecting the [@] in the item of [@]. */
    public static final String NMAM8326E = "NMAM8326E";

    /**
     * The file with other than the file extension "csv" "txt" can't
     * be uploaded.
     */
    public static final String ZYEM0096E = "ZYEM0096E";

    /** Required of Modifier field is mandatory. Please put the Check. */
    public static final String NMAM8317E = "NMAM8317E";

    /** Modifier item, please choose only one sure. */
    public static final String NMAM8316E = "NMAM8316E";

    /** [@] should be selected. */
    public static final String NMAM8352E = "NMAM8352E";

    /** Only 1 [@]can be selected. */
    public static final String NMAM8353E = "NMAM8353E";

    /** Please select one or more non-Modifier item. */
    public static final String NMAM8315E = "NMAM8315E";

    /** @ is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** Cannot be added because the number will exceed [@]. */
    public static final String NMAM0050E = "NMAM0050E";

    /** The chronological sequence is wrong. */
    public static final String NMAM8115E = "NMAM8115E";

    /** [@] field is mandatory. */
    public static final String NMAM0836E = "NMAM0836E";

    /** Please input [@] with [@] characters or less. */
    public static final String NMAM8350E = "NMAM8350E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] field requires numeric input only. */
    public static final String ZZM9004E = "ZZM9004E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Message Kind information */
    public static final String MESSAGE_KIND_INFO = "I";

    // QC#22595 add Start
    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    /** Detail requires at least one line.  Please enter. */
    public static final String NMAM8190E = "NMAM8190E";
    // QC#22595 add End

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
    // 2018/06/15 Delete Start QC#22594
//    /** OpenWin_CoaProd(09) */
//    public static final String OPENWIN_COAPROD = "OpenWin_CoaProd";
    // 2018/06/15 Delete End QC#22594
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

    /** OpenWin_PrcGrpCust(Sold To) */
    public static final String OPENWIN_PRCGRPCUST_SOLD = "OpenWin_PrcGrpCustSold";
    // 2018/06/15 Delete Start QC#22594
//    /** OpenWin_BillTo */
//    public static final String OPENWIN_BILLTO = "OpenWin_BillTo";
    
//    /** OpenWin_BillToAcctChnl */
//    public static final String OPENWIN_BILLTOACCTCHNL = "OpenWin_BillToAcctChnl";
//
//    /** OpenWin_BillToAcctClss */
//    public static final String OPENWIN_BILLTOACCTCLS = "OpenWin_BillToAcctClss";
//
//    /** OpenWin_Branch */
//    public static final String OPENWIN_BRANCH = "OpenWin_Branch";
//
//    /** OpenWin_CallType */
//    public static final String OPENWIN_CALLTYPE = "OpenWin_CallType";
//
//    /** OpenWin_LineCatg */
//    public static final String OPENWIN_LINECATG = "OpenWin_LineCatg";
//
//    /** OpenWin_MarketMdlNm */
//    public static final String OPENWIN_MARKETMDLNM = "OpenWin_MarketMdlNm";
//
//    /** OpenWin_ModelSeg */
//    public static final String OPENWIN_MODELSEG = "OpenWin_ModelSeg";
//
//    /** OpenWin_OrderSrc */
//    public static final String OPENWIN_ORDERSRC = "OpenWin_OrderSrc";
//
//    /** OpenWin_PaymentTp */
//    public static final String OPENWIN_PAYMENTTP = "OpenWin_PaymentTp";
    // 2018/06/15 Delete End QC#22594
    /** OpenWin_PrcList */
    public static final String OPENWIN_PRCLIST = "OpenWin_PrcList";
    // 2018/06/15 Delete Start QC#22594
//    /** OpenWin_RtnRsnCd */
//    public static final String OPENWIN_RTNRSNCD = "OpenWin_RtnRsnCd";
//
//    /** OpenWin_ServiceLv */
//    public static final String OPENWIN_SERVICELV = "OpenWin_ServiceLv";
    // 2018/06/15 Delete End QC#22594
    /** OpenWin_ServiceMdl */
    public static final String OPENWIN_SERVICEMDL = "OpenWin_ServiceMdl";
    // 2018/06/15 Delete Start QC#22594
//    /** OpenWin_ServiceZone */
//    public static final String OPENWIN_SERVICEZONE = "OpenWin_ServiceZone";
//
//    /** OpenWin_ShipToAcct */
//    public static final String OPENWIN_SHIPTOACCT = "OpenWin_ShipToAcct";
//
//    /** OpenWin_SpecialHandTp */
//    public static final String OPENWIN_SPECIALHANDTP = "OpenWin_SpecialHandTp";
//
//    /** OpenWin_BizUnit */
//    public static final String OPENWIN_BIZUNIT = "OpenWin_BizUnit";
//
//    /** OpenWin_FreightTerm */
//    public static final String OPENWIN_FREIGHTTERM = "OpenWin_FreightTerm";
 // 2018/06/15 Delete End QC#22594
    /** OpenWin_FreightZone */
    public static final String OPENWIN_FREIGHTZONE = "OpenWin_FreightZone";

    /** OpenWin_PrcdGrp */
    public static final String OPENWIN_PRCDGRP = "OpenWin_PrcdGrp";
    // 2017/08/24 QC#20729 Add Start
    /** OpenWin_ServiceMdl */
    public static final String OPENWIN_SVCMDL = "OpenWin_ServiceMdl";
    // 2017/08/24 QC#20729 Add End
    // QC#20249 2017/11/10 Add Start
    /** OpenWin_ServiceMdl */
    public static final String OPENWIN_PRCGRPMAT_QTYBRK = "OpenWin_PrcGrpMatQtyBrk";
    // QC#20249 2017/11/10 Add End
    // 2018/04/19 QC#22569 add start
    /** OpenWin_SlsMatGrpDescTxt1 */
    public static final String OPENWIN_MATGRP1 = "OpenWin_SlsMatGrpDescTxt1";

    /** OpenWin_SlsMatGrpDescTxt2 */
    public static final String OPENWIN_MATGRP2 = "OpenWin_SlsMatGrpDescTxt2";

    /** OpenWin_SlsMatGrpDescTxt3 */
    public static final String OPENWIN_MATGRP3 = "OpenWin_SlsMatGrpDescTxt3";
    // 2018/04/19 QC#22569 add end

    /** Modifier Column(Formula,Percent,Value) */
    public static final String MODIFIER_COLUMN = "Modifier Column(Formula,Percent,Value)";

    /** SEMICOLON */
    public static final String SEMICOLON = ":";

    /** Default column count */
    public static final int DEFAULT_COL_CNT = 3;

    /** date max */
    public static final String TO_DATE_MAX = "99991231";

    /** table def max */
    public static final int TABLE_DEF_MAX_CNT = 13;

    /** NMAL6760 : DISP_HRCH_ACCT_CD_BILL */
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";

    /** PRC_RULE_COND_GRP_CD Max Length */
    public static final int COND_GRP_MAX_LENGTH = 2;

    // 2018/06/15 Add Start QC#22594
    // --------------------------------
    // NMAL6050_EventNm
    // --------------------------------
    /** OpenWin_CoaMdseTp */
    public static final String OPENWIN_COAMDSETP = "OpenWin_CoaMdseTp";

    /** OpenWin_CoaProd */
    public static final String OPENWIN_COAPROD = "OpenWin_CoaProd";

    /** OpenWin_DSOrdCatg */
    public static final String OPENWIN_DSORDCATG = "OpenWin_DSOrdCatg";

    /** OpenWin_DSOrdTp */
    public static final String OPENWIN_DSORDTP = "OpenWin_DSOrdTp";

    /** OpenWin_LineBizTp */
    public static final String OPENWIN_LINEBIZTP = "OpenWin_LineBizTp";

    /** OpenWin_CoaChSold */
    public static final String OPENWIN_COACHSOLD = "OpenWin_CoaChSold";

    /** OpenWin_DsAcctClsSold */
    public static final String OPENWIN_DSACCTCLSSOLD = "OpenWin_DsAcctClsSold";

    /** OpenWin_BillToAcctChnl */
    public static final String OPENWIN_BILLTOACCTCHNL = "OpenWin_BillToAcctChnl";

    /** OpenWin_BillToAcctClss */
    public static final String OPENWIN_BILLTOACCTCLSS = "OpenWin_BillToAcctClss";

    /** OpenWin_BillToAcctClss */
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

    /** OpenWin_RtnRsnCd */
    public static final String OPENWIN_RTNRSNCD = "OpenWin_RtnRsnCd";

    /** OpenWin_ServiceLv */
    public static final String OPENWIN_SERVICELV = "OpenWin_ServiceLv";

    /** OpenWin_ServiceZone */
    public static final String OPENWIN_SERVICEZONE = "OpenWin_ServiceZone";

    /** OpenWin_ShipToAcct */
    public static final String OPENWIN_SHIPTOACCT = "OpenWin_ShipToAcct";

    /** OpenWin_SpecialHandTp */
    public static final String OPENWIN_SPECIALHANDTP = "OpenWin_SpecialHandTp";

    /** OpenWin_BizUnit */
    public static final String OPENWIN_BIZUNIT = "OpenWin_BizUnit";

    /** OpenWin_FreightTerm */
    public static final String OPENWIN_FREIGHTTERM = "OpenWin_FreightTerm";

    // --------------------------------
    // NMAL6760_EventNm
    // --------------------------------
    /** OpenWin_BillTo */
    public static final String OPENWIN_BILLTO = "OpenWin_BillTo";

    /** OpenWin_AcctNumCustSold */
    public static final String OPENWIN_ACCTNUMCUSTSOLD = "OpenWin_AcctNumCustSold";

    // --------------------------------
    // NMAL6800_EventNm
    // --------------------------------
    /** OpenWin_ItemSearch */
    public static final String OPENWIN_ITEMSEARCH = "OpenWin_ItemSearch";
    // 2018/06/15 Add End QC#22594

    // --------------------------------
    // Index
    // --------------------------------
    public static final int IDX_0 = 0;

    public static final int IDX_1 = 1;

    public static final int IDX_2 = 2;

    public static final int IDX_3 = 3;

    public static final int IDX_4 = 4;

    public static final int IDX_5 = 5;

    public static final int IDX_6 = 6;

    public static final int IDX_7 = 7;

    public static final int IDX_8 = 8;

    public static final int IDX_9 = 9;

    public static final int IDX_10 = 10;

    public static final int IDX_11 = 11;

    public static final int IDX_12 = 12;

    public static final int IDX_13 = 13;

    public static final int IDX_20 = 20;

    public static final int IDX_22 = 22;

    public static final int IDX_26 = 26;

    public static final int IDX_27 = 27;

    public static final int IDX_30 = 30;

    public static final int IDX_31 = 31;

    public static final int IDX_33 = 33;

    public static final int IDX_50 = 50;

    public static final int IDX_80 = 80;

    public static final int IDX_100 = 100;

    // Add Start S21_NA QC#18237(Sol#161)
    // --------------------------------
    // Filter
    // --------------------------------

    public static final int FILTER_CSMP_NUM = 4;

    public static final int FILTER_CSMP_COMMENT = 104;

    public static final int FILTER_GRP_PK = 5;

    public static final int FILTER_GRP_NM = 105;

    public static final int FILTER_PROD_CTRL_1 = 6;

    public static final int FILTER_PROD_CTRL_1_TXT = 106;

    public static final int FILTER_PROD_CTRL_2 = 7;

    public static final int FILTER_PROD_CTRL_2_TXT = 107;

    public static final int FILTER_COA_MDSE_TP = 8;

    public static final int FILTER_COA_MDSE_TP_DESC_TXT = 108;

    public static final int FILTER_COA_PROD = 9;

    public static final int FILTER_COA_PROD_DESC_TXT = 109;

    public static final int FILTER_MDSE_CD = 10;

    public static final int FILTER_MDSE_SHORT_TXT = 110;

    public static final int FILTER_MNF_ITEM_CD = 62;

    public static final int FILTER_DS_ORD_CATG = 11;

    public static final int FILTER_DS_ORD_CATG_DESC_TXT = 111;

    public static final int FILTER_DS_ORD_TP = 12;

    public static final int FILTER_DS_ORD_TP_DESC_TXT = 112;

    public static final int FILTER_LINE_BIZ_TP = 13;

    public static final int FILTER_LINE_BIZ_TP_DESC_TXT = 113;

    public static final int FILTER_PRC_GRP_TRT = 14;

    public static final int FILTER_PRC_GRP_TRT_TXT = 114;

    public static final int FILTER_TOTAL_TRX_WEIGHT_FROM_FROM = 15;

    public static final int FILTER_TOTAL_TRX_WEIGHT_FROM_TO = 115;

    public static final int FILTER_TOTAL_TRX_WEIGHT_TO_FROM = 171;

    public static final int FILTER_TOTAL_TRX_WEIGHT_TO_TO = 172;

    public static final int FILTER_BILL_TO_ACCT = 16;

    public static final int FILTER_BILL_TO_ACCT_NM = 116;

    public static final int FILTER_COA_CH = 17;

    public static final int FILTER_COA_CH_DESC_TXT = 117;

    public static final int FILTER_DS_ACCT_CLS = 18;

    public static final int FILTER_DS_ACCT_CLS_DESC_TXT = 118;

    public static final int FILTER_COA_BR = 19;

    public static final int FILTER_COA_BR_DESC_TXT = 119;

    public static final int FILTER_SVC_CALL_TP = 20;

    public static final int FILTER_SVC_CALL_TP_DESC_TXT = 120;

    public static final int FILTER_CALL_DATE_FROM_FROM = 21;

    public static final int FILTER_CALL_DATET_FROM_TO = 121;

    public static final int FILTER_CALL_DATE_TO_FROM = 181;

    public static final int FILTER_CALL_DATE_TO_TO = 182;

    public static final int FILTER_CUST_PO_FROM_FROM = 22;

    public static final int FILTER_CUST_PO_FROM_TO = 122;

    public static final int FILTER_CUST_PO_TO_FROM = 173;

    public static final int FILTER_CUST_PO_TO_TO = 174;

    public static final int FILTER_LINE_AMT_FROM_FROM = 24;

    public static final int FILTER_LINE_AMT_FROM_TO = 124;

    public static final int FILTER_LINE_AMT_TO_FROM = 175;

    public static final int FILTER_LINE_AMT_TO_TO = 176;

    public static final int FILTER_DS_ORD_LINE_CATG = 25;

    public static final int FILTER_DS_ORD_LINE_CATG_DESC_TXT = 125;

    public static final int FILTER_LINE_QTY_FROM_FROM = 26;

    public static final int FILTER_LINE_QTY_FROM_TO = 126;

    public static final int FILTER_LINE_QTY_TO_FROM = 177;

    public static final int FILTER_LINE_QTY_TO_TO = 178;

    public static final int FILTER_MKT_MDL = 27;

    public static final int FILTER_MKT_MDL_DESC_TXT = 127;

    public static final int FILTER_MKT_MDL_SEG = 28;

    public static final int FILTER_MKT_MDL_SEG_DESC_TXT = 128;

    public static final int FILTER_CPO_SRC_TP = 29;

    public static final int FILTER_CPO_SRC_TP_DESC_TXT = 129;

    public static final int FILTER_ORD_SUB_TOT_FROM_FROM = 30;

    public static final int FILTER_ORD_SUB_TOT_FROM_TO = 130;

    public static final int FILTER_ORD_SUB_TOT_TO_FROM = 179;

    public static final int FILTER_ORD_SUB_TOT_TO_TO = 180;

    public static final int FILTER_DS_PMT_METH = 31;

    public static final int FILTER_DS_PMT_METH_DESC_TXT = 131;

    public static final int FILTER_PRC_LIST = 32;

    public static final int FILTER_PRC_LIST_TXT = 132;

    public static final int FILTER_PRC_DATE_FROM_FROM = 33;

    public static final int FILTER_PRC_DATE_FROM_TO = 133;

    public static final int FILTER_PRC_DATE_TO_FROM = 183;

    public static final int FILTER_PRC_DATE_TO_TO = 184;

    public static final int FILTER_PROD_CTRL_3 = 34;

    public static final int FILTER_PROD_CTRL_3_TXT = 134;

    public static final int FILTER_PROD_CTRL_4 = 35;

    public static final int FILTER_PROD_CTRL_4_TXT = 135;

    public static final int FILTER_PROD_CTRL_5 = 36;

    public static final int FILTER_PROD_CTRL_5_TXT = 136;

    public static final int FILTER_REQ_DATE_FROM_FROM = 37;

    public static final int FILTER_REQ_DATE_TOT_FROM_TO = 137;

    public static final int FILTER_REQ_DATE_TO_FROM = 185;

    public static final int FILTER_REQ_DATE_TO_TO = 186;

    public static final int FILTER_RTRN_RSN = 38;

    public static final int FILTER_RTRN_RSN_DESC_TXT = 138;

    public static final int FILTER_SHPG_SVC_LVL = 39;

    public static final int FILTER_SHPG_SVC_LVL_DESC_TXT = 139;

    public static final int FILTER_MDL = 40;

    public static final int FILTER_MDL_NM = 140;

    public static final int FILTER_PRC_SVC_ZONE = 41;

    public static final int FILTER_PRC_SVC_ZONE_DESC_TXT = 141;

    public static final int FILTER_DS_ACCT_CLS_BILL = 42;

    public static final int FILTER_DS_ACCT_CLS_BILL_DESC_TXT = 142;

    public static final int FILTER_SPCL_HDLG_TP = 44;

    public static final int FILTER_SPCL_HDLG_TP_DESC_TXT = 144;

    public static final int FILTER_TOT_QTY_FROM_FROM = 45;

    public static final int FILTER_TOT_QTY_FROM_TO = 145;

    public static final int FILTER_TOT_QTY_TO_FROM = 187;

    public static final int FILTER_TOT_QTY_TO_TO = 188;

    public static final int FILTER_COA_EXTN = 46;

    public static final int FILTER_COA_EXTN_DESC_TXT = 146;

    public static final int FILTER_FRT_COND = 48;

    public static final int FILTER_FRT_COND_DESC_TXT = 148;

    public static final int FILTER_FREIGHT_ZONE = 49;

    public static final int FILTER_FREIGHT_ZONE_TXT = 149;

    public static final int FILTER_PRC_GRP_CUST_SOLD = 53;

    public static final int FILTER_PRC_GRP_CUST_SOLD_TXT = 153;

    public static final int FILTER_DS_ACCT = 54;

    public static final int FILTER_DS_ACCT_NM = 154;

    public static final int FILTER_PRC_RULE_COND = 55;

    public static final int FILTER_PRC_RULE_COND_FROM_TXT = 155;

    public static final int FILTER_ACCT_CLS = 56;

    public static final int FILTER_ACCT_CLS_DESC_TXT = 156;

    // QC#20249 2017/11/10 Add Start
    public static final int FILTER_MAT_GRP_QTYBREAK_PK = 57;

    public static final int FILTER_MAT_GRP_QTYBREAK_NM = 157;

    public static final int FILTER_LINEQTY_QTYBREAK_FROM_FROM = 58;

    public static final int FILTER_LINEQTY_QTYBREAK_FROM_TO = 158;

    public static final int FILTER_LINEQTY_QTYBREAK_TO_FROM = 199;

    public static final int FILTER_LINEQTY_QTYBREAK_TO_TO = 200;

    // QC#20249 2017/11/10 Add End
    // 2018/04/19 QC#22569 add start
    public static final int FILTER_MAT_GRP_1 = 59;

    public static final int FILTER_MAT_GRP_2 = 60;

    public static final int FILTER_MAT_GRP_3 = 61;

    public static final int FILTER_MAT_GRP_1_TXT = 159;

    public static final int FILTER_MAT_GRP_2_TXT = 160;

    public static final int FILTER_MAT_GRP_3_TXT = 161;
    // 2018/04/19 QC#22569 add end

    /** String Date High Value */
    public static final String HIGH_VAL_DT = "99991231";

    // Add End S21_NA QC#18237(Sol#161)
    // QC#20249 2017/11/10 Add Start
    public static final int NMAL7300_GLBL_CMPY_CD = 1;

    public static final int NMAL7300_PRC_ADJ_LINE = 2;

    public static final int NMAL7300_CONDITION_LIST = 3;

    public static final int NMAL7300_INPUT_LIST = 4;

    public static final int NMAL7300_MAX_INPUT_PARAM_NUM = NMAL7300_INPUT_LIST;
    // QC#20249 2017/11/10 Add End

    // QC#22595 add Start
    /** Check Box PB */
    public static final String CHK_B = "xxChkBox_B1";
    // QC#22595 add End

    // QC#22593 add Start
    /** TAB : Table Definition */
    public static final String TAB_ADJ_TBL_DFN = "AdjTblDfn";

    /** TAB : Table Data */
    public static final String TAB_ADJ_TBL_DATA = "AdjTblData";
    // QC#22593 add End
}
