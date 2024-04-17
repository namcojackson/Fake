/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC061001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Service Pricing API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/17/2015   Hitachi         T.Tsuchida      Create          NA#Service Pricing API
 * 04/15/2016   Hitachi         A.Kohinata      Update          QC#5489
 * 03/02/2017   Hitachi         K.Ochiai        Update          QC#17715
 * 05/19/2017   Hitachi         K.Kitachi       Update          QC#18213
 * 07/26/2017   Hitachi         T.Tomita        Update          QC#17129
 * 10/02/2017   Hitachi         K.Kim           Update          QC#21168
 * 07/26/2018   Hitachi         T.Tomita        Update          QC#27418
 * 2018/12/04   Hitachi         K.Kitachi       Update          QC#28635
 * 2019/02/22   Hitachi         T.Tomita        Update          QC#30413
 * </pre>
 */
public final class NSZC061001Constant {

    /**
     * Process Mode: Call Creation
     */
    public static final String PROCESS_MODE_CALL_CREATION = "0";

    /**
     * Process Mode: Call Close
     */
    public static final String PROCESS_MODE_CALL_CLOSE = "1";

    // START 2017/03/02 K.Ochiai [QC#17715, MOD]
    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "Process Mode" is a mandatory field.
     */
    public static final String NSZM0003E = "NSZM0003E";

    /**
     * Input parameter "Service Machine Master PK" is a mandatory field.
     */
    public static final String NSZM0074E = "NSZM0074E";

    /**
     * Input parameter "Service Task Number" is a mandatory field.
     */
    public static final String NSZM0082E = "NSZM0082E";

    /**
     * Input parameter "FSR Visit Arrive Date" is a mandatory field.
     */
    public static final String NSZM0214E = "NSZM0214E";

    /**
     * Input parameter "FSR Visit Arrive Time" is a mandatory field.
     */
    public static final String NSZM0215E = "NSZM0215E";

    /**
     * Input parameter "Service Travel Time Number" is a mandatory field.
     */
    public static final String NSZM0218E = "NSZM0218E";

    /**
     * Input parameter "Service Labor Time Number" is a mandatory field.
     */
    public static final String NSZM0247E = "NSZM0247E";

    /**
     * Input parameter "FSR Number" is a mandatory field.
     */
    public static final String NSZM0291E = "NSZM0291E";

    // START 2017/10/02 K.Kim [QC#21168,ADD]
    /** 
     * The entered 'Call Type' does not exist.
     */
    public static final String NSZM0549E = "NSZM0549E";
    // END 2017/10/02 K.Kim [QC#21168,ADD]

    /**
     * Input parameter "Start Date" is a mandatory field.
     */
    public static final String NSZM0699E = "NSZM0699E";

    /**
     * Input parameter "Start Time" is a mandatory field.
     */
    public static final String NSZM0700E = "NSZM0700E";
    // END 2017/03/02 K.Ochiai [QC#17715, MOD]

    /**
     * Process Mode is not valid.
     */
    public static final String NSZM0039E = "NSZM0039E";

    /**
     * The entered 'Service Task Number' does not exist.
     */
    public static final String NSZM0302E = "NSZM0302E";

    /**
     * Date Attribute Value Code
     */
    public static final String DT_ATRB_VAL_CD = "1";

    /**
     * Key Of VAR_CHAR_CONST:After Hours Shift Bill Flag
     */
    public static final String AH_BILL_FLG = "AH_BILL_FLG";

    /**
     * Key Of VAR_CHAR_CONST:After Hours Shift Bill Rate
     */
    public static final String AH_BILL_RATE = "AH_BILL_RATE";

    // add start 2016/04/15 CSA Defect#5489
    /**
     * Key Of VAR_CHAR_CONST:After Hours Working Program
     */
    public static final String TERM_COND_AFTER_HOURS_WRK_PGM = "TERM_COND_AFTER_HOURS_WRK_PGM";
    // add end 2016/04/15 CSA Defect#5489

    // Add Start 2017/07/26 QC#17129
    /**
     * Key Of VAR_CHAR_CONST: PARTS_ESS_PRC_CATG_CD
     */
    public static final String PARTS_ESS_PRC_CATG_CD = "PARTS_ESS_PRC_CATG_CD";

    /**
     * Key Of VAR_CHAR_CONST: PARTS_LFS_PRC_CATG_CD
     */
    public static final String PARTS_LFS_PRC_CATG_CD = "PARTS_LFS_PRC_CATG_CD";

    /**
     * Key Of VAR_CHAR_CONST: PARTS_PPS_PRC_CATG_CD
     */
    public static final String PARTS_PPS_PRC_CATG_CD = "PARTS_PPS_PRC_CATG_CD";

    /**
     * Key Of VAR_CHAR_CONST: DRUM_ESS_PRC_CATG_CD
     */
    public static final String DRUM_ESS_PRC_CATG_CD = "DRUM_ESS_PRC_CATG_CD";

    /**
     * Key Of VAR_CHAR_CONST: DRUM_LFS_PRC_CATG_CD
     */
    public static final String DRUM_LFS_PRC_CATG_CD = "DRUM_LFS_PRC_CATG_CD";

