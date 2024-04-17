//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20150827212714000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPZC107001PMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPZC107001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPZC107001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** xxSupdMdseList*/
	public final business.parts.NPZC107001_xxSupdMdseListPMsgArray              xxSupdMdseList;

    /** xxMsgIdList*/
	public final business.parts.NPZC107001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NPZC107001PMsg is constructor.
	 * The initialization when the instance of NPZC107001PMsg is generated.
	 */
	public NPZC107001PMsg() {
		this(false, -1);
	}

	/**
	 * NPZC107001PMsg is constructor.
	 * The initialization when the instance of NPZC107001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPZC107001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		xxSupdMdseList = (business.parts.NPZC107001_xxSupdMdseListPMsgArray)newMsgArray("xxSupdMdseList");
		xxMsgIdList = (business.parts.NPZC107001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NPZC107001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPZC107001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxSupdMdseList", "xxSupdMdseList", null, "100", "business.parts.NPZC107001_xxSupdMdseListPMsgArray", null, null},
	
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NPZC107001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
		null,	//xxSupdMdseList
		null,	//xxMsgIdList
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
