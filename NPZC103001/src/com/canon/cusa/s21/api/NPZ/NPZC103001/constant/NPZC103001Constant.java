/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC103001.constant;

/**
 * <pre>
 * PR Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2014/06/03   Hitachi         T.Kanasaka      Update          CSA-279_288
 * 2015/10/27   CITS            H.Sugawara      Update          CSA
 * 2016/03/29   CSAI            K.Lee           Update          QC#6259
 * 2016/11/21   CITS            K.Ogino         Update          QC#16064
 * 2016/11/21   CITS            K.Ogino         Update          QC#16036
 * 2016/11/22   CITS            K.Ogino         Update          QC#8295
 * 2016/12/19   CITS            K.Ogino         Update          QC#6562
 * 2017/02/21   CITS            Y.IWASAKI       Update          QC#17487
 * 2017/02/24   CITS            R.Shimamoto     Update          QC#17049
 * 2017/03/03   CITS            R.Shimamoto     Update          QC#17395
 * 2017/08/31   CITS            S.Endo          Update          Sol#406(QC#19243)
 * 2017/09/04   CITS            K.Kameoka       Update          Sol#369(QC#19243)
 * 2017/11/21   CITS            K.Ogino         Update          QC#22212
 * 2017/12/25   CITS            T.Wada          Update          QC#22747
 * 2018/05/23   CITS            T.Tokutomi      Update          QC#2366
 * 2018/08/23   CITS            T.Tokutomi      Update          QC#27660
 * 2018/11/28   Fujitsu         S.Takami        Update          QC#28735
 * 05/20/2019   CITS            K.Ogino         Update          QC#50072
 * 07/11/2019   CITS            M.Naito         Update          QC#51148
 * 04/08/2020   CITS            M.Furugoori     Update          QC#56406
 * 02/06/2023   CITS            A.Cullano       Update          QC#60993
 * 02/08/2023   Hitachi         T.Kuroda        Update          QC#60966
 * 2023/03/31   Hitachi         E.Watabe        Update          QC#61210
 * </pre>
 */
public class NPZC103001Constant {

    /**
     * Mode Create
     */
    public static final String MODE_CREATE = "1";

    /**
     * Mode Update
     */
    public static final String MODE_UPDATE = "2";

    /**
     * Mode Cancel
     */
    public static final String MODE_CANCEL = "3";

    /**
     * Mode TP Ship Conf
     */
    public static final String MODE_TP_SHIP_CONF = "4";

    /**
     * Mode Close
     */
    public static final String MODE_CLOSE = "5";

    /**
     * Event Submit
     */
    public static final String EVENT_SUBMIT = "1";

    /**
     * Event Insourcing
     */
    public static final String EVENT_INSOURCING = "2";

    /**
     * Event Approval
     */
    public static final String EVENT_APPROVAL = "3";

    /**
     * Event Hold
     */
    public static final String EVENT_HOLD = "4";

    /**
     * Event Order Release
     */
    public static final String EVENT_ORDER_RELEASE = "5";

    /**
     * Event Shipped
     */
    public static final String EVENT_SHIPPED = "6";

    /**
     * Event Received
     */
    public static final String EVENT_RECEIVED = "7";

    /**
     * Event Update
     */
    public static final String EVENT_UPDATE = "8";

    // QC#17049 Add.
    /**
     * Event ASN
     */
    public static final String EVENT_ASN = "9";

    /**
     * NEW_ORDER
     */
    public static final String NEW_ORDER = "1";

    /**
     * UPDATE_ORDER
     */
    public static final String UPDATE_ORDER = "2";

    /**
     * PRCH_REQ_NUM_SQ
     */
    public static final String PRCH_REQ_NUM_SQ = "PRCH_REQ_NUM_SQ";

    /**
     * PRCH_REQ_SER_SQ
     */
    public static final String PRCH_REQ_SER_SQ = "PRCH_REQ_SER_SQ";

    /**
     * PRCH_REQ_BIZ_PROC_LOG_SQ
     */
    public static final String PRCH_REQ_BIZ_PROC_LOG_SQ = "PRCH_REQ_BIZ_PROC_LOG_SQ";

    /**
     * PRCH_REQ_NUM_SQ_DIGIT
     */
    public static final int PRCH_REQ_NUM_SQ_DIGIT = 8;

