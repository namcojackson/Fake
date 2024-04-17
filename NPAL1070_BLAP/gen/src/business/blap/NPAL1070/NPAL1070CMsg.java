//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230419132643000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1070CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1070;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1070 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1070CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** MRP_INFO_PK*/
	public final EZDCBigDecimalItem              mrpInfoPk;

    /** MRP_INFO_PK_IM*/
	public final EZDCBigDecimalItem              mrpInfoPk_IM;

    /** MRP_PLN_NM_H1*/
	public final EZDCStringItem              mrpPlnNm_H1;

    /** MDSE_CD_H2*/
	public final EZDCStringItem              mdseCd_H2;

    /** RTL_WH_CD_H3*/
	public final EZDCStringItem              rtlWhCd_H3;

    /** RTL_SWH_CD_H4*/
	public final EZDCStringItem              rtlSwhCd_H4;

    /** RTL_WH_CATG_DESC_TXT*/
	public final EZDCStringItem              rtlWhCatgDescTxt;

    /** COA_MDSE_TP_CD*/
	public final EZDCStringItem              coaMdseTpCd;

    /** COA_PROD_CD*/
	public final EZDCStringItem              coaProdCd;

    /** XX_MSG_TXT*/
	public final EZDCStringItem              xxMsgTxt;

    /** XX_YES_NO_CD*/
	public final EZDCStringItem              xxYesNoCd;

    /** MRP_PLN_NM*/
	public final EZDCStringItem              mrpPlnNm;

    /** MDSE_CD*/
	public final EZDCStringItem              mdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDCStringItem              mdseDescShortTxt;

    /** MDSE_TP_CD*/
	public final EZDCStringItem              mdseTpCd;

    /** RTL_WH_CATG_CD_PD*/
	public final EZDCStringItemArray              rtlWhCatgCd_PD;

    /** RTL_WH_CATG_DESC_TXT_PD*/
	public final EZDCStringItemArray              rtlWhCatgDescTxt_PD;

    /** RTL_WH_CATG_CD_SL*/
	public final EZDCStringItem              rtlWhCatgCd_SL;

    /** LOC_TP_CD*/
	public final EZDCStringItem              locTpCd;

    /** RTL_WH_CD*/
	public final EZDCStringItem              rtlWhCd;

    /** RTL_WH_NM_W1*/
	public final EZDCStringItem              rtlWhNm_W1;

    /** RTL_SWH_CD*/
	public final EZDCStringItem              rtlSwhCd;

    /** RTL_SWH_NM_S1*/
	public final EZDCStringItem              rtlSwhNm_S1;

    /** WH_MGR_PSN_CD*/
	public final EZDCStringItem              whMgrPsnCd;

    /** FULL_PSN_NM*/
	public final EZDCStringItem              fullPsnNm;

    /** PROCR_TP_CD_PD*/
	public final EZDCStringItemArray              procrTpCd_PD;

    /** PROCR_TP_DESC_TXT_PD*/
	public final EZDCStringItemArray              procrTpDescTxt_PD;

    /** PROCR_TP_CD_SL*/
	public final EZDCStringItem              procrTpCd_SL;

    /** CALC_ORD_PROC_CD_C1*/
	public final EZDCStringItem              calcOrdProcCd_C1;

    /** SRC_RTL_WH_CD*/
	public final EZDCStringItem              srcRtlWhCd;

    /** RTL_WH_NM_W2*/
	public final EZDCStringItem              rtlWhNm_W2;

    /** SRC_RTL_SWH_CD*/
	public final EZDCStringItem              srcRtlSwhCd;

    /** RTL_SWH_NM_S2*/
	public final EZDCStringItem              rtlSwhNm_S2;

    /** MRP_ENBL_FLG*/
	public final EZDCStringItem              mrpEnblFlg;

    /** CALC_ORD_PROC_CD*/
	public final EZDCStringItem              calcOrdProcCd;

    /** RPLSH_DLY_FLG*/
	public final EZDCStringItem              rplshDlyFlg;

    /** RPLSH_MON_FLG*/
	public final EZDCStringItem              rplshMonFlg;

    /** RPLSH_TUE_FLG*/
	public final EZDCStringItem              rplshTueFlg;

    /** RPLSH_WED_FLG*/
	public final EZDCStringItem              rplshWedFlg;

    /** RPLSH_THU_FLG*/
	public final EZDCStringItem              rplshThuFlg;

    /** RPLSH_FRI_FLG*/
	public final EZDCStringItem              rplshFriFlg;

    /** XX_SEL_FLG*/
	public final EZDCStringItem              xxSelFlg;

    /** XX_WRN_SKIP_FLG_SB*/
	public final EZDCStringItem              xxWrnSkipFlg_SB;

    /** CALC_ORD_PROC_CD_C2*/
	public final EZDCStringItem              calcOrdProcCd_C2;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDCStringItem              xxComnColOrdTxt;

    /** XX_NUM*/
	public final EZDCBigDecimalItem              xxNum;

    /** PROCR_TP_CD_AP*/
	public final EZDCStringItemArray              procrTpCd_AP;

    /** PROCR_TP_DESC_TXT_AP*/
	public final EZDCStringItemArray              procrTpDescTxt_AP;

    /** A*/
	public final business.blap.NPAL1070.NPAL1070_ACMsgArray              A;

    /** XX_FILE_DATA_UP*/
	public final EZDCMimeSourceItem              xxFileData_UP;

    /** B*/
	public final business.blap.NPAL1070.NPAL1070_BCMsgArray              B;

    /** XX_FILE_DATA_DW*/
	public final EZDCMimeSourceItem              xxFileData_DW;

    /** XX_POP_PRM_EV*/
	public final EZDCStringItem              xxPopPrm_EV;

    /** P*/
	public final business.blap.NPAL1070.NPAL1070_PCMsgArray              P;

    /** X*/
	public final business.blap.NPAL1070.NPAL1070_XCMsgArray              X;

    /** XX_POP_PRM_M0*/
	public final EZDCStringItem              xxPopPrm_M0;

    /** XX_POP_PRM_M1*/
	public final EZDCStringItem              xxPopPrm_M1;

    /** XX_POP_PRM_M2*/
	public final EZDCStringItem              xxPopPrm_M2;

    /** XX_POP_PRM_M3*/
	public final EZDCStringItem              xxPopPrm_M3;

    /** XX_POP_PRM_M4*/
	public final EZDCStringItem              xxPopPrm_M4;

    /** XX_POP_PRM_M5*/
	public final EZDCStringItem              xxPopPrm_M5;

    /** XX_POP_PRM_M6*/
	public final EZDCStringItem              xxPopPrm_M6;

    /** XX_POP_PRM_M7*/
	public final EZDCStringItem              xxPopPrm_M7;

    /** XX_POP_PRM_M8*/
	public final EZDCStringItem              xxPopPrm_M8;

    /** XX_POP_PRM_M9*/
	public final EZDCStringItem              xxPopPrm_M9;

    /** XX_POP_PRM_C0*/
	public final EZDCStringItem              xxPopPrm_C0;

    /** XX_POP_PRM_C1*/
	public final EZDCStringItem              xxPopPrm_C1;

    /** XX_POP_PRM_C2*/
	public final EZDCStringItem              xxPopPrm_C2;

    /** XX_POP_PRM_C3*/
	public final EZDCStringItem              xxPopPrm_C3;

    /** XX_POP_PRM_C4*/
	public final EZDCStringItem              xxPopPrm_C4;

    /** XX_TBL_NM_P1*/
	public final EZDCStringItem              xxTblNm_P1;

    /** XX_TBL_CD_COL_NM_P1*/
	public final EZDCStringItem              xxTblCdColNm_P1;

    /** XX_TBL_NM_COL_NM_P1*/
	public final EZDCStringItem              xxTblNmColNm_P1;

    /** XX_TBL_SORT_COL_NM_P1*/
	public final EZDCStringItem              xxTblSortColNm_P1;

    /** XX_SCR_NM_P1*/
	public final EZDCStringItem              xxScrNm_P1;

    /** XX_HDR_CD_LB_NM_P1*/
	public final EZDCStringItem              xxHdrCdLbNm_P1;

    /** XX_HDR_NM_LB_NM_P1*/
	public final EZDCStringItem              xxHdrNmLbNm_P1;

    /** XX_DTL_CD_LB_NM_P1*/
	public final EZDCStringItem              xxDtlCdLbNm_P1;

    /** XX_DTL_NM_LB_NM_P1*/
	public final EZDCStringItem              xxDtlNmLbNm_P1;

    /** XX_COND_CD_P1*/
	public final EZDCStringItem              xxCondCd_P1;

    /** XX_COND_NM_P1*/
	public final EZDCStringItem              xxCondNm_P1;

    /** XX_REC_HIST_CRAT_TS*/
	public final EZDCStringItem              xxRecHistCratTs;

    /** XX_REC_HIST_CRAT_BY_NM*/
	public final EZDCStringItem              xxRecHistCratByNm;

    /** XX_REC_HIST_UPD_TS*/
	public final EZDCStringItem              xxRecHistUpdTs;

    /** XX_REC_HIST_UPD_BY_NM*/
	public final EZDCStringItem              xxRecHistUpdByNm;

    /** XX_REC_HIST_TBL_NM*/
	public final EZDCStringItem              xxRecHistTblNm;


	/**
	 * NPAL1070CMsg is constructor.
	 * The initialization when the instance of NPAL1070CMsg is generated.
	 */
	public NPAL1070CMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1070CMsg is constructor.
	 * The initialization when the instance of NPAL1070CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1070CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		mrpInfoPk = (EZDCBigDecimalItem)newItem("mrpInfoPk");
		mrpInfoPk_IM = (EZDCBigDecimalItem)newItem("mrpInfoPk_IM");
		mrpPlnNm_H1 = (EZDCStringItem)newItem("mrpPlnNm_H1");
		mdseCd_H2 = (EZDCStringItem)newItem("mdseCd_H2");
		rtlWhCd_H3 = (EZDCStringItem)newItem("rtlWhCd_H3");
		rtlSwhCd_H4 = (EZDCStringItem)newItem("rtlSwhCd_H4");
		rtlWhCatgDescTxt = (EZDCStringItem)newItem("rtlWhCatgDescTxt");
		coaMdseTpCd = (EZDCStringItem)newItem("coaMdseTpCd");
		coaProdCd = (EZDCStringItem)newItem("coaProdCd");
		xxMsgTxt = (EZDCStringItem)newItem("xxMsgTxt");
		xxYesNoCd = (EZDCStringItem)newItem("xxYesNoCd");
		mrpPlnNm = (EZDCStringItem)newItem("mrpPlnNm");
		mdseCd = (EZDCStringItem)newItem("mdseCd");
		mdseDescShortTxt = (EZDCStringItem)newItem("mdseDescShortTxt");
		mdseTpCd = (EZDCStringItem)newItem("mdseTpCd");
		rtlWhCatgCd_PD = (EZDCStringItemArray)newItemArray("rtlWhCatgCd_PD");
		rtlWhCatgDescTxt_PD = (EZDCStringItemArray)newItemArray("rtlWhCatgDescTxt_PD");
		rtlWhCatgCd_SL = (EZDCStringItem)newItem("rtlWhCatgCd_SL");
		locTpCd = (EZDCStringItem)newItem("locTpCd");
		rtlWhCd = (EZDCStringItem)newItem("rtlWhCd");
		rtlWhNm_W1 = (EZDCStringItem)newItem("rtlWhNm_W1");
		rtlSwhCd = (EZDCStringItem)newItem("rtlSwhCd");
		rtlSwhNm_S1 = (EZDCStringItem)newItem("rtlSwhNm_S1");
		whMgrPsnCd = (EZDCStringItem)newItem("whMgrPsnCd");
		fullPsnNm = (EZDCStringItem)newItem("fullPsnNm");
		procrTpCd_PD = (EZDCStringItemArray)newItemArray("procrTpCd_PD");
		procrTpDescTxt_PD = (EZDCStringItemArray)newItemArray("procrTpDescTxt_PD");
		procrTpCd_SL = (EZDCStringItem)newItem("procrTpCd_SL");
		calcOrdProcCd_C1 = (EZDCStringItem)newItem("calcOrdProcCd_C1");
		srcRtlWhCd = (EZDCStringItem)newItem("srcRtlWhCd");
		rtlWhNm_W2 = (EZDCStringItem)newItem("rtlWhNm_W2");
		srcRtlSwhCd = (EZDCStringItem)newItem("srcRtlSwhCd");
		rtlSwhNm_S2 = (EZDCStringItem)newItem("rtlSwhNm_S2");
		mrpEnblFlg = (EZDCStringItem)newItem("mrpEnblFlg");
		calcOrdProcCd = (EZDCStringItem)newItem("calcOrdProcCd");
		rplshDlyFlg = (EZDCStringItem)newItem("rplshDlyFlg");
		rplshMonFlg = (EZDCStringItem)newItem("rplshMonFlg");
		rplshTueFlg = (EZDCStringItem)newItem("rplshTueFlg");
		rplshWedFlg = (EZDCStringItem)newItem("rplshWedFlg");
		rplshThuFlg = (EZDCStringItem)newItem("rplshThuFlg");
		rplshFriFlg = (EZDCStringItem)newItem("rplshFriFlg");
		xxSelFlg = (EZDCStringItem)newItem("xxSelFlg");
		xxWrnSkipFlg_SB = (EZDCStringItem)newItem("xxWrnSkipFlg_SB");
		calcOrdProcCd_C2 = (EZDCStringItem)newItem("calcOrdProcCd_C2");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxComnColOrdTxt = (EZDCStringItem)newItem("xxComnColOrdTxt");
		xxNum = (EZDCBigDecimalItem)newItem("xxNum");
		procrTpCd_AP = (EZDCStringItemArray)newItemArray("procrTpCd_AP");
		procrTpDescTxt_AP = (EZDCStringItemArray)newItemArray("procrTpDescTxt_AP");
		A = (business.blap.NPAL1070.NPAL1070_ACMsgArray)newMsgArray("A");
		xxFileData_UP = (EZDCMimeSourceItem)newItem("xxFileData_UP");
		B = (business.blap.NPAL1070.NPAL1070_BCMsgArray)newMsgArray("B");
		xxFileData_DW = (EZDCMimeSourceItem)newItem("xxFileData_DW");
		xxPopPrm_EV = (EZDCStringItem)newItem("xxPopPrm_EV");
		P = (business.blap.NPAL1070.NPAL1070_PCMsgArray)newMsgArray("P");
		X = (business.blap.NPAL1070.NPAL1070_XCMsgArray)newMsgArray("X");
		xxPopPrm_M0 = (EZDCStringItem)newItem("xxPopPrm_M0");
		xxPopPrm_M1 = (EZDCStringItem)newItem("xxPopPrm_M1");
		xxPopPrm_M2 = (EZDCStringItem)newItem("xxPopPrm_M2");
		xxPopPrm_M3 = (EZDCStringItem)newItem("xxPopPrm_M3");
		xxPopPrm_M4 = (EZDCStringItem)newItem("xxPopPrm_M4");
		xxPopPrm_M5 = (EZDCStringItem)newItem("xxPopPrm_M5");
		xxPopPrm_M6 = (EZDCStringItem)newItem("xxPopPrm_M6");
		xxPopPrm_M7 = (EZDCStringItem)newItem("xxPopPrm_M7");
		xxPopPrm_M8 = (EZDCStringItem)newItem("xxPopPrm_M8");
		xxPopPrm_M9 = (EZDCStringItem)newItem("xxPopPrm_M9");
		xxPopPrm_C0 = (EZDCStringItem)newItem("xxPopPrm_C0");
		xxPopPrm_C1 = (EZDCStringItem)newItem("xxPopPrm_C1");
		xxPopPrm_C2 = (EZDCStringItem)newItem("xxPopPrm_C2");
		xxPopPrm_C3 = (EZDCStringItem)newItem("xxPopPrm_C3");
		xxPopPrm_C4 = (EZDCStringItem)newItem("xxPopPrm_C4");
		xxTblNm_P1 = (EZDCStringItem)newItem("xxTblNm_P1");
		xxTblCdColNm_P1 = (EZDCStringItem)newItem("xxTblCdColNm_P1");
		xxTblNmColNm_P1 = (EZDCStringItem)newItem("xxTblNmColNm_P1");
		xxTblSortColNm_P1 = (EZDCStringItem)newItem("xxTblSortColNm_P1");
		xxScrNm_P1 = (EZDCStringItem)newItem("xxScrNm_P1");
		xxHdrCdLbNm_P1 = (EZDCStringItem)newItem("xxHdrCdLbNm_P1");
		xxHdrNmLbNm_P1 = (EZDCStringItem)newItem("xxHdrNmLbNm_P1");
		xxDtlCdLbNm_P1 = (EZDCStringItem)newItem("xxDtlCdLbNm_P1");
		xxDtlNmLbNm_P1 = (EZDCStringItem)newItem("xxDtlNmLbNm_P1");
		xxCondCd_P1 = (EZDCStringItem)newItem("xxCondCd_P1");
		xxCondNm_P1 = (EZDCStringItem)newItem("xxCondNm_P1");
		xxRecHistCratTs = (EZDCStringItem)newItem("xxRecHistCratTs");
		xxRecHistCratByNm = (EZDCStringItem)newItem("xxRecHistCratByNm");
		xxRecHistUpdTs = (EZDCStringItem)newItem("xxRecHistUpdTs");
		xxRecHistUpdByNm = (EZDCStringItem)newItem("xxRecHistUpdByNm");
		xxRecHistTblNm = (EZDCStringItem)newItem("xxRecHistTblNm");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1070CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1070CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"mrpInfoPk", "mrpInfoPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mrpInfoPk_IM", "mrpInfoPk_IM", "IM", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mrpPlnNm_H1", "mrpPlnNm_H1", "H1", null, TYPE_HANKAKUEISU, "70", null},
	{"mdseCd_H2", "mdseCd_H2", "H2", null, TYPE_HANKAKUEISU, "16", null},
	{"rtlWhCd_H3", "rtlWhCd_H3", "H3", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhCd_H4", "rtlSwhCd_H4", "H4", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhCatgDescTxt", "rtlWhCatgDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"coaMdseTpCd", "coaMdseTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"coaProdCd", "coaProdCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"xxMsgTxt", "xxMsgTxt", null, null, TYPE_HANKAKUEISU, "300", null},
	{"xxYesNoCd", "xxYesNoCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"mrpPlnNm", "mrpPlnNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"mdseTpCd", "mdseTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
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
	{"calcOrdProcCd_C1", "calcOrdProcCd_C1", "C1", null, TYPE_HANKAKUEISU, "1", null},
	{"srcRtlWhCd", "srcRtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_W2", "rtlWhNm_W2", "W2", null, TYPE_HANKAKUEISU, "30", null},
	{"srcRtlSwhCd", "srcRtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_S2", "rtlSwhNm_S2", "S2", null, TYPE_HANKAKUEISU, "30", null},
	{"mrpEnblFlg", "mrpEnblFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"calcOrdProcCd", "calcOrdProcCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"rplshDlyFlg", "rplshDlyFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"rplshMonFlg", "rplshMonFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"rplshTueFlg", "rplshTueFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"rplshWedFlg", "rplshWedFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"rplshThuFlg", "rplshThuFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"rplshFriFlg", "rplshFriFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSelFlg", "xxSelFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxWrnSkipFlg_SB", "xxWrnSkipFlg_SB", "SB", null, TYPE_HANKAKUEISU, "1", null},
	{"calcOrdProcCd_C2", "calcOrdProcCd_C2", "C2", null, TYPE_HANKAKUEISU, "1", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxNum", "xxNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"procrTpCd_AP", "procrTpCd_AP", "AP", "99", TYPE_HANKAKUEISU, "2", null},
	{"procrTpDescTxt_AP", "procrTpDescTxt_AP", "AP", "99", TYPE_HANKAKUEISU, "50", null},
	{"A", "A", null, "20", "business.blap.NPAL1070.NPAL1070_ACMsgArray", null, null},
	
	{"xxFileData_UP", "xxFileData_UP", "UP", null, TYPE_UPLOAD, null, null},
	{"B", "B", null, "20", "business.blap.NPAL1070.NPAL1070_BCMsgArray", null, null},
	
	{"xxFileData_DW", "xxFileData_DW", "DW", null, TYPE_UPLOAD, null, null},
	{"xxPopPrm_EV", "xxPopPrm_EV", "EV", null, TYPE_HANKAKUEISU, "300", null},
	{"P", "P", null, "22", "business.blap.NPAL1070.NPAL1070_PCMsgArray", null, null},
	
	{"X", "X", null, "30", "business.blap.NPAL1070.NPAL1070_XCMsgArray", null, null},
	
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
	{"xxPopPrm_C0", "xxPopPrm_C0", "C0", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_C1", "xxPopPrm_C1", "C1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_C2", "xxPopPrm_C2", "C2", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_C3", "xxPopPrm_C3", "C3", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_C4", "xxPopPrm_C4", "C4", null, TYPE_HANKAKUEISU, "300", null},
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
	{"xxRecHistCratTs", "xxRecHistCratTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm", "xxRecHistCratByNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs", "xxRecHistUpdTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm", "xxRecHistUpdByNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm", "xxRecHistTblNm", null, null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"MRP_INFO_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpInfoPk
        {"MRP_INFO_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpInfoPk_IM
        {"MRP_PLN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpPlnNm_H1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_H2
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_H3
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_H4
        {"RTL_WH_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgDescTxt
        {"COA_MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd
        {"XX_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgTxt
        {"XX_YES_NO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd
        {"MRP_PLN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpPlnNm
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseTpCd
        {"RTL_WH_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgCd_PD
        {"RTL_WH_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgDescTxt_PD
        {"RTL_WH_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgCd_SL
        {"LOC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locTpCd
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_W1
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_S1
        {"WH_MGR_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whMgrPsnCd
        {"FULL_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fullPsnNm
        {"PROCR_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpCd_PD
        {"PROCR_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpDescTxt_PD
        {"PROCR_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpCd_SL
        {"CALC_ORD_PROC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//calcOrdProcCd_C1
        {"SRC_RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlWhCd
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_W2
        {"SRC_RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlSwhCd
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_S2
        {"MRP_ENBL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpEnblFlg
        {"CALC_ORD_PROC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//calcOrdProcCd
        {"RPLSH_DLY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshDlyFlg
        {"RPLSH_MON_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshMonFlg
        {"RPLSH_TUE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshTueFlg
        {"RPLSH_WED_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshWedFlg
        {"RPLSH_THU_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshThuFlg
        {"RPLSH_FRI_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshFriFlg
        {"XX_SEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelFlg
        {"XX_WRN_SKIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWrnSkipFlg_SB
        {"CALC_ORD_PROC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//calcOrdProcCd_C2
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum
        {"PROCR_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpCd_AP
        {"PROCR_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpDescTxt_AP
		null,	//A
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_UP
		null,	//B
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_DW
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_EV
		null,	//P
		null,	//X
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M0
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M2
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M3
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M4
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M5
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M6
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M7
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M8
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_M9
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_C0
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_C1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_C2
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_C3
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_C4
        {"XX_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNm_P1
        {"XX_TBL_CD_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblCdColNm_P1
        {"XX_TBL_NM_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNmColNm_P1
        {"XX_TBL_SORT_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblSortColNm_P1
        {"XX_SCR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm_P1
        {"XX_HDR_CD_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrCdLbNm_P1
        {"XX_HDR_NM_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrNmLbNm_P1
        {"XX_DTL_CD_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCdLbNm_P1
        {"XX_DTL_NM_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlNmLbNm_P1
        {"XX_COND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCondCd_P1
        {"XX_COND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCondNm_P1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm
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

