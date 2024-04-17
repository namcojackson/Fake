//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161128143601000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0820CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0820;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0820 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0820CMsg extends EZDCMsg implements EZDSchemaItemDefines {

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

    /** XX_ROW_NUM_H*/
	public final EZDCBigDecimalItem              xxRowNum_H;

    /** XX_EXST_FLG*/
	public final EZDCStringItem              xxExstFlg;

    /** CONTR_INTFC_SRC_TP_CD_SS*/
	public final EZDCStringItem              contrIntfcSrcTpCd_SS;

    /** CONTR_INTFC_SRC_TP_CD_SC*/
	public final EZDCStringItemArray              contrIntfcSrcTpCd_SC;

    /** CONTR_INTFC_SRC_TP_DESC_TXT_SC*/
	public final EZDCStringItemArray              contrIntfcSrcTpDescTxt_SC;

    /** DS_CONTR_INTFC_BAT_NUM_S*/
	public final EZDCStringItem              dsContrIntfcBatNum_S;

    /** DS_CONTR_SRC_REF_NUM_S*/
	public final EZDCStringItem              dsContrSrcRefNum_S;

    /** DS_CONTR_NUM_S*/
	public final EZDCStringItem              dsContrNum_S;

    /** XX_ERR_FLG_S*/
	public final EZDCStringItem              xxErrFlg_S;

    /** CONTR_INTFC_SRC_TP_CD_S2*/
	public final EZDCStringItem              contrIntfcSrcTpCd_S2;

    /** DS_CONTR_INTFC_BAT_NUM_S2*/
	public final EZDCStringItem              dsContrIntfcBatNum_S2;

    /** DS_CONTR_SRC_REF_NUM_S2*/
	public final EZDCStringItem              dsContrSrcRefNum_S2;

    /** DS_CONTR_NUM_S2*/
	public final EZDCStringItem              dsContrNum_S2;

    /** XX_ERR_FLG_S2*/
	public final EZDCStringItem              xxErrFlg_S2;

    /** A*/
	public final business.blap.NSAL0820.NSAL0820_ACMsgArray              A;

    /** CONTR_INTFC_SRC_TP_CD_AC*/
	public final EZDCStringItemArray              contrIntfcSrcTpCd_AC;

    /** CONTR_INTFC_SRC_TP_DESC_TXT_AC*/
	public final EZDCStringItemArray              contrIntfcSrcTpDescTxt_AC;

    /** XX_SORT_TBL_NM*/
	public final EZDCStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDCStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDCStringItem              xxSortOrdByTxt;

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

    /** T*/
	public final business.blap.NSAL0820.NSAL0820_TCMsgArray              T;

    /** P*/
	public final business.blap.NSAL0820.NSAL0820_PCMsgArray              P;


	/**
	 * NSAL0820CMsg is constructor.
	 * The initialization when the instance of NSAL0820CMsg is generated.
	 */
	public NSAL0820CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0820CMsg is constructor.
	 * The initialization when the instance of NSAL0820CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0820CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		slsDt = (EZDCDateItem)newItem("slsDt");
		xxFileData_U = (EZDCMimeSourceItem)newItem("xxFileData_U");
		xxFileData_D = (EZDCMimeSourceItem)newItem("xxFileData_D");
		xxComnColOrdTxt = (EZDCStringItem)newItem("xxComnColOrdTxt");
		xxModeCd = (EZDCStringItem)newItem("xxModeCd");
		xxRowNum_H = (EZDCBigDecimalItem)newItem("xxRowNum_H");
		xxExstFlg = (EZDCStringItem)newItem("xxExstFlg");
		contrIntfcSrcTpCd_SS = (EZDCStringItem)newItem("contrIntfcSrcTpCd_SS");
		contrIntfcSrcTpCd_SC = (EZDCStringItemArray)newItemArray("contrIntfcSrcTpCd_SC");
		contrIntfcSrcTpDescTxt_SC = (EZDCStringItemArray)newItemArray("contrIntfcSrcTpDescTxt_SC");
		dsContrIntfcBatNum_S = (EZDCStringItem)newItem("dsContrIntfcBatNum_S");
		dsContrSrcRefNum_S = (EZDCStringItem)newItem("dsContrSrcRefNum_S");
		dsContrNum_S = (EZDCStringItem)newItem("dsContrNum_S");
		xxErrFlg_S = (EZDCStringItem)newItem("xxErrFlg_S");
		contrIntfcSrcTpCd_S2 = (EZDCStringItem)newItem("contrIntfcSrcTpCd_S2");
		dsContrIntfcBatNum_S2 = (EZDCStringItem)newItem("dsContrIntfcBatNum_S2");
		dsContrSrcRefNum_S2 = (EZDCStringItem)newItem("dsContrSrcRefNum_S2");
		dsContrNum_S2 = (EZDCStringItem)newItem("dsContrNum_S2");
		xxErrFlg_S2 = (EZDCStringItem)newItem("xxErrFlg_S2");
		A = (business.blap.NSAL0820.NSAL0820_ACMsgArray)newMsgArray("A");
		contrIntfcSrcTpCd_AC = (EZDCStringItemArray)newItemArray("contrIntfcSrcTpCd_AC");
		contrIntfcSrcTpDescTxt_AC = (EZDCStringItemArray)newItemArray("contrIntfcSrcTpDescTxt_AC");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDCBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDCBigDecimalItem)newItem("xxPageShowTotNum");
		T = (business.blap.NSAL0820.NSAL0820_TCMsgArray)newMsgArray("T");
		P = (business.blap.NSAL0820.NSAL0820_PCMsgArray)newMsgArray("P");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0820CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0820CMsgArray();
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
	{"xxRowNum_H", "xxRowNum_H", "H", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxExstFlg", "xxExstFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"contrIntfcSrcTpCd_SS", "contrIntfcSrcTpCd_SS", "SS", null, TYPE_HANKAKUEISU, "5", null},
	{"contrIntfcSrcTpCd_SC", "contrIntfcSrcTpCd_SC", "SC", "99", TYPE_HANKAKUEISU, "5", null},
	{"contrIntfcSrcTpDescTxt_SC", "contrIntfcSrcTpDescTxt_SC", "SC", "99", TYPE_HANKAKUEISU, "50", null},
	{"dsContrIntfcBatNum_S", "dsContrIntfcBatNum_S", "S", null, TYPE_HANKAKUEISU, "14", null},
	{"dsContrSrcRefNum_S", "dsContrSrcRefNum_S", "S", null, TYPE_HANKAKUEISU, "90", null},
	{"dsContrNum_S", "dsContrNum_S", "S", null, TYPE_HANKAKUEISU, "30", null},
	{"xxErrFlg_S", "xxErrFlg_S", "S", null, TYPE_HANKAKUEISU, "1", null},
	{"contrIntfcSrcTpCd_S2", "contrIntfcSrcTpCd_S2", "S2", null, TYPE_HANKAKUEISU, "5", null},
	{"dsContrIntfcBatNum_S2", "dsContrIntfcBatNum_S2", "S2", null, TYPE_HANKAKUEISU, "14", null},
	{"dsContrSrcRefNum_S2", "dsContrSrcRefNum_S2", "S2", null, TYPE_HANKAKUEISU, "90", null},
	{"dsContrNum_S2", "dsContrNum_S2", "S2", null, TYPE_HANKAKUEISU, "30", null},
	{"xxErrFlg_S2", "xxErrFlg_S2", "S2", null, TYPE_HANKAKUEISU, "1", null},
	{"A", "A", null, "50", "business.blap.NSAL0820.NSAL0820_ACMsgArray", null, null},
	
	{"contrIntfcSrcTpCd_AC", "contrIntfcSrcTpCd_AC", "AC", "99", TYPE_HANKAKUEISU, "5", null},
	{"contrIntfcSrcTpDescTxt_AC", "contrIntfcSrcTpDescTxt_AC", "AC", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"T", "T", null, "200", "business.blap.NSAL0820.NSAL0820_TCMsgArray", null, null},
	
	{"P", "P", null, "200", "business.blap.NSAL0820.NSAL0820_PCMsgArray", null, null},
	
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
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_H
        {"XX_EXST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg
        {"CONTR_INTFC_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrIntfcSrcTpCd_SS
        {"CONTR_INTFC_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrIntfcSrcTpCd_SC
        {"CONTR_INTFC_SRC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrIntfcSrcTpDescTxt_SC
        {"DS_CONTR_INTFC_BAT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrIntfcBatNum_S
        {"DS_CONTR_SRC_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrSrcRefNum_S
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_S
        {"XX_ERR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxErrFlg_S
        {"CONTR_INTFC_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrIntfcSrcTpCd_S2
        {"DS_CONTR_INTFC_BAT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrIntfcBatNum_S2
        {"DS_CONTR_SRC_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrSrcRefNum_S2
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_S2
        {"XX_ERR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxErrFlg_S2
		null,	//A
        {"CONTR_INTFC_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrIntfcSrcTpCd_AC
        {"CONTR_INTFC_SRC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrIntfcSrcTpDescTxt_AC
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
		null,	//T
		null,	//P
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

