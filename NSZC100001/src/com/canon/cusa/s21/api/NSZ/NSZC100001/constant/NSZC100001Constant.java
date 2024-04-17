/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC100001.constant;

/**
 * <pre>
 * Contract Agreement Letter Creation API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/27/2016   Hitachi         T.Tsuchida      Create          #N/A
 * 08/08/2017   Hitachi         K.Kim           Update          QC#20351
 * 08/21/2017   CITS            M.Naito         Update          QC#8661
 * 02/03/2021   CITS            R.Mercado       Update          QC#58314
 * 02/04/2021   CITS            Y.Penequito     Update          QC#58312
 * 03/17/2021   CITS            L.Mandanas      Update          QC#58314-1
 * </pre>
 */
public class NSZC100001Constant {

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "Sales Date" is a mandatory field.
     */
    public static final String NSZM0002E = "NSZM0002E";

    /**
     * Contract does not exist.
     */
    public static final String NSZM0434E = "NSZM0434E";

    /**
     * Input parameter "Application Id" is a mandatory field.
     */
    public static final String NSZM0854E = "NSZM0854E";

    /**
     * Input parameter "Output Option Code" is a mandatory field.
     */
    public static final String NSZM0855E = "NSZM0855E";

    /**
     * Input parameter "DS Contract Pk" is a mandatory field.
     */
    public static final String NSZM0856E = "NSZM0856E";

    /**
     * Input parameter "DS Contract Detail Pk" is a mandatory field.
     */
    public static final String NSZM0857E = "NSZM0857E";

    /**
     * Failed to insert the PRC_RNW_LTR_WRK.
     */
    public static final String NSZM0858E = "NSZM0858E";

    /**
     * Failed to insert the TERM_COND_LTR_WRK.
     */
    public static final String NSZM0859E = "NSZM0859E";

    // START 2017/08/08 K.Kim [QC#20351, ADD]
    /** ZZZM9004E: This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";
    // START 2017/08/08 K.Kim [QC#20351, ADD]

    //START 2017/08/21 M.Naito [QC#8661, ADD]
    /**
     * Input parameter "Report ID" is a mandatory field.
     */
    public static final String NSZM1294E = "NSZM1294E";

    /**
     * An error occurred when attempting to change date format.
     */
    public static final String NSZM0046E = "NSZM0046E";
    //END 2017/08/21 M.Naito [QC#8661, ADD]
    // QC#58314 Add Start
    /**
     * Active CumCopy and Allowance coexist.
     */
    public static final String NSZM1378E = "NSZM1378E";
    // QC#58314 Add End
    /**
     * MAX_THRU_DT : 29991231
     */
    public static final String MAX_THRU_DT = "29991231";

    /**
     * LVL_USAGE : 2
     */
    public static final String LVL_USAGE = "2";

    /**
     * FLEET_CONTRACT : Fleet Contract
     */
    public static final String FLEET_CONTRACT = "Fleet Contract";

    /**
     * MAP_KEY : GLBL_CMPY_CD
     */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /**
     * MAP_KEY : SVC_PRC_RNW_LTR_WRK_PK
     */
    public static final String SVC_PRC_RNW_LTR_WRK_PK = "SVC_PRC_RNW_LTR_WRK_PK";

    /**
     * MAP_KEY : LINE_LV
     */
    public static final String LINE_LV = "LINE_LV";

    /**
     * MAP_KEY : SVC_PHYS_MTR_PK
     */
    public static final String SVC_PHYS_MTR_PK = "SVC_PHYS_MTR_PK";


    /**
     * MAP_KEY : DS_CONTR_PK
     */
    public static final String DS_CONTR_PK = "DS_CONTR_PK";

    /**
     * MAP_KEY : DS_CONTR_DTL_PK
     */
    public static final String DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";

    /**
     * MAP_KEY : DS_CONTR_DTL_SORT_NUM
     */
    public static final String DS_CONTR_DTL_SORT_NUM = "DS_CONTR_DTL_SORT_NUM";

    /**
     * MAP_KEY : SLS_DT
     */
    public static final String SLS_DT = "SLS_DT";

