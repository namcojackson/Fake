/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC112001.constant;

/**
 * <pre>
 * Install Confirmation from eCarrier API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/09   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSZC112001Constant {

    /** Input parameter "Global Company Code" is a mandatory field. */
    public static final String NSZM0001E = "NSZM0001E";

    /** Input parameter "Sales Date" is a mandatory field. */
    public static final String NSZM0002E = "NSZM0002E";

    /** Service machine master is not found. */
    public static final String NSZM0006E = "NSZM0006E";

    /** Input parameter "Installed Date" is a mandatory field. */
    public static final String NSZM0029E = "NSZM0029E";

    /**
     * Input parameter "Service Machine Master PK" is a mandatory
     * field.
     */
    public static final String NSZM0074E = "NSZM0074E";

    /** Failed to insert the SVC_MEMO. (System Error) */
    public static final String NSZM0475E = "NSZM0475E";

    /** Service Machine Master Track is not found. */
    public static final String NSZM0725E = "NSZM0725E";

    /** UPD_FLD_TXT : SVC_MACH_MSTR_TRK_PK */
    public static final String UPD_FLD_TXT = "SVC_MACH_MSTR_STS_CD";

    /** SVC_MEMO_TRX_NM : SVC_MACH_MSTR_TRK_PK */
    public static final String SVC_MEMO_TRX_NM = "SVC_MACH_MSTR_TRK_PK";
}
