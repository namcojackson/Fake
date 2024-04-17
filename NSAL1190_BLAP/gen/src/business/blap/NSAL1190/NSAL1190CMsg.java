//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180205111948000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1190CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1190;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1190 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1190CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDCDateItem              slsDt;

    /** MTR_LB_NM_H*/
	public final EZDCStringItem              mtrLbNm_H;

    /** MTR_LB_DESC_TXT_H*/
	public final EZDCStringItem              mtrLbDescTxt_H;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDCStringItem              xxComnColOrdTxt;

    /** XX_WRN_SKIP_FLG*/
	public final EZDCStringItem              xxWrnSkipFlg;

    /** XX_NUM_EV*/
	public final EZDCBigDecimalItem              xxNum_EV;

    /** XX_POP_PRM_0*/
	public final EZDCStringItem              xxPopPrm_0;

    /** XX_POP_PRM_1*/
	public final EZDCStringItem              xxPopPrm_1;

    /** XX_POP_PRM_2*/
	public final EZDCStringItem              xxPopPrm_2;

    /** XX_POP_PRM_3*/
	public final EZDCStringItem              xxPopPrm_3;

    /** XX_POP_PRM_4*/
	public final EZDCStringItem              xxPopPrm_4;

    /** XX_POP_PRM_5*/
	public final EZDCStringItem              xxPopPrm_5;

    /** XX_POP_PRM_6*/
	public final EZDCStringItem              xxPopPrm_6;

    /** XX_POP_PRM_7*/
	public final EZDCStringItem              xxPopPrm_7;

    /** XX_POP_PRM_8*/
	public final EZDCStringItem              xxPopPrm_8;

    /** XX_POP_PRM_9*/
	public final EZDCStringItem              xxPopPrm_9;

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

    /** MTR_LB_TP_CD_01*/
	public final EZDCStringItemArray              mtrLbTpCd_01;

    /** MTR_LB_TP_DESC_TXT_01*/
	public final EZDCStringItemArray              mtrLbTpDescTxt_01;

    /** MTR_IDX_CD_01*/
	public final EZDCStringItemArray              mtrIdxCd_01;

    /** MTR_IDX_DESC_TXT_01*/
	public final EZDCStringItemArray              mtrIdxDescTxt_01;

    /** XX_DPLY_BY_CTRL_ITEM_CD_01*/
	public final EZDCStringItemArray              xxDplyByCtrlItemCd_01;

    /** XX_DPLY_BY_CTRL_ITEM_CD_NM_01*/
	public final EZDCStringItemArray              xxDplyByCtrlItemCdNm_01;

    /** A*/
	public final business.blap.NSAL1190.NSAL1190_ACMsgArray              A;


	/**
	 * NSAL1190CMsg is constructor.
	 * The initialization when the instance of NSAL1190CMsg is generated.
	 */
	public NSAL1190CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1190CMsg is constructor.
	 * The initialization when the instance of NSAL1190CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1190CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		slsDt = (EZDCDateItem)newItem("slsDt");
		mtrLbNm_H = (EZDCStringItem)newItem("mtrLbNm_H");
		mtrLbDescTxt_H = (EZDCStringItem)newItem("mtrLbDescTxt_H");
		xxComnColOrdTxt = (EZDCStringItem)newItem("xxComnColOrdTxt");
		xxWrnSkipFlg = (EZDCStringItem)newItem("xxWrnSkipFlg");
		xxNum_EV = (EZDCBigDecimalItem)newItem("xxNum_EV");
		xxPopPrm_0 = (EZDCStringItem)newItem("xxPopPrm_0");
		xxPopPrm_1 = (EZDCStringItem)newItem("xxPopPrm_1");
		xxPopPrm_2 = (EZDCStringItem)newItem("xxPopPrm_2");
		xxPopPrm_3 = (EZDCStringItem)newItem("xxPopPrm_3");
		xxPopPrm_4 = (EZDCStringItem)newItem("xxPopPrm_4");
		xxPopPrm_5 = (EZDCStringItem)newItem("xxPopPrm_5");
		xxPopPrm_6 = (EZDCStringItem)newItem("xxPopPrm_6");
		xxPopPrm_7 = (EZDCStringItem)newItem("xxPopPrm_7");
		xxPopPrm_8 = (EZDCStringItem)newItem("xxPopPrm_8");
		xxPopPrm_9 = (EZDCStringItem)newItem("xxPopPrm_9");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDCBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDCBigDecimalItem)newItem("xxPageShowTotNum");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		mtrLbTpCd_01 = (EZDCStringItemArray)newItemArray("mtrLbTpCd_01");
		mtrLbTpDescTxt_01 = (EZDCStringItemArray)newItemArray("mtrLbTpDescTxt_01");
		mtrIdxCd_01 = (EZDCStringItemArray)newItemArray("mtrIdxCd_01");
		mtrIdxDescTxt_01 = (EZDCStringItemArray)newItemArray("mtrIdxDescTxt_01");
		xxDplyByCtrlItemCd_01 = (EZDCStringItemArray)newItemArray("xxDplyByCtrlItemCd_01");
		xxDplyByCtrlItemCdNm_01 = (EZDCStringItemArray)newItemArray("xxDplyByCtrlItemCdNm_01");
		A = (business.blap.NSAL1190.NSAL1190_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1190CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1190CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"mtrLbNm_H", "mtrLbNm_H", "H", null, TYPE_HANKAKUEISU, "30", null},
	{"mtrLbDescTxt_H", "mtrLbDescTxt_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxWrnSkipFlg", "xxWrnSkipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxNum_EV", "xxNum_EV", "EV", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPopPrm_0", "xxPopPrm_0", "0", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_1", "xxPopPrm_1", "1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_2", "xxPopPrm_2", "2", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_3", "xxPopPrm_3", "3", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_4", "xxPopPrm_4", "4", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_5", "xxPopPrm_5", "5", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_6", "xxPopPrm_6", "6", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_7", "xxPopPrm_7", "7", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_8", "xxPopPrm_8", "8", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_9", "xxPopPrm_9", "9", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"mtrLbTpCd_01", "mtrLbTpCd_01", "01", "99", TYPE_HANKAKUEISU, "2", null},
	{"mtrLbTpDescTxt_01", "mtrLbTpDescTxt_01", "01", "99", TYPE_HANKAKUEISU, "50", null},
	{"mtrIdxCd_01", "mtrIdxCd_01", "01", "99", TYPE_HANKAKUEISU, "2", null},
	{"mtrIdxDescTxt_01", "mtrIdxDescTxt_01", "01", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxDplyByCtrlItemCd_01", "xxDplyByCtrlItemCd_01", "01", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxDplyByCtrlItemCdNm_01", "xxDplyByCtrlItemCdNm_01", "01", "99", TYPE_HANKAKUEISU, "50", null},
	{"A", "A", null, "50", "business.blap.NSAL1190.NSAL1190_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"MTR_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm_H
        {"MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_H
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_WRN_SKIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWrnSkipFlg
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_EV
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_0
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_2
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_3
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_4
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_5
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_6
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_7
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_8
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_9
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"MTR_LB_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbTpCd_01
        {"MTR_LB_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbTpDescTxt_01
        {"MTR_IDX_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrIdxCd_01
        {"MTR_IDX_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrIdxDescTxt_01
        {"XX_DPLY_BY_CTRL_ITEM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByCtrlItemCd_01
        {"XX_DPLY_BY_CTRL_ITEM_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByCtrlItemCdNm_01
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
