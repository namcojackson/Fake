/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1810.constant;

/**
 *<pre>
 * NWAL1810Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         S.Ohki          Create          N/A
 * 2016/04/08   Fujitsu         M.Yamada        Update          QC#6731
 * 2016/04/20   Fujitsu         M.Yamada        Update          QC#7245
 * 2022/10/20   Hitachi         H.Watanabe      Update          QC#60258
 *</pre>
 */
public class NWAL1810Constant {

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** [@] is not found. */
    public static final String AZZM0001W = "AZZM0001W";

    /** No search result was found. */
    public static final String NWAM0006I = "NWAM0006I";

    /** Tab Summary. */
    public static final String TAB_SUMMARY = "TAB_Summary";

    /** Tab Detail. */
    public static final String TAB_DETAIL = "TAB_Detail";

    /** Level Code All. */
    public static final String LVL_CD_ALL = "1";

    /** Level Code Configuration. */
    public static final String LVL_CD_CONF = "2";

    /** Level Code Line. */
    public static final String LVL_CD_LINE = "3";

    /** List header Name Summary. */
    public static final String LIST_HEADER_NAME_SUMMARY = "AHEAD";

    /** List header Name Detail. */
    public static final String LIST_HEADER_NAME_DETAIL = "BHEAD";

    /** Source Id Order. */
    public static final String SOURCE_ID_ORDER = "1";

    /** Source ID Quote. */
    public static final String SOURCE_ID_QUOTE = "2";

    /** Source Id Schedule. */
    public static final String SOURCE_ID_SCHEDULE = "3";

    /** Period */
    public static final String PERIOD = ".";

    /** Split Period */
    public static final String SPLIT_PERIOD = "\\.";

    /** Line break. */
    public static final String LINE_BREAK = "\n";

    /** Space. */
    public static final String SPACE = " ";

    /** SQL String OR. */
    public static final String SQL_OR = "OR";

    /** SQL String AND. */
    public static final String SQL_AND = "AND";

    /** SQL String Inequality bond. */
    public static final String SQL_INEQUALITY_BOND = "<>";

    /** SQL String IS NULL */
    public static final String SQL_IS_NULL = "IS NULL";

    /** SQL String IS NOT NULL */
    public static final String SQL_IS_NOT_NULL = "IS NOT NULL";

    /** SQL Table Alias Name Before 01. */
    public static final String SQL_TABLE_ALIAS_NAME_BEFORE_01 = "BF1";

    /** SQL Table Alias Name Before 02. */
    public static final String SQL_TABLE_ALIAS_NAME_BEFORE_02 = "BF2";

    /** SQL Table Alias Name After 01. */
    public static final String SQL_TABLE_ALIAS_NAME_AFTER_01 = "AF1";

    /** SQL Table Alias Name After 02. */
    public static final String SQL_TABLE_ALIAS_NAME_AFTER_02 = "AF2";

    /** percent. */
    public static final String PERCENT = "%";

    // 2022/10/20 QC#60258 Add Start
    /** DateTimeFormatFromDt */
    public static final String DATE_TIME_FORMAT_FROM_DT = "000000000";

    /** DateTimeFormatThruDt */
    public static final String DATE_TIME_FORMAT_THRU_DT = "235959999";
    // 2022/10/20 QC#60258 Add Start

    /** Process ID OM. */
    public static final String PROC_ID_OM = "OM";

    /** SQL Output Flag ON. */
    public static final String SQL_OUTPUT_FLG_ON = "Y";

    /** SQL Output Flag OFF. */
    public static final String SQL_OUTPUT_FLG_OFF = "N";

    /** SQL UNION Output Flag ON. */
    public static final String SQL_UNION_OUTPUT_FLG_ON = "Y";

    /** SQL UNION Output Flag OFF. */
    public static final String SQL_UNION_OUTPUT_FLG_OFF = "N";

    // 2022/10/20 QC#60258 Add Start
    /** SQL LINE SEARCH TYPE FLAG ON. */
    public static final String SQL_LINE_SEARCH_TYPE_FLAG_ON = "Y";

    /** SQL LINE SEARCH TYPE FLAG OFF. */
    public static final String SQL_LINE_SEARCH_TYPE_FLAG_OFF = "N";

