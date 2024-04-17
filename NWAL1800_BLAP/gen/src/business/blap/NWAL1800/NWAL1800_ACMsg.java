//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160907135729000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1800_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1800;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1800 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1800_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_NUM_A*/
	public final EZDCStringItem              dsContrNum_A;

    /** MDSE_CD_A*/
	public final EZDCStringItem              mdseCd_A;

    /** MDSE_DESC_SHORT_TXT_A*/
	public final EZDCStringItem              mdseDescShortTxt_A;

    /** SCHD_AGMT_NUM_A*/
	public final EZDCStringItem              schdAgmtNum_A;


	/**
	 * NWAL1800_ACMsg is constructor.
	 * The initialization when the instance of NWAL1800_ACMsg is generated.
	 */
	public NWAL1800_ACMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1800_ACMsg is constructor.
	 * The initialization when the instance of NWAL1800_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1800_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrNum_A = (EZDCStringItem)newItem("dsContrNum_A");
		mdseCd_A = (EZDCStringItem)newItem("mdseCd_A");
		mdseDescShortTxt_A = (EZDCStringItem)newItem("mdseDescShortTxt_A");
		schdAgmtNum_A = (EZDCStringItem)newItem("schdAgmtNum_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1800_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1800_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrNum_A", "dsContrNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A", "mdseDescShortTxt_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"schdAgmtNum_A", "schdAgmtNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A
        {"SCHD_AGMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtNum_A
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
