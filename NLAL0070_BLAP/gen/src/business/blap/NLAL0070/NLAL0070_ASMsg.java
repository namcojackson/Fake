//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161216012747000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLAL0070_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLAL0070;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLAL0070 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLAL0070_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDSStringItem              xxChkBox_A1;

    /** WH_SCHD_REF_KEY_NUM_SQ_A1*/
	public final EZDSBigDecimalItem              whSchdRefKeyNumSq_A1;

    /** INVTY_LOC_CD_A1*/
	public final EZDSStringItem              invtyLocCd_A1;

    /** RWS_REF_NUM_A1*/
	public final EZDSStringItem              rwsRefNum_A1;

    /** IMPT_CNTNR_LOT_NUM_A1*/
	public final EZDSStringItem              imptCntnrLotNum_A1;

    /** ASN_NUM_A1*/
	public final EZDSStringItem              asnNum_A1;

    /** LGSC_DELY_TP_CD_A1*/
	public final EZDSStringItem              lgscDelyTpCd_A1;

    /** LGSC_DELY_TP_NM_A1*/
	public final EZDSStringItem              lgscDelyTpNm_A1;

    /** WH_IN_PRTY_FLG_A1*/
	public final EZDSStringItem              whInPrtyFlg_A1;

    /** INBD_VIS_EVENT_CD_A1*/
	public final EZDSStringItem              inbdVisEventCd_A1;

    /** INBD_VIS_EVENT_NM_A1*/
	public final EZDSStringItem              inbdVisEventNm_A1;

    /** IMPT_TRM_CD_A1*/
	public final EZDSStringItem              imptTrmCd_A1;

    /** IMPT_TRM_ETA_DT_A1*/
	public final EZDSDateItem              imptTrmEtaDt_A1;

    /** LTST_WH_IN_ETA_DT_A1*/
	public final EZDSDateItem              ltstWhInEtaDt_A1;

    /** TEMP_WH_IN_ETA_DT_A1*/
	public final EZDSDateItem              tempWhInEtaDt_A1;

    /** SCHD_WH_IN_ETA_DT_A1*/
	public final EZDSDateItem              schdWhInEtaDt_A1;

    /** SCHD_ETA_CHK_FLG_A1*/
	public final EZDSStringItem              schdEtaChkFlg_A1;

    /** FINAL_WH_IN_ETA_DT_A1*/
	public final EZDSDateItem              finalWhInEtaDt_A1;

    /** FINAL_ETA_CHK_FLG_A1*/
	public final EZDSStringItem              finalEtaChkFlg_A1;

    /** RAIL_AVAL_DT_A1*/
	public final EZDSDateItem              railAvalDt_A1;

    /** APPT_TM_TXT_A1*/
	public final EZDSStringItem              apptTmTxt_A1;

    /** APPT_DRYG_CARR_CD_A2*/
	public final EZDSStringItem              apptDrygCarrCd_A2;

    /** MDSE_CD_A1*/
	public final EZDSStringItem              mdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDSStringItem              mdseDescShortTxt_A1;

    /** REQ_STK_IN_QTY_A1*/
	public final EZDSBigDecimalItem              reqStkInQty_A1;

    /** QTY_PKG_APVL_STS_CD_A1*/
	public final EZDSStringItem              qtyPkgApvlStsCd_A1;

    /** CARR_CD_A1*/
	public final EZDSStringItem              carrCd_A1;

    /** LOC_NM_A1*/
	public final EZDSStringItem              locNm_A1;

    /** IMPT_INV_VESL_NM_A1*/
	public final EZDSStringItem              imptInvVeslNm_A1;

    /** IMPT_INV_BOL_NUM_A1*/
	public final EZDSStringItem              imptInvBolNum_A1;

    /** RWS_REF_NUM_A2*/
	public final EZDSStringItem              rwsRefNum_A2;

    /** RWS_STS_CD_A1*/
	public final EZDSStringItem              rwsStsCd_A1;

    /** SCE_ORD_TP_CD_A1*/
	public final EZDSStringItem              sceOrdTpCd_A1;

    /** SCHD_TP_CD_A1*/
	public final EZDSStringItem              schdTpCd_A1;

    /** SCHD_TP_NM_A1*/
	public final EZDSStringItem              schdTpNm_A1;

    /** ORG_WH_IN_ETA_DT_A1*/
	public final EZDSDateItem              orgWhInEtaDt_A1;

    /** IMPT_TRM_NM_A1*/
	public final EZDSStringItem              imptTrmNm_A1;

    /** WH_CD_A1*/
	public final EZDSStringItem              whCd_A1;

    /** SCHD_WH_IN_ETA_DT_A2*/
	public final EZDSDateItem              schdWhInEtaDt_A2;

    /** FINAL_WH_IN_ETA_DT_A2*/
	public final EZDSDateItem              finalWhInEtaDt_A2;

    /** WH_SCHD_CMNT_TXT_A1*/
	public final EZDSStringItem              whSchdCmntTxt_A1;

    /** SCHD_ETA_DT_A1*/
	public final EZDSDateItem              schdEtaDt_A1;

    /** FINAL_ETA_DT_A1*/
	public final EZDSDateItem              finalEtaDt_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDSStringItem              ezUpTime_A1;

    /** IMPT_INV_LCL_FLG_A1*/
	public final EZDSStringItem              imptInvLclFlg_A1;

    /** IMPT_INV_NUM_A1*/
	public final EZDSStringItem              imptInvNum_A1;

    /** IMPT_INV_CNSGN_CD_A1*/
	public final EZDSStringItem              imptInvCnsgnCd_A1;

    /** IMPT_CNTNR_NUM_A1*/
	public final EZDSStringItem              imptCntnrNum_A1;

    /** INLND_SHPG_METH_CD_A1*/
	public final EZDSStringItem              inlndShpgMethCd_A1;

    /** SHPG_METH_TP_CD_A1*/
	public final EZDSStringItem              shpgMethTpCd_A1;

    /** IMPT_TRM_TO_WH_DAYS_AOT_A1*/
	public final EZDSBigDecimalItem              imptTrmToWhDaysAot_A1;


	/**
	 * NLAL0070_ASMsg is constructor.
	 * The initialization when the instance of NLAL0070_ASMsg is generated.
	 */
	public NLAL0070_ASMsg() {
		this(false, -1);
	}

	/**
	 * NLAL0070_ASMsg is constructor.
	 * The initialization when the instance of NLAL0070_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLAL0070_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDSStringItem)newItem("xxChkBox_A1");
		whSchdRefKeyNumSq_A1 = (EZDSBigDecimalItem)newItem("whSchdRefKeyNumSq_A1");
		invtyLocCd_A1 = (EZDSStringItem)newItem("invtyLocCd_A1");
		rwsRefNum_A1 = (EZDSStringItem)newItem("rwsRefNum_A1");
		imptCntnrLotNum_A1 = (EZDSStringItem)newItem("imptCntnrLotNum_A1");
		asnNum_A1 = (EZDSStringItem)newItem("asnNum_A1");
		lgscDelyTpCd_A1 = (EZDSStringItem)newItem("lgscDelyTpCd_A1");
		lgscDelyTpNm_A1 = (EZDSStringItem)newItem("lgscDelyTpNm_A1");
		whInPrtyFlg_A1 = (EZDSStringItem)newItem("whInPrtyFlg_A1");
		inbdVisEventCd_A1 = (EZDSStringItem)newItem("inbdVisEventCd_A1");
		inbdVisEventNm_A1 = (EZDSStringItem)newItem("inbdVisEventNm_A1");
		imptTrmCd_A1 = (EZDSStringItem)newItem("imptTrmCd_A1");
		imptTrmEtaDt_A1 = (EZDSDateItem)newItem("imptTrmEtaDt_A1");
		ltstWhInEtaDt_A1 = (EZDSDateItem)newItem("ltstWhInEtaDt_A1");
		tempWhInEtaDt_A1 = (EZDSDateItem)newItem("tempWhInEtaDt_A1");
		schdWhInEtaDt_A1 = (EZDSDateItem)newItem("schdWhInEtaDt_A1");
		schdEtaChkFlg_A1 = (EZDSStringItem)newItem("schdEtaChkFlg_A1");
		finalWhInEtaDt_A1 = (EZDSDateItem)newItem("finalWhInEtaDt_A1");
		finalEtaChkFlg_A1 = (EZDSStringItem)newItem("finalEtaChkFlg_A1");
		railAvalDt_A1 = (EZDSDateItem)newItem("railAvalDt_A1");
		apptTmTxt_A1 = (EZDSStringItem)newItem("apptTmTxt_A1");
		apptDrygCarrCd_A2 = (EZDSStringItem)newItem("apptDrygCarrCd_A2");
		mdseCd_A1 = (EZDSStringItem)newItem("mdseCd_A1");
		mdseDescShortTxt_A1 = (EZDSStringItem)newItem("mdseDescShortTxt_A1");
		reqStkInQty_A1 = (EZDSBigDecimalItem)newItem("reqStkInQty_A1");
		qtyPkgApvlStsCd_A1 = (EZDSStringItem)newItem("qtyPkgApvlStsCd_A1");
		carrCd_A1 = (EZDSStringItem)newItem("carrCd_A1");
		locNm_A1 = (EZDSStringItem)newItem("locNm_A1");
		imptInvVeslNm_A1 = (EZDSStringItem)newItem("imptInvVeslNm_A1");
		imptInvBolNum_A1 = (EZDSStringItem)newItem("imptInvBolNum_A1");
		rwsRefNum_A2 = (EZDSStringItem)newItem("rwsRefNum_A2");
		rwsStsCd_A1 = (EZDSStringItem)newItem("rwsStsCd_A1");
		sceOrdTpCd_A1 = (EZDSStringItem)newItem("sceOrdTpCd_A1");
		schdTpCd_A1 = (EZDSStringItem)newItem("schdTpCd_A1");
		schdTpNm_A1 = (EZDSStringItem)newItem("schdTpNm_A1");
		orgWhInEtaDt_A1 = (EZDSDateItem)newItem("orgWhInEtaDt_A1");
		imptTrmNm_A1 = (EZDSStringItem)newItem("imptTrmNm_A1");
		whCd_A1 = (EZDSStringItem)newItem("whCd_A1");
		schdWhInEtaDt_A2 = (EZDSDateItem)newItem("schdWhInEtaDt_A2");
		finalWhInEtaDt_A2 = (EZDSDateItem)newItem("finalWhInEtaDt_A2");
		whSchdCmntTxt_A1 = (EZDSStringItem)newItem("whSchdCmntTxt_A1");
		schdEtaDt_A1 = (EZDSDateItem)newItem("schdEtaDt_A1");
		finalEtaDt_A1 = (EZDSDateItem)newItem("finalEtaDt_A1");
		ezUpTime_A1 = (EZDSStringItem)newItem("ezUpTime_A1");
		imptInvLclFlg_A1 = (EZDSStringItem)newItem("imptInvLclFlg_A1");
		imptInvNum_A1 = (EZDSStringItem)newItem("imptInvNum_A1");
		imptInvCnsgnCd_A1 = (EZDSStringItem)newItem("imptInvCnsgnCd_A1");
		imptCntnrNum_A1 = (EZDSStringItem)newItem("imptCntnrNum_A1");
		inlndShpgMethCd_A1 = (EZDSStringItem)newItem("inlndShpgMethCd_A1");
		shpgMethTpCd_A1 = (EZDSStringItem)newItem("shpgMethTpCd_A1");
		imptTrmToWhDaysAot_A1 = (EZDSBigDecimalItem)newItem("imptTrmToWhDaysAot_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLAL0070_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLAL0070_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"whSchdRefKeyNumSq_A1", "whSchdRefKeyNumSq_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"invtyLocCd_A1", "invtyLocCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rwsRefNum_A1", "rwsRefNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"imptCntnrLotNum_A1", "imptCntnrLotNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"asnNum_A1", "asnNum_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"lgscDelyTpCd_A1", "lgscDelyTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"lgscDelyTpNm_A1", "lgscDelyTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"whInPrtyFlg_A1", "whInPrtyFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"inbdVisEventCd_A1", "inbdVisEventCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"inbdVisEventNm_A1", "inbdVisEventNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"imptTrmCd_A1", "imptTrmCd_A1", "A1", null, TYPE_HANKAKUEISU, "6", null},
	{"imptTrmEtaDt_A1", "imptTrmEtaDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"ltstWhInEtaDt_A1", "ltstWhInEtaDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"tempWhInEtaDt_A1", "tempWhInEtaDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"schdWhInEtaDt_A1", "schdWhInEtaDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"schdEtaChkFlg_A1", "schdEtaChkFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"finalWhInEtaDt_A1", "finalWhInEtaDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"finalEtaChkFlg_A1", "finalEtaChkFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"railAvalDt_A1", "railAvalDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"apptTmTxt_A1", "apptTmTxt_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"apptDrygCarrCd_A2", "apptDrygCarrCd_A2", "A2", null, TYPE_HANKAKUEISU, "20", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"reqStkInQty_A1", "reqStkInQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"qtyPkgApvlStsCd_A1", "qtyPkgApvlStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"carrCd_A1", "carrCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_A1", "locNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"imptInvVeslNm_A1", "imptInvVeslNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"imptInvBolNum_A1", "imptInvBolNum_A1", "A1", null, TYPE_HANKAKUEISU, "25", null},
	{"rwsRefNum_A2", "rwsRefNum_A2", "A2", null, TYPE_HANKAKUEISU, "15", null},
	{"rwsStsCd_A1", "rwsStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"sceOrdTpCd_A1", "sceOrdTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"schdTpCd_A1", "schdTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"schdTpNm_A1", "schdTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"orgWhInEtaDt_A1", "orgWhInEtaDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"imptTrmNm_A1", "imptTrmNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"whCd_A1", "whCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"schdWhInEtaDt_A2", "schdWhInEtaDt_A2", "A2", null, TYPE_NENTSUKIHI, "8", null},
	{"finalWhInEtaDt_A2", "finalWhInEtaDt_A2", "A2", null, TYPE_NENTSUKIHI, "8", null},
	{"whSchdCmntTxt_A1", "whSchdCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "90", null},
	{"schdEtaDt_A1", "schdEtaDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"finalEtaDt_A1", "finalEtaDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"imptInvLclFlg_A1", "imptInvLclFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"imptInvNum_A1", "imptInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"imptInvCnsgnCd_A1", "imptInvCnsgnCd_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	{"imptCntnrNum_A1", "imptCntnrNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"inlndShpgMethCd_A1", "inlndShpgMethCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"shpgMethTpCd_A1", "shpgMethTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"imptTrmToWhDaysAot_A1", "imptTrmToWhDaysAot_A1", "A1", null, TYPE_SEISU_SYOSU, "3", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"WH_SCHD_REF_KEY_NUM_SQ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whSchdRefKeyNumSq_A1
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_A1
        {"RWS_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsRefNum_A1
        {"IMPT_CNTNR_LOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptCntnrLotNum_A1
        {"ASN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnNum_A1
        {"LGSC_DELY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lgscDelyTpCd_A1
        {"LGSC_DELY_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lgscDelyTpNm_A1
        {"WH_IN_PRTY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whInPrtyFlg_A1
        {"INBD_VIS_EVENT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdVisEventCd_A1
        {"INBD_VIS_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdVisEventNm_A1
        {"IMPT_TRM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptTrmCd_A1
        {"IMPT_TRM_ETA_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptTrmEtaDt_A1
        {"LTST_WH_IN_ETA_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ltstWhInEtaDt_A1
        {"TEMP_WH_IN_ETA_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tempWhInEtaDt_A1
        {"SCHD_WH_IN_ETA_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdWhInEtaDt_A1
        {"SCHD_ETA_CHK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdEtaChkFlg_A1
        {"FINAL_WH_IN_ETA_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//finalWhInEtaDt_A1
        {"FINAL_ETA_CHK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//finalEtaChkFlg_A1
        {"RAIL_AVAL_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//railAvalDt_A1
        {"APPT_TM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apptTmTxt_A1
        {"APPT_DRYG_CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apptDrygCarrCd_A2
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"REQ_STK_IN_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//reqStkInQty_A1
        {"QTY_PKG_APVL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//qtyPkgApvlStsCd_A1
        {"CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd_A1
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_A1
        {"IMPT_INV_VESL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvVeslNm_A1
        {"IMPT_INV_BOL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvBolNum_A1
        {"RWS_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsRefNum_A2
        {"RWS_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsStsCd_A1
        {"SCE_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpCd_A1
        {"SCHD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdTpCd_A1
        {"SCHD_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdTpNm_A1
        {"ORG_WH_IN_ETA_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgWhInEtaDt_A1
        {"IMPT_TRM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptTrmNm_A1
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_A1
        {"SCHD_WH_IN_ETA_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdWhInEtaDt_A2
        {"FINAL_WH_IN_ETA_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//finalWhInEtaDt_A2
        {"WH_SCHD_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whSchdCmntTxt_A1
        {"SCHD_ETA_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdEtaDt_A1
        {"FINAL_ETA_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//finalEtaDt_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"IMPT_INV_LCL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvLclFlg_A1
        {"IMPT_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvNum_A1
        {"IMPT_INV_CNSGN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvCnsgnCd_A1
        {"IMPT_CNTNR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptCntnrNum_A1
        {"INLND_SHPG_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inlndShpgMethCd_A1
        {"SHPG_METH_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgMethTpCd_A1
        {"IMPT_TRM_TO_WH_DAYS_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptTrmToWhDaysAot_A1
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

