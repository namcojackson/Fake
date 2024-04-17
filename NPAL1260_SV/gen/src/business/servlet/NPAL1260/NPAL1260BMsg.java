//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231025163903000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1260BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1260;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1260 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1260BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SRCH_OPT_PK_SE*/
	public final EZDBBigDecimalItem              srchOptPk_SE;

    /** SRCH_OPT_PK_CD*/
	public final EZDBBigDecimalItemArray              srchOptPk_CD;

    /** SRCH_OPT_NM_DI*/
	public final EZDBStringItemArray              srchOptNm_DI;

    /** SRCH_OPT_NM_TX*/
	public final EZDBStringItem              srchOptNm_TX;

    /** SRCH_OPT_USR_ID_U1*/
	public final EZDBStringItem              srchOptUsrId_U1;

    /** PRCH_REQ_NUM_H1*/
	public final EZDBStringItem              prchReqNum_H1;

    /** PRCH_REQ_TP_CD_SE*/
	public final EZDBStringItem              prchReqTpCd_SE;

    /** PRCH_REQ_TP_CD_CD*/
	public final EZDBStringItemArray              prchReqTpCd_CD;

    /** PRCH_REQ_TP_DESC_TXT_DI*/
	public final EZDBStringItemArray              prchReqTpDescTxt_DI;

    /** MDSE_CD_AC*/
	public final EZDBStringItem              mdseCd_AC;

    /** MDSE_CD_H1*/
	public final EZDBStringItem              mdseCd_H1;

    /** PRCH_REQ_LINE_TP_CD_SE*/
	public final EZDBStringItem              prchReqLineTpCd_SE;

    /** PRCH_REQ_LINE_TP_CD_CD*/
	public final EZDBStringItemArray              prchReqLineTpCd_CD;

    /** PRCH_REQ_LINE_TP_DESC_TXT_DI*/
	public final EZDBStringItemArray              prchReqLineTpDescTxt_DI;

    /** PRCH_REQ_LINE_STS_CD_SE*/
	public final EZDBStringItem              prchReqLineStsCd_SE;

    /** PRCH_REQ_LINE_STS_CD_CD*/
	public final EZDBStringItemArray              prchReqLineStsCd_CD;

    /** PRCH_REQ_LINE_STS_DESC_TXT_DI*/
	public final EZDBStringItemArray              prchReqLineStsDescTxt_DI;

    /** PRCH_REQ_CRAT_DT_HF*/
	public final EZDBDateItem              prchReqCratDt_HF;

    /** PRCH_REQ_CRAT_DT_HT*/
	public final EZDBDateItem              prchReqCratDt_HT;

    /** RQST_RCV_DT_HF*/
	public final EZDBDateItem              rqstRcvDt_HF;

    /** RQST_RCV_DT_HT*/
	public final EZDBDateItem              rqstRcvDt_HT;

    /** SHIP_DT_HF*/
	public final EZDBDateItem              shipDt_HF;

    /** SHIP_DT_HT*/
	public final EZDBDateItem              shipDt_HT;

    /** LINE_BIZ_TP_CD_SE*/
	public final EZDBStringItem              lineBizTpCd_SE;

    /** LINE_BIZ_TP_CD_CD*/
	public final EZDBStringItemArray              lineBizTpCd_CD;

    /** LINE_BIZ_TP_DESC_TXT_DI*/
	public final EZDBStringItemArray              lineBizTpDescTxt_DI;

    /** PRCH_REQ_SRC_TP_CD_SE*/
	public final EZDBStringItem              prchReqSrcTpCd_SE;

    /** PRCH_REQ_SRC_TP_CD_CD*/
	public final EZDBStringItemArray              prchReqSrcTpCd_CD;

    /** PRCH_REQ_SRC_TP_DESC_TXT_DI*/
	public final EZDBStringItemArray              prchReqSrcTpDescTxt_DI;

    /** FSR_NUM_H1*/
	public final EZDBStringItem              fsrNum_H1;

    /** SVC_TASK_NUM_H1*/
	public final EZDBStringItem              svcTaskNum_H1;

    /** SVC_MACH_SER_NUM_H1*/
	public final EZDBStringItem              svcMachSerNum_H1;

    /** PO_ORD_NUM_H1*/
	public final EZDBStringItem              poOrdNum_H1;

    /** RWS_REF_NUM_H1*/
	public final EZDBStringItem              rwsRefNum_H1;

    /** PRCH_REQ_NUM_H2*/
	public final EZDBStringItem              prchReqNum_H2;

    /** RTL_WH_CD_AC*/
	public final EZDBStringItem              rtlWhCd_AC;

    /** RTL_WH_CD_H1*/
	public final EZDBStringItem              rtlWhCd_H1;

    /** RTL_WH_NM_H1*/
	public final EZDBStringItem              rtlWhNm_H1;

    /** RTL_SWH_CD_H1*/
	public final EZDBStringItem              rtlSwhCd_H1;

    /** RTL_SWH_NM_H1*/
	public final EZDBStringItem              rtlSwhNm_H1;

    /** PRNT_VND_CD_AC*/
	public final EZDBStringItem              prntVndCd_AC;

    /** PRNT_VND_NM_H1*/
	public final EZDBStringItem              prntVndNm_H1;

    /** VND_CD_AC*/
	public final EZDBStringItem              vndCd_AC;

    /** LOC_NM_H1*/
	public final EZDBStringItem              locNm_H1;

    /** DEST_RTL_WH_CD_AC*/
	public final EZDBStringItem              destRtlWhCd_AC;

    /** DEST_RTL_WH_CD_H1*/
	public final EZDBStringItem              destRtlWhCd_H1;

    /** RTL_WH_NM_H2*/
	public final EZDBStringItem              rtlWhNm_H2;

    /** DEST_RTL_SWH_CD_H1*/
	public final EZDBStringItem              destRtlSwhCd_H1;

    /** RTL_SWH_NM_H2*/
	public final EZDBStringItem              rtlSwhNm_H2;

    /** PSN_CD_AC*/
	public final EZDBStringItem              psnCd_AC;

    /** TECH_NM_H1*/
	public final EZDBStringItem              techNm_H1;

    /** SHIP_TO_CUST_CD_AC*/
	public final EZDBStringItem              shipToCustCd_AC;

    /** DS_ACCT_NM_H1*/
	public final EZDBStringItem              dsAcctNm_H1;

    /** CARR_CD_AC*/
	public final EZDBStringItem              carrCd_AC;

    /** CARR_NM_H1*/
	public final EZDBStringItem              carrNm_H1;

    /** SHPG_SVC_LVL_CD_SE*/
	public final EZDBStringItem              shpgSvcLvlCd_SE;

    /** SHPG_SVC_LVL_CD_CD*/
	public final EZDBStringItemArray              shpgSvcLvlCd_CD;

    /** SHPG_SVC_LVL_DESC_TXT_DI*/
	public final EZDBStringItemArray              shpgSvcLvlDescTxt_DI;

    /** PRO_NUM_H1*/
	public final EZDBStringItem              proNum_H1;

    /** XX_CHK_BOX_H1*/
	public final EZDBStringItem              xxChkBox_H1;

    /** XX_CHK_BOX_H2*/
	public final EZDBStringItem              xxChkBox_H2;

    /** XX_CHK_BOX_H3*/
	public final EZDBStringItem              xxChkBox_H3;

    /** XX_CHK_BOX_H4*/
	public final EZDBStringItem              xxChkBox_H4;

    /** XX_CHK_BOX_H5*/
	public final EZDBStringItem              xxChkBox_H5;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.servlet.NPAL1260.NPAL1260_ABMsgArray              A;

    /** XX_POP_PRM_P0*/
	public final EZDBStringItem              xxPopPrm_P0;

    /** XX_POP_PRM_P1*/
	public final EZDBStringItem              xxPopPrm_P1;

    /** XX_POP_PRM_P2*/
	public final EZDBStringItem              xxPopPrm_P2;

    /** XX_POP_PRM_P3*/
	public final EZDBStringItem              xxPopPrm_P3;

    /** XX_POP_PRM_P4*/
	public final EZDBStringItem              xxPopPrm_P4;

    /** XX_POP_PRM_P5*/
	public final EZDBStringItem              xxPopPrm_P5;

    /** XX_POP_PRM_P6*/
	public final EZDBStringItem              xxPopPrm_P6;

    /** XX_POP_PRM_P7*/
	public final EZDBStringItem              xxPopPrm_P7;

    /** XX_POP_PRM_P8*/
	public final EZDBStringItem              xxPopPrm_P8;

    /** XX_POP_PRM_P9*/
	public final EZDBStringItem              xxPopPrm_P9;

    /** XX_POP_PRM_PA*/
	public final EZDBStringItem              xxPopPrm_PA;

    /** XX_UPLD_CSV_TEMP_DIR_TXT_PX*/
	public final EZDBStringItem              xxUpldCsvTempDirTxt_PX;

    /** EVENT_NM*/
	public final EZDBStringItem              eventNm;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDBStringItem              xxComnColOrdTxt;

    /** P*/
	public final business.servlet.NPAL1260.NPAL1260_PBMsgArray              P;

    /** Q*/
	public final business.servlet.NPAL1260.NPAL1260_QBMsgArray              Q;

    /** S*/
	public final business.servlet.NPAL1260.NPAL1260_SBMsgArray              S;

    /** R*/
	public final business.servlet.NPAL1260.NPAL1260_RBMsgArray              R;

    /** BILL_TO_CUST_CD_P1*/
	public final EZDBStringItem              billToCustCd_P1;

    /** BILL_TO_LOC_NM_P1*/
	public final EZDBStringItem              billToLocNm_P1;

    /** SELL_TO_CUST_CD_P1*/
	public final EZDBStringItem              sellToCustCd_P1;

    /** SELL_TO_LOC_NM_P1*/
	public final EZDBStringItem              sellToLocNm_P1;

    /** SHIP_TO_CUST_CD_P1*/
	public final EZDBStringItem              shipToCustCd_P1;

    /** SHIP_TO_LOC_NM_P1*/
	public final EZDBStringItem              shipToLocNm_P1;

    /** XX_TBL_NM_P2*/
	public final EZDBStringItem              xxTblNm_P2;

    /** XX_TBL_CD_COL_NM_P2*/
	public final EZDBStringItem              xxTblCdColNm_P2;

    /** XX_TBL_NM_COL_NM_P2*/
	public final EZDBStringItem              xxTblNmColNm_P2;

    /** XX_TBL_SORT_COL_NM_P2*/
	public final EZDBStringItem              xxTblSortColNm_P2;

    /** XX_SCR_NM_P2*/
	public final EZDBStringItem              xxScrNm_P2;

    /** XX_HDR_CD_LB_NM_P2*/
	public final EZDBStringItem              xxHdrCdLbNm_P2;

    /** XX_HDR_NM_LB_NM_P2*/
	public final EZDBStringItem              xxHdrNmLbNm_P2;

    /** XX_DTL_CD_LB_NM_P2*/
	public final EZDBStringItem              xxDtlCdLbNm_P2;

    /** XX_DTL_NM_LB_NM_P2*/
	public final EZDBStringItem              xxDtlNmLbNm_P2;

    /** XX_COND_CD_P2*/
	public final EZDBStringItem              xxCondCd_P2;

    /** XX_COND_NM_P2*/
	public final EZDBStringItem              xxCondNm_P2;

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;

    /** O*/
	public final business.servlet.NPAL1260.NPAL1260_OBMsgArray              O;


	/**
	 * NPAL1260BMsg is constructor.
	 * The initialization when the instance of NPAL1260BMsg is generated.
	 */
	public NPAL1260BMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1260BMsg is constructor.
	 * The initialization when the instance of NPAL1260BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1260BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		srchOptPk_SE = (EZDBBigDecimalItem)newItem("srchOptPk_SE");
		srchOptPk_CD = (EZDBBigDecimalItemArray)newItemArray("srchOptPk_CD");
		srchOptNm_DI = (EZDBStringItemArray)newItemArray("srchOptNm_DI");
		srchOptNm_TX = (EZDBStringItem)newItem("srchOptNm_TX");
		srchOptUsrId_U1 = (EZDBStringItem)newItem("srchOptUsrId_U1");
		prchReqNum_H1 = (EZDBStringItem)newItem("prchReqNum_H1");
		prchReqTpCd_SE = (EZDBStringItem)newItem("prchReqTpCd_SE");
		prchReqTpCd_CD = (EZDBStringItemArray)newItemArray("prchReqTpCd_CD");
		prchReqTpDescTxt_DI = (EZDBStringItemArray)newItemArray("prchReqTpDescTxt_DI");
		mdseCd_AC = (EZDBStringItem)newItem("mdseCd_AC");
		mdseCd_H1 = (EZDBStringItem)newItem("mdseCd_H1");
		prchReqLineTpCd_SE = (EZDBStringItem)newItem("prchReqLineTpCd_SE");
		prchReqLineTpCd_CD = (EZDBStringItemArray)newItemArray("prchReqLineTpCd_CD");
		prchReqLineTpDescTxt_DI = (EZDBStringItemArray)newItemArray("prchReqLineTpDescTxt_DI");
		prchReqLineStsCd_SE = (EZDBStringItem)newItem("prchReqLineStsCd_SE");
		prchReqLineStsCd_CD = (EZDBStringItemArray)newItemArray("prchReqLineStsCd_CD");
		prchReqLineStsDescTxt_DI = (EZDBStringItemArray)newItemArray("prchReqLineStsDescTxt_DI");
		prchReqCratDt_HF = (EZDBDateItem)newItem("prchReqCratDt_HF");
		prchReqCratDt_HT = (EZDBDateItem)newItem("prchReqCratDt_HT");
		rqstRcvDt_HF = (EZDBDateItem)newItem("rqstRcvDt_HF");
		rqstRcvDt_HT = (EZDBDateItem)newItem("rqstRcvDt_HT");
		shipDt_HF = (EZDBDateItem)newItem("shipDt_HF");
		shipDt_HT = (EZDBDateItem)newItem("shipDt_HT");
		lineBizTpCd_SE = (EZDBStringItem)newItem("lineBizTpCd_SE");
		lineBizTpCd_CD = (EZDBStringItemArray)newItemArray("lineBizTpCd_CD");
		lineBizTpDescTxt_DI = (EZDBStringItemArray)newItemArray("lineBizTpDescTxt_DI");
		prchReqSrcTpCd_SE = (EZDBStringItem)newItem("prchReqSrcTpCd_SE");
		prchReqSrcTpCd_CD = (EZDBStringItemArray)newItemArray("prchReqSrcTpCd_CD");
		prchReqSrcTpDescTxt_DI = (EZDBStringItemArray)newItemArray("prchReqSrcTpDescTxt_DI");
		fsrNum_H1 = (EZDBStringItem)newItem("fsrNum_H1");
		svcTaskNum_H1 = (EZDBStringItem)newItem("svcTaskNum_H1");
		svcMachSerNum_H1 = (EZDBStringItem)newItem("svcMachSerNum_H1");
		poOrdNum_H1 = (EZDBStringItem)newItem("poOrdNum_H1");
		rwsRefNum_H1 = (EZDBStringItem)newItem("rwsRefNum_H1");
		prchReqNum_H2 = (EZDBStringItem)newItem("prchReqNum_H2");
		rtlWhCd_AC = (EZDBStringItem)newItem("rtlWhCd_AC");
		rtlWhCd_H1 = (EZDBStringItem)newItem("rtlWhCd_H1");
		rtlWhNm_H1 = (EZDBStringItem)newItem("rtlWhNm_H1");
		rtlSwhCd_H1 = (EZDBStringItem)newItem("rtlSwhCd_H1");
		rtlSwhNm_H1 = (EZDBStringItem)newItem("rtlSwhNm_H1");
		prntVndCd_AC = (EZDBStringItem)newItem("prntVndCd_AC");
		prntVndNm_H1 = (EZDBStringItem)newItem("prntVndNm_H1");
		vndCd_AC = (EZDBStringItem)newItem("vndCd_AC");
		locNm_H1 = (EZDBStringItem)newItem("locNm_H1");
		destRtlWhCd_AC = (EZDBStringItem)newItem("destRtlWhCd_AC");
		destRtlWhCd_H1 = (EZDBStringItem)newItem("destRtlWhCd_H1");
		rtlWhNm_H2 = (EZDBStringItem)newItem("rtlWhNm_H2");
		destRtlSwhCd_H1 = (EZDBStringItem)newItem("destRtlSwhCd_H1");
		rtlSwhNm_H2 = (EZDBStringItem)newItem("rtlSwhNm_H2");
		psnCd_AC = (EZDBStringItem)newItem("psnCd_AC");
		techNm_H1 = (EZDBStringItem)newItem("techNm_H1");
		shipToCustCd_AC = (EZDBStringItem)newItem("shipToCustCd_AC");
		dsAcctNm_H1 = (EZDBStringItem)newItem("dsAcctNm_H1");
		carrCd_AC = (EZDBStringItem)newItem("carrCd_AC");
		carrNm_H1 = (EZDBStringItem)newItem("carrNm_H1");
		shpgSvcLvlCd_SE = (EZDBStringItem)newItem("shpgSvcLvlCd_SE");
		shpgSvcLvlCd_CD = (EZDBStringItemArray)newItemArray("shpgSvcLvlCd_CD");
		shpgSvcLvlDescTxt_DI = (EZDBStringItemArray)newItemArray("shpgSvcLvlDescTxt_DI");
		proNum_H1 = (EZDBStringItem)newItem("proNum_H1");
		xxChkBox_H1 = (EZDBStringItem)newItem("xxChkBox_H1");
		xxChkBox_H2 = (EZDBStringItem)newItem("xxChkBox_H2");
		xxChkBox_H3 = (EZDBStringItem)newItem("xxChkBox_H3");
		xxChkBox_H4 = (EZDBStringItem)newItem("xxChkBox_H4");
		xxChkBox_H5 = (EZDBStringItem)newItem("xxChkBox_H5");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.servlet.NPAL1260.NPAL1260_ABMsgArray)newMsgArray("A");
		xxPopPrm_P0 = (EZDBStringItem)newItem("xxPopPrm_P0");
		xxPopPrm_P1 = (EZDBStringItem)newItem("xxPopPrm_P1");
		xxPopPrm_P2 = (EZDBStringItem)newItem("xxPopPrm_P2");
		xxPopPrm_P3 = (EZDBStringItem)newItem("xxPopPrm_P3");
		xxPopPrm_P4 = (EZDBStringItem)newItem("xxPopPrm_P4");
		xxPopPrm_P5 = (EZDBStringItem)newItem("xxPopPrm_P5");
		xxPopPrm_P6 = (EZDBStringItem)newItem("xxPopPrm_P6");
		xxPopPrm_P7 = (EZDBStringItem)newItem("xxPopPrm_P7");
		xxPopPrm_P8 = (EZDBStringItem)newItem("xxPopPrm_P8");
		xxPopPrm_P9 = (EZDBStringItem)newItem("xxPopPrm_P9");
		xxPopPrm_PA = (EZDBStringItem)newItem("xxPopPrm_PA");
		xxUpldCsvTempDirTxt_PX = (EZDBStringItem)newItem("xxUpldCsvTempDirTxt_PX");
		eventNm = (EZDBStringItem)newItem("eventNm");
		xxComnColOrdTxt = (EZDBStringItem)newItem("xxComnColOrdTxt");
		P = (business.servlet.NPAL1260.NPAL1260_PBMsgArray)newMsgArray("P");
		Q = (business.servlet.NPAL1260.NPAL1260_QBMsgArray)newMsgArray("Q");
		S = (business.servlet.NPAL1260.NPAL1260_SBMsgArray)newMsgArray("S");
		R = (business.servlet.NPAL1260.NPAL1260_RBMsgArray)newMsgArray("R");
		billToCustCd_P1 = (EZDBStringItem)newItem("billToCustCd_P1");
		billToLocNm_P1 = (EZDBStringItem)newItem("billToLocNm_P1");
		sellToCustCd_P1 = (EZDBStringItem)newItem("sellToCustCd_P1");
		sellToLocNm_P1 = (EZDBStringItem)newItem("sellToLocNm_P1");
		shipToCustCd_P1 = (EZDBStringItem)newItem("shipToCustCd_P1");
		shipToLocNm_P1 = (EZDBStringItem)newItem("shipToLocNm_P1");
		xxTblNm_P2 = (EZDBStringItem)newItem("xxTblNm_P2");
		xxTblCdColNm_P2 = (EZDBStringItem)newItem("xxTblCdColNm_P2");
		xxTblNmColNm_P2 = (EZDBStringItem)newItem("xxTblNmColNm_P2");
		xxTblSortColNm_P2 = (EZDBStringItem)newItem("xxTblSortColNm_P2");
		xxScrNm_P2 = (EZDBStringItem)newItem("xxScrNm_P2");
		xxHdrCdLbNm_P2 = (EZDBStringItem)newItem("xxHdrCdLbNm_P2");
		xxHdrNmLbNm_P2 = (EZDBStringItem)newItem("xxHdrNmLbNm_P2");
		xxDtlCdLbNm_P2 = (EZDBStringItem)newItem("xxDtlCdLbNm_P2");

		xxDtlNmLbNm_P2 = (EZDBStringItem)newItem("xxDtlNmLbNm_P2");
		xxCondCd_P2 = (EZDBStringItem)newItem("xxCondCd_P2");
		xxCondNm_P2 = (EZDBStringItem)newItem("xxCondNm_P2");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		O = (business.servlet.NPAL1260.NPAL1260_OBMsgArray)newMsgArray("O");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1260BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1260BMsgArray();
	}

	/**
	 * Definition of Sub class for Schema Item Array(Basic data,Visible Field)
	 */
	private static class NPAL1260BMsgSchemaContents1 {

		/**
		 * Array of Schema Items in Sub class(Basic data)
		 */
		private static final String[][] BASE_CONTENTS = {
		//["key name", "variable", "suffix", "array length", "item type", "digits", "after decimal point degits"] <- This is how to define.
			{"srchOptPk_SE", "srchOptPk_SE", "SE", null, TYPE_SEISU_SYOSU, "28", "0"},
			{"srchOptPk_CD", "srchOptPk_CD", "CD", "99", TYPE_SEISU_SYOSU, "28", "0"},
			{"srchOptNm_DI", "srchOptNm_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
			{"srchOptNm_TX", "srchOptNm_TX", "TX", null, TYPE_HANKAKUEISU, "50", null},
			{"srchOptUsrId_U1", "srchOptUsrId_U1", "U1", null, TYPE_HANKAKUEISU, "16", null},
			{"prchReqNum_H1", "prchReqNum_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
			{"prchReqTpCd_SE", "prchReqTpCd_SE", "SE", null, TYPE_HANKAKUEISU, "4", null},
			{"prchReqTpCd_CD", "prchReqTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "4", null},
			{"prchReqTpDescTxt_DI", "prchReqTpDescTxt_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
			{"mdseCd_AC", "mdseCd_AC", "AC", null, TYPE_HANKAKUEISU, "16", null},
			{"mdseCd_H1", "mdseCd_H1", "H1", null, TYPE_HANKAKUEISU, "16", null},
			{"prchReqLineTpCd_SE", "prchReqLineTpCd_SE", "SE", null, TYPE_HANKAKUEISU, "4", null},
			{"prchReqLineTpCd_CD", "prchReqLineTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "4", null},
			{"prchReqLineTpDescTxt_DI", "prchReqLineTpDescTxt_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
			{"prchReqLineStsCd_SE", "prchReqLineStsCd_SE", "SE", null, TYPE_HANKAKUEISU, "2", null},
			{"prchReqLineStsCd_CD", "prchReqLineStsCd_CD", "CD", "99", TYPE_HANKAKUEISU, "2", null},
			{"prchReqLineStsDescTxt_DI", "prchReqLineStsDescTxt_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
			{"prchReqCratDt_HF", "prchReqCratDt_HF", "HF", null, TYPE_NENTSUKIHI, "8", null},
			{"prchReqCratDt_HT", "prchReqCratDt_HT", "HT", null, TYPE_NENTSUKIHI, "8", null},
			{"rqstRcvDt_HF", "rqstRcvDt_HF", "HF", null, TYPE_NENTSUKIHI, "8", null},
			{"rqstRcvDt_HT", "rqstRcvDt_HT", "HT", null, TYPE_NENTSUKIHI, "8", null},
			{"shipDt_HF", "shipDt_HF", "HF", null, TYPE_NENTSUKIHI, "8", null},
			{"shipDt_HT", "shipDt_HT", "HT", null, TYPE_NENTSUKIHI, "8", null},
			{"lineBizTpCd_SE", "lineBizTpCd_SE", "SE", null, TYPE_HANKAKUEISU, "8", null},
			{"lineBizTpCd_CD", "lineBizTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "8", null},
			{"lineBizTpDescTxt_DI", "lineBizTpDescTxt_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
			{"prchReqSrcTpCd_SE", "prchReqSrcTpCd_SE", "SE", null, TYPE_HANKAKUEISU, "2", null},
			{"prchReqSrcTpCd_CD", "prchReqSrcTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "2", null},
			{"prchReqSrcTpDescTxt_DI", "prchReqSrcTpDescTxt_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
			{"fsrNum_H1", "fsrNum_H1", "H1", null, TYPE_HANKAKUEISU, "10", null},
			{"svcTaskNum_H1", "svcTaskNum_H1", "H1", null, TYPE_HANKAKUEISU, "10", null},
			{"svcMachSerNum_H1", "svcMachSerNum_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
			{"poOrdNum_H1", "poOrdNum_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
			{"rwsRefNum_H1", "rwsRefNum_H1", "H1", null, TYPE_HANKAKUEISU, "15", null},
			{"prchReqNum_H2", "prchReqNum_H2", "H2", null, TYPE_HANKAKUEISU, "8", null},
			{"rtlWhCd_AC", "rtlWhCd_AC", "AC", null, TYPE_HANKAKUEISU, "20", null},
			{"rtlWhCd_H1", "rtlWhCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
			{"rtlWhNm_H1", "rtlWhNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
			{"rtlSwhCd_H1", "rtlSwhCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
			{"rtlSwhNm_H1", "rtlSwhNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
			{"prntVndCd_AC", "prntVndCd_AC", "AC", null, TYPE_HANKAKUEISU, "30", null},
			{"prntVndNm_H1", "prntVndNm_H1", "H1", null, TYPE_HANKAKUEISU, "240", null},
			{"vndCd_AC", "vndCd_AC", "AC", null, TYPE_HANKAKUEISU, "20", null},
			{"locNm_H1", "locNm_H1", "H1", null, TYPE_HANKAKUEISU, "60", null},
			{"destRtlWhCd_AC", "destRtlWhCd_AC", "AC", null, TYPE_HANKAKUEISU, "20", null},
			{"destRtlWhCd_H1", "destRtlWhCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
			{"rtlWhNm_H2", "rtlWhNm_H2", "H2", null, TYPE_HANKAKUEISU, "30", null},
			{"destRtlSwhCd_H1", "destRtlSwhCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
			{"rtlSwhNm_H2", "rtlSwhNm_H2", "H2", null, TYPE_HANKAKUEISU, "30", null},
			{"psnCd_AC", "psnCd_AC", "AC", null, TYPE_HANKAKUEISU, "8", null},
			{"techNm_H1", "techNm_H1", "H1", null, TYPE_HANKAKUEISU, "45", null},
			{"shipToCustCd_AC", "shipToCustCd_AC", "AC", null, TYPE_HANKAKUEISU, "20", null},
			{"dsAcctNm_H1", "dsAcctNm_H1", "H1", null, TYPE_HANKAKUEISU, "360", null},
			{"carrCd_AC", "carrCd_AC", "AC", null, TYPE_HANKAKUEISU, "20", null},
			{"carrNm_H1", "carrNm_H1", "H1", null, TYPE_HANKAKUEISU, "60", null},
			{"shpgSvcLvlCd_SE", "shpgSvcLvlCd_SE", "SE", null, TYPE_HANKAKUEISU, "2", null},
			{"shpgSvcLvlCd_CD", "shpgSvcLvlCd_CD", "CD", "99", TYPE_HANKAKUEISU, "2", null},
			{"shpgSvcLvlDescTxt_DI", "shpgSvcLvlDescTxt_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
			{"proNum_H1", "proNum_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
			{"xxChkBox_H1", "xxChkBox_H1", "H1", null, TYPE_HANKAKUEIJI, "1", null},
			{"xxChkBox_H2", "xxChkBox_H2", "H2", null, TYPE_HANKAKUEIJI, "1", null},
			{"xxChkBox_H3", "xxChkBox_H3", "H3", null, TYPE_HANKAKUEIJI, "1", null},
			{"xxChkBox_H4", "xxChkBox_H4", "H4", null, TYPE_HANKAKUEIJI, "1", null},
			{"xxChkBox_H5", "xxChkBox_H5", "H5", null, TYPE_HANKAKUEIJI, "1", null},
			{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
			{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
			{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
			{"A", "A", null, "20", "business.servlet.NPAL1260.NPAL1260_ABMsgArray", null, null},
			{"xxPopPrm_P0", "xxPopPrm_P0", "P0", null, TYPE_HANKAKUEISU, "300", null},
			{"xxPopPrm_P1", "xxPopPrm_P1", "P1", null, TYPE_HANKAKUEISU, "300", null},
			{"xxPopPrm_P2", "xxPopPrm_P2", "P2", null, TYPE_HANKAKUEISU, "300", null},
			{"xxPopPrm_P3", "xxPopPrm_P3", "P3", null, TYPE_HANKAKUEISU, "300", null},
			{"xxPopPrm_P4", "xxPopPrm_P4", "P4", null, TYPE_HANKAKUEISU, "300", null},
			{"xxPopPrm_P5", "xxPopPrm_P5", "P5", null, TYPE_HANKAKUEISU, "300", null},
			{"xxPopPrm_P6", "xxPopPrm_P6", "P6", null, TYPE_HANKAKUEISU, "300", null},
			{"xxPopPrm_P7", "xxPopPrm_P7", "P7", null, TYPE_HANKAKUEISU, "300", null},
			{"xxPopPrm_P8", "xxPopPrm_P8", "P8", null, TYPE_HANKAKUEISU, "300", null},
			{"xxPopPrm_P9", "xxPopPrm_P9", "P9", null, TYPE_HANKAKUEISU, "300", null},
			{"xxPopPrm_PA", "xxPopPrm_PA", "PA", null, TYPE_HANKAKUEISU, "300", null},
			{"xxUpldCsvTempDirTxt_PX", "xxUpldCsvTempDirTxt_PX", "PX", null, TYPE_HANKAKUEISU, "1000", null},
			{"eventNm", "eventNm", null, null, TYPE_HANKAKUEISU, "60", null},
			{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
			{"P", "P", null, "40", "business.servlet.NPAL1260.NPAL1260_PBMsgArray", null, null},
			{"Q", "Q", null, "40", "business.servlet.NPAL1260.NPAL1260_QBMsgArray", null, null},
			{"S", "S", null, "40", "business.servlet.NPAL1260.NPAL1260_SBMsgArray", null, null},
			{"R", "R", null, "10", "business.servlet.NPAL1260.NPAL1260_RBMsgArray", null, null},
			{"billToCustCd_P1", "billToCustCd_P1", "P1", null, TYPE_HANKAKUEISU, "20", null},
			{"billToLocNm_P1", "billToLocNm_P1", "P1", null, TYPE_HANKAKUEISU, "60", null},
			{"sellToCustCd_P1", "sellToCustCd_P1", "P1", null, TYPE_HANKAKUEISU, "20", null},
			{"sellToLocNm_P1", "sellToLocNm_P1", "P1", null, TYPE_HANKAKUEISU, "60", null},
			{"shipToCustCd_P1", "shipToCustCd_P1", "P1", null, TYPE_HANKAKUEISU, "20", null},
			{"shipToLocNm_P1", "shipToLocNm_P1", "P1", null, TYPE_HANKAKUEISU, "60", null},
			{"xxTblNm_P2", "xxTblNm_P2", "P2", null, TYPE_HANKAKUEISU, "30", null},
			{"xxTblCdColNm_P2", "xxTblCdColNm_P2", "P2", null, TYPE_HANKAKUEISU, "30", null},
			{"xxTblNmColNm_P2", "xxTblNmColNm_P2", "P2", null, TYPE_HANKAKUEISU, "30", null},
			{"xxTblSortColNm_P2", "xxTblSortColNm_P2", "P2", null, TYPE_HANKAKUEISU, "30", null},
			{"xxScrNm_P2", "xxScrNm_P2", "P2", null, TYPE_HANKAKUEISU, "70", null},
			{"xxHdrCdLbNm_P2", "xxHdrCdLbNm_P2", "P2", null, TYPE_HANKAKUEISU, "30", null},
			{"xxHdrNmLbNm_P2", "xxHdrNmLbNm_P2", "P2", null, TYPE_HANKAKUEISU, "30", null},
			{"xxDtlCdLbNm_P2", "xxDtlCdLbNm_P2", "P2", null, TYPE_HANKAKUEISU, "30", null},
		};

		/**
		 * Array of Schema Items in Sub class(Visible Field)
		 */
		private static final String[][] DISP_CONTENTS = {
		// ["message display string","mandotry item", "minimum", "maximum", "I/O type", "yen", "comma", "zero suppress", "plus display flag", "display length", "display length after decimal point digit", "Dividing date", "Year display pattern", "zero pudding for date", "separator for date"] <- This is how to define
	        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_SE
	        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_CD
	        {"SRCH_OPT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_DI
	        {"SRCH_OPT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_TX
	        {"SRCH_OPT_USR_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptUsrId_U1
	        {"PRCH_REQ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqNum_H1
	        {"PRCH_REQ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqTpCd_SE
	        {"PRCH_REQ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqTpCd_CD
	        {"PRCH_REQ_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqTpDescTxt_DI
	        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_AC
	        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_H1
	        {"PRCH_REQ_LINE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqLineTpCd_SE
	        {"PRCH_REQ_LINE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqLineTpCd_CD
	        {"PRCH_REQ_LINE_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqLineTpDescTxt_DI
	        {"PRCH_REQ_LINE_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqLineStsCd_SE
	        {"PRCH_REQ_LINE_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqLineStsCd_CD
	        {"PRCH_REQ_LINE_STS_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqLineStsDescTxt_DI
	        {"PRCH_REQ_CRAT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//prchReqCratDt_HF
	        {"PRCH_REQ_CRAT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//prchReqCratDt_HT
	        {"RQST_RCV_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//rqstRcvDt_HF
	        {"RQST_RCV_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//rqstRcvDt_HT
	        {"SHIP_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//shipDt_HF
	        {"SHIP_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//shipDt_HT
	        {"LINE_BIZ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_SE
	        {"LINE_BIZ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_CD
	        {"LINE_BIZ_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpDescTxt_DI
	        {"PRCH_REQ_SRC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqSrcTpCd_SE
	        {"PRCH_REQ_SRC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqSrcTpCd_CD
	        {"PRCH_REQ_SRC_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqSrcTpDescTxt_DI
	        {"FSR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum_H1
	        {"SVC_TASK_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum_H1
	        {"SVC_MACH_SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachSerNum_H1
	        {"PO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdNum_H1
	        {"RWS_REF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsRefNum_H1
	        {"PRCH_REQ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqNum_H2
	        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_AC
	        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_H1
	        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_H1
	        {"RTL_SWH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_H1
	        {"RTL_SWH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_H1
	        {"PRNT_VND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndCd_AC
	        {"PRNT_VND_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndNm_H1
	        {"VND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_AC
	        {"LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_H1
	        {"DEST_RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//destRtlWhCd_AC
	        {"DEST_RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//destRtlWhCd_H1
	        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_H2
	        {"DEST_RTL_SWH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//destRtlSwhCd_H1
	        {"RTL_SWH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_H2
	        {"PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_AC
	        {"TECH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm_H1
	        {"SHIP_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_AC
	        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_H1
	        {"CARR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd_AC
	        {"CARR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrNm_H1
	        {"SHPG_SVC_LVL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlCd_SE
	        {"SHPG_SVC_LVL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlCd_CD
	        {"SHPG_SVC_LVL_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlDescTxt_DI
	        {"PRO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proNum_H1
	        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
	        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H2
	        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H3
	        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H4
	        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H5
	        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
	        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
	        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
			null,	//A
	        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P0
	        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P1
	        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P2
	        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P3
	        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P4
	        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P5
	        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P6
	        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P7
	        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P8
	        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P9
	        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PA
	        {"XX_UPLD_CSV_TEMP_DIR_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxUpldCsvTempDirTxt_PX
	        {"EVENT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eventNm
	        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
			null,	//P
			null,	//Q
			null,	//S
			null,	//R
	        {"BILL_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_P1
	        {"BILL_TO_LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToLocNm_P1
	        {"SELL_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd_P1
	        {"SELL_TO_LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToLocNm_P1
	        {"SHIP_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_P1
	        {"SHIP_TO_LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToLocNm_P1
	        {"XX_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNm_P2
	        {"XX_TBL_CD_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblCdColNm_P2
	        {"XX_TBL_NM_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNmColNm_P2
	        {"XX_TBL_SORT_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblSortColNm_P2
	        {"XX_SCR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm_P2
	        {"XX_HDR_CD_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrCdLbNm_P2
	        {"XX_HDR_NM_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrNmLbNm_P2
	        {"XX_DTL_CD_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCdLbNm_P2
		};

	}

	private static class NPAL1260BMsgSchemaContents2 {

		/**
		 * Array of Schema Items in Sub class(Basic data)
		 */
		private static final String[][] BASE_CONTENTS = {
		//["key name", "variable", "suffix", "array length", "item type", "digits", "after decimal point degits"] <- This is how to define.
			{"xxDtlNmLbNm_P2", "xxDtlNmLbNm_P2", "P2", null, TYPE_HANKAKUEISU, "30", null},
			{"xxCondCd_P2", "xxCondCd_P2", "P2", null, TYPE_HANKAKUEISU, "20", null},
			{"xxCondNm_P2", "xxCondNm_P2", "P2", null, TYPE_HANKAKUEISU, "70", null},
			{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
			{"O", "O", null, "35", "business.servlet.NPAL1260.NPAL1260_OBMsgArray", null, null},
		};

		/**
		 * Array of Schema Items in Sub class(Visible Field)
		 */
		private static final String[][] DISP_CONTENTS = {
		// ["message display string","mandotry item", "minimum", "maximum", "I/O type", "yen", "comma", "zero suppress", "plus display flag", "display length", "display length after decimal point digit", "Dividing date", "Year display pattern", "zero pudding for date", "separator for date"] <- This is how to define
	        {"XX_DTL_NM_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlNmLbNm_P2
	        {"XX_COND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCondCd_P2
	        {"XX_COND_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCondNm_P2
	        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
			null,	//O
		};

	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = new String[105][NPAL1260BMsgSchemaContents1.BASE_CONTENTS[0].length];
	static {
		int arcpStartPoint=0;
	System.arraycopy(NPAL1260BMsgSchemaContents1.BASE_CONTENTS, 0, BASE_CONTENTS, arcpStartPoint, NPAL1260BMsgSchemaContents1.BASE_CONTENTS.length);
	arcpStartPoint = arcpStartPoint + NPAL1260BMsgSchemaContents1.BASE_CONTENTS.length;
	System.arraycopy(NPAL1260BMsgSchemaContents2.BASE_CONTENTS, 0, BASE_CONTENTS, arcpStartPoint, NPAL1260BMsgSchemaContents2.BASE_CONTENTS.length);
	arcpStartPoint = arcpStartPoint + NPAL1260BMsgSchemaContents2.BASE_CONTENTS.length;
	}

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = new String[105][15];
	static {
		int arcpStartPoint=0;
		System.arraycopy(NPAL1260BMsgSchemaContents1.DISP_CONTENTS, 0, DISP_CONTENTS, arcpStartPoint, NPAL1260BMsgSchemaContents1.DISP_CONTENTS.length);
		arcpStartPoint = arcpStartPoint + NPAL1260BMsgSchemaContents1.DISP_CONTENTS.length;
		System.arraycopy(NPAL1260BMsgSchemaContents2.DISP_CONTENTS, 0, DISP_CONTENTS, arcpStartPoint, NPAL1260BMsgSchemaContents2.DISP_CONTENTS.length);
		arcpStartPoint = arcpStartPoint + NPAL1260BMsgSchemaContents2.DISP_CONTENTS.length;
	}

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

