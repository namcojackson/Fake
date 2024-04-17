//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20170314104931000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFZC601001_InvoiceInfoListPMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFZC601001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFZC601001_InvoiceInfoListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** INV_NUM*/
	public final EZDPStringItem              invNum;

    /** DEAL_APPLY_AMT*/
	public final EZDPBigDecimalItem              dealApplyAmt;


	/**
	 * NFZC601001_InvoiceInfoListPMsg is constructor.
	 * The initialization when the instance of NFZC601001_InvoiceInfoListPMsg is generated.
	 */
	public NFZC601001_InvoiceInfoListPMsg() {
		this(false, -1);
	}

	/**
	 * NFZC601001_InvoiceInfoListPMsg is constructor.
	 * The initialization when the instance of NFZC601001_InvoiceInfoListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFZC601001_InvoiceInfoListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		invNum = (EZDPStringItem)newItem("invNum");
		dealApplyAmt = (EZDPBigDecimalItem)newItem("dealApplyAmt");
	}

	/**
	 * get the type of array which is stored
	 * @return NFZC601001_InvoiceInfoListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFZC601001_InvoiceInfoListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"invNum", "invNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"dealApplyAmt", "dealApplyAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum
        {"DEAL_APPLY_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealApplyAmt
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
