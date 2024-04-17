//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230227160345000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLAL2030SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLAL2030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLAL2030 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLAL2030SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;

    /** A*/
	public final business.blap.NLAL2030.NLAL2030_ASMsgArray              A;

    /** B*/
	public final business.blap.NLAL2030.NLAL2030_BSMsgArray              B;

    /** C*/
	public final business.blap.NLAL2030.NLAL2030_CSMsgArray              C;

    /** D*/
	public final business.blap.NLAL2030.NLAL2030_DSMsgArray              D;


	/**
	 * NLAL2030SMsg is constructor.
	 * The initialization when the instance of NLAL2030SMsg is generated.
	 */
	public NLAL2030SMsg() {
		this(false, -1);
	}

	/**
	 * NLAL2030SMsg is constructor.
	 * The initialization when the instance of NLAL2030SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLAL2030SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
		A = (business.blap.NLAL2030.NLAL2030_ASMsgArray)newMsgArray("A");
		B = (business.blap.NLAL2030.NLAL2030_BSMsgArray)newMsgArray("B");
		C = (business.blap.NLAL2030.NLAL2030_CSMsgArray)newMsgArray("C");
		D = (business.blap.NLAL2030.NLAL2030_DSMsgArray)newMsgArray("D");
	}

	/**
	 * get the type of array which is stored
	 * @return NLAL2030SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLAL2030SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"A", "A", null, "1000", "business.blap.NLAL2030.NLAL2030_ASMsgArray", null, null},
	
	{"B", "B", null, "1000", "business.blap.NLAL2030.NLAL2030_BSMsgArray", null, null},
	
	{"C", "C", null, "100000", "business.blap.NLAL2030.NLAL2030_CSMsgArray", null, null},
	
	{"D", "D", null, "100000", "business.blap.NLAL2030.NLAL2030_DSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
		null,	//A
		null,	//B
		null,	//C
		null,	//D
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

