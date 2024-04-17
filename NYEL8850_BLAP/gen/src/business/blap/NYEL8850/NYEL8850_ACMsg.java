//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170302155332000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8850_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NYEL8850;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NYEL8850 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NYEL8850_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** WF_WRK_ITEM_STS_CD_A*/
	public final EZDCStringItem              wfWrkItemStsCd_A;

    /** WF_WRK_ITEM_ID_A*/
	public final EZDCStringItem              wfWrkItemId_A;

    /** WF_WRK_ITEM_NM_A*/
	public final EZDCStringItem              wfWrkItemNm_A;

    /** XX_WF_ASG_TO_NM_A*/
	public final EZDCStringItem              xxWfAsgToNm_A;


	/**
	 * NYEL8850_ACMsg is constructor.
	 * The initialization when the instance of NYEL8850_ACMsg is generated.
	 */
	public NYEL8850_ACMsg() {
		this(false, -1);
	}

	/**
	 * NYEL8850_ACMsg is constructor.
	 * The initialization when the instance of NYEL8850_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYEL8850_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		wfWrkItemStsCd_A = (EZDCStringItem)newItem("wfWrkItemStsCd_A");
		wfWrkItemId_A = (EZDCStringItem)newItem("wfWrkItemId_A");
		wfWrkItemNm_A = (EZDCStringItem)newItem("wfWrkItemNm_A");
		xxWfAsgToNm_A = (EZDCStringItem)newItem("xxWfAsgToNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NYEL8850_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYEL8850_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"wfWrkItemStsCd_A", "wfWrkItemStsCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"wfWrkItemId_A", "wfWrkItemId_A", "A", null, TYPE_HANKAKUEISU, "40", null},
	{"wfWrkItemNm_A", "wfWrkItemNm_A", "A", null, TYPE_HANKAKUEISU, "40", null},
	{"xxWfAsgToNm_A", "xxWfAsgToNm_A", "A", null, TYPE_HANKAKUEISU, "1024", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"WF_WRK_ITEM_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfWrkItemStsCd_A
        {"WF_WRK_ITEM_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfWrkItemId_A
        {"WF_WRK_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfWrkItemNm_A
        {"XX_WF_ASG_TO_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfAsgToNm_A
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

