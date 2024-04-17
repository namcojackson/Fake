//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20240325170116000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1320SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1320;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1320 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1320SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** A*/
	public final business.blap.NSAL1320.NSAL1320_ASMsgArray              A;

    /** B*/
	public final business.blap.NSAL1320.NSAL1320_BSMsgArray              B;


	/**
	 * NSAL1320SMsg is constructor.
	 * The initialization when the instance of NSAL1320SMsg is generated.
	 */
	public NSAL1320SMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1320SMsg is constructor.
	 * The initialization when the instance of NSAL1320SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1320SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		A = (business.blap.NSAL1320.NSAL1320_ASMsgArray)newMsgArray("A");
		B = (business.blap.NSAL1320.NSAL1320_BSMsgArray)newMsgArray("B");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1320SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1320SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"A", "A", null, "200", "business.blap.NSAL1320.NSAL1320_ASMsgArray", null, null},
	
	{"B", "B", null, "200", "business.blap.NSAL1320.NSAL1320_BSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

		null,	//A
		null,	//B
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

