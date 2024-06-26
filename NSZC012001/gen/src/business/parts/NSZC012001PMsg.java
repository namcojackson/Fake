//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20160630152441000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC012001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC012001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC012001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** XX_MODE_CD*/
	public final EZDPStringItem              xxModeCd;

    /** FSRVisitList*/
	public final business.parts.NSZC012001_FSRVisitListPMsgArray              FSRVisitList;

    /** xxMsgIdList*/
	public final business.parts.NSZC012001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NSZC012001PMsg is constructor.
	 * The initialization when the instance of NSZC012001PMsg is generated.
	 */
	public NSZC012001PMsg() {
		this(false, -1);
	}

	/**
	 * NSZC012001PMsg is constructor.
	 * The initialization when the instance of NSZC012001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC012001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		xxModeCd = (EZDPStringItem)newItem("xxModeCd");
		FSRVisitList = (business.parts.NSZC012001_FSRVisitListPMsgArray)newMsgArray("FSRVisitList");
		xxMsgIdList = (business.parts.NSZC012001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC012001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC012001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"FSRVisitList", "FSRVisitList", null, "100", "business.parts.NSZC012001_FSRVisitListPMsgArray", null, null},
	
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NSZC012001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
		null,	//FSRVisitList
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

