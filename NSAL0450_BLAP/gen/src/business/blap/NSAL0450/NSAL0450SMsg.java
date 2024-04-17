//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20190121132650000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0450SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0450;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0450 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0450SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** COA_AFFL_ACCT_NM_H*/
	public final EZDSStringItem              coaAfflAcctNm_H;

    /** DS_CONTR_PK_H*/
	public final EZDSBigDecimalItem              dsContrPk_H;

    /** XX_MODE_CD*/
	public final EZDSStringItem              xxModeCd;

    /** GLBL_CMPY_CD*/
	public final EZDSStringItem              glblCmpyCd;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDSBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDSBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDSBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDSBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDSBigDecimalItem              xxPageShowTotNum;

    /** A*/
	public final business.blap.NSAL0450.NSAL0450_ASMsgArray              A;

    /** XX_NUM*/
	public final EZDSBigDecimalItem              xxNum;


	/**
	 * NSAL0450SMsg is constructor.
	 * The initialization when the instance of NSAL0450SMsg is generated.
	 */
	public NSAL0450SMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0450SMsg is constructor.
	 * The initialization when the instance of NSAL0450SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0450SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		coaAfflAcctNm_H = (EZDSStringItem)newItem("coaAfflAcctNm_H");
		dsContrPk_H = (EZDSBigDecimalItem)newItem("dsContrPk_H");
		xxModeCd = (EZDSStringItem)newItem("xxModeCd");
		glblCmpyCd = (EZDSStringItem)newItem("glblCmpyCd");
		xxPageShowFromNum = (EZDSBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDSBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDSBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDSBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDSBigDecimalItem)newItem("xxPageShowTotNum");
		A = (business.blap.NSAL0450.NSAL0450_ASMsgArray)newMsgArray("A");
		xxNum = (EZDSBigDecimalItem)newItem("xxNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0450SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0450SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"coaAfflAcctNm_H", "coaAfflAcctNm_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"dsContrPk_H", "dsContrPk_H", "H", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "10000", "business.blap.NSAL0450.NSAL0450_ASMsgArray", null, null},
	
	{"xxNum", "xxNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"COA_AFFL_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAfflAcctNm_H
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_H
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
		null,	//A
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum
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

