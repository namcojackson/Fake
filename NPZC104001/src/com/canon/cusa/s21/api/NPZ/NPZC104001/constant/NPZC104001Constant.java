/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC104001.constant;

/**
 * <pre>
 * PO Create API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   CITS            T.kikuhara      Create          CSA Ver3.0
 * 09/29/2016   CITS            T.Gotoda        Update          QC#13163
 * 10/14/2016   CITS            R.Shimamoto     Update          QC#6159
 * 10/26/2016   CITS            R.Shimamoto     Update          QC#15178
 * 02/27/2017   CITS            S.Endo          Update          QC#17806
 * 10/20/2017   CITS            S.Katsuma       Update          QC#21206
 * 03/15/2018   CITS            K.Ogino         Update          QC#24780
 * 07/30/2018   CITS            K.Kameoka       Update          QC#27024
 * 10/01/2018   CITS            K.Kameoka       Update          QC#28463
 * 12/20/2019   CITS            R.Shimamoto     Update          QC#55085
 * </pre>
 */
public class NPZC104001Constant {

    /** MODE */
    public static final String MODE_CREATE = "1";

    /**  */
    public static final String MODE_UPDATE = "2";

    /**  */
    public static final String MODE_CANCEL = "3";

    /**  */
    public static final String MODE_SEND_PO = "4";

    /** Add QC#24780 */
    public static final String MODE_ASN = "5";

    /** EVENT */
    public static final String EVENT_SAVE = "1";

    /**  */
    public static final String EVENT_SUBMIT = "2";

    // FIX VALUE
    /**  */
    public static final String NEW_ORDER = "1";

    /**  */
    public static final String UPDATE_ORDER = "2";

    /**  */
    public static final String FRT_COND_COLLECT = "F04";

    /**  */
    public static final String STAR = "*";

    /**  */
    public static final int LENGTH_FIVE = 5;

    /**  */
    public static final int LENGTH_THREE = 3;

    /**  */
    public static final int LENGTH_FOUR = 4;

    /**  */
    public static final int LENGTH_8 = 8;

    /**  */
    public static final int LENGTH_13 = 13;

    /**  */
    public static final int LENGTH_15 = 15;

    /**  */
    public static final int ROUND_UP_NO_3 = 3;

    /**  */
    public static final int PO_MSG_SEG_ID_1 = 1;

    /**  */
    public static final int PO_MSG_SEG_ID_2 = 2;

    /**  */
    public static final int PO_MSG_SEG_ID_3 = 3;

    /**  */
    public static final int PO_MSG_SEG_ID_4 = 4;

    /**  */
    public static final String TRX_LINE_SUB_NUM = "001";

    /**  */
    public static final String PRINTED = "PRINTED";

    /**  */
    public static final String RECEIVING = "RECEIVING";

    /**  */
    public static final String PO_ORD_NUM_ONLINE_KEY = "VP#_ONL";

    /**  */
    public static final String PO_ORD_NUM_BATCH_KEY = "VP#_BAT";

    /**  */
    public static final String SEND_PO = "Send PO";

    /**  */
    public static final String PO_SUBMITTED = "PO Submitted";

    /**  */
    public static final String PO_CONFIRMED = "PO Confirmed";

    /**  */
    public static final String PO_PRINTED = "PO Printed";

    /**  */
    public static final String AWAITING_PO_CHANGE = "Awaiting Approval(PO Change)";

    /**  */
    public static final String AWAITING_PO_CANCEL = "Awaiting Approval(PO Cancel)";

    // VAR_CHAR_CONST_NM
    /**  */
    public static final String PO_CUST_DROP_QLFY_CD = "PO_CUST_DROP_QLFY_CD";

    /**  */
    public static final String PO_LINE_MAX_CNT = "PO_LINE_MAX_CNT";

    /**  */
    public static final String DRCT_CPO_CRAT_FLG = "DRCT_CPO_CRAT_FLG";

