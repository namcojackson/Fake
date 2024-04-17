//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20230320154401000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC133001_xxMsgIdListPMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSZC133001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC133001_xxMsgIdListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_MSG_ID*/
	public final EZDPStringItem              xxMsgId;

    /** XX_MSG_PRM_TXT_0*/
	public final EZDPStringItem              xxMsgPrmTxt_0;


	/**
	 * NSZC133001_xxMsgIdListPMsg is constructor.
	 * The initialization when the instance of NSZC133001_xxMsgIdListPMsg is generated.
	 */
	public NSZC133001_xxMsgIdListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC133001_xxMsgIdListPMsg is constructor.
	 * The initialization when the instance of NSZC133001_xxMsgIdListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC133001_xxMsgIdListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxMsgId = (EZDPStringItem)newItem("xxMsgId");
		xxMsgPrmTxt_0 = (EZDPStringItem)newItem("xxMsgPrmTxt_0");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC133001_xxMsgIdListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC133001_xxMsgIdListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxMsgId", "xxMsgId", null, null, TYPE_HANKAKUEISU, "9", null},
	{"xxMsgPrmTxt_0", "xxMsgPrmTxt_0", "0", null, TYPE_HANKAKUEISU, "100", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_MSG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgId
        {"XX_MSG_PRM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgPrmTxt_0
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

