/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB100001.constant;

/**
 * <pre>
 * Service Physical Meter Update Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/01/26   Hitachi         H.Watanabe      Create          QC#60925
 * 2023/07/07   Hitachi         H.Watanabe      Update          QC#60925
 * 2023/10/20   Hitachi         N.Takatsu       Update          QC#60925
 *</pre>
 */
public final class NSBB100001Constant {

    /** Batch Id */
    public static final String BATCH_ID = "NSBB100001";

    /** FETCH_SIZE_MAX */
    public static final int FETCH_SIZE_MAX = 1000;

    /** PROC_MODE_CD */
    public static final String PROC_MODE_CD_CREATE = "01";

    /** PROC_MODE_CD */
    public static final String PROC_MODE_CD_UPDATE = "02";
    // 2023/07/07 QC#60925 Del Start
//    /** DS_TEST_COPY_CLS_DESC_TXT_IN */
//    public static final String DS_TEST_COPY_CLS_DESC_TXT_IN = "In";
//
//    /** DS_TEST_COPY_CLS_DESC_TXT_OUT */
//    public static final String DS_TEST_COPY_CLS_DESC_TXT_OUT = "Out";
//
//    /** DS_MTR_READ_TP_DESC_TXT_ACTUAL */
//    public static final String DS_MTR_READ_TP_DESC_TXT_ACTUAL = "ACTUAL";
//
//    /** DS_MTR_READ_TP_DESC_TXT_ADJUSTMENT */
//    public static final String DS_MTR_READ_TP_DESC_TXT_ADJUSTMENT = "ADJUSTMENT";
    // 2023/07/07 QC#60925 Del End
    /** VLD_MTR_FLG_X */
    public static final String VLD_MTR_FLG_X = "X";

    // ---------------------------------------
    // Messages
    // ---------------------------------------

    /**
     * Since the record corresponding to the Upload CSV Request Table
     * (UPLD_CSV_RQST) does not exist, it will be abended.
     */
    public static final String ZYPM0181E = "ZYPM0181E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] does not exist in Master. */
    public static final String NSBM0197E = "NSBM0197E";

    /** Invalid value is set for the process mode. */
    public static final String NSBM0198E = "NSBM0198E";

    /** The specified [Service Task Number] is not valid. */
    public static final String NSBM0199E = "NSBM0199E";

    /** The format of [Meter Reading Date] is incorrect. */
    public static final String NSBM0200E = "NSBM0200E";

    /**
     * Please enter a date earlier than Sales Date in the
     * "Meter Reading Date" field.
     */
    public static final String NSBM0201E = "NSBM0201E";

    /** Specify [@] or [@] in [@]. */
    public static final String NSBM0202E = "NSBM0202E";

    /**
     * Only Meter read valid/invalid flag can be entered for the Input
     * Parameter X.
     */
    public static final String NSBM0203E = "NSBM0203E";

    // 2023/07/07 QC#60925 Del Start
//    /** Meter does not match with the label.　 */
//    public static final String NSBM0204E = "NSBM0204E";
    // 2023/07/07 QC#60925 Del End

    /** No corresponding In read data exists. */
    public static final String NSBM0205E = "NSBM0205E";

    /**
     * The uploaded Meter Count is lower than the previous actual
     * service read. Please correct the meter read count.
     */
    public static final String NSBM0206E = "NSBM0206E";

    /**
     * The uploaded Meter Count is higher than the next actual service
     * read. Please correct the meter read count.
     */
    public static final String NSBM0207E = "NSBM0207E";

    // 2023/07/07 QC#60925 Add Start
    /**
     * Only Service Reads or Service Force Reads can be updated.
     */
    public static final String NSBM0219E = "NSBM0219E";

    /**
     * Multiple meter labels exist in Serial# and Meter Read Date.
     */
    public static final String NSBM0220E = "NSBM0220E";

    /**
     *There is a discrepancy between the uploaded meter labels and the machine physical meters.
     */
    public static final String NSBM0221E = "NSBM0221E";

    /**
     *Specify ACTUAL or ADJUSTMENT or Rollover or Exchange Meter To in Meter Read Type.
     */
    public static final String NSBM0222E = "NSBM0222E";

    // 2023/07/07 QC#60925 Add End

    /** MessageID : NYXM0001I */
    public static final String NYXM0001I = "NYXM0001I";

    /** MessageID : NYXM0002E */
    public static final String NYXM0002E = "NYXM0002E";

    // ---------------------------------------
    // Message
    // ---------------------------------------

    /** Message : RESULT_MSG_INS */
    public static final String RESULT_MSG_INS = "%d record(s) successfully created.";

    /** Message : RESULT_MSG_INS_MARNING */
    public static final String RESULT_MSG_INS_MARNING = "%d record(s) successfully created with warning.";

    /** Message : RESULT_MSG_ERR */
    public static final String RESULT_MSG_ERR = "%d record(s) skipped/errored.";

    // START 2023/10/20 N.Takatsu [QC#60925, ADD]
    /** Message : RESULT_MSG_INS_ERR */
    public static final String RESULT_MSG_INS_ERR = "There is an error uploading the same machine.";
    // END   2023/10/20 N.Takatsu [QC#60925, ADD]
}
