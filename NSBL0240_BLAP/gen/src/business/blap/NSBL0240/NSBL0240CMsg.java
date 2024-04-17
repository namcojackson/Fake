//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170530182809000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0240CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0240;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0240 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0240CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDCDateItem              slsDt;

    /** MDL_GRP_NM_H*/
	public final EZDCStringItem              mdlGrpNm_H;

    /** MDL_GRP_ID_H*/
	public final EZDCBigDecimalItem              mdlGrpId_H;

    /** SVC_LINE_BIZ_CD_H*/
	public final EZDCStringItem              svcLineBizCd_H;

    /** LINE_BIZ_TP_CD_L*/
	public final EZDCStringItemArray              lineBizTpCd_L;

    /** LINE_BIZ_TP_DESC_TXT_L*/
	public final EZDCStringItemArray              lineBizTpDescTxt_L;

    /** SVC_TRVL_CHRG_TP_CD_L*/
	public final EZDCStringItemArray              svcTrvlChrgTpCd_L;

    /** SVC_TRVL_CHRG_TP_DESC_TXT_L*/
	public final EZDCStringItemArray              svcTrvlChrgTpDescTxt_L;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDCBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDCBigDecimalItem              xxPageShowTotNum;

    /** XX_SFX_KEY_TXT*/
	public final EZDCStringItem              xxSfxKeyTxt;

    /** XX_ROW_NUM_S*/
	public final EZDCBigDecimalItem              xxRowNum_S;

    /** XX_FILE_DATA_U*/
	public final EZDCMimeSourceItem              xxFileData_U;

    /** XX_FILE_DATA_D*/
	public final EZDCMimeSourceItem              xxFileData_D;

    /** C*/
	public final business.blap.NSBL0240.NSBL0240_CCMsgArray              C;

    /** A*/
	public final business.blap.NSBL0240.NSBL0240_ACMsgArray              A;


	/**
	 * NSBL0240CMsg is constructor.
	 * The initialization when the instance of NSBL0240CMsg is generated.
	 */
	public NSBL0240CMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0240CMsg is constructor.
	 * The initialization when the instance of NSBL0240CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0240CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		slsDt = (EZDCDateItem)newItem("slsDt");
		mdlGrpNm_H = (EZDCStringItem)newItem("mdlGrpNm_H");
		mdlGrpId_H = (EZDCBigDecimalItem)newItem("mdlGrpId_H");
		svcLineBizCd_H = (EZDCStringItem)newItem("svcLineBizCd_H");
		lineBizTpCd_L = (EZDCStringItemArray)newItemArray("lineBizTpCd_L");
		lineBizTpDescTxt_L = (EZDCStringItemArray)newItemArray("lineBizTpDescTxt_L");
		svcTrvlChrgTpCd_L = (EZDCStringItemArray)newItemArray("svcTrvlChrgTpCd_L");
		svcTrvlChrgTpDescTxt_L = (EZDCStringItemArray)newItemArray("svcTrvlChrgTpDescTxt_L");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDCBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDCBigDecimalItem)newItem("xxPageShowTotNum");
		xxSfxKeyTxt = (EZDCStringItem)newItem("xxSfxKeyTxt");
		xxRowNum_S = (EZDCBigDecimalItem)newItem("xxRowNum_S");
		xxFileData_U = (EZDCMimeSourceItem)newItem("xxFileData_U");
		xxFileData_D = (EZDCMimeSourceItem)newItem("xxFileData_D");
		C = (business.blap.NSBL0240.NSBL0240_CCMsgArray)newMsgArray("C");
		A = (business.blap.NSBL0240.NSBL0240_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0240CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0240CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"mdlGrpNm_H", "mdlGrpNm_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"mdlGrpId_H", "mdlGrpId_H", "H", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"svcLineBizCd_H", "svcLineBizCd_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"lineBizTpCd_L", "lineBizTpCd_L", "L", "99", TYPE_HANKAKUEISU, "8", null},
	{"lineBizTpDescTxt_L", "lineBizTpDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"svcTrvlChrgTpCd_L", "svcTrvlChrgTpCd_L", "L", "99", TYPE_HANKAKUEISU, "2", null},
	{"svcTrvlChrgTpDescTxt_L", "svcTrvlChrgTpDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSfxKeyTxt", "xxSfxKeyTxt", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxRowNum_S", "xxRowNum_S", "S", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxFileData_U", "xxFileData_U", "U", null, TYPE_UPLOAD, null, null},
	{"xxFileData_D", "xxFileData_D", "D", null, TYPE_UPLOAD, null, null},
	{"C", "C", null, "50", "business.blap.NSBL0240.NSBL0240_CCMsgArray", null, null},
	
	{"A", "A", null, "50", "business.blap.NSBL0240.NSBL0240_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"MDL_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlGrpNm_H
        {"MDL_GRP_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlGrpId_H
        {"SVC_LINE_BIZ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLineBizCd_H
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_L
        {"LINE_BIZ_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpDescTxt_L
        {"SVC_TRVL_CHRG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTrvlChrgTpCd_L
        {"SVC_TRVL_CHRG_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTrvlChrgTpDescTxt_L
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_SFX_KEY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSfxKeyTxt
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_S
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_U
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_D
		null,	//C
		null,	//A
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
