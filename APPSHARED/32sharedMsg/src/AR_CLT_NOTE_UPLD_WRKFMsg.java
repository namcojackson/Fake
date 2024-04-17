//This file was automatically generated by Logical Database Layout Definition Document and XLA200710010.
// Generated on    :20100211102535000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :LogicalDatabaseLayoutDefinition Document_AddIn_XLS200710010.xla XLA200710010
/*
 *AR_CLT_NOTE_UPLD_WRKFMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is AR_CLT_NOTE_UPLD_WRK Table Layout Message class.
 * @author
 * @version XLA200710010
 */
public class AR_CLT_NOTE_UPLD_WRKFMsg extends EZDFMsg implements EZDSchemaItemDefines {

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

    /** CR_MGR_PSN_CD*/
	public final EZDFStringItem              crMgrPsnCd;

    /** BILL_TO_CUST_CD*/
	public final EZDFStringItem              billToCustCd;

    /** BILL_TO_CUST_LOC_NM*/
	public final EZDFStringItem              billToCustLocNm;

    /** SELL_TO_CUST_CD*/
	public final EZDFStringItem              sellToCustCd;

    /** SELL_TO_CUST_LOC_NM*/
	public final EZDFStringItem              sellToCustLocNm;

    /** TRX_TP_CD_AND_NUM*/
	public final EZDFStringItem              trxTpCdAndNum;

    /** LAST_FILE_UPLD_DT_TXT*/
	public final EZDFStringItem              lastFileUpldDtTxt;

    /** PMT_TERM_CD*/
	public final EZDFStringItem              pmtTermCd;

    /** CASH_DISC_TERM_CD*/
	public final EZDFStringItem              cashDiscTermCd;

    /** CUST_ISS_PO_NUM*/
	public final EZDFStringItem              custIssPoNum;

    /** AR_TRX_DT_TXT*/
	public final EZDFStringItem              arTrxDtTxt;

    /** INV_DUE_DT_TXT*/
	public final EZDFStringItem              invDueDtTxt;

    /** DEAL_RMNG_BAL_GRS_AMT*/
	public final EZDFBigDecimalItem              dealRmngBalGrsAmt;

    /** TRX_LIFE_DAYS_AOT*/
	public final EZDFBigDecimalItem              trxLifeDaysAot;

    /** AR_CUST_REF_NUM*/
	public final EZDFStringItem              arCustRefNum;

    /** AR_CR_LAST_ENTRY_DT_TXT*/
	public final EZDFStringItem              arCrLastEntryDtTxt;

    /** AR_SLS_LAST_ENTRY_DT_TXT*/
	public final EZDFStringItem              arSlsLastEntryDtTxt;

    /** AR_LGSC_LAST_ENTRY_DT_TXT*/
	public final EZDFStringItem              arLgscLastEntryDtTxt;

    /** AR_CLT_CATG_CD*/
	public final EZDFStringItem              arCltCatgCd;

    /** AR_CLT_CATG_NM*/
	public final EZDFStringItem              arCltCatgNm;

    /** AR_OPEN_TRX_RSN_CD*/
	public final EZDFStringItem              arOpenTrxRsnCd;

    /** AR_OPEN_TRX_RSN_NM*/
	public final EZDFStringItem              arOpenTrxRsnNm;

    /** AR_CLT_RVW_GRP_CD*/
	public final EZDFStringItem              arCltRvwGrpCd;

    /** AR_CLT_RVW_GRP_NM*/
	public final EZDFStringItem              arCltRvwGrpNm;

    /** AR_CLT_RVW_GRP_SUB_CD*/
	public final EZDFStringItem              arCltRvwGrpSubCd;

    /** AR_CLT_RVW_GRP_SUB_NM*/
	public final EZDFStringItem              arCltRvwGrpSubNm;

    /** LGSC_CARR_VND_CD*/
	public final EZDFStringItem              lgscCarrVndCd;

    /** LGSC_CARR_VND_LOC_NM*/
	public final EZDFStringItem              lgscCarrVndLocNm;

    /** WH_CD*/
	public final EZDFStringItem              whCd;