    /**  */
    public static final String SEND_PO_IF_CRAT_FLG = "SEND_PO_IF_CRAT_FLG";

    /**  */
    public static final String PO_ACRL_RTL_WH_ACCT_TP_CD = "PO_ACRL_RTL_WH_ACCT_TP_CD";

    /**  */
    public static final String NPAL0110_BILL_TO_CD = "NPAL0110_BILL_TO_CD";

    /**  */
    public static final String CUSA_PARTS_PO_CHK_RQST_FLG = "CUSA_PARTS_PO_CHK_RQST_FLG";

    /**  */
    public static final String CHECK_SHIP_TO_CUST_FLG = "CHECK_SHIP_TO_CUST_FLG";

    /** SORT_KEY_PO_ORD_DTL_LINE_NUM */
    public static final String SORT_KEY_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    /** SORT_KEY_SVC_CONFIG_MSTR_PK */
    public static final String SORT_KEY_SVC_CONFIG_MSTR_PK = "svcConfigMstrPk";

    // MSG_ID
    /**  */
    public static final String NPZM9999E = "NPZM9999E";

    /**  */
    public static final String NPZM0001E = "NPZM0001E";

    /**  */
    public static final String NPZM0093E = "NPZM0093E";

    /**  */
    public static final String NPZM0101E = "NPZM0101E";

    /**  */
    public static final String NPZM0094E = "NPZM0094E";

    /**  */
    public static final String NPZM0102E = "NPZM0102E";

    /**  */
    public static final String NPZM0059E = "NPZM0059E";

    /**  */
    public static final String NPZM0122E = "NPZM0122E";

    /**  */
    public static final String NPZM0063E = "NPZM0063E";

    /**  */
    public static final String NPZM0109E = "NPZM0109E";

    /**  */
    public static final String NPZM0113E = "NPZM0113E";

    /**  */
    public static final String NPZM0108E = "NPZM0108E";

    /**  */
    public static final String NPZM0020E = "NPZM0020E";

    /**  */
    public static final String NPZM0110E = "NPZM0110E";

    /**  */
    public static final String NPZM0114E = "NPZM0114E";

    /**  */
    public static final String NPZM0116E = "NPZM0116E";

    /**  */
    public static final String NPZM0146E = "NPZM0146E";

    /**  */
    public static final String NPZM0043E = "NPZM0043E";

    /**  */
    public static final String NPZM0162E = "NPZM0162E";

    /**  */
    public static final String NPAM1365E = "NPAM1365E";

    /**  */
    public static final String NPAM1366E = "NPAM1366E";

    /**  */
    public static final String NPAM1367E = "NPAM1367E";

    /**  */
    public static final String NPAM0069E = "NPAM0069E";

    /**  */
    public static final String NPZM0140E = "NPZM0140E";

    /**  */
    public static final String NPAM1364E = "NPAM1364E";

    /**  */
    public static final String NPAM0039E = "NPAM0039E";

    /**  */
    public static final String NPAM1600E = "NPAM1600E";

    /**  */
    public static final String NPZM0118E = "NPZM0118E";

    /**  */
    public static final String NPAM1601E = "NPAM1601E";

    /**  */
    public static final String NPZM0120E = "NPZM0120E";

    /**  */
    public static final String NPZM0121E = "NPZM0121E";

    /**  */
    public static final String NPZM0069E = "NPZM0069E";

    /**  */
    public static final String NPZM0112E = "NPZM0112E";

    /**  */
    public static final String NPAM1424E = "NPAM1424E";

    /**  */
    public static final String NPAM1425E = "NPAM1425E";

    /**  */
    public static final String NPAM1426E = "NPAM1426E";

    /**  */
    public static final String NPAM1427E = "NPAM1427E";

    /**  */
    public static final String NPAM1428E = "NPAM1428E";

    /**  */
    public static final String NPAM1429E = "NPAM1429E";

