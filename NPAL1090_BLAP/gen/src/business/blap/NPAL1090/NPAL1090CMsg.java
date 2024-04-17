//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160609101154000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1090CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1090;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1090 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1090CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SRCH_OPT_PK_SE*/
	public final EZDCBigDecimalItem              srchOptPk_SE;

    /** SRCH_OPT_PK_CD*/
	public final EZDCBigDecimalItemArray              srchOptPk_CD;

    /** SRCH_OPT_NM_DI*/
	public final EZDCStringItemArray              srchOptNm_DI;

    /** SRCH_OPT_NM_TX*/
	public final EZDCStringItem              srchOptNm_TX;

    /** SRCH_OPT_USR_ID_U1*/
	public final EZDCStringItem              srchOptUsrId_U1;

    /** PRCH_REQ_NUM_H1*/
	public final EZDCStringItem              prchReqNum_H1;

    /** PRCH_REQ_TP_CD_SE*/
	public final EZDCStringItem              prchReqTpCd_SE;

    /** PRCH_REQ_TP_CD_CD*/
	public final EZDCStringItemArray              prchReqTpCd_CD;

    /** PRCH_REQ_TP_DESC_TXT_DI*/
	public final EZDCStringItemArray              prchReqTpDescTxt_DI;

    /** PRCH_REQ_STS_CD_SE*/
	public final EZDCStringItem              prchReqStsCd_SE;

    /** PRCH_REQ_STS_CD_CD*/
	public final EZDCStringItemArray              prchReqStsCd_CD;

    /** PRCH_REQ_STS_DESC_TXT_DI*/
	public final EZDCStringItemArray              prchReqStsDescTxt_DI;

    /** PRCH_REQ_APVL_STS_CD_SE*/
	public final EZDCStringItem              prchReqApvlStsCd_SE;

    /** PRCH_REQ_APVL_STS_CD_CD*/
	public final EZDCStringItemArray              prchReqApvlStsCd_CD;

    /** PRCH_REQ_APVL_STS_DESC_TXT_DI*/
	public final EZDCStringItemArray              prchReqApvlStsDescTxt_DI;

    /** PRCH_REQ_CRAT_DT_HF*/
	public final EZDCDateItem              prchReqCratDt_HF;

    /** PRCH_REQ_CRAT_DT_HT*/
	public final EZDCDateItem              prchReqCratDt_HT;

    /** PRCH_REQ_SRC_TP_CD_SE*/
	public final EZDCStringItem              prchReqSrcTpCd_SE;

    /** PRCH_REQ_SRC_TP_CD_CD*/
	public final EZDCStringItemArray              prchReqSrcTpCd_CD;

    /** PRCH_REQ_SRC_TP_DESC_TXT_DI*/
	public final EZDCStringItemArray              prchReqSrcTpDescTxt_DI;

    /** FSR_NUM_H1*/
	public final EZDCStringItem              fsrNum_H1;

    /** SVC_TASK_NUM_H1*/
	public final EZDCStringItem              svcTaskNum_H1;

    /** SVC_MACH_SER_NUM_H1*/
	public final EZDCStringItem              svcMachSerNum_H1;

    /** RQST_RCV_DT_HF*/
	public final EZDCDateItem              rqstRcvDt_HF;

    /** RQST_RCV_DT_HT*/
	public final EZDCDateItem              rqstRcvDt_HT;

    /** RQST_TECH_TOC_CD_AC*/
	public final EZDCStringItem              rqstTechTocCd_AC;

    /** TECH_NM_H1*/
	public final EZDCStringItem              techNm_H1;

    /** RTL_WH_CD_AC*/
	public final EZDCStringItem              rtlWhCd_AC;

    /** RTL_WH_CD_H1*/
	public final EZDCStringItem              rtlWhCd_H1;

    /** RTL_WH_NM_H1*/
	public final EZDCStringItem              rtlWhNm_H1;

    /** RTL_SWH_CD_H1*/
	public final EZDCStringItem              rtlSwhCd_H1;

    /** RTL_SWH_NM_H1*/
	public final EZDCStringItem              rtlSwhNm_H1;

    /** SHIP_TO_CUST_CD_AC*/
	public final EZDCStringItem              shipToCustCd_AC;

    /** SHIP_TO_CUST_CD_H1*/
	public final EZDCStringItem              shipToCustCd_H1;

    /** LOC_NM_H1*/
	public final EZDCStringItem              locNm_H1;

    /** SHPG_SVC_LVL_CD_SE*/
	public final EZDCStringItem              shpgSvcLvlCd_SE;

    /** SHPG_SVC_LVL_CD_CD*/
	public final EZDCStringItemArray              shpgSvcLvlCd_CD;

    /** SHPG_SVC_LVL_DESC_TXT_DI*/
	public final EZDCStringItemArray              shpgSvcLvlDescTxt_DI;

    /** CARR_CD_AC*/
	public final EZDCStringItem              carrCd_AC;

    /** CARR_NM_H1*/
	public final EZDCStringItem              carrNm_H1;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.blap.NPAL1090.NPAL1090_ACMsgArray              A;

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

    /** PRCH_REQ_NUM_P2*/
	public final EZDCStringItem              prchReqNum_P2;

    /** XX_POP_PRM_EV*/
	public final EZDCStringItem              xxPopPrm_EV;

    /** XX_POP_PRM_P0*/
	public final EZDCStringItem              xxPopPrm_P0;

    /** XX_POP_PRM_P1*/
	public final EZDCStringItem              xxPopPrm_P1;

    /** XX_POP_PRM_P2*/
	public final EZDCStringItem              xxPopPrm_P2;

    /** XX_POP_PRM_P3*/
	public final EZDCStringItem              xxPopPrm_P3;

    /** XX_POP_PRM_P4*/
	public final EZDCStringItem              xxPopPrm_P4;

    /** XX_POP_PRM_P5*/
	public final EZDCStringItem              xxPopPrm_P5;

    /** XX_POP_PRM_P6*/
	public final EZDCStringItem              xxPopPrm_P6;

    /** XX_POP_PRM_P7*/
	public final EZDCStringItem              xxPopPrm_P7;

    /** XX_POP_PRM_P8*/
	public final EZDCStringItem              xxPopPrm_P8;

    /** XX_POP_PRM_P9*/
	public final EZDCStringItem              xxPopPrm_P9;

    /** XX_POP_PRM_PA*/
	public final EZDCStringItem              xxPopPrm_PA;

    /** BILL_TO_CUST_CD_P3*/
	public final EZDCStringItem              billToCustCd_P3;

    /** BILL_TO_LOC_NM_P3*/
	public final EZDCStringItem              billToLocNm_P3;

    /** SELL_TO_CUST_CD_P3*/
	public final EZDCStringItem              sellToCustCd_P3;

    /** SELL_TO_LOC_NM_P3*/
	public final EZDCStringItem              sellToLocNm_P3;

    /** SHIP_TO_CUST_CD_P3*/
	public final EZDCStringItem              shipToCustCd_P3;

    /** SHIP_TO_LOC_NM_P3*/
	public final EZDCStringItem              shipToLocNm_P3;

    /** F*/
	public final business.blap.NPAL1090.NPAL1090_FCMsgArray              F;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDCStringItem              xxComnColOrdTxt;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;


	/**
	 * NPAL1090CMsg is constructor.
	 * The initialization when the instance of NPAL1090CMsg is generated.
	 */
	public NPAL1090CMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1090CMsg is constructor.
	 * The initialization when the instance of NPAL1090CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1090CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		srchOptPk_SE = (EZDCBigDecimalItem)newItem("srchOptPk_SE");
		srchOptPk_CD = (EZDCBigDecimalItemArray)newItemArray("srchOptPk_CD");
		srchOptNm_DI = (EZDCStringItemArray)newItemArray("srchOptNm_DI");
		srchOptNm_TX = (EZDCStringItem)newItem("srchOptNm_TX");
		srchOptUsrId_U1 = (EZDCStringItem)newItem("srchOptUsrId_U1");
		prchReqNum_H1 = (EZDCStringItem)newItem("prchReqNum_H1");
		prchReqTpCd_SE = (EZDCStringItem)newItem("prchReqTpCd_SE");
		prchReqTpCd_CD = (EZDCStringItemArray)newItemArray("prchReqTpCd_CD");
		prchReqTpDescTxt_DI = (EZDCStringItemArray)newItemArray("prchReqTpDescTxt_DI");
		prchReqStsCd_SE = (EZDCStringItem)newItem("prchReqStsCd_SE");
		prchReqStsCd_CD = (EZDCStringItemArray)newItemArray("prchReqStsCd_CD");
		prchReqStsDescTxt_DI = (EZDCStringItemArray)newItemArray("prchReqStsDescTxt_DI");
		prchReqApvlStsCd_SE = (EZDCStringItem)newItem("prchReqApvlStsCd_SE");
		prchReqApvlStsCd_CD = (EZDCStringItemArray)newItemArray("prchReqApvlStsCd_CD");
		prchReqApvlStsDescTxt_DI = (EZDCStringItemArray)newItemArray("prchReqApvlStsDescTxt_DI");
		prchReqCratDt_HF = (EZDCDateItem)newItem("prchReqCratDt_HF");
		prchReqCratDt_HT = (EZDCDateItem)newItem("prchReqCratDt_HT");
		prchReqSrcTpCd_SE = (EZDCStringItem)newItem("prchReqSrcTpCd_SE");
		prchReqSrcTpCd_CD = (EZDCStringItemArray)newItemArray("prchReqSrcTpCd_CD");
		prchReqSrcTpDescTxt_DI = (EZDCStringItemArray)newItemArray("prchReqSrcTpDescTxt_DI");
		fsrNum_H1 = (EZDCStringItem)newItem("fsrNum_H1");
		svcTaskNum_H1 = (EZDCStringItem)newItem("svcTaskNum_H1");
		svcMachSerNum_H1 = (EZDCStringItem)newItem("svcMachSerNum_H1");
		rqstRcvDt_HF = (EZDCDateItem)newItem("rqstRcvDt_HF");
		rqstRcvDt_HT = (EZDCDateItem)newItem("rqstRcvDt_HT");
		rqstTechTocCd_AC = (EZDCStringItem)newItem("rqstTechTocCd_AC");
		techNm_H1 = (EZDCStringItem)newItem("techNm_H1");
		rtlWhCd_AC = (EZDCStringItem)newItem("rtlWhCd_AC");
		rtlWhCd_H1 = (EZDCStringItem)newItem("rtlWhCd_H1");
		rtlWhNm_H1 = (EZDCStringItem)newItem("rtlWhNm_H1");
		rtlSwhCd_H1 = (EZDCStringItem)newItem("rtlSwhCd_H1");
		rtlSwhNm_H1 = (EZDCStringItem)newItem("rtlSwhNm_H1");
		shipToCustCd_AC = (EZDCStringItem)newItem("shipToCustCd_AC");
		shipToCustCd_H1 = (EZDCStringItem)newItem("shipToCustCd_H1");
		locNm_H1 = (EZDCStringItem)newItem("locNm_H1");
		shpgSvcLvlCd_SE = (EZDCStringItem)newItem("shpgSvcLvlCd_SE");
		shpgSvcLvlCd_CD = (EZDCStringItemArray)newItemArray("shpgSvcLvlCd_CD");
		shpgSvcLvlDescTxt_DI = (EZDCStringItemArray)newItemArray("shpgSvcLvlDescTxt_DI");
		carrCd_AC = (EZDCStringItem)newItem("carrCd_AC");
		carrNm_H1 = (EZDCStringItem)newItem("carrNm_H1");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.blap.NPAL1090.NPAL1090_ACMsgArray)newMsgArray("A");
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
		prchReqNum_P2 = (EZDCStringItem)newItem("prchReqNum_P2");
		xxPopPrm_EV = (EZDCStringItem)newItem("xxPopPrm_EV");
		xxPopPrm_P0 = (EZDCStringItem)newItem("xxPopPrm_P0");
		xxPopPrm_P1 = (EZDCStringItem)newItem("xxPopPrm_P1");
		xxPopPrm_P2 = (EZDCStringItem)newItem("xxPopPrm_P2");
		xxPopPrm_P3 = (EZDCStringItem)newItem("xxPopPrm_P3");
		xxPopPrm_P4 = (EZDCStringItem)newItem("xxPopPrm_P4");
		xxPopPrm_P5 = (EZDCStringItem)newItem("xxPopPrm_P5");
		xxPopPrm_P6 = (EZDCStringItem)newItem("xxPopPrm_P6");
		xxPopPrm_P7 = (EZDCStringItem)newItem("xxPopPrm_P7");
		xxPopPrm_P8 = (EZDCStringItem)newItem("xxPopPrm_P8");
		xxPopPrm_P9 = (EZDCStringItem)newItem("xxPopPrm_P9");
		xxPopPrm_PA = (EZDCStringItem)newItem("xxPopPrm_PA");
		billToCustCd_P3 = (EZDCStringItem)newItem("billToCustCd_P3");
		billToLocNm_P3 = (EZDCStringItem)newItem("billToLocNm_P3");
		sellToCustCd_P3 = (EZDCStringItem)newItem("sellToCustCd_P3");
		sellToLocNm_P3 = (EZDCStringItem)newItem("sellToLocNm_P3");
		shipToCustCd_P3 = (EZDCStringItem)newItem("shipToCustCd_P3");
		shipToLocNm_P3 = (EZDCStringItem)newItem("shipToLocNm_P3");
		F = (business.blap.NPAL1090.NPAL1090_FCMsgArray)newMsgArray("F");
		xxComnColOrdTxt = (EZDCStringItem)newItem("xxComnColOrdTxt");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1090CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1090CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"srchOptPk_SE", "srchOptPk_SE", "SE", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptPk_CD", "srchOptPk_CD", "CD", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_DI", "srchOptNm_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
	{"srchOptNm_TX", "srchOptNm_TX", "TX", null, TYPE_HANKAKUEISU, "50", null},
	{"srchOptUsrId_U1", "srchOptUsrId_U1", "U1", null, TYPE_HANKAKUEISU, "16", null},
	{"prchReqNum_H1", "prchReqNum_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"prchReqTpCd_SE", "prchReqTpCd_SE", "SE", null, TYPE_HANKAKUEISU, "4", null},
	{"prchReqTpCd_CD", "prchReqTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "4", null},
	{"prchReqTpDescTxt_DI", "prchReqTpDescTxt_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
	{"prchReqStsCd_SE", "prchReqStsCd_SE", "SE", null, TYPE_HANKAKUEISU, "2", null},
	{"prchReqStsCd_CD", "prchReqStsCd_CD", "CD", "99", TYPE_HANKAKUEISU, "2", null},
	{"prchReqStsDescTxt_DI", "prchReqStsDescTxt_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
	{"prchReqApvlStsCd_SE", "prchReqApvlStsCd_SE", "SE", null, TYPE_HANKAKUEISU, "2", null},
	{"prchReqApvlStsCd_CD", "prchReqApvlStsCd_CD", "CD", "99", TYPE_HANKAKUEISU, "2", null},
	{"prchReqApvlStsDescTxt_DI", "prchReqApvlStsDescTxt_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
	{"prchReqCratDt_HF", "prchReqCratDt_HF", "HF", null, TYPE_NENTSUKIHI, "8", null},
	{"prchReqCratDt_HT", "prchReqCratDt_HT", "HT", null, TYPE_NENTSUKIHI, "8", null},
	{"prchReqSrcTpCd_SE", "prchReqSrcTpCd_SE", "SE", null, TYPE_HANKAKUEISU, "2", null},
	{"prchReqSrcTpCd_CD", "prchReqSrcTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "2", null},
	{"prchReqSrcTpDescTxt_DI", "prchReqSrcTpDescTxt_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
	{"fsrNum_H1", "fsrNum_H1", "H1", null, TYPE_HANKAKUEISU, "10", null},
	{"svcTaskNum_H1", "svcTaskNum_H1", "H1", null, TYPE_HANKAKUEISU, "10", null},
	{"svcMachSerNum_H1", "svcMachSerNum_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"rqstRcvDt_HF", "rqstRcvDt_HF", "HF", null, TYPE_NENTSUKIHI, "8", null},
	{"rqstRcvDt_HT", "rqstRcvDt_HT", "HT", null, TYPE_NENTSUKIHI, "8", null},
	{"rqstTechTocCd_AC", "rqstTechTocCd_AC", "AC", null, TYPE_HANKAKUEISU, "8", null},
	{"techNm_H1", "techNm_H1", "H1", null, TYPE_HANKAKUEISU, "45", null},
	{"rtlWhCd_AC", "rtlWhCd_AC", "AC", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhCd_H1", "rtlWhCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_H1", "rtlWhNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_H1", "rtlSwhCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_H1", "rtlSwhNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"shipToCustCd_AC", "shipToCustCd_AC", "AC", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd_H1", "shipToCustCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_H1", "locNm_H1", "H1", null, TYPE_HANKAKUEISU, "60", null},
	{"shpgSvcLvlCd_SE", "shpgSvcLvlCd_SE", "SE", null, TYPE_HANKAKUEISU, "2", null},
	{"shpgSvcLvlCd_CD", "shpgSvcLvlCd_CD", "CD", "99", TYPE_HANKAKUEISU, "2", null},
	{"shpgSvcLvlDescTxt_DI", "shpgSvcLvlDescTxt_DI", "DI", "99", TYPE_HANKAKUEISU, "50", null},
	{"carrCd_AC", "carrCd_AC", "AC", null, TYPE_HANKAKUEISU, "20", null},
	{"carrNm_H1", "carrNm_H1", "H1", null, TYPE_HANKAKUEISU, "60", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "20", "business.blap.NPAL1090.NPAL1090_ACMsgArray", null, null},
	
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
	{"prchReqNum_P2", "prchReqNum_P2", "P2", null, TYPE_HANKAKUEISU, "8", null},
	{"xxPopPrm_EV", "xxPopPrm_EV", "EV", null, TYPE_HANKAKUEISU, "300", null},
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
	{"billToCustCd_P3", "billToCustCd_P3", "P3", null, TYPE_HANKAKUEISU, "20", null},
	{"billToLocNm_P3", "billToLocNm_P3", "P3", null, TYPE_HANKAKUEISU, "60", null},
	{"sellToCustCd_P3", "sellToCustCd_P3", "P3", null, TYPE_HANKAKUEISU, "20", null},
	{"sellToLocNm_P3", "sellToLocNm_P3", "P3", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToCustCd_P3", "shipToCustCd_P3", "P3", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToLocNm_P3", "shipToLocNm_P3", "P3", null, TYPE_HANKAKUEISU, "60", null},
	{"F", "F", null, "2", "business.blap.NPAL1090.NPAL1090_FCMsgArray", null, null},
	
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_SE
        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_CD
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_DI
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_TX
        {"SRCH_OPT_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptUsrId_U1
        {"PRCH_REQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqNum_H1
        {"PRCH_REQ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqTpCd_SE
        {"PRCH_REQ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqTpCd_CD
        {"PRCH_REQ_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqTpDescTxt_DI
        {"PRCH_REQ_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqStsCd_SE
        {"PRCH_REQ_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqStsCd_CD
        {"PRCH_REQ_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqStsDescTxt_DI
        {"PRCH_REQ_APVL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqApvlStsCd_SE
        {"PRCH_REQ_APVL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqApvlStsCd_CD
        {"PRCH_REQ_APVL_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqApvlStsDescTxt_DI
        {"PRCH_REQ_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqCratDt_HF
        {"PRCH_REQ_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqCratDt_HT
        {"PRCH_REQ_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqSrcTpCd_SE
        {"PRCH_REQ_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqSrcTpCd_CD
        {"PRCH_REQ_SRC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqSrcTpDescTxt_DI
        {"FSR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum_H1
        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum_H1
        {"SVC_MACH_SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachSerNum_H1
        {"RQST_RCV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDt_HF
        {"RQST_RCV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDt_HT
        {"RQST_TECH_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstTechTocCd_AC
        {"TECH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm_H1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_AC
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_H1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_H1
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_H1
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_H1
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_AC
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_H1
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_H1
        {"SHPG_SVC_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlCd_SE
        {"SHPG_SVC_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlCd_CD
        {"SHPG_SVC_LVL_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlDescTxt_DI
        {"CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd_AC
        {"CARR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrNm_H1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
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
        {"PRCH_REQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqNum_P2
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_EV
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P0
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P2
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P3
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P4
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P5
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P6
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P7
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P8
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P9
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PA
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_P3
        {"BILL_TO_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToLocNm_P3
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd_P3
        {"SELL_TO_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToLocNm_P3
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_P3
        {"SHIP_TO_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToLocNm_P3
		null,	//F
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
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
