//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190510174837000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZYPL0310_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZYPL0310;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZYPL0310 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZYPL0310_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX*/
	public final EZDBStringItem              xxChkBox;

    /** XX_NUM*/
	public final EZDBBigDecimalItem              xxNum;

    /** ATT_DATA_TP_CD_AO*/
	public final EZDBStringItemArray              attDataTpCd_AO;

    /** XX_ATT_DATA_TP_NM_AO*/
	public final EZDBStringItemArray              xxAttDataTpNm_AO;

    /** ATT_DATA_TP_CD_AI*/
	public final EZDBStringItem              attDataTpCd_AI;

    /** ATT_DOC_TP_CD_AO*/
	public final EZDBStringItemArray              attDocTpCd_AO;

    /** XX_ATT_DOC_TP_NM_AO*/
	public final EZDBStringItemArray              xxAttDocTpNm_AO;

    /** ATT_DOC_TP_CD_AI*/
	public final EZDBStringItem              attDocTpCd_AI;

    /** ATT_DATA_PK*/
	public final EZDBBigDecimalItem              attDataPk;

    /** ATT_DATA_NM*/
	public final EZDBStringItem              attDataNm;

    /** OTH_SYS_URL_AO*/
	public final EZDBStringItem              othSysUrl_AO;

    /** OTH_SYS_URL_BK*/
	public final EZDBStringItem              othSysUrl_BK;

    /** ATT_DATA_VOL*/
	public final EZDBBigDecimalItem              attDataVol;

    /** ATT_DATA_VOL_O*/
	public final EZDBBigDecimalItem              attDataVol_O;

    /** XX_FILE_VOL_UNIT*/
	public final EZDBStringItem              xxFileVolUnit;

    /** XX_ATT_DATA_CMNT_TXT*/
	public final EZDBStringItem              xxAttDataCmntTxt;

    /** ATT_DATA_DESC_TXT_FG*/
	public final EZDBStringItem              attDataDescTxt_FG;

    /** _EZRegistrationDateTime*/
	public final EZDBStringItem              ezInTime;

    /** _EZRegistrationDateTime_DT*/
	public final EZDBStringItem              ezInTime_DT;

    /** _EZRegistrationDateTime_TM*/
	public final EZDBStringItem              ezInTime_TM;

    /** _EZInUserID*/
	public final EZDBStringItem              ezInUserID;


	/**
	 * ZYPL0310_ABMsg is constructor.
	 * The initialization when the instance of ZYPL0310_ABMsg is generated.
	 */
	public ZYPL0310_ABMsg() {
		this(false, -1);
	}

	/**
	 * ZYPL0310_ABMsg is constructor.
	 * The initialization when the instance of ZYPL0310_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZYPL0310_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox = (EZDBStringItem)newItem("xxChkBox");
		xxNum = (EZDBBigDecimalItem)newItem("xxNum");
		attDataTpCd_AO = (EZDBStringItemArray)newItemArray("attDataTpCd_AO");
		xxAttDataTpNm_AO = (EZDBStringItemArray)newItemArray("xxAttDataTpNm_AO");
		attDataTpCd_AI = (EZDBStringItem)newItem("attDataTpCd_AI");
		attDocTpCd_AO = (EZDBStringItemArray)newItemArray("attDocTpCd_AO");
		xxAttDocTpNm_AO = (EZDBStringItemArray)newItemArray("xxAttDocTpNm_AO");
		attDocTpCd_AI = (EZDBStringItem)newItem("attDocTpCd_AI");
		attDataPk = (EZDBBigDecimalItem)newItem("attDataPk");
		attDataNm = (EZDBStringItem)newItem("attDataNm");
		othSysUrl_AO = (EZDBStringItem)newItem("othSysUrl_AO");
		othSysUrl_BK = (EZDBStringItem)newItem("othSysUrl_BK");
		attDataVol = (EZDBBigDecimalItem)newItem("attDataVol");
		attDataVol_O = (EZDBBigDecimalItem)newItem("attDataVol_O");
		xxFileVolUnit = (EZDBStringItem)newItem("xxFileVolUnit");
		xxAttDataCmntTxt = (EZDBStringItem)newItem("xxAttDataCmntTxt");
		attDataDescTxt_FG = (EZDBStringItem)newItem("attDataDescTxt_FG");
		ezInTime = (EZDBStringItem)newItem("ezInTime");
		ezInTime_DT = (EZDBStringItem)newItem("ezInTime_DT");
		ezInTime_TM = (EZDBStringItem)newItem("ezInTime_TM");
		ezInUserID = (EZDBStringItem)newItem("ezInUserID");
	}

	/**
	 * get the type of array which is stored
	 * @return ZYPL0310_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZYPL0310_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox", "xxChkBox", null, null, TYPE_HANKAKUEIJI, "1", null},
	{"xxNum", "xxNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"attDataTpCd_AO", "attDataTpCd_AO", "AO", "5", TYPE_HANKAKUEISU, "5", null},
	{"xxAttDataTpNm_AO", "xxAttDataTpNm_AO", "AO", "5", TYPE_HANKAKUEISU, "15", null},
	{"attDataTpCd_AI", "attDataTpCd_AI", "AI", null, TYPE_HANKAKUEISU, "5", null},
	{"attDocTpCd_AO", "attDocTpCd_AO", "AO", "99", TYPE_HANKAKUEISU, "5", null},
	{"xxAttDocTpNm_AO", "xxAttDocTpNm_AO", "AO", "99", TYPE_HANKAKUEISU, "50", null},
	{"attDocTpCd_AI", "attDocTpCd_AI", "AI", null, TYPE_HANKAKUEISU, "5", null},
	{"attDataPk", "attDataPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"attDataNm", "attDataNm", null, null, TYPE_HANKAKUEISU, "256", null},
	{"othSysUrl_AO", "othSysUrl_AO", "AO", null, TYPE_HANKAKUEISU, "1000", null},
	{"othSysUrl_BK", "othSysUrl_BK", "BK", null, TYPE_HANKAKUEISU, "1000", null},
	{"attDataVol", "attDataVol", null, null, TYPE_SEISU_SYOSU, "20", "0"},
	{"attDataVol_O", "attDataVol_O", "O", null, TYPE_SEISU_SYOSU, "20", "0"},
	{"xxFileVolUnit", "xxFileVolUnit", null, null, TYPE_HANKAKUEIJI, "4", null},
	{"xxAttDataCmntTxt", "xxAttDataCmntTxt", null, null, TYPE_KONZAI, "100000", null},
	{"attDataDescTxt_FG", "attDataDescTxt_FG", "FG", null, TYPE_KONZAI, "500", null},
	{"ezInTime", "ezInTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezInTime_DT", "ezInTime_DT", "DT", null, TYPE_HANKAKUEISU, "17", null},
	{"ezInTime_TM", "ezInTime_TM", "TM", null, TYPE_HANKAKUEISU, "17", null},
	{"ezInUserID", "ezInUserID", null, null, TYPE_HANKAKUEISU, "16", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"XX_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum
        {"ATT_DATA_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attDataTpCd_AO
        {"XX_ATT_DATA_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAttDataTpNm_AO
        {"ATT_DATA_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attDataTpCd_AI
        {"ATT_DOC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attDocTpCd_AO
        {"XX_ATT_DOC_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAttDocTpNm_AO
        {"ATT_DOC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attDocTpCd_AI
        {"ATT_DATA_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attDataPk
        {"ATT_DATA_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attDataNm
        {"OTH_SYS_URL",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//othSysUrl_AO
        {"OTH_SYS_URL",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//othSysUrl_BK
        {"ATT_DATA_VOL",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attDataVol
        {"ATT_DATA_VOL",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attDataVol_O
        {"XX_FILE_VOL_UNIT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileVolUnit
        {"XX_ATT_DATA_CMNT_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAttDataCmntTxt
        {"ATT_DATA_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attDataDescTxt_FG
        {"_EZRegistrationDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTime
        {"_EZRegistrationDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTime_DT
        {"_EZRegistrationDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTime_TM
        {"_EZInUserID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInUserID
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
