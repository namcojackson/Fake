//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20131112120639000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZML0071SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZML0071;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZML0071 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class ZZML0071SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDSStringItem              glblCmpyCd;

    /** A*/
	public final business.blap.ZZML0071.ZZML0071_ASMsgArray              A;


	/**
	 * ZZML0071SMsg is constructor.
	 * The initialization when the instance of ZZML0071SMsg is generated.
	 */
	public ZZML0071SMsg() {
		this(false, -1);
	}

	/**
	 * ZZML0071SMsg is constructor.
	 * The initialization when the instance of ZZML0071SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZML0071SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDSStringItem)newItem("glblCmpyCd");
		A = (business.blap.ZZML0071.ZZML0071_ASMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZML0071SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZML0071SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "200", "business.blap.ZZML0071.ZZML0071_ASMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
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

