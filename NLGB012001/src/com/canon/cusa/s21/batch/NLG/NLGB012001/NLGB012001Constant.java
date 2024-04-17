/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB012001;

/**
 * <pre>
 * Batch Program Constant Class for Translate SOConf/PKT/ASN from WMS.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/20/2013   CSAI            K.Kondo         Create          MW Replace Initial
 * 09/02/2015   CSAI            K.Hayashida     Update          ITG#601006 - [CUSA] S21MW can not process SO confirmation due to memory overflow
 * 09/11/2015   CSAI            K.Hayashida     Update          ITG#591081 - [CUSA] Add search condition to Carrier Mapping Maintenance Screen
 * 04/05/2017   CITS            R.Shimamoto     Update          QC#17981
 * 02/15/2018   CITS            T.Hakodate      Update          QC#22613
 * 02/27/2018   CITS            T.Hakodate      Update          QC#21913
 * 02/06/2019   Fujitsu         T.Ogura         Update          QC#30182
 * 08/07/2019   CITS            T.Wada          Update          QC#52104
 * 09/02/2019   CITS            R.Shimamoto     Update          QC#53233
 * 10/08/2019   CITS            K.Ogino         Update          QC#53872
 * 05/25/2020   Fujitsu         T.Ogura         Update          QC#56612
 * 08/27/2021   CITS            R.Azucena       Update          QC#59036
 * 03/08/2022   CITS            M.Furugoori     Update          QC#59764
 * 11/11/2022   CITS            F.Fadriquela    Update          QC#60772
 *</pre>
 */
public interface NLGB012001Constant {

    // *********************************************************
    // Constants: Fixed length Data value
    // *********************************************************
    /** Interface Record Type : SO Confirmation */
    public static final String INTFC_REC_TP_SO_CONF = "12";

    /** Interface Record Type : PKT Status */
    public static final String INTFC_REC_TP_PKT_STS = "15";

    /** Interface Record Type : ASN */
    public static final String INTFC_REC_TP_ASN = "16";

    /** Fixed length Data Detail Type */
    public static final String INTFC_DTL_TP = "1";

    /** Fixed length Data Detail Type:SO Conf OSHP */
    public static final String INTFC_DTL_TP_OSHP = "1";

    /** Fixed length Data Detail Type:SO Conf SHIP */
    public static final String INTFC_DTL_TP_SHIP = "2";

    /** Fixed length Data Detail Type:SO Conf SERL */
    public static final String INTFC_DTL_TP_SERL = "3";

    /** Fixed length Data Company Code */
    public static final String INTFC_CMPY_CD = "01";

    /** padding Value : Zero */
    public static final String PAD_VAL_ZERO = "0";

    /** S80_STK_STS_CD_S1 */
    public static final String S80_STK_STS_CD_S1 = "S1";

    /** Prefix Value : S80_STK_STS_CD_IF */
    public static final String PFX_S80_STK_STS_CD_IF = "S";

    /** ASN Search Key WMS_CARR_CD */
    public static final String KEY_VAL_WMS_CARR_CD = "EXPT";

    /** Fixed length Data length : LG_ORD_PROC_STS */
    public static final String PDLT_ORD_PROC_STS_IF = "O";

    // *********************************************************
    // Constants: Fixed length Data length
    // *********************************************************
    /** Fixed length Data length : WH_CD */
    public static final int LG_INTFC_WH_CD = 4;

    /** Fixed length Data length : WMS_REC_ID */
    public static final int LG_INTFC_WMS_REC_ID = 7;

    /** Fixed length Data length : INTFC_REC_TP */
    public static final int LG_INTFC_REC_TP = 2;

    /** Fixed length Data length : INTFC_DTL_TP */
    public static final int LG_INTFC_DTL_TP = 1;

    /** Fixed length Data length : INTFC_FILL */
    public static final int LG_INTFC_FILL = 1;

