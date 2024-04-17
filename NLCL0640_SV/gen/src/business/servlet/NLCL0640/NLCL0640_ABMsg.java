//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230511170853000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0640_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLCL0640;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL0640 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL0640_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TECH_LOC_CD_A*/
	public final EZDBStringItem              techLocCd_A;

    /** LOC_NM_A*/
	public final EZDBStringItem              locNm_A;

    /** MDSE_CD_A*/
	public final EZDBStringItem              mdseCd_A;

    /** MDSE_DESC_SHORT_TXT_A*/
	public final EZDBStringItem              mdseDescShortTxt_A;

    /** RCV_SER_TAKE_FLG_A*/
	public final EZDBStringItem              rcvSerTakeFlg_A;

    /** CNT_INP_QTY_A*/
	public final EZDBBigDecimalItem              cntInpQty_A;

    /** SER_NUM_A*/
	public final EZDBStringItem              serNum_A;


	/**
	 * NLCL0640_ABMsg is constructor.
	 * The initialization when the instance of NLCL0640_ABMsg is generated.
	 */
	public NLCL0640_ABMsg() {
		this(false, -1);
	}

	/**
	 * NLCL0640_ABMsg is constructor.
	 * The initialization when the instance of NLCL0640_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL0640_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		techLocCd_A = (EZDBStringItem)newItem("techLocCd_A");
		locNm_A = (EZDBStringItem)newItem("locNm_A");
		mdseCd_A = (EZDBStringItem)newItem("mdseCd_A");
		mdseDescShortTxt_A = (EZDBStringItem)newItem("mdseDescShortTxt_A");
		rcvSerTakeFlg_A = (EZDBStringItem)newItem("rcvSerTakeFlg_A");
		cntInpQty_A = (EZDBBigDecimalItem)newItem("cntInpQty_A");
		serNum_A = (EZDBStringItem)newItem("serNum_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL0640_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL0640_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"techLocCd_A", "techLocCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_A", "locNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A", "mdseDescShortTxt_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"rcvSerTakeFlg_A", "rcvSerTakeFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"cntInpQty_A", "cntInpQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TECH_LOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techLocCd_A
        {"LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_A
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A
        {"RCV_SER_TAKE_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcvSerTakeFlg_A
        {"CNT_INP_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cntInpQty_A
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
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

