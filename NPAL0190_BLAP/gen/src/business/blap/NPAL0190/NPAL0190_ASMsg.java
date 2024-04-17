//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160406040250000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL0190_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL0190;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL0190 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL0190_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** EVENT_ID*/
	public final EZDSStringItem              eventId;

    /** XX_SCR_ITEM_19_TXT*/
	public final EZDSStringItem              xxScrItem19Txt;

    /** PO_SUBMT_PSN_CD*/
	public final EZDSStringItem              poSubmtPsnCd;

    /** PO_HDR_STS_DESC_TXT*/
	public final EZDSStringItem              poHdrStsDescTxt;

    /** PO_APVL_STS_DESC_TXT*/
	public final EZDSStringItem              poApvlStsDescTxt;

    /** DISP_PO_DTL_LINE_NUM*/
	public final EZDSStringItem              dispPoDtlLineNum;

    /** PO_LINE_STS_DESC_TXT*/
	public final EZDSStringItem              poLineStsDescTxt;

    /** MDSE_CD*/
	public final EZDSStringItem              mdseCd;

    /** ASL_MDSE_CD*/
	public final EZDSStringItem              aslMdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDSStringItem              mdseDescShortTxt;

    /** ENT_DEAL_NET_UNIT_PRC_AMT*/
	public final EZDSBigDecimalItem              entDealNetUnitPrcAmt;

    /** PO_DISP_QTY*/
	public final EZDSBigDecimalItem              poDispQty;

    /** PKG_UOM_CLS_DESC_TXT*/
	public final EZDSStringItem              pkgUomClsDescTxt;

    /** ENT_PO_DTL_DEAL_NET_AMT*/
	public final EZDSBigDecimalItem              entPoDtlDealNetAmt;

    /** RQST_RCV_DT*/
	public final EZDSDateItem              rqstRcvDt;

    /** SRC_RTL_WH_CD*/
	public final EZDSStringItem              srcRtlWhCd;

    /** SRC_RTL_SWH_CD*/
	public final EZDSStringItem              srcRtlSwhCd;

    /** DEST_RTL_WH_CD*/
	public final EZDSStringItem              destRtlWhCd;

    /** DEST_RTL_SWH_CD*/
	public final EZDSStringItem              destRtlSwhCd;

    /** SHIP_TO_CUST_CD*/
	public final EZDSStringItem              shipToCustCd;

    /** SHIP_TO_LOC_NM*/
	public final EZDSStringItem              shipToLocNm;

    /** PO_MATCH_TP_DESC_TXT*/
	public final EZDSStringItem              poMatchTpDescTxt;

    /** PO_RCV_QTY*/
	public final EZDSBigDecimalItem              poRcvQty;

    /** PO_CANC_QTY*/
	public final EZDSBigDecimalItem              poCancQty;

    /** PO_INV_QTY*/
	public final EZDSBigDecimalItem              poInvQty;

    /** STK_STS_DESC_TXT_FR*/
	public final EZDSStringItem              stkStsDescTxt_FR;

    /** STK_STS_DESC_TXT_TO*/
	public final EZDSStringItem              stkStsDescTxt_TO;

    /** SER_NUM_LIST_TXT*/
	public final EZDSStringItem              serNumListTxt;

    /** XX_SCR_ITEM_130_TXT_CH*/
	public final EZDSStringItem              xxScrItem130Txt_CH;

    /** XX_SCR_ITEM_130_TXT_AC*/
	public final EZDSStringItem              xxScrItem130Txt_AC;

    /** XX_SCR_ITEM_130_TXT_VA*/
	public final EZDSStringItem              xxScrItem130Txt_VA;

    /** PO_ORD_DTL_CMNT_TXT*/
	public final EZDSStringItem              poOrdDtlCmntTxt;


	/**
	 * NPAL0190_ASMsg is constructor.
	 * The initialization when the instance of NPAL0190_ASMsg is generated.
	 */
	public NPAL0190_ASMsg() {
		this(false, -1);
	}

	/**
	 * NPAL0190_ASMsg is constructor.
	 * The initialization when the instance of NPAL0190_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL0190_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		eventId = (EZDSStringItem)newItem("eventId");
		xxScrItem19Txt = (EZDSStringItem)newItem("xxScrItem19Txt");
		poSubmtPsnCd = (EZDSStringItem)newItem("poSubmtPsnCd");
		poHdrStsDescTxt = (EZDSStringItem)newItem("poHdrStsDescTxt");
		poApvlStsDescTxt = (EZDSStringItem)newItem("poApvlStsDescTxt");
		dispPoDtlLineNum = (EZDSStringItem)newItem("dispPoDtlLineNum");
		poLineStsDescTxt = (EZDSStringItem)newItem("poLineStsDescTxt");
		mdseCd = (EZDSStringItem)newItem("mdseCd");
		aslMdseCd = (EZDSStringItem)newItem("aslMdseCd");
		mdseDescShortTxt = (EZDSStringItem)newItem("mdseDescShortTxt");
		entDealNetUnitPrcAmt = (EZDSBigDecimalItem)newItem("entDealNetUnitPrcAmt");
		poDispQty = (EZDSBigDecimalItem)newItem("poDispQty");
		pkgUomClsDescTxt = (EZDSStringItem)newItem("pkgUomClsDescTxt");
		entPoDtlDealNetAmt = (EZDSBigDecimalItem)newItem("entPoDtlDealNetAmt");
		rqstRcvDt = (EZDSDateItem)newItem("rqstRcvDt");
		srcRtlWhCd = (EZDSStringItem)newItem("srcRtlWhCd");
		srcRtlSwhCd = (EZDSStringItem)newItem("srcRtlSwhCd");
		destRtlWhCd = (EZDSStringItem)newItem("destRtlWhCd");
		destRtlSwhCd = (EZDSStringItem)newItem("destRtlSwhCd");
		shipToCustCd = (EZDSStringItem)newItem("shipToCustCd");
		shipToLocNm = (EZDSStringItem)newItem("shipToLocNm");
		poMatchTpDescTxt = (EZDSStringItem)newItem("poMatchTpDescTxt");
		poRcvQty = (EZDSBigDecimalItem)newItem("poRcvQty");
		poCancQty = (EZDSBigDecimalItem)newItem("poCancQty");
		poInvQty = (EZDSBigDecimalItem)newItem("poInvQty");
		stkStsDescTxt_FR = (EZDSStringItem)newItem("stkStsDescTxt_FR");
		stkStsDescTxt_TO = (EZDSStringItem)newItem("stkStsDescTxt_TO");
		serNumListTxt = (EZDSStringItem)newItem("serNumListTxt");
		xxScrItem130Txt_CH = (EZDSStringItem)newItem("xxScrItem130Txt_CH");
		xxScrItem130Txt_AC = (EZDSStringItem)newItem("xxScrItem130Txt_AC");
		xxScrItem130Txt_VA = (EZDSStringItem)newItem("xxScrItem130Txt_VA");
		poOrdDtlCmntTxt = (EZDSStringItem)newItem("poOrdDtlCmntTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL0190_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL0190_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"eventId", "eventId", null, null, TYPE_HANKAKUEISU, "32", null},
	{"xxScrItem19Txt", "xxScrItem19Txt", null, null, TYPE_HANKAKUEISU, "19", null},
	{"poSubmtPsnCd", "poSubmtPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"poHdrStsDescTxt", "poHdrStsDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"poApvlStsDescTxt", "poApvlStsDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dispPoDtlLineNum", "dispPoDtlLineNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"poLineStsDescTxt", "poLineStsDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"aslMdseCd", "aslMdseCd", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"entDealNetUnitPrcAmt", "entDealNetUnitPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"poDispQty", "poDispQty", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"pkgUomClsDescTxt", "pkgUomClsDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"entPoDtlDealNetAmt", "entPoDtlDealNetAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"rqstRcvDt", "rqstRcvDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"srcRtlWhCd", "srcRtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"srcRtlSwhCd", "srcRtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"destRtlWhCd", "destRtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"destRtlSwhCd", "destRtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd", "shipToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"shipToLocNm", "shipToLocNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"poMatchTpDescTxt", "poMatchTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"poRcvQty", "poRcvQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"poCancQty", "poCancQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"poInvQty", "poInvQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"stkStsDescTxt_FR", "stkStsDescTxt_FR", "FR", null, TYPE_HANKAKUEISU, "50", null},
	{"stkStsDescTxt_TO", "stkStsDescTxt_TO", "TO", null, TYPE_HANKAKUEISU, "50", null},
	{"serNumListTxt", "serNumListTxt", null, null, TYPE_HANKAKUEISU, "3000", null},
	{"xxScrItem130Txt_CH", "xxScrItem130Txt_CH", "CH", null, TYPE_HANKAKUEISU, "130", null},
	{"xxScrItem130Txt_AC", "xxScrItem130Txt_AC", "AC", null, TYPE_HANKAKUEISU, "130", null},
	{"xxScrItem130Txt_VA", "xxScrItem130Txt_VA", "VA", null, TYPE_HANKAKUEISU, "130", null},
	{"poOrdDtlCmntTxt", "poOrdDtlCmntTxt", null, null, TYPE_HANKAKUEISU, "120", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"EVENT_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eventId
        {"XX_SCR_ITEM_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem19Txt
        {"PO_SUBMT_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poSubmtPsnCd
        {"PO_HDR_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poHdrStsDescTxt
        {"PO_APVL_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poApvlStsDescTxt
        {"DISP_PO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dispPoDtlLineNum
        {"PO_LINE_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poLineStsDescTxt
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"ASL_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aslMdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"ENT_DEAL_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entDealNetUnitPrcAmt
        {"PO_DISP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poDispQty
        {"PKG_UOM_CLS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomClsDescTxt
        {"ENT_PO_DTL_DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entPoDtlDealNetAmt
        {"RQST_RCV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDt
        {"SRC_RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlWhCd
        {"SRC_RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlSwhCd
        {"DEST_RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//destRtlWhCd
        {"DEST_RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//destRtlSwhCd
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd
        {"SHIP_TO_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToLocNm
        {"PO_MATCH_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poMatchTpDescTxt
        {"PO_RCV_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poRcvQty
        {"PO_CANC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poCancQty
        {"PO_INV_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poInvQty
        {"STK_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsDescTxt_FR
        {"STK_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsDescTxt_TO
        {"SER_NUM_LIST_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNumListTxt
        {"XX_SCR_ITEM_130_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem130Txt_CH
        {"XX_SCR_ITEM_130_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem130Txt_AC
        {"XX_SCR_ITEM_130_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem130Txt_VA
        {"PO_ORD_DTL_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdDtlCmntTxt
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

