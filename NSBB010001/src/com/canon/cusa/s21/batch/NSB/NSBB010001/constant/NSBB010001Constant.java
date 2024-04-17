/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB010001.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/19/2013   Fujitsu         S.Nakai         Create          N/A
 * 12/15/2015   Hitachi         T.Harada        Update          CSA
 * 02/16/2016   Hitachi         A.Kohinata      Update          QC3373
 * 02/23/2016   Hitachi         T.Aoyagi        Update          QC2885
 * 03/28/2016   Hitachi         K.Kishimoto     Update          QC#3829
 * 07/05/2016   Hitachi         T.Kanasaka      Update          QC#9438
 * 08/05/2016   Hitachi         T.Tomita        Update          QC#12836
 * 11/02/2016   Hitachi         K.Kishimoto     Update          QC#12540
 * 11/10/2016   Hitachi         K.Kishimoto     Update          QC#15789
 * 02/22/2017   Hitachi         K.Kitachi       Update          QC#17515
 * 08/07/2017   Hitachi         M.Kidokoro      Update          QC#20073
 * 2017/11/29   Hitachi         K.Kojima        Update          QC#21918
 * 2018/01/24   Hitachi         T.Tomita        Update          QC#23684
 * 2018/05/31   Hitachi         K.Kojima        Update          QC#23685
 * 2018/07/10   Hitachi         K.Kitachi       Update          QC#22788
 * 2018/07/25   CITS            M.Naito         Update          QC#13309
 * 2018/08/08   Hitachi         A.Kohinata      Update          QC#27329-3
 * 2019/03/18   Hitachi         S.Kitamura      Update          QC#30736
 * 2019/04/03   Hitachi         A.Kohinata      Update          QC#31037
 * 2020/07/13   Hitachi         K.Kitachi       Update          QC#57304
 * 2023/01/20   Hitachi         R.Onozuka       Update          QC#60823
 * </pre>
 */
public final class NSBB010001Constant {

    /** ****************************************** */
    /** Message                                    */
    /** ****************************************** */
    /** business_id : NSBB0100 */
    public static final String BUSINESS_ID = "NSBB0100";

    // START 2016/02/16 A.Kohinata [QC3373, ADD]
    /** program_id : NSBB010001 */
    public static final String PROGRAM_ID = BUSINESS_ID + "01";
    // END 2016/02/16 A.Kohinata [QC3373, ADD]

    /** There is no parameter in [@]. */
    public static final String NSBM0032E = "NSBM0032E";

    /** @ doesn't exist in the VAR_CHAR_CONST. */
    public static final String NSBM0069E = "NSBM0069E";

    /** The target data does not exist. */
    public static final String NSBM0070I = "NSBM0070I";

    /** Could not get the [@]. */
    public static final String NSBM0071E = "NSBM0071E";

    /** The target data for the update does not exist. Table Name: [@], Key Field Name: [@], Key Value: [@] */
    public static final String NSBM0072E = "NSBM0072E";

    /** The record cannot be updated. Table Name: [@], Key Field Name:[@], Key Value: [@] */
    public static final String NSBM0073E = "NSBM0073E";

    /** This data has been updated by another user.Table Name: [@], Key Field Name: [@], Key Value: [@] */
    public static final String NSBM0075E = "NSBM0075E";

    /** Since the Send-to information was not available, it did not send the mail.<Mail Group ID: [@]> */
    public static final String NSBM0076E = "NSBM0076E";

    /** The e-mail template <template ID: [@]> cannot be obtained. */
    public static final String NSBM0077E = "NSBM0077E";

    /** The Send-from information was not available.<Mail Group ID: [@], Mail Key1: [@]> */
    public static final String NSBM0078E = "NSBM0078E";

    /** [@] already exists. */
    public static final String NSBM0016E = "NSBM0016E";

    // START 2019/03/18 S.Kitamura [QC#30736,ADD]
    /** This job is abended because today is month end date. */
    public static final String NSBM0189E = "NSBM0189E";
    // END 2019/03/18 S.Kitamura [QC#30736,ADD]
    /** Message Parameter : Global Company Code */
    public static final String MSG_GLBL_CMPY_CD = "Global Company Code";

    // START 2016/02/16 A.Kohinata [QC3373, MOD]
//    /** Message Parameter : Batch Date */
//    public static final String MSG_BATCH_DATE = "Batch Date";
    /** Message Parameter : Sales Date */
    public static final String MSG_SALES_DATE = "Sales Date";
    // END 2016/02/16 A.Kohinata [QC3373, MOD]

