/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC101001.constant;

/**
 * <pre>
 * Complete Contract Approval API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/25/2016   Hitachi         A.Kohinata      Create          QC#1088
 *</pre>
 */
public class NSZC101001Constant {

    /** Process Type : Approve */
    public static final String PROC_TP_APPROVE = "1";

    /** Process Type : Reject */
    public static final String PROC_TP_REJECT = "2";

    /** VAR_CHAR_CONST:SVC_MEMO_RSN_FOR_CPLT_CONTR */
    public static final String SVC_MEMO_RSN_FOR_CPLT_CONTR = "SVC_MEMO_RSN_FOR_CPLT_CONTR";

    /** Max memo size */
    public static final int SVC_MEMO_SIZE = 4000;

    /** Input parameter "Global Company Code" is a mandatory field. */
    public static final String NSZM0001E = "NSZM0001E";

    /** Failed to insert a Service Memo record. */
    public static final String NSZM0081E = "NSZM0081E";

    /** Input parameter "DS Contract Number" is a mandatory field. */
    public static final String NSZM0271E = "NSZM0271E";

    /** Input parameter "User ID" is a mandatory field. */
    public static final String NSZM0293E = "NSZM0293E";

    /** The corresponding data does not exist in "DS_CONTR". */
    public static final String NSZM0639E = "NSZM0639E";

    /** Failed to update DS_CONTR. */
    public static final String NSZM0873E = "NSZM0873E";

    /** Failed to update DS_CONTR_BLLG_MTR. */
    public static final String NSZM0874E = "NSZM0874E";

    /** Failed to update DS_CONTR_DTL. */
    public static final String NSZM0875E = "NSZM0875E";

    /** Failed to update DS_CONTR_PRC_EFF. */
    public static final String NSZM0876E = "NSZM0876E";

    /** Failed to delete the DS_CONTR_VLD_RSLT_WRK. */
    public static final String NSZM0967E = "NSZM0967E";
}
