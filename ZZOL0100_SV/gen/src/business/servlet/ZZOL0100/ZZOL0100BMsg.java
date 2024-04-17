//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20110406113354000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZOL0100BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZOL0100;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZOL0100 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZOL0100BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SCR_NM*/
	public final EZDBStringItem              xxScrNm;

    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** GLBL_CMPY_CD_BK*/
	public final EZDBStringItem              glblCmpyCd_BK;

    /** GLBL_CMPY_NM*/
	public final EZDBStringItem              glblCmpyNm;

    /** MENU_INFO_PK*/
	public final EZDBBigDecimalItem              menuInfoPk;

    /** MENU_INFO_TXT*/
	public final EZDBStringItem              menuInfoTxt;

    /** XX_CHK_BOX*/
	public final EZDBStringItem              xxChkBox;

    /** MENU_INFO_SORT_NUM*/
	public final EZDBBigDecimalItem              menuInfoSortNum;

    /** MENU_EFF_FROM_DT*/
	public final EZDBDateItem              menuEffFromDt;

    /** MENU_EFF_FROM_TM_F1*/
	public final EZDBStringItemArray              menuEffFromTm_F1;

    /** XX_HRS_MN_F2*/
	public final EZDBStringItemArray              xxHrsMn_F2;

    /** MENU_EFF_FROM_TM_F3*/
	public final EZDBStringItem              menuEffFromTm_F3;

    /** MENU_EFF_THRU_DT*/
	public final EZDBDateItem              menuEffThruDt;

    /** MENU_EFF_THRU_TM_T1*/
	public final EZDBStringItemArray              menuEffThruTm_T1;

    /** XX_HRS_MN_T2*/
	public final EZDBStringItemArray              xxHrsMn_T2;

    /** MENU_EFF_THRU_TM_T3*/
	public final EZDBStringItem              menuEffThruTm_T3;

    /** _EZUpdateDateTime*/
	public final EZDBStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDBStringItem              ezUpTimeZone;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;

    /** A*/
	public final business.servlet.ZZOL0100.ZZOL0100_ABMsgArray              A;

    /** MENU_EFF_FROM_DT_P0*/
	public final EZDBDateItem              menuEffFromDt_P0;

    /** MENU_EFF_FROM_TM_P1*/
	public final EZDBStringItemArray              menuEffFromTm_P1;

    /** XX_HRS_MN_P2*/
	public final EZDBStringItemArray              xxHrsMn_P2;

    /** MENU_EFF_FROM_TM_P3*/
	public final EZDBStringItem              menuEffFromTm_P3;

    /** MENU_INFO_TXT_P1*/
	public final EZDBStringItem              menuInfoTxt_P1;


	/**
	 * ZZOL0100BMsg is constructor.
	 * The initialization when the instance of ZZOL0100BMsg is generated.
	 */
	public ZZOL0100BMsg() {
		this(false, -1);
	}

	/**
	 * ZZOL0100BMsg is constructor.
	 * The initialization when the instance of ZZOL0100BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZOL0100BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxScrNm = (EZDBStringItem)newItem("xxScrNm");
		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		glblCmpyCd_BK = (EZDBStringItem)newItem("glblCmpyCd_BK");
		glblCmpyNm = (EZDBStringItem)newItem("glblCmpyNm");
		menuInfoPk = (EZDBBigDecimalItem)newItem("menuInfoPk");
		menuInfoTxt = (EZDBStringItem)newItem("menuInfoTxt");
		xxChkBox = (EZDBStringItem)newItem("xxChkBox");
		menuInfoSortNum = (EZDBBigDecimalItem)newItem("menuInfoSortNum");
		menuEffFromDt = (EZDBDateItem)newItem("menuEffFromDt");
		menuEffFromTm_F1 = (EZDBStringItemArray)newItemArray("menuEffFromTm_F1");
		xxHrsMn_F2 = (EZDBStringItemArray)newItemArray("xxHrsMn_F2");
		menuEffFromTm_F3 = (EZDBStringItem)newItem("menuEffFromTm_F3");
		menuEffThruDt = (EZDBDateItem)newItem("menuEffThruDt");
		menuEffThruTm_T1 = (EZDBStringItemArray)newItemArray("menuEffThruTm_T1");
		xxHrsMn_T2 = (EZDBStringItemArray)newItemArray("xxHrsMn_T2");
		menuEffThruTm_T3 = (EZDBStringItem)newItem("menuEffThruTm_T3");
		ezUpTime = (EZDBStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDBStringItem)newItem("ezUpTimeZone");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		A = (business.servlet.ZZOL0100.ZZOL0100_ABMsgArray)newMsgArray("A");
		menuEffFromDt_P0 = (EZDBDateItem)newItem("menuEffFromDt_P0");
		menuEffFromTm_P1 = (EZDBStringItemArray)newItemArray("menuEffFromTm_P1");
		xxHrsMn_P2 = (EZDBStringItemArray)newItemArray("xxHrsMn_P2");
		menuEffFromTm_P3 = (EZDBStringItem)newItem("menuEffFromTm_P3");
		menuInfoTxt_P1 = (EZDBStringItem)newItem("menuInfoTxt_P1");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZOL0100BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZOL0100BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxScrNm", "xxScrNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"glblCmpyCd_BK", "glblCmpyCd_BK", "BK", null, TYPE_HANKAKUEISU, "4", null},
	{"glblCmpyNm", "glblCmpyNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"menuInfoPk", "menuInfoPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"menuInfoTxt", "menuInfoTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxChkBox", "xxChkBox", null, null, TYPE_HANKAKUEIJI, "1", null},
	{"menuInfoSortNum", "menuInfoSortNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"menuEffFromDt", "menuEffFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"menuEffFromTm_F1", "menuEffFromTm_F1", "F1", "30", TYPE_HANKAKUSUJI, "6", null},
	{"xxHrsMn_F2", "xxHrsMn_F2", "F2", "30", TYPE_HANKAKUEISU, "5", null},
	{"menuEffFromTm_F3", "menuEffFromTm_F3", "F3", null, TYPE_HANKAKUSUJI, "6", null},
	{"menuEffThruDt", "menuEffThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"menuEffThruTm_T1", "menuEffThruTm_T1", "T1", "30", TYPE_HANKAKUSUJI, "6", null},
	{"xxHrsMn_T2", "xxHrsMn_T2", "T2", "30", TYPE_HANKAKUEISU, "5", null},
	{"menuEffThruTm_T3", "menuEffThruTm_T3", "T3", null, TYPE_HANKAKUSUJI, "6", null},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "40", "business.servlet.ZZOL0100.ZZOL0100_ABMsgArray", null, null},
	
	{"menuEffFromDt_P0", "menuEffFromDt_P0", "P0", null, TYPE_NENTSUKIHI, "8", null},
	{"menuEffFromTm_P1", "menuEffFromTm_P1", "P1", "30", TYPE_HANKAKUSUJI, "6", null},
	{"xxHrsMn_P2", "xxHrsMn_P2", "P2", "30", TYPE_HANKAKUEISU, "5", null},
	{"menuEffFromTm_P3", "menuEffFromTm_P3", "P3", null, TYPE_HANKAKUSUJI, "6", null},
	{"menuInfoTxt_P1", "menuInfoTxt_P1", "P1", null, TYPE_HANKAKUEISU, "2000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SCR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm
        {"GLBL_CMPY_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"GLBL_CMPY_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_BK
        {"GLBL_CMPY_NM",  NO,  null,null,"0", NO, NO, NO, NO,"15",null,null, null,  NO,  NO},	//glblCmpyNm
        {"MENU_INFO_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuInfoPk
        {"MENU_INFO_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuInfoTxt
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"MENU_INFO_SORT_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuInfoSortNum
        {"MENU_EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//menuEffFromDt
        {"MENU_EFF_FROM_TM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuEffFromTm_F1
        {"XX_HRS_MN",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrsMn_F2
        {"MENU_EFF_FROM_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuEffFromTm_F3
        {"MENU_EFF_THRU_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//menuEffThruDt
        {"MENU_EFF_THRU_TM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuEffThruTm_T1
        {"XX_HRS_MN",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrsMn_T2
        {"MENU_EFF_THRU_TM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuEffThruTm_T3
        {"_EZUpdateDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_SORT_TBL_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
		null,	//A
        {"MENU_EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//menuEffFromDt_P0
        {"MENU_EFF_FROM_TM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuEffFromTm_P1
        {"XX_HRS_MN",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrsMn_P2
        {"MENU_EFF_FROM_TM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuEffFromTm_P3
        {"MENU_INFO_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuInfoTxt_P1
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
