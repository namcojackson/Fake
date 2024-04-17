//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20181128185754000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1900_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1900;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1900 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1900_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_RULE_HDR_PK_A*/
	public final EZDSBigDecimalItem              prcRuleHdrPk_A;

    /** PRC_RULE_NM_A*/
	public final EZDSStringItem              prcRuleNm_A;

    /** PRC_RULE_COND_TP_CD_A*/
	public final EZDSStringItem              prcRuleCondTpCd_A;

    /** PRC_RULE_COND_TP_NM_A*/
	public final EZDSStringItem              prcRuleCondTpNm_A;

    /** XX_CMNT_TXT_A1*/
	public final EZDSStringItem              xxCmntTxt_A1;

    /** XX_CMNT_TXT_A2*/
	public final EZDSStringItem              xxCmntTxt_A2;

    /** DEF_RULE_PRCD_NUM_A*/
	public final EZDSBigDecimalItem              defRulePrcdNum_A;

    /** EFF_FROM_DT_A*/
	public final EZDSDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDSDateItem              effThruDt_A;

    /** XX_CMNT_TXT_A3*/
	public final EZDSStringItem              xxCmntTxt_A3;

    /** PRC_RULE_PRCD_PK_A*/
	public final EZDSBigDecimalItem              prcRulePrcdPk_A;

    /** PRC_PRCD_ACT_TP_NM_A*/
	public final EZDSStringItem              prcPrcdActTpNm_A;

    /** PRC_ADJ_DTL_PK_A*/
	public final EZDSBigDecimalItem              prcAdjDtlPk_A;

    /** PRC_RULE_DTL_PK_A*/
	public final EZDSBigDecimalItem              prcRuleDtlPk_A;


	/**
	 * NWAL1900_ASMsg is constructor.
	 * The initialization when the instance of NWAL1900_ASMsg is generated.
	 */
	public NWAL1900_ASMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1900_ASMsg is constructor.
	 * The initialization when the instance of NWAL1900_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1900_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcRuleHdrPk_A = (EZDSBigDecimalItem)newItem("prcRuleHdrPk_A");
		prcRuleNm_A = (EZDSStringItem)newItem("prcRuleNm_A");
		prcRuleCondTpCd_A = (EZDSStringItem)newItem("prcRuleCondTpCd_A");
		prcRuleCondTpNm_A = (EZDSStringItem)newItem("prcRuleCondTpNm_A");
		xxCmntTxt_A1 = (EZDSStringItem)newItem("xxCmntTxt_A1");
		xxCmntTxt_A2 = (EZDSStringItem)newItem("xxCmntTxt_A2");
		defRulePrcdNum_A = (EZDSBigDecimalItem)newItem("defRulePrcdNum_A");
		effFromDt_A = (EZDSDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDSDateItem)newItem("effThruDt_A");
		xxCmntTxt_A3 = (EZDSStringItem)newItem("xxCmntTxt_A3");
		prcRulePrcdPk_A = (EZDSBigDecimalItem)newItem("prcRulePrcdPk_A");
		prcPrcdActTpNm_A = (EZDSStringItem)newItem("prcPrcdActTpNm_A");
		prcAdjDtlPk_A = (EZDSBigDecimalItem)newItem("prcAdjDtlPk_A");
		prcRuleDtlPk_A = (EZDSBigDecimalItem)newItem("prcRuleDtlPk_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1900_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1900_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcRuleHdrPk_A", "prcRuleHdrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcRuleNm_A", "prcRuleNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"prcRuleCondTpCd_A", "prcRuleCondTpCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"prcRuleCondTpNm_A", "prcRuleCondTpNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"xxCmntTxt_A1", "xxCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"xxCmntTxt_A2", "xxCmntTxt_A2", "A2", null, TYPE_HANKAKUEISU, "60", null},
	{"defRulePrcdNum_A", "defRulePrcdNum_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"xxCmntTxt_A3", "xxCmntTxt_A3", "A3", null, TYPE_HANKAKUEISU, "60", null},
	{"prcRulePrcdPk_A", "prcRulePrcdPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcPrcdActTpNm_A", "prcPrcdActTpNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"prcAdjDtlPk_A", "prcAdjDtlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcRuleDtlPk_A", "prcRuleDtlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_RULE_HDR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleHdrPk_A
        {"PRC_RULE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleNm_A
        {"PRC_RULE_COND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCondTpCd_A
        {"PRC_RULE_COND_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCondTpNm_A
        {"XX_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCmntTxt_A1
        {"XX_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCmntTxt_A2
        {"DEF_RULE_PRCD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//defRulePrcdNum_A
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A
        {"XX_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCmntTxt_A3
        {"PRC_RULE_PRCD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRulePrcdPk_A
        {"PRC_PRCD_ACT_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcPrcdActTpNm_A
        {"PRC_ADJ_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcAdjDtlPk_A
        {"PRC_RULE_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleDtlPk_A
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
