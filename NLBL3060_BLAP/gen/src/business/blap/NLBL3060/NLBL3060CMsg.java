//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20231027190156000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3060CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3060 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3060CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** XX_SORT_TBL_NM*/
	public final EZDCStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDCStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDCStringItem              xxSortOrdByTxt;

    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** RTL_WH_CD*/
	public final EZDCStringItem              rtlWhCd;

    /** RTL_WH_NM*/
	public final EZDCStringItem              rtlWhNm;

    /** SCHD_COORD_PSN_CD*/
	public final EZDCStringItem              schdCoordPsnCd;

    /** FULL_PSN_NM*/
	public final EZDCStringItem              fullPsnNm;

    /** XX_CHK_BOX*/
	public final EZDCStringItem              xxChkBox;

    /** RTL_WH_CD_B*/
	public final EZDCStringItem              rtlWhCd_B;

    /** SCHD_COORD_PSN_CD_B*/
	public final EZDCStringItem              schdCoordPsnCd_B;

    /** A*/
	public final business.blap.NLBL3060.NLBL3060_ACMsgArray              A;

    /** P*/
	public final business.blap.NLBL3060.NLBL3060_PCMsgArray              P;

    /** XX_SCR_EVENT_NM*/
	public final EZDCStringItem              xxScrEventNm;

    /** XX_NUM*/
	public final EZDCBigDecimalItem              xxNum;

    /** XX_FILE_DATA_UP*/
	public final EZDCMimeSourceItem              xxFileData_UP;

    /** XX_FILE_DATA_DL*/
	public final EZDCMimeSourceItem              xxFileData_DL;

    /** RTL_WH_CATG_CD_HD*/
	public final EZDCStringItem              rtlWhCatgCd_HD;

    /** RTL_WH_CATG_CD_PD*/
	public final EZDCStringItemArray              rtlWhCatgCd_PD;

    /** RTL_WH_CATG_NM_PD*/
	public final EZDCStringItemArray              rtlWhCatgNm_PD;

    /** PHYS_WH_CD_HD*/
	public final EZDCStringItem              physWhCd_HD;


	/**
	 * NLBL3060CMsg is constructor.
	 * The initialization when the instance of NLBL3060CMsg is generated.
	 */
	public NLBL3060CMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3060CMsg is constructor.
	 * The initialization when the instance of NLBL3060CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3060CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		rtlWhCd = (EZDCStringItem)newItem("rtlWhCd");
		rtlWhNm = (EZDCStringItem)newItem("rtlWhNm");
		schdCoordPsnCd = (EZDCStringItem)newItem("schdCoordPsnCd");
		fullPsnNm = (EZDCStringItem)newItem("fullPsnNm");
		xxChkBox = (EZDCStringItem)newItem("xxChkBox");
		rtlWhCd_B = (EZDCStringItem)newItem("rtlWhCd_B");
		schdCoordPsnCd_B = (EZDCStringItem)newItem("schdCoordPsnCd_B");
		A = (business.blap.NLBL3060.NLBL3060_ACMsgArray)newMsgArray("A");
		P = (business.blap.NLBL3060.NLBL3060_PCMsgArray)newMsgArray("P");
		xxScrEventNm = (EZDCStringItem)newItem("xxScrEventNm");
		xxNum = (EZDCBigDecimalItem)newItem("xxNum");
		xxFileData_UP = (EZDCMimeSourceItem)newItem("xxFileData_UP");
		xxFileData_DL = (EZDCMimeSourceItem)newItem("xxFileData_DL");
		rtlWhCatgCd_HD = (EZDCStringItem)newItem("rtlWhCatgCd_HD");
		rtlWhCatgCd_PD = (EZDCStringItemArray)newItemArray("rtlWhCatgCd_PD");
		rtlWhCatgNm_PD = (EZDCStringItemArray)newItemArray("rtlWhCatgNm_PD");
		physWhCd_HD = (EZDCStringItem)newItem("physWhCd_HD");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3060CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3060CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"rtlWhCd", "rtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm", "rtlWhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"schdCoordPsnCd", "schdCoordPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"fullPsnNm", "fullPsnNm", null, null, TYPE_HANKAKUEISU, "62", null},
	{"xxChkBox", "xxChkBox", null, null, TYPE_HANKAKUEIJI, "1", null},
	{"rtlWhCd_B", "rtlWhCd_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"schdCoordPsnCd_B", "schdCoordPsnCd_B", "B", null, TYPE_HANKAKUEISU, "8", null},
	{"A", "A", null, "40", "business.blap.NLBL3060.NLBL3060_ACMsgArray", null, null},
	
	{"P", "P", null, "99", "business.blap.NLBL3060.NLBL3060_PCMsgArray", null, null},
	
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxNum", "xxNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxFileData_UP", "xxFileData_UP", "UP", null, TYPE_UPLOAD, null, null},
	{"xxFileData_DL", "xxFileData_DL", "DL", null, TYPE_UPLOAD, null, null},
	{"rtlWhCatgCd_HD", "rtlWhCatgCd_HD", "HD", null, TYPE_HANKAKUEISU, "2", null},
	{"rtlWhCatgCd_PD", "rtlWhCatgCd_PD", "PD", "99", TYPE_HANKAKUEISU, "2", null},
	{"rtlWhCatgNm_PD", "rtlWhCatgNm_PD", "PD", "99", TYPE_HANKAKUEISU, "20", null},
	{"physWhCd_HD", "physWhCd_HD", "HD", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm
        {"SCHD_COORD_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdCoordPsnCd
        {"FULL_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fullPsnNm
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_B
        {"SCHD_COORD_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdCoordPsnCd_B
		null,	//A
		null,	//P
        {"XX_SCR_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_UP
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_DL
        {"RTL_WH_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgCd_HD
        {"RTL_WH_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgCd_PD
        {"RTL_WH_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgNm_PD
        {"PHYS_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physWhCd_HD
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
