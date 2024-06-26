//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170531165830000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2320_DBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2320;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2320 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2320_DBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CPO_ORD_NUM_ER*/
	public final EZDBStringItem              cpoOrdNum_ER;

    /** MDSE_CD_ER*/
	public final EZDBStringItem              mdseCd_ER;

    /** MDSE_NM_ER*/
	public final EZDBStringItem              mdseNm_ER;

    /** ORD_QTY_ER*/
	public final EZDBBigDecimalItem              ordQty_ER;

    /** ENT_DEAL_NET_UNIT_PRC_AMT_ER*/
	public final EZDBBigDecimalItem              entDealNetUnitPrcAmt_ER;

    /** DS_ORD_POSN_NUM_ER*/
	public final EZDBStringItem              dsOrdPosnNum_ER;

    /** DS_ORD_LINE_CATG_CD_ER*/
	public final EZDBStringItem              dsOrdLineCatgCd_ER;

    /** DS_ORD_LINE_CATG_DESC_TXT_ER*/
	public final EZDBStringItem              dsOrdLineCatgDescTxt_ER;

    /** BILL_TO_CUST_LOC_CD_ER*/
	public final EZDBStringItem              billToCustLocCd_ER;

    /** SHIP_TO_CUST_LOC_CD_ER*/
	public final EZDBStringItem              shipToCustLocCd_ER;

    /** SER_NUM_ER*/
	public final EZDBStringItem              serNum_ER;

    /** SLS_REP_TOC_CD_ER*/
	public final EZDBStringItem              slsRepTocCd_ER;

    /** TOC_NM_ER*/
	public final EZDBStringItem              tocNm_ER;

    /** SLS_REP_ROLE_TP_CD_ER*/
	public final EZDBStringItem              slsRepRoleTpCd_ER;

    /** COA_EXTN_CD_ER*/
	public final EZDBStringItem              coaExtnCd_ER;

    /** RTL_WH_CD_ER*/
	public final EZDBStringItem              rtlWhCd_ER;

    /** RTL_WH_NM_ER*/
	public final EZDBStringItem              rtlWhNm_ER;

    /** RTRN_RSN_CD_ER*/
	public final EZDBStringItem              rtrnRsnCd_ER;

    /** RTRN_RSN_DESC_TXT_ER*/
	public final EZDBStringItem              rtrnRsnDescTxt_ER;

    /** XX_SHPG_ORD_CMNT_TXT_ER*/
	public final EZDBStringItem              xxShpgOrdCmntTxt_ER;

    /** ORD_UPLD_VLD_STS_DESC_TXT_ER*/
	public final EZDBStringItem              ordUpldVldStsDescTxt_ER;

    /** XX_COMN_SCR_COL_VAL_TXT_ER*/
	public final EZDBStringItem              xxComnScrColValTxt_ER;

    /** XX_DS_MULT_MSG_DPLY_TXT_ER*/
	public final EZDBStringItem              xxDsMultMsgDplyTxt_ER;


	/**
	 * NWAL2320_DBMsg is constructor.
	 * The initialization when the instance of NWAL2320_DBMsg is generated.
	 */
	public NWAL2320_DBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2320_DBMsg is constructor.
	 * The initialization when the instance of NWAL2320_DBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2320_DBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cpoOrdNum_ER = (EZDBStringItem)newItem("cpoOrdNum_ER");
		mdseCd_ER = (EZDBStringItem)newItem("mdseCd_ER");
		mdseNm_ER = (EZDBStringItem)newItem("mdseNm_ER");
		ordQty_ER = (EZDBBigDecimalItem)newItem("ordQty_ER");
		entDealNetUnitPrcAmt_ER = (EZDBBigDecimalItem)newItem("entDealNetUnitPrcAmt_ER");
		dsOrdPosnNum_ER = (EZDBStringItem)newItem("dsOrdPosnNum_ER");
		dsOrdLineCatgCd_ER = (EZDBStringItem)newItem("dsOrdLineCatgCd_ER");
		dsOrdLineCatgDescTxt_ER = (EZDBStringItem)newItem("dsOrdLineCatgDescTxt_ER");
		billToCustLocCd_ER = (EZDBStringItem)newItem("billToCustLocCd_ER");
		shipToCustLocCd_ER = (EZDBStringItem)newItem("shipToCustLocCd_ER");
		serNum_ER = (EZDBStringItem)newItem("serNum_ER");
		slsRepTocCd_ER = (EZDBStringItem)newItem("slsRepTocCd_ER");
		tocNm_ER = (EZDBStringItem)newItem("tocNm_ER");
		slsRepRoleTpCd_ER = (EZDBStringItem)newItem("slsRepRoleTpCd_ER");
		coaExtnCd_ER = (EZDBStringItem)newItem("coaExtnCd_ER");
		rtlWhCd_ER = (EZDBStringItem)newItem("rtlWhCd_ER");
		rtlWhNm_ER = (EZDBStringItem)newItem("rtlWhNm_ER");
		rtrnRsnCd_ER = (EZDBStringItem)newItem("rtrnRsnCd_ER");
		rtrnRsnDescTxt_ER = (EZDBStringItem)newItem("rtrnRsnDescTxt_ER");
		xxShpgOrdCmntTxt_ER = (EZDBStringItem)newItem("xxShpgOrdCmntTxt_ER");
		ordUpldVldStsDescTxt_ER = (EZDBStringItem)newItem("ordUpldVldStsDescTxt_ER");
		xxComnScrColValTxt_ER = (EZDBStringItem)newItem("xxComnScrColValTxt_ER");
		xxDsMultMsgDplyTxt_ER = (EZDBStringItem)newItem("xxDsMultMsgDplyTxt_ER");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2320_DBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2320_DBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cpoOrdNum_ER", "cpoOrdNum_ER", "ER", null, TYPE_HANKAKUEISU, "8", null},
	{"mdseCd_ER", "mdseCd_ER", "ER", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseNm_ER", "mdseNm_ER", "ER", null, TYPE_HANKAKUEISU, "30", null},
	{"ordQty_ER", "ordQty_ER", "ER", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"entDealNetUnitPrcAmt_ER", "entDealNetUnitPrcAmt_ER", "ER", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsOrdPosnNum_ER", "dsOrdPosnNum_ER", "ER", null, TYPE_HANKAKUEISU, "6", null},
	{"dsOrdLineCatgCd_ER", "dsOrdLineCatgCd_ER", "ER", null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdLineCatgDescTxt_ER", "dsOrdLineCatgDescTxt_ER", "ER", null, TYPE_HANKAKUEISU, "50", null},
	{"billToCustLocCd_ER", "billToCustLocCd_ER", "ER", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustLocCd_ER", "shipToCustLocCd_ER", "ER", null, TYPE_HANKAKUEISU, "20", null},
	{"serNum_ER", "serNum_ER", "ER", null, TYPE_HANKAKUEISU, "30", null},
	{"slsRepTocCd_ER", "slsRepTocCd_ER", "ER", null, TYPE_HANKAKUEISU, "8", null},
	{"tocNm_ER", "tocNm_ER", "ER", null, TYPE_HANKAKUEISU, "50", null},
	{"slsRepRoleTpCd_ER", "slsRepRoleTpCd_ER", "ER", null, TYPE_HANKAKUEISU, "10", null},
	{"coaExtnCd_ER", "coaExtnCd_ER", "ER", null, TYPE_HANKAKUEISU, "3", null},
	{"rtlWhCd_ER", "rtlWhCd_ER", "ER", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_ER", "rtlWhNm_ER", "ER", null, TYPE_HANKAKUEISU, "30", null},
	{"rtrnRsnCd_ER", "rtrnRsnCd_ER", "ER", null, TYPE_HANKAKUEISU, "2", null},
	{"rtrnRsnDescTxt_ER", "rtrnRsnDescTxt_ER", "ER", null, TYPE_HANKAKUEISU, "50", null},
	{"xxShpgOrdCmntTxt_ER", "xxShpgOrdCmntTxt_ER", "ER", null, TYPE_HANKAKUEISU, "240", null},
	{"ordUpldVldStsDescTxt_ER", "ordUpldVldStsDescTxt_ER", "ER", null, TYPE_HANKAKUEISU, "50", null},
	{"xxComnScrColValTxt_ER", "xxComnScrColValTxt_ER", "ER", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxDsMultMsgDplyTxt_ER", "xxDsMultMsgDplyTxt_ER", "ER", null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CPO_ORD_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_ER
        {"MDSE_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_ER
        {"MDSE_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseNm_ER
        {"ORD_QTY",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_ER
        {"ENT_DEAL_NET_UNIT_PRC_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//entDealNetUnitPrcAmt_ER
        {"DS_ORD_POSN_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_ER
        {"DS_ORD_LINE_CATG_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgCd_ER
        {"DS_ORD_LINE_CATG_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgDescTxt_ER
        {"BILL_TO_CUST_LOC_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustLocCd_ER
        {"SHIP_TO_CUST_LOC_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustLocCd_ER
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_ER
        {"SLS_REP_TOC_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd_ER
        {"TOC_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocNm_ER
        {"SLS_REP_ROLE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepRoleTpCd_ER
        {"COA_EXTN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_ER
        {"RTL_WH_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_ER
        {"RTL_WH_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_ER
        {"RTRN_RSN_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtrnRsnCd_ER
        {"RTRN_RSN_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtrnRsnDescTxt_ER
        {"XX_SHPG_ORD_CMNT_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShpgOrdCmntTxt_ER
        {"ORD_UPLD_VLD_STS_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordUpldVldStsDescTxt_ER
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_ER
        {"XX_DS_MULT_MSG_DPLY_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMultMsgDplyTxt_ER
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

