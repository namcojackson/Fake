//This file was automatically generated by Logical Database Layout Definition Document and XLA200710010c.
// Generated on    :20091028143340000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :LogicalDatabaseLayoutDefinition Document_AddIn_XLS200710010.xla XLA200710010c
/*
 *DIST_UPLD_WRKFMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is DIST_UPLD_WRK Table Layout Message class.
 * @author
 * @version XLA200710010c
 */
public class DIST_UPLD_WRKFMsg extends EZDFMsg implements EZDSchemaItemDefines {

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

    /** DIST_PLN_NUM*/
	public final EZDFStringItem              distPlnNum;

    /** MDSE_CD*/
	public final EZDFStringItem              mdseCd;

    /** MDSE_NM*/
	public final EZDFStringItem              mdseNm;

    /** INVTY_LOC_CD*/
	public final EZDFStringItem              invtyLocCd;

    /** TM_FRAME_FROM_DT_TXT*/
	public final EZDFStringItem              tmFrameFromDtTxt;

    /** TM_FRAME_THRU_DT_TXT*/
	public final EZDFStringItem              tmFrameThruDtTxt;

    /** DIST_PLN_STS_NM*/
	public final EZDFStringItem              distPlnStsNm;

    /** FIRST_DIST_STRU_SEG_CD*/
	public final EZDFStringItem              firstDistStruSegCd;

    /** FIRST_DIST_STRU_SEG_NM*/
	public final EZDFStringItem              firstDistStruSegNm;

    /** FIRST_DIST_SEG_TP_NM*/
	public final EZDFStringItem              firstDistSegTpNm;

    /** SCD_DIST_STRU_SEG_CD*/
	public final EZDFStringItem              scdDistStruSegCd;

    /** SCD_DIST_STRU_SEG_NM*/
	public final EZDFStringItem              scdDistStruSegNm;

    /** SCD_DIST_SEG_TP_NM*/
	public final EZDFStringItem              scdDistSegTpNm;

    /** THIRD_DIST_STRU_SEG_CD*/
	public final EZDFStringItem              thirdDistStruSegCd;

    /** THIRD_DIST_STRU_SEG_NM*/
	public final EZDFStringItem              thirdDistStruSegNm;

    /** THIRD_DIST_SEG_TP_NM*/
	public final EZDFStringItem              thirdDistSegTpNm;

    /** FRTH_DIST_STRU_SEG_CD*/
	public final EZDFStringItem              frthDistStruSegCd;

    /** FRTH_DIST_STRU_SEG_NM*/
	public final EZDFStringItem              frthDistStruSegNm;

    /** FRTH_DIST_SEG_TP_NM*/
	public final EZDFStringItem              frthDistSegTpNm;

    /** FIFTH_DIST_STRU_SEG_CD*/
	public final EZDFStringItem              fifthDistStruSegCd;

    /** FIFTH_DIST_STRU_SEG_NM*/
	public final EZDFStringItem              fifthDistStruSegNm;

    /** FIFTH_DIST_SEG_TP_NM*/
	public final EZDFStringItem              fifthDistSegTpNm;

    /** SIXTH_DIST_STRU_SEG_CD*/
	public final EZDFStringItem              sixthDistStruSegCd;

    /** SIXTH_DIST_STRU_SEG_NM*/
	public final EZDFStringItem              sixthDistStruSegNm;

    /** SIXTH_DIST_SEG_TP_NM*/
	public final EZDFStringItem              sixthDistSegTpNm;

    /** SVNTH_DIST_STRU_SEG_CD*/
	public final EZDFStringItem              svnthDistStruSegCd;

    /** SVNTH_DIST_STRU_SEG_NM*/
	public final EZDFStringItem              svnthDistStruSegNm;

    /** SVNTH_DIST_SEG_TP_NM*/
	public final EZDFStringItem              svnthDistSegTpNm;

    /** EIGHTH_DIST_STRU_SEG_CD*/
	public final EZDFStringItem              eighthDistStruSegCd;

    /** EIGHTH_DIST_STRU_SEG_NM*/
	public final EZDFStringItem              eighthDistStruSegNm;

    /** EIGHTH_DIST_SEG_TP_NM*/
	public final EZDFStringItem              eighthDistSegTpNm;

    /** DIST_QTY*/
	public final EZDFBigDecimalItem              distQty;

    /** SAVE_QTY*/
	public final EZDFBigDecimalItem              saveQty;

    /** MAN_SOFT_ALLOC_QTY*/
	public final EZDFBigDecimalItem              manSoftAllocQty;

