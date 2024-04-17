//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160621092201000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0220_DCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFAL0220;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFAL0220 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFAL0220_DCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MAN_JRNL_ENTRY_DTL_PK*/
	public final EZDCBigDecimalItem              manJrnlEntryDtlPk;


	/**
	 * NFAL0220_DCMsg is constructor.
	 * The initialization when the instance of NFAL0220_DCMsg is generated.
	 */
	public NFAL0220_DCMsg() {
		this(false, -1);
	}

	/**
	 * NFAL0220_DCMsg is constructor.
	 * The initialization when the instance of NFAL0220_DCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFAL0220_DCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		manJrnlEntryDtlPk = (EZDCBigDecimalItem)newItem("manJrnlEntryDtlPk");
	}

	/**
	 * get the type of array which is stored
	 * @return NFAL0220_DCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFAL0220_DCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"manJrnlEntryDtlPk", "manJrnlEntryDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MAN_JRNL_ENTRY_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manJrnlEntryDtlPk
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

