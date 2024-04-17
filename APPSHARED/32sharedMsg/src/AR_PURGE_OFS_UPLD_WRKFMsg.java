//This file was automatically generated by Logical Database Layout Definition Document and XLA200710010c.
// Generated on    :20110623205009000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :LogicalDatabaseLayoutDefinition Document_AddIn_XLS200710010.xla XLA200710010c
/*
 *AR_PURGE_OFS_UPLD_WRKFMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is AR_PURGE_OFS_UPLD_WRK Table Layout Message class.
 * @author
 * @version XLA200710010c
 */
public class AR_PURGE_OFS_UPLD_WRKFMsg extends EZDFMsg implements EZDSchemaItemDefines {

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

    /** GL_DT_TXT*/
	public final EZDFStringItem              glDtTxt;

    /** PAYER_CUST_CD*/
	public final EZDFStringItem              payerCustCd;

    /** AR_RCPT_TRX_TP_CD*/
	public final EZDFStringItem              arRcptTrxTpCd;

    /** AR_RCPT_TP_CD*/
	public final EZDFStringItem              arRcptTpCd;

    /** RCPT_CHK_NUM*/
	public final EZDFStringItem              rcptChkNum;

    /** DEAL_APPLY_GRS_AMT*/
	public final EZDFBigDecimalItem              dealApplyGrsAmt;

    /** AR_CUST_REF_NUM*/
	public final EZDFStringItem              arCustRefNum;

    /** AR_TRX_TP_CD*/
	public final EZDFStringItem              arTrxTpCd;

    /** TOC_CD*/
	public final EZDFStringItem              tocCd;

    /** COA_PROD_CD*/
	public final EZDFStringItem              coaProdCd;

    /** RCPT_FIRST_CMNT_TXT*/
	public final EZDFStringItem              rcptFirstCmntTxt;

    /** RCPT_SCD_CMNT_TXT*/
	public final EZDFStringItem              rcptScdCmntTxt;

    /** GRP_INV_FLG*/
	public final EZDFStringItem              grpInvFlg;

    /** EXPT_FLG*/
	public final EZDFStringItem              exptFlg;

    /** UPLD_CSV_RQST_CMNT_TXT*/
	public final EZDFStringItem              upldCsvRqstCmntTxt;


	/**
	 * AR_PURGE_OFS_UPLD_WRKFMsg is constructor.
	 * The initialization when the instance of AR_PURGE_OFS_UPLD_WRKFMsg is generated.
	 */
	public AR_PURGE_OFS_UPLD_WRKFMsg() {
		this(false, -1);
	}

	/**
	 * AR_PURGE_OFS_UPLD_WRKFMsg is constructor.
	 * The initialization when the instance of AR_PURGE_OFS_UPLD_WRKFMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public AR_PURGE_OFS_UPLD_WRKFMsg(boolean child, int eleNo) {
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
		glDtTxt = (EZDFStringItem)newItem("glDtTxt");
		payerCustCd = (EZDFStringItem)newItem("payerCustCd");
		arRcptTrxTpCd = (EZDFStringItem)newItem("arRcptTrxTpCd");
		arRcptTpCd = (EZDFStringItem)newItem("arRcptTpCd");
		rcptChkNum = (EZDFStringItem)newItem("rcptChkNum");
		dealApplyGrsAmt = (EZDFBigDecimalItem)newItem("dealApplyGrsAmt");
		arCustRefNum = (EZDFStringItem)newItem("arCustRefNum");
		arTrxTpCd = (EZDFStringItem)newItem("arTrxTpCd");
		tocCd = (EZDFStringItem)newItem("tocCd");
		coaProdCd = (EZDFStringItem)newItem("coaProdCd");
		rcptFirstCmntTxt = (EZDFStringItem)newItem("rcptFirstCmntTxt");
		rcptScdCmntTxt = (EZDFStringItem)newItem("rcptScdCmntTxt");
		grpInvFlg = (EZDFStringItem)newItem("grpInvFlg");
		exptFlg = (EZDFStringItem)newItem("exptFlg");
		upldCsvRqstCmntTxt = (EZDFStringItem)newItem("upldCsvRqstCmntTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return AR_PURGE_OFS_UPLD_WRKFMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new AR_PURGE_OFS_UPLD_WRKFMsgArray();
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
	{"glDtTxt", "glDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"payerCustCd", "payerCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"arRcptTrxTpCd", "arRcptTrxTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"arRcptTpCd", "arRcptTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"rcptChkNum", "rcptChkNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"dealApplyGrsAmt", "dealApplyGrsAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"arCustRefNum", "arCustRefNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"arTrxTpCd", "arTrxTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"tocCd", "tocCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdCd", "coaProdCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"rcptFirstCmntTxt", "rcptFirstCmntTxt", null, null, TYPE_HANKAKUEISU, "65", null},
	{"rcptScdCmntTxt", "rcptScdCmntTxt", null, null, TYPE_HANKAKUEISU, "65", null},
	{"grpInvFlg", "grpInvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"exptFlg", "exptFlg", null, null, TYPE_HANKAKUEISU, "1", null},
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
        {"GL_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glDtTxt
        {"PAYER_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//payerCustCd
        {"AR_RCPT_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRcptTrxTpCd
        {"AR_RCPT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRcptTpCd
        {"RCPT_CHK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptChkNum
        {"DEAL_APPLY_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealApplyGrsAmt
        {"AR_CUST_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCustRefNum
        {"AR_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxTpCd
        {"TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd
        {"RCPT_FIRST_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptFirstCmntTxt
        {"RCPT_SCD_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptScdCmntTxt
        {"GRP_INV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//grpInvFlg
        {"EXPT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//exptFlg
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
		{"GL_DT_TXT",  NO, null},  // glDtTxt
		{"PAYER_CUST_CD",  NO, null},  // payerCustCd
		{"AR_RCPT_TRX_TP_CD",  NO, null},  // arRcptTrxTpCd
		{"AR_RCPT_TP_CD",  NO, null},  // arRcptTpCd
		{"RCPT_CHK_NUM",  NO, null},  // rcptChkNum
		{"DEAL_APPLY_GRS_AMT",  NO, null},  // dealApplyGrsAmt
		{"AR_CUST_REF_NUM",  NO, null},  // arCustRefNum
		{"AR_TRX_TP_CD",  NO, null},  // arTrxTpCd
		{"TOC_CD",  NO, null},  // tocCd
		{"COA_PROD_CD",  NO, null},  // coaProdCd
		{"RCPT_FIRST_CMNT_TXT",  NO, null},  // rcptFirstCmntTxt
		{"RCPT_SCD_CMNT_TXT",  NO, null},  // rcptScdCmntTxt
		{"GRP_INV_FLG",  NO, null},  // grpInvFlg
		{"EXPT_FLG",  NO, null},  // exptFlg
		{"UPLD_CSV_RQST_CMNT_TXT",  NO, null},  // upldCsvRqstCmntTxt
	};

	/**
	 * Database Table Name
	 */
	private static final String TABLE_NAME = "AR_PURGE_OFS_UPLD_WRK";

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

