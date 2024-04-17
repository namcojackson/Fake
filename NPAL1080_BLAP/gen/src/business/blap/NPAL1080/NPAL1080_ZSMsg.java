//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230317104141000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1080_ZSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1080 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1080_ZSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_POP_PRM_AT*/
	public final EZDSStringItem              xxPopPrm_AT;


	/**
	 * NPAL1080_ZSMsg is constructor.
	 * The initialization when the instance of NPAL1080_ZSMsg is generated.
	 */
	public NPAL1080_ZSMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1080_ZSMsg is constructor.
	 * The initialization when the instance of NPAL1080_ZSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1080_ZSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPopPrm_AT = (EZDSStringItem)newItem("xxPopPrm_AT");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1080_ZSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1080_ZSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxPopPrm_AT", "xxPopPrm_AT", "AT", null, TYPE_HANKAKUEISU, "300", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_AT
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