    /**
     * PRCH_REQ_SER_SQ_DIGIT
     */
    public static final int PRCH_REQ_SER_SQ_DIGIT = 28;

    /**
     * PRCH_REQ_BIZ_PROC_LOG_SQ_DIGIT
     */
    public static final int PRCH_REQ_BIZ_PROC_LOG_SQ_DIGIT = 28;

    /**
     * LINE_NUM_LENGTH
     */
    public static final int LINE_NUM_LENGTH = 3;

    /**
     * INVTY_ORD_CMNT_LENGTH
     */
    public static final int INVTY_ORD_CMNT_LENGTH = 64;

    /**
     * TM_LENGTH
     */
    public static final int TM_LENGTH = 4;

    /**
     * DT_LENGTH
     */
    public static final int DT_LENGTH = 8;

    /**
     * DT_TM_TS_LENGTH
     */
    public static final int DT_TM_TS_LENGTH = 14;

    /**
     * PSN_CD_LENGTH
     */
    public static final int PSN_CD_LENGTH = 8;

    /**
     * SECOND_CMNT_LENGTH
     */
    public static final int SECOND_CMNT_LENGTH = 2;

    /**
     * THIRD_CMNT_LENGTH
     */
    public static final int THIRD_CMNT_LENGTH = 3;

    /**
     * TECH_NTC_ML_PROC_EXCLUDED
     */
    public static final String TECH_NTC_ML_PROC_EXCLUDED = "8";

    /**
     * PREMIUM_RUSH_JUDGE_TIME
     */
    public static final String PREMIUM_RUSH_JUDGE_TIME = "1800";

    /**
     * AWATING_APPROVAL_JUDGE_TIME
     */
    public static final String AWATING_APPROVAL_JUDGE_TIME = "0830";

    /**
     * BLANK
     */
    public static final String BLANK = "";

    /**
     * DELIMITER
     */
    public static final String DELIMITER = ",";

    /**
     * DATE_PATTERN
     */
    public static final String DATE_PATTERN = "yyyyMMdd";

    /**
     * TIME_PATTERN
     */
    public static final String TIME_PATTERN = "HHmmss";

    /**
     * DTTMTS_PATTERN
     */
    public static final String DTTMTS_PATTERN = "yyyyMMddHHmmss";

    /**
     * TIMESTAMP_PATTERN
     */
    public static final String TIMESTAMP_PATTERN = "yyyyMMddHHmmssSSS";

    /**
     * ALL
     */
    public static final String ALL = "ALL";

    /**
     * LINE
     */
    public static final String LINE = "LINE";

    // START 2023/02/06 A.Cullano [QC#60993, ADD]
    /**
     * MNX
     */
    public static final String MNX = "MNX";
    // END 2023/02/06 A.Cullano [QC#60993, ADD]

    /**
     * PR_CRAT_SYSTEM_USER
     */
    public static final String PR_CRAT_SYSTEM_USER = "PR_CRAT_SYSTEM_USER";

    /**
     * PR_PREAPVL_WH_OWNR_CD
     */
    public static final String PR_PREAPVL_WH_OWNR_CD = "PR_PREAPVL_WH_OWNR_CD";

    /**
     * TP_SHIP_CONF_CANC_LINE_TP
     */
    public static final String TP_SHIP_CONF_CANC_LINE_TP = "TP_SHIP_CONF_CANC_LINE_TP";
    
    /**
     * DB Param Name: DS Condition Constant Group Id
     */
    public static final String DB_PARAM_DS_COND_CONST_GRP_ID = "dsCondConstGrpId";
    
    /**
     * Group ID for Constant Value
     */
    public static final String DS_COND_CONST_GRP_ID = "NPZC1170";
    
    /**
     * DB Column Name: DS_COND_CONST_VAL_TXT_07
     */
    public static final String DB_COLUMN_DS_COND_CONST_VAL_TXT_07 = "DS_COND_CONST_VAL_TXT_07";

    /**
     * "Mode" must be entered.
     */
    public static final String NPZM0093E = "NPZM0093E";

    /**
     * "Event" must be entered.
     */
    public static final String NPZM0094E = "NPZM0094E";

