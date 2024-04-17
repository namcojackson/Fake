//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170901131729000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2460SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2460;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2460 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2460SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;

    /** A*/
	public final business.blap.NMAL2460.NMAL2460_ASMsgArray              A;

    /** B*/
	public final business.blap.NMAL2460.NMAL2460_BSMsgArray              B;

    /** C*/
	public final business.blap.NMAL2460.NMAL2460_CSMsgArray              C;


	/**
	 * NMAL2460SMsg is constructor.
	 * The initialization when the instance of NMAL2460SMsg is generated.
	 */
	public NMAL2460SMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2460SMsg is constructor.
	 * The initialization when the instance of NMAL2460SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2460SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
		A = (business.blap.NMAL2460.NMAL2460_ASMsgArray)newMsgArray("A");
		B = (business.blap.NMAL2460.NMAL2460_BSMsgArray)newMsgArray("B");
		C = (business.blap.NMAL2460.NMAL2460_CSMsgArray)newMsgArray("C");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2460SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2460SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"A", "A", null, "200", "business.blap.NMAL2460.NMAL2460_ASMsgArray", null, null},
	
	{"B", "B", null, "200", "business.blap.NMAL2460.NMAL2460_BSMsgArray", null, null},
	
	{"C", "C", null, "200", "business.blap.NMAL2460.NMAL2460_CSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
		null,	//A
		null,	//B
		null,	//C
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

