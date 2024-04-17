//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20190118170853000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0730_CSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0730;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0730 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0730_CSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_SCR_COL_VAL_TXT*/
	public final EZDSStringItem              xxComnScrColValTxt;


	/**
	 * NSAL0730_CSMsg is constructor.
	 * The initialization when the instance of NSAL0730_CSMsg is generated.
	 */
	public NSAL0730_CSMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0730_CSMsg is constructor.
	 * The initialization when the instance of NSAL0730_CSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0730_CSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnScrColValTxt = (EZDSStringItem)newItem("xxComnScrColValTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0730_CSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0730_CSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnScrColValTxt", "xxComnScrColValTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt
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

