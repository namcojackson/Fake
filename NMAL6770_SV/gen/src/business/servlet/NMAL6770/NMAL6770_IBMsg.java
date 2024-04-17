//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180228112001000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6770_IBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6770;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6770 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6770_IBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_DPLY_CTRL_FLG_I1*/
	public final EZDBStringItem              xxDplyCtrlFlg_I1;


	/**
	 * NMAL6770_IBMsg is constructor.
	 * The initialization when the instance of NMAL6770_IBMsg is generated.
	 */
	public NMAL6770_IBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6770_IBMsg is constructor.
	 * The initialization when the instance of NMAL6770_IBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6770_IBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxDplyCtrlFlg_I1 = (EZDBStringItem)newItem("xxDplyCtrlFlg_I1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6770_IBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6770_IBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxDplyCtrlFlg_I1", "xxDplyCtrlFlg_I1", "I1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_DPLY_CTRL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_I1
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

