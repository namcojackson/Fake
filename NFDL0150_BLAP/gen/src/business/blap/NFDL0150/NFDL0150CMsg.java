//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160204084043000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0150CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0150;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0150 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0150CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDCDateItem              slsDt;

    /** XX_RADIO_BTN_A*/
	public final EZDCBigDecimalItem              xxRadioBtn_A;

    /** A*/
	public final business.blap.NFDL0150.NFDL0150_ACMsgArray              A;

    /** D*/
	public final business.blap.NFDL0150.NFDL0150_DCMsgArray              D;


	/**
	 * NFDL0150CMsg is constructor.
	 * The initialization when the instance of NFDL0150CMsg is generated.
	 */
	public NFDL0150CMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0150CMsg is constructor.
	 * The initialization when the instance of NFDL0150CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0150CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		slsDt = (EZDCDateItem)newItem("slsDt");
		xxRadioBtn_A = (EZDCBigDecimalItem)newItem("xxRadioBtn_A");
		A = (business.blap.NFDL0150.NFDL0150_ACMsgArray)newMsgArray("A");
		D = (business.blap.NFDL0150.NFDL0150_DCMsgArray)newMsgArray("D");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0150CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0150CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxRadioBtn_A", "xxRadioBtn_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"A", "A", null, "200", "business.blap.NFDL0150.NFDL0150_ACMsgArray", null, null},
	
	{"D", "D", null, "200", "business.blap.NFDL0150.NFDL0150_DCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn_A
		null,	//A
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