    /**  */
    public static final String NPAM1430E = "NPAM1430E";

    /**  */
    public static final String NPAM1431E = "NPAM1431E";

    /**  */
    public static final String NPAM1432E = "NPAM1432E";

    /**  */
    public static final String NPAM1433E = "NPAM1433E";

    /**  */
    public static final String NPAM1434E = "NPAM1434E";

    /**  */
    public static final String NPAM1435E = "NPAM1435E";

    /**  */
    public static final String NPAM1436E = "NPAM1436E";

    /**  */
    public static final String NPAM1437E = "NPAM1437E";

    /**  */
    public static final String NPAM1438E = "NPAM1438E";

    /**  */
    public static final String NPAM1439E = "NPAM1439E";

    /**  */
    public static final String NPAM1440E = "NPAM1440E";

    /**  */
    public static final String NPAM1441E = "NPAM1441E";

    /**  */
    public static final String NPAM1442E = "NPAM1442E";

    /**  */
    public static final String NPAM1443E = "NPAM1443E";

    /**  */
    public static final String NPAM1444E = "NPAM1444E";

    /**  */
    public static final String NPAM1445E = "NPAM1445E";

    /**  */
    public static final String NPZM0082E = "NPZM0082E";

    /**  */
    public static final String NPZM0084E = "NPZM0084E";

    /**  */
    public static final String NPZM0123E = "NPZM0123E";

    /**  */
    public static final String NPAM1384E = "NPAM1384E";

    /**  */
    public static final String NPZM0078E = "NPZM0078E";

    /**  */
    public static final String NPZM0065E = "NPZM0065E";

    /**  */
    public static final String NPZM0031E = "NPZM0031E";

    /**  */
    public static final String NPAM1446E = "NPAM1446E";

    /**  */
    public static final String NPAM1447E = "NPAM1447E";

    /**  */
    public static final String NPAM1448E = "NPAM1448E";

    /**  */
    public static final String NPAM1449E = "NPAM1449E";

    /**  */
    public static final String NPAM1450E = "NPAM1450E";

    /**  */
    public static final String NPAM1451E = "NPAM1451E";

    /**  */
    public static final String NPAM1452E = "NPAM1452E";

    /**  */
    public static final String NPAM1453E = "NPAM1453E";

    /**  */
    public static final String NPAM1454E = "NPAM1454E";

    /**  */
    public static final String NPAM1455E = "NPAM1455E";

    /**  */
    public static final String NPAM1456E = "NPAM1456E";

    /**  */
    public static final String NPAM1457E = "NPAM1457E";

    /**  */
    public static final String NPAM1458E = "NPAM1458E";

    /**  */
    public static final String NPAM1459E = "NPAM1459E";

    /**  */
    public static final String NPAM1460E = "NPAM1460E";

    /**  */
    public static final String NPAM1461E = "NPAM1461E";

    /**  */
    public static final String NPAM1462E = "NPAM1462E";

    /**  */
    public static final String NPAM1463E = "NPAM1463E";

    /**  */
    public static final String NPAM1464E = "NPAM1464E";

    /**  */
    public static final String NPAM1465E = "NPAM1465E";

    /**  */
    public static final String NPAM1466E = "NPAM1466E";

    /**  */
    public static final String NPAM1467E = "NPAM1467E";

    /**  */
    public static final String NPAM1468E = "NPAM1468E";

    /**  */
    public static final String NPAM1469E = "NPAM1469E";

    /**  */
    public static final String NPAM1470E = "NPAM1470E";

    /**  */
    public static final String NPAM1471E = "NPAM1471E";

    /**  */
    public static final String NPAM1472E = "NPAM1472E";

    /**  */
    public static final String NPAM1473E = "NPAM1473E";

    /**  */
    public static final String NPAM1474E = "NPAM1474E";

    /**  */
    public static final String NPAM1475E = "NPAM1475E";

