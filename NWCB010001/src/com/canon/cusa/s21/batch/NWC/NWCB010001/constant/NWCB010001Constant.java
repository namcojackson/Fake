/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB010001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 10/28/2015   Fujitsu        T.Murai          Create          N/A
 * 01/19/2016   Fujitsu        H.Nagashima      Update          QC#3350
 * 06/23/2016   SRAA           K.Aratani        Update          QC#10850
 * 02/07/2017   SRAA           K.Aratani        Update          QC#17491
 * 09/05/2017   Fujitsu        S.Iidaka         Update          QC#20888
 * 09/26/2017   Fujitsu        H.Nagashima      Update          QC#20788
 * 2017/10/30   Fujitsu        H.Ikeda          Update          QC#20141
 * 01/18/2018   Fujitsu        Mz.Takahashi     Update          QC#20141
 * 06/26/2017   SRAA           K.Aratani        Update          QC#26846
 * 09/10/2018   Fujitsu        T.Ogura          Update          QC#27999
 * 10/23/2018   Fujitsu        Mz.Takahashi     Update          QC#27069
 * 01/17/2019   Fujitsu        Y.Kanefusa       Update          QC#29371 SRNO14
 * 01/22/2019   Fujitsu        Y.Kanefusa       Update          QC#29371 SRNOO6
 * 01/19/2019   Fujitsu        M.Ohno           Update          QC#29371 SRNO30
 * 02/01/2019   Fujitsu         Y.Kanefusa      Update          QC#29371 SRNO.11
 * 05/07/2019   Fujitsu        T.Murai          Update          QC#50078
 * 2019/05/13   Fujitsu        Mz.Takahashi     Update          QC#50149
 * 2019/07/06   Fujitsu        Mz.Takahashi     Update          QC#50885
 * 2019/08/22   Fujitsu        Mz.Takahashi     Update          QC#52928
 * 2019/09/04   Fujitsu         S.Takami        Update          QC#53254
 * 2019/10/11   Fujitsu        M.Ohno           Update          QC#53888
 * 2020/06/03   CITS           K.Ogino          Update          QC#57075
 * 2021/04/23   CITS           L.Mandanas       Update          QC#58350
 * 2021/09/23   CITS           L.Mandanas       Update          QC#58350
 * 2022/08/03   Hitachi        D.Yoshida        Update          QC#60129
 * 2023/12/15   Hitachi        R.Takau          Update          QC#61584
 * 
 *</pre>
 */
public class NWCB010001Constant {

    /** Process Mode - Print. */
    public static final String PRINT_MODE = "01";

    /** Process Mode - RE Print. */
    public static final String REPRINT_MODE = "02";

    /** format key - 1:SVC */
    public static final String FORMAT_TP_SVC = "1";

    /** format key - 2:SALE */
    public static final String FORMAT_TP_SALE = "2";
    
    /** invPrintStsCd - not Print 0 */
    public static final String INV_PRT_STS_0 = "0";

    /** invPrintStsCd - ready for Print */
    public static final String INV_PRT_STS_1 = "1";

    /** invPrintStsCd - Printed 2 */
    public static final String INV_PRT_STS_2 = "2";

    /** invPrintStsCd - Exclude Consolidated Credit Memo 3 */
    public static final String INV_PRT_STS_3 = "3";
    

    //Error Message
    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** BUSINESS_ID. */
    public static final String BUSINESS_ID = "NWCB010001";

    /** Mail Group ID. */
    public static final String ML_GRP_ID = "NWCB0100";

    /** Shell Variable "@" has not been set. */
    public static final String NWCM0059E = "NWCM0059E";

    /** NWCM0060E */
    public static final String NWCM0060E = "NWCM0060E";

    /** NWCM0146E */
    public static final String NWCM0146E = "NWCM0146E";
    
    /** NWCM0109E */
    public static final String NWCM0109E = "NWCM0109E";

    /** NWCM0110E */
    public static final String NWCM0110E = "NWCM0110E";

    /** NWCM0112E */
    public static final String NWCM0112E = "NWCM0112E";

    /** NWCM0146E */
    public static final String USG_INV_DESC_OTHER = "3";

    /** Line feed code. */
    public static final String ENTER = System.getProperty("line.separator");

    /** fixed number for Line Count */
    public static final int FIXED_COUNT = 6;

    /** fixed number for Line Count */
    //public static final int ONE_PAGE_LINE_COUNT = 27;
    public static final int ONE_PAGE_LINE_COUNT = 25;

    /** CC Fixed Header Message. */
    public static final String NWCB0100_CR_CARD_MSG_01 = "NWCB0100_CR_CARD_MSG_01";

    /** Fixed Header Message. */
    public static final String NWCB0100_CR_CARD_MSG_02 = "NWCB0100_CR_CARD_MSG_02";

    /** Fixed Header Message. */
    public static final String NWCB0100_CR_CARD_MSG_03 = "NWCB0100_CR_CARD_MSG_03";

    /** Fixed Header Message. */
    public static final String NWCB0100_PRT_CONSL_CC_FTR_MSG = "NWCB0100_PRT_CONSL_CC_FTR_MSG";
    
    /** Fixed Header Message. */
    public static final String SVC_MESSAGE1 = "(WITH ";

    // 2019/09/04 QC#53254 Del Start
    /** Fixed Header Message. */
//    public static final String SVC_MESSAGE2 = "for your records only- paid with credit card";
    // 2019/09/04 QC#53254 Del End

    /** Fixed Header Message. */
    public static final String SVC_MESSAGE3 = " Copies included)";

    /** Fixed Header Message. */
    public static final String SERIAL_MESSAGE = "(BASE:";

    /** Fixed Message. */
    public static final String EASY_PAC_MSG1 = "per sq. ft.";

    /** Fixed Message. */
    public static final String EASY_PAC_MSG2 = "sq. ft.";

    /** Var_Char_Const name Cmpy First Address */
    public static final String VAR_CHAR_CONST_NM_CMPY_FIRST_ADDR = "NWCB0100_CMPY_FIRST_ADDR";

    /** Var_Char_Const name Cmpy Second Address. */
    public static final String VAR_CHAR_CONST_NM_CMPY_SCD_ADDR = "NWCB0100_CMPY_SCD_ADDR";

    /** Var_Char_Const name Cmpy City. */
    public static final String VAR_CHAR_CONST_NM_CMPY_CTY_ADDR = "NWCB0100_CMPY_CTY_ADDR";

    /** Var_Char_Const name Cmpy State. */
    public static final String VAR_CHAR_CONST_NM_CMPY_STATE = "NWCB0100_CMPY_STATE";

    /** Var_Char_Const name Cmpy Post. */
    public static final String VAR_CHAR_CONST_NM_CMPY_POST = "NWCB0100_CMPY_POST";

