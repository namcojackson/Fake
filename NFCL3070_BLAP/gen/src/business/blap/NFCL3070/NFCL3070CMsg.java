//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180829111904000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3070CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3070;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL3070 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3070CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** XX_RADIO_BTN*/
	public final EZDCBigDecimalItem              xxRadioBtn;

    /** CUST_INCDT_ID*/
	public final EZDCStringItem              custIncdtId;

    /** XX_INV_TOT_AMT_CI*/
	public final EZDCBigDecimalItem              xxInvTotAmt_CI;

    /** ORIG_INV_NUM*/
	public final EZDCStringItem              origInvNum;

    /** ORIG_INV_AMT*/
	public final EZDCBigDecimalItem              origInvAmt;

    /** XX_LINK_PROT*/
	public final EZDCStringItem              xxLinkProt;

    /** AR_CR_REBIL_RSN_CD*/
	public final EZDCStringItem              arCrRebilRsnCd;

    /** AR_CR_REBIL_RSN_NM*/
	public final EZDCStringItem              arCrRebilRsnNm;

    /** AR_CR_REBIL_CMNT_TXT*/
	public final EZDCStringItem              arCrRebilCmntTxt;

    /** INV_NUM_C*/
	public final EZDCStringItem              invNum_C;

    /** CR_REBIL_AMT_C*/
	public final EZDCBigDecimalItem              crRebilAmt_C;

    /** AR_CR_REBIL_PK*/
	public final EZDCBigDecimalItem              arCrRebilPk;

    /** AR_CR_REBIL_PK_C*/
	public final EZDCBigDecimalItem              arCrRebilPk_C;

    /** INV_NUM_R*/
	public final EZDCStringItem              invNum_R;

    /** CR_REBIL_AMT_R*/
	public final EZDCBigDecimalItem              crRebilAmt_R;

    /** AR_CR_REBIL_ADDL_CMNT_TXT*/
	public final EZDCStringItem              arCrRebilAddlCmntTxt;

    /** INV_BOL_LINE_NUM*/
	public final EZDCStringItem              invBolLineNum;

    /** INV_LINE_NUM*/
	public final EZDCStringItem              invLineNum;

    /** AR_CR_TP_CD*/
	public final EZDCStringItem              arCrTpCd;

    /** AR_CR_TP_CD_CD*/
	public final EZDCStringItemArray              arCrTpCd_CD;

    /** AR_CR_TP_DESC_TXT_DI*/
	public final EZDCStringItemArray              arCrTpDescTxt_DI;

    /** AR_CR_PCT*/
	public final EZDCBigDecimalItem              arCrPct;

    /** CR_REBIL_AMT_CO*/
	public final EZDCBigDecimalItem              crRebilAmt_CO;

    /** CR_REBIL_AMT_CA*/
	public final EZDCBigDecimalItem              crRebilAmt_CA;

    /** CR_REBIL_AMT_TO*/
	public final EZDCBigDecimalItem              crRebilAmt_TO;

    /** AR_AUTO_CASH_APP_FLG*/
	public final EZDCStringItem              arAutoCashAppFlg;

    /** AR_CASH_APPLY_ELIG_FLG*/
	public final EZDCStringItem              arCashApplyEligFlg;

    /** ACTV_FLG*/
	public final EZDCStringItem              actvFlg;

    /** ACTV_FLG_E*/
	public final EZDCStringItem              actvFlg_E;

    /** ALLW_FLG*/
	public final EZDCStringItem              allwFlg;

    /** PRM_CMNT_TXT*/
	public final EZDCStringItem              prmCmntTxt;

    /** AR_CR_REBIL_RQST_USR_ID*/
	public final EZDCStringItem              arCrRebilRqstUsrId;

    /** AR_CR_REBIL_RQST_TS*/
	public final EZDCStringItem              arCrRebilRqstTs;

    /** AR_CR_REBIL_TP_CD*/
	public final EZDCStringItem              arCrRebilTpCd;

    /** FNLZ_INV_FLG*/
	public final EZDCStringItem              fnlzInvFlg;

    /** BILL_TO_CUST_CD*/
	public final EZDCStringItem              billToCustCd;

    /** SELL_TO_CUST_CD*/
	public final EZDCStringItem              sellToCustCd;

    /** SHIP_TO_CUST_CD*/
	public final EZDCStringItem              shipToCustCd;

    /** SHIP_TO_CUST_ACCT_CD*/
	public final EZDCStringItem              shipToCustAcctCd;

    /** BILL_TO_CUST_ACCT_CD*/
	public final EZDCStringItem              billToCustAcctCd;

    /** INV_DT*/
	public final EZDCDateItem              invDt;

    /** SLS_REP_TOC_CD*/
	public final EZDCStringItem              slsRepTocCd;

    /** LEASE_PRCH_OPT_CD*/
	public final EZDCStringItem              leasePrchOptCd;

    /** SHIP_TO_LOC_NM*/
	public final EZDCStringItem              shipToLocNm;

    /** DS_CONTR_NUM*/
	public final EZDCStringItem              dsContrNum;

    /** CR_DS_INV_TP_CD*/
	public final EZDCStringItem              crDsInvTpCd;

    /** FUNC_CCY_CD*/
	public final EZDCStringItem              funcCcyCd;

    /** AFT_DECL_PNT_DIGIT_NUM*/
	public final EZDCBigDecimalItem              aftDeclPntDigitNum;

    /** DS_BIZ_AREA_CD*/
	public final EZDCStringItem              dsBizAreaCd;

    /** DS_INV_TP_CD*/
	public final EZDCStringItem              dsInvTpCd;

    /** LOC_NM*/
	public final EZDCStringItem              locNm;

    /** DEAL_RMNG_BAL_GRS_AMT*/
	public final EZDCBigDecimalItem              dealRmngBalGrsAmt;

    /** XX_INV_TOT_CR_AMT*/
	public final EZDCBigDecimalItem              xxInvTotCrAmt;

    /** XX_TOT_INV_PCT*/
	public final EZDCBigDecimalItem              xxTotInvPct;

    /** XX_PG_FLG*/
	public final EZDCStringItem              xxPgFlg;

    /** XX_PG_FLG_P*/
	public final EZDCStringItem              xxPgFlg_P;

    /** XX_PG_FLG_C*/
	public final EZDCStringItem              xxPgFlg_C;

    /** XX_PG_FLG_T*/
	public final EZDCStringItem              xxPgFlg_T;

    /** SYS_SRC_CD*/
	public final EZDCStringItem              sysSrcCd;


	/**
	 * NFCL3070CMsg is constructor.
	 * The initialization when the instance of NFCL3070CMsg is generated.
	 */
	public NFCL3070CMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3070CMsg is constructor.
	 * The initialization when the instance of NFCL3070CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3070CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		xxRadioBtn = (EZDCBigDecimalItem)newItem("xxRadioBtn");
		custIncdtId = (EZDCStringItem)newItem("custIncdtId");
		xxInvTotAmt_CI = (EZDCBigDecimalItem)newItem("xxInvTotAmt_CI");
		origInvNum = (EZDCStringItem)newItem("origInvNum");
		origInvAmt = (EZDCBigDecimalItem)newItem("origInvAmt");
		xxLinkProt = (EZDCStringItem)newItem("xxLinkProt");
		arCrRebilRsnCd = (EZDCStringItem)newItem("arCrRebilRsnCd");
		arCrRebilRsnNm = (EZDCStringItem)newItem("arCrRebilRsnNm");
		arCrRebilCmntTxt = (EZDCStringItem)newItem("arCrRebilCmntTxt");
		invNum_C = (EZDCStringItem)newItem("invNum_C");
		crRebilAmt_C = (EZDCBigDecimalItem)newItem("crRebilAmt_C");
		arCrRebilPk = (EZDCBigDecimalItem)newItem("arCrRebilPk");
		arCrRebilPk_C = (EZDCBigDecimalItem)newItem("arCrRebilPk_C");
		invNum_R = (EZDCStringItem)newItem("invNum_R");
		crRebilAmt_R = (EZDCBigDecimalItem)newItem("crRebilAmt_R");
		arCrRebilAddlCmntTxt = (EZDCStringItem)newItem("arCrRebilAddlCmntTxt");
		invBolLineNum = (EZDCStringItem)newItem("invBolLineNum");
		invLineNum = (EZDCStringItem)newItem("invLineNum");
		arCrTpCd = (EZDCStringItem)newItem("arCrTpCd");
		arCrTpCd_CD = (EZDCStringItemArray)newItemArray("arCrTpCd_CD");
		arCrTpDescTxt_DI = (EZDCStringItemArray)newItemArray("arCrTpDescTxt_DI");
		arCrPct = (EZDCBigDecimalItem)newItem("arCrPct");
		crRebilAmt_CO = (EZDCBigDecimalItem)newItem("crRebilAmt_CO");
		crRebilAmt_CA = (EZDCBigDecimalItem)newItem("crRebilAmt_CA");
		crRebilAmt_TO = (EZDCBigDecimalItem)newItem("crRebilAmt_TO");
		arAutoCashAppFlg = (EZDCStringItem)newItem("arAutoCashAppFlg");
		arCashApplyEligFlg = (EZDCStringItem)newItem("arCashApplyEligFlg");
		actvFlg = (EZDCStringItem)newItem("actvFlg");
		actvFlg_E = (EZDCStringItem)newItem("actvFlg_E");
		allwFlg = (EZDCStringItem)newItem("allwFlg");
		prmCmntTxt = (EZDCStringItem)newItem("prmCmntTxt");
		arCrRebilRqstUsrId = (EZDCStringItem)newItem("arCrRebilRqstUsrId");
		arCrRebilRqstTs = (EZDCStringItem)newItem("arCrRebilRqstTs");
		arCrRebilTpCd = (EZDCStringItem)newItem("arCrRebilTpCd");
		fnlzInvFlg = (EZDCStringItem)newItem("fnlzInvFlg");
		billToCustCd = (EZDCStringItem)newItem("billToCustCd");
		sellToCustCd = (EZDCStringItem)newItem("sellToCustCd");
		shipToCustCd = (EZDCStringItem)newItem("shipToCustCd");
		shipToCustAcctCd = (EZDCStringItem)newItem("shipToCustAcctCd");
		billToCustAcctCd = (EZDCStringItem)newItem("billToCustAcctCd");
		invDt = (EZDCDateItem)newItem("invDt");
		slsRepTocCd = (EZDCStringItem)newItem("slsRepTocCd");
		leasePrchOptCd = (EZDCStringItem)newItem("leasePrchOptCd");
		shipToLocNm = (EZDCStringItem)newItem("shipToLocNm");
		dsContrNum = (EZDCStringItem)newItem("dsContrNum");
		crDsInvTpCd = (EZDCStringItem)newItem("crDsInvTpCd");
		funcCcyCd = (EZDCStringItem)newItem("funcCcyCd");
		aftDeclPntDigitNum = (EZDCBigDecimalItem)newItem("aftDeclPntDigitNum");
		dsBizAreaCd = (EZDCStringItem)newItem("dsBizAreaCd");
		dsInvTpCd = (EZDCStringItem)newItem("dsInvTpCd");
		locNm = (EZDCStringItem)newItem("locNm");
		dealRmngBalGrsAmt = (EZDCBigDecimalItem)newItem("dealRmngBalGrsAmt");
		xxInvTotCrAmt = (EZDCBigDecimalItem)newItem("xxInvTotCrAmt");
		xxTotInvPct = (EZDCBigDecimalItem)newItem("xxTotInvPct");
		xxPgFlg = (EZDCStringItem)newItem("xxPgFlg");
		xxPgFlg_P = (EZDCStringItem)newItem("xxPgFlg_P");
		xxPgFlg_C = (EZDCStringItem)newItem("xxPgFlg_C");
		xxPgFlg_T = (EZDCStringItem)newItem("xxPgFlg_T");
		sysSrcCd = (EZDCStringItem)newItem("sysSrcCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3070CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3070CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxRadioBtn", "xxRadioBtn", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"custIncdtId", "custIncdtId", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxInvTotAmt_CI", "xxInvTotAmt_CI", "CI", null, TYPE_SEISU_SYOSU, "13", "2"},
	{"origInvNum", "origInvNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"origInvAmt", "origInvAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxLinkProt", "xxLinkProt", null, null, TYPE_HANKAKUEISU, "1", null},
	{"arCrRebilRsnCd", "arCrRebilRsnCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"arCrRebilRsnNm", "arCrRebilRsnNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"arCrRebilCmntTxt", "arCrRebilCmntTxt", null, null, TYPE_HANKAKUEISU, "200", null},
	{"invNum_C", "invNum_C", "C", null, TYPE_HANKAKUEISU, "13", null},
	{"crRebilAmt_C", "crRebilAmt_C", "C", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"arCrRebilPk", "arCrRebilPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"arCrRebilPk_C", "arCrRebilPk_C", "C", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"invNum_R", "invNum_R", "R", null, TYPE_HANKAKUEISU, "13", null},
	{"crRebilAmt_R", "crRebilAmt_R", "R", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"arCrRebilAddlCmntTxt", "arCrRebilAddlCmntTxt", null, null, TYPE_HANKAKUEISU, "200", null},
	{"invBolLineNum", "invBolLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"invLineNum", "invLineNum", null, null, TYPE_HANKAKUEISU, "5", null},
	{"arCrTpCd", "arCrTpCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"arCrTpCd_CD", "arCrTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "20", null},
	{"arCrTpDescTxt_DI", "arCrTpDescTxt_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
	{"arCrPct", "arCrPct", null, null, TYPE_SEISU_SYOSU, "5", "2"},
	{"crRebilAmt_CO", "crRebilAmt_CO", "CO", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"crRebilAmt_CA", "crRebilAmt_CA", "CA", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"crRebilAmt_TO", "crRebilAmt_TO", "TO", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"arAutoCashAppFlg", "arAutoCashAppFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"arCashApplyEligFlg", "arCashApplyEligFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"actvFlg", "actvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"actvFlg_E", "actvFlg_E", "E", null, TYPE_HANKAKUEISU, "1", null},
	{"allwFlg", "allwFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"prmCmntTxt", "prmCmntTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"arCrRebilRqstUsrId", "arCrRebilRqstUsrId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"arCrRebilRqstTs", "arCrRebilRqstTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"arCrRebilTpCd", "arCrRebilTpCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"fnlzInvFlg", "fnlzInvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"billToCustCd", "billToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"sellToCustCd", "sellToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd", "shipToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustAcctCd", "shipToCustAcctCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustAcctCd", "billToCustAcctCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"invDt", "invDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"slsRepTocCd", "slsRepTocCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"leasePrchOptCd", "leasePrchOptCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"shipToLocNm", "shipToLocNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"crDsInvTpCd", "crDsInvTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"funcCcyCd", "funcCcyCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"aftDeclPntDigitNum", "aftDeclPntDigitNum", null, null, TYPE_SEISU_SYOSU, "1", "0"},
	{"dsBizAreaCd", "dsBizAreaCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsInvTpCd", "dsInvTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"locNm", "locNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"dealRmngBalGrsAmt", "dealRmngBalGrsAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxInvTotCrAmt", "xxInvTotCrAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotInvPct", "xxTotInvPct", null, null, TYPE_SEISU_SYOSU, "5", "2"},
	{"xxPgFlg", "xxPgFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxPgFlg_P", "xxPgFlg_P", "P", null, TYPE_HANKAKUEISU, "1", null},
	{"xxPgFlg_C", "xxPgFlg_C", "C", null, TYPE_HANKAKUEISU, "1", null},
	{"xxPgFlg_T", "xxPgFlg_T", "T", null, TYPE_HANKAKUEISU, "1", null},
	{"sysSrcCd", "sysSrcCd", null, null, TYPE_HANKAKUEISU, "3", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn
        {"CUST_INCDT_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIncdtId
        {"XX_INV_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInvTotAmt_CI
        {"ORIG_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origInvNum
        {"ORIG_INV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origInvAmt
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt
        {"AR_CR_REBIL_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCrRebilRsnCd
        {"AR_CR_REBIL_RSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCrRebilRsnNm
        {"AR_CR_REBIL_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCrRebilCmntTxt
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_C
        {"CR_REBIL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilAmt_C
        {"AR_CR_REBIL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCrRebilPk
        {"AR_CR_REBIL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCrRebilPk_C
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_R
        {"CR_REBIL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilAmt_R
        {"AR_CR_REBIL_ADDL_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCrRebilAddlCmntTxt
        {"INV_BOL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invBolLineNum
        {"INV_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineNum
        {"AR_CR_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCrTpCd
        {"AR_CR_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCrTpCd_CD
        {"AR_CR_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCrTpDescTxt_DI
        {"AR_CR_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCrPct
        {"CR_REBIL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilAmt_CO
        {"CR_REBIL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilAmt_CA
        {"CR_REBIL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilAmt_TO
        {"AR_AUTO_CASH_APP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAutoCashAppFlg
        {"AR_CASH_APPLY_ELIG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCashApplyEligFlg
        {"ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg
        {"ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_E
        {"ALLW_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//allwFlg
        {"PRM_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prmCmntTxt
        {"AR_CR_REBIL_RQST_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCrRebilRqstUsrId
        {"AR_CR_REBIL_RQST_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCrRebilRqstTs
        {"AR_CR_REBIL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCrRebilTpCd
        {"FNLZ_INV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fnlzInvFlg
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd
        {"SHIP_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustAcctCd
        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt
        {"SLS_REP_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd
        {"LEASE_PRCH_OPT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leasePrchOptCd
        {"SHIP_TO_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToLocNm
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"CR_DS_INV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crDsInvTpCd
        {"FUNC_CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcCcyCd
        {"AFT_DECL_PNT_DIGIT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDeclPntDigitNum
        {"DS_BIZ_AREA_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBizAreaCd
        {"DS_INV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsInvTpCd
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm
        {"DEAL_RMNG_BAL_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRmngBalGrsAmt
        {"XX_INV_TOT_CR_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInvTotCrAmt
        {"XX_TOT_INV_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotInvPct
        {"XX_PG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPgFlg
        {"XX_PG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPgFlg_P
        {"XX_PG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPgFlg_C
        {"XX_PG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPgFlg_T
        {"SYS_SRC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysSrcCd
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
