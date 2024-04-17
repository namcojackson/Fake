//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160627164911000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8860_RCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NYEL8860;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NYEL8860 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NYEL8860_RCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_POP_PRM_P1*/
	public final EZDCStringItem              xxPopPrm_P1;

    /** XX_POP_PRM_P2*/
	public final EZDCStringItem              xxPopPrm_P2;

    /** XX_POP_PRM_P3*/
	public final EZDCStringItem              xxPopPrm_P3;


	/**
	 * NYEL8860_RCMsg is constructor.
	 * The initialization when the instance of NYEL8860_RCMsg is generated.
	 */
	public NYEL8860_RCMsg() {
		this(false, -1);
	}

	/**
	 * NYEL8860_RCMsg is constructor.
	 * The initialization when the instance of NYEL8860_RCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYEL8860_RCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPopPrm_P1 = (EZDCStringItem)newItem("xxPopPrm_P1");
		xxPopPrm_P2 = (EZDCStringItem)newItem("xxPopPrm_P2");
		xxPopPrm_P3 = (EZDCStringItem)newItem("xxPopPrm_P3");
	}

	/**
	 * get the type of array which is stored
	 * @return NYEL8860_RCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYEL8860_RCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxPopPrm_P1", "xxPopPrm_P1", "P1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P2", "xxPopPrm_P2", "P2", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P3", "xxPopPrm_P3", "P3", null, TYPE_HANKAKUEISU, "300", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P2
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P3
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