    /** Var_Char_Const name Cmpy Tel. */
    public static final String VAR_CHAR_CONST_NM_CMPY_TEL = "NWCB0100_CMPY_TEL";

    /** Var_Char_Const name Cmpy Url. */
    public static final String VAR_CHAR_CONST_NM_CMPY_URL = "NWCB0100_CMPY_URL";

    /** Var_Char_Const name Default Bill To Attention. */
    public static final String VAR_CHAR_CONST_NM_DEF_BILL_TO_ATTN = "NWCB0100_DEF_BILL_TO_ATTN";
    
    /** Var_Char_Const name EasyPac1 Messag1. */
    public static final String VAR_CHAR_CONST_NM_EASY_PAC_1_INV_MEG_01 = "EASY_PAC_1_INV_MEG_01";

    /** Var_Char_Const name EasyPac1 Messag2. */
    public static final String VAR_CHAR_CONST_NM_EASY_PAC_1_INV_MEG_02 = "EASY_PAC_1_INV_MEG_02";

    /** Var_Char_Const name EasyPac1 Messag3. */
    public static final String VAR_CHAR_CONST_NM_EASY_PAC_1_INV_MEG_03 = "EASY_PAC_1_INV_MEG_03";

    /** Var_Char_Const name EasyPac1 Messag4. */
    public static final String VAR_CHAR_CONST_NM_EASY_PAC_1_INV_MEG_04 = "EASY_PAC_1_INV_MEG_04";

    /** Num_Const name Invoice Print Limit Amount. */
    public static final String NUM_CONST_NM_INV_PRT_TOT_LMT = "INV_PRT_TOT_LMT";

    /** Var_Char_Const name Short Fall Text. */
    public static final String INV_PRINT_SHORT_FALL_TXT = "INV_PRINT_SHORT_FALL_TXT";
    
    /** Var_Char_Const name Short Fall Item. */
    public static final String INV_PRINT_SHORT_FALL_ITEM = "INV_PRINT_SHORT_FALL_ITEM";
    
    /** Var_Char_Const name OM Invoice BO Sort.(0:INV->BO, 1:BO->INV, 2:Mix) */
    public static final String INV_PRINT_OM_INV_BO_SORT = "INV_PRINT_OM_INV_BO_SORT";
    
    /** Var_Char_Const name Run Book DS_INV_TP_CD */
    public static final String VAR_CHAR_CONST_NM_RUN_BOOK_DS_INV_TP = "NWCB0100_RUN_BOOK_DS_INV_TP";

    /** Var_Char_Const name Run Book SYS_SRC_CD */
    public static final String VAR_CHAR_CONST_NM_RUN_BOOK_SYS_SRC = "NWCB0100_RUN_BOOK_SYS_SRC";

    // QC#50078 2019/05/07 Add Start
    /** Var_Char_Const name FSR Invoice Type */
    public static final String VAR_CHAR_CONST_NM_FSR_INV_TP = "NFCL3000_FSR_INV_TP";
    // QC#50078 2019/05/07 Add End

    // START 2023/11/9 R.Takau [QC#61584, ADD]
    /** Var_Char_Const name Tax Print Direct Sales Invoice Type */
    public static final String VAR_CHAR_CONST_NWCB0010_TAX_PRINT_DS_INV_TP = "NWCB0010_TAX_PRINT_DS_INV_TP";

    /** Var_Char_Const name Tax Print Direct Sales Invoice Type VALUE */
    public static final String NWCB0010_TAX_PRINT_DS_INV_TP_CONST_VAL = "0027,0028,0049,0050";

    /** Var_Char_Const name Tax Print State Code */
    public static final String VAR_CHAR_CONST_NWCB0100_SLS_REP_ST_CD = "NWCB0100_SLS_REP_ST_CD";

    /** Var_Char_Const name Tax Print State Code VALUE*/
    public static final String NWCB0100_SLS_REP_ST_CD_CONST_VAL = "IL";

    /** Var_Char_Const name Ship To Customer's State Code */
    public static final String VAR_CHAR_CONST_NWCB0100_SHIP_TO_CUST_ST_CD = "NWCB0100_SHIP_TO_CUST_ST_CD";

    /** Var_Char_Const name Ship To Customer's State Code VALUE */
    public static final String NWCB0100_SHIP_TO_CUST_ST_CD_CONST_VAL = "IL";

    /** Var_Char_Const name Tax Print Merchandise Code */
    public static final String VAR_CHAR_CONST_SLS_TAX_MDSE_CD = "NWCB0100_SLS_TAX_MDSE_CD";

    /** Var_Char_Const name Tax Print Merchandise Code VALUE */
    public static final String NWCB0100_SLS_TAX_MDSE_CD_CONST_VAL = "026ZZ806";

    /** Var_Char_Const name Bill To Customer Code CFS */
    public static final String VAR_CHAR_CONST_BILL_TO_CUST_ACCT_CD_CFS = "BILL_TO_CUST_ACCT_CD_CFS";

    /** Var_Char_Const name Bill To Customer Code CFS VALUE*/
    public static final String BILL_TO_CUST_ACCT_CD_CFS_CONST_VAL = "555435";

    // END 2023/11/9 R.Takau [QC#61584, ADD]

    /** Bisiness Area Code */
    public static final String[] BIZ_AREA_CD = {"00", "50", "60" };

    /** Format key */
    public static final String FM_QTY = "FM9G999G999G999";

    /** Format key */
    public static final String FM_QTY2 = "FM999G999G999G999G999";

    /** Format key */
    public static final String FM_QTY3 = "9,999,999,990";

    //QC#17491
    /** Format key */
    public static final String FM_QTY4 = "FM999G990D9999";

    /** Format key */
    public static final String FM_RATE = "FM99G999D9999";

    /** Format key */ // Mod QC#57075
    public static final String FM_RATE2 = "FM9G999G999G990D009999";

    /** Format key */
    public static final String FM_RATE3 = "9,999,999,999,990.000000";

    /** Format key */
    public static final String FM_AMT = "FM999G999G999G999G990D00";

    /** Format key */
    public static final String FM_RATE6 = "FM9G999G999G990D999999";

    /** Fixed BigDecimal 100 */
    public static final BigDecimal BIGDECIMAL_100 = new BigDecimal(100);

    /** INV_SMRY_LINE_TP_NM NON_COPIER */
    // QC#29371 2019/01/17 Mod Start
    //public static final String NON_COPIER = "NON-COPIER";
    public static final String NON_COPIER = "NON-DEVICE";
    // QC#29371 2019/01/17 Mod End

    /** INV_SMRY_LINE_TP_NM COPIER */
    // QC#29371 2019/01/17 Mod Start
    //public static final String COPIER = "COPIER";
    public static final String COPIER = "DEVICE";
    // QC#29371 2019/01/17 Mod End

