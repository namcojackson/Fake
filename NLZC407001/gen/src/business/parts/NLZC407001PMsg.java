//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20170531123008000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC407001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLZC407001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLZC407001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** RWS_NUM*/
	public final EZDPStringItem              rwsNum;

    /** XX_WRN_SKIP_FLG*/
	public final EZDPStringItem              xxWrnSkipFlg;

    /** XX_PRC_CLO_FLG*/
	public final EZDPStringItem              xxPrcCloFlg;

    /** inputList*/
	public final business.parts.NLZC407001_inputListPMsgArray              inputList;

    /** outputList*/
	public final business.parts.NLZC407001_outputListPMsgArray              outputList;

    /** xxMsgIdList*/
	public final business.parts.NLZC407001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NLZC407001PMsg is constructor.
	 * The initialization when the instance of NLZC407001PMsg is generated.
	 */
	public NLZC407001PMsg() {
		this(false, -1);
	}

	/**
	 * NLZC407001PMsg is constructor.
	 * The initialization when the instance of NLZC407001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLZC407001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		rwsNum = (EZDPStringItem)newItem("rwsNum");
		xxWrnSkipFlg = (EZDPStringItem)newItem("xxWrnSkipFlg");
		xxPrcCloFlg = (EZDPStringItem)newItem("xxPrcCloFlg");
		inputList = (business.parts.NLZC407001_inputListPMsgArray)newMsgArray("inputList");
		outputList = (business.parts.NLZC407001_outputListPMsgArray)newMsgArray("outputList");
		xxMsgIdList = (business.parts.NLZC407001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NLZC407001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLZC407001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"rwsNum", "rwsNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"xxWrnSkipFlg", "xxWrnSkipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxPrcCloFlg", "xxPrcCloFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"inputList", "inputList", null, "999", "business.parts.NLZC407001_inputListPMsgArray", null, null},
	
	{"outputList", "outputList", null, "999", "business.parts.NLZC407001_outputListPMsgArray", null, null},
	
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NLZC407001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"RWS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsNum
        {"XX_WRN_SKIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWrnSkipFlg
        {"XX_PRC_CLO_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPrcCloFlg
		null,	//inputList
		null,	//outputList
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