    /** Fixed length Data length : INTFC_CMPY_CD */
    public static final int LG_INTFC_CMPY_CD = 2;

    /** Fixed length Data length : OTBD_ORD_NUM */
    public static final int LG_INTFC_OTBD_ORD_NUM = 15;

    /** Fixed length Data length : OTBD_ORD_LINE_NUM */
    public static final int LG_INTFC_OTBD_ORD_LINE_NUM = 3;

    /** Fixed length Data length : OTBD_ORD_TP_CD */
    public static final int LG_INTFC_OTBD_ORD_TP_CD = 1;

    /** Fixed length Data length : WMS_TRX_DT_TM_TS */
    public static final int LG_INTFC_WMS_TRX_DT_TM_TS = 18;

    /** Fixed length Data length : WMS_OUT_CNTNR_NUM */
    public static final int LG_INTFC_WMS_OUT_CNTNR_NUM = 20;

    /** Fixed length Data length : WMS_MDSE_CD */
    public static final int LG_INTFC_WMS_MDSE_CD = 25;

    /** Fixed length Data length : WMS_STK_STS_CD */
    public static final int LG_INTFC_WMS_STK_STS_CD = 2;

    /** Fixed length Data length : PKT_STS_CD */
    public static final int LG_INTFC_PKT_STS_CD = 4;

    /** Fixed length Data length : PKT_STS_TXT */
    public static final int LG_INTFC_PKT_STS_TXT = 25;

    /** Fixed length Data length : WMS_ORD_STS_CD */
    public static final int LG_INTFC_WMS_ORD_STS_CD = 10;

    /** Fixed length Data length : WMS_ORD_STS_CD_SO_CONF */
    public static final int WMS_ORD_STS_CD_SO_CONF = 1;

    /** Fixed length Data length : PLAN_SHIP_DATE */
    public static final int LG_INTFC_PLAN_SHIP_DATE = 10;

    /** Fixed length Data length : EST_DOCK_DT_TM_TS */
    public static final int LG_INTFC_EST_DOCK_DT_TM_TS = 10;

    /** Fixed length Data length : WMS_CARR_CD */
    public static final int LG_INTFC_WMS_CARR_CD = 10;

    /** Fixed length Data length : WMS_SO_ASN_HDR_ID */
    public static final int LG_INTFC_WMS_SO_ASN_HDR_ID = 10;

    /** Fixed length Data length : WMS_SO_ASN_DTL_ID */
    public static final int LG_INTFC_WMS_SO_ASN_DTL_ID = 7;

    /** Fixed length Data length : WMS_BOL_NUM */
    public static final int LG_INTFC_WMS_BOL_NUM = 40;

    /** Fixed length Data length : MSTR_BOL_NUM */
    public static final int LG_INTFC_MSTR_BOL_NUM = 40;

    /** Fixed length Data length : WMS_SO_ID */
    public static final int LG_INTFC_WMS_SO_ID = 15;

    /** Fixed length Data length : WMS_LOAD_ID */
    public static final int LG_INTFC_WMS_LOAD_ID = 20;

    /** Fixed length Data length : WMS_SO_SHIP_ID */
    public static final int LG_INTFC_WMS_SO_SHIP_ID = 20;

    /** Fixed length Data length : WMS_SHIP_TM_TS */
    public static final int LG_INTFC_WMS_SHIP_TM_TS = 18;

    /** Fixed length Data length : WMS_TOT_WT */
    public static final int LG_INTFC_WMS_TOT_WT = 12;

    /** Fixed length Data length : BOL_NUM */
    public static final int LG_INTFC_BOL_NUM = 15;

    /** Fixed length Data length : CARR_SCAC_CD */
    public static final int LG_CARR_SCAC_CD = 10;

    /** Fixed length Data length : PRO_NUM */
    public static final int LG_PRO_NUM = 30;

    /** Fixed length Data length : WMS_FRT_CHRG_AMT */
    public static final int LG_INTFC_WMS_FRT_CHRG_AMT = 14;

