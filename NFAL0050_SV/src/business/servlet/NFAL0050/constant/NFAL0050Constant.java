/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0050.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 * Class name: NFAL0040Constant
 * <dd>The class explanation: Constant variable for screen component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public interface NFAL0050Constant {

    /** Screen ID */
    static final String SCRN_ID = "NFAL0050Scrn00";

    /** Business ID */
    static final String BIZ_ID = "NFAL0050";

    /** Event name for CMN_Close */
    static final String EVENT_CMN_CLOSE = "CMN_Close";

    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    /** Function Button 1 */
    static final String[] BTN_CMN_SAVE = {"btn1", "", "" };

    /** Function Button 2 */
    static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    static final String[] BTN_CMN_APPLY = {"btn3", "", "" };

    /** Function Button 4 */
    static final String[] BTN_CMN_APPROVE = {"btn4", "", "" };

    /** Function Button 5 */
    static final String[] BTN_CMN_REJECT = {"btn5", "", "" };

    /** Function Button 6 */
    static final String[] BTN_CMN_DOWNLOAD = {"btn6", "", "" };

    /** Function Button 7 */
    static final String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Event name for Add New EVT_ADD_NEW_AJE_ID ID */
    static final String EVT_ADD_NEW_AJE_ID = "AddNewAJEIDBtn";

    /** Event name for EVT_ON_SEARCH */
    static final String EVT_ON_SEARCH = "SearchAjeIdBtn";

    /** Event name for name for EVT_COPY_ROW */
    static final String EVT_COPY_ROW = "CopyRowBtn";

    /** Event name for name for EVT_PASTE_ROW */
    static final String EVT_PASTE_ROW = "PasteRowBtn";

    /** Event name for name for EVT_ADD_ROW */
    static final String EVT_ADD_ROW = "AddRowBtn";

    /** Event name for EVT_DELETE_ROW */
    static final String EVT_DELETE_ROW = "DeleteRowBtn";

    /** Event name for Search Cash Account Code */
    static final String EVT_SELECT_ALL = "SelectAllBtn";

    /** Event name for Search DA Account Code */
    static final String EVT_UN_SELECT_ALL = "UnselectAllBtn";

    /** Event name for Search COA_BR Code */
    static final String EVT_COA_BR = "EVT_COA_BR";

    /** Event name for Search COA_CC Code */
    static final String EVT_COA_CC = "EVT_COA_CC";

    /** Event name for Search COA_PROD Code */
    static final String EVT_COA_PROD = "EVT_COA_PROD";

    /** Event name for Search COA_CH Code */
    static final String EVT_COA_CH = "EVT_COA_CH";

    /** Event name for Search COA_AFFL Code */
    static final String EVT_COA_AFFL = "EVT_COA_AFFL";

    /** Event name for Search COA_PROJ Code */
    static final String EVT_COA_PROJ = "EVT_COA_PROJ";

    /** Event name for Search JRNL_CATG Code */
    static final String EVT_JRNL_CATG = "EVT_JRNL_CATG";

    /** DEFAULT_VAL_CD_2 ID */
    static final String DEFAULT_VAL_CD_2 = new String("ZZ");

    /** DEFAULT_VAL_CD_3 ID */
    static final String DEFAULT_VAL_CD_3 = new String("ZZZ");

    /** AJE_LINK_FLG_Y */
    static final String AJE_LINK_FLG_Y = ZYPConstant.FLG_ON_Y;

    /** RESET */
    static final boolean RESET = true;

    /** NOT_RESET */
    static final boolean NOT_RESET = false;

    /** Hyphen */
    static final String HYPHEN = "-";

    /** HAS_RESULT */
    static final String HAS_RESULT = "Y";

    /** NO_RESULT */
    static final String HAS_NO_RESULT = "N";

    /** DEFAULT */
    static final String DEFAULT = "D";

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

    /** #PR */
    static final String PR = "#PR";

    /** #PRBR */
    static final String PRBR = "#PRBR";

    /** #WH */
    static final String WH = "#WH";

    /** CheckBox Checked Flg */
    static final String CHECKED = ZYPConstant.CHKBOX_ON_Y;

    /** Zero value */
    static final String ZERO_VAL = new String("0");

    /** Blank */
    static final String BLANK = new String("");

    /** Error Message Code */
    static final String MSG_CD_ERR = new String("E");

    /** 0:E, 1:W, 2:OK */
    static final int ERROR = 0;

    /** 0:E, 1:W, 2:OK */
    static final int WARNING = 1;

    /** 0:E, 1:W, 2:OK */
    static final int OK = 2;

    /** AJE ID */
    static final String AJE_ID = "AJE ID";

    /** System Source Code */
    static final String SYS_SRC_CD = "System Source Code";

    /** Transaction Code */
    static final String TRX_CD = "Transaction Code";

    /** Transaction Reason Code */
    static final String TRX_RSN_CD = "Transaction Reason sCode";

    /** Indicator Type */
    static final String IND_TP_CD = "Indicator Type";

    /** Message */
    static final String DR_AND_CR = ":Dr and Cr";

    /** Index */
    static final String INDEX = "INDEX";

    /** Message */
    static final String MSG = "MSG";

    /** Event name for Paste */
    static final String EVT_PASTE = "EVT_PASTE";

    /** Message text */
    static final String PASTE_MODE_TXT = "In Paste Mode";
    
    /** Read Only user */
    static final String AUTH_READONLY = "NFAL0050T020";

    /** READ_ONLY_MSG */
    static final String READ_ONLY_MSG = " Read Only";

    /** READ_ONLY */
    static final String READ_ONLY = "READ_ONLY";
    // Start 2016/12/20 H.Ikeda [QC#12865,MOD]
    /** FUNC_ID_UPDATE */
    static final String FUNC_ID_UPDATE = "NFAL0050T020";
    /** User @ has no permissions to operate this application. */
    static final String ZZSM4300E = "ZZSM4300E";
    // End   2016/12/20 H.Ikeda [QC#12865,MOD]
}
