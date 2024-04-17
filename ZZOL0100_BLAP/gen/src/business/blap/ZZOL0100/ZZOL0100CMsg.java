//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20110406113215000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZOL0100CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZOL0100;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZOL0100 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZZOL0100CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SCR_NM*/
	public final EZDCStringItem              xxScrNm;

    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** GLBL_CMPY_CD_BK*/
	public final EZDCStringItem              glblCmpyCd_BK;

    /** GLBL_CMPY_NM*/
	public final EZDCStringItem              glblCmpyNm;

    /** MENU_INFO_PK*/
	public final EZDCBigDecimalItem              menuInfoPk;

    /** MENU_INFO_TXT*/
	public final EZDCStringItem              menuInfoTxt;

    /** XX_CHK_BOX*/
	public final EZDCStringItem              xxChkBox;

    /** MENU_INFO_SORT_NUM*/
	public final EZDCBigDecimalItem              menuInfoSortNum;

    /** MENU_EFF_FROM_DT*/
	public final EZDCDateItem              menuEffFromDt;

    /** MENU_EFF_FROM_TM_F1*/
	public final EZDCStringItemArray              menuEffFromTm_F1;

    /** XX_HRS_MN_F2*/
	public final EZDCStringItemArray              xxHrsMn_F2;

    /** MENU_EFF_FROM_TM_F3*/
	public final EZDCStringItem              menuEffFromTm_F3;

    /** MENU_EFF_THRU_DT*/
	public final EZDCDateItem              menuEffThruDt;

    /** MENU_EFF_THRU_TM_T1*/
	public final EZDCStringItemArray              menuEffThruTm_T1;

    /** XX_HRS_MN_T2*/
	public final EZDCStringItemArray              xxHrsMn_T2;

    /** MENU_EFF_THRU_TM_T3*/
	public final EZDCStringItem              menuEffThruTm_T3;

    /** _EZUpdateDateTime*/
	public final EZDCStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDCStringItem              ezUpTimeZone;

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

    /** A*/
	public final business.blap.ZZOL0100.ZZOL0100_ACMsgArray              A;

    /** MENU_EFF_FROM_DT_P0*/
	public final EZDCDateItem              menuEffFromDt_P0;

    /** MENU_EFF_FROM_TM_P1*/
	public final EZDCStringItemArray              menuEffFromTm_P1;

    /** XX_HRS_MN_P2*/
	public final EZDCStringItemArray              xxHrsMn_P2;

    /** MENU_EFF_FROM_TM_P3*/
	public final EZDCStringItem              menuEffFromTm_P3;

    /** MENU_INFO_TXT_P1*/
	public final EZDCStringItem              menuInfoTxt_P1;


	/**
	 * ZZOL0100CMsg is constructor.
	 * The initialization when the instance of ZZOL0100CMsg is generated.
	 */
	public ZZOL0100CMsg() {
		this(false, -1);
	}

	/**
	 * ZZOL0100CMsg is constructor.
	 * The initialization when the instance of ZZOL0100CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZOL0100CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxScrNm = (EZDCStringItem)newItem("xxScrNm");
		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		glblCmpyCd_BK = (EZDCStringItem)newItem("glblCmpyCd_BK");
		glblCmpyNm = (EZDCStringItem)newItem("glblCmpyNm");
		menuInfoPk = (EZDCBigDecimalItem)newItem("menuInfoPk");
		menuInfoTxt = (EZDCStringItem)newItem("menuInfoTxt");
		xxChkBox = (EZDCStringItem)newItem("xxChkBox");
		menuInfoSortNum = (EZDCBigDecimalItem)newItem("menuInfoSortNum");
		menuEffFromDt = (EZDCDateItem)newItem("menuEffFromDt");
		menuEffFromTm_F1 = (EZDCStringItemArray)newItemArray("menuEffFromTm_F1");
		xxHrsMn_F2 = (EZDCStringItemArray)newItemArray("xxHrsMn_F2");
		menuEffFromTm_F3 = (EZDCStringItem)newItem("menuEffFromTm_F3");
		menuEffThruDt = (EZDCDateItem)newItem("menuEffThruDt");
		menuEffThruTm_T1 = (EZDCStringItemArray)newItemArray("menuEffThruTm_T1");
		xxHrsMn_T2 = (EZDCStringItemArray)newItemArray("xxHrsMn_T2");
		menuEffThruTm_T3 = (EZDCStringItem)newItem("menuEffThruTm_T3");
		ezUpTime = (EZDCStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDCStringItem)newItem("ezUpTimeZone");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		A = (business.blap.ZZOL0100.ZZOL0100_ACMsgArray)newMsgArray("A");
		menuEffFromDt_P0 = (EZDCDateItem)newItem("menuEffFromDt_P0");
		menuEffFromTm_P1 = (EZDCStringItemArray)newItemArray("menuEffFromTm_P1");
		xxHrsMn_P2 = (EZDCStringItemArray)newItemArray("xxHrsMn_P2");
		menuEffFromTm_P3 = (EZDCStringItem)newItem("menuEffFromTm_P3");
		menuInfoTxt_P1 = (EZDCStringItem)newItem("menuInfoTxt_P1");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZOL0100CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZOL0100CMsgArray();
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
	{"A", "A", null, "40", "business.blap.ZZOL0100.ZZOL0100_ACMsgArray", null, null},
	
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

        {"XX_SCR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_BK
        {"GLBL_CMPY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyNm
        {"MENU_INFO_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuInfoPk
        {"MENU_INFO_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuInfoTxt
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"MENU_INFO_SORT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuInfoSortNum
        {"MENU_EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuEffFromDt
        {"MENU_EFF_FROM_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuEffFromTm_F1
        {"XX_HRS_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrsMn_F2
        {"MENU_EFF_FROM_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuEffFromTm_F3
        {"MENU_EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuEffThruDt
        {"MENU_EFF_THRU_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuEffThruTm_T1
        {"XX_HRS_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrsMn_T2
        {"MENU_EFF_THRU_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuEffThruTm_T3
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
		null,	//A
        {"MENU_EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuEffFromDt_P0
        {"MENU_EFF_FROM_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuEffFromTm_P1
        {"XX_HRS_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrsMn_P2
        {"MENU_EFF_FROM_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuEffFromTm_P3
        {"MENU_INFO_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuInfoTxt_P1
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

