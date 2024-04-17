/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NWZ.NWZC152001.constant;

/**
 * <pre>
 * CPO Sales Credit Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/02   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/25   Fujitsu         S.Ohki          Update          S21_NA#3328
 *</pre>
 */
public class NWZC152001Constant {

    /** Process ID (OM) */
    public static final String PROCESS_ID = "OM";

    /** Document Type (OM) */
    public static final String DOCUMENT_TYPE = "OM";

    /** Document Type (RT) */
    public static final String DOCUMENT_TYPE_RTN = "RT"; // 2016/02/25 S21_NA#3328 Add

    /** Event ID (Order Create) */
    public static final String EVENT_ID_ORD_CRAT = "Order Create(Sales Credit)";

    /** Event ID (Order Change) */
    public static final String EVENT_ID_ORD_MOD = "Order Save(Sales Credit)";

    /** Event ID (Order Cancel) */
    public static final String EVENT_ID_ORD_DEL = "Order Cancel(Sales Credit)";

    /*****************************************************************
     * Message ID
     ****************************************************************/
    /** "Global Company Code" is required. */
    public static final String NWZM0188E = "NWZM0188E";

    /** "Request Type" is required. */
    public static final String NWZM0189E = "NWZM0189E";

    /** The parameter's "Order Number" is not set. */
    public static final String NWZM0912E = "NWZM0912E";

    /** "CPO Configuration PK" is required. */
    public static final String NWZM1337E = "NWZM1337E";

    /** "Sales Rep Code" is required. */
    public static final String NWZM0194E = "NWZM0194E";

    /** "Sales Rep Role Type Code" is required. */
    public static final String NWZM1338E = "NWZM1338E";

    /** "Request Type Code" has an invalid value. */
    public static final String NWZM0209E = "NWZM0209E";

    /** "CPO Sales Credit PK" is required. */
    public static final String NWZM1339E = "NWZM1339E";

    /** Sales Representative Credit Percentage is required. */
    public static final String NWZM1344E = "NWZM1344E";

    /** Sales Credit Quote Flag is required. */
    public static final String NWZM1345E = "NWZM1345E";

    /** The entered "Sales Rep Code" does not exist in Master. */
    public static final String NWZM0054E = "NWZM0054E";

    /** Total of credit percentage must be 100%. */
    public static final String NWZM1387E = "NWZM1387E";

    /** Credit Percentage is invalid. */
    public static final String NWZM1388E = "NWZM1388E";

    /** Configuration does not exist. */
    public static final String NWZM1389E = "NWZM1389E";

    /** Failed to insert the DS_CPO_SLS_CR. */
    public static final String NWZM1390E = "NWZM1390E";

    /** Failed to update the DS_CPO_SLS_CR. */
    public static final String NWZM1391E = "NWZM1391E";

    /** Failed to delete the DS_CPO_SLS_CR. */
    public static final String NWZM1392E = "NWZM1392E";

    /** Failed to insert the DS_CPO_SLS_CR_REC. */
    public static final String NWZM1759E = "NWZM1759E";

    /*****************************************************************
     * Variable
     ****************************************************************/
    /** Mode (New) */
    public static final String MODE_NEW = "1";

    /** Mode (Modify) */
    public static final String MODE_MOD = "2";

    /** Mode (Delete) */
    public static final String MODE_DEL = "3";
}
