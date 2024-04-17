//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20091028155011000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZOL0110_EBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZOL0110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZOL0110 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZOL0110_EBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** BIZ_APP_ID_E1*/
	public final EZDBStringItem              bizAppId_E1;

    /** SYS_MENU_NM_E1*/
	public final EZDBStringItem              sysMenuNm_E1;

    /** SYS_USBLE_FLG_E1*/
	public final EZDBStringItem              sysUsbleFlg_E1;

    /** WF_APP_NM_E1*/
	public final EZDBStringItem              wfAppNm_E1;


	/**
	 * ZZOL0110_EBMsg is constructor.
	 * The initialization when the instance of ZZOL0110_EBMsg is generated.
	 */
	public ZZOL0110_EBMsg() {
		this(false, -1);
	}

	/**
	 * ZZOL0110_EBMsg is constructor.
	 * The initialization when the instance of ZZOL0110_EBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZOL0110_EBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		bizAppId_E1 = (EZDBStringItem)newItem("bizAppId_E1");
		sysMenuNm_E1 = (EZDBStringItem)newItem("sysMenuNm_E1");
		sysUsbleFlg_E1 = (EZDBStringItem)newItem("sysUsbleFlg_E1");
		wfAppNm_E1 = (EZDBStringItem)newItem("wfAppNm_E1");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZOL0110_EBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZOL0110_EBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"bizAppId_E1", "bizAppId_E1", "E1", null, TYPE_HANKAKUEISU, "8", null},
	{"sysMenuNm_E1", "sysMenuNm_E1", "E1", null, TYPE_HANKAKUEISU, "50", null},
	{"sysUsbleFlg_E1", "sysUsbleFlg_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"wfAppNm_E1", "wfAppNm_E1", "E1", null, TYPE_HANKAKUEISU, "100", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"BIZ_APP_ID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAppId_E1
        {"SYS_MENU_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysMenuNm_E1
        {"SYS_USBLE_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysUsbleFlg_E1
        {"WF_APP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfAppNm_E1
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
