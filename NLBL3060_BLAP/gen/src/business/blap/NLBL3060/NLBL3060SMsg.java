//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20231027190159000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3060SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3060 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3060SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** A*/
	public final business.blap.NLBL3060.NLBL3060_ASMsgArray              A;

    /** B*/
	public final business.blap.NLBL3060.NLBL3060_BSMsgArray              B;

    /** XX_FILE_DATA_UP*/
	public final EZDSMimeSourceItem              xxFileData_UP;

    /** XX_FILE_DATA_DL*/
	public final EZDSMimeSourceItem              xxFileData_DL;


	/**
	 * NLBL3060SMsg is constructor.
	 * The initialization when the instance of NLBL3060SMsg is generated.
	 */
	public NLBL3060SMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3060SMsg is constructor.
	 * The initialization when the instance of NLBL3060SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3060SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		A = (business.blap.NLBL3060.NLBL3060_ASMsgArray)newMsgArray("A");
		B = (business.blap.NLBL3060.NLBL3060_BSMsgArray)newMsgArray("B");
		xxFileData_UP = (EZDSMimeSourceItem)newItem("xxFileData_UP");
		xxFileData_DL = (EZDSMimeSourceItem)newItem("xxFileData_DL");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3060SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3060SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"A", "A", null, "1000", "business.blap.NLBL3060.NLBL3060_ASMsgArray", null, null},
	
	{"B", "B", null, "200", "business.blap.NLBL3060.NLBL3060_BSMsgArray", null, null},
	
	{"xxFileData_UP", "xxFileData_UP", "UP", null, TYPE_UPLOAD, null, null},
	{"xxFileData_DL", "xxFileData_DL", "DL", null, TYPE_UPLOAD, null, null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

		null,	//A
		null,	//B
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_UP
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_DL
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

