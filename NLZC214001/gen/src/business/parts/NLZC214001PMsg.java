//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20090825100326000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC214001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLZC214001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLZC214001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** USR_ID*/
	public final EZDPStringItem              usrId;

    /** PROC_START_TS*/
	public final EZDPStringItem              procStartTs;

    /** soNumList*/
	public final business.parts.NLZC214001_soNumListPMsgArray              soNumList;

    /** XX_TEMP_FILE_PATH_TXT*/
	public final EZDPStringItem              xxTempFilePathTxt;

    /** xxMsgIdList*/
	public final business.parts.NLZC214001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NLZC214001PMsg is constructor.
	 * The initialization when the instance of NLZC214001PMsg is generated.
	 */
	public NLZC214001PMsg() {
		this(false, -1);
	}

	/**
	 * NLZC214001PMsg is constructor.
	 * The initialization when the instance of NLZC214001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLZC214001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		usrId = (EZDPStringItem)newItem("usrId");
		procStartTs = (EZDPStringItem)newItem("procStartTs");
		soNumList = (business.parts.NLZC214001_soNumListPMsgArray)newMsgArray("soNumList");
		xxTempFilePathTxt = (EZDPStringItem)newItem("xxTempFilePathTxt");
		xxMsgIdList = (business.parts.NLZC214001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NLZC214001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLZC214001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"usrId", "usrId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"procStartTs", "procStartTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"soNumList", "soNumList", null, "999", "business.parts.NLZC214001_soNumListPMsgArray", null, null},
	
	{"xxTempFilePathTxt", "xxTempFilePathTxt", null, null, TYPE_HANKAKUEISU, "120", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NLZC214001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//usrId
        {"PROC_START_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procStartTs
		null,	//soNumList
        {"XX_TEMP_FILE_PATH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTempFilePathTxt
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

