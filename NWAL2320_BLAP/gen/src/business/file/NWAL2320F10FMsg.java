//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20170531170624000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2320F10FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWAL2320F10 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2320F10FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PROC_GRP_NUM*/
	public final EZDFStringItem              procGrpNum;

    /** CUST_ISS_PO_NUM*/
	public final EZDFStringItem              custIssPoNum;

    /** AQU_NUM*/
	public final EZDFStringItem              aquNum;

    /** NEGO_DEAL_AMT*/
	public final EZDFBigDecimalItem              negoDealAmt;

    /** DS_ORD_CATG_DESC_TXT*/
	public final EZDFStringItem              dsOrdCatgDescTxt;

    /** DS_ORD_TP_DESC_TXT*/
	public final EZDFStringItem              dsOrdTpDescTxt;

    /** MDSE_CD*/
	public final EZDFStringItem              mdseCd;

    /** ORD_QTY*/
	public final EZDFBigDecimalItem              ordQty;

    /** ENT_DEAL_NET_UNIT_PRC_AMT*/
	public final EZDFBigDecimalItem              entDealNetUnitPrcAmt;

    /** DS_ORD_POSN_NUM*/
	public final EZDFStringItem              dsOrdPosnNum;

    /** DS_ORD_LINE_CATG_DESC_TXT*/
	public final EZDFStringItem              dsOrdLineCatgDescTxt;

    /** BILL_TO_CUST_LOC_CD*/
	public final EZDFStringItem              billToCustLocCd;

    /** SHIP_TO_CUST_LOC_CD*/
	public final EZDFStringItem              shipToCustLocCd;

    /** SOLD_TO_CUST_LOC_CD*/
	public final EZDFStringItem              soldToCustLocCd;

    /** TOC_NM*/
	public final EZDFStringItem              tocNm;

    /** RTL_WH_NM*/
	public final EZDFStringItem              rtlWhNm;

    /** RTL_SWH_NM*/
	public final EZDFStringItem              rtlSwhNm;

    /** XX_SHPG_ORD_CMNT_TXT*/
	public final EZDFStringItem              xxShpgOrdCmntTxt;

    /** ORD_UPLD_VLD_STS_DESC_TXT*/
	public final EZDFStringItem              ordUpldVldStsDescTxt;

    /** XX_DS_MULT_MSG_DPLY_TXT*/
	public final EZDFStringItem              xxDsMultMsgDplyTxt;


	/**
	 * NWAL2320F10FMsg is constructor.
	 * The initialization when the instance of NWAL2320F10FMsg is generated.
	 */
	public NWAL2320F10FMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2320F10FMsg is constructor.
	 * The initialization when the instance of NWAL2320F10FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2320F10FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		procGrpNum = (EZDFStringItem)newItem("procGrpNum");
		custIssPoNum = (EZDFStringItem)newItem("custIssPoNum");
		aquNum = (EZDFStringItem)newItem("aquNum");
		negoDealAmt = (EZDFBigDecimalItem)newItem("negoDealAmt");
		dsOrdCatgDescTxt = (EZDFStringItem)newItem("dsOrdCatgDescTxt");
		dsOrdTpDescTxt = (EZDFStringItem)newItem("dsOrdTpDescTxt");
		mdseCd = (EZDFStringItem)newItem("mdseCd");
		ordQty = (EZDFBigDecimalItem)newItem("ordQty");
		entDealNetUnitPrcAmt = (EZDFBigDecimalItem)newItem("entDealNetUnitPrcAmt");
		dsOrdPosnNum = (EZDFStringItem)newItem("dsOrdPosnNum");
		dsOrdLineCatgDescTxt = (EZDFStringItem)newItem("dsOrdLineCatgDescTxt");
		billToCustLocCd = (EZDFStringItem)newItem("billToCustLocCd");
		shipToCustLocCd = (EZDFStringItem)newItem("shipToCustLocCd");
		soldToCustLocCd = (EZDFStringItem)newItem("soldToCustLocCd");
		tocNm = (EZDFStringItem)newItem("tocNm");
		rtlWhNm = (EZDFStringItem)newItem("rtlWhNm");
		rtlSwhNm = (EZDFStringItem)newItem("rtlSwhNm");
		xxShpgOrdCmntTxt = (EZDFStringItem)newItem("xxShpgOrdCmntTxt");
		ordUpldVldStsDescTxt = (EZDFStringItem)newItem("ordUpldVldStsDescTxt");
		xxDsMultMsgDplyTxt = (EZDFStringItem)newItem("xxDsMultMsgDplyTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2320F10FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2320F10FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"procGrpNum", "procGrpNum", null, null, TYPE_HANKAKUEISU, "7", null},
	{"custIssPoNum", "custIssPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"aquNum", "aquNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"negoDealAmt", "negoDealAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsOrdCatgDescTxt", "dsOrdCatgDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdTpDescTxt", "dsOrdTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"ordQty", "ordQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"entDealNetUnitPrcAmt", "entDealNetUnitPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsOrdPosnNum", "dsOrdPosnNum", null, null, TYPE_HANKAKUEISU, "6", null},
	{"dsOrdLineCatgDescTxt", "dsOrdLineCatgDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"billToCustLocCd", "billToCustLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustLocCd", "shipToCustLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"soldToCustLocCd", "soldToCustLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"tocNm", "tocNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"rtlWhNm", "rtlWhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhNm", "rtlSwhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxShpgOrdCmntTxt", "xxShpgOrdCmntTxt", null, null, TYPE_HANKAKUEISU, "240", null},
	{"ordUpldVldStsDescTxt", "ordUpldVldStsDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxDsMultMsgDplyTxt", "xxDsMultMsgDplyTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PROC_GRP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procGrpNum
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum
        {"AQU_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aquNum
        {"NEGO_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//negoDealAmt
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt
        {"DS_ORD_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpDescTxt
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty
        {"ENT_DEAL_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entDealNetUnitPrcAmt
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum
        {"DS_ORD_LINE_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgDescTxt
        {"BILL_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustLocCd
        {"SHIP_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustLocCd
        {"SOLD_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustLocCd
        {"TOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocNm
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm
        {"XX_SHPG_ORD_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShpgOrdCmntTxt
        {"ORD_UPLD_VLD_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordUpldVldStsDescTxt
        {"XX_DS_MULT_MSG_DPLY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMultMsgDplyTxt
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