    /** Fixed length Data length : FRT_CHRG_COST_AMT */
    public static final int LG_INTFC_FRT_CHRG_COST_AMT = 14;

    /** Fixed length Data length : CARR_SCAC_CD */
    public static final int LG_INTFC_CARR_SCAC_CD = 4;

    /** Fixed length Data length : WMS_CARR_NM */
    public static final int LG_INTFC_WMS_CARR_NM = 40;

    /** Fixed length Data length : CARR_SVC_CD */
    public static final int LG_INTFC_CARR_SVC_CD = 4;

    /** Fixed length Data length : CARR_PRO_ID */
    public static final int LG_INTFC_CARR_PRO_ID = 30;

    /** Fixed length Data length : REF */
    public static final int LG_INTFC_REF = 30;

    /** Fixed length Data length : WMS_TRNSP_TP_CD */
    public static final int LG_WMS_TRNSP_TP_CD = 4;

    /** Fixed length Data length : WMS_PKG_NUM */
    public static final int LG_WMS_PKG_NUM = 10;

    /** Fixed length Data length : WMS_TRK_NUM */
    public static final int LG_WMS_TRK_NUM = 40;

    /** Fixed length Data length : WMS_UCC128_CD */
    public static final int LG_WMS_UCC128_CD = 20;

    /** Fixed length Data length : WMS_PRNT_UCC128_CD */
    public static final int LG_PRNT_UCC128_CD = 20;

    /** Fixed length Data length : WMS_MDSE_CD */
    public static final int LG_WMS_MDSE_CD = 25;

    /** Fixed length Data length : WMS_STK_STS_CD */
    public static final int LG_ASN_WMS_STK_STS_CD = 4;

    /** Fixed length Data length : WMS_LINE_NUM */
    public static final int LG_WMS_LINE_NUM = 3;

    /** Fixed length Data length : WMS_ORD_QTY */
    public static final int LG_WMS_ORD_QTY = 9;

    /** Fixed length Data length : CUST_ACCT_NUM */
    public static final int LG_CUST_ACCT_NUM = 10;

    /** Fixed length Data length : SER_NUM */
    public static final int LG_INTFC_SER_NUM = 20;

    /** Fixed length Data length : EZINTIME */
    public static final int LG_EZINTIME = 18;

    /** Fixed length Data length : LINE_VOID */
    public static final int LG_LINE_VOID = 1;

    /** Fixed length Data length : SHPG_SVC_LVL_CD */
    public static final int LG_SHPG_SVC_LVL_CD = 2;

    // *********************************************************
    // Constants: Map Key Name
    // *********************************************************
    /** Map Key Name: KEY_OTBD_ORD_NUM */
    public static final String KEY_OTBD_ORD_NUM = "otbdOrdNum";

    /** Map Key Name: KEY_WMS_OUT_CONTNR_NUM */
    public static final String KEY_WMS_OUT_CONTNR_NUM = "wmsOutCntnrNum";

    /** Map Key Name: WMS_TOT_WT */
    public static final String KEY_WMS_TOT_WT = "wmsTotWt";

    // *********************************************************
    // Constants: Message ID
    // *********************************************************
    /** Since physical inventory is in progress, cannot be processed. */
    public static final String NLBM1347E = "NLBM1347E";

    /**
     * Message ID: NLGM0045E The record cannot be registered.
     * Registration Table Name: [@], Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    public static final String NLGM0045E = "NLGM0045E";

    /**
     * Message ID: NLGM0046E The record cannot be updated. Table Name:
     * [@], Key Field Name: [@], Key Value: [@]
     */
    public static final String NLGM0046E = "NLGM0046E";

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

    /**
     * Message ID: Order ID is Closed or Canceled already. Order ID:
     * [@], Table Name: [@], Date Field Name: [@], Date registered:
     * [@].
     */
    public static final String NLGM0053E = "NLGM0053E";

