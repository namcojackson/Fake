//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180720110758000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3080CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3080 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3080CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** USR_ID*/
	public final EZDCStringItem              usrId;

    /** DUP_ERR_CD*/
	public final EZDCStringItem              dupErrCd;

    /** XX_WRN_SKIP_FLG*/
	public final EZDCStringItem              xxWrnSkipFlg;

    /** XX_CELL_IDX*/
	public final EZDCBigDecimalItem              xxCellIdx;

    /** P*/
	public final business.blap.NLBL3080.NLBL3080_PCMsgArray              P;

    /** CPO_ORD_NUM_BK*/
	public final EZDCStringItem              cpoOrdNum_BK;

    /** T_MDL_NM_BK*/
	public final EZDCStringItem              t_MdlNm_BK;

    /** SVC_CONFIG_MSTR_PK_BK*/
	public final EZDCBigDecimalItem              svcConfigMstrPk_BK;

    /** RTL_WH_CD_BK*/
	public final EZDCStringItem              rtlWhCd_BK;

    /** SHIP_TO_CUST_CD_BK*/
	public final EZDCStringItem              shipToCustCd_BK;

    /** XX_DPLY_TAB_BK*/
	public final EZDCStringItem              xxDplyTab_BK;

    /** SRCH_OPT_PK_S*/
	public final EZDCBigDecimalItem              srchOptPk_S;

    /** SRCH_OPT_NM_S*/
	public final EZDCStringItem              srchOptNm_S;

    /** SRCH_OPT_PK_L*/
	public final EZDCBigDecimalItemArray              srchOptPk_L;

    /** SRCH_OPT_NM_L*/
	public final EZDCStringItemArray              srchOptNm_L;

    /** CPO_ORD_NUM*/
	public final EZDCStringItem              cpoOrdNum;

    /** DS_ORD_CATG_CD*/
	public final EZDCStringItem              dsOrdCatgCd;

    /** DS_ORD_CATG_CD_P*/
	public final EZDCStringItemArray              dsOrdCatgCd_P;

    /** DS_ORD_CATG_DESC_TXT_P*/
	public final EZDCStringItemArray              dsOrdCatgDescTxt_P;

    /** DS_ORD_TP_CD*/
	public final EZDCStringItem              dsOrdTpCd;

    /** DS_ORD_TP_CD_P*/
	public final EZDCStringItemArray              dsOrdTpCd_P;

    /** DS_ORD_TP_DESC_TXT_P*/
	public final EZDCStringItemArray              dsOrdTpDescTxt_P;

    /** SER_NUM*/
	public final EZDCStringItem              serNum;

    /** XX_LINK_ANCR_ML*/
	public final EZDCStringItem              xxLinkAncr_ML;

    /** T_MDL_NM*/
	public final EZDCStringItem              t_MdlNm;

    /** XX_LINK_ANCR_CF*/
	public final EZDCStringItem              xxLinkAncr_CF;

    /** SVC_CONFIG_MSTR_PK*/
	public final EZDCBigDecimalItem              svcConfigMstrPk;

    /** RTL_WH_CD*/
	public final EZDCStringItem              rtlWhCd;

    /** RTL_WH_NM*/
	public final EZDCStringItem              rtlWhNm;

    /** XX_LINK_ANCR_WR*/
	public final EZDCStringItem              xxLinkAncr_WR;

    /** SHIP_TO_CUST_CD*/
	public final EZDCStringItem              shipToCustCd;

    /** DS_ACCT_NM*/
	public final EZDCStringItem              dsAcctNm;

    /** XX_LINK_ANCR_SP*/
	public final EZDCStringItem              xxLinkAncr_SP;

    /** TOC_CD*/
	public final EZDCStringItem              tocCd;

    /** TOC_NM*/
	public final EZDCStringItem              tocNm;

    /** XX_LINK_ANCR_TC*/
	public final EZDCStringItem              xxLinkAncr_TC;

    /** MDSE_CD*/
	public final EZDCStringItem              mdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDCStringItem              mdseDescShortTxt;

    /** XX_LINK_ANCR_MD*/
	public final EZDCStringItem              xxLinkAncr_MD;

    /** RDD_DT_FR*/
	public final EZDCDateItem              rddDt_FR;

    /** RDD_DT_TO*/
	public final EZDCDateItem              rddDt_TO;

    /** XX_ORD_DT_FR*/
	public final EZDCDateItem              xxOrdDt_FR;

    /** XX_ORD_DT_TO*/
	public final EZDCDateItem              xxOrdDt_TO;

    /** XX_CHK_BOX_BO*/
	public final EZDCStringItem              xxChkBox_BO;

    /** XX_CHK_BOX_AL*/
	public final EZDCStringItem              xxChkBox_AL;

    /** XX_CHK_BOX_NS*/
	public final EZDCStringItem              xxChkBox_NS;

    /** XX_DPLY_TAB*/
	public final EZDCStringItem              xxDplyTab;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDCStringItem              xxComnColOrdTxt;

    /** XX_NUM_EV*/
	public final EZDCBigDecimalItem              xxNum_EV;

    /** XX_PAGE_SHOW_FROM_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_A;

    /** XX_PAGE_SHOW_TO_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowToNum_A;

    /** XX_PAGE_SHOW_OF_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowOfNum_A;

    /** XX_PAGE_SHOW_CUR_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowCurNum_A;

    /** XX_PAGE_SHOW_TOT_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowTotNum_A;

    /** XX_SORT_TBL_NM_A*/
	public final EZDCStringItem              xxSortTblNm_A;

    /** XX_SORT_ITEM_NM_A*/
	public final EZDCStringItem              xxSortItemNm_A;

    /** XX_SORT_ORD_BY_TXT_A*/
	public final EZDCStringItem              xxSortOrdByTxt_A;

    /** A*/
	public final business.blap.NLBL3080.NLBL3080_ACMsgArray              A;

    /** XX_PAGE_SHOW_FROM_NUM_B*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_B;

    /** XX_PAGE_SHOW_TO_NUM_B*/
	public final EZDCBigDecimalItem              xxPageShowToNum_B;

    /** XX_PAGE_SHOW_OF_NUM_B*/
	public final EZDCBigDecimalItem              xxPageShowOfNum_B;

    /** XX_PAGE_SHOW_CUR_NUM_B*/
	public final EZDCBigDecimalItem              xxPageShowCurNum_B;

    /** XX_PAGE_SHOW_TOT_NUM_B*/
	public final EZDCBigDecimalItem              xxPageShowTotNum_B;

    /** XX_SORT_TBL_NM_B*/
	public final EZDCStringItem              xxSortTblNm_B;

    /** XX_SORT_ITEM_NM_B*/
	public final EZDCStringItem              xxSortItemNm_B;

    /** XX_SORT_ORD_BY_TXT_B*/
	public final EZDCStringItem              xxSortOrdByTxt_B;

    /** B*/
	public final business.blap.NLBL3080.NLBL3080_BCMsgArray              B;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;


	/**
	 * NLBL3080CMsg is constructor.
	 * The initialization when the instance of NLBL3080CMsg is generated.
	 */
	public NLBL3080CMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3080CMsg is constructor.
	 * The initialization when the instance of NLBL3080CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3080CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		usrId = (EZDCStringItem)newItem("usrId");
		dupErrCd = (EZDCStringItem)newItem("dupErrCd");
		xxWrnSkipFlg = (EZDCStringItem)newItem("xxWrnSkipFlg");
		xxCellIdx = (EZDCBigDecimalItem)newItem("xxCellIdx");
		P = (business.blap.NLBL3080.NLBL3080_PCMsgArray)newMsgArray("P");
		cpoOrdNum_BK = (EZDCStringItem)newItem("cpoOrdNum_BK");
		t_MdlNm_BK = (EZDCStringItem)newItem("t_MdlNm_BK");
		svcConfigMstrPk_BK = (EZDCBigDecimalItem)newItem("svcConfigMstrPk_BK");
		rtlWhCd_BK = (EZDCStringItem)newItem("rtlWhCd_BK");
		shipToCustCd_BK = (EZDCStringItem)newItem("shipToCustCd_BK");
		xxDplyTab_BK = (EZDCStringItem)newItem("xxDplyTab_BK");
		srchOptPk_S = (EZDCBigDecimalItem)newItem("srchOptPk_S");
		srchOptNm_S = (EZDCStringItem)newItem("srchOptNm_S");
		srchOptPk_L = (EZDCBigDecimalItemArray)newItemArray("srchOptPk_L");
		srchOptNm_L = (EZDCStringItemArray)newItemArray("srchOptNm_L");
		cpoOrdNum = (EZDCStringItem)newItem("cpoOrdNum");
		dsOrdCatgCd = (EZDCStringItem)newItem("dsOrdCatgCd");
		dsOrdCatgCd_P = (EZDCStringItemArray)newItemArray("dsOrdCatgCd_P");
		dsOrdCatgDescTxt_P = (EZDCStringItemArray)newItemArray("dsOrdCatgDescTxt_P");
		dsOrdTpCd = (EZDCStringItem)newItem("dsOrdTpCd");
		dsOrdTpCd_P = (EZDCStringItemArray)newItemArray("dsOrdTpCd_P");
		dsOrdTpDescTxt_P = (EZDCStringItemArray)newItemArray("dsOrdTpDescTxt_P");
		serNum = (EZDCStringItem)newItem("serNum");
		xxLinkAncr_ML = (EZDCStringItem)newItem("xxLinkAncr_ML");
		t_MdlNm = (EZDCStringItem)newItem("t_MdlNm");
		xxLinkAncr_CF = (EZDCStringItem)newItem("xxLinkAncr_CF");
		svcConfigMstrPk = (EZDCBigDecimalItem)newItem("svcConfigMstrPk");
		rtlWhCd = (EZDCStringItem)newItem("rtlWhCd");
		rtlWhNm = (EZDCStringItem)newItem("rtlWhNm");
		xxLinkAncr_WR = (EZDCStringItem)newItem("xxLinkAncr_WR");
		shipToCustCd = (EZDCStringItem)newItem("shipToCustCd");
		dsAcctNm = (EZDCStringItem)newItem("dsAcctNm");
		xxLinkAncr_SP = (EZDCStringItem)newItem("xxLinkAncr_SP");
		tocCd = (EZDCStringItem)newItem("tocCd");
		tocNm = (EZDCStringItem)newItem("tocNm");
		xxLinkAncr_TC = (EZDCStringItem)newItem("xxLinkAncr_TC");
		mdseCd = (EZDCStringItem)newItem("mdseCd");
		mdseDescShortTxt = (EZDCStringItem)newItem("mdseDescShortTxt");
		xxLinkAncr_MD = (EZDCStringItem)newItem("xxLinkAncr_MD");
		rddDt_FR = (EZDCDateItem)newItem("rddDt_FR");
		rddDt_TO = (EZDCDateItem)newItem("rddDt_TO");
		xxOrdDt_FR = (EZDCDateItem)newItem("xxOrdDt_FR");
		xxOrdDt_TO = (EZDCDateItem)newItem("xxOrdDt_TO");
		xxChkBox_BO = (EZDCStringItem)newItem("xxChkBox_BO");
		xxChkBox_AL = (EZDCStringItem)newItem("xxChkBox_AL");
		xxChkBox_NS = (EZDCStringItem)newItem("xxChkBox_NS");
		xxDplyTab = (EZDCStringItem)newItem("xxDplyTab");
		xxComnColOrdTxt = (EZDCStringItem)newItem("xxComnColOrdTxt");
		xxNum_EV = (EZDCBigDecimalItem)newItem("xxNum_EV");
		xxPageShowFromNum_A = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_A");
		xxPageShowToNum_A = (EZDCBigDecimalItem)newItem("xxPageShowToNum_A");
		xxPageShowOfNum_A = (EZDCBigDecimalItem)newItem("xxPageShowOfNum_A");
		xxPageShowCurNum_A = (EZDCBigDecimalItem)newItem("xxPageShowCurNum_A");
		xxPageShowTotNum_A = (EZDCBigDecimalItem)newItem("xxPageShowTotNum_A");
		xxSortTblNm_A = (EZDCStringItem)newItem("xxSortTblNm_A");
		xxSortItemNm_A = (EZDCStringItem)newItem("xxSortItemNm_A");
		xxSortOrdByTxt_A = (EZDCStringItem)newItem("xxSortOrdByTxt_A");
		A = (business.blap.NLBL3080.NLBL3080_ACMsgArray)newMsgArray("A");
		xxPageShowFromNum_B = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_B");
		xxPageShowToNum_B = (EZDCBigDecimalItem)newItem("xxPageShowToNum_B");
		xxPageShowOfNum_B = (EZDCBigDecimalItem)newItem("xxPageShowOfNum_B");
		xxPageShowCurNum_B = (EZDCBigDecimalItem)newItem("xxPageShowCurNum_B");
		xxPageShowTotNum_B = (EZDCBigDecimalItem)newItem("xxPageShowTotNum_B");
		xxSortTblNm_B = (EZDCStringItem)newItem("xxSortTblNm_B");
		xxSortItemNm_B = (EZDCStringItem)newItem("xxSortItemNm_B");
		xxSortOrdByTxt_B = (EZDCStringItem)newItem("xxSortOrdByTxt_B");
		B = (business.blap.NLBL3080.NLBL3080_BCMsgArray)newMsgArray("B");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3080CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3080CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"usrId", "usrId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"dupErrCd", "dupErrCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxWrnSkipFlg", "xxWrnSkipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxCellIdx", "xxCellIdx", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"P", "P", null, "200", "business.blap.NLBL3080.NLBL3080_PCMsgArray", null, null},
	
	{"cpoOrdNum_BK", "cpoOrdNum_BK", "BK", null, TYPE_HANKAKUEISU, "8", null},
	{"t_MdlNm_BK", "t_MdlNm_BK", "BK", null, TYPE_HANKAKUEISU, "50", null},
	{"svcConfigMstrPk_BK", "svcConfigMstrPk_BK", "BK", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"rtlWhCd_BK", "rtlWhCd_BK", "BK", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd_BK", "shipToCustCd_BK", "BK", null, TYPE_HANKAKUEISU, "20", null},
	{"xxDplyTab_BK", "xxDplyTab_BK", "BK", null, TYPE_HANKAKUEISU, "50", null},
	{"srchOptPk_S", "srchOptPk_S", "S", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_S", "srchOptNm_S", "S", null, TYPE_HANKAKUEISU, "50", null},
	{"srchOptPk_L", "srchOptPk_L", "L", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_L", "srchOptNm_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"dsOrdCatgCd", "dsOrdCatgCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdCatgCd_P", "dsOrdCatgCd_P", "P", "99", TYPE_HANKAKUEISU, "4", null},
	{"dsOrdCatgDescTxt_P", "dsOrdCatgDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"dsOrdTpCd", "dsOrdTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdTpCd_P", "dsOrdTpCd_P", "P", "99", TYPE_HANKAKUEISU, "4", null},
	{"dsOrdTpDescTxt_P", "dsOrdTpDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxLinkAncr_ML", "xxLinkAncr_ML", "ML", null, TYPE_HANKAKUEISU, "30", null},
	{"t_MdlNm", "t_MdlNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkAncr_CF", "xxLinkAncr_CF", "CF", null, TYPE_HANKAKUEISU, "30", null},
	{"svcConfigMstrPk", "svcConfigMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"rtlWhCd", "rtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm", "rtlWhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxLinkAncr_WR", "xxLinkAncr_WR", "WR", null, TYPE_HANKAKUEISU, "30", null},
	{"shipToCustCd", "shipToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm", "dsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"xxLinkAncr_SP", "xxLinkAncr_SP", "SP", null, TYPE_HANKAKUEISU, "30", null},
	{"tocCd", "tocCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"tocNm", "tocNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkAncr_TC", "xxLinkAncr_TC", "TC", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"xxLinkAncr_MD", "xxLinkAncr_MD", "MD", null, TYPE_HANKAKUEISU, "30", null},
	{"rddDt_FR", "rddDt_FR", "FR", null, TYPE_NENTSUKIHI, "8", null},
	{"rddDt_TO", "rddDt_TO", "TO", null, TYPE_NENTSUKIHI, "8", null},
	{"xxOrdDt_FR", "xxOrdDt_FR", "FR", null, TYPE_NENTSUKIHI, "8", null},
	{"xxOrdDt_TO", "xxOrdDt_TO", "TO", null, TYPE_NENTSUKIHI, "8", null},
	{"xxChkBox_BO", "xxChkBox_BO", "BO", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_AL", "xxChkBox_AL", "AL", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_NS", "xxChkBox_NS", "NS", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxDplyTab", "xxDplyTab", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxNum_EV", "xxNum_EV", "EV", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowFromNum_A", "xxPageShowFromNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_A", "xxPageShowToNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_A", "xxPageShowOfNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum_A", "xxPageShowCurNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum_A", "xxPageShowTotNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm_A", "xxSortTblNm_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm_A", "xxSortItemNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt_A", "xxSortOrdByTxt_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "40", "business.blap.NLBL3080.NLBL3080_ACMsgArray", null, null},
	
	{"xxPageShowFromNum_B", "xxPageShowFromNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_B", "xxPageShowToNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_B", "xxPageShowOfNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum_B", "xxPageShowCurNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum_B", "xxPageShowTotNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm_B", "xxSortTblNm_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm_B", "xxSortItemNm_B", "B", null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt_B", "xxSortOrdByTxt_B", "B", null, TYPE_HANKAKUEISU, "4", null},
	{"B", "B", null, "40", "business.blap.NLBL3080.NLBL3080_BCMsgArray", null, null},
	
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//usrId
        {"DUP_ERR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dupErrCd
        {"XX_WRN_SKIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWrnSkipFlg
        {"XX_CELL_IDX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCellIdx
		null,	//P
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_BK
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_BK
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_BK
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_BK
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_BK
        {"XX_DPLY_TAB",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab_BK
        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_S
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_S
        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_L
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_L
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"DS_ORD_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd
        {"DS_ORD_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd_P
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt_P
        {"DS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd
        {"DS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd_P
        {"DS_ORD_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpDescTxt_P
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_ML
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_CF
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_WR
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_SP
        {"TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd
        {"TOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocNm
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_TC
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_MD
        {"RDD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rddDt_FR
        {"RDD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rddDt_TO
        {"XX_ORD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdDt_FR
        {"XX_ORD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdDt_TO
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_BO
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AL
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_NS
        {"XX_DPLY_TAB",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_EV
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_A
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_A
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_A
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum_A
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum_A
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm_A
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm_A
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt_A
		null,	//A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_B
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_B
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_B
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum_B
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum_B
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm_B
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm_B
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt_B
		null,	//B
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
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

