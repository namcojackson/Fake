//This file was automatically generated by Logical Database Layout Definition Document and XLA200710010c.
// Generated on    :20140313163312000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :LogicalDatabaseLayoutDefinition Document_AddIn_XLS200710010.xla XLA200710010c
/*
 *AR_APPLY_UPLD_RCV_WRKFMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is AR_APPLY_UPLD_RCV_WRK Table Layout Message class.
 * @author
 * @version XLA200710010c
 */
public class AR_APPLY_UPLD_RCV_WRKFMsg extends EZDFMsg implements EZDSchemaItemDefines {

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

    /** RCPT_NUM*/
	public final EZDFStringItem              rcptNum;

    /** RCPT_AR_CUST_REF_NUM*/
	public final EZDFStringItem              rcptArCustRefNum;

    /** DEAL_RCPT_DTL_AMT*/
	public final EZDFBigDecimalItem              dealRcptDtlAmt;

    /** DEAL_TRX_RMNG_BAL_AMT*/
	public final EZDFBigDecimalItem              dealTrxRmngBalAmt;

    /** DEAL_APPLY_GRS_AMT*/
	public final EZDFBigDecimalItem              dealApplyGrsAmt;

    /** APP_AR_CUST_REF_NUM*/
	public final EZDFStringItem              appArCustRefNum;

    /** GL_DT_TXT*/
	public final EZDFStringItem              glDtTxt;

    /** AR_TRX_TP_CD*/
	public final EZDFStringItem              arTrxTpCd;

    /** TRX_AR_CUST_REF_NUM*/
	public final EZDFStringItem              trxArCustRefNum;

    /** CASH_DISC_PCT*/
	public final EZDFBigDecimalItem              cashDiscPct;

    /** DEAL_CASH_DISC_AMT*/
	public final EZDFBigDecimalItem              dealCashDiscAmt;

    /** AR_ADJ_TP_CD*/
	public final EZDFStringItem              arAdjTpCd;

    /** DEAL_AR_ADJ_AMT*/
	public final EZDFBigDecimalItem              dealArAdjAmt;

    /** ADJ_AND_DEDCT_CMNT_TXT*/
	public final EZDFStringItem              adjAndDedctCmntTxt;

    /** UPLD_CSV_RQST_CMNT_TXT*/
	public final EZDFStringItem              upldCsvRqstCmntTxt;


	/**
	 * AR_APPLY_UPLD_RCV_WRKFMsg is constructor.
	 * The initialization when the instance of AR_APPLY_UPLD_RCV_WRKFMsg is generated.
	 */
	public AR_APPLY_UPLD_RCV_WRKFMsg() {
		this(false, -1);
	}

