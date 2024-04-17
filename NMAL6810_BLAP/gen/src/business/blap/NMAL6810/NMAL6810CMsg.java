//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20150327121712000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6810CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6810;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6810 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6810CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
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

    /** MDSE_ITEM_TP_CD_HL*/
	public final EZDCStringItemArray              mdseItemTpCd_HL;

    /** MDSE_ITEM_TP_DESC_TXT_HL*/
	public final EZDCStringItemArray              mdseItemTpDescTxt_HL;

    /** MDSE_ITEM_TP_CD_HS*/
	public final EZDCStringItem              mdseItemTpCd_HS;

    /** MDSE_CRAT_TMPL_NM_H*/
	public final EZDCStringItem              mdseCratTmplNm_H;

    /** A*/
	public final business.blap.NMAL6810.NMAL6810_ACMsgArray              A;


	/**
	 * NMAL6810CMsg is constructor.
	 * The initialization when the instance of NMAL6810CMsg is generated.
	 */
	public NMAL6810CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6810CMsg is constructor.
	 * The initialization when the instance of NMAL6810CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6810CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		mdseItemTpCd_HL = (EZDCStringItemArray)newItemArray("mdseItemTpCd_HL");
		mdseItemTpDescTxt_HL = (EZDCStringItemArray)newItemArray("mdseItemTpDescTxt_HL");
		mdseItemTpCd_HS = (EZDCStringItem)newItem("mdseItemTpCd_HS");
		mdseCratTmplNm_H = (EZDCStringItem)newItem("mdseCratTmplNm_H");
		A = (business.blap.NMAL6810.NMAL6810_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6810CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6810CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"mdseItemTpCd_HL", "mdseItemTpCd_HL", "HL", "99", TYPE_HANKAKUEISU, "2", null},
	{"mdseItemTpDescTxt_HL", "mdseItemTpDescTxt_HL", "HL", "99", TYPE_HANKAKUEISU, "50", null},
	{"mdseItemTpCd_HS", "mdseItemTpCd_HS", "HS", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseCratTmplNm_H", "mdseCratTmplNm_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"A", "A", null, "200", "business.blap.NMAL6810.NMAL6810_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"MDSE_ITEM_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd_HL
        {"MDSE_ITEM_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpDescTxt_HL
        {"MDSE_ITEM_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd_HS
        {"MDSE_CRAT_TMPL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCratTmplNm_H
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
