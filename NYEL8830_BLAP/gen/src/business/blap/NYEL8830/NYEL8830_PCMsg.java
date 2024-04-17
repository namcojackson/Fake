//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180516094218000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8830_PCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NYEL8830;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NYEL8830 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NYEL8830_PCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** WF_PROC_PK_P*/
	public final EZDCBigDecimalItem              wfProcPk_P;

    /** WF_WRK_ITEM_PK_P*/
	public final EZDCBigDecimalItem              wfWrkItemPk_P;

    /** WF_WRK_ITEM_ACT_CNT_P*/
	public final EZDCBigDecimalItem              wfWrkItemActCnt_P;

    /** WF_PROC_STS_CD_P*/
	public final EZDCStringItem              wfProcStsCd_P;


	/**
	 * NYEL8830_PCMsg is constructor.
	 * The initialization when the instance of NYEL8830_PCMsg is generated.
	 */
	public NYEL8830_PCMsg() {
		this(false, -1);
	}

	/**
	 * NYEL8830_PCMsg is constructor.
	 * The initialization when the instance of NYEL8830_PCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYEL8830_PCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		wfProcPk_P = (EZDCBigDecimalItem)newItem("wfProcPk_P");
		wfWrkItemPk_P = (EZDCBigDecimalItem)newItem("wfWrkItemPk_P");
		wfWrkItemActCnt_P = (EZDCBigDecimalItem)newItem("wfWrkItemActCnt_P");
		wfProcStsCd_P = (EZDCStringItem)newItem("wfProcStsCd_P");
	}

	/**
	 * get the type of array which is stored
	 * @return NYEL8830_PCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYEL8830_PCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"wfProcPk_P", "wfProcPk_P", "P", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"wfWrkItemPk_P", "wfWrkItemPk_P", "P", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"wfWrkItemActCnt_P", "wfWrkItemActCnt_P", "P", null, TYPE_SEISU_SYOSU, "6", "0"},
	{"wfProcStsCd_P", "wfProcStsCd_P", "P", null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"WF_PROC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfProcPk_P
        {"WF_WRK_ITEM_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfWrkItemPk_P
        {"WF_WRK_ITEM_ACT_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfWrkItemActCnt_P
        {"WF_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfProcStsCd_P
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

