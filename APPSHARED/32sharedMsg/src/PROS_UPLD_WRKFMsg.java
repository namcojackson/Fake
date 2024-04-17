//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160616165155000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *PROS_UPLD_WRKFMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is PROS_UPLD_WRK File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class PROS_UPLD_WRKFMsg extends EZDFMsg implements EZDSchemaItemDefines {

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

    /** ORG_NM*/
	public final EZDFStringItem              orgNm;

    /** DS_ACCT_NM*/
	public final EZDFStringItem              dsAcctNm;

    /** FIRST_LINE_ADDR*/
	public final EZDFStringItem              firstLineAddr;

    /** SCD_LINE_ADDR*/
	public final EZDFStringItem              scdLineAddr;

    /** THIRD_LINE_ADDR*/
	public final EZDFStringItem              thirdLineAddr;

    /** FRTH_LINE_ADDR*/
	public final EZDFStringItem              frthLineAddr;

    /** CTY_ADDR*/
	public final EZDFStringItem              ctyAddr;

    /** ST_CD*/
	public final EZDFStringItem              stCd;

    /** POST_CD*/
	public final EZDFStringItem              postCd;

    /** CNTY_NM*/
	public final EZDFStringItem              cntyNm;

    /** CTRY_CD*/
	public final EZDFStringItem              ctryCd;

    /** TEL_NUM*/
	public final EZDFStringItem              telNum;

    /** FAX_NUM*/
	public final EZDFStringItem              faxNum;

    /** DS_ACCT_URL*/
	public final EZDFStringItem              dsAcctUrl;

    /** DS_CUST_SIC_DESC_TXT*/
	public final EZDFStringItem              dsCustSicDescTxt;

    /** DS_LOC_REV_AMT*/
	public final EZDFBigDecimalItem              dsLocRevAmt;

    /** DS_LOC_EMP_NUM*/
	public final EZDFBigDecimalItem              dsLocEmpNum;

    /** DS_CUST_SIC_CD*/
	public final EZDFStringItem              dsCustSicCd;

    /** DS_CUST_CMPY_SIC_CD*/
	public final EZDFStringItem              dsCustCmpySicCd;

    /** DUNS_NUM*/
	public final EZDFStringItem              dunsNum;

    /** DS_ULT_DUNS_NUM*/
	public final EZDFStringItem              dsUltDunsNum;

    /** DS_PRNT_DUNS_NUM*/
	public final EZDFStringItem              dsPrntDunsNum;

    /** DS_ACCT_DUNS_NM*/
	public final EZDFStringItem              dsAcctDunsNm;

    /** GLN_NUM*/
	public final EZDFBigDecimalItem              glnNum;

    /** ACCT_SRC_TXT*/
	public final EZDFStringItem              acctSrcTxt;

    /** LINE_BIZ_TP_CD*/
	public final EZDFStringItem              lineBizTpCd;

    /** DS_CTAC_PSN_SALT_DESC_TXT*/
	public final EZDFStringItem              dsCtacPsnSaltDescTxt;

    /** CTAC_PSN_FIRST_NM*/
	public final EZDFStringItem              ctacPsnFirstNm;

    /** CTAC_PSN_LAST_NM*/
	public final EZDFStringItem              ctacPsnLastNm;

    /** DS_CTAC_PSN_TTL_DESC_TXT*/
	public final EZDFStringItem              dsCtacPsnTtlDescTxt;

    /** CTAC_TP_DESC_TXT*/
	public final EZDFStringItem              ctacTpDescTxt;

    /** DS_CTAC_PNT_TP_DESC_TXT*/
	public final EZDFStringItem              dsCtacPntTpDescTxt;

    /** PROS_CTAC_PHO_NUM*/
	public final EZDFStringItem              prosCtacPhoNum;

    /** PROS_CTAC_FAX_NUM*/
	public final EZDFStringItem              prosCtacFaxNum;

    /** PROS_CTAC_EML_ADDR*/
	public final EZDFStringItem              prosCtacEmlAddr;

    /** PROS_CTAC_CELL_PHO_NUM*/
	public final EZDFStringItem              prosCtacCellPhoNum;

    /** UPLD_CSV_RQST_CMNT_TXT*/
	public final EZDFStringItem              upldCsvRqstCmntTxt;


	/**
	 * PROS_UPLD_WRKFMsg is constructor.
	 * The initialization when the instance of PROS_UPLD_WRKFMsg is generated.
	 */
	public PROS_UPLD_WRKFMsg() {
		this(false, -1);
	}

	/**
	 * PROS_UPLD_WRKFMsg is constructor.
	 * The initialization when the instance of PROS_UPLD_WRKFMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public PROS_UPLD_WRKFMsg(boolean child, int eleNo) {
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
		orgNm = (EZDFStringItem)newItem("orgNm");
		dsAcctNm = (EZDFStringItem)newItem("dsAcctNm");
		firstLineAddr = (EZDFStringItem)newItem("firstLineAddr");
		scdLineAddr = (EZDFStringItem)newItem("scdLineAddr");
		thirdLineAddr = (EZDFStringItem)newItem("thirdLineAddr");
		frthLineAddr = (EZDFStringItem)newItem("frthLineAddr");
		ctyAddr = (EZDFStringItem)newItem("ctyAddr");
		stCd = (EZDFStringItem)newItem("stCd");
		postCd = (EZDFStringItem)newItem("postCd");
		cntyNm = (EZDFStringItem)newItem("cntyNm");
		ctryCd = (EZDFStringItem)newItem("ctryCd");
		telNum = (EZDFStringItem)newItem("telNum");
		faxNum = (EZDFStringItem)newItem("faxNum");
		dsAcctUrl = (EZDFStringItem)newItem("dsAcctUrl");
		dsCustSicDescTxt = (EZDFStringItem)newItem("dsCustSicDescTxt");
		dsLocRevAmt = (EZDFBigDecimalItem)newItem("dsLocRevAmt");
		dsLocEmpNum = (EZDFBigDecimalItem)newItem("dsLocEmpNum");
		dsCustSicCd = (EZDFStringItem)newItem("dsCustSicCd");
		dsCustCmpySicCd = (EZDFStringItem)newItem("dsCustCmpySicCd");
		dunsNum = (EZDFStringItem)newItem("dunsNum");
		dsUltDunsNum = (EZDFStringItem)newItem("dsUltDunsNum");
		dsPrntDunsNum = (EZDFStringItem)newItem("dsPrntDunsNum");
		dsAcctDunsNm = (EZDFStringItem)newItem("dsAcctDunsNm");
		glnNum = (EZDFBigDecimalItem)newItem("glnNum");
		acctSrcTxt = (EZDFStringItem)newItem("acctSrcTxt");
		lineBizTpCd = (EZDFStringItem)newItem("lineBizTpCd");
		dsCtacPsnSaltDescTxt = (EZDFStringItem)newItem("dsCtacPsnSaltDescTxt");
		ctacPsnFirstNm = (EZDFStringItem)newItem("ctacPsnFirstNm");
		ctacPsnLastNm = (EZDFStringItem)newItem("ctacPsnLastNm");
		dsCtacPsnTtlDescTxt = (EZDFStringItem)newItem("dsCtacPsnTtlDescTxt");
		ctacTpDescTxt = (EZDFStringItem)newItem("ctacTpDescTxt");
		dsCtacPntTpDescTxt = (EZDFStringItem)newItem("dsCtacPntTpDescTxt");
		prosCtacPhoNum = (EZDFStringItem)newItem("prosCtacPhoNum");
		prosCtacFaxNum = (EZDFStringItem)newItem("prosCtacFaxNum");
		prosCtacEmlAddr = (EZDFStringItem)newItem("prosCtacEmlAddr");
		prosCtacCellPhoNum = (EZDFStringItem)newItem("prosCtacCellPhoNum");
		upldCsvRqstCmntTxt = (EZDFStringItem)newItem("upldCsvRqstCmntTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return PROS_UPLD_WRKFMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new PROS_UPLD_WRKFMsgArray();
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
	{"orgNm", "orgNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsAcctNm", "dsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"firstLineAddr", "firstLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"scdLineAddr", "scdLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"thirdLineAddr", "thirdLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"frthLineAddr", "frthLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"ctyAddr", "ctyAddr", null, null, TYPE_HANKAKUEISU, "25", null},
	{"stCd", "stCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"postCd", "postCd", null, null, TYPE_HANKAKUEISU, "15", null},
	{"cntyNm", "cntyNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"ctryCd", "ctryCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"telNum", "telNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"faxNum", "faxNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctUrl", "dsAcctUrl", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"dsCustSicDescTxt", "dsCustSicDescTxt", null, null, TYPE_HANKAKUEISU, "100", null},
	{"dsLocRevAmt", "dsLocRevAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsLocEmpNum", "dsLocEmpNum", null, null, TYPE_SEISU_SYOSU, "8", "0"},
	{"dsCustSicCd", "dsCustSicCd", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsCustCmpySicCd", "dsCustCmpySicCd", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dunsNum", "dunsNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsUltDunsNum", "dsUltDunsNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsPrntDunsNum", "dsPrntDunsNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctDunsNm", "dsAcctDunsNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"glnNum", "glnNum", null, null, TYPE_SEISU_SYOSU, "13", "0"},
	{"acctSrcTxt", "acctSrcTxt", null, null, TYPE_HANKAKUEISU, "100", null},
	{"lineBizTpCd", "lineBizTpCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"dsCtacPsnSaltDescTxt", "dsCtacPsnSaltDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"ctacPsnFirstNm", "ctacPsnFirstNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnLastNm", "ctacPsnLastNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsCtacPsnTtlDescTxt", "dsCtacPsnTtlDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"ctacTpDescTxt", "ctacTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsCtacPntTpDescTxt", "dsCtacPntTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prosCtacPhoNum", "prosCtacPhoNum", null, null, TYPE_HANKAKUEISU, "320", null},
	{"prosCtacFaxNum", "prosCtacFaxNum", null, null, TYPE_HANKAKUEISU, "320", null},
	{"prosCtacEmlAddr", "prosCtacEmlAddr", null, null, TYPE_HANKAKUEISU, "320", null},
	{"prosCtacCellPhoNum", "prosCtacCellPhoNum", null, null, TYPE_HANKAKUEISU, "320", null},
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
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr
        {"SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdLineAddr
        {"THIRD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdLineAddr
        {"FRTH_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthLineAddr
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd
        {"CNTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cntyNm
        {"CTRY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctryCd
        {"TEL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//telNum
        {"FAX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//faxNum
        {"DS_ACCT_URL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctUrl
        {"DS_CUST_SIC_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustSicDescTxt
        {"DS_LOC_REV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsLocRevAmt
        {"DS_LOC_EMP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsLocEmpNum
        {"DS_CUST_SIC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustSicCd
        {"DS_CUST_CMPY_SIC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustCmpySicCd
        {"DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsNum
        {"DS_ULT_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsUltDunsNum
        {"DS_PRNT_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPrntDunsNum
        {"DS_ACCT_DUNS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctDunsNm
        {"GLN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glnNum
        {"ACCT_SRC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctSrcTxt
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd
        {"DS_CTAC_PSN_SALT_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnSaltDescTxt
        {"CTAC_PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnFirstNm
        {"CTAC_PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnLastNm
        {"DS_CTAC_PSN_TTL_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnTtlDescTxt
        {"CTAC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacTpDescTxt
        {"DS_CTAC_PNT_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntTpDescTxt
        {"PROS_CTAC_PHO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prosCtacPhoNum
        {"PROS_CTAC_FAX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prosCtacFaxNum
        {"PROS_CTAC_EML_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prosCtacEmlAddr
        {"PROS_CTAC_CELL_PHO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prosCtacCellPhoNum
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

}
