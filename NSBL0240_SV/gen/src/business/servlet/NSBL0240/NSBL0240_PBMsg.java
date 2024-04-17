//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170412110717000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0240_PBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSBL0240;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0240 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0240_PBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_SCR_COL_VAL_TXT*/
	public final EZDBStringItem              xxComnScrColValTxt;

    /** XX_COMN_SCR_QUERY_COL_NM*/
	public final EZDBStringItem              xxComnScrQueryColNm;

    /** XX_SEL_FLG*/
	public final EZDBStringItem              xxSelFlg;


	/**
	 * NSBL0240_PBMsg is constructor.
	 * The initialization when the instance of NSBL0240_PBMsg is generated.
	 */
	public NSBL0240_PBMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0240_PBMsg is constructor.
	 * The initialization when the instance of NSBL0240_PBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0240_PBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnScrColValTxt = (EZDBStringItem)newItem("xxComnScrColValTxt");
		xxComnScrQueryColNm = (EZDBStringItem)newItem("xxComnScrQueryColNm");
		xxSelFlg = (EZDBStringItem)newItem("xxSelFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0240_PBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0240_PBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnScrColValTxt", "xxComnScrColValTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrQueryColNm", "xxComnScrQueryColNm", null, null, TYPE_HANKAKUEISU, "27", null},
	{"xxSelFlg", "xxSelFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt
        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm
        {"XX_SEL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelFlg
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

