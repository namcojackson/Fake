//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160701175902000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL3200F01FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL3200F01 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL3200F01FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MKTG_MAP_RQST_PK*/
	public final EZDFBigDecimalItem              mktgMapRqstPk;

    /** MKTG_FLD_MAP_NM*/
	public final EZDFStringItem              mktgFldMapNm;

    /** DS_ACCT_NM*/
	public final EZDFStringItem              dsAcctNm;

    /** FIRST_LINE_ADDR*/
	public final EZDFStringItem              firstLineAddr;

    /** SCD_LINE_ADDR*/
	public final EZDFStringItem              scdLineAddr;

    /** THIRD_LINE_ADDR*/
	public final EZDFStringItem              thirdLineAddr;

    /** FRTH_LINE_ADDR*/
	public final EZDFStringItem              frthLineAddr;

    /** CTY_ADDR*/
	public final EZDFStringItem              ctyAddr;

    /** CNTY_PK*/
	public final EZDFBigDecimalItem              cntyPk;

    /** ST_CD*/
	public final EZDFStringItem              stCd;

    /** POST_CD*/
	public final EZDFStringItem              postCd;

    /** CTRY_CD*/
	public final EZDFStringItem              ctryCd;

    /** DUNS_NUM*/
	public final EZDFStringItem              dunsNum;

    /** GLN_NUM*/
	public final EZDFBigDecimalItem              glnNum;

    /** HIN_NUM*/
	public final EZDFStringItem              hinNum;

    /** MKTG_MAP_ATTRB_TXT_01*/
	public final EZDFStringItem              mktgMapAttrbTxt_01;

    /** MKTG_MAP_ATTRB_TXT_02*/
	public final EZDFStringItem              mktgMapAttrbTxt_02;

    /** MKTG_MAP_ATTRB_TXT_03*/
	public final EZDFStringItem              mktgMapAttrbTxt_03;

    /** MKTG_MAP_ATTRB_TXT_04*/
	public final EZDFStringItem              mktgMapAttrbTxt_04;

    /** MKTG_MAP_ATTRB_TXT_05*/
	public final EZDFStringItem              mktgMapAttrbTxt_05;

    /** MKTG_MAP_ATTRB_TXT_06*/
	public final EZDFStringItem              mktgMapAttrbTxt_06;

    /** MKTG_MAP_ATTRB_TXT_07*/
	public final EZDFStringItem              mktgMapAttrbTxt_07;

    /** MKTG_MAP_ATTRB_TXT_08*/
	public final EZDFStringItem              mktgMapAttrbTxt_08;

    /** MKTG_MAP_ATTRB_TXT_09*/
	public final EZDFStringItem              mktgMapAttrbTxt_09;

    /** MKTG_MAP_ATTRB_TXT_10*/
	public final EZDFStringItem              mktgMapAttrbTxt_10;

    /** MKTG_MAP_ATTRB_TXT_11*/
	public final EZDFStringItem              mktgMapAttrbTxt_11;

    /** MKTG_MAP_ATTRB_TXT_12*/
	public final EZDFStringItem              mktgMapAttrbTxt_12;

    /** MKTG_MAP_ATTRB_TXT_13*/
	public final EZDFStringItem              mktgMapAttrbTxt_13;

    /** MKTG_MAP_ATTRB_TXT_14*/
	public final EZDFStringItem              mktgMapAttrbTxt_14;

    /** MKTG_MAP_ATTRB_TXT_15*/
	public final EZDFStringItem              mktgMapAttrbTxt_15;

    /** MKTG_MAP_ATTRB_TXT_16*/
	public final EZDFStringItem              mktgMapAttrbTxt_16;

    /** MKTG_MAP_ATTRB_TXT_17*/
	public final EZDFStringItem              mktgMapAttrbTxt_17;

    /** MKTG_MAP_ATTRB_TXT_18*/
	public final EZDFStringItem              mktgMapAttrbTxt_18;

    /** MKTG_MAP_ATTRB_TXT_19*/
	public final EZDFStringItem              mktgMapAttrbTxt_19;

    /** MKTG_MAP_ATTRB_TXT_20*/
	public final EZDFStringItem              mktgMapAttrbTxt_20;

    /** CRAT_USR_ID*/
	public final EZDFStringItem              cratUsrId;

    /** CRAT_TS*/
	public final EZDFStringItem              cratTs;

    /** UPLD_FILE_NM*/
	public final EZDFStringItem              upldFileNm;

    /** MATCH_CRIT_TP_TXT*/
	public final EZDFStringItem              matchCritTpTxt;

    /** EXACT_MATCH_LOC_NUM*/
	public final EZDFStringItem              exactMatchLocNum;

    /** EXACT_MATCH_SF_ACCT_ID*/
	public final EZDFStringItem              exactMatchSfAcctId;

    /** PRTL_MATCH_LOC_NUM*/
	public final EZDFStringItem              prtlMatchLocNum;

    /** PRTL_MATCH_SF_ACCT_ID*/
	public final EZDFStringItem              prtlMatchSfAcctId;

    /** DUNS_MATCH_LOC_NUM*/
	public final EZDFStringItem              dunsMatchLocNum;

    /** DUNS_MATCH_SF_ACCT_ID*/
	public final EZDFStringItem              dunsMatchSfAcctId;

    /** GLN_MATCH_LOC_NUM*/
	public final EZDFStringItem              glnMatchLocNum;

    /** GLN_MATCH_SF_ACCT_ID*/
	public final EZDFStringItem              glnMatchSfAcctId;

    /** CANDI_TRTY_NM*/
	public final EZDFStringItem              candiTrtyNm;


	/**
	 * NMAL3200F01FMsg is constructor.
	 * The initialization when the instance of NMAL3200F01FMsg is generated.
	 */
	public NMAL3200F01FMsg() {
		this(false, -1);
	}

	/**
	 * NMAL3200F01FMsg is constructor.
	 * The initialization when the instance of NMAL3200F01FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL3200F01FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mktgMapRqstPk = (EZDFBigDecimalItem)newItem("mktgMapRqstPk");
		mktgFldMapNm = (EZDFStringItem)newItem("mktgFldMapNm");
		dsAcctNm = (EZDFStringItem)newItem("dsAcctNm");
		firstLineAddr = (EZDFStringItem)newItem("firstLineAddr");
		scdLineAddr = (EZDFStringItem)newItem("scdLineAddr");
		thirdLineAddr = (EZDFStringItem)newItem("thirdLineAddr");
		frthLineAddr = (EZDFStringItem)newItem("frthLineAddr");
		ctyAddr = (EZDFStringItem)newItem("ctyAddr");
		cntyPk = (EZDFBigDecimalItem)newItem("cntyPk");
		stCd = (EZDFStringItem)newItem("stCd");
		postCd = (EZDFStringItem)newItem("postCd");
		ctryCd = (EZDFStringItem)newItem("ctryCd");
		dunsNum = (EZDFStringItem)newItem("dunsNum");
		glnNum = (EZDFBigDecimalItem)newItem("glnNum");
		hinNum = (EZDFStringItem)newItem("hinNum");
		mktgMapAttrbTxt_01 = (EZDFStringItem)newItem("mktgMapAttrbTxt_01");
		mktgMapAttrbTxt_02 = (EZDFStringItem)newItem("mktgMapAttrbTxt_02");
		mktgMapAttrbTxt_03 = (EZDFStringItem)newItem("mktgMapAttrbTxt_03");
		mktgMapAttrbTxt_04 = (EZDFStringItem)newItem("mktgMapAttrbTxt_04");
		mktgMapAttrbTxt_05 = (EZDFStringItem)newItem("mktgMapAttrbTxt_05");
		mktgMapAttrbTxt_06 = (EZDFStringItem)newItem("mktgMapAttrbTxt_06");
		mktgMapAttrbTxt_07 = (EZDFStringItem)newItem("mktgMapAttrbTxt_07");
		mktgMapAttrbTxt_08 = (EZDFStringItem)newItem("mktgMapAttrbTxt_08");
		mktgMapAttrbTxt_09 = (EZDFStringItem)newItem("mktgMapAttrbTxt_09");
		mktgMapAttrbTxt_10 = (EZDFStringItem)newItem("mktgMapAttrbTxt_10");
		mktgMapAttrbTxt_11 = (EZDFStringItem)newItem("mktgMapAttrbTxt_11");
		mktgMapAttrbTxt_12 = (EZDFStringItem)newItem("mktgMapAttrbTxt_12");
		mktgMapAttrbTxt_13 = (EZDFStringItem)newItem("mktgMapAttrbTxt_13");
		mktgMapAttrbTxt_14 = (EZDFStringItem)newItem("mktgMapAttrbTxt_14");
		mktgMapAttrbTxt_15 = (EZDFStringItem)newItem("mktgMapAttrbTxt_15");
		mktgMapAttrbTxt_16 = (EZDFStringItem)newItem("mktgMapAttrbTxt_16");
		mktgMapAttrbTxt_17 = (EZDFStringItem)newItem("mktgMapAttrbTxt_17");
		mktgMapAttrbTxt_18 = (EZDFStringItem)newItem("mktgMapAttrbTxt_18");
		mktgMapAttrbTxt_19 = (EZDFStringItem)newItem("mktgMapAttrbTxt_19");
		mktgMapAttrbTxt_20 = (EZDFStringItem)newItem("mktgMapAttrbTxt_20");
		cratUsrId = (EZDFStringItem)newItem("cratUsrId");
		cratTs = (EZDFStringItem)newItem("cratTs");
		upldFileNm = (EZDFStringItem)newItem("upldFileNm");
		matchCritTpTxt = (EZDFStringItem)newItem("matchCritTpTxt");
		exactMatchLocNum = (EZDFStringItem)newItem("exactMatchLocNum");
		exactMatchSfAcctId = (EZDFStringItem)newItem("exactMatchSfAcctId");
		prtlMatchLocNum = (EZDFStringItem)newItem("prtlMatchLocNum");
		prtlMatchSfAcctId = (EZDFStringItem)newItem("prtlMatchSfAcctId");
		dunsMatchLocNum = (EZDFStringItem)newItem("dunsMatchLocNum");
		dunsMatchSfAcctId = (EZDFStringItem)newItem("dunsMatchSfAcctId");
		glnMatchLocNum = (EZDFStringItem)newItem("glnMatchLocNum");
		glnMatchSfAcctId = (EZDFStringItem)newItem("glnMatchSfAcctId");
		candiTrtyNm = (EZDFStringItem)newItem("candiTrtyNm");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL3200F01FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL3200F01FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mktgMapRqstPk", "mktgMapRqstPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mktgFldMapNm", "mktgFldMapNm", null, null, TYPE_HANKAKUEISU, "250", null},
	{"dsAcctNm", "dsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"firstLineAddr", "firstLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"scdLineAddr", "scdLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"thirdLineAddr", "thirdLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"frthLineAddr", "frthLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"ctyAddr", "ctyAddr", null, null, TYPE_HANKAKUEISU, "25", null},
	{"cntyPk", "cntyPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"stCd", "stCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"postCd", "postCd", null, null, TYPE_HANKAKUEISU, "15", null},
	{"ctryCd", "ctryCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"dunsNum", "dunsNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"glnNum", "glnNum", null, null, TYPE_SEISU_SYOSU, "13", "0"},
	{"hinNum", "hinNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"mktgMapAttrbTxt_01", "mktgMapAttrbTxt_01", "01", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_02", "mktgMapAttrbTxt_02", "02", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_03", "mktgMapAttrbTxt_03", "03", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_04", "mktgMapAttrbTxt_04", "04", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_05", "mktgMapAttrbTxt_05", "05", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_06", "mktgMapAttrbTxt_06", "06", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_07", "mktgMapAttrbTxt_07", "07", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_08", "mktgMapAttrbTxt_08", "08", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_09", "mktgMapAttrbTxt_09", "09", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_10", "mktgMapAttrbTxt_10", "10", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_11", "mktgMapAttrbTxt_11", "11", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_12", "mktgMapAttrbTxt_12", "12", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_13", "mktgMapAttrbTxt_13", "13", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_14", "mktgMapAttrbTxt_14", "14", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_15", "mktgMapAttrbTxt_15", "15", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_16", "mktgMapAttrbTxt_16", "16", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_17", "mktgMapAttrbTxt_17", "17", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_18", "mktgMapAttrbTxt_18", "18", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_19", "mktgMapAttrbTxt_19", "19", null, TYPE_HANKAKUEISU, "360", null},
	{"mktgMapAttrbTxt_20", "mktgMapAttrbTxt_20", "20", null, TYPE_HANKAKUEISU, "360", null},
	{"cratUsrId", "cratUsrId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"cratTs", "cratTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"upldFileNm", "upldFileNm", null, null, TYPE_HANKAKUEISU, "256", null},
	{"matchCritTpTxt", "matchCritTpTxt", null, null, TYPE_HANKAKUEISU, "100", null},
	{"exactMatchLocNum", "exactMatchLocNum", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"exactMatchSfAcctId", "exactMatchSfAcctId", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"prtlMatchLocNum", "prtlMatchLocNum", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"prtlMatchSfAcctId", "prtlMatchSfAcctId", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"dunsMatchLocNum", "dunsMatchLocNum", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"dunsMatchSfAcctId", "dunsMatchSfAcctId", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"glnMatchLocNum", "glnMatchLocNum", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"glnMatchSfAcctId", "glnMatchSfAcctId", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"candiTrtyNm", "candiTrtyNm", null, null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MKTG_MAP_RQST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapRqstPk
        {"MKTG_FLD_MAP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgFldMapNm
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr
        {"SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdLineAddr
        {"THIRD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdLineAddr
        {"FRTH_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthLineAddr
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr
        {"CNTY_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cntyPk
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd
        {"CTRY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctryCd
        {"DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsNum
        {"GLN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glnNum
        {"HIN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hinNum
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_01
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_02
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_03
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_04
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_05
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_06
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_07
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_08
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_09
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_10
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_11
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_12
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_13
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_14
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_15
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_16
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_17
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_18
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_19
        {"MKTG_MAP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktgMapAttrbTxt_20
        {"CRAT_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratUsrId
        {"CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratTs
        {"UPLD_FILE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldFileNm
        {"MATCH_CRIT_TP_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//matchCritTpTxt
        {"EXACT_MATCH_LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//exactMatchLocNum
        {"EXACT_MATCH_SF_ACCT_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//exactMatchSfAcctId
        {"PRTL_MATCH_LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prtlMatchLocNum
        {"PRTL_MATCH_SF_ACCT_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prtlMatchSfAcctId
        {"DUNS_MATCH_LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsMatchLocNum
        {"DUNS_MATCH_SF_ACCT_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsMatchSfAcctId
        {"GLN_MATCH_LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glnMatchLocNum
        {"GLN_MATCH_SF_ACCT_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glnMatchSfAcctId
        {"CANDI_TRTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//candiTrtyNm
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