    /**
     * MAP_KEY : BIZ_APP_ID
     */
    public static final String BIZ_APP_ID = "BIZ_APP_ID";

    /**
     * MAP_KEY : OTPT_OP_CD
     */
    public static final String OTPT_OP_CD = "OTPT_OP_CD";

    /**
     * MAP_KEY : TOC_NM
     */
    public static final String TOC_NM = "TOC_NM";

    /**
     * MAP_KEY : BILL_TO_CUST_CD
     */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /**
     * MAP_KEY : BILL_TO_LOC_NM
     */
    public static final String BILL_TO_LOC_NM = "BILL_TO_LOC_NM";

    /**
     * MAP_KEY : BILL_TO_FIRST_LINE_ADDR
     */
    public static final String BILL_TO_FIRST_LINE_ADDR = "BILL_TO_FIRST_LINE_ADDR";

    /**
     * MAP_KEY : BILL_TO_SCD_LINE_ADDR
     */
    public static final String BILL_TO_SCD_LINE_ADDR = "BILL_TO_SCD_LINE_ADDR";

    /**
     * MAP_KEY : BILL_TO_THIRD_LINE_ADDR
     */
    public static final String BILL_TO_THIRD_LINE_ADDR = "BILL_TO_THIRD_LINE_ADDR";

    /**
     * MAP_KEY : BILL_TO_FRTH_LINE_ADDR
     */
    public static final String BILL_TO_FRTH_LINE_ADDR = "BILL_TO_FRTH_LINE_ADDR";

    /**
     * MAP_KEY : BILL_TO_CTY_ADDR
     */
    public static final String BILL_TO_CTY_ADDR = "BILL_TO_CTY_ADDR";

    /**
     * MAP_KEY : BILL_TO_ST_CD
     */
    public static final String BILL_TO_ST_CD = "BILL_TO_ST_CD";

    /**
     * MAP_KEY : BILL_TO_POST_CD
     */
    public static final String BILL_TO_POST_CD = "BILL_TO_POST_CD";

    /**
     * MAP_KEY : BILL_TO_CTRY_NM
     */
    public static final String BILL_TO_CTRY_NM = "BILL_TO_CTRY_NM";

    /**
     * MAP_KEY : SHIP_TO_CUST_CD
     */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /**
     * MAP_KEY : SHIP_TO_LOC_NM
     */
    public static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    /**
     * MAP_KEY : SHIP_TO_FIRST_LINE_ADDR
     */
    public static final String SHIP_TO_FIRST_LINE_ADDR = "SHIP_TO_FIRST_LINE_ADDR";

    /**
     * MAP_KEY : SHIP_TO_SCD_LINE_ADDR
     */
    public static final String SHIP_TO_SCD_LINE_ADDR = "SHIP_TO_SCD_LINE_ADDR";

    /**
     * MAP_KEY : SHIP_TO_THIRD_LINE_ADDR
     */
    public static final String SHIP_TO_THIRD_LINE_ADDR = "SHIP_TO_THIRD_LINE_ADDR";

    /**
     * MAP_KEY : SHIP_TO_FRTH_LINE_ADDR
     */
    public static final String SHIP_TO_FRTH_LINE_ADDR = "SHIP_TO_FRTH_LINE_ADDR";

    /**
     * MAP_KEY : SHIP_TO_CTY_ADDR
     */
    public static final String SHIP_TO_CTY_ADDR = "SHIP_TO_CTY_ADDR";

    /**
     * MAP_KEY : SHIP_TO_ST_CD
     */
    public static final String SHIP_TO_ST_CD = "SHIP_TO_ST_CD";

    /**
     * MAP_KEY : SHIP_TO_POST_CD
     */
    public static final String SHIP_TO_POST_CD = "SHIP_TO_POST_CD";

    /**
     * MAP_KEY : SHIP_TO_CTRY_NM
     */
    public static final String SHIP_TO_CTRY_NM = "SHIP_TO_CTRY_NM";

    /**
     * MAP_KEY : DS_CONTR_NUM
     */
    public static final String DS_CONTR_NUM = "DS_CONTR_NUM";
    /**
     * MAP_KEY : BASE_BLLG_CYCLE_DESC_TXT
     */
    public static final String BASE_BLLG_CYCLE_DESC_TXT = "BASE_BLLG_CYCLE_DESC_TXT";

