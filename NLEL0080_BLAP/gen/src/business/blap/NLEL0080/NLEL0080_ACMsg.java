//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180625164113000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLEL0080_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLEL0080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLEL0080 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLEL0080_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX*/
	public final EZDCStringItem              xxChkBox;

    /** DS_ASSET_MSTR_PK*/
	public final EZDCBigDecimalItem              dsAssetMstrPk;

    /** DS_ASSET_DESC_TXT*/
	public final EZDCStringItem              dsAssetDescTxt;

    /** TOT_ASSET_QTY*/
	public final EZDCBigDecimalItem              totAssetQty;

    /** DEPC_MTH_NUM*/
	public final EZDCStringItem              depcMthNum;

    /** SER_NUM*/
	public final EZDCStringItem              serNum;

    /** CUR_VAL_AMT*/
	public final EZDCBigDecimalItem              curValAmt;

    /** PRNT_DS_ASSET_MSTR_PK*/
	public final EZDCBigDecimalItem              prntDsAssetMstrPk;

    /** XX_SCR_ITEM_81_TXT*/
	public final EZDCStringItem              xxScrItem81Txt;

    /** SLS_REP_TOC_CD*/
	public final EZDCStringItem              slsRepTocCd;

    /** CPO_ORD_NUM*/
	public final EZDCStringItem              cpoOrdNum;

    /** CPO_DTL_LINE_NUM*/
	public final EZDCStringItem              cpoDtlLineNum;

    /** CPO_DTL_LINE_SUB_NUM*/
	public final EZDCStringItem              cpoDtlLineSubNum;

    /** CPO_DPLY_LINE_NUM*/
	public final EZDCStringItem              cpoDplyLineNum;

    /** BLLG_INV_NUM*/
	public final EZDCStringItem              bllgInvNum;

    /** DS_CONTR_NUM*/
	public final EZDCStringItem              dsContrNum;

    /** CONTR_EFF_FROM_DT*/
	public final EZDCDateItem              contrEffFromDt;

    /** CONTR_EFF_THRU_DT*/
	public final EZDCDateItem              contrEffThruDt;

    /** LAST_BILL_DT*/
	public final EZDCDateItem              lastBillDt;

    /** CUST_ISS_PO_NUM*/
	public final EZDCStringItem              custIssPoNum;

    /** INV_NUM*/
	public final EZDCStringItem              invNum;

    /** INV_DT*/
	public final EZDCDateItem              invDt;

    /** SELL_TO_CUST_CD*/
	public final EZDCStringItem              sellToCustCd;

    /** VND_TP_DESC_TXT*/
	public final EZDCStringItem              vndTpDescTxt;

    /** FIRST_LINE_ADDR*/
	public final EZDCStringItem              firstLineAddr;

    /** CTY_ADDR*/
	public final EZDCStringItem              ctyAddr;

    /** ST_CD*/
	public final EZDCStringItem              stCd;

    /** POST_CD*/
	public final EZDCStringItem              postCd;


	/**
	 * NLEL0080_ACMsg is constructor.
	 * The initialization when the instance of NLEL0080_ACMsg is generated.
	 */
	public NLEL0080_ACMsg() {
		this(false, -1);
	}

	/**
	 * NLEL0080_ACMsg is constructor.
	 * The initialization when the instance of NLEL0080_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLEL0080_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox = (EZDCStringItem)newItem("xxChkBox");
		dsAssetMstrPk = (EZDCBigDecimalItem)newItem("dsAssetMstrPk");
		dsAssetDescTxt = (EZDCStringItem)newItem("dsAssetDescTxt");
		totAssetQty = (EZDCBigDecimalItem)newItem("totAssetQty");
		depcMthNum = (EZDCStringItem)newItem("depcMthNum");
		serNum = (EZDCStringItem)newItem("serNum");
		curValAmt = (EZDCBigDecimalItem)newItem("curValAmt");
		prntDsAssetMstrPk = (EZDCBigDecimalItem)newItem("prntDsAssetMstrPk");
		xxScrItem81Txt = (EZDCStringItem)newItem("xxScrItem81Txt");
		slsRepTocCd = (EZDCStringItem)newItem("slsRepTocCd");
		cpoOrdNum = (EZDCStringItem)newItem("cpoOrdNum");
		cpoDtlLineNum = (EZDCStringItem)newItem("cpoDtlLineNum");
		cpoDtlLineSubNum = (EZDCStringItem)newItem("cpoDtlLineSubNum");
		cpoDplyLineNum = (EZDCStringItem)newItem("cpoDplyLineNum");
		bllgInvNum = (EZDCStringItem)newItem("bllgInvNum");
		dsContrNum = (EZDCStringItem)newItem("dsContrNum");
		contrEffFromDt = (EZDCDateItem)newItem("contrEffFromDt");
		contrEffThruDt = (EZDCDateItem)newItem("contrEffThruDt");
		lastBillDt = (EZDCDateItem)newItem("lastBillDt");
		custIssPoNum = (EZDCStringItem)newItem("custIssPoNum");
		invNum = (EZDCStringItem)newItem("invNum");
		invDt = (EZDCDateItem)newItem("invDt");
		sellToCustCd = (EZDCStringItem)newItem("sellToCustCd");
		vndTpDescTxt = (EZDCStringItem)newItem("vndTpDescTxt");
		firstLineAddr = (EZDCStringItem)newItem("firstLineAddr");
		ctyAddr = (EZDCStringItem)newItem("ctyAddr");
		stCd = (EZDCStringItem)newItem("stCd");
		postCd = (EZDCStringItem)newItem("postCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NLEL0080_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLEL0080_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox", "xxChkBox", null, null, TYPE_HANKAKUEIJI, "1", null},
	{"dsAssetMstrPk", "dsAssetMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsAssetDescTxt", "dsAssetDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"totAssetQty", "totAssetQty", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"depcMthNum", "depcMthNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"curValAmt", "curValAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prntDsAssetMstrPk", "prntDsAssetMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxScrItem81Txt", "xxScrItem81Txt", null, null, TYPE_HANKAKUEISU, "81", null},
	{"slsRepTocCd", "slsRepTocCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"cpoDtlLineNum", "cpoDtlLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum", "cpoDtlLineSubNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDplyLineNum", "cpoDplyLineNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"bllgInvNum", "bllgInvNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"contrEffFromDt", "contrEffFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"contrEffThruDt", "contrEffThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"lastBillDt", "lastBillDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"custIssPoNum", "custIssPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"invNum", "invNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"invDt", "invDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"sellToCustCd", "sellToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"vndTpDescTxt", "vndTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"firstLineAddr", "firstLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"ctyAddr", "ctyAddr", null, null, TYPE_HANKAKUEISU, "25", null},
	{"stCd", "stCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"postCd", "postCd", null, null, TYPE_HANKAKUEISU, "15", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"DS_ASSET_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAssetMstrPk
        {"DS_ASSET_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAssetDescTxt
        {"TOT_ASSET_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totAssetQty
        {"DEPC_MTH_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//depcMthNum
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"CUR_VAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//curValAmt
        {"PRNT_DS_ASSET_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntDsAssetMstrPk
        {"XX_SCR_ITEM_81_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem81Txt
        {"SLS_REP_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"CPO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum
        {"CPO_DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDplyLineNum
        {"BLLG_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgInvNum
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"CONTR_EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrEffFromDt
        {"CONTR_EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrEffThruDt
        {"LAST_BILL_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lastBillDt
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd
        {"VND_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndTpDescTxt
        {"FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd
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

