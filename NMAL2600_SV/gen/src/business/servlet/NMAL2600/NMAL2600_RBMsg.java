//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170530171150000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2600_RBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2600;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2600 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2600_RBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PSN_CD_R*/
	public final EZDBStringItem              psnCd_R;

    /** XX_FULL_NM_R*/
	public final EZDBStringItem              xxFullNm_R;


	/**
	 * NMAL2600_RBMsg is constructor.
	 * The initialization when the instance of NMAL2600_RBMsg is generated.
	 */
	public NMAL2600_RBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2600_RBMsg is constructor.
	 * The initialization when the instance of NMAL2600_RBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2600_RBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		psnCd_R = (EZDBStringItem)newItem("psnCd_R");
		xxFullNm_R = (EZDBStringItem)newItem("xxFullNm_R");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2600_RBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2600_RBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"psnCd_R", "psnCd_R", "R", null, TYPE_HANKAKUEISU, "8", null},
	{"xxFullNm_R", "xxFullNm_R", "R", null, TYPE_HANKAKUEISU, "100", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_R
        {"XX_FULL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_R
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
