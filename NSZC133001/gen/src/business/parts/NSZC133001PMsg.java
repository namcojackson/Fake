//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20230320154401000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC133001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC133001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC133001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CUST_SYS_ID*/
	public final EZDPStringItem              custSysId;

    /** CUST_CALL_ID*/
	public final EZDPStringItem              custCallId;

    /** FSR_NUM*/
	public final EZDPStringItem              fsrNum;

    /** SVC_CMNT_TXT*/
	public final EZDPStringItem              svcCmntTxt;

    /** SVC_WRK_TXT*/
	public final EZDPStringItem              svcWrkTxt;

    /** xxMsgIdList*/
	public final business.parts.NSZC133001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NSZC133001PMsg is constructor.
	 * The initialization when the instance of NSZC133001PMsg is generated.
	 */
	public NSZC133001PMsg() {
		this(false, -1);
	}

	/**
	 * NSZC133001PMsg is constructor.
	 * The initialization when the instance of NSZC133001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC133001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		custSysId = (EZDPStringItem)newItem("custSysId");
		custCallId = (EZDPStringItem)newItem("custCallId");
		fsrNum = (EZDPStringItem)newItem("fsrNum");
		svcCmntTxt = (EZDPStringItem)newItem("svcCmntTxt");
		svcWrkTxt = (EZDPStringItem)newItem("svcWrkTxt");
		xxMsgIdList = (business.parts.NSZC133001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC133001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC133001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"custSysId", "custSysId", null, null, TYPE_HANKAKUEISU, "32", null},
	{"custCallId", "custCallId", null, null, TYPE_HANKAKUEISU, "100", null},
	{"fsrNum", "fsrNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"svcCmntTxt", "svcCmntTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"svcWrkTxt", "svcWrkTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NSZC133001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CUST_SYS_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custSysId
        {"CUST_CALL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custCallId
        {"FSR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum
        {"SVC_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt
        {"SVC_WRK_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcWrkTxt
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
