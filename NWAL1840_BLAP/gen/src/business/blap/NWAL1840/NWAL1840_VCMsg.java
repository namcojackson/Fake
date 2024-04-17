//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20231010145517000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1840_VCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1840;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1840 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1840_VCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ACCT_NUM_V*/
	public final EZDCStringItem              dsAcctNum_V;

    /** BILL_TO_CUST_CD_V*/
	public final EZDCStringItem              billToCustCd_V;

    /** SHIP_TO_CUST_CD_V*/
	public final EZDCStringItem              shipToCustCd_V;


	/**
	 * NWAL1840_VCMsg is constructor.
	 * The initialization when the instance of NWAL1840_VCMsg is generated.
	 */
	public NWAL1840_VCMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1840_VCMsg is constructor.
	 * The initialization when the instance of NWAL1840_VCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1840_VCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsAcctNum_V = (EZDCStringItem)newItem("dsAcctNum_V");
		billToCustCd_V = (EZDCStringItem)newItem("billToCustCd_V");
		shipToCustCd_V = (EZDCStringItem)newItem("shipToCustCd_V");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1840_VCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1840_VCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsAcctNum_V", "dsAcctNum_V", "V", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustCd_V", "billToCustCd_V", "V", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd_V", "shipToCustCd_V", "V", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_V
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_V
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_V
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

