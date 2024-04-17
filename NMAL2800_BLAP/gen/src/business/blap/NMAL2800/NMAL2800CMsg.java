//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180328155318000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2800CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2800;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2800 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2800CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_FILE_DATA_U*/
	public final EZDCMimeSourceItem              xxFileData_U;

    /** XX_FILE_DATA_D*/
	public final EZDCMimeSourceItem              xxFileData_D;

    /** SRCH_OPT_PK*/
	public final EZDCBigDecimalItem              srchOptPk;

    /** SRCH_OPT_PK_L*/
	public final EZDCBigDecimalItemArray              srchOptPk_L;

    /** SRCH_OPT_NM_L*/
	public final EZDCStringItemArray              srchOptNm_L;

    /** SRCH_OPT_NM*/
	public final EZDCStringItem              srchOptNm;

    /** XX_TP_CD_H1*/
	public final EZDCStringItem              xxTpCd_H1;

    /** XX_TP_CD_L*/
	public final EZDCStringItemArray              xxTpCd_L;

    /** XX_TP_NM_L*/
	public final EZDCStringItemArray              xxTpNm_L;

    /** LINE_BIZ_TP_CD_H1*/
	public final EZDCStringItem              lineBizTpCd_H1;

    /** LINE_BIZ_TP_CD_L*/
	public final EZDCStringItemArray              lineBizTpCd_L;

    /** LINE_BIZ_TP_DESC_TXT_L*/
	public final EZDCStringItemArray              lineBizTpDescTxt_L;

    /** XX_DT_10_DT_H1*/
	public final EZDCDateItem              xxDt10Dt_H1;

    /** XX_DT_10_DT_H2*/
	public final EZDCDateItem              xxDt10Dt_H2;

    /** BEF_DS_ACCT_NM_H1*/
	public final EZDCStringItem              befDsAcctNm_H1;

    /** RVW_PROS_NUM_H1*/
	public final EZDCStringItem              rvwProsNum_H1;

    /** XX_SCR_ITEM_61_TXT_H1*/
	public final EZDCStringItem              xxScrItem61Txt_H1;

    /** BEF_PSN_NUM_H1*/
	public final EZDCStringItem              befPsnNum_H1;

    /** ORG_NM_H1*/
	public final EZDCStringItem              orgNm_H1;

    /** PROS_RVW_STS_CD_H1*/
	public final EZDCStringItem              prosRvwStsCd_H1;

    /** PROS_RVW_STS_CD_L*/
	public final EZDCStringItemArray              prosRvwStsCd_L;

    /** PROS_RVW_STS_DESC_TXT_L*/
	public final EZDCStringItemArray              prosRvwStsDescTxt_L;

    /** PROS_RVW_STS_CD_L2*/
	public final EZDCStringItemArray              prosRvwStsCd_L2;

    /** PROS_RVW_STS_NM_L2*/
	public final EZDCStringItemArray              prosRvwStsNm_L2;

    /** DUP_ACCT_LOC_FLG_H1*/
	public final EZDCStringItem              dupAcctLocFlg_H1;

    /** XX_YES_NO_CD_L*/
	public final EZDCStringItemArray              xxYesNoCd_L;

    /** XX_YES_NO_NM_L*/
	public final EZDCStringItemArray              xxYesNoNm_L;

    /** SHIP_ADDR_ALL_TXT_H1*/
	public final EZDCStringItem              shipAddrAllTxt_H1;

    /** BEF_SHIP_TO_CTY_ADDR_H1*/
	public final EZDCStringItem              befShipToCtyAddr_H1;

    /** BEF_SHIP_TO_ST_NM_H1*/
	public final EZDCStringItem              befShipToStNm_H1;

    /** ST_CD_L*/
	public final EZDCStringItemArray              stCd_L;

    /** ST_DESC_TXT_L*/
	public final EZDCStringItemArray              stDescTxt_L;

    /** BEF_SHIP_TO_POST_CD_H1*/
	public final EZDCStringItem              befShipToPostCd_H1;

    /** CTRY_CD_L*/
	public final EZDCStringItemArray              ctryCd_L;

    /** CTRY_DESC_TXT_L*/
	public final EZDCStringItemArray              ctryDescTxt_L;

    /** XX_CELL_IDX_H1*/
	public final EZDCBigDecimalItem              xxCellIdx_H1;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

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
	public final business.blap.NMAL2800.NMAL2800_ACMsgArray              A;

    /** XX_ROW_NUM*/
	public final EZDCBigDecimalItem              xxRowNum;

    /** XX_MODE_NM_23_TXT*/
	public final EZDCStringItem              xxModeNm23Txt;

    /** BIZ_ID*/
	public final EZDCStringItem              bizId;

    /** XX_SCR_ITEM_61_TXT*/
	public final EZDCStringItem              xxScrItem61Txt;

    /** C*/
	public final business.blap.NMAL2800.NMAL2800_CCMsgArray              C;


	/**
	 * NMAL2800CMsg is constructor.
	 * The initialization when the instance of NMAL2800CMsg is generated.
	 */
	public NMAL2800CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2800CMsg is constructor.
	 * The initialization when the instance of NMAL2800CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2800CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxFileData_U = (EZDCMimeSourceItem)newItem("xxFileData_U");
		xxFileData_D = (EZDCMimeSourceItem)newItem("xxFileData_D");
		srchOptPk = (EZDCBigDecimalItem)newItem("srchOptPk");
		srchOptPk_L = (EZDCBigDecimalItemArray)newItemArray("srchOptPk_L");
		srchOptNm_L = (EZDCStringItemArray)newItemArray("srchOptNm_L");
		srchOptNm = (EZDCStringItem)newItem("srchOptNm");
		xxTpCd_H1 = (EZDCStringItem)newItem("xxTpCd_H1");
		xxTpCd_L = (EZDCStringItemArray)newItemArray("xxTpCd_L");
		xxTpNm_L = (EZDCStringItemArray)newItemArray("xxTpNm_L");
		lineBizTpCd_H1 = (EZDCStringItem)newItem("lineBizTpCd_H1");
		lineBizTpCd_L = (EZDCStringItemArray)newItemArray("lineBizTpCd_L");
		lineBizTpDescTxt_L = (EZDCStringItemArray)newItemArray("lineBizTpDescTxt_L");
		xxDt10Dt_H1 = (EZDCDateItem)newItem("xxDt10Dt_H1");
		xxDt10Dt_H2 = (EZDCDateItem)newItem("xxDt10Dt_H2");
		befDsAcctNm_H1 = (EZDCStringItem)newItem("befDsAcctNm_H1");
		rvwProsNum_H1 = (EZDCStringItem)newItem("rvwProsNum_H1");
		xxScrItem61Txt_H1 = (EZDCStringItem)newItem("xxScrItem61Txt_H1");
		befPsnNum_H1 = (EZDCStringItem)newItem("befPsnNum_H1");
		orgNm_H1 = (EZDCStringItem)newItem("orgNm_H1");
		prosRvwStsCd_H1 = (EZDCStringItem)newItem("prosRvwStsCd_H1");
		prosRvwStsCd_L = (EZDCStringItemArray)newItemArray("prosRvwStsCd_L");
		prosRvwStsDescTxt_L = (EZDCStringItemArray)newItemArray("prosRvwStsDescTxt_L");
		prosRvwStsCd_L2 = (EZDCStringItemArray)newItemArray("prosRvwStsCd_L2");
		prosRvwStsNm_L2 = (EZDCStringItemArray)newItemArray("prosRvwStsNm_L2");
		dupAcctLocFlg_H1 = (EZDCStringItem)newItem("dupAcctLocFlg_H1");
		xxYesNoCd_L = (EZDCStringItemArray)newItemArray("xxYesNoCd_L");
		xxYesNoNm_L = (EZDCStringItemArray)newItemArray("xxYesNoNm_L");
		shipAddrAllTxt_H1 = (EZDCStringItem)newItem("shipAddrAllTxt_H1");
		befShipToCtyAddr_H1 = (EZDCStringItem)newItem("befShipToCtyAddr_H1");
		befShipToStNm_H1 = (EZDCStringItem)newItem("befShipToStNm_H1");
		stCd_L = (EZDCStringItemArray)newItemArray("stCd_L");
		stDescTxt_L = (EZDCStringItemArray)newItemArray("stDescTxt_L");
		befShipToPostCd_H1 = (EZDCStringItem)newItem("befShipToPostCd_H1");
		ctryCd_L = (EZDCStringItemArray)newItemArray("ctryCd_L");
		ctryDescTxt_L = (EZDCStringItemArray)newItemArray("ctryDescTxt_L");
		xxCellIdx_H1 = (EZDCBigDecimalItem)newItem("xxCellIdx_H1");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowCurNum = (EZDCBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDCBigDecimalItem)newItem("xxPageShowTotNum");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		A = (business.blap.NMAL2800.NMAL2800_ACMsgArray)newMsgArray("A");
		xxRowNum = (EZDCBigDecimalItem)newItem("xxRowNum");
		xxModeNm23Txt = (EZDCStringItem)newItem("xxModeNm23Txt");
		bizId = (EZDCStringItem)newItem("bizId");
		xxScrItem61Txt = (EZDCStringItem)newItem("xxScrItem61Txt");
		C = (business.blap.NMAL2800.NMAL2800_CCMsgArray)newMsgArray("C");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2800CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2800CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxFileData_U", "xxFileData_U", "U", null, TYPE_UPLOAD, null, null},
	{"xxFileData_D", "xxFileData_D", "D", null, TYPE_UPLOAD, null, null},
	{"srchOptPk", "srchOptPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptPk_L", "srchOptPk_L", "L", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_L", "srchOptNm_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"srchOptNm", "srchOptNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxTpCd_H1", "xxTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxTpCd_L", "xxTpCd_L", "L", "99", TYPE_HANKAKUEISU, "1", null},
	{"xxTpNm_L", "xxTpNm_L", "L", "99", TYPE_HANKAKUEISU, "10", null},
	{"lineBizTpCd_H1", "lineBizTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"lineBizTpCd_L", "lineBizTpCd_L", "L", "99", TYPE_HANKAKUEISU, "8", null},
	{"lineBizTpDescTxt_L", "lineBizTpDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxDt10Dt_H1", "xxDt10Dt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxDt10Dt_H2", "xxDt10Dt_H2", "H2", null, TYPE_NENTSUKIHI, "8", null},
	{"befDsAcctNm_H1", "befDsAcctNm_H1", "H1", null, TYPE_HANKAKUEISU, "360", null},
	{"rvwProsNum_H1", "rvwProsNum_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxScrItem61Txt_H1", "xxScrItem61Txt_H1", "H1", null, TYPE_HANKAKUEISU, "61", null},
	{"befPsnNum_H1", "befPsnNum_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"orgNm_H1", "orgNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"prosRvwStsCd_H1", "prosRvwStsCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"prosRvwStsCd_L", "prosRvwStsCd_L", "L", "99", TYPE_HANKAKUEISU, "2", null},
	{"prosRvwStsDescTxt_L", "prosRvwStsDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"prosRvwStsCd_L2", "prosRvwStsCd_L2", "L2", "99", TYPE_HANKAKUEISU, "2", null},
	{"prosRvwStsNm_L2", "prosRvwStsNm_L2", "L2", "99", TYPE_HANKAKUEISU, "30", null},
	{"dupAcctLocFlg_H1", "dupAcctLocFlg_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxYesNoCd_L", "xxYesNoCd_L", "L", "99", TYPE_HANKAKUEISU, "1", null},
	{"xxYesNoNm_L", "xxYesNoNm_L", "L", "99", TYPE_HANKAKUEISU, "3", null},
	{"shipAddrAllTxt_H1", "shipAddrAllTxt_H1", "H1", null, TYPE_HANKAKUEISU, "240", null},
	{"befShipToCtyAddr_H1", "befShipToCtyAddr_H1", "H1", null, TYPE_HANKAKUEISU, "60", null},
	{"befShipToStNm_H1", "befShipToStNm_H1", "H1", null, TYPE_HANKAKUEISU, "60", null},
	{"stCd_L", "stCd_L", "L", "99", TYPE_HANKAKUEISU, "2", null},
	{"stDescTxt_L", "stDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"befShipToPostCd_H1", "befShipToPostCd_H1", "H1", null, TYPE_HANKAKUEISU, "60", null},
	{"ctryCd_L", "ctryCd_L", "L", "200", TYPE_HANKAKUEISU, "3", null},
	{"ctryDescTxt_L", "ctryDescTxt_L", "L", "200", TYPE_HANKAKUEISU, "50", null},
	{"xxCellIdx_H1", "xxCellIdx_H1", "H1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "100", "business.blap.NMAL2800.NMAL2800_ACMsgArray", null, null},
	
	{"xxRowNum", "xxRowNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxModeNm23Txt", "xxModeNm23Txt", null, null, TYPE_HANKAKUEISU, "23", null},
	{"bizId", "bizId", null, null, TYPE_HANKAKUEISU, "10", null},
	{"xxScrItem61Txt", "xxScrItem61Txt", null, null, TYPE_HANKAKUEISU, "61", null},
	{"C", "C", null, "400", "business.blap.NMAL2800.NMAL2800_CCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_U
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_D
        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk
        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_L
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_L
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm
        {"XX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTpCd_H1
        {"XX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTpCd_L
        {"XX_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTpNm_L
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_H1
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_L
        {"LINE_BIZ_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpDescTxt_L
        {"XX_DT_10_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDt10Dt_H1
        {"XX_DT_10_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDt10Dt_H2
        {"BEF_DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befDsAcctNm_H1
        {"RVW_PROS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rvwProsNum_H1
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_H1
        {"BEF_PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befPsnNum_H1
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_H1
        {"PROS_RVW_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prosRvwStsCd_H1
        {"PROS_RVW_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prosRvwStsCd_L
        {"PROS_RVW_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prosRvwStsDescTxt_L
        {"PROS_RVW_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prosRvwStsCd_L2
        {"PROS_RVW_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prosRvwStsNm_L2
        {"DUP_ACCT_LOC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dupAcctLocFlg_H1
        {"XX_YES_NO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd_L
        {"XX_YES_NO_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoNm_L
        {"SHIP_ADDR_ALL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipAddrAllTxt_H1
        {"BEF_SHIP_TO_CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befShipToCtyAddr_H1
        {"BEF_SHIP_TO_ST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befShipToStNm_H1
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_L
        {"ST_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stDescTxt_L
        {"BEF_SHIP_TO_POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befShipToPostCd_H1
        {"CTRY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctryCd_L
        {"CTRY_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctryDescTxt_L
        {"XX_CELL_IDX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCellIdx_H1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
		null,	//A
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum
        {"XX_MODE_NM_23_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeNm23Txt
        {"BIZ_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizId
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt
		null,	//C
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

