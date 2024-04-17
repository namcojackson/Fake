//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180720110723000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3080BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL3080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3080 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3080BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** USR_ID*/
	public final EZDBStringItem              usrId;

    /** DUP_ERR_CD*/
	public final EZDBStringItem              dupErrCd;

    /** XX_MNT_EVENT_NM*/
	public final EZDBStringItem              xxMntEventNm;

    /** XX_WRN_SKIP_FLG*/
	public final EZDBStringItem              xxWrnSkipFlg;

    /** XX_CELL_IDX*/
	public final EZDBBigDecimalItem              xxCellIdx;

    /** P*/
	public final business.servlet.NLBL3080.NLBL3080_PBMsgArray              P;

    /** I*/
	public final business.servlet.NLBL3080.NLBL3080_IBMsgArray              I;

    /** CPO_ORD_NUM_BK*/
	public final EZDBStringItem              cpoOrdNum_BK;

    /** T_MDL_NM_BK*/
	public final EZDBStringItem              t_MdlNm_BK;

    /** SVC_CONFIG_MSTR_PK_BK*/
	public final EZDBBigDecimalItem              svcConfigMstrPk_BK;

    /** RTL_WH_CD_BK*/
	public final EZDBStringItem              rtlWhCd_BK;

    /** SHIP_TO_CUST_CD_BK*/
	public final EZDBStringItem              shipToCustCd_BK;

    /** XX_DPLY_TAB_BK*/
	public final EZDBStringItem              xxDplyTab_BK;

    /** SRCH_OPT_PK_S*/
	public final EZDBBigDecimalItem              srchOptPk_S;

    /** SRCH_OPT_NM_S*/
	public final EZDBStringItem              srchOptNm_S;

    /** SRCH_OPT_PK_L*/
	public final EZDBBigDecimalItemArray              srchOptPk_L;

    /** SRCH_OPT_NM_L*/
	public final EZDBStringItemArray              srchOptNm_L;

    /** CPO_ORD_NUM*/
	public final EZDBStringItem              cpoOrdNum;

    /** DS_ORD_CATG_CD*/
	public final EZDBStringItem              dsOrdCatgCd;

    /** DS_ORD_CATG_CD_P*/
	public final EZDBStringItemArray              dsOrdCatgCd_P;

    /** DS_ORD_CATG_DESC_TXT_P*/
	public final EZDBStringItemArray              dsOrdCatgDescTxt_P;

    /** DS_ORD_TP_CD*/
	public final EZDBStringItem              dsOrdTpCd;

    /** DS_ORD_TP_CD_P*/
	public final EZDBStringItemArray              dsOrdTpCd_P;

    /** DS_ORD_TP_DESC_TXT_P*/
	public final EZDBStringItemArray              dsOrdTpDescTxt_P;

    /** SER_NUM*/
	public final EZDBStringItem              serNum;

    /** XX_LINK_ANCR_ML*/
	public final EZDBStringItem              xxLinkAncr_ML;

    /** T_MDL_NM*/
	public final EZDBStringItem              t_MdlNm;

    /** XX_LINK_ANCR_CF*/
	public final EZDBStringItem              xxLinkAncr_CF;

    /** SVC_CONFIG_MSTR_PK*/
	public final EZDBBigDecimalItem              svcConfigMstrPk;

    /** RTL_WH_CD*/
	public final EZDBStringItem              rtlWhCd;

    /** RTL_WH_NM*/
	public final EZDBStringItem              rtlWhNm;

    /** XX_LINK_ANCR_WR*/
	public final EZDBStringItem              xxLinkAncr_WR;

    /** SHIP_TO_CUST_CD*/
	public final EZDBStringItem              shipToCustCd;

    /** DS_ACCT_NM*/
	public final EZDBStringItem              dsAcctNm;

    /** XX_LINK_ANCR_SP*/
	public final EZDBStringItem              xxLinkAncr_SP;

    /** TOC_CD*/
	public final EZDBStringItem              tocCd;

    /** TOC_NM*/
	public final EZDBStringItem              tocNm;

    /** XX_LINK_ANCR_TC*/
	public final EZDBStringItem              xxLinkAncr_TC;

    /** MDSE_CD*/
	public final EZDBStringItem              mdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDBStringItem              mdseDescShortTxt;

    /** XX_LINK_ANCR_MD*/
	public final EZDBStringItem              xxLinkAncr_MD;

    /** RDD_DT_FR*/
	public final EZDBDateItem              rddDt_FR;

    /** RDD_DT_TO*/
	public final EZDBDateItem              rddDt_TO;

    /** XX_ORD_DT_FR*/
	public final EZDBDateItem              xxOrdDt_FR;

    /** XX_ORD_DT_TO*/
	public final EZDBDateItem              xxOrdDt_TO;

    /** XX_CHK_BOX_BO*/
	public final EZDBStringItem              xxChkBox_BO;

    /** XX_CHK_BOX_AL*/
	public final EZDBStringItem              xxChkBox_AL;

    /** XX_CHK_BOX_NS*/
	public final EZDBStringItem              xxChkBox_NS;

    /** XX_DPLY_TAB*/
	public final EZDBStringItem              xxDplyTab;

    /** XX_NUM_EV*/
	public final EZDBBigDecimalItem              xxNum_EV;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDBStringItem              xxComnColOrdTxt;

    /** XX_PAGE_SHOW_FROM_NUM_A*/
	public final EZDBBigDecimalItem              xxPageShowFromNum_A;

    /** XX_PAGE_SHOW_TO_NUM_A*/
	public final EZDBBigDecimalItem              xxPageShowToNum_A;

    /** XX_PAGE_SHOW_OF_NUM_A*/
	public final EZDBBigDecimalItem              xxPageShowOfNum_A;

    /** XX_PAGE_SHOW_CUR_NUM_A*/
	public final EZDBBigDecimalItem              xxPageShowCurNum_A;

    /** XX_PAGE_SHOW_TOT_NUM_A*/
	public final EZDBBigDecimalItem              xxPageShowTotNum_A;

    /** XX_SORT_TBL_NM_A*/
	public final EZDBStringItem              xxSortTblNm_A;

    /** XX_SORT_ITEM_NM_A*/
	public final EZDBStringItem              xxSortItemNm_A;

    /** XX_SORT_ORD_BY_TXT_A*/
	public final EZDBStringItem              xxSortOrdByTxt_A;

    /** A*/
	public final business.servlet.NLBL3080.NLBL3080_ABMsgArray              A;

    /** XX_PAGE_SHOW_FROM_NUM_B*/
	public final EZDBBigDecimalItem              xxPageShowFromNum_B;

    /** XX_PAGE_SHOW_TO_NUM_B*/
	public final EZDBBigDecimalItem              xxPageShowToNum_B;

    /** XX_PAGE_SHOW_OF_NUM_B*/
	public final EZDBBigDecimalItem              xxPageShowOfNum_B;

    /** XX_PAGE_SHOW_CUR_NUM_B*/
	public final EZDBBigDecimalItem              xxPageShowCurNum_B;

    /** XX_PAGE_SHOW_TOT_NUM_B*/
	public final EZDBBigDecimalItem              xxPageShowTotNum_B;

    /** XX_SORT_TBL_NM_B*/
	public final EZDBStringItem              xxSortTblNm_B;

    /** XX_SORT_ITEM_NM_B*/
	public final EZDBStringItem              xxSortItemNm_B;

    /** XX_SORT_ORD_BY_TXT_B*/
	public final EZDBStringItem              xxSortOrdByTxt_B;

    /** B*/
	public final business.servlet.NLBL3080.NLBL3080_BBMsgArray              B;

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;


	/**
	 * NLBL3080BMsg is constructor.
	 * The initialization when the instance of NLBL3080BMsg is generated.
	 */
	public NLBL3080BMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3080BMsg is constructor.
	 * The initialization when the instance of NLBL3080BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3080BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		usrId = (EZDBStringItem)newItem("usrId");
		dupErrCd = (EZDBStringItem)newItem("dupErrCd");
		xxMntEventNm = (EZDBStringItem)newItem("xxMntEventNm");
		xxWrnSkipFlg = (EZDBStringItem)newItem("xxWrnSkipFlg");
		xxCellIdx = (EZDBBigDecimalItem)newItem("xxCellIdx");
		P = (business.servlet.NLBL3080.NLBL3080_PBMsgArray)newMsgArray("P");
		I = (business.servlet.NLBL3080.NLBL3080_IBMsgArray)newMsgArray("I");
		cpoOrdNum_BK = (EZDBStringItem)newItem("cpoOrdNum_BK");
		t_MdlNm_BK = (EZDBStringItem)newItem("t_MdlNm_BK");
		svcConfigMstrPk_BK = (EZDBBigDecimalItem)newItem("svcConfigMstrPk_BK");
		rtlWhCd_BK = (EZDBStringItem)newItem("rtlWhCd_BK");
		shipToCustCd_BK = (EZDBStringItem)newItem("shipToCustCd_BK");
		xxDplyTab_BK = (EZDBStringItem)newItem("xxDplyTab_BK");
		srchOptPk_S = (EZDBBigDecimalItem)newItem("srchOptPk_S");
		srchOptNm_S = (EZDBStringItem)newItem("srchOptNm_S");
		srchOptPk_L = (EZDBBigDecimalItemArray)newItemArray("srchOptPk_L");
		srchOptNm_L = (EZDBStringItemArray)newItemArray("srchOptNm_L");
		cpoOrdNum = (EZDBStringItem)newItem("cpoOrdNum");
		dsOrdCatgCd = (EZDBStringItem)newItem("dsOrdCatgCd");
		dsOrdCatgCd_P = (EZDBStringItemArray)newItemArray("dsOrdCatgCd_P");
		dsOrdCatgDescTxt_P = (EZDBStringItemArray)newItemArray("dsOrdCatgDescTxt_P");
		dsOrdTpCd = (EZDBStringItem)newItem("dsOrdTpCd");
		dsOrdTpCd_P = (EZDBStringItemArray)newItemArray("dsOrdTpCd_P");
		dsOrdTpDescTxt_P = (EZDBStringItemArray)newItemArray("dsOrdTpDescTxt_P");
		serNum = (EZDBStringItem)newItem("serNum");
		xxLinkAncr_ML = (EZDBStringItem)newItem("xxLinkAncr_ML");
		t_MdlNm = (EZDBStringItem)newItem("t_MdlNm");
		xxLinkAncr_CF = (EZDBStringItem)newItem("xxLinkAncr_CF");
		svcConfigMstrPk = (EZDBBigDecimalItem)newItem("svcConfigMstrPk");
		rtlWhCd = (EZDBStringItem)newItem("rtlWhCd");
		rtlWhNm = (EZDBStringItem)newItem("rtlWhNm");
		xxLinkAncr_WR = (EZDBStringItem)newItem("xxLinkAncr_WR");
		shipToCustCd = (EZDBStringItem)newItem("shipToCustCd");
		dsAcctNm = (EZDBStringItem)newItem("dsAcctNm");
		xxLinkAncr_SP = (EZDBStringItem)newItem("xxLinkAncr_SP");
		tocCd = (EZDBStringItem)newItem("tocCd");
		tocNm = (EZDBStringItem)newItem("tocNm");
		xxLinkAncr_TC = (EZDBStringItem)newItem("xxLinkAncr_TC");
		mdseCd = (EZDBStringItem)newItem("mdseCd");
		mdseDescShortTxt = (EZDBStringItem)newItem("mdseDescShortTxt");
		xxLinkAncr_MD = (EZDBStringItem)newItem("xxLinkAncr_MD");
		rddDt_FR = (EZDBDateItem)newItem("rddDt_FR");
		rddDt_TO = (EZDBDateItem)newItem("rddDt_TO");
		xxOrdDt_FR = (EZDBDateItem)newItem("xxOrdDt_FR");
		xxOrdDt_TO = (EZDBDateItem)newItem("xxOrdDt_TO");
		xxChkBox_BO = (EZDBStringItem)newItem("xxChkBox_BO");
		xxChkBox_AL = (EZDBStringItem)newItem("xxChkBox_AL");
		xxChkBox_NS = (EZDBStringItem)newItem("xxChkBox_NS");
		xxDplyTab = (EZDBStringItem)newItem("xxDplyTab");
		xxNum_EV = (EZDBBigDecimalItem)newItem("xxNum_EV");
		xxComnColOrdTxt = (EZDBStringItem)newItem("xxComnColOrdTxt");
		xxPageShowFromNum_A = (EZDBBigDecimalItem)newItem("xxPageShowFromNum_A");
		xxPageShowToNum_A = (EZDBBigDecimalItem)newItem("xxPageShowToNum_A");
		xxPageShowOfNum_A = (EZDBBigDecimalItem)newItem("xxPageShowOfNum_A");
		xxPageShowCurNum_A = (EZDBBigDecimalItem)newItem("xxPageShowCurNum_A");
		xxPageShowTotNum_A = (EZDBBigDecimalItem)newItem("xxPageShowTotNum_A");
		xxSortTblNm_A = (EZDBStringItem)newItem("xxSortTblNm_A");
		xxSortItemNm_A = (EZDBStringItem)newItem("xxSortItemNm_A");
		xxSortOrdByTxt_A = (EZDBStringItem)newItem("xxSortOrdByTxt_A");
		A = (business.servlet.NLBL3080.NLBL3080_ABMsgArray)newMsgArray("A");
		xxPageShowFromNum_B = (EZDBBigDecimalItem)newItem("xxPageShowFromNum_B");
		xxPageShowToNum_B = (EZDBBigDecimalItem)newItem("xxPageShowToNum_B");
		xxPageShowOfNum_B = (EZDBBigDecimalItem)newItem("xxPageShowOfNum_B");
		xxPageShowCurNum_B = (EZDBBigDecimalItem)newItem("xxPageShowCurNum_B");
		xxPageShowTotNum_B = (EZDBBigDecimalItem)newItem("xxPageShowTotNum_B");
		xxSortTblNm_B = (EZDBStringItem)newItem("xxSortTblNm_B");
		xxSortItemNm_B = (EZDBStringItem)newItem("xxSortItemNm_B");
		xxSortOrdByTxt_B = (EZDBStringItem)newItem("xxSortOrdByTxt_B");
		B = (business.servlet.NLBL3080.NLBL3080_BBMsgArray)newMsgArray("B");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3080BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3080BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"usrId", "usrId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"dupErrCd", "dupErrCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxMntEventNm", "xxMntEventNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxWrnSkipFlg", "xxWrnSkipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxCellIdx", "xxCellIdx", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"P", "P", null, "35", "business.servlet.NLBL3080.NLBL3080_PBMsgArray", null, null},
	
	{"I", "I", null, "2", "business.servlet.NLBL3080.NLBL3080_IBMsgArray", null, null},
	
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
	{"xxNum_EV", "xxNum_EV", "EV", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxPageShowFromNum_A", "xxPageShowFromNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_A", "xxPageShowToNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_A", "xxPageShowOfNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum_A", "xxPageShowCurNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum_A", "xxPageShowTotNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm_A", "xxSortTblNm_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm_A", "xxSortItemNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt_A", "xxSortOrdByTxt_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "40", "business.servlet.NLBL3080.NLBL3080_ABMsgArray", null, null},
	
	{"xxPageShowFromNum_B", "xxPageShowFromNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_B", "xxPageShowToNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_B", "xxPageShowOfNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum_B", "xxPageShowCurNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum_B", "xxPageShowTotNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm_B", "xxSortTblNm_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm_B", "xxSortItemNm_B", "B", null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt_B", "xxSortOrdByTxt_B", "B", null, TYPE_HANKAKUEISU, "4", null},
	{"B", "B", null, "40", "business.servlet.NLBL3080.NLBL3080_BBMsgArray", null, null},
	
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"USR_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//usrId
        {"DUP_ERR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dupErrCd
        {"XX_MNT_EVENT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMntEventNm
        {"XX_WRN_SKIP_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWrnSkipFlg
        {"XX_CELL_IDX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCellIdx
		null,	//P
		null,	//I
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_BK
        {"T_MDL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_BK
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_BK
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_BK
        {"SHIP_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_BK
        {"XX_DPLY_TAB",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab_BK
        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_S
        {"SRCH_OPT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_S
        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_L
        {"SRCH_OPT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_L
        {"CPO_ORD_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"DS_ORD_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd
        {"DS_ORD_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd_P
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt_P
        {"DS_ORD_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd
        {"DS_ORD_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd_P
        {"DS_ORD_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpDescTxt_P
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"XX_LINK_ANCR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_ML
        {"T_MDL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm
        {"XX_LINK_ANCR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_CF
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm
        {"XX_LINK_ANCR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_WR
        {"SHIP_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"XX_LINK_ANCR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_SP
        {"TOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd
        {"TOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocNm
        {"XX_LINK_ANCR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_TC
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"XX_LINK_ANCR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_MD
        {"RDD_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//rddDt_FR
        {"RDD_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//rddDt_TO
        {"XX_ORD_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxOrdDt_FR
        {"XX_ORD_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxOrdDt_TO
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_BO
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AL
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_NS
        {"XX_DPLY_TAB",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab
        {"XX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_EV
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_A
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_A
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_A
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum_A
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum_A
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm_A
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm_A
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt_A
		null,	//A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_B
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_B
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_B
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum_B
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum_B
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm_B
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm_B
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt_B
		null,	//B
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
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

