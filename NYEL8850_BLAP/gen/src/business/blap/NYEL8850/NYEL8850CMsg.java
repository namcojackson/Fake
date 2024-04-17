//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170302155332000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8850CMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NYEL8850CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** WF_PROC_TAG_NM*/
	public final EZDCStringItem              wfProcTagNm;

    /** WF_DESC_TXT_PR*/
	public final EZDCStringItem              wfDescTxt_PR;

    /** XX_CELL_IDX*/
	public final EZDCBigDecimalItem              xxCellIdx;

    /** H*/
	public final business.blap.NYEL8850.NYEL8850_HCMsgArray              H;

    /** A*/
	public final business.blap.NYEL8850.NYEL8850_ACMsgArray              A;

    /** B*/
	public final business.blap.NYEL8850.NYEL8850_BCMsgArray              B;

    /** Z*/
	public final business.blap.NYEL8850.NYEL8850_ZCMsgArray              Z;

    /** P*/
	public final business.blap.NYEL8850.NYEL8850_PCMsgArray              P;


	/**
	 * NYEL8850CMsg is constructor.
	 * The initialization when the instance of NYEL8850CMsg is generated.
	 */
	public NYEL8850CMsg() {
		this(false, -1);
	}

	/**
	 * NYEL8850CMsg is constructor.
	 * The initialization when the instance of NYEL8850CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYEL8850CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		wfProcTagNm = (EZDCStringItem)newItem("wfProcTagNm");
		wfDescTxt_PR = (EZDCStringItem)newItem("wfDescTxt_PR");
		xxCellIdx = (EZDCBigDecimalItem)newItem("xxCellIdx");
		H = (business.blap.NYEL8850.NYEL8850_HCMsgArray)newMsgArray("H");
		A = (business.blap.NYEL8850.NYEL8850_ACMsgArray)newMsgArray("A");
		B = (business.blap.NYEL8850.NYEL8850_BCMsgArray)newMsgArray("B");
		Z = (business.blap.NYEL8850.NYEL8850_ZCMsgArray)newMsgArray("Z");
		P = (business.blap.NYEL8850.NYEL8850_PCMsgArray)newMsgArray("P");
	}

	/**
	 * get the type of array which is stored
	 * @return NYEL8850CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYEL8850CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"wfProcTagNm", "wfProcTagNm", null, null, TYPE_HANKAKUEISU, "40", null},
	{"wfDescTxt_PR", "wfDescTxt_PR", "PR", null, TYPE_HANKAKUEISU, "50", null},
	{"xxCellIdx", "xxCellIdx", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"H", "H", null, "200", "business.blap.NYEL8850.NYEL8850_HCMsgArray", null, null},
	
	{"A", "A", null, "200", "business.blap.NYEL8850.NYEL8850_ACMsgArray", null, null},
	
	{"B", "B", null, "200", "business.blap.NYEL8850.NYEL8850_BCMsgArray", null, null},
	
	{"Z", "Z", null, "3", "business.blap.NYEL8850.NYEL8850_ZCMsgArray", null, null},
	
	{"P", "P", null, "3", "business.blap.NYEL8850.NYEL8850_PCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"WF_PROC_TAG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfProcTagNm
        {"WF_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfDescTxt_PR
        {"XX_CELL_IDX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCellIdx
		null,	//H
		null,	//A
		null,	//B
		null,	//Z
		null,	//P
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
