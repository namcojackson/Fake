/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC501001.constant;

/**
 *<pre>
 * Workflow Status update API
 *
 * Date         Company         Name        Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/01/2016   Hitachi         J.Kim       Create          N/A
 * 07/31/2018   Hitachi         E.Kameishi      Update          QC#27462
 *</pre>
 */
public class NFZC501001Constant {

    /** Data does not exist. */
    public static final String NFCM0508E = "NFCM0508E";

    /** Mandatory field: Please enter the value. */
    public static final String NFCM0039E = "NFCM0039E";

    /** Entry Parameter Error. */
    public static final String NFCM0522E = "NFCM0522E";

    // START 2018/07/31 E.Kameishi [QC#27462,ADD]
    /** Workflod Id */
    public static final String AR_REFUND_WF_ID = "AR_REFUND_WF_ID";
    // END 2018/07/31 E.Kameishi [QC#27462,ADD]
}
