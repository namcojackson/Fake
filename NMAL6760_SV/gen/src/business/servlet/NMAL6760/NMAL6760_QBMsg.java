//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180710115010000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6760_QBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6760;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6760 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6760_QBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ACCT_NUM_QL*/
	public final EZDBStringItem              dsAcctNum_QL;

    /** BILL_TO_CUST_CD_QL*/
	public final EZDBStringItem              billToCustCd_QL;

    /** SHIP_TO_CUST_CD_QL*/
	public final EZDBStringItem              shipToCustCd_QL;


	/**
	 * NMAL6760_QBMsg is constructor.
	 * The initialization when the instance of NMAL6760_QBMsg is generated.
	 */
	public NMAL6760_QBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6760_QBMsg is constructor.
	 * The initialization when the instance of NMAL6760_QBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6760_QBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsAcctNum_QL = (EZDBStringItem)newItem("dsAcctNum_QL");
		billToCustCd_QL = (EZDBStringItem)newItem("billToCustCd_QL");
		shipToCustCd_QL = (EZDBStringItem)newItem("shipToCustCd_QL");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6760_QBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6760_QBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsAcctNum_QL", "dsAcctNum_QL", "QL", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustCd_QL", "billToCustCd_QL", "QL", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd_QL", "shipToCustCd_QL", "QL", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_QL
        {"BILL_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_QL
        {"SHIP_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_QL
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

