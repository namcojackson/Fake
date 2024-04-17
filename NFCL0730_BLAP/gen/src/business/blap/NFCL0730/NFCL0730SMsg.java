//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180117095258000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL0730SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL0730;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL0730 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL0730SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** A*/
	public final business.blap.NFCL0730.NFCL0730_ASMsgArray              A;

    /** B*/
	public final business.blap.NFCL0730.NFCL0730_BSMsgArray              B;


	/**
	 * NFCL0730SMsg is constructor.
	 * The initialization when the instance of NFCL0730SMsg is generated.
	 */
	public NFCL0730SMsg() {
		this(false, -1);
	}

	/**
	 * NFCL0730SMsg is constructor.
	 * The initialization when the instance of NFCL0730SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL0730SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		A = (business.blap.NFCL0730.NFCL0730_ASMsgArray)newMsgArray("A");
		B = (business.blap.NFCL0730.NFCL0730_BSMsgArray)newMsgArray("B");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL0730SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL0730SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"A", "A", null, "200", "business.blap.NFCL0730.NFCL0730_ASMsgArray", null, null},
	
	{"B", "B", null, "200", "business.blap.NFCL0730.NFCL0730_BSMsgArray", null, null},
	
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

