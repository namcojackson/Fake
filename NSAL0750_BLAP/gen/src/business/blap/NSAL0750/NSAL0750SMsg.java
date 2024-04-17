//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170227165646000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0750SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0750;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0750 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0750SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDSStringItem              glblCmpyCd;

    /** A*/
	public final business.blap.NSAL0750.NSAL0750_ASMsgArray              A;

    /** XX_CHK_BOX_H1*/
	public final EZDSStringItem              xxChkBox_H1;

    /** XX_CHK_BOX_H2*/
	public final EZDSStringItem              xxChkBox_H2;

    /** XX_CHK_BOX_H3*/
	public final EZDSStringItem              xxChkBox_H3;


	/**
	 * NSAL0750SMsg is constructor.
	 * The initialization when the instance of NSAL0750SMsg is generated.
	 */
	public NSAL0750SMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0750SMsg is constructor.
	 * The initialization when the instance of NSAL0750SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0750SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDSStringItem)newItem("glblCmpyCd");
		A = (business.blap.NSAL0750.NSAL0750_ASMsgArray)newMsgArray("A");
		xxChkBox_H1 = (EZDSStringItem)newItem("xxChkBox_H1");
		xxChkBox_H2 = (EZDSStringItem)newItem("xxChkBox_H2");
		xxChkBox_H3 = (EZDSStringItem)newItem("xxChkBox_H3");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0750SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0750SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "2000", "business.blap.NSAL0750.NSAL0750_ASMsgArray", null, null},
	
	{"xxChkBox_H1", "xxChkBox_H1", "H1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_H2", "xxChkBox_H2", "H2", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_H3", "xxChkBox_H3", "H3", null, TYPE_HANKAKUEIJI, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
		null,	//A
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H2
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H3
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

