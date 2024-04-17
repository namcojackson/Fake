//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160615190823000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2300_YBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2300;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2300 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2300_YBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_Y1*/
	public final EZDBStringItem              xxChkBox_Y1;

    /** CR_REBIL_RSN_CD_Y1*/
	public final EZDBStringItem              crRebilRsnCd_Y1;


	/**
	 * NWAL2300_YBMsg is constructor.
	 * The initialization when the instance of NWAL2300_YBMsg is generated.
	 */
	public NWAL2300_YBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2300_YBMsg is constructor.
	 * The initialization when the instance of NWAL2300_YBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2300_YBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_Y1 = (EZDBStringItem)newItem("xxChkBox_Y1");
		crRebilRsnCd_Y1 = (EZDBStringItem)newItem("crRebilRsnCd_Y1");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2300_YBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2300_YBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_Y1", "xxChkBox_Y1", "Y1", null, TYPE_HANKAKUEIJI, "1", null},
	{"crRebilRsnCd_Y1", "crRebilRsnCd_Y1", "Y1", null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_Y1
        {"CR_REBIL_RSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilRsnCd_Y1
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