    /** Message Parameter : SVC_INV */
    public static final String MSG_SVC_INV = "SVC_INV";

    /** Message Parameter : SVC_INV_PK */
    public static final String MSG_SVC_INV_PK = "SVC_INV_PK";

    /** Message Parameter :Divide Number */
    public static final String MSG_DIVIDE_NUM = "Divide Number";

    /** Message Parameter : error line */
    public static final String MSG_ERR_LINE = "Error Invoice Line Count : ";

    /** Message Parameter : normal line  */
    public static final String MSG_NRML_LINE = "Normal Invoice Line Count : ";

    /** Message Parameter : Service Invoice Number */
    public static final String MSG_SVC_INV_NUM = "Service Invoice Number : ";

    // add start 2019/04/03 QC#31037
    /** Message Parameter : User Variable1 */
    public static final String MSG_USR_VAR1 = "User Variable1";

    /** User Variable : Create Stand By Fee Invoice Mode */
    public static final String CREATE_STAND_BY_FEE_MODE = "X";
  // add end 2019/04/03 QC#31037

    /** ****************************************** */
    /** SQL Columns                                */
    /** ****************************************** */
    /** getSvcInvList */
    public static enum SELECT {
        // --** for Invoice Header Parameter ** --
        /** */
        INV_RANK
        /** */
        , TM
        /** */
        , TM_ZN
        /** */
        , SVC_INV_PK
        /** */
        , SVC_INV_NUM
        /** */
        , ORIG_SVC_INV_NUM
        /** */
        , INV_DT, INV_TP_CD
        /** */
        , INV_DUE_DT
        /** */
        , CUST_ISS_PO_NUM
        /** */
        , CUST_ISS_PO_DT
        /** */
        , BILL_TO_CUST_CD
        /** */
        , BILL_TO_CUST_ACCT_CD, SELL_TO_CUST_CD
        /** */
        , BILL_TO_LOC_NM
        /** */
        , BILL_TO_ADDL_LOC_NM
        /** */
        , BILL_TO_FIRST_LINE_ADDR
        /** */
        , BILL_TO_SCD_LINE_ADDR
        /** */
        , BILL_TO_THIRD_LINE_ADDR
        /** */
        , BILL_TO_FRTH_LINE_ADDR
        /** */
        , BILL_TO_CTY_ADDR
        /** */
        , BILL_TO_PROV_NM
        /** */
        , BILL_TO_CNTY_NM
        /** */
        , BILL_TO_ST_CD
        /** */
        , BILL_TO_POST_CD
        /** */
        , BILL_TO_CTRY_CD
        /** */
        , BILL_TO_FIRST_REF_CMNT_TXT
        /** */
        , BILL_TO_SCD_REF_CMNT_TXT
        /** */
        , PMT_TERM_START_DT
        /** */
        , PMT_TERM_CD
        /** */
        , CASH_DISC_TERM_CD
        /** */
        , INV_TOT_DEAL_NET_AMT
        // START 2018/05/31 K.Kojima [QC#23685,DEL]
        // /** */
        // , INV_TOT_DEAL_TAX_AMT
        // END 2018/05/31 K.Kojima [QC#23685,DEL]
        /** */
        , DEAL_CCY_CD
        /** */
        , CCY_EXCH_RATE
        /** */
        , INV_PRINT_FLG_CRD
        /** */
        , CR_DR_RSN_CD
        /** */
        , CR_DR_SUB_RSN_CD
        /** */
        , PMT_TERM_CASH_DISC_CD
        /** */
        , PMT_CC_FLG
        // Add Start 03/28/2016 <QC#3829>
        /** */
        ,CR_CARD_CUST_REF_NUM
        /** */
        ,CR_CARD_TP_CD
        /** */
        ,DS_INV_EXPR_DT
        // Add End  03/28/2016 <QC#3829>
        /** */
        /** */
        , DS_INV_TP_CD
        /** */
        , SVC_INV_SRC_TP_CD
        /** */
        , DS_CONTR_NUM
        /** */
        , FSR_NUM
        /** */
        , SLS_REP_TOC_CD
        // START 2017/11/29 K.Kojima [QC#21918,ADD]
        /** */
        , SLS_REP_TOC_CD_FOR_LINE
        // END 2017/11/29 K.Kojima [QC#21918,ADD]
        /** */
        , CUST_CARE_TKT_NUM
        /** */
        , DS_CONTR_CATG_CD
        /** */
        , LINE_BIZ_TP_CD
        /** */
        , DS_BIZ_AREA_CD
        // START 2016/02/23 T.Aoyagi [QC2885, ADD]
        /** */
        , CUST_INV_SRC_CD
        // END 2016/02/23 T.Aoyagi [QC2885, ADD]
        /** */
        , CTAC_PSN_FIRST_NM
        /** */
        , CTAC_PSN_MID_NM
        /** */
        , CTAC_PSN_LAST_NM
        /** */
        , INV_PRINT_FLG_DIT
        /** */
        , INV_PRINT_FLG_DCE
        /** */
        , DS_CONTR_EDI_CD
        /** */
        , DS_ACCT_DLR_CD
        // --** for Shipments Parameter ** --
        /** */
        , CTAC_PSN_PK
        // --** for Invoice Line Parameter ** --
        /** */
        , INV_LINE_RANK
        /** */
        , SVC_INV_LINE_PK
        /** */
        , SVC_INV_LINE_NUM
        /** */
        , MAIN_MACH_FLG
        /** */
        , CONTR_ASRY_FLG
        /** */
        , MDSE_CD
        /** */
        , MDSE_NM
        /** */
        , INTG_MDSE_CD
        /** */
        , INTG_MDSE_NM
        /** */
        , SVC_INV_QTY
        /** */
        , DEAL_UNIT_PRC_AMT
        /** */
        , INV_DISP_UNIT_PRC_AMT
        // START 2018/05/31 K.Kojima [QC#23685,DEL]
        // /** */
        // , INV_LINE_DEAL_TAX_AMT
        // END 2018/05/31 K.Kojima [QC#23685,DEL]
        /** */
        , INV_LINE_DEAL_NET_AMT
        /** */
        , FUNC_UNIT_PRC_AMT
        // START 2018/05/31 K.Kojima [QC#23685,DEL]
        // /** */
        // , INV_LINE_FUNC_TAX_AMT
        // END 2018/05/31 K.Kojima [QC#23685,DEL]
        /** */
        , INV_LINE_FUNC_NET_AMT
        // START 2018/05/31 K.Kojima [QC#23685,DEL]
        // /** */
        // , SLS_TAX_RATE
        // END 2018/05/31 K.Kojima [QC#23685,DEL]
        /** */
        , SVC_CONFIG_MSTR_PK
        /** */
        , DS_CONTR_SQ_NUM
        /** */
        , FIRST_BLLG_ATTRB_VAL_TXT
        /** */
        , SCD_BLLG_ATTRB_VAL_TXT
        /** */
        , THIRD_BLLG_ATTRB_VAL_TXT
        /** */
        , FRTH_BLLG_ATTRB_VAL_TXT
        /** */
        , FIFTH_BLLG_ATTRB_VAL_TXT
        /** */
        , SIXTH_BLLG_ATTRB_VAL_TXT
        /** */
        , UOM_CD
        /** */
        , DS_CONTR_DTL_PK
        /** */
        , MDL_ID
        /** */
        , SVC_INV_CHRG_TP_CD
        /** */
        , BLLG_PER_FROM_DT
        /** */
        , BLLG_PER_THRU_DT
        /** */
        , BLLG_COPY_QTY
        /** */
        , TAX_CALC_GEO_CD
        /** */
        , CUST_ISS_PO_NUM_2
        /** */
        , CUST_ISS_PO_DT_2
        /** */
        , INV_DISP_QTY
        // --** for Invoice Line Sales Credit Parameter ** --
        /** */
        , SVC_INV_LINE_ALLOC_PK
        /** */
        , SVC_INV_LINE_ALLOC_NUM
        /** */
        , INV_LINE_SPL_TP_CD
        /** */
        , SLS_ALLOC_RATE
        /** */
        , TOC_CD
        /** */
        , INV_LINE_DEAL_NET_AMT_2
        /** */
        , INV_LINE_FUNC_NET_AMT_2
        /** */
        , CCY_CD
        /** */
        , DFRD_ACCTG_RULE_CD
        /** */
        , DFRD_ACCTG_RULE_DURN_AOT
        /** */
        , COA_BR_CD_ORG
        /** */
        , COA_CMPY_CD
        /** */
        , COA_AFFL_CD
        /** */
        , COA_BR_CD
        /** */
        , COA_CH_CD
        /** */
        , COA_CC_CD
        /** */
        , COA_ACCT_CD
        /** */
        , COA_PROD_CD
        /** */
        , COA_PROJ_CD
        /** */
        , COA_EXTN_CD
        /** */
        , TRX_CD
        /** */
        , TRX_RSN_CD
        // Add Start 2016/11/10 <QC#15789> 
        /** */
        , AJE_INV_LINE_ALLOC_CD
        // Add End   2016/11/10 <QC#15789> 
        // --** for Invoice Line Tax Detail Parameter ** --
        /** */
        , DS_SLS_TAX_TP_CD
        /** */
        , FUNC_TAX_AMT
        /** */
        , DEAL_TAX_AMT
        /** */
        , SLS_TAX_RATE_2
        /** */
        // --** for Invoice Line Count ** --
        ,SER_CNT
        /** */
        , TIE_CNT
        /** */
        , BCG_CNT
        /** */
        , PGM_CNT
        // --** for Service Invoice Page Layout Columns ** --
        /** */
        ,CHARGE_CONTENT
        /** */
        , CHARGE_TIT
        /** */
        , DETAIL_FIT
        /** */
        , PROG_BEF_TOT
        /** */
        , PROG_DESC
        /** */
        , PROG_DESC_MOD_SEP
        /** */
        , PROG_DESC_SEP
        /** */
        , PROG_MTOT_SEP
        /** */
        , PROG_NAME
        /** */
        , PROG_SEP
        /** */
        , PROG_STOT_SEP
        /** */
        , PROG_TOT
        /** */
        , SERIAL_CONTENT
        /** */
        , SERIAL_SEP_FL
        /** */
        , SERIAL_SEP_NF
        /** */
        , SERIAL_TIT
        /** */
        , SRSUM_SEP_FL
        /** */
        , SRSUM_SEP_NF
        /** */
        , SUMMARY_FIT
        // START 2016/07/05 T.Kanasaka [QC#9438, ADD]
        /** */
        , SHIP_RANK
        // add start 2016/08/05 CSA Defect#12836
        /** */
        , SHIP_TO_CUST_ACCT_CD
        // add end 2016/08/05 CSA Defect#12836
        /** */
        , SHIP_TO_CUST_CD
        /** */
        , SHIP_TO_LOC_NM
        /** */
        , SHIP_TO_ADDL_LOC_NM
        /** */
        , SHIP_TO_FIRST_LINE_ADDR
        /** */
        , SHIP_TO_SCD_LINE_ADDR
        /** */
        , SHIP_TO_THIRD_LINE_ADDR
        /** */
        , SHIP_TO_FRTH_LINE_ADDR
        /** */
        , SHIP_TO_ST_CD
        /** */
        , SHIP_TO_PROV_NM
        /** */
        , SHIP_TO_CNTY_NM
        /** */
        , SHIP_TO_FIRST_REF_CMNT_TXT
        /** */
        , SHIP_TO_SCD_REF_CMNT_TXT
        /** */
        , SHIP_TO_POST_CD
        /** */
        , SHIP_TO_CTY_ADDR
        /** */
        , SHIP_TO_CTRY_CD
        // END 2016/07/05 T.Kanasaka [QC#9438, ADD]
        // Add Start 2016/11/02 <QC#12540>
        /** */
        , BASE_BLLG_TMG_CD
        /** */
        , MTR_BLLG_TMG_CD
        // Add End   2016/11/02 <QC#12540>
        // START 2017/02/22 K.Kitachi [QC#17515, ADD]
        /** */
        , CONTR_INV_CMNT_TXT
        // END 2017/02/22 K.Kitachi [QC#17515, ADD]
        // START 2017/08/07 M.Kidokoro [QC#20073, ADD]
        /** */
        , TAX_AREA_ID
        // END 2017/08/07 M.Kidokoro [QC#20073, ADD]
        // START 2018/07/10 K.Kitachi [QC#22788, ADD]
        /** */
        , SVC_MACH_MSTR_PK
        // END 2018/07/10 K.Kitachi [QC#22788, ADD]
        // START 2018/07/25 M.Naito [QC#13309, ADD]
        /** */
        , TEMP_ETTL_NUM
        // END 2018/07/25 M.Naito [QC#13309, ADD]
        // add start 2018/08/09 QC#27329-3
        , DS_CONTR_BLLG_SCHD_PK
        // add end 2018/08/09 QC#27329-3
        // START 2017/02/22 K.Kitachi [QC#17515, ADD]
        /** */
        , ALT_PAYER_CUST_CD
        /** */
        , DS_ACCT_NUM
        /** */
        , SELL_TO_LOC_NM
        /** */
        , SELL_TO_ADDL_LOC_NM
        /** */
        , SELL_TO_FIRST_LINE_ADDR
        /** */
        , SELL_TO_SCD_LINE_ADDR
        /** */
        , SELL_TO_THIRD_LINE_ADDR
        /** */
        , SELL_TO_FRTH_LINE_ADDR
        /** */
        , SELL_TO_CTY_ADDR
        /** */
        , SELL_TO_PROV_NM
        /** */
        , SELL_TO_CNTY_NM
        /** */
        , SELL_TO_ST_CD
        /** */
        , SELL_TO_POST_CD
        /** */
        , SELL_TO_CTRY_CD
        /** */
        , SELL_TO_FIRST_REF_CMNT_TXT
        /** */
        , SELL_TO_SCD_REF_CMNT_TXT
        /** */
        , LEASE_CMPY_FLG
        // END 2017/02/22 K.Kitachi [QC#17515, ADD]
        // START 2023/01/20 R.Onozuka [QC#60823, ADD]
        /** */
        , DS_CR_CARD_EZCANCELFLAG
        // END 2023/01/20 R.Onozuka [QC#60823, ADD]
    }