    // QC#20141 mod Start
    /** INV_SMRY_LINE_TP_NM SHORTFALL */
    public static final String SHORTFALL = "SHORTFALL";

    /** INV_SMRY_LINE_TP_NM SHORTFALL_EASY_PAC1 */
    public static final String SHORTFALL_EASY_PAC1 = "Contract Shortfall Sq Footage";

    /** INV_SMRY_LINE_TP_NM COPIER_EASY_PAC1 */
    public static final String COPIER_EASY_PAC1 = "Media Sq Footage";
    // QC#20141 mod End

    /** ORD_CLS_NM PARTS/LABOR */
    public static final String PARTS_OR_LABOR = "PARTS/LABOR";

    /** ORD_CLS_NM SUPPLY */
    public static final String SUPPLY = "SUPPLY";
    //QC#26846
    /** ORD_CLS_NM PARTS */
    public static final String PARTS = "PARTS";

    /** ORD_CLS_NM SALE */
    public static final String SALE = "SALE";

    /** SVC_INV_CHRG_TP_NM Base */
    public static final String BASE = "Base";

    /** SVC_INV_CHRG_TP_NM Usage */
    public static final String USAGE = "Usage";

    /** SVC_INV_CHRG_TP_NM Attachment */
    public static final String ATT = "Attachment";

    /** Credit and Rebill */
    public static final String CREDIT_ANDOR_REBILL = "Credit and/or Rebill";

    /** DB Item Column Name */
    public static final String INV_NUM = "INV_NUM";

    /** DB Item Column Name */
    public static final String INV_BOL_LINE_NUM = "INV_BOL_LINE_NUM";

    /** DB Item Column Name */
    public static final String INV_LINE_NUM = "INV_LINE_NUM";

    /** DB Item Column Name */
    public static final String INV_LINE_SUB_NUM = "INV_LINE_SUB_NUM";

    /** DB Item Column Name */
    public static final String INV_LINE_SUB_TRX_NUM = "INV_LINE_SUB_TRX_NUM";

    /** DB Item Column Name */
    public static final String INV_TRX_LINE_SUB_NUM = "INV_TRX_LINE_SUB_NUM";

    /** DB Item Column Name */
    public static final String CONSL_BILL_NUM = "CONSL_BILL_NUM";

    /** DB Item Column Name */
    public static final String DS_BIZ_AREA_CD = "DS_BIZ_AREA_CD";

    /** DB Item Column Name */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** DB Item Column Name */
    public static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";

    /** DB Item Column Name */
    public static final String INV_PRINT_MSG_TXT = "INV_PRINT_MSG_TXT";

    /** DB Item Column Name */
    public static final String INV_AMT = "INV_AMT";

    /** DB Item Column Name */
    public static final String BALANCE_DUE = "BALANCE_DUE";

    /** DB Item Column Name */
    public static final String INV_AMT_TXT = "INV_AMT_TXT";

    /** DB Item Column Name */
    public static final String BALANCE_DUE_TXT = "BALANCE_DUE_TXT";

    /** DB Item Column Name */
    public static final String COMMENT1 = "COMMENT1";

    /** DB Item Column Name */
    public static final String COMMENT2 = "COMMENT2";

    /** DB Item Column Name */
    public static final String COMMENT3 = "COMMENT3";

    /** DB Item Column Name */
    public static final String COMMENT4 = "COMMENT4";

    /** DB Item Column Name */
    public static final String PMT_CC_FLG = "PMT_CC_FLG";

    /** DB Item Column Name */
    public static final String SVC_INV_SRC_TP_CD = "SVC_INV_SRC_TP_CD";

    /** DB Item Column Name */
    public static final String CR_CARD_LAST_DIGIT = "CR_CARD_LAST_DIGIT";

    /** DB Item Column Name */
    public static final String INV_TP_CD = "INV_TP_CD";

    /** DB Item Column Name */
    public static final String SLS_REP_TOC_CD = "SLS_REP_TOC_CD";
    
    /** DB Item Column Name */
    public static final String FORMAT_KEY = "FORMAT_KEY";

    /** DB Item Column Name */
    public static final String INV_PRINT_FLG = "INV_PRINT_FLG";

    /** DB Item Column Name */
    public static final String INV_PRINT_STS_CD = "INV_PRINT_STS_CD";

    /** DB Item Column Name */
    public static final String PMT_TERM_CD = "PMT_TERM_CD";

    /** DB Item Column Name */
    public static final String ISTL_PMT_TERM_FLG = "ISTL_PMT_TERM_FLG";

    /** DB Item Column Name */
    public static final String ISTL_PMT_TERM_PCT = "ISTL_PMT_TERM_PCT";

    /** DB Item Column Name */
    public static final String ISTL_PMT_TERM_AOT = "ISTL_PMT_TERM_AOT";

    /** DB Item Column Name */
    public static final String ISTL_PMT_TERM_DTL_NUM = "ISTL_PMT_TERM_DTL_NUM";

    /** DB Item Column Name */
    public static final String INV_DT = "INV_DT";

    /** DB Item Column Name */
    public static final String BILL_TO_CD = "BILL_TO_CD";

    /** DB Item Column Name */
    public static final String BILL_TO_NM = "BILL_TO_NM";

    /** DB Item Column Name */
    public static final String BILL_TO_LOC_NUM = "BILL_TO_LOC_NUM";

    /** DB Item Column Name */
    public static final String PAST_DUE_AFT = "PAST_DUE_AFT";

    /** DB Item Column Name */
    public static final String INV_CHARGE_AMT = "INV_CHARGE_AMT";

    /** DB Item Column Name */
    public static final String BASE_CHARGE = "BASE_CHARGE";

    /** DB Item Column Name */
    public static final String ATTACHMENT_CHARGE = "ATTACHMENT_CHARGE";

    /** DB Item Column Name */
    public static final String USAGE_CHARGE = "USAGE_CHARGE";

    /** DB Item Column Name */
    public static final String FRT_AMT = "FRT_AMT";

    /** DB Item Column Name */
    public static final String TAX_AMT = "TAX_AMT";

    /** DB Item Column Name */
    public static final String PRE_PMT_AMT = "PRE_PMT_AMT";

    /** DB Item Column Name */
    public static final String INV_CHARGE_AMT_TXT = "INV_CHARGE_AMT_TXT";

    /** DB Item Column Name */
    public static final String FRT_AMT_TXT = "FRT_AMT_TXT";

    /** DB Item Column Name */
    public static final String TAX_AMT_TXT = "TAX_AMT_TXT";

    /** DB Item Column Name */
    public static final String PRE_PMT_AMT_TXT = "PRE_PMT_AMT_TXT";

    /** DB Item Column Name */
    public static final String BILL_TO_ATTN = "BILL_TO_ATTN";