    /**
     * Message ID: No WMS_CARR_CD is in SHIP_VIA master table.
     * Outbound Order ID:[@] WMS_CARR_CD:[@] WMS_ORG_HOST_ID:[@]
     */
    public static final String NLGM0062E = "NLGM0062E";

    /**
     * Message ID: It is not canceled whether all orders about
     * shipment are sent. Outbound Order ID:[@] WH_CD:[@]
     * WMS_SHIP_ID:[@]
     */
    public static final String NLGM0063E = "NLGM0063E";

    /**
     * Message ID: No item of the lines of downloaded SO is received.
     * WMS_INBD_TRX_PK: [@] WMS_SHIP_ID: [@] BOL_NUM: [@]
     */
    public static final String NLGM0064E = "NLGM0064E";

    /**
     * Message ID: No CARR_SCAC_CD or WMS_TRNSP_TP_CD is in SHIP_VIA
     * master table. Outbound Order ID:[@] WMS_CARR_CD:[@]
     * WMS_ORG_HOST_ID:[@]
     */
    public static final String NLGM0065E = "NLGM0065E";

    /**
     * Message ID: The quantity of SO line and the quantity of MW
     * dealings sum total for lines are not in agreement. Outbound
     * Order ID:[@] Line:[@] Total Qty:[@] Ship Qty:[@]
     */
    public static final String NLGM0066E = "NLGM0066E";

    /**
     * Message ID: Since a SO Confirmation verification error is not
     * improved, verification processing is suspended.
     * WMS_INBD_TRX_PK: [@]
     */
    public static final String NLGM0067I = "NLGM0067I";

    /**
     * Message ID: Since a ASN verification error is not improved,
     * verification processing is suspended. WMS_INBD_TRX_PK: [@]
     */
    public static final String NLGM0068I = "NLGM0068I";

    /**
     * Message ID: Tech code does not exist in Tech Master.
     */
    public static final String NLGM0081E = "NLGM0081E";

    /**
     * Message ID: No corresponding data exists for this SO.
     */
    public static final String NLZM2005E = "NLZM2005E";

    /**
     * Message ID: The SHPG_STS of this data is incorrect.
     */
    public static final String NLZM2006E = "NLZM2006E";

    /**
     * Message ID: STK_STS_CD does not exist.
     */
    public static final String NLBM1127E = "NLBM1127E";

    /**
     * Message ID: FROM_STK_STS_CD is abnormal.
     */
    public static final String NLZM2312E = "NLZM2312E";

    /**
     * Message ID: DB error occurred.
     */
    public static final String NLBM1064E = "NLBM1064E";

    /**
     * Message ID: WH_CD does not match.
     */
    public static final String NLZM2007E = "NLZM2007E";

    /**
     * Message ID: SHIP_QTY is abnormal.
     */
    public static final String NLZM2009E = "NLZM2009E";

    /**
     * Message ID: MDSE_CD does not match.
     */
    public static final String NLZM2010E = "NLZM2010E";

    /**
     * Message ID: BOL_NUM,PRO_NUM has not been set.
     */
    public static final String NLZM2011E = "NLZM2011E";

    /**
     * Message ID: SCE_ORD_TP_CD does not exist.
     */
    public static final String NLBM1126E = "NLBM1126E";

    /**
     * Message ID: The process cannot be executed because [@] is [@].
     */
    public static final String NSZM0961E = "NSZM0961E";

    /**
     * Message ID: Target data does not exist.
     */
    public static final String NLBM1063E = "NLBM1063E";

     // QC#22613 T.Hakodate ADD START
    /**
     * Message ID: Failed to update SHPG_ORD_DTL.
     */
    public static final String NLZM2130E = "NLZM2130E";
    // QC#22613 T.Hakodate ADD END

    // QC#21913 T.Hakodate ADD START
    /**
     * Message ID: It failed to insert the [@]. PK [@]
     */
    public static final String NPAM1499E = "NPAM1499E";
    // QC#21913 T.Hakodate ADD END

