//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170803014821000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3150BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL3150;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3150 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3150BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD*/
	public final EZDBStringItem              mdseCd;

    /** RTL_WH_CD*/
	public final EZDBStringItem              rtlWhCd;

    /** RTL_WH_NM*/
	public final EZDBStringItem              rtlWhNm;

    /** RTL_SWH_CD*/
	public final EZDBStringItem              rtlSwhCd;

    /** RTL_SWH_NM*/
	public final EZDBStringItem              rtlSwhNm;

    /** A*/
	public final business.servlet.NLBL3150.NLBL3150_ABMsgArray              A;

    /** X*/
	public final business.servlet.NLBL3150.NLBL3150_XBMsgArray              X;

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

    /** XX_SCR_EVENT_NM*/
	public final EZDBStringItem              xxScrEventNm;

    /** XX_CELL_IDX*/
	public final EZDBBigDecimalItem              xxCellIdx;

    /** Y*/
	public final business.servlet.NLBL3150.NLBL3150_YBMsgArray              Y;

    /** Z*/
	public final business.servlet.NLBL3150.NLBL3150_ZBMsgArray              Z;


	/**
	 * NLBL3150BMsg is constructor.
	 * The initialization when the instance of NLBL3150BMsg is generated.
	 */
	public NLBL3150BMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3150BMsg is constructor.
	 * The initialization when the instance of NLBL3150BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3150BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd = (EZDBStringItem)newItem("mdseCd");
		rtlWhCd = (EZDBStringItem)newItem("rtlWhCd");
		rtlWhNm = (EZDBStringItem)newItem("rtlWhNm");
		rtlSwhCd = (EZDBStringItem)newItem("rtlSwhCd");
		rtlSwhNm = (EZDBStringItem)newItem("rtlSwhNm");
		A = (business.servlet.NLBL3150.NLBL3150_ABMsgArray)newMsgArray("A");
		X = (business.servlet.NLBL3150.NLBL3150_XBMsgArray)newMsgArray("X");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		xxScrEventNm = (EZDBStringItem)newItem("xxScrEventNm");
		xxCellIdx = (EZDBBigDecimalItem)newItem("xxCellIdx");
		Y = (business.servlet.NLBL3150.NLBL3150_YBMsgArray)newMsgArray("Y");
		Z = (business.servlet.NLBL3150.NLBL3150_ZBMsgArray)newMsgArray("Z");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3150BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3150BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"rtlWhCd", "rtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm", "rtlWhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd", "rtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm", "rtlSwhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"A", "A", null, "40", "business.servlet.NLBL3150.NLBL3150_ABMsgArray", null, null},
	
	{"X", "X", null, "10", "business.servlet.NLBL3150.NLBL3150_XBMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxCellIdx", "xxCellIdx", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"Y", "Y", null, "20", "business.servlet.NLBL3150.NLBL3150_YBMsgArray", null, null},
	
	{"Z", "Z", null, "10", "business.servlet.NLBL3150.NLBL3150_ZBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm
        {"RTL_SWH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd
        {"RTL_SWH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm
		null,	//A
		null,	//X
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_SCR_EVENT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"XX_CELL_IDX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCellIdx
		null,	//Y
		null,	//Z
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
