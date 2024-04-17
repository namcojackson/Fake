//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20091028155011000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZOL0110_FBMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class ZZOL0110_FBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** BIZ_APP_ID_F1*/
	public final EZDBStringItem              bizAppId_F1;

    /** SYS_MENU_NM_F1*/
	public final EZDBStringItem              sysMenuNm_F1;

    /** SYS_USBLE_FLG_F1*/
	public final EZDBStringItem              sysUsbleFlg_F1;

    /** WF_APP_NM_F1*/
	public final EZDBStringItem              wfAppNm_F1;


	/**
	 * ZZOL0110_FBMsg is constructor.
	 * The initialization when the instance of ZZOL0110_FBMsg is generated.
	 */
	public ZZOL0110_FBMsg() {
		this(false, -1);
	}

	/**
	 * ZZOL0110_FBMsg is constructor.
	 * The initialization when the instance of ZZOL0110_FBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZOL0110_FBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		bizAppId_F1 = (EZDBStringItem)newItem("bizAppId_F1");
		sysMenuNm_F1 = (EZDBStringItem)newItem("sysMenuNm_F1");
		sysUsbleFlg_F1 = (EZDBStringItem)newItem("sysUsbleFlg_F1");
		wfAppNm_F1 = (EZDBStringItem)newItem("wfAppNm_F1");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZOL0110_FBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZOL0110_FBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"bizAppId_F1", "bizAppId_F1", "F1", null, TYPE_HANKAKUEISU, "8", null},
	{"sysMenuNm_F1", "sysMenuNm_F1", "F1", null, TYPE_HANKAKUEISU, "50", null},
	{"sysUsbleFlg_F1", "sysUsbleFlg_F1", "F1", null, TYPE_HANKAKUEISU, "1", null},
	{"wfAppNm_F1", "wfAppNm_F1", "F1", null, TYPE_HANKAKUEISU, "100", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"BIZ_APP_ID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAppId_F1
        {"SYS_MENU_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysMenuNm_F1
        {"SYS_USBLE_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysUsbleFlg_F1
        {"WF_APP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfAppNm_F1
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
