//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161018162428000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1540BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1540;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1540 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1540BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_MODE_CD_IN*/
	public final EZDBStringItem              xxModeCd_IN;

    /** TRX_HDR_NUM_IN*/
	public final EZDBStringItem              trxHdrNum_IN;

    /** XX_MODE_CD*/
	public final EZDBStringItem              xxModeCd;

    /** XX_MODE_CD_CD*/
	public final EZDBStringItemArray              xxModeCd_CD;

    /** XX_SCR_ITEM_8_TXT_NM*/
	public final EZDBStringItemArray              xxScrItem8Txt_NM;

    /** TRX_HDR_NUM*/
	public final EZDBStringItem              trxHdrNum;

    /** ORD_PRFT_VRSN_NUM*/
	public final EZDBBigDecimalItem              ordPrftVrsnNum;

    /** ORD_PRFT_VRSN_NUM_CD*/
	public final EZDBBigDecimalItemArray              ordPrftVrsnNum_CD;

    /** XX_SCR_ITEM_13_TXT_NM*/
	public final EZDBStringItemArray              xxScrItem13Txt_NM;

    /** FUNC_NEGO_DEAL_AMT*/
	public final EZDBBigDecimalItem              funcNegoDealAmt;

    /** TOT_INV_AMT*/
	public final EZDBBigDecimalItem              totInvAmt;

    /** TOT_FUNC_REP_REV_AMT*/
	public final EZDBBigDecimalItem              totFuncRepRevAmt;

    /** TOT_FUNC_FINAL_FL_AMT*/
	public final EZDBBigDecimalItem              totFuncFinalFlAmt;

    /** TOT_FUNC_REP_REV_ADJ_AMT*/
	public final EZDBBigDecimalItem              totFuncRepRevAdjAmt;

    /** TOT_FUNC_DLR_CR_AMT*/
	public final EZDBBigDecimalItem              totFuncDlrCrAmt;

    /** LAST_PRFT_CALC_USR_ID*/
	public final EZDBStringItem              lastPrftCalcUsrId;

    /** PSN_LAST_NM*/
	public final EZDBStringItem              psnLastNm;

    /** PSN_FIRST_NM*/
	public final EZDBStringItem              psnFirstNm;

    /** LAST_PRFT_CALC_TS*/
	public final EZDBStringItem              lastPrftCalcTs;

    /** XX_SCR_ITEM_81_TXT*/
	public final EZDBStringItem              xxScrItem81Txt;

    /** XX_SCR_ITEM_19_TXT*/
	public final EZDBStringItem              xxScrItem19Txt;

    /** GRS_PRFT_PCT*/
	public final EZDBBigDecimalItem              grsPrftPct;

    /** FUNC_GRS_PRFT_AMT*/
	public final EZDBBigDecimalItem              funcGrsPrftAmt;

    /** XX_ORD_PRFT_FNDR_FEE_AMT*/
	public final EZDBBigDecimalItem              xxOrdPrftFndrFeeAmt;

    /** XX_ORD_PRFT_ISTL_CR_AMT*/
	public final EZDBBigDecimalItem              xxOrdPrftIstlCrAmt;

    /** FUNC_ALT_GRS_PRFT_AMT*/
	public final EZDBBigDecimalItem              funcAltGrsPrftAmt;

    /** ALT_GRS_PRFT_PCT*/
	public final EZDBBigDecimalItem              altGrsPrftPct;

    /** FUNC_ALT_GRS_PRFT_AMT_RE*/
	public final EZDBBigDecimalItem              funcAltGrsPrftAmt_RE;

    /** ALT_GRS_PRFT_PCT_RE*/
	public final EZDBBigDecimalItem              altGrsPrftPct_RE;

    /** FUNC_ALT_GRS_PRFT_AMT_MV*/
	public final EZDBBigDecimalItem              funcAltGrsPrftAmt_MV;

    /** ALT_GRS_PRFT_PCT_MV*/
	public final EZDBBigDecimalItem              altGrsPrftPct_MV;

    /** TOT_FUNC_MSRP_AMT*/
	public final EZDBBigDecimalItem              totFuncMsrpAmt;

    /** TOT_FUNC_STD_FL_AMT*/
	public final EZDBBigDecimalItem              totFuncStdFlAmt;

    /** TOT_FUNC_FL_ADJ_AMT*/
	public final EZDBBigDecimalItem              totFuncFlAdjAmt;

    /** TOT_COST_AMT*/
	public final EZDBBigDecimalItem              totCostAmt;

    /** CSMP_ORD_FLG*/
	public final EZDBStringItem              csmpOrdFlg;

    /** TOT_FUNC_CSMP_CR_AMT*/
	public final EZDBBigDecimalItem              totFuncCsmpCrAmt;

    /** CSMP_CONTR_NUM*/
	public final EZDBStringItem              csmpContrNum;

    /** TOT_FUNC_BYOT_AMT*/
	public final EZDBBigDecimalItem              totFuncByotAmt;

    /** TOT_FUNC_SVC_REV_TRNSF_AMT*/
	public final EZDBBigDecimalItem              totFuncSvcRevTrnsfAmt;

    /** TOT_FUNC_SVC_COST_TRNSF_AMT*/
	public final EZDBBigDecimalItem              totFuncSvcCostTrnsfAmt;

    /** TOT_FUNC_PRO_SVC_AMT*/
	public final EZDBBigDecimalItem              totFuncProSvcAmt;

    /** XX_ORD_PRFT_SVC_AMT*/
	public final EZDBBigDecimalItem              xxOrdPrftSvcAmt;

    /** XX_ORD_PRFT_SPLY_AMT*/
	public final EZDBBigDecimalItem              xxOrdPrftSplyAmt;

    /** ORD_HDR_STS_CD*/
	public final EZDBStringItem              ordHdrStsCd;

    /** CR_REBIL_CD*/
	public final EZDBStringItem              crRebilCd;

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

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDBStringItem              xxComnColOrdTxt;

    /** A*/
	public final business.servlet.NWAL1540.NWAL1540_ABMsgArray              A;


	/**
	 * NWAL1540BMsg is constructor.
	 * The initialization when the instance of NWAL1540BMsg is generated.
	 */
	public NWAL1540BMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1540BMsg is constructor.
	 * The initialization when the instance of NWAL1540BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1540BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxModeCd_IN = (EZDBStringItem)newItem("xxModeCd_IN");
		trxHdrNum_IN = (EZDBStringItem)newItem("trxHdrNum_IN");
		xxModeCd = (EZDBStringItem)newItem("xxModeCd");
		xxModeCd_CD = (EZDBStringItemArray)newItemArray("xxModeCd_CD");
		xxScrItem8Txt_NM = (EZDBStringItemArray)newItemArray("xxScrItem8Txt_NM");
		trxHdrNum = (EZDBStringItem)newItem("trxHdrNum");
		ordPrftVrsnNum = (EZDBBigDecimalItem)newItem("ordPrftVrsnNum");
		ordPrftVrsnNum_CD = (EZDBBigDecimalItemArray)newItemArray("ordPrftVrsnNum_CD");
		xxScrItem13Txt_NM = (EZDBStringItemArray)newItemArray("xxScrItem13Txt_NM");
		funcNegoDealAmt = (EZDBBigDecimalItem)newItem("funcNegoDealAmt");
		totInvAmt = (EZDBBigDecimalItem)newItem("totInvAmt");
		totFuncRepRevAmt = (EZDBBigDecimalItem)newItem("totFuncRepRevAmt");
		totFuncFinalFlAmt = (EZDBBigDecimalItem)newItem("totFuncFinalFlAmt");
		totFuncRepRevAdjAmt = (EZDBBigDecimalItem)newItem("totFuncRepRevAdjAmt");
		totFuncDlrCrAmt = (EZDBBigDecimalItem)newItem("totFuncDlrCrAmt");
		lastPrftCalcUsrId = (EZDBStringItem)newItem("lastPrftCalcUsrId");
		psnLastNm = (EZDBStringItem)newItem("psnLastNm");
		psnFirstNm = (EZDBStringItem)newItem("psnFirstNm");
		lastPrftCalcTs = (EZDBStringItem)newItem("lastPrftCalcTs");
		xxScrItem81Txt = (EZDBStringItem)newItem("xxScrItem81Txt");
		xxScrItem19Txt = (EZDBStringItem)newItem("xxScrItem19Txt");
		grsPrftPct = (EZDBBigDecimalItem)newItem("grsPrftPct");
		funcGrsPrftAmt = (EZDBBigDecimalItem)newItem("funcGrsPrftAmt");
		xxOrdPrftFndrFeeAmt = (EZDBBigDecimalItem)newItem("xxOrdPrftFndrFeeAmt");
		xxOrdPrftIstlCrAmt = (EZDBBigDecimalItem)newItem("xxOrdPrftIstlCrAmt");
		funcAltGrsPrftAmt = (EZDBBigDecimalItem)newItem("funcAltGrsPrftAmt");
		altGrsPrftPct = (EZDBBigDecimalItem)newItem("altGrsPrftPct");
		funcAltGrsPrftAmt_RE = (EZDBBigDecimalItem)newItem("funcAltGrsPrftAmt_RE");
		altGrsPrftPct_RE = (EZDBBigDecimalItem)newItem("altGrsPrftPct_RE");
		funcAltGrsPrftAmt_MV = (EZDBBigDecimalItem)newItem("funcAltGrsPrftAmt_MV");
		altGrsPrftPct_MV = (EZDBBigDecimalItem)newItem("altGrsPrftPct_MV");
		totFuncMsrpAmt = (EZDBBigDecimalItem)newItem("totFuncMsrpAmt");
		totFuncStdFlAmt = (EZDBBigDecimalItem)newItem("totFuncStdFlAmt");
		totFuncFlAdjAmt = (EZDBBigDecimalItem)newItem("totFuncFlAdjAmt");
		totCostAmt = (EZDBBigDecimalItem)newItem("totCostAmt");
		csmpOrdFlg = (EZDBStringItem)newItem("csmpOrdFlg");
		totFuncCsmpCrAmt = (EZDBBigDecimalItem)newItem("totFuncCsmpCrAmt");
		csmpContrNum = (EZDBStringItem)newItem("csmpContrNum");
		totFuncByotAmt = (EZDBBigDecimalItem)newItem("totFuncByotAmt");
		totFuncSvcRevTrnsfAmt = (EZDBBigDecimalItem)newItem("totFuncSvcRevTrnsfAmt");
		totFuncSvcCostTrnsfAmt = (EZDBBigDecimalItem)newItem("totFuncSvcCostTrnsfAmt");
		totFuncProSvcAmt = (EZDBBigDecimalItem)newItem("totFuncProSvcAmt");
		xxOrdPrftSvcAmt = (EZDBBigDecimalItem)newItem("xxOrdPrftSvcAmt");
		xxOrdPrftSplyAmt = (EZDBBigDecimalItem)newItem("xxOrdPrftSplyAmt");
		ordHdrStsCd = (EZDBStringItem)newItem("ordHdrStsCd");
		crRebilCd = (EZDBStringItem)newItem("crRebilCd");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		xxComnColOrdTxt = (EZDBStringItem)newItem("xxComnColOrdTxt");
		A = (business.servlet.NWAL1540.NWAL1540_ABMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1540BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1540BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxModeCd_IN", "xxModeCd_IN", "IN", null, TYPE_HANKAKUEISU, "2", null},
	{"trxHdrNum_IN", "trxHdrNum_IN", "IN", null, TYPE_HANKAKUEISU, "8", null},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxModeCd_CD", "xxModeCd_CD", "CD", "99", TYPE_HANKAKUEISU, "2", null},
	{"xxScrItem8Txt_NM", "xxScrItem8Txt_NM", "NM", "99", TYPE_HANKAKUEISU, "8", null},
	{"trxHdrNum", "trxHdrNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"ordPrftVrsnNum", "ordPrftVrsnNum", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"ordPrftVrsnNum_CD", "ordPrftVrsnNum_CD", "CD", "99", TYPE_SEISU_SYOSU, "10", "0"},
	{"xxScrItem13Txt_NM", "xxScrItem13Txt_NM", "NM", "99", TYPE_HANKAKUEISU, "13", null},
	{"funcNegoDealAmt", "funcNegoDealAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totInvAmt", "totInvAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncRepRevAmt", "totFuncRepRevAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncFinalFlAmt", "totFuncFinalFlAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncRepRevAdjAmt", "totFuncRepRevAdjAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncDlrCrAmt", "totFuncDlrCrAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"lastPrftCalcUsrId", "lastPrftCalcUsrId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"psnLastNm", "psnLastNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"psnFirstNm", "psnFirstNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"lastPrftCalcTs", "lastPrftCalcTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxScrItem81Txt", "xxScrItem81Txt", null, null, TYPE_HANKAKUEISU, "81", null},
	{"xxScrItem19Txt", "xxScrItem19Txt", null, null, TYPE_HANKAKUEISU, "19", null},
	{"grsPrftPct", "grsPrftPct", null, null, TYPE_SEISU_SYOSU, "19", "2"},
	{"funcGrsPrftAmt", "funcGrsPrftAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxOrdPrftFndrFeeAmt", "xxOrdPrftFndrFeeAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxOrdPrftIstlCrAmt", "xxOrdPrftIstlCrAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcAltGrsPrftAmt", "funcAltGrsPrftAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"altGrsPrftPct", "altGrsPrftPct", null, null, TYPE_SEISU_SYOSU, "19", "2"},
	{"funcAltGrsPrftAmt_RE", "funcAltGrsPrftAmt_RE", "RE", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"altGrsPrftPct_RE", "altGrsPrftPct_RE", "RE", null, TYPE_SEISU_SYOSU, "19", "2"},
	{"funcAltGrsPrftAmt_MV", "funcAltGrsPrftAmt_MV", "MV", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"altGrsPrftPct_MV", "altGrsPrftPct_MV", "MV", null, TYPE_SEISU_SYOSU, "19", "2"},
	{"totFuncMsrpAmt", "totFuncMsrpAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncStdFlAmt", "totFuncStdFlAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncFlAdjAmt", "totFuncFlAdjAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totCostAmt", "totCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"csmpOrdFlg", "csmpOrdFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"totFuncCsmpCrAmt", "totFuncCsmpCrAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"csmpContrNum", "csmpContrNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"totFuncByotAmt", "totFuncByotAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncSvcRevTrnsfAmt", "totFuncSvcRevTrnsfAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncSvcCostTrnsfAmt", "totFuncSvcCostTrnsfAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totFuncProSvcAmt", "totFuncProSvcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxOrdPrftSvcAmt", "xxOrdPrftSvcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxOrdPrftSplyAmt", "xxOrdPrftSplyAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"ordHdrStsCd", "ordHdrStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"crRebilCd", "crRebilCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"A", "A", null, "100", "business.servlet.NWAL1540.NWAL1540_ABMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_MODE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd_IN
        {"TRX_HDR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNum_IN
        {"XX_MODE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"XX_MODE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd_CD
        {"XX_SCR_ITEM_8_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem8Txt_NM
        {"TRX_HDR_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNum
        {"ORD_PRFT_VRSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordPrftVrsnNum
        {"ORD_PRFT_VRSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordPrftVrsnNum_CD
        {"XX_SCR_ITEM_13_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem13Txt_NM
        {"FUNC_NEGO_DEAL_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcNegoDealAmt
        {"TOT_INV_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//totInvAmt
        {"TOT_FUNC_REP_REV_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//totFuncRepRevAmt
        {"TOT_FUNC_FINAL_FL_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//totFuncFinalFlAmt
        {"TOT_FUNC_REP_REV_ADJ_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//totFuncRepRevAdjAmt
        {"TOT_FUNC_DLR_CR_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//totFuncDlrCrAmt
        {"LAST_PRFT_CALC_USR_ID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lastPrftCalcUsrId
        {"PSN_LAST_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnLastNm
        {"PSN_FIRST_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnFirstNm
        {"LAST_PRFT_CALC_TS",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lastPrftCalcTs
        {"XX_SCR_ITEM_81_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem81Txt
        {"XX_SCR_ITEM_19_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem19Txt
        {"GRS_PRFT_PCT",  NO,  null,null,"0", NO, NO, NO, NO,"19","2",null, null,  NO,  NO},	//grsPrftPct
        {"FUNC_GRS_PRFT_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcGrsPrftAmt
        {"XX_ORD_PRFT_FNDR_FEE_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//xxOrdPrftFndrFeeAmt
        {"XX_ORD_PRFT_ISTL_CR_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//xxOrdPrftIstlCrAmt
        {"FUNC_ALT_GRS_PRFT_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcAltGrsPrftAmt
        {"ALT_GRS_PRFT_PCT",  NO,  null,null,"0", NO,YES, NO, NO,"19","2",null, null,  NO,  NO},	//altGrsPrftPct
        {"FUNC_ALT_GRS_PRFT_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcAltGrsPrftAmt_RE
        {"ALT_GRS_PRFT_PCT",  NO,  null,null,"0", NO,YES, NO, NO,"19","2",null, null,  NO,  NO},	//altGrsPrftPct_RE
        {"FUNC_ALT_GRS_PRFT_AMT",  NO,  null,null,"0", NO, NO, NO, NO,"17","2",null, null,  NO,  NO},	//funcAltGrsPrftAmt_MV
        {"ALT_GRS_PRFT_PCT",  NO,  null,null,"0", NO, NO, NO, NO,"19","2",null, null,  NO,  NO},	//altGrsPrftPct_MV
        {"TOT_FUNC_MSRP_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//totFuncMsrpAmt
        {"TOT_FUNC_STD_FL_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//totFuncStdFlAmt
        {"TOT_FUNC_FL_ADJ_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//totFuncFlAdjAmt
        {"TOT_COST_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//totCostAmt
        {"CSMP_ORD_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpOrdFlg
        {"TOT_FUNC_CSMP_CR_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//totFuncCsmpCrAmt
        {"CSMP_CONTR_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrNum
        {"TOT_FUNC_BYOT_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//totFuncByotAmt
        {"TOT_FUNC_SVC_REV_TRNSF_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//totFuncSvcRevTrnsfAmt
        {"TOT_FUNC_SVC_COST_TRNSF_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//totFuncSvcCostTrnsfAmt
        {"TOT_FUNC_PRO_SVC_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//totFuncProSvcAmt
        {"XX_ORD_PRFT_SVC_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//xxOrdPrftSvcAmt
        {"XX_ORD_PRFT_SPLY_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//xxOrdPrftSplyAmt
        {"ORD_HDR_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordHdrStsCd
        {"CR_REBIL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilCd
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
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

