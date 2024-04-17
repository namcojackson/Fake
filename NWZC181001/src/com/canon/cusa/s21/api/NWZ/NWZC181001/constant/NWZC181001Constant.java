/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC181001.constant;

/**
 * <pre>
 * Auto Add Supply API Constant
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/15   Fujitsu         E.Yoshitake     Create          N/A
 * 2016/06/20   Fujitsu         H.Nagashima     Update          QC#10386
 *</pre>
 */
public class NWZC181001Constant {

    /** PROCESS MODE : ORDER ENTRY */
    public static final String PROC_MD_ODR_ENT = "1";

    /** PROCESS MODE : ORDER IMPORT */
    public static final String PROC_MD_ODR_IMP = "2";

    /** AUTO_SUPPLY */
    public static final String STR_AUTO_SUPPLY = "AUTO_SUPPLY";

    /** INITIAL_SUPPLY */
    public static final String STR_INITIAL_SUPPLY = "INITIAL_SUPPLY";

    /** "Global Company Code" is not set. */
    public static final String NWZM0473E = "NWZM0473E";

    /** "Process Mode" is not set. */
    public static final String NWZM0580E = "NWZM0580E";

    /** An input parameter "DS Order Category Code" has not been set. */
    public static final String NWZM1568E = "NWZM1568E";

    /** An input parameter "DS Order Type Code" has not been set. */
    public static final String NWZM1253E = "NWZM1253E";

    /** An input parameter "Model ID" has not been set. */
    public static final String NWZM1567E = "NWZM1567E";

    /** Invalid value is set for process mode. */
    public static final String NWZM0977E = "NWZM0977E";

    /** "DS Order Category Code" does not exist in the Master. */
    public static final String NWZM1415E = "NWZM1415E";

    /** The data does not exist in DS Order Type. */
    public static final String NWZM1286E = "NWZM1286E";

    /** The entered "Model ID" does not exist in the Master. */
    public static final String NWZM1443E = "NWZM1443E";

    /** "Sales Date" is not set. */
    public static final String NWZM0482E = "NWZM0482E";
// QC#10386 add Start
    /** "No Toner Mapping Found. */
    public static final String NWZM1948E = "NWZM1948E";
// QC#10386 add End
}