    /**
     * Global Company Code is required.
     */
    public static final String NPZM0001E = "NPZM0001E";

    /**
     * "Process Date" has not been configured.
     */
    public static final String NPZM0059E = "NPZM0059E";

    /**
     * "Purchase Requisition Information" must be entered.
     */
    public static final String NPZM0095E = "NPZM0095E";

    /**
     * Input Parameter "Purchase Requisition Number" is a mandatory
     * field.
     */
    public static final String NPZM0060E = "NPZM0060E";

    /**
     * Input Parameter "Purchase Requisition Line Number" is a
     * mandatory field.
     */
    public static final String NPZM0061E = "NPZM0061E";

    /**
     * Input Parameter "Purchase Requisition Status Code" is a
     * mandatory field.
     */
    public static final String NPZM0077E = "NPZM0077E";

    /**
     * Input Parameter "Procurement Type Code" is a mandatory field.
     */
    public static final String NPZM0062E = "NPZM0062E";

    /**
     * "Purchase Requisition Type Code" must be entered.
     */
    public static final String NPZM0096E = "NPZM0096E";

    /**
     * Merchandise Code is required.
     */
    public static final String NPZM0020E = "NPZM0020E";

    /**
     * SO Number is required.
     */
    public static final String NPZM0028E = "NPZM0028E";

    /**
     * Input Parameter "Purchase Requisition Quantity" is a mandatory
     * field.
     */
    public static final String NPZM0064E = "NPZM0064E";

    /**
     * Input Parameter "To Location Code" is a mandatory field.
     */
    public static final String NPZM0046E = "NPZM0046E";

    /**
     * Input Parameter "Ship To Customer Code" is a mandatory field.
     */
    public static final String NPZM0066E = "NPZM0066E";

    /**
     * Input Parameter "Purchase Requisition Source Type Code" is a
     * mandatory field.
     */
    public static final String NPZM0076E = "NPZM0076E";

    /**
     * "FSR Number" must be entered.
     */
    public static final String NPZM0097E = "NPZM0097E";

    /**
     * A valid To Location Code does not exist in Master.
     */
    public static final String NPZM0047E = "NPZM0047E";

    /**
     * A valid Merchandise Code does not exist in Master.
     */
    public static final String NPZM0045E = "NPZM0045E";

    /**
     * Vendor Code does not exist in Master.
     */
    public static final String NPZM0043E = "NPZM0043E";

    /**
     * "From Location Code" does not exist in master.
     */
    public static final String NPZM0098E = "NPZM0098E";

    /**
     * Failed to register to Purchase Requisition Header.
     */
    public static final String NPZM0099E = "NPZM0099E";

    /**
     * Failed to update to Purchase Requisition Header.
     */
    public static final String NPZM0100E = "NPZM0100E";

    /**
     * Mode is invalid.
     */
    public static final String NPZM0101E = "NPZM0101E";

    /**
     * Event is invalid.
     */
    public static final String NPZM0102E = "NPZM0102E";

    /**
     * "Ship To Location Name" must be entered.
     */
    public static final String NPZM0103E = "NPZM0103E";

    /**
     * "Ship To First Line Address" must be entered.
     */
    public static final String NPZM0104E = "NPZM0104E";

    /**
     * "Ship to City Address" must be entered.
     */
    public static final String NPZM0105E = "NPZM0105E";

    /**
     * "Ship to State Code" must be entered.
     */
    public static final String NPZM0106E = "NPZM0106E";

    /**
     * "Ship to Postal Code" must be entered.
     */
    public static final String NPZM0107E = "NPZM0107E";

    /**
     * PDD_DT cannot be obtained.
     */
    public static final String NPZM0143W = "NPZM0143W";

    /**
     * NPZM0166E: Input Parameter
     * "Purchase Requisition Record Type Code" is a mandatory field.
     */
    public static final String NPZM0166E = "NPZM0166E";

    /**
     * NPZM0167E: Input Parameter "Ship To Customer Code" is a
     * mandatory field.
     */
    public static final String NPZM0167E = "NPZM0167E";

    /**
     * NPZM0168E: Input Parameter "Purchase Requisition Approval Date"
     * is a mandatory field.
     */
    public static final String NPZM0168E = "NPZM0168E";

