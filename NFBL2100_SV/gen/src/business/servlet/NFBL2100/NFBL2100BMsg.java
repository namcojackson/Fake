//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20171116154250000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2100BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFBL2100;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFBL2100 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFBL2100BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDBDateItem              slsDt;

    /** AP_DS_WF_STS_CD_SV*/
	public final EZDBStringItem              apDsWfStsCd_SV;

    /** AP_DS_WF_STS_CD_CD*/
	public final EZDBStringItemArray              apDsWfStsCd_CD;

    /** AP_DS_WF_STS_DESC_TXT_SC*/
	public final EZDBStringItemArray              apDsWfStsDescTxt_SC;

    /** VND_CD*/
	public final EZDBStringItem              vndCd;

    /** DPLY_VND_NM*/
	public final EZDBStringItem              dplyVndNm;

    /** XX_FROM_DT*/
	public final EZDBDateItem              xxFromDt;

    /** XX_TO_DT*/
	public final EZDBDateItem              xxToDt;

    /** CPO_ORD_NUM*/
	public final EZDBStringItem              cpoOrdNum;

    /** CPO_ORD_NUM_H*/
	public final EZDBStringItem              cpoOrdNum_H;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDBBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDBBigDecimalItem              xxPageShowTotNum;

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDBStringItem              xxComnColOrdTxt;

    /** A*/
	public final business.servlet.NFBL2100.NFBL2100_ABMsgArray              A;

    /** XX_SCR_EVENT_NM*/
	public final EZDBStringItem              xxScrEventNm;

    /** XX_POP_PRM*/
	public final EZDBStringItem              xxPopPrm;

    /** Z*/
	public final business.servlet.NFBL2100.NFBL2100_ZBMsgArray              Z;


	/**
	 * NFBL2100BMsg is constructor.
	 * The initialization when the instance of NFBL2100BMsg is generated.
	 */
	public NFBL2100BMsg() {
		this(false, -1);
	}

	/**
	 * NFBL2100BMsg is constructor.
	 * The initialization when the instance of NFBL2100BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFBL2100BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		slsDt = (EZDBDateItem)newItem("slsDt");
		apDsWfStsCd_SV = (EZDBStringItem)newItem("apDsWfStsCd_SV");
		apDsWfStsCd_CD = (EZDBStringItemArray)newItemArray("apDsWfStsCd_CD");
		apDsWfStsDescTxt_SC = (EZDBStringItemArray)newItemArray("apDsWfStsDescTxt_SC");
		vndCd = (EZDBStringItem)newItem("vndCd");
		dplyVndNm = (EZDBStringItem)newItem("dplyVndNm");
		xxFromDt = (EZDBDateItem)newItem("xxFromDt");
		xxToDt = (EZDBDateItem)newItem("xxToDt");
		cpoOrdNum = (EZDBStringItem)newItem("cpoOrdNum");
		cpoOrdNum_H = (EZDBStringItem)newItem("cpoOrdNum_H");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		xxComnColOrdTxt = (EZDBStringItem)newItem("xxComnColOrdTxt");
		A = (business.servlet.NFBL2100.NFBL2100_ABMsgArray)newMsgArray("A");
		xxScrEventNm = (EZDBStringItem)newItem("xxScrEventNm");
		xxPopPrm = (EZDBStringItem)newItem("xxPopPrm");
		Z = (business.servlet.NFBL2100.NFBL2100_ZBMsgArray)newMsgArray("Z");
	}

	/**
	 * get the type of array which is stored
	 * @return NFBL2100BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFBL2100BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"apDsWfStsCd_SV", "apDsWfStsCd_SV", "SV", null, TYPE_HANKAKUEISU, "2", null},
	{"apDsWfStsCd_CD", "apDsWfStsCd_CD", "CD", "99", TYPE_HANKAKUEISU, "2", null},
	{"apDsWfStsDescTxt_SC", "apDsWfStsDescTxt_SC", "SC", "99", TYPE_HANKAKUEISU, "50", null},
	{"vndCd", "vndCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dplyVndNm", "dplyVndNm", null, null, TYPE_HANKAKUEISU, "320", null},
	{"xxFromDt", "xxFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxToDt", "xxToDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"cpoOrdNum_H", "cpoOrdNum_H", "H", null, TYPE_HANKAKUEISU, "8", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"A", "A", null, "17", "business.servlet.NFBL2100.NFBL2100_ABMsgArray", null, null},
	
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxPopPrm", "xxPopPrm", null, null, TYPE_HANKAKUEISU, "300", null},
	{"Z", "Z", null, "10", "business.servlet.NFBL2100.NFBL2100_ZBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO,  NO},	//slsDt
        {"AP_DS_WF_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apDsWfStsCd_SV
        {"AP_DS_WF_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apDsWfStsCd_CD
        {"AP_DS_WF_STS_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apDsWfStsDescTxt_SC
        {"VND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd
        {"DPLY_VND_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyVndNm
        {"XX_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxFromDt
        {"XX_TO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxToDt
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_H
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
		null,	//A
        {"XX_SCR_EVENT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm
		null,	//Z
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
