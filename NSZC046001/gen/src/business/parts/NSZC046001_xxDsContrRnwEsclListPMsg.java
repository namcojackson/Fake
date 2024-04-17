//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20220328112903000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC046001_xxDsContrRnwEsclListPMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSZC046001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC046001_xxDsContrRnwEsclListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_MODE_CD*/
	public final EZDPStringItem              xxModeCd;

    /** DS_CONTR_RNW_ESCL_PK*/
	public final EZDPBigDecimalItem              dsContrRnwEsclPk;

    /** DS_CONTR_PK*/
	public final EZDPBigDecimalItem              dsContrPk;

    /** DS_CONTR_DTL_PK*/
	public final EZDPBigDecimalItem              dsContrDtlPk;

    /** DS_CONTR_MACH_LVL_NUM*/
	public final EZDPStringItem              dsContrMachLvlNum;

    /** DS_CONTR_BASE_USG_NM*/
	public final EZDPStringItem              dsContrBaseUsgNm;

    /** CONTR_AUTO_RNW_TP_CD*/
	public final EZDPStringItem              contrAutoRnwTpCd;

    /** RNW_PRC_METH_CD*/
	public final EZDPStringItem              rnwPrcMethCd;

    /** BEF_END_RNW_DAYS_AOT*/
	public final EZDPBigDecimalItem              befEndRnwDaysAot;

    /** BASE_PRC_UP_RATIO*/
	public final EZDPBigDecimalItem              basePrcUpRatio;

    /** MTR_PRC_UP_RATIO*/
	public final EZDPBigDecimalItem              mtrPrcUpRatio;

    /** CONTR_UPLFT_TP_CD*/
	public final EZDPStringItem              contrUplftTpCd;

    /** UPLFT_PRC_METH_CD*/
	public final EZDPStringItem              uplftPrcMethCd;

    /** UPLFT_BASE_PRC_UP_RATIO*/
	public final EZDPBigDecimalItem              uplftBasePrcUpRatio;

    /** UPLFT_MTR_PRC_UP_RATIO*/
	public final EZDPBigDecimalItem              uplftMtrPrcUpRatio;

    /** FIRST_YR_CONTR_UPLFT_FLG*/
	public final EZDPStringItem              firstYrContrUplftFlg;

    /** SCD_YR_CONTR_UPLFT_FLG*/
	public final EZDPStringItem              scdYrContrUplftFlg;

    /** THIRD_YR_CONTR_UPLFT_FLG*/
	public final EZDPStringItem              thirdYrContrUplftFlg;

    /** FRTH_YR_CONTR_UPLFT_FLG*/
	public final EZDPStringItem              frthYrContrUplftFlg;

    /** FIFTH_YR_CONTR_UPLFT_FLG*/
	public final EZDPStringItem              fifthYrContrUplftFlg;

    /** SIXTH_YR_CONTR_UPLFT_FLG*/
	public final EZDPStringItem              sixthYrContrUplftFlg;

    /** SVNTH_YR_CONTR_UPLFT_FLG*/
	public final EZDPStringItem              svnthYrContrUplftFlg;

    /** EIGHTH_YR_CONTR_UPLFT_FLG*/
	public final EZDPStringItem              eighthYrContrUplftFlg;

    /** NINTH_YR_CONTR_UPLFT_FLG*/
	public final EZDPStringItem              ninthYrContrUplftFlg;

    /** TENTH_YR_CONTR_UPLFT_FLG*/
	public final EZDPStringItem              tenthYrContrUplftFlg;

    /** UPLFT_BEF_END_RNW_DAYS_AOT*/
	public final EZDPBigDecimalItem              uplftBefEndRnwDaysAot;

    /** SVC_MACH_MSTR_PK*/
	public final EZDPBigDecimalItem              svcMachMstrPk;

    /** XX_MSG_ID*/
	public final EZDPStringItem              xxMsgId;

    /** XX_DS_MULT_MSG_DPLY_TXT*/
	public final EZDPStringItem              xxDsMultMsgDplyTxt;


	/**
	 * NSZC046001_xxDsContrRnwEsclListPMsg is constructor.
	 * The initialization when the instance of NSZC046001_xxDsContrRnwEsclListPMsg is generated.
	 */
	public NSZC046001_xxDsContrRnwEsclListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC046001_xxDsContrRnwEsclListPMsg is constructor.
	 * The initialization when the instance of NSZC046001_xxDsContrRnwEsclListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC046001_xxDsContrRnwEsclListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxModeCd = (EZDPStringItem)newItem("xxModeCd");
		dsContrRnwEsclPk = (EZDPBigDecimalItem)newItem("dsContrRnwEsclPk");
		dsContrPk = (EZDPBigDecimalItem)newItem("dsContrPk");
		dsContrDtlPk = (EZDPBigDecimalItem)newItem("dsContrDtlPk");
		dsContrMachLvlNum = (EZDPStringItem)newItem("dsContrMachLvlNum");
		dsContrBaseUsgNm = (EZDPStringItem)newItem("dsContrBaseUsgNm");
		contrAutoRnwTpCd = (EZDPStringItem)newItem("contrAutoRnwTpCd");
		rnwPrcMethCd = (EZDPStringItem)newItem("rnwPrcMethCd");
		befEndRnwDaysAot = (EZDPBigDecimalItem)newItem("befEndRnwDaysAot");
		basePrcUpRatio = (EZDPBigDecimalItem)newItem("basePrcUpRatio");
		mtrPrcUpRatio = (EZDPBigDecimalItem)newItem("mtrPrcUpRatio");
		contrUplftTpCd = (EZDPStringItem)newItem("contrUplftTpCd");
		uplftPrcMethCd = (EZDPStringItem)newItem("uplftPrcMethCd");
		uplftBasePrcUpRatio = (EZDPBigDecimalItem)newItem("uplftBasePrcUpRatio");
		uplftMtrPrcUpRatio = (EZDPBigDecimalItem)newItem("uplftMtrPrcUpRatio");
		firstYrContrUplftFlg = (EZDPStringItem)newItem("firstYrContrUplftFlg");
		scdYrContrUplftFlg = (EZDPStringItem)newItem("scdYrContrUplftFlg");
		thirdYrContrUplftFlg = (EZDPStringItem)newItem("thirdYrContrUplftFlg");
		frthYrContrUplftFlg = (EZDPStringItem)newItem("frthYrContrUplftFlg");
		fifthYrContrUplftFlg = (EZDPStringItem)newItem("fifthYrContrUplftFlg");
		sixthYrContrUplftFlg = (EZDPStringItem)newItem("sixthYrContrUplftFlg");
		svnthYrContrUplftFlg = (EZDPStringItem)newItem("svnthYrContrUplftFlg");
		eighthYrContrUplftFlg = (EZDPStringItem)newItem("eighthYrContrUplftFlg");
		ninthYrContrUplftFlg = (EZDPStringItem)newItem("ninthYrContrUplftFlg");
		tenthYrContrUplftFlg = (EZDPStringItem)newItem("tenthYrContrUplftFlg");
		uplftBefEndRnwDaysAot = (EZDPBigDecimalItem)newItem("uplftBefEndRnwDaysAot");
		svcMachMstrPk = (EZDPBigDecimalItem)newItem("svcMachMstrPk");
		xxMsgId = (EZDPStringItem)newItem("xxMsgId");
		xxDsMultMsgDplyTxt = (EZDPStringItem)newItem("xxDsMultMsgDplyTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC046001_xxDsContrRnwEsclListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC046001_xxDsContrRnwEsclListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrRnwEsclPk", "dsContrRnwEsclPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrPk", "dsContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrMachLvlNum", "dsContrMachLvlNum", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrBaseUsgNm", "dsContrBaseUsgNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"contrAutoRnwTpCd", "contrAutoRnwTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"rnwPrcMethCd", "rnwPrcMethCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"befEndRnwDaysAot", "befEndRnwDaysAot", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"basePrcUpRatio", "basePrcUpRatio", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"mtrPrcUpRatio", "mtrPrcUpRatio", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"contrUplftTpCd", "contrUplftTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"uplftPrcMethCd", "uplftPrcMethCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"uplftBasePrcUpRatio", "uplftBasePrcUpRatio", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"uplftMtrPrcUpRatio", "uplftMtrPrcUpRatio", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"firstYrContrUplftFlg", "firstYrContrUplftFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"scdYrContrUplftFlg", "scdYrContrUplftFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"thirdYrContrUplftFlg", "thirdYrContrUplftFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"frthYrContrUplftFlg", "frthYrContrUplftFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"fifthYrContrUplftFlg", "fifthYrContrUplftFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"sixthYrContrUplftFlg", "sixthYrContrUplftFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"svnthYrContrUplftFlg", "svnthYrContrUplftFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"eighthYrContrUplftFlg", "eighthYrContrUplftFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"ninthYrContrUplftFlg", "ninthYrContrUplftFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"tenthYrContrUplftFlg", "tenthYrContrUplftFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"uplftBefEndRnwDaysAot", "uplftBefEndRnwDaysAot", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxMsgId", "xxMsgId", null, null, TYPE_HANKAKUEISU, "9", null},
	{"xxDsMultMsgDplyTxt", "xxDsMultMsgDplyTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"DS_CONTR_RNW_ESCL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrRnwEsclPk
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"DS_CONTR_MACH_LVL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrMachLvlNum
        {"DS_CONTR_BASE_USG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBaseUsgNm
        {"CONTR_AUTO_RNW_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrAutoRnwTpCd
        {"RNW_PRC_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwPrcMethCd
        {"BEF_END_RNW_DAYS_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befEndRnwDaysAot
        {"BASE_PRC_UP_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcUpRatio
        {"MTR_PRC_UP_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrPrcUpRatio
        {"CONTR_UPLFT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrUplftTpCd
        {"UPLFT_PRC_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftPrcMethCd
        {"UPLFT_BASE_PRC_UP_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftBasePrcUpRatio
        {"UPLFT_MTR_PRC_UP_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftMtrPrcUpRatio
        {"FIRST_YR_CONTR_UPLFT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstYrContrUplftFlg
        {"SCD_YR_CONTR_UPLFT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdYrContrUplftFlg
        {"THIRD_YR_CONTR_UPLFT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdYrContrUplftFlg
        {"FRTH_YR_CONTR_UPLFT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthYrContrUplftFlg
        {"FIFTH_YR_CONTR_UPLFT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fifthYrContrUplftFlg
        {"SIXTH_YR_CONTR_UPLFT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sixthYrContrUplftFlg
        {"SVNTH_YR_CONTR_UPLFT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svnthYrContrUplftFlg
        {"EIGHTH_YR_CONTR_UPLFT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eighthYrContrUplftFlg
        {"NINTH_YR_CONTR_UPLFT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ninthYrContrUplftFlg
        {"TENTH_YR_CONTR_UPLFT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tenthYrContrUplftFlg
        {"UPLFT_BEF_END_RNW_DAYS_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftBefEndRnwDaysAot
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"XX_MSG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgId
        {"XX_DS_MULT_MSG_DPLY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMultMsgDplyTxt
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