    /** WH_NM*/
	public final EZDFStringItem              whNm;

    /** AR_RVW_CD*/
	public final EZDFStringItem              arRvwCd;

    /** AR_RVW_NM*/
	public final EZDFStringItem              arRvwNm;

    /** AR_LGSC_STS_CD*/
	public final EZDFStringItem              arLgscStsCd;

    /** AR_LGSC_STS_NM*/
	public final EZDFStringItem              arLgscStsNm;

    /** AR_SLS_STS_CD*/
	public final EZDFStringItem              arSlsStsCd;

    /** AR_SLS_STS_NM*/
	public final EZDFStringItem              arSlsStsNm;

    /** AR_CLT_CMNT_TS_TXT*/
	public final EZDFStringItem              arCltCmntTsTxt;

    /** AR_CRAT_PSN_CD*/
	public final EZDFStringItem              arCratPsnCd;

    /** AR_CLT_NOTE_TP_CD*/
	public final EZDFStringItem              arCltNoteTpCd;

    /** AR_CLT_NOTE_TP_NM*/
	public final EZDFStringItem              arCltNoteTpNm;

    /** AR_CLT_GRP_TP_CD*/
	public final EZDFStringItem              arCltGrpTpCd;

    /** AR_CLT_GRP_TP_NM*/
	public final EZDFStringItem              arCltGrpTpNm;

    /** AR_CLT_CMNT_TXT*/
	public final EZDFStringItem              arCltCmntTxt;

    /** AR_CLT_LINE_NUM*/
	public final EZDFBigDecimalItem              arCltLineNum;

    /** UPLD_CSV_RQST_CMNT_TXT*/
	public final EZDFStringItem              upldCsvRqstCmntTxt;


	/**
	 * AR_CLT_NOTE_UPLD_WRKFMsg is constructor.
	 * The initialization when the instance of AR_CLT_NOTE_UPLD_WRKFMsg is generated.
	 */
	public AR_CLT_NOTE_UPLD_WRKFMsg() {
		this(false, -1);
	}

