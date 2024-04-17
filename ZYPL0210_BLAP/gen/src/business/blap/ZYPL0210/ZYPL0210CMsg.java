//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20100309210513000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZYPL0210CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZYPL0210;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZYPL0210 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZYPL0210CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** UPLD_CSV_ID_0S*/
	public final EZDCStringItemArray              upldCsvId_0S;

    /** UPLD_CSV_NM*/
	public final EZDCStringItemArray              upldCsvNm;

    /** UPLD_CSV_ID_0H*/
	public final EZDCStringItem              upldCsvId_0H;

    /** XX_UPLD_CSV_USR_TXT_0H*/
	public final EZDCStringItem              xxUpldCsvUsrTxt_0H;

    /** XX_FILE_DATA_CU*/
	public final EZDCMimeSourceItem              xxFileData_CU;

    /** XX_UPLD_CSV_FILE_PATH_TXT*/
	public final EZDCStringItem              xxUpldCsvFilePathTxt;

    /** UPLD_CSV_FILE_DESC_TXT_0H*/
	public final EZDCStringItem              upldCsvFileDescTxt_0H;

    /** XX_FILE_DATA_TD*/
	public final EZDCMimeSourceItem              xxFileData_TD;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.blap.ZYPL0210.ZYPL0210_ACMsgArray              A;

    /** XX_FILE_DATA_CD*/
	public final EZDCMimeSourceItem              xxFileData_CD;

    /** UPLD_CSV_ID_CT*/
	public final EZDCStringItem              upldCsvId_CT;

    /** UPLD_CSV_RQST_PK_CT*/
	public final EZDCBigDecimalItem              upldCsvRqstPk_CT;

    /** XX_UPLD_CSV_RST_CD*/
	public final EZDCStringItem              xxUpldCsvRstCd;

    /** UPLD_CSV_RST_BIZ_APP_ID*/
	public final EZDCStringItem              upldCsvRstBizAppId;

    /** MENU_PROC_ID*/
	public final EZDCStringItem              menuProcId;

    /** UPLD_CSV_RST_PROC_NM*/
	public final EZDCStringItem              upldCsvRstProcNm;


	/**
	 * ZYPL0210CMsg is constructor.
	 * The initialization when the instance of ZYPL0210CMsg is generated.
	 */
	public ZYPL0210CMsg() {
		this(false, -1);
	}

	/**
	 * ZYPL0210CMsg is constructor.
	 * The initialization when the instance of ZYPL0210CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZYPL0210CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		upldCsvId_0S = (EZDCStringItemArray)newItemArray("upldCsvId_0S");
		upldCsvNm = (EZDCStringItemArray)newItemArray("upldCsvNm");
		upldCsvId_0H = (EZDCStringItem)newItem("upldCsvId_0H");
		xxUpldCsvUsrTxt_0H = (EZDCStringItem)newItem("xxUpldCsvUsrTxt_0H");
		xxFileData_CU = (EZDCMimeSourceItem)newItem("xxFileData_CU");
		xxUpldCsvFilePathTxt = (EZDCStringItem)newItem("xxUpldCsvFilePathTxt");
		upldCsvFileDescTxt_0H = (EZDCStringItem)newItem("upldCsvFileDescTxt_0H");
		xxFileData_TD = (EZDCMimeSourceItem)newItem("xxFileData_TD");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.blap.ZYPL0210.ZYPL0210_ACMsgArray)newMsgArray("A");
		xxFileData_CD = (EZDCMimeSourceItem)newItem("xxFileData_CD");
		upldCsvId_CT = (EZDCStringItem)newItem("upldCsvId_CT");
		upldCsvRqstPk_CT = (EZDCBigDecimalItem)newItem("upldCsvRqstPk_CT");
		xxUpldCsvRstCd = (EZDCStringItem)newItem("xxUpldCsvRstCd");
		upldCsvRstBizAppId = (EZDCStringItem)newItem("upldCsvRstBizAppId");
		menuProcId = (EZDCStringItem)newItem("menuProcId");
		upldCsvRstProcNm = (EZDCStringItem)newItem("upldCsvRstProcNm");
	}

	/**
	 * get the type of array which is stored
	 * @return ZYPL0210CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZYPL0210CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"upldCsvId_0S", "upldCsvId_0S", "0S", "99", TYPE_HANKAKUEISU, "10", null},
	{"upldCsvNm", "upldCsvNm", null, "99", TYPE_HANKAKUEISU, "64", null},
	{"upldCsvId_0H", "upldCsvId_0H", "0H", null, TYPE_HANKAKUEISU, "10", null},
	{"xxUpldCsvUsrTxt_0H", "xxUpldCsvUsrTxt_0H", "0H", null, TYPE_HANKAKUEISU, "16", null},
	{"xxFileData_CU", "xxFileData_CU", "CU", null, TYPE_UPLOAD, null, null},
	{"xxUpldCsvFilePathTxt", "xxUpldCsvFilePathTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"upldCsvFileDescTxt_0H", "upldCsvFileDescTxt_0H", "0H", null, TYPE_HANKAKUEISU, "128", null},
	{"xxFileData_TD", "xxFileData_TD", "TD", null, TYPE_UPLOAD, null, null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "20", "business.blap.ZYPL0210.ZYPL0210_ACMsgArray", null, null},
	
	{"xxFileData_CD", "xxFileData_CD", "CD", null, TYPE_UPLOAD, null, null},
	{"upldCsvId_CT", "upldCsvId_CT", "CT", null, TYPE_HANKAKUEISU, "10", null},
	{"upldCsvRqstPk_CT", "upldCsvRqstPk_CT", "CT", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxUpldCsvRstCd", "xxUpldCsvRstCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"upldCsvRstBizAppId", "upldCsvRstBizAppId", null, null, TYPE_HANKAKUEISU, "8", null},
	{"menuProcId", "menuProcId", null, null, TYPE_HANKAKUEISU, "18", null},
	{"upldCsvRstProcNm", "upldCsvRstProcNm", null, null, TYPE_HANKAKUEISU, "100", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"UPLD_CSV_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvId_0S
        {"UPLD_CSV_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvNm
        {"UPLD_CSV_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvId_0H
        {"XX_UPLD_CSV_USR_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxUpldCsvUsrTxt_0H
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_CU
        {"XX_UPLD_CSV_FILE_PATH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxUpldCsvFilePathTxt
        {"UPLD_CSV_FILE_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvFileDescTxt_0H
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_TD
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_CD
        {"UPLD_CSV_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvId_CT
        {"UPLD_CSV_RQST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvRqstPk_CT
        {"XX_UPLD_CSV_RST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxUpldCsvRstCd
        {"UPLD_CSV_RST_BIZ_APP_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvRstBizAppId
        {"MENU_PROC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcId
        {"UPLD_CSV_RST_PROC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvRstProcNm
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

