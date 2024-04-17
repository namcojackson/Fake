//This file was automatically generated by Logical Database Layout Definition Document and XLA200710010.
// Generated on    :20090907101702000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :LogicalDatabaseLayoutDefinition Document_AddIn_XLS200710010.xla XLA200710010
/*
 *E_MAINT_BLLG_WRKFMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is E_MAINT_BLLG_WRK Table Layout Message class.
 * @author
 * @version XLA200710010
 */
public class E_MAINT_BLLG_WRKFMsg extends EZDFMsg implements EZDSchemaItemDefines {

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

    /** CINC_CUST_CD*/
	public final EZDFStringItem              cincCustCd;

    /** CINC_CUST_NM*/
	public final EZDFStringItem              cincCustNm;

    /** BASIC_ACTV_DVC_QTY*/
	public final EZDFBigDecimalItem              basicActvDvcQty;

    /** ADV_ACTV_DVC_QTY*/
	public final EZDFBigDecimalItem              advActvDvcQty;

    /** MFP_ACTV_DVC_QTY*/
	public final EZDFBigDecimalItem              mfpActvDvcQty;

    /** LBP_ACTV_DVC_QTY*/
	public final EZDFBigDecimalItem              lbpActvDvcQty;

    /** MIB_ACTV_DVC_QTY*/
	public final EZDFBigDecimalItem              mibActvDvcQty;

    /** REGD_CUST_QTY*/
	public final EZDFBigDecimalItem              regdCustQty;

    /** REGD_RDS_QTY*/
	public final EZDFBigDecimalItem              regdRdsQty;

    /** REGD_DVC_QTY*/
	public final EZDFBigDecimalItem              regdDvcQty;

    /** PRT_STK_CTRL_CONTR_QTY*/
	public final EZDFBigDecimalItem              prtStkCtrlContrQty;

    /** UPLD_RMK_TXT*/
	public final EZDFStringItem              upldRmkTxt;

    /** UPLD_CSV_RQST_CMNT_TXT*/
	public final EZDFStringItem              upldCsvRqstCmntTxt;


	/**
	 * E_MAINT_BLLG_WRKFMsg is constructor.
	 * The initialization when the instance of E_MAINT_BLLG_WRKFMsg is generated.
	 */
	public E_MAINT_BLLG_WRKFMsg() {
		this(false, -1);
	}

	/**
	 * E_MAINT_BLLG_WRKFMsg is constructor.
	 * The initialization when the instance of E_MAINT_BLLG_WRKFMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public E_MAINT_BLLG_WRKFMsg(boolean child, int eleNo) {
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
		cincCustCd = (EZDFStringItem)newItem("cincCustCd");
		cincCustNm = (EZDFStringItem)newItem("cincCustNm");
		basicActvDvcQty = (EZDFBigDecimalItem)newItem("basicActvDvcQty");
		advActvDvcQty = (EZDFBigDecimalItem)newItem("advActvDvcQty");
		mfpActvDvcQty = (EZDFBigDecimalItem)newItem("mfpActvDvcQty");
		lbpActvDvcQty = (EZDFBigDecimalItem)newItem("lbpActvDvcQty");
		mibActvDvcQty = (EZDFBigDecimalItem)newItem("mibActvDvcQty");
		regdCustQty = (EZDFBigDecimalItem)newItem("regdCustQty");
		regdRdsQty = (EZDFBigDecimalItem)newItem("regdRdsQty");
		regdDvcQty = (EZDFBigDecimalItem)newItem("regdDvcQty");
		prtStkCtrlContrQty = (EZDFBigDecimalItem)newItem("prtStkCtrlContrQty");
		upldRmkTxt = (EZDFStringItem)newItem("upldRmkTxt");
		upldCsvRqstCmntTxt = (EZDFStringItem)newItem("upldCsvRqstCmntTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return E_MAINT_BLLG_WRKFMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new E_MAINT_BLLG_WRKFMsgArray();
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
	{"cincCustCd", "cincCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"cincCustNm", "cincCustNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"basicActvDvcQty", "basicActvDvcQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"advActvDvcQty", "advActvDvcQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"mfpActvDvcQty", "mfpActvDvcQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"lbpActvDvcQty", "lbpActvDvcQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"mibActvDvcQty", "mibActvDvcQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"regdCustQty", "regdCustQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"regdRdsQty", "regdRdsQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"regdDvcQty", "regdDvcQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"prtStkCtrlContrQty", "prtStkCtrlContrQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"upldRmkTxt", "upldRmkTxt", null, null, TYPE_HANKAKUEISU, "30", null},
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
        {"CINC_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cincCustCd
        {"CINC_CUST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cincCustNm
        {"BASIC_ACTV_DVC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basicActvDvcQty
        {"ADV_ACTV_DVC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//advActvDvcQty
        {"MFP_ACTV_DVC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mfpActvDvcQty
        {"LBP_ACTV_DVC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lbpActvDvcQty
        {"MIB_ACTV_DVC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mibActvDvcQty
        {"REGD_CUST_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//regdCustQty
        {"REGD_RDS_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//regdRdsQty
        {"REGD_DVC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//regdDvcQty
        {"PRT_STK_CTRL_CONTR_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prtStkCtrlContrQty
        {"UPLD_RMK_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldRmkTxt
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
		{"CINC_CUST_CD",  NO, null},  // cincCustCd
		{"CINC_CUST_NM",  NO, null},  // cincCustNm
		{"BASIC_ACTV_DVC_QTY",  NO, null},  // basicActvDvcQty
		{"ADV_ACTV_DVC_QTY",  NO, null},  // advActvDvcQty
		{"MFP_ACTV_DVC_QTY",  NO, null},  // mfpActvDvcQty
		{"LBP_ACTV_DVC_QTY",  NO, null},  // lbpActvDvcQty
		{"MIB_ACTV_DVC_QTY",  NO, null},  // mibActvDvcQty
		{"REGD_CUST_QTY",  NO, null},  // regdCustQty
		{"REGD_RDS_QTY",  NO, null},  // regdRdsQty
		{"REGD_DVC_QTY",  NO, null},  // regdDvcQty
		{"PRT_STK_CTRL_CONTR_QTY",  NO, null},  // prtStkCtrlContrQty
		{"UPLD_RMK_TXT",  NO, null},  // upldRmkTxt
		{"UPLD_CSV_RQST_CMNT_TXT",  NO, null},  // upldCsvRqstCmntTxt
	};

	/**
	 * Database Table Name
	 */
	private static final String TABLE_NAME = "E_MAINT_BLLG_WRK";

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