    /**  */
    public static final String NPAM1476E = "NPAM1476E";

    /**  */
    public static final String NPZM0232E = "NPZM0232E";

    /**  */
    public static final String NPZM0234E = "NPZM0234E";

    /** There is no Install Base to be processed. */
    public static final String NPZM0265E = "NPZM0265E";

    /** Configuration ID is different from Install Base's. */
    public static final String NPZM0266E = "NPZM0266E";

    /** The machine of entered Config# is allocated with other order. */
    public static final String NPAM1370E = "NPAM1370E";

    /**
     * This configuration includes some components which exist in
     * other location.
     */
    public static final String NPAM1510E = "NPAM1510E";

    // QC#13163 Start
    /**
     * Invalid combination. VND_PMT_TERM_CD & VND_PMT_TERM_DESC_TXT
     */
    public static final String NPZM0293E = "NPZM0293E";

    /**
     * Specified VND_PMT_TERM_DESC_TXT does not exist in VND_PMT_TERM
     * table.
     */
    public static final String NPZM0294E = "NPZM0294E";

    // QC#13163 End

    /**
     * This configuration includes some components which are located
     * at customer.
     */
    public static final String NPAM1511E = "NPAM1511E";

    /**
     * Suppier Item Code is duplicated, Please select another item or review ASL.
     */
    public static final String NPZM0300E = "NPZM0300E";

    /**
     * Hard Allocation could not be executed.
     */
    public static final String NWZM0452E = "NWZM0452E";

    // XML SQL NAME
    /**  */
    public static final String GET_SHIP_TO_CUST = "getShipToCust";

    /**  */
    public static final String GET_VENDOR = "getVendor";

    /**  */
    public static final String GET_VENDOR_BUY_BACK = "getVendorBuyBack";

    /**  */
    public static final String GET_RETAIL_WARE_HOUSE = "getRetailWarehouse";

    /**  */
    public static final String GET_RETAIL_WARE_HOUSE_DEFAULT = "getRetailWarehouseDefault";

    /**  */
    public static final String GET_RTL_WH_SRC_DEST = "getRtlWhSrcDest";

    /**  */
    public static final String GET_MDSE = "getMdse";

    /**  */
    public static final String GET_COA_PROJ_ACCT = "getCoaProjAcct";

    /**  */
    public static final String GET_SVC_MACH_MSTR = "getSvcMachMstr";

    /**  */
    public static final String GET_CONFIG_COMPONENT = "getConfigComponet";

    /**  */
    public static final String GET_RWS_QTY = "getRwsQty";

    /**  */
    public static final String GET_MAX_RWS_REF_NUM = "getMaxRwsRefNum";

    /**  */
    public static final String GET_SERIAL_LIST = "getSerialList";

    /**  */
    public static final String GET_SVC_MACH_MSTR_PK = "getSvcMachMstrPk";

    /**  */
    public static final String GET_SET_COMPONENT = "getSetComponent";

    /**  */
    public static final String GET_SCE_ORD_TP = "getSceOrdTp";

    /**  */
    public static final String GET_PO_MSG_TXT = "getPoMsgTxt";

    /**  */
    public static final String GET_CARR_SVC_LVL_CD = "getCarrSvcLvlCd";

    /**  */
    public static final String GET_FRT_COND_CD = "getFrtCondCd";

    /**  */
    public static final String GET_DS_INVTY_LOC_V = "getDsInvtyLocV";

    /**  */
    public static final String GET_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    // DB COLUMN NAME
    /**  */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** . */
    public static final String PO_ORD_NUM = "PO_ORD_NUM";

    /** . */
    public static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /**  */
    public static final String RGTN_STS_CD = "RGTN_STS_CD";

    /**  */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** . */
    public static final String LOC_NM = "LOC_NM";

    /** . */
    public static final String ACCT_NM = "ACCT_NM";

    /** . */
    public static final String ADDL_LOC_NM = "ADDL_LOC_NM";

