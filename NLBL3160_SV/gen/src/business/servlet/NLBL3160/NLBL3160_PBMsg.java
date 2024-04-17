//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20221107114339000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3160_PBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL3160;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3160 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3160_PBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_POP_PRM*/
	public final EZDBStringItem              xxPopPrm;

    /** XX_COMN_SCR_COL_VAL_TXT*/
	public final EZDBStringItem              xxComnScrColValTxt;


	/**
	 * NLBL3160_PBMsg is constructor.
	 * The initialization when the instance of NLBL3160_PBMsg is generated.
	 */
	public NLBL3160_PBMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3160_PBMsg is constructor.
	 * The initialization when the instance of NLBL3160_PBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3160_PBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPopPrm = (EZDBStringItem)newItem("xxPopPrm");
		xxComnScrColValTxt = (EZDBStringItem)newItem("xxComnScrColValTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3160_PBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3160_PBMsgArray();
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

        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt
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

