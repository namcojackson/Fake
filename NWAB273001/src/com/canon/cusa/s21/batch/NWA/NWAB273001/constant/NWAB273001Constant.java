/**
 * <Pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB273001.constant;

/**
 * <pre>
 * eCheck Capture Batch.
 * This class defines the constant used in the batch application
 * program of BusinessID NWAB273001. 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/06/2023   Hitachi         M.Hashino       Create          QC#55645
 * 
 *</pre>
 */

public class NWAB273001Constant {

    /**
     * Message ID
     */
    public static enum MSG_ID {
        /** [@] is mandatory. */
        ZZZM9025E,
        /** @ ended abnormally. @ */
        NWAM0447E,
        /** The mail template cannot be acquired. <Template ID: [@]> */
        NWAM0319E,
        /** It failed to register Mail. */
        NWAM0268E,
        /**
         * Could not retrieve MailGroupAddress. MAIL_GROUP_ID_TO : [@]
         * MAIL_KEY1 : [@]
         */
        NWAM0516E
    }

    /** Program ID */
    public static final String PROGRAM_ID = "NWAB273001";

    /** Program Name */
    public static final String PROGRAM_NM = "eCheck Capture Batch";

    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_ID = "NWAB2730M001";

    /** STR_CRLF */
    public static final String CRLF = "\r\n";

    /** Invoice number max length */
    public static final int INV_NUM_LEN = 13;

    /** INV_DT_LEN */
    public static final int INV_DT_LEN = 10;

    /** INV_TOT_DEAL_NET_AMT_LEN */
    public static final int INV_TOT_DEAL_NET_AMT_LEN = 20;

    /** Process Result Code (Success) */
    public static final String PROC_RES_CD_SUCCESS = "1";

    /** Process Result Code (Error) */
    public static final String PROC_RES_CD_ERROR = "9";

    /** email content header label */
    public static final String CONTENT_HDR_LBL = "Invoice#         Invoice Date      Deal Net Amount ";

    /** email content header div line */
    public static final String CONTENT_HDR_DIV_LINE = "---------------------------------------------------";

    /** one white space */
    public static final String ONE_SPACE = " ";

    /** 4 white space */
    public static final String SEPT_SPACE = "    ";

    /** Mail Group Id FROM0001 */
    public static final String MAIL_GROUP_ID_FROM = "FROM0001";

    /** Mail Key Id Z */
    public static final String MAIL_KEY_FROM = "Z";

    /** Mail Key Id To */
    public static final String MAIL_KEY_TO = "To";

    /** Mail Group Id Key: To */
    public static final String MAIL_GROUP_ID_TO = "NWAB2730";

    /** Mail Template ID M001 */
    public static final String MAIL_TEMPLATE_ID_M001 = "NWAB2730M001";

    /** template KEY Sales Date */
    public static final String MAIL_TEMPLATE_KEY_SLS_DT = "slsDt";

    /** template KEY CONTENT */
    public static final String MAIL_TEMPLATE_KEY_CONTENT = "content";

    /** Mail Date Format MM/dd/yyyy */
    public static final String MAIL_OUT_TYPE_MM_DD_YYYY = "MM/dd/yyyy";

    /** The payment process succeeded, but the DB registration process failed. Please contact IT support. Otherwise, double payment could occur.  */
    public static final String NWZM2320E = "NWZM2320E";

}
