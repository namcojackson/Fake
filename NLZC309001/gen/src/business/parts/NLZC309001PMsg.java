//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20180410142237000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC309001PMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLZC309001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLZC309001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** DS_ASSET_STGNG_PK*/
	public final EZDPBigDecimalItem              dsAssetStgngPk;

    /** PROC_MODE_CD*/
	public final EZDPStringItem              procModeCd;

    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** FROM_SVC_CONFIG_MSTR_PK*/
	public final EZDPBigDecimalItem              fromSvcConfigMstrPk;

    /** TO_SVC_CONFIG_MSTR_PK*/
	public final EZDPBigDecimalItem              toSvcConfigMstrPk;

    /** SER_NUM*/
	public final EZDPStringItem              serNum;

    /** SVC_MACH_MSTR_PK*/
	public final EZDPBigDecimalItem              svcMachMstrPk;

    /** BASE_CMPT_FLG*/
	public final EZDPStringItem              baseCmptFlg;

    /** RTRN_WH_CD*/
	public final EZDPStringItem              rtrnWhCd;

    /** ASSET_TP_CD*/
	public final EZDPStringItem              assetTpCd;

    /** SHPG_PLN_NUM*/
	public final EZDPStringItem              shpgPlnNum;

    /** INVTY_TRX_PK*/
	public final EZDPBigDecimalItem              invtyTrxPk;

    /** CPO_ORD_NUM*/
	public final EZDPStringItem              cpoOrdNum;

    /** CPO_DTL_LINE_NUM*/
	public final EZDPStringItem              cpoDtlLineNum;

    /** CPO_DTL_LINE_SUB_NUM*/
	public final EZDPStringItem              cpoDtlLineSubNum;

    /** DS_ORD_POSN_NUM*/
	public final EZDPStringItem              dsOrdPosnNum;

    /** SHIP_TO_CUST_ACCT_CD*/
	public final EZDPStringItem              shipToCustAcctCd;

    /** SHIP_TO_CUST_CD*/
	public final EZDPStringItem              shipToCustCd;

    /** SELL_TO_CUST_CD*/
	public final EZDPStringItem              sellToCustCd;

    /** SOLD_TO_CUST_LOC_CD*/
	public final EZDPStringItem              soldToCustLocCd;

    /** SLS_REP_TOC_CD*/
	public final EZDPStringItem              slsRepTocCd;

    /** INV_NUM*/
	public final EZDPStringItem              invNum;

    /** INV_DT*/
	public final EZDPDateItem              invDt;

    /** CSMP_PRC_AMT*/
	public final EZDPBigDecimalItem              csmpPrcAmt;

    /** ORD_ADJ_AMT*/
	public final EZDPBigDecimalItem              ordAdjAmt;

    /** STD_COST_AMT*/
	public final EZDPBigDecimalItem              stdCostAmt;

    /** DS_ASSET_MSTR_PK*/
	public final EZDPBigDecimalItem              dsAssetMstrPk;

    /** AP_VND_INV_SQ_NUM*/
	public final EZDPStringItem              apVndInvSqNum;

    /** TOT_ASSET_QTY*/
	public final EZDPBigDecimalItem              totAssetQty;

    /** AP_VND_INV_LINE_NUM*/
	public final EZDPStringItem              apVndInvLineNum;

    /** xxMsgIdList*/
	public final business.parts.NLZC309001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NLZC309001PMsg is constructor.
	 * The initialization when the instance of NLZC309001PMsg is generated.
	 */
	public NLZC309001PMsg() {
		this(false, -1);
	}

	/**
	 * NLZC309001PMsg is constructor.
	 * The initialization when the instance of NLZC309001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLZC309001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		dsAssetStgngPk = (EZDPBigDecimalItem)newItem("dsAssetStgngPk");
		procModeCd = (EZDPStringItem)newItem("procModeCd");
		mdseCd = (EZDPStringItem)newItem("mdseCd");
		fromSvcConfigMstrPk = (EZDPBigDecimalItem)newItem("fromSvcConfigMstrPk");
		toSvcConfigMstrPk = (EZDPBigDecimalItem)newItem("toSvcConfigMstrPk");
		serNum = (EZDPStringItem)newItem("serNum");
		svcMachMstrPk = (EZDPBigDecimalItem)newItem("svcMachMstrPk");
		baseCmptFlg = (EZDPStringItem)newItem("baseCmptFlg");
		rtrnWhCd = (EZDPStringItem)newItem("rtrnWhCd");
		assetTpCd = (EZDPStringItem)newItem("assetTpCd");
		shpgPlnNum = (EZDPStringItem)newItem("shpgPlnNum");
		invtyTrxPk = (EZDPBigDecimalItem)newItem("invtyTrxPk");
		cpoOrdNum = (EZDPStringItem)newItem("cpoOrdNum");
		cpoDtlLineNum = (EZDPStringItem)newItem("cpoDtlLineNum");
		cpoDtlLineSubNum = (EZDPStringItem)newItem("cpoDtlLineSubNum");
		dsOrdPosnNum = (EZDPStringItem)newItem("dsOrdPosnNum");
		shipToCustAcctCd = (EZDPStringItem)newItem("shipToCustAcctCd");
		shipToCustCd = (EZDPStringItem)newItem("shipToCustCd");
		sellToCustCd = (EZDPStringItem)newItem("sellToCustCd");
		soldToCustLocCd = (EZDPStringItem)newItem("soldToCustLocCd");
		slsRepTocCd = (EZDPStringItem)newItem("slsRepTocCd");
		invNum = (EZDPStringItem)newItem("invNum");
		invDt = (EZDPDateItem)newItem("invDt");
		csmpPrcAmt = (EZDPBigDecimalItem)newItem("csmpPrcAmt");
		ordAdjAmt = (EZDPBigDecimalItem)newItem("ordAdjAmt");
		stdCostAmt = (EZDPBigDecimalItem)newItem("stdCostAmt");
		dsAssetMstrPk = (EZDPBigDecimalItem)newItem("dsAssetMstrPk");
		apVndInvSqNum = (EZDPStringItem)newItem("apVndInvSqNum");
		totAssetQty = (EZDPBigDecimalItem)newItem("totAssetQty");
		apVndInvLineNum = (EZDPStringItem)newItem("apVndInvLineNum");
		xxMsgIdList = (business.parts.NLZC309001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NLZC309001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLZC309001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsAssetStgngPk", "dsAssetStgngPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"procModeCd", "procModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"fromSvcConfigMstrPk", "fromSvcConfigMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"toSvcConfigMstrPk", "toSvcConfigMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"baseCmptFlg", "baseCmptFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"rtrnWhCd", "rtrnWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"assetTpCd", "assetTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"shpgPlnNum", "shpgPlnNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"invtyTrxPk", "invtyTrxPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"cpoDtlLineNum", "cpoDtlLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum", "cpoDtlLineSubNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"dsOrdPosnNum", "dsOrdPosnNum", null, null, TYPE_HANKAKUEISU, "6", null},
	{"shipToCustAcctCd", "shipToCustAcctCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd", "shipToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"sellToCustCd", "sellToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"soldToCustLocCd", "soldToCustLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"slsRepTocCd", "slsRepTocCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"invNum", "invNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"invDt", "invDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"csmpPrcAmt", "csmpPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"ordAdjAmt", "ordAdjAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"stdCostAmt", "stdCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsAssetMstrPk", "dsAssetMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"apVndInvSqNum", "apVndInvSqNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"totAssetQty", "totAssetQty", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"apVndInvLineNum", "apVndInvLineNum", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NLZC309001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"DS_ASSET_STGNG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAssetStgngPk
        {"PROC_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procModeCd
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"FROM_SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromSvcConfigMstrPk
        {"TO_SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//toSvcConfigMstrPk
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"BASE_CMPT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseCmptFlg
        {"RTRN_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtrnWhCd
        {"ASSET_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpCd
        {"SHPG_PLN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgPlnNum
        {"INVTY_TRX_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyTrxPk
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"CPO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum
        {"SHIP_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustAcctCd
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd
        {"SOLD_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustLocCd
        {"SLS_REP_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt
        {"CSMP_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpPrcAmt
        {"ORD_ADJ_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordAdjAmt
        {"STD_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stdCostAmt
        {"DS_ASSET_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAssetMstrPk
        {"AP_VND_INV_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndInvSqNum
        {"TOT_ASSET_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totAssetQty
        {"AP_VND_INV_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndInvLineNum
		null,	//xxMsgIdList
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
