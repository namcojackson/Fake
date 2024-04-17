//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230808112258000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1770_EBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1770;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1770 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1770_EBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_LINE_NUM_E*/
	public final EZDBStringItem              xxLineNum_E;

    /** ORD_PRC_CALC_BASE_PK_E*/
	public final EZDBBigDecimalItem              ordPrcCalcBasePk_E;

    /** SPLY_QUOTE_NUM_E*/
	public final EZDBStringItem              splyQuoteNum_E;

    /** SPLY_QUOTE_DTL_LINE_NUM_E*/
	public final EZDBStringItem              splyQuoteDtlLineNum_E;

    /** SPLY_QUOTE_DTL_LINE_SUB_NUM_E*/
	public final EZDBStringItem              splyQuoteDtlLineSubNum_E;

    /** PRC_COND_TP_CD_E*/
	public final EZDBStringItem              prcCondTpCd_E;

    /** PRC_COND_TP_DESC_TXT_E*/
	public final EZDBStringItem              prcCondTpDescTxt_E;

    /** PRC_DTL_GRP_CD_E*/
	public final EZDBStringItem              prcDtlGrpCd_E;

    /** PRC_JRNL_GRP_CD_E*/
	public final EZDBStringItem              prcJrnlGrpCd_E;

    /** PRC_CATG_CD_E*/
	public final EZDBStringItem              prcCatgCd_E;

    /** PKG_UOM_CD_E*/
	public final EZDBStringItem              pkgUomCd_E;

    /** PRC_COND_UNIT_CD_E*/
	public final EZDBStringItem              prcCondUnitCd_E;

    /** PRC_CALC_METH_CD_E*/
	public final EZDBStringItem              prcCalcMethCd_E;

    /** DS_MDSE_PRC_PK_E*/
	public final EZDBBigDecimalItem              dsMdsePrcPk_E;

    /** SPEC_COND_PRC_PK_E*/
	public final EZDBBigDecimalItem              specCondPrcPk_E;

    /** FRT_PER_WT_PK_E*/
	public final EZDBBigDecimalItem              frtPerWtPk_E;

    /** PRC_COND_MAN_ENTRY_FLG_E*/
	public final EZDBStringItem              prcCondManEntryFlg_E;

    /** PRC_COND_MAN_ADD_FLG_E*/
	public final EZDBStringItem              prcCondManAddFlg_E;

    /** PRC_COND_MAN_DEL_FLG_E*/
	public final EZDBStringItem              prcCondManDelFlg_E;

    /** AUTO_PRC_AMT_RATE_E*/
	public final EZDBBigDecimalItem              autoPrcAmtRate_E;

    /** MAX_PRC_AMT_RATE_E*/
	public final EZDBBigDecimalItem              maxPrcAmtRate_E;

    /** MIN_PRC_AMT_RATE_E*/
	public final EZDBBigDecimalItem              minPrcAmtRate_E;

    /** MAN_PRC_AMT_RATE_E*/
	public final EZDBBigDecimalItem              manPrcAmtRate_E;

    /** CALC_PRC_AMT_RATE_E*/
	public final EZDBBigDecimalItem              calcPrcAmtRate_E;

    /** UNIT_PRC_AMT_E*/
	public final EZDBBigDecimalItem              unitPrcAmt_E;

    /** COA_ACCT_CD_E*/
	public final EZDBStringItem              coaAcctCd_E;

    /** AUTO_PRC_CCY_CD_E*/
	public final EZDBStringItem              autoPrcCcyCd_E;

    /** PRC_RULE_APPLY_FLG_E*/
	public final EZDBStringItem              prcRuleApplyFlg_E;

    /** PRC_RULE_PRCD_PK_E*/
	public final EZDBBigDecimalItem              prcRulePrcdPk_E;


	/**
	 * NWAL1770_EBMsg is constructor.
	 * The initialization when the instance of NWAL1770_EBMsg is generated.
	 */
	public NWAL1770_EBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1770_EBMsg is constructor.
	 * The initialization when the instance of NWAL1770_EBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1770_EBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxLineNum_E = (EZDBStringItem)newItem("xxLineNum_E");
		ordPrcCalcBasePk_E = (EZDBBigDecimalItem)newItem("ordPrcCalcBasePk_E");
		splyQuoteNum_E = (EZDBStringItem)newItem("splyQuoteNum_E");
		splyQuoteDtlLineNum_E = (EZDBStringItem)newItem("splyQuoteDtlLineNum_E");
		splyQuoteDtlLineSubNum_E = (EZDBStringItem)newItem("splyQuoteDtlLineSubNum_E");
		prcCondTpCd_E = (EZDBStringItem)newItem("prcCondTpCd_E");
		prcCondTpDescTxt_E = (EZDBStringItem)newItem("prcCondTpDescTxt_E");
		prcDtlGrpCd_E = (EZDBStringItem)newItem("prcDtlGrpCd_E");
		prcJrnlGrpCd_E = (EZDBStringItem)newItem("prcJrnlGrpCd_E");
		prcCatgCd_E = (EZDBStringItem)newItem("prcCatgCd_E");
		pkgUomCd_E = (EZDBStringItem)newItem("pkgUomCd_E");
		prcCondUnitCd_E = (EZDBStringItem)newItem("prcCondUnitCd_E");
		prcCalcMethCd_E = (EZDBStringItem)newItem("prcCalcMethCd_E");
		dsMdsePrcPk_E = (EZDBBigDecimalItem)newItem("dsMdsePrcPk_E");
		specCondPrcPk_E = (EZDBBigDecimalItem)newItem("specCondPrcPk_E");
		frtPerWtPk_E = (EZDBBigDecimalItem)newItem("frtPerWtPk_E");
		prcCondManEntryFlg_E = (EZDBStringItem)newItem("prcCondManEntryFlg_E");
		prcCondManAddFlg_E = (EZDBStringItem)newItem("prcCondManAddFlg_E");
		prcCondManDelFlg_E = (EZDBStringItem)newItem("prcCondManDelFlg_E");
		autoPrcAmtRate_E = (EZDBBigDecimalItem)newItem("autoPrcAmtRate_E");
		maxPrcAmtRate_E = (EZDBBigDecimalItem)newItem("maxPrcAmtRate_E");
		minPrcAmtRate_E = (EZDBBigDecimalItem)newItem("minPrcAmtRate_E");
		manPrcAmtRate_E = (EZDBBigDecimalItem)newItem("manPrcAmtRate_E");
		calcPrcAmtRate_E = (EZDBBigDecimalItem)newItem("calcPrcAmtRate_E");
		unitPrcAmt_E = (EZDBBigDecimalItem)newItem("unitPrcAmt_E");
		coaAcctCd_E = (EZDBStringItem)newItem("coaAcctCd_E");
		autoPrcCcyCd_E = (EZDBStringItem)newItem("autoPrcCcyCd_E");
		prcRuleApplyFlg_E = (EZDBStringItem)newItem("prcRuleApplyFlg_E");
		prcRulePrcdPk_E = (EZDBBigDecimalItem)newItem("prcRulePrcdPk_E");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1770_EBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1770_EBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxLineNum_E", "xxLineNum_E", "E", null, TYPE_HANKAKUEISU, "11", null},
	{"ordPrcCalcBasePk_E", "ordPrcCalcBasePk_E", "E", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"splyQuoteNum_E", "splyQuoteNum_E", "E", null, TYPE_HANKAKUEISU, "8", null},
	{"splyQuoteDtlLineNum_E", "splyQuoteDtlLineNum_E", "E", null, TYPE_HANKAKUEISU, "3", null},
	{"splyQuoteDtlLineSubNum_E", "splyQuoteDtlLineSubNum_E", "E", null, TYPE_HANKAKUEISU, "3", null},
	{"prcCondTpCd_E", "prcCondTpCd_E", "E", null, TYPE_HANKAKUEISU, "4", null},
	{"prcCondTpDescTxt_E", "prcCondTpDescTxt_E", "E", null, TYPE_HANKAKUEISU, "50", null},
	{"prcDtlGrpCd_E", "prcDtlGrpCd_E", "E", null, TYPE_HANKAKUEISU, "4", null},
	{"prcJrnlGrpCd_E", "prcJrnlGrpCd_E", "E", null, TYPE_HANKAKUEISU, "4", null},
	{"prcCatgCd_E", "prcCatgCd_E", "E", null, TYPE_HANKAKUEISU, "10", null},
	{"pkgUomCd_E", "pkgUomCd_E", "E", null, TYPE_HANKAKUEISU, "4", null},
	{"prcCondUnitCd_E", "prcCondUnitCd_E", "E", null, TYPE_HANKAKUEISU, "1", null},
	{"prcCalcMethCd_E", "prcCalcMethCd_E", "E", null, TYPE_HANKAKUEISU, "2", null},
	{"dsMdsePrcPk_E", "dsMdsePrcPk_E", "E", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"specCondPrcPk_E", "specCondPrcPk_E", "E", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"frtPerWtPk_E", "frtPerWtPk_E", "E", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcCondManEntryFlg_E", "prcCondManEntryFlg_E", "E", null, TYPE_HANKAKUEISU, "1", null},
	{"prcCondManAddFlg_E", "prcCondManAddFlg_E", "E", null, TYPE_HANKAKUEISU, "1", null},
	{"prcCondManDelFlg_E", "prcCondManDelFlg_E", "E", null, TYPE_HANKAKUEISU, "1", null},
	{"autoPrcAmtRate_E", "autoPrcAmtRate_E", "E", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"maxPrcAmtRate_E", "maxPrcAmtRate_E", "E", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"minPrcAmtRate_E", "minPrcAmtRate_E", "E", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"manPrcAmtRate_E", "manPrcAmtRate_E", "E", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"calcPrcAmtRate_E", "calcPrcAmtRate_E", "E", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"unitPrcAmt_E", "unitPrcAmt_E", "E", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"coaAcctCd_E", "coaAcctCd_E", "E", null, TYPE_HANKAKUEISU, "8", null},
	{"autoPrcCcyCd_E", "autoPrcCcyCd_E", "E", null, TYPE_HANKAKUEISU, "3", null},
	{"prcRuleApplyFlg_E", "prcRuleApplyFlg_E", "E", null, TYPE_HANKAKUEISU, "1", null},
	{"prcRulePrcdPk_E", "prcRulePrcdPk_E", "E", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineNum_E
        {"ORD_PRC_CALC_BASE_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordPrcCalcBasePk_E
        {"SPLY_QUOTE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyQuoteNum_E
        {"SPLY_QUOTE_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyQuoteDtlLineNum_E
        {"SPLY_QUOTE_DTL_LINE_SUB_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyQuoteDtlLineSubNum_E
        {"PRC_COND_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondTpCd_E
        {"PRC_COND_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondTpDescTxt_E
        {"PRC_DTL_GRP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcDtlGrpCd_E
        {"PRC_JRNL_GRP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcJrnlGrpCd_E
        {"PRC_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd_E
        {"PKG_UOM_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomCd_E
        {"PRC_COND_UNIT_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondUnitCd_E
        {"PRC_CALC_METH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCalcMethCd_E
        {"DS_MDSE_PRC_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMdsePrcPk_E
        {"SPEC_COND_PRC_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//specCondPrcPk_E
        {"FRT_PER_WT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtPerWtPk_E
        {"PRC_COND_MAN_ENTRY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondManEntryFlg_E
        {"PRC_COND_MAN_ADD_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondManAddFlg_E
        {"PRC_COND_MAN_DEL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondManDelFlg_E
        {"AUTO_PRC_AMT_RATE",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoPrcAmtRate_E
        {"MAX_PRC_AMT_RATE",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxPrcAmtRate_E
        {"MIN_PRC_AMT_RATE",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minPrcAmtRate_E
        {"MAN_PRC_AMT_RATE",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manPrcAmtRate_E
        {"CALC_PRC_AMT_RATE",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//calcPrcAmtRate_E
        {"UNIT_PRC_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unitPrcAmt_E
        {"COA_ACCT_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctCd_E
        {"AUTO_PRC_CCY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoPrcCcyCd_E
        {"PRC_RULE_APPLY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleApplyFlg_E
        {"PRC_RULE_PRCD_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRulePrcdPk_E
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