    /**
     * NPZM0169E: Input Parameter
     * "Purchase Requisition Approval By Name" is a mandatory field.
     */
    public static final String NPZM0169E = "NPZM0169E";

    /**
     * NPZM0170E: Input Parameter "Purchase Requisition Freeze Flag"
     * is a mandatory field.
     */
    public static final String NPZM0170E = "NPZM0170E";

    /**
     * NPZM0171E: Input Parameter
     * "Purchase Requisition Line Sub Number" is a mandatory field.
     */
    public static final String NPZM0171E = "NPZM0171E";

    /**
     * NPZM0172E: Input Parameter
     * "Purchase Requisition Line Type Code" is a mandatory field.
     */
    public static final String NPZM0172E = "NPZM0172E";

    /**
     * NPZM0173E: Input Parameter "Source Inventory Location Code" is
     * a mandatory field.
     */
    public static final String NPZM0173E = "NPZM0173E";

    /**
     * NPZM0174E: Input Parameter "Purchase Requisition Released Qty"
     * is a mandatory field.
     */
    public static final String NPZM0174E = "NPZM0174E";

    /**
     * NPZM0182E: Input Parameter "Purchase Requisition Create By Person Code"
     * is a mandatory field.
     */
    public static final String NPZM0182E = "NPZM0182E";

    /**
     * NPZM0183E: Input Parameter "FSR Number" or "Service Task Number"
     * is a mandatory field.
     */
    public static final String NPZM0183E = "NPZM0183E";

    /**
     * NPZM0184E: Input Parameter "Request Technician TOC Code"
     * is a mandatory field.
     */
    public static final String NPZM0184E = "NPZM0184E";

    /**
     * NPZM0185E: Input Parameter "Destination Inventory Location Code"
     * is a mandatory field.
     */
    public static final String NPZM0185E = "NPZM0185E";

    /**
     * NPZM0186E: Input Parameter "Purchase Requisition Approval Status Code"
     * is a mandatory field.
     */
    public static final String NPZM0186E = "NPZM0186E";

    /**
     * NPZM0187E: Input Parameter "Purchase Requisition Line Status Code"
     * is a mandatory field.
     */
    public static final String NPZM0187E = "NPZM0187E";

    /**
     * NPZM0188E: Input Parameter "Purchase Requisition Released Status Code"
     * is a mandatory field.
     */
    public static final String NPZM0188E = "NPZM0188E";

    /**
     * NPZM0189E: Input Parameter "RWS Number"
     * is a mandatory field.
     */
    public static final String NPZM0189E = "NPZM0189E";

    /**
     * NPZM0190E: Input Parameter "Transaction Reference Number"
     * is a mandatory field.
     */
    public static final String NPZM0190E = "NPZM0190E";

    /**
     * NPZM0195E: Input Parameter "Ship Quantity"
     * is a mandatory field.
     */
    public static final String NPZM0195E = "NPZM0195E";

    /**
     * NPZM0233E: Failed to register "PRCH_REQ_BIZ_PROC_LOG".
     */
    public static final String NPZM0233E = "NPZM0233E";

    /**
     * NPZM0235E: Could not get Purchasing Group of Request Item.
     */
    public static final String NPZM0235E = "NPZM0235E";

    /**
     * The entered Service Request Information does not exist.
     */
    public static final String NPZM0263E = "NPZM0263E";

    /**
     * Failed to register to Purchase Requisition Detail.
     */
    public static final String NPZM0289E = "NPZM0289E";

    /**
     * Failed to register to Purchase Requisition Serial.
     */
    public static final String NPZM0290E = "NPZM0290E";

    /**
     * Failed to update to Purchase Requisition Detail.
     */
    public static final String NPZM0291E = "NPZM0291E";

    // QC#2366 Add.
    /**
     * Shipping address for hazard material was not found.
     */
    public static final String NPZM0303E = "NPZM0303E";

    // QC#2366 Add.
    /**
     * If the order containing hazmat items and the preferred carrier
     * is FedEx, the ship to customer code must be Technician hazmat
     * code.
     */
    public static final String NPZM0304W = "NPZM0304W";

    // QC#2366 Add.
    /**
     * If the order containing hazmat items, this shipping service
     * level must be Ground or Will Call.
     */
    public static final String NPZM0305W = "NPZM0305W";

