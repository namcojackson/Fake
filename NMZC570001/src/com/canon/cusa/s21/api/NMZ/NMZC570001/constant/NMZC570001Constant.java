/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC570001.constant;

/**
 *<pre>
 * Prospect Review Inbound API.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/21/2016   Hitachi         Y.Takeno        Create          N/A
 * 12/19/2016   Fujitsu         Y.Kanefusa      Update          QC#16681
 * 08/22/2016   Fujitsu         R.Nakamura      Update          QC#20658
 *</pre>
 */
public class NMZC570001Constant {

    /** 
     * Global Company Code
     */
    public static final String GLBL_CMPY_CD = "ADB";

    /**
     * Date Format : MM/dd/yyyy HH:mm:ss.SSS
     */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

    /**
     * Mail Group From
     */
    public static final String MAIL_GROUP_FROM = "From";

    /**
     * Mail Group ID From
     */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /**
     * Mail Group To
     */
    public static final String MAIL_GROUP_TO = "To";

    /**
     * Mail Group ID To
     */
    public static final  String MAIL_GROUP_ID_TO = "NMAB5700";

    /**
     * Mail Template ID
     */
    public static final  String MAIL_TEMPLATE_ID_01 = "NMAB5700M001";

    /**
     * VAR_CHAR_CONST Name : NMZC5700_MAIL_ROW_CNT
     */
    public static final String CONST_NAME_NMZC5700_MAIL_ROW_CNT = "NMZC5700_MAIL_ROW_CNT";

    /**
     * Default Mail Row Count
     */
    public static final int DEFAULT_MAIL_ROW_COUNT = 100;

    /**
     * Mail Max Length (${message})
     */
    public static final int MAIL_ITEM_MAX_LENGTH_MESSSAGE = 500;

    /**
     * Mail item : ${ApiId}
     */
    public static final String MAIL_ITEM_API_ID = "ApiId";

    /**
     * Mail item : ${FunctionName}
     */
    public static final String MAIL_ITEM_FUNCTION_NAME = "FunctionName";

    /**
     * Mail item : ${TableName}
     */
    public static final String MAIL_ITEM_TABLE_NAME = "TableName";

    /**
     * Mail item : ${NormalRecordCount}
     */
    public static final String MAIL_ITEM_NORMAL_CNT = "NormalRecordCount";

    /**
     * Mail item : ${ErrorRecordCount}
     */
    public static final String MAIL_ITEM_ERROR_CNT = "ErrorRecordCount";

    /**
     * Mail item : ${SkipRecordCount}
     */
    public static final String MAIL_ITEM_SKIP_CNT = "SkipRecordCount";

    /**
     * Mail item : ${TotalRecordCount}
     */
    public static final String MAIL_ITEM_TOTAL_CNT = "TotalRecordCount";

    /**
     * Mail item : ${errDate}
     */
    public static final String MAIL_ITEM_ERR_DATE = "errDate";

    /**
     * Mail item : ${message}
     */
    public static final String MAIL_ITEM_MESSAGE = "message";

    /**
     * Mail item value : ${ApiId}
     */
    public static final String MAIL_ITEM_VALUE_API_ID = "NMZC570001";

    /**
     * Mail item value : ${FunctionName}
     */
    public static final String MAIL_ITEM_VALUE_FUNCTION_NAME = "Prospect Review Inbound";

    /**
     * Mail item value : ${TableName}
     */
    public static final String MAIL_ITEM_VALUE_TABLE_NAME = "DS_ACCT_RVW_PROS";

    /**
     * Mail item format : ${message}
     */
    public static final String MAIL_ITEM_FORMAT_MESSAGE = "Prospect# %s / %s";

    // Add Start 2017/08/22 QC#20658
    /**
     * Mail item format : ${message}
     */
    public static final String LINE_BIZ_TP_NM_LFS = "LFS Prospect";

    /**
     * Mail item format : ${message}
     */
    public static final String LINE_BIZ_TP_NM_PPS = "PPS Prospect";

    /**
     * Mail item format : ${message}
     */
    public static final String LINE_BIZ_TP_NM_ESS = "Prospect";
    // Add End 2017/08/22 QC#20658

    /**
     *  [@] field is mandatory. 
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     *  The field of [@] is not input.
     */
    public static final String ZZZM9007E = "ZZZM9007E";

    /**
     * E-mail address <Type: [@], Group: [@], Key: [@]>  cannot be obtained.
     */
    public static final String NMAM8028E = "NMAM8028E";

    /**
     * An valid e-mail address <Type: [@], Group: [@], Key: [@]> is not registered.
     */
    public static final String NMAM8029E = "NMAM8029E";

    /**
     * Multiple Sender (From:) e-mail addresses <Group: [@], Key: [@]> are registered.
     */
    public static final String NMAM8030E = "NMAM8030E";

    /**
     * The e-mail template <template ID: [@]> cannot be obtained.
     */
    public static final String NMAM8031E = "NMAM8031E";

    /**
     * Error occurred in item registration API.
     */
    public static final String NMAM8304E = "NMAM8304E";

    /**
     * Registration process is not finished normally.
     */
    public static final String NMAM8570E = "NMAM8570E";

    /** filler string millisecond part*/
    public static final String FILLER_MSEC_STRING  = "000";

    /** time-stamp length */
    public static final int LENGTH_TS = 14;

    /** Account Source Text : SFDC */
    public static final String CONST_ACCT_SRC_TXT = "SFDC"; // QC#16681 2016/12/19 Add
}
