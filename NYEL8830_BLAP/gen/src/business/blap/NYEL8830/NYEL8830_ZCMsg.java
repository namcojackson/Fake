//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180516094218000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8830_ZCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NYEL8830;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NYEL8830 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NYEL8830_ZCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_POP_PRM_Z1*/
	public final EZDCStringItem              xxPopPrm_Z1;

    /** XX_POP_PRM_Z2*/
	public final EZDCStringItem              xxPopPrm_Z2;

    /** XX_POP_PRM_Z3*/
	public final EZDCStringItem              xxPopPrm_Z3;

    /** XX_POP_PRM_Z4*/
	public final EZDCStringItem              xxPopPrm_Z4;

    /** XX_POP_PRM_Z5*/
	public final EZDCStringItem              xxPopPrm_Z5;


	/**
	 * NYEL8830_ZCMsg is constructor.
	 * The initialization when the instance of NYEL8830_ZCMsg is generated.
	 */
	public NYEL8830_ZCMsg() {
		this(false, -1);
	}

	/**
	 * NYEL8830_ZCMsg is constructor.
	 * The initialization when the instance of NYEL8830_ZCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYEL8830_ZCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPopPrm_Z1 = (EZDCStringItem)newItem("xxPopPrm_Z1");
		xxPopPrm_Z2 = (EZDCStringItem)newItem("xxPopPrm_Z2");
		xxPopPrm_Z3 = (EZDCStringItem)newItem("xxPopPrm_Z3");
		xxPopPrm_Z4 = (EZDCStringItem)newItem("xxPopPrm_Z4");
		xxPopPrm_Z5 = (EZDCStringItem)newItem("xxPopPrm_Z5");
	}

	/**
	 * get the type of array which is stored
	 * @return NYEL8830_ZCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYEL8830_ZCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxPopPrm_Z1", "xxPopPrm_Z1", "Z1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_Z2", "xxPopPrm_Z2", "Z2", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_Z3", "xxPopPrm_Z3", "Z3", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_Z4", "xxPopPrm_Z4", "Z4", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_Z5", "xxPopPrm_Z5", "Z5", null, TYPE_HANKAKUEISU, "300", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_Z1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_Z2
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_Z3
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_Z4
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_Z5
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