    // QC#51148 Add.
    /**
     * The target "Purchase Request" cannot be found.
     */
    public static final String NPZM0311E = "NPZM0311E";

    // START 04/08/2020 [QC#56406,ADD]
    /**
     * If the order containing hazmat items and the preferred carrier
     * is FedEx, the ship to customer code must be Technician hazmat
     * code.
     */
    public static final String NPZM0313E = "NPZM0313E";
    // END   04/08/2020 [QC#56406,ADD]

    /**
     * COL_ORIG_RQST_MDSE_CD
     */
    public static final String COL_ORIG_RQST_MDSE_CD = "origRqstMdseCd";

    /**
     * COL_PRCH_REQ_CRAT_TZ
     */
    public static final String COL_PRCH_REQ_CRAT_TZ = "prchReqCratTz";

    /**
     * COL_PRCH_REQ_CRAT_DISP_DT_TM_TS
     */
    public static final String COL_PRCH_REQ_CRAT_DISP_DT_TM_TS = "prchReqCratDispDtTmTs";

    /**
     * COL_VND_DROP_SHIP_FLG
     */
    public static final String COL_VND_DROP_SHIP_FLG = "vndDropShipFlg";

    /**
     * COL_SCE_ORD_TP_CD
     */
    public static final String COL_SCE_ORD_TP_CD = "sceOrdTpCd";

    /**
     * COL_TRX_SRC_TP_CD
     */
    public static final String COL_TRX_SRC_TP_CD = "trxSrcTpCd";

    /**
     * COL_THIS_MTH_FOB_COST_AMT
     */
    public static final String COL_THIS_MTH_FOB_COST_AMT = "thisMthFobCostAmt";

    /**
     * COL_PROCR_TP_CD
     */
    public static final String COL_PROCR_TP_CD = "procrTpCd";

    /**
     * COL_SRC_LOC_CD
     */
    public static final String COL_SRC_LOC_CD = "srcLocCd";

    /**
     * COL_SRC_RTL_WH_CD
     */
    public static final String COL_SRC_RTL_WH_CD = "srcRtlWhCd";

    /**
     * COL_SRC_RTL_SWH_CD
     */
    public static final String COL_SRC_RTL_SWH_CD = "srcRtlSwhCd";

    /**
     * COL_VND_CD
     */
    public static final String COL_VND_CD = "vndCd";

    /**
     * COL_PRNT_VND_CD
     */
    public static final String COL_PRNT_VND_CD = "prntVndCd";

    /**
     * COL_ASL_DTL_PK
     */
    public static final String COL_ASL_DTL_PK = "aslDtlPk";

    /**
     * COL_VND_UOM_CD
     */
    public static final String COL_VND_UOM_CD = "vndUomCd";

    /**
     * COL_UNIT_PRC_AMT
     */
    public static final String COL_UNIT_PRC_AMT = "unitPrcAmt";

    /**
     * INVTY_LOC_CD
     */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /**
     * RTL_WH_CD
     */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /**
     * RTL_SWH_CD
     */
    public static final String RTL_SWH_CD = "RTL_SWH_CD";

    /**
     * VND_PMT_TERM_CD
     */
    public static final String VND_PMT_TERM_CD = "VND_PMT_TERM_CD";

    /**
     * VND_PMT_TERM_DESC_TXT
     */
    public static final String VND_PMT_TERM_DESC_TXT = "VND_PMT_TERM_DESC_TXT";

    /**
     * LOC_NM
     */
    public static final String LOC_NM = "LOC_NM";

    /**
     * ADDL_LOC_NM
     */
    public static final String ADDL_LOC_NM = "ADDL_LOC_NM";

    /**
     * FIRST_LINE_ADDR
     */
    public static final String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /**
     * SCD_LINE_ADDR
     */
    public static final String SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /**
     * THIRD_LINE_ADDR
     */
    public static final String THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /**
     * FRTH_LINE_ADDR
     */
    public static final String FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /**
     * FIRST_REF_CMNT_TXT
     */
    public static final String FIRST_REF_CMNT_TXT = "FIRST_REF_CMNT_TXT";

