/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC093001.constant;

/**
 * <pre>
 * ONA Meter Reads Contact API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/04   Hitachi         A.Kohinata      Create          N/A
 * 2016/07/28   Hitachi         T.Tomita        Update          QC#12490
 * 2016/08/09   Hitachi         A.Kohinata      Update          QC#12591
 * </pre>
 */
public final class NSZC093001Constant {

    /** Mode: Registration */
    public static final String MODE_REGISTRATION = "01";

    /** Mode: Create */
    public static final String MODE_CREATE = "02";

    /** Mode: Update */
    public static final String MODE_UPDATE = "03";

    /** Mode: Delete */
    public static final String MODE_DELETE = "04";

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = "ADB";

    /** Program ID */
    public static final String PROGRAM_ID = "NSZC093001";

    /** Input parameter "Process Mode" is a mandatory field. */
    public static final String NSZM0003E = "NSZM0003E";

    /** Process Mode is not valid. */
    public static final String NSZM0039E = "NSZM0039E";

    /**
     * Input parameter "Service Machine Master PK" is a mandatory
     * field.
     */
    public static final String NSZM0074E = "NSZM0074E";

    /**
     * Input parameter "Contact Person Primary Key" is a mandatory
     * field.
     */
    public static final String NSZM0927E = "NSZM0927E";

    /**
     * Input parameter "Contact Person First Name" is a mandatory
     * field.
     */
    public static final String NSZM0928E = "NSZM0928E";

    /**
     * Input parameter "Contact Person Last Name" is a mandatory
     * field.
     */
    public static final String NSZM0929E = "NSZM0929E";

    /**
     * Input parameter "Direct Sales Contact Point List" is a
     * mandatory field.
     */
    public static final String NSZM0930E = "NSZM0930E";

    /**
     * Input parameter "Direct Sales Contact Point Primary Key" is a
     * mandatory field.
     */
    public static final String NSZM0931E = "NSZM0931E";

    /**
     * Input parameter "Direct Sales Contact Point Type Code" is a
     * mandatory field.
     */
    public static final String NSZM0932E = "NSZM0932E";

    /**
     * Input parameter "Direct Sales Contact Point Value Text" is a
     * mandatory field.
     */
    public static final String NSZM0933E = "NSZM0933E";

    /**
     * Input parameter "Service Machine Contact Point List" is a
     * mandatory field.
     */
    public static final String NSZM0934E = "NSZM0934E";

    /**
     * Input parameter "Service Machine Contact Person PK" is a
     * mandatory field.
     */
    public static final String NSZM0935E = "NSZM0935E";

    /**
     * Input parameter "Service Contact Type Code" is a mandatory
     * field.
     */
    public static final String NSZM0936E = "NSZM0936E";

    /** Input parameter "Last Update User ID" is a mandatory field. */
    public static final String NSZM0937E = "NSZM0937E";

    /** Input parameter "Last Update Date" is a mandatory field. */
    public static final String NSZM0938E = "NSZM0938E";

    /**
     * "Direct Sales Contact Point Type Code" does not exist in the
     * master.
     */
    public static final String NSZM0939E = "NSZM0939E";

    /** "Service Contact Type Code" does not exist in the master. */
    public static final String NSZM0940E = "NSZM0940E";

    /** Failed to insert the SVC_MACH_CTAC_PSN. */
    public static final String NSZM0941E = "NSZM0941E";

    /** Failed to update SVC_MACH_CTAC_PSN. */
    public static final String NSZM0942E = "NSZM0942E";

    /** [@] for update target is not found. */
    public static final String NSZM0962E = "NSZM0962E";

    // START 2016/07/28 T.Tomita [QC#12490, ADD]
    /** Multiple contact points exist in machine. */
    public static final String NSZM1037E = "NSZM1037E";
    // END 2016/07/28 T.Tomita [QC#12490, ADD]

    // add start 2016/08/09 CSA Defect#12591
    /** @ does not exist. */
    public static final String NSZM0713E = "NSZM0713E";
    // add end 2016/08/09 CSA Defect#12591
}
