/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC055001.constant;

/**
 * <pre>
 * Preview Billing Action API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/17/2015   Hitachi         Y.Takeno        Create          N/A
 * 2018/12/20   Hitachi         K.Kitachi       Update          QC#29647
 * 2019/11/28   Hitachi         K.Kishimoto     Update          QC#53567
 * </pre>
 */
public final class NSZC0550001Constant {

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "Process Mode" is a mandatory field.
     */
    public static final String NSZM0003E = "NSZM0003E";

    /**
     * Input parameter "User ID" is a mandatory field.
     */
    public static final String NSZM0293E = "NSZM0293E";

    /**
     * The input is not a defined "Process Mode".
     */
    public static final String NSZM0004E = "NSZM0004E";

    /**
     * Input Parameter "Contract Number" or "DS Contract PK" is a mandatory field.
     */
    public static final String NSZM0785E = "NSZM0785E";

    /**
     * SVC_CONTR_BLLG is not found.
     */
    public static final String NSZM0786E = "NSZM0786E";

    /**
     * Failed to update a SVC_CONTR_BLLG record.
     */
    public static final String NSZM0787E = "NSZM0787E";

    /**
     * Failed to create a DS_MSG record.
     */
    public static final String NSZM0788E = "NSZM0788E";

    /**
     * Failed to update a DS_CONTR record.
     */
    public static final String NSZM0789E = "NSZM0789E";

    // START 2018/12/20 K.Kitachi [QC#29647, ADD]
    /**
     * Failed to update DS_CONTR_BLLG_MTR.
     */
    public static final String NSZM0874E = "NSZM0874E";

    /**
     * Failed to update DS_CONTR_DTL.
     */
    public static final String NSZM0875E = "NSZM0875E";

    /**
     * Failed to update DS_CONTR_PRC_EFF.
     */
    public static final String NSZM0876E = "NSZM0876E";
    // END 2018/12/20 K.Kitachi [QC#29647, ADD]

    /**
     * Process Type ('0' : Approve)
     */
    public static final String XX_PROC_TP_CD_APPROVE = "0";

    /**
     * Process Type ('1' : Reassign)
     */
    public static final String XX_PROC_TP_CD_REASSIGN = "1";

    // START 2019/11/28 [QC#53567, ADD]
    /** Workflow process name */
    public static final String WF_PROCESS_NM = "NSWP0002";

    /** Workflow process name to compare to meter */
    public static final String WF_PROCESS_NM_COMP = "NSWP0005";
    // END   2019/11/28 [QC#53567, ADD]
}
