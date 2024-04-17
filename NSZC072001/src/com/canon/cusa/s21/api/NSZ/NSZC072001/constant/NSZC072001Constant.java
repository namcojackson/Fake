/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC072001.constant;

/**
 * <pre>
 * Machine Validation API Constant.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/01/2015   Hitachi         Y.Tsuchimoto    Create          NA#Machine Validation API
 * 11/08/2016   Hitachi         T.Mizuki        Update          QC#15848
 * 11/15/2016   Hitachi         A.Kohinata      Update          QC#15952
 * </pre>
 */
public final class NSZC072001Constant {

    /**
     * Global Company Code
     */
    public static final String GLBL_CMPY_CD = "ADB";

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "Sales Date" is a mandatory field.
     */
    public static final String NSZM0002E = "NSZM0002E";

    /**
     * Input parameter "Serial#" is a mandatory field.
     */
    public static final String NSZM0653E = "NSZM0653E";

    /**
     * Parameter name : Global Company Code
     */
    public static final String PARAM_GLBL_CMPY_CD = "Global Company Code";

    /**
     * Parameter name : Sales Date
     */
    public static final String PARAM_SLS_DT = "Sales Date";

    /**
     * Parameter name : Serial#
     */
    public static final String PARAM_SER_NUM = "Serial#";

    /**
     * Column name : MACH_VLD_ADDR_RTRN_FLG
     */
    public static final String COLUMN_MACH_VLD_ADDR_RTRN_FLG = "MACH_VLD_ADDR_RTRN_FLG";

    /**
     * Column name : SER_NUM
     */
    public static final String COLUMN_SER_NUM = "SER_NUM";

    /**
     * Column name : LOC_NM
     */
    public static final String COLUMN_LOC_NM = "LOC_NM";

    /**
     * Column name : MACH_VLD_STS_TXT
     */
    public static final String COLUMN_MACH_VLD_STS_TXT = "MACH_VLD_STS_TXT";

    /**
     * Column name : ADDL_LOC_NM
     */
    public static final String COLUMN_ADDL_LOC_NM = "ADDL_LOC_NM";

    /**
     * Column name : FIRST_LINE_ADDR
     */
    public static final String COLUMN_FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /**
     * Column name : SCD_LINE_ADDR
     */
    public static final String COLUMN_SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /**
     * Column name : THIRD_LINE_ADDR
     */
    public static final String COLUMN_THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /**
     * Column name : FRTH_LINE_ADDR
     */
    public static final String COLUMN_FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /**
     * Column name : CTY_ADDR
     */
    public static final String COLUMN_CTY_ADDR = "CTY_ADDR";

    /**
     * Column name : ST_CD
     */
    public static final String COLUMN_ST_CD = "ST_CD";

    /**
     * Column name : POST_CD
     */
    public static final String COLUMN_POST_CD = "POST_CD";

    /**
     * Column name : CTRY_CD
     */
    public static final String COLUMN_CTRY_CD = "CTRY_CD";

    /**
     * Column name : T_MDL_NM
     */
    public static final String COLUMN_T_MDL_NM = "T_MDL_NM";

    /**
     * MACH_VLD_STS_TXT Constant value
     */
    public static final String MACH_VLD_STS_TXT = "inActiveStatusText";
    
    /**
     * machVldList MAX
     */
    public static final int MACH_VLD_LIST_MAX = 2000;

    // mod start 2016/11/08 CSA QC#15848
    /**
     * NSZC0720_MACH_LIMIT
     */
    public static final String NSZC0720_MACH_LIMIT = "NSZC0720_MACH_LIMIT";
    // mod end 2016/11/08 CSA QC#15848

    // add start 2016/11/15 CSA QC#15952
    /**
     * NSZC0720_NO_RECORDS_MESSAGE
     */
    public static final String NSZC0720_NO_RECORDS_MESSAGE = "NSZC0720_NO_RECORDS_MESSAGE";

    /**
     * NSZC0720_MAX_RECORDS_MESSAGE
     */
    public static final String NSZC0720_MAX_RECORDS_MESSAGE = "NSZC0720_MAX_RECORDS_MESSAGE";
    // add end 2016/11/15 CSA QC#15952
}
