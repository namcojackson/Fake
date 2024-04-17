//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180605185915000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0170_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0170;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0170 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0170_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** BILL_TO_CUST_ACCT_CD_A*/
	public final EZDSStringItem              billToCustAcctCd_A;

    /** BILL_TO_CUST_ACCT_NM_A*/
	public final EZDSStringItem              billToCustAcctNm_A;

    /** LOC_NUM_A*/
	public final EZDSStringItem              locNum_A;


	/**
	 * NFDL0170_ASMsg is constructor.
	 * The initialization when the instance of NFDL0170_ASMsg is generated.
	 */
	public NFDL0170_ASMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0170_ASMsg is constructor.
	 * The initialization when the instance of NFDL0170_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0170_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		billToCustAcctCd_A = (EZDSStringItem)newItem("billToCustAcctCd_A");
		billToCustAcctNm_A = (EZDSStringItem)newItem("billToCustAcctNm_A");
		locNum_A = (EZDSStringItem)newItem("locNum_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0170_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0170_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"billToCustAcctCd_A", "billToCustAcctCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustAcctNm_A", "billToCustAcctNm_A", "A", null, TYPE_HANKAKUEISU, "360", null},
	{"locNum_A", "locNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd_A
        {"BILL_TO_CUST_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctNm_A
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_A
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

