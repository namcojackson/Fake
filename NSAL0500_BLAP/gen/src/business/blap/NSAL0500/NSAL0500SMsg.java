//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20181211191308000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0500SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0500;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0500 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0500SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** A*/
	public final business.blap.NSAL0500.NSAL0500_ASMsgArray              A;

    /** B*/
	public final business.blap.NSAL0500.NSAL0500_BSMsgArray              B;

    /** C*/
	public final business.blap.NSAL0500.NSAL0500_CSMsgArray              C;


	/**
	 * NSAL0500SMsg is constructor.
	 * The initialization when the instance of NSAL0500SMsg is generated.
	 */
	public NSAL0500SMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0500SMsg is constructor.
	 * The initialization when the instance of NSAL0500SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0500SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		A = (business.blap.NSAL0500.NSAL0500_ASMsgArray)newMsgArray("A");
		B = (business.blap.NSAL0500.NSAL0500_BSMsgArray)newMsgArray("B");
		C = (business.blap.NSAL0500.NSAL0500_CSMsgArray)newMsgArray("C");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0500SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0500SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"A", "A", null, "200", "business.blap.NSAL0500.NSAL0500_ASMsgArray", null, null},
	
	{"B", "B", null, "200", "business.blap.NSAL0500.NSAL0500_BSMsgArray", null, null},
	
	{"C", "C", null, "200", "business.blap.NSAL0500.NSAL0500_CSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

		null,	//A
		null,	//B
		null,	//C
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

