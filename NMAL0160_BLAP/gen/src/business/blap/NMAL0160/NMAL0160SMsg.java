//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161110091804000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0160SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0160;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0160 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0160SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** A*/
	public final business.blap.NMAL0160.NMAL0160_ASMsgArray              A;

    /** B*/
	public final business.blap.NMAL0160.NMAL0160_BSMsgArray              B;


	/**
	 * NMAL0160SMsg is constructor.
	 * The initialization when the instance of NMAL0160SMsg is generated.
	 */
	public NMAL0160SMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0160SMsg is constructor.
	 * The initialization when the instance of NMAL0160SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0160SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		A = (business.blap.NMAL0160.NMAL0160_ASMsgArray)newMsgArray("A");
		B = (business.blap.NMAL0160.NMAL0160_BSMsgArray)newMsgArray("B");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0160SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0160SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"A", "A", null, "10000", "business.blap.NMAL0160.NMAL0160_ASMsgArray", null, null},
	
	{"B", "B", null, "10000", "business.blap.NMAL0160.NMAL0160_BSMsgArray", null, null},
	
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

