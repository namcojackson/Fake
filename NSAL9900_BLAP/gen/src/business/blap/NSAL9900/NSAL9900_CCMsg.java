//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190424094639000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL9900_CCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL9900;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL9900 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL9900_CCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PHYS_MAINT_TRGT_TBL_NM*/
	public final EZDCStringItem              physMaintTrgtTblNm;

    /** PHYS_MAINT_TRGT_COL_NM*/
	public final EZDCStringItem              physMaintTrgtColNm;

    /** LOGIC_MAINT_TRGT_COL_NM*/
	public final EZDCStringItem              logicMaintTrgtColNm;

    /** UNIQ_KEY_FLG*/
	public final EZDCStringItem              uniqKeyFlg;

    /** COL_TP_CD*/
	public final EZDCStringItem              colTpCd;

    /** ITEM_MAX_LG*/
	public final EZDCBigDecimalItem              itemMaxLg;

    /** ITEM_DPLY_LG*/
	public final EZDCBigDecimalItem              itemDplyLg;

    /** ITEM_SCL_NUM*/
	public final EZDCBigDecimalItem              itemSclNum;

    /** DPLY_CTRL_CD*/
	public final EZDCStringItem              dplyCtrlCd;

    /** DPLY_SORT_NUM*/
	public final EZDCBigDecimalItem              dplySortNum;

    /** TO_UPPER_FLG*/
	public final EZDCStringItem              toUpperFlg;

    /** COMMA_SEPT_FLG*/
	public final EZDCStringItem              commaSeptFlg;

    /** DEF_VAL_TXT*/
	public final EZDCStringItem              defValTxt;

    /** SRCH_ITEM_FLG*/
	public final EZDCStringItem              srchItemFlg;

    /** LIKE_SRCH_FLG*/
	public final EZDCStringItem              likeSrchFlg;

    /** ITEM_SORT_NUM*/
	public final EZDCBigDecimalItem              itemSortNum;

    /** SORT_TP_CD*/
	public final EZDCStringItem              sortTpCd;

    /** PHYS_RELN_TBL_NM*/
	public final EZDCStringItem              physRelnTblNm;

    /** PHYS_RELN_COL_CD*/
	public final EZDCStringItem              physRelnColCd;

    /** PHYS_RELN_COL_NM*/
	public final EZDCStringItem              physRelnColNm;

    /** PHYS_SRC_COL_CD*/
	public final EZDCStringItem              physSrcColCd;

    /** ITEM_INAC_FLG*/
	public final EZDCStringItem              itemInacFlg;

    /** MND_FLG*/
	public final EZDCStringItem              mndFlg;

    /** LOGIC_POPUP_COL_NM*/
	public final EZDCStringItem              logicPopupColNm;

    /** PHYS_POPUP_COL_NM*/
	public final EZDCStringItem              physPopupColNm;

    /** SEARCH_ITEM_NM*/
	public final EZDCStringItem              searchItemNm;

    /** DETAIL_ITEM_NM*/
	public final EZDCStringItem              detailItemNm;

    /** XX_WIDTH*/
	public final EZDCBigDecimalItem              xxWidth;


	/**
	 * NSAL9900_CCMsg is constructor.
	 * The initialization when the instance of NSAL9900_CCMsg is generated.
	 */
	public NSAL9900_CCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL9900_CCMsg is constructor.
	 * The initialization when the instance of NSAL9900_CCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL9900_CCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		physMaintTrgtTblNm = (EZDCStringItem)newItem("physMaintTrgtTblNm");
		physMaintTrgtColNm = (EZDCStringItem)newItem("physMaintTrgtColNm");
		logicMaintTrgtColNm = (EZDCStringItem)newItem("logicMaintTrgtColNm");
		uniqKeyFlg = (EZDCStringItem)newItem("uniqKeyFlg");
		colTpCd = (EZDCStringItem)newItem("colTpCd");
		itemMaxLg = (EZDCBigDecimalItem)newItem("itemMaxLg");
		itemDplyLg = (EZDCBigDecimalItem)newItem("itemDplyLg");
		itemSclNum = (EZDCBigDecimalItem)newItem("itemSclNum");
		dplyCtrlCd = (EZDCStringItem)newItem("dplyCtrlCd");
		dplySortNum = (EZDCBigDecimalItem)newItem("dplySortNum");
		toUpperFlg = (EZDCStringItem)newItem("toUpperFlg");
		commaSeptFlg = (EZDCStringItem)newItem("commaSeptFlg");
		defValTxt = (EZDCStringItem)newItem("defValTxt");
		srchItemFlg = (EZDCStringItem)newItem("srchItemFlg");
		likeSrchFlg = (EZDCStringItem)newItem("likeSrchFlg");
		itemSortNum = (EZDCBigDecimalItem)newItem("itemSortNum");
		sortTpCd = (EZDCStringItem)newItem("sortTpCd");
		physRelnTblNm = (EZDCStringItem)newItem("physRelnTblNm");
		physRelnColCd = (EZDCStringItem)newItem("physRelnColCd");
		physRelnColNm = (EZDCStringItem)newItem("physRelnColNm");
		physSrcColCd = (EZDCStringItem)newItem("physSrcColCd");
		itemInacFlg = (EZDCStringItem)newItem("itemInacFlg");
		mndFlg = (EZDCStringItem)newItem("mndFlg");
		logicPopupColNm = (EZDCStringItem)newItem("logicPopupColNm");
		physPopupColNm = (EZDCStringItem)newItem("physPopupColNm");
		searchItemNm = (EZDCStringItem)newItem("searchItemNm");
		detailItemNm = (EZDCStringItem)newItem("detailItemNm");
		xxWidth = (EZDCBigDecimalItem)newItem("xxWidth");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL9900_CCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL9900_CCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"physMaintTrgtTblNm", "physMaintTrgtTblNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"physMaintTrgtColNm", "physMaintTrgtColNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"logicMaintTrgtColNm", "logicMaintTrgtColNm", null, null, TYPE_HANKAKUEISU, "100", null},
	{"uniqKeyFlg", "uniqKeyFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"colTpCd", "colTpCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"itemMaxLg", "itemMaxLg", null, null, TYPE_SEISU_SYOSU, "9", "0"},
	{"itemDplyLg", "itemDplyLg", null, null, TYPE_SEISU_SYOSU, "9", "0"},
	{"itemSclNum", "itemSclNum", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"dplyCtrlCd", "dplyCtrlCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dplySortNum", "dplySortNum", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"toUpperFlg", "toUpperFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"commaSeptFlg", "commaSeptFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"defValTxt", "defValTxt", null, null, TYPE_HANKAKUEISU, "100", null},
	{"srchItemFlg", "srchItemFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"likeSrchFlg", "likeSrchFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"itemSortNum", "itemSortNum", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"sortTpCd", "sortTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"physRelnTblNm", "physRelnTblNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"physRelnColCd", "physRelnColCd", null, null, TYPE_HANKAKUEISU, "30", null},
	{"physRelnColNm", "physRelnColNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"physSrcColCd", "physSrcColCd", null, null, TYPE_HANKAKUEISU, "30", null},
	{"itemInacFlg", "itemInacFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"mndFlg", "mndFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"logicPopupColNm", "logicPopupColNm", null, null, TYPE_HANKAKUEISU, "100", null},
	{"physPopupColNm", "physPopupColNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"searchItemNm", "searchItemNm", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"detailItemNm", "detailItemNm", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxWidth", "xxWidth", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PHYS_MAINT_TRGT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physMaintTrgtTblNm
        {"PHYS_MAINT_TRGT_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physMaintTrgtColNm
        {"LOGIC_MAINT_TRGT_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//logicMaintTrgtColNm
        {"UNIQ_KEY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uniqKeyFlg
        {"COL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//colTpCd
        {"ITEM_MAX_LG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itemMaxLg
        {"ITEM_DPLY_LG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itemDplyLg
        {"ITEM_SCL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itemSclNum
        {"DPLY_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyCtrlCd
        {"DPLY_SORT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplySortNum
        {"TO_UPPER_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//toUpperFlg
        {"COMMA_SEPT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//commaSeptFlg
        {"DEF_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//defValTxt
        {"SRCH_ITEM_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchItemFlg
        {"LIKE_SRCH_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//likeSrchFlg
        {"ITEM_SORT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itemSortNum
        {"SORT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sortTpCd
        {"PHYS_RELN_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physRelnTblNm
        {"PHYS_RELN_COL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physRelnColCd
        {"PHYS_RELN_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physRelnColNm
        {"PHYS_SRC_COL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physSrcColCd
        {"ITEM_INAC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itemInacFlg
        {"MND_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mndFlg
        {"LOGIC_POPUP_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//logicPopupColNm
        {"PHYS_POPUP_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physPopupColNm
        {"SEARCH_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//searchItemNm
        {"DETAIL_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//detailItemNm
        {"XX_WIDTH",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWidth
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

