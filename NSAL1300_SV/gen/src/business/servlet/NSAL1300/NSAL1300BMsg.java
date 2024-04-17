//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180307171423000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1300BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1300;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1300 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1300BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_CONTR_BLLG_PK*/
	public final EZDBBigDecimalItem              svcContrBllgPk;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDBStringItem              xxComnColOrdTxt;

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

    /** A*/
	public final business.servlet.NSAL1300.NSAL1300_ABMsgArray              A;

    /** B*/
	public final business.servlet.NSAL1300.NSAL1300_BBMsgArray              B;

    /** MTR_LB_DESC_TXT_FN*/
	public final EZDBStringItem              mtrLbDescTxt_FN;

    /** MTR_LB_DESC_TXT_FT*/
	public final EZDBStringItem              mtrLbDescTxt_FT;

    /** SER_NUM_F*/
	public final EZDBStringItem              serNum_F;

    /** XX_WRN_SKIP_FLG*/
	public final EZDBStringItem              xxWrnSkipFlg;


	/**
	 * NSAL1300BMsg is constructor.
	 * The initialization when the instance of NSAL1300BMsg is generated.
	 */
	public NSAL1300BMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1300BMsg is constructor.
	 * The initialization when the instance of NSAL1300BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1300BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcContrBllgPk = (EZDBBigDecimalItem)newItem("svcContrBllgPk");
		xxComnColOrdTxt = (EZDBStringItem)newItem("xxComnColOrdTxt");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		A = (business.servlet.NSAL1300.NSAL1300_ABMsgArray)newMsgArray("A");
		B = (business.servlet.NSAL1300.NSAL1300_BBMsgArray)newMsgArray("B");
		mtrLbDescTxt_FN = (EZDBStringItem)newItem("mtrLbDescTxt_FN");
		mtrLbDescTxt_FT = (EZDBStringItem)newItem("mtrLbDescTxt_FT");
		serNum_F = (EZDBStringItem)newItem("serNum_F");
		xxWrnSkipFlg = (EZDBStringItem)newItem("xxWrnSkipFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1300BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1300BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcContrBllgPk", "svcContrBllgPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "100", "business.servlet.NSAL1300.NSAL1300_ABMsgArray", null, null},
	
	{"B", "B", null, "40", "business.servlet.NSAL1300.NSAL1300_BBMsgArray", null, null},
	
	{"mtrLbDescTxt_FN", "mtrLbDescTxt_FN", "FN", null, TYPE_HANKAKUEISU, "50", null},
	{"mtrLbDescTxt_FT", "mtrLbDescTxt_FT", "FT", null, TYPE_HANKAKUEISU, "50", null},
	{"serNum_F", "serNum_F", "F", null, TYPE_HANKAKUEISU, "30", null},
	{"xxWrnSkipFlg", "xxWrnSkipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_CONTR_BLLG_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBllgPk
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
		null,	//A
		null,	//B
        {"MTR_LB_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_FN
        {"MTR_LB_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_FT
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_F
        {"XX_WRN_SKIP_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWrnSkipFlg
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
