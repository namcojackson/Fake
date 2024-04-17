//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20160114181032000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC167001_APMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.EZDMsgArray;
import parts.common.EZDPBigDecimalItem;
import parts.common.EZDPMsg;
import parts.common.EZDPStringItem;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWZC167001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC167001_APMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** REQUEST_TYPE_IF_A*/
	public final EZDPStringItem              requestTypeIf_A;

    /** SPLY_QUOTE_DTL_LINE_NUM_A*/
	public final EZDPStringItem              splyQuoteDtlLineNum_A;

    /** SPLY_QUOTE_DTL_LINE_SUB_NUM_A*/
	public final EZDPStringItem              splyQuoteDtlLineSubNum_A;

    /** CPO_ORD_TP_CD_A*/
	public final EZDPStringItem              cpoOrdTpCd_A;

    /** MDSE_CD_A*/
	public final EZDPStringItem              mdseCd_A;

    /** ORD_QTY_A*/
	public final EZDPBigDecimalItem              ordQty_A;

    /** ORD_CUST_UOM_QTY_A*/
	public final EZDPBigDecimalItem              ordCustUomQty_A;

    /** INVTY_LOC_CD_A*/
	public final EZDPStringItem              invtyLocCd_A;

    /** DEAL_NET_UNIT_PRC_AMT_A*/
	public final EZDPBigDecimalItem              dealNetUnitPrcAmt_A;

    /** CCY_CD_A*/
	public final EZDPStringItem              ccyCd_A;

    /** STK_STS_CD_A*/
	public final EZDPStringItem              stkStsCd_A;

    /** CUST_MDSE_CD_A*/
	public final EZDPStringItem              custMdseCd_A;

    /** CUST_UOM_CD_A*/
	public final EZDPStringItem              custUomCd_A;

    /** MAN_PRC_FLG_A*/
	public final EZDPStringItem              manPrcFlg_A;

    /** SET_ITEM_SHIP_CPLT_FLG_A*/
	public final EZDPStringItem              setItemShipCpltFlg_A;

    /** UNIT_NET_WT_A*/
	public final EZDPBigDecimalItem              unitNetWt_A;

    /** MDSE_FRT_CLS_CD_A*/
	public final EZDPStringItem              mdseFrtClsCd_A;

    /** MDSE_PRC_GRP_CD_A*/
	public final EZDPStringItem              mdsePrcGrpCd_A;

    /** MDSE_PRC_GRP_GRS_WT_A*/
	public final EZDPBigDecimalItem              mdsePrcGrpGrsWt_A;

    /** DEAL_SPLY_QUOTE_DTL_SLS_AMT_A*/
	public final EZDPBigDecimalItem              dealSplyQuoteDtlSlsAmt_A;

    /** XX_TOT_DISC_AMT_A*/
	public final EZDPBigDecimalItem              xxTotDiscAmt_A;

    /** XX_TOT_SPCL_PRC_AMT_A*/
	public final EZDPBigDecimalItem              xxTotSpclPrcAmt_A;

    /** XX_TOT_NET_DISC_AMT_A*/
	public final EZDPBigDecimalItem              xxTotNetDiscAmt_A;

    /** DEAL_SPLY_QUOTE_DTL_NET_AMT_A*/
	public final EZDPBigDecimalItem              dealSplyQuoteDtlNetAmt_A;

    /** FUNC_QUOTE_DTL_NET_AMT_A*/
	public final EZDPBigDecimalItem              funcQuoteDtlNetAmt_A;

    /** XX_TOT_FRT_AMT_A*/
	public final EZDPBigDecimalItem              xxTotFrtAmt_A;

    /** XX_TOT_SPCL_CHRG_AMT_A*/
	public final EZDPBigDecimalItem              xxTotSpclChrgAmt_A;

    /** XX_TOT_TAX_AMT_A*/
	public final EZDPBigDecimalItem              xxTotTaxAmt_A;

    /** XX_TOT_NET_PRC_AMT_A*/
	public final EZDPBigDecimalItem              xxTotNetPrcAmt_A;

    /** XX_TOT_AMT_A*/
	public final EZDPBigDecimalItem              xxTotAmt_A;

    /** DS_ORD_LINE_CATG_CD_A*/
	public final EZDPStringItem              dsOrdLineCatgCd_A;

    /** ORD_LINE_SRC_CD_A*/
	public final EZDPStringItem              ordLineSrcCd_A;

    /** RTL_WH_CD_A*/
	public final EZDPStringItem              rtlWhCd_A;

    /** RTL_SWH_CD_A*/
	public final EZDPStringItem              rtlSwhCd_A;

    /** DEAL_PRC_LIST_PRC_AMT_A*/
	public final EZDPBigDecimalItem              dealPrcListPrcAmt_A;

    /** FUNC_PRC_LIST_PRC_AMT_A*/
	public final EZDPBigDecimalItem              funcPrcListPrcAmt_A;

    /** SUPD_LOCK_FLG_A*/
	public final EZDPStringItem              supdLockFlg_A;

    /** $$NWZC167002PMsg*/
	public final business.parts.NWZC167002PMsgArray              NWZC167002PMsg;


	/**
	 * NWZC167001_APMsg is constructor.
	 * The initialization when the instance of NWZC167001_APMsg is generated.
	 */
	public NWZC167001_APMsg() {
		this(false, -1);
	}

	/**
	 * NWZC167001_APMsg is constructor.
	 * The initialization when the instance of NWZC167001_APMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC167001_APMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		requestTypeIf_A = (EZDPStringItem)newItem("requestTypeIf_A");
		splyQuoteDtlLineNum_A = (EZDPStringItem)newItem("splyQuoteDtlLineNum_A");
		splyQuoteDtlLineSubNum_A = (EZDPStringItem)newItem("splyQuoteDtlLineSubNum_A");
		cpoOrdTpCd_A = (EZDPStringItem)newItem("cpoOrdTpCd_A");
		mdseCd_A = (EZDPStringItem)newItem("mdseCd_A");
		ordQty_A = (EZDPBigDecimalItem)newItem("ordQty_A");
		ordCustUomQty_A = (EZDPBigDecimalItem)newItem("ordCustUomQty_A");
		invtyLocCd_A = (EZDPStringItem)newItem("invtyLocCd_A");
		dealNetUnitPrcAmt_A = (EZDPBigDecimalItem)newItem("dealNetUnitPrcAmt_A");
		ccyCd_A = (EZDPStringItem)newItem("ccyCd_A");
		stkStsCd_A = (EZDPStringItem)newItem("stkStsCd_A");
		custMdseCd_A = (EZDPStringItem)newItem("custMdseCd_A");
		custUomCd_A = (EZDPStringItem)newItem("custUomCd_A");
		manPrcFlg_A = (EZDPStringItem)newItem("manPrcFlg_A");
		setItemShipCpltFlg_A = (EZDPStringItem)newItem("setItemShipCpltFlg_A");
		unitNetWt_A = (EZDPBigDecimalItem)newItem("unitNetWt_A");
		mdseFrtClsCd_A = (EZDPStringItem)newItem("mdseFrtClsCd_A");
		mdsePrcGrpCd_A = (EZDPStringItem)newItem("mdsePrcGrpCd_A");
		mdsePrcGrpGrsWt_A = (EZDPBigDecimalItem)newItem("mdsePrcGrpGrsWt_A");
		dealSplyQuoteDtlSlsAmt_A = (EZDPBigDecimalItem)newItem("dealSplyQuoteDtlSlsAmt_A");
		xxTotDiscAmt_A = (EZDPBigDecimalItem)newItem("xxTotDiscAmt_A");
		xxTotSpclPrcAmt_A = (EZDPBigDecimalItem)newItem("xxTotSpclPrcAmt_A");
		xxTotNetDiscAmt_A = (EZDPBigDecimalItem)newItem("xxTotNetDiscAmt_A");
		dealSplyQuoteDtlNetAmt_A = (EZDPBigDecimalItem)newItem("dealSplyQuoteDtlNetAmt_A");
		funcQuoteDtlNetAmt_A = (EZDPBigDecimalItem)newItem("funcQuoteDtlNetAmt_A");
		xxTotFrtAmt_A = (EZDPBigDecimalItem)newItem("xxTotFrtAmt_A");
		xxTotSpclChrgAmt_A = (EZDPBigDecimalItem)newItem("xxTotSpclChrgAmt_A");
		xxTotTaxAmt_A = (EZDPBigDecimalItem)newItem("xxTotTaxAmt_A");
		xxTotNetPrcAmt_A = (EZDPBigDecimalItem)newItem("xxTotNetPrcAmt_A");
		xxTotAmt_A = (EZDPBigDecimalItem)newItem("xxTotAmt_A");
		dsOrdLineCatgCd_A = (EZDPStringItem)newItem("dsOrdLineCatgCd_A");
		ordLineSrcCd_A = (EZDPStringItem)newItem("ordLineSrcCd_A");
		rtlWhCd_A = (EZDPStringItem)newItem("rtlWhCd_A");
		rtlSwhCd_A = (EZDPStringItem)newItem("rtlSwhCd_A");
		dealPrcListPrcAmt_A = (EZDPBigDecimalItem)newItem("dealPrcListPrcAmt_A");
		funcPrcListPrcAmt_A = (EZDPBigDecimalItem)newItem("funcPrcListPrcAmt_A");
		supdLockFlg_A = (EZDPStringItem)newItem("supdLockFlg_A");
		NWZC167002PMsg = (business.parts.NWZC167002PMsgArray)newMsgArray("NWZC167002PMsg");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC167001_APMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC167001_APMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"requestTypeIf_A", "requestTypeIf_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"splyQuoteDtlLineNum_A", "splyQuoteDtlLineNum_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"splyQuoteDtlLineSubNum_A", "splyQuoteDtlLineSubNum_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoOrdTpCd_A", "cpoOrdTpCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"ordQty_A", "ordQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"ordCustUomQty_A", "ordCustUomQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"invtyLocCd_A", "invtyLocCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dealNetUnitPrcAmt_A", "dealNetUnitPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"ccyCd_A", "ccyCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"stkStsCd_A", "stkStsCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"custMdseCd_A", "custMdseCd_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"custUomCd_A", "custUomCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"manPrcFlg_A", "manPrcFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"setItemShipCpltFlg_A", "setItemShipCpltFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"unitNetWt_A", "unitNetWt_A", "A", null, TYPE_SEISU_SYOSU, "17", "6"},
	{"mdseFrtClsCd_A", "mdseFrtClsCd_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"mdsePrcGrpCd_A", "mdsePrcGrpCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"mdsePrcGrpGrsWt_A", "mdsePrcGrpGrsWt_A", "A", null, TYPE_SEISU_SYOSU, "17", "6"},
	{"dealSplyQuoteDtlSlsAmt_A", "dealSplyQuoteDtlSlsAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotDiscAmt_A", "xxTotDiscAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotSpclPrcAmt_A", "xxTotSpclPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotNetDiscAmt_A", "xxTotNetDiscAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealSplyQuoteDtlNetAmt_A", "dealSplyQuoteDtlNetAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcQuoteDtlNetAmt_A", "funcQuoteDtlNetAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotFrtAmt_A", "xxTotFrtAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotSpclChrgAmt_A", "xxTotSpclChrgAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotTaxAmt_A", "xxTotTaxAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotNetPrcAmt_A", "xxTotNetPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotAmt_A", "xxTotAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsOrdLineCatgCd_A", "dsOrdLineCatgCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"ordLineSrcCd_A", "ordLineSrcCd_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"rtlWhCd_A", "rtlWhCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhCd_A", "rtlSwhCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dealPrcListPrcAmt_A", "dealPrcListPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcPrcListPrcAmt_A", "funcPrcListPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"supdLockFlg_A", "supdLockFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"NWZC167002PMsg", "NWZC167002PMsg", null, "999", "business.parts.NWZC167002PMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"REQUEST_TYPE_IF",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//requestTypeIf_A
        {"SPLY_QUOTE_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyQuoteDtlLineNum_A
        {"SPLY_QUOTE_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyQuoteDtlLineSubNum_A
        {"CPO_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdTpCd_A
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_A
        {"ORD_CUST_UOM_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordCustUomQty_A
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_A
        {"DEAL_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealNetUnitPrcAmt_A
        {"CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ccyCd_A
        {"STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd_A
        {"CUST_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custMdseCd_A
        {"CUST_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custUomCd_A
        {"MAN_PRC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manPrcFlg_A
        {"SET_ITEM_SHIP_CPLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//setItemShipCpltFlg_A
        {"UNIT_NET_WT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unitNetWt_A
        {"MDSE_FRT_CLS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseFrtClsCd_A
        {"MDSE_PRC_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdsePrcGrpCd_A
        {"MDSE_PRC_GRP_GRS_WT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdsePrcGrpGrsWt_A
        {"DEAL_SPLY_QUOTE_DTL_SLS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealSplyQuoteDtlSlsAmt_A
        {"XX_TOT_DISC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotDiscAmt_A
        {"XX_TOT_SPCL_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotSpclPrcAmt_A
        {"XX_TOT_NET_DISC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotNetDiscAmt_A
        {"DEAL_SPLY_QUOTE_DTL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealSplyQuoteDtlNetAmt_A
        {"FUNC_QUOTE_DTL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcQuoteDtlNetAmt_A
        {"XX_TOT_FRT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotFrtAmt_A
        {"XX_TOT_SPCL_CHRG_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotSpclChrgAmt_A
        {"XX_TOT_TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotTaxAmt_A
        {"XX_TOT_NET_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotNetPrcAmt_A
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_A
        {"DS_ORD_LINE_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgCd_A
        {"ORD_LINE_SRC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLineSrcCd_A
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_A
        {"DEAL_PRC_LIST_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealPrcListPrcAmt_A
        {"FUNC_PRC_LIST_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcPrcListPrcAmt_A
        {"SUPD_LOCK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdLockFlg_A
		null,	//NWZC167002PMsg
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

