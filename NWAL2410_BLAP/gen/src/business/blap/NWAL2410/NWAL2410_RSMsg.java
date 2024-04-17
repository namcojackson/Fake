//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170131105353000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2410_RSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2410;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2410 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2410_RSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_SCR_COL_VAL_TXT*/
	public final EZDSStringItem              xxComnScrColValTxt;

    /** XX_COMN_SCR_QUERY_COL_NM*/
	public final EZDSStringItem              xxComnScrQueryColNm;

    /** XX_SEL_FLG*/
	public final EZDSStringItem              xxSelFlg;


	/**
	 * NWAL2410_RSMsg is constructor.
	 * The initialization when the instance of NWAL2410_RSMsg is generated.
	 */
	public NWAL2410_RSMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2410_RSMsg is constructor.
	 * The initialization when the instance of NWAL2410_RSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2410_RSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnScrColValTxt = (EZDSStringItem)newItem("xxComnScrColValTxt");
		xxComnScrQueryColNm = (EZDSStringItem)newItem("xxComnScrQueryColNm");
		xxSelFlg = (EZDSStringItem)newItem("xxSelFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2410_RSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2410_RSMsgArray();
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

        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt
        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm
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
