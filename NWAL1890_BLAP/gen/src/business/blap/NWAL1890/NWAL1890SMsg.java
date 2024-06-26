//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170814094828000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1890SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1890;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1890 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1890SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDSStringItem              glblCmpyCd;

    /** XX_MODE_CD*/
	public final EZDSStringItem              xxModeCd;

    /** CPO_ORD_NUM*/
	public final EZDSStringItem              cpoOrdNum;

    /** DS_ORD_POSN_NUM*/
	public final EZDSStringItem              dsOrdPosnNum;

    /** XX_CONFIG_ID_SRCH_TXT_LK*/
	public final EZDSStringItem              xxConfigIdSrchTxt_LK;

    /** XX_CONFIG_ID_SRCH_TXT*/
	public final EZDSStringItem              xxConfigIdSrchTxt;

    /** XX_MDL_SRCH_TXT_LK*/
	public final EZDSStringItem              xxMdlSrchTxt_LK;

    /** XX_MDL_SRCH_TXT*/
	public final EZDSStringItem              xxMdlSrchTxt;

    /** XX_BILL_TO_ACCT_NM_SRCH_TXT_LK*/
	public final EZDSStringItem              xxBillToAcctNmSrchTxt_LK;

    /** XX_BILL_TO_ACCT_NM_SRCH_TXT*/
	public final EZDSStringItem              xxBillToAcctNmSrchTxt;

    /** XX_BILL_TO_ACCT_CD_SRCH_TXT_LK*/
	public final EZDSStringItem              xxBillToAcctCdSrchTxt_LK;

    /** XX_BILL_TO_ACCT_CD_SRCH_TXT*/
	public final EZDSStringItem              xxBillToAcctCdSrchTxt;

    /** XX_BILL_TO_LOC_CD_SRCH_TXT_LK*/
	public final EZDSStringItem              xxBillToLocCdSrchTxt_LK;

    /** XX_BILL_TO_LOC_CD_SRCH_TXT*/
	public final EZDSStringItem              xxBillToLocCdSrchTxt;

    /** XX_SHIP_TO_ACCT_NM_SRCH_TXT_LK*/
	public final EZDSStringItem              xxShipToAcctNmSrchTxt_LK;

    /** XX_SHIP_TO_ACCT_NM_SRCH_TXT*/
	public final EZDSStringItem              xxShipToAcctNmSrchTxt;

    /** XX_SHIP_TO_ACCT_CD_SRCH_TXT_LK*/
	public final EZDSStringItem              xxShipToAcctCdSrchTxt_LK;

    /** XX_SHIP_TO_ACCT_CD_SRCH_TXT*/
	public final EZDSStringItem              xxShipToAcctCdSrchTxt;

    /** XX_SHIP_TO_LOC_CD_SRCH_TXT_LK*/
	public final EZDSStringItem              xxShipToLocCdSrchTxt_LK;

    /** XX_SHIP_TO_LOC_CD_SRCH_TXT*/
	public final EZDSStringItem              xxShipToLocCdSrchTxt;

    /** XX_SOLD_TO_ACCT_NM_SRCH_TXT_LK*/
	public final EZDSStringItem              xxSoldToAcctNmSrchTxt_LK;

    /** XX_SOLD_TO_ACCT_NM_SRCH_TXT*/
	public final EZDSStringItem              xxSoldToAcctNmSrchTxt;

    /** XX_SOLD_TO_ACCT_CD_SRCH_TXT_LK*/
	public final EZDSStringItem              xxSoldToAcctCdSrchTxt_LK;

    /** XX_SOLD_TO_ACCT_CD_SRCH_TXT*/
	public final EZDSStringItem              xxSoldToAcctCdSrchTxt;

    /** XX_SOLD_TO_LOC_CD_SRCH_TXT_LK*/
	public final EZDSStringItem              xxSoldToLocCdSrchTxt_LK;

    /** XX_SOLD_TO_LOC_CD_SRCH_TXT*/
	public final EZDSStringItem              xxSoldToLocCdSrchTxt;

    /** XX_SHOW_INCL_LINE_FLG*/
	public final EZDSStringItem              xxShowInclLineFlg;

    /** XX_LINE_NUM*/
	public final EZDSStringItem              xxLineNum;

    /** XX_LINE_STS_SRCH_TXT_LK*/
	public final EZDSStringItem              xxLineStsSrchTxt_LK;

    /** XX_LINE_STS_SRCH_TXT*/
	public final EZDSStringItem              xxLineStsSrchTxt;

    /** XX_ORD_ITEM_SRCH_TXT_LK*/
	public final EZDSStringItem              xxOrdItemSrchTxt_LK;

    /** XX_ORD_ITEM_SRCH_TXT*/
	public final EZDSStringItem              xxOrdItemSrchTxt;

    /** XX_RTL_WH_SRCH_TXT_LK*/
	public final EZDSStringItem              xxRtlWhSrchTxt_LK;

    /** XX_RTL_WH_SRCH_TXT*/
	public final EZDSStringItem              xxRtlWhSrchTxt;

    /** XX_RTL_SWH_SRCH_TXT_LK*/
	public final EZDSStringItem              xxRtlSwhSrchTxt_LK;

    /** XX_RTL_SWH_SRCH_TXT*/
	public final EZDSStringItem              xxRtlSwhSrchTxt;

    /** XX_CPO_SRC_TP_SRCH_TXT_LK*/
	public final EZDSStringItem              xxCpoSrcTpSrchTxt_LK;

    /** XX_CPO_SRC_TP_SRCH_TXT*/
	public final EZDSStringItem              xxCpoSrcTpSrchTxt;

    /** XX_ORD_SRC_REF_NUM_SRCH_TXT_LK*/
	public final EZDSStringItem              xxOrdSrcRefNumSrchTxt_LK;

    /** XX_ORD_SRC_REF_NUM_SRCH_TXT*/
	public final EZDSStringItem              xxOrdSrcRefNumSrchTxt;

    /** XX_SBST_ITEM_SRCH_TXT_LK*/
	public final EZDSStringItem              xxSbstItemSrchTxt_LK;

    /** XX_SBST_ITEM_SRCH_TXT*/
	public final EZDSStringItem              xxSbstItemSrchTxt;

    /** XX_SER_NUM_SRCH_TXT_LK*/
	public final EZDSStringItem              xxSerNumSrchTxt_LK;

    /** XX_SER_NUM_SRCH_TXT*/
	public final EZDSStringItem              xxSerNumSrchTxt;

    /** XX_SHOW_INCL_CLO_LINE_FLG*/
	public final EZDSStringItem              xxShowInclCloLineFlg;

    /** XX_SHOW_INCL_CANC_LINE_FLG*/
	public final EZDSStringItem              xxShowInclCancLineFlg;

    /** XX_LINE_NUM_R*/
	public final EZDSStringItem              xxLineNum_R;

    /** XX_LINE_STS_SRCH_TXT_RL*/
	public final EZDSStringItem              xxLineStsSrchTxt_RL;

    /** XX_LINE_STS_SRCH_TXT_R*/
	public final EZDSStringItem              xxLineStsSrchTxt_R;

    /** XX_ORD_ITEM_SRCH_TXT_RL*/
	public final EZDSStringItem              xxOrdItemSrchTxt_RL;

    /** XX_ORD_ITEM_SRCH_TXT_R*/
	public final EZDSStringItem              xxOrdItemSrchTxt_R;

    /** XX_RTRN_RSN_SRCH_TXT_RL*/
	public final EZDSStringItem              xxRtrnRsnSrchTxt_RL;

    /** XX_RTRN_RSN_SRCH_TXT_R*/
	public final EZDSStringItem              xxRtrnRsnSrchTxt_R;

    /** XX_RTL_WH_SRCH_TXT_RL*/
	public final EZDSStringItem              xxRtlWhSrchTxt_RL;

    /** XX_RTL_WH_SRCH_TXT_R*/
	public final EZDSStringItem              xxRtlWhSrchTxt_R;

    /** XX_RTL_SWH_SRCH_TXT_RL*/
	public final EZDSStringItem              xxRtlSwhSrchTxt_RL;

    /** XX_RTL_SWH_SRCH_TXT_R*/
	public final EZDSStringItem              xxRtlSwhSrchTxt_R;

    /** XX_ORD_SRC_REF_NUM_SRCH_TXT_RL*/
	public final EZDSStringItem              xxOrdSrcRefNumSrchTxt_RL;

    /** XX_ORD_SRC_REF_NUM_SRCH_TXT_R*/
	public final EZDSStringItem              xxOrdSrcRefNumSrchTxt_R;

    /** XX_SER_NUM_SRCH_TXT_RL*/
	public final EZDSStringItem              xxSerNumSrchTxt_RL;

    /** XX_SER_NUM_SRCH_TXT_R*/
	public final EZDSStringItem              xxSerNumSrchTxt_R;

    /** XX_SHOW_INCL_CLO_LINE_FLG_R*/
	public final EZDSStringItem              xxShowInclCloLineFlg_R;

    /** XX_SHOW_INCL_CANC_LINE_FLG_R*/
	public final EZDSStringItem              xxShowInclCancLineFlg_R;

    /** Z*/
	public final business.blap.NWAL1890.NWAL1890_ZSMsgArray              Z;

    /** XX_SCR_EVENT_NM*/
	public final EZDSStringItem              xxScrEventNm;


	/**
	 * NWAL1890SMsg is constructor.
	 * The initialization when the instance of NWAL1890SMsg is generated.
	 */
	public NWAL1890SMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1890SMsg is constructor.
	 * The initialization when the instance of NWAL1890SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1890SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDSStringItem)newItem("glblCmpyCd");
		xxModeCd = (EZDSStringItem)newItem("xxModeCd");
		cpoOrdNum = (EZDSStringItem)newItem("cpoOrdNum");
		dsOrdPosnNum = (EZDSStringItem)newItem("dsOrdPosnNum");
		xxConfigIdSrchTxt_LK = (EZDSStringItem)newItem("xxConfigIdSrchTxt_LK");
		xxConfigIdSrchTxt = (EZDSStringItem)newItem("xxConfigIdSrchTxt");
		xxMdlSrchTxt_LK = (EZDSStringItem)newItem("xxMdlSrchTxt_LK");
		xxMdlSrchTxt = (EZDSStringItem)newItem("xxMdlSrchTxt");
		xxBillToAcctNmSrchTxt_LK = (EZDSStringItem)newItem("xxBillToAcctNmSrchTxt_LK");
		xxBillToAcctNmSrchTxt = (EZDSStringItem)newItem("xxBillToAcctNmSrchTxt");
		xxBillToAcctCdSrchTxt_LK = (EZDSStringItem)newItem("xxBillToAcctCdSrchTxt_LK");
		xxBillToAcctCdSrchTxt = (EZDSStringItem)newItem("xxBillToAcctCdSrchTxt");
		xxBillToLocCdSrchTxt_LK = (EZDSStringItem)newItem("xxBillToLocCdSrchTxt_LK");
		xxBillToLocCdSrchTxt = (EZDSStringItem)newItem("xxBillToLocCdSrchTxt");
		xxShipToAcctNmSrchTxt_LK = (EZDSStringItem)newItem("xxShipToAcctNmSrchTxt_LK");
		xxShipToAcctNmSrchTxt = (EZDSStringItem)newItem("xxShipToAcctNmSrchTxt");
		xxShipToAcctCdSrchTxt_LK = (EZDSStringItem)newItem("xxShipToAcctCdSrchTxt_LK");
		xxShipToAcctCdSrchTxt = (EZDSStringItem)newItem("xxShipToAcctCdSrchTxt");
		xxShipToLocCdSrchTxt_LK = (EZDSStringItem)newItem("xxShipToLocCdSrchTxt_LK");
		xxShipToLocCdSrchTxt = (EZDSStringItem)newItem("xxShipToLocCdSrchTxt");
		xxSoldToAcctNmSrchTxt_LK = (EZDSStringItem)newItem("xxSoldToAcctNmSrchTxt_LK");
		xxSoldToAcctNmSrchTxt = (EZDSStringItem)newItem("xxSoldToAcctNmSrchTxt");
		xxSoldToAcctCdSrchTxt_LK = (EZDSStringItem)newItem("xxSoldToAcctCdSrchTxt_LK");
		xxSoldToAcctCdSrchTxt = (EZDSStringItem)newItem("xxSoldToAcctCdSrchTxt");
		xxSoldToLocCdSrchTxt_LK = (EZDSStringItem)newItem("xxSoldToLocCdSrchTxt_LK");
		xxSoldToLocCdSrchTxt = (EZDSStringItem)newItem("xxSoldToLocCdSrchTxt");
		xxShowInclLineFlg = (EZDSStringItem)newItem("xxShowInclLineFlg");
		xxLineNum = (EZDSStringItem)newItem("xxLineNum");
		xxLineStsSrchTxt_LK = (EZDSStringItem)newItem("xxLineStsSrchTxt_LK");
		xxLineStsSrchTxt = (EZDSStringItem)newItem("xxLineStsSrchTxt");
		xxOrdItemSrchTxt_LK = (EZDSStringItem)newItem("xxOrdItemSrchTxt_LK");
		xxOrdItemSrchTxt = (EZDSStringItem)newItem("xxOrdItemSrchTxt");
		xxRtlWhSrchTxt_LK = (EZDSStringItem)newItem("xxRtlWhSrchTxt_LK");
		xxRtlWhSrchTxt = (EZDSStringItem)newItem("xxRtlWhSrchTxt");
		xxRtlSwhSrchTxt_LK = (EZDSStringItem)newItem("xxRtlSwhSrchTxt_LK");
		xxRtlSwhSrchTxt = (EZDSStringItem)newItem("xxRtlSwhSrchTxt");
		xxCpoSrcTpSrchTxt_LK = (EZDSStringItem)newItem("xxCpoSrcTpSrchTxt_LK");
		xxCpoSrcTpSrchTxt = (EZDSStringItem)newItem("xxCpoSrcTpSrchTxt");
		xxOrdSrcRefNumSrchTxt_LK = (EZDSStringItem)newItem("xxOrdSrcRefNumSrchTxt_LK");
		xxOrdSrcRefNumSrchTxt = (EZDSStringItem)newItem("xxOrdSrcRefNumSrchTxt");
		xxSbstItemSrchTxt_LK = (EZDSStringItem)newItem("xxSbstItemSrchTxt_LK");
		xxSbstItemSrchTxt = (EZDSStringItem)newItem("xxSbstItemSrchTxt");
		xxSerNumSrchTxt_LK = (EZDSStringItem)newItem("xxSerNumSrchTxt_LK");
		xxSerNumSrchTxt = (EZDSStringItem)newItem("xxSerNumSrchTxt");
		xxShowInclCloLineFlg = (EZDSStringItem)newItem("xxShowInclCloLineFlg");
		xxShowInclCancLineFlg = (EZDSStringItem)newItem("xxShowInclCancLineFlg");
		xxLineNum_R = (EZDSStringItem)newItem("xxLineNum_R");
		xxLineStsSrchTxt_RL = (EZDSStringItem)newItem("xxLineStsSrchTxt_RL");
		xxLineStsSrchTxt_R = (EZDSStringItem)newItem("xxLineStsSrchTxt_R");
		xxOrdItemSrchTxt_RL = (EZDSStringItem)newItem("xxOrdItemSrchTxt_RL");
		xxOrdItemSrchTxt_R = (EZDSStringItem)newItem("xxOrdItemSrchTxt_R");
		xxRtrnRsnSrchTxt_RL = (EZDSStringItem)newItem("xxRtrnRsnSrchTxt_RL");
		xxRtrnRsnSrchTxt_R = (EZDSStringItem)newItem("xxRtrnRsnSrchTxt_R");
		xxRtlWhSrchTxt_RL = (EZDSStringItem)newItem("xxRtlWhSrchTxt_RL");
		xxRtlWhSrchTxt_R = (EZDSStringItem)newItem("xxRtlWhSrchTxt_R");
		xxRtlSwhSrchTxt_RL = (EZDSStringItem)newItem("xxRtlSwhSrchTxt_RL");
		xxRtlSwhSrchTxt_R = (EZDSStringItem)newItem("xxRtlSwhSrchTxt_R");
		xxOrdSrcRefNumSrchTxt_RL = (EZDSStringItem)newItem("xxOrdSrcRefNumSrchTxt_RL");
		xxOrdSrcRefNumSrchTxt_R = (EZDSStringItem)newItem("xxOrdSrcRefNumSrchTxt_R");
		xxSerNumSrchTxt_RL = (EZDSStringItem)newItem("xxSerNumSrchTxt_RL");
		xxSerNumSrchTxt_R = (EZDSStringItem)newItem("xxSerNumSrchTxt_R");
		xxShowInclCloLineFlg_R = (EZDSStringItem)newItem("xxShowInclCloLineFlg_R");
		xxShowInclCancLineFlg_R = (EZDSStringItem)newItem("xxShowInclCancLineFlg_R");
		Z = (business.blap.NWAL1890.NWAL1890_ZSMsgArray)newMsgArray("Z");
		xxScrEventNm = (EZDSStringItem)newItem("xxScrEventNm");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1890SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1890SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"dsOrdPosnNum", "dsOrdPosnNum", null, null, TYPE_HANKAKUEISU, "6", null},
	{"xxConfigIdSrchTxt_LK", "xxConfigIdSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxConfigIdSrchTxt", "xxConfigIdSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxMdlSrchTxt_LK", "xxMdlSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxMdlSrchTxt", "xxMdlSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxBillToAcctNmSrchTxt_LK", "xxBillToAcctNmSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxBillToAcctNmSrchTxt", "xxBillToAcctNmSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxBillToAcctCdSrchTxt_LK", "xxBillToAcctCdSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxBillToAcctCdSrchTxt", "xxBillToAcctCdSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxBillToLocCdSrchTxt_LK", "xxBillToLocCdSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxBillToLocCdSrchTxt", "xxBillToLocCdSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxShipToAcctNmSrchTxt_LK", "xxShipToAcctNmSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxShipToAcctNmSrchTxt", "xxShipToAcctNmSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxShipToAcctCdSrchTxt_LK", "xxShipToAcctCdSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxShipToAcctCdSrchTxt", "xxShipToAcctCdSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxShipToLocCdSrchTxt_LK", "xxShipToLocCdSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxShipToLocCdSrchTxt", "xxShipToLocCdSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxSoldToAcctNmSrchTxt_LK", "xxSoldToAcctNmSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxSoldToAcctNmSrchTxt", "xxSoldToAcctNmSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxSoldToAcctCdSrchTxt_LK", "xxSoldToAcctCdSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxSoldToAcctCdSrchTxt", "xxSoldToAcctCdSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxSoldToLocCdSrchTxt_LK", "xxSoldToLocCdSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxSoldToLocCdSrchTxt", "xxSoldToLocCdSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxShowInclLineFlg", "xxShowInclLineFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxLineNum", "xxLineNum", null, null, TYPE_HANKAKUEISU, "11", null},
	{"xxLineStsSrchTxt_LK", "xxLineStsSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxLineStsSrchTxt", "xxLineStsSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxOrdItemSrchTxt_LK", "xxOrdItemSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxOrdItemSrchTxt", "xxOrdItemSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxRtlWhSrchTxt_LK", "xxRtlWhSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxRtlWhSrchTxt", "xxRtlWhSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxRtlSwhSrchTxt_LK", "xxRtlSwhSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxRtlSwhSrchTxt", "xxRtlSwhSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxCpoSrcTpSrchTxt_LK", "xxCpoSrcTpSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxCpoSrcTpSrchTxt", "xxCpoSrcTpSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxOrdSrcRefNumSrchTxt_LK", "xxOrdSrcRefNumSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxOrdSrcRefNumSrchTxt", "xxOrdSrcRefNumSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxSbstItemSrchTxt_LK", "xxSbstItemSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxSbstItemSrchTxt", "xxSbstItemSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxSerNumSrchTxt_LK", "xxSerNumSrchTxt_LK", "LK", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxSerNumSrchTxt", "xxSerNumSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxShowInclCloLineFlg", "xxShowInclCloLineFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxShowInclCancLineFlg", "xxShowInclCancLineFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxLineNum_R", "xxLineNum_R", "R", null, TYPE_HANKAKUEISU, "11", null},
	{"xxLineStsSrchTxt_RL", "xxLineStsSrchTxt_RL", "RL", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxLineStsSrchTxt_R", "xxLineStsSrchTxt_R", "R", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxOrdItemSrchTxt_RL", "xxOrdItemSrchTxt_RL", "RL", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxOrdItemSrchTxt_R", "xxOrdItemSrchTxt_R", "R", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxRtrnRsnSrchTxt_RL", "xxRtrnRsnSrchTxt_RL", "RL", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxRtrnRsnSrchTxt_R", "xxRtrnRsnSrchTxt_R", "R", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxRtlWhSrchTxt_RL", "xxRtlWhSrchTxt_RL", "RL", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxRtlWhSrchTxt_R", "xxRtlWhSrchTxt_R", "R", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxRtlSwhSrchTxt_RL", "xxRtlSwhSrchTxt_RL", "RL", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxRtlSwhSrchTxt_R", "xxRtlSwhSrchTxt_R", "R", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxOrdSrcRefNumSrchTxt_RL", "xxOrdSrcRefNumSrchTxt_RL", "RL", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxOrdSrcRefNumSrchTxt_R", "xxOrdSrcRefNumSrchTxt_R", "R", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxSerNumSrchTxt_RL", "xxSerNumSrchTxt_RL", "RL", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxSerNumSrchTxt_R", "xxSerNumSrchTxt_R", "R", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxShowInclCloLineFlg_R", "xxShowInclCloLineFlg_R", "R", null, TYPE_HANKAKUEISU, "1", null},
	{"xxShowInclCancLineFlg_R", "xxShowInclCancLineFlg_R", "R", null, TYPE_HANKAKUEISU, "1", null},
	{"Z", "Z", null, "200", "business.blap.NWAL1890.NWAL1890_ZSMsgArray", null, null},
	
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum
        {"XX_CONFIG_ID_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxConfigIdSrchTxt_LK
        {"XX_CONFIG_ID_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxConfigIdSrchTxt
        {"XX_MDL_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMdlSrchTxt_LK
        {"XX_MDL_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMdlSrchTxt
        {"XX_BILL_TO_ACCT_NM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBillToAcctNmSrchTxt_LK
        {"XX_BILL_TO_ACCT_NM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBillToAcctNmSrchTxt
        {"XX_BILL_TO_ACCT_CD_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBillToAcctCdSrchTxt_LK
        {"XX_BILL_TO_ACCT_CD_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBillToAcctCdSrchTxt
        {"XX_BILL_TO_LOC_CD_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBillToLocCdSrchTxt_LK
        {"XX_BILL_TO_LOC_CD_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBillToLocCdSrchTxt
        {"XX_SHIP_TO_ACCT_NM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShipToAcctNmSrchTxt_LK
        {"XX_SHIP_TO_ACCT_NM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShipToAcctNmSrchTxt
        {"XX_SHIP_TO_ACCT_CD_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShipToAcctCdSrchTxt_LK
        {"XX_SHIP_TO_ACCT_CD_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShipToAcctCdSrchTxt
        {"XX_SHIP_TO_LOC_CD_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShipToLocCdSrchTxt_LK
        {"XX_SHIP_TO_LOC_CD_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShipToLocCdSrchTxt
        {"XX_SOLD_TO_ACCT_NM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSoldToAcctNmSrchTxt_LK
        {"XX_SOLD_TO_ACCT_NM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSoldToAcctNmSrchTxt
        {"XX_SOLD_TO_ACCT_CD_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSoldToAcctCdSrchTxt_LK
        {"XX_SOLD_TO_ACCT_CD_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSoldToAcctCdSrchTxt
        {"XX_SOLD_TO_LOC_CD_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSoldToLocCdSrchTxt_LK
        {"XX_SOLD_TO_LOC_CD_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSoldToLocCdSrchTxt
        {"XX_SHOW_INCL_LINE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShowInclLineFlg
        {"XX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineNum
        {"XX_LINE_STS_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineStsSrchTxt_LK
        {"XX_LINE_STS_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineStsSrchTxt
        {"XX_ORD_ITEM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdItemSrchTxt_LK
        {"XX_ORD_ITEM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdItemSrchTxt
        {"XX_RTL_WH_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRtlWhSrchTxt_LK
        {"XX_RTL_WH_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRtlWhSrchTxt
        {"XX_RTL_SWH_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRtlSwhSrchTxt_LK
        {"XX_RTL_SWH_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRtlSwhSrchTxt
        {"XX_CPO_SRC_TP_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCpoSrcTpSrchTxt_LK
        {"XX_CPO_SRC_TP_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCpoSrcTpSrchTxt
        {"XX_ORD_SRC_REF_NUM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdSrcRefNumSrchTxt_LK
        {"XX_ORD_SRC_REF_NUM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdSrcRefNumSrchTxt
        {"XX_SBST_ITEM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSbstItemSrchTxt_LK
        {"XX_SBST_ITEM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSbstItemSrchTxt
        {"XX_SER_NUM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSerNumSrchTxt_LK
        {"XX_SER_NUM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSerNumSrchTxt
        {"XX_SHOW_INCL_CLO_LINE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShowInclCloLineFlg
        {"XX_SHOW_INCL_CANC_LINE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShowInclCancLineFlg
        {"XX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineNum_R
        {"XX_LINE_STS_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineStsSrchTxt_RL
        {"XX_LINE_STS_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineStsSrchTxt_R
        {"XX_ORD_ITEM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdItemSrchTxt_RL
        {"XX_ORD_ITEM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdItemSrchTxt_R
        {"XX_RTRN_RSN_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRtrnRsnSrchTxt_RL
        {"XX_RTRN_RSN_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRtrnRsnSrchTxt_R
        {"XX_RTL_WH_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRtlWhSrchTxt_RL
        {"XX_RTL_WH_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRtlWhSrchTxt_R
        {"XX_RTL_SWH_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRtlSwhSrchTxt_RL
        {"XX_RTL_SWH_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRtlSwhSrchTxt_R
        {"XX_ORD_SRC_REF_NUM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdSrcRefNumSrchTxt_RL
        {"XX_ORD_SRC_REF_NUM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdSrcRefNumSrchTxt_R
        {"XX_SER_NUM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSerNumSrchTxt_RL
        {"XX_SER_NUM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSerNumSrchTxt_R
        {"XX_SHOW_INCL_CLO_LINE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShowInclCloLineFlg_R
        {"XX_SHOW_INCL_CANC_LINE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShowInclCancLineFlg_R
		null,	//Z
        {"XX_SCR_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
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

