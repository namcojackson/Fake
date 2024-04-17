//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160926205100000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2120CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFBL2120;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFBL2120 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFBL2120CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDCBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDCBigDecimalItem              xxPageShowTotNum;

    /** XX_SORT_TBL_NM*/
	public final EZDCStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDCStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDCStringItem              xxSortOrdByTxt;

    /** XX_SCR_DPLY*/
	public final EZDCStringItem              xxScrDply;

    /** DOC_MGT_DOC_ID*/
	public final EZDCBigDecimalItem              docMgtDocId;

    /** DOC_MGT_CATG_CD*/
	public final EZDCStringItem              docMgtCatgCd;

    /** XX_SCR_EVENT_NM*/
	public final EZDCStringItem              xxScrEventNm;

    /** DOC_MGT_ORG_CD_L*/
	public final EZDCStringItemArray              docMgtOrgCd_L;

    /** DOC_MGT_ORG_DESC_TXT_L*/
	public final EZDCStringItemArray              docMgtOrgDescTxt_L;

    /** DOC_MGT_ORG_CD_H*/
	public final EZDCStringItem              docMgtOrgCd_H;

    /** DOC_MGT_PRTY_CD_L*/
	public final EZDCStringItemArray              docMgtPrtyCd_L;

    /** DOC_MGT_PRTY_DESC_TXT_L*/
	public final EZDCStringItemArray              docMgtPrtyDescTxt_L;

    /** DOC_MGT_PRTY_CD_H*/
	public final EZDCStringItem              docMgtPrtyCd_H;

    /** DOC_MGT_CATG_CD_L*/
	public final EZDCStringItemArray              docMgtCatgCd_L;

    /** DOC_MGT_CATG_DESC_TXT_L*/
	public final EZDCStringItemArray              docMgtCatgDescTxt_L;

    /** DOC_MGT_CATG_CD_H*/
	public final EZDCStringItem              docMgtCatgCd_H;

    /** XX_SRV_URL_TXT*/
	public final EZDCStringItem              xxSrvUrlTxt;

    /** XX_RADIO_BTN*/
	public final EZDCBigDecimalItem              xxRadioBtn;

    /** A*/
	public final business.blap.NFBL2120.NFBL2120_ACMsgArray              A;


	/**
	 * NFBL2120CMsg is constructor.
	 * The initialization when the instance of NFBL2120CMsg is generated.
	 */
	public NFBL2120CMsg() {
		this(false, -1);
	}

	/**
	 * NFBL2120CMsg is constructor.
	 * The initialization when the instance of NFBL2120CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFBL2120CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDCBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDCBigDecimalItem)newItem("xxPageShowTotNum");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		xxScrDply = (EZDCStringItem)newItem("xxScrDply");
		docMgtDocId = (EZDCBigDecimalItem)newItem("docMgtDocId");
		docMgtCatgCd = (EZDCStringItem)newItem("docMgtCatgCd");
		xxScrEventNm = (EZDCStringItem)newItem("xxScrEventNm");
		docMgtOrgCd_L = (EZDCStringItemArray)newItemArray("docMgtOrgCd_L");
		docMgtOrgDescTxt_L = (EZDCStringItemArray)newItemArray("docMgtOrgDescTxt_L");
		docMgtOrgCd_H = (EZDCStringItem)newItem("docMgtOrgCd_H");
		docMgtPrtyCd_L = (EZDCStringItemArray)newItemArray("docMgtPrtyCd_L");
		docMgtPrtyDescTxt_L = (EZDCStringItemArray)newItemArray("docMgtPrtyDescTxt_L");
		docMgtPrtyCd_H = (EZDCStringItem)newItem("docMgtPrtyCd_H");
		docMgtCatgCd_L = (EZDCStringItemArray)newItemArray("docMgtCatgCd_L");
		docMgtCatgDescTxt_L = (EZDCStringItemArray)newItemArray("docMgtCatgDescTxt_L");
		docMgtCatgCd_H = (EZDCStringItem)newItem("docMgtCatgCd_H");
		xxSrvUrlTxt = (EZDCStringItem)newItem("xxSrvUrlTxt");
		xxRadioBtn = (EZDCBigDecimalItem)newItem("xxRadioBtn");
		A = (business.blap.NFBL2120.NFBL2120_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFBL2120CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFBL2120CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxScrDply", "xxScrDply", null, null, TYPE_HANKAKUEISU, "50", null},
	{"docMgtDocId", "docMgtDocId", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"docMgtCatgCd", "docMgtCatgCd", null, null, TYPE_HANKAKUEISU, "5", null},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"docMgtOrgCd_L", "docMgtOrgCd_L", "L", "99", TYPE_HANKAKUEISU, "5", null},
	{"docMgtOrgDescTxt_L", "docMgtOrgDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"docMgtOrgCd_H", "docMgtOrgCd_H", "H", null, TYPE_HANKAKUEISU, "5", null},
	{"docMgtPrtyCd_L", "docMgtPrtyCd_L", "L", "99", TYPE_HANKAKUEISU, "5", null},
	{"docMgtPrtyDescTxt_L", "docMgtPrtyDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"docMgtPrtyCd_H", "docMgtPrtyCd_H", "H", null, TYPE_HANKAKUEISU, "5", null},
	{"docMgtCatgCd_L", "docMgtCatgCd_L", "L", "99", TYPE_HANKAKUEISU, "5", null},
	{"docMgtCatgDescTxt_L", "docMgtCatgDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"docMgtCatgCd_H", "docMgtCatgCd_H", "H", null, TYPE_HANKAKUEISU, "5", null},
	{"xxSrvUrlTxt", "xxSrvUrlTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxRadioBtn", "xxRadioBtn", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"A", "A", null, "20", "business.blap.NFBL2120.NFBL2120_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_SCR_DPLY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrDply
        {"DOC_MGT_DOC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtDocId
        {"DOC_MGT_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtCatgCd
        {"XX_SCR_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"DOC_MGT_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtOrgCd_L
        {"DOC_MGT_ORG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtOrgDescTxt_L
        {"DOC_MGT_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtOrgCd_H
        {"DOC_MGT_PRTY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtPrtyCd_L
        {"DOC_MGT_PRTY_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtPrtyDescTxt_L
        {"DOC_MGT_PRTY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtPrtyCd_H
        {"DOC_MGT_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtCatgCd_L
        {"DOC_MGT_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtCatgDescTxt_L
        {"DOC_MGT_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtCatgCd_H
        {"XX_SRV_URL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSrvUrlTxt
        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn
		null,	//A
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
