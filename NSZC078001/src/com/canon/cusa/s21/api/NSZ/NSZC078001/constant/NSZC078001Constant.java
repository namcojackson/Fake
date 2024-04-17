/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC078001.constant;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMG_SPLY_COLOR_TP;

/**
 * <pre>
 * NSZC078001Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/30/2015   Hitachi         T.Harada        Create
 * 03/17/2016   Hitachi         A.Kohinata      Update          QC#5647
 * 2019/12/12   Hitachi         K.Kitachi       Update          QC#55064
 * </pre>
 */
public final class NSZC078001Constant {

    /** This Program ID */
    public static final String PROGRAM_ID = "NSZC0780";

    /*****************************************************************
     * Message ID
     ****************************************************************/
    /** An input parameter, [@], has not been set. */
    public static final String NZZM0012E = "NZZM0012E";

    /** Failed to register to the @ table. */
    public static final String NSZM0398E = "NSZM0398E";

    /** Failed to update the @ table.[@] */
    public static final String NSZM0399E = "NSZM0399E";

    /** No search results found. */
    public static final String NSZM0505E = "NSZM0505E";

    /*****************************************************************
     * Other Constant
     ****************************************************************/
    /** */
    public static final String[] IMG_SPLY_COLOR_TP_LIST = {IMG_SPLY_COLOR_TP.BLACK, IMG_SPLY_COLOR_TP.YELLOW, IMG_SPLY_COLOR_TP.MAGENTA, IMG_SPLY_COLOR_TP.CYAN };

    /** MDL_MTR_TP_CD : BLACK AND WHITE */
    public static final String MDL_MTR_TP_BW = "1";

    /** MDL_MTR_TP_CD : COLOR */
    public static final String MDL_MTR_TP_CL = "2";

    /** CALC_TP : ALLW */
    public static final String CALC_TP_ALLW = "ALLW";

    /** CALC_TP : ADCV */
    public static final String CALC_TP_PRRT = "PRRT";

    /** for Calculate ADCV_CNT */
    public static final int GET_BLLG_SCHD_NUM = 4;

    /** AVG_MLY_COPY_CNT_COF */
    public static final BigDecimal AVG_MLY_COPY_CNT_COF = new BigDecimal(30.42);

    /** Date Format */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    /** One Hundred 100 */
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    /** coefficient : BLACK AND WHITE */
    public static final BigDecimal COEFFICIENT_BW = new BigDecimal(1);

    /** coefficient : COLOR */
    public static final BigDecimal COEFFICIENT_CL = new BigDecimal(3);

    /** abuseVarPct scale */
    public static final int PCT_SCALE = 5;

    // START 2019/12/12 K.Kitachi [QC#55064, ADD]
    /** abuseVarPct max value */
    public static final BigDecimal ABUSE_VAR_PCT_MAX_VAL = new BigDecimal("9999.99999");
    // END 2019/12/12 K.Kitachi [QC#55064, ADD]
}
