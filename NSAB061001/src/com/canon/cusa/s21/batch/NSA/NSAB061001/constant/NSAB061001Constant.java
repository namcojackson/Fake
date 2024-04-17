/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB061001.constant;

/**
 * <pre>
 * Machine Master Solution Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/13   Hitachi         T.Tomita        Create          QC#6999
 * 2017/02/09   Hitachi         N.Arai          Update          QC#17296
 * 2018/07/17   Hitachi         A.Kohinata      Update          QC#22583
 * </pre>
 */
public class NSAB061001Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSAB0610";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** MAX_COMMIT_NUMBER */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** SVC_CONFIG_LIST_NUM */
    public static final int SVC_CONFIG_LIST_NUM = 7;

    /** [@] does not exist in Master. */
    public static final String NSAM0011E = "NSAM0011E";

    /** "@" is invalid. */
    public static final String NSAM0040E = "NSAM0040E";

    /** Please confirm the error details. */
    public static final String NSAM0510E = "NSAM0510E";

    /**
     * Since the record corresponding to the Upload CSV Request Table
     * (UPLD_CSV_RQST) does not exist, it will be abended.
     */
    public static final String ZYPM0181E = "ZYPM0181E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** MSG_GLOBAL_COMPANY_CODE */
    public static final String MSG_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** MSG_SALES_DATE */
    public static final String MSG_SALES_DATE = "Sales Date";

    /** MSG_SVC_SLN_NM */
    public static final String MSG_SVC_SLN_NM = "Solution Name";

    /** MSG_SVC_CONFIG_MSTR_PK_01 */
    public static final String MSG_SVC_CONFIG_MSTR_PK_01 = "Config ID 1";

    /** MSG_SVC_CONFIG_MSTR_PK_02 */
    public static final String MSG_SVC_CONFIG_MSTR_PK_02 = "Config ID 2";

    /** MSG_SVC_CONFIG_MSTR_PK_03 */
    public static final String MSG_SVC_CONFIG_MSTR_PK_03 = "Config ID 3";

    /** MSG_SVC_CONFIG_MSTR_PK_04 */
    public static final String MSG_SVC_CONFIG_MSTR_PK_04 = "Config ID 4";

    /** MSG_SVC_CONFIG_MSTR_PK_05 */
    public static final String MSG_SVC_CONFIG_MSTR_PK_05 = "Config ID 5";

    /** MSG_SVC_CONFIG_MSTR_PK_06 */
    public static final String MSG_SVC_CONFIG_MSTR_PK_06 = "Config ID 6";

    /** MSG_SVC_CONFIG_MSTR_PK_07 */
    public static final String MSG_SVC_CONFIG_MSTR_PK_07 = "Config ID 7";

    /** MSG_UPLD_CSV_RQST_PK */
    public static final String MSG_UPLD_CSV_RQST_PK = "Upload CSV Request PK";

 // START 2017/02/09 N.Arai [QC#17296, MOD]
    /** Upload failed.(@) */
    public static final String NSAM0624E = "NSAM0624E";
 // END 2017/02/09 N.Arai [QC#17296, MOD]

    // add start 2018/07/17 QC#22583
    /** MessageID : NYXM0001I */
    public static final String NYXM0001I = "NYXM0001I";

    /** MessageID : NYXM0002E */
    public static final String NYXM0002E = "NYXM0002E";

    /** Message : RESULT_MSG_UPD */
    public static final String RESULT_MSG_UPD = "%d record(s) successfully updated.";

    /** Message : RESULT_MSG_ERR */
    public static final String RESULT_MSG_ERR = "%d record(s) skipped/errored.";
    // add end 2018/07/17 QC#22583
}
