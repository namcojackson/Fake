//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180628172809000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3080CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL3080 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3080CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** INV_NUM*/
	public final EZDCStringItem              invNum;

    /** INV_NUM_H*/
	public final EZDCStringItem              invNum_H;

    /** INV_NUM_OR*/
	public final EZDCStringItem              invNum_OR;

    /** INV_TP_CD_H*/
	public final EZDCStringItem              invTpCd_H;

    /** A*/
	public final business.blap.NFCL3080.NFCL3080_ACMsgArray              A;


	/**
	 * NFCL3080CMsg is constructor.
	 * The initialization when the instance of NFCL3080CMsg is generated.
	 */
	public NFCL3080CMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3080CMsg is constructor.
	 * The initialization when the instance of NFCL3080CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3080CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		invNum = (EZDCStringItem)newItem("invNum");
		invNum_H = (EZDCStringItem)newItem("invNum_H");
		invNum_OR = (EZDCStringItem)newItem("invNum_OR");
		invTpCd_H = (EZDCStringItem)newItem("invTpCd_H");
		A = (business.blap.NFCL3080.NFCL3080_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3080CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3080CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"invNum", "invNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"invNum_H", "invNum_H", "H", null, TYPE_HANKAKUEISU, "13", null},
	{"invNum_OR", "invNum_OR", "OR", null, TYPE_HANKAKUEISU, "13", null},
	{"invTpCd_H", "invTpCd_H", "H", null, TYPE_HANKAKUEISU, "1", null},
	{"A", "A", null, "200", "business.blap.NFCL3080.NFCL3080_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_H
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_OR
        {"INV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTpCd_H
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