    /** DB Item Column Name */
    public static final String BILL_TO_FIRST_ADDR = "BILL_TO_FIRST_ADDR";

    /** DB Item Column Name */
    public static final String BILL_TO_SCD_ADDR = "BILL_TO_SCD_ADDR";

    /** DB Item Column Name */
    public static final String BILL_TO_CTY = "BILL_TO_CTY";

    /** DB Item Column Name */
    public static final String BILL_TO_ST = "BILL_TO_ST";

    /** DB Item Column Name */
    public static final String BILL_TO_ZIP_CD = "BILL_TO_ZIP_CD";

    /** DB Item Column Name */
    public static final String TERMS = "TERMS";

    /** DB Item Column Name */
    public static final String CONTR_CATG = "CONTR_CATG";

    /** DB Item Column Name */
    public static final String INV_SRC_TP = "INV_SRC_TP";

    /** DB Item Column Name */
    public static final String ORDER_TYPE = "ORDER_TYPE";

    /** DB Item Column Name */
    public static final String DS_INV_TP = "DS_INV_TP";

    /** DB Item Column Name */
    public static final String REM_TO_EIN_CD = "REM_TO_EIN_CD";

    /** DB Item Column Name */
    public static final String REM_TO_NM = "REM_TO_NM";

    /** DB Item Column Name */
    public static final String REM_TO_FIRST_ADDR = "REM_TO_FIRST_ADDR";

    /** DB Item Column Name */
    public static final String REM_TO_SCD_ADDR = "REM_TO_SCD_ADDR";

    /** DB Item Column Name */
    public static final String REM_TO_CTY = "REM_TO_CTY";

    /** DB Item Column Name */
    public static final String REM_TO_ST = "REM_TO_ST";

    /** DB Item Column Name */
    public static final String REM_TO_POST = "REM_TO_POST";

    /** DB Item Column Name */
    public static final String DTL_DPLY_FLG = "DTL_DPLY_FLG";

    /** DB Item Column Name */
    public static final String FORMAT_TYPE = "FORMAT_TYPE";

    /** DB Item Column Name */
    public static final String PMT_TERM_CASH_DISC_CD = "PMT_TERM_CASH_DISC_CD";

    /** DB Item Column Name */
    public static final String ISTL_FLG = "ISTL_FLG";

    /** DB Item Column Name */
    public static final String BAT_TP = "BAT_TP";

    /** DB Item Column Name */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** DB Item Column Name */
    public static final String CONTR_PK = "CONTR_PK";

    /** DB Item Column Name */
    public static final String CONTR_NO = "CONTR_NO";

    /** DB Item Column Name */
    public static final String FSR_NUM = "FSR_NUM";

    /** DB Item Column Name */
    public static final String TAX_DETAIL_FLG = "TAX_DETAIL_FLG";

    /** DB Item Column Name */
    public static final String USG_INV_DESC_TXT = "USG_INV_DESC_TXT";

    /** DB Item Column Name */
    public static final String USG_INV_DESC_CD = "USG_INV_DESC_CD";

    /** DB Item Column Name */
    public static final String SVC_PGM_CD = "SVC_PGM_CD";
    
    /** DB Item Column Name */
    public static final String SVC_PGM_NM = "SVC_PGM_NM";

    /** DB Item Column Name */
    public static final String PO_NO = "PO_NO";

    /** DB Item Column Name */
    public static final String STATE_TAX = "STATE_TAX";

    /** DB Item Column Name */
    public static final String COUNTY_TAX = "COUNTY_TAX";

    /** DB Item Column Name */
    public static final String CITY_TAX = "CITY_TAX";

    /** DB Item Column Name */
    public static final String TOTAL_TAX = "TOTAL_TAX";

    /** DB Item Column Name */
    public static final String TOTAL_AMT = "TOTAL_AMT";

    /** DB Item Column Name */
    public static final String TOTAL_AMOUNT = "TOTAL_AMOUNT";
        
    /** DB Item Column Name */
    public static final String SUBTOTALS = "SUBTOTALS";

    /** DB Item Column Name */
    public static final String BASE_CHARGE_COUNT = "BASE_CHARGE_COUNT";

    /** DB Item Column Name */
    public static final String METER_CHARGE_COUNT = "METER_CHARGE_COUNT";

    /** DB Item Column Name */
    public static final String BASE_COPY_QTY = "BASE_COPY_QTY";

    /** DB Item Column Name */
    public static final String CHARGE_TYPE = "CHARGE_TYPE";

    /** DB Item Column Name */
    public static final String PERIOD_FROM = "PERIOD_FROM";

    /** DB Item Column Name */
    public static final String PERIOD_THRU = "PERIOD_THRU";

    /** DB Item Column Name */
    public static final String SPECIAL_CHARGE_FLG = "SPECIAL_CHARGE_FLG";

    // QC#29371 2019/01/22 Add Start
    /** DB Item Column Name */
    public static final String SVC_INV_CHRG_TP_CD = "SVC_INV_CHRG_TP_CD";

    /** DB Item Column Name */
    public static final String TYPE = "TYPE";

    /** DB Item Column Name */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** DB Item Column Name */
    public static final String DS_CONTR_CRAT_TP_CD = "DS_CONTR_CRAT_TP_CD";

    /** DB Item Column Name */
    public static final String SVC_INV_MERGE_TP_CD = "SVC_INV_MERGE_TP_CD";

    /** DB Item Column Name */
    public static final String SVC_PGM_TP_CD = "SVC_PGM_TP_CD";

    // QC#29371 2019/01/22 Add End

    // QC#20788 add Start
    /** DB Item Column Name */
    public static final String SHIP_TO_CUST_ACCT_CD = "SHIP_TO_CUST_ACCT_CD";

    /** DB Item Column Name */
    public static final String SHIP_TO_CUST_ACCT_NM = "SHIP_TO_CUST_ACCT_NM";
// QC#20788 add End

    /** DB Item Column Name */
    public static final String SHIP_TO_CODE = "SHIP_TO_CODE";

    /** DB Item Column Name */
    public static final String SHIP_TO_NAME = "SHIP_TO_NAME";

    /** DB Item Column Name */
    public static final String SHIP_TO_FST_ADDR = "SHIP_TO_FST_ADDR";

    /** DB Item Column Name */
    public static final String SHIP_TO_SCD_ADDR = "SHIP_TO_SCD_ADDR";

    /** DB Item Column Name */
    public static final String SHIP_TO_CITY = "SHIP_TO_CITY";

    /** DB Item Column Name */
    public static final String SHIP_TO_ST = "SHIP_TO_ST";

    /** DB Item Column Name */
    public static final String SHIP_TO_POST = "SHIP_TO_POST";

    /** DB Item Column Name */
    public static final String MACHINE_PK = "MACHINE_PK";

    /** DB Item Column Name */
    public static final String SERIAL_NO = "SERIAL_NO";

