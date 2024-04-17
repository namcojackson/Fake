/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPB.NPBB003001.constant;

/**
 * <pre>
 * NPBB003001:
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/01   CITS            T.Kikuhara      Create          QC#18365(L3)
 * 2017/11/17   CITS            S.Katsuma       Update          QC#22163
 *</pre>
 */
public class NPBB003001Constant {

    /** Group ID */
    public static final String GROUP_ID = "NPBB0030";

    /** Global Company Code is mandatory. */
    public static final String NPAM1479E = "NPAM1479E";

    /** Sales date cannot be obtained. */
    public static final String NPAM1480E = "NPAM1480E";

    // START 2017/11/17 S.Katsuma [QC#22163,ADD]
    /** . */
    public static final String PR_CRAT_SYSTEM_USER = "PR_CRAT_SYSTEM_USER";
    // END 2017/11/17 S.Katsuma [QC#22163,ADD]
}
