//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20171003180739000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC200001_RWSNumListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NLZC200001_RWSNumListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** RWS_NUM*/
	public final EZDPStringItem              rwsNum;


	/**
	 * NLZC200001_RWSNumListPMsg is constructor.
	 * The initialization when the instance of NLZC200001_RWSNumListPMsg is generated.
	 */
	public NLZC200001_RWSNumListPMsg() {
		this(false, -1);
	}

	/**
	 * NLZC200001_RWSNumListPMsg is constructor.
	 * The initialization when the instance of NLZC200001_RWSNumListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLZC200001_RWSNumListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		rwsNum = (EZDPStringItem)newItem("rwsNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NLZC200001_RWSNumListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLZC200001_RWSNumListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"rwsNum", "rwsNum", null, null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"RWS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsNum
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
