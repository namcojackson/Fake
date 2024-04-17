//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170530175654000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0160_CBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSBL0160;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0160 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0160_CBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_SCR_COL_LB_TXT_CL*/
	public final EZDBStringItem              xxComnScrColLbTxt_CL;

    /** XX_COMN_SCR_COL_LB_TXT_SC*/
	public final EZDBStringItem              xxComnScrColLbTxt_SC;


	/**
	 * NSBL0160_CBMsg is constructor.
	 * The initialization when the instance of NSBL0160_CBMsg is generated.
	 */
	public NSBL0160_CBMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0160_CBMsg is constructor.
	 * The initialization when the instance of NSBL0160_CBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0160_CBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnScrColLbTxt_CL = (EZDBStringItem)newItem("xxComnScrColLbTxt_CL");
		xxComnScrColLbTxt_SC = (EZDBStringItem)newItem("xxComnScrColLbTxt_SC");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0160_CBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0160_CBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnScrColLbTxt_CL", "xxComnScrColLbTxt_CL", "CL", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_SC", "xxComnScrColLbTxt_SC", "SC", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_CL
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_SC
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
