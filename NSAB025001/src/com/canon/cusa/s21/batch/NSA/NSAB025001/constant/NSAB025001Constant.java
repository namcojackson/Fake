/**
 * <pre>
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB025001.constant;

/**
 *<pre>
 * Auto Registers & Deregister unit to UGW
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/08   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/09/01   Hitachi         T.Tomita        Update          QC#14071
 *</pre>
 */
public class NSAB025001Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSAB0250";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** [@] is not registered.(@) */
    public static final String NSAM0069E = "NSAM0069E";

    /** Global Company Code is mandatory. */
    public static final String NSAM0177E = "NSAM0177E";

    /** Sales date cannot be obtained. */
    public static final String NSAM0178E = "NSAM0178E";

    /** Column name : GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column name : SVC_MACH_MSTR_PK */
    public static final String COL_SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** Column name : IWR_RGTN_RQST_FLG */
    public static final String COL_IWR_RGTN_RQST_FLG = "IWR_RGTN_RQST_FLG";

    /** Column name : IWR_DE_RGTN_RQST_FLG */
    public static final String COL_IWR_DE_RGTN_RQST_FLG = "IWR_DE_RGTN_RQST_FLG";

    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = BIZ_APP_ID;

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

    // START 2016/09/01 T.Tomita [QC#14071, ADD]
    /** Constant key : NSAB0250_WARN_MAIL_SEND_FLG */
    public static final String CONST_KEY_NSAB0250_WARN_MAIL_SEND_FLG = "NSAB0250_WARN_MAIL_SEND_FLG";
    // END 2016/09/01 T.Tomita [QC#14071, ADD]
}
