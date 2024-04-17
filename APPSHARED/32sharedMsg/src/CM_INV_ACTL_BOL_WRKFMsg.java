//This file was automatically generated by Logical Database Layout Definition Document and XLA200710010c.
// Generated on    :20110628141842000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :LogicalDatabaseLayoutDefinition Document_AddIn_XLS200710010.xla XLA200710010c
/*
 *CM_INV_ACTL_BOL_WRKFMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is CM_INV_ACTL_BOL_WRK Table Layout Message class.
 * @author
 * @version XLA200710010c
 */
public class CM_INV_ACTL_BOL_WRKFMsg extends EZDFMsg implements EZDSchemaItemDefines {

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

    /** UPLD_CSV_RQST_PK*/
	public final EZDFBigDecimalItem              upldCsvRqstPk;

    /** UPLD_CSV_RQST_ROW_NUM*/
	public final EZDFBigDecimalItem              upldCsvRqstRowNum;

    /** AP_VND_CD*/
	public final EZDFStringItem              apVndCd;

    /** AP_VND_INV_NUM*/
	public final EZDFStringItem              apVndInvNum;

    /** AP_VND_INV_SQ_NUM*/
	public final EZDFStringItem              apVndInvSqNum;

    /** REM_SQ_NUM*/
	public final EZDFStringItem              remSqNum;

    /** CM_CNTNR_BOL_NUM*/
	public final EZDFStringItem              cmCntnrBolNum;

    /** CM_CNTNR_BOL_CD*/
	public final EZDFStringItem              cmCntnrBolCd;

    /** VND_CD*/
	public final EZDFStringItem              vndCd;

    /** INV_DT*/
	public final EZDFDateItem              invDt;

    /** CCY_CD*/
	public final EZDFStringItem              ccyCd;

    /** PMT_DUE_DT*/
	public final EZDFDateItem              pmtDueDt;

    /** INV_OC_ORIG_COST_AMT*/
	public final EZDFBigDecimalItem              invOcOrigCostAmt;

    /** AC_OC_INTL_FRT_COST_AMT*/
	public final EZDFBigDecimalItem              acOcIntlFrtCostAmt;

    /** AC_OC_INS_COST_AMT*/
	public final EZDFBigDecimalItem              acOcInsCostAmt;

    /** AC_OC_DTY_COST_AMT*/
	public final EZDFBigDecimalItem              acOcDtyCostAmt;

    /** AC_OC_DETN_COST_AMT*/
	public final EZDFBigDecimalItem              acOcDetnCostAmt;

    /** AC_OC_DEMG_COST_AMT*/
	public final EZDFBigDecimalItem              acOcDemgCostAmt;

    /** AC_OC_XD_OPS_COST_AMT*/
	public final EZDFBigDecimalItem              acOcXdOpsCostAmt;

    /** AC_OC_XD_DELY_COST_AMT*/
	public final EZDFBigDecimalItem              acOcXdDelyCostAmt;

    /** AC_OC_DOM_FRT_COST_AMT*/
	public final EZDFBigDecimalItem              acOcDomFrtCostAmt;

    /** AC_OC_HMF_COST_AMT*/
	public final EZDFBigDecimalItem              acOcHmfCostAmt;

    /** AC_OC_MPF_COST_AMT*/
	public final EZDFBigDecimalItem              acOcMpfCostAmt;

    /** AC_OC_ISF_COST_AMT*/
	public final EZDFBigDecimalItem              acOcIsfCostAmt;

    /** AC_OC_ISF_HDLG_COST_AMT*/
	public final EZDFBigDecimalItem              acOcIsfHdlgCostAmt;

    /** AC_OC_TMF_COST_AMT*/
	public final EZDFBigDecimalItem              acOcTmfCostAmt;

    /** AC_OC_CTP_COST_AMT*/
	public final EZDFBigDecimalItem              acOcCtpCostAmt;

    /** AC_OC_CTP_HDLG_COST_AMT*/
	public final EZDFBigDecimalItem              acOcCtpHdlgCostAmt;

    /** AC_OC_ENTRY_COST_AMT*/
	public final EZDFBigDecimalItem              acOcEntryCostAmt;

    /** AC_OC_ANTI_DUMP_COST_AMT*/
	public final EZDFBigDecimalItem              acOcAntiDumpCostAmt;

    /** AC_OC_ADD_LINE_COST_AMT*/
	public final EZDFBigDecimalItem              acOcAddLineCostAmt;

    /** AC_OC_HDLG_COST_AMT*/
	public final EZDFBigDecimalItem              acOcHdlgCostAmt;

