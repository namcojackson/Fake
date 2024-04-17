//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20200408160135000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMZC004001_xxErrItemListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMZC004001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMZC004001_xxErrItemListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** APP_MSG_TXT*/
	public final EZDPStringItem              appMsgTxt;


	/**
	 * NMZC004001_xxErrItemListPMsg is constructor.
	 * The initialization when the instance of NMZC004001_xxErrItemListPMsg is generated.
	 */
	public NMZC004001_xxErrItemListPMsg() {
		this(false, -1);
	}

	/**
	 * NMZC004001_xxErrItemListPMsg is constructor.
	 * The initialization when the instance of NMZC004001_xxErrItemListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMZC004001_xxErrItemListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd = (EZDPStringItem)newItem("mdseCd");
		appMsgTxt = (EZDPStringItem)newItem("appMsgTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NMZC004001_xxErrItemListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMZC004001_xxErrItemListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"appMsgTxt", "appMsgTxt", null, null, TYPE_HANKAKUEISU, "500", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"APP_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//appMsgTxt
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

