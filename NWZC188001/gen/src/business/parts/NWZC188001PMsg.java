//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20180313112724000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC188001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC188001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC188001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** CPO_ORD_NUM*/
	public final EZDPStringItem              cpoOrdNum;

    /** CANC_FLG*/
	public final EZDPStringItem              cancFlg;

    /** xxMsgIdList*/
	public final business.parts.NWZC188001_xxMsgIdListPMsgArray              xxMsgIdList;

    /** shpgPlnNumList*/
	public final business.parts.NWZC188001_shpgPlnNumListPMsgArray              shpgPlnNumList;

    /** rmaLineList*/
	public final business.parts.NWZC188001_rmaLineListPMsgArray              rmaLineList;


	/**
	 * NWZC188001PMsg is constructor.
	 * The initialization when the instance of NWZC188001PMsg is generated.
	 */
	public NWZC188001PMsg() {
		this(false, -1);
	}

	/**
	 * NWZC188001PMsg is constructor.
	 * The initialization when the instance of NWZC188001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC188001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		cpoOrdNum = (EZDPStringItem)newItem("cpoOrdNum");
		cancFlg = (EZDPStringItem)newItem("cancFlg");
		xxMsgIdList = (business.parts.NWZC188001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
		shpgPlnNumList = (business.parts.NWZC188001_shpgPlnNumListPMsgArray)newMsgArray("shpgPlnNumList");
		rmaLineList = (business.parts.NWZC188001_rmaLineListPMsgArray)newMsgArray("rmaLineList");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC188001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC188001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"cancFlg", "cancFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxMsgIdList", "xxMsgIdList", null, "999", "business.parts.NWZC188001_xxMsgIdListPMsgArray", null, null},
	
	{"shpgPlnNumList", "shpgPlnNumList", null, "5000", "business.parts.NWZC188001_shpgPlnNumListPMsgArray", null, null},
	
	{"rmaLineList", "rmaLineList", null, "5000", "business.parts.NWZC188001_rmaLineListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"CANC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cancFlg
		null,	//xxMsgIdList
		null,	//shpgPlnNumList
		null,	//rmaLineList
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

