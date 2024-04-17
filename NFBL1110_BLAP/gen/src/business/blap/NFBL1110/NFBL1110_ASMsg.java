//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170316173141000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBL1110_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFBL1110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFBL1110 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NFBL1110_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_GRP_FLG_A1*/
	public final EZDSStringItem              xxGrpFlg_A1;

    /** XX_LIST_NUM_A1*/
	public final EZDSBigDecimalItem              xxListNum_A1;

    /** _EZUpdateDateTime_IH*/
	public final EZDSStringItem              ezUpTime_IH;

    /** _EZUpTimeZone_IH*/
	public final EZDSStringItem              ezUpTimeZone_IH;

    /** AP_INV_NUM_IH*/
	public final EZDSStringItem              apInvNum_IH;

    /** PRNT_VND_CD_IH*/
	public final EZDSStringItem              prntVndCd_IH;

    /** AP_DS_WF_STS_CD_IH*/
	public final EZDSStringItem              apDsWfStsCd_IH;

    /** AP_DS_WF_STS_NM_IH*/
	public final EZDSStringItem              apDsWfStsNm_IH;

    /** AP_MAINT_INV_STS_CD_IH*/
	public final EZDSStringItem              apMaintInvStsCd_IH;

    /** AP_MAINT_INV_STS_NM_IH*/
	public final EZDSStringItem              apMaintInvStsNm_IH;

    /** INV_DT_IH*/
	public final EZDSDateItem              invDt_IH;

    /** VAR_CHAR_CONST_VAL_IH*/
	public final EZDSStringItem              varCharConstVal_IH;

    /** PRNT_VND_NM_IH*/
	public final EZDSStringItem              prntVndNm_IH;

    /** AP_VND_CD_HD*/
	public final EZDSStringItem              apVndCd_HD;

    /** VND_SITE_NM_IH*/
	public final EZDSStringItem              vndSiteNm_IH;

    /** AP_INV_AMT_IH*/
	public final EZDSBigDecimalItem              apInvAmt_IH;

    /** AP_MISC_AMT_IH*/
	public final EZDSBigDecimalItem              apMiscAmt_IH;

    /** AP_TAX_AMT_IH*/
	public final EZDSBigDecimalItem              apTaxAmt_IH;

    /** LATE_FEE_AMT_IH*/
	public final EZDSBigDecimalItem              lateFeeAmt_IH;

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

    /** _EZUpdateDateTime_SE*/
	public final EZDSStringItem              ezUpTime_SE;

    /** _EZUpTimeZone_SE*/
	public final EZDSStringItem              ezUpTimeZone_SE;

    /** SER_NUM_A1*/
	public final EZDSStringItem              serNum_A1;

    /** OVRD_SER_NUM_A1*/
	public final EZDSStringItem              ovrdSerNum_A1;

    /** START_DT_A1*/
	public final EZDSDateItem              startDt_A1;

    /** END_DT_A1*/
	public final EZDSDateItem              endDt_A1;

    /** BASE_AMT_A1*/
	public final EZDSBigDecimalItem              baseAmt_A1;

    /** _EZUpdateDateTime_ME*/
	public final EZDSStringItem              ezUpTime_ME;

    /** _EZUpTimeZone_ME*/
	public final EZDSStringItem              ezUpTimeZone_ME;

    /** CNTR_TP_CD_A1*/
	public final EZDSStringItem              cntrTpCd_A1;

    /** START_READ_MTR_CNT_A1*/
	public final EZDSBigDecimalItem              startReadMtrCnt_A1;

    /** END_READ_MTR_CNT_A1*/
	public final EZDSBigDecimalItem              endReadMtrCnt_A1;

    /** READ_MTR_CNT_A1*/
	public final EZDSBigDecimalItem              readMtrCnt_A1;

    /** AP_TOL_AMT_A1*/
	public final EZDSBigDecimalItem              apTolAmt_A1;

    /** XX_REC_HIST_CRAT_TS_A1*/
	public final EZDSStringItem              xxRecHistCratTs_A1;

    /** XX_REC_HIST_CRAT_BY_NM_A1*/
	public final EZDSStringItem              xxRecHistCratByNm_A1;

    /** XX_REC_HIST_UPD_TS_A1*/
	public final EZDSStringItem              xxRecHistUpdTs_A1;

    /** XX_REC_HIST_UPD_BY_NM_A1*/
	public final EZDSStringItem              xxRecHistUpdByNm_A1;

    /** XX_REC_HIST_TBL_NM_A1*/
	public final EZDSStringItem              xxRecHistTblNm_A1;

    /** INV_CMNT_TXT_CO*/
	public final EZDSStringItem              invCmntTxt_CO;

    /** AP_ADJ_AMT_CO*/
	public final EZDSBigDecimalItem              apAdjAmt_CO;

    /** AP_ADJ_RSN_CD_CO*/
	public final EZDSStringItem              apAdjRsnCd_CO;


	/**
	 * NFBL1110_ASMsg is constructor.
	 * The initialization when the instance of NFBL1110_ASMsg is generated.
	 */
	public NFBL1110_ASMsg() {
		this(false, -1);
	}

	/**
	 * NFBL1110_ASMsg is constructor.
	 * The initialization when the instance of NFBL1110_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFBL1110_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxGrpFlg_A1 = (EZDSStringItem)newItem("xxGrpFlg_A1");
		xxListNum_A1 = (EZDSBigDecimalItem)newItem("xxListNum_A1");
		ezUpTime_IH = (EZDSStringItem)newItem("ezUpTime_IH");
		ezUpTimeZone_IH = (EZDSStringItem)newItem("ezUpTimeZone_IH");
		apInvNum_IH = (EZDSStringItem)newItem("apInvNum_IH");
		prntVndCd_IH = (EZDSStringItem)newItem("prntVndCd_IH");
		apDsWfStsCd_IH = (EZDSStringItem)newItem("apDsWfStsCd_IH");
		apDsWfStsNm_IH = (EZDSStringItem)newItem("apDsWfStsNm_IH");
		apMaintInvStsCd_IH = (EZDSStringItem)newItem("apMaintInvStsCd_IH");
		apMaintInvStsNm_IH = (EZDSStringItem)newItem("apMaintInvStsNm_IH");
		invDt_IH = (EZDSDateItem)newItem("invDt_IH");
		varCharConstVal_IH = (EZDSStringItem)newItem("varCharConstVal_IH");
		prntVndNm_IH = (EZDSStringItem)newItem("prntVndNm_IH");
		apVndCd_HD = (EZDSStringItem)newItem("apVndCd_HD");
		vndSiteNm_IH = (EZDSStringItem)newItem("vndSiteNm_IH");
		apInvAmt_IH = (EZDSBigDecimalItem)newItem("apInvAmt_IH");
		apMiscAmt_IH = (EZDSBigDecimalItem)newItem("apMiscAmt_IH");
		apTaxAmt_IH = (EZDSBigDecimalItem)newItem("apTaxAmt_IH");
		lateFeeAmt_IH = (EZDSBigDecimalItem)newItem("lateFeeAmt_IH");
		xxRecHistCratTs = (EZDSStringItem)newItem("xxRecHistCratTs");
		xxRecHistCratByNm = (EZDSStringItem)newItem("xxRecHistCratByNm");
		xxRecHistUpdTs = (EZDSStringItem)newItem("xxRecHistUpdTs");
		xxRecHistUpdByNm = (EZDSStringItem)newItem("xxRecHistUpdByNm");
		xxRecHistTblNm = (EZDSStringItem)newItem("xxRecHistTblNm");
		ezUpTime_SE = (EZDSStringItem)newItem("ezUpTime_SE");
		ezUpTimeZone_SE = (EZDSStringItem)newItem("ezUpTimeZone_SE");
		serNum_A1 = (EZDSStringItem)newItem("serNum_A1");
		ovrdSerNum_A1 = (EZDSStringItem)newItem("ovrdSerNum_A1");
		startDt_A1 = (EZDSDateItem)newItem("startDt_A1");
		endDt_A1 = (EZDSDateItem)newItem("endDt_A1");
		baseAmt_A1 = (EZDSBigDecimalItem)newItem("baseAmt_A1");
		ezUpTime_ME = (EZDSStringItem)newItem("ezUpTime_ME");
		ezUpTimeZone_ME = (EZDSStringItem)newItem("ezUpTimeZone_ME");
		cntrTpCd_A1 = (EZDSStringItem)newItem("cntrTpCd_A1");
		startReadMtrCnt_A1 = (EZDSBigDecimalItem)newItem("startReadMtrCnt_A1");
		endReadMtrCnt_A1 = (EZDSBigDecimalItem)newItem("endReadMtrCnt_A1");
		readMtrCnt_A1 = (EZDSBigDecimalItem)newItem("readMtrCnt_A1");
		apTolAmt_A1 = (EZDSBigDecimalItem)newItem("apTolAmt_A1");
		xxRecHistCratTs_A1 = (EZDSStringItem)newItem("xxRecHistCratTs_A1");
		xxRecHistCratByNm_A1 = (EZDSStringItem)newItem("xxRecHistCratByNm_A1");
		xxRecHistUpdTs_A1 = (EZDSStringItem)newItem("xxRecHistUpdTs_A1");
		xxRecHistUpdByNm_A1 = (EZDSStringItem)newItem("xxRecHistUpdByNm_A1");
		xxRecHistTblNm_A1 = (EZDSStringItem)newItem("xxRecHistTblNm_A1");
		invCmntTxt_CO = (EZDSStringItem)newItem("invCmntTxt_CO");
		apAdjAmt_CO = (EZDSBigDecimalItem)newItem("apAdjAmt_CO");
		apAdjRsnCd_CO = (EZDSStringItem)newItem("apAdjRsnCd_CO");
	}

	/**
	 * get the type of array which is stored
	 * @return NFBL1110_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFBL1110_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxGrpFlg_A1", "xxGrpFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxListNum_A1", "xxListNum_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"ezUpTime_IH", "ezUpTime_IH", "IH", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_IH", "ezUpTimeZone_IH", "IH", null, TYPE_HANKAKUEISU, "5", null},
	{"apInvNum_IH", "apInvNum_IH", "IH", null, TYPE_HANKAKUEISU, "15", null},
	{"prntVndCd_IH", "prntVndCd_IH", "IH", null, TYPE_HANKAKUEISU, "30", null},
	{"apDsWfStsCd_IH", "apDsWfStsCd_IH", "IH", null, TYPE_HANKAKUEISU, "2", null},
	{"apDsWfStsNm_IH", "apDsWfStsNm_IH", "IH", null, TYPE_HANKAKUEISU, "30", null},
	{"apMaintInvStsCd_IH", "apMaintInvStsCd_IH", "IH", null, TYPE_HANKAKUEISU, "2", null},
	{"apMaintInvStsNm_IH", "apMaintInvStsNm_IH", "IH", null, TYPE_HANKAKUEISU, "30", null},
	{"invDt_IH", "invDt_IH", "IH", null, TYPE_NENTSUKIHI, "8", null},
	{"varCharConstVal_IH", "varCharConstVal_IH", "IH", null, TYPE_HANKAKUEISU, "1000", null},
	{"prntVndNm_IH", "prntVndNm_IH", "IH", null, TYPE_HANKAKUEISU, "240", null},
	{"apVndCd_HD", "apVndCd_HD", "HD", null, TYPE_HANKAKUEISU, "20", null},
	{"vndSiteNm_IH", "vndSiteNm_IH", "IH", null, TYPE_HANKAKUEISU, "20", null},
	{"apInvAmt_IH", "apInvAmt_IH", "IH", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"apMiscAmt_IH", "apMiscAmt_IH", "IH", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"apTaxAmt_IH", "apTaxAmt_IH", "IH", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"lateFeeAmt_IH", "lateFeeAmt_IH", "IH", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxRecHistCratTs", "xxRecHistCratTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm", "xxRecHistCratByNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs", "xxRecHistUpdTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm", "xxRecHistUpdByNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm", "xxRecHistTblNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"ezUpTime_SE", "ezUpTime_SE", "SE", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_SE", "ezUpTimeZone_SE", "SE", null, TYPE_HANKAKUEISU, "5", null},
	{"serNum_A1", "serNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"ovrdSerNum_A1", "ovrdSerNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"startDt_A1", "startDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"endDt_A1", "endDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"baseAmt_A1", "baseAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"ezUpTime_ME", "ezUpTime_ME", "ME", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_ME", "ezUpTimeZone_ME", "ME", null, TYPE_HANKAKUEISU, "5", null},
	{"cntrTpCd_A1", "cntrTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"startReadMtrCnt_A1", "startReadMtrCnt_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"endReadMtrCnt_A1", "endReadMtrCnt_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"readMtrCnt_A1", "readMtrCnt_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"apTolAmt_A1", "apTolAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxRecHistCratTs_A1", "xxRecHistCratTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A1", "xxRecHistCratByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A1", "xxRecHistUpdTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A1", "xxRecHistUpdByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A1", "xxRecHistTblNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"invCmntTxt_CO", "invCmntTxt_CO", "CO", null, TYPE_HANKAKUEISU, "260", null},
	{"apAdjAmt_CO", "apAdjAmt_CO", "CO", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"apAdjRsnCd_CO", "apAdjRsnCd_CO", "CO", null, TYPE_HANKAKUEISU, "3", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_GRP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGrpFlg_A1
        {"XX_LIST_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxListNum_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_IH
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_IH
        {"AP_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apInvNum_IH
        {"PRNT_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndCd_IH
        {"AP_DS_WF_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apDsWfStsCd_IH
        {"AP_DS_WF_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apDsWfStsNm_IH
        {"AP_MAINT_INV_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apMaintInvStsCd_IH
        {"AP_MAINT_INV_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apMaintInvStsNm_IH
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt_IH
        {"VAR_CHAR_CONST_VAL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//varCharConstVal_IH
        {"PRNT_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndNm_IH
        {"AP_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndCd_HD
        {"VND_SITE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndSiteNm_IH
        {"AP_INV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apInvAmt_IH
        {"AP_MISC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apMiscAmt_IH
        {"AP_TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apTaxAmt_IH
        {"LATE_FEE_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lateFeeAmt_IH
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_SE
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_SE
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A1
        {"OVRD_SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ovrdSerNum_A1
        {"START_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//startDt_A1
        {"END_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//endDt_A1
        {"BASE_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseAmt_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_ME
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_ME
        {"CNTR_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cntrTpCd_A1
        {"START_READ_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//startReadMtrCnt_A1
        {"END_READ_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//endReadMtrCnt_A1
        {"READ_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//readMtrCnt_A1
        {"AP_TOL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apTolAmt_A1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A1
        {"INV_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invCmntTxt_CO
        {"AP_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apAdjAmt_CO
        {"AP_ADJ_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apAdjRsnCd_CO
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

