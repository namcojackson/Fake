//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170302142547000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0450_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0450;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0450 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0450_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_RADIO_BTN_A*/
	public final EZDSBigDecimalItem              xxRadioBtn_A;

    /** SVC_SUPPL_TASK_NUM_A*/
	public final EZDSStringItem              svcSupplTaskNum_A;

    /** SVC_SUPPL_TASK_TP_DESC_TXT_A*/
	public final EZDSStringItem              svcSupplTaskTpDescTxt_A;

    /** TECH_CD_A*/
	public final EZDSStringItem              techCd_A;

    /** TECH_PSN_NM_A*/
	public final EZDSStringItem              techPsnNm_A;

    /** MGR_NM_A*/
	public final EZDSStringItem              mgrNm_A;

    /** XX_CMNT_TXT_A*/
	public final EZDSStringItem              xxCmntTxt_A;

    /** SVC_SUPPL_FROM_DT_A*/
	public final EZDSDateItem              svcSupplFromDt_A;

    /** XX_DT_TM_A1*/
	public final EZDSStringItem              xxDtTm_A1;

    /** SVC_SUPPL_TO_DT_A*/
	public final EZDSDateItem              svcSupplToDt_A;

    /** XX_DT_TM_A2*/
	public final EZDSStringItem              xxDtTm_A2;

    /** XX_DT_TM_ST*/
	public final EZDSStringItem              xxDtTm_ST;

    /** XX_DT_TM_EN*/
	public final EZDSStringItem              xxDtTm_EN;

    /** XX_DT_TM_A3*/
	public final EZDSStringItem              xxDtTm_A3;

    /** XX_DT_TM_A4*/
	public final EZDSStringItem              xxDtTm_A4;

    /** XX_DT_TM_A5*/
	public final EZDSStringItem              xxDtTm_A5;

    /** XX_ALL_PSN_NM_A1*/
	public final EZDSStringItem              xxAllPsnNm_A1;

    /** CRAT_DT_A*/
	public final EZDSDateItem              cratDt_A;

    /** XX_DT_TM_A6*/
	public final EZDSStringItem              xxDtTm_A6;

    /** XX_ALL_PSN_NM_A2*/
	public final EZDSStringItem              xxAllPsnNm_A2;

    /** UPD_DT_A*/
	public final EZDSDateItem              updDt_A;

    /** XX_DT_TM_A7*/
	public final EZDSStringItem              xxDtTm_A7;

    /** SVC_SUPPL_TASK_STS_CD_A*/
	public final EZDSStringItem              svcSupplTaskStsCd_A;

    /** SVC_TASK_NUM_A*/
	public final EZDSStringItem              svcTaskNum_A;

    /** XX_ROW_NUM_A*/
	public final EZDSBigDecimalItem              xxRowNum_A;

    /** SUBMT_FLG_A*/
	public final EZDSStringItem              submtFlg_A;

    /** XX_DT_TM_W1*/
	public final EZDSStringItem              xxDtTm_W1;

    /** XX_DT_TM_W2*/
	public final EZDSStringItem              xxDtTm_W2;

    /** XX_DT_TM_W6*/
	public final EZDSStringItem              xxDtTm_W6;

    /** XX_DT_TM_W7*/
	public final EZDSStringItem              xxDtTm_W7;

    /** _EZUpdateDateTime*/
	public final EZDSStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDSStringItem              ezUpTimeZone;


	/**
	 * NSBL0450_ASMsg is constructor.
	 * The initialization when the instance of NSBL0450_ASMsg is generated.
	 */
	public NSBL0450_ASMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0450_ASMsg is constructor.
	 * The initialization when the instance of NSBL0450_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0450_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRadioBtn_A = (EZDSBigDecimalItem)newItem("xxRadioBtn_A");
		svcSupplTaskNum_A = (EZDSStringItem)newItem("svcSupplTaskNum_A");
		svcSupplTaskTpDescTxt_A = (EZDSStringItem)newItem("svcSupplTaskTpDescTxt_A");
		techCd_A = (EZDSStringItem)newItem("techCd_A");
		techPsnNm_A = (EZDSStringItem)newItem("techPsnNm_A");
		mgrNm_A = (EZDSStringItem)newItem("mgrNm_A");
		xxCmntTxt_A = (EZDSStringItem)newItem("xxCmntTxt_A");
		svcSupplFromDt_A = (EZDSDateItem)newItem("svcSupplFromDt_A");
		xxDtTm_A1 = (EZDSStringItem)newItem("xxDtTm_A1");
		svcSupplToDt_A = (EZDSDateItem)newItem("svcSupplToDt_A");
		xxDtTm_A2 = (EZDSStringItem)newItem("xxDtTm_A2");
		xxDtTm_ST = (EZDSStringItem)newItem("xxDtTm_ST");
		xxDtTm_EN = (EZDSStringItem)newItem("xxDtTm_EN");
		xxDtTm_A3 = (EZDSStringItem)newItem("xxDtTm_A3");
		xxDtTm_A4 = (EZDSStringItem)newItem("xxDtTm_A4");
		xxDtTm_A5 = (EZDSStringItem)newItem("xxDtTm_A5");
		xxAllPsnNm_A1 = (EZDSStringItem)newItem("xxAllPsnNm_A1");
		cratDt_A = (EZDSDateItem)newItem("cratDt_A");
		xxDtTm_A6 = (EZDSStringItem)newItem("xxDtTm_A6");
		xxAllPsnNm_A2 = (EZDSStringItem)newItem("xxAllPsnNm_A2");
		updDt_A = (EZDSDateItem)newItem("updDt_A");
		xxDtTm_A7 = (EZDSStringItem)newItem("xxDtTm_A7");
		svcSupplTaskStsCd_A = (EZDSStringItem)newItem("svcSupplTaskStsCd_A");
		svcTaskNum_A = (EZDSStringItem)newItem("svcTaskNum_A");
		xxRowNum_A = (EZDSBigDecimalItem)newItem("xxRowNum_A");
		submtFlg_A = (EZDSStringItem)newItem("submtFlg_A");
		xxDtTm_W1 = (EZDSStringItem)newItem("xxDtTm_W1");
		xxDtTm_W2 = (EZDSStringItem)newItem("xxDtTm_W2");
		xxDtTm_W6 = (EZDSStringItem)newItem("xxDtTm_W6");
		xxDtTm_W7 = (EZDSStringItem)newItem("xxDtTm_W7");
		ezUpTime = (EZDSStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDSStringItem)newItem("ezUpTimeZone");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0450_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0450_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRadioBtn_A", "xxRadioBtn_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"svcSupplTaskNum_A", "svcSupplTaskNum_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"svcSupplTaskTpDescTxt_A", "svcSupplTaskTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"techCd_A", "techCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"techPsnNm_A", "techPsnNm_A", "A", null, TYPE_HANKAKUEISU, "90", null},
	{"mgrNm_A", "mgrNm_A", "A", null, TYPE_HANKAKUEISU, "90", null},
	{"xxCmntTxt_A", "xxCmntTxt_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	{"svcSupplFromDt_A", "svcSupplFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"xxDtTm_A1", "xxDtTm_A1", "A1", null, TYPE_HANKAKUEISU, "23", null},
	{"svcSupplToDt_A", "svcSupplToDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"xxDtTm_A2", "xxDtTm_A2", "A2", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_ST", "xxDtTm_ST", "ST", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_EN", "xxDtTm_EN", "EN", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_A3", "xxDtTm_A3", "A3", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_A4", "xxDtTm_A4", "A4", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_A5", "xxDtTm_A5", "A5", null, TYPE_HANKAKUEISU, "23", null},
	{"xxAllPsnNm_A1", "xxAllPsnNm_A1", "A1", null, TYPE_HANKAKUEISU, "99", null},
	{"cratDt_A", "cratDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"xxDtTm_A6", "xxDtTm_A6", "A6", null, TYPE_HANKAKUEISU, "23", null},
	{"xxAllPsnNm_A2", "xxAllPsnNm_A2", "A2", null, TYPE_HANKAKUEISU, "99", null},
	{"updDt_A", "updDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"xxDtTm_A7", "xxDtTm_A7", "A7", null, TYPE_HANKAKUEISU, "23", null},
	{"svcSupplTaskStsCd_A", "svcSupplTaskStsCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"svcTaskNum_A", "svcTaskNum_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"xxRowNum_A", "xxRowNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"submtFlg_A", "submtFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDtTm_W1", "xxDtTm_W1", "W1", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_W2", "xxDtTm_W2", "W2", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_W6", "xxDtTm_W6", "W6", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_W7", "xxDtTm_W7", "W7", null, TYPE_HANKAKUEISU, "23", null},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn_A
        {"SVC_SUPPL_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSupplTaskNum_A
        {"SVC_SUPPL_TASK_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSupplTaskTpDescTxt_A
        {"TECH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techCd_A
        {"TECH_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techPsnNm_A
        {"MGR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mgrNm_A
        {"XX_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCmntTxt_A
        {"SVC_SUPPL_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSupplFromDt_A
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A1
        {"SVC_SUPPL_TO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSupplToDt_A
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A2
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_ST
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_EN
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A3
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A4
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A5
        {"XX_ALL_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllPsnNm_A1
        {"CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratDt_A
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A6
        {"XX_ALL_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllPsnNm_A2
        {"UPD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//updDt_A
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A7
        {"SVC_SUPPL_TASK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSupplTaskStsCd_A
        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum_A
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"SUBMT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//submtFlg_A
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_W1
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_W2
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_W6
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_W7
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
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

