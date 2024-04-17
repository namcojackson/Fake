//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20171003180739000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC200001_xxDetailListPMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLZC200001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLZC200001_xxDetailListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CPO_RTRN_LINE_NUM*/
	public final EZDPStringItem              dsCpoRtrnLineNum;

    /** DS_CPO_RTRN_LINE_SUB_NUM*/
	public final EZDPStringItem              dsCpoRtrnLineSubNum;

    /** ORD_QTY*/
	public final EZDPBigDecimalItem              ordQty;

    /** SER_NUM*/
	public final EZDPStringItem              serNum;


	/**
	 * NLZC200001_xxDetailListPMsg is constructor.
	 * The initialization when the instance of NLZC200001_xxDetailListPMsg is generated.
	 */
	public NLZC200001_xxDetailListPMsg() {
		this(false, -1);
	}

	/**
	 * NLZC200001_xxDetailListPMsg is constructor.
	 * The initialization when the instance of NLZC200001_xxDetailListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLZC200001_xxDetailListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsCpoRtrnLineNum = (EZDPStringItem)newItem("dsCpoRtrnLineNum");
		dsCpoRtrnLineSubNum = (EZDPStringItem)newItem("dsCpoRtrnLineSubNum");
		ordQty = (EZDPBigDecimalItem)newItem("ordQty");
		serNum = (EZDPStringItem)newItem("serNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NLZC200001_xxDetailListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLZC200001_xxDetailListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsCpoRtrnLineNum", "dsCpoRtrnLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"dsCpoRtrnLineSubNum", "dsCpoRtrnLineSubNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"ordQty", "ordQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CPO_RTRN_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoRtrnLineNum
        {"DS_CPO_RTRN_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoRtrnLineSubNum
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
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

