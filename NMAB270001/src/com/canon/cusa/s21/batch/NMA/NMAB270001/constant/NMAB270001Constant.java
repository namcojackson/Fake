/*
 * Copyright (c) 2016 Canon USA Inc. All rights reserved. Original
 */
package com.canon.cusa.s21.batch.NMA.NMAB270001.constant;

/**
 * <pre>
* Duns Upload Batch
*
* Date           Company    Name         Create/Update     Defect No
* ----------------------------------------------------------------------
* 2016/07/21     Fujitsu    R.Nakamura   Create            N/A
* 2016/09/21     Fujitsu    C.Yokoi      Update            QC#14694
*</pre>
 */
public class NMAB270001Constant {

    /** DEFAULT_FETCH_SIZE */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Batch ID */
    public static final String BATCH_ID = "MMAB2690";

    /** Batch name */
    public static final String BATCH_NAME = "DUNS Upload";

    /** Business ID */
    public static final String BIZ_ID = "NMZC0010";

    /** Error Value Name(Global Company Code) */
    public static final String GLBL_CMPY_CD = "Global Company Code";

    /** Error Value Name(Sale Date) */
    public static final String SALE_DATE = "Sale Date";

    /** Error Value Name(Sale Date) */
    public static final String CLSS_FUNC_CD = "ClassFunction Code";

    /** Error Value Name(Sale Date) */
    public static final String COA_CH_CD = "GL Classification Code";

    /** Error Value Name(Sale Date) */
    public static final String COA_AFFL_CD = "GL Intercompany Code";

    /** MSG_LOCATION */
    public static final String MSG_LOCATION = "Location";

    /** MSG_PAREN_START */
    public static final String MSG_PAREN_START = "(";

    /** MSG_PAREN_END */
    public static final String MSG_PAREN_END = ")";

    /** MSG_UPLD_CSV_ID */
    public static final String MSG_UPLD_CSV_ID = "Upload Csv ID";

    /** Search Value Name(Sale Date) */
    public static final String VAR_CHAR_CONST_NM = "NMAB5810_DEF_LGCY_CUST_CLS_CD";

    /** PROCESS MODE 02 New Prospect update. */
    public static final String PROCESS_MODE_PROS_UPD = "02";

    /** Upload a Reviewed Duns Data */
    public static final String UPLOAD_REVIEW_DATA = "30";

    /** Upload Duns File */
    public static final String UPLOAD_DUNS_FILE = "40";

    /** Search Value Name(Application Function ID) */
    public static final String APP_FUNC_ID = "NMAL6700001";

    /** Details cannot be added because the number will exceed [@]. */
    public static final String NMAM0050E = "NMAM0050E";

    // 2016/09/21 CSA-QC#14694 Add Start
    /** The entered [@] does not exist in master. */
    public static final String NMAM8121E = "NMAM8121E";
    // 2016/09/21 CSA-QC#14694 Add End

    /** Parameter @ is required. */
    public static final String NMAM8228E = "NMAM8228E";

    /** Account# @ is not exist in Master. */
    public static final String NMAM8411E = "NMAM8411E";

    /** Location# related @(Account#) is not exist in Master. */
    public static final String NMAM8412E = "NMAM8412E";

    /** @ is invalid format. */
    public static final String NMAM8567E = "NMAM8567E";

    /** Duplicate Account# and Location#. */
    public static final String NMAM8568E = "NMAM8568E";

    /** @ */
    public static final String NYXM0001I = "NYXM0001I";

    /** @ */
    public static final String NYXM0002E = "NYXM0002E";

    /** Error Msg */
    public static final String ZYPM0181E = "ZYPM0181E";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** View Time Type */
    public static final String SET_TIME_TYPE = "yyyyMMddHHmmssSSS";

    /** View Date Type */
    public static final String SET_DATE_TYPE = "MMddyyyy";

    /** MSG_UPLD_CSV_RQST_PK */
    public static final String MSG_UPLD_CSV_RQST_PK = "Upload CSV Request PK";

    /** S21 ACCOUNT NUMBER */
    public static final String S21_ACCT_NUM = "S21 ACCOUNT NUMBER";

    /** S21 LOCATION NUMBER */
    public static final String S21_LOC_NUM = "S21 LOCATION NUMBER";

    /** Info Message(@ record(s) successfully created.) */
    public static final String INFO_MSG = " record(s) successfully created.";

    /** Error Message(@ record(s) skipped/errored.) */
    public static final String ERR_MSG = " record(s) skipped/errored.";

    /** Date Pattan */
    public static final int DATE_LENGTH = 8;

    /** Update Count */
    public static final String UPDATE_COUNT_CONFIGRATION = "NMAL2840_UPLOAD_MAX_NUM_ROWS";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";
}
