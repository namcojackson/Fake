//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20221129134643000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL2760_EBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFCL2760;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL2760 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL2760_EBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_NUM*/
	public final EZDBBigDecimalItem              xxRowNum;


	/**
	 * NFCL2760_EBMsg is constructor.
	 * The initialization when the instance of NFCL2760_EBMsg is generated.
	 */
	public NFCL2760_EBMsg() {
		this(false, -1);
	}

	/**
	 * NFCL2760_EBMsg is constructor.
	 * The initialization when the instance of NFCL2760_EBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL2760_EBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowNum = (EZDBBigDecimalItem)newItem("xxRowNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL2760_EBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL2760_EBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRowNum", "xxRowNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum
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

