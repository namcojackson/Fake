//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180625155248000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLEL0080_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLEL0080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLEL0080 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLEL0080_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX*/
	public final EZDBStringItem              xxChkBox;

    /** DS_ASSET_MSTR_PK*/
	public final EZDBBigDecimalItem              dsAssetMstrPk;

    /** DS_ASSET_DESC_TXT*/
	public final EZDBStringItem              dsAssetDescTxt;

    /** TOT_ASSET_QTY*/
	public final EZDBBigDecimalItem              totAssetQty;

    /** DEPC_MTH_NUM*/
	public final EZDBStringItem              depcMthNum;

    /** SER_NUM*/
	public final EZDBStringItem              serNum;

    /** CUR_VAL_AMT*/
	public final EZDBBigDecimalItem              curValAmt;

    /** PRNT_DS_ASSET_MSTR_PK*/
	public final EZDBBigDecimalItem              prntDsAssetMstrPk;

    /** XX_SCR_ITEM_81_TXT*/
	public final EZDBStringItem              xxScrItem81Txt;

    /** SLS_REP_TOC_CD*/
	public final EZDBStringItem              slsRepTocCd;

    /** CPO_ORD_NUM*/
	public final EZDBStringItem              cpoOrdNum;

    /** CPO_DPLY_LINE_NUM*/
	public final EZDBStringItem              cpoDplyLineNum;

    /** BLLG_INV_NUM*/
	public final EZDBStringItem              bllgInvNum;

    /** DS_CONTR_NUM*/
	public final EZDBStringItem              dsContrNum;

    /** CONTR_EFF_FROM_DT*/
	public final EZDBDateItem              contrEffFromDt;

    /** CONTR_EFF_THRU_DT*/
	public final EZDBDateItem              contrEffThruDt;

    /** LAST_BILL_DT*/
	public final EZDBDateItem              lastBillDt;

    /** CUST_ISS_PO_NUM*/
	public final EZDBStringItem              custIssPoNum;

    /** INV_NUM*/
	public final EZDBStringItem              invNum;

    /** INV_DT*/
	public final EZDBDateItem              invDt;

    /** SELL_TO_CUST_CD*/
	public final EZDBStringItem              sellToCustCd;

    /** VND_TP_DESC_TXT*/
	public final EZDBStringItem              vndTpDescTxt;

    /** FIRST_LINE_ADDR*/
	public final EZDBStringItem              firstLineAddr;

    /** CTY_ADDR*/
	public final EZDBStringItem              ctyAddr;

    /** ST_CD*/
	public final EZDBStringItem              stCd;

    /** POST_CD*/
	public final EZDBStringItem              postCd;


	/**
	 * NLEL0080_ABMsg is constructor.
	 * The initialization when the instance of NLEL0080_ABMsg is generated.
	 */
	public NLEL0080_ABMsg() {
		this(false, -1);
	}

	/**
	 * NLEL0080_ABMsg is constructor.
	 * The initialization when the instance of NLEL0080_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLEL0080_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox = (EZDBStringItem)newItem("xxChkBox");
		dsAssetMstrPk = (EZDBBigDecimalItem)newItem("dsAssetMstrPk");
		dsAssetDescTxt = (EZDBStringItem)newItem("dsAssetDescTxt");
		totAssetQty = (EZDBBigDecimalItem)newItem("totAssetQty");
		depcMthNum = (EZDBStringItem)newItem("depcMthNum");
		serNum = (EZDBStringItem)newItem("serNum");
		curValAmt = (EZDBBigDecimalItem)newItem("curValAmt");
		prntDsAssetMstrPk = (EZDBBigDecimalItem)newItem("prntDsAssetMstrPk");
		xxScrItem81Txt = (EZDBStringItem)newItem("xxScrItem81Txt");
		slsRepTocCd = (EZDBStringItem)newItem("slsRepTocCd");
		cpoOrdNum = (EZDBStringItem)newItem("cpoOrdNum");
		cpoDplyLineNum = (EZDBStringItem)newItem("cpoDplyLineNum");
		bllgInvNum = (EZDBStringItem)newItem("bllgInvNum");
		dsContrNum = (EZDBStringItem)newItem("dsContrNum");
		contrEffFromDt = (EZDBDateItem)newItem("contrEffFromDt");
		contrEffThruDt = (EZDBDateItem)newItem("contrEffThruDt");
		lastBillDt = (EZDBDateItem)newItem("lastBillDt");
		custIssPoNum = (EZDBStringItem)newItem("custIssPoNum");
		invNum = (EZDBStringItem)newItem("invNum");
		invDt = (EZDBDateItem)newItem("invDt");
		sellToCustCd = (EZDBStringItem)newItem("sellToCustCd");
		vndTpDescTxt = (EZDBStringItem)newItem("vndTpDescTxt");
		firstLineAddr = (EZDBStringItem)newItem("firstLineAddr");
		ctyAddr = (EZDBStringItem)newItem("ctyAddr");
		stCd = (EZDBStringItem)newItem("stCd");
		postCd = (EZDBStringItem)newItem("postCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NLEL0080_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLEL0080_ABMsgArray();
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

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"DS_ASSET_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAssetMstrPk
        {"DS_ASSET_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAssetDescTxt
        {"TOT_ASSET_QTY",  NO,  "0",null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totAssetQty
        {"DEPC_MTH_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//depcMthNum
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"CUR_VAL_AMT", YES,  "0",null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//curValAmt
        {"PRNT_DS_ASSET_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntDsAssetMstrPk
        {"XX_SCR_ITEM_81_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem81Txt
        {"SLS_REP_TOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"CPO_DPLY_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDplyLineNum
        {"BLLG_INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgInvNum
        {"DS_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"CONTR_EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//contrEffFromDt
        {"CONTR_EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//contrEffThruDt
        {"LAST_BILL_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//lastBillDt
        {"CUST_ISS_PO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum
        {"INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum
        {"INV_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//invDt
        {"SELL_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd
        {"VND_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndTpDescTxt
        {"FIRST_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr
        {"CTY_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr
        {"ST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd
        {"POST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd
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

