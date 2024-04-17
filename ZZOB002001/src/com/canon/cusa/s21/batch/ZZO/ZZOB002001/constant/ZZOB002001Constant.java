/*
 * <pre>Copyright (c) 2019 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.ZZO.ZZOB002001.constant;

/**
 * <pre>
 * Update Table Statistics Batch Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/11/2019   Fujitsu         C.Ogaki         Create          QC#30652
 * 07/07/2020   CITS            K.Ogino         Update          QC#57302
 *</pre>
 */
public class ZZOB002001Constant {

    /** message id : ZZZM9025E [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** Global Company Code */
    public static final String MSG_GLBL_CMPY_CD = "Global Company Code";

    /** User Variable 1 : DB Schema */
    public static final String USR_VAR1 = "DB Schema";

    /** User Variable 2 : TARGET */
    public static final String USR_VAR2 = "TARGET";

    /** User Variable 3 : MODE */
    public static final String USR_VAR3 = "MODE";

    /** TARGET : ALL */
    public static final String TARGET_ALL = "ALL";

    /** TARGET : CONFIG */
    public static final String TARGET_CONFIG = "CONFIG";

    /** EXEC_MODE : DAYTIME */
    public static final String MODE_DAYTIME = "DAYTIME";

    /** EXEC_MODE : NIGHTLY */
    public static final String MODE_NIGHTLY = "NIGHTLY";

    // QC#57302 Add
    /** TARGET : FULL */
    public static final String TARGET_FULL = "FULL";

}
