//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161221104727000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZVL0020_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZVL0020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZVL0020 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZVL0020_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDBStringItem              xxChkBox_A1;

    /** MENU_PROC_NM_A1*/
	public final EZDBStringItem              menuProcNm_A1;

    /** UP_TAB_NM_A1*/
	public final EZDBStringItem              upTabNm_A1;

    /** BIZ_APP_NM_A1*/
	public final EZDBStringItem              bizAppNm_A1;

    /** UP_TAB_CD_A1*/
	public final EZDBStringItem              upTabCd_A1;


	/**
	 * ZZVL0020_ABMsg is constructor.
	 * The initialization when the instance of ZZVL0020_ABMsg is generated.
	 */
	public ZZVL0020_ABMsg() {
		this(false, -1);
	}

	/**
	 * ZZVL0020_ABMsg is constructor.
	 * The initialization when the instance of ZZVL0020_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZVL0020_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDBStringItem)newItem("xxChkBox_A1");
		menuProcNm_A1 = (EZDBStringItem)newItem("menuProcNm_A1");
		upTabNm_A1 = (EZDBStringItem)newItem("upTabNm_A1");
		bizAppNm_A1 = (EZDBStringItem)newItem("bizAppNm_A1");
		upTabCd_A1 = (EZDBStringItem)newItem("upTabCd_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZVL0020_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZVL0020_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"menuProcNm_A1", "menuProcNm_A1", "A1", null, TYPE_HANKAKUEISU, "100", null},
	{"upTabNm_A1", "upTabNm_A1", "A1", null, TYPE_HANKAKUEISU, "12", null},
	{"bizAppNm_A1", "bizAppNm_A1", "A1", null, TYPE_HANKAKUEISU, "100", null},
	{"upTabCd_A1", "upTabCd_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"MENU_PROC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcNm_A1
        {"UP_TAB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upTabNm_A1
        {"BIZ_APP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAppNm_A1
        {"UP_TAB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upTabCd_A1
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
