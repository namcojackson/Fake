//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20220620110704000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC194001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC194001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC194001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** XX_MODE_CD*/
	public final EZDPStringItem              xxModeCd;

    /** DS_CONTR_PK*/
	public final EZDPBigDecimalItem              dsContrPk;

    /** SVC_MACH_MSTR_PK*/
	public final EZDPBigDecimalItem              svcMachMstrPk;

    /** xxMsgIdList*/
	public final business.parts.NWZC194001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NWZC194001PMsg is constructor.
	 * The initialization when the instance of NWZC194001PMsg is generated.
	 */
	public NWZC194001PMsg() {
		this(false, -1);
	}

	/**
	 * NWZC194001PMsg is constructor.
	 * The initialization when the instance of NWZC194001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC194001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		xxModeCd = (EZDPStringItem)newItem("xxModeCd");
		dsContrPk = (EZDPBigDecimalItem)newItem("dsContrPk");
		svcMachMstrPk = (EZDPBigDecimalItem)newItem("svcMachMstrPk");
		xxMsgIdList = (business.parts.NWZC194001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC194001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC194001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrPk", "dsContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NWZC194001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
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
