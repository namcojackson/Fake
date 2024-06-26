//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160329184855000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1740SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1740;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1740 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1740SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** A*/
	public final business.blap.NWAL1740.NWAL1740_ASMsgArray              A;

    /** B*/
	public final business.blap.NWAL1740.NWAL1740_BSMsgArray              B;

    /** C*/
	public final business.blap.NWAL1740.NWAL1740_CSMsgArray              C;


	/**
	 * NWAL1740SMsg is constructor.
	 * The initialization when the instance of NWAL1740SMsg is generated.
	 */
	public NWAL1740SMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1740SMsg is constructor.
	 * The initialization when the instance of NWAL1740SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1740SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		A = (business.blap.NWAL1740.NWAL1740_ASMsgArray)newMsgArray("A");
		B = (business.blap.NWAL1740.NWAL1740_BSMsgArray)newMsgArray("B");
		C = (business.blap.NWAL1740.NWAL1740_CSMsgArray)newMsgArray("C");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1740SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1740SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"A", "A", null, "200", "business.blap.NWAL1740.NWAL1740_ASMsgArray", null, null},
	
	{"B", "B", null, "200", "business.blap.NWAL1740.NWAL1740_BSMsgArray", null, null},
	
	{"C", "C", null, "200", "business.blap.NWAL1740.NWAL1740_CSMsgArray", null, null},
	
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

