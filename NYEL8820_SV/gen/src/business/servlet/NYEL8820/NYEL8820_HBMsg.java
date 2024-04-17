//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20150708170920000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8820_HBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NYEL8820;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NYEL8820 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NYEL8820_HBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ASG_TO_H*/
	public final EZDBStringItem              asgTo_H;


	/**
	 * NYEL8820_HBMsg is constructor.
	 * The initialization when the instance of NYEL8820_HBMsg is generated.
	 */
	public NYEL8820_HBMsg() {
		this(false, -1);
	}

	/**
	 * NYEL8820_HBMsg is constructor.
	 * The initialization when the instance of NYEL8820_HBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYEL8820_HBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		asgTo_H = (EZDBStringItem)newItem("asgTo_H");
	}

	/**
	 * get the type of array which is stored
	 * @return NYEL8820_HBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYEL8820_HBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"asgTo_H", "asgTo_H", "H", null, TYPE_HANKAKUEISU, "255", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ASG_TO",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asgTo_H
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

