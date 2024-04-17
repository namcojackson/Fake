//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180628172810000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3080SMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NFCL3080 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3080SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDSStringItem              glblCmpyCd;

    /** INV_NUM*/
	public final EZDSStringItem              invNum;

    /** INV_NUM_H*/
	public final EZDSStringItem              invNum_H;

    /** INV_NUM_OR*/
	public final EZDSStringItem              invNum_OR;

    /** INV_TP_CD_H*/
	public final EZDSStringItem              invTpCd_H;

    /** A*/
	public final business.blap.NFCL3080.NFCL3080_ASMsgArray              A;


	/**
	 * NFCL3080SMsg is constructor.
	 * The initialization when the instance of NFCL3080SMsg is generated.
	 */
	public NFCL3080SMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3080SMsg is constructor.
	 * The initialization when the instance of NFCL3080SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3080SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDSStringItem)newItem("glblCmpyCd");
		invNum = (EZDSStringItem)newItem("invNum");
		invNum_H = (EZDSStringItem)newItem("invNum_H");
		invNum_OR = (EZDSStringItem)newItem("invNum_OR");
		invTpCd_H = (EZDSStringItem)newItem("invTpCd_H");
		A = (business.blap.NFCL3080.NFCL3080_ASMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3080SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3080SMsgArray();
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
	{"A", "A", null, "200", "business.blap.NFCL3080.NFCL3080_ASMsgArray", null, null},
	
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

