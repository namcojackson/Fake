//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161110092236000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZVL0010_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZVL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZVL0010 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZVL0010_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ROLE_NM_2*/
	public final EZDBStringItem              roleNm_2;

    /** ROLE_DESC_TXT_2*/
	public final EZDBStringItem              roleDescTxt_2;


	/**
	 * ZZVL0010_ABMsg is constructor.
	 * The initialization when the instance of ZZVL0010_ABMsg is generated.
	 */
	public ZZVL0010_ABMsg() {
		this(false, -1);
	}

	/**
	 * ZZVL0010_ABMsg is constructor.
	 * The initialization when the instance of ZZVL0010_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZVL0010_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		roleNm_2 = (EZDBStringItem)newItem("roleNm_2");
		roleDescTxt_2 = (EZDBStringItem)newItem("roleDescTxt_2");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZVL0010_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZVL0010_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"roleNm_2", "roleNm_2", "2", null, TYPE_HANKAKUEISU, "24", null},
	{"roleDescTxt_2", "roleDescTxt_2", "2", null, TYPE_HANKAKUEISU, "100", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ROLE_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//roleNm_2
        {"ROLE_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//roleDescTxt_2
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

