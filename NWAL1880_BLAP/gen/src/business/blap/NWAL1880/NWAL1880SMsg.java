//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170530163151000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1880SMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWAL1880 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1880SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDSStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDSDateItem              slsDt;

    /** XX_ORD_TEAM_SRCH_TXT*/
	public final EZDSStringItem              xxOrdTeamSrchTxt;

    /** XX_ORD_TEAM_SRCH_TXT_LK*/
	public final EZDSStringItem              xxOrdTeamSrchTxt_LK;

    /** XX_ORD_TEAM_SRCH_TXT_BK*/
	public final EZDSStringItem              xxOrdTeamSrchTxt_BK;

    /** XX_ORD_ZN_SRCH_TXT*/
	public final EZDSStringItem              xxOrdZnSrchTxt;

    /** XX_ORD_ZN_SRCH_TXT_LK*/
	public final EZDSStringItem              xxOrdZnSrchTxt_LK;

    /** XX_ORD_ZN_SRCH_TXT_BK*/
	public final EZDSStringItem              xxOrdZnSrchTxt_BK;

    /** XX_CRAT_BY_USR_SRCH_TXT*/
	public final EZDSStringItem              xxCratByUsrSrchTxt;

    /** XX_CRAT_BY_USR_SRCH_TXT_LK*/
	public final EZDSStringItem              xxCratByUsrSrchTxt_LK;

    /** XX_CRAT_BY_USR_SRCH_TXT_BK*/
	public final EZDSStringItem              xxCratByUsrSrchTxt_BK;

    /** XX_SCR_ITEM_50_TXT_CD*/
	public final EZDSStringItemArray              xxScrItem50Txt_CD;

    /** XX_SCR_ITEM_50_TXT_NM*/
	public final EZDSStringItemArray              xxScrItem50Txt_NM;

    /** XX_SCR_ITEM_50_TXT*/
	public final EZDSStringItem              xxScrItem50Txt;

    /** DPLY_BY_ITEM_LB_NM*/
	public final EZDSStringItem              dplyByItemLbNm;

    /** PAIR_DPLY_BY_ITEM_NM*/
	public final EZDSStringItem              pairDplyByItemNm;

    /** XX_SPL_CHAR_TXT*/
	public final EZDSStringItem              xxSplCharTxt;

    /** AFT_DECL_PNT_DIGIT_NUM*/
	public final EZDSBigDecimalItem              aftDeclPntDigitNum;

    /** H*/
	public final business.blap.NWAL1880.NWAL1880_HSMsgArray              H;

    /** XX_SCR_EVENT_NM*/
	public final EZDSStringItem              xxScrEventNm;

    /** A*/
	public final business.blap.NWAL1880.NWAL1880_ASMsgArray              A;

    /** B*/
	public final business.blap.NWAL1880.NWAL1880_BSMsgArray              B;

    /** C*/
	public final business.blap.NWAL1880.NWAL1880_CSMsgArray              C;

    /** D*/
	public final business.blap.NWAL1880.NWAL1880_DSMsgArray              D;

    /** E*/
	public final business.blap.NWAL1880.NWAL1880_ESMsgArray              E;

    /** F*/
	public final business.blap.NWAL1880.NWAL1880_FSMsgArray              F;

    /** G*/
	public final business.blap.NWAL1880.NWAL1880_GSMsgArray              G;


	/**
	 * NWAL1880SMsg is constructor.
	 * The initialization when the instance of NWAL1880SMsg is generated.
	 */
	public NWAL1880SMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1880SMsg is constructor.
	 * The initialization when the instance of NWAL1880SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1880SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDSStringItem)newItem("glblCmpyCd");
		slsDt = (EZDSDateItem)newItem("slsDt");
		xxOrdTeamSrchTxt = (EZDSStringItem)newItem("xxOrdTeamSrchTxt");
		xxOrdTeamSrchTxt_LK = (EZDSStringItem)newItem("xxOrdTeamSrchTxt_LK");
		xxOrdTeamSrchTxt_BK = (EZDSStringItem)newItem("xxOrdTeamSrchTxt_BK");
		xxOrdZnSrchTxt = (EZDSStringItem)newItem("xxOrdZnSrchTxt");
		xxOrdZnSrchTxt_LK = (EZDSStringItem)newItem("xxOrdZnSrchTxt_LK");
		xxOrdZnSrchTxt_BK = (EZDSStringItem)newItem("xxOrdZnSrchTxt_BK");
		xxCratByUsrSrchTxt = (EZDSStringItem)newItem("xxCratByUsrSrchTxt");
		xxCratByUsrSrchTxt_LK = (EZDSStringItem)newItem("xxCratByUsrSrchTxt_LK");
		xxCratByUsrSrchTxt_BK = (EZDSStringItem)newItem("xxCratByUsrSrchTxt_BK");
		xxScrItem50Txt_CD = (EZDSStringItemArray)newItemArray("xxScrItem50Txt_CD");
		xxScrItem50Txt_NM = (EZDSStringItemArray)newItemArray("xxScrItem50Txt_NM");
		xxScrItem50Txt = (EZDSStringItem)newItem("xxScrItem50Txt");
		dplyByItemLbNm = (EZDSStringItem)newItem("dplyByItemLbNm");
		pairDplyByItemNm = (EZDSStringItem)newItem("pairDplyByItemNm");
		xxSplCharTxt = (EZDSStringItem)newItem("xxSplCharTxt");
		aftDeclPntDigitNum = (EZDSBigDecimalItem)newItem("aftDeclPntDigitNum");
		H = (business.blap.NWAL1880.NWAL1880_HSMsgArray)newMsgArray("H");
		xxScrEventNm = (EZDSStringItem)newItem("xxScrEventNm");
		A = (business.blap.NWAL1880.NWAL1880_ASMsgArray)newMsgArray("A");
		B = (business.blap.NWAL1880.NWAL1880_BSMsgArray)newMsgArray("B");
		C = (business.blap.NWAL1880.NWAL1880_CSMsgArray)newMsgArray("C");
		D = (business.blap.NWAL1880.NWAL1880_DSMsgArray)newMsgArray("D");
		E = (business.blap.NWAL1880.NWAL1880_ESMsgArray)newMsgArray("E");
		F = (business.blap.NWAL1880.NWAL1880_FSMsgArray)newMsgArray("F");
		G = (business.blap.NWAL1880.NWAL1880_GSMsgArray)newMsgArray("G");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1880SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1880SMsgArray();
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
	{"H", "H", null, "3", "business.blap.NWAL1880.NWAL1880_HSMsgArray", null, null},
	
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"A", "A", null, "200", "business.blap.NWAL1880.NWAL1880_ASMsgArray", null, null},
	
	{"B", "B", null, "200", "business.blap.NWAL1880.NWAL1880_BSMsgArray", null, null},
	
	{"C", "C", null, "200", "business.blap.NWAL1880.NWAL1880_CSMsgArray", null, null},
	
	{"D", "D", null, "200", "business.blap.NWAL1880.NWAL1880_DSMsgArray", null, null},
	
	{"E", "E", null, "200", "business.blap.NWAL1880.NWAL1880_ESMsgArray", null, null},
	
	{"F", "F", null, "200", "business.blap.NWAL1880.NWAL1880_FSMsgArray", null, null},
	
	{"G", "G", null, "200", "business.blap.NWAL1880.NWAL1880_GSMsgArray", null, null},
	
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

