//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160621170121000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2830BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2830;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2830 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2830BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SRCH_OPT_NM_S*/
	public final EZDBStringItem              srchOptNm_S;

    /** SRCH_OPT_PK_S*/
	public final EZDBBigDecimalItem              srchOptPk_S;

    /** SRCH_OPT_PK_L*/
	public final EZDBBigDecimalItemArray              srchOptPk_L;

    /** SRCH_OPT_NM_L*/
	public final EZDBStringItemArray              srchOptNm_L;

    /** XX_FROM_DT*/
	public final EZDBDateItem              xxFromDt;

    /** DS_ACCT_NM*/
	public final EZDBStringItem              dsAcctNm;

    /** FILL_65_TXT_RN*/
	public final EZDBStringItem              fill65Txt_RN;

    /** ORG_NM_TN*/
	public final EZDBStringItem              orgNm_TN;

    /** XX_TP_CD_D*/
	public final EZDBStringItem              xxTpCd_D;

    /** XX_TP_CD_DL*/
	public final EZDBStringItemArray              xxTpCd_DL;

    /** XX_TP_NM_DL*/
	public final EZDBStringItemArray              xxTpNm_DL;

    /** XX_TO_DT*/
	public final EZDBDateItem              xxToDt;

    /** DS_XREF_ACCT_CD*/
	public final EZDBStringItem              dsXrefAcctCd;

    /** PSN_CD*/
	public final EZDBStringItem              psnCd;

    /** ORG_NM_ON*/
	public final EZDBStringItem              orgNm_ON;

    /** XX_CHK_BOX_RT*/
	public final EZDBStringItem              xxChkBox_RT;

    /** XX_ALL_LINE_ADDR*/
	public final EZDBStringItem              xxAllLineAddr;

    /** CTY_ADDR*/
	public final EZDBStringItem              ctyAddr;

    /** ST_CD*/
	public final EZDBStringItem              stCd;

    /** ST_CD_L*/
	public final EZDBStringItemArray              stCd_L;

    /** ST_DESC_TXT_L*/
	public final EZDBStringItemArray              stDescTxt_L;

    /** POST_CD*/
	public final EZDBStringItem              postCd;

    /** DS_ACCT_NUM*/
	public final EZDBStringItem              dsAcctNum;

    /** LOC_NUM*/
	public final EZDBStringItem              locNum;

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

    /** XX_ROW_NUM_CH*/
	public final EZDBBigDecimalItem              xxRowNum_CH;

    /** A*/
	public final business.servlet.NMAL2830.NMAL2830_ABMsgArray              A;

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

    /** XX_POP_PRM_25*/
	public final EZDBStringItem              xxPopPrm_25;

    /** XX_POP_PRM_26*/
	public final EZDBStringItem              xxPopPrm_26;

    /** XX_POP_PRM_27*/
	public final EZDBStringItem              xxPopPrm_27;

    /** XX_POP_PRM_28*/
	public final EZDBStringItem              xxPopPrm_28;

    /** XX_POP_PRM_29*/
	public final EZDBStringItem              xxPopPrm_29;

    /** XX_POP_PRM_30*/
	public final EZDBStringItem              xxPopPrm_30;

    /** XX_POP_PRM_31*/
	public final EZDBStringItem              xxPopPrm_31;

    /** XX_POP_PRM_32*/
	public final EZDBStringItem              xxPopPrm_32;

    /** XX_POP_PRM_33*/
	public final EZDBStringItem              xxPopPrm_33;

    /** XX_POP_PRM_34*/
	public final EZDBStringItem              xxPopPrm_34;

    /** XX_POP_PRM_35*/
	public final EZDBStringItem              xxPopPrm_35;

    /** Z*/
	public final business.servlet.NMAL2830.NMAL2830_ZBMsgArray              Z;

    /** XX_SCR_EVENT_NM*/
	public final EZDBStringItem              xxScrEventNm;


	/**
	 * NMAL2830BMsg is constructor.
	 * The initialization when the instance of NMAL2830BMsg is generated.
	 */
	public NMAL2830BMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2830BMsg is constructor.
	 * The initialization when the instance of NMAL2830BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2830BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		srchOptNm_S = (EZDBStringItem)newItem("srchOptNm_S");
		srchOptPk_S = (EZDBBigDecimalItem)newItem("srchOptPk_S");
		srchOptPk_L = (EZDBBigDecimalItemArray)newItemArray("srchOptPk_L");
		srchOptNm_L = (EZDBStringItemArray)newItemArray("srchOptNm_L");
		xxFromDt = (EZDBDateItem)newItem("xxFromDt");
		dsAcctNm = (EZDBStringItem)newItem("dsAcctNm");
		fill65Txt_RN = (EZDBStringItem)newItem("fill65Txt_RN");
		orgNm_TN = (EZDBStringItem)newItem("orgNm_TN");
		xxTpCd_D = (EZDBStringItem)newItem("xxTpCd_D");
		xxTpCd_DL = (EZDBStringItemArray)newItemArray("xxTpCd_DL");
		xxTpNm_DL = (EZDBStringItemArray)newItemArray("xxTpNm_DL");
		xxToDt = (EZDBDateItem)newItem("xxToDt");
		dsXrefAcctCd = (EZDBStringItem)newItem("dsXrefAcctCd");
		psnCd = (EZDBStringItem)newItem("psnCd");
		orgNm_ON = (EZDBStringItem)newItem("orgNm_ON");
		xxChkBox_RT = (EZDBStringItem)newItem("xxChkBox_RT");
		xxAllLineAddr = (EZDBStringItem)newItem("xxAllLineAddr");
		ctyAddr = (EZDBStringItem)newItem("ctyAddr");
		stCd = (EZDBStringItem)newItem("stCd");
		stCd_L = (EZDBStringItemArray)newItemArray("stCd_L");
		stDescTxt_L = (EZDBStringItemArray)newItemArray("stDescTxt_L");
		postCd = (EZDBStringItem)newItem("postCd");
		dsAcctNum = (EZDBStringItem)newItem("dsAcctNum");
		locNum = (EZDBStringItem)newItem("locNum");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		xxRowNum_CH = (EZDBBigDecimalItem)newItem("xxRowNum_CH");
		A = (business.servlet.NMAL2830.NMAL2830_ABMsgArray)newMsgArray("A");
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
		xxPopPrm_25 = (EZDBStringItem)newItem("xxPopPrm_25");
		xxPopPrm_26 = (EZDBStringItem)newItem("xxPopPrm_26");
		xxPopPrm_27 = (EZDBStringItem)newItem("xxPopPrm_27");
		xxPopPrm_28 = (EZDBStringItem)newItem("xxPopPrm_28");
		xxPopPrm_29 = (EZDBStringItem)newItem("xxPopPrm_29");
		xxPopPrm_30 = (EZDBStringItem)newItem("xxPopPrm_30");
		xxPopPrm_31 = (EZDBStringItem)newItem("xxPopPrm_31");
		xxPopPrm_32 = (EZDBStringItem)newItem("xxPopPrm_32");
		xxPopPrm_33 = (EZDBStringItem)newItem("xxPopPrm_33");
		xxPopPrm_34 = (EZDBStringItem)newItem("xxPopPrm_34");
		xxPopPrm_35 = (EZDBStringItem)newItem("xxPopPrm_35");
		Z = (business.servlet.NMAL2830.NMAL2830_ZBMsgArray)newMsgArray("Z");
		xxScrEventNm = (EZDBStringItem)newItem("xxScrEventNm");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2830BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2830BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"srchOptNm_S", "srchOptNm_S", "S", null, TYPE_HANKAKUEISU, "50", null},
	{"srchOptPk_S", "srchOptPk_S", "S", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptPk_L", "srchOptPk_L", "L", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_L", "srchOptNm_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxFromDt", "xxFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsAcctNm", "dsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"fill65Txt_RN", "fill65Txt_RN", "RN", null, TYPE_HANKAKUEISU, "65", null},
	{"orgNm_TN", "orgNm_TN", "TN", null, TYPE_HANKAKUEISU, "50", null},
	{"xxTpCd_D", "xxTpCd_D", "D", null, TYPE_HANKAKUEISU, "1", null},
	{"xxTpCd_DL", "xxTpCd_DL", "DL", "99", TYPE_HANKAKUEISU, "1", null},
	{"xxTpNm_DL", "xxTpNm_DL", "DL", "99", TYPE_HANKAKUEISU, "10", null},
	{"xxToDt", "xxToDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsXrefAcctCd", "dsXrefAcctCd", null, null, TYPE_HANKAKUEISU, "60", null},
	{"psnCd", "psnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_ON", "orgNm_ON", "ON", null, TYPE_HANKAKUEISU, "50", null},
	{"xxChkBox_RT", "xxChkBox_RT", "RT", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxAllLineAddr", "xxAllLineAddr", null, null, TYPE_HANKAKUEISU, "400", null},
	{"ctyAddr", "ctyAddr", null, null, TYPE_HANKAKUEISU, "25", null},
	{"stCd", "stCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"stCd_L", "stCd_L", "L", "99", TYPE_HANKAKUEISU, "2", null},
	{"stDescTxt_L", "stDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"postCd", "postCd", null, null, TYPE_HANKAKUEISU, "15", null},
	{"dsAcctNum", "dsAcctNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"locNum", "locNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxRowNum_CH", "xxRowNum_CH", "CH", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "120", "business.servlet.NMAL2830.NMAL2830_ABMsgArray", null, null},
	
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
	{"xxPopPrm_25", "xxPopPrm_25", "25", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_26", "xxPopPrm_26", "26", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_27", "xxPopPrm_27", "27", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_28", "xxPopPrm_28", "28", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_29", "xxPopPrm_29", "29", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_30", "xxPopPrm_30", "30", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_31", "xxPopPrm_31", "31", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_32", "xxPopPrm_32", "32", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_33", "xxPopPrm_33", "33", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_34", "xxPopPrm_34", "34", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_35", "xxPopPrm_35", "35", null, TYPE_HANKAKUEISU, "300", null},
	{"Z", "Z", null, "99", "business.servlet.NMAL2830.NMAL2830_ZBMsgArray", null, null},
	
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SRCH_OPT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_S
        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_S
        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_L
        {"SRCH_OPT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_L
        {"XX_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxFromDt
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"FILL_65_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fill65Txt_RN
        {"ORG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_TN
        {"XX_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTpCd_D
        {"XX_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTpCd_DL
        {"XX_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTpNm_DL
        {"XX_TO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxToDt
        {"DS_XREF_ACCT_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsXrefAcctCd
        {"PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd
        {"ORG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_ON
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_RT
        {"XX_ALL_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr
        {"CTY_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr
        {"ST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd
        {"ST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_L
        {"ST_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stDescTxt_L
        {"POST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd
        {"DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum
        {"LOC_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_CH
		null,	//A
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
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_25
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_26
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_27
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_28
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_29
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_30
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_31
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_32
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_33
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_34
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_35
		null,	//Z
        {"XX_SCR_EVENT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
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
