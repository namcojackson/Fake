/*
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC064001.constant;

/**
 * <pre>
 * NSZC064001Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/06/2015   Hitachi         J.Kim           Create
 * 09/28/2016   Hitachi         A.Kohinata      Update          QC#12898
 * </pre>
 */
public final class NSZC064001Constant {

    /*****************************************************************
     * Message ID
     ****************************************************************/

    /** Input parameter "Global Company Code" is a mandatory field. */
    public static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "CPO.CPO_ORD_TP_CD" is not Supply Order.
     */
    public static final String NSZM0608E = "NSZM0608E";

    /**
     * "YYYYMMDD LENGTH
     */
    public static final int LENGTH_8 = 8;

    // START 2016/09/28 A.Kohinata [QC#12898, ADD]
    /**
     * Input parameter "DS Contract Number" is a mandatory field.
     */
    public static final String NSZM0271E = "NSZM0271E";

    /**
     * Input parameter "CPO_DTL.SVC_MACH_MSTR_PK" is a mandatory field.
     */
    public static final String NSZM0615E = "NSZM0615E";
    // END 2016/09/28 A.Kohinata [QC#12898, ADD]
}
