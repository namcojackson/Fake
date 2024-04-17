//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180328151516000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1360CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1360;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1360 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1360CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** WRK_ORD_NUM_P1*/
	public final EZDCStringItem              wrkOrdNum_P1;

    /** MDSE_CD_P1*/
	public final EZDCStringItem              mdseCd_P1;

    /** ORD_QTY_P1*/
	public final EZDCBigDecimalItem              ordQty_P1;

    /** RTL_WH_CD_P1*/
	public final EZDCStringItem              rtlWhCd_P1;

    /** RTL_SWH_CD_P1*/
	public final EZDCStringItem              rtlSwhCd_P1;

    /** PRCH_REQ_NUM_P1*/
	public final EZDCStringItem              prchReqNum_P1;

    /** PRCH_REQ_LINE_NUM_P1*/
	public final EZDCStringItem              prchReqLineNum_P1;

    /** PRCH_REQ_LINE_SUB_NUM_P1*/
	public final EZDCBigDecimalItem              prchReqLineSubNum_P1;

    /** SER_NUM_P1*/
	public final EZDCStringItem              serNum_P1;

    /** RQST_RCV_DT_P1*/
	public final EZDCDateItem              rqstRcvDt_P1;

    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDCDateItem              slsDt;

    /** WRK_ORD_NUM*/
	public final EZDCStringItem              wrkOrdNum;

    /** WRK_ORD_NUM_SV*/
	public final EZDCStringItem              wrkOrdNum_SV;

    /** DS_WRK_ORD_TP_CD_CD*/
	public final EZDCStringItemArray              dsWrkOrdTpCd_CD;

    /** DS_WRK_ORD_TP_DESC_TXT_NM*/
	public final EZDCStringItemArray              dsWrkOrdTpDescTxt_NM;

    /** DS_WRK_ORD_TP_CD_SL*/
	public final EZDCStringItem              dsWrkOrdTpCd_SL;

    /** WRK_ORD_STS_CD*/
	public final EZDCStringItem              wrkOrdStsCd;

    /** DS_WRK_ORD_STS_NM*/
	public final EZDCStringItem              dsWrkOrdStsNm;

    /** WRK_ORD_DT*/
	public final EZDCDateItem              wrkOrdDt;

    /** PRCH_REQ_NUM*/
	public final EZDCStringItem              prchReqNum;

    /** XX_LINK_ANCR_WH*/
	public final EZDCStringItem              xxLinkAncr_WH;

    /** RTL_WH_CD*/
	public final EZDCStringItem              rtlWhCd;

    /** RTL_WH_NM*/
	public final EZDCStringItem              rtlWhNm;

    /** XX_LINK_ANCR_SW*/
	public final EZDCStringItem              xxLinkAncr_SW;

    /** CPLT_RTL_SWH_CD*/
	public final EZDCStringItem              cpltRtlSwhCd;

    /** RTL_SWH_NM*/
	public final EZDCStringItem              rtlSwhNm;

    /** RQST_RCV_DT*/
	public final EZDCDateItem              rqstRcvDt;

    /** XX_LINK_ANCR_KT*/
	public final EZDCStringItem              xxLinkAncr_KT;

    /** FNSH_GOODS_MDSE_CD*/
	public final EZDCStringItem              fnshGoodsMdseCd;

    /** FNSH_MDSE_DESC_SHORT_TXT*/
	public final EZDCStringItem              fnshMdseDescShortTxt;

    /** FNSH_GOODS_MDSE_NM*/
	public final EZDCStringItem              fnshGoodsMdseNm;

    /** FIRST_PROD_CTRL_CD*/
	public final EZDCStringItem              firstProdCtrlCd;

    /** RCV_SER_TAKE_FLG*/
	public final EZDCStringItem              rcvSerTakeFlg;

    /** SER_NUM*/
	public final EZDCStringItem              serNum;

    /** SER_NUM_TAKE_FLG*/
	public final EZDCStringItem              serNumTakeFlg;

    /** EFF_FROM_DT*/
	public final EZDCDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDCDateItem              effThruDt;

    /** BASE_PKG_UOM_CD*/
	public final EZDCStringItem              basePkgUomCd;

    /** FNSH_GOODS_ORD_QTY*/
	public final EZDCBigDecimalItem              fnshGoodsOrdQty;

    /** FNSH_GOODS_RCV_QTY*/
	public final EZDCBigDecimalItem              fnshGoodsRcvQty;

    /** FNSH_GOODS_BAL_QTY*/
	public final EZDCBigDecimalItem              fnshGoodsBalQty;

    /** FNSH_GOODS_CANC_QTY*/
	public final EZDCBigDecimalItem              fnshGoodsCancQty;

    /** WRK_ORD_MSG_TXT*/
	public final EZDCStringItem              wrkOrdMsgTxt;

    /** WRK_ORD_DTL_LINE_NUM_SL*/
	public final EZDCStringItem              wrkOrdDtlLineNum_SL;

    /** CMPSN_REVN_NUM*/
	public final EZDCBigDecimalItem              cmpsnRevnNum;

    /** TRX_SRC_TP_CD*/
	public final EZDCStringItem              trxSrcTpCd;

    /** INVTY_LOC_CD*/
	public final EZDCStringItem              invtyLocCd;

    /** INVTY_CTRL_FLG*/
	public final EZDCStringItem              invtyCtrlFlg;

    /** INSTL_BASE_CTRL_FLG*/
	public final EZDCStringItem              instlBaseCtrlFlg;

    /** INVTY_ALLOC_QTY*/
	public final EZDCBigDecimalItem              invtyAllocQty;

    /** TOT_ORD_QTY*/
	public final EZDCBigDecimalItem              totOrdQty;

    /** SO_NUM*/
	public final EZDCStringItem              soNum;

    /** XX_YES_NO_CD*/
	public final EZDCStringItem              xxYesNoCd;

    /** XX_RQST_TS_WH*/
	public final EZDCStringItem              xxRqstTs_WH;

    /** XX_RQST_TZ_WH*/
	public final EZDCStringItem              xxRqstTz_WH;

    /** XX_RQST_TS_DH*/
	public final EZDCStringItem              xxRqstTs_DH;

    /** XX_RQST_TZ_DH*/
	public final EZDCStringItem              xxRqstTz_DH;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** A*/
	public final business.blap.NPAL1360.NPAL1360_ACMsgArray              A;

    /** S*/
	public final business.blap.NPAL1360.NPAL1360_SCMsgArray              S;

    /** W*/
	public final business.blap.NPAL1360.NPAL1360_WCMsgArray              W;

    /** EVENT_NM*/
	public final EZDCStringItem              eventNm;

    /** P*/
	public final business.blap.NPAL1360.NPAL1360_PCMsgArray              P;

    /** X*/
	public final business.blap.NPAL1360.NPAL1360_XCMsgArray              X;

    /** L*/
	public final business.blap.NPAL1360.NPAL1360_LCMsgArray              L;

    /** R*/
	public final business.blap.NPAL1360.NPAL1360_RCMsgArray              R;


	/**
	 * NPAL1360CMsg is constructor.
	 * The initialization when the instance of NPAL1360CMsg is generated.
	 */
	public NPAL1360CMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1360CMsg is constructor.
	 * The initialization when the instance of NPAL1360CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1360CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		wrkOrdNum_P1 = (EZDCStringItem)newItem("wrkOrdNum_P1");
		mdseCd_P1 = (EZDCStringItem)newItem("mdseCd_P1");
		ordQty_P1 = (EZDCBigDecimalItem)newItem("ordQty_P1");
		rtlWhCd_P1 = (EZDCStringItem)newItem("rtlWhCd_P1");
		rtlSwhCd_P1 = (EZDCStringItem)newItem("rtlSwhCd_P1");
		prchReqNum_P1 = (EZDCStringItem)newItem("prchReqNum_P1");
		prchReqLineNum_P1 = (EZDCStringItem)newItem("prchReqLineNum_P1");
		prchReqLineSubNum_P1 = (EZDCBigDecimalItem)newItem("prchReqLineSubNum_P1");
		serNum_P1 = (EZDCStringItem)newItem("serNum_P1");
		rqstRcvDt_P1 = (EZDCDateItem)newItem("rqstRcvDt_P1");
		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		slsDt = (EZDCDateItem)newItem("slsDt");
		wrkOrdNum = (EZDCStringItem)newItem("wrkOrdNum");
		wrkOrdNum_SV = (EZDCStringItem)newItem("wrkOrdNum_SV");
		dsWrkOrdTpCd_CD = (EZDCStringItemArray)newItemArray("dsWrkOrdTpCd_CD");
		dsWrkOrdTpDescTxt_NM = (EZDCStringItemArray)newItemArray("dsWrkOrdTpDescTxt_NM");
		dsWrkOrdTpCd_SL = (EZDCStringItem)newItem("dsWrkOrdTpCd_SL");
		wrkOrdStsCd = (EZDCStringItem)newItem("wrkOrdStsCd");
		dsWrkOrdStsNm = (EZDCStringItem)newItem("dsWrkOrdStsNm");
		wrkOrdDt = (EZDCDateItem)newItem("wrkOrdDt");
		prchReqNum = (EZDCStringItem)newItem("prchReqNum");
		xxLinkAncr_WH = (EZDCStringItem)newItem("xxLinkAncr_WH");
		rtlWhCd = (EZDCStringItem)newItem("rtlWhCd");
		rtlWhNm = (EZDCStringItem)newItem("rtlWhNm");
		xxLinkAncr_SW = (EZDCStringItem)newItem("xxLinkAncr_SW");
		cpltRtlSwhCd = (EZDCStringItem)newItem("cpltRtlSwhCd");
		rtlSwhNm = (EZDCStringItem)newItem("rtlSwhNm");
		rqstRcvDt = (EZDCDateItem)newItem("rqstRcvDt");
		xxLinkAncr_KT = (EZDCStringItem)newItem("xxLinkAncr_KT");
		fnshGoodsMdseCd = (EZDCStringItem)newItem("fnshGoodsMdseCd");
		fnshMdseDescShortTxt = (EZDCStringItem)newItem("fnshMdseDescShortTxt");
		fnshGoodsMdseNm = (EZDCStringItem)newItem("fnshGoodsMdseNm");
		firstProdCtrlCd = (EZDCStringItem)newItem("firstProdCtrlCd");
		rcvSerTakeFlg = (EZDCStringItem)newItem("rcvSerTakeFlg");
		serNum = (EZDCStringItem)newItem("serNum");
		serNumTakeFlg = (EZDCStringItem)newItem("serNumTakeFlg");
		effFromDt = (EZDCDateItem)newItem("effFromDt");
		effThruDt = (EZDCDateItem)newItem("effThruDt");
		basePkgUomCd = (EZDCStringItem)newItem("basePkgUomCd");
		fnshGoodsOrdQty = (EZDCBigDecimalItem)newItem("fnshGoodsOrdQty");
		fnshGoodsRcvQty = (EZDCBigDecimalItem)newItem("fnshGoodsRcvQty");
		fnshGoodsBalQty = (EZDCBigDecimalItem)newItem("fnshGoodsBalQty");
		fnshGoodsCancQty = (EZDCBigDecimalItem)newItem("fnshGoodsCancQty");
		wrkOrdMsgTxt = (EZDCStringItem)newItem("wrkOrdMsgTxt");
		wrkOrdDtlLineNum_SL = (EZDCStringItem)newItem("wrkOrdDtlLineNum_SL");
		cmpsnRevnNum = (EZDCBigDecimalItem)newItem("cmpsnRevnNum");
		trxSrcTpCd = (EZDCStringItem)newItem("trxSrcTpCd");
		invtyLocCd = (EZDCStringItem)newItem("invtyLocCd");
		invtyCtrlFlg = (EZDCStringItem)newItem("invtyCtrlFlg");
		instlBaseCtrlFlg = (EZDCStringItem)newItem("instlBaseCtrlFlg");
		invtyAllocQty = (EZDCBigDecimalItem)newItem("invtyAllocQty");
		totOrdQty = (EZDCBigDecimalItem)newItem("totOrdQty");
		soNum = (EZDCStringItem)newItem("soNum");
		xxYesNoCd = (EZDCStringItem)newItem("xxYesNoCd");
		xxRqstTs_WH = (EZDCStringItem)newItem("xxRqstTs_WH");
		xxRqstTz_WH = (EZDCStringItem)newItem("xxRqstTz_WH");
		xxRqstTs_DH = (EZDCStringItem)newItem("xxRqstTs_DH");
		xxRqstTz_DH = (EZDCStringItem)newItem("xxRqstTz_DH");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		A = (business.blap.NPAL1360.NPAL1360_ACMsgArray)newMsgArray("A");
		S = (business.blap.NPAL1360.NPAL1360_SCMsgArray)newMsgArray("S");
		W = (business.blap.NPAL1360.NPAL1360_WCMsgArray)newMsgArray("W");
		eventNm = (EZDCStringItem)newItem("eventNm");
		P = (business.blap.NPAL1360.NPAL1360_PCMsgArray)newMsgArray("P");
		X = (business.blap.NPAL1360.NPAL1360_XCMsgArray)newMsgArray("X");
		L = (business.blap.NPAL1360.NPAL1360_LCMsgArray)newMsgArray("L");
		R = (business.blap.NPAL1360.NPAL1360_RCMsgArray)newMsgArray("R");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1360CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1360CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"wrkOrdNum_P1", "wrkOrdNum_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"mdseCd_P1", "mdseCd_P1", "P1", null, TYPE_HANKAKUEISU, "16", null},
	{"ordQty_P1", "ordQty_P1", "P1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"rtlWhCd_P1", "rtlWhCd_P1", "P1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhCd_P1", "rtlSwhCd_P1", "P1", null, TYPE_HANKAKUEISU, "20", null},
	{"prchReqNum_P1", "prchReqNum_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"prchReqLineNum_P1", "prchReqLineNum_P1", "P1", null, TYPE_HANKAKUEISU, "3", null},
	{"prchReqLineSubNum_P1", "prchReqLineSubNum_P1", "P1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"serNum_P1", "serNum_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"rqstRcvDt_P1", "rqstRcvDt_P1", "P1", null, TYPE_NENTSUKIHI, "8", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"wrkOrdNum", "wrkOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"wrkOrdNum_SV", "wrkOrdNum_SV", "SV", null, TYPE_HANKAKUEISU, "8", null},
	{"dsWrkOrdTpCd_CD", "dsWrkOrdTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "2", null},
	{"dsWrkOrdTpDescTxt_NM", "dsWrkOrdTpDescTxt_NM", "NM", "99", TYPE_HANKAKUEISU, "50", null},
	{"dsWrkOrdTpCd_SL", "dsWrkOrdTpCd_SL", "SL", null, TYPE_HANKAKUEISU, "2", null},
	{"wrkOrdStsCd", "wrkOrdStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsWrkOrdStsNm", "dsWrkOrdStsNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"wrkOrdDt", "wrkOrdDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"prchReqNum", "prchReqNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"xxLinkAncr_WH", "xxLinkAncr_WH", "WH", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlWhCd", "rtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm", "rtlWhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxLinkAncr_SW", "xxLinkAncr_SW", "SW", null, TYPE_HANKAKUEISU, "30", null},
	{"cpltRtlSwhCd", "cpltRtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm", "rtlSwhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"rqstRcvDt", "rqstRcvDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxLinkAncr_KT", "xxLinkAncr_KT", "KT", null, TYPE_HANKAKUEISU, "30", null},
	{"fnshGoodsMdseCd", "fnshGoodsMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"fnshMdseDescShortTxt", "fnshMdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"fnshGoodsMdseNm", "fnshGoodsMdseNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"firstProdCtrlCd", "firstProdCtrlCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"rcvSerTakeFlg", "rcvSerTakeFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"serNumTakeFlg", "serNumTakeFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"basePkgUomCd", "basePkgUomCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"fnshGoodsOrdQty", "fnshGoodsOrdQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"fnshGoodsRcvQty", "fnshGoodsRcvQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"fnshGoodsBalQty", "fnshGoodsBalQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"fnshGoodsCancQty", "fnshGoodsCancQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"wrkOrdMsgTxt", "wrkOrdMsgTxt", null, null, TYPE_HANKAKUEISU, "120", null},
	{"wrkOrdDtlLineNum_SL", "wrkOrdDtlLineNum_SL", "SL", null, TYPE_HANKAKUEISU, "3", null},
	{"cmpsnRevnNum", "cmpsnRevnNum", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"trxSrcTpCd", "trxSrcTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"invtyLocCd", "invtyLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"invtyCtrlFlg", "invtyCtrlFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"instlBaseCtrlFlg", "instlBaseCtrlFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"invtyAllocQty", "invtyAllocQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"totOrdQty", "totOrdQty", null, null, TYPE_SEISU_SYOSU, "9", "0"},
	{"soNum", "soNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"xxYesNoCd", "xxYesNoCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxRqstTs_WH", "xxRqstTs_WH", "WH", null, TYPE_HANKAKUEISU, "17", null},
	{"xxRqstTz_WH", "xxRqstTz_WH", "WH", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRqstTs_DH", "xxRqstTs_DH", "DH", null, TYPE_HANKAKUEISU, "17", null},
	{"xxRqstTz_DH", "xxRqstTz_DH", "DH", null, TYPE_HANKAKUEISU, "5", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"A", "A", null, "200", "business.blap.NPAL1360.NPAL1360_ACMsgArray", null, null},
	
	{"S", "S", null, "200", "business.blap.NPAL1360.NPAL1360_SCMsgArray", null, null},
	
	{"W", "W", null, "200", "business.blap.NPAL1360.NPAL1360_WCMsgArray", null, null},
	
	{"eventNm", "eventNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"P", "P", null, "15", "business.blap.NPAL1360.NPAL1360_PCMsgArray", null, null},
	
	{"X", "X", null, "30", "business.blap.NPAL1360.NPAL1360_XCMsgArray", null, null},
	
	{"L", "L", null, "200", "business.blap.NPAL1360.NPAL1360_LCMsgArray", null, null},
	
	{"R", "R", null, "15", "business.blap.NPAL1360.NPAL1360_RCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"WRK_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrkOrdNum_P1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_P1
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_P1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_P1
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_P1
        {"PRCH_REQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqNum_P1
        {"PRCH_REQ_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqLineNum_P1
        {"PRCH_REQ_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqLineSubNum_P1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_P1
        {"RQST_RCV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDt_P1
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"WRK_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrkOrdNum
        {"WRK_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrkOrdNum_SV
        {"DS_WRK_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsWrkOrdTpCd_CD
        {"DS_WRK_ORD_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsWrkOrdTpDescTxt_NM
        {"DS_WRK_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsWrkOrdTpCd_SL
        {"WRK_ORD_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrkOrdStsCd
        {"DS_WRK_ORD_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsWrkOrdStsNm
        {"WRK_ORD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrkOrdDt
        {"PRCH_REQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqNum
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_WH
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_SW
        {"CPLT_RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpltRtlSwhCd
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm
        {"RQST_RCV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDt
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_KT
        {"FNSH_GOODS_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fnshGoodsMdseCd
        {"FNSH_MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fnshMdseDescShortTxt
        {"FNSH_GOODS_MDSE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fnshGoodsMdseNm
        {"FIRST_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstProdCtrlCd
        {"RCV_SER_TAKE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcvSerTakeFlg
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"SER_NUM_TAKE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNumTakeFlg
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
        {"BASE_PKG_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePkgUomCd
        {"FNSH_GOODS_ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fnshGoodsOrdQty
        {"FNSH_GOODS_RCV_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fnshGoodsRcvQty
        {"FNSH_GOODS_BAL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fnshGoodsBalQty
        {"FNSH_GOODS_CANC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fnshGoodsCancQty
        {"WRK_ORD_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrkOrdMsgTxt
        {"WRK_ORD_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrkOrdDtlLineNum_SL
        {"CMPSN_REVN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnRevnNum
        {"TRX_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxSrcTpCd
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd
        {"INVTY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyCtrlFlg
        {"INSTL_BASE_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//instlBaseCtrlFlg
        {"INVTY_ALLOC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyAllocQty
        {"TOT_ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totOrdQty
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum
        {"XX_YES_NO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd
        {"XX_RQST_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTs_WH
        {"XX_RQST_TZ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTz_WH
        {"XX_RQST_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTs_DH
        {"XX_RQST_TZ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTz_DH
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
		null,	//A
		null,	//S
		null,	//W
        {"EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eventNm
		null,	//P
		null,	//X
		null,	//L
		null,	//R
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
