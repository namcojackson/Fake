//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190730204018000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1020_PCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1020 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1020_PCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_MACH_MSTR_PK_PD*/
	public final EZDCBigDecimalItem              svcMachMstrPk_PD;


	/**
	 * NSAL1020_PCMsg is constructor.
	 * The initialization when the instance of NSAL1020_PCMsg is generated.
	 */
	public NSAL1020_PCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1020_PCMsg is constructor.
	 * The initialization when the instance of NSAL1020_PCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1020_PCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcMachMstrPk_PD = (EZDCBigDecimalItem)newItem("svcMachMstrPk_PD");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1020_PCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1020_PCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcMachMstrPk_PD", "svcMachMstrPk_PD", "PD", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_PD
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

