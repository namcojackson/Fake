//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161018162428000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1540_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1540;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1540 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1540_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORD_PRFT_TRX_CATG_CD_A*/
	public final EZDBStringItem              ordPrftTrxCatgCd_A;

    /** XX_LINE_NUM_A*/
	public final EZDBStringItem              xxLineNum_A;

    /** MDSE_CD_A*/
	public final EZDBStringItem              mdseCd_A;

    /** MDSE_DESC_SHORT_TXT_A*/
	public final EZDBStringItem              mdseDescShortTxt_A;

    /** FUNC_CSMP_FL_PRC_AMT_A*/
	public final EZDBBigDecimalItem              funcCsmpFlPrcAmt_A;

    /** FUNC_NET_SELL_PRC_AMT_A*/
	public final EZDBBigDecimalItem              funcNetSellPrcAmt_A;

    /** FUNC_FINAL_FL_PRC_AMT_A*/
	public final EZDBBigDecimalItem              funcFinalFlPrcAmt_A;

    /** FUNC_FINAL_REP_REV_AMT_A*/
	public final EZDBBigDecimalItem              funcFinalRepRevAmt_A;

    /** FUNC_CSMP_UNIT_CR_AMT_A*/
	public final EZDBBigDecimalItem              funcCsmpUnitCrAmt_A;

    /** FUNC_CSMP_CR_AMT_A*/
	public final EZDBBigDecimalItem              funcCsmpCrAmt_A;

    /** CSMP_CONTR_NUM_A*/
	public final EZDBStringItem              csmpContrNum_A;

    /** DLR_REF_NUM_A*/
	public final EZDBStringItem              dlrRefNum_A;

    /** CSMP_PRC_LIST_CD_A*/
	public final EZDBStringItem              csmpPrcListCd_A;

    /** PRC_CATG_NM_A*/
	public final EZDBStringItem              prcCatgNm_A;

    /** FL_PRC_LIST_CD_A*/
	public final EZDBStringItem              flPrcListCd_A;

    /** FL_PRC_LIST_NM_A*/
	public final EZDBStringItem              flPrcListNm_A;

    /** FUNC_UNIT_FL_PRC_AMT_A*/
	public final EZDBBigDecimalItem              funcUnitFlPrcAmt_A;

    /** FUNC_UNIT_MSRP_AMT_A*/
	public final EZDBBigDecimalItem              funcUnitMsrpAmt_A;

    /** LINE_WT_AMT_RATE_A*/
	public final EZDBBigDecimalItem              lineWtAmtRate_A;

    /** FUNC_FL_ADJ_AMT_A*/
	public final EZDBBigDecimalItem              funcFlAdjAmt_A;

    /** FUNC_REP_REV_ADJ_AMT_A*/
	public final EZDBBigDecimalItem              funcRepRevAdjAmt_A;

    /** FUNC_UNIT_STD_COST_AMT_A*/
	public final EZDBBigDecimalItem              funcUnitStdCostAmt_A;

    /** DS_ORD_POSN_NUM_A*/
	public final EZDBStringItem              dsOrdPosnNum_A;

    /** COA_PROJ_NM_A*/
	public final EZDBStringItem              coaProjNm_A;

    /** ORD_QTY_A*/
	public final EZDBBigDecimalItem              ordQty_A;

    /** FUNC_NET_UNIT_PRC_AMT_A*/
	public final EZDBBigDecimalItem              funcNetUnitPrcAmt_A;

    /** FUNC_FINAL_UNIT_FL_PRC_AMT_A*/
	public final EZDBBigDecimalItem              funcFinalUnitFlPrcAmt_A;

    /** FUNC_FINAL_UNIT_REV_AMT_A*/
	public final EZDBBigDecimalItem              funcFinalUnitRevAmt_A;

    /** PRC_CATG_CD_A*/
	public final EZDBStringItem              prcCatgCd_A;

    /** PRC_CATG_NM_A2*/
	public final EZDBStringItem              prcCatgNm_A2;

    /** FUNC_UNIT_LIST_PRC_AMT_A*/
	public final EZDBBigDecimalItem              funcUnitListPrcAmt_A;

    /** FUNC_WT_SVC_REV_TRNSF_AMT_A*/
	public final EZDBBigDecimalItem              funcWtSvcRevTrnsfAmt_A;

    /** FUNC_WT_SVC_COST_TRNSF_AMT_A*/
	public final EZDBBigDecimalItem              funcWtSvcCostTrnsfAmt_A;

    /** FUNC_WT_MAN_FL_ADJ_AMT_A*/
	public final EZDBBigDecimalItem              funcWtManFlAdjAmt_A;

    /** FUNC_WT_MAN_REV_ADJ_AMT_A*/
	public final EZDBBigDecimalItem              funcWtManRevAdjAmt_A;


	/**
	 * NWAL1540_ABMsg is constructor.
	 * The initialization when the instance of NWAL1540_ABMsg is generated.
	 */
	public NWAL1540_ABMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1540_ABMsg is constructor.
	 * The initialization when the instance of NWAL1540_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1540_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ordPrftTrxCatgCd_A = (EZDBStringItem)newItem("ordPrftTrxCatgCd_A");
		xxLineNum_A = (EZDBStringItem)newItem("xxLineNum_A");
		mdseCd_A = (EZDBStringItem)newItem("mdseCd_A");
		mdseDescShortTxt_A = (EZDBStringItem)newItem("mdseDescShortTxt_A");
		funcCsmpFlPrcAmt_A = (EZDBBigDecimalItem)newItem("funcCsmpFlPrcAmt_A");
		funcNetSellPrcAmt_A = (EZDBBigDecimalItem)newItem("funcNetSellPrcAmt_A");
		funcFinalFlPrcAmt_A = (EZDBBigDecimalItem)newItem("funcFinalFlPrcAmt_A");
		funcFinalRepRevAmt_A = (EZDBBigDecimalItem)newItem("funcFinalRepRevAmt_A");
		funcCsmpUnitCrAmt_A = (EZDBBigDecimalItem)newItem("funcCsmpUnitCrAmt_A");
		funcCsmpCrAmt_A = (EZDBBigDecimalItem)newItem("funcCsmpCrAmt_A");
		csmpContrNum_A = (EZDBStringItem)newItem("csmpContrNum_A");
		dlrRefNum_A = (EZDBStringItem)newItem("dlrRefNum_A");
		csmpPrcListCd_A = (EZDBStringItem)newItem("csmpPrcListCd_A");
		prcCatgNm_A = (EZDBStringItem)newItem("prcCatgNm_A");
		flPrcListCd_A = (EZDBStringItem)newItem("flPrcListCd_A");
		flPrcListNm_A = (EZDBStringItem)newItem("flPrcListNm_A");
		funcUnitFlPrcAmt_A = (EZDBBigDecimalItem)newItem("funcUnitFlPrcAmt_A");
		funcUnitMsrpAmt_A = (EZDBBigDecimalItem)newItem("funcUnitMsrpAmt_A");
		lineWtAmtRate_A = (EZDBBigDecimalItem)newItem("lineWtAmtRate_A");
		funcFlAdjAmt_A = (EZDBBigDecimalItem)newItem("funcFlAdjAmt_A");
		funcRepRevAdjAmt_A = (EZDBBigDecimalItem)newItem("funcRepRevAdjAmt_A");
		funcUnitStdCostAmt_A = (EZDBBigDecimalItem)newItem("funcUnitStdCostAmt_A");
		dsOrdPosnNum_A = (EZDBStringItem)newItem("dsOrdPosnNum_A");
		coaProjNm_A = (EZDBStringItem)newItem("coaProjNm_A");
		ordQty_A = (EZDBBigDecimalItem)newItem("ordQty_A");
		funcNetUnitPrcAmt_A = (EZDBBigDecimalItem)newItem("funcNetUnitPrcAmt_A");
		funcFinalUnitFlPrcAmt_A = (EZDBBigDecimalItem)newItem("funcFinalUnitFlPrcAmt_A");
		funcFinalUnitRevAmt_A = (EZDBBigDecimalItem)newItem("funcFinalUnitRevAmt_A");
		prcCatgCd_A = (EZDBStringItem)newItem("prcCatgCd_A");
		prcCatgNm_A2 = (EZDBStringItem)newItem("prcCatgNm_A2");
		funcUnitListPrcAmt_A = (EZDBBigDecimalItem)newItem("funcUnitListPrcAmt_A");
		funcWtSvcRevTrnsfAmt_A = (EZDBBigDecimalItem)newItem("funcWtSvcRevTrnsfAmt_A");
		funcWtSvcCostTrnsfAmt_A = (EZDBBigDecimalItem)newItem("funcWtSvcCostTrnsfAmt_A");
		funcWtManFlAdjAmt_A = (EZDBBigDecimalItem)newItem("funcWtManFlAdjAmt_A");
		funcWtManRevAdjAmt_A = (EZDBBigDecimalItem)newItem("funcWtManRevAdjAmt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1540_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1540_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ordPrftTrxCatgCd_A", "ordPrftTrxCatgCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxLineNum_A", "xxLineNum_A", "A", null, TYPE_HANKAKUEISU, "11", null},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A", "mdseDescShortTxt_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"funcCsmpFlPrcAmt_A", "funcCsmpFlPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcNetSellPrcAmt_A", "funcNetSellPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcFinalFlPrcAmt_A", "funcFinalFlPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcFinalRepRevAmt_A", "funcFinalRepRevAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcCsmpUnitCrAmt_A", "funcCsmpUnitCrAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcCsmpCrAmt_A", "funcCsmpCrAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"csmpContrNum_A", "csmpContrNum_A", "A", null, TYPE_HANKAKUEISU, "15", null},
	{"dlrRefNum_A", "dlrRefNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"csmpPrcListCd_A", "csmpPrcListCd_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"prcCatgNm_A", "prcCatgNm_A", "A", null, TYPE_HANKAKUEISU, "75", null},
	{"flPrcListCd_A", "flPrcListCd_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"flPrcListNm_A", "flPrcListNm_A", "A", null, TYPE_HANKAKUEISU, "75", null},
	{"funcUnitFlPrcAmt_A", "funcUnitFlPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcUnitMsrpAmt_A", "funcUnitMsrpAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"lineWtAmtRate_A", "lineWtAmtRate_A", "A", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"funcFlAdjAmt_A", "funcFlAdjAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcRepRevAdjAmt_A", "funcRepRevAdjAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcUnitStdCostAmt_A", "funcUnitStdCostAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsOrdPosnNum_A", "dsOrdPosnNum_A", "A", null, TYPE_HANKAKUEISU, "6", null},
	{"coaProjNm_A", "coaProjNm_A", "A", null, TYPE_HANKAKUEISU, "240", null},
	{"ordQty_A", "ordQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"funcNetUnitPrcAmt_A", "funcNetUnitPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcFinalUnitFlPrcAmt_A", "funcFinalUnitFlPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcFinalUnitRevAmt_A", "funcFinalUnitRevAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcCatgCd_A", "prcCatgCd_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"prcCatgNm_A2", "prcCatgNm_A2", "A2", null, TYPE_HANKAKUEISU, "75", null},
	{"funcUnitListPrcAmt_A", "funcUnitListPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcWtSvcRevTrnsfAmt_A", "funcWtSvcRevTrnsfAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcWtSvcCostTrnsfAmt_A", "funcWtSvcCostTrnsfAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcWtManFlAdjAmt_A", "funcWtManFlAdjAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcWtManRevAdjAmt_A", "funcWtManRevAdjAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORD_PRFT_TRX_CATG_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordPrftTrxCatgCd_A
        {"XX_LINE_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineNum_A
        {"MDSE_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A
        {"FUNC_CSMP_FL_PRC_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcCsmpFlPrcAmt_A
        {"FUNC_NET_SELL_PRC_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcNetSellPrcAmt_A
        {"FUNC_FINAL_FL_PRC_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcFinalFlPrcAmt_A
        {"FUNC_FINAL_REP_REV_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcFinalRepRevAmt_A
        {"FUNC_CSMP_UNIT_CR_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcCsmpUnitCrAmt_A
        {"FUNC_CSMP_CR_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcCsmpCrAmt_A
        {"CSMP_CONTR_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrNum_A
        {"DLR_REF_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrRefNum_A
        {"CSMP_PRC_LIST_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpPrcListCd_A
        {"PRC_CATG_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm_A
        {"FL_PRC_LIST_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//flPrcListCd_A
        {"FL_PRC_LIST_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//flPrcListNm_A
        {"FUNC_UNIT_FL_PRC_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcUnitFlPrcAmt_A
        {"FUNC_UNIT_MSRP_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcUnitMsrpAmt_A
        {"LINE_WT_AMT_RATE",  NO,  null,null,"0", NO,YES, NO, NO,"15","2",null, null,  NO,  NO},	//lineWtAmtRate_A
        {"FUNC_FL_ADJ_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcFlAdjAmt_A
        {"FUNC_REP_REV_ADJ_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcRepRevAdjAmt_A
        {"FUNC_UNIT_STD_COST_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcUnitStdCostAmt_A
        {"DS_ORD_POSN_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_A
        {"COA_PROJ_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjNm_A
        {"ORD_QTY",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_A
        {"FUNC_NET_UNIT_PRC_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcNetUnitPrcAmt_A
        {"FUNC_FINAL_UNIT_FL_PRC_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcFinalUnitFlPrcAmt_A
        {"FUNC_FINAL_UNIT_REV_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcFinalUnitRevAmt_A
        {"PRC_CATG_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd_A
        {"PRC_CATG_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm_A2
        {"FUNC_UNIT_LIST_PRC_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcUnitListPrcAmt_A
        {"FUNC_WT_SVC_REV_TRNSF_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcWtSvcRevTrnsfAmt_A
        {"FUNC_WT_SVC_COST_TRNSF_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcWtSvcCostTrnsfAmt_A
        {"FUNC_WT_MAN_FL_ADJ_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcWtManFlAdjAmt_A
        {"FUNC_WT_MAN_REV_ADJ_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//funcWtManRevAdjAmt_A
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

