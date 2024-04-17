//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160701181742000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2840_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2840;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2840 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2840_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ACCT_NM_B*/
	public final EZDSStringItem              dsAcctNm_B;

    /** DS_ACCT_NUM_B*/
	public final EZDSStringItem              dsAcctNum_B;

    /** LOC_NUM_B*/
	public final EZDSStringItem              locNum_B;

    /** FIRST_LINE_ADDR_B*/
	public final EZDSStringItem              firstLineAddr_B;

    /** SCD_LINE_ADDR_B*/
	public final EZDSStringItem              scdLineAddr_B;

    /** CTY_ADDR_B*/
	public final EZDSStringItem              ctyAddr_B;

    /** ST_CD_B*/
	public final EZDSStringItem              stCd_B;

    /** POST_CD_1*/
	public final EZDSStringItem              postCd_1;

    /** DS_LOC_EMP_NUM_B*/
	public final EZDSBigDecimalItem              dsLocEmpNum_B;

    /** DS_LOC_REV_AMT_B*/
	public final EZDSBigDecimalItem              dsLocRevAmt_B;

    /** DS_CUST_SIC_DESC_TXT_1*/
	public final EZDSStringItem              dsCustSicDescTxt_1;

    /** DS_CUST_SIC_CD_1*/
	public final EZDSStringItem              dsCustSicCd_1;

    /** DUNS_NUM_1*/
	public final EZDSStringItem              dunsNum_1;

    /** DS_ULT_DUNS_NUM_B*/
	public final EZDSStringItem              dsUltDunsNum_B;

    /** HQ_DUNS_NUM_1*/
	public final EZDSStringItem              hqDunsNum_1;

    /** DS_PRNT_DUNS_NUM_B*/
	public final EZDSStringItem              dsPrntDunsNum_B;

    /** DUNS_PROC_TS_B*/
	public final EZDSStringItem              dunsProcTs_B;

    /** DUNS_BIZ_NM_1*/
	public final EZDSStringItem              dunsBizNm_1;

    /** DUNS_TRADE_STYLE_NM_1*/
	public final EZDSStringItem              dunsTradeStyleNm_1;

    /** DUNS_LINE_ADDR_1*/
	public final EZDSStringItem              dunsLineAddr_1;

    /** DUNS_ACTV_CD_1*/
	public final EZDSStringItem              dunsActvCd_1;

    /** DS_LAST_UPD_DUNS_DT_B*/
	public final EZDSDateItem              dsLastUpdDunsDt_B;

    /** DS_CUST_SIC_CD_2*/
	public final EZDSStringItem              dsCustSicCd_2;

    /** DS_CUST_SIC_DESC_TXT_2*/
	public final EZDSStringItem              dsCustSicDescTxt_2;

    /** DUNS_MATCH_CD_B*/
	public final EZDSStringItem              dunsMatchCd_B;

    /** NM_PRFL_CD_B*/
	public final EZDSStringItem              nmPrflCd_B;

    /** STR_NO_PRFL_CD_B*/
	public final EZDSStringItem              strNoPrflCd_B;

    /** STR_NM_PRFL_CD_B*/
	public final EZDSStringItem              strNmPrflCd_B;

    /** DUNS_CNFD_CD_B*/
	public final EZDSStringItem              dunsCnfdCd_B;

    /** MATCH_GRD_CD_B*/
	public final EZDSStringItem              matchGrdCd_B;

    /** NIXIE_A_CD_B*/
	public final EZDSStringItem              nixieACd_B;

    /** DUNS_ACTV_CD_2*/
	public final EZDSStringItem              dunsActvCd_2;

    /** DUNS_BIZ_NM_2*/
	public final EZDSStringItem              dunsBizNm_2;

    /** DUNS_TRADE_STYLE_NM_2*/
	public final EZDSStringItem              dunsTradeStyleNm_2;

    /** DUNS_LINE_ADDR_2*/
	public final EZDSStringItem              dunsLineAddr_2;

    /** DUNS_CTY_NM_B*/
	public final EZDSStringItem              dunsCtyNm_B;

    /** DUNS_ST_CD_B*/
	public final EZDSStringItem              dunsStCd_B;

    /** POST_CD_2*/
	public final EZDSStringItem              postCd_2;

    /** DUNS_NUM_2*/
	public final EZDSStringItem              dunsNum_2;

    /** EMP_TOT_NUM_B*/
	public final EZDSBigDecimalItem              empTotNum_B;

    /** ANN_SLS_AMT_B*/
	public final EZDSBigDecimalItem              annSlsAmt_B;

    /** LINE_BIZ_NM_B*/
	public final EZDSStringItem              lineBizNm_B;

    /** FIRST_SIC_CD_B*/
	public final EZDSStringItem              firstSicCd_B;

    /** GLBL_ULT_DUNS_NUM_B*/
	public final EZDSStringItem              glblUltDunsNum_B;

    /** HQ_DUNS_NUM_2*/
	public final EZDSStringItem              hqDunsNum_2;

    /** PRNT_DUNS_NUM_B*/
	public final EZDSStringItem              prntDunsNum_B;

    /** GLBL_ULT_BIZ_NM_B*/
	public final EZDSStringItem              glblUltBizNm_B;

    /** DUNS_CMPY_SIC_CD_B*/
	public final EZDSStringItem              dunsCmpySicCd_B;

    /** DUNS_CMPY_SIC_DESC_TXT_B*/
	public final EZDSStringItem              dunsCmpySicDescTxt_B;


	/**
	 * NMAL2840_BSMsg is constructor.
	 * The initialization when the instance of NMAL2840_BSMsg is generated.
	 */
	public NMAL2840_BSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2840_BSMsg is constructor.
	 * The initialization when the instance of NMAL2840_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2840_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsAcctNm_B = (EZDSStringItem)newItem("dsAcctNm_B");
		dsAcctNum_B = (EZDSStringItem)newItem("dsAcctNum_B");
		locNum_B = (EZDSStringItem)newItem("locNum_B");
		firstLineAddr_B = (EZDSStringItem)newItem("firstLineAddr_B");
		scdLineAddr_B = (EZDSStringItem)newItem("scdLineAddr_B");
		ctyAddr_B = (EZDSStringItem)newItem("ctyAddr_B");
		stCd_B = (EZDSStringItem)newItem("stCd_B");
		postCd_1 = (EZDSStringItem)newItem("postCd_1");
		dsLocEmpNum_B = (EZDSBigDecimalItem)newItem("dsLocEmpNum_B");
		dsLocRevAmt_B = (EZDSBigDecimalItem)newItem("dsLocRevAmt_B");
		dsCustSicDescTxt_1 = (EZDSStringItem)newItem("dsCustSicDescTxt_1");
		dsCustSicCd_1 = (EZDSStringItem)newItem("dsCustSicCd_1");
		dunsNum_1 = (EZDSStringItem)newItem("dunsNum_1");
		dsUltDunsNum_B = (EZDSStringItem)newItem("dsUltDunsNum_B");
		hqDunsNum_1 = (EZDSStringItem)newItem("hqDunsNum_1");
		dsPrntDunsNum_B = (EZDSStringItem)newItem("dsPrntDunsNum_B");
		dunsProcTs_B = (EZDSStringItem)newItem("dunsProcTs_B");
		dunsBizNm_1 = (EZDSStringItem)newItem("dunsBizNm_1");
		dunsTradeStyleNm_1 = (EZDSStringItem)newItem("dunsTradeStyleNm_1");
		dunsLineAddr_1 = (EZDSStringItem)newItem("dunsLineAddr_1");
		dunsActvCd_1 = (EZDSStringItem)newItem("dunsActvCd_1");
		dsLastUpdDunsDt_B = (EZDSDateItem)newItem("dsLastUpdDunsDt_B");
		dsCustSicCd_2 = (EZDSStringItem)newItem("dsCustSicCd_2");
		dsCustSicDescTxt_2 = (EZDSStringItem)newItem("dsCustSicDescTxt_2");
		dunsMatchCd_B = (EZDSStringItem)newItem("dunsMatchCd_B");
		nmPrflCd_B = (EZDSStringItem)newItem("nmPrflCd_B");
		strNoPrflCd_B = (EZDSStringItem)newItem("strNoPrflCd_B");
		strNmPrflCd_B = (EZDSStringItem)newItem("strNmPrflCd_B");
		dunsCnfdCd_B = (EZDSStringItem)newItem("dunsCnfdCd_B");
		matchGrdCd_B = (EZDSStringItem)newItem("matchGrdCd_B");
		nixieACd_B = (EZDSStringItem)newItem("nixieACd_B");
		dunsActvCd_2 = (EZDSStringItem)newItem("dunsActvCd_2");
		dunsBizNm_2 = (EZDSStringItem)newItem("dunsBizNm_2");
		dunsTradeStyleNm_2 = (EZDSStringItem)newItem("dunsTradeStyleNm_2");
		dunsLineAddr_2 = (EZDSStringItem)newItem("dunsLineAddr_2");
		dunsCtyNm_B = (EZDSStringItem)newItem("dunsCtyNm_B");
		dunsStCd_B = (EZDSStringItem)newItem("dunsStCd_B");
		postCd_2 = (EZDSStringItem)newItem("postCd_2");
		dunsNum_2 = (EZDSStringItem)newItem("dunsNum_2");
		empTotNum_B = (EZDSBigDecimalItem)newItem("empTotNum_B");
		annSlsAmt_B = (EZDSBigDecimalItem)newItem("annSlsAmt_B");
		lineBizNm_B = (EZDSStringItem)newItem("lineBizNm_B");
		firstSicCd_B = (EZDSStringItem)newItem("firstSicCd_B");
		glblUltDunsNum_B = (EZDSStringItem)newItem("glblUltDunsNum_B");
		hqDunsNum_2 = (EZDSStringItem)newItem("hqDunsNum_2");
		prntDunsNum_B = (EZDSStringItem)newItem("prntDunsNum_B");
		glblUltBizNm_B = (EZDSStringItem)newItem("glblUltBizNm_B");
		dunsCmpySicCd_B = (EZDSStringItem)newItem("dunsCmpySicCd_B");
		dunsCmpySicDescTxt_B = (EZDSStringItem)newItem("dunsCmpySicDescTxt_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2840_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2840_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsAcctNm_B", "dsAcctNm_B", "B", null, TYPE_HANKAKUEISU, "360", null},
	{"dsAcctNum_B", "dsAcctNum_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"locNum_B", "locNum_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"firstLineAddr_B", "firstLineAddr_B", "B", null, TYPE_HANKAKUEISU, "60", null},
	{"scdLineAddr_B", "scdLineAddr_B", "B", null, TYPE_HANKAKUEISU, "60", null},
	{"ctyAddr_B", "ctyAddr_B", "B", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_B", "stCd_B", "B", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_1", "postCd_1", "1", null, TYPE_HANKAKUEISU, "15", null},
	{"dsLocEmpNum_B", "dsLocEmpNum_B", "B", null, TYPE_SEISU_SYOSU, "8", "0"},
	{"dsLocRevAmt_B", "dsLocRevAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsCustSicDescTxt_1", "dsCustSicDescTxt_1", "1", null, TYPE_HANKAKUEISU, "100", null},
	{"dsCustSicCd_1", "dsCustSicCd_1", "1", null, TYPE_HANKAKUEISU, "30", null},
	{"dunsNum_1", "dunsNum_1", "1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsUltDunsNum_B", "dsUltDunsNum_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"hqDunsNum_1", "hqDunsNum_1", "1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsPrntDunsNum_B", "dsPrntDunsNum_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"dunsProcTs_B", "dunsProcTs_B", "B", null, TYPE_HANKAKUSUJI, "17", null},
	{"dunsBizNm_1", "dunsBizNm_1", "1", null, TYPE_HANKAKUEISU, "150", null},
	{"dunsTradeStyleNm_1", "dunsTradeStyleNm_1", "1", null, TYPE_HANKAKUEISU, "100", null},
	{"dunsLineAddr_1", "dunsLineAddr_1", "1", null, TYPE_HANKAKUEISU, "240", null},
	{"dunsActvCd_1", "dunsActvCd_1", "1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsLastUpdDunsDt_B", "dsLastUpdDunsDt_B", "B", null, TYPE_NENTSUKIHI, "8", null},
	{"dsCustSicCd_2", "dsCustSicCd_2", "2", null, TYPE_HANKAKUEISU, "30", null},
	{"dsCustSicDescTxt_2", "dsCustSicDescTxt_2", "2", null, TYPE_HANKAKUEISU, "100", null},
	{"dunsMatchCd_B", "dunsMatchCd_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"nmPrflCd_B", "nmPrflCd_B", "B", null, TYPE_HANKAKUEISU, "2", null},
	{"strNoPrflCd_B", "strNoPrflCd_B", "B", null, TYPE_HANKAKUEISU, "2", null},
	{"strNmPrflCd_B", "strNmPrflCd_B", "B", null, TYPE_HANKAKUEISU, "2", null},
	{"dunsCnfdCd_B", "dunsCnfdCd_B", "B", null, TYPE_HANKAKUEISU, "2", null},
	{"matchGrdCd_B", "matchGrdCd_B", "B", null, TYPE_HANKAKUEISU, "11", null},
	{"nixieACd_B", "nixieACd_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"dunsActvCd_2", "dunsActvCd_2", "2", null, TYPE_HANKAKUEISU, "50", null},
	{"dunsBizNm_2", "dunsBizNm_2", "2", null, TYPE_HANKAKUEISU, "150", null},
	{"dunsTradeStyleNm_2", "dunsTradeStyleNm_2", "2", null, TYPE_HANKAKUEISU, "100", null},
	{"dunsLineAddr_2", "dunsLineAddr_2", "2", null, TYPE_HANKAKUEISU, "240", null},
	{"dunsCtyNm_B", "dunsCtyNm_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"dunsStCd_B", "dunsStCd_B", "B", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_2", "postCd_2", "2", null, TYPE_HANKAKUEISU, "15", null},
	{"dunsNum_2", "dunsNum_2", "2", null, TYPE_HANKAKUEISU, "20", null},
	{"empTotNum_B", "empTotNum_B", "B", null, TYPE_SEISU_SYOSU, "9", "0"},
	{"annSlsAmt_B", "annSlsAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"lineBizNm_B", "lineBizNm_B", "B", null, TYPE_HANKAKUEISU, "19", null},
	{"firstSicCd_B", "firstSicCd_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"glblUltDunsNum_B", "glblUltDunsNum_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"hqDunsNum_2", "hqDunsNum_2", "2", null, TYPE_HANKAKUEISU, "20", null},
	{"prntDunsNum_B", "prntDunsNum_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"glblUltBizNm_B", "glblUltBizNm_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"dunsCmpySicCd_B", "dunsCmpySicCd_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"dunsCmpySicDescTxt_B", "dunsCmpySicDescTxt_B", "B", null, TYPE_HANKAKUEISU, "100", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_B
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_B
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_B
        {"FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr_B
        {"SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdLineAddr_B
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_B
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_B
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_1
        {"DS_LOC_EMP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsLocEmpNum_B
        {"DS_LOC_REV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsLocRevAmt_B
        {"DS_CUST_SIC_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustSicDescTxt_1
        {"DS_CUST_SIC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustSicCd_1
        {"DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsNum_1
        {"DS_ULT_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsUltDunsNum_B
        {"HQ_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hqDunsNum_1
        {"DS_PRNT_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPrntDunsNum_B
        {"DUNS_PROC_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsProcTs_B
        {"DUNS_BIZ_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsBizNm_1
        {"DUNS_TRADE_STYLE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsTradeStyleNm_1
        {"DUNS_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsLineAddr_1
        {"DUNS_ACTV_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsActvCd_1
        {"DS_LAST_UPD_DUNS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsLastUpdDunsDt_B
        {"DS_CUST_SIC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustSicCd_2
        {"DS_CUST_SIC_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustSicDescTxt_2
        {"DUNS_MATCH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsMatchCd_B
        {"NM_PRFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//nmPrflCd_B
        {"STR_NO_PRFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//strNoPrflCd_B
        {"STR_NM_PRFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//strNmPrflCd_B
        {"DUNS_CNFD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCnfdCd_B
        {"MATCH_GRD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//matchGrdCd_B
        {"NIXIE_A_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//nixieACd_B
        {"DUNS_ACTV_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsActvCd_2
        {"DUNS_BIZ_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsBizNm_2
        {"DUNS_TRADE_STYLE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsTradeStyleNm_2
        {"DUNS_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsLineAddr_2
        {"DUNS_CTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCtyNm_B
        {"DUNS_ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsStCd_B
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_2
        {"DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsNum_2
        {"EMP_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//empTotNum_B
        {"ANN_SLS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//annSlsAmt_B
        {"LINE_BIZ_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizNm_B
        {"FIRST_SIC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstSicCd_B
        {"GLBL_ULT_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblUltDunsNum_B
        {"HQ_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hqDunsNum_2
        {"PRNT_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntDunsNum_B
        {"GLBL_ULT_BIZ_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblUltBizNm_B
        {"DUNS_CMPY_SIC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCmpySicCd_B
        {"DUNS_CMPY_SIC_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsCmpySicDescTxt_B
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
