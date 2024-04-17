//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160324114211000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0060BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFAL0060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFAL0060 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFAL0060BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZUpdateDateTime*/
	public final EZDBStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDBStringItem              ezUpTimeZone;

    /** AJE_PTRN_IND_TP_CD_1*/
	public final EZDBStringItemArray              ajePtrnIndTpCd_1;

    /** AJE_PTRN_IND_TP_CD_2*/
	public final EZDBStringItemArray              ajePtrnIndTpCd_2;

    /** AJE_PTRN_IND_TP_CD_3*/
	public final EZDBStringItem              ajePtrnIndTpCd_3;

    /** AJE_PTRN_IND_TP_CD_T*/
	public final EZDBStringItem              ajePtrnIndTpCd_T;

    /** AJE_PTRN_IND_TP_CD_NW*/
	public final EZDBStringItem              ajePtrnIndTpCd_NW;

    /** AJE_PTRN_IND_TP_NM*/
	public final EZDBStringItem              ajePtrnIndTpNm;

    /** AJE_PTRN_IND_TP_NM_NW*/
	public final EZDBStringItem              ajePtrnIndTpNm_NW;

    /** AJE_PTRN_ACTL_CD_1*/
	public final EZDBStringItemArray              ajePtrnActlCd_1;

    /** AJE_PTRN_ACTL_CD_2*/
	public final EZDBStringItemArray              ajePtrnActlCd_2;

    /** AJE_PTRN_ACTL_CD_3*/
	public final EZDBStringItem              ajePtrnActlCd_3;

    /** AJE_PTRN_ACTL_CD_T*/
	public final EZDBStringItem              ajePtrnActlCd_T;

    /** AJE_PTRN_ACTL_NM*/
	public final EZDBStringItem              ajePtrnActlNm;

    /** AJE_CD_TBL_LIST_CD_1*/
	public final EZDBStringItemArray              ajeCdTblListCd_1;

    /** AJE_CD_TBL_LIST_NM_2*/
	public final EZDBStringItemArray              ajeCdTblListNm_2;

    /** AJE_CD_TBL_LIST_CD_3*/
	public final EZDBStringItem              ajeCdTblListCd_3;

    /** AJE_CD_TBL_LIST_CD_T*/
	public final EZDBStringItem              ajeCdTblListCd_T;

    /** XX_TBL_NM*/
	public final EZDBStringItem              xxTblNm;

    /** XX_TBL_CD_COL_NM*/
	public final EZDBStringItem              xxTblCdColNm;

    /** XX_TBL_NM_COL_NM*/
	public final EZDBStringItem              xxTblNmColNm;

    /** XX_TBL_SORT_COL_NM*/
	public final EZDBStringItem              xxTblSortColNm;

    /** AJE_INTFC_COL_TXT_IN*/
	public final EZDBStringItem              ajeIntfcColTxt_IN;

    /** A*/
	public final business.servlet.NFAL0060.NFAL0060_ABMsgArray              A;

    /** B*/
	public final business.servlet.NFAL0060.NFAL0060_BBMsgArray              B;

    /** C*/
	public final business.servlet.NFAL0060.NFAL0060_CBMsgArray              C;

    /** D*/
	public final business.servlet.NFAL0060.NFAL0060_DBMsgArray              D;

    /** XX_TOT_CNT*/
	public final EZDBBigDecimalItem              xxTotCnt;

    /** XX_TOT_CNT_AD*/
	public final EZDBBigDecimalItem              xxTotCnt_AD;


	/**
	 * NFAL0060BMsg is constructor.
	 * The initialization when the instance of NFAL0060BMsg is generated.
	 */
	public NFAL0060BMsg() {
		this(false, -1);
	}

	/**
	 * NFAL0060BMsg is constructor.
	 * The initialization when the instance of NFAL0060BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFAL0060BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezUpTime = (EZDBStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDBStringItem)newItem("ezUpTimeZone");
		ajePtrnIndTpCd_1 = (EZDBStringItemArray)newItemArray("ajePtrnIndTpCd_1");
		ajePtrnIndTpCd_2 = (EZDBStringItemArray)newItemArray("ajePtrnIndTpCd_2");
		ajePtrnIndTpCd_3 = (EZDBStringItem)newItem("ajePtrnIndTpCd_3");
		ajePtrnIndTpCd_T = (EZDBStringItem)newItem("ajePtrnIndTpCd_T");
		ajePtrnIndTpCd_NW = (EZDBStringItem)newItem("ajePtrnIndTpCd_NW");
		ajePtrnIndTpNm = (EZDBStringItem)newItem("ajePtrnIndTpNm");
		ajePtrnIndTpNm_NW = (EZDBStringItem)newItem("ajePtrnIndTpNm_NW");
		ajePtrnActlCd_1 = (EZDBStringItemArray)newItemArray("ajePtrnActlCd_1");
		ajePtrnActlCd_2 = (EZDBStringItemArray)newItemArray("ajePtrnActlCd_2");
		ajePtrnActlCd_3 = (EZDBStringItem)newItem("ajePtrnActlCd_3");
		ajePtrnActlCd_T = (EZDBStringItem)newItem("ajePtrnActlCd_T");
		ajePtrnActlNm = (EZDBStringItem)newItem("ajePtrnActlNm");
		ajeCdTblListCd_1 = (EZDBStringItemArray)newItemArray("ajeCdTblListCd_1");
		ajeCdTblListNm_2 = (EZDBStringItemArray)newItemArray("ajeCdTblListNm_2");
		ajeCdTblListCd_3 = (EZDBStringItem)newItem("ajeCdTblListCd_3");
		ajeCdTblListCd_T = (EZDBStringItem)newItem("ajeCdTblListCd_T");
		xxTblNm = (EZDBStringItem)newItem("xxTblNm");
		xxTblCdColNm = (EZDBStringItem)newItem("xxTblCdColNm");
		xxTblNmColNm = (EZDBStringItem)newItem("xxTblNmColNm");
		xxTblSortColNm = (EZDBStringItem)newItem("xxTblSortColNm");
		ajeIntfcColTxt_IN = (EZDBStringItem)newItem("ajeIntfcColTxt_IN");
		A = (business.servlet.NFAL0060.NFAL0060_ABMsgArray)newMsgArray("A");
		B = (business.servlet.NFAL0060.NFAL0060_BBMsgArray)newMsgArray("B");
		C = (business.servlet.NFAL0060.NFAL0060_CBMsgArray)newMsgArray("C");
		D = (business.servlet.NFAL0060.NFAL0060_DBMsgArray)newMsgArray("D");
		xxTotCnt = (EZDBBigDecimalItem)newItem("xxTotCnt");
		xxTotCnt_AD = (EZDBBigDecimalItem)newItem("xxTotCnt_AD");
	}

	/**
	 * get the type of array which is stored
	 * @return NFAL0060BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFAL0060BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	{"ajePtrnIndTpCd_1", "ajePtrnIndTpCd_1", "1", "99", TYPE_HANKAKUEISU, "3", null},
	{"ajePtrnIndTpCd_2", "ajePtrnIndTpCd_2", "2", "99", TYPE_HANKAKUEISU, "3", null},
	{"ajePtrnIndTpCd_3", "ajePtrnIndTpCd_3", "3", null, TYPE_HANKAKUEISU, "3", null},
	{"ajePtrnIndTpCd_T", "ajePtrnIndTpCd_T", "T", null, TYPE_HANKAKUEISU, "3", null},
	{"ajePtrnIndTpCd_NW", "ajePtrnIndTpCd_NW", "NW", null, TYPE_HANKAKUEISU, "3", null},
	{"ajePtrnIndTpNm", "ajePtrnIndTpNm", null, null, TYPE_HANKAKUEISU, "100", null},
	{"ajePtrnIndTpNm_NW", "ajePtrnIndTpNm_NW", "NW", null, TYPE_HANKAKUEISU, "100", null},
	{"ajePtrnActlCd_1", "ajePtrnActlCd_1", "1", "99", TYPE_HANKAKUEISU, "20", null},
	{"ajePtrnActlCd_2", "ajePtrnActlCd_2", "2", "99", TYPE_HANKAKUEISU, "20", null},
	{"ajePtrnActlCd_3", "ajePtrnActlCd_3", "3", null, TYPE_HANKAKUEISU, "20", null},
	{"ajePtrnActlCd_T", "ajePtrnActlCd_T", "T", null, TYPE_HANKAKUEISU, "20", null},
	{"ajePtrnActlNm", "ajePtrnActlNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"ajeCdTblListCd_1", "ajeCdTblListCd_1", "1", "99", TYPE_HANKAKUEISU, "2", null},
	{"ajeCdTblListNm_2", "ajeCdTblListNm_2", "2", "99", TYPE_HANKAKUEISU, "30", null},
	{"ajeCdTblListCd_3", "ajeCdTblListCd_3", "3", null, TYPE_HANKAKUEISU, "2", null},
	{"ajeCdTblListCd_T", "ajeCdTblListCd_T", "T", null, TYPE_HANKAKUEISU, "2", null},
	{"xxTblNm", "xxTblNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblCdColNm", "xxTblCdColNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblNmColNm", "xxTblNmColNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblSortColNm", "xxTblSortColNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"ajeIntfcColTxt_IN", "ajeIntfcColTxt_IN", "IN", null, TYPE_HANKAKUEISU, "30", null},
	{"A", "A", null, "200", "business.servlet.NFAL0060.NFAL0060_ABMsgArray", null, null},
	
	{"B", "B", null, "200", "business.servlet.NFAL0060.NFAL0060_BBMsgArray", null, null},
	
	{"C", "C", null, "200", "business.servlet.NFAL0060.NFAL0060_CBMsgArray", null, null},
	
	{"D", "D", null, "200", "business.servlet.NFAL0060.NFAL0060_DBMsgArray", null, null},
	
	{"xxTotCnt", "xxTotCnt", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxTotCnt_AD", "xxTotCnt_AD", "AD", null, TYPE_SEISU_SYOSU, "10", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"AJE_PTRN_IND_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_1
        {"AJE_PTRN_IND_TP_CD", YES,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_2
        {"AJE_PTRN_IND_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_3
        {"AJE_PTRN_IND_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_T
        {"AJE_PTRN_IND_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_NW
        {"AJE_PTRN_IND_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpNm
        {"AJE_PTRN_IND_TP_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpNm_NW
        {"AJE_PTRN_ACTL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_1
        {"AJE_PTRN_ACTL_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_2
        {"AJE_PTRN_ACTL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_3
        {"AJE_PTRN_ACTL_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_T
        {"AJE_PTRN_ACTL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlNm
        {"AJE_CD_TBL_LIST_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCdTblListCd_1
        {"AJE_CD_TBL_LIST_NM", YES,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCdTblListNm_2
        {"AJE_CD_TBL_LIST_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCdTblListCd_3
        {"AJE_CD_TBL_LIST_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCdTblListCd_T
        {"XX_TBL_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNm
        {"XX_TBL_CD_COL_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblCdColNm
        {"XX_TBL_NM_COL_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNmColNm
        {"XX_TBL_SORT_COL_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblSortColNm
        {"AJE_INTFC_COL_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeIntfcColTxt_IN
		null,	//A
		null,	//B
		null,	//C
		null,	//D
        {"XX_TOT_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotCnt
        {"XX_TOT_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotCnt_AD
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