	/**
	 * AR_APPLY_UPLD_RCV_WRKFMsg is constructor.
	 * The initialization when the instance of AR_APPLY_UPLD_RCV_WRKFMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public AR_APPLY_UPLD_RCV_WRKFMsg(boolean child, int eleNo) {
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
		rcptNum = (EZDFStringItem)newItem("rcptNum");
		rcptArCustRefNum = (EZDFStringItem)newItem("rcptArCustRefNum");
		dealRcptDtlAmt = (EZDFBigDecimalItem)newItem("dealRcptDtlAmt");
		dealTrxRmngBalAmt = (EZDFBigDecimalItem)newItem("dealTrxRmngBalAmt");
		dealApplyGrsAmt = (EZDFBigDecimalItem)newItem("dealApplyGrsAmt");
		appArCustRefNum = (EZDFStringItem)newItem("appArCustRefNum");
		glDtTxt = (EZDFStringItem)newItem("glDtTxt");
		arTrxTpCd = (EZDFStringItem)newItem("arTrxTpCd");
		trxArCustRefNum = (EZDFStringItem)newItem("trxArCustRefNum");
		cashDiscPct = (EZDFBigDecimalItem)newItem("cashDiscPct");
		dealCashDiscAmt = (EZDFBigDecimalItem)newItem("dealCashDiscAmt");
		arAdjTpCd = (EZDFStringItem)newItem("arAdjTpCd");
		dealArAdjAmt = (EZDFBigDecimalItem)newItem("dealArAdjAmt");
		adjAndDedctCmntTxt = (EZDFStringItem)newItem("adjAndDedctCmntTxt");
		upldCsvRqstCmntTxt = (EZDFStringItem)newItem("upldCsvRqstCmntTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return AR_APPLY_UPLD_RCV_WRKFMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new AR_APPLY_UPLD_RCV_WRKFMsgArray();
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
	{"rcptNum", "rcptNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"rcptArCustRefNum", "rcptArCustRefNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"dealRcptDtlAmt", "dealRcptDtlAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealTrxRmngBalAmt", "dealTrxRmngBalAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealApplyGrsAmt", "dealApplyGrsAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"appArCustRefNum", "appArCustRefNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"glDtTxt", "glDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"arTrxTpCd", "arTrxTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"trxArCustRefNum", "trxArCustRefNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"cashDiscPct", "cashDiscPct", null, null, TYPE_SEISU_SYOSU, "5", "2"},
	{"dealCashDiscAmt", "dealCashDiscAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"arAdjTpCd", "arAdjTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"dealArAdjAmt", "dealArAdjAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"adjAndDedctCmntTxt", "adjAndDedctCmntTxt", null, null, TYPE_HANKAKUEISU, "65", null},
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
        {"RCPT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptNum
        {"RCPT_AR_CUST_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptArCustRefNum
        {"DEAL_RCPT_DTL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRcptDtlAmt
        {"DEAL_TRX_RMNG_BAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealTrxRmngBalAmt
        {"DEAL_APPLY_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealApplyGrsAmt
        {"APP_AR_CUST_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//appArCustRefNum
        {"GL_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glDtTxt
        {"AR_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxTpCd
        {"TRX_AR_CUST_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxArCustRefNum
        {"CASH_DISC_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cashDiscPct
        {"DEAL_CASH_DISC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealCashDiscAmt
        {"AR_ADJ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpCd
        {"DEAL_AR_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealArAdjAmt
        {"ADJ_AND_DEDCT_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjAndDedctCmntTxt
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
		{"RCPT_NUM",  NO, null},  // rcptNum
		{"RCPT_AR_CUST_REF_NUM",  NO, null},  // rcptArCustRefNum
		{"DEAL_RCPT_DTL_AMT",  NO, null},  // dealRcptDtlAmt
		{"DEAL_TRX_RMNG_BAL_AMT",  NO, null},  // dealTrxRmngBalAmt
		{"DEAL_APPLY_GRS_AMT",  NO, null},  // dealApplyGrsAmt
		{"APP_AR_CUST_REF_NUM",  NO, null},  // appArCustRefNum
		{"GL_DT_TXT",  NO, null},  // glDtTxt
		{"AR_TRX_TP_CD",  NO, null},  // arTrxTpCd
		{"TRX_AR_CUST_REF_NUM",  NO, null},  // trxArCustRefNum
		{"CASH_DISC_PCT",  NO, null},  // cashDiscPct
		{"DEAL_CASH_DISC_AMT",  NO, null},  // dealCashDiscAmt
		{"AR_ADJ_TP_CD",  NO, null},  // arAdjTpCd
		{"DEAL_AR_ADJ_AMT",  NO, null},  // dealArAdjAmt
		{"ADJ_AND_DEDCT_CMNT_TXT",  NO, null},  // adjAndDedctCmntTxt
		{"UPLD_CSV_RQST_CMNT_TXT",  NO, null},  // upldCsvRqstCmntTxt
	};

	/**
	 * Database Table Name
	 */
	private static final String TABLE_NAME = "AR_APPLY_UPLD_RCV_WRK";

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

