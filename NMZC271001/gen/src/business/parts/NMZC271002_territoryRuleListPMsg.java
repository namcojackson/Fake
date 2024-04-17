//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20160222213237000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMZC271002_territoryRuleListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMZC271002 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMZC271002_territoryRuleListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TRTY_RULE_PK*/
	public final EZDPBigDecimalItem              trtyRulePk;


	/**
	 * NMZC271002_territoryRuleListPMsg is constructor.
	 * The initialization when the instance of NMZC271002_territoryRuleListPMsg is generated.
	 */
	public NMZC271002_territoryRuleListPMsg() {
		this(false, -1);
	}

	/**
	 * NMZC271002_territoryRuleListPMsg is constructor.
	 * The initialization when the instance of NMZC271002_territoryRuleListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMZC271002_territoryRuleListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		trtyRulePk = (EZDPBigDecimalItem)newItem("trtyRulePk");
	}

	/**
	 * get the type of array which is stored
	 * @return NMZC271002_territoryRuleListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMZC271002_territoryRuleListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"trtyRulePk", "trtyRulePk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TRTY_RULE_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRulePk
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

