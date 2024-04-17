//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180116114852000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1530_PSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1530;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1530 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1530_PSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_SCR_QUERY_COL_NM*/
	public final EZDSStringItem              xxComnScrQueryColNm;

    /** XX_COMN_SCR_COL_VAL_TXT*/
	public final EZDSStringItem              xxComnScrColValTxt;

    /** XX_SEL_FLG*/
	public final EZDSStringItem              xxSelFlg;


	/**
	 * NPAL1530_PSMsg is constructor.
	 * The initialization when the instance of NPAL1530_PSMsg is generated.
	 */
	public NPAL1530_PSMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1530_PSMsg is constructor.
	 * The initialization when the instance of NPAL1530_PSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1530_PSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnScrQueryColNm = (EZDSStringItem)newItem("xxComnScrQueryColNm");
		xxComnScrColValTxt = (EZDSStringItem)newItem("xxComnScrColValTxt");
		xxSelFlg = (EZDSStringItem)newItem("xxSelFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1530_PSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1530_PSMsgArray();
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

