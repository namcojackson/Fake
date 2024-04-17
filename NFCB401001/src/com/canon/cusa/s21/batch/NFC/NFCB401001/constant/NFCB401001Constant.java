/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB401001.constant;

/**
 * <pre>
 * Compensation IF(LFS)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/08   Hitachi         A.Kohinata      Create          N/A
 * 2018/08/20   Fujitsu         W.Honda         Update          S21_NA#25938
 * 2018/09/25   Fujitsu         T.Noguchi       Update          QC#28231
 * 2020/12/01   CITS            K.Ogino         Update          QC#58045
 * </pre>
 */

public class NFCB401001Constant {

    /** @ cannot be obtained. */
    public static final String NFCM0531E = "NFCM0531E";

    /** VAR_CHAR_CONST_KEY: SEND_COMP_CPLT_CD */
    public static final String VAR_CHAR_CONST_KEY_SEND_COMP_CPLT_CD = "SEND_COMP_CPLT_CD";

    /** VAR_CHAR_CONST_KEY: COMP_LFS_BIZ_TP_CD */
    public static final String VAR_CHAR_CONST_KEY_COMP_LFS_BIZ_TP_CD = "COMP_LFS_BIZ_TP_CD";

    // Add Start 2018/08/20 S21_NA#25938
    /** VAR_CHAR_CONST_KEY: COMP_IS_BIZ_TP_CD */
    public static final String VAR_CHAR_CONST_KEY_COMP_IS_BIZ_TP_CD = "COMP_IS_BIZ_TP_CD";

    /** STRING PERCENT */
    public static final String PERCENT = "%";
    // Add End 2018/08/20 S21_NA#25938

    /** VAR_CHAR_CONST_KEY: WRITER_ORG_CD */
    public static final String VAR_CHAR_CONST_KEY_WRITER_ORG_CD = "WRITER_ORG_CD";

    /** MAX_FETCH_SIZE */
    public static final int MAX_FETCH_SIZE = 1000;

    /** MAX_COMMIT_NUMBER */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** Mode: Normal */
    public static final String MODE_NORMAL = "N";

    /** Mode: Accrual */
    public static final String MODE_ACCRUAL = "A";

    /** COMP_PROC_STS: COMPLETE */
    public static final String COMP_PROC_STS_COMPLETE = "1";

    /** STR_01 */
    public static final String STR_01 = "01";

    /** STR_02 */
    public static final String STR_02 = "02";

    /** STR_03 */
    public static final String STR_03 = "03";

    /** DEF_SER_NUM */
    public static final String DEF_SER_NUM = "00000000000000000000";

    /** LEVEL_LINE */
    public static final String LEVEL_LINE = "LINE";

    // Add Start 2018/01/29 S21_NA#22002
    /** COMP_DIV_CD: TDS */
    public static final String COMP_DIV_CD_TDS = "TDS";
    // Add End 2018/01/29 S21_NA#22002

    // 2018/09/25 QC#28231 Add Start
    /** Statement Id : getCompInfoNormal */
    public static final String SQL_STATEMENT_GET_COMP_INFO_NORMAL = "getCompInfoNormal";

    /** Statement Id : getCompInfoNormalRMA */
    public static final String SQL_STATEMENT_GET_COMP_INFO_NORMAL_RMA = "getCompInfoNormalRMA";

    /** Statement Id : getCompInfoAccrual */
    public static final String SQL_STATEMENT_GET_COMP_INFO_ACCRUAL = "getCompInfoAccrual";

    /** Statement Id : getCompInfoAccrualRMA */
    public static final String SQL_STATEMENT_GET_COMP_INFO_ACCRUAL_RMA = "getCompInfoAccrualRMA";
    // 2018/09/25 QC#28231 Add End

    // QC#58045
    /** VAR_CHAR_CONST_KEY: NFCB4010_LFS_COMM_MDSE_ITEM */
    public static final String VAR_CHAR_CONST_KEY_NFCB4010_LFS_COMM_MDSE_ITEM = "NFCB4010_LFS_COMM_MDSE_ITEM";
}
