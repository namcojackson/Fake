//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230710182101000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3140CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3140;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3140 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3140CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** LINE_BIZ_TP_CD*/
	public final EZDCStringItem              lineBizTpCd;

    /** LINE_BIZ_TP_CD_PL*/
	public final EZDCStringItemArray              lineBizTpCd_PL;

    /** LINE_BIZ_TP_DESC_TXT_PL*/
	public final EZDCStringItemArray              lineBizTpDescTxt_PL;

    /** DS_ORD_CATG_CD*/
	public final EZDCStringItem              dsOrdCatgCd;

    /** DS_ORD_CATG_DESC_TXT*/
	public final EZDCStringItem              dsOrdCatgDescTxt;

    /** RTL_WH_CD*/
	public final EZDCStringItem              rtlWhCd;

    /** RTL_WH_NM*/
	public final EZDCStringItem              rtlWhNm;

    /** RTL_SWH_CD*/
	public final EZDCStringItem              rtlSwhCd;

    /** RTL_SWH_NM*/
	public final EZDCStringItem              rtlSwhNm;

    /** HARD_ALLOC_TP_CD*/
	public final EZDCStringItem              hardAllocTpCd;

    /** HARD_ALLOC_TP_CD_PL*/
	public final EZDCStringItemArray              hardAllocTpCd_PL;

    /** HARD_ALLOC_TP_DESC_TXT_PL*/
	public final EZDCStringItemArray              hardAllocTpDescTxt_PL;

    /** COA_PROD_CD*/
	public final EZDCStringItem              coaProdCd;

    /** COA_PROD_NM*/
	public final EZDCStringItem              coaProdNm;

    /** A*/
	public final business.blap.NLBL3140.NLBL3140_ACMsgArray              A;

    /** B*/
	public final business.blap.NLBL3140.NLBL3140_BCMsgArray              B;

    /** X*/
	public final business.blap.NLBL3140.NLBL3140_XCMsgArray              X;

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

    /** Z*/
	public final business.blap.NLBL3140.NLBL3140_ZCMsgArray              Z;

    /** XX_CELL_IDX*/
	public final EZDCBigDecimalItem              xxCellIdx;


	/**
	 * NLBL3140CMsg is constructor.
	 * The initialization when the instance of NLBL3140CMsg is generated.
	 */
	public NLBL3140CMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3140CMsg is constructor.
	 * The initialization when the instance of NLBL3140CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3140CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		lineBizTpCd = (EZDCStringItem)newItem("lineBizTpCd");
		lineBizTpCd_PL = (EZDCStringItemArray)newItemArray("lineBizTpCd_PL");
		lineBizTpDescTxt_PL = (EZDCStringItemArray)newItemArray("lineBizTpDescTxt_PL");
		dsOrdCatgCd = (EZDCStringItem)newItem("dsOrdCatgCd");
		dsOrdCatgDescTxt = (EZDCStringItem)newItem("dsOrdCatgDescTxt");
		rtlWhCd = (EZDCStringItem)newItem("rtlWhCd");
		rtlWhNm = (EZDCStringItem)newItem("rtlWhNm");
		rtlSwhCd = (EZDCStringItem)newItem("rtlSwhCd");
		rtlSwhNm = (EZDCStringItem)newItem("rtlSwhNm");
		hardAllocTpCd = (EZDCStringItem)newItem("hardAllocTpCd");
		hardAllocTpCd_PL = (EZDCStringItemArray)newItemArray("hardAllocTpCd_PL");
		hardAllocTpDescTxt_PL = (EZDCStringItemArray)newItemArray("hardAllocTpDescTxt_PL");
		coaProdCd = (EZDCStringItem)newItem("coaProdCd");
		coaProdNm = (EZDCStringItem)newItem("coaProdNm");
		A = (business.blap.NLBL3140.NLBL3140_ACMsgArray)newMsgArray("A");
		B = (business.blap.NLBL3140.NLBL3140_BCMsgArray)newMsgArray("B");
		X = (business.blap.NLBL3140.NLBL3140_XCMsgArray)newMsgArray("X");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDCBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDCBigDecimalItem)newItem("xxPageShowTotNum");
		Z = (business.blap.NLBL3140.NLBL3140_ZCMsgArray)newMsgArray("Z");
		xxCellIdx = (EZDCBigDecimalItem)newItem("xxCellIdx");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3140CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3140CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"lineBizTpCd", "lineBizTpCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"lineBizTpCd_PL", "lineBizTpCd_PL", "PL", "99", TYPE_HANKAKUEISU, "8", null},
	{"lineBizTpDescTxt_PL", "lineBizTpDescTxt_PL", "PL", "99", TYPE_HANKAKUEISU, "50", null},
	{"dsOrdCatgCd", "dsOrdCatgCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdCatgDescTxt", "dsOrdCatgDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"rtlWhCd", "rtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm", "rtlWhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd", "rtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm", "rtlSwhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"hardAllocTpCd", "hardAllocTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"hardAllocTpCd_PL", "hardAllocTpCd_PL", "PL", "99", TYPE_HANKAKUEISU, "2", null},
	{"hardAllocTpDescTxt_PL", "hardAllocTpDescTxt_PL", "PL", "99", TYPE_HANKAKUEISU, "50", null},
	{"coaProdCd", "coaProdCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdNm", "coaProdNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"A", "A", null, "40", "business.blap.NLBL3140.NLBL3140_ACMsgArray", null, null},
	
	{"B", "B", null, "40", "business.blap.NLBL3140.NLBL3140_BCMsgArray", null, null},
	
	{"X", "X", null, "10", "business.blap.NLBL3140.NLBL3140_XCMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"Z", "Z", null, "10", "business.blap.NLBL3140.NLBL3140_ZCMsgArray", null, null},
	
	{"xxCellIdx", "xxCellIdx", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_PL
        {"LINE_BIZ_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpDescTxt_PL
        {"DS_ORD_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm
        {"HARD_ALLOC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hardAllocTpCd
        {"HARD_ALLOC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hardAllocTpCd_PL
        {"HARD_ALLOC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hardAllocTpDescTxt_PL
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd
        {"COA_PROD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdNm
		null,	//A
		null,	//B
		null,	//X
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
		null,	//Z
        {"XX_CELL_IDX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCellIdx
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