    /** AC_OC_TRM_COST_AMT*/
	public final EZDFBigDecimalItem              acOcTrmCostAmt;

    /** AC_OC_STORE_COST_AMT*/
	public final EZDFBigDecimalItem              acOcStoreCostAmt;

    /** AC_OC_DELY_COST_AMT*/
	public final EZDFBigDecimalItem              acOcDelyCostAmt;

    /** AC_OC_OTH_COST_AMT*/
	public final EZDFBigDecimalItem              acOcOthCostAmt;

    /** AC_OC_TOT_COST_AMT*/
	public final EZDFBigDecimalItem              acOcTotCostAmt;

    /** UPLD_CSV_RQST_CMNT_TXT*/
	public final EZDFStringItem              upldCsvRqstCmntTxt;


	/**
	 * CM_INV_ACTL_BOL_WRKFMsg is constructor.
	 * The initialization when the instance of CM_INV_ACTL_BOL_WRKFMsg is generated.
	 */
	public CM_INV_ACTL_BOL_WRKFMsg() {
		this(false, -1);
	}

	/**
	 * CM_INV_ACTL_BOL_WRKFMsg is constructor.
	 * The initialization when the instance of CM_INV_ACTL_BOL_WRKFMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public CM_INV_ACTL_BOL_WRKFMsg(boolean child, int eleNo) {
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
		upldCsvRqstPk = (EZDFBigDecimalItem)newItem("upldCsvRqstPk");
		upldCsvRqstRowNum = (EZDFBigDecimalItem)newItem("upldCsvRqstRowNum");
		apVndCd = (EZDFStringItem)newItem("apVndCd");
		apVndInvNum = (EZDFStringItem)newItem("apVndInvNum");
		apVndInvSqNum = (EZDFStringItem)newItem("apVndInvSqNum");
		remSqNum = (EZDFStringItem)newItem("remSqNum");
		cmCntnrBolNum = (EZDFStringItem)newItem("cmCntnrBolNum");
		cmCntnrBolCd = (EZDFStringItem)newItem("cmCntnrBolCd");
		vndCd = (EZDFStringItem)newItem("vndCd");
		invDt = (EZDFDateItem)newItem("invDt");
		ccyCd = (EZDFStringItem)newItem("ccyCd");
		pmtDueDt = (EZDFDateItem)newItem("pmtDueDt");
		invOcOrigCostAmt = (EZDFBigDecimalItem)newItem("invOcOrigCostAmt");
		acOcIntlFrtCostAmt = (EZDFBigDecimalItem)newItem("acOcIntlFrtCostAmt");
		acOcInsCostAmt = (EZDFBigDecimalItem)newItem("acOcInsCostAmt");
		acOcDtyCostAmt = (EZDFBigDecimalItem)newItem("acOcDtyCostAmt");
		acOcDetnCostAmt = (EZDFBigDecimalItem)newItem("acOcDetnCostAmt");
		acOcDemgCostAmt = (EZDFBigDecimalItem)newItem("acOcDemgCostAmt");
		acOcXdOpsCostAmt = (EZDFBigDecimalItem)newItem("acOcXdOpsCostAmt");
		acOcXdDelyCostAmt = (EZDFBigDecimalItem)newItem("acOcXdDelyCostAmt");
		acOcDomFrtCostAmt = (EZDFBigDecimalItem)newItem("acOcDomFrtCostAmt");
		acOcHmfCostAmt = (EZDFBigDecimalItem)newItem("acOcHmfCostAmt");
		acOcMpfCostAmt = (EZDFBigDecimalItem)newItem("acOcMpfCostAmt");
		acOcIsfCostAmt = (EZDFBigDecimalItem)newItem("acOcIsfCostAmt");
		acOcIsfHdlgCostAmt = (EZDFBigDecimalItem)newItem("acOcIsfHdlgCostAmt");
		acOcTmfCostAmt = (EZDFBigDecimalItem)newItem("acOcTmfCostAmt");
		acOcCtpCostAmt = (EZDFBigDecimalItem)newItem("acOcCtpCostAmt");
		acOcCtpHdlgCostAmt = (EZDFBigDecimalItem)newItem("acOcCtpHdlgCostAmt");
		acOcEntryCostAmt = (EZDFBigDecimalItem)newItem("acOcEntryCostAmt");
		acOcAntiDumpCostAmt = (EZDFBigDecimalItem)newItem("acOcAntiDumpCostAmt");
		acOcAddLineCostAmt = (EZDFBigDecimalItem)newItem("acOcAddLineCostAmt");
		acOcHdlgCostAmt = (EZDFBigDecimalItem)newItem("acOcHdlgCostAmt");
		acOcTrmCostAmt = (EZDFBigDecimalItem)newItem("acOcTrmCostAmt");
		acOcStoreCostAmt = (EZDFBigDecimalItem)newItem("acOcStoreCostAmt");
		acOcDelyCostAmt = (EZDFBigDecimalItem)newItem("acOcDelyCostAmt");
		acOcOthCostAmt = (EZDFBigDecimalItem)newItem("acOcOthCostAmt");
		acOcTotCostAmt = (EZDFBigDecimalItem)newItem("acOcTotCostAmt");
		upldCsvRqstCmntTxt = (EZDFStringItem)newItem("upldCsvRqstCmntTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return CM_INV_ACTL_BOL_WRKFMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new CM_INV_ACTL_BOL_WRKFMsgArray();
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
	{"upldCsvRqstPk", "upldCsvRqstPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"upldCsvRqstRowNum", "upldCsvRqstRowNum", null, null, TYPE_SEISU_SYOSU, "9", "0"},
	{"apVndCd", "apVndCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"apVndInvNum", "apVndInvNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"apVndInvSqNum", "apVndInvSqNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"remSqNum", "remSqNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"cmCntnrBolNum", "cmCntnrBolNum", null, null, TYPE_HANKAKUEISU, "25", null},
	{"cmCntnrBolCd", "cmCntnrBolCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"vndCd", "vndCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"invDt", "invDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"ccyCd", "ccyCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"pmtDueDt", "pmtDueDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"invOcOrigCostAmt", "invOcOrigCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcIntlFrtCostAmt", "acOcIntlFrtCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcInsCostAmt", "acOcInsCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcDtyCostAmt", "acOcDtyCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcDetnCostAmt", "acOcDetnCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcDemgCostAmt", "acOcDemgCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcXdOpsCostAmt", "acOcXdOpsCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcXdDelyCostAmt", "acOcXdDelyCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcDomFrtCostAmt", "acOcDomFrtCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcHmfCostAmt", "acOcHmfCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcMpfCostAmt", "acOcMpfCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcIsfCostAmt", "acOcIsfCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcIsfHdlgCostAmt", "acOcIsfHdlgCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcTmfCostAmt", "acOcTmfCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcCtpCostAmt", "acOcCtpCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcCtpHdlgCostAmt", "acOcCtpHdlgCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcEntryCostAmt", "acOcEntryCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcAntiDumpCostAmt", "acOcAntiDumpCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcAddLineCostAmt", "acOcAddLineCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcHdlgCostAmt", "acOcHdlgCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcTrmCostAmt", "acOcTrmCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcStoreCostAmt", "acOcStoreCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcDelyCostAmt", "acOcDelyCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcOthCostAmt", "acOcOthCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acOcTotCostAmt", "acOcTotCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"upldCsvRqstCmntTxt", "upldCsvRqstCmntTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
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
        {"UPLD_CSV_RQST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvRqstPk
        {"UPLD_CSV_RQST_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvRqstRowNum
        {"AP_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndCd
        {"AP_VND_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndInvNum
        {"AP_VND_INV_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndInvSqNum
        {"REM_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//remSqNum
        {"CM_CNTNR_BOL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmCntnrBolNum
        {"CM_CNTNR_BOL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmCntnrBolCd
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt
        {"CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ccyCd
        {"PMT_DUE_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtDueDt
        {"INV_OC_ORIG_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invOcOrigCostAmt
        {"AC_OC_INTL_FRT_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcIntlFrtCostAmt
        {"AC_OC_INS_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcInsCostAmt
        {"AC_OC_DTY_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcDtyCostAmt
        {"AC_OC_DETN_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcDetnCostAmt
        {"AC_OC_DEMG_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcDemgCostAmt
        {"AC_OC_XD_OPS_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcXdOpsCostAmt
        {"AC_OC_XD_DELY_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcXdDelyCostAmt
        {"AC_OC_DOM_FRT_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcDomFrtCostAmt
        {"AC_OC_HMF_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcHmfCostAmt
        {"AC_OC_MPF_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcMpfCostAmt
        {"AC_OC_ISF_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcIsfCostAmt
        {"AC_OC_ISF_HDLG_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcIsfHdlgCostAmt
        {"AC_OC_TMF_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcTmfCostAmt
        {"AC_OC_CTP_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcCtpCostAmt
        {"AC_OC_CTP_HDLG_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcCtpHdlgCostAmt
        {"AC_OC_ENTRY_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcEntryCostAmt
        {"AC_OC_ANTI_DUMP_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcAntiDumpCostAmt
        {"AC_OC_ADD_LINE_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcAddLineCostAmt
        {"AC_OC_HDLG_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcHdlgCostAmt
        {"AC_OC_TRM_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcTrmCostAmt
        {"AC_OC_STORE_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcStoreCostAmt
        {"AC_OC_DELY_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcDelyCostAmt
        {"AC_OC_OTH_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcOthCostAmt
        {"AC_OC_TOT_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acOcTotCostAmt
        {"UPLD_CSV_RQST_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvRqstCmntTxt
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
		{"UPLD_CSV_RQST_PK",  NO, DB_PRIMARYKEY},  // upldCsvRqstPk
		{"UPLD_CSV_RQST_ROW_NUM",  NO, DB_PRIMARYKEY},  // upldCsvRqstRowNum
		{"AP_VND_CD",  NO, null},  // apVndCd
		{"AP_VND_INV_NUM",  NO, null},  // apVndInvNum
		{"AP_VND_INV_SQ_NUM",  NO, null},  // apVndInvSqNum
		{"REM_SQ_NUM",  NO, null},  // remSqNum
		{"CM_CNTNR_BOL_NUM",  NO, null},  // cmCntnrBolNum
		{"CM_CNTNR_BOL_CD",  NO, null},  // cmCntnrBolCd
		{"VND_CD",  NO, null},  // vndCd
		{"INV_DT",  NO, null},  // invDt
		{"CCY_CD",  NO, null},  // ccyCd
		{"PMT_DUE_DT",  NO, null},  // pmtDueDt
		{"INV_OC_ORIG_COST_AMT",  NO, null},  // invOcOrigCostAmt
		{"AC_OC_INTL_FRT_COST_AMT",  NO, null},  // acOcIntlFrtCostAmt
		{"AC_OC_INS_COST_AMT",  NO, null},  // acOcInsCostAmt
		{"AC_OC_DTY_COST_AMT",  NO, null},  // acOcDtyCostAmt
		{"AC_OC_DETN_COST_AMT",  NO, null},  // acOcDetnCostAmt
		{"AC_OC_DEMG_COST_AMT",  NO, null},  // acOcDemgCostAmt
		{"AC_OC_XD_OPS_COST_AMT",  NO, null},  // acOcXdOpsCostAmt
		{"AC_OC_XD_DELY_COST_AMT",  NO, null},  // acOcXdDelyCostAmt
		{"AC_OC_DOM_FRT_COST_AMT",  NO, null},  // acOcDomFrtCostAmt
		{"AC_OC_HMF_COST_AMT",  NO, null},  // acOcHmfCostAmt
		{"AC_OC_MPF_COST_AMT",  NO, null},  // acOcMpfCostAmt
		{"AC_OC_ISF_COST_AMT",  NO, null},  // acOcIsfCostAmt
		{"AC_OC_ISF_HDLG_COST_AMT",  NO, null},  // acOcIsfHdlgCostAmt
		{"AC_OC_TMF_COST_AMT",  NO, null},  // acOcTmfCostAmt
		{"AC_OC_CTP_COST_AMT",  NO, null},  // acOcCtpCostAmt
		{"AC_OC_CTP_HDLG_COST_AMT",  NO, null},  // acOcCtpHdlgCostAmt
		{"AC_OC_ENTRY_COST_AMT",  NO, null},  // acOcEntryCostAmt
		{"AC_OC_ANTI_DUMP_COST_AMT",  NO, null},  // acOcAntiDumpCostAmt
		{"AC_OC_ADD_LINE_COST_AMT",  NO, null},  // acOcAddLineCostAmt
		{"AC_OC_HDLG_COST_AMT",  NO, null},  // acOcHdlgCostAmt
		{"AC_OC_TRM_COST_AMT",  NO, null},  // acOcTrmCostAmt
		{"AC_OC_STORE_COST_AMT",  NO, null},  // acOcStoreCostAmt
		{"AC_OC_DELY_COST_AMT",  NO, null},  // acOcDelyCostAmt
		{"AC_OC_OTH_COST_AMT",  NO, null},  // acOcOthCostAmt
		{"AC_OC_TOT_COST_AMT",  NO, null},  // acOcTotCostAmt
		{"UPLD_CSV_RQST_CMNT_TXT",  NO, null},  // upldCsvRqstCmntTxt
	};

	/**
	 * Database Table Name
	 */
	private static final String TABLE_NAME = "CM_INV_ACTL_BOL_WRK";

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

