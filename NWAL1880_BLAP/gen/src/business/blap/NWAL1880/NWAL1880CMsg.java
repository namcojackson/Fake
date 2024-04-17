//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170530163150000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1880CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1880;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1880 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1880CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDCDateItem              slsDt;

    /** XX_ORD_TEAM_SRCH_TXT*/
	public final EZDCStringItem              xxOrdTeamSrchTxt;

    /** XX_ORD_TEAM_SRCH_TXT_LK*/
	public final EZDCStringItem              xxOrdTeamSrchTxt_LK;

    /** XX_ORD_TEAM_SRCH_TXT_BK*/
	public final EZDCStringItem              xxOrdTeamSrchTxt_BK;

    /** XX_ORD_ZN_SRCH_TXT*/
	public final EZDCStringItem              xxOrdZnSrchTxt;

    /** XX_ORD_ZN_SRCH_TXT_LK*/
	public final EZDCStringItem              xxOrdZnSrchTxt_LK;

    /** XX_ORD_ZN_SRCH_TXT_BK*/
	public final EZDCStringItem              xxOrdZnSrchTxt_BK;

    /** XX_CRAT_BY_USR_SRCH_TXT*/
	public final EZDCStringItem              xxCratByUsrSrchTxt;

    /** XX_CRAT_BY_USR_SRCH_TXT_LK*/
	public final EZDCStringItem              xxCratByUsrSrchTxt_LK;

    /** XX_CRAT_BY_USR_SRCH_TXT_BK*/
	public final EZDCStringItem              xxCratByUsrSrchTxt_BK;

    /** XX_SCR_ITEM_50_TXT_CD*/
	public final EZDCStringItemArray              xxScrItem50Txt_CD;

    /** XX_SCR_ITEM_50_TXT_NM*/
	public final EZDCStringItemArray              xxScrItem50Txt_NM;

    /** XX_SCR_ITEM_50_TXT*/
	public final EZDCStringItem              xxScrItem50Txt;

    /** DPLY_BY_ITEM_LB_NM*/
	public final EZDCStringItem              dplyByItemLbNm;

    /** PAIR_DPLY_BY_ITEM_NM*/
	public final EZDCStringItem              pairDplyByItemNm;

    /** XX_SPL_CHAR_TXT*/
	public final EZDCStringItem              xxSplCharTxt;

    /** AFT_DECL_PNT_DIGIT_NUM*/
	public final EZDCBigDecimalItem              aftDeclPntDigitNum;

    /** H*/
	public final business.blap.NWAL1880.NWAL1880_HCMsgArray              H;

    /** XX_SCR_EVENT_NM*/
	public final EZDCStringItem              xxScrEventNm;

    /** A*/
	public final business.blap.NWAL1880.NWAL1880_ACMsgArray              A;

    /** B*/
	public final business.blap.NWAL1880.NWAL1880_BCMsgArray              B;

    /** C*/
	public final business.blap.NWAL1880.NWAL1880_CCMsgArray              C;

    /** D*/
	public final business.blap.NWAL1880.NWAL1880_DCMsgArray              D;

    /** E*/
	public final business.blap.NWAL1880.NWAL1880_ECMsgArray              E;

    /** F*/
	public final business.blap.NWAL1880.NWAL1880_FCMsgArray              F;

    /** G*/
	public final business.blap.NWAL1880.NWAL1880_GCMsgArray              G;

    /** Z*/
	public final business.blap.NWAL1880.NWAL1880_ZCMsgArray              Z;

    /** XX_POP_PRM_P0*/
	public final EZDCStringItem              xxPopPrm_P0;

    /** XX_POP_PRM_P1*/
	public final EZDCStringItem              xxPopPrm_P1;

    /** XX_POP_PRM_P2*/
	public final EZDCStringItem              xxPopPrm_P2;

    /** XX_POP_PRM_P3*/
	public final EZDCStringItem              xxPopPrm_P3;

    /** XX_POP_PRM_P4*/
	public final EZDCStringItem              xxPopPrm_P4;

    /** XX_POP_PRM_P5*/
	public final EZDCStringItem              xxPopPrm_P5;

    /** XX_POP_PRM_P6*/
	public final EZDCStringItem              xxPopPrm_P6;

    /** XX_POP_PRM_P7*/
	public final EZDCStringItem              xxPopPrm_P7;

    /** XX_POP_PRM_P8*/
	public final EZDCStringItem              xxPopPrm_P8;

    /** XX_POP_PRM_P9*/
	public final EZDCStringItem              xxPopPrm_P9;

    /** XX_POP_PRM_PA*/
	public final EZDCStringItem              xxPopPrm_PA;

    /** XX_POP_PRM_PB*/
	public final EZDCStringItem              xxPopPrm_PB;

    /** XX_POP_PRM_PC*/
	public final EZDCStringItem              xxPopPrm_PC;

    /** XX_POP_PRM_PD*/
	public final EZDCStringItem              xxPopPrm_PD;

    /** XX_POP_PRM_PE*/
	public final EZDCStringItem              xxPopPrm_PE;

    /** XX_POP_PRM_PF*/
	public final EZDCStringItem              xxPopPrm_PF;

    /** XX_POP_PRM_PG*/
	public final EZDCStringItem              xxPopPrm_PG;

    /** XX_POP_PRM_PH*/
	public final EZDCStringItem              xxPopPrm_PH;

    /** XX_POP_PRM_PI*/
	public final EZDCStringItem              xxPopPrm_PI;

    /** XX_POP_PRM_PJ*/
	public final EZDCStringItem              xxPopPrm_PJ;

    /** XX_ROW_NUM*/
	public final EZDCBigDecimalItem              xxRowNum;


	/**
	 * NWAL1880CMsg is constructor.
	 * The initialization when the instance of NWAL1880CMsg is generated.
	 */
	public NWAL1880CMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1880CMsg is constructor.
	 * The initialization when the instance of NWAL1880CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1880CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		slsDt = (EZDCDateItem)newItem("slsDt");
		xxOrdTeamSrchTxt = (EZDCStringItem)newItem("xxOrdTeamSrchTxt");
		xxOrdTeamSrchTxt_LK = (EZDCStringItem)newItem("xxOrdTeamSrchTxt_LK");
		xxOrdTeamSrchTxt_BK = (EZDCStringItem)newItem("xxOrdTeamSrchTxt_BK");
		xxOrdZnSrchTxt = (EZDCStringItem)newItem("xxOrdZnSrchTxt");
		xxOrdZnSrchTxt_LK = (EZDCStringItem)newItem("xxOrdZnSrchTxt_LK");
		xxOrdZnSrchTxt_BK = (EZDCStringItem)newItem("xxOrdZnSrchTxt_BK");
		xxCratByUsrSrchTxt = (EZDCStringItem)newItem("xxCratByUsrSrchTxt");
		xxCratByUsrSrchTxt_LK = (EZDCStringItem)newItem("xxCratByUsrSrchTxt_LK");
		xxCratByUsrSrchTxt_BK = (EZDCStringItem)newItem("xxCratByUsrSrchTxt_BK");
		xxScrItem50Txt_CD = (EZDCStringItemArray)newItemArray("xxScrItem50Txt_CD");
		xxScrItem50Txt_NM = (EZDCStringItemArray)newItemArray("xxScrItem50Txt_NM");
		xxScrItem50Txt = (EZDCStringItem)newItem("xxScrItem50Txt");
		dplyByItemLbNm = (EZDCStringItem)newItem("dplyByItemLbNm");
		pairDplyByItemNm = (EZDCStringItem)newItem("pairDplyByItemNm");
		xxSplCharTxt = (EZDCStringItem)newItem("xxSplCharTxt");
		aftDeclPntDigitNum = (EZDCBigDecimalItem)newItem("aftDeclPntDigitNum");
		H = (business.blap.NWAL1880.NWAL1880_HCMsgArray)newMsgArray("H");
		xxScrEventNm = (EZDCStringItem)newItem("xxScrEventNm");
		A = (business.blap.NWAL1880.NWAL1880_ACMsgArray)newMsgArray("A");
		B = (business.blap.NWAL1880.NWAL1880_BCMsgArray)newMsgArray("B");
		C = (business.blap.NWAL1880.NWAL1880_CCMsgArray)newMsgArray("C");
		D = (business.blap.NWAL1880.NWAL1880_DCMsgArray)newMsgArray("D");
		E = (business.blap.NWAL1880.NWAL1880_ECMsgArray)newMsgArray("E");
		F = (business.blap.NWAL1880.NWAL1880_FCMsgArray)newMsgArray("F");
		G = (business.blap.NWAL1880.NWAL1880_GCMsgArray)newMsgArray("G");
		Z = (business.blap.NWAL1880.NWAL1880_ZCMsgArray)newMsgArray("Z");
		xxPopPrm_P0 = (EZDCStringItem)newItem("xxPopPrm_P0");
		xxPopPrm_P1 = (EZDCStringItem)newItem("xxPopPrm_P1");
		xxPopPrm_P2 = (EZDCStringItem)newItem("xxPopPrm_P2");
		xxPopPrm_P3 = (EZDCStringItem)newItem("xxPopPrm_P3");
		xxPopPrm_P4 = (EZDCStringItem)newItem("xxPopPrm_P4");
		xxPopPrm_P5 = (EZDCStringItem)newItem("xxPopPrm_P5");
		xxPopPrm_P6 = (EZDCStringItem)newItem("xxPopPrm_P6");
		xxPopPrm_P7 = (EZDCStringItem)newItem("xxPopPrm_P7");
		xxPopPrm_P8 = (EZDCStringItem)newItem("xxPopPrm_P8");
		xxPopPrm_P9 = (EZDCStringItem)newItem("xxPopPrm_P9");
		xxPopPrm_PA = (EZDCStringItem)newItem("xxPopPrm_PA");
		xxPopPrm_PB = (EZDCStringItem)newItem("xxPopPrm_PB");
		xxPopPrm_PC = (EZDCStringItem)newItem("xxPopPrm_PC");
		xxPopPrm_PD = (EZDCStringItem)newItem("xxPopPrm_PD");
		xxPopPrm_PE = (EZDCStringItem)newItem("xxPopPrm_PE");
		xxPopPrm_PF = (EZDCStringItem)newItem("xxPopPrm_PF");
		xxPopPrm_PG = (EZDCStringItem)newItem("xxPopPrm_PG");
		xxPopPrm_PH = (EZDCStringItem)newItem("xxPopPrm_PH");
		xxPopPrm_PI = (EZDCStringItem)newItem("xxPopPrm_PI");
		xxPopPrm_PJ = (EZDCStringItem)newItem("xxPopPrm_PJ");
		xxRowNum = (EZDCBigDecimalItem)newItem("xxRowNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1880CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1880CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxOrdTeamSrchTxt", "xxOrdTeamSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxOrdTeamSrchTxt_LK", "xxOrdTeamSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxOrdTeamSrchTxt_BK", "xxOrdTeamSrchTxt_BK", "BK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxOrdZnSrchTxt", "xxOrdZnSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxOrdZnSrchTxt_LK", "xxOrdZnSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxOrdZnSrchTxt_BK", "xxOrdZnSrchTxt_BK", "BK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxCratByUsrSrchTxt", "xxCratByUsrSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxCratByUsrSrchTxt_LK", "xxCratByUsrSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxCratByUsrSrchTxt_BK", "xxCratByUsrSrchTxt_BK", "BK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxScrItem50Txt_CD", "xxScrItem50Txt_CD", "CD", "5", TYPE_HANKAKUEISU, "50", null},
	{"xxScrItem50Txt_NM", "xxScrItem50Txt_NM", "NM", "5", TYPE_HANKAKUEISU, "50", null},
	{"xxScrItem50Txt", "xxScrItem50Txt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dplyByItemLbNm", "dplyByItemLbNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"pairDplyByItemNm", "pairDplyByItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSplCharTxt", "xxSplCharTxt", null, null, TYPE_HANKAKUEISU, "1", null},
	{"aftDeclPntDigitNum", "aftDeclPntDigitNum", null, null, TYPE_SEISU_SYOSU, "1", "0"},
	{"H", "H", null, "3", "business.blap.NWAL1880.NWAL1880_HCMsgArray", null, null},
	
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"A", "A", null, "200", "business.blap.NWAL1880.NWAL1880_ACMsgArray", null, null},
	
	{"B", "B", null, "200", "business.blap.NWAL1880.NWAL1880_BCMsgArray", null, null},
	
	{"C", "C", null, "200", "business.blap.NWAL1880.NWAL1880_CCMsgArray", null, null},
	
	{"D", "D", null, "200", "business.blap.NWAL1880.NWAL1880_DCMsgArray", null, null},
	
	{"E", "E", null, "200", "business.blap.NWAL1880.NWAL1880_ECMsgArray", null, null},
	
	{"F", "F", null, "200", "business.blap.NWAL1880.NWAL1880_FCMsgArray", null, null},
	
	{"G", "G", null, "200", "business.blap.NWAL1880.NWAL1880_GCMsgArray", null, null},
	
	{"Z", "Z", null, "200", "business.blap.NWAL1880.NWAL1880_ZCMsgArray", null, null},
	
	{"xxPopPrm_P0", "xxPopPrm_P0", "P0", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P1", "xxPopPrm_P1", "P1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P2", "xxPopPrm_P2", "P2", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P3", "xxPopPrm_P3", "P3", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P4", "xxPopPrm_P4", "P4", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P5", "xxPopPrm_P5", "P5", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P6", "xxPopPrm_P6", "P6", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P7", "xxPopPrm_P7", "P7", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P8", "xxPopPrm_P8", "P8", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P9", "xxPopPrm_P9", "P9", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PA", "xxPopPrm_PA", "PA", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PB", "xxPopPrm_PB", "PB", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PC", "xxPopPrm_PC", "PC", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PD", "xxPopPrm_PD", "PD", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PE", "xxPopPrm_PE", "PE", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PF", "xxPopPrm_PF", "PF", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PG", "xxPopPrm_PG", "PG", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PH", "xxPopPrm_PH", "PH", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PI", "xxPopPrm_PI", "PI", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PJ", "xxPopPrm_PJ", "PJ", null, TYPE_HANKAKUEISU, "300", null},
	{"xxRowNum", "xxRowNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"XX_ORD_TEAM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdTeamSrchTxt
        {"XX_ORD_TEAM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdTeamSrchTxt_LK
        {"XX_ORD_TEAM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdTeamSrchTxt_BK
        {"XX_ORD_ZN_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdZnSrchTxt
        {"XX_ORD_ZN_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdZnSrchTxt_LK
        {"XX_ORD_ZN_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdZnSrchTxt_BK
        {"XX_CRAT_BY_USR_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCratByUsrSrchTxt
        {"XX_CRAT_BY_USR_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCratByUsrSrchTxt_LK
        {"XX_CRAT_BY_USR_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCratByUsrSrchTxt_BK
        {"XX_SCR_ITEM_50_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem50Txt_CD
        {"XX_SCR_ITEM_50_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem50Txt_NM
        {"XX_SCR_ITEM_50_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem50Txt
        {"DPLY_BY_ITEM_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyByItemLbNm
        {"PAIR_DPLY_BY_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pairDplyByItemNm
        {"XX_SPL_CHAR_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSplCharTxt
        {"AFT_DECL_PNT_DIGIT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDeclPntDigitNum
		null,	//H
        {"XX_SCR_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
		null,	//A
		null,	//B
		null,	//C
		null,	//D
		null,	//E
		null,	//F
		null,	//G
		null,	//Z
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P0
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P2
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P3
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P4
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P5
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P6
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P7
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P8
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P9
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PA
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PB
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PC
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PD
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PE
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PF
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PG
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PH
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PI
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PJ
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum
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
