//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20220328075727000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0810_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0810;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0810 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0810_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** GLBL_CMPY_CD_A*/
	public final EZDBStringItem              glblCmpyCd_A;

    /** DS_CONTR_INTFC_PK_A*/
	public final EZDBBigDecimalItem              dsContrIntfcPk_A;

    /** CONTR_INTFC_SRC_TP_CD_AS*/
	public final EZDBStringItem              contrIntfcSrcTpCd_AS;

    /** DS_CONTR_SRC_REF_NUM_A*/
	public final EZDBStringItem              dsContrSrcRefNum_A;

    /** DS_CONTR_INTFC_BAT_NUM_A*/
	public final EZDBStringItem              dsContrIntfcBatNum_A;

    /** DS_CONTR_NUM_A*/
	public final EZDBStringItem              dsContrNum_A;

    /** DS_CONTR_INTFC_ACT_CD_AS*/
	public final EZDBStringItem              dsContrIntfcActCd_AS;

    /** SER_NUM_A*/
	public final EZDBStringItem              serNum_A;

    /** SVC_MACH_MSTR_PK_A*/
	public final EZDBBigDecimalItem              svcMachMstrPk_A;

    /** CONTR_INTFC_DTL_TP_CD_AS*/
	public final EZDBStringItem              contrIntfcDtlTpCd_AS;

    /** CONTR_INTFC_DTL_TP_CD_A*/
	public final EZDBStringItem              contrIntfcDtlTpCd_A;

    /** DS_CONTR_INTFC_STS_CD_A*/
	public final EZDBStringItem              dsContrIntfcStsCd_A;

    /** INTFC_ERR_MSG_TXT_A*/
	public final EZDBStringItem              intfcErrMsgTxt_A;

    /** DS_CONTR_PROC_STS_DESC_TXT_A*/
	public final EZDBStringItem              dsContrProcStsDescTxt_A;

    /** DS_CONTR_PROC_STS_CD_AS*/
	public final EZDBStringItem              dsContrProcStsCd_AS;

    /** DS_ACCT_NUM_A*/
	public final EZDBStringItem              dsAcctNum_A;

    /** DS_ACCT_NM_AC*/
	public final EZDBStringItem              dsAcctNm_AC;

    /** BILL_TO_CUST_CD_A*/
	public final EZDBStringItem              billToCustCd_A;

    /** DS_ACCT_NM_AB*/
	public final EZDBStringItem              dsAcctNm_AB;

    /** BILL_TO_LOC_NUM_A*/
	public final EZDBStringItem              billToLocNum_A;

    /** LEASE_CMPY_CD_A*/
	public final EZDBStringItem              leaseCmpyCd_A;

    /** DS_ACCT_NM_AL*/
	public final EZDBStringItem              dsAcctNm_AL;

    /** SVC_CONTR_BR_CD_A*/
	public final EZDBStringItem              svcContrBrCd_A;

    /** CONTR_ADMIN_PSN_CD_A*/
	public final EZDBStringItem              contrAdminPsnCd_A;

    /** TOC_CD_A*/
	public final EZDBStringItem              tocCd_A;

    /** CUST_PO_NUM_A*/
	public final EZDBStringItem              custPoNum_A;

    /** PO_DT_A*/
	public final EZDBDateItem              poDt_A;

    /** CR_CARD_CUST_REF_NUM_A*/
	public final EZDBStringItem              crCardCustRefNum_A;

    /** CR_CARD_EXPR_YR_MTH_A*/
	public final EZDBDateItem              crCardExprYrMth_A;

    /** MTR_EST_TP_CD_AS*/
	public final EZDBStringItem              mtrEstTpCd_AS;

    /** SVC_PGM_MDSE_CD_A*/
	public final EZDBStringItem              svcPgmMdseCd_A;

    /** MDSE_CD_A*/
	public final EZDBStringItem              mdseCd_A;

    /** MDL_ID_A*/
	public final EZDBBigDecimalItem              mdlId_A;

    /** MDL_NM_A*/
	public final EZDBStringItem              mdlNm_A;

    /** CONTR_FROM_DT_A*/
	public final EZDBDateItem              contrFromDt_A;

    /** CONTR_THRU_DT_A*/
	public final EZDBDateItem              contrThruDt_A;

    /** BLLG_CYCLE_CD_AS*/
	public final EZDBStringItem              bllgCycleCd_AS;

    /** PRC_ALLOC_BY_MACH_QTY_FLG_A*/
	public final EZDBStringItem              prcAllocByMachQtyFlg_A;

    /** CONTR_AUTO_RNW_TP_CD_AS*/
	public final EZDBStringItem              contrAutoRnwTpCd_AS;

    /** RNW_PRC_METH_CD_AS*/
	public final EZDBStringItem              rnwPrcMethCd_AS;

    /** BEF_END_RNW_DAYS_AOT_A*/
	public final EZDBBigDecimalItem              befEndRnwDaysAot_A;

    /** RNW_PRC_UP_RATIO_A*/
	public final EZDBBigDecimalItem              rnwPrcUpRatio_A;

    /** CONTR_UPLFT_TP_CD_AS*/
	public final EZDBStringItem              contrUplftTpCd_AS;

    /** UPLFT_PRC_METH_CD_AS*/
	public final EZDBStringItem              uplftPrcMethCd_AS;

    /** UPLFT_PRC_UP_RATIO_A*/
	public final EZDBBigDecimalItem              uplftPrcUpRatio_A;

    /** MTR_READ_METH_CD_AS*/
	public final EZDBStringItem              mtrReadMethCd_AS;

    /** BASE_PRC_DEAL_AMT_A*/
	public final EZDBBigDecimalItem              basePrcDealAmt_A;

    /** CONTR_CLO_DAY_A*/
	public final EZDBStringItem              contrCloDay_A;

    /** CONTR_BLLG_DAY_A*/
	public final EZDBStringItem              contrBllgDay_A;

    /** BLLG_THRU_DT_A*/
	public final EZDBDateItem              bllgThruDt_A;

    /** BLLG_MTR_LB_CD_A*/
	public final EZDBStringItem              bllgMtrLbCd_A;

    /** MTR_LB_DESC_TXT_A*/
	public final EZDBStringItem              mtrLbDescTxt_A;

    /** START_MTR_CNT_A*/
	public final EZDBBigDecimalItem              startMtrCnt_A;

    /** BLLG_ROLL_OVER_RATIO_A*/
	public final EZDBBigDecimalItem              bllgRollOverRatio_A;

    /** DS_CONTR_CATG_CD_AS*/
	public final EZDBStringItem              dsContrCatgCd_AS;

    /** DS_CONTR_STS_CD_AS*/
	public final EZDBStringItem              dsContrStsCd_AS;

    /** XS_CHRG_TP_CD_AS*/
	public final EZDBStringItem              xsChrgTpCd_AS;

    /** XS_MTR_COPY_QTY_A*/
	public final EZDBBigDecimalItem              xsMtrCopyQty_A;

    /** XS_MTR_AMT_RATE_A*/
	public final EZDBBigDecimalItem              xsMtrAmtRate_A;

    /** ADDL_CHRG_TP_CD_AS*/
	public final EZDBStringItem              addlChrgTpCd_AS;

    /** ADDL_CHRG_FLAT_DEAL_PRC_AMT_A*/
	public final EZDBBigDecimalItem              addlChrgFlatDealPrcAmt_A;

    /** ADDL_CHRG_APLC_PCT_A*/
	public final EZDBBigDecimalItem              addlChrgAplcPct_A;

    /** CHRG_LVL_TP_CD_AS*/
	public final EZDBStringItem              chrgLvlTpCd_AS;

    /** ADDL_CHRG_INV_TP_CD_AS*/
	public final EZDBStringItem              addlChrgInvTpCd_AS;

    /** PRINT_DTL_FLG_A*/
	public final EZDBStringItem              printDtlFlg_A;

    /** BASE_PRC_TERM_DEAL_AMT_RATE_A*/
	public final EZDBBigDecimalItem              basePrcTermDealAmtRate_A;

    /** DS_CONTR_CLS_CD_AS*/
	public final EZDBStringItem              dsContrClsCd_AS;

    /** CTAC_PSN_PK_A*/
	public final EZDBBigDecimalItem              ctacPsnPk_A;

    /** CTAC_PSN_NM_A*/
	public final EZDBStringItem              ctacPsnNm_A;

    /** DS_INV_TGTR_TP_CD_AS*/
	public final EZDBStringItem              dsInvTgtrTpCd_AS;

    /** CONTR_CLO_DT_A*/
	public final EZDBDateItem              contrCloDt_A;

    /** CONTR_DURN_AOT_A*/
	public final EZDBBigDecimalItem              contrDurnAot_A;

    /** PMT_TERM_CASH_DISC_CD_A*/
	public final EZDBStringItem              pmtTermCashDiscCd_A;

    /** SVC_LINE_BIZ_CD_AS*/
	public final EZDBStringItem              svcLineBizCd_AS;

    /** BLLG_TMG_TP_CD_AS*/
	public final EZDBStringItem              bllgTmgTpCd_AS;

    /** DS_CONTR_EDI_CD_A*/
	public final EZDBStringItem              dsContrEdiCd_A;

    /** DS_CONTR_DESC_TXT_A*/
	public final EZDBStringItem              dsContrDescTxt_A;

    /** BASE_CHRG_TO_LEASE_CMPY_FLG_A*/
	public final EZDBStringItem              baseChrgToLeaseCmpyFlg_A;

    /** USG_CHRG_TO_LEASE_CMPY_FLG_A*/
	public final EZDBStringItem              usgChrgToLeaseCmpyFlg_A;

    /** INTG_MDSE_CD_A*/
	public final EZDBStringItem              intgMdseCd_A;

    /** DS_CONTR_INTFC_DT_A*/
	public final EZDBDateItem              dsContrIntfcDt_A;

    /** CAP_BW_ORIG_QTY_A*/
	public final EZDBBigDecimalItem              capBwOrigQty_A;

    /** CAP_COLOR_ORIG_QTY_A*/
	public final EZDBBigDecimalItem              capColorOrigQty_A;

    /** CAP_TOT_ORIG_QTY_A*/
	public final EZDBBigDecimalItem              capTotOrigQty_A;

    /** CAP_BW_RUN_QTY_A*/
	public final EZDBBigDecimalItem              capBwRunQty_A;

    /** CAP_COLOR_RUN_QTY_A*/
	public final EZDBBigDecimalItem              capColorRunQty_A;

    /** CAP_TOT_RUN_QTY_A*/
	public final EZDBBigDecimalItem              capTotRunQty_A;

    /** CPO_SVC_DTL_PK_A*/
	public final EZDBBigDecimalItem              cpoSvcDtlPk_A;

    /** XX_ROW_NUM_A*/
	public final EZDBBigDecimalItem              xxRowNum_A;

    /** CTAC_PSN_FIRST_NM_A*/
	public final EZDBStringItem              ctacPsnFirstNm_A;

    /** CTAC_PSN_LAST_NM_A*/
	public final EZDBStringItem              ctacPsnLastNm_A;

    /** MAN_CONTR_OVRD_FLG_A*/
	public final EZDBStringItem              manContrOvrdFlg_A;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDBStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDBStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDBStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDBStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDBStringItem              xxRecHistTblNm_A;


	/**
	 * NSAL0810_ABMsg is constructor.
	 * The initialization when the instance of NSAL0810_ABMsg is generated.
	 */
	public NSAL0810_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0810_ABMsg is constructor.
	 * The initialization when the instance of NSAL0810_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0810_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		glblCmpyCd_A = (EZDBStringItem)newItem("glblCmpyCd_A");
		dsContrIntfcPk_A = (EZDBBigDecimalItem)newItem("dsContrIntfcPk_A");
		contrIntfcSrcTpCd_AS = (EZDBStringItem)newItem("contrIntfcSrcTpCd_AS");
		dsContrSrcRefNum_A = (EZDBStringItem)newItem("dsContrSrcRefNum_A");
		dsContrIntfcBatNum_A = (EZDBStringItem)newItem("dsContrIntfcBatNum_A");
		dsContrNum_A = (EZDBStringItem)newItem("dsContrNum_A");
		dsContrIntfcActCd_AS = (EZDBStringItem)newItem("dsContrIntfcActCd_AS");
		serNum_A = (EZDBStringItem)newItem("serNum_A");
		svcMachMstrPk_A = (EZDBBigDecimalItem)newItem("svcMachMstrPk_A");
		contrIntfcDtlTpCd_AS = (EZDBStringItem)newItem("contrIntfcDtlTpCd_AS");
		contrIntfcDtlTpCd_A = (EZDBStringItem)newItem("contrIntfcDtlTpCd_A");
		dsContrIntfcStsCd_A = (EZDBStringItem)newItem("dsContrIntfcStsCd_A");
		intfcErrMsgTxt_A = (EZDBStringItem)newItem("intfcErrMsgTxt_A");
		dsContrProcStsDescTxt_A = (EZDBStringItem)newItem("dsContrProcStsDescTxt_A");
		dsContrProcStsCd_AS = (EZDBStringItem)newItem("dsContrProcStsCd_AS");
		dsAcctNum_A = (EZDBStringItem)newItem("dsAcctNum_A");
		dsAcctNm_AC = (EZDBStringItem)newItem("dsAcctNm_AC");
		billToCustCd_A = (EZDBStringItem)newItem("billToCustCd_A");
		dsAcctNm_AB = (EZDBStringItem)newItem("dsAcctNm_AB");
		billToLocNum_A = (EZDBStringItem)newItem("billToLocNum_A");
		leaseCmpyCd_A = (EZDBStringItem)newItem("leaseCmpyCd_A");
		dsAcctNm_AL = (EZDBStringItem)newItem("dsAcctNm_AL");
		svcContrBrCd_A = (EZDBStringItem)newItem("svcContrBrCd_A");
		contrAdminPsnCd_A = (EZDBStringItem)newItem("contrAdminPsnCd_A");
		tocCd_A = (EZDBStringItem)newItem("tocCd_A");
		custPoNum_A = (EZDBStringItem)newItem("custPoNum_A");
		poDt_A = (EZDBDateItem)newItem("poDt_A");
		crCardCustRefNum_A = (EZDBStringItem)newItem("crCardCustRefNum_A");
		crCardExprYrMth_A = (EZDBDateItem)newItem("crCardExprYrMth_A");
		mtrEstTpCd_AS = (EZDBStringItem)newItem("mtrEstTpCd_AS");
		svcPgmMdseCd_A = (EZDBStringItem)newItem("svcPgmMdseCd_A");
		mdseCd_A = (EZDBStringItem)newItem("mdseCd_A");
		mdlId_A = (EZDBBigDecimalItem)newItem("mdlId_A");
		mdlNm_A = (EZDBStringItem)newItem("mdlNm_A");
		contrFromDt_A = (EZDBDateItem)newItem("contrFromDt_A");
		contrThruDt_A = (EZDBDateItem)newItem("contrThruDt_A");
		bllgCycleCd_AS = (EZDBStringItem)newItem("bllgCycleCd_AS");
		prcAllocByMachQtyFlg_A = (EZDBStringItem)newItem("prcAllocByMachQtyFlg_A");
		contrAutoRnwTpCd_AS = (EZDBStringItem)newItem("contrAutoRnwTpCd_AS");
		rnwPrcMethCd_AS = (EZDBStringItem)newItem("rnwPrcMethCd_AS");
		befEndRnwDaysAot_A = (EZDBBigDecimalItem)newItem("befEndRnwDaysAot_A");
		rnwPrcUpRatio_A = (EZDBBigDecimalItem)newItem("rnwPrcUpRatio_A");
		contrUplftTpCd_AS = (EZDBStringItem)newItem("contrUplftTpCd_AS");
		uplftPrcMethCd_AS = (EZDBStringItem)newItem("uplftPrcMethCd_AS");
		uplftPrcUpRatio_A = (EZDBBigDecimalItem)newItem("uplftPrcUpRatio_A");
		mtrReadMethCd_AS = (EZDBStringItem)newItem("mtrReadMethCd_AS");
		basePrcDealAmt_A = (EZDBBigDecimalItem)newItem("basePrcDealAmt_A");
		contrCloDay_A = (EZDBStringItem)newItem("contrCloDay_A");
		contrBllgDay_A = (EZDBStringItem)newItem("contrBllgDay_A");
		bllgThruDt_A = (EZDBDateItem)newItem("bllgThruDt_A");
		bllgMtrLbCd_A = (EZDBStringItem)newItem("bllgMtrLbCd_A");
		mtrLbDescTxt_A = (EZDBStringItem)newItem("mtrLbDescTxt_A");
		startMtrCnt_A = (EZDBBigDecimalItem)newItem("startMtrCnt_A");
		bllgRollOverRatio_A = (EZDBBigDecimalItem)newItem("bllgRollOverRatio_A");
		dsContrCatgCd_AS = (EZDBStringItem)newItem("dsContrCatgCd_AS");
		dsContrStsCd_AS = (EZDBStringItem)newItem("dsContrStsCd_AS");
		xsChrgTpCd_AS = (EZDBStringItem)newItem("xsChrgTpCd_AS");
		xsMtrCopyQty_A = (EZDBBigDecimalItem)newItem("xsMtrCopyQty_A");
		xsMtrAmtRate_A = (EZDBBigDecimalItem)newItem("xsMtrAmtRate_A");
		addlChrgTpCd_AS = (EZDBStringItem)newItem("addlChrgTpCd_AS");
		addlChrgFlatDealPrcAmt_A = (EZDBBigDecimalItem)newItem("addlChrgFlatDealPrcAmt_A");
		addlChrgAplcPct_A = (EZDBBigDecimalItem)newItem("addlChrgAplcPct_A");
		chrgLvlTpCd_AS = (EZDBStringItem)newItem("chrgLvlTpCd_AS");
		addlChrgInvTpCd_AS = (EZDBStringItem)newItem("addlChrgInvTpCd_AS");
		printDtlFlg_A = (EZDBStringItem)newItem("printDtlFlg_A");
		basePrcTermDealAmtRate_A = (EZDBBigDecimalItem)newItem("basePrcTermDealAmtRate_A");
		dsContrClsCd_AS = (EZDBStringItem)newItem("dsContrClsCd_AS");
		ctacPsnPk_A = (EZDBBigDecimalItem)newItem("ctacPsnPk_A");
		ctacPsnNm_A = (EZDBStringItem)newItem("ctacPsnNm_A");
		dsInvTgtrTpCd_AS = (EZDBStringItem)newItem("dsInvTgtrTpCd_AS");
		contrCloDt_A = (EZDBDateItem)newItem("contrCloDt_A");
		contrDurnAot_A = (EZDBBigDecimalItem)newItem("contrDurnAot_A");
		pmtTermCashDiscCd_A = (EZDBStringItem)newItem("pmtTermCashDiscCd_A");
		svcLineBizCd_AS = (EZDBStringItem)newItem("svcLineBizCd_AS");
		bllgTmgTpCd_AS = (EZDBStringItem)newItem("bllgTmgTpCd_AS");
		dsContrEdiCd_A = (EZDBStringItem)newItem("dsContrEdiCd_A");
		dsContrDescTxt_A = (EZDBStringItem)newItem("dsContrDescTxt_A");
		baseChrgToLeaseCmpyFlg_A = (EZDBStringItem)newItem("baseChrgToLeaseCmpyFlg_A");
		usgChrgToLeaseCmpyFlg_A = (EZDBStringItem)newItem("usgChrgToLeaseCmpyFlg_A");
		intgMdseCd_A = (EZDBStringItem)newItem("intgMdseCd_A");
		dsContrIntfcDt_A = (EZDBDateItem)newItem("dsContrIntfcDt_A");
		capBwOrigQty_A = (EZDBBigDecimalItem)newItem("capBwOrigQty_A");
		capColorOrigQty_A = (EZDBBigDecimalItem)newItem("capColorOrigQty_A");
		capTotOrigQty_A = (EZDBBigDecimalItem)newItem("capTotOrigQty_A");
		capBwRunQty_A = (EZDBBigDecimalItem)newItem("capBwRunQty_A");
		capColorRunQty_A = (EZDBBigDecimalItem)newItem("capColorRunQty_A");
		capTotRunQty_A = (EZDBBigDecimalItem)newItem("capTotRunQty_A");
		cpoSvcDtlPk_A = (EZDBBigDecimalItem)newItem("cpoSvcDtlPk_A");
		xxRowNum_A = (EZDBBigDecimalItem)newItem("xxRowNum_A");
		ctacPsnFirstNm_A = (EZDBStringItem)newItem("ctacPsnFirstNm_A");
		ctacPsnLastNm_A = (EZDBStringItem)newItem("ctacPsnLastNm_A");
		manContrOvrdFlg_A = (EZDBStringItem)newItem("manContrOvrdFlg_A");
		xxRecHistCratTs_A = (EZDBStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDBStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDBStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDBStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDBStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0810_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0810_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"glblCmpyCd_A", "glblCmpyCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"dsContrIntfcPk_A", "dsContrIntfcPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"contrIntfcSrcTpCd_AS", "contrIntfcSrcTpCd_AS", "AS", null, TYPE_HANKAKUEISU, "5", null},
	{"dsContrSrcRefNum_A", "dsContrSrcRefNum_A", "A", null, TYPE_HANKAKUEISU, "90", null},
	{"dsContrIntfcBatNum_A", "dsContrIntfcBatNum_A", "A", null, TYPE_HANKAKUEISU, "14", null},
	{"dsContrNum_A", "dsContrNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrIntfcActCd_AS", "dsContrIntfcActCd_AS", "AS", null, TYPE_HANKAKUEISU, "3", null},
	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"svcMachMstrPk_A", "svcMachMstrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"contrIntfcDtlTpCd_AS", "contrIntfcDtlTpCd_AS", "AS", null, TYPE_HANKAKUEISU, "5", null},
	{"contrIntfcDtlTpCd_A", "contrIntfcDtlTpCd_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"dsContrIntfcStsCd_A", "dsContrIntfcStsCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"intfcErrMsgTxt_A", "intfcErrMsgTxt_A", "A", null, TYPE_HANKAKUEISU, "400", null},
	{"dsContrProcStsDescTxt_A", "dsContrProcStsDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"dsContrProcStsCd_AS", "dsContrProcStsCd_AS", "AS", null, TYPE_HANKAKUEISU, "2", null},
	{"dsAcctNum_A", "dsAcctNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_AC", "dsAcctNm_AC", "AC", null, TYPE_HANKAKUEISU, "360", null},
	{"billToCustCd_A", "billToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_AB", "dsAcctNm_AB", "AB", null, TYPE_HANKAKUEISU, "360", null},
	{"billToLocNum_A", "billToLocNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"leaseCmpyCd_A", "leaseCmpyCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_AL", "dsAcctNm_AL", "AL", null, TYPE_HANKAKUEISU, "360", null},
	{"svcContrBrCd_A", "svcContrBrCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"contrAdminPsnCd_A", "contrAdminPsnCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"tocCd_A", "tocCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"custPoNum_A", "custPoNum_A", "A", null, TYPE_HANKAKUEISU, "35", null},
	{"poDt_A", "poDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"crCardCustRefNum_A", "crCardCustRefNum_A", "A", null, TYPE_HANKAKUEISU, "40", null},
	{"crCardExprYrMth_A", "crCardExprYrMth_A", "A", null, TYPE_NENTSUKI, "6", null},
	{"mtrEstTpCd_AS", "mtrEstTpCd_AS", "AS", null, TYPE_HANKAKUEISU, "2", null},
	{"svcPgmMdseCd_A", "svcPgmMdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdlId_A", "mdlId_A", "A", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"mdlNm_A", "mdlNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"contrFromDt_A", "contrFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"contrThruDt_A", "contrThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgCycleCd_AS", "bllgCycleCd_AS", "AS", null, TYPE_HANKAKUEISU, "1", null},
	{"prcAllocByMachQtyFlg_A", "prcAllocByMachQtyFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"contrAutoRnwTpCd_AS", "contrAutoRnwTpCd_AS", "AS", null, TYPE_HANKAKUEISU, "2", null},
	{"rnwPrcMethCd_AS", "rnwPrcMethCd_AS", "AS", null, TYPE_HANKAKUEISU, "2", null},
	{"befEndRnwDaysAot_A", "befEndRnwDaysAot_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"rnwPrcUpRatio_A", "rnwPrcUpRatio_A", "A", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"contrUplftTpCd_AS", "contrUplftTpCd_AS", "AS", null, TYPE_HANKAKUEISU, "2", null},
	{"uplftPrcMethCd_AS", "uplftPrcMethCd_AS", "AS", null, TYPE_HANKAKUEISU, "2", null},
	{"uplftPrcUpRatio_A", "uplftPrcUpRatio_A", "A", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"mtrReadMethCd_AS", "mtrReadMethCd_AS", "AS", null, TYPE_HANKAKUEISU, "2", null},
	{"basePrcDealAmt_A", "basePrcDealAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"contrCloDay_A", "contrCloDay_A", "A", null, TYPE_HANKAKUSUJI, "2", null},
	{"contrBllgDay_A", "contrBllgDay_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"bllgThruDt_A", "bllgThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgMtrLbCd_A", "bllgMtrLbCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbDescTxt_A", "mtrLbDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"startMtrCnt_A", "startMtrCnt_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"bllgRollOverRatio_A", "bllgRollOverRatio_A", "A", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"dsContrCatgCd_AS", "dsContrCatgCd_AS", "AS", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrStsCd_AS", "dsContrStsCd_AS", "AS", null, TYPE_HANKAKUEISU, "2", null},
	{"xsChrgTpCd_AS", "xsChrgTpCd_AS", "AS", null, TYPE_HANKAKUEISU, "1", null},
	{"xsMtrCopyQty_A", "xsMtrCopyQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrAmtRate_A", "xsMtrAmtRate_A", "A", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"addlChrgTpCd_AS", "addlChrgTpCd_AS", "AS", null, TYPE_HANKAKUEISU, "16", null},
	{"addlChrgFlatDealPrcAmt_A", "addlChrgFlatDealPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"addlChrgAplcPct_A", "addlChrgAplcPct_A", "A", null, TYPE_SEISU_SYOSU, "5", "2"},
	{"chrgLvlTpCd_AS", "chrgLvlTpCd_AS", "AS", null, TYPE_HANKAKUEISU, "4", null},
	{"addlChrgInvTpCd_AS", "addlChrgInvTpCd_AS", "AS", null, TYPE_HANKAKUEISU, "4", null},
	{"printDtlFlg_A", "printDtlFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"basePrcTermDealAmtRate_A", "basePrcTermDealAmtRate_A", "A", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"dsContrClsCd_AS", "dsContrClsCd_AS", "AS", null, TYPE_HANKAKUEISU, "3", null},
	{"ctacPsnPk_A", "ctacPsnPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ctacPsnNm_A", "ctacPsnNm_A", "A", null, TYPE_HANKAKUEISU, "90", null},
	{"dsInvTgtrTpCd_AS", "dsInvTgtrTpCd_AS", "AS", null, TYPE_HANKAKUEISU, "2", null},
	{"contrCloDt_A", "contrCloDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"contrDurnAot_A", "contrDurnAot_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"pmtTermCashDiscCd_A", "pmtTermCashDiscCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"svcLineBizCd_AS", "svcLineBizCd_AS", "AS", null, TYPE_HANKAKUEISU, "20", null},
	{"bllgTmgTpCd_AS", "bllgTmgTpCd_AS", "AS", null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrEdiCd_A", "dsContrEdiCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrDescTxt_A", "dsContrDescTxt_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"baseChrgToLeaseCmpyFlg_A", "baseChrgToLeaseCmpyFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"usgChrgToLeaseCmpyFlg_A", "usgChrgToLeaseCmpyFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"intgMdseCd_A", "intgMdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"dsContrIntfcDt_A", "dsContrIntfcDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"capBwOrigQty_A", "capBwOrigQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"capColorOrigQty_A", "capColorOrigQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"capTotOrigQty_A", "capTotOrigQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"capBwRunQty_A", "capBwRunQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"capColorRunQty_A", "capColorRunQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"capTotRunQty_A", "capTotRunQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"cpoSvcDtlPk_A", "cpoSvcDtlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxRowNum_A", "xxRowNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"ctacPsnFirstNm_A", "ctacPsnFirstNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnLastNm_A", "ctacPsnLastNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"manContrOvrdFlg_A", "manContrOvrdFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxRecHistCratTs_A", "xxRecHistCratTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A", "xxRecHistCratByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A", "xxRecHistUpdTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A", "xxRecHistUpdByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A", "xxRecHistTblNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_A
        {"DS_CONTR_INTFC_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrIntfcPk_A
        {"CONTR_INTFC_SRC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrIntfcSrcTpCd_AS
        {"DS_CONTR_SRC_REF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrSrcRefNum_A
        {"DS_CONTR_INTFC_BAT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrIntfcBatNum_A
        {"DS_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A
        {"DS_CONTR_INTFC_ACT_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrIntfcActCd_AS
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"SVC_MACH_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_A
        {"CONTR_INTFC_DTL_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrIntfcDtlTpCd_AS
        {"CONTR_INTFC_DTL_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrIntfcDtlTpCd_A
        {"DS_CONTR_INTFC_STS_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrIntfcStsCd_A
        {"INTFC_ERR_MSG_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//intfcErrMsgTxt_A
        {"DS_CONTR_PROC_STS_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrProcStsDescTxt_A
        {"DS_CONTR_PROC_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrProcStsCd_AS
        {"DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A
        {"DS_ACCT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_AC
        {"BILL_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_A
        {"DS_ACCT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_AB
        {"BILL_TO_LOC_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToLocNum_A
        {"LEASE_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leaseCmpyCd_A
        {"DS_ACCT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_AL
        {"SVC_CONTR_BR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrCd_A
        {"CONTR_ADMIN_PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrAdminPsnCd_A
        {"TOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd_A
        {"CUST_PO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custPoNum_A
        {"PO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//poDt_A
        {"CR_CARD_CUST_REF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardCustRefNum_A
        {"CR_CARD_EXPR_YR_MTH",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//crCardExprYrMth_A
        {"MTR_EST_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrEstTpCd_AS
        {"SVC_PGM_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPgmMdseCd_A
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"MDL_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_A
        {"MDL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm_A
        {"CONTR_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//contrFromDt_A
        {"CONTR_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//contrThruDt_A
        {"BLLG_CYCLE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_AS
        {"PRC_ALLOC_BY_MACH_QTY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcAllocByMachQtyFlg_A
        {"CONTR_AUTO_RNW_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrAutoRnwTpCd_AS
        {"RNW_PRC_METH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwPrcMethCd_AS
        {"BEF_END_RNW_DAYS_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befEndRnwDaysAot_A
        {"RNW_PRC_UP_RATIO",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwPrcUpRatio_A
        {"CONTR_UPLFT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrUplftTpCd_AS
        {"UPLFT_PRC_METH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftPrcMethCd_AS
        {"UPLFT_PRC_UP_RATIO",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftPrcUpRatio_A
        {"MTR_READ_METH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd_AS
        {"BASE_PRC_DEAL_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcDealAmt_A
        {"CONTR_CLO_DAY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrCloDay_A
        {"CONTR_BLLG_DAY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrBllgDay_A
        {"BLLG_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//bllgThruDt_A
        {"BLLG_MTR_LB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd_A
        {"MTR_LB_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_A
        {"START_MTR_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//startMtrCnt_A
        {"BLLG_ROLL_OVER_RATIO",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgRollOverRatio_A
        {"DS_CONTR_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd_AS
        {"DS_CONTR_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrStsCd_AS
        {"XS_CHRG_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsChrgTpCd_AS
        {"XS_MTR_COPY_QTY",  NO,  "0",null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrCopyQty_A
        {"XS_MTR_AMT_RATE",  NO,  "0",null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_A
        {"ADDL_CHRG_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgTpCd_AS
        {"ADDL_CHRG_FLAT_DEAL_PRC_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgFlatDealPrcAmt_A
        {"ADDL_CHRG_APLC_PCT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgAplcPct_A
        {"CHRG_LVL_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//chrgLvlTpCd_AS
        {"ADDL_CHRG_INV_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgInvTpCd_AS
        {"PRINT_DTL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//printDtlFlg_A
        {"BASE_PRC_TERM_DEAL_AMT_RATE",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcTermDealAmtRate_A
        {"DS_CONTR_CLS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrClsCd_AS
        {"CTAC_PSN_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnPk_A
        {"CTAC_PSN_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnNm_A
        {"DS_INV_TGTR_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsInvTgtrTpCd_AS
        {"CONTR_CLO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//contrCloDt_A
        {"CONTR_DURN_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrDurnAot_A
        {"PMT_TERM_CASH_DISC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCashDiscCd_A
        {"SVC_LINE_BIZ_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLineBizCd_AS
        {"BLLG_TMG_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgTmgTpCd_AS
        {"DS_CONTR_EDI_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrEdiCd_A
        {"DS_CONTR_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDescTxt_A
        {"BASE_CHRG_TO_LEASE_CMPY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseChrgToLeaseCmpyFlg_A
        {"USG_CHRG_TO_LEASE_CMPY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//usgChrgToLeaseCmpyFlg_A
        {"INTG_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//intgMdseCd_A
        {"DS_CONTR_INTFC_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//dsContrIntfcDt_A
        {"CAP_BW_ORIG_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//capBwOrigQty_A
        {"CAP_COLOR_ORIG_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//capColorOrigQty_A
        {"CAP_TOT_ORIG_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//capTotOrigQty_A
        {"CAP_BW_RUN_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//capBwRunQty_A
        {"CAP_COLOR_RUN_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//capColorRunQty_A
        {"CAP_TOT_RUN_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//capTotRunQty_A
        {"CPO_SVC_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcDtlPk_A
        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"CTAC_PSN_FIRST_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnFirstNm_A
        {"CTAC_PSN_LAST_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnLastNm_A
        {"MAN_CONTR_OVRD_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manContrOvrdFlg_A
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A
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
