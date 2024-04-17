//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170531092317000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2700_ZBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2700;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2700 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2700_ZBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZUpdateDateTime_Z*/
	public final EZDBStringItem              ezUpTime_Z;

    /** _EZUpTimeZone_Z*/
	public final EZDBStringItem              ezUpTimeZone_Z;

    /** ORG_FUNC_ROLE_TP_CD_Z*/
	public final EZDBStringItem              orgFuncRoleTpCd_Z;


	/**
	 * NMAL2700_ZBMsg is constructor.
	 * The initialization when the instance of NMAL2700_ZBMsg is generated.
	 */
	public NMAL2700_ZBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2700_ZBMsg is constructor.
	 * The initialization when the instance of NMAL2700_ZBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2700_ZBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezUpTime_Z = (EZDBStringItem)newItem("ezUpTime_Z");
		ezUpTimeZone_Z = (EZDBStringItem)newItem("ezUpTimeZone_Z");
		orgFuncRoleTpCd_Z = (EZDBStringItem)newItem("orgFuncRoleTpCd_Z");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2700_ZBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2700_ZBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ezUpTime_Z", "ezUpTime_Z", "Z", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_Z", "ezUpTimeZone_Z", "Z", null, TYPE_HANKAKUEISU, "5", null},
	{"orgFuncRoleTpCd_Z", "orgFuncRoleTpCd_Z", "Z", null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_Z
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_Z
        {"ORG_FUNC_ROLE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpCd_Z
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
