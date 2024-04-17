//This file was automatically generated by Screen Field Definition Document and XLA710010.
// Generated on    :20180801091329000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA710010
/*
 *NFDL0010_MBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFDL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0010 Screen Data Message class.
 * @author
 * @version XLA710010
 */
public class NFDL0010_MBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_POP_PRM*/
	public final EZDBStringItem              xxPopPrm;


	/**
	 * NFDL0010_MBMsg is constructor.
	 * The initialization when the instance of NFDL0010_MBMsg is generated.
	 */
	public NFDL0010_MBMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0010_MBMsg is constructor.
	 * The initialization when the instance of NFDL0010_MBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0010_MBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPopPrm = (EZDBStringItem)newItem("xxPopPrm");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0010_MBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0010_MBMsgArray();
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
