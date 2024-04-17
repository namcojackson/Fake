//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170207133630000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0230SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0230;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0230 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0230SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** A*/
	public final business.blap.NMAL0230.NMAL0230_ASMsgArray              A;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;


	/**
	 * NMAL0230SMsg is constructor.
	 * The initialization when the instance of NMAL0230SMsg is generated.
	 */
	public NMAL0230SMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0230SMsg is constructor.
	 * The initialization when the instance of NMAL0230SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0230SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		A = (business.blap.NMAL0230.NMAL0230_ASMsgArray)newMsgArray("A");
		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0230SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0230SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"A", "A", null, "1000", "business.blap.NMAL0230.NMAL0230_ASMsgArray", null, null},
	
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

		null,	//A
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
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
