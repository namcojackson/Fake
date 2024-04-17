//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20090825100326000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC214001_soNumListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLZC214001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLZC214001_soNumListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SO_NUM*/
	public final EZDPStringItem              soNum;


	/**
	 * NLZC214001_soNumListPMsg is constructor.
	 * The initialization when the instance of NLZC214001_soNumListPMsg is generated.
	 */
	public NLZC214001_soNumListPMsg() {
		this(false, -1);
	}

	/**
	 * NLZC214001_soNumListPMsg is constructor.
	 * The initialization when the instance of NLZC214001_soNumListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLZC214001_soNumListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		soNum = (EZDPStringItem)newItem("soNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NLZC214001_soNumListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLZC214001_soNumListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"soNum", "soNum", null, null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum
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

