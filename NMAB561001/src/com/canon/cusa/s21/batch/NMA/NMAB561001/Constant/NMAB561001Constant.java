/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB561001.Constant;

/**
 * <pre>
 * Customer Effective Check Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/17   Fujitsu         K.Minamide      Create          N/A
 * 2016/05/25   SRAA            Y.Chen          Update          QC#8590
 * 2019/05/23   Fujitsu         Hd.Sugawara     Update          QC#50101
 * 2022/04/28   CITS            S.go            Update          QC#59983
 *</pre>
 */
public class NMAB561001Constant {
    /** DEFAULT_COMMIT_UNIT */
    public static final int DEFAULT_COMMIT_UNIT = 1000;

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** Data insert failure. (table name is [@]) */
    public static final String MMAM0003E = "MMAM0003E";

    /** Data update failure. (table name is [@]) */
    public static final String MMAM0004E = "MMAM0004E";

    /** mode cust account */
    public static final String MODE_CUST_ACCT = "01";

    /** Max Length of Location Name */
    public static final int MAX_LEN_LOC_NM = 60;

    // Add Start 2019/05/23 QC#50101
    /** MAX_DT */
    public static final String MAX_DT = "99991231";
    // Add End 2019/05/23 QC#50101

    // 2022/04/28 QC#59983 Add Start
    /** Mail Type : From */
    public static final String MAIL_TYPE_FROM = "From";

    /** Mail Group Id Key: From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** Mail Key From : DS */
    public static final String MAIL_KEY_FROM = "NMA";

    /** Mail Type : To */
    public static final String MAIL_TYPE_TO = "To";

    /** Mail Group Id Key: To */
    public static final String MAIL_GROUP_ID_TO = "NMAB5610";

    /** Mail Group id for To. */
    public static final String MAIL_TEMPLATE_ID = "NMAB5610M001";

    /** Mail Batch Program ID */
    public static final String BATCH_PROGRAM_ID = "NMAB5610";

    /** Mail Batch Program ID */
    public static final String BATCH_PROGRAM_NAME = "Customer Effective Check Batch";

    /** Mail Parameter : ErrorInfo */
    public static final String MAIL_FIELD_ERR_MSG = "ErrorInfo";

    /** Mail Parameter : batchId */
    public static final String MAIL_FIELD_BAT_ID = "batchId";

    /** Mail Parameter : batchNm */
    public static final String MAIL_FIELD_BAT_NM = "batchNm";

    /** Mail Character Set */
    public static final String MAIL_CHARSET = "ISO-8859-1";

    /** Mail Message New Line */
    public static final String CRLF = "\r\n";

    /** Mail Message Hyphen */
    public static final String HYPHEN = "-";
    // 2022/04/28 QC#59983 Add End
}
