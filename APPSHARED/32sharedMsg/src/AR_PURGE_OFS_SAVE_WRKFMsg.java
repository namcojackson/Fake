//This file was automatically generated by Logical Database Layout Definition Document and XLA200710010c.
// Generated on    :20140314193916000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :LogicalDatabaseLayoutDefinition Document_AddIn_XLS200710010.xla XLA200710010c
/*
 *AR_PURGE_OFS_SAVE_WRKFMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.db;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is AR_PURGE_OFS_SAVE_WRK Table Layout Message class.
 * @author
 * @version XLA200710010c
 */
public class AR_PURGE_OFS_SAVE_WRKFMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZTableID*/
	public final EZDFStringItem              ezTableID;

    /** _EZCancelFlag*/
	public final EZDFStringItem              ezCancelFlag;

    /** _EZRegistrationDateTime*/
	public final EZDFStringItem              ezInTime;

    /** _EZInTimeZone*/
	public final EZDFStringItem              ezInTimeZone;

    /** _EZInCompanyCode*/
	public final EZDFStringItem              ezInCompanyCode;

    /** _EZInUserID*/
	public final EZDFStringItem              ezInUserID;

    /** _EZInAplID*/
	public final EZDFStringItem              ezInAplID;

    /** _EZUpdateDateTime*/
	public final EZDFStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDFStringItem              ezUpTimeZone;

    /** _EZUpCompanyCode*/
	public final EZDFStringItem              ezUpCompanyCode;

    /** _EZUpdateUserID*/
	public final EZDFStringItem              ezUpUserID;

    /** _EZUpdateApplicationID*/
	public final EZDFStringItem              ezUpAplID;

    /** GLBL_CMPY_CD*/
	public final EZDFStringItem              glblCmpyCd;

    /** AR_PURGE_OFS_SAVE_WRK_PK*/
	public final EZDFBigDecimalItem              arPurgeOfsSaveWrkPk;

    /** RCV_HDR_NUM*/
	public final EZDFStringItem              rcvHdrNum;

    /** RCV_DTL_NUM*/
	public final EZDFStringItem              rcvDtlNum;

    /** RCV_DT*/
	public final EZDFDateItem              rcvDt;

    /** RCV_TM*/
	public final EZDFStringItem              rcvTm;

    /** RCPT_STS_CD*/
	public final EZDFStringItem              rcptStsCd;

    /** RCPT_BAT_NUM*/
	public final EZDFStringItem              rcptBatNum;

    /** RCPT_BAT_SQ_NUM*/
	public final EZDFStringItem              rcptBatSqNum;

    /** AR_RCPT_TRX_TP_CD*/
	public final EZDFStringItem              arRcptTrxTpCd;

    /** AR_RCPT_TP_CD*/
	public final EZDFStringItem              arRcptTpCd;

    /** DEAL_CCY_CD*/
	public final EZDFStringItem              dealCcyCd;

    /** DEAL_RCPT_AMT*/
	public final EZDFBigDecimalItem              dealRcptAmt;

    /** DEAL_RCPT_RMNG_BAL_AMT*/
	public final EZDFBigDecimalItem              dealRcptRmngBalAmt;

    /** RCPT_DT*/
	public final EZDFDateItem              rcptDt;

    /** GL_DT*/
	public final EZDFDateItem              glDt;

    /** RCPT_CHK_NUM*/
	public final EZDFStringItem              rcptChkNum;

    /** RCPT_CHK_DT*/
	public final EZDFDateItem              rcptChkDt;

    /** PAYER_CUST_CD*/
	public final EZDFStringItem              payerCustCd;

    /** AR_BANK_ACCT_CD*/
	public final EZDFStringItem              arBankAcctCd;

    /** CRAT_METH_TP_CD*/
	public final EZDFStringItem              cratMethTpCd;

    /** RCPT_FIRST_CMNT_TXT*/
	public final EZDFStringItem              rcptFirstCmntTxt;

    /** RCPT_SCD_CMNT_TXT*/
	public final EZDFStringItem              rcptScdCmntTxt;

    /** AR_CUST_REF_NUM*/
	public final EZDFStringItem              arCustRefNum;

    /** DEAL_RCPT_DTL_AMT*/
	public final EZDFBigDecimalItem              dealRcptDtlAmt;

    /** DEAL_APPLY_GRS_AMT*/
	public final EZDFBigDecimalItem              dealApplyGrsAmt;

    /** AR_TRX_TP_CD*/
	public final EZDFStringItem              arTrxTpCd;

    /** AR_ADJ_TP_CD*/
	public final EZDFStringItem              arAdjTpCd;

    /** TOC_CD*/
	public final EZDFStringItem              tocCd;

    /** COA_PROD_CD*/
	public final EZDFStringItem              coaProdCd;

    /** UPLD_CSV_RQST_PK*/
	public final EZDFBigDecimalItem              upldCsvRqstPk;

    /** GRP_INV_FLG*/
	public final EZDFStringItem              grpInvFlg;

    /** AR_WF_INCL_WIRE_OFF_FLG*/
	public final EZDFStringItem              arWfInclWireOffFlg;

    /** AR_WF_SUBMT_TO_ID*/
	public final EZDFStringItem              arWfSubmtToId;

    /** AR_WF_LVL_CD*/
	public final EZDFStringItem              arWfLvlCd;

    /** AR_WF_STS_CD*/
	public final EZDFStringItem              arWfStsCd;

    /** AR_WF_FIRST_LVL_USER_ID*/
	public final EZDFStringItem              arWfFirstLvlUserId;

    /** AR_WF_FIRST_LVL_DT*/
	public final EZDFDateItem              arWfFirstLvlDt;

    /** AR_WF_FIRST_LVL_TM*/
	public final EZDFStringItem              arWfFirstLvlTm;

    /** AR_WF_SCD_LVL_USER_ID*/
	public final EZDFStringItem              arWfScdLvlUserId;

    /** AR_WF_SCD_LVL_DT*/
	public final EZDFDateItem              arWfScdLvlDt;

    /** AR_WF_SCD_LVL_TM*/
	public final EZDFStringItem              arWfScdLvlTm;

    /** AR_WF_THIRD_LVL_USER_ID*/
	public final EZDFStringItem              arWfThirdLvlUserId;

    /** AR_WF_THIRD_LVL_DT*/
	public final EZDFDateItem              arWfThirdLvlDt;

    /** AR_WF_THIRD_LVL_TM*/
	public final EZDFStringItem              arWfThirdLvlTm;

    /** AR_WF_FRTH_LVL_USER_ID*/
	public final EZDFStringItem              arWfFrthLvlUserId;

    /** AR_WF_FRTH_LVL_DT*/
	public final EZDFDateItem              arWfFrthLvlDt;

    /** AR_WF_FRTH_LVL_TM*/
	public final EZDFStringItem              arWfFrthLvlTm;

    /** AR_WF_CR_REJ_CMNT_TXT*/
	public final EZDFStringItem              arWfCrRejCmntTxt;

    /** AR_WF_PEND_DEL_CMNT_TXT*/
	public final EZDFStringItem              arWfPendDelCmntTxt;

    /** AR_WF_PEND_USER_ID*/
	public final EZDFStringItem              arWfPendUserId;

    /** EXPT_FLG*/
	public final EZDFStringItem              exptFlg;


	/**
	 * AR_PURGE_OFS_SAVE_WRKFMsg is constructor.
	 * The initialization when the instance of AR_PURGE_OFS_SAVE_WRKFMsg is generated.
	 */
	public AR_PURGE_OFS_SAVE_WRKFMsg() {
		this(false, -1);
	}

	/**
	 * AR_PURGE_OFS_SAVE_WRKFMsg is constructor.
	 * The initialization when the instance of AR_PURGE_OFS_SAVE_WRKFMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public AR_PURGE_OFS_SAVE_WRKFMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezTableID = (EZDFStringItem)newItem("ezTableID");
		ezCancelFlag = (EZDFStringItem)newItem("ezCancelFlag");
		ezInTime = (EZDFStringItem)newItem("ezInTime");
		ezInTimeZone = (EZDFStringItem)newItem("ezInTimeZone");
		ezInCompanyCode = (EZDFStringItem)newItem("ezInCompanyCode");
		ezInUserID = (EZDFStringItem)newItem("ezInUserID");
		ezInAplID = (EZDFStringItem)newItem("ezInAplID");
		ezUpTime = (EZDFStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDFStringItem)newItem("ezUpTimeZone");
		ezUpCompanyCode = (EZDFStringItem)newItem("ezUpCompanyCode");
		ezUpUserID = (EZDFStringItem)newItem("ezUpUserID");
		ezUpAplID = (EZDFStringItem)newItem("ezUpAplID");
		glblCmpyCd = (EZDFStringItem)newItem("glblCmpyCd");
		arPurgeOfsSaveWrkPk = (EZDFBigDecimalItem)newItem("arPurgeOfsSaveWrkPk");
		rcvHdrNum = (EZDFStringItem)newItem("rcvHdrNum");
		rcvDtlNum = (EZDFStringItem)newItem("rcvDtlNum");
		rcvDt = (EZDFDateItem)newItem("rcvDt");
		rcvTm = (EZDFStringItem)newItem("rcvTm");
		rcptStsCd = (EZDFStringItem)newItem("rcptStsCd");
		rcptBatNum = (EZDFStringItem)newItem("rcptBatNum");
		rcptBatSqNum = (EZDFStringItem)newItem("rcptBatSqNum");
		arRcptTrxTpCd = (EZDFStringItem)newItem("arRcptTrxTpCd");
		arRcptTpCd = (EZDFStringItem)newItem("arRcptTpCd");
		dealCcyCd = (EZDFStringItem)newItem("dealCcyCd");
		dealRcptAmt = (EZDFBigDecimalItem)newItem("dealRcptAmt");
		dealRcptRmngBalAmt = (EZDFBigDecimalItem)newItem("dealRcptRmngBalAmt");
		rcptDt = (EZDFDateItem)newItem("rcptDt");
		glDt = (EZDFDateItem)newItem("glDt");
		rcptChkNum = (EZDFStringItem)newItem("rcptChkNum");
		rcptChkDt = (EZDFDateItem)newItem("rcptChkDt");
		payerCustCd = (EZDFStringItem)newItem("payerCustCd");
		arBankAcctCd = (EZDFStringItem)newItem("arBankAcctCd");
		cratMethTpCd = (EZDFStringItem)newItem("cratMethTpCd");
		rcptFirstCmntTxt = (EZDFStringItem)newItem("rcptFirstCmntTxt");
		rcptScdCmntTxt = (EZDFStringItem)newItem("rcptScdCmntTxt");
		arCustRefNum = (EZDFStringItem)newItem("arCustRefNum");
		dealRcptDtlAmt = (EZDFBigDecimalItem)newItem("dealRcptDtlAmt");
		dealApplyGrsAmt = (EZDFBigDecimalItem)newItem("dealApplyGrsAmt");
		arTrxTpCd = (EZDFStringItem)newItem("arTrxTpCd");
		arAdjTpCd = (EZDFStringItem)newItem("arAdjTpCd");
		tocCd = (EZDFStringItem)newItem("tocCd");
		coaProdCd = (EZDFStringItem)newItem("coaProdCd");
		upldCsvRqstPk = (EZDFBigDecimalItem)newItem("upldCsvRqstPk");
		grpInvFlg = (EZDFStringItem)newItem("grpInvFlg");
		arWfInclWireOffFlg = (EZDFStringItem)newItem("arWfInclWireOffFlg");
		arWfSubmtToId = (EZDFStringItem)newItem("arWfSubmtToId");
		arWfLvlCd = (EZDFStringItem)newItem("arWfLvlCd");
		arWfStsCd = (EZDFStringItem)newItem("arWfStsCd");
		arWfFirstLvlUserId = (EZDFStringItem)newItem("arWfFirstLvlUserId");
		arWfFirstLvlDt = (EZDFDateItem)newItem("arWfFirstLvlDt");
		arWfFirstLvlTm = (EZDFStringItem)newItem("arWfFirstLvlTm");
		arWfScdLvlUserId = (EZDFStringItem)newItem("arWfScdLvlUserId");
		arWfScdLvlDt = (EZDFDateItem)newItem("arWfScdLvlDt");
		arWfScdLvlTm = (EZDFStringItem)newItem("arWfScdLvlTm");
		arWfThirdLvlUserId = (EZDFStringItem)newItem("arWfThirdLvlUserId");
		arWfThirdLvlDt = (EZDFDateItem)newItem("arWfThirdLvlDt");
		arWfThirdLvlTm = (EZDFStringItem)newItem("arWfThirdLvlTm");
		arWfFrthLvlUserId = (EZDFStringItem)newItem("arWfFrthLvlUserId");
		arWfFrthLvlDt = (EZDFDateItem)newItem("arWfFrthLvlDt");
		arWfFrthLvlTm = (EZDFStringItem)newItem("arWfFrthLvlTm");
		arWfCrRejCmntTxt = (EZDFStringItem)newItem("arWfCrRejCmntTxt");
		arWfPendDelCmntTxt = (EZDFStringItem)newItem("arWfPendDelCmntTxt");
		arWfPendUserId = (EZDFStringItem)newItem("arWfPendUserId");
		exptFlg = (EZDFStringItem)newItem("exptFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return AR_PURGE_OFS_SAVE_WRKFMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new AR_PURGE_OFS_SAVE_WRKFMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ezTableID", "ezTableID", null, null, TYPE_HANKAKUEISU, "28", null},
	{"ezCancelFlag", "ezCancelFlag", null, null, TYPE_HANKAKUEISU, "1", null},
	{"ezInTime", "ezInTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezInTimeZone", "ezInTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	{"ezInCompanyCode", "ezInCompanyCode", null, null, TYPE_HANKAKUEISU, "4", null},
	{"ezInUserID", "ezInUserID", null, null, TYPE_HANKAKUEISU, "16", null},
	{"ezInAplID", "ezInAplID", null, null, TYPE_HANKAKUEISU, "64", null},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpCompanyCode", "ezUpCompanyCode", null, null, TYPE_HANKAKUEISU, "4", null},
	{"ezUpUserID", "ezUpUserID", null, null, TYPE_HANKAKUEISU, "16", null},
	{"ezUpAplID", "ezUpAplID", null, null, TYPE_KONZAI, "64", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"arPurgeOfsSaveWrkPk", "arPurgeOfsSaveWrkPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"rcvHdrNum", "rcvHdrNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"rcvDtlNum", "rcvDtlNum", null, null, TYPE_HANKAKUEISU, "4", null},
	{"rcvDt", "rcvDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"rcvTm", "rcvTm", null, null, TYPE_HANKAKUSUJI, "6", null},
	{"rcptStsCd", "rcptStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"rcptBatNum", "rcptBatNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"rcptBatSqNum", "rcptBatSqNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"arRcptTrxTpCd", "arRcptTrxTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"arRcptTpCd", "arRcptTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dealCcyCd", "dealCcyCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"dealRcptAmt", "dealRcptAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealRcptRmngBalAmt", "dealRcptRmngBalAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"rcptDt", "rcptDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"glDt", "glDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"rcptChkNum", "rcptChkNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"rcptChkDt", "rcptChkDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"payerCustCd", "payerCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"arBankAcctCd", "arBankAcctCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"cratMethTpCd", "cratMethTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"rcptFirstCmntTxt", "rcptFirstCmntTxt", null, null, TYPE_HANKAKUEISU, "65", null},
	{"rcptScdCmntTxt", "rcptScdCmntTxt", null, null, TYPE_HANKAKUEISU, "65", null},
	{"arCustRefNum", "arCustRefNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"dealRcptDtlAmt", "dealRcptDtlAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealApplyGrsAmt", "dealApplyGrsAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"arTrxTpCd", "arTrxTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"arAdjTpCd", "arAdjTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"tocCd", "tocCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdCd", "coaProdCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"upldCsvRqstPk", "upldCsvRqstPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"grpInvFlg", "grpInvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"arWfInclWireOffFlg", "arWfInclWireOffFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"arWfSubmtToId", "arWfSubmtToId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"arWfLvlCd", "arWfLvlCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"arWfStsCd", "arWfStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"arWfFirstLvlUserId", "arWfFirstLvlUserId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"arWfFirstLvlDt", "arWfFirstLvlDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"arWfFirstLvlTm", "arWfFirstLvlTm", null, null, TYPE_HANKAKUSUJI, "6", null},
	{"arWfScdLvlUserId", "arWfScdLvlUserId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"arWfScdLvlDt", "arWfScdLvlDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"arWfScdLvlTm", "arWfScdLvlTm", null, null, TYPE_HANKAKUSUJI, "6", null},
	{"arWfThirdLvlUserId", "arWfThirdLvlUserId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"arWfThirdLvlDt", "arWfThirdLvlDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"arWfThirdLvlTm", "arWfThirdLvlTm", null, null, TYPE_HANKAKUSUJI, "6", null},
	{"arWfFrthLvlUserId", "arWfFrthLvlUserId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"arWfFrthLvlDt", "arWfFrthLvlDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"arWfFrthLvlTm", "arWfFrthLvlTm", null, null, TYPE_HANKAKUSUJI, "6", null},
	{"arWfCrRejCmntTxt", "arWfCrRejCmntTxt", null, null, TYPE_HANKAKUEISU, "65", null},
	{"arWfPendDelCmntTxt", "arWfPendDelCmntTxt", null, null, TYPE_HANKAKUEISU, "65", null},
	{"arWfPendUserId", "arWfPendUserId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"exptFlg", "exptFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"_EZTableID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezTableID
        {"_EZCancelFlag",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezCancelFlag
        {"_EZRegistrationDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTime
        {"_EZInTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTimeZone
        {"_EZInCompanyCode",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInCompanyCode
        {"_EZInUserID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInUserID
        {"_EZInAplID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInAplID
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"_EZUpCompanyCode",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpCompanyCode
        {"_EZUpdateUserID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpUserID
        {"_EZUpdateApplicationID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpAplID
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"AR_PURGE_OFS_SAVE_WRK_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arPurgeOfsSaveWrkPk
        {"RCV_HDR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcvHdrNum
        {"RCV_DTL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcvDtlNum
        {"RCV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcvDt
        {"RCV_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcvTm
        {"RCPT_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptStsCd
        {"RCPT_BAT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptBatNum
        {"RCPT_BAT_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptBatSqNum
        {"AR_RCPT_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRcptTrxTpCd
        {"AR_RCPT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRcptTpCd
        {"DEAL_CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealCcyCd
        {"DEAL_RCPT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRcptAmt
        {"DEAL_RCPT_RMNG_BAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRcptRmngBalAmt
        {"RCPT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptDt
        {"GL_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glDt
        {"RCPT_CHK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptChkNum
        {"RCPT_CHK_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptChkDt
        {"PAYER_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//payerCustCd
        {"AR_BANK_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arBankAcctCd
        {"CRAT_METH_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratMethTpCd
        {"RCPT_FIRST_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptFirstCmntTxt
        {"RCPT_SCD_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptScdCmntTxt
        {"AR_CUST_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCustRefNum
        {"DEAL_RCPT_DTL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRcptDtlAmt
        {"DEAL_APPLY_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealApplyGrsAmt
        {"AR_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxTpCd
        {"AR_ADJ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpCd
        {"TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd
        {"UPLD_CSV_RQST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvRqstPk
        {"GRP_INV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//grpInvFlg
        {"AR_WF_INCL_WIRE_OFF_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfInclWireOffFlg
        {"AR_WF_SUBMT_TO_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfSubmtToId
        {"AR_WF_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfLvlCd
        {"AR_WF_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfStsCd
        {"AR_WF_FIRST_LVL_USER_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfFirstLvlUserId
        {"AR_WF_FIRST_LVL_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfFirstLvlDt
        {"AR_WF_FIRST_LVL_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfFirstLvlTm
        {"AR_WF_SCD_LVL_USER_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfScdLvlUserId
        {"AR_WF_SCD_LVL_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfScdLvlDt
        {"AR_WF_SCD_LVL_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfScdLvlTm
        {"AR_WF_THIRD_LVL_USER_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfThirdLvlUserId
        {"AR_WF_THIRD_LVL_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfThirdLvlDt
        {"AR_WF_THIRD_LVL_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfThirdLvlTm
        {"AR_WF_FRTH_LVL_USER_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfFrthLvlUserId
        {"AR_WF_FRTH_LVL_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfFrthLvlDt
        {"AR_WF_FRTH_LVL_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfFrthLvlTm
        {"AR_WF_CR_REJ_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfCrRejCmntTxt
        {"AR_WF_PEND_DEL_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfPendDelCmntTxt
        {"AR_WF_PEND_USER_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWfPendUserId
        {"EXPT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//exptFlg
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

	
	/**
	 * Array of schema data(Database data)
	 */
	private static final String[][] DB_CONTENTS = {

		//It is defined as {"Database column name", "Database column type flag", and "Primary key" } etc.
		{"EZTABLEID",  NO, null},  // ezTableID
		{"EZCANCELFLAG",  NO, null},  // ezCancelFlag
		{"EZINTIME",  NO, null},  // ezInTime
		{"EZINTIMEZONE",  NO, null},  // ezInTimeZone
		{"EZINCOMPANYCODE",  NO, null},  // ezInCompanyCode
		{"EZINUSERID",  NO, null},  // ezInUserID
		{"EZINAPLID",  NO, null},  // ezInAplID
		{"EZUPTIME",  NO, null},  // ezUpTime
		{"EZUPTIMEZONE",  NO, null},  // ezUpTimeZone
		{"EZUPCOMPANYCODE",  NO, null},  // ezUpCompanyCode
		{"EZUPUSERID",  NO, null},  // ezUpUserID
		{"EZUPAPLID",  NO, null},  // ezUpAplID
		{"GLBL_CMPY_CD",  NO, DB_PRIMARYKEY},  // glblCmpyCd
		{"AR_PURGE_OFS_SAVE_WRK_PK",  NO, DB_PRIMARYKEY},  // arPurgeOfsSaveWrkPk
		{"RCV_HDR_NUM",  NO, DB_PRIMARYKEY},  // rcvHdrNum
		{"RCV_DTL_NUM",  NO, DB_PRIMARYKEY},  // rcvDtlNum
		{"RCV_DT",  NO, null},  // rcvDt
		{"RCV_TM",  NO, null},  // rcvTm
		{"RCPT_STS_CD",  NO, null},  // rcptStsCd
		{"RCPT_BAT_NUM",  NO, null},  // rcptBatNum
		{"RCPT_BAT_SQ_NUM",  NO, null},  // rcptBatSqNum
		{"AR_RCPT_TRX_TP_CD",  NO, null},  // arRcptTrxTpCd
		{"AR_RCPT_TP_CD",  NO, null},  // arRcptTpCd
		{"DEAL_CCY_CD",  NO, null},  // dealCcyCd
		{"DEAL_RCPT_AMT",  NO, null},  // dealRcptAmt
		{"DEAL_RCPT_RMNG_BAL_AMT",  NO, null},  // dealRcptRmngBalAmt
		{"RCPT_DT",  NO, null},  // rcptDt
		{"GL_DT",  NO, null},  // glDt
		{"RCPT_CHK_NUM",  NO, null},  // rcptChkNum
		{"RCPT_CHK_DT",  NO, null},  // rcptChkDt
		{"PAYER_CUST_CD",  NO, null},  // payerCustCd
		{"AR_BANK_ACCT_CD",  NO, null},  // arBankAcctCd
		{"CRAT_METH_TP_CD",  NO, null},  // cratMethTpCd
		{"RCPT_FIRST_CMNT_TXT",  NO, null},  // rcptFirstCmntTxt
		{"RCPT_SCD_CMNT_TXT",  NO, null},  // rcptScdCmntTxt
		{"AR_CUST_REF_NUM",  NO, null},  // arCustRefNum
		{"DEAL_RCPT_DTL_AMT",  NO, null},  // dealRcptDtlAmt
		{"DEAL_APPLY_GRS_AMT",  NO, null},  // dealApplyGrsAmt
		{"AR_TRX_TP_CD",  NO, null},  // arTrxTpCd
		{"AR_ADJ_TP_CD",  NO, null},  // arAdjTpCd
		{"TOC_CD",  NO, null},  // tocCd
		{"COA_PROD_CD",  NO, null},  // coaProdCd
		{"UPLD_CSV_RQST_PK",  NO, null},  // upldCsvRqstPk
		{"GRP_INV_FLG",  NO, null},  // grpInvFlg
		{"AR_WF_INCL_WIRE_OFF_FLG",  NO, null},  // arWfInclWireOffFlg
		{"AR_WF_SUBMT_TO_ID",  NO, null},  // arWfSubmtToId
		{"AR_WF_LVL_CD",  NO, null},  // arWfLvlCd
		{"AR_WF_STS_CD",  NO, null},  // arWfStsCd
		{"AR_WF_FIRST_LVL_USER_ID",  NO, null},  // arWfFirstLvlUserId
		{"AR_WF_FIRST_LVL_DT",  NO, null},  // arWfFirstLvlDt
		{"AR_WF_FIRST_LVL_TM",  NO, null},  // arWfFirstLvlTm
		{"AR_WF_SCD_LVL_USER_ID",  NO, null},  // arWfScdLvlUserId
		{"AR_WF_SCD_LVL_DT",  NO, null},  // arWfScdLvlDt
		{"AR_WF_SCD_LVL_TM",  NO, null},  // arWfScdLvlTm
		{"AR_WF_THIRD_LVL_USER_ID",  NO, null},  // arWfThirdLvlUserId
		{"AR_WF_THIRD_LVL_DT",  NO, null},  // arWfThirdLvlDt
		{"AR_WF_THIRD_LVL_TM",  NO, null},  // arWfThirdLvlTm
		{"AR_WF_FRTH_LVL_USER_ID",  NO, null},  // arWfFrthLvlUserId
		{"AR_WF_FRTH_LVL_DT",  NO, null},  // arWfFrthLvlDt
		{"AR_WF_FRTH_LVL_TM",  NO, null},  // arWfFrthLvlTm
		{"AR_WF_CR_REJ_CMNT_TXT",  NO, null},  // arWfCrRejCmntTxt
		{"AR_WF_PEND_DEL_CMNT_TXT",  NO, null},  // arWfPendDelCmntTxt
		{"AR_WF_PEND_USER_ID",  NO, null},  // arWfPendUserId
		{"EXPT_FLG",  NO, null},  // exptFlg
	};

	/**
	 * Database Table Name
	 */
	private static final String TABLE_NAME = "AR_PURGE_OFS_SAVE_WRK";

	/**
	 * get Array of Database Data.
	 * @return Array of Database Data
	 */
	protected String[][] getDBContents(){
		return DB_CONTENTS;
	}

	/**
	 * get Table Name.
	 * @return Table Name
	 */
	public String getTableName() {
		return TABLE_NAME;
	}

}
