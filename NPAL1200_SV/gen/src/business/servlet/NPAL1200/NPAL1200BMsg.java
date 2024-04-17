//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230228115513000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1200BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1200;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1200 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1200BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SRCH_OPT_PK_SE*/
	public final EZDBBigDecimalItem              srchOptPk_SE;

    /** SRCH_OPT_PK_CD*/
	public final EZDBBigDecimalItemArray              srchOptPk_CD;

    /** SRCH_OPT_NM_DI*/
	public final EZDBStringItemArray              srchOptNm_DI;

    /** SRCH_OPT_NM_TX*/
	public final EZDBStringItem              srchOptNm_TX;

    /** SRCH_OPT_USR_ID_U1*/
	public final EZDBStringItem              srchOptUsrId_U1;

    /** SRC_ZN_CD_SF*/
	public final EZDBStringItem              srcZnCd_SF;

    /** SRC_ZN_CD_CF*/
	public final EZDBStringItemArray              srcZnCd_CF;

    /** SRC_ZN_CD_DF*/
	public final EZDBStringItemArray              srcZnCd_DF;

    /** SRC_ZN_CD_ST*/
	public final EZDBStringItem              srcZnCd_ST;

    /** SRC_ZN_CD_CT*/
	public final EZDBStringItemArray              srcZnCd_CT;

    /** SRC_ZN_CD_DT*/
	public final EZDBStringItemArray              srcZnCd_DT;

    /** MDSE_ITEM_CLS_TP_CD_SH*/
	public final EZDBStringItem              mdseItemClsTpCd_SH;

    /** MDSE_ITEM_CLS_TP_CD_CH*/
	public final EZDBStringItemArray              mdseItemClsTpCd_CH;

    /** MDSE_ITEM_CLS_TP_DESC_TXT_DH*/
	public final EZDBStringItemArray              mdseItemClsTpDescTxt_DH;

    /** RTL_WH_CD_A1*/
	public final EZDBStringItem              rtlWhCd_A1;

    /** RTL_WH_CD_HF*/
	public final EZDBStringItem              rtlWhCd_HF;

    /** RTL_WH_NM_HF*/
	public final EZDBStringItem              rtlWhNm_HF;

    /** RTL_WH_CD_A2*/
	public final EZDBStringItem              rtlWhCd_A2;

    /** RTL_WH_CD_HT*/
	public final EZDBStringItem              rtlWhCd_HT;

    /** RTL_WH_NM_HT*/
	public final EZDBStringItem              rtlWhNm_HT;

    /** XX_CHK_BOX_H1*/
	public final EZDBStringItem              xxChkBox_H1;

    /** PRCH_REQ_TP_CD_SF*/
	public final EZDBStringItem              prchReqTpCd_SF;

    /** PRCH_REQ_TP_CD_CF*/
	public final EZDBStringItemArray              prchReqTpCd_CF;

    /** PRCH_REQ_TP_DESC_TXT_DF*/
	public final EZDBStringItemArray              prchReqTpDescTxt_DF;

    /** XX_TP_CD_SF*/
	public final EZDBStringItem              xxTpCd_SF;

    /** XX_TP_CD_CF*/
	public final EZDBStringItemArray              xxTpCd_CF;

    /** XX_TP_NM_DF*/
	public final EZDBStringItemArray              xxTpNm_DF;

    /** RTL_WH_CD_A3*/
	public final EZDBStringItem              rtlWhCd_A3;

    /** RTL_WH_CD_DF*/
	public final EZDBStringItem              rtlWhCd_DF;

    /** RTL_WH_NM_DF*/
	public final EZDBStringItem              rtlWhNm_DF;

    /** WH_OWNR_CD_DF*/
	public final EZDBStringItem              whOwnrCd_DF;

    /** RTL_WH_CD_A4*/
	public final EZDBStringItem              rtlWhCd_A4;

    /** RTL_WH_CD_DT*/
	public final EZDBStringItem              rtlWhCd_DT;

    /** RTL_WH_NM_DT*/
	public final EZDBStringItem              rtlWhNm_DT;

    /** MDSE_ITEM_CLS_TP_CD_SD*/
	public final EZDBStringItem              mdseItemClsTpCd_SD;

    /** MDSE_ITEM_CLS_TP_CD_CD*/
	public final EZDBStringItemArray              mdseItemClsTpCd_CD;

    /** MDSE_ITEM_CLS_TP_DESC_TXT_DD*/
	public final EZDBStringItemArray              mdseItemClsTpDescTxt_DD;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.servlet.NPAL1200.NPAL1200_ABMsgArray              A;

    /** XX_POP_PRM*/
	public final EZDBStringItem              xxPopPrm;

    /** XX_POP_PRM_P0*/
	public final EZDBStringItem              xxPopPrm_P0;

    /** XX_POP_PRM_P1*/
	public final EZDBStringItem              xxPopPrm_P1;

    /** XX_POP_PRM_P2*/
	public final EZDBStringItem              xxPopPrm_P2;

    /** XX_POP_PRM_P3*/
	public final EZDBStringItem              xxPopPrm_P3;

    /** XX_POP_PRM_P4*/
	public final EZDBStringItem              xxPopPrm_P4;

    /** XX_POP_PRM_P5*/
	public final EZDBStringItem              xxPopPrm_P5;

    /** XX_POP_PRM_P6*/
	public final EZDBStringItem              xxPopPrm_P6;

    /** XX_POP_PRM_P7*/
	public final EZDBStringItem              xxPopPrm_P7;

    /** XX_POP_PRM_P8*/
	public final EZDBStringItem              xxPopPrm_P8;

    /** XX_POP_PRM_P9*/
	public final EZDBStringItem              xxPopPrm_P9;

    /** XX_POP_PRM_PA*/
	public final EZDBStringItem              xxPopPrm_PA;

    /** XX_POP_PRM_PB*/
	public final EZDBStringItem              xxPopPrm_PB;

    /** EVENT_NM*/
	public final EZDBStringItem              eventNm;

    /** B*/
	public final business.servlet.NPAL1200.NPAL1200_BBMsgArray              B;

    /** C*/
	public final business.servlet.NPAL1200.NPAL1200_CBMsgArray              C;

    /** VAR_CHAR_CONST_VAL_XX*/
	public final EZDBStringItem              varCharConstVal_XX;

    /** XX_NUM_MD*/
	public final EZDBBigDecimalItem              xxNum_MD;

    /** XX_NUM_EF*/
	public final EZDBBigDecimalItem              xxNum_EF;

    /** D*/
	public final business.servlet.NPAL1200.NPAL1200_DBMsgArray              D;


	/**
	 * NPAL1200BMsg is constructor.
	 * The initialization when the instance of NPAL1200BMsg is generated.
	 */
	public NPAL1200BMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1200BMsg is constructor.
	 * The initialization when the instance of NPAL1200BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1200BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		srchOptPk_SE = (EZDBBigDecimalItem)newItem("srchOptPk_SE");
		srchOptPk_CD = (EZDBBigDecimalItemArray)newItemArray("srchOptPk_CD");
		srchOptNm_DI = (EZDBStringItemArray)newItemArray("srchOptNm_DI");
		srchOptNm_TX = (EZDBStringItem)newItem("srchOptNm_TX");
		srchOptUsrId_U1 = (EZDBStringItem)newItem("srchOptUsrId_U1");
		srcZnCd_SF = (EZDBStringItem)newItem("srcZnCd_SF");
		srcZnCd_CF = (EZDBStringItemArray)newItemArray("srcZnCd_CF");
		srcZnCd_DF = (EZDBStringItemArray)newItemArray("srcZnCd_DF");
		srcZnCd_ST = (EZDBStringItem)newItem("srcZnCd_ST");
		srcZnCd_CT = (EZDBStringItemArray)newItemArray("srcZnCd_CT");
		srcZnCd_DT = (EZDBStringItemArray)newItemArray("srcZnCd_DT");
		mdseItemClsTpCd_SH = (EZDBStringItem)newItem("mdseItemClsTpCd_SH");
		mdseItemClsTpCd_CH = (EZDBStringItemArray)newItemArray("mdseItemClsTpCd_CH");
		mdseItemClsTpDescTxt_DH = (EZDBStringItemArray)newItemArray("mdseItemClsTpDescTxt_DH");
		rtlWhCd_A1 = (EZDBStringItem)newItem("rtlWhCd_A1");
		rtlWhCd_HF = (EZDBStringItem)newItem("rtlWhCd_HF");
		rtlWhNm_HF = (EZDBStringItem)newItem("rtlWhNm_HF");
		rtlWhCd_A2 = (EZDBStringItem)newItem("rtlWhCd_A2");
		rtlWhCd_HT = (EZDBStringItem)newItem("rtlWhCd_HT");
		rtlWhNm_HT = (EZDBStringItem)newItem("rtlWhNm_HT");
		xxChkBox_H1 = (EZDBStringItem)newItem("xxChkBox_H1");
		prchReqTpCd_SF = (EZDBStringItem)newItem("prchReqTpCd_SF");
		prchReqTpCd_CF = (EZDBStringItemArray)newItemArray("prchReqTpCd_CF");
		prchReqTpDescTxt_DF = (EZDBStringItemArray)newItemArray("prchReqTpDescTxt_DF");
		xxTpCd_SF = (EZDBStringItem)newItem("xxTpCd_SF");
		xxTpCd_CF = (EZDBStringItemArray)newItemArray("xxTpCd_CF");
		xxTpNm_DF = (EZDBStringItemArray)newItemArray("xxTpNm_DF");
		rtlWhCd_A3 = (EZDBStringItem)newItem("rtlWhCd_A3");
		rtlWhCd_DF = (EZDBStringItem)newItem("rtlWhCd_DF");
		rtlWhNm_DF = (EZDBStringItem)newItem("rtlWhNm_DF");
		whOwnrCd_DF = (EZDBStringItem)newItem("whOwnrCd_DF");
		rtlWhCd_A4 = (EZDBStringItem)newItem("rtlWhCd_A4");
		rtlWhCd_DT = (EZDBStringItem)newItem("rtlWhCd_DT");
		rtlWhNm_DT = (EZDBStringItem)newItem("rtlWhNm_DT");
		mdseItemClsTpCd_SD = (EZDBStringItem)newItem("mdseItemClsTpCd_SD");
		mdseItemClsTpCd_CD = (EZDBStringItemArray)newItemArray("mdseItemClsTpCd_CD");
		mdseItemClsTpDescTxt_DD = (EZDBStringItemArray)newItemArray("mdseItemClsTpDescTxt_DD");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.servlet.NPAL1200.NPAL1200_ABMsgArray)newMsgArray("A");
		xxPopPrm = (EZDBStringItem)newItem("xxPopPrm");
		xxPopPrm_P0 = (EZDBStringItem)newItem("xxPopPrm_P0");
		xxPopPrm_P1 = (EZDBStringItem)newItem("xxPopPrm_P1");
		xxPopPrm_P2 = (EZDBStringItem)newItem("xxPopPrm_P2");
		xxPopPrm_P3 = (EZDBStringItem)newItem("xxPopPrm_P3");
		xxPopPrm_P4 = (EZDBStringItem)newItem("xxPopPrm_P4");
		xxPopPrm_P5 = (EZDBStringItem)newItem("xxPopPrm_P5");
		xxPopPrm_P6 = (EZDBStringItem)newItem("xxPopPrm_P6");
		xxPopPrm_P7 = (EZDBStringItem)newItem("xxPopPrm_P7");
		xxPopPrm_P8 = (EZDBStringItem)newItem("xxPopPrm_P8");
		xxPopPrm_P9 = (EZDBStringItem)newItem("xxPopPrm_P9");
		xxPopPrm_PA = (EZDBStringItem)newItem("xxPopPrm_PA");
		xxPopPrm_PB = (EZDBStringItem)newItem("xxPopPrm_PB");
		eventNm = (EZDBStringItem)newItem("eventNm");
		B = (business.servlet.NPAL1200.NPAL1200_BBMsgArray)newMsgArray("B");
		C = (business.servlet.NPAL1200.NPAL1200_CBMsgArray)newMsgArray("C");
		varCharConstVal_XX = (EZDBStringItem)newItem("varCharConstVal_XX");
		xxNum_MD = (EZDBBigDecimalItem)newItem("xxNum_MD");
		xxNum_EF = (EZDBBigDecimalItem)newItem("xxNum_EF");
		D = (business.servlet.NPAL1200.NPAL1200_DBMsgArray)newMsgArray("D");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1200BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1200BMsgArray();
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
	{"prchReqTpCd_SF", "prchReqTpCd_SF", "SF", null, TYPE_HANKAKUEISU, "4", null},
	{"prchReqTpCd_CF", "prchReqTpCd_CF", "CF", "99", TYPE_HANKAKUEISU, "4", null},
	{"prchReqTpDescTxt_DF", "prchReqTpDescTxt_DF", "DF", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxTpCd_SF", "xxTpCd_SF", "SF", null, TYPE_HANKAKUEISU, "1", null},
	{"xxTpCd_CF", "xxTpCd_CF", "CF", "99", TYPE_HANKAKUEISU, "1", null},
	{"xxTpNm_DF", "xxTpNm_DF", "DF", "99", TYPE_HANKAKUEISU, "10", null},
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
	{"A", "A", null, "40", "business.servlet.NPAL1200.NPAL1200_ABMsgArray", null, null},
	
	{"xxPopPrm", "xxPopPrm", null, null, TYPE_HANKAKUEISU, "300", null},
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
	{"eventNm", "eventNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"B", "B", null, "99", "business.servlet.NPAL1200.NPAL1200_BBMsgArray", null, null},
	
	{"C", "C", null, "99", "business.servlet.NPAL1200.NPAL1200_CBMsgArray", null, null},
	
	{"varCharConstVal_XX", "varCharConstVal_XX", "XX", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxNum_MD", "xxNum_MD", "MD", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxNum_EF", "xxNum_EF", "EF", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"D", "D", null, "99", "business.servlet.NPAL1200.NPAL1200_DBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_SE
        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_CD
        {"SRCH_OPT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_DI
        {"SRCH_OPT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_TX
        {"SRCH_OPT_USR_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptUsrId_U1
        {"SRC_ZN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcZnCd_SF
        {"SRC_ZN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcZnCd_CF
        {"SRC_ZN_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcZnCd_DF
        {"SRC_ZN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcZnCd_ST
        {"SRC_ZN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcZnCd_CT
        {"SRC_ZN_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcZnCd_DT
        {"MDSE_ITEM_CLS_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpCd_SH
        {"MDSE_ITEM_CLS_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpCd_CH
        {"MDSE_ITEM_CLS_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpDescTxt_DH
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A1
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_HF
        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_HF
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A2
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_HT
        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_HT
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
        {"PRCH_REQ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqTpCd_SF
        {"PRCH_REQ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqTpCd_CF
        {"PRCH_REQ_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqTpDescTxt_DF
        {"XX_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTpCd_SF
        {"XX_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTpCd_CF
        {"XX_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTpNm_DF
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A3
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_DF
        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_DF
        {"WH_OWNR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whOwnrCd_DF
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A4
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_DT
        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_DT
        {"MDSE_ITEM_CLS_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpCd_SD
        {"MDSE_ITEM_CLS_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpCd_CD
        {"MDSE_ITEM_CLS_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpDescTxt_DD
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P0
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P1
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P2
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P3
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P4
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P5
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P6
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P7
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P8
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P9
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PA
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PB
        {"EVENT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eventNm
		null,	//B
		null,	//C
        {"VAR_CHAR_CONST_VAL",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//varCharConstVal_XX
        {"XX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_MD
        {"XX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_EF
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
