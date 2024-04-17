//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170823135827000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1390_ZBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1390;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1390 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1390_ZBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_SCR_QUERY_COL_NM_Z*/
	public final EZDBStringItem              xxComnScrQueryColNm_Z;

    /** XX_COMN_SCR_COL_VAL_LG_Z*/
	public final EZDBBigDecimalItem              xxComnScrColValLg_Z;

    /** XX_SEL_FLG_Z*/
	public final EZDBStringItem              xxSelFlg_Z;


	/**
	 * NSAL1390_ZBMsg is constructor.
	 * The initialization when the instance of NSAL1390_ZBMsg is generated.
	 */
	public NSAL1390_ZBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1390_ZBMsg is constructor.
	 * The initialization when the instance of NSAL1390_ZBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1390_ZBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnScrQueryColNm_Z = (EZDBStringItem)newItem("xxComnScrQueryColNm_Z");
		xxComnScrColValLg_Z = (EZDBBigDecimalItem)newItem("xxComnScrColValLg_Z");
		xxSelFlg_Z = (EZDBStringItem)newItem("xxSelFlg_Z");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1390_ZBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1390_ZBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnScrQueryColNm_Z", "xxComnScrQueryColNm_Z", "Z", null, TYPE_HANKAKUEISU, "27", null},
	{"xxComnScrColValLg_Z", "xxComnScrColValLg_Z", "Z", null, TYPE_SEISU_SYOSU, "10", "4"},
	{"xxSelFlg_Z", "xxSelFlg_Z", "Z", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm_Z
        {"XX_COMN_SCR_COL_VAL_LG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValLg_Z
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

