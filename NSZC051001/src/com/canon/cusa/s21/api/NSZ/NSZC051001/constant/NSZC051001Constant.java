/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC051001.constant;

/**
 * <pre>
 * Meter Bulk Upload API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         K.Kojima        Create          N/A
 * 2016/03/09   Hitachi         T.Tomita        Update          CSA Defect#5203
 * 2016/03/10   Hitachi         T.Tomita        Update          CSA Defect#5204, 5205
 * 2016/12/22   Hitachi         K.Kojima        Update          QC#16600
 * 2017/05/22   Hitachi         K.Kitachi       Update          QC#18342
 * </pre>
 */
public class NSZC051001Constant {

    /** Return Status Code : Normal */
    public static final String RETURN_CODE_NORMAL = "0";

    /** Return Status Code : Error */
    public static final String RETURN_CODE_ERROR = "9";

    /** Message Code : NSZM0396E */
    public static final String NSZM0396E = "NSZM0396E";

    /** Message Code : NSZM0401E */
    public static final String NSZM0401E = "NSZM0401E";

    /** Message Code : NSZM0872W */
    public static final String NSZM0872W = "NSZM0872W";

    // START 2016/12/22 K.Kojima [QC#16600,ADD]
    /** Message Code : NSZM1096E */
    public static final String NSZM1096E = "NSZM1096E";
    // END 2016/12/22 K.Kojima [QC#16600,ADD]

    /** Column name : SVC_MACH_MSTR_PK */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** Column name : MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** Column name : SER_NUM */
    public static final String SER_NUM = "SER_NUM";

    // START 2017/05/22 K.Kitachi [QC#18342, DEL]
//    /** Column name : DS_MTR_INTFC_PK */
//    public static final String DS_MTR_INTFC_PK = "DS_MTR_INTFC_PK";
    // END 2017/05/22 K.Kitachi [QC#18342, DEL]

    /** Message ID Length */
    public static final int MSG_ID_LEN = 10;
}
