//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20221020071144000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1520BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1520;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1520 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1520BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SRCH_OPT_PK_PD*/
	public final EZDBBigDecimalItemArray              srchOptPk_PD;

    /** SRCH_OPT_NM_PD*/
	public final EZDBStringItemArray              srchOptNm_PD;

    /** SRCH_OPT_PK_SL*/
	public final EZDBBigDecimalItem              srchOptPk_SL;

    /** SRCH_OPT_NM_TX*/
	public final EZDBStringItem              srchOptNm_TX;

    /** SRCH_OPT_USR_ID_U1*/
	public final EZDBStringItem              srchOptUsrId_U1;

    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** MRP_PLN_NM*/
	public final EZDBStringItem              mrpPlnNm;

    /** MDSE_CD*/
	public final EZDBStringItem              mdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDBStringItem              mdseDescShortTxt;

    /** RTL_WH_CATG_CD_PD*/
	public final EZDBStringItemArray              rtlWhCatgCd_PD;

    /** RTL_WH_CATG_DESC_TXT_PD*/
	public final EZDBStringItemArray              rtlWhCatgDescTxt_PD;

    /** RTL_WH_CATG_CD_SL*/
	public final EZDBStringItem              rtlWhCatgCd_SL;

    /** LOC_TP_CD*/
	public final EZDBStringItem              locTpCd;

    /** RTL_WH_CD*/
	public final EZDBStringItem              rtlWhCd;

    /** RTL_WH_NM_W1*/
	public final EZDBStringItem              rtlWhNm_W1;

    /** RTL_SWH_CD*/
	public final EZDBStringItem              rtlSwhCd;

    /** RTL_SWH_NM_S1*/
	public final EZDBStringItem              rtlSwhNm_S1;

    /** WH_MGR_PSN_CD*/
	public final EZDBStringItem              whMgrPsnCd;

    /** FULL_PSN_NM*/
	public final EZDBStringItem              fullPsnNm;

    /** PROCR_TP_CD_PD*/
	public final EZDBStringItemArray              procrTpCd_PD;

    /** PROCR_TP_DESC_TXT_PD*/
	public final EZDBStringItemArray              procrTpDescTxt_PD;

    /** PROCR_TP_CD_SL*/
	public final EZDBStringItem              procrTpCd_SL;

    /** SRC_RTL_WH_CD*/
	public final EZDBStringItem              srcRtlWhCd;

    /** RTL_WH_NM_W2*/
	public final EZDBStringItem              rtlWhNm_W2;

    /** SRC_RTL_SWH_CD*/
	public final EZDBStringItem              srcRtlSwhCd;

    /** RTL_SWH_NM_S2*/
	public final EZDBStringItem              rtlSwhNm_S2;

    /** MRP_ENBL_FLG*/
	public final EZDBStringItem              mrpEnblFlg;

    /** CALC_ORD_PROC_CD*/
	public final EZDBStringItem              calcOrdProcCd;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDBStringItem              xxComnColOrdTxt;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_RADIO_BTN*/
	public final EZDBBigDecimalItem              xxRadioBtn;

    /** A*/
	public final business.servlet.NPAL1520.NPAL1520_ABMsgArray              A;

    /** XX_POP_PRM_EV*/
	public final EZDBStringItem              xxPopPrm_EV;

    /** P*/
	public final business.servlet.NPAL1520.NPAL1520_PBMsgArray              P;

    /** XX_TBL_NM_P1*/
	public final EZDBStringItem              xxTblNm_P1;

    /** XX_TBL_CD_COL_NM_P1*/
	public final EZDBStringItem              xxTblCdColNm_P1;

    /** XX_TBL_NM_COL_NM_P1*/
	public final EZDBStringItem              xxTblNmColNm_P1;

    /** XX_TBL_SORT_COL_NM_P1*/
	public final EZDBStringItem              xxTblSortColNm_P1;

    /** XX_SCR_NM_P1*/
	public final EZDBStringItem              xxScrNm_P1;

    /** XX_HDR_CD_LB_NM_P1*/
	public final EZDBStringItem              xxHdrCdLbNm_P1;

    /** XX_HDR_NM_LB_NM_P1*/
	public final EZDBStringItem              xxHdrNmLbNm_P1;

    /** XX_DTL_CD_LB_NM_P1*/
	public final EZDBStringItem              xxDtlCdLbNm_P1;

    /** XX_DTL_NM_LB_NM_P1*/
	public final EZDBStringItem              xxDtlNmLbNm_P1;

    /** XX_COND_CD_P1*/
	public final EZDBStringItem              xxCondCd_P1;

    /** XX_COND_NM_P1*/
	public final EZDBStringItem              xxCondNm_P1;

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;

    /** XX_POP_PRM_E0*/
	public final EZDBStringItem              xxPopPrm_E0;

    /** XX_POP_PRM_E1*/
	public final EZDBStringItem              xxPopPrm_E1;

    /** XX_POP_PRM_E2*/
	public final EZDBStringItem              xxPopPrm_E2;

    /** XX_POP_PRM_E3*/
	public final EZDBStringItem              xxPopPrm_E3;

    /** XX_POP_PRM_M0*/
	public final EZDBStringItem              xxPopPrm_M0;

    /** XX_POP_PRM_M1*/
	public final EZDBStringItem              xxPopPrm_M1;

    /** XX_POP_PRM_M2*/
	public final EZDBStringItem              xxPopPrm_M2;

    /** XX_POP_PRM_M3*/
	public final EZDBStringItem              xxPopPrm_M3;

    /** XX_POP_PRM_M4*/
	public final EZDBStringItem              xxPopPrm_M4;

    /** XX_POP_PRM_M5*/
	public final EZDBStringItem              xxPopPrm_M5;

    /** XX_POP_PRM_M6*/
	public final EZDBStringItem              xxPopPrm_M6;

    /** XX_POP_PRM_M7*/
	public final EZDBStringItem              xxPopPrm_M7;

    /** XX_POP_PRM_M8*/
	public final EZDBStringItem              xxPopPrm_M8;

    /** XX_POP_PRM_M9*/
	public final EZDBStringItem              xxPopPrm_M9;


	/**
	 * NPAL1520BMsg is constructor.
	 * The initialization when the instance of NPAL1520BMsg is generated.
	 */
	public NPAL1520BMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1520BMsg is constructor.
	 * The initialization when the instance of NPAL1520BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1520BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		srchOptPk_PD = (EZDBBigDecimalItemArray)newItemArray("srchOptPk_PD");
		srchOptNm_PD = (EZDBStringItemArray)newItemArray("srchOptNm_PD");
		srchOptPk_SL = (EZDBBigDecimalItem)newItem("srchOptPk_SL");
		srchOptNm_TX = (EZDBStringItem)newItem("srchOptNm_TX");
		srchOptUsrId_U1 = (EZDBStringItem)newItem("srchOptUsrId_U1");
		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		mrpPlnNm = (EZDBStringItem)newItem("mrpPlnNm");
		mdseCd = (EZDBStringItem)newItem("mdseCd");
		mdseDescShortTxt = (EZDBStringItem)newItem("mdseDescShortTxt");
		rtlWhCatgCd_PD = (EZDBStringItemArray)newItemArray("rtlWhCatgCd_PD");
		rtlWhCatgDescTxt_PD = (EZDBStringItemArray)newItemArray("rtlWhCatgDescTxt_PD");
		rtlWhCatgCd_SL = (EZDBStringItem)newItem("rtlWhCatgCd_SL");
		locTpCd = (EZDBStringItem)newItem("locTpCd");
		rtlWhCd = (EZDBStringItem)newItem("rtlWhCd");
		rtlWhNm_W1 = (EZDBStringItem)newItem("rtlWhNm_W1");
		rtlSwhCd = (EZDBStringItem)newItem("rtlSwhCd");
		rtlSwhNm_S1 = (EZDBStringItem)newItem("rtlSwhNm_S1");
		whMgrPsnCd = (EZDBStringItem)newItem("whMgrPsnCd");
		fullPsnNm = (EZDBStringItem)newItem("fullPsnNm");
		procrTpCd_PD = (EZDBStringItemArray)newItemArray("procrTpCd_PD");
		procrTpDescTxt_PD = (EZDBStringItemArray)newItemArray("procrTpDescTxt_PD");
		procrTpCd_SL = (EZDBStringItem)newItem("procrTpCd_SL");
		srcRtlWhCd = (EZDBStringItem)newItem("srcRtlWhCd");
		rtlWhNm_W2 = (EZDBStringItem)newItem("rtlWhNm_W2");
		srcRtlSwhCd = (EZDBStringItem)newItem("srcRtlSwhCd");
		rtlSwhNm_S2 = (EZDBStringItem)newItem("rtlSwhNm_S2");
		mrpEnblFlg = (EZDBStringItem)newItem("mrpEnblFlg");
		calcOrdProcCd = (EZDBStringItem)newItem("calcOrdProcCd");
		xxComnColOrdTxt = (EZDBStringItem)newItem("xxComnColOrdTxt");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxRadioBtn = (EZDBBigDecimalItem)newItem("xxRadioBtn");
		A = (business.servlet.NPAL1520.NPAL1520_ABMsgArray)newMsgArray("A");
		xxPopPrm_EV = (EZDBStringItem)newItem("xxPopPrm_EV");
		P = (business.servlet.NPAL1520.NPAL1520_PBMsgArray)newMsgArray("P");
		xxTblNm_P1 = (EZDBStringItem)newItem("xxTblNm_P1");
		xxTblCdColNm_P1 = (EZDBStringItem)newItem("xxTblCdColNm_P1");
		xxTblNmColNm_P1 = (EZDBStringItem)newItem("xxTblNmColNm_P1");
		xxTblSortColNm_P1 = (EZDBStringItem)newItem("xxTblSortColNm_P1");
		xxScrNm_P1 = (EZDBStringItem)newItem("xxScrNm_P1");
		xxHdrCdLbNm_P1 = (EZDBStringItem)newItem("xxHdrCdLbNm_P1");
		xxHdrNmLbNm_P1 = (EZDBStringItem)newItem("xxHdrNmLbNm_P1");
		xxDtlCdLbNm_P1 = (EZDBStringItem)newItem("xxDtlCdLbNm_P1");
		xxDtlNmLbNm_P1 = (EZDBStringItem)newItem("xxDtlNmLbNm_P1");
		xxCondCd_P1 = (EZDBStringItem)newItem("xxCondCd_P1");
		xxCondNm_P1 = (EZDBStringItem)newItem("xxCondNm_P1");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		xxPopPrm_E0 = (EZDBStringItem)newItem("xxPopPrm_E0");
		xxPopPrm_E1 = (EZDBStringItem)newItem("xxPopPrm_E1");
		xxPopPrm_E2 = (EZDBStringItem)newItem("xxPopPrm_E2");
		xxPopPrm_E3 = (EZDBStringItem)newItem("xxPopPrm_E3");
		xxPopPrm_M0 = (EZDBStringItem)newItem("xxPopPrm_M0");
		xxPopPrm_M1 = (EZDBStringItem)newItem("xxPopPrm_M1");
		xxPopPrm_M2 = (EZDBStringItem)newItem("xxPopPrm_M2");
		xxPopPrm_M3 = (EZDBStringItem)newItem("xxPopPrm_M3");
		xxPopPrm_M4 = (EZDBStringItem)newItem("xxPopPrm_M4");
		xxPopPrm_M5 = (EZDBStringItem)newItem("xxPopPrm_M5");
		xxPopPrm_M6 = (EZDBStringItem)newItem("xxPopPrm_M6");
		xxPopPrm_M7 = (EZDBStringItem)newItem("xxPopPrm_M7");
		xxPopPrm_M8 = (EZDBStringItem)newItem("xxPopPrm_M8");
		xxPopPrm_M9 = (EZDBStringItem)newItem("xxPopPrm_M9");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1520BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1520BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"srchOptPk_PD", "srchOptPk_PD", "PD", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_PD", "srchOptNm_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
	{"srchOptPk_SL", "srchOptPk_SL", "SL", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_TX", "srchOptNm_TX", "TX", null, TYPE_HANKAKUEISU, "50", null},
	{"srchOptUsrId_U1", "srchOptUsrId_U1", "U1", null, TYPE_HANKAKUEISU, "16", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"mrpPlnNm", "mrpPlnNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"rtlWhCatgCd_PD", "rtlWhCatgCd_PD", "PD", "99", TYPE_HANKAKUEISU, "2", null},
	{"rtlWhCatgDescTxt_PD", "rtlWhCatgDescTxt_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
	{"rtlWhCatgCd_SL", "rtlWhCatgCd_SL", "SL", null, TYPE_HANKAKUEISU, "2", null},
	{"locTpCd", "locTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"rtlWhCd", "rtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_W1", "rtlWhNm_W1", "W1", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd", "rtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_S1", "rtlSwhNm_S1", "S1", null, TYPE_HANKAKUEISU, "30", null},
	{"whMgrPsnCd", "whMgrPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"fullPsnNm", "fullPsnNm", null, null, TYPE_HANKAKUEISU, "62", null},
	{"procrTpCd_PD", "procrTpCd_PD", "PD", "99", TYPE_HANKAKUEISU, "2", null},
	{"procrTpDescTxt_PD", "procrTpDescTxt_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
	{"procrTpCd_SL", "procrTpCd_SL", "SL", null, TYPE_HANKAKUEISU, "2", null},
	{"srcRtlWhCd", "srcRtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_W2", "rtlWhNm_W2", "W2", null, TYPE_HANKAKUEISU, "30", null},
	{"srcRtlSwhCd", "srcRtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_S2", "rtlSwhNm_S2", "S2", null, TYPE_HANKAKUEISU, "30", null},
	{"mrpEnblFlg", "mrpEnblFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"calcOrdProcCd", "calcOrdProcCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxRadioBtn", "xxRadioBtn", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"A", "A", null, "20", "business.servlet.NPAL1520.NPAL1520_ABMsgArray", null, null},
	
	{"xxPopPrm_EV", "xxPopPrm_EV", "EV", null, TYPE_HANKAKUEISU, "300", null},
	{"P", "P", null, "22", "business.servlet.NPAL1520.NPAL1520_PBMsgArray", null, null},
	
	{"xxTblNm_P1", "xxTblNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblCdColNm_P1", "xxTblCdColNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblNmColNm_P1", "xxTblNmColNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblSortColNm_P1", "xxTblSortColNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxScrNm_P1", "xxScrNm_P1", "P1", null, TYPE_HANKAKUEISU, "70", null},
	{"xxHdrCdLbNm_P1", "xxHdrCdLbNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxHdrNmLbNm_P1", "xxHdrNmLbNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxDtlCdLbNm_P1", "xxDtlCdLbNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxDtlNmLbNm_P1", "xxDtlNmLbNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxCondCd_P1", "xxCondCd_P1", "P1", null, TYPE_HANKAKUEISU, "20", null},
	{"xxCondNm_P1", "xxCondNm_P1", "P1", null, TYPE_HANKAKUEISU, "70", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"xxPopPrm_E0", "xxPopPrm_E0", "E0", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_E1", "xxPopPrm_E1", "E1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_E2", "xxPopPrm_E2", "E2", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_E3", "xxPopPrm_E3", "E3", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_M0", "xxPopPrm_M0", "M0", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_M1", "xxPopPrm_M1", "M1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_M2", "xxPopPrm_M2", "M2", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_M3", "xxPopPrm_M3", "M3", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_M4", "xxPopPrm_M4", "M4", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_M5", "xxPopPrm_M5", "M5", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_M6", "xxPopPrm_M6", "M6", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_M7", "xxPopPrm_M7", "M7", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_M8", "xxPopPrm_M8", "M8", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_M9", "xxPopPrm_M9", "M9", null, TYPE_HANKAKUEISU, "300", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_PD
        {"SRCH_OPT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_PD
        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_SL
        {"SRCH_OPT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_TX
        {"SRCH_OPT_USR_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptUsrId_U1
        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"MRP_PLN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpPlnNm
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"RTL_WH_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgCd_PD
        {"RTL_WH_CATG_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgDescTxt_PD
        {"RTL_WH_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgCd_SL
        {"LOC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locTpCd
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_W1
        {"RTL_SWH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd
        {"RTL_SWH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_S1
        {"WH_MGR_PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whMgrPsnCd
        {"FULL_PSN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fullPsnNm
        {"PROCR_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpCd_PD
        {"PROCR_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpDescTxt_PD
        {"PROCR_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpCd_SL
        {"SRC_RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlWhCd
        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_W2
        {"SRC_RTL_SWH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlSwhCd
        {"RTL_SWH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_S2
        {"MRP_ENBL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpEnblFlg
        {"CALC_ORD_PROC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//calcOrdProcCd
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_RADIO_BTN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn
		null,	//A
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_EV
		null,	//P
        {"XX_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNm_P1
        {"XX_TBL_CD_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblCdColNm_P1
        {"XX_TBL_NM_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNmColNm_P1
        {"XX_TBL_SORT_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblSortColNm_P1
        {"XX_SCR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm_P1
        {"XX_HDR_CD_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrCdLbNm_P1
        {"XX_HDR_NM_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrNmLbNm_P1
        {"XX_DTL_CD_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCdLbNm_P1
        {"XX_DTL_NM_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlNmLbNm_P1
        {"XX_COND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCondCd_P1
        {"XX_COND_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCondNm_P1
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_E0
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_E1
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_E2
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_E3
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M0
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M1
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M2
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M3
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M4
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M5
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M6
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M7
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M8
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M9
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
