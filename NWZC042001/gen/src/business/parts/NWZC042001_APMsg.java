//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20090901111250000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC042001_APMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC042001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC042001_APMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TXT_TP_CD*/
	public final EZDPStringItem              txtTpCd;

    /** MSG_TXT_INFO_TXT*/
	public final EZDPStringItem              msgTxtInfoTxt;


	/**
	 * NWZC042001_APMsg is constructor.
	 * The initialization when the instance of NWZC042001_APMsg is generated.
	 */
	public NWZC042001_APMsg() {
		this(false, -1);
	}

	/**
	 * NWZC042001_APMsg is constructor.
	 * The initialization when the instance of NWZC042001_APMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC042001_APMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		txtTpCd = (EZDPStringItem)newItem("txtTpCd");
		msgTxtInfoTxt = (EZDPStringItem)newItem("msgTxtInfoTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC042001_APMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC042001_APMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"txtTpCd", "txtTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"msgTxtInfoTxt", "msgTxtInfoTxt", null, null, TYPE_HANKAKUEISU, "65", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TXT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//txtTpCd
        {"MSG_TXT_INFO_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//msgTxtInfoTxt
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

