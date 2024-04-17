/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB273001.constant;

/**
 * <pre>
 * NMAB2650 Sales Territory Visibility Defaulting Processor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/07/15   Fujitsu         M.Ohno          Create          QC#57315
 *</pre>
 */
public class NMAB273001Constant {

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** Parameter @ is required. */
    public static final String NMAM8228E = "NMAM8228E";

    /** @ is invalid. */
    public static final String NMAM0071E = "NMAM0071E";
    
    /** The entered [@] does not exist in master. */
    public static final String NMAM8121E = "NMAM8121E";

    /** Error Msg */
    public static final String ZYPM0181E = "ZYPM0181E";

    /** @ */
    public static final String NYXM0001I = "NYXM0001I";

    /** @ */
    public static final String NYXM0002E = "NYXM0002E";

    /** MSG_UPLD_CSV_RQST_PK */
    public static final String MSG_UPLD_CSV_RQST_PK = "Upload CSV Request PK";

    /** MSG_UPLD_CSV_ID */
    public static final String MSG_UPLD_CSV_ID = "Upload Csv ID";

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** TRTY_SIZE */
    public static final int TRTY_SIZE = 20;

    /** MAX_DT */
    public static final String MAX_DT = "99991231";

    /** Info Message(@ record(s) successfully updated.) */
    public static final String INFO_MSG = " record(s) successfully updated.";

    /** Error Message(@ record(s) skipped/errored.) */
    public static final String ERR_MSG = " record(s) skipped/errored.";
}