    /** Default Code Summary. */
    public static final String DEF_CD_SUMMARY = "S";
    // 2022/10/20 QC#60258 Add End

    /** SQL text Parameter Number 0. */
    public static final int SQL_TXT_PAR_NUM_00 = 0;

    /** SQL text Parameter Number 1. */
    public static final int SQL_TXT_PAR_NUM_01 = 1;

    /** SQL text Parameter Number 2. */
    public static final int SQL_TXT_PAR_NUM_02 = 2;

    /** SQL text Parameter Number 3. */
    public static final int SQL_TXT_PAR_NUM_03 = 3;

    /** SQL Map Parameter List Name CPO */
    public static final String LIST_NAME_CPO = "cpoAttrList";

    /** SQL Map Parameter List Name DS_CPO_SLS_CR */
    public static final String LIST_NAME_CPO_SLS = "cpoSlsAttrList";

    /** SQL Map Parameter List Name DS_CPO_CONFIG */
    public static final String LIST_NAME_DS_CPO_CONFIG = "dsCpoConfAttrList";

    /** SQL Map Parameter List Name DS_CPO_SLS_CR */
    public static final String LIST_NAME_DS_CPO_SLS_CR = "dsCpoSlsCrAttrList";

    /** SQL Map Parameter List Name CPO_DTL */
    public static final String LIST_NAME_CPO_DTL = "cpoDtlAttrList";

    /** SQL Map Parameter List Name DS_CPO_RTRN_DTL */
    public static final String LIST_NAME_DS_CPO_RTRN_DTL = "dsCpoRtrnDtlAttrList";

    /** SQL Map Parameter List Name ORD_PRC_CALC_BASE */
    public static final String LIST_NAME_ORD_PRC_CALC_BASE = "ordPrcCalcBaseAttrList";

    /** SQL Map Parameter List Name CPO_RTRN_CALC_BASE */
    public static final String LIST_NAME_CPO_RTRN_CALC_BASE = "cpoRtrnCalcBaseAttrList";

    /** SQL Map Parameter List Name DS_CONTR */
    public static final String LIST_NAME_DS_CONTR = "dsContrAttrList";
    /** SQL Map Parameter List Name SVC_MEMO */
    public static final String LIST_NAME_SVC_MEMO = "svcMemoAttrList";
    /** SQL Map Parameter List Name DS_CONTR_DTL */
    public static final String LIST_NAME_DS_CONTR_DTL = "dsContrDtlAttrList";
    /** SQL Map Parameter List Name DS_CONTR_ADDL_CHRG */
    public static final String LIST_NAME_DS_CONTR_ADDL_CHRG = "dsContrAddlChrgAttrList";

    /** SQL Map Parameter List Name SPLY_QUOTE */
    public static final String LIST_NAME_SPLY_QUOTE = "splyQuoteAttrList";

    /** SQL Map Parameter List Name SPLY_QUOTE_SLS_CR */
    public static final String LIST_NAME_SPLY_QUOTE_SLS_CR = "splyQuoteSlsCrAttrList";

    /** SQL Map Parameter List Name SPLY_QUOTE_DTL */
    public static final String LIST_NAME_SPLY_QUOTE_DTL = "splyQuoteDtlAttrList";

    /** SQL Map Parameter List Name SPLY_QUOTE_CALC_BASE */
    public static final String LIST_NAME_SPLY_QUOTE_CALC_BASE = "splyQuoteCalcBaseAttrList";

    //--
    /** SQL Map Parameter List Name DB_NAME_SPLY_QUOTE_REC */
    public static final String DB_NAME_SPLY_QUOTE_REC = "SPLY_QUOTE_REC";

    /** SQL Map Parameter List Name DB_NAME_SPLY_QUOTE_SLS_CR_REC */
    public static final String DB_NAME_SPLY_QUOTE_SLS_CR_REC = "SPLY_QUOTE_SLS_CR_REC";

    /** SQL Map Parameter List Name DB_NAME_SPLY_QUOTE_DTL_REC */
    public static final String DB_NAME_SPLY_QUOTE_DTL_REC = "SPLY_QUOTE_DTL_REC";