    /** Table Name : SVC_INV_PG_LYOT */
    public static final String SVC_INV_PG_LYOT = "SVC_INV_PG_LYOT";

    /** Column Name : SVC_INV_PG_LYOT_CD */
    public static final String SVC_INV_PG_LYOT_CD = "SVC_INV_PG_LYOT_CD";

    /** Column Name : SVC_INV_PG_LINE_HGT */
    public static final String SVC_INV_PG_LINE_HGT = "SVC_INV_PG_LINE_HGT";

    /** ****************************************** */
    /** Default Value                              */
    /** ****************************************** */
    /** default invoice bol line number */
    public static final String DFLT_INV_BOL_LINE_NUM = "001";

    /** default invoice line sub trx number */
    public static final String DFLT_INV_LINE_SUB_TRX_NUM = "001";

    /** default invoice Print Status */
    public static final String INV_PRINT_STS_DEF = "1";

    /** default invoice Print Status */
    public static final String INV_PRINT_STS_N = "2";

    /** default invoice Send Status */
    public static final String INV_SEND_STS_PROCESSED = "2";

    // START 2018/07/18 M.Naito [QC#13309, ADD]
    /** default Status */
    public static final String STS_CD_ZERO = "0";

    /** ZERO PADDING */
    public static final String DEFAULT_SVC_INV_LINE_NUM = "00001";

