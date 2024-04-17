//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20150817190458000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8850SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NYEL8850;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NYEL8850 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NYEL8850SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** A*/
	public final business.blap.NYEL8850.NYEL8850_ASMsgArray              A;

    /** B*/
	public final business.blap.NYEL8850.NYEL8850_BSMsgArray              B;


	/**
	 * NYEL8850SMsg is constructor.
	 * The initialization when the instance of NYEL8850SMsg is generated.
	 */
	public NYEL8850SMsg() {
		this(false, -1);
	}

	/**
	 * NYEL8850SMsg is constructor.
	 * The initialization when the instance of NYEL8850SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYEL8850SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		A = (business.blap.NYEL8850.NYEL8850_ASMsgArray)newMsgArray("A");
		B = (business.blap.NYEL8850.NYEL8850_BSMsgArray)newMsgArray("B");
	}

	/**
	 * get the type of array which is stored
	 * @return NYEL8850SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYEL8850SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"A", "A", null, "2000", "business.blap.NYEL8850.NYEL8850_ASMsgArray", null, null},
	
	{"B", "B", null, "2000", "business.blap.NYEL8850.NYEL8850_BSMsgArray", null, null},
	
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
