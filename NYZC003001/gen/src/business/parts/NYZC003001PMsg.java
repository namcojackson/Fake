//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20170710111800000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYZC003001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NYZC003001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NYZC003001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** xxMsgIdList*/
	public final business.parts.NYZC003001_xxMsgIdListPMsgArray              xxMsgIdList;

    /** ATT_DATA_PK*/
	public final EZDPBigDecimalItem              attDataPk;

    /** ATT_DATA_NM*/
	public final EZDPStringItem              attDataNm;


	/**
	 * NYZC003001PMsg is constructor.
	 * The initialization when the instance of NYZC003001PMsg is generated.
	 */
	public NYZC003001PMsg() {
		this(false, -1);
	}

	/**
	 * NYZC003001PMsg is constructor.
	 * The initialization when the instance of NYZC003001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYZC003001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		xxMsgIdList = (business.parts.NYZC003001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
		attDataPk = (EZDPBigDecimalItem)newItem("attDataPk");
		attDataNm = (EZDPStringItem)newItem("attDataNm");
	}

	/**
	 * get the type of array which is stored
	 * @return NYZC003001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYZC003001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NYZC003001_xxMsgIdListPMsgArray", null, null},
	
	{"attDataPk", "attDataPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"attDataNm", "attDataNm", null, null, TYPE_HANKAKUEISU, "256", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
		null,	//xxMsgIdList
        {"ATT_DATA_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attDataPk
        {"ATT_DATA_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attDataNm
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
