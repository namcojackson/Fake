/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC060001.constant;

import com.canon.cusa.s21.framework.api.S21ApiCommonBase;

/**
 *<pre>
 * PI Activity Status API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/04/2015   CSAI            K.Lee           Create          Initial
 * 07/19/2016   CSAI            Y.Imazu         Update          QC#7981
 *</pre>
 */
public class NLZC060001Constant extends S21ApiCommonBase {

    /**
     * Return Status Code 0 : Normal
     */
    public static final String RETURN_CODE_NORMAL = "0";

    /**
     * Return Status Code 9 : Error
     */
    public static final String RETURN_CODE_ERROR = "9";

    /**
     * Global Company Code is mandatory.
     */
    public static final String NLGM0012E = "NLGM0012E";

    /**
     * The value for [@] must be bigger than [@].
     */
    public static final String NLZM2277E = "NLZM2277E";

    /**
     * Please set parameter either one of SO#, RWS#, or Inventory
     * Location Code.
     */
    public static final String NLZM2474E = "NLZM2474E";
}
