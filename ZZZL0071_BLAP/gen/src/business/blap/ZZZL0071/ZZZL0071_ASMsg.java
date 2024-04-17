//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20140623104241000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZZL0071_ASMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is ZZZL0071 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class ZZZL0071_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDSStringItem              xxChkBox_A;

    /** GLBL_CMPY_CD_A*/
	public final EZDSStringItem              glblCmpyCd_A;

    /** BAT_PROC_JOB_ID_A*/
	public final EZDSStringItem              batProcJobId_A;

    /** BAT_PROC_PGM_ID_A*/
	public final EZDSStringItem              batProcPgmId_A;


	/**
	 * ZZZL0071_ASMsg is constructor.
	 * The initialization when the instance of ZZZL0071_ASMsg is generated.
	 */
	public ZZZL0071_ASMsg() {
		this(false, -1);
	}

	/**
	 * ZZZL0071_ASMsg is constructor.
	 * The initialization when the instance of ZZZL0071_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZZL0071_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDSStringItem)newItem("xxChkBox_A");
		glblCmpyCd_A = (EZDSStringItem)newItem("glblCmpyCd_A");
		batProcJobId_A = (EZDSStringItem)newItem("batProcJobId_A");
		batProcPgmId_A = (EZDSStringItem)newItem("batProcPgmId_A");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZZL0071_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZZL0071_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"glblCmpyCd_A", "glblCmpyCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"batProcJobId_A", "batProcJobId_A", "A", null, TYPE_HANKAKUEISU, "18", null},
	{"batProcPgmId_A", "batProcPgmId_A", "A", null, TYPE_HANKAKUEISU, "64", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_A
        {"BAT_PROC_JOB_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcJobId_A
        {"BAT_PROC_PGM_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcPgmId_A
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