    /**
     * MAP_KEY : USG_BLLG_CYCLE_DESC_TXT
     */
    public static final String USG_BLLG_CYCLE_DESC_TXT = "USG_BLLG_CYCLE_DESC_TXT";
    
    // QC#58314 Add Start
    /**
     * MAP_KEY : CUM_BLLG_CYCLE_NM
     */
    public static final String CUM_BLLG_CYCLE_NM = "CUM_BLLG_CYCLE_NM";
    // QC#58314 Add End

    /**
     * MAP_KEY : CONTR_EFF_FROM_DT_TXT
     */
    public static final String CONTR_EFF_FROM_DT_TXT = "CONTR_EFF_FROM_DT_TXT";

    /**
     * MAP_KEY : CONTR_EFF_THRU_DT_TXT
     */
    public static final String CONTR_EFF_THRU_DT_TXT = "CONTR_EFF_THRU_DT_TXT";

    /**
     * MAP_KEY : MDL_NM
     */
    public static final String MDL_NM = "MDL_NM";

    /**
     * MAP_KEY : SER_NUM
     */
    public static final String SER_NUM = "SER_NUM";

    /**
     * MAP_KEY : START_READ_MTR_CNT_QTY_TXT
     */
    public static final String START_READ_MTR_CNT_QTY_TXT = "START_READ_MTR_CNT_QTY_TXT";

    /**
     * MAP_KEY : XS_MTR_COV_COPY_QTY_TXT
     */
    public static final String XS_MTR_COV_COPY_QTY_TXT = "XS_MTR_COV_COPY_QTY_TXT";
    
    // QC#58314 Add Start
    /**
     * MAP_KEY : CUM_COPY_CNT
     */
    public static final String CUM_COPY_CNT = "CUM_COPY_CNT";
    // QC#58314 Add End

    // START 2021/03/17 L.Mandanas [QC#58314-1, ADD]
    /**
     * MAP_KEY : CUM_COPY_END_DT.
     */
    public static final String CUM_COPY_END_DT = "CUM_COPY_END_DT";

    /**
     * MAP_KEY : UPLFT_PCY_DT.
     */
    public static final String UPLFT_PCY_DT = "UPLFT_PCY_DT";
    // END 2021/03/17 L.Mandanas [QC#58314-1, ADD]
    
    /**
     * MAP_KEY : XS_MTR_COPY_FROM_QTY_TXT
     */
    public static final String XS_MTR_COPY_FROM_QTY_TXT = "XS_MTR_COPY_FROM_QTY_TXT";

    /**
     * MAP_KEY : XS_MTR_COPY_THRU_QTY_TXT
     */
    public static final String XS_MTR_COPY_THRU_QTY_TXT = "XS_MTR_COPY_THRU_QTY_TXT";

    /**
     * MAP_KEY : XS_MTR_AMT_TXT
     */
    public static final String XS_MTR_AMT_TXT = "XS_MTR_AMT_TXT";

    /**
     * MAP_KEY : BASE_PRC_DEAL_AMT_TXT
     */
    public static final String BASE_PRC_DEAL_AMT_TXT = "BASE_PRC_DEAL_AMT_TXT";

    /**
     * MAP_KEY : TOT_DEAL_NET_AMT_TXT
     */
    public static final String TOT_DEAL_NET_AMT_TXT = "TOT_DEAL_NET_AMT_TXT";

    /**
     * US_DOLLAR : $
     */
    public static final String US_DOLLAR = "$";

    /**
     * MAP_KEY : SPEC_SIDE_TP_CD
     */
    public static final String SPEC_SIDE_TP_CD = "SPEC_SIDE_TP_CD";

    /**
     * MAP_KEY : SVC_TERM_COND_HDR_NUM
     */
    public static final String SVC_TERM_COND_HDR_NUM = "SVC_TERM_COND_HDR_NUM";

    /**
     * MAP_KEY : SVC_TERM_COND_DTL_NUM
     */
    public static final String SVC_TERM_COND_DTL_NUM = "SVC_TERM_COND_DTL_NUM";

