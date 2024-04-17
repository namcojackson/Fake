//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170529183122000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0140_OCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0140;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0140 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0140_OCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_SCR_QUERY_COL_NM_O1*/
	public final EZDCStringItem              xxComnScrQueryColNm_O1;

    /** XX_COMN_SCR_COL_VAL_TXT_O1*/
	public final EZDCStringItem              xxComnScrColValTxt_O1;

    /** XX_SEL_FLG_O1*/
	public final EZDCStringItem              xxSelFlg_O1;


	/**
	 * NMAL0140_OCMsg is constructor.
	 * The initialization when the instance of NMAL0140_OCMsg is generated.
	 */
	public NMAL0140_OCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0140_OCMsg is constructor.
	 * The initialization when the instance of NMAL0140_OCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0140_OCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnScrQueryColNm_O1 = (EZDCStringItem)newItem("xxComnScrQueryColNm_O1");
		xxComnScrColValTxt_O1 = (EZDCStringItem)newItem("xxComnScrColValTxt_O1");
		xxSelFlg_O1 = (EZDCStringItem)newItem("xxSelFlg_O1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0140_OCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0140_OCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnScrQueryColNm_O1", "xxComnScrQueryColNm_O1", "O1", null, TYPE_HANKAKUEISU, "27", null},
	{"xxComnScrColValTxt_O1", "xxComnScrColValTxt_O1", "O1", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxSelFlg_O1", "xxSelFlg_O1", "O1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm_O1
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_O1
        {"XX_SEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelFlg_O1
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

