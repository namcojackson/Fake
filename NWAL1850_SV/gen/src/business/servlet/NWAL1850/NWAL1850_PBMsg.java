//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20220603143922000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1850_PBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1850;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1850 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1850_PBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SEL_FLG_P*/
	public final EZDBStringItem              xxSelFlg_P;

    /** XX_COMN_SCR_COL_VAL_TXT_P*/
	public final EZDBStringItem              xxComnScrColValTxt_P;

    /** XX_POP_PRM_P*/
	public final EZDBStringItem              xxPopPrm_P;


	/**
	 * NWAL1850_PBMsg is constructor.
	 * The initialization when the instance of NWAL1850_PBMsg is generated.
	 */
	public NWAL1850_PBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1850_PBMsg is constructor.
	 * The initialization when the instance of NWAL1850_PBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1850_PBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxSelFlg_P = (EZDBStringItem)newItem("xxSelFlg_P");
		xxComnScrColValTxt_P = (EZDBStringItem)newItem("xxComnScrColValTxt_P");
		xxPopPrm_P = (EZDBStringItem)newItem("xxPopPrm_P");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1850_PBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1850_PBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxSelFlg_P", "xxSelFlg_P", "P", null, TYPE_HANKAKUEISU, "1", null},
	{"xxComnScrColValTxt_P", "xxComnScrColValTxt_P", "P", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxPopPrm_P", "xxPopPrm_P", "P", null, TYPE_HANKAKUEISU, "300", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SEL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelFlg_P
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_P
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P
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

