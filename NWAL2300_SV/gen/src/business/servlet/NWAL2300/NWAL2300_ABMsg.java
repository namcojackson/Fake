//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180411185553000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2300_ABMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NWAL2300_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDBStringItem              xxChkBox_A1;

    /** CR_REBIL_RSN_CD_A1*/
	public final EZDBStringItem              crRebilRsnCd_A1;

    /** CR_REBIL_RSN_NM_A1*/
	public final EZDBStringItem              crRebilRsnNm_A1;


	/**
	 * NWAL2300_ABMsg is constructor.
	 * The initialization when the instance of NWAL2300_ABMsg is generated.
	 */
	public NWAL2300_ABMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2300_ABMsg is constructor.
	 * The initialization when the instance of NWAL2300_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2300_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDBStringItem)newItem("xxChkBox_A1");
		crRebilRsnCd_A1 = (EZDBStringItem)newItem("crRebilRsnCd_A1");
		crRebilRsnNm_A1 = (EZDBStringItem)newItem("crRebilRsnNm_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2300_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2300_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"crRebilRsnCd_A1", "crRebilRsnCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"crRebilRsnNm_A1", "crRebilRsnNm_A1", "A1", null, TYPE_HANKAKUEISU, "100", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"CR_REBIL_RSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilRsnCd_A1
        {"CR_REBIL_RSN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilRsnNm_A1
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

