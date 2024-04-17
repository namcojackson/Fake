/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB274001.constant;

/**
 *<pre>
 * Account Owner Look Up Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/03/29   Hitachi         S.Fujita        Create
 *</pre>
 */
public class NMAB274001Constant {

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** Parameter @ is required. */
    public static final String NMAM8228E = "NMAM8228E";

    /** The entered [@] does not exist in master. */
    public static final String NMAM8121E = "NMAM8121E";

    /** @ is invalid. */
    public static final String NMAM0071E = "NMAM0071E";

    /** @ is required fields. */
    public static final String NMAM0192E = "NMAM0192E";

    /** The entered [@] is not active. */
    public static final String NMAM8460E = "NMAM8460E";

    /** Please set After Territory at least 1 column. */
    public static final String NMAM8556E = "NMAM8556E";

    /** Please enter both 'Before Territory' and 'After Territory'. */
    public static final String NMAM8561E = "NMAM8561E";

    /**
     * [This record] has been updated by another user because
     * [Territory Visilibity information's last update time is after
     * request date time.]
     */
    public static final String NMAM8562E = "NMAM8562E";

    /** Please confirm the error details. */
    public static final String NMAM8457E = "NMAM8457E";

    /** Duplicate Location# in One Account. */
    public static final String NMAM8404E = "NMAM8404E";

    /** This record is no change. */
    public static final String NMAM8407E = "NMAM8407E";

    /** Please select territory which is assigned Sales Rep. */
    public static final String NMAM8545E = "NMAM8545E";

    /** Please select territory which is assigned Resource(s). */
    public static final String NMAM8546E = "NMAM8546E";

    /** MessageID : NYXM0001I */
    public static final String NYXM0001I = "NYXM0001I";

    /** MessageID : NYXM0002E */
    public static final String NYXM0002E = "NYXM0002E";

    /**
     * Territory Rule cannot be registered because Location Number
     * already exists in Territory Rule.
     */
    public static final String NMAM8587I = "NMAM8587I";

    /**
     * Territory Rule cannot be registered because Territory Rule
     * Logic Type in target rule is not "OR".
     */
    public static final String NMAM8588I = "NMAM8588I";

    /** Could not obtain Line Biz Role Type. Please set up the role. */
    public static final String NMAM8647E = "NMAM8647E";

    /**
     * The entered Territory ID and Account / Location Number is
     * inconsistent. You can edit only "Uploadable" column in the
     * upload file.
     */
    public static final String NMAM8672E = "NMAM8672E";

    /** Message : RESULT_MSG_INS */
    public static final String RESULT_MSG_INS = "%d record(s) successfully created.";

    /** Message : RESULT_MSG_UPD */
    public static final String RESULT_MSG_UPD = "%d record(s) successfully updated.";

    /** Message : RESULT_MSG_ERR */
    public static final String RESULT_MSG_ERR = "%d record(s) skipped/errored.";

    /**
     * Combination of Account# and location ID does not existed in
     * Master.
     */
    public static final String NMAM8557E = "NMAM8557E";

    /** No Account data found. */
    public static final String NMAM8683E = "NMAM8683E";

    /** GLOBAL_COMPANY_CODE */
    public static final String GLOBAL_COMPANY_CODE = "Global Company Code";

    /** MSG_UPLD_CSV_RQST_PK */
    public static final String UPLD_CSV_RQST_PK = "Upload CSV Request PK";

    /** MSG_UPLD_CSV_ID */
    public static final String UPLD_CSV_ID = "Upload Csv ID";

    /** TRTY_GRP_TP_CD */
    public static final String TRTY_GRP_TP_CD = "TRTY_GRP_TP_CD";

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** TRTY_SIZE */
    public static final int TRTY_SIZE = 20;

    /** TRTY_GRP_TP_CD_PPS */
    public static final int TRTY_GRP_TP_CD_PPS = 5;

    /** TRTY_GRP_TP_CD_LFS */
    public static final int TRTY_GRP_TP_CD_LFS = 7;

    /** TRTY_GRP_TP_CD_IS */
    public static final int TRTY_GRP_TP_CD_IS = 9;

    /** MAX_DT */
    public static final String MAX_DT = "99991231";

    /** SLS_CD */
    public static final String SLS_CD = "1";

    /** BOTH_FLG */
    public static final String BOTH_FLG = "B";

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** ORG_NM_DEL */
    public static final String ORG_NM_DEL = "DEL";

    /** LOB_PPS */
    public static final String LOB_PPS = "PPS";

    /** LOB_LFS */
    public static final String LOB_LFS = "LFS";

    /** LOB_IS */
    public static final String LOB_IS = "IS";
}
