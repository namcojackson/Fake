//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180228113840000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0250SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0250;

import parts.common.EZDMsgArray;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0250 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0250SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_ITEM_TP_CD*/
	public final EZDSStringItem              mdseItemTpCd;

    /** MDSE_ITEM_TP_CD_L*/
	public final EZDSStringItem              mdseItemTpCd_L;

    /** MDSE_ITEM_TP_DESC_TXT_L*/
	public final EZDSStringItem              mdseItemTpDescTxt_L;

    /** MDSE_ITEM_TP_DESC_TXT*/
	public final EZDSStringItem              mdseItemTpDescTxt;

    /** MDSE_CD*/
	public final EZDSStringItem              mdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDSStringItem              mdseDescShortTxt;

    /** COA_MDSE_TP_CD*/
	public final EZDSStringItem              coaMdseTpCd;

    /** COA_MDSE_TP_CD_L*/
	public final EZDSStringItem              coaMdseTpCd_L;

    /** COA_PROJ_DESC_TXT_L*/
	public final EZDSStringItem              coaProjDescTxt_L;

    /** COA_PROJ_DESC_TXT*/
	public final EZDSStringItem              coaProjDescTxt;

    /** XX_SCR_ITEM_61_TXT*/
	public final EZDSStringItem              xxScrItem61Txt;

    /** ACTV_FLG*/
	public final EZDSStringItem              actvFlg;

    /** XX_CHK_BOX*/
	public final EZDSStringItem              xxChkBox;

    /** PRNT_MDSE_CD*/
	public final EZDSStringItem              prntMdseCd;

    /** XX_LINE_NUM*/
	public final EZDSStringItem              xxLineNum;

    /** CMPSN_MSG_TXT_A0*/
	public final EZDSStringItem              cmpsnMsgTxt_A0;

    /** CMPSN_MSG_PK_A0*/
	public final EZDSBigDecimalItem              cmpsnMsgPk_A0;

    /** CMPSN_MSG_SEG_ID_A0*/
	public final EZDSBigDecimalItem              cmpsnMsgSegId_A0;

    /** CMPSN_MSG_TXT_A1*/
	public final EZDSStringItem              cmpsnMsgTxt_A1;

    /** CMPSN_MSG_PK_A1*/
	public final EZDSBigDecimalItem              cmpsnMsgPk_A1;

    /** CMPSN_MSG_SEG_ID_A1*/
	public final EZDSBigDecimalItem              cmpsnMsgSegId_A1;

    /** CMPSN_MSG_TXT_A2*/
	public final EZDSStringItem              cmpsnMsgTxt_A2;

    /** CMPSN_MSG_PK_A2*/
	public final EZDSBigDecimalItem              cmpsnMsgPk_A2;

    /** CMPSN_MSG_SEG_ID_A2*/
	public final EZDSBigDecimalItem              cmpsnMsgSegId_A2;

    /** CMPSN_MSG_TXT_A3*/
	public final EZDSStringItem              cmpsnMsgTxt_A3;

    /** CMPSN_MSG_PK_A3*/
	public final EZDSBigDecimalItem              cmpsnMsgPk_A3;

    /** CMPSN_MSG_SEG_ID_A3*/
	public final EZDSBigDecimalItem              cmpsnMsgSegId_A3;

    /** CMPSN_MSG_TXT_A4*/
	public final EZDSStringItem              cmpsnMsgTxt_A4;

    /** CMPSN_MSG_PK_A4*/
	public final EZDSBigDecimalItem              cmpsnMsgPk_A4;

    /** CMPSN_MSG_SEG_ID_A4*/
	public final EZDSBigDecimalItem              cmpsnMsgSegId_A4;

    /** CMPSN_MSG_TXT_A5*/
	public final EZDSStringItem              cmpsnMsgTxt_A5;

    /** CMPSN_MSG_PK_A5*/
	public final EZDSBigDecimalItem              cmpsnMsgPk_A5;

    /** CMPSN_MSG_SEG_ID_A5*/
	public final EZDSBigDecimalItem              cmpsnMsgSegId_A5;

    /** CMPSN_MSG_TXT_A6*/
	public final EZDSStringItem              cmpsnMsgTxt_A6;

    /** CMPSN_MSG_PK_A6*/
	public final EZDSBigDecimalItem              cmpsnMsgPk_A6;

    /** CMPSN_MSG_SEG_ID_A6*/
	public final EZDSBigDecimalItem              cmpsnMsgSegId_A6;

    /** CMPSN_MSG_TXT_A7*/
	public final EZDSStringItem              cmpsnMsgTxt_A7;

    /** CMPSN_MSG_PK_A7*/
	public final EZDSBigDecimalItem              cmpsnMsgPk_A7;

    /** CMPSN_MSG_SEG_ID_A7*/
	public final EZDSBigDecimalItem              cmpsnMsgSegId_A7;

    /** CMPSN_MSG_TXT_A8*/
	public final EZDSStringItem              cmpsnMsgTxt_A8;

    /** CMPSN_MSG_PK_A8*/
	public final EZDSBigDecimalItem              cmpsnMsgPk_A8;

    /** CMPSN_MSG_SEG_ID_A8*/
	public final EZDSBigDecimalItem              cmpsnMsgSegId_A8;

    /** CMPSN_MSG_TXT_A9*/
	public final EZDSStringItem              cmpsnMsgTxt_A9;

    /** CMPSN_MSG_PK_A9*/
	public final EZDSBigDecimalItem              cmpsnMsgPk_A9;

    /** CMPSN_MSG_SEG_ID_A9*/
	public final EZDSBigDecimalItem              cmpsnMsgSegId_A9;

    /** CMPSN_MSG_TXT_AA*/
	public final EZDSStringItem              cmpsnMsgTxt_AA;

    /** CMPSN_MSG_PK_AA*/
	public final EZDSBigDecimalItem              cmpsnMsgPk_AA;

    /** CMPSN_MSG_SEG_ID_AA*/
	public final EZDSBigDecimalItem              cmpsnMsgSegId_AA;

    /** CMPSN_MSG_TXT_AB*/
	public final EZDSStringItem              cmpsnMsgTxt_AB;

    /** CMPSN_MSG_PK_AB*/
	public final EZDSBigDecimalItem              cmpsnMsgPk_AB;

    /** CMPSN_MSG_SEG_ID_AB*/
	public final EZDSBigDecimalItem              cmpsnMsgSegId_AB;

    /** CMPSN_MSG_TXT_AC*/
	public final EZDSStringItem              cmpsnMsgTxt_AC;

    /** CMPSN_MSG_PK_AC*/
	public final EZDSBigDecimalItem              cmpsnMsgPk_AC;

    /** CMPSN_MSG_SEG_ID_AC*/
	public final EZDSBigDecimalItem              cmpsnMsgSegId_AC;

    /** CMPSN_MSG_TXT_AD*/
	public final EZDSStringItem              cmpsnMsgTxt_AD;

    /** CMPSN_MSG_PK_AD*/
	public final EZDSBigDecimalItem              cmpsnMsgPk_AD;

    /** CMPSN_MSG_SEG_ID_AD*/
	public final EZDSBigDecimalItem              cmpsnMsgSegId_AD;

    /** CMPSN_MSG_TXT_AE*/
	public final EZDSStringItem              cmpsnMsgTxt_AE;

    /** CMPSN_MSG_PK_AE*/
	public final EZDSBigDecimalItem              cmpsnMsgPk_AE;

    /** CMPSN_MSG_SEG_ID_AE*/
	public final EZDSBigDecimalItem              cmpsnMsgSegId_AE;


	/**
	 * NMAL0250SMsg is constructor.
	 * The initialization when the instance of NMAL0250SMsg is generated.
	 */
	public NMAL0250SMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0250SMsg is constructor.
	 * The initialization when the instance of NMAL0250SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0250SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseItemTpCd = (EZDSStringItem)newItem("mdseItemTpCd");
		mdseItemTpCd_L = (EZDSStringItem)newItem("mdseItemTpCd_L");
		mdseItemTpDescTxt_L = (EZDSStringItem)newItem("mdseItemTpDescTxt_L");
		mdseItemTpDescTxt = (EZDSStringItem)newItem("mdseItemTpDescTxt");
		mdseCd = (EZDSStringItem)newItem("mdseCd");
		mdseDescShortTxt = (EZDSStringItem)newItem("mdseDescShortTxt");
		coaMdseTpCd = (EZDSStringItem)newItem("coaMdseTpCd");
		coaMdseTpCd_L = (EZDSStringItem)newItem("coaMdseTpCd_L");
		coaProjDescTxt_L = (EZDSStringItem)newItem("coaProjDescTxt_L");
		coaProjDescTxt = (EZDSStringItem)newItem("coaProjDescTxt");
		xxScrItem61Txt = (EZDSStringItem)newItem("xxScrItem61Txt");
		actvFlg = (EZDSStringItem)newItem("actvFlg");
		xxChkBox = (EZDSStringItem)newItem("xxChkBox");
		prntMdseCd = (EZDSStringItem)newItem("prntMdseCd");
		xxLineNum = (EZDSStringItem)newItem("xxLineNum");
		cmpsnMsgTxt_A0 = (EZDSStringItem)newItem("cmpsnMsgTxt_A0");
		cmpsnMsgPk_A0 = (EZDSBigDecimalItem)newItem("cmpsnMsgPk_A0");
		cmpsnMsgSegId_A0 = (EZDSBigDecimalItem)newItem("cmpsnMsgSegId_A0");
		cmpsnMsgTxt_A1 = (EZDSStringItem)newItem("cmpsnMsgTxt_A1");
		cmpsnMsgPk_A1 = (EZDSBigDecimalItem)newItem("cmpsnMsgPk_A1");
		cmpsnMsgSegId_A1 = (EZDSBigDecimalItem)newItem("cmpsnMsgSegId_A1");
		cmpsnMsgTxt_A2 = (EZDSStringItem)newItem("cmpsnMsgTxt_A2");
		cmpsnMsgPk_A2 = (EZDSBigDecimalItem)newItem("cmpsnMsgPk_A2");
		cmpsnMsgSegId_A2 = (EZDSBigDecimalItem)newItem("cmpsnMsgSegId_A2");
		cmpsnMsgTxt_A3 = (EZDSStringItem)newItem("cmpsnMsgTxt_A3");
		cmpsnMsgPk_A3 = (EZDSBigDecimalItem)newItem("cmpsnMsgPk_A3");
		cmpsnMsgSegId_A3 = (EZDSBigDecimalItem)newItem("cmpsnMsgSegId_A3");
		cmpsnMsgTxt_A4 = (EZDSStringItem)newItem("cmpsnMsgTxt_A4");
		cmpsnMsgPk_A4 = (EZDSBigDecimalItem)newItem("cmpsnMsgPk_A4");
		cmpsnMsgSegId_A4 = (EZDSBigDecimalItem)newItem("cmpsnMsgSegId_A4");
		cmpsnMsgTxt_A5 = (EZDSStringItem)newItem("cmpsnMsgTxt_A5");
		cmpsnMsgPk_A5 = (EZDSBigDecimalItem)newItem("cmpsnMsgPk_A5");
		cmpsnMsgSegId_A5 = (EZDSBigDecimalItem)newItem("cmpsnMsgSegId_A5");
		cmpsnMsgTxt_A6 = (EZDSStringItem)newItem("cmpsnMsgTxt_A6");
		cmpsnMsgPk_A6 = (EZDSBigDecimalItem)newItem("cmpsnMsgPk_A6");
		cmpsnMsgSegId_A6 = (EZDSBigDecimalItem)newItem("cmpsnMsgSegId_A6");
		cmpsnMsgTxt_A7 = (EZDSStringItem)newItem("cmpsnMsgTxt_A7");
		cmpsnMsgPk_A7 = (EZDSBigDecimalItem)newItem("cmpsnMsgPk_A7");
		cmpsnMsgSegId_A7 = (EZDSBigDecimalItem)newItem("cmpsnMsgSegId_A7");
		cmpsnMsgTxt_A8 = (EZDSStringItem)newItem("cmpsnMsgTxt_A8");
		cmpsnMsgPk_A8 = (EZDSBigDecimalItem)newItem("cmpsnMsgPk_A8");
		cmpsnMsgSegId_A8 = (EZDSBigDecimalItem)newItem("cmpsnMsgSegId_A8");
		cmpsnMsgTxt_A9 = (EZDSStringItem)newItem("cmpsnMsgTxt_A9");
		cmpsnMsgPk_A9 = (EZDSBigDecimalItem)newItem("cmpsnMsgPk_A9");
		cmpsnMsgSegId_A9 = (EZDSBigDecimalItem)newItem("cmpsnMsgSegId_A9");
		cmpsnMsgTxt_AA = (EZDSStringItem)newItem("cmpsnMsgTxt_AA");
		cmpsnMsgPk_AA = (EZDSBigDecimalItem)newItem("cmpsnMsgPk_AA");
		cmpsnMsgSegId_AA = (EZDSBigDecimalItem)newItem("cmpsnMsgSegId_AA");
		cmpsnMsgTxt_AB = (EZDSStringItem)newItem("cmpsnMsgTxt_AB");
		cmpsnMsgPk_AB = (EZDSBigDecimalItem)newItem("cmpsnMsgPk_AB");
		cmpsnMsgSegId_AB = (EZDSBigDecimalItem)newItem("cmpsnMsgSegId_AB");
		cmpsnMsgTxt_AC = (EZDSStringItem)newItem("cmpsnMsgTxt_AC");
		cmpsnMsgPk_AC = (EZDSBigDecimalItem)newItem("cmpsnMsgPk_AC");
		cmpsnMsgSegId_AC = (EZDSBigDecimalItem)newItem("cmpsnMsgSegId_AC");
		cmpsnMsgTxt_AD = (EZDSStringItem)newItem("cmpsnMsgTxt_AD");
		cmpsnMsgPk_AD = (EZDSBigDecimalItem)newItem("cmpsnMsgPk_AD");
		cmpsnMsgSegId_AD = (EZDSBigDecimalItem)newItem("cmpsnMsgSegId_AD");
		cmpsnMsgTxt_AE = (EZDSStringItem)newItem("cmpsnMsgTxt_AE");
		cmpsnMsgPk_AE = (EZDSBigDecimalItem)newItem("cmpsnMsgPk_AE");
		cmpsnMsgSegId_AE = (EZDSBigDecimalItem)newItem("cmpsnMsgSegId_AE");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0250SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0250SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseItemTpCd", "mdseItemTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemTpCd_L", "mdseItemTpCd_L", "L", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemTpDescTxt_L", "mdseItemTpDescTxt_L", "L", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseItemTpDescTxt", "mdseItemTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"coaMdseTpCd", "coaMdseTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"coaMdseTpCd_L", "coaMdseTpCd_L", "L", null, TYPE_HANKAKUEISU, "2", null},
	{"coaProjDescTxt_L", "coaProjDescTxt_L", "L", null, TYPE_HANKAKUEISU, "50", null},
	{"coaProjDescTxt", "coaProjDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxScrItem61Txt", "xxScrItem61Txt", null, null, TYPE_HANKAKUEISU, "61", null},
	{"actvFlg", "actvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxChkBox", "xxChkBox", null, null, TYPE_HANKAKUEIJI, "1", null},
	{"prntMdseCd", "prntMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"xxLineNum", "xxLineNum", null, null, TYPE_HANKAKUEISU, "11", null},
	{"cmpsnMsgTxt_A0", "cmpsnMsgTxt_A0", "A0", null, TYPE_HANKAKUEISU, "90", null},
	{"cmpsnMsgPk_A0", "cmpsnMsgPk_A0", "A0", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cmpsnMsgSegId_A0", "cmpsnMsgSegId_A0", "A0", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cmpsnMsgTxt_A1", "cmpsnMsgTxt_A1", "A1", null, TYPE_HANKAKUEISU, "90", null},
	{"cmpsnMsgPk_A1", "cmpsnMsgPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cmpsnMsgSegId_A1", "cmpsnMsgSegId_A1", "A1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cmpsnMsgTxt_A2", "cmpsnMsgTxt_A2", "A2", null, TYPE_HANKAKUEISU, "90", null},
	{"cmpsnMsgPk_A2", "cmpsnMsgPk_A2", "A2", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cmpsnMsgSegId_A2", "cmpsnMsgSegId_A2", "A2", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cmpsnMsgTxt_A3", "cmpsnMsgTxt_A3", "A3", null, TYPE_HANKAKUEISU, "90", null},
	{"cmpsnMsgPk_A3", "cmpsnMsgPk_A3", "A3", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cmpsnMsgSegId_A3", "cmpsnMsgSegId_A3", "A3", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cmpsnMsgTxt_A4", "cmpsnMsgTxt_A4", "A4", null, TYPE_HANKAKUEISU, "90", null},
	{"cmpsnMsgPk_A4", "cmpsnMsgPk_A4", "A4", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cmpsnMsgSegId_A4", "cmpsnMsgSegId_A4", "A4", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cmpsnMsgTxt_A5", "cmpsnMsgTxt_A5", "A5", null, TYPE_HANKAKUEISU, "90", null},
	{"cmpsnMsgPk_A5", "cmpsnMsgPk_A5", "A5", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cmpsnMsgSegId_A5", "cmpsnMsgSegId_A5", "A5", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cmpsnMsgTxt_A6", "cmpsnMsgTxt_A6", "A6", null, TYPE_HANKAKUEISU, "90", null},
	{"cmpsnMsgPk_A6", "cmpsnMsgPk_A6", "A6", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cmpsnMsgSegId_A6", "cmpsnMsgSegId_A6", "A6", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cmpsnMsgTxt_A7", "cmpsnMsgTxt_A7", "A7", null, TYPE_HANKAKUEISU, "90", null},
	{"cmpsnMsgPk_A7", "cmpsnMsgPk_A7", "A7", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cmpsnMsgSegId_A7", "cmpsnMsgSegId_A7", "A7", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cmpsnMsgTxt_A8", "cmpsnMsgTxt_A8", "A8", null, TYPE_HANKAKUEISU, "90", null},
	{"cmpsnMsgPk_A8", "cmpsnMsgPk_A8", "A8", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cmpsnMsgSegId_A8", "cmpsnMsgSegId_A8", "A8", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cmpsnMsgTxt_A9", "cmpsnMsgTxt_A9", "A9", null, TYPE_HANKAKUEISU, "90", null},
	{"cmpsnMsgPk_A9", "cmpsnMsgPk_A9", "A9", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cmpsnMsgSegId_A9", "cmpsnMsgSegId_A9", "A9", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cmpsnMsgTxt_AA", "cmpsnMsgTxt_AA", "AA", null, TYPE_HANKAKUEISU, "90", null},
	{"cmpsnMsgPk_AA", "cmpsnMsgPk_AA", "AA", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cmpsnMsgSegId_AA", "cmpsnMsgSegId_AA", "AA", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cmpsnMsgTxt_AB", "cmpsnMsgTxt_AB", "AB", null, TYPE_HANKAKUEISU, "90", null},
	{"cmpsnMsgPk_AB", "cmpsnMsgPk_AB", "AB", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cmpsnMsgSegId_AB", "cmpsnMsgSegId_AB", "AB", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cmpsnMsgTxt_AC", "cmpsnMsgTxt_AC", "AC", null, TYPE_HANKAKUEISU, "90", null},
	{"cmpsnMsgPk_AC", "cmpsnMsgPk_AC", "AC", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cmpsnMsgSegId_AC", "cmpsnMsgSegId_AC", "AC", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cmpsnMsgTxt_AD", "cmpsnMsgTxt_AD", "AD", null, TYPE_HANKAKUEISU, "90", null},
	{"cmpsnMsgPk_AD", "cmpsnMsgPk_AD", "AD", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cmpsnMsgSegId_AD", "cmpsnMsgSegId_AD", "AD", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cmpsnMsgTxt_AE", "cmpsnMsgTxt_AE", "AE", null, TYPE_HANKAKUEISU, "90", null},
	{"cmpsnMsgPk_AE", "cmpsnMsgPk_AE", "AE", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cmpsnMsgSegId_AE", "cmpsnMsgSegId_AE", "AE", null, TYPE_SEISU_SYOSU, "3", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_ITEM_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd
        {"MDSE_ITEM_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd_L
        {"MDSE_ITEM_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpDescTxt_L
        {"MDSE_ITEM_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpDescTxt
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"COA_MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd
        {"COA_MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd_L
        {"COA_PROJ_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjDescTxt_L
        {"COA_PROJ_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjDescTxt
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt
        {"ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"PRNT_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntMdseCd
        {"XX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineNum
        {"CMPSN_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgTxt_A0
        {"CMPSN_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgPk_A0
        {"CMPSN_MSG_SEG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgSegId_A0
        {"CMPSN_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgTxt_A1
        {"CMPSN_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgPk_A1
        {"CMPSN_MSG_SEG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgSegId_A1
        {"CMPSN_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgTxt_A2
        {"CMPSN_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgPk_A2
        {"CMPSN_MSG_SEG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgSegId_A2
        {"CMPSN_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgTxt_A3
        {"CMPSN_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgPk_A3
        {"CMPSN_MSG_SEG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgSegId_A3
        {"CMPSN_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgTxt_A4
        {"CMPSN_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgPk_A4
        {"CMPSN_MSG_SEG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgSegId_A4
        {"CMPSN_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgTxt_A5
        {"CMPSN_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgPk_A5
        {"CMPSN_MSG_SEG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgSegId_A5
        {"CMPSN_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgTxt_A6
        {"CMPSN_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgPk_A6
        {"CMPSN_MSG_SEG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgSegId_A6
        {"CMPSN_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgTxt_A7
        {"CMPSN_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgPk_A7
        {"CMPSN_MSG_SEG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgSegId_A7
        {"CMPSN_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgTxt_A8
        {"CMPSN_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgPk_A8
        {"CMPSN_MSG_SEG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgSegId_A8
        {"CMPSN_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgTxt_A9
        {"CMPSN_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgPk_A9
        {"CMPSN_MSG_SEG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgSegId_A9
        {"CMPSN_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgTxt_AA
        {"CMPSN_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgPk_AA
        {"CMPSN_MSG_SEG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgSegId_AA
        {"CMPSN_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgTxt_AB
        {"CMPSN_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgPk_AB
        {"CMPSN_MSG_SEG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgSegId_AB
        {"CMPSN_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgTxt_AC
        {"CMPSN_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgPk_AC
        {"CMPSN_MSG_SEG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgSegId_AC
        {"CMPSN_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgTxt_AD
        {"CMPSN_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgPk_AD
        {"CMPSN_MSG_SEG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgSegId_AD
        {"CMPSN_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgTxt_AE
        {"CMPSN_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgPk_AE
        {"CMPSN_MSG_SEG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgSegId_AE
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

