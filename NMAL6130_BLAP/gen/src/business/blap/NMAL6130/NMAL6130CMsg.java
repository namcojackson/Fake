//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20100526120442000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6130CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6130;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6130 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6130CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** MSTR_ATT_DATA_DESC_TXT_I*/
	public final EZDCStringItem              mstrAttDataDescTxt_I;

    /** MSTR_ATT_DATA_NM_I*/
	public final EZDCStringItem              mstrAttDataNm_I;

    /** MSTR_ACTV_CD_I1*/
	public final EZDCStringItem              mstrActvCd_I1;

    /** MSTR_ACTV_CD_I2*/
	public final EZDCStringItemArray              mstrActvCd_I2;

    /** MSTR_ACTV_NM_I2*/
	public final EZDCStringItemArray              mstrActvNm_I2;

    /** CMPY_PK_I*/
	public final EZDCBigDecimalItem              cmpyPk_I;

    /** PTY_LOC_PK_I*/
	public final EZDCBigDecimalItem              ptyLocPk_I;

    /** MSTR_BIZ_ID_I*/
	public final EZDCStringItem              mstrBizId_I;

    /** MSTR_ATT_DATA_GRP_TXT_I*/
	public final EZDCStringItem              mstrAttDataGrpTxt_I;

    /** VAR_CHAR_CONST_NM_I1*/
	public final EZDCStringItem              varCharConstNm_I1;

    /** VAR_CHAR_CONST_VAL_I1*/
	public final EZDCStringItem              varCharConstVal_I1;

    /** VAR_CHAR_CONST_NM_I2*/
	public final EZDCStringItem              varCharConstNm_I2;

    /** VAR_CHAR_CONST_VAL_I2*/
	public final EZDCStringItem              varCharConstVal_I2;

    /** NUM_CONST_NM_I1*/
	public final EZDCStringItem              numConstNm_I1;

    /** NUM_CONST_VAL_I1*/
	public final EZDCBigDecimalItem              numConstVal_I1;

    /** NUM_CONST_NM_I2*/
	public final EZDCStringItem              numConstNm_I2;

    /** NUM_CONST_VAL_I2*/
	public final EZDCBigDecimalItem              numConstVal_I2;

    /** A*/
	public final business.blap.NMAL6130.NMAL6130_ACMsgArray              A;

    /** MSTR_ATT_DATA_DESC_TXT_O*/
	public final EZDCStringItem              mstrAttDataDescTxt_O;

    /** XX_CELL_IDX_DL*/
	public final EZDCBigDecimalItem              xxCellIdx_DL;

    /** XX_FILE_DATA_DL*/
	public final EZDCMimeSourceItem              xxFileData_DL;


	/**
	 * NMAL6130CMsg is constructor.
	 * The initialization when the instance of NMAL6130CMsg is generated.
	 */
	public NMAL6130CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6130CMsg is constructor.
	 * The initialization when the instance of NMAL6130CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6130CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		mstrAttDataDescTxt_I = (EZDCStringItem)newItem("mstrAttDataDescTxt_I");
		mstrAttDataNm_I = (EZDCStringItem)newItem("mstrAttDataNm_I");
		mstrActvCd_I1 = (EZDCStringItem)newItem("mstrActvCd_I1");
		mstrActvCd_I2 = (EZDCStringItemArray)newItemArray("mstrActvCd_I2");
		mstrActvNm_I2 = (EZDCStringItemArray)newItemArray("mstrActvNm_I2");
		cmpyPk_I = (EZDCBigDecimalItem)newItem("cmpyPk_I");
		ptyLocPk_I = (EZDCBigDecimalItem)newItem("ptyLocPk_I");
		mstrBizId_I = (EZDCStringItem)newItem("mstrBizId_I");
		mstrAttDataGrpTxt_I = (EZDCStringItem)newItem("mstrAttDataGrpTxt_I");
		varCharConstNm_I1 = (EZDCStringItem)newItem("varCharConstNm_I1");
		varCharConstVal_I1 = (EZDCStringItem)newItem("varCharConstVal_I1");
		varCharConstNm_I2 = (EZDCStringItem)newItem("varCharConstNm_I2");
		varCharConstVal_I2 = (EZDCStringItem)newItem("varCharConstVal_I2");
		numConstNm_I1 = (EZDCStringItem)newItem("numConstNm_I1");
		numConstVal_I1 = (EZDCBigDecimalItem)newItem("numConstVal_I1");
		numConstNm_I2 = (EZDCStringItem)newItem("numConstNm_I2");
		numConstVal_I2 = (EZDCBigDecimalItem)newItem("numConstVal_I2");
		A = (business.blap.NMAL6130.NMAL6130_ACMsgArray)newMsgArray("A");
		mstrAttDataDescTxt_O = (EZDCStringItem)newItem("mstrAttDataDescTxt_O");
		xxCellIdx_DL = (EZDCBigDecimalItem)newItem("xxCellIdx_DL");
		xxFileData_DL = (EZDCMimeSourceItem)newItem("xxFileData_DL");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6130CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6130CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"mstrAttDataDescTxt_I", "mstrAttDataDescTxt_I", "I", null, TYPE_HANKAKUEISU, "500", null},
	{"mstrAttDataNm_I", "mstrAttDataNm_I", "I", null, TYPE_HANKAKUEISU, "256", null},
	{"mstrActvCd_I1", "mstrActvCd_I1", "I1", null, TYPE_HANKAKUEISU, "2", null},
	{"mstrActvCd_I2", "mstrActvCd_I2", "I2", "99", TYPE_HANKAKUEISU, "2", null},
	{"mstrActvNm_I2", "mstrActvNm_I2", "I2", "99", TYPE_HANKAKUEISU, "100", null},
	{"cmpyPk_I", "cmpyPk_I", "I", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ptyLocPk_I", "ptyLocPk_I", "I", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mstrBizId_I", "mstrBizId_I", "I", null, TYPE_HANKAKUEISU, "8", null},
	{"mstrAttDataGrpTxt_I", "mstrAttDataGrpTxt_I", "I", null, TYPE_HANKAKUEISU, "500", null},
	{"varCharConstNm_I1", "varCharConstNm_I1", "I1", null, TYPE_HANKAKUEISU, "30", null},
	{"varCharConstVal_I1", "varCharConstVal_I1", "I1", null, TYPE_HANKAKUEISU, "1000", null},
	{"varCharConstNm_I2", "varCharConstNm_I2", "I2", null, TYPE_HANKAKUEISU, "30", null},
	{"varCharConstVal_I2", "varCharConstVal_I2", "I2", null, TYPE_HANKAKUEISU, "1000", null},
	{"numConstNm_I1", "numConstNm_I1", "I1", null, TYPE_HANKAKUEISU, "30", null},
	{"numConstVal_I1", "numConstVal_I1", "I1", null, TYPE_SEISU_SYOSU, "33", "5"},
	{"numConstNm_I2", "numConstNm_I2", "I2", null, TYPE_HANKAKUEISU, "30", null},
	{"numConstVal_I2", "numConstVal_I2", "I2", null, TYPE_SEISU_SYOSU, "33", "5"},
	{"A", "A", null, "20", "business.blap.NMAL6130.NMAL6130_ACMsgArray", null, null},
	
	{"mstrAttDataDescTxt_O", "mstrAttDataDescTxt_O", "O", null, TYPE_HANKAKUEISU, "500", null},
	{"xxCellIdx_DL", "xxCellIdx_DL", "DL", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"xxFileData_DL", "xxFileData_DL", "DL", null, TYPE_UPLOAD, null, null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"MSTR_ATT_DATA_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrAttDataDescTxt_I
        {"MSTR_ATT_DATA_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrAttDataNm_I
        {"MSTR_ACTV_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrActvCd_I1
        {"MSTR_ACTV_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrActvCd_I2
        {"MSTR_ACTV_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrActvNm_I2
        {"CMPY_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpyPk_I
        {"PTY_LOC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ptyLocPk_I
        {"MSTR_BIZ_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrBizId_I
        {"MSTR_ATT_DATA_GRP_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrAttDataGrpTxt_I
        {"VAR_CHAR_CONST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//varCharConstNm_I1
        {"VAR_CHAR_CONST_VAL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//varCharConstVal_I1
        {"VAR_CHAR_CONST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//varCharConstNm_I2
        {"VAR_CHAR_CONST_VAL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//varCharConstVal_I2
        {"NUM_CONST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//numConstNm_I1
        {"NUM_CONST_VAL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//numConstVal_I1
        {"NUM_CONST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//numConstNm_I2
        {"NUM_CONST_VAL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//numConstVal_I2
		null,	//A
        {"MSTR_ATT_DATA_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrAttDataDescTxt_O
        {"XX_CELL_IDX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCellIdx_DL
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_DL
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

