//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160808100804000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2400_PSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2400;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2400 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2400_PSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_POP_PRM_P*/
	public final EZDSStringItem              xxPopPrm_P;


	/**
	 * NWAL2400_PSMsg is constructor.
	 * The initialization when the instance of NWAL2400_PSMsg is generated.
	 */
	public NWAL2400_PSMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2400_PSMsg is constructor.
	 * The initialization when the instance of NWAL2400_PSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2400_PSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPopPrm_P = (EZDSStringItem)newItem("xxPopPrm_P");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2400_PSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2400_PSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxPopPrm_P", "xxPopPrm_P", "P", null, TYPE_HANKAKUEISU, "300", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P
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

