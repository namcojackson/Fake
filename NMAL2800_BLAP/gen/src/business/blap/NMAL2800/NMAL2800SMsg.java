//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180328155319000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2800SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2800;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2800 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2800SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SRCH_OPT_PK*/
	public final EZDSBigDecimalItem              srchOptPk;

    /** A*/
	public final business.blap.NMAL2800.NMAL2800_ASMsgArray              A;

    /** B*/
	public final business.blap.NMAL2800.NMAL2800_BSMsgArray              B;


	/**
	 * NMAL2800SMsg is constructor.
	 * The initialization when the instance of NMAL2800SMsg is generated.
	 */
	public NMAL2800SMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2800SMsg is constructor.
	 * The initialization when the instance of NMAL2800SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2800SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		srchOptPk = (EZDSBigDecimalItem)newItem("srchOptPk");
		A = (business.blap.NMAL2800.NMAL2800_ASMsgArray)newMsgArray("A");
		B = (business.blap.NMAL2800.NMAL2800_BSMsgArray)newMsgArray("B");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2800SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2800SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"srchOptPk", "srchOptPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"A", "A", null, "2000", "business.blap.NMAL2800.NMAL2800_ASMsgArray", null, null},
	
	{"B", "B", null, "2000", "business.blap.NMAL2800.NMAL2800_BSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk
		null,	//A
		null,	//B
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
