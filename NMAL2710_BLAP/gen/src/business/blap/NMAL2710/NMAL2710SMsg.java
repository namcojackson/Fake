//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20171121102135000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2710SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2710;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2710 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2710SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition

	/**
	 * NMAL2710SMsg is constructor.
	 * The initialization when the instance of NMAL2710SMsg is generated.
	 */
	public NMAL2710SMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2710SMsg is constructor.
	 * The initialization when the instance of NMAL2710SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2710SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2710SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2710SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

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
