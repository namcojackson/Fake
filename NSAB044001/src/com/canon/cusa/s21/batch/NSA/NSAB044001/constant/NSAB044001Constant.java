/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB044001.constant;

/**
 *<pre>
 * Update Not Communication Status
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/31   Hitachi         Y.Takeno        Create          N/A
 * 2016/07/08   Hitachi         Y.Takeno        Create          QC#9425
 * 2016/11/09   Hitachi         N.Arai          Update          QC#15829
 *</pre>
 */
public class NSAB044001Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSAB0440";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = "NSAB0440";

    /** mail key for To */
    public static final String MAIL_KEY_TO = "To";

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = BIZ_APP_ID + "M001";

    /** template parameter key : batch id */
    public static final String MAIL_TEMPLATE_KEY_ID = "batchId";

    /** template parameter key : err date */
    public static final String MAIL_TEMPLATE_KEY_DATE = "errDate";

    /** template parameter key : message */
    public static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** Date Time Pattern For Mail */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";

    /** Date Format: yyyyMMddHHmmssSSS */
    public static final String DATE_FORMAT_FULL = "yyyyMMddHHmmssSSS";

    /** [@] is not registered.(@) */
    public static final String NSAM0069E = "NSAM0069E";

    /** Global Company Code is mandatory. */
    public static final String NSAM0177E = "NSAM0177E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

// START 07/08/2016 [QC#9425, MOD]
    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";
// END   07/08/2016 [QC#9425, MOD]

    /** Max commit number */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** num const : IWR_CONN_ERR_DAYS */
    public static final String CONST_NM_IWR_CONN_ERR_DAYS = "IWR_CONN_ERR_DAYS";

    /** sqlId : getSvcMachMstrList */
    public static final String SQLID_GET_SVC_MACH_MSTR = "getSvcMachMstrList";

    /** table name : SVC_MACH_MSTR */
    public static final String TBL_NM_SVC_MACH_MSTR = "SVC_MACH_MSTR";

// START 2016/11/09 N.Arai [QC#15829, MOD]
    /** Error Msg : Sales date cannot be obtained.  **/
    public static final String NSAM0178E = "NSAM0178E";

    /** column name : SVC_MACH_MSTR */
    public static final String IWR_COND_CD = "IWR_COND_CD";

    /** table name : SVC_MACH_MSTR_TRK */
    public static final String TBL_NM_SVC_MACH_MSTR_TRK = "SVC_MACH_MSTR_TRK";
// END 2016/11/09 N.Arai [QC#15829, MOD]
}