    /**
     * SCD_REF_CMNT_TXT
     */
    public static final String SCD_REF_CMNT_TXT = "SCD_REF_CMNT_TXT";

    /**
     * CTY_ADDR
     */
    public static final String CTY_ADDR = "CTY_ADDR";

    /**
     * ST_CD
     */
    public static final String ST_CD = "ST_CD";

    /**
     * PROV_NM
     */
    public static final String PROV_NM = "PROV_NM";

    /**
     * CNTY_NM
     */
    public static final String CNTY_NM = "CNTY_NM";

    /**
     * POST_CD
     */
    public static final String POST_CD = "POST_CD";

    /**
     * CTRY_CD
     */
    public static final String CTRY_CD = "CTRY_CD";

    /**
     * BILL_TO_CUST_CD
     */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /**
     * SELL_TO_CUST_CD
     */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /**
     * WH_OWNR_CD
     */
    public static final String WH_OWNR_CD = "WH_OWNR_CD";

    /**
     * PRCH_REQ_LINE_TP_CD
     */
    public static final String PRCH_REQ_LINE_TP_CD = "PRCH_REQ_LINE_TP_CD";

    /**
     * TO_STK_STS_CD
     */
    public static final String TO_STK_STS_CD = "TO_STK_STS_CD";

    /**
     * SUM_PRCH_REQ_QTY
     */
    public static final String SUM_PRCH_REQ_QTY = "SUM_PRCH_REQ_QTY";

    /**
     * SUM_RWS_PUT_AWAY_QTY
     */
    public static final String SUM_RWS_PUT_AWAY_QTY = "SUM_RWS_PUT_AWAY_QTY";

    // QC#17395 Start.
    /** VAR_CHAR_CONST Key : WH_OWNR_CD */
    public static final String HAZMAT_WH_OWNR_CD = "NLBB0020_HAZMAT_WH_OWNR_CD";

    /**
     * HAZ_MAT_FLG
     */
    public static final String HAZ_MAT_FLG = "HAZ_MAT_FLG";
    // QC#17395 End.
    //08/31/2017 CITS S.Endo Add Sol#406(QC#19243) START
    /** LOC_CD_LENGTH */
    public static final int LOC_CD_LENGTH = 6;
    /** WH_CD_LENGTH */
    public static final int WH_CD_LENGTH = 3;
    //08/31/2017 CITS S.Endo Add Sol#406(QC#19243) END

    //Sol#369(QC#19243) K.Kameoka Update Start
    // =================================================
    // e-mail
    // =================================================
    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_ID = "NPZC1030M001";

    /** Mail Template ID Add QC#22210 */
    public static final String MAIL_TEMPLATE_ID_PREMIUM_RUSH = "NPZC1030M002";

    /** Mail Group ID(To) */
    public static final String MAIL_TO_GROUP_ID = "NPZC103001";

    /** Mail Group ID(Cc1) */
    public static final String MAIL_CC1_GROUP_ID = "NPZC103002";

    /** Mail Group ID(Cc2) */
    public static final String MAIL_CC2_GROUP_ID = "NPZC103002";

    /** Mail Group ID(From) */
    public static final String MAIL_FROM_GROUP_ID = "FROM0012";

    /** Mail Language */
    public static final String ML_LANG = "en";

    /** Mail Language Code */
    public static final String ML_LANG_CD = "ISO-8859-1";

    /** . */
    public static final String EMAIL_PARAM_TECH_TOC_CD = "rqstTechTocCd";

    /** rqstRcvDt. */
    public static final String EMAIL_PARAM_RQST_RCV_DT = "rqstRcvDt";

    // START 2023/02/08 T.Kuroda [QC#60966, ADD]
    /** rqstShipDt. */
    public static final String EMAIL_PARAM_RQST_SHIP_DT = "rqstShipDt";
    // END   2023/02/08 T.Kuroda [QC#60966, ADD]

    /** shpgSvcLvlCd. */
    public static final String EMAIL_PARAM_SHPG_SVC_LVL_CD = "shpgSvcLvlCd";

    /** . */
    public static final String EMAIL_PARAM_SHIP_TO_LOC_NM = "shipToLocNm";

