//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151102121349000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC401001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLZC401001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLZC401001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** serInfo*/
	public final business.parts.NLZC401001_serInfoPMsgArray              serInfo;

    /** xxMsgIdList*/
	public final business.parts.NLZC401001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NLZC401001PMsg is constructor.
	 * The initialization when the instance of NLZC401001PMsg is generated.
	 */
	public NLZC401001PMsg() {
		this(false, -1);
	}

	/**
	 * NLZC401001PMsg is constructor.
	 * The initialization when the instance of NLZC401001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLZC401001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		serInfo = (business.parts.NLZC401001_serInfoPMsgArray)newMsgArray("serInfo");
		xxMsgIdList = (business.parts.NLZC401001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NLZC401001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLZC401001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"serInfo", "serInfo", null, "1000", "business.parts.NLZC401001_serInfoPMsgArray", null, null},
	
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NLZC401001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
		null,	//serInfo
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

