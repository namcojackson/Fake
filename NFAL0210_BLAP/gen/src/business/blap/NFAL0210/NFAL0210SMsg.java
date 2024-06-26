//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160615113719000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0210SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFAL0210;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFAL0210 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NFAL0210SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** A*/
	public final business.blap.NFAL0210.NFAL0210_ASMsgArray              A;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;


	/**
	 * NFAL0210SMsg is constructor.
	 * The initialization when the instance of NFAL0210SMsg is generated.
	 */
	public NFAL0210SMsg() {
		this(false, -1);
	}

	/**
	 * NFAL0210SMsg is constructor.
	 * The initialization when the instance of NFAL0210SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFAL0210SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		A = (business.blap.NFAL0210.NFAL0210_ASMsgArray)newMsgArray("A");
		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NFAL0210SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFAL0210SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"A", "A", null, "2000", "business.blap.NFAL0210.NFAL0210_ASMsgArray", null, null},
	
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

