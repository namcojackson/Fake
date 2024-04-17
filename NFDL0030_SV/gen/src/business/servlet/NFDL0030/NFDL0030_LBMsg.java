//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230320115234000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0030_LBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFDL0030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0030 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0030_LBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CLT_PMT_CONF_LTR_RQST_NUM_L1*/
	public final EZDBBigDecimalItem              cltPmtConfLtrRqstNum_L1;


	/**
	 * NFDL0030_LBMsg is constructor.
	 * The initialization when the instance of NFDL0030_LBMsg is generated.
	 */
	public NFDL0030_LBMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0030_LBMsg is constructor.
	 * The initialization when the instance of NFDL0030_LBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0030_LBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cltPmtConfLtrRqstNum_L1 = (EZDBBigDecimalItem)newItem("cltPmtConfLtrRqstNum_L1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0030_LBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0030_LBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cltPmtConfLtrRqstNum_L1", "cltPmtConfLtrRqstNum_L1", "L1", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CLT_PMT_CONF_LTR_RQST_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltPmtConfLtrRqstNum_L1
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

