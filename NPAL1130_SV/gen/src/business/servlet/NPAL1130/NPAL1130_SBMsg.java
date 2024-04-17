//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230417095838000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1130_SBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1130;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1130 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1130_SBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** STK_STS_CD_S0*/
	public final EZDBStringItem              stkStsCd_S0;


	/**
	 * NPAL1130_SBMsg is constructor.
	 * The initialization when the instance of NPAL1130_SBMsg is generated.
	 */
	public NPAL1130_SBMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1130_SBMsg is constructor.
	 * The initialization when the instance of NPAL1130_SBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1130_SBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		stkStsCd_S0 = (EZDBStringItem)newItem("stkStsCd_S0");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1130_SBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1130_SBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"stkStsCd_S0", "stkStsCd_S0", "S0", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"STK_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd_S0
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