    /** default rate */
    public static final int DEFAULT_RATE = 100;

    /** default scale */
    public static final int DEFAULT_SCL = 4;
    // END 2018/07/18 M.Naito [QC#13309, ADD]

    /** ****************************************** */
    /** VARCHAR/NUM CONST Key                      */
    /** ****************************************** */
    /** VARCHAR CONST : NAAB0030_DS_ORD_TP_CD */
    public static final String NAAB0030_DS_ORD_TP_CD = "NAAB0030_DS_ORD_TP_CD";

    /** VARCHAR CONST : NAAB0030_DS_ORD_RSN_CD */
    public static final String NAAB0030_DS_ORD_RSN_CD = "NAAB0030_DS_ORD_RSN_CD";

    /** VARCHAR CONST : NAAB0030_TRX_SRC_TP_CD */
    public static final String NAAB0030_TRX_SRC_TP_CD = "NAAB0030_TRX_SRC_TP_CD";

    /** VARCHAR CONST : NAAB0030_SYS_SRC_CD */
    public static final String NAAB0030_SYS_SRC_CD = "NAAB0030_SYS_SRC_CD";

    // START 2018/07/18 M.Naito [QC#13309, ADD]
    /** VARCHAR CONST : DS_INV_TYPE_INVOICE */
    public static final String DS_INV_TYPE_INVOICE = "NSZC0060_DS_INV_TYPE_INVOICE";

