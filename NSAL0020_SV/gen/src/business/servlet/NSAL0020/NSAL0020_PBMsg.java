//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20130910114008000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0020_PBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0020 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0020_PBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_SCR_QUERY_COL_NM_P6*/
	public final EZDBStringItem              xxComnScrQueryColNm_P6;

    /** XX_COMN_SCR_COL_VAL_TXT_P6*/
	public final EZDBStringItem              xxComnScrColValTxt_P6;

    /** XX_SEL_FLG_P6*/
	public final EZDBStringItem              xxSelFlg_P6;


	/**
	 * NSAL0020_PBMsg is constructor.
	 * The initialization when the instance of NSAL0020_PBMsg is generated.
	 */
	public NSAL0020_PBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0020_PBMsg is constructor.
	 * The initialization when the instance of NSAL0020_PBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0020_PBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnScrQueryColNm_P6 = (EZDBStringItem)newItem("xxComnScrQueryColNm_P6");
		xxComnScrColValTxt_P6 = (EZDBStringItem)newItem("xxComnScrColValTxt_P6");
		xxSelFlg_P6 = (EZDBStringItem)newItem("xxSelFlg_P6");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0020_PBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0020_PBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnScrQueryColNm_P6", "xxComnScrQueryColNm_P6", "P6", null, TYPE_HANKAKUEISU, "27", null},
	{"xxComnScrColValTxt_P6", "xxComnScrColValTxt_P6", "P6", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxSelFlg_P6", "xxSelFlg_P6", "P6", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm_P6
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_P6
        {"XX_SEL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelFlg_P6
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

