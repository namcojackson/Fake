//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230808112258000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1770_ZBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1770;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1770 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1770_ZBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_FUNC_ID*/
	public final EZDBStringItem              xxFuncId;


	/**
	 * NWAL1770_ZBMsg is constructor.
	 * The initialization when the instance of NWAL1770_ZBMsg is generated.
	 */
	public NWAL1770_ZBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1770_ZBMsg is constructor.
	 * The initialization when the instance of NWAL1770_ZBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1770_ZBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxFuncId = (EZDBStringItem)newItem("xxFuncId");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1770_ZBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1770_ZBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxFuncId", "xxFuncId", null, null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_FUNC_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFuncId
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

