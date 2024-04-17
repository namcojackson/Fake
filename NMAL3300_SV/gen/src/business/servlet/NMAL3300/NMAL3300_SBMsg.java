//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20181113090600000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL3300_SBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL3300;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL3300 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL3300_SBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ACCT_NUM_S1*/
	public final EZDBStringItem              dsAcctNum_S1;

    /** BILL_TO_CUST_CD_S1*/
	public final EZDBStringItem              billToCustCd_S1;

    /** SHIP_TO_CUST_CD_S1*/
	public final EZDBStringItem              shipToCustCd_S1;

    /** DS_ACCT_NUM_S2*/
	public final EZDBStringItem              dsAcctNum_S2;

    /** LOC_NUM_S2*/
	public final EZDBStringItem              locNum_S2;


	/**
	 * NMAL3300_SBMsg is constructor.
	 * The initialization when the instance of NMAL3300_SBMsg is generated.
	 */
	public NMAL3300_SBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL3300_SBMsg is constructor.
	 * The initialization when the instance of NMAL3300_SBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL3300_SBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsAcctNum_S1 = (EZDBStringItem)newItem("dsAcctNum_S1");
		billToCustCd_S1 = (EZDBStringItem)newItem("billToCustCd_S1");
		shipToCustCd_S1 = (EZDBStringItem)newItem("shipToCustCd_S1");
		dsAcctNum_S2 = (EZDBStringItem)newItem("dsAcctNum_S2");
		locNum_S2 = (EZDBStringItem)newItem("locNum_S2");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL3300_SBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL3300_SBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsAcctNum_S1", "dsAcctNum_S1", "S1", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustCd_S1", "billToCustCd_S1", "S1", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd_S1", "shipToCustCd_S1", "S1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_S2", "dsAcctNum_S2", "S2", null, TYPE_HANKAKUEISU, "20", null},
	{"locNum_S2", "locNum_S2", "S2", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_S1
        {"BILL_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_S1
        {"SHIP_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_S1
        {"DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_S2
        {"LOC_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_S2
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
