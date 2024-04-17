//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20161020094847000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0010F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSBL0010F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0010F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TECH_CD_A*/
	public final EZDFStringItem              techCd_A;

    /** SVC_CONTR_BR_CD_A*/
	public final EZDFStringItem              svcContrBrCd_A;

    /** SVC_CONTR_BR_DESC_TXT_A*/
	public final EZDFStringItem              svcContrBrDescTxt_A;

    /** SVC_BY_LINE_BIZ_TP_CD_A*/
	public final EZDFStringItem              svcByLineBizTpCd_A;

    /** MDL_NM_A*/
	public final EZDFStringItem              mdlNm_A;

    /** XX_DT_TM_A1*/
	public final EZDFStringItem              xxDtTm_A1;

    /** XX_DT_TM_A2*/
	public final EZDFStringItem              xxDtTm_A2;

    /** XX_DT_TM_A3*/
	public final EZDFStringItem              xxDtTm_A3;

    /** XX_DT_TM_A4*/
	public final EZDFStringItem              xxDtTm_A4;

    /** TECH_SCHD_TZ_A*/
	public final EZDFStringItem              techSchdTz_A;

    /** SVC_CALL_PRTY_CD_A*/
	public final EZDFStringItem              svcCallPrtyCd_A;

    /** ORIG_SVC_CALL_PRTY_CD_A*/
	public final EZDFStringItem              origSvcCallPrtyCd_A;

    /** DS_SVC_CALL_TP_CD_A*/
	public final EZDFStringItem              dsSvcCallTpCd_A;

    /** DS_SVC_CALL_TP_NM_A*/
	public final EZDFStringItem              dsSvcCallTpNm_A;

    /** SVC_BILL_TP_CD_A*/
	public final EZDFStringItem              svcBillTpCd_A;

    /** SVC_BILL_TP_NM_A*/
	public final EZDFStringItem              svcBillTpNm_A;

    /** SVC_PBLM_SYMP_TP_CD_A*/
	public final EZDFStringItem              svcPblmSympTpCd_A;

    /** SVC_PBLM_SYMP_TP_NM_A*/
	public final EZDFStringItem              svcPblmSympTpNm_A;

    /** XX_DT_TM_A5*/
	public final EZDFStringItem              xxDtTm_A5;

    /** XX_DT_TM_A6*/
	public final EZDFStringItem              xxDtTm_A6;

    /** SVC_TASK_RCV_TZ_A*/
	public final EZDFStringItem              svcTaskRcvTz_A;

    /** SER_NUM_A*/
	public final EZDFStringItem              serNum_A;

    /** CUST_AVAL_FROM_HOUR_MN_A*/
	public final EZDFStringItem              custAvalFromHourMn_A;

    /** CUST_AVAL_TO_HOUR_MN_A*/
	public final EZDFStringItem              custAvalToHourMn_A;

    /** SVC_RSP_TM_MN_AOT_A*/
	public final EZDFBigDecimalItem              svcRspTmMnAot_A;

    /** SVC_RSP_TM_NUM_A*/
	public final EZDFBigDecimalItem              svcRspTmNum_A;

    /** SVC_TASK_NUM_A*/
	public final EZDFStringItem              svcTaskNum_A;

    /** FSR_NUM_A*/
	public final EZDFStringItem              fsrNum_A;

    /** FSR_VISIT_NUM_A*/
	public final EZDFStringItem              fsrVisitNum_A;

    /** SVC_TASK_STS_NM_A*/
	public final EZDFStringItem              svcTaskStsNm_A;

    /** SHIP_TO_CUST_CD_A*/
	public final EZDFStringItem              shipToCustCd_A;

    /** LOC_NM_A*/
	public final EZDFStringItem              locNm_A;

    /** TECH_ACPT_FLG_A*/
	public final EZDFStringItem              techAcptFlg_A;

    /** SVC_CR_HLD_FLG_A*/
	public final EZDFStringItem              svcCrHldFlg_A;

    /** TECH_AVAL_FLG_A*/
	public final EZDFStringItem              techAvalFlg_A;

    /** MACH_DOWN_FLG_A*/
	public final EZDFStringItem              machDownFlg_A;

    /** XX_BTN_FLG_AM*/
	public final EZDFStringItem              xxBtnFlg_AM;

    /** XX_BTN_FLG_AE*/
	public final EZDFStringItem              xxBtnFlg_AE;

    /** XX_BTN_FLG_AC*/
	public final EZDFStringItem              xxBtnFlg_AC;

    /** SCHD_DISPT_EML_FLG_A*/
	public final EZDFStringItem              schdDisptEmlFlg_A;


	/**
	 * NSBL0010F00FMsg is constructor.
	 * The initialization when the instance of NSBL0010F00FMsg is generated.
	 */
	public NSBL0010F00FMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0010F00FMsg is constructor.
	 * The initialization when the instance of NSBL0010F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0010F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		techCd_A = (EZDFStringItem)newItem("techCd_A");
		svcContrBrCd_A = (EZDFStringItem)newItem("svcContrBrCd_A");
		svcContrBrDescTxt_A = (EZDFStringItem)newItem("svcContrBrDescTxt_A");
		svcByLineBizTpCd_A = (EZDFStringItem)newItem("svcByLineBizTpCd_A");
		mdlNm_A = (EZDFStringItem)newItem("mdlNm_A");
		xxDtTm_A1 = (EZDFStringItem)newItem("xxDtTm_A1");
		xxDtTm_A2 = (EZDFStringItem)newItem("xxDtTm_A2");
		xxDtTm_A3 = (EZDFStringItem)newItem("xxDtTm_A3");
		xxDtTm_A4 = (EZDFStringItem)newItem("xxDtTm_A4");
		techSchdTz_A = (EZDFStringItem)newItem("techSchdTz_A");
		svcCallPrtyCd_A = (EZDFStringItem)newItem("svcCallPrtyCd_A");
		origSvcCallPrtyCd_A = (EZDFStringItem)newItem("origSvcCallPrtyCd_A");
		dsSvcCallTpCd_A = (EZDFStringItem)newItem("dsSvcCallTpCd_A");
		dsSvcCallTpNm_A = (EZDFStringItem)newItem("dsSvcCallTpNm_A");
		svcBillTpCd_A = (EZDFStringItem)newItem("svcBillTpCd_A");
		svcBillTpNm_A = (EZDFStringItem)newItem("svcBillTpNm_A");
		svcPblmSympTpCd_A = (EZDFStringItem)newItem("svcPblmSympTpCd_A");
		svcPblmSympTpNm_A = (EZDFStringItem)newItem("svcPblmSympTpNm_A");
		xxDtTm_A5 = (EZDFStringItem)newItem("xxDtTm_A5");
		xxDtTm_A6 = (EZDFStringItem)newItem("xxDtTm_A6");
		svcTaskRcvTz_A = (EZDFStringItem)newItem("svcTaskRcvTz_A");
		serNum_A = (EZDFStringItem)newItem("serNum_A");
		custAvalFromHourMn_A = (EZDFStringItem)newItem("custAvalFromHourMn_A");
		custAvalToHourMn_A = (EZDFStringItem)newItem("custAvalToHourMn_A");
		svcRspTmMnAot_A = (EZDFBigDecimalItem)newItem("svcRspTmMnAot_A");
		svcRspTmNum_A = (EZDFBigDecimalItem)newItem("svcRspTmNum_A");
		svcTaskNum_A = (EZDFStringItem)newItem("svcTaskNum_A");
		fsrNum_A = (EZDFStringItem)newItem("fsrNum_A");
		fsrVisitNum_A = (EZDFStringItem)newItem("fsrVisitNum_A");
		svcTaskStsNm_A = (EZDFStringItem)newItem("svcTaskStsNm_A");
		shipToCustCd_A = (EZDFStringItem)newItem("shipToCustCd_A");
		locNm_A = (EZDFStringItem)newItem("locNm_A");
		techAcptFlg_A = (EZDFStringItem)newItem("techAcptFlg_A");
		svcCrHldFlg_A = (EZDFStringItem)newItem("svcCrHldFlg_A");
		techAvalFlg_A = (EZDFStringItem)newItem("techAvalFlg_A");
		machDownFlg_A = (EZDFStringItem)newItem("machDownFlg_A");
		xxBtnFlg_AM = (EZDFStringItem)newItem("xxBtnFlg_AM");
		xxBtnFlg_AE = (EZDFStringItem)newItem("xxBtnFlg_AE");
		xxBtnFlg_AC = (EZDFStringItem)newItem("xxBtnFlg_AC");
		schdDisptEmlFlg_A = (EZDFStringItem)newItem("schdDisptEmlFlg_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0010F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0010F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"techCd_A", "techCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"svcContrBrCd_A", "svcContrBrCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"svcContrBrDescTxt_A", "svcContrBrDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcByLineBizTpCd_A", "svcByLineBizTpCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"mdlNm_A", "mdlNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"xxDtTm_A1", "xxDtTm_A1", "A1", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_A2", "xxDtTm_A2", "A2", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_A3", "xxDtTm_A3", "A3", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_A4", "xxDtTm_A4", "A4", null, TYPE_HANKAKUEISU, "23", null},
	{"techSchdTz_A", "techSchdTz_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"svcCallPrtyCd_A", "svcCallPrtyCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"origSvcCallPrtyCd_A", "origSvcCallPrtyCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"dsSvcCallTpCd_A", "dsSvcCallTpCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"dsSvcCallTpNm_A", "dsSvcCallTpNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"svcBillTpCd_A", "svcBillTpCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"svcBillTpNm_A", "svcBillTpNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"svcPblmSympTpCd_A", "svcPblmSympTpCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"svcPblmSympTpNm_A", "svcPblmSympTpNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"xxDtTm_A5", "xxDtTm_A5", "A5", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_A6", "xxDtTm_A6", "A6", null, TYPE_HANKAKUEISU, "23", null},
	{"svcTaskRcvTz_A", "svcTaskRcvTz_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"custAvalFromHourMn_A", "custAvalFromHourMn_A", "A", null, TYPE_HANKAKUSUJI, "4", null},
	{"custAvalToHourMn_A", "custAvalToHourMn_A", "A", null, TYPE_HANKAKUSUJI, "4", null},
	{"svcRspTmMnAot_A", "svcRspTmMnAot_A", "A", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"svcRspTmNum_A", "svcRspTmNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"svcTaskNum_A", "svcTaskNum_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"fsrNum_A", "fsrNum_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"fsrVisitNum_A", "fsrVisitNum_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"svcTaskStsNm_A", "svcTaskStsNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"shipToCustCd_A", "shipToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_A", "locNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	{"techAcptFlg_A", "techAcptFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"svcCrHldFlg_A", "svcCrHldFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"techAvalFlg_A", "techAvalFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"machDownFlg_A", "machDownFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxBtnFlg_AM", "xxBtnFlg_AM", "AM", null, TYPE_HANKAKUEISU, "1", null},
	{"xxBtnFlg_AE", "xxBtnFlg_AE", "AE", null, TYPE_HANKAKUEISU, "1", null},
	{"xxBtnFlg_AC", "xxBtnFlg_AC", "AC", null, TYPE_HANKAKUEISU, "1", null},
	{"schdDisptEmlFlg_A", "schdDisptEmlFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TECH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techCd_A
        {"SVC_CONTR_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrCd_A
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt_A
        {"SVC_BY_LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcByLineBizTpCd_A
        {"MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm_A
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A1
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A2
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A3
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A4
        {"TECH_SCHD_TZ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techSchdTz_A
        {"SVC_CALL_PRTY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCallPrtyCd_A
        {"ORIG_SVC_CALL_PRTY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origSvcCallPrtyCd_A
        {"DS_SVC_CALL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSvcCallTpCd_A
        {"DS_SVC_CALL_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSvcCallTpNm_A
        {"SVC_BILL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBillTpCd_A
        {"SVC_BILL_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBillTpNm_A
        {"SVC_PBLM_SYMP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmSympTpCd_A
        {"SVC_PBLM_SYMP_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmSympTpNm_A
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A5
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A6
        {"SVC_TASK_RCV_TZ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskRcvTz_A
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"CUST_AVAL_FROM_HOUR_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custAvalFromHourMn_A
        {"CUST_AVAL_TO_HOUR_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custAvalToHourMn_A
        {"SVC_RSP_TM_MN_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcRspTmMnAot_A
        {"SVC_RSP_TM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcRspTmNum_A
        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum_A
        {"FSR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum_A
        {"FSR_VISIT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrVisitNum_A
        {"SVC_TASK_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskStsNm_A
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_A
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_A
        {"TECH_ACPT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techAcptFlg_A
        {"SVC_CR_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrHldFlg_A
        {"TECH_AVAL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techAvalFlg_A
        {"MACH_DOWN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//machDownFlg_A
        {"XX_BTN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg_AM
        {"XX_BTN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg_AE
        {"XX_BTN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg_AC
        {"SCHD_DISPT_EML_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdDisptEmlFlg_A
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
