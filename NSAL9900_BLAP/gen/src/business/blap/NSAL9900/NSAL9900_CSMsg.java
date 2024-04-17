//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20190424094642000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL9900_CSMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSAL9900 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL9900_CSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PHYS_MAINT_TRGT_TBL_NM*/
	public final EZDSStringItem              physMaintTrgtTblNm;

    /** PHYS_MAINT_TRGT_COL_NM*/
	public final EZDSStringItem              physMaintTrgtColNm;

    /** LOGIC_MAINT_TRGT_COL_NM*/
	public final EZDSStringItem              logicMaintTrgtColNm;

    /** UNIQ_KEY_FLG*/
	public final EZDSStringItem              uniqKeyFlg;

    /** COL_TP_CD*/
	public final EZDSStringItem              colTpCd;

    /** ITEM_MAX_LG*/
	public final EZDSBigDecimalItem              itemMaxLg;

    /** ITEM_DPLY_LG*/
	public final EZDSBigDecimalItem              itemDplyLg;

    /** ITEM_SCL_NUM*/
	public final EZDSBigDecimalItem              itemSclNum;

    /** DPLY_CTRL_CD*/
	public final EZDSStringItem              dplyCtrlCd;

    /** DPLY_SORT_NUM*/
	public final EZDSBigDecimalItem              dplySortNum;

    /** TO_UPPER_FLG*/
	public final EZDSStringItem              toUpperFlg;

    /** COMMA_SEPT_FLG*/
	public final EZDSStringItem              commaSeptFlg;

    /** DEF_VAL_TXT*/
	public final EZDSStringItem              defValTxt;

    /** SRCH_ITEM_FLG*/
	public final EZDSStringItem              srchItemFlg;

    /** LIKE_SRCH_FLG*/
	public final EZDSStringItem              likeSrchFlg;

    /** ITEM_SORT_NUM*/
	public final EZDSBigDecimalItem              itemSortNum;

    /** SORT_TP_CD*/
	public final EZDSStringItem              sortTpCd;

    /** PHYS_RELN_TBL_NM*/
	public final EZDSStringItem              physRelnTblNm;

    /** PHYS_RELN_COL_CD*/
	public final EZDSStringItem              physRelnColCd;

    /** PHYS_RELN_COL_NM*/
	public final EZDSStringItem              physRelnColNm;

    /** PHYS_SRC_COL_CD*/
	public final EZDSStringItem              physSrcColCd;

    /** ITEM_INAC_FLG*/
	public final EZDSStringItem              itemInacFlg;

    /** MND_FLG*/
	public final EZDSStringItem              mndFlg;

    /** LOGIC_POPUP_COL_NM*/
	public final EZDSStringItem              logicPopupColNm;

    /** PHYS_POPUP_COL_NM*/
	public final EZDSStringItem              physPopupColNm;

    /** SEARCH_ITEM_NM*/
	public final EZDSStringItem              searchItemNm;

    /** DETAIL_ITEM_NM*/
	public final EZDSStringItem              detailItemNm;

    /** XX_WIDTH*/
	public final EZDSBigDecimalItem              xxWidth;


	/**
	 * NSAL9900_CSMsg is constructor.
	 * The initialization when the instance of NSAL9900_CSMsg is generated.
	 */
	public NSAL9900_CSMsg() {
		this(false, -1);
	}

	/**
	 * NSAL9900_CSMsg is constructor.
	 * The initialization when the instance of NSAL9900_CSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL9900_CSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		physMaintTrgtTblNm = (EZDSStringItem)newItem("physMaintTrgtTblNm");
		physMaintTrgtColNm = (EZDSStringItem)newItem("physMaintTrgtColNm");
		logicMaintTrgtColNm = (EZDSStringItem)newItem("logicMaintTrgtColNm");
		uniqKeyFlg = (EZDSStringItem)newItem("uniqKeyFlg");
		colTpCd = (EZDSStringItem)newItem("colTpCd");
		itemMaxLg = (EZDSBigDecimalItem)newItem("itemMaxLg");
		itemDplyLg = (EZDSBigDecimalItem)newItem("itemDplyLg");
		itemSclNum = (EZDSBigDecimalItem)newItem("itemSclNum");
		dplyCtrlCd = (EZDSStringItem)newItem("dplyCtrlCd");
		dplySortNum = (EZDSBigDecimalItem)newItem("dplySortNum");
		toUpperFlg = (EZDSStringItem)newItem("toUpperFlg");
		commaSeptFlg = (EZDSStringItem)newItem("commaSeptFlg");
		defValTxt = (EZDSStringItem)newItem("defValTxt");
		srchItemFlg = (EZDSStringItem)newItem("srchItemFlg");
		likeSrchFlg = (EZDSStringItem)newItem("likeSrchFlg");
		itemSortNum = (EZDSBigDecimalItem)newItem("itemSortNum");
		sortTpCd = (EZDSStringItem)newItem("sortTpCd");
		physRelnTblNm = (EZDSStringItem)newItem("physRelnTblNm");
		physRelnColCd = (EZDSStringItem)newItem("physRelnColCd");
		physRelnColNm = (EZDSStringItem)newItem("physRelnColNm");
		physSrcColCd = (EZDSStringItem)newItem("physSrcColCd");
		itemInacFlg = (EZDSStringItem)newItem("itemInacFlg");
		mndFlg = (EZDSStringItem)newItem("mndFlg");
		logicPopupColNm = (EZDSStringItem)newItem("logicPopupColNm");
		physPopupColNm = (EZDSStringItem)newItem("physPopupColNm");
		searchItemNm = (EZDSStringItem)newItem("searchItemNm");
		detailItemNm = (EZDSStringItem)newItem("detailItemNm");
		xxWidth = (EZDSBigDecimalItem)newItem("xxWidth");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL9900_CSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL9900_CSMsgArray();
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

