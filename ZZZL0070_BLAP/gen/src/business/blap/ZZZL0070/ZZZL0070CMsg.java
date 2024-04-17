//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20140623103439000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZZL0070CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZZL0070;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZZL0070 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZZZL0070CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** BAT_PROC_JOB_ID*/
	public final EZDCStringItem              batProcJobId;

    /** XX_FROM_DT*/
	public final EZDCDateItem              xxFromDt;

    /** XX_TO_DT*/
	public final EZDCDateItem              xxToDt;

    /** DEL_FLG*/
	public final EZDCStringItem              delFlg;

    /** A*/
	public final business.blap.ZZZL0070.ZZZL0070_ACMsgArray              A;


	/**
	 * ZZZL0070CMsg is constructor.
	 * The initialization when the instance of ZZZL0070CMsg is generated.
	 */
	public ZZZL0070CMsg() {
		this(false, -1);
	}

	/**
	 * ZZZL0070CMsg is constructor.
	 * The initialization when the instance of ZZZL0070CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZZL0070CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		batProcJobId = (EZDCStringItem)newItem("batProcJobId");
		xxFromDt = (EZDCDateItem)newItem("xxFromDt");
		xxToDt = (EZDCDateItem)newItem("xxToDt");
		delFlg = (EZDCStringItem)newItem("delFlg");
		A = (business.blap.ZZZL0070.ZZZL0070_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZZL0070CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZZL0070CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"batProcJobId", "batProcJobId", null, null, TYPE_HANKAKUEISU, "18", null},
	{"xxFromDt", "xxFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxToDt", "xxToDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"delFlg", "delFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"A", "A", null, "20", "business.blap.ZZZL0070.ZZZL0070_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"BAT_PROC_JOB_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcJobId
        {"XX_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFromDt
        {"XX_TO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxToDt
        {"DEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delFlg
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
