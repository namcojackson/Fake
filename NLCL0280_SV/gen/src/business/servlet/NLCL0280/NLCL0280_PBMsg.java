//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230406162543000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0280_PBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLCL0280;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL0280 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL0280_PBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_POP_PRM*/
	public final EZDBStringItem              xxPopPrm;


	/**
	 * NLCL0280_PBMsg is constructor.
	 * The initialization when the instance of NLCL0280_PBMsg is generated.
	 */
	public NLCL0280_PBMsg() {
		this(false, -1);
	}

	/**
	 * NLCL0280_PBMsg is constructor.
	 * The initialization when the instance of NLCL0280_PBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL0280_PBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPopPrm = (EZDBStringItem)newItem("xxPopPrm");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL0280_PBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL0280_PBMsgArray();
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

