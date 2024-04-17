//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160627164912000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8860SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NYEL8860;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NYEL8860 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NYEL8860SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** A*/
	public final business.blap.NYEL8860.NYEL8860_ASMsgArray              A;


	/**
	 * NYEL8860SMsg is constructor.
	 * The initialization when the instance of NYEL8860SMsg is generated.
	 */
	public NYEL8860SMsg() {
		this(false, -1);
	}

	/**
	 * NYEL8860SMsg is constructor.
	 * The initialization when the instance of NYEL8860SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYEL8860SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		A = (business.blap.NYEL8860.NYEL8860_ASMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NYEL8860SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYEL8860SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"A", "A", null, "200", "business.blap.NYEL8860.NYEL8860_ASMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

		null,	//A
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

