//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170531165915000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2320_CSMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWAL2320 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2320_CSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PROC_GRP_NUM_NR*/
	public final EZDSStringItem              procGrpNum_NR;

    /** CUST_ISS_PO_NUM_NR*/
	public final EZDSStringItem              custIssPoNum_NR;

    /** AQU_NUM_NR*/
	public final EZDSStringItem              aquNum_NR;

    /** NEGO_DEAL_AMT_NR*/
	public final EZDSBigDecimalItem              negoDealAmt_NR;

    /** DS_ORD_CATG_CD_NR*/
	public final EZDSStringItem              dsOrdCatgCd_NR;

    /** DS_ORD_CATG_DESC_TXT_NR*/
	public final EZDSStringItem              dsOrdCatgDescTxt_NR;

    /** DS_ORD_TP_CD_NR*/
	public final EZDSStringItem              dsOrdTpCd_NR;

    /** DS_ORD_TP_DESC_TXT_NR*/
	public final EZDSStringItem              dsOrdTpDescTxt_NR;

    /** MDSE_CD_NR*/
	public final EZDSStringItem              mdseCd_NR;

    /** MDSE_NM_NR*/
	public final EZDSStringItem              mdseNm_NR;

    /** ORD_QTY_NR*/
	public final EZDSBigDecimalItem              ordQty_NR;

    /** ENT_DEAL_NET_UNIT_PRC_AMT_NR*/
	public final EZDSBigDecimalItem              entDealNetUnitPrcAmt_NR;

    /** DS_ORD_POSN_NUM_NR*/
	public final EZDSStringItem              dsOrdPosnNum_NR;

    /** DS_ORD_LINE_CATG_CD_NR*/
	public final EZDSStringItem              dsOrdLineCatgCd_NR;

    /** DS_ORD_LINE_CATG_DESC_TXT_NR*/
	public final EZDSStringItem              dsOrdLineCatgDescTxt_NR;

    /** BILL_TO_CUST_LOC_CD_NR*/
	public final EZDSStringItem              billToCustLocCd_NR;

    /** SHIP_TO_CUST_LOC_CD_NR*/
	public final EZDSStringItem              shipToCustLocCd_NR;

    /** SOLD_TO_CUST_LOC_CD_NR*/
	public final EZDSStringItem              soldToCustLocCd_NR;

    /** SER_NUM_NR*/
	public final EZDSStringItem              serNum_NR;

    /** SLS_REP_TOC_CD_NR*/
	public final EZDSStringItem              slsRepTocCd_NR;

    /** TOC_NM_NR*/
	public final EZDSStringItem              tocNm_NR;

    /** SLS_REP_ROLE_TP_CD_NR*/
	public final EZDSStringItem              slsRepRoleTpCd_NR;

    /** COA_EXTN_CD_NR*/
	public final EZDSStringItem              coaExtnCd_NR;

    /** RTL_WH_CD_NR*/
	public final EZDSStringItem              rtlWhCd_NR;

    /** RTL_WH_NM_NR*/
	public final EZDSStringItem              rtlWhNm_NR;

    /** RTRN_RSN_CD_NR*/
	public final EZDSStringItem              rtrnRsnCd_NR;

    /** RTRN_RSN_DESC_TXT_NR*/
	public final EZDSStringItem              rtrnRsnDescTxt_NR;

    /** XX_SHPG_ORD_CMNT_TXT_NR*/
	public final EZDSStringItem              xxShpgOrdCmntTxt_NR;

    /** ORD_UPLD_VLD_STS_DESC_TXT_NR*/
	public final EZDSStringItem              ordUpldVldStsDescTxt_NR;

    /** XX_COMN_SCR_COL_VAL_TXT_NR*/
	public final EZDSStringItem              xxComnScrColValTxt_NR;

    /** XX_DS_MULT_MSG_DPLY_TXT_NR*/
	public final EZDSStringItem              xxDsMultMsgDplyTxt_NR;


	/**
	 * NWAL2320_CSMsg is constructor.
	 * The initialization when the instance of NWAL2320_CSMsg is generated.
	 */
	public NWAL2320_CSMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2320_CSMsg is constructor.
	 * The initialization when the instance of NWAL2320_CSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2320_CSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		procGrpNum_NR = (EZDSStringItem)newItem("procGrpNum_NR");
		custIssPoNum_NR = (EZDSStringItem)newItem("custIssPoNum_NR");
		aquNum_NR = (EZDSStringItem)newItem("aquNum_NR");
		negoDealAmt_NR = (EZDSBigDecimalItem)newItem("negoDealAmt_NR");
		dsOrdCatgCd_NR = (EZDSStringItem)newItem("dsOrdCatgCd_NR");
		dsOrdCatgDescTxt_NR = (EZDSStringItem)newItem("dsOrdCatgDescTxt_NR");
		dsOrdTpCd_NR = (EZDSStringItem)newItem("dsOrdTpCd_NR");
		dsOrdTpDescTxt_NR = (EZDSStringItem)newItem("dsOrdTpDescTxt_NR");
		mdseCd_NR = (EZDSStringItem)newItem("mdseCd_NR");
		mdseNm_NR = (EZDSStringItem)newItem("mdseNm_NR");
		ordQty_NR = (EZDSBigDecimalItem)newItem("ordQty_NR");
		entDealNetUnitPrcAmt_NR = (EZDSBigDecimalItem)newItem("entDealNetUnitPrcAmt_NR");
		dsOrdPosnNum_NR = (EZDSStringItem)newItem("dsOrdPosnNum_NR");
		dsOrdLineCatgCd_NR = (EZDSStringItem)newItem("dsOrdLineCatgCd_NR");
		dsOrdLineCatgDescTxt_NR = (EZDSStringItem)newItem("dsOrdLineCatgDescTxt_NR");
		billToCustLocCd_NR = (EZDSStringItem)newItem("billToCustLocCd_NR");
		shipToCustLocCd_NR = (EZDSStringItem)newItem("shipToCustLocCd_NR");
		soldToCustLocCd_NR = (EZDSStringItem)newItem("soldToCustLocCd_NR");
		serNum_NR = (EZDSStringItem)newItem("serNum_NR");
		slsRepTocCd_NR = (EZDSStringItem)newItem("slsRepTocCd_NR");
		tocNm_NR = (EZDSStringItem)newItem("tocNm_NR");
		slsRepRoleTpCd_NR = (EZDSStringItem)newItem("slsRepRoleTpCd_NR");
		coaExtnCd_NR = (EZDSStringItem)newItem("coaExtnCd_NR");
		rtlWhCd_NR = (EZDSStringItem)newItem("rtlWhCd_NR");
		rtlWhNm_NR = (EZDSStringItem)newItem("rtlWhNm_NR");
		rtrnRsnCd_NR = (EZDSStringItem)newItem("rtrnRsnCd_NR");
		rtrnRsnDescTxt_NR = (EZDSStringItem)newItem("rtrnRsnDescTxt_NR");
		xxShpgOrdCmntTxt_NR = (EZDSStringItem)newItem("xxShpgOrdCmntTxt_NR");
		ordUpldVldStsDescTxt_NR = (EZDSStringItem)newItem("ordUpldVldStsDescTxt_NR");
		xxComnScrColValTxt_NR = (EZDSStringItem)newItem("xxComnScrColValTxt_NR");
		xxDsMultMsgDplyTxt_NR = (EZDSStringItem)newItem("xxDsMultMsgDplyTxt_NR");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2320_CSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2320_CSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"procGrpNum_NR", "procGrpNum_NR", "NR", null, TYPE_HANKAKUEISU, "7", null},
	{"custIssPoNum_NR", "custIssPoNum_NR", "NR", null, TYPE_HANKAKUEISU, "35", null},
	{"aquNum_NR", "aquNum_NR", "NR", null, TYPE_HANKAKUEISU, "35", null},
	{"negoDealAmt_NR", "negoDealAmt_NR", "NR", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsOrdCatgCd_NR", "dsOrdCatgCd_NR", "NR", null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdCatgDescTxt_NR", "dsOrdCatgDescTxt_NR", "NR", null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdTpCd_NR", "dsOrdTpCd_NR", "NR", null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdTpDescTxt_NR", "dsOrdTpDescTxt_NR", "NR", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseCd_NR", "mdseCd_NR", "NR", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseNm_NR", "mdseNm_NR", "NR", null, TYPE_HANKAKUEISU, "30", null},
	{"ordQty_NR", "ordQty_NR", "NR", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"entDealNetUnitPrcAmt_NR", "entDealNetUnitPrcAmt_NR", "NR", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsOrdPosnNum_NR", "dsOrdPosnNum_NR", "NR", null, TYPE_HANKAKUEISU, "6", null},
	{"dsOrdLineCatgCd_NR", "dsOrdLineCatgCd_NR", "NR", null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdLineCatgDescTxt_NR", "dsOrdLineCatgDescTxt_NR", "NR", null, TYPE_HANKAKUEISU, "50", null},
	{"billToCustLocCd_NR", "billToCustLocCd_NR", "NR", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustLocCd_NR", "shipToCustLocCd_NR", "NR", null, TYPE_HANKAKUEISU, "20", null},
	{"soldToCustLocCd_NR", "soldToCustLocCd_NR", "NR", null, TYPE_HANKAKUEISU, "20", null},
	{"serNum_NR", "serNum_NR", "NR", null, TYPE_HANKAKUEISU, "30", null},
	{"slsRepTocCd_NR", "slsRepTocCd_NR", "NR", null, TYPE_HANKAKUEISU, "8", null},
	{"tocNm_NR", "tocNm_NR", "NR", null, TYPE_HANKAKUEISU, "50", null},
	{"slsRepRoleTpCd_NR", "slsRepRoleTpCd_NR", "NR", null, TYPE_HANKAKUEISU, "10", null},
	{"coaExtnCd_NR", "coaExtnCd_NR", "NR", null, TYPE_HANKAKUEISU, "3", null},
	{"rtlWhCd_NR", "rtlWhCd_NR", "NR", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_NR", "rtlWhNm_NR", "NR", null, TYPE_HANKAKUEISU, "30", null},
	{"rtrnRsnCd_NR", "rtrnRsnCd_NR", "NR", null, TYPE_HANKAKUEISU, "2", null},
	{"rtrnRsnDescTxt_NR", "rtrnRsnDescTxt_NR", "NR", null, TYPE_HANKAKUEISU, "50", null},
	{"xxShpgOrdCmntTxt_NR", "xxShpgOrdCmntTxt_NR", "NR", null, TYPE_HANKAKUEISU, "240", null},
	{"ordUpldVldStsDescTxt_NR", "ordUpldVldStsDescTxt_NR", "NR", null, TYPE_HANKAKUEISU, "50", null},
	{"xxComnScrColValTxt_NR", "xxComnScrColValTxt_NR", "NR", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxDsMultMsgDplyTxt_NR", "xxDsMultMsgDplyTxt_NR", "NR", null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PROC_GRP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procGrpNum_NR
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_NR
        {"AQU_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aquNum_NR
        {"NEGO_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//negoDealAmt_NR
        {"DS_ORD_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd_NR
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt_NR
        {"DS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd_NR
        {"DS_ORD_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpDescTxt_NR
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_NR
        {"MDSE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseNm_NR
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_NR
        {"ENT_DEAL_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entDealNetUnitPrcAmt_NR
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_NR
        {"DS_ORD_LINE_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgCd_NR
        {"DS_ORD_LINE_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgDescTxt_NR
        {"BILL_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustLocCd_NR
        {"SHIP_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustLocCd_NR
        {"SOLD_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustLocCd_NR
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_NR
        {"SLS_REP_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd_NR
        {"TOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocNm_NR
        {"SLS_REP_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepRoleTpCd_NR
        {"COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_NR
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_NR
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_NR
        {"RTRN_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtrnRsnCd_NR
        {"RTRN_RSN_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtrnRsnDescTxt_NR
        {"XX_SHPG_ORD_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShpgOrdCmntTxt_NR
        {"ORD_UPLD_VLD_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordUpldVldStsDescTxt_NR
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_NR
        {"XX_DS_MULT_MSG_DPLY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMultMsgDplyTxt_NR
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
