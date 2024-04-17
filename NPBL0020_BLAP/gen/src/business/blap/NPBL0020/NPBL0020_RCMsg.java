//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230703090332000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPBL0020_RCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPBL0020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPBL0020 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPBL0020_RCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_SCR_QUERY_COL_NM*/
	public final EZDCStringItem              xxComnScrQueryColNm;

    /** XX_COMN_SCR_COL_VAL_TXT*/
	public final EZDCStringItem              xxComnScrColValTxt;

    /** XX_SEL_FLG*/
	public final EZDCStringItem              xxSelFlg;


	/**
	 * NPBL0020_RCMsg is constructor.
	 * The initialization when the instance of NPBL0020_RCMsg is generated.
	 */
	public NPBL0020_RCMsg() {
		this(false, -1);
	}

	/**
	 * NPBL0020_RCMsg is constructor.
	 * The initialization when the instance of NPBL0020_RCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPBL0020_RCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnScrQueryColNm = (EZDCStringItem)newItem("xxComnScrQueryColNm");
		xxComnScrColValTxt = (EZDCStringItem)newItem("xxComnScrColValTxt");
		xxSelFlg = (EZDCStringItem)newItem("xxSelFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NPBL0020_RCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPBL0020_RCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnScrQueryColNm", "xxComnScrQueryColNm", null, null, TYPE_HANKAKUEISU, "27", null},
	{"xxComnScrColValTxt", "xxComnScrColValTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"xxSelFlg", "xxSelFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt
        {"XX_SEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelFlg
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

