//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20161018181327000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC156001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NWZC156001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** XX_MODE_CD*/
	public final EZDPStringItem              xxModeCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** TRX_HDR_NUM*/
	public final EZDPStringItem              trxHdrNum;

    /** DS_ORD_CATG_CD*/
	public final EZDPStringItem              dsOrdCatgCd;

    /** DS_ORD_TP_CD*/
	public final EZDPStringItem              dsOrdTpCd;

    /** DS_ORD_RSN_CD*/
	public final EZDPStringItem              dsOrdRsnCd;

    /** PRC_BASE_DT*/
	public final EZDPDateItem              prcBaseDt;

    /** PRC_CALC_DT*/
	public final EZDPDateItem              prcCalcDt;

    /** ORD_PRFT_VRSN_NUM*/
	public final EZDPBigDecimalItem              ordPrftVrsnNum;

    /** FUNC_NEGO_DEAL_AMT*/
	public final EZDPBigDecimalItem              funcNegoDealAmt;

    /** TOT_FUNC_REP_REV_AMT*/
	public final EZDPBigDecimalItem              totFuncRepRevAmt;

    /** TOT_FUNC_REP_REV_ADJ_AMT*/
	public final EZDPBigDecimalItem              totFuncRepRevAdjAmt;

    /** TOT_FUNC_FINAL_FL_AMT*/
	public final EZDPBigDecimalItem              totFuncFinalFlAmt;

    /** XX_ORD_PRFT_FNDR_FEE_AMT*/
	public final EZDPBigDecimalItem              xxOrdPrftFndrFeeAmt;

    /** XX_ORD_PRFT_ISTL_CR_AMT*/
	public final EZDPBigDecimalItem              xxOrdPrftIstlCrAmt;

    /** FUNC_GRS_PRFT_AMT*/
	public final EZDPBigDecimalItem              funcGrsPrftAmt;

    /** GRS_PRFT_PCT*/
	public final EZDPBigDecimalItem              grsPrftPct;

    /** TOT_FUNC_MSRP_AMT*/
	public final EZDPBigDecimalItem              totFuncMsrpAmt;

    /** TOT_FUNC_STD_FL_AMT*/
	public final EZDPBigDecimalItem              totFuncStdFlAmt;

    /** TOT_FUNC_FL_ADJ_AMT*/
	public final EZDPBigDecimalItem              totFuncFlAdjAmt;

    /** CSMP_ORD_FLG*/
	public final EZDPStringItem              csmpOrdFlg;

    /** CSMP_CONTR_NUM*/
	public final EZDPStringItem              csmpContrNum;

    /** DLR_REF_NUM*/
	public final EZDPStringItem              dlrRefNum;

    /** CSMP_CONTR_START_DT*/
	public final EZDPDateItem              csmpContrStartDt;

    /** CSMP_CONTR_END_DT*/
	public final EZDPDateItem              csmpContrEndDt;

    /** TOT_FUNC_CSMP_CR_AMT*/
	public final EZDPBigDecimalItem              totFuncCsmpCrAmt;

    /** TOT_FUNC_CSMP_FL_AMT*/
	public final EZDPBigDecimalItem              totFuncCsmpFlAmt;

    /** LAST_PRFT_CALC_USR_ID*/
	public final EZDPStringItem              lastPrftCalcUsrId;

    /** LAST_PRFT_CALC_TS*/
	public final EZDPStringItem              lastPrftCalcTs;

    /** TOT_FUNC_BYOT_AMT*/
	public final EZDPBigDecimalItem              totFuncByotAmt;

    /** TOT_FUNC_SVC_REV_TRNSF_AMT*/
	public final EZDPBigDecimalItem              totFuncSvcRevTrnsfAmt;

    /** TOT_FUNC_SVC_COST_TRNSF_AMT*/
	public final EZDPBigDecimalItem              totFuncSvcCostTrnsfAmt;

    /** TOT_FUNC_PRO_SVC_AMT*/
	public final EZDPBigDecimalItem              totFuncProSvcAmt;

    /** XX_ORD_PRFT_SVC_AMT*/
	public final EZDPBigDecimalItem              xxOrdPrftSvcAmt;

    /** XX_ORD_PRFT_SPLY_AMT*/
	public final EZDPBigDecimalItem              xxOrdPrftSplyAmt;

    /** TOT_FUNC_OM_MAINT_BLLBL_AMT*/
	public final EZDPBigDecimalItem              totFuncOmMaintBllblAmt;

    /** FUNC_ALT_GRS_PRFT_AMT*/
	public final EZDPBigDecimalItem              funcAltGrsPrftAmt;

    /** ALT_GRS_PRFT_PCT*/
	public final EZDPBigDecimalItem              altGrsPrftPct;

    /** TOT_FUNC_DLR_CR_AMT*/
	public final EZDPBigDecimalItem              totFuncDlrCrAmt;

    /** TOT_FUNC_DLR_INV_AMT*/
	public final EZDPBigDecimalItem              totFuncDlrInvAmt;

    /** TOT_FUNC_RED_COMP_AMT*/
	public final EZDPBigDecimalItem              totFuncRedCompAmt;

    /** DS_ACCT_NUM*/
	public final EZDPStringItem              dsAcctNum;

    /** svcConfigRef*/
	public final business.parts.NWZC156001_svcConfigRefPMsgArray              svcConfigRef;

    /** xxMsgIdList*/
	public final business.parts.NWZC156001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NWZC156001PMsg is constructor.
	 * The initialization when the instance of NWZC156001PMsg is generated.
	 */
	public NWZC156001PMsg() {
		this(false, -1);
	}

	/**
	 * NWZC156001PMsg is constructor.
	 * The initialization when the instance of NWZC156001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC156001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		xxModeCd = (EZDPStringItem)newItem("xxModeCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		trxHdrNum = (EZDPStringItem)newItem("trxHdrNum");
		dsOrdCatgCd = (EZDPStringItem)newItem("dsOrdCatgCd");
		dsOrdTpCd = (EZDPStringItem)newItem("dsOrdTpCd");
		dsOrdRsnCd = (EZDPStringItem)newItem("dsOrdRsnCd");
		prcBaseDt = (EZDPDateItem)newItem("prcBaseDt");
		prcCalcDt = (EZDPDateItem)newItem("prcCalcDt");
		ordPrftVrsnNum = (EZDPBigDecimalItem)newItem("ordPrftVrsnNum");
		funcNegoDealAmt = (EZDPBigDecimalItem)newItem("funcNegoDealAmt");
		totFuncRepRevAmt = (EZDPBigDecimalItem)newItem("totFuncRepRevAmt");
		totFuncRepRevAdjAmt = (EZDPBigDecimalItem)newItem("totFuncRepRevAdjAmt");
		totFuncFinalFlAmt = (EZDPBigDecimalItem)newItem("totFuncFinalFlAmt");
		xxOrdPrftFndrFeeAmt = (EZDPBigDecimalItem)newItem("xxOrdPrftFndrFeeAmt");
		xxOrdPrftIstlCrAmt = (EZDPBigDecimalItem)newItem("xxOrdPrftIstlCrAmt");
		funcGrsPrftAmt = (EZDPBigDecimalItem)newItem("funcGrsPrftAmt");
		grsPrftPct = (EZDPBigDecimalItem)newItem("grsPrftPct");
		totFuncMsrpAmt = (EZDPBigDecimalItem)newItem("totFuncMsrpAmt");
		totFuncStdFlAmt = (EZDPBigDecimalItem)newItem("totFuncStdFlAmt");
		totFuncFlAdjAmt = (EZDPBigDecimalItem)newItem("totFuncFlAdjAmt");
		csmpOrdFlg = (EZDPStringItem)newItem("csmpOrdFlg");
		csmpContrNum = (EZDPStringItem)newItem("csmpContrNum");
		dlrRefNum = (EZDPStringItem)newItem("dlrRefNum");
		csmpContrStartDt = (EZDPDateItem)newItem("csmpContrStartDt");
		csmpContrEndDt = (EZDPDateItem)newItem("csmpContrEndDt");
		totFuncCsmpCrAmt = (EZDPBigDecimalItem)newItem("totFuncCsmpCrAmt");
		totFuncCsmpFlAmt = (EZDPBigDecimalItem)newItem("totFuncCsmpFlAmt");
		lastPrftCalcUsrId = (EZDPStringItem)newItem("lastPrftCalcUsrId");
		lastPrftCalcTs = (EZDPStringItem)newItem("lastPrftCalcTs");
		totFuncByotAmt = (EZDPBigDecimalItem)newItem("totFuncByotAmt");
		totFuncSvcRevTrnsfAmt = (EZDPBigDecimalItem)newItem("totFuncSvcRevTrnsfAmt");
		totFuncSvcCostTrnsfAmt = (EZDPBigDecimalItem)newItem("totFuncSvcCostTrnsfAmt");
		totFuncProSvcAmt = (EZDPBigDecimalItem)newItem("totFuncProSvcAmt");
		xxOrdPrftSvcAmt = (EZDPBigDecimalItem)newItem("xxOrdPrftSvcAmt");
		xxOrdPrftSplyAmt = (EZDPBigDecimalItem)newItem("xxOrdPrftSplyAmt");
		totFuncOmMaintBllblAmt = (EZDPBigDecimalItem)newItem("totFuncOmMaintBllblAmt");
		funcAltGrsPrftAmt = (EZDPBigDecimalItem)newItem("funcAltGrsPrftAmt");
		altGrsPrftPct = (EZDPBigDecimalItem)newItem("altGrsPrftPct");
		totFuncDlrCrAmt = (EZDPBigDecimalItem)newItem("totFuncDlrCrAmt");
		totFuncDlrInvAmt = (EZDPBigDecimalItem)newItem("totFuncDlrInvAmt");
		totFuncRedCompAmt = (EZDPBigDecimalItem)newItem("totFuncRedCompAmt");
		dsAcctNum = (EZDPStringItem)newItem("dsAcctNum");
		svcConfigRef = (business.parts.NWZC156001_svcConfigRefPMsgArray)newMsgArray("svcConfigRef");
		xxMsgIdList = (business.parts.NWZC156001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC156001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC156001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"trxHdrNum", "trxHdrNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"dsOrdCatgCd", "dsOrdCatgCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdTpCd", "dsOrdTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdRsnCd", "dsOrdRsnCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"prcBaseDt", "prcBaseDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"prcCalcDt", "prcCalcDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"ordPrftVrsnNum", "ordPrftVrsnNum", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"funcNegoDealAmt", "funcNegoDealAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncRepRevAmt", "totFuncRepRevAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncRepRevAdjAmt", "totFuncRepRevAdjAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncFinalFlAmt", "totFuncFinalFlAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxOrdPrftFndrFeeAmt", "xxOrdPrftFndrFeeAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxOrdPrftIstlCrAmt", "xxOrdPrftIstlCrAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcGrsPrftAmt", "funcGrsPrftAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"grsPrftPct", "grsPrftPct", null, null, TYPE_SEISU_SYOSU, "19", "2"},
	{"totFuncMsrpAmt", "totFuncMsrpAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncStdFlAmt", "totFuncStdFlAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncFlAdjAmt", "totFuncFlAdjAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"csmpOrdFlg", "csmpOrdFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"csmpContrNum", "csmpContrNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"dlrRefNum", "dlrRefNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"csmpContrStartDt", "csmpContrStartDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"csmpContrEndDt", "csmpContrEndDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"totFuncCsmpCrAmt", "totFuncCsmpCrAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncCsmpFlAmt", "totFuncCsmpFlAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"lastPrftCalcUsrId", "lastPrftCalcUsrId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"lastPrftCalcTs", "lastPrftCalcTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"totFuncByotAmt", "totFuncByotAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncSvcRevTrnsfAmt", "totFuncSvcRevTrnsfAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncSvcCostTrnsfAmt", "totFuncSvcCostTrnsfAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncProSvcAmt", "totFuncProSvcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxOrdPrftSvcAmt", "xxOrdPrftSvcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxOrdPrftSplyAmt", "xxOrdPrftSplyAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncOmMaintBllblAmt", "totFuncOmMaintBllblAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcAltGrsPrftAmt", "funcAltGrsPrftAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"altGrsPrftPct", "altGrsPrftPct", null, null, TYPE_SEISU_SYOSU, "19", "2"},
	{"totFuncDlrCrAmt", "totFuncDlrCrAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncDlrInvAmt", "totFuncDlrInvAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncRedCompAmt", "totFuncRedCompAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsAcctNum", "dsAcctNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"svcConfigRef", "svcConfigRef", null, "3000", "business.parts.NWZC156001_svcConfigRefPMsgArray", null, null},
	
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NWZC156001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"TRX_HDR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNum
        {"DS_ORD_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd
        {"DS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd
        {"DS_ORD_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdRsnCd
        {"PRC_BASE_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcBaseDt
        {"PRC_CALC_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCalcDt
        {"ORD_PRFT_VRSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordPrftVrsnNum
        {"FUNC_NEGO_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcNegoDealAmt
        {"TOT_FUNC_REP_REV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFuncRepRevAmt
        {"TOT_FUNC_REP_REV_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFuncRepRevAdjAmt
        {"TOT_FUNC_FINAL_FL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFuncFinalFlAmt
        {"XX_ORD_PRFT_FNDR_FEE_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdPrftFndrFeeAmt
        {"XX_ORD_PRFT_ISTL_CR_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdPrftIstlCrAmt
        {"FUNC_GRS_PRFT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcGrsPrftAmt
        {"GRS_PRFT_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//grsPrftPct
        {"TOT_FUNC_MSRP_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFuncMsrpAmt
        {"TOT_FUNC_STD_FL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFuncStdFlAmt
        {"TOT_FUNC_FL_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFuncFlAdjAmt
        {"CSMP_ORD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpOrdFlg
        {"CSMP_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrNum
        {"DLR_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrRefNum
        {"CSMP_CONTR_START_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrStartDt
        {"CSMP_CONTR_END_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrEndDt
        {"TOT_FUNC_CSMP_CR_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFuncCsmpCrAmt
        {"TOT_FUNC_CSMP_FL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFuncCsmpFlAmt
        {"LAST_PRFT_CALC_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lastPrftCalcUsrId
        {"LAST_PRFT_CALC_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lastPrftCalcTs
        {"TOT_FUNC_BYOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFuncByotAmt
        {"TOT_FUNC_SVC_REV_TRNSF_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFuncSvcRevTrnsfAmt
        {"TOT_FUNC_SVC_COST_TRNSF_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFuncSvcCostTrnsfAmt
        {"TOT_FUNC_PRO_SVC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFuncProSvcAmt
        {"XX_ORD_PRFT_SVC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdPrftSvcAmt
        {"XX_ORD_PRFT_SPLY_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdPrftSplyAmt
        {"TOT_FUNC_OM_MAINT_BLLBL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFuncOmMaintBllblAmt
        {"FUNC_ALT_GRS_PRFT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcAltGrsPrftAmt
        {"ALT_GRS_PRFT_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//altGrsPrftPct
        {"TOT_FUNC_DLR_CR_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFuncDlrCrAmt
        {"TOT_FUNC_DLR_INV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFuncDlrInvAmt
        {"TOT_FUNC_RED_COMP_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFuncRedCompAmt
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum
		null,	//svcConfigRef
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

