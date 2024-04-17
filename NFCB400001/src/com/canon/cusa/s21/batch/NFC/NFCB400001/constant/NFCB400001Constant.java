/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB400001.constant;

/**
 * <pre>
 * Compensation IF(PPS)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/08   Hitachi         A.Kohinata      Create          N/A
 * </pre>
 */

public class NFCB400001Constant {

    /** @ cannot be obtained. */
    public static final String NFCM0531E = "NFCM0531E";

    /** VAR_CHAR_CONST_KEY: SEND_COMP_CPLT_CD */
    public static final String VAR_CHAR_CONST_KEY_SEND_COMP_CPLT_CD = "SEND_COMP_CPLT_CD";

    /** VAR_CHAR_CONST_KEY: COMP_PPS_BIZ_TP_CD */
    public static final String VAR_CHAR_CONST_KEY_COMP_PPS_BIZ_TP_CD = "COMP_PPS_BIZ_TP_CD";

    /** MAX_FETCH_SIZE */
    public static final int MAX_FETCH_SIZE = 1000;

    /** MAX_COMMIT_NUMBER */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** COMP_PROC_STS: COMPLETE */
    public static final String COMP_PROC_STS_COMPLETE = "1";

    /** STR_01 */
    public static final String STR_01 = "01";

    /** STR_02 */
    public static final String STR_02 = "02";

    /** STR_03 */
    public static final String STR_03 = "03";

    /** STR_04 */
    public static final String STR_04 = "04";

    /** STR_06 */
    public static final String STR_06 = "06";

    /** STR_07 */
    public static final String STR_07 = "07";

    /** STR_09 */
    public static final String STR_09 = "09";

    /** STR_10 */
    public static final String STR_10 = "10";

    /** STR_12 */
    public static final String STR_12 = "12";

    /** DATE_FORMAT */
    public static final String DATE_FORMAT = "YYYYMMDD";

    /** DEF_SER_NUM */
    public static final String DEF_SER_NUM = "00000000000000000000";

    // 2018/09/25 QC#28231 Add Start
    /** Statement Id : getCompInfo */
    public static final String SQL_STATEMENT_GET_COMP_INFO = "getCompInfo";

    /** Statement Id : getCompInfoRMA */
    public static final String SQL_STATEMENT_GET_COMP_INFO_RMA = "getCompInfoRMA";
    // 2018/09/25 QC#28231 Add End

}
