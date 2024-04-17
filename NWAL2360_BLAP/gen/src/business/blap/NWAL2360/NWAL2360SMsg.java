//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160923132303000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2360SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2360;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2360 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2360SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_IMPT_ORD_PK*/
	public final EZDSBigDecimalItem              dsImptOrdPk;

    /** A*/
	public final business.blap.NWAL2360.NWAL2360_ASMsgArray              A;


	/**
	 * NWAL2360SMsg is constructor.
	 * The initialization when the instance of NWAL2360SMsg is generated.
	 */
	public NWAL2360SMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2360SMsg is constructor.
	 * The initialization when the instance of NWAL2360SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2360SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsImptOrdPk = (EZDSBigDecimalItem)newItem("dsImptOrdPk");
		A = (business.blap.NWAL2360.NWAL2360_ASMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2360SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2360SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsImptOrdPk", "dsImptOrdPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"A", "A", null, "200", "business.blap.NWAL2360.NWAL2360_ASMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_IMPT_ORD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptOrdPk
		null,	//A
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
