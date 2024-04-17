//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20191031133223000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6800SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6800;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6800 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6800SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;

    /** A*/
	public final business.blap.NMAL6800.NMAL6800_ASMsgArray              A;


	/**
	 * NMAL6800SMsg is constructor.
	 * The initialization when the instance of NMAL6800SMsg is generated.
	 */
	public NMAL6800SMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6800SMsg is constructor.
	 * The initialization when the instance of NMAL6800SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6800SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
		A = (business.blap.NMAL6800.NMAL6800_ASMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6800SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6800SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"A", "A", null, "1000", "business.blap.NMAL6800.NMAL6800_ASMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
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