    //QC#50149 2019/05/13 Add Start
    /** DB Item Column Name(Sort) */
    public static final String SERIAL_NO_SORT = "SERIAL_NO_SORT";
    //QC#50149 2019/05/13 Add End

    /** DB Item Column Name */
    public static final String MODEL = "MODEL";

    /** DB Item Column Name */
    public static final String MODEL_ID = "MODEL_ID";

    /** DB Item Column Name */
    public static final String CUST_CODES_1 = "CUST_CODES1";

    /** DB Item Column Name */
    public static final String CUST_CODES_2 = "CUST_CODES2";

    /** DB Item Column Name */
    public static final String CUST_CODES_3 = "CUST_CODES3";

    /** DB Item Column Name */
    public static final String CUST_CODES_4 = "CUST_CODES4";

    /** DB Item Column Name */
    public static final String CUST_CODES_5 = "CUST_CODES5";

    /** DB Item Column Name */
    public static final String CUST_CODES_6 = "CUST_CODES6";

    /** DB Item Column Name */
    public static final String METER_TYPE = "METER_TYPE";

    /** DB Item Column Name */
    public static final String START_READ = "START_READ";

    /** DB Item Column Name */
    public static final String END_READ = "END_READ";

    /** DB Item Column Name */
    public static final String TEST_COPIES = "TEST_COPIES";

    /** DB Item Column Name */
    public static final String COPIES_MADE = "COPIES_MADE";

    /** DB Item Column Name */
    public static final String MULTIPLIER = "MULTIPLIER";

    /** DB Item Column Name */
    public static final String ALLOWANCE = "ALLOWANCE";

    /** DB Item Column Name */
    public static final String AMOUNT = "AMOUNT";

    /** DB Item Column Name */
    public static final String COPIES_MADE_TXT = "COPIES_MADE_TXT";

    /** DB Item Column Name */
    public static final String MULTIPLIER_TXT = "MULTIPLIER_TXT";

    /** DB Item Column Name */
    public static final String ALLOWANCE_TXT = "ALLOWANCE_TXT";

    /** DB Item Column Name */
    public static final String AMOUNT_TXT = "AMOUNT_TXT";

    // START 2021/02/22 L.Mandanas [QC#58350, ADD]
    /** DB Item Column Name */
    public static final String DS_CONTR_BLLG_SCHD_PK = "DS_CONTR_BLLG_SCHD_PK";

    /** DB Item Column Name */
    public static final String ROLL_OVER_CNT = "ROLL_OVER_CNT";
    // END 2021/02/22 L.Mandanas [QC#58350, ADD]

    // START 2021/09/23 L.Mandanas [QC#58350, ADD]
    /** DB Item Column Name */
    public static final String USG_FREE_COPY_CNT = "USG_FREE_COPY_CNT";

    /** DB Item Column Name */
    public static final String FREE_COPY_CNT = "FREE_COPY_CNT";
    // END 2021/09/23 L.Mandanas [QC#58350, ADD]

    /** DB Item Column Name */
    public static final String MAX_DS_CONTRACT_BLLG_MTR_PK = "MAX_DS_CONTRACT_BLLG_MTR_PK";

    /** DB Item Column Name */
    public static final String MTR_LB_CD = "MTR_LB_CD";

    /** DB Item Column Name */
    public static final String QTY = "QTY";

    /** DB Item Column Name */
    public static final String RATE = "RATE";

    /** DB Item Column Name */
    public static final String RATE_TXT = "RATE_TXT";

    /** DB Item Column Name */
    public static final String INV_LINE_TP_CD = "INV_LINE_TP_CD";

    /** DB Item Column Name */
    public static final String BASE_SERIAL_NO = "BASE_SERIAL_NO";

    /** DB Item Column Name */
    public static final String METER_AMOUNT_TXT = "METER_AMOUNT_TXT";

    /** DB Item Column Name */
    public static final String BILLABLE_COPIES = "BILLABLE_COPIES";

    /** DB Item Column Name */
    public static final String DS_CONTRACT_BLLG_MTR_PK = "DS_CONTRACT_BLLG_MTR_PK";

    /** DB Item Column Name */
    public static final String ORDER_NO = "ORDER_NO";

    /** DB Item Column Name */
    public static final String TRACKING_NO = "TRACKING_NO";

    /** DB Item Column Name */
    public static final String SALES_REP = "SALES_REP";

    /** DB Item Column Name */
    public static final String SHIP_QTY = "SHIP_QTY";

    /** DB Item Column Name */
    public static final String ORDER_QTY = "ORDER_QTY";

    /** DB Item Column Name */
    public static final String PRICE_AMOUNT_TXT = "PRICE_AMOUNT_TXT";

    /** DB Item Column Name */
    public static final String LINE_TOTAL_AMOUNT_TXT = "LINE_TOTAL_AMOUNT_TXT";

    /** DB Item Column Name */
    public static final String DISPLAY_LINE_NO = "DISPLAY_LINE_NO";

    /** DB Item Column Name */
    public static final String MDSE_CD = "MDSE_CD";

    /** DB Item Column Name */
    public static final String MDSE_NM = "MDSE_NM";

    /** DB Item Column Name */
    public static final String DESCRIPTION = "DESCRIPTION";

    /** DB Item Column Name */
    public static final String ORDER_MDSE = "ORDER_MDSE";
    
    /** DB Item Column Name */
    public static final String ORDER_TAKE_MDSE = "ORDER_TAKE_MDSE";
    
    /** DB Item Column Name */
    public static final String CUSTOMER_MDSE_CODE = "CUSTOMER_MDSE_CODE";

    /** DB Item Column Name */
    public static final String CSMP_NO = "CSMP_NO";

    /** DB Item Column Name */
    public static final String BASE_CMPT_FLG = "BASE_CMPT_FLG";

    /** DB Item Column Name */
    public static final String DS_ORD_CATG_CD = "DS_ORD_CATG_CD";

    /** DB Item Column Name */
    public static final String DS_ORD_TP_CD = "DS_ORD_TP_CD";

    /** DB Item Column Name */
    public static final String DS_ORD_RSN_CD = "DS_ORD_RSN_CD";

    /** DB Item Column Name */
    public static final String LINE_COUNT_PER_POSITION_NO = "LINE_COUNT_PER_POSITION_NO";

    /** DB Item Column Name */
    public static final String FSR_NO = "FSR_NO";

    /** DB Item Column Name */
    public static final String TECH_CD = "TECH_CD";

    /** DB Item Column Name */
    public static final String TECH_NM = "TECH_NM";

    /** DB Item Column Name */
    public static final String SALES_REP_CODE = "SALES_REP_CODE";

    /** DB Item Column Name */
    public static final String ORD_DT = "ORD_DT";

