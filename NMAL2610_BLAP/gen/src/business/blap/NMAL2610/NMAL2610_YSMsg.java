//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20171121171621000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2610_YSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2610;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2610 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2610_YSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TRTY_RULE_PK_R2*/
	public final EZDSBigDecimalItem              trtyRulePk_R2;


	/**
	 * NMAL2610_YSMsg is constructor.
	 * The initialization when the instance of NMAL2610_YSMsg is generated.
	 */
	public NMAL2610_YSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2610_YSMsg is constructor.
	 * The initialization when the instance of NMAL2610_YSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2610_YSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		trtyRulePk_R2 = (EZDSBigDecimalItem)newItem("trtyRulePk_R2");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2610_YSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2610_YSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"trtyRulePk_R2", "trtyRulePk_R2", "R2", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TRTY_RULE_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRulePk_R2
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
