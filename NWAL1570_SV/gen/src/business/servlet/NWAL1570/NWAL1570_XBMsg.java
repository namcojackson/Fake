//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20240229113847000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1570_XBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1570;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1570 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1570_XBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** RTRN_LINE_STS_NM_PR*/
	public final EZDBStringItem              rtrnLineStsNm_PR;


	/**
	 * NWAL1570_XBMsg is constructor.
	 * The initialization when the instance of NWAL1570_XBMsg is generated.
	 */
	public NWAL1570_XBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1570_XBMsg is constructor.
	 * The initialization when the instance of NWAL1570_XBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1570_XBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		rtrnLineStsNm_PR = (EZDBStringItem)newItem("rtrnLineStsNm_PR");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1570_XBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1570_XBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"rtrnLineStsNm_PR", "rtrnLineStsNm_PR", "PR", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"RTRN_LINE_STS_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtrnLineStsNm_PR
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

