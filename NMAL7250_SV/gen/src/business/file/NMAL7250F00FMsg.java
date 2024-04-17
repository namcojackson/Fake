//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20151216151531000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7250F00FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7250F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7250F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX*/
	public final EZDFStringItem              xxChkBox;

    /** PRC_RULE_HDR_PK*/
	public final EZDFBigDecimalItem              prcRuleHdrPk;

    /** PRC_RULE_NM*/
	public final EZDFStringItem              prcRuleNm;

    /** PRC_RULE_COND_TP_DESC_TXT*/
	public final EZDFStringItem              prcRuleCondTpDescTxt;

    /** DS_BIZ_LINE_DESC_TXT*/
	public final EZDFStringItem              dsBizLineDescTxt;

    /** PRC_RULE_CATG_DESC_TXT*/
	public final EZDFStringItem              prcRuleCatgDescTxt;

    /** XX_FLG_NM_AP*/
	public final EZDFStringItem              xxFlgNm_AP;

    /** XX_FLG_NM_OV*/
	public final EZDFStringItem              xxFlgNm_OV;

    /** PRC_DISP_REC_TP_DESC_TXT*/
	public final EZDFStringItem              prcDispRecTpDescTxt;

    /** DEF_RULE_PRCD_NUM*/
	public final EZDFBigDecimalItem              defRulePrcdNum;

    /** EFF_FROM_DT*/
	public final EZDFDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDFDateItem              effThruDt;


	/**
	 * NMAL7250F00FMsg is constructor.
	 * The initialization when the instance of NMAL7250F00FMsg is generated.
	 */
	public NMAL7250F00FMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7250F00FMsg is constructor.
	 * The initialization when the instance of NMAL7250F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7250F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox = (EZDFStringItem)newItem("xxChkBox");
		prcRuleHdrPk = (EZDFBigDecimalItem)newItem("prcRuleHdrPk");
		prcRuleNm = (EZDFStringItem)newItem("prcRuleNm");
		prcRuleCondTpDescTxt = (EZDFStringItem)newItem("prcRuleCondTpDescTxt");
		dsBizLineDescTxt = (EZDFStringItem)newItem("dsBizLineDescTxt");
		prcRuleCatgDescTxt = (EZDFStringItem)newItem("prcRuleCatgDescTxt");
		xxFlgNm_AP = (EZDFStringItem)newItem("xxFlgNm_AP");
		xxFlgNm_OV = (EZDFStringItem)newItem("xxFlgNm_OV");
		prcDispRecTpDescTxt = (EZDFStringItem)newItem("prcDispRecTpDescTxt");
		defRulePrcdNum = (EZDFBigDecimalItem)newItem("defRulePrcdNum");
		effFromDt = (EZDFDateItem)newItem("effFromDt");
		effThruDt = (EZDFDateItem)newItem("effThruDt");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7250F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7250F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox", "xxChkBox", null, null, TYPE_HANKAKUEIJI, "1", null},
	{"prcRuleHdrPk", "prcRuleHdrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcRuleNm", "prcRuleNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcRuleCondTpDescTxt", "prcRuleCondTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsBizLineDescTxt", "dsBizLineDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcRuleCatgDescTxt", "prcRuleCatgDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxFlgNm_AP", "xxFlgNm_AP", "AP", null, TYPE_HANKAKUEISU, "4", null},
	{"xxFlgNm_OV", "xxFlgNm_OV", "OV", null, TYPE_HANKAKUEISU, "4", null},
	{"prcDispRecTpDescTxt", "prcDispRecTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"defRulePrcdNum", "defRulePrcdNum", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"PRC_RULE_HDR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleHdrPk
        {"PRC_RULE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleNm
        {"PRC_RULE_COND_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCondTpDescTxt
        {"DS_BIZ_LINE_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBizLineDescTxt
        {"PRC_RULE_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCatgDescTxt
        {"XX_FLG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFlgNm_AP
        {"XX_FLG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFlgNm_OV
        {"PRC_DISP_REC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcDispRecTpDescTxt
        {"DEF_RULE_PRCD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//defRulePrcdNum
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
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

