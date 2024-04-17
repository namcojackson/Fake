//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160414180616000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1670BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1670;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1670 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1670BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDBDateItem              slsDt;

    /** ORD_TEAM_MSTR_NM*/
	public final EZDBStringItem              ordTeamMstrNm;

    /** ORD_ZN_CD*/
	public final EZDBStringItem              ordZnCd;

    /** XX_CHK_BOX*/
	public final EZDBStringItem              xxChkBox;

    /** XX_NUM_PO*/
	public final EZDBBigDecimalItem              xxNum_PO;

    /** ORD_ZN_CD_01*/
	public final EZDBStringItemArray              ordZnCd_01;

    /** ORD_ZN_DESC_TXT_01*/
	public final EZDBStringItemArray              ordZnDescTxt_01;

    /** ORD_TEAM_ATTRB_TP_CD_01*/
	public final EZDBStringItemArray              ordTeamAttrbTpCd_01;

    /** ORD_TEAM_ATTRB_TP_DESC_TXT_01*/
	public final EZDBStringItemArray              ordTeamAttrbTpDescTxt_01;

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

    /** XX_POP_PRM_01*/
	public final EZDBStringItem              xxPopPrm_01;

    /** XX_POP_PRM_02*/
	public final EZDBStringItem              xxPopPrm_02;

    /** XX_POP_PRM_03*/
	public final EZDBStringItem              xxPopPrm_03;

    /** XX_POP_PRM_04*/
	public final EZDBStringItem              xxPopPrm_04;

    /** XX_POP_PRM_05*/
	public final EZDBStringItem              xxPopPrm_05;

    /** XX_POP_PRM_06*/
	public final EZDBStringItem              xxPopPrm_06;

    /** XX_POP_PRM_07*/
	public final EZDBStringItem              xxPopPrm_07;

    /** XX_POP_PRM_08*/
	public final EZDBStringItem              xxPopPrm_08;

    /** XX_POP_PRM_09*/
	public final EZDBStringItem              xxPopPrm_09;

    /** XX_POP_PRM_10*/
	public final EZDBStringItem              xxPopPrm_10;

    /** XX_POP_PRM_11*/
	public final EZDBStringItem              xxPopPrm_11;

    /** XX_POP_PRM_12*/
	public final EZDBStringItem              xxPopPrm_12;

    /** XX_POP_PRM_13*/
	public final EZDBStringItem              xxPopPrm_13;

    /** XX_POP_PRM_14*/
	public final EZDBStringItem              xxPopPrm_14;

    /** XX_POP_PRM_15*/
	public final EZDBStringItem              xxPopPrm_15;

    /** XX_POP_PRM_16*/
	public final EZDBStringItem              xxPopPrm_16;

    /** XX_POP_PRM_17*/
	public final EZDBStringItem              xxPopPrm_17;

    /** XX_POP_PRM_18*/
	public final EZDBStringItem              xxPopPrm_18;

    /** XX_POP_PRM_19*/
	public final EZDBStringItem              xxPopPrm_19;

    /** XX_POP_PRM_20*/
	public final EZDBStringItem              xxPopPrm_20;

    /** XX_POP_PRM_21*/
	public final EZDBStringItem              xxPopPrm_21;

    /** XX_POP_PRM_22*/
	public final EZDBStringItem              xxPopPrm_22;

    /** XX_POP_PRM_23*/
	public final EZDBStringItem              xxPopPrm_23;

    /** XX_POP_PRM_24*/
	public final EZDBStringItem              xxPopPrm_24;

    /** XX_RADIO_BTN*/
	public final EZDBBigDecimalItem              xxRadioBtn;

    /** A*/
	public final business.servlet.NWAL1670.NWAL1670_ABMsgArray              A;

    /** B*/
	public final business.servlet.NWAL1670.NWAL1670_BBMsgArray              B;

    /** ORD_TEAM_ATTRB_TP_CD*/
	public final EZDBStringItem              ordTeamAttrbTpCd;

    /** C*/
	public final business.servlet.NWAL1670.NWAL1670_CBMsgArray              C;


	/**
	 * NWAL1670BMsg is constructor.
	 * The initialization when the instance of NWAL1670BMsg is generated.
	 */
	public NWAL1670BMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1670BMsg is constructor.
	 * The initialization when the instance of NWAL1670BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1670BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		slsDt = (EZDBDateItem)newItem("slsDt");
		ordTeamMstrNm = (EZDBStringItem)newItem("ordTeamMstrNm");
		ordZnCd = (EZDBStringItem)newItem("ordZnCd");
		xxChkBox = (EZDBStringItem)newItem("xxChkBox");
		xxNum_PO = (EZDBBigDecimalItem)newItem("xxNum_PO");
		ordZnCd_01 = (EZDBStringItemArray)newItemArray("ordZnCd_01");
		ordZnDescTxt_01 = (EZDBStringItemArray)newItemArray("ordZnDescTxt_01");
		ordTeamAttrbTpCd_01 = (EZDBStringItemArray)newItemArray("ordTeamAttrbTpCd_01");
		ordTeamAttrbTpDescTxt_01 = (EZDBStringItemArray)newItemArray("ordTeamAttrbTpDescTxt_01");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		xxPopPrm_01 = (EZDBStringItem)newItem("xxPopPrm_01");
		xxPopPrm_02 = (EZDBStringItem)newItem("xxPopPrm_02");
		xxPopPrm_03 = (EZDBStringItem)newItem("xxPopPrm_03");
		xxPopPrm_04 = (EZDBStringItem)newItem("xxPopPrm_04");
		xxPopPrm_05 = (EZDBStringItem)newItem("xxPopPrm_05");
		xxPopPrm_06 = (EZDBStringItem)newItem("xxPopPrm_06");
		xxPopPrm_07 = (EZDBStringItem)newItem("xxPopPrm_07");
		xxPopPrm_08 = (EZDBStringItem)newItem("xxPopPrm_08");
		xxPopPrm_09 = (EZDBStringItem)newItem("xxPopPrm_09");
		xxPopPrm_10 = (EZDBStringItem)newItem("xxPopPrm_10");
		xxPopPrm_11 = (EZDBStringItem)newItem("xxPopPrm_11");
		xxPopPrm_12 = (EZDBStringItem)newItem("xxPopPrm_12");
		xxPopPrm_13 = (EZDBStringItem)newItem("xxPopPrm_13");
		xxPopPrm_14 = (EZDBStringItem)newItem("xxPopPrm_14");
		xxPopPrm_15 = (EZDBStringItem)newItem("xxPopPrm_15");
		xxPopPrm_16 = (EZDBStringItem)newItem("xxPopPrm_16");
		xxPopPrm_17 = (EZDBStringItem)newItem("xxPopPrm_17");
		xxPopPrm_18 = (EZDBStringItem)newItem("xxPopPrm_18");
		xxPopPrm_19 = (EZDBStringItem)newItem("xxPopPrm_19");
		xxPopPrm_20 = (EZDBStringItem)newItem("xxPopPrm_20");
		xxPopPrm_21 = (EZDBStringItem)newItem("xxPopPrm_21");
		xxPopPrm_22 = (EZDBStringItem)newItem("xxPopPrm_22");
		xxPopPrm_23 = (EZDBStringItem)newItem("xxPopPrm_23");
		xxPopPrm_24 = (EZDBStringItem)newItem("xxPopPrm_24");
		xxRadioBtn = (EZDBBigDecimalItem)newItem("xxRadioBtn");
		A = (business.servlet.NWAL1670.NWAL1670_ABMsgArray)newMsgArray("A");
		B = (business.servlet.NWAL1670.NWAL1670_BBMsgArray)newMsgArray("B");
		ordTeamAttrbTpCd = (EZDBStringItem)newItem("ordTeamAttrbTpCd");
		C = (business.servlet.NWAL1670.NWAL1670_CBMsgArray)newMsgArray("C");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1670BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1670BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"ordTeamMstrNm", "ordTeamMstrNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"ordZnCd", "ordZnCd", null, null, TYPE_HANKAKUEISU, "6", null},
	{"xxChkBox", "xxChkBox", null, null, TYPE_HANKAKUEIJI, "1", null},
	{"xxNum_PO", "xxNum_PO", "PO", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"ordZnCd_01", "ordZnCd_01", "01", "99", TYPE_HANKAKUEISU, "6", null},
	{"ordZnDescTxt_01", "ordZnDescTxt_01", "01", "99", TYPE_HANKAKUEISU, "50", null},
	{"ordTeamAttrbTpCd_01", "ordTeamAttrbTpCd_01", "01", "99", TYPE_HANKAKUEISU, "2", null},
	{"ordTeamAttrbTpDescTxt_01", "ordTeamAttrbTpDescTxt_01", "01", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPopPrm_01", "xxPopPrm_01", "01", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_02", "xxPopPrm_02", "02", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_03", "xxPopPrm_03", "03", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_04", "xxPopPrm_04", "04", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_05", "xxPopPrm_05", "05", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_06", "xxPopPrm_06", "06", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_07", "xxPopPrm_07", "07", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_08", "xxPopPrm_08", "08", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_09", "xxPopPrm_09", "09", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_10", "xxPopPrm_10", "10", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_11", "xxPopPrm_11", "11", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_12", "xxPopPrm_12", "12", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_13", "xxPopPrm_13", "13", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_14", "xxPopPrm_14", "14", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_15", "xxPopPrm_15", "15", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_16", "xxPopPrm_16", "16", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_17", "xxPopPrm_17", "17", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_18", "xxPopPrm_18", "18", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_19", "xxPopPrm_19", "19", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_20", "xxPopPrm_20", "20", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_21", "xxPopPrm_21", "21", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_22", "xxPopPrm_22", "22", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_23", "xxPopPrm_23", "23", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_24", "xxPopPrm_24", "24", null, TYPE_HANKAKUEISU, "300", null},
	{"xxRadioBtn", "xxRadioBtn", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"A", "A", null, "200", "business.servlet.NWAL1670.NWAL1670_ABMsgArray", null, null},
	
	{"B", "B", null, "200", "business.servlet.NWAL1670.NWAL1670_BBMsgArray", null, null},
	
	{"ordTeamAttrbTpCd", "ordTeamAttrbTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"C", "C", null, "200", "business.servlet.NWAL1670.NWAL1670_CBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//slsDt
        {"ORD_TEAM_MSTR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordTeamMstrNm
        {"ORD_ZN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordZnCd
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"XX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_PO
        {"ORD_ZN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordZnCd_01
        {"ORD_ZN_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordZnDescTxt_01
        {"ORD_TEAM_ATTRB_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordTeamAttrbTpCd_01
        {"ORD_TEAM_ATTRB_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordTeamAttrbTpDescTxt_01
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_01
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_02
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_03
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_04
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_05
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_06
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_07
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_08
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_09
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_10
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_11
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_12
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_13
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_14
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_15
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_16
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_17
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_18
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_19
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_20
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_21
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_22
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_23
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_24
        {"XX_RADIO_BTN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn
		null,	//A
		null,	//B
        {"ORD_TEAM_ATTRB_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordTeamAttrbTpCd
		null,	//C
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

