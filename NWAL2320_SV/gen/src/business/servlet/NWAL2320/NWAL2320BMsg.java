//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170110144309000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2320BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2320;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2320 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2320BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORD_UPLD_TMPL_TP_CD*/
	public final EZDBStringItem              ordUpldTmplTpCd;

    /** ORD_UPLD_TMPL_TP_CD_L1*/
	public final EZDBStringItemArray              ordUpldTmplTpCd_L1;

    /** ORD_UPLD_TMPL_TP_DESC_TXT_L1*/
	public final EZDBStringItemArray              ordUpldTmplTpDescTxt_L1;

    /** XX_FILE_DATA_DL*/
	public final EZDBMimeSourceItem              xxFileData_DL;

    /** XX_FILE_DATA_UL*/
	public final EZDBMimeSourceItem              xxFileData_UL;

    /** A*/
	public final business.servlet.NWAL2320.NWAL2320_ABMsgArray              A;

    /** B*/
	public final business.servlet.NWAL2320.NWAL2320_BBMsgArray              B;

    /** C*/
	public final business.servlet.NWAL2320.NWAL2320_CBMsgArray              C;

    /** D*/
	public final business.servlet.NWAL2320.NWAL2320_DBMsgArray              D;

    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDBDateItem              slsDt;

    /** MAX_LG_NUM*/
	public final EZDBBigDecimalItem              maxLgNum;

    /** XX_ERR_NUM_UP*/
	public final EZDBBigDecimalItem              xxErrNum_UP;

    /** XX_ERR_NUM_UL*/
	public final EZDBBigDecimalItem              xxErrNum_UL;

    /** XX_PAGE_CD_CM*/
	public final EZDBStringItem              xxPageCd_CM;

    /** XX_PAGE_CD_LC*/
	public final EZDBStringItemArray              xxPageCd_LC;

    /** XX_PAGE_CD_LT*/
	public final EZDBStringItemArray              xxPageCd_LT;

    /** XX_PAGE_SHOW_FROM_NUM_CM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum_CM;

    /** XX_PAGE_SHOW_TO_NUM_CM*/
	public final EZDBBigDecimalItem              xxPageShowToNum_CM;

    /** XX_PAGE_SHOW_OF_NUM_CM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum_CM;

    /** XX_PAGE_SHOW_CUR_NUM_CM*/
	public final EZDBBigDecimalItem              xxPageShowCurNum_CM;

    /** XX_PAGE_SHOW_TOT_NUM_CM*/
	public final EZDBBigDecimalItem              xxPageShowTotNum_CM;


	/**
	 * NWAL2320BMsg is constructor.
	 * The initialization when the instance of NWAL2320BMsg is generated.
	 */
	public NWAL2320BMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2320BMsg is constructor.
	 * The initialization when the instance of NWAL2320BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2320BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ordUpldTmplTpCd = (EZDBStringItem)newItem("ordUpldTmplTpCd");
		ordUpldTmplTpCd_L1 = (EZDBStringItemArray)newItemArray("ordUpldTmplTpCd_L1");
		ordUpldTmplTpDescTxt_L1 = (EZDBStringItemArray)newItemArray("ordUpldTmplTpDescTxt_L1");
		xxFileData_DL = (EZDBMimeSourceItem)newItem("xxFileData_DL");
		xxFileData_UL = (EZDBMimeSourceItem)newItem("xxFileData_UL");
		A = (business.servlet.NWAL2320.NWAL2320_ABMsgArray)newMsgArray("A");
		B = (business.servlet.NWAL2320.NWAL2320_BBMsgArray)newMsgArray("B");
		C = (business.servlet.NWAL2320.NWAL2320_CBMsgArray)newMsgArray("C");
		D = (business.servlet.NWAL2320.NWAL2320_DBMsgArray)newMsgArray("D");
		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		slsDt = (EZDBDateItem)newItem("slsDt");
		maxLgNum = (EZDBBigDecimalItem)newItem("maxLgNum");
		xxErrNum_UP = (EZDBBigDecimalItem)newItem("xxErrNum_UP");
		xxErrNum_UL = (EZDBBigDecimalItem)newItem("xxErrNum_UL");
		xxPageCd_CM = (EZDBStringItem)newItem("xxPageCd_CM");
		xxPageCd_LC = (EZDBStringItemArray)newItemArray("xxPageCd_LC");
		xxPageCd_LT = (EZDBStringItemArray)newItemArray("xxPageCd_LT");
		xxPageShowFromNum_CM = (EZDBBigDecimalItem)newItem("xxPageShowFromNum_CM");
		xxPageShowToNum_CM = (EZDBBigDecimalItem)newItem("xxPageShowToNum_CM");
		xxPageShowOfNum_CM = (EZDBBigDecimalItem)newItem("xxPageShowOfNum_CM");
		xxPageShowCurNum_CM = (EZDBBigDecimalItem)newItem("xxPageShowCurNum_CM");
		xxPageShowTotNum_CM = (EZDBBigDecimalItem)newItem("xxPageShowTotNum_CM");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2320BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2320BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ordUpldTmplTpCd", "ordUpldTmplTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"ordUpldTmplTpCd_L1", "ordUpldTmplTpCd_L1", "L1", "99", TYPE_HANKAKUEISU, "1", null},
	{"ordUpldTmplTpDescTxt_L1", "ordUpldTmplTpDescTxt_L1", "L1", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxFileData_DL", "xxFileData_DL", "DL", null, TYPE_UPLOAD, null, null},
	{"xxFileData_UL", "xxFileData_UL", "UL", null, TYPE_UPLOAD, null, null},
	{"A", "A", null, "200", "business.servlet.NWAL2320.NWAL2320_ABMsgArray", null, null},
	
	{"B", "B", null, "200", "business.servlet.NWAL2320.NWAL2320_BBMsgArray", null, null},
	
	{"C", "C", null, "200", "business.servlet.NWAL2320.NWAL2320_CBMsgArray", null, null},
	
	{"D", "D", null, "200", "business.servlet.NWAL2320.NWAL2320_DBMsgArray", null, null},
	
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"maxLgNum", "maxLgNum", null, null, TYPE_SEISU_SYOSU, "9", "0"},
	{"xxErrNum_UP", "xxErrNum_UP", "UP", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxErrNum_UL", "xxErrNum_UL", "UL", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageCd_CM", "xxPageCd_CM", "CM", null, TYPE_HANKAKUEISU, "5", null},
	{"xxPageCd_LC", "xxPageCd_LC", "LC", "99", TYPE_HANKAKUEISU, "5", null},
	{"xxPageCd_LT", "xxPageCd_LT", "LT", "99", TYPE_HANKAKUEISU, "5", null},
	{"xxPageShowFromNum_CM", "xxPageShowFromNum_CM", "CM", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_CM", "xxPageShowToNum_CM", "CM", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_CM", "xxPageShowOfNum_CM", "CM", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum_CM", "xxPageShowCurNum_CM", "CM", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum_CM", "xxPageShowTotNum_CM", "CM", null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORD_UPLD_TMPL_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordUpldTmplTpCd
        {"ORD_UPLD_TMPL_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordUpldTmplTpCd_L1
        {"ORD_UPLD_TMPL_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordUpldTmplTpDescTxt_L1
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_DL
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_UL
		null,	//A
		null,	//B
		null,	//C
		null,	//D
        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//slsDt
        {"MAX_LG_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxLgNum
        {"XX_ERR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxErrNum_UP
        {"XX_ERR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxErrNum_UL
        {"XX_PAGE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageCd_CM
        {"XX_PAGE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageCd_LC
        {"XX_PAGE_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageCd_LT
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_CM
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_CM
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_CM
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum_CM
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum_CM
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

