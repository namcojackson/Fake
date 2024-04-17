//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160407141007000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7070BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7070;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7070 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7070BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SPLY_AGMT_PLN_NM*/
	public final EZDBStringItem              splyAgmtPlnNm;

    /** SPLY_AGMT_PLN_SHORT_NM*/
	public final EZDBStringItem              splyAgmtPlnShortNm;

    /** SPLY_AGMT_PLN_DESC_TXT*/
	public final EZDBStringItem              splyAgmtPlnDescTxt;

    /** SPLY_AGMT_PLN_TP_CD*/
	public final EZDBStringItem              splyAgmtPlnTpCd;

    /** SPLY_AGMT_PLN_TP_CD_P*/
	public final EZDBStringItemArray              splyAgmtPlnTpCd_P;

    /** SPLY_AGMT_PLN_TP_DESC_TXT_P*/
	public final EZDBStringItemArray              splyAgmtPlnTpDescTxt_P;

    /** SPLY_AGMT_DOC_TP_CD*/
	public final EZDBStringItem              splyAgmtDocTpCd;

    /** SPLY_AGMT_DOC_TP_CD_P*/
	public final EZDBStringItemArray              splyAgmtDocTpCd_P;

    /** SPLY_AGMT_DOC_TP_DESC_TXT_P*/
	public final EZDBStringItemArray              splyAgmtDocTpDescTxt_P;

    /** DS_ACCT_NUM*/
	public final EZDBStringItem              dsAcctNum;

    /** DS_ACCT_NM*/
	public final EZDBStringItem              dsAcctNm;

    /** CSMP_NUM*/
	public final EZDBStringItem              csmpNum;

    /** COA_BR_CD*/
	public final EZDBStringItem              coaBrCd;

    /** LINE_BIZ_TP_CD*/
	public final EZDBStringItem              lineBizTpCd;

    /** LINE_BIZ_TP_CD_P*/
	public final EZDBStringItemArray              lineBizTpCd_P;

    /** LINE_BIZ_TP_DESC_TXT_P*/
	public final EZDBStringItemArray              lineBizTpDescTxt_P;

    /** EFF_FROM_DT*/
	public final EZDBDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDBDateItem              effThruDt;

    /** SPLY_AGMT_PLN_STS_CD*/
	public final EZDBStringItem              splyAgmtPlnStsCd;

    /** SPLY_AGMT_PLN_STS_CD_P*/
	public final EZDBStringItemArray              splyAgmtPlnStsCd_P;

    /** SPLY_AGMT_PLN_STS_DESC_TXT_P*/
	public final EZDBStringItemArray              splyAgmtPlnStsDescTxt_P;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDBBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDBBigDecimalItem              xxPageShowTotNum;

    /** XX_RADIO_BTN*/
	public final EZDBBigDecimalItem              xxRadioBtn;

    /** A*/
	public final business.servlet.NMAL7070.NMAL7070_ABMsgArray              A;

    /** P*/
	public final business.servlet.NMAL7070.NMAL7070_PBMsgArray              P;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDBStringItem              xxComnColOrdTxt;

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;

    /** XX_SCR_EVENT_NM*/
	public final EZDBStringItem              xxScrEventNm;

    /** XX_FILE_DATA_A*/
	public final EZDBMimeSourceItem              xxFileData_A;

    /** SRCH_OPT_PK*/
	public final EZDBBigDecimalItem              srchOptPk;

    /** SRCH_OPT_PK_L1*/
	public final EZDBBigDecimalItemArray              srchOptPk_L1;

    /** SRCH_OPT_NM_L1*/
	public final EZDBStringItemArray              srchOptNm_L1;

    /** SRCH_OPT_NM*/
	public final EZDBStringItem              srchOptNm;

    /** R*/
	public final business.servlet.NMAL7070.NMAL7070_RBMsgArray              R;


	/**
	 * NMAL7070BMsg is constructor.
	 * The initialization when the instance of NMAL7070BMsg is generated.
	 */
	public NMAL7070BMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7070BMsg is constructor.
	 * The initialization when the instance of NMAL7070BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7070BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		splyAgmtPlnNm = (EZDBStringItem)newItem("splyAgmtPlnNm");
		splyAgmtPlnShortNm = (EZDBStringItem)newItem("splyAgmtPlnShortNm");
		splyAgmtPlnDescTxt = (EZDBStringItem)newItem("splyAgmtPlnDescTxt");
		splyAgmtPlnTpCd = (EZDBStringItem)newItem("splyAgmtPlnTpCd");
		splyAgmtPlnTpCd_P = (EZDBStringItemArray)newItemArray("splyAgmtPlnTpCd_P");
		splyAgmtPlnTpDescTxt_P = (EZDBStringItemArray)newItemArray("splyAgmtPlnTpDescTxt_P");
		splyAgmtDocTpCd = (EZDBStringItem)newItem("splyAgmtDocTpCd");
		splyAgmtDocTpCd_P = (EZDBStringItemArray)newItemArray("splyAgmtDocTpCd_P");
		splyAgmtDocTpDescTxt_P = (EZDBStringItemArray)newItemArray("splyAgmtDocTpDescTxt_P");
		dsAcctNum = (EZDBStringItem)newItem("dsAcctNum");
		dsAcctNm = (EZDBStringItem)newItem("dsAcctNm");
		csmpNum = (EZDBStringItem)newItem("csmpNum");
		coaBrCd = (EZDBStringItem)newItem("coaBrCd");
		lineBizTpCd = (EZDBStringItem)newItem("lineBizTpCd");
		lineBizTpCd_P = (EZDBStringItemArray)newItemArray("lineBizTpCd_P");
		lineBizTpDescTxt_P = (EZDBStringItemArray)newItemArray("lineBizTpDescTxt_P");
		effFromDt = (EZDBDateItem)newItem("effFromDt");
		effThruDt = (EZDBDateItem)newItem("effThruDt");
		splyAgmtPlnStsCd = (EZDBStringItem)newItem("splyAgmtPlnStsCd");
		splyAgmtPlnStsCd_P = (EZDBStringItemArray)newItemArray("splyAgmtPlnStsCd_P");
		splyAgmtPlnStsDescTxt_P = (EZDBStringItemArray)newItemArray("splyAgmtPlnStsDescTxt_P");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		xxRadioBtn = (EZDBBigDecimalItem)newItem("xxRadioBtn");
		A = (business.servlet.NMAL7070.NMAL7070_ABMsgArray)newMsgArray("A");
		P = (business.servlet.NMAL7070.NMAL7070_PBMsgArray)newMsgArray("P");
		xxComnColOrdTxt = (EZDBStringItem)newItem("xxComnColOrdTxt");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		xxScrEventNm = (EZDBStringItem)newItem("xxScrEventNm");
		xxFileData_A = (EZDBMimeSourceItem)newItem("xxFileData_A");
		srchOptPk = (EZDBBigDecimalItem)newItem("srchOptPk");
		srchOptPk_L1 = (EZDBBigDecimalItemArray)newItemArray("srchOptPk_L1");
		srchOptNm_L1 = (EZDBStringItemArray)newItemArray("srchOptNm_L1");
		srchOptNm = (EZDBStringItem)newItem("srchOptNm");
		R = (business.servlet.NMAL7070.NMAL7070_RBMsgArray)newMsgArray("R");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7070BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7070BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"splyAgmtPlnNm", "splyAgmtPlnNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtPlnShortNm", "splyAgmtPlnShortNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtPlnDescTxt", "splyAgmtPlnDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtPlnTpCd", "splyAgmtPlnTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"splyAgmtPlnTpCd_P", "splyAgmtPlnTpCd_P", "P", "99", TYPE_HANKAKUEISU, "2", null},
	{"splyAgmtPlnTpDescTxt_P", "splyAgmtPlnTpDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtDocTpCd", "splyAgmtDocTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"splyAgmtDocTpCd_P", "splyAgmtDocTpCd_P", "P", "99", TYPE_HANKAKUEISU, "2", null},
	{"splyAgmtDocTpDescTxt_P", "splyAgmtDocTpDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"dsAcctNum", "dsAcctNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm", "dsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"csmpNum", "csmpNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"coaBrCd", "coaBrCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"lineBizTpCd", "lineBizTpCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"lineBizTpCd_P", "lineBizTpCd_P", "P", "99", TYPE_HANKAKUEISU, "8", null},
	{"lineBizTpDescTxt_P", "lineBizTpDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"splyAgmtPlnStsCd", "splyAgmtPlnStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"splyAgmtPlnStsCd_P", "splyAgmtPlnStsCd_P", "P", "99", TYPE_HANKAKUEISU, "2", null},
	{"splyAgmtPlnStsDescTxt_P", "splyAgmtPlnStsDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxRadioBtn", "xxRadioBtn", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"A", "A", null, "100", "business.servlet.NMAL7070.NMAL7070_ABMsgArray", null, null},
	
	{"P", "P", null, "24", "business.servlet.NMAL7070.NMAL7070_PBMsgArray", null, null},
	
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxFileData_A", "xxFileData_A", "A", null, TYPE_UPLOAD, null, null},
	{"srchOptPk", "srchOptPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptPk_L1", "srchOptPk_L1", "L1", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_L1", "srchOptNm_L1", "L1", "99", TYPE_HANKAKUEISU, "50", null},
	{"srchOptNm", "srchOptNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"R", "R", null, "99", "business.servlet.NMAL7070.NMAL7070_RBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SPLY_AGMT_PLN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnNm
        {"SPLY_AGMT_PLN_SHORT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnShortNm
        {"SPLY_AGMT_PLN_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnDescTxt
        {"SPLY_AGMT_PLN_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnTpCd
        {"SPLY_AGMT_PLN_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnTpCd_P
        {"SPLY_AGMT_PLN_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnTpDescTxt_P
        {"SPLY_AGMT_DOC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtDocTpCd
        {"SPLY_AGMT_DOC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtDocTpCd_P
        {"SPLY_AGMT_DOC_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtDocTpDescTxt_P
        {"DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"CSMP_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpNum
        {"COA_BR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd
        {"LINE_BIZ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd
        {"LINE_BIZ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_P
        {"LINE_BIZ_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpDescTxt_P
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt
        {"SPLY_AGMT_PLN_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnStsCd
        {"SPLY_AGMT_PLN_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnStsCd_P
        {"SPLY_AGMT_PLN_STS_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnStsDescTxt_P
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_RADIO_BTN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn
		null,	//A
		null,	//P
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_SCR_EVENT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_A
        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk
        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_L1
        {"SRCH_OPT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_L1
        {"SRCH_OPT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm
		null,	//R
	};

	/**
	 * get Array of common (basic) data.
	 * @return Array of common (basis) data
	 */
	protected String[][] getBaseContents() {
		return BASE_CONTENTS;
	}

	/**
	 * get Array of Display Field.
	 * @return Array of  Display  Fields
	 */
	protected String[][] getDispContents() {
		return DISP_CONTENTS;
	}

}

