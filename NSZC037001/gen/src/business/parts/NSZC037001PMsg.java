//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151014153651000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC037001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC037001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC037001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** USR_ID*/
	public final EZDPStringItem              usrId;

    /** FSR_NUM*/
	public final EZDPStringItem              fsrNum;

    /** A*/
	public final business.parts.NSZC037001_APMsgArray              A;

    /** xxMsgIdList*/
	public final business.parts.NSZC037001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NSZC037001PMsg is constructor.
	 * The initialization when the instance of NSZC037001PMsg is generated.
	 */
	public NSZC037001PMsg() {
		this(false, -1);
	}

	/**
	 * NSZC037001PMsg is constructor.
	 * The initialization when the instance of NSZC037001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC037001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		usrId = (EZDPStringItem)newItem("usrId");
		fsrNum = (EZDPStringItem)newItem("fsrNum");
		A = (business.parts.NSZC037001_APMsgArray)newMsgArray("A");
		xxMsgIdList = (business.parts.NSZC037001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC037001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC037001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"usrId", "usrId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"fsrNum", "fsrNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"A", "A", null, "2000", "business.parts.NSZC037001_APMsgArray", null, null},
	
	{"xxMsgIdList", "xxMsgIdList", null, "2000", "business.parts.NSZC037001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//usrId
        {"FSR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum
		null,	//A
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

