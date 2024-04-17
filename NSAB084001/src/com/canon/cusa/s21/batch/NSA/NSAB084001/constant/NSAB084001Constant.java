/*
 * <pre>Copyright (c) 2021 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB084001.constant;

/**
 * <pre>
 * Contract Branch Rep Update Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/04/21   Hitachi         T.Nagae         Create          CCI-QC#61248
 *</pre>
 */
public final class NSAB084001Constant {

    /** Batch Id */
    public static final String BATCH_ID = "NSAB084001";

    /** FETCH_SIZE_MAX */
    public static final int FETCH_SIZE_MAX = 1000;

    /** MAX_COMMIT_LIMIT */
    public static final int MAX_COMMIT_LIMIT = 1000;

    /** UPD_SYSTEM_NAME */
    public static final String UPD_SYSTEM_NAME = "SYSTEM";

    /** MAX_DATA */
    public static final int MAX_DATA = 99999999;

    // ---------------------------------------
    // Messages
    // ---------------------------------------

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** It failed to update @ Table.[@] */
    public static final String NSAM0470E = "NSAM0470E";

    /** [@] is not registered.(@) */
    public static final String NSAM0069E = "NSAM0069E";

    // ---------------------------------------
    // Update Status
    // ---------------------------------------

    /** UPD_STS_COMPLETE */
    public static final String UPD_STS_COMPLETE = "UPD_STS_COMPLETE";

    /** UPD_STS_WARNING */
    public static final String UPD_STS_WARNING = "UPD_STS_WARNING";

    /** UPD_STS_ERROR */
    public static final String UPD_STS_ERROR = "UPD_STS_ERROR";

    /** UPD_STS_ERROR BOL IS NOT REGISTED */
    public static final String UPD_STS_ERROR_BOL_IS_NOT_REGISTED = "UPD_STS_ERROR_BOL_IS_NOT_REGISTED";

    // ---------------------------------------
    // Constant Key
    // ---------------------------------------

    /** VAR_CHAR_CONST CONTR_REP_DEFAULT KEY : PPS */
    public static final String CONTR_REP_KEY_PPS = "PPS_CONTR_REP_DEFAULT";

    /** VAR_CHAR_CONST CONTR_REP_DEFAULT KEY : LFS */
    public static final String CONTR_REP_KEY_LFS = "LFS_CONTR_REP_DEFAULT";

    /** VAR_CHAR_CONST CONTR_REP_DEFAULT KEY : ESS */
    public static final String CONTR_REP_KEY_ESS = "ESS_CONTR_REP_DEFAULT";

    // ---------------------------------------
    // Mail Template
    // ---------------------------------------

    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    public static final String MAIL_GROUP_ID_TO = "NSAB0840";

    public static final String MAIL_TEMPLATE_ID = "NSAB0840M001";

    public static final String MAIL_TEMPLATE_SET_KEY_BATCH_ID = "batchId";

    public static final String MAIL_TEMPLATE_SET_KEY_ERROR_DATE = "errDate";

    public static final String MAIL_TEMPLATE_SET_KEY_MESSAGE = "message";

    public static final String ORG_STRU_TP_CD_BOS = "BOS";

    public static final String DS_CONTR_STS_CD_EXPIRED = "4";

    public static final String DS_CONTR_STS_CD_TERMINATED = "T";
}
