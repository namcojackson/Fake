//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180514113649000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2420_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2420;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2420 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2420_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDCStringItem              xxChkBox_A;

    /** CTAC_PSN_EML_ADDR_A*/
	public final EZDCStringItem              ctacPsnEmlAddr_A;


	/**
	 * NWAL2420_ACMsg is constructor.
	 * The initialization when the instance of NWAL2420_ACMsg is generated.
	 */
	public NWAL2420_ACMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2420_ACMsg is constructor.
	 * The initialization when the instance of NWAL2420_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2420_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDCStringItem)newItem("xxChkBox_A");
		ctacPsnEmlAddr_A = (EZDCStringItem)newItem("ctacPsnEmlAddr_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2420_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2420_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"ctacPsnEmlAddr_A", "ctacPsnEmlAddr_A", "A", null, TYPE_HANKAKUEISU, "320", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"CTAC_PSN_EML_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnEmlAddr_A
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

