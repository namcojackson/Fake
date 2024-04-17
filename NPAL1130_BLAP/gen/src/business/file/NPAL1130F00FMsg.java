//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20180622093444000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1130F00FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1130F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1130F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD*/
	public final EZDFStringItem              mdseCd;

    /** INVTY_LOC_CD*/
	public final EZDFStringItem              invtyLocCd;

    /** ETA_ETD_DT*/
	public final EZDFDateItem              etaEtdDt;

    /** INBD_OTBD_NM*/
	public final EZDFStringItem              inbdOtbdNm;

    /** AR_TRX_TP_NM*/
	public final EZDFStringItem              arTrxTpNm;

    /** ORIG_ORD_NUM*/
	public final EZDFStringItem              origOrdNum;

    /** XX_DPLY_ORD_LINE_NUM*/
	public final EZDFStringItem              xxDplyOrdLineNum;

    /** ORD_TP_DESC_TXT*/
	public final EZDFStringItem              ordTpDescTxt;

    /** ORD_HDR_DPLY_STS_DESC_TXT*/
	public final EZDFStringItem              ordHdrDplyStsDescTxt;

    /** SHPG_STS_NM*/
	public final EZDFStringItem              shpgStsNm;

    /** APVL_STS_NM*/
	public final EZDFStringItem              apvlStsNm;

    /** ORD_QTY*/
	public final EZDFBigDecimalItem              ordQty;

    /** AVAL_QTY*/
	public final EZDFBigDecimalItem              avalQty;

    /** SUPD_FLG*/
	public final EZDFStringItem              supdFlg;

    /** ORD_DT*/
	public final EZDFDateItem              ordDt;

    /** RQST_RCV_DT*/
	public final EZDFDateItem              rqstRcvDt;


	/**
	 * NPAL1130F00FMsg is constructor.
	 * The initialization when the instance of NPAL1130F00FMsg is generated.
	 */
	public NPAL1130F00FMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1130F00FMsg is constructor.
	 * The initialization when the instance of NPAL1130F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1130F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd = (EZDFStringItem)newItem("mdseCd");
		invtyLocCd = (EZDFStringItem)newItem("invtyLocCd");
		etaEtdDt = (EZDFDateItem)newItem("etaEtdDt");
		inbdOtbdNm = (EZDFStringItem)newItem("inbdOtbdNm");
		arTrxTpNm = (EZDFStringItem)newItem("arTrxTpNm");
		origOrdNum = (EZDFStringItem)newItem("origOrdNum");
		xxDplyOrdLineNum = (EZDFStringItem)newItem("xxDplyOrdLineNum");
		ordTpDescTxt = (EZDFStringItem)newItem("ordTpDescTxt");
		ordHdrDplyStsDescTxt = (EZDFStringItem)newItem("ordHdrDplyStsDescTxt");
		shpgStsNm = (EZDFStringItem)newItem("shpgStsNm");
		apvlStsNm = (EZDFStringItem)newItem("apvlStsNm");
		ordQty = (EZDFBigDecimalItem)newItem("ordQty");
		avalQty = (EZDFBigDecimalItem)newItem("avalQty");
		supdFlg = (EZDFStringItem)newItem("supdFlg");
		ordDt = (EZDFDateItem)newItem("ordDt");
		rqstRcvDt = (EZDFDateItem)newItem("rqstRcvDt");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1130F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1130F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"invtyLocCd", "invtyLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"etaEtdDt", "etaEtdDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"inbdOtbdNm", "inbdOtbdNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"arTrxTpNm", "arTrxTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"origOrdNum", "origOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"xxDplyOrdLineNum", "xxDplyOrdLineNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"ordTpDescTxt", "ordTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"ordHdrDplyStsDescTxt", "ordHdrDplyStsDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"shpgStsNm", "shpgStsNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"apvlStsNm", "apvlStsNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"ordQty", "ordQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"avalQty", "avalQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"supdFlg", "supdFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"ordDt", "ordDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"rqstRcvDt", "rqstRcvDt", null, null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd
        {"ETA_ETD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//etaEtdDt
        {"INBD_OTBD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdOtbdNm
        {"AR_TRX_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxTpNm
        {"ORIG_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origOrdNum
        {"XX_DPLY_ORD_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyOrdLineNum
        {"ORD_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordTpDescTxt
        {"ORD_HDR_DPLY_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordHdrDplyStsDescTxt
        {"SHPG_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgStsNm
        {"APVL_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlStsNm
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty
        {"AVAL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//avalQty
        {"SUPD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdFlg
        {"ORD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordDt
        {"RQST_RCV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDt
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