    // START 05/25/2020 T.Ogura [QC#56612,ADD]
    /**
     * Message ID: Since a SO# that does not exist in S21 is linked, 
     * import was avoided as an error. 
     * WMS_INBD_TRX_PK: [@] Outbound Order ID: [@]
     */
    public static final String NLGM0093E = "NLGM0093E";
    // END   05/25/2020 T.Ogura [QC#56612,ADD]

    // *********************************************************
    // Constants: Unique
    // *********************************************************
    /** Business ID */
    public static final String BUSINESS_ID = "NLGB0120";

    /** Prameter Name: GLBL_CMPY_CD */
    public static final String PRAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Prameter Name: WH_GRP_CD */
    public static final String PRAM_NM_WH_GRP_CD = "Warehouse Group Code";

    /** Prameter Name: PO_PROCESS_CD */
    public static final String PRAM_NM_BAT_PROCESS_CD = "Batch Process Code";

    /** SSM set Key Name: PROC_STS_CD */
    public static final String KEY_PROC_STS_CD = "procStsCd";

    /** SSM set Key Name: ERR_MSG_CD */
    public static final String KEY_ERR_MSG_CD = "errMsgCd";

    /** SSM set Key Name: KEY_WMS_CANC_DT_TM_TS */
    public static final String KEY_WMS_CANC_DT_TM_TS = "wmsCancDtTmTs";

    /** SSM set Key Name: KEY_SHIP_DT_TM_TS */
    public static final String KEY_SHIP_DT_TM_TS = "shipDtTmTs";

    /** SSM set Key Name: KEY_WMS_FRT_CHRG_AMT */
    public static final String KEY_WMS_FRT_CHRG_AMT = "wmsFrtChrgAmt";

    /** Batch Process Key: SO Confirmation */
    public static final String SO_CONF_FLOW = "1";

    /** Batch Key: PKT STATUS */
    public static final String PKT_STS_FLOW = "2";

    /** Batch Process Key: ASN */
    public static final String ASN_FLOW = "3";

    /** Default SO Confirmation Check Interval */
    public static final long DEF_SO_CONF_MIN_INTVL = 1440;

    /** Default ASN Check Interval */
    public static final long DEF_ASN_MIN_INTVL = 1440;

    /** Default Standby time of the data for ASN processing(min) */
    public static final long DEF_PROC_STDBY_ASN_MIN = 10;

    /** Minute Calculated Multiplication value */
    public static final long MIN_CALC_MULT_VAL = 60000;

    /** padding Value : OTBD_ORD_LINE_NUM */
    public static final String PAD_VAL_OTBD_ORD_LINE_NUM_IF = "0";

    /** Current Date Time Format */
    public static final String CUR_DT_TM_FMT = "yyyyMMddHHmmss";

    /** Plus Weight Format */
    public static final String WT_FMT = "00000000000.00";

    /** Minus Weight Format */
    public static final String MINUS_WT_FMT = "0000000000.00";

    /** Quantity Format */
    public static final String QTY_FMT = "0000000.0000";

    /** Quantity Format Minus */
    public static final String MINUS_QTY_FMT = "000000.0000";

    /** PRO_NUM Noshing Constant : NONE */
    public static final String CON_PRO_NUM_NONE = "NONE";

    /** WMS_CNTNR_ID The conditions of a format 000 */
    public static final String WMS_CNTNR_LIKE000 = "000";

    /** WMS_CNTNR_ID The conditions of a format 001 */
    public static final String WMS_CNTNR_LIKE001 = "001";

    /** WMS_CNTNR_ID The conditions of a value */
    public static final String WMS_CNTNR_TOP_VAL = "000";

    /** Substring From length for determine the WMS_CNTNR_ID */
    public static final int LG_CUT_WMS_CNTNR_FROM = 0;

