//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180514112552000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2420_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2420;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2420 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2420_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** CTAC_PSN_EML_ADDR_A*/
	public final EZDBStringItem              ctacPsnEmlAddr_A;


	/**
	 * NWAL2420_ABMsg is constructor.
	 * The initialization when the instance of NWAL2420_ABMsg is generated.
	 */
	public NWAL2420_ABMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2420_ABMsg is constructor.
	 * The initialization when the instance of NWAL2420_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2420_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		ctacPsnEmlAddr_A = (EZDBStringItem)newItem("ctacPsnEmlAddr_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2420_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2420_ABMsgArray();
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

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"CTAC_PSN_EML_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnEmlAddr_A
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
