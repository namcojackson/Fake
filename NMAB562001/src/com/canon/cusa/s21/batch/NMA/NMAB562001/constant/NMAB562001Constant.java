/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB562001.constant;

/**
 * <pre>
 * CUSA Retail Location Cross Reference Sync Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 06/27/2016   Hitachi         Y.Osawa          CREATE          NEW
 *</pre>
 */
public class NMAB562001Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NMAB5620";

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = "Global Company Code";

    /** Acquisition Number */
    public static final String AQU_NUM = "Acquisition Number";

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** DEFAULT_COMMIT_SIZE */
    public static final int DEFAULT_COMMIT_SIZE = 1000;

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** @(@) format is incorrect. [@] */
    public static final String NMAM8612E = "NMAM8612E";

    /** [@] already exists in [@] */
    public static final String NMAM8613E = "NMAM8613E";

    /** Error Massage : CRLF */
    public static final String ERR_MSG_CRLF = "\r\n";

    /** Error Massage : Space */
    public static final String ERR_MSG_SPACE = "    ";

    /** hyphen */
    public static final String HYPHEN = "-";

    /** Mail Group ID (From) */
    public static final String MAIL_GRP_ID_FROM = "FROM0003";

    /** Set ID: Mail Template ID */
    public static final String SET_MAIL_TEMPLATE_ID = "NMAB5620M001";

    /** Set Fixed Value: 3 */
    public static final int CHK_LIST_LENGTH = 3;

    /** Set Fixed Value: 6 */
    public static final int CHK_LENGTH = 6;

    /** Set Fixed Value: 3 */
    public static final int BEF_DAY = 3;
}
