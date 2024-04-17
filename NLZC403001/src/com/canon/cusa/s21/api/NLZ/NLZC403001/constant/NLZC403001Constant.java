/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC403001.constant;

import com.canon.cusa.s21.framework.api.S21ApiCommonBase;

/**
 *<pre>
 * Serial Validation API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/04/2015   CSAI            K.Lee           Create          Initial
 * 02/01/2017   CITS            Y.Fujii         Update          QC#17214
 * 02/13/2017   CITS            T.Wada          Update          QC#17339
 * 03/23/2017   CITS            R.Shimamoto     Update          QC#17919
 *</pre>
 */
public class NLZC403001Constant extends S21ApiCommonBase {

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
     * Process Mode 01 : Out Bound
     */
    public static final String MODE_OUTBOUND = "01";

    /**
     * Mode 02 : In Bound
     */
    public static final String MODE_INBOUND = "02";

    /**
     * Mode 03 : In Bound(Return)
     */
    public static final String MODE_INBOUND_RETURN = "03";

    /**
     * Mode 04 : In Bound(Buy Back)
     */
    public static final String MODE_INBOUND_BUYBACK = "04";

    /**
     * Mode 05 : Adjustment(Decrease)
     */
    public static final String MODE_ADJUSTMENT_DECREASE = "05";

    /**
     * Mode 06 : In Bound(SubContract)
     */
    public static final String MODE_INBOUND_SUBCONTRACT = "06";
    /**
     * Mode 07 : In Bound(DC_TRANSFER)
     */
    public static final String MODE_INBOUND_DC_TRANSFER = "07";
    /**
     * Message ID : NLGM0012E Global Company Code is mandatory.
     */
    public static final String NLGM0012E = "NLGM0012E";

    /**
     * Message ID : NLZM2087E Process mode is not set.
     */
    public static final String NLZM2087E = "NLZM2087E";

    /**
     * Message ID : NLZM2091E Serial # is not set.
     */
    public static final String NLZM2091E = "NLZM2091E";

    /**
     * Message ID : NLZM2287E Item Code does not exist in Item Master.
     */
    public static final String NLZM2287E = "NLZM2287E";

    /**
     * Message ID : NLZM2288W Item is not controlled by install base.
     */
    public static final String NLZM2288W = "NLZM2288W";

    /**
     * Message ID : NLZM2289E Item Code is mandatory.
     */
    public static final String NLZM2289E = "NLZM2289E";

    /**
     * Message ID : NLZM2290E SO# or Warehouse Code is must be entered on Out-Bound Mode.
     */
    public static final String NLZM2290E = "NLZM2290E";

    /**
     * Message ID : NLZM2291E RWS# or Warehouse Code is must be entered on In-Bound Mode.
     */
    public static final String NLZM2291E = "NLZM2291E";

    /**
     * Message ID : NLZM2292E The Serial # is incorrect. It is set out of the range.
     */
    public static final String NLZM2292E = "NLZM2292E";

    /**
     * Message ID : NLZM2293E Entered serial does not exist in IB Master.
     */
    public static final String NLZM2293E = "NLZM2293E";

    /**
     * Message ID : NLZM2294E Entered serial already have been allocated.
     */
    public static final String NLZM2294E = "NLZM2294E";

    /**
     * Message ID : NLZM2295E IB status of entered serial is not [Created] or [Removed].
     */
    public static final String NLZM2295E = "NLZM2295E";

    /**
     * Message ID : NLZM2296E Entered warehouse is not match with IB.
     */
    public static final String NLZM2296E = "NLZM2296E";

    /**
     * Message ID : NLZM2297E Entered serial is not matched with SO.
     */
    public static final String NLZM2297E = "NLZM2297E";

    /**
     * Message ID : NLZM2298E Entered serial is not matched with Receiving work sheet.
     */
    public static final String NLZM2298E = "NLZM2298E";

    /**
     * Message ID : NLZM2299E Entered serial is already registered in IB.
     */
    public static final String NLZM2299E = "NLZM2299E";

    /**
     * Message ID : NLZM2299E The SO# does not Exist.
     */
    public static final String NLZM2300E = "NLZM2300E";

    /**
     * Message ID : NLZM2301E The RWS# does not Exist.
     */
    public static final String NLZM2301E = "NLZM2301E";

    /**
     * Message ID : NLZM2479E Serial acquisition is not required.
     */
    public static final String NLZM2479E = "NLZM2479E";

    /**
     * Message ID : NLBM1359E Serial Number is different from the shipped serial.
     */
    public static final String NLBM1359E = "NLBM1359E";
}