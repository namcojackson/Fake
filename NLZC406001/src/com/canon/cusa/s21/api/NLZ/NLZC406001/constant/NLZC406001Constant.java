/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC406001.constant;

import com.canon.cusa.s21.framework.api.S21ApiCommonBase;

/**
 *<pre>
 * Serial Validation API For WMS
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/04/2016   CSAI            K.Lee           Create          Initial
 *</pre>
 */
public class NLZC406001Constant extends S21ApiCommonBase {

    /**
     * Return Status Code 1 : Normal
     */
    public static final String RETURN_CODE_NORMAL = "1";

    /**
     * Return Status Code 0 : N/A
     */
    public static final String RETURN_CODE_NA = "0";

    /**
     * Return Status Code 9 : Error
     */
    public static final String RETURN_CODE_ERROR = "9";

    /**
     * SCE Order Type DT : DC Transfer
     */
    public static final String DC_TRANSFER = "DT";

    /**
     * SCE Order Type RT : Return
     */
    public static final String RETURN = "RT";

    /**
     * Message ID : NLGM0012E Global Company Code is mandatory.
     */
    public static final String NLGM0012E = "NLGM0012E";

    /**
     * Message ID : NLZM2091E Serial # is not set.
     */
    public static final String NLZM2091E = "NLZM2091E";

    /**
     * Message ID : NLZM2287E Item Code does not exist in Item Master.
     */
    public static final String NLZM2287E = "NLZM2287E";
//
    /**
     * Message ID : NLZM2288W Item is not controlled by install base.
     */
    public static final String NLZM2288W = "NLZM2288W";

    /**
     * Message ID : NLZM2289E Item Code is mandatory.
     */
    public static final String NLZM2289E = "NLZM2289E";

    /**
     * Message ID : NLZM2292E The Serial # is incorrect. It is set out of the range.
     */
    public static final String NLZM2292E = "NLZM2292E";

    /**
     * Message ID : NLZM2299E Entered serial is already registered in IB.
     */
    public static final String NLZM2299E = "NLZM2299E";
}