    /** DB Item Column Name */
    public static final String AMT_RATE = "AMT_RATE";

    /** DB Item Column Name */
    public static final String QUOT_QTY = "QUOT_QTY";

    /** DB Item Column Name */
    public static final String AR_METER_FLG = "AR_METER_FLG";

    /** DB Item Column Name */
    public static final String B_BASE_CMPT_FLG = "B_BASE_CMPT_FLG";

    /** DB Item Column Name */
    public static final String CONS_BILL_NUM = "CONS_BILL_NUM";

    /** DB Item Column Name */
    public static final String CONTROL_REC_CD = "CONTROL_REC_CD";

    /** DB Item Column Name */
    public static final String INV_PRT_CTRL_PK = "INV_PRT_CTRL_PK";

    /** DB Item Column Name */
    public static final String BATCH_TP_NM = "BATCH_TP_NM";

    /** DB Item Column Name */
    public static final String CONS_BILL_DT = "CONS_BILL_DT";

    /** DB Item Column Name */
    public static final String CONS_CREATE_USER = "CONS_CREATE_USER";

    /** DB Item Column Name */
    public static final String CONS_BILL_TO_ACCT = "CONS_BILL_TO_ACCT";

    /** DB Item Column Name */
    public static final String CONS_BILL_TO_ACCT_NM = "CONS_BILL_TO_ACCT_NM";

    /** DB Item Column Name */
    public static final String CONS_BILL_TO_CD = "CONS_BILL_TO_CD";

    /** DB Item Column Name */
    public static final String CONS_BILL_TO_LOC_NUM = "CONS_BILL_TO_LOC_NUM";

    /** DB Item Column Name */
    public static final String CONS_BILL_DUE_DT = "CONS_BILL_DUE_DT";

    /** DB Item Column Name */
    public static final String CONS_BILL_TO_NM = "CONS_BILL_TO_NM";

    /** DB Item Column Name */
    public static final String CONS_BILL_TO_ATTENTION = "CONS_BILL_TO_ATTENTION";

    /** DB Item Column Name */
    public static final String CONS_BILL_TO_FST_ADDR = "CONS_BILL_TO_FST_ADDR";

    /** DB Item Column Name */
    public static final String CONS_BILL_TO_SCD_ADDR = "CONS_BILL_TO_SCD_ADDR";

    /** DB Item Column Name */
    public static final String CONS_BILL_TO_CTY = "CONS_BILL_TO_CTY";

    /** DB Item Column Name */
    public static final String CONS_BILL_TO_ST = "CONS_BILL_TO_ST";

    /** DB Item Column Name */
    public static final String C1ONS_BILL_TO_NM = "CONS_BILL_TO_NM";

    /** DB Item Column Name */
    public static final String CONS_BILL_TO_ZIP_CD = "CONS_BILL_TO_ZIP_CD";

    /** DB Item Column Name */
    public static final String CONS_REMIT_TO_EIN_CD = "CONS_REMIT_TO_EIN_CD";

    /** DB Item Column Name */
    public static final String CONS_REMIT_TO_NM = "CONS_REMIT_TO_NM";

    /** DB Item Column Name */
    public static final String CONS_REMIT_TO_FST_ADDR = "CONS_REMIT_TO_FST_ADDR";

    /** DB Item Column Name */
    public static final String CONS_REMIT_TO_SCD_ADDR = "CONS_REMIT_TO_SCD_ADDR";

    /** DB Item Column Name */
    public static final String CONS_REMIT_TO_CTY = "CONS_REMIT_TO_CTY";

    /** DB Item Column Name */
    public static final String CONS_REMIT_TO_ST = "CONS_REMIT_TO_ST";

    /** DB Item Column Name */
    public static final String CONS_REMIT_TO_POST = "CONS_REMIT_TO_POST";

    /** DB Item Column Name */
    public static final String CONS_TERM_CD = "CONS_TERM_CD";

    /** DB Item Column Name */
    public static final String CONS_TERMS = "CONS_TERMS";

    /** DB Item Column Name */
    public static final String PMT_TERM_CASH_DISC_DESC_TXT = "PMT_TERM_CASH_DISC_DESC_TXT";

    /** DB Item Column Name */
    public static final String CR_REBIL_RSN_CATG_CD = "CR_REBIL_RSN_CATG_CD";
    
    /** DB Item Column Name */
    public static final String CCY_SGN_TXT = "CCY_SGN_TXT";
    
    /** DB Item Column Name */
    public static final String MC_CNT = "MC_CNT";
    
    /** DB Item Column Name */
    public static final String LOC_CNT = "LOC_CNT";

    /** DB Item Column Name */
    public static final String MACH_CNT = "MACH_CNT";
    
    /** DB Item Column Name */
    public static final String CONSL_BILL_INV_DT = "CONSL_BILL_INV_DT";
    
    /** DB Item Column Name */
    public static final String CONSL_BILL_TOT_AMT = "CONSL_BILL_TOT_AMT";
    
    /** DB Item Column Name */
    public static final String CONSL_BILL_INV_CRAT_PSN_CD = "CONSL_BILL_INV_CRAT_PSN_CD";
    
    /** DB Item Column Name */
    public static final String ORIG_CONSL_BILL_NUM = "ORIG_CONSL_BILL_NUM";
    
    /** DB Item Column Name */
    public static final String INV_PRT_FLEET_LOC_PK = "INV_PRT_FLEET_LOC_PK";

    /** DB Item Column Name */
    public static final String SOURCE = "SOURCE";
    
    /** DB Item Column Name */
    public static final String PRODUCT_TYPE = "PRODUCT_TYPE";
    
    /** DB Item Column Name */
    public static final String INV_TOT_DEAL_SLS_AMT = "INV_TOT_DEAL_SLS_AMT";

    /** DB Item Column Name */
    public static final String INV_TOT_DEAL_FRT_AMT = "INV_TOT_DEAL_FRT_AMT";

    /** DB Item Column Name */
    public static final String INV_TOT_DEAL_TAX_AMT = "INV_TOT_DEAL_TAX_AMT";

    /** DB Item Column Name */
    public static final String INV_TOT_DEAL_NET_AMT = "INV_TOT_DEAL_NET_AMT";
    
    /** DB Item Column Name */
    public static final String STATE_TAX_TXT = "STATE_TAX_TXT";
    
    /** DB Item Column Name */
    public static final String COUNTY_TAX_TXT = "COUNTY_TAX_TXT";

    /** DB Item Column Name */
    public static final String CITY_TAX_TXT = "CITY_TAX_TXT";
    
    /** DB Item Column Name */
    public static final String TOTAL_TAX_TXT = "TOTAL_TAX_TXT";
    
    /** DB Item Column Name */
    public static final String TOTAL_AMT_TXT = "TOTAL_AMT_TXT";
    
