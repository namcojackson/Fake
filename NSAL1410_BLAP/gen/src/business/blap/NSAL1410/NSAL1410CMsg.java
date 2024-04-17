//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20220624092106000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1410CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1410;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1410 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1410CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDCDateItem              slsDt;

    /** XX_FILE_DATA_U*/
	public final EZDCMimeSourceItem              xxFileData_U;

    /** XX_FILE_DATA_D*/
	public final EZDCMimeSourceItem              xxFileData_D;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDCStringItem              xxComnColOrdTxt;

    /** XX_MODE_CD*/
	public final EZDCStringItem              xxModeCd;

    /** DS_CONTR_NUM_H*/
	public final EZDCStringItem              dsContrNum_H;

    /** SVC_CONTR_BR_CD_H*/
	public final EZDCStringItem              svcContrBrCd_H;

    /** SVC_CONTR_BR_DESC_TXT_H*/
	public final EZDCStringItem              svcContrBrDescTxt_H;

    /** SVC_LINE_BIZ_CD_H*/
	public final EZDCStringItem              svcLineBizCd_H;

    /** XX_GENL_FLD_AREA_TXT_H*/
	public final EZDCStringItem              xxGenlFldAreaTxt_H;

    /** PSN_CD_H*/
	public final EZDCStringItem              psnCd_H;

    /** XX_PSN_NM_H*/
	public final EZDCStringItem              xxPsnNm_H;

    /** BILL_TO_CUST_CD_H*/
	public final EZDCStringItem              billToCustCd_H;

    /** LOC_NM_H*/
	public final EZDCStringItem              locNm_H;

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

    /** XX_SORT_TBL_NM*/
	public final EZDCStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDCStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDCStringItem              xxSortOrdByTxt;

    /** A*/
	public final business.blap.NSAL1410.NSAL1410_ACMsgArray              A;

    /** XX_ROW_NUM*/
	public final EZDCBigDecimalItem              xxRowNum;


	/**
	 * NSAL1410CMsg is constructor.
	 * The initialization when the instance of NSAL1410CMsg is generated.
	 */
	public NSAL1410CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1410CMsg is constructor.
	 * The initialization when the instance of NSAL1410CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1410CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		slsDt = (EZDCDateItem)newItem("slsDt");
		xxFileData_U = (EZDCMimeSourceItem)newItem("xxFileData_U");
		xxFileData_D = (EZDCMimeSourceItem)newItem("xxFileData_D");
		xxComnColOrdTxt = (EZDCStringItem)newItem("xxComnColOrdTxt");
		xxModeCd = (EZDCStringItem)newItem("xxModeCd");
		dsContrNum_H = (EZDCStringItem)newItem("dsContrNum_H");
		svcContrBrCd_H = (EZDCStringItem)newItem("svcContrBrCd_H");
		svcContrBrDescTxt_H = (EZDCStringItem)newItem("svcContrBrDescTxt_H");
		svcLineBizCd_H = (EZDCStringItem)newItem("svcLineBizCd_H");
		xxGenlFldAreaTxt_H = (EZDCStringItem)newItem("xxGenlFldAreaTxt_H");
		psnCd_H = (EZDCStringItem)newItem("psnCd_H");
		xxPsnNm_H = (EZDCStringItem)newItem("xxPsnNm_H");
		billToCustCd_H = (EZDCStringItem)newItem("billToCustCd_H");
		locNm_H = (EZDCStringItem)newItem("locNm_H");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDCBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDCBigDecimalItem)newItem("xxPageShowTotNum");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		A = (business.blap.NSAL1410.NSAL1410_ACMsgArray)newMsgArray("A");
		xxRowNum = (EZDCBigDecimalItem)newItem("xxRowNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1410CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1410CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxFileData_U", "xxFileData_U", "U", null, TYPE_UPLOAD, null, null},
	{"xxFileData_D", "xxFileData_D", "D", null, TYPE_UPLOAD, null, null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrNum_H", "dsContrNum_H", "H", null, TYPE_HANKAKUEISU, "30", null},
	{"svcContrBrCd_H", "svcContrBrCd_H", "H", null, TYPE_HANKAKUEISU, "3", null},
	{"svcContrBrDescTxt_H", "svcContrBrDescTxt_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"svcLineBizCd_H", "svcLineBizCd_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"xxGenlFldAreaTxt_H", "xxGenlFldAreaTxt_H", "H", null, TYPE_HANKAKUEISU, "1000", null},
	{"psnCd_H", "psnCd_H", "H", null, TYPE_HANKAKUEISU, "8", null},
	{"xxPsnNm_H", "xxPsnNm_H", "H", null, TYPE_HANKAKUEISU, "62", null},
	{"billToCustCd_H", "billToCustCd_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_H", "locNm_H", "H", null, TYPE_HANKAKUEISU, "60", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "200", "business.blap.NSAL1410.NSAL1410_ACMsgArray", null, null},
	
	{"xxRowNum", "xxRowNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_U
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_D
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_H
        {"SVC_CONTR_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrCd_H
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt_H
        {"SVC_LINE_BIZ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLineBizCd_H
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_H
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_H
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_H
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_H
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_H
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
		null,	//A
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum
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

