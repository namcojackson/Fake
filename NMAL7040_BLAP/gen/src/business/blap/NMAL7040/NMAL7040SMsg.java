//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20181127195407000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7040SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7040 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7040SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition

	/**
	 * NMAL7040SMsg is constructor.
	 * The initialization when the instance of NMAL7040SMsg is generated.
	 */
	public NMAL7040SMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7040SMsg is constructor.
	 * The initialization when the instance of NMAL7040SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7040SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7040SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7040SMsgArray();
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

