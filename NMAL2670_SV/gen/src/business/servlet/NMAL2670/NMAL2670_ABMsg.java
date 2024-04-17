//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160309143126000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2670_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2670;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2670 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2670_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TRTY_RULE_PK_A1*/
	public final EZDBBigDecimalItem              trtyRulePk_A1;

    /** XX_DS_MSG_ENTRY_TXT_A1*/
	public final EZDBStringItem              xxDsMsgEntryTxt_A1;


	/**
	 * NMAL2670_ABMsg is constructor.
	 * The initialization when the instance of NMAL2670_ABMsg is generated.
	 */
	public NMAL2670_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2670_ABMsg is constructor.
	 * The initialization when the instance of NMAL2670_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2670_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		trtyRulePk_A1 = (EZDBBigDecimalItem)newItem("trtyRulePk_A1");
		xxDsMsgEntryTxt_A1 = (EZDBStringItem)newItem("xxDsMsgEntryTxt_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2670_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2670_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"trtyRulePk_A1", "trtyRulePk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxDsMsgEntryTxt_A1", "xxDsMsgEntryTxt_A1", "A1", null, TYPE_HANKAKUEISU, "1000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TRTY_RULE_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRulePk_A1
        {"XX_DS_MSG_ENTRY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMsgEntryTxt_A1
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

