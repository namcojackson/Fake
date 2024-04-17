//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170531165913000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2320_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2320;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2320 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2320_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PROC_GRP_NUM_CO*/
	public final EZDCStringItem              procGrpNum_CO;

    /** CUST_ISS_PO_NUM_CO*/
	public final EZDCStringItem              custIssPoNum_CO;

    /** AQU_NUM_CO*/
	public final EZDCStringItem              aquNum_CO;

    /** NEGO_DEAL_AMT_CO*/
	public final EZDCBigDecimalItem              negoDealAmt_CO;

    /** DS_ORD_CATG_CD_CO*/
	public final EZDCStringItem              dsOrdCatgCd_CO;

    /** DS_ORD_CATG_DESC_TXT_CO*/
	public final EZDCStringItem              dsOrdCatgDescTxt_CO;

    /** DS_ORD_TP_CD_CO*/
	public final EZDCStringItem              dsOrdTpCd_CO;

    /** DS_ORD_TP_DESC_TXT_CO*/
	public final EZDCStringItem              dsOrdTpDescTxt_CO;

    /** MDSE_CD_CO*/
	public final EZDCStringItem              mdseCd_CO;

    /** MDSE_NM_CO*/
	public final EZDCStringItem              mdseNm_CO;

    /** ORD_QTY_CO*/
	public final EZDCBigDecimalItem              ordQty_CO;

    /** ENT_DEAL_NET_UNIT_PRC_AMT_CO*/
	public final EZDCBigDecimalItem              entDealNetUnitPrcAmt_CO;

    /** DS_ORD_POSN_NUM_CO*/
	public final EZDCStringItem              dsOrdPosnNum_CO;

    /** DS_ORD_LINE_CATG_CD_CO*/
	public final EZDCStringItem              dsOrdLineCatgCd_CO;

    /** DS_ORD_LINE_CATG_DESC_TXT_CO*/
	public final EZDCStringItem              dsOrdLineCatgDescTxt_CO;

    /** BILL_TO_CUST_LOC_CD_CO*/
	public final EZDCStringItem              billToCustLocCd_CO;

    /** SHIP_TO_CUST_LOC_CD_CO*/
	public final EZDCStringItem              shipToCustLocCd_CO;

    /** SOLD_TO_CUST_LOC_CD_CO*/
	public final EZDCStringItem              soldToCustLocCd_CO;

    /** SLS_REP_TOC_CD_CO*/
	public final EZDCStringItem              slsRepTocCd_CO;

    /** TOC_NM_CO*/
	public final EZDCStringItem              tocNm_CO;

    /** SLS_REP_ROLE_TP_CD_CO*/
	public final EZDCStringItem              slsRepRoleTpCd_CO;

    /** COA_EXTN_CD_CO*/
	public final EZDCStringItem              coaExtnCd_CO;

    /** RTL_WH_CD_CO*/
	public final EZDCStringItem              rtlWhCd_CO;

    /** RTL_WH_NM_CO*/
	public final EZDCStringItem              rtlWhNm_CO;

    /** RTL_SWH_CD_CO*/
	public final EZDCStringItem              rtlSwhCd_CO;

    /** RTL_SWH_NM_CO*/
	public final EZDCStringItem              rtlSwhNm_CO;

    /** XX_SHPG_ORD_CMNT_TXT_CO*/
	public final EZDCStringItem              xxShpgOrdCmntTxt_CO;

    /** ORD_UPLD_VLD_STS_DESC_TXT_CO*/
	public final EZDCStringItem              ordUpldVldStsDescTxt_CO;

    /** XX_COMN_SCR_COL_VAL_TXT_CO*/
	public final EZDCStringItem              xxComnScrColValTxt_CO;

    /** XX_DS_MULT_MSG_DPLY_TXT_CO*/
	public final EZDCStringItem              xxDsMultMsgDplyTxt_CO;


	/**
	 * NWAL2320_ACMsg is constructor.
	 * The initialization when the instance of NWAL2320_ACMsg is generated.
	 */
	public NWAL2320_ACMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2320_ACMsg is constructor.
	 * The initialization when the instance of NWAL2320_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2320_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		procGrpNum_CO = (EZDCStringItem)newItem("procGrpNum_CO");
		custIssPoNum_CO = (EZDCStringItem)newItem("custIssPoNum_CO");
		aquNum_CO = (EZDCStringItem)newItem("aquNum_CO");
		negoDealAmt_CO = (EZDCBigDecimalItem)newItem("negoDealAmt_CO");
		dsOrdCatgCd_CO = (EZDCStringItem)newItem("dsOrdCatgCd_CO");
		dsOrdCatgDescTxt_CO = (EZDCStringItem)newItem("dsOrdCatgDescTxt_CO");
		dsOrdTpCd_CO = (EZDCStringItem)newItem("dsOrdTpCd_CO");
		dsOrdTpDescTxt_CO = (EZDCStringItem)newItem("dsOrdTpDescTxt_CO");
		mdseCd_CO = (EZDCStringItem)newItem("mdseCd_CO");
		mdseNm_CO = (EZDCStringItem)newItem("mdseNm_CO");
		ordQty_CO = (EZDCBigDecimalItem)newItem("ordQty_CO");
		entDealNetUnitPrcAmt_CO = (EZDCBigDecimalItem)newItem("entDealNetUnitPrcAmt_CO");
		dsOrdPosnNum_CO = (EZDCStringItem)newItem("dsOrdPosnNum_CO");
		dsOrdLineCatgCd_CO = (EZDCStringItem)newItem("dsOrdLineCatgCd_CO");
		dsOrdLineCatgDescTxt_CO = (EZDCStringItem)newItem("dsOrdLineCatgDescTxt_CO");
		billToCustLocCd_CO = (EZDCStringItem)newItem("billToCustLocCd_CO");
		shipToCustLocCd_CO = (EZDCStringItem)newItem("shipToCustLocCd_CO");
		soldToCustLocCd_CO = (EZDCStringItem)newItem("soldToCustLocCd_CO");
		slsRepTocCd_CO = (EZDCStringItem)newItem("slsRepTocCd_CO");
		tocNm_CO = (EZDCStringItem)newItem("tocNm_CO");
		slsRepRoleTpCd_CO = (EZDCStringItem)newItem("slsRepRoleTpCd_CO");
		coaExtnCd_CO = (EZDCStringItem)newItem("coaExtnCd_CO");
		rtlWhCd_CO = (EZDCStringItem)newItem("rtlWhCd_CO");
		rtlWhNm_CO = (EZDCStringItem)newItem("rtlWhNm_CO");
		rtlSwhCd_CO = (EZDCStringItem)newItem("rtlSwhCd_CO");
		rtlSwhNm_CO = (EZDCStringItem)newItem("rtlSwhNm_CO");
		xxShpgOrdCmntTxt_CO = (EZDCStringItem)newItem("xxShpgOrdCmntTxt_CO");
		ordUpldVldStsDescTxt_CO = (EZDCStringItem)newItem("ordUpldVldStsDescTxt_CO");
		xxComnScrColValTxt_CO = (EZDCStringItem)newItem("xxComnScrColValTxt_CO");
		xxDsMultMsgDplyTxt_CO = (EZDCStringItem)newItem("xxDsMultMsgDplyTxt_CO");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2320_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2320_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"procGrpNum_CO", "procGrpNum_CO", "CO", null, TYPE_HANKAKUEISU, "7", null},
	{"custIssPoNum_CO", "custIssPoNum_CO", "CO", null, TYPE_HANKAKUEISU, "35", null},
	{"aquNum_CO", "aquNum_CO", "CO", null, TYPE_HANKAKUEISU, "35", null},
	{"negoDealAmt_CO", "negoDealAmt_CO", "CO", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsOrdCatgCd_CO", "dsOrdCatgCd_CO", "CO", null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdCatgDescTxt_CO", "dsOrdCatgDescTxt_CO", "CO", null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdTpCd_CO", "dsOrdTpCd_CO", "CO", null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdTpDescTxt_CO", "dsOrdTpDescTxt_CO", "CO", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseCd_CO", "mdseCd_CO", "CO", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseNm_CO", "mdseNm_CO", "CO", null, TYPE_HANKAKUEISU, "30", null},
	{"ordQty_CO", "ordQty_CO", "CO", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"entDealNetUnitPrcAmt_CO", "entDealNetUnitPrcAmt_CO", "CO", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsOrdPosnNum_CO", "dsOrdPosnNum_CO", "CO", null, TYPE_HANKAKUEISU, "6", null},
	{"dsOrdLineCatgCd_CO", "dsOrdLineCatgCd_CO", "CO", null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdLineCatgDescTxt_CO", "dsOrdLineCatgDescTxt_CO", "CO", null, TYPE_HANKAKUEISU, "50", null},
	{"billToCustLocCd_CO", "billToCustLocCd_CO", "CO", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustLocCd_CO", "shipToCustLocCd_CO", "CO", null, TYPE_HANKAKUEISU, "20", null},
	{"soldToCustLocCd_CO", "soldToCustLocCd_CO", "CO", null, TYPE_HANKAKUEISU, "20", null},
	{"slsRepTocCd_CO", "slsRepTocCd_CO", "CO", null, TYPE_HANKAKUEISU, "8", null},
	{"tocNm_CO", "tocNm_CO", "CO", null, TYPE_HANKAKUEISU, "50", null},
	{"slsRepRoleTpCd_CO", "slsRepRoleTpCd_CO", "CO", null, TYPE_HANKAKUEISU, "10", null},
	{"coaExtnCd_CO", "coaExtnCd_CO", "CO", null, TYPE_HANKAKUEISU, "3", null},
	{"rtlWhCd_CO", "rtlWhCd_CO", "CO", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_CO", "rtlWhNm_CO", "CO", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_CO", "rtlSwhCd_CO", "CO", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_CO", "rtlSwhNm_CO", "CO", null, TYPE_HANKAKUEISU, "30", null},
	{"xxShpgOrdCmntTxt_CO", "xxShpgOrdCmntTxt_CO", "CO", null, TYPE_HANKAKUEISU, "240", null},
	{"ordUpldVldStsDescTxt_CO", "ordUpldVldStsDescTxt_CO", "CO", null, TYPE_HANKAKUEISU, "50", null},
	{"xxComnScrColValTxt_CO", "xxComnScrColValTxt_CO", "CO", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxDsMultMsgDplyTxt_CO", "xxDsMultMsgDplyTxt_CO", "CO", null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PROC_GRP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procGrpNum_CO
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_CO
        {"AQU_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aquNum_CO
        {"NEGO_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//negoDealAmt_CO
        {"DS_ORD_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd_CO
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt_CO
        {"DS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd_CO
        {"DS_ORD_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpDescTxt_CO
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_CO
        {"MDSE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseNm_CO
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_CO
        {"ENT_DEAL_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entDealNetUnitPrcAmt_CO
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_CO
        {"DS_ORD_LINE_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgCd_CO
        {"DS_ORD_LINE_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgDescTxt_CO
        {"BILL_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustLocCd_CO
        {"SHIP_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustLocCd_CO
        {"SOLD_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustLocCd_CO
        {"SLS_REP_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd_CO
        {"TOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocNm_CO
        {"SLS_REP_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepRoleTpCd_CO
        {"COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_CO
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_CO
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_CO
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_CO
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_CO
        {"XX_SHPG_ORD_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShpgOrdCmntTxt_CO
        {"ORD_UPLD_VLD_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordUpldVldStsDescTxt_CO
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_CO
        {"XX_DS_MULT_MSG_DPLY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMultMsgDplyTxt_CO
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
