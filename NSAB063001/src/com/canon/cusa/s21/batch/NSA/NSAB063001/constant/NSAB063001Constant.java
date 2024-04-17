/**
 * <pre>
 * Copyright (c) 2015 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB063001.constant;

/**
 * <pre>
 * Machine Master Contact Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/13   Hitachi         A.Kohinata      Create          N/A
 * 2016/07/04   Hitachi         T.Tomita        Update          QC#11164
 * 2017/09/19   Hitachi         M.Kidokoro      Update          QC#21031
 * 2018/01/17   CITS            M.Naito         Update          QC#22038
 * 2018/07/05   Hitachi         K.Kitachi       Update          QC#26340
 * 2018/07/17   Hitachi         A.Kohinata      Update          QC#22583
 * 2018/09/25   Hitachi         K.Kitachi       Update          QC#27788
 * </pre>
 */
public class NSAB063001Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSAB0630";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** MAX_COMMIT_NUMBER : 1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /**
     * Since the record corresponding to the Upload CSV Request Table
     * (UPLD_CSV_RQST) does not exist, it will be abended.
     */
    public static final String ZYPM0181E = "ZYPM0181E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Global Company Code is mandatory. */
    public static final String NASM0010E = "NASM0010E";

    /** Sales date cannot be obtained. */
    public static final String NASM0011E = "NASM0011E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** "@" does not exist in master. */
    public static final String NSAM0037E = "NSAM0037E";

    /** "@" is invalid. */
    public static final String NSAM0040E = "NSAM0040E";

    // START 2017/09/19 M.Kidokoro [QC#21031, ADD]
    /** Data Duplication Error occurred. */
    public static final String NSAM0213E = "NSAM0213E";
    // END 2017/09/19 M.Kidokoro [QC#21031, ADD]

    /** The start date must be later than [@]. */
    public static final String NSAM0373E = "NSAM0373E";

    // START 2016/07/04 T.Tomita [QC#11164, ADD]
    /** The end date must be later than [@]. */
    public static final String NSAM0374E = "NSAM0374E";
    // END 2016/07/04 T.Tomita [QC#11164, ADD]

    /** Only a contact can be a primary contact. */
    public static final String NSAM0445E = "NSAM0445E";

    /** Please confirm the error details. */
    public static final String NSAM0510E = "NSAM0510E";

    /** [@] is not set in Service Machine Master. */
    public static final String NSAM0535E = "NSAM0535E";

    // START 2018/09/25 K.Kitachi [QC#27788, ADD]
    /** Multiple Primary cannot be setup in one machine contacts. */
    public static final String NSAM0738E = "NSAM0738E";
    // END 2018/09/25 K.Kitachi [QC#27788, ADD]

    // START 2018/01/17 M.Naito [QC#22038, ADD]
    // START 2018/07/05 K.Kitachi [QC#26340, MOD]
//    /** The target data does not exist in Contract. */
//    public static final String NSZM0617E = "NSZM0617E";
    /** The target data does not exist in @. */
    public static final String NSZM0610E = "NSZM0610E";
    // END 2018/07/05 K.Kitachi [QC#26340, MOD]
    // END 2018/01/17 M.Naito [QC#22038, ADD]

    /** MSG_UPLD_CSV_RQST_PK */
    public static final String MSG_UPLD_CSV_RQST_PK = "Upload CSV Request PK";

    /** MSG_MODE */
    public static final String MSG_MODE = "Mode";

    /** MSG_BASE_ID_OR_SERIAL_NUM */
    public static final String MSG_BASE_ID_OR_SERIAL_NUM = "Install Base ID or Serial#";

    /** MSG_CONTACT_ID */
    public static final String MSG_CONTACT_ID = "Contact ID";

    /** MSG_LAST_NAME */
    public static final String MSG_LAST_NAME = "Last Name";

    /** MSG_FIRST_NAME */
    public static final String MSG_FIRST_NAME = "First Name";

    /** MSG_CUR_LOC_ACCT_NUM */
    public static final String MSG_CUR_LOC_ACCT_NUM = "Current Loc Acct#";

    /** MSG_CTAC_PNT */
    public static final String MSG_CTAC_PNT = "Contact Point";

    /** MSG_CTAC_PNT_VAL */
    public static final String MSG_CTAC_PNT_VAL = "Contact Value";

    /** MSG_CTAC_TP_CD */
    public static final String MSG_CTAC_TP_CD = "IB Contact Type";

    /** MSG_EFF_FROM_DT */
    public static final String MSG_EFF_FROM_DT = "Start Date";

    /** MSG_SALES_DATE */
    public static final String MSG_SALES_DATE = "Sales Date";

    // START 2017/09/19 M.Kidokoro [QC#21031, ADD]
    /** MAX_DT_VAL */
    public static final String MAX_DT_VAL = "29991231";
    // END 2017/09/19 M.Kidokoro [QC#21031, ADD]

    // add start 2018/07/17 QC#22583
    /** MessageID : NYXM0001I */
    public static final String NYXM0001I = "NYXM0001I";

    /** MessageID : NYXM0002E */
    public static final String NYXM0002E = "NYXM0002E";

    /** Message : RESULT_MSG_INS */
    public static final String RESULT_MSG_INS = "%d record(s) successfully created.";

    /** Message : RESULT_MSG_UPD */
    public static final String RESULT_MSG_UPD = "%d record(s) successfully updated.";

    /** Message : RESULT_MSG_ERR */
    public static final String RESULT_MSG_ERR = "%d record(s) skipped/errored.";
    // add end 2018/07/17 QC#22583
}
