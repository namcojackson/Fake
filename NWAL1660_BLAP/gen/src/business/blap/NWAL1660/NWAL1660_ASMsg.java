//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20151110150437000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1660_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1660;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1660 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1660_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_COND_MAN_DEL_FLG_A*/
	public final EZDSStringItem              prcCondManDelFlg_A;

    /** PRC_RULE_HDR_PK_A*/
	public final EZDSBigDecimalItem              prcRuleHdrPk_A;

    /** PRC_RULE_CATG_CD_A*/
	public final EZDSStringItem              prcRuleCatgCd_A;

    /** PRC_RULE_CATG_DESC_TXT_A*/
	public final EZDSStringItem              prcRuleCatgDescTxt_A;

    /** PRC_COND_UNIT_CD_A*/
	public final EZDSStringItem              prcCondUnitCd_A;

    /** PRC_RULE_DTL_CHRG_NM_A*/
	public final EZDSStringItem              prcRuleDtlChrgNm_A;

    /** PRC_RULE_ADJ_TP_CD_A*/
	public final EZDSStringItem              prcRuleAdjTpCd_A;

    /** PRC_RULE_ADJ_TP_DESC_TXT_A*/
	public final EZDSStringItem              prcRuleAdjTpDescTxt_A;

    /** APPLY_AUTO_FLG_A*/
	public final EZDSStringItem              applyAutoFlg_A;

    /** MAN_PRC_AMT_RATE_A*/
	public final EZDSBigDecimalItem              manPrcAmtRate_A;

    /** CALC_PRC_AMT_RATE_A*/
	public final EZDSBigDecimalItem              calcPrcAmtRate_A;

    /** FUNC_UNIT_LIST_PRC_AMT_A*/
	public final EZDSBigDecimalItem              funcUnitListPrcAmt_A;

    /** FUNC_NET_SELL_PRC_AMT_A*/
	public final EZDSBigDecimalItem              funcNetSellPrcAmt_A;


	/**
	 * NWAL1660_ASMsg is constructor.
	 * The initialization when the instance of NWAL1660_ASMsg is generated.
	 */
	public NWAL1660_ASMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1660_ASMsg is constructor.
	 * The initialization when the instance of NWAL1660_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1660_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcCondManDelFlg_A = (EZDSStringItem)newItem("prcCondManDelFlg_A");
		prcRuleHdrPk_A = (EZDSBigDecimalItem)newItem("prcRuleHdrPk_A");
		prcRuleCatgCd_A = (EZDSStringItem)newItem("prcRuleCatgCd_A");
		prcRuleCatgDescTxt_A = (EZDSStringItem)newItem("prcRuleCatgDescTxt_A");
		prcCondUnitCd_A = (EZDSStringItem)newItem("prcCondUnitCd_A");
		prcRuleDtlChrgNm_A = (EZDSStringItem)newItem("prcRuleDtlChrgNm_A");
		prcRuleAdjTpCd_A = (EZDSStringItem)newItem("prcRuleAdjTpCd_A");
		prcRuleAdjTpDescTxt_A = (EZDSStringItem)newItem("prcRuleAdjTpDescTxt_A");
		applyAutoFlg_A = (EZDSStringItem)newItem("applyAutoFlg_A");
		manPrcAmtRate_A = (EZDSBigDecimalItem)newItem("manPrcAmtRate_A");
		calcPrcAmtRate_A = (EZDSBigDecimalItem)newItem("calcPrcAmtRate_A");
		funcUnitListPrcAmt_A = (EZDSBigDecimalItem)newItem("funcUnitListPrcAmt_A");
		funcNetSellPrcAmt_A = (EZDSBigDecimalItem)newItem("funcNetSellPrcAmt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1660_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1660_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcCondManDelFlg_A", "prcCondManDelFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"prcRuleHdrPk_A", "prcRuleHdrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcRuleCatgCd_A", "prcRuleCatgCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"prcRuleCatgDescTxt_A", "prcRuleCatgDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"prcCondUnitCd_A", "prcCondUnitCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"prcRuleDtlChrgNm_A", "prcRuleDtlChrgNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"prcRuleAdjTpCd_A", "prcRuleAdjTpCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"prcRuleAdjTpDescTxt_A", "prcRuleAdjTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"applyAutoFlg_A", "applyAutoFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"manPrcAmtRate_A", "manPrcAmtRate_A", "A", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"calcPrcAmtRate_A", "calcPrcAmtRate_A", "A", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"funcUnitListPrcAmt_A", "funcUnitListPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcNetSellPrcAmt_A", "funcNetSellPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_COND_MAN_DEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondManDelFlg_A
        {"PRC_RULE_HDR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleHdrPk_A
        {"PRC_RULE_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCatgCd_A
        {"PRC_RULE_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCatgDescTxt_A
        {"PRC_COND_UNIT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondUnitCd_A
        {"PRC_RULE_DTL_CHRG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleDtlChrgNm_A
        {"PRC_RULE_ADJ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleAdjTpCd_A
        {"PRC_RULE_ADJ_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleAdjTpDescTxt_A
        {"APPLY_AUTO_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//applyAutoFlg_A
        {"MAN_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manPrcAmtRate_A
        {"CALC_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//calcPrcAmtRate_A
        {"FUNC_UNIT_LIST_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcUnitListPrcAmt_A
        {"FUNC_NET_SELL_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcNetSellPrcAmt_A
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
