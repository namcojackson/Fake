//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20200602123439000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC167002_APMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC167002 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC167002_APMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORD_PRC_CALC_BASE_PK_A*/
	public final EZDPBigDecimalItem              ordPrcCalcBasePk_A;

    /** SPLY_QUOTE_DTL_LINE_NUM_A*/
	public final EZDPStringItem              splyQuoteDtlLineNum_A;

    /** SPLY_QUOTE_DTL_LINE_SUB_NUM_A*/
	public final EZDPStringItem              splyQuoteDtlLineSubNum_A;

    /** PRC_COND_TP_CD_A*/
	public final EZDPStringItem              prcCondTpCd_A;

    /** PRC_COND_TP_DESC_TXT_A*/
	public final EZDPStringItem              prcCondTpDescTxt_A;

    /** PRC_DTL_GRP_CD_A*/
	public final EZDPStringItem              prcDtlGrpCd_A;

    /** PRC_JRNL_GRP_CD_A*/
	public final EZDPStringItem              prcJrnlGrpCd_A;

    /** PRC_COND_MAN_ENTRY_FLG_A*/
	public final EZDPStringItem              prcCondManEntryFlg_A;

    /** PRC_COND_MAN_ADD_FLG_A*/
	public final EZDPStringItem              prcCondManAddFlg_A;

    /** PRC_COND_MAN_DEL_FLG_A*/
	public final EZDPStringItem              prcCondManDelFlg_A;

    /** PKG_UOM_CD_A*/
	public final EZDPStringItem              pkgUomCd_A;

    /** PRC_COND_UNIT_CD_A*/
	public final EZDPStringItem              prcCondUnitCd_A;

    /** PRC_CALC_METH_CD_A*/
	public final EZDPStringItem              prcCalcMethCd_A;

    /** AUTO_PRC_AMT_RATE_A*/
	public final EZDPBigDecimalItem              autoPrcAmtRate_A;

    /** MAX_PRC_AMT_RATE_A*/
	public final EZDPBigDecimalItem              maxPrcAmtRate_A;

    /** MIN_PRC_AMT_RATE_A*/
	public final EZDPBigDecimalItem              minPrcAmtRate_A;

    /** MAN_PRC_AMT_RATE_A*/
	public final EZDPBigDecimalItem              manPrcAmtRate_A;

    /** CALC_PRC_AMT_RATE_A*/
	public final EZDPBigDecimalItem              calcPrcAmtRate_A;

    /** UNIT_PRC_AMT_A*/
	public final EZDPBigDecimalItem              unitPrcAmt_A;

    /** DS_MDSE_PRC_PK_A*/
	public final EZDPBigDecimalItem              dsMdsePrcPk_A;

    /** SPEC_COND_PRC_PK_A*/
	public final EZDPBigDecimalItem              specCondPrcPk_A;

    /** FRT_PER_WT_PK_A*/
	public final EZDPBigDecimalItem              frtPerWtPk_A;

    /** AUTO_PRC_CCY_CD_A*/
	public final EZDPStringItem              autoPrcCcyCd_A;

    /** PRC_RULE_APPLY_FLG_A*/
	public final EZDPStringItem              prcRuleApplyFlg_A;

    /** PRC_RULE_PRCD_PK_A*/
	public final EZDPBigDecimalItem              prcRulePrcdPk_A;


	/**
	 * NWZC167002_APMsg is constructor.
	 * The initialization when the instance of NWZC167002_APMsg is generated.
	 */
	public NWZC167002_APMsg() {
		this(false, -1);
	}

	/**
	 * NWZC167002_APMsg is constructor.
	 * The initialization when the instance of NWZC167002_APMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC167002_APMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ordPrcCalcBasePk_A = (EZDPBigDecimalItem)newItem("ordPrcCalcBasePk_A");
		splyQuoteDtlLineNum_A = (EZDPStringItem)newItem("splyQuoteDtlLineNum_A");
		splyQuoteDtlLineSubNum_A = (EZDPStringItem)newItem("splyQuoteDtlLineSubNum_A");
		prcCondTpCd_A = (EZDPStringItem)newItem("prcCondTpCd_A");
		prcCondTpDescTxt_A = (EZDPStringItem)newItem("prcCondTpDescTxt_A");
		prcDtlGrpCd_A = (EZDPStringItem)newItem("prcDtlGrpCd_A");
		prcJrnlGrpCd_A = (EZDPStringItem)newItem("prcJrnlGrpCd_A");
		prcCondManEntryFlg_A = (EZDPStringItem)newItem("prcCondManEntryFlg_A");
		prcCondManAddFlg_A = (EZDPStringItem)newItem("prcCondManAddFlg_A");
		prcCondManDelFlg_A = (EZDPStringItem)newItem("prcCondManDelFlg_A");
		pkgUomCd_A = (EZDPStringItem)newItem("pkgUomCd_A");
		prcCondUnitCd_A = (EZDPStringItem)newItem("prcCondUnitCd_A");
		prcCalcMethCd_A = (EZDPStringItem)newItem("prcCalcMethCd_A");
		autoPrcAmtRate_A = (EZDPBigDecimalItem)newItem("autoPrcAmtRate_A");
		maxPrcAmtRate_A = (EZDPBigDecimalItem)newItem("maxPrcAmtRate_A");
		minPrcAmtRate_A = (EZDPBigDecimalItem)newItem("minPrcAmtRate_A");
		manPrcAmtRate_A = (EZDPBigDecimalItem)newItem("manPrcAmtRate_A");
		calcPrcAmtRate_A = (EZDPBigDecimalItem)newItem("calcPrcAmtRate_A");
		unitPrcAmt_A = (EZDPBigDecimalItem)newItem("unitPrcAmt_A");
		dsMdsePrcPk_A = (EZDPBigDecimalItem)newItem("dsMdsePrcPk_A");
		specCondPrcPk_A = (EZDPBigDecimalItem)newItem("specCondPrcPk_A");
		frtPerWtPk_A = (EZDPBigDecimalItem)newItem("frtPerWtPk_A");
		autoPrcCcyCd_A = (EZDPStringItem)newItem("autoPrcCcyCd_A");
		prcRuleApplyFlg_A = (EZDPStringItem)newItem("prcRuleApplyFlg_A");
		prcRulePrcdPk_A = (EZDPBigDecimalItem)newItem("prcRulePrcdPk_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC167002_APMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC167002_APMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ordPrcCalcBasePk_A", "ordPrcCalcBasePk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"splyQuoteDtlLineNum_A", "splyQuoteDtlLineNum_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"splyQuoteDtlLineSubNum_A", "splyQuoteDtlLineSubNum_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"prcCondTpCd_A", "prcCondTpCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"prcCondTpDescTxt_A", "prcCondTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"prcDtlGrpCd_A", "prcDtlGrpCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"prcJrnlGrpCd_A", "prcJrnlGrpCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"prcCondManEntryFlg_A", "prcCondManEntryFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"prcCondManAddFlg_A", "prcCondManAddFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"prcCondManDelFlg_A", "prcCondManDelFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"pkgUomCd_A", "pkgUomCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"prcCondUnitCd_A", "prcCondUnitCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"prcCalcMethCd_A", "prcCalcMethCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"autoPrcAmtRate_A", "autoPrcAmtRate_A", "A", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"maxPrcAmtRate_A", "maxPrcAmtRate_A", "A", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"minPrcAmtRate_A", "minPrcAmtRate_A", "A", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"manPrcAmtRate_A", "manPrcAmtRate_A", "A", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"calcPrcAmtRate_A", "calcPrcAmtRate_A", "A", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"unitPrcAmt_A", "unitPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsMdsePrcPk_A", "dsMdsePrcPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"specCondPrcPk_A", "specCondPrcPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"frtPerWtPk_A", "frtPerWtPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"autoPrcCcyCd_A", "autoPrcCcyCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"prcRuleApplyFlg_A", "prcRuleApplyFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"prcRulePrcdPk_A", "prcRulePrcdPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORD_PRC_CALC_BASE_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordPrcCalcBasePk_A
        {"SPLY_QUOTE_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyQuoteDtlLineNum_A
        {"SPLY_QUOTE_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyQuoteDtlLineSubNum_A
        {"PRC_COND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondTpCd_A
        {"PRC_COND_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondTpDescTxt_A
        {"PRC_DTL_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcDtlGrpCd_A
        {"PRC_JRNL_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcJrnlGrpCd_A
        {"PRC_COND_MAN_ENTRY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondManEntryFlg_A
        {"PRC_COND_MAN_ADD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondManAddFlg_A
        {"PRC_COND_MAN_DEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondManDelFlg_A
        {"PKG_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomCd_A
        {"PRC_COND_UNIT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondUnitCd_A
        {"PRC_CALC_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCalcMethCd_A
        {"AUTO_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoPrcAmtRate_A
        {"MAX_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxPrcAmtRate_A
        {"MIN_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minPrcAmtRate_A
        {"MAN_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manPrcAmtRate_A
        {"CALC_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//calcPrcAmtRate_A
        {"UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unitPrcAmt_A
        {"DS_MDSE_PRC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMdsePrcPk_A
        {"SPEC_COND_PRC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//specCondPrcPk_A
        {"FRT_PER_WT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtPerWtPk_A
        {"AUTO_PRC_CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoPrcCcyCd_A
        {"PRC_RULE_APPLY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleApplyFlg_A
        {"PRC_RULE_PRCD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRulePrcdPk_A
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