    /** VARCHAR CONST : COA_CMPY_CD */
    public static final String COA_CMPY_CD = "COA_CMPY_CD";

    /** VARCHAR CONST : COA_EXTN_CD */
    public static final String COA_EXTN_CD = "COA_EXTN_CD";

    /** VARCHAR CONST : DEF_SLS_REP_TOC_CD_ESS */
    public static final String DEF_SLS_REP_TOC_CD_ESS = "DEF_SLS_REP_TOC_CD_ESS";

    /** VARCHAR CONST : DEF_SLS_REP_TOC_CD_LFS */
    public static final String DEF_SLS_REP_TOC_CD_LFS = "DEF_SLS_REP_TOC_CD_LFS";

    /** VARCHAR CONST : DEF_SLS_REP_TOC_CD_PPS */
    public static final String DEF_SLS_REP_TOC_CD_PPS = "DEF_SLS_REP_TOC_CD_PPS";

    /** VARCHAR CONST : STAND_BY_FEE_MDSE_CD */
    public static final String STAND_BY_FEE_MDSE_CD = "STAND_BY_FEE_MDSE_CD";
    // END 2018/07/18 M.Naito [QC#13309, ADD]

    // Mod Start 2018/01/24 QC#23684
    /** NUM CONST : MULTI_SVC_INV_CALC_CNT */
    public static final String MULTI_SVC_INV_CALC_CNT = "NSBB0100MULTI_SVC_INV_CALC_CNT";
    // Mod End 2018/01/24 QC#23684

