/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB403001.constant;

/**
 * <pre>
 * Copensation IF(IS)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/13   Hitachi         Y.Zhang         Create          N/A
 * 2018/09/25   Fujitsu         T.Noguchi       Update          QC#28231
 * 2019/09/05   Fujitsu         Hd.Sugawara     Update          QC#51704
 * </pre>
 */

public class NFCB403001Constant {

    /** @ cannot be obtained. */
    public static final String NFCM0531E = "NFCM0531E";

    /** VAR_CHAR_CONST_KEY: SEND_COMP_CPLT_CD */
    public static final String VAR_CHAR_CONST_KEY_SEND_COMP_CPLT_CD = "SEND_COMP_CPLT_CD";

    /** VAR_CHAR_CONST_KEY: COMP_PPS_BIZ_TP_CD */
    public static final String VAR_CHAR_CONST_KEY_COMP_IS_BIZ_TP_CD = "COMP_IS_BIZ_TP_CD";

    /** MAX_FETCH_SIZE */
    public static final int MAX_FETCH_SIZE = 1000;

    /** MAX_COMMIT_NUMBER */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** COMP_PROC_STS: COMPLETE */
    public static final String COMP_PROC_STS_COMPLETE = "1";
    /**
     * Const Value:WMS_MTR_READ_PAST_DAY_AOT
     */
    public static final String CONST_PAST_COMP_DAYS_AOT = "PAST_COMP_DAYS_AOT";
    
    // 2018/02/15 S21_NA#23230 Add Start
    /** STRING PERCENT */
    public static final String PERCENT = "%";
    // 2018/02/15 S21_NA#23230 Add End

    // 2018/09/11 QC#28231 Add Start
    /** Statement Id : getCompInfo */
    public static final String SQL_STATEMENT_GET_COMP_INFO = "getCompInfo";

    /** Statement Id : getCompInfoRMA */
    public static final String SQL_STATEMENT_GET_COMP_INFO_RMA = "getCompInfoRMA";
    // 2018/09/11 QC#28231 Add End

    // Add Start 2019/09/05 QC#51704
    /** SUBSTR_DT_LNGTH */
    public static final int SUBSTR_DT_LNGTH = 8;
    // Add End 2019/09/05 QC#51704

    // 2020/04/23 QC#56638 Add Start
    /** Error Message (The field of [@] is illegal.) **/
    public static final String ZZMM0007E = "ZZMM0007E";

    /** CRLF */
    public static final String CRLF = "\r\n";
 // 2020/04/23 QC#56638 Add End
}
