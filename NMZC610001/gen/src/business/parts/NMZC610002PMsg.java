//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20160613161041000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMZC610002PMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMZC610002 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMZC610002PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** A*/
	public final business.parts.NMZC610002_APMsgArray              A;

    /** B*/
	public final business.parts.NMZC610002_BPMsgArray              B;


	/**
	 * NMZC610002PMsg is constructor.
	 * The initialization when the instance of NMZC610002PMsg is generated.
	 */
	public NMZC610002PMsg() {
		this(false, -1);
	}

	/**
	 * NMZC610002PMsg is constructor.
	 * The initialization when the instance of NMZC610002PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMZC610002PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		A = (business.parts.NMZC610002_APMsgArray)newMsgArray("A");
		B = (business.parts.NMZC610002_BPMsgArray)newMsgArray("B");
	}

	/**
	 * get the type of array which is stored
	 * @return NMZC610002PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMZC610002PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"A", "A", null, "20", "business.parts.NMZC610002_APMsgArray", null, null},
	
	{"B", "B", null, "20", "business.parts.NMZC610002_BPMsgArray", null, null},
	
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
