/**
 * <pre>
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 * </pre>
 */

package com.canon.cusa.s21.batch.NSA.NSAB075001.constant;

/**
 *<pre>
 * Sub Contract Creation Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/05   Hitachi       K.Yamada             Create          N/A
 * 2017/02/24   Hitachi       Y.Takeno             Update          QC#14675-1
 * 2017/10/20   Hitachi       U.Kim                Update          QC#21840
 *</pre>
 */

public class NSAB075001Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSAB0750";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** [@] is not registered.(@) */
    public static final String NSAM0069E = "NSAM0069E";

    /** Global Company Code is mandatory. */
    public static final String NSAM0177E = "NSAM0177E";

    /** Sales date cannot be obtained. */
    public static final String NSAM0178E = "NSAM0178E";

    /** Failed to register to the @ table. */
    public static final String NSZM0398E = "NSZM0398E";

    /** Table name : DS_CONTR_DTL */
    public static final String DS_CONTR_DTL = "DS_CONTR_DTL";

    /** Column name : GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column name : DS_CONTR_DTL_PK */
    public static final String COL_DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";

    /** Column name : ITT_NUMBER */
    public static final String ITT_NUMBER = "ITT_NUMBER";

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

    /** Column meter type : BLACK  */
    public static final String METER_TYPE_BLACK = "BLACK";

    /** Column meter type : COLOR  */
    public static final String METER_TYPE_COLOR = "COLOR";

    /** Parameter SubContract Create ProcCode Default */
    public static final String SUB_CONTR_CRAT_PROC_CD_DEFAULT = "0";

    /** Parameter SubContract Create ProcCode Updated */
    // START 2017/09/16 [QC#21153, DEL]
    // public static final String SUB_CONTR_CRAT_PROC_CD_UPDATED = "9";
    // END   2017/09/16 [QC#21153, DEL]

    // START 2017/02/24 [QC#16285-1, ADD]
    /** ITT_STATUS : closed */
    public static final String ITT_HEADER_ITT_STATUS_CLOSED = "HEADER_STATUS_03";

    /** MAX_DATE : 29991231 */
    public static final String MAX_DATE = "29991231";
    // END  2017/02/24 [QC#16285-1, ADD]

    // START 2017/10/20 U.Kim [QC#21840, ADD]
    /** CANON_E580_SERVICE_PRICING_TBL.CONTRACT_TYPE : Inclusive */
    public static final String CANON_E580_SERVICE_PRICING_ICV = "CANON_E580_SERVICE_PRICING_ICV";

    // END 2017/10/20 U.Kim [QC#21840, ADD]
}
