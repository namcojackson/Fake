//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230602103743000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0020_FSMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NFDL0020 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0020_FSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CLT_NOTE_DTL_PK_FD*/
	public final EZDSBigDecimalItem              cltNoteDtlPk_FD;

    /** CRAT_DT_FD*/
	public final EZDSDateItem              cratDt_FD;

    /** CRAT_TS_DPLY_TXT_FD*/
	public final EZDSStringItem              cratTsDplyTxt_FD;

    /** CLT_NOTE_TP_CD_FD*/
	public final EZDSStringItem              cltNoteTpCd_FD;

    /** CLT_NOTE_TP_DESC_TXT_FD*/
	public final EZDSStringItem              cltNoteTpDescTxt_FD;

    /** COL_NOTE_SUBJ_TXT_FD*/
	public final EZDSStringItem              colNoteSubjTxt_FD;

    /** XX_ML_BODY_TXT_FD*/
	public final EZDSStringItem              xxMlBodyTxt_FD;

    /** CRAT_USR_ID_FD*/
	public final EZDSStringItem              cratUsrId_FD;

    /** XX_PSN_NM_FD*/
	public final EZDSStringItem              xxPsnNm_FD;

    /** XX_SORT_KEY_TXT_FD*/
	public final EZDSStringItem              xxSortKeyTxt_FD;


	/**
	 * NFDL0020_FSMsg is constructor.
	 * The initialization when the instance of NFDL0020_FSMsg is generated.
	 */
	public NFDL0020_FSMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0020_FSMsg is constructor.
	 * The initialization when the instance of NFDL0020_FSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0020_FSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cltNoteDtlPk_FD = (EZDSBigDecimalItem)newItem("cltNoteDtlPk_FD");
		cratDt_FD = (EZDSDateItem)newItem("cratDt_FD");
		cratTsDplyTxt_FD = (EZDSStringItem)newItem("cratTsDplyTxt_FD");
		cltNoteTpCd_FD = (EZDSStringItem)newItem("cltNoteTpCd_FD");
		cltNoteTpDescTxt_FD = (EZDSStringItem)newItem("cltNoteTpDescTxt_FD");
		colNoteSubjTxt_FD = (EZDSStringItem)newItem("colNoteSubjTxt_FD");
		xxMlBodyTxt_FD = (EZDSStringItem)newItem("xxMlBodyTxt_FD");
		cratUsrId_FD = (EZDSStringItem)newItem("cratUsrId_FD");
		xxPsnNm_FD = (EZDSStringItem)newItem("xxPsnNm_FD");
		xxSortKeyTxt_FD = (EZDSStringItem)newItem("xxSortKeyTxt_FD");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0020_FSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0020_FSMsgArray();
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

