//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20090818184739000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL0020_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NYEL0020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NYEL0020 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NYEL0020_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDSStringItem              xxChkBox_A1;

    /** MENU_PROC_NM_A1*/
	public final EZDSStringItem              menuProcNm_A1;

    /** UP_TAB_NM_A1*/
	public final EZDSStringItem              upTabNm_A1;

    /** BIZ_APP_NM_A1*/
	public final EZDSStringItem              bizAppNm_A1;

    /** UP_TAB_CD_A1*/
	public final EZDSStringItem              upTabCd_A1;


	/**
	 * NYEL0020_ASMsg is constructor.
	 * The initialization when the instance of NYEL0020_ASMsg is generated.
	 */
	public NYEL0020_ASMsg() {
		this(false, -1);
	}

	/**
	 * NYEL0020_ASMsg is constructor.
	 * The initialization when the instance of NYEL0020_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYEL0020_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDSStringItem)newItem("xxChkBox_A1");
		menuProcNm_A1 = (EZDSStringItem)newItem("menuProcNm_A1");
		upTabNm_A1 = (EZDSStringItem)newItem("upTabNm_A1");
		bizAppNm_A1 = (EZDSStringItem)newItem("bizAppNm_A1");
		upTabCd_A1 = (EZDSStringItem)newItem("upTabCd_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NYEL0020_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYEL0020_ASMsgArray();
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

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"MENU_PROC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcNm_A1
        {"UP_TAB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upTabNm_A1
        {"BIZ_APP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAppNm_A1
        {"UP_TAB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upTabCd_A1
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

