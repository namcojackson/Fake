//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230511170928000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0640_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL0640;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL0640 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL0640_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TECH_LOC_CD_A*/
	public final EZDCStringItem              techLocCd_A;

    /** LOC_NM_A*/
	public final EZDCStringItem              locNm_A;

    /** MDSE_CD_A*/
	public final EZDCStringItem              mdseCd_A;

    /** MDSE_DESC_SHORT_TXT_A*/
	public final EZDCStringItem              mdseDescShortTxt_A;

    /** RCV_SER_TAKE_FLG_A*/
	public final EZDCStringItem              rcvSerTakeFlg_A;

    /** CNT_INP_QTY_A*/
	public final EZDCBigDecimalItem              cntInpQty_A;

    /** SER_NUM_A*/
	public final EZDCStringItem              serNum_A;


	/**
	 * NLCL0640_ACMsg is constructor.
	 * The initialization when the instance of NLCL0640_ACMsg is generated.
	 */
	public NLCL0640_ACMsg() {
		this(false, -1);
	}

	/**
	 * NLCL0640_ACMsg is constructor.
	 * The initialization when the instance of NLCL0640_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL0640_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		techLocCd_A = (EZDCStringItem)newItem("techLocCd_A");
		locNm_A = (EZDCStringItem)newItem("locNm_A");
		mdseCd_A = (EZDCStringItem)newItem("mdseCd_A");
		mdseDescShortTxt_A = (EZDCStringItem)newItem("mdseDescShortTxt_A");
		rcvSerTakeFlg_A = (EZDCStringItem)newItem("rcvSerTakeFlg_A");
		cntInpQty_A = (EZDCBigDecimalItem)newItem("cntInpQty_A");
		serNum_A = (EZDCStringItem)newItem("serNum_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL0640_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL0640_ACMsgArray();
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

        {"TECH_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techLocCd_A
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_A
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A
        {"RCV_SER_TAKE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcvSerTakeFlg_A
        {"CNT_INP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cntInpQty_A
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
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
