//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20190816113351000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0060_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0060 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0060_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_TASK_NUM*/
	public final EZDSStringItem              svcTaskNum;

    /** FSR_NUM*/
	public final EZDSStringItem              fsrNum;

    /** FSR_VISIT_NUM*/
	public final EZDSStringItem              fsrVisitNum;

    /** XX_EDT_CD_NM_ST*/
	public final EZDSStringItem              xxEdtCdNm_ST;

    /** XX_EDT_CD_NM_CA*/
	public final EZDSStringItem              xxEdtCdNm_CA;

    /** XX_EDT_CD_NM_SY*/
	public final EZDSStringItem              xxEdtCdNm_SY;

    /** XX_EDT_CD_NM_PR*/
	public final EZDSStringItem              xxEdtCdNm_PR;

    /** XX_EDT_CD_NM_RE*/
	public final EZDSStringItem              xxEdtCdNm_RE;

    /** XX_EDT_CD_NM_LO*/
	public final EZDSStringItem              xxEdtCdNm_LO;

    /** XX_EDT_CD_NM_CO*/
	public final EZDSStringItem              xxEdtCdNm_CO;

    /** XX_EDT_CD_NM_TE*/
	public final EZDSStringItem              xxEdtCdNm_TE;

    /** SVC_TASK_RCV_DT*/
	public final EZDSDateItem              svcTaskRcvDt;

    /** SVC_TASK_CPLT_DT*/
	public final EZDSDateItem              svcTaskCpltDt;

    /** XX_DT_TM_DL*/
	public final EZDSStringItem              xxDtTm_DL;

    /** READ_MTR_CNT*/
	public final EZDSBigDecimalItem              readMtrCnt;

    /** TEST_MTR_CNT*/
	public final EZDSBigDecimalItem              testMtrCnt;

    /** XX_EDT_CD_NM_BI*/
	public final EZDSStringItem              xxEdtCdNm_BI;

    /** XX_INP_AMT_NUM_PA*/
	public final EZDSBigDecimalItem              xxInpAmtNum_PA;

    /** XX_INP_AMT_NUM_LA*/
	public final EZDSBigDecimalItem              xxInpAmtNum_LA;

    /** XX_INP_AMT_NUM_TR*/
	public final EZDSBigDecimalItem              xxInpAmtNum_TR;

    /** INV_CCY_CD*/
	public final EZDSStringItem              invCcyCd;

    /** SVC_TASK_STS_CD*/
	public final EZDSStringItem              svcTaskStsCd;

    /** SVC_TASK_STS_NM_ST*/
	public final EZDSStringItem              svcTaskStsNm_ST;

    /** FSR_VISIT_STS_CD*/
	public final EZDSStringItem              fsrVisitStsCd;

    /** SVC_TASK_STS_NM_FV*/
	public final EZDSStringItem              svcTaskStsNm_FV;

    /** DS_SVC_CALL_TP_CD*/
	public final EZDSStringItem              dsSvcCallTpCd;

    /** DS_SVC_CALL_TP_NM*/
	public final EZDSStringItem              dsSvcCallTpNm;

    /** SVC_PBLM_SYMP_TP_CD*/
	public final EZDSStringItem              svcPblmSympTpCd;

    /** SVC_PBLM_SYMP_TP_NM*/
	public final EZDSStringItem              svcPblmSympTpNm;

    /** SVC_PBLM_TP_CD*/
	public final EZDSStringItem              svcPblmTpCd;

    /** SVC_PBLM_TP_NM*/
	public final EZDSStringItem              svcPblmTpNm;

    /** SVC_PBLM_RSN_TP_CD*/
	public final EZDSStringItem              svcPblmRsnTpCd;

    /** SVC_PBLM_RSN_TP_NM*/
	public final EZDSStringItem              svcPblmRsnTpNm;

    /** SVC_PBLM_LOC_TP_CD*/
	public final EZDSStringItem              svcPblmLocTpCd;

    /** SVC_PBLM_LOC_TP_NM*/
	public final EZDSStringItem              svcPblmLocTpNm;

    /** SVC_PBLM_CRCT_TP_CD*/
	public final EZDSStringItem              svcPblmCrctTpCd;

    /** SVC_PBLM_CRCT_TP_NM*/
	public final EZDSStringItem              svcPblmCrctTpNm;

    /** TECH_CD_ST*/
	public final EZDSStringItem              techCd_ST;

    /** TECH_NM_ST*/
	public final EZDSStringItem              techNm_ST;

    /** TECH_CD_FV*/
	public final EZDSStringItem              techCd_FV;

    /** TECH_NM_FV*/
	public final EZDSStringItem              techNm_FV;

    /** SVC_RSP_TM_NUM*/
	public final EZDSBigDecimalItem              svcRspTmNum;

    /** SVC_BILL_TP_CD*/
	public final EZDSStringItem              svcBillTpCd;

    /** SVC_BILL_TP_NM*/
	public final EZDSStringItem              svcBillTpNm;

    /** SVC_PRT_DEAL_AMT*/
	public final EZDSBigDecimalItem              svcPrtDealAmt;

    /** SVC_LBOR_DEAL_AMT_FV*/
	public final EZDSBigDecimalItem              svcLborDealAmt_FV;

    /** SVC_LBOR_DEAL_AMT_ST*/
	public final EZDSBigDecimalItem              svcLborDealAmt_ST;

    /** SVC_TRVL_DEAL_AMT*/
	public final EZDSBigDecimalItem              svcTrvlDealAmt;

    /** TM_ZONE_CD_A1*/
	public final EZDSStringItem              tmZoneCd_A1;

    /** TM_ZONE_CD_A2*/
	public final EZDSStringItem              tmZoneCd_A2;

    /** SVC_TASK_RCV_TM*/
	public final EZDSStringItem              svcTaskRcvTm;

    /** SVC_TASK_CPLT_TM*/
	public final EZDSStringItem              svcTaskCpltTm;


	/**
	 * NSBL0060_ASMsg is constructor.
	 * The initialization when the instance of NSBL0060_ASMsg is generated.
	 */
	public NSBL0060_ASMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0060_ASMsg is constructor.
	 * The initialization when the instance of NSBL0060_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0060_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcTaskNum = (EZDSStringItem)newItem("svcTaskNum");
		fsrNum = (EZDSStringItem)newItem("fsrNum");
		fsrVisitNum = (EZDSStringItem)newItem("fsrVisitNum");
		xxEdtCdNm_ST = (EZDSStringItem)newItem("xxEdtCdNm_ST");
		xxEdtCdNm_CA = (EZDSStringItem)newItem("xxEdtCdNm_CA");
		xxEdtCdNm_SY = (EZDSStringItem)newItem("xxEdtCdNm_SY");
		xxEdtCdNm_PR = (EZDSStringItem)newItem("xxEdtCdNm_PR");
		xxEdtCdNm_RE = (EZDSStringItem)newItem("xxEdtCdNm_RE");
		xxEdtCdNm_LO = (EZDSStringItem)newItem("xxEdtCdNm_LO");
		xxEdtCdNm_CO = (EZDSStringItem)newItem("xxEdtCdNm_CO");
		xxEdtCdNm_TE = (EZDSStringItem)newItem("xxEdtCdNm_TE");
		svcTaskRcvDt = (EZDSDateItem)newItem("svcTaskRcvDt");
		svcTaskCpltDt = (EZDSDateItem)newItem("svcTaskCpltDt");
		xxDtTm_DL = (EZDSStringItem)newItem("xxDtTm_DL");
		readMtrCnt = (EZDSBigDecimalItem)newItem("readMtrCnt");
		testMtrCnt = (EZDSBigDecimalItem)newItem("testMtrCnt");
		xxEdtCdNm_BI = (EZDSStringItem)newItem("xxEdtCdNm_BI");
		xxInpAmtNum_PA = (EZDSBigDecimalItem)newItem("xxInpAmtNum_PA");
		xxInpAmtNum_LA = (EZDSBigDecimalItem)newItem("xxInpAmtNum_LA");
		xxInpAmtNum_TR = (EZDSBigDecimalItem)newItem("xxInpAmtNum_TR");
		invCcyCd = (EZDSStringItem)newItem("invCcyCd");
		svcTaskStsCd = (EZDSStringItem)newItem("svcTaskStsCd");
		svcTaskStsNm_ST = (EZDSStringItem)newItem("svcTaskStsNm_ST");
		fsrVisitStsCd = (EZDSStringItem)newItem("fsrVisitStsCd");
		svcTaskStsNm_FV = (EZDSStringItem)newItem("svcTaskStsNm_FV");
		dsSvcCallTpCd = (EZDSStringItem)newItem("dsSvcCallTpCd");
		dsSvcCallTpNm = (EZDSStringItem)newItem("dsSvcCallTpNm");
		svcPblmSympTpCd = (EZDSStringItem)newItem("svcPblmSympTpCd");
		svcPblmSympTpNm = (EZDSStringItem)newItem("svcPblmSympTpNm");
		svcPblmTpCd = (EZDSStringItem)newItem("svcPblmTpCd");
		svcPblmTpNm = (EZDSStringItem)newItem("svcPblmTpNm");
		svcPblmRsnTpCd = (EZDSStringItem)newItem("svcPblmRsnTpCd");
		svcPblmRsnTpNm = (EZDSStringItem)newItem("svcPblmRsnTpNm");
		svcPblmLocTpCd = (EZDSStringItem)newItem("svcPblmLocTpCd");
		svcPblmLocTpNm = (EZDSStringItem)newItem("svcPblmLocTpNm");
		svcPblmCrctTpCd = (EZDSStringItem)newItem("svcPblmCrctTpCd");
		svcPblmCrctTpNm = (EZDSStringItem)newItem("svcPblmCrctTpNm");
		techCd_ST = (EZDSStringItem)newItem("techCd_ST");
		techNm_ST = (EZDSStringItem)newItem("techNm_ST");
		techCd_FV = (EZDSStringItem)newItem("techCd_FV");
		techNm_FV = (EZDSStringItem)newItem("techNm_FV");
		svcRspTmNum = (EZDSBigDecimalItem)newItem("svcRspTmNum");
		svcBillTpCd = (EZDSStringItem)newItem("svcBillTpCd");
		svcBillTpNm = (EZDSStringItem)newItem("svcBillTpNm");
		svcPrtDealAmt = (EZDSBigDecimalItem)newItem("svcPrtDealAmt");
		svcLborDealAmt_FV = (EZDSBigDecimalItem)newItem("svcLborDealAmt_FV");
		svcLborDealAmt_ST = (EZDSBigDecimalItem)newItem("svcLborDealAmt_ST");
		svcTrvlDealAmt = (EZDSBigDecimalItem)newItem("svcTrvlDealAmt");
		tmZoneCd_A1 = (EZDSStringItem)newItem("tmZoneCd_A1");
		tmZoneCd_A2 = (EZDSStringItem)newItem("tmZoneCd_A2");
		svcTaskRcvTm = (EZDSStringItem)newItem("svcTaskRcvTm");
		svcTaskCpltTm = (EZDSStringItem)newItem("svcTaskCpltTm");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0060_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0060_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcTaskNum", "svcTaskNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"fsrNum", "fsrNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"fsrVisitNum", "fsrVisitNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"xxEdtCdNm_ST", "xxEdtCdNm_ST", "ST", null, TYPE_HANKAKUEISU, "100", null},
	{"xxEdtCdNm_CA", "xxEdtCdNm_CA", "CA", null, TYPE_HANKAKUEISU, "100", null},
	{"xxEdtCdNm_SY", "xxEdtCdNm_SY", "SY", null, TYPE_HANKAKUEISU, "100", null},
	{"xxEdtCdNm_PR", "xxEdtCdNm_PR", "PR", null, TYPE_HANKAKUEISU, "100", null},
	{"xxEdtCdNm_RE", "xxEdtCdNm_RE", "RE", null, TYPE_HANKAKUEISU, "100", null},
	{"xxEdtCdNm_LO", "xxEdtCdNm_LO", "LO", null, TYPE_HANKAKUEISU, "100", null},
	{"xxEdtCdNm_CO", "xxEdtCdNm_CO", "CO", null, TYPE_HANKAKUEISU, "100", null},
	{"xxEdtCdNm_TE", "xxEdtCdNm_TE", "TE", null, TYPE_HANKAKUEISU, "100", null},
	{"svcTaskRcvDt", "svcTaskRcvDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"svcTaskCpltDt", "svcTaskCpltDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxDtTm_DL", "xxDtTm_DL", "DL", null, TYPE_HANKAKUEISU, "23", null},
	{"readMtrCnt", "readMtrCnt", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"testMtrCnt", "testMtrCnt", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxEdtCdNm_BI", "xxEdtCdNm_BI", "BI", null, TYPE_HANKAKUEISU, "100", null},
	{"xxInpAmtNum_PA", "xxInpAmtNum_PA", "PA", null, TYPE_SEISU_SYOSU, "17", "2"},
	{"xxInpAmtNum_LA", "xxInpAmtNum_LA", "LA", null, TYPE_SEISU_SYOSU, "17", "2"},
	{"xxInpAmtNum_TR", "xxInpAmtNum_TR", "TR", null, TYPE_SEISU_SYOSU, "17", "2"},
	{"invCcyCd", "invCcyCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"svcTaskStsCd", "svcTaskStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"svcTaskStsNm_ST", "svcTaskStsNm_ST", "ST", null, TYPE_HANKAKUEISU, "30", null},
	{"fsrVisitStsCd", "fsrVisitStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"svcTaskStsNm_FV", "svcTaskStsNm_FV", "FV", null, TYPE_HANKAKUEISU, "30", null},
	{"dsSvcCallTpCd", "dsSvcCallTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"dsSvcCallTpNm", "dsSvcCallTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcPblmSympTpCd", "svcPblmSympTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"svcPblmSympTpNm", "svcPblmSympTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcPblmTpCd", "svcPblmTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"svcPblmTpNm", "svcPblmTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcPblmRsnTpCd", "svcPblmRsnTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"svcPblmRsnTpNm", "svcPblmRsnTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcPblmLocTpCd", "svcPblmLocTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"svcPblmLocTpNm", "svcPblmLocTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcPblmCrctTpCd", "svcPblmCrctTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"svcPblmCrctTpNm", "svcPblmCrctTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"techCd_ST", "techCd_ST", "ST", null, TYPE_HANKAKUEISU, "20", null},
	{"techNm_ST", "techNm_ST", "ST", null, TYPE_HANKAKUEISU, "45", null},
	{"techCd_FV", "techCd_FV", "FV", null, TYPE_HANKAKUEISU, "20", null},
	{"techNm_FV", "techNm_FV", "FV", null, TYPE_HANKAKUEISU, "45", null},
	{"svcRspTmNum", "svcRspTmNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"svcBillTpCd", "svcBillTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"svcBillTpNm", "svcBillTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcPrtDealAmt", "svcPrtDealAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"svcLborDealAmt_FV", "svcLborDealAmt_FV", "FV", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"svcLborDealAmt_ST", "svcLborDealAmt_ST", "ST", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"svcTrvlDealAmt", "svcTrvlDealAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"tmZoneCd_A1", "tmZoneCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"tmZoneCd_A2", "tmZoneCd_A2", "A2", null, TYPE_HANKAKUEISU, "20", null},
	{"svcTaskRcvTm", "svcTaskRcvTm", null, null, TYPE_HANKAKUSUJI, "6", null},
	{"svcTaskCpltTm", "svcTaskCpltTm", null, null, TYPE_HANKAKUSUJI, "6", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum
        {"FSR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum
        {"FSR_VISIT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrVisitNum
        {"XX_EDT_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtCdNm_ST
        {"XX_EDT_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtCdNm_CA
        {"XX_EDT_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtCdNm_SY
        {"XX_EDT_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtCdNm_PR
        {"XX_EDT_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtCdNm_RE
        {"XX_EDT_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtCdNm_LO
        {"XX_EDT_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtCdNm_CO
        {"XX_EDT_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtCdNm_TE
        {"SVC_TASK_RCV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskRcvDt
        {"SVC_TASK_CPLT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskCpltDt
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_DL
        {"READ_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//readMtrCnt
        {"TEST_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//testMtrCnt
        {"XX_EDT_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtCdNm_BI
        {"XX_INP_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInpAmtNum_PA
        {"XX_INP_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInpAmtNum_LA
        {"XX_INP_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInpAmtNum_TR
        {"INV_CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invCcyCd
        {"SVC_TASK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskStsCd
        {"SVC_TASK_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskStsNm_ST
        {"FSR_VISIT_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrVisitStsCd
        {"SVC_TASK_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskStsNm_FV
        {"DS_SVC_CALL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSvcCallTpCd
        {"DS_SVC_CALL_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSvcCallTpNm
        {"SVC_PBLM_SYMP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmSympTpCd
        {"SVC_PBLM_SYMP_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmSympTpNm
        {"SVC_PBLM_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmTpCd
        {"SVC_PBLM_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmTpNm
        {"SVC_PBLM_RSN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmRsnTpCd
        {"SVC_PBLM_RSN_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmRsnTpNm
        {"SVC_PBLM_LOC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmLocTpCd
        {"SVC_PBLM_LOC_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmLocTpNm
        {"SVC_PBLM_CRCT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmCrctTpCd
        {"SVC_PBLM_CRCT_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmCrctTpNm
        {"TECH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techCd_ST
        {"TECH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm_ST
        {"TECH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techCd_FV
        {"TECH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm_FV
        {"SVC_RSP_TM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcRspTmNum
        {"SVC_BILL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBillTpCd
        {"SVC_BILL_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBillTpNm
        {"SVC_PRT_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPrtDealAmt
        {"SVC_LBOR_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLborDealAmt_FV
        {"SVC_LBOR_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLborDealAmt_ST
        {"SVC_TRVL_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTrvlDealAmt
        {"TM_ZONE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tmZoneCd_A1
        {"TM_ZONE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tmZoneCd_A2
        {"SVC_TASK_RCV_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskRcvTm
        {"SVC_TASK_CPLT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskCpltTm
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
