//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190514112626000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLAL1100_PCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLAL1100;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLAL1100 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLAL1100_PCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_POP_PRM*/
	public final EZDCStringItem              xxPopPrm;

    /** XX_COMN_SCR_COL_VAL_TXT*/
	public final EZDCStringItem              xxComnScrColValTxt;


	/**
	 * NLAL1100_PCMsg is constructor.
	 * The initialization when the instance of NLAL1100_PCMsg is generated.
	 */
	public NLAL1100_PCMsg() {
		this(false, -1);
	}

	/**
	 * NLAL1100_PCMsg is constructor.
	 * The initialization when the instance of NLAL1100_PCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLAL1100_PCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPopPrm = (EZDCStringItem)newItem("xxPopPrm");
		xxComnScrColValTxt = (EZDCStringItem)newItem("xxComnScrColValTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NLAL1100_PCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLAL1100_PCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxPopPrm", "xxPopPrm", null, null, TYPE_HANKAKUEISU, "300", null},
	{"xxComnScrColValTxt", "xxComnScrColValTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm
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

