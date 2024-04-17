//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230427160253000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1700_GCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1700;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1700 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1700_GCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORD_LINE_CTX_TP_CD_G*/
	public final EZDCStringItem              ordLineCtxTpCd_G;

    /** DS_ORD_LINE_CATG_DESC_TXT_G*/
	public final EZDCStringItem              dsOrdLineCatgDescTxt_G;


	/**
	 * NWAL1700_GCMsg is constructor.
	 * The initialization when the instance of NWAL1700_GCMsg is generated.
	 */
	public NWAL1700_GCMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1700_GCMsg is constructor.
	 * The initialization when the instance of NWAL1700_GCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1700_GCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ordLineCtxTpCd_G = (EZDCStringItem)newItem("ordLineCtxTpCd_G");
		dsOrdLineCatgDescTxt_G = (EZDCStringItem)newItem("dsOrdLineCatgDescTxt_G");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1700_GCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1700_GCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ordLineCtxTpCd_G", "ordLineCtxTpCd_G", "G", null, TYPE_HANKAKUEISU, "30", null},
	{"dsOrdLineCatgDescTxt_G", "dsOrdLineCatgDescTxt_G", "G", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORD_LINE_CTX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLineCtxTpCd_G
        {"DS_ORD_LINE_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgDescTxt_G
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