    /** DB Item Column Name */
    public static final String TOTAL_AMOUNT_TXT = "TOTAL_AMOUNT_TXT";

    /** DB Item Column Name */
    public static final String BASE_CHRG_COUNT = "BASE_CHRG_COUNT";
    
    /** DB Item Column Name */
    public static final String METER_CHRG_COUNT = "METER_CHRG_COUNT";
    
    /** DB Item Column Name */
    public static final String SVC_CONTR_MTR_BLLG_PK = "SVC_CONTR_MTR_BLLG_PK";
    
    /** DB Item Column Name */
    public static final String CHARGE = "CHARGE";
    
    /** DB Item Column Name */
    public static final String INVOICE_BOL_NUM = "INVOICE_BOL_NUM";
    
    /** DB Item Column Name */
    public static final String FREIGHT = "FREIGHT";
    
    /** DB Item Column Name */
    public static final String FREIGHT_STATE_TAX = "FREIGHT_STATE_TAX";
    
    /** DB Item Column Name */
    public static final String FREIGHT_COUNTY_TAX = "FREIGHT_COUNTY_TAX";
    
    /** DB Item Column Name */
    public static final String FREIGHT_CITY_TAX = "FREIGHT_CITY_TAX";
    
    /** DB Item Column Name */
    public static final String FREIGHT_TOTAL_TAX = "FREIGHT_TOTAL_TAX";
    
    /** DB Item Column Name */
    public static final String PRICE_AMOUNT = "PRICE_AMOUNT";
    
    /** DB Item Column Name */
    public static final String LINE_TOTAL_AMOUNT = "LINE_TOTAL_AMOUNT";
    
    /** DB Item Column Name */
    public static final String CHARGE_TXT = "CHARGE_TXT";
    
    /** DB Item Column Name */
    public static final String SYS_SRC_CD = "SYS_SRC_CD";
    
    /** DB Item Column Name */
    public static final String BALANCE_AMT = "BALANCE_AMT";
    
    /** DB Item Column Name */
    public static final String BATCH_TP = "BATCH_TP";
    
    /** DB Item Column Name */
    public static final String CONSL_TOT_DEAL_NET_AMT = "CONSL_TOT_DEAL_NET_AMT";
    
    /** DB Item Column Name */
    public static final String DETAIL_DISPLAY_FLG = "DETAIL_DISPLAY_FLG";
    
    /** DB Item Column Name */
    public static final String INV_TOT_CHRG_AMT_TXT = "INV_TOT_CHRG_AMT_TXT";
    
    /** DB Item Column Name */
    public static final String INV_FRT_AMT_TXT = "INV_FRT_AMT_TXT";
    
    /** DB Item Column Name */
    public static final String INV_FRT_TAX_AMT_TXT = "INV_FRT_TAX_AMT_TXT";
    
    /** DB Item Column Name */
    public static final String INV_TOT_AMT_TXT = "INV_TOT_AMT_TXT";
    
    /** DB Item Column Name */
    public static final String INV_BAL_AMT_TXT = "INV_BAL_AMT_TXT";
    
    /** DB Item Column Name */
    public static final String INV_PRT_FLEET_SUB_TOT_PK = "INV_PRT_FLEET_SUB_TOT_PK";
    
    /** DB Item Column Name */
    public static final String DS_CONTR_PK = "DS_CONTR_PK";

    /** DB Item Column Name */
    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";
    
    /** DB Item Column Name */
    public static final String INV_PRT_MAINT_SUB_TOT_PK = "INV_PRT_MAINT_SUB_TOT_PK";
    
    /** DB Item Column Name */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";
    
    /** DB Item Column Name */
    public static final String FIRST_BLLG_ATTRB_VAL_TXT = "FIRST_BLLG_ATTRB_VAL_TXT";
    
    /** DB Item Column Name */
    public static final String SCD_BLLG_ATTRB_VAL_TXT = "SCD_BLLG_ATTRB_VAL_TXT";
    
    /** DB Item Column Name */
    public static final String THIRD_BLLG_ATTRB_VAL_TXT = "THIRD_BLLG_ATTRB_VAL_TXT";
    
    /** DB Item Column Name */
    public static final String FRTH_BLLG_ATTRB_VAL_TXT = "FRTH_BLLG_ATTRB_VAL_TXT";
    
    /** DB Item Column Name */
    public static final String FIFTH_BLLG_ATTRB_VAL_TXT = "FIFTH_BLLG_ATTRB_VAL_TXT";
    
    /** DB Item Column Name */
    public static final String SIXTH_BLLG_ATTRB_VAL_TXT = "SIXTH_BLLG_ATTRB_VAL_TXT";
    
    /** DB Item Column Name */
    public static final String CR_CARD_CUST_REF_NUM = "CR_CARD_CUST_REF_NUM";
    
    /** DB Item Column Name */
    public static final String EXISTS_FLG = "EXISTS_FLG";
    
    /** DB Item Column Name */
    public static final String DPLY_LINE_NUM = "DPLY_LINE_NUM";
    
    // QC#20141 add Start
    /** DB Item Column Name */
    public static final String CR_DR_RSN_SUB_CD = "CR_DR_RSN_SUB_CD";
    
    /** DB Item Column Name */
    public static final String EASY_PACK_TP_CD = "EASY_PACK_TP_CD";
    
    /** DB Item Column Name */
    public static final String TOTAL_SQ_FEET = "TOTAL_SQ_FEET";
    
    /** DB Item Column Name */
    public static final String DS_ORD_POSN_NUM = "DS_ORD_POSN_NUM";

    /** DB Item Column Name */
    public static final String CUST_CARE_TKT_NUM = "CUST_CARE_TKT_NUM";

    /** DB Item Column Name */
    public static final String ORIG_INV_NUM = "ORIG_INV_NUM";

    /** DB Item Column Name */
    public static final String INV_PRINT_STYLE_CD = "INV_PRINT_STYLE_CD";

    /** DB Item Column Name */
    public static final String CONFIG_SUM_PRICE_AMOUNT = "CONFIG_SUM_PRICE_AMOUNT";

    /** DB Item Column Name */
    public static final String CONFIG_SUM_LINE_TOTAL_AMOUNT = "CONFIG_SUM_LINE_TOTAL_AMOUNT";

    // START 2018/09/10 T.Ogura [QC#27999,ADD]
    /** DB Item Column Name */
    public static final String INV_LINE_CATG_CD = "INV_LINE_CATG_CD";
    // END   2018/09/10 T.Ogura [QC#27999,ADD]

    //QC#27069 Add Start
    public static final String MAN_INV_FLG = "MAN_INV_FLG";
    //QC#27069 Add End

    //Short Fall Code
    public static final String CR_DR_RSN_SUB_SHORT_FALL_CD = "300001";
    // QC#20141 add End

