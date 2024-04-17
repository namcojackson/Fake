//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151116142007000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC400001_xxOrdInfoListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC400001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC400001_xxOrdInfoListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CPO_ORD_NUM_O*/
	public final EZDPStringItem              cpoOrdNum_O;

    /** DS_CPO_RTRN_LINE_NUM_O*/
	public final EZDPStringItem              dsCpoRtrnLineNum_O;

    /** DS_CPO_RTRN_LINE_SUB_NUM_O*/
	public final EZDPStringItem              dsCpoRtrnLineSubNum_O;


	/**
	 * NWZC400001_xxOrdInfoListPMsg is constructor.
	 * The initialization when the instance of NWZC400001_xxOrdInfoListPMsg is generated.
	 */
	public NWZC400001_xxOrdInfoListPMsg() {
		this(false, -1);
	}

	/**
	 * NWZC400001_xxOrdInfoListPMsg is constructor.
	 * The initialization when the instance of NWZC400001_xxOrdInfoListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC400001_xxOrdInfoListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cpoOrdNum_O = (EZDPStringItem)newItem("cpoOrdNum_O");
		dsCpoRtrnLineNum_O = (EZDPStringItem)newItem("dsCpoRtrnLineNum_O");
		dsCpoRtrnLineSubNum_O = (EZDPStringItem)newItem("dsCpoRtrnLineSubNum_O");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC400001_xxOrdInfoListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC400001_xxOrdInfoListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cpoOrdNum_O", "cpoOrdNum_O", "O", null, TYPE_HANKAKUEISU, "8", null},
	{"dsCpoRtrnLineNum_O", "dsCpoRtrnLineNum_O", "O", null, TYPE_HANKAKUEISU, "3", null},
	{"dsCpoRtrnLineSubNum_O", "dsCpoRtrnLineSubNum_O", "O", null, TYPE_HANKAKUEISU, "3", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_O
        {"DS_CPO_RTRN_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoRtrnLineNum_O
        {"DS_CPO_RTRN_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoRtrnLineSubNum_O
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
