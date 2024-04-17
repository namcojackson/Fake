/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants;

/**
 * <pre>
 * CPO Config Update API.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/01   Fujitsu         C.Yokoi         Create
 * 2016/02/25   Fujitsu         S.Ohki          Update          S21_NA#3328
 * </pre>
 */
public class NWZC150001CpoConfConstant {

    /** Process ID (OM) */
    public static final String PROCESS_ID = "OM";

    /** Document Type (OM) */
    public static final String DOCUMENT_TYPE = "OM";

    /** Document Type (RT) */
    public static final String DOCUMENT_TYPE_RTN = "RT"; // 2016/02/25 S21_NA#3328 Add

    /** Event ID (Order Create) */
    public static final String EVENT_ID_ORD_CRAT = "Order Create";

    /** Event ID (Order Change) */
    public static final String EVENT_ID_ORD_MOD = "Order Change";

    /*****************************************************************
     * Message ID
     ****************************************************************/
    /** "Global Company Code" is required. */
    public static final String NWZM0188E = "NWZM0188E";

    /** Mode is required. */
    public static final String NWZM0012E = "NWZM0012E";

    /** The parameter's "Order Number" is not set. */
    public static final String NWZM0912E = "NWZM0912E";

    /** DS CPO Position Number is required. */
    public static final String NWZM1374E = "NWZM1374E";

    /** Configuration Category Code is required. */
    public static final String NWZM1375E = "NWZM1375E";

    /** Configuration Type Code is required. */
    public static final String NWZM1376E = "NWZM1376E";

    /** Bill To Customer Account Code is required. */
    public static final String NWZM1377E = "NWZM1377E";

    /** Bill To Customer Location Code is required. */
    public static final String NWZM1378E = "NWZM1378E";

    /** Ship To Customer Account Code is required. */
    public static final String NWZM1379E = "NWZM1379E";

    /** Ship To Customer Location Code is required. */
    public static final String NWZM1380E = "NWZM1380E";

    /** "CPO Configuration PK" is required. */
    public static final String NWZM1337E = "NWZM1337E";

    /** Specified value for Mode is invalid. */
    public static final String NWZM0013E = "NWZM0013E";

    /** DS_CPO_CONFIG for update target is not found. */
    public static final String NWZM1381E = "NWZM1381E";

    /** Failed to insert the DS_CPO_CONFIG. */
    public static final String NWZM1382E = "NWZM1382E";

    /** Failed to update the DS_CPO_CONFIG. */
    public static final String NWZM1383E = "NWZM1383E";

    /** Failed to insert the DS_CPO_CONFIG_REC. */
    public static final String NWZM1758E = "NWZM1758E";
}
