//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20200229105712000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6860_ZBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6860;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6860 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6860_ZBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_SCR_QUERY_COL_NM_Z*/
	public final EZDBStringItem              xxComnScrQueryColNm_Z;

    /** XX_COMN_SCR_COL_VAL_TXT_Z*/
	public final EZDBStringItem              xxComnScrColValTxt_Z;

    /** XX_SEL_FLG_Z*/
	public final EZDBStringItem              xxSelFlg_Z;


	/**
	 * NMAL6860_ZBMsg is constructor.
	 * The initialization when the instance of NMAL6860_ZBMsg is generated.
	 */
	public NMAL6860_ZBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6860_ZBMsg is constructor.
	 * The initialization when the instance of NMAL6860_ZBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6860_ZBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnScrQueryColNm_Z = (EZDBStringItem)newItem("xxComnScrQueryColNm_Z");
		xxComnScrColValTxt_Z = (EZDBStringItem)newItem("xxComnScrColValTxt_Z");
		xxSelFlg_Z = (EZDBStringItem)newItem("xxSelFlg_Z");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6860_ZBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6860_ZBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnScrQueryColNm_Z", "xxComnScrQueryColNm_Z", "Z", null, TYPE_HANKAKUEISU, "27", null},
	{"xxComnScrColValTxt_Z", "xxComnScrColValTxt_Z", "Z", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxSelFlg_Z", "xxSelFlg_Z", "Z", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm_Z
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_Z
        {"XX_SEL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelFlg_Z
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
