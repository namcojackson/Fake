/**
 * <pre>
 * Copyright (c) 2015 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB013001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Batch Fax
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/07/2015   Hitachi         T.Tomita        Create          N/A
 * </pre>
 */
public class NSAB013001Constant {

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Failed to insert "@". */
    public static final String INS_ERR_MSD_ID = "NSAM0032E";

    /** "@" does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: InterfaceId */
    public static final String MSG_ITEM_INTERFACE_ID = "Interface Id";

    /** message Item: BatchFaxDays */
    public static final String MSG_ITEM_BAT_FAX_DAYS = "Batch Fax Days";

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** Substring Position 8 */
    public static final int SUB_STR_POS_8 = 8;

    /** Substring Position 10 */
    public static final int SUB_STR_POS_10 = 10;

    /** Substring Position 12 */
    public static final int SUB_STR_POS_12 = 12;

    /** Substring Position 15 */
    public static final int SUB_STR_POS_15 = 15;

    /** Substring Position 20 */
    public static final int SUB_STR_POS_20 = 20;

    /** Substring Position 30 */
    public static final int SUB_STR_POS_30 = 30;

    /** Substring Position 150 */
    public static final int SUB_STR_POS_150 = 150;

    /** Space */
    public static final String STR_SPACE = " ";

    /** Hyphen */
    public static final String STR_HYPHEN = "-";

    /** Business Application ID */
    public static final String STR_BIZ_APP_ID = "NSAB0130";

    /** MAX_COUNT:999999999 */
    public static final BigDecimal MAX_COUNT = new BigDecimal(999999999);

    /** NumConst Key */
    public static final String KEY_NSAB0130_BAT_FAX_DAYS = "BAT_FAX_DAYS";

    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = "NSAB0130";

    /** mail key for To */
    public static final String MAIL_KEY_TO = "To";

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = STR_BIZ_APP_ID + "M001";

    /** template parameter key : batch id */
    public static final String MAIL_TEMPLATE_KEY_ID = "batchId";

    /** template parameter key : err date */
    public static final String MAIL_TEMPLATE_KEY_DATE = "errDate";

    /** template parameter key : message */
    public static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    /** DS Contract PK */
    public static final String KEY_DS_CONTR_PK = "DS Contract PK";

    /** DS Contract Detail PK */
    public static final String KEY_DS_CONTR_DTL_PK = "DS Contract Detail PK";

    /** Service Machine Master PK */
    public static final String KEY_SVC_MACH_MSTR_PK = "Service Machine Master PK";

    /** DS Contract Billing Meter PK */
    public static final String KEY_DS_CONTR_BLLG_MTR_PK = "DS Contract Billing Meter PK";

    /** Service Physical Meter PK */
    public static final String KEY_SVC_PHYS_MTR_PK = "Service Physical Meter PK";

    /** Total Count */
    public static final String KEY_TOTAL_COUNT = "Total Count";

    /** Error Count */
    public static final String KEY_ERROR_COUNT = "Error Count";

}
