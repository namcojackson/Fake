/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB073001.constant;

/**
 * <pre>
 * NSAB073001
 * Void Credit Card for Service Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/08/2016   Hitachi         N.Arai          Create          N/A
 * </pre>
 */

public class NSAB073001Constant {

    /**
     * BATCH_PROGRAM_ID(NSAB073001)
     */
    public static final String PROGRAM_ID = "NSAB073001";

    /**
     * BATCH_BUSINESS_ID(NSAB0730)
     */
    public static final String BUSINESS_ID = "NSAB0730";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /**
     * Error Msg : Global Company Code is mandatory.
     */
    public static final String NASM0010E = "NASM0010E";

    /**
     * Error Msg : Sales date cannot be obtained.
     */
    public static final String NSAM0178E = "NSAM0178E";

    /** Mail Group From(FROM0005) */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Mail Group To(NSAB0730) */
    public static final String MAIL_GROUP_ID_TO = BUSINESS_ID;

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = BUSINESS_ID + "M001";

    /** Mail Item (${batchId}) */
    public static final String MAIL_ITEM_BATCH_ID = "batchId";

    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    /** Mail Item (${errDate}) */
    public static final String MAIL_ITEM_ERR_DATE = "errDate";

    /** Mail Item (${message}) */
    public static final String MAIL_ITEM_ERR_MSG = "message";

    /** ProsessMode 02:Write CC Transaction */
    public static final String WRITE_CC_TRANSACTION_02 = "02";

}
