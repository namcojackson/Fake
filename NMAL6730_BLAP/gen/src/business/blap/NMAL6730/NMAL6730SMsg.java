//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230208094935000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6730SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6730;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6730 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6730SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD_H1*/
	public final EZDSStringItem              glblCmpyCd_H1;

    /** DS_ACCT_NUM_H1*/
	public final EZDSStringItem              dsAcctNum_H1;

    /** DS_ACCT_NM_H1*/
	public final EZDSStringItem              dsAcctNm_H1;

    /** XX_ALL_LINE_ADDR_H1*/
	public final EZDSStringItem              xxAllLineAddr_H1;

    /** FIRST_LINE_ADDR_H1*/
	public final EZDSStringItem              firstLineAddr_H1;

    /** SCD_LINE_ADDR_H1*/
	public final EZDSStringItem              scdLineAddr_H1;

    /** THIRD_LINE_ADDR_H1*/
	public final EZDSStringItem              thirdLineAddr_H1;

    /** FRTH_LINE_ADDR_H1*/
	public final EZDSStringItem              frthLineAddr_H1;

    /** CTY_ADDR_H1*/
	public final EZDSStringItem              ctyAddr_H1;

    /** ST_CD_H1*/
	public final EZDSStringItem              stCd_H1;

    /** POST_CD_H1*/
	public final EZDSStringItem              postCd_H1;

    /** LOC_NUM_H1*/
	public final EZDSStringItem              locNum_H1;

    /** BILL_TO_CUST_CD_H1*/
	public final EZDSStringItem              billToCustCd_H1;

    /** BILL_TO_CUST_PK_H1*/
	public final EZDSBigDecimalItem              billToCustPk_H1;

    /** COA_CH_CD_H1*/
	public final EZDSStringItem              coaChCd_H1;

    /** COA_CH_CD_BK*/
	public final EZDSStringItem              coaChCd_BK;

    /** COA_CH_NM_H1*/
	public final EZDSStringItem              coaChNm_H1;

    /** COA_AFFL_CD_H1*/
	public final EZDSStringItem              coaAfflCd_H1;

    /** COA_CMPY_CD_H1*/
	public final EZDSStringItem              coaCmpyCd_H1;

    /** COA_BR_CD_H1*/
	public final EZDSStringItem              coaBrCd_H1;

    /** COA_CC_CD_H1*/
	public final EZDSStringItem              coaCcCd_H1;

    /** COA_ACCT_CD_H1*/
	public final EZDSStringItem              coaAcctCd_H1;

    /** COA_PROD_CD_H1*/
	public final EZDSStringItem              coaProdCd_H1;

    /** COA_PROJ_CD_H1*/
	public final EZDSStringItem              coaProjCd_H1;

    /** COA_EXTN_CD_H1*/
	public final EZDSStringItem              coaExtnCd_H1;

    /** _EZUpdateDateTime_H1*/
	public final EZDSStringItem              ezUpTime_H1;

    /** _EZUpTimeZone_H1*/
	public final EZDSStringItem              ezUpTimeZone_H1;

    /** _EZUpdateDateTime_H2*/
	public final EZDSStringItem              ezUpTime_H2;

    /** _EZUpTimeZone_H2*/
	public final EZDSStringItem              ezUpTimeZone_H2;

    /** RGTN_STS_CD_H1*/
	public final EZDSStringItem              rgtnStsCd_H1;

    /** DS_CUST_AR_TMPL_PK_F1*/
	public final EZDSBigDecimalItem              dsCustArTmplPk_F1;

    /** DS_CUST_AR_TMPL_NM_F1*/
	public final EZDSStringItem              dsCustArTmplNm_F1;

    /** CCY_CD_P1*/
	public final EZDSStringItem              ccyCd_P1;

    /** CUST_CR_RTG_CD_P1*/
	public final EZDSStringItem              custCrRtgCd_P1;

    /** CR_LIMIT_AMT_F1*/
	public final EZDSBigDecimalItem              crLimitAmt_F1;

    /** CR_CHK_REQ_TP_CD_P1*/
	public final EZDSStringItem              crChkReqTpCd_P1;

    /** CR_RISK_CLS_CD_P1*/
	public final EZDSStringItem              crRiskClsCd_P1;

    /** CONTR_CR_RISK_CLS_CD_P1*/
	public final EZDSStringItem              contrCrRiskClsCd_P1;

    /** PMT_TERM_CASH_DISC_CD_P1*/
	public final EZDSStringItem              pmtTermCashDiscCd_P1;

    /** OVRD_PMT_TERM_FLG_F1*/
	public final EZDSStringItem              ovrdPmtTermFlg_F1;

    /** CASH_OR_CC_REQ_FLG_F1*/
	public final EZDSStringItem              cashOrCcReqFlg_F1;

    /** CUST_HARD_HLD_FLG_F1*/
	public final EZDSStringItem              custHardHldFlg_F1;

    /** REM_ID_F1*/
	public final EZDSStringItem              remId_F1;

    /** AR_STMT_FLG_F1*/
	public final EZDSStringItem              arStmtFlg_F1;

    /** SEND_CR_BAL_STMT_FLG_F1*/
	public final EZDSStringItem              sendCrBalStmtFlg_F1;

    /** AR_STMT_ISS_CYCLE_CD_P1*/
	public final EZDSStringItem              arStmtIssCycleCd_P1;

    /** CLT_CUST_TP_CD_F1*/
	public final EZDSStringItem              cltCustTpCd_F1;

    /** CLT_CUST_TP_NM_F1*/
	public final EZDSStringItem              cltCustTpNm_F1;

    /** CLT_PTFO_CD_F1*/
	public final EZDSStringItem              cltPtfoCd_F1;

    /** CLT_PTFO_NM_F1*/
	public final EZDSStringItem              cltPtfoNm_F1;

    /** CLT_PTFO_PK_F1*/
	public final EZDSBigDecimalItem              cltPtfoPk_F1;

    /** DS_CLT_ACCT_STS_CD_P1*/
	public final EZDSStringItem              dsCltAcctStsCd_P1;

    /** LATE_FEE_FLG_F1*/
	public final EZDSStringItem              lateFeeFlg_F1;

    /** LATE_FEE_AMT_F1*/
	public final EZDSBigDecimalItem              lateFeeAmt_F1;

    /** MLY_LATE_FEE_RATE_F1*/
	public final EZDSBigDecimalItem              mlyLateFeeRate_F1;

    /** DS_TAX_PRNT_TP_CD_P1*/
	public final EZDSStringItem              dsTaxPrntTpCd_P1;

    /** DS_TAX_EXEM_FLG_F1*/
	public final EZDSStringItem              dsTaxExemFlg_F1;

    /** DS_EXEM_EXPR_DT_F1*/
	public final EZDSDateItem              dsExemExprDt_F1;

    /** AUTO_CASH_HRCH_CD_P1*/
	public final EZDSStringItem              autoCashHrchCd_P1;

    /** _EZUpdateDateTime_F1*/
	public final EZDSStringItem              ezUpTime_F1;

    /** _EZUpTimeZone_F1*/
	public final EZDSStringItem              ezUpTimeZone_F1;

    /** XX_REC_HIST_CRAT_TS*/
	public final EZDSStringItem              xxRecHistCratTs;

    /** XX_REC_HIST_CRAT_BY_NM*/
	public final EZDSStringItem              xxRecHistCratByNm;

    /** XX_REC_HIST_UPD_TS*/
	public final EZDSStringItem              xxRecHistUpdTs;

    /** XX_REC_HIST_UPD_BY_NM*/
	public final EZDSStringItem              xxRecHistUpdByNm;

    /** XX_REC_HIST_TBL_NM*/
	public final EZDSStringItem              xxRecHistTblNm;

    /** XX_REC_HIST_CRAT_TS_F1*/
	public final EZDSStringItem              xxRecHistCratTs_F1;

    /** XX_REC_HIST_CRAT_BY_NM_F1*/
	public final EZDSStringItem              xxRecHistCratByNm_F1;

    /** XX_REC_HIST_UPD_TS_F1*/
	public final EZDSStringItem              xxRecHistUpdTs_F1;

    /** XX_REC_HIST_UPD_BY_NM_F1*/
	public final EZDSStringItem              xxRecHistUpdByNm_F1;

    /** XX_REC_HIST_TBL_NM_F1*/
	public final EZDSStringItem              xxRecHistTblNm_F1;

    /** DS_TAX_GRP_EXEM_CD_P1*/
	public final EZDSStringItem              dsTaxGrpExemCd_P1;

    /** A*/
	public final business.blap.NMAL6730.NMAL6730_ASMsgArray              A;

    /** DEF_BASE_TP_CD_P1*/
	public final EZDSStringItem              defBaseTpCd_P1;

    /** DEF_BASE_CYCLE_CD_P1*/
	public final EZDSStringItem              defBaseCycleCd_P1;

    /** DEF_USG_TP_CD_P1*/
	public final EZDSStringItem              defUsgTpCd_P1;

    /** DEF_USG_CYCLE_CD_P1*/
	public final EZDSStringItem              defUsgCycleCd_P1;

    /** DS_BILL_TGTR_FLG_I1*/
	public final EZDSStringItem              dsBillTgtrFlg_I1;

    /** B*/
	public final business.blap.NMAL6730.NMAL6730_BSMsgArray              B;

    /** C*/
	public final business.blap.NMAL6730.NMAL6730_CSMsgArray              C;

    /** K*/
	public final business.blap.NMAL6730.NMAL6730_KSMsgArray              K;

    /** T*/
	public final business.blap.NMAL6730.NMAL6730_TSMsgArray              T;


	/**
	 * NMAL6730SMsg is constructor.
	 * The initialization when the instance of NMAL6730SMsg is generated.
	 */
	public NMAL6730SMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6730SMsg is constructor.
	 * The initialization when the instance of NMAL6730SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6730SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd_H1 = (EZDSStringItem)newItem("glblCmpyCd_H1");
		dsAcctNum_H1 = (EZDSStringItem)newItem("dsAcctNum_H1");
		dsAcctNm_H1 = (EZDSStringItem)newItem("dsAcctNm_H1");
		xxAllLineAddr_H1 = (EZDSStringItem)newItem("xxAllLineAddr_H1");
		firstLineAddr_H1 = (EZDSStringItem)newItem("firstLineAddr_H1");
		scdLineAddr_H1 = (EZDSStringItem)newItem("scdLineAddr_H1");
		thirdLineAddr_H1 = (EZDSStringItem)newItem("thirdLineAddr_H1");
		frthLineAddr_H1 = (EZDSStringItem)newItem("frthLineAddr_H1");
		ctyAddr_H1 = (EZDSStringItem)newItem("ctyAddr_H1");
		stCd_H1 = (EZDSStringItem)newItem("stCd_H1");
		postCd_H1 = (EZDSStringItem)newItem("postCd_H1");
		locNum_H1 = (EZDSStringItem)newItem("locNum_H1");
		billToCustCd_H1 = (EZDSStringItem)newItem("billToCustCd_H1");
		billToCustPk_H1 = (EZDSBigDecimalItem)newItem("billToCustPk_H1");
		coaChCd_H1 = (EZDSStringItem)newItem("coaChCd_H1");
		coaChCd_BK = (EZDSStringItem)newItem("coaChCd_BK");
		coaChNm_H1 = (EZDSStringItem)newItem("coaChNm_H1");
		coaAfflCd_H1 = (EZDSStringItem)newItem("coaAfflCd_H1");
		coaCmpyCd_H1 = (EZDSStringItem)newItem("coaCmpyCd_H1");
		coaBrCd_H1 = (EZDSStringItem)newItem("coaBrCd_H1");
		coaCcCd_H1 = (EZDSStringItem)newItem("coaCcCd_H1");
		coaAcctCd_H1 = (EZDSStringItem)newItem("coaAcctCd_H1");
		coaProdCd_H1 = (EZDSStringItem)newItem("coaProdCd_H1");
		coaProjCd_H1 = (EZDSStringItem)newItem("coaProjCd_H1");
		coaExtnCd_H1 = (EZDSStringItem)newItem("coaExtnCd_H1");
		ezUpTime_H1 = (EZDSStringItem)newItem("ezUpTime_H1");
		ezUpTimeZone_H1 = (EZDSStringItem)newItem("ezUpTimeZone_H1");
		ezUpTime_H2 = (EZDSStringItem)newItem("ezUpTime_H2");
		ezUpTimeZone_H2 = (EZDSStringItem)newItem("ezUpTimeZone_H2");
		rgtnStsCd_H1 = (EZDSStringItem)newItem("rgtnStsCd_H1");
		dsCustArTmplPk_F1 = (EZDSBigDecimalItem)newItem("dsCustArTmplPk_F1");
		dsCustArTmplNm_F1 = (EZDSStringItem)newItem("dsCustArTmplNm_F1");
		ccyCd_P1 = (EZDSStringItem)newItem("ccyCd_P1");
		custCrRtgCd_P1 = (EZDSStringItem)newItem("custCrRtgCd_P1");
		crLimitAmt_F1 = (EZDSBigDecimalItem)newItem("crLimitAmt_F1");
		crChkReqTpCd_P1 = (EZDSStringItem)newItem("crChkReqTpCd_P1");
		crRiskClsCd_P1 = (EZDSStringItem)newItem("crRiskClsCd_P1");
		contrCrRiskClsCd_P1 = (EZDSStringItem)newItem("contrCrRiskClsCd_P1");
		pmtTermCashDiscCd_P1 = (EZDSStringItem)newItem("pmtTermCashDiscCd_P1");
		ovrdPmtTermFlg_F1 = (EZDSStringItem)newItem("ovrdPmtTermFlg_F1");
		cashOrCcReqFlg_F1 = (EZDSStringItem)newItem("cashOrCcReqFlg_F1");
		custHardHldFlg_F1 = (EZDSStringItem)newItem("custHardHldFlg_F1");
		remId_F1 = (EZDSStringItem)newItem("remId_F1");
		arStmtFlg_F1 = (EZDSStringItem)newItem("arStmtFlg_F1");
		sendCrBalStmtFlg_F1 = (EZDSStringItem)newItem("sendCrBalStmtFlg_F1");
		arStmtIssCycleCd_P1 = (EZDSStringItem)newItem("arStmtIssCycleCd_P1");
		cltCustTpCd_F1 = (EZDSStringItem)newItem("cltCustTpCd_F1");
		cltCustTpNm_F1 = (EZDSStringItem)newItem("cltCustTpNm_F1");
		cltPtfoCd_F1 = (EZDSStringItem)newItem("cltPtfoCd_F1");
		cltPtfoNm_F1 = (EZDSStringItem)newItem("cltPtfoNm_F1");
		cltPtfoPk_F1 = (EZDSBigDecimalItem)newItem("cltPtfoPk_F1");
		dsCltAcctStsCd_P1 = (EZDSStringItem)newItem("dsCltAcctStsCd_P1");
		lateFeeFlg_F1 = (EZDSStringItem)newItem("lateFeeFlg_F1");
		lateFeeAmt_F1 = (EZDSBigDecimalItem)newItem("lateFeeAmt_F1");
		mlyLateFeeRate_F1 = (EZDSBigDecimalItem)newItem("mlyLateFeeRate_F1");
		dsTaxPrntTpCd_P1 = (EZDSStringItem)newItem("dsTaxPrntTpCd_P1");
		dsTaxExemFlg_F1 = (EZDSStringItem)newItem("dsTaxExemFlg_F1");
		dsExemExprDt_F1 = (EZDSDateItem)newItem("dsExemExprDt_F1");
		autoCashHrchCd_P1 = (EZDSStringItem)newItem("autoCashHrchCd_P1");
		ezUpTime_F1 = (EZDSStringItem)newItem("ezUpTime_F1");
		ezUpTimeZone_F1 = (EZDSStringItem)newItem("ezUpTimeZone_F1");
		xxRecHistCratTs = (EZDSStringItem)newItem("xxRecHistCratTs");
		xxRecHistCratByNm = (EZDSStringItem)newItem("xxRecHistCratByNm");
		xxRecHistUpdTs = (EZDSStringItem)newItem("xxRecHistUpdTs");
		xxRecHistUpdByNm = (EZDSStringItem)newItem("xxRecHistUpdByNm");
		xxRecHistTblNm = (EZDSStringItem)newItem("xxRecHistTblNm");
		xxRecHistCratTs_F1 = (EZDSStringItem)newItem("xxRecHistCratTs_F1");
		xxRecHistCratByNm_F1 = (EZDSStringItem)newItem("xxRecHistCratByNm_F1");
		xxRecHistUpdTs_F1 = (EZDSStringItem)newItem("xxRecHistUpdTs_F1");
		xxRecHistUpdByNm_F1 = (EZDSStringItem)newItem("xxRecHistUpdByNm_F1");
		xxRecHistTblNm_F1 = (EZDSStringItem)newItem("xxRecHistTblNm_F1");
		dsTaxGrpExemCd_P1 = (EZDSStringItem)newItem("dsTaxGrpExemCd_P1");
		A = (business.blap.NMAL6730.NMAL6730_ASMsgArray)newMsgArray("A");
		defBaseTpCd_P1 = (EZDSStringItem)newItem("defBaseTpCd_P1");
		defBaseCycleCd_P1 = (EZDSStringItem)newItem("defBaseCycleCd_P1");
		defUsgTpCd_P1 = (EZDSStringItem)newItem("defUsgTpCd_P1");
		defUsgCycleCd_P1 = (EZDSStringItem)newItem("defUsgCycleCd_P1");
		dsBillTgtrFlg_I1 = (EZDSStringItem)newItem("dsBillTgtrFlg_I1");
		B = (business.blap.NMAL6730.NMAL6730_BSMsgArray)newMsgArray("B");
		C = (business.blap.NMAL6730.NMAL6730_CSMsgArray)newMsgArray("C");
		K = (business.blap.NMAL6730.NMAL6730_KSMsgArray)newMsgArray("K");
		T = (business.blap.NMAL6730.NMAL6730_TSMsgArray)newMsgArray("T");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6730SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6730SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd_H1", "glblCmpyCd_H1", "H1", null, TYPE_HANKAKUEISU, "4", null},
	{"dsAcctNum_H1", "dsAcctNum_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_H1", "dsAcctNm_H1", "H1", null, TYPE_HANKAKUEISU, "360", null},
	{"xxAllLineAddr_H1", "xxAllLineAddr_H1", "H1", null, TYPE_HANKAKUEISU, "400", null},
	{"firstLineAddr_H1", "firstLineAddr_H1", "H1", null, TYPE_HANKAKUEISU, "60", null},
	{"scdLineAddr_H1", "scdLineAddr_H1", "H1", null, TYPE_HANKAKUEISU, "60", null},
	{"thirdLineAddr_H1", "thirdLineAddr_H1", "H1", null, TYPE_HANKAKUEISU, "60", null},
	{"frthLineAddr_H1", "frthLineAddr_H1", "H1", null, TYPE_HANKAKUEISU, "60", null},
	{"ctyAddr_H1", "ctyAddr_H1", "H1", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_H1", "stCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_H1", "postCd_H1", "H1", null, TYPE_HANKAKUEISU, "15", null},
	{"locNum_H1", "locNum_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"billToCustCd_H1", "billToCustCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustPk_H1", "billToCustPk_H1", "H1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"coaChCd_H1", "coaChCd_H1", "H1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaChCd_BK", "coaChCd_BK", "BK", null, TYPE_HANKAKUEISU, "3", null},
	{"coaChNm_H1", "coaChNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"coaAfflCd_H1", "coaAfflCd_H1", "H1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaCmpyCd_H1", "coaCmpyCd_H1", "H1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaBrCd_H1", "coaBrCd_H1", "H1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaCcCd_H1", "coaCcCd_H1", "H1", null, TYPE_HANKAKUEISU, "6", null},
	{"coaAcctCd_H1", "coaAcctCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdCd_H1", "coaProdCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"coaProjCd_H1", "coaProjCd_H1", "H1", null, TYPE_HANKAKUEISU, "4", null},
	{"coaExtnCd_H1", "coaExtnCd_H1", "H1", null, TYPE_HANKAKUEISU, "3", null},
	{"ezUpTime_H1", "ezUpTime_H1", "H1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_H1", "ezUpTimeZone_H1", "H1", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_H2", "ezUpTime_H2", "H2", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_H2", "ezUpTimeZone_H2", "H2", null, TYPE_HANKAKUEISU, "5", null},
	{"rgtnStsCd_H1", "rgtnStsCd_H1", "H1", null, TYPE_HANKAKUEISU, "5", null},
	{"dsCustArTmplPk_F1", "dsCustArTmplPk_F1", "F1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsCustArTmplNm_F1", "dsCustArTmplNm_F1", "F1", null, TYPE_HANKAKUEISU, "28", null},
	{"ccyCd_P1", "ccyCd_P1", "P1", null, TYPE_HANKAKUEISU, "3", null},
	{"custCrRtgCd_P1", "custCrRtgCd_P1", "P1", null, TYPE_HANKAKUEISU, "3", null},
	{"crLimitAmt_F1", "crLimitAmt_F1", "F1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"crChkReqTpCd_P1", "crChkReqTpCd_P1", "P1", null, TYPE_HANKAKUEISU, "1", null},
	{"crRiskClsCd_P1", "crRiskClsCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"contrCrRiskClsCd_P1", "contrCrRiskClsCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"pmtTermCashDiscCd_P1", "pmtTermCashDiscCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"ovrdPmtTermFlg_F1", "ovrdPmtTermFlg_F1", "F1", null, TYPE_HANKAKUEISU, "1", null},
	{"cashOrCcReqFlg_F1", "cashOrCcReqFlg_F1", "F1", null, TYPE_HANKAKUEISU, "1", null},
	{"custHardHldFlg_F1", "custHardHldFlg_F1", "F1", null, TYPE_HANKAKUEISU, "1", null},
	{"remId_F1", "remId_F1", "F1", null, TYPE_HANKAKUEISU, "20", null},
	{"arStmtFlg_F1", "arStmtFlg_F1", "F1", null, TYPE_HANKAKUEISU, "1", null},
	{"sendCrBalStmtFlg_F1", "sendCrBalStmtFlg_F1", "F1", null, TYPE_HANKAKUEISU, "1", null},
	{"arStmtIssCycleCd_P1", "arStmtIssCycleCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"cltCustTpCd_F1", "cltCustTpCd_F1", "F1", null, TYPE_HANKAKUEISU, "2", null},
	{"cltCustTpNm_F1", "cltCustTpNm_F1", "F1", null, TYPE_HANKAKUEISU, "100", null},
	{"cltPtfoCd_F1", "cltPtfoCd_F1", "F1", null, TYPE_HANKAKUEISU, "8", null},
	{"cltPtfoNm_F1", "cltPtfoNm_F1", "F1", null, TYPE_HANKAKUEISU, "30", null},
	{"cltPtfoPk_F1", "cltPtfoPk_F1", "F1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsCltAcctStsCd_P1", "dsCltAcctStsCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"lateFeeFlg_F1", "lateFeeFlg_F1", "F1", null, TYPE_HANKAKUEISU, "1", null},
	{"lateFeeAmt_F1", "lateFeeAmt_F1", "F1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"mlyLateFeeRate_F1", "mlyLateFeeRate_F1", "F1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsTaxPrntTpCd_P1", "dsTaxPrntTpCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsTaxExemFlg_F1", "dsTaxExemFlg_F1", "F1", null, TYPE_HANKAKUEISU, "1", null},
	{"dsExemExprDt_F1", "dsExemExprDt_F1", "F1", null, TYPE_NENTSUKIHI, "8", null},
	{"autoCashHrchCd_P1", "autoCashHrchCd_P1", "P1", null, TYPE_HANKAKUEISU, "4", null},
	{"ezUpTime_F1", "ezUpTime_F1", "F1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_F1", "ezUpTimeZone_F1", "F1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs", "xxRecHistCratTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm", "xxRecHistCratByNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs", "xxRecHistUpdTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm", "xxRecHistUpdByNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm", "xxRecHistTblNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"xxRecHistCratTs_F1", "xxRecHistCratTs_F1", "F1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_F1", "xxRecHistCratByNm_F1", "F1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_F1", "xxRecHistUpdTs_F1", "F1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_F1", "xxRecHistUpdByNm_F1", "F1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_F1", "xxRecHistTblNm_F1", "F1", null, TYPE_HANKAKUEISU, "60", null},
	{"dsTaxGrpExemCd_P1", "dsTaxGrpExemCd_P1", "P1", null, TYPE_HANKAKUEISU, "60", null},
	{"A", "A", null, "200", "business.blap.NMAL6730.NMAL6730_ASMsgArray", null, null},
	
	{"defBaseTpCd_P1", "defBaseTpCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"defBaseCycleCd_P1", "defBaseCycleCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"defUsgTpCd_P1", "defUsgTpCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"defUsgCycleCd_P1", "defUsgCycleCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsBillTgtrFlg_I1", "dsBillTgtrFlg_I1", "I1", null, TYPE_HANKAKUEISU, "1", null},
	{"B", "B", null, "200", "business.blap.NMAL6730.NMAL6730_BSMsgArray", null, null},
	
	{"C", "C", null, "200", "business.blap.NMAL6730.NMAL6730_CSMsgArray", null, null},
	
	{"K", "K", null, "6", "business.blap.NMAL6730.NMAL6730_KSMsgArray", null, null},
	
	{"T", "T", null, "200", "business.blap.NMAL6730.NMAL6730_TSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_H1
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_H1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_H1
        {"XX_ALL_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr_H1
        {"FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr_H1
        {"SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdLineAddr_H1
        {"THIRD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdLineAddr_H1
        {"FRTH_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthLineAddr_H1
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_H1
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_H1
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_H1
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_H1
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_H1
        {"BILL_TO_CUST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustPk_H1
        {"COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaChCd_H1
        {"COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaChCd_BK
        {"COA_CH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaChNm_H1
        {"COA_AFFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAfflCd_H1
        {"COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCmpyCd_H1
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_H1
        {"COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcCd_H1
        {"COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctCd_H1
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_H1
        {"COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjCd_H1
        {"COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_H1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_H1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_H1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_H2
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_H2
        {"RGTN_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rgtnStsCd_H1
        {"DS_CUST_AR_TMPL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustArTmplPk_F1
        {"DS_CUST_AR_TMPL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustArTmplNm_F1
        {"CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ccyCd_P1
        {"CUST_CR_RTG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custCrRtgCd_P1
        {"CR_LIMIT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crLimitAmt_F1
        {"CR_CHK_REQ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crChkReqTpCd_P1
        {"CR_RISK_CLS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRiskClsCd_P1
        {"CONTR_CR_RISK_CLS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrCrRiskClsCd_P1
        {"PMT_TERM_CASH_DISC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCashDiscCd_P1
        {"OVRD_PMT_TERM_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ovrdPmtTermFlg_F1
        {"CASH_OR_CC_REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cashOrCcReqFlg_F1
        {"CUST_HARD_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custHardHldFlg_F1
        {"REM_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//remId_F1
        {"AR_STMT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arStmtFlg_F1
        {"SEND_CR_BAL_STMT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sendCrBalStmtFlg_F1
        {"AR_STMT_ISS_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arStmtIssCycleCd_P1
        {"CLT_CUST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltCustTpCd_F1
        {"CLT_CUST_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltCustTpNm_F1
        {"CLT_PTFO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltPtfoCd_F1
        {"CLT_PTFO_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltPtfoNm_F1
        {"CLT_PTFO_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltPtfoPk_F1
        {"DS_CLT_ACCT_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCltAcctStsCd_P1
        {"LATE_FEE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lateFeeFlg_F1
        {"LATE_FEE_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lateFeeAmt_F1
        {"MLY_LATE_FEE_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlyLateFeeRate_F1
        {"DS_TAX_PRNT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsTaxPrntTpCd_P1
        {"DS_TAX_EXEM_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsTaxExemFlg_F1
        {"DS_EXEM_EXPR_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsExemExprDt_F1
        {"AUTO_CASH_HRCH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoCashHrchCd_P1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_F1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_F1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_F1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_F1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_F1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_F1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_F1
        {"DS_TAX_GRP_EXEM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsTaxGrpExemCd_P1
		null,	//A
        {"DEF_BASE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//defBaseTpCd_P1
        {"DEF_BASE_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//defBaseCycleCd_P1
        {"DEF_USG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//defUsgTpCd_P1
        {"DEF_USG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//defUsgCycleCd_P1
        {"DS_BILL_TGTR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBillTgtrFlg_I1
		null,	//B
		null,	//C
		null,	//K
		null,	//T
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
