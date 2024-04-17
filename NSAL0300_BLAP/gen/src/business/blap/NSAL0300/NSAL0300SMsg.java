//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20220331113802000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0300SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0300;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0300 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0300SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** A*/
	public final business.blap.NSAL0300.NSAL0300_ASMsgArray              A;

    /** X*/
	public final business.blap.NSAL0300.NSAL0300_XSMsgArray              X;


	/**
	 * NSAL0300SMsg is constructor.
	 * The initialization when the instance of NSAL0300SMsg is generated.
	 */
	public NSAL0300SMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0300SMsg is constructor.
	 * The initialization when the instance of NSAL0300SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0300SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		A = (business.blap.NSAL0300.NSAL0300_ASMsgArray)newMsgArray("A");
		X = (business.blap.NSAL0300.NSAL0300_XSMsgArray)newMsgArray("X");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0300SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0300SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"A", "A", null, "2000", "business.blap.NSAL0300.NSAL0300_ASMsgArray", null, null},
	
	{"X", "X", null, "2000", "business.blap.NSAL0300.NSAL0300_XSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

		null,	//A
		null,	//X
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
