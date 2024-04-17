//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160722005238000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLAL0070F00FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLAL0070F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NLAL0070F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** INVTY_LOC_CD_E1*/
	public final EZDFStringItem              invtyLocCd_E1;

    /** RWS_REF_NUM_E1*/
	public final EZDFStringItem              rwsRefNum_E1;

    /** LGSC_DELY_TP_NM_E1*/
	public final EZDFStringItem              lgscDelyTpNm_E1;

    /** INLND_SHPG_METH_CD_E1*/
	public final EZDFStringItem              inlndShpgMethCd_E1;

    /** WH_IN_PRTY_FLG_E1*/
	public final EZDFStringItem              whInPrtyFlg_E1;

    /** INBD_VIS_EVENT_NM_E1*/
	public final EZDFStringItem              inbdVisEventNm_E1;

    /** IMPT_TRM_NM_E1*/
	public final EZDFStringItem              imptTrmNm_E1;

    /** XX_DT_TXT_E1*/
	public final EZDFStringItem              xxDtTxt_E1;

    /** XX_DT_TXT_E2*/
	public final EZDFStringItem              xxDtTxt_E2;

    /** XX_DT_TXT_E3*/
	public final EZDFStringItem              xxDtTxt_E3;

    /** XX_DT_TXT_E4*/
	public final EZDFStringItem              xxDtTxt_E4;

    /** SCHD_ETA_CHK_FLG_E1*/
	public final EZDFStringItem              schdEtaChkFlg_E1;

    /** XX_DT_TXT_E5*/
	public final EZDFStringItem              xxDtTxt_E5;

    /** FINAL_ETA_CHK_FLG_E1*/
	public final EZDFStringItem              finalEtaChkFlg_E1;

    /** XX_DT_TXT_E6*/
	public final EZDFStringItem              xxDtTxt_E6;

    /** APPT_TM_TXT_E1*/
	public final EZDFStringItem              apptTmTxt_E1;

    /** APPT_DRYG_CARR_CD_E1*/
	public final EZDFStringItem              apptDrygCarrCd_E1;

    /** MDSE_CD_E1*/
	public final EZDFStringItem              mdseCd_E1;

    /** MDSE_DESC_SHORT_TXT_E1*/
	public final EZDFStringItem              mdseDescShortTxt_E1;

    /** REQ_STK_IN_QTY_E1*/
	public final EZDFBigDecimalItem              reqStkInQty_E1;

    /** QTY_PKG_APVL_STS_CD_E1*/
	public final EZDFStringItem              qtyPkgApvlStsCd_E1;

    /** WH_CD_E1*/
	public final EZDFStringItem              whCd_E1;

    /** LOC_NM_E1*/
	public final EZDFStringItem              locNm_E1;

    /** IMPT_INV_VESL_NM_E1*/
	public final EZDFStringItem              imptInvVeslNm_E1;

    /** IMPT_INV_BOL_NUM_E1*/
	public final EZDFStringItem              imptInvBolNum_E1;

    /** IMPT_INV_CNSGN_CD_E1*/
	public final EZDFStringItem              imptInvCnsgnCd_E1;

    /** IMPT_INV_NUM_E1*/
	public final EZDFStringItem              imptInvNum_E1;

    /** IMPT_CNTNR_NUM_E1*/
	public final EZDFStringItem              imptCntnrNum_E1;

    /** RWS_REF_NUM_E2*/
	public final EZDFStringItem              rwsRefNum_E2;

    /** SCE_ORD_TP_CD_E1*/
	public final EZDFStringItem              sceOrdTpCd_E1;

    /** SCHD_TP_NM_E1*/
	public final EZDFStringItem              schdTpNm_E1;

    /** WH_SCHD_CMNT_TXT_E1*/
	public final EZDFStringItem              whSchdCmntTxt_E1;


	/**
	 * NLAL0070F00FMsg is constructor.
	 * The initialization when the instance of NLAL0070F00FMsg is generated.
	 */
	public NLAL0070F00FMsg() {
		this(false, -1);
	}

	/**
	 * NLAL0070F00FMsg is constructor.
	 * The initialization when the instance of NLAL0070F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLAL0070F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		invtyLocCd_E1 = (EZDFStringItem)newItem("invtyLocCd_E1");
		rwsRefNum_E1 = (EZDFStringItem)newItem("rwsRefNum_E1");
		lgscDelyTpNm_E1 = (EZDFStringItem)newItem("lgscDelyTpNm_E1");
		inlndShpgMethCd_E1 = (EZDFStringItem)newItem("inlndShpgMethCd_E1");
		whInPrtyFlg_E1 = (EZDFStringItem)newItem("whInPrtyFlg_E1");
		inbdVisEventNm_E1 = (EZDFStringItem)newItem("inbdVisEventNm_E1");
		imptTrmNm_E1 = (EZDFStringItem)newItem("imptTrmNm_E1");
		xxDtTxt_E1 = (EZDFStringItem)newItem("xxDtTxt_E1");
		xxDtTxt_E2 = (EZDFStringItem)newItem("xxDtTxt_E2");
		xxDtTxt_E3 = (EZDFStringItem)newItem("xxDtTxt_E3");
		xxDtTxt_E4 = (EZDFStringItem)newItem("xxDtTxt_E4");
		schdEtaChkFlg_E1 = (EZDFStringItem)newItem("schdEtaChkFlg_E1");
		xxDtTxt_E5 = (EZDFStringItem)newItem("xxDtTxt_E5");
		finalEtaChkFlg_E1 = (EZDFStringItem)newItem("finalEtaChkFlg_E1");
		xxDtTxt_E6 = (EZDFStringItem)newItem("xxDtTxt_E6");
		apptTmTxt_E1 = (EZDFStringItem)newItem("apptTmTxt_E1");
		apptDrygCarrCd_E1 = (EZDFStringItem)newItem("apptDrygCarrCd_E1");
		mdseCd_E1 = (EZDFStringItem)newItem("mdseCd_E1");
		mdseDescShortTxt_E1 = (EZDFStringItem)newItem("mdseDescShortTxt_E1");
		reqStkInQty_E1 = (EZDFBigDecimalItem)newItem("reqStkInQty_E1");
		qtyPkgApvlStsCd_E1 = (EZDFStringItem)newItem("qtyPkgApvlStsCd_E1");
		whCd_E1 = (EZDFStringItem)newItem("whCd_E1");
		locNm_E1 = (EZDFStringItem)newItem("locNm_E1");
		imptInvVeslNm_E1 = (EZDFStringItem)newItem("imptInvVeslNm_E1");
		imptInvBolNum_E1 = (EZDFStringItem)newItem("imptInvBolNum_E1");
		imptInvCnsgnCd_E1 = (EZDFStringItem)newItem("imptInvCnsgnCd_E1");
		imptInvNum_E1 = (EZDFStringItem)newItem("imptInvNum_E1");
		imptCntnrNum_E1 = (EZDFStringItem)newItem("imptCntnrNum_E1");
		rwsRefNum_E2 = (EZDFStringItem)newItem("rwsRefNum_E2");
		sceOrdTpCd_E1 = (EZDFStringItem)newItem("sceOrdTpCd_E1");
		schdTpNm_E1 = (EZDFStringItem)newItem("schdTpNm_E1");
		whSchdCmntTxt_E1 = (EZDFStringItem)newItem("whSchdCmntTxt_E1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLAL0070F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLAL0070F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"invtyLocCd_E1", "invtyLocCd_E1", "E1", null, TYPE_HANKAKUEISU, "20", null},
	{"rwsRefNum_E1", "rwsRefNum_E1", "E1", null, TYPE_HANKAKUEISU, "15", null},
	{"lgscDelyTpNm_E1", "lgscDelyTpNm_E1", "E1", null, TYPE_HANKAKUEISU, "30", null},
	{"inlndShpgMethCd_E1", "inlndShpgMethCd_E1", "E1", null, TYPE_HANKAKUEISU, "2", null},
	{"whInPrtyFlg_E1", "whInPrtyFlg_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"inbdVisEventNm_E1", "inbdVisEventNm_E1", "E1", null, TYPE_HANKAKUEISU, "50", null},
	{"imptTrmNm_E1", "imptTrmNm_E1", "E1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxDtTxt_E1", "xxDtTxt_E1", "E1", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_E2", "xxDtTxt_E2", "E2", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_E3", "xxDtTxt_E3", "E3", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_E4", "xxDtTxt_E4", "E4", null, TYPE_HANKAKUEISU, "10", null},
	{"schdEtaChkFlg_E1", "schdEtaChkFlg_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDtTxt_E5", "xxDtTxt_E5", "E5", null, TYPE_HANKAKUEISU, "10", null},
	{"finalEtaChkFlg_E1", "finalEtaChkFlg_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDtTxt_E6", "xxDtTxt_E6", "E6", null, TYPE_HANKAKUEISU, "10", null},
	{"apptTmTxt_E1", "apptTmTxt_E1", "E1", null, TYPE_HANKAKUEISU, "8", null},
	{"apptDrygCarrCd_E1", "apptDrygCarrCd_E1", "E1", null, TYPE_HANKAKUEISU, "20", null},
	{"mdseCd_E1", "mdseCd_E1", "E1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_E1", "mdseDescShortTxt_E1", "E1", null, TYPE_HANKAKUEISU, "250", null},
	{"reqStkInQty_E1", "reqStkInQty_E1", "E1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"qtyPkgApvlStsCd_E1", "qtyPkgApvlStsCd_E1", "E1", null, TYPE_HANKAKUEISU, "2", null},
	{"whCd_E1", "whCd_E1", "E1", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_E1", "locNm_E1", "E1", null, TYPE_HANKAKUEISU, "60", null},
	{"imptInvVeslNm_E1", "imptInvVeslNm_E1", "E1", null, TYPE_HANKAKUEISU, "30", null},
	{"imptInvBolNum_E1", "imptInvBolNum_E1", "E1", null, TYPE_HANKAKUEISU, "25", null},
	{"imptInvCnsgnCd_E1", "imptInvCnsgnCd_E1", "E1", null, TYPE_HANKAKUEISU, "5", null},
	{"imptInvNum_E1", "imptInvNum_E1", "E1", null, TYPE_HANKAKUEISU, "15", null},
	{"imptCntnrNum_E1", "imptCntnrNum_E1", "E1", null, TYPE_HANKAKUEISU, "15", null},
	{"rwsRefNum_E2", "rwsRefNum_E2", "E2", null, TYPE_HANKAKUEISU, "15", null},
	{"sceOrdTpCd_E1", "sceOrdTpCd_E1", "E1", null, TYPE_HANKAKUEISU, "2", null},
	{"schdTpNm_E1", "schdTpNm_E1", "E1", null, TYPE_HANKAKUEISU, "30", null},
	{"whSchdCmntTxt_E1", "whSchdCmntTxt_E1", "E1", null, TYPE_HANKAKUEISU, "90", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_E1
        {"RWS_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsRefNum_E1
        {"LGSC_DELY_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lgscDelyTpNm_E1
        {"INLND_SHPG_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inlndShpgMethCd_E1
        {"WH_IN_PRTY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whInPrtyFlg_E1
        {"INBD_VIS_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdVisEventNm_E1
        {"IMPT_TRM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptTrmNm_E1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_E1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_E2
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_E3
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_E4
        {"SCHD_ETA_CHK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdEtaChkFlg_E1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_E5
        {"FINAL_ETA_CHK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//finalEtaChkFlg_E1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_E6
        {"APPT_TM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apptTmTxt_E1
        {"APPT_DRYG_CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apptDrygCarrCd_E1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_E1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_E1
        {"REQ_STK_IN_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//reqStkInQty_E1
        {"QTY_PKG_APVL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//qtyPkgApvlStsCd_E1
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_E1
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_E1
        {"IMPT_INV_VESL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvVeslNm_E1
        {"IMPT_INV_BOL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvBolNum_E1
        {"IMPT_INV_CNSGN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvCnsgnCd_E1
        {"IMPT_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvNum_E1
        {"IMPT_CNTNR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptCntnrNum_E1
        {"RWS_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsRefNum_E2
        {"SCE_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpCd_E1
        {"SCHD_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdTpNm_E1
        {"WH_SCHD_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whSchdCmntTxt_E1
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

