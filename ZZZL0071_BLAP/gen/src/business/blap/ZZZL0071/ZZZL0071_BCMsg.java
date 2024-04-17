//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20140623104240000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZZL0071_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZZL0071;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZZL0071 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZZZL0071_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD_B*/
	public final EZDCStringItem              glblCmpyCd_B;

    /** BAT_PROC_JOB_ID_B*/
	public final EZDCStringItem              batProcJobId_B;

    /** BAT_PROC_PGM_ID_B*/
	public final EZDCStringItem              batProcPgmId_B;


	/**
	 * ZZZL0071_BCMsg is constructor.
	 * The initialization when the instance of ZZZL0071_BCMsg is generated.
	 */
	public ZZZL0071_BCMsg() {
		this(false, -1);
	}

	/**
	 * ZZZL0071_BCMsg is constructor.
	 * The initialization when the instance of ZZZL0071_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZZL0071_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd_B = (EZDCStringItem)newItem("glblCmpyCd_B");
		batProcJobId_B = (EZDCStringItem)newItem("batProcJobId_B");
		batProcPgmId_B = (EZDCStringItem)newItem("batProcPgmId_B");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZZL0071_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZZL0071_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd_B", "glblCmpyCd_B", "B", null, TYPE_HANKAKUEISU, "4", null},
	{"batProcJobId_B", "batProcJobId_B", "B", null, TYPE_HANKAKUEISU, "18", null},
	{"batProcPgmId_B", "batProcPgmId_B", "B", null, TYPE_HANKAKUEISU, "64", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_B
        {"BAT_PROC_JOB_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcJobId_B
        {"BAT_PROC_PGM_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcPgmId_B
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

