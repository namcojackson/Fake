//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160914093253000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWWL0020_PBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWWL0020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWWL0020 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWWL0020_PBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_SCR_COL_VAL_TXT_1*/
	public final EZDBStringItem              xxComnScrColValTxt_1;


	/**
	 * NWWL0020_PBMsg is constructor.
	 * The initialization when the instance of NWWL0020_PBMsg is generated.
	 */
	public NWWL0020_PBMsg() {
		this(false, -1);
	}

	/**
	 * NWWL0020_PBMsg is constructor.
	 * The initialization when the instance of NWWL0020_PBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWWL0020_PBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnScrColValTxt_1 = (EZDBStringItem)newItem("xxComnScrColValTxt_1");
	}

	/**
	 * get the type of array which is stored
	 * @return NWWL0020_PBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWWL0020_PBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnScrColValTxt_1", "xxComnScrColValTxt_1", "1", null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_1
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