    /** AUTO_SOFT_ALLOC_QTY*/
	public final EZDFBigDecimalItem              autoSoftAllocQty;

    /** HARD_ALLOC_QTY*/
	public final EZDFBigDecimalItem              hardAllocQty;

    /** SHIP_QTY*/
	public final EZDFBigDecimalItem              shipQty;

    /** NOT_SOFT_ALLOC_QTY*/
	public final EZDFBigDecimalItem              notSoftAllocQty;

    /** UPLD_CSV_RQST_CMNT_TXT*/
	public final EZDFStringItem              upldCsvRqstCmntTxt;


	/**
	 * DIST_UPLD_WRKFMsg is constructor.
	 * The initialization when the instance of DIST_UPLD_WRKFMsg is generated.
	 */
	public DIST_UPLD_WRKFMsg() {
		this(false, -1);
	}

	/**
	 * DIST_UPLD_WRKFMsg is constructor.
	 * The initialization when the instance of DIST_UPLD_WRKFMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public DIST_UPLD_WRKFMsg(boolean child, int eleNo) {
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
		distPlnNum = (EZDFStringItem)newItem("distPlnNum");
		mdseCd = (EZDFStringItem)newItem("mdseCd");
		mdseNm = (EZDFStringItem)newItem("mdseNm");
		invtyLocCd = (EZDFStringItem)newItem("invtyLocCd");
		tmFrameFromDtTxt = (EZDFStringItem)newItem("tmFrameFromDtTxt");
		tmFrameThruDtTxt = (EZDFStringItem)newItem("tmFrameThruDtTxt");
		distPlnStsNm = (EZDFStringItem)newItem("distPlnStsNm");
		firstDistStruSegCd = (EZDFStringItem)newItem("firstDistStruSegCd");
		firstDistStruSegNm = (EZDFStringItem)newItem("firstDistStruSegNm");
		firstDistSegTpNm = (EZDFStringItem)newItem("firstDistSegTpNm");
		scdDistStruSegCd = (EZDFStringItem)newItem("scdDistStruSegCd");
		scdDistStruSegNm = (EZDFStringItem)newItem("scdDistStruSegNm");
		scdDistSegTpNm = (EZDFStringItem)newItem("scdDistSegTpNm");
		thirdDistStruSegCd = (EZDFStringItem)newItem("thirdDistStruSegCd");
		thirdDistStruSegNm = (EZDFStringItem)newItem("thirdDistStruSegNm");
		thirdDistSegTpNm = (EZDFStringItem)newItem("thirdDistSegTpNm");
		frthDistStruSegCd = (EZDFStringItem)newItem("frthDistStruSegCd");
		frthDistStruSegNm = (EZDFStringItem)newItem("frthDistStruSegNm");
		frthDistSegTpNm = (EZDFStringItem)newItem("frthDistSegTpNm");
		fifthDistStruSegCd = (EZDFStringItem)newItem("fifthDistStruSegCd");
		fifthDistStruSegNm = (EZDFStringItem)newItem("fifthDistStruSegNm");
		fifthDistSegTpNm = (EZDFStringItem)newItem("fifthDistSegTpNm");
		sixthDistStruSegCd = (EZDFStringItem)newItem("sixthDistStruSegCd");
		sixthDistStruSegNm = (EZDFStringItem)newItem("sixthDistStruSegNm");
		sixthDistSegTpNm = (EZDFStringItem)newItem("sixthDistSegTpNm");
		svnthDistStruSegCd = (EZDFStringItem)newItem("svnthDistStruSegCd");
		svnthDistStruSegNm = (EZDFStringItem)newItem("svnthDistStruSegNm");
		svnthDistSegTpNm = (EZDFStringItem)newItem("svnthDistSegTpNm");
		eighthDistStruSegCd = (EZDFStringItem)newItem("eighthDistStruSegCd");
		eighthDistStruSegNm = (EZDFStringItem)newItem("eighthDistStruSegNm");
		eighthDistSegTpNm = (EZDFStringItem)newItem("eighthDistSegTpNm");
		distQty = (EZDFBigDecimalItem)newItem("distQty");
		saveQty = (EZDFBigDecimalItem)newItem("saveQty");
		manSoftAllocQty = (EZDFBigDecimalItem)newItem("manSoftAllocQty");
		autoSoftAllocQty = (EZDFBigDecimalItem)newItem("autoSoftAllocQty");
		hardAllocQty = (EZDFBigDecimalItem)newItem("hardAllocQty");
		shipQty = (EZDFBigDecimalItem)newItem("shipQty");
		notSoftAllocQty = (EZDFBigDecimalItem)newItem("notSoftAllocQty");
		upldCsvRqstCmntTxt = (EZDFStringItem)newItem("upldCsvRqstCmntTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return DIST_UPLD_WRKFMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new DIST_UPLD_WRKFMsgArray();
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
	{"distPlnNum", "distPlnNum", null, null, TYPE_HANKAKUEISU, "28", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseNm", "mdseNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"invtyLocCd", "invtyLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"tmFrameFromDtTxt", "tmFrameFromDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"tmFrameThruDtTxt", "tmFrameThruDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"distPlnStsNm", "distPlnStsNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"firstDistStruSegCd", "firstDistStruSegCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"firstDistStruSegNm", "firstDistStruSegNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"firstDistSegTpNm", "firstDistSegTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"scdDistStruSegCd", "scdDistStruSegCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"scdDistStruSegNm", "scdDistStruSegNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"scdDistSegTpNm", "scdDistSegTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"thirdDistStruSegCd", "thirdDistStruSegCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"thirdDistStruSegNm", "thirdDistStruSegNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"thirdDistSegTpNm", "thirdDistSegTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"frthDistStruSegCd", "frthDistStruSegCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"frthDistStruSegNm", "frthDistStruSegNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"frthDistSegTpNm", "frthDistSegTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"fifthDistStruSegCd", "fifthDistStruSegCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"fifthDistStruSegNm", "fifthDistStruSegNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"fifthDistSegTpNm", "fifthDistSegTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"sixthDistStruSegCd", "sixthDistStruSegCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"sixthDistStruSegNm", "sixthDistStruSegNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"sixthDistSegTpNm", "sixthDistSegTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svnthDistStruSegCd", "svnthDistStruSegCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"svnthDistStruSegNm", "svnthDistStruSegNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"svnthDistSegTpNm", "svnthDistSegTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"eighthDistStruSegCd", "eighthDistStruSegCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"eighthDistStruSegNm", "eighthDistStruSegNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"eighthDistSegTpNm", "eighthDistSegTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"distQty", "distQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"saveQty", "saveQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"manSoftAllocQty", "manSoftAllocQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"autoSoftAllocQty", "autoSoftAllocQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"hardAllocQty", "hardAllocQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"shipQty", "shipQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"notSoftAllocQty", "notSoftAllocQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
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
        {"DIST_PLN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//distPlnNum
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDSE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseNm
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd
        {"TM_FRAME_FROM_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tmFrameFromDtTxt
        {"TM_FRAME_THRU_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tmFrameThruDtTxt
        {"DIST_PLN_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//distPlnStsNm
        {"FIRST_DIST_STRU_SEG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstDistStruSegCd
        {"FIRST_DIST_STRU_SEG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstDistStruSegNm
        {"FIRST_DIST_SEG_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstDistSegTpNm
        {"SCD_DIST_STRU_SEG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdDistStruSegCd
        {"SCD_DIST_STRU_SEG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdDistStruSegNm
        {"SCD_DIST_SEG_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdDistSegTpNm
        {"THIRD_DIST_STRU_SEG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdDistStruSegCd
        {"THIRD_DIST_STRU_SEG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdDistStruSegNm
        {"THIRD_DIST_SEG_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdDistSegTpNm
        {"FRTH_DIST_STRU_SEG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthDistStruSegCd
        {"FRTH_DIST_STRU_SEG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthDistStruSegNm
        {"FRTH_DIST_SEG_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthDistSegTpNm
        {"FIFTH_DIST_STRU_SEG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fifthDistStruSegCd
        {"FIFTH_DIST_STRU_SEG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fifthDistStruSegNm
        {"FIFTH_DIST_SEG_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fifthDistSegTpNm
        {"SIXTH_DIST_STRU_SEG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sixthDistStruSegCd
        {"SIXTH_DIST_STRU_SEG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sixthDistStruSegNm
        {"SIXTH_DIST_SEG_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sixthDistSegTpNm
        {"SVNTH_DIST_STRU_SEG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svnthDistStruSegCd
        {"SVNTH_DIST_STRU_SEG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svnthDistStruSegNm
        {"SVNTH_DIST_SEG_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svnthDistSegTpNm
        {"EIGHTH_DIST_STRU_SEG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eighthDistStruSegCd
        {"EIGHTH_DIST_STRU_SEG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eighthDistStruSegNm
        {"EIGHTH_DIST_SEG_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eighthDistSegTpNm
        {"DIST_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//distQty
        {"SAVE_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//saveQty
        {"MAN_SOFT_ALLOC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manSoftAllocQty
        {"AUTO_SOFT_ALLOC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoSoftAllocQty
        {"HARD_ALLOC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hardAllocQty
        {"SHIP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipQty
        {"NOT_SOFT_ALLOC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//notSoftAllocQty
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
		{"DIST_PLN_NUM",  NO, null},  // distPlnNum
		{"MDSE_CD",  NO, null},  // mdseCd
		{"MDSE_NM",  NO, null},  // mdseNm
		{"INVTY_LOC_CD",  NO, null},  // invtyLocCd
		{"TM_FRAME_FROM_DT_TXT",  NO, null},  // tmFrameFromDtTxt
		{"TM_FRAME_THRU_DT_TXT",  NO, null},  // tmFrameThruDtTxt
		{"DIST_PLN_STS_NM",  NO, null},  // distPlnStsNm
		{"FIRST_DIST_STRU_SEG_CD",  NO, null},  // firstDistStruSegCd
		{"FIRST_DIST_STRU_SEG_NM",  NO, null},  // firstDistStruSegNm
		{"FIRST_DIST_SEG_TP_NM",  NO, null},  // firstDistSegTpNm
		{"SCD_DIST_STRU_SEG_CD",  NO, null},  // scdDistStruSegCd
		{"SCD_DIST_STRU_SEG_NM",  NO, null},  // scdDistStruSegNm
		{"SCD_DIST_SEG_TP_NM",  NO, null},  // scdDistSegTpNm
		{"THIRD_DIST_STRU_SEG_CD",  NO, null},  // thirdDistStruSegCd
		{"THIRD_DIST_STRU_SEG_NM",  NO, null},  // thirdDistStruSegNm
		{"THIRD_DIST_SEG_TP_NM",  NO, null},  // thirdDistSegTpNm
		{"FRTH_DIST_STRU_SEG_CD",  NO, null},  // frthDistStruSegCd
		{"FRTH_DIST_STRU_SEG_NM",  NO, null},  // frthDistStruSegNm
		{"FRTH_DIST_SEG_TP_NM",  NO, null},  // frthDistSegTpNm
		{"FIFTH_DIST_STRU_SEG_CD",  NO, null},  // fifthDistStruSegCd
		{"FIFTH_DIST_STRU_SEG_NM",  NO, null},  // fifthDistStruSegNm
		{"FIFTH_DIST_SEG_TP_NM",  NO, null},  // fifthDistSegTpNm
		{"SIXTH_DIST_STRU_SEG_CD",  NO, null},  // sixthDistStruSegCd
		{"SIXTH_DIST_STRU_SEG_NM",  NO, null},  // sixthDistStruSegNm
		{"SIXTH_DIST_SEG_TP_NM",  NO, null},  // sixthDistSegTpNm
		{"SVNTH_DIST_STRU_SEG_CD",  NO, null},  // svnthDistStruSegCd
		{"SVNTH_DIST_STRU_SEG_NM",  NO, null},  // svnthDistStruSegNm
		{"SVNTH_DIST_SEG_TP_NM",  NO, null},  // svnthDistSegTpNm
		{"EIGHTH_DIST_STRU_SEG_CD",  NO, null},  // eighthDistStruSegCd
		{"EIGHTH_DIST_STRU_SEG_NM",  NO, null},  // eighthDistStruSegNm
		{"EIGHTH_DIST_SEG_TP_NM",  NO, null},  // eighthDistSegTpNm
		{"DIST_QTY",  NO, null},  // distQty
		{"SAVE_QTY",  NO, null},  // saveQty
		{"MAN_SOFT_ALLOC_QTY",  NO, null},  // manSoftAllocQty
		{"AUTO_SOFT_ALLOC_QTY",  NO, null},  // autoSoftAllocQty
		{"HARD_ALLOC_QTY",  NO, null},  // hardAllocQty
		{"SHIP_QTY",  NO, null},  // shipQty
		{"NOT_SOFT_ALLOC_QTY",  NO, null},  // notSoftAllocQty
		{"UPLD_CSV_RQST_CMNT_TXT",  NO, null},  // upldCsvRqstCmntTxt
	};

	/**
	 * Database Table Name
	 */
	private static final String TABLE_NAME = "DIST_UPLD_WRK";

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
