//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230228115554000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1200SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1200;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1200 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1200SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SRCH_OPT_PK_SE*/
	public final EZDSBigDecimalItem              srchOptPk_SE;

    /** SRCH_OPT_PK_CD*/
	public final EZDSBigDecimalItemArray              srchOptPk_CD;

    /** SRCH_OPT_NM_DI*/
	public final EZDSStringItemArray              srchOptNm_DI;

    /** SRCH_OPT_NM_TX*/
	public final EZDSStringItem              srchOptNm_TX;

    /** SRCH_OPT_USR_ID_U1*/
	public final EZDSStringItem              srchOptUsrId_U1;

    /** SRC_ZN_CD_SF*/
	public final EZDSStringItem              srcZnCd_SF;

    /** SRC_ZN_CD_CF*/
	public final EZDSStringItemArray              srcZnCd_CF;

    /** SRC_ZN_CD_DF*/
	public final EZDSStringItemArray              srcZnCd_DF;

    /** SRC_ZN_CD_ST*/
	public final EZDSStringItem              srcZnCd_ST;

    /** SRC_ZN_CD_CT*/
	public final EZDSStringItemArray              srcZnCd_CT;

    /** SRC_ZN_CD_DT*/
	public final EZDSStringItemArray              srcZnCd_DT;

    /** MDSE_ITEM_CLS_TP_CD_SH*/
	public final EZDSStringItem              mdseItemClsTpCd_SH;

    /** MDSE_ITEM_CLS_TP_CD_CH*/
	public final EZDSStringItemArray              mdseItemClsTpCd_CH;

    /** MDSE_ITEM_CLS_TP_DESC_TXT_DH*/
	public final EZDSStringItemArray              mdseItemClsTpDescTxt_DH;

    /** RTL_WH_CD_A1*/
	public final EZDSStringItem              rtlWhCd_A1;

    /** RTL_WH_CD_HF*/
	public final EZDSStringItem              rtlWhCd_HF;

    /** RTL_WH_NM_HF*/
	public final EZDSStringItem              rtlWhNm_HF;

    /** RTL_WH_CD_A2*/
	public final EZDSStringItem              rtlWhCd_A2;

    /** RTL_WH_CD_HT*/
	public final EZDSStringItem              rtlWhCd_HT;

    /** RTL_WH_NM_HT*/
	public final EZDSStringItem              rtlWhNm_HT;

    /** XX_CHK_BOX_H1*/
	public final EZDSStringItem              xxChkBox_H1;

    /** RTL_WH_CD_A3*/
	public final EZDSStringItem              rtlWhCd_A3;

    /** RTL_WH_CD_DF*/
	public final EZDSStringItem              rtlWhCd_DF;

    /** RTL_WH_NM_DF*/
	public final EZDSStringItem              rtlWhNm_DF;

    /** WH_OWNR_CD_DF*/
	public final EZDSStringItem              whOwnrCd_DF;

    /** RTL_WH_CD_A4*/
	public final EZDSStringItem              rtlWhCd_A4;

    /** RTL_WH_CD_DT*/
	public final EZDSStringItem              rtlWhCd_DT;

    /** RTL_WH_NM_DT*/
	public final EZDSStringItem              rtlWhNm_DT;

    /** MDSE_ITEM_CLS_TP_CD_SD*/
	public final EZDSStringItem              mdseItemClsTpCd_SD;

    /** MDSE_ITEM_CLS_TP_CD_CD*/
	public final EZDSStringItemArray              mdseItemClsTpCd_CD;

    /** MDSE_ITEM_CLS_TP_DESC_TXT_DD*/
	public final EZDSStringItemArray              mdseItemClsTpDescTxt_DD;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDSBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDSBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDSBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.blap.NPAL1200.NPAL1200_ASMsgArray              A;

    /** B*/
	public final business.blap.NPAL1200.NPAL1200_BSMsgArray              B;

    /** C*/
	public final business.blap.NPAL1200.NPAL1200_CSMsgArray              C;

    /** VAR_CHAR_CONST_VAL_XX*/
	public final EZDSStringItem              varCharConstVal_XX;

    /** XX_NUM_MD*/
	public final EZDSBigDecimalItem              xxNum_MD;

    /** XX_NUM_EF*/
	public final EZDSBigDecimalItem              xxNum_EF;

    /** XX_POP_PRM*/
	public final EZDSStringItem              xxPopPrm;

    /** EVENT_NM*/
	public final EZDSStringItem              eventNm;

    /** D*/
	public final business.blap.NPAL1200.NPAL1200_DSMsgArray              D;


	/**
	 * NPAL1200SMsg is constructor.
	 * The initialization when the instance of NPAL1200SMsg is generated.
	 */
	public NPAL1200SMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1200SMsg is constructor.
	 * The initialization when the instance of NPAL1200SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1200SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		srchOptPk_SE = (EZDSBigDecimalItem)newItem("srchOptPk_SE");
		srchOptPk_CD = (EZDSBigDecimalItemArray)newItemArray("srchOptPk_CD");
		srchOptNm_DI = (EZDSStringItemArray)newItemArray("srchOptNm_DI");
		srchOptNm_TX = (EZDSStringItem)newItem("srchOptNm_TX");
		srchOptUsrId_U1 = (EZDSStringItem)newItem("srchOptUsrId_U1");
		srcZnCd_SF = (EZDSStringItem)newItem("srcZnCd_SF");
		srcZnCd_CF = (EZDSStringItemArray)newItemArray("srcZnCd_CF");
		srcZnCd_DF = (EZDSStringItemArray)newItemArray("srcZnCd_DF");
		srcZnCd_ST = (EZDSStringItem)newItem("srcZnCd_ST");
		srcZnCd_CT = (EZDSStringItemArray)newItemArray("srcZnCd_CT");
		srcZnCd_DT = (EZDSStringItemArray)newItemArray("srcZnCd_DT");
		mdseItemClsTpCd_SH = (EZDSStringItem)newItem("mdseItemClsTpCd_SH");
		mdseItemClsTpCd_CH = (EZDSStringItemArray)newItemArray("mdseItemClsTpCd_CH");
		mdseItemClsTpDescTxt_DH = (EZDSStringItemArray)newItemArray("mdseItemClsTpDescTxt_DH");
		rtlWhCd_A1 = (EZDSStringItem)newItem("rtlWhCd_A1");
		rtlWhCd_HF = (EZDSStringItem)newItem("rtlWhCd_HF");
		rtlWhNm_HF = (EZDSStringItem)newItem("rtlWhNm_HF");
		rtlWhCd_A2 = (EZDSStringItem)newItem("rtlWhCd_A2");
		rtlWhCd_HT = (EZDSStringItem)newItem("rtlWhCd_HT");
		rtlWhNm_HT = (EZDSStringItem)newItem("rtlWhNm_HT");
		xxChkBox_H1 = (EZDSStringItem)newItem("xxChkBox_H1");
		rtlWhCd_A3 = (EZDSStringItem)newItem("rtlWhCd_A3");
		rtlWhCd_DF = (EZDSStringItem)newItem("rtlWhCd_DF");
		rtlWhNm_DF = (EZDSStringItem)newItem("rtlWhNm_DF");
		whOwnrCd_DF = (EZDSStringItem)newItem("whOwnrCd_DF");
		rtlWhCd_A4 = (EZDSStringItem)newItem("rtlWhCd_A4");
		rtlWhCd_DT = (EZDSStringItem)newItem("rtlWhCd_DT");
		rtlWhNm_DT = (EZDSStringItem)newItem("rtlWhNm_DT");
		mdseItemClsTpCd_SD = (EZDSStringItem)newItem("mdseItemClsTpCd_SD");
		mdseItemClsTpCd_CD = (EZDSStringItemArray)newItemArray("mdseItemClsTpCd_CD");
		mdseItemClsTpDescTxt_DD = (EZDSStringItemArray)newItemArray("mdseItemClsTpDescTxt_DD");
		xxPageShowFromNum = (EZDSBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDSBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDSBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.blap.NPAL1200.NPAL1200_ASMsgArray)newMsgArray("A");
		B = (business.blap.NPAL1200.NPAL1200_BSMsgArray)newMsgArray("B");
		C = (business.blap.NPAL1200.NPAL1200_CSMsgArray)newMsgArray("C");
		varCharConstVal_XX = (EZDSStringItem)newItem("varCharConstVal_XX");
		xxNum_MD = (EZDSBigDecimalItem)newItem("xxNum_MD");
		xxNum_EF = (EZDSBigDecimalItem)newItem("xxNum_EF");
		xxPopPrm = (EZDSStringItem)newItem("xxPopPrm");
		eventNm = (EZDSStringItem)newItem("eventNm");
		D = (business.blap.NPAL1200.NPAL1200_DSMsgArray)newMsgArray("D");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1200SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1200SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"srchOptPk_SE", "srchOptPk_SE", "SE", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptPk_CD", "srchOptPk_CD", "CD", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_DI", "srchOptNm_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
	{"srchOptNm_TX", "srchOptNm_TX", "TX", null, TYPE_HANKAKUEISU, "50", null},
	{"srchOptUsrId_U1", "srchOptUsrId_U1", "U1", null, TYPE_HANKAKUEISU, "16", null},
	{"srcZnCd_SF", "srcZnCd_SF", "SF", null, TYPE_HANKAKUEISU, "20", null},
	{"srcZnCd_CF", "srcZnCd_CF", "CF", "99", TYPE_HANKAKUEISU, "20", null},
	{"srcZnCd_DF", "srcZnCd_DF", "DF", "99", TYPE_HANKAKUEISU, "20", null},
	{"srcZnCd_ST", "srcZnCd_ST", "ST", null, TYPE_HANKAKUEISU, "20", null},
	{"srcZnCd_CT", "srcZnCd_CT", "CT", "99", TYPE_HANKAKUEISU, "20", null},
	{"srcZnCd_DT", "srcZnCd_DT", "DT", "99", TYPE_HANKAKUEISU, "20", null},
	{"mdseItemClsTpCd_SH", "mdseItemClsTpCd_SH", "SH", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemClsTpCd_CH", "mdseItemClsTpCd_CH", "CH", "99", TYPE_HANKAKUEISU, "2", null},
	{"mdseItemClsTpDescTxt_DH", "mdseItemClsTpDescTxt_DH", "DH", "99", TYPE_HANKAKUEISU, "50", null},
	{"rtlWhCd_A1", "rtlWhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhCd_HF", "rtlWhCd_HF", "HF", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_HF", "rtlWhNm_HF", "HF", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlWhCd_A2", "rtlWhCd_A2", "A2", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhCd_HT", "rtlWhCd_HT", "HT", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_HT", "rtlWhNm_HT", "HT", null, TYPE_HANKAKUEISU, "30", null},
	{"xxChkBox_H1", "xxChkBox_H1", "H1", null, TYPE_HANKAKUEIJI, "1", null},
	{"rtlWhCd_A3", "rtlWhCd_A3", "A3", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhCd_DF", "rtlWhCd_DF", "DF", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_DF", "rtlWhNm_DF", "DF", null, TYPE_HANKAKUEISU, "30", null},
	{"whOwnrCd_DF", "whOwnrCd_DF", "DF", null, TYPE_HANKAKUEISU, "3", null},
	{"rtlWhCd_A4", "rtlWhCd_A4", "A4", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhCd_DT", "rtlWhCd_DT", "DT", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_DT", "rtlWhNm_DT", "DT", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseItemClsTpCd_SD", "mdseItemClsTpCd_SD", "SD", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemClsTpCd_CD", "mdseItemClsTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "2", null},
	{"mdseItemClsTpDescTxt_DD", "mdseItemClsTpDescTxt_DD", "DD", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "200", "business.blap.NPAL1200.NPAL1200_ASMsgArray", null, null},
	
	{"B", "B", null, "99", "business.blap.NPAL1200.NPAL1200_BSMsgArray", null, null},
	
	{"C", "C", null, "99", "business.blap.NPAL1200.NPAL1200_CSMsgArray", null, null},
	
	{"varCharConstVal_XX", "varCharConstVal_XX", "XX", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxNum_MD", "xxNum_MD", "MD", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxNum_EF", "xxNum_EF", "EF", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPopPrm", "xxPopPrm", null, null, TYPE_HANKAKUEISU, "300", null},
	{"eventNm", "eventNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"D", "D", null, "99", "business.blap.NPAL1200.NPAL1200_DSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_SE
        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_CD
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_DI
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_TX
        {"SRCH_OPT_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptUsrId_U1
        {"SRC_ZN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcZnCd_SF
        {"SRC_ZN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcZnCd_CF
        {"SRC_ZN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcZnCd_DF
        {"SRC_ZN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcZnCd_ST
        {"SRC_ZN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcZnCd_CT
        {"SRC_ZN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcZnCd_DT
        {"MDSE_ITEM_CLS_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpCd_SH
        {"MDSE_ITEM_CLS_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpCd_CH
        {"MDSE_ITEM_CLS_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpDescTxt_DH
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_HF
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_HF
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A2
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_HT
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_HT
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A3
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_DF
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_DF
        {"WH_OWNR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whOwnrCd_DF
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A4
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_DT
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_DT
        {"MDSE_ITEM_CLS_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpCd_SD
        {"MDSE_ITEM_CLS_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpCd_CD
        {"MDSE_ITEM_CLS_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpDescTxt_DD
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
		null,	//B
		null,	//C
        {"VAR_CHAR_CONST_VAL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//varCharConstVal_XX
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_MD
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_EF
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm
        {"EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eventNm
		null,	//D
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
