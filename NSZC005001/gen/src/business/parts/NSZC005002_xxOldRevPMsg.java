//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151102152611000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC005002_xxOldRevPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC005002 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC005002_xxOldRevPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** INVTY_AVAL_QTY*/
	public final EZDPBigDecimalItem              invtyAvalQty;


	/**
	 * NSZC005002_xxOldRevPMsg is constructor.
	 * The initialization when the instance of NSZC005002_xxOldRevPMsg is generated.
	 */
	public NSZC005002_xxOldRevPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC005002_xxOldRevPMsg is constructor.
	 * The initialization when the instance of NSZC005002_xxOldRevPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC005002_xxOldRevPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd = (EZDPStringItem)newItem("mdseCd");
		invtyAvalQty = (EZDPBigDecimalItem)newItem("invtyAvalQty");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC005002_xxOldRevPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC005002_xxOldRevPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"invtyAvalQty", "invtyAvalQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"INVTY_AVAL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyAvalQty
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