    /** Substring To length for determine the WMS_CNTNR_ID */
    public static final int LG_CUT_WMS_CNTNR_TO = 3;

    /** Substring From length for determine the WMS_REC_ID */
    public static final int LG_CUT_WMS_REC_ID_FROM = 0;

    /** Substring To length for determine the WMS_REC_ID */
    public static final int LG_CUT_WMS_REC_ID_TO = 6;

    /** Substring From length for determine the SER_NUM */
    public static final int LG_CUT_SER_NUM_FROM = 0;

    /** Substring From length for determine the OTBD_ORD_LINE_NUM */
    public static final int LG_CUT_OTBD_ORD_LINE_NUM_FROM = 0;

    /** Substring From length for determine the WMS_REC_ID */
    public static final int LG_CUT_EST_DOCK_DT_TM_TS_FROM = 0;

    /** Substring From length for determine the WMS_REC_ID */
    public static final int LG_CUT_EST_DOCK_DT_TM_TS_TO = 8;

    /** M/W date time format length */
    public static final int LG_MW_DT_TM_TS = 14;

    /**
     * VAR_CHAR_CONST Key Name: Verification interval of SO
     * Confirmation(minute)
     */
    public static final String VLD_INTVL_SO_CONF_MIN = "VLD_INTVL_SO_CONF_MIN";

    /**
     * VAR_CHAR_CONST Key Name: Verification interval of ASN(minute)
     */
    public static final String VLD_INTVL_ASN_MIN = "VLD_INTVL_ASN_MIN";

    /**
     * VAR_CHAR_CONST Key Name: Standby time of the data for ASN
     * processing(min)
     */
    public static final String PROC_STDBY_ASN_MIN = "PROC_STDBY_ASN_MIN";

    // START 2022/03/08 [QC#59764,ADD]
    /**
     * VAR_CHAR_CONST Key Name: Mail Group for NLGB0120
     */
    public static final String NLGB0120_ML_GRP = "NLGB0120_ML_GRP";
    // END 2022/03/08 [QC#59764,ADD]

    /** Hyphen */
    public static final String VAL_HYPHEN = "-";

    // ITG#601006 ADD START
    /** Fetch Size */
    public static int FETCH_SIZE = 1000;
    // ITG#601006 ADD END

    // ITG#591081 ADD START
    /** Mail Map Key */
    public static enum EMAIL_MAP_KEY {
        /** WH_CD */
        WH_CD
        /** WMS_SHIP_ID */
        , WMS_SHIP_ID
        /** BOL_NUM */
        , BOL_NUM
        /** WMS_CARR_CD */
        , WMS_CARR_CD
        /* */;
    }

    /** Mail Group Id Key: From */
    public static String MAIL_GROUP_ID_FROM = "FROM0003";

    /** Mail Key 1: From */
    public static String MAIL_KEY_FROM = "NLB";

    /** Mail Group ID (TO) */
    public static String MAIL_GROUP_ID_TO = "NLGB0120";

    /** Mail template for SO Confirmation error */
    public static String MAIL_TEMPLATE_ID_SO_CONF_ERR = "NLGB0120M001";

    /** Mail template for ASN error */
    public static String MAIL_TEMPLATE_ID_ASN_ERR = "NLGB0120M002";

    /** Message template replace from : WH_CD */
    public static String MT_WH_CD = "WH_CD";

    /** Message template replace from : DTL_MSG */
    public static String MT_DTL_MSG = "DTL_MSG";

    /**
     * Could not get MailGroupAddress. MAIL_GROUP_ID_TO : [@]
     * MAIL_KEY1 : [@]
     */
    public static String NPAM0063E = "NPAM0063E";

    /** The mail template cannot be acquired. <Template ID: [@]> */
    public static String NPAM0064E = "NPAM0064E";

    /** The specified [SO#] is not exists. */
    public static String NLZM2475E = "NLZM2475E";

