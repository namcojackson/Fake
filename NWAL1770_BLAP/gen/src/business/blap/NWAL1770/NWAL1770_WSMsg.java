//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160125093954000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1770_WSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1770;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1770 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1770_WSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CTAC_PSN_EML_ADDR*/
	public final EZDSStringItem              ctacPsnEmlAddr;


	/**
	 * NWAL1770_WSMsg is constructor.
	 * The initialization when the instance of NWAL1770_WSMsg is generated.
	 */
	public NWAL1770_WSMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1770_WSMsg is constructor.
	 * The initialization when the instance of NWAL1770_WSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1770_WSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ctacPsnEmlAddr = (EZDSStringItem)newItem("ctacPsnEmlAddr");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1770_WSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1770_WSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ctacPsnEmlAddr", "ctacPsnEmlAddr", null, null, TYPE_HANKAKUEISU, "320", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CTAC_PSN_EML_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnEmlAddr
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

