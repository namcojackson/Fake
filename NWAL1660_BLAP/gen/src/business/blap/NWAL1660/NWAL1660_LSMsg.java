//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20151110150437000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1660_LSMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NWAL1660_LSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_COND_TP_CD_PL*/
	public final EZDSStringItem              prcCondTpCd_PL;

    /** PRC_COND_TP_DESC_TXT_PL*/
	public final EZDSStringItem              prcCondTpDescTxt_PL;

    /** PRC_DTL_GRP_CD_PL*/
	public final EZDSStringItem              prcDtlGrpCd_PL;

    /** PRC_JRNL_GRP_CD_PL*/
	public final EZDSStringItem              prcJrnlGrpCd_PL;

    /** PRC_CATG_CD_PL*/
	public final EZDSStringItem              prcCatgCd_PL;

    /** PRC_COND_MAN_ENTRY_FLG_PL*/
	public final EZDSStringItem              prcCondManEntryFlg_PL;

    /** PRC_COND_MAN_ADD_FLG_PL*/
	public final EZDSStringItem              prcCondManAddFlg_PL;

    /** PRC_COND_MAN_DEL_FLG_PL*/
	public final EZDSStringItem              prcCondManDelFlg_PL;

    /** UOM_CD_PL*/
	public final EZDSStringItem              uomCd_PL;

    /** PRC_COND_UNIT_CD_PL*/
	public final EZDSStringItem              prcCondUnitCd_PL;

    /** PRC_CALC_METH_CD_PL*/
	public final EZDSStringItem              prcCalcMethCd_PL;

    /** AUTO_PRC_CCY_CD_PL*/
	public final EZDSStringItem              autoPrcCcyCd_PL;

    /** AUTO_PRC_AMT_RATE_PL*/
	public final EZDSBigDecimalItem              autoPrcAmtRate_PL;

    /** MAX_PRC_AMT_RATE_PL*/
	public final EZDSBigDecimalItem              maxPrcAmtRate_PL;

    /** MIN_PRC_AMT_RATE_PL*/
	public final EZDSBigDecimalItem              minPrcAmtRate_PL;

    /** MAN_PRC_AMT_RATE_PL*/
	public final EZDSBigDecimalItem              manPrcAmtRate_PL;

    /** CALC_PRC_AMT_RATE_PL*/
	public final EZDSBigDecimalItem              calcPrcAmtRate_PL;

    /** UNIT_PRC_AMT_PL*/
	public final EZDSBigDecimalItem              unitPrcAmt_PL;

    /** DS_MDSE_PRC_PK_PL*/
	public final EZDSBigDecimalItem              dsMdsePrcPk_PL;

    /** SPEC_COND_PRC_PK_PL*/
	public final EZDSBigDecimalItem              specCondPrcPk_PL;

    /** FRT_PER_WT_PK_PL*/
	public final EZDSBigDecimalItem              frtPerWtPk_PL;

    /** ORD_PRC_CALC_BASE_PK_PL*/
	public final EZDSBigDecimalItem              ordPrcCalcBasePk_PL;


	/**
	 * NWAL1660_LSMsg is constructor.
	 * The initialization when the instance of NWAL1660_LSMsg is generated.
	 */
	public NWAL1660_LSMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1660_LSMsg is constructor.
	 * The initialization when the instance of NWAL1660_LSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1660_LSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcCondTpCd_PL = (EZDSStringItem)newItem("prcCondTpCd_PL");
		prcCondTpDescTxt_PL = (EZDSStringItem)newItem("prcCondTpDescTxt_PL");
		prcDtlGrpCd_PL = (EZDSStringItem)newItem("prcDtlGrpCd_PL");
		prcJrnlGrpCd_PL = (EZDSStringItem)newItem("prcJrnlGrpCd_PL");
		prcCatgCd_PL = (EZDSStringItem)newItem("prcCatgCd_PL");
		prcCondManEntryFlg_PL = (EZDSStringItem)newItem("prcCondManEntryFlg_PL");
		prcCondManAddFlg_PL = (EZDSStringItem)newItem("prcCondManAddFlg_PL");
		prcCondManDelFlg_PL = (EZDSStringItem)newItem("prcCondManDelFlg_PL");
		uomCd_PL = (EZDSStringItem)newItem("uomCd_PL");
		prcCondUnitCd_PL = (EZDSStringItem)newItem("prcCondUnitCd_PL");
		prcCalcMethCd_PL = (EZDSStringItem)newItem("prcCalcMethCd_PL");
		autoPrcCcyCd_PL = (EZDSStringItem)newItem("autoPrcCcyCd_PL");
		autoPrcAmtRate_PL = (EZDSBigDecimalItem)newItem("autoPrcAmtRate_PL");
		maxPrcAmtRate_PL = (EZDSBigDecimalItem)newItem("maxPrcAmtRate_PL");
		minPrcAmtRate_PL = (EZDSBigDecimalItem)newItem("minPrcAmtRate_PL");
		manPrcAmtRate_PL = (EZDSBigDecimalItem)newItem("manPrcAmtRate_PL");
		calcPrcAmtRate_PL = (EZDSBigDecimalItem)newItem("calcPrcAmtRate_PL");
		unitPrcAmt_PL = (EZDSBigDecimalItem)newItem("unitPrcAmt_PL");
		dsMdsePrcPk_PL = (EZDSBigDecimalItem)newItem("dsMdsePrcPk_PL");
		specCondPrcPk_PL = (EZDSBigDecimalItem)newItem("specCondPrcPk_PL");
		frtPerWtPk_PL = (EZDSBigDecimalItem)newItem("frtPerWtPk_PL");
		ordPrcCalcBasePk_PL = (EZDSBigDecimalItem)newItem("ordPrcCalcBasePk_PL");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1660_LSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1660_LSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcCondTpCd_PL", "prcCondTpCd_PL", "PL", null, TYPE_HANKAKUEISU, "4", null},
	{"prcCondTpDescTxt_PL", "prcCondTpDescTxt_PL", "PL", null, TYPE_HANKAKUEISU, "50", null},
	{"prcDtlGrpCd_PL", "prcDtlGrpCd_PL", "PL", null, TYPE_HANKAKUEISU, "4", null},
	{"prcJrnlGrpCd_PL", "prcJrnlGrpCd_PL", "PL", null, TYPE_HANKAKUEISU, "4", null},
	{"prcCatgCd_PL", "prcCatgCd_PL", "PL", null, TYPE_HANKAKUEISU, "10", null},
	{"prcCondManEntryFlg_PL", "prcCondManEntryFlg_PL", "PL", null, TYPE_HANKAKUEISU, "1", null},
	{"prcCondManAddFlg_PL", "prcCondManAddFlg_PL", "PL", null, TYPE_HANKAKUEISU, "1", null},
	{"prcCondManDelFlg_PL", "prcCondManDelFlg_PL", "PL", null, TYPE_HANKAKUEISU, "1", null},
	{"uomCd_PL", "uomCd_PL", "PL", null, TYPE_HANKAKUEISU, "4", null},
	{"prcCondUnitCd_PL", "prcCondUnitCd_PL", "PL", null, TYPE_HANKAKUEISU, "1", null},
	{"prcCalcMethCd_PL", "prcCalcMethCd_PL", "PL", null, TYPE_HANKAKUEISU, "2", null},
	{"autoPrcCcyCd_PL", "autoPrcCcyCd_PL", "PL", null, TYPE_HANKAKUEISU, "3", null},
	{"autoPrcAmtRate_PL", "autoPrcAmtRate_PL", "PL", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"maxPrcAmtRate_PL", "maxPrcAmtRate_PL", "PL", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"minPrcAmtRate_PL", "minPrcAmtRate_PL", "PL", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"manPrcAmtRate_PL", "manPrcAmtRate_PL", "PL", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"calcPrcAmtRate_PL", "calcPrcAmtRate_PL", "PL", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"unitPrcAmt_PL", "unitPrcAmt_PL", "PL", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsMdsePrcPk_PL", "dsMdsePrcPk_PL", "PL", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"specCondPrcPk_PL", "specCondPrcPk_PL", "PL", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"frtPerWtPk_PL", "frtPerWtPk_PL", "PL", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ordPrcCalcBasePk_PL", "ordPrcCalcBasePk_PL", "PL", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_COND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondTpCd_PL
        {"PRC_COND_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondTpDescTxt_PL
        {"PRC_DTL_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcDtlGrpCd_PL
        {"PRC_JRNL_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcJrnlGrpCd_PL
        {"PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd_PL
        {"PRC_COND_MAN_ENTRY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondManEntryFlg_PL
        {"PRC_COND_MAN_ADD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondManAddFlg_PL
        {"PRC_COND_MAN_DEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondManDelFlg_PL
        {"UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uomCd_PL
        {"PRC_COND_UNIT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCondUnitCd_PL
        {"PRC_CALC_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCalcMethCd_PL
        {"AUTO_PRC_CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoPrcCcyCd_PL
        {"AUTO_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoPrcAmtRate_PL
        {"MAX_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxPrcAmtRate_PL
        {"MIN_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minPrcAmtRate_PL
        {"MAN_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manPrcAmtRate_PL
        {"CALC_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//calcPrcAmtRate_PL
        {"UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unitPrcAmt_PL
        {"DS_MDSE_PRC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMdsePrcPk_PL
        {"SPEC_COND_PRC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//specCondPrcPk_PL
        {"FRT_PER_WT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtPerWtPk_PL
        {"ORD_PRC_CALC_BASE_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordPrcCalcBasePk_PL
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

