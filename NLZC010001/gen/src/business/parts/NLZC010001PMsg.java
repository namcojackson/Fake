//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151111191353000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC010001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLZC010001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLZC010001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** SYS_SRC_CD*/
	public final EZDPStringItem              sysSrcCd;

    /** PO_RCV_NUM*/
	public final EZDPStringItem              poRcvNum;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** SO_NUM*/
	public final EZDPStringItem              soNum;

    /** xxMsgIdList*/
	public final business.parts.NLZC010001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NLZC010001PMsg is constructor.
	 * The initialization when the instance of NLZC010001PMsg is generated.
	 */
	public NLZC010001PMsg() {
		this(false, -1);
	}

	/**
	 * NLZC010001PMsg is constructor.
	 * The initialization when the instance of NLZC010001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLZC010001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		sysSrcCd = (EZDPStringItem)newItem("sysSrcCd");
		poRcvNum = (EZDPStringItem)newItem("poRcvNum");
		slsDt = (EZDPDateItem)newItem("slsDt");
		soNum = (EZDPStringItem)newItem("soNum");
		xxMsgIdList = (business.parts.NLZC010001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NLZC010001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLZC010001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"sysSrcCd", "sysSrcCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"poRcvNum", "poRcvNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"soNum", "soNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NLZC010001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SYS_SRC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysSrcCd
        {"PO_RCV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poRcvNum
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum
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
