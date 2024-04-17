/**
 * <pre>
 * Copyright (c) 2015 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB060001.constant;

/**
 * <pre>
 * Meter Read Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/27   Hitachi         A.Kohinata      Create          N/A
 * 2017/02/07   Hitachi         N.Arai          Update          QC#17296
 * 2017/05/22   Hitachi         K.Kitachi       Update          QC#18342
 * 2017/09/19   Hitachi         T.Tomita        Update          QC#21185
 * 2017/09/26   Hitachi         T.Tomita        Update          QC#21185
 * 2017/12/20   Hitachi         M.Kidokoro      Update          QC#21127
 * 2018/07/17   Hitachi         A.Kohinata      Update          QC#22583
 * 2019/01/10   Hitachi         Y.Takeno        Update          QC#29719
 * 2019/01/30   Hitachi         K.Kitachi       Update          QC#29719
 * 2019/08/20   Hitachi         K.Kitachi       Update          QC#52860
 * 2024/03/26   Hitachi         K.Watanabe      Update          QC#63549
 * </pre>
 */
public class NSAB060001Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSAB0600";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** MIN_EFF_FROM_DT */
    public static final String MIN_EFF_FROM_DT = "20000101";

    /** MAX_EFF_THRU_DT */
    public static final String MAX_EFF_THRU_DT = "99991231";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * Since the record corresponding to the Upload CSV Request Table
     * (UPLD_CSV_RQST) does not exist, it will be abended.
     */
    public static final String ZYPM0181E = "ZYPM0181E";

    /** "@" is invalid. */
    public static final String NSAM0040E = "NSAM0040E";

    /** Please confirm the error details. */
    public static final String NSAM0510E = "NSAM0510E";

    /** Serial# does not exist in Install Base. */
    public static final String NSAM0511E = "NSAM0511E";

    /**
     * Meter Label Code/Description does not exist in Meter Label
     * Master.
     */
    public static final String NSAM0512E = "NSAM0512E";

    /** Reading Date cannot be greater than the today's date. */
    public static final String NSAM0513E = "NSAM0513E";

    /** Multiple meter labels exist in Serial# and Meter Read Date. */
    public static final String NSAM0514E = "NSAM0514E";

    /**
     * Meter Label Code/Description does not exist in Service Physical
     * Meter.
     */
    public static final String NSAM0515E = "NSAM0515E";

    /**
     * Current meter reading can not be lower than the previous read.
     * Please select the meter reading type as ‘Adjustment’ to enter
     * lower reads.
     */
    public static final String NSAM0516E = "NSAM0516E";

    /** Error records exist in the same Serial# and Meter Read Date. */
    public static final String NSAM0517E = "NSAM0517E";

    /** MSG_GLOBAL_COMPANY_CODE */
    public static final String MSG_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** MSG_SERIAL_NUM */
    public static final String MSG_SERIAL_NUM = "Serial#";

    /** MSG_MTR_READ_DT */
    public static final String MSG_MTR_READ_DT = "Meter Read Date";

    /** MSG_MTR_LB_CD_AND_DESC */
    public static final String MSG_MTR_LB_CD_AND_DESC = "Meter Label Code or Description";

    /** MSG_UPLD_CSV_RQST_PK */
    public static final String MSG_UPLD_CSV_RQST_PK = "Upload CSV Request PK";

 // START 2017/02/07 N.Arai [QC#17296, MOD]
    /** Upload failed.(@) */
    public static final String NSAM0624E = "NSAM0624E";
 // END 2017/02/07 N.Arai [QC#17296, MOD]

    // Add Start 2017/09/19 QC#21185
    /** Upload warning.(@) */
    public static final String NSAM0700W = "NSAM0700W";
    // Add End 2017/09/19 QC#21185
    // Add Start 2017/09/26 QC#21185
    /** Please confirm the warning details. */
    public static final String NSAM0705W = "NSAM0705W";
    // Add End 2017/09/26 QC#21185

    // START 2018/01/10 [QC#29719, ADD]
    // START 2019/01/30 K.Kitachi [QC#29719, DEL]
//    /** Serial # cannot be uniquely specified. Please enter Mdse Code too. */
//    public static final String NSAM0018E = "NSAM0018E";
    // END 2019/01/30 K.Kitachi [QC#29719, DEL]
    // END   2018/01/10 [QC#29719, ADD]

    // START 2017/05/22 K.Kitachi [QC#18342, ADD]
    /** Format System Time stamp **/
    public static final String FORMAT_SYS_TS = "yyyyMMddHHmmssSSS";

    /** Column name : DS_MTR_INTFC_PK */
    public static final String DS_MTR_INTFC_PK = "DS_MTR_INTFC_PK";
    // END 2017/05/22 K.Kitachi [QC#18342, ADD]

    // START 2017/12/20 M.Kidokoro [QC#21127, ADD]
    /**
     * Current meter reading can not be higher than the next actual
     * read. Please enter lower reads than the next actual read.
     */
    public static final String NSZM1312E = "NSZM1312E";
    // END 2017/12/20 M.Kidokoro [QC#21127, ADD]

    // add start 2018/07/17 QC#22583
    /** MessageID : NYXM0001I */
    public static final String NYXM0001I = "NYXM0001I";

    /** MessageID : NYXM0002E */
    public static final String NYXM0002E = "NYXM0002E";

    /** Message : RESULT_MSG_INS */
    public static final String RESULT_MSG_INS = "%d record(s) successfully created.";

    /** Message : RESULT_MSG_INS_MARNING */
    public static final String RESULT_MSG_INS_MARNING = "%d record(s) successfully created with warning.";

    /** Message : RESULT_MSG_ERR */
    public static final String RESULT_MSG_ERR = "%d record(s) skipped/errored.";
    // add end 2018/07/17 QC#22583

    // START 2019/08/20 K.Kitachi [QC#52860, ADD]
    /**
     *  You can not register a new meter read within the invoiced period.
     */
    public static final String NSAM0709E = "NSAM0709E";

    /**
     * The reads will be registered as meter rollover because of lower reads than previous one.
     */
    public static final String NSZM1335W = "NSZM1335W";
    // END 2019/08/20 K.Kitachi [QC#52860, ADD]

    // START 2024/03/26 K.Watanabe [QC#63549, ADD]
    /** There is billing schedule that requires final read. Please check the meter type. */
    public static final String NSAM0787W = "NSAM0787W";

    /** DEF_WINDOW_BEF_DAYS */
    public static final int DEF_WINDOW_BEF_DAYS = 0;

    /**
     * SQL parameter<br>
     * SQL ID = getFinalReadPeriod<br>
     */
    public static final String DS_WIN_DAYS_TRGT_PER_TXT = "*";

    public static final String DS_WIN_DAYS_SVC_LINE_BIZ_CD_ALL = "*";
    // END   2024/03/26 K.Watanabe [QC#63549, ADD]
}