    /** shipToFirstLineAddr. */
    public static final String EMAIL_PARAM_SHIP_TO_FIRST_LINE_ADDR = "shipToFirstLineAddr";

//    /** shipToScdLineAddr. */
//    public static final String EMAIL_PARAM_SHIP_TO_SCD_LINE_ADDR = "shipToScdLineAddr";
//
//    /** shipToThirdLineAddr. */
//    public static final String EMAIL_PARAM_SHIP_TO_THIRD_LINE_ADDR = "shipToThirdLineAddr";
//
//    /** shipToFrthLineAddr. */
//    public static final String EMAIL_PARAM_SHIP_TO_FRTH_LINE_ADDR = "shipToFrthLineAddr";

    /** . */
    public static final String EMAIL_PARAM_PRCH_REQ_NUM = "prchReqNum";

    /** . */
    public static final String EMAIL_PARAM_CITY_STATE_POST = "cityStatePost";

    /** . */
    public static final String EMAIL_PARAM_TECH_CELL_PHO_NUM = "techCellPhoNum";

    /** . */
    public static final String EMAIL_PARAM_EML_ADDR = "emlAddr";

    /** . */
    public static final String EMAIL_PARAM_MESSAGE = "message";

    /** It failed to register an email. */
    public static final String NPZM0161E = "NPZM0161E";

    /** . */
    public static final String EMAIL_PARAM_MATERIAL = "Material:";

    /** . */
    public static final String EMAIL_PARAM_QTY = "Qty:";

    /** Request Tech Toc Name 2018/11/28 QC#28735 Add */
    public static final String EMAIL_PARAM_TECH_TOC_NM = "rqstTechTocNm";

    /** .*/
    public static final String ASTERISK = "*";

    /** .*/
    public static final String RQST_RCV_DT_DATE_FORMAT = "yyyyMMdd";

    // START 2023/02/08 T.Kuroda [QC#60966, ADD]
    /** .*/
    public static final String RQST_SHIP_DT_DATE_FORMAT = "yyyyMMdd";
    // END   2023/02/08 T.Kuroda [QC#60966, ADD]

    /** .*/
    public static final String EMAIL_DATE_FORMAT = "MM/dd/yyyy";

    /** .*/
    public static final String COL_EMAIL = "EML_ADDR";

    /** .*/
    public static final String COL_PHO_NUM = "TECH_CELL_PHO_NUM";

    /** .*/
    public static final String COL_TEL_NUM = "TEL_NUM";

    /** Add QC#22212 */
    public static final String COL_TECH_MBL_MSG_ADDR = "TECH_MBL_MSG_ADDR";

    /** Tech Toc Name 2018/11/28 QC#28735 Add */
    public static final String COL_TECH_TOC_NM = "TECH_NM";

    /** .*/
    public static final String CHO = "CHO";

    /** .*/
    public static final String COL_LOC_NM = "LOC_NM";

    /** .*/
    public static final String COL_FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /** .*/
    public static final String COL_SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /** .*/
    public static final String COL_THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /** .*/
    public static final String COL_FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /** .*/
    public static final String COL_CTY_ADDR = "CTY_ADDR";

    /** .*/
    public static final String COL_ST_CD = "ST_CD";

    /** .*/
    public static final String COL_POST_CD = "POST_CD";

    /** .*/
    public static final String SPACE_STRING = " ";
    //Sol#369(QC#19243) K.Kameoka Update End
    
    // QC#22747 Add Start
    /** MIN_START_DATE_VAL. */
    public static final String MIN_START_DATE_VAL = "10000101";

    /** MAX_START_DATE_VAL. */
    public static final String MAX_START_DATE_VAL = "99991231";
    // QC#22747 Add End
    
    // QC#27660 Add.
    /** LENGTH_PRCH_REQ_LINE_CMNT_TXT */
    public static final int LENGTH_PRCH_REQ_LINE_CMNT_TXT = 240;

    // QC#50072
    /** . */
    public static final String EMAIL_CTAC_PSN_NM = "ctacPsnNm";
    
    // START 2023/03/31 E.Watabe [QC#61210, ADD]
    public static final String CUSA_VAR_CHAR_CONST_NM = "NPZC1030_CUSA_PRNT_VND_CD";
    // END 2023/03/31 E.Watabe [QC#61210, ADD]
}
