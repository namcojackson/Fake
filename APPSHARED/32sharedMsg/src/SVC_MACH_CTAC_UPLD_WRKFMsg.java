//This file was automatically generated by Logical Database Layout Definition Document and XLA200710010c.
// Generated on    :20160624095659000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :LogicalDatabaseLayoutDefinition Document_AddIn_XLS200710010.xla XLA200710010c
/*
 *SVC_MACH_CTAC_UPLD_WRKFMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is SVC_MACH_CTAC_UPLD_WRK Table Layout Message class.
 * @author
 * @version XLA200710010c
 */
public class SVC_MACH_CTAC_UPLD_WRKFMsg extends EZDFMsg implements EZDSchemaItemDefines {

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

    /** PROC_MODE_CD*/
	public final EZDFStringItem              procModeCd;

    /** SVC_MACH_MSTR_PK*/
	public final EZDFBigDecimalItem              svcMachMstrPk;

    /** SER_NUM*/
	public final EZDFStringItem              serNum;

    /** CTAC_PSN_PK*/
	public final EZDFBigDecimalItem              ctacPsnPk;

    /** CTAC_PSN_LAST_NM*/
	public final EZDFStringItem              ctacPsnLastNm;

    /** CTAC_PSN_FIRST_NM*/
	public final EZDFStringItem              ctacPsnFirstNm;

    /** DS_CTAC_PNT_TP_CD*/
	public final EZDFStringItem              dsCtacPntTpCd;

    /** DS_CTAC_PNT_VAL_TXT*/
	public final EZDFStringItem              dsCtacPntValTxt;

    /** DS_CTAC_PSN_EXTN_NUM*/
	public final EZDFStringItem              dsCtacPsnExtnNum;

    /** SVC_CTAC_TP_CD*/
	public final EZDFStringItem              svcCtacTpCd;

    /** DS_CTAC_PNT_ACTV_FLG*/
	public final EZDFStringItem              dsCtacPntActvFlg;

    /** EFF_FROM_DT*/
	public final EZDFDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDFDateItem              effThruDt;

    /** UPLD_CSV_RQST_CMNT_TXT*/
	public final EZDFStringItem              upldCsvRqstCmntTxt;


	/**
	 * SVC_MACH_CTAC_UPLD_WRKFMsg is constructor.
	 * The initialization when the instance of SVC_MACH_CTAC_UPLD_WRKFMsg is generated.
	 */
	public SVC_MACH_CTAC_UPLD_WRKFMsg() {
		this(false, -1);
	}

	/**
	 * SVC_MACH_CTAC_UPLD_WRKFMsg is constructor.
	 * The initialization when the instance of SVC_MACH_CTAC_UPLD_WRKFMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public SVC_MACH_CTAC_UPLD_WRKFMsg(boolean child, int eleNo) {
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
		procModeCd = (EZDFStringItem)newItem("procModeCd");
		svcMachMstrPk = (EZDFBigDecimalItem)newItem("svcMachMstrPk");
		serNum = (EZDFStringItem)newItem("serNum");
		ctacPsnPk = (EZDFBigDecimalItem)newItem("ctacPsnPk");
		ctacPsnLastNm = (EZDFStringItem)newItem("ctacPsnLastNm");
		ctacPsnFirstNm = (EZDFStringItem)newItem("ctacPsnFirstNm");
		dsCtacPntTpCd = (EZDFStringItem)newItem("dsCtacPntTpCd");
		dsCtacPntValTxt = (EZDFStringItem)newItem("dsCtacPntValTxt");
		dsCtacPsnExtnNum = (EZDFStringItem)newItem("dsCtacPsnExtnNum");
		svcCtacTpCd = (EZDFStringItem)newItem("svcCtacTpCd");
		dsCtacPntActvFlg = (EZDFStringItem)newItem("dsCtacPntActvFlg");
		effFromDt = (EZDFDateItem)newItem("effFromDt");
		effThruDt = (EZDFDateItem)newItem("effThruDt");
		upldCsvRqstCmntTxt = (EZDFStringItem)newItem("upldCsvRqstCmntTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return SVC_MACH_CTAC_UPLD_WRKFMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new SVC_MACH_CTAC_UPLD_WRKFMsgArray();
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
	{"procModeCd", "procModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnPk", "ctacPsnPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ctacPsnLastNm", "ctacPsnLastNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnFirstNm", "ctacPsnFirstNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsCtacPntTpCd", "dsCtacPntTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsCtacPntValTxt", "dsCtacPntValTxt", null, null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPsnExtnNum", "dsCtacPsnExtnNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"svcCtacTpCd", "svcCtacTpCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsCtacPntActvFlg", "dsCtacPntActvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
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
        {"PROC_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procModeCd
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"CTAC_PSN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnPk
        {"CTAC_PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnLastNm
        {"CTAC_PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnFirstNm
        {"DS_CTAC_PNT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntTpCd
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt
        {"DS_CTAC_PSN_EXTN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnExtnNum
        {"SVC_CTAC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCtacTpCd
        {"DS_CTAC_PNT_ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntActvFlg
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
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
		{"PROC_MODE_CD",  NO, null},  // procModeCd
		{"SVC_MACH_MSTR_PK",  NO, null},  // svcMachMstrPk
		{"SER_NUM",  NO, null},  // serNum
		{"CTAC_PSN_PK",  NO, null},  // ctacPsnPk
		{"CTAC_PSN_LAST_NM",  NO, null},  // ctacPsnLastNm
		{"CTAC_PSN_FIRST_NM",  NO, null},  // ctacPsnFirstNm
		{"DS_CTAC_PNT_TP_CD",  NO, null},  // dsCtacPntTpCd
		{"DS_CTAC_PNT_VAL_TXT",  NO, null},  // dsCtacPntValTxt
		{"DS_CTAC_PSN_EXTN_NUM",  NO, null},  // dsCtacPsnExtnNum
		{"SVC_CTAC_TP_CD",  NO, null},  // svcCtacTpCd
		{"DS_CTAC_PNT_ACTV_FLG",  NO, null},  // dsCtacPntActvFlg
		{"EFF_FROM_DT",  NO, null},  // effFromDt
		{"EFF_THRU_DT",  NO, null},  // effThruDt
		{"UPLD_CSV_RQST_CMNT_TXT",  NO, null},  // upldCsvRqstCmntTxt
	};

	/**
	 * Database Table Name
	 */
	private static final String TABLE_NAME = "SVC_MACH_CTAC_UPLD_WRK";

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
