//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170530163151000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1880_HSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1880;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1880 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1880_HSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_FUNC_ID*/
	public final EZDSStringItem              xxFuncId;


	/**
	 * NWAL1880_HSMsg is constructor.
	 * The initialization when the instance of NWAL1880_HSMsg is generated.
	 */
	public NWAL1880_HSMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1880_HSMsg is constructor.
	 * The initialization when the instance of NWAL1880_HSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1880_HSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxFuncId = (EZDSStringItem)newItem("xxFuncId");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1880_HSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1880_HSMsgArray();
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

        {"XX_FUNC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFuncId
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