	/**
	 * AR_CLT_NOTE_UPLD_WRKFMsg is constructor.
	 * The initialization when the instance of AR_CLT_NOTE_UPLD_WRKFMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public AR_CLT_NOTE_UPLD_WRKFMsg(boolean child, int eleNo) {
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
		crMgrPsnCd = (EZDFStringItem)newItem("crMgrPsnCd");
		billToCustCd = (EZDFStringItem)newItem("billToCustCd");
		billToCustLocNm = (EZDFStringItem)newItem("billToCustLocNm");
		sellToCustCd = (EZDFStringItem)newItem("sellToCustCd");
		sellToCustLocNm = (EZDFStringItem)newItem("sellToCustLocNm");
		trxTpCdAndNum = (EZDFStringItem)newItem("trxTpCdAndNum");
		lastFileUpldDtTxt = (EZDFStringItem)newItem("lastFileUpldDtTxt");
		pmtTermCd = (EZDFStringItem)newItem("pmtTermCd");
		cashDiscTermCd = (EZDFStringItem)newItem("cashDiscTermCd");
		custIssPoNum = (EZDFStringItem)newItem("custIssPoNum");
		arTrxDtTxt = (EZDFStringItem)newItem("arTrxDtTxt");
		invDueDtTxt = (EZDFStringItem)newItem("invDueDtTxt");
		dealRmngBalGrsAmt = (EZDFBigDecimalItem)newItem("dealRmngBalGrsAmt");
		trxLifeDaysAot = (EZDFBigDecimalItem)newItem("trxLifeDaysAot");
		arCustRefNum = (EZDFStringItem)newItem("arCustRefNum");
		arCrLastEntryDtTxt = (EZDFStringItem)newItem("arCrLastEntryDtTxt");
		arSlsLastEntryDtTxt = (EZDFStringItem)newItem("arSlsLastEntryDtTxt");
		arLgscLastEntryDtTxt = (EZDFStringItem)newItem("arLgscLastEntryDtTxt");
		arCltCatgCd = (EZDFStringItem)newItem("arCltCatgCd");
		arCltCatgNm = (EZDFStringItem)newItem("arCltCatgNm");
		arOpenTrxRsnCd = (EZDFStringItem)newItem("arOpenTrxRsnCd");
		arOpenTrxRsnNm = (EZDFStringItem)newItem("arOpenTrxRsnNm");
		arCltRvwGrpCd = (EZDFStringItem)newItem("arCltRvwGrpCd");
		arCltRvwGrpNm = (EZDFStringItem)newItem("arCltRvwGrpNm");
		arCltRvwGrpSubCd = (EZDFStringItem)newItem("arCltRvwGrpSubCd");
		arCltRvwGrpSubNm = (EZDFStringItem)newItem("arCltRvwGrpSubNm");
		lgscCarrVndCd = (EZDFStringItem)newItem("lgscCarrVndCd");
		lgscCarrVndLocNm = (EZDFStringItem)newItem("lgscCarrVndLocNm");
		whCd = (EZDFStringItem)newItem("whCd");
		whNm = (EZDFStringItem)newItem("whNm");
		arRvwCd = (EZDFStringItem)newItem("arRvwCd");
		arRvwNm = (EZDFStringItem)newItem("arRvwNm");
		arLgscStsCd = (EZDFStringItem)newItem("arLgscStsCd");
		arLgscStsNm = (EZDFStringItem)newItem("arLgscStsNm");
		arSlsStsCd = (EZDFStringItem)newItem("arSlsStsCd");
		arSlsStsNm = (EZDFStringItem)newItem("arSlsStsNm");
		arCltCmntTsTxt = (EZDFStringItem)newItem("arCltCmntTsTxt");
		arCratPsnCd = (EZDFStringItem)newItem("arCratPsnCd");
		arCltNoteTpCd = (EZDFStringItem)newItem("arCltNoteTpCd");
		arCltNoteTpNm = (EZDFStringItem)newItem("arCltNoteTpNm");
		arCltGrpTpCd = (EZDFStringItem)newItem("arCltGrpTpCd");
		arCltGrpTpNm = (EZDFStringItem)newItem("arCltGrpTpNm");
		arCltCmntTxt = (EZDFStringItem)newItem("arCltCmntTxt");
		arCltLineNum = (EZDFBigDecimalItem)newItem("arCltLineNum");
		upldCsvRqstCmntTxt = (EZDFStringItem)newItem("upldCsvRqstCmntTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return AR_CLT_NOTE_UPLD_WRKFMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new AR_CLT_NOTE_UPLD_WRKFMsgArray();
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
	{"crMgrPsnCd", "crMgrPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"billToCustCd", "billToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustLocNm", "billToCustLocNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"sellToCustCd", "sellToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"sellToCustLocNm", "sellToCustLocNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"trxTpCdAndNum", "trxTpCdAndNum", null, null, TYPE_HANKAKUEISU, "17", null},
	{"lastFileUpldDtTxt", "lastFileUpldDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"pmtTermCd", "pmtTermCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"cashDiscTermCd", "cashDiscTermCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"custIssPoNum", "custIssPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"arTrxDtTxt", "arTrxDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"invDueDtTxt", "invDueDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"dealRmngBalGrsAmt", "dealRmngBalGrsAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"trxLifeDaysAot", "trxLifeDaysAot", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"arCustRefNum", "arCustRefNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"arCrLastEntryDtTxt", "arCrLastEntryDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"arSlsLastEntryDtTxt", "arSlsLastEntryDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"arLgscLastEntryDtTxt", "arLgscLastEntryDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"arCltCatgCd", "arCltCatgCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"arCltCatgNm", "arCltCatgNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"arOpenTrxRsnCd", "arOpenTrxRsnCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"arOpenTrxRsnNm", "arOpenTrxRsnNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"arCltRvwGrpCd", "arCltRvwGrpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"arCltRvwGrpNm", "arCltRvwGrpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"arCltRvwGrpSubCd", "arCltRvwGrpSubCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"arCltRvwGrpSubNm", "arCltRvwGrpSubNm", null, null, TYPE_HANKAKUEISU, "15", null},
	{"lgscCarrVndCd", "lgscCarrVndCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"lgscCarrVndLocNm", "lgscCarrVndLocNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"whCd", "whCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"whNm", "whNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"arRvwCd", "arRvwCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"arRvwNm", "arRvwNm", null, null, TYPE_HANKAKUEISU, "90", null},
	{"arLgscStsCd", "arLgscStsCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"arLgscStsNm", "arLgscStsNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"arSlsStsCd", "arSlsStsCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"arSlsStsNm", "arSlsStsNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"arCltCmntTsTxt", "arCltCmntTsTxt", null, null, TYPE_HANKAKUEISU, "19", null},
	{"arCratPsnCd", "arCratPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"arCltNoteTpCd", "arCltNoteTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"arCltNoteTpNm", "arCltNoteTpNm", null, null, TYPE_HANKAKUEISU, "15", null},
	{"arCltGrpTpCd", "arCltGrpTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"arCltGrpTpNm", "arCltGrpTpNm", null, null, TYPE_HANKAKUEISU, "10", null},
	{"arCltCmntTxt", "arCltCmntTxt", null, null, TYPE_HANKAKUEISU, "512", null},
	{"arCltLineNum", "arCltLineNum", null, null, TYPE_SEISU_SYOSU, "3", "0"},
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
        {"CR_MGR_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crMgrPsnCd
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd
        {"BILL_TO_CUST_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustLocNm
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd
        {"SELL_TO_CUST_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustLocNm
        {"TRX_TP_CD_AND_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxTpCdAndNum
        {"LAST_FILE_UPLD_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lastFileUpldDtTxt
        {"PMT_TERM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCd
        {"CASH_DISC_TERM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cashDiscTermCd
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum
        {"AR_TRX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxDtTxt
        {"INV_DUE_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDueDtTxt
        {"DEAL_RMNG_BAL_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRmngBalGrsAmt
        {"TRX_LIFE_DAYS_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLifeDaysAot
        {"AR_CUST_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCustRefNum
        {"AR_CR_LAST_ENTRY_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCrLastEntryDtTxt
        {"AR_SLS_LAST_ENTRY_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arSlsLastEntryDtTxt
        {"AR_LGSC_LAST_ENTRY_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arLgscLastEntryDtTxt
        {"AR_CLT_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCltCatgCd
        {"AR_CLT_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCltCatgNm
        {"AR_OPEN_TRX_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arOpenTrxRsnCd
        {"AR_OPEN_TRX_RSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arOpenTrxRsnNm
        {"AR_CLT_RVW_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCltRvwGrpCd
        {"AR_CLT_RVW_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCltRvwGrpNm
        {"AR_CLT_RVW_GRP_SUB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCltRvwGrpSubCd
        {"AR_CLT_RVW_GRP_SUB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCltRvwGrpSubNm
        {"LGSC_CARR_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lgscCarrVndCd
        {"LGSC_CARR_VND_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lgscCarrVndLocNm
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd
        {"WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whNm
        {"AR_RVW_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRvwCd
        {"AR_RVW_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRvwNm
        {"AR_LGSC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arLgscStsCd
        {"AR_LGSC_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arLgscStsNm
        {"AR_SLS_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arSlsStsCd
        {"AR_SLS_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arSlsStsNm
        {"AR_CLT_CMNT_TS_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCltCmntTsTxt
        {"AR_CRAT_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCratPsnCd
        {"AR_CLT_NOTE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCltNoteTpCd
        {"AR_CLT_NOTE_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCltNoteTpNm
        {"AR_CLT_GRP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCltGrpTpCd
        {"AR_CLT_GRP_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCltGrpTpNm
        {"AR_CLT_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCltCmntTxt
        {"AR_CLT_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCltLineNum
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
		{"CR_MGR_PSN_CD",  NO, null},  // crMgrPsnCd
		{"BILL_TO_CUST_CD",  NO, null},  // billToCustCd
		{"BILL_TO_CUST_LOC_NM",  NO, null},  // billToCustLocNm
		{"SELL_TO_CUST_CD",  NO, null},  // sellToCustCd
		{"SELL_TO_CUST_LOC_NM",  NO, null},  // sellToCustLocNm
		{"TRX_TP_CD_AND_NUM",  NO, null},  // trxTpCdAndNum
		{"LAST_FILE_UPLD_DT_TXT",  NO, null},  // lastFileUpldDtTxt
		{"PMT_TERM_CD",  NO, null},  // pmtTermCd
		{"CASH_DISC_TERM_CD",  NO, null},  // cashDiscTermCd
		{"CUST_ISS_PO_NUM",  NO, null},  // custIssPoNum
		{"AR_TRX_DT_TXT",  NO, null},  // arTrxDtTxt
		{"INV_DUE_DT_TXT",  NO, null},  // invDueDtTxt
		{"DEAL_RMNG_BAL_GRS_AMT",  NO, null},  // dealRmngBalGrsAmt
		{"TRX_LIFE_DAYS_AOT",  NO, null},  // trxLifeDaysAot
		{"AR_CUST_REF_NUM",  NO, null},  // arCustRefNum
		{"AR_CR_LAST_ENTRY_DT_TXT",  NO, null},  // arCrLastEntryDtTxt
		{"AR_SLS_LAST_ENTRY_DT_TXT",  NO, null},  // arSlsLastEntryDtTxt
		{"AR_LGSC_LAST_ENTRY_DT_TXT",  NO, null},  // arLgscLastEntryDtTxt
		{"AR_CLT_CATG_CD",  NO, null},  // arCltCatgCd
		{"AR_CLT_CATG_NM",  NO, null},  // arCltCatgNm
		{"AR_OPEN_TRX_RSN_CD",  NO, null},  // arOpenTrxRsnCd
		{"AR_OPEN_TRX_RSN_NM",  NO, null},  // arOpenTrxRsnNm
		{"AR_CLT_RVW_GRP_CD",  NO, null},  // arCltRvwGrpCd
		{"AR_CLT_RVW_GRP_NM",  NO, null},  // arCltRvwGrpNm
		{"AR_CLT_RVW_GRP_SUB_CD",  NO, null},  // arCltRvwGrpSubCd
		{"AR_CLT_RVW_GRP_SUB_NM",  NO, null},  // arCltRvwGrpSubNm
		{"LGSC_CARR_VND_CD",  NO, null},  // lgscCarrVndCd
		{"LGSC_CARR_VND_LOC_NM",  NO, null},  // lgscCarrVndLocNm
		{"WH_CD",  NO, null},  // whCd
		{"WH_NM",  NO, null},  // whNm
		{"AR_RVW_CD",  NO, null},  // arRvwCd
		{"AR_RVW_NM",  NO, null},  // arRvwNm
		{"AR_LGSC_STS_CD",  NO, null},  // arLgscStsCd
		{"AR_LGSC_STS_NM",  NO, null},  // arLgscStsNm
		{"AR_SLS_STS_CD",  NO, null},  // arSlsStsCd
		{"AR_SLS_STS_NM",  NO, null},  // arSlsStsNm
		{"AR_CLT_CMNT_TS_TXT",  NO, null},  // arCltCmntTsTxt
		{"AR_CRAT_PSN_CD",  NO, null},  // arCratPsnCd
		{"AR_CLT_NOTE_TP_CD",  NO, null},  // arCltNoteTpCd
		{"AR_CLT_NOTE_TP_NM",  NO, null},  // arCltNoteTpNm
		{"AR_CLT_GRP_TP_CD",  NO, null},  // arCltGrpTpCd
		{"AR_CLT_GRP_TP_NM",  NO, null},  // arCltGrpTpNm
		{"AR_CLT_CMNT_TXT",  NO, null},  // arCltCmntTxt
		{"AR_CLT_LINE_NUM",  NO, null},  // arCltLineNum
		{"UPLD_CSV_RQST_CMNT_TXT",  NO, null},  // upldCsvRqstCmntTxt
	};

	/**
	 * Database Table Name
	 */
	private static final String TABLE_NAME = "AR_CLT_NOTE_UPLD_WRK";

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
