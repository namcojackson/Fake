//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20161115140119000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC072001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC072001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC072001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** SER_NUM*/
	public final EZDPStringItem              serNum;

    /** CLICK_MSG_TXT*/
	public final EZDPStringItem              clickMsgTxt;

    /** machVldList*/
	public final business.parts.NSZC072001_machVldListPMsgArray              machVldList;

    /** xxMsgIdList*/
	public final business.parts.NSZC072001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NSZC072001PMsg is constructor.
	 * The initialization when the instance of NSZC072001PMsg is generated.
	 */
	public NSZC072001PMsg() {
		this(false, -1);
	}

	/**
	 * NSZC072001PMsg is constructor.
	 * The initialization when the instance of NSZC072001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC072001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		serNum = (EZDPStringItem)newItem("serNum");
		clickMsgTxt = (EZDPStringItem)newItem("clickMsgTxt");
		machVldList = (business.parts.NSZC072001_machVldListPMsgArray)newMsgArray("machVldList");
		xxMsgIdList = (business.parts.NSZC072001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC072001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC072001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"clickMsgTxt", "clickMsgTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"machVldList", "machVldList", null, "2000", "business.parts.NSZC072001_machVldListPMsgArray", null, null},
	
	{"xxMsgIdList", "xxMsgIdList", null, "2000", "business.parts.NSZC072001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"CLICK_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//clickMsgTxt
		null,	//machVldList
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

