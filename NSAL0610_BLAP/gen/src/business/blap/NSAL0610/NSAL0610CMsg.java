//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160421185217000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0610CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0610;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0610 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0610CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDCDateItem              slsDt;

    /** DS_CONTR_PK*/
	public final EZDCBigDecimalItem              dsContrPk;

    /** DS_CONTR_NUM_H1*/
	public final EZDCStringItem              dsContrNum_H1;

    /** DS_CONTR_NUM_H2*/
	public final EZDCStringItem              dsContrNum_H2;

    /** DS_CONTR_CATG_CD*/
	public final EZDCStringItem              dsContrCatgCd;

    /** XX_CHK_BOX_H1*/
	public final EZDCStringItem              xxChkBox_H1;

    /** XX_CHK_BOX_H2*/
	public final EZDCStringItem              xxChkBox_H2;

    /** XX_SEL_FLG_HA*/
	public final EZDCStringItem              xxSelFlg_HA;

    /** XX_MODE_IND*/
	public final EZDCStringItem              xxModeInd;

    /** A*/
	public final business.blap.NSAL0610.NSAL0610_ACMsgArray              A;

    /** N*/
	public final business.blap.NSAL0610.NSAL0610_NCMsgArray              N;

    /** XX_PAGE_SHOW_FROM_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_A;

    /** XX_PAGE_SHOW_TO_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowToNum_A;

    /** XX_PAGE_SHOW_OF_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowOfNum_A;

    /** XX_PAGE_SHOW_FROM_NUM_N*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_N;

    /** XX_PAGE_SHOW_TO_NUM_N*/
	public final EZDCBigDecimalItem              xxPageShowToNum_N;

    /** XX_PAGE_SHOW_OF_NUM_N*/
	public final EZDCBigDecimalItem              xxPageShowOfNum_N;


	/**
	 * NSAL0610CMsg is constructor.
	 * The initialization when the instance of NSAL0610CMsg is generated.
	 */
	public NSAL0610CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0610CMsg is constructor.
	 * The initialization when the instance of NSAL0610CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0610CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		slsDt = (EZDCDateItem)newItem("slsDt");
		dsContrPk = (EZDCBigDecimalItem)newItem("dsContrPk");
		dsContrNum_H1 = (EZDCStringItem)newItem("dsContrNum_H1");
		dsContrNum_H2 = (EZDCStringItem)newItem("dsContrNum_H2");
		dsContrCatgCd = (EZDCStringItem)newItem("dsContrCatgCd");
		xxChkBox_H1 = (EZDCStringItem)newItem("xxChkBox_H1");
		xxChkBox_H2 = (EZDCStringItem)newItem("xxChkBox_H2");
		xxSelFlg_HA = (EZDCStringItem)newItem("xxSelFlg_HA");
		xxModeInd = (EZDCStringItem)newItem("xxModeInd");
		A = (business.blap.NSAL0610.NSAL0610_ACMsgArray)newMsgArray("A");
		N = (business.blap.NSAL0610.NSAL0610_NCMsgArray)newMsgArray("N");
		xxPageShowFromNum_A = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_A");
		xxPageShowToNum_A = (EZDCBigDecimalItem)newItem("xxPageShowToNum_A");
		xxPageShowOfNum_A = (EZDCBigDecimalItem)newItem("xxPageShowOfNum_A");
		xxPageShowFromNum_N = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_N");
		xxPageShowToNum_N = (EZDCBigDecimalItem)newItem("xxPageShowToNum_N");
		xxPageShowOfNum_N = (EZDCBigDecimalItem)newItem("xxPageShowOfNum_N");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0610CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0610CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsContrPk", "dsContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrNum_H1", "dsContrNum_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrNum_H2", "dsContrNum_H2", "H2", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrCatgCd", "dsContrCatgCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"xxChkBox_H1", "xxChkBox_H1", "H1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_H2", "xxChkBox_H2", "H2", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxSelFlg_HA", "xxSelFlg_HA", "HA", null, TYPE_HANKAKUEISU, "1", null},
	{"xxModeInd", "xxModeInd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"A", "A", null, "100", "business.blap.NSAL0610.NSAL0610_ACMsgArray", null, null},
	
	{"N", "N", null, "100", "business.blap.NSAL0610.NSAL0610_NCMsgArray", null, null},
	
	{"xxPageShowFromNum_A", "xxPageShowFromNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_A", "xxPageShowToNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_A", "xxPageShowOfNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowFromNum_N", "xxPageShowFromNum_N", "N", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_N", "xxPageShowToNum_N", "N", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_N", "xxPageShowOfNum_N", "N", null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_H1
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_H2
        {"DS_CONTR_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H2
        {"XX_SEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelFlg_HA
        {"XX_MODE_IND",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeInd
		null,	//A
		null,	//N
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_A
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_A
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_N
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_N
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_N
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
