//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20130531130524000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC004001_xxSvcTasNumListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC004001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC004001_xxSvcTasNumListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_TASK_NUM*/
	public final EZDPStringItem              svcTaskNum;


	/**
	 * NSZC004001_xxSvcTasNumListPMsg is constructor.
	 * The initialization when the instance of NSZC004001_xxSvcTasNumListPMsg is generated.
	 */
	public NSZC004001_xxSvcTasNumListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC004001_xxSvcTasNumListPMsg is constructor.
	 * The initialization when the instance of NSZC004001_xxSvcTasNumListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC004001_xxSvcTasNumListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcTaskNum = (EZDPStringItem)newItem("svcTaskNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC004001_xxSvcTasNumListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC004001_xxSvcTasNumListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcTaskNum", "svcTaskNum", null, null, TYPE_HANKAKUEISU, "10", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum
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

