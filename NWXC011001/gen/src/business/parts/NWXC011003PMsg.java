//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20180906155627000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWXC011003PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWXC011003 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWXC011003PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TRX_LINE_NUM*/
	public final EZDPStringItem              trxLineNum;

    /** TRX_LINE_SUB_NUM*/
	public final EZDPStringItem              trxLineSubNum;

    /** CONFIG_CATG_CD*/
	public final EZDPStringItem              configCatgCd;

    /** PRC_COND_TP_CD*/
	public final EZDPStringItem              prcCondTpCd;

    /** PRC_COND_TP_DESC_TXT*/
	public final EZDPStringItem              prcCondTpDescTxt;

    /** PRC_DTL_GRP_CD*/
	public final EZDPStringItem              prcDtlGrpCd;

    /** PRC_JRNL_GRP_CD*/
	public final EZDPStringItem              prcJrnlGrpCd;

    /** PRC_CATG_CD*/
	public final EZDPStringItem              prcCatgCd;

    /** PRC_COND_MAN_ENTRY_FLG*/
	public final EZDPStringItem              prcCondManEntryFlg;

    /** PRC_COND_MAN_ADD_FLG*/
	public final EZDPStringItem              prcCondManAddFlg;

    /** PRC_COND_MAN_DEL_FLG*/
	public final EZDPStringItem              prcCondManDelFlg;

    /** PKG_UOM_CD*/
	public final EZDPStringItem              pkgUomCd;

    /** PRC_COND_UNIT_CD*/
	public final EZDPStringItem              prcCondUnitCd;

    /** PRC_CALC_METH_CD*/
	public final EZDPStringItem              prcCalcMethCd;

    /** AUTO_PRC_CCY_CD*/
	public final EZDPStringItem              autoPrcCcyCd;

    /** AUTO_PRC_AMT_RATE*/
	public final EZDPBigDecimalItem              autoPrcAmtRate;

    /** MAX_PRC_AMT_RATE*/
	public final EZDPBigDecimalItem              maxPrcAmtRate;

    /** MIN_PRC_AMT_RATE*/
	public final EZDPBigDecimalItem              minPrcAmtRate;

    /** MAN_PRC_AMT_RATE*/
	public final EZDPBigDecimalItem              manPrcAmtRate;

    /** CALC_PRC_AMT_RATE*/
	public final EZDPBigDecimalItem              calcPrcAmtRate;

    /** UNIT_PRC_AMT*/
	public final EZDPBigDecimalItem              unitPrcAmt;

    /** DS_MDSE_PRC_PK*/
	public final EZDPBigDecimalItem              dsMdsePrcPk;

    /** SPEC_COND_PRC_PK*/
	public final EZDPBigDecimalItem              specCondPrcPk;

    /** FRT_PER_WT_PK*/
	public final EZDPBigDecimalItem              frtPerWtPk;

    /** ORD_PRC_CALC_BASE_PK*/
	public final EZDPBigDecimalItem              ordPrcCalcBasePk;

    /** PRC_RULE_APPLY_FLG*/
	public final EZDPStringItem              prcRuleApplyFlg;

    /** PRC_RULE_PRCD_PK*/
	public final EZDPBigDecimalItem              prcRulePrcdPk;

    /** xxMsgIdList*/
	public final business.parts.NWXC011003_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NWXC011003PMsg is constructor.
	 * The initialization when the instance of NWXC011003PMsg is generated.
	 */
	public NWXC011003PMsg() {
		this(false, -1);
	}

	/**
	 * NWXC011003PMsg is constructor.
	 * The initialization when the instance of NWXC011003PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWXC011003PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		trxLineNum = (EZDPStringItem)newItem("trxLineNum");
		trxLineSubNum = (EZDPStringItem)newItem("trxLineSubNum");
		configCatgCd = (EZDPStringItem)newItem("configCatgCd");
		prcCondTpCd = (EZDPStringItem)newItem("prcCondTpCd");
		prcCondTpDescTxt = (EZDPStringItem)newItem("prcCondTpDescTxt");
		prcDtlGrpCd = (EZDPStringItem)newItem("prcDtlGrpCd");
		prcJrnlGrpCd = (EZDPStringItem)newItem("prcJrnlGrpCd");
		prcCatgCd = (EZDPStringItem)newItem("prcCatgCd");
		prcCondManEntryFlg = (EZDPStringItem)newItem("prcCondManEntryFlg");
		prcCondManAddFlg = (EZDPStringItem)newItem("prcCondManAddFlg");
		prcCondManDelFlg = (EZDPStringItem)newItem("prcCondManDelFlg");
		pkgUomCd = (EZDPStringItem)newItem("pkgUomCd");
		prcCondUnitCd = (EZDPStringItem)newItem("prcCondUnitCd");
		prcCalcMethCd = (EZDPStringItem)newItem("prcCalcMethCd");
		autoPrcCcyCd = (EZDPStringItem)newItem("autoPrcCcyCd");
		autoPrcAmtRate = (EZDPBigDecimalItem)newItem("autoPrcAmtRate");
		maxPrcAmtRate = (EZDPBigDecimalItem)newItem("maxPrcAmtRate");
		minPrcAmtRate = (EZDPBigDecimalItem)newItem("minPrcAmtRate");
		manPrcAmtRate = (EZDPBigDecimalItem)newItem("manPrcAmtRate");
		calcPrcAmtRate = (EZDPBigDecimalItem)newItem("calcPrcAmtRate");
		unitPrcAmt = (EZDPBigDecimalItem)newItem("unitPrcAmt");
		dsMdsePrcPk = (EZDPBigDecimalItem)newItem("dsMdsePrcPk");
		specCondPrcPk = (EZDPBigDecimalItem)newItem("specCondPrcPk");
		frtPerWtPk = (EZDPBigDecimalItem)newItem("frtPerWtPk");
		ordPrcCalcBasePk = (EZDPBigDecimalItem)newItem("ordPrcCalcBasePk");
		prcRuleApplyFlg = (EZDPStringItem)newItem("prcRuleApplyFlg");
		prcRulePrcdPk = (EZDPBigDecimalItem)newItem("prcRulePrcdPk");
		xxMsgIdList = (business.parts.NWXC011003_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NWXC011003PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWXC011003PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"trxLineNum", "trxLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"trxLineSubNum", "trxLineSubNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"configCatgCd", "configCatgCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"prcCondTpCd", "prcCondTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"prcCondTpDescTxt", "prcCondTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcDtlGrpCd", "prcDtlGrpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"prcJrnlGrpCd", "prcJrnlGrpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"prcCatgCd", "prcCatgCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"prcCondManEntryFlg", "prcCondManEntryFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"prcCondManAddFlg", "prcCondManAddFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"prcCondManDelFlg", "prcCondManDelFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"pkgUomCd", "pkgUomCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"prcCondUnitCd", "prcCondUnitCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"prcCalcMethCd", "prcCalcMethCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"autoPrcCcyCd", "autoPrcCcyCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"autoPrcAmtRate", "autoPrcAmtRate", null, null, TYPE_SEISU_SYOSU, "19", "6"},
	{"maxPrcAmtRate", "maxPrcAmtRate", null, null, TYPE_SEISU_SYOSU, "19", "6"},
	{"minPrcAmtRate", "minPrcAmtRate", null, null, TYPE_SEISU_SYOSU, "19", "6"},
	{"manPrcAmtRate", "manPrcAmtRate", null, null, TYPE_SEISU_SYOSU, "19", "6"},
	{"calcPrcAmtRate", "calcPrcAmtRate", null, null, TYPE_SEISU_SYOSU, "19", "6"},
	{"unitPrcAmt", "unitPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsMdsePrcPk", "dsMdsePrcPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"specCondPrcPk", "specCondPrcPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"frtPerWtPk", "frtPerWtPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ordPrcCalcBasePk", "ordPrcCalcBasePk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcRuleApplyFlg", "prcRuleApplyFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"prcRulePrcdPk", "prcRulePrcdPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxMsgIdList", "xxMsgIdList", null, "3", "business.parts.NWXC011003_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TRX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineNum
        {"TRX_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineSubNum
        {"CONFIG_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//configCatgCd
        {"PRC_COND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondTpCd
        {"PRC_COND_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondTpDescTxt
        {"PRC_DTL_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcDtlGrpCd
        {"PRC_JRNL_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcJrnlGrpCd
        {"PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd
        {"PRC_COND_MAN_ENTRY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondManEntryFlg
        {"PRC_COND_MAN_ADD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondManAddFlg
        {"PRC_COND_MAN_DEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondManDelFlg
        {"PKG_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomCd
        {"PRC_COND_UNIT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondUnitCd
        {"PRC_CALC_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCalcMethCd
        {"AUTO_PRC_CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoPrcCcyCd
        {"AUTO_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoPrcAmtRate
        {"MAX_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxPrcAmtRate
        {"MIN_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minPrcAmtRate
        {"MAN_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manPrcAmtRate
        {"CALC_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//calcPrcAmtRate
        {"UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unitPrcAmt
        {"DS_MDSE_PRC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMdsePrcPk
        {"SPEC_COND_PRC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//specCondPrcPk
        {"FRT_PER_WT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtPerWtPk
        {"ORD_PRC_CALC_BASE_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordPrcCalcBasePk
        {"PRC_RULE_APPLY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleApplyFlg
        {"PRC_RULE_PRCD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRulePrcdPk
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