    /**
     * Key Of VAR_CHAR_CONST: DRUM_PPS_PRC_CATG_CD
     */
    public static final String DRUM_PPS_PRC_CATG_CD = "DRUM_PPS_PRC_CATG_CD";
    // Add End 2017/07/26 QC#17129

    // Add Start 2019/02/22 QC#30413
    /**
     * Key Of VAR_CHAR_CONST: EXCL_PRT_DISC_SVC_BILL_TP
     */
    public static final String EXCL_PRT_DISC_SVC_BILL_TP = "EXCL_PRT_DISC_SVC_BILL_TP";
    // Add End 2019/02/22 QC#30413

    /**
     * Day of the week is Holiday
     */
    public static final int DAT_OF_THE_WEEK_IS_HOLIDAY = 0;

    /** Substring Position 4 */
    public static final int SUB_STR_POS_4 = 4;

    /** Substring Position 6 */
    public static final int SUB_STR_POS_6 = 6;

    /** Substring Position 8 */
    public static final int SUB_STR_POS_8 = 8;

    // START 2017/05/19 K.Kitachi [QC#18213, ADD]
    /** Substring Position 10 */
    public static final int SUB_STR_POS_10 = 10;
    // END 2017/05/19 K.Kitachi [QC#18213, ADD]

    // mod start 2015/12/16 CSA Defect#978
    /** Divide Time 15 Minute*/
    public static final BigDecimal DIVIDE_TIME_15MIN = new BigDecimal(15);

    /** Divide Time 60 Minute */
    public static final BigDecimal DIVIDE_TIME_60MIN = new BigDecimal(60);
    // mod end 2015/12/16 CSA Defect#978

    /** Rate 100 */
    public static final BigDecimal RATE_100 = new BigDecimal(100);

    // del start 2015/12/16 CSA Defect#978
//    /** Service Travel Charge Type is Flat */
//    public static final String SVC_TRVL_CHRG_TP_IS_FLAT = "Flat";
//
//    /** Service Travel Charge Type is Hourly Charge */
//    public static final String SVC_TRVL_CHRG_TP_IS_HOURLY_CHARGE = "Hourly Charge";
    // del end 2015/12/16 CSA Defect#978

    /** Decimal Format : 000 */
    public static final String DECIMAL_FORMAT = "000";

    /** Service Coverage Feature Code Is Labor Charge : 00 */
    public static final String SVC_COV_FEAT_CD_IS_LBOR_CHRG = "00";

    /** Service Coverage Feature Code Is Parts Charge : 02 */
    public static final String SVC_COV_FEAT_CD_IS_PRT_CHRG = "02";

    /** Service Coverage Feature Code Is Drum Charge : 03 */
    public static final String SVC_COV_FEAT_CD_IS_DRUM_CHRG = "03";

    /** Service Coverage Feature Code Is Expense Charge : 04 */
    public static final String SVC_COV_FEAT_CD_IS_EXP_CHRG = "04";

    /** Service Coverage Feature Code Is Bill Code : 05 */
    public static final String SVC_COV_FEAT_CD_IS_BILL_CD = "05";

    /**  It is a computational method that shows multiplication. */
    public static final String MULTIPLICATION = "*";

    /** It is a computational method that shows division. */
    public static final String DIVISION = "/";

    /** All Business Line Type Code. */
    public static final String ALL_BIZ_LINE_TP_CD = "*";

    // START 2017/10/02 K.Kim [QC#21168,ADD]
    /** All Billing Type Code. */
    public static final String ALL_BILL_TP_CD = "*";
    // END 2017/10/02 K.Kim [QC#21168,ADD]
    // START 2018/01/18 U.Kim [QC#22668, ADD]
    /** Key of Num Const : AHS_CALC_MIN*/
    public static final String AHS_CALC_MIN = "AHS_CALC_MIN";

    /** Time format*/
    public static final String TIME_FORMAT = "HHmmss";
    // END 2018/01/18 U.Kim [QC#22668, ADD]
    // Add Start 2018/07/26 QC#27418
    /** Key of varchar Const : AHS_POST_RELN_EXCL_LOB*/
    public static final String AHS_POST_RELN_EXCL_LOB = "AHS_POST_RELN_EXCL_LOB";

    /** Format System Time stamp **/
    public static final String FORMAT_SYS_TS = "yyyyMMddHHmmssSSS";
    // Add End 2018/07/26 QC#27418

    // START 2018/12/04 K.Kitachi [QC#28635, ADD]
    /** Check Level : Item */
    public static final String CHK_LVL_ITEM = "item";

    /** Check Level : Serial */
    public static final String CHK_LVL_SER = "serial";

    /** Check Level : Contract */
    public static final String CHK_LVL_CONTR = "contract";

    /** Check Level : Customer */
    public static final String CHK_LVL_CUST = "customer";

    /** Check Level : Model */
    public static final String CHK_LVL_MDL = "model";

    /** Check Level : Model Group */
    public static final String CHK_LVL_MDL_GRP = "modelGroup";

    /** Check Level : Service Program */
    public static final String CHK_LVL_SVC_PGM = "serviceProgram";
    // END 2018/12/04 K.Kitachi [QC#28635, ADD]
}
