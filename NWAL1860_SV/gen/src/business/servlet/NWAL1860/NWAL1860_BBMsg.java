//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20151221202421000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1860_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1860;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1860 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1860_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_POP_PRM_B*/
	public final EZDBStringItem              xxPopPrm_B;


	/**
	 * NWAL1860_BBMsg is constructor.
	 * The initialization when the instance of NWAL1860_BBMsg is generated.
	 */
	public NWAL1860_BBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1860_BBMsg is constructor.
	 * The initialization when the instance of NWAL1860_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1860_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPopPrm_B = (EZDBStringItem)newItem("xxPopPrm_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1860_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1860_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxPopPrm_B", "xxPopPrm_B", "B", null, TYPE_HANKAKUEISU, "300", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_B
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

