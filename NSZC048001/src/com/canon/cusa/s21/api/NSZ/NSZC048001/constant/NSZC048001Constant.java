/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC048001.constant;

/**
 * <pre>
 * Service Model API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/18/2015   Hitachi         T.Tsuchida      Create
 * 03/24/2016   Hitachi         M.Gotou         Update          CSA QC#4887
 * 05/25/2017   Hitachi         K.Kitachi       Update          QC#18642
 * 06/14/2017   Hitachi         T.Mizuki        Update          QC#19027
 * 2018/01/05   Hitachi         U.Kim           Update          QC#23352
 *</pre>
 */
public class NSZC048001Constant {

    /** @ is not set.[@] */
    public static final String NSZM0401E = "NSZM0401E";

    /** @ is not found.[@] */
    public static final String NSZM0396E = "NSZM0396E";

    // START 2016/03/24 M.Gotou [QC#4887, ADD]
    /**
     * The model cannot be found for the configuration. Please review
     * model setup.
     */
    public static final String NSZM0925E = "NSZM0925E";
    // END 2016/03/24 M.Gotou [QC#4887, ADD]

    // START 2016/03/24 M.Gotou [QC#4887, DEL]
//    /** [@] is not found. */
//    public static final String ZZZM9006E = "ZZZM9006E";
    // END 2016/03/24 M.Gotou [QC#4887, DEL]

    /** GLBL_CMPY_CD */
    public static final String MSG_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** SLS_DT */
    public static final String MSG_SLS_DT = "SLS_DT";

    /** PRNT_MDSE_CD */
    public static final String MSG_PRNT_MDSE_CD = "PRNT_MDSE_CD";

    // START 2018/01/05 U.Kim [QC#23352,ADD]
    /** CHILD_MDSE_CD */
    public static final String MSG_CHILD_MDSE_CD = "CHILD_MDSE_CD";
    // END 2018/01/05 U.Kim [QC#23352,ADD]

    /** Input Parameters */
    public static final String MSG_INFO_IN_PRM = "Input Parameters";

    /** MDSE Code: */
    public static final String MSG_INFO_MDSE_CD = "MDSE Code:";

    /** MDSE Master */
    public static final String MSG_INFO_MDSE_MSTR = "MDSE Master";

    /** Parent MDSE Code: */
    public static final String MSG_INFO_PRT_MDSE_CD = "Parent MDSE Code:";

    /** DS Model Config Master */
    public static final String MSG_INFO_DS_MDL_CONFIG_MSTR = "DS Model Config Master";

    // START 2016/03/24 M.Gotou [QC#4887, DEL]
//    /** Model ID */
//    public static final String MSG_INFO_MDL_ID = "Model ID";
    // END 2016/03/24 M.Gotou [QC#4887, DEL]

    /** % */
    public static final String STR_PERCENT = "%";

    // START 2017/06/14 T.Mizuki [QC#19027, ADD]
    // START 2017/05/25 K.Kitachi [QC#18642, DEL]
    /** start position : 0 */
    public static final int START_POS_0 = 0;

    /** end position : 8 */
    public static final int END_POS_8 = 8;
    // END 2017/05/25 K.Kitachi [QC#18642, DEL]
    // END 2017/06/14 T.Mizuki [QC#19027, ADD]
    
    /** % */
    public static final String MAX_EFF_THRU_DT = "99991231";
}
