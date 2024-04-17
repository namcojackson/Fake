//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20081229173029000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZZQ910000PMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZZQ910000 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZZZQ910000PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZBusinessApplicationID*/
	public final EZDPStringItem              ezBusinessID;

    /** A*/
	public final business.parts.ZZZQ910000_APMsgArray              A;


	/**
	 * ZZZQ910000PMsg is constructor.
	 * The initialization when the instance of ZZZQ910000PMsg is generated.
	 */
	public ZZZQ910000PMsg() {
		this(false, -1);
	}

	/**
	 * ZZZQ910000PMsg is constructor.
	 * The initialization when the instance of ZZZQ910000PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZZQ910000PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item
		ezBusinessID = (EZDPStringItem)newItem("ezBusinessID");
		A = (business.parts.ZZZQ910000_APMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZZQ910000PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZZQ910000PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {
	
		// It is defined as {"Key name", "Variabler", "Qualifier", "Array length", "Data type", "Number of digits", and "Number of decimal digits" } etc.
		{"ezBusinessID", "ezBusinessID", null, null, TYPE_HANKAKUEISU, "8", null},
		{"A", "A", null, "200", "business.parts.ZZZQ910000_APMsgArray", null, null},

	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

		// It is defined as {"Message display character string", "Indispensable input paragraph", "Minimum value", "Maximum value", "Division of I/O item/output item", "\", "Comma", "Zero suppression", "Plus display flag", "Number of display item digits", "Number of display fraction part digits", "Division at the date of the edit at the date", "Display pattern of the year of the edit at the date", "0 burials of the edit at the date", and "Separator of the edit at the date" } etc.	
        {"_EZBusinessApplicationID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezBusinessID
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
