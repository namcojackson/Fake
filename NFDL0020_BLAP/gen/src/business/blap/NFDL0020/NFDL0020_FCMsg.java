//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230602103740000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0020_FCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0020 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0020_FCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CLT_NOTE_DTL_PK_FD*/
	public final EZDCBigDecimalItem              cltNoteDtlPk_FD;

    /** CRAT_DT_FD*/
	public final EZDCDateItem              cratDt_FD;

    /** CRAT_TS_DPLY_TXT_FD*/
	public final EZDCStringItem              cratTsDplyTxt_FD;

    /** CLT_NOTE_TP_CD_FD*/
	public final EZDCStringItem              cltNoteTpCd_FD;

    /** CLT_NOTE_TP_DESC_TXT_FD*/
	public final EZDCStringItem              cltNoteTpDescTxt_FD;

    /** COL_NOTE_SUBJ_TXT_FD*/
	public final EZDCStringItem              colNoteSubjTxt_FD;

    /** XX_ML_BODY_TXT_FD*/
	public final EZDCStringItem              xxMlBodyTxt_FD;

    /** CRAT_USR_ID_FD*/
	public final EZDCStringItem              cratUsrId_FD;

    /** XX_PSN_NM_FD*/
	public final EZDCStringItem              xxPsnNm_FD;

    /** XX_SORT_KEY_TXT_FD*/
	public final EZDCStringItem              xxSortKeyTxt_FD;


	/**
	 * NFDL0020_FCMsg is constructor.
	 * The initialization when the instance of NFDL0020_FCMsg is generated.
	 */
	public NFDL0020_FCMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0020_FCMsg is constructor.
	 * The initialization when the instance of NFDL0020_FCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0020_FCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cltNoteDtlPk_FD = (EZDCBigDecimalItem)newItem("cltNoteDtlPk_FD");
		cratDt_FD = (EZDCDateItem)newItem("cratDt_FD");
		cratTsDplyTxt_FD = (EZDCStringItem)newItem("cratTsDplyTxt_FD");
		cltNoteTpCd_FD = (EZDCStringItem)newItem("cltNoteTpCd_FD");
		cltNoteTpDescTxt_FD = (EZDCStringItem)newItem("cltNoteTpDescTxt_FD");
		colNoteSubjTxt_FD = (EZDCStringItem)newItem("colNoteSubjTxt_FD");
		xxMlBodyTxt_FD = (EZDCStringItem)newItem("xxMlBodyTxt_FD");
		cratUsrId_FD = (EZDCStringItem)newItem("cratUsrId_FD");
		xxPsnNm_FD = (EZDCStringItem)newItem("xxPsnNm_FD");
		xxSortKeyTxt_FD = (EZDCStringItem)newItem("xxSortKeyTxt_FD");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0020_FCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0020_FCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cltNoteDtlPk_FD", "cltNoteDtlPk_FD", "FD", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cratDt_FD", "cratDt_FD", "FD", null, TYPE_NENTSUKIHI, "8", null},
	{"cratTsDplyTxt_FD", "cratTsDplyTxt_FD", "FD", null, TYPE_HANKAKUEISU, "19", null},
	{"cltNoteTpCd_FD", "cltNoteTpCd_FD", "FD", null, TYPE_HANKAKUEISU, "2", null},
	{"cltNoteTpDescTxt_FD", "cltNoteTpDescTxt_FD", "FD", null, TYPE_HANKAKUEISU, "50", null},
	{"colNoteSubjTxt_FD", "colNoteSubjTxt_FD", "FD", null, TYPE_HANKAKUEISU, "100", null},
	{"xxMlBodyTxt_FD", "xxMlBodyTxt_FD", "FD", null, TYPE_KONZAI, "65536", null},
	{"cratUsrId_FD", "cratUsrId_FD", "FD", null, TYPE_HANKAKUEISU, "16", null},
	{"xxPsnNm_FD", "xxPsnNm_FD", "FD", null, TYPE_HANKAKUEISU, "62", null},
	{"xxSortKeyTxt_FD", "xxSortKeyTxt_FD", "FD", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CLT_NOTE_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltNoteDtlPk_FD
        {"CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratDt_FD
        {"CRAT_TS_DPLY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratTsDplyTxt_FD
        {"CLT_NOTE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltNoteTpCd_FD
        {"CLT_NOTE_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltNoteTpDescTxt_FD
        {"COL_NOTE_SUBJ_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//colNoteSubjTxt_FD
        {"XX_ML_BODY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMlBodyTxt_FD
        {"CRAT_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratUsrId_FD
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_FD
        {"XX_SORT_KEY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortKeyTxt_FD
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

