//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190808083216000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3000_QCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3000;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL3000 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3000_QCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CTAC_PNT_PK*/
	public final EZDCBigDecimalItem              dsCtacPntPk;


	/**
	 * NFCL3000_QCMsg is constructor.
	 * The initialization when the instance of NFCL3000_QCMsg is generated.
	 */
	public NFCL3000_QCMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3000_QCMsg is constructor.
	 * The initialization when the instance of NFCL3000_QCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3000_QCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsCtacPntPk = (EZDCBigDecimalItem)newItem("dsCtacPntPk");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3000_QCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3000_QCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsCtacPntPk", "dsCtacPntPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CTAC_PNT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntPk
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
