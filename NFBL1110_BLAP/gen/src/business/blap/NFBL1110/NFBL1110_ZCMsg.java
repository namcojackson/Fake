//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160930131141000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBL1110_ZCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFBL1110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFBL1110 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFBL1110_ZCMsg extends EZDCMsg implements EZDSchemaItemDefines {

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
	 * NFBL1110_ZCMsg is constructor.
	 * The initialization when the instance of NFBL1110_ZCMsg is generated.
	 */
	public NFBL1110_ZCMsg() {
		this(false, -1);
	}

	/**
	 * NFBL1110_ZCMsg is constructor.
	 * The initialization when the instance of NFBL1110_ZCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFBL1110_ZCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnScrQueryColNm = (EZDCStringItem)newItem("xxComnScrQueryColNm");
		xxComnScrColValTxt = (EZDCStringItem)newItem("xxComnScrColValTxt");
		xxSelFlg = (EZDCStringItem)newItem("xxSelFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NFBL1110_ZCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFBL1110_ZCMsgArray();
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

