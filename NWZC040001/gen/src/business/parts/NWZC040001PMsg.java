//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20170406115047000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC040001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC040001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC040001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** INV_NUM*/
	public final EZDPStringItem              invNum;

    /** ORIG_INV_NUM*/
	public final EZDPStringItem              origInvNum;

    /** GRP_INV_NUM*/
	public final EZDPStringItem              grpInvNum;

    /** INV_DT*/
	public final EZDPDateItem              invDt;

    /** ACCT_DT*/
	public final EZDPDateItem              acctDt;

    /** INV_TP_CD*/
	public final EZDPStringItem              invTpCd;

    /** NET_DUE_DT*/
	public final EZDPDateItem              netDueDt;

    /** CPO_ORD_NUM*/
	public final EZDPStringItem              cpoOrdNum;

    /** ORD_DT*/
	public final EZDPDateItem              ordDt;

    /** CUST_ISS_PO_NUM*/
	public final EZDPStringItem              custIssPoNum;

    /** CUST_ISS_PO_DT*/
	public final EZDPDateItem              custIssPoDt;

    /** BILL_TO_CUST_CD*/
	public final EZDPStringItem              billToCustCd;

    /** SELL_TO_CUST_CD*/
	public final EZDPStringItem              sellToCustCd;

    /** SELL_TO_LOC_NM*/
	public final EZDPStringItem              sellToLocNm;

    /** SELL_TO_ADDL_LOC_NM*/
	public final EZDPStringItem              sellToAddlLocNm;

    /** SELL_TO_FIRST_LINE_ADDR*/
	public final EZDPStringItem              sellToFirstLineAddr;

    /** SELL_TO_SCD_LINE_ADDR*/
	public final EZDPStringItem              sellToScdLineAddr;

    /** SELL_TO_THIRD_LINE_ADDR*/
	public final EZDPStringItem              sellToThirdLineAddr;

    /** SELL_TO_FRTH_LINE_ADDR*/
	public final EZDPStringItem              sellToFrthLineAddr;

    /** SELL_TO_CTY_ADDR*/
	public final EZDPStringItem              sellToCtyAddr;

    /** SELL_TO_PROV_NM*/
	public final EZDPStringItem              sellToProvNm;

    /** SELL_TO_CNTY_NM*/
	public final EZDPStringItem              sellToCntyNm;

    /** SELL_TO_ST_CD*/
	public final EZDPStringItem              sellToStCd;

    /** SELL_TO_POST_CD*/
	public final EZDPStringItem              sellToPostCd;

    /** SELL_TO_CTRY_CD*/
	public final EZDPStringItem              sellToCtryCd;

    /** SELL_TO_FIRST_REF_CMNT_TXT*/
	public final EZDPStringItem              sellToFirstRefCmntTxt;

    /** SELL_TO_SCD_REF_CMNT_TXT*/
	public final EZDPStringItem              sellToScdRefCmntTxt;

    /** PAYER_CUST_CD*/
	public final EZDPStringItem              payerCustCd;

    /** ADV_RCPT_NUM*/
	public final EZDPStringItem              advRcptNum;

    /** PMT_TERM_START_DT*/
	public final EZDPDateItem              pmtTermStartDt;

    /** INV_PMT_METH_CD*/
	public final EZDPStringItem              invPmtMethCd;

    /** INV_PMT_COND_CD*/
	public final EZDPStringItem              invPmtCondCd;

    /** PMT_TERM_CD*/
	public final EZDPStringItem              pmtTermCd;

    /** PMT_TERM_NM*/
	public final EZDPStringItem              pmtTermNm;

    /** CASH_DISC_TERM_CD*/
	public final EZDPStringItem              cashDiscTermCd;

    /** SLS_ADMIN_PSN_CD*/
	public final EZDPStringItem              slsAdminPsnCd;

    /** INV_TOT_DEAL_NET_AMT*/
	public final EZDPBigDecimalItem              invTotDealNetAmt;

    /** INV_TOT_DEAL_SLS_AMT*/
	public final EZDPBigDecimalItem              invTotDealSlsAmt;

    /** INV_TOT_DEAL_FRT_AMT*/
	public final EZDPBigDecimalItem              invTotDealFrtAmt;

    /** INV_TOT_DEAL_TAX_AMT*/
	public final EZDPBigDecimalItem              invTotDealTaxAmt;

    /** INV_TOT_DEAL_DISC_AMT*/
	public final EZDPBigDecimalItem              invTotDealDiscAmt;

    /** INV_TOT_DEAL_INS_AMT*/
	public final EZDPBigDecimalItem              invTotDealInsAmt;

    /** INV_TOT_FUNC_INS_AMT*/
	public final EZDPBigDecimalItem              invTotFuncInsAmt;

    /** INV_FIRST_CMNT_TXT*/
	public final EZDPStringItem              invFirstCmntTxt;

    /** INV_SCD_CMNT_TXT*/
	public final EZDPStringItem              invScdCmntTxt;

    /** INV_THIRD_CMNT_TXT*/
	public final EZDPStringItem              invThirdCmntTxt;

    /** INV_FRTH_CMNT_TXT*/
	public final EZDPStringItem              invFrthCmntTxt;

    /** DEAL_CCY_CD*/
	public final EZDPStringItem              dealCcyCd;

    /** ACTL_APPLY_EXCH_RATE*/
	public final EZDPBigDecimalItem              actlApplyExchRate;

    /** FL_PLN_FLG*/
	public final EZDPStringItem              flPlnFlg;

    /** INV_PRINT_STS_CD*/
	public final EZDPStringItem              invPrintStsCd;

    /** TRX_SRC_TP_CD*/
	public final EZDPStringItem              trxSrcTpCd;

    /** INV_ML_SEND_STS_CD*/
	public final EZDPStringItem              invMlSendStsCd;

    /** INV_EDI_SEND_STS_CD*/
	public final EZDPStringItem              invEdiSendStsCd;

    /** INV_FAX_SEND_STS_CD*/
	public final EZDPStringItem              invFaxSendStsCd;

    /** INV_EML_SEND_STS_CD*/
	public final EZDPStringItem              invEmlSendStsCd;

    /** AUTH_CD*/
	public final EZDPStringItem              authCd;

    /** CR_DR_RSN_CD*/
	public final EZDPStringItem              crDrRsnCd;

    /** CR_DR_RSN_SUB_CD*/
	public final EZDPStringItem              crDrRsnSubCd;

    /** SYS_SRC_CD*/
	public final EZDPStringItem              sysSrcCd;

    /** ROSS_ORD_TP_CD*/
	public final EZDPStringItem              rossOrdTpCd;

    /** PMT_TERM_CASH_DISC_CD*/
	public final EZDPStringItem              pmtTermCashDiscCd;

    /** CR_CARD_ORD_NUM*/
	public final EZDPStringItem              crCardOrdNum;

    /** HIST_CRAT_CPLT_FLG*/
	public final EZDPStringItem              histCratCpltFlg;

    /** DS_ORD_TP_CD*/
	public final EZDPStringItem              dsOrdTpCd;

    /** DS_ORD_RSN_CD*/
	public final EZDPStringItem              dsOrdRsnCd;

    /** INV_RCPNT_CUST_CD*/
	public final EZDPStringItem              invRcpntCustCd;

    /** CR_CARD_CHRG_CPLT_CD*/
	public final EZDPStringItem              crCardChrgCpltCd;

    /** CR_CARD_CUST_REF_NUM*/
	public final EZDPStringItem              crCardCustRefNum;

    /** CR_CARD_AUTH_REF_NUM*/
	public final EZDPStringItem              crCardAuthRefNum;

    /** CR_CARD_AUTH_DT*/
	public final EZDPDateItem              crCardAuthDt;

    /** CR_CARD_TP_CD*/
	public final EZDPStringItem              crCardTpCd;

    /** DS_INV_EXPR_DT*/
	public final EZDPDateItem              dsInvExprDt;

    /** EASY_PACK_RATE*/
	public final EZDPBigDecimalItem              easyPackRate;

    /** EASY_PACK_MTH_QUOT_SUM_QTY*/
	public final EZDPBigDecimalItem              easyPackMthQuotSumQty;

    /** DS_INV_TP_CD*/
	public final EZDPStringItem              dsInvTpCd;

    /** SRC_SYS_DOC_NUM*/
	public final EZDPStringItem              srcSysDocNum;

    /** SLS_REP_TOC_CD*/
	public final EZDPStringItem              slsRepTocCd;

    /** ITRL_INV_ERR_FLG*/
	public final EZDPStringItem              itrlInvErrFlg;

    /** CUST_CARE_TKT_NUM*/
	public final EZDPStringItem              custCareTktNum;

    /** BILL_TO_CUST_ACCT_CD*/
	public final EZDPStringItem              billToCustAcctCd;

    /** SVC_INV_PK*/
	public final EZDPBigDecimalItem              svcInvPk;

    /** DS_CONTR_CATG_CD*/
	public final EZDPStringItem              dsContrCatgCd;

    /** SOLD_TO_CUST_LOC_CD*/
	public final EZDPStringItem              soldToCustLocCd;

    /** LINE_BIZ_TP_CD*/
	public final EZDPStringItem              lineBizTpCd;

    /** DS_BIZ_AREA_CD*/
	public final EZDPStringItem              dsBizAreaCd;

    /** DPLY_MDSE_DTL_FLG*/
	public final EZDPStringItem              dplyMdseDtlFlg;

    /** BILL_TO_CTAC_PSN_FIRST_NM*/
	public final EZDPStringItem              billToCtacPsnFirstNm;

    /** BILL_TO_CTAC_PSN_MID_NM*/
	public final EZDPStringItem              billToCtacPsnMidNm;

    /** BILL_TO_CTAC_PSN_LAST_NM*/
	public final EZDPStringItem              billToCtacPsnLastNm;

    /** INV_PRT_BAT_TP_CD*/
	public final EZDPStringItem              invPrtBatTpCd;

    /** CONTR_RNW_TOT_CNT*/
	public final EZDPBigDecimalItem              contrRnwTotCnt;

    /** DS_ORD_CATG_CD*/
	public final EZDPStringItem              dsOrdCatgCd;

    /** CSMP_INV_CLM_STS_CD*/
	public final EZDPStringItem              csmpInvClmStsCd;

    /** CSMP_ORIG_INV_NUM*/
	public final EZDPStringItem              csmpOrigInvNum;

    /** CR_REBIL_RSN_CATG_CD*/
	public final EZDPStringItem              crRebilRsnCatgCd;

    /** xxMsgIdList*/
	public final business.parts.NWZC040001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NWZC040001PMsg is constructor.
	 * The initialization when the instance of NWZC040001PMsg is generated.
	 */
	public NWZC040001PMsg() {
		this(false, -1);
	}

	/**
	 * NWZC040001PMsg is constructor.
	 * The initialization when the instance of NWZC040001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC040001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		invNum = (EZDPStringItem)newItem("invNum");
		origInvNum = (EZDPStringItem)newItem("origInvNum");
		grpInvNum = (EZDPStringItem)newItem("grpInvNum");
		invDt = (EZDPDateItem)newItem("invDt");
		acctDt = (EZDPDateItem)newItem("acctDt");
		invTpCd = (EZDPStringItem)newItem("invTpCd");
		netDueDt = (EZDPDateItem)newItem("netDueDt");
		cpoOrdNum = (EZDPStringItem)newItem("cpoOrdNum");
		ordDt = (EZDPDateItem)newItem("ordDt");
		custIssPoNum = (EZDPStringItem)newItem("custIssPoNum");
		custIssPoDt = (EZDPDateItem)newItem("custIssPoDt");
		billToCustCd = (EZDPStringItem)newItem("billToCustCd");
		sellToCustCd = (EZDPStringItem)newItem("sellToCustCd");
		sellToLocNm = (EZDPStringItem)newItem("sellToLocNm");
		sellToAddlLocNm = (EZDPStringItem)newItem("sellToAddlLocNm");
		sellToFirstLineAddr = (EZDPStringItem)newItem("sellToFirstLineAddr");
		sellToScdLineAddr = (EZDPStringItem)newItem("sellToScdLineAddr");
		sellToThirdLineAddr = (EZDPStringItem)newItem("sellToThirdLineAddr");
		sellToFrthLineAddr = (EZDPStringItem)newItem("sellToFrthLineAddr");
		sellToCtyAddr = (EZDPStringItem)newItem("sellToCtyAddr");
		sellToProvNm = (EZDPStringItem)newItem("sellToProvNm");
		sellToCntyNm = (EZDPStringItem)newItem("sellToCntyNm");
		sellToStCd = (EZDPStringItem)newItem("sellToStCd");
		sellToPostCd = (EZDPStringItem)newItem("sellToPostCd");
		sellToCtryCd = (EZDPStringItem)newItem("sellToCtryCd");
		sellToFirstRefCmntTxt = (EZDPStringItem)newItem("sellToFirstRefCmntTxt");
		sellToScdRefCmntTxt = (EZDPStringItem)newItem("sellToScdRefCmntTxt");
		payerCustCd = (EZDPStringItem)newItem("payerCustCd");
		advRcptNum = (EZDPStringItem)newItem("advRcptNum");
		pmtTermStartDt = (EZDPDateItem)newItem("pmtTermStartDt");
		invPmtMethCd = (EZDPStringItem)newItem("invPmtMethCd");
		invPmtCondCd = (EZDPStringItem)newItem("invPmtCondCd");
		pmtTermCd = (EZDPStringItem)newItem("pmtTermCd");
		pmtTermNm = (EZDPStringItem)newItem("pmtTermNm");
		cashDiscTermCd = (EZDPStringItem)newItem("cashDiscTermCd");
		slsAdminPsnCd = (EZDPStringItem)newItem("slsAdminPsnCd");
		invTotDealNetAmt = (EZDPBigDecimalItem)newItem("invTotDealNetAmt");
		invTotDealSlsAmt = (EZDPBigDecimalItem)newItem("invTotDealSlsAmt");
		invTotDealFrtAmt = (EZDPBigDecimalItem)newItem("invTotDealFrtAmt");
		invTotDealTaxAmt = (EZDPBigDecimalItem)newItem("invTotDealTaxAmt");
		invTotDealDiscAmt = (EZDPBigDecimalItem)newItem("invTotDealDiscAmt");
		invTotDealInsAmt = (EZDPBigDecimalItem)newItem("invTotDealInsAmt");
		invTotFuncInsAmt = (EZDPBigDecimalItem)newItem("invTotFuncInsAmt");
		invFirstCmntTxt = (EZDPStringItem)newItem("invFirstCmntTxt");
		invScdCmntTxt = (EZDPStringItem)newItem("invScdCmntTxt");
		invThirdCmntTxt = (EZDPStringItem)newItem("invThirdCmntTxt");
		invFrthCmntTxt = (EZDPStringItem)newItem("invFrthCmntTxt");
		dealCcyCd = (EZDPStringItem)newItem("dealCcyCd");
		actlApplyExchRate = (EZDPBigDecimalItem)newItem("actlApplyExchRate");
		flPlnFlg = (EZDPStringItem)newItem("flPlnFlg");
		invPrintStsCd = (EZDPStringItem)newItem("invPrintStsCd");
		trxSrcTpCd = (EZDPStringItem)newItem("trxSrcTpCd");
		invMlSendStsCd = (EZDPStringItem)newItem("invMlSendStsCd");
		invEdiSendStsCd = (EZDPStringItem)newItem("invEdiSendStsCd");
		invFaxSendStsCd = (EZDPStringItem)newItem("invFaxSendStsCd");
		invEmlSendStsCd = (EZDPStringItem)newItem("invEmlSendStsCd");
		authCd = (EZDPStringItem)newItem("authCd");
		crDrRsnCd = (EZDPStringItem)newItem("crDrRsnCd");
		crDrRsnSubCd = (EZDPStringItem)newItem("crDrRsnSubCd");
		sysSrcCd = (EZDPStringItem)newItem("sysSrcCd");
		rossOrdTpCd = (EZDPStringItem)newItem("rossOrdTpCd");
		pmtTermCashDiscCd = (EZDPStringItem)newItem("pmtTermCashDiscCd");
		crCardOrdNum = (EZDPStringItem)newItem("crCardOrdNum");
		histCratCpltFlg = (EZDPStringItem)newItem("histCratCpltFlg");
		dsOrdTpCd = (EZDPStringItem)newItem("dsOrdTpCd");
		dsOrdRsnCd = (EZDPStringItem)newItem("dsOrdRsnCd");
		invRcpntCustCd = (EZDPStringItem)newItem("invRcpntCustCd");
		crCardChrgCpltCd = (EZDPStringItem)newItem("crCardChrgCpltCd");
		crCardCustRefNum = (EZDPStringItem)newItem("crCardCustRefNum");
		crCardAuthRefNum = (EZDPStringItem)newItem("crCardAuthRefNum");
		crCardAuthDt = (EZDPDateItem)newItem("crCardAuthDt");
		crCardTpCd = (EZDPStringItem)newItem("crCardTpCd");
		dsInvExprDt = (EZDPDateItem)newItem("dsInvExprDt");
		easyPackRate = (EZDPBigDecimalItem)newItem("easyPackRate");
		easyPackMthQuotSumQty = (EZDPBigDecimalItem)newItem("easyPackMthQuotSumQty");
		dsInvTpCd = (EZDPStringItem)newItem("dsInvTpCd");
		srcSysDocNum = (EZDPStringItem)newItem("srcSysDocNum");
		slsRepTocCd = (EZDPStringItem)newItem("slsRepTocCd");
		itrlInvErrFlg = (EZDPStringItem)newItem("itrlInvErrFlg");
		custCareTktNum = (EZDPStringItem)newItem("custCareTktNum");
		billToCustAcctCd = (EZDPStringItem)newItem("billToCustAcctCd");
		svcInvPk = (EZDPBigDecimalItem)newItem("svcInvPk");
		dsContrCatgCd = (EZDPStringItem)newItem("dsContrCatgCd");
		soldToCustLocCd = (EZDPStringItem)newItem("soldToCustLocCd");
		lineBizTpCd = (EZDPStringItem)newItem("lineBizTpCd");
		dsBizAreaCd = (EZDPStringItem)newItem("dsBizAreaCd");
		dplyMdseDtlFlg = (EZDPStringItem)newItem("dplyMdseDtlFlg");
		billToCtacPsnFirstNm = (EZDPStringItem)newItem("billToCtacPsnFirstNm");
		billToCtacPsnMidNm = (EZDPStringItem)newItem("billToCtacPsnMidNm");
		billToCtacPsnLastNm = (EZDPStringItem)newItem("billToCtacPsnLastNm");
		invPrtBatTpCd = (EZDPStringItem)newItem("invPrtBatTpCd");
		contrRnwTotCnt = (EZDPBigDecimalItem)newItem("contrRnwTotCnt");
		dsOrdCatgCd = (EZDPStringItem)newItem("dsOrdCatgCd");
		csmpInvClmStsCd = (EZDPStringItem)newItem("csmpInvClmStsCd");
		csmpOrigInvNum = (EZDPStringItem)newItem("csmpOrigInvNum");
		crRebilRsnCatgCd = (EZDPStringItem)newItem("crRebilRsnCatgCd");
		xxMsgIdList = (business.parts.NWZC040001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC040001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC040001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"invNum", "invNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"origInvNum", "origInvNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"grpInvNum", "grpInvNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"invDt", "invDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"acctDt", "acctDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"invTpCd", "invTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"netDueDt", "netDueDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"ordDt", "ordDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"custIssPoNum", "custIssPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"custIssPoDt", "custIssPoDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"billToCustCd", "billToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"sellToCustCd", "sellToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"sellToLocNm", "sellToLocNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"sellToAddlLocNm", "sellToAddlLocNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"sellToFirstLineAddr", "sellToFirstLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"sellToScdLineAddr", "sellToScdLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"sellToThirdLineAddr", "sellToThirdLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"sellToFrthLineAddr", "sellToFrthLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"sellToCtyAddr", "sellToCtyAddr", null, null, TYPE_HANKAKUEISU, "25", null},
	{"sellToProvNm", "sellToProvNm", null, null, TYPE_HANKAKUEISU, "25", null},
	{"sellToCntyNm", "sellToCntyNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"sellToStCd", "sellToStCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"sellToPostCd", "sellToPostCd", null, null, TYPE_HANKAKUEISU, "15", null},
	{"sellToCtryCd", "sellToCtryCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"sellToFirstRefCmntTxt", "sellToFirstRefCmntTxt", null, null, TYPE_HANKAKUEISU, "90", null},
	{"sellToScdRefCmntTxt", "sellToScdRefCmntTxt", null, null, TYPE_HANKAKUEISU, "90", null},
	{"payerCustCd", "payerCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"advRcptNum", "advRcptNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"pmtTermStartDt", "pmtTermStartDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"invPmtMethCd", "invPmtMethCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"invPmtCondCd", "invPmtCondCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"pmtTermCd", "pmtTermCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"pmtTermNm", "pmtTermNm", null, null, TYPE_HANKAKUEISU, "20", null},
	{"cashDiscTermCd", "cashDiscTermCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"slsAdminPsnCd", "slsAdminPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"invTotDealNetAmt", "invTotDealNetAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invTotDealSlsAmt", "invTotDealSlsAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invTotDealFrtAmt", "invTotDealFrtAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invTotDealTaxAmt", "invTotDealTaxAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invTotDealDiscAmt", "invTotDealDiscAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invTotDealInsAmt", "invTotDealInsAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invTotFuncInsAmt", "invTotFuncInsAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invFirstCmntTxt", "invFirstCmntTxt", null, null, TYPE_HANKAKUEISU, "65", null},
	{"invScdCmntTxt", "invScdCmntTxt", null, null, TYPE_HANKAKUEISU, "65", null},
	{"invThirdCmntTxt", "invThirdCmntTxt", null, null, TYPE_HANKAKUEISU, "65", null},
	{"invFrthCmntTxt", "invFrthCmntTxt", null, null, TYPE_HANKAKUEISU, "65", null},
	{"dealCcyCd", "dealCcyCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"actlApplyExchRate", "actlApplyExchRate", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"flPlnFlg", "flPlnFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"invPrintStsCd", "invPrintStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"trxSrcTpCd", "trxSrcTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"invMlSendStsCd", "invMlSendStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"invEdiSendStsCd", "invEdiSendStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"invFaxSendStsCd", "invFaxSendStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"invEmlSendStsCd", "invEmlSendStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"authCd", "authCd", null, null, TYPE_HANKAKUEISU, "15", null},
	{"crDrRsnCd", "crDrRsnCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"crDrRsnSubCd", "crDrRsnSubCd", null, null, TYPE_HANKAKUEISU, "6", null},
	{"sysSrcCd", "sysSrcCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"rossOrdTpCd", "rossOrdTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"pmtTermCashDiscCd", "pmtTermCashDiscCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"crCardOrdNum", "crCardOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"histCratCpltFlg", "histCratCpltFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dsOrdTpCd", "dsOrdTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdRsnCd", "dsOrdRsnCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"invRcpntCustCd", "invRcpntCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"crCardChrgCpltCd", "crCardChrgCpltCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"crCardCustRefNum", "crCardCustRefNum", null, null, TYPE_HANKAKUEISU, "40", null},
	{"crCardAuthRefNum", "crCardAuthRefNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"crCardAuthDt", "crCardAuthDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"crCardTpCd", "crCardTpCd", null, null, TYPE_HANKAKUEISU, "5", null},
	{"dsInvExprDt", "dsInvExprDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"easyPackRate", "easyPackRate", null, null, TYPE_SEISU_SYOSU, "11", "5"},
	{"easyPackMthQuotSumQty", "easyPackMthQuotSumQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"dsInvTpCd", "dsInvTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"srcSysDocNum", "srcSysDocNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"slsRepTocCd", "slsRepTocCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"itrlInvErrFlg", "itrlInvErrFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"custCareTktNum", "custCareTktNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"billToCustAcctCd", "billToCustAcctCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"svcInvPk", "svcInvPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrCatgCd", "dsContrCatgCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"soldToCustLocCd", "soldToCustLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"lineBizTpCd", "lineBizTpCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"dsBizAreaCd", "dsBizAreaCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dplyMdseDtlFlg", "dplyMdseDtlFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"billToCtacPsnFirstNm", "billToCtacPsnFirstNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"billToCtacPsnMidNm", "billToCtacPsnMidNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"billToCtacPsnLastNm", "billToCtacPsnLastNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"invPrtBatTpCd", "invPrtBatTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"contrRnwTotCnt", "contrRnwTotCnt", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"dsOrdCatgCd", "dsOrdCatgCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"csmpInvClmStsCd", "csmpInvClmStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"csmpOrigInvNum", "csmpOrigInvNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"crRebilRsnCatgCd", "crRebilRsnCatgCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NWZC040001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum
        {"ORIG_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origInvNum
        {"GRP_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//grpInvNum
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt
        {"ACCT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctDt
        {"INV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTpCd
        {"NET_DUE_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//netDueDt
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"ORD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordDt
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum
        {"CUST_ISS_PO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoDt
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd
        {"SELL_TO_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToLocNm
        {"SELL_TO_ADDL_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToAddlLocNm
        {"SELL_TO_FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToFirstLineAddr
        {"SELL_TO_SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToScdLineAddr
        {"SELL_TO_THIRD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToThirdLineAddr
        {"SELL_TO_FRTH_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToFrthLineAddr
        {"SELL_TO_CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCtyAddr
        {"SELL_TO_PROV_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToProvNm
        {"SELL_TO_CNTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCntyNm
        {"SELL_TO_ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToStCd
        {"SELL_TO_POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToPostCd
        {"SELL_TO_CTRY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCtryCd
        {"SELL_TO_FIRST_REF_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToFirstRefCmntTxt
        {"SELL_TO_SCD_REF_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToScdRefCmntTxt
        {"PAYER_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//payerCustCd
        {"ADV_RCPT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//advRcptNum
        {"PMT_TERM_START_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermStartDt
        {"INV_PMT_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPmtMethCd
        {"INV_PMT_COND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPmtCondCd
        {"PMT_TERM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCd
        {"PMT_TERM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermNm
        {"CASH_DISC_TERM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cashDiscTermCd
        {"SLS_ADMIN_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsAdminPsnCd
        {"INV_TOT_DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealNetAmt
        {"INV_TOT_DEAL_SLS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealSlsAmt
        {"INV_TOT_DEAL_FRT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealFrtAmt
        {"INV_TOT_DEAL_TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealTaxAmt
        {"INV_TOT_DEAL_DISC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealDiscAmt
        {"INV_TOT_DEAL_INS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealInsAmt
        {"INV_TOT_FUNC_INS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotFuncInsAmt
        {"INV_FIRST_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFirstCmntTxt
        {"INV_SCD_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invScdCmntTxt
        {"INV_THIRD_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invThirdCmntTxt
        {"INV_FRTH_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFrthCmntTxt
        {"DEAL_CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealCcyCd
        {"ACTL_APPLY_EXCH_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actlApplyExchRate
        {"FL_PLN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//flPlnFlg
        {"INV_PRINT_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrintStsCd
        {"TRX_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxSrcTpCd
        {"INV_ML_SEND_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invMlSendStsCd
        {"INV_EDI_SEND_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invEdiSendStsCd
        {"INV_FAX_SEND_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFaxSendStsCd
        {"INV_EML_SEND_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invEmlSendStsCd
        {"AUTH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//authCd
        {"CR_DR_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crDrRsnCd
        {"CR_DR_RSN_SUB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crDrRsnSubCd
        {"SYS_SRC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysSrcCd
        {"ROSS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rossOrdTpCd
        {"PMT_TERM_CASH_DISC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCashDiscCd
        {"CR_CARD_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardOrdNum
        {"HIST_CRAT_CPLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//histCratCpltFlg
        {"DS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd
        {"DS_ORD_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdRsnCd
        {"INV_RCPNT_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invRcpntCustCd
        {"CR_CARD_CHRG_CPLT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardChrgCpltCd
        {"CR_CARD_CUST_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardCustRefNum
        {"CR_CARD_AUTH_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardAuthRefNum
        {"CR_CARD_AUTH_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardAuthDt
        {"CR_CARD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardTpCd
        {"DS_INV_EXPR_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsInvExprDt
        {"EASY_PACK_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//easyPackRate
        {"EASY_PACK_MTH_QUOT_SUM_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//easyPackMthQuotSumQty
        {"DS_INV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsInvTpCd
        {"SRC_SYS_DOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcSysDocNum
        {"SLS_REP_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd
        {"ITRL_INV_ERR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlInvErrFlg
        {"CUST_CARE_TKT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custCareTktNum
        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd
        {"SVC_INV_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvPk
        {"DS_CONTR_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd
        {"SOLD_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustLocCd
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd
        {"DS_BIZ_AREA_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBizAreaCd
        {"DPLY_MDSE_DTL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyMdseDtlFlg
        {"BILL_TO_CTAC_PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCtacPsnFirstNm
        {"BILL_TO_CTAC_PSN_MID_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCtacPsnMidNm
        {"BILL_TO_CTAC_PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCtacPsnLastNm
        {"INV_PRT_BAT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrtBatTpCd
        {"CONTR_RNW_TOT_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrRnwTotCnt
        {"DS_ORD_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd
        {"CSMP_INV_CLM_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpInvClmStsCd
        {"CSMP_ORIG_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpOrigInvNum
        {"CR_REBIL_RSN_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilRsnCatgCd
		null,	//xxMsgIdList
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
