//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20221227111436000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8840_PCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NYEL8840;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NYEL8840 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NYEL8840_PCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_POP_PRM*/
	public final EZDCStringItem              xxPopPrm;


	/**
	 * NYEL8840_PCMsg is constructor.
	 * The initialization when the instance of NYEL8840_PCMsg is generated.
	 */
	public NYEL8840_PCMsg() {
		this(false, -1);
	}

	/**
	 * NYEL8840_PCMsg is constructor.
	 * The initialization when the instance of NYEL8840_PCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYEL8840_PCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPopPrm = (EZDCStringItem)newItem("xxPopPrm");
	}

	/**
	 * get the type of array which is stored
	 * @return NYEL8840_PCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYEL8840_PCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxPopPrm", "xxPopPrm", null, null, TYPE_HANKAKUEISU, "300", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm
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