    /** NUM CONST : INV_PRT_TOT_LMT(SVC_INV_NO_PRINT_AMT) */
    public static final String INV_PRT_TOT_LMT = "INV_PRT_TOT_LMT";

    /** NUM CONST : BLLG_PRVW_AVAL_MAX_PRC(SVC_INV_REVIEW_AMT) */
    public static final String BLLG_PRVW_AVAL_MAX_PRC = "BLLG_PRVW_AVAL_MAX_PRC";

    /** NUM CONST : DEF_SLS_REP_CR_PCT = 100 */
    public static final String DEF_SLS_REP_CR_PCT = "DEF_SLS_REP_CR_PCT";

    /** ****************************************** */
    /** Mail                                       */
    /** ****************************************** */
    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = BUSINESS_ID;

    /** mail from group */
    public static final String MAIL_FROM_GRP = "NS";

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = BUSINESS_ID + "M001";

    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    /** mail message mail invoice */
    public static final String ML_MSG_SVC_INV = "Service Invoice#: ";

    /** mail message error */
    public static final String ML_MSG_ERR = "Error: ";

    /** ****************************************** */
    /** Other                                      */
    /** ****************************************** */
    /** message kind error */
    public static final String MSG_KIND_ERROR = "E";

    /** message space */
    public static final String SPACE = " ";

    // START 2016/07/05 T.Kanasaka [QC#9438, ADD]
    /** message space */
    public static final String ZERO = "0";

    /** message space */
    public static final int INV_BOL_LINE_NUM_LENGTH = 3;

    /** message space */
    public static final int INV_LINE_NUM_LENGTH = 5;
    // END 2016/07/05 T.Kanasaka [QC#9438, ADD]

    // START 2017/02/22 K.Kitachi [QC#17515, ADD]
    /** Invoice Comment Text Length */
    public static final int INV_CMNT_TXT_LENGTH = 65;

    /** Invoice Second Comment Text Start Index */
    public static final int INV_SCD_CMNT_TXT_START_IDX = 65;

    /** Invoice Third Comment Text Start Index */
    public static final int INV_THIRD_CMNT_TXT_START_IDX = 130;

    /** Invoice Fourth Comment Text Start Index */
    public static final int INV_FRTH_CMNT_TXT_START_IDX = 195;
    // END 2017/02/22 K.Kitachi [QC#17515, ADD]

    /**
     * ACCT_ARTH_TP: Multiply
     */
    public static final String ACCT_ARTH_TP_MULTIPLY = "*";

    /**
     * ACCT_ARTH_TP: Divide
     */
    public static final String ACCT_ARTH_TP_DIVIDE = "/";
    
    // START 2019/03/18 S.Kitamura [QC#30736,ADD]
    /** Month End Mode for NSBB0100 */
    public static final String NSBB0100_MONTH_END_MODE = "NSBB0100_MONTH_END_MODE";
    // END 2019/03/18 S.Kitamura [QC#30736,ADD]

}
