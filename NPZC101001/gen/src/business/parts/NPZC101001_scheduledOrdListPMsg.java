//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20230417100138000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPZC101001_scheduledOrdListPMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPZC101001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPZC101001_scheduledOrdListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CUR_INVTY_QTY*/
	public final EZDPBigDecimalItem              curInvtyQty;

    /** SCHD_INBD_QTY*/
	public final EZDPBigDecimalItem              schdInbdQty;

    /** SCHD_OTBD_QTY*/
	public final EZDPBigDecimalItem              schdOtbdQty;

    /** CUR_INVTY_AVAL_QTY*/
	public final EZDPBigDecimalItem              curInvtyAvalQty;

    /** PRCH_REQ_QTY*/
	public final EZDPBigDecimalItem              prchReqQty;

    /** XX_MDSE_CD*/
	public final EZDPStringItem              xxMdseCd;

    /** XX_INVTY_LOC_CD*/
	public final EZDPStringItem              xxInvtyLocCd;

    /** ETA_ETD_DT*/
	public final EZDPDateItem              etaEtdDt;

    /** INBD_OTBD_NM*/
	public final EZDPStringItem              inbdOtbdNm;

    /** ORD_DT*/
	public final EZDPDateItem              ordDt;

    /** AR_TRX_TP_NM*/
	public final EZDPStringItem              arTrxTpNm;

    /** ORIG_ORD_NUM*/
	public final EZDPStringItem              origOrdNum;

    /** ORD_LINE_NUM*/
	public final EZDPStringItem              ordLineNum;

    /** ORD_TP_DESC_TXT*/
	public final EZDPStringItem              ordTpDescTxt;

    /** RQST_RCV_DT*/
	public final EZDPDateItem              rqstRcvDt;

    /** XX_DPLY_ORD_LINE_NUM*/
	public final EZDPStringItem              xxDplyOrdLineNum;

    /** ORD_HDR_DPLY_STS_CD*/
	public final EZDPStringItem              ordHdrDplyStsCd;

    /** ORD_HDR_DPLY_STS_DESC_TXT*/
	public final EZDPStringItem              ordHdrDplyStsDescTxt;

    /** SHPG_STS_CD*/
	public final EZDPStringItem              shpgStsCd;

    /** SHPG_STS_NM*/
	public final EZDPStringItem              shpgStsNm;

    /** APVL_STS_CD*/
	public final EZDPStringItem              apvlStsCd;

    /** APVL_STS_NM*/
	public final EZDPStringItem              apvlStsNm;

    /** XX_REC_HIST_CRAT_TS_A0*/
	public final EZDPStringItem              xxRecHistCratTs_A0;

    /** XX_REC_HIST_CRAT_BY_NM_A0*/
	public final EZDPStringItem              xxRecHistCratByNm_A0;

    /** XX_REC_HIST_UPD_TS_A0*/
	public final EZDPStringItem              xxRecHistUpdTs_A0;

    /** XX_REC_HIST_UPD_BY_NM_A0*/
	public final EZDPStringItem              xxRecHistUpdByNm_A0;

    /** XX_REC_HIST_TBL_NM_A0*/
	public final EZDPStringItem              xxRecHistTblNm_A0;


	/**
	 * NPZC101001_scheduledOrdListPMsg is constructor.
	 * The initialization when the instance of NPZC101001_scheduledOrdListPMsg is generated.
	 */
	public NPZC101001_scheduledOrdListPMsg() {
		this(false, -1);
	}

	/**
	 * NPZC101001_scheduledOrdListPMsg is constructor.
	 * The initialization when the instance of NPZC101001_scheduledOrdListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPZC101001_scheduledOrdListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		curInvtyQty = (EZDPBigDecimalItem)newItem("curInvtyQty");
		schdInbdQty = (EZDPBigDecimalItem)newItem("schdInbdQty");
		schdOtbdQty = (EZDPBigDecimalItem)newItem("schdOtbdQty");
		curInvtyAvalQty = (EZDPBigDecimalItem)newItem("curInvtyAvalQty");
		prchReqQty = (EZDPBigDecimalItem)newItem("prchReqQty");
		xxMdseCd = (EZDPStringItem)newItem("xxMdseCd");
		xxInvtyLocCd = (EZDPStringItem)newItem("xxInvtyLocCd");
		etaEtdDt = (EZDPDateItem)newItem("etaEtdDt");
		inbdOtbdNm = (EZDPStringItem)newItem("inbdOtbdNm");
		ordDt = (EZDPDateItem)newItem("ordDt");
		arTrxTpNm = (EZDPStringItem)newItem("arTrxTpNm");
		origOrdNum = (EZDPStringItem)newItem("origOrdNum");
		ordLineNum = (EZDPStringItem)newItem("ordLineNum");
		ordTpDescTxt = (EZDPStringItem)newItem("ordTpDescTxt");
		rqstRcvDt = (EZDPDateItem)newItem("rqstRcvDt");
		xxDplyOrdLineNum = (EZDPStringItem)newItem("xxDplyOrdLineNum");
		ordHdrDplyStsCd = (EZDPStringItem)newItem("ordHdrDplyStsCd");
		ordHdrDplyStsDescTxt = (EZDPStringItem)newItem("ordHdrDplyStsDescTxt");
		shpgStsCd = (EZDPStringItem)newItem("shpgStsCd");
		shpgStsNm = (EZDPStringItem)newItem("shpgStsNm");
		apvlStsCd = (EZDPStringItem)newItem("apvlStsCd");
		apvlStsNm = (EZDPStringItem)newItem("apvlStsNm");
		xxRecHistCratTs_A0 = (EZDPStringItem)newItem("xxRecHistCratTs_A0");
		xxRecHistCratByNm_A0 = (EZDPStringItem)newItem("xxRecHistCratByNm_A0");
		xxRecHistUpdTs_A0 = (EZDPStringItem)newItem("xxRecHistUpdTs_A0");
		xxRecHistUpdByNm_A0 = (EZDPStringItem)newItem("xxRecHistUpdByNm_A0");
		xxRecHistTblNm_A0 = (EZDPStringItem)newItem("xxRecHistTblNm_A0");
	}

	/**
	 * get the type of array which is stored
	 * @return NPZC101001_scheduledOrdListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPZC101001_scheduledOrdListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"curInvtyQty", "curInvtyQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"schdInbdQty", "schdInbdQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"schdOtbdQty", "schdOtbdQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"curInvtyAvalQty", "curInvtyAvalQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"prchReqQty", "prchReqQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxMdseCd", "xxMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"xxInvtyLocCd", "xxInvtyLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"etaEtdDt", "etaEtdDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"inbdOtbdNm", "inbdOtbdNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"ordDt", "ordDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"arTrxTpNm", "arTrxTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"origOrdNum", "origOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"ordLineNum", "ordLineNum", null, null, TYPE_HANKAKUEISU, "6", null},
	{"ordTpDescTxt", "ordTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"rqstRcvDt", "rqstRcvDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxDplyOrdLineNum", "xxDplyOrdLineNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"ordHdrDplyStsCd", "ordHdrDplyStsCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"ordHdrDplyStsDescTxt", "ordHdrDplyStsDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"shpgStsCd", "shpgStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"shpgStsNm", "shpgStsNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"apvlStsCd", "apvlStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"apvlStsNm", "apvlStsNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxRecHistCratTs_A0", "xxRecHistCratTs_A0", "A0", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A0", "xxRecHistCratByNm_A0", "A0", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A0", "xxRecHistUpdTs_A0", "A0", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A0", "xxRecHistUpdByNm_A0", "A0", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A0", "xxRecHistTblNm_A0", "A0", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CUR_INVTY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//curInvtyQty
        {"SCHD_INBD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdInbdQty
        {"SCHD_OTBD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdOtbdQty
        {"CUR_INVTY_AVAL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//curInvtyAvalQty
        {"PRCH_REQ_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqQty
        {"XX_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMdseCd
        {"XX_INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInvtyLocCd
        {"ETA_ETD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//etaEtdDt
        {"INBD_OTBD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdOtbdNm
        {"ORD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordDt
        {"AR_TRX_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxTpNm
        {"ORIG_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origOrdNum
        {"ORD_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLineNum
        {"ORD_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordTpDescTxt
        {"RQST_RCV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDt
        {"XX_DPLY_ORD_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyOrdLineNum
        {"ORD_HDR_DPLY_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordHdrDplyStsCd
        {"ORD_HDR_DPLY_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordHdrDplyStsDescTxt
        {"SHPG_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgStsCd
        {"SHPG_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgStsNm
        {"APVL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlStsCd
        {"APVL_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlStsNm
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A0
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A0
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A0
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A0
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A0
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
