/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFBL2120.constant;

/**
 *<pre>
 * NFBL2120Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/14   Fujitsu         W.Honda         Create          N/A
 * 2016/10/25   Fujitsu         W.Honda         Update          QC#15114
 *</pre>
 */
public class NFBL2120Constant {

    /** Message Kind */
    public static final String MESSAGE_KIND_E = "E";

    /** DOC_MGT_CATG_NUM */
    public static final String COL_DOC_MGT_CATG_NUM = "DOC_MGT_CATG_NUM";

    /** DOC_MGT_CATG_CD */
    public static final String COL_DOC_MGT_CATG_CD = "DOC_MGT_CATG_CD";

    /** DOC_MGT_CATG_DESC_TXT */
    public static final String COL_DOC_MGT_CATG_DESC_TXT = "DOC_MGT_CATG_DESC_TXT";

    /** DOC_MGT_FLD_NUM */
    public static final String COL_DOC_MGT_FLD_NUM = "DOC_MGT_FLD_NUM";

    /** DOC_MGT_FLD_DESC_TXT */
    public static final String COL_DOC_MGT_FLD_DESC_TXT = "DOC_MGT_FLD_DESC_TXT";

    /** VAR_CHAR_CONST_NM : THEREFORE_CONN_AVAL_FLG */
    public static final String THEREFORE_CONN_AVAL_FLG = "THEREFORE_CONN_AVAL_FLG";

    // START 2016/10/25 W.Honda [QC#15114,ADD]
    /** VAR_CHAR_CONST_NM : NFBL2120_SEARCH_FOR_THEREFORE */
    public static final String NFBL2120_SEARCH_FOR_THEREFORE = "NFBL2120_SEARCH_FOR_THEREFORE";

    /** Search Condition String Asterisk : * */
    public static final String SEARCH_CONDITION_STRING_ASTERISK = "*";
    // END 2016/10/25 W.Honda [QC#15114,ADD]

    /** For search result field information */
    public static final String[] GET_SEARCH_RESULT_FLD_NM_LIST = {
        "Submitted By"
        , "Category"
        , "Line of BusinesNo"
        , "Submit Time"
        , "PriorityNo"
    };

    /** For search condition field information */
    public static final String[] GET_SEARCH_CONDITION_FLD_NM_LIST = {
        "Line of BusinesNo"
        , "PriorityNo"
        , "Submitted By"
        , "Invoice Status"
    };

    //--------------------------------
    // Message ID
    //--------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";
}