    /** line feed code */
    public static String LINE_FEED_CODE = "\r\n";

    /** Email detail header for SO Confirmation */
    public static String DTL_MSG_HDR_SO_CONF = "WH    SO#       Carrier Code" + LINE_FEED_CODE + "--------------------------------------------------------";

    /** Email detail header for ASN */
    public static String DTL_MSG_HDR_ASN = "WH    Shipment#             BOL#                                      Carrier Code" //
            + LINE_FEED_CODE + "--------------------------------------------------------------------------------------------------------------";

    /** Blank 1 */
    public static String BLANK_1 = " ";

    /** Blank 2 */
    public static String BLANK_2 = "  ";

    /** Blank 100 */
    public static String BLANK_100 = "                                                                                                    ";

    /** Width of email contents */
    public static int WIDTH_WH_CD = 4;

    /** Width of email contents */
    public static int WIDTH_SO_NUM = 8;

    /** Width of email contents */
    public static int WIDTH_WMS_CARR_CD = 40;

    /** Width of email contents */
    public static int WIDTH_WMS_SHIP_ID = 20;

    /** Width of email contents */
    public static int WIDTH_BOL_NUM = 40;
    // ITG#591081 ADD END

    // QC#53233 Add Start
    public static int TOT_SHIP_WT_LENGTH = 11;

    public static String TOT_SHIP_WT_MAX = "9999999.999";
    // QC#53233 Add End

    /** VAR_CHAR_CONST_NM: NLGB0120_WMS_ORD_TP_LIST */
    public static String NLGB0120_WMS_ORD_TP_LIST = "NLGB0120_WMS_ORD_TP_LIST";

    /** VAR_CHAR_CONST_NM: NLGB0120_VALIDATE_SO_CONF_DATA */
    public static String NLGB0120_VALIDATE_SO_CONF_DATA = "NLGB0120_VALIDATE_SO_CONF_DATA";

    /** VAR_CHAR_CONST_NM: NLGB0120_CALC_FREIGHT_CHARGE */
    public static String NLGB0120_CALC_FREIGHT_CHARGE = "NLGB0120_CALC_FREIGHT_CHARGE";

    /** VAR_CHAR_CONST_NM: NLGB0120_UPD_GETWMSINBDSOHDR */
    public static String NLGB0120_UPD_GETWMSINBDSOHDR = "NLGB0120_UPD_GETWMSINBDSOHDR";

    // START 2019/02/06 T.Ogura [QC#30182,ADD]
    /** VAR_CHAR_CONST_NM: NLGB0120_CARR_CD_IS_NOT_SET(Value to be substituted when CARR_CD is not set.) */
    public static String NLGB0120_CARR_CD_IS_NOT_SET = "NLGB0120_CARR_CD_IS_NOT_SET";
    // END   2019/02/06 T.Ogura [QC#30182,ADD]

    // QC#52104
    public static String NLGB0120_NO_RETRY_ERRMSG_LST = "NLGB0120_NO_RETRY_ERRMSG_LST";

    // QC#53872
    /** Serial# and Mdse Code and Stock Status Code do not exist in machine master. */
    public static String NLGM0087E = "NLGM0087E";

    // QC#54135 Add
    public static final String NLGB0120_SHPG_STS_CD_SKIP_PKT = "NLGB0120_SHPG_STS_CD_SKIP_PKT";

    // START 2021/08/27 R.Azucena[QC#59036, ADD]
    /** RTL_WH_CD+RTL_SWH_CD does not match IB CUR_LOC_NUM. Please process it manually.*/
    public static String NLGM0098E = "NLGM0098E";
    // END 2021/08/27 R.Azucena[QC#59036, ADD]

    // START 2022/11/11 F.Fadriquela [QC#60772, ADD]
    /** Stock status of the specified Serial number is different from IB. */
    public static String NLZM2414E = "NLZM2414E";
    // END 2022/11/11 F.Fadriquela [QC#60772, ADD]
}
