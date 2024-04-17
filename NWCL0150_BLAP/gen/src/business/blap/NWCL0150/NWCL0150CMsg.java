//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180104145240000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWCL0150CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWCL0150;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWCL0150 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWCL0150CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CPO_ORD_NUM*/
	public final EZDCStringItem              cpoOrdNum;

    /** INV_NUM*/
	public final EZDCStringItem              invNum;

    /** CFS_LEASE_PKG_HLD_FLG_HF*/
	public final EZDCStringItemArray              cfsLeasePkgHldFlg_HF;

    /** XX_FLG_NM_HF*/
	public final EZDCStringItemArray              xxFlgNm_HF;

    /** CFS_LEASE_PKG_HLD_FLG_SL*/
	public final EZDCStringItem              cfsLeasePkgHldFlg_SL;

    /** LEASE_PKG_CRAT_FLG_CF*/
	public final EZDCStringItemArray              leasePkgCratFlg_CF;

    /** XX_FLG_NM_CF*/
	public final EZDCStringItemArray              xxFlgNm_CF;

    /** LEASE_PKG_CRAT_FLG_SL*/
	public final EZDCStringItem              leasePkgCratFlg_SL;

    /** ATTRB_VAL_NUM*/
	public final EZDCBigDecimalItem              attrbValNum;

    /** A*/
	public final business.blap.NWCL0150.NWCL0150_ACMsgArray              A;

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

    /** XX_SCR_EVENT_NM*/
	public final EZDCStringItem              xxScrEventNm;


	/**
	 * NWCL0150CMsg is constructor.
	 * The initialization when the instance of NWCL0150CMsg is generated.
	 */
	public NWCL0150CMsg() {
		this(false, -1);
	}

	/**
	 * NWCL0150CMsg is constructor.
	 * The initialization when the instance of NWCL0150CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWCL0150CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cpoOrdNum = (EZDCStringItem)newItem("cpoOrdNum");
		invNum = (EZDCStringItem)newItem("invNum");
		cfsLeasePkgHldFlg_HF = (EZDCStringItemArray)newItemArray("cfsLeasePkgHldFlg_HF");
		xxFlgNm_HF = (EZDCStringItemArray)newItemArray("xxFlgNm_HF");
		cfsLeasePkgHldFlg_SL = (EZDCStringItem)newItem("cfsLeasePkgHldFlg_SL");
		leasePkgCratFlg_CF = (EZDCStringItemArray)newItemArray("leasePkgCratFlg_CF");
		xxFlgNm_CF = (EZDCStringItemArray)newItemArray("xxFlgNm_CF");
		leasePkgCratFlg_SL = (EZDCStringItem)newItem("leasePkgCratFlg_SL");
		attrbValNum = (EZDCBigDecimalItem)newItem("attrbValNum");
		A = (business.blap.NWCL0150.NWCL0150_ACMsgArray)newMsgArray("A");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDCBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDCBigDecimalItem)newItem("xxPageShowTotNum");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		xxScrEventNm = (EZDCStringItem)newItem("xxScrEventNm");
	}

	/**
	 * get the type of array which is stored
	 * @return NWCL0150CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWCL0150CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"invNum", "invNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"cfsLeasePkgHldFlg_HF", "cfsLeasePkgHldFlg_HF", "HF", "3", TYPE_HANKAKUEISU, "1", null},
	{"xxFlgNm_HF", "xxFlgNm_HF", "HF", "3", TYPE_HANKAKUEISU, "4", null},
	{"cfsLeasePkgHldFlg_SL", "cfsLeasePkgHldFlg_SL", "SL", null, TYPE_HANKAKUEISU, "1", null},
	{"leasePkgCratFlg_CF", "leasePkgCratFlg_CF", "CF", "3", TYPE_HANKAKUEISU, "1", null},
	{"xxFlgNm_CF", "xxFlgNm_CF", "CF", "3", TYPE_HANKAKUEISU, "4", null},
	{"leasePkgCratFlg_SL", "leasePkgCratFlg_SL", "SL", null, TYPE_HANKAKUEISU, "1", null},
	{"attrbValNum", "attrbValNum", null, null, TYPE_SEISU_SYOSU, "33", "5"},
	{"A", "A", null, "40", "business.blap.NWCL0150.NWCL0150_ACMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum
        {"CFS_LEASE_PKG_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cfsLeasePkgHldFlg_HF
        {"XX_FLG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFlgNm_HF
        {"CFS_LEASE_PKG_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cfsLeasePkgHldFlg_SL
        {"LEASE_PKG_CRAT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leasePkgCratFlg_CF
        {"XX_FLG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFlgNm_CF
        {"LEASE_PKG_CRAT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leasePkgCratFlg_SL
        {"ATTRB_VAL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attrbValNum
		null,	//A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_SCR_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
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
