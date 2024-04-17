/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC400001.constant;

import com.canon.cusa.s21.framework.api.S21ApiCommonBase;

/**
 *<pre>
 * Serial Validation API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/04/2015   CSAI            K.Lee           Create          Initial
 * 11/01/2016   CSAI            K.Lee           Update          QC#15667
 *</pre>
 */
public class NLZC400001Constant extends S21ApiCommonBase {

    /**
     * Return Status Code 0 : Validated
     */
    public static final String RETURN_CODE_VALIDATED = "0";

    /**
     * Return Status Code 1 : IB Allocation Error
     */
    public static final String RETURN_CODE_WARNING = "1";

    /**
     * Return Status Code 9 : Error
     */
    public static final String RETURN_CODE_ERROR = "9";

    /**
     * Process Mode 01 : Order Serial Mode
     */
    public static final String MODE_ORDER_SERIAL = "1";

    /**
     * Process Mode 02 : Pickup Serial Mode
     */
    public static final String MODE_PICKUP_SERIAL = "2";

    /**
     * Mode 03 : Pickup Cancel Mode
     */
    public static final String MODE_PICKUP_CANCEL = "3";

    /**
     * Message ID : NLGM0012E Global Company Code is mandatory.
     */
    public static final String NLGM0012E = "NLGM0012E";

    /**
     * Message ID : NLZM2320W The entered Serial Number is not registered on IB.[item:@, Serial#:@]
     */
    public static final String NLZM2320W = "NLZM2320W";

    /**
     * Message ID : NLZM2321W The entered Serial Number is already
     * allocated on IB.[item:@, Serial#:@]
     */
    public static final String NLZM2321W = "NLZM2321W";

    /**
     * Message ID : NLZM2322W The entered Serial Number is failed to
     * allocate IB.[item:@, Serial#:@]
     */
    public static final String NLZM2322W = "NLZM2322W";

    /**
     * Message ID : NLZM2323E A value is not set in the required parameter field. Field Name:[@] List Index:[@]
     */
    public static final String NLZM2323E = "NLZM2323E";

    /**
     * Message ID : NLAM1295E Failed to register [@].
     */
    public static final String NLAM1295E = "NLAM1295E";

    /**
     * Message ID : NLBM1080E 
     */
    public static final String NLBM1080E = "NLBM1080E";
}