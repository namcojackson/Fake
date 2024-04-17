/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0050.constant;

/**
 * Class name: NFAL0040Constant
 * <dd>The class explanation: Constant variable for screen component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public interface NFAL0050Constant {

    /** Field ID */
    static final String IND_TP_CD_01 = "ajePtrnIndTpCd01";

    /** Field ID */
    static final String IND_TP_CD_02 = "ajePtrnIndTpCd02";

    /** Field ID */
    static final String IND_TP_CD_03 = "ajePtrnIndTpCd03";

    /** Field ID */
    static final String ACTL_CD_01 = "ajePtrnActlCd01";

    /** Field ID */
    static final String ACTL_CD_02 = "ajePtrnActlCd02";

    /** Field ID */
    static final String ACTL_CD_03 = "ajePtrnActlCd03";

    /** DEFAULT_VAL_CD_2 ID */
    static final String DEFAULT_VAL_CD_2 = new String("ZZ");

    /** DEFAULT_VAL_CD_3 ID */
    static final String DEFAULT_VAL_CD_3 = new String("ZZZ");

    /** Zero value */
    static final String ZERO_VAL = new String("0");

    /** Blank */
    static final String BLANK = new String("");

    /** Error Message Code */
    static final String MSG_CD_ERR = new String("E");

    /** RESET */
    static final boolean RESET = true;

    /** NOT_RESET */
    static final boolean NOT_RESET = false;

    /** HAS_RESULT */
    static final String HAS_RESULT = "Y";

    /** NO_RESULT */
    static final String HAS_NO_RESULT = "N";

    /** DEFAULT */
    static final String DEFAULT = "D";

    /** Fixed Value : */
    static final String FLG_Y = "Y";

    /** Fixed Value : */
    static final String FLG_N = "N";

    /** COA_CMPY */
    static final String COA_CMPY_CD = "COA_CMPY_CD";

    /** COA_BR */
    static final String COA_BR_CD = "COA_BR_CD";

    /** COA_CC_CD */
    static final String COA_CC_CD = "COA_CC_CD";

    /** COA_ACCT_CD */
    static final String COA_ACCT_CD = "COA_ACCT_CD";

    /** COA_PROD_CD */
    static final String COA_PROD_CD = "COA_PROD_CD";

    /** COA_BR */
    static final String COA_CH_CD = "COA_CH_CD";

    /** COA_AFFL_CD */
    static final String COA_AFFL_CD = "COA_AFFL_CD";

    /** COA_PROJ_CD */
    static final String COA_PROJ_CD = "COA_PROJ_CD";

    /** COA_EXTN_CD */
    static final String COA_EXTN_CD = "COA_EXTN_CD";

    /** CD_TOC */
    static final String CD_TOC = "@TOC";

    /** CD_LINK */
    static final String CD_LINK = "@LINK";

    /** CD_ITEM */
    static final String CD_ITEM = "@ITEM";

    /** CD_CUST */
    static final String CD_CUST = "@CUST";

    /** CD_VND */
    static final String CD_VND = "@VND";

    /** AJE ID */
    static final String AJE_ID = "AJE ID";

    /** #PR */
    static final String PR = "#PR";

    /** #PRBR */
    static final String PRBR = "#PRBR";

    /** #WH */
    static final String WH = "#WH";

    /** System Source Code */
    static final String SYS_SRC_CD = "System Source Code";

    /** Transaction Code */
    static final String TRX_CD = "Transaction Code";

    /** Transaction Reason Code */
    static final String TRX_RSN_CD = "Transaction Reason Code";

    /** Found Message */
    static final String MSG_FOUND_MORE_THAN_ONE = " Records found ";

    /** Found Message */
    static final String MSG_FOUND_ONE = "Record found";

    /** Hyphen */
    static final String HYPHEN = "-";

    /** Message for no record */
    static final String MSG_NO = "No";

    /** Message for no record */
    static final String MSG_IN = " in ";

    /** Message for no record */
    static final String MSG_THIS_COMB = "this combination. ";

    /** Message for Indicator Type */
    static final String MSG_SELECT_IND_TP_OR_POPUP = " - Select Indicator Type or Use Popup. ";

    /** Message for Code Value Type */
    static final String MSG_SELECT_ACTL_CD = " - Select Code Value Type. ";

    /** TYPE_1 */
    static final String TYPE_1 = "TYPE_1";

    /** TYPE_2 */
    static final String TYPE_2 = "TYPE_2";

    /** TYPE_3 */
    static final String TYPE_3 = "TYPE_3";

    /** CODE_1 */
    static final String CODE_1 = "CODE_1";

    /** CODE_2 */
    static final String CODE_2 = "CODE_2";

    /** CODE_3 */
    static final String CODE_3 = "CODE_3";

    /** LineIdxNum Array */
    static final String[] LINE_IDX_NUM_ARR = {"1D", "1C", "2D", "2C", "3D", "3C", "4D", "4C", "5D", "5C" };

    /** Event name for Paste */
    static final String EVT_PASTE = "EVT_PASTE";
}
