//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230511170929000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0640_BSMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLCL0640 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL0640_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TECH_LOC_CD_B*/
	public final EZDSStringItem              techLocCd_B;

    /** LOC_NM_B*/
	public final EZDSStringItem              locNm_B;

    /** MDSE_CD_B*/
	public final EZDSStringItem              mdseCd_B;

    /** MDSE_DESC_SHORT_TXT_B*/
	public final EZDSStringItem              mdseDescShortTxt_B;

    /** RCV_SER_TAKE_FLG_B*/
	public final EZDSStringItem              rcvSerTakeFlg_B;

    /** CNT_INP_QTY_B*/
	public final EZDSBigDecimalItem              cntInpQty_B;

    /** SER_NUM_B*/
	public final EZDSStringItem              serNum_B;


	/**
	 * NLCL0640_BSMsg is constructor.
	 * The initialization when the instance of NLCL0640_BSMsg is generated.
	 */
	public NLCL0640_BSMsg() {
		this(false, -1);
	}

	/**
	 * NLCL0640_BSMsg is constructor.
	 * The initialization when the instance of NLCL0640_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL0640_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		techLocCd_B = (EZDSStringItem)newItem("techLocCd_B");
		locNm_B = (EZDSStringItem)newItem("locNm_B");
		mdseCd_B = (EZDSStringItem)newItem("mdseCd_B");
		mdseDescShortTxt_B = (EZDSStringItem)newItem("mdseDescShortTxt_B");
		rcvSerTakeFlg_B = (EZDSStringItem)newItem("rcvSerTakeFlg_B");
		cntInpQty_B = (EZDSBigDecimalItem)newItem("cntInpQty_B");
		serNum_B = (EZDSStringItem)newItem("serNum_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL0640_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL0640_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"techLocCd_B", "techLocCd_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_B", "locNm_B", "B", null, TYPE_HANKAKUEISU, "60", null},
	{"mdseCd_B", "mdseCd_B", "B", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_B", "mdseDescShortTxt_B", "B", null, TYPE_HANKAKUEISU, "250", null},
	{"rcvSerTakeFlg_B", "rcvSerTakeFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"cntInpQty_B", "cntInpQty_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"serNum_B", "serNum_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TECH_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techLocCd_B
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_B
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_B
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_B
        {"RCV_SER_TAKE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcvSerTakeFlg_B
        {"CNT_INP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cntInpQty_B
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_B
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
