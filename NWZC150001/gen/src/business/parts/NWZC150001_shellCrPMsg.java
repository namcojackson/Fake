//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20150928180816000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC150001_shellCrPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC150001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC150001_shellCrPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_RQST_TP_CD*/
	public final EZDPStringItem              xxRqstTpCd;


	/**
	 * NWZC150001_shellCrPMsg is constructor.
	 * The initialization when the instance of NWZC150001_shellCrPMsg is generated.
	 */
	public NWZC150001_shellCrPMsg() {
		this(false, -1);
	}

	/**
	 * NWZC150001_shellCrPMsg is constructor.
	 * The initialization when the instance of NWZC150001_shellCrPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC150001_shellCrPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRqstTpCd = (EZDPStringItem)newItem("xxRqstTpCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC150001_shellCrPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC150001_shellCrPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRqstTpCd", "xxRqstTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_RQST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTpCd
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
