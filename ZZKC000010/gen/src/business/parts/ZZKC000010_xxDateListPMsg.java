//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20130827115917000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZKC000010_xxDateListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is ZZKC000010 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZZKC000010_xxDateListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZServerCallDateTime*/
	public final EZDPStringItem              ezServerCallTime;


	/**
	 * ZZKC000010_xxDateListPMsg is constructor.
	 * The initialization when the instance of ZZKC000010_xxDateListPMsg is generated.
	 */
	public ZZKC000010_xxDateListPMsg() {
		this(false, -1);
	}

	/**
	 * ZZKC000010_xxDateListPMsg is constructor.
	 * The initialization when the instance of ZZKC000010_xxDateListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZKC000010_xxDateListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezServerCallTime = (EZDPStringItem)newItem("ezServerCallTime");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZKC000010_xxDateListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZKC000010_xxDateListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ezServerCallTime", "ezServerCallTime", null, null, TYPE_HANKAKUEISU, "17", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"_EZServerCallDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezServerCallTime
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

