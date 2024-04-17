//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230213171414000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1500_QSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1500;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1500 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1500_QSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PO_MSG_PK*/
	public final EZDSBigDecimalItem              poMsgPk;

    /** PO_MSG_SEG_ID*/
	public final EZDSBigDecimalItem              poMsgSegId;

    /** PO_MSG_SUBMT_PSN_CD*/
	public final EZDSStringItem              poMsgSubmtPsnCd;

    /** PO_MSG_TXT*/
	public final EZDSStringItem              poMsgTxt;


	/**
	 * NPAL1500_QSMsg is constructor.
	 * The initialization when the instance of NPAL1500_QSMsg is generated.
	 */
	public NPAL1500_QSMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1500_QSMsg is constructor.
	 * The initialization when the instance of NPAL1500_QSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1500_QSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		poMsgPk = (EZDSBigDecimalItem)newItem("poMsgPk");
		poMsgSegId = (EZDSBigDecimalItem)newItem("poMsgSegId");
		poMsgSubmtPsnCd = (EZDSStringItem)newItem("poMsgSubmtPsnCd");
		poMsgTxt = (EZDSStringItem)newItem("poMsgTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1500_QSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1500_QSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"poMsgPk", "poMsgPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"poMsgSegId", "poMsgSegId", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"poMsgSubmtPsnCd", "poMsgSubmtPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"poMsgTxt", "poMsgTxt", null, null, TYPE_HANKAKUEISU, "65", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PO_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poMsgPk
        {"PO_MSG_SEG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poMsgSegId
        {"PO_MSG_SUBMT_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poMsgSubmtPsnCd
        {"PO_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poMsgTxt
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

