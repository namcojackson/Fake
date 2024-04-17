//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180702165712000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1310BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1310;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1310 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1310BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDBDateItem              slsDt;

    /** SVC_COV_TMPL_TP_CD_SV*/
	public final EZDBStringItem              svcCovTmplTpCd_SV;

    /** SVC_COV_TMPL_TP_CD_CD*/
	public final EZDBStringItemArray              svcCovTmplTpCd_CD;

    /** SVC_COV_TMPL_TP_DESC_TXT_SC*/
	public final EZDBStringItemArray              svcCovTmplTpDescTxt_SC;

    /** SVC_TERM_ATTRB_DESC_TXT_H*/
	public final EZDBStringItem              svcTermAttrbDescTxt_H;

    /** XX_CHK_BOX_H*/
	public final EZDBStringItem              xxChkBox_H;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDBStringItem              xxComnColOrdTxt;

    /** A*/
	public final business.servlet.NSAL1310.NSAL1310_ABMsgArray              A;

    /** Z*/
	public final business.servlet.NSAL1310.NSAL1310_ZBMsgArray              Z;

    /** XX_SCR_EVENT_NM*/
	public final EZDBStringItem              xxScrEventNm;

    /** XX_NUM*/
	public final EZDBBigDecimalItem              xxNum;

    /** XX_LIST_NUM*/
	public final EZDBBigDecimalItem              xxListNum;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_FROM_NUM_BK*/
	public final EZDBBigDecimalItem              xxPageShowFromNum_BK;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDBBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDBBigDecimalItem              xxPageShowTotNum;


	/**
	 * NSAL1310BMsg is constructor.
	 * The initialization when the instance of NSAL1310BMsg is generated.
	 */
	public NSAL1310BMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1310BMsg is constructor.
	 * The initialization when the instance of NSAL1310BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1310BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		slsDt = (EZDBDateItem)newItem("slsDt");
		svcCovTmplTpCd_SV = (EZDBStringItem)newItem("svcCovTmplTpCd_SV");
		svcCovTmplTpCd_CD = (EZDBStringItemArray)newItemArray("svcCovTmplTpCd_CD");
		svcCovTmplTpDescTxt_SC = (EZDBStringItemArray)newItemArray("svcCovTmplTpDescTxt_SC");
		svcTermAttrbDescTxt_H = (EZDBStringItem)newItem("svcTermAttrbDescTxt_H");
		xxChkBox_H = (EZDBStringItem)newItem("xxChkBox_H");
		xxComnColOrdTxt = (EZDBStringItem)newItem("xxComnColOrdTxt");
		A = (business.servlet.NSAL1310.NSAL1310_ABMsgArray)newMsgArray("A");
		Z = (business.servlet.NSAL1310.NSAL1310_ZBMsgArray)newMsgArray("Z");
		xxScrEventNm = (EZDBStringItem)newItem("xxScrEventNm");
		xxNum = (EZDBBigDecimalItem)newItem("xxNum");
		xxListNum = (EZDBBigDecimalItem)newItem("xxListNum");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowFromNum_BK = (EZDBBigDecimalItem)newItem("xxPageShowFromNum_BK");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1310BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1310BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"svcCovTmplTpCd_SV", "svcCovTmplTpCd_SV", "SV", null, TYPE_HANKAKUEISU, "2", null},
	{"svcCovTmplTpCd_CD", "svcCovTmplTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "2", null},
	{"svcCovTmplTpDescTxt_SC", "svcCovTmplTpDescTxt_SC", "SC", "99", TYPE_HANKAKUEISU, "50", null},
	{"svcTermAttrbDescTxt_H", "svcTermAttrbDescTxt_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"xxChkBox_H", "xxChkBox_H", "H", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"A", "A", null, "100", "business.servlet.NSAL1310.NSAL1310_ABMsgArray", null, null},
	
	{"Z", "Z", null, "99", "business.servlet.NSAL1310.NSAL1310_ZBMsgArray", null, null},
	
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxNum", "xxNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxListNum", "xxListNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowFromNum_BK", "xxPageShowFromNum_BK", "BK", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO,  NO},	//slsDt
        {"SVC_COV_TMPL_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCovTmplTpCd_SV
        {"SVC_COV_TMPL_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCovTmplTpCd_CD
        {"SVC_COV_TMPL_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCovTmplTpDescTxt_SC
        {"SVC_TERM_ATTRB_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermAttrbDescTxt_H
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
		null,	//A
		null,	//Z
        {"XX_SCR_EVENT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"XX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum
        {"XX_LIST_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxListNum
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_BK
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
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

