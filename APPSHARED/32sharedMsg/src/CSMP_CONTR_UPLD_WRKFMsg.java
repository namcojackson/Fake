//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20180910140114000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *CSMP_CONTR_UPLD_WRKFMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is CSMP_CONTR_UPLD_WRK File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class CSMP_CONTR_UPLD_WRKFMsg extends EZDFMsg implements EZDSchemaItemDefines {

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

    /** DS_ACCT_NUM*/
	public final EZDFStringItem              dsAcctNum;

    /** CSMP_NUM*/
	public final EZDFStringItem              csmpNum;

    /** DLR_REF_NUM*/
	public final EZDFStringItem              dlrRefNum;

    /** PRC_CATG_NM*/
	public final EZDFStringItem              prcCatgNm;

    /** ORIG_COA_BR_CD*/
	public final EZDFStringItem              origCoaBrCd;

    /** RTL_DLR_CD*/
	public final EZDFStringItem              rtlDlrCd;

    /** EFF_FROM_DT*/
	public final EZDFDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDFDateItem              effThruDt;

    /** CUSA_REJ_DT*/
	public final EZDFDateItem              cusaRejDt;

    /** ERL_TRMN_DT*/
	public final EZDFDateItem              erlTrmnDt;

    /** RNW_CSMP_NUM*/
	public final EZDFStringItem              rnwCsmpNum;

    /** UPLFT_EQUIP_RATIO*/
	public final EZDFBigDecimalItem              uplftEquipRatio;

    /** UPLFT_ASRY_RATIO*/
	public final EZDFBigDecimalItem              uplftAsryRatio;

    /** CSMP_NUM_CMNT_TXT*/
	public final EZDFStringItem              csmpNumCmntTxt;

    /** CSMP_CONTR_ACTV_FLG*/
	public final EZDFStringItem              csmpContrActvFlg;

    /** UPLD_CSV_RQST_CMNT_TXT*/
	public final EZDFStringItem              upldCsvRqstCmntTxt;


	/**
	 * CSMP_CONTR_UPLD_WRKFMsg is constructor.
	 * The initialization when the instance of CSMP_CONTR_UPLD_WRKFMsg is generated.
	 */
	public CSMP_CONTR_UPLD_WRKFMsg() {
		this(false, -1);
	}

	/**
	 * CSMP_CONTR_UPLD_WRKFMsg is constructor.
	 * The initialization when the instance of CSMP_CONTR_UPLD_WRKFMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public CSMP_CONTR_UPLD_WRKFMsg(boolean child, int eleNo) {
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
		dsAcctNum = (EZDFStringItem)newItem("dsAcctNum");
		csmpNum = (EZDFStringItem)newItem("csmpNum");
		dlrRefNum = (EZDFStringItem)newItem("dlrRefNum");
		prcCatgNm = (EZDFStringItem)newItem("prcCatgNm");
		origCoaBrCd = (EZDFStringItem)newItem("origCoaBrCd");
		rtlDlrCd = (EZDFStringItem)newItem("rtlDlrCd");
		effFromDt = (EZDFDateItem)newItem("effFromDt");
		effThruDt = (EZDFDateItem)newItem("effThruDt");
		cusaRejDt = (EZDFDateItem)newItem("cusaRejDt");
		erlTrmnDt = (EZDFDateItem)newItem("erlTrmnDt");
		rnwCsmpNum = (EZDFStringItem)newItem("rnwCsmpNum");
		uplftEquipRatio = (EZDFBigDecimalItem)newItem("uplftEquipRatio");
		uplftAsryRatio = (EZDFBigDecimalItem)newItem("uplftAsryRatio");
		csmpNumCmntTxt = (EZDFStringItem)newItem("csmpNumCmntTxt");
		csmpContrActvFlg = (EZDFStringItem)newItem("csmpContrActvFlg");
		upldCsvRqstCmntTxt = (EZDFStringItem)newItem("upldCsvRqstCmntTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return CSMP_CONTR_UPLD_WRKFMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new CSMP_CONTR_UPLD_WRKFMsgArray();
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
	{"dsAcctNum", "dsAcctNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"csmpNum", "csmpNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"dlrRefNum", "dlrRefNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"prcCatgNm", "prcCatgNm", null, null, TYPE_HANKAKUEISU, "75", null},
	{"origCoaBrCd", "origCoaBrCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"rtlDlrCd", "rtlDlrCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"cusaRejDt", "cusaRejDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"erlTrmnDt", "erlTrmnDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"rnwCsmpNum", "rnwCsmpNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"uplftEquipRatio", "uplftEquipRatio", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"uplftAsryRatio", "uplftAsryRatio", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"csmpNumCmntTxt", "csmpNumCmntTxt", null, null, TYPE_HANKAKUEISU, "90", null},
	{"csmpContrActvFlg", "csmpContrActvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
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
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum
        {"CSMP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpNum
        {"DLR_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrRefNum
        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm
        {"ORIG_COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origCoaBrCd
        {"RTL_DLR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlDlrCd
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
        {"CUSA_REJ_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cusaRejDt
        {"ERL_TRMN_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//erlTrmnDt
        {"RNW_CSMP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwCsmpNum
        {"UPLFT_EQUIP_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftEquipRatio
        {"UPLFT_ASRY_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftAsryRatio
        {"CSMP_NUM_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpNumCmntTxt
        {"CSMP_CONTR_ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrActvFlg
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
