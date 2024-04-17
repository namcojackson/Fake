//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170531165913000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2320_BCMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NWAL2320_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CPO_ORD_NUM_EO*/
	public final EZDCStringItem              cpoOrdNum_EO;

    /** MDSE_CD_EO*/
	public final EZDCStringItem              mdseCd_EO;

    /** MDSE_NM_EO*/
	public final EZDCStringItem              mdseNm_EO;

    /** ORD_QTY_EO*/
	public final EZDCBigDecimalItem              ordQty_EO;

    /** ENT_DEAL_NET_UNIT_PRC_AMT_EO*/
	public final EZDCBigDecimalItem              entDealNetUnitPrcAmt_EO;

    /** DS_ORD_POSN_NUM_EO*/
	public final EZDCStringItem              dsOrdPosnNum_EO;

    /** DS_ORD_LINE_CATG_CD_EO*/
	public final EZDCStringItem              dsOrdLineCatgCd_EO;

    /** DS_ORD_LINE_CATG_DESC_TXT_EO*/
	public final EZDCStringItem              dsOrdLineCatgDescTxt_EO;

    /** BILL_TO_CUST_LOC_CD_EO*/
	public final EZDCStringItem              billToCustLocCd_EO;

    /** SHIP_TO_CUST_LOC_CD_EO*/
	public final EZDCStringItem              shipToCustLocCd_EO;

    /** SLS_REP_TOC_CD_EO*/
	public final EZDCStringItem              slsRepTocCd_EO;

    /** TOC_NM_EO*/
	public final EZDCStringItem              tocNm_EO;

    /** SLS_REP_ROLE_TP_CD_EO*/
	public final EZDCStringItem              slsRepRoleTpCd_EO;

    /** COA_EXTN_CD_EO*/
	public final EZDCStringItem              coaExtnCd_EO;

    /** RTL_WH_CD_EO*/
	public final EZDCStringItem              rtlWhCd_EO;

    /** RTL_WH_NM_EO*/
	public final EZDCStringItem              rtlWhNm_EO;

    /** RTL_SWH_CD_EO*/
	public final EZDCStringItem              rtlSwhCd_EO;

    /** RTL_SWH_NM_EO*/
	public final EZDCStringItem              rtlSwhNm_EO;

    /** XX_SHPG_ORD_CMNT_TXT_EO*/
	public final EZDCStringItem              xxShpgOrdCmntTxt_EO;

    /** ORD_UPLD_VLD_STS_DESC_TXT_EO*/
	public final EZDCStringItem              ordUpldVldStsDescTxt_EO;

    /** XX_COMN_SCR_COL_VAL_TXT_EO*/
	public final EZDCStringItem              xxComnScrColValTxt_EO;

    /** XX_DS_MULT_MSG_DPLY_TXT_EO*/
	public final EZDCStringItem              xxDsMultMsgDplyTxt_EO;


	/**
	 * NWAL2320_BCMsg is constructor.
	 * The initialization when the instance of NWAL2320_BCMsg is generated.
	 */
	public NWAL2320_BCMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2320_BCMsg is constructor.
	 * The initialization when the instance of NWAL2320_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2320_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cpoOrdNum_EO = (EZDCStringItem)newItem("cpoOrdNum_EO");
		mdseCd_EO = (EZDCStringItem)newItem("mdseCd_EO");
		mdseNm_EO = (EZDCStringItem)newItem("mdseNm_EO");
		ordQty_EO = (EZDCBigDecimalItem)newItem("ordQty_EO");
		entDealNetUnitPrcAmt_EO = (EZDCBigDecimalItem)newItem("entDealNetUnitPrcAmt_EO");
		dsOrdPosnNum_EO = (EZDCStringItem)newItem("dsOrdPosnNum_EO");
		dsOrdLineCatgCd_EO = (EZDCStringItem)newItem("dsOrdLineCatgCd_EO");
		dsOrdLineCatgDescTxt_EO = (EZDCStringItem)newItem("dsOrdLineCatgDescTxt_EO");
		billToCustLocCd_EO = (EZDCStringItem)newItem("billToCustLocCd_EO");
		shipToCustLocCd_EO = (EZDCStringItem)newItem("shipToCustLocCd_EO");
		slsRepTocCd_EO = (EZDCStringItem)newItem("slsRepTocCd_EO");
		tocNm_EO = (EZDCStringItem)newItem("tocNm_EO");
		slsRepRoleTpCd_EO = (EZDCStringItem)newItem("slsRepRoleTpCd_EO");
		coaExtnCd_EO = (EZDCStringItem)newItem("coaExtnCd_EO");
		rtlWhCd_EO = (EZDCStringItem)newItem("rtlWhCd_EO");
		rtlWhNm_EO = (EZDCStringItem)newItem("rtlWhNm_EO");
		rtlSwhCd_EO = (EZDCStringItem)newItem("rtlSwhCd_EO");
		rtlSwhNm_EO = (EZDCStringItem)newItem("rtlSwhNm_EO");
		xxShpgOrdCmntTxt_EO = (EZDCStringItem)newItem("xxShpgOrdCmntTxt_EO");
		ordUpldVldStsDescTxt_EO = (EZDCStringItem)newItem("ordUpldVldStsDescTxt_EO");
		xxComnScrColValTxt_EO = (EZDCStringItem)newItem("xxComnScrColValTxt_EO");
		xxDsMultMsgDplyTxt_EO = (EZDCStringItem)newItem("xxDsMultMsgDplyTxt_EO");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2320_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2320_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cpoOrdNum_EO", "cpoOrdNum_EO", "EO", null, TYPE_HANKAKUEISU, "8", null},
	{"mdseCd_EO", "mdseCd_EO", "EO", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseNm_EO", "mdseNm_EO", "EO", null, TYPE_HANKAKUEISU, "30", null},
	{"ordQty_EO", "ordQty_EO", "EO", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"entDealNetUnitPrcAmt_EO", "entDealNetUnitPrcAmt_EO", "EO", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsOrdPosnNum_EO", "dsOrdPosnNum_EO", "EO", null, TYPE_HANKAKUEISU, "6", null},
	{"dsOrdLineCatgCd_EO", "dsOrdLineCatgCd_EO", "EO", null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdLineCatgDescTxt_EO", "dsOrdLineCatgDescTxt_EO", "EO", null, TYPE_HANKAKUEISU, "50", null},
	{"billToCustLocCd_EO", "billToCustLocCd_EO", "EO", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustLocCd_EO", "shipToCustLocCd_EO", "EO", null, TYPE_HANKAKUEISU, "20", null},
	{"slsRepTocCd_EO", "slsRepTocCd_EO", "EO", null, TYPE_HANKAKUEISU, "8", null},
	{"tocNm_EO", "tocNm_EO", "EO", null, TYPE_HANKAKUEISU, "50", null},
	{"slsRepRoleTpCd_EO", "slsRepRoleTpCd_EO", "EO", null, TYPE_HANKAKUEISU, "10", null},
	{"coaExtnCd_EO", "coaExtnCd_EO", "EO", null, TYPE_HANKAKUEISU, "3", null},
	{"rtlWhCd_EO", "rtlWhCd_EO", "EO", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_EO", "rtlWhNm_EO", "EO", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_EO", "rtlSwhCd_EO", "EO", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_EO", "rtlSwhNm_EO", "EO", null, TYPE_HANKAKUEISU, "30", null},
	{"xxShpgOrdCmntTxt_EO", "xxShpgOrdCmntTxt_EO", "EO", null, TYPE_HANKAKUEISU, "240", null},
	{"ordUpldVldStsDescTxt_EO", "ordUpldVldStsDescTxt_EO", "EO", null, TYPE_HANKAKUEISU, "50", null},
	{"xxComnScrColValTxt_EO", "xxComnScrColValTxt_EO", "EO", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxDsMultMsgDplyTxt_EO", "xxDsMultMsgDplyTxt_EO", "EO", null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_EO
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_EO
        {"MDSE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseNm_EO
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_EO
        {"ENT_DEAL_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entDealNetUnitPrcAmt_EO
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_EO
        {"DS_ORD_LINE_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgCd_EO
        {"DS_ORD_LINE_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgDescTxt_EO
        {"BILL_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustLocCd_EO
        {"SHIP_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustLocCd_EO
        {"SLS_REP_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd_EO
        {"TOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocNm_EO
        {"SLS_REP_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepRoleTpCd_EO
        {"COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_EO
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_EO
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_EO
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_EO
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_EO
        {"XX_SHPG_ORD_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShpgOrdCmntTxt_EO
        {"ORD_UPLD_VLD_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordUpldVldStsDescTxt_EO
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_EO
        {"XX_DS_MULT_MSG_DPLY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMultMsgDplyTxt_EO
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

