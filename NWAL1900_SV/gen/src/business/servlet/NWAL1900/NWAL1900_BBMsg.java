//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20181130181851000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1900_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1900;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1900 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1900_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_RULE_HDR_PK_B*/
	public final EZDBBigDecimalItem              prcRuleHdrPk_B;

    /** PRC_ADJ_DTL_PK_B*/
	public final EZDBBigDecimalItem              prcAdjDtlPk_B;

    /** PRC_RULE_APPLY_FLG_B*/
	public final EZDBStringItem              prcRuleApplyFlg_B;

    /** PRC_RULE_PRCD_PK_B*/
	public final EZDBBigDecimalItem              prcRulePrcdPk_B;


	/**
	 * NWAL1900_BBMsg is constructor.
	 * The initialization when the instance of NWAL1900_BBMsg is generated.
	 */
	public NWAL1900_BBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1900_BBMsg is constructor.
	 * The initialization when the instance of NWAL1900_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1900_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcRuleHdrPk_B = (EZDBBigDecimalItem)newItem("prcRuleHdrPk_B");
		prcAdjDtlPk_B = (EZDBBigDecimalItem)newItem("prcAdjDtlPk_B");
		prcRuleApplyFlg_B = (EZDBStringItem)newItem("prcRuleApplyFlg_B");
		prcRulePrcdPk_B = (EZDBBigDecimalItem)newItem("prcRulePrcdPk_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1900_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1900_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcRuleHdrPk_B", "prcRuleHdrPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcAdjDtlPk_B", "prcAdjDtlPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcRuleApplyFlg_B", "prcRuleApplyFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"prcRulePrcdPk_B", "prcRulePrcdPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_RULE_HDR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleHdrPk_B
        {"PRC_ADJ_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcAdjDtlPk_B
        {"PRC_RULE_APPLY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleApplyFlg_B
        {"PRC_RULE_PRCD_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRulePrcdPk_B
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

