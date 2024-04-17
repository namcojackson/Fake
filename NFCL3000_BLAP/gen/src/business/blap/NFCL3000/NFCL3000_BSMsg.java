//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20190808083122000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3000_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3000;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL3000 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3000_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_HLD_FLG_B1*/
	public final EZDSStringItem              xxHldFlg_B1;

    /** XX_PG_FLG_B1*/
	public final EZDSStringItem              xxPgFlg_B1;

    /** XX_CHK_BOX_B1*/
	public final EZDSStringItem              xxChkBox_B1;

    /** XX_LINE_NUM_B1*/
	public final EZDSStringItem              xxLineNum_B1;

    /** INV_BOL_LINE_NUM_B1*/
	public final EZDSStringItem              invBolLineNum_B1;

    /** INV_LINE_NUM_B1*/
	public final EZDSStringItem              invLineNum_B1;

    /** INV_LINE_SUB_NUM_B1*/
	public final EZDSStringItem              invLineSubNum_B1;

    /** INV_TRX_LINE_SUB_NUM_B1*/
	public final EZDSStringItem              invTrxLineSubNum_B1;

    /** MDSE_CD_B1*/
	public final EZDSStringItem              mdseCd_B1;

    /** INV_LINE_SPL_PCT_B1*/
	public final EZDSBigDecimalItem              invLineSplPct_B1;

    /** INV_LINE_SPL_PCT_B2*/
	public final EZDSBigDecimalItem              invLineSplPct_B2;

    /** TOC_NM_B1*/
	public final EZDSStringItem              tocNm_B1;

    /** SLS_REP_TOC_CD_B1*/
	public final EZDSStringItem              slsRepTocCd_B1;

    /** PSN_NUM_B1*/
	public final EZDSStringItem              psnNum_B1;

    /** SLS_REP_CR_PCT_B1*/
	public final EZDSBigDecimalItem              slsRepCrPct_B1;

    /** SLS_REP_CR_PCT_B2*/
	public final EZDSBigDecimalItem              slsRepCrPct_B2;

    /** DEAL_SLS_CR_AMT_B1*/
	public final EZDSBigDecimalItem              dealSlsCrAmt_B1;

    /** XX_TOT_AMT_B1*/
	public final EZDSBigDecimalItem              xxTotAmt_B1;

    /** SLS_REP_BR_CD_B1*/
	public final EZDSStringItem              slsRepBrCd_B1;

    /** MAN_INV_CRAT_CMNT_TXT_B1*/
	public final EZDSStringItem              manInvCratCmntTxt_B1;

    /** DFRD_ACCTG_RULE_CD_B1*/
	public final EZDSStringItem              dfrdAcctgRuleCd_B1;

    /** DFRD_ACCTG_RULE_DURN_AOT_B1*/
	public final EZDSBigDecimalItem              dfrdAcctgRuleDurnAot_B1;

    /** DURN_START_DT_B1*/
	public final EZDSDateItem              durnStartDt_B1;

    /** INV_LINE_SPL_TP_CD_B1*/
	public final EZDSStringItem              invLineSplTpCd_B1;

    /** INV_ERR_MSG_TXT_B1*/
	public final EZDSStringItem              invErrMsgTxt_B1;

    /** INVLD_VAL_TXT_B1*/
	public final EZDSStringItem              invldValTxt_B1;

    /** DS_INV_SLS_CR_PK_B1*/
	public final EZDSBigDecimalItem              dsInvSlsCrPk_B1;

    /** DFRD_REV_FLG_B1*/
	public final EZDSStringItem              dfrdRevFlg_B1;

    /** TRX_CD_B1*/
	public final EZDSStringItem              trxCd_B1;

    /** TRX_RSN_CD_B1*/
	public final EZDSStringItem              trxRsnCd_B1;

    /** COA_BR_CD_B1*/
	public final EZDSStringItem              coaBrCd_B1;

    /** COA_BR_NM_B1*/
	public final EZDSStringItem              coaBrNm_B1;

    /** DEAL_DFRD_BAL_AMT_B1*/
	public final EZDSBigDecimalItem              dealDfrdBalAmt_B1;

    /** FUNC_DFRD_BAL_AMT_B1*/
	public final EZDSBigDecimalItem              funcDfrdBalAmt_B1;

    /** REV_RECOG_CNT_B1*/
	public final EZDSBigDecimalItem              revRecogCnt_B1;

    /** FIRST_REV_RECOG_DT_B1*/
	public final EZDSDateItem              firstRevRecogDt_B1;

    /** REV_RECOG_PROC_STS_CD_B1*/
	public final EZDSStringItem              revRecogProcStsCd_B1;

    /** DS_CPO_CONFIG_PK_B1*/
	public final EZDSBigDecimalItem              dsCpoConfigPk_B1;

    /** SLS_REP_ROLE_TP_CD_B1*/
	public final EZDSStringItem              slsRepRoleTpCd_B1;

    /** CPLT_REV_RECOG_DT_B1*/
	public final EZDSDateItem              cpltRevRecogDt_B1;

    /** COA_CMPY_CD_B1*/
	public final EZDSStringItem              coaCmpyCd_B1;

    /** COA_CC_CD_B1*/
	public final EZDSStringItem              coaCcCd_B1;

    /** COA_ACCT_CD_B1*/
	public final EZDSStringItem              coaAcctCd_B1;

    /** COA_PROD_CD_B1*/
	public final EZDSStringItem              coaProdCd_B1;

    /** COA_CH_CD_B1*/
	public final EZDSStringItem              coaChCd_B1;

    /** COA_AFFL_CD_B1*/
	public final EZDSStringItem              coaAfflCd_B1;

    /** COA_PROJ_CD_B1*/
	public final EZDSStringItem              coaProjCd_B1;

    /** COA_EXTN_CD_B1*/
	public final EZDSStringItem              coaExtnCd_B1;

    /** FIX_DURN_FLG_B1*/
	public final EZDSStringItem              fixDurnFlg_B1;

    /** NEXT_REV_RECOG_DT_B1*/
	public final EZDSDateItem              nextRevRecogDt_B1;

    /** DEAL_SCHD_REV_AMT_B1*/
	public final EZDSBigDecimalItem              dealSchdRevAmt_B1;

    /** XX_REC_HIST_CRAT_TS_B*/
	public final EZDSStringItem              xxRecHistCratTs_B;

    /** XX_REC_HIST_CRAT_BY_NM_B*/
	public final EZDSStringItem              xxRecHistCratByNm_B;

    /** XX_REC_HIST_UPD_TS_B*/
	public final EZDSStringItem              xxRecHistUpdTs_B;

    /** XX_REC_HIST_UPD_BY_NM_B*/
	public final EZDSStringItem              xxRecHistUpdByNm_B;

    /** XX_REC_HIST_TBL_NM_B*/
	public final EZDSStringItem              xxRecHistTblNm_B;


	/**
	 * NFCL3000_BSMsg is constructor.
	 * The initialization when the instance of NFCL3000_BSMsg is generated.
	 */
	public NFCL3000_BSMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3000_BSMsg is constructor.
	 * The initialization when the instance of NFCL3000_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3000_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxHldFlg_B1 = (EZDSStringItem)newItem("xxHldFlg_B1");
		xxPgFlg_B1 = (EZDSStringItem)newItem("xxPgFlg_B1");
		xxChkBox_B1 = (EZDSStringItem)newItem("xxChkBox_B1");
		xxLineNum_B1 = (EZDSStringItem)newItem("xxLineNum_B1");
		invBolLineNum_B1 = (EZDSStringItem)newItem("invBolLineNum_B1");
		invLineNum_B1 = (EZDSStringItem)newItem("invLineNum_B1");
		invLineSubNum_B1 = (EZDSStringItem)newItem("invLineSubNum_B1");
		invTrxLineSubNum_B1 = (EZDSStringItem)newItem("invTrxLineSubNum_B1");
		mdseCd_B1 = (EZDSStringItem)newItem("mdseCd_B1");
		invLineSplPct_B1 = (EZDSBigDecimalItem)newItem("invLineSplPct_B1");
		invLineSplPct_B2 = (EZDSBigDecimalItem)newItem("invLineSplPct_B2");
		tocNm_B1 = (EZDSStringItem)newItem("tocNm_B1");
		slsRepTocCd_B1 = (EZDSStringItem)newItem("slsRepTocCd_B1");
		psnNum_B1 = (EZDSStringItem)newItem("psnNum_B1");
		slsRepCrPct_B1 = (EZDSBigDecimalItem)newItem("slsRepCrPct_B1");
		slsRepCrPct_B2 = (EZDSBigDecimalItem)newItem("slsRepCrPct_B2");
		dealSlsCrAmt_B1 = (EZDSBigDecimalItem)newItem("dealSlsCrAmt_B1");
		xxTotAmt_B1 = (EZDSBigDecimalItem)newItem("xxTotAmt_B1");
		slsRepBrCd_B1 = (EZDSStringItem)newItem("slsRepBrCd_B1");
		manInvCratCmntTxt_B1 = (EZDSStringItem)newItem("manInvCratCmntTxt_B1");
		dfrdAcctgRuleCd_B1 = (EZDSStringItem)newItem("dfrdAcctgRuleCd_B1");
		dfrdAcctgRuleDurnAot_B1 = (EZDSBigDecimalItem)newItem("dfrdAcctgRuleDurnAot_B1");
		durnStartDt_B1 = (EZDSDateItem)newItem("durnStartDt_B1");
		invLineSplTpCd_B1 = (EZDSStringItem)newItem("invLineSplTpCd_B1");
		invErrMsgTxt_B1 = (EZDSStringItem)newItem("invErrMsgTxt_B1");
		invldValTxt_B1 = (EZDSStringItem)newItem("invldValTxt_B1");
		dsInvSlsCrPk_B1 = (EZDSBigDecimalItem)newItem("dsInvSlsCrPk_B1");
		dfrdRevFlg_B1 = (EZDSStringItem)newItem("dfrdRevFlg_B1");
		trxCd_B1 = (EZDSStringItem)newItem("trxCd_B1");
		trxRsnCd_B1 = (EZDSStringItem)newItem("trxRsnCd_B1");
		coaBrCd_B1 = (EZDSStringItem)newItem("coaBrCd_B1");
		coaBrNm_B1 = (EZDSStringItem)newItem("coaBrNm_B1");
		dealDfrdBalAmt_B1 = (EZDSBigDecimalItem)newItem("dealDfrdBalAmt_B1");
		funcDfrdBalAmt_B1 = (EZDSBigDecimalItem)newItem("funcDfrdBalAmt_B1");
		revRecogCnt_B1 = (EZDSBigDecimalItem)newItem("revRecogCnt_B1");
		firstRevRecogDt_B1 = (EZDSDateItem)newItem("firstRevRecogDt_B1");
		revRecogProcStsCd_B1 = (EZDSStringItem)newItem("revRecogProcStsCd_B1");
		dsCpoConfigPk_B1 = (EZDSBigDecimalItem)newItem("dsCpoConfigPk_B1");
		slsRepRoleTpCd_B1 = (EZDSStringItem)newItem("slsRepRoleTpCd_B1");
		cpltRevRecogDt_B1 = (EZDSDateItem)newItem("cpltRevRecogDt_B1");
		coaCmpyCd_B1 = (EZDSStringItem)newItem("coaCmpyCd_B1");
		coaCcCd_B1 = (EZDSStringItem)newItem("coaCcCd_B1");
		coaAcctCd_B1 = (EZDSStringItem)newItem("coaAcctCd_B1");
		coaProdCd_B1 = (EZDSStringItem)newItem("coaProdCd_B1");
		coaChCd_B1 = (EZDSStringItem)newItem("coaChCd_B1");
		coaAfflCd_B1 = (EZDSStringItem)newItem("coaAfflCd_B1");
		coaProjCd_B1 = (EZDSStringItem)newItem("coaProjCd_B1");
		coaExtnCd_B1 = (EZDSStringItem)newItem("coaExtnCd_B1");
		fixDurnFlg_B1 = (EZDSStringItem)newItem("fixDurnFlg_B1");
		nextRevRecogDt_B1 = (EZDSDateItem)newItem("nextRevRecogDt_B1");
		dealSchdRevAmt_B1 = (EZDSBigDecimalItem)newItem("dealSchdRevAmt_B1");
		xxRecHistCratTs_B = (EZDSStringItem)newItem("xxRecHistCratTs_B");
		xxRecHistCratByNm_B = (EZDSStringItem)newItem("xxRecHistCratByNm_B");
		xxRecHistUpdTs_B = (EZDSStringItem)newItem("xxRecHistUpdTs_B");
		xxRecHistUpdByNm_B = (EZDSStringItem)newItem("xxRecHistUpdByNm_B");
		xxRecHistTblNm_B = (EZDSStringItem)newItem("xxRecHistTblNm_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3000_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3000_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxHldFlg_B1", "xxHldFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxPgFlg_B1", "xxPgFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxChkBox_B1", "xxChkBox_B1", "B1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxLineNum_B1", "xxLineNum_B1", "B1", null, TYPE_HANKAKUEISU, "11", null},
	{"invBolLineNum_B1", "invBolLineNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"invLineNum_B1", "invLineNum_B1", "B1", null, TYPE_HANKAKUEISU, "5", null},
	{"invLineSubNum_B1", "invLineSubNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"invTrxLineSubNum_B1", "invTrxLineSubNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"mdseCd_B1", "mdseCd_B1", "B1", null, TYPE_HANKAKUEISU, "16", null},
	{"invLineSplPct_B1", "invLineSplPct_B1", "B1", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"invLineSplPct_B2", "invLineSplPct_B2", "B2", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"tocNm_B1", "tocNm_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"slsRepTocCd_B1", "slsRepTocCd_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"psnNum_B1", "psnNum_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"slsRepCrPct_B1", "slsRepCrPct_B1", "B1", null, TYPE_SEISU_SYOSU, "5", "2"},
	{"slsRepCrPct_B2", "slsRepCrPct_B2", "B2", null, TYPE_SEISU_SYOSU, "5", "2"},
	{"dealSlsCrAmt_B1", "dealSlsCrAmt_B1", "B1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotAmt_B1", "xxTotAmt_B1", "B1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"slsRepBrCd_B1", "slsRepBrCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"manInvCratCmntTxt_B1", "manInvCratCmntTxt_B1", "B1", null, TYPE_HANKAKUEISU, "1000", null},
	{"dfrdAcctgRuleCd_B1", "dfrdAcctgRuleCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"dfrdAcctgRuleDurnAot_B1", "dfrdAcctgRuleDurnAot_B1", "B1", null, TYPE_SEISU_SYOSU, "7", "4"},
	{"durnStartDt_B1", "durnStartDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"invLineSplTpCd_B1", "invLineSplTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"invErrMsgTxt_B1", "invErrMsgTxt_B1", "B1", null, TYPE_HANKAKUEISU, "1000", null},
	{"invldValTxt_B1", "invldValTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsInvSlsCrPk_B1", "dsInvSlsCrPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dfrdRevFlg_B1", "dfrdRevFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"trxCd_B1", "trxCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"trxRsnCd_B1", "trxRsnCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"coaBrCd_B1", "coaBrCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaBrNm_B1", "coaBrNm_B1", "B1", null, TYPE_HANKAKUEISU, "240", null},
	{"dealDfrdBalAmt_B1", "dealDfrdBalAmt_B1", "B1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcDfrdBalAmt_B1", "funcDfrdBalAmt_B1", "B1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"revRecogCnt_B1", "revRecogCnt_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"firstRevRecogDt_B1", "firstRevRecogDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"revRecogProcStsCd_B1", "revRecogProcStsCd_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"dsCpoConfigPk_B1", "dsCpoConfigPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"slsRepRoleTpCd_B1", "slsRepRoleTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "10", null},
	{"cpltRevRecogDt_B1", "cpltRevRecogDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"coaCmpyCd_B1", "coaCmpyCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaCcCd_B1", "coaCcCd_B1", "B1", null, TYPE_HANKAKUEISU, "6", null},
	{"coaAcctCd_B1", "coaAcctCd_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdCd_B1", "coaProdCd_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"coaChCd_B1", "coaChCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaAfflCd_B1", "coaAfflCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaProjCd_B1", "coaProjCd_B1", "B1", null, TYPE_HANKAKUEISU, "4", null},
	{"coaExtnCd_B1", "coaExtnCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"fixDurnFlg_B1", "fixDurnFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"nextRevRecogDt_B1", "nextRevRecogDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"dealSchdRevAmt_B1", "dealSchdRevAmt_B1", "B1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxRecHistCratTs_B", "xxRecHistCratTs_B", "B", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_B", "xxRecHistCratByNm_B", "B", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_B", "xxRecHistUpdTs_B", "B", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_B", "xxRecHistUpdByNm_B", "B", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_B", "xxRecHistTblNm_B", "B", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHldFlg_B1
        {"XX_PG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPgFlg_B1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"XX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineNum_B1
        {"INV_BOL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invBolLineNum_B1
        {"INV_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineNum_B1
        {"INV_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineSubNum_B1
        {"INV_TRX_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTrxLineSubNum_B1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_B1
        {"INV_LINE_SPL_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineSplPct_B1
        {"INV_LINE_SPL_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineSplPct_B2
        {"TOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocNm_B1
        {"SLS_REP_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd_B1
        {"PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_B1
        {"SLS_REP_CR_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepCrPct_B1
        {"SLS_REP_CR_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepCrPct_B2
        {"DEAL_SLS_CR_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealSlsCrAmt_B1
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_B1
        {"SLS_REP_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepBrCd_B1
        {"MAN_INV_CRAT_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manInvCratCmntTxt_B1
        {"DFRD_ACCTG_RULE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dfrdAcctgRuleCd_B1
        {"DFRD_ACCTG_RULE_DURN_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dfrdAcctgRuleDurnAot_B1
        {"DURN_START_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//durnStartDt_B1
        {"INV_LINE_SPL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineSplTpCd_B1
        {"INV_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invErrMsgTxt_B1
        {"INVLD_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invldValTxt_B1
        {"DS_INV_SLS_CR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsInvSlsCrPk_B1
        {"DFRD_REV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dfrdRevFlg_B1
        {"TRX_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxCd_B1
        {"TRX_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRsnCd_B1
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_B1
        {"COA_BR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrNm_B1
        {"DEAL_DFRD_BAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealDfrdBalAmt_B1
        {"FUNC_DFRD_BAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcDfrdBalAmt_B1
        {"REV_RECOG_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//revRecogCnt_B1
        {"FIRST_REV_RECOG_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstRevRecogDt_B1
        {"REV_RECOG_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//revRecogProcStsCd_B1
        {"DS_CPO_CONFIG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoConfigPk_B1
        {"SLS_REP_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepRoleTpCd_B1
        {"CPLT_REV_RECOG_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpltRevRecogDt_B1
        {"COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCmpyCd_B1
        {"COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcCd_B1
        {"COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctCd_B1
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_B1
        {"COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaChCd_B1
        {"COA_AFFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAfflCd_B1
        {"COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjCd_B1
        {"COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_B1
        {"FIX_DURN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fixDurnFlg_B1
        {"NEXT_REV_RECOG_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//nextRevRecogDt_B1
        {"DEAL_SCHD_REV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealSchdRevAmt_B1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_B
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_B
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_B
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_B
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_B
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
