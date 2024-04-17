//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20161018181327000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC156001_svcConfigRefPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC156001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC156001_svcConfigRefPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORD_PRFT_TRX_CATG_CD*/
	public final EZDPStringItem              ordPrftTrxCatgCd;

    /** TRX_LINE_NUM*/
	public final EZDPStringItem              trxLineNum;

    /** TRX_LINE_SUB_NUM*/
	public final EZDPStringItem              trxLineSubNum;

    /** DS_ORD_POSN_NUM*/
	public final EZDPStringItem              dsOrdPosnNum;

    /** DS_CPO_LINE_NUM*/
	public final EZDPBigDecimalItem              dsCpoLineNum;

    /** DS_CPO_LINE_SUB_NUM*/
	public final EZDPBigDecimalItem              dsCpoLineSubNum;

    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDPStringItem              mdseDescShortTxt;

    /** MDSE_NM*/
	public final EZDPStringItem              mdseNm;

    /** ORIG_MDSE_CD*/
	public final EZDPStringItem              origMdseCd;

    /** LINE_BIZ_TP_CD*/
	public final EZDPStringItem              lineBizTpCd;

    /** RTL_WH_CD*/
	public final EZDPStringItem              rtlWhCd;

    /** RTL_SWH_CD*/
	public final EZDPStringItem              rtlSwhCd;

    /** DS_ORD_LINE_CATG_CD*/
	public final EZDPStringItem              dsOrdLineCatgCd;

    /** ORD_QTY*/
	public final EZDPBigDecimalItem              ordQty;

    /** CUST_UOM_CD*/
	public final EZDPStringItem              custUomCd;

    /** TRX_REF_LINE_NUM*/
	public final EZDPStringItem              trxRefLineNum;

    /** TRX_REF_LINE_SUB_NUM*/
	public final EZDPStringItem              trxRefLineSubNum;

    /** FL_PRC_LIST_CD*/
	public final EZDPStringItem              flPrcListCd;

    /** FL_PRC_LIST_NM*/
	public final EZDPStringItem              flPrcListNm;

    /** CSMP_PRC_LIST_CD*/
	public final EZDPStringItem              csmpPrcListCd;

    /** PRC_CATG_NM*/
	public final EZDPStringItem              prcCatgNm;

    /** FUNC_DLR_CR_AMT*/
	public final EZDPBigDecimalItem              funcDlrCrAmt;

    /** FUNC_RED_COMP_AMT*/
	public final EZDPBigDecimalItem              funcRedCompAmt;

    /** FUNC_UNIT_FL_PRC_AMT*/
	public final EZDPBigDecimalItem              funcUnitFlPrcAmt;

    /** MDSE_INVTY_COST_PCT*/
	public final EZDPBigDecimalItem              mdseInvtyCostPct;

    /** FUNC_INIT_FL_PRC_AMT*/
	public final EZDPBigDecimalItem              funcInitFlPrcAmt;

    /** LINE_WT_AMT_RATE*/
	public final EZDPBigDecimalItem              lineWtAmtRate;

    /** FUNC_GENL_FL_ADJ_AMT*/
	public final EZDPBigDecimalItem              funcGenlFlAdjAmt;

    /** FUNC_SPEC_FL_ADJ_AMT*/
	public final EZDPBigDecimalItem              funcSpecFlAdjAmt;

    /** FUNC_FL_ADJ_AMT*/
	public final EZDPBigDecimalItem              funcFlAdjAmt;

    /** FUNC_MAN_FL_ADJ_AMT*/
	public final EZDPBigDecimalItem              funcManFlAdjAmt;

    /** FUNC_CSMP_UNIT_CR_AMT*/
	public final EZDPBigDecimalItem              funcCsmpUnitCrAmt;

    /** FUNC_CSMP_CR_AMT*/
	public final EZDPBigDecimalItem              funcCsmpCrAmt;

    /** FUNC_CSMP_FL_PRC_AMT*/
	public final EZDPBigDecimalItem              funcCsmpFlPrcAmt;

    /** FUNC_SVC_COST_TRNSF_AMT*/
	public final EZDPBigDecimalItem              funcSvcCostTrnsfAmt;

    /** FUNC_WT_SVC_COST_TRNSF_AMT*/
	public final EZDPBigDecimalItem              funcWtSvcCostTrnsfAmt;

    /** FUNC_FINAL_FL_PRC_AMT*/
	public final EZDPBigDecimalItem              funcFinalFlPrcAmt;

    /** FUNC_UNIT_LIST_PRC_AMT*/
	public final EZDPBigDecimalItem              funcUnitListPrcAmt;

    /** FUNC_NET_UNIT_PRC_AMT*/
	public final EZDPBigDecimalItem              funcNetUnitPrcAmt;

    /** FUNC_NET_SELL_PRC_AMT*/
	public final EZDPBigDecimalItem              funcNetSellPrcAmt;

    /** FUNC_INIT_REP_REV_AMT*/
	public final EZDPBigDecimalItem              funcInitRepRevAmt;

    /** FUNC_GENL_REP_REV_ADJ_AMT*/
	public final EZDPBigDecimalItem              funcGenlRepRevAdjAmt;

    /** FUNC_SPEC_REP_REV_ADJ_AMT*/
	public final EZDPBigDecimalItem              funcSpecRepRevAdjAmt;

    /** FUNC_REP_REV_ADJ_AMT*/
	public final EZDPBigDecimalItem              funcRepRevAdjAmt;

    /** FUNC_MAN_REP_REV_ADJ_AMT*/
	public final EZDPBigDecimalItem              funcManRepRevAdjAmt;

    /** FUNC_SVC_REV_TRNSF_AMT*/
	public final EZDPBigDecimalItem              funcSvcRevTrnsfAmt;

    /** FUNC_WT_SVC_REV_TRNSF_AMT*/
	public final EZDPBigDecimalItem              funcWtSvcRevTrnsfAmt;

    /** FUNC_FINAL_REP_REV_AMT*/
	public final EZDPBigDecimalItem              funcFinalRepRevAmt;

    /** FUNC_UNIT_MSRP_AMT*/
	public final EZDPBigDecimalItem              funcUnitMsrpAmt;

    /** FUNC_UNIT_STD_COST_AMT*/
	public final EZDPBigDecimalItem              funcUnitStdCostAmt;

    /** FUNC_FINAL_STD_COST_AMT*/
	public final EZDPBigDecimalItem              funcFinalStdCostAmt;

    /** CHNG_ORD_FLG*/
	public final EZDPStringItem              chngOrdFlg;

    /** COA_MDSE_TP_CD*/
	public final EZDPStringItem              coaMdseTpCd;

    /** COA_PROD_CD*/
	public final EZDPStringItem              coaProdCd;

    /** MDSE_ITEM_TP_CD*/
	public final EZDPStringItem              mdseItemTpCd;

    /** LAST_PRFT_CALC_USR_ID*/
	public final EZDPStringItem              lastPrftCalcUsrId;

    /** LAST_PRFT_CALC_TS*/
	public final EZDPStringItem              lastPrftCalcTs;

    /** ORD_PRFT_RULE_TP_CD*/
	public final EZDPStringItem              ordPrftRuleTpCd;

    /** FL_PRC_CALC_INCL_FLG*/
	public final EZDPStringItem              flPrcCalcInclFlg;

    /** REP_REV_CALC_INCL_FLG*/
	public final EZDPStringItem              repRevCalcInclFlg;

    /** DISC_MDSE_TP_FLG*/
	public final EZDPStringItem              discMdseTpFlg;

    /** RED_FL_PRC_FLG*/
	public final EZDPStringItem              redFlPrcFlg;

    /** RED_REP_REV_FLG*/
	public final EZDPStringItem              redRepRevFlg;

    /** DISC_ALLOC_METH_CD*/
	public final EZDPStringItem              discAllocMethCd;

    /** DLR_CR_PRFT_INCL_FLG*/
	public final EZDPStringItem              dlrCrPrftInclFlg;

    /** RED_COMP_AMT_FLG*/
	public final EZDPStringItem              redCompAmtFlg;

    /** BILL_TO_CUST_CD*/
	public final EZDPStringItem              billToCustCd;

    /** SHIP_TO_CUST_CD*/
	public final EZDPStringItem              shipToCustCd;

    /** ORD_LINE_STS_CD*/
	public final EZDPStringItem              ordLineStsCd;

    /** CPO_DTL_FUNC_SLS_AMT*/
	public final EZDPBigDecimalItem              cpoDtlFuncSlsAmt;

    /** ORD_CUST_UOM_QTY*/
	public final EZDPBigDecimalItem              ordCustUomQty;

    /** CSMP_CONTR_NUM*/
	public final EZDPStringItem              csmpContrNum;

    /** DLR_REF_NUM*/
	public final EZDPStringItem              dlrRefNum;

    /** COA_PROJ_NM*/
	public final EZDPStringItem              coaProjNm;

    /** FUNC_FINAL_UNIT_FL_PRC_AMT*/
	public final EZDPBigDecimalItem              funcFinalUnitFlPrcAmt;

    /** FUNC_FINAL_UNIT_REV_AMT*/
	public final EZDPBigDecimalItem              funcFinalUnitRevAmt;

    /** PRC_CATG_CD*/
	public final EZDPStringItem              prcCatgCd;

    /** PRC_CATG_NM_P2*/
	public final EZDPStringItem              prcCatgNm_P2;

    /** FUNC_WT_MAN_FL_ADJ_AMT*/
	public final EZDPBigDecimalItem              funcWtManFlAdjAmt;

    /** FUNC_WT_MAN_REV_ADJ_AMT*/
	public final EZDPBigDecimalItem              funcWtManRevAdjAmt;

    /** PRC_BASE_DT*/
	public final EZDPDateItem              prcBaseDt;


	/**
	 * NWZC156001_svcConfigRefPMsg is constructor.
	 * The initialization when the instance of NWZC156001_svcConfigRefPMsg is generated.
	 */
	public NWZC156001_svcConfigRefPMsg() {
		this(false, -1);
	}

	/**
	 * NWZC156001_svcConfigRefPMsg is constructor.
	 * The initialization when the instance of NWZC156001_svcConfigRefPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC156001_svcConfigRefPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ordPrftTrxCatgCd = (EZDPStringItem)newItem("ordPrftTrxCatgCd");
		trxLineNum = (EZDPStringItem)newItem("trxLineNum");
		trxLineSubNum = (EZDPStringItem)newItem("trxLineSubNum");
		dsOrdPosnNum = (EZDPStringItem)newItem("dsOrdPosnNum");
		dsCpoLineNum = (EZDPBigDecimalItem)newItem("dsCpoLineNum");
		dsCpoLineSubNum = (EZDPBigDecimalItem)newItem("dsCpoLineSubNum");
		mdseCd = (EZDPStringItem)newItem("mdseCd");
		mdseDescShortTxt = (EZDPStringItem)newItem("mdseDescShortTxt");
		mdseNm = (EZDPStringItem)newItem("mdseNm");
		origMdseCd = (EZDPStringItem)newItem("origMdseCd");
		lineBizTpCd = (EZDPStringItem)newItem("lineBizTpCd");
		rtlWhCd = (EZDPStringItem)newItem("rtlWhCd");
		rtlSwhCd = (EZDPStringItem)newItem("rtlSwhCd");
		dsOrdLineCatgCd = (EZDPStringItem)newItem("dsOrdLineCatgCd");
		ordQty = (EZDPBigDecimalItem)newItem("ordQty");
		custUomCd = (EZDPStringItem)newItem("custUomCd");
		trxRefLineNum = (EZDPStringItem)newItem("trxRefLineNum");
		trxRefLineSubNum = (EZDPStringItem)newItem("trxRefLineSubNum");
		flPrcListCd = (EZDPStringItem)newItem("flPrcListCd");
		flPrcListNm = (EZDPStringItem)newItem("flPrcListNm");
		csmpPrcListCd = (EZDPStringItem)newItem("csmpPrcListCd");
		prcCatgNm = (EZDPStringItem)newItem("prcCatgNm");
		funcDlrCrAmt = (EZDPBigDecimalItem)newItem("funcDlrCrAmt");
		funcRedCompAmt = (EZDPBigDecimalItem)newItem("funcRedCompAmt");
		funcUnitFlPrcAmt = (EZDPBigDecimalItem)newItem("funcUnitFlPrcAmt");
		mdseInvtyCostPct = (EZDPBigDecimalItem)newItem("mdseInvtyCostPct");
		funcInitFlPrcAmt = (EZDPBigDecimalItem)newItem("funcInitFlPrcAmt");
		lineWtAmtRate = (EZDPBigDecimalItem)newItem("lineWtAmtRate");
		funcGenlFlAdjAmt = (EZDPBigDecimalItem)newItem("funcGenlFlAdjAmt");
		funcSpecFlAdjAmt = (EZDPBigDecimalItem)newItem("funcSpecFlAdjAmt");
		funcFlAdjAmt = (EZDPBigDecimalItem)newItem("funcFlAdjAmt");
		funcManFlAdjAmt = (EZDPBigDecimalItem)newItem("funcManFlAdjAmt");
		funcCsmpUnitCrAmt = (EZDPBigDecimalItem)newItem("funcCsmpUnitCrAmt");
		funcCsmpCrAmt = (EZDPBigDecimalItem)newItem("funcCsmpCrAmt");
		funcCsmpFlPrcAmt = (EZDPBigDecimalItem)newItem("funcCsmpFlPrcAmt");
		funcSvcCostTrnsfAmt = (EZDPBigDecimalItem)newItem("funcSvcCostTrnsfAmt");
		funcWtSvcCostTrnsfAmt = (EZDPBigDecimalItem)newItem("funcWtSvcCostTrnsfAmt");
		funcFinalFlPrcAmt = (EZDPBigDecimalItem)newItem("funcFinalFlPrcAmt");
		funcUnitListPrcAmt = (EZDPBigDecimalItem)newItem("funcUnitListPrcAmt");
		funcNetUnitPrcAmt = (EZDPBigDecimalItem)newItem("funcNetUnitPrcAmt");
		funcNetSellPrcAmt = (EZDPBigDecimalItem)newItem("funcNetSellPrcAmt");
		funcInitRepRevAmt = (EZDPBigDecimalItem)newItem("funcInitRepRevAmt");
		funcGenlRepRevAdjAmt = (EZDPBigDecimalItem)newItem("funcGenlRepRevAdjAmt");
		funcSpecRepRevAdjAmt = (EZDPBigDecimalItem)newItem("funcSpecRepRevAdjAmt");
		funcRepRevAdjAmt = (EZDPBigDecimalItem)newItem("funcRepRevAdjAmt");
		funcManRepRevAdjAmt = (EZDPBigDecimalItem)newItem("funcManRepRevAdjAmt");
		funcSvcRevTrnsfAmt = (EZDPBigDecimalItem)newItem("funcSvcRevTrnsfAmt");
		funcWtSvcRevTrnsfAmt = (EZDPBigDecimalItem)newItem("funcWtSvcRevTrnsfAmt");
		funcFinalRepRevAmt = (EZDPBigDecimalItem)newItem("funcFinalRepRevAmt");
		funcUnitMsrpAmt = (EZDPBigDecimalItem)newItem("funcUnitMsrpAmt");
		funcUnitStdCostAmt = (EZDPBigDecimalItem)newItem("funcUnitStdCostAmt");
		funcFinalStdCostAmt = (EZDPBigDecimalItem)newItem("funcFinalStdCostAmt");
		chngOrdFlg = (EZDPStringItem)newItem("chngOrdFlg");
		coaMdseTpCd = (EZDPStringItem)newItem("coaMdseTpCd");
		coaProdCd = (EZDPStringItem)newItem("coaProdCd");
		mdseItemTpCd = (EZDPStringItem)newItem("mdseItemTpCd");
		lastPrftCalcUsrId = (EZDPStringItem)newItem("lastPrftCalcUsrId");
		lastPrftCalcTs = (EZDPStringItem)newItem("lastPrftCalcTs");
		ordPrftRuleTpCd = (EZDPStringItem)newItem("ordPrftRuleTpCd");
		flPrcCalcInclFlg = (EZDPStringItem)newItem("flPrcCalcInclFlg");
		repRevCalcInclFlg = (EZDPStringItem)newItem("repRevCalcInclFlg");
		discMdseTpFlg = (EZDPStringItem)newItem("discMdseTpFlg");
		redFlPrcFlg = (EZDPStringItem)newItem("redFlPrcFlg");
		redRepRevFlg = (EZDPStringItem)newItem("redRepRevFlg");
		discAllocMethCd = (EZDPStringItem)newItem("discAllocMethCd");
		dlrCrPrftInclFlg = (EZDPStringItem)newItem("dlrCrPrftInclFlg");
		redCompAmtFlg = (EZDPStringItem)newItem("redCompAmtFlg");
		billToCustCd = (EZDPStringItem)newItem("billToCustCd");
		shipToCustCd = (EZDPStringItem)newItem("shipToCustCd");
		ordLineStsCd = (EZDPStringItem)newItem("ordLineStsCd");
		cpoDtlFuncSlsAmt = (EZDPBigDecimalItem)newItem("cpoDtlFuncSlsAmt");
		ordCustUomQty = (EZDPBigDecimalItem)newItem("ordCustUomQty");
		csmpContrNum = (EZDPStringItem)newItem("csmpContrNum");
		dlrRefNum = (EZDPStringItem)newItem("dlrRefNum");
		coaProjNm = (EZDPStringItem)newItem("coaProjNm");
		funcFinalUnitFlPrcAmt = (EZDPBigDecimalItem)newItem("funcFinalUnitFlPrcAmt");
		funcFinalUnitRevAmt = (EZDPBigDecimalItem)newItem("funcFinalUnitRevAmt");
		prcCatgCd = (EZDPStringItem)newItem("prcCatgCd");
		prcCatgNm_P2 = (EZDPStringItem)newItem("prcCatgNm_P2");
		funcWtManFlAdjAmt = (EZDPBigDecimalItem)newItem("funcWtManFlAdjAmt");
		funcWtManRevAdjAmt = (EZDPBigDecimalItem)newItem("funcWtManRevAdjAmt");
		prcBaseDt = (EZDPDateItem)newItem("prcBaseDt");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC156001_svcConfigRefPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC156001_svcConfigRefPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ordPrftTrxCatgCd", "ordPrftTrxCatgCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"trxLineNum", "trxLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"trxLineSubNum", "trxLineSubNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"dsOrdPosnNum", "dsOrdPosnNum", null, null, TYPE_HANKAKUEISU, "6", null},
	{"dsCpoLineNum", "dsCpoLineNum", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"dsCpoLineSubNum", "dsCpoLineSubNum", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"mdseNm", "mdseNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"origMdseCd", "origMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"lineBizTpCd", "lineBizTpCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"rtlWhCd", "rtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhCd", "rtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsOrdLineCatgCd", "dsOrdLineCatgCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"ordQty", "ordQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"custUomCd", "custUomCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"trxRefLineNum", "trxRefLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"trxRefLineSubNum", "trxRefLineSubNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"flPrcListCd", "flPrcListCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"flPrcListNm", "flPrcListNm", null, null, TYPE_HANKAKUEISU, "75", null},
	{"csmpPrcListCd", "csmpPrcListCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"prcCatgNm", "prcCatgNm", null, null, TYPE_HANKAKUEISU, "75", null},
	{"funcDlrCrAmt", "funcDlrCrAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcRedCompAmt", "funcRedCompAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcUnitFlPrcAmt", "funcUnitFlPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"mdseInvtyCostPct", "mdseInvtyCostPct", null, null, TYPE_SEISU_SYOSU, "5", "2"},
	{"funcInitFlPrcAmt", "funcInitFlPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"lineWtAmtRate", "lineWtAmtRate", null, null, TYPE_SEISU_SYOSU, "19", "6"},
	{"funcGenlFlAdjAmt", "funcGenlFlAdjAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcSpecFlAdjAmt", "funcSpecFlAdjAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcFlAdjAmt", "funcFlAdjAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcManFlAdjAmt", "funcManFlAdjAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcCsmpUnitCrAmt", "funcCsmpUnitCrAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcCsmpCrAmt", "funcCsmpCrAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcCsmpFlPrcAmt", "funcCsmpFlPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcSvcCostTrnsfAmt", "funcSvcCostTrnsfAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcWtSvcCostTrnsfAmt", "funcWtSvcCostTrnsfAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcFinalFlPrcAmt", "funcFinalFlPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcUnitListPrcAmt", "funcUnitListPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcNetUnitPrcAmt", "funcNetUnitPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcNetSellPrcAmt", "funcNetSellPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcInitRepRevAmt", "funcInitRepRevAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcGenlRepRevAdjAmt", "funcGenlRepRevAdjAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcSpecRepRevAdjAmt", "funcSpecRepRevAdjAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcRepRevAdjAmt", "funcRepRevAdjAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcManRepRevAdjAmt", "funcManRepRevAdjAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcSvcRevTrnsfAmt", "funcSvcRevTrnsfAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcWtSvcRevTrnsfAmt", "funcWtSvcRevTrnsfAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcFinalRepRevAmt", "funcFinalRepRevAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcUnitMsrpAmt", "funcUnitMsrpAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcUnitStdCostAmt", "funcUnitStdCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcFinalStdCostAmt", "funcFinalStdCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"chngOrdFlg", "chngOrdFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"coaMdseTpCd", "coaMdseTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"coaProdCd", "coaProdCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"mdseItemTpCd", "mdseItemTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"lastPrftCalcUsrId", "lastPrftCalcUsrId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"lastPrftCalcTs", "lastPrftCalcTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"ordPrftRuleTpCd", "ordPrftRuleTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"flPrcCalcInclFlg", "flPrcCalcInclFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"repRevCalcInclFlg", "repRevCalcInclFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"discMdseTpFlg", "discMdseTpFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"redFlPrcFlg", "redFlPrcFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"redRepRevFlg", "redRepRevFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"discAllocMethCd", "discAllocMethCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dlrCrPrftInclFlg", "dlrCrPrftInclFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"redCompAmtFlg", "redCompAmtFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"billToCustCd", "billToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd", "shipToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"ordLineStsCd", "ordLineStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"cpoDtlFuncSlsAmt", "cpoDtlFuncSlsAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"ordCustUomQty", "ordCustUomQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"csmpContrNum", "csmpContrNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"dlrRefNum", "dlrRefNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"coaProjNm", "coaProjNm", null, null, TYPE_HANKAKUEISU, "240", null},
	{"funcFinalUnitFlPrcAmt", "funcFinalUnitFlPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcFinalUnitRevAmt", "funcFinalUnitRevAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcCatgCd", "prcCatgCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"prcCatgNm_P2", "prcCatgNm_P2", "P2", null, TYPE_HANKAKUEISU, "75", null},
	{"funcWtManFlAdjAmt", "funcWtManFlAdjAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcWtManRevAdjAmt", "funcWtManRevAdjAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcBaseDt", "prcBaseDt", null, null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORD_PRFT_TRX_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordPrftTrxCatgCd
        {"TRX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineNum
        {"TRX_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineSubNum
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum
        {"DS_CPO_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoLineNum
        {"DS_CPO_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoLineSubNum
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"MDSE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseNm
        {"ORIG_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origMdseCd
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd
        {"DS_ORD_LINE_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgCd
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty
        {"CUST_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custUomCd
        {"TRX_REF_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRefLineNum
        {"TRX_REF_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRefLineSubNum
        {"FL_PRC_LIST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//flPrcListCd
        {"FL_PRC_LIST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//flPrcListNm
        {"CSMP_PRC_LIST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpPrcListCd
        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm
        {"FUNC_DLR_CR_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcDlrCrAmt
        {"FUNC_RED_COMP_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcRedCompAmt
        {"FUNC_UNIT_FL_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcUnitFlPrcAmt
        {"MDSE_INVTY_COST_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseInvtyCostPct
        {"FUNC_INIT_FL_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcInitFlPrcAmt
        {"LINE_WT_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineWtAmtRate
        {"FUNC_GENL_FL_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcGenlFlAdjAmt
        {"FUNC_SPEC_FL_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcSpecFlAdjAmt
        {"FUNC_FL_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcFlAdjAmt
        {"FUNC_MAN_FL_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcManFlAdjAmt
        {"FUNC_CSMP_UNIT_CR_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcCsmpUnitCrAmt
        {"FUNC_CSMP_CR_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcCsmpCrAmt
        {"FUNC_CSMP_FL_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcCsmpFlPrcAmt
        {"FUNC_SVC_COST_TRNSF_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcSvcCostTrnsfAmt
        {"FUNC_WT_SVC_COST_TRNSF_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcWtSvcCostTrnsfAmt
        {"FUNC_FINAL_FL_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcFinalFlPrcAmt
        {"FUNC_UNIT_LIST_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcUnitListPrcAmt
        {"FUNC_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcNetUnitPrcAmt
        {"FUNC_NET_SELL_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcNetSellPrcAmt
        {"FUNC_INIT_REP_REV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcInitRepRevAmt
        {"FUNC_GENL_REP_REV_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcGenlRepRevAdjAmt
        {"FUNC_SPEC_REP_REV_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcSpecRepRevAdjAmt
        {"FUNC_REP_REV_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcRepRevAdjAmt
        {"FUNC_MAN_REP_REV_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcManRepRevAdjAmt
        {"FUNC_SVC_REV_TRNSF_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcSvcRevTrnsfAmt
        {"FUNC_WT_SVC_REV_TRNSF_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcWtSvcRevTrnsfAmt
        {"FUNC_FINAL_REP_REV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcFinalRepRevAmt
        {"FUNC_UNIT_MSRP_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcUnitMsrpAmt
        {"FUNC_UNIT_STD_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcUnitStdCostAmt
        {"FUNC_FINAL_STD_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcFinalStdCostAmt
        {"CHNG_ORD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//chngOrdFlg
        {"COA_MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd
        {"MDSE_ITEM_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd
        {"LAST_PRFT_CALC_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lastPrftCalcUsrId
        {"LAST_PRFT_CALC_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lastPrftCalcTs
        {"ORD_PRFT_RULE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordPrftRuleTpCd
        {"FL_PRC_CALC_INCL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//flPrcCalcInclFlg
        {"REP_REV_CALC_INCL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//repRevCalcInclFlg
        {"DISC_MDSE_TP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//discMdseTpFlg
        {"RED_FL_PRC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//redFlPrcFlg
        {"RED_REP_REV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//redRepRevFlg
        {"DISC_ALLOC_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//discAllocMethCd
        {"DLR_CR_PRFT_INCL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrCrPrftInclFlg
        {"RED_COMP_AMT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//redCompAmtFlg
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd
        {"ORD_LINE_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLineStsCd
        {"CPO_DTL_FUNC_SLS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlFuncSlsAmt
        {"ORD_CUST_UOM_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordCustUomQty
        {"CSMP_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrNum
        {"DLR_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrRefNum
        {"COA_PROJ_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjNm
        {"FUNC_FINAL_UNIT_FL_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcFinalUnitFlPrcAmt
        {"FUNC_FINAL_UNIT_REV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcFinalUnitRevAmt
        {"PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd
        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm_P2
        {"FUNC_WT_MAN_FL_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcWtManFlAdjAmt
        {"FUNC_WT_MAN_REV_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcWtManRevAdjAmt
        {"PRC_BASE_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcBaseDt
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
