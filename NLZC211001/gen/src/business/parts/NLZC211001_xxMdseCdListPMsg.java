//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20100924102421000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC211001_xxMdseCdListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLZC211001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLZC211001_xxMdseCdListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;


	/**
	 * NLZC211001_xxMdseCdListPMsg is constructor.
	 * The initialization when the instance of NLZC211001_xxMdseCdListPMsg is generated.
	 */
	public NLZC211001_xxMdseCdListPMsg() {
		this(false, -1);
	}

	/**
	 * NLZC211001_xxMdseCdListPMsg is constructor.
	 * The initialization when the instance of NLZC211001_xxMdseCdListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLZC211001_xxMdseCdListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd = (EZDPStringItem)newItem("mdseCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NLZC211001_xxMdseCdListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLZC211001_xxMdseCdListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
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