    // 2019/07/16 QC#50885 Add Start
    /**  DB Item Column Name */
    public static final String IS_MANTE = "IS_MANTE";
    // 2019/07/16 QC#50885 Add End

    // START 2023/11/30 R.Takau [QC#61584,ADD]
    /**  DB Item Column Name */
    public static final String INV_LINE_DEAL_TAX_AMT = "TAX_AMT";

    /**  DB Item Column Name */
    public static final String SHIP_TO_ST_CD = "SHIP_TO_ST_CD";

    /**  DB Item Column Name */
    public static final String INV_PRINT_CATG_CD = "INV_PRINT_CATG_CD";
    // END 2023/11/30 R.Takau [QC#61584,ADD]

    /** Map Key */
    public static final String INV_PRT_CTRL = "INV_PRT_CTRL";
    
    /** Map Key */
    public static final String INV_PRT_FLEET_SUB_TOT = "INV_PRT_FLEET_SUB_TOT";
    
    /** Map Key */
    public static final String INV_PRT_MAINT_SUB_TOT = "INV_PRT_MAINT_SUB_TOT";
    
    /** Map Key */
    public static final String INV_PRT_SLS_PART_SUB_TOT = "INV_PRT_SLS_PART_SUB_TOT";

    /** Map Key */
    public static final String NMZC610001PMSG_FOR_RULE = "NMZC610001PMSG_FOR_RULE";
    
    /** Map Key */
    public static final String NMZC610001PMSG_FOR_MSG = "NMZC610001PMSG_FOR_MSG";
    
    /** Map Key */
    public static final String FOOTER_COMMENT = "FOOTER_COMMENT";

    /** Map Key */
    public static final String CONSL_LINE = "getConslLine";

    /** Map Key */
    public static final String PMT_TERM_INFO_LIST = "PMT_TERM_INFO_LIST";
    
    /** Map Key */
    public static final String SPLY_CONTR_BY_BILL_TO_ACCT_CUST = "SPLY_CONTR_BY_BILL_TO_ACCT_CUST";
    
    /** Map Key */
    public static final String ORD_CATG_BIZ_CTX_EXISTS_FLG = "ORD_CATG_BIZ_CTX_EXISTS_FLG";

    /** Map Key */
    public static final String SPECIAL_BILLING_FLG = "SPECIAL_BILLING_FLG";

    /**
     * RETURN_CD_NORMAL -- 0000
     */
    public static String RETURN_CD_NORMAL = "0000";

    /**
     * Meter Label cut off length
     */
    public static int MTR_LB_CUT_OFF_LEN = 30;
    
    /**
     * Bill To Account Name
     */
    public static int BILL_TO_ACCT_NM_CUT_OFF_LEN = 60;
    
    /**
     * Invoice Comment Max Length
     */
    public static int INV_CMT_CUT_OFF_LEN = 1000;
    
    /**
     * Invoice Line Comment Max Length
     */
    // 2019/01/29 QC#29371 Mod Start
//    public static int INV_LINE_CMT_CUT_OFF_LEN = 130;
    public static int INV_LINE_CMT_CUT_OFF_LEN = 1000;
    // 2019/01/29 QC#29371 Mod End
    
    /**
     * Invoice is part of a Consolidated Bill
     */
    public static final String NWCB0100_PRT_OF_CONSL_MSG = "NWCB0100_PRT_OF_CONSL_MSG";
    
    /** Mail Group From */
    public static final String GROUP_FROM = "FROM0005";

    /** Mail template ID */
    public static final String MAIL_TEMPLATE_ID = "NWCB0100M001";

    /** Batch Name */
    public static final String BATCH_NM = "Invoice Print Data Creation Batch";
    
    /** Line Feed Code */
    public static final String LINE_FEED_CODE = System.getProperty("line.separator");
    
    /** Mail Info : Message : Bill/Invoice No. */
    public static final String NUMBER = "NUMBER";

    /** Mail Info : Message */
    public static final String MESSAGE = "MESSAGE";

    /** Mail Info : SEPARATOR */
    public static final String SEPARATOR = ", ";

    /** OM Invoice BO Sort First.(0:INV->BO, 1:BO->INV, 2:Mix) */
    public static final String INV_PRINT_OM_INV_BO_SORT_FIRST = "0";

    /** OM Invoice BO Sort Second.(0:INV->BO, 1:BO->INV, 2:Mix) */
    public static final String INV_PRINT_OM_INV_BO_SORT_SCD = "1";

    // QC#25026
    /** ORIG_CONSL_BILL_PK */
    public static final String ORIG_CONSL_BILL_PK = "ORIG_CONSL_BILL_PK";
    /** Dummy COA_PROJ_CD */
    public static final String COA_PROJ_CD_ZZZ = "ZZZ";

    // QC#25431 Add Start
    /** Invoice Print Exclusive */
    public static final String INV_PRINT_EXCLUSIVE = "0";
    // QC#25431 Add End

    // QC#29371 SRNO.11 2019/02/01 Add Start
    public static final String INV_PRT_PRMO_ITEMS = "INV_PRT_PRMO_ITEMS";

    public static final String PRMO_ITEM_PRINT_FLG = "PRMO_ITEM_PRINT_FLG";

    public static final String COA_MDSE_TP_CD = "COA_MDSE_TP_CD";
    // QC#29371 SRNO.11 2019/02/01 Add End

    // 2019/08/22 QC#52928 Add Start
    public static final String IS_EXISTS_BASE_CMPT = "IS_EXISTS_BASE_CMPT";
    // 2019/08/22 QC#52928 Add End

    // 2019/09/04 QC#53254 Add Start
    /** Var_char_Const Credit Card failed: "Your credit card has been declined. Please contact Customer Service" */
    public static final String NWCB0100_CR_CARD_MSG_04 = "NWCB0100_CR_CARD_MSG_04";
    // 2019/09/04 QC#53254 Add End

    // 2019/10/11 QC#53888 Add Start
    /** DB Owner */
    public static final String CONST_DB_SCHEMA = "NMAB2650_DB_SCHEMA";

    /** Table Name : INV_PRT_SVC_INV_LINE_WRK */
    public static final String WRK_TBL_NM = "INV_PRT_SVC_INV_LINE_WRK";
    // 2019/10/11 QC#53888 Add End
    
    // 2019/11/02 QC#53873 Add Start
    /**CONS_BILL_STATUS */
    public static final String CONS_BILL_STATUS = "CONS_BILL_STATUS";
    // 2019/11/02 QC#53873 Add End

    // START 2022/08/03 [QC#60129, ADD]
    /** Var_char_Const Credit Card failed: "One or more invoices paid by card has been declined. Please contact Customer Service." */
    public static final String NWCB0100_CR_CARD_MSG_05 = "NWCB0100_CR_CARD_MSG_05";
    // END   2022/08/03 [QC#60129, ADD]
}
