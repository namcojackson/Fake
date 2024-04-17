//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230316094857000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1080_OBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1080 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1080_OBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_POP_PRM_O1*/
	public final EZDBStringItem              xxPopPrm_O1;


	/**
	 * NPAL1080_OBMsg is constructor.
	 * The initialization when the instance of NPAL1080_OBMsg is generated.
	 */
	public NPAL1080_OBMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1080_OBMsg is constructor.
	 * The initialization when the instance of NPAL1080_OBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1080_OBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPopPrm_O1 = (EZDBStringItem)newItem("xxPopPrm_O1");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1080_OBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1080_OBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxPopPrm_O1", "xxPopPrm_O1", "O1", null, TYPE_HANKAKUEISU, "300", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_O1
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

