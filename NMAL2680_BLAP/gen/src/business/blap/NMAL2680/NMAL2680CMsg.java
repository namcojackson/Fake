//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160601160101000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2680CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2680;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2680 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2680CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TRTY_RULE_PK_H1*/
	public final EZDCBigDecimalItem              trtyRulePk_H1;

    /** DS_ACCT_TP_CD_H1*/
	public final EZDCStringItem              dsAcctTpCd_H1;

    /** ORG_CD_P1*/
	public final EZDCStringItem              orgCd_P1;

    /** ORG_CD_H1*/
	public final EZDCStringItem              orgCd_H1;

    /** ORG_NM_H1*/
	public final EZDCStringItem              orgNm_H1;

    /** XX_DS_MSG_ENTRY_TXT_H1*/
	public final EZDCStringItem              xxDsMsgEntryTxt_H1;

    /** XX_QUERY_FLTR_TXT_H1*/
	public final EZDCStringItem              xxQueryFltrTxt_H1;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** XX_SORT_TBL_NM*/
	public final EZDCStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDCStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDCStringItem              xxSortOrdByTxt;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** A*/
	public final business.blap.NMAL2680.NMAL2680_ACMsgArray              A;


	/**
	 * NMAL2680CMsg is constructor.
	 * The initialization when the instance of NMAL2680CMsg is generated.
	 */
	public NMAL2680CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2680CMsg is constructor.
	 * The initialization when the instance of NMAL2680CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2680CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		trtyRulePk_H1 = (EZDCBigDecimalItem)newItem("trtyRulePk_H1");
		dsAcctTpCd_H1 = (EZDCStringItem)newItem("dsAcctTpCd_H1");
		orgCd_P1 = (EZDCStringItem)newItem("orgCd_P1");
		orgCd_H1 = (EZDCStringItem)newItem("orgCd_H1");
		orgNm_H1 = (EZDCStringItem)newItem("orgNm_H1");
		xxDsMsgEntryTxt_H1 = (EZDCStringItem)newItem("xxDsMsgEntryTxt_H1");
		xxQueryFltrTxt_H1 = (EZDCStringItem)newItem("xxQueryFltrTxt_H1");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		A = (business.blap.NMAL2680.NMAL2680_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2680CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2680CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"trtyRulePk_H1", "trtyRulePk_H1", "H1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsAcctTpCd_H1", "dsAcctTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"orgCd_P1", "orgCd_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgCd_H1", "orgCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_H1", "orgNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxDsMsgEntryTxt_H1", "xxDsMsgEntryTxt_H1", "H1", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxQueryFltrTxt_H1", "xxQueryFltrTxt_H1", "H1", null, TYPE_HANKAKUEISU, "200", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"A", "A", null, "200", "business.blap.NMAL2680.NMAL2680_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TRTY_RULE_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRulePk_H1
        {"DS_ACCT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctTpCd_H1
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_P1
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_H1
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_H1
        {"XX_DS_MSG_ENTRY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMsgEntryTxt_H1
        {"XX_QUERY_FLTR_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxQueryFltrTxt_H1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
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