    /**
     * MAP_KEY : TTL_VAL_TXT
     */
    public static final String TTL_VAL_TXT = "TTL_VAL_TXT";

    /**
     * MAP_KEY : DESC_VAL_TXT
     */
    public static final String DESC_VAL_TXT = "DESC_VAL_TXT";

    /**
     * MAP_KEY : TERM_COND_VRSN_TXT
     */
    public static final String TERM_COND_VRSN_TXT = "TERM_COND_VRSN_TXT";

    //START 2017/08/21 M.Naito [QC#8661, ADD]
    /**
     * MAP_KEY : OLD_XS_MTR_AMT_TXT
     */
    public static final String OLD_XS_MTR_AMT_TXT = "OLD_XS_MTR_AMT_TXT";

    /**
     * MAP_KEY : OLD_BASE_PRC_DEAL_AMT_TXT
     */
    public static final String OLD_BASE_PRC_DEAL_AMT_TXT = "OLD_BASE_PRC_DEAL_AMT_TXT";

    /**
     * MAP_KEY : SVC_PRC_RNW_DESC_LINE_TXT
     */
    public static final String SVC_PRC_RNW_DESC_LINE_TXT = "SVC_PRC_RNW_DESC_LINE_TXT";

    /**
     * MAP_KEY : BLLG_MTR_LB_CD
     */
    public static final String BLLG_MTR_LB_CD = "BLLG_MTR_LB_CD";
 
    /**
     * DATE_FORMAT_DDMMMYYYY : dd-MMM-yyyy
     */
    public static final String DATE_FORMAT_DDMMMYYYY = "dd-MMM-yyyy";

    /**
     * SPACE
     */
    public static final String SPACE = " ";

    /**
     * BIZ_APP_ID : NSAB0030
     */
    public static final String BIZ_APP_ID_NSAB0030 = "NSAB0030";

    /**
     * BIZ_APP_ID : NSAB0360
     */
    public static final String BIZ_APP_ID_NSAB0360 = "NSAB0360";

    /**
     * LANG_KEY : 6300
     */
    public static final String LANG_KEY_6300 = "6300";

    /**
     * LANG_KEY : 23215
     */
    public static final String LANG_KEY_23215 = "23215";

    /**
     * LANG_KEY : 23216
     */
    public static final String LANG_KEY_23216 = "23216";

    /**
     * LANG_KEY : 23217
     */
    public static final String LANG_KEY_23217 = "23217";

    /**
     * LANG_KEY : 23218
     */
    public static final String LANG_KEY_23218 = "23218";

    /**
     * AMOUNT_SCALE : 2
     */
    public static final int AMOUNT_SCALE_2 = 2;

    /**
     * AMOUNT_SCALE : 6
     */
    public static final int AMOUNT_SCALE_6= 6;

    //END 2017/08/21 M.Naito [QC#8661, ADD]
    // START 2021/02/04 Y.Penequito [QC#58312, MOD]
    /**
     * LINE_LV_THREE : 3
     */
    public static final String LINE_LV_THREE = "3";

    /**
     * LINE_LV_THREE : 1
     */
    public static final String LINE_LV_ONE = "1";

    /**
     * PRINT_BASE : 1
     */
    public static final String PRINT_BASE = "1";
    // END 2021/02/04 Y.Penequito [QC#58312, MOD]

    // START 2021/03/17 L.Mandanas [QC#58314-1, ADD]
    /**
     * SEMIANNUAL : SemiAnnual.
     */
    public static final String SEMIANNUAL = "SemiAnnual";

    /**
     * SEMIANNUALLY : Semiannually.
     */
    public static final String SEMIANNUALLY = "Semiannually";

    // START 2021/04/19 L.Mandanas [QC#58314-1, ADD]
    /**
     * LINE_LV_TWO : 2.
     */
    public static final String LINE_LV_TWO = "2";
    /**
     * LINE_LV_FOUR : 4.
     */
    public static final String LINE_LV_FOUR = "4";
    // END 2021/04/19 L.Mandanas [QC#58314-1, ADD]
    // END 2021/03/17 L.Mandanas [QC#58314-1, ADD]
}
