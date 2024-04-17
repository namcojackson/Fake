//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20100621114730000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6130_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6130;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6130 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6130_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX*/
	public final EZDBStringItem              xxChkBox;

    /** XX_NUM*/
	public final EZDBBigDecimalItem              xxNum;

    /** MSTR_ATT_DATA_PK*/
	public final EZDBBigDecimalItem              mstrAttDataPk;

    /** CMPY_PK*/
	public final EZDBBigDecimalItem              cmpyPk;

    /** PTY_LOC_PK*/
	public final EZDBBigDecimalItem              ptyLocPk;

    /** MSTR_ATT_DATA_NM*/
	public final EZDBStringItem              mstrAttDataNm;

    /** MSTR_ATT_DATA_VOL*/
	public final EZDBBigDecimalItem              mstrAttDataVol;

    /** MSTR_ATT_DATA_VOL_O*/
	public final EZDBBigDecimalItem              mstrAttDataVol_O;

    /** XX_FILE_VOL_UNIT*/
	public final EZDBStringItem              xxFileVolUnit;

    /** MSTR_ATT_DATA_DESC_TXT*/
	public final EZDBStringItem              mstrAttDataDescTxt;

    /** MSTR_ATT_DATA_DESC_TXT_FG*/
	public final EZDBStringItem              mstrAttDataDescTxt_FG;

    /** MSTR_ACTV_CD*/
	public final EZDBStringItem              mstrActvCd;

    /** MSTR_ACTV_NM*/
	public final EZDBStringItem              mstrActvNm;

    /** BAT_PROC_END_FLG*/
	public final EZDBStringItem              batProcEndFlg;

    /** _EZRegistrationDateTime*/
	public final EZDBStringItem              ezInTime;

    /** _EZRegistrationDateTime_DT*/
	public final EZDBStringItem              ezInTime_DT;

    /** _EZRegistrationDateTime_TM*/
	public final EZDBStringItem              ezInTime_TM;


	/**
	 * NMAL6130_ABMsg is constructor.
	 * The initialization when the instance of NMAL6130_ABMsg is generated.
	 */
	public NMAL6130_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6130_ABMsg is constructor.
	 * The initialization when the instance of NMAL6130_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6130_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox = (EZDBStringItem)newItem("xxChkBox");
		xxNum = (EZDBBigDecimalItem)newItem("xxNum");
		mstrAttDataPk = (EZDBBigDecimalItem)newItem("mstrAttDataPk");
		cmpyPk = (EZDBBigDecimalItem)newItem("cmpyPk");
		ptyLocPk = (EZDBBigDecimalItem)newItem("ptyLocPk");
		mstrAttDataNm = (EZDBStringItem)newItem("mstrAttDataNm");
		mstrAttDataVol = (EZDBBigDecimalItem)newItem("mstrAttDataVol");
		mstrAttDataVol_O = (EZDBBigDecimalItem)newItem("mstrAttDataVol_O");
		xxFileVolUnit = (EZDBStringItem)newItem("xxFileVolUnit");
		mstrAttDataDescTxt = (EZDBStringItem)newItem("mstrAttDataDescTxt");
		mstrAttDataDescTxt_FG = (EZDBStringItem)newItem("mstrAttDataDescTxt_FG");
		mstrActvCd = (EZDBStringItem)newItem("mstrActvCd");
		mstrActvNm = (EZDBStringItem)newItem("mstrActvNm");
		batProcEndFlg = (EZDBStringItem)newItem("batProcEndFlg");
		ezInTime = (EZDBStringItem)newItem("ezInTime");
		ezInTime_DT = (EZDBStringItem)newItem("ezInTime_DT");
		ezInTime_TM = (EZDBStringItem)newItem("ezInTime_TM");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6130_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6130_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox", "xxChkBox", null, null, TYPE_HANKAKUEIJI, "1", null},
	{"xxNum", "xxNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"mstrAttDataPk", "mstrAttDataPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cmpyPk", "cmpyPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ptyLocPk", "ptyLocPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mstrAttDataNm", "mstrAttDataNm", null, null, TYPE_HANKAKUEISU, "256", null},
	{"mstrAttDataVol", "mstrAttDataVol", null, null, TYPE_SEISU_SYOSU, "20", "0"},
	{"mstrAttDataVol_O", "mstrAttDataVol_O", "O", null, TYPE_SEISU_SYOSU, "20", "0"},
	{"xxFileVolUnit", "xxFileVolUnit", null, null, TYPE_HANKAKUEIJI, "4", null},
	{"mstrAttDataDescTxt", "mstrAttDataDescTxt", null, null, TYPE_HANKAKUEISU, "500", null},
	{"mstrAttDataDescTxt_FG", "mstrAttDataDescTxt_FG", "FG", null, TYPE_HANKAKUEISU, "500", null},
	{"mstrActvCd", "mstrActvCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"mstrActvNm", "mstrActvNm", null, null, TYPE_HANKAKUEISU, "100", null},
	{"batProcEndFlg", "batProcEndFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"ezInTime", "ezInTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezInTime_DT", "ezInTime_DT", "DT", null, TYPE_HANKAKUEISU, "17", null},
	{"ezInTime_TM", "ezInTime_TM", "TM", null, TYPE_HANKAKUEISU, "17", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"XX_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum
        {"MSTR_ATT_DATA_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrAttDataPk
        {"CMPY_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpyPk
        {"PTY_LOC_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ptyLocPk
        {"MSTR_ATT_DATA_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrAttDataNm
        {"MSTR_ATT_DATA_VOL",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrAttDataVol
        {"MSTR_ATT_DATA_VOL",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrAttDataVol_O
        {"XX_FILE_VOL_UNIT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileVolUnit
        {"MSTR_ATT_DATA_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,"1",null,null, null,  NO,  NO},	//mstrAttDataDescTxt
        {"MSTR_ATT_DATA_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrAttDataDescTxt_FG
        {"MSTR_ACTV_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrActvCd
        {"MSTR_ACTV_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrActvNm
        {"BAT_PROC_END_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcEndFlg
        {"_EZRegistrationDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTime
        {"_EZRegistrationDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTime_DT
        {"_EZRegistrationDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTime_TM
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
