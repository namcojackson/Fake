/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC035001.constant;

/**
 * <pre>
 * Fleet Calculation API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Hitachi         T.Kanasaka      Create          NA
 * 2016/01/18   Hitachi         T.Kanasaka      Update          QC3069
 * 2016/10/04   Hitachi         T.Kanasaka      Update          QC12274
 * 2017/09/20   Hitachi         T.Kanasaka      Update          QC#15134
 * 2019/09/04   Hitachi         T.Aoyagi        Update          QC#53124
 * </pre>
 */
public class NSZC035001Constant {

    /**
     * Process Mode 00: Normal
     */
    public static final String MODE_NORMAL = "00";

    /**
     * Process Mode 01: Rebill
     */
    public static final String MODE_REBILL = "01";

    /**
     * SVC_INV_REBILL_TP_CD
     */
    public static final String SVC_INV_REBILL_TP_CD = "SVC_INV_REBILL_TP_CD";

    // add start 2019/09/04 QC#53124
    /**
     * CONTR_LOB_EXCL_TEST_COPY
     */
    public static final String CONTR_LOB_EXCL_TEST_COPY = "CONTR_LOB_EXCL_TEST_COPY";
    // add end 2019/09/04 QC#53124

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NSZM0001E = "NSZM0001E";

    /**
     * Input Parameter xxModeCd is not correct.
     */
    public static final String NSZM0175E = "NSZM0175E";

    /**
     * Input parameter "DS Contract Number" is a mandatory field.
     */
    public static final String NSZM0271E = "NSZM0271E";

    /**
     * No Contract detail found.
     */
    public static final String NSZM0671E = "NSZM0671E";

    /**
     * Input parameter "Billing Date" is a mandatory field.
     */
    public static final String NSZM0817E = "NSZM0817E";

    /**
     * Input parameter "Parent DS Contract Billing Schedule PK" is a mandatory field.
     */
    public static final String NSZM0831E = "NSZM0831E";

    /**
     * Input Parameter "Service Credit Rebilling Pk" is mandatory field if mode is rebill.
     */
    public static final String NSZM0832E = "NSZM0832E";

    /**
     * Input Parameter "Service Credit Rebilling Detail Pk" is mandatory field if mode is rebill.
     */
    public static final String NSZM0833E = "NSZM0833E";

    /**
     * The corresponding data does not exist in Billing Schedule.
     */
    public static final String NSZM0834E = "NSZM0834E";

    /**
     * Failed to insert the FLEET_READ_ROLL_UP.
     */
    public static final String NSZM0835E = "NSZM0835E";

    /**
     * Failed to update the FLEET_READ_ROLL_UP.
     */
    public static final String NSZM0836E = "NSZM0836E";

    /**
     * Failed to insert the FLEET_READ_ROLL_UP_DTL.
     */
    public static final String NSZM0837E = "NSZM0837E";

    /**
     * Failed to update the DS_CONTR_BLLG_SCHD.
     */
    public static final String NSZM0838E = "NSZM0838E";

    /**
     * Previous Fleet Roll Up is not calculated.
     */
    public static final String NSZM0839E = "NSZM0839E";

    /**
     * Failed to get the Meter Read.
     */
    public static final String NSZM0840E = "NSZM0840E";

    /**
     * Failed to calculate Fleet Roll Up.
     */
    public static final String NSZM0841E = "NSZM0841E";

    // START 2017/09/20 T.Kanasaka [QC#15134,ADD]
    /**
     * Failed to insert the CALC_MTR_SCHD_RELN.
     */
    public static final String NSZM1301E = "NSZM1301E";
    // END 2017/09/20 T.Kanasaka [QC#15134,ADD]

    /**
     * Size of ArrayList, Hashmap : 3
     */
    public static final int SIZE_3 = 3;

    /**
     * Size of ArrayList, Hashmap : 5
     */
    public static final int SIZE_5 = 5;

    /**
     * Size of ArrayList, Hashmap : 9
     */
    public static final int SIZE_9 = 9;

    /**
     * MAP_KEY : bllgFromDt
     */
    public static final String MAP_KEY_BLLG_FROM_DT = "bllgFromDt";

    /**
     * MAP_KEY : bllgThruDt
     */
    public static final String MAP_KEY_BLLG_THRU_DT = "bllgThruDt";

    /**
     * MAP_KEY : sumPriReadMtrCnt
     */
    public static final String MAP_KEY_SUM_PRI_READ_MTR_CNT = "sumPriReadMtrCnt";

    /**
     * MAP_KEY : sumCurReadMtrCnt
     */
    public static final String MAP_KEY_SUM_CUR_READ_MTR_CNT = "sumCurReadMtrCnt";

    /**
     * MAP_KEY : mtrReadDt
     */
    public static final String MAP_KEY_MTR_READ_DT = "mtrReadDt";

    // START 2016/10/04 T.Kanasaka [QC#12274, ADD]
    /**
     * MAP_KEY : sumTestMtrCnt
     */
    public static final String MAP_KEY_SUM_TEST_MTR_CNT = "sumTestMtrCnt";
    // END 2016/10/04 T.Kanasaka [QC#12274, ADD]

    // START 2017/09/20 T.Kanasaka [QC#15134,ADD]
    /**
     * MAP_KEY : sumAftResetMtrCnt
     */
    public static final String MAP_KEY_SUM_AFT_RESET_MTR_CNT = "sumAftResetMtrCnt";
    // END 2017/09/20 T.Kanasaka [QC#15134,ADD]
}
