//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20221130162139000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL0770_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL0770;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL0770 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL0770_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX*/
	public final EZDCStringItem              xxChkBox;

    /** XX_AR_CASH_APPLY_STS_TXT*/
	public final EZDCStringItem              xxArCashApplyStsTxt;

    /** AR_TRX_NUM*/
	public final EZDCStringItem              arTrxNum;

    /** GRP_INV_NUM*/
	public final EZDCStringItem              grpInvNum;

    /** AR_TRX_TP_CD*/
	public final EZDCStringItem              arTrxTpCd;

    /** CPO_ORD_NUM*/
	public final EZDCStringItem              cpoOrdNum;

    /** INV_DUE_DT*/
	public final EZDCDateItem              invDueDt;

    /** GL_DT_A1*/
	public final EZDCDateItem              glDt_A1;

    /** CUST_ISS_PO_NUM*/
	public final EZDCStringItem              custIssPoNum;

    /** AR_CUST_REF_NUM*/
	public final EZDCStringItem              arCustRefNum;

    /** AR_CUST_REF_NUM_BK*/
	public final EZDCStringItem              arCustRefNum_BK;

    /** SELL_TO_CUST_CD*/
	public final EZDCStringItem              sellToCustCd;

    /** BILL_TO_CUST_CD*/
	public final EZDCStringItem              billToCustCd;

    /** DEAL_ORIG_GRS_AMT*/
	public final EZDCBigDecimalItem              dealOrigGrsAmt;

    /** DEAL_APPLY_GRS_AMT*/
	public final EZDCBigDecimalItem              dealApplyGrsAmt;

    /** DEAL_APPLY_CR_AMT*/
	public final EZDCBigDecimalItem              dealApplyCrAmt;

    /** DEAL_NET_SLS_AMT*/
	public final EZDCBigDecimalItem              dealNetSlsAmt;

    /** DEAL_FRT_AMT*/
	public final EZDCBigDecimalItem              dealFrtAmt;

    /** DEAL_TAX_AMT*/
	public final EZDCBigDecimalItem              dealTaxAmt;

    /** DEAL_RMNG_BAL_GRS_AMT*/
	public final EZDCBigDecimalItem              dealRmngBalGrsAmt;

    /** XX_DTL_DIFF_AMT_A1*/
	public final EZDCBigDecimalItem              xxDtlDiffAmt_A1;

    /** XX_DEAL_APPLY_AMT_NUM_A1*/
	public final EZDCBigDecimalItem              xxDealApplyAmtNum_A1;

    /** CASH_APP_DT_A1*/
	public final EZDCDateItem              cashAppDt_A1;

    /** CASH_APP_GL_DT_A1*/
	public final EZDCDateItem              cashAppGlDt_A1;

    /** XX_CMNT_TXT_A1*/
	public final EZDCStringItem              xxCmntTxt_A1;

    /** DEAL_CASH_DISC_AMT_A1*/
	public final EZDCBigDecimalItem              dealCashDiscAmt_A1;

    /** PMT_TERM_CASH_DISC_CD_A1*/
	public final EZDCStringItem              pmtTermCashDiscCd_A1;

    /** PMT_TERM_CD_A1*/
	public final EZDCStringItem              pmtTermCd_A1;

    /** INV_PMT_COND_CD_A1*/
	public final EZDCStringItem              invPmtCondCd_A1;

    /** INV_PMT_METH_CD_A1*/
	public final EZDCStringItem              invPmtMethCd_A1;

    /** AR_EXP_PMT_COND_NM_A1*/
	public final EZDCStringItem              arExpPmtCondNm_A1;

    /** AR_EXP_PMT_METH_NM_A1*/
	public final EZDCStringItem              arExpPmtMethNm_A1;

    /** XX_SHPG_METH_TP_TXT_A1*/
	public final EZDCStringItem              xxShpgMethTpTxt_A1;

    /** XX_SHPG_METH_TP_TXT_A2*/
	public final EZDCStringItem              xxShpgMethTpTxt_A2;

    /** DEAL_CCY_CD_A1*/
	public final EZDCStringItem              dealCcyCd_A1;

    /** CASH_DISC_PCT_A1*/
	public final EZDCBigDecimalItem              cashDiscPct_A1;

    /** AR_ADJ_TP_CD_A1*/
	public final EZDCStringItem              arAdjTpCd_A1;

    /** AR_ADJ_TP_NM_A1*/
	public final EZDCStringItem              arAdjTpNm_A1;

    /** XX_DEAL_APPLY_ADJ_AMT_NUM_A1*/
	public final EZDCBigDecimalItem              xxDealApplyAdjAmtNum_A1;

    /** AR_TRX_DT*/
	public final EZDCDateItem              arTrxDt;

    /** CASH_DISC_TERM_CD_A1*/
	public final EZDCStringItem              cashDiscTermCd_A1;

    /** TOC_CD_A1*/
	public final EZDCStringItem              tocCd_A1;

    /** XX_PG_FLG_A1*/
	public final EZDCStringItem              xxPgFlg_A1;

    /** XX_PG_FLG_A2*/
	public final EZDCStringItem              xxPgFlg_A2;

    /** XX_PG_FLG_A3*/
	public final EZDCStringItem              xxPgFlg_A3;

    /** BILL_TO_CUST_ACCT_CD_A1*/
	public final EZDCStringItem              billToCustAcctCd_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDCStringItem              dsAcctNm_A1;

    /** XX_MODE_IND_BK*/
	public final EZDCStringItem              xxModeInd_BK;

    /** APPLY_GRP_KEY_BK*/
	public final EZDCStringItem              applyGrpKey_BK;

    /** APPLY_GRP_SUB_PK_BK*/
	public final EZDCBigDecimalItem              applyGrpSubPk_BK;

    /** AR_TRX_BAL_PK_BK*/
	public final EZDCBigDecimalItem              arTrxBalPk_BK;

    /** AR_CASH_DISC_SCHD_SQ_NUM_BK*/
	public final EZDCStringItem              arCashDiscSchdSqNum_BK;

    /** INV_TRX_BAL_PK_BK*/
	public final EZDCBigDecimalItem              invTrxBalPk_BK;

    /** INV_TRX_BAL_LAST_UPD_TS_BK*/
	public final EZDCStringItem              invTrxBalLastUpdTs_BK;

    /** INV_TRX_BAL_TZ_BK*/
	public final EZDCStringItem              invTrxBalTz_BK;

    /** CR_NUM_BK*/
	public final EZDCStringItem              crNum_BK;

    /** CR_TRX_BAL_PK_BK*/
	public final EZDCBigDecimalItem              crTrxBalPk_BK;

    /** CR_TRX_BAL_LAST_UPD_TS_BK*/
	public final EZDCStringItem              crTrxBalLastUpdTs_BK;

    /** CR_TRX_BAL_TZ_BK*/
	public final EZDCStringItem              crTrxBalTz_BK;

    /** AR_ADJ_NUM_BK*/
	public final EZDCStringItem              arAdjNum_BK;

    /** AR_ADJ_TRX_TP_CD_BK*/
	public final EZDCStringItem              arAdjTrxTpCd_BK;

    /** AR_ADJ_TP_CD_BK*/
	public final EZDCStringItem              arAdjTpCd_BK;

    /** AR_ADJ_TP_NM_BK*/
	public final EZDCStringItem              arAdjTpNm_BK;

    /** ADJ_CMNT_TXT_BK*/
	public final EZDCStringItem              adjCmntTxt_BK;

    /** DEAL_APPLY_AMT_BK*/
	public final EZDCBigDecimalItem              dealApplyAmt_BK;

    /** DEAL_APPLY_ADJ_AMT_BK*/
	public final EZDCBigDecimalItem              dealApplyAdjAmt_BK;

    /** DEAL_APPLY_ADJ_RSVD_AMT_BK*/
	public final EZDCBigDecimalItem              dealApplyAdjRsvdAmt_BK;

    /** AR_CASH_APP_PK_BK*/
	public final EZDCBigDecimalItem              arCashAppPk_BK;

    /** AR_CASH_APPLY_STS_CD_BK*/
	public final EZDCStringItem              arCashApplyStsCd_BK;

    /** AR_ADJ_TP_CD_A3*/
	public final EZDCStringItem              arAdjTpCd_A3;

    /** AR_ADJ_TP_NM_A3*/
	public final EZDCStringItem              arAdjTpNm_A3;

    /** DEAL_APPLY_ADJ_AMT_A3*/
	public final EZDCBigDecimalItem              dealApplyAdjAmt_A3;

    /** DEAL_APPLY_ADJ_RSVD_AMT_A3*/
	public final EZDCBigDecimalItem              dealApplyAdjRsvdAmt_A3;

    /** AR_CASH_APP_SQ_NUM_UP*/
	public final EZDCStringItem              arCashAppSqNum_UP;

    /** _EZUpdateDateTime_UP*/
	public final EZDCStringItem              ezUpTime_UP;

    /** _EZUpTimeZone_UP*/
	public final EZDCStringItem              ezUpTimeZone_UP;

    /** DEAL_APPLY_ADJ_AMT_B3*/
	public final EZDCBigDecimalItem              dealApplyAdjAmt_B3;

    /** DEAL_APPLY_ADJ_RSVD_AMT_B3*/
	public final EZDCBigDecimalItem              dealApplyAdjRsvdAmt_B3;

    /** COA_PROD_CD_BK*/
	public final EZDCStringItem              coaProdCd_BK;


	/**
	 * NFCL0770_ACMsg is constructor.
	 * The initialization when the instance of NFCL0770_ACMsg is generated.
	 */
	public NFCL0770_ACMsg() {
		this(false, -1);
	}

	/**
	 * NFCL0770_ACMsg is constructor.
	 * The initialization when the instance of NFCL0770_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL0770_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox = (EZDCStringItem)newItem("xxChkBox");
		xxArCashApplyStsTxt = (EZDCStringItem)newItem("xxArCashApplyStsTxt");
		arTrxNum = (EZDCStringItem)newItem("arTrxNum");
		grpInvNum = (EZDCStringItem)newItem("grpInvNum");
		arTrxTpCd = (EZDCStringItem)newItem("arTrxTpCd");
		cpoOrdNum = (EZDCStringItem)newItem("cpoOrdNum");
		invDueDt = (EZDCDateItem)newItem("invDueDt");
		glDt_A1 = (EZDCDateItem)newItem("glDt_A1");
		custIssPoNum = (EZDCStringItem)newItem("custIssPoNum");
		arCustRefNum = (EZDCStringItem)newItem("arCustRefNum");
		arCustRefNum_BK = (EZDCStringItem)newItem("arCustRefNum_BK");
		sellToCustCd = (EZDCStringItem)newItem("sellToCustCd");
		billToCustCd = (EZDCStringItem)newItem("billToCustCd");
		dealOrigGrsAmt = (EZDCBigDecimalItem)newItem("dealOrigGrsAmt");
		dealApplyGrsAmt = (EZDCBigDecimalItem)newItem("dealApplyGrsAmt");
		dealApplyCrAmt = (EZDCBigDecimalItem)newItem("dealApplyCrAmt");
		dealNetSlsAmt = (EZDCBigDecimalItem)newItem("dealNetSlsAmt");
		dealFrtAmt = (EZDCBigDecimalItem)newItem("dealFrtAmt");
		dealTaxAmt = (EZDCBigDecimalItem)newItem("dealTaxAmt");
		dealRmngBalGrsAmt = (EZDCBigDecimalItem)newItem("dealRmngBalGrsAmt");
		xxDtlDiffAmt_A1 = (EZDCBigDecimalItem)newItem("xxDtlDiffAmt_A1");
		xxDealApplyAmtNum_A1 = (EZDCBigDecimalItem)newItem("xxDealApplyAmtNum_A1");
		cashAppDt_A1 = (EZDCDateItem)newItem("cashAppDt_A1");
		cashAppGlDt_A1 = (EZDCDateItem)newItem("cashAppGlDt_A1");
		xxCmntTxt_A1 = (EZDCStringItem)newItem("xxCmntTxt_A1");
		dealCashDiscAmt_A1 = (EZDCBigDecimalItem)newItem("dealCashDiscAmt_A1");
		pmtTermCashDiscCd_A1 = (EZDCStringItem)newItem("pmtTermCashDiscCd_A1");
		pmtTermCd_A1 = (EZDCStringItem)newItem("pmtTermCd_A1");
		invPmtCondCd_A1 = (EZDCStringItem)newItem("invPmtCondCd_A1");
		invPmtMethCd_A1 = (EZDCStringItem)newItem("invPmtMethCd_A1");
		arExpPmtCondNm_A1 = (EZDCStringItem)newItem("arExpPmtCondNm_A1");
		arExpPmtMethNm_A1 = (EZDCStringItem)newItem("arExpPmtMethNm_A1");
		xxShpgMethTpTxt_A1 = (EZDCStringItem)newItem("xxShpgMethTpTxt_A1");
		xxShpgMethTpTxt_A2 = (EZDCStringItem)newItem("xxShpgMethTpTxt_A2");
		dealCcyCd_A1 = (EZDCStringItem)newItem("dealCcyCd_A1");
		cashDiscPct_A1 = (EZDCBigDecimalItem)newItem("cashDiscPct_A1");
		arAdjTpCd_A1 = (EZDCStringItem)newItem("arAdjTpCd_A1");
		arAdjTpNm_A1 = (EZDCStringItem)newItem("arAdjTpNm_A1");
		xxDealApplyAdjAmtNum_A1 = (EZDCBigDecimalItem)newItem("xxDealApplyAdjAmtNum_A1");
		arTrxDt = (EZDCDateItem)newItem("arTrxDt");
		cashDiscTermCd_A1 = (EZDCStringItem)newItem("cashDiscTermCd_A1");
		tocCd_A1 = (EZDCStringItem)newItem("tocCd_A1");
		xxPgFlg_A1 = (EZDCStringItem)newItem("xxPgFlg_A1");
		xxPgFlg_A2 = (EZDCStringItem)newItem("xxPgFlg_A2");
		xxPgFlg_A3 = (EZDCStringItem)newItem("xxPgFlg_A3");
		billToCustAcctCd_A1 = (EZDCStringItem)newItem("billToCustAcctCd_A1");
		dsAcctNm_A1 = (EZDCStringItem)newItem("dsAcctNm_A1");
		xxModeInd_BK = (EZDCStringItem)newItem("xxModeInd_BK");
		applyGrpKey_BK = (EZDCStringItem)newItem("applyGrpKey_BK");
		applyGrpSubPk_BK = (EZDCBigDecimalItem)newItem("applyGrpSubPk_BK");
		arTrxBalPk_BK = (EZDCBigDecimalItem)newItem("arTrxBalPk_BK");
		arCashDiscSchdSqNum_BK = (EZDCStringItem)newItem("arCashDiscSchdSqNum_BK");
		invTrxBalPk_BK = (EZDCBigDecimalItem)newItem("invTrxBalPk_BK");
		invTrxBalLastUpdTs_BK = (EZDCStringItem)newItem("invTrxBalLastUpdTs_BK");
		invTrxBalTz_BK = (EZDCStringItem)newItem("invTrxBalTz_BK");
		crNum_BK = (EZDCStringItem)newItem("crNum_BK");
		crTrxBalPk_BK = (EZDCBigDecimalItem)newItem("crTrxBalPk_BK");
		crTrxBalLastUpdTs_BK = (EZDCStringItem)newItem("crTrxBalLastUpdTs_BK");
		crTrxBalTz_BK = (EZDCStringItem)newItem("crTrxBalTz_BK");
		arAdjNum_BK = (EZDCStringItem)newItem("arAdjNum_BK");
		arAdjTrxTpCd_BK = (EZDCStringItem)newItem("arAdjTrxTpCd_BK");
		arAdjTpCd_BK = (EZDCStringItem)newItem("arAdjTpCd_BK");
		arAdjTpNm_BK = (EZDCStringItem)newItem("arAdjTpNm_BK");
		adjCmntTxt_BK = (EZDCStringItem)newItem("adjCmntTxt_BK");
		dealApplyAmt_BK = (EZDCBigDecimalItem)newItem("dealApplyAmt_BK");
		dealApplyAdjAmt_BK = (EZDCBigDecimalItem)newItem("dealApplyAdjAmt_BK");
		dealApplyAdjRsvdAmt_BK = (EZDCBigDecimalItem)newItem("dealApplyAdjRsvdAmt_BK");
		arCashAppPk_BK = (EZDCBigDecimalItem)newItem("arCashAppPk_BK");
		arCashApplyStsCd_BK = (EZDCStringItem)newItem("arCashApplyStsCd_BK");
		arAdjTpCd_A3 = (EZDCStringItem)newItem("arAdjTpCd_A3");
		arAdjTpNm_A3 = (EZDCStringItem)newItem("arAdjTpNm_A3");
		dealApplyAdjAmt_A3 = (EZDCBigDecimalItem)newItem("dealApplyAdjAmt_A3");
		dealApplyAdjRsvdAmt_A3 = (EZDCBigDecimalItem)newItem("dealApplyAdjRsvdAmt_A3");
		arCashAppSqNum_UP = (EZDCStringItem)newItem("arCashAppSqNum_UP");
		ezUpTime_UP = (EZDCStringItem)newItem("ezUpTime_UP");
		ezUpTimeZone_UP = (EZDCStringItem)newItem("ezUpTimeZone_UP");
		dealApplyAdjAmt_B3 = (EZDCBigDecimalItem)newItem("dealApplyAdjAmt_B3");
		dealApplyAdjRsvdAmt_B3 = (EZDCBigDecimalItem)newItem("dealApplyAdjRsvdAmt_B3");
		coaProdCd_BK = (EZDCStringItem)newItem("coaProdCd_BK");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL0770_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL0770_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox", "xxChkBox", null, null, TYPE_HANKAKUEIJI, "1", null},
	{"xxArCashApplyStsTxt", "xxArCashApplyStsTxt", null, null, TYPE_HANKAKUEISU, "32", null},
	{"arTrxNum", "arTrxNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"grpInvNum", "grpInvNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"arTrxTpCd", "arTrxTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"invDueDt", "invDueDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"glDt_A1", "glDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"custIssPoNum", "custIssPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"arCustRefNum", "arCustRefNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"arCustRefNum_BK", "arCustRefNum_BK", "BK", null, TYPE_HANKAKUEISU, "35", null},
	{"sellToCustCd", "sellToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustCd", "billToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dealOrigGrsAmt", "dealOrigGrsAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealApplyGrsAmt", "dealApplyGrsAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealApplyCrAmt", "dealApplyCrAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealNetSlsAmt", "dealNetSlsAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealFrtAmt", "dealFrtAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealTaxAmt", "dealTaxAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealRmngBalGrsAmt", "dealRmngBalGrsAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxDtlDiffAmt_A1", "xxDtlDiffAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxDealApplyAmtNum_A1", "xxDealApplyAmtNum_A1", "A1", null, TYPE_SEISU_SYOSU, "17", "2"},
	{"cashAppDt_A1", "cashAppDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"cashAppGlDt_A1", "cashAppGlDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxCmntTxt_A1", "xxCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"dealCashDiscAmt_A1", "dealCashDiscAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"pmtTermCashDiscCd_A1", "pmtTermCashDiscCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"pmtTermCd_A1", "pmtTermCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"invPmtCondCd_A1", "invPmtCondCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"invPmtMethCd_A1", "invPmtMethCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"arExpPmtCondNm_A1", "arExpPmtCondNm_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"arExpPmtMethNm_A1", "arExpPmtMethNm_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"xxShpgMethTpTxt_A1", "xxShpgMethTpTxt_A1", "A1", null, TYPE_HANKAKUEISU, "33", null},
	{"xxShpgMethTpTxt_A2", "xxShpgMethTpTxt_A2", "A2", null, TYPE_HANKAKUEISU, "33", null},
	{"dealCcyCd_A1", "dealCcyCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"cashDiscPct_A1", "cashDiscPct_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "2"},
	{"arAdjTpCd_A1", "arAdjTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"arAdjTpNm_A1", "arAdjTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxDealApplyAdjAmtNum_A1", "xxDealApplyAdjAmtNum_A1", "A1", null, TYPE_SEISU_SYOSU, "17", "2"},
	{"arTrxDt", "arTrxDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"cashDiscTermCd_A1", "cashDiscTermCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"tocCd_A1", "tocCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxPgFlg_A1", "xxPgFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxPgFlg_A2", "xxPgFlg_A2", "A2", null, TYPE_HANKAKUEISU, "1", null},
	{"xxPgFlg_A3", "xxPgFlg_A3", "A3", null, TYPE_HANKAKUEISU, "1", null},
	{"billToCustAcctCd_A1", "billToCustAcctCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"xxModeInd_BK", "xxModeInd_BK", "BK", null, TYPE_HANKAKUEISU, "1", null},
	{"applyGrpKey_BK", "applyGrpKey_BK", "BK", null, TYPE_HANKAKUEISU, "40", null},
	{"applyGrpSubPk_BK", "applyGrpSubPk_BK", "BK", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"arTrxBalPk_BK", "arTrxBalPk_BK", "BK", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"arCashDiscSchdSqNum_BK", "arCashDiscSchdSqNum_BK", "BK", null, TYPE_HANKAKUEISU, "3", null},
	{"invTrxBalPk_BK", "invTrxBalPk_BK", "BK", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"invTrxBalLastUpdTs_BK", "invTrxBalLastUpdTs_BK", "BK", null, TYPE_HANKAKUSUJI, "17", null},
	{"invTrxBalTz_BK", "invTrxBalTz_BK", "BK", null, TYPE_HANKAKUEISU, "5", null},
	{"crNum_BK", "crNum_BK", "BK", null, TYPE_HANKAKUEISU, "13", null},
	{"crTrxBalPk_BK", "crTrxBalPk_BK", "BK", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"crTrxBalLastUpdTs_BK", "crTrxBalLastUpdTs_BK", "BK", null, TYPE_HANKAKUSUJI, "17", null},
	{"crTrxBalTz_BK", "crTrxBalTz_BK", "BK", null, TYPE_HANKAKUEISU, "5", null},
	{"arAdjNum_BK", "arAdjNum_BK", "BK", null, TYPE_HANKAKUEISU, "8", null},
	{"arAdjTrxTpCd_BK", "arAdjTrxTpCd_BK", "BK", null, TYPE_HANKAKUEISU, "3", null},
	{"arAdjTpCd_BK", "arAdjTpCd_BK", "BK", null, TYPE_HANKAKUEISU, "3", null},
	{"arAdjTpNm_BK", "arAdjTpNm_BK", "BK", null, TYPE_HANKAKUEISU, "30", null},
	{"adjCmntTxt_BK", "adjCmntTxt_BK", "BK", null, TYPE_HANKAKUEISU, "65", null},
	{"dealApplyAmt_BK", "dealApplyAmt_BK", "BK", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealApplyAdjAmt_BK", "dealApplyAdjAmt_BK", "BK", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealApplyAdjRsvdAmt_BK", "dealApplyAdjRsvdAmt_BK", "BK", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"arCashAppPk_BK", "arCashAppPk_BK", "BK", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"arCashApplyStsCd_BK", "arCashApplyStsCd_BK", "BK", null, TYPE_HANKAKUEISU, "1", null},
	{"arAdjTpCd_A3", "arAdjTpCd_A3", "A3", null, TYPE_HANKAKUEISU, "3", null},
	{"arAdjTpNm_A3", "arAdjTpNm_A3", "A3", null, TYPE_HANKAKUEISU, "30", null},
	{"dealApplyAdjAmt_A3", "dealApplyAdjAmt_A3", "A3", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealApplyAdjRsvdAmt_A3", "dealApplyAdjRsvdAmt_A3", "A3", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"arCashAppSqNum_UP", "arCashAppSqNum_UP", "UP", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_UP", "ezUpTime_UP", "UP", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_UP", "ezUpTimeZone_UP", "UP", null, TYPE_HANKAKUEISU, "5", null},
	{"dealApplyAdjAmt_B3", "dealApplyAdjAmt_B3", "B3", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealApplyAdjRsvdAmt_B3", "dealApplyAdjRsvdAmt_B3", "B3", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"coaProdCd_BK", "coaProdCd_BK", "BK", null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"XX_AR_CASH_APPLY_STS_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxArCashApplyStsTxt
        {"AR_TRX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxNum
        {"GRP_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//grpInvNum
        {"AR_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxTpCd
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"INV_DUE_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDueDt
        {"GL_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glDt_A1
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum
        {"AR_CUST_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCustRefNum
        {"AR_CUST_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCustRefNum_BK
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd
        {"DEAL_ORIG_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealOrigGrsAmt
        {"DEAL_APPLY_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealApplyGrsAmt
        {"DEAL_APPLY_CR_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealApplyCrAmt
        {"DEAL_NET_SLS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealNetSlsAmt
        {"DEAL_FRT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealFrtAmt
        {"DEAL_TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealTaxAmt
        {"DEAL_RMNG_BAL_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRmngBalGrsAmt
        {"XX_DTL_DIFF_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlDiffAmt_A1
        {"XX_DEAL_APPLY_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDealApplyAmtNum_A1
        {"CASH_APP_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cashAppDt_A1
        {"CASH_APP_GL_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cashAppGlDt_A1
        {"XX_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCmntTxt_A1
        {"DEAL_CASH_DISC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealCashDiscAmt_A1
        {"PMT_TERM_CASH_DISC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCashDiscCd_A1
        {"PMT_TERM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCd_A1
        {"INV_PMT_COND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPmtCondCd_A1
        {"INV_PMT_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPmtMethCd_A1
        {"AR_EXP_PMT_COND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arExpPmtCondNm_A1
        {"AR_EXP_PMT_METH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arExpPmtMethNm_A1
        {"XX_SHPG_METH_TP_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShpgMethTpTxt_A1
        {"XX_SHPG_METH_TP_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShpgMethTpTxt_A2
        {"DEAL_CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealCcyCd_A1
        {"CASH_DISC_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cashDiscPct_A1
        {"AR_ADJ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpCd_A1
        {"AR_ADJ_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpNm_A1
        {"XX_DEAL_APPLY_ADJ_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDealApplyAdjAmtNum_A1
        {"AR_TRX_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxDt
        {"CASH_DISC_TERM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cashDiscTermCd_A1
        {"TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd_A1
        {"XX_PG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPgFlg_A1
        {"XX_PG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPgFlg_A2
        {"XX_PG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPgFlg_A3
        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd_A1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"XX_MODE_IND",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeInd_BK
        {"APPLY_GRP_KEY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//applyGrpKey_BK
        {"APPLY_GRP_SUB_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//applyGrpSubPk_BK
        {"AR_TRX_BAL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxBalPk_BK
        {"AR_CASH_DISC_SCHD_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCashDiscSchdSqNum_BK
        {"INV_TRX_BAL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTrxBalPk_BK
        {"INV_TRX_BAL_LAST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTrxBalLastUpdTs_BK
        {"INV_TRX_BAL_TZ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTrxBalTz_BK
        {"CR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crNum_BK
        {"CR_TRX_BAL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crTrxBalPk_BK
        {"CR_TRX_BAL_LAST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crTrxBalLastUpdTs_BK
        {"CR_TRX_BAL_TZ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crTrxBalTz_BK
        {"AR_ADJ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjNum_BK
        {"AR_ADJ_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTrxTpCd_BK
        {"AR_ADJ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpCd_BK
        {"AR_ADJ_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpNm_BK
        {"ADJ_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjCmntTxt_BK
        {"DEAL_APPLY_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealApplyAmt_BK
        {"DEAL_APPLY_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealApplyAdjAmt_BK
        {"DEAL_APPLY_ADJ_RSVD_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealApplyAdjRsvdAmt_BK
        {"AR_CASH_APP_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCashAppPk_BK
        {"AR_CASH_APPLY_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCashApplyStsCd_BK
        {"AR_ADJ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpCd_A3
        {"AR_ADJ_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpNm_A3
        {"DEAL_APPLY_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealApplyAdjAmt_A3
        {"DEAL_APPLY_ADJ_RSVD_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealApplyAdjRsvdAmt_A3
        {"AR_CASH_APP_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCashAppSqNum_UP
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_UP
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_UP
        {"DEAL_APPLY_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealApplyAdjAmt_B3
        {"DEAL_APPLY_ADJ_RSVD_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealApplyAdjRsvdAmt_B3
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_BK
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

