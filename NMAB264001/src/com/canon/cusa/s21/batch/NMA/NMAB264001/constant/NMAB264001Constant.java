/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB264001.constant;

/**
 * NMAB264001Constant.
 */
public class NMAB264001Constant {

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** A past date cannot be entered into the "Date Effective From". */
    public static final String NMAM0185E = "NMAM0185E";

    /** The entered [@] does not exist in master. */
    public static final String NMAM8121E = "NMAM8121E";

    /** Parameter @ is required. */
    public static final String NMAM8228E = "NMAM8228E";

    /** [@] is existed in master. */
    public static final String NMAM8440E = "NMAM8440E";

    /** Please confirm the error details. */
    public static final String NMAM8457E = "NMAM8457E";

    /** Please input Effective Date From/To within Resource Effective. */
    public static final String NMAM8488E = "NMAM8488E";

    /** @  is invalid format. */
    public static final String NMAM8567E = "NMAM8567E";

    /** Multiple same organization update request can't be requested. */
    public static final String NMAM8577E = "NMAM8577E";

    /** Current Resource# has been updated by another user because Current Resource#'s last update time is after request date time. */
    public static final String NMAM8605E = "NMAM8605E";

    /** Move Resource# has been updated by another user because Move Resource#'s last update time is after request date time. */
    public static final String NMAM8606E = "NMAM8606E";

    /** Current/Move Resource# has been updated by another user because Current/Move Resource#'s last update time is after request date time. */
    public static final String NMAM8607E = "NMAM8607E";

    /** Since the record corresponding to the Upload CSV Request Table (UPLD_CSV_RQST) does not exist, it will be abended. */
    public static final String ZYPM0181E = "ZYPM0181E";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** @ */
    public static final String NYXM0001I = "NYXM0001I";

    /** @ */
    public static final String NYXM0002E = "NYXM0002E";

    /** MSG_UPLD_CSV_RQST_PK */
    public static final String MSG_UPLD_CSV_RQST_PK = "Upload CSV Request PK";

    /** Info Message(@ record(s) successfully created.) */
    public static final String INFO_MSG = " record(s) successfully created.";

    /** Error Message(@ record(s) skipped/errored.) */
    public static final String ERR_MSG = " record(s) skipped/errored.";

    /** Date check Pattern */
    public static final String CHK_DATE_PATTERN = "\\d{1,2}/\\d{1,2}/\\d{4}";
}
