//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20140623104240000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZZL0071CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZZL0071;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZZL0071 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZZZL0071CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** XX_FLG_NM*/
	public final EZDCStringItem              xxFlgNm;

    /** BAT_PROC_JOB_ID*/
	public final EZDCStringItem              batProcJobId;

    /** BAT_PROC_PGM_ID*/
	public final EZDCStringItem              batProcPgmId;

    /** A*/
	public final business.blap.ZZZL0071.ZZZL0071_ACMsgArray              A;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** B*/
	public final business.blap.ZZZL0071.ZZZL0071_BCMsgArray              B;


	/**
	 * ZZZL0071CMsg is constructor.
	 * The initialization when the instance of ZZZL0071CMsg is generated.
	 */
	public ZZZL0071CMsg() {
		this(false, -1);
	}

	/**
	 * ZZZL0071CMsg is constructor.
	 * The initialization when the instance of ZZZL0071CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZZL0071CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		xxFlgNm = (EZDCStringItem)newItem("xxFlgNm");
		batProcJobId = (EZDCStringItem)newItem("batProcJobId");
		batProcPgmId = (EZDCStringItem)newItem("batProcPgmId");
		A = (business.blap.ZZZL0071.ZZZL0071_ACMsgArray)newMsgArray("A");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		B = (business.blap.ZZZL0071.ZZZL0071_BCMsgArray)newMsgArray("B");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZZL0071CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZZL0071CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxFlgNm", "xxFlgNm", null, null, TYPE_HANKAKUEISU, "4", null},
	{"batProcJobId", "batProcJobId", null, null, TYPE_HANKAKUEISU, "18", null},
	{"batProcPgmId", "batProcPgmId", null, null, TYPE_HANKAKUEISU, "64", null},
	{"A", "A", null, "40", "business.blap.ZZZL0071.ZZZL0071_ACMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"B", "B", null, "200", "business.blap.ZZZL0071.ZZZL0071_BCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_FLG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFlgNm
        {"BAT_PROC_JOB_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcJobId
        {"BAT_PROC_PGM_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcPgmId
		null,	//A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//B
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
