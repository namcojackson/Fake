//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160727130940000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWWL0040SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWWL0040;

import parts.common.EZDMsgArray;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSDateItem;
import parts.common.EZDSMimeSourceItem;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.common.EZDSStringItemArray;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWWL0040 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWWL0040SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** NTFY_DIST_LIST_NM*/
	public final EZDSStringItem              ntfyDistListNm;

    /** NTFY_DIST_LIST_DESC_TXT*/
	public final EZDSStringItem              ntfyDistListDescTxt;

    /** NTFY_BIZ_AREA_TP_CD_D*/
	public final EZDSStringItem              ntfyBizAreaTpCd_D;

    /** NTFY_SUB_AREA_TP_CD_D*/
	public final EZDSStringItem              ntfySubAreaTpCd_D;

    /** NTFY_SUB_AREA_TP_CD_DP*/
	public final EZDSStringItemArray              ntfySubAreaTpCd_DP;

    /** NTFY_SUB_AREA_TP_DESC_TXT_DP*/
	public final EZDSStringItemArray              ntfySubAreaTpDescTxt_DP;

    /** EFF_FROM_DT_D*/
	public final EZDSDateItem              effFromDt_D;

    /** EFF_THRU_DT_D*/
	public final EZDSDateItem              effThruDt_D;

    /** NTFY_DIST_LIST_ACTV_FLG*/
	public final EZDSStringItem              ntfyDistListActvFlg;

    /** NTFY_HDR_NM*/
	public final EZDSStringItem              ntfyHdrNm;

    /** NTFY_HDR_DESC_TXT*/
	public final EZDSStringItem              ntfyHdrDescTxt;

    /** NTFY_BIZ_AREA_TP_CD_N*/
	public final EZDSStringItem              ntfyBizAreaTpCd_N;

    /** NTFY_SUB_AREA_TP_CD_N*/
	public final EZDSStringItem              ntfySubAreaTpCd_N;

    /** NTFY_SUB_AREA_TP_CD_NP*/
	public final EZDSStringItemArray              ntfySubAreaTpCd_NP;

    /** NTFY_SUB_AREA_TP_DESC_TXT_NP*/
	public final EZDSStringItemArray              ntfySubAreaTpDescTxt_NP;

    /** EFF_FROM_DT_N*/
	public final EZDSDateItem              effFromDt_N;

    /** EFF_THRU_DT_N*/
	public final EZDSDateItem              effThruDt_N;

    /** NTFY_HDR_ACTV_FLG*/
	public final EZDSStringItem              ntfyHdrActvFlg;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDSBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDSBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDSBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDSBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDSBigDecimalItem              xxPageShowTotNum;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;

    /** XX_SORT_TBL_NM*/
	public final EZDSStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDSStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDSStringItem              xxSortOrdByTxt;

    /** XX_FILE_DATA_A*/
	public final EZDSMimeSourceItem              xxFileData_A;

    /** A*/
	public final business.blap.NWWL0040.NWWL0040_ASMsgArray              A;


	/**
	 * NWWL0040SMsg is constructor.
	 * The initialization when the instance of NWWL0040SMsg is generated.
	 */
	public NWWL0040SMsg() {
		this(false, -1);
	}

	/**
	 * NWWL0040SMsg is constructor.
	 * The initialization when the instance of NWWL0040SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWWL0040SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ntfyDistListNm = (EZDSStringItem)newItem("ntfyDistListNm");
		ntfyDistListDescTxt = (EZDSStringItem)newItem("ntfyDistListDescTxt");
		ntfyBizAreaTpCd_D = (EZDSStringItem)newItem("ntfyBizAreaTpCd_D");
		ntfySubAreaTpCd_D = (EZDSStringItem)newItem("ntfySubAreaTpCd_D");
		ntfySubAreaTpCd_DP = (EZDSStringItemArray)newItemArray("ntfySubAreaTpCd_DP");
		ntfySubAreaTpDescTxt_DP = (EZDSStringItemArray)newItemArray("ntfySubAreaTpDescTxt_DP");
		effFromDt_D = (EZDSDateItem)newItem("effFromDt_D");
		effThruDt_D = (EZDSDateItem)newItem("effThruDt_D");
		ntfyDistListActvFlg = (EZDSStringItem)newItem("ntfyDistListActvFlg");
		ntfyHdrNm = (EZDSStringItem)newItem("ntfyHdrNm");
		ntfyHdrDescTxt = (EZDSStringItem)newItem("ntfyHdrDescTxt");
		ntfyBizAreaTpCd_N = (EZDSStringItem)newItem("ntfyBizAreaTpCd_N");
		ntfySubAreaTpCd_N = (EZDSStringItem)newItem("ntfySubAreaTpCd_N");
		ntfySubAreaTpCd_NP = (EZDSStringItemArray)newItemArray("ntfySubAreaTpCd_NP");
		ntfySubAreaTpDescTxt_NP = (EZDSStringItemArray)newItemArray("ntfySubAreaTpDescTxt_NP");
		effFromDt_N = (EZDSDateItem)newItem("effFromDt_N");
		effThruDt_N = (EZDSDateItem)newItem("effThruDt_N");
		ntfyHdrActvFlg = (EZDSStringItem)newItem("ntfyHdrActvFlg");
		xxPageShowFromNum = (EZDSBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDSBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDSBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDSBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDSBigDecimalItem)newItem("xxPageShowTotNum");
		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
		xxSortTblNm = (EZDSStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDSStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDSStringItem)newItem("xxSortOrdByTxt");
		xxFileData_A = (EZDSMimeSourceItem)newItem("xxFileData_A");
		A = (business.blap.NWWL0040.NWWL0040_ASMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWWL0040SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWWL0040SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ntfyDistListNm", "ntfyDistListNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"ntfyDistListDescTxt", "ntfyDistListDescTxt", null, null, TYPE_HANKAKUEISU, "240", null},
	{"ntfyBizAreaTpCd_D", "ntfyBizAreaTpCd_D", "D", null, TYPE_HANKAKUEISU, "2", null},
	{"ntfySubAreaTpCd_D", "ntfySubAreaTpCd_D", "D", null, TYPE_HANKAKUEISU, "2", null},
	{"ntfySubAreaTpCd_DP", "ntfySubAreaTpCd_DP", "DP", "99", TYPE_HANKAKUEISU, "2", null},
	{"ntfySubAreaTpDescTxt_DP", "ntfySubAreaTpDescTxt_DP", "DP", "99", TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_D", "effFromDt_D", "D", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_D", "effThruDt_D", "D", null, TYPE_NENTSUKIHI, "8", null},
	{"ntfyDistListActvFlg", "ntfyDistListActvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"ntfyHdrNm", "ntfyHdrNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"ntfyHdrDescTxt", "ntfyHdrDescTxt", null, null, TYPE_HANKAKUEISU, "240", null},
	{"ntfyBizAreaTpCd_N", "ntfyBizAreaTpCd_N", "N", null, TYPE_HANKAKUEISU, "2", null},
	{"ntfySubAreaTpCd_N", "ntfySubAreaTpCd_N", "N", null, TYPE_HANKAKUEISU, "2", null},
	{"ntfySubAreaTpCd_NP", "ntfySubAreaTpCd_NP", "NP", "99", TYPE_HANKAKUEISU, "2", null},
	{"ntfySubAreaTpDescTxt_NP", "ntfySubAreaTpDescTxt_NP", "NP", "99", TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_N", "effFromDt_N", "N", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_N", "effThruDt_N", "N", null, TYPE_NENTSUKIHI, "8", null},
	{"ntfyHdrActvFlg", "ntfyHdrActvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxFileData_A", "xxFileData_A", "A", null, TYPE_UPLOAD, null, null},
	{"A", "A", null, "200", "business.blap.NWWL0040.NWWL0040_ASMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"NTFY_DIST_LIST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyDistListNm
        {"NTFY_DIST_LIST_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyDistListDescTxt
        {"NTFY_BIZ_AREA_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyBizAreaTpCd_D
        {"NTFY_SUB_AREA_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySubAreaTpCd_D
        {"NTFY_SUB_AREA_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySubAreaTpCd_DP
        {"NTFY_SUB_AREA_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySubAreaTpDescTxt_DP
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_D
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_D
        {"NTFY_DIST_LIST_ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyDistListActvFlg
        {"NTFY_HDR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrNm
        {"NTFY_HDR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrDescTxt
        {"NTFY_BIZ_AREA_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyBizAreaTpCd_N
        {"NTFY_SUB_AREA_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySubAreaTpCd_N
        {"NTFY_SUB_AREA_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySubAreaTpCd_NP
        {"NTFY_SUB_AREA_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySubAreaTpDescTxt_NP
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_N
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_N
        {"NTFY_HDR_ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrActvFlg
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_A
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

