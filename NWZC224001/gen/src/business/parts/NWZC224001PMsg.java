//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20161019110539000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC224001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC224001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC224001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** DOC_MGT_DOC_ID*/
	public final EZDPBigDecimalItem              docMgtDocId;

    /** DOC_MGT_CATG_CD*/
	public final EZDPStringItem              docMgtCatgCd;

    /** DOC_MGT_PRNT_DOC_NUM*/
	public final EZDPStringItem              docMgtPrntDocNum;

    /** DOC_MGT_BIZ_DOC_NUM*/
	public final EZDPStringItem              docMgtBizDocNum;

    /** PROC_MODE_CD*/
	public final EZDPStringItem              procModeCd;

    /** xxMsgIdList*/
	public final business.parts.NWZC224001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NWZC224001PMsg is constructor.
	 * The initialization when the instance of NWZC224001PMsg is generated.
	 */
	public NWZC224001PMsg() {
		this(false, -1);
	}

	/**
	 * NWZC224001PMsg is constructor.
	 * The initialization when the instance of NWZC224001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC224001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		docMgtDocId = (EZDPBigDecimalItem)newItem("docMgtDocId");
		docMgtCatgCd = (EZDPStringItem)newItem("docMgtCatgCd");
		docMgtPrntDocNum = (EZDPStringItem)newItem("docMgtPrntDocNum");
		docMgtBizDocNum = (EZDPStringItem)newItem("docMgtBizDocNum");
		procModeCd = (EZDPStringItem)newItem("procModeCd");
		xxMsgIdList = (business.parts.NWZC224001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC224001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC224001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"docMgtDocId", "docMgtDocId", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"docMgtCatgCd", "docMgtCatgCd", null, null, TYPE_HANKAKUEISU, "5", null},
	{"docMgtPrntDocNum", "docMgtPrntDocNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"docMgtBizDocNum", "docMgtBizDocNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"procModeCd", "procModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxMsgIdList", "xxMsgIdList", null, "99", "business.parts.NWZC224001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"DOC_MGT_DOC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtDocId
        {"DOC_MGT_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtCatgCd
        {"DOC_MGT_PRNT_DOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtPrntDocNum
        {"DOC_MGT_BIZ_DOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docMgtBizDocNum
        {"PROC_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procModeCd
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
