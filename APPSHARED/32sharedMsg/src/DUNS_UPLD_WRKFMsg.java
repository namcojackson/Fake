//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20200716184835000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *DUNS_UPLD_WRKFMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMA2730001 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class DUNS_UPLD_WRKFMsg extends EZDFMsg implements EZDSchemaItemDefines {

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

    /** LOC_NUM*/
	public final EZDFStringItem              locNum;

    /** DUNS_NUM*/
	public final EZDFStringItem              dunsNum;

    /** DS_ULT_DUNS_NUM*/
	public final EZDFStringItem              dsUltDunsNum;

    /** HQ_DUNS_NUM*/
	public final EZDFStringItem              hqDunsNum;

    /** DS_LOC_EMP_NUM*/
	public final EZDFBigDecimalItem              dsLocEmpNum;

    /** DS_LOC_REV_AMT*/
	public final EZDFBigDecimalItem              dsLocRevAmt;

    /** DS_CUST_SIC_CD*/
	public final EZDFStringItem              dsCustSicCd;

    /** DS_CUST_SIC_DESC_TXT*/
	public final EZDFStringItem              dsCustSicDescTxt;

    /** UPLD_CSV_RQST_CMNT_TXT*/
	public final EZDFStringItem              upldCsvRqstCmntTxt;


	/**
	 * DUNS_UPLD_WRKFMsg is constructor.
	 * The initialization when the instance of DUNS_UPLD_WRKFMsg is generated.
	 */
	public DUNS_UPLD_WRKFMsg() {
		this(false, -1);
	}

	/**
	 * DUNS_UPLD_WRKFMsg is constructor.
	 * The initialization when the instance of DUNS_UPLD_WRKFMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public DUNS_UPLD_WRKFMsg(boolean child, int eleNo) {
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
		locNum = (EZDFStringItem)newItem("locNum");
		dunsNum = (EZDFStringItem)newItem("dunsNum");
		dsUltDunsNum = (EZDFStringItem)newItem("dsUltDunsNum");
		hqDunsNum = (EZDFStringItem)newItem("hqDunsNum");
		dsLocEmpNum = (EZDFBigDecimalItem)newItem("dsLocEmpNum");
		dsLocRevAmt = (EZDFBigDecimalItem)newItem("dsLocRevAmt");
		dsCustSicCd = (EZDFStringItem)newItem("dsCustSicCd");
		dsCustSicDescTxt = (EZDFStringItem)newItem("dsCustSicDescTxt");
		upldCsvRqstCmntTxt = (EZDFStringItem)newItem("upldCsvRqstCmntTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return DUNS_UPLD_WRKFMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new DUNS_UPLD_WRKFMsgArray();
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
	{"locNum", "locNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dunsNum", "dunsNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsUltDunsNum", "dsUltDunsNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"hqDunsNum", "hqDunsNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsLocEmpNum", "dsLocEmpNum", null, null, TYPE_SEISU_SYOSU, "8", "0"},
	{"dsLocRevAmt", "dsLocRevAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsCustSicCd", "dsCustSicCd", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsCustSicDescTxt", "dsCustSicDescTxt", null, null, TYPE_HANKAKUEISU, "100", null},
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
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum
        {"DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dunsNum
        {"DS_ULT_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsUltDunsNum
        {"HQ_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hqDunsNum
        {"DS_LOC_EMP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsLocEmpNum
        {"DS_LOC_REV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsLocRevAmt
        {"DS_CUST_SIC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustSicCd
        {"DS_CUST_SIC_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustSicDescTxt
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

