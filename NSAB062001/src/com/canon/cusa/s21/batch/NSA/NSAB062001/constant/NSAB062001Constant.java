/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB062001.constant;

/**
 * <pre>
 * Machine Master Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/21   Hitachi         T.Tomita        Create          QC#6999
 * 2016/10/14   Hitachi         T.Tomita        Update          QC#14734
 * 2017/01/30   Hitachi         K.Ochiai        Update          QC#17296
 * 2017/07/06   Hitachi         K.Kojima        Update          QC#19654
 * 2018/05/28   Hitachi         K.Kitachi       Update          QC#19932
 * 2018/07/17   Hitachi         A.Kohinata      Update          QC#22583
 * 2018/08/22   CITS            M.Naito         Update          QC#20733
 * 2019/08/07   Hitachi         A.Kohinata      Update          QC#52198
 * 2021/11/15   CITS            R.Jabal         Update          QC#59104
 * 2023/09/22   Hitachi         T.Kuroda        Update          QC#61265
 * 2023/10/06   Hitachi         Y.Nagasawa      Update          QC#60082
 * </pre>
 */
public class NSAB062001Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSAB0620";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** MAX_COMMIT_NUMBER */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** DEF_MAX_COUNT */
    public static final int DEF_MAX_COUNT = 1000;

    /** NSAB0620_MAX_PROC_CNT */
    public static final String NSAB0620_MAX_PROC_CNT = "NSAB0620_MAX_PROC_CNT";

    /** Mode : Create */
    public static final String MODE_CREATE = "01";

    /** Mode : Update */
    public static final String MODE_UPDATE = "02";

    /** Mode : Terminate */
    public static final String MODE_TERMINATE = "03";

    /** UPDATE */
    public static final String FUNC_ID_UPDATE = "NSAL0010T020";

    /** SERIAL_CHANGE */
    public static final String FUNC_ID_SER_CHANGE = "NSAL0010T030";

    /** MDSE_EDIT_USER */
    public static final String FUNC_ID_MDSE_EDIT = "NSAL0010T050";

    // massage
    /** [@] does not exist in Master. */
    public static final String NSAM0011E = "NSAM0011E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** "@" is invalid. */
    public static final String NSAM0040E = "NSAM0040E";

    /** Specified "@" already exists. */
    public static final String NSAM0059E = "NSAM0059E";

    /**
     * Machine master status cannot be updated because active contract
     * exists.[@]
     */
    public static final String NSAM0381E = "NSAM0381E";

    /**
     * Machine master status cannot be updated because active order
     * exists.[@]
     */
    public static final String NSAM0382E = "NSAM0382E";

    /** Entered Item Number is not install base trackable. */
    public static final String NSAM0399E = "NSAM0399E";

    /** Serial# cannot be entered because the specified item is not serial trackable. */
    public static final String NSAM0451E = "NSAM0451E";

    /** Specified item is not Inventory Trackable therefore Serial # cannot be entered. */
    public static final String NSAM0473E = "NSAM0473E";

    /** Please confirm the error details. */
    public static final String NSAM0510E = "NSAM0510E";

    /** Processing record count has gone over the limit of max [@]. */
    public static final String NSAM0542E = "NSAM0542E";

    /** Processing record count is 0. */
    public static final String NSAM0543E = "NSAM0543E";

    /** CSV upload user don't have the authorization to process. */
    public static final String NSAM0544E = "NSAM0544E";

    /** CSV upload user don't have the authorization for "@". */
    public static final String NSAM0545E = "NSAM0545E";

    /**
     * Relation between "Bill to Account" and "Bill to Location" is
     * not correct.
     */
    public static final String NSAM0546E = "NSAM0546E";

    /**
     * Relation between "Current Location Account" and
     * "Current Location" is not correct.
     */
    public static final String NSAM0547E = "NSAM0547E";

    /**
     * Relation between "Bill to Account" and "Current Location" is
     * not correct.
     */
    public static final String NSAM0548E = "NSAM0548E";

    /**
     * Relation between "Bill to Account" and "Owner Account" is not
     * correct.
     */
    public static final String NSAM0549E = "NSAM0549E";

    /** This machine is already used by other configuration. [@] */
    public static final String NSAM0550E = "NSAM0550E";

    /**
     * Relationship between Machine Master Status and Machine Usage
     * Status are invalid.
     */
    public static final String NSZM0869E = "NSZM0869E";

    /**
     * Since the record corresponding to the Upload CSV Request Table
     * (UPLD_CSV_RQST) does not exist, it will be abended.
     */
    public static final String ZYPM0181E = "ZYPM0181E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    // START 2016/10/14 T.Tomita [QC#14734, ADD]
    /**
     * When Machine Status is "@", cannot entered service configuration.
     */
    public static final String NSAM0613E = "NSAM0613E";
    // END 2016/10/14 T.Tomita [QC#14734, ADD]

    // START 2017/01/30 K.Ochiai [QC#17296, ADD]
    /**
     * Upload failed.(@)
     */
    public static final String NSAM0624E = "NSAM0624E";
    // END 2017/01/30 K.Ochiai [QC#17296, ADD]

    // START 2017/07/06 K.Kojima [QC#19654,ADD]
    /**
     * Parent Serial# has not been assigned Config# yet.
     */
    public static final String NSAM0685E = "NSAM0685E";
    // END 2017/07/06 K.Kojima [QC#19654,ADD]

    // START 2018/05/28 K.Kitachi [QC#19932, ADD]
    /** Because inconsistency with inventory will occur, it cannot be changed. */
    public static final String NSAM0722E = "NSAM0722E";
    // END 2018/05/28 K.Kitachi [QC#19932, ADD]

    // START 2018/08/22 M.Naito [QC#20733, ADD]
    /** Serial # cannot be uniquely specified. Please enter Mdse Code too. */
    public static final String NSAM0018E = "NSAM0018E";
    // START 2018/08/22 M.Naito [QC#20733, ADD]

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

    // add start 2019/08/07 QC#52198
    /** VARCHAR CONST KEY: OWNR_RELN_CHECK_EXCL_TRX_TP_CD */
    public static final String OWNR_RELN_CHECK_EXCL_TRX_TP_CD = "OWNR_RELN_CHECK_EXCL_TRX_TP_CD";

    /** VARCHAR CONST KEY: OWNR_RELN_CHECK_EXCL_ACCT_NUM */
    public static final String OWNR_RELN_CHECK_EXCL_ACCT_NUM = "OWNR_RELN_CHECK_EXCL_ACCT_NUM";
    // add end 2019/08/07 QC#52198

    // START 2021/11/15 R.Jabal [QC#59104, ADD]
    /** 
     * MessageID: NSAM0758E Machine to be updated is unavailable.
     */
    public static final String NSAM0758E = "NSAM0758E";
    // END 2021/11/15 R.Jabal [QC#59104, ADD]

    // START 2023/09/22 T.Kuroda [QC#61265, ADD]
    /** Loc Sts Blank */
    public static final String LOC_STS_BLANK = "";
    // END   2022/09/22 T.Kuroda [QC#61265, ADD]

    // START 2023/10/06 Y.Nagasawa [QC#60082,ADD]
    public static final String UPDATE_END_DATE_TO_BLANK = "NSAB0620_END_DATE_BLANK";

    public static final String UPDATE_REQ_TECH_CD_TO_BLANK = "NSAB0620_REQ_TECH_CD_BLANK";
    // END 2023/10/06 Y.Nagasawa [QC#60082,ADD]
}
