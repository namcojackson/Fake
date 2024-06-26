//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170530120949000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0870SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0870;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0870 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0870SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** A*/
	public final business.blap.NSAL0870.NSAL0870_ASMsgArray              A;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;


	/**
	 * NSAL0870SMsg is constructor.
	 * The initialization when the instance of NSAL0870SMsg is generated.
	 */
	public NSAL0870SMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0870SMsg is constructor.
	 * The initialization when the instance of NSAL0870SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0870SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		A = (business.blap.NSAL0870.NSAL0870_ASMsgArray)newMsgArray("A");
		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0870SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0870SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"A", "A", null, "1000", "business.blap.NSAL0870.NSAL0870_ASMsgArray", null, null},
	
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

