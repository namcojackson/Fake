//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20161005181819000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC110001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC110001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC110001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** CFS_CONTR_PRC_UPLFT_PK*/
	public final EZDPBigDecimalItem              cfsContrPrcUplftPk;

    /** CFS_PROC_STS_CD*/
	public final EZDPStringItem              cfsProcStsCd;

    /** xxMsgIdList*/
	public final business.parts.NSZC110001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NSZC110001PMsg is constructor.
	 * The initialization when the instance of NSZC110001PMsg is generated.
	 */
	public NSZC110001PMsg() {
		this(false, -1);
	}

	/**
	 * NSZC110001PMsg is constructor.
	 * The initialization when the instance of NSZC110001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC110001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		cfsContrPrcUplftPk = (EZDPBigDecimalItem)newItem("cfsContrPrcUplftPk");
		cfsProcStsCd = (EZDPStringItem)newItem("cfsProcStsCd");
		xxMsgIdList = (business.parts.NSZC110001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC110001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC110001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"cfsContrPrcUplftPk", "cfsContrPrcUplftPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cfsProcStsCd", "cfsProcStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxMsgIdList", "xxMsgIdList", null, "2000", "business.parts.NSZC110001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"CFS_CONTR_PRC_UPLFT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cfsContrPrcUplftPk
        {"CFS_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cfsProcStsCd
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
