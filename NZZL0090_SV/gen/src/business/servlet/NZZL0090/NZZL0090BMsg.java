//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20220203170156000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NZZL0090BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NZZL0090;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NZZL0090 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NZZL0090BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_POP_PRM*/
	public final EZDBStringItem              xxPopPrm;


	/**
	 * NZZL0090BMsg is constructor.
	 * The initialization when the instance of NZZL0090BMsg is generated.
	 */
	public NZZL0090BMsg() {
		this(false, -1);
	}

	/**
	 * NZZL0090BMsg is constructor.
	 * The initialization when the instance of NZZL0090BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NZZL0090BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPopPrm = (EZDBStringItem)newItem("xxPopPrm");
	}

	/**
	 * get the type of array which is stored
	 * @return NZZL0090BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NZZL0090BMsgArray();
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

        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm
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

