//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20150317190809000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1090_QBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1090;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1090 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1090_QBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_SCR_COL_LB_TXT_C*/
	public final EZDBStringItem              xxComnScrColLbTxt_C;

    /** XX_COMN_SCR_QUERY_COL_NM_C*/
	public final EZDBStringItem              xxComnScrQueryColNm_C;

    /** XX_COMN_SCR_COL_LG_C*/
	public final EZDBBigDecimalItem              xxComnScrColLg_C;

    /** XX_SEL_FLG_C*/
	public final EZDBStringItem              xxSelFlg_C;


	/**
	 * NPAL1090_QBMsg is constructor.
	 * The initialization when the instance of NPAL1090_QBMsg is generated.
	 */
	public NPAL1090_QBMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1090_QBMsg is constructor.
	 * The initialization when the instance of NPAL1090_QBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1090_QBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnScrColLbTxt_C = (EZDBStringItem)newItem("xxComnScrColLbTxt_C");
		xxComnScrQueryColNm_C = (EZDBStringItem)newItem("xxComnScrQueryColNm_C");
		xxComnScrColLg_C = (EZDBBigDecimalItem)newItem("xxComnScrColLg_C");
		xxSelFlg_C = (EZDBStringItem)newItem("xxSelFlg_C");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1090_QBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1090_QBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnScrColLbTxt_C", "xxComnScrColLbTxt_C", "C", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrQueryColNm_C", "xxComnScrQueryColNm_C", "C", null, TYPE_HANKAKUEISU, "27", null},
	{"xxComnScrColLg_C", "xxComnScrColLg_C", "C", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"xxSelFlg_C", "xxSelFlg_C", "C", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_C
        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm_C
        {"XX_COMN_SCR_COL_LG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLg_C
        {"XX_SEL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelFlg_C
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

