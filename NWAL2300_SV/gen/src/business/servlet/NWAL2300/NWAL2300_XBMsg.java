//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160317093857000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2300_XBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2300;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2300 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2300_XBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** INV_NUM_X1*/
	public final EZDBStringItem              invNum_X1;

    /** INV_DT_X1*/
	public final EZDBDateItem              invDt_X1;

    /** INV_TOT_DEAL_NET_AMT_X1*/
	public final EZDBBigDecimalItem              invTotDealNetAmt_X1;

    /** DEAL_RMNG_BAL_GRS_AMT_X1*/
	public final EZDBBigDecimalItem              dealRmngBalGrsAmt_X1;


	/**
	 * NWAL2300_XBMsg is constructor.
	 * The initialization when the instance of NWAL2300_XBMsg is generated.
	 */
	public NWAL2300_XBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2300_XBMsg is constructor.
	 * The initialization when the instance of NWAL2300_XBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2300_XBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		invNum_X1 = (EZDBStringItem)newItem("invNum_X1");
		invDt_X1 = (EZDBDateItem)newItem("invDt_X1");
		invTotDealNetAmt_X1 = (EZDBBigDecimalItem)newItem("invTotDealNetAmt_X1");
		dealRmngBalGrsAmt_X1 = (EZDBBigDecimalItem)newItem("dealRmngBalGrsAmt_X1");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2300_XBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2300_XBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"invNum_X1", "invNum_X1", "X1", null, TYPE_HANKAKUEISU, "13", null},
	{"invDt_X1", "invDt_X1", "X1", null, TYPE_NENTSUKIHI, "8", null},
	{"invTotDealNetAmt_X1", "invTotDealNetAmt_X1", "X1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealRmngBalGrsAmt_X1", "dealRmngBalGrsAmt_X1", "X1", null, TYPE_SEISU_SYOSU, "19", "4"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_X1
        {"INV_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//invDt_X1
        {"INV_TOT_DEAL_NET_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealNetAmt_X1
        {"DEAL_RMNG_BAL_GRS_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//dealRmngBalGrsAmt_X1
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
