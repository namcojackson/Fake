//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20220405102155000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0800BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0800;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0800 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0800BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CONTR_INTFC_SRC_TP_CD_HS*/
	public final EZDBStringItem              contrIntfcSrcTpCd_HS;

    /** CONTR_INTFC_SRC_TP_CD_H1*/
	public final EZDBStringItemArray              contrIntfcSrcTpCd_H1;

    /** CONTR_INTFC_SRC_TP_DESC_TXT_H1*/
	public final EZDBStringItemArray              contrIntfcSrcTpDescTxt_H1;

    /** DS_CONTR_CLS_CD_HS*/
	public final EZDBStringItem              dsContrClsCd_HS;

    /** DS_CONTR_CLS_CD_H1*/
	public final EZDBStringItemArray              dsContrClsCd_H1;

    /** DS_CONTR_CLS_DESC_TXT_H1*/
	public final EZDBStringItemArray              dsContrClsDescTxt_H1;

    /** SVC_LINE_BIZ_CD_HS*/
	public final EZDBStringItem              svcLineBizCd_HS;

    /** SVC_LINE_BIZ_CD_H1*/
	public final EZDBStringItemArray              svcLineBizCd_H1;

    /** SVC_LINE_BIZ_DESC_TXT_H1*/
	public final EZDBStringItemArray              svcLineBizDescTxt_H1;

    /** EFF_FROM_DT_H1*/
	public final EZDBDateItem              effFromDt_H1;

    /** ENBL_FLG_H1*/
	public final EZDBStringItem              enblFlg_H1;

    /** LEASE_CMPY_CD_A1*/
	public final EZDBStringItem              leaseCmpyCd_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDBStringItem              dsAcctNm_A1;

    /** MTR_READ_METH_CD_AS*/
	public final EZDBStringItem              mtrReadMethCd_AS;

    /** MTR_READ_METH_CD_A1*/
	public final EZDBStringItemArray              mtrReadMethCd_A1;

    /** MTR_READ_METH_DESC_TXT_A1*/
	public final EZDBStringItemArray              mtrReadMethDescTxt_A1;

    /** DS_CONTR_SLS_CR_TP_CD_AS*/
	public final EZDBStringItem              dsContrSlsCrTpCd_AS;

    /** DS_CONTR_SLS_CR_TP_CD_A1*/
	public final EZDBStringItemArray              dsContrSlsCrTpCd_A1;

    /** DS_CONTR_SLS_CR_TP_DESC_TXT_A1*/
	public final EZDBStringItemArray              dsContrSlsCrTpDescTxt_A1;

    /** CONTR_AUTO_RNW_TP_CD_BS*/
	public final EZDBStringItem              contrAutoRnwTpCd_BS;

    /** CONTR_AUTO_RNW_TP_CD_B1*/
	public final EZDBStringItemArray              contrAutoRnwTpCd_B1;

    /** CONTR_AUTO_RNW_TP_DESC_TXT_B1*/
	public final EZDBStringItemArray              contrAutoRnwTpDescTxt_B1;

    /** RNW_PRC_METH_CD_BS*/
	public final EZDBStringItem              rnwPrcMethCd_BS;

    /** RNW_PRC_METH_CD_B1*/
	public final EZDBStringItemArray              rnwPrcMethCd_B1;

    /** RNW_PRC_METH_DESC_TXT_B1*/
	public final EZDBStringItemArray              rnwPrcMethDescTxt_B1;

    /** BEF_END_RNW_DAYS_AOT_B1*/
	public final EZDBBigDecimalItem              befEndRnwDaysAot_B1;

    /** BASE_PRC_UP_RATIO_B1*/
	public final EZDBBigDecimalItem              basePrcUpRatio_B1;

    /** MTR_PRC_UP_RATIO_B1*/
	public final EZDBBigDecimalItem              mtrPrcUpRatio_B1;

    /** RNW_EXT_MTH_AOT_B1*/
	public final EZDBBigDecimalItem              rnwExtMthAot_B1;

    /** START_READ_VLD_TP_CD_CS*/
	public final EZDBStringItem              startReadVldTpCd_CS;

    /** START_READ_VLD_TP_CD_C1*/
	public final EZDBStringItemArray              startReadVldTpCd_C1;

    /** START_READ_VLD_TP_DESC_TXT_C1*/
	public final EZDBStringItemArray              startReadVldTpDescTxt_C1;

    /** MTR_READ_WIN_DAYS_AOT_C1*/
	public final EZDBBigDecimalItem              mtrReadWinDaysAot_C1;

    /** ISTL_CALL_MTR_READ_USE_FLG_C1*/
	public final EZDBStringItem              istlCallMtrReadUseFlg_C1;

    /** BLLG_ROLL_OVER_RATIO_C1*/
	public final EZDBBigDecimalItem              bllgRollOverRatio_C1;

    /** MTR_READ_WIN_MLY_DAYS_AOT_C1*/
	public final EZDBBigDecimalItem              mtrReadWinMlyDaysAot_C1;

    /** MTR_READ_WIN_OTH_DAYS_AOT_C1*/
	public final EZDBBigDecimalItem              mtrReadWinOthDaysAot_C1;

    /** BLLG_LIMIT_AMT_C1*/
	public final EZDBBigDecimalItem              bllgLimitAmt_C1;

    /** ALLW_DATA_CRCT_FLG_D1*/
	public final EZDBStringItem              allwDataCrctFlg_D1;

    /** MTR_EST_TP_CD_ES*/
	public final EZDBStringItem              mtrEstTpCd_ES;

    /** MTR_EST_TP_CD_E1*/
	public final EZDBStringItemArray              mtrEstTpCd_E1;

    /** MTR_EST_TP_DESC_TXT_E1*/
	public final EZDBStringItemArray              mtrEstTpDescTxt_E1;

    /** BLLG_CYCLE_CD_E2*/
	public final EZDBStringItem              bllgCycleCd_E2;

    /** BLLG_CYCLE_CD_E1*/
	public final EZDBStringItemArray              bllgCycleCd_E1;

    /** BLLG_CYCLE_DESC_TXT_E1*/
	public final EZDBStringItemArray              bllgCycleDescTxt_E1;

    /** BLLG_CYCLE_CD_E4*/
	public final EZDBStringItem              bllgCycleCd_E4;

    /** BLLG_CYCLE_CD_E3*/
	public final EZDBStringItemArray              bllgCycleCd_E3;

    /** BLLG_CYCLE_DESC_TXT_E2*/
	public final EZDBStringItemArray              bllgCycleDescTxt_E2;

    /** DS_INV_TGTR_TP_CD_E1*/
	public final EZDBStringItem              dsInvTgtrTpCd_E1;

    /** DS_INV_TGTR_TP_CD_E2*/
	public final EZDBStringItemArray              dsInvTgtrTpCd_E2;

    /** DS_INV_TGTR_TP_DESC_TXT_E1*/
	public final EZDBStringItemArray              dsInvTgtrTpDescTxt_E1;

    /** PRC_ALLOC_BY_MACH_QTY_FLG_E1*/
	public final EZDBStringItem              prcAllocByMachQtyFlg_E1;

    /** PRINT_DTL_FLG_E1*/
	public final EZDBStringItem              printDtlFlg_E1;

    /** INV_SEPT_BASE_USG_MSTR_FLG_E1*/
	public final EZDBStringItem              invSeptBaseUsgMstrFlg_E1;

    /** CONTR_UPLFT_TP_CD_FS*/
	public final EZDBStringItem              contrUplftTpCd_FS;

    /** CONTR_UPLFT_TP_CD_F1*/
	public final EZDBStringItemArray              contrUplftTpCd_F1;

    /** CONTR_UPLFT_TP_DESC_TXT_F1*/
	public final EZDBStringItemArray              contrUplftTpDescTxt_F1;

    /** UPLFT_PRC_METH_CD_FS*/
	public final EZDBStringItem              uplftPrcMethCd_FS;

    /** UPLFT_PRC_METH_CD_F1*/
	public final EZDBStringItemArray              uplftPrcMethCd_F1;

    /** UPLFT_PRC_METH_DESC_TXT_F1*/
	public final EZDBStringItemArray              uplftPrcMethDescTxt_F1;

    /** BEF_END_UPLFT_DAYS_AOT_F1*/
	public final EZDBBigDecimalItem              befEndUplftDaysAot_F1;

    /** UPLFT_BASE_PRC_UP_RATIO_F1*/
	public final EZDBBigDecimalItem              uplftBasePrcUpRatio_F1;

    /** UPLFT_MTR_PRC_UP_RATIO_F1*/
	public final EZDBBigDecimalItem              uplftMtrPrcUpRatio_F1;

    /** BLLG_THRU_TP_CD_GS*/
	public final EZDBStringItem              bllgThruTpCd_GS;

    /** BLLG_THRU_TP_CD_G1*/
	public final EZDBStringItemArray              bllgThruTpCd_G1;

    /** BLLG_THRU_TP_DESC_TXT_G1*/
	public final EZDBStringItemArray              bllgThruTpDescTxt_G1;

    /** BLLG_THRU_DT_G1*/
	public final EZDBDateItem              bllgThruDt_G1;

    /** CONTR_CLO_DAY_GS*/
	public final EZDBStringItem              contrCloDay_GS;

    /** CONTR_CLO_DAY_G1*/
	public final EZDBStringItemArray              contrCloDay_G1;

    /** XX_EDT_DESC_TXT_G1*/
	public final EZDBStringItemArray              xxEdtDescTxt_G1;

    /** CONTR_BLLG_DAY_GS*/
	public final EZDBStringItem              contrBllgDay_GS;

    /** CONTR_BLLG_DAY_G1*/
	public final EZDBStringItemArray              contrBllgDay_G1;

    /** XX_EDT_DESC_TXT_G2*/
	public final EZDBStringItemArray              xxEdtDescTxt_G2;

    /** BLLG_TMG_TP_CD_GS*/
	public final EZDBStringItem              bllgTmgTpCd_GS;

    /** BLLG_TMG_TP_CD_G1*/
	public final EZDBStringItemArray              bllgTmgTpCd_G1;

    /** BLLG_TMG_TP_DESC_TXT_G1*/
	public final EZDBStringItemArray              bllgTmgTpDescTxt_G1;

    /** STUB_PREP_FROM_TP_CD_IS*/
	public final EZDBStringItem              stubPrepFromTpCd_IS;

    /** STUB_PREP_FROM_TP_CD_I1*/
	public final EZDBStringItemArray              stubPrepFromTpCd_I1;

    /** STUB_PREP_FROM_TP_DESC_TXT_I1*/
	public final EZDBStringItemArray              stubPrepFromTpDescTxt_I1;

    /** STUB_PREP_THRU_TP_CD_IS*/
	public final EZDBStringItem              stubPrepThruTpCd_IS;

    /** STUB_PREP_THRU_TP_CD_I1*/
	public final EZDBStringItemArray              stubPrepThruTpCd_I1;

    /** STUB_PREP_THRU_TP_DESC_TXT_I1*/
	public final EZDBStringItemArray              stubPrepThruTpDescTxt_I1;

    /** AUTO_EFF_FLEET_FLG_I1*/
	public final EZDBStringItem              autoEffFleetFlg_I1;

    /** AUTO_EFF_AGGR_FLG_I1*/
	public final EZDBStringItem              autoEffAggrFlg_I1;

    /** XX_REC_HIST_CRAT_TS*/
	public final EZDBStringItem              xxRecHistCratTs;

    /** XX_REC_HIST_CRAT_BY_NM*/
	public final EZDBStringItem              xxRecHistCratByNm;

    /** XX_REC_HIST_UPD_TS*/
	public final EZDBStringItem              xxRecHistUpdTs;

    /** XX_REC_HIST_UPD_BY_NM*/
	public final EZDBStringItem              xxRecHistUpdByNm;

    /** XX_REC_HIST_TBL_NM*/
	public final EZDBStringItem              xxRecHistTblNm;

    /** XX_FILE_DATA_X1*/
	public final EZDBMimeSourceItem              xxFileData_X1;


	/**
	 * NSAL0800BMsg is constructor.
	 * The initialization when the instance of NSAL0800BMsg is generated.
	 */
	public NSAL0800BMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0800BMsg is constructor.
	 * The initialization when the instance of NSAL0800BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0800BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		contrIntfcSrcTpCd_HS = (EZDBStringItem)newItem("contrIntfcSrcTpCd_HS");
		contrIntfcSrcTpCd_H1 = (EZDBStringItemArray)newItemArray("contrIntfcSrcTpCd_H1");
		contrIntfcSrcTpDescTxt_H1 = (EZDBStringItemArray)newItemArray("contrIntfcSrcTpDescTxt_H1");
		dsContrClsCd_HS = (EZDBStringItem)newItem("dsContrClsCd_HS");
		dsContrClsCd_H1 = (EZDBStringItemArray)newItemArray("dsContrClsCd_H1");
		dsContrClsDescTxt_H1 = (EZDBStringItemArray)newItemArray("dsContrClsDescTxt_H1");
		svcLineBizCd_HS = (EZDBStringItem)newItem("svcLineBizCd_HS");
		svcLineBizCd_H1 = (EZDBStringItemArray)newItemArray("svcLineBizCd_H1");
		svcLineBizDescTxt_H1 = (EZDBStringItemArray)newItemArray("svcLineBizDescTxt_H1");
		effFromDt_H1 = (EZDBDateItem)newItem("effFromDt_H1");
		enblFlg_H1 = (EZDBStringItem)newItem("enblFlg_H1");
		leaseCmpyCd_A1 = (EZDBStringItem)newItem("leaseCmpyCd_A1");
		dsAcctNm_A1 = (EZDBStringItem)newItem("dsAcctNm_A1");
		mtrReadMethCd_AS = (EZDBStringItem)newItem("mtrReadMethCd_AS");
		mtrReadMethCd_A1 = (EZDBStringItemArray)newItemArray("mtrReadMethCd_A1");
		mtrReadMethDescTxt_A1 = (EZDBStringItemArray)newItemArray("mtrReadMethDescTxt_A1");
		dsContrSlsCrTpCd_AS = (EZDBStringItem)newItem("dsContrSlsCrTpCd_AS");
		dsContrSlsCrTpCd_A1 = (EZDBStringItemArray)newItemArray("dsContrSlsCrTpCd_A1");
		dsContrSlsCrTpDescTxt_A1 = (EZDBStringItemArray)newItemArray("dsContrSlsCrTpDescTxt_A1");
		contrAutoRnwTpCd_BS = (EZDBStringItem)newItem("contrAutoRnwTpCd_BS");
		contrAutoRnwTpCd_B1 = (EZDBStringItemArray)newItemArray("contrAutoRnwTpCd_B1");
		contrAutoRnwTpDescTxt_B1 = (EZDBStringItemArray)newItemArray("contrAutoRnwTpDescTxt_B1");
		rnwPrcMethCd_BS = (EZDBStringItem)newItem("rnwPrcMethCd_BS");
		rnwPrcMethCd_B1 = (EZDBStringItemArray)newItemArray("rnwPrcMethCd_B1");
		rnwPrcMethDescTxt_B1 = (EZDBStringItemArray)newItemArray("rnwPrcMethDescTxt_B1");
		befEndRnwDaysAot_B1 = (EZDBBigDecimalItem)newItem("befEndRnwDaysAot_B1");
		basePrcUpRatio_B1 = (EZDBBigDecimalItem)newItem("basePrcUpRatio_B1");
		mtrPrcUpRatio_B1 = (EZDBBigDecimalItem)newItem("mtrPrcUpRatio_B1");
		rnwExtMthAot_B1 = (EZDBBigDecimalItem)newItem("rnwExtMthAot_B1");
		startReadVldTpCd_CS = (EZDBStringItem)newItem("startReadVldTpCd_CS");
		startReadVldTpCd_C1 = (EZDBStringItemArray)newItemArray("startReadVldTpCd_C1");
		startReadVldTpDescTxt_C1 = (EZDBStringItemArray)newItemArray("startReadVldTpDescTxt_C1");
		mtrReadWinDaysAot_C1 = (EZDBBigDecimalItem)newItem("mtrReadWinDaysAot_C1");
		istlCallMtrReadUseFlg_C1 = (EZDBStringItem)newItem("istlCallMtrReadUseFlg_C1");
		bllgRollOverRatio_C1 = (EZDBBigDecimalItem)newItem("bllgRollOverRatio_C1");
		mtrReadWinMlyDaysAot_C1 = (EZDBBigDecimalItem)newItem("mtrReadWinMlyDaysAot_C1");
		mtrReadWinOthDaysAot_C1 = (EZDBBigDecimalItem)newItem("mtrReadWinOthDaysAot_C1");
		bllgLimitAmt_C1 = (EZDBBigDecimalItem)newItem("bllgLimitAmt_C1");
		allwDataCrctFlg_D1 = (EZDBStringItem)newItem("allwDataCrctFlg_D1");
		mtrEstTpCd_ES = (EZDBStringItem)newItem("mtrEstTpCd_ES");
		mtrEstTpCd_E1 = (EZDBStringItemArray)newItemArray("mtrEstTpCd_E1");
		mtrEstTpDescTxt_E1 = (EZDBStringItemArray)newItemArray("mtrEstTpDescTxt_E1");
		bllgCycleCd_E2 = (EZDBStringItem)newItem("bllgCycleCd_E2");
		bllgCycleCd_E1 = (EZDBStringItemArray)newItemArray("bllgCycleCd_E1");
		bllgCycleDescTxt_E1 = (EZDBStringItemArray)newItemArray("bllgCycleDescTxt_E1");
		bllgCycleCd_E4 = (EZDBStringItem)newItem("bllgCycleCd_E4");
		bllgCycleCd_E3 = (EZDBStringItemArray)newItemArray("bllgCycleCd_E3");
		bllgCycleDescTxt_E2 = (EZDBStringItemArray)newItemArray("bllgCycleDescTxt_E2");
		dsInvTgtrTpCd_E1 = (EZDBStringItem)newItem("dsInvTgtrTpCd_E1");
		dsInvTgtrTpCd_E2 = (EZDBStringItemArray)newItemArray("dsInvTgtrTpCd_E2");
		dsInvTgtrTpDescTxt_E1 = (EZDBStringItemArray)newItemArray("dsInvTgtrTpDescTxt_E1");
		prcAllocByMachQtyFlg_E1 = (EZDBStringItem)newItem("prcAllocByMachQtyFlg_E1");
		printDtlFlg_E1 = (EZDBStringItem)newItem("printDtlFlg_E1");
		invSeptBaseUsgMstrFlg_E1 = (EZDBStringItem)newItem("invSeptBaseUsgMstrFlg_E1");
		contrUplftTpCd_FS = (EZDBStringItem)newItem("contrUplftTpCd_FS");
		contrUplftTpCd_F1 = (EZDBStringItemArray)newItemArray("contrUplftTpCd_F1");
		contrUplftTpDescTxt_F1 = (EZDBStringItemArray)newItemArray("contrUplftTpDescTxt_F1");
		uplftPrcMethCd_FS = (EZDBStringItem)newItem("uplftPrcMethCd_FS");
		uplftPrcMethCd_F1 = (EZDBStringItemArray)newItemArray("uplftPrcMethCd_F1");
		uplftPrcMethDescTxt_F1 = (EZDBStringItemArray)newItemArray("uplftPrcMethDescTxt_F1");
		befEndUplftDaysAot_F1 = (EZDBBigDecimalItem)newItem("befEndUplftDaysAot_F1");
		uplftBasePrcUpRatio_F1 = (EZDBBigDecimalItem)newItem("uplftBasePrcUpRatio_F1");
		uplftMtrPrcUpRatio_F1 = (EZDBBigDecimalItem)newItem("uplftMtrPrcUpRatio_F1");
		bllgThruTpCd_GS = (EZDBStringItem)newItem("bllgThruTpCd_GS");
		bllgThruTpCd_G1 = (EZDBStringItemArray)newItemArray("bllgThruTpCd_G1");
		bllgThruTpDescTxt_G1 = (EZDBStringItemArray)newItemArray("bllgThruTpDescTxt_G1");
		bllgThruDt_G1 = (EZDBDateItem)newItem("bllgThruDt_G1");
		contrCloDay_GS = (EZDBStringItem)newItem("contrCloDay_GS");
		contrCloDay_G1 = (EZDBStringItemArray)newItemArray("contrCloDay_G1");
		xxEdtDescTxt_G1 = (EZDBStringItemArray)newItemArray("xxEdtDescTxt_G1");
		contrBllgDay_GS = (EZDBStringItem)newItem("contrBllgDay_GS");
		contrBllgDay_G1 = (EZDBStringItemArray)newItemArray("contrBllgDay_G1");
		xxEdtDescTxt_G2 = (EZDBStringItemArray)newItemArray("xxEdtDescTxt_G2");
		bllgTmgTpCd_GS = (EZDBStringItem)newItem("bllgTmgTpCd_GS");
		bllgTmgTpCd_G1 = (EZDBStringItemArray)newItemArray("bllgTmgTpCd_G1");
		bllgTmgTpDescTxt_G1 = (EZDBStringItemArray)newItemArray("bllgTmgTpDescTxt_G1");
		stubPrepFromTpCd_IS = (EZDBStringItem)newItem("stubPrepFromTpCd_IS");
		stubPrepFromTpCd_I1 = (EZDBStringItemArray)newItemArray("stubPrepFromTpCd_I1");
		stubPrepFromTpDescTxt_I1 = (EZDBStringItemArray)newItemArray("stubPrepFromTpDescTxt_I1");
		stubPrepThruTpCd_IS = (EZDBStringItem)newItem("stubPrepThruTpCd_IS");
		stubPrepThruTpCd_I1 = (EZDBStringItemArray)newItemArray("stubPrepThruTpCd_I1");
		stubPrepThruTpDescTxt_I1 = (EZDBStringItemArray)newItemArray("stubPrepThruTpDescTxt_I1");
		autoEffFleetFlg_I1 = (EZDBStringItem)newItem("autoEffFleetFlg_I1");
		autoEffAggrFlg_I1 = (EZDBStringItem)newItem("autoEffAggrFlg_I1");
		xxRecHistCratTs = (EZDBStringItem)newItem("xxRecHistCratTs");
		xxRecHistCratByNm = (EZDBStringItem)newItem("xxRecHistCratByNm");
		xxRecHistUpdTs = (EZDBStringItem)newItem("xxRecHistUpdTs");
		xxRecHistUpdByNm = (EZDBStringItem)newItem("xxRecHistUpdByNm");
		xxRecHistTblNm = (EZDBStringItem)newItem("xxRecHistTblNm");
		xxFileData_X1 = (EZDBMimeSourceItem)newItem("xxFileData_X1");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0800BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0800BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"contrIntfcSrcTpCd_HS", "contrIntfcSrcTpCd_HS", "HS", null, TYPE_HANKAKUEISU, "5", null},
	{"contrIntfcSrcTpCd_H1", "contrIntfcSrcTpCd_H1", "H1", "99", TYPE_HANKAKUEISU, "5", null},
	{"contrIntfcSrcTpDescTxt_H1", "contrIntfcSrcTpDescTxt_H1", "H1", "99", TYPE_HANKAKUEISU, "50", null},
	{"dsContrClsCd_HS", "dsContrClsCd_HS", "HS", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrClsCd_H1", "dsContrClsCd_H1", "H1", "99", TYPE_HANKAKUEISU, "3", null},
	{"dsContrClsDescTxt_H1", "dsContrClsDescTxt_H1", "H1", "99", TYPE_HANKAKUEISU, "50", null},
	{"svcLineBizCd_HS", "svcLineBizCd_HS", "HS", null, TYPE_HANKAKUEISU, "20", null},
	{"svcLineBizCd_H1", "svcLineBizCd_H1", "H1", "99", TYPE_HANKAKUEISU, "20", null},
	{"svcLineBizDescTxt_H1", "svcLineBizDescTxt_H1", "H1", "99", TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_H1", "effFromDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"enblFlg_H1", "enblFlg_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"leaseCmpyCd_A1", "leaseCmpyCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"mtrReadMethCd_AS", "mtrReadMethCd_AS", "AS", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrReadMethCd_A1", "mtrReadMethCd_A1", "A1", "99", TYPE_HANKAKUEISU, "2", null},
	{"mtrReadMethDescTxt_A1", "mtrReadMethDescTxt_A1", "A1", "99", TYPE_HANKAKUEISU, "50", null},
	{"dsContrSlsCrTpCd_AS", "dsContrSlsCrTpCd_AS", "AS", null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrSlsCrTpCd_A1", "dsContrSlsCrTpCd_A1", "A1", "99", TYPE_HANKAKUEISU, "2", null},
	{"dsContrSlsCrTpDescTxt_A1", "dsContrSlsCrTpDescTxt_A1", "A1", "99", TYPE_HANKAKUEISU, "50", null},
	{"contrAutoRnwTpCd_BS", "contrAutoRnwTpCd_BS", "BS", null, TYPE_HANKAKUEISU, "2", null},
	{"contrAutoRnwTpCd_B1", "contrAutoRnwTpCd_B1", "B1", "99", TYPE_HANKAKUEISU, "2", null},
	{"contrAutoRnwTpDescTxt_B1", "contrAutoRnwTpDescTxt_B1", "B1", "99", TYPE_HANKAKUEISU, "50", null},
	{"rnwPrcMethCd_BS", "rnwPrcMethCd_BS", "BS", null, TYPE_HANKAKUEISU, "2", null},
	{"rnwPrcMethCd_B1", "rnwPrcMethCd_B1", "B1", "99", TYPE_HANKAKUEISU, "2", null},
	{"rnwPrcMethDescTxt_B1", "rnwPrcMethDescTxt_B1", "B1", "99", TYPE_HANKAKUEISU, "50", null},
	{"befEndRnwDaysAot_B1", "befEndRnwDaysAot_B1", "B1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"basePrcUpRatio_B1", "basePrcUpRatio_B1", "B1", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"mtrPrcUpRatio_B1", "mtrPrcUpRatio_B1", "B1", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"rnwExtMthAot_B1", "rnwExtMthAot_B1", "B1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"startReadVldTpCd_CS", "startReadVldTpCd_CS", "CS", null, TYPE_HANKAKUEISU, "2", null},
	{"startReadVldTpCd_C1", "startReadVldTpCd_C1", "C1", "99", TYPE_HANKAKUEISU, "2", null},
	{"startReadVldTpDescTxt_C1", "startReadVldTpDescTxt_C1", "C1", "99", TYPE_HANKAKUEISU, "50", null},
	{"mtrReadWinDaysAot_C1", "mtrReadWinDaysAot_C1", "C1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"istlCallMtrReadUseFlg_C1", "istlCallMtrReadUseFlg_C1", "C1", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgRollOverRatio_C1", "bllgRollOverRatio_C1", "C1", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"mtrReadWinMlyDaysAot_C1", "mtrReadWinMlyDaysAot_C1", "C1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"mtrReadWinOthDaysAot_C1", "mtrReadWinOthDaysAot_C1", "C1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"bllgLimitAmt_C1", "bllgLimitAmt_C1", "C1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"allwDataCrctFlg_D1", "allwDataCrctFlg_D1", "D1", null, TYPE_HANKAKUEISU, "1", null},
	{"mtrEstTpCd_ES", "mtrEstTpCd_ES", "ES", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrEstTpCd_E1", "mtrEstTpCd_E1", "E1", "99", TYPE_HANKAKUEISU, "2", null},
	{"mtrEstTpDescTxt_E1", "mtrEstTpDescTxt_E1", "E1", "99", TYPE_HANKAKUEISU, "50", null},
	{"bllgCycleCd_E2", "bllgCycleCd_E2", "E2", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleCd_E1", "bllgCycleCd_E1", "E1", "99", TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleDescTxt_E1", "bllgCycleDescTxt_E1", "E1", "99", TYPE_HANKAKUEISU, "50", null},
	{"bllgCycleCd_E4", "bllgCycleCd_E4", "E4", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleCd_E3", "bllgCycleCd_E3", "E3", "99", TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleDescTxt_E2", "bllgCycleDescTxt_E2", "E2", "99", TYPE_HANKAKUEISU, "50", null},
	{"dsInvTgtrTpCd_E1", "dsInvTgtrTpCd_E1", "E1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsInvTgtrTpCd_E2", "dsInvTgtrTpCd_E2", "E2", "99", TYPE_HANKAKUEISU, "2", null},
	{"dsInvTgtrTpDescTxt_E1", "dsInvTgtrTpDescTxt_E1", "E1", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcAllocByMachQtyFlg_E1", "prcAllocByMachQtyFlg_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"printDtlFlg_E1", "printDtlFlg_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"invSeptBaseUsgMstrFlg_E1", "invSeptBaseUsgMstrFlg_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"contrUplftTpCd_FS", "contrUplftTpCd_FS", "FS", null, TYPE_HANKAKUEISU, "2", null},
	{"contrUplftTpCd_F1", "contrUplftTpCd_F1", "F1", "99", TYPE_HANKAKUEISU, "2", null},
	{"contrUplftTpDescTxt_F1", "contrUplftTpDescTxt_F1", "F1", "99", TYPE_HANKAKUEISU, "50", null},
	{"uplftPrcMethCd_FS", "uplftPrcMethCd_FS", "FS", null, TYPE_HANKAKUEISU, "2", null},
	{"uplftPrcMethCd_F1", "uplftPrcMethCd_F1", "F1", "99", TYPE_HANKAKUEISU, "2", null},
	{"uplftPrcMethDescTxt_F1", "uplftPrcMethDescTxt_F1", "F1", "99", TYPE_HANKAKUEISU, "50", null},
	{"befEndUplftDaysAot_F1", "befEndUplftDaysAot_F1", "F1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"uplftBasePrcUpRatio_F1", "uplftBasePrcUpRatio_F1", "F1", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"uplftMtrPrcUpRatio_F1", "uplftMtrPrcUpRatio_F1", "F1", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"bllgThruTpCd_GS", "bllgThruTpCd_GS", "GS", null, TYPE_HANKAKUEISU, "2", null},
	{"bllgThruTpCd_G1", "bllgThruTpCd_G1", "G1", "99", TYPE_HANKAKUEISU, "2", null},
	{"bllgThruTpDescTxt_G1", "bllgThruTpDescTxt_G1", "G1", "99", TYPE_HANKAKUEISU, "50", null},
	{"bllgThruDt_G1", "bllgThruDt_G1", "G1", null, TYPE_NENTSUKIHI, "8", null},
	{"contrCloDay_GS", "contrCloDay_GS", "GS", null, TYPE_HANKAKUSUJI, "2", null},
	{"contrCloDay_G1", "contrCloDay_G1", "G1", "99", TYPE_HANKAKUSUJI, "2", null},
	{"xxEdtDescTxt_G1", "xxEdtDescTxt_G1", "G1", "99", TYPE_HANKAKUEISU, "30", null},
	{"contrBllgDay_GS", "contrBllgDay_GS", "GS", null, TYPE_HANKAKUEISU, "4", null},
	{"contrBllgDay_G1", "contrBllgDay_G1", "G1", "99", TYPE_HANKAKUEISU, "4", null},
	{"xxEdtDescTxt_G2", "xxEdtDescTxt_G2", "G2", "99", TYPE_HANKAKUEISU, "30", null},
	{"bllgTmgTpCd_GS", "bllgTmgTpCd_GS", "GS", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgTmgTpCd_G1", "bllgTmgTpCd_G1", "G1", "99", TYPE_HANKAKUEISU, "1", null},
	{"bllgTmgTpDescTxt_G1", "bllgTmgTpDescTxt_G1", "G1", "99", TYPE_HANKAKUEISU, "50", null},
	{"stubPrepFromTpCd_IS", "stubPrepFromTpCd_IS", "IS", null, TYPE_HANKAKUEISU, "2", null},
	{"stubPrepFromTpCd_I1", "stubPrepFromTpCd_I1", "I1", "99", TYPE_HANKAKUEISU, "2", null},
	{"stubPrepFromTpDescTxt_I1", "stubPrepFromTpDescTxt_I1", "I1", "99", TYPE_HANKAKUEISU, "50", null},
	{"stubPrepThruTpCd_IS", "stubPrepThruTpCd_IS", "IS", null, TYPE_HANKAKUEISU, "2", null},
	{"stubPrepThruTpCd_I1", "stubPrepThruTpCd_I1", "I1", "99", TYPE_HANKAKUEISU, "2", null},
	{"stubPrepThruTpDescTxt_I1", "stubPrepThruTpDescTxt_I1", "I1", "99", TYPE_HANKAKUEISU, "50", null},
	{"autoEffFleetFlg_I1", "autoEffFleetFlg_I1", "I1", null, TYPE_HANKAKUEISU, "1", null},
	{"autoEffAggrFlg_I1", "autoEffAggrFlg_I1", "I1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxRecHistCratTs", "xxRecHistCratTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm", "xxRecHistCratByNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs", "xxRecHistUpdTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm", "xxRecHistUpdByNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm", "xxRecHistTblNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"xxFileData_X1", "xxFileData_X1", "X1", null, TYPE_UPLOAD, null, null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CONTR_INTFC_SRC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrIntfcSrcTpCd_HS
        {"CONTR_INTFC_SRC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrIntfcSrcTpCd_H1
        {"CONTR_INTFC_SRC_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrIntfcSrcTpDescTxt_H1
        {"DS_CONTR_CLS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrClsCd_HS
        {"DS_CONTR_CLS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrClsCd_H1
        {"DS_CONTR_CLS_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrClsDescTxt_H1
        {"SVC_LINE_BIZ_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLineBizCd_HS
        {"SVC_LINE_BIZ_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLineBizCd_H1
        {"SVC_LINE_BIZ_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLineBizDescTxt_H1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_H1
        {"ENBL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//enblFlg_H1
        {"LEASE_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leaseCmpyCd_A1
        {"DS_ACCT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"MTR_READ_METH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd_AS
        {"MTR_READ_METH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd_A1
        {"MTR_READ_METH_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethDescTxt_A1
        {"DS_CONTR_SLS_CR_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrSlsCrTpCd_AS
        {"DS_CONTR_SLS_CR_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrSlsCrTpCd_A1
        {"DS_CONTR_SLS_CR_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrSlsCrTpDescTxt_A1
        {"CONTR_AUTO_RNW_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrAutoRnwTpCd_BS
        {"CONTR_AUTO_RNW_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrAutoRnwTpCd_B1
        {"CONTR_AUTO_RNW_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrAutoRnwTpDescTxt_B1
        {"RNW_PRC_METH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwPrcMethCd_BS
        {"RNW_PRC_METH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwPrcMethCd_B1
        {"RNW_PRC_METH_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwPrcMethDescTxt_B1
        {"BEF_END_RNW_DAYS_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befEndRnwDaysAot_B1
        {"BASE_PRC_UP_RATIO",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcUpRatio_B1
        {"MTR_PRC_UP_RATIO",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrPrcUpRatio_B1
        {"RNW_EXT_MTH_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwExtMthAot_B1
        {"START_READ_VLD_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//startReadVldTpCd_CS
        {"START_READ_VLD_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//startReadVldTpCd_C1
        {"START_READ_VLD_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//startReadVldTpDescTxt_C1
        {"MTR_READ_WIN_DAYS_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadWinDaysAot_C1
        {"ISTL_CALL_MTR_READ_USE_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//istlCallMtrReadUseFlg_C1
        {"BLLG_ROLL_OVER_RATIO",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgRollOverRatio_C1
        {"MTR_READ_WIN_MLY_DAYS_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadWinMlyDaysAot_C1
        {"MTR_READ_WIN_OTH_DAYS_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadWinOthDaysAot_C1
        {"BLLG_LIMIT_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgLimitAmt_C1
        {"ALLW_DATA_CRCT_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//allwDataCrctFlg_D1
        {"MTR_EST_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrEstTpCd_ES
        {"MTR_EST_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrEstTpCd_E1
        {"MTR_EST_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrEstTpDescTxt_E1
        {"BLLG_CYCLE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_E2
        {"BLLG_CYCLE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_E1
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt_E1
        {"BLLG_CYCLE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_E4
        {"BLLG_CYCLE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_E3
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt_E2
        {"DS_INV_TGTR_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsInvTgtrTpCd_E1
        {"DS_INV_TGTR_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsInvTgtrTpCd_E2
        {"DS_INV_TGTR_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsInvTgtrTpDescTxt_E1
        {"PRC_ALLOC_BY_MACH_QTY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcAllocByMachQtyFlg_E1
        {"PRINT_DTL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//printDtlFlg_E1
        {"INV_SEPT_BASE_USG_MSTR_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invSeptBaseUsgMstrFlg_E1
        {"CONTR_UPLFT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrUplftTpCd_FS
        {"CONTR_UPLFT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrUplftTpCd_F1
        {"CONTR_UPLFT_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrUplftTpDescTxt_F1
        {"UPLFT_PRC_METH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftPrcMethCd_FS
        {"UPLFT_PRC_METH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftPrcMethCd_F1
        {"UPLFT_PRC_METH_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftPrcMethDescTxt_F1
        {"BEF_END_UPLFT_DAYS_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befEndUplftDaysAot_F1
        {"UPLFT_BASE_PRC_UP_RATIO",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftBasePrcUpRatio_F1
        {"UPLFT_MTR_PRC_UP_RATIO",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftMtrPrcUpRatio_F1
        {"BLLG_THRU_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgThruTpCd_GS
        {"BLLG_THRU_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgThruTpCd_G1
        {"BLLG_THRU_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgThruTpDescTxt_G1
        {"BLLG_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//bllgThruDt_G1
        {"CONTR_CLO_DAY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrCloDay_GS
        {"CONTR_CLO_DAY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrCloDay_G1
        {"XX_EDT_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtDescTxt_G1
        {"CONTR_BLLG_DAY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrBllgDay_GS
        {"CONTR_BLLG_DAY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrBllgDay_G1
        {"XX_EDT_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtDescTxt_G2
        {"BLLG_TMG_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgTmgTpCd_GS
        {"BLLG_TMG_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgTmgTpCd_G1
        {"BLLG_TMG_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgTmgTpDescTxt_G1
        {"STUB_PREP_FROM_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stubPrepFromTpCd_IS
        {"STUB_PREP_FROM_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stubPrepFromTpCd_I1
        {"STUB_PREP_FROM_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stubPrepFromTpDescTxt_I1
        {"STUB_PREP_THRU_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stubPrepThruTpCd_IS
        {"STUB_PREP_THRU_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stubPrepThruTpCd_I1
        {"STUB_PREP_THRU_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stubPrepThruTpDescTxt_I1
        {"AUTO_EFF_FLEET_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoEffFleetFlg_I1
        {"AUTO_EFF_AGGR_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoEffAggrFlg_I1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_X1
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