    /** . */
    public static final String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /** . */
    public static final String SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /** . */
    public static final String THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /** . */
    public static final String FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /** . */
    public static final String FIRST_REF_CMNT_TXT = "FIRST_REF_CMNT_TXT";

    /** . */
    public static final String SCD_REF_CMNT_TXT = "SCD_REF_CMNT_TXT";

    /** . */
    public static final String CTY_ADDR = "CTY_ADDR";

    /** . */
    public static final String ST_CD = "ST_CD";

    /** . */
    public static final String PROV_NM = "PROV_NM";

    /** . */
    public static final String POST_CD = "POST_CD";

    /** . */
    public static final String CTRY_CD = "CTRY_CD";

    /** . */
    public static final String CNTY_NM = "CNTY_NM";

    /** . */
    public static final String VND_CD = "VND_CD";

    /** . */
    public static final String VND_TP_CD = "VND_TP_CD";

    /** . */
    public static final String INTL_VND_FLG = "INTL_VND_FLG";

    /** . */
    public static final String PRNT_VND_CD = "PRNT_VND_CD";

    /** . */
    public static final String VND_NM = "VND_NM";

    /** . */
    public static final String PRNT_VND_NM = "PRNT_VND_NM";

    /** . */
    public static final String DEAL_CCY_CD = "DEAL_CCY_CD";

    /** . */
    public static final String STD_CCY_CD = "STD_CCY_CD";

    /** . */
    public static final String TRSMT_METH_TP_CD = "TRSMT_METH_TP_CD";

    /** . */
    public static final String FAX_NUM = "FAX_NUM";

    /** . */
    public static final String SEND_PO_EML_ADDR = "SEND_PO_EML_ADDR";

    /** . */
    public static final String RTRN_SHIP_TO_RTL_WH_CD = "RTRN_SHIP_TO_RTL_WH_CD";

    /** . */
    public static final String SRC_RTL_WH_CD = "SRC_RTL_WH_CD";

    /** . */
    public static final String DEST_RTL_WH_CD = "DEST_RTL_WH_CD";

    /** . */
    public static final String DEST_RTL_SWH_CD = "DEST_RTL_SWH_CD";

    /** . */
    public static final String SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /** . */
    public static final String TRX_SRC_TP_CD = "TRX_SRC_TP_CD";

    /** . */
    public static final String FROM_BIZ_APP_LOC_CHK_KEY_ID = "FROM_BIZ_APP_LOC_CHK_KEY_ID";

    /** . */
    public static final String TO_BIZ_APP_LOC_CHK_KEY_ID = "TO_BIZ_APP_LOC_CHK_KEY_ID";

    /** . */
    public static final String ENBL_ASEET_FLG = "ENBL_ASEET_FLG";

    /** . */
    public static final String INVTY_LOC_NM = "INVTY_LOC_NM";

    /** . */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** . */
    public static final String TECH_TOC_CD = "TECH_TOC_CD";

    /** . */
    public static final String MDSE_CD = "MDSE_CD";

    /** . */
    public static final String MDSE_NM = "MDSE_NM";

    /** . */
    public static final String INVTY_CTRL_FLG = "INVTY_CTRL_FLG";

    /** . */
    public static final String RCV_SER_TAKE_FLG = "RCV_SER_TAKE_FLG";

    /** . */
    public static final String MDSE_DESC_SHORT_TEXT = "MDSE_DESC_SHORT_TXT";

    /** . */
    public static final String PRNT_CMPY_SET_MDSE_FLG = "PRNT_CMPY_SET_MDSE_FLG";

    /** . */
    public static final String PO_MATCH_TP_CD = "PO_MATCH_TP_CD";

    /** . */
    public static final String DSCTN_FLG = "DSCTN_FLG";

    /** . */
    public static final String ENBL_ASSET_FLG = "ENBL_ASSET_FLG";

    /** . */
    public static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** . */
    public static final String CREATED = "CREATED";

