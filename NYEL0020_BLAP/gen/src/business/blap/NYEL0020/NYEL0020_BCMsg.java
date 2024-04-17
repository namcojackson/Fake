//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20090818184737000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL0020_BCMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NYEL0020 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NYEL0020_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B1*/
	public final EZDCStringItem              xxChkBox_B1;

    /** MENU_PROC_NM_B1*/
	public final EZDCStringItem              menuProcNm_B1;

    /** UP_TAB_NM_B1*/
	public final EZDCStringItem              upTabNm_B1;

    /** BIZ_APP_NM_B1*/
	public final EZDCStringItem              bizAppNm_B1;

    /** UP_TAB_CD_B1*/
	public final EZDCStringItem              upTabCd_B1;

    /** XX_ERR_FLG_B1*/
	public final EZDCStringItem              xxErrFlg_B1;


	/**
	 * NYEL0020_BCMsg is constructor.
	 * The initialization when the instance of NYEL0020_BCMsg is generated.
	 */
	public NYEL0020_BCMsg() {
		this(false, -1);
	}

	/**
	 * NYEL0020_BCMsg is constructor.
	 * The initialization when the instance of NYEL0020_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYEL0020_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B1 = (EZDCStringItem)newItem("xxChkBox_B1");
		menuProcNm_B1 = (EZDCStringItem)newItem("menuProcNm_B1");
		upTabNm_B1 = (EZDCStringItem)newItem("upTabNm_B1");
		bizAppNm_B1 = (EZDCStringItem)newItem("bizAppNm_B1");
		upTabCd_B1 = (EZDCStringItem)newItem("upTabCd_B1");
		xxErrFlg_B1 = (EZDCStringItem)newItem("xxErrFlg_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NYEL0020_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYEL0020_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_B1", "xxChkBox_B1", "B1", null, TYPE_HANKAKUEIJI, "1", null},
	{"menuProcNm_B1", "menuProcNm_B1", "B1", null, TYPE_HANKAKUEISU, "100", null},
	{"upTabNm_B1", "upTabNm_B1", "B1", null, TYPE_HANKAKUEISU, "12", null},
	{"bizAppNm_B1", "bizAppNm_B1", "B1", null, TYPE_HANKAKUEISU, "100", null},
	{"upTabCd_B1", "upTabCd_B1", "B1", null, TYPE_HANKAKUEISU, "10", null},
	{"xxErrFlg_B1", "xxErrFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"MENU_PROC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcNm_B1
        {"UP_TAB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upTabNm_B1
        {"BIZ_APP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAppNm_B1
        {"UP_TAB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upTabCd_B1
        {"XX_ERR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxErrFlg_B1
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

