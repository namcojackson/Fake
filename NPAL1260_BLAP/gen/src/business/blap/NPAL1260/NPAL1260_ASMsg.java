//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20231025163815000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1260_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1260;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1260 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1260_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRCH_REQ_NUM_D1*/
	public final EZDSStringItem              prchReqNum_D1;

    /** PRCH_REQ_LINE_NUM_D1*/
	public final EZDSStringItem              prchReqLineNum_D1;

    /** PRCH_REQ_LINE_SUB_NUM_D1*/
	public final EZDSBigDecimalItem              prchReqLineSubNum_D1;

    /** PRCH_REQ_TP_DESC_TXT_D1*/
	public final EZDSStringItem              prchReqTpDescTxt_D1;

    /** PRCH_REQ_LINE_TP_DESC_TXT_D1*/
	public final EZDSStringItem              prchReqLineTpDescTxt_D1;

    /** MDSE_CD_D1*/
	public final EZDSStringItem              mdseCd_D1;

    /** MDSE_DESC_SHORT_TXT_D1*/
	public final EZDSStringItem              mdseDescShortTxt_D1;

    /** PRCH_REQ_DISP_QTY_D1*/
	public final EZDSBigDecimalItem              prchReqDispQty_D1;

    /** PROCR_TP_DESC_TXT_D1*/
	public final EZDSStringItem              procrTpDescTxt_D1;

    /** SRC_RTL_WH_CD_D1*/
	public final EZDSStringItem              srcRtlWhCd_D1;

    /** RTL_WH_NM_D1*/
	public final EZDSStringItem              rtlWhNm_D1;

    /** SRC_RTL_SWH_CD_D1*/
	public final EZDSStringItem              srcRtlSwhCd_D1;

    /** RTL_SWH_NM_D1*/
	public final EZDSStringItem              rtlSwhNm_D1;

    /** DEST_RTL_WH_CD_D1*/
	public final EZDSStringItem              destRtlWhCd_D1;

    /** RTL_WH_NM_D2*/
	public final EZDSStringItem              rtlWhNm_D2;

    /** DEST_RTL_SWH_CD_D1*/
	public final EZDSStringItem              destRtlSwhCd_D1;

    /** RTL_SWH_NM_D2*/
	public final EZDSStringItem              rtlSwhNm_D2;

    /** PRCH_REQ_LINE_STS_DESC_TXT_D1*/
	public final EZDSStringItem              prchReqLineStsDescTxt_D1;

    /** ORD_QTY_D1*/
	public final EZDSBigDecimalItem              ordQty_D1;

    /** SHIP_QTY_D1*/
	public final EZDSBigDecimalItem              shipQty_D1;

    /** RWS_PUT_AWAY_QTY_D1*/
	public final EZDSBigDecimalItem              rwsPutAwayQty_D1;

    /** ORD_QTY_D2*/
	public final EZDSBigDecimalItem              ordQty_D2;

    /** PRCH_REQ_FRZ_FLG_D1*/
	public final EZDSStringItem              prchReqFrzFlg_D1;

    /** INSRC_FLG_D1*/
	public final EZDSStringItem              insrcFlg_D1;

    /** PO_CRAT_FLG_D1*/
	public final EZDSStringItem              poCratFlg_D1;

    /** ZN_FLG_D1*/
	public final EZDSStringItem              znFlg_D1;

    /** PRCH_REQ_CANC_QTY_D1*/
	public final EZDSBigDecimalItem              prchReqCancQty_D1;

    /** ORIG_PRCH_REQ_LINE_NUM_D1*/
	public final EZDSStringItem              origPrchReqLineNum_D1;

    /** XX_ADD_INV_NUM_TXT_D1*/
	public final EZDSStringItem              xxAddInvNumTxt_D1;

    /** ORIG_PRCH_REQ_LINE_SUB_NUM_D1*/
	public final EZDSBigDecimalItem              origPrchReqLineSubNum_D1;

    /** PRCH_REQ_CRAT_DT_D1*/
	public final EZDSDateItem              prchReqCratDt_D1;

    /** PRCH_REQ_CRAT_TM_D1*/
	public final EZDSStringItem              prchReqCratTm_D1;

    /** XX_POP_PRM_D1*/
	public final EZDSStringItem              xxPopPrm_D1;

    /** RQST_RCV_DT_D1*/
	public final EZDSDateItem              rqstRcvDt_D1;

    /** RQST_RCV_DISP_TM_D1*/
	public final EZDSStringItem              rqstRcvDispTm_D1;

    /** XX_POP_PRM_D2*/
	public final EZDSStringItem              xxPopPrm_D2;

    /** SHIP_DT_TM_TS_D1*/
	public final EZDSStringItem              shipDtTmTs_D1;

    /** XX_POP_PRM_D3*/
	public final EZDSStringItem              xxPopPrm_D3;

    /** RWS_CLO_DT_TM_TS_D1*/
	public final EZDSStringItem              rwsCloDtTmTs_D1;

    /** XX_POP_PRM_D4*/
	public final EZDSStringItem              xxPopPrm_D4;

    /** ORD_LAST_UPD_TS_D1*/
	public final EZDSStringItem              ordLastUpdTs_D1;

    /** XX_POP_PRM_D5*/
	public final EZDSStringItem              xxPopPrm_D5;

    /** LINE_BIZ_TP_DESC_TXT_D1*/
	public final EZDSStringItem              lineBizTpDescTxt_D1;

    /** PRCH_REQ_SRC_TP_DESC_TXT_D1*/
	public final EZDSStringItem              prchReqSrcTpDescTxt_D1;

    /** FSR_NUM_D1*/
	public final EZDSStringItem              fsrNum_D1;

    /** SVC_TASK_NUM_D1*/
	public final EZDSStringItem              svcTaskNum_D1;

    /** SVC_MACH_SER_NUM_D1*/
	public final EZDSStringItem              svcMachSerNum_D1;

    /** PO_ORD_NUM_D1*/
	public final EZDSStringItem              poOrdNum_D1;

    /** VND_SO_NUM_D1*/
	public final EZDSStringItem              vndSoNum_D1;

    /** RWS_REF_NUM_D1*/
	public final EZDSStringItem              rwsRefNum_D1;

    /** PRCH_REQ_NUM_D2*/
	public final EZDSStringItem              prchReqNum_D2;

    /** XX_POP_PRM_D7*/
	public final EZDSStringItem              xxPopPrm_D7;

    /** XX_POP_PRM_D6*/
	public final EZDSStringItem              xxPopPrm_D6;

    /** PRNT_VND_CD_D1*/
	public final EZDSStringItem              prntVndCd_D1;

    /** PRNT_VND_NM_D1*/
	public final EZDSStringItem              prntVndNm_D1;

    /** VND_CD_D1*/
	public final EZDSStringItem              vndCd_D1;

    /** LOC_NM_D1*/
	public final EZDSStringItem              locNm_D1;

    /** SHIP_TO_CUST_CD_D1*/
	public final EZDSStringItem              shipToCustCd_D1;

    /** DS_ACCT_NM_D1*/
	public final EZDSStringItem              dsAcctNm_D1;

    /** TECH_TOC_CD_D1*/
	public final EZDSStringItem              techTocCd_D1;

    /** TECH_NM_D1*/
	public final EZDSStringItem              techNm_D1;

    /** CARR_NM_D1*/
	public final EZDSStringItem              carrNm_D1;

    /** SHPG_SVC_LVL_NM_D1*/
	public final EZDSStringItem              shpgSvcLvlNm_D1;

    /** PRO_NUM_D1*/
	public final EZDSStringItem              proNum_D1;

    /** PRCH_REQ_LINE_TP_CD_D1*/
	public final EZDSStringItem              prchReqLineTpCd_D1;


	/**
	 * NPAL1260_ASMsg is constructor.
	 * The initialization when the instance of NPAL1260_ASMsg is generated.
	 */
	public NPAL1260_ASMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1260_ASMsg is constructor.
	 * The initialization when the instance of NPAL1260_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1260_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prchReqNum_D1 = (EZDSStringItem)newItem("prchReqNum_D1");
		prchReqLineNum_D1 = (EZDSStringItem)newItem("prchReqLineNum_D1");
		prchReqLineSubNum_D1 = (EZDSBigDecimalItem)newItem("prchReqLineSubNum_D1");
		prchReqTpDescTxt_D1 = (EZDSStringItem)newItem("prchReqTpDescTxt_D1");
		prchReqLineTpDescTxt_D1 = (EZDSStringItem)newItem("prchReqLineTpDescTxt_D1");
		mdseCd_D1 = (EZDSStringItem)newItem("mdseCd_D1");
		mdseDescShortTxt_D1 = (EZDSStringItem)newItem("mdseDescShortTxt_D1");
		prchReqDispQty_D1 = (EZDSBigDecimalItem)newItem("prchReqDispQty_D1");
		procrTpDescTxt_D1 = (EZDSStringItem)newItem("procrTpDescTxt_D1");
		srcRtlWhCd_D1 = (EZDSStringItem)newItem("srcRtlWhCd_D1");
		rtlWhNm_D1 = (EZDSStringItem)newItem("rtlWhNm_D1");
		srcRtlSwhCd_D1 = (EZDSStringItem)newItem("srcRtlSwhCd_D1");
		rtlSwhNm_D1 = (EZDSStringItem)newItem("rtlSwhNm_D1");
		destRtlWhCd_D1 = (EZDSStringItem)newItem("destRtlWhCd_D1");
		rtlWhNm_D2 = (EZDSStringItem)newItem("rtlWhNm_D2");
		destRtlSwhCd_D1 = (EZDSStringItem)newItem("destRtlSwhCd_D1");
		rtlSwhNm_D2 = (EZDSStringItem)newItem("rtlSwhNm_D2");
		prchReqLineStsDescTxt_D1 = (EZDSStringItem)newItem("prchReqLineStsDescTxt_D1");
		ordQty_D1 = (EZDSBigDecimalItem)newItem("ordQty_D1");
		shipQty_D1 = (EZDSBigDecimalItem)newItem("shipQty_D1");
		rwsPutAwayQty_D1 = (EZDSBigDecimalItem)newItem("rwsPutAwayQty_D1");
		ordQty_D2 = (EZDSBigDecimalItem)newItem("ordQty_D2");
		prchReqFrzFlg_D1 = (EZDSStringItem)newItem("prchReqFrzFlg_D1");
		insrcFlg_D1 = (EZDSStringItem)newItem("insrcFlg_D1");
		poCratFlg_D1 = (EZDSStringItem)newItem("poCratFlg_D1");
		znFlg_D1 = (EZDSStringItem)newItem("znFlg_D1");
		prchReqCancQty_D1 = (EZDSBigDecimalItem)newItem("prchReqCancQty_D1");
		origPrchReqLineNum_D1 = (EZDSStringItem)newItem("origPrchReqLineNum_D1");
		xxAddInvNumTxt_D1 = (EZDSStringItem)newItem("xxAddInvNumTxt_D1");
		origPrchReqLineSubNum_D1 = (EZDSBigDecimalItem)newItem("origPrchReqLineSubNum_D1");
		prchReqCratDt_D1 = (EZDSDateItem)newItem("prchReqCratDt_D1");
		prchReqCratTm_D1 = (EZDSStringItem)newItem("prchReqCratTm_D1");
		xxPopPrm_D1 = (EZDSStringItem)newItem("xxPopPrm_D1");
		rqstRcvDt_D1 = (EZDSDateItem)newItem("rqstRcvDt_D1");
		rqstRcvDispTm_D1 = (EZDSStringItem)newItem("rqstRcvDispTm_D1");
		xxPopPrm_D2 = (EZDSStringItem)newItem("xxPopPrm_D2");
		shipDtTmTs_D1 = (EZDSStringItem)newItem("shipDtTmTs_D1");
		xxPopPrm_D3 = (EZDSStringItem)newItem("xxPopPrm_D3");
		rwsCloDtTmTs_D1 = (EZDSStringItem)newItem("rwsCloDtTmTs_D1");
		xxPopPrm_D4 = (EZDSStringItem)newItem("xxPopPrm_D4");
		ordLastUpdTs_D1 = (EZDSStringItem)newItem("ordLastUpdTs_D1");
		xxPopPrm_D5 = (EZDSStringItem)newItem("xxPopPrm_D5");
		lineBizTpDescTxt_D1 = (EZDSStringItem)newItem("lineBizTpDescTxt_D1");
		prchReqSrcTpDescTxt_D1 = (EZDSStringItem)newItem("prchReqSrcTpDescTxt_D1");
		fsrNum_D1 = (EZDSStringItem)newItem("fsrNum_D1");
		svcTaskNum_D1 = (EZDSStringItem)newItem("svcTaskNum_D1");
		svcMachSerNum_D1 = (EZDSStringItem)newItem("svcMachSerNum_D1");
		poOrdNum_D1 = (EZDSStringItem)newItem("poOrdNum_D1");
		vndSoNum_D1 = (EZDSStringItem)newItem("vndSoNum_D1");
		rwsRefNum_D1 = (EZDSStringItem)newItem("rwsRefNum_D1");
		prchReqNum_D2 = (EZDSStringItem)newItem("prchReqNum_D2");
		xxPopPrm_D7 = (EZDSStringItem)newItem("xxPopPrm_D7");
		xxPopPrm_D6 = (EZDSStringItem)newItem("xxPopPrm_D6");
		prntVndCd_D1 = (EZDSStringItem)newItem("prntVndCd_D1");
		prntVndNm_D1 = (EZDSStringItem)newItem("prntVndNm_D1");
		vndCd_D1 = (EZDSStringItem)newItem("vndCd_D1");
		locNm_D1 = (EZDSStringItem)newItem("locNm_D1");
		shipToCustCd_D1 = (EZDSStringItem)newItem("shipToCustCd_D1");
		dsAcctNm_D1 = (EZDSStringItem)newItem("dsAcctNm_D1");
		techTocCd_D1 = (EZDSStringItem)newItem("techTocCd_D1");
		techNm_D1 = (EZDSStringItem)newItem("techNm_D1");
		carrNm_D1 = (EZDSStringItem)newItem("carrNm_D1");
		shpgSvcLvlNm_D1 = (EZDSStringItem)newItem("shpgSvcLvlNm_D1");
		proNum_D1 = (EZDSStringItem)newItem("proNum_D1");
		prchReqLineTpCd_D1 = (EZDSStringItem)newItem("prchReqLineTpCd_D1");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1260_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1260_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prchReqNum_D1", "prchReqNum_D1", "D1", null, TYPE_HANKAKUEISU, "8", null},
	{"prchReqLineNum_D1", "prchReqLineNum_D1", "D1", null, TYPE_HANKAKUEISU, "3", null},
	{"prchReqLineSubNum_D1", "prchReqLineSubNum_D1", "D1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"prchReqTpDescTxt_D1", "prchReqTpDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"prchReqLineTpDescTxt_D1", "prchReqLineTpDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseCd_D1", "mdseCd_D1", "D1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_D1", "mdseDescShortTxt_D1", "D1", null, TYPE_HANKAKUEISU, "250", null},
	{"prchReqDispQty_D1", "prchReqDispQty_D1", "D1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"procrTpDescTxt_D1", "procrTpDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"srcRtlWhCd_D1", "srcRtlWhCd_D1", "D1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_D1", "rtlWhNm_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"srcRtlSwhCd_D1", "srcRtlSwhCd_D1", "D1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_D1", "rtlSwhNm_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"destRtlWhCd_D1", "destRtlWhCd_D1", "D1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_D2", "rtlWhNm_D2", "D2", null, TYPE_HANKAKUEISU, "30", null},
	{"destRtlSwhCd_D1", "destRtlSwhCd_D1", "D1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_D2", "rtlSwhNm_D2", "D2", null, TYPE_HANKAKUEISU, "30", null},
	{"prchReqLineStsDescTxt_D1", "prchReqLineStsDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"ordQty_D1", "ordQty_D1", "D1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"shipQty_D1", "shipQty_D1", "D1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"rwsPutAwayQty_D1", "rwsPutAwayQty_D1", "D1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"ordQty_D2", "ordQty_D2", "D2", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"prchReqFrzFlg_D1", "prchReqFrzFlg_D1", "D1", null, TYPE_HANKAKUEISU, "1", null},
	{"insrcFlg_D1", "insrcFlg_D1", "D1", null, TYPE_HANKAKUEISU, "1", null},
	{"poCratFlg_D1", "poCratFlg_D1", "D1", null, TYPE_HANKAKUEISU, "1", null},
	{"znFlg_D1", "znFlg_D1", "D1", null, TYPE_HANKAKUEISU, "1", null},
	{"prchReqCancQty_D1", "prchReqCancQty_D1", "D1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"origPrchReqLineNum_D1", "origPrchReqLineNum_D1", "D1", null, TYPE_HANKAKUEISU, "3", null},
	{"xxAddInvNumTxt_D1", "xxAddInvNumTxt_D1", "D1", null, TYPE_HANKAKUEISU, "15", null},
	{"origPrchReqLineSubNum_D1", "origPrchReqLineSubNum_D1", "D1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"prchReqCratDt_D1", "prchReqCratDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"prchReqCratTm_D1", "prchReqCratTm_D1", "D1", null, TYPE_HANKAKUSUJI, "6", null},
	{"xxPopPrm_D1", "xxPopPrm_D1", "D1", null, TYPE_HANKAKUEISU, "300", null},
	{"rqstRcvDt_D1", "rqstRcvDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"rqstRcvDispTm_D1", "rqstRcvDispTm_D1", "D1", null, TYPE_HANKAKUSUJI, "6", null},
	{"xxPopPrm_D2", "xxPopPrm_D2", "D2", null, TYPE_HANKAKUEISU, "300", null},
	{"shipDtTmTs_D1", "shipDtTmTs_D1", "D1", null, TYPE_HANKAKUSUJI, "14", null},
	{"xxPopPrm_D3", "xxPopPrm_D3", "D3", null, TYPE_HANKAKUEISU, "300", null},
	{"rwsCloDtTmTs_D1", "rwsCloDtTmTs_D1", "D1", null, TYPE_HANKAKUSUJI, "14", null},
	{"xxPopPrm_D4", "xxPopPrm_D4", "D4", null, TYPE_HANKAKUEISU, "300", null},
	{"ordLastUpdTs_D1", "ordLastUpdTs_D1", "D1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxPopPrm_D5", "xxPopPrm_D5", "D5", null, TYPE_HANKAKUEISU, "300", null},
	{"lineBizTpDescTxt_D1", "lineBizTpDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"prchReqSrcTpDescTxt_D1", "prchReqSrcTpDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"fsrNum_D1", "fsrNum_D1", "D1", null, TYPE_HANKAKUEISU, "10", null},
	{"svcTaskNum_D1", "svcTaskNum_D1", "D1", null, TYPE_HANKAKUEISU, "10", null},
	{"svcMachSerNum_D1", "svcMachSerNum_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"poOrdNum_D1", "poOrdNum_D1", "D1", null, TYPE_HANKAKUEISU, "8", null},
	{"vndSoNum_D1", "vndSoNum_D1", "D1", null, TYPE_HANKAKUEISU, "20", null},
	{"rwsRefNum_D1", "rwsRefNum_D1", "D1", null, TYPE_HANKAKUEISU, "15", null},
	{"prchReqNum_D2", "prchReqNum_D2", "D2", null, TYPE_HANKAKUEISU, "8", null},
	{"xxPopPrm_D7", "xxPopPrm_D7", "D7", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_D6", "xxPopPrm_D6", "D6", null, TYPE_HANKAKUEISU, "300", null},
	{"prntVndCd_D1", "prntVndCd_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"prntVndNm_D1", "prntVndNm_D1", "D1", null, TYPE_HANKAKUEISU, "240", null},
	{"vndCd_D1", "vndCd_D1", "D1", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_D1", "locNm_D1", "D1", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToCustCd_D1", "shipToCustCd_D1", "D1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_D1", "dsAcctNm_D1", "D1", null, TYPE_HANKAKUEISU, "360", null},
	{"techTocCd_D1", "techTocCd_D1", "D1", null, TYPE_HANKAKUEISU, "8", null},
	{"techNm_D1", "techNm_D1", "D1", null, TYPE_HANKAKUEISU, "45", null},
	{"carrNm_D1", "carrNm_D1", "D1", null, TYPE_HANKAKUEISU, "60", null},
	{"shpgSvcLvlNm_D1", "shpgSvcLvlNm_D1", "D1", null, TYPE_HANKAKUEISU, "20", null},
	{"proNum_D1", "proNum_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"prchReqLineTpCd_D1", "prchReqLineTpCd_D1", "D1", null, TYPE_HANKAKUEISU, "4", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRCH_REQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqNum_D1
        {"PRCH_REQ_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqLineNum_D1
        {"PRCH_REQ_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqLineSubNum_D1
        {"PRCH_REQ_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqTpDescTxt_D1
        {"PRCH_REQ_LINE_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqLineTpDescTxt_D1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_D1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_D1
        {"PRCH_REQ_DISP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqDispQty_D1
        {"PROCR_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpDescTxt_D1
        {"SRC_RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlWhCd_D1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_D1
        {"SRC_RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlSwhCd_D1
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_D1
        {"DEST_RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//destRtlWhCd_D1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_D2
        {"DEST_RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//destRtlSwhCd_D1
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_D2
        {"PRCH_REQ_LINE_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqLineStsDescTxt_D1
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_D1
        {"SHIP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipQty_D1
        {"RWS_PUT_AWAY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsPutAwayQty_D1
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_D2
        {"PRCH_REQ_FRZ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqFrzFlg_D1
        {"INSRC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//insrcFlg_D1
        {"PO_CRAT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poCratFlg_D1
        {"ZN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//znFlg_D1
        {"PRCH_REQ_CANC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqCancQty_D1
        {"ORIG_PRCH_REQ_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origPrchReqLineNum_D1
        {"XX_ADD_INV_NUM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAddInvNumTxt_D1
        {"ORIG_PRCH_REQ_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origPrchReqLineSubNum_D1
        {"PRCH_REQ_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqCratDt_D1
        {"PRCH_REQ_CRAT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqCratTm_D1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_D1
        {"RQST_RCV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDt_D1
        {"RQST_RCV_DISP_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDispTm_D1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_D2
        {"SHIP_DT_TM_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipDtTmTs_D1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_D3
        {"RWS_CLO_DT_TM_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsCloDtTmTs_D1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_D4
        {"ORD_LAST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLastUpdTs_D1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_D5
        {"LINE_BIZ_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpDescTxt_D1
        {"PRCH_REQ_SRC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqSrcTpDescTxt_D1
        {"FSR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum_D1
        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum_D1
        {"SVC_MACH_SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachSerNum_D1
        {"PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdNum_D1
        {"VND_SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndSoNum_D1
        {"RWS_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsRefNum_D1
        {"PRCH_REQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqNum_D2
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_D7
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_D6
        {"PRNT_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndCd_D1
        {"PRNT_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndNm_D1
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_D1
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_D1
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_D1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_D1
        {"TECH_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techTocCd_D1
        {"TECH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm_D1
        {"CARR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrNm_D1
        {"SHPG_SVC_LVL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlNm_D1
        {"PRO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proNum_D1
        {"PRCH_REQ_LINE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqLineTpCd_D1
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

