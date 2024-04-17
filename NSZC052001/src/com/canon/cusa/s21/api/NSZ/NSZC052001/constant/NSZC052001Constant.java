/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC052001.constant;

/**
 * <pre>
 * Sub Contract Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/18/2015   Hitachi         A.Kohinata      Create          N/A
 * 03/01/2017   Hitachi         T.Mizuki        Update          QC#17575
 * 04/19/2017   Hitachi         K.Kitachi       Update          QC#18097
 *</pre>
 */
public class NSZC052001Constant {

    /** Input parameter "Global Company Code" is a mandatory field. */
    public static final String NSZM0001E = "NSZM0001E";

    /** Input parameter "Sales Date" is a mandatory field. */
    public static final String NSZM0002E = "NSZM0002E";

    // START 2017/04/19 K.Kitachi [QC#18097, ADD]
    /** Service machine master is not found. */
    public static final String NSZM0006E = "NSZM0006E";
    // END 2017/04/19 K.Kitachi [QC#18097, ADD]

    /** Input parameter "Serial Number" is a mandatory field. */
    public static final String NSZM0012E = "NSZM0012E";

    /** Input parameter "DS Contract Number" is a mandatory field. */
    public static final String NSZM0271E = "NSZM0271E";

    /** Failed to register to the @ table. */
    public static final String NSZM0398E = "NSZM0398E";

    /** Contract does not exist. */
    public static final String NSZM0434E = "NSZM0434E";

    /** Input parameter "@" is a mandatory field. */
    public static final String NSZM0609E = "NSZM0609E";

    /** The chronological sequence between the [@] is wrong. */
    public static final String NSZM0620E = "NSZM0620E";

    /** @ cannot be specified. */
    public static final String NSZM0630E = "NSZM0630E";

    /** In this status, Sub Contract cannot be processed. */
    public static final String NSZM0631E = "NSZM0631E";

    /** In this status, termination of Sub Contract cannot be processed. */
    public static final String NSZM0632E = "NSZM0632E";

    /** @ does not exist in @. */
    public static final String NSZM0633E = "NSZM0633E";

    /** @ is not @.*/
    public static final String NSZM0634E = "NSZM0634E";

    /** @ is not in contract detail effective period. */
    public static final String NSZM0635E = "NSZM0635E";

    /** The effective period is overwrapped. */
    public static final String NSZM0636E = "NSZM0636E";

    /** Failed to delete the @ table. */
    public static final String NSZM0637E = "NSZM0637E";

    /** Update DS_CONTR_DTL column "SUB_CONTR_CRAT_PROC_CD" */
    public static final String SUB_CONTR_CRAT_PROC_CD_UPDATED = "1";

    // mod start 2017/03/01 CSA QC#17575
    /** MAX_THRU_DT */
    public static final String MAX_THRU_DT = "29991231";
    // mod end 2017/03/01 CSA QC#17575
}
