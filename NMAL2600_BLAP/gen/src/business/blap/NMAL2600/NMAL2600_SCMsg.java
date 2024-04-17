//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170530171130000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2600_SCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2600;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2600 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2600_SCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TRTY_RULE_PK_S*/
	public final EZDCBigDecimalItem              trtyRulePk_S;

    /** XX_FULL_NM_S*/
	public final EZDCStringItem              xxFullNm_S;


	/**
	 * NMAL2600_SCMsg is constructor.
	 * The initialization when the instance of NMAL2600_SCMsg is generated.
	 */
	public NMAL2600_SCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2600_SCMsg is constructor.
	 * The initialization when the instance of NMAL2600_SCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2600_SCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		trtyRulePk_S = (EZDCBigDecimalItem)newItem("trtyRulePk_S");
		xxFullNm_S = (EZDCStringItem)newItem("xxFullNm_S");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2600_SCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2600_SCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"trtyRulePk_S", "trtyRulePk_S", "S", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxFullNm_S", "xxFullNm_S", "S", null, TYPE_HANKAKUEISU, "100", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TRTY_RULE_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRulePk_S
        {"XX_FULL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_S
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

