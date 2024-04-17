//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161025104300000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2590BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2590;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2590 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2590BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** PROC_DT*/
	public final EZDBDateItem              procDt;

    /** CTY_ADDR*/
	public final EZDBStringItem              ctyAddr;

    /** ST_CD*/
	public final EZDBStringItem              stCd;

    /** POST_CD*/
	public final EZDBStringItem              postCd;

    /** CNTY_NM*/
	public final EZDBStringItem              cntyNm;

    /** XX_CELL_IDX*/
	public final EZDBBigDecimalItem              xxCellIdx;

    /** A*/
	public final business.servlet.NMAL2590.NMAL2590_ABMsgArray              A;

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;


	/**
	 * NMAL2590BMsg is constructor.
	 * The initialization when the instance of NMAL2590BMsg is generated.
	 */
	public NMAL2590BMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2590BMsg is constructor.
	 * The initialization when the instance of NMAL2590BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2590BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		procDt = (EZDBDateItem)newItem("procDt");
		ctyAddr = (EZDBStringItem)newItem("ctyAddr");
		stCd = (EZDBStringItem)newItem("stCd");
		postCd = (EZDBStringItem)newItem("postCd");
		cntyNm = (EZDBStringItem)newItem("cntyNm");
		xxCellIdx = (EZDBBigDecimalItem)newItem("xxCellIdx");
		A = (business.servlet.NMAL2590.NMAL2590_ABMsgArray)newMsgArray("A");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2590BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2590BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"procDt", "procDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"ctyAddr", "ctyAddr", null, null, TYPE_HANKAKUEISU, "25", null},
	{"stCd", "stCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"postCd", "postCd", null, null, TYPE_HANKAKUEISU, "15", null},
	{"cntyNm", "cntyNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxCellIdx", "xxCellIdx", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"A", "A", null, "200", "business.servlet.NMAL2590.NMAL2590_ABMsgArray", null, null},
	
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"PROC_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//procDt
        {"CTY_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr
        {"ST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd
        {"POST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd
        {"CNTY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cntyNm
        {"XX_CELL_IDX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCellIdx
		null,	//A
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
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