    /** . */
    public static final String REMOVED = "REMOVED";

    /** . */
    public static final String CUR_LOC_NUM = "CUR_LOC_NUM";

    /** . */
    public static final String SVC_MACH_MAINT_AVAL_FLG = "SVC_MACH_MAINT_AVAL_FLG";

    /** . */
    public static final String SER_NUM = "SER_NUM";

    /** . */
    public static final String PRNT_MDSE_CD = "PRNT_MDSE_CD";

    /** . */
    public static final String MDSE_CMPSN_TP_CD = "MDSE_CMPSN_TP_CD";

    /** . */
    public static final String PO_SUBMT_TS = "PO_SUBMT_TS";

    /** . */
    public static final String COA_ACCT_CD = "COA_ACCT_CD";

    /** . */
    public static final String CHILD_MDSE_CD = "CHILD_MDSE_CD";

    /** . */
    public static final String CHILD_MDSE_QTY = "CHILD_MDSE_QTY";

    /** . */
    public static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /** . */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** . */
    public static final String SVC_MACH_MSTR_LOC_STS_CD = "SVC_MACH_MSTR_LOC_STS_CD";

    /** . */
    public static final String INSTL_BASE_CTRL_FLG = "INSTL_BASE_CTRL_FLG";

    /** . */
    public static final String RWS_QTY = "RWS_QTY";

    /** . */
    public static final String RWS_NUM = "RWS_NUM";

    /** . */
    public static final String RWS_REF_NUM = "RWS_REF_NUM";

    /** . */
    public static final String RWS_DTL_LINE_NUM = "RWS_DTL_LINE_NUM";

    /** . */
    public static final String TRX_CD = "TRX_CD";

    /** . */
    public static final String TRX_RSN_CD = "TRX_RSN_CD";

    /** . */
    public static final String PO_MSG_TP_CD = "PO_MSG_TP_CD";

    /** . */
    public static final String PO_MSG_SEG_ID = "PO_MSG_SEG_ID";

    /** . */
    public static final String PO_MSG_TXT = "PO_MSG_TXT";

    /** . */
    public static final String CARR_CD = "CARR_CD";

    /** . */
    public static final String SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** . */
    public static final String CARR_SVC_LVL_CD = "CARR_SVC_LVL_CD";

    /** . */
    public static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";

    /** . */
    public static final String FRT_COND_CD = "FRT_COND_CD";

    /** . */
    public static final String INBD_OTBD_CD = "INBD_OTBD_CD";

    /** . */
    public static final String PO_ACCT_TP_CD = "PO_ACCT_TP_CD";

    /** . */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** VND_PMT_TERM_DESC_TXT */
    public static final String VND_PMT_TERM_DESC_TXT = "VND_PMT_TERM_DESC_TXT";

    /** . */
    public static final String MDSE_CMPSN_TP_CD_ORDERTAKE = "MDSE_CMPSN_TP_CD_ORDERTAKE";
    
    /** DOMESTIC_VENDOR_PO(02) */
    public static final String DOMESTIC_VENDOR_PO = "02";

    // START 2017/10/20 S.Katsuma QC#21206 ADD
    /**  */
    public static final String PO_STS_CD = "PO_STS_CD";

    /**  */
    public static final String PO_LINE_STS_CD = "PO_LINE_STS_CD";
    // END 2017/10/20 S.Katsuma QC#21206 ADD
    
    //QC#27024 Add Start
    /** . */
    public static final String RWS_STS_CD = "RWS_STS_CD";
    //QC#27024 Add End
    
    //QC#28463 Add Start
    /** . */
    public static final String STAR_TXT = "STAR";
    //QC#28463 Add End

    // QC#55085 ADD START
    /** NPAL1500BL06 */
    public static final String PO_ENTRY_SUBMIT_APL_ID = "NPAL1500BL06";
    // QC#55085 ADD END


}
