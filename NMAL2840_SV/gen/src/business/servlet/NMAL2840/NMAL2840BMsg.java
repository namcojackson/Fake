//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161108105530000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2840BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2840;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2840 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2840BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_DT_TM_1*/
	public final EZDBStringItem              xxDtTm_1;

    /** DUNS_CRIT_CD_PS*/
	public final EZDBStringItem              dunsCritCd_PS;

    /** DUNS_CRIT_CD_PC*/
	public final EZDBStringItemArray              dunsCritCd_PC;

    /** DUNS_CRIT_DESC_TXT_PC*/
	public final EZDBStringItemArray              dunsCritDescTxt_PC;

    /** DUNS_CRIT_DEF_VAL_FLG_11*/
	public final EZDBStringItem              dunsCritDefValFlg_11;

    /** DUNS_CRIT_DEF_VAL_FLG_12*/
	public final EZDBStringItem              dunsCritDefValFlg_12;

    /** DUNS_CRIT_DEF_VAL_FLG_21*/
	public final EZDBStringItem              dunsCritDefValFlg_21;

    /** DUNS_CRIT_DEF_VAL_FLG_31*/
	public final EZDBStringItem              dunsCritDefValFlg_31;

    /** DUNS_CRIT_DEF_VAL_FLG_32*/
	public final EZDBStringItem              dunsCritDefValFlg_32;

    /** DUNS_CRIT_DEF_VAL_FLG_33*/
	public final EZDBStringItem              dunsCritDefValFlg_33;

    /** EFF_FROM_DT*/
	public final EZDBDateItem              effFromDt;

    /** DUNS_CRIT_CD_BK*/
	public final EZDBStringItem              dunsCritCd_BK;

    /** DUNS_CRIT_DEF_VAL_FLG_B1*/
	public final EZDBStringItem              dunsCritDefValFlg_B1;

    /** DUNS_CRIT_DEF_VAL_FLG_B2*/
	public final EZDBStringItem              dunsCritDefValFlg_B2;

    /** DUNS_CRIT_DEF_VAL_FLG_B3*/
	public final EZDBStringItem              dunsCritDefValFlg_B3;

    /** DUNS_CRIT_DEF_VAL_FLG_B4*/
	public final EZDBStringItem              dunsCritDefValFlg_B4;

    /** DUNS_CRIT_DEF_VAL_FLG_B5*/
	public final EZDBStringItem              dunsCritDefValFlg_B5;

    /** DUNS_CRIT_DEF_VAL_FLG_B6*/
	public final EZDBStringItem              dunsCritDefValFlg_B6;

    /** EFF_FROM_DT_BK*/
	public final EZDBDateItem              effFromDt_BK;

    /** DUNS_CRIT_DEF_VAL_FLG_WN*/
	public final EZDBStringItem              dunsCritDefValFlg_WN;

    /** XX_DT_TM_2*/
	public final EZDBStringItem              xxDtTm_2;

    /** XX_DT_TM_PS*/
	public final EZDBStringItem              xxDtTm_PS;

    /** XX_DT_TM_PC*/
	public final EZDBStringItemArray              xxDtTm_PC;

    /** XX_DT_TM_PN*/
	public final EZDBStringItemArray              xxDtTm_PN;

    /** DUNS_FILE_NM_1*/
	public final EZDBStringItem              dunsFileNm_1;

    /** DUNS_FILE_TP_DESC_TXT*/
	public final EZDBStringItem              dunsFileTpDescTxt;

    /** DUNS_CRIT_DESC_TXT_51*/
	public final EZDBStringItem              dunsCritDescTxt_51;

    /** DUNS_CRIT_DEF_VAL_FLG_51*/
	public final EZDBStringItem              dunsCritDefValFlg_51;

    /** DUNS_CRIT_DESC_TXT_52*/
	public final EZDBStringItem              dunsCritDescTxt_52;

    /** DUNS_CRIT_DEF_VAL_FLG_52*/
	public final EZDBStringItem              dunsCritDefValFlg_52;

    /** DUNS_CRIT_DESC_TXT_53*/
	public final EZDBStringItem              dunsCritDescTxt_53;

    /** DUNS_CRIT_DEF_VAL_FLG_53*/
	public final EZDBStringItem              dunsCritDefValFlg_53;

    /** DUNS_CRIT_DESC_TXT_54*/
	public final EZDBStringItem              dunsCritDescTxt_54;

    /** DUNS_CRIT_DEF_VAL_FLG_54*/
	public final EZDBStringItem              dunsCritDefValFlg_54;

    /** DUNS_CRIT_DESC_TXT_55*/
	public final EZDBStringItem              dunsCritDescTxt_55;

    /** DUNS_CRIT_DEF_VAL_FLG_55*/
	public final EZDBStringItem              dunsCritDefValFlg_55;

    /** DUNS_CRIT_DESC_TXT_56*/
	public final EZDBStringItem              dunsCritDescTxt_56;

    /** DUNS_CRIT_DEF_VAL_FLG_56*/
	public final EZDBStringItem              dunsCritDefValFlg_56;

    /** DUNS_CRIT_DESC_TXT_57*/
	public final EZDBStringItem              dunsCritDescTxt_57;

    /** DUNS_CRIT_DEF_VAL_FLG_57*/
	public final EZDBStringItem              dunsCritDefValFlg_57;

    /** XX_ROW_NUM*/
	public final EZDBBigDecimalItem              xxRowNum;

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

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;

    /** XX_POP_PRM*/
	public final EZDBStringItem              xxPopPrm;

    /** A*/
	public final business.servlet.NMAL2840.NMAL2840_ABMsgArray              A;


	/**
	 * NMAL2840BMsg is constructor.
	 * The initialization when the instance of NMAL2840BMsg is generated.
	 */
	public NMAL2840BMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2840BMsg is constructor.
	 * The initialization when the instance of NMAL2840BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2840BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxDtTm_1 = (EZDBStringItem)newItem("xxDtTm_1");
		dunsCritCd_PS = (EZDBStringItem)newItem("dunsCritCd_PS");
		dunsCritCd_PC = (EZDBStringItemArray)newItemArray("dunsCritCd_PC");
		dunsCritDescTxt_PC = (EZDBStringItemArray)newItemArray("dunsCritDescTxt_PC");
		dunsCritDefValFlg_11 = (EZDBStringItem)newItem("dunsCritDefValFlg_11");
		dunsCritDefValFlg_12 = (EZDBStringItem)newItem("dunsCritDefValFlg_12");
		dunsCritDefValFlg_21 = (EZDBStringItem)newItem("dunsCritDefValFlg_21");
		dunsCritDefValFlg_31 = (EZDBStringItem)newItem("dunsCritDefValFlg_31");
		dunsCritDefValFlg_32 = (EZDBStringItem)newItem("dunsCritDefValFlg_32");
		dunsCritDefValFlg_33 = (EZDBStringItem)newItem("dunsCritDefValFlg_33");
		effFromDt = (EZDBDateItem)newItem("effFromDt");
		dunsCritCd_BK = (EZDBStringItem)newItem("dunsCritCd_BK");
		dunsCritDefValFlg_B1 = (EZDBStringItem)newItem("dunsCritDefValFlg_B1");
		dunsCritDefValFlg_B2 = (EZDBStringItem)newItem("dunsCritDefValFlg_B2");
		dunsCritDefValFlg_B3 = (EZDBStringItem)newItem("dunsCritDefValFlg_B3");
		dunsCritDefValFlg_B4 = (EZDBStringItem)newItem("dunsCritDefValFlg_B4");
		dunsCritDefValFlg_B5 = (EZDBStringItem)newItem("dunsCritDefValFlg_B5");
		dunsCritDefValFlg_B6 = (EZDBStringItem)newItem("dunsCritDefValFlg_B6");
		effFromDt_BK = (EZDBDateItem)newItem("effFromDt_BK");
		dunsCritDefValFlg_WN = (EZDBStringItem)newItem("dunsCritDefValFlg_WN");
		xxDtTm_2 = (EZDBStringItem)newItem("xxDtTm_2");
		xxDtTm_PS = (EZDBStringItem)newItem("xxDtTm_PS");
		xxDtTm_PC = (EZDBStringItemArray)newItemArray("xxDtTm_PC");
		xxDtTm_PN = (EZDBStringItemArray)newItemArray("xxDtTm_PN");
		dunsFileNm_1 = (EZDBStringItem)newItem("dunsFileNm_1");
		dunsFileTpDescTxt = (EZDBStringItem)newItem("dunsFileTpDescTxt");
		dunsCritDescTxt_51 = (EZDBStringItem)newItem("dunsCritDescTxt_51");
		dunsCritDefValFlg_51 = (EZDBStringItem)newItem("dunsCritDefValFlg_51");
		dunsCritDescTxt_52 = (EZDBStringItem)newItem("dunsCritDescTxt_52");
		dunsCritDefValFlg_52 = (EZDBStringItem)newItem("dunsCritDefValFlg_52");
		dunsCritDescTxt_53 = (EZDBStringItem)newItem("dunsCritDescTxt_53");
		dunsCritDefValFlg_53 = (EZDBStringItem)newItem("dunsCritDefValFlg_53");
		dunsCritDescTxt_54 = (EZDBStringItem)newItem("dunsCritDescTxt_54");
		dunsCritDefValFlg_54 = (EZDBStringItem)newItem("dunsCritDefValFlg_54");
		dunsCritDescTxt_55 = (EZDBStringItem)newItem("dunsCritDescTxt_55");
		dunsCritDefValFlg_55 = (EZDBStringItem)newItem("dunsCritDefValFlg_55");
		dunsCritDescTxt_56 = (EZDBStringItem)newItem("dunsCritDescTxt_56");
		dunsCritDefValFlg_56 = (EZDBStringItem)newItem("dunsCritDefValFlg_56");
		dunsCritDescTxt_57 = (EZDBStringItem)newItem("dunsCritDescTxt_57");
		dunsCritDefValFlg_57 = (EZDBStringItem)newItem("dunsCritDefValFlg_57");
		xxRowNum = (EZDBBigDecimalItem)newItem("xxRowNum");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		xxPopPrm = (EZDBStringItem)newItem("xxPopPrm");
		A = (business.servlet.NMAL2840.NMAL2840_ABMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2840BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2840BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxDtTm_1", "xxDtTm_1", "1", null, TYPE_HANKAKUEISU, "23", null},
	{"dunsCritCd_PS", "dunsCritCd_PS", "PS", null, TYPE_HANKAKUEISU, "4", null},
	{"dunsCritCd_PC", "dunsCritCd_PC", "PC", "99", TYPE_HANKAKUEISU, "4", null},
	{"dunsCritDescTxt_PC", "dunsCritDescTxt_PC", "PC", "99", TYPE_HANKAKUEISU, "50", null},
	{"dunsCritDefValFlg_11", "dunsCritDefValFlg_11", "11", null, TYPE_HANKAKUEISU, "1", null},
	{"dunsCritDefValFlg_12", "dunsCritDefValFlg_12", "12", null, TYPE_HANKAKUEISU, "1", null},
	{"dunsCritDefValFlg_21", "dunsCritDefValFlg_21", "21", null, TYPE_HANKAKUEISU, "1", null},
	{"dunsCritDefValFlg_31", "dunsCritDefValFlg_31", "31", null, TYPE_HANKAKUEISU, "1", null},
	{"dunsCritDefValFlg_32", "dunsCritDefValFlg_32", "32", null, TYPE_HANKAKUEISU, "1", null},
	{"dunsCritDefValFlg_33", "dunsCritDefValFlg_33", "33", null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dunsCritCd_BK", "dunsCritCd_BK", "BK", null, TYPE_HANKAKUEISU, "4", null},
	{"dunsCritDefValFlg_B1", "dunsCritDefValFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"dunsCritDefValFlg_B2", "dunsCritDefValFlg_B2", "B2", null, TYPE_HANKAKUEISU, "1", null},
	{"dunsCritDefValFlg_B3", "dunsCritDefValFlg_B3", "B3", null, TYPE_HANKAKUEISU, "1", null},
	{"dunsCritDefValFlg_B4", "dunsCritDefValFlg_B4", "B4", null, TYPE_HANKAKUEISU, "1", null},
	{"dunsCritDefValFlg_B5", "dunsCritDefValFlg_B5", "B5", null, TYPE_HANKAKUEISU, "1", null},
	{"dunsCritDefValFlg_B6", "dunsCritDefValFlg_B6", "B6", null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt_BK", "effFromDt_BK", "BK", null, TYPE_NENTSUKIHI, "8", null},
	{"dunsCritDefValFlg_WN", "dunsCritDefValFlg_WN", "WN", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDtTm_2", "xxDtTm_2", "2", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_PS", "xxDtTm_PS", "PS", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_PC", "xxDtTm_PC", "PC", "20", TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_PN", "xxDtTm_PN", "PN", "20", TYPE_HANKAKUEISU, "23", null},
	{"dunsFileNm_1", "dunsFileNm_1", "1", null, TYPE_HANKAKUEISU, "256", null},
	{"dunsFileTpDescTxt", "dunsFileTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dunsCritDescTxt_51", "dunsCritDescTxt_51", "51", null, TYPE_HANKAKUEISU, "50", null},
	{"dunsCritDefValFlg_51", "dunsCritDefValFlg_51", "51", null, TYPE_HANKAKUEISU, "1", null},
	{"dunsCritDescTxt_52", "dunsCritDescTxt_52", "52", null, TYPE_HANKAKUEISU, "50", null},
	{"dunsCritDefValFlg_52", "dunsCritDefValFlg_52", "52", null, TYPE_HANKAKUEISU, "1", null},
	{"dunsCritDescTxt_53", "dunsCritDescTxt_53", "53", null, TYPE_HANKAKUEISU, "50", null},
	{"dunsCritDefValFlg_53", "dunsCritDefValFlg_53", "53", null, TYPE_HANKAKUEISU, "1", null},
	{"dunsCritDescTxt_54", "dunsCritDescTxt_54", "54", null, TYPE_HANKAKUEISU, "50", null},
	{"dunsCritDefValFlg_54", "dunsCritDefValFlg_54", "54", null, TYPE_HANKAKUEISU, "1", null},
	{"dunsCritDescTxt_55", "dunsCritDescTxt_55", "55", null, TYPE_HANKAKUEISU, "50", null},
	{"dunsCritDefValFlg_55", "dunsCritDefValFlg_55", "55", null, TYPE_HANKAKUEISU, "1", null},
	{"dunsCritDescTxt_56", "dunsCritDescTxt_56", "56", null, TYPE_HANKAKUEISU, "50", null},
	{"dunsCritDefValFlg_56", "dunsCritDefValFlg_56", "56", null, TYPE_HANKAKUEISU, "1", null},
	{"dunsCritDescTxt_57", "dunsCritDescTxt_57", "57", null, TYPE_HANKAKUEISU, "50", null},
	{"dunsCritDefValFlg_57", "dunsCritDefValFlg_57", "57", null, TYPE_HANKAKUEISU, "1", null},
	{"xxRowNum", "xxRowNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"xxPopPrm", "xxPopPrm", null, null, TYPE_HANKAKUEISU, "300", null},
	{"A", "A", null, "40", "business.servlet.NMAL2840.NMAL2840_ABMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_DT_TM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_1
        {"DUNS_CRIT_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritCd_PS
        {"DUNS_CRIT_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritCd_PC
        {"DUNS_CRIT_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDescTxt_PC
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_11
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_12
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_21
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_31
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_32
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_33
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt
        {"DUNS_CRIT_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritCd_BK
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_B1
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_B2
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_B3
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_B4
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_B5
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_B6
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_BK
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_WN
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_2
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_PS
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_PC
        {"XX_DT_TM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_PN
        {"DUNS_FILE_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsFileNm_1
        {"DUNS_FILE_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsFileTpDescTxt
        {"DUNS_CRIT_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDescTxt_51
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_51
        {"DUNS_CRIT_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDescTxt_52
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_52
        {"DUNS_CRIT_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDescTxt_53
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_53
        {"DUNS_CRIT_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDescTxt_54
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_54
        {"DUNS_CRIT_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDescTxt_55
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_55
        {"DUNS_CRIT_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDescTxt_56
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_56
        {"DUNS_CRIT_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDescTxt_57
        {"DUNS_CRIT_DEF_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCritDefValFlg_57
        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm
		null,	//A
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
