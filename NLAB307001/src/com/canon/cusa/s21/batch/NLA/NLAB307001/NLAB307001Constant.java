/*
 *  <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.batch.NLA.NLAB307001;

/**
 *<pre>
 * RMA Tracking Notification Batch
 *</pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/05/2016   CITS            M.Ito           Create          N/A
 *</pre>
 */
public class NLAB307001Constant {

    /** Program ID for Log */
    public static final String PROGRAM_ID = " ## NLAB307001 ## ";

    /** Batch ID */
    public static final String BATCH_ID = "NLAB307001";

    /** Message Type: E */
    public static final String MSG_TYPE_ERROR = "E";

    /** Global Company Code is mandatory. */
    public static final String NASM0010E = "NASM0010E";

    /** "Data Global Company Code" does not exist in the Master. */
    public static final String NWZM0650E = "NWZM0650E";

    /** Batch Operation Date cannot be obtained. */
    public static final String NDMM0016E = "NDMM0016E";

    /** Information Message() */
    public static final String ZZBM0009I = "ZZBM0009I";

    /** It failed to register an  email.   */
    public static final String NLEM0004E = "NLEM0004E";

    /** It failed to get @. @ */
    public static final String NLEM0001E = "NLEM0001E";

    /** Date Format */
    public static final String DT_FMT = "YYYYMMDD";

    /** DS Condition Constant Code : 1 */
    public static final String DS_COND_CONST_CD_1 = "1";

    /** DS Condition Constant Code : 2 */
    public static final String DS_COND_CONST_CD_2 = "2";

    /** DS Condition Constant Code : 3 */
    public static final String DS_COND_CONST_CD_3 = "3";

    /** DS Condition Constant Group ID : NLAB3070_AUTO_UPD */
    public static final String DS_COND_CONST_GRP_ID = "NLAB3070_AUTO_UPD";

    /** Message Key : batchId */
    public static final String MSG_KEY_BATCH_ID = "batchId";

    /** Message Key : message */
    public static final String MSG_KEY_MESSAGE = "message";

    /** Message Key : errDate */
    public static final String MSG_KEY_ERR_DT = "errDate";

    /** Message Key : status */
    public static final String MSG_KEY_STS = "status";

    /** Error Group ID */
    public static final String ERR_GRP_ID = "NLAB307000";

    /** Error Mail Template ID */
    public static final String ERR_TMP = "NLAB3070M000";

    /** Error Date Format */
    public static final String ERR_DT_FMT = "MM/dd/yyyy HH:mm:ss.SSS";

    /** Line Feed Code */
    public static final String LINE_FEED_CODE = "\r\n";

    /** Tab */
    public static final String TAB = "    ";

    /** Mail Group ID From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Message Parameter : Mail Group */
    public static final String MSG_PARAM_GRP = "Mail Group";

    /** Message Parameter : Mail Address */
    public static final String MSG_PARAM_ADDR = "Mail Address";

    /** Message Parameter : Mail Template */
    public static final String MSG_PARAM_TMP = "Mail Template";

    /** Message Parameter : RWS# */
    public static final String MSG_PARAM_RWS = "RWS#";

    /** Message Parameter : Line# */
    public static final String MSG_PARAM_LINE = "Line#";

    /** Space */
    public static final String SPACE = " ";

    /** Mail Language */
    public static final String ML_LANG = "en";

    /** Mail Language Code */
    public static final String ML_LANG_CD = "ISO-8859-1";

}