    /** SQL Map Parameter List Name DB_NAME_SPLY_QUOTE_CALC_BASE_REC */
    public static final String DB_NAME_SPLY_QUOTE_CALC_BASE_REC = "SPLY_QUOTE_CALC_BASE_REC";

    //--
    /** SQL Map Parameter List Name DB_NAME_SCHD_AGMT_REC */
    public static final String DB_NAME_SCHD_AGMT_REC = "SCHD_AGMT_REC";

    /** SQL Map Parameter List Name DB_NAME_SCHD_AGMT_SLS_CR_REC */
    public static final String DB_NAME_SCHD_AGMT_SLS_CR_REC = "SCHD_AGMT_SLS_CR_REC";

    /** SQL Map Parameter List Name DB_NAME_SCHD_AGMT_LINE_REC */
    public static final String DB_NAME_SCHD_AGMT_LINE_REC = "SCHD_AGMT_LINE_REC";

    /** SQL Map Parameter List Name DB_NAME_SCHD_AGMT_CALC_BASE_REC */
    public static final String DB_NAME_SCHD_AGMT_CALC_BASE_REC = "SCHD_AGMT_CALC_BASE_REC";

    /** SQL Map Parameter List Name DB_NAME_SCHD_AGMT_PLN_REC */
    public static final String DB_NAME_SCHD_AGMT_PLN_REC = "SCHD_AGMT_PLN_REC";

    //--

    /** SQL Map Parameter List Name SCHD_AGMT */
    public static final String LIST_NAME_SCHD_AGMT = "schdAgmtAttrList";

    /** SQL Map Parameter List Name SCHD_AGMT_SLS_CR */
    public static final String LIST_NAME_SCHD_AGMT_SLS_CR = "schdAgmtSlsCrAttrList";

    /** SQL Map Parameter List Name SCHD_AGMT_LINE */
    public static final String LIST_NAME_SCHD_AGMT_LINE = "schdAgmtLineAttrList";

    /** SQL Map Parameter List Name SCHD_AGMT_PRC_CALC */
    public static final String LIST_NAME_SCHD_AGMT_PRC_CALC = "schdAgmtPrcCalcAttrList";

    /** SQL Map Parameter List Name SCHD_AGMT_PLN */
    public static final String LIST_NAME_SCHD_AGMT_PLN = "schdAgmtPlnAttrList";

    //-------------------
    /** YYYYMMDDHHMISS */
    public static final String YYYYMMDDHHMISS = "YYYYMMDDHHMISS";

    /** LINE_STR_PTN (e.g. 001.001) */
    public static final String LINE_STR_PTN = "\\d{3}\\.\\d{3}";

    /** LINE_STR_PTN (e.g. 001.001) */
    public static final String SQL_LINE_STR_PTN = "^\\d{3}\\.\\d{3}$";

    /** SQL_LINE_STR_FLT_PTN (e.g. 1.FLT) */
    public static final String SQL_LINE_STR_FLT_PTN = "^\\d+\\.FLT$";

    /** Document Type Code : OM */
    public static final String DOC_TP_CD_OM = "OM";

    /** Document Type Code : RT */
    public static final String DOC_TP_CD_RT = "RT";

    /** Document Type Code : QT */
    public static final String DOC_TP_CD_QUOTE = "QT";

    /** Document Type Code : SA */
    public static final String DOC_TP_CD_SCHEDULE_AGREEMENT = "SA";

    /** Target Entity : Order */
    public static final String TRGT_ENTITY_ORDER = "Order";

    /** Target Entity : Quote */
    public static final String TRGT_ENTITY_QUOTE = "Quote";

    /** Target Entity : Schedule */
    public static final String TRGT_ENTITY_SCHEDULE = "Schedule";

    /** NWAL1500 */
    public static final String SCRN_ID_ORDER_ENTRY = "NWAL1500";

    /** NWAL1770 */
    public static final String SCRN_ID_QUOTE_ENTRY = "NWAL1770";

    /** NWAL1840 */
    public static final String SCRN_ID_SCHD_AGMT = "NWAL1840";

    /** 1. */
    public static final String QUOTE_CONFIG_PREFIX = "1.";

    /** Order Prefix Transaction Category Code Shell */
    public static final String ORD_PRFT_TRX_CATG_SHELL = "S";
}